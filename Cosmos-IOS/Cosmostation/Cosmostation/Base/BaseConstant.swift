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
let SUPPORT_BEP3_SWAP                   = true;

let KEY_RECENT_ACCOUNT                  = "KEY_RECENT_ACCOUNT"
let KEY_RECENT_CHAIN                    = "KEY_RECENT_CHAIN"
let KEY_ALL_VAL_SORT                    = "KEY_ALL_VAL_SORT"
let KEY_MY_VAL_SORT                     = "KEY_MY_VAL_SORT"
let KEY_LAST_TAB                        = "KEY_LAST_TAB"
let KEY_ACCOUNT_REFRESH_ALL             = "KEY_ACCOUNT_REFRESH_ALL"
let KEY_CURRENCY                        = "KEY_CURRENCY"
let KEY_USING_APP_LOCK                  = "KEY_USING_APP_LOCK"
let KEY_USING_BIO_AUTH                  = "KEY_USING_BIO_AUTH"
let KEY_FCM_TOKEN                       = "KEY_FCM_TOKEN"
let KEY_KAVA_TESTNET_WARN               = "KEY_KAVA_TESTNET_WARN"
let KEY_PRE_EVENT_HIDE                  = "KEY_PRE_EVENT_HIDE"

let STATION_URL                         = "https://api-utility.cosmostation.io/";
let CSS_URL                             = "https://api-wallet.cosmostation.io/";

let COSMOS_API                          = "https://api.cosmostation.io/";

let COSMOS_TEST_API                     = "https://api-office.cosmostation.io/stargate-final/";


let IRIS_API                            = "https://api-iris.cosmostation.io/";

let IRIS_TEST_API                       = "https://api-office.cosmostation.io/bifrost/";


let BNB_URL                             = "https://dex.binance.org/";
let BNB_TEST_URL                        = "https://testnet-dex.binance.org/";


let KAVA_URL                            = "https://lcd-kava-app.cosmostation.io/";
let KAVA_API                            = "https://api-kava.cosmostation.io/";

let KAVA_TEST_URL                       = "https://lcd-office.cosmostation.io/kava-testnet-12000/";
let KAVA_TEST_API                       = "https://api-office.cosmostation.io/kava-testnet-12000/";


let IOV_URL                             = "https://lcd-iov.cosmostation.io/";
let IOV_API                             = "https://api-iov.cosmostation.io/";

let IOV_TEST_URL                        = "https://iovnscli-rest-api.cluster-galaxynet.iov.one/";
let IOV_TEST_API                        = "";


let BAND_URL                            = "https://lcd-band.cosmostation.io/";
let BAND_API                            = "https://api-band.cosmostation.io/";


let OKEX_URL                            = "https://www.okex.com/okexchain/v1/";

let OKEX_TEST_URL                       = "https://www.okex.com/okexchain-test/v1/";


let CERTIK_URL                          = "https://lcd-certik.cosmostation.io/";
let CERTIK_API                          = "https://api-certik.cosmostation.io/";

let CERTIK_TEST_URL                     = "https://lcd-certik-testnet3.cosmostation.io/";
let CERTIK_TEST_API                     = "https://api-certik-testnet3.cosmostation.io/";


let SECRET_URL                          = "https://secret-2--lcd--full.datahub.figment.io/apikey/290520481df876316ee7664924c0a1df/";
let SECRET_API                          = "";


let AKASH_API                           = "https://api-akash.cosmostation.io/";


let PERSIS_API                          = "https://api-persistence.cosmostation.io/";


let SENTINEL_API                        = "https://api-sentinel.cosmostation.io/";


let FETCH_URL                           = "https://lcd-fetchai-app.cosmostation.io/";
let FETCH_API                           = "https://api-fetchai.cosmostation.io/";


let CRYTO_API                           = "https://api-cryptocom.cosmostation.io/";


let SIF_URL                             = "https://lcd-sifchain-app.cosmostation.io/";
let SIF_API                             = "https://api-sifchain.cosmostation.io/";


let KI_URL                              = "https://lcd-kichain-app.cosmostation.io/";
let KI_API                              = "https://api-kichain.cosmostation.io/";


let RIZON_TEST_API                      = "https://api-rizon-testnet.cosmostation.io/";


let MEDI_TEST_URL                       = "https://lcd-medibloc-opentestnet.cosmostation.io/";
let MEDI_TEST_API                       = "https://api-medibloc-opentestnet.cosmostation.io/";


let OSMOSIS_API                         = "https://api-osmosis.cosmostation.io/";


let HDAC_STATUS                         = "https://wallet.new.hdactech.com/insight-api/status";
let HDAC_GET_UTXO                       = "https://wallet.new.hdactech.com/insight-api/addr/";
let HDAC_BROADCAST                      = "https://wallet.new.hdactech.com/insight-api/tx/send";
let ALTHEA_TEST_API                     = "https://api-office.cosmostation.io/althea-testnet2v1/";

let MOON_PAY_URL                        = "https://buy.moonpay.io";
let MOON_PAY_PUBLICK                    = "pk_live_zbG1BOGMVTcfKibboIE2K3vduJBTuuCn";

let CSS_VERSION                         = CSS_URL + "v1/app/version/ios";
let CSS_PUSH_UPDATE                     = CSS_URL + "v1/account/update";
let CSS_MOON_PAY                        = CSS_URL + "v1/sign/moonpay";


let COSMOS_VAL_URL                      = "https://raw.githubusercontent.com/cosmostation/cosmostation_token_resource/master/moniker/cosmoshub/";
let IRIS_VAL_URL                        = "https://raw.githubusercontent.com/cosmostation/cosmostation_token_resource/master/moniker/irishub/";
let KAVA_VAL_URL                        = "https://raw.githubusercontent.com/cosmostation/cosmostation_token_resource/master/moniker/kava/kava-2/";
let BAND_VAL_URL                        = "https://raw.githubusercontent.com/cosmostation/cosmostation_token_resource/master/moniker/bandprotocol/";
let IOV_VAL_URL                         = "https://raw.githubusercontent.com/cosmostation/cosmostation_token_resource/master/moniker/iov/";
let CERTIK_VAL_URL                      = "https://raw.githubusercontent.com/cosmostation/cosmostation_token_resource/master/moniker/certik/";
let SECRET_VAL_URL                      = "https://raw.githubusercontent.com/cosmostation/cosmostation_token_resource/master/moniker/secret/";
let AKASH_VAL_URL                       = "https://raw.githubusercontent.com/cosmostation/cosmostation_token_resource/master/moniker/akash/";
let PERSIS_VAL_URL                      = "https://raw.githubusercontent.com/cosmostation/cosmostation_token_resource/master/moniker/persistence/";
let SENTINEL_VAL_URL                    = "https://raw.githubusercontent.com/cosmostation/cosmostation_token_resource/master/moniker/sentinel/";
let OKEX_VAL_URL                        = "https://raw.githubusercontent.com/cosmostation/cosmostation_token_resource/master/moniker/okex/";
let FETCH_VAL_URL                       = "https://raw.githubusercontent.com/cosmostation/cosmostation_token_resource/master/moniker/fetchai/";
let CRYPTO_VAL_URL                      = "https://raw.githubusercontent.com/cosmostation/cosmostation_token_resource/master/moniker/cryto/";
let SIF_VAL_URL                         = "https://raw.githubusercontent.com/cosmostation/cosmostation_token_resource/master/moniker/sif/";
let KI_VAL_URL                          = "https://raw.githubusercontent.com/cosmostation/cosmostation_token_resource/master/moniker/ki/";
let RIZON_VAL_URL                       = "https://raw.githubusercontent.com/cosmostation/cosmostation_token_resource/master/moniker/rizon/";
let MEDI_VAL_URL                        = "https://raw.githubusercontent.com/cosmostation/cosmostation_token_resource/master/moniker/medibloc/";
let ALTHEA_VAL_URL                      = "https://raw.githubusercontent.com/cosmostation/cosmostation_token_resource/master/moniker/althea/";
let OSMOSIS_VAL_URL                     = "https://raw.githubusercontent.com/cosmostation/cosmostation_token_resource/master/moniker/osmosis/";

