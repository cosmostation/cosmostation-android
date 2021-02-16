package wannabit.io.cosmostaion.base;

import java.math.BigDecimal;

public class BaseConstant {
    public final static boolean IS_SHOWLOG              = true;
    public final static boolean SUPPORT_MOONPAY         = true;
    public final static boolean SUPPORT_BEP3_SWAP       = true;
    public final static String LOG_TAG                  = "Cosmostation";

    public final static String DB_NAME                  = "WannaBit";
    public final static int DB_VERSION                  = 5;
    public final static String DB_TABLE_PASSWORD        = "paswd";
    public final static String DB_TABLE_ACCOUNT         = "accnt";
    public final static String DB_TABLE_BALANCE         = "balan";
    public final static String DB_TABLE_BONDING         = "bondi";
    public final static String DB_TABLE_UNBONDING       = "unbond";
    public final static String DB_TABLE_PRICE           = "price";

    public final static String PRE_USER_ID                  = "PRE_USER_ID";
    public final static String PRE_SELECTED_CHAIN           = "PRE_SELECTED_CHAIN";
    public final static String PRE_VALIDATOR_SORTING        = "PRE_VALIDATOR_SORTING";
    public final static String PRE_MY_VALIDATOR_SORTING     = "PRE_MY_VALIDATOR_SORTING";
    public final static String PRE_ATOM_TIC                 = "PRE_ATOM_TIC";
    public final static String PRE_ATOM_UP_DOWN_24          = "PRE_ATOM_UP_DOWN_24";
    public final static String PRE_IRIS_TIC                 = "PRE_IRIS_TIC";
    public final static String PRE_IRIS_UP_DOWN_24          = "PRE_IRIS_UP_DOWN_24";
    public final static String PRE_BNB_TIC                  = "PRE_BNB_TIC";
    public final static String PRE_BNB_UP_DOWN_24           = "PRE_BNB_UP_DOWN_24";
    public final static String PRE_KAVA_TIC                 = "PRE_KAVA_TIC";
    public final static String PRE_KAVA_DOLLOR_TIC          = "PRE_KAVA_DOLLOR_TIC";
    public final static String PRE_KAVA_UP_DOWN_24          = "PRE_KAVA_UP_DOWN_24";
    public final static String PRE_BAND_TIC                 = "PRE_BAND_TIC";
    public final static String PRE_BAND_UP_DOWN_24          = "PRE_BAND_UP_DOWN_24";
    public final static String PRE_IOV_TIC                  = "PRE_IOV_TIC";
    public final static String PRE_IOV_UP_DOWN_24           = "PRE_IOV_UP_DOWN_24";
    public final static String PRE_CERTIK_TIC               = "PRE_CERTIK_TIC";
    public final static String PRE_CERTIK_UP_DOWN_24        = "PRE_CERTIK_UP_DOWN_24";
    public final static String PRE_AKASH_TIC                = "PRE_AKASH_TIC";
    public final static String PRE_AKASH_UP_DOWN_24         = "PRE_AKASH_UP_DOWN_24";
    public final static String PRE_SECRET_TIC               = "PRE_SECRET_TIC";
    public final static String PRE_SECRET_UP_DOWN_24        = "PRE_SECRET_UP_DOWN_24";
    public final static String PRE_OKEX_TIC                 = "PRE_OKEX_TIC";
    public final static String PRE_OKEX_UP_DOWN_24          = "PRE_OKEX_UP_DOWN_24";
    public final static String PRE_OKEX_DOLLOR_TIC          = "PRE_OKEX_DOLLOR_TIC";
    public final static String PRE_CURRENCY                 = "PRE_CURRENCY";
    public final static String PRE_MARKET                   = "PRE_MARKET";
    public final static String PRE_USING_APP_LOCK           = "PRE_USING_APP_LOCK";
    public final static String PRE_USING_FINGERPRINT        = "PRE_USING_FINGERPRINT";
    public final static String PRE_APP_LOCK_TIME            = "PRE_APP_LOCK_TIME";
    public final static String PRE_APP_LOCK_LEAVE_TIME      = "PRE_APP_LOCK_LEAVE_TIME";
    public final static String PRE_TOKEN_SORTING            = "PRE_TOKEN_SORTING";
    public final static String PRE_ACCOUNT_ORDER            = "PRE_ACCOUNT_ORDER";
    public final static String PRE_FCM_TOKEN                = "PRE_FCM_TOKEN";
    public final static String PRE_KAVA_TESTNET_WARN        = "PRE_KAVA_TESTNET_WARN";
    public final static String PRE_EVENT_HIDE               = "PRE_EVENT_HIDE";


