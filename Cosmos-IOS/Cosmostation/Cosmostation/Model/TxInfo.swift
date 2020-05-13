//
//  TxInfo.swift
//  Cosmostation
//
//  Created by yongjoo on 12/04/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import Foundation


public struct TxInfo {
    var height: String = ""
    var txhash: String = ""
    var hash: String = ""
    var txTime: String = ""
    var tx: StdTx = StdTx.init()
    
    var rawLog: String = ""
    var events: Array<Event> = Array<Event>()
    var gas_wanted: String = ""
    var gas_used: String = ""
//    var logs: Data?
    var isSuccess: Bool = true
    var failMsg: String = ""
    
    
    //for binance chain
    var ok: Bool = false
    var log: String = ""
    
    
    init() {}
    
    init(_ dictionary: [String: Any]) {
        self.height = dictionary["height"] as? String ?? ""
        self.txhash = dictionary["txhash"] as? String ?? ""
        self.hash = dictionary["hash"] as? String ?? ""
        self.txTime = dictionary["timestamp"] as? String ?? ""
        self.tx = StdTx.init(dictionary["tx"] as! [String : Any])
        
        
        self.rawLog = dictionary["raw_log"] as? String ?? ""
        self.events.removeAll()
        if let rawEvents = dictionary["events"] as? Array<NSDictionary> {
            for rawEvent in rawEvents {
                self.events.append(Event(rawEvent as! [String : Any]))
            }
        }
        self.gas_wanted = dictionary["gas_wanted"] as? String ?? ""
        self.gas_used = dictionary["gas_used"] as? String ?? ""
        
        
        //COSMOS HUB-3 case
        if let logs = dictionary["logs"] as? Array<NSDictionary> {
            for log in logs {
                if let success = log.object(forKey: "success") as? Bool {
                    if(!success) {
                        self.isSuccess = false
                        if let failmsg = log.object(forKey: "log") as? String {
                            self.failMsg = failmsg
                        }
                        return;
                    }
                }
            }
        }
        if let code = dictionary["code"] as? Int {
            if (code != 0) {
                self.isSuccess = false
                if let failmsg = dictionary["raw_log"] as? String {
                    self.failMsg = failmsg
                }
                return
            }
        }
        
        
        self.ok = dictionary["ok"] as? Bool ?? false
        self.log = dictionary["log"] as? String ?? ""
    }
    
    
//    public struct Log {
//        var success: Bool = false
//        var log: String = ""
//
//        init() {}
//
//        init(_ dictionary: [String: Any]) {
//            self.success = dictionary["success"] as? Bool ?? false
//            self.log = dictionary["log"] as? String ?? ""
//        }
//    }
    
    public struct Event {
        var type: String = ""
        var attributes: Array<EventAttribute> = Array<EventAttribute>()
        
        init() {}
        
        init(_ dictionary: [String: Any]) {
            self.type = dictionary["type"] as? String ?? ""
            self.attributes.removeAll()
            let rawAttrs = dictionary["attributes"] as! Array<NSDictionary>
            for rawAttr in rawAttrs {
                self.attributes.append(EventAttribute(rawAttr as! [String : Any]))
            }
        }
    }
    
    public struct EventAttribute {
        var key: String = ""
        var value: String = ""
        
        init() {}
        
        init(_ dictionary: [String: Any]) {
            self.key = dictionary["key"] as? String ?? ""
            self.value = dictionary["value"] as? String ?? ""
        }
    }
    
    public func getMsgs() -> Array<Msg> {
        return tx.value.msg
    }
    
    
    public func getSimpleAutoReward() -> String {
        var result = "0"
        for event in self.events {
            if (event.type == "transfer") {
                for attr in event.attributes {
                    if (attr.key == "amount") {
                        result = attr.value.replacingOccurrences(of: "uatom", with: "").replacingOccurrences(of: "ukava", with: "")
                        break
                    }
                }
            }
        }
        return result
    }
    
    public func getSimpleReward(_ opAddr: String) -> String {
        var result = "0"
        for event in self.events {
            if (event.type == "withdraw_rewards") {
                for i in 0...event.attributes.count {
                    if (event.attributes[i].key == "validator" && event.attributes[i].value == opAddr) {
                        result = event.attributes[i - 1].value.replacingOccurrences(of: "uatom", with: "").replacingOccurrences(of: "ukava", with: "")
                        break
                    }
                }
            }
        }
        return result
    }
    
    public func getSimpleCommission() -> String {
        var result = "0"
        for event in self.events {
            if (event.type == "withdraw_commission") {
                for attr in event.attributes {
                    if (attr.value == "amount") {
                        result = attr.value.replacingOccurrences(of: "uatom", with: "").replacingOccurrences(of: "ukava", with: "")
                    }
                }
            }
        }
        return result
    }
    
    public func getSimpleFee() -> String {
        var result = "0"
        if (tx.value.fee.amount != nil && tx.value.fee.amount.count > 0) {
            result = tx.value.fee.amount[0].amount
        }
        return result
    }
    
    
    public func getSimpleSwapCoin() -> Coin {
        var coin = Coin.init()
        for event in self.events {
            if (event.type == "transfer") {
                for attr in event.attributes {
                    if (attr.key == "amount") {
                        let value = attr.value;
                        coin.denom = value.filter{ $0.isLetter }
                        coin.amount = value.filter{ $0.isNumber }
                    }
                }
            }
        }
        return coin
    }
    
    public func getSimpleBnbSwapId() -> String {
        return log.split(separator: ":").last?.trimmingCharacters(in: CharacterSet.whitespacesAndNewlines) ?? ""
    }
    
    public func getSimpleKavaSwapId() -> String {
        var result = ""
        for event in self.events {
            if (event.type == "createAtomicSwap") {
                for attr in event.attributes {
                    if (attr.key == "atomic_swap_id") {
                        result = attr.value;
                    }
                }
            }
        }
        return result
    }
    
}
