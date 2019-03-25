//
//  Signature.swift
//  Cosmostation
//
//  Created by yongjoo on 25/03/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import Foundation

public class Signature {
    var pub_key: PublicKey = PublicKey.init()
    var signature: String = ""
    
    init(_ dictionary: [String: Any]) {
        self.signature = dictionary["signature"] as? String ?? ""
        self.pub_key = PublicKey.init(dictionary["pub_key"] as! [String : Any])
    }
}