    public final static int TASK_INIT_PW                                = 2000;
    public final static int TASK_INIT_ACCOUNT                           = 2002;
    public final static int TASK_INIT_EMPTY_ACCOUNT                     = 2003;
    public final static int TASK_FETCH_ACCOUNT                          = 2004;
    public final static int TASK_FETCH_BONDEB_VALIDATOR                 = 2005;
    public final static int TASK_FETCH_BONDING_STATE                    = 2006;
    public final static int TASK_FETCH_UNBONDING_STATE                  = 2007;
    public final static int TASK_FETCH_TOTAL_REWARDS                    = 2008;
    public final static int TASK_FETCH_SINGLE_VALIDATOR                 = 2009;
    public final static int TASK_FETCH_SINGLE_BONDING                   = 2010;
    public final static int TASK_FETCH_SINGLE_UNBONDING                 = 2011;
    public final static int TASK_FETCH_SINGLE_REWARD                    = 2012;
    public final static int TASK_FETCH_SINGLE_SELF_BONDING              = 2013;
    public final static int TASK_PASSWORD_CHECK                         = 2015;
    public final static int TASK_FETCH_WITHDRAW_ADDRESS                 = 2016;
//    public final static int TASK_FETCH_HISTORY                          = 2017;
    public final static int TASK_GEN_TX_SIMPLE_SEND                     = 2018;
    public final static int TASK_OVERRIDE_ACCOUNT                       = 2019;
    public final static int TASK_GEN_TX_SIMPLE_DELEGATE                 = 2020;
    public final static int TASK_GEN_TX_SIMPLE_UNDELEGATE               = 2021;
    public final static int TASK_GEN_TX_SIMPLE_REWARD                   = 2022;
    public final static int TASK_FETCH_ALL_PROPOSAL                     = 2023;
    public final static int TASK_DELETE_USER                            = 2024;
    public final static int TASK_CHECK_MNEMONIC                         = 2025;
//    public final static int TASK_FETCH_VAL_HISTORY                      = 2026;
    public final static int TASK_FETCH_UNBONDING_VALIDATOR              = 2027;
    public final static int TASK_FETCH_UNBONDED_VALIDATOR               = 2028;
    public final static int TASK_FETCH_SINGLE_REDELEGATE                = 2029;
    public final static int TASK_FETCH_SINGLE_ALL_REDELEGATE            = 2030;
    public final static int TASK_GEN_TX_SIMPLE_REDELEGATE               = 2031;
    public final static int TASK_GEN_TX_SIMPLE_REWARD_ADDRESS_CHANGE    = 2032;
    public final static int TASK_FETCH_INFLATION                        = 2033;
    public final static int TASK_FETCH_PROVISIONS                       = 2034;
    public final static int TASK_FETCH_STAKING_POOL                     = 2035;
    public final static int TASK_GEN_TX_REINVEST                        = 2036;
    public final static int TASK_IRIS_REWARD                            = 2037;
    public final static int TASK_IRIS_POOL                              = 2038;
    public final static int TASK_IRIS_PROPOSAL                          = 2039;
    public final static int TASK_IRIS_REDELEGATE                        = 2040;
    public final static int TASK_FETCH_BNB_HISTORY                      = 2041;
    public final static int TASK_FETCH_BNB_TOKENS                       = 2042;
    public final static int TASK_FETCH_IRIS_TOKENS                      = 2043;
//    public final static int TASK_FETCH_TOKEN_HISTORY                    = 2044;
    public final static int TASK_GEN_TX_BNB_SIMPLE_SEND                 = 2045;
    public final static int TASK_FETCH_PROPOSAL_DETAIL                  = 2046;
    public final static int TASK_FETCH_PROPOSAL_VOTED                   = 2047;
    public final static int TASK_FETCH_PROPOSAL_PROPOSER                = 2048;
    public final static int TASK_FETCH_PROPOSAL_TALLY                   = 2049;
//    public final static int TASK_FETCH_IOV_BALANCE                      = 2050;
//    public final static int TASK_FETCH_IOV_NONCE                        = 2051;
//    public final static int TASK_FETCH_IOV_ADDRESS_INFO                 = 2052;
//    public final static int TASK_FETCH_IOV_TOKENS                       = 2053;
    public final static int TASK_FETCH_IRIS_VOTE_LIST                   = 2054;
    public final static int TASK_IRIS_PROPOSAL_DETAIL                   = 2055;
    public final static int TASK_GEN_TX_SIMPLE_VOTE                     = 2056;
    public final static int TASK_PUSH_STATUS_UPDATE                     = 2057;
    public final static int TASK_MOON_PAY_SIGNATURE                     = 2058;
    public final static int TASK_FETCH_KAVA_CDP_PARAM                   = 2059;
    public final static int TASK_FETCH_KAVA_CDP_OWENER                  = 2060;
    public final static int TASK_FETCH_KAVA_CDP_DEPOSIT                 = 2061;
    public final static int TASK_FETCH_KAVA_CDP_LIST_DENOM              = 2062;
    public final static int TASK_FETCH_KAVA_CDP_LIST_RATIO              = 2063;
    public final static int TASK_FETCH_KAVA_TOKEN_PRICE                 = 2064;
    public final static int TASK_GEN_TX_CREATE_CDP                      = 2065;
    public final static int TASK_GEN_TX_REPAY_CDP                       = 2066;
    public final static int TASK_GEN_TX_DRAW_DEBT_CDP                   = 2067;
    public final static int TASK_GEN_TX_DEPOSIT_CDP                     = 2068;
    public final static int TASK_GEN_TX_WITHDRAW_CDP                    = 2069;
    public final static int TASK_GEN_TX_HTLC_SWAP                       = 2070;
    public final static int TASK_GEN_TX_HTLC_REFUND                     = 2071;
    public final static int TASK_GEN_TX_BNB_HTLC_REFUND                 = 2072;
    public final static int TASK_FETCH_MY_VOTE                          = 2073;
    public final static int TASK_FETCH_KAVA_INCENTIVE_PARAM             = 2074;
    public final static int TASK_FETCH_KAVA_PRICE_FEED_PARAM            = 2075;
    public final static int TASK_FETCH_KAVA_INCENTIVE_REWARD            = 2076;
    public final static int TASK_GEN_KAVA_CLAIM_INCENTIVE               = 2077;
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
    public final static int TASK_FETCH_KAVA_TOTAL_SUPPLY                = 2089;
    public final static int TASK_FETCH_MY_STARNAME_ACCOUNT              = 2090;
    public final static int TASK_FETCH_MY_STARNAME_DOMAIN               = 2091;
    public final static int TASK_FETCH_STARNAME_FEE                     = 2092;
    public final static int TASK_FETCH_STARNAME_CONFIG                  = 2093;
    public final static int TASK_GEN_TX_REGISTER_DOMAIN                 = 2094;
    public final static int TASK_GEN_TX_REGISTER_ACCOUNT                = 2095;
    public final static int TASK_FETCH_STARNAME_RESOLVE                 = 2096;
    public final static int TASK_FETCH_STARNAME_DOMAIN_INFO             = 2097;
    public final static int TASK_GEN_TX_DELETE_DOMAIN                   = 2098;
    public final static int TASK_GEN_TX_DELETE_ACCOUNT                  = 2099;
    public final static int TASK_GEN_TX_RENEW_DOMAIN                    = 2100;
    public final static int TASK_GEN_TX_RENEW_ACCOUNT                   = 2101;
    public final static int TASK_GEN_TX_REPLACE_STARNAME                = 2102;
    public final static int TASK_FETCH_KAVA_HARVEST_PARAM               = 2103;
    public final static int TASK_FETCH_KAVA_HARVEST_DEPOSIT             = 2104;
    public final static int TASK_FETCH_KAVA_HARVEST_REWARD              = 2105;
    public final static int TASK_FETCH_KAVA_HARVEST_ACCOUNT             = 2106;
    public final static int TASK_GEN_TX_KAVA_DEPOSIT_HARVEST            = 2107;
    public final static int TASK_GEN_TX_KAVA_WITHDRAW_HARVEST           = 2108;
    public final static int TASK_GEN_TX_KAVA_CLAIM_HARVEST              = 2109;
    public final static int TASK_FETCH_BAND_ORACLE_STATUS               = 2111;
    public final static int TASK_FETCH_MINT_PARAM                       = 2112;
    public final static int TASK_FETCH_OK_HISTORY                       = 2113;
    public final static int TASK_FETCH_OK_DEX_TICKERS                   = 2114;

