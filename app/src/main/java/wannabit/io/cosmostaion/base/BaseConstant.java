package wannabit.io.cosmostaion.base;

import java.math.BigDecimal;

public class BaseConstant {
    public final static boolean SUPPORT_MOONPAY         = true;
    public final static boolean SUPPORT_BEP3_SWAP       = true;
    public final static String LOG_TAG                  = "Cosmostation";

    public final static String DB_NAME                  = "WannaBit";
    public final static int DB_VERSION                  = 7;
    public final static String DB_TABLE_PASSWORD        = "paswd";
    public final static String DB_TABLE_ACCOUNT         = "accnt";
    public final static String DB_TABLE_BALANCE         = "balan";
    public final static String DB_TABLE_BONDING         = "bondi";
    public final static String DB_TABLE_UNBONDING       = "unbond";
    public final static String DB_TABLE_MNEMONIC        = "mnemonic";

    public final static String PRE_USER_ID                  = "PRE_USER_ID";
    public final static String PRE_SELECTED_CHAINS          = "PRE_SELECTED_CHAINS";
    public final static String PRE_VALIDATOR_SORTING        = "PRE_VALIDATOR_SORTING";
    public final static String PRE_MY_VALIDATOR_SORTING     = "PRE_MY_VALIDATOR_SORTING";
    public final static String PRE_ALARM_STATUS             = "PRE_ALARM_STATUS";
    public final static String PRE_CURRENCY                 = "PRE_CURRENCY";
    public final static String PRE_USING_APP_LOCK           = "PRE_USING_APP_LOCK";
    public final static String PRE_USING_FINGERPRINT        = "PRE_USING_FINGERPRINT";
    public final static String PRE_USING_PASS               = "PRE_USING_AUTO_PASS";
    public final static String PRE_LAST_PASS_TIME           = "PRE_LAST_PASS_TIME";
    public final static String PRE_FCM_TOKEN                = "PRE_FCM_TOKEN_NEW";
    public final static String PRE_USER_HIDEN_CHAINS        = "PRE_USER_HIDEN_CHAINS";
    public final static String PRE_USER_SORTED_CHAINS       = "PRE_USER_SORTED_CHAINS";
    public final static String PRE_USER_EXPENDED_CHAINS     = "PRE_USER_EXPENDED_CHAINS";
    public final static String PRE_DB_VERSION               = "PRE_DB_VERSION";
    public final static String PRE_THEME_MOD                = "PRE_THEME_MOD";

    public final static int TASK_INIT_PW                                = 2000;
    public final static int TASK_INIT_ACCOUNT                           = 2002;
    public final static int TASK_INIT_EMPTY_ACCOUNT                     = 2003;
    public final static int TASK_FETCH_ACCOUNT                          = 2004;
    public final static int TASK_PASSWORD_CHECK                         = 2015;
    public final static int TASK_GEN_TX_SIMPLE_SEND                     = 2018;
    public final static int TASK_OVERRIDE_ACCOUNT                       = 2019;
    public final static int TASK_DELETE_USER                            = 2024;
    public final static int TASK_CHECK_MNEMONIC                         = 2025;
    public final static int TASK_FETCH_BNB_HISTORY                      = 2041;
    public final static int TASK_FETCH_BNB_TOKENS                       = 2042;
    public final static int TASK_FETCH_BNB_TICKER                       = 2043;
    public final static int TASK_FETCH_BNB_MINI_TICKER                  = 2044;
    public final static int TASK_GEN_TX_BNB_SIMPLE_SEND                 = 2045;
    public final static int TASK_PUSH_STATUS_UPDATE                     = 2057;
    public final static int TASK_MOON_PAY_SIGNATURE                     = 2058;
    public final static int TASK_FETCH_KAVA_CDP_DEPOSIT                 = 2061;
    public final static int TASK_FETCH_KAVA_INCENTIVE_PARAM             = 2074;
    public final static int TASK_FETCH_KAVA_INCENTIVE_REWARD            = 2076;
    public final static int TASK_FETCH_BNB_MINI_TOKENS                  = 2078;
    public final static int TASK_GEN_TX_HTLC_CREATE                     = 2079;
    public final static int TASK_GEN_TX_HTLC_CLAIM                      = 2080;
    public final static int TASK_FETCH_BNB_FEES                         = 2081;
    public final static int TASK_FETCH_OK_STAKING_INFO                  = 2082;
    public final static int TASK_FETCH_OK_UNBONDING_INFO                = 2083;
    public final static int TASK_FETCH_OK_ACCOUNT_BALANCE               = 2084;
    public final static int TASK_FETCH_OK_TOKEN_LIST                    = 2085;
    public final static int TASK_GEN_TX_OK_DEPOSIT                      = 2086;
    public final static int TASK_GEN_TX_OK_WITHDRAW                     = 2087;
    public final static int TASK_GEN_TX_OK_DIRECT_VOTE                  = 2088;
    public final static int TASK_FETCH_OK_HISTORY                       = 2113;
    public final static int TASK_FETCH_OK_DEX_TICKERS                   = 2114;
    public final static int TASK_FETCH_KAVA_HARD_MODULE_ACCOUNT         = 2123;
    public final static int TASK_FETCH_NODE_INFO                        = 2126;
    public final static int TASK_FETCH_OKEX_ALL_VALIDATORS              = 2127;
    public final static int TASK_CHECK_PRIVATE_KEY                      = 2135;

