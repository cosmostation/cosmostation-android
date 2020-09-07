//
//  IovNameResolve.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/09/02.
//  Copyright © 2020 wannabit. All rights reserved.
//

import Foundation

public struct IovNameResolve: Codable {
    var height: String = ""
    var result: IovNameValue = IovNameValue.init()
    
    init() {}
    
    init(_ dictionary: [String: Any]) {
        self.height = dictionary["height"] as? String ?? ""
        self.result = IovNameValue.init(dictionary["result"] as! [String : Any])
    }
    
    //TODO update with hardcoding
    func getAddressWithChain(_ chain: ChainType) -> String! {
        for nameResource in self.result.account.resources {
            if (chain == ChainType.COSMOS_MAIN) {
                if (nameResource.uri == "asset:atom" && nameResource.resource.starts(with: "cosmos")) {
                    return nameResource.resource
                }
                
            } else if (chain == ChainType.IRIS_MAIN) {
                if (nameResource.uri == "asset:iris" && nameResource.resource.starts(with: "iaa")) {
                    return nameResource.resource
                }
                
            } else if (chain == ChainType.BINANCE_MAIN) {
                if (nameResource.uri == "asset:bnb" && nameResource.resource.starts(with: "bnb")) {
                    return nameResource.resource
                }
                
            } else if (chain == ChainType.KAVA_MAIN) {
                if (nameResource.uri == "asset:kava" && nameResource.resource.starts(with: "kava")) {
                    return nameResource.resource
                }
                
            } else if (chain == ChainType.IOV_MAIN) {
                if (nameResource.uri == "asset:iov" && nameResource.resource.starts(with: "star")) {
                    return nameResource.resource
                }
                
            } else if (chain == ChainType.BAND_MAIN) {
                if (nameResource.uri == "asset:band" && nameResource.resource.starts(with: "band")) {
                    return nameResource.resource
                }
                
            }
        }
        return nil
    }
    
    
    
    public struct IovNameValue: Codable {
        var account: NameAccount = NameAccount.init()
        
        init() {}
        
        init(_ dictionary: [String: Any]) {
            self.account = NameAccount.init(dictionary["account"] as! [String : Any])
        }
    }
    
    public struct NameAccount: Codable {
        var domain: String = ""
        var name: String = ""
        var owner: String = ""
        var valid_until: Int64 = -1
        var certificates: String = ""
        var broker: String = ""
        var metadata_uri: String = ""
        var resources: Array<NameResource> = Array<NameResource>()
        
        init() {}
        
        init(_ dictionary: [String: Any]) {
            self.domain = dictionary["domain"] as? String ?? ""
            self.name = dictionary["name"] as? String ?? ""
            self.owner = dictionary["owner"] as? String ?? ""
            self.valid_until = dictionary["valid_until"] as? Int64 ?? -1
            self.certificates = dictionary["certificates"] as? String ?? ""
            self.broker = dictionary["broker"] as? String ?? ""
            self.metadata_uri = dictionary["metadata_uri"] as? String ?? ""
            self.resources.removeAll()
            if let rawResources = dictionary["resources"] as? Array<NSDictionary> {
                for rawResource in rawResources {
                    self.resources.append(NameResource.init(rawResource as! [String : Any]))
                }
            }
        }
    }
    
    public struct NameResource: Codable {
        var uri: String = ""
        var resource: String = ""
        
        init() {}
        
        init(_ dictionary: [String: Any]) {
            self.uri = dictionary["uri"] as? String ?? ""
            self.resource = dictionary["resource"] as? String ?? ""
        }
    }
}
