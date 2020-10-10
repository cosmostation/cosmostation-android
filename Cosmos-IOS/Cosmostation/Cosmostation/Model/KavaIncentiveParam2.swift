//
//  KavaIncentiveParam2.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/10/08.
//  Copyright © 2020 wannabit. All rights reserved.
//

import Foundation


public class KavaIncentiveParam2 {
    var height: String = ""
    var result: IncentiveParam2 = IncentiveParam2.init()
    
    init() {}
    
    init(_ dictionary: NSDictionary) {
        self.height = dictionary["height"] as? String ?? ""
        self.result = IncentiveParam2.init(dictionary["result"] as! [String : Any])
    }
    
    
    public class IncentiveParam2 {
        var active: Bool = false
        var rewards: Array<IncentiveReward2> = Array<IncentiveReward2>()
        
        init() {}
        
        init(_ dictionary: [String: Any]) {
            self.active = dictionary["active"] as? Bool ?? false
            if let rawRewards = dictionary["rewards"] as? Array<NSDictionary> {
                self.rewards.removeAll()
                for rawReward in rawRewards {
                    self.rewards.append(IncentiveReward2(rawReward as! [String : Any]))
                }
            }
        }
    }
    
    public class IncentiveReward2 {
        var active: Bool = false
        var collateral_type: String = ""
        var available_rewards: Coin = Coin.init()
        var duration: String = ""
        var claim_multipliers: Array<KavaClaimMultiplier> = Array<KavaClaimMultiplier>()
        var claim_duration: String = ""
        
        init() {}
        
        init(_ dictionary: [String: Any]) {
            self.active = dictionary["active"] as? Bool ?? false
            self.collateral_type = dictionary["collateral_type"] as? String ?? ""
            self.available_rewards =  Coin.init(dictionary["available_rewards"] as! [String : Any])
            self.duration = dictionary["duration"] as? String ?? ""
            self.claim_multipliers.removeAll()
            if let claim_multipliers = dictionary["claim_multipliers"] as? Array<NSDictionary> {
                for claim_multiplier in claim_multipliers {
                    self.claim_multipliers.append(KavaClaimMultiplier(claim_multiplier as! [String : Any]))
                }
            }
            self.claim_duration = dictionary["claim_duration"] as? String ?? ""
        }
    }
}
