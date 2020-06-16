//
//  KavaSupply.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/06/16.
//  Copyright © 2020 wannabit. All rights reserved.
//

import Foundation

public class KavaSupply {
    var height: String = ""
    var result: Array<Coin> = Array<Coin>()
    
    init() {}
    
    init(_ dictionary: [String: Any]) {
        self.height = dictionary["height"] as? String ?? ""
        if let results = dictionary["result"] as? Array<NSDictionary> {
            self.result.removeAll()
            for coin in results {
                self.result.append(Coin(coin as! [String : Any]))
            }
        }
    }
    
    public func getDebtAmount() -> NSDecimalNumber {
        var result = NSDecimalNumber.zero
        if (getDebt() != nil) {
            result = NSDecimalNumber.init(string: getDebt()!.amount)
        }
        return result;
    }
    
    public func getDebt() -> Coin? {
        for coin in result {
            if (coin.denom == "debt") {
                return coin
            }
        }
        return nil
    }
}
