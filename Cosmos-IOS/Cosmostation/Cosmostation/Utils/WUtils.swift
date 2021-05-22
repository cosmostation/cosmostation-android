//
//  WUtils.swift
//  Cosmostation
//
//  Created by yongjoo on 22/03/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import Foundation
import UIKit

class WUtils {
    
    static let handler18 = NSDecimalNumberHandler(roundingMode: NSDecimalNumber.RoundingMode.down, scale: 18, raiseOnExactness: true, raiseOnOverflow: false, raiseOnUnderflow: true, raiseOnDivideByZero: true)
    
    static let handler12 = NSDecimalNumberHandler(roundingMode: NSDecimalNumber.RoundingMode.down, scale: 12, raiseOnExactness: true, raiseOnOverflow: false, raiseOnUnderflow: true, raiseOnDivideByZero: true)
    
    static let handler8 = NSDecimalNumberHandler(roundingMode: NSDecimalNumber.RoundingMode.down, scale: 8, raiseOnExactness: false, raiseOnOverflow: false, raiseOnUnderflow: false, raiseOnDivideByZero: true)
    
    static let handler6 = NSDecimalNumberHandler(roundingMode: NSDecimalNumber.RoundingMode.down, scale: 6, raiseOnExactness: false, raiseOnOverflow: false, raiseOnUnderflow: false, raiseOnDivideByZero: true)
    
    static let handler4Down = NSDecimalNumberHandler(roundingMode: NSDecimalNumber.RoundingMode.down, scale: 4, raiseOnExactness: false, raiseOnOverflow: false, raiseOnUnderflow: false, raiseOnDivideByZero: true)
    
    static let handler2 = NSDecimalNumberHandler(roundingMode: NSDecimalNumber.RoundingMode.bankers, scale: 2, raiseOnExactness: false, raiseOnOverflow: false, raiseOnUnderflow: false, raiseOnDivideByZero: true)
    
    static let handler2Down = NSDecimalNumberHandler(roundingMode: NSDecimalNumber.RoundingMode.down, scale: 2, raiseOnExactness: false, raiseOnOverflow: false, raiseOnUnderflow: false, raiseOnDivideByZero: true)
    
    static let handler3Down = NSDecimalNumberHandler(roundingMode: NSDecimalNumber.RoundingMode.down, scale: 3, raiseOnExactness: false, raiseOnOverflow: false, raiseOnUnderflow: false, raiseOnDivideByZero: true)
    
    static let handler0 = NSDecimalNumberHandler(roundingMode: NSDecimalNumber.RoundingMode.bankers, scale: 0, raiseOnExactness: false, raiseOnOverflow: false, raiseOnUnderflow: false, raiseOnDivideByZero: true)
    
    static let handler0Up = NSDecimalNumberHandler(roundingMode: NSDecimalNumber.RoundingMode.up, scale: 0, raiseOnExactness: false, raiseOnOverflow: false, raiseOnUnderflow: false, raiseOnDivideByZero: true)
    
    static let handler0Down = NSDecimalNumberHandler(roundingMode: NSDecimalNumber.RoundingMode.down, scale: 0, raiseOnExactness: false, raiseOnOverflow: false, raiseOnUnderflow: false, raiseOnDivideByZero: true)
    
    static let handler12Down = NSDecimalNumberHandler(roundingMode: NSDecimalNumber.RoundingMode.down, scale: 12, raiseOnExactness: false, raiseOnOverflow: false, raiseOnUnderflow: false, raiseOnDivideByZero: true)

    static let handler24Down = NSDecimalNumberHandler(roundingMode: NSDecimalNumber.RoundingMode.down, scale: 24, raiseOnExactness: false, raiseOnOverflow: false, raiseOnUnderflow: false, raiseOnDivideByZero: true)
    
    static func getDivideHandler(_ decimal:Int16) -> NSDecimalNumberHandler{
        return NSDecimalNumberHandler(roundingMode: NSDecimalNumber.RoundingMode.down, scale: decimal, raiseOnExactness: false, raiseOnOverflow: false, raiseOnUnderflow: false, raiseOnDivideByZero: true)
    }

    
    static func getAccountWithAccountInfo(_ account: Account, _ accountInfo: AccountInfo) -> Account {
        let result = account
        if (accountInfo.type == COSMOS_AUTH_TYPE_ACCOUNT || accountInfo.type == COSMOS_AUTH_TYPE_ACCOUNT_LEGACY || accountInfo.type == COSMOS_AUTH_TYPE_CERTIK_MANUAL) {
            result.account_address = accountInfo.value.address
            result.account_sequence_number = Int64(accountInfo.value.sequence) ?? 0
            result.account_account_numner = Int64(accountInfo.value.account_number) ?? 0
        } else {
            result.account_address = accountInfo.value.BaseVestingAccount.BaseAccount.address
            result.account_sequence_number = Int64(accountInfo.value.BaseVestingAccount.BaseAccount.sequence) ?? 0
            result.account_account_numner = Int64(accountInfo.value.BaseVestingAccount.BaseAccount.account_number) ?? 0
        }
        return result
    }
    
    static func getAccountWithBnbAccountInfo(_ account: Account, _ accountInfo: BnbAccountInfo) -> Account {
        let result = account
        result.account_address = accountInfo.address
        result.account_sequence_number = Int64(accountInfo.sequence)
        result.account_account_numner = Int64(accountInfo.account_number)
        return result
    }
    
    static func getAccountWithKavaAccountInfo(_ account: Account, _ accountInfo: KavaAccountInfo) -> Account {
        let result = account
        if (accountInfo.result.type == COSMOS_AUTH_TYPE_ACCOUNT) {
            result.account_address = accountInfo.result.value.address
            result.account_sequence_number = Int64(accountInfo.result.value.sequence)!
            result.account_account_numner = Int64(accountInfo.result.value.account_number)!
        } else if (accountInfo.result.type == COSMOS_AUTH_TYPE_VESTING_ACCOUNT || accountInfo.result.type == COSMOS_AUTH_TYPE_P_VESTING_ACCOUNT) {
            result.account_address = accountInfo.result.value.address
            result.account_sequence_number = Int64(accountInfo.result.value.sequence)!
            result.account_account_numner = Int64(accountInfo.result.value.account_number)!
        }
        return result
    }
    
    static func getAccountWithOkAccountInfo(_ account: Account, _ accountInfo: OkAccountInfo) -> Account {
        let result = account
        if (accountInfo.type == COSMOS_AUTH_TYPE_OKEX_ACCOUNT) {
            result.account_address = accountInfo.value!.address!
            result.account_sequence_number = Int64(accountInfo.value!.sequence!)!
            result.account_account_numner = Int64(accountInfo.value!.account_number!)!
        }
        return result
    }
    
    static func getAccountWithVestingAccountInfo(_ account: Account, _ accountInfo: VestingAccountInfo) -> Account {
        let result = account
        result.account_address = account.account_address
        result.account_sequence_number = Int64(accountInfo.result!.value!.sequence!)!
        result.account_account_numner = Int64(accountInfo.result!.value!.account_number!)!
        return result
    }
    
    static func getBalancesWithAccountInfo(_ account: Account, _ accountInfo: AccountInfo) -> Array<Balance> {
        var result = Array<Balance>()
        if (accountInfo.type == COSMOS_AUTH_TYPE_ACCOUNT || accountInfo.type == COSMOS_AUTH_TYPE_ACCOUNT_LEGACY ||
            accountInfo.type == COSMOS_AUTH_TYPE_CERTIK_MANUAL) {
            for coin in accountInfo.value.coins {
                result.append(Balance.init(account.account_id, coin.denom, coin.amount, Date().millisecondsSince1970))
            }
            
        } else {
            for coin in accountInfo.value.BaseVestingAccount.BaseAccount.coins {
                result.append(Balance.init(account.account_id, coin.denom, coin.amount, Date().millisecondsSince1970))
            }
        }
        return result
    }
    
    static func getBalancesWithAccountInfo(_ accountInfo: AccountInfo) -> Array<Balance> {
        var result = Array<Balance>()
        if(accountInfo.type == COSMOS_AUTH_TYPE_ACCOUNT ||
            accountInfo.type == COSMOS_AUTH_TYPE_ACCOUNT_LEGACY ||
            accountInfo.type == COSMOS_AUTH_TYPE_CERTIK_MANUAL) {
            for coin in accountInfo.value.coins {
                result.append(Balance.init(-1, coin.denom, coin.amount, Date().millisecondsSince1970))
            }

        } else {
            for coin in accountInfo.value.BaseVestingAccount.BaseAccount.coins {
                result.append(Balance.init(-1, coin.denom, coin.amount, Date().millisecondsSince1970))
            }
        }
        return result
    }
    
    
    
    static func getBalancesWithBnbAccountInfo(_ account: Account, _ accountInfo: BnbAccountInfo) -> Array<Balance> {
        var result = Array<Balance>()
        for bnbBalance in accountInfo.balances {
            result.append(Balance(account.account_id, bnbBalance.symbol, bnbBalance.free, Date().millisecondsSince1970, bnbBalance.frozen, bnbBalance.locked))
        }
        return result;
    }
    
    static func getBalancesWithKavaAccountInfo(_ account: Account, _ accountInfo: KavaAccountInfo) -> Array<Balance> {
        var result = Array<Balance>()
        if (accountInfo.result.type == COSMOS_AUTH_TYPE_ACCOUNT) {
            accountInfo.result.value.coins.forEach({ (coin) in
                result.append(Balance.init(account.account_id, coin.denom, coin.amount, Date().millisecondsSince1970))
            })
            
        } else if (accountInfo.result.type == COSMOS_AUTH_TYPE_VESTING_ACCOUNT || accountInfo.result.type == COSMOS_AUTH_TYPE_P_VESTING_ACCOUNT) {
            var dpBalance = NSDecimalNumber.zero
            var dpVesting = NSDecimalNumber.zero
            var originalVesting = NSDecimalNumber.zero
            var remainVesting = NSDecimalNumber.zero
            var delegatedVesting = NSDecimalNumber.zero
            
            accountInfo.result.value.coins.forEach({ (coin) in
                if (coin.denom == KAVA_MAIN_DENOM) {
                    dpBalance = NSDecimalNumber.zero
                    dpVesting = NSDecimalNumber.zero
                    originalVesting = NSDecimalNumber.zero
                    remainVesting = NSDecimalNumber.zero
                    delegatedVesting = NSDecimalNumber.zero
                    dpBalance = NSDecimalNumber.init(string: coin.amount)
                    
                    accountInfo.result.value.original_vesting.forEach({ (coin) in
                        if (coin.denom == KAVA_MAIN_DENOM) {
                            originalVesting = originalVesting.adding(NSDecimalNumber.init(string: coin.amount))
                        }
                    })
                    
                    accountInfo.result.value.delegated_vesting.forEach({ (coin) in
                        if (coin.denom == KAVA_MAIN_DENOM) {
                            delegatedVesting = delegatedVesting.adding(NSDecimalNumber.init(string: coin.amount))
                        }
                    })
                    
//                    if (SHOW_LOG) {
//                        print("Kava dpBalance            ", dpBalance)
//                        print("Kava originalVesting      ", originalVesting)
//                        print("Kava delegatedVesting     ", delegatedVesting)
//                    }
                    
                    remainVesting = accountInfo.result.getCalcurateVestingAmountSumByDenom(KAVA_MAIN_DENOM)
//                    if (SHOW_LOG) { print("Kava remainVesting            ", remainVesting)}
                    
                    dpVesting = remainVesting.subtracting(delegatedVesting);
//                    if (SHOW_LOG) { print("Kava dpVesting      ", dpVesting) }
                    
                    if (dpVesting.compare(NSDecimalNumber.zero).rawValue <= 0) {
                        dpVesting = NSDecimalNumber.zero;
                    }
//                    if (SHOW_LOG) { print("Kava dpVesting1      ", dpVesting) }
                    
                    if (remainVesting.compare(delegatedVesting).rawValue > 0) {
                        dpBalance = dpBalance.subtracting(remainVesting).adding(delegatedVesting);
                    }
//                    if (SHOW_LOG) { print("Kava dpBalance      ", dpBalance) }
                    
                    result.append(Balance.init(account.account_id, coin.denom, dpBalance.stringValue, Date().millisecondsSince1970, delegatedVesting.stringValue, dpVesting.stringValue))
                    
                } else if (coin.denom == KAVA_HARD_DENOM) {
                    dpBalance = NSDecimalNumber.zero
                    dpVesting = NSDecimalNumber.zero
                    originalVesting = NSDecimalNumber.zero
                    remainVesting = NSDecimalNumber.zero
                    delegatedVesting = NSDecimalNumber.zero
                    dpBalance = NSDecimalNumber.init(string: coin.amount)
                    
                    accountInfo.result.value.original_vesting.forEach({ (coin) in
                        if (coin.denom == KAVA_HARD_DENOM) {
                            originalVesting = originalVesting.adding(NSDecimalNumber.init(string: coin.amount))
                        }
                    })
                    
//                    if (SHOW_LOG) {
//                        print("Hard dpBalance            ", dpBalance)
//                        print("Hard originalVesting      ", originalVesting)
//                    }
                    
                    remainVesting = accountInfo.result.getCalcurateVestingAmountSumByDenom(KAVA_HARD_DENOM)
//                    if (SHOW_LOG) { print("Hard remainVesting   ", remainVesting)}
                    
                    dpBalance = dpBalance.subtracting(remainVesting)
//                    if (SHOW_LOG) { print("Hard dpBalance      ", dpBalance) }
                    
                    result.append(Balance.init(account.account_id, coin.denom, dpBalance.stringValue, Date().millisecondsSince1970, "0", remainVesting.stringValue))
                    
                } else {
                    result.append(Balance.init(account.account_id, coin.denom, coin.amount, Date().millisecondsSince1970))
                }

            })
        }
        return result;
    }
    
    static func getBalancesWithOkAccountInfo(_ account: Account, _ accountToken: OkAccountToken) -> Array<Balance> {
        var result = Array<Balance>()
        for okBalance in accountToken.data.currencies {
             result.append(Balance(account.account_id, okBalance.symbol, okBalance.available, Date().millisecondsSince1970, "0", okBalance.locked))
        }
        return result;
    }
    
    //vesting account paring with lcd
    static func getBalancesWithVestingAccountInfo(_ account: Account, _ accountInfo: VestingAccountInfo) -> Array<Balance> {
        var result = Array<Balance>()
        if (accountInfo.result?.type == COSMOS_AUTH_TYPE_ACCOUNT) {
            accountInfo.result?.value?.coins?.forEach({ (coin) in
                result.append(Balance.init(account.account_id, coin.denom, coin.amount, Date().millisecondsSince1970))
            })
            
        } else if (accountInfo.result?.type == COSMOS_AUTH_TYPE_P_VESTING_ACCOUNT) {
            accountInfo.result?.value?.coins?.forEach({ (coin) in
                var dpBalance = NSDecimalNumber.zero
                var dpVesting = NSDecimalNumber.zero
                var originalVesting = NSDecimalNumber.zero
                var remainVesting = NSDecimalNumber.zero
                var delegatedVesting = NSDecimalNumber.zero
                let denom = coin.denom
                
                dpBalance = NSDecimalNumber.init(string: coin.amount)
//                print("P_VESTING_ACCOUNT dpBalance", denom, " ", dpBalance)
                
                accountInfo.result?.value?.original_vesting?.forEach({ (coin) in
                    if (coin.denom == denom) {
                        originalVesting = originalVesting.adding(NSDecimalNumber.init(string: coin.amount))
                    }
                })
//                print("P_VESTING_ACCOUNT originalVesting", denom, " ", originalVesting)
                
                accountInfo.result?.value?.delegated_vesting?.forEach({ (coin) in
                    if (coin.denom == denom) {
                        delegatedVesting = delegatedVesting.adding(NSDecimalNumber.init(string: coin.amount))
                    }
                })
//                print("P_VESTING_ACCOUNT delegatedVesting", denom, " ", delegatedVesting)
                
                remainVesting = accountInfo.result?.getCalcurateVestingAmountSumByDenom(denom) ?? NSDecimalNumber.zero
//                print("P_VESTING_ACCOUNT remainVesting", denom, " ", remainVesting)
                
                dpVesting = remainVesting.subtracting(delegatedVesting);
//                print("P_VESTING_ACCOUNT dpVestingA", denom, " ", dpVesting)
                
                if (dpVesting.compare(NSDecimalNumber.zero).rawValue <= 0) {
                    dpVesting = NSDecimalNumber.zero;
                }
//                print("P_VESTING_ACCOUNT dpVestingB", denom, " ", dpVesting)
                
                if (remainVesting.compare(delegatedVesting).rawValue > 0) {
                    dpBalance = dpBalance.subtracting(remainVesting).adding(delegatedVesting);
                }
//                print("P_VESTING_ACCOUNT dpBalance", denom, " ", dpBalance)
                
                result.append(Balance.init(account.account_id, coin.denom, dpBalance.stringValue, Date().millisecondsSince1970, delegatedVesting.stringValue, dpVesting.stringValue))
                
            })
            
        } else if (accountInfo.result?.type == COSMOS_AUTH_TYPE_C_VESTING_ACCOUNT) {
            accountInfo.result?.value?.coins?.forEach({ (coin) in
                var dpBalance = NSDecimalNumber.zero
                var dpVesting = NSDecimalNumber.zero
                var originalVesting = NSDecimalNumber.zero
                var remainVesting = NSDecimalNumber.zero
                var delegatedVesting = NSDecimalNumber.zero
                let denom = coin.denom
                
                dpBalance = NSDecimalNumber.init(string: coin.amount)
//                print("C_VESTING_ACCOUNT dpBalance", denom, " ", dpBalance)
                
                accountInfo.result?.value?.original_vesting?.forEach({ (coin) in
                    if (coin.denom == denom) {
                        originalVesting = originalVesting.adding(NSDecimalNumber.init(string: coin.amount))
                    }
                })
//                print("C_VESTING_ACCOUNT originalVesting", denom, " ", originalVesting)
                
                accountInfo.result?.value?.delegated_vesting?.forEach({ (coin) in
                    if (coin.denom == denom) {
                        delegatedVesting = delegatedVesting.adding(NSDecimalNumber.init(string: coin.amount))
                    }
                })
//                print("C_VESTING_ACCOUNT delegatedVesting", denom, " ", delegatedVesting)
                
                let cTime = Date().millisecondsSince1970
                let vestingStart = accountInfo.result!.value!.getStartTime() * 1000
                let vestingEnd = accountInfo.result!.value!.getEndTime() * 1000
                if (cTime < vestingStart) {
                    remainVesting = originalVesting
                } else if (cTime > vestingEnd) {
                    remainVesting = NSDecimalNumber.zero
                } else if (cTime < vestingEnd) {
                    let progress = ((Float)(cTime - vestingStart)) / ((Float)(vestingEnd - vestingStart))
                    remainVesting = originalVesting.multiplying(by: NSDecimalNumber.init(value: 1 - progress), withBehavior: handler0Up)
                }
//                print("C_VESTING_ACCOUNT remainVesting ", denom, "  ", remainVesting)
                
                dpVesting = remainVesting.subtracting(delegatedVesting);
//                print("C_VESTING_ACCOUNT dpVestingA ", denom, "  ", dpVesting)
                
                dpVesting = dpVesting.compare(NSDecimalNumber.zero).rawValue <= 0 ? NSDecimalNumber.zero : dpVesting
//                print("C_VESTING_ACCOUNT dpVestingB ", denom, "  ", dpVesting)
                
                if (remainVesting.compare(delegatedVesting).rawValue > 0) {
                    dpBalance = dpBalance.subtracting(remainVesting).adding(delegatedVesting);
                }
//                print("C_VESTING_ACCOUNT final dpBalance ", denom, "  ", dpBalance)
                
                result.append(Balance.init(account.account_id, coin.denom, dpBalance.stringValue, Date().millisecondsSince1970, delegatedVesting.stringValue, dpVesting.stringValue))
            })
            
        } else if (accountInfo.result?.type == COSMOS_AUTH_TYPE_D_VESTING_ACCOUNT) {
            accountInfo.result?.value?.coins?.forEach({ (coin) in
                var dpBalance = NSDecimalNumber.zero
                var dpVesting = NSDecimalNumber.zero
                var originalVesting = NSDecimalNumber.zero
                var remainVesting = NSDecimalNumber.zero
                var delegatedVesting = NSDecimalNumber.zero
                let denom = coin.denom
                
                dpBalance = NSDecimalNumber.init(string: coin.amount)
//                print("D_VESTING_ACCOUNT dpBalance", denom, " ", dpBalance)
                
                accountInfo.result?.value?.original_vesting?.forEach({ (coin) in
                    if (coin.denom == denom) {
                        originalVesting = originalVesting.adding(NSDecimalNumber.init(string: coin.amount))
                    }
                })
//                print("D_VESTING_ACCOUNT originalVesting", denom, " ", originalVesting)
                
                accountInfo.result?.value?.delegated_vesting?.forEach({ (coin) in
                    if (coin.denom == denom) {
                        delegatedVesting = delegatedVesting.adding(NSDecimalNumber.init(string: coin.amount))
                    }
                })
//                print("D_VESTING_ACCOUNT delegatedVesting", denom, " ", delegatedVesting)
                
                let cTime = Date().millisecondsSince1970
                let vestingEnd = accountInfo.result!.value!.getEndTime() * 1000
                
                if (cTime < vestingEnd) {
                    remainVesting = originalVesting
                }
//                print("D_VESTING_ACCOUNT remainVesting ", denom, "  ", remainVesting)
                
                dpVesting = remainVesting.subtracting(delegatedVesting);
//                print("D_VESTING_ACCOUNT dpVestingA ", denom, "  ", dpVesting)
                
                dpVesting = dpVesting.compare(NSDecimalNumber.zero).rawValue <= 0 ? NSDecimalNumber.zero : dpVesting
//                print("D_VESTING_ACCOUNT dpVestingB ", denom, "  ", dpVesting)
                
                if (remainVesting.compare(delegatedVesting).rawValue > 0) {
                    dpBalance = dpBalance.subtracting(remainVesting).adding(delegatedVesting);
                }
//                print("D_VESTING_ACCOUNT final dpBalance ", denom, "  ", dpBalance)
                
                result.append(Balance.init(account.account_id, coin.denom, dpBalance.stringValue, Date().millisecondsSince1970, delegatedVesting.stringValue, dpVesting.stringValue))
            })
            
        }
        return result
    }
    
    
    static func nodeTimeToInt64(input: String) -> Date {
        let nodeFormatter = DateFormatter()
        nodeFormatter.dateFormat = "yyyy-MM-dd'T'HH:mm:ss.SSSSSSSSS'Z'"
        nodeFormatter.timeZone = NSTimeZone(name: "UTC") as TimeZone!
        return nodeFormatter.date(from: input) ?? Date.init()
    }
    
    static func nodeTimetoString(input: String?) -> String {
        if (input == nil) { return ""}
        let nodeFormatter = DateFormatter()
        nodeFormatter.dateFormat = "yyyy-MM-dd'T'HH:mm:ss.SSSSSSSSS'Z'"
        nodeFormatter.timeZone = NSTimeZone(name: "UTC") as TimeZone!
        
        let localFormatter = DateFormatter()
        localFormatter.dateFormat = NSLocalizedString("date_format", comment: "")
        
        if (input != nil) {
            let fullDate = nodeFormatter.date(from: input!)
            return localFormatter.string(from: fullDate!)
        } else {
            return ""
        }
    }
    
    static func txTimeToInt64(input: String) -> Date {
        let nodeFormatter = DateFormatter()
        nodeFormatter.dateFormat = "yyyy-MM-dd'T'HH:mm:ss'Z'"
        nodeFormatter.timeZone = NSTimeZone(name: "UTC") as TimeZone!
        return nodeFormatter.date(from: input) ?? Date.init()
    }
    
    static func txTimetoString(input: String?) -> String {
        if (input == nil || input!.count == 0) {
            return "??"
        }
        let nodeFormatter = DateFormatter()
        nodeFormatter.dateFormat = "yyyy-MM-dd'T'HH:mm:ss'Z'"
        nodeFormatter.timeZone = NSTimeZone(name: "UTC") as TimeZone!
        
        let localFormatter = DateFormatter()
        localFormatter.dateFormat = NSLocalizedString("date_format", comment: "")
        if (input != nil) {
            let fullDate = nodeFormatter.date(from: input!)
            return localFormatter.string(from: fullDate!)
        } else {
            return ""
        }
    }
    
    static func txTimetoShortString(input: String?) -> String {
        let nodeFormatter = DateFormatter()
        nodeFormatter.dateFormat = "yyyy-MM-dd'T'HH:mm:ss'Z'"
        nodeFormatter.timeZone = NSTimeZone(name: "UTC") as TimeZone!
        
        let localFormatter = DateFormatter()
        localFormatter.dateFormat = NSLocalizedString("date_format2", comment: "")
        if (input != nil) {
            let fullDate = nodeFormatter.date(from: input!)
            return localFormatter.string(from: fullDate!)
        } else {
            return ""
        }
    }
    
    
    static func longTimetoString(input: Int64) -> String {
        let localFormatter = DateFormatter()
        localFormatter.dateFormat = NSLocalizedString("date_format", comment: "")
        
        let fullDate = Date.init(milliseconds: Int(input))
        return localFormatter.string(from: fullDate)
    }
    
    static func vestingTimeToString(_ startTime:Int64, _ vesting: KavaAccountInfo.VestingPeriod) -> String {
        let localFormatter = DateFormatter()
        localFormatter.dateFormat = NSLocalizedString("date_format", comment: "")
        let unlockTime = (startTime + vesting.length) * 1000
        
        let fullDate = Date.init(milliseconds: Int(unlockTime))
        return localFormatter.string(from: fullDate)
    }
    
    static func unbondingDateFromNow(_ date:Int) -> String {
        let localFormatter = DateFormatter()
        localFormatter.dateFormat = NSLocalizedString("date_format", comment: "")
        
        let afterDate = Calendar.current.date(
            byAdding: .day,
            value: +date,
            to: Date())
        return localFormatter.string(from: afterDate!)
    }
    
    static func getUnbondingTimeleft(_ input: Int64) -> String {
        let secondsLeft = Int(Date().timeIntervalSince(Date.init(milliseconds: Int(input)))) * -1
        
        let minute = 60
        let hour = 60 * minute
        let day = 24 * hour
        if secondsLeft < minute {
            return "Soon"
        } else if secondsLeft < hour {
            return "(\(secondsLeft / minute) minutes remaining)"
        } else if secondsLeft < day {
            return "(\(secondsLeft / hour) hours remaining)"
        } else {
            return "(\(secondsLeft / day) days remaining)"
        }
    }
    
    static func timeGap(input: String?) -> String {
        if (input == nil) { return "-"}
        let secondsAgo = Int(Date().timeIntervalSince(nodeTimeToInt64(input: input!)))
        
        let minute = 60
        let hour = 60 * minute
        let day = 24 * hour
        
        if secondsAgo < minute {
            return "(\(secondsAgo) seconds ago)"
        } else if secondsAgo < hour {
            return "(\(secondsAgo / minute) minutes ago)"
        } else if secondsAgo < day {
            return "(\(secondsAgo / hour) hours ago)"
        } else {
            return "(\(secondsAgo / day) days ago)"
        }
    }
    
    static func timeGap2(input: Int64) -> String {
        if (input == nil) { return "-"}
        let secondsAgo = (Int(Date().millisecondsSince1970 - input) / 1000)
        
        let minute = 60
        let hour = 60 * minute
        let day = 24 * hour
        
        if secondsAgo < minute {
            return "(\(secondsAgo) seconds ago)"
        } else if secondsAgo < hour {
            return "(\(secondsAgo / minute) minutes ago)"
        } else if secondsAgo < day {
            return "(\(secondsAgo / hour) hours ago)"
        } else {
            return "(\(secondsAgo / day) days ago)"
        }
    }
    
    static func txTimeGap(input: String?) -> String {
        if (input == nil || input!.count == 0) { return "??" }
        let secondsAgo = Int(Date().timeIntervalSince(txTimeToInt64(input: input!)))
        
        let minute = 60
        let hour = 60 * minute
        let day = 24 * hour
        
        if secondsAgo < minute {
            return "(\(secondsAgo) seconds ago)"
        } else if secondsAgo < hour {
            return "(\(secondsAgo / minute) minutes ago)"
        } else if secondsAgo < day {
            return "(\(secondsAgo / hour) hours ago)"
        } else {
            return "(\(secondsAgo / day) days ago)"
        }
    }
    
    static func vestingTimeGap(_ startTime:Int64, _ vesting: KavaAccountInfo.VestingPeriod) -> String {
        let unlockTime = (startTime + vesting.length) * 1000
        let secondsLeft = Int(Date().timeIntervalSince(Date.init(milliseconds: Int(unlockTime)))) * -1
        
        let minute = 60
        let hour = 60 * minute
        let day = 24 * hour
        if secondsLeft < minute {
            return "Soon"
        } else if secondsLeft < hour {
            return "(\(secondsLeft / minute) minutes remaining)"
        } else if secondsLeft < day {
            return "(\(secondsLeft / hour) hours remaining)"
        } else {
            return "(\(secondsLeft / day) days remaining)"
        }
    }
    
    
    
