//
//  WCInteractor.swift
//  WalletConnect
//
//  Created by Tao Xu on 3/29/19.
//  Copyright © 2019 Trust. All rights reserved.
//

import Foundation
import Starscream
import PromiseKit

public typealias SessionRequestClosure = (_ id: Int64, _ peer: WCPeerMeta) -> Void
public typealias DisconnectClosure = (Error?) -> Void
public typealias EthSignClosure = (_ id: Int64, _ params: [String]) -> Void
public typealias EthSendTransactionClosure = (_ id: Int64, _ transaction: WCEthereumSendTransaction) -> Void
public typealias BnbSignClosure = (_ id: Int64, _ order: WCBinanceOrder) -> Void

func print(_ items: Any..., separator: String = " ", terminator: String = "\n") {
    #if DEBUG
    items.forEach {
        Swift.print($0, separator: separator, terminator: terminator)
    }
    #endif
}

public class WCInteractor {
    public let session: WCSession
    public var connected: Bool {
        return socket.isConnected
    }

    public let clientId: String
    public let clientMeta: WCPeerMeta

    // incoming event handlers
    public var onSessionRequest: SessionRequestClosure?
    public var onDisconnect: DisconnectClosure?
    public var onEthSign: EthSignClosure?
    public var onEthSendTransaction: EthSendTransactionClosure?
    public var onBnbSign: BnbSignClosure?

    // outgoing promise resolvers
    var connectResolver: Resolver<Bool>?
    var bnbTxConfirmResolvers: [Int64: Resolver<WCBinanceTxConfirmParam>] = [:]

    private let socket: WebSocket
    private var handshakeId: Int64 = -1
    private var pingTimer: Timer?

    private var peerId: String?
    private var peerMeta: WCPeerMeta?

    public init(session: WCSession, meta: WCPeerMeta) {
        self.session = session
        self.clientId = (UIDevice.current.identifierForVendor ?? UUID()).description.lowercased()
        self.clientMeta = meta
        self.socket = WebSocket.init(url: session.bridge)

        socket.onConnect = { [weak self] in self?.onConnect() }
        socket.onDisconnect = { [weak self] error in self?.onDisconnect(error: error) }
        socket.onText = { [weak self] text in self?.onReceiveMessage(text: text) }
        socket.onPong = { _ in print("<== pong") }
        socket.onData = { data in print("<== websocketDidReceiveData: \(data.toHexString())") }
    }

    deinit {
        disconnect()
    }

    public func connect() -> Promise<Bool> {
        if socket.isConnected {
            return Promise.value(true)
        }
        socket.connect()
        return Promise<Bool> { [weak self] seal in
            self?.connectResolver = seal
        }
    }

    public func disconnect() {
        pingTimer?.invalidate()
        socket.disconnect()
        connectResolver = nil
        handshakeId = -1
    }

    public func approveSession(accounts: [String], chainId: Int) -> Promise<Void> {
        guard handshakeId > 0 else {
            return Promise(error: WCError.invalidSession)
        }
        let result = WCApproveSessionResponse(
            approved: true,
            chainId: chainId,
            accounts: accounts,
            peerId: clientId,
            peerMeta: clientMeta
        )
        let response = JSONRPCResponse(id: handshakeId, result: result)
        return encryptAndSend(data: response.encoded)
    }

    public func rejectSession(_ message: String = "Session Rejected") -> Promise<Void> {
        guard handshakeId > 0 else {
            return Promise(error: WCError.invalidSession)
        }
        let response = JSONRPCErrorResponse(id: handshakeId, error: JSONRPCError(code: -32000, message: message))
        return encryptAndSend(data: response.encoded)
    }

    public func killSession() -> Promise<Void> {
        let result = WCSessionUpdateParam(approved: false, chainId: nil, accounts: nil)
        let response = JSONRPCRequest(id: generateId(), method: WCEvent.sessionUpdate.rawValue, params: [result])
        return encryptAndSend(data: response.encoded)
            .map { [weak self] in
            self?.disconnect()
        }
    }

    public func approveBnbOrder(id: Int64, signed: WCBinanceOrderSignature) -> Promise<WCBinanceTxConfirmParam> {
        let result = signed.encodedString
        return approveRequest(id: id, result: result)
            .then { _ -> Promise<WCBinanceTxConfirmParam> in
                return Promise { [weak self] seal in
                    self?.bnbTxConfirmResolvers[id] = seal
                }
            }
    }

    public func approveRequest(id: Int64, result: String) -> Promise<Void> {
        let response = JSONRPCResponse(id: id, result: result)
        return encryptAndSend(data: response.encoded)
    }

    public func rejectRequest(id: Int64, message: String) -> Promise<Void> {
        let response = JSONRPCErrorResponse(id: id, error: JSONRPCError(code: -32000, message: message))
        return encryptAndSend(data: response.encoded)
    }
}

