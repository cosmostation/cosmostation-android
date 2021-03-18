//
//  KavaCdpDeposits.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/03/09.
//  Copyright © 2021 wannabit. All rights reserved.
//

import Foundation

public struct KavaCdpDeposits {
    var height: String?
    var result: Array<CdpDeposit>?
    
    init(_ dictionary: NSDictionary?) {
        self.height = dictionary?["height"] as? String
        if let rawResults = dictionary?["result"] as? Array<NSDictionary> {
            self.result = Array<CdpDeposit>()
            for rawResult in rawResults {
                self.result?.append(CdpDeposit.init(rawResult))
            }
        }
    }
}
