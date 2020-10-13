//
//  KavaIncentiveReward2.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/10/13.
//  Copyright © 2020 wannabit. All rights reserved.
//

import Foundation

public class KavaIncentiveReward2 {
    var height: String = ""
    var result: Array<IncentiveRewardClaimable> = Array<IncentiveRewardClaimable>()
    
    init() {}
    
    init(_ dictionary: NSDictionary) {
        self.height = dictionary["height"] as? String ?? ""
        if let rawResults = dictionary["result"] as? Array<NSDictionary> {
            self.result.removeAll()
            for rawResult in rawResults {
                self.result.append(IncentiveRewardClaimable(rawResult as! [String : Any]))
            }
        }
    }
    
    public class IncentiveRewardClaimable {
        var claimable: Bool = false
        var claim: IncentiveRewardClaim = IncentiveRewardClaim.init()
        
        init() {}
        
        init(_ dictionary: [String: Any]) {
            self.claimable = dictionary["claimable"] as? Bool ?? false
            self.claim = IncentiveRewardClaim.init(dictionary["claim"] as! [String : Any])
        }
    }
    
    public class IncentiveRewardClaim {
        var owner: String = ""
        var reward: Coin = Coin.init()
        var collateral_type: String = ""
        var claim_period_id: String = ""

        init() {}

        init(_ dictionary: [String: Any]) {
            self.owner = dictionary["owner"] as? String ?? ""
            self.collateral_type = dictionary["collateral_type"] as? String ?? ""
            self.claim_period_id = dictionary["claim_period_id"] as? String ?? ""
            self.reward =  Coin.init(dictionary["reward"] as! [String : Any])
        }
    }
}
