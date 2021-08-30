//
//  KavaMyCdps.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/03/04.
//  Copyright © 2021 wannabit. All rights reserved.
//

import Foundation

public struct KavaMyCdps {
    var height: String?
    var result: Array<MyCdp> = Array<MyCdp>()
    
    init(_ dictionary: NSDictionary?) {
        self.height = dictionary?["height"] as? String
        if let rawMyCdps = dictionary?["result"] as? Array<NSDictionary> {
            for rawMyCdp in rawMyCdps {
                self.result.append(MyCdp.init(rawMyCdp))
            }
        }
    }
    
}

