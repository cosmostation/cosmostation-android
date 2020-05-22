//
//  KAvaIncentiveParam.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/05/22.
//  Copyright © 2020 wannabit. All rights reserved.
//

import Foundation

public class KavaIncentiveParam {
    var height: String = ""
    var result: IncentiveParam = IncentiveParam.init()
    
    init() {}
    
    init(_ dictionary: NSDictionary) {
        self.height = dictionary["height"] as? String ?? ""
        self.result = IncentiveParam.init(dictionary["result"] as! [String : Any])
    }
    
    
    public class IncentiveParam {
        var active: Bool = false
        var rewards: Array<IncentiveReward> = Array<IncentiveReward>()
        
        init() {}
        
        init(_ dictionary: [String: Any]) {
            self.active = dictionary["active"] as? Bool ?? false
            if let rawRewards = dictionary["rewards"] as? Array<NSDictionary> {
                self.rewards.removeAll()
                for rawReward in rawRewards {
                    self.rewards.append(IncentiveReward(rawReward as! [String : Any]))
                }
            }
        }
    }
    
    public class IncentiveReward {
        var active: Bool = false
        var denom: String = ""
        var available_rewards: Coin = Coin.init()
        var duration: String = ""
        var time_lock: String = ""
        var claim_duration: String = ""
        
        init() {}
        
        init(_ dictionary: [String: Any]) {
            self.active = dictionary["active"] as? Bool ?? false
            self.denom = dictionary["denom"] as? String ?? ""
            self.available_rewards =  Coin.init(dictionary["available_rewards"] as! [String : Any])
            self.duration = dictionary["duration"] as? String ?? ""
            self.time_lock = dictionary["time_lock"] as? String ?? ""
            self.claim_duration = dictionary["claim_duration"] as? String ?? ""
        }
    }
}
