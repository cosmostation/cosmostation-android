//
//  IovStarNameConfig.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/10/12.
//  Copyright © 2020 wannabit. All rights reserved.
//

import Foundation

public struct IovStarNameConfig: Codable {
    var height: String = ""
    var result: IovConfigValue = IovConfigValue.init()
    
    init() {}
    
    init(_ dictionary: [String: Any]) {
        self.height = dictionary["height"] as? String ?? ""
        self.result = IovConfigValue.init(dictionary["result"] as! [String : Any])
    }
    
    public struct IovConfigValue: Codable {
        var configuration: IovConfig = IovConfig.init()
        
        init() {}
        
        init(_ dictionary: [String: Any]) {
            self.configuration = IovConfig.init(dictionary["configuration"] as! [String : Any])
        }
    }
    
    public struct IovConfig: Codable {
        var configurer: String = ""
        var valid_domain_name: String = ""
        var valid_account_name: String = ""
        var valid_uri: String = ""
        var valid_resource: String = ""
        var domain_renew_period: Int64 = -1
        var domain_renew_count_max: Int64 = -1
        var domain_grace_period: Int64 = -1
        var account_renew_period: Int64 = -1
        var account_renew_count_max: Int64 = -1
        var account_grace_period: Int64 = -1
        var resources_max: Int64 = -1
        var certificate_size_max: Int64 = -1
        var certificate_count_max: Int64 = -1
        var metadata_size_max: Int64 = -1
        
        init() {}
        
        init(_ dictionary: [String: Any]) {
            self.configurer = dictionary["configurer"] as? String ?? ""
            self.valid_domain_name = dictionary["valid_domain_name"] as? String ?? ""
            self.valid_account_name = dictionary["valid_account_name"] as? String ?? ""
            self.valid_uri = dictionary["valid_uri"] as? String ?? ""
            self.valid_resource = dictionary["valid_resource"] as? String ?? ""
            self.domain_renew_period = dictionary["domain_renew_period"] as? Int64 ?? -1
            self.domain_renew_count_max = dictionary["domain_renew_count_max"] as? Int64 ?? -1
            self.domain_grace_period = dictionary["domain_grace_period"] as? Int64 ?? -1
            self.account_renew_period = dictionary["account_renew_period"] as? Int64 ?? -1
            self.account_renew_count_max = dictionary["account_renew_count_max"] as? Int64 ?? -1
            self.account_grace_period = dictionary["account_grace_period"] as? Int64 ?? -1
            self.resources_max = dictionary["resources_max"] as? Int64 ?? -1
            self.certificate_size_max = dictionary["certificate_size_max"] as? Int64 ?? -1
            self.certificate_count_max = dictionary["certificate_count_max"] as? Int64 ?? -1
            self.metadata_size_max = dictionary["metadata_size_max"] as? Int64 ?? -1
        }
        
        public func getRenewPeriod(_ type: String) -> Int64 {
            if (type == IOV_MSG_TYPE_RENEW_DOMAIN) {
                return NSDecimalNumber.init(value: domain_renew_period).multiplying(byPowerOf10: -6).int64Value
            }
            return NSDecimalNumber.init(value: account_renew_period).multiplying(byPowerOf10: -6).int64Value
        }
        
        public func getRegisterDomainExpireTime() -> Int64 {
            return NSDecimalNumber.init(value: domain_renew_period).multiplying(byPowerOf10: -6).int64Value
        }
    }
}
