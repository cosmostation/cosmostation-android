//
//  BnbAccountInfo.swift
//  Cosmostation
//
//  Created by yongjoo on 27/09/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import Foundation

public class BnbAccountInfo {
    var address: String = ""
    var account_number: UInt64 = 0
    var sequence: UInt64 = 0
    var balances: Array<BnbBalance> = Array<BnbBalance>()
    
    init() {}
    
    init(_ dictionary: [String: Any]) {
        self.address = dictionary["address"] as? String ?? ""
        self.account_number = dictionary["account_number"] as? UInt64 ?? 0
        self.sequence = dictionary["sequence"] as? UInt64 ?? 0
        self.balances.removeAll()
        if let rawBalances = dictionary["balances"] as? Array<NSDictionary> {
            for balance in rawBalances {
                self.balances.append(BnbBalance(balance as! [String : Any]))
            }
        }
    }
    
    init(_ dictionary: NSDictionary?) {
        self.address = dictionary?["address"] as? String ?? ""
        self.account_number = dictionary?["account_number"] as? UInt64 ?? 0
        self.sequence = dictionary?["sequence"] as? UInt64 ?? 0
        self.balances.removeAll()
        if let rawBalances = dictionary?["balances"] as? Array<NSDictionary> {
            for balance in rawBalances {
                self.balances.append(BnbBalance(balance as! [String : Any]))
            }
        }
        
    }
    
    public class BnbBalance {
        var free: String = ""
        var frozen: String = ""
        var locked: String = ""
        var symbol: String = ""
        
        init() {}
        
        init(_ dictionary: [String: Any]) {
            self.free = dictionary["free"] as? String ?? ""
            self.frozen = dictionary["frozen"] as? String ?? ""
            self.locked = dictionary["locked"] as? String ?? ""
            self.symbol = dictionary["symbol"] as? String ?? ""
        }
        
    }
}
