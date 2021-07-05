//
//  IbcToken.swift
//  Cosmostation
//
//  Created by yongjoo jung on 2021/07/04.
//  Copyright © 2021 wannabit. All rights reserved.
//

import Foundation

public struct IbcToken {
    var auth: Bool?
    var hash: String?
    var base_denom: String?
    var display_denom: String?
    var decimal: Int16?
    var channel_id: String?
    var port_id: String?
    var moniker: String?
    var counter_party: TokenCounterParty?
    
    init(_ dictionary: NSDictionary?) {
        self.auth = dictionary?["auth"] as? Bool
        self.hash = dictionary?["hash"] as? String
        self.base_denom = dictionary?["base_denom"] as? String
        self.display_denom = dictionary?["display_denom"] as? String
        self.decimal = dictionary?["decimal"] as? Int16
        self.channel_id = dictionary?["channel_id"] as? String
        self.port_id = dictionary?["port_id"] as? String
        self.moniker = dictionary?["moniker"] as? String
        if let rawTokenCounterParty = dictionary?["counter_party"] as? NSDictionary {
            self.counter_party = TokenCounterParty.init(rawTokenCounterParty)
        }
    }
}


public struct TokenCounterParty {
    var chain_id: String?
    var channel_id: String?
    var port_id: String?
    
    init(_ dictionary: NSDictionary?) {
        self.chain_id = dictionary?["chain_id"] as? String
        self.channel_id = dictionary?["channel_id"] as? String
        self.port_id = dictionary?["port_id"] as? String
    }
}
