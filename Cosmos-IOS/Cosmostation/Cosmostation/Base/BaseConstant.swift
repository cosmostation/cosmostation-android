//
//  BaseConstant.swift
//  Cosmostation
//
//  Created by yongjoo on 07/03/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import Foundation
import SQLite

let SHOW_LOG                            = true;
let TESTNET                             = false;
let FEE_FREE                            = true;

let KEY_RECENT_ACCOUNT                  = "KEY_RECENT_ACCOUNT"
let KEY_ALL_VAL_SORT                    = "KEY_ALL_VAL_SORT"
let KEY_MY_VAL_SORT                     = "KEY_MY_VAL_SORT"
let KEY_LAST_TAB                        = "KEY_LAST_TAB"
let KEY_ACCOUNT_REFRESH_ALL             = "KEY_ACCOUNT_REFRESH_ALL"
let KEY_ATOM_TIC_CMC                    = "KEY_ATOM_TIC_CMC"


//let CSS_LCD_URL                         = "https://lcd-dev.cosmostation.io/";
//let CSS_LCD_URL                         = "https://lcd-dev-internal.cosmostation.io/";

//product URL
//let CSS_LCD_URL                         = "https://lcd.cosmostation.io/";
let CSS_LCD_URL                         = "https://lcd-app.cosmostation.io/";
//let CSS_LCD_URL                         = "https://lcd-mainnet-dev.cosmostation.io/";


//free URL
//let CSS_LCD_URL                         = "https://lcd-dev.cosmostation.io/";


//testnet URL
//let CSS_LCD_URL                         = "https://lcd-gaia.cosmostation.io/";

let CMC_URL                             = "https://api.coinmarketcap.com/";
let KEY_BASE_URL                        = "https://keybase.io/";

let CSS_LCD_URL_BLOCK                   = CSS_LCD_URL + "blocks/";
let CSS_LCD_URL_TX                      = CSS_LCD_URL + "txs/";
let CSS_LCD_URL_VALIDATORS              = CSS_LCD_URL + "staking/validators";
let CSS_LCD_URL_ACCOUNT_INFO            = CSS_LCD_URL + "auth/accounts/";
let CSS_LCD_URL_BONDING                 = CSS_LCD_URL + "staking/delegators/";
let CSS_LCD_URL_BONDING_TAIL            = "/delegations";
let CSS_LCD_URL_UNBONDING               = CSS_LCD_URL + "staking/delegators/";
let CSS_LCD_URL_UNBONDING_TAIL          = "/unbonding_delegations";
let CSS_LCD_URL_REWARD_ALL              = CSS_LCD_URL + "distribution/delegators/";
let CSS_LCD_URL_REWARD_ALL_TAIL         = "/rewards";
let CSS_LCD_URL_REWARD_FROM_VAL         = CSS_LCD_URL + "distribution/delegators/";
let CSS_LCD_URL_REWARD_FROM_VAL_TAIL    = "/rewards/";
let CSS_LCD_URL_REWARD_ADDRESS          = CSS_LCD_URL + "distribution/delegators/";
let CSS_LCD_URL_REWARD_ADDRESS_TAIL     = "/withdraw_address";

let CSS_LCD_URL_PROPOSALS               = CSS_LCD_URL + "/gov/proposals";

let CSS_LCD_URL_BORAD_TX                = CSS_LCD_URL + "txs";


let CSS_ES_URL                          = "https://search-cosmoshub-2-app-7rb6ujvvmemw6holj6cr7em23u.ap-northeast-2.es.amazonaws.com/tx_index/_search/";


let CMC_PRICE_TIC                       = CMC_URL + "v2/ticker/";


let KEY_BASE_URL_USER_INFO              = KEY_BASE_URL + "_/api/1.0/user/lookup.json";



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


////DB for Password
//let DB_PASSWORD = Table("passwd")
//let DB_PASSWORD_ID                  = Expression<Int64>("id")
//let DB_PASSWORD_RESOURCE            = Expression<String>("resource")


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





let COSMOS_AUTH_TYPE_DELAYEDACCOUNT         = "auth/DelayedVestingAccount";
let COSMOS_AUTH_TYPE_ACCOUNT                = "auth/Account";

