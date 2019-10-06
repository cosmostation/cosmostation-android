//
//  Encodable+Extension.swift
//  WalletConnect
//
//  Created by Tao Xu on 4/1/19.
//  Copyright © 2019 Trust. All rights reserved.
//

import Foundation

extension Encodable {
    public var encoded: Data {
        let encoder = JSONEncoder()
        encoder.outputFormatting = [.sortedKeys]
        return try! encoder.encode(self)
    }
    public var encodedString: String {
        return String(data: encoded, encoding: .utf8)!
    }
}