    public final static int TASK_FETCH_API_ADDRESS_HISTORY              = 2300;
    public final static int TASK_FETCH_API_STAKE_HISTORY                = 2301;

    public final static int TASK_FETCH_PRICE_INFO                       = 2800;
    public final static int TASK_FETCH_PARAM_INFO                       = 2801;
    public final static int TASK_FETCH_IBC_PATHS                        = 2802;
    public final static int TASK_FETCH_IBC_TOKENS                       = 2803;
    public final static int TASK_FETCH_MINTSCAN_PROPOSAL                = 2804;
    public final static int TASK_FETCH_MINTSCAN_ASSETS                  = 2805;
    public final static int TASK_FETCH_MINTSCAN_PROPOSAL_LIST           = 2806;
    public final static int TASK_FETCH_MINTSCAN_CW20_ASSETS             = 2807;


    //gRPC
    public final static int TASK_GRPC_FETCH_BALANCE                     = 4001;
    public final static int TASK_GRPC_FETCH_BONDED_VALIDATORS           = 4002;
    public final static int TASK_GRPC_FETCH_UNBONDED_VALIDATORS         = 4003;
    public final static int TASK_GRPC_FETCH_UNBONDING_VALIDATORS        = 4004;
    public final static int TASK_GRPC_FETCH_DELEGATIONS                 = 4005;
    public final static int TASK_GRPC_FETCH_UNDELEGATIONS               = 4006;
    public final static int TASK_GRPC_FETCH_ALL_REWARDS                 = 4007;
    public final static int TASK_GRPC_FETCH_COMMISSION                  = 4008;
    public final static int TASK_GRPC_FETCH_STAKING_POOL                = 4012;
    public final static int TASK_GRPC_FETCH_VALIDATOR_INFO              = 4014;
    public final static int TASK_GRPC_FETCH_SELF_BONDING                = 4015;
    public final static int TASK_GRPC_FETCH_WITHDRAW_ADDRESS            = 4016;
    public final static int TASK_GRPC_FETCH_REDELEGATIONS_TO            = 4017;
    public final static int TASK_GRPC_FETCH_REDELEGATIONS_FROM_TO       = 4018;
    public final static int TASK_GRPC_FETCH_PROPOSAL_MY_VOTE            = 4023;
    public final static int TASK_GRPC_FETCH_NODE_INFO                   = 4024;
    public final static int TASK_GRPC_FETCH_AUTH                        = 4025;
    public final static int TASK_GRPC_FETCH_TOTAL_SUPPLY                = 4029;
    public final static int TASK_GRPC_FETCH_DESMOS_PROFILE_INFO         = 4031;
    public final static int TASK_GRPC_FETCH_BALANCE_OF_CW20             = 4033;

