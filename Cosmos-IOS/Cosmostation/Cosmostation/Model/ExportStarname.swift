//
//  ExportStarname.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/11/12.
//  Copyright © 2020 wannabit. All rights reserved.
//

import Foundation

public struct ExportStarname: Codable {
    var type: String = ""
    var addresses: Array<ExportResource> = Array<ExportResource>()
    
    init() {}
    
    init(_ type: String, _ addresses: Array<ExportResource>) {
        self.type = type
        self.addresses = addresses
    }
    
    public struct ExportResource: Codable {
        var ticker: String = ""
        var address: String = ""
    
        init() {}
        
        init(_ ticker: String, _ address: String) {
            self.ticker = ticker
            self.address = address
        }
    }
    
}
