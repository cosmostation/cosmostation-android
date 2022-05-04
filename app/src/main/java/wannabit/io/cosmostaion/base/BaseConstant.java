package wannabit.io.cosmostaion.base;

import java.math.BigDecimal;

public class BaseConstant {
    public final static boolean SUPPORT_MOONPAY         = true;
    public final static boolean SUPPORT_BEP3_SWAP       = true;
    public final static String LOG_TAG                  = "Cosmostation";

    public final static String DB_NAME                  = "WannaBit";
    public final static int DB_VERSION                  = 6;
    public final static String DB_TABLE_PASSWORD        = "paswd";
    public final static String DB_TABLE_ACCOUNT         = "accnt";
    public final static String DB_TABLE_BALANCE         = "balan";
    public final static String DB_TABLE_BONDING         = "bondi";
    public final static String DB_TABLE_UNBONDING       = "unbond";
    public final static String DB_TABLE_PRICE           = "price";

    public final static String PRE_USER_ID                  = "PRE_USER_ID";
    public final static String PRE_SELECTED_CHAINS          = "PRE_SELECTED_CHAINS";
    public final static String PRE_VALIDATOR_SORTING        = "PRE_VALIDATOR_SORTING";
    public final static String PRE_MY_VALIDATOR_SORTING     = "PRE_MY_VALIDATOR_SORTING";
    public final static String PRE_CURRENCY                 = "PRE_CURRENCY";
    public final static String PRE_USING_APP_LOCK           = "PRE_USING_APP_LOCK";
    public final static String PRE_USING_FINGERPRINT        = "PRE_USING_FINGERPRINT";
    public final static String PRE_APP_LOCK_TIME            = "PRE_APP_LOCK_TIME";
    public final static String PRE_APP_LOCK_LEAVE_TIME      = "PRE_APP_LOCK_LEAVE_TIME";
    public final static String PRE_FCM_TOKEN                = "PRE_FCM_TOKEN";
    public final static String PRE_EVENT_HIDE               = "PRE_EVENT_HIDE";
    public final static String PRE_USER_HIDEN_CHAINS        = "PRE_USER_HIDEN_CHAINS";
    public final static String PRE_USER_SORTED_CHAINS       = "PRE_USER_SORTED_CHAINS";
    public final static String PRE_USER_EXPENDED_CHAINS     = "PRE_USER_EXPENDED_CHAINS";


    public final static int TASK_INIT_PW                                = 2000;
    public final static int TASK_INIT_ACCOUNT                           = 2002;
    public final static int TASK_INIT_EMPTY_ACCOUNT                     = 2003;
    public final static int TASK_FETCH_ACCOUNT                          = 2004;
    public final static int TASK_FETCH_ALL_REWARDS                      = 2008;
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
    public final static int TASK_GEN_TX_HTLC_REFUND                     = 2071;
    public final static int TASK_GEN_TX_BNB_HTLC_REFUND                 = 2072;
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
    public final static int TASK_GEN_TX_KAVA_CLAIM_HARVEST              = 2109;
    public final static int TASK_FETCH_OK_HISTORY                       = 2113;
    public final static int TASK_FETCH_OK_DEX_TICKERS                   = 2114;
    public final static int TASK_FETCH_KAVA_HARD_MODULE_ACCOUNT         = 2123;
    public final static int TASK_FETCH_NODE_INFO                        = 2126;
    public final static int TASK_FETCH_OKEX_ALL_VALIDATORS              = 2127;
    public final static int TASK_CHECK_PRIVATE_KEY                      = 2135;
    public final static int TASK_INIT_PKEY_ACCOUNT                      = 2136;
    public final static int TASK_OVERRIDE_PKEY_ACCOUNT                  = 2137;

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

    public final static int TASK_HDAC_NODE_INFO                         = 2900;
    public final static int TASK_HDAC_UTXO                              = 2901;
    public final static int TASK_HDAC_TX_DETAIL                         = 2902;
    public final static int TASK_HDAC_BROAD_BURN                        = 2903;
    public final static int TASK_RIZON_SWAP_STATUS                      = 2904;


    //gRPC
    public final static int TASK_GRPC_FETCH_BALANCE                     = 4001;
    public final static int TASK_GRPC_FETCH_BONDED_VALIDATORS           = 4002;
    public final static int TASK_GRPC_FETCH_UNBONDED_VALIDATORS         = 4003;
    public final static int TASK_GRPC_FETCH_UNBONDING_VALIDATORS        = 4004;
    public final static int TASK_GRPC_FETCH_DELEGATIONS                 = 4005;
    public final static int TASK_GRPC_FETCH_UNDELEGATIONS               = 4006;
    public final static int TASK_GRPC_FETCH_ALL_REWARDS                 = 4007;
    public final static int TASK_GRPC_FETCH_IRIS_PARAM_MINT             = 4009;
    public final static int TASK_GRPC_FETCH_STAKING_POOL                = 4012;
    public final static int TASK_GRPC_FETCH_IRIS_TOKEN_LIST             = 4013;
    public final static int TASK_GRPC_FETCH_VALIDATOR_INFO              = 4014;
    public final static int TASK_GRPC_FETCH_SELF_BONDING                = 4015;
    public final static int TASK_GRPC_FETCH_WITHDRAW_ADDRESS            = 4016;
    public final static int TASK_GRPC_FETCH_REDELEGATIONS_TO            = 4017;
    public final static int TASK_GRPC_FETCH_REDELEGATIONS_FROM_TO       = 4018;
    public final static int TASK_GRPC_FETCH_PROPOSAL_MY_VOTE            = 4023;
    public final static int TASK_GRPC_FETCH_NODE_INFO                   = 4024;
    public final static int TASK_GRPC_FETCH_AUTH                        = 4025;
    public final static int TASK_GRPC_FETCH_GRAVITY_POOL_LIST           = 4026;
    public final static int TASK_GRPC_FETCH_GRAVITY_PARAM               = 4027;
    public final static int TASK_GRPC_FETCH_GRAVITY_MANAGER             = 4028;
    public final static int TASK_GRPC_FETCH_TOTAL_SUPPLY                = 4029;
    public final static int TASK_GRPC_FETCH_GRAVITY_POOL_INFO           = 4030;
    public final static int TASK_GRPC_FETCH_DESMOS_PROFILE_INFO         = 4031;
    public final static int TASK_GRPC_FETCH_SUPPLY_OF_INFO              = 4032;
    public final static int TASK_GRPC_FETCH_BALANCE_OF_CW20             = 4033;

    public final static int TASK_GRPC_FETCH_BAND_ORACLE_STATUS          = 4100;
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
    public final static int TASK_GRPC_GEN_TX_GRAVITY_SWAP               = 4321;
    public final static int TASK_GRPC_GEN_TX_GRAVITY_JOIN_POOL          = 4322;
    public final static int TASK_GRPC_GEN_TX_GRAVITY_EXIT_POOL          = 4323;
    public final static int TASK_GRPC_GEN_TX_IBC_TRANSFER               = 4324;
    public final static int TASK_GRPC_GEN_TX_SIF_CLAIM_INCENTIVE        = 4325;
    public final static int TASK_GRPC_GEN_TX_SIF_SWAP                   = 4326;
    public final static int TASK_GRPC_GEN_TX_SIF_JOIN_POOL              = 4327;
    public final static int TASK_GRPC_GEN_TX_SIF_EXIT_POOL              = 4328;
    public final static int TASK_GRPC_GEN_TX_MINT_NFT                   = 4329;
    public final static int TASK_GRPC_GEN_TX_TRANSFER_NFT               = 4330;
    public final static int TASK_GRPC_GEN_TX_CREATE_PROFILE             = 4331;
    public final static int TASK_GRPC_GEN_TX_LINK_ACCOUNT               = 4332;
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
    public final static int TASK_GRPC_SIMULATE_GRAVITY_SWAP             = 4521;
    public final static int TASK_GRPC_SIMULATE_GRAVITY_JOIN_POOL        = 4522;
    public final static int TASK_GRPC_SIMULATE_GRAVITY_EXIT_POOL        = 4523;
    public final static int TASK_GRPC_SIMULATE_IBC_TRANSFER             = 4524;
    public final static int TASK_GRPC_SIMULATE_SIF_CLAIM_INCENTIVE      = 4526;
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

