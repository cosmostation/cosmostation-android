//
//  HardAccountValue.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/03/10.
//  Copyright © 2021 wannabit. All rights reserved.
//

import Foundation

public struct HardAccountValue {
    var address: String?
    var account_number: String?
    var sequence: String?
    var name: String?
    var coins: Array<Coin>?
    
    init(_ dictionary: NSDictionary?) {
        self.address = dictionary?["address"] as? String
        self.account_number = dictionary?["account_number"] as? String
        self.sequence = dictionary?["sequence"] as? String
        self.name = dictionary?["name"] as? String
        if let rawCoins = dictionary?["coins"] as? Array<NSDictionary> {
            self.coins = Array<Coin>()
            for rawCoin in rawCoins {
                self.coins?.append(Coin.init(rawCoin))
            }
        }
    }
    
}