let COSMOS_MSG_TYPE_TRANSFER                = "cosmos-sdk/Send";
let COSMOS_MSG_TYPE_TRANSFER2               = "cosmos-sdk/MsgSend";
let COSMOS_MSG_TYPE_DELEGATE                = "cosmos-sdk/MsgDelegate";
let COSMOS_MSG_TYPE_UNDELEGATE              = "cosmos-sdk/Undelegate";
let COSMOS_MSG_TYPE_UNDELEGATE2             = "cosmos-sdk/MsgUndelegate";
let COSMOS_MSG_TYPE_REDELEGATE              = "cosmos-sdk/BeginRedelegate";
let COSMOS_MSG_TYPE_REDELEGATE2             = "cosmos-sdk/MsgBeginRedelegate";
let COSMOS_MSG_TYPE_WITHDRAW_DEL            = "cosmos-sdk/MsgWithdrawDelegationReward";
let COSMOS_MSG_TYPE_WITHDRAW_VAL            = "cosmos-sdk/MsgWithdrawValidatorCommission";
let COSMOS_MSG_TYPE_WITHDRAW_MIDIFY         = "cosmos-sdk/MsgModifyWithdrawAddress";


let COSMOS_MSG_TYPE_VOTE                    = "cosmos-sdk/MsgVote";
let COSMOS_MSG_TYPE_SUBMIT_PROPOSAL         = "cosmos-sdk/MsgSubmitProposal";
let COSMOS_MSG_TYPE_DEPOSIT                 = "cosmos-sdk/MsgDeposit";
let COSMOS_MSG_TYPE_CREATE_VALIDATOR        = "cosmos-sdk/MsgCreateValidator";
let COSMOS_MSG_TYPE_EDIT_VALIDATOR          = "cosmos-sdk/MsgEditValidator";


let COSMOS_KEY_TYPE_PUBLIC                  = "tendermint/PubKeySecp256k1";
let COSMOS_AUTH_TYPE_STDTX                  = "auth/StdTx";







let PASSWORD_ACTION_INIT                    = "ACTION_INIT"
let PASSWORD_ACTION_SIMPLE_CHECK            = "ACTION_SIMPLE_CHECK"
let PASSWORD_ACTION_DELETE_ACCOUNT          = "ACTION_DELETE_ACCOUNT"
let PASSWORD_ACTION_CHECK_TX                = "PASSWORD_ACTION_CHECK_TX"



let PASSWORD_RESUKT_OK                      = 0
let PASSWORD_RESUKT_CANCEL                  = 1
let PASSWORD_RESUKT_FAIL                    = 2
let PASSWORD_RESUKT_OK_FOR_DELETE           = 3




//let SUPPORT_CHAIN_COSMOS_MAIN               = "cosmoshub"
//let SUPPORT_CHAIN_IRSI_MAIN                 = "irishub"



let BASE_PATH                               = "44'/118'/0'/0/"
let FEE_ATOM_TINY                           = "500";
let FEE_ATOM_LOW                            = "1000";
let FEE_ATOM_MID                            = "2000";
let FEE_ATOM_HIGH                           = "4000";
let FEE_MIN_RATE                            = "0.0025";

let FEE_REWARD_GAS_1                        = "200000";
let FEE_REWARD_GAS_2                        = "200000";
let FEE_REWARD_GAS_3                        = "220000";
let FEE_REWARD_GAS_4                        = "300000";
let FEE_REWARD_GAS_5                        = "380000";
let FEE_REWARD_GAS_6                        = "400000";
let FEE_REWARD_GAS_7                        = "560000";
let FEE_REWARD_GAS_8                        = "600000";
let FEE_REWARD_GAS_9                        = "740000";
let FEE_REWARD_GAS_10                       = "800000";
let FEE_REWARD_GAS_11                       = "920000";
let FEE_REWARD_GAS_12                       = "1000000";
let FEE_REWARD_GAS_13                       = "1050000";
let FEE_REWARD_GAS_14                       = "1100000";
let FEE_REWARD_GAS_15                       = "1150000";
let FEE_REWARD_GAS_16                       = "1200000";



enum ChainType: String {
    case SUPPORT_CHAIN_COSMOS_MAIN
    case SUPPORT_CHAIN_IRSI_MAIN
    
}