    static func historyTitle(_ msgs:Array<Msg>, _ myaddress:String) -> String {
        var resultMsg = NSLocalizedString("tx_known", comment: "")

        if (msgs == nil || msgs.count <= 0) {
            return resultMsg
        }
        
        if(msgs.count == 2) {
            if (msgs[0].type == COSMOS_MSG_TYPE_WITHDRAW_DEL && msgs[1].type == COSMOS_MSG_TYPE_DELEGATE) {
                resultMsg = NSLocalizedString("tx_reinvest", comment: "")
                return resultMsg
            }
            if (msgs[0].type == IRIS_MSG_TYPE_WITHDRAW && msgs[1].type == IRIS_MSG_TYPE_DELEGATE) {
                resultMsg = NSLocalizedString("tx_reinvest", comment: "")
                return resultMsg
            }
        }
        
        if (msgs[0].type == COSMOS_MSG_TYPE_TRANSFER || msgs[0].type == COSMOS_MSG_TYPE_TRANSFER2 || msgs[0].type == CERTIK_MSG_TYPE_TRANSFER) {
            if (msgs[0].value.from_address != nil && msgs[0].value.from_address == myaddress) {
                resultMsg = NSLocalizedString("tx_send", comment: "")
            } else if (msgs[0].value.to_address != nil && msgs[0].value.to_address == myaddress) {
                resultMsg = NSLocalizedString("tx_receive", comment: "")
            } else {
                if (msgs[0].value.inputs != nil && msgs[0].value.inputs!.count > 0) {
                    for input in msgs[0].value.inputs! {
                        if (input.address == myaddress) {
                            resultMsg = NSLocalizedString("tx_send", comment: "")
                            return resultMsg
                        }
                    }
                }
                if (msgs[0].value.outputs != nil && msgs[0].value.outputs!.count > 0) {
                    for input in msgs[0].value.outputs! {
                        if (input.address == myaddress) {
                            resultMsg = NSLocalizedString("tx_receive", comment: "")
                            return resultMsg
                        }
                    }
                }
                resultMsg = NSLocalizedString("tx_transfer", comment: "")
            }
            
        } else if (msgs[0].type == COSMOS_MSG_TYPE_DELEGATE || msgs[0].type == IRIS_MSG_TYPE_DELEGATE ) {
            resultMsg = NSLocalizedString("tx_delegate", comment: "")
            
        } else if (msgs[0].type == COSMOS_MSG_TYPE_UNDELEGATE || msgs[0].type == COSMOS_MSG_TYPE_UNDELEGATE2 || msgs[0].type == IRIS_MSG_TYPE_UNDELEGATE) {
            resultMsg = NSLocalizedString("tx_undelegate", comment: "")
            
        } else if (msgs[0].type == COSMOS_MSG_TYPE_REDELEGATE || msgs[0].type == COSMOS_MSG_TYPE_REDELEGATE2 || msgs[0].type == IRIS_MSG_TYPE_REDELEGATE) {
            resultMsg = NSLocalizedString("tx_redelegate", comment: "")
            
        } else if (msgs[0].type == COSMOS_MSG_TYPE_WITHDRAW_DEL || msgs[0].type == IRIS_MSG_TYPE_WITHDRAW) {
            resultMsg = NSLocalizedString("tx_get_reward", comment: "")
            
        } else if (msgs[0].type == COSMOS_MSG_TYPE_WITHDRAW_VAL) {
            resultMsg = NSLocalizedString("tx_get_commission", comment: "")
            
        } else if (msgs[0].type == COSMOS_MSG_TYPE_WITHDRAW_MIDIFY || msgs[0].type == IRIS_MSG_TYPE_WITHDRAW_MIDIFY) {
            resultMsg = NSLocalizedString("tx_change_reward_address", comment: "")
            
        } else if (msgs[0].type == COSMOS_MSG_TYPE_VOTE || msgs[0].type == IRIS_MSG_TYPE_VOTE) {
            resultMsg = NSLocalizedString("tx_vote", comment: "")
            
        } else if (msgs[0].type == COSMOS_MSG_TYPE_SUBMIT_PROPOSAL || msgs[0].type == IRIS_MSG_TYPE_SUBMIT_PROPOSAL) {
            resultMsg = NSLocalizedString("tx_submit_proposal", comment: "")
            
        } else if (msgs[0].type == COSMOS_MSG_TYPE_DEPOSIT || msgs[0].type == IRIS_MSG_TYPE_DEPOSIT) {
            resultMsg = NSLocalizedString("tx_deposit", comment: "")
            
        } else if (msgs[0].type == COSMOS_MSG_TYPE_CREATE_VALIDATOR || msgs[0].type == IRIS_MSG_TYPE_CREATE_VALIDATOR) {
            resultMsg = NSLocalizedString("tx_create_validator", comment: "")
            
        } else if (msgs[0].type == COSMOS_MSG_TYPE_EDIT_VALIDATOR) {
            resultMsg = NSLocalizedString("tx_edit_validator", comment: "")
            
        } else if (msgs[0].type == IRIS_MSG_TYPE_WITHDRAW_ALL) {
            resultMsg = NSLocalizedString("tx_get_reward_all", comment: "")
            
        } else if (msgs[0].type == IRIS_MSG_TYPE_ISSUE_TOKEN) {
            resultMsg = NSLocalizedString("tx_issue_token", comment: "")
            
        } else if (msgs[0].type == COSMOS_MSG_TYPE_TRANSFER3) {
            resultMsg = NSLocalizedString("tx_transfer", comment: "")
            
        } else if (msgs[0].type == KAVA_MSG_TYPE_POST_PRICE) {
            resultMsg = NSLocalizedString("tx_kava_post_price", comment: "")
            
        } else if (msgs[0].type == KAVA_MSG_TYPE_CREATE_CDP) {
            resultMsg = NSLocalizedString("tx_kava_create_cdp", comment: "")
            
        } else if (msgs[0].type == KAVA_MSG_TYPE_DEPOSIT_CDP) {
            resultMsg = NSLocalizedString("tx_kava_deposit_cdp", comment: "")
            
        } else if (msgs[0].type == KAVA_MSG_TYPE_WITHDRAW_CDP) {
            resultMsg = NSLocalizedString("tx_kava_withdraw_cdp", comment: "")
            
        } else if (msgs[0].type == KAVA_MSG_TYPE_DRAWDEBT_CDP) {
            resultMsg = NSLocalizedString("tx_kava_drawdebt_cdp", comment: "")
            
        } else if (msgs[0].type == KAVA_MSG_TYPE_REPAYDEBT_CDP) {
            resultMsg = NSLocalizedString("tx_kava_repaydebt_cdp", comment: "")
            
        } else if (msgs[0].type == KAVA_MSG_TYPE_LIQUIDATE_CDP) {
            resultMsg = NSLocalizedString("tx_kava_liquidate_cdp", comment: "")
            
        } else if (msgs[0].type == KAVA_MSG_TYPE_CREATE_SWAP) {
            resultMsg = NSLocalizedString("tx_kava_bep3_create", comment: "")
            
        } else if (msgs[0].type == KAVA_MSG_TYPE_CLAIM_SWAP) {
            resultMsg = NSLocalizedString("tx_kava_bep3_claim", comment: "")
            
        } else if (msgs[0].type == KAVA_MSG_TYPE_REFUND_SWAP) {
            resultMsg = NSLocalizedString("tx_kava_bep3_refund", comment: "")
            
        } else if (msgs[0].type == KAVA_MSG_TYPE_DEPOSIT_HAVEST || msgs[0].type == KAVA_MSG_TYPE_DEPOSIT_HARD) {
            resultMsg = NSLocalizedString("tx_kava_hard_deposit", comment: "")
            
        } else if (msgs[0].type == KAVA_MSG_TYPE_WITHDRAW_HAVEST || msgs[0].type == KAVA_MSG_TYPE_WITHDRAW_HARD) {
            resultMsg = NSLocalizedString("tx_kava_hard_withdraw", comment: "")
            
        }else if (msgs[0].type == KAVA_MSG_TYPE_BORROW_HARD) {
            resultMsg = NSLocalizedString("tx_kava_hard_borrow", comment: "")
            
        } else if (msgs[0].type == KAVA_MSG_TYPE_REPAY_HARD) {
            resultMsg = NSLocalizedString("tx_kava_hard_repay", comment: "")
            
        } else if (msgs[0].type == KAVA_MSG_TYPE_LIQUIDATE_HARD) {
            resultMsg = NSLocalizedString("tx_kava_hard_liquidate", comment: "")
            
        } else if (msgs[0].type == KAVA_MSG_TYPE_CLAIM_HAVEST || msgs[0].type == KAVA_MSG_TYPE_CLAIM_HARD_INCENTIVE) {
            resultMsg = NSLocalizedString("tx_kava_hard_hard_incentive", comment: "")
            
        } else if (msgs[0].type == KAVA_MSG_TYPE_INCENTIVE_REWARD || msgs[0].type == KAVA_MSG_TYPE_USDX_MINT_INCENTIVE) {
            resultMsg = NSLocalizedString("tx_kava_hard_mint_incentive", comment: "")
            
        }
        
        else if (msgs[0].type == IOV_MSG_TYPE_REGISTER_DOMAIN) {
            resultMsg = NSLocalizedString("tx_starname_registe_domain", comment: "")
            
        } else if (msgs[0].type == IOV_MSG_TYPE_REGISTER_ACCOUNT) {
            resultMsg = NSLocalizedString("tx_starname_registe_account", comment: "")
            
        } else if (msgs[0].type == IOV_MSG_TYPE_DELETE_DOMAIN) {
            resultMsg = NSLocalizedString("tx_starname_delete_domain", comment: "")
            
        } else if (msgs[0].type == IOV_MSG_TYPE_DELETE_ACCOUNT) {
            resultMsg = NSLocalizedString("tx_starname_delete_account", comment: "")
            
        } else if (msgs[0].type == IOV_MSG_TYPE_RENEW_DOMAIN) {
            resultMsg = NSLocalizedString("tx_starname_renew_domain", comment: "")
            
        } else if (msgs[0].type == IOV_MSG_TYPE_RENEW_ACCOUNT) {
            resultMsg = NSLocalizedString("tx_starname_renew_account", comment: "")
            
        } else if (msgs[0].type == IOV_MSG_TYPE_REPLACE_ACCOUNT_RESOURCE) {
            resultMsg = NSLocalizedString("tx_starname_update_resource", comment: "")
            
        }
        
        if(msgs.count > 1) {
            resultMsg = resultMsg +  "\n+ " + String(msgs.count - 1)
        }
        return resultMsg
    }
    
    static func bnbHistoryTitle(_ bnbHistory:BnbHistory, _ myaddress:String) -> String {
        var resultMsg = NSLocalizedString("tx_known", comment: "")
        if (bnbHistory.txType == "NEW_ORDER") {
            resultMsg = NSLocalizedString("tx_new_order", comment: "")
            
        } else if (bnbHistory.txType == "CANCEL_ORDER") {
            resultMsg = NSLocalizedString("tx_cancel_order", comment: "")
            
        } else if (bnbHistory.txType == "TRANSFER") {
            if (bnbHistory.fromAddr == myaddress) {
                resultMsg = NSLocalizedString("tx_send", comment: "")
            } else {
                resultMsg = NSLocalizedString("tx_receive", comment: "")
            }
        } else if (bnbHistory.txType == "HTL_TRANSFER") {
            if (bnbHistory.fromAddr == myaddress) {
                resultMsg = NSLocalizedString("tx_send_htlc", comment: "")
            } else if (bnbHistory.toAddr == myaddress) {
                resultMsg = NSLocalizedString("tx_receive_htlc", comment: "")
            } else {
                resultMsg = NSLocalizedString("tx_create_htlc", comment: "")
            }
            
        } else if (bnbHistory.txType == "CLAIM_HTL") {
            resultMsg = NSLocalizedString("tx_claim_htlc", comment: "")
            
        } else if (bnbHistory.txType == "REFUND_HTL") {
            resultMsg = NSLocalizedString("tx_refund_htlc", comment: "")
        }
        return resultMsg
    }
    
    static func okHistoryTitle(_ okHistory: OkHistory.DataDetail) -> String {
        var resultMsg = NSLocalizedString("tx_known", comment: "")
        if (okHistory.type! == OK_TX_TYPE_TRANSFER) {
            if (okHistory.side! == OK_TX_TYPE_SIDE_SEND) {
                resultMsg = NSLocalizedString("tx_send", comment: "")
            } else {
                resultMsg = NSLocalizedString("tx_receive", comment: "")
            }
        } else if (okHistory.type! == OK_TX_TYPE_TRANSFER) {
            resultMsg = NSLocalizedString("tx_new_order", comment: "")
            
        } else if (okHistory.type! == OK_TX_TYPE_CANCEL_ORDER) {
            resultMsg = NSLocalizedString("tx_cancel_order", comment: "")
        }
        return resultMsg
    }
    
    
    static func proposalType(_ type:String) -> String {
        if (type == IRIS_PROPOAL_TYPE_BasicProposal) {
            return NSLocalizedString("proposal_type_basic", comment: "")
            
        } else if (type == IRIS_PROPOAL_TYPE_ParameterProposal) {
            return NSLocalizedString("proposal_type_parameter", comment: "")
            
        } else if (type == IRIS_PROPOAL_TYPE_PlainTextProposal) {
            return NSLocalizedString("proposal_type_plaintext", comment: "")
                   
        } else if (type == IRIS_PROPOAL_TYPE_TokenAdditionProposal) {
            return NSLocalizedString("proposal_type_tokenaddition", comment: "")
                  
        } else if (type == IRIS_PROPOAL_TYPE_SoftwareUpgradeProposal) {
            return NSLocalizedString("proposal_type_softwareupgrade", comment: "")
                 
        } else if (type == IRIS_PROPOAL_TYPE_SystemHaltProposal) {
            return NSLocalizedString("proposal_type_systemhalt", comment: "")
                
        } else if (type == IRIS_PROPOAL_TYPE_CommunityTaxUsageProposal) {
            return NSLocalizedString("proposal_type_communitytaxusage", comment: "")
               
        }
        return ""
    }
    
    
    static func checkNAN(_ check: NSDecimalNumber) -> NSDecimalNumber{
        if(check.isEqual(to: NSDecimalNumber.notANumber)) {
            return NSDecimalNumber.zero
        }
        return check
    }
    
    static func decimalNumberToLocaleString(_ input: NSDecimalNumber, _ deciaml:Int16) -> String {
        let nf = NumberFormatter()
        nf.minimumFractionDigits = 0
        nf.maximumFractionDigits = Int(deciaml)
        nf.numberStyle = .decimal
        nf.locale = Locale.current
        nf.groupingSeparator = ""
        return nf.string(from: input)!
    }
    
    static func localeStringToDecimal(_ input: String?) -> NSDecimalNumber {
        let result = NSDecimalNumber(string: input, locale: Locale.current)
        if (NSDecimalNumber.notANumber == result) {
            return NSDecimalNumber.zero
        } else {
            return result
        }
    }
    
    static func plainStringToDecimal(_ input: String?) -> NSDecimalNumber {
        if (input == nil) { return NSDecimalNumber.zero }
        let result = NSDecimalNumber(string: input)
        if (NSDecimalNumber.notANumber == result) {
            return NSDecimalNumber.zero
        } else {
            return result
        }
    }
    
    static func displayAmount(_ amount: String?, _ font:UIFont, _ deciaml:Int, _ chain:ChainType) -> NSMutableAttributedString {
        let nf = NumberFormatter()
        nf.minimumFractionDigits = deciaml
        nf.maximumFractionDigits = deciaml
        nf.numberStyle = .decimal

        let amount = localeStringToDecimal(amount)
        let handler = NSDecimalNumberHandler(roundingMode: NSDecimalNumber.RoundingMode.down, scale: Int16(deciaml), raiseOnExactness: false, raiseOnOverflow: false, raiseOnUnderflow: false, raiseOnDivideByZero: true)

        var formatted: String?
        if (amount == NSDecimalNumber.zero) {
            formatted = nf.string(from: NSDecimalNumber.zero)
        } else if (chain == ChainType.COSMOS_MAIN || chain == ChainType.KAVA_MAIN || chain == ChainType.KAVA_TEST ||
                    chain == ChainType.BAND_MAIN || chain == ChainType.SECRET_MAIN || chain == ChainType.CERTIK_MAIN ||
                    chain == ChainType.IOV_MAIN || chain == ChainType.IOV_TEST || chain == ChainType.CERTIK_TEST ||
                    chain == ChainType.AKASH_MAIN || chain == ChainType.COSMOS_TEST || chain == ChainType.IRIS_TEST || chain == ChainType.PERSIS_MAIN || chain == ChainType.SENTINEL_MAIN) {
            formatted = nf.string(from: amount.dividing(by: 1000000).rounding(accordingToBehavior: handler))
        } else if (chain == ChainType.BINANCE_MAIN || chain == ChainType.BINANCE_TEST) {
            formatted = nf.string(from: amount.rounding(accordingToBehavior: handler))
        }

        let added       = formatted
        let endIndex    = added!.index(added!.endIndex, offsetBy: -deciaml)

        let preString   = added![..<endIndex]
        let postString  = added![endIndex...]

        let preAttrs = [NSAttributedString.Key.font : font]
        let postAttrs = [NSAttributedString.Key.font : font.withSize(CGFloat(Int(Double(font.pointSize) * 0.85)))]

        let attributedString1 = NSMutableAttributedString(string:String(preString), attributes:preAttrs as [NSAttributedString.Key : Any])
        let attributedString2 = NSMutableAttributedString(string:String(postString), attributes:postAttrs as [NSAttributedString.Key : Any])

        attributedString1.append(attributedString2)
        return attributedString1
    }
    
    static func displayAmount2(_ amount: String?, _ font:UIFont, _ inputPoint:Int16, _ dpPoint:Int16) -> NSMutableAttributedString {
        let nf = NumberFormatter()
        nf.minimumFractionDigits = Int(dpPoint)
        nf.maximumFractionDigits = Int(dpPoint)
        nf.numberStyle = .decimal
        
        let amount = plainStringToDecimal(amount)
        let handler = NSDecimalNumberHandler(roundingMode: NSDecimalNumber.RoundingMode.down, scale: Int16(dpPoint), raiseOnExactness: false, raiseOnOverflow: false, raiseOnUnderflow: false, raiseOnDivideByZero: true)
        
        var formatted: String?
        if (amount == NSDecimalNumber.zero) {
            formatted = nf.string(from: NSDecimalNumber.zero)
        } else {
            formatted = nf.string(from: amount.multiplying(byPowerOf10: -Int16(inputPoint), withBehavior: handler))
        }
        
        let added       = formatted
        let endIndex    = added!.index(added!.endIndex, offsetBy: -dpPoint)
        
        let preString   = added![..<endIndex]
        let postString  = added![endIndex...]
        
        let preAttrs = [NSAttributedString.Key.font : font]
        let postAttrs = [NSAttributedString.Key.font : font.withSize(CGFloat(Int(Double(font.pointSize) * 0.85)))]
        
        let attributedString1 = NSMutableAttributedString(string:String(preString), attributes:preAttrs as [NSAttributedString.Key : Any])
        let attributedString2 = NSMutableAttributedString(string:String(postString), attributes:postAttrs as [NSAttributedString.Key : Any])
        
        attributedString1.append(attributedString2)
        return attributedString1
    }
    
    static func getFormattedNumber(_ amount: NSDecimalNumber, _ dpPoint:Int16) -> String {
        let nf = NumberFormatter()
        nf.minimumFractionDigits = Int(dpPoint)
        nf.locale = Locale(identifier: "en_US")
        nf.numberStyle = .decimal
        
        let formatted = nf.string(from: amount)?.replacingOccurrences(of: ",", with: "" )
        return formatted!
    }
    
    static func dpTokenAvailable(_ balances:Array<Balance>, _ font:UIFont, _ deciaml:Int, _ symbol:String, _ chain:ChainType) -> NSMutableAttributedString {
        var amount = NSDecimalNumber.zero
        for balance in balances {
            if (balance.balance_denom == symbol) {
                amount = plainStringToDecimal(balance.balance_amount)
            }
        }
        return displayAmount(amount.stringValue, font, deciaml, chain);
    }
    
    static func availableAmount(_ balances:Array<Balance>, _ symbol:String) -> NSDecimalNumber {
        var amount = NSDecimalNumber.zero
        for balance in balances {
            if (balance.balance_denom == symbol) {
                amount = plainStringToDecimal(balance.balance_amount)
            }
        }
        return amount;
    }
    
    static func lockedAmount(_ balances:Array<Balance>, _ symbol:String) -> NSDecimalNumber {
        var amount = NSDecimalNumber.zero
        for balance in balances {
            if (balance.balance_denom == symbol) {
                amount = plainStringToDecimal(balance.balance_locked)
            }
        }
        return amount;
    }
    
    static func okDepositAmount(_ deposit: OkStaking?) -> NSDecimalNumber {
        return plainStringToDecimal(deposit?.tokens)
    }
    
    static func okWithdrawAmount(_ withdraw: OkUnbonding?) -> NSDecimalNumber {
        return plainStringToDecimal(withdraw?.quantity)
    }
    
    static func dpVestingCoin(_ balances:Array<Balance>, _ font:UIFont, _ deciaml:Int, _ symbol:String, _ chain:ChainType) -> NSMutableAttributedString {
        var amount = NSDecimalNumber.zero
        for balance in balances {
            if (balance.balance_denom == symbol) {
                amount = plainStringToDecimal(balance.balance_locked)
            }
        }
        return displayAmount(amount.stringValue, font, deciaml, chain);
    }
    
    //price displaying
    static func tokenCnt(_ chainType: ChainType?) -> String {
        if (isGRPC(chainType)) {
            return String(BaseData.instance.mMyBalances_gRPC.count)
        } else {
            return String(BaseData.instance.mBalances.count)
        }
    }
    
    static func valueChange(_ denom: String) -> NSDecimalNumber {
        let baseData = BaseData.instance
        guard let coinPrice = baseData.getPrice(denom) else {
            return NSDecimalNumber.zero.rounding(accordingToBehavior: handler2Down)
        }
        return coinPrice.priceChange()
    }
    
    static func dpValueChange(_ denom: String, font:UIFont ) -> NSMutableAttributedString {
        let nf = getNumberFormatter(2)
        let formatted = nf.string(from: valueChange(denom))! + "% (24h)"
        return getDpAttributedString(formatted, 9, font)
    }
    
    static func perUsdValue(_ denom: String) -> NSDecimalNumber? {
        if let coinPrice = BaseData.instance.getPrice(denom) {
            return coinPrice.currencyPrice("usd").rounding(accordingToBehavior: handler3Down)
        }
        return nil
    }
    
    static func usdValue(_ denom: String, _ amount: NSDecimalNumber, _ divider: Int16) -> NSDecimalNumber {
        if let perUsdValue = perUsdValue(denom) {
            return perUsdValue.multiplying(by: amount).multiplying(byPowerOf10: -divider, withBehavior: handler3Down)
        }
        return NSDecimalNumber.zero
    }
    
    static func perUserCurrencyValue(_ denom: String) -> NSDecimalNumber {
        let baseData = BaseData.instance
        guard let perUsdValue = perUsdValue(denom), let usdtPrice = baseData.getPrice("usdt") else {
            return NSDecimalNumber.zero.rounding(accordingToBehavior: handler3Down)
        }
        if (baseData.getCurrency() == 0) {
            return perUsdValue
        } else {
            let priceUSDT = usdtPrice.currencyPrice(baseData.getCurrencyString().lowercased())
            return perUsdValue.multiplying(by: priceUSDT, withBehavior: handler3Down)
        }
    }
    
    static func perBtcValue(_ denom: String) -> NSDecimalNumber {
        let baseData = BaseData.instance
        guard let perUsdValue = perUsdValue(denom), let usdtPrice = baseData.getPrice("usdt") else {
            return NSDecimalNumber.zero.rounding(accordingToBehavior: handler8)
        }
        let priceUSDT = usdtPrice.currencyPrice("btc")
        return perUsdValue.multiplying(by: priceUSDT, withBehavior: handler8)
    }
    
    
    static func dpPerUserCurrencyValue(_ denom: String, _ font:UIFont) -> NSMutableAttributedString {
        let nf = getNumberFormatter(3)
        let formatted = BaseData.instance.getCurrencySymbol() + " " + nf.string(from: perUserCurrencyValue(denom))!
        return getDpAttributedString(formatted, 3, font)
    }
    
    static func userCurrencyValue(_ denom: String, _ amount: NSDecimalNumber, _ divider: Int16) -> NSDecimalNumber {
        return perUserCurrencyValue(denom).multiplying(by: amount).multiplying(byPowerOf10: -divider, withBehavior: handler3Down)
    }
    
    static func dpUserCurrencyValue(_ denom: String, _ amount: NSDecimalNumber, _ divider: Int16, _ font:UIFont) -> NSMutableAttributedString {
        let nf = getNumberFormatter(3)
        let formatted = BaseData.instance.getCurrencySymbol() + " " + nf.string(from: userCurrencyValue(denom, amount, divider))!
        return getDpAttributedString(formatted, 3, font)
    }
    
    static func btcValue(_ denom: String, _ amount: NSDecimalNumber, _ divider: Int16) -> NSDecimalNumber {
        return perBtcValue(denom).multiplying(by: amount).multiplying(byPowerOf10: -divider, withBehavior: handler8)
    }
    
//    static func allAssetToUsd(_ chainType: ChainType?) -> NSDecimalNumber {
//        let baseData = BaseData.instance
//        var totalUsdValue = NSDecimalNumber.zero
//        if (isGRPC(chainType)) {
//            baseData.mMyBalances_gRPC.forEach { coin in
//                if (coin.denom == getMainDenom(chainType)) {
//                    let amount = getAllMainAsset(coin.denom)
//                    let assetValue = usdValue(coin.denom, amount, 6)
//                    totalUsdValue = totalUsdValue.adding(assetValue)
//
//                } else {
//                    // not yet!
//
//                }
//            }
//        }
//        return totalUsdValue
//    }
    
    static func allAssetToUserCurrency(_ chainType: ChainType?) -> NSDecimalNumber {
        let baseData = BaseData.instance
        var totalValue = NSDecimalNumber.zero
        if (isGRPC(chainType)) {
            baseData.mMyBalances_gRPC.forEach { coin in
                if (coin.denom == getMainDenom(chainType)) {
                    let amount = getAllMainAsset(coin.denom)
                    let assetValue = userCurrencyValue(coin.denom, amount, 6)
                    totalValue = totalValue.adding(assetValue)
                } else {
                    // not yet!
                    
                }
            }
        }
        else if (chainType! == ChainType.BINANCE_MAIN || chainType! == ChainType.BINANCE_TEST) {
            baseData.mBalances.forEach { coin in
                var allBnb = NSDecimalNumber.zero
                let amount = WUtils.getAllBnbToken(coin.balance_denom)
                if (coin.balance_denom == getMainDenom(chainType)) {
                    allBnb = allBnb.adding(amount)
                } else {
                    allBnb = allBnb.adding(getBnbConvertAmount(coin.balance_denom, amount))
                }
                let assetValue = userCurrencyValue(getMainDenom(chainType), allBnb, 0)
                totalValue = totalValue.adding(assetValue)
            }
            
        } else if (chainType! == ChainType.KAVA_MAIN || chainType! == ChainType.KAVA_TEST) {
            baseData.mBalances.forEach { coin in
                var allKava = NSDecimalNumber.zero
                if (coin.balance_denom == getMainDenom(chainType)) {
                    allKava = allKava.adding(getAllMainAssetOld(getMainDenom(chainType)))
                } else {
                    allKava = allKava.adding(convertTokenToKava(coin.balance_denom))
                }
                let assetValue = userCurrencyValue(getMainDenom(chainType), allKava, 6)
                totalValue = totalValue.adding(assetValue)
            }
            
        } else if (chainType! == ChainType.OKEX_MAIN || chainType! == ChainType.OKEX_TEST) {
            baseData.mBalances.forEach { coin in
                var allOKT = NSDecimalNumber.zero
                if (coin.balance_denom == getMainDenom(chainType)) {
                    allOKT = allOKT.adding(getAllExToken(coin.balance_denom))
                } else {
                    allOKT = allOKT.adding(convertTokenToOkt(coin.balance_denom))
                }
                let assetValue = userCurrencyValue(getMainDenom(chainType), allOKT, 0)
                totalValue = totalValue.adding(assetValue)
            }
            
        } else if (chainType! == ChainType.SIF_MAIN) {
            baseData.mBalances.forEach { coin in
                if (coin.balance_denom == getMainDenom(chainType)) {
                    let amount = getAllMainAssetOld(getMainDenom(chainType))
                    totalValue = totalValue.adding(userCurrencyValue(coin.balance_denom, amount, 18))
                } else {
                    let available = baseData.availableAmount(coin.balance_denom)
                    let decimal = getSifCoinDecimal(coin.balance_denom)
                    totalValue = totalValue.adding(userCurrencyValue(coin.balance_denom.substring(from: 1), available, decimal))
                }
            }
        }
        
        else {
            baseData.mBalances.forEach { coin in
                if (coin.balance_denom == getMainDenom(chainType)) {
                    let amount = getAllMainAssetOld(getMainDenom(chainType))
                    totalValue = totalValue.adding(userCurrencyValue(coin.balance_denom, amount, mainDivideDecimal(chainType)))
                }
            }
        }
        return totalValue
    }
    
