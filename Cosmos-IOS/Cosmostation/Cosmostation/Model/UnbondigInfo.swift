//
//  UnbondigInfo.swift
//  Cosmostation
//
//  Created by yongjoo on 22/03/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import Foundation

public class UnbondingInfo {
    var delegator_address:String = "";
    var validator_address:String = "";
    var entries: Array<Entry> = Array<Entry>()
    
    init() {}
    
    init(_ dictionary: [String: Any]) {
        self.delegator_address = dictionary["delegator_address"] as? String ?? ""
        self.validator_address = dictionary["validator_address"] as? String ?? ""
        
        self.entries.removeAll()
//        let rawEntries = dictionary["entries"] as! Array<NSDictionary>
//        for entry in rawEntries {
//            self.entries.append(Entry.init(entry as! [String : Any]))
//        }
        if let rawEntries = dictionary["entries"] as? Array<NSDictionary> {
            for entry in rawEntries {
                self.entries.append(Entry.init(entry as! [String : Any]))
            }
        }
    }
    
    
    public class Entry {
        var creation_height:String = "";
        var completion_time:String = "";
        var initial_balance:String = "";
        var balance:String = "";
        
        init() {}
        
        init(_ dictionary: [String: Any]) {
            self.creation_height = dictionary["creation_height"] as? String ?? ""
            self.completion_time = dictionary["completion_time"] as? String ?? ""
            self.initial_balance = dictionary["initial_balance"] as? String ?? ""
            self.balance = dictionary["balance"] as? String ?? ""
        }
    }
}
