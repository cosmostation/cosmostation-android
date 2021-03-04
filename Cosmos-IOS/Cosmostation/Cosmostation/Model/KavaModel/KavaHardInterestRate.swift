//
//  KavaHardInterestRate.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/03/04.
//  Copyright © 2021 wannabit. All rights reserved.
//

import Foundation

public struct KavaHardInterestRate {
    var height: String?
    var result: Array<HardInterestRate>?
    
    init(_ dictionary: NSDictionary?) {
        self.height = dictionary?["height"] as? String
        if let rawHardInterestRates = dictionary?["result"] as? Array<NSDictionary> {
            self.result = Array<HardInterestRate>()
            for rawHardInterestRate in rawHardInterestRates {
                self.result?.append(HardInterestRate.init(rawHardInterestRate))
            }
        }
    }
}
