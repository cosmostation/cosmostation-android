//
//  HardInterestRate.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/03/04.
//  Copyright © 2021 wannabit. All rights reserved.
//

import Foundation

public struct HardInterestRate {
    var denom: String?
    var supply_interest_rate: String?
    var borrow_interest_rate: String?
    
    init(_ dictionary: NSDictionary) {
        self.denom = dictionary["denom"] as? String
        self.supply_interest_rate = dictionary["supply_interest_rate"] as? String
        self.borrow_interest_rate = dictionary["borrow_interest_rate"] as? String
    }
}
