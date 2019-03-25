//
//  Msg.swift
//  Cosmostation
//
//  Created by yongjoo on 25/03/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import Foundation

public class Msg {
    var type: String = ""
    var value: Value = Value.init()
    
    init() {}
    
    init(_ dictionary: [String: Any]) {
        self.type = dictionary["type"] as? String ?? ""
        self.value = Value.init(dictionary["value"] as! [String : Any])
    }
    
    
    
    public class Value {
//        var inputs: Array<InOutPut> = Array<InOutPut>()
//        var outputs: Array<InOutPut> = Array<InOutPut>()
//        var from_address: String = ""
//        var to_address: String = ""
//        var delegator_address: String = ""
//        var validator_address: String = ""
//        var value: Coin = Coin.init()
//        var shares_amount: String = ""
//        var amount: Array<Coin> = Array<Coin>()
        
        var inputs: Array<InOutPut>?
        var outputs: Array<InOutPut>?
        var from_address: String?
        var to_address: String?
        var delegator_address: String?
        var validator_address: String?
        var value: Coin?
        var shares_amount: String?
        var amount: Array<Coin>?
        
        init() {}
        
        init(_ dictionary: [String: Any]) {
            if let rawInputs = dictionary["inputs"] as? Array<NSDictionary> {
                self.inputs =  Array<InOutPut>()
                for rawInput in rawInputs {
                    self.inputs?.append(InOutPut(rawInput as! [String : Any]))
                }
            }
            
            if let rawOutputs = dictionary["outputs"] as? Array<NSDictionary> {
                self.outputs =  Array<InOutPut>()
                for rawOutput in rawOutputs {
                    self.outputs?.append(InOutPut(rawOutput as! [String : Any]))
                }
            }
            
            if let fddress =  dictionary["from_address"] as? String {
                self.from_address = fddress
            }
            
            if let tddress =  dictionary["to_address"] as? String {
                self.to_address = tddress
            }
            
            if let daddress =  dictionary["delegator_address"] as? String {
                self.delegator_address = daddress
            }
            
            if let vaddress =  dictionary["validator_address"] as? String {
                self.validator_address = vaddress
            }
            
            if let rawValue = dictionary["value"] as? [String : Any] {
                self.value = Coin(rawValue)
            }
            
            if let sAmount =  dictionary["shares_amount"] as? String {
                self.shares_amount = sAmount
            }
            
            if let rawAmounts = dictionary["amount"] as? Array<NSDictionary> {
                self.amount =  Array<Coin>()
                for rawAmount in rawAmounts {
                    self.amount?.append(Coin(rawAmount as! [String : Any]))
                }
            }
            
            
        }
        
    }
}
