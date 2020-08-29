//
//  OkTokenList.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/08/25.
//  Copyright © 2020 wannabit. All rights reserved.
//

import Foundation

public class OkTokenList {
    var code: Int = -1
    var msg: String = ""
    var detail_msg: String = ""
    var data: Array<OkToken> = Array<OkToken>()
    
    init() {}
    
    init(_ dictionary: [String: Any]) {
        self.code = dictionary["code"] as? Int ?? -1
        self.msg = dictionary["msg"] as? String ?? ""
        self.detail_msg = dictionary["detail_msg"] as? String ?? ""
        self.data.removeAll()
        if let tokens = dictionary["data"] as? Array<NSDictionary> {
            for token in tokens {
                self.data.append(OkToken(token as! [String : Any]))
            }
        }
    }
    
}
