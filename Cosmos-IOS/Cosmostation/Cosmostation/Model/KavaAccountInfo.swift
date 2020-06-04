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
    
    public class KavaAccountResult {
        var type: String = ""
        var value: KavaAccountValue?
        
        init() {}
        
        init(_ dictionary: [String: Any]) {
            self.type = dictionary["type"] as? String ?? ""
            self.value = KavaAccountValue.init(dictionary["value"] as! [String : Any])
        }
    }
    
    public class KavaAccountValue {
        var address: String = ""
        var coins: Array<Coin> = Array<Coin>()
        var account_number: String = ""
        var sequence: String = ""
        var PeriodicVestingAccount: KavaPeriodicVestingAccount = KavaPeriodicVestingAccount.init()
        var vesting_period_progress: Array<VestingPeriodProgress> = Array<VestingPeriodProgress>()
        var BaseVestingAccount: KavaBaseVestingAccount = KavaBaseVestingAccount.init()
        var vesting_periods: Array<VestingPeriod> = Array<VestingPeriod>()
        
        var original_vesting: Array<Coin> = Array<Coin>()
        var delegated_vesting: Array<Coin> = Array<Coin>()
        
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
            
            if let pva = dictionary["PeriodicVestingAccount"] as? [String : Any] {
                self.PeriodicVestingAccount = KavaPeriodicVestingAccount.init(pva)
            }

            self.vesting_period_progress.removeAll()
            if let vpps = dictionary["vesting_period_progress"] as? Array<NSDictionary> {
                for vpp in vpps {
                    self.vesting_period_progress.append(VestingPeriodProgress(vpp as! [String : Any]))
                }
            }
            
            if let kba = dictionary["BaseVestingAccount"] as? [String : Any] {
                self.BaseVestingAccount = KavaBaseVestingAccount.init(kba)
            }
            
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
        }
    }
    
    
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

    public class VestingPeriod {
        var amount: Array<Coin> = Array<Coin>()
        
        init() {}
        
        init(_ dictionary: [String: Any]) {
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
