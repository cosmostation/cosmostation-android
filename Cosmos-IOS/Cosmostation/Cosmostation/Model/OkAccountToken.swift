//
//  OkAccountToken.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/08/25.
//  Copyright © 2020 wannabit. All rights reserved.
//

import Foundation

public class OkAccountToken {
    var code: Int = -1
    var msg: String = ""
    var detail_msg: String = ""
    var data = OkTokenData.init()
    
    init() {}
    
    init(_ dictionary: [String: Any]) {
        self.code = dictionary["code"] as? Int ?? -1
        self.msg = dictionary["msg"] as? String ?? ""
        self.detail_msg = dictionary["detail_msg"] as? String ?? ""
        if let rawData = dictionary["data"] as? [String : Any] {
            self.data = OkTokenData.init(rawData)
        }
    }
    
    public class OkTokenData {
        var address: String = ""
        var currencies: Array<OkCurrency> = Array<OkCurrency>()
        
        init() {}
        
        init(_ dictionary: [String: Any]) {
            self.address = dictionary["address"] as? String ?? ""
            self.currencies.removeAll()
            if let currencies = dictionary["currencies"] as? Array<NSDictionary> {
                for currency in currencies {
                    self.currencies.append(OkCurrency(currency as! [String : Any]))
                }
            }
        }
    }
    
    public class OkCurrency {
        var symbol: String = ""
        var available: String = ""
        var locked: String = ""
        
        init() {}
        
        init(_ dictionary: [String: Any]) {
            self.symbol = dictionary["symbol"] as? String ?? ""
            self.available = dictionary["available"] as? String ?? ""
            self.locked = dictionary["locked"] as? String ?? ""
        }
    }
}
