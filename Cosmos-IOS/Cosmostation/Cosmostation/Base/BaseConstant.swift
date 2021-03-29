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
let KEY_ATOM_TIC_CMC                    = "KEY_ATOM_TIC_CMC"
let KEY_CURRENCY                        = "KEY_CURRENCY"
let KEY_MARKET                          = "KEY_MARKET"
let KEY_USING_APP_LOCK                  = "KEY_USING_APP_LOCK"
let KEY_USING_BIO_AUTH                  = "KEY_USING_BIO_AUTH"
let KEY_PRICE_TIC_CGC                   = "KEY_PRICE_TIC_CGC"
let KEY_PRICE_TIC_CMC                   = "KEY_PRICE_TIC_CMC"
let KEY_FCM_TOKEN                       = "KEY_FCM_TOKEN"
let KEY_KAVA_TESTNET_WARN               = "KEY_KAVA_TESTNET_WARN"
let KEY_PRE_EVENT_HIDE                  = "KEY_PRE_EVENT_HIDE"

let CSS_URL                             = "https://api-wallet.cosmostation.io/";

let COSMOS_URL                          = "https://lcd-cosmos-app.cosmostation.io/";
let COSMOS_API                          = "https://api.cosmostation.io/";

let COSMOS_TEST_URL                     = "https://lcd-office.cosmostation.io/stargate-final/";
let COSMOS_TEST_API                     = "https://api-office.cosmostation.io/stargate-final/";


let IRIS_LCD_URL                        = "https://lcd-iris-app.cosmostation.io/";
let IRIS_API                            = "https://api-iris.cosmostation.io/";

let IRIS_TEST_URL                       = "https://lcd-office.cosmostation.io/bifrost/";
let IRIS_TEST_API                       = "https://api-office.cosmostation.io/bifrost/";


let BNB_URL                             = "https://dex.binance.org/";
let BNB_TEST_URL                        = "https://testnet-dex.binance.org/";


let KAVA_URL                            = "https://lcd-kava-app.cosmostation.io/";
let KAVA_API                            = "https://api-kava.cosmostation.io/";

let KAVA_TEST_URL                       = "https://lcd-office.cosmostation.io/kava-testnet-12000/";
let KAVA_TEST_API                       = "https://api-office.cosmostation.io/kava-testnet-12000/";
let KAVA_TEST_FAUCET                    = "";


let IOV_URL                             = "https://lcd-iov.cosmostation.io/";
let IOV_API                             = "https://api-iov.cosmostation.io/";

let IOV_TEST_URL                        = "https://iovnscli-rest-api.cluster-galaxynet.iov.one/";
let IOV_TEST_API                        = "";
let IOV_TEST_FAUCET                     = "https://faucet.cluster-galaxynet.iov.one/credit?address=";


let BAND_URL                            = "https://lcd-band.cosmostation.io/";
let BAND_API                            = "https://api-band.cosmostation.io/";


let OKEX_URL                            = "https://www.okex.com/okexchain/v1/";

let OKEX_TEST_URL                       = "https://www.okex.com/okexchain-test/v1/";


let CERTIK_URL                          = "https://lcd-certik.cosmostation.io/";
let CERTIK_API                          = "https://api-certik.cosmostation.io/";

let CERTIK_TEST_URL                     = "https://lcd-certik-testnet3.cosmostation.io/";
let CERTIK_TEST_API                     = "https://api-certik-testnet3.cosmostation.io/";


let SECRET_URL                          = "https://api.secretnodes.org/";
let SECRET_API                          = "";


let AKASH_API                           = "https://api-akash.cosmostation.io/";


//let PERSIS_API                          = "https://api-office.cosmostation.io/persistence/";
let PERSIS_API                          = "https://api-persistence.cosmostation.io/";


let SENTINEL_URL                        = "https://lcd-sentinel.cosmostation.io/";
let SENTINEL_API                        = "https://api-sentinel.cosmostation.io/";


let CGC_URL                             = "https://api.coingecko.com/";
let CMC_URL                             = "https://api.coinmarketcap.com/";
let MOON_PAY_URL                        = "https://buy.moonpay.io";
let MOON_PAY_PUBLICK                    = "pk_live_zbG1BOGMVTcfKibboIE2K3vduJBTuuCn";

let CSS_VERSION                         = CSS_URL + "v1/app/version/ios";
let CSS_PUSH_UPDATE                     = CSS_URL + "v1/account/update";
let CSS_MOON_PAY                        = CSS_URL + "v1/sign/moonpay";


//COSMOS_MAIN_URL
let COSMOS_MAIN_HISTORY                 = COSMOS_API + "v1/account/txs/";
let COSMOS_MAIN_TRANS_HISTORY           = COSMOS_API + "v1/account/transfer_txs/";

//COSMOS_TEST_URL
let COSMOS_TEST_HISTORY                 = COSMOS_TEST_API + "v1/account/txs/";
let COSMOS_TEST_TRANS_HISTORY           = COSMOS_TEST_API + "v1/account/transfer_txs/";


//IRIS_MAIN_URL
let IRIS_MAIN_HISTORY                   = IRIS_API + "v1/account/txs/";
let IRIS_MAIN_TRANS_HISTORY             = IRIS_API + "v1/account/transfer_txs/";

//IRIS_TEST_URL
let IRIS_TEST_HISTORY                   = IRIS_TEST_API + "v1/account/txs/";
let IRIS_TEST_TRANS_HISTORY             = IRIS_TEST_API + "v1/account/transfer_txs/";


