//
//  KavaHardTotalBorrow.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/03/04.
//  Copyright © 2021 wannabit. All rights reserved.
//

import Foundation

public struct KavaHardTotalBorrow  {
    var height: String?
    var result: Array<Coin>?
    
    init(_ dictionary: NSDictionary?) {
        self.height = dictionary?["height"] as? String
        if let rawCoins = dictionary?["result"] as? Array<NSDictionary> {
            self.result = Array<Coin>()
            for rawCoin in rawCoins {
                self.result?.append(Coin.init(rawCoin))
            }
        }
    }
}
