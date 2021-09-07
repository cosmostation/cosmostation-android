//
//  GDexManager.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/09/02.
//  Copyright © 2021 wannabit. All rights reserved.
//

import Foundation

public struct GDexManager {
    var address = "";
    var reserve: Array<Coin> = Array<Coin>()
    
    init(_ address: String, _ coins: Array<Cosmos_Base_V1beta1_Coin>) {
        self.address = address
        coins.forEach { coin in
            reserve.append(Coin.init(coin.denom, coin.amount))
        }
    }
    
}
