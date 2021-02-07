//
//  OkTickerList.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/02/07.
//  Copyright © 2021 wannabit. All rights reserved.
//

import Foundation

public struct OkTickerList {
    var code: Int?
    var msg: String?
    var detail_msg: String?
    var data: Array<OkTicker>?
    
    init(_ dictionary: NSDictionary?) {
        self.code = dictionary?["code"] as? Int
        self.msg = dictionary?["msg"] as? String
        self.detail_msg = dictionary?["detail_msg"] as? String
        if let rawDatas = dictionary?["data"] as? Array<NSDictionary> {
            data = Array<OkTicker>()
            for rawData in rawDatas {
                self.data!.append(OkTicker(rawData))
            }
        }
    }
    
}
