//
//  Coin.swift
//  Cosmostation
//
//  Created by yongjoo on 22/03/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import Foundation

public struct Coin: Codable {
    var denom: String = ""
    var amount: String = ""
    
    init(){}
    
    init(_ dictionary: [String: Any]) {
        self.denom = dictionary["denom"] as? String ?? ""
        self.amount = dictionary["amount"] as? String ?? ""
    }
    
    init(_ dictionary: NSDictionary?) {
        self.denom = dictionary?["denom"] as? String ?? ""
        self.amount = dictionary?["amount"] as? String ?? ""
    }
    
    init(_ denom:String, _ amount:String) {
        self.denom = denom
        self.amount = amount
    }
    
    func isIbc() -> Bool {
        if (denom.starts(with: "ibc/")) {
            return true
        }
        return false
    }
    
    func getIbcHash() -> String? {
        if (!isIbc()) {return nil}
        return denom.replacingOccurrences(of: "ibc/", with: "")
    }
    
    func isOsmosisAmm() -> Bool {
        if (denom.starts(with: "gamm/pool/")) {
            return true
        }
        return false
    }
    
    func isOsmosisAmmDpDenom() -> String {
        return "GAMM-" + String(denom.split(separator: "/").last!)
    }
}