    static func allAssetToBtc(_ chainType: ChainType?) -> NSDecimalNumber {
        let baseData = BaseData.instance
        var totalValue = NSDecimalNumber.zero
        if (isGRPC(chainType)) {
            baseData.mMyBalances_gRPC.forEach { coin in
                if (coin.denom == getMainDenom(chainType)) {
                    let amount = getAllMainAsset(coin.denom)
                    let btcValue = btcValue(coin.denom, amount, 6)
                    totalValue = totalValue.adding(btcValue)
                    
                } else {
                    // not yet!
                    
                }
            }
        }
        else if (chainType! == ChainType.BINANCE_MAIN || chainType! == ChainType.BINANCE_TEST) {
            baseData.mBalances.forEach { coin in
                var allBnb = NSDecimalNumber.zero
                let amount = WUtils.getAllBnbToken(coin.balance_denom)
                if (coin.balance_denom == getMainDenom(chainType)) {
                    allBnb = allBnb.adding(amount)
                } else {
                    allBnb = allBnb.adding(getBnbConvertAmount(coin.balance_denom, amount))
                }
                let btcValue = btcValue(getMainDenom(chainType), allBnb, 0)
                totalValue = totalValue.adding(btcValue)
            }
            
        } else if (chainType! == ChainType.KAVA_MAIN || chainType! == ChainType.KAVA_TEST) {
            baseData.mBalances.forEach { coin in
                var allKava = NSDecimalNumber.zero
                if (coin.balance_denom == getMainDenom(chainType)) {
                    allKava = allKava.adding(getAllMainAssetOld(getMainDenom(chainType)))
                } else {
                    allKava = allKava.adding(convertTokenToKava(coin.balance_denom))
                }
                let btcValue = btcValue(getMainDenom(chainType), allKava, 6)
                totalValue = totalValue.adding(btcValue)
            }
            
        } else if (chainType! == ChainType.OKEX_MAIN || chainType! == ChainType.OKEX_TEST) {
            baseData.mBalances.forEach { coin in
                if (coin.balance_denom == getMainDenom(chainType)) {
                    let amount = getAllExToken(coin.balance_denom)
                    let btcValue = btcValue(getMainDenom(chainType), amount, 0)
                    totalValue = totalValue.adding(btcValue)
                } else {
                    let convertAmount = convertTokenToOkt(coin.balance_denom)
                    let btcValue = btcValue(getMainDenom(chainType), convertAmount, 0)
                    totalValue = totalValue.adding(btcValue)
                    
                }
            }
            
        } else if (chainType! == ChainType.SIF_MAIN) {
            baseData.mBalances.forEach { coin in
                if (coin.balance_denom == getMainDenom(chainType)) {
                    let amount = getAllMainAssetOld(getMainDenom(chainType))
                    totalValue = totalValue.adding(btcValue(coin.balance_denom, amount, 18))
                } else {
                    let available = baseData.availableAmount(coin.balance_denom)
                    let decimal = getSifCoinDecimal(coin.balance_denom)
                    totalValue = totalValue.adding(btcValue(coin.balance_denom.substring(from: 1), available, decimal))
                }
            }
        }
        
        else {
            baseData.mBalances.forEach { coin in
                if (coin.balance_denom == getMainDenom(chainType)) {
                    let amount = getAllMainAssetOld(getMainDenom(chainType))
                    totalValue = totalValue.adding(btcValue(coin.balance_denom, amount, mainDivideDecimal(chainType)))
                }
            }
        }
        return totalValue
    }
    
    static func dpAllAssetValueUserCurrency(_ chainType: ChainType?, _ font:UIFont) -> NSMutableAttributedString {
        let totalValue = allAssetToUserCurrency(chainType)
        let nf = getNumberFormatter(3)
        let formatted = BaseData.instance.getCurrencySymbol() + " " + nf.string(from: totalValue)!
        return getDpAttributedString(formatted, 3, font)
    }
    
    static func dpAllAssetValueBtc(_ chainType: ChainType?, _ font:UIFont) -> NSMutableAttributedString {
        let totalValue = allAssetToBtc(chainType)
        let nf = getNumberFormatter(8)
        let formatted = nf.string(from: totalValue)!
        return getDpAttributedString(formatted, 8, font)
    }
    
    
    static func getNumberFormatter(_ divider: Int) -> NumberFormatter {
        let nf = NumberFormatter()
        nf.numberStyle = .decimal
        nf.minimumFractionDigits = divider
        nf.maximumFractionDigits = divider
        return nf
    }
    
    static func getDpAttributedString(_ dpString: String, _ divider: Int, _ font:UIFont)  -> NSMutableAttributedString {
        let endIndex    = dpString.index(dpString.endIndex, offsetBy: -divider)
        let preString   = dpString[..<endIndex]
        let postString  = dpString[endIndex...]
        let preAttrs    = [NSAttributedString.Key.font : font]
        let postAttrs   = [NSAttributedString.Key.font : font.withSize(CGFloat(Int(Double(font.pointSize) * 0.85)))]
        
        let attributedString1 = NSMutableAttributedString(string:String(preString), attributes:preAttrs as [NSAttributedString.Key : Any])
        let attributedString2 = NSMutableAttributedString(string:String(postString), attributes:postAttrs as [NSAttributedString.Key : Any])
        attributedString1.append(attributedString2)
        return attributedString1
    }
    
    
    static func displayGasRate(_ rate: NSDecimalNumber, font:UIFont, _ deciaml:Int) -> NSMutableAttributedString {
        let nf = NumberFormatter()
        nf.minimumFractionDigits = deciaml
        nf.maximumFractionDigits = deciaml
        nf.numberStyle = .decimal
        
        let formatted   = nf.string(from: rate)!
        let endIndex    = formatted.index(formatted.endIndex, offsetBy: -(deciaml))
        
        let preString   = formatted[..<endIndex]
        let postString  = formatted[endIndex...]
        
        let preAttrs = [NSAttributedString.Key.font : font]
        let postAttrs = [NSAttributedString.Key.font : font.withSize(CGFloat(Int(Double(font.pointSize) * 0.85)))]
        
        let attributedString1 = NSMutableAttributedString(string:String(preString), attributes:preAttrs as [NSAttributedString.Key : Any])
        let attributedString2 = NSMutableAttributedString(string:String(postString), attributes:postAttrs as [NSAttributedString.Key : Any])
        
        attributedString1.append(attributedString2)
        return attributedString1
    }
    
    static func displayGasRate2(_ rate: NSDecimalNumber, font:UIFont) -> NSMutableAttributedString {
        let nf = NumberFormatter()
        let deciaml = rate.stringValue.count - 2
        nf.minimumFractionDigits = deciaml
        nf.maximumFractionDigits = deciaml
        nf.numberStyle = .decimal
        
        let formatted   = nf.string(from: rate)!
        let endIndex    = formatted.index(formatted.endIndex, offsetBy: -(deciaml))
        
        let preString   = formatted[..<endIndex]
        let postString  = formatted[endIndex...]
        
        let preAttrs = [NSAttributedString.Key.font : font]
        let postAttrs = [NSAttributedString.Key.font : font.withSize(CGFloat(Int(Double(font.pointSize) * 0.85)))]
        
        let attributedString1 = NSMutableAttributedString(string:String(preString), attributes:preAttrs as [NSAttributedString.Key : Any])
        let attributedString2 = NSMutableAttributedString(string:String(postString), attributes:postAttrs as [NSAttributedString.Key : Any])
        
        attributedString1.append(attributedString2)
        return attributedString1
    }
    
    static func displayPriceUPdown(_ updown:NSDecimalNumber, font:UIFont ) -> NSMutableAttributedString {
        let nf = NumberFormatter()
        nf.minimumFractionDigits = 2
        nf.maximumFractionDigits = 2
        nf.numberStyle = .decimal
        
        let formatted   = nf.string(from: updown.rounding(accordingToBehavior: handler2))! + "% (24h)"
        let endIndex    = formatted.index(formatted.endIndex, offsetBy: -9)
        
        let preString   = formatted[..<endIndex]
        let postString  = formatted[endIndex...]
        
        let preAttrs = [NSAttributedString.Key.font : font]
        let postAttrs = [NSAttributedString.Key.font : font.withSize(CGFloat(Int(Double(font.pointSize) * 0.85)))]
        
        let attributedString1 = NSMutableAttributedString(string:String(preString), attributes:preAttrs as [NSAttributedString.Key : Any])
        let attributedString2 = NSMutableAttributedString(string:String(postString), attributes:postAttrs as [NSAttributedString.Key : Any])
        
        attributedString1.append(attributedString2)
        return attributedString1
    }
    
    static func displayPercent(_ rate:NSDecimalNumber, _ font:UIFont ) -> NSMutableAttributedString {
        let nf = NumberFormatter()
        nf.minimumFractionDigits = 2
        nf.maximumFractionDigits = 2
        nf.numberStyle = .decimal
        
        let formatted   = nf.string(from: rate.rounding(accordingToBehavior: handler2Down))! + "%"
        let endIndex    = formatted.index(formatted.endIndex, offsetBy: -3)
        
        let preString   = formatted[..<endIndex]
        let postString  = formatted[endIndex...]
        
        let preAttrs = [NSAttributedString.Key.font : font]
        let postAttrs = [NSAttributedString.Key.font : font.withSize(CGFloat(Int(Double(font.pointSize) * 0.85)))]
        
        let attributedString1 = NSMutableAttributedString(string:String(preString), attributes:preAttrs as [NSAttributedString.Key : Any])
        let attributedString2 = NSMutableAttributedString(string:String(postString), attributes:postAttrs as [NSAttributedString.Key : Any])
        
        attributedString1.append(attributedString2)
        return attributedString1
    }
    
    //remove this!!
    static func displayInflation(_ rate:NSDecimalNumber, font:UIFont) -> NSMutableAttributedString {
        let nf = NumberFormatter()
        nf.minimumFractionDigits = 2
        nf.maximumFractionDigits = 2
        nf.numberStyle = .decimal
        
        let formatted   = nf.string(from: rate.multiplying(by: 100).rounding(accordingToBehavior: handler2Down))! + "%"
        let endIndex    = formatted.index(formatted.endIndex, offsetBy: -3)
        
        let preString   = formatted[..<endIndex]
        let postString  = formatted[endIndex...]
        
        let preAttrs = [NSAttributedString.Key.font : font]
        let postAttrs = [NSAttributedString.Key.font : font.withSize(CGFloat(Int(Double(font.pointSize) * 0.85)))]
        
        let attributedString1 = NSMutableAttributedString(string:String(preString), attributes:preAttrs as [NSAttributedString.Key : Any])
        let attributedString2 = NSMutableAttributedString(string:String(postString), attributes:postAttrs as [NSAttributedString.Key : Any])
        
        attributedString1.append(attributedString2)
        return attributedString1
    }
    
    static func displayInflation(_ inflation:String?, font:UIFont) -> NSMutableAttributedString {
        let nf = NumberFormatter()
        nf.minimumFractionDigits = 2
        nf.maximumFractionDigits = 2
        nf.numberStyle = .decimal
        
        let inflationValue  = WUtils.plainStringToDecimal(inflation).multiplying(byPowerOf10: 2)
        return displayPercent(inflationValue, font)
    }
    
    static func getYieldPerBlock(_ chain: ChainType) -> NSDecimalNumber {
        let data = BaseData.instance
        if (chain == ChainType.COSMOS_MAIN || chain == ChainType.AKASH_MAIN || chain == ChainType.PERSIS_MAIN || chain == ChainType.CRYPTO_MAIN
                || chain == ChainType.COSMOS_TEST) {
            if (data.mStakingPool_gRPC == nil || data.mProvision_gRPC == nil || data.mMintParam_gRPC == nil) {
                return NSDecimalNumber.zero
            }
            let provisions = data.mProvision_gRPC!
            let bonded = plainStringToDecimal(data.mStakingPool_gRPC!.bondedTokens)
            let blocksPerYear = NSDecimalNumber.init(value: data.mMintParam_gRPC!.blocksPerYear)
            return provisions.dividing(by: bonded, withBehavior: handler24Down).dividing(by: blocksPerYear, withBehavior: handler24Down)
            
        } else if (chain == ChainType.IRIS_MAIN || chain == ChainType.IRIS_TEST) {
            if (data.mStakingPool_gRPC == nil || data.mIrisMintParam_gRPC == nil) {
                return NSDecimalNumber.zero
            } else {
                let inflation_base = NSDecimalNumber.init(string: "2000000000000000")
                let provisions = inflation_base.multiplying(by: plainStringToDecimal(data.mIrisMintParam_gRPC!.inflation)).multiplying(byPowerOf10: -18)
                let bonded = plainStringToDecimal(data.mStakingPool_gRPC!.bondedTokens)
                return provisions.dividing(by: bonded, withBehavior: handler24Down).dividing(by: plainStringToDecimal("6311520"), withBehavior: handler24Down)
            }
            
        } else {
            if (data.mStakingPool == nil || data.mProvision == nil || data.mMintParam == nil) {
                return NSDecimalNumber.zero
            }
            let provisions = WUtils.plainStringToDecimal(data.mProvision)
            let bonded = WUtils.plainStringToDecimal(data.mStakingPool!.object(forKey: "bonded_tokens") as? String)
            let blocksPerYear = WUtils.plainStringToDecimal(data.mMintParam?.blocks_per_year)
            return provisions.dividing(by: bonded, withBehavior: handler24Down).dividing(by: blocksPerYear, withBehavior: handler24Down)
        }
    }
    
    static func getDpEstApr(_ font: UIFont, _ chain: ChainType) -> NSMutableAttributedString {
        let rpr = getYieldPerBlock(chain)
        let estApr = YEAR_SEC.dividing(by: getCBlockTime(chain), withBehavior: handler24Down).multiplying(by: rpr).multiplying(byPowerOf10: 2)
        return displayPercent(estApr, font)
    }
    
    static func getDpEstAprCommission(_ font: UIFont, _ commission: NSDecimalNumber, _ chain: ChainType) -> NSMutableAttributedString {
        let rpr = getYieldPerBlock(chain)
        let commissionCal = NSDecimalNumber.one.subtracting(commission)
        let estAprCommission = YEAR_SEC.dividing(by: getCBlockTime(chain), withBehavior: handler24Down).multiplying(by: commissionCal).multiplying(by: rpr).multiplying(byPowerOf10: 2)
        return displayPercent(estAprCommission, font)
    }
    
    static func getDailyReward(_ font: UIFont, _ commission: NSDecimalNumber, _ delegated: NSDecimalNumber?, _ chain: ChainType) -> NSMutableAttributedString {
        let rpr = getYieldPerBlock(chain)
        let commissionCal = NSDecimalNumber.one.subtracting(commission)
        var dAmount = NSDecimalNumber.zero
        if (delegated != nil) {
            dAmount = delegated!
        }
        let estDpr = DAY_SEC.multiplying(by: commissionCal).multiplying(by: rpr).multiplying(by: dAmount).dividing(by: getCBlockTime(chain), withBehavior: handler12Down)
        return displayAmount2(estDpr.stringValue, font, mainDivideDecimal(chain), 12)
    }
    
    static func getMonthlyReward(_ font: UIFont, _ commission: NSDecimalNumber, _ delegated: NSDecimalNumber?, _ chain: ChainType) -> NSMutableAttributedString {
        let rpr = getYieldPerBlock(chain)
        let commissionCal = NSDecimalNumber.one.subtracting(commission)
        var dAmount = NSDecimalNumber.zero
        if (delegated != nil) {
            dAmount = delegated!
        }
        let estDpr = MONTH_SEC.multiplying(by: commissionCal).multiplying(by: rpr).multiplying(by: dAmount).dividing(by: getCBlockTime(chain), withBehavior: handler12Down)
        return displayAmount2(estDpr.stringValue, font, mainDivideDecimal(chain), 12)
    }
    
    static func displayYield(_ bonded:NSDecimalNumber, _ provision:NSDecimalNumber, _ commission:NSDecimalNumber, font:UIFont ) -> NSMutableAttributedString {
        let nf = NumberFormatter()
        nf.minimumFractionDigits = 2
        nf.maximumFractionDigits = 2
        nf.numberStyle = .decimal
        
        let formatted   = nf.string(from: provision.dividing(by: bonded).multiplying(by: (NSDecimalNumber.one.subtracting(commission))).multiplying(by: 100))! + "%"
        let endIndex    = formatted.index(formatted.endIndex, offsetBy: -3)
        
        let preString   = formatted[..<endIndex]
        let postString  = formatted[endIndex...]
        
        let preAttrs = [NSAttributedString.Key.font : font]
        let postAttrs = [NSAttributedString.Key.font : font.withSize(CGFloat(Int(Double(font.pointSize) * 0.85)))]
        
        let attributedString1 = NSMutableAttributedString(string:String(preString), attributes:preAttrs as [NSAttributedString.Key : Any])
        let attributedString2 = NSMutableAttributedString(string:String(postString), attributes:postAttrs as [NSAttributedString.Key : Any])
        
        attributedString1.append(attributedString2)
        return attributedString1
        
    }
    
    static func displayCommission(_ rate:String?, font:UIFont ) -> NSMutableAttributedString {
        let nf = NumberFormatter()
        nf.minimumFractionDigits = 2
        nf.maximumFractionDigits = 2
        nf.numberStyle = .decimal
        
        let formatted   = nf.string(from: plainStringToDecimal(rate).multiplying(by: 100))! + "%"
        let endIndex    = formatted.index(formatted.endIndex, offsetBy: -3)
        
        let preString   = formatted[..<endIndex]
        let postString  = formatted[endIndex...]
        
        let preAttrs = [NSAttributedString.Key.font : font]
        let postAttrs = [NSAttributedString.Key.font : font.withSize(CGFloat(Int(Double(font.pointSize) * 0.85)))]
        
        let attributedString1 = NSMutableAttributedString(string:String(preString), attributes:preAttrs as [NSAttributedString.Key : Any])
        let attributedString2 = NSMutableAttributedString(string:String(postString), attributes:postAttrs as [NSAttributedString.Key : Any])
        
        attributedString1.append(attributedString2)
        return attributedString1
    }
    
    static func displaySelfBondRate(_ selfShare: String?, _ totalShare: String?, _ font:UIFont ) ->  NSMutableAttributedString {
        let nf = NumberFormatter()
        nf.minimumFractionDigits = 2
        nf.maximumFractionDigits = 2
        nf.numberStyle = .decimal
        
        let selfDecimal = plainStringToDecimal(selfShare)
        let totalDecimal = plainStringToDecimal(totalShare)
        
        let formatted   = nf.string(from: selfDecimal.multiplying(by: 100).dividing(by: totalDecimal, withBehavior: handler2Down))! + "%"
        let endIndex    = formatted.index(formatted.endIndex, offsetBy: -3)
        
        let preString   = formatted[..<endIndex]
        let postString  = formatted[endIndex...]
        
        let preAttrs = [NSAttributedString.Key.font : font]
        let postAttrs = [NSAttributedString.Key.font : font.withSize(CGFloat(Int(Double(font.pointSize) * 0.85)))]
        
        let attributedString1 = NSMutableAttributedString(string:String(preString), attributes:preAttrs as [NSAttributedString.Key : Any])
        let attributedString2 = NSMutableAttributedString(string:String(postString), attributes:postAttrs as [NSAttributedString.Key : Any])
        
        attributedString1.append(attributedString2)
        return attributedString1
    }
    
    static func getAllMainAsset(_ denom: String) -> NSDecimalNumber {
        var amount = NSDecimalNumber.zero
        let data = BaseData.instance
        for balance in data.mMyBalances_gRPC {
            if (balance.denom == denom) {
                amount = amount.adding(plainStringToDecimal(balance.amount))
            }
        }
        for balance in data.mMyVestings_gRPC {
            if (balance.denom == denom) {
                amount = amount.adding(plainStringToDecimal(balance.amount))
            }
        }
        for delegation in data.mMyDelegations_gRPC {
            amount = amount.adding(plainStringToDecimal(delegation.balance.amount))
        }
        for unbonding in data.mMyUnbondings_gRPC {
            for entry in unbonding.entries {
                amount = amount.adding(plainStringToDecimal(entry.balance))
            }
        }
        for reward in data.mMyReward_gRPC {
            for coin in reward.reward {
                if (coin.denom == denom) {
                    amount = amount.adding(plainStringToDecimal(coin.amount).multiplying(byPowerOf10: -18))
                }
            }
        }
        return amount
    }
    
    static func getAllMainAssetOld(_ denom: String) -> NSDecimalNumber {
        let available = BaseData.instance.availableAmount(denom)
        let lock = BaseData.instance.lockedAmount(denom)
        let delegated = BaseData.instance.delegatedSumAmount()
        let unbonding = BaseData.instance.unbondingSumAmount()
        let reward = BaseData.instance.rewardAmount(denom)
        
        return available.adding(lock).adding(delegated).adding(unbonding).adding(reward)
    }
    
    static func marketPrice(_ chain: ChainType) -> String {
        var result = "usdt"
        if (chain == ChainType.COSMOS_MAIN || chain == ChainType.COSMOS_TEST) {
            result = result + ",uatom"
            for balance in BaseData.instance.mMyBalances_gRPC {
                if (balance.denom != COSMOS_MAIN_DENOM) {
                }
            }
            
        } else if (chain == ChainType.IRIS_MAIN || chain == ChainType.IRIS_TEST) {
            result = result + ",uiris"
            for balance in BaseData.instance.mMyBalances_gRPC {
                if (balance.denom != IRIS_MAIN_DENOM) {
                }
            }
            
        } else if (chain == ChainType.AKASH_MAIN) {
            result = result + ",uakt"
            for balance in BaseData.instance.mMyBalances_gRPC {
                if (balance.denom != getMainDenom(chain)) {
                }
            }
            
        } else if (chain == ChainType.PERSIS_MAIN) {
            result = result + ",uxprt"
            for balance in BaseData.instance.mMyBalances_gRPC {
                if (balance.denom != getMainDenom(chain)) {
                }
            }
            
        } else if (chain == ChainType.CRYPTO_MAIN) {
            result = result + ",basecro"
            for balance in BaseData.instance.mMyBalances_gRPC {
                if (balance.denom != getMainDenom(chain)) {
                }
            }
        }
        
        else if (chain == ChainType.BINANCE_MAIN || chain == ChainType.BINANCE_TEST) {
            result = result + ",bnb"
            
        } else if (chain == ChainType.OKEX_MAIN || chain == ChainType.OKEX_TEST) {
            result = result + ",okb,okt"
            
        } else if (chain == ChainType.KAVA_MAIN || chain == ChainType.KAVA_TEST) {
            result = result + ",ukava,hard"
            
        } else if (chain == ChainType.BAND_MAIN) {
            result = result + ",uband"
            
        } else if (chain == ChainType.IOV_MAIN || chain == ChainType.IOV_TEST) {
            result = result + ",uiov"
            
        } else if (chain == ChainType.CERTIK_MAIN) {
            result = result + ",uctk"
            
        } else if (chain == ChainType.SENTINEL_MAIN) {
            result = result + ",udvpn"
            
        } else if (chain == ChainType.FETCH_MAIN) {
            result = result + ",afet"
            
        } else if (chain == ChainType.SIF_MAIN) {
            result = result + ",rowan"
            for balance in BaseData.instance.mBalances {
                if (balance.balance_denom != getMainDenom(chain) && balance.balance_denom.starts(with: "c")) {
                    result = result + "," + balance.balance_denom.substring(from: 1)
                }
            }
            
        } else if (chain == ChainType.KI_MAIN) {
            result = result + ",uxki"
            
        } else if (chain == ChainType.SECRET_MAIN) {
            result = result + ",uscrt"
            
        }
        return result
    }
    
    static func getAllExToken(_ symbol: String) -> NSDecimalNumber {
        let dataBase = BaseData.instance
        if (symbol == OKEX_MAIN_DENOM) {
            return dataBase.availableAmount(symbol).adding(dataBase.lockedAmount(symbol)).adding(dataBase.okDepositAmount()).adding(dataBase.okWithdrawAmount())
        } else {
            return dataBase.availableAmount(symbol).adding(dataBase.lockedAmount(symbol))
        }
    }
    
    static func getAllBnbToken(_ symbol: String) -> NSDecimalNumber {
        let dataBase = BaseData.instance
        return dataBase.availableAmount(symbol).adding(dataBase.frozenAmount(symbol)).adding(dataBase.lockedAmount(symbol))
    }
    
    static func getKavaTokenAvailable(_ denom: String, _ balances: Array<Balance>) -> NSDecimalNumber {
        var amount = NSDecimalNumber.zero
        for balance in balances {
            if (balance.balance_denom == denom) {
                amount = localeStringToDecimal(balance.balance_amount)
            }
        }
        return amount
    }
    
    static func getKavaTokenVesting(_ denom: String, _ balances: Array<Balance>) -> NSDecimalNumber {
        var amount = NSDecimalNumber.zero
        for balance in balances {
            if (balance.balance_denom == denom) {
                amount = localeStringToDecimal(balance.balance_locked)
            }
        }
        return amount
    }
    
    static func getKavaTokenAll(_ denom: String, _ balances: Array<Balance>) -> NSDecimalNumber {
        var amount = NSDecimalNumber.zero
        for balance in balances {
            if (balance.balance_denom == denom) {
                amount = localeStringToDecimal(balance.balance_amount)
                amount = amount.adding(localeStringToDecimal(balance.balance_locked))
            }
        }
        return amount
    }
    
    static func getKavaTokenDollorValue(_ denom: String, _ amount: NSDecimalNumber) -> NSDecimalNumber {
        let dpDeciaml = getKavaCoinDecimal(denom)
        if (denom == "usdx" || denom == "busd") {
            return amount.multiplying(byPowerOf10: -dpDeciaml)
            
        } else {
            let prices = BaseData.instance.mKavaPrice
            if let price = prices["hard:usd:30"], denom == "hard" {
                return amount.multiplying(byPowerOf10: -dpDeciaml).multiplying(by: NSDecimalNumber.init(string: price.result.price))
            }
            if let price = prices["btc:usd:30"], denom.contains("btc") {
                return amount.multiplying(byPowerOf10: -dpDeciaml).multiplying(by: NSDecimalNumber.init(string: price.result.price))
            }
            if let price = prices["bnb:usd:30"], denom.contains("bnb") {
                return amount.multiplying(byPowerOf10: -dpDeciaml).multiplying(by: NSDecimalNumber.init(string: price.result.price))
            }
            if let price = prices["xrp:usd:30"], denom.contains("xrp") {
                return amount.multiplying(byPowerOf10: -dpDeciaml).multiplying(by: NSDecimalNumber.init(string: price.result.price))
            }
            return NSDecimalNumber.zero
        }
    }
    
    static func convertTokenToKava(_ denom: String) -> NSDecimalNumber {
        let baseData = BaseData.instance
        let tokenAmount = baseData.availableAmount(denom).adding(baseData.lockedAmount(denom))
        let totalTokenValue = getKavaTokenDollorValue(denom, tokenAmount)
        if let kavaUsd = perUsdValue(KAVA_MAIN_DENOM) {
            return totalTokenValue.multiplying(byPowerOf10: 6).dividing(by: kavaUsd, withBehavior: WUtils.getDivideHandler(6))
        }
        return NSDecimalNumber.zero
    }
    
    static func getOkexTokenDollorValue(_ okToken: OkToken?, _ amount: NSDecimalNumber) -> NSDecimalNumber {
        if (okToken == nil) { return NSDecimalNumber.zero }
        if (okToken!.original_symbol == "usdt" || okToken!.original_symbol == "usdc" || okToken!.original_symbol == "usdk") {
            return amount
            
        } else if (okToken!.original_symbol == "okb" && BaseData.instance.mOKBPrice != nil) {
            return amount.multiplying(by: BaseData.instance.mOKBPrice)
            
        } else if (BaseData.instance.mOkTickerList != nil) {
            //TODO display with ticker update!
            let okTickers = BaseData.instance.mOkTickerList?.data
            return NSDecimalNumber.zero
        }
        return NSDecimalNumber.zero
    }
    
    static func convertTokenToOkt(_ denom: String) -> NSDecimalNumber {
        let baseData = BaseData.instance
        let okToken = getOkToken(denom)
        let tokenAmount = baseData.availableAmount(denom).adding(baseData.lockedAmount(denom))
        let totalTokenValue = getOkexTokenDollorValue(okToken, tokenAmount)
        if let okTUsd = perUsdValue(OKEX_MAIN_DENOM) {
            return totalTokenValue.dividing(by: okTUsd, withBehavior: handler18)
        }
        return NSDecimalNumber.zero
    }
    
