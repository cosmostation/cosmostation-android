//
//  KavaHarvestAccount.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/10/14.
//  Copyright © 2020 wannabit. All rights reserved.
//

import Foundation

public class KavaHarvestAccount {
    var height: String = ""
    var result: Array<HarvestAccount> = Array<HarvestAccount>()
    
    init() {}
    
    init(_ dictionary: NSDictionary) {
        self.height = dictionary["height"] as? String ?? ""
        if let rawResults = dictionary["result"] as? Array<NSDictionary> {
            self.result.removeAll()
            for rawResult in rawResults {
                self.result.append(HarvestAccount(rawResult as! [String : Any]))
            }
        }
    }
    
    public class HarvestAccount {
        var type: String = ""
        var value: HarvestAccountValue = HarvestAccountValue.init()
        
        init() {}
        
        init(_ dictionary: [String: Any]) {
            self.type = dictionary["type"] as? String ?? ""
            self.value = HarvestAccountValue.init(dictionary["value"] as! [String : Any])
        }
    }
    
    public class HarvestAccountValue {
        var address: String = ""
        var account_number: String = ""
        var sequence: String = ""
        var name: String = ""
        var coins: Array<Coin> = Array<Coin>()
        
        init() {}
        
        init(_ dictionary: [String: Any]) {
            self.address = dictionary["address"] as? String ?? ""
            self.account_number = dictionary["account_number"] as? String ?? ""
            self.sequence = dictionary["sequence"] as? String ?? ""
            self.name = dictionary["name"] as? String ?? ""
            
            self.coins.removeAll()
            if let rawCoins = dictionary["coins"] as? Array<NSDictionary> {
                for coin in rawCoins {
                    self.coins.append(Coin(coin as! [String : Any]))
                }
            }
        }
    }
}
