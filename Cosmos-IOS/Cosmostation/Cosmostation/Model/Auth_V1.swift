//
//  Auth_V1.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/12/14.
//  Copyright © 2020 wannabit. All rights reserved.
//

import Foundation

public struct Auth_V1 {
    var type: String?
    var address: String?
    var account_number: String?
    var sequence: String?
    var start_time: String?
    var base_vesting_account: BaseVestingAccount_V1?
    
    init(_ dictionary: NSDictionary?) {
        self.type = dictionary?["@type"] as? String
        self.address = dictionary?["address"] as? String
        self.account_number = dictionary?["account_number"] as? String
        self.sequence = dictionary?["sequence"] as? String
        self.start_time = dictionary?["start_time"] as? String
        if let rawBaseVestingAccount = dictionary?["base_vesting_account"] as? NSDictionary {
            self.base_vesting_account = BaseVestingAccount_V1.init(rawBaseVestingAccount)
        }
    }
    
    func getAddress() -> String {
        if (base_vesting_account != nil || base_vesting_account?.base_account != nil) {
            return base_vesting_account!.base_account!.address!
        } else {
            return address!
        }
    }
    
    func getAccountNumber() -> String {
        if (base_vesting_account != nil || base_vesting_account?.base_account != nil) {
            return base_vesting_account!.base_account!.account_number!
        } else {
            return account_number!
        }
    }
    
    func getSequenceNumber() -> String {
        if (base_vesting_account != nil || base_vesting_account?.base_account != nil) {
            return base_vesting_account!.base_account!.sequence!
        } else {
            return sequence!
        }
    }
    
    public class BaseVestingAccount_V1 {
        var base_account: BaseAccount_V1?
        var original_vesting: Array<Coin>?
        var delegated_free: Array<Coin>?
        var delegated_vesting: Array<Coin>?
        var end_time: String?
        
        init(_ dictionary: NSDictionary?) {
            self.base_account = BaseAccount_V1.init(dictionary?["base_account"] as? NSDictionary)
            if let rawOriginalVestings = dictionary?["original_vesting"] as? Array<NSDictionary>  {
                self.original_vesting = Array<Coin>()
                for rawOriginalVesting in rawOriginalVestings {
                    self.original_vesting!.append(Coin(rawOriginalVesting))
                }
            }
            if let rawDelegatedFrees = dictionary?["delegated_free"] as? Array<NSDictionary>  {
                self.delegated_free = Array<Coin>()
                for rawDelegatedFree in rawDelegatedFrees {
                    self.delegated_free!.append(Coin(rawDelegatedFree))
                }
            }
            if let rawDelegatedVestings = dictionary?["delegated_vesting"] as? Array<NSDictionary>  {
                self.delegated_vesting = Array<Coin>()
                for rawDelegatedVesting in rawDelegatedVestings {
                    self.delegated_vesting!.append(Coin(rawDelegatedVesting))
                }
            }
            self.end_time = dictionary?["end_time"] as? String
        }
    }
    
    public class BaseAccount_V1 {
        var address: String?
        var account_number: String?
        var sequence: String?
        
        init(_ dictionary: NSDictionary?) {
            self.address = dictionary?["address"] as? String
            self.account_number = dictionary?["account_number"] as? String
            self.sequence = dictionary?["sequence"] as? String
        }
    }
}
