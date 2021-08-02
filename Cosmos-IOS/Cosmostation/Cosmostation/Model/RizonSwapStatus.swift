//
//  RizonSwapStatus.swift
//  Cosmostation
//
//  Created by 정용주 on 2021/07/30.
//  Copyright © 2021 wannabit. All rights reserved.
//

import Foundation

public struct RizonSwapStatus {
    var status: String?
    var _id: String?
    var from: String?
    var to: String?
    var amount: Double?
    var hdacTxId: String?
    var rizonTxId: String?
    var __v: UInt16?
    var hdacTx: HdacTx?
    
    init(_ dictionary: NSDictionary?) {
        self.status = dictionary?["status"] as? String
        self._id = dictionary?["_id"] as? String
        self.from = dictionary?["from"] as? String
        self.to = dictionary?["to"] as? String
        self.amount = dictionary?["amount"] as? Double
        self.hdacTxId = dictionary?["hdacTxId"] as? String
        self.rizonTxId = dictionary?["rizonTxId"] as? String
        self.__v = dictionary?["__v"] as? UInt16
        if let rawHdacTx = dictionary?["hdacTx"] as? NSDictionary {
            self.hdacTx = HdacTx.init(rawHdacTx)
        }
    }
}

public struct HdacTx {
    var txid: String?
    var confirmations: UInt64?
    var time: UInt64?
    var data: Array<String>?
    
    init(_ dictionary: NSDictionary?) {
        self.txid = dictionary?["txid"] as? String
        self.confirmations = dictionary?["confirmations"] as? UInt64
        self.time = dictionary?["time"] as? UInt64
        if let rawData = dictionary?["data"] as? Array<String> {
            self.data = rawData
        }
    }
    
}
