//
//  KavaClaimMultiplier.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/10/08.
//  Copyright © 2020 wannabit. All rights reserved.
//

import Foundation

public class KavaClaimMultiplier {
    var name: String = ""
    var months_lockup: String = ""
    var factor: String = ""
    
    init() {}
    
    init(_ dictionary: [String: Any]) {
        self.name = dictionary["name"] as? String ?? ""
        self.months_lockup = dictionary["months_lockup"] as? String ?? ""
        self.factor = dictionary["factor"] as? String ?? ""
    }
}
