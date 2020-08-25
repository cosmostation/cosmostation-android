//
//  OkDeposit.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/08/26.
//  Copyright © 2020 wannabit. All rights reserved.
//

import Foundation

public class OkDeposit {
    var delegator_address: String = ""
    var validator_address: Array<String> = Array<String>()
    var shares: String = ""
    var tokens: String = ""
    var is_proxy: Bool = false
    var total_delegated_tokens: String = ""
    var proxy_address: String = ""
    
    init() {}
    
    init(_ dictionary: [String: Any]) {
        self.delegator_address = dictionary["delegator_address"] as? String ?? ""
        self.validator_address.removeAll()
        if let vAddresses = dictionary["validator_address"] as? Array<String> {
            for vAddress in vAddresses {
                self.validator_address.append(vAddress)
            }
        }
        self.shares = dictionary["shares"] as? String ?? ""
        self.tokens = dictionary["tokens"] as? String ?? ""
        self.is_proxy = dictionary["is_proxy"] as? Bool ?? false
        self.total_delegated_tokens = dictionary["total_delegated_tokens"] as? String ?? ""
        self.proxy_address = dictionary["proxy_address"] as? String ?? ""
    }
}
