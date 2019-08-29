//
//  Validator.swift
//  Cosmostation
//
//  Created by yongjoo on 22/03/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import Foundation

public class Validator {
    let UNBONDED = 0;
    let UNBONDING = 1;
    let BONDED = 2;

    var operator_address: String = ""
    var consensus_pubkey: String = ""
    var jailed: Bool = false
    var status: Int = -1;
    var tokens: String = "";
    var delegator_shares: String = "";
    var bond_height: String = "";
    var unbonding_height: String = "";
    var unbonding_time: String = "";
    var description: Description = Description.init();
    var commission: Commission = Commission.init();
    
    init(_ dictionary: [String: Any]) {
        self.operator_address = dictionary["operator_address"] as? String ?? ""
        self.consensus_pubkey = dictionary["operator_address"] as? String ?? ""
        self.jailed = dictionary["jailed"] as? Bool ?? false
        self.status = dictionary["status"] as? Int ?? -1
        self.tokens = dictionary["tokens"] as? String ?? ""
        self.delegator_shares = dictionary["delegator_shares"] as? String ?? ""
        self.bond_height = dictionary["bond_height"] as? String ?? ""
        self.unbonding_height = dictionary["unbonding_height"] as? String ?? ""
        self.unbonding_time = dictionary["unbonding_time"] as? String ?? ""
        
        self.description = Description.init(dictionary["description"] as! [String : Any])
        self.commission = Commission.init(dictionary["commission"] as! [String : Any])
        
    }
    
    public class Description {
        var moniker: String = ""
        var identity: String = ""
        var website: String = "";
        var details = "";
        
        init() {}
        
        init(_ dictionary: [String: Any]) {
            self.moniker = dictionary["moniker"] as? String ?? ""
            self.identity = dictionary["identity"] as? String ?? ""
            self.website = dictionary["website"] as? String ?? ""
            self.details = dictionary["details"] as? String ?? ""
        }
    }
    
    
    public class Commission {
        var rate: String = ""
        var max_rate: String = ""
        var max_change_rate: String = "";
        var update_time: String = "";
        var commission_rates: CommissionRate = CommissionRate.init();
        
        init() {}
        
        init(_ dictionary: [String: Any]) {
            self.rate = dictionary["rate"] as? String ?? ""
            self.max_rate = dictionary["max_rate"] as? String ?? ""
            self.max_change_rate = dictionary["max_change_rate"] as? String ?? ""
            self.update_time = dictionary["update_time"] as? String ?? ""
            if let commission_rates = dictionary["commission_rates"] as? [String : Any] {
                self.commission_rates = CommissionRate.init(commission_rates)
            }
        }
    }
    
    public class CommissionRate {
        var rate: String = ""
        var max_rate: String = ""
        var max_change_rate: String = "";
        
        init() {}
        
        init(_ dictionary: [String: Any]) {
            self.rate = dictionary["rate"] as? String ?? ""
            self.max_rate = dictionary["max_rate"] as? String ?? ""
            self.max_change_rate = dictionary["max_change_rate"] as? String ?? ""
        }
    }
    
    
    
}
