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
    var allResult: Bool = true
    
    init() {}
    
    init(_ dictionary: [String: Any]) {
        self.gas_wanted = dictionary["gas_wanted"] as? String ?? ""
        self.gas_used = dictionary["gas_used"] as? String ?? ""
        if let logs = dictionary["log"] as? Array<NSDictionary> {
            for log in logs {
                guard let success = log.object(forKey: "success") as? Bool else {
                    return
                }
                if(!success) {
                    self.allResult = false
                }
            }
            return
        }
    }
}