    public final static int TASK_GRPC_FETCH_STARNAME_FEE                = 4101;
    public final static int TASK_GRPC_FETCH_STARNAME_CONFIG             = 4102;
    public final static int TASK_GRPC_FETCH_STARNAME_ACCOUNT            = 4103;
    public final static int TASK_GRPC_FETCH_STARNAME_DOMAIN             = 4104;
    public final static int TASK_GRPC_FETCH_STARNAME_RESOLVE            = 4105;
    public final static int TASK_GRPC_FETCH_STARNAME_DOMAIN_INFO        = 4106;

    public final static int TASK_GRPC_FETCH_OSMOSIS_POOL_LIST           = 4200;
    public final static int TASK_GRPC_FETCH_OSMOSIS_POOL_INFO           = 4201;
    public final static int TASK_GRPC_FETCH_OSMOSIS_INCENTIVIZED        = 4202;
    public final static int TASK_GRPC_FETCH_OSMOSIS_ACTIVE_GAUGES       = 4203;
    public final static int TASK_GRPC_FETCH_OSMOSIS_LOCKUP_STATUS       = 4204;

    public final static int TASK_GRPC_FETCH_SIF_POOL_LIST               = 4250;
    public final static int TASK_GRPC_FETCH_SIF_POOL_INFO               = 4251;
    public final static int TASK_GRPC_FETCH_SIF_POOL_ASSET_LIST         = 4252;
    public final static int TASK_GRPC_FETCH_SIF_MY_PROVIDER             = 4253;

    public final static int TASK_GRPC_FETCH_NFTOKEN_LIST                = 4270;
    public final static int TASK_GRPC_FETCH_NFTOKEN_INFO                = 4271;

    public final static int TASK_GRPC_FETCH_PROFILE_INFO                = 4275;

    public final static int TASK_GRPC_FETCH_KAVA_PRICES                 = 4277;
    public final static int TASK_GRPC_FETCH_KAVA_PRICE_TOKEN            = 4278;
    public final static int TASK_GRPC_FETCH_KAVA_SWAP_PARAMS            = 4279;
    public final static int TASK_GRPC_FETCH_KAVA_SWAP_POOLS             = 4280;
    public final static int TASK_GRPC_FETCH_KAVA_SWAP_DEPOSITS          = 4281;
    public final static int TASK_GRPC_FETCH_KAVA_SWAP_POOLS_INFO        = 4282;
    public final static int TASK_GRPC_FETCH_KAVA_CDP_PARAMS             = 4283;
    public final static int TASK_GRPC_FETCH_KAVA_MY_CDPS                = 4284;
    public final static int TASK_GRPC_FETCH_KAVA_CDP_BY_DEPOSITOR       = 4285;
    public final static int TASK_GRPC_FETCH_KAVA_HARD_PARAMS            = 4286;
    public final static int TASK_GRPC_FETCH_KAVA_HARD_MY_DEPOSIT        = 4287;
    public final static int TASK_GRPC_FETCH_KAVA_HARD_MY_BORROW         = 4288;
    public final static int TASK_GRPC_FETCH_KAVA_HARD_INTEREST_RATE     = 4289;
    public final static int TASK_GRPC_FETCH_KAVA_HARD_MODULE_ACCOUNT    = 4290;
    public final static int TASK_GRPC_FETCH_KAVA_HARD_RESERVES          = 4291;
    public final static int TASK_GRPC_FETCH_KAVA_HARD_TOTAL_DEPOSIT     = 4292;
    public final static int TASK_GRPC_FETCH_KAVA_HARD_TOTAL_BORROW      = 4293;

    public final static int TASK_GRPC_FETCH_AUTHZ_GRANTER_LIST          = 4294;
    public final static int TASK_GRPC_FETCH_AUTHZ_GRANT_LIST            = 4295;