//BNB_URL
let BNB_URL_NODE_INFO                   = BNB_URL + "api/v1/node-info";
let BNB_URL_ACCOUNT_INFO                = BNB_URL + "api/v1/account/";
let BNB_URL_TOKENS                      = BNB_URL + "api/v1/tokens";
let BNB_URL_MINI_TOKENS                 = BNB_URL + "api/v1/mini/tokens";
let BNB_URL_TIC                         = BNB_URL + "api/v1/ticker/24hr";
let BNB_URL_HISTORY                     = BNB_URL + "api/v1/transactions";
let BNB_URL_TX                          = BNB_URL + "api/v1/tx/";
let BNB_URL_CHECK_SWAPID                = BNB_URL + "api/v1/atomic-swaps/";


//BNB_TEST_URL
let BNB_TEST_URL_NODE_INFO              = BNB_TEST_URL + "api/v1/node-info";
let BNB_TEST_URL_ACCOUNT_INFO           = BNB_TEST_URL + "api/v1/account/";
let BNB_TEST_URL_TOKENS                 = BNB_TEST_URL + "api/v1/tokens";
let BNB_TEST_URL_MINI_TOKENS            = BNB_TEST_URL + "api/v1/mini/tokens";
let BNB_TEST_URL_TIC                    = BNB_TEST_URL + "api/v1/ticker/24hr";
let BNB_TEST_URL_HISTORY                = BNB_TEST_URL + "api/v1/transactions";
let BNB_TEST_URL_TX                     = BNB_TEST_URL + "api/v1/tx/";
let BNB_TEST_URL_CHECK_SWAPID           = BNB_TEST_URL + "api/v1/atomic-swaps/";
let BNB_TEST_FAUCET                     = "https://faucet-binance.cosmostation.io/claim/";


//KAVA_URL
let KAVA_NODE_INFO                      = KAVA_URL + "node_info";
let KAVA_ACCOUNT_INFO                   = KAVA_URL + "auth/accounts/";
let KAVA_VALIDATORS                     = KAVA_URL + "staking/validators";
let KAVA_BONDING                        = KAVA_URL + "staking/delegators/";
let KAVA_BONDING_TAIL                   = "/delegations";
let KAVA_UNBONDING                      = KAVA_URL + "staking/delegators/";
let KAVA_UNBONDING_TAIL                 = "/unbonding_delegations";
let KAVA_REWARD_FROM_VAL                = KAVA_URL + "distribution/delegators/";
let KAVA_REWARD_FROM_VAL_TAIL           = "/rewards/";
let KAVA_MINT_PARAM                     = KAVA_URL + "minting/parameters";
let KAVA_INFLATION                      = KAVA_URL + "minting/inflation";
let KAVA_PROVISIONS                     = KAVA_URL + "minting/annual-provisions";
let KAVA_STAKING_POOL                   = KAVA_URL + "staking/pool";
let KAVA_BORAD_TX                       = KAVA_URL + "txs";
let KAVA_TX                             = KAVA_URL + "txs/";
let KAVA_REDELEGATION                   = KAVA_URL + "staking/redelegations";
let KAVA_REWARD_ADDRESS                 = KAVA_URL + "distribution/delegators/";
let KAVA_REWARD_ADDRESS_TAIL            = "/withdraw_address";
let KAVA_PROPOSALS                      = KAVA_URL + "gov/proposals";
let KAVA_PROPOSALS_TALLY_TAIL           = "/tally";
let KAVA_CDP_PARAM                      = KAVA_URL + "cdp/parameters";
let KAVA_CDP_OWEN                       = KAVA_URL + "cdp/cdps";
let KAVA_CDP_DEPOSIT                    = KAVA_URL + "cdp/cdps/cdp/deposits/";
let KAVA_PRICE_FEED_PARAM               = KAVA_URL + "pricefeed/parameters";
let KAVA_PRICE_FEED_PRICE               = KAVA_URL + "pricefeed/price/";
let KAVA_CHECK_SWAPID                   = KAVA_URL + "bep3/swap/";
let KAVA_CHECK_SWAP_PARAM               = KAVA_URL + "bep3/parameters";
let KAVA_CHECK_SWAP_SUPPLY              = KAVA_URL + "bep3/supplies";
let KAVA_INCENTIVE_PARAM                = KAVA_URL + "incentive/parameters";
let KAVA_MY_INCENTIVE                   = KAVA_URL + "incentive/claims/";
let KAVA_CHECK_SUPPLY                   = KAVA_URL + "supply/total";
let KAVA_HAVEST_PARAM                   = KAVA_URL + "harvest/parameters";
let KAVA_HAVEST_DEPOSIT                 = KAVA_URL + "harvest/deposits";
let KAVA_HAVEST_REWARD                  = KAVA_URL + "harvest/claims";
let KAVA_HAVEST_ACCOUNT                 = KAVA_URL + "harvest/accounts";
let KAVA_INCENTIVE_REWARD               = KAVA_URL + "incentive/rewards";
let KAVA_HARD_PARAM                     = KAVA_URL + "hard/parameters";
let KAVA_HARD_TOTAL_DEPOIST             = KAVA_URL + "hard/total-deposited";
let KAVA_HARD_TOTAL_BORROW              = KAVA_URL + "hard/total-borrowed";
let KAVA_HARD_MY_DEPOSIT                = KAVA_URL + "hard/deposits";
let KAVA_HARD_MY_BORROW                 = KAVA_URL + "hard/borrows";
let KAVA_HARD_INTERESTRATE              = KAVA_URL + "hard/interest-rate";
let KAVA_HARD_RESERVE                   = KAVA_URL + "hard/reserves";
let KAVA_HARD_ACCOUNT                   = KAVA_URL + "hard/accounts";

let KAVA_API_HISTORY                    = KAVA_API + "v1/account/txs/";
let KAVA_API_TRANS_HISTORY              = KAVA_API + "v1/account/transfer_txs/";


