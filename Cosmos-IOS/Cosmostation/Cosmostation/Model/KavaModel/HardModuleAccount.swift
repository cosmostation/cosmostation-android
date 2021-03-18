//
//  HardModuleAccount.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/03/10.
//  Copyright © 2021 wannabit. All rights reserved.
//

import Foundation

public struct HardModuleAccount {
    var type: String?
    var value: HardAccountValue?
    
    init(_ dictionary: NSDictionary?) {
        self.type = dictionary?["type"] as? String
        if let rawValue = dictionary?["value"] as? NSDictionary {
            self.value = HardAccountValue.init(rawValue)
        }
    }
}