    public final static int TASK_GRPC_BROAD_DELEGATE                    = 4300;
    public final static int TASK_GRPC_BROAD_UNDELEGATE                  = 4301;
    public final static int TASK_GRPC_BROAD_CLAIM_REWARDS               = 4302;
    public final static int TASK_GRPC_BROAD_SEND                        = 4303;
    public final static int TASK_GRPC_BROAD_REDELEGATE                  = 4304;
    public final static int TASK_GRPC_BROAD_REINVEST                    = 4305;
    public final static int TASK_GRPC_BROAD_REWARD_ADDRESS_CHANGE       = 4306;
    public final static int TASK_GRPC_BROAD_VOTE                        = 4307;
    public final static int TASK_GRPC_GEN_TX_REGISTER_DOMAIN            = 4308;
    public final static int TASK_GRPC_GEN_TX_REGISTER_ACCOUNT           = 4309;
    public final static int TASK_GRPC_GEN_TX_DELETE_DOMAIN              = 4310;
    public final static int TASK_GRPC_GEN_TX_DELETE_ACCOUNT             = 4311;
    public final static int TASK_GRPC_GEN_TX_RENEW_DOMAIN               = 4312;
    public final static int TASK_GRPC_GEN_TX_RENEW_ACCOUNT              = 4313;
    public final static int TASK_GRPC_GEN_TX_REPLACE_STARNAME           = 4314;
    public final static int TASK_GRPC_GEN_TX_SWAP_IN                    = 4315;
    public final static int TASK_GRPC_GEN_TX_JOIN_POOL                  = 4316;
    public final static int TASK_GRPC_GEN_TX_EXIT_POOL                  = 4317;
    public final static int TASK_GRPC_GEN_TX_START_LOCK                 = 4318;
    public final static int TASK_GRPC_GEN_TX_BEGIN_UNBONDING            = 4319;
    public final static int TASK_GRPC_GEN_TX_IBC_TRANSFER               = 4324;
    public final static int TASK_GRPC_GEN_TX_SIF_SWAP                   = 4326;
    public final static int TASK_GRPC_GEN_TX_SIF_JOIN_POOL              = 4327;
    public final static int TASK_GRPC_GEN_TX_SIF_EXIT_POOL              = 4328;
    public final static int TASK_GRPC_GEN_TX_MINT_NFT                   = 4329;
    public final static int TASK_GRPC_GEN_TX_TRANSFER_NFT               = 4330;
    public final static int TASK_GRPC_GEN_TX_CREATE_PROFILE             = 4331;
    public final static int TASK_GRPC_GEN_TX_KAVA_SWAP                  = 4333;
    public final static int TASK_GRPC_GEN_TX_KAVA_DEPOSIT               = 4334;
    public final static int TASK_GRPC_GEN_TX_KAVA_WITHDRAW              = 4335;
    public final static int TASK_GRPC_GEN_TX_KAVA_CREATE_CDP            = 4336;
    public final static int TASK_GRPC_GEN_TX_KAVA_DEPOSIT_CDP           = 4337;
    public final static int TASK_GRPC_GEN_TX_KAVA_WITHDRAW_CDP          = 4338;
    public final static int TASK_GRPC_GEN_TX_KAVA_DRAW_DEBT_CDP         = 4339;
    public final static int TASK_GRPC_GEN_TX_KAVA_REPAY_CDP             = 4340;
    public final static int TASK_GRPC_GEN_TX_KAVA_DEPOSIT_HARD          = 4341;
    public final static int TASK_GRPC_GEN_TX_KAVA_WITHDRAW_HARD         = 4342;
    public final static int TASK_GRPC_GEN_TX_KAVA_BORROW_HARD           = 4343;
    public final static int TASK_GRPC_GEN_TX_KAVA_REPAY_HARD            = 4344;
    public final static int TASK_GRPC_GEN_TX_KAVA_CLAIM_INCENTIVES      = 4345;
    public final static int TASK_GRPC_GEN_TX_EXECUTE_CONTRACT           = 4346;
    public final static int TASK_GRPC_GEN_TX_AUTHZ_DELEGATE             = 4347;
    public final static int TASK_GRPC_GEN_TX_AUTHZ_UNDELEGATE           = 4348;


