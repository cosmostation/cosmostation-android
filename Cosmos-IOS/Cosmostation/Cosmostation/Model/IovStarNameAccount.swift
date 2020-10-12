//
//  IovStarNameAccount.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/10/12.
//  Copyright © 2020 wannabit. All rights reserved.
//

import Foundation

public struct IovStarNameAccount: Codable {
    var height: String = ""
    var result: IovAccountValue = IovAccountValue.init()
    
    init() {}
    
    init(_ dictionary: [String: Any]) {
        self.height = dictionary["height"] as? String ?? ""
        self.result = IovAccountValue.init(dictionary["result"] as! [String : Any])
    }
    
    public struct IovAccountValue: Codable {
        var accounts: Array<StarNameAccount> = Array<StarNameAccount>()
        
        init() {}
        
        init(_ dictionary: [String: Any]) {
            self.accounts.removeAll()
            if let RawAccounts = dictionary["accounts"] as? Array<NSDictionary> {
                for RawAccount in RawAccounts {
                    self.accounts.append(StarNameAccount(RawAccount as! [String : Any]))
                }
            }
        }
    }
}
