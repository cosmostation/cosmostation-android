//
//  StarNameResource.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/10/12.
//  Copyright © 2020 wannabit. All rights reserved.
//

import Foundation

public struct StarNameResource: Codable {
    var uri: String = ""
    var resource: String = ""
    
    init() {}
    
    init (_ uri: String) {
        self.uri = uri
    }
    
    init (_ uri: String, _ resource: String) {
        self.uri = uri
        self.resource = resource
    }
    
    init(_ dictionary: [String: Any]) {
        self.uri = dictionary["uri"] as? String ?? ""
        self.resource = dictionary["resource"] as? String ?? ""
    }
}
