//
//  Delegation_V1.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/12/09.
//  Copyright © 2020 wannabit. All rights reserved.
//

import Foundation

public struct DelegationInfo_V1 {
    var delegation: Delegation_V1?
    var balance: Coin?
    
    init(_ dictionary: NSDictionary?) {
        self.delegation = Delegation_V1.init(dictionary?["delegation"] as? NSDictionary)
        self.balance = Coin(dictionary?["balance"] as? NSDictionary)
    }
    
    public struct Delegation_V1 {
        var delegator_address: String?
        var validator_address: String?
        var shares: String?
        
        init(_ dictionary: NSDictionary?) {
            self.delegator_address = dictionary?["delegator_address"] as? String
            self.validator_address = dictionary?["validator_address"] as? String
            self.shares = dictionary?["shares"] as? String
        }
    }
    
    public func getDelegation() -> NSDecimalNumber {
        var result = NSDecimalNumber.zero
        if (self.balance != nil) {
            result = WUtils.plainStringToDecimal(self.balance?.amount)
        }
        return result
    }
}
