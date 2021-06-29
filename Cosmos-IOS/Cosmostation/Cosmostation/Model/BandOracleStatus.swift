//
//  BandOracleStatus.swift
//  Cosmostation
//
//  Created by yongjoo jung on 2021/06/29.
//  Copyright © 2021 wannabit. All rights reserved.
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
    
    public func isEnable(_ valOpAddress: String?) -> Bool{
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
