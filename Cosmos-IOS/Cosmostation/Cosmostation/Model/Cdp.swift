//
//  Cdp.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/03/26.
//  Copyright © 2020 wannabit. All rights reserved.
//

import Foundation

public struct Cdp: Codable {
    var id: String?
    var owner: String?
    var collateral: Coin?
    var principal: Coin?
    var accumulated_fees: Coin?
    var fees_updated: String?
    
    init() {}
    
    init(_ dictionary: [String: Any]) {
        if let id =  dictionary["id"] as? String {
            self.id = id
        }
        
        if let owner =  dictionary["owner"] as? String {
            self.owner = owner
        }
        
        if let rawCollateral = dictionary["collateral"] as? [String : Any] {
            self.collateral = Coin(rawCollateral)
        }
        
        if let rawPrincipal = dictionary["principal"] as? [String : Any] {
            self.principal = Coin(rawPrincipal)
        }
        
        if let rawAccumulated_fees = dictionary["accumulated_fees"] as? [String : Any] {
            self.accumulated_fees = Coin(rawAccumulated_fees)
        }
        
        if let fees_updated =  dictionary["fees_updated"] as? String {
            self.fees_updated = fees_updated
        }
    }
    
    public func getCdpId() -> Int {
        return Int.init(id!)!
    }
    
    public func getMarketId() -> String {
        return collateral!.denom + ":usd:30"
    }
    
    public func getcDenom() -> String {
        return collateral!.denom
    }
    
    public func getpDenom() -> String {
        return principal!.denom
    }
    
    public func getRawCollateralAmount() -> NSDecimalNumber {
        return NSDecimalNumber.init(string: collateral!.amount)
    }
    
    public func getRawPrincipalAmount() -> NSDecimalNumber {
        return NSDecimalNumber.init(string: principal!.amount)
    }
    
    public func getRawDebtAmount() -> NSDecimalNumber {
        if (accumulated_fees != nil) {
            return NSDecimalNumber.init(string: principal!.amount).adding(NSDecimalNumber.init(string: accumulated_fees!.amount))
        }
        return NSDecimalNumber.init(string: principal!.amount)
    }
    
    public func getHiddenFee(_ cParam:CdpParam.CollateralParam) -> NSDecimalNumber {
        let rawDebtAmount = getRawDebtAmount()
        let now = Date().millisecondsSince1970
        let start = WUtils.nodeTimeToInt64(input: fees_updated!).millisecondsSince1970
        let gap = (now - start)/1000 + 30
        
        let doubel1 = Double(cParam.stability_fee)
        let doubel2 = Double(gap)
        let power = Double(pow(doubel1!, doubel2))
        return (rawDebtAmount.multiplying(by: NSDecimalNumber.init(value: power), withBehavior: WUtils.handler0Up)).subtracting(rawDebtAmount)
    }
    
    public func getEstimatedTotalFee(_ cParam:CdpParam.CollateralParam) -> NSDecimalNumber {
        if (accumulated_fees != nil) {
            return NSDecimalNumber.init(string: accumulated_fees!.amount).adding(getHiddenFee(cParam))
        }
        return getHiddenFee(cParam)
    }
    
    public func getEstimatedTotalDebt(_ cParam:CdpParam.CollateralParam) -> NSDecimalNumber {
        return getRawDebtAmount().adding(getHiddenFee(cParam))
    }
    
}
