//
//  OldKey.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/07/08.
//  Copyright © 2021 wannabit. All rights reserved.
//

import Foundation
import BitcoinKit
import HDWalletKit

class OldKey {
    
    static func getMasterKeyFromWords(_ m: [String]) -> HDPrivateKey {
        return HDPrivateKey(seed: Mnemonic.seed(mnemonic: m), network: .testnet)
    }

    static func getHDKeyFromWords(_ m: [String], _ account:Account) -> HDPrivateKey {
        let masterKey = getMasterKeyFromWords(m)
        let chainType = WUtils.getChainType(account.account_base_chain)

        if (chainType == ChainType.COSMOS_MAIN || chainType == ChainType.IRIS_MAIN || chainType == ChainType.CERTIK_MAIN || chainType == ChainType.AKASH_MAIN ||
                chainType == ChainType.SENTINEL_MAIN || chainType == ChainType.SIF_MAIN || chainType == ChainType.KI_MAIN || chainType == ChainType.OSMOSIS_MAIN ||
                chainType == ChainType.COSMOS_TEST || chainType == ChainType.IRIS_TEST || chainType == ChainType.CERTIK_TEST) {
            return try! masterKey.derived(at: 44, hardened: true).derived(at: 118, hardened: true).derived(at: 0, hardened: true).derived(at: 0).derived(at: UInt32(account.account_path)!)

        } else if (chainType == ChainType.KAVA_MAIN || chainType == ChainType.KAVA_TEST) {
            if (account.account_new_bip44) {
                return try! masterKey.derived(at: 44, hardened: true).derived(at: 459, hardened: true).derived(at: 0, hardened: true).derived(at: 0).derived(at: UInt32(account.account_path)!)
            } else {
                return try! masterKey.derived(at: 44, hardened: true).derived(at: 118, hardened: true).derived(at: 0, hardened: true).derived(at: 0).derived(at: UInt32(account.account_path)!)
            }

        } else if (chainType == ChainType.BINANCE_MAIN || chainType == ChainType.BINANCE_TEST) {
            return try! masterKey.derived(at: 44, hardened: true).derived(at: 714, hardened: true).derived(at: 0, hardened: true).derived(at: 0).derived(at: UInt32(account.account_path)!)

        } else if (chainType == ChainType.BAND_MAIN) {
            return try! masterKey.derived(at: 44, hardened: true).derived(at: 494, hardened: true).derived(at: 0, hardened: true).derived(at: 0).derived(at: UInt32(account.account_path)!)

        } else if (chainType == ChainType.SECRET_MAIN) {
            if (account.account_new_bip44) {
                return try! masterKey.derived(at: 44, hardened: true).derived(at: 118, hardened: true).derived(at: 0, hardened: true).derived(at: 0).derived(at: UInt32(account.account_path)!)
            } else {
                return try! masterKey.derived(at: 44, hardened: true).derived(at: 529, hardened: true).derived(at: 0, hardened: true).derived(at: 0).derived(at: UInt32(account.account_path)!)
            }

        } else if (chainType == ChainType.IOV_MAIN || chainType == ChainType.IOV_TEST) {
            return try! masterKey.derived(at: 44, hardened: true).derived(at: 234, hardened: true).derived(at: 0, hardened: true).derived(at: 0).derived(at: UInt32(account.account_path)!)

        } else if (chainType == ChainType.OKEX_MAIN || chainType == ChainType.OKEX_TEST) {
            return try! masterKey.derived(at: 44, hardened: true).derived(at: 996, hardened: true).derived(at: 0, hardened: true).derived(at: 0).derived(at: UInt32(account.account_path)!)

        } else if (chainType == ChainType.PERSIS_MAIN) {
            return try! masterKey.derived(at: 44, hardened: true).derived(at: 750, hardened: true).derived(at: 0, hardened: true).derived(at: 0).derived(at: UInt32(account.account_path)!)

        } else if (chainType == ChainType.CRYPTO_MAIN) {
            return try! masterKey.derived(at: 44, hardened: true).derived(at: 394, hardened: true).derived(at: 0, hardened: true).derived(at: 0).derived(at: UInt32(account.account_path)!)

        } else if (chainType == ChainType.RIZON_TEST) {
            return try! masterKey.derived(at: 44, hardened: true).derived(at: 1217, hardened: true).derived(at: 0, hardened: true).derived(at: 0).derived(at: UInt32(account.account_path)!)

        } else if (chainType == ChainType.MEDI_MAIN || chainType == ChainType.MEDI_TEST) {
            return try! masterKey.derived(at: 44, hardened: true).derived(at: 371, hardened: true).derived(at: 0, hardened: true).derived(at: 0).derived(at: UInt32(account.account_path)!)

        } else if (chainType == ChainType.ALTHEA_TEST) {
            return try! masterKey.derived(at: 44, hardened: true).derived(at: 60, hardened: true).derived(at: 0, hardened: true).derived(at: 0).derived(at: UInt32(account.account_path)!)

        } else if (chainType == ChainType.FETCH_MAIN) {
            if (account.account_custom_path == 1) {
                return try! masterKey.derived(at: 44, hardened: true).derived(at: 60, hardened: true).derived(at: 0, hardened: true).derived(at: 0).derived(at: UInt32(account.account_path)!)
                
            } else if (account.account_custom_path == 2) {
                return try! masterKey.derived(at: 44, hardened: true).derived(at: 60, hardened: true).derived(at: UInt32(account.account_path)!, hardened: true).derived(at: 0).derived(at: 0)
                
            } else if (account.account_custom_path == 3) {
                return try! masterKey.derived(at: 44, hardened: true).derived(at: 60, hardened: true).derived(at: 0, hardened: true).derived(at: UInt32(account.account_path)!)
                
            } else {
                return try! masterKey.derived(at: 44, hardened: true).derived(at: 118, hardened: true).derived(at: 0, hardened: true).derived(at: 0).derived(at: UInt32(account.account_path)!)
            }
            
        } else {
            return try! masterKey.derived(at: 44, hardened: true).derived(at: 118, hardened: true).derived(at: 0, hardened: true).derived(at: 0).derived(at: UInt32(account.account_path)!)
        }
    }

