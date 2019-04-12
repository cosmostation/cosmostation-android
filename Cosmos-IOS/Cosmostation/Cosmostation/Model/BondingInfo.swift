//
//  BondingInfo.swift
//  Cosmostation
//
//  Created by yongjoo on 22/03/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import Foundation

public struct BondingInfo {
    var delegator_address: String = ""
    var validator_address: String = ""
    var shares: String = ""
    
    init(_ dictionary: [String: Any]) {
        self.delegator_address = dictionary["delegator_address"] as? String ?? ""
        self.validator_address = dictionary["validator_address"] as? String ?? ""
        self.shares = dictionary["shares"] as? String ?? ""
    }
}