extension WCInteractor {
    private func subscribe(topic: String) {
        let message = WCSocketMessage(topic: topic, type: .sub, payload: "")
        let data = try! JSONEncoder().encode(message)
        socket.write(data: data)
        print("==> subscribe: \(String(data: data, encoding: .utf8)!)")
    }

    private func encryptAndSend(data: Data) -> Promise<Void> {
        print("==> encrypt: \(String(data: data, encoding: .utf8)!) ")
        let encoder = JSONEncoder()
        let payload = try! WCEncryptor.encrypt(data: data, with: session.key)
        let payloadString = encoder.encodeAsUTF8(payload)
        let message = WCSocketMessage(topic: peerId ?? session.topic, type: .pub, payload: payloadString)
        let data = message.encoded
        return Promise { seal in
            socket.write(data: data) {
                print("==> sent \(String(data: data, encoding: .utf8)!) ")
                seal.fulfill(())
            }
        }
    }

    private func handleEvent(_ event: WCEvent, topic: String, decrypted: Data) {
        do {
            switch event {
            // topic == session.topic
            case .sessionRequest:
                let request: JSONRPCRequest<[WCSessionRequestParam]> = try event.decode(decrypted)
                guard let params = request.params.first else {
                    throw WCError.badJSONRPCRequest
                }
                handshakeId = request.id
                peerId = params.peerId
                peerMeta = params.peerMeta
                onSessionRequest?(request.id, params.peerMeta)
            // topic == clientId
            case .ethSign, .ethPersonalSign:
                let request: JSONRPCRequest<[String]> = try event.decode(decrypted)
                onEthSign?(request.id, request.params)
            case .ethSendTransaction:
                let request: JSONRPCRequest<[WCEthereumSendTransaction]> = try event.decode(decrypted)
                guard request.params.count > 0 else {
                    throw WCError.badJSONRPCRequest
                }
                onEthSendTransaction?(request.id, request.params[0])
            case .bnbSign:
                if let request: JSONRPCRequest<[WCBinanceTradeOrder]> = try? event.decode(decrypted) {
                    onBnbSign?(request.id, request.params[0])
                } else if let request: JSONRPCRequest<[WCBinanceCancelOrder]> = try? event.decode(decrypted) {
                    onBnbSign?(request.id, request.params[0])
                } else if let request: JSONRPCRequest<[WCBinanceTransferOrder]> = try? event.decode(decrypted) {
                    onBnbSign?(request.id, request.params[0])
                }
                break
            case .bnbTransactionConfirm:
                let request: JSONRPCRequest<[WCBinanceTxConfirmParam]> = try event.decode(decrypted)
                guard request.params.count > 0 else {
                    throw WCError.badJSONRPCRequest
                }
                bnbTxConfirmResolvers[request.id]?.fulfill(request.params[0])
                bnbTxConfirmResolvers[request.id] = nil
            case .sessionUpdate:
                let request: JSONRPCRequest<[WCSessionUpdateParam]> = try event.decode(decrypted)
                guard let param = request.params.first else {
                    throw WCError.badJSONRPCRequest
                }
                if param.approved == false {
                    disconnect()
                }
            default:
                break
            }
        } catch let error {
            print("==> handleEvent error: \(error.localizedDescription)")
        }
    }
}
extension WCInteractor {

    private func onConnect() {
        print("<== websocketDidConnect")
        pingTimer = Timer.scheduledTimer(withTimeInterval: 15, repeats: true, block: { [weak socket] _ in
            print("==> ping")
            socket?.write(ping: Data())
        })
        subscribe(topic: session.topic)
        subscribe(topic: clientId)
        connectResolver?.fulfill(true)
        connectResolver = nil
    }

    private func onDisconnect(error: Error?) {
        print("<== websocketDidDisconnect, error: \(error.debugDescription)")
        pingTimer?.invalidate()
        if let error = error {
            connectResolver?.reject(error)
        } else {
            connectResolver?.fulfill(false)
        }
        connectResolver = nil
        onDisconnect?(error)
    }

    private func onReceiveMessage(text: String) {
        print("<== receive: \(text)")
        guard let (topic, payload) = WCEncryptionPayload.extract(text) else { return }
        do {
            let decrypted = try WCEncryptor.decrypt(payload: payload, with: session.key)
            guard let json = try JSONSerialization.jsonObject(with: decrypted, options: [])
                as? [String: Any] else {
                throw WCError.badServerResponse
            }
            print("<== decrypted: \(String(data: decrypted, encoding: .utf8)!)")
            if let method = json["method"] as? String,
                let event = WCEvent(rawValue: method) {
                handleEvent(event, topic: topic, decrypted: decrypted)
            }
        } catch let error {
            print(error)
        }
    }
}
