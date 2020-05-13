//
//  BnbSwapInfo.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/05/13.
//  Copyright © 2020 wannabit. All rights reserved.
//

import Foundation


public class BnbSwapInfo {
    static let BNB_STATUS_OPEN          = 1;
    static let BNB_STATUS_COMPLETED     = 2;
    static let BNB_STATUS_REFUNDED      = 3;
    
    var swapId: String = ""
    var fromAddr: String = ""
    var status: Int = 0
    var randomNumber: String = ""
    var outAmount: String = ""
    var expireHeight: UInt64 = 0
    
    init() {}
    
    init(_ dictionary: [String: Any]) {
        self.swapId = dictionary["swapId"] as? String ?? ""
        self.fromAddr = dictionary["fromAddr"] as? String ?? ""
        self.status = dictionary["status"] as? Int ?? 0
        self.randomNumber = dictionary["randomNumber"] as? String ?? ""
        self.outAmount = dictionary["outAmount"] as? String ?? ""
        self.expireHeight = dictionary["expireHeight"] as? UInt64 ?? 0
    }
}
