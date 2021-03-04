//
//  KavaHardMyDeposit.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/03/04.
//  Copyright © 2021 wannabit. All rights reserved.
//

import Foundation

public struct KavaHardMyDeposit {
    var height: String?
    var result: Array<HardMyDeposit>?
    
    init(_ dictionary: NSDictionary?) {
        self.height = dictionary?["height"] as? String
        if let rawResults = dictionary?["result"] as? Array<NSDictionary> {
            self.result = Array<HardMyDeposit>()
            for rawResult in rawResults {
                self.result?.append(HardMyDeposit.init(rawResult))
            }
        }
    }
}
