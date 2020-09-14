//
//  KavaSwapParam.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/08/06.
//  Copyright © 2020 wannabit. All rights reserved.
//

import Foundation

public class KavaSwapParam {
    var height: String = ""
    var result: SwapParam = SwapParam.init()
    
    init() {}
    
    init(_ dictionary: [String: Any]) {
        self.height = dictionary["height"] as? String ?? ""
        self.result = SwapParam.init(dictionary["result"] as! [String : Any])
    }
    
    public class SwapParam {
        var bnb_deputy_address: String = ""
        var bnb_deputy_fixed_fee: String = ""
        var min_amount: String = ""
        var max_amount: String = ""
        var min_block_lock: String = ""
        var max_block_lock: String = ""
        var supported_assets: Array<SwapSupportAsset> = Array<SwapSupportAsset>()
        
        init() {}
        
        init(_ dictionary: [String: Any]) {
            self.bnb_deputy_address = dictionary["bnb_deputy_address"] as? String ?? ""
            self.bnb_deputy_fixed_fee = dictionary["bnb_deputy_fixed_fee"] as? String ?? ""
            self.min_amount = dictionary["min_amount"] as? String ?? ""
            self.max_amount = dictionary["max_amount"] as? String ?? ""
            self.min_block_lock = dictionary["min_block_lock"] as? String ?? ""
            self.max_block_lock = dictionary["max_block_lock"] as? String ?? ""
            self.supported_assets.removeAll()
            if let supportedAssets = dictionary["supported_assets"] as? Array<NSDictionary> {
                for supportedAsset in supportedAssets {
                    self.supported_assets.append(SwapSupportAsset(supportedAsset as! [String : Any]))
                }
            }
        }
    }
    
    public class SwapSupportAsset {
        var denom: String = ""
        var coin_id: String = ""
        var limit: String = ""
        var active: Bool = false
        
        init() {}
        
        init(_ dictionary: [String: Any]) {
            self.denom = dictionary["denom"] as? String ?? ""
            self.coin_id = dictionary["coin_id"] as? String ?? ""
            self.limit = dictionary["limit"] as? String ?? ""
            self.active = dictionary["active"] as? Bool ?? false
        }
        
    }
    
    public func getSupportedSwapAsset(_ denom: String) -> SwapSupportAsset? {
        for supportAsset in result.supported_assets {
            if (denom.lowercased() == supportAsset.denom.lowercased()) {
                return supportAsset
            }
        }
        return nil
    }
    
    public func getSupportedSwapAssetLimit(_ denom: String) -> NSDecimalNumber {
        return NSDecimalNumber.init(string: getSupportedSwapAsset(denom)?.limit)
    }
    
    public func getSupportedSwapAssetMaxOnce(_ denom: String) -> NSDecimalNumber {
        return NSDecimalNumber.init(string: result.max_amount)
    }
}
