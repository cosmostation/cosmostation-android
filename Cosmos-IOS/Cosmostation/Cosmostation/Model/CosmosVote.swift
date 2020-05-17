//
//  CosmosVote.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/05/16.
//  Copyright © 2020 wannabit. All rights reserved.
//

import Foundation

public class CosmosVote {
    
    var height: String = ""
    var result: Vote = Vote.init()
    
    init() {}
    
    init(_ dictionary: [String: Any]) {
        self.height = dictionary["height"] as? String ?? ""
        self.result = Vote.init(dictionary["result"] as! [String : Any])
    }
}
