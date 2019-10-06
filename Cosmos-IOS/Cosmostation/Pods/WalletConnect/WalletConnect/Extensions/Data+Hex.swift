//
//  Data+Hex.swift
//  WalletConnect
//
//  Created by Tao Xu on 3/30/19.
//  Copyright © 2019 Trust. All rights reserved.
//

import Foundation
import CryptoSwift

extension Data {
    var hex: String {
        return self.toHexString()
    }
}

extension JSONEncoder {
    func encodeAsUTF8<T>(_ value: T) -> String where T : Encodable {
        guard let data = try? self.encode(value),
            let string = String(data: data, encoding: .utf8) else {
            return ""
        }
        return string
    }
}
