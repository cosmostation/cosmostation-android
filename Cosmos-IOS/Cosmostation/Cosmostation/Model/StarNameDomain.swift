//
//  StarNameDomain.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/10/12.
//  Copyright © 2020 wannabit. All rights reserved.
//

import Foundation

public struct StarNameDomain: Codable {
    var name: String = ""
    var admin: String = ""
    var valid_until: Int64 = -1
    var type: String = ""
    var broker: String = ""
    
    init() {}
    
    init(_ dictionary: [String: Any]) {
        self.name = dictionary["name"] as? String ?? ""
        self.admin = dictionary["admin"] as? String ?? ""
        self.valid_until = dictionary["valid_until"] as? Int64 ?? -1
        self.type = dictionary["type"] as? String ?? ""
        self.broker = dictionary["broker"] as? String ?? ""
    }
}
