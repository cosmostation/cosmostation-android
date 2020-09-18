//
//  Proposal.swift
//  Cosmostation
//
//  Created by yongjoo on 25/03/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import Foundation
import UIKit

public class Proposal {
    
    static let PROPOSAL_DEPOSIT         = "DepositPeriod"
    static let PROPOSAL_VOTING          = "VotingPeriod"
    static let PROPOSAL_REJECTED        = "Rejected"
    static let PROPOSAL_PASSED          = "Passed"
    
    var id: String = ""
    var proposal_id: String = ""
    var proposal_status: String = ""
    var voting_start_time: String? = ""
    var voting_end_time: String? = ""
    var content: ProposalContent?
    
    init() {}
    
    init(_ dictionary: [String: Any]) {
        self.id = dictionary["id"] as? String ?? ""
        self.proposal_id = dictionary["proposal_id"] as? String ?? ""
        self.proposal_status = dictionary["proposal_status"] as? String ?? ""
        self.voting_start_time = dictionary["voting_start_time"] as? String ?? nil
        self.voting_end_time = dictionary["voting_end_time"] as? String ?? nil
        if let content = dictionary["content"] as? [String : Any] {
            self.content = ProposalContent.init(content)
        }
    }
    
    public func getStatusImg() -> UIImage? {
        if (proposal_status == Proposal.PROPOSAL_DEPOSIT) {
            return UIImage.init(named: "depositImg")
        } else if (proposal_status == Proposal.PROPOSAL_VOTING) {
            return UIImage.init(named: "votingImg")
        } else if (proposal_status == Proposal.PROPOSAL_REJECTED) {
            return UIImage.init(named: "rejectedImg")
        } else if (proposal_status == Proposal.PROPOSAL_PASSED) {
            return UIImage.init(named: "passedImg")
        }
        return nil
    }
    
    public func getTitle() -> String? {
        return "# " + id + ". "  + (content?.value.title)!
    }
    
    public func getStartTime() -> String? {
        if (proposal_status == Proposal.PROPOSAL_DEPOSIT) {
            return NSLocalizedString("waiting_deposit", comment: "")
        } else {
            return WUtils.nodeTimetoString(input: voting_start_time)
        }
    }
    
    public func getEndTime() -> String? {
        if (proposal_status == Proposal.PROPOSAL_DEPOSIT) {
            return NSLocalizedString("waiting_deposit", comment: "")
        } else {
            return WUtils.nodeTimetoString(input: voting_end_time)
        }
    }
    
    public class ProposalContent {
        var type: String = ""
        var value: Value = Value.init()
        
        init() {}
        
        init(_ dictionary: [String: Any]) {
            self.type = dictionary["type"] as? String ?? ""
            if let value = dictionary["value"] as? [String : Any] {
                self.value = Value.init(value)
            }
        }
    }
    
    public class Value {
        var title: String = ""
        var description: String = ""
        var recipient: String = ""
        var amount: Array<Coin>?
        
        init() {}
        
        init(_ dictionary: [String: Any]) {
            self.title = dictionary["title"] as? String ?? ""
            self.description = dictionary["description"] as? String ?? ""
            self.recipient = dictionary["recipient"] as? String ?? ""
            if let rawAmounts = dictionary["amount"] as? Array<NSDictionary> {
                self.amount = Array<Coin>()
                for rawAmount in rawAmounts {
                    self.amount?.append(Coin(rawAmount as! [String : Any]))
                }
            }
        }
    }
    
}
