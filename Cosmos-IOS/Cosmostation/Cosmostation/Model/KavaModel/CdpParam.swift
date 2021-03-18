//
//  CdpParam.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/03/04.
//  Copyright © 2021 wannabit. All rights reserved.
//

import Foundation

public struct CdpParam {
    var surplus_auction_threshold: String?
    var surplus_auction_lot: String?
    var debt_auction_threshold: String?
    var debt_auction_lot: String?
    var savings_distribution_frequency: String?
    var circuit_breaker: Bool?
    var global_debt_limit: Coin?
    var debt_param: DebtParam?
    var collateral_params: Array<CollateralParam>?
    
    init(_ dictionary: NSDictionary?) {
        self.surplus_auction_threshold = dictionary?["surplus_auction_threshold"] as? String
        self.surplus_auction_lot = dictionary?["surplus_auction_lot"] as? String
        self.debt_auction_threshold = dictionary?["debt_auction_threshold"] as? String
        self.debt_auction_lot = dictionary?["debt_auction_lot"] as? String
        self.savings_distribution_frequency = dictionary?["savings_distribution_frequency"] as? String
        self.circuit_breaker = dictionary?["circuit_breaker"] as? Bool
        if let rawGlobalDebtLimit = dictionary?["global_debt_limit"] as? NSDictionary {
            self.global_debt_limit = Coin.init(rawGlobalDebtLimit)
        }
        if let rawDebtParam = dictionary?["debt_param"] as? NSDictionary {
            self.debt_param = DebtParam.init(rawDebtParam)
        }
        if let rawCollateralParams = dictionary?["collateral_params"] as? Array<NSDictionary> {
            self.collateral_params = Array<CollateralParam>()
            for rawCollateralParam in rawCollateralParams {
                self.collateral_params?.append(CollateralParam.init(rawCollateralParam))
            }
        }
    }
    
    public func getCollateralParamByDenom(_ denom: String) -> CollateralParam? {
        return collateral_params?.filter { $0.denom == denom}.first
//        for param in collateral_params {
//            if (param.denom == denom) {
//                return param
//            }
//        }
//        return nil
    }
    
    public func getCollateralParamByType(_ type: String) -> CollateralParam? {
        return collateral_params?.filter { $0.type == type}.first
//        for param in collateral_params {
//            if (param.type == type) {
//                return param
//            }
//        }
//        return nil
    }
    
    public func getGlobalDebtAmount() -> NSDecimalNumber {
        var result = NSDecimalNumber.zero
        if (global_debt_limit != nil && !global_debt_limit!.denom.isEmpty) {
            result =  NSDecimalNumber.init(string: global_debt_limit?.amount)
        }
        return result
    }
    
}