    static func getDpAddressPath(_ mnemonic: [String], _ path:Int, _ chain:ChainType, _ newbip:Bool) -> String {
        let masterKey = getMasterKeyFromWords(mnemonic)
        if ((chain == ChainType.OKEX_MAIN || chain == ChainType.OKEX_TEST) && newbip) {
            let pKey = try! masterKey.derived(at: 44, hardened: true).derived(at: 996, hardened: true).derived(at: 0, hardened: true).derived(at: 0).derived(at: UInt32(path))
            return WKey.generateAddressFromPriv("okexchain", pKey.privateKey().raw)

        } else {
            return getHDKeyDpAddressWithPath(masterKey, path: path, chain: chain, newbip)
        }
    }
    
    static func getDpAddressFetchCustomPath(_ mnemonic: [String], _ path: UInt32, _ chain: ChainType, _ pathType: Int) -> String {
        let masterKey = getMasterKeyFromWords(mnemonic)
        if (pathType == 0) {
            let childKey = try! masterKey.derived(at: 44, hardened: true).derived(at: 118, hardened: true).derived(at: 0, hardened: true).derived(at: 0).derived(at: path)
            return WKey.getPubToDpAddress(childKey.privateKey().publicKey().raw.dataToHexString(), chain)
            
        } else if (pathType == 1) {
            let childKey = try! masterKey.derived(at: 44, hardened: true).derived(at: 60, hardened: true).derived(at: 0, hardened: true).derived(at: 0).derived(at: path)
            return WKey.getPubToDpAddress(childKey.privateKey().publicKey().raw.dataToHexString(), chain)
            
        } else if (pathType == 2) {
            let childKey = try! masterKey.derived(at: 44, hardened: true).derived(at: 60, hardened: true).derived(at: path, hardened: true).derived(at: 0).derived(at: 0)
            return WKey.getPubToDpAddress(childKey.privateKey().publicKey().raw.dataToHexString(), chain)
            
        } else if (pathType == 3) {
            let childKey = try! masterKey.derived(at: 44, hardened: true).derived(at: 60, hardened: true).derived(at: 0, hardened: true).derived(at: path)
            return WKey.getPubToDpAddress(childKey.privateKey().publicKey().raw.dataToHexString(), chain)
            
        }
        return ""
    }