let TOKEN_IMG_URL                       = "https://raw.githubusercontent.com/cosmostation/cosmostation_token_resource/master/thumnail/"
let KAVA_COIN_IMG_URL                   = "https://raw.githubusercontent.com/cosmostation/cosmostation_token_resource/master/kava/coin/";
let KAVA_CDP_IMG_URL                    = "https://raw.githubusercontent.com/cosmostation/cosmostation_token_resource/master/kava/cdp/";
let KAVA_HARD_POOL_IMG_URL              = "https://raw.githubusercontent.com/cosmostation/cosmostation_token_resource/master/kava/hard/";
let OKEX_COIN_IMG_URL                   = "https://raw.githubusercontent.com/cosmostation/cosmostation_token_resource/master/coin_image/okex/";
let SIF_COIN_IMG_URL                    = "https://raw.githubusercontent.com/cosmostation/cosmostation_token_resource/master/coin_image/sif/";


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
let DB_ACCOUNT_LAST_TOTAL           = Expression<String>("lastTotal")
let DB_ACCOUNT_SORT_ORDER           = Expression<Int64>("sortOrder")
let DB_ACCOUNT_PUSHALARM            = Expression<Bool>("pushAlarm")
let DB_ACCOUNT_NEW_BIP              = Expression<Bool>("newBip")                //using alternative ket gen path or type(OKex)


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
//Support BNB
let DB_BALANCE_FROZEN               = Expression<String?>("frozen")
let DB_BALANCE_LOCKED               = Expression<String?>("locked")


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





let COSMOS_AUTH_TYPE_DELAYEDACCOUNT         = "cosmos-sdk/DelayedVestingAccount";
let COSMOS_AUTH_TYPE_ACCOUNT                = "cosmos-sdk/Account";
let COSMOS_AUTH_TYPE_ACCOUNT_LEGACY         = "auth/Account";
let COSMOS_AUTH_TYPE_VESTING_ACCOUNT        = "cosmos-sdk/ValidatorVestingAccount";
let COSMOS_AUTH_TYPE_P_VESTING_ACCOUNT      = "cosmos-sdk/PeriodicVestingAccount";
let COSMOS_AUTH_TYPE_C_VESTING_ACCOUNT      = "cosmos-sdk/ContinuousVestingAccount";
let COSMOS_AUTH_TYPE_D_VESTING_ACCOUNT      = "cosmos-sdk/DelayedVestingAccount";
let COSMOS_AUTH_TYPE_CERTIK_MANUAL          = "auth/ManualVestingAccount";
let COSMOS_AUTH_TYPE_OKEX_ACCOUNT           = "okexchain/EthAccount";

let COSMOS_MSG_TYPE_TRANSFER                = "cosmos-sdk/Send";
let COSMOS_MSG_TYPE_TRANSFER2               = "cosmos-sdk/MsgSend";
let COSMOS_MSG_TYPE_TRANSFER3               = "cosmos-sdk/MsgMultiSend";
let COSMOS_MSG_TYPE_DELEGATE                = "cosmos-sdk/MsgDelegate";
let COSMOS_MSG_TYPE_UNDELEGATE              = "cosmos-sdk/Undelegate";
let COSMOS_MSG_TYPE_UNDELEGATE2             = "cosmos-sdk/MsgUndelegate";
let COSMOS_MSG_TYPE_REDELEGATE              = "cosmos-sdk/BeginRedelegate";
let COSMOS_MSG_TYPE_REDELEGATE2             = "cosmos-sdk/MsgBeginRedelegate";
let COSMOS_MSG_TYPE_WITHDRAW_DEL            = "cosmos-sdk/MsgWithdrawDelegationReward";
let COSMOS_MSG_TYPE_WITHDRAW_VAL            = "cosmos-sdk/MsgWithdrawValidatorCommission";
let COSMOS_MSG_TYPE_WITHDRAW_MIDIFY         = "cosmos-sdk/MsgModifyWithdrawAddress";
let COSMOS_MULTI_MSG_TYPE_REINVEST          = "cosmos-multi-ReInvest";

let COSMOS_MSG_TYPE_VOTE                    = "cosmos-sdk/MsgVote";
let COSMOS_MSG_TYPE_SUBMIT_PROPOSAL         = "cosmos-sdk/MsgSubmitProposal";
let COSMOS_MSG_TYPE_DEPOSIT                 = "cosmos-sdk/MsgDeposit";
let COSMOS_MSG_TYPE_CREATE_VALIDATOR        = "cosmos-sdk/MsgCreateValidator";
let COSMOS_MSG_TYPE_EDIT_VALIDATOR          = "cosmos-sdk/MsgEditValidator";

let COSMOS_KEY_TYPE_PUBLIC                  = "tendermint/PubKeySecp256k1";
let ETHERMINT_KEY_TYPE_PUBLIC               = "ethermint/PubKeyEthSecp256k1";
let COSMOS_AUTH_TYPE_STDTX                  = "auth/StdTx";


let IRIS_MSG_TYPE_TRANSFER                  = "irishub/bank/Send";
let IRIS_MSG_TYPE_DELEGATE                  = "irishub/stake/MsgDelegate";
let IRIS_MSG_TYPE_UNDELEGATE                = "irishub/stake/BeginUnbonding";
let IRIS_MSG_TYPE_REDELEGATE                = "irishub/stake/BeginRedelegate";
let IRIS_MSG_TYPE_WITHDRAW                  = "irishub/distr/MsgWithdrawDelegationReward";
let IRIS_MSG_TYPE_WITHDRAW_ALL              = "irishub/distr/MsgWithdrawDelegationRewardsAll";
let IRIS_MSG_TYPE_COMMISSION                = "irishub/distr/MsgWithdrawValidatorRewardsAll";
let IRIS_MSG_TYPE_VOTE                      = "irishub/gov/MsgVote";
let IRIS_MSG_TYPE_DEPOSIT                   = "irishub/gov/MsgDeposit";
let IRIS_MSG_TYPE_SUBMIT_PROPOSAL           = "irishub/gov/MsgSubmitProposal";
let IRIS_MSG_TYPE_CREATE_VALIDATOR          = "irishub/stake/MsgCreateValidator";
let IRIS_MSG_TYPE_WITHDRAW_MIDIFY           = "irishub/distr/MsgModifyWithdrawAddress";
let IRIS_MSG_TYPE_ISSUE_TOKEN               = "irishub/asset/MsgIssueToken";

