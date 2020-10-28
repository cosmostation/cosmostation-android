//
//  IovStarNameDomainInfo.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/10/28.
//  Copyright © 2020 wannabit. All rights reserved.
//

import Foundation

public struct IovStarNameDomainInfo: Codable {
    var height: String = ""
    var result: IovDomainInfoValue = IovDomainInfoValue.init()
    
    init() {}
    
    init(_ dictionary: [String: Any]) {
        self.height = dictionary["height"] as? String ?? ""
        self.result = IovDomainInfoValue.init(dictionary["result"] as! [String : Any])
    }
    
    public struct IovDomainInfoValue: Codable {
        var domain: StarNameDomain?
        
        init() {}
        
        init(_ dictionary: [String: Any]) {
            self.domain = StarNameDomain.init(dictionary["domain"] as! [String : Any])
        }
    }
    
}