    static func getBnbToken(_ symbol: String?) -> BnbToken? {
        return BaseData.instance.mBnbTokenList.filter{ $0.symbol == symbol }.first
    }
    
    static func getBnbTokenTic(_ symbol: String?) -> BnbTicker? {
        return BaseData.instance.mBnbTokenTicker.filter { $0.symbol == getBnbTicSymbol(symbol!)}.first
    }
    
    static func getBnbConvertAmount(_ symbol: String?, _ amount: NSDecimalNumber) -> NSDecimalNumber {
        if let ticker = getBnbTokenTic(symbol) {
            if (isBnbMarketToken(symbol)) {
                return amount.dividing(by: ticker.getLastPrice(), withBehavior: WUtils.handler8)
            } else {
                return amount.multiplying(by: ticker.getLastPrice(), withBehavior: WUtils.handler8)
            }
        }
        return NSDecimalNumber.zero
    }
    
    static func getBnbMainToken(_ bnbTokens:Array<BnbToken>) -> BnbToken? {
        for bnbToken in bnbTokens {
            if (bnbToken.symbol == BNB_MAIN_DENOM) {
                return bnbToken
            }
        }
        return nil
    }
    
    static func getOkToken(_ symbol:String?) -> OkToken? {
        return BaseData.instance.mOkTokenList?.data?.filter { $0.symbol == symbol}.first
    }
    
    static func getTokenAmount(_ balances:Array<Balance>?, _ symbol:String) -> NSDecimalNumber {
        var result = NSDecimalNumber.zero
        if (balances != nil) {
            balances!.forEach({ (balance) in
                if (balance.balance_denom.caseInsensitiveCompare(symbol) == .orderedSame) {
                    result = result.adding(WUtils.plainStringToDecimal(balance.balance_amount))
                }
            })
        }
        return result
    }
    
    static func getDelegableAmount(_ balances:Array<Balance>?, _ symbol:String) -> NSDecimalNumber {
        var result = NSDecimalNumber.zero
        if (balances != nil) {
            balances!.forEach({ (balance) in
                if (balance.balance_denom.caseInsensitiveCompare(symbol) == .orderedSame) {
                    result = result.adding(WUtils.plainStringToDecimal(balance.balance_amount))
                    result = result.adding(WUtils.plainStringToDecimal(balance.balance_locked))
                }
            })
        }
        return result
    }
    
    static func showCoinDp(_ coin:Coin, _ denomLabel:UILabel, _ amountLabel:UILabel, _ chainType:ChainType) {
        if (chainType == ChainType.COSMOS_MAIN || chainType == ChainType.COSMOS_TEST) {
            if (coin.denom == COSMOS_MAIN_DENOM) {
                WUtils.setDenomTitle(chainType, denomLabel)
            } else {
                denomLabel.textColor = .white
                denomLabel.text = coin.denom.uppercased()
            }
            amountLabel.attributedText = displayAmount2(coin.amount, amountLabel.font, 6, 6)
            
        } else if (chainType == ChainType.IRIS_MAIN) {
            if (coin.denom == IRIS_MAIN_DENOM) {
                WUtils.setDenomTitle(chainType, denomLabel)
            } else {
                denomLabel.textColor = .white
                denomLabel.text = coin.denom.uppercased()
            }
            amountLabel.attributedText = displayAmount2(coin.amount, amountLabel.font, 6, 6)
            
        } else if (chainType == ChainType.BINANCE_MAIN || chainType == ChainType.BINANCE_TEST) {
            if (coin.denom == BNB_MAIN_DENOM) {
                WUtils.setDenomTitle(chainType, denomLabel)
            } else {
                denomLabel.textColor = .white
                denomLabel.text = coin.denom.uppercased()
            }
            amountLabel.attributedText = displayAmount2(coin.amount, amountLabel.font, 8, 8)
            
        } else if (chainType == ChainType.KAVA_MAIN || chainType == ChainType.KAVA_TEST) {
            if (coin.denom == KAVA_MAIN_DENOM) {
                WUtils.setDenomTitle(chainType, denomLabel)
            } else if (coin.denom == KAVA_HARD_DENOM) {
                denomLabel.textColor = COLOR_HARD
                denomLabel.text = coin.denom.uppercased()
            } else {
                denomLabel.textColor = .white
                denomLabel.text = coin.denom.uppercased()
            }
            amountLabel.attributedText = displayAmount2(coin.amount, amountLabel.font, getKavaCoinDecimal(coin.denom), getKavaCoinDecimal(coin.denom))
            
        } else if (chainType == ChainType.BAND_MAIN) {
            if (coin.denom == BAND_MAIN_DENOM) {
                WUtils.setDenomTitle(chainType, denomLabel)
            } else {
                denomLabel.textColor = .white
                denomLabel.text = coin.denom.uppercased()
            }
            amountLabel.attributedText = displayAmount2(coin.amount, amountLabel.font, 6, 6)
            
        } else if (chainType == ChainType.SECRET_MAIN) {
            if (coin.denom == SECRET_MAIN_DENOM) {
                WUtils.setDenomTitle(chainType, denomLabel)
            } else {
                denomLabel.textColor = .white
                denomLabel.text = coin.denom.uppercased()
            }
            amountLabel.attributedText = displayAmount2(coin.amount, amountLabel.font, 6, 6)
            
        } else if (chainType == ChainType.IOV_MAIN) {
            if (coin.denom == IOV_MAIN_DENOM) {
                WUtils.setDenomTitle(chainType, denomLabel)
            } else {
                denomLabel.textColor = .white
                denomLabel.text = coin.denom.uppercased()
            }
            amountLabel.attributedText = displayAmount2(coin.amount, amountLabel.font, 6, 6)
            
        } else if (chainType == ChainType.CERTIK_MAIN) {
            if (coin.denom == CERTIK_MAIN_DENOM) {
                WUtils.setDenomTitle(chainType, denomLabel)
            } else {
                denomLabel.textColor = .white
                denomLabel.text = coin.denom.uppercased()
            }
            amountLabel.attributedText = displayAmount2(coin.amount, amountLabel.font, 6, 6)
            
        } else if (chainType == ChainType.IOV_TEST) {
            if (coin.denom == IOV_TEST_DENOM) {
                WUtils.setDenomTitle(chainType, denomLabel)
            } else {
                denomLabel.textColor = .white
                denomLabel.text = coin.denom.uppercased()
            }
            amountLabel.attributedText = displayAmount2(coin.amount, amountLabel.font, 6, 6)
            
        } else if (chainType == ChainType.OKEX_MAIN || chainType == ChainType.OKEX_TEST) {
            if (coin.denom == OKEX_MAIN_DENOM) {
                WUtils.setDenomTitle(chainType, denomLabel)
            } else {
                denomLabel.textColor = .white
                denomLabel.text = coin.denom.uppercased()
            }
            amountLabel.attributedText = displayAmount2(coin.amount, amountLabel.font, 0, 18)
            
        } else if (chainType == ChainType.CERTIK_TEST) {
            if (coin.denom == CERTIK_MAIN_DENOM) {
                WUtils.setDenomTitle(chainType, denomLabel)
            } else {
                denomLabel.textColor = .white
                denomLabel.text = coin.denom.uppercased()
            }
            amountLabel.attributedText = displayAmount2(coin.amount, amountLabel.font, 6, 6)
            
        } else if (chainType == ChainType.AKASH_MAIN) {
            if (coin.denom == AKASH_MAIN_DENOM) {
                WUtils.setDenomTitle(chainType, denomLabel)
            } else {
                denomLabel.textColor = .white
                denomLabel.text = coin.denom.uppercased()
            }
            amountLabel.attributedText = displayAmount2(coin.amount, amountLabel.font, 6, 6)
            
        } else if (chainType == ChainType.PERSIS_MAIN) {
            if (coin.denom == PERSIS_MAIN_DENOM) {
                WUtils.setDenomTitle(chainType, denomLabel)
            } else {
                denomLabel.textColor = .white
                denomLabel.text = coin.denom.uppercased()
            }
            amountLabel.attributedText = displayAmount2(coin.amount, amountLabel.font, 6, 6)
            
        } else if (chainType == ChainType.SENTINEL_MAIN) {
            if (coin.denom == SENTINEL_MAIN_DENOM) {
                WUtils.setDenomTitle(chainType, denomLabel)
            } else {
                denomLabel.textColor = .white
                denomLabel.text = coin.denom.uppercased()
            }
            amountLabel.attributedText = displayAmount2(coin.amount, amountLabel.font, 6, 6)
            
        } else if (chainType == ChainType.FETCH_MAIN) {
            if (coin.denom == FETCH_MAIN_DENOM) {
                WUtils.setDenomTitle(chainType, denomLabel)
            } else {
                denomLabel.textColor = .white
                denomLabel.text = coin.denom.uppercased()
            }
            amountLabel.attributedText = displayAmount2(coin.amount, amountLabel.font, 18, 18)
            
        } else if (chainType == ChainType.CRYPTO_MAIN) {
            if (coin.denom == CRYPTO_MAIN_DENOM) {
                WUtils.setDenomTitle(chainType, denomLabel)
            } else {
                denomLabel.textColor = .white
                denomLabel.text = coin.denom.uppercased()
            }
            amountLabel.attributedText = displayAmount2(coin.amount, amountLabel.font, 8, 8)
            
        } else if (chainType == ChainType.SIF_MAIN) {
            let dpDecimal = WUtils.getSifCoinDecimal(coin.denom)
            if (coin.denom == SIF_MAIN_DENOM) {
                WUtils.setDenomTitle(chainType, denomLabel)
            } else {
                denomLabel.textColor = .white
                denomLabel.text = coin.denom.uppercased()
            }
            amountLabel.attributedText = displayAmount2(coin.amount, amountLabel.font, dpDecimal, dpDecimal)
            
        } else if (chainType == ChainType.KI_MAIN) {
            if (coin.denom == KI_MAIN_DENOM) {
                WUtils.setDenomTitle(chainType, denomLabel)
            } else {
                denomLabel.textColor = .white
                denomLabel.text = coin.denom.uppercased()
            }
            amountLabel.attributedText = displayAmount2(coin.amount, amountLabel.font, 6, 6)
            
        } else if (chainType == ChainType.IRIS_TEST) {
            if (coin.denom == IRIS_TEST_DENOM) {
                WUtils.setDenomTitle(chainType, denomLabel)
            } else {
                denomLabel.textColor = .white
                denomLabel.text = coin.denom.uppercased()
            }
            amountLabel.attributedText = displayAmount2(coin.amount, amountLabel.font, 6, 6)
            
        }
    }
    
    static func showCoinDp(_ denom:String, _ amount:String, _ denomLabel:UILabel, _ amountLabel:UILabel, _ chainType:ChainType) {
        if (chainType == ChainType.COSMOS_MAIN || chainType == ChainType.COSMOS_TEST) {
            if (denom == COSMOS_MAIN_DENOM) {
                WUtils.setDenomTitle(chainType, denomLabel)
            } else {
                denomLabel.textColor = .white
                denomLabel.text = denom.uppercased()
            }
            amountLabel.attributedText = displayAmount2(amount, amountLabel.font, 6, 6)
            
        } else if (chainType == ChainType.IRIS_MAIN) {
            if (denom == IRIS_MAIN_DENOM) {
                WUtils.setDenomTitle(chainType, denomLabel)
            } else {
                denomLabel.textColor = .white
                denomLabel.text = denom.uppercased()
            }
            amountLabel.attributedText = displayAmount2(amount, amountLabel.font, 6, 6)
            
        } else if (chainType == ChainType.BINANCE_MAIN || chainType == ChainType.BINANCE_TEST) {
            if (denom == BNB_MAIN_DENOM) {
                WUtils.setDenomTitle(chainType, denomLabel)
            } else {
                denomLabel.textColor = .white
                denomLabel.text = denom.uppercased()
            }
            amountLabel.attributedText = displayAmount2(amount, amountLabel.font, 8, 8)
            
        } else if (chainType == ChainType.KAVA_MAIN || chainType == ChainType.KAVA_TEST) {
            if (denom == KAVA_MAIN_DENOM) {
                WUtils.setDenomTitle(chainType, denomLabel)
            }  else if (denom == KAVA_HARD_DENOM) {
                denomLabel.textColor = COLOR_HARD
                denomLabel.text = denom.uppercased()
            }else {
                denomLabel.textColor = .white
                denomLabel.text = denom.uppercased()
            }
            amountLabel.attributedText = displayAmount2(amount, amountLabel.font, getKavaCoinDecimal(denom), getKavaCoinDecimal(denom))
            
        } else if (chainType == ChainType.BAND_MAIN) {
            if (denom == BAND_MAIN_DENOM) {
                WUtils.setDenomTitle(chainType, denomLabel)
            } else {
                denomLabel.textColor = .white
                denomLabel.text = denom.uppercased()
            }
            amountLabel.attributedText = displayAmount2(amount, amountLabel.font, 6, 6)
            
        } else if (chainType == ChainType.SECRET_MAIN) {
            if (denom == SECRET_MAIN_DENOM) {
                WUtils.setDenomTitle(chainType, denomLabel)
            } else {
                denomLabel.textColor = .white
                denomLabel.text = denom.uppercased()
            }
            amountLabel.attributedText = displayAmount2(amount, amountLabel.font, 6, 6)
            
        } else if (chainType == ChainType.IOV_MAIN) {
            if (denom == IOV_MAIN_DENOM) {
                WUtils.setDenomTitle(chainType, denomLabel)
            } else {
                denomLabel.textColor = .white
                denomLabel.text = denom.uppercased()
            }
            amountLabel.attributedText = displayAmount2(amount, amountLabel.font, 6, 6)
            
        } else if (chainType == ChainType.CERTIK_MAIN) {
            if (denom == CERTIK_MAIN_DENOM) {
                WUtils.setDenomTitle(chainType, denomLabel)
            } else {
                denomLabel.textColor = .white
                denomLabel.text = denom.uppercased()
            }
            amountLabel.attributedText = displayAmount2(amount, amountLabel.font, 6, 6)
            
        } else if (chainType == ChainType.IOV_TEST) {
            if (denom == IOV_TEST_DENOM) {
                WUtils.setDenomTitle(chainType, denomLabel)
            } else {
                denomLabel.textColor = .white
                denomLabel.text = denom.uppercased()
            }
            amountLabel.attributedText = displayAmount2(amount, amountLabel.font, 6, 6)
            
        } else if (chainType == ChainType.OKEX_MAIN || chainType == ChainType.OKEX_TEST) {
            if (denom == OKEX_MAIN_DENOM) {
                WUtils.setDenomTitle(chainType, denomLabel)
            } else {
                denomLabel.textColor = .white
                denomLabel.text = denom.uppercased()
            }
            amountLabel.attributedText = displayAmount2(amount, amountLabel.font, 0, 18)
            
        } else if (chainType == ChainType.CERTIK_TEST) {
            if (denom == CERTIK_MAIN_DENOM) {
                WUtils.setDenomTitle(chainType, denomLabel)
            } else {
                denomLabel.textColor = .white
                denomLabel.text = denom.uppercased()
            }
            amountLabel.attributedText = displayAmount2(amount, amountLabel.font, 6, 6)
            
        } else if (chainType == ChainType.AKASH_MAIN) {
            if (denom == AKASH_MAIN_DENOM) {
                WUtils.setDenomTitle(chainType, denomLabel)
            } else {
                denomLabel.textColor = .white
                denomLabel.text = denom.uppercased()
            }
            amountLabel.attributedText = displayAmount2(amount, amountLabel.font, 6, 6)
            
        } else if (chainType == ChainType.PERSIS_MAIN) {
            if (denom == PERSIS_MAIN_DENOM) {
                WUtils.setDenomTitle(chainType, denomLabel)
            } else {
                denomLabel.textColor = .white
                denomLabel.text = denom.uppercased()
            }
            amountLabel.attributedText = displayAmount2(amount, amountLabel.font, 6, 6)
            
        } else if (chainType == ChainType.SENTINEL_MAIN) {
            if (denom == SENTINEL_MAIN_DENOM) {
                WUtils.setDenomTitle(chainType, denomLabel)
            } else {
                denomLabel.textColor = .white
                denomLabel.text = denom.uppercased()
            }
            amountLabel.attributedText = displayAmount2(amount, amountLabel.font, 6, 6)
            
        } else if (chainType == ChainType.FETCH_MAIN) {
            if (denom == FETCH_MAIN_DENOM) {
                WUtils.setDenomTitle(chainType, denomLabel)
            } else {
                denomLabel.textColor = .white
                denomLabel.text = denom.uppercased()
            }
            amountLabel.attributedText = displayAmount2(amount, amountLabel.font, 18, 18)
            
        } else if (chainType == ChainType.CRYPTO_MAIN) {
            if (denom == CRYPTO_MAIN_DENOM) {
                WUtils.setDenomTitle(chainType, denomLabel)
            } else {
                denomLabel.textColor = .white
                denomLabel.text = denom.uppercased()
            }
            amountLabel.attributedText = displayAmount2(amount, amountLabel.font, 8, 8)
            
        } else if (chainType == ChainType.SIF_MAIN) {
            let dpDecimal = WUtils.getSifCoinDecimal(denom)
            if (denom == SIF_MAIN_DENOM) {
                WUtils.setDenomTitle(chainType, denomLabel)
            } else {
                denomLabel.textColor = .white
                denomLabel.text = denom.uppercased()
            }
            amountLabel.attributedText = displayAmount2(amount, amountLabel.font, dpDecimal, dpDecimal)
            
        } else if (chainType == ChainType.KI_MAIN) {
            if (denom == KI_MAIN_DENOM) {
                WUtils.setDenomTitle(chainType, denomLabel)
            } else {
                denomLabel.textColor = .white
                denomLabel.text = denom.uppercased()
            }
            amountLabel.attributedText = displayAmount2(amount, amountLabel.font, 6, 6)
            
        } else if (chainType == ChainType.IRIS_TEST) {
            if (denom == IRIS_TEST_DENOM) {
                WUtils.setDenomTitle(chainType, denomLabel)
            } else {
                denomLabel.textColor = .white
                denomLabel.text = denom.uppercased()
            }
            amountLabel.attributedText = displayAmount2(amount, amountLabel.font, 6, 6)
        }
            
    }
    
    static func isBnbMarketToken(_ symbol:String?) ->Bool {
        switch symbol {
        case "USDT.B-B7C":
            return true
        case "ETH.B-261":
            return true
        case "BTC.B-918":
            return true
        case "USDSB-1AC":
            return true
        case "THKDB-888":
            return true
        case "TUSDB-888":
            return true
        case "BTCB-1DE":
            return true
            
        case "ETH-1C9":
            return true
        case "BUSD-BD1":
            return true
        case "IDRTB-178":
            return true
        case "TAUDB-888":
            return true
            
        default:
            return false
        }
    }
    
    static func getBnbTicSymbol(_ symbol:String) -> String {
        if (isBnbMarketToken(symbol)) {
            return BNB_MAIN_DENOM + "_" + symbol
        } else {
            return  "" + symbol + "_" + BNB_MAIN_DENOM
        }
    }
    
    static func getTicData(_ ticSymbol:String, _ tics:[String : NSMutableDictionary]) ->NSMutableDictionary? {
        for (title, data) in tics {
            if (title == ticSymbol) {
                return data
            }
        }
        return nil
    }
    
    
    
    
    static func getChainColor(_ chain:ChainType?) -> UIColor {
        if (chain == ChainType.COSMOS_MAIN || chain == ChainType.COSMOS_TEST) {
            return COLOR_ATOM
        } else if (chain == ChainType.IRIS_MAIN || chain == ChainType.IRIS_TEST) {
            return COLOR_IRIS
        } else if (chain == ChainType.BINANCE_MAIN || chain == ChainType.BINANCE_TEST) {
            return COLOR_BNB
        } else if (chain == ChainType.KAVA_MAIN || chain == ChainType.KAVA_TEST) {
            return COLOR_KAVA
        } else if (chain == ChainType.IOV_MAIN || chain == ChainType.IOV_TEST) {
            return COLOR_IOV
        } else if (chain == ChainType.BAND_MAIN) {
            return COLOR_BAND
        } else if (chain == ChainType.SECRET_MAIN) {
            return COLOR_SECRET
        } else if (chain == ChainType.CERTIK_MAIN || chain == ChainType.CERTIK_TEST) {
            return COLOR_CERTIK
        } else if (chain == ChainType.OKEX_MAIN || chain == ChainType.OKEX_TEST) {
            return COLOR_OK
        } else if (chain == ChainType.AKASH_MAIN) {
            return COLOR_AKASH
        } else if (chain == ChainType.PERSIS_MAIN) {
            return COLOR_PERSIS
        } else if (chain == ChainType.SENTINEL_MAIN) {
            return COLOR_SENTINEL
        } else if (chain == ChainType.FETCH_MAIN) {
            return COLOR_FETCH
        } else if (chain == ChainType.CRYPTO_MAIN) {
            return COLOR_CRYPTO
        } else if (chain == ChainType.SIF_MAIN) {
            return COLOR_SIF
        } else if (chain == ChainType.KI_MAIN) {
            return COLOR_KI
        }
        return COLOR_ATOM
    }
    
    static func getChainDarkColor(_ chain:ChainType) -> UIColor {
        if (chain == ChainType.COSMOS_MAIN || chain == ChainType.COSMOS_TEST ) {
            return COLOR_ATOM_DARK
        } else if (chain == ChainType.IRIS_MAIN || chain == ChainType.IRIS_TEST) {
            return COLOR_IRIS_DARK
        } else if (chain == ChainType.BINANCE_MAIN || chain == ChainType.BINANCE_TEST) {
            return COLOR_BNB_DARK
        } else if (chain == ChainType.KAVA_MAIN || chain == ChainType.KAVA_TEST) {
            return COLOR_KAVA_DARK
        } else if (chain == ChainType.IOV_MAIN || chain == ChainType.IOV_TEST) {
            return COLOR_IOV_DARK
        } else if (chain == ChainType.BAND_MAIN) {
            return COLOR_BAND_DARK
        } else if (chain == ChainType.SECRET_MAIN) {
            return COLOR_SECRET_DARK
        } else if (chain == ChainType.CERTIK_MAIN || chain == ChainType.CERTIK_TEST) {
            return COLOR_CERTIK_DARK
        } else if (chain == ChainType.AKASH_MAIN) {
            return COLOR_AKASH_DARK
        } else if (chain == ChainType.OKEX_MAIN || chain == ChainType.OKEX_TEST) {
            return COLOR_OK_DARK
        } else if (chain == ChainType.PERSIS_MAIN) {
            return COLOR_PERSIS_DARK
        } else if (chain == ChainType.SENTINEL_MAIN) {
            return COLOR_SENTINEL_DARK
        } else if (chain == ChainType.FETCH_MAIN) {
            return COLOR_FETCH_DARK
        } else if (chain == ChainType.CRYPTO_MAIN) {
            return COLOR_CRYPTO_DARK
        } else if (chain == ChainType.SIF_MAIN) {
            return COLOR_SIF_DARK
        } else if (chain == ChainType.KI_MAIN) {
            return COLOR_KI_DARK
        }
        return COLOR_DARK_GRAY
    }
    
    static func getChainBg(_ chain:ChainType?) -> UIColor {
        if (chain == ChainType.COSMOS_MAIN || chain == ChainType.COSMOS_TEST) {
            return TRANS_BG_COLOR_COSMOS
        } else if (chain == ChainType.IRIS_MAIN || chain == ChainType.IRIS_TEST) {
            return TRANS_BG_COLOR_IRIS
        } else if (chain == ChainType.BINANCE_MAIN || chain == ChainType.BINANCE_TEST) {
            return TRANS_BG_COLOR_BNB
        } else if (chain == ChainType.KAVA_MAIN || chain == ChainType.KAVA_TEST) {
            return TRANS_BG_COLOR_KAVA
        } else if (chain == ChainType.IOV_MAIN || chain == ChainType.IOV_TEST ) {
            return TRANS_BG_COLOR_IOV
        } else if (chain == ChainType.BAND_MAIN) {
            return TRANS_BG_COLOR_BAND
        } else if (chain == ChainType.SECRET_MAIN) {
            return TRANS_BG_COLOR_SECRET
        } else if (chain == ChainType.CERTIK_MAIN || chain == ChainType.CERTIK_TEST) {
            return TRANS_BG_COLOR_CERTIK
        } else if (chain == ChainType.AKASH_MAIN) {
            return TRANS_BG_COLOR_AKASH
        } else if (chain == ChainType.OKEX_MAIN || chain == ChainType.OKEX_TEST) {
            return TRANS_BG_COLOR_OK
        } else if (chain == ChainType.PERSIS_MAIN) {
            return TRANS_BG_COLOR_PERSIS
        } else if (chain == ChainType.SENTINEL_MAIN) {
            return TRANS_BG_COLOR_SENTINEL
        } else if (chain == ChainType.FETCH_MAIN) {
            return TRANS_BG_COLOR_FETCH
        } else if (chain == ChainType.CRYPTO_MAIN) {
            return TRANS_BG_COLOR_CRYPTO
        } else if (chain == ChainType.SIF_MAIN) {
            return TRANS_BG_COLOR_SIF
        } else if (chain == ChainType.KI_MAIN) {
            return TRANS_BG_COLOR_KI
        }
        return COLOR_BG_GRAY
    }
    
    static func getDpMainDenom(_ chain:ChainType) -> String {
        if (chain == ChainType.COSMOS_MAIN) {
            return "ATOM"
        } else if (chain == ChainType.IRIS_MAIN) {
            return "IRIS"
        } else if (chain == ChainType.BINANCE_MAIN || chain == ChainType.BINANCE_TEST) {
            return "BNB"
        } else if (chain == ChainType.KAVA_MAIN || chain == ChainType.KAVA_TEST) {
            return "KAVA"
        } else if (chain == ChainType.IOV_MAIN || chain == ChainType.IOV_TEST) {
            return "IOV"
        } else if (chain == ChainType.BAND_MAIN) {
            return "BAND"
        } else if (chain == ChainType.SECRET_MAIN) {
            return "SCRT"
        } else if (chain == ChainType.OKEX_MAIN || chain == ChainType.OKEX_TEST) {
            return "OKT"
        } else if (chain == ChainType.CERTIK_MAIN || chain == ChainType.CERTIK_TEST) {
            return "CTK"
        } else if (chain == ChainType.AKASH_MAIN) {
            return "AKT"
        } else if (chain == ChainType.PERSIS_MAIN) {
            return "XPRT"
        } else if (chain == ChainType.SENTINEL_MAIN) {
            return "DVPN"
        } else if (chain == ChainType.FETCH_MAIN) {
            return "FET"
        } else if (chain == ChainType.CRYPTO_MAIN) {
            return "CRO"
        } else if (chain == ChainType.SIF_MAIN) {
            return "ROWAN"
        } else if (chain == ChainType.KI_MAIN) {
            return "XKI"
        } else if (chain == ChainType.COSMOS_TEST) {
            return "MUON"
        } else if (chain == ChainType.IRIS_TEST) {
            return "BIF"
        }
        return ""
    }
    
    static func getMainDenom(_ chain:ChainType?) -> String {
        if (chain == ChainType.COSMOS_MAIN) {
            return COSMOS_MAIN_DENOM
        } else if (chain == ChainType.IRIS_MAIN) {
            return IRIS_MAIN_DENOM
        } else if (chain == ChainType.BINANCE_MAIN || chain == ChainType.BINANCE_TEST) {
            return BNB_MAIN_DENOM
        } else if (chain == ChainType.KAVA_MAIN || chain == ChainType.KAVA_TEST) {
            return KAVA_MAIN_DENOM
        } else if (chain == ChainType.IOV_MAIN ) {
            return IOV_MAIN_DENOM
        } else if (chain == ChainType.BAND_MAIN) {
            return BAND_MAIN_DENOM
        } else if (chain == ChainType.SECRET_MAIN) {
            return SECRET_MAIN_DENOM
        } else if (chain == ChainType.OKEX_MAIN || chain == ChainType.OKEX_TEST) {
            return OKEX_MAIN_DENOM
        } else if (chain == ChainType.CERTIK_MAIN || chain == ChainType.CERTIK_TEST) {
            return CERTIK_MAIN_DENOM
        } else if (chain == ChainType.AKASH_MAIN) {
            return AKASH_MAIN_DENOM
        } else if (chain == ChainType.PERSIS_MAIN) {
            return PERSIS_MAIN_DENOM
        } else if (chain == ChainType.SENTINEL_MAIN) {
            return SENTINEL_MAIN_DENOM
        } else if (chain == ChainType.FETCH_MAIN) {
            return FETCH_MAIN_DENOM
        } else if (chain == ChainType.CRYPTO_MAIN) {
            return CRYPTO_MAIN_DENOM
        } else if (chain == ChainType.SIF_MAIN) {
            return SIF_MAIN_DENOM
        } else if (chain == ChainType.KI_MAIN) {
            return KI_MAIN_DENOM
        }
        
        else if (chain == ChainType.COSMOS_TEST) {
            return COSMOS_TEST_DENOM
        } else if (chain == ChainType.IRIS_TEST) {
            return IRIS_TEST_DENOM
        } else if (chain == ChainType.IOV_TEST) {
            return IOV_TEST_DENOM
        }
        return ""

    }
    
