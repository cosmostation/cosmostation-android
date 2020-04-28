//
//  IovNonce.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/04/28.
//  Copyright © 2020 wannabit. All rights reserved.
//

import Foundation

public class IovNonce {
    
    var key: String = ""
    var model: IovModel?
    
    init() {}
    
    init(_ dictionary: [String: Any]) {
        self.key = dictionary["key"] as? String ?? ""
        self.model = IovModel.init(dictionary["model"] as! [String : Any])
    }
    
    public class IovModel {
        var sequence: Int64 = 0
        
        init() {}
        
        init(_ dictionary: [String: Any]) {
            self.sequence = dictionary["sequence"] as? Int64 ?? 0
        }
    }
}