let IRIS_PROPOAL_TYPE_BasicProposal             = "irishub/gov/BasicProposal";
let IRIS_PROPOAL_TYPE_ParameterProposal         = "irishub/gov/ParameterProposal";
let IRIS_PROPOAL_TYPE_PlainTextProposal         = "irishub/gov/PlainTextProposal";
let IRIS_PROPOAL_TYPE_TokenAdditionProposal     = "irishub/gov/TokenAdditionProposal";
let IRIS_PROPOAL_TYPE_SoftwareUpgradeProposal   = "irishub/gov/SoftwareUpgradeProposal";
let IRIS_PROPOAL_TYPE_SystemHaltProposal        = "irishub/gov/SystemHaltProposal";
let IRIS_PROPOAL_TYPE_CommunityTaxUsageProposal = "irishub/gov/CommunityTaxUsageProposal";


let BNB_MSG_TYPE_HTLC                       = "tokens/HTLTMsg";
let BNB_MSG_TYPE_HTLC_CLIAM                 = "tokens/ClaimHTLTMsg";
let BNB_MSG_TYPE_HTLC_REFUND                = "tokens/RefundHTLTMsg";

let TASK_TYPE_VOTE                          = "vote_task";
let TASK_TYPE_HTLC_SWAP                     = "htlc_swap";
let TASK_TYPE_HTLC_REFUND                   = "htlc_refund";

let KAVA_MSG_TYPE_POST_PRICE                = "pricefeed/MsgPostPrice";
let KAVA_MSG_TYPE_CREATE_CDP                = "cdp/MsgCreateCDP";
let KAVA_MSG_TYPE_DEPOSIT_CDP               = "cdp/MsgDeposit";
let KAVA_MSG_TYPE_WITHDRAW_CDP              = "cdp/MsgWithdraw";
let KAVA_MSG_TYPE_DRAWDEBT_CDP              = "cdp/MsgDrawDebt";
let KAVA_MSG_TYPE_REPAYDEBT_CDP             = "cdp/MsgRepayDebt";
let KAVA_MSG_TYPE_LIQUIDATE_CDP             = "cdp/MsgLiquidate";
let KAVA_MSG_TYPE_CREATE_SWAP               = "bep3/MsgCreateAtomicSwap";
let KAVA_MSG_TYPE_CLAIM_SWAP                = "bep3/MsgClaimAtomicSwap";
let KAVA_MSG_TYPE_REFUND_SWAP               = "bep3/MsgRefundAtomicSwap";
let KAVA_MSG_TYPE_INCENTIVE_REWARD          = "incentive/MsgClaimReward";
let KAVA_MSG_TYPE_DEPOSIT_HAVEST            = "harvest/MsgDeposit";
let KAVA_MSG_TYPE_WITHDRAW_HAVEST           = "harvest/MsgWithdraw";
let KAVA_MSG_TYPE_CLAIM_HAVEST              = "harvest/MsgClaimReward";
let KAVA_MSG_TYPE_USDX_MINT_INCENTIVE       = "incentive/MsgClaimUSDXMintingReward";
let KAVA_MSG_TYPE_CLAIM_HARD_INCENTIVE      = "incentive/MsgClaimHardReward";
let KAVA_MSG_TYPE_DEPOSIT_HARD              = "hard/MsgDeposit";
let KAVA_MSG_TYPE_WITHDRAW_HARD             = "hard/MsgWithdraw";
let KAVA_MSG_TYPE_BORROW_HARD               = "hard/MsgBorrow";
let KAVA_MSG_TYPE_REPAY_HARD                = "hard/MsgRepay";
let KAVA_MSG_TYPE_LIQUIDATE_HARD            = "hard/MsgLiquidate";


let CERTIK_MSG_TYPE_TRANSFER                = "bank/MsgSend";

let OK_MSG_TYPE_TRANSFER                    = "okexchain/token/MsgTransfer";
let OK_MSG_TYPE_MULTI_TRANSFER              = "okexchain/token/MsgMultiTransfer";
let OK_MSG_TYPE_DEPOSIT                     = "okexchain/staking/MsgDeposit";
let OK_MSG_TYPE_WITHDRAW                    = "okexchain/staking/MsgWithdraw";
let OK_MSG_TYPE_DIRECT_VOTE                 = "okexchain/staking/MsgAddShares";


let IOV_MSG_TYPE_REGISTER_DOMAIN            = "starname/RegisterDomain";
let IOV_MSG_TYPE_REGISTER_ACCOUNT           = "starname/RegisterAccount";
let IOV_MSG_TYPE_DELETE_DOMAIN              = "starname/DeleteDomain";
let IOV_MSG_TYPE_DELETE_ACCOUNT             = "starname/DeleteAccount";
let IOV_MSG_TYPE_REPLACE_ACCOUNT_RESOURCE   = "starname/ReplaceAccountResources";
let IOV_MSG_TYPE_RENEW_DOMAIN               = "starname/RenewDomain";
let IOV_MSG_TYPE_RENEW_ACCOUNT              = "starname/RenewAccount";


let PASSWORD_ACTION_INIT                    = "ACTION_INIT"
let PASSWORD_ACTION_SIMPLE_CHECK            = "ACTION_SIMPLE_CHECK"
let PASSWORD_ACTION_DELETE_ACCOUNT          = "ACTION_DELETE_ACCOUNT"
let PASSWORD_ACTION_CHECK_TX                = "ACTION_CHECK_TX"
let PASSWORD_ACTION_APP_LOCK                = "ACTION_APP_LOCK"
let PASSWORD_ACTION_INTRO_LOCK              = "ACTION_INTRO_LOCK"



let PASSWORD_RESUKT_OK                      = 0
let PASSWORD_RESUKT_CANCEL                  = 1
let PASSWORD_RESUKT_FAIL                    = 2
let PASSWORD_RESUKT_OK_FOR_DELETE           = 3


let BASE_PATH                               = "m/44'/118'/0'/0/"
let BNB_BASE_PATH                           = "m/44'/714'/0'/0/"
let KAVA_BASE_PATH                          = "m/44'/459'/0'/0/"
let IOV_BASE_PATH                           = "m/44'/234'/0'/0/"
let BAND_BASE_PATH                          = "m/44'/494'/0'/0/"
let SECRET_BASE_PATH                        = "m/44'/529'/0'/0/"
let OK_BASE_PATH                            = "m/44'/996'/0'/0/"
let PERSIS_BASE_PATH                        = "m/44'/750'/0'/0/"
let CRYPTO_BASE_PATH                        = "m/44'/394'/0'/0/"
let RIZON_BASE_PATH                         = "m/44'/1217'/0'/0/"
let MEDI_BASE_PATH                          = "m/44'/371'/0'/0/"
let ALTHEA_BASE_PATH                        = "m/44'/60'/0'/0/"



let FEE_REWARD_GAS_1                        = "150000";
let FEE_REWARD_GAS_2                        = "220000";
let FEE_REWARD_GAS_3                        = "280000";
let FEE_REWARD_GAS_4                        = "320000";
let FEE_REWARD_GAS_5                        = "380000";
let FEE_REWARD_GAS_6                        = "440000";
let FEE_REWARD_GAS_7                        = "500000";
let FEE_REWARD_GAS_8                        = "560000";
let FEE_REWARD_GAS_9                        = "620000";
let FEE_REWARD_GAS_10                       = "680000";
let FEE_REWARD_GAS_11                       = "740000";
let FEE_REWARD_GAS_12                       = "820000";
let FEE_REWARD_GAS_13                       = "900000";
let FEE_REWARD_GAS_14                       = "980000";
let FEE_REWARD_GAS_15                       = "1020000";
let FEE_REWARD_GAS_16                       = "1080000";