    public final static String COSMOS_AUTH_TYPE_DELAYEDACCOUNT              = "cosmos-sdk/DelayedVestingAccount";
    public final static String COSMOS_AUTH_TYPE_VESTING_ACCOUNT             = "cosmos-sdk/ValidatorVestingAccount";
    public final static String COSMOS_AUTH_TYPE_P_VESTING_ACCOUNT           = "cosmos-sdk/PeriodicVestingAccount";
    public final static String COSMOS_AUTH_TYPE_C_VESTING_ACCOUNT           = "cosmos-sdk/ContinuousVestingAccount";
    public final static String COSMOS_AUTH_TYPE_D_VESTING_ACCOUNT           = "cosmos-sdk/DelayedVestingAccount";
    public final static String COSMOS_AUTH_TYPE_ACCOUNT                     = "cosmos-sdk/Account";
    public final static String COSMOS_AUTH_TYPE_ACCOUNT_LEGACY              = "auth/Account";
    public final static String COSMOS_AUTH_TYPE_CERTIK_MANUAL               = "auth/ManualVestingAccount";
    public final static String COSMOS_AUTH_TYPE_OKEX_ACCOUNT                = "okexchain/EthAccount";

    public final static String COSMOS_MSG_TYPE_TRANSFER                     = "cosmos-sdk/Send";
    public final static String COSMOS_MSG_TYPE_TRANSFER2                    = "cosmos-sdk/MsgSend";
    public final static String COSMOS_MSG_TYPE_TRANSFER3                    = "cosmos-sdk/MsgMultiSend";
    public final static String COSMOS_MSG_TYPE_DELEGATE                     = "cosmos-sdk/MsgDelegate";
    public final static String COSMOS_MSG_TYPE_UNDELEGATE                   = "cosmos-sdk/Undelegate";
    public final static String COSMOS_MSG_TYPE_UNDELEGATE2                  = "cosmos-sdk/MsgUndelegate";
    public final static String COSMOS_MSG_TYPE_REDELEGATE                   = "cosmos-sdk/BeginRedelegate";
    public final static String COSMOS_MSG_TYPE_REDELEGATE2                  = "cosmos-sdk/MsgBeginRedelegate";
    public final static String COSMOS_MSG_TYPE_WITHDRAW_DEL                 = "cosmos-sdk/MsgWithdrawDelegationReward";
    public final static String COSMOS_MSG_TYPE_WITHDRAW_VAL                 = "cosmos-sdk/MsgWithdrawValidatorCommission";
    public final static String COSMOS_MSG_TYPE_WITHDRAW_MIDIFY              = "cosmos-sdk/MsgModifyWithdrawAddress";
    public final static String COSMOS_MSG_TYPE_VOTE                         = "cosmos-sdk/MsgVote";
    public final static String COSMOS_MSG_TYPE_SUBMIT_PROPOSAL              = "cosmos-sdk/MsgSubmitProposal";
    public final static String COSMOS_MSG_TYPE_DEPOSIT                      = "cosmos-sdk/MsgDeposit";
    public final static String COSMOS_MSG_TYPE_CREATE_VALIDATOR             = "cosmos-sdk/MsgCreateValidator";
    public final static String COSMOS_MSG_TYPE_EDIT_VALIDATOR               = "cosmos-sdk/MsgEditValidator";
    public final static String COSMOS_MSG_TYPE_UNJAIL                       = "cosmos-sdk/MsgUnjail";

    public final static String KAVA_MSG_TYPE_POST_PRICE                     = "/kava.pricefeed.v1beta1.MsgPostPrice";
    public final static String KAVA_MSG_TYPE_CREATE_CDP                     = "/kava.cdp.v1beta1.MsgCreateCDP";
    public final static String KAVA_MSG_TYPE_DEPOSIT_CDP                    = "/kava.cdp.v1beta1.MsgDeposit";
    public final static String KAVA_MSG_TYPE_WITHDRAW_CDP                   = "/kava.cdp.v1beta1.MsgWithdraw";
    public final static String KAVA_MSG_TYPE_DRAWDEBT_CDP                   = "/kava.cdp.v1beta1.MsgDrawDebt";
    public final static String KAVA_MSG_TYPE_REPAYDEBT_CDP                  = "/kava.cdp.v1beta1.MsgRepayDebt";
    public final static String KAVA_MSG_TYPE_LIQUIDATE_CDP                  = "/kava.cdp.v1beta1.MsgLiquidate";
    public final static String KAVA_MSG_TYPE_BEP3_CREATE_SWAP               = "/kava.bep3.v1beta1.MsgCreateAtomicSwap";
    public final static String KAVA_MSG_TYPE_BEP3_CLAM_SWAP                 = "/kava.bep3.v1beta1.MsgClaimAtomicSwap";
    public final static String KAVA_MSG_TYPE_BEP3_REFUND_SWAP               = "/kava.bep3.v1beta1.MsgRefundAtomicSwap";
    public final static String KAVA_MSG_TYPE_INCENTIVE_REWARD               = "incentive/MsgClaimReward";
    public final static String KAVA_MSG_TYPE_USDX_MINT_INCENTIVE            = "/kava.incentive.v1beta1.MsgClaimUSDXMintingReward";
    public final static String KAVA_MSG_TYPE_CLAIM_HARD_INCENTIVE           = "/kava.incentive.v1beta1.MsgClaimHardReward";
    public final static String KAVA_MSG_TYPE_DELEGATOR_INCENTIVE            = "/kava.incentive.v1beta1.MsgClaimDelegatorReward";
    public final static String KAVA_MSG_TYPE_SWAP_INCENTIVE                 = "/kava.incentive.v1beta1.MsgClaimSwapReward";
    public final static String KAVA_MSG_TYPE_CLAIM_HAVEST                   = "harvest/MsgClaimReward";
    public final static String KAVA_MSG_TYPE_DEPOSIT_HARD                   = "/kava.hard.v1beta1.MsgDeposit";
    public final static String KAVA_MSG_TYPE_WITHDRAW_HARD                  = "/kava.hard.v1beta1.MsgWithdraw";
    public final static String KAVA_MSG_TYPE_BORROW_HARD                    = "/kava.hard.v1beta1.MsgBorrow";
    public final static String KAVA_MSG_TYPE_REPAY_HARD                     = "/kava.hard.v1beta1.MsgRepay";
    public final static String KAVA_MSG_TYPE_LIQUIDATE_HARD                 = "hard/MsgLiquidate";
    public final static String KAVA_MSG_TYPE_SWAP_TOKEN                     = "/kava.swap.v1beta1.MsgSwapExactForTokens";
    public final static String KAVA_MSG_TYPE_SWAP_TOKEN2                    = "/kava.swap.v1beta1.MsgSwapForExactTokens";
    public final static String KAVA_MSG_TYPE_DEPOSIT                        = "/kava.swap.v1beta1.MsgDeposit";
    public final static String KAVA_MSG_TYPE_WITHDRAW                       = "/kava.swap.v1beta1.MsgWithdraw";


    public final static String BNB_MSG_TYPE_HTLC                            = "tokens/HTLTMsg";
    public final static String BNB_MSG_TYPE_HTLC_CLIAM                      = "tokens/ClaimHTLTMsg";
    public final static String BNB_MSG_TYPE_HTLC_REFUND                     = "tokens/RefundHTLTMsg";


    public final static String OK_MSG_TYPE_TRANSFER                         = "okexchain/token/MsgTransfer";
    public final static String OK_MSG_TYPE_MULTI_TRANSFER                   = "okexchain/token/MsgMultiTransfer";
    public final static String OK_MSG_TYPE_DEPOSIT                          = "okexchain/staking/MsgDeposit";
    public final static String OK_MSG_TYPE_WITHDRAW                         = "okexchain/staking/MsgWithdraw";
    public final static String OK_MSG_TYPE_DIRECT_VOTE                      = "okexchain/staking/MsgAddShares";



    public final static String IOV_MSG_TYPE_REGISTER_DOMAIN                 = "starname/RegisterDomain";
    public final static String IOV_MSG_TYPE_REGISTER_ACCOUNT                = "starname/RegisterAccount";
    public final static String IOV_MSG_TYPE_DELETE_ACCOUNT                  = "starname/DeleteAccount";
    public final static String IOV_MSG_TYPE_DELETE_DOMAIN                   = "starname/DeleteDomain";
    public final static String IOV_MSG_TYPE_REPLACE_ACCOUNT_RESOURCE        = "starname/ReplaceAccountResources";
    public final static String IOV_MSG_TYPE_RENEW_DOMAIN                    = "starname/RenewDomain";
    public final static String IOV_MSG_TYPE_RENEW_ACCOUNT                   = "starname/RenewAccount";


    public final static String CERTIK_MSG_TYPE_TRANSFER                     = "bank/MsgSend";


    public final static String COSMOS_KEY_TYPE_PUBLIC                       = "tendermint/PubKeySecp256k1";
    public final static String ETHERMINT_KEY_TYPE_PUBLIC                    = "ethermint/PubKeyEthSecp256k1";
    public final static String IOV_KEY_TYPE                                 = "sigs/ed25519/";

    public final static String LIQUIDITY_MSG_TYPE_SWAP                      = "liquidity/MsgSwapWithinBatch";

