//
//  KavaPrice.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/03/26.
//  Copyright © 2020 wannabit. All rights reserved.
//

import Foundation

class KavaPriceFeedPrice {
    var height: String = ""
    var result: KavaMarketPrice = KavaMarketPrice.init()
    
    init() {}
    
    init(_ dictionary: NSDictionary) {
        self.height = dictionary["height"] as? String ?? ""
        self.result = KavaMarketPrice.init(dictionary["result"] as! [String : Any])
    }
    
    public class KavaMarketPrice {
        var market_id: String = ""
        var price: String = ""
        
        init() {}
        
        init(_ dictionary: [String: Any]) {
            if let market_id =  dictionary["market_id"] as? String {
                self.market_id = market_id
            }
            
            if let price =  dictionary["price"] as? String {
                self.price = price
            }
        }
    }
}
