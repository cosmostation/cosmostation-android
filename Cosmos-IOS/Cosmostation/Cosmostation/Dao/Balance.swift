//
//  Balance.swift
//  Cosmostation
//
//  Created by yongjoo on 20/03/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import Foundation

public class Balance {
    var balance_id:Int64 = -1;
    var balance_account_id:Int64 = -1;
    var balance_denom: String = "";
    var balance_amount:String = "";
    var balance_fetch_time:Int64 = -1;
    
    var balance_frozen:String?
    var balance_locked:String?
    
    init(_ id:Int64, _ accout_id:Int64, _ demon:String, _ amount:String, _ fetch_time:Int64, _ frozen:String?, _ locked:String?) {
        self.balance_id = id;
        self.balance_account_id = accout_id;
        self.balance_denom = demon;
        self.balance_amount = amount;
        self.balance_fetch_time = fetch_time;
        if (frozen != nil) {
            self.balance_frozen = frozen;
        } else {
            self.balance_frozen = "0";
        }
        if (locked != nil) {
            self.balance_locked = locked;
        } else {
            self.balance_locked = "0";
        }
    }
    
    
    
    //uKAVA amount = available, frozen = delegated,   locked == vested but delegatable
    //hard amount = available, frozen == remain vestiing amount
    init(_ accout_id:Int64, _ demon:String, _ amount:String, _ fetch_time:Int64, _ frozen:String?, _ locked:String?) {
        self.balance_account_id = accout_id;
        self.balance_denom = demon;
        self.balance_amount = amount;
        self.balance_fetch_time = fetch_time;
        if (frozen != nil) {
            self.balance_frozen = frozen;
        } else {
            self.balance_frozen = "0";
        }
        if (locked != nil) {
            self.balance_locked = locked;
        } else {
            self.balance_locked = "0";
        }
    }
    
    init(_ accout_id:Int64, _ demon:String, _ amount:String, _ fetch_time:Int64) {
        self.balance_account_id = accout_id;
        self.balance_denom = demon;
        self.balance_amount = amount;
        self.balance_fetch_time = fetch_time;
    }
    
    func getAllAmountBnbToken() -> NSDecimalNumber {
        return WUtils.plainStringToDecimal(self.balance_amount).adding(WUtils.plainStringToDecimal(self.balance_locked))
    }
    
    func exchangeBnbValue(_ tic:NSMutableDictionary?) -> NSDecimalNumber {
        if (tic == nil || tic!.object(forKey: "lastPrice") == nil) {
            return NSDecimalNumber.zero
        }
        if (WUtils.isBnbMArketToken(self.balance_denom)) {
            return getAllAmountBnbToken().dividing(by: NSDecimalNumber(string: tic!.object(forKey: "lastPrice") as? String), withBehavior: WUtils.handler8)
        } else {
            return getAllAmountBnbToken().multiplying(by: NSDecimalNumber(string: tic!.object(forKey: "lastPrice") as? String), withBehavior: WUtils.handler8)
        }
    }
    
    func kavaTokenDollorValue(_ prices: [String:KavaTokenPrice], _ cdpParam: CdpParam) -> NSDecimalNumber {
        if (balance_denom == "usdx"){
            return WUtils.plainStringToDecimal(self.balance_amount).multiplying(byPowerOf10: -WUtils.getKavaCoinDecimal(balance_denom))
            
        } else {
            if (prices.count <= 0) {
                return NSDecimalNumber.zero
            }
            
            guard let collateralParam = cdpParam.result.getcParam(self.balance_denom), let kavaPrice = prices[collateralParam.liquidation_market_id] else {
                return NSDecimalNumber.zero
            }
            
            return WUtils.plainStringToDecimal(self.balance_amount).multiplying(byPowerOf10: -WUtils.getKavaCoinDecimal(balance_denom)).multiplying(by: NSDecimalNumber.init(string: kavaPrice.result.price), withBehavior: WUtils.handler6)
        }
    }
    
}


