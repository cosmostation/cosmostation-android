//
//  CosmosProposer.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/05/18.
//  Copyright © 2020 wannabit. All rights reserved.
//

import Foundation

public class CosmosProposer {
    
    var height: String = ""
    var result: Proposer = Proposer.init()
    
    init() {}
    
    init(_ dictionary: [String: Any]) {
        self.height = dictionary["height"] as? String ?? ""
        self.result = Proposer.init(dictionary["result"] as! [String : Any])
    }
    
    public class Proposer {
        
        var proposal_id: String = ""
        var proposer: String = ""
        
        init() {}
        
        init(_ dictionary: [String: Any]) {
            self.proposal_id = dictionary["proposal_id"] as? String ?? ""
            self.proposer = dictionary["proposer"] as? String ?? ""
        }
    }
    
}
