//
//  BaseConstant.swift
//  Cosmostation
//
//  Created by yongjoo on 07/03/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import Foundation
import SQLite

let SHOW_LOG                        = true;



//DB for Account
let DB_ACCOUNT = Table("accnt")
let DB_ACCOUNT_ID                   = Expression<Int64>("id")
let DB_ACCOUNT_UUID                 = Expression<String>("uuid")
let DB_ACCOUNT_NICKNAME             = Expression<String>("nickName")
let DB_ACCOUNT_FAVO                 = Expression<Bool>("isFavo")
let DB_ACCOUNT_ADDRESS              = Expression<String>("address")

let DB_ACCOUNT_BASECHAIN            = Expression<String>("baseChain")
let DB_ACCOUNT_HAS_PRIVATE          = Expression<Bool>("hasPrivateKey")
let DB_ACCOUNT_RESOURCE             = Expression<String>("resource")
//let DB_ACCOUNT_SEPC               = Expression<String>("spec")
let DB_ACCOUNT_FROM_MNEMONIC        = Expression<Bool>("fromMnemonic")
let DB_ACCOUNT_PATH                 = Expression<String>("path")

let DB_ACCOUNT_IS_VALIDATOR         = Expression<Bool>("isValidator")
let DB_ACCOUNT_SEQUENCE_NUMBER      = Expression<Int64>("sequenceNumber")
let DB_ACCOUNT_ACCOUNT_NUMBER       = Expression<Int64>("accountNumber")
let DB_ACCOUNT_FETCH_TIME           = Expression<Int64>("fetchTime")
let DB_ACCOUNT_M_SIZE               = Expression<Int64>("msize")

let DB_ACCOUNT_IMPORT_TIME          = Expression<Int64>("importTime")


//DB for Password
let DB_PASSWORD = Table("passwd")
let DB_PASSWORD_ID                  = Expression<Int64>("id")
let DB_PASSWORD_RESOURCE            = Expression<String>("resource")


//DB for Balance(Available)
let DB_BALANCE = Table("balan")
let DB_BALANCE_ID                   = Expression<Int64>("id")
let DB_BALANCE_ACCOUNT_ID           = Expression<Int64>("accountId")
let DB_BALANCE_DENOM                = Expression<String>("denom")
let DB_BALANCE_AMOUNT               = Expression<String>("amount")
let DB_BALANCE_FETCH_TIME           = Expression<Int64>("fetchTime")


//DB for Bonding
let DB_BONDING = Table("bondi")
let DB_BONDING_ID                   = Expression<Int64>("id")
let DB_BONDING_ACCOUNT_ID           = Expression<Int64>("accountId")
let DB_BONDING_V_Address            = Expression<String>("validatorAddress")
let DB_BONDING_SHARES               = Expression<String>("shares")
let DB_BONDING_FETCH_TIME           = Expression<Int64>("fetchTime")


//DB for UnBonding
let DB_UNBONDING = Table("unbond")
let DB_UNBONDING_ID                 = Expression<Int64>("id")
let DB_UNBONDING_ACCOUNT_ID         = Expression<Int64>("accountId")
let DB_UNBONDING_V_Address          = Expression<String>("validatorAddress")
let DB_UNBONDING_CREATE_HEIGHT      = Expression<String>("creationHeight")
let DB_UNBONDING_COMPLETE_TIME      = Expression<Int64>("completionTime")
let DB_UNBONDING_INITIAL_BALANCE    = Expression<String>("initialBalance")
let DB_UNBONDING_BALANCE            = Expression<String>("balance")
let DB_UNBONDING_FETCH_TIME         = Expression<Int64>("fetchTime")
