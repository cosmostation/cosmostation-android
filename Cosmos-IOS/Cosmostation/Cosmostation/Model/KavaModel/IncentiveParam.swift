//
//  IncentiveParam.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/03/04.
//  Copyright © 2021 wannabit. All rights reserved.
//

import Foundation

public struct IncentiveParam {
    var claim_multipliers: Array<ClaimMultiplier>?
    
    init(_ dictionary: NSDictionary?) {
        if let rawClaimMultipliers = dictionary?["claim_multipliers"] as? Array<NSDictionary>  {
            self.claim_multipliers = Array<ClaimMultiplier>()
            for rawClaimMultiplier in rawClaimMultipliers {
                self.claim_multipliers!.append(ClaimMultiplier(rawClaimMultiplier))
            }
        }
    }
}
