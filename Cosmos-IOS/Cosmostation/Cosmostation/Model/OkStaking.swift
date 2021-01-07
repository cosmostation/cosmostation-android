//
//  OkDeposit.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/08/26.
//  Copyright © 2020 wannabit. All rights reserved.
//

import Foundation

public struct OkStaking {
    var delegator_address: String?
    var validator_address: Array<String>?
    var shares: String?
    var tokens: String?
    var is_proxy: Bool?
    var total_delegated_tokens: String?
    var proxy_address: String?
    
    init(_ dictionary: NSDictionary?) {
        self.delegator_address = dictionary?["delegator_address"] as? String
        if let rawValidators = dictionary?["validator_address"] as? Array<String>  {
            validator_address = Array<String>()
            for rawValidator in rawValidators {
                self.validator_address!.append(rawValidator)
            }
        }
        self.shares = dictionary?["shares"] as? String
        self.tokens = dictionary?["tokens"] as? String
        self.is_proxy = dictionary?["is_proxy"] as? Bool
        self.total_delegated_tokens = dictionary?["total_delegated_tokens"] as? String
        self.proxy_address = dictionary?["proxy_address"] as? String
    }
}
