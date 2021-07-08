//
//  BinanceMessage.swift
//  Cosmostation
//
//  Created by yongjoo jung on 2021/06/30.
//  Copyright © 2021 wannabit. All rights reserved.
//

import Foundation
import HDWalletKit
import CryptoSwift

public class BinanceMessage {
    
    private enum MessageType: String {
        case newOrder = "CE6DC043"
        case cancelOrder = "166E681B"
        case freeze = "E774B32D"
        case unfreeze = "6515FF0D"
        case transfer = "2A2C87FA"
        case vote = "A1CADD36"
        case stdtx = "F0625DEE"
        case signature = ""
        case publicKey = "EB5AE987"
        
        case createHtlc = "B33F9A24"
        case claimHtlc = "C1665300"
        case refundHtlc = "3454A27C"
    }
    
    private enum Source: Int {
        case hidden = 0
        case broadcast = 1
        case cosmostation = 82
    }
    
    private var type: MessageType = .newOrder
    private var symbol: String = ""
    private var orderId: String = ""
    private var orderType: OrderType = .limit
    private var side: Side = .buy
    private var price: Double = 0
    private var amount: Double = 0
    private var quantity: Double = 0
    private var timeInForce: TimeInForce = .goodTillExpire
    private var data: Data = Data()
    private var memo: String = ""
    private var toAddress: String = ""
    private var proposalId: Int = 0
    private var voteOption: VoteOption = .no
    private var source: Source = .cosmostation

    private var swapID: String = ""
    private var randomNumber: String = ""
    private var recipientOtherChain: String = ""
    private var senderOtherChain: String = ""
    private var randomNumberHash: String = ""
    private var timestamp: Int64 = 0
    private var tokenAmount: [Token] = []
    private var expectedIncome: String = ""
    private var heightSpan: Int64 = 0
    private var crossChain: Bool = true
    
    
    private var key: PrivateKey!
    private var publicKey: Data = Data()
    private var signerAddress: String = ""
    private var sequence: Int = 0
    private var accountNumber: Int = 0
    private var chainId: String = ""
    
    
    

    private init(type: MessageType, _ privateKey: PrivateKey, _ signerAddress: String, _ sequence: Int, _ accountNumber: Int, _ chainId: String) {
        self.type = type
        self.key = privateKey
        self.publicKey = key.publicKey.data
        self.signerAddress = signerAddress
        self.sequence = sequence
        self.accountNumber = accountNumber
        self.chainId = chainId
    }
    
    
    public static func transfer(symbol: String, amount: Double, toAddress: String, memo: String,
                                privateKey: PrivateKey, signerAddress: String, sequence: Int, accountNumber: Int, chainId: String) -> BinanceMessage {
        let message = BinanceMessage(type: .transfer, privateKey, signerAddress, sequence, accountNumber, chainId)
        message.symbol = symbol
        message.amount = amount
        message.toAddress = toAddress
        message.memo = memo
        return message
    }
    
    public static func createHtlc(toAddress: String, otherFrom: String, otherTo: String,
                                   timestamp: Int64, randomNumberHash: String, sendAmount: Int64, sendDenom: String,
                                   expectedIncom: String, heightSpan: Int64, crossChain: Bool, memo: String,
                                   privateKey: PrivateKey, signerAddress: String, sequence: Int, accountNumber: Int, chainId: String) -> BinanceMessage {
        let message = BinanceMessage(type: .createHtlc, privateKey, signerAddress, sequence, accountNumber, chainId)
        message.toAddress = toAddress
        message.senderOtherChain = otherFrom
        message.recipientOtherChain = otherTo
        message.timestamp = timestamp
        message.randomNumberHash = randomNumberHash

        var token = Token.init()
        token.amount = sendAmount
        token.denom = sendDenom
        message.tokenAmount = [token]

        message.expectedIncome = expectedIncom
        message.heightSpan = heightSpan
        message.crossChain = crossChain
        message.memo = memo
        return message
    }
    
