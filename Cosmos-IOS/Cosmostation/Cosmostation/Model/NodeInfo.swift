//
//  NodeInfo.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/03/20.
//  Copyright © 2021 wannabit. All rights reserved.
//

import Foundation

public struct NodeInfo {
    var network: String?
    var version: String?
    var channels: String?
    var moniker: String?
    
    init(_ dictionary: NSDictionary?) {
        self.network = dictionary?["network"] as? String
        self.version = dictionary?["version"] as? String
        self.channels = dictionary?["channels"] as? String
        self.moniker = dictionary?["moniker"] as? String
    }
    
}
