//
//  HistoryStdTx.swift
//  Cosmostation
//
//  Created by yongjoo on 09/05/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import Foundation

public class HistoryStdTx: Codable {
    var type: String = ""
    var value: Value = Value.init()
    
    init() {}
    
    init(_ dictionary: [String: Any]) {
        self.type = dictionary["type"] as? String ?? ""
        self.value = Value.init(dictionary["value"] as! [String : Any])
    }
    
    public class Value: Codable {
        var msg: Array<HistoryMsg> = Array<HistoryMsg>()
        var fee: Fee = Fee.init()
        var signatures: Array<Signature> = Array<Signature>()
        var memo: String = ""
        
        init() {}
        
        init(_ dictionary: [String: Any]) {
            self.msg.removeAll()
            let rawMsgs = dictionary["msg"] as! Array<NSDictionary>
            for rawMsg in rawMsgs {
                self.msg.append(HistoryMsg(rawMsg as! [String : Any]))
            }
            
            self.fee = Fee.init(dictionary["fee"] as! [String : Any])
            
            self.signatures.removeAll()
            let rawSignatures = dictionary["signatures"] as! Array<NSDictionary>
            for rawSignature in rawSignatures {
                self.signatures.append(Signature(rawSignature as! [String : Any]))
            }
            
            self.memo = dictionary["memo"] as? String ?? ""
        }
    }
}
