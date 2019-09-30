//
//  IrisToken.swift
//  Cosmostation
//
//  Created by yongjoo on 30/09/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import Foundation

public class IrisToken {
    var base_token: BaseToken?
    
    init() {}
    
    init(_ dictionary: [String: Any]) {
        self.base_token = BaseToken((dictionary["base_token"] as? [String : Any])!)
    }
    
}

public class BaseToken {
    var id: String = ""
    var family: String = ""
    var source: String = ""
    var gateway: String = ""
    var symbol: String = ""
    var name: String = ""
    var canonical_symbol: String = ""
    var validator: String = ""
    var decimal: Int = 0
    
    init() {}
    
    init(_ dictionary: [String: Any]) {
        self.id = dictionary["id"] as? String ?? ""
        self.family = dictionary["family"] as? String ?? ""
        self.source = dictionary["source"] as? String ?? ""
        self.gateway = dictionary["gateway"] as? String ?? ""
        self.symbol = dictionary["symbol"] as? String ?? ""
        self.name = dictionary["name"] as? String ?? ""
        self.canonical_symbol = dictionary["canonical_symbol"] as? String ?? ""
        self.validator = dictionary["validator"] as? String ?? ""
        self.decimal = dictionary["decimal"] as? Int ?? 0
    }
}
