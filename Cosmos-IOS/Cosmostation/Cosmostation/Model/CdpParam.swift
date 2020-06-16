//
//  CdpParam.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/03/26.
//  Copyright © 2020 wannabit. All rights reserved.
//

import Foundation

public class CdpParam {
    var height: String = ""
    var result: CdpParamResult = CdpParamResult.init()
    
    init() {}
    
    init(_ dictionary: [String: Any]) {
        self.height = dictionary["height"] as? String ?? ""
        self.result = CdpParamResult.init(dictionary["result"] as! [String : Any])
    }
    
    init(_ dictionary: NSDictionary) {
        self.height = dictionary["height"] as? String ?? ""
        self.result = CdpParamResult.init(dictionary["result"] as! [String : Any])
    }
    
    public class CdpParamResult {
        var surplus_auction_threshold: String = ""
        var debt_auction_threshold: String = ""
        var circuit_breaker: Bool = true
        var global_debt_limit: Coin?
        var debt_param: DebtParam?
        var collateral_params: Array<CollateralParam> = Array<CollateralParam>()
        
        init() {}
        
        init(_ dictionary: [String: Any]) {
            if let surplus_auction_threshold =  dictionary["surplus_auction_threshold"] as? String {
                self.surplus_auction_threshold = surplus_auction_threshold
            }
            
            if let debt_auction_threshold =  dictionary["debt_auction_threshold"] as? String {
                self.debt_auction_threshold = debt_auction_threshold
            }
            
            if let circuit_breaker =  dictionary["circuit_breaker"] as? Bool {
                self.circuit_breaker = circuit_breaker
            }
            
            if let rawGlobalDebtLimits = dictionary["global_debt_limit"] as? [String : Any] {
                self.global_debt_limit = Coin(rawGlobalDebtLimits)
            }
            
            if let rawDebtParam = dictionary["debt_param"] as? [String : Any] {
                self.debt_param = DebtParam(rawDebtParam)
            }
            
            if let rawCollateralParams = dictionary["collateral_params"] as? Array<NSDictionary> {
                self.collateral_params.removeAll()
                for rawCollateralParam in rawCollateralParams {
                    self.collateral_params.append(CollateralParam(rawCollateralParam as! [String : Any]))
                }
            }
        }
        
        public func getcParam(_ denom:String) -> CollateralParam? {
            for param in collateral_params {
                if (param.denom == denom) {
                    return param
                }
            }
            return nil
        }
    }
    
    public class CollateralParam {
        var denom: String = ""
        var liquidation_ratio: String = ""
        var debt_limit: Coin?
        var stability_fee: String = ""
        var auction_size: String = ""
        var liquidation_penalty: String = ""
        var prefix: Int = 0
        var conversion_factor: String = ""
        var market_id: String = ""
        
        var spot_market_id: String = ""
        var liquidation_market_id: String = ""
        
        init() {}
        
        init(_ dictionary: [String: Any]) {
            if let denom =  dictionary["denom"] as? String {
                self.denom = denom
            }
            
            if let liquidation_ratio =  dictionary["liquidation_ratio"] as? String {
                self.liquidation_ratio = liquidation_ratio
            }
            
            if let rawDebt_limit = dictionary["debt_limit"] as? [String : Any] {
                self.debt_limit = Coin(rawDebt_limit)
            }
            
            
            if let stability_fee =  dictionary["stability_fee"] as? String {
                self.stability_fee = stability_fee
            }
            
            if let auction_size =  dictionary["auction_size"] as? String {
                self.auction_size = auction_size
            }
            
            if let liquidation_penalty =  dictionary["liquidation_penalty"] as? String {
                self.liquidation_penalty = liquidation_penalty
            }
            
            if let prefix =  dictionary["prefix"] as? Int {
                self.prefix = prefix
            }
            
            if let conversion_factor =  dictionary["conversion_factor"] as? String {
                self.conversion_factor = conversion_factor
            }
            
            if let market_id =  dictionary["market_id"] as? String {
                self.market_id = market_id
            }
            
            if let spot_market_id =  dictionary["spot_market_id"] as? String {
                self.spot_market_id = spot_market_id
            }
            
            if let liquidation_market_id =  dictionary["liquidation_market_id"] as? String {
                self.liquidation_market_id = liquidation_market_id
            }
        }
        
        func getpDenom() -> String {
            return debt_limit!.denom
        }
        
        func getMarketImgPath() -> String {
            if (!spot_market_id.isEmpty) {
                return spot_market_id.replacingOccurrences(of: ":", with: "")
            }
            if (!market_id.isEmpty) {
                return market_id.replacingOccurrences(of: ":", with: "")
            }
            return ""
        }
        
        func getDpMarketId() -> String {
            if (!spot_market_id.isEmpty) {
                return spot_market_id.split(separator: ":")[0].uppercased() + " : " + spot_market_id.split(separator: ":")[1].uppercased() + "X"
            }
            if (!market_id.isEmpty) {
                return market_id.split(separator: ":")[0].uppercased() + " : " + market_id.split(separator: ":")[1].uppercased() + "X"
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
    
    public class DebtParam {
        var denom: String = ""
        var reference_asset: String = ""
        var conversion_factor: String = ""
        var debt_floor: String = ""
        
        init() {}
        
        init(_ dictionary: [String: Any]) {
            if let denom =  dictionary["denom"] as? String {
                self.denom = denom
            }
            
            if let reference_asset =  dictionary["reference_asset"] as? String {
                self.reference_asset = reference_asset
            }
            
            if let conversion_factor =  dictionary["conversion_factor"] as? String {
                self.conversion_factor = conversion_factor
            }
            
            if let debt_floor =  dictionary["debt_floor"] as? String {
                self.debt_floor = debt_floor
            }
        }
    }
}