    public final static String CONST_PW_PURPOSE                             = "CONST_PW_PURPOSE";
    public final static int CONST_PW_INIT                                   = 5000;
    public final static int CONST_PW_UNLOUCK                                = 5001;
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
    public final static int CONST_PW_TX_HTLS_REFUND                         = 5019;
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
    public final static int CONST_PW_TX_CLAIM_HARVEST_REWARD                = 5033;
    public final static int CONST_PW_TX_BORROW_HARD                         = 5034;
    public final static int CONST_PW_TX_REPAY_HARD                          = 5035;
    public final static int CONST_PW_TX_OSMOSIS_SWAP                        = 5036;
    public final static int CONST_PW_TX_OSMOSIS_JOIN_POOL                   = 5037;
    public final static int CONST_PW_TX_OSMOSIS_EXIT_POOL                   = 5038;
    public final static int CONST_PW_TX_RIZON_SWAP                          = 5039;
    public final static int CONST_PW_TX_OSMOSIS_EARNING                     = 5040;
    public final static int CONST_PW_TX_OSMOSIS_BEGIN_UNBONDING             = 5041;
    public final static int CONST_PW_TX_OSMOSIS_UNLOCK                      = 5042;
    public final static int CONST_PW_TX_KAVA_SWAP                           = 5043;
    public final static int CONST_PW_TX_KAVA_JOIN_POOL                      = 5044;
    public final static int CONST_PW_TX_KAVA_EXIT_POOL                      = 5045;
    public final static int CONST_PW_TX_GDEX_SWAP                           = 5046;
    public final static int CONST_PW_TX_GDEX_DEPOSIT                        = 5047;
    public final static int CONST_PW_TX_GDEX_WITHDRAW                       = 5048;
    public final static int CONST_PW_TX_IBC_TRANSFER                        = 5049;
    public final static int CONST_PW_TX_SIF_CLAIM_INCENTIVE                 = 5050;
    public final static int CONST_PW_TX_SIF_SWAP                            = 5051;
    public final static int CONST_PW_TX_SIF_JOIN_POOL                       = 5052;
    public final static int CONST_PW_TX_SIF_EXIT_POOL                       = 5053;
    public final static int CONST_PW_CHECK_PRIVATE_KEY                      = 5054;
    public final static int CONST_PW_TX_MINT_NFT                            = 5055;
    public final static int CONST_PW_TX_SEND_NFT                            = 5056;
    public final static int CONST_PW_TX_PROFILE                             = 5057;
    public final static int CONST_PW_TX_LINK_ACCOUNT                        = 5058;
    public final static int CONST_PW_TX_EXECUTE_CONTRACT                    = 5059;

    public final static int ERROR_CODE_UNKNOWN              = 8000;
    public final static int ERROR_CODE_NETWORK              = 8001;
    public final static int ERROR_CODE_INVALID_PASSWORD     = 8002;
    public final static int ERROR_CODE_TIMEOUT              = 8003;
    public final static int ERROR_CODE_BROADCAST            = 8004;


    public final static String TOKEN_ATOM           = "uatom";
    public final static String TOKEN_IRIS           = "uiris";
    public final static String TOKEN_IRIS_ATTO      = "iris-atto";
    public final static String TOKEN_BNB            = "BNB";
    public final static String TOKEN_KAVA           = "ukava";
    public final static String TOKEN_HARD           = "hard";
    public final static String TOKEN_USDX           = "usdx";
    public final static String TOKEN_SWP            = "swp";
    public final static String TOKEN_IOV            = "uiov";
    public final static String TOKEN_CERTIK         = "uctk";
    public final static String TOKEN_BAND           = "uband";
    public final static String TOKEN_AKASH          = "uakt";
    public final static String TOKEN_SECRET         = "uscrt";
    public final static String TOKEN_OK             = "okt";
    public final static String TOKEN_OK_OKB         = "okb";
    public final static String TOKEN_XPRT           = "uxprt";
    public final static String TOKEN_DVPN           = "udvpn";
    public final static String TOKEN_FET            = "afet";
    public final static String TOKEN_CRO            = "basecro";
    public final static String TOKEN_SIF            = "rowan";
    public final static String TOKEN_KI             = "uxki";
    public final static String TOKEN_RIZON          = "uatolo";
    public final static String TOKEN_MEDI           = "umed";
    public final static String TOKEN_ALTHEA         = "ualtg";
    public final static String TOKEN_OSMOSIS        = "uosmo";
    public final static String TOKEN_ION            = "uion";
    public final static String TOKEN_UMEE           = "uumee";
    public final static String TOKEN_NGM            = "ungm";
    public final static String TOKEN_AXELAR         = "uaxl";
    public final static String TOKEN_JUNO           = "ujuno";
    public final static String TOKEN_REGEN          = "uregen";
    public final static String TOKEN_BITCANNA       = "ubcna";
    public final static String TOKEN_STARGAZE       = "ustars";
    public final static String TOKEN_GRABRIDGE      = "ugraviton";
    public final static String TOKEN_COMDEX         = "ucmdx";
    public final static String TOKEN_INJ            = "inj";
    public final static String TOKEN_BITSONG        = "ubtsg";
    public final static String TOKEN_DESMOS         = "udsm";
    public final static String TOKEN_LUM            = "ulum";
    public final static String TOKEN_CHIHUAHUA      = "uhuahua";
    public final static String TOKEN_DARC           = "udarc";
    public final static String TOKEN_EVMOS          = "aevmos";
    public final static String TOKEN_CUDOS          = "acudos";
    public final static String TOKEN_HASH           = "nhash";
    public final static String TOKEN_CRBRUS         = "ucrbrus";
    public final static String TOKEN_FLIX           = "uflix";
    public final static String TOKEN_CRE            = "ucre";
    public final static String TOKEN_BCRE           = "ubcre";
    public final static String TOKEN_MANTLE         = "umntl";


    public final static String TOKEN_EMONEY_EUR     = "eeur";
    public final static String TOKEN_EMONEY_CHF     = "echf";
    public final static String TOKEN_EMONEY_DKK     = "edkk";
    public final static String TOKEN_EMONEY_NOK     = "enok";
    public final static String TOKEN_EMONEY_SEK     = "esek";

    public final static String TOKEN_IOV_TEST       = "uvoi";
    public final static String TOKEN_COSMOS_TEST    = "umuon";
    public final static String TOKEN_IRIS_TEST      = "ubif";
    public final static String TOKEN_CRESCENT_TEST  = "ucre";
    public final static String TOKEN_BCRESCENT_TEST = "ubcre";


    //HTLC swap support Token Types
    public final static String  TOKEN_HTLC_BINANCE_BNB               = "BNB";
    public final static String  TOKEN_HTLC_KAVA_BNB                  = "bnb";
    public final static String  TOKEN_HTLC_BINANCE_BTCB             = "BTCB-1DE";
    public final static String  TOKEN_HTLC_KAVA_BTCB                = "btcb";
    public final static String  TOKEN_HTLC_BINANCE_XRPB             = "XRP-BF2";
    public final static String  TOKEN_HTLC_KAVA_XRPB                = "xrpb";
    public final static String  TOKEN_HTLC_BINANCE_BUSD             = "BUSD-BD1";
    public final static String  TOKEN_HTLC_KAVA_BUSD                = "busd";

    public final static String TOKEN_HTLC_BINANCE_TEST_BNB          = "BNB";
    public final static String TOKEN_HTLC_BINANCE_TEST_BTC          = "BTCB-101";
    public final static String TOKEN_HTLC_KAVA_TEST_BNB             = "bnb";
    public final static String TOKEN_HTLC_KAVA_TEST_BTC             = "btcb";


    //TODO HardCoding!!
    public final static long COSMOS_UNBONDING_TIME = 1814400000;
    public final static long COSMOS_UNBONDING_DAY = 3;


    public final static String KEY_PATH                     = "m/44'/118'/0'/0/";
    public final static String KEY_BNB_PATH                 = "m/44'/714'/0'/0/";
    public final static String KEY_IOV_PATH                 = "m/44'/234'/0'/0/";
    public final static String KEY_NEW_KAVA_PATH            = "m/44'/459'/0'/0/";
    public final static String KEY_BAND_PATH                = "m/44'/494'/0'/0/";
    public final static String KEY_NEW_OK_PATH              = "m/44'/996'/0'/0/";
    public final static String KEY_NEW_SECRET_PATH          = "m/44'/529'/0'/0/";
    public final static String KEY_PERSIS_PATH              = "m/44'/750'/0'/0/";
    public final static String KEY_CRYPTO_PATH              = "m/44'/394'/0'/0/";
    public final static String KEY_MEDI_PATH                = "m/44'/371'/0'/0/";
    public final static String KEY_ALTHEA_PATH              = "m/44'/60'/0'/0/";
    public final static String KEY_FETCH_BASE_PATH          = "m/44'/118'/0'/0/";
    public final static String KEY_ETH_NON_LEDGER_PATH      = "m/44'/60'/0'/0/";
    public final static String KEY_ETH_LEDGER_LIVE_PATH_1   = "m/44'/60'/";
    public final static String KEY_ETH_LEDGER_LIVE_PATH_2   = "'/0/0";
    public final static String KEY_ETH_LEDGER_LEGACY_PATH   = "m/44'/60'/0'/";
    public final static String KEY_ETH_PATH                 = "m/44'/60'/0'/0/";
    public final static String KEY_BITSONG_PATH             = "m/44'/639'/0'/0/";
    public final static String KEY_DESMOS_PATH              = "m/44'/852'/0'/0/";
    public final static String KEY_LUM_PATH                 = "m/44'/880'/0'/0/";
    public final static String KEY_PROVENANCE_PATH          = "m/44'/505'/0'/0/";
    public final static String characterFilter              = "[^\\p{L}\\p{M}\\p{N}\\p{P}\\p{Z}\\p{Cf}\\p{Cs}\\s]";


