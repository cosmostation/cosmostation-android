//
//  TxInfo.swift
//  Cosmostation
//
//  Created by yongjoo on 12/04/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import Foundation

public struct TxInfo {
    var height: String?
    var txhash: String?
    var code: Int?
    var logs: Array<Log>?
    var gas_wanted: String?
    var gas_used: String?
    var tx: StdTx?
    var timestamp: String?
    var raw_log: String?
    
    //for binance chain
    var ok: Bool?
    var log: String?
    
    //for iris chain
    var hash: String?
    var result: Result?
    
    init() {}
    
    init(_ dictionary: [String: Any]) {
        if let rawHeight = dictionary["height"] as? String {
            self.height = rawHeight
        }
        if let rawTxhash = dictionary["txhash"] as? String {
            self.txhash = rawTxhash
        }
        if let rawCode = dictionary["code"] as? Int {
            self.code = rawCode
        }
        if let rawLogs = dictionary["logs"] as? Array<NSDictionary> {
            if (self.logs == nil) { self.logs = Array<Log>() }
            for rawLog in rawLogs {
                self.logs?.append(Log(rawLog as! [String : Any]))
            }
        }
        if let rawGasWanted = dictionary["gas_wanted"] as? String {
            self.gas_wanted = rawGasWanted
        }
        if let rawGasUsed = dictionary["gas_used"] as? String {
            self.gas_used = rawGasUsed
        }
        if let rawTx = dictionary["tx"] as? [String : Any] {
            self.tx = StdTx.init(rawTx)
        }
        if let rawTimestamp = dictionary["timestamp"] as? String {
            self.timestamp = rawTimestamp
        }
        if let raw_Log = dictionary["raw_log"] as? String {
            self.raw_log = raw_Log
        }
        
        
        if let rawBNBOk = dictionary["ok"] as? Bool {
            self.ok = rawBNBOk
        }
        if let rawBNBLog = dictionary["log"] as? String {
            self.log = rawBNBLog
        }
        
        
        if let rawHash = dictionary["hash"] as? String {
            self.hash = rawHash
        }
        if let rawResult = dictionary["result"] as? [String : Any] {
            self.result = Result.init(rawResult)
        }
    }
    
    public func isSuccess() -> Bool {
        if (code ?? 0 > 0) {
            return false
        } else {
            return true
        }
    }
    
    public func failMsg() -> String {
        if let msg = raw_log, !msg.isEmpty {
            return msg
        }
        return ""
    }
    
    public func simpleFee() -> NSDecimalNumber {
        var fee = NSDecimalNumber.zero
        if let feeAmount = tx?.value.fee.amount[0].amount {
            fee = NSDecimalNumber.init(string: feeAmount)
        }
        return fee
    }
    
    
    public func getMsgs() -> Array<Msg> {
        if let msgs = tx?.value.msg {
            return msgs
        }
        return Array<Msg>()
    }
    
    public func getMsg(_ position:Int) -> Msg? {
        return tx?.value.msg[position]
    }
    
    public func simpleReward(_ opAddr: String, _ position:Int) -> NSDecimalNumber {
        var reward = NSDecimalNumber.zero
        self.logs?[position].events?.forEach({ (event) in
            if (event.type == "withdraw_rewards") {
                if let attributes = event.attributes {
                    for i in 0...attributes.count - 1 {
                        if (attributes[i].key == "validator" && attributes[i].value == opAddr) {
                            if let value = attributes[i - 1].value {
                                reward = NSDecimalNumber.init(string: value.filter{ $0.isNumber })
                            }
                        }
                    }
                }
            }
        })
        return reward
    }
    
    public func simpleAutoReward(_ Addr: String, _ position:Int) -> NSDecimalNumber {
        var aReward = NSDecimalNumber.zero
        self.logs?[position].events?.forEach({ (event) in
            if (event.type == "transfer") {
                if let attributes = event.attributes {
                    for i in 0...attributes.count - 1 {
                        if (attributes[i].key == "recipient" && attributes[i].value == Addr) {
                            if let value = attributes[i + 1].value {
                                aReward = NSDecimalNumber.init(string: value.filter{ $0.isNumber })
                            }
                        }
                    }
                }
            }
        })
        return aReward
    }
    
    public func simpleCommission(_ position:Int) -> NSDecimalNumber {
        var commission = NSDecimalNumber.zero
        self.logs?[position].events?.forEach({ (event) in
            if (event.type == "withdraw_commission") {
                if let attributes = event.attributes {
                    for i in 0...attributes.count - 1 {
                        if (attributes[i].key == "amount") {
                            if let value = attributes[i].value {
                                commission = NSDecimalNumber.init(string: value.filter{ $0.isNumber })
                            }
                        }
                    }
                }
            }
        })
        return commission
    }
    
