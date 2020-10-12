//
//  IovStarNameDomain.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/10/12.
//  Copyright © 2020 wannabit. All rights reserved.
//

import Foundation

public struct IovStarNameDomain: Codable {
    var height: String = ""
    var result: IovDomainValue = IovDomainValue.init()
    
    init() {}
    
    init(_ dictionary: [String: Any]) {
        self.height = dictionary["height"] as? String ?? ""
        self.result = IovDomainValue.init(dictionary["result"] as! [String : Any])
    }
    
    public struct IovDomainValue: Codable {
        var Domains: Array<StarNameDomain> = Array<StarNameDomain>()
        
        init() {}
        
        init(_ dictionary: [String: Any]) {
            self.Domains.removeAll()
            if let RawDomains = dictionary["Domains"] as? Array<NSDictionary> {
                for RawDomain in RawDomains {
                    self.Domains.append(StarNameDomain(RawDomain as! [String : Any]))
                }
            }
        }
    }
    
}
