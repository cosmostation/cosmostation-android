//
//  Proposal.swift
//  Cosmostation
//
//  Created by yongjoo on 25/03/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import Foundation

public class Proposal {
    var proposal_id: String = ""
    var proposal_status: String = ""
    var proposal_content: ProposalContent?
    var value: IrisValue?
    
    init() {}
    
    init(_ dictionary: [String: Any]) {
        self.proposal_id = dictionary["proposal_id"] as? String ?? ""
        self.proposal_status = dictionary["proposal_status"] as? String ?? ""
        if let pc = dictionary["proposal_content"] as? [String : Any] {
            self.proposal_content = ProposalContent.init(pc)
        }
        if let irisValue = dictionary["value"] as? [String : Any] {
            self.value = IrisValue.init(irisValue)
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
        
        init() {}
        
        init(_ dictionary: [String: Any]) {
            self.title = dictionary["title"] as? String ?? ""
            self.description = dictionary["description"] as? String ?? ""
        }
    }
    
    public class IrisValue {
        var basicProposal: BasicProposal?
        
        init(_ dictionary: [String: Any]) {
            if let value = dictionary["BasicProposal"] as? [String : Any] {
                self.basicProposal = BasicProposal.init(value)
            }
        }
    }
    
    public class BasicProposal {
        var proposal_id: String = ""
        var title: String = ""
        var description: String = ""
        var proposal_status: String = ""
        
        init(_ dictionary: [String: Any]) {
            self.proposal_id = dictionary["proposal_id"] as? String ?? ""
            self.title = dictionary["title"] as? String ?? ""
            self.description = dictionary["description"] as? String ?? ""
            self.proposal_status = dictionary["proposal_status"] as? String ?? ""
        }
    }
    
}
/*
public class Proposal {
    var type: String = ""
    var value: Value = Value.init()
    
    init() {}
    
    
    
    init(_ dictionary: [String: Any]) {
        self.type = dictionary["type"] as? String ?? ""
        self.value = Value.init(dictionary["value"] as! [String : Any])
    }
    
    
    public class Value {
        var proposal_id: String = ""
        var title: String = ""
        var description: String = ""
        var proposal_status: String = ""
        var final_tally_result: FinalTallyResult = FinalTallyResult.init()
        var submit_time: String = ""
        var deposit_end_time: String = ""
        var total_deposit: Array<Coin> = Array<Coin>()
        var voting_start_time: String = ""
        var voting_end_time: String = ""

        init() {}
        
        init(_ dictionary: [String: Any]) {
            self.proposal_id = dictionary["proposal_id"] as? String ?? ""
            self.title = dictionary["title"] as? String ?? ""
            self.description = dictionary["description"] as? String ?? ""
            self.proposal_status = dictionary["proposal_status"] as? String ?? ""
            self.final_tally_result = FinalTallyResult.init(dictionary["final_tally_result"] as! [String : Any])
            self.submit_time = dictionary["submit_time"] as? String ?? ""
            self.deposit_end_time = dictionary["deposit_end_time"] as? String ?? ""
            self.voting_start_time = dictionary["voting_start_time"] as? String ?? ""
            self.voting_end_time = dictionary["voting_end_time"] as? String ?? ""
            
            self.total_deposit.removeAll()
            let rawDeposit = dictionary["total_deposit"] as! Array<NSDictionary>
            for deposit in rawDeposit {
                self.total_deposit.append(Coin(deposit as! [String : Any]))
            }
        }
    }
    
    
    public class FinalTallyResult {
        var yes: String = ""
        var abstain: String = ""
        var no: String = ""
        var no_with_veto: String = ""
        
        init() {}
        
        init(_ dictionary: [String: Any]) {
            self.yes = dictionary["yes"] as? String ?? ""
            self.abstain = dictionary["abstain"] as? String ?? ""
            self.no = dictionary["no"] as? String ?? ""
            self.no_with_veto = dictionary["no_with_veto"] as? String ?? ""
        }
    }
}
*/
