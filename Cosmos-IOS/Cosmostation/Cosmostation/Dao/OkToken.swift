//
//  OkToken.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/08/25.
//  Copyright © 2020 wannabit. All rights reserved.
//

import Foundation


public class OkToken {
    var description: String = ""
    var symbol: String = ""
    var original_symbol: String = ""
    var whole_name: String = ""
    var original_total_supply: String = ""
    var total_supply: String = ""
    var owner: String = ""
    var mintable: Bool = false
    
    init() {}
    
    init(_ dictionary: [String: Any]) {
        self.description = dictionary["description"] as? String ?? ""
        self.symbol = dictionary["symbol"] as? String ?? ""
        self.original_symbol = dictionary["original_symbol"] as? String ?? ""
        self.whole_name = dictionary["whole_name"] as? String ?? ""
        self.original_total_supply = dictionary["original_total_supply"] as? String ?? ""
        self.total_supply = dictionary["total_supply"] as? String ?? ""
        self.owner = dictionary["owner"] as? String ?? ""
        self.mintable = dictionary["mintable"] as? Bool ?? false
    }
}
