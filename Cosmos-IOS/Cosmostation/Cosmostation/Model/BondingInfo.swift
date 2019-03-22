//
//  BondingInfo.swift
//  Cosmostation
//
//  Created by yongjoo on 22/03/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import Foundation

public class BondingInfo {
    var delegator_addr: String = ""
    var validator_addr: String = ""
    var shares: String = ""
    
    init(_ dictionary: [String: Any]) {
        self.delegator_addr = dictionary["delegator_addr"] as? String ?? ""
        self.validator_addr = dictionary["validator_addr"] as? String ?? ""
        self.shares = dictionary["shares"] as? String ?? ""
    }
}
