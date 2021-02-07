//
//  OkTicker.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/02/07.
//  Copyright © 2021 wannabit. All rights reserved.
//

import Foundation

public struct OkTicker {
    var close: String?
    var high: String?
    var low: String?
    var open: String?
    var price: String?
    var product: String?
    var symbol: String?
    var timestamp: String?
    var volume: String?
    
    init(_ dictionary: NSDictionary?) {
        self.close = dictionary?["close"] as? String
        self.high = dictionary?["high"] as? String
        self.low = dictionary?["low"] as? String
        self.open = dictionary?["open"] as? String
        self.price = dictionary?["price"] as? String
        self.product = dictionary?["product"] as? String
        self.symbol = dictionary?["symbol"] as? String
        self.timestamp = dictionary?["timestamp"] as? String
        self.volume = dictionary?["volume"] as? String
    }
}
