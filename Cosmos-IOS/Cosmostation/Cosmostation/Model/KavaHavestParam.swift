//
//  KavaHavestParam.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/10/07.
//  Copyright © 2020 wannabit. All rights reserved.
//

import Foundation

public class KavaHavestParam {
    var height: String = ""
    var result: KavaHavestParamData = KavaHavestParamData.init()
    
    init() {}
    
    init(_ dictionary: NSDictionary) {
        self.height = dictionary["height"] as? String ?? ""
        self.result = KavaHavestParamData.init(dictionary["result"] as! [String : Any])
    }
    
    public func getKavaStakerSchedule() -> DelegatorDistributionSchedule? {
        for schedule in result.delegator_distribution_schedules {
            if (schedule.distribution_schedule.deposit_denom == KAVA_MAIN_DENOM) {
                return schedule
            }
        }
        return nil
    }
    
    public class KavaHavestParamData {
        var active: Bool = false
        var liquidity_provider_schedules: Array<DistributionSchedule> = Array<DistributionSchedule>()
        var delegator_distribution_schedules: Array<DelegatorDistributionSchedule> = Array<DelegatorDistributionSchedule>()
        
        init() {}
        
        init(_ dictionary: [String: Any]) {
            self.active = dictionary["active"] as? Bool ?? false
            self.liquidity_provider_schedules.removeAll()
            if let liquidity_provider_schedules = dictionary["liquidity_provider_schedules"] as? Array<NSDictionary> {
                for liquidity_provider_schedule in liquidity_provider_schedules {
                    self.liquidity_provider_schedules.append(DistributionSchedule(liquidity_provider_schedule as! [String : Any]))
                }
            }
            self.delegator_distribution_schedules.removeAll()
            if let delegator_distribution_schedules = dictionary["delegator_distribution_schedules"] as? Array<NSDictionary> {
                for delegator_distribution_schedule in delegator_distribution_schedules {
                    self.delegator_distribution_schedules.append(DelegatorDistributionSchedule(delegator_distribution_schedule as! [String : Any]))
                }
            }
        }
    }
    
    public class DistributionSchedule {
        var active: Bool = false
        var deposit_denom: String = ""
        var start: String = ""
        var end: String = ""
        var rewards_per_second: Coin = Coin.init()
        var claim_end: String = ""
        var claim_multipliers: Array<KavaClaimMultiplier> = Array<KavaClaimMultiplier>()
        
        init() {}
        
        init(_ dictionary: [String: Any]) {
            self.active = dictionary["active"] as? Bool ?? false
            self.deposit_denom = dictionary["deposit_denom"] as? String ?? ""
            self.start = dictionary["start"] as? String ?? ""
            self.end = dictionary["end"] as? String ?? ""
            self.rewards_per_second =  Coin.init(dictionary["rewards_per_second"] as! [String : Any])
            self.claim_end = dictionary["claim_end"] as? String ?? ""
            self.claim_multipliers.removeAll()
            if let claim_multipliers = dictionary["claim_multipliers"] as? Array<NSDictionary> {
                for claim_multiplier in claim_multipliers {
                    self.claim_multipliers.append(KavaClaimMultiplier(claim_multiplier as! [String : Any]))
                }
            }
        }
    }
    
    public class DelegatorDistributionSchedule {
        var distribution_schedule: DistributionSchedule = DistributionSchedule.init()
        var distribution_frequency: String = ""
        
        init() {}
        
        init(_ dictionary: [String: Any]) {
            self.distribution_schedule =  DistributionSchedule.init(dictionary["distribution_schedule"] as! [String : Any])
            self.distribution_frequency = dictionary["distribution_frequency"] as? String ?? ""
        }
    }
}
