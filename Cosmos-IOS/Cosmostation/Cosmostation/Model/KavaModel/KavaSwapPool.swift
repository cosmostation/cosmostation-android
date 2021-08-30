//
//  KavaSwapPool.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/08/28.
//  Copyright © 2021 wannabit. All rights reserved.
//

import Foundation

public struct KavaSwapPool {
    var height: String?
    var result: Array<SwapPool> = Array<SwapPool>()
    
    init(_ dictionary: NSDictionary) {
        self.height = dictionary["height"] as? String
        if let rawHardParams = dictionary["result"] as? Array<NSDictionary> {
            rawHardParams.forEach { rawHardParam in
                self.result.append(SwapPool.init(rawHardParam))
            }
        }
    }
}

public struct SwapPool {
    var name: String?
    var total_shares = NSDecimalNumber.zero
    var coins: Array<Coin> = Array<Coin>()
    
    init(_ dictionary: NSDictionary?) {
        self.name = dictionary?["name"] as? String
        if let rawTotalShares = dictionary?["total_shares"] as? String {
            self.total_shares = NSDecimalNumber.init(string: rawTotalShares)
        }
        if let rawCoins = dictionary?["coins"] as? Array<NSDictionary> {
            for rawCoin in rawCoins {
                self.coins.append(Coin.init(rawCoin))
            }
        }
    }
}
