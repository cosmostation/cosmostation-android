//
//  IrisToken_V1.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/01/21.
//  Copyright © 2021 wannabit. All rights reserved.
//

import Foundation

public struct IrisToken_V1 {
    var type: String?
    var symbol: String?
    var name: String?
    var scale: Int16?
    var min_unit: String?
    var initial_supply: String?
    var max_supply: String?
    var mintable: Bool?
    var owner: String?
    
    init(_ dictionary: NSDictionary?) {
        self.type = dictionary?["@type"] as? String
        self.symbol = dictionary?["symbol"] as? String
        self.name = dictionary?["name"] as? String
        self.scale = dictionary?["scale"] as? Int16
        self.min_unit = dictionary?["min_unit"] as? String
        self.initial_supply = dictionary?["initial_supply"] as? String
        self.max_supply = dictionary?["max_supply"] as? String
        self.mintable = dictionary?["mintable"] as? Bool
        self.owner = dictionary?["owner"] as? String
    }
}
