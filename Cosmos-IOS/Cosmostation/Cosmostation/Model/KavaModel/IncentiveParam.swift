//
//  IncentiveParam.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/03/04.
//  Copyright © 2021 wannabit. All rights reserved.
//

import Foundation

public struct IncentiveParam {
    var claim_multipliers: Array<ClaimMultiplier> = Array<ClaimMultiplier>()
    
    init(_ dictionary: NSDictionary?) {
        if let rawClaimMultipliers = dictionary?["claim_multipliers"] as? Array<NSDictionary>  {
            for rawClaimMultiplier in rawClaimMultipliers {
                self.claim_multipliers.append(ClaimMultiplier(rawClaimMultiplier))
            }
        }
    }
    
    public func getFactor(_ denom: String, _ position: Int) -> NSDecimalNumber{
        for claim_multipliers in claim_multipliers {
            if (claim_multipliers.denom == denom) {
                return claim_multipliers.multipliers[position].factor
            }
        }
        return NSDecimalNumber.zero
    }
}
