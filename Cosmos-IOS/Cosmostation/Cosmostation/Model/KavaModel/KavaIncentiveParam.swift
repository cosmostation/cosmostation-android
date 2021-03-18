//
//  KavaIncentiveParam.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/03/04.
//  Copyright © 2021 wannabit. All rights reserved.
//

import Foundation

public struct KavaIncentiveParam {
    var height: String?
    var result: IncentiveParam?
    
    init(_ dictionary: NSDictionary?) {
        self.height = dictionary?["height"] as? String
        if let rawIncentiveParam = dictionary?["result"] as? NSDictionary {
            self.result = IncentiveParam.init(rawIncentiveParam)
        }
    }
}
