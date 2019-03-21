//
//  Unbonding.swift
//  Cosmostation
//
//  Created by yongjoo on 20/03/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import Foundation

public class Unbonding {
    var unbonding_id:Int64 = -1;
    var unbonding_account_id:Int64 = -1;
    var unbonding_v_address:String = "";
    var unbonding_create_height:String = "";
    var unbonding_complete_time:Int64 = -1;
    var unbonding_inital_balance:String = "";
    var unbonding_balance:String = "";
    var unbonding_fetch_time:Int64 = -1;
    
    init(_ id:Int64, _ account_id:Int64, _ val_address:String, _ create_height:String,
         _ complete_time:Int64, _ initial_balance:String, _ balance:String, _ fetch_time:Int64) {
        self.unbonding_id = id;
        self.unbonding_account_id = account_id;
        self.unbonding_v_address = val_address;
        self.unbonding_create_height = create_height;
        self.unbonding_complete_time = complete_time;
        self.unbonding_inital_balance = initial_balance;
        self.unbonding_balance = balance;
        self.unbonding_fetch_time = fetch_time;
    }
    
}
