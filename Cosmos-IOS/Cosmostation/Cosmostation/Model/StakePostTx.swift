//
//  StakePostTx.swift
//  Cosmostation
//
//  Created by yongjoo on 22/04/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import Foundation

public struct StakePostTx: Codable {
    var returns: String = ""
    var tx: StakeStdTx.Value?
    
    init() {}
    
    init(_ returns:String, _ tx:StakeStdTx.Value) {
        self.returns = returns
        self.tx = tx
    }
    enum CodingKeys: String, CodingKey {
        case returns = "mode"
        case tx = "tx"
    }
}
