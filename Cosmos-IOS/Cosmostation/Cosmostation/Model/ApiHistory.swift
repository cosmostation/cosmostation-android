//
//  ApiHistory.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/03/11.
//  Copyright © 2020 wannabit. All rights reserved.
//

import Foundation

public class ApiHistory {
    var historyData: Array<HistoryData> = Array<HistoryData>()
    
    init() {}
    
    init(_ dictionary: [String: Any]) {
        let rawDatas = dictionary["data"] as! Array<NSDictionary>
        for rawData in rawDatas {
            self.historyData.append(HistoryData(rawData as! [String : Any]))
        }
    }
    
    
    public class HistoryData {
        var id: Int64 = -1
        var height: Int64 = -1
        var tx_hash: String = ""
        var memo: String = ""
        var time: String = ""
        var fee: Fee = Fee.init()
        var msg: Array<Msg> = Array<Msg>()
        
        var isSuccess: Bool = true
        
        init() {}
        
        init(_ dictionary: NSDictionary) {
            self.id = dictionary["id"] as? Int64 ?? -1
            self.height = dictionary["height"] as? Int64 ?? -1
            self.tx_hash = dictionary["tx_hash"] as? String ?? ""
            self.memo = dictionary["memo"] as? String ?? ""
            self.time = dictionary["time"] as? String ?? ""
            
            if let feedata = dictionary["fee"] as? [String : Any] {
                self.fee = Fee.init(feedata)
            }
            
            if let rawMsgs = dictionary["messages"] as? Array<NSDictionary> {
                for rawMsg in rawMsgs {
                    self.msg.append(Msg(rawMsg as! [String : Any]))
                }
            }
            
            if let rawMsgs = dictionary["msg"] as? Array<NSDictionary> {
                for rawMsg in rawMsgs {
                    self.msg.append(Msg(rawMsg as! [String : Any]))
                }
            }
            
            
//            if let logs = dictionary["logs"] as? NSDictionary {
//                if let check = logs.object(forKey: "log") as? String {
//                    if (!check.isEmpty) {
//                        self.isSuccess = false
//                        return;
//                    }
//                }
//            }
//
//            if let logs = dictionary["logs"] as? Array<NSDictionary> {
//                for log in logs {
//                    if let check = log.object(forKey: "log") as? String {
//                        if (!check.isEmpty) {
//                            self.isSuccess = false
//                            return;
//                        }
//                    }
//                }
//            }
            if let logs = dictionary["logs"] as? NSDictionary {
                self.isSuccess = true
            } else {
                self.isSuccess = false
            }
            
            if let logs = dictionary["logs"] as? Array<NSDictionary> {
                self.isSuccess = true
            } else {
                self.isSuccess = false
            }
        }
        
        init(_ dictionary: [String: Any]) {
            self.id = dictionary["id"] as? Int64 ?? -1
            self.height = dictionary["height"] as? Int64 ?? -1
            self.tx_hash = dictionary["tx_hash"] as? String ?? ""
            self.memo = dictionary["memo"] as? String ?? ""
            self.time = dictionary["time"] as? String ?? ""
            
            if let feedata = dictionary["fee"] as? [String : Any] {
                self.fee = Fee.init(feedata)
            }
            
            let rawMsgs = dictionary["messages"] as! Array<NSDictionary>
            for rawMsg in rawMsgs {
                self.msg.append(Msg(rawMsg as! [String : Any]))
            }
            
            
            if let logs = dictionary["logs"] as? NSDictionary {
                if let check = logs.object(forKey: "log") as? String {
                    if (!check.isEmpty) {
                        self.isSuccess = false
                        return;
                    }
                }
            }
            
            if let logs = dictionary["logs"] as? Array<NSDictionary> {
                for log in logs {
                    if let check = log.object(forKey: "log") as? String {
                        if (!check.isEmpty) {
                            self.isSuccess = false
                            return;
                        }
                    }
                }
            }
        }
    }
}
