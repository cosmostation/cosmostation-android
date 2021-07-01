//
//  BiananceModel.swift
//  Cosmostation
//
//  Created by yongjoo jung on 2021/06/30.
//  Copyright © 2021 wannabit. All rights reserved.
//

import Foundation
import SwiftyJSON


public class Transactions: CustomStringConvertible {
    public var description: String = ""
    
    public var total: Int = 0
    public var tx: [Tx] = []
}

public class Transaction: CustomStringConvertible {
    public var description: String = ""
    
    public var hash: String = ""
    public var log: String = ""
    public var data: String = ""
    public var ok: Bool = false
    public var tx: Tx = Tx()
}


public class Tx: CustomStringConvertible {
    public var description: String = ""
    
    public var blockHeight: Double = 0
    public var code: Int = 0
    public var confirmBlocks: Double = 0
    public var data: String = ""
    public var fromAddr: String = ""
    public var orderId: String = ""
    public var timestamp: Date = Date()
    public var toAddr: String = ""
    public var txAge: Double = 0
    public var txAsset: String = ""
    public var txFee: String = ""
    public var txHash: String = ""
    public var txType: TxType = .newOrder
    public var value: String = ""
}
