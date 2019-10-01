//
//  BnbHistory.swift
//  Cosmostation
//
//  Created by yongjoo on 01/10/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import Foundation

public class BnbHistory {
    var txHash: String = ""
    var blockHeight: Int64 = -1
    var txType: String = ""
    var timeStamp: String = ""
    var fromAddr: String = ""
    var toAddr: String = ""
    
    init() {}
    
    init(_ dictionary: [String: Any]) {
        self.txHash = dictionary["txHash"] as? String ?? ""
        self.blockHeight = dictionary["blockHeight"] as? Int64 ?? -1
        self.txType = dictionary["txType"] as? String ?? ""
        self.timeStamp = dictionary["timeStamp"] as? String ?? ""
        self.fromAddr = dictionary["fromAddr"] as? String ?? ""
        self.toAddr = dictionary["toAddr"] as? String ?? ""
    }
}