    static func mainDivideDecimal(_ chain:ChainType?) -> Int16 {
        if (chain == ChainType.BINANCE_MAIN || chain == ChainType.BINANCE_TEST) {
            return 0
        } else if (chain == ChainType.OKEX_MAIN || chain == ChainType.OKEX_TEST) {
            return 0
        } else if (chain == ChainType.FETCH_MAIN || chain == ChainType.SIF_MAIN) {
            return 18
        } else if (chain == ChainType.CRYPTO_MAIN) {
            return 8
        } else {
            return 6
        }
    }
    
    static func mainDisplayDecimal(_ chain:ChainType?) -> Int16 {
        if (chain == ChainType.BINANCE_MAIN || chain == ChainType.BINANCE_TEST) {
            return 8
        } else if (chain == ChainType.OKEX_MAIN || chain == ChainType.OKEX_TEST) {
            return 18
        } else if (chain == ChainType.FETCH_MAIN || chain == ChainType.SIF_MAIN) {
            return 18
        } else if (chain == ChainType.CRYPTO_MAIN) {
            return 8
        } else {
            return 6
        }
    }
    
    static func setDenomTitle(_ chain: ChainType?, _ label: UILabel) {
        if (chain == ChainType.COSMOS_MAIN) {
            label.text = "ATOM"
            label.textColor = COLOR_ATOM
        } else if (chain == ChainType.IRIS_MAIN) {
            label.text = "IRIS"
            label.textColor = COLOR_IRIS
        } else if (chain == ChainType.BINANCE_MAIN || chain == ChainType.BINANCE_TEST) {
            label.text = "BNB"
            label.textColor = COLOR_BNB
        } else if (chain == ChainType.KAVA_MAIN || chain == ChainType.KAVA_TEST) {
            label.text = "KAVA"
            label.textColor = COLOR_KAVA
        } else if (chain == ChainType.IOV_MAIN || chain == ChainType.IOV_TEST) {
            label.text = "IOV"
            label.textColor = COLOR_IOV
        } else if (chain == ChainType.BAND_MAIN) {
            label.text = "BAND"
            label.textColor = COLOR_BAND
        } else if (chain == ChainType.SECRET_MAIN) {
            label.text = "SCRT"
            label.textColor = COLOR_SECRET
        } else if (chain == ChainType.OKEX_MAIN || chain == ChainType.OKEX_TEST) {
            label.text = "OKT"
            label.textColor = COLOR_OK
        } else if (chain == ChainType.CERTIK_MAIN || chain == ChainType.CERTIK_TEST) {
            label.text = "CTK"
            label.textColor = COLOR_CERTIK
        } else if (chain == ChainType.AKASH_MAIN) {
            label.text = "AKT"
            label.textColor = COLOR_AKASH
        } else if (chain == ChainType.PERSIS_MAIN) {
            label.text = "XPRT"
            label.textColor = COLOR_PERSIS
        } else if (chain == ChainType.SENTINEL_MAIN) {
            label.text = "DVPN"
            label.textColor = COLOR_SENTINEL
        } else if (chain == ChainType.FETCH_MAIN) {
            label.text = "FET"
            label.textColor = COLOR_FETCH
        } else if (chain == ChainType.CRYPTO_MAIN) {
            label.text = "CRO"
            label.textColor = COLOR_CRYPTO
        } else if (chain == ChainType.SIF_MAIN) {
            label.text = "ROWAN"
            label.textColor = COLOR_SIF
        } else if (chain == ChainType.KI_MAIN) {
            label.text = "XKI"
            label.textColor = COLOR_KI
        } else if (chain == ChainType.COSMOS_TEST) {
            label.text = "MUON"
            label.textColor = COLOR_ATOM
        } else if (chain == ChainType.IRIS_TEST) {
            label.text = "BIF"
            label.textColor = COLOR_IRIS
        }
    }
    
    static func getChainType(_ chainS:String) -> ChainType? {
        if (chainS == CHAIN_COSMOS_S ) {
            return ChainType.COSMOS_MAIN
        } else if (chainS == CHAIN_IRIS_S) {
            return ChainType.IRIS_MAIN
        } else if (chainS == CHAIN_BINANCE_S) {
            return ChainType.BINANCE_MAIN
        } else if (chainS == CHAIN_KAVA_S) {
           return ChainType.KAVA_MAIN
        } else if (chainS == CHAIN_IOV_S) {
            return ChainType.IOV_MAIN
        } else if (chainS == CHAIN_BAND_S) {
            return ChainType.BAND_MAIN
        } else if (chainS == CHAIN_SECRET_S) {
            return ChainType.SECRET_MAIN
        } else if (chainS == CHAIN_CERTIK_S) {
            return ChainType.CERTIK_MAIN
        } else if (chainS == CHAIN_AKASH_S) {
            return ChainType.AKASH_MAIN
        } else if (chainS == CHAIN_OKEX_S) {
            return ChainType.OKEX_MAIN
        } else if (chainS == CHAIN_PERSIS_S) {
            return ChainType.PERSIS_MAIN
        } else if (chainS == CHAIN_SENTINEL_S) {
            return ChainType.SENTINEL_MAIN
        } else if (chainS == CHAIN_FETCH_S) {
            return ChainType.FETCH_MAIN
        } else if (chainS == CHAIN_CRYPTO_S) {
            return ChainType.CRYPTO_MAIN
        } else if (chainS == CHAIN_SIF_S) {
            return ChainType.SIF_MAIN
        } else if (chainS == CHAIN_KI_S) {
            return ChainType.KI_MAIN
        }
        
        else if (chainS == CHAIN_COSMOS_TEST_S) {
            return ChainType.COSMOS_TEST
        } else if (chainS == CHAIN_IRIS_TEST_S) {
            return ChainType.IRIS_TEST
        } else if (chainS == CHAIN_KAVA_TEST_S) {
            return ChainType.KAVA_TEST
        } else if (chainS == CHAIN_BINANCE_TEST_S) {
            return ChainType.BINANCE_TEST
        } else if (chainS == CHAIN_IOV_TEST_S) {
            return ChainType.IOV_TEST
        } else if (chainS == CHAIN_OKEX_TEST_S) {
            return ChainType.OKEX_TEST
        } else if (chainS == CHAIN_CERTIK_TEST_S) {
            return ChainType.CERTIK_TEST
        }
        return nil
    }
    
    static func getChainDBName(_ chain:ChainType) -> String {
        if (chain == ChainType.COSMOS_MAIN) {
            return CHAIN_COSMOS_S
        } else if (chain == ChainType.IRIS_MAIN) {
            return CHAIN_IRIS_S
        } else if (chain == ChainType.BINANCE_MAIN) {
            return CHAIN_BINANCE_S
        } else if (chain == ChainType.KAVA_MAIN) {
            return CHAIN_KAVA_S
        } else if (chain == ChainType.IOV_MAIN) {
            return CHAIN_IOV_S
        } else if (chain == ChainType.BAND_MAIN) {
            return CHAIN_BAND_S
        } else if (chain == ChainType.SECRET_MAIN) {
            return CHAIN_SECRET_S
        } else if (chain == ChainType.CERTIK_MAIN) {
            return CHAIN_CERTIK_S
        } else if (chain == ChainType.AKASH_MAIN) {
            return CHAIN_AKASH_S
        } else if (chain == ChainType.OKEX_MAIN) {
            return CHAIN_OKEX_S
        } else if (chain == ChainType.PERSIS_MAIN) {
            return CHAIN_PERSIS_S
        } else if (chain == ChainType.SENTINEL_MAIN) {
            return CHAIN_SENTINEL_S
        } else if (chain == ChainType.FETCH_MAIN) {
            return CHAIN_FETCH_S
        } else if (chain == ChainType.CRYPTO_MAIN) {
            return CHAIN_CRYPTO_S
        } else if (chain == ChainType.SIF_MAIN) {
            return CHAIN_SIF_S
        } else if (chain == ChainType.KI_MAIN) {
            return CHAIN_KI_S
        }
        
        else if (chain == ChainType.COSMOS_TEST) {
            return CHAIN_COSMOS_TEST_S
        } else if (chain == ChainType.IRIS_TEST) {
            return CHAIN_IRIS_TEST_S
        } else if (chain == ChainType.BINANCE_TEST) {
            return CHAIN_BINANCE_TEST_S
        } else if (chain == ChainType.KAVA_TEST) {
            return CHAIN_KAVA_TEST_S
        } else if (chain == ChainType.IOV_TEST) {
            return CHAIN_IOV_TEST_S
        } else if (chain == ChainType.OKEX_TEST) {
            return CHAIN_OKEX_TEST_S
        } else if (chain == ChainType.CERTIK_TEST) {
            return CHAIN_CERTIK_TEST_S
        }
        return ""
    }
    
    static func getChainTypeInt(_ chainS:String) -> Int {
        if (chainS == CHAIN_COSMOS_S ) {
            return 1
        } else if (chainS == CHAIN_IRIS_S) {
            return 2
        } else if (chainS == CHAIN_BINANCE_S) {
            return 3
        }
        return 0
    }
    
    static func getMonikerName(_ validators: Array<Validator>,  _ opAddr: String, _ bracket:Bool) -> String {
        for val in validators {
            if (val.operator_address == opAddr) {
                if (bracket) {
                    return "(" + val.description.moniker + ")"
                } else {
                    return val.description.moniker
                }
            }
        }
        return ""
    }
    
    //TODO check confirm starname regular express
    static func isValidStarName(_ starname: String) -> Bool {
        let starNameRegEx = "[0-9a-z.-]{0,64}+\\*[a-z0-9.-]{3,16}"
        let starNamePred = NSPredicate(format:"SELF MATCHES %@", starNameRegEx)
        return starNamePred.evaluate(with: starname)
    }
    
    static func isValidDomain(_ starname: String) -> Bool {
        let starNameRegEx = "[a-z0-9]{4,32}"
        let starNamePred = NSPredicate(format:"SELF MATCHES %@", starNameRegEx)
        return starNamePred.evaluate(with: starname)
    }
    
    static func isValidAccount(_ starname: String) -> Bool {
        let starNameRegEx = "[0-9a-z.-]{1,63}"
        let starNamePred = NSPredicate(format:"SELF MATCHES %@", starNameRegEx)
        return starNamePred.evaluate(with: starname)
    }
    
    static func clearBackgroundColor(of view: UIView) {
        if let effectsView = view as? UIVisualEffectView {
            effectsView.removeFromSuperview()
            return
        }
        view.backgroundColor = .clear
        view.subviews.forEach { (subview) in
            self.clearBackgroundColor(of: subview)
        }
    }
    
    
    
    static func getPasswordAni() -> CAAnimation{
        let transition:CATransition = CATransition()
        transition.duration = 0.3
        transition.timingFunction = CAMediaTimingFunction(name: CAMediaTimingFunctionName.easeInEaseOut)
        transition.type = CATransitionType.moveIn
        transition.subtype = CATransitionSubtype.fromTop
        return transition
    }
    

    static func getEstimateGasAmount(_ chain:ChainType, _ type:String,  _ valCnt:Int) -> NSDecimalNumber {
        var result = NSDecimalNumber.zero
        if (chain == ChainType.COSMOS_MAIN || chain == ChainType.IRIS_MAIN || chain == ChainType.AKASH_MAIN || chain == ChainType.PERSIS_MAIN || chain == ChainType.CRYPTO_MAIN ||
                chain == ChainType.COSMOS_TEST || chain == ChainType.IRIS_TEST) {
            if (type == COSMOS_MSG_TYPE_TRANSFER2) {
                result = NSDecimalNumber.init(string: String(GAS_FEE_AMOUNT_LOW))
            } else if (type == COSMOS_MSG_TYPE_DELEGATE) {
                result = NSDecimalNumber.init(string: String(GAS_FEE_AMOUNT_MID))
            } else if (type == COSMOS_MSG_TYPE_UNDELEGATE2) {
                result = NSDecimalNumber.init(string: String(GAS_FEE_AMOUNT_MID))
            } else if (type == COSMOS_MSG_TYPE_REDELEGATE2) {
                result = NSDecimalNumber.init(string: String(GAS_FEE_AMOUNT_HIGH))
            } else if (type == COSMOS_MSG_TYPE_WITHDRAW_DEL) {
                result = getGasAmountForRewards()[valCnt - 1]
            } else if (type == COSMOS_MULTI_MSG_TYPE_REINVEST) {
                result = NSDecimalNumber.init(string: String(GAS_FEE_AMOUNT_HIGH))
            } else if (type == COSMOS_MSG_TYPE_WITHDRAW_MIDIFY) {
                result = NSDecimalNumber.init(string: String(GAS_FEE_AMOUNT_LOW))
            } else if (type == TASK_TYPE_VOTE) {
                result = NSDecimalNumber.init(string: String(GAS_FEE_AMOUNT_LOW))
            }
            
        }
        
        else if (chain == ChainType.KAVA_MAIN || chain == ChainType.KAVA_TEST) {
            if (type == COSMOS_MSG_TYPE_TRANSFER2) {
                result = NSDecimalNumber.init(string: String(KAVA_GAS_AMOUNT_SEND))
            } else if (type == COSMOS_MSG_TYPE_DELEGATE) {
                result = NSDecimalNumber.init(string: String(KAVA_GAS_AMOUNT_STAKE))
            } else if (type == COSMOS_MSG_TYPE_UNDELEGATE2) {
                result = NSDecimalNumber.init(string: String(KAVA_GAS_AMOUNT_STAKE))
            } else if (type == COSMOS_MSG_TYPE_REDELEGATE2) {
                result = NSDecimalNumber.init(string: String(KAVA_GAS_AMOUNT_REDELEGATE))
            } else if (type == COSMOS_MSG_TYPE_WITHDRAW_DEL) {
                result = getGasAmountForKavaRewards()[valCnt - 1]
            } else if (type == COSMOS_MULTI_MSG_TYPE_REINVEST) {
                result = NSDecimalNumber.init(string: String(KAVA_GAS_AMOUNT_REINVEST))
            } else if (type == TASK_TYPE_VOTE) {
                result = NSDecimalNumber.init(string: String(KAVA_GAS_AMOUNT_VOTE))
            } else if (type == KAVA_MSG_TYPE_USDX_MINT_INCENTIVE || type == KAVA_MSG_TYPE_CLAIM_HARD_INCENTIVE) {
                result = NSDecimalNumber.init(string: String(KAVA_GAS_AMOUNT_CLAIM_INCENTIVE))
            } else if (type == KAVA_MSG_TYPE_CREATE_CDP || type == KAVA_MSG_TYPE_DEPOSIT_CDP || type == KAVA_MSG_TYPE_WITHDRAW_CDP ||
                        type == KAVA_MSG_TYPE_DRAWDEBT_CDP || type == KAVA_MSG_TYPE_REPAYDEBT_CDP) {
                result = NSDecimalNumber.init(string: String(KAVA_GAS_AMOUNT_CDP))
            } else if (type == KAVA_MSG_TYPE_DEPOSIT_HARD || type == KAVA_MSG_TYPE_WITHDRAW_HARD || type == KAVA_MSG_TYPE_BORROW_HARD ||
                        type == KAVA_MSG_TYPE_REPAY_HARD) {
                result = NSDecimalNumber.init(string: String(KAVA_GAS_AMOUNT_HARD_POOL))
            } else if (type == TASK_TYPE_HTLC_SWAP) {
                result = NSDecimalNumber.init(string: String(KAVA_GAS_AMOUNT_BEP3))
            }
            
        } else if (chain == ChainType.BAND_MAIN) {
            if (type == COSMOS_MSG_TYPE_TRANSFER2) {
                result = NSDecimalNumber.init(string: String(BAND_GAS_AMOUNT_SEND))
            } else if (type == COSMOS_MSG_TYPE_DELEGATE) {
                result = NSDecimalNumber.init(string: String(BAND_GAS_AMOUNT_STAKE))
            } else if (type == COSMOS_MSG_TYPE_UNDELEGATE2) {
                result = NSDecimalNumber.init(string: String(BAND_GAS_AMOUNT_STAKE))
            } else if (type == COSMOS_MSG_TYPE_REDELEGATE2) {
                result = NSDecimalNumber.init(string: String(BAND_GAS_AMOUNT_REDELEGATE))
            } else if (type == COSMOS_MSG_TYPE_WITHDRAW_DEL) {
                result = WUtils.getGasAmountForKavaRewards()[valCnt - 1]
            } else if (type == COSMOS_MULTI_MSG_TYPE_REINVEST) {
                result = NSDecimalNumber.init(string: String(BAND_GAS_AMOUNT_REINVEST))
            } else if (type == COSMOS_MSG_TYPE_WITHDRAW_MIDIFY) {
                result = NSDecimalNumber.init(string: String(BAND_GAS_AMOUNT_ADDRESS_CHANGE))
            } else if (type == TASK_TYPE_VOTE) {
                result = NSDecimalNumber.init(string: String(BAND_GAS_AMOUNT_VOTE))
            }
            
        } else if (chain == ChainType.IOV_MAIN || chain == ChainType.IOV_TEST) {
            if (type == COSMOS_MSG_TYPE_TRANSFER2) {
                result = NSDecimalNumber.init(string: String(IOV_GAS_AMOUNT_SEND))
            } else if (type == COSMOS_MSG_TYPE_DELEGATE) {
                result = NSDecimalNumber.init(string: String(IOV_GAS_AMOUNT_STAKE))
            } else if (type == COSMOS_MSG_TYPE_UNDELEGATE2) {
                result = NSDecimalNumber.init(string: String(IOV_GAS_AMOUNT_STAKE))
            } else if (type == COSMOS_MSG_TYPE_REDELEGATE2) {
                result = NSDecimalNumber.init(string: String(IOV_GAS_AMOUNT_REDELEGATE))
            } else if (type == COSMOS_MSG_TYPE_WITHDRAW_DEL) {
                result = WUtils.getGasAmountForKavaRewards()[valCnt - 1]
            } else if (type == COSMOS_MULTI_MSG_TYPE_REINVEST) {
                result = NSDecimalNumber.init(string: String(IOV_GAS_AMOUNT_REINVEST))
            } else if (type == COSMOS_MSG_TYPE_WITHDRAW_MIDIFY) {
                result = NSDecimalNumber.init(string: String(IOV_GAS_AMOUNT_ADDRESS_CHANGE))
            } else if (type == TASK_TYPE_VOTE) {
                result = NSDecimalNumber.init(string: String(IOV_GAS_AMOUNT_VOTE))
            } else if (type == IOV_MSG_TYPE_REGISTER_DOMAIN || type == IOV_MSG_TYPE_REGISTER_ACCOUNT) {
                result = NSDecimalNumber.init(string: String(IOV_GAS_AMOUNT_REGISTER))
            } else if (type == IOV_MSG_TYPE_DELETE_DOMAIN || type == IOV_MSG_TYPE_DELETE_ACCOUNT) {
                result = NSDecimalNumber.init(string: String(IOV_GAS_AMOUNT_DELETE))
            } else if (type == IOV_MSG_TYPE_RENEW_DOMAIN || type == IOV_MSG_TYPE_RENEW_ACCOUNT) {
                result = NSDecimalNumber.init(string: String(IOV_GAS_AMOUNT_RENEW))
            } else if (type == IOV_MSG_TYPE_REPLACE_ACCOUNT_RESOURCE) {
                result = NSDecimalNumber.init(string: String(IOV_GAS_AMOUNT_REPLACE))
            }
            
        } else if (chain == ChainType.OKEX_MAIN || chain == ChainType.OKEX_TEST) {
            if (type == OK_MSG_TYPE_TRANSFER) {
                result = NSDecimalNumber.init(string: String(OK_GAS_AMOUNT_SEND))
            } else if (type == OK_MSG_TYPE_DEPOSIT || type == OK_MSG_TYPE_WITHDRAW) {
                result = (NSDecimalNumber.init(string: OK_GAS_AMOUNT_STAKE_MUX).multiplying(by: NSDecimalNumber.init(value: valCnt))).adding(NSDecimalNumber.init(string: OK_GAS_AMOUNT_STAKE))
            } else if (type == OK_MSG_TYPE_DIRECT_VOTE) {
                result = (NSDecimalNumber.init(string: OK_GAS_AMOUNT_VOTE_MUX).multiplying(by: NSDecimalNumber.init(value: valCnt))).adding(NSDecimalNumber.init(string: OK_GAS_AMOUNT_VOTE))
            }
            
        } else if (chain == ChainType.CERTIK_MAIN || chain == ChainType.CERTIK_TEST) {
            if (type == CERTIK_MSG_TYPE_TRANSFER) {
                result = NSDecimalNumber.init(string: String(CERTIK_GAS_AMOUNT_SEND))
            } else if (type == COSMOS_MSG_TYPE_DELEGATE || type == COSMOS_MSG_TYPE_UNDELEGATE2) {
                result = NSDecimalNumber.init(string: String(CERTIK_GAS_AMOUNT_STAKE))
            } else if (type == COSMOS_MSG_TYPE_WITHDRAW_DEL) {
                result = getGasAmountForKavaRewards()[valCnt - 1]
            } else if (type == COSMOS_MSG_TYPE_REDELEGATE2) {
                result = NSDecimalNumber.init(string: String(CERTIK_GAS_AMOUNT_REDELEGATE))
            } else if (type == COSMOS_MULTI_MSG_TYPE_REINVEST) {
                result = NSDecimalNumber.init(string: String(CERTIK_GAS_AMOUNT_REINVEST))
            } else if (type == COSMOS_MSG_TYPE_WITHDRAW_MIDIFY) {
                result = NSDecimalNumber.init(string: String(CERTIK_GAS_AMOUNT_REWARD_ADDRESS_CHANGE))
            } else if (type == TASK_TYPE_VOTE) {
                result = NSDecimalNumber.init(string: String(CERTIK_GAS_AMOUNT_VOTE))
            }
            
        } else if (chain == ChainType.SECRET_MAIN) {
            if (type == COSMOS_MSG_TYPE_TRANSFER2) {
                result = NSDecimalNumber.init(string: String(SECRET_GAS_AMOUNT_SEND))
            } else if (type == COSMOS_MSG_TYPE_DELEGATE || type == COSMOS_MSG_TYPE_UNDELEGATE2) {
                result = NSDecimalNumber.init(string: String(SECRET_GAS_AMOUNT_STAKE))
            } else if (type == COSMOS_MSG_TYPE_WITHDRAW_DEL) {
                result = getGasAmountForKavaRewards()[valCnt - 1]
            } else if (type == COSMOS_MSG_TYPE_REDELEGATE2) {
                result = NSDecimalNumber.init(string: String(SECRET_GAS_AMOUNT_REDELEGATE))
            } else if (type == COSMOS_MULTI_MSG_TYPE_REINVEST) {
                result = NSDecimalNumber.init(string: String(SECRET_GAS_AMOUNT_REINVEST))
            } else if (type == COSMOS_MSG_TYPE_WITHDRAW_MIDIFY) {
                result = NSDecimalNumber.init(string: String(SECRET_GAS_AMOUNT_REWARD_ADDRESS_CHANGE))
            } else if (type == TASK_TYPE_VOTE) {
                result = NSDecimalNumber.init(string: String(SECRET_GAS_AMOUNT_VOTE))
            }
            
        } else if (chain == ChainType.SENTINEL_MAIN) {
            if (type == COSMOS_MSG_TYPE_TRANSFER2) {
                result = NSDecimalNumber.init(string: String(SENTINEL_GAS_AMOUNT_SEND))
            } else if (type == COSMOS_MSG_TYPE_DELEGATE) {
                result = NSDecimalNumber.init(string: String(SENTINEL_GAS_AMOUNT_STAKE))
            } else if (type == COSMOS_MSG_TYPE_UNDELEGATE2) {
                result = NSDecimalNumber.init(string: String(SENTINEL_GAS_AMOUNT_STAKE))
            } else if (type == COSMOS_MSG_TYPE_REDELEGATE2) {
                result = NSDecimalNumber.init(string: String(SENTINEL_GAS_AMOUNT_REDELEGATE))
            } else if (type == COSMOS_MSG_TYPE_WITHDRAW_DEL) {
                result = getGasAmountForKavaRewards()[valCnt - 1]
            } else if (type == COSMOS_MULTI_MSG_TYPE_REINVEST) {
                result = NSDecimalNumber.init(string: String(SENTINEL_GAS_AMOUNT_REINVEST))
            } else if (type == COSMOS_MSG_TYPE_WITHDRAW_MIDIFY) {
                result = NSDecimalNumber.init(string: String(SENTINEL_GAS_AMOUNT_REWARD_ADDRESS_CHANGE))
            } else if (type == TASK_TYPE_VOTE) {
                result = NSDecimalNumber.init(string: String(SENTINEL_GAS_AMOUNT_VOTE))
            }
            
        } else if (chain == ChainType.FETCH_MAIN) {
            if (type == COSMOS_MSG_TYPE_TRANSFER2) {
                result = NSDecimalNumber.init(string: String(FETCH_GAS_AMOUNT_SEND))
            } else if (type == COSMOS_MSG_TYPE_DELEGATE) {
                result = NSDecimalNumber.init(string: String(FETCH_GAS_AMOUNT_STAKE))
            } else if (type == COSMOS_MSG_TYPE_UNDELEGATE2) {
                result = NSDecimalNumber.init(string: String(FETCH_GAS_AMOUNT_STAKE))
            } else if (type == COSMOS_MSG_TYPE_REDELEGATE2) {
                result = NSDecimalNumber.init(string: String(FETCH_GAS_AMOUNT_REDELEGATE))
            } else if (type == COSMOS_MSG_TYPE_WITHDRAW_DEL) {
                result = getGasAmountForKavaRewards()[valCnt - 1]
            } else if (type == COSMOS_MULTI_MSG_TYPE_REINVEST) {
                result = NSDecimalNumber.init(string: String(FETCH_GAS_AMOUNT_REINVEST))
            } else if (type == COSMOS_MSG_TYPE_WITHDRAW_MIDIFY) {
                result = NSDecimalNumber.init(string: String(FETCH_GAS_AMOUNT_REWARD_ADDRESS_CHANGE))
            } else if (type == TASK_TYPE_VOTE) {
                result = NSDecimalNumber.init(string: String(FETCH_GAS_AMOUNT_VOTE))
            }
            
        } else if (chain == ChainType.SIF_MAIN) {
            if (type == COSMOS_MSG_TYPE_TRANSFER2) {
                result = NSDecimalNumber.init(string: String(SIF_GAS_AMOUNT_SEND))
            } else if (type == COSMOS_MSG_TYPE_DELEGATE) {
                result = NSDecimalNumber.init(string: String(SIF_GAS_AMOUNT_STAKE))
            } else if (type == COSMOS_MSG_TYPE_UNDELEGATE2) {
                result = NSDecimalNumber.init(string: String(SIF_GAS_AMOUNT_STAKE))
            } else if (type == COSMOS_MSG_TYPE_REDELEGATE2) {
                result = NSDecimalNumber.init(string: String(SIF_GAS_AMOUNT_REDELEGATE))
            } else if (type == COSMOS_MSG_TYPE_WITHDRAW_DEL) {
                result = getGasAmountForKavaRewards()[valCnt - 1]
            } else if (type == COSMOS_MULTI_MSG_TYPE_REINVEST) {
                result = NSDecimalNumber.init(string: String(SIF_GAS_AMOUNT_REINVEST))
            } else if (type == COSMOS_MSG_TYPE_WITHDRAW_MIDIFY) {
                result = NSDecimalNumber.init(string: String(SIF_GAS_AMOUNT_REWARD_ADDRESS_CHANGE))
            } else if (type == TASK_TYPE_VOTE) {
                result = NSDecimalNumber.init(string: String(SIF_GAS_AMOUNT_VOTE))
            }
            
        } else if (chain == ChainType.KI_MAIN) {
            if (type == COSMOS_MSG_TYPE_TRANSFER2) {
               result = NSDecimalNumber.init(string: String(KI_GAS_AMOUNT_SEND))
            } else if (type == COSMOS_MSG_TYPE_DELEGATE) {
                result = NSDecimalNumber.init(string: String(KI_GAS_AMOUNT_STAKE))
            } else if (type == COSMOS_MSG_TYPE_UNDELEGATE2) {
                result = NSDecimalNumber.init(string: String(KI_GAS_AMOUNT_STAKE))
            } else if (type == COSMOS_MSG_TYPE_REDELEGATE2) {
                result = NSDecimalNumber.init(string: String(KI_GAS_AMOUNT_REDELEGATE))
            } else if (type == COSMOS_MSG_TYPE_WITHDRAW_DEL) {
                result = getGasAmountForKavaRewards()[valCnt - 1]
            } else if (type == COSMOS_MULTI_MSG_TYPE_REINVEST) {
                result = NSDecimalNumber.init(string: String(KI_GAS_AMOUNT_REINVEST))
            } else if (type == COSMOS_MSG_TYPE_WITHDRAW_MIDIFY) {
                result = NSDecimalNumber.init(string: String(KI_GAS_AMOUNT_REWARD_ADDRESS_CHANGE))
            } else if (type == TASK_TYPE_VOTE) {
                result = NSDecimalNumber.init(string: String(KI_GAS_AMOUNT_VOTE))
            }
            
        }
        return result
    }
    
