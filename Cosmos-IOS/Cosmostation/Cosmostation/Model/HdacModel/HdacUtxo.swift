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
    var satoshis: Int64?
    var txid: String?
    
    init(_ dictionary: NSDictionary?) {
        self.unspent_hash = dictionary?["unspent_hash"] as? String
        self.address = dictionary?["address"] as? String
        self.scriptPubKey = dictionary?["scriptPubKey"] as? String
        self.amount = dictionary?["amount"] as? String
        self.vout = dictionary?["vout"] as? UInt32
        self.confirmations = dictionary?["confirmations"] as? Int64
        self.satoshis = dictionary?["satoshis"] as? Int64
        self.txid = dictionary?["txid"] as? String
    }
    
    func toUTXO() -> HDWalletKit.UnspentTransaction {
        let rawValue = NSDecimalNumber.init(string: self.amount).multiplying(byPowerOf10: 8, withBehavior: WUtils.handler0).uint64Value
        let rawlockingScript = dataWithHexString(hex: self.scriptPubKey!)
        let output = TransactionOutput(value: rawValue, lockingScript: rawlockingScript)
        
        let rawHashData = dataWithHexString(hex: self.txid!)
        let outpoint = TransactionOutPoint(hash: rawHashData, index: vout!)
        return UnspentTransaction(output: output, outpoint: outpoint)
    }
    
}
