//
//  KavaAccountInfo.swift
//  Cosmostation
//
//  Created by yongjoo on 07/11/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import Foundation


public class KavaAccountInfo {
    var height: String = ""
    var result: KavaAccountResult = KavaAccountResult.init()
    
    init() {}
    
    init(_ dictionary: [String: Any]) {
        self.height = dictionary["height"] as? String ?? ""
        self.result = KavaAccountResult.init(dictionary["result"] as! [String : Any])
    }
    
    init(_ dictionary: NSDictionary?) {
        self.height = dictionary?["height"] as? String ?? ""
        self.result = KavaAccountResult.init(dictionary?["result"] as! [String : Any])
    }
    
    public class KavaAccountResult {
        var type: String = ""
        var value: KavaAccountValue = KavaAccountValue.init()
        
        init() {}
        
        init(_ dictionary: [String: Any]) {
            self.type = dictionary["type"] as? String ?? ""
            self.value = KavaAccountValue.init(dictionary["value"] as! [String : Any])
        }
        
        func getCalcurateVesting() -> Array<VestingPeriod> {
            var results: Array<VestingPeriod> = Array<VestingPeriod>()
            let cTime = Date().millisecondsSince1970
            for i in 0..<value.vesting_periods.count {
                let unlockTime = getUnLockTime(i)
                if (cTime < unlockTime) {
                    let tempVp = VestingPeriod.init()
                    tempVp.length = unlockTime
                    tempVp.amount = value.vesting_periods[i].amount
                    results.append(tempVp)
                }
            }
            return results
        }
        
        func getAllCalcurateVestingCnt() -> Int {
            return getCalcurateVesting().count
        }
        
        func getCalcurateVestingCntByDenom(_ denom: String) -> Int {
            var results = 0
            for vp in getCalcurateVesting() {
                for coin in vp.amount {
                    if (coin.denom ==  denom) {
                        results = results + 1
                    }
                }
            }
            return results
        }
        
        func getCalcurateVestingByDenom(_ denom: String) -> Array<VestingPeriod> {
            var results: Array<VestingPeriod> = Array<VestingPeriod>()
            for vp in getCalcurateVesting() {
                for coin in vp.amount {
                    if (coin.denom ==  denom) {
                        results.append(vp)
                    }
                }
            }
            return results
        }
        
        func getCalcurateTime(_ denom: String, _ position: Int) -> Int64 {
            return getCalcurateVestingByDenom(denom)[position].length;
        }
        
        func getCalcurateAmount(_ denom: String, _ position: Int) -> NSDecimalNumber {
            var result = NSDecimalNumber.zero
            let period = getCalcurateVestingByDenom(denom)[position]
            for coin in period.amount {
                if (coin.denom ==  denom) {
                    result = NSDecimalNumber.init(string: coin.amount)
                }
            }
            return result
        }
        
        func getCalcurateVestingAmountSumByDenom(_ denom: String) -> NSDecimalNumber {
            var results = NSDecimalNumber.zero
            for vp in getCalcurateVestingByDenom(denom) {
                for coin in vp.amount {
                    if (coin.denom ==  denom) {
                        results = results.adding(NSDecimalNumber.init(string: coin.amount))
                    }
                }
            }
            return results
        }
        
        func getUnLockTime(_ position:Int) -> Int64 {
            var result: Int64 = value.start_time
            for i in 0..<(position + 1) {
                result = result + value.vesting_periods[i].length
            }
            return result * 1000
        }
        
    }
    
    public class KavaAccountValue {
        var address: String = ""
        var coins: Array<Coin> = Array<Coin>()
        var account_number: String = ""
        var sequence: String = ""
//        var PeriodicVestingAccount: KavaPeriodicVestingAccount = KavaPeriodicVestingAccount.init()
//        var vesting_period_progress: Array<VestingPeriodProgress> = Array<VestingPeriodProgress>()
//        var BaseVestingAccount: KavaBaseVestingAccount = KavaBaseVestingAccount.init()
        var vesting_periods: Array<VestingPeriod> = Array<VestingPeriod>()
        
        var original_vesting: Array<Coin> = Array<Coin>()
        var delegated_vesting: Array<Coin> = Array<Coin>()
        var start_time: Int64 = 0
        var end_time: Int64 = 0
        
        init() {}
        
