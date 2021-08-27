//
//  ClaimMultiplier.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/03/04.
//  Copyright © 2021 wannabit. All rights reserved.
//

import Foundation

public struct ClaimMultiplier {
    var denom: String?
    var multipliers: Array<Multiplier> = Array<Multiplier>()
    
    init(_ dictionary: NSDictionary?) {
        self.denom = dictionary?["denom"] as? String
        if let rawMultipliers = dictionary?["multipliers"] as? Array<NSDictionary>  {
            for rawMultiplier in rawMultipliers {
                self.multipliers.append(Multiplier(rawMultiplier))
            }
        }
    }
}

public struct Multiplier {
    var name: String?
    var months_lockup: String?
    var factor: String?
    
    init(_ dictionary: NSDictionary?) {
        self.name = dictionary?["name"] as? String
        self.months_lockup = dictionary?["months_lockup"] as? String
        self.factor = dictionary?["factor"] as? String
    }
    
}