    public final static long CONSTANT_S = 1000l;
    public final static long CONSTANT_10S = CONSTANT_S * 10;
    public final static long CONSTANT_30S = CONSTANT_S * 30;
    public final static long CONSTANT_M = CONSTANT_S * 60;
    public final static long CONSTANT_H = CONSTANT_M * 60;
    public final static long CONSTANT_D = CONSTANT_H * 24;


    public final static int MEMO_ATOM = 255;
    public final static int MEMO_BNB = 100;





    public final static String FEE_BNB_SEND                             = "0.000075";

    public final static String KAVA_GAS_AMOUNT_SEND                     = "400000";
    public final static String KAVA_GAS_AMOUNT_STAKE                    = "800000";
    public final static String KAVA_GAS_AMOUNT_REINVEST                 = "800000";
    public final static String KAVA_GAS_AMOUNT_REDELEGATE               = "800000";
    public final static String KAVA_GAS_AMOUNT_VOTE                     = "300000";
    public final static String KAVA_GAS_AMOUNT_CLAIM_INCENTIVE          = "2000000";
    public final static String KAVA_GAS_AMOUNT_CDP                      = "2000000";
    public final static String KAVA_GAS_AMOUNT_HARD_POOL                = "800000";
    public final static String KAVA_GAS_AMOUNT_BEP3                     = "500000";
    public final static String KAVA_GAS_AMOUNT_SWAP                     = "800000";
    public final static String KAVA_GAS_AMOUNT_JOIN_POOL                = "800000";
    public final static String KAVA_GAS_AMOUNT_EXIT_POOL                = "800000";
    public final static String KAVA_GAS_AMOUNT_IBC_SEND                 = "500000";
    public final static String KAVA_GAS_AMOUNT_REWARD_ADDRESS_CHANGE    = "100000";

    public final static String BAND_GAS_AMOUNT_SEND                     = "100000";
    public final static String BAND_GAS_AMOUNT_STAKE                    = "200000";
    public final static String BAND_GAS_AMOUNT_REDELEGATE               = "240000";
    public final static String BAND_GAS_AMOUNT_REINVEST                 = "220000";
    public final static String BAND_GAS_AMOUNT_ADDRESS_CHANGE           = "100000";
    public final static String BAND_GAS_AMOUNT_VOTE                     = "100000";
    public final static String BAND_GAS_AMOUNT_IBC_SEND                 = "500000";

    public final static String IOV_GAS_AMOUNT_SEND                      = "100000";
    public final static String IOV_GAS_AMOUNT_STAKE                     = "200000";
    public final static String IOV_GAS_AMOUNT_REDELEGATE                = "300000";
    public final static String IOV_GAS_AMOUNT_REINVEST                  = "300000";
    public final static String IOV_GAS_AMOUNT_LOW                       = "100000";
    public final static String IOV_GAS_AMOUNT_REGISTER                  = "300000";
    public final static String IOV_GAS_AMOUNT_DELETE                    = "150000";
    public final static String IOV_GAS_AMOUNT_RENEW                     = "300000";
    public final static String IOV_GAS_AMOUNT_REPLACE                   = "300000";
    public final static String IOV_GAS_AMOUNT_IBC_SEND                  = "500000";

    public final static String OK_GAS_RATE_AVERAGE                      = "0.0000000001";
    public final static String OK_GAS_AMOUNT_SEND                       = "200000";
    public final static String OK_GAS_AMOUNT_STAKE                      = "200000";
    public final static String OK_GAS_AMOUNT_STAKE_MUX                  = "20000";
    public final static String OK_GAS_AMOUNT_VOTE                       = "200000";
    public final static String OK_GAS_AMOUNT_VOTE_MUX                   = "50000";

    public final static String CERTIK_GAS_AMOUNT_SEND                   = "100000";
    public final static String CERTIK_GAS_AMOUNT_STAKE                  = "200000";
    public final static String CERTIK_GAS_AMOUNT_REDELEGATE             = "300000";
    public final static String CERTIK_GAS_AMOUNT_REINVEST               = "300000";
    public final static String CERTIK_GAS_AMOUNT_REWARD_ADDRESS_CHANGE  = "100000";
    public final static String CERTIK_GAS_AMOUNT_VOTE                   = "100000";
    public final static String CERTIK_GAS_AMOUNT_IBC_SEND               = "500000";

    public final static String SECRET_GAS_AMOUNT_SEND                   = "80000";
    public final static String SECRET_GAS_AMOUNT_STAKE                  = "200000";
    public final static String SECRET_GAS_AMOUNT_REDELEGATE             = "300000";
    public final static String SECRET_GAS_AMOUNT_REINVEST               = "350000";
    public final static String SECRET_GAS_AMOUNT_REWARD_ADDRESS_CHANGE  = "80000";
    public final static String SECRET_GAS_AMOUNT_VOTE                   = "100000";
    public final static String SECRET_GAS_AMOUNT_IBC_SEND               = "500000";

    public final static String SIF_GAS_AMOUNT_CLAIM_INCENTIVE           = "250000";
    public final static String SIF_GAS_AMOUNT_DEX                       = "250000";

    public final static String GAS_AMOUNT_SEND                          = "100000";
    public final static String GAS_AMOUNT_STAKE                         = "200000";
    public final static String GAS_AMOUNT_REDELEGATE                    = "300000";
    public final static String GAS_AMOUNT_REINVEST                      = "350000";
    public final static String GAS_AMOUNT_REWARD_ADDRESS_CHANGE         = "100000";
    public final static String GAS_AMOUNT_VOTE                          = "100000";
    public final static String GAS_AMOUNT_IBC_SEND                      = "500000";

    public final static String OSMOSIS_GAS_AMOUNT_SEND                  = "100000";
    public final static String OSMOSIS_GAS_AMOUNT_STAKE                 = "200000";
    public final static String OSMOSIS_GAS_AMOUNT_REDELEGATE            = "300000";
    public final static String OSMOSIS_GAS_AMOUNT_REINVEST              = "350000";
    public final static String OSMOSIS_GAS_AMOUNT_LOW                   = "100000";
    public final static String OSMOSIS_GAS_AMOUNT_POOL                  = "1500000";
    public final static String OSMOSIS_GAS_AMOUNT_SWAP                  = "500000";
    public final static String OSMOSIS_GAS_AMOUNT_LOCK                  = "1500000";
    public final static String OSMOSIS_GAS_AMOUNT_BEGIN_UNBONDING       = "1500000";
    public final static String OSMOSIS_GAS_AMOUNT_UNLOCK                = "1500000";
    public final static String OSMOSIS_GAS_AMOUNT_IBC_SEND              = "500000";

    public final static String COSMOS_GAS_AMOUNT_SWAP                   = "200000";
    public final static String COSMOS_GAS_AMOUNT_JOIN_POOL              = "300000";
    public final static String COSMOS_GAS_AMOUNT_EXIT_POOL              = "300000";
    public final static String COSMOS_GAS_AMOUNT_IBC_SEND               = "500000";

    public final static String COSMOS_GAS_AMOUNT_EXECUTE_CONTRACT       = "500000";

    public final static String COSMOS_GAS_RATE_TINY                     = "0.00025";
    public final static String COSMOS_GAS_RATE_LOW                      = "0.0025";
    public final static String COSMOS_GAS_RATE_AVERAGE                  = "0.025";

    public final static String IRIS_GAS_RATE_TINY                       = "0.002";
    public final static String IRIS_GAS_RATE_LOW                        = "0.02";
    public final static String IRIS_GAS_RATE_AVERAGE                    = "0.2";

    public final static String SENTINEL_GAS_RATE_TINY                   = "0.01";
    public final static String SENTINEL_GAS_RATE_LOW                    = "0.1";
    public final static String SENTINEL_GAS_RATE_AVERAGE                = "0.1";

    public final static String PERSIS_GAS_RATE_TINY                     = "0.000";
    public final static String PERSIS_GAS_RATE_LOW                      = "0.000";
    public final static String PERSIS_GAS_RATE_AVERAGE                  = "0.000";

