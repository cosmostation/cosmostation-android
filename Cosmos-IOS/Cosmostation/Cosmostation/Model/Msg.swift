//
//  Msg.swift
//  Cosmostation
//
//  Created by yongjoo on 25/03/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import Foundation


enum AmountType: Codable {
    case coin(Coin)
    case coins(Array<Coin>)
    
    init(from decoder: Decoder) throws {
        let container = try decoder.singleValueContainer()
        if let x = try? container.decode(Coin.self) {
            self = .coin(x)
            return
        }
        if let x = try? container.decode(Array<Coin>.self) {
            self = .coins(x)
            return
        }
        throw DecodingError.typeMismatch(AmountType.self, DecodingError.Context(codingPath: decoder.codingPath, debugDescription: "Wrong type for AmountType"))

    }
    
    func encode(to encoder: Encoder) throws {
        var container = encoder.singleValueContainer()
        switch self {
        case .coin(let x):
            try container.encode(x)
        case .coins(let x):
            try container.encode(x)
        }
    }
}

public struct Msg: Codable {
    var type: String = ""
    var value: Value = Value.init()
    
    init() {}
    
    init(_ dictionary: [String: Any]) {
        self.type = dictionary["type"] as? String ?? ""
        self.value = Value.init(dictionary["value"] as! [String : Any])
    }
    
    public struct Value: Codable {
        var inputs: Array<InOutPut>?
        var outputs: Array<InOutPut>?
        var from_address: String?
        var to_address: String?
        var delegator_address: String?
        var validator_address: String?
        var value: Coin?
        var shares_amount: String?
        var amount: AmountType?
        var withdraw_address: String?
        var validator_src_address: String?
        var validator_dst_address: String?
        var delegation: Coin?
        var delegator_addr: String?
        var validator_addr: String?
        
        enum CodingKeys: String, CodingKey {
            case inputs
            case outputs
            case from_address
            case to_address
            case delegator_address
            case validator_address
            case value
            case shares_amount
            case amount
            case withdraw_address
            case validator_src_address
            case validator_dst_address
            case delegation
            case delegator_addr
            case validator_addr
        }
        
        public func getAmount() -> Coin? {
            var result:Coin?
            let data = try? JSONEncoder().encode(amount)
            do {
                result = try JSONDecoder().decode(Coin.self, from:data!)
            } catch {
                print(error)
            }
            return result;
        }
        
        public func getAmounts() -> Array<Coin>? {
            var result =  Array<Coin>()
            let data = try? JSONEncoder().encode(amount)
            do {
                result = try JSONDecoder().decode(Array<Coin>.self, from:data!)
            } catch {
                print(error)
            }
            return result
        }
        
        init() {}
        
        init(_ dictionary: [String: Any]) {
            if let rawInputs = dictionary["inputs"] as? Array<NSDictionary> {
                self.inputs =  Array<InOutPut>()
                for rawInput in rawInputs {
                    self.inputs?.append(InOutPut(rawInput as! [String : Any]))
                }
            }
            
            if let rawOutputs = dictionary["outputs"] as? Array<NSDictionary> {
                self.outputs =  Array<InOutPut>()
                for rawOutput in rawOutputs {
                    self.outputs?.append(InOutPut(rawOutput as! [String : Any]))
                }
            }
            
            if let fddress =  dictionary["from_address"] as? String {
                self.from_address = fddress
            }
            
            if let tddress =  dictionary["to_address"] as? String {
                self.to_address = tddress
            }
            
            if let daddress =  dictionary["delegator_address"] as? String {
                self.delegator_address = daddress
            }
            
            if let vaddress =  dictionary["validator_address"] as? String {
                self.validator_address = vaddress
            }
            
            if let rawValue = dictionary["value"] as? [String : Any] {
                self.value = Coin(rawValue)
            }
            
            if let sAmount =  dictionary["shares_amount"] as? String {
                self.shares_amount = sAmount
            }
            
            if let rawAmounts = dictionary["amount"] as? Array<NSDictionary> {
                var tempAmounts =  Array<Coin>()
                for rawAmount in rawAmounts {
                    tempAmounts.append(Coin(rawAmount as! [String : Any]))
                }
                let data = try? JSONEncoder().encode(tempAmounts)
                do {
                    self.amount = try JSONDecoder().decode(AmountType.self, from:data!)
                } catch {
                    print(error)
                }
            }

            if let rawAmount = dictionary["amount"] as? [String : Any] {
                let tempAmount =  Coin(rawAmount)
                let data = try? JSONEncoder().encode(tempAmount)
                do {
                    self.amount = try JSONDecoder().decode(AmountType.self, from:data!)
                } catch {
                    print(error)
                }
            }
            
            if let vaddress =  dictionary["withdraw_address"] as? String {
                self.withdraw_address = vaddress
            }
            
            if let vaddress =  dictionary["validator_src_address"] as? String {
                self.validator_src_address = vaddress
            }
            
            if let vaddress =  dictionary["validator_dst_address"] as? String {
                self.validator_dst_address = vaddress
            }
            
            if let rawValue = dictionary["delegation"] as? [String : Any] {
                self.delegation = Coin(rawValue)
            }
            
            if let address = dictionary["delegator_addr"] as? String {
                self.delegator_addr = address
            }
            
            if let address = dictionary["validator_addr"] as? String {
                self.validator_addr = address
            }
        }
        
    }
}
