//
//  CdpOwen.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/03/26.
//  Copyright © 2020 wannabit. All rights reserved.
//

import Foundation

public class CdpOwen {
    var height: String = ""
    var result: CdpOwenResult = CdpOwenResult.init()
    
    init() {}
    
    init(_ dictionary: [String: Any]) {
        self.height = dictionary["height"] as? String ?? ""
        self.result = CdpOwenResult.init(dictionary["result"] as! [String : Any])
    }
    
    init(_ dictionary: NSDictionary) {
        self.height = dictionary["height"] as? String ?? ""
        self.result = CdpOwenResult.init(dictionary["result"] as! [String : Any])
    }
    
    public class CdpOwenResult {
        var cdp: Cdp = Cdp.init()
        var collateral_value: Coin = Coin.init()
        var collateralization_ratio: String = ""
        
        init() {}
        
        init(_ dictionary: [String: Any]) {
            self.cdp =  Cdp.init(dictionary["cdp"] as! [String : Any])
            self.collateral_value =  Coin.init(dictionary["collateral_value"] as! [String : Any])
            self.collateralization_ratio = dictionary["collateralization_ratio"] as? String ?? ""
        }
    }
}
