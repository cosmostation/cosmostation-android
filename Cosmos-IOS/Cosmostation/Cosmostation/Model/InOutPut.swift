//
//  InOutPut.swift
//  Cosmostation
//
//  Created by yongjoo on 25/03/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import Foundation


public class InOutPut {
    var address: String = ""
    var coins: Array<Coin> = Array<Coin>()
    
    init() {}
    
    init(_ dictionary: [String: Any]) {
        self.address = dictionary["address"] as? String ?? ""
       
        self.coins.removeAll()
        let rawCoins = dictionary["coins"] as! Array<NSDictionary>
        for coin in rawCoins {
            self.coins.append(Coin(coin as! [String : Any]))
        }
    }
}