    public final static String CRYPTO_GAS_RATE_TINY                     = "0.025";
    public final static String CRYPTO_GAS_RATE_LOW                      = "0.05";
    public final static String CRYPTO_GAS_RATE_AVERAGE                  = "0.075";

    public final static String OSMOSIS_GAS_RATE_TINY                    = "0.000";
    public final static String OSMOSIS_GAS_RATE_LOW                     = "0.0025";
    public final static String OSMOSIS_GAS_RATE_AVERAGE                 = "0.025";

    public final static String SIF_GAS_RATE_TINY                        = "0.50";
    public final static String SIF_GAS_RATE_LOW                         = "0.50";
    public final static String SIF_GAS_RATE_AVERAGE                     = "0.50";

    public final static String BAND_GAS_RATE_TINY                       = "0.000";
    public final static String BAND_GAS_RATE_LOW                        = "0.0025";
    public final static String BAND_GAS_RATE_AVERAGE                    = "0.025";

    public final static String STARNAME_GAS_RATE_TINY                   = "0.10";
    public final static String STARNAME_GAS_RATE_LOW                    = "1.00";
    public final static String STARNAME_GAS_RATE_AVERAGE                = "1.00";

    public final static String MEDI_GAS_RATE_TINY                       = "5.00";
    public final static String MEDI_GAS_RATE_LOW                        = "5.00";
    public final static String MEDI_GAS_RATE_AVERAGE                    = "5.00";

    public final static String CERTIK_GAS_RATE_TINY                     = "0.05";
    public final static String CERTIK_GAS_RATE_LOW                      = "0.05";
    public final static String CERTIK_GAS_RATE_AVERAGE                  = "0.05";

    public final static String EMONEY_GAS_RATE_TINY                     = "0.10";
    public final static String EMONEY_GAS_RATE_LOW                      = "0.30";
    public final static String EMONEY_GAS_RATE_AVERAGE                  = "1.00";

    public final static String FETCH_GAS_RATE_TINY                      = "0.00";
    public final static String FETCH_GAS_RATE_LOW                       = "0.00";
    public final static String FETCH_GAS_RATE_AVERAGE                   = "0.00";

    public final static String BITCANNA_GAS_RATE_TINY                   = "0.025";
    public final static String BITCANNA_GAS_RATE_LOW                    = "0.025";
    public final static String BITCANNA_GAS_RATE_AVERAGE                = "0.025";

    public final static String STARGAZE_GAS_RATE_TINY                   = "0.00";
    public final static String STARGAZE_GAS_RATE_LOW                    = "0.00";
    public final static String STARGAZE_GAS_RATE_AVERAGE                = "0.00";

    public final static String KI_GAS_RATE_TINY                         = "0.025";
    public final static String KI_GAS_RATE_LOW                          = "0.025";
    public final static String KI_GAS_RATE_AVERAGE                      = "0.025";

    public final static String COMDEX_GAS_RATE_TINY                     = "0.025";
    public final static String COMDEX_GAS_RATE_LOW                      = "0.025";
    public final static String COMDEX_GAS_RATE_AVERAGE                  = "0.025";

    public final static String SECRET_GAS_RATE_TINY                     = "0.25";
    public final static String SECRET_GAS_RATE_LOW                      = "0.25";
    public final static String SECRET_GAS_RATE_AVERAGE                  = "0.25";

    public final static String BITSONG_GAS_RATE_TINY                    = "0.025";
    public final static String BITSONG_GAS_RATE_LOW                     = "0.025";
    public final static String BITSONG_GAS_RATE_AVERAGE                 = "0.025";

    public final static String DESMOS_GAS_RATE_TINY                     = "0.001";
    public final static String DESMOS_GAS_RATE_LOW                      = "0.010";
    public final static String DESMOS_GAS_RATE_AVERAGE                  = "0.025";

    public final static String LUM_GAS_RATE_TINY                        = "0.001";
    public final static String LUM_GAS_RATE_LOW                         = "0.001";
    public final static String LUM_GAS_RATE_AVERAGE                     = "0.001";

    public final static String INJ_GAS_RATE_TINY                        = "500000000.00";
    public final static String INJ_GAS_RATE_LOW                         = "500000000.00";
    public final static String INJ_GAS_RATE_AVERAGE                     = "500000000.00";

    public final static String GRAV_GAS_RATE_TINY                       = "0.00";
    public final static String GRAV_GAS_RATE_LOW                        = "0.00";
    public final static String GRAV_GAS_RATE_AVERAGE                    = "0.00";

    public final static String CHIHUAHUA_GAS_RATE_TINY                  = "0.00035";
    public final static String CHIHUAHUA_GAS_RATE_LOW                   = "0.0035";
    public final static String CHIHUAHUA_GAS_RATE_AVERAGE               = "0.035";

    public final static String AXELAR_GAS_RATE_TINY                     = "0.05";
    public final static String AXELAR_GAS_RATE_LOW                      = "0.05";
    public final static String AXELAR_GAS_RATE_AVERAGE                  = "0.05";

    public final static String KAVA_GAS_RATE_TINY                       = "0.00";
    public final static String KAVA_GAS_RATE_LOW                        = "0.0025";
    public final static String KAVA_GAS_RATE_AVERAGE                    = "0.025";

    public final static String JUNO_GAS_RATE_TINY                       = "0.0025";
    public final static String JUNO_GAS_RATE_LOW                        = "0.005";
    public final static String JUNO_GAS_RATE_AVERAGE                    = "0.025";

    public final static String KONSTELL_GAS_RATE_TINY                   = "0.0001";
    public final static String KONSTELL_GAS_RATE_LOW                    = "0.001";
    public final static String KONSTELL_GAS_RATE_AVERAGE                = "0.01";

    public final static String UMEE_GAS_RATE_TINY                       = "0.000";
    public final static String UMEE_GAS_RATE_LOW                        = "0.001";
    public final static String UMEE_GAS_RATE_AVERAGE                    = "0.005";

    public final static String EVMOS_GAS_RATE_TINY                      = "0.000";
    public final static String EVMOS_GAS_RATE_LOW                       = "0.000";
    public final static String EVMOS_GAS_RATE_AVERAGE                   = "0.000";

    public final static String CUDOS_GAS_RATE_TINY                      = "0.000";
    public final static String CUDOS_GAS_RATE_LOW                       = "0.000";
    public final static String CUDOS_GAS_RATE_AVERAGE                   = "0.000";

    public final static String PROVENANCE_GAS_RATE_TINY                 = "2000.00";
    public final static String PROVENANCE_GAS_RATE_LOW                  = "2000.00";
    public final static String PROVENANCE_GAS_RATE_AVERAGE              = "2000.00";

    public final static String CERBERUS_GAS_RATE_TINY                   = "0.00";
    public final static String CERBERUS_GAS_RATE_LOW                    = "0.00";
    public final static String CERBERUS_GAS_RATE_AVERAGE                = "0.00";

    public final static String OMNIFLIX_GAS_RATE_TINY                   = "0.001";
    public final static String OMNIFLIX_GAS_RATE_LOW                    = "0.001";
    public final static String OMNIFLIX_GAS_RATE_AVERAGE                = "0.001";

    public final static String CRESCENT_GAS_RATE_TINY                   = "0.01";
    public final static String CRESCENT_GAS_RATE_LOW                    = "0.02";
    public final static String CRESCENT_GAS_RATE_AVERAGE                = "0.05";

    public final static String MANTLE_GAS_RATE_TINY                     = "0.000";
    public final static String MANTLE_GAS_RATE_LOW                      = "0.000";
    public final static String MANTLE_GAS_RATE_AVERAGE                  = "0.000";

    public final static String V1_GAS_AMOUNT_LOW                        = "100000";
    public final static String V1_GAS_AMOUNT_MID                        = "200000";
    public final static String V1_GAS_AMOUNT_HIGH                       = "300000";
    public final static String V1_GAS_AMOUNT_TOO_HIGH                   = "350000";


    public final static String BINANCE_MAIN_BNB_DEPUTY  = "bnb1jh7uv2rm6339yue8k4mj9406k3509kr4wt5nxn";
    public final static String KAVA_MAIN_BNB_DEPUTY     = "kava1r4v2zdhdalfj2ydazallqvrus9fkphmglhn6u6";
    public final static String BINANCE_MAIN_BTCB_DEPUTY = "bnb1xz3xqf4p2ygrw9lhp5g5df4ep4nd20vsywnmpr";
    public final static String KAVA_MAIN_BTCB_DEPUTY    = "kava14qsmvzprqvhwmgql9fr0u3zv9n2qla8zhnm5pc";
    public final static String BINANCE_MAIN_XRPB_DEPUTY = "bnb15jzuvvg2kf0fka3fl2c8rx0kc3g6wkmvsqhgnh";
    public final static String KAVA_MAIN_XRPB_DEPUTY    = "kava1c0ju5vnwgpgxnrktfnkccuth9xqc68dcdpzpas";
    public final static String BINANCE_MAIN_BUSD_DEPUTY = "bnb10zq89008gmedc6rrwzdfukjk94swynd7dl97w8";
    public final static String KAVA_MAIN_BUSD_DEPUTY    = "kava1hh4x3a4suu5zyaeauvmv7ypf7w9llwlfufjmuu";