    public static func claimHtlc(randomNumber: String, swapId: String, memo: String,
                                 privateKey: PrivateKey, signerAddress: String, sequence: Int, accountNumber: Int, chainId: String) -> BinanceMessage {
        let message = BinanceMessage(type: .claimHtlc, privateKey, signerAddress, sequence, accountNumber, chainId)
        message.randomNumber = randomNumber
        message.swapID = swapId
        message.memo = memo
        return message
    }
    
    public static func refundHtlc(swapId: String, memo: String,
                                  privateKey: PrivateKey, signerAddress: String, sequence: Int, accountNumber: Int, chainId: String) -> BinanceMessage {
        let message = BinanceMessage(type: .refundHtlc, privateKey, signerAddress, sequence, accountNumber, chainId)
        message.swapID = swapId
        message.memo = memo
        return message
    }
    
    
    
    public func encode() throws -> Data {
        var message = Data()
        message.append(self.type.rawValue.unhexlify)
        message.append(try self.body(for: self.type))
        let signature = try self.body(for: .signature)

        var stdtx = BinanceStdTx()
        stdtx.msgs.append(message)
        stdtx.signatures.append(signature)
        stdtx.memo = self.memo
        stdtx.source = Int64(Source.cosmostation.rawValue)
        stdtx.data = self.data

        var content = Data()
        content.append(MessageType.stdtx.rawValue.unhexlify)
        content.append(try stdtx.serializedData())

        var transaction = Data()
        transaction.append(content.count.varint)
        transaction.append(content)

        return transaction

    }
    
    
    private func body(for type: MessageType) throws -> Data {

        switch (type) {

        case .transfer:
            var token = Send.Token()
            token.denom = self.symbol
            token.amount = Int64(self.amount.encoded)

            var input = Send.Input()
            input.address = self.address(from: self.signerAddress).unhexlify
            input.coins = [token]

            var output = Send.Output()
            output.address = self.address(from: self.toAddress).unhexlify
            output.coins = [token]
            
            var send = Send()
            send.inputs.append(input)
            send.outputs.append(output)
            return try send.serializedData()
            
        case .createHtlc:
            var createHtlc = HashTimerLockTransferMsg()
            createHtlc.from = self.address(from: self.signerAddress).unhexlify
            createHtlc.to = self.address(from: toAddress).unhexlify
            createHtlc.senderOtherChain = self.senderOtherChain
            createHtlc.recipientOtherChain = self.recipientOtherChain
            createHtlc.randomNumberHash = self.randomNumberHash.unhexlify
            createHtlc.timestamp = self.timestamp
            createHtlc.amount = self.tokenAmount
            createHtlc.expectedIncome = self.expectedIncome
            createHtlc.heightSpan = self.heightSpan
            createHtlc.crossChain = self.crossChain
            return try createHtlc.serializedData()
            
        case .claimHtlc:
            var claimHtlc = ClaimHashTimerLockMsg()
            claimHtlc.from = self.address(from: self.signerAddress).unhexlify
            claimHtlc.swapID = self.swapID.unhexlify
            claimHtlc.randomNumber = self.randomNumber.unhexlify
            return try claimHtlc.serializedData()
            
            
        case .refundHtlc:
            var refundHtlc = RefundHashTimerLockMsg()
            refundHtlc.from = self.address(from: self.signerAddress).unhexlify
            refundHtlc.swapID = self.swapID.unhexlify
            return try refundHtlc.serializedData()

        case .signature:
            var pb = StdSignature()
            pb.sequence = Int64(self.sequence)
            pb.accountNumber = Int64(self.accountNumber)
            pb.pubKey = try self.body(for: .publicKey)
            pb.signature = self.signature()
            return try pb.serializedData()

        case .publicKey:
            let key = self.publicKey
            var data = Data()
            data.append(type.rawValue.unhexlify)
            data.append(key.count.varint)
            data.append(key)
            return data
            
        default:
            return Data()

        }

    }
    
