//
//  WCBinanceCancelOrder.swift
//  WalletConnect
//
//  Created by Tao Xu on 4/7/19.
//  Copyright © 2019 Trust. All rights reserved.
//

import Foundation

public struct WCBinanceCancelOrder: WCBinanceOrder, Codable {
    public struct Message: Codable {
        public let refid: String
        public let sender: String
        public let symbol: String
    }
    public let account_number: String
    public let chain_id: String
    public let data: String?
    public let memo: String
    public let msgs: [Message]
    public let sequence: String
    public let source: String

    public func encode(to encoder: Encoder) throws {
        var container = encoder.container(keyedBy: CodingKeys.self)
        try container.encode(account_number, forKey: .account_number)
        try container.encode(chain_id, forKey: .chain_id)
        try container.encode(data, forKey: .data)
        try container.encode(memo, forKey: .memo)
        try container.encode(msgs, forKey: .msgs)
        try container.encode(sequence, forKey: .sequence)
        try container.encode(source, forKey: .source)
    }
}
