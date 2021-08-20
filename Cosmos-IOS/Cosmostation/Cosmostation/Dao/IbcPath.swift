//
//  IbcPath.swift
//  Cosmostation
//
//  Created by yongjoo jung on 2021/07/04.
//  Copyright © 2021 wannabit. All rights reserved.
//

import Foundation

public struct IbcPath {
    var chain_id: String?
    var paths: Array<Path> = Array<Path>()
    
    init(_ dictionary: NSDictionary?) {
        self.chain_id = dictionary?["chain_id"] as? String
        if let rawPaths = dictionary?["paths"] as? Array<NSDictionary> {
            for rawPath in rawPaths {
                self.paths.append(Path.init(rawPath))
            }
        }
    }
}

public struct Path {
    var auth: Bool?
    var channel_id: String?
    var port_id: String?
    var counter_party: CounterParty?
    
    init(_ dictionary: NSDictionary?) {
        self.auth = dictionary?["auth"] as? Bool
        self.channel_id = dictionary?["channel_id"] as? String
        self.port_id = dictionary?["port_id"] as? String
        if let rawCounterParty = dictionary?["counter_party"] as? NSDictionary {
            self.counter_party = CounterParty.init(rawCounterParty)
        }
    }
}

public struct CounterParty {
    var channel_id: String?
    var port_id: String?
    
    init(_ dictionary: NSDictionary?) {
        self.channel_id = dictionary?["channel_id"] as? String
        self.port_id = dictionary?["port_id"] as? String
    }
}
