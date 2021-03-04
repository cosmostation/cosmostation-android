//
//  ClaimMultiplier.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/03/04.
//  Copyright © 2021 wannabit. All rights reserved.
//

import Foundation

public struct ClaimMultiplier {
    var name: String?
    var months_lockup: String?
    var factor: String?
    
    init(_ dictionary: NSDictionary?) {
        self.name = dictionary?["name"] as? String
        self.months_lockup = dictionary?["months_lockup"] as? String
        self.factor = dictionary?["factor"] as? String
    }
}
