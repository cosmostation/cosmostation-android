//
//  KavaHardMyBorrow.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/03/04.
//  Copyright © 2021 wannabit. All rights reserved.
//

import Foundation

public struct KavaHardMyBorrow {
    var height: String?
    var result: Array<HardMyBorrow>?
    
    init(_ dictionary: NSDictionary?) {
        self.height = dictionary?["height"] as? String
        if let rawResults = dictionary?["result"] as? Array<NSDictionary> {
            self.result = Array<HardMyBorrow>()
            for rawResult in rawResults {
                self.result?.append(HardMyBorrow.init(rawResult))
            }
        }
    }
}