    public func simpleSwapCoin() -> Coin? {
        var coin = Coin.init()
        self.logs?[0].events?.forEach({ (event) in
            if (event.type == "transfer") {
                if let attributes = event.attributes {
                    for i in 0...attributes.count - 1 {
                        if (attributes[i].key == "amount") {
                            if let value = attributes[i].value {
                                coin.denom = value.filter{ $0.isLetter }
                                coin.amount = value.filter{ $0.isNumber }
                            }
                        }
                    }
                }
            }
        })
        return coin
    }
    
    public func simpleBnbSwapId() -> String {
        if let log = log {
            return log.split(separator: ":").last?.trimmingCharacters(in: CharacterSet.whitespacesAndNewlines) ?? ""
        }
        return ""
    }
    
    public func simpleKavaSwapId() -> String? {
        var swapId :String?
        self.logs?[0].events?.forEach({ (event) in
            if (event.type == "create_atomic_swap") {
                event.attributes?.forEach({ (eventAttribute) in
                    if (eventAttribute.key == "atomic_swap_id") {
                        swapId = eventAttribute.value
                    }
                })
            }
        })
        return swapId
    }
    
    public func simpleIncentive() -> Coin? {
        var coin = Coin.init()
        var rewardSum = NSDecimalNumber.zero
        self.logs?[0].events?.forEach({ (event) in
            if (event.type == "claim_reward") {
                event.attributes?.forEach({ (eventAttribute) in
                    if (eventAttribute.key == "claim_amount") {
                        if let value = eventAttribute.value {
                            coin.denom = value.filter{ $0.isLetter }
                            rewardSum = rewardSum.adding(NSDecimalNumber.init(string: value.filter{ $0.isNumber }))
                        }
                    }
                })
            }
        })
        coin.amount = rewardSum.stringValue
        return coin
    }
    
    public func simpleRefund() -> Coin? {
        var coin = Coin.init()
        self.logs?[0].events?.forEach({ (event) in
            if (event.type == "transfer") {
                event.attributes?.forEach({ (eventAttribute) in
                    if (eventAttribute.key == "amount") {
                        if let value = eventAttribute.value {
                            coin.denom = value.filter{ $0.isLetter }
                            coin.amount = value.filter{ $0.isNumber }
                        }
                    }
                })
            }
        })
        return coin
    }
    
    
    
    public struct Log {
        var msg_index: Int?
        var success: Bool?
        var log: String?
        var events: Array<Event>?
        
        init() {}
        
        init(_ dictionary: [String: Any]) {
            if let rawMsgIndex = dictionary["msg_index"] as? Int {
                self.msg_index = rawMsgIndex
            }
            if let rawSuccess = dictionary["success"] as? Bool {
                self.success = rawSuccess
            }
            if let rawLog = dictionary["log"] as? String {
                self.log = rawLog
            }
            if let rawEvents = dictionary["events"] as? Array<NSDictionary> {
                for rawEvent in rawEvents {
                    if (self.events == nil) { self.events = Array<Event>() }
                    self.events?.append(Event(rawEvent as! [String : Any]))
                }
            }
        }
    }
    
    public struct Event {
        var type: String?
        var attributes: Array<EventAttribute>?
        
        init() {}
        
        init(_ dictionary: [String: Any]) {
            if let rawType = dictionary["type"] as? String {
                self.type = rawType
            }
            if let rawAttrs = dictionary["attributes"] as? Array<NSDictionary> {
                for rawAttr in rawAttrs {
                    if (self.attributes == nil) { self.attributes = Array<EventAttribute>() }
                    self.attributes?.append(EventAttribute(rawAttr as! [String : Any]))
                }
            }
        }
    }
    
    public struct EventAttribute {
        var key: String?
        var value: String?
        
        init() {}
        
        init(_ dictionary: [String: Any]) {
            if let rawKey = dictionary["key"] as? String {
                self.key = rawKey
            }
            if let rawValue = dictionary["value"] as? String {
                self.value = rawValue
            }
        }
    }
    
    public struct Result {
        var Code: Int?
        var Log: String?
        var GasWanted: String?
        var GasUsed: String?
        var Tags: Array<Tag>?
        
        init() {}
        
        init(_ dictionary: [String: Any]) {
            if let rawCode = dictionary["Code"] as? Int {
                self.Code = rawCode
            }
            if let rawLog = dictionary["Log"] as? String {
                self.Log = rawLog
            }
            if let rawGasWanted = dictionary["GasWanted"] as? String {
                self.GasWanted = rawGasWanted
            }
            if let rawGasUsed = dictionary["GasUsed"] as? String {
                self.GasUsed = rawGasUsed
            }
            if let rawTags = dictionary["Tags"] as? Array<NSDictionary> {
                for rawTag in rawTags {
                    if (self.Tags == nil) { self.Tags = Array<Tag>() }
                    self.Tags?.append(Tag(rawTag as! [String : Any]))
                }
            }
        }
    }
    
    public struct Tag {
        var key: String?
        var value: String?
        
        init() {}
        
        init(_ dictionary: [String: Any]) {
            if let rawKey = dictionary["key"] as? String {
                self.key = rawKey
            }
            if let rawValue = dictionary["value"] as? String {
                self.value = rawValue
            }
        }
    }
    
    
    
