//
//  StakingPool_V1.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/12/09.
//  Copyright © 2020 wannabit. All rights reserved.
//

import Foundation

public struct StakingPool_V1 {
    var not_bonded_tokens: String?
    var bonded_tokens: String?
    
    init(_ dictionary: NSDictionary?) {
        self.not_bonded_tokens = dictionary?["not_bonded_tokens"] as? String
        self.bonded_tokens = dictionary?["bonded_tokens"] as? String
    }
}
