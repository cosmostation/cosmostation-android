//
//  Validator_V1.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/12/09.
//  Copyright © 2020 wannabit. All rights reserved.
//

import Foundation

let UNBONDED_V1 = "BOND_STATUS_UNBONDED";
let UNBONDING_V1 = "BOND_STATUS_UNBONDING";
let BONDED_V1 = "BOND_STATUS_BONDED";

public class Validator_V1 {
    var operator_address: String?
//    var consensus_pubkey: String?
    var jailed: Bool?
    var status: String?
    var tokens: String?
    var delegator_shares: String?
    var bond_height: String?
    var unbonding_height: String?
    var unbonding_time: String?
    var min_self_delegation: String?
    var description: Description_V1?
    var commission: Commission_V1?
    
    init(_ dictionary: NSDictionary?) {
        self.operator_address = dictionary?["operator_address"] as? String
//        self.consensus_pubkey = dictionary?["operator_address"] as? String
        self.jailed = dictionary?["jailed"] as? Bool
        self.status = dictionary?["status"] as? String
        self.tokens = dictionary?["tokens"] as? String
        self.delegator_shares = dictionary?["delegator_shares"] as? String
        self.bond_height = dictionary?["bond_height"] as? String
        self.unbonding_height = dictionary?["unbonding_height"] as? String
        self.unbonding_time = dictionary?["unbonding_time"] as? String
        self.min_self_delegation = dictionary?["min_self_delegation"] as? String
        self.description = Description_V1.init(dictionary?["description"] as? NSDictionary)
        self.commission = Commission_V1.init(dictionary?["commission"] as? NSDictionary)
    }
    
    public struct Description_V1 {
        var moniker: String?
        var identity: String?
        var website: String?
        var securityContact: String?
        var details: String?
        
        init(_ dictionary: NSDictionary?) {
            self.moniker = dictionary?["moniker"] as? String
            self.identity = dictionary?["identity"] as? String
            self.website = dictionary?["website"] as? String
            self.securityContact = dictionary?["security_contact"] as? String
            self.details = dictionary?["details"] as? String
        }
    }
    
    public struct Commission_V1 {
        var commission_rates: CommissionRate_V1?
        var update_time: String?
        
        init(_ dictionary: NSDictionary?) {
            self.commission_rates = CommissionRate_V1.init(dictionary?["commission_rates"] as? NSDictionary)
            self.update_time = dictionary?["update_time"] as? String
        }
    }
    
    public struct CommissionRate_V1 {
        var rate: String?
        var max_rate: String?
        var max_change_rate: String?

        init(_ dictionary: NSDictionary?) {
            self.rate = dictionary?["rate"] as? String
            self.max_rate = dictionary?["max_rate"] as? String
            self.max_change_rate = dictionary?["max_change_rate"] as? String
        }
    }
}
