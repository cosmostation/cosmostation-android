//
//  HistoryMsg.swift
//  Cosmostation
//
//  Created by yongjoo on 09/05/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import Foundation

public struct HistoryMsg: Codable {
    var type: String = ""
    var value: Value = Value.init()
    
    init() {}
    
    init(_ dictionary: [String: Any]) {
        self.type = dictionary["type"] as? String ?? ""
        self.value = Value.init(dictionary["value"] as! [String : Any])
    }
    
    
    public struct Value: Codable {
        var from_address: String?
        var to_address: String?
        
        init() {}
        
        init(_ dictionary: [String: Any]) {
            if let fddress =  dictionary["from_address"] as? String {
                self.from_address = fddress
            }
            
            if let tddress =  dictionary["to_address"] as? String {
                self.to_address = tddress
            }
        }
    }
}