//KAVA_TEST_URL
let KAVA_TEST_NODE_INFO                 = KAVA_TEST_URL + "node_info";
let KAVA_TEST_ACCOUNT_INFO              = KAVA_TEST_URL + "auth/accounts/";
let KAVA_TEST_VALIDATORS                = KAVA_TEST_URL + "staking/validators";
let KAVA_TEST_BONDING                   = KAVA_TEST_URL + "staking/delegators/";
let KAVA_TEST_BONDING_TAIL              = "/delegations";
let KAVA_TEST_UNBONDING                 = KAVA_TEST_URL + "staking/delegators/";
let KAVA_TEST_UNBONDING_TAIL            = "/unbonding_delegations";
let KAVA_TEST_REWARD_FROM_VAL           = KAVA_TEST_URL + "distribution/delegators/";
let KAVA_TEST_REWARD_FROM_VAL_TAIL      = "/rewards/";
let KAVA_TEST_MINT_PARAM                = KAVA_TEST_URL + "minting/parameters";
let KAVA_TEST_INFLATION                 = KAVA_TEST_URL + "minting/inflation";
let KAVA_TEST_PROVISIONS                = KAVA_TEST_URL + "minting/annual-provisions";
let KAVA_TEST_STAKING_POOL              = KAVA_TEST_URL + "staking/pool";
let KAVA_TEST_BORAD_TX                  = KAVA_TEST_URL + "txs";
let KAVA_TEST_TX                        = KAVA_TEST_URL + "txs/";
let KAVA_TEST_REDELEGATION              = KAVA_TEST_URL + "staking/redelegations";
let KAVA_TEST_REWARD_ADDRESS            = KAVA_TEST_URL + "distribution/delegators/";
let KAVA_TEST_REWARD_ADDRESS_TAIL       = "/withdraw_address";
let KAVA_TEST_PROPOSALS                 = KAVA_TEST_URL + "gov/proposals";
let KAVA_TEST_CDP_PARAM                 = KAVA_TEST_URL + "cdp/parameters";
let KAVA_TEST_CDP_OWEN                  = KAVA_TEST_URL + "cdp/cdps";
let KAVA_TEST_CDP_DEPOSIT               = KAVA_TEST_URL + "cdp/cdps/cdp/deposits/";
let KAVA_TEST_PRICE_FEED_PARAM          = KAVA_TEST_URL + "pricefeed/parameters";
let KAVA_TEST_PRICE_FEED_PRICE          = KAVA_TEST_URL + "pricefeed/price/";
let KAVA_TEST_CHECK_SWAPID              = KAVA_TEST_URL + "bep3/swap/";
let KAVA_TEST_CHECK_SWAP_PARAM          = KAVA_TEST_URL + "bep3/parameters";
let KAVA_TEST_CHECK_SWAP_SUPPLY         = KAVA_TEST_URL + "bep3/supplies";
let KAVA_TEST_INCENTIVE_PARAM           = KAVA_TEST_URL + "incentive/parameters";
let KAVA_TEST_MY_INCENTIVE              = KAVA_TEST_URL + "incentive/claims/";
let KAVA_TEST_CHECK_SUPPLY              = KAVA_TEST_URL + "supply/total";
let KAVA_TEST_HAVEST_PARAM              = KAVA_TEST_URL + "harvest/parameters";
let KAVA_TEST_HAVEST_DEPOSIT            = KAVA_TEST_URL + "harvest/deposits";
let KAVA_TEST_HAVEST_REWARD             = KAVA_TEST_URL + "harvest/claims";
let KAVA_TEST_HAVEST_ACCOUNT            = KAVA_TEST_URL + "harvest/accounts";
let KAVA_TEST_INCENTIVE_REWARD          = KAVA_TEST_URL + "incentive/rewards";
let KAVA_TEST_HARD_PARAM                = KAVA_TEST_URL + "hard/parameters";
let KAVA_TEST_HARD_TOTAL_DEPOIST        = KAVA_TEST_URL + "hard/total-deposited";
let KAVA_TEST_HARD_TOTAL_BORROW         = KAVA_TEST_URL + "hard/total-borrowed";
let KAVA_TEST_HARD_MY_DEPOSIT           = KAVA_TEST_URL + "hard/deposits";
let KAVA_TEST_HARD_MY_BORROW            = KAVA_TEST_URL + "hard/borrows";
let KAVA_TEST_HARD_INTERESTRATE         = KAVA_TEST_URL + "hard/interest-rate";
let KAVA_TEST_HARD_RESERVE              = KAVA_TEST_URL + "hard/reserves";
let KAVA_TEST_HARD_ACCOUNT              = KAVA_TEST_URL + "hard/accounts";

let KAVA_TEST_API_HISTORY               = KAVA_TEST_API + "v1/account/txs/";
let KAVA_TEST_API_TRANS_HISTORY         = KAVA_TEST_API + "v1/account/transfer_txs/";


//BAND_URL
let BAND_NODE_INFO                      = BAND_URL + "node_info";
let BAND_TX                             = BAND_URL + "txs/";
let BAND_VALIDATORS                     = BAND_URL + "staking/validators";
let BAND_ACCOUNT_INFO                   = BAND_URL + "auth/accounts/";
let BAND_BONDING                        = BAND_URL + "staking/delegators/";
let BAND_BONDING_TAIL                   = "/delegations";
let BAND_UNBONDING                      = BAND_URL + "staking/delegators/";
let BAND_UNBONDING_TAIL                 = "/unbonding_delegations";
let BAND_REWARD_FROM_VAL                = BAND_URL + "distribution/delegators/";
let BAND_REWARD_FROM_VAL_TAIL           = "/rewards/";
let BAND_REWARD_ADDRESS                 = BAND_URL + "distribution/delegators/";
let BAND_REWARD_ADDRESS_TAIL            = "/withdraw_address";
let BAND_REDELEGATION                   = BAND_URL + "staking/redelegations";
let BAND_MINT_PARAM                     = BAND_URL + "minting/parameters";
let BAND_INFLATION                      = BAND_URL + "minting/inflation";
let BAND_PROVISIONS                     = BAND_URL + "minting/annual-provisions";
let BAND_STAKING_POOL                   = BAND_URL + "staking/pool";
let BAND_BORAD_TX                       = BAND_URL + "txs";
let BAND_PROPOSALS                      = BAND_URL + "gov/proposals";
let BAND_PROPOSALS_TALLY_TAIL           = "/tally";
let BAND_ORACLE_STATUS                  = BAND_URL + "oracle/active_validators";

