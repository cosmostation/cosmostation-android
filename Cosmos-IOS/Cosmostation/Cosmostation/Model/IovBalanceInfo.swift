//
//  IovBalanceInfo.swift
//  Cosmostation
//
//  Created by yongjoo on 28/10/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import Foundation
public class IovBalanceInfo {
    
    var coins: Array<IovCoin> = Array<IovCoin>()
    
    init() {}
    
    init(_ dictionary: [String: Any]) {
        self.coins.removeAll()
        if let rawCoins = dictionary["coins"] as? Array<NSDictionary> {
            for coin in rawCoins {
                self.coins.append(IovCoin(coin as! [String : Any]))
            }
        }
    }
    
    public class IovCoin {
        var whole: Int = 0
        var fractional: Int = 0
        var ticker: String = ""
        
        init() {}
        
        init(_ dictionary: [String: Any]) {
            self.whole = dictionary["whole"] as? Int ?? 0
            self.fractional = dictionary["fractional"] as? Int ?? 0
            self.ticker = dictionary["ticker"] as? String ?? ""
        }
        
        public func getDpAmount(_ denom:String) -> String {
            if (denom == ticker) {
                return "" + String(whole) + "." + String(fractional)
            }
            return "0"
        }
    }
    
    
//
//    init(_ dictionary: [String: Any]) {
//        self.address = dictionary["address"] as? String ?? ""
//        self.balance.removeAll()
//        if let rawBalances = dictionary["balance"] as? Array<NSDictionary> {
//            for balance in rawBalances {
//                self.balance.append(IovBalance(balance as! [String : Any]))
//            }
//        }
//    }
//
//    public class IovBalance {
//        var quantity: String = ""
//        var fractionalDigits: Int = 0
//        var tokenTicker: String = ""
//
//        init() {}
//
//        init(_ dictionary: [String: Any]) {
//            self.quantity = dictionary["quantity"] as? String ?? ""
//            self.fractionalDigits = dictionary["fractionalDigits"] as? Int ?? 0
//            self.tokenTicker = dictionary["tokenTicker"] as? String ?? ""
//        }
//    }
    
    
}
