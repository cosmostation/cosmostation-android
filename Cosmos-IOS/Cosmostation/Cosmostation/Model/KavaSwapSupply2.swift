//
//  KavaSwapSupply2.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/09/14.
//  Copyright © 2020 wannabit. All rights reserved.
//

import Foundation

public class KavaSwapSupply2 {
    var height: String = ""
    var result: Array<SwapSupply2> = Array<SwapSupply2>()
    
    init() {}
    
    init(_ dictionary: [String: Any]) {
        self.height = dictionary["height"] as? String ?? ""
        if let results = dictionary["result"] as? Array<NSDictionary> {
            self.result.removeAll()
            for supply in results {
                self.result.append(SwapSupply2(supply as! [String : Any]))
            }
        }
    }
    
    public class SwapSupply2 {
        var incoming_supply: Coin = Coin.init()
        var outgoing_supply: Coin = Coin.init()
        var current_supply: Coin = Coin.init()
        var time_limited_current_supply: Coin = Coin.init()
        var time_elapsed: String = ""
        
        init() {}
        
        init(_ dictionary: [String: Any]) {
            self.incoming_supply =  Coin.init(dictionary["incoming_supply"] as! [String : Any])
            self.outgoing_supply =  Coin.init(dictionary["outgoing_supply"] as! [String : Any])
            self.current_supply =  Coin.init(dictionary["current_supply"] as! [String : Any])
            self.time_limited_current_supply =  Coin.init(dictionary["time_limited_current_supply"] as! [String : Any])
            self.time_elapsed = dictionary["time_elapsed"] as? String ?? ""
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
    }
    
    public func getSwapSupply(_ denom:String) -> SwapSupply2?{
        for supply in result {
            if (denom.lowercased().starts(with: supply.incoming_supply.denom.lowercased())) {
                return supply
            }
        }
        return nil
    }
    
    
    public func getRemainCap(_ denom: String, _ supplyLimit: NSDecimalNumber) -> NSDecimalNumber {
        let swapSupply = getSwapSupply(denom)
        if (swapSupply != nil) {
            return supplyLimit.subtracting((swapSupply?.getCurrentAmount())!).subtracting((swapSupply?.getIncomingAmount())!)
        }
        return NSDecimalNumber.zero
    }
}
