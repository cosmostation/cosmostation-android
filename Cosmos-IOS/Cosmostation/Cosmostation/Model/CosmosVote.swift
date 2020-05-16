//
//  CosmosVote.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/05/16.
//  Copyright © 2020 wannabit. All rights reserved.
//

import Foundation

public class CosmosVote {
    
    static let OPTION_YES           = "Yes"
    static let OPTION_NO            = "No"
    static let OPTION_VETO          = "NoWithVeto"
    static let OPTION_ABSTAIN       = "Abstain"
    
    var height: String = ""
    var result: CosmosVoteResult = CosmosVoteResult.init()
    
    init() {}
    
    init(_ dictionary: [String: Any]) {
        self.height = dictionary["height"] as? String ?? ""
        self.result = CosmosVoteResult.init(dictionary["result"] as! [String : Any])
    }
    
    public class CosmosVoteResult {
        var voter: String = ""
        var proposal_id: String = ""
        var option: String = ""
        
        init() {}
        
        init(_ dictionary: [String: Any]) {
            self.voter = dictionary["voter"] as? String ?? ""
            self.proposal_id = dictionary["proposal_id"] as? String ?? ""
            self.option = dictionary["option"] as? String ?? ""
        }
    }
}