let BAND_API_HISTORY                    = BAND_API + "v1/account/txs/";
let BAND_API_TRANS_HISTORY              = BAND_API + "v1/account/transfer_txs/";


//IOV_URL
let IOV_NODE_INFO                       = IOV_URL + "node_info";
let IOV_TX                              = IOV_URL + "txs/";
let IOV_VALIDATORS                      = IOV_URL + "staking/validators";
let IOV_ACCOUNT_INFO                    = IOV_URL + "auth/accounts/";
let IOV_BONDING                         = IOV_URL + "staking/delegators/";
let IOV_BONDING_TAIL                    = "/delegations";
let IOV_UNBONDING                       = IOV_URL + "staking/delegators/";
let IOV_UNBONDING_TAIL                  = "/unbonding_delegations";
let IOV_REWARD_FROM_VAL                 = IOV_URL + "distribution/delegators/";
let IOV_REWARD_FROM_VAL_TAIL            = "/rewards/";
let IOV_REWARD_ADDRESS                  = IOV_URL + "distribution/delegators/";
let IOV_REWARD_ADDRESS_TAIL             = "/withdraw_address";
let IOV_REDELEGATION                    = IOV_URL + "staking/redelegations";
let IOV_MINT_PARAM                      = IOV_URL + "minting/parameters";
let IOV_INFLATION                       = IOV_URL + "minting/inflation";
let IOV_PROVISIONS                      = IOV_URL + "minting/annual-provisions";
let IOV_STAKING_POOL                    = IOV_URL + "staking/pool";
let IOV_BORAD_TX                        = IOV_URL + "txs";
let IOV_STARNAME_FEE                    = IOV_URL + "configuration/query/fees";
let IOV_STARNAME_CONFIG                 = IOV_URL + "configuration/query/configuration";
let IOV_CHECK_WITH_STARNAME             = IOV_URL + "starname/query/resolve";
let IOV_CHECK_MY_DOMAIN                 = IOV_URL + "starname/query/domainsWithOwner";
let IOV_CHECK_MY_ACCOUNT                = IOV_URL + "starname/query/accountsWithOwner";
let IOV_STARNAME_DOMAIN_INFO            = IOV_URL + "starname/query/domainInfo";
let IOV_PROPOSALS                       = IOV_URL + "gov/proposals";
let IOV_PROPOSALS_TALLY_TAIL            = "/tally";

let IOV_API_HISTORY                     = IOV_API + "v1/account/txs/";
let IOV_API_TRANS_HISTORY               = IOV_API + "v1/account/transfer_txs/";

//IOV_TEST_URL
let IOV_TEST_NODE_INFO                  = IOV_TEST_URL + "node_info";
let IOV_TEST_TX                         = IOV_TEST_URL + "txs/";
let IOV_TEST_VALIDATORS                 = IOV_TEST_URL + "staking/validators";
let IOV_TEST_ACCOUNT_INFO               = IOV_TEST_URL + "auth/accounts/";
let IOV_TEST_BONDING                    = IOV_TEST_URL + "staking/delegators/";
let IOV_TEST_BONDING_TAIL               = "/delegations";
let IOV_TEST_UNBONDING                  = IOV_TEST_URL + "staking/delegators/";
let IOV_TEST_UNBONDING_TAIL             = "/unbonding_delegations";
let IOV_TEST_REWARD_FROM_VAL            = IOV_TEST_URL + "distribution/delegators/";
let IOV_TEST_REWARD_FROM_VAL_TAIL       = "/rewards/";
let IOV_TEST_REWARD_ADDRESS             = IOV_TEST_URL + "distribution/delegators/";
let IOV_TEST_REWARD_ADDRESS_TAIL        = "/withdraw_address";
let IOV_TEST_REDELEGATION               = IOV_TEST_URL + "staking/redelegations";
let IOV_TEST_MINT_PARAM                 = IOV_TEST_URL + "minting/parameters";
let IOV_TEST_INFLATION                  = IOV_TEST_URL + "minting/inflation";
let IOV_TEST_PROVISIONS                 = IOV_TEST_URL + "minting/annual-provisions";
let IOV_TEST_STAKING_POOL               = IOV_TEST_URL + "staking/pool";
let IOV_TEST_BORAD_TX                   = IOV_TEST_URL + "txs";
let IOV_TEST_STARNAME_FEE               = IOV_TEST_URL + "configuration/query/fees";
let IOV_TEST_STARNAME_CONFIG            = IOV_TEST_URL + "configuration/query/configuration";
let IOV_TEST_CHECK_WITH_STARNAME        = IOV_TEST_URL + "starname/query/resolve";
let IOV_TEST_CHECK_MY_DOMAIN            = IOV_TEST_URL + "starname/query/domainsWithOwner";
let IOV_TEST_CHECK_MY_ACCOUNT           = IOV_TEST_URL + "starname/query/accountsWithOwner";
let IOV_TEST_STARNAME_DOMAIN_INFO       = IOV_TEST_URL + "starname/query/domainInfo";



//OKEX_URL
let OKEX_NODE_INFO                          = OKEX_URL + "node_info";
let OKEX_TX                                 = OKEX_URL + "txs/";
let OKEX_VALIDATORS                         = OKEX_URL + "staking/validators";
let OKEX_ACCOUNT_INFO                       = OKEX_URL + "auth/accounts/";
let OKEX_ACCOUNT_BALANCE                    = OKEX_URL + "accounts/";
let OKEX_TOKEN_LIST                         = OKEX_URL + "tokens";
let OKEX_TICKER_LIST                        = OKEX_URL + "tickers";
let OKEX_STAKING                            = OKEX_URL + "staking/delegators/";
let OKEX_UNBONDING                          = OKEX_URL + "staking/delegators/";
let OKEX_UNBONDING_TAIL                     = "/unbonding_delegations";
let OKEX_HISTORY                            = OKEX_URL + "transactions";
let OKEX_BORAD_TX                           = OKEX_URL + "txs";

