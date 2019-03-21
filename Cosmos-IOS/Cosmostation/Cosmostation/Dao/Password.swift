//
//  Password.swift
//  Cosmostation
//
//  Created by yongjoo on 20/03/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import Foundation

public class Password {
    var password_id: Int64 = -1;
    var password_resource: String = "";
    
    
    init(_ id:Int64, _ resource:String) {
        self.password_id = id;
        self.password_resource = resource;
    }
}