    public final static int TASK_FETCH_API_ADDRESS_HISTORY              = 2300;
    public final static int TASK_FETCH_API_TOKEN_HISTORY                = 2301;
    public final static int TASK_FETCH_API_STAKE_HISTORY                = 2302;


    public final static int TASK_V1_FETCH_BALANCE                       = 3001;
    public final static int TASK_V1_FETCH_BONDED_VALIDATORS             = 3002;
    public final static int TASK_V1_FETCH_UNBONDED_VALIDATORS           = 3003;
    public final static int TASK_V1_FETCH_UNBONDING_VALIDATORS          = 3004;
    public final static int TASK_V1_FETCH_DELEGATIONS                   = 3005;
    public final static int TASK_V1_FETCH_UNDELEGATIONS                 = 3006;
    public final static int TASK_V1_FETCH_ALL_REWARDS                   = 3007;
    public final static int TASK_V1_FETCH_PARAM_MINT                    = 3008;
    public final static int TASK_V1_FETCH_INFLATION                     = 3009;
    public final static int TASK_V1_FETCH_PROVISION                     = 3010;
    public final static int TASK_V1_FETCH_STAKING_POOL                  = 3011;
    public final static int TASK_V1_FETCH_IRIS_TOKEN_LIST               = 3012;
    public final static int TASK_V1_FETCH_VALIDATOR_INFO                = 3013;
    public final static int TASK_V1_FETCH_SELF_BONDING                  = 3014;
    public final static int TASK_V1_FETCH_WITHDRAW_ADDRESS              = 3015;

    public final static int TASK_V1_BROAD_DELEGATE                      = 3300;
    public final static int TASK_V1_BROAD_UNDELEGATE                    = 3301;
    public final static int TASK_V1_BROAD_CLAIM_REWARDS                 = 3302;
    public final static int TASK_V1_BROAD_SEND                          = 3303;


