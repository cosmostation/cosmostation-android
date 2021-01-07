//
//  OkAccountInfo.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/01/07.
//  Copyright © 2021 wannabit. All rights reserved.
//

import Foundation

public struct OkAccountInfo {
    var type: String?
    var value: OkAccountValue?
    
    init(_ dictionary: NSDictionary?) {
        self.type = dictionary?["type"] as? String
        if let rawValue = dictionary?["value"] as? NSDictionary {
            self.value = OkAccountValue.init(rawValue)
        }
        
    }
    
    public struct OkAccountValue {
        var address: String?
        var eth_address: String?
        var account_number: String?
        var sequence: String?
        var code_hash: String?
        
        init(_ dictionary: NSDictionary?) {
            self.address = dictionary?["address"] as? String
            self.eth_address = dictionary?["eth_address"] as? String
            if let accountNumber = dictionary?["account_number"] as? String {
                self.account_number = accountNumber
            }
            if let accountNumber = dictionary?["account_number"] as? Int64 {
                self.account_number = String(accountNumber)
            }
            
            if let seQuence = dictionary?["sequence"] as? String {
                self.sequence = seQuence
            }
            if let seQuence = dictionary?["sequence"] as? Int64 {
                self.sequence = String(seQuence)
            }
            self.code_hash = dictionary?["code_hash"] as? String
        }
        
    }
}
