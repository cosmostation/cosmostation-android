//
//  Proposal.swift
//  Cosmostation
//
//  Created by yongjoo on 25/03/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import Foundation

public class Proposal {
    var id: String = ""
    var proposal_id: String = ""
    var proposal_status: String = ""
    var content: ProposalContent?
    
    init() {}
    
    init(_ dictionary: [String: Any]) {
        self.id = dictionary["id"] as? String ?? ""
        self.proposal_id = dictionary["proposal_id"] as? String ?? ""
        self.proposal_status = dictionary["proposal_status"] as? String ?? ""
        if let content = dictionary["content"] as? [String : Any] {
            self.content = ProposalContent.init(content)
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
    
}
