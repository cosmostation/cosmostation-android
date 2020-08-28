//
//  OkTransfer.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/08/27.
//  Copyright © 2020 wannabit. All rights reserved.
//

import Foundation

public struct OkTransfer: Codable {
    var to: String = ""
    var coins: Array<Coin> = Array<Coin>()
    
    init() {}
    
    init(_ dictionary: [String: Any]) {
        self.to = dictionary["to"] as? String ?? ""
        
        self.coins.removeAll()
        if let rawCoins = dictionary["coins"] as? Array<NSDictionary> {
            for rawCoin in rawCoins {
                self.coins.append(Coin.init(rawCoin as! [String : Any]))
            }
        }
    }
    
    init(_ to:String, _ coins:Array<Coin>) {
        self.to = to
        self.coins = coins
    }
}
