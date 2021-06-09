//
//  HdacUtil.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/06/06.
//  Copyright © 2021 wannabit. All rights reserved.
//

import Foundation
import HDWalletKit
import BitcoinKit

class HdacUtil {
    
    static func getMasterKeyFromWords(_ seed: String) -> HDWalletKit.PrivateKey {
        return PrivateKey(seed: Mnemonic.createSeed(mnemonic: seed), coin: .bitcoin)
    }
    
    static func getKey(_ seed: String) -> HDWalletKit.PrivateKey {
        return getMasterKeyFromWords(seed).derived(at: .hardened(44)).derived(at: .hardened(200)).derived(at: .hardened(0)).derived(at: .notHardened(0)).derived(at: .notHardened(0))
    }
    
    static func getBalacne(_ utxos: Array<HdacUtxo>) -> NSDecimalNumber {
        var result = NSDecimalNumber.zero
        utxos.forEach { utxo in
            if let satoshis = utxo.satoshis, let confirmations = utxo.confirmations {
                if (satoshis > 0 && confirmations > 0) {
                    result = result.adding(NSDecimalNumber.init(string: utxo.amount))
                }
            }
        }
        return result
    }
    
    static func getRemainUtxo(_ utxos: Array<HdacUtxo>) -> Array<HdacUtxo> {
        var result = Array<HdacUtxo>()
        utxos.forEach { utxo in
            if let satoshis = utxo.satoshis, let confirmations = utxo.confirmations {
                if (satoshis > 0 && confirmations > 0) {
                    result.append(utxo)
                }
            }
        }
        return result
    }
    
    static func generateHdacAddress(_ publickey: HDWalletKit.PublicKey) -> String {
        let prefix = Data([0x28])
        let publicKeyy = publickey.getPublicKey(compressed: true)
        let payload = Crypto.ripemd160(publicKeyy.sha256())
        
        var checksum = (prefix + payload).sha256().sha256().prefix(4)
        checksum = swapUInt32Data(checksum)
        var hdacChecksum = dataWithHexString(hex: "48444143")
        hdacChecksum = swapUInt32Data(hdacChecksum)
        var result = Data.getxor(left: checksum, right: hdacChecksum)
        result = swapUInt32Data(result)
        return HDWalletKit.Base58.encode(prefix + payload + result)
    }
    
    static func swapUInt32Data(_ data: Data) -> Data {
        var mdata = data // make a mutable copy
        let count = data.count / MemoryLayout<UInt32>.size
        mdata.withUnsafeMutableBytes { (i16ptr: UnsafeMutablePointer<UInt32>) in
            for i in 0..<count {
                i16ptr[i] =  i16ptr[i].byteSwapped
            }
        }
        return mdata
    }
}


extension Data {
    static func getxor (left: Data, right: Data) -> Data {
        if left.count != right.count {
            NSLog("Warning! XOR operands are not equal. left = \(left), right = \(right)")
        }

        var result: Data = Data()
        var smaller: Data, bigger: Data
        if left.count <= right.count {
            smaller = left
            bigger = right
        } else {
            smaller = right
            bigger = left
        }

        let bs:[UInt8] = Array(smaller)
        let bb:[UInt8] = Array (bigger)
        var br = [UInt8] ()
        for i in 0..<bs.count {
            br.append(bs[i] ^ bb[i])
        }
        for j in bs.count..<bb.count {
            br.append(bb[j])
        }
        result = Data(br)
        return result
    }
        
}
