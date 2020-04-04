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
    var collateral: Array<Coin>?
    var principal: Array<Coin>?
    var accumulated_fees: Array<Coin>?
    var fees_updated: String?
    
    init() {}
    
    init(_ dictionary: [String: Any]) {
        if let id =  dictionary["id"] as? String {
            self.id = id
        }
        
        if let owner =  dictionary["owner"] as? String {
            self.owner = owner
        }
        
        if let rawCollaterals = dictionary["collateral"] as? Array<NSDictionary> {
            self.collateral =  Array<Coin>()
            for rawCollateral in rawCollaterals {
                self.collateral?.append(Coin(rawCollateral as! [String : Any]))
            }
        }
        
        if let rawPrincipals = dictionary["principal"] as? Array<NSDictionary> {
            self.principal =  Array<Coin>()
            for rawPrincipal in rawPrincipals {
                self.principal?.append(Coin(rawPrincipal as! [String : Any]))
            }
        }
        
        if let rawAccumulatedFees = dictionary["accumulated_fees"] as? Array<NSDictionary> {
            self.accumulated_fees =  Array<Coin>()
            for rawAccumulatedFee in rawAccumulatedFees {
                self.accumulated_fees?.append(Coin(rawAccumulatedFee as! [String : Any]))
            }
        }
        
        if let fees_updated =  dictionary["fees_updated"] as? String {
            self.fees_updated = fees_updated
        }
    }
    
    public func getCdpId() -> Int {
        return Int.init(id!)!
    }
    
    public func getMarketId() -> String {
        return collateral![0].denom + ":usd"
    }
    
    public func getcDenom() -> String {
        return collateral![0].denom
    }
    
    public func getpDenom() -> String {
        return principal![0].denom
    }
    
    public func getRawCollateralAmount() -> NSDecimalNumber {
        return NSDecimalNumber.init(string: collateral![0].amount)
    }
    
    public func getRawPrincipalAmount() -> NSDecimalNumber {
        return NSDecimalNumber.init(string: principal![0].amount)
    }
    
    public func getRawDebtAmount() -> NSDecimalNumber {
        if (accumulated_fees != nil && accumulated_fees!.count > 0) {
            return NSDecimalNumber.init(string: principal![0].amount).adding(NSDecimalNumber.init(string: accumulated_fees![0].amount))
        }
        return NSDecimalNumber.init(string: principal![0].amount)
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
        if (accumulated_fees != nil && accumulated_fees!.count > 0) {
            return NSDecimalNumber.init(string: accumulated_fees![0].amount).adding(getHiddenFee(cParam))
        }
        return getHiddenFee(cParam)
    }
    
    public func getEstimatedTotalDebt(_ cParam:CdpParam.CollateralParam) -> NSDecimalNumber {
        return getRawDebtAmount().adding(getHiddenFee(cParam))
    }
    
}
