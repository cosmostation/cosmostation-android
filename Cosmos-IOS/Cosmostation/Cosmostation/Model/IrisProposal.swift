//
//  IrisProposal.swift
//  Cosmostation
//
//  Created by yongjoo on 10/12/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import Foundation


public class IrisProposal {
    var type: String = ""
    var value: Value?
    
    init() {}
    
    init(_ dictionary: [String: Any]) {
        self.type = dictionary["type"] as? String ?? ""
        if let rawValue = dictionary["value"] as? [String : Any] {
            self.value = Value.init(rawValue)
        }
    }
    
    public func getSum() ->NSDecimalNumber {
        var result = NSDecimalNumber.zero
        if let tally = self.value?.basicProposal?.tally_result {
            result = result.adding(NSDecimalNumber.init(string: tally.yes))
            result = result.adding(NSDecimalNumber.init(string: tally.no))
            result = result.adding(NSDecimalNumber.init(string: tally.no_with_veto))
            result = result.adding(NSDecimalNumber.init(string: tally.abstain))
        }
        return result
    }
    
    public func getYes() ->NSDecimalNumber {
        if let tally = self.value?.basicProposal?.tally_result {
            return NSDecimalNumber.init(string: tally.yes).multiplying(byPowerOf10: 2).dividing(by: getSum(), withBehavior: WUtils.handler2)
        }
        return NSDecimalNumber.zero
    }
    
    public func getNo() ->NSDecimalNumber {
        if let tally = self.value?.basicProposal?.tally_result {
            return NSDecimalNumber.init(string: tally.no).multiplying(byPowerOf10: 2).dividing(by: getSum(), withBehavior: WUtils.handler2)
        }
        return NSDecimalNumber.zero
    }
    
    public func getVeto() ->NSDecimalNumber {
        if let tally = self.value?.basicProposal?.tally_result {
            return NSDecimalNumber.init(string: tally.no_with_veto).multiplying(byPowerOf10: 2).dividing(by: getSum(), withBehavior: WUtils.handler2)
        }
        return NSDecimalNumber.zero
    }
    
    public func getAbstain() ->NSDecimalNumber {
        if let tally = self.value?.basicProposal?.tally_result {
            return NSDecimalNumber.init(string: tally.abstain).multiplying(byPowerOf10: 2).dividing(by: getSum(), withBehavior: WUtils.handler2)
        }
        return NSDecimalNumber.zero
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
        var tally_result: TallyResult?
        
        init(_ dictionary: [String: Any]) {
            self.proposal_id = dictionary["proposal_id"] as? String ?? ""
            self.title = dictionary["title"] as? String ?? ""
            self.description = dictionary["description"] as? String ?? ""
            self.proposal_status = dictionary["proposal_status"] as? String ?? ""
            self.voting_start_time = dictionary["voting_start_time"] as? String ?? ""
            self.voting_end_time = dictionary["voting_end_time"] as? String ?? ""
            self.proposer = dictionary["proposer"] as? String ?? ""
            if let rawTally = dictionary["tally_result"] as? [String : Any] {
                self.tally_result = TallyResult.init(rawTally)
            }
        }
    }
    
    public class TallyResult {
        var yes: String = ""
        var abstain: String = ""
        var no: String = ""
        var no_with_veto: String = ""
        
        init(_ dictionary: [String: Any]) {
            self.yes = dictionary["yes"] as? String ?? ""
            self.abstain = dictionary["abstain"] as? String ?? ""
            self.no = dictionary["no"] as? String ?? ""
            self.no_with_veto = dictionary["no_with_veto"] as? String ?? ""
        }
    }
}
