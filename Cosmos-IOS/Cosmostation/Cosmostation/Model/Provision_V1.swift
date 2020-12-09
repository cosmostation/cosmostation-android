//
//  Provision_V1.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/12/09.
//  Copyright © 2020 wannabit. All rights reserved.
//

import Foundation

public struct Provision_V1 {
    var annual_provisions: String?
    
    init(_ dictionary: NSDictionary?) {
        self.annual_provisions = dictionary?["annual_provisions"] as? String
    }
}