let FEE_KAVA_REWARD_GAS_1                   = "200000";
let FEE_KAVA_REWARD_GAS_2                   = "280000";
let FEE_KAVA_REWARD_GAS_3                   = "360000";
let FEE_KAVA_REWARD_GAS_4                   = "440000";
let FEE_KAVA_REWARD_GAS_5                   = "520000";
let FEE_KAVA_REWARD_GAS_6                   = "600000";
let FEE_KAVA_REWARD_GAS_7                   = "700000";
let FEE_KAVA_REWARD_GAS_8                   = "780000";
let FEE_KAVA_REWARD_GAS_9                   = "860000";
let FEE_KAVA_REWARD_GAS_10                  = "940000";
let FEE_KAVA_REWARD_GAS_11                  = "1020000";
let FEE_KAVA_REWARD_GAS_12                  = "1100000";
let FEE_KAVA_REWARD_GAS_13                  = "1200000";
let FEE_KAVA_REWARD_GAS_14                  = "1280000";
let FEE_KAVA_REWARD_GAS_15                  = "1360000";
let FEE_KAVA_REWARD_GAS_16                  = "1440000";


let GAS_FEE_RATE_TINY                       = "0.00025"
let GAS_FEE_RATE_LOW                        = "0.0025"
let GAS_FEE_RATE_AVERAGE                    = "0.025"

let GAS_FEE_RATE_TINY_IRIS                  = "0.002"
let GAS_FEE_RATE_LOW_IRIS                   = "0.02"
let GAS_FEE_RATE_AVERAGE_IRIS               = "0.2"

let GAS_FEE_RATE_TINY_PERSIS                = "0.000"
let GAS_FEE_RATE_LOW_PERSIS                 = "0.000"
let GAS_FEE_RATE_AVERAGE_PERSIS             = "0.000"

let GAS_FEE_RATE_TINY_CRYPTO                = "0.025"
let GAS_FEE_RATE_LOW_CRYPTO                 = "0.05"
let GAS_FEE_RATE_AVERAGE_CRYPTO             = "0.075"

let GAS_FEE_RATE_TINY_SENTINEL              = "0.01"
let GAS_FEE_RATE_LOW_SENTINEL               = "0.1"
let GAS_FEE_RATE_AVERAGE_SENTINEL           = "0.1"

let GAS_FEE_AMOUNT_LOW                      = "100000"
let GAS_FEE_AMOUNT_MID                      = "200000"
let GAS_FEE_AMOUNT_HIGH                     = "300000"
let GAS_FEE_AMOUNT_REINVEST                 = "220000"
let GAS_FEE_AMOUNT_REDELE                   = "240000"

let FEE_BNB_TRANSFER                            = "0.000075"

let KAVA_GAS_RATE_LOW                           = "0.0025";
let KAVA_GAS_RATE_AVERAGE                       = "0.025";
let KAVA_GAS_AMOUNT_SEND                        = "300000";
let KAVA_GAS_AMOUNT_STAKE                       = "800000";
let KAVA_GAS_AMOUNT_REINVEST                    = "800000";
let KAVA_GAS_AMOUNT_REDELEGATE                  = "800000";
let KAVA_GAS_AMOUNT_VOTE                        = "300000";
let KAVA_GAS_AMOUNT_CLAIM_INCENTIVE             = "800000";
let KAVA_GAS_AMOUNT_CDP                         = "2000000";
let KAVA_GAS_AMOUNT_HARD_POOL                   = "800000";
let KAVA_GAS_AMOUNT_BEP3                        = "500000";

let BAND_GAS_RATE_LOW                           = "0.0025";
let BAND_GAS_RATE_AVERAGE                       = "0.025";
let BAND_GAS_AMOUNT_SEND                        = "100000";
let BAND_GAS_AMOUNT_STAKE                       = "200000";
let BAND_GAS_AMOUNT_REDELEGATE                  = "240000";
let BAND_GAS_AMOUNT_REINVEST                    = "220000";
let BAND_GAS_AMOUNT_ADDRESS_CHANGE              = "100000";
let BAND_GAS_AMOUNT_VOTE                        = "100000";

let IOV_GAS_RATE_AVERAGE                        = "1.00";
let IOV_GAS_AMOUNT_SEND                         = "100000";
let IOV_GAS_AMOUNT_STAKE                        = "200000";
let IOV_GAS_AMOUNT_REDELEGATE                   = "300000";
let IOV_GAS_AMOUNT_REINVEST                     = "300000";
let IOV_GAS_AMOUNT_ADDRESS_CHANGE               = "100000";
let IOV_GAS_AMOUNT_VOTE                         = "100000";
let IOV_GAS_AMOUNT_REGISTER                     = "300000";
let IOV_GAS_AMOUNT_DELETE                       = "150000";
let IOV_GAS_AMOUNT_RENEW                        = "300000";
let IOV_GAS_AMOUNT_REPLACE                      = "300000";

let OK_GAS_RATE_AVERAGE                         = "0.000000001";
let OK_GAS_AMOUNT_SEND                          = "200000";
let OK_GAS_AMOUNT_STAKE                         = "200000";
let OK_GAS_AMOUNT_STAKE_MUX                     = "20000";
let OK_GAS_AMOUNT_VOTE                          = "200000";
let OK_GAS_AMOUNT_VOTE_MUX                      = "50000";

let CERTIK_GAS_RATE_AVERAGE                     = "0.05";
let CERTIK_GAS_AMOUNT_SEND                      = "100000";
let CERTIK_GAS_AMOUNT_STAKE                     = "200000";
let CERTIK_GAS_AMOUNT_REDELEGATE                = "300000";
let CERTIK_GAS_AMOUNT_REINVEST                  = "300000";
let CERTIK_GAS_AMOUNT_REWARD_ADDRESS_CHANGE     = "100000";
let CERTIK_GAS_AMOUNT_VOTE                      = "100000";

let SECRET_GAS_FEE_RATE_AVERAGE                 = "0.25";
let SECRET_GAS_AMOUNT_SEND                      = "80000";
let SECRET_GAS_AMOUNT_STAKE                     = "200000";
let SECRET_GAS_AMOUNT_REDELEGATE                = "300000";
let SECRET_GAS_AMOUNT_REINVEST                  = "350000";
let SECRET_GAS_AMOUNT_REWARD_ADDRESS_CHANGE     = "80000";
let SECRET_GAS_AMOUNT_VOTE                      = "100000";

let SENTINEL_GAS_AMOUNT_SEND                    = "100000";
let SENTINEL_GAS_AMOUNT_STAKE                   = "200000";
let SENTINEL_GAS_AMOUNT_REDELEGATE              = "300000";
let SENTINEL_GAS_AMOUNT_REINVEST                = "350000";
let SENTINEL_GAS_AMOUNT_REWARD_ADDRESS_CHANGE   = "100000";
let SENTINEL_GAS_AMOUNT_VOTE                    = "100000";

let FETCH_GAS_FEE_RATE_AVERAGE                  = "0.00";
let FETCH_GAS_AMOUNT_SEND                       = "100000";
let FETCH_GAS_AMOUNT_STAKE                      = "200000";
let FETCH_GAS_AMOUNT_REDELEGATE                 = "300000";
let FETCH_GAS_AMOUNT_REINVEST                   = "350000";
let FETCH_GAS_AMOUNT_REWARD_ADDRESS_CHANGE      = "100000";
let FETCH_GAS_AMOUNT_VOTE                       = "100000";

