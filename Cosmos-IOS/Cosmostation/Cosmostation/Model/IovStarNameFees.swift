//
//  IovStarNameFees.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/10/12.
//  Copyright © 2020 wannabit. All rights reserved.
//

import Foundation

public struct IovStarNameFees: Codable {
    var height: String = ""
    var result: IovFeeValue = IovFeeValue.init()
    
    init() {}
    
    init(_ dictionary: [String: Any]) {
        self.height = dictionary["height"] as? String ?? ""
        self.result = IovFeeValue.init(dictionary["result"] as! [String : Any])
    }
    
    public struct IovFeeValue: Codable {
        var fees: IovFee = IovFee.init()
        
        init() {}
        
        init(_ dictionary: [String: Any]) {
            self.fees = IovFee.init(dictionary["fees"] as! [String : Any])
        }
    }
    
    public struct IovFee: Codable {
        var fee_coin_denom: String = ""
        var fee_coin_price: String = ""
        var fee_default: String = ""
        var register_account_closed: String = ""
        var register_account_open: String = ""
        var transfer_account_closed: String = ""
        var transfer_account_open: String = ""
        var replace_account_resources: String = ""
        var add_account_certificate: String = ""
        var del_account_certificate: String = ""
        var set_account_metadata: String = ""
        var register_domain_1: String = ""
        var register_domain_2: String = ""
        var register_domain_3: String = ""
        var register_domain_4: String = ""
        var register_domain_5: String = ""
        var register_domain_default: String = ""
        var register_open_domain_multiplier: String = ""
        var transfer_domain_closed: String = ""
        var transfer_domain_open: String = ""
        var renew_domain_open: String = ""
        
        init() {}
        
        init(_ dictionary: [String: Any]) {
            self.fee_coin_denom = dictionary["fee_coin_denom"] as? String ?? ""
            self.fee_coin_price = dictionary["fee_coin_price"] as? String ?? ""
            self.fee_default = dictionary["fee_default"] as? String ?? ""
            self.register_account_closed = dictionary["register_account_closed"] as? String ?? ""
            self.register_account_open = dictionary["register_account_open"] as? String ?? ""
            self.transfer_account_closed = dictionary["transfer_account_closed"] as? String ?? ""
            self.transfer_account_open = dictionary["transfer_account_open"] as? String ?? ""
            self.replace_account_resources = dictionary["replace_account_resources"] as? String ?? ""
            self.add_account_certificate = dictionary["add_account_certificate"] as? String ?? ""
            self.del_account_certificate = dictionary["del_account_certificate"] as? String ?? ""
            self.set_account_metadata = dictionary["set_account_metadata"] as? String ?? ""
            self.register_domain_1 = dictionary["register_domain_1"] as? String ?? ""
            self.register_domain_2 = dictionary["register_domain_2"] as? String ?? ""
            self.register_domain_3 = dictionary["register_domain_3"] as? String ?? ""
            self.register_domain_4 = dictionary["register_domain_4"] as? String ?? ""
            self.register_domain_5 = dictionary["register_domain_5"] as? String ?? ""
            self.register_domain_default = dictionary["register_domain_default"] as? String ?? ""
            self.register_open_domain_multiplier = dictionary["register_open_domain_multiplier"] as? String ?? ""
            self.transfer_domain_closed = dictionary["transfer_domain_closed"] as? String ?? ""
            self.transfer_domain_open = dictionary["transfer_domain_open"] as? String ?? ""
            self.renew_domain_open = dictionary["renew_domain_open"] as? String ?? ""
        }
        
        
        
        
        
        public func getDomainFee(_ domain: String, _ type: String) -> NSDecimalNumber {
            var feeResult = NSDecimalNumber.zero
            if (domain.isEmpty) {
                return feeResult
            } else if (domain.count <= 3) {
                return feeResult
                
            } else if (domain.count == 4) {
                feeResult = NSDecimalNumber.init(string: register_domain_4).dividing(by: NSDecimalNumber.init(string: fee_coin_price), withBehavior: WUtils.handler0Down)

            } else if (domain.count == 5) {
                feeResult = NSDecimalNumber.init(string: register_domain_5).dividing(by: NSDecimalNumber.init(string: fee_coin_price), withBehavior: WUtils.handler0Down)

            } else {
                feeResult = NSDecimalNumber.init(string: register_domain_default).dividing(by: NSDecimalNumber.init(string: fee_coin_price), withBehavior: WUtils.handler0Down)
            
            }
            
            if (type == "open") {
                feeResult = feeResult.multiplying(by: NSDecimalNumber.init(string: register_open_domain_multiplier))
            }
            return feeResult
        }
        
        public func getAccountFee(_ type: String) -> NSDecimalNumber {
            if (type == "open") {
                return NSDecimalNumber.init(string: register_account_open).dividing(by: NSDecimalNumber.init(string: fee_coin_price), withBehavior: WUtils.handler0Down)
            } else {
                return NSDecimalNumber.init(string: register_account_closed).dividing(by: NSDecimalNumber.init(string: fee_coin_price), withBehavior: WUtils.handler0Down)
            }
        }
        
        public func getReplaceFee() -> NSDecimalNumber {
            return NSDecimalNumber.init(string: replace_account_resources).dividing(by: NSDecimalNumber.init(string: fee_coin_price), withBehavior: WUtils.handler0Down)
        }
        
        public func getDomainRenewFee(_ type: String) -> NSDecimalNumber {
            if (type == "open") {
                return NSDecimalNumber.init(string: renew_domain_open).dividing(by: NSDecimalNumber.init(string: fee_coin_price), withBehavior: WUtils.handler0Down)
            } else {
                return NSDecimalNumber.init(string: register_account_closed).dividing(by: NSDecimalNumber.init(string: fee_coin_price), withBehavior: WUtils.handler0Down)
            }
        }
        
        public func getAccountRenewFee(_ type: String) -> NSDecimalNumber {
            if (type == "open") {
                return NSDecimalNumber.init(string: register_account_open).dividing(by: NSDecimalNumber.init(string: fee_coin_price), withBehavior: WUtils.handler0Down)
            } else {
                return NSDecimalNumber.init(string: register_account_closed).dividing(by: NSDecimalNumber.init(string: fee_coin_price), withBehavior: WUtils.handler0Down)
            }
        }
    }
}