    public final static int TASK_GRPC_FETCH_BALANCE                     = 4001;
    public final static int TASK_GRPC_FETCH_BONDED_VALIDATORS           = 4002;
    public final static int TASK_GRPC_FETCH_UNBONDED_VALIDATORS         = 4003;
    public final static int TASK_GRPC_FETCH_UNBONDING_VALIDATORS        = 4004;
    public final static int TASK_GRPC_FETCH_DELEGATIONS                 = 4005;
    public final static int TASK_GRPC_FETCH_UNDELEGATIONS               = 4006;
    public final static int TASK_GRPC_FETCH_ALL_REWARDS                 = 4007;
    public final static int TASK_GRPC_FETCH_PARAM_MINT                  = 4008;
    public final static int TASK_GRPC_FETCH_IRIS_PARAM_MINT             = 4009;
    public final static int TASK_GRPC_FETCH_INFLATION                   = 4010;
    public final static int TASK_GRPC_FETCH_PROVISION                   = 4011;
    public final static int TASK_GRPC_FETCH_STAKING_POOL                = 4012;
    public final static int TASK_GRPC_FETCH_IRIS_TOKEN_LIST             = 4013;
    public final static int TASK_GRPC_FETCH_VALIDATOR_INFO              = 4014;
    public final static int TASK_GRPC_FETCH_SELF_BONDING                = 4015;
    public final static int TASK_GRPC_FETCH_WITHDRAW_ADDRESS            = 4016;
    public final static int TASK_GRPC_FETCH_REDELEGATIONS_TO            = 4017;
    public final static int TASK_GRPC_FETCH_REDELEGATIONS_FROM_TO       = 4018;

    public final static int TASK_GRPC_BROAD_DELEGATE                    = 4300;
    public final static int TASK_GRPC_BROAD_UNDELEGATE                  = 4301;
    public final static int TASK_GRPC_BROAD_CLAIM_REWARDS               = 4302;
    public final static int TASK_GRPC_BROAD_SEND                        = 4303;
    public final static int TASK_GRPC_BROAD_REDELEGATE                  = 4304;
    public final static int TASK_GRPC_BROAD_REINVEST                    = 4305;





    public final static String COSMOS_AUTH_TYPE_STDTX                       = "auth/StdTx";

    public final static String COSMOS_AUTH_TYPE_DELAYEDACCOUNT              = "cosmos-sdk/DelayedVestingAccount";
    public final static String COSMOS_AUTH_TYPE_VESTING_ACCOUNT             = "cosmos-sdk/ValidatorVestingAccount";
    public final static String COSMOS_AUTH_TYPE_P_VESTING_ACCOUNT           = "cosmos-sdk/PeriodicVestingAccount";
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

    public final static String COSMOS_BANK_TYPE_STDTX                       = "irishub/bank/StdTx";
    public final static String IRIS_BANK_TYPE_ACCOUNT                       = "irishub/bank/Account";
    public final static String IRIS_MSG_TYPE_TRANSFER                       = "irishub/bank/Send";
    public final static String IRIS_MSG_TYPE_DELEGATE                       = "irishub/stake/MsgDelegate";
    public final static String IRIS_MSG_TYPE_UNDELEGATE                     = "irishub/stake/BeginUnbonding";
    public final static String IRIS_MSG_TYPE_REDELEGATE                     = "irishub/stake/BeginRedelegate";
    public final static String IRIS_MSG_TYPE_WITHDRAW                       = "irishub/distr/MsgWithdrawDelegationReward";
    public final static String IRIS_MSG_TYPE_WITHDRAW_ALL                   = "irishub/distr/MsgWithdrawDelegationRewardsAll";
    public final static String IRIS_MSG_TYPE_COMMISSION                     = "irishub/distr/MsgWithdrawValidatorRewardsAll";
    public final static String IRIS_MSG_TYPE_VOTE                           = "irishub/gov/MsgVote";
    public final static String IRIS_MSG_TYPE_DEPOSIT                        = "irishub/gov/MsgDeposit";
    public final static String IRIS_MSG_TYPE_SUBMIT_PROPOSAL                = "irishub/gov/MsgSubmitProposal";
    public final static String IRIS_MSG_TYPE_CREATE_VALIDATOR               = "irishub/stake/MsgCreateValidator";
    public final static String IRIS_MSG_TYPE_WITHDRAW_MIDIFY                = "irishub/distr/MsgModifyWithdrawAddress";
    public final static String IRIS_MSG_TYPE_ISSUE_TOKEN                    = "irishub/asset/MsgIssueToken";

    public final static String IRIS_PROPOAL_TYPE_BasicProposal              = "irishub/gov/BasicProposal";
    public final static String IRIS_PROPOAL_TYPE_ParameterProposal          = "irishub/gov/ParameterProposal";
    public final static String IRIS_PROPOAL_TYPE_PlainTextProposal          = "irishub/gov/PlainTextProposal";
    public final static String IRIS_PROPOAL_TYPE_TokenAdditionProposal      = "irishub/gov/TokenAdditionProposal";
    public final static String IRIS_PROPOAL_TYPE_SoftwareUpgradeProposal    = "irishub/gov/SoftwareUpgradeProposal";
    public final static String IRIS_PROPOAL_TYPE_SystemHaltProposal         = "irishub/gov/SystemHaltProposal";
    public final static String IRIS_PROPOAL_TYPE_CommunityTaxUsageProposal  = "irishub/gov/CommunityTaxUsageProposal";