let SIF_GAS_FEE_RATE_AVERAGE                    = "0.50";
let SIF_GAS_AMOUNT_SEND                         = "100000";
let SIF_GAS_AMOUNT_STAKE                        = "200000";
let SIF_GAS_AMOUNT_REDELEGATE                   = "300000";
let SIF_GAS_AMOUNT_REINVEST                     = "350000";
let SIF_GAS_AMOUNT_REWARD_ADDRESS_CHANGE        = "100000";
let SIF_GAS_AMOUNT_VOTE                         = "100000";

let KI_GAS_FEE_RATE_AVERAGE                     = "0.025";
let KI_GAS_AMOUNT_SEND                          = "100000";
let KI_GAS_AMOUNT_STAKE                         = "200000";
let KI_GAS_AMOUNT_REDELEGATE                    = "300000";
let KI_GAS_AMOUNT_REINVEST                      = "350000";
let KI_GAS_AMOUNT_REWARD_ADDRESS_CHANGE         = "100000";
let KI_GAS_AMOUNT_VOTE                          = "100000";

let MEDI_GAS_FEE_RATE_AVERAGE                   = "0.025";
let MEDI_GAS_AMOUNT_SEND                        = "100000";
let MEDI_GAS_AMOUNT_STAKE                       = "200000";
let MEDI_GAS_AMOUNT_REDELEGATE                  = "300000";
let MEDI_GAS_AMOUNT_REINVEST                    = "350000";
let MEDI_GAS_AMOUNT_REWARD_ADDRESS_CHANGE       = "100000";
let MEDI_GAS_AMOUNT_VOTE                        = "100000";





// Constant for BEP3-Swap
let KAVA_MAIN_BNB_DEPUTY                    = "kava1r4v2zdhdalfj2ydazallqvrus9fkphmglhn6u6"
let KAVA_MAIN_BTCB_DEPUTY                   = "kava14qsmvzprqvhwmgql9fr0u3zv9n2qla8zhnm5pc"
let KAVA_MAIN_XRPB_DEPUTY                   = "kava1c0ju5vnwgpgxnrktfnkccuth9xqc68dcdpzpas"
let KAVA_MAIN_BUSD_DEPUTY                   = "kava1hh4x3a4suu5zyaeauvmv7ypf7w9llwlfufjmuu"

let BINANCE_MAIN_BNB_DEPUTY                 = "bnb1jh7uv2rm6339yue8k4mj9406k3509kr4wt5nxn"
let BINANCE_MAIN_BTCB_DEPUTY                = "bnb1xz3xqf4p2ygrw9lhp5g5df4ep4nd20vsywnmpr"
let BINANCE_MAIN_XRPB_DEPUTY                = "bnb15jzuvvg2kf0fka3fl2c8rx0kc3g6wkmvsqhgnh"
let BINANCE_MAIN_BUSD_DEPUTY                = "bnb10zq89008gmedc6rrwzdfukjk94swynd7dl97w8"


//For 9000
let BINANCE_TEST_BNB_DEPUTY                 = "tbnb10uypsspvl6jlxcx5xse02pag39l8xpe7a3468h"
let KAVA_TEST_BNB_DEPUTY                    = "kava1tfvn5t8qwngqd2q427za2mel48pcus3z9u73fl"
let BINANCE_TEST_BTC_DEPUTY                 = "tbnb1dmn2xgnc8kcxn4s0ts5llu9ry3ulp2nlhuh5fz"
let KAVA_TEST_BTC_DEPUTY                    = "kava1kla4wl0ccv7u85cemvs3y987hqk0afcv7vue84"

let TOKEN_HTLC_BINANCE_BNB                  = "BNB"
let TOKEN_HTLC_KAVA_BNB                     = "bnb"
let TOKEN_HTLC_BINANCE_BTCB                 = "BTCB-1DE"
let TOKEN_HTLC_KAVA_BTCB                    = "btcb"
let TOKEN_HTLC_BINANCE_XRPB                 = "XRP-BF2"
let TOKEN_HTLC_KAVA_XRPB                    = "xrpb"
let TOKEN_HTLC_BINANCE_BUSD                 = "BUSD-BD1"
let TOKEN_HTLC_KAVA_BUSD                    = "busd"



let TOKEN_HTLC_BINANCE_TEST_BNB             = "BNB"
let TOKEN_HTLC_BINANCE_TEST_BTC             = "BTCB-101"
let TOKEN_HTLC_KAVA_TEST_BNB                = "bnb"
let TOKEN_HTLC_KAVA_TEST_BTC                = "btcb"



let SWAP_MEMO_CREATE                        = "Create Atomic Swap via Cosmostation iOS Wallet"
let SWAP_MEMO_CLAIM                         = "Claim Atomic Swap via Cosmostation iOS Wallet"



let COLOR_BG_GRAY                           = UIColor.init(hexString: "2E2E2E", alpha: 0.4)
let COLOR_DARK_GRAY                         = UIColor.init(hexString: "36393C")

let TRANS_BG_COLOR_COSMOS                   = UIColor.init(hexString: "9C6CFF", alpha: 0.15)
let TRANS_BG_COLOR_COSMOS2                  = UIColor.init(hexString: "9C6CFF", alpha: 0.4)
let COLOR_ATOM                              = UIColor.init(hexString: "9C6CFF")
let COLOR_ATOM_DARK                         = UIColor.init(hexString: "372758")
let COLOR_PHOTON                            = UIColor.init(hexString: "05D2DD")

let TRANS_BG_COLOR_IRIS                     = UIColor.init(hexString: "0080ff", alpha: 0.15)
let TRANS_BG_COLOR_IRIS2                    = UIColor.init(hexString: "0080ff", alpha: 0.4)
let COLOR_IRIS                              = UIColor.init(hexString: "00A8FF")
let COLOR_IRIS_DARK                         = UIColor.init(hexString: "003870")

let TRANS_BG_COLOR_BNB                      = UIColor.init(hexString: "f0b90b", alpha: 0.15)
let COLOR_BNB                               = UIColor.init(hexString: "E9BC00")
let COLOR_BNB_DARK                          = UIColor.init(hexString: "634C04")

let TRANS_BG_COLOR_KAVA                     = UIColor.init(hexString: "ff554f", alpha: 0.15)
let TRANS_BG_COLOR_KAVA2                    = UIColor.init(hexString: "ff554f", alpha: 0.4)
let COLOR_KAVA                              = UIColor.init(hexString: "FF564F")
let COLOR_KAVA_DARK                         = UIColor.init(hexString: "631D1B")
let COLOR_BG_COLOR_HARD                     = UIColor.init(hexString: "8626E1", alpha: 0.15)
let COLOR_HARD                              = UIColor.init(hexString: "8626E1")

let TRANS_BG_COLOR_IOV                      = UIColor.init(hexString: "6e7cde", alpha: 0.15)
let TRANS_BG_COLOR_IOV2                     = UIColor.init(hexString: "6e7cde", alpha: 0.4)
let COLOR_IOV                               = UIColor.init(hexString: "6e7cde")
let COLOR_IOV_DARK                          = UIColor.init(hexString: "2c367e")

let TRANS_BG_COLOR_BAND                     = UIColor.init(hexString: "5286FF", alpha: 0.15)
let TRANS_BG_COLOR_BAND2                    = UIColor.init(hexString: "5286FF", alpha: 0.4)
let COLOR_BAND                              = UIColor.init(hexString: "516FFA")
let COLOR_BAND_DARK                         = UIColor.init(hexString: "2A3C8B")

