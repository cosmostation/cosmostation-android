//
//  KavaTokenPriceParam.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/03/26.
//  Copyright © 2020 wannabit. All rights reserved.
//

import Foundation

class KavaPriceFeedParam {
    var height: String = ""
    var result: KavaPriceFeedParamResult = KavaPriceFeedParamResult.init()
    
    init() {}
    
    init(_ dictionary: [String: Any]) {
        self.height = dictionary["height"] as? String ?? ""
        self.result = KavaPriceFeedParamResult.init(dictionary["result"] as! [String : Any])
    }
    
    init(_ dictionary: NSDictionary) {
        self.height = dictionary["height"] as? String ?? ""
        self.result = KavaPriceFeedParamResult.init(dictionary["result"] as! [String : Any])
    }
    
    public class KavaPriceFeedParamResult {
        var markets: Array<KavaTokenPriceMarket> = Array<KavaTokenPriceMarket>()
        
        init() {}
        
        init(_ dictionary: [String: Any]) {
            if let rawMarkets = dictionary["markets"] as? Array<NSDictionary> {
                self.markets.removeAll()
                for rawMarket in rawMarkets {
                    self.markets.append(KavaTokenPriceMarket(rawMarket as! [String : Any]))
                }
            }
        }
    }
    
    public class KavaTokenPriceMarket {
        var market_id: String = ""
        var base_asset: String = ""
        var quote_asset: String = ""
        var active: Bool = false
        
        init() {}
        
        init(_ dictionary: [String: Any]) {
            if let market_id =  dictionary["market_id"] as? String {
                self.market_id = market_id
            }
            
            if let base_asset =  dictionary["base_asset"] as? String {
                self.base_asset = base_asset
            }
            
            if let quote_asset =  dictionary["quote_asset"] as? String {
                self.quote_asset = quote_asset
            }
            
            if let active =  dictionary["active"] as? Bool {
                self.active = active
            }
        }
    }
}
