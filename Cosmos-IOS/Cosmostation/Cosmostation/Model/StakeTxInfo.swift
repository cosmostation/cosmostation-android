//
//  StakeTxInfo.swift
//  Cosmostation
//
//  Created by yongjoo on 22/04/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import Foundation

public struct StakeTxInfo {
    var height: String = ""
    var txhash: String = ""
    var tx: StakeStdTx = StakeStdTx.init()
    
    init() {}
    
    init(_ dictionary: [String: Any]) {
        self.height = dictionary["height"] as? String ?? ""
        self.txhash = dictionary["txhash"] as? String ?? ""
        self.tx = StakeStdTx.init(dictionary["tx"] as! [String : Any])
        
    }
    
}
