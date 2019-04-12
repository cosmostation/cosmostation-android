//
//  Amino.swift
//  Cosmostation
//
//  Created by yongjoo on 09/04/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import Foundation

public struct Amino : Codable {
    var name: String?
    var id: Int?
    var innder: Inner?
    var data: String?
    
    func toDic() -> [String:Any] {
        return ["name":self.name, "id":self.id]
    }
    
    func toDic2() -> [String:Any] {
//        return ["name":self.name, "id":self.id, "innder":["innerName":self.innder?.innerName], "data":NSNull()]
        var result = ["name":self.name, "id":self.id, "innder":["innerName":self.innder?.innerName]] as [String : Any]
        result["data"] = NSNull()
        return result
    }
    
    init() {}
    
    
    public struct Inner : Codable {
        var innerName: String?
        
        init() {}
    }
    
    public func encode(to encoder: Encoder) throws {
        var container = encoder.container(keyedBy: CodingKeys.self)
        try container.encode(name, forKey: .name)
        try container.encode(id, forKey: .id)
        try container.encode(innder, forKey: .innder)
        try container.encode(data, forKey: .data)
    }
    
//    public class Inner {
//        var innerName: String?
//    }
}