    //For 9000
    public final static String BINANCE_TEST_BNB_DEPUTY  = "tbnb10uypsspvl6jlxcx5xse02pag39l8xpe7a3468h";
    public final static String KAVA_TEST_BNB_DEPUTY     = "kava1tfvn5t8qwngqd2q427za2mel48pcus3z9u73fl";
    public final static String BINANCE_TEST_BTC_DEPUTY  = "tbnb1dmn2xgnc8kcxn4s0ts5llu9ry3ulp2nlhuh5fz";
    public final static String KAVA_TEST_BTC_DEPUTY     = "kava1kla4wl0ccv7u85cemvs3y987hqk0afcv7vue84";

    public final static String CHAIN_IMG_URL            = "https://raw.githubusercontent.com/cosmostation/cosmostation_token_resource/master/chains/logo/";

    public final static String COSMOS_VAL_URL           = "https://raw.githubusercontent.com/cosmostation/cosmostation_token_resource/master/moniker/cosmoshub/";
    public final static String IRIS_VAL_URL             = "https://raw.githubusercontent.com/cosmostation/cosmostation_token_resource/master/moniker/irishub/";
    public final static String KAVA_VAL_URL             = "https://raw.githubusercontent.com/cosmostation/cosmostation_token_resource/master/moniker/kava/";
    public final static String BAND_VAL_URL             = "https://raw.githubusercontent.com/cosmostation/cosmostation_token_resource/master/moniker/bandprotocol/";
    public final static String IOV_VAL_URL              = "https://raw.githubusercontent.com/cosmostation/cosmostation_token_resource/master/moniker/iov/";
    public final static String CERTIK_VAL_URL           = "https://raw.githubusercontent.com/cosmostation/cosmostation_token_resource/master/moniker/certik/";
    public final static String SECRET_VAL_URL           = "https://raw.githubusercontent.com/cosmostation/cosmostation_token_resource/master/moniker/secret/";
    public final static String AKASH_VAL_URL            = "https://raw.githubusercontent.com/cosmostation/cosmostation_token_resource/master/moniker/akash/";
    public final static String PERSIS_VAL_URL           = "https://raw.githubusercontent.com/cosmostation/cosmostation_token_resource/master/moniker/persistence/";
    public final static String SENTINEL_VAL_URL         = "https://raw.githubusercontent.com/cosmostation/cosmostation_token_resource/master/moniker/sentinel/";
    public final static String OKEX_VAL_URL             = "https://raw.githubusercontent.com/cosmostation/cosmostation_token_resource/master/moniker/okex/";
    public final static String FETCH_VAL_URL            = "https://raw.githubusercontent.com/cosmostation/cosmostation_token_resource/master/moniker/fetchai/";
    public final static String CRYPTO_VAL_URL           = "https://raw.githubusercontent.com/cosmostation/cosmostation_token_resource/master/moniker/cryto/";
    public final static String SIF_VAL_URL              = "https://raw.githubusercontent.com/cosmostation/cosmostation_token_resource/master/moniker/sif/";
    public final static String KI_VAL_URL               = "https://raw.githubusercontent.com/cosmostation/cosmostation_token_resource/master/moniker/ki/";
    public final static String RIZON_VAL_URL            = "https://raw.githubusercontent.com/cosmostation/cosmostation_token_resource/master/moniker/rizon/";
    public final static String MEDI_VAL_URL             = "https://raw.githubusercontent.com/cosmostation/cosmostation_token_resource/master/moniker/medibloc/";
    public final static String ALTHEA_VAL_URL           = "https://raw.githubusercontent.com/cosmostation/cosmostation_token_resource/master/moniker/althea/";
    public final static String OSMOSIS_VAL_URL          = "https://raw.githubusercontent.com/cosmostation/cosmostation_token_resource/master/moniker/osmosis/";
    public final static String EMONEY_VAL_URL           = "https://raw.githubusercontent.com/cosmostation/cosmostation_token_resource/master/moniker/emoney/";
    public final static String UMEE_VAL_URL             = "https://raw.githubusercontent.com/cosmostation/cosmostation_token_resource/master/moniker/umee/";
    public final static String AXELAR_VAL_URL           = "https://raw.githubusercontent.com/cosmostation/cosmostation_token_resource/master/moniker/axelar/";
    public final static String JUNO_VAL_URL             = "https://raw.githubusercontent.com/cosmostation/cosmostation_token_resource/master/moniker/juno/";
    public final static String REGEN_VAL_URL            = "https://raw.githubusercontent.com/cosmostation/cosmostation_token_resource/master/moniker/regen/";
    public final static String BITCANNA_VAL_URL         = "https://raw.githubusercontent.com/cosmostation/cosmostation_token_resource/master/moniker/bitcanna/";
    public final static String STARGAZE_VAL_URL         = "https://raw.githubusercontent.com/cosmostation/cosmostation_token_resource/master/moniker/stargaze/";
    public final static String GRABRIDGE_VAL_URL        = "https://raw.githubusercontent.com/cosmostation/cosmostation_token_resource/master/moniker/gravity-bridge/";
    public final static String COMDEX_VAL_URL           = "https://raw.githubusercontent.com/cosmostation/cosmostation_token_resource/master/moniker/comdex/";
    public final static String INJ_VAL_URL              = "https://raw.githubusercontent.com/cosmostation/cosmostation_token_resource/master/moniker/injective/";
    public final static String BITSONG_VAL_URL          = "https://raw.githubusercontent.com/cosmostation/cosmostation_token_resource/master/moniker/bitsong/";
    public final static String DESMOS_VAL_URL           = "https://raw.githubusercontent.com/cosmostation/cosmostation_token_resource/master/moniker/desmos/";
    public final static String LUM_VAL_URL              = "https://raw.githubusercontent.com/cosmostation/cosmostation_token_resource/master/moniker/lum-network/";
    public final static String CHIHUAHUA_VAL_URL        = "https://raw.githubusercontent.com/cosmostation/cosmostation_token_resource/master/moniker/chihuahua/";
    public final static String KONSTELL_VAL_URL         = "https://raw.githubusercontent.com/cosmostation/cosmostation_token_resource/master/moniker/konstellation/";
    public final static String EVMOS_VAL_URL            = "https://raw.githubusercontent.com/cosmostation/cosmostation_token_resource/master/moniker/evmos/";
    public final static String CUDOS_VAL_URL            = "https://raw.githubusercontent.com/cosmostation/cosmostation_token_resource/master/moniker/cudos/";
    public final static String PROVENANCE_VAL_URL       = "https://raw.githubusercontent.com/cosmostation/cosmostation_token_resource/master/moniker/provenance/";
    public final static String CERBERUS_VAL_URL         = "https://raw.githubusercontent.com/cosmostation/cosmostation_token_resource/master/moniker/cerberus/";
    public final static String OMNIFLIX_VAL_URL         = "https://raw.githubusercontent.com/cosmostation/cosmostation_token_resource/master/moniker/omniflix/";
    public final static String CRESCENT_VAL_URL         = "https://raw.githubusercontent.com/cosmostation/cosmostation_token_resource/master/moniker/crescent/";
    public final static String ASSETMANTLE_VAL_URL      = "https://raw.githubusercontent.com/cosmostation/cosmostation_token_resource/master/moniker/asset-mantle/";

    public final static String COSMOS_COIN_IMG_URL      = "https://raw.githubusercontent.com/cosmostation/cosmostation_token_resource/master/coin_image/cosmos/";
    public final static String KAVA_COIN_IMG_URL        = "https://raw.githubusercontent.com/cosmostation/cosmostation_token_resource/master/coin_image/kava/";
    public final static String KAVA_CDP_IMG_URL         = "https://raw.githubusercontent.com/cosmostation/cosmostation_token_resource/master/kava/cdp/";
    public final static String KAVA_HARD_POOL_IMG_URL   = "https://raw.githubusercontent.com/cosmostation/cosmostation_token_resource/master/kava/hard/";
    public final static String BINANCE_TOKEN_IMG_URL    = "https://raw.githubusercontent.com/cosmostation/cosmostation_token_resource/master/coin_image/binance/";
    public final static String OKEX_COIN_IMG_URL        = "https://raw.githubusercontent.com/cosmostation/cosmostation_token_resource/master/coin_image/okex/";
    public final static String EMONEY_COIN_IMG_URL      = "https://raw.githubusercontent.com/cosmostation/cosmostation_token_resource/master/coin_image/emoney/";
    public final static String ASSET_IMG_URL            = "https://raw.githubusercontent.com/cosmostation/cosmostation_token_resource/master/assets/images/ethereum/";


