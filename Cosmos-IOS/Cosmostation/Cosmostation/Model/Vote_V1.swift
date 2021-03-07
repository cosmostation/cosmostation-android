//
//  Vote_V1.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/03/07.
//  Copyright © 2021 wannabit. All rights reserved.
//

import Foundation

public struct Vote_V1 {
    public static let OPTION_YES           = "VOTE_OPTION_YES"
    public static let OPTION_NO            = "VOTE_OPTION_NO"
    public static let OPTION_VETO          = "VOTE_OPTION_NO_WITH_VETO"
    public static let OPTION_ABSTAIN       = "VOTE_OPTION_ABSTAIN"
    
    var proposal_id: String?
    var voter: String?
    var option: String?
    
    init(_ dictionary: NSDictionary?) {
        self.proposal_id = dictionary?["proposal_id"] as? String
        self.voter = dictionary?["voter"] as? String
        self.option = dictionary?["option"] as? String
    }
}