    private func signature() -> Data {
        let json = self.json(for: .signature)
        let data = Data(json.utf8)
        return self.sign(self.key.raw, data)
    }
    
    private func json(for type: MessageType) -> String {

        switch (type) {
        
        case .transfer:
            return String(format: JSON.transfer,
                          self.signerAddress,
                          self.amount.encoded,
                          self.symbol,
                          self.toAddress,
                          self.amount.encoded,
                          self.symbol)
            
            
        case .createHtlc:
            return String(format: JSON.createHtlc,
                          self.tokenAmount[0].amount,
                          self.tokenAmount[0].denom,
                          self.crossChain == true ? "true" : "false",
                          self.expectedIncome,
                          self.signerAddress,
                          self.heightSpan,
                          self.randomNumberHash,
                          self.recipientOtherChain,
                          self.senderOtherChain,
                          self.timestamp,
                          self.toAddress)
                              
        case .claimHtlc:
          return String(format: JSON.claimHtlc,
                        self.signerAddress,
                        self.randomNumber,
                        self.swapID)
            
        case .refundHtlc:
            return String(format: JSON.refundHtlc,
                          self.signerAddress,
                          self.swapID)

        case .signature:
            return String(format: JSON.signature,
                          self.accountNumber,
                          self.chainId,
                          self.memo,
                          self.json(for: self.type),
                          sequence,
                          self.source.rawValue)

        default:
            return "{}"
            
        }
    }
    
    public func address(from account: String) -> String {
        do {
            let (_, data) = try Bech32().decode(account)
            let bits = try BinanceSegwitAddrCoder().convertBits(from: 5, to: 8, pad: false, idata: data)
            return bits.hexlify
        } catch {
            return "Invalid Key"
        }
    }
    
    public func sign(_ pkey: Data?, _ message: Data?) -> Data {
        do {
            return try ECDSA.compactsign(message!.sha256(), privateKey: pkey!)
        } catch let error {
            print(error)
        }
        return message!
    }
}



fileprivate extension Double {
    var encoded: Int {
        // Multiply by 1e8 (10^8) and round to int
        return Int(self * pow(10, 8))
    }
}

fileprivate class JSON {

    // Signing requires a strictly ordered JSON string. Neither swift nor
    // SwiftyJSON maintained the order, so instead we use strings.

    static let signature = """
    {"account_number":"%d","chain_id":"%@","data":null,"memo":"%@","msgs":[%@],"sequence":"%d","source":"%d"}
    """

    static let newOrder = """
    {"id":"%@","ordertype":%d,"price":%d,"quantity":%d,"sender":"%@","side":%d,"symbol":"%@","timeinforce":%d}
    """

    static let cancelOrder = """
    {"refid":"%@","sender":"%@","symbol":"%@"}
    """

    static let freeze = """
    {"amount":%ld,"from":"%@","symbol":"%@"}
    """

    static let unfreeze = """
    {"amount":%ld,"from":"%@","symbol":"%@"}
    """

    static let transfer = """
    {"inputs":[{"address":"%@","coins":[{"amount":%ld,"denom":"%@"}]}],"outputs":[{"address":"%@","coins":[{"amount":%ld,"denom":"%@"}]}]}
    """

    static let vote = """
    {"option":%d,"proposal_id":%d,voter":"%@"}
    """

    static let createHtlc = """
    {"amount":[{"amount":%ld,"denom":"%@"}],"cross_chain":%@,"expected_income":"%@","from":"%@","height_span":%ld,"random_number_hash":"%@","recipient_other_chain":"%@","sender_other_chain":"%@","timestamp":%ld,"to":"%@"}
    """
    
    static let claimHtlc = """
    {"from":"%@","random_number":"%@","swap_id":"%@"}
    """
    
    static let refundHtlc = """
    {"from":"%@","swap_id":"%@"}
    """
}

