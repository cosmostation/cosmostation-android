//
//  BlockInfo.swift
//  Cosmostation
//
//  Created by yongjoo on 12/04/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import Foundation

public struct BlockInfo {
    var block_meta: BlockMeta = BlockMeta.init()
    
    init() {}
    
    init(_ dictionary: [String: Any]) {
        self.block_meta = BlockMeta.init(dictionary["block_meta"] as! [String : Any])
    }
    
    public struct BlockMeta {
        var header: BlockMetaHeader = BlockMetaHeader.init()
        
        init() {}
        
        init(_ dictionary: [String: Any]) {
            self.header = BlockMetaHeader.init(dictionary["header"] as! [String : Any])
        }
    }
    
    
    public struct BlockMetaHeader {
        var height: String = ""
        var time: String = ""
        
        init() {}
        
        init(_ dictionary: [String: Any]) {
            self.height = dictionary["height"] as? String ?? ""
            self.time = dictionary["time"] as? String ?? ""
        }
    }
}
