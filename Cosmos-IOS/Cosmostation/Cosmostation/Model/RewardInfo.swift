//
//  RewardInfo.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/04/07.
//  Copyright © 2021 wannabit. All rights reserved.
//

import Foundation

public class RewardInfo {
    var validator_address: String = "";
    var reward: Array<Coin> = Array<Coin>()
    
    init(_ dictionary: [String: Any]) {
        self.validator_address = dictionary["validator_address"] as? String ?? ""
        self.reward.removeAll()
        if let rawRewards = dictionary["reward"] as? Array<NSDictionary> {
            for rawReward in rawRewards {
                self.reward.append(Coin.init(rawReward))
            }
        }
    }
    
    init(_ dictionary: NSDictionary?) {
        self.validator_address = dictionary?["validator_address"] as? String ?? ""
        self.reward.removeAll()
        if let rawRewards = dictionary?["reward"] as? Array<NSDictionary> {
            for rawReward in rawRewards {
                self.reward.append(Coin.init(rawReward))
            }
        }
    }
}
