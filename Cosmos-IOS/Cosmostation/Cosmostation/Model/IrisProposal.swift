//
//  IrisProposal.swift
//  Cosmostation
//
//  Created by yongjoo on 10/12/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import Foundation
import UIKit

public class IrisProposal {
    
    static let PROPOSAL_DEPOSIT         = "DepositPeriod"
    static let PROPOSAL_VOTING          = "VotingPeriod"
    static let PROPOSAL_REJECTED        = "Rejected"
    static let PROPOSAL_PASSED          = "Passed"
    
    var type: String = ""
    var value: Value?
    
    init() {}
    
    init(_ dictionary: [String: Any]) {
        self.type = dictionary["type"] as? String ?? ""
        if let rawValue = dictionary["value"] as? [String : Any] {
            self.value = Value.init(rawValue)
        }
    }
    
    public func getStatusImg() -> UIImage? {
        if (value?.basicProposal?.proposal_status == IrisProposal.PROPOSAL_DEPOSIT) {
            return UIImage.init(named: "depositImg")
        } else if (value?.basicProposal?.proposal_status == IrisProposal.PROPOSAL_VOTING) {
            return UIImage.init(named: "votingImg")
        } else if (value?.basicProposal?.proposal_status == IrisProposal.PROPOSAL_REJECTED) {
            return UIImage.init(named: "rejectedImg")
        } else if (value?.basicProposal?.proposal_status == IrisProposal.PROPOSAL_PASSED) {
            return UIImage.init(named: "passedImg")
        }
        return nil
    }
    
    public func getTitle() -> String? {
        return "# " + (value?.basicProposal?.proposal_id)! + ". "  + (value?.basicProposal?.title)!
    }
    
    public func getStartTime() -> String? {
        if (value?.basicProposal?.proposal_status == IrisProposal.PROPOSAL_DEPOSIT) {
            return NSLocalizedString("waiting_deposit", comment: "")
        } else {
            return WUtils.nodeTimetoString(input: (value?.basicProposal?.voting_start_time)!)
        }
    }
    
    public func getEndTime() -> String? {
        if (value?.basicProposal?.proposal_status == IrisProposal.PROPOSAL_DEPOSIT) {
            return NSLocalizedString("waiting_deposit", comment: "")
        } else {
            return WUtils.nodeTimetoString(input: (value?.basicProposal?.voting_end_time)!)
        }
    }
    
    
    public class Value {
        var basicProposal: BasicProposal?
        
        init (_ dictionary: [String: Any]) {
            if let RawBasciProposal = dictionary["BasicProposal"] as? [String : Any] {
                self.basicProposal = BasicProposal.init(RawBasciProposal)
            }
        }
    }
    
    public class BasicProposal {
        var proposal_id: String = ""
        var title: String = ""
        var description: String = ""
        var proposal_status: String = ""
        var voting_start_time: String = ""
        var voting_end_time: String = ""
        var proposer: String = ""
        var tally_result: Tally?
        
        init(_ dictionary: [String: Any]) {
            self.proposal_id = dictionary["proposal_id"] as? String ?? ""
            self.title = dictionary["title"] as? String ?? ""
            self.description = dictionary["description"] as? String ?? ""
            self.proposal_status = dictionary["proposal_status"] as? String ?? ""
            self.voting_start_time = dictionary["voting_start_time"] as? String ?? ""
            self.voting_end_time = dictionary["voting_end_time"] as? String ?? ""
            self.proposer = dictionary["proposer"] as? String ?? ""
            if let rawTally = dictionary["tally_result"] as? [String : Any] {
                self.tally_result = Tally.init(rawTally)
            }
        }
    }
}
