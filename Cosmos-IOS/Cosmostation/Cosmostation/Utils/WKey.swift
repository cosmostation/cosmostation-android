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
    
    static func isValidateAddressOrPubKey(_ address:String) -> Bool {
        let bech32 = Bech32()
        guard let _ = try? bech32.decode(address) else {
            return false
        }
        return true
    }
    
    static func getCosmosAddressFromPubKey(_ pubkey:String) -> String {
        var result = ""
        let bech32 = Bech32()
        do {
            guard let (_, data) = try? bech32.decode(pubkey) else {
                return result
            }
            let converted = try convertBits(from: 5, to: 8, pad: false, idata: data)
            let convertedhex = converted.hexEncodedString().replacingOccurrences(of: "eb5ae98721", with: "")
            let sha256 = Crypto.sha256(dataWithHexString(hex: convertedhex))
            let ripemd160 = Crypto.ripemd160(sha256)
            result = try! SegwitAddrCoder.shared.encode2(hrp: "cosmos", program: ripemd160)
        } catch {
            print(error)
        }
        
        
        return result;
    }
    
    
    static func convertBits(from: Int, to: Int, pad: Bool, idata: Data) throws -> Data {
        var acc: Int = 0
        var bits: Int = 0
        let maxv: Int = (1 << to) - 1
        let maxAcc: Int = (1 << (from + to - 1)) - 1
        var odata = Data()
        for ibyte in idata {
            acc = ((acc << from) | Int(ibyte)) & maxAcc
            bits += from
            while bits >= to {
                bits -= to
                odata.append(UInt8((acc >> bits) & maxv))
            }
        }
        if pad {
            if bits != 0 {
                odata.append(UInt8((acc << (to - bits)) & maxv))
            }
        } else if (bits >= from || ((acc << (to - bits)) & maxv) != 0) {
            print("error")
        }
        return odata
    }
}
