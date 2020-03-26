//
//  Cdp.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/03/26.
//  Copyright © 2020 wannabit. All rights reserved.
//

import Foundation

public struct Cdp: Codable {
    var id: String?
    var owner: String?
    var collateral: Array<Coin>?
    var principal: Array<Coin>?
    var accumulated_fees: Array<Coin>?
    var fees_updated: String?
    
    init() {}
    
    init(_ dictionary: [String: Any]) {
        if let id =  dictionary["id"] as? String {
            self.id = id
        }
        
        if let owner =  dictionary["owner"] as? String {
            self.owner = owner
        }
        
        if let rawCollaterals = dictionary["collateral"] as? Array<NSDictionary> {
            self.collateral =  Array<Coin>()
            for rawCollateral in rawCollaterals {
                self.collateral?.append(Coin(rawCollateral as! [String : Any]))
            }
        }
        
        if let rawPrincipals = dictionary["principal"] as? Array<NSDictionary> {
            self.principal =  Array<Coin>()
            for rawPrincipal in rawPrincipals {
                self.principal?.append(Coin(rawPrincipal as! [String : Any]))
            }
        }
        
        if let rawAccumulatedFees = dictionary["accumulated_fees"] as? Array<NSDictionary> {
            self.accumulated_fees =  Array<Coin>()
            for rawAccumulatedFee in rawAccumulatedFees {
                self.accumulated_fees?.append(Coin(rawAccumulatedFee as! [String : Any]))
            }
        }
        
        if let fees_updated =  dictionary["fees_updated"] as? String {
            self.fees_updated = fees_updated
        }
    }
}
