//
//  Signature.swift
//  Cosmostation
//
//  Created by yongjoo on 25/03/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import Foundation

public struct Signature: Codable {
    var pub_key: PublicKey = PublicKey.init()
    var signature: String = ""
    
    init() {}
    
    init(_ dictionary: [String: Any]) {
        self.signature = dictionary["signature"] as? String ?? ""
        self.pub_key = PublicKey.init(dictionary["pub_key"] as! [String : Any])
    }
}
