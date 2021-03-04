//
//  HardIndex.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/03/04.
//  Copyright © 2021 wannabit. All rights reserved.
//

import Foundation

public struct HardIndex {
    var denom: String?
    var value: String?
    
    init(_ dictionary: NSDictionary) {
        self.denom = dictionary["denom"] as? String
        self.value = dictionary["value"] as? String
    }
}
