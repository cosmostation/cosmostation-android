//
//  BandOracleStatus.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/10/20.
//  Copyright © 2020 wannabit. All rights reserved.
//

import Foundation

public struct BandOracleStatus: Codable {
    var height: String = ""
    var result: Array<BandOracle> = Array<BandOracle>()
    
    init() {}
    
    init(_ dictionary: [String: Any]) {
        self.height = dictionary["height"] as? String ?? ""
        if let rawBandOracles = dictionary["result"] as? Array<NSDictionary> {
            self.result.removeAll()
            for rawBandOracle in rawBandOracles {
                self.result.append(BandOracle(rawBandOracle as! [String : Any]))
            }
        }
    }
    
    public func isEnable(_ valOpAddress: String) -> Bool{
        for oracle in result {
            if (oracle.address == valOpAddress) {
                return true
            }
        }
        return false
    }
    
    public struct BandOracle: Codable {
        var address: String = ""
        var power: String = ""
        
        init() {}
        
        init(_ dictionary: [String: Any]) {
            self.address = dictionary["address"] as? String ?? ""
            self.power = dictionary["power"] as? String ?? ""
        }
    }
}