    public final static String EXPLORER_COSMOS_MAIN     = "https://www.mintscan.io/cosmos/";
    public final static String EXPLORER_IRIS_MAIN       = "https://www.mintscan.io/iris/";
    public final static String EXPLORER_KAVA_MAIN       = "https://www.mintscan.io/kava/";
    public final static String EXPLORER_IOV_MAIN        = "https://www.mintscan.io/starname/";
    public final static String EXPLORER_BINANCE_MAIN    = "https://binance.mintscan.io/";
    public final static String EXPLORER_BAND_MAIN       = "https://www.mintscan.io/band/";
    public final static String EXPLORER_SECRET_MAIN     = "https://www.mintscan.io/secret/";
    public final static String EXPLORER_AKASH_MAIN      = "https://www.mintscan.io/akash/";
    public final static String EXPLORER_OKEX_MAIN       = "https://www.oklink.com/okexchain/";
    public final static String EXPLORER_PERSIS_MAIN     = "https://www.mintscan.io/persistence/";
    public final static String EXPLORER_SENTINEL_MAIN   = "https://www.mintscan.io/sentinel/";
    public final static String EXPLORER_FETCHAI_MAIN    = "https://www.mintscan.io/fetchai/";
    public final static String EXPLORER_CRYPTOORG_MAIN  = "https://www.mintscan.io/crypto-org/";
    public final static String EXPLORER_SIF_MAIN        = "https://www.mintscan.io/sifchain/";
    public final static String EXPLORER_KI_MAIN         = "https://www.mintscan.io/ki-chain/";
    public final static String EXPLORER_OSMOSIS_MAIN    = "https://www.mintscan.io/osmosis/";
    public final static String EXPLORER_MEDI_MAIN       = "https://www.mintscan.io/medibloc/";
    public final static String EXPLORER_CERTIK_MAIN     = "https://www.mintscan.io/certik/";
    public final static String EXPLORER_EMONEY_MAIN     = "https://www.mintscan.io/emoney/";
    public final static String EXPLORER_RIZON_MAIN      = "https://www.mintscan.io/rizon/";
    public final static String EXPLORER_JUNO_MAIN       = "https://www.mintscan.io/juno/";
    public final static String EXPLORER_REGEN_MAIN      = "https://www.mintscan.io/regen/";
    public final static String EXPLORER_BITCANNA_MAIN   = "https://www.mintscan.io/bitcanna/";
    public final static String EXPLORER_ALTHEA_MAIN     = "https://www.mintscan.io/althea/";
    public final static String EXPLORER_STARGAZE_MAIN   = "https://www.mintscan.io/stargaze/";
    public final static String EXPLORER_GRABRIDGE_MAIN  = "https://www.mintscan.io/gravity-bridge/";
    public final static String EXPLORER_COMDEX_MAIN     = "https://www.mintscan.io/comdex/";
    public final static String EXPLORER_INJ_MAIN        = "https://www.mintscan.io/injective/";
    public final static String EXPLORER_BITSONG_MAIN    = "https://www.mintscan.io/bitsong/";
    public final static String EXPLORER_DESMOS_MAIN     = "https://www.mintscan.io/desmos/";
    public final static String EXPLORER_LUM_MAIN        = "https://www.mintscan.io/lum/";
    public final static String EXPLORER_CHIHUAHUA_MAIN  = "https://www.mintscan.io/chihuahua/";
    public final static String EXPLORER_AXELAR_MAIN     = "https://www.mintscan.io/axelar/";
    public final static String EXPLORER_KONSTELL_MAIN   = "https://www.mintscan.io/konstellation/";
    public final static String EXPLORER_UMEE_MAIN       = "https://www.mintscan.io/umee/";
    public final static String EXPLORER_EVMOS_MAIN      = "https://www.mintscan.io/evmos/";
    public final static String EXPLORER_CUDOS_MAIN      = "https://www.mintscan.io/cudos/";
    public final static String EXPLORER_PROVENANCE_MAIN = "https://www.mintscan.io/provenance/";
    public final static String EXPLORER_CERBERUS_MAIN   = "https://www.mintscan.io/cerberus/";
    public final static String EXPLORER_OMNIFLIX_MAIN   = "https://www.mintscan.io/omniflix/";
    public final static String EXPLORER_CRESCENT_MAIN   = "https://www.mintscan.io/crescent/";
    public final static String EXPLORER_MANTLE_MAIN     = "https://www.mintscan.io/asset-mantle/";
    public final static String EXPLORER_HDAC_MAIN       = "https://explorer.as.hdactech.com/hdac-explorer/";

    public final static String EXPLORER_NOTICE_MINTSCAN = "https://notice.mintscan.io/";

    public final static String EXPLORER_OEC_TX          = "https://www.oklink.com/oec/";

    public final static String NFT_INFURA               = "https://ipfs.infura.io/ipfs/";


    public final static String EXPLORER_COSMOS_TEST     = "https://testnet.mintscan.io/cosmos/";
    public final static String EXPLORER_IRIS_TEST       = "https://testnet.mintscan.io/iris/";
    public final static String EXPLORER_RIZON_TEST      = "https://testnet.mintscan.io/rizon/";
    public final static String EXPLORER_CRESCENT_TEST   = "https://testnet.mintscan.io/crescent/";
    public final static String EXPLORER_HDAC_TEST       = "http://test.explorer.hdactech.com/hdac-explorer/";


