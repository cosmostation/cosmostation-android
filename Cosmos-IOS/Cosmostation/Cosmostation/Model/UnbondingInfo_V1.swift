//
//  UnbondingInfo_V1.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/12/09.
//  Copyright © 2020 wannabit. All rights reserved.
//

import Foundation

public struct UnbondingInfo_V1 {
    var delegator_address: String?
    var validator_address: String?
    var entries: Array<Entry_V1>?
    
    init(_ dictionary: NSDictionary?) {
        self.delegator_address = dictionary?["delegator_address"] as? String
        self.validator_address = dictionary?["validator_address"] as? String
        if let rawEntries = dictionary?["entries"] as? Array<NSDictionary>  {
            entries = Array<Entry_V1>()
            for rawEntry in rawEntries {
                self.entries!.append(Entry_V1(rawEntry))
            }
        }
    }
    
    public struct Entry_V1 {
        var creation_height: String?
        var completion_time: String?
        var initial_balance: String?
        var balance: String?
        
        init(_ dictionary: NSDictionary?) {
            self.creation_height = dictionary?["creation_height"] as? String
            self.completion_time = dictionary?["completion_time"] as? String
            self.initial_balance = dictionary?["initial_balance"] as? String
            self.balance = dictionary?["balance"] as? String
        }
    }
}
