//
//  IrisVote.swift
//  Cosmostation
//
//  Created by yongjoo on 10/12/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import Foundation


public class IrisVote {
    
    static let OPTION_YES           = "Yes"
    static let OPTION_NO            = "No"
    static let OPTION_VETO          = "NoWithVeto"
    static let OPTION_ABSTAIN       = "Abstain"
    
    var voter: String = ""
    var proposal_id: String = ""
    var option: String = ""
    
    init(_ dictionary: [String: Any]) {
        self.voter = dictionary["voter"] as? String ?? ""
        self.proposal_id = dictionary["proposal_id"] as? String ?? ""
        self.option = dictionary["option"] as? String ?? ""
    }
}
