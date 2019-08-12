//
//  IrisRewards.swift
//  Cosmostation
//
//  Created by yongjoo on 30/07/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import Foundation

public class IrisRewards {
    var total: Array<Coin> = Array<Coin>()
    var delegations: Array<IrisDelegations> = Array<IrisDelegations>()
    var commission: Array<Coin> = Array<Coin>()
    
    init() {}
    
    init(_ dictionary: [String: Any]) {
        self.total.removeAll()
        if let rawTotals = dictionary["total"] as? Array<NSDictionary>  {
            for rawTotal in rawTotals {
                self.total.append(Coin(rawTotal as! [String : Any]))
            }
        }
        
        self.delegations.removeAll()
        if let rawDelegations = dictionary["delegations"] as? Array<NSDictionary>  {
            for rawDelegation in rawDelegations {
                self.delegations.append(IrisDelegations(rawDelegation as! [String : Any]))
            }
        }
        
        self.commission.removeAll()
        if let rawCommissions = dictionary["commission"] as? Array<NSDictionary>  {
            for rawCommission in rawCommissions {
                self.commission.append(Coin(rawCommission as! [String : Any]))
            }
        }
    }
    
    func getSimpleIrisReward() -> NSDecimalNumber {
        var result = NSDecimalNumber.zero
        for delegation in delegations {
            for coin in delegation.reward {
                if (coin.denom == IRIS_MAIN_DENOM) {
                    result = result.adding(NSDecimalNumber.init(string: coin.amount))
                }
            }
        }
        return result;
    }
    
    func getPerValReward(valOp:String) -> NSDecimalNumber {
        var result = NSDecimalNumber.zero
        for delegation in delegations {
            if (delegation.validator == valOp) {
                for coin in delegation.reward {
                    if (coin.denom == IRIS_MAIN_DENOM) {
                        result = result.adding(NSDecimalNumber.init(string: coin.amount))
                    }
                }
            }
        }
        return result;
    }
    
    func getPerValRewardCoin(valOp:String) -> Coin? {
        var result: Coin?
        for delegation in delegations {
            if (delegation.validator == valOp) {
                result = delegation.reward[0]
            }
        }
        return result
    }
}

public class IrisDelegations {
    var validator: String = ""
    var reward: Array<Coin> = Array<Coin>()
    
    init(_ dictionary: [String: Any]) {
        self.validator = dictionary["validator"] as? String ?? ""
        self.reward.removeAll()
        if let rawRewards = dictionary["reward"] as? Array<NSDictionary>  {
            for rawReward in rawRewards {
                self.reward.append(Coin(rawReward as! [String : Any]))
            }
        }
    }
}
