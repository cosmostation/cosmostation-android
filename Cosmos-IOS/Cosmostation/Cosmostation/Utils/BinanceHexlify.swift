//
//  BinanceHexlify.swift
//  Cosmostation
//
//  Created by yongjoo jung on 2021/06/30.
//  Copyright © 2021 wannabit. All rights reserved.
//

import Foundation

extension String {
    var unhexlify: Data {
        let length = self.count / 2
        var data = Data(capacity: length)
        for i in 0 ..< length {
            let j = self.index(self.startIndex, offsetBy: i * 2)
            let k = self.index(j, offsetBy: 2)
            let bytes = self[j..<k]
            if var byte = UInt8(bytes, radix: 16) {
                data.append(&byte, count: 1)
            }
        }
        return data
    }

}

extension Data {
    var hexdata: Data {
        return Data(self.hexlify.utf8)
    }

    var hexlify: String {
        let hexDigits = Array("0123456789abcdef".utf16)
        var hexChars = [UTF16.CodeUnit]()
        hexChars.reserveCapacity(count * 2)
        for byte in self {
            let (index1, index2) = Int(byte).quotientAndRemainder(dividingBy: 16)
            hexChars.append(hexDigits[index1])
            hexChars.append(hexDigits[index2])
        }
        return String(utf16CodeUnits: hexChars, count: hexChars.count)
    }

}
