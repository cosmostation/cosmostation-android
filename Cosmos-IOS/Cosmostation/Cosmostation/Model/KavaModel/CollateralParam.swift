//
//  CollateralParam.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/03/04.
//  Copyright © 2021 wannabit. All rights reserved.
//

import Foundation

public struct CollateralParam {
    var denom: String?
    var type: String?
    var liquidation_ratio: String?
    var debt_limit: Coin?
    var stability_fee: String?
    var auction_size: String?
    var liquidation_penalty: String?
    var prefix: Int?
    var conversion_factor: String?
    var spot_market_id: String?
    var liquidation_market_id: String?
    
    init(_ dictionary: NSDictionary?) {
        self.denom = dictionary?["denom"] as? String
        self.type = dictionary?["type"] as? String
        self.liquidation_ratio = dictionary?["liquidation_ratio"] as? String
        if let rawDebtLimit = dictionary?["debt_limit"] as? NSDictionary {
            self.debt_limit = Coin.init(rawDebtLimit)
        }
        self.stability_fee = dictionary?["stability_fee"] as? String
        self.auction_size = dictionary?["auction_size"] as? String
        self.liquidation_penalty = dictionary?["liquidation_penalty"] as? String
        self.prefix = dictionary?["prefix"] as? Int
        self.conversion_factor = dictionary?["conversion_factor"] as? String
        self.spot_market_id = dictionary?["spot_market_id"] as? String
        self.liquidation_market_id = dictionary?["liquidation_market_id"] as? String
    }
    
    func getpDenom() -> String? {
        return debt_limit?.denom
    }
    
    func getcDenom() -> String? {
        return denom
    }
    
    func getMarketImgPath() -> String? {
        return denom! + "usd"
    }
    
    func getDpMarketId() -> String? {
        if let dpDenom = denom?.uppercased(), let dpDebtLimit = debt_limit?.denom.uppercased() {
            return dpDenom + " : " + dpDebtLimit
        }
        return ""
    }
    
    func getLiquidationRatio() -> NSDecimalNumber {
        return NSDecimalNumber.init(string: liquidation_ratio)
    }
    
    func getDpLiquidationRatio() -> NSDecimalNumber {
        return NSDecimalNumber.init(string: liquidation_ratio).multiplying(byPowerOf10: 2, withBehavior: WUtils.handler2Down)
    }
    
    func getDpLiquidationPenalty() -> NSDecimalNumber {
        return NSDecimalNumber.init(string: liquidation_penalty).multiplying(byPowerOf10: 2, withBehavior: WUtils.handler2Down)
    }
    
    func getDpStabilityFee() -> NSDecimalNumber {
        return (NSDecimalNumber.init(string: stability_fee).subtracting(NSDecimalNumber.one)).multiplying(by: NSDecimalNumber.init(string: "31536000")).multiplying(byPowerOf10: 2, withBehavior: WUtils.handler2Down)
    }
}
