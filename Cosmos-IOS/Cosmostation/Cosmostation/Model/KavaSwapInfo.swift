//
//  KavaSwapInfo.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/05/13.
//  Copyright © 2020 wannabit. All rights reserved.
//

import Foundation

public class KavaSwapInfo {
    static let STATUS_NULL      = "NULL";
    static let STATUS_OPEN      = "Open";
    static let STATUS_COMPLETED = "Completed";
    static let STATUS_EXPIRED   = "Expired";
    
    var height: String = ""
    var result: KavaSwapResult = KavaSwapResult.init()
    
    init() {}

    
    init(_ dictionary: [String: Any]) {
        self.height = dictionary["height"] as? String ?? ""
        self.result = KavaSwapResult.init(dictionary["result"] as! [String : Any])
    }
    
    public class KavaSwapResult {
        var sender: String = ""
        var status: String = ""
        var direction: String = ""
        var amount: Array<Coin> = Array<Coin>()
        
        init() {}
        
        init(_ dictionary: [String: Any]) {
            self.sender = dictionary["sender"] as? String ?? ""
            self.status = dictionary["status"] as? String ?? ""
            self.direction = dictionary["direction"] as? String ?? ""
            self.amount.removeAll()
            if let rawAmounts = dictionary["amount"] as? Array<NSDictionary>  {
                for amount in rawAmounts {
                    self.amount.append(Coin(amount as! [String : Any]))
                }
            }
        }
    }
}
