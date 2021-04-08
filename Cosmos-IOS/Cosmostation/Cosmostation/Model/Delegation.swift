//
//  Delegation.swift
//  Cosmostation
//
//  Created by yongjoo on 25/03/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import Foundation

public class Delegation {
    var denom: String = ""
    var amount: String = ""

    init() {}

    init(_ dictionary: [String: Any]) {
        self.denom = dictionary["denom"] as? String ?? ""
        self.amount = dictionary["amount"] as? String ?? ""
    }

}