let TRANS_BG_COLOR_OK                       = UIColor.init(hexString: "88bdf3", alpha: 0.15)
let TRANS_BG_COLOR_OK2                      = UIColor.init(hexString: "88bdf3", alpha: 0.4)
let COLOR_OK                                = UIColor.init(hexString: "88bdf3")
let COLOR_OK_DARK                           = UIColor.init(hexString: "45678b")

let TRANS_BG_COLOR_CERTIK                   = UIColor.init(hexString: "E1AA4C", alpha: 0.15)
let TRANS_BG_COLOR_CERTIK2                  = UIColor.init(hexString: "E1AA4C", alpha: 0.4)
let COLOR_CERTIK                            = UIColor.init(hexString: "E1AA4C")
let COLOR_CERTIK_DARK                       = UIColor.init(hexString: "59441E")

let TRANS_BG_COLOR_SECRET                   = UIColor.init(hexString: "C4C4C4", alpha: 0.15)
let TRANS_BG_COLOR_SECRET2                  = UIColor.init(hexString: "C4C4C4", alpha: 0.4)
let COLOR_SECRET                            = UIColor.init(hexString: "C4C4C4")
let COLOR_SECRET_DARK                       = UIColor.init(hexString: "585858")

let TRANS_BG_COLOR_AKASH                    = UIColor.init(hexString: "c71e10", alpha: 0.15)
let TRANS_BG_COLOR_AKASH2                   = UIColor.init(hexString: "c71e10", alpha: 0.4)
let COLOR_AKASH                             = UIColor.init(hexString: "c71e10")
let COLOR_AKASH_DARK                        = UIColor.init(hexString: "631008")

let TRANS_BG_COLOR_PERSIS                   = UIColor.init(hexString: "ededed", alpha: 0.15)
let TRANS_BG_COLOR_PERSIS2                  = UIColor.init(hexString: "ededed", alpha: 0.4)
let COLOR_PERSIS                            = UIColor.init(hexString: "e50a13")
let COLOR_PERSIS_DARK                       = UIColor.init(hexString: "670000")

let TRANS_BG_COLOR_SENTINEL                 = UIColor.init(hexString: "5b8ed7", alpha: 0.15)
let TRANS_BG_COLOR_SENTINEL2                = UIColor.init(hexString: "5b8ed7", alpha: 0.4)
let COLOR_SENTINEL                          = UIColor.init(hexString: "5b8ed7")
let COLOR_SENTINEL_DARK                     = UIColor.init(hexString: "1e447b")
let COLOR_SENTINEL_DARK2                    = UIColor.init(hexString: "142d51")

let TRANS_BG_COLOR_FETCH                    = UIColor.init(hexString: "57b5a8", alpha: 0.15)
let TRANS_BG_COLOR_FETCH2                   = UIColor.init(hexString: "57b5a8", alpha: 0.4)
let COLOR_FETCH                             = UIColor.init(hexString: "57b5a8")
let COLOR_FETCH_DARK                        = UIColor.init(hexString: "265750")
let COLOR_FETCH_DARK2                       = UIColor.init(hexString: "1d284c")

let TRANS_BG_COLOR_CRYPTO                   = UIColor.init(hexString: "1199fa", alpha: 0.15)
let TRANS_BG_COLOR_CRYPTO2                  = UIColor.init(hexString: "1199fa", alpha: 0.4)
let COLOR_CRYPTO                            = UIColor.init(hexString: "1199fa")
let COLOR_CRYPTO_DARK                       = UIColor.init(hexString: "032d74")

let TRANS_BG_COLOR_SIF                      = UIColor.init(hexString: "c19f33", alpha: 0.15)
let TRANS_BG_COLOR_SIF2                     = UIColor.init(hexString: "c19f33", alpha: 0.4)
let COLOR_SIF                               = UIColor.init(hexString: "c19f33")
let COLOR_SIF_DARK                          = UIColor.init(hexString: "5e4d19")

let TRANS_BG_COLOR_KI                       = UIColor.init(hexString: "3756fc", alpha: 0.15)
let TRANS_BG_COLOR_KI2                      = UIColor.init(hexString: "3756fc", alpha: 0.4)
let COLOR_KI                                = UIColor.init(hexString: "3756fc")
let COLOR_KI_DARK                           = UIColor.init(hexString: "02188d")

let TRANS_BG_COLOR_RIZON                    = UIColor.init(hexString: "8c8af2", alpha: 0.15)
let TRANS_BG_COLOR_RIZON2                   = UIColor.init(hexString: "8c8af2", alpha: 0.4)
let COLOR_RIZON                             = UIColor.init(hexString: "8c8af2")
let COLOR_RIZON_DARK                        = UIColor.init(hexString: "484773")

let TRANS_BG_COLOR_MEDI                     = UIColor.init(hexString: "79aaf2", alpha: 0.15)
let TRANS_BG_COLOR_MEDI2                    = UIColor.init(hexString: "79aaf2", alpha: 0.4)
let COLOR_MEDI                              = UIColor.init(hexString: "79aaf2")
let COLOR_MEDI_DARK                         = UIColor.init(hexString: "0d3d84")

let TRANS_BG_COLOR_ALTHEA                   = UIColor.init(hexString: "eadea5", alpha: 0.15)
let TRANS_BG_COLOR_ALTHEA2                  = UIColor.init(hexString: "eadea5", alpha: 0.4)
let COLOR_ALTHEA                            = UIColor.init(hexString: "eadea5")
let COLOR_ALTHEA_DARK                       = UIColor.init(hexString: "7d7659")

let TRANS_BG_COLOR_OSMOSIS                  = UIColor.init(hexString: "9248db", alpha: 0.15)
let TRANS_BG_COLOR_OSMOSIS2                 = UIColor.init(hexString: "9248db", alpha: 0.4)
let COLOR_OSMOSIS                           = UIColor.init(hexString: "9248db")
let COLOR_OSMOSIS_DARK                      = UIColor.init(hexString: "4e1a82")
let COLOR_ION                               = UIColor.init(hexString: "3C90FC")


let COLOR_CDP_DANGER                        = UIColor.init(hexString: "FF2745")
let COLOR_CDP_STABLE                        = UIColor.init(hexString: "FFE62B")
let COLOR_CDP_SAFE                          = UIColor.init(hexString: "40F683")

let COLOR_WC_TRADE_BUY                      = UIColor.init(hexString: "37CC6E")
let COLOR_WC_TRADE_SELL                     = UIColor.init(hexString: "FF2745")

let COLOR_STAKE_DROP                        = UIColor.init(hexString: "E1AA4C")
let COLOR_STAKE_DROP_BG                     = UIColor.init(hexString: "E1AA4C", alpha: 0.15)


enum ChainType: String {
    case COSMOS_MAIN
    case IRIS_MAIN
    case BINANCE_MAIN
    case KAVA_MAIN
    case IOV_MAIN
    case BAND_MAIN
    case SECRET_MAIN
    case CERTIK_MAIN
    case AKASH_MAIN
    case OKEX_MAIN
    case PERSIS_MAIN
    case SENTINEL_MAIN
    case FETCH_MAIN
    case CRYPTO_MAIN
    case SIF_MAIN
    case KI_MAIN
    case OSMOSIS_MAIN
    
    case COSMOS_TEST
    case IRIS_TEST
    case BINANCE_TEST
    case KAVA_TEST
    case IOV_TEST
    case OKEX_TEST
    case CERTIK_TEST
    case RIZON_TEST
    case MEDI_TEST
    case ALTHEA_TEST
    