    static func getEstimateGasFeeAmount(_ chain:ChainType, _ type:String,  _ valCnt:Int) -> NSDecimalNumber {
        if (chain == ChainType.COSMOS_MAIN || chain == ChainType.AKASH_MAIN || chain == ChainType.COSMOS_TEST) {
            let gasRate = NSDecimalNumber.init(string: GAS_FEE_RATE_AVERAGE)
            let gasAmount = getEstimateGasAmount(chain, type, valCnt)
            return gasRate.multiplying(by: gasAmount, withBehavior: handler0)
            
        } else if (chain == ChainType.IRIS_MAIN || chain == ChainType.IRIS_TEST) {
            let gasRate = NSDecimalNumber.init(string: GAS_FEE_RATE_AVERAGE_IRIS)
            let gasAmount = getEstimateGasAmount(chain, type, valCnt)
            return gasRate.multiplying(by: gasAmount, withBehavior: handler0)
            
        } else if (chain == ChainType.PERSIS_MAIN) {
            let gasRate = NSDecimalNumber.init(string: GAS_FEE_RATE_AVERAGE_PERSIS)
            let gasAmount = getEstimateGasAmount(chain, type, valCnt)
            return gasRate.multiplying(by: gasAmount, withBehavior: handler0)
            
        } else if (chain == ChainType.CRYPTO_MAIN) {
            let gasRate = NSDecimalNumber.init(string: GAS_FEE_RATE_TINY_CRYPTO)
            let gasAmount = getEstimateGasAmount(chain, type, valCnt)
            return gasRate.multiplying(by: gasAmount, withBehavior: handler0)
            
        }
        
        else if (chain == ChainType.BINANCE_MAIN || chain == ChainType.BINANCE_TEST) {
            return NSDecimalNumber.init(string: FEE_BNB_TRANSFER).rounding(accordingToBehavior: handler8)
            
        } else if (chain == ChainType.OKEX_MAIN || chain == ChainType.OKEX_TEST) {
            let gasRate = NSDecimalNumber.init(string: OK_GAS_RATE_AVERAGE)
            let gasAmount = getEstimateGasAmount(chain, type, valCnt)
            return gasRate.multiplying(by: gasAmount, withBehavior: handler18)
            
        }
        
        else if (chain == ChainType.KAVA_MAIN || chain == ChainType.KAVA_TEST) {
            return NSDecimalNumber.zero
            
        } else if (chain == ChainType.BAND_MAIN) {
            return NSDecimalNumber.zero
            
        } else if (chain == ChainType.IOV_MAIN || chain == ChainType.IOV_TEST) {
            let gasRate = NSDecimalNumber.init(string: IOV_GAS_RATE_AVERAGE)
            let gasAmount = getEstimateGasAmount(chain, type, valCnt)
            return gasRate.multiplying(by: gasAmount, withBehavior: handler0)
            
        } else if (chain == ChainType.CERTIK_MAIN || chain == ChainType.CERTIK_TEST) {
            let gasRate = NSDecimalNumber.init(string: CERTIK_GAS_RATE_AVERAGE)
            let gasAmount = getEstimateGasAmount(chain, type, valCnt)
            return gasRate.multiplying(by: gasAmount, withBehavior: handler0)
            
        } else if (chain == ChainType.SECRET_MAIN) {
            let gasRate = NSDecimalNumber.init(string: SECRET_GAS_FEE_RATE_AVERAGE)
            let gasAmount = getEstimateGasAmount(chain, type, valCnt)
            return gasRate.multiplying(by: gasAmount, withBehavior: handler0)
            
        } else if (chain == ChainType.SENTINEL_MAIN) {
            let gasRate = NSDecimalNumber.init(string: SENTINEL_GAS_FEE_RATE_AVERAGE)
            let gasAmount = getEstimateGasAmount(chain, type, valCnt)
            return gasRate.multiplying(by: gasAmount, withBehavior: handler0)
            
        } else if (chain == ChainType.FETCH_MAIN) {
            let gasRate = NSDecimalNumber.init(string: FETCH_GAS_FEE_RATE_AVERAGE)
            let gasAmount = getEstimateGasAmount(chain, type, valCnt)
            return gasRate.multiplying(by: gasAmount, withBehavior: handler0)
            
        } else if (chain == ChainType.SIF_MAIN) {
            let gasRate = NSDecimalNumber.init(string: SIF_GAS_FEE_RATE_AVERAGE)
            let gasAmount = getEstimateGasAmount(chain, type, valCnt)
            return gasRate.multiplying(by: gasAmount, withBehavior: handler0)
            
        } else if (chain == ChainType.KI_MAIN) {
            let gasRate = NSDecimalNumber.init(string: KI_GAS_FEE_RATE_AVERAGE)
            let gasAmount = getEstimateGasAmount(chain, type, valCnt)
            return gasRate.multiplying(by: gasAmount, withBehavior: handler0)
            
        }
        return NSDecimalNumber.zero
    }
    
    static func getGasRate(_ chain:ChainType, _ position: Int) -> NSDecimalNumber {
        if (chain == ChainType.COSMOS_MAIN || chain == ChainType.COSMOS_TEST) {
            if (position == 0) {
                return NSDecimalNumber.init(string: GAS_FEE_RATE_TINY)
            } else if (position == 1) {
                return NSDecimalNumber.init(string: GAS_FEE_RATE_LOW)
            } else {
                return NSDecimalNumber.init(string: GAS_FEE_RATE_AVERAGE)
            }
            
        } else if (chain == ChainType.IRIS_MAIN || chain == ChainType.IRIS_TEST) {
            if (position == 0) {
                return NSDecimalNumber.init(string: GAS_FEE_RATE_TINY_IRIS)
            } else if (position == 1) {
                return NSDecimalNumber.init(string: GAS_FEE_RATE_LOW_IRIS)
            } else {
                return NSDecimalNumber.init(string: GAS_FEE_RATE_AVERAGE_IRIS)
            }
            
        } else if (chain == ChainType.PERSIS_MAIN) {
            if (position == 0) {
                return NSDecimalNumber.init(string: GAS_FEE_RATE_TINY_PERSIS)
            } else if (position == 1) {
                return NSDecimalNumber.init(string: GAS_FEE_RATE_LOW_PERSIS)
            } else {
                return NSDecimalNumber.init(string: GAS_FEE_RATE_AVERAGE_PERSIS)
            }
            
        } else if (chain == ChainType.CRYPTO_MAIN) {
            if (position == 0) {
                return NSDecimalNumber.init(string: GAS_FEE_RATE_TINY_CRYPTO)
            } else if (position == 1) {
                return NSDecimalNumber.init(string: GAS_FEE_RATE_LOW_CRYPTO)
            } else {
                return NSDecimalNumber.init(string: GAS_FEE_RATE_AVERAGE_CRYPTO)
            }
            
        }
        
        else if (chain == ChainType.KAVA_MAIN || chain == ChainType.KAVA_TEST) {
            if (position == 0) {
                return NSDecimalNumber.zero
            } else if (position == 1) {
                return NSDecimalNumber.init(string: KAVA_GAS_RATE_LOW)
            } else {
                return NSDecimalNumber.init(string: KAVA_GAS_RATE_AVERAGE)
            }
            
        } else if (chain == ChainType.BAND_MAIN) {
            if (position == 0) {
                return NSDecimalNumber.zero
            } else if (position == 1) {
                return NSDecimalNumber.init(string: BAND_GAS_RATE_LOW)
            } else {
                return NSDecimalNumber.init(string: BAND_GAS_RATE_AVERAGE)
            }
            
        }
        
        else if (chain == ChainType.BINANCE_MAIN || chain == ChainType.BINANCE_TEST) {
            return NSDecimalNumber.zero
            
        } else if (chain == ChainType.IOV_MAIN || chain == ChainType.IOV_TEST) {
            return NSDecimalNumber.init(string: IOV_GAS_RATE_AVERAGE)
            
        } else if (chain == ChainType.OKEX_MAIN || chain == ChainType.OKEX_TEST) {
            return NSDecimalNumber.init(string: OK_GAS_RATE_AVERAGE)
            
        } else if (chain == ChainType.CERTIK_MAIN || chain == ChainType.CERTIK_TEST) {
            return NSDecimalNumber.init(string: CERTIK_GAS_RATE_AVERAGE)
            
        } else if (chain == ChainType.SECRET_MAIN) {
            return NSDecimalNumber.init(string: SECRET_GAS_FEE_RATE_AVERAGE)
            
        } else if (chain == ChainType.SENTINEL_MAIN) {
            return NSDecimalNumber.init(string: SENTINEL_GAS_FEE_RATE_AVERAGE)
            
        } else if (chain == ChainType.FETCH_MAIN) {
            return NSDecimalNumber.init(string: FETCH_GAS_FEE_RATE_AVERAGE)
            
        } else if (chain == ChainType.SIF_MAIN) {
            return NSDecimalNumber.init(string: SIF_GAS_FEE_RATE_AVERAGE)
            
        } else if (chain == ChainType.KI_MAIN) {
            return NSDecimalNumber.init(string: KI_GAS_FEE_RATE_AVERAGE)
            
        }
        return NSDecimalNumber.zero
    }
    
    static func getGasAmountForRewards() -> Array<NSDecimalNumber> {
        var gasAmounts = Array<NSDecimalNumber>()
        gasAmounts.append(NSDecimalNumber.init(string: FEE_REWARD_GAS_1))
        gasAmounts.append(NSDecimalNumber.init(string: FEE_REWARD_GAS_2))
        gasAmounts.append(NSDecimalNumber.init(string: FEE_REWARD_GAS_3))
        gasAmounts.append(NSDecimalNumber.init(string: FEE_REWARD_GAS_4))
        
        gasAmounts.append(NSDecimalNumber.init(string: FEE_REWARD_GAS_5))
        gasAmounts.append(NSDecimalNumber.init(string: FEE_REWARD_GAS_6))
        gasAmounts.append(NSDecimalNumber.init(string: FEE_REWARD_GAS_7))
        gasAmounts.append(NSDecimalNumber.init(string: FEE_REWARD_GAS_8))
        
        gasAmounts.append(NSDecimalNumber.init(string: FEE_REWARD_GAS_9))
        gasAmounts.append(NSDecimalNumber.init(string: FEE_REWARD_GAS_10))
        gasAmounts.append(NSDecimalNumber.init(string: FEE_REWARD_GAS_11))
        gasAmounts.append(NSDecimalNumber.init(string: FEE_REWARD_GAS_12))
        
        gasAmounts.append(NSDecimalNumber.init(string: FEE_REWARD_GAS_13))
        gasAmounts.append(NSDecimalNumber.init(string: FEE_REWARD_GAS_14))
        gasAmounts.append(NSDecimalNumber.init(string: FEE_REWARD_GAS_15))
        gasAmounts.append(NSDecimalNumber.init(string: FEE_REWARD_GAS_16))
        return gasAmounts
    }
    
    static func getGasAmountForKavaRewards() -> Array<NSDecimalNumber> {
        var gasAmounts = Array<NSDecimalNumber>()
        gasAmounts.append(NSDecimalNumber.init(string: FEE_KAVA_REWARD_GAS_1))
        gasAmounts.append(NSDecimalNumber.init(string: FEE_KAVA_REWARD_GAS_2))
        gasAmounts.append(NSDecimalNumber.init(string: FEE_KAVA_REWARD_GAS_3))
        gasAmounts.append(NSDecimalNumber.init(string: FEE_KAVA_REWARD_GAS_4))
        
        gasAmounts.append(NSDecimalNumber.init(string: FEE_KAVA_REWARD_GAS_5))
        gasAmounts.append(NSDecimalNumber.init(string: FEE_KAVA_REWARD_GAS_6))
        gasAmounts.append(NSDecimalNumber.init(string: FEE_KAVA_REWARD_GAS_7))
        gasAmounts.append(NSDecimalNumber.init(string: FEE_KAVA_REWARD_GAS_8))
        
        gasAmounts.append(NSDecimalNumber.init(string: FEE_KAVA_REWARD_GAS_9))
        gasAmounts.append(NSDecimalNumber.init(string: FEE_KAVA_REWARD_GAS_10))
        gasAmounts.append(NSDecimalNumber.init(string: FEE_KAVA_REWARD_GAS_11))
        gasAmounts.append(NSDecimalNumber.init(string: FEE_KAVA_REWARD_GAS_12))
        
        gasAmounts.append(NSDecimalNumber.init(string: FEE_KAVA_REWARD_GAS_13))
        gasAmounts.append(NSDecimalNumber.init(string: FEE_KAVA_REWARD_GAS_14))
        gasAmounts.append(NSDecimalNumber.init(string: FEE_KAVA_REWARD_GAS_15))
        gasAmounts.append(NSDecimalNumber.init(string: FEE_KAVA_REWARD_GAS_16))
        return gasAmounts
    }
    
    static func getKavaCoinDecimal(_ denom:String) -> Int16 {
        if (denom.caseInsensitiveCompare(KAVA_MAIN_DENOM) == .orderedSame) {
            return 6;
        } else if (denom.caseInsensitiveCompare("btc") == .orderedSame) {
            return 8;
        } else if (denom.caseInsensitiveCompare("usdx") == .orderedSame) {
            return 6;
        } else if (denom.caseInsensitiveCompare("bnb") == .orderedSame) {
            return 8;
        } else if (denom.caseInsensitiveCompare("btcb") == .orderedSame || denom.caseInsensitiveCompare("hbtc") == .orderedSame) {
            return 8;
        } else if (denom.caseInsensitiveCompare("busd") == .orderedSame) {
            return 8;
        } else if (denom.caseInsensitiveCompare("xrpb") == .orderedSame || denom.caseInsensitiveCompare("xrbp") == .orderedSame) {
            return 8;
        } else if (denom.caseInsensitiveCompare("hard") == .orderedSame) {
            return 6;
        }
        return 100;
    }
    
    static func getSifCoinDecimal(_ denom:String?) -> Int16 {
        if (denom?.caseInsensitiveCompare(SIF_MAIN_DENOM) == .orderedSame) { return 18; }
        else if (denom?.caseInsensitiveCompare("cusdt") == .orderedSame) { return 6; }
        else if (denom?.caseInsensitiveCompare("cusdc") == .orderedSame) { return 6; }
        else if (denom?.caseInsensitiveCompare("csrm") == .orderedSame) { return 6; }
        else if (denom?.caseInsensitiveCompare("cwscrt") == .orderedSame) { return 6; }
        else if (denom?.caseInsensitiveCompare("ccro") == .orderedSame) { return 8; }
        else if (denom?.caseInsensitiveCompare("cwbtc") == .orderedSame) { return 8; }
        return 18;
    }
    
    static func getVoterTypeCnt(_ votes: Array<Vote>, _ type: String) -> String {
        var result = 0
        for vote in votes {
            if (vote.option == type) {
                result = result + 1
            }
        }
        return String(result)
    }
    
    static func getDPRawDollor(_ price:String, _ scale:Int, _ font:UIFont) -> NSMutableAttributedString {
        let nf = NumberFormatter()
        nf.minimumFractionDigits = scale
        nf.maximumFractionDigits = scale
        nf.numberStyle = .decimal
        
        let handler = NSDecimalNumberHandler(roundingMode: NSDecimalNumber.RoundingMode.down, scale: Int16(scale), raiseOnExactness: false, raiseOnOverflow: false, raiseOnUnderflow: false, raiseOnDivideByZero: true)
        let amount = plainStringToDecimal(price).rounding(accordingToBehavior: handler)
        
        let added       = "$ " + nf.string(from: amount)!
        let endIndex    = added.index(added.endIndex, offsetBy: -scale)
        
        let preString   = added[..<endIndex]
        let postString  = added[endIndex...]
        
        let preAttrs = [NSAttributedString.Key.font : font]
        let postAttrs = [NSAttributedString.Key.font : font.withSize(CGFloat(Int(Double(font.pointSize) * 0.85)))]
        
        let attributedString1 = NSMutableAttributedString(string:String(preString), attributes:preAttrs as [NSAttributedString.Key : Any])
        let attributedString2 = NSMutableAttributedString(string:String(postString), attributes:postAttrs as [NSAttributedString.Key : Any])
        
        attributedString1.append(attributedString2)
        return attributedString1
    }
    
    static func getRiskColor(_ riskRate: NSDecimalNumber) -> UIColor {
        if (riskRate.doubleValue <= 50) {
            return COLOR_CDP_SAFE
        } else if (riskRate.doubleValue < 80) {
            return COLOR_CDP_STABLE
        } else {
            return COLOR_CDP_DANGER
        }
    }
    
    static func showRiskRate(_ riskRate: NSDecimalNumber, _ scoreLabel: UILabel, _rateIamg:UIImageView?) {
        scoreLabel.attributedText = displayAmount2(riskRate.stringValue, scoreLabel.font, 0, 2)
        if (riskRate.floatValue <= 50) {
            scoreLabel.textColor = COLOR_CDP_SAFE
            _rateIamg?.image = UIImage(named: "safe")
            
        } else if (riskRate.floatValue < 80) {
            scoreLabel.textColor = COLOR_CDP_STABLE
            _rateIamg?.image = UIImage(named: "stable")
            
        } else {
            scoreLabel.textColor = COLOR_CDP_DANGER
            _rateIamg?.image = UIImage(named: "danger")
        }
    }
    
    static func showRiskRate2(_ riskRate: NSDecimalNumber, _ scoreLabel: UILabel, _ textLabel:UILabel) {
        scoreLabel.attributedText = displayAmount2(riskRate.stringValue, scoreLabel.font, 0, 2)
        if (riskRate.doubleValue <= 50) {
            scoreLabel.textColor = COLOR_CDP_SAFE
            textLabel.textColor = COLOR_CDP_SAFE
            textLabel.text = "SAFE"
            
        } else if (riskRate.doubleValue < 80) {
            scoreLabel.textColor = COLOR_CDP_STABLE
            textLabel.textColor = COLOR_CDP_STABLE
            textLabel.text = "STABLE"
            
        } else {
            scoreLabel.textColor = COLOR_CDP_DANGER
            textLabel.textColor = COLOR_CDP_DANGER
            textLabel.text = "DANGER"
        }
    }
    
    static func showRiskRate3(_ riskRate: NSDecimalNumber, _ scoreLabel: UILabel, _ textLabel:UILabel, _ cardView:CardView) {
        scoreLabel.attributedText = displayAmount2(riskRate.stringValue, scoreLabel.font, 0, 2)
        if (riskRate.doubleValue <= 50) {
            textLabel.text = "SAFE"
            cardView.backgroundColor = COLOR_CDP_SAFE
            
        } else if (riskRate.doubleValue < 80) {
            textLabel.text = "STABLE"
            cardView.backgroundColor = COLOR_CDP_STABLE
            
        } else {
            textLabel.text = "DANGER"
            cardView.backgroundColor = COLOR_CDP_DANGER
        }
    }
    
    static func dpChainInfo(_ chain: ChainType, _ img: UIImageView?, _ label: UILabel) {
        if (chain == ChainType.COSMOS_MAIN) {
            label.text = NSLocalizedString("chain_title_cosmos", comment: "")
            img?.image = UIImage(named: "cosmosWhMain")
        } else if (chain == ChainType.IRIS_MAIN) {
            label.text = NSLocalizedString("chain_title_iris", comment: "")
            img?.image = UIImage(named: "irisWh")
        } else if (chain == ChainType.BINANCE_MAIN) {
            label.text = NSLocalizedString("chain_title_bnb", comment: "")
            img?.image = UIImage(named: "binanceChImg")
        } else if (chain == ChainType.KAVA_MAIN) {
            label.text = NSLocalizedString("chain_title_kava", comment: "")
           img?.image = UIImage(named: "kavaImg")
        } else if (chain == ChainType.IOV_MAIN) {
            label.text = NSLocalizedString("chain_title_iov", comment: "")
            img?.image = UIImage(named: "iovImg")
        } else if (chain == ChainType.BAND_MAIN) {
            label.text = NSLocalizedString("chain_title_band", comment: "")
            img?.image = UIImage(named: "bandChainImg")
        } else if (chain == ChainType.BINANCE_TEST) {
            label.text = NSLocalizedString("chain_title_test_bnb", comment: "")
            img?.image = UIImage(named: "binancetestnet")
        } else if (chain == ChainType.KAVA_TEST) {
            label.text = NSLocalizedString("chain_title_kava_test", comment: "")
            img?.image = UIImage(named: "kavaTestImg")
        }
    }
    
    static func dpChainName(_ chain: ChainType) -> String {
        if (chain == ChainType.COSMOS_MAIN) {
            return NSLocalizedString("chain_title_cosmos", comment: "")
        } else if (chain == ChainType.IRIS_MAIN) {
            return NSLocalizedString("chain_title_iris", comment: "")
        } else if (chain == ChainType.BINANCE_MAIN) {
            return NSLocalizedString("chain_title_bnb", comment: "")
        } else if (chain == ChainType.KAVA_MAIN) {
            return NSLocalizedString("chain_title_kava", comment: "")
        } else if (chain == ChainType.IOV_MAIN) {
            return NSLocalizedString("chain_title_iov", comment: "")
        } else if (chain == ChainType.BAND_MAIN) {
            return NSLocalizedString("chain_title_band", comment: "")
        } else if (chain == ChainType.BINANCE_TEST) {
            return NSLocalizedString("chain_title_test_bnb", comment: "")
        } else if (chain == ChainType.KAVA_TEST) {
            return NSLocalizedString("chain_title_kava_test", comment: "")
        }
        return ""
    }
    
    static func onDpSwapChain(_ chain: ChainType, _ img: UIImageView?, _ label: UILabel) {
        if (chain == ChainType.BINANCE_MAIN) {
            label.text = "BINANCE"
            img?.image = UIImage(named: "binanceChImg")
        } else if (chain == ChainType.KAVA_MAIN) {
            label.text = "KAVA"
           img?.image = UIImage(named: "kavaImg")
        } else if (chain == ChainType.BINANCE_TEST) {
            label.text = "BINANCE"
            img?.image = UIImage(named: "binancetestnet")
        } else if (chain == ChainType.KAVA_TEST) {
            label.text = "KAVA"
            img?.image = UIImage(named: "kavaTestImg")
        }
    }
    
    static func getKavaHtlcStatus(_ txInfo:TxInfo, _ swap:KavaSwapInfo?) -> String {
        if (!txInfo.isSuccess()) {
            return NSLocalizedString("bep3_status_open", comment: "")
        }
        
        if (swap == nil) {
            return NSLocalizedString("bep3_status_completed", comment: "")
        }
        
        if (swap?.result.status == KavaSwapInfo.STATUS_EXPIRED) {
            return NSLocalizedString("bep3_status_expired", comment: "")
        } else if (swap?.result.status == KavaSwapInfo.STATUS_OPEN) {
            return NSLocalizedString("bep3_status_open", comment: "")
        } else {
            return NSLocalizedString("bep3_status_completed", comment: "")
        }
    }
    
    static func getBnbHtlcStatus(_ swap:BnbSwapInfo?, _ node:BnbNodeInfo?) -> String {
        if (swap == nil || node == nil) {
            return "-"
        }
        
        if (swap?.status == BnbSwapInfo.BNB_STATUS_REFUNDED) {
            return NSLocalizedString("bep3_status_refunded", comment: "")
        } else if (swap?.status == BnbSwapInfo.BNB_STATUS_COMPLETED) {
            return NSLocalizedString("bep3_status_completed", comment: "")
        } else if (swap?.status == BnbSwapInfo.BNB_STATUS_OPEN && swap!.expireHeight < node!.getCHeight()) {
            return NSLocalizedString("bep3_status_expired", comment: "")
        }
        return NSLocalizedString("bep3_status_open", comment: "")
    }
    
    static func isDisplayEventCard(_ chainType: ChainType?) -> Bool {
        if (chainType == ChainType.COSMOS_MAIN) {
            if (BaseData.instance.mHeight > PERSISTENCE_COSMOS_EVENT_START &&
                    BaseData.instance.mHeight < PERSISTENCE_COSMOS_EVENT_END &&
                    BaseData.instance.getEventTime()) {
                return true
            }
        } else if (chainType == ChainType.KAVA_MAIN) {
            if (BaseData.instance.mHeight > PERSISTENCE_KAVA_EVENT_START &&
                    BaseData.instance.mHeight < PERSISTENCE_KAVA_EVENT_END &&
                    BaseData.instance.getEventTime()) {
                return true
            }
        }
        return false
    }
    
    static func getStarNameChainImg(_ resource: StarNameResource) -> UIImage? {
        if (resource.uri == BITCOINCASH) {
            return UIImage.init(named: "bcashChainImg")
        } else if (resource.uri == BITCOIN) {
            return UIImage.init(named: "bitcoinChainImg")
        } else if (resource.uri == LITECOIN) {
            return UIImage.init(named: "liteChainImg")
        } else if (resource.uri == BINANCE) {
            return UIImage.init(named: "binanceChImg")
        } else if (resource.uri == LUNA) {
            return UIImage.init(named: "terraChainImg")
        } else if (resource.uri == COSMOS) {
            return UIImage.init(named: "cosmosWhMain")
        } else if (resource.uri == EMONEY) {
            return UIImage.init(named: "emoneyChainImg")
        } else if (resource.uri == IRIS) {
            return UIImage.init(named: "irisWh")
        } else if (resource.uri == KAVA) {
            return UIImage.init(named: "kavaImg")
        } else if (resource.uri == ETHEREUM) {
            return UIImage.init(named: "ethereumChainImg")
        } else if (resource.uri == STARNAME) {
            return UIImage.init(named: "iovChainImg")
        } else if (resource.uri == BAND) {
            return UIImage.init(named: "bandChainImg")
        } else if (resource.uri == TEZOS) {
            return UIImage.init(named: "tezosChainImg")
        } else if (resource.uri == LISK) {
            return UIImage.init(named: "liskChainImg")
        } else {
            return UIImage.init(named: "defaultChainImg")
        }
    }
    
    static func getStarNameChainName(_ resource: StarNameResource) -> String? {
        if (resource.uri == BITCOINCASH) {
            return "Bitcoin Cash";
        } else if (resource.uri == BITCOIN) {
            return "Bitcoin";
        } else if (resource.uri == LITECOIN) {
            return "Litecoin";
        } else if (resource.uri == BINANCE) {
            return "Binance";
        } else if (resource.uri == LUNA) {
            return "Terra";
        } else if (resource.uri == COSMOS) {
            return "Cosmos";
        } else if (resource.uri == EMONEY) {
            return "E-Money";
        } else if (resource.uri == IRIS) {
            return "Iris";
        } else if (resource.uri == KAVA) {
            return "Kava";
        } else if (resource.uri == ETHEREUM) {
            return "Ethereum";
        } else if (resource.uri == STARNAME) {
            return "Starname";
        } else if (resource.uri == BAND) {
            return "Band";
        } else if (resource.uri == TEZOS) {
            return "Tezos";
        } else if (resource.uri == LISK) {
            return "Lisk";
        } else {
            return resource.uri;
        }
    }
    
    static func getStarNameAllResources() -> Array<StarNameResource> {
        var result: Array<StarNameResource> = Array<StarNameResource>()
        result.append(StarNameResource.init(STARNAME))
        result.append(StarNameResource.init(COSMOS))
        result.append(StarNameResource.init(BITCOIN))
        result.append(StarNameResource.init(ETHEREUM))
        result.append(StarNameResource.init(BINANCE))
        result.append(StarNameResource.init(IRIS))
        result.append(StarNameResource.init(KAVA))
        result.append(StarNameResource.init(BAND))
        result.append(StarNameResource.init(BITCOINCASH))
        result.append(StarNameResource.init(LITECOIN))
        result.append(StarNameResource.init(EMONEY))
        result.append(StarNameResource.init(TEZOS))
        result.append(StarNameResource.init(LISK))
        result.append(StarNameResource.init(LUNA))
        return result
    }
    
    static func getChainTypeWithUri(_ uri: String?) -> ChainType? {
        if (uri == COSMOS) {
            return ChainType.COSMOS_MAIN
        } else if (uri == IRIS) {
            return ChainType.IRIS_MAIN
        } else if (uri == BINANCE) {
            return ChainType.BINANCE_MAIN
        } else if (uri == STARNAME) {
            return ChainType.IOV_MAIN
        } else if (uri == KAVA) {
            return ChainType.KAVA_MAIN
        } else if (uri == BAND) {
            return ChainType.BAND_MAIN
        }
        return nil
    }
    
