//
//  KavaSwapSupply.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/06/16.
//  Copyright © 2020 wannabit. All rights reserved.
//

import Foundation

public class KavaSwapSupply {
    var height: String = ""
    var result: Array<SwapSupply> = Array<SwapSupply>()
    
    init() {}
    
    init(_ dictionary: [String: Any]) {
        self.height = dictionary["height"] as? String ?? ""
        if let results = dictionary["result"] as? Array<NSDictionary> {
            self.result.removeAll()
            for supply in results {
                self.result.append(SwapSupply(supply as! [String : Any]))
            }
        }
    }
    
    public func getSwapSupply(_ denom:String) -> SwapSupply{
        for supply in result {
            if (supply.denom.lowercased() == denom.lowercased()) {
                return supply
            }
        }
        return SwapSupply.init()
    }
    
    public class SwapSupply {
        var denom: String = ""
        var incoming_supply: Coin = Coin.init()
        var outgoing_supply: Coin = Coin.init()
        var current_supply: Coin = Coin.init()
        var supply_limit: Coin = Coin.init()
        
        init() {}
        
        init(_ dictionary: [String: Any]) {
            self.denom = dictionary["denom"] as? String ?? ""
            self.incoming_supply =  Coin.init(dictionary["incoming_supply"] as! [String : Any])
            self.outgoing_supply =  Coin.init(dictionary["outgoing_supply"] as! [String : Any])
            self.current_supply =  Coin.init(dictionary["current_supply"] as! [String : Any])
            self.supply_limit =  Coin.init(dictionary["supply_limit"] as! [String : Any])
        }
        
        public func getIncomingAmount() -> NSDecimalNumber {
            var result = NSDecimalNumber.zero
            if (!incoming_supply.denom.isEmpty) {
                result = NSDecimalNumber.init(string: incoming_supply.amount)
            }
            return result;
        }
        
        public func getOutgoingAmount() -> NSDecimalNumber {
            var result = NSDecimalNumber.zero
            if (!outgoing_supply.denom.isEmpty) {
                result = NSDecimalNumber.init(string: outgoing_supply.amount)
            }
            return result;
        }
        
        public func getCurrentAmount() -> NSDecimalNumber {
            var result = NSDecimalNumber.zero
            if (!current_supply.denom.isEmpty) {
                result = NSDecimalNumber.init(string: current_supply.amount)
            }
            return result;
        }
        
        public func getLimitAmount() -> NSDecimalNumber {
            var result = NSDecimalNumber.zero
            if (!supply_limit.denom.isEmpty) {
                result = NSDecimalNumber.init(string: supply_limit.amount)
            }
            return result;
        }
        
    }
    
    public func getRemainCap(_ denom: String, _ supplyLimit: NSDecimalNumber) -> NSDecimalNumber {
        var remainCap = NSDecimalNumber.zero
        let swapSupply = getSwapSupply(denom)
        remainCap = supplyLimit.subtracting(swapSupply.getCurrentAmount()).subtracting(swapSupply.getIncomingAmount())
        return remainCap
    }
    
}
