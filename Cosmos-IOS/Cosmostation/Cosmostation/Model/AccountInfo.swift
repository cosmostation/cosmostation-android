//
//  AccountInfo.swift
//  Cosmostation
//
//  Created by yongjoo on 22/03/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import Foundation

public class AccountInfo {
    var type: String = ""
    var value: Value = Value.init()
    
    init() {}
    
    init(_ dictionary: [String: Any]) {
        self.type = dictionary["type"] as? String ?? ""
        self.value = Value.init(dictionary["value"] as! [String : Any])
    }
    
    public class Value {
        var address: String = ""
        var coins: Array<Coin> = Array<Coin>()
        var public_key: PublicKey = PublicKey.init()
        var account_number: String = ""
        var sequence: String = ""
        var BaseVestingAccount: BaseVestingAccountC = BaseVestingAccountC.init()
        
        init() {}
        
        init(_ dictionary: [String: Any]) {
            self.address = dictionary["address"] as? String ?? ""
            self.account_number = dictionary["account_number"] as? String ?? ""
            self.sequence = dictionary["sequence"] as? String ?? ""
            
            if let pkey = dictionary["public_key"] as? [String : Any] {
                self.public_key = PublicKey.init(pkey)
            }
            
            if let bva = dictionary["BaseVestingAccount"] as? [String : Any] {
                self.BaseVestingAccount = BaseVestingAccountC.init(bva)
            }
            
            self.coins.removeAll()
            if let rawCoins = dictionary["coins"] as? Array<NSDictionary> {
                for coin in rawCoins {
                    self.coins.append(Coin(coin as! [String : Any]))
                }
            }
        }
    }
    
    
    public class BaseVestingAccountC {
        var BaseAccount: BaseAccountC = BaseAccountC.init()
        var OriginalVesting: Array<Coin> = Array<Coin>()
        var DelegatedFree: Array<Coin> = Array<Coin>()
        var DelegatedVesting: Array<Coin> = Array<Coin>()
        var EndTime: String = ""
        
        init() {}
        
        init(_ dictionary: [String: Any]) {
            self.BaseAccount = BaseAccountC.init(dictionary["BaseAccount"] as! [String : Any])
            self.EndTime = dictionary["EndTime"] as? String ?? ""
            
            self.OriginalVesting.removeAll()
            let oriCoins = dictionary["original_vesting"] as! Array<NSDictionary>
            for coin in oriCoins {
                self.OriginalVesting.append(Coin(coin as! [String : Any]))
            }
            
            self.DelegatedFree.removeAll()
            let deleCoins = dictionary["delegated_free"] as! Array<NSDictionary>
            for coin in deleCoins {
                self.DelegatedFree.append(Coin(coin as! [String : Any]))
            }
            
            self.DelegatedVesting.removeAll()
            let vestCoins = dictionary["delegated_vesting"] as! Array<NSDictionary>
            for coin in vestCoins {
                self.DelegatedVesting.append(Coin(coin as! [String : Any]))
            }
        }
    }
    
    
    public class BaseAccountC {
        var address: String = ""
        var coins: Array<Coin> = Array<Coin>()
        var public_key: PublicKey = PublicKey.init()
        var account_number: String = ""
        var sequence: String = ""
        
        init() {}
        
        init(_ dictionary: [String: Any]) {
            self.address = dictionary["address"] as? String ?? ""
            self.public_key = PublicKey.init(dictionary["public_key"] as! [String : Any])
            self.account_number = dictionary["account_number"] as? String ?? ""
            self.sequence = dictionary["sequence"] as? String ?? ""
            
            self.coins.removeAll()
            let rawCoins = dictionary["coins"] as! Array<NSDictionary>
            for coin in rawCoins {
                self.coins.append(Coin(coin as! [String : Any]))
            }
        }
    }
}