    static func getExportResource(_ accounts: Array<Account>) -> ExportStarname {
        var result = ExportStarname.init()
        result.type = "starname"
        accounts.forEach { (account) in
            var resource = ExportStarname.ExportResource.init()
            if (WUtils.getChainType(account.account_base_chain) == ChainType.COSMOS_MAIN) {
                resource.ticker = "atom"
                resource.address = account.account_address
                result.addresses.append(resource)
            } else if (WUtils.getChainType(account.account_base_chain) == ChainType.IRIS_MAIN) {
                resource.ticker = "iris"
                resource.address = account.account_address
                result.addresses.append(resource)
            } else if (WUtils.getChainType(account.account_base_chain) == ChainType.KAVA_MAIN) {
                resource.ticker = "kava"
                resource.address = account.account_address
                result.addresses.append(resource)
            } else if (WUtils.getChainType(account.account_base_chain) == ChainType.BINANCE_MAIN) {
                resource.ticker = "bnb"
                resource.address = account.account_address
                result.addresses.append(resource)
            } else if (WUtils.getChainType(account.account_base_chain) == ChainType.IOV_MAIN) {
                resource.ticker = "iov"
                resource.address = account.account_address
                result.addresses.append(resource)
            } else if (WUtils.getChainType(account.account_base_chain) == ChainType.BAND_MAIN) {
                resource.ticker = "band"
                resource.address = account.account_address
                result.addresses.append(resource)
            }
        }
        return result;
        
    }
    
    static func getCBlockTime(_ chain: ChainType?) -> NSDecimalNumber {
        if (chain == ChainType.COSMOS_MAIN || chain == ChainType.COSMOS_TEST) {
            return BLOCK_TIME_COSMOS
            
        } else if (chain == ChainType.IRIS_MAIN || chain == ChainType.IRIS_TEST) {
            return BLOCK_TIME_IRIS
            
        } else if (chain == ChainType.IOV_MAIN) {
            return BLOCK_TIME_IOV
            
        } else if (chain == ChainType.KAVA_MAIN) {
            return BLOCK_TIME_KAVA
            
        } else if (chain == ChainType.BAND_MAIN) {
            return BLOCK_TIME_BAND
            
        } else if (chain == ChainType.CERTIK_MAIN) {
            return BLOCK_TIME_CERTIK
            
        } else if (chain == ChainType.SECRET_MAIN) {
            return BLOCK_TIME_SECRET
            
        } else if (chain == ChainType.AKASH_MAIN) {
            return BLOCK_TIME_AKASH
            
        } else if (chain == ChainType.SENTINEL_MAIN) {
            return BLOCK_TIME_SENTINEL
            
        } else if (chain == ChainType.PERSIS_MAIN) {
            return BLOCK_TIME_PERSISTENCE
            
        } else if (chain == ChainType.FETCH_MAIN) {
            return BLOCK_TIME_FETCH
            
        } else if (chain == ChainType.CRYPTO_MAIN) {
            return BLOCK_TIME_CRYPTO
            
        } else if (chain == ChainType.SIF_MAIN) {
            return BLOCK_TIME_SIF
            
        } else if (chain == ChainType.KI_MAIN) {
            return BLOCK_TIME_KI
            
        }
        return NSDecimalNumber.init(string: "6")
    }
    
    static func getMonikerImgUrl(_ chain: ChainType?, _ opAddress: String) -> String {
        if (chain == ChainType.COSMOS_MAIN || chain == ChainType.COSMOS_TEST) {
            return COSMOS_VAL_URL + opAddress + ".png";
        } else if (chain == ChainType.IRIS_MAIN || chain == ChainType.IRIS_TEST) {
            return IRIS_VAL_URL + opAddress + ".png";
        } else if (chain == ChainType.AKASH_MAIN) {
            return AKASH_VAL_URL + opAddress + ".png";
        } else if (chain == ChainType.PERSIS_MAIN) {
            return PERSIS_VAL_URL + opAddress + ".png";
        }
        else if (chain == ChainType.KAVA_MAIN || chain == ChainType.KAVA_TEST) {
            return KAVA_VAL_URL + opAddress + ".png";
        } else if (chain == ChainType.BAND_MAIN) {
            return BAND_VAL_URL + opAddress + ".png";
        } else if (chain == ChainType.IOV_MAIN || chain == ChainType.IOV_TEST) {
            return IOV_VAL_URL + opAddress + ".png";
        } else if (chain == ChainType.CERTIK_MAIN || chain == ChainType.CERTIK_TEST) {
            return CERTIK_VAL_URL + opAddress + ".png";
        } else if (chain == ChainType.SECRET_MAIN) {
            return SECRET_VAL_URL + opAddress + ".png";
        } else if (chain == ChainType.SENTINEL_MAIN) {
            return SENTINEL_VAL_URL + opAddress + ".png";
        } else if (chain == ChainType.OKEX_MAIN || chain == ChainType.OKEX_TEST) {
            return OKEX_VAL_URL + opAddress + ".png";
        } else if (chain == ChainType.FETCH_MAIN) {
            return FETCH_VAL_URL + opAddress + ".png";
        } else if (chain == ChainType.CRYPTO_MAIN) {
            return CRYPTO_VAL_URL + opAddress + ".png";
        } else if (chain == ChainType.SIF_MAIN) {
            return SIF_VAL_URL + opAddress + ".png";
        } else if (chain == ChainType.KI_MAIN) {
            return KI_VAL_URL + opAddress + ".png";
        }
        return ""
    }
    
    static func getTxExplorer(_ chain: ChainType, _ hash: String) -> String {
        if (chain == ChainType.COSMOS_MAIN) {
            return EXPLORER_COSMOS_MAIN + "txs/" + hash
            
        } else if (chain == ChainType.IRIS_MAIN) {
            return EXPLORER_IRIS_MAIN + "txs/" + hash
            
        } else if (chain == ChainType.AKASH_MAIN) {
            return EXPLORER_AKASH_MAIN + "txs/" + hash
            
        } else if (chain == ChainType.PERSIS_MAIN) {
            return EXPLORER_PERSIS_MAIN + "txs/" + hash
            
        } else if (chain == ChainType.CRYPTO_MAIN) {
            return EXPLORER_CRYPTO_MAIN + "txs/" + hash
            
        }
        
        else if (chain == ChainType.COSMOS_TEST) {
            return EXPLORER_COSMOS_TEST + "txs/" + hash
            
        } else if (chain == ChainType.IRIS_TEST) {
            return EXPLORER_IRIS_TEST + "txs/" + hash
            
        }
        
        if (chain == ChainType.BINANCE_MAIN) {
            return EXPLORER_BINANCE_MAIN + "txs/" + hash
            
        } else if (chain == ChainType.KAVA_MAIN) {
            return EXPLORER_KAVA_MAIN + "txs/" + hash
            
        } else if (chain == ChainType.BAND_MAIN) {
            return EXPLORER_BAND_MAIN + "tx/" + hash
            
        } else if (chain == ChainType.SECRET_MAIN) {
            return EXPLORER_SECRET_MAIN + "transactions/" + hash
            
        } else if (chain == ChainType.IOV_MAIN) {
            return EXPLORER_IOV_MAIN + "txs/" + hash
            
        } else if (chain == ChainType.OKEX_MAIN) {
            return EXPLORER_OKEX_MAIN + "tx/" + hash
            
        } else if (chain == ChainType.CERTIK_MAIN || chain == ChainType.CERTIK_TEST) {
            return EXPLORER_CERTIK + "Transactions/" + hash + "?net=" + BaseData.instance.getChainId()
            
        } else if (chain == ChainType.SENTINEL_MAIN) {
            return EXPLORER_SENTINEL_MAIN + "txs/" + hash
            
        } else if (chain == ChainType.FETCH_MAIN) {
            return EXPLORER_FETCH_MAIN + "txs/" + hash
            
        } else if (chain == ChainType.SIF_MAIN) {
            return EXPLORER_SIF_MAIN + "txs/" + hash
            
        } else if (chain == ChainType.KI_MAIN) {
            return EXPLORER_KI_MAIN + "txs/" + hash
            
        }
        
        return ""
    }
    
    static func getAccountExplorer(_ chain: ChainType, _ address: String) -> String {
        if (chain == ChainType.COSMOS_MAIN) {
            return EXPLORER_COSMOS_MAIN + "account/" + address
            
        } else if (chain == ChainType.IRIS_MAIN) {
            return EXPLORER_IRIS_MAIN + "account/" + address
            
        } else if (chain == ChainType.AKASH_MAIN) {
            return EXPLORER_AKASH_MAIN + "account/" + address
            
        } else if (chain == ChainType.KAVA_MAIN) {
            return EXPLORER_KAVA_MAIN + "account/" + address
            
        } else if (chain == ChainType.BAND_MAIN) {
            return EXPLORER_BAND_MAIN + "account/" + address
            
        } else if (chain == ChainType.SECRET_MAIN) {
            return EXPLORER_SECRET_MAIN + "accounts/" + address
            
        } else if (chain == ChainType.CERTIK_MAIN) {
            return EXPLORER_CERTIK + "accounts/" + address + "?net=" + BaseData.instance.getChainId()
            
        } else if (chain == ChainType.IOV_MAIN) {
            return EXPLORER_IOV_MAIN + "account/" + address
            
        } else if (chain == ChainType.PERSIS_MAIN) {
            return EXPLORER_PERSIS_MAIN + "account/" + address
            
        } else if (chain == ChainType.SENTINEL_MAIN) {
            return EXPLORER_SENTINEL_MAIN + "account/" + address
            
        } else if (chain == ChainType.BINANCE_MAIN) {
            return EXPLORER_BINANCE_MAIN + "account/" + address
            
        } else if (chain == ChainType.OKEX_MAIN) {
            return EXPLORER_OKEX_MAIN + "address/" + address
            
        } else if (chain == ChainType.FETCH_MAIN) {
            return EXPLORER_FETCH_MAIN + "account/" + address
            
        } else if (chain == ChainType.CRYPTO_MAIN) {
            return EXPLORER_CRYPTO_MAIN + "account/" + address
            
        } else if (chain == ChainType.SIF_MAIN) {
            return EXPLORER_SIF_MAIN + "account/" + address
            
        } else if (chain == ChainType.KI_MAIN) {
            return EXPLORER_KI_MAIN + "account/" + address
            
        }
        
        else if (chain == ChainType.COSMOS_TEST) {
            return EXPLORER_COSMOS_TEST + "account/" + address
            
        } else if (chain == ChainType.IRIS_TEST) {
            return EXPLORER_IRIS_TEST + "address/" + address
            
        } else if (chain == ChainType.CERTIK_TEST) {
            return EXPLORER_CERTIK + "address/" + address + "?net=" + BaseData.instance.getChainId()
            
        } else if (chain == ChainType.BINANCE_TEST) {
            return EXPLORER_BINANCE_TEST + "accounts/" + address
            
        } else if (chain == ChainType.OKEX_TEST) {
            return EXPLORER_OKEX_TEST + "address/" + address
            
        } else if (chain == ChainType.KAVA_TEST) {
            return EXPLORER_KAVA_TEST + "account/" + address
            
        }
        return ""
    }
    
    static func getProposalExplorer(_ chain: ChainType, _ proposalId: String) -> String {
        if (chain == ChainType.COSMOS_MAIN) {
            return EXPLORER_COSMOS_MAIN + "proposals/" + proposalId
            
        } else if (chain == ChainType.IRIS_MAIN) {
            return EXPLORER_IRIS_MAIN + "proposals/" + proposalId
            
        } else if (chain == ChainType.AKASH_MAIN) {
            return EXPLORER_AKASH_MAIN + "proposals/" + proposalId
            
        } else if (chain == ChainType.KAVA_MAIN) {
            return EXPLORER_KAVA_MAIN + "proposals/" + proposalId
            
        } else if (chain == ChainType.BAND_MAIN) {
            return EXPLORER_BAND_MAIN + "proposal/" + proposalId
            
        } else if (chain == ChainType.SECRET_MAIN) {
            return EXPLORER_SECRET_MAIN + "governance/proposals/" + proposalId
            
        } else if (chain == ChainType.CERTIK_MAIN) {
            return EXPLORER_CERTIK + "governance/proposals/" + proposalId + "?net=" + BaseData.instance.getChainId()
            
        } else if (chain == ChainType.IOV_MAIN) {
            return EXPLORER_IOV_MAIN + "proposals/" + proposalId
            
        } else if (chain == ChainType.PERSIS_MAIN) {
            return EXPLORER_PERSIS_MAIN + "proposals/" + proposalId
            
        } else if (chain == ChainType.SENTINEL_MAIN) {
            return EXPLORER_SENTINEL_MAIN + "proposals/" + proposalId
            
        } else if (chain == ChainType.FETCH_MAIN) {
            return EXPLORER_FETCH_MAIN + "proposals/" + proposalId
            
        } else if (chain == ChainType.CRYPTO_MAIN) {
            return EXPLORER_CRYPTO_MAIN + "proposals/" + proposalId
            
        } else if (chain == ChainType.SIF_MAIN) {
            return EXPLORER_SIF_MAIN + "proposals/" + proposalId
            
        } else if (chain == ChainType.KI_MAIN) {
            return EXPLORER_KI_MAIN + "proposals/" + proposalId
            
        }
        
        else if (chain == ChainType.COSMOS_TEST) {
            return EXPLORER_COSMOS_TEST + "proposals/" + proposalId
            
        } else if (chain == ChainType.IRIS_TEST) {
            return EXPLORER_IRIS_TEST + "proposals/" + proposalId
            
        } else if (chain == ChainType.CERTIK_TEST) {
            return EXPLORER_CERTIK + "governance/proposals/" + proposalId + "?net=" + BaseData.instance.getChainId()
        }
        return ""
    }
    
    static func systemQuorum(_ chain: ChainType?) -> NSDecimalNumber {
        if (chain == ChainType.IRIS_MAIN || chain == ChainType.IRIS_TEST) {
            return NSDecimalNumber.init(string: "0.5")
        } else if (chain == ChainType.AKASH_MAIN || chain == ChainType.SENTINEL_MAIN || chain == ChainType.IOV_MAIN ||
                    chain == ChainType.CERTIK_MAIN || chain == ChainType.SECRET_MAIN || chain == ChainType.CRYPTO_MAIN ||
                    chain == ChainType.SIF_MAIN || chain == ChainType.KI_MAIN) {
            return NSDecimalNumber.init(string: "0.334")
        }
        return NSDecimalNumber.init(string: "0.4")
    }
    
    static func getHardSuppliedAmountByDenom(_ denom: String, _ mydeposit: Array<HardMyDeposit>?) -> NSDecimalNumber {
        var result = NSDecimalNumber.zero
        if let deposit = mydeposit?[0], let coins = deposit.amount {
            for coin in coins {
                if (coin.denom == denom) {
                    result = NSDecimalNumber.init(string: coin.amount)
                }
            }
        }
        return result
    }
    
    static func getHardBorrowedValueByDenom(_ denom: String, _ mydeposit: Array<HardMyBorrow>?) -> NSDecimalNumber {
        let denomPrice = getKavaPrice(denom)
        let decimal = getKavaCoinDecimal(denom)
        let amount = getHardBorrowedAmountByDenom(denom, mydeposit)
        return amount.multiplying(byPowerOf10: -decimal).multiplying(by: denomPrice, withBehavior: WUtils.handler2Down)
    }
    
    static func getHardBorrowableAmountByDenom(_ denom: String, _ mydeposit: Array<HardMyDeposit>?, _ myBorrow: Array<HardMyBorrow>?,
                                               _ moduleCoins: Array<Coin>?, _ reserveCoins: Array<Coin>?) -> NSDecimalNumber {
        var totalLTVValue = NSDecimalNumber.zero
        var totalBorrowedValue = NSDecimalNumber.zero
        var totalBorrowAbleAmount = NSDecimalNumber.zero
        
        var SystemBorrowableAmount = NSDecimalNumber.zero
        var moduleAmount = NSDecimalNumber.zero
        var reserveAmount = NSDecimalNumber.zero
        
        let hardParam = BaseData.instance.mHardParam
        let hardMoneyMarket = hardParam?.getHardMoneyMarket(denom)
        let denomPrice = getKavaPrice(denom)
        let decimal = getKavaCoinDecimal(denom)
        
        mydeposit?[0].amount?.forEach({ coin in
            let innnerDecimal   = getKavaCoinDecimal(coin.denom)
            let LTV             = hardParam!.getLTV(coin.denom)
            var depositValue    = NSDecimalNumber.zero
            var LTVValue        = NSDecimalNumber.zero
            if (coin.denom == "usdx") {
                depositValue = NSDecimalNumber.init(string: coin.amount).multiplying(byPowerOf10: -innnerDecimal)
                
            } else {
                let innerPrice = getKavaPrice(coin.denom)
                depositValue = NSDecimalNumber.init(string: coin.amount).multiplying(byPowerOf10: -innnerDecimal).multiplying(by: innerPrice, withBehavior: WUtils.handler2Down)
                
            }
            LTVValue = depositValue.multiplying(by: LTV)
            totalLTVValue = totalLTVValue.adding(LTVValue)
        })
        
        myBorrow?[0].amount?.forEach({ coin in
            let innnerDecimal   = getKavaCoinDecimal(coin.denom)
            var borrowedValue   = NSDecimalNumber.zero
            if (coin.denom == "usdx") {
                borrowedValue = NSDecimalNumber.init(string: coin.amount).multiplying(byPowerOf10: -innnerDecimal)
                
            } else {
                let innerPrice = getKavaPrice(coin.denom)
                borrowedValue = NSDecimalNumber.init(string: coin.amount).multiplying(byPowerOf10: -innnerDecimal).multiplying(by: innerPrice, withBehavior: WUtils.handler2Down)
                
            }
            totalBorrowedValue = totalBorrowedValue.adding(borrowedValue)
        })
        let tempBorrowAbleValue  = totalLTVValue.subtracting(totalBorrowedValue)
        let totalBorrowAbleValue = tempBorrowAbleValue.compare(NSDecimalNumber.zero).rawValue > 0 ? tempBorrowAbleValue : NSDecimalNumber.zero
        totalBorrowAbleAmount = totalBorrowAbleValue.multiplying(byPowerOf10: decimal).dividing(by: denomPrice, withBehavior: getDivideHandler(decimal))
//        print("totalBorrowAbleAmount ", totalBorrowAbleAmount)
        
        
        if let moduleCoin = moduleCoins?.filter({ $0.denom == denom}).first {
            moduleAmount = NSDecimalNumber.init(string: moduleCoin.amount)
        }
        if let reserveCoin = reserveCoins?.filter({ $0.denom == denom}).first {
            reserveAmount = NSDecimalNumber.init(string: reserveCoin.amount)
        }
        let moduleBorrowable = moduleAmount.subtracting(reserveAmount)
        if (hardMoneyMarket?.borrow_limit?.has_max_limit == true) {
            let maximum_limit = NSDecimalNumber.init(string: hardParam?.getHardMoneyMarket(denom)?.borrow_limit?.maximum_limit)
            SystemBorrowableAmount = maximum_limit.compare(moduleBorrowable).rawValue > 0 ? moduleBorrowable : maximum_limit
        } else {
            SystemBorrowableAmount = moduleBorrowable
        }
//        print("SystemBorrowableAmount ", SystemBorrowableAmount)
        
        return totalBorrowAbleAmount.compare(SystemBorrowableAmount).rawValue > 0 ? SystemBorrowableAmount : totalBorrowAbleAmount
    }
    
    static func getHardBorrowableValueByDenom(_ denom: String, _ mydeposit: Array<HardMyDeposit>?, _ myBorrow: Array<HardMyBorrow>?,
                                              _ moduleCoins: Array<Coin>?, _ reserveCoins: Array<Coin>?) -> NSDecimalNumber {
        let denomPrice = getKavaPrice(denom)
        let decimal = getKavaCoinDecimal(denom)
        let amount = getHardBorrowableAmountByDenom(denom, mydeposit, myBorrow, moduleCoins, reserveCoins)
        return amount.multiplying(byPowerOf10: -decimal).multiplying(by: denomPrice, withBehavior: WUtils.handler2Down)
    }
    
    static func getKavaPrice(_ denom: String) -> NSDecimalNumber {
        var result = NSDecimalNumber.zero
        if (denom == "usdx") {
            result = NSDecimalNumber.one
        } else {
            let hardParam = BaseData.instance.mHardParam
            if let price = BaseData.instance.mKavaPrice[hardParam!.getSpotMarketId(denom)!] {
                result = NSDecimalNumber.init(string: price.result.price)
            }
        }
        return result
    }
    
    static func getHardSuppliedValueByDenom(_ denom: String, _ mydeposit: Array<HardMyDeposit>?) -> NSDecimalNumber {
        let denomPrice = getKavaPrice(denom)
        let decimal = getKavaCoinDecimal(denom)
        let amount = getHardSuppliedAmountByDenom(denom, mydeposit)
        return amount.multiplying(byPowerOf10: -decimal).multiplying(by: denomPrice, withBehavior: WUtils.handler2Down)
    }
    
    static func getHardBorrowedAmountByDenom(_ denom: String, _ myBorrow: Array<HardMyBorrow>?) -> NSDecimalNumber {
        var result = NSDecimalNumber.zero
        if let borrow = myBorrow?[0], let coins = borrow.amount {
            for coin in coins {
                if (coin.denom == denom) {
                    result = NSDecimalNumber.init(string: coin.amount)
                }
            }
        }
        return result
    }
    
    
    //address, accountnumber, sequencenumber
    static func onParseAuthGrpc(_ response :Cosmos_Auth_V1beta1_QueryAccountResponse) -> (String?, UInt64?, UInt64?) {
        if (response.account.typeURL.contains(Cosmos_Auth_V1beta1_BaseAccount.protoMessageName)) {
            let auth = try! Cosmos_Auth_V1beta1_BaseAccount.init(serializedData: response.account.value)
            return (auth.address, auth.accountNumber, auth.sequence)
            
        } else if (response.account.typeURL.contains(Cosmos_Vesting_V1beta1_PeriodicVestingAccount.protoMessageName)) {
            let auth = try! Cosmos_Vesting_V1beta1_PeriodicVestingAccount.init(serializedData: response.account.value).baseVestingAccount.baseAccount
            return (auth.address, auth.accountNumber, auth.sequence)
            
        } else if (response.account.typeURL.contains(Cosmos_Vesting_V1beta1_ContinuousVestingAccount.protoMessageName)) {
            let auth = try! Cosmos_Vesting_V1beta1_ContinuousVestingAccount.init(serializedData: response.account.value).baseVestingAccount.baseAccount
            return (auth.address, auth.accountNumber, auth.sequence)
            
        } else if (response.account.typeURL.contains(Cosmos_Vesting_V1beta1_DelayedVestingAccount.protoMessageName)) {
            let auth = try! Cosmos_Vesting_V1beta1_DelayedVestingAccount.init(serializedData: response.account.value).baseVestingAccount.baseAccount
            return (auth.address, auth.accountNumber, auth.sequence)
            
        }
        return (nil, nil, nil)
    }
    
    static func onParseVestingAccount() {
        print("onParseVestingAccount")
        guard let account = BaseData.instance.mAccount_gRPC else { return }
        var sBalace = Array<Coin>()
        BaseData.instance.mMyBalances_gRPC.forEach { coin in
            sBalace.append(coin)
        }
//        print("sBalace ", sBalace)
        if (account.typeURL.contains(Cosmos_Vesting_V1beta1_PeriodicVestingAccount.protoMessageName)) {
            let vestingAccount = try! Cosmos_Vesting_V1beta1_PeriodicVestingAccount.init(serializedData: account.value)
            sBalace.forEach({ (coin) in
                let denom = coin.denom
                var dpBalance = NSDecimalNumber.zero
                var dpVesting = NSDecimalNumber.zero
                var originalVesting = NSDecimalNumber.zero
                var remainVesting = NSDecimalNumber.zero
                var delegatedVesting = NSDecimalNumber.zero
                
                dpBalance = NSDecimalNumber.init(string: coin.amount)
//                print("dpBalance ", denom, "  ", dpBalance)
                
                vestingAccount.baseVestingAccount.originalVesting.forEach({ (coin) in
                    if (coin.denom == denom) {
                        originalVesting = originalVesting.adding(NSDecimalNumber.init(string: coin.amount))
                    }
                })
//                print("originalVesting ", denom, "  ", originalVesting)
                
                vestingAccount.baseVestingAccount.delegatedVesting.forEach({ (coin) in
                    if (coin.denom == denom) {
                        delegatedVesting = delegatedVesting.adding(NSDecimalNumber.init(string: coin.amount))
                    }
                })
//                print("delegatedVesting ", denom, "  ", delegatedVesting)
                
                remainVesting = WUtils.onParsePeriodicRemainVestingsAmountByDenom(vestingAccount, denom)
//                print("remainVesting ", denom, "  ", remainVesting)
                
                dpVesting = remainVesting.subtracting(delegatedVesting);
//                print("dpVestingA ", denom, "  ", dpVesting)
                
                dpVesting = dpVesting.compare(NSDecimalNumber.zero).rawValue <= 0 ? NSDecimalNumber.zero : dpVesting
//                print("dpVestingB ", denom, "  ", dpVesting)
                
                if (remainVesting.compare(delegatedVesting).rawValue > 0) {
                    dpBalance = dpBalance.subtracting(remainVesting).adding(delegatedVesting);
                }
//                print("final dpBalance ", denom, "  ", dpBalance)
                
                if (dpVesting.compare(NSDecimalNumber.zero).rawValue > 0) {
                    let vestingCoin = Coin.init(denom, dpVesting.stringValue)
                    BaseData.instance.mMyVestings_gRPC.append(vestingCoin)
                    var replace = -1
                    for i in 0..<BaseData.instance.mMyBalances_gRPC.count {
                        if (BaseData.instance.mMyBalances_gRPC[i].denom == denom) {
                            replace = i
                        }
                    }
                    if (replace >= 0) {
                        BaseData.instance.mMyBalances_gRPC[replace] = Coin.init(denom, dpBalance.stringValue)
                    }
                }
            })
            
        } else if (account.typeURL.contains(Cosmos_Vesting_V1beta1_ContinuousVestingAccount.protoMessageName)) {
            let vestingAccount = try! Cosmos_Vesting_V1beta1_ContinuousVestingAccount.init(serializedData: account.value)
            sBalace.forEach({ (coin) in
                let denom = coin.denom
                var dpBalance = NSDecimalNumber.zero
                var dpVesting = NSDecimalNumber.zero
                var originalVesting = NSDecimalNumber.zero
                var remainVesting = NSDecimalNumber.zero
                var delegatedVesting = NSDecimalNumber.zero
                
                dpBalance = NSDecimalNumber.init(string: coin.amount)
//                print("dpBalance ", denom, "  ", dpBalance)
                
                vestingAccount.baseVestingAccount.originalVesting.forEach({ (coin) in
                    if (coin.denom == denom) {
                        originalVesting = originalVesting.adding(NSDecimalNumber.init(string: coin.amount))
                    }
                })
//                print("originalVesting ", denom, "  ", originalVesting)
                
                vestingAccount.baseVestingAccount.delegatedVesting.forEach({ (coin) in
                    if (coin.denom == denom) {
                        delegatedVesting = delegatedVesting.adding(NSDecimalNumber.init(string: coin.amount))
                    }
                })
//                print("delegatedVesting ", denom, "  ", delegatedVesting)
                
                let cTime = Date().millisecondsSince1970
                let vestingStart = vestingAccount.startTime * 1000
                let vestingEnd = vestingAccount.baseVestingAccount.endTime * 1000
                if (cTime < vestingStart) {
                    remainVesting = originalVesting
                } else if (cTime > vestingEnd) {
                    remainVesting = NSDecimalNumber.zero
                } else {
                    let progress = ((Float)(cTime - vestingStart)) / ((Float)(vestingEnd - vestingStart))
                    remainVesting = originalVesting.multiplying(by: NSDecimalNumber.init(value: 1 - progress), withBehavior: handler0Up)
                }
//                print("remainVesting ", denom, "  ", remainVesting)
                
                dpVesting = remainVesting.subtracting(delegatedVesting);
//                print("dpVestingA ", denom, "  ", dpVesting)
                
                dpVesting = dpVesting.compare(NSDecimalNumber.zero).rawValue <= 0 ? NSDecimalNumber.zero : dpVesting
//                print("dpVestingB ", denom, "  ", dpVesting)
                
                if (remainVesting.compare(delegatedVesting).rawValue > 0) {
                    dpBalance = dpBalance.subtracting(remainVesting).adding(delegatedVesting);
                }
//                print("final dpBalance ", denom, "  ", dpBalance)
                
                if (dpVesting.compare(NSDecimalNumber.zero).rawValue > 0) {
                    let vestingCoin = Coin.init(denom, dpVesting.stringValue)
                    BaseData.instance.mMyVestings_gRPC.append(vestingCoin)
                    var replace = -1
                    for i in 0..<BaseData.instance.mMyBalances_gRPC.count {
                        if (BaseData.instance.mMyBalances_gRPC[i].denom == denom) {
                            replace = i
                        }
                    }
                    if (replace >= 0) {
                        BaseData.instance.mMyBalances_gRPC[replace] = Coin.init(denom, dpBalance.stringValue)
                    }
                }
                
            })
            
        } else if (account.typeURL.contains(Cosmos_Vesting_V1beta1_DelayedVestingAccount.protoMessageName)) {
            let vestingAccount = try! Cosmos_Vesting_V1beta1_DelayedVestingAccount.init(serializedData: account.value)
            sBalace.forEach({ (coin) in
                let denom = coin.denom
                var dpBalance = NSDecimalNumber.zero
                var dpVesting = NSDecimalNumber.zero
                var originalVesting = NSDecimalNumber.zero
                var remainVesting = NSDecimalNumber.zero
                var delegatedVesting = NSDecimalNumber.zero
                
                dpBalance = NSDecimalNumber.init(string: coin.amount)
//                print("dpBalance ", denom, "  ", dpBalance)
                
                vestingAccount.baseVestingAccount.originalVesting.forEach({ (coin) in
                    if (coin.denom == denom) {
                        originalVesting = originalVesting.adding(NSDecimalNumber.init(string: coin.amount))
                    }
                })
//                print("originalVesting ", denom, "  ", originalVesting)
                
                vestingAccount.baseVestingAccount.delegatedVesting.forEach({ (coin) in
                    if (coin.denom == denom) {
                        delegatedVesting = delegatedVesting.adding(NSDecimalNumber.init(string: coin.amount))
                    }
                })
//                print("delegatedVesting ", denom, "  ", delegatedVesting)
                
                let cTime = Date().millisecondsSince1970
                let vestingEnd = vestingAccount.baseVestingAccount.endTime * 1000
                if (cTime < vestingEnd) {
                    remainVesting = originalVesting
                }
//                print("remainVesting ", denom, "  ", remainVesting)
                
                dpVesting = remainVesting.subtracting(delegatedVesting);
//                print("dpVestingA ", denom, "  ", dpVesting)
                
                dpVesting = dpVesting.compare(NSDecimalNumber.zero).rawValue <= 0 ? NSDecimalNumber.zero : dpVesting
//                print("dpVestingB ", denom, "  ", dpVesting)
                
                if (remainVesting.compare(delegatedVesting).rawValue > 0) {
                    dpBalance = dpBalance.subtracting(remainVesting).adding(delegatedVesting);
                }
//                print("final dpBalance ", denom, "  ", dpBalance)
                
                if (dpVesting.compare(NSDecimalNumber.zero).rawValue > 0) {
                    let vestingCoin = Coin.init(denom, dpVesting.stringValue)
                    BaseData.instance.mMyVestings_gRPC.append(vestingCoin)
                    var replace = -1
                    for i in 0..<BaseData.instance.mMyBalances_gRPC.count {
                        if (BaseData.instance.mMyBalances_gRPC[i].denom == denom) {
                            replace = i
                        }
                    }
                    if (replace >= 0) {
                        BaseData.instance.mMyBalances_gRPC[replace] = Coin.init(denom, dpBalance.stringValue)
                    }
                }
                
            })
            
        }
    }
    
