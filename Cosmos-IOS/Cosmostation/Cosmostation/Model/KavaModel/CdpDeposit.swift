//
//  CdpDeposit.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/03/09.
//  Copyright © 2021 wannabit. All rights reserved.
//

import Foundation

public struct CdpDeposit {
    var cdp_id: String?
    var depositor: String?
    var amount: Coin?
    
    init(_ dictionary: NSDictionary?) {
        self.cdp_id = dictionary?["cdp_id"] as? String
        self.depositor = dictionary?["depositor"] as? String
        if let rawAmount = dictionary?["amount"] as? NSDictionary {
            self.amount = Coin.init(rawAmount)
        }
    }
}
