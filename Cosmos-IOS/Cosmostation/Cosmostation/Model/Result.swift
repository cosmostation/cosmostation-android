//
//  Result.swift
//  Cosmostation
//
//  Created by yongjoo on 25/03/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import Foundation

public struct Result {
    var code: Int = 0
    var gas_wanted: String = ""
    var gas_used: String = ""
    var log: Data?
    var allResult: Bool = true
    
    init() {}
    
    init(_ dictionary: [String: Any]) {
        self.code = dictionary["Code"] as? Int ?? -1
        self.gas_wanted = dictionary["gas_wanted"] as? String ?? ""
        self.gas_used = dictionary["gas_used"] as? String ?? ""
        if let logs = dictionary["log"] as? Array<NSDictionary> {
//            print("log Array ", logs.count, "   ", logs)
            for log in logs {
                if let success = log.object(forKey: "success") as? Bool {
                    if(!success) {
                        self.allResult = false
                        return;
                    }
                }
            }
        }
        
        if let log = dictionary["log"] as? NSDictionary {
//            print("log NSDictionary ", log)
            if let code = log.object(forKey: "code") as? Int {
                if(code > -1) {
                    self.allResult = false
                    return;
                }
            }
        }
    }
}