    static func onParsePersisVestingAccount() {
//        print("onParsePersisVestingAccount")
        guard let account = BaseData.instance.mAccount_gRPC else { return }
        var sBalace = Array<Coin>()
        BaseData.instance.mMyBalances_gRPC.forEach { coin in
            sBalace.append(coin)
        }
//        print("sBalace ", sBalace)
        if (account.typeURL.contains(Cosmos_Vesting_V1beta1_PeriodicVestingAccount.protoMessageName)) {
            let vestingAccount = try! Cosmos_Vesting_V1beta1_PeriodicVestingAccount.init(serializedData: account.value)
            sBalace.forEach({ (coin) in
                let denom = coin.denom
                var bankBalance = NSDecimalNumber.zero
                var dpBalance = NSDecimalNumber.zero
                var dpVesting = NSDecimalNumber.zero
                var remainVesting = NSDecimalNumber.zero
                
                bankBalance = NSDecimalNumber.init(string: coin.amount)
//                print("bankBalance ", denom, "  ", bankBalance)
                
                remainVesting = WUtils.onParsePeriodicRemainVestingsAmountByDenom(vestingAccount, denom)
//                print("remainVesting ", denom, "  ", remainVesting)
                
                dpBalance = bankBalance.compare(remainVesting).rawValue <= 0 ? NSDecimalNumber.zero : bankBalance.subtracting(remainVesting)
//                print("final dpBalance ", denom, "  ", dpBalance)
                
                dpVesting = bankBalance.subtracting(dpBalance)
//                print("dpVesting ", denom, "  ", dpVesting)
                
                let vestingCoin = Coin.init(denom, dpVesting.stringValue)
                BaseData.instance.mMyVestings_gRPC.append(vestingCoin)
                var replace = -1
                for i in 0..<BaseData.instance.mMyBalances_gRPC.count {
                    if (BaseData.instance.mMyBalances_gRPC[i].denom == denom) {
                        replace = i
                    }
                }
                if (replace >= 0) {
                    BaseData.instance.mMyBalances_gRPC[replace] = Coin.init(denom, dpBalance.stringValue)
                }
            })
            
        } else if (account.typeURL.contains(Cosmos_Vesting_V1beta1_ContinuousVestingAccount.protoMessageName)) {
            let vestingAccount = try! Cosmos_Vesting_V1beta1_ContinuousVestingAccount.init(serializedData: account.value)
            sBalace.forEach({ (coin) in
                let denom = coin.denom
                var bankBalance = NSDecimalNumber.zero
                var dpBalance = NSDecimalNumber.zero
                var dpVesting = NSDecimalNumber.zero
                var originalVesting = NSDecimalNumber.zero
                var remainVesting = NSDecimalNumber.zero
                
                bankBalance = NSDecimalNumber.init(string: coin.amount)
//                print("bankBalance ", denom, "  ", bankBalance)
                
                vestingAccount.baseVestingAccount.originalVesting.forEach({ (coin) in
                    if (coin.denom == denom) {
                        originalVesting = originalVesting.adding(NSDecimalNumber.init(string: coin.amount))
                    }
                })
//                print("originalVesting ", denom, "  ", originalVesting)
                
                let cTime = Date().millisecondsSince1970
                let vestingStart = vestingAccount.startTime * 1000
                let vestingEnd = vestingAccount.baseVestingAccount.endTime * 1000
                
                if (cTime < vestingStart) {
                    remainVesting = originalVesting
                } else if (cTime > vestingEnd) {
                    remainVesting = NSDecimalNumber.zero
                } else {
                    let progress = ((Float)(cTime - vestingStart)) / ((Float)(vestingEnd - vestingStart))
                    remainVesting = originalVesting.multiplying(by: NSDecimalNumber.init(value: 1 - progress), withBehavior: handler0Up)
                }
                
                if (remainVesting.compare(NSDecimalNumber.zero).rawValue > 0) {
                    dpBalance = NSDecimalNumber.zero
                    dpVesting = bankBalance
                    
                } else {
                    dpBalance = bankBalance
                    dpVesting = NSDecimalNumber.zero
                    
                }
                
                if (dpVesting.compare(NSDecimalNumber.zero).rawValue > 0) {
                    let vestingCoin = Coin.init(denom, dpVesting.stringValue)
                    BaseData.instance.mMyVestings_gRPC.append(vestingCoin)
                    var replace = -1
                    for i in 0..<BaseData.instance.mMyBalances_gRPC.count {
                        if (BaseData.instance.mMyBalances_gRPC[i].denom == denom) {
                            replace = i
                        }
                    }
                    if (replace >= 0) {
                        BaseData.instance.mMyBalances_gRPC[replace] = Coin.init(denom, dpBalance.stringValue)
                    }
                }
            })
        }
    }
    
    static func onParsePeriodicUnLockTime(_ vestingAccount: Cosmos_Vesting_V1beta1_PeriodicVestingAccount, _ position: Int) -> Int64 {
        var result = vestingAccount.startTime
        for i in 0..<(position + 1) {
            result = result + vestingAccount.vestingPeriods[i].length
        }
        return result * 1000
    }
    
    static func onParsePeriodicRemainVestings(_ vestingAccount: Cosmos_Vesting_V1beta1_PeriodicVestingAccount) -> Array<Cosmos_Vesting_V1beta1_Period> {
        var results = Array<Cosmos_Vesting_V1beta1_Period>()
        let cTime = Date().millisecondsSince1970
        for i in 0..<vestingAccount.vestingPeriods.count {
            let unlockTime = onParsePeriodicUnLockTime(vestingAccount, i)
            if (cTime < unlockTime) {
                let temp = Cosmos_Vesting_V1beta1_Period.with {
                    $0.length = unlockTime
                    $0.amount = vestingAccount.vestingPeriods[i].amount
                }
                results.append(temp)
            }
        }
        return results
    }
    
    static func onParsePeriodicRemainVestingsByDenom(_ vestingAccount: Cosmos_Vesting_V1beta1_PeriodicVestingAccount, _ denom: String) -> Array<Cosmos_Vesting_V1beta1_Period> {
        var results = Array<Cosmos_Vesting_V1beta1_Period>()
        for vp in onParsePeriodicRemainVestings(vestingAccount) {
            for coin in vp.amount {
                if (coin.denom ==  denom) {
                    results.append(vp)
                }
            }
        }
        return results
    }
    
    static func onParseAllPeriodicRemainVestingsCnt(_ vestingAccount: Cosmos_Vesting_V1beta1_PeriodicVestingAccount) -> Int {
        return onParsePeriodicRemainVestings(vestingAccount).count
    }
    
    static func onParsePeriodicRemainVestingsCntByDenom(_ vestingAccount: Cosmos_Vesting_V1beta1_PeriodicVestingAccount, _ denom: String) -> Int {
        return onParsePeriodicRemainVestingsByDenom(vestingAccount, denom).count
    }

    static func onParsePeriodicRemainVestingTime(_ vestingAccount: Cosmos_Vesting_V1beta1_PeriodicVestingAccount, _ denom: String, _ position: Int) -> Int64 {
        return onParsePeriodicRemainVestingsByDenom(vestingAccount, denom)[position].length
    }
    
    static func onParsePeriodicRemainVestingsAmountByDenom(_ vestingAccount: Cosmos_Vesting_V1beta1_PeriodicVestingAccount, _ denom: String) -> NSDecimalNumber {
        var results = NSDecimalNumber.zero
        let periods = onParsePeriodicRemainVestingsByDenom(vestingAccount, denom)
        for vp in periods {
            for coin in vp.amount {
                if (coin.denom ==  denom) {
                    results = results.adding(NSDecimalNumber.init(string: coin.amount))
                }
            }
        }
        return results
    }
    
    static func onParsePeriodicRemainVestingAmount(_ vestingAccount: Cosmos_Vesting_V1beta1_PeriodicVestingAccount, _ denom: String, _ position: Int) -> NSDecimalNumber {
        let periods = onParsePeriodicRemainVestingsByDenom(vestingAccount, denom)
        if (periods.count > position && periods[position] != nil) {
            let coin = periods[position].amount.filter { $0.denom == denom }.first
            return NSDecimalNumber.init(string: coin?.amount)
        }
        return NSDecimalNumber.zero
    }
    
    static func getAmountVp(_ vp: Cosmos_Vesting_V1beta1_Period, _ denom: String) -> NSDecimalNumber {
        var result = NSDecimalNumber.zero
        vp.amount.forEach { (coin) in
            if (coin.denom == denom) {
                result = NSDecimalNumber.init(string: coin.amount)
            }
        }
        return result
    }
    
    static func onParseFeeAmountGrpc(_ tx: Cosmos_Tx_V1beta1_GetTxResponse) -> NSDecimalNumber {
        let result = NSDecimalNumber.zero
        if (tx.tx.authInfo.fee.amount.count > 0) {
            return NSDecimalNumber.init(string: tx.tx.authInfo.fee.amount[0].amount)
        }
        return result
    }
    
    static func onParseAutoRewardGrpc(_ tx: Cosmos_Tx_V1beta1_GetTxResponse, _ address: String, _ position: Int) -> NSDecimalNumber {
        var result = NSDecimalNumber.zero
        if (tx.txResponse.logs == nil || tx.txResponse.logs.count <= position || tx.txResponse.logs[position] == nil)  { return result }
        tx.txResponse.logs[position].events.forEach { (event) in
            for i in 0...event.attributes.count - 1 {
                if (event.attributes[i].key == "recipient" && event.attributes[i].value == address) {
                    for j in i...event.attributes.count - 1 {
                        if (event.attributes[j].key == "amount") {
                            let amount = event.attributes[j].value.filter{ $0.isNumber }
                            result = NSDecimalNumber.init(string: amount)
                            break
                        }
                    }
                }
            }
        }
        return result
    }
    
    
    
    static func onParseStakeRewardGrpc(_ tx: Cosmos_Tx_V1beta1_GetTxResponse, _ opAddress: String, _ position: Int) -> NSDecimalNumber {
        var result = NSDecimalNumber.zero
        if (tx.txResponse.logs == nil || tx.txResponse.logs.count <= position || tx.txResponse.logs[position] == nil)  { return result }
        tx.txResponse.logs[position].events.forEach { (event) in
            if (event.type == "withdraw_rewards") {
                for i in 0...event.attributes.count - 1 {
                    if (event.attributes[i].key == "validator" && event.attributes[i].value == opAddress) {
                        let amount = event.attributes[i - 1].value.filter{ $0.isNumber }
                        result = NSDecimalNumber.init(string: amount)
                        break
                    }
                }
            }
        }
        return result
    }
    
    static func onParseCommisiondGrpc(_ tx: Cosmos_Tx_V1beta1_GetTxResponse, _ position: Int) -> NSDecimalNumber {
        var result = NSDecimalNumber.zero
        if (tx.txResponse.logs == nil || tx.txResponse.logs.count <= position || tx.txResponse.logs[position] == nil)  { return result }
        tx.txResponse.logs[position].events.forEach { (event) in
            if (event.type == "withdraw_commission") {
                for i in 0...event.attributes.count - 1 {
                    if (event.attributes[i].key == "amount") {
                        let amount = event.attributes[i].value.filter{ $0.isNumber }
                        result = NSDecimalNumber.init(string: amount)
                        break
                    }
                }
            }
        }
        return result
    }
    
    static func onParseProposalTitle(_ proposal: Cosmos_Gov_V1beta1_Proposal) -> String {
        if (proposal.content.typeURL.contains(Cosmos_Gov_V1beta1_TextProposal.protoMessageName)) {
            let textProposal = try! Cosmos_Gov_V1beta1_TextProposal.init(serializedData: proposal.content.value)
            return textProposal.title
            
        } else if (proposal.content.typeURL.contains(Cosmos_Params_V1beta1_ParameterChangeProposal.protoMessageName)) {
            let paramProposal = try! Cosmos_Params_V1beta1_ParameterChangeProposal.init(serializedData: proposal.content.value)
            return paramProposal.title
            
        } else if (proposal.content.typeURL.contains(Ibc_Core_Client_V1_ClientUpdateProposal.protoMessageName)) {
            let clientProposal = try! Ibc_Core_Client_V1_ClientUpdateProposal.init(serializedData: proposal.content.value)
            return clientProposal.title
            
        } else if (proposal.content.typeURL.contains(Cosmos_Distribution_V1beta1_CommunityPoolSpendProposal.protoMessageName)) {
            let poolProposal = try! Cosmos_Distribution_V1beta1_CommunityPoolSpendProposal.init(serializedData: proposal.content.value)
            return poolProposal.title
            
        } else if (proposal.content.typeURL.contains(Cosmos_Upgrade_V1beta1_SoftwareUpgradeProposal.protoMessageName)) {
            let upgradeProposal = try! Cosmos_Upgrade_V1beta1_SoftwareUpgradeProposal.init(serializedData: proposal.content.value)
            return upgradeProposal.title
            
        } else if (proposal.content.typeURL.contains(Cosmos_Upgrade_V1beta1_CancelSoftwareUpgradeProposal.protoMessageName)) {
            let cancelProposal = try! Cosmos_Upgrade_V1beta1_CancelSoftwareUpgradeProposal.init(serializedData: proposal.content.value)
            return cancelProposal.title
        }
        return ""
    }
    
    static func onParseProposalDescription(_ proposal: Cosmos_Gov_V1beta1_Proposal) -> String {
        if (proposal.content.typeURL.contains(Cosmos_Gov_V1beta1_TextProposal.protoMessageName)) {
            let textProposal = try! Cosmos_Gov_V1beta1_TextProposal.init(serializedData: proposal.content.value)
            return textProposal.description_p
            
        } else if (proposal.content.typeURL.contains(Cosmos_Params_V1beta1_ParameterChangeProposal.protoMessageName)) {
            let paramProposal = try! Cosmos_Params_V1beta1_ParameterChangeProposal.init(serializedData: proposal.content.value)
            return paramProposal.description_p
            
        } else if (proposal.content.typeURL.contains(Ibc_Core_Client_V1_ClientUpdateProposal.protoMessageName)) {
            let clientProposal = try! Ibc_Core_Client_V1_ClientUpdateProposal.init(serializedData: proposal.content.value)
            return clientProposal.description_p
            
        } else if (proposal.content.typeURL.contains(Cosmos_Distribution_V1beta1_CommunityPoolSpendProposal.protoMessageName)) {
            let poolProposal = try! Cosmos_Distribution_V1beta1_CommunityPoolSpendProposal.init(serializedData: proposal.content.value)
            return poolProposal.description_p
            
        } else if (proposal.content.typeURL.contains(Cosmos_Upgrade_V1beta1_SoftwareUpgradeProposal.protoMessageName)) {
            let upgradeProposal = try! Cosmos_Upgrade_V1beta1_SoftwareUpgradeProposal.init(serializedData: proposal.content.value)
            return upgradeProposal.description_p
            
        } else if (proposal.content.typeURL.contains(Cosmos_Upgrade_V1beta1_CancelSoftwareUpgradeProposal.protoMessageName)) {
            let cancelProposal = try! Cosmos_Upgrade_V1beta1_CancelSoftwareUpgradeProposal.init(serializedData: proposal.content.value)
            return cancelProposal.description_p
        }
        return ""
    }
    
    static func onParseProposalRequestAmount(_ proposal: Cosmos_Gov_V1beta1_Proposal) -> Coin? {
        if (proposal.content.typeURL.contains(Cosmos_Distribution_V1beta1_CommunityPoolSpendProposal.protoMessageName)) {
            let poolProposal = try! Cosmos_Distribution_V1beta1_CommunityPoolSpendProposal.init(serializedData: proposal.content.value)
            return Coin.init(poolProposal.amount[0].denom, poolProposal.amount[0].amount)
        } else {
            return nil;
        }
    }
    
    static func onParseProposalStatusTxt(_ proposal: Cosmos_Gov_V1beta1_Proposal) -> String {
        if (proposal.status == Cosmos_Gov_V1beta1_ProposalStatus.depositPeriod) {
            return "DepositPeriod"
        } else if (proposal.status == Cosmos_Gov_V1beta1_ProposalStatus.votingPeriod) {
            return "VotingPeriod"
        } else if (proposal.status == Cosmos_Gov_V1beta1_ProposalStatus.passed) {
            return "Passed"
        } else if (proposal.status == Cosmos_Gov_V1beta1_ProposalStatus.rejected) {
            return "Rejected"
        }
        return "unKnown"
    }
    
    static func onParseProposalStatusImg(_ proposal: Cosmos_Gov_V1beta1_Proposal) -> UIImage? {
        if (proposal.status == Cosmos_Gov_V1beta1_ProposalStatus.depositPeriod) {
            return UIImage.init(named: "depositImg")
        } else if (proposal.status == Cosmos_Gov_V1beta1_ProposalStatus.votingPeriod) {
            return UIImage.init(named: "votingImg")
        } else if (proposal.status == Cosmos_Gov_V1beta1_ProposalStatus.passed) {
            return UIImage.init(named: "passedImg")
        } else if (proposal.status == Cosmos_Gov_V1beta1_ProposalStatus.rejected) {
            return UIImage.init(named: "rejectedImg")
        }
        return nil
    }
    
    static func onParseProposalStartTime(_ proposal: Cosmos_Gov_V1beta1_Proposal) -> String {
        if (proposal.status == Cosmos_Gov_V1beta1_ProposalStatus.depositPeriod) {
            return "Waiting Deposit"
        } else {
            return longTimetoString(input: proposal.votingStartTime.seconds * 1000)
        }
    }
    
    static func onParseProposalEndTime(_ proposal: Cosmos_Gov_V1beta1_Proposal) -> String {
        if (proposal.status == Cosmos_Gov_V1beta1_ProposalStatus.depositPeriod) {
            return "Waiting Deposit"
        } else {
            return longTimetoString(input: proposal.votingEndTime.seconds * 1000)
        }
    }
    
    
    static func getSum(_ tally:Cosmos_Gov_V1beta1_TallyResult) ->NSDecimalNumber {
        var sum = NSDecimalNumber.zero
        sum = sum.adding(NSDecimalNumber.init(string: tally.yes))
        sum = sum.adding(NSDecimalNumber.init(string: tally.no))
        sum = sum.adding(NSDecimalNumber.init(string: tally.noWithVeto))
        sum = sum.adding(NSDecimalNumber.init(string: tally.abstain))
        return sum
    }
    
    static func getYes(_ tally:Cosmos_Gov_V1beta1_TallyResult) -> NSDecimalNumber {
        if (getSum(tally) == NSDecimalNumber.zero) {
            return NSDecimalNumber.zero
        }
        return NSDecimalNumber.init(string: tally.yes).multiplying(byPowerOf10: 2).dividing(by: getSum(tally), withBehavior: WUtils.handler2)
    }
    
    static func getNo(_ tally:Cosmos_Gov_V1beta1_TallyResult) -> NSDecimalNumber {
        if (getSum(tally) == NSDecimalNumber.zero) {
            return NSDecimalNumber.zero
        }
        return NSDecimalNumber.init(string: tally.no).multiplying(byPowerOf10: 2).dividing(by: getSum(tally), withBehavior: WUtils.handler2)
    }
    
    static func getVeto(_ tally:Cosmos_Gov_V1beta1_TallyResult) -> NSDecimalNumber {
        if (getSum(tally) == NSDecimalNumber.zero) {
            return NSDecimalNumber.zero
        }
        return NSDecimalNumber.init(string: tally.noWithVeto).multiplying(byPowerOf10: 2).dividing(by: getSum(tally), withBehavior: WUtils.handler2)
    }
    
    static func getAbstain(_ tally:Cosmos_Gov_V1beta1_TallyResult) -> NSDecimalNumber {
        if (getSum(tally) == NSDecimalNumber.zero) {
            return NSDecimalNumber.zero
        }
        return NSDecimalNumber.init(string: tally.abstain).multiplying(byPowerOf10: 2).dividing(by: getSum(tally), withBehavior: WUtils.handler2)
    }
    
    static func getTurnout(_ tally:Cosmos_Gov_V1beta1_TallyResult) -> NSDecimalNumber {
        if let bonded = BaseData.instance.mStakingPool_gRPC?.bondedTokens {
            return getSum(tally).multiplying(byPowerOf10: 2).dividing(by: NSDecimalNumber.init(string: bonded), withBehavior: WUtils.handler2)
        }
        return NSDecimalNumber.zero
    }
    
    static func getVoterTypeCnt_gRPC(_ votes: Array<Cosmos_Gov_V1beta1_Vote>?, _ option: Cosmos_Gov_V1beta1_VoteOption) -> String {
        var result = 0
        if (votes == nil) {
            return String(result)
        }
        for vote in votes! {
            if (vote.option == option) {
                result = result + 1
            }
        }
        return String(result)
    }
    
    static func isGRPC(_ chain: ChainType?) -> Bool {
        if (chain == ChainType.COSMOS_MAIN || chain == ChainType.IRIS_MAIN || chain == ChainType.AKASH_MAIN ||
                chain == ChainType.PERSIS_MAIN || chain == ChainType.CRYPTO_MAIN || chain == ChainType.COSMOS_TEST || chain == ChainType.IRIS_TEST) {
            return true
        }
        return false
    }
}

extension Date {
    var millisecondsSince1970:Int64 {
        return Int64((self.timeIntervalSince1970 * 1000.0).rounded())
    }
    
    var StringmillisecondsSince1970:String {
        return String((self.timeIntervalSince1970 * 1000.0).rounded())
    }
    
    var Stringmilli3MonthAgo:Int64 {
        return Int64((self.timeIntervalSince1970 * 1000.0) - TimeInterval(7776000000.0).rounded())
    }
    
    init(milliseconds:Int) {
        self = Date(timeIntervalSince1970: TimeInterval(milliseconds) / 1000)
    }
}

extension String {
    func index(from: Int) -> Index {
        return self.index(startIndex, offsetBy: from)
    }

    func substring(from: Int) -> String {
        let fromIndex = index(from: from)
        return String(self[fromIndex...])
    }

    func substring(to: Int) -> String {
        let toIndex = index(from: to)
        return String(self[..<toIndex])
    }

    func substring(with r: Range<Int>) -> String {
        let startIndex = index(from: r.lowerBound)
        let endIndex = index(from: r.upperBound)
        return String(self[startIndex..<endIndex])
    }
}


extension UIImage {
    
    public class func gifImageWithData(_ data: Data) -> UIImage? {
        guard let source = CGImageSourceCreateWithData(data as CFData, nil) else {
            return nil
        }
        
        return UIImage.animatedImageWithSource(source)
    }
    
    public class func gifImageWithURL(_ gifUrl:String) -> UIImage? {
        guard let bundleURL:URL? = URL(string: gifUrl)
            else {
                return nil
        }
        guard let imageData = try? Data(contentsOf: bundleURL!) else {
            return nil
        }
        
        return gifImageWithData(imageData)
    }
    
    public class func gifImageWithName(_ name: String) -> UIImage? {
        guard let bundleURL = Bundle.main
            .url(forResource: name, withExtension: "gif") else {
                return nil
        }
        guard let imageData = try? Data(contentsOf: bundleURL) else {
            return nil
        }
        
        return gifImageWithData(imageData)
    }
    
    class func delayForImageAtIndex(_ index: Int, source: CGImageSource!) -> Double {
        var delay = 0.1
        
        let cfProperties = CGImageSourceCopyPropertiesAtIndex(source, index, nil)
        let gifProperties: CFDictionary = unsafeBitCast(
            CFDictionaryGetValue(cfProperties,
                                 Unmanaged.passUnretained(kCGImagePropertyGIFDictionary).toOpaque()),
            to: CFDictionary.self)
        
        var delayObject: AnyObject = unsafeBitCast(
            CFDictionaryGetValue(gifProperties,
                                 Unmanaged.passUnretained(kCGImagePropertyGIFUnclampedDelayTime).toOpaque()),
            to: AnyObject.self)
        if delayObject.doubleValue == 0 {
            delayObject = unsafeBitCast(CFDictionaryGetValue(gifProperties,
                                                             Unmanaged.passUnretained(kCGImagePropertyGIFDelayTime).toOpaque()), to: AnyObject.self)
        }
        
        delay = delayObject as! Double
        
        if delay < 0.1 {
            delay = 0.1
        }
        
        return delay
    }
    
    class func gcdForPair(_ a: Int?, _ b: Int?) -> Int {
        var a = a
        var b = b
        if b == nil || a == nil {
            if b != nil {
                return b!
            } else if a != nil {
                return a!
            } else {
                return 0
            }
        }
        
        if a! < b! {
            let c = a
            a = b
            b = c
        }
        
        var rest: Int
        while true {
            rest = a! % b!
            
            if rest == 0 {
                return b!
            } else {
                a = b
                b = rest
            }
        }
    }
    
    class func gcdForArray(_ array: Array<Int>) -> Int {
        if array.isEmpty {
            return 1
        }
        
        var gcd = array[0]
        
        for val in array {
            gcd = UIImage.gcdForPair(val, gcd)
        }
        
        return gcd
    }
    
    class func animatedImageWithSource(_ source: CGImageSource) -> UIImage? {
        let count = CGImageSourceGetCount(source)
        var images = [CGImage]()
        var delays = [Int]()
        
        for i in 0..<count {
            if let image = CGImageSourceCreateImageAtIndex(source, i, nil) {
                images.append(image)
            }
            
            let delaySeconds = UIImage.delayForImageAtIndex(Int(i), source: source)
            delays.append(Int(delaySeconds * 3000.0)) // Seconds to ms
        }
        
        let duration: Int = {
            var sum = 0
            
            for val: Int in delays {
                sum += val
            }
            
            return sum
        }()
        
        let gcd = gcdForArray(delays)
        var frames = [UIImage]()
        
        var frame: UIImage
        var frameCount: Int
        for i in 0..<count {
            frame = UIImage(cgImage: images[Int(i)])
            frameCount = Int(delays[Int(i)] / gcd)
            
            for _ in 0..<frameCount {
                frames.append(frame)
            }
        }
        
        let animation = UIImage.animatedImage(with: frames,
                                              duration: Double(duration) / 3000.0)
        
        return animation
    }
}


open class CustomSlider : UISlider {
    @IBInspectable open var trackWidth:CGFloat = 2 {
        didSet {setNeedsDisplay()}
    }

    override open func trackRect(forBounds bounds: CGRect) -> CGRect {
        let defaultBounds = super.trackRect(forBounds: bounds)
        return CGRect(
            x: defaultBounds.origin.x,
            y: defaultBounds.origin.y + defaultBounds.size.height/2 - trackWidth/2,
            width: defaultBounds.size.width,
            height: trackWidth
        )
    }
}
