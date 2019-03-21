//
//  Account.swift
//  Cosmostation
//
//  Created by yongjoo on 20/03/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import Foundation

public class Account {
    var account_id: Int64 = -1;
    var account_uuid: String = "";
    var account_nick_name: String = "";
    var account_favo: Bool = false;
    var account_address: String = "";
    
    var account_base_chain: String = "";
    var account_has_private: Bool = false;
    var account_resource:String = "";
    var account_from_mnemonic:Bool = false;
    var account_path:String = "";
    
    var account_is_validator: Bool = false;
    var account_sequence_number: Int64 = -1;
    var account_account_numner: Int64 = -1;
    var account_fetch_time:Int64 = -1;
    var account_m_size:Int64 = -1;
    
    var account_import_time:Int64 = -1;
    
    init(isNew: Bool) {
        account_uuid = UUID().uuidString
    }
    
    init(_ id:Int64, _ uuid:String, _ nickName:String, _ favo:Bool, _ address:String,
         _ baseChain:String, _ hasPrivate:Bool, _ resource:String, _ fromMnemonic:Bool, _ path:String,
         _ isValidator:Bool, _ sequenceNumber:Int64, _ accountNumber:Int64, _ fetchTime:Int64, _ mSize:Int64,
         _ importTime:Int64) {
        
        self.account_id = id;
        self.account_uuid = uuid;
        self.account_nick_name = nickName;
        self.account_favo = favo;
        self.account_address = address;
        
        self.account_base_chain = baseChain;
        self.account_has_private = hasPrivate;
        self.account_resource = resource;
        self.account_from_mnemonic = fromMnemonic;
        self.account_path = path;
        
        self.account_is_validator = isValidator;
        self.account_sequence_number = sequenceNumber;
        self.account_account_numner = accountNumber;
        self.account_fetch_time = fetchTime;
        self.account_m_size = mSize;
        
        self.account_import_time = importTime;
        
    }
}
