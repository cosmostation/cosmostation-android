//
//  DenomsToClaim.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/08/29.
//  Copyright © 2021 wannabit. All rights reserved.
//

import Foundation

public struct DenomsToClaim: Codable {
    var denom: String = ""
    var multiplier_name: String = ""
    
    init(_ denom: String, _ multiplier_name: String) {
        self.denom = denom
        self.multiplier_name = multiplier_name
    }
    
    init(_ dictionary: NSDictionary?) {
        self.denom = dictionary?["denom"] as! String
        self.multiplier_name = dictionary?["multiplier_name"] as! String
    }
}
