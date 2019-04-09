//
//  Coin.swift
//  Cosmostation
//
//  Created by yongjoo on 22/03/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import Foundation

public class Coin {
    var denom: String = ""
    var amount: String = ""
    
    init(){}
    
    init(_ dictionary: [String: Any]) {
        self.denom = dictionary["denom"] as? String ?? ""
        self.amount = dictionary["amount"] as? String ?? ""
    }
    
    init(_ denom:String, _ amount:String) {
        self.denom = denom
        self.amount = amount
    }
}