    public final static int TASK_GRPC_SIMULATE_DELEGATE                 = 4500;
    public final static int TASK_GRPC_SIMULATE_UNDELEGATE               = 4501;
    public final static int TASK_GRPC_SIMULATE_CLAIM_REWARDS            = 4502;
    public final static int TASK_GRPC_SIMULATE_SEND                     = 4503;
    public final static int TASK_GRPC_SIMULATE_REDELEGATE               = 4504;
    public final static int TASK_GRPC_SIMULATE_REINVEST                 = 4505;
    public final static int TASK_GRPC_SIMULATE_REWARD_ADDRESS_CHANGE    = 4506;
    public final static int TASK_GRPC_SIMULATE_VOTE                     = 4507;
    public final static int TASK_GRPC_SIMULATE_REGISTER_DOMAIN          = 4508;
    public final static int TASK_GRPC_SIMULATE_REGISTER_ACCOUNT         = 4509;
    public final static int TASK_GRPC_SIMULATE_DELETE_DOMAIN            = 4510;
    public final static int TASK_GRPC_SIMULATE_DELETE_ACCOUNT           = 4511;
    public final static int TASK_GRPC_SIMULATE_RENEW_DOMAIN             = 4512;
    public final static int TASK_GRPC_SIMULATE_RENEW_ACCOUNT            = 4513;
    public final static int TASK_GRPC_SIMULATE_REPLACE_STARNAME         = 4514;
    public final static int TASK_GRPC_SIMULATE_SWAP_IN                  = 4515;
    public final static int TASK_GRPC_SIMULATE_JOIN_POOL                = 4516;
    public final static int TASK_GRPC_SIMULATE_EXIT_POOL                = 4517;
    public final static int TASK_GRPC_SIMULATE_START_LOCK               = 4518;
    public final static int TASK_GRPC_SIMULATE_BEGIN_UNBONDING          = 4519;
    public final static int TASK_GRPC_SIMULATE_IBC_TRANSFER             = 4524;
    public final static int TASK_GRPC_SIMULATE_SIF_SWAP                 = 4527;
    public final static int TASK_GRPC_SIMULATE_SIF_JOIN_POOL            = 4528;
    public final static int TASK_GRPC_SIMULATE_SIF_WITHDRAW_POOL        = 4529;
    public final static int TASK_GRPC_SIMULATE_MINT_NFT                 = 4530;
    public final static int TASK_GRPC_SIMULATE_TRANSFER_NFT             = 4531;
    public final static int TASK_GRPC_SIMULATE_CREATE_PROFILE           = 4532;
    public final static int TASK_GRPC_SIMULATE_KAVA_SWAP                = 4533;
    public final static int TASK_GRPC_SIMULATE_KAVA_DEPOSIT             = 4534;
    public final static int TASK_GRPC_SIMULATE_KAVA_WITHDRAW            = 4535;
    public final static int TASK_GRPC_SIMULATE_KAVA_CREATE_CDP          = 4536;
    public final static int TASK_GRPC_SIMULATE_KAVA_DEPOSIT_CDP         = 4537;
    public final static int TASK_GRPC_SIMULATE_KAVA_WITHDRAW_CDP        = 4538;
    public final static int TASK_GRPC_SIMULATE_KAVA_DRAW_DEBT_CDP       = 4539;
    public final static int TASK_GRPC_SIMULATE_KAVA_REPAY_CDP           = 4540;
    public final static int TASK_GRPC_SIMULATE_KAVA_DEPOSIT_HARD        = 4541;
    public final static int TASK_GRPC_SIMULATE_KAVA_WITHDRAW_HARD       = 4542;
    public final static int TASK_GRPC_SIMULATE_KAVA_BORROW_HARD         = 4543;
    public final static int TASK_GRPC_SIMULATE_KAVA_REPAY_HARD          = 4544;
    public final static int TASK_GRPC_SIMULATE_KAVA_CLAIM_INCENTIVES    = 4545;
    public final static int TASK_GRPC_SIMULATE_EXECUTE_CONTRACT         = 4546;



