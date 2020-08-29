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
        var withdraw_addr: String?
        var validator_src_addr: String?
        var validator_dst_addr: String?
        var shares: String?
        var proposal_id: String?
        var voter: String?
        var option: String?
        var from: String?
        var market_id: String?
        var price: String?
        var expiry: String?
        var sender: String?
        var depositor: String?
        var owner: String?
        var collateral: Coin?
        var principal: Coin?
        var payment: Coin?
        
        var cdp_denom: String?
        var swap_id: String?
        var random_number: String?
        var random_number_hash: String?
        var to: String?
        var recipient_other_chain: String?
        var sender_other_chain: String?
        var timestamp: String?
        var expected_income: String?
        var height_span: String?
        var cross_chain: Bool?
        var denom: String?
        
        var transfers: Array<OkTransfer>?
        var quantity: Coin?
        var validator_addresses: Array<String>?
        
        
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
            case withdraw_addr
            case validator_src_addr
            case validator_dst_addr
            case shares
            case proposal_id
            case voter
            case option
            case from
            case market_id
            case price
            case expiry
            case sender
            case depositor
            case owner
            case collateral
            case principal
            case payment
            case cdp_denom
            case swap_id
            case random_number
            case random_number_hash
            case to
            case recipient_other_chain
            case sender_other_chain
            case timestamp
            case expected_income
            case height_span
            case cross_chain
            case denom
            
            case transfers
            case quantity
            case validator_addresses
            
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
            
            if let address = dictionary["withdraw_addr"] as? String {
                self.withdraw_addr = address
            }
            
            if let address = dictionary["validator_src_addr"] as? String {
                self.validator_src_addr = address
            }
            
            if let address = dictionary["validator_dst_addr"] as? String {
                self.validator_dst_addr = address
            }
            
            if let shares = dictionary["shares"] as? String {
                self.shares = shares
            }
            
            if let proposal_id =  dictionary["proposal_id"] as? String {
                self.proposal_id = proposal_id
            }
            
            if let voter =  dictionary["voter"] as? String {
                self.voter = voter
            }
            
            if let option =  dictionary["option"] as? String {
                self.option = option
            }
            
            if let from =  dictionary["from"] as? String {
                self.from = from
            }
            if let market_id =  dictionary["market_id"] as? String {
                self.market_id = market_id
            }
            if let price =  dictionary["price"] as? String {
                self.price = price
            }
            if let expiry =  dictionary["expiry"] as? String {
                self.expiry = expiry
            }
            if let sender =  dictionary["sender"] as? String {
                self.sender = sender
            }
            if let depositor =  dictionary["depositor"] as? String {
                self.depositor = depositor
            }
            if let owner =  dictionary["owner"] as? String {
                self.owner = owner
            }
            if let rawCollateral = dictionary["collateral"] as? [String : Any] {
                self.collateral = Coin(rawCollateral)
            }
            if let rawPrincipal = dictionary["principal"] as? [String : Any] {
                self.principal = Coin(rawPrincipal)
            }
            if let rawPayment = dictionary["payment"] as? [String : Any] {
                self.payment = Coin(rawPayment)
            }
            
            
            if let cdp_denom =  dictionary["cdp_denom"] as? String {
                self.cdp_denom = cdp_denom
            }
            
            
            if let swap_id =  dictionary["swap_id"] as? String {
                self.swap_id = swap_id
            }
            if let random_number =  dictionary["random_number"] as? String {
                self.random_number = random_number
            }
            if let random_number_hash =  dictionary["random_number_hash"] as? String {
                self.random_number_hash = random_number_hash
            }
            if let to =  dictionary["to"] as? String {
                self.to = to
            }
            if let recipient_other_chain =  dictionary["recipient_other_chain"] as? String {
                self.recipient_other_chain = recipient_other_chain
            }
            if let sender_other_chain =  dictionary["sender_other_chain"] as? String {
                self.sender_other_chain = sender_other_chain
            }
            if let timestamp =  dictionary["timestamp"] as? String {
                self.timestamp = timestamp
            }
            if let expected_income =  dictionary["expected_income"] as? String {
                self.expected_income = expected_income
            }
            if let height_span =  dictionary["height_span"] as? String {
                self.height_span = height_span
            }
            if let cross_chain =  dictionary["cross_chain"] as? Bool {
                self.cross_chain = cross_chain
            }
            if let denom =  dictionary["denom"] as? String {
                self.denom = denom
            }
            
            
            
            if let rawTransfers = dictionary["transfers"] as? Array<NSDictionary> {
                self.transfers =  Array<OkTransfer>()
                for rawTransfer in rawTransfers {
                    self.transfers?.append(OkTransfer(rawTransfer as! [String : Any]))
                }
            }
            if let rawQuantity = dictionary["quantity"] as? [String : Any] {
                self.quantity = Coin(rawQuantity)
            }
            if let vAddresses = dictionary["validator_addresses"] as? Array<String> {
                self.validator_addresses =  Array<String>()
                for vAddresse in vAddresses {
                    self.validator_addresses?.append(vAddresse)
                }
            }
        }
        
    }
}
