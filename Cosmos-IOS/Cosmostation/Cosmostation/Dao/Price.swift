//
//  Price.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/05/17.
//  Copyright © 2021 wannabit. All rights reserved.
//

import Foundation

public struct Price {
    var denom: String?
    var last_updated: String?
    var prices: Array<Prices> = Array<Prices>()
    
    init(_ dictionary: NSDictionary?) {
        self.denom = dictionary?["denom"] as? String
        self.last_updated = dictionary?["last_updated"] as? String
        self.prices.removeAll()
        if let RawPrices = dictionary?["prices"] as? Array<NSDictionary> {
            for RawPrice in RawPrices {
                self.prices.append(Prices(RawPrice))
            }
        }
    }
    
    public struct Prices {
        var currency: String?
        var current_price: Double?
        var daily_price_change_in_percentage: Double?
        
        init(_ dictionary: NSDictionary?) {
            self.currency = dictionary?["currency"] as? String
            self.current_price = dictionary?["current_price"] as? Double
            self.daily_price_change_in_percentage = dictionary?["daily_price_change_in_percentage"] as? Double
        }
    }
    
    
    public func currencyPrice(_ currency: String) -> NSDecimalNumber {
        if let price = prices.filter({ $0.currency == currency }).first {
            return NSDecimalNumber.init(value: price.current_price!).rounding(accordingToBehavior: WUtils.handler8)
        }
        return NSDecimalNumber.zero.rounding(accordingToBehavior: WUtils.handler8)
    }
    
    public func priceChange() -> NSDecimalNumber {
        if let price = prices.filter({ $0.currency == "usd" }).first {
            return NSDecimalNumber.init(value: price.daily_price_change_in_percentage!).rounding(accordingToBehavior: WUtils.handler2Down)
        }
        return NSDecimalNumber.zero.rounding(accordingToBehavior: WUtils.handler2Down)
    }
}
