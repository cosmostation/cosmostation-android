//
//  HardParam.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/03/04.
//  Copyright © 2021 wannabit. All rights reserved.
//

import Foundation

public struct HardParam {
    var minimum_borrow_usd_value: String?
    var money_markets: Array<HardMoneyMarket>?
    
    init(_ dictionary: NSDictionary?) {
        self.minimum_borrow_usd_value = dictionary?["minimum_borrow_usd_value"] as? String
        if let rawMoneyMarkets = dictionary?["money_markets"] as? Array<NSDictionary> {
            self.money_markets = Array<HardMoneyMarket>()
            for rawMoneyMarket in rawMoneyMarkets {
                self.money_markets?.append(HardMoneyMarket.init(rawMoneyMarket))
            }
        }
    }
    
    public func getHardMoneyMarket(_ denom: String) -> HardMoneyMarket? {
        return money_markets?.filter { $0.denom == denom}.first
    }
    
    public func getSpotMarketId(_ denom: String) -> String? {
        if let market = money_markets?.filter { $0.denom == denom}.first {
            return market.spot_market_id! + ":30"
        }
        return ""
    }
    
    public func getLTV(_ denom: String) -> NSDecimalNumber {
        var result = NSDecimalNumber.zero
        if let market = money_markets?.filter { $0.denom == denom}.first {
            return NSDecimalNumber.init(string: market.borrow_limit?.loan_to_value)
        }
        return result
    }
    
    
    public struct HardMoneyMarket {
        var denom: String?
        var borrow_limit: BorrowLimit?
        var spot_market_id: String?
        var conversion_factor: String?
        var interest_rate_model: InterestRateModel?
        var reserve_factor: String?
        var keeper_reward_percentage: String?
        
        init(_ dictionary: NSDictionary?) {
            self.denom = dictionary?["denom"] as? String
            if let rawBorrowLimit = dictionary?["borrow_limit"] as? NSDictionary {
                self.borrow_limit = BorrowLimit.init(rawBorrowLimit)
            }
            self.spot_market_id = dictionary?["spot_market_id"] as? String
            self.conversion_factor = dictionary?["conversion_factor"] as? String
            if let rawInterestRateModel = dictionary?["interest_rate_model"] as? NSDictionary {
                self.interest_rate_model = InterestRateModel.init(rawInterestRateModel)
            }
            self.reserve_factor = dictionary?["reserve_factor"] as? String
            self.keeper_reward_percentage = dictionary?["keeper_reward_percentage"] as? String
        }
        
    }
    
    public struct BorrowLimit {
        var has_max_limit: Bool?
        var maximum_limit: String?
        var loan_to_value: String?
        
        init(_ dictionary: NSDictionary?) {
            self.has_max_limit = dictionary?["has_max_limit"] as? Bool
            self.maximum_limit = dictionary?["maximum_limit"] as? String
            self.loan_to_value = dictionary?["loan_to_value"] as? String
        }
    }
    
    public struct InterestRateModel {
        var base_rate_apy: String?
        var base_multiplier: String?
        var kink: String?
        var jump_multiplier: String?
        
        init(_ dictionary: NSDictionary?) {
            self.base_rate_apy = dictionary?["base_rate_apy"] as? String
            self.base_multiplier = dictionary?["base_multiplier"] as? String
            self.kink = dictionary?["kink"] as? String
            self.jump_multiplier = dictionary?["jump_multiplier"] as? String
        }
    }
}
