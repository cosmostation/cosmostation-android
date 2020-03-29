//
//  CdpDeposits.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/03/26.
//  Copyright © 2020 wannabit. All rights reserved.
//

import Foundation

public class CdpDeposits {
    var height: String = ""
    var result: Array<CdpDepositResult> = Array<CdpDepositResult>()
    
    init() {}
    
    init(_ dictionary: [String: Any]) {
        self.height = dictionary["height"] as? String ?? ""
        
        if let rawResults = dictionary["result"] as? Array<NSDictionary> {
            self.result.removeAll()
            for rawResult in rawResults {
                self.result.append(CdpDepositResult(rawResult as! [String : Any]))
            }
        }
    }
    
    init(_ dictionary: NSDictionary) {
        self.height = dictionary["height"] as? String ?? ""
        
        if let rawResults = dictionary["result"] as? Array<NSDictionary> {
            self.result.removeAll()
            for rawResult in rawResults {
                self.result.append(CdpDepositResult(rawResult as! [String : Any]))
            }
        }
    }
    
    public func getCdpId() -> Int {
        return Int.init(self.result[0].cdp_id)! 
    }
    
    public func getSelfDeposit(_ address:String) -> NSDecimalNumber {
        var result = NSDecimalNumber.zero
        for deposit in self.result{
            if (deposit.depositor == address) {
                result = NSDecimalNumber.init(string: deposit.amount[0].amount)
            }
        }
        return result;
    }
    
    public class CdpDepositResult {
        var cdp_id: String = ""
        var depositor: String = ""
        var amount: Array<Coin> = Array<Coin>()
        
        init() {}
        
        init(_ dictionary: [String: Any]) {
            if let cdp_id =  dictionary["cdp_id"] as? String {
                self.cdp_id = cdp_id
            }
            
            if let depositor =  dictionary["depositor"] as? String {
                self.depositor = depositor
            }
            
            if let rawAmounts = dictionary["amount"] as? Array<NSDictionary> {
                self.amount.removeAll()
                for rawAmount in rawAmounts {
                    self.amount.append(Coin(rawAmount as! [String : Any]))
                }
            }
        }
    }
}
