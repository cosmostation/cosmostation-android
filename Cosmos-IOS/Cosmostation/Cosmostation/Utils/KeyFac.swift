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
            resultAddress =  OldKey.getDpAddressPath(mnemonic, path, chain, newbip)
        } else {
            resultAddress =  WKey.getDpAddressPath(mnemonic, path, chain, newbip)
        }
        return resultAddress
    }
    
    
}