    static func SUPPRT_CHAIN() -> Array<ChainType> {
        var result = [ChainType]()
        result.append(COSMOS_MAIN)
        result.append(IRIS_MAIN)
        result.append(BINANCE_MAIN)
        result.append(OKEX_MAIN)
        result.append(KAVA_MAIN)
        result.append(BAND_MAIN)
        result.append(PERSIS_MAIN)
        result.append(IOV_MAIN)
        result.append(CERTIK_MAIN)
        result.append(AKASH_MAIN)
        result.append(SENTINEL_MAIN)
        result.append(FETCH_MAIN)
        result.append(CRYPTO_MAIN)
        result.append(SIF_MAIN)
        result.append(KI_MAIN)
        result.append(OSMOSIS_MAIN)
        result.append(SECRET_MAIN)

//        result.append(COSMOS_TEST)
//        result.append(IRIS_TEST)
//        result.append(BINANCE_TEST)
//        result.append(KAVA_TEST)
//        result.append(IOV_TEST)
//        result.append(OKEX_TEST)
//        result.append(CERTIK_TEST)
        result.append(RIZON_TEST)
        result.append(MEDI_TEST)
        result.append(ALTHEA_TEST)
        return result
    }
    
    static func IS_SUPPORT_CHAIN(_ chainS: String) -> Bool {
        if let chainS = WUtils.getChainType(chainS) {
            return SUPPRT_CHAIN().contains(chainS)
        }
        return false
    }
    
    static func getHtlcSendable(_ chain: ChainType) -> Array<ChainType> {
        var result = Array<ChainType>()
        if (chain == BINANCE_TEST) {
            result.append(KAVA_TEST)
            
        } else if (chain == KAVA_TEST) {
            result.append(BINANCE_TEST)
            
        } else if (chain == BINANCE_MAIN) {
            result.append(KAVA_MAIN)
            
        } else if (chain == KAVA_MAIN) {
            result.append(BINANCE_MAIN)
            
        }
        return result
    }
    
    static func getHtlcSwappableCoin(_ chain: ChainType) -> Array<String> {
        var result = Array<String>()
        if (chain == BINANCE_MAIN) {
            result.append(TOKEN_HTLC_BINANCE_BNB)
            result.append(TOKEN_HTLC_BINANCE_BTCB)
            result.append(TOKEN_HTLC_BINANCE_XRPB)
            result.append(TOKEN_HTLC_BINANCE_BUSD)
            
        } else if (chain == KAVA_MAIN) {
            result.append(TOKEN_HTLC_KAVA_BNB)
            result.append(TOKEN_HTLC_KAVA_BTCB)
            result.append(TOKEN_HTLC_KAVA_XRPB)
            result.append(TOKEN_HTLC_KAVA_BUSD)
            
        } else if (chain == BINANCE_TEST) {
            result.append(TOKEN_HTLC_BINANCE_TEST_BNB)
            result.append(TOKEN_HTLC_BINANCE_TEST_BTC)
            
        } else if (chain == KAVA_TEST) {
            result.append(TOKEN_HTLC_KAVA_TEST_BNB)
            result.append(TOKEN_HTLC_KAVA_TEST_BTC)
            
        }
        return result
    }
    
    static func isHtlcSwappableCoin(_ chain: ChainType?, _ denom: String?) -> Bool {
        if (chain == BINANCE_MAIN) {
            if (denom == TOKEN_HTLC_BINANCE_BNB) { return true }
            if (denom == TOKEN_HTLC_BINANCE_BTCB) { return true }
            if (denom == TOKEN_HTLC_BINANCE_XRPB) { return true }
            if (denom == TOKEN_HTLC_BINANCE_BUSD) { return true }
        } else if (chain == KAVA_MAIN) {
            if (denom == TOKEN_HTLC_KAVA_BNB) { return true }
            if (denom == TOKEN_HTLC_KAVA_BTCB) { return true }
            if (denom == TOKEN_HTLC_KAVA_XRPB) { return true }
            if (denom == TOKEN_HTLC_KAVA_BUSD) { return true }
        } else if (chain == BINANCE_TEST) {
            if (denom == TOKEN_HTLC_BINANCE_TEST_BNB) { return true }
            if (denom == TOKEN_HTLC_BINANCE_TEST_BTC) { return true }
        } else if (chain == KAVA_TEST) {
            if (denom == TOKEN_HTLC_KAVA_TEST_BNB) { return true }
            if (denom == TOKEN_HTLC_KAVA_TEST_BTC) { return true }
        }
        return false
    }
}

let CHAIN_COSMOS_S = "SUPPORT_CHAIN_COSMOS_MAIN"
let CHAIN_IRIS_S = "SUPPORT_CHAIN_IRIS_MAIN"
let CHAIN_BINANCE_S = "SUPPORT_CHAIN_BINANCE_MAIN"
let CHAIN_KAVA_S = "SUPPORT_CHAIN_KAVA_MAIN"
let CHAIN_IOV_S = "SUPPORT_CHAIN_IOV_MAIN"
let CHAIN_BAND_S = "SUPPORT_CHAIN_BAND_MAIN"
let CHAIN_SECRET_S = "SUPPORT_CHAIN_SECRET_MAIN"
let CHAIN_CERTIK_S = "SUPPORT_CHAIN_CERTIK_MAIN"
let CHAIN_AKASH_S = "SUPPORT_CHAIN_AKASH_MAIN"
let CHAIN_OKEX_S = "SUPPORT_CHAIN_OKEX_MAIN"
let CHAIN_PERSIS_S = "SUPPORT_CHAIN_PERSISTENCE_MAIN"
let CHAIN_SENTINEL_S = "SUPPORT_CHAIN_SENTINEL_MAIN"
let CHAIN_FETCH_S = "SUPPORT_CHAIN_FETCH_MAIN"
let CHAIN_CRYPTO_S = "SUPPORT_CHAIN_CRYTO_MAIN"
let CHAIN_SIF_S = "SUPPORT_CHAIN_SIF_MAIN"
let CHAIN_KI_S = "SUPPORT_CHAIN_KI_MAIN"
let CHAIN_OSMOSIS_S = "SUPPORT_CHAIN_OSMOSIS_MAIN"

let CHAIN_COSMOS_TEST_S = "SUPPORT_CHAIN_COSMOS_TEST"
let CHAIN_IRIS_TEST_S = "SUPPORT_CHAIN_IRIS_TEST"
let CHAIN_BINANCE_TEST_S = "SUPPORT_CHAIN_BINANCE_TEST"
let CHAIN_KAVA_TEST_S = "SUPPORT_CHAIN_KAVA_TEST"
let CHAIN_IOV_TEST_S = "SUPPORT_CHAIN_IOV_TEST"
let CHAIN_OKEX_TEST_S = "SUPPORT_CHAIN_OKEX_TEST"
let CHAIN_CERTIK_TEST_S = "SUPPORT_CHAIN_CERTIK_TEST"
let CHAIN_RIZON_TEST_S = "SUPPORT_CHAIN_RIZON_TEST"
let CHAIN_MEDI_TEST_S = "SUPPORT_CHAIN_MEDI_TEST"
let CHAIN_ALTHEA_TEST_S = "SUPPORT_CHAIN_ALTHEA_TEST"

