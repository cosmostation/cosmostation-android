//
//  PostTx.swift
//  Cosmostation
//
//  Created by yongjoo on 11/04/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import Foundation

public struct PostTx: Codable {
    var returns: String = ""
    var tx: StdTx.Value?
    
    init() {}
    
    init(_ returns:String, _ tx:StdTx.Value) {
        self.returns = returns
        self.tx = tx
    }
    enum CodingKeys: String, CodingKey {
        case returns = "return"
        case tx = "tx"
    }
}
