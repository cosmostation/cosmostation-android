//
//  OkHistory.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/01/11.
//  Copyright © 2021 wannabit. All rights reserved.
//

import Foundation

public struct OkHistory {
    var code: Int64?
    var msg: String?
    var detail_msg: String?
    var data: Data?
    
    init(_ dictionary: NSDictionary?) {
        self.code = dictionary?["code"] as? Int64
        self.msg = dictionary?["msg"] as? String
        self.detail_msg = dictionary?["detail_msg"] as? String
        if let rawData = dictionary?["data"] as? NSDictionary {
            self.data = Data.init(rawData)
        }
    }
    
    public struct Data {
        var dataDetails: Array<DataDetail>?
        var param_page: PageParam?
        
        init(_ dictionary: NSDictionary?) {
            if let rawDatas = dictionary?["data"] as? Array<NSDictionary>  {
                dataDetails = Array<DataDetail>()
                for rawData in rawDatas {
                    self.dataDetails?.append(DataDetail.init(rawData))
                }
            }
            if let rawParamPage = dictionary?["param_page"] as? NSDictionary {
                self.param_page = PageParam.init(rawParamPage)
            }
        }
        
    }
    
    public struct DataDetail {
        var txhash: String?
        var type: Int64?
        var address: String?
        var symbol: String?
        var side: Int64?
        var quantity: String?
        var fee: String?
        var timestamp: Int64?
        
        init(_ dictionary: NSDictionary?) {
            self.txhash = dictionary?["txhash"] as? String
            self.type = dictionary?["type"] as? Int64
            self.address = dictionary?["address"] as? String
            self.symbol = dictionary?["symbol"] as? String
            self.side = dictionary?["side"] as? Int64
            self.quantity = dictionary?["quantity"] as? String
            self.fee = dictionary?["fee"] as? String
            self.timestamp = dictionary?["timestamp"] as? Int64
        }
        
    }
    
    public struct PageParam {
        var page: Int64?
        var per_page: Int64?
        var total: Int64?
        
        init(_ dictionary: NSDictionary?) {
            self.page = dictionary?["page"] as? Int64
            self.per_page = dictionary?["per_page"] as? Int64
            self.total = dictionary?["total"] as? Int64
        }
        
    }
}
