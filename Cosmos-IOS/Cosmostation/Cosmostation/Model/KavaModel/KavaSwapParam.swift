//
//  KavaSwapParam.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/08/28.
//  Copyright © 2021 wannabit. All rights reserved.
//

import Foundation

public struct KavaSwapParam {
    var height: String?
    var result: SwapParam?
    
    init(_ dictionary: NSDictionary) {
        self.height = dictionary["height"] as? String
        if let rawSwapParam = dictionary["result"] as? NSDictionary {
            self.result = SwapParam.init(rawSwapParam)
        }
    }
}


public struct SwapParam {
    var swap_fee = NSDecimalNumber.zero
    var allowed_pools: Array<AllowedPool> = Array<AllowedPool>()
    
    init(_ dictionary: NSDictionary?) {
        if let rawSwapFee = dictionary?["swap_fee"] as? String {
            self.swap_fee = NSDecimalNumber.init(string: rawSwapFee)
        }
        if let rawAllowedPools = dictionary?["allowed_pools"] as? Array<NSDictionary> {
            for rawAllowedPool in rawAllowedPools {
                self.allowed_pools.append(AllowedPool.init(rawAllowedPool))
            }
        }
    }
}

public struct AllowedPool {
    var token_a: String?
    var token_b: String?
    
    init(_ dictionary: NSDictionary?) {
        self.token_a = dictionary?["token_a"] as? String
        self.token_b = dictionary?["token_b"] as? String
    }
}