    public func isSuccessIris() -> Bool {
        if (result?.Code ?? 0 > 0) {
            return false
        } else {
            return true
        }
    }
    
    public func failMsgIris() -> String {
        if let msg = result?.Log, !msg.isEmpty {
            return msg
        }
        return ""
    }
    
    public func simpleUsedFeeIris() -> NSDecimalNumber {
        return simpleFee().multiplying(by: NSDecimalNumber.init(string: result?.GasUsed)).dividing(by: NSDecimalNumber.init(string: result?.GasWanted), withBehavior: WUtils.handler18)
    }
    
    public func simpleRewardIris() -> NSDecimalNumber {
        var reward = NSDecimalNumber.zero
        self.result?.Tags?.forEach({ (tag) in
            if (tag.key == "withdraw-reward-total") {
                if let value = tag.value {
                    reward = NSDecimalNumber.init(string: value.filter{ $0.isNumber })
                }
            }
        })
        return reward
    }
    
    public func rewardValidatorsIris() -> Array<Tag> {
        var tags = Array<Tag>()
        self.result?.Tags?.forEach({ (tag) in
            if ((tag.key?.starts(with: "withdraw-reward-from-validator-")) != nil) {
                tags.append(tag)
            }
        })
        return tags
    }
    
    public func rewardValidatorIris(_ position:Int) -> String {
        if let key = rewardValidatorsIris()[position].key {
            return key.replacingOccurrences(of: "withdraw-reward-from-validator-", with: "")
        }
        return ""
    }
    
    public func simpleCommissionIris() -> NSDecimalNumber {
        var reward = NSDecimalNumber.zero
        self.result?.Tags?.forEach({ (tag) in
            if (tag.key == "withdraw-reward-commission") {
                if let value = tag.value {
                    reward = NSDecimalNumber.init(string: value.filter{ $0.isNumber })
                }
            }
        })
        return reward
    }
}

/*
public struct TxInfo {
    var height: String = ""
    var txhash: String = ""
    var code: Int = -1
    
    
    
    var hash: String = ""
    var txTime: String = ""
    var tx: StdTx = StdTx.init()
    
    var raw_log: String = ""
    var events: Array<Event> = Array<Event>()
    var gas_wanted: String = ""
    var gas_used: String = ""
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
        
        
        self.raw_log = dictionary["raw_log"] as? String ?? ""
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
                
                if (self.events.count <= 0) {
                    if let rawEvents = log.object(forKey: "events") as? Array<NSDictionary> {
                        for rawEvent in rawEvents {
                            self.events.append(Event(rawEvent as! [String : Any]))
                        }
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
    
    
    public struct Log {
//        var msg_index: Int = -1
//        var success: Bool = false
//        var log: String = ""
//        var events: Array<Event> = Array<Event>()
        var msg_index: Int?
        var success: Bool?
        var log: String?
        var events: Array<Event>?

        init() {}

        init(_ dictionary: [String: Any]) {
            if let rawMsgIndex = dictionary["msg_index"] as? Int {
                self.msg_index = rawMsgIndex
            }
            if let rawSuccess = dictionary["success"] as? Bool {
                self.success = rawSuccess
            }
            if let rawLog = dictionary["log"] as? String {
                self.log = rawLog
            }
            if let rawEvents = dictionary["events"] as? Array<NSDictionary> {
                for rawEvent in rawEvents {
                    self.events?.append(Event(rawEvent as! [String : Any]))
                }
            }
//            self.msg_index = dictionary["msg_index"] as? Int ?? -1
//            self.success = dictionary["success"] as? Bool ?? false
//            self.log = dictionary["log"] as? String ?? ""
//            self.events.removeAll()
//            let rawEvents = dictionary["events"] as! Array<NSDictionary>
//            for rawEvent in rawEvents {
//                self.events.append(Event(rawEvent as! [String : Any]))
//            }
        }
    }
    
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
                        result = attr.value.filter{ $0.isNumber }
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
                        result = event.attributes[i - 1].value.filter{ $0.isNumber }
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
                        result = attr.value.filter{ $0.isNumber }
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
            if (event.type == "create_atomic_swap") {
                for attr in event.attributes {
                    if (attr.key == "atomic_swap_id") {
                        result = attr.value;
                    }
                }
            }
        }
        return result
    }
    
    public func getKavaIncentive() -> Coin {
        var coin = Coin.init()
        var rewardSum = NSDecimalNumber.zero
        for event in self.events {
            if (event.type == "claim_reward") {
                for attr in event.attributes {
                    if (attr.key == "claim_amount") {
                        let value = attr.value;
                        coin.denom = value.filter{ $0.isLetter }
//                        coin.amount = value.filter{ $0.isNumber }
                        rewardSum = rewardSum.adding(NSDecimalNumber.init(string: value.filter{ $0.isNumber }))
                    }
                }
            }
        }
        coin.amount = rewardSum.stringValue
        return coin
    }
}
*/
