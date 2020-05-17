//
//  CosmosTally.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/05/16.
//  Copyright © 2020 wannabit. All rights reserved.
//

import Foundation

public class CosmosTally {
    
    var height: String = ""
    var result: Tally = Tally.init()
    
    init() {}
    
    init(_ dictionary: [String: Any]) {
        self.height = dictionary["height"] as? String ?? ""
        self.result = Tally.init(dictionary["result"] as! [String : Any])
    }
}