        init(_ dictionary: [String: Any]) {
            self.address = dictionary["address"] as? String ?? ""
            if let accountNumber = dictionary["account_number"] as? String {
                self.account_number = accountNumber
            }
            if let accountNumber = dictionary["account_number"] as? Int64 {
                self.account_number = String(accountNumber)
            }
            
            if let seQuence = dictionary["sequence"] as? String {
                self.sequence = seQuence
            }
            if let seQuence = dictionary["sequence"] as? Int64 {
                self.sequence = String(seQuence)
            }
            
            
            self.coins.removeAll()
            if let rawCoins = dictionary["coins"] as? Array<NSDictionary> {
                for coin in rawCoins {
                    self.coins.append(Coin(coin as! [String : Any]))
                }
            }
            
//            if let pva = dictionary["PeriodicVestingAccount"] as? [String : Any] {
//                self.PeriodicVestingAccount = KavaPeriodicVestingAccount.init(pva)
//            }
//
//            self.vesting_period_progress.removeAll()
//            if let vpps = dictionary["vesting_period_progress"] as? Array<NSDictionary> {
//                for vpp in vpps {
//                    self.vesting_period_progress.append(VestingPeriodProgress(vpp as! [String : Any]))
//                }
//            }
//
//            if let kba = dictionary["BaseVestingAccount"] as? [String : Any] {
//                self.BaseVestingAccount = KavaBaseVestingAccount.init(kba)
//            }
            
            self.vesting_periods.removeAll()
            if let vps = dictionary["vesting_periods"] as? Array<NSDictionary> {
                for vp in vps {
                    self.vesting_periods.append(VestingPeriod(vp as! [String : Any]))
                }
            }
            
            self.original_vesting.removeAll()
            if let rawOriginal_vesting = dictionary["original_vesting"] as? Array<NSDictionary> {
                for coin in rawOriginal_vesting {
                    self.original_vesting.append(Coin(coin as! [String : Any]))
                }
            }
            
            self.delegated_vesting.removeAll()
            if let rawDelegated_vesting = dictionary["delegated_vesting"] as? Array<NSDictionary> {
                for coin in rawDelegated_vesting {
                    self.delegated_vesting.append(Coin(coin as! [String : Any]))
                }
            }
            
            if let startTime = dictionary["start_time"] as? Int64 {
                self.start_time = startTime
            }
            if let startTime = dictionary["start_time"] as? String {
                self.start_time = Int64(startTime) ?? 0
            }
            
            if let endTime = dictionary["end_time"] as? Int64 {
                self.end_time = endTime
            }
            if let endTime = dictionary["end_time"] as? String {
                self.end_time = Int64(endTime) ?? 0
            }
        }
    }
    
    /*
    public class KavaPeriodicVestingAccount {
        var BaseVestingAccount: KavaBaseVestingAccount = KavaBaseVestingAccount.init()
        var vesting_periods: Array<VestingPeriod> = Array<VestingPeriod>()
        
        init() {}

        init(_ dictionary: [String: Any]) {
            self.BaseVestingAccount = KavaBaseVestingAccount.init(dictionary["BaseVestingAccount"] as! [String : Any])
            
            self.vesting_periods.removeAll()
            let vps = dictionary["vesting_periods"] as! Array<NSDictionary>
            for vp in vps {
                self.vesting_periods.append(VestingPeriod(vp as! [String : Any]))
            }
        }
    }

   
    public class KavaBaseVestingAccount {
        var BaseAccount: KavaBaseAccount = KavaBaseAccount.init()
        var original_vesting: Array<Coin> = Array<Coin>()
        var delegated_vesting: Array<Coin> = Array<Coin>()
        var end_time: String = ""
        
        init() {}
        
        init(_ dictionary: [String: Any]) {
            self.BaseAccount = KavaBaseAccount.init(dictionary["BaseAccount"] as! [String : Any])
            self.end_time = dictionary["end_time"] as? String ?? ""
            
            self.original_vesting.removeAll()
            let rawCoins1 = dictionary["original_vesting"] as! Array<NSDictionary>
            for coin in rawCoins1 {
                self.original_vesting.append(Coin(coin as! [String : Any]))
            }
            
            self.delegated_vesting.removeAll()
            let rawCoins2 = dictionary["delegated_vesting"] as! Array<NSDictionary>
            for coin in rawCoins2 {
                self.delegated_vesting.append(Coin(coin as! [String : Any]))
            }
        }
    }

    public class KavaBaseAccount {
        var address: String = ""
        var coins: Array<Coin> = Array<Coin>()
        var account_number: String = ""
        var sequence: String = ""
        
        init() {}
        
        init(_ dictionary: [String: Any]) {
            self.address = dictionary["address"] as? String ?? ""
            if let accountNumber = dictionary["account_number"] as? String {
                self.account_number = accountNumber
            }
            if let accountNumber = dictionary["account_number"] as? Int64 {
                self.account_number = String(accountNumber)
            }
            
            if let seQuence = dictionary["sequence"] as? String {
                self.sequence = seQuence
            }
            if let seQuence = dictionary["sequence"] as? Int64 {
                self.sequence = String(seQuence)
            }
            
            
            self.coins.removeAll()
            let rawCoins = dictionary["coins"] as! Array<NSDictionary>
            for coin in rawCoins {
                self.coins.append(Coin(coin as! [String : Any]))
            }
        }
    }
     */

    public class VestingPeriod {
        var length: Int64 = 0
        var amount: Array<Coin> = Array<Coin>()
        
        init() {}
        
        init(_ dictionary: [String: Any]) {
            if let length = dictionary["length"] as? Int64 {
                self.length = length
            }
            if let length = dictionary["length"] as? String {
                self.length = Int64(length) ?? 0
            }
            
            self.amount.removeAll()
            let rawCoins = dictionary["amount"] as! Array<NSDictionary>
            for coin in rawCoins {
                self.amount.append(Coin(coin as! [String : Any]))
            }
        }
    }

    public class VestingPeriodProgress{
        var period_complete = false
        var vesting_successful = false
        
        init() {}
        
        init(_ dictionary: [String: Any]) {
            self.period_complete = dictionary["period_complete"] as? Bool ?? false
            self.vesting_successful = dictionary["vesting_successful"] as? Bool ?? false
        }
    }
}
