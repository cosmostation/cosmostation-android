//
//  KeyFac.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/07/08.
//  Copyright © 2021 wannabit. All rights reserved.
//

import Foundation

class KeyFac {
    
    static func getDpAddressPath(_ mnemonic: [String], _ path:Int, _ chain:ChainType, _ newbip:Bool) -> String {
        var resultAddress = ""
        if (BaseData.instance.getUsingEnginerMode()) {
            resultAddress = OldKey.getDpAddressPath(mnemonic, path, chain, newbip)
        } else {
            resultAddress = WKey.getDpAddressPath(mnemonic, path, chain, newbip)
        }
        return resultAddress
    }
    
    static func getDpAddressFetchCustomPath(_ mnemonic: [String], _ path: UInt32, _ chain: ChainType, _ pathType: Int) -> String {
        var resultAddress = ""
        if (BaseData.instance.getUsingEnginerMode()) {
            resultAddress = OldKey.getDpAddressFetchCustomPath(mnemonic, path, chain, pathType)
        } else {
            resultAddress = WKey.getDpAddressFetchCustomPath(mnemonic, path, chain, pathType)
        }
        return resultAddress
    }
    
    static func getPrivateRaw(_ mnemonic: [String], _ account: Account) -> Data {
        if (BaseData.instance.getUsingEnginerMode()) {
            return OldKey.getPrivateRaw(mnemonic, account)
        } else {
            return WKey.getPrivateRaw(mnemonic, account)
        }
    }
    
    static func getPublicRaw(_ mnemonic: [String], _ account: Account) -> Data {
        if (BaseData.instance.getUsingEnginerMode()) {
            return OldKey.getPublicRaw(mnemonic, account)
        } else {
            return WKey.getPublicRaw(mnemonic, account)
        }
    }
    
    static func getStdTx(_ words: [String], _ msgList: Array<Msg>, _ stdMsg: StdSignMsg, _ account: Account, _ fee: Fee, _ memo: String) -> StdTx {
        if (BaseData.instance.getUsingEnginerMode()) {
            return OldKey.getStdTx(words, msgList, stdMsg, account, fee, memo)
        } else {
            return WKey.getStdTx(words, msgList, stdMsg, account, fee, memo)
        }
    }
    
}
