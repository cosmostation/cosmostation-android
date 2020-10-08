//
//  KavaHavestDeposit.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/10/08.
//  Copyright © 2020 wannabit. All rights reserved.
//

import Foundation

public class KavaHavestDeposit {
    var height: String = ""
    var result: Array<HavestDeposit> = Array<HavestDeposit>()
    
    init() {}
    
    init(_ dictionary: NSDictionary) {
        self.height = dictionary["height"] as? String ?? ""
        if let rawResults = dictionary["result"] as? Array<NSDictionary> {
            self.result.removeAll()
            for rawResult in rawResults {
                self.result.append(HavestDeposit(rawResult as! [String : Any]))
            }
        }
    }
    
    public class HavestDeposit {
        var depositor: String = ""
        var amount: Coin = Coin.init()
        var type: String = ""
        
        init() {}
        
        init(_ dictionary: [String: Any]) {
            self.depositor = dictionary["depositor"] as? String ?? ""
            self.amount =  Coin.init(dictionary["amount"] as! [String : Any])
            self.type = dictionary["type"] as? String ?? ""
        }
    }
}