//OKEX_TEST_URL
let OKEX_TEST_NODE_INFO                     = OKEX_TEST_URL + "node_info";
let OKEX_TEST_TX                            = OKEX_TEST_URL + "txs/";
let OKEX_TEST_VALIDATORS                    = OKEX_TEST_URL + "staking/validators";
let OKEX_TEST_ACCOUNT_INFO                  = OKEX_TEST_URL + "auth/accounts/";
let OKEX_TEST_ACCOUNT_BALANCE               = OKEX_TEST_URL + "accounts/";
let OKEX_TEST_TOKEN_LIST                    = OKEX_TEST_URL + "tokens";
let OKEX_TEST_TICKER_LIST                   = OKEX_TEST_URL + "tickers";
let OKEX_TEST_STAKING                       = OKEX_TEST_URL + "staking/delegators/";
let OKEX_TEST_UNBONDING                     = OKEX_TEST_URL + "staking/delegators/";
let OKEX_TEST_UNBONDING_TAIL                = "/unbonding_delegations";
let OKEX_TEST_HISTORY                       = OKEX_TEST_URL + "transactions";
let OKEX_TEST_BORAD_TX                      = OKEX_TEST_URL + "txs";


//SECRET_URL
let SECRET_NODE_INFO                        = SECRET_URL + "node_info";
let SECRET_TX                               = SECRET_URL + "txs/";
let SECRET_VALIDATORS                       = SECRET_URL + "staking/validators";
let SECRET_ACCOUNT_INFO                     = SECRET_URL + "auth/accounts/";
let SECRET_BONDING                          = SECRET_URL + "staking/delegators/";
let SECRET_BONDING_TAIL                     = "/delegations";
let SECRET_UNBONDING                        = SECRET_URL + "staking/delegators/";
let SECRET_UNBONDING_TAIL                   = "/unbonding_delegations";
let SECRET_REWARD_FROM_VAL                  = SECRET_URL + "distribution/delegators/";
let SECRET_REWARD_FROM_VAL_TAIL             = "/rewards/";
let SECRET_REWARD_ADDRESS                   = SECRET_URL + "distribution/delegators/";
let SECRET_REWARD_ADDRESS_TAIL              = "/withdraw_address";
let SECRET_REDELEGATION                     = SECRET_URL + "staking/redelegations";
let SECRET_MINT_PARAM                       = SECRET_URL + "minting/parameters";
let SECRET_INFLATION                        = SECRET_URL + "minting/inflation";
let SECRET_PROVISIONS                       = SECRET_URL + "minting/annual-provisions";
let SECRET_STAKING_POOL                     = SECRET_URL + "staking/pool";
let SECRET_BORAD_TX                         = SECRET_URL + "txs";
let SECRET_PROPOSALS                        = SECRET_URL + "gov/proposals";
let SECRET_PROPOSALS_TALLY_TAIL             = "/tally";

let SECRET_API_HISTORY                      = SECRET_API + "v1/account/txs/";
let SECRET_API_TRANS_HISTORY                = SECRET_API + "v1/account/transfer_txs/";


//CERTIC_URL
let CERTIK_NODE_INFO                        = CERTIK_URL + "node_info";
let CERTIK_TX                               = CERTIK_URL + "txs/";
let CERTIK_VALIDATORS                       = CERTIK_URL + "staking/validators";
let CERTIK_ACCOUNT_INFO                     = CERTIK_URL + "auth/accounts/";
let CERTIK_BONDING                          = CERTIK_URL + "staking/delegators/";
let CERTIK_BONDING_TAIL                     = "/delegations";
let CERTIK_UNBONDING                        = CERTIK_URL + "staking/delegators/";
let CERTIK_UNBONDING_TAIL                   = "/unbonding_delegations";
let CERTIK_REWARD_FROM_VAL                  = CERTIK_URL + "distribution/delegators/";
let CERTIK_REWARD_FROM_VAL_TAIL             = "/rewards/";
let CERTIK_MINT_PARAM                       = CERTIK_URL + "minting/parameters";
let CERTIK_INFLATION                        = CERTIK_URL + "minting/inflation";
let CERTIK_PROVISIONS                       = CERTIK_URL + "minting/annual-provisions";
let CERTIK_STAKING_POOL                     = CERTIK_URL + "staking/pool";
let CERTIK_REWARD_ADDRESS                   = CERTIK_URL + "distribution/delegators/";
let CERTIK_REWARD_ADDRESS_TAIL              = "/withdraw_address";
let CERTIK_REDELEGATION                     = CERTIK_URL + "staking/redelegations";
let CERTIK_BORAD_TX                         = CERTIK_URL + "txs";
let CERTIK_PROPOSALS                        = CERTIK_URL + "gov/proposals";
let CERTIK_PROPOSALS_TALLY_TAIL             = "/tally";

let CERTIK_API_HISTORY                      = CERTIK_API + "v1/account/txs/";
let CERTIK_API_TRANS_HISTORY                = CERTIK_API + "v1/account/transfer_txs/";


