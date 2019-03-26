//
//  WKeyUtils.swift
//  Cosmostation
//
//  Created by yongjoo on 25/03/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import Foundation
import BitcoinKit

class WKey {
    let PRE_COSMOS_PUB_KEY = "eb5ae98721";
    let PRE_COSMOS_PRI_KEY = "e1b0f79b20";
    
    static func getMasterKeyFromWords(mnemonic m: [String]) -> HDPrivateKey {
        return HDPrivateKey(seed: Mnemonic.seed(mnemonic: m), network: .testnet)
    }
    
    static func getCosmosKeyFromWords(mnemonic m: [String], path p:UInt32) -> HDPrivateKey {
        let masterKey = getMasterKeyFromWords(mnemonic: m)
         return try! masterKey.derived(at: 44, hardened: true).derived(at: 118, hardened: true).derived(at: 0, hardened: true).derived(at: 0).derived(at: p)
    }
    
    static func getCosmosDpAddress(key hdKey:HDPrivateKey) -> String {
        let sha256 = Crypto.sha256(hdKey.privateKey().publicKey().raw)
        let ripemd160 = Crypto.ripemd160(sha256)
        return try! SegwitAddrCoder.shared.encode2(hrp: "cosmos", program: ripemd160)
    }
}
