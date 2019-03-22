//
//  PublicKey.swift
//  Cosmostation
//
//  Created by yongjoo on 22/03/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import Foundation

public class PublicKey {
    var type: String = ""
    var value: String = ""
    
    init() {}
    
    init(_ dictionary: [String: Any]) {
        self.type = dictionary["type"] as? String ?? ""
        self.value = dictionary["value"] as? String ?? ""
    }
}