//CERTIC_TEST_URL
let CERTIK_TEST_NODE_INFO                   = CERTIK_TEST_URL + "node_info";
let CERTIK_TEST_TX                          = CERTIK_TEST_URL + "txs/";
let CERTIK_TEST_VALIDATORS                  = CERTIK_TEST_URL + "staking/validators";
let CERTIK_TEST_ACCOUNT_INFO                = CERTIK_TEST_URL + "auth/accounts/";
let CERTIK_TEST_BONDING                     = CERTIK_TEST_URL + "staking/delegators/";
let CERTIK_TEST_BONDING_TAIL                = "/delegations";
let CERTIK_TEST_UNBONDING                   = CERTIK_TEST_URL + "staking/delegators/";
let CERTIK_TEST_UNBONDING_TAIL              = "/unbonding_delegations";
let CERTIK_TEST_REWARD_FROM_VAL             = CERTIK_TEST_URL + "distribution/delegators/";
let CERTIK_TEST_REWARD_FROM_VAL_TAIL        = "/rewards/";
let CERTIK_TEST_MINT_PARAM                  = CERTIK_TEST_URL + "minting/parameters";
let CERTIK_TEST_INFLATION                   = CERTIK_TEST_URL + "minting/inflation";
let CERTIK_TEST_PROVISIONS                  = CERTIK_TEST_URL + "minting/annual-provisions";
let CERTIK_TEST_STAKING_POOL                = CERTIK_TEST_URL + "staking/pool";
let CERTIK_TEST_REWARD_ADDRESS              = CERTIK_TEST_URL + "distribution/delegators/";
let CERTIK_TEST_REWARD_ADDRESS_TAIL         = "/withdraw_address";
let CERTIK_TEST_REDELEGATION                = CERTIK_TEST_URL + "staking/redelegations";
let CERTIK_TEST_BORAD_TX                    = CERTIK_TEST_URL + "txs";
let CERTIK_TEST_PROPOSALS                   = CERTIK_TEST_URL + "gov/proposals";
let CERTIK_TEST_PROPOSALS_TALLY_TAIL        = "/tally";

let CERTIK_TEST_API_HISTORY                 = CERTIK_TEST_API + "v1/account/txs/";
let CERTIK_TEST_API_TRANS_HISTORY           = CERTIK_TEST_API + "v1/account/transfer_txs/";



//AKASH Mainnet
let AKASH_MAIN_HISTORY                      = AKASH_API + "v1/account/txs/";
let AKASH_MAIN_TRANS_HISTORY                = AKASH_API + "v1/account/transfer_txs/";


//PERSISTENCE Mainnet
let PERSIS_MAIN_HISTORY                     = PERSIS_API + "v1/account/txs/";
let PERSIS_MAIN_TRANS_HISTORY               = PERSIS_API + "v1/account/transfer_txs/";


//SENTINEL_URL
let SENTINEL_NODE_INFO                  = SENTINEL_URL + "node_info";
let SENTINEL_TX                         = SENTINEL_URL + "txs/";
let SENTINEL_VALIDATORS                 = SENTINEL_URL + "staking/validators";
let SENTINEL_ACCOUNT_INFO               = SENTINEL_URL + "auth/accounts/";
let SENTINEL_BONDING                    = SENTINEL_URL + "staking/delegators/";
let SENTINEL_BONDING_TAIL               = "/delegations";
let SENTINEL_UNBONDING                  = SENTINEL_URL + "staking/delegators/";
let SENTINEL_UNBONDING_TAIL             = "/unbonding_delegations";
let SENTINEL_REWARD_FROM_VAL            = SENTINEL_URL + "distribution/delegators/";
let SENTINEL_REWARD_FROM_VAL_TAIL       = "/rewards/";
let SENTINEL_REWARD_ADDRESS             = SENTINEL_URL + "distribution/delegators/";
let SENTINEL_REWARD_ADDRESS_TAIL        = "/withdraw_address";
let SENTINEL_REDELEGATION               = SENTINEL_URL + "staking/redelegations";
let SENTINEL_MINT_PARAM                 = SENTINEL_URL + "minting/parameters";
let SENTINEL_INFLATION                  = SENTINEL_URL + "minting/inflation";
let SENTINEL_PROVISIONS                 = SENTINEL_URL + "minting/annual-provisions";
let SENTINEL_STAKING_POOL               = SENTINEL_URL + "staking/pool";
let SENTINEL_BORAD_TX                   = SENTINEL_URL + "txs";
let SENTINEL_PROPOSALS                  = SENTINEL_URL + "gov/proposals";
let SENTINEL_PROPOSALS_TALLY_TAIL       = "/tally";

let SENTINEL_API_HISTORY                = SENTINEL_API + "v1/account/txs/";
let SENTINEL_API_TRANS_HISTORY          = SENTINEL_API + "v1/account/transfer_txs/";


let CMC_PRICE_TIC                       = CMC_URL + "v2/ticker/";
let CGC_PRICE_TIC                       = CGC_URL + "api/v3/coins/";


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

let TOKEN_IMG_URL                       = "https://raw.githubusercontent.com/cosmostation/cosmostation_token_resource/master/thumnail/"
let KAVA_COIN_IMG_URL                   = "https://raw.githubusercontent.com/cosmostation/cosmostation_token_resource/master/kava/coin/";
let KAVA_CDP_IMG_URL                    = "https://raw.githubusercontent.com/cosmostation/cosmostation_token_resource/master/kava/cdp/";
let KAVA_HARD_POOL_IMG_URL              = "https://raw.githubusercontent.com/cosmostation/cosmostation_token_resource/master/kava/hard/";
let OKEX_COIN_IMG_URL                   = "https://raw.githubusercontent.com/cosmostation/cosmostation_token_resource/master/coin_image/okex/";


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


let IRIS_BANK_TYPE_ACCOUNT                  = "irishub/bank/Account";
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


let BNB_MSG_TYPE_TRANSFER                   = "bnb_transfer";
let BNB_MSG_TYPE_HTLC                       = "tokens/HTLTMsg";
let BNB_MSG_TYPE_HTLC_CLIAM                 = "tokens/ClaimHTLTMsg";
let BNB_MSG_TYPE_HTLC_REFUND                = "tokens/RefundHTLTMsg";

