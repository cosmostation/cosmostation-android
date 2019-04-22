//
//  StakeMsg.swift
//  Cosmostation
//
//  Created by yongjoo on 22/04/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import Foundation

public struct StakeMsg: Codable {
    
    var type: String = ""
    var value: Value = Value.init()
    
    init() {}
    
    init(_ dictionary: [String: Any]) {
        self.type = dictionary["type"] as? String ?? ""
        self.value = Value.init(dictionary["value"] as! [String : Any])
    }
    
    public struct Value: Codable {
        var delegator_address: String?
        var validator_address: String?
        var amount: Coin?
        
        init() {}
        
        init(_ dictionary: [String: Any]) {
            
            if let daddress =  dictionary["delegator_address"] as? String {
                self.delegator_address = daddress
            }
            
            if let vaddress =  dictionary["validator_address"] as? String {
                self.validator_address = vaddress
            }
            
            if let rawAmount = dictionary["amount"] as? [String : Any] {
                self.amount = Coin(rawAmount)
            }
            
        }
        
    }
    
}