    public final static String KAVA_MSG_TYPE_POST_PRICE                     = "pricefeed/MsgPostPrice";
    public final static String KAVA_MSG_TYPE_CREATE_CDP                     = "cdp/MsgCreateCDP";
    public final static String KAVA_MSG_TYPE_DEPOSIT_CDP                    = "cdp/MsgDeposit";
    public final static String KAVA_MSG_TYPE_WITHDRAW_CDP                   = "cdp/MsgWithdraw";
    public final static String KAVA_MSG_TYPE_DRAWDEBT_CDP                   = "cdp/MsgDrawDebt";
    public final static String KAVA_MSG_TYPE_REPAYDEBT_CDP                  = "cdp/MsgRepayDebt";
    public final static String KAVA_MSG_TYPE_BEP3_CREATE_SWAP               = "bep3/MsgCreateAtomicSwap";
    public final static String KAVA_MSG_TYPE_BEP3_CLAM_SWAP                 = "bep3/MsgClaimAtomicSwap";
    public final static String KAVA_MSG_TYPE_BEP3_REFUND_SWAP               = "bep3/MsgRefundAtomicSwap";
    public final static String KAVA_MSG_TYPE_INCENTIVE_REWARD               = "incentive/MsgClaimReward";
    public final static String KAVA_MSG_TYPE_DEPOSIT_HAVEST                 = "harvest/MsgDeposit";
    public final static String KAVA_MSG_TYPE_WITHDRAW_HAVEST                = "harvest/MsgWithdraw";
    public final static String KAVA_MSG_TYPE_CLAIM_HAVEST                   = "harvest/MsgClaimReward";


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

    public final static String IOV_MSG_TYPE_ADD_ACCOUNT_CERT                = "starname/AddAccountCertificates";
    public final static String IOV_MSG_TYPE_DELETE_ACCOUNT_CERT             = "starname/DeleteAccountCertificates";
    public final static String IOV_MSG_TYPE_TRANSFER_ACCOUNT                = "starname/TransferAccount";
    public final static String IOV_MSG_TYPE_TRANSFER_DOMAIN_ALL             = "starname/TransferDomainAll";
    public final static String IOV_MSG_TYPE_SET_ACCOUNT_META_DATA           = "starname/SetAccountMetadata";



    public final static String CERTIK_MSG_TYPE_TRANSFER                     = "bank/MsgSend";


    public final static String COSMOS_KEY_TYPE_PUBLIC                       = "tendermint/PubKeySecp256k1";
    public final static String ETHERMINT_KEY_TYPE_PUBLIC                    = "ethermint/PubKeyEthSecp256k1";
    public final static String IOV_KEY_TYPE                                 = "sigs/ed25519/";

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
//    public final static int CONST_PW_TX_HTLS_SWAP                           = 5018;
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
    public final static int CONST_PW_TX_DEPOSIT_HARVEST                     = 5031;
    public final static int CONST_PW_TX_WITHDRAW_HARVEST                    = 5032;
    public final static int CONST_PW_TX_CLAIM_HARVEST_REWARD                = 5033;


    public final static int TX_TYPE_UNKNOWN                     = 3000;
    public final static int TX_TYPE_SEND                        = 3001;
    public final static int TX_TYPE_RECEIVE                     = 3002;
    public final static int TX_TYPE_DELEGATE                    = 3003;
    public final static int TX_TYPE_UNDELEGATE                  = 3004;
    public final static int TX_TYPE_REDELEGATE                  = 3005;
    public final static int TX_TYPE_GET_REWARD                  = 3006;
    public final static int TX_TYPE_GET_COMMISSION              = 3007;
    public final static int TX_TYPE_CHAGE_REWARD_ADDRESS        = 3008;
    public final static int TX_TYPE_TRANSFER                    = 3009;
    public final static int TX_TYPE_VOTE                        = 3010;
    public final static int TX_TYPE_REINVEST                    = 3011;
    public final static int TX_TYPE_SUBMIT_PROPOSAL             = 3012;
    public final static int TX_TYPE_DEPOSIT                     = 3013;
    public final static int TX_TYPE_CREATE_VALIDATOR            = 3014;
    public final static int TX_TYPE_EDIT_VALIDATOR              = 3015;
    public final static int TX_TYPE_IRIS_GET_REWARD_ALL         = 3016;
    public final static int TX_TYPE_IRIS_ISSUE_TOKEN            = 3017;
    public final static int TX_TYPE_KAVA_POST_PRICE             = 3018;
    public final static int TX_TYPE_KAVA_CREATE_CDP             = 3019;
    public final static int TX_TYPE_KAVA_DEPOSIT_CDP            = 3020;
    public final static int TX_TYPE_KAVA_WITHDRAW_CDP           = 3021;
    public final static int TX_TYPE_KAVA_DRAWDEBT_CDP           = 3022;
    public final static int TX_TYPE_KAVA_REPAYDEBT_CDP          = 3023;
    public final static int TX_TYPE_KAVA_BEP3_CREATE            = 3024;
    public final static int TX_TYPE_KAVA_BEP3_CLAIM             = 3025;
    public final static int TX_TYPE_KAVA_BEP3_REFUND            = 3026;
    public final static int TX_TYPE_KAVA_INCENTIVE_REWARD       = 3027;
    public final static int TX_TYPE_STARNAME_REGISTER_DOMAIN    = 3028;
    public final static int TX_TYPE_STARNAME_REGISTER_ACCOUNT   = 3029;
    public final static int TX_TYPE_STARNAME_DELETE_ACCOUNT     = 3030;
    public final static int TX_TYPE_STARNAME_DELETE_DOMAIN      = 3031;
    public final static int TX_TYPE_STARNAME_REPLACE_RESOURCE   = 3032;
    public final static int TX_TYPE_STARNAME_RENEW_DOMAIN       = 3033;
    public final static int TX_TYPE_STARNAME_RENEW_ACCOUNT      = 3034;
    public final static int TX_TYPE_KAVA_DEPOSIT_HARVEST        = 3035;
    public final static int TX_TYPE_KAVA_WITHDRAW_HARVEST       = 3036;
    public final static int TX_TYPE_KAVA_CLAIM_HARVEST          = 3037;


