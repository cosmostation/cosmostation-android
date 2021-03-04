//
//  MyCdp.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/03/04.
//  Copyright © 2021 wannabit. All rights reserved.
//

import Foundation

public struct MyCdp {
    var cdp: Cdp?
    var collateral_value: Coin?
    var collateralization_ratio: String?
    
    init(_ dictionary: NSDictionary?) {
        if let rawCdp = dictionary?["cdp"] as? NSDictionary {
            self.cdp = Cdp.init(rawCdp)
        }
        if let rawCollateralValue = dictionary?["collateral_value"] as? NSDictionary {
            self.collateral_value = Coin.init(rawCollateralValue)
        }
        self.collateralization_ratio = dictionary?["collateralization_ratio"] as? String
    }
    
}
