//
//  OldKey.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/07/08.
//  Copyright © 2021 wannabit. All rights reserved.
//

import Foundation
import BitcoinKit

class OldKey {
    
    static func getMasterKeyFromWords(_ m: [String]) -> HDPrivateKey {
        return HDPrivateKey(seed: Mnemonic.seed(mnemonic: m), network: .testnet)
    }

    static func getHDKeyFromWords(_ m: [String], _ account:Account) -> HDPrivateKey {
        let masterKey = getMasterKeyFromWords(m)
        let chainType = WUtils.getChainType(account.account_base_chain)

        if (chainType == ChainType.COSMOS_MAIN || chainType == ChainType.IRIS_MAIN || chainType == ChainType.CERTIK_MAIN || chainType == ChainType.AKASH_MAIN ||
                chainType == ChainType.SENTINEL_MAIN || chainType == ChainType.FETCH_MAIN || chainType == ChainType.SIF_MAIN || chainType == ChainType.KI_MAIN || chainType == ChainType.OSMOSIS_MAIN ||
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

        } else if (chainType == ChainType.MEDI_TEST) {
            return try! masterKey.derived(at: 44, hardened: true).derived(at: 371, hardened: true).derived(at: 0, hardened: true).derived(at: 0).derived(at: UInt32(account.account_path)!)

        } else if (chainType == ChainType.ALTHEA_TEST) {
            return try! masterKey.derived(at: 44, hardened: true).derived(at: 60, hardened: true).derived(at: 0, hardened: true).derived(at: 0).derived(at: UInt32(account.account_path)!)

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

    static func getHDKeyDpAddressWithPath(_ masterKey:HDPrivateKey, path:Int, chain:ChainType, _ newbip:Bool) -> String {
        do {
            var childKey:HDPrivateKey?
            if (chain == ChainType.COSMOS_MAIN || chain == ChainType.IRIS_MAIN || chain == ChainType.CERTIK_MAIN || chain == ChainType.AKASH_MAIN ||
                    chain == ChainType.SENTINEL_MAIN || chain == ChainType.FETCH_MAIN || chain == ChainType.SIF_MAIN || chain == ChainType.KI_MAIN || chain == ChainType.OSMOSIS_MAIN ||
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

            } else if (chain == ChainType.MEDI_TEST) {
                childKey = try masterKey.derived(at: 44, hardened: true).derived(at: 371, hardened: true).derived(at: 0, hardened: true).derived(at: 0).derived(at: UInt32(path))

            } else if (chain == ChainType.ALTHEA_TEST) {
                childKey = try masterKey.derived(at: 44, hardened: true).derived(at: 60, hardened: true).derived(at: 0, hardened: true).derived(at: 0).derived(at: UInt32(path))

            } else {
                childKey = try masterKey.derived(at: 44, hardened: true).derived(at: 118, hardened: true).derived(at: 0, hardened: true).derived(at: 0).derived(at: UInt32(path))

            }
            return WKey.getPubToDpAddress(childKey!.privateKey().publicKey().raw.dataToHexString(), chain)

        } catch { return "" }
    }
}
