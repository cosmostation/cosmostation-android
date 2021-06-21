//
//  HdacUtxo.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/06/07.
//  Copyright © 2021 wannabit. All rights reserved.
//

import Foundation
import HDWalletKit

public struct HdacUtxo {
    var unspent_hash: String?
    var address: String?
    var scriptPubKey: String?
    var amount: String?
    var vout: UInt32?
    var confirmations: Int64?
    var satoshis: UInt64?
    var txid: String?
    
    init(_ dictionary: NSDictionary?) {
        self.unspent_hash = dictionary?["unspent_hash"] as? String
        self.address = dictionary?["address"] as? String
        self.scriptPubKey = dictionary?["scriptPubKey"] as? String
        self.amount = dictionary?["amount"] as? String
        self.vout = dictionary?["vout"] as? UInt32
        self.confirmations = dictionary?["confirmations"] as? Int64
        self.satoshis = dictionary?["satoshis"] as? UInt64
        self.txid = dictionary?["txid"] as? String
    }
    
    func toUTXO() -> UnspentTransaction {
        let rawValue = satoshis
        let rawlockingScript = self.scriptPubKey!.hexadecimal
        let output = TransactionOutput(value: rawValue!, lockingScript: rawlockingScript!)
        let reverseRawHashData = self.txid!.groups(of: 2).reversed().joined().hexadecimal
        let outpoint = TransactionOutPoint(hash: reverseRawHashData!, index: vout!)
        return UnspentTransaction(output: output, outpoint: outpoint)
    }
    
}

extension String {
    var hexadecimal: Data? {
        var data = Data(capacity: count / 2)
        let regex = try! NSRegularExpression(pattern: "[0-9a-f]{1,2}", options: .caseInsensitive)
        regex.enumerateMatches(in: self, range: NSRange(startIndex..., in: self)) { match, _, _ in
            let byteString = (self as NSString).substring(with: match!.range)
            let num = UInt8(byteString, radix: 16)!
            data.append(num)
        }
        
        guard data.count > 0 else { return nil }
        return data
    }
}

extension Collection {
    func groups(of n: Int) -> [SubSequence] {
        var startIndex = self.startIndex
        let count = self.count
        return (0..<count/n).map { _ in
            var endIndex = index(startIndex, offsetBy: n, limitedBy: self.endIndex) ?? self.endIndex
            if count % n > 0, distance(from: self.startIndex, to: startIndex) > (count / n) {
                endIndex = self.endIndex
            }
            defer { startIndex = endIndex }
            return self[startIndex..<endIndex]
        }
    }
}
