//
//  MintParam_V1.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/12/09.
//  Copyright © 2020 wannabit. All rights reserved.
//

import Foundation

public struct MintParam_V1 {
    var mint_denom: String?
    var inflation_rate_change: String?
    var inflation_max: String?
    var inflation_min: String?
    var goal_bonded: String?
    var blocks_per_year: String?
    
    init(_ dictionary: NSDictionary?) {
        self.mint_denom = dictionary?["mint_denom"] as? String
        self.inflation_rate_change = dictionary?["inflation_rate_change"] as? String
        self.inflation_max = dictionary?["inflation_max"] as? String
        self.inflation_min = dictionary?["inflation_min"] as? String
        self.goal_bonded = dictionary?["goal_bonded"] as? String
        self.blocks_per_year = dictionary?["blocks_per_year"] as? String
    }
}