    public final static int ERROR_CODE_UNKNOWN              = 8000;
    public final static int ERROR_CODE_NETWORK              = 8001;
    public final static int ERROR_CODE_INVALID_PASSWORD     = 8002;
    public final static int ERROR_CODE_TIMEOUT              = 8003;
    public final static int ERROR_CODE_BROADCAST            = 8004;


    public final static String TOKEN_ATOM           = "uatom";
    public final static String TOKEN_IRIS           = "iris";
    public final static String TOKEN_IRIS_ATTO      = "iris-atto";
    public final static String TOKEN_BNB            = "BNB";
    public final static String TOKEN_KAVA           = "ukava";
    public final static String TOKEN_HARD           = "hard";
    public final static String TOKEN_IOV            = "uiov";
    public final static String TOKEN_CERTIK         = "uctk";
    public final static String TOKEN_BAND           = "uband";
    public final static String TOKEN_AKASH          = "uakt";
    public final static String TOKEN_SECRET         = "uscrt";
    public final static String TOKEN_OK             = "okt";
    public final static String TOKEN_OK_OKB         = "okb";

    public final static String TOKEN_IOV_TEST       = "uvoi";
    public final static String TOKEN_COSMOS_TEST    = "umuon";
    public final static String TOKEN_IRIS_TEST      = "ubif";


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


    public final static String KEY_PATH             = "44'/118'/0'/0/";
    public final static String KEY_BNB_PATH         = "44'/714'/0'/0/";
    public final static String KEY_IOV_PATH         = "44'/234'/0'/0/";
    public final static String KEY_NEW_KAVA_PATH    = "44'/459'/0'/0/";
    public final static String KEY_BAND_PATH        = "44'/494'/0'/0/";
    public final static String KEY_NEW_OK_PATH      = "44'/996'/0'/0/";
    public final static String KEY_NEW_SECRET_PATH  = "44'/529'/0'/0/";
    public final static String characterFilter      = "[^\\p{L}\\p{M}\\p{N}\\p{P}\\p{Z}\\p{Cf}\\p{Cs}\\s]";


    public final static long CONSTANT_S = 1000l;
    public final static long CONSTANT_10S = CONSTANT_S * 10;
    public final static long CONSTANT_30S = CONSTANT_S * 30;
    public final static long CONSTANT_M = CONSTANT_S * 60;
    public final static long CONSTANT_H = CONSTANT_M * 60;
    public final static long CONSTANT_D = CONSTANT_H * 24;


    public final static int MEMO_ATOM = 255;
    public final static int MEMO_IRIS = 100;
    public final static int MEMO_BNB = 100;

    public final static String FEE_GAS_RATE_LOW = "0.0025";
    public final static String FEE_GAS_RATE_AVERAGE = "0.025";


    public final static String FEE_GAS_AMOUNT_HALF          = "100000";
    public final static String FEE_GAS_AMOUNT_AVERAGE       = "200000";
    public final static String FEE_GAS_AMOUNT_REDELEGATE    = "240000";
    public final static String FEE_GAS_AMOUNT_REINVEST      = "220000";


    public final static String FEE_KAVA_GAS_AMOUNT_LOW          = "150000";
    public final static String FEE_KAVA_GAS_AMOUNT_SEND         = "200000";
    public final static String FEE_KAVA_GAS_AMOUNT_REWARD       = "200000";
    public final static String FEE_KAVA_GAS_AMOUNT_AVERAGE      = "500000";
    public final static String FEE_KAVA_GAS_AMOUNT_REINVEST     = "500000";
    public final static String FEE_KAVA_GAS_AMOUNT_REDELEGATE   = "500000";
    public final static String FEE_KAVA_GAS_AMOUNT_CDP          = "1500000";
    public final static String FEE_KAVA_GAS_AMOUNT_CREATE_CDP   = "1500000";
    public final static String FEE_KAVA_GAS_AMOUNT_BEP3         = "200000";
    public final static String FEE_KAVA_GAS_AMOUNT_HIGH         = "350000";

    public final static String FEE_IRIS_GAS_RATE_AVERAGE        = "0.000008";