let TASK_TYPE_VOTE                          = "vote_task";
let TASK_TYPE_HTLC_SWAP                     = "htlc_swap";
let TASK_TYPE_HTLC_REFUND                   = "htlc_refund";

let KAVA_MSG_TYPE_TRANSFER                  = "kava_transfer";
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



let IOV_MSG_TYPE_TRANSFER                   = "iov_transfer";
let BAND_MSG_TYPE_TRANSFER                  = "band_transfer";
let CERTIK_MSG_TYPE_TRANSFER                = "bank/MsgSend";
let SECRET_MSG_TYPE_TRANSFER                = "secret_transfer";

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
let FEE_ATOM_TINY                           = "500";
let FEE_ATOM_LOW                            = "1000";
let FEE_ATOM_MID                            = "2000";
let FEE_ATOM_HIGH                           = "4000";
let FEE_MIN_RATE                            = "0.0025";


let GAS_FEE_RATE_TINY                       = 0.00025
let GAS_FEE_RATE_LOW                        = 0.0025
let GAS_FEE_RATE_AVERAGE                    = 0.025

let GAS_FEE_RATE_TINY_IRIS                  = 0.002
let GAS_FEE_RATE_LOW_IRIS                   = 0.02
let GAS_FEE_RATE_AVERAGE_IRIS               = 0.2

let GAS_FEE_AMOUNT_LOW                      = "100000"
let GAS_FEE_AMOUNT_MID                      = "200000"
let GAS_FEE_AMOUNT_HIGH                     = "300000"
let GAS_FEE_AMOUNT_REINVEST                 = "220000"
let GAS_FEE_AMOUNT_REDELE                   = "240000"

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



let GAS_FEE_RATE_IRIS_AVERAGE               = "0.000008"

let GAS_FEE_AMOUNT_IRIS_LOW                 = "10000"
let GAS_FEE_AMOUNT_IRIS_SEND                = "25000"
let GAS_FEE_AMOUNT_IRIS_MID                 = "50000"
let GAS_FEE_AMOUNT_IRIS_REDELEGATE          = "65000"
let GAS_FEE_AMOUNT_IRIS_REWARD_BASE         = "10000"
let GAS_FEE_AMOUNT_IRIS_REWARD_MUX          = "5000"



let GAS_FEE_BNB_TRANSFER                    = "0.000375"

let KAVA_GAS_FEE_AMOUNT_LOW                 = "150000"
let KAVA_GAS_FEE_AMOUNT_SEND                = "200000"
let KAVA_GAS_FEE_AMOUNT_REWARD              = "200000"
let KAVA_GAS_FEE_AMOUNT_AVERAGE             = "500000"
let KAVA_GAS_FEE_AMOUNT_REDELEGATE          = "500000"
let KAVA_GAS_FEE_AMOUNT_REINVEST            = "500000"
let KAVA_GAS_FEE_AMOUNT_CDP                 = "1500000"
let KAVA_GAS_FEE_AMOUNT_BEP3                = "300000"
let KAVA_GAS_FEE_AMOUNT_HIGH                = "350000"
let KAVA_GAS_FEE_AMOUNT_CREATE_CDP          = "1500000"


let IOV_GAS_FEE_RATE_AVERAGE                = "1"
let IOV_GAS_AMOUNT_SEND                     = "100000"
let IOV_GAS_AMOUNT_STAKE                    = "200000"
let IOV_GAS_AMOUNT_REDELEGATE               = "300000"
let IOV_GAS_AMOUNT_REINVEST                 = "300000"
let IOV_GAS_AMOUNT_LOW                      = "100000"
let IOV_GAS_AMOUNT_REGISTER                 = "300000"
let IOV_GAS_AMOUNT_DELETE                   = "150000"
let IOV_GAS_AMOUNT_RENEW                    = "300000"
let IOV_GAS_AMOUNT_REPLACE                  = "300000"

let GAS_FEE_IOV_TRANSFER                    = "0.500000000"


let GAS_FEE_RATE_OK                         = "0.00000001"
let OK_GAS_AMOUNT_SEND                      = "200000"
let OK_GAS_AMOUNT_STAKE                     = "200000"
let OK_GAS_AMOUNT_STAKE_MUX                 = "20000"
let OK_GAS_AMOUNT_VOTE                      = "200000"
let OK_GAS_AMOUNT_VOTE_MUX                  = "50000"


let CERTIK_GAS_FEE_RATE_AVERAGE             = "0.05"
let CERTIK_GAS_AMOUNT_SEND                  = "100000"
let CERTIK_GAS_AMOUNT_STAKE                 = "200000"
let CERTIK_GAS_AMOUNT_REDELEGATE            = "300000"
let CERTIK_GAS_AMOUNT_REINVEST              = "300000"
let CERTIK_GAS_AMOUNT_REWARD_ADDRESS_CHANGE = "100000"
let CERTIK_GAS_AMOUNT_VOTE                  = "100000"

let SECRET_GAS_FEE_RATE_AVERAGE             = "0.25"
let SECRET_GAS_AMOUNT_SEND                  = "80000"
let SECRET_GAS_AMOUNT_STAKE                 = "200000"
let SECRET_GAS_AMOUNT_REDELEGATE            = "300000"
let SECRET_GAS_AMOUNT_REINVEST              = "350000"
let SECRET_GAS_AMOUNT_REWARD_ADDRESS_CHANGE = "80000"
let SECRET_GAS_AMOUNT_VOTE                  = "100000"


let SENTINEL_GAS_FEE_RATE_AVERAGE             = "0.1"
let SENTINEL_GAS_AMOUNT_SEND                  = "100000"
let SENTINEL_GAS_AMOUNT_STAKE                 = "200000"
let SENTINEL_GAS_AMOUNT_REDELEGATE            = "300000"
let SENTINEL_GAS_AMOUNT_REINVEST              = "350000"
let SENTINEL_GAS_AMOUNT_REWARD_ADDRESS_CHANGE = "100000"
let SENTINEL_GAS_AMOUNT_VOTE                  = "100000"


