//
//  KavaIncentiveReward.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/03/04.
//  Copyright © 2021 wannabit. All rights reserved.
//

import Foundation

public struct KavaIncentiveReward {
    var height: String?
    var result: IncentiveReward?
    
    init(_ dictionary: NSDictionary?) {
        self.height = dictionary?["height"] as? String
        if let rawIncentiveReward = dictionary?["result"] as? NSDictionary {
            self.result = IncentiveReward.init(rawIncentiveReward)
        }
    }
}
