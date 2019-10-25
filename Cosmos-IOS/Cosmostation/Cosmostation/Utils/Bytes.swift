//
//  Bytes.swift
//  libEd25519Blake2b
//
//  Created by Stone on 2018/9/3.
//

import Foundation

public typealias Bytes = Array<UInt8>

extension Array where Element == UInt8 {
    init (count bytes: Int) {
        self.init(repeating: 0, count: bytes)
    }
}

public extension String {
    var hex2Bytes: Bytes {

        if self.unicodeScalars.lazy.underestimatedCount % 2 != 0 {
            return []
        }

        var bytes = Bytes()
        bytes.reserveCapacity(self.unicodeScalars.lazy.underestimatedCount / 2)

        var buffer: UInt8?
        var skip = self.hasPrefix("0x") ? 2 : 0
        for char in self.unicodeScalars.lazy {
            guard skip == 0 else {
                skip -= 1
                continue
            }
            guard char.value >= 48 && char.value <= 102 else {
                return []
            }
            let v: UInt8
            let c: UInt8 = UInt8(char.value)
            switch c {
            case let c where c <= 57:
                v = c - 48
            case let c where c >= 65 && c <= 70:
                v = c - 55
            case let c where c >= 97:
                v = c - 87
            default:
                return []
            }
            if let b = buffer {
                bytes.append(b << 4 | v)
                buffer = nil
            } else {
                buffer = v
            }
        }
        if let b = buffer {
            bytes.append(b)
        }

        return bytes
    }
}
