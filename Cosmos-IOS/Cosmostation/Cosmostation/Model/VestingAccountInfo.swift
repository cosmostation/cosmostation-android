//
//  VestingAccountInfo.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/03/29.
//  Copyright © 2021 wannabit. All rights reserved.
//

import Foundation

public struct VestingAccountInfo {
    var height: String?
    var result: VestingAccountInfoResult?
    
    init(_ dictionary: NSDictionary?) {
        self.height = dictionary?["height"] as? String
        if let rawResult = dictionary?["result"] as? NSDictionary {
            self.result = VestingAccountInfoResult.init(rawResult)
        }
    }
    
    public struct VestingAccountInfoResult {
        var type: String?
        var value: VestingAccountInfoValue?
        
        init(_ dictionary: NSDictionary?) {
            self.type = dictionary?["type"] as? String
            if let rawValue = dictionary?["value"] as? NSDictionary {
                self.value = VestingAccountInfoValue.init(rawValue)
            }
        }
        
        func getCalcurateVesting() -> Array<VestingPeriod> {
            var results: Array<VestingPeriod> = Array<VestingPeriod>()
            let cTime = Date().millisecondsSince1970
            for i in 0..<value!.vesting_periods!.count {
                let unlockTime = getUnLockTime(i)
                if (cTime < unlockTime) {
                    results.append(VestingPeriod.init(String(unlockTime), value!.vesting_periods![i].amount!))
                }
            }
            return results
        }
        
        func getCalcurateVestingCntByDenom(_ denom: String) -> Int {
            var results = 0
            getCalcurateVesting().forEach { (vp) in
                vp.amount?.forEach({ (coin) in
                    if (coin.denom == denom) {
                        results = results + 1
                    }
                })
            }
            return results
        }
        
        func getCalcurateVestingByDenom(_ denom: String) -> Array<VestingPeriod> {
            var results: Array<VestingPeriod> = Array<VestingPeriod>()
            getCalcurateVesting().forEach { (vp) in
                vp.amount?.forEach({ (coin) in
                    if (coin.denom == denom) {
                        results.append(vp)
                    }
                })
            }
            return results
        }
        
        func getCalcurateTime(_ denom: String, _ position: Int) -> Int64 {
            return getCalcurateVestingByDenom(denom)[position].getlength();
        }
        
        func getCalcurateAmount(_ denom: String, _ position: Int) -> NSDecimalNumber {
            var result = NSDecimalNumber.zero
            getCalcurateVestingByDenom(denom)[position].amount?.forEach({ (coin) in
                if (coin.denom ==  denom) {
                    result = NSDecimalNumber.init(string: coin.amount)
                }
            })
            return result
        }
        
        func getCalcurateVestingAmountSumByDenom(_ denom: String) -> NSDecimalNumber {
            var results = NSDecimalNumber.zero
            getCalcurateVestingByDenom(denom).forEach { (vp) in
                vp.amount?.forEach({ (coin) in
                    if (coin.denom ==  denom) {
                        results = results.adding(NSDecimalNumber.init(string: coin.amount))
                    }
                })
            }
            return results
        }
        
        func getUnLockTime(_ position:Int) -> Int64 {
            var result = value!.getStartTime()
            for i in 0..<(position + 1) {
                result = result + value!.vesting_periods![i].getlength()
            }
            return result * 1000
        }
    }
    
    public struct VestingAccountInfoValue {
        var address: String?
        var coins: Array<Coin>?
        var account_number: String?
        var sequence: String?
        var original_vesting: Array<Coin>?
        var delegated_free: Array<Coin>?
        var delegated_vesting: Array<Coin>?
        var start_time: String?
        var end_time: String?
        var vesting_periods: Array<VestingPeriod>?
        
        init(_ dictionary: NSDictionary?) {
            self.address = dictionary?["address"] as? String
            if let rawCoins = dictionary?["coins"] as? Array<NSDictionary> {
                self.coins = Array<Coin>()
                for rawCoin in rawCoins {
                    self.coins!.append(Coin.init(rawCoin))
                }
            }
            self.account_number = dictionary?["account_number"] as? String
            self.sequence = dictionary?["sequence"] as? String
            if let rawOriginalVestings = dictionary?["original_vesting"] as? Array<NSDictionary> {
                self.original_vesting = Array<Coin>()
                for rawOriginalVesting in rawOriginalVestings {
                    self.original_vesting!.append(Coin.init(rawOriginalVesting))
                }
            }
            if let rawDelegatedFrees = dictionary?["delegated_free"] as? Array<NSDictionary> {
                self.delegated_free = Array<Coin>()
                for rawDelegatedFree in rawDelegatedFrees {
                    self.delegated_free!.append(Coin.init(rawDelegatedFree))
                }
            }
            if let rawDelegatedVestings = dictionary?["delegated_vesting"] as? Array<NSDictionary> {
                self.delegated_vesting = Array<Coin>()
                for rawDelegatedVesting in rawDelegatedVestings {
                    self.delegated_vesting!.append(Coin.init(rawDelegatedVesting))
                }
            }
            self.start_time = dictionary?["start_time"] as? String
            self.end_time = dictionary?["end_time"] as? String
            if let rawVestingPeriods = dictionary?["vesting_periods"] as? Array<NSDictionary> {
                self.vesting_periods = Array<VestingPeriod>()
                for rawVestingPeriod in rawVestingPeriods {
                    self.vesting_periods!.append(VestingPeriod.init(rawVestingPeriod))
                }
            }
        }
        
        public func getStartTime() -> Int64 {
            return Int64(start_time!)!
        }
        
        public func getEndTime() -> Int64 {
            return Int64(end_time!)!
        }
    }
    
    public struct VestingPeriod {
        var length: String?
        var amount: Array<Coin>?
        
        init(_ dictionary: NSDictionary?) {
            self.length = dictionary?["length"] as? String
            if let rawAmounts = dictionary?["amount"] as? Array<NSDictionary> {
                self.amount = Array<Coin>()
                for rawAmount in rawAmounts {
                    self.amount!.append(Coin.init(rawAmount))
                }
            }
        }
        
        init(_ len: String, _ rawAmount: Array<Coin>) {
            self.length = len
            self.amount = rawAmount
        }
        
        public func getlength() -> Int64 {
            return Int64(length!)!
        }
    }
}
