//
//  KavaSwapParam2.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/09/14.
//  Copyright © 2020 wannabit. All rights reserved.
//

import Foundation

public class KavaSwapParam2 {
    var height: String = ""
    var result: KavaBep3AssetParams = KavaBep3AssetParams.init()
    
    init() {}
    
    init(_ dictionary: [String: Any]) {
        self.height = dictionary["height"] as? String ?? ""
        self.result = KavaBep3AssetParams.init(dictionary["result"] as! [String : Any])
    }
    
    
    public class KavaBep3AssetParams {
        var asset_params: Array<KavaBep3AssetParam> = Array<KavaBep3AssetParam>()
        
        init() {}
        
        init(_ dictionary: [String: Any]) {
            self.asset_params.removeAll()
            if let assetParams = dictionary["asset_params"] as? Array<NSDictionary> {
                for assetParam in assetParams {
                    self.asset_params.append(KavaBep3AssetParam(assetParam as! [String : Any]))
                }
            }
        }
    }
    
    
    public class KavaBep3AssetParam {
        var denom: String = ""
        var coin_id: String = ""
        var active: Bool = false
        var deputy_address: String = ""
        var fixed_fee: String = ""
        var min_swap_amount: String = ""
        var max_swap_amount: String = ""
        var min_block_lock: String = ""
        var max_block_lock: String = ""
        var supply_limit: KavaBep3SupplyLimit = KavaBep3SupplyLimit.init()
        
        init() {}
        
        init(_ dictionary: [String: Any]) {
            self.denom = dictionary["denom"] as? String ?? ""
            self.coin_id = dictionary["coin_id"] as? String ?? ""
            self.active = dictionary["active"] as? Bool ?? false
            self.deputy_address = dictionary["deputy_address"] as? String ?? ""
            self.fixed_fee = dictionary["fixed_fee"] as? String ?? ""
            self.min_swap_amount = dictionary["min_swap_amount"] as? String ?? ""
            self.max_swap_amount = dictionary["max_swap_amount"] as? String ?? ""
            self.min_block_lock = dictionary["min_block_lock"] as? String ?? ""
            self.max_block_lock = dictionary["max_block_lock"] as? String ?? ""
            self.supply_limit = KavaBep3SupplyLimit.init(dictionary["supply_limit"] as! [String : Any])
        }
    }
    
    
    public class KavaBep3SupplyLimit {
        var limit: String = ""
        var time_limited: Bool = false
        var time_period: String = ""
        var time_based_limit: String = ""
        
        init() {}
        
        init(_ dictionary: [String: Any]) {
            self.limit = dictionary["limit"] as? String ?? ""
            self.time_limited = dictionary["time_limited"] as? Bool ?? false
            self.time_period = dictionary["time_period"] as? String ?? ""
            self.time_based_limit = dictionary["time_based_limit"] as? String ?? ""
        }
    }
    
    public func getSupportedSwapAsset(_ denom: String) -> KavaBep3AssetParam? {
        for assetParam in result.asset_params {
            if (denom.lowercased().starts(with: assetParam.denom.lowercased())) {
                return assetParam
            }
            if (denom.lowercased().starts(with: "xrp") && assetParam.denom.lowercased().starts(with: "xrp")) {
                return assetParam
            }
        }
        return nil
    }
    
    public func getSupportedSwapAssetLimit(_ denom: String) -> NSDecimalNumber {
        return NSDecimalNumber.init(string: getSupportedSwapAsset(denom)?.supply_limit.limit)
    }
    
    public func getSupportedSwapAssetMaxOnce(_ denom: String) -> NSDecimalNumber {
        return NSDecimalNumber.init(string: getSupportedSwapAsset(denom)?.max_swap_amount)
    }
    
    public func getSupportedSwapAssetMin(_ denom: String) -> NSDecimalNumber {
        return NSDecimalNumber.init(string: getSupportedSwapAsset(denom)?.min_swap_amount).adding(getSupportedSwapAssetFee(denom))
    }
    
    public func getSupportedSwapAssetFee(_ denom: String) -> NSDecimalNumber {
        return NSDecimalNumber.init(string: getSupportedSwapAsset(denom)?.fixed_fee)
    }
}
