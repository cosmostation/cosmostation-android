//
//  Proposal_V1.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/02/18.
//  Copyright © 2021 wannabit. All rights reserved.
//

import Foundation
import UIKit

public struct Proposal_V1 {
    var proposal_id: String?
    var status: String?
    var content: Proposal_V1Content?
    
    
    init(_ dictionary: NSDictionary?) {
        self.proposal_id = dictionary?["proposal_id"] as? String ?? ""
        self.status = dictionary?["status"] as? String ?? ""
        if let rawContent = dictionary?["content"] as? NSDictionary {
            self.content = Proposal_V1Content.init(rawContent)
        }
    }
    
    public struct Proposal_V1Content {
        var type: String = ""
        var title: String = ""
        var description: String = ""
        
        init(_ dictionary: NSDictionary?) {
            self.type = dictionary?["@type"] as? String ?? ""
            self.title = dictionary?["title"] as? String ?? ""
            self.description = dictionary?["description"] as? String ?? ""
        }
    }
    
    public func getStatusImg() -> UIImage? {
        if (status == "PROPOSAL_STATUS_DEPOSIT_PERIOD") {
            return UIImage.init(named: "depositImg")
        } else if (status == "PROPOSAL_STATUS_VOTING_PERIOD") {
            return UIImage.init(named: "votingImg")
        } else if (status == "PROPOSAL_STATUS_REJECTED") {
            return UIImage.init(named: "rejectedImg")
        } else if (status == "PROPOSAL_STATUS_PASSED") {
            return UIImage.init(named: "passedImg")
        }
        return nil
    }
    
    public func getStatusText() -> String {
        if (status == "PROPOSAL_STATUS_DEPOSIT_PERIOD") {
            return "DepositPeriod"
        } else if (status == "PROPOSAL_STATUS_VOTING_PERIOD") {
            return "VotingPeriod"
        } else if (status == "PROPOSAL_STATUS_REJECTED") {
            return "Rejected"
        } else if (status == "PROPOSAL_STATUS_PASSED") {
            return "Passed"
        }
        return "UnKnown"
    }
    
    public func getTitle() -> String? {
        return "# " + self.proposal_id! + ". "  + (content!.title)
    }
}
