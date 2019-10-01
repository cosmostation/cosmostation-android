//
//  BnbToken.swift
//  Cosmostation
//
//  Created by yongjoo on 01/10/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import Foundation

public class BnbToken {
    var symbol: String = ""
    var original_symbol: String = ""
    var name: String = ""
    var owner: String = ""
    var total_supply: String = ""
    var mintable: Bool = false
    
    init() {}
    
    init(_ dictionary: [String: Any]) {
        self.symbol = dictionary["symbol"] as? String ?? ""
        self.original_symbol = dictionary["original_symbol"] as? String ?? ""
        self.name = dictionary["name"] as? String ?? ""
        self.owner = dictionary["owner"] as? String ?? ""
        self.total_supply = dictionary["total_supply"] as? String ?? ""
        self.mintable = dictionary["mintable"] as? Bool ?? false
    }
}
