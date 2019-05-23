//
//  TxInfo.swift
//  Cosmostation
//
//  Created by yongjoo on 12/04/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import Foundation


public struct TxInfo {
    var height: String = ""
    var txhash: String = ""
    var txTime: String = ""
    var tx: StdTx = StdTx.init()
    
    init() {}
    
    init(_ dictionary: [String: Any]) {
        self.height = dictionary["height"] as? String ?? ""
        self.txhash = dictionary["txhash"] as? String ?? ""
        self.txTime = dictionary["timestamp"] as? String ?? ""
        self.tx = StdTx.init(dictionary["tx"] as! [String : Any])
        
    }
    
}
