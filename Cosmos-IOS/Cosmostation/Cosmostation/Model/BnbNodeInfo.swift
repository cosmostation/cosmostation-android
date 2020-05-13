//
//  BnbNodeInfo.swift
//  Cosmostation
//
//  Created by 정용주 on 2020/05/13.
//  Copyright © 2020 wannabit. All rights reserved.
//

import Foundation

public class BnbNodeInfo {
    
    var sync_info: BnbSyncInfo =  BnbSyncInfo.init()
    
    init() {}
    
    init(_ dictionary: [String: Any]) {
        self.sync_info = BnbSyncInfo.init(dictionary["sync_info"] as! [String : Any])
    }
    
    public func getCHeight() -> UInt64 {
        return self.sync_info.latest_block_height
    }
    
    public class BnbSyncInfo {
        var latest_block_height: UInt64 = 0
        var latest_block_time: String = ""
        var catching_up = false
        
        init() {}
        
        init(_ dictionary: [String: Any]) {
            self.latest_block_height = dictionary["latest_block_height"] as? UInt64 ?? 0
            self.latest_block_time = dictionary["latest_block_time"] as? String ?? ""
            self.catching_up = dictionary["catching_up"] as? Bool ?? false
        }
    }
    
    
    
}
