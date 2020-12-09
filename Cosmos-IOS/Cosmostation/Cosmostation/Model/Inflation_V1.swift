//
//  Inflation_V1.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/12/09.
//  Copyright © 2020 wannabit. All rights reserved.
//

import Foundation

public struct Inflation_V1 {
    var inflation: String?
    
    init(_ dictionary: NSDictionary?) {
        self.inflation = dictionary?["inflation"] as? String
    }
}
