//
//  KavaAccountInfo.swift
//  Cosmostation
//
//  Created by yongjoo on 07/11/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import Foundation


public struct KavaAccountInfo: Codable {
    var height: String?
    var result: Result?
    
    init(_ dictionary: [String: Any]) {
        self.height = dictionary["height"] as? String
        self.result = dictionary["result"] as? Result
    }
    
    public struct Result: Codable  {
        var type: String?
        var value: Value?
        
        init(_ dictionary: [String: Any]) {
            self.type = dictionary["type"] as? String
            self.value = dictionary["value"] as? Value
        }
    }
    
    public struct Value: Codable {
        var address: String?
        var coins: Array<Coin>?
        var account_number: String?
        var sequence: String?
        var PeriodicVestingAccount: PeriodicVestingAccount?
        var vesting_period_progress: Array<VestingPeriodProgress>?
        
        init(_ dictionary: [String: Any]) {
            self.address = dictionary["address"] as? String
            self.coins = dictionary["coins"] as? Array<Coin>
            self.account_number = dictionary["account_number"] as? String
            self.sequence = dictionary["sequence"] as? String
            self.PeriodicVestingAccount = dictionary["PeriodicVestingAccount"] as? PeriodicVestingAccount
            self.vesting_period_progress = dictionary["vesting_period_progress"] as? Array<VestingPeriodProgress>
        }
    }
}

public struct PeriodicVestingAccount: Codable {
    var BaseVestingAccount: BaseVestingAccount?
    var vesting_periods: Array<VestingPeriod>?
    
    init(_ dictionary: [String: Any]) {
        self.BaseVestingAccount = dictionary["BaseVestingAccount"] as? BaseVestingAccount
        self.vesting_periods = dictionary["vesting_periods"] as? Array<VestingPeriod>
    }
}

public struct BaseVestingAccount: Codable {
    var BaseAccount: BaseAccount?
    var original_vesting: Array<Coin>?
    var delegated_vesting: Array<Coin>?
    var end_time: String?
    
    init(_ dictionary: [String: Any]) {
        self.BaseAccount = dictionary["BaseAccount"] as? BaseAccount
        self.original_vesting = dictionary["original_vesting"] as? Array<Coin>
        self.delegated_vesting = dictionary["delegated_vesting"] as? Array<Coin>
        self.end_time = dictionary["end_time"] as? String
    }
}

public struct BaseAccount: Codable {
    var address: String?
    var coins: Array<Coin>?
    var account_number: String?
    var sequence: String?
    
    init(_ dictionary: [String: Any]) {
        self.address = dictionary["address"] as? String
        self.coins = dictionary["coins"] as? Array<Coin>
        self.account_number = dictionary["account_number"] as? String
        self.sequence = dictionary["sequence"] as? String
    }
}

public struct VestingPeriod: Codable {
    var amount: Array<Coin>?
    
    init(_ dictionary: [String: Any]) {
        self.amount = dictionary["amount"] as? Array<Coin>
    }
}

public struct VestingPeriodProgress: Codable {
    var period_complete: Bool?
    var vesting_successful: Bool?
    
    init(_ dictionary: [String: Any]) {
        self.period_complete = dictionary["period_complete"] as? Bool
        self.vesting_successful = dictionary["vesting_successful"] as? Bool
    }
}