    public final static String FEE_IRIS_GAS_AMOUNT_SEND         = "25000";
    public final static String FEE_IRIS_GAS_AMOUNT_AVERAGE      = "50000";
    public final static String FEE_IRIS_GAS_AMOUNT_REDELEGATE   = "65000";
    public final static String FEE_IRIS_GAS_AMOUNT_REWARD_BASE  = "10000";
    public final static String FEE_IRIS_GAS_AMOUNT_REWARD_MUX   = "5000";
    public final static String FEE_IRIS_GAS_AMOUNT_VOTE         = "10000";
    public final static String FEE_IRIS_GAS_AMOUNT_ADDRESS_CHANGE = "10000";

    public final static String FEE_BNB_SEND                     = "0.000375";
    public final static String FEE_BEP3_RELAY_FEE               = "0.00001";

    public final static String FEE_IOV_GAS_RATE_AVERAGE         = "1.00";
    public final static String FEE_IOV_GAS_AMOUNT_SEND          = "100000";
    public final static String FEE_IOV_GAS_AMOUNT_STAKE         = "200000";
    public final static String FEE_IOV_GAS_AMOUNT_REDELEGATE    = "300000";
    public final static String FEE_IOV_GAS_AMOUNT_REINVEST      = "300000";
    public final static String FEE_IOV_GAS_AMOUNT_LOW           = "100000";
    public final static String FEE_IOV_GAS_AMOUNT_REGISTER      = "300000";
    public final static String FEE_IOV_GAS_AMOUNT_DELETE        = "150000";
    public final static String FEE_IOV_GAS_AMOUNT_RENEW         = "300000";
    public final static String FEE_IOV_GAS_AMOUNT_REPLACE       = "300000";

    public final static String FEE_OK_GAS_RATE_AVERAGE          = "0.00000001";
    public final static String FEE_OK_GAS_AMOUNT_SEND           = "200000";
    public final static String FEE_OK_GAS_AMOUNT_STAKE          = "200000";
    public final static String FEE_OK_GAS_AMOUNT_STAKE_MUX      = "20000";
    public final static String FEE_OK_GAS_AMOUNT_VOTE           = "200000";
    public final static String FEE_OK_GAS_AMOUNT_VOTE_MUX       = "50000";

    public final static String FEE_CERTIK_GAS_RATE_AVERAGE      = "0.05";
    public final static String FEE_CERTIK_GAS_AMOUNT_SEND       = "100000";
    public final static String FEE_CERTIK_GAS_AMOUNT_STAKE      = "200000";
    public final static String FEE_CERTIK_GAS_AMOUNT_REDELEGATE = "300000";
    public final static String FEE_CERTIK_GAS_AMOUNT_REINVEST   = "300000";
    public final static String FEE_CERTIK_GAS_ADDRESS_CHANGE    = "100000";
    public final static String FEE_CERTIK_GAS_VOTE              = "100000";

    public final static String FEE_AKASH_GAS_RATE_AVERAGE           = "0.025";
    public final static String FEE_AKASH_GAS_AMOUNT_SEND            = "100000";
    public final static String FEE_AKASH_GAS_AMOUNT_STAKE           = "200000";
    public final static String FEE_AKASH_GAS_AMOUNT_REDELEGATE      = "300000";
    public final static String FEE_AKASH_GAS_AMOUNT_REINVEST        = "300000";
    public final static String FEE_AKASH_GAS_AMOUNT_VOTE            = "100000";
    public final static String FEE_AKASH_GAS_AMOUNT_ADDRESS_CHANGE  = "100000";

    public final static String SECRET_GAS_FEE_RATE_AVERAGE              = "0.25";
    public final static String SECRET_GAS_AMOUNT_SEND                   = "80000";
    public final static String SECRET_GAS_AMOUNT_STAKE                  = "200000";
    public final static String SECRET_GAS_AMOUNT_REDELEGATE             = "300000";
    public final static String SECRET_GAS_AMOUNT_REINVEST               = "350000";
    public final static String SECRET_GAS_AMOUNT_REWARD_ADDRESS_CHANGE  = "80000";
    public final static String SECRET_GAS_AMOUNT_VOTE                   = "100000";

    public final static String STARGATE_GAS_RATE_AVERAGE                = "0.025";
    public final static String STARGATE_GAS_AMOUNT_LOW                  = "100000";
    public final static String STARGATE_GAS_AMOUNT_MID                  = "200000";
    public final static String STARGATE_GAS_AMOUNT_HIGH                 = "300000";

    public final static String CGC_ATOM = "cosmos";
    public final static String CGC_IRIS = "iris-network";
    public final static String CGC_BNB  = "binancecoin";
    public final static String CGC_KAVA = "kava";
    public final static String CGC_BAND = "band-protocol";
    public final static String CGC_IOV = "starname";
    public final static String CGC_CERTIK = "certik";
    public final static String CGC_AKASH = "akash-network";
    public final static String CGC_SECRET = "secret";
    public final static String CGC_OKEX = "okexchain";

    public final static int CMC_ATOM = 3794;
    public final static int CMC_IRIS = 3874;
    public final static int CMC_BNB = 1839;
    public final static int CMC_KAVA = 4846;


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