    public final static String COSMOS_AUTH_TYPE_STDTX                       = "auth/StdTx";
    public final static String COSMOS_AUTH_TYPE_OKEX_ACCOUNT                = "okexchain/EthAccount";

    public final static String COSMOS_MSG_TYPE_TRANSFER                     = "cosmos-sdk/Send";
    public final static String COSMOS_MSG_TYPE_TRANSFER2                    = "cosmos-sdk/MsgSend";

    public final static String OK_MSG_TYPE_TRANSFER                         = "okexchain/token/MsgTransfer";
    public final static String OK_MSG_TYPE_MULTI_TRANSFER                   = "okexchain/token/MsgMultiTransfer";
    public final static String OK_MSG_TYPE_DEPOSIT                          = "okexchain/staking/MsgDeposit";
    public final static String OK_MSG_TYPE_WITHDRAW                         = "okexchain/staking/MsgWithdraw";
    public final static String OK_MSG_TYPE_DIRECT_VOTE                      = "okexchain/staking/MsgAddShares";

    public final static String IOV_MSG_TYPE_DELETE_ACCOUNT                  = "starname/DeleteAccount";
    public final static String IOV_MSG_TYPE_DELETE_DOMAIN                   = "starname/DeleteDomain";
    public final static String IOV_MSG_TYPE_RENEW_DOMAIN                    = "starname/RenewDomain";
    public final static String IOV_MSG_TYPE_RENEW_ACCOUNT                   = "starname/RenewAccount";


    public final static String COSMOS_KEY_TYPE_PUBLIC                       = "tendermint/PubKeySecp256k1";
    public final static String ETHERMINT_KEY_TYPE_PUBLIC                    = "ethermint/PubKeyEthSecp256k1";