let COSMOS_MAIN_DENOM = "uatom"
let IRIS_MAIN_DENOM = "uiris"
let BNB_MAIN_DENOM = "BNB"
let IOV_MAIN_DENOM = "uiov"
let KAVA_MAIN_DENOM = "ukava"
let BAND_MAIN_DENOM = "uband"
let SECRET_MAIN_DENOM = "uscrt"
let CERTIK_MAIN_DENOM = "uctk"
let AKASH_MAIN_DENOM = "uakt"
let OKEX_MAIN_DENOM = "okt"
let OKEX_MAIN_OKB = "okb"
let PERSIS_MAIN_DENOM = "uxprt"
let SENTINEL_MAIN_DENOM = "udvpn"
let FETCH_MAIN_DENOM = "afet"
let CRYPTO_MAIN_DENOM = "basecro"
let SIF_MAIN_DENOM = "rowan"
let KI_MAIN_DENOM = "uxki"
let RIZON_MAIN_DENOM = "uatolo"
let MEDI_MAIN_DENOM = "umed"
let ALTHEA_MAIN_DENOM = "ualtg"
let OSMOSIS_MAIN_DENOM = "uosmo"

let COSMOS_TEST_DENOM = "umuon"
let IRIS_TEST_DENOM = "ubif"
let IOV_TEST_DENOM = "uvoi"
let KAVA_HARD_DENOM = "hard"
let OSMOSIS_ION_DENOM = "uion"


let BITCOINCASH    = "asset:bch";
let BITCOIN        = "asset:btc";
let LITECOIN       = "asset:ltc";
let BINANCE        = "asset:bnb";
let LUNA           = "asset:luna";
let COSMOS         = "asset:atom";
let EMONEY         = "asset:ngm";
let IRIS           = "asset:iris";
let KAVA           = "asset:kava";
let ETHEREUM       = "asset:eth";
let STARNAME       = "asset:iov";
let BAND           = "asset:band";
let TEZOS          = "asset:xtz";
let LISK           = "asset:lsk";


let Font_17_body = UIFont(name: "Helvetica-Light", size: 17)!
let Font_15_subTitle = UIFont(name: "Helvetica-Light", size: 15)!
let Font_13_footnote = UIFont(name: "Helvetica-Light", size: 13)!
let Font_12_caption1 = UIFont(name: "Helvetica-Light", size: 12)!
let Font_11_caption2 = UIFont(name: "Helvetica-Light", size: 11)!


let SELECT_POPUP_HTLC_TO_CHAIN = 0
let SELECT_POPUP_HTLC_TO_COIN = 1
let SELECT_POPUP_HTLC_TO_ACCOUNT = 2
let SELECT_POPUP_STARNAME_ACCOUNT = 3


let EXPLORER_COSMOS_MAIN    = "https://www.mintscan.io/cosmos/";
let EXPLORER_IRIS_MAIN      = "https://www.mintscan.io/iris/";
let EXPLORER_KAVA_MAIN      = "https://www.mintscan.io/kava/";
let EXPLORER_IOV_MAIN       = "https://www.mintscan.io/starname/";
let EXPLORER_BINANCE_MAIN   = "https://binance.mintscan.io/";
let EXPLORER_BAND_MAIN      = "https://cosmoscan.io/";
let EXPLORER_SECRET_MAIN    = "https://secretnodes.com/secret/chains/secret-2/";
let EXPLORER_AKASH_MAIN     = "https://www.mintscan.io/akash/";
let EXPLORER_OKEX_MAIN      = "https://www.oklink.com/okexchain/";
let EXPLORER_PERSIS_MAIN    = "https://www.mintscan.io/persistence/";
let EXPLORER_SENTINEL_MAIN  = "https://www.mintscan.io/sentinel/";
let EXPLORER_FETCH_MAIN     = "https://www.mintscan.io/fetchai/";
let EXPLORER_CRYPTO_MAIN    = "https://www.mintscan.io/crypto-org/";
let EXPLORER_SIF_MAIN       = "https://www.mintscan.io/sifchain/";
let EXPLORER_KI_MAIN        = "https://www.mintscan.io/ki-chain/";
let EXPLORER_OSMOSIS_MAIN   = "https://www.mintscan.io/osmosis/";

let EXPLORER_COSMOS_TEST    = "https://testnet.mintscan.io/";
let EXPLORER_IRIS_TEST      = "https://testnet.mintscan.io/iris/";
let EXPLORER_BINANCE_TEST   = "https://testnet-explorer.binance.org/";
let EXPLORER_KAVA_TEST      = "https://dev.mintscan.io/kava-testnet/";
let EXPLORER_OKEX_TEST      = "https://www.oklink.com/okexchain-test/";
let EXPLORER_CERTIK         = "https://explorer.certik.foundation/";
let EXPLORER_MEDI_TEST      = "https://testnet.mintscan.io/medibloc/";
let EXPLORER_RIZON_TEST     = "https://testnet.mintscan.io/rizon/";
let EXPLORER_ALTHEA_TEST    = "https://testnet.mintscan.io/althea/";




let PERSISTENCE_COSMOS_EVENT_ADDRESS    = "cosmos1ea6cx6km3jmryax5aefq0vy5wrfcdqtaau4f22";
let PERSISTENCE_COSMOS_EVENT_START      = 3846000;
let PERSISTENCE_COSMOS_EVENT_END        = 4206000;

let PERSISTENCE_KAVA_EVENT_ADDRESS      = "kava1fxxxruhmqx3myuhjwxx9gk90kwqrgs9jamr892";
let PERSISTENCE_KAVA_EVENT_START        = 422360;
let PERSISTENCE_KAVA_EVENT_END          = 672440;


let DAY_SEC     = NSDecimalNumber.init(string: "86400")
let MONTH_SEC   = DAY_SEC.multiplying(by: NSDecimalNumber.init(string: "30"))
let YEAR_SEC    = DAY_SEC.multiplying(by: NSDecimalNumber.init(string: "365"))

let BLOCK_TIME_COSMOS       = NSDecimalNumber.init(string: "7.3266")
let BLOCK_TIME_IRIS         = NSDecimalNumber.init(string: "6.6094")
let BLOCK_TIME_IOV          = NSDecimalNumber.init(string: "6.1838")
let BLOCK_TIME_KAVA         = NSDecimalNumber.init(string: "6.5700")
let BLOCK_TIME_BAND         = NSDecimalNumber.init(string: "3.01")
let BLOCK_TIME_CERTIK       = NSDecimalNumber.init(string: "5.75")
let BLOCK_TIME_SECRET       = NSDecimalNumber.init(string: "5.96")
let BLOCK_TIME_AKASH        = NSDecimalNumber.init(string: "6.1908")
let BLOCK_TIME_SENTINEL     = NSDecimalNumber.init(string: "6.0418")
let BLOCK_TIME_PERSISTENCE  = NSDecimalNumber.init(string: "5.6381")
let BLOCK_TIME_FETCH        = NSDecimalNumber.init(string: "5.8956")
let BLOCK_TIME_CRYPTO       = NSDecimalNumber.init(string: "6.4383")
let BLOCK_TIME_SIF          = NSDecimalNumber.init(string: "5.6577")
let BLOCK_TIME_KI           = NSDecimalNumber.init(string: "5.6524")


let OK_TX_TYPE_TRANSFER        = 1;
let OK_TX_TYPE_NEW_ORDER       = 2;
let OK_TX_TYPE_CANCEL_ORDER    = 3;

let OK_TX_TYPE_SIDE_BUY        = 1;
let OK_TX_TYPE_SIDE_SELL       = 2;
let OK_TX_TYPE_SIDE_SEND       = 3;
let OK_TX_TYPE_SIDE_RECEIVE    = 4;