    public final static String TOKEN_IMG_URL            = "https://raw.githubusercontent.com/cosmostation/cosmostation_token_resource/master/thumnail/";
    public final static String COSMOS_VAL_URL           = "https://raw.githubusercontent.com/cosmostation/cosmostation_token_resource/master/moniker/cosmoshub/";
    public final static String IRIS_VAL_URL             = "https://raw.githubusercontent.com/cosmostation/cosmostation_token_resource/master/moniker/irishub/";
    public final static String KAVA_VAL_URL             = "https://raw.githubusercontent.com/cosmostation/cosmostation_token_resource/master/moniker/kava/kava-2/";
    public final static String KAVA_COIN_IMG_URL        = "https://raw.githubusercontent.com/cosmostation/cosmostation_token_resource/master/coin_image/kava/";
    public final static String KAVA_CDP_MARKET_IMG_URL  = "https://raw.githubusercontent.com/cosmostation/cosmostation_token_resource/master/cdp_market/kava/";
    public final static String KAVA_HARVEST_MARKET_IMG_URL  = "https://raw.githubusercontent.com/cosmostation/cosmostation_token_resource/master/harvest_market/kava/";
    public final static String BAND_VAL_URL             = "https://raw.githubusercontent.com/cosmostation/cosmostation_token_resource/master/moniker/bandprotocol/";
    public final static String IOV_VAL_URL              = "https://raw.githubusercontent.com/cosmostation/cosmostation_token_resource/master/moniker/iov/";
    public final static String CERTIK_VAL_URL           = "https://raw.githubusercontent.com/cosmostation/cosmostation_token_resource/master/moniker/certik/";
    public final static String SECRET_VAL_URL            = "https://raw.githubusercontent.com/cosmostation/cosmostation_token_resource/master/moniker/secret/";
    public final static String AKASH_VAL_URL            = "https://raw.githubusercontent.com/cosmostation/cosmostation_token_resource/master/moniker/akash/";
    public final static String OKEX_COIN_IMG_URL        = "https://raw.githubusercontent.com/cosmostation/cosmostation_token_resource/master/coin_image/okex/";


    public final static String EXPLORER_COSMOS_MAIN     = "https://www.mintscan.io/cosmos/";
    public final static String EXPLORER_IRIS_MAIN       = "https://www.mintscan.io/iris/";
    public final static String EXPLORER_KAVA_MAIN       = "https://www.mintscan.io/kava/";
    public final static String EXPLORER_IOV_MAIN        = "https://www.mintscan.io/starname/";
    public final static String EXPLORER_BINANCE_MAIN    = "https://binance.mintscan.io/";
    public final static String EXPLORER_BAND_MAIN       = "https://cosmoscan.io/";
    public final static String EXPLORER_SECRET_MAIN     = "https://explorer.cashmaney.com/";
    public final static String EXPLORER_AKASH_MAIN      = "https://www.mintscan.io/akash/";
    public final static String EXPLORER_OKEX_MAIN       = "https://www.oklink.com/okexchain/";

    public final static String EXPLORER_BINANCE_TEST    = "https://testnet-explorer.binance.org/";
    public final static String EXPLORER_KAVA_TEST       = "https://kava-testnet-9000.mintscan.io/";
    public final static String EXPLORER_OKEX_TEST       = "https://www.oklink.com/okexchain-test/";
    public final static String EXPLORER_CERTIK          = "https://explorer.certik.foundation/";



    public final static String  PERSISTENCE_COSMOS_EVENT_ADDRESS    = "cosmos1ea6cx6km3jmryax5aefq0vy5wrfcdqtaau4f22";
    public final static int     PERSISTENCE_COSMOS_EVENT_START      = 3846000;
    public final static int     PERSISTENCE_COSMOS_EVENT_END        = 4206000;

    public final static String  PERSISTENCE_KAVA_EVENT_ADDRESS      = "kava1fxxxruhmqx3myuhjwxx9gk90kwqrgs9jamr892";
    public final static int     PERSISTENCE_KAVA_EVENT_START        = 422360;
    public final static int     PERSISTENCE_KAVA_EVENT_END          = 672440;




    public final static BigDecimal DAY_SEC = new BigDecimal("86400");
    public final static BigDecimal MONTH_SEC = DAY_SEC.multiply(new BigDecimal("30"));
    public final static BigDecimal YEAR_SEC = DAY_SEC.multiply(new BigDecimal("365"));

    public final static BigDecimal BLOCK_TIME_COSMOS = new BigDecimal("7.31");
    public final static BigDecimal BLOCK_TIME_IRIS = new BigDecimal("6.93");
    public final static BigDecimal BLOCK_TIME_IOV = new BigDecimal("6.21");
    public final static BigDecimal BLOCK_TIME_KAVA = new BigDecimal("8.01");
    public final static BigDecimal BLOCK_TIME_BAND = new BigDecimal("3.01");
    public final static BigDecimal BLOCK_TIME_CERTIK = new BigDecimal("5.75");
    public final static BigDecimal BLOCK_TIME_SECRET = new BigDecimal("5.96");
    public final static BigDecimal BLOCK_TIME_AKASH = new BigDecimal("6.21");
}
