//
//  HdacUtil.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/06/06.
//  Copyright © 2021 wannabit. All rights reserved.
//

import Foundation
import HDWalletKit

class HdacUtil {
    static let HDAC_BURN_ADDRESS_TEST = "hXXXXXXXXXXXXXXXXXXXXXXXXXXXYHCpEz"
    static let HDAC_BURN_ADDRESS_MAIN = "HJXXXXXXXXXXXXXXXXXXXXXXXXXXVarS5i"
    
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
//        let publicKeyy = publickey.getPublicKey(compressed: true)
        let publicKeyy = publickey.compressedPublicKey
        let payload = RIPEMD160.hash(publicKeyy.sha256())

        var checksum = (prefix + payload).sha256().sha256().prefix(4)
        checksum = swapUInt32Data(checksum)
        var hdacChecksum = dataWithHexString(hex: "48444143")
        hdacChecksum = swapUInt32Data(hdacChecksum)
        var result = Data.getxor(left: checksum, right: hdacChecksum)
        result = swapUInt32Data(result)
        return Base58.encode(prefix + payload + result)
    }

    static func createSendTx(_ utxos: [UnspentTransaction], _ recipient: String, _ amount: NSDecimalNumber, _ key: PrivateKey) throws -> String {
        let utxoTransactionBuilder = UtxoTransactionBuilder()
        let utoxTransactionSigner = UtxoTransactionSigner()

        let toAddress: Address = try! HdacAddress.init(recipient, coin: .hdac)
        let changeAddress: Address = try! HdacAddress.init(key.publicKey.address, coin: .hdac)
        let amount: UInt64 = amount.uint64Value

        let totalAmount: UInt64 = utxos.sum()
        let fee: UInt64 = 10000000
        let change: UInt64 = totalAmount - amount - fee
        let destinations: [(Address, UInt64)] = [(toAddress, amount), (changeAddress, change)]
        let unsignedTx = try utxoTransactionBuilder.build(destinations: destinations, utxos: utxos)
        let signedTx = try utoxTransactionSigner.sign(unsignedTx, with: key)
        print("unsignedTx ", unsignedTx.tx.serialized().hex.uppercased())
        print("signedTx ", signedTx.serialized().hex.uppercased())
        return signedTx.serialized().hex
    }
    
    static func createSwapTx(_ utxos: [UnspentTransaction], _ rizonAddress: String, _ key: PrivateKey) throws -> String {
        let utoxTransactionSigner = UtxoTransactionSigner()
        let burnAddress: Address = try! HdacAddress.init(HDAC_BURN_ADDRESS, coin: .hdac)
        let totalAmount: UInt64 = utxos.sum()
        let fee: UInt64 = 10000000
        let burnAmount: UInt64 = totalAmount - fee
        
        let unsignedTx = swapBuild(utxos: utxos, address: burnAddress, amount: burnAmount, rizonAddress: rizonAddress)
        let signedTx = try utoxTransactionSigner.sign(unsignedTx!, with: key)
        print("unsignedTx ", unsignedTx?.tx.serialized().toHexString().uppercased())
        print("signedTx ", signedTx.serialized().hex.uppercased())
        return signedTx.serialized().hex
    }
    
    static func swapBuild(utxos: [UnspentTransaction], address: Address, amount: UInt64, rizonAddress: String) -> UnsignedTransaction? {
        var outputs: [TransactionOutput] = [TransactionOutput]()
        outputs.append(TransactionOutput(value: amount, lockingScript: Script(address: address)!.data))
        let returnScript = ScriptFactory.OpReturn.build(text: rizonAddress)
        outputs.append(TransactionOutput(value: 546, lockingScript: returnScript!.data))
        
        let unsignedInputs = utxos.map { TransactionInput(previousOutput: $0.outpoint, signatureScript: $0.output.lockingScript, sequence: UInt32.max) }
        let tx = HDWalletKit.Transaction(version: 1, inputs: unsignedInputs, outputs: outputs, lockTime: 0)
        return UnsignedTransaction(tx: tx, utxos: utxos)
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

extension Sequence where Element == UnspentTransaction {
    public func sum() -> UInt64 {
        return reduce(UInt64()) { $0 + $1.output.value }
    }
}
