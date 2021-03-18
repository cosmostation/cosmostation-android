//
//  DebtParam.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/03/04.
//  Copyright © 2021 wannabit. All rights reserved.
//

import Foundation

public struct DebtParam {
    var denom: String?
    var reference_asset: String?
    var conversion_factor: String?
    var debt_floor: String?
    var savings_rate: String?
    
    init(_ dictionary: NSDictionary?) {
        self.denom = dictionary?["denom"] as? String
        self.reference_asset = dictionary?["reference_asset"] as? String
        self.conversion_factor = dictionary?["conversion_factor"] as? String
        self.debt_floor = dictionary?["debt_floor"] as? String
        self.savings_rate = dictionary?["savings_rate"] as? String
    }
}
