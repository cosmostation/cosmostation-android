//
//  KavaSupply.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/06/16.
//  Copyright © 2020 wannabit. All rights reserved.
//

import Foundation

public struct KavaSupply {
    var height: String?
    var result: Array<Coin>?
    
    init(_ dictionary: NSDictionary?) {
        self.height = dictionary?["height"] as? String
        if let rawResults = dictionary?["result"] as? Array<NSDictionary> {
            self.result = Array<Coin>()
            for rawResult in rawResults {
                self.result?.append(Coin.init(rawResult))
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
        if (result == nil) {
            return nil
        }
        for coin in result! {
            if (coin.denom == "debt") {
                return coin
            }
        }
        return nil
    }
}
