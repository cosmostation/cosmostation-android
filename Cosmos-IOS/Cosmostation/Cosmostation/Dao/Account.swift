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
}
