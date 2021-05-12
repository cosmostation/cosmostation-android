//
//  BnbTicker.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/05/11.
//  Copyright © 2021 wannabit. All rights reserved.
//

import Foundation

public struct BnbTicker {
    var symbol: String?
    var baseAssetName: String?
    var quoteAssetName: String?
    var lastPrice: String?
    
    init(_ dictionary: NSDictionary?) {
        self.symbol = dictionary?["symbol"] as? String
        self.baseAssetName = dictionary?["baseAssetName"] as? String
        self.quoteAssetName = dictionary?["quoteAssetName"] as? String
        self.lastPrice = dictionary?["lastPrice"] as? String
    }
    
    func getLastPrice() -> NSDecimalNumber {
        return NSDecimalNumber.init(string: lastPrice)
    }
}
