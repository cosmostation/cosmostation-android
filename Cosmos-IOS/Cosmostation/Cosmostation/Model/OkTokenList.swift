//
//  OkTokenList.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/08/25.
//  Copyright © 2020 wannabit. All rights reserved.
//

import Foundation

public struct OkTokenList {
    var code: Int?
    var msg: String?
    var detail_msg: String?
    var data: Array<OkToken>?
    
    init(_ dictionary: NSDictionary?) {
        self.code = dictionary?["code"] as? Int
        self.msg = dictionary?["msg"] as? String
        self.detail_msg = dictionary?["detail_msg"] as? String
        if let rawDatas = dictionary?["data"] as? Array<NSDictionary> {
            data = Array<OkToken>()
            for rawData in rawDatas {
                self.data!.append(OkToken(rawData))
            }
        }
    }
    
}
