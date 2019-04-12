//
//  Result.swift
//  Cosmostation
//
//  Created by yongjoo on 25/03/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import Foundation

public struct Result {
    var gas_wanted: String = ""
    var gas_used: String = ""
    var log: Data?
    var success: Bool = false
    
    init() {}
    
    init(_ dictionary: [String: Any]) {
        self.gas_wanted = dictionary["gas_wanted"] as? String ?? ""
        self.gas_used = dictionary["gas_used"] as? String ?? ""
        
//        if let logS = dictionary["log"] as? String {
//            print("logS ", logS)
//        }
//
//        if let logD = dictionary["log"] as? NSDictionary {
//            print("logD ", logD)
//        }
//
//        if let logSA = dictionary["log"] as? [String : Any] {
//            print("logSA ", logSA)
//        }
        
        if let logDA = dictionary["log"] as? Array<NSDictionary> {
//            print("logDA ", logDA)
            if let success = logDA[0].object(forKey: "success") as? Bool {
                if(success) {
                    self.success = true
                } else {
                    self.success = false
                }
            }
            
        }
    }
}
