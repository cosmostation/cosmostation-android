//
//  MyCdp.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/03/04.
//  Copyright © 2021 wannabit. All rights reserved.
//

import Foundation

public struct MyCdp {
    var cdp: Cdp?
    var collateral_value: Coin?
    var collateralization_ratio: String?
    
    init(_ dictionary: NSDictionary?) {
        if let rawCdp = dictionary?["cdp"] as? NSDictionary {
            self.cdp = Cdp.init(rawCdp)
        }
        if let rawCollateralValue = dictionary?["collateral_value"] as? NSDictionary {
            self.collateral_value = Coin.init(rawCollateralValue)
        }
        self.collateralization_ratio = dictionary?["collateralization_ratio"] as? String
    }
    
    public func getTotalCollateralAmount() -> NSDecimalNumber {
        return NSDecimalNumber.init(string: cdp!.collateral!.amount)
    }
    
    public func getDpCollateralValue(_ pDenom:String) -> NSDecimalNumber {
        return NSDecimalNumber.init(string: collateral_value!.amount).multiplying(byPowerOf10: -WUtils.getKavaCoinDecimal(pDenom))
    }
    
    public func getLiquidationPrice(_ cDenom:String, _ pDenom:String, _ cParam:CollateralParam) -> NSDecimalNumber {
        let collateralAmount = cdp!.getRawCollateralAmount().multiplying(byPowerOf10: -WUtils.getKavaCoinDecimal(cDenom))
        let rawDebtAmount = cdp!.getEstimatedTotalDebt(cParam).multiplying(by: NSDecimalNumber.init(string: cParam.liquidation_ratio)).multiplying(byPowerOf10: -WUtils.getKavaCoinDecimal(pDenom))
        return rawDebtAmount.dividing(by: collateralAmount, withBehavior: WUtils.getDivideHandler(WUtils.getKavaCoinDecimal(pDenom)))
    }
    
    public func getDpEstimatedTotalDebtValue(_ pDenom:String, _ cParam:CollateralParam) -> NSDecimalNumber {
        return cdp!.getEstimatedTotalDebt(cParam).multiplying(byPowerOf10: -WUtils.getKavaCoinDecimal(pDenom))
    }
    
    public func getWithdrawableAmount(_ cDenom:String, _ pDenom:String, _ cParam:CollateralParam, _ cPrice:NSDecimalNumber, _ selfDepositAmount: NSDecimalNumber) -> NSDecimalNumber {
        let cValue = NSDecimalNumber.init(string: collateral_value!.amount)
        let minCValue = cdp!.getEstimatedTotalDebt(cParam).multiplying(by: cParam.getLiquidationRatio()).dividing(by: NSDecimalNumber.init(string: "0.95"), withBehavior:WUtils.handler0Down)
        let maxWithdrawableValue = cValue.subtracting(minCValue)
//            print("maxWithdrawableValue " , maxWithdrawableValue)
        var maxWithdrawableAmount = maxWithdrawableValue.multiplying(byPowerOf10: WUtils.getKavaCoinDecimal(cDenom) - WUtils.getKavaCoinDecimal(pDenom)).dividing(by: cPrice, withBehavior: WUtils.handler0Down)
//            print("maxWithdrawableAmount " , maxWithdrawableAmount)
        
        if (maxWithdrawableAmount.compare(selfDepositAmount).rawValue > 0) {
            maxWithdrawableAmount = selfDepositAmount
        }
        if (maxWithdrawableAmount.compare(NSDecimalNumber.zero).rawValue <= 0) {
            maxWithdrawableAmount = NSDecimalNumber.zero
        }
        return maxWithdrawableAmount
        
        
    }
    
    public func getMoreLoanableAmount(_ cParam:CollateralParam) -> NSDecimalNumber {
        var maxDebtValue = NSDecimalNumber.init(string: collateral_value!.amount).dividing(by: cParam.getLiquidationRatio(), withBehavior: WUtils.handler0Down)
//            print("maxDebtValue " , maxDebtValue)
        maxDebtValue = maxDebtValue.multiplying(by: NSDecimalNumber.init(string: "0.95"), withBehavior: WUtils.handler0Down)
        maxDebtValue = maxDebtValue.subtracting(cdp!.getEstimatedTotalDebt(cParam))
        if (maxDebtValue.compare(NSDecimalNumber.zero).rawValue <= 0) {
            maxDebtValue = NSDecimalNumber.zero
        }
        return maxDebtValue
    }
    
}
