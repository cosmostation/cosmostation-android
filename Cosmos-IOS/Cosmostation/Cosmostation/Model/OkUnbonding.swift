//
//  OkWithdraw.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/08/26.
//  Copyright © 2020 wannabit. All rights reserved.
//

import Foundation

public struct OkUnbonding {
    var delegator_address: String?
    var quantity: String?
    var completion_time: String?
    
    init(_ dictionary: NSDictionary?) {
        self.delegator_address = dictionary?["delegator_address"] as? String
        self.quantity = dictionary?["quantity"] as? String
        self.completion_time = dictionary?["completion_time"] as? String
    }
}
