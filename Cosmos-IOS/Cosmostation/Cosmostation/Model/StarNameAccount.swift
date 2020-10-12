//
//  StarNameAccount.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/10/12.
//  Copyright © 2020 wannabit. All rights reserved.
//

import Foundation

public struct StarNameAccount: Codable {
    var domain: String = ""
    var name: String = ""
    var owner: String = ""
    var valid_until: Int64 = -1
    var certificates: String = ""
    var broker: String = ""
    var metadata_uri: String = ""
    var resources: Array<StarNameResource> = Array<StarNameResource>()
    
    init() {}
    
    init(_ dictionary: [String: Any]) {
        self.domain = dictionary["domain"] as? String ?? ""
        self.name = dictionary["name"] as? String ?? ""
        self.owner = dictionary["owner"] as? String ?? ""
        self.valid_until = dictionary["valid_until"] as? Int64 ?? -1
        self.certificates = dictionary["certificates"] as? String ?? ""
        self.broker = dictionary["broker"] as? String ?? ""
        self.metadata_uri = dictionary["metadata_uri"] as? String ?? ""
        if let rawResources = dictionary["resources"] as? Array<NSDictionary> {
            self.resources.removeAll()
            for rawResource in rawResources {
                self.resources.append(StarNameResource(rawResource as! [String : Any]))
            }
        }
    }
}
