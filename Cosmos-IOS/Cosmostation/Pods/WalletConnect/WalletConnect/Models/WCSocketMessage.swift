//
//  SocketMessage.swift
//  WalletConnect
//
//  Created by Tao Xu on 3/29/19.
//  Copyright © 2019 Trust. All rights reserved.
//

import Foundation

public struct WCEncryptionPayload: Codable {
    public let data: String
    public let hmac: String
    public let iv: String

    public init(data: String, hmac: String, iv: String) {
        self.data = data
        self.hmac = hmac
        self.iv = iv
    }
}

public struct WCSocketMessage<T: Codable>: Codable {
    public enum MessageType: String, Codable {
        case pub
        case sub
    }
    public let topic: String
    public let type: MessageType
    public let payload: T
}

public extension WCEncryptionPayload {
    static func extract(_ string: String) -> (topic: String, payload: WCEncryptionPayload)? {
        guard let data = string.data(using: .utf8) else {
            return nil
        }
        do {
            let decoder = JSONDecoder()
            if let message = try? decoder.decode(WCSocketMessage<WCEncryptionPayload>.self, from: data) {
                return (message.topic, message.payload)
            } else {
                let message = try decoder.decode(WCSocketMessage<String>.self, from: data)
                let payloadData = message.payload.data(using: .utf8)
                return  (message.topic, try decoder.decode(WCEncryptionPayload.self, from: payloadData!))
            }
        } catch let error {
            print(error)
        }
        return nil
    }
}