    public final static String CONST_PW_PURPOSE                             = "CONST_PW_PURPOSE";
    public final static int CONST_PW_INIT                                   = 5000;
    public final static int CONST_PW_SIMPLE_CHECK                           = 5002;
    public final static int CONST_PW_TX_SIMPLE_SEND                         = 5003;
    public final static int CONST_PW_TX_SIMPLE_DELEGATE                     = 5004;
    public final static int CONST_PW_TX_SIMPLE_UNDELEGATE                   = 5005;
    public final static int CONST_PW_TX_SIMPLE_REWARD                       = 5006;
    public final static int CONST_PW_DELETE_ACCOUNT                         = 5007;
    public final static int CONST_PW_CHECK_MNEMONIC                         = 5008;
    public final static int CONST_PW_TX_SIMPLE_REDELEGATE                   = 5009;
    public final static int CONST_PW_TX_SIMPLE_CHANGE_REWARD_ADDRESS        = 5010;
    public final static int CONST_PW_TX_REINVEST                            = 5011;
    public final static int CONST_PW_TX_VOTE                                = 5012;
    public final static int CONST_PW_TX_CREATE_CDP                          = 5013;
    public final static int CONST_PW_TX_REPAY_CDP                           = 5014;
    public final static int CONST_PW_TX_DRAW_DEBT_CDP                       = 5015;
    public final static int CONST_PW_TX_DEPOSIT_CDP                         = 5016;
    public final static int CONST_PW_TX_WITHDRAW_CDP                        = 5017;
    public final static int CONST_PW_TX_CLAIM_INCENTIVE                     = 5020;
    public final static int CONST_PW_TX_OK_DEPOSIT                          = 5021;
    public final static int CONST_PW_TX_OK_WITHDRAW                         = 5022;
    public final static int CONST_PW_TX_OK_DIRECT_VOTE                      = 5023;
    public final static int CONST_PW_TX_REGISTER_DOMAIN                     = 5024;
    public final static int CONST_PW_TX_REGISTER_ACCOUNT                    = 5025;
    public final static int CONST_PW_TX_DELETE_DOMAIN                       = 5026;
    public final static int CONST_PW_TX_DELETE_ACCOUNT                      = 5027;
    public final static int CONST_PW_TX_RENEW_DOMAIN                        = 5028;
    public final static int CONST_PW_TX_RENEW_ACCOUNT                       = 5029;
    public final static int CONST_PW_TX_REPLACE_STARNAME                    = 5030;
    public final static int CONST_PW_TX_DEPOSIT_HARD                        = 5031;
    public final static int CONST_PW_TX_WITHDRAW_HARD                       = 5032;
    public final static int CONST_PW_TX_BORROW_HARD                         = 5034;
    public final static int CONST_PW_TX_REPAY_HARD                          = 5035;
    public final static int CONST_PW_TX_OSMOSIS_SWAP                        = 5036;
    public final static int CONST_PW_TX_OSMOSIS_JOIN_POOL                   = 5037;
    public final static int CONST_PW_TX_OSMOSIS_EXIT_POOL                   = 5038;
    public final static int CONST_PW_TX_OSMOSIS_EARNING                     = 5040;
    public final static int CONST_PW_TX_OSMOSIS_BEGIN_UNBONDING             = 5041;
    public final static int CONST_PW_TX_KAVA_SWAP                           = 5043;
    public final static int CONST_PW_TX_KAVA_JOIN_POOL                      = 5044;
    public final static int CONST_PW_TX_KAVA_EXIT_POOL                      = 5045;
    public final static int CONST_PW_TX_IBC_TRANSFER                        = 5049;
    public final static int CONST_PW_TX_SIF_SWAP                            = 5051;
    public final static int CONST_PW_TX_SIF_JOIN_POOL                       = 5052;
    public final static int CONST_PW_TX_SIF_EXIT_POOL                       = 5053;
    public final static int CONST_PW_CHECK_PRIVATE_KEY                      = 5054;
    public final static int CONST_PW_TX_MINT_NFT                            = 5055;
    public final static int CONST_PW_TX_SEND_NFT                            = 5056;
    public final static int CONST_PW_TX_PROFILE                             = 5057;
    public final static int CONST_PW_TX_EXECUTE_CONTRACT                    = 5059;

    public final static int CONST_PW_TX_AUTHZ_DELEGATE                      = 5060;
    public final static int CONST_PW_TX_AUTHZ_UNDELEGATE                    = 5061;
    public final static int CONST_PW_TX_AUTHZ_REDELEGATE                    = 5062;
    public final static int CONST_PW_TX_AUTHZ_SEND                          = 5063;
    public final static int CONST_PW_TX_AUTHZ_VOTE                          = 5064;
    public final static int CONST_PW_TX_AUTHZ_CLAIM_REWARD                  = 5065;
    public final static int CONST_PW_TX_AUTHZ_CLAIM_COMMISSION              = 5066;


    public final static int ERROR_CODE_UNKNOWN              = 8000;
    public final static int ERROR_CODE_NETWORK              = 8001;
    public final static int ERROR_CODE_INVALID_PASSWORD     = 8002;
    public final static int ERROR_CODE_TIMEOUT              = 8003;
    public final static int ERROR_CODE_BROADCAST            = 8004;


    //HTLC swap support Token Types
    public final static String TOKEN_HTLC_KAVA_BNB                 = "bnb";
    public final static String TOKEN_HTLC_KAVA_BTCB                = "btcb";
    public final static String TOKEN_HTLC_KAVA_XRPB                = "xrpb";
    public final static String TOKEN_HTLC_KAVA_BUSD                = "busd";

    public final static String TOKEN_HTLC_BINANCE_BNB              = "BNB";
    public final static String TOKEN_HTLC_BINANCE_BTCB             = "BTCB-1DE";
    public final static String TOKEN_HTLC_BINANCE_XRPB             = "XRP-BF2";
    public final static String TOKEN_HTLC_BINANCE_BUSD             = "BUSD-BD1";


