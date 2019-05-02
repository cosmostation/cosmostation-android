//
//  Bonding.swift
//  Cosmostation
//
//  Created by yongjoo on 20/03/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import Foundation

//public class Bonding {
//    var bonding_id:Int64 = -1;
//    var bonding_account_id:Int64 = -1;
//    var bonding_v_address:String = "";
//    var bonding_fetch_time:Int64 = -1;
//
//    init(_ id:Int64, _ account_id: Int64, _ val_address:String,
//         _ shares:String, _ fetch_time:Int64) {
//        self.bonding_id = id;
//        self.bonding_account_id = account_id;
//        self.bonding_v_address = val_address;
//        self.bonding_fetch_time = fetch_time;
//    }
//
//    init(_ account_id: Int64, _ val_address:String, _ fetch_time:Int64) {
//        self.bonding_account_id = account_id;
//        self.bonding_v_address = val_address;
//        self.bonding_fetch_time = fetch_time;
//    }
//}

public class Bonding {
    var bonding_id:Int64 = -1;
    var bonding_account_id:Int64 = -1;
    var bonding_v_address:String = "";
    var bonding_shares:String = "";
    var bonding_fetch_time:Int64 = -1;

    init(_ id:Int64, _ account_id: Int64, _ val_address:String,
         _ shares:String, _ fetch_time:Int64) {
        self.bonding_id = id;
        self.bonding_account_id = account_id;
        self.bonding_v_address = val_address;
        self.bonding_shares = shares;
        self.bonding_fetch_time = fetch_time;
    }

    init(_ account_id: Int64, _ val_address:String,
         _ shares:String, _ fetch_time:Int64) {
        self.bonding_account_id = account_id;
        self.bonding_v_address = val_address;
        self.bonding_shares = shares;
        self.bonding_fetch_time = fetch_time;
    }

    public func getBondingAtom(_ validator:Validator) -> NSDecimalNumber {
        return (WUtils.stringToDecimal(validator.tokens)).dividing(by: WUtils.stringToDecimal(validator.delegator_shares)).multiplying(by: WUtils.stringToDecimal(bonding_shares)).rounding(accordingToBehavior: WUtils.handler0)
    }

    public func getBondingAtom(_ validators:Array<Validator>) -> NSDecimalNumber {
        for v in validators {
            if(v.operator_address ==  bonding_v_address) {
                return (WUtils.stringToDecimal(v.tokens)).dividing(by: WUtils.stringToDecimal(v.delegator_shares)).multiplying(by: WUtils.stringToDecimal(bonding_shares)).rounding(accordingToBehavior: WUtils.handler0)
            }
        }
        return NSDecimalNumber.zero
    }
}
