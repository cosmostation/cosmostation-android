//
//  BaseData.swift
//  Cosmostation
//
//  Created by yongjoo on 07/03/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import Foundation
import SQLite


final class BaseData : NSObject{
    
    static let instance = BaseData()
    
    var database: Connection!
    
    public override init() {
        super.init();
        if database == nil {
            self.initdb();
        }
    }
    
    
    func initdb() {
        do {
            let documentDirectory = try FileManager.default.url(for: .documentDirectory, in: .userDomainMask, appropriateFor: nil, create: true)
            let fileUrl = documentDirectory.appendingPathComponent("cosmostation").appendingPathExtension("sqlite3")
            let database = try Connection(fileUrl.path)
            self.database = database
            
        } catch {
            
        }
    }
}
