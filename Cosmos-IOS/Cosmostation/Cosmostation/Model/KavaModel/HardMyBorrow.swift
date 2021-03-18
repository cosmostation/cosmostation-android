//
//  HardMyBorrow.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/03/04.
//  Copyright © 2021 wannabit. All rights reserved.
//

import Foundation

public struct HardMyBorrow {
    var borrower: String?
    var amount: Array<Coin>?
    var index: Array<HardIndex>?
    
    init(_ dictionary: NSDictionary?) {
        self.borrower = dictionary?["borrower"] as? String
        if let rawAmounts = dictionary?["amount"] as? Array<NSDictionary> {
            self.amount = Array<Coin>()
            for rawAmount in rawAmounts {
                self.amount?.append(Coin.init(rawAmount))
            }
        }
        if let rawHardIndexs = dictionary?["index"] as? Array<NSDictionary> {
            self.index = Array<HardIndex>()
            for rawHardIndex in rawHardIndexs {
                self.index?.append(HardIndex.init(rawHardIndex))
            }
        }
    }
}
