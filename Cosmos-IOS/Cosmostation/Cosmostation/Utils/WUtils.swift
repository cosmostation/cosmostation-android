//
//  WUtils.swift
//  Cosmostation
//
//  Created by yongjoo on 22/03/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import Foundation

class WUtils {
    static func getAccountWithAccountInfo(_ account: Account, _ accountInfo: AccountInfo) -> Account {
        let result = account
        if(accountInfo.type == COSMOS_AUTH_TYPE_ACCOUNT) {
            result.account_address = accountInfo.value.address
            result.account_sequence_number = Int64(accountInfo.value.sequence) ?? 0
            result.account_account_numner = Int64(accountInfo.value.account_number) ?? 0
        } else {
            result.account_address = accountInfo.value.BaseVestingAccount.BaseAccount.address
            result.account_sequence_number = Int64(accountInfo.value.BaseVestingAccount.BaseAccount.sequence) ?? 0
            result.account_account_numner = Int64(accountInfo.value.BaseVestingAccount.BaseAccount.account_number) ?? 0
        }
        
        return account
    }
    
    static func getBalancesWithAccountInfo(_ account: Account, _ accountInfo: AccountInfo) -> Array<Balance> {
        var result = Array<Balance>()
        if(accountInfo.type == COSMOS_AUTH_TYPE_ACCOUNT) {
            for coin in accountInfo.value.coins {
                result.append(Balance.init( account.account_id, coin.denom, coin.amount, Date().millisecondsSince1970))
            }
            
        } else {
            for coin in accountInfo.value.BaseVestingAccount.BaseAccount.coins {
                result.append(Balance.init( account.account_id, coin.denom, coin.amount, Date().millisecondsSince1970))
            }
        }
        return result
    }
    
    static func getBondingwithBondingInfo(_ account: Account, _ bondinginfos: Array<BondingInfo>) -> Array<Bonding> {
        var result = Array<Bonding>()
        for bondinginfo in bondinginfos {
            result.append(Bonding(account.account_id, bondinginfo.validator_addr, bondinginfo.shares, Date().millisecondsSince1970))
        }
        return result
    }
    
    static func getBondingwithBondingInfo(_ account: Account, _ rawbondinginfos: Array<NSDictionary>) -> Array<Bonding> {
        var result = Array<Bonding>()
        for raw in rawbondinginfos{
            let bondinginfo = BondingInfo(raw as! [String : Any])
            result.append(Bonding(account.account_id, bondinginfo.validator_addr, bondinginfo.shares, Date().millisecondsSince1970))
        }
        return result
    }
    
    
    static func getUnbondingwithUnbondingInfo(_ account: Account, _ unbondinginfos: Array<UnbondingInfo>) -> Array<Unbonding> {
        var result = Array<Unbonding>()
        for unbondinginfo in unbondinginfos {
            for entry in unbondinginfo.entries {
                result.append(Unbonding(account.account_id, unbondinginfo.validator_address, entry.creation_height, nodeTimeToInt64(input: entry.completion_time).millisecondsSince1970, entry.initial_balance, entry.balance, Date().millisecondsSince1970))
            }
        }
        return result
    }
    
    static func getUnbondingwithUnbondingInfo(_ account: Account, _ rawunbondinginfos: Array<NSDictionary>) -> Array<Unbonding> {
        var result = Array<Unbonding>()
        for raw in rawunbondinginfos {
            let unbondinginfo = UnbondingInfo(raw as! [String : Any])
            for entry in unbondinginfo.entries {
                result.append(Unbonding(account.account_id, unbondinginfo.validator_address, entry.creation_height, nodeTimeToInt64(input: entry.completion_time).millisecondsSince1970, entry.initial_balance, entry.balance, Date().millisecondsSince1970))
            }
        }
        return result
    }
    
    
    static func nodeTimeToInt64(input: String) -> Date {
        let nodeFormatter = DateFormatter()
        nodeFormatter.dateFormat = "yyyy-MM-dd'T'HH:mm:ss.SSSSSSSSS'Z'"
        nodeFormatter.timeZone = NSTimeZone(name: "UTC") as TimeZone!
        return nodeFormatter.date(from: input) ?? Date.init()
    }
    
}

extension Date {
    var millisecondsSince1970:Int64 {
        return Int64((self.timeIntervalSince1970 * 1000.0).rounded())
    }
    
    var StringmillisecondsSince1970:String {
        return String((self.timeIntervalSince1970 * 1000.0).rounded())
    }
    
    init(milliseconds:Int) {
        self = Date(timeIntervalSince1970: TimeInterval(milliseconds) / 1000)
    }
}