    static func getHDKeyDpAddressWithPath(_ masterKey:HDPrivateKey, path:Int, chain:ChainType, _ newbip:Bool) -> String {
        do {
            var childKey:HDPrivateKey?
            if (chain == ChainType.COSMOS_MAIN || chain == ChainType.IRIS_MAIN || chain == ChainType.CERTIK_MAIN || chain == ChainType.AKASH_MAIN ||
                    chain == ChainType.SENTINEL_MAIN || chain == ChainType.SIF_MAIN || chain == ChainType.KI_MAIN || chain == ChainType.OSMOSIS_MAIN ||
                    chain == ChainType.COSMOS_TEST || chain == ChainType.IRIS_TEST || chain == ChainType.CERTIK_TEST) {
                childKey = try masterKey.derived(at: 44, hardened: true).derived(at: 118, hardened: true).derived(at: 0, hardened: true).derived(at: 0).derived(at: UInt32(path))

            } else if (chain == ChainType.BINANCE_MAIN || chain == ChainType.BINANCE_TEST) {
                childKey = try masterKey.derived(at: 44, hardened: true).derived(at: 714, hardened: true).derived(at: 0, hardened: true).derived(at: 0).derived(at: UInt32(path))

            } else if (chain == ChainType.KAVA_MAIN || chain == ChainType.KAVA_TEST) {
                if (newbip) {
                    childKey = try masterKey.derived(at: 44, hardened: true).derived(at: 459, hardened: true).derived(at: 0, hardened: true).derived(at: 0).derived(at: UInt32(path))
                } else {
                    childKey = try masterKey.derived(at: 44, hardened: true).derived(at: 118, hardened: true).derived(at: 0, hardened: true).derived(at: 0).derived(at: UInt32(path))
                }

            } else if (chain == ChainType.BAND_MAIN) {
                childKey = try masterKey.derived(at: 44, hardened: true).derived(at: 494, hardened: true).derived(at: 0, hardened: true).derived(at: 0).derived(at: UInt32(path))

            } else if (chain == ChainType.SECRET_MAIN) {
                if (newbip) {
                    childKey = try masterKey.derived(at: 44, hardened: true).derived(at: 118, hardened: true).derived(at: 0, hardened: true).derived(at: 0).derived(at: UInt32(path))
                } else {
                    childKey = try masterKey.derived(at: 44, hardened: true).derived(at: 529, hardened: true).derived(at: 0, hardened: true).derived(at: 0).derived(at: UInt32(path))
                }

            } else if (chain == ChainType.IOV_MAIN || chain == ChainType.IOV_TEST) {
                childKey = try masterKey.derived(at: 44, hardened: true).derived(at: 234, hardened: true).derived(at: 0, hardened: true).derived(at: 0).derived(at: UInt32(path))

            } else if (chain == ChainType.OKEX_MAIN || chain == ChainType.OKEX_TEST) {
                childKey = try masterKey.derived(at: 44, hardened: true).derived(at: 996, hardened: true).derived(at: 0, hardened: true).derived(at: 0).derived(at: UInt32(path))

            } else if (chain == ChainType.PERSIS_MAIN) {
                childKey = try masterKey.derived(at: 44, hardened: true).derived(at: 750, hardened: true).derived(at: 0, hardened: true).derived(at: 0).derived(at: UInt32(path))

            } else if (chain == ChainType.CRYPTO_MAIN) {
                childKey = try masterKey.derived(at: 44, hardened: true).derived(at: 394, hardened: true).derived(at: 0, hardened: true).derived(at: 0).derived(at: UInt32(path))

            } else if (chain == ChainType.RIZON_TEST) {
                childKey = try masterKey.derived(at: 44, hardened: true).derived(at: 1217, hardened: true).derived(at: 0, hardened: true).derived(at: 0).derived(at: UInt32(path))

            } else if (chain == ChainType.MEDI_MAIN || chain == ChainType.MEDI_TEST) {
                childKey = try masterKey.derived(at: 44, hardened: true).derived(at: 371, hardened: true).derived(at: 0, hardened: true).derived(at: 0).derived(at: UInt32(path))

            } else if (chain == ChainType.ALTHEA_TEST) {
                childKey = try masterKey.derived(at: 44, hardened: true).derived(at: 60, hardened: true).derived(at: 0, hardened: true).derived(at: 0).derived(at: UInt32(path))

            } else {
                childKey = try masterKey.derived(at: 44, hardened: true).derived(at: 118, hardened: true).derived(at: 0, hardened: true).derived(at: 0).derived(at: UInt32(path))

            }
            return WKey.getPubToDpAddress(childKey!.privateKey().publicKey().raw.dataToHexString(), chain)

        } catch { return "" }
    }
    
    
    
    static func getPrivateRaw(_ mnemonic: [String], _ account: Account) -> Data {
        return getHDKeyFromWords(mnemonic, account).privateKey().raw
    }
    
    static func getPublicRaw(_ mnemonic: [String], _ account: Account) -> Data {
        return getHDKeyFromWords(mnemonic, account).privateKey().publicKey().raw
    }
    
    static func getStdTx(_ words: [String], _ msgList: Array<Msg>, _ stdMsg: StdSignMsg, _ account: Account, _ fee: Fee, _ memo: String) -> StdTx {
        let pKey = getHDKeyFromWords(words, account)
        let encoder = JSONEncoder()
        encoder.outputFormatting = .sortedKeys
        let data = try? encoder.encode(stdMsg)
        let rawResult = String(data:data!, encoding:.utf8)?.replacingOccurrences(of: "\\/", with: "/")
        let rawData: Data? = rawResult!.data(using: .utf8)
        let hash = rawData!.sha256()
        let signedData = try! HDWalletKit.ECDSA.compactsign(hash, privateKey: pKey.privateKey().raw)

        var genedSignature = Signature.init()
        var genPubkey =  PublicKey.init()
        genPubkey.type = COSMOS_KEY_TYPE_PUBLIC
        genPubkey.value = pKey.privateKey().publicKey().raw.base64EncodedString()
        genedSignature.pub_key = genPubkey
        genedSignature.signature = signedData.base64EncodedString()
        genedSignature.account_number = String(account.account_account_numner)
        genedSignature.sequence = String(account.account_sequence_number)

        var signatures: Array<Signature> = Array<Signature>()
        signatures.append(genedSignature)

        return MsgGenerator.genSignedTx(msgList, fee, memo, signatures)
    }
}
