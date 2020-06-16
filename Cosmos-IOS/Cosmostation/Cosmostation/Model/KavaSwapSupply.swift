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
            if (supply.denom == denom) {
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
        
        public func getCurrentCapAmount() -> NSDecimalNumber {
            if (getCurrentCap() != nil) {
                return NSDecimalNumber.init(string: getCurrentCap()!.amount)
            } else {
                return NSDecimalNumber.zero
            }
            
        }
        
        public func getRemainAmount() -> NSDecimalNumber {
            if (getRemainCap() != nil) {
                return NSDecimalNumber.init(string: getRemainCap()!.amount)
            } else {
                return NSDecimalNumber.zero
            }
        }
        
        
        public func getCurrentCap() -> Coin? {
            if (!supply_limit.denom.isEmpty) {
                return Coin.init(supply_limit.denom, getIncomingAmount().adding(getOutgoingAmount()).adding(getCurrentAmount()).stringValue)
            }
            return nil
        }
        
        public func getRemainCap() -> Coin? {
            if (!supply_limit.denom.isEmpty) {
                var remainAmount = getLimitAmount().subtracting(getIncomingAmount()).subtracting(getOutgoingAmount()).subtracting(getCurrentAmount())
                if (remainAmount.compare(NSDecimalNumber.init(string: "10000000")).rawValue < 0) {
                    remainAmount = NSDecimalNumber.zero
                }
                return Coin.init(supply_limit.denom, remainAmount.stringValue)
            }
            return nil
        }
        
    }
}
