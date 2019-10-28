//
//  IovBalanceInfo.swift
//  Cosmostation
//
//  Created by yongjoo on 28/10/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import Foundation
public class IovBalanceInfo {
    
    var address: String = ""
    var balance: Array<IovBalance> = Array<IovBalance>()
    
    init() {}
    
    init(_ dictionary: [String: Any]) {
        self.address = dictionary["address"] as? String ?? ""
        self.balance.removeAll()
        if let rawBalances = dictionary["balance"] as? Array<NSDictionary> {
            for balance in rawBalances {
                self.balance.append(IovBalance(balance as! [String : Any]))
            }
        }
    }
    
    public class IovBalance {
        var quantity: String = ""
        var fractionalDigits: Int = 0
        var tokenTicker: String = ""
        
        init() {}
        
        init(_ dictionary: [String: Any]) {
            self.quantity = dictionary["quantity"] as? String ?? ""
            self.fractionalDigits = dictionary["fractionalDigits"] as? Int ?? 0
            self.tokenTicker = dictionary["tokenTicker"] as? String ?? ""
        }
    }
}