// Constant for BEP3-Swap
let BINANCE_MAIN_BNB_DEPUTY                 = "bnb1jh7uv2rm6339yue8k4mj9406k3509kr4wt5nxn"
let KAVA_MAIN_BNB_DEPUTY                    = "kava1r4v2zdhdalfj2ydazallqvrus9fkphmglhn6u6"
let BINANCE_MAIN_BTCB_DEPUTY                = "bnb1xz3xqf4p2ygrw9lhp5g5df4ep4nd20vsywnmpr"
let KAVA_MAIN_BTCB_DEPUTY                   = "kava14qsmvzprqvhwmgql9fr0u3zv9n2qla8zhnm5pc"
let BINANCE_MAIN_XRPB_DEPUTY                = "bnb15jzuvvg2kf0fka3fl2c8rx0kc3g6wkmvsqhgnh"
let KAVA_MAIN_XRPB_DEPUTY                   = "kava1c0ju5vnwgpgxnrktfnkccuth9xqc68dcdpzpas"
let BINANCE_MAIN_BUSD_DEPUTY                = "bnb10zq89008gmedc6rrwzdfukjk94swynd7dl97w8"
let KAVA_MAIN_BUSD_DEPUTY                   = "kava1hh4x3a4suu5zyaeauvmv7ypf7w9llwlfufjmuu"


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
    
    case COSMOS_TEST
    case IRIS_TEST
    case BINANCE_TEST
    case KAVA_TEST
    case IOV_TEST
    case OKEX_TEST
    case CERTIK_TEST
    
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
        result.append(SECRET_MAIN)

//        result.append(COSMOS_TEST)
//        result.append(IRIS_TEST)
//        result.append(BINANCE_TEST)
        result.append(KAVA_TEST)
//        result.append(IOV_TEST)
//        result.append(OKEX_TEST)
//        result.append(CERTIK_TEST)
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

let CHAIN_COSMOS_TEST_S = "SUPPORT_CHAIN_COSMOS_TEST"
let CHAIN_IRIS_TEST_S = "SUPPORT_CHAIN_IRIS_TEST"
let CHAIN_BINANCE_TEST_S = "SUPPORT_CHAIN_BINANCE_TEST"
let CHAIN_KAVA_TEST_S = "SUPPORT_CHAIN_KAVA_TEST"
let CHAIN_IOV_TEST_S = "SUPPORT_CHAIN_IOV_TEST"
let CHAIN_OKEX_TEST_S = "SUPPORT_CHAIN_OKEX_TEST"
let CHAIN_CERTIK_TEST_S = "SUPPORT_CHAIN_CERTIK_TEST"

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

let COSMOS_TEST_DENOM = "umuon"
let IRIS_TEST_DENOM = "ubif"
let IOV_TEST_DENOM = "uvoi"
let KAVA_HARD_DENOM = "hard"


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
let EXPLORER_PERSIS_MAIN    = "https://dev.mintscan.io/persistence/";
let EXPLORER_SENTINEL_MAIN  = "https://www.mintscan.io/sentinel/";

let EXPLORER_COSMOS_TEST    = "https://testnet.mintscan.io/";
let EXPLORER_IRIS_TEST      = "https://testnet.mintscan.io/iris/";
let EXPLORER_BINANCE_TEST   = "https://testnet-explorer.binance.org/";
let EXPLORER_KAVA_TEST      = "https://dev.mintscan.io/kava-testnet/";
let EXPLORER_OKEX_TEST      = "https://www.oklink.com/okexchain-test/";
let EXPLORER_CERTIK         = "https://explorer.certik.foundation/";




let PERSISTENCE_COSMOS_EVENT_ADDRESS    = "cosmos1ea6cx6km3jmryax5aefq0vy5wrfcdqtaau4f22";
let PERSISTENCE_COSMOS_EVENT_START      = 3846000;
let PERSISTENCE_COSMOS_EVENT_END        = 4206000;

let PERSISTENCE_KAVA_EVENT_ADDRESS      = "kava1fxxxruhmqx3myuhjwxx9gk90kwqrgs9jamr892";
let PERSISTENCE_KAVA_EVENT_START        = 422360;
let PERSISTENCE_KAVA_EVENT_END          = 672440;


let DAY_SEC     = NSDecimalNumber.init(string: "86400")
let MONTH_SEC   = DAY_SEC.multiplying(by: NSDecimalNumber.init(string: "30"))
let YEAR_SEC    = DAY_SEC.multiplying(by: NSDecimalNumber.init(string: "365"))

let BLOCK_TIME_COSMOS   = NSDecimalNumber.init(string: "7.11")
let BLOCK_TIME_IRIS     = NSDecimalNumber.init(string: "6.93")
let BLOCK_TIME_IOV      = NSDecimalNumber.init(string: "6.21")
let BLOCK_TIME_KAVA     = NSDecimalNumber.init(string: "8.01")
let BLOCK_TIME_BAND     = NSDecimalNumber.init(string: "3.01")
let BLOCK_TIME_CERTIK   = NSDecimalNumber.init(string: "5.75")
let BLOCK_TIME_SECRET   = NSDecimalNumber.init(string: "5.96")
let BLOCK_TIME_AKASH    = NSDecimalNumber.init(string: "6.21")


let OK_TX_TYPE_TRANSFER        = 1;
let OK_TX_TYPE_NEW_ORDER       = 2;
let OK_TX_TYPE_CANCEL_ORDER    = 3;

let OK_TX_TYPE_SIDE_BUY        = 1;
let OK_TX_TYPE_SIDE_SELL       = 2;
let OK_TX_TYPE_SIDE_SEND       = 3;
let OK_TX_TYPE_SIDE_RECEIVE    = 4;

