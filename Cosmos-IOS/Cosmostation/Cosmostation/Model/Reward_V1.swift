//
//  Reward_V1.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/12/10.
//  Copyright © 2020 wannabit. All rights reserved.
//

import Foundation

public struct Reward_V1 {
    var validator_address: String?
    var reward: Array<Coin>?
    
    init(_ dictionary: NSDictionary?) {
        self.validator_address = dictionary?["validator_address"] as? String
        if let rawRewards = dictionary?["reward"] as? Array<NSDictionary>  {
            reward = Array<Coin>()
            for rawReward in rawRewards {
                let decimalReward = WUtils.localeStringToDecimal(rawReward["amount"] as? String).rounding(accordingToBehavior: WUtils.handler6)
                self.reward!.append(Coin(rawReward["denom"] as! String, decimalReward.stringValue))
            }
        }
    }
    
    public func getRewardByDenom(_ symbol:String) -> NSDecimalNumber {
        var result = NSDecimalNumber.zero
        if (self.reward != nil) {
            for rewardDetail in self.reward! {
                if (rewardDetail.denom == symbol) {
                    result = result.adding(WUtils.plainStringToDecimal(rewardDetail.amount))
                }
            }
        }
        return result
    }
}
