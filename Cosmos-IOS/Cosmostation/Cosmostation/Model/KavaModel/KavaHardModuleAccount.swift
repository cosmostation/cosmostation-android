//
//  KavaHardModuleAccount.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/03/10.
//  Copyright © 2021 wannabit. All rights reserved.
//

import Foundation

public struct KavaHardModuleAccount {
    var height: String?
    var result: Array<HardModuleAccount>?
    
    init(_ dictionary: NSDictionary?) {
        self.height = dictionary?["height"] as? String
        if let rawResults = dictionary?["result"] as? Array<NSDictionary> {
            self.result = Array<HardModuleAccount>()
            for rawResult in rawResults {
                self.result?.append(HardModuleAccount.init(rawResult))
            }
        }
    }
}