    public final static String AKASH_UNKNOWN_RELAYER        = "https://raw.githubusercontent.com/cosmostation/cosmostation_token_resource/master/relayer/akash/relay-akash-unknown.png";
    public final static String BAND_UNKNOWN_RELAYER         = "https://raw.githubusercontent.com/cosmostation/cosmostation_token_resource/master/relayer/band/relay-band-unknown.png";
    public final static String CERTIK_UNKNOWN_RELAYER       = "https://raw.githubusercontent.com/cosmostation/cosmostation_token_resource/master/relayer/certik/relay-certik-unknown.png";
    public final static String COSMOS_UNKNOWN_RELAYER       = "https://raw.githubusercontent.com/cosmostation/cosmostation_token_resource/master/relayer/cosmos/relay-cosmos-unknown.png";
    public final static String CRYPTO_UNKNOWN_RELAYER       = "https://raw.githubusercontent.com/cosmostation/cosmostation_token_resource/master/relayer/cryptoorg/relay-cryptoorg-unknown.png";
    public final static String EMONEY_UNKNOWN_RELAYER       = "https://raw.githubusercontent.com/cosmostation/cosmostation_token_resource/master/relayer/emoney/relay-emoney-unknown.png";
    public final static String FETCHAI_UNKNOWN_RELAYER      = "https://raw.githubusercontent.com/cosmostation/cosmostation_token_resource/master/relayer/fetchai/relay-fetchai-unknown.png";
    public final static String INJ_UNKNOWN_RELAYER          = "https://raw.githubusercontent.com/cosmostation/cosmostation_token_resource/master/relayer/injective/relay-injective-unknown.png";
    public final static String IRIS_UNKNOWN_RELAYER         = "https://raw.githubusercontent.com/cosmostation/cosmostation_token_resource/master/relayer/iris/relay-iris-unknown.png";
    public final static String JUNO_UNKNOWN_RELAYER         = "https://raw.githubusercontent.com/cosmostation/cosmostation_token_resource/master/relayer/juno/relay-juno-unknown.png";
    public final static String OSMOSIS_UNKNOWN_RELAYER      = "https://raw.githubusercontent.com/cosmostation/cosmostation_token_resource/master/relayer/osmosis/relay-osmosis-unknown.png";
    public final static String PERSIS_UNKNOWN_RELAYER       = "https://raw.githubusercontent.com/cosmostation/cosmostation_token_resource/master/relayer/persistence/relay-persistence-unknown.png";
    public final static String REGEN_UNKNOWN_RELAYER        = "https://raw.githubusercontent.com/cosmostation/cosmostation_token_resource/master/relayer/regen/relay-regen-unknown.png";
    public final static String SENTINEL_UNKNOWN_RELAYER     = "https://raw.githubusercontent.com/cosmostation/cosmostation_token_resource/master/relayer/sentinel/relay-sentinel-unknown.png";
    public final static String SIFCHAIN_UNKNOWN_RELAYER     = "https://raw.githubusercontent.com/cosmostation/cosmostation_token_resource/master/relayer/sifchain/relay-sifchain-unknown.png";
    public final static String STARNAME_UNKNOWN_RELAYER     = "https://raw.githubusercontent.com/cosmostation/cosmostation_token_resource/master/relayer/starname/relay-starname-unknown.png";
    public final static String KI_UNKNOWN_RELAYER           = "https://raw.githubusercontent.com/cosmostation/cosmostation_token_resource/master/relayer/kichain/relay-kichain-unknown.png";
    public final static String BITCANNA_UNKNOWN_RELAYER     = "https://raw.githubusercontent.com/cosmostation/cosmostation_token_resource/master/relayer/bitcanna/relay-bitcanna-unknown.png";
    public final static String RIZON_UNKNOWN_RELAYER        = "https://raw.githubusercontent.com/cosmostation/cosmostation_token_resource/master/relayer/rizon/relay-rizon-unknown.png";
    public final static String MEDI_UNKNOWN_RELAYER         = "https://raw.githubusercontent.com/cosmostation/cosmostation_token_resource/master/relayer/medibloc/relay-medibloc-unknown.png";
    public final static String STARGAZE_UNKNOWN_RELAYER     = "https://raw.githubusercontent.com/cosmostation/cosmostation_token_resource/master/relayer/stargaze/relay-stargaze-unknown.png";
    public final static String COMDEX_UNKNOWN_RELAYER       = "https://raw.githubusercontent.com/cosmostation/cosmostation_token_resource/master/relayer/comdex/relay-comdex-unknown.png";
    public final static String SECRET_UNKNOWN_RELAYER       = "https://raw.githubusercontent.com/cosmostation/cosmostation_token_resource/master/relayer/secret/relay-secret-unknown.png";
    public final static String BITSONG_UNKNOWN_RELAYER      = "https://raw.githubusercontent.com/cosmostation/cosmostation_token_resource/master/relayer/bitsong/relay-bitsong-unknown.png";
    public final static String DESMOS_UNKNOWN_RELAYER       = "https://raw.githubusercontent.com/cosmostation/cosmostation_token_resource/master/relayer/desmos/relay-desmos-unknown.png";
    public final static String GRAB_UNKNOWN_RELAYER         = "https://raw.githubusercontent.com/cosmostation/cosmostation_token_resource/master/relayer/gravity-bridge/relay-gravitybridge-unknown.png";
    public final static String LUM_UNKNOWN_RELAYER          = "https://raw.githubusercontent.com/cosmostation/cosmostation_token_resource/master/relayer/lum-network/relay-lum-unknown.png";
    public final static String CHIHUAHUA_UNKNOWN_RELAYER    = "https://raw.githubusercontent.com/cosmostation/cosmostation_token_resource/master/relayer/chihuahua/relay-chihuahua-unknown.png";
    public final static String KAVA_UNKNOWN_RELAYER         = "https://raw.githubusercontent.com/cosmostation/cosmostation_token_resource/master/relayer/kava/relay-kava-unknown.png";
    public final static String AXELAR_UNKNOWN_RELAYER       = "https://raw.githubusercontent.com/cosmostation/cosmostation_token_resource/master/relayer/axelar/relay-axelar-unknown.png";
    public final static String KONSTELL_UNKNOWN_RELAYER     = "https://raw.githubusercontent.com/cosmostation/cosmostation_token_resource/master/relayer/konstellation/relay-konstellation-unknown.png";
    public final static String UMEE_UNKNOWN_RELAYER         = "https://raw.githubusercontent.com/cosmostation/cosmostation_token_resource/master/relayer/umee/relay-umee-unknown.png";
    public final static String EVMOS_UNKNOWN_RELAYER        = "https://raw.githubusercontent.com/cosmostation/cosmostation_token_resource/master/relayer/evmos/relay-evmos-unknown.png";
    public final static String CUDOS_UNKNOWN_RELAYER        = "https://raw.githubusercontent.com/cosmostation/cosmostation_token_resource/master/relayer/cudos/relay-cudos-unknown.png";
    public final static String PROVENANCE_UNKNOWN_RELAYER   = "https://raw.githubusercontent.com/cosmostation/cosmostation_token_resource/master/relayer/provenance/relay-provenance-unknown.png";
    public final static String CERBERUS_UNKNOWN_RELAYER     = "https://raw.githubusercontent.com/cosmostation/cosmostation_token_resource/master/relayer/cerberus/relay-cerberus-unknown.png";
    public final static String OMNIFLIX_UNKNOWN_RELAYER     = "https://raw.githubusercontent.com/cosmostation/cosmostation_token_resource/master/relayer/omniflix/relay-omniflix-unknown.png";
    public final static String CRESCENT_UNKNOWN_RELAYER     = "https://raw.githubusercontent.com/cosmostation/cosmostation_token_resource/master/relayer/crescent/relay-crescent-unknown.png";
    public final static String MANTLE_UNKNOWN_RELAYER       = "https://raw.githubusercontent.com/cosmostation/cosmostation_token_resource/master/relayer/asset-mantle/relay-assetmantle-unknown.png";

    //NFT Denom Default config
    public final static String STATION_NFT_DENOM        = "station";



    public final static BigDecimal DAY_SEC = new BigDecimal("86400");
    public final static BigDecimal MONTH_SEC = DAY_SEC.multiply(new BigDecimal("30"));
    public final static BigDecimal YEAR_SEC = DAY_SEC.multiply(new BigDecimal("365"));

    public final static BigDecimal BLOCK_TIME_COSMOS = new BigDecimal("7.6597");
    public final static BigDecimal BLOCK_TIME_IRIS = new BigDecimal("6.7884");
    public final static BigDecimal BLOCK_TIME_IOV = new BigDecimal("6.0124");
    public final static BigDecimal BLOCK_TIME_KAVA = new BigDecimal("6.7262");
    public final static BigDecimal BLOCK_TIME_BAND = new BigDecimal("3.0236");
    public final static BigDecimal BLOCK_TIME_CERTIK = new BigDecimal("5.9740");
    public final static BigDecimal BLOCK_TIME_SECRET = new BigDecimal("6.0408");
    public final static BigDecimal BLOCK_TIME_AKASH = new BigDecimal("6.4526");
    public final static BigDecimal BLOCK_TIME_SENTINEL = new BigDecimal("6.3113");
    public final static BigDecimal BLOCK_TIME_PERSISTENCE = new BigDecimal("5.7982");
    public final static BigDecimal BLOCK_TIME_FETCH = new BigDecimal("6.0678");
    public final static BigDecimal BLOCK_TIME_CRYPTO = new BigDecimal("6.1939");
    public final static BigDecimal BLOCK_TIME_SIF = new BigDecimal("5.7246");
    public final static BigDecimal BLOCK_TIME_KI = new BigDecimal("5.7571");
    public final static BigDecimal BLOCK_TIME_MEDI = new BigDecimal("5.7849");
    public final static BigDecimal BLOCK_TIME_OSMOSIS = new BigDecimal("6.5324");
    public final static BigDecimal BLOCK_TIME_EMONEY = new BigDecimal("24.8486");
    public final static BigDecimal BLOCK_TIME_RIZON = new BigDecimal("5.8850");
    public final static BigDecimal BLOCK_TIME_JUNO = new BigDecimal("6.3104");
    public final static BigDecimal BLOCK_TIME_BITCANNA = new BigDecimal("6.0256");
    public final static BigDecimal BLOCK_TIME_REGEN = new BigDecimal("6.2491");
    public final static BigDecimal BLOCK_TIME_STARGAZE = new BigDecimal("5.8129");
    public final static BigDecimal BLOCK_TIME_INJECTIVE = new BigDecimal("2.4865");
    public final static BigDecimal BLOCK_TIME_BITSONG = new BigDecimal("5.9040");
    public final static BigDecimal BLOCK_TIME_OKEX = new BigDecimal("4.0286");
    public final static BigDecimal BLOCK_TIME_BNB = new BigDecimal("0.4124");
    public final static BigDecimal BLOCK_TIME_COMDEX = new BigDecimal("6.1746");
    public final static BigDecimal BLOCK_TIME_DESMOS = new BigDecimal("6.1605");
    public final static BigDecimal BLOCK_TIME_GRAV = new BigDecimal("6.4500");
    public final static BigDecimal BLOCK_TIME_LUM = new BigDecimal("5.7210");
    public final static BigDecimal BLOCK_TIME_CHIHUAHUA = new BigDecimal("5.8172");
    public final static BigDecimal BLOCK_TIME_AXELAR = new BigDecimal("5.5596");
    public final static BigDecimal BLOCK_TIME_KONSTELLATION = new BigDecimal("5.376");
    public final static BigDecimal BLOCK_TIME_UMEE = new BigDecimal("5.658");
    public final static BigDecimal BLOCK_TIME_EVMOS = new BigDecimal("5.824");
    public final static BigDecimal BLOCK_TIME_PROVENANCE = new BigDecimal("6.3061");
    public final static BigDecimal BLOCK_TIME_CERBERUS = new BigDecimal("5.9666");
    public final static BigDecimal BLOCK_TIME_OMNIFLIX = new BigDecimal("5.7970");
    public final static BigDecimal BLOCK_TIME_CRESCENT = new BigDecimal("6.355");
    public final static BigDecimal BLOCK_TIME_MANTLE = new BigDecimal("5.8040");
}
