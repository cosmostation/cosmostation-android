//
//  KavaIncentiveReward.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/05/27.
//  Copyright © 2020 wannabit. All rights reserved.
//

import Foundation

public class KavaIncentiveReward {
    var height: String = ""
    var result: Array<IncentiveReward> = Array<IncentiveReward>()
    
    init() {}
    
    init(_ dictionary: NSDictionary) {
        self.height = dictionary["height"] as? String ?? ""
        if let rawResults = dictionary["result"] as? Array<NSDictionary> {
            self.result.removeAll()
            for rawResult in rawResults {
                self.result.append(IncentiveReward(rawResult as! [String : Any]))
            }
        }
    }
    
    public class IncentiveReward {
        var owner: String = ""
        var reward: Coin = Coin.init()
        var denom: String = ""
        var claim_period_id: String = ""
        
        init() {}
        
        init(_ dictionary: [String: Any]) {
            self.owner = dictionary["owner"] as? String ?? ""
            self.denom = dictionary["denom"] as? String ?? ""
            self.claim_period_id = dictionary["claim_period_id"] as? String ?? ""
            self.reward =  Coin.init(dictionary["reward"] as! [String : Any])
        }
        
//        public func isClaimable() -> Bool {
//            return !owner.isEmpty && !claim_period_id.isEmpty
//        }
    }
}
