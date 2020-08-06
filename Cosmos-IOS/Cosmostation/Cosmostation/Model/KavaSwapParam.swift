//
//  KavaSwapParam.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/08/06.
//  Copyright © 2020 wannabit. All rights reserved.
//

import Foundation

public class KavaSwapParam {
    var height: String = ""
    var result: SwapParam = SwapParam.init()
    
    init() {}
    
    init(_ dictionary: [String: Any]) {
        self.height = dictionary["height"] as? String ?? ""
        self.result = SwapParam.init(dictionary["result"] as! [String : Any])
    }
    
    public class SwapParam {
        var bnb_deputy_address: String = ""
        var bnb_deputy_fixed_fee: String = ""
        var min_amount: String = ""
        var max_amount: String = ""
        var min_block_lock: String = ""
        var max_block_lock: String = ""
        
        init() {}
        
        init(_ dictionary: [String: Any]) {
            self.bnb_deputy_address = dictionary["bnb_deputy_address"] as? String ?? ""
            self.bnb_deputy_fixed_fee = dictionary["bnb_deputy_fixed_fee"] as? String ?? ""
            self.min_amount = dictionary["min_amount"] as? String ?? ""
            self.max_amount = dictionary["max_amount"] as? String ?? ""
            self.min_block_lock = dictionary["min_block_lock"] as? String ?? ""
            self.max_block_lock = dictionary["max_block_lock"] as? String ?? ""
        }
    }
    
    public func getMaxOnce() -> NSDecimalNumber {
        return NSDecimalNumber.init(string: result.max_amount)
    }
}