    //TODO HardCoding!!
    public final static long COSMOS_UNBONDING_TIME = 1814400000;
    public final static long COSMOS_UNBONDING_DAY = 3;


    public final static long CONSTANT_S = 1000l;
    public final static long CONSTANT_10S = CONSTANT_S * 10;
    public final static long CONSTANT_30S = CONSTANT_S * 30;
    public final static long CONSTANT_M = CONSTANT_S * 60;
    public final static long CONSTANT_H = CONSTANT_M * 60;
    public final static long CONSTANT_D = CONSTANT_H * 24;

    public final static String BASE_GAS_AMOUNT                          = "500000";
    public final static String FEE_BNB_SEND                             = "0.000075";
    public final static String FEE_OKC_BASE                             = "0.00005";

    public final static String KAVA_SLIPPAGE                            = "30000000000000000";

    public final static String BINANCE_MAIN_BNB_DEPUTY  = "bnb1jh7uv2rm6339yue8k4mj9406k3509kr4wt5nxn";
    public final static String KAVA_MAIN_BNB_DEPUTY     = "kava1r4v2zdhdalfj2ydazallqvrus9fkphmglhn6u6";
    public final static String BINANCE_MAIN_BTCB_DEPUTY = "bnb1xz3xqf4p2ygrw9lhp5g5df4ep4nd20vsywnmpr";
    public final static String KAVA_MAIN_BTCB_DEPUTY    = "kava14qsmvzprqvhwmgql9fr0u3zv9n2qla8zhnm5pc";
    public final static String BINANCE_MAIN_XRPB_DEPUTY = "bnb15jzuvvg2kf0fka3fl2c8rx0kc3g6wkmvsqhgnh";
    public final static String KAVA_MAIN_XRPB_DEPUTY    = "kava1c0ju5vnwgpgxnrktfnkccuth9xqc68dcdpzpas";
    public final static String BINANCE_MAIN_BUSD_DEPUTY = "bnb10zq89008gmedc6rrwzdfukjk94swynd7dl97w8";
    public final static String KAVA_MAIN_BUSD_DEPUTY    = "kava1hh4x3a4suu5zyaeauvmv7ypf7w9llwlfufjmuu";

    public final static String CHAIN_IMG_URL            = "https://raw.githubusercontent.com/cosmostation/cosmostation_token_resource/master/chains/logo/";
    public final static String BINANCE_TOKEN_IMG_URL    = "https://raw.githubusercontent.com/cosmostation/cosmostation_token_resource/master/coin_image/binance/";

    public final static String EXPLORER_NOTICE_MINTSCAN = "https://notice.mintscan.io/";

    public final static String NFT_INFURA               = "https://ipfs.infura.io/ipfs/";

    public final static String EXPLORER_BASE_URL        = "https://www.mintscan.io/";
    public final static String RESOURCE_BASE_URL        = "https://raw.githubusercontent.com/cosmostation/cosmostation_token_resource/master/";
    public final static String MONIKER_URL              = RESOURCE_BASE_URL + "moniker/";
    public final static String UNKNOWN_RELAYER_URL      = RESOURCE_BASE_URL + "relayer/";
    public final static String ASSET_IMG_URL            = RESOURCE_BASE_URL + "assets/images/ethereum/";
    public final static String ASSETV2_IMG_URL          = RESOURCE_BASE_URL + "assets/images/";
    public final static String COINGECKO_URL            = "https://www.coingecko.com/en/coins/";


    //NFT Denom Default config
    public final static String STATION_NFT_DENOM        = "station";


    public final static BigDecimal DAY_SEC              = new BigDecimal("86400");
    public final static BigDecimal MONTH_SEC            = DAY_SEC.multiply(new BigDecimal("30"));
    public final static BigDecimal YEAR_SEC             = DAY_SEC.multiply(new BigDecimal("365"));

}
