package wannabit.io.cosmostaion.base;

import java.math.BigDecimal;

public class BaseConstant {
    public final static boolean SUPPORT_BEP3_SWAP = true;
    public final static String LOG_TAG = "Cosmostation";
    public final static String POSITION = "position";

    public final static String DB_NAME = "WannaBit";
    public final static int DB_VERSION = 7;
    public final static String DB_TABLE_PASSWORD = "paswd";
    public final static String DB_TABLE_ACCOUNT = "accnt";
    public final static String DB_TABLE_BALANCE = "balan";
    public final static String DB_TABLE_BONDING = "bondi";
    public final static String DB_TABLE_UNBONDING = "unbond";
    public final static String DB_TABLE_MNEMONIC = "mnemonic";

    public final static String PRE_USER_ID = "PRE_USER_ID";
    public final static String PRE_SELECTED_CHAINS = "PRE_SELECTED_CHAINS";
    public final static String PRE_VALIDATOR_SORTING = "PRE_VALIDATOR_SORTING";
    public final static String PRE_MY_VALIDATOR_SORTING = "PRE_MY_VALIDATOR_SORTING";
    public final static String PRE_ALARM_STATUS = "PRE_ALARM_STATUS";
    public final static String PRE_CURRENCY = "PRE_CURRENCY";
    public final static String PRE_USING_APP_LOCK = "PRE_USING_APP_LOCK";
    public final static String PRE_USING_FINGERPRINT = "PRE_USING_FINGERPRINT";
    public final static String PRE_USING_PASS = "PRE_USING_AUTO_PASS";
    public final static String PRE_LAST_PASS_TIME = "PRE_LAST_PASS_TIME";
    public final static String PRE_LAST_PRICE_TIME = "PRE_LAST_PRICE_TiME";
    public final static String PRE_FCM_TOKEN = "PRE_FCM_TOKEN_NEW";
    public final static String PRE_USER_HIDEN_CHAINS = "PRE_USER_HIDEN_CHAINS";
    public final static String PRE_USER_SORTED_CHAINS = "PRE_USER_SORTED_CHAINS";
    public final static String PRE_USER_EXPENDED_CHAINS = "PRE_USER_EXPENDED_CHAINS";
    public final static String PRE_USER_FAVO_TOKENS = "PRE_USER_FAVO_TOKENS";
    public final static String PRE_DB_VERSION = "PRE_DB_VERSION";
    public final static String PRE_THEME_MOD = "PRE_THEME_MOD";
    public final static String PRE_LANGUAGE = "PRE_LANGUAGE";
    public final static String PRE_AUTO_SIGN = "PRE_AUTO_SIGN";
    public final static String PRE_WC_WHITE_LIST = "PRE_WC_WHITE_LIST";
    public final static String PRE_PRICE_COLOR = "PRE_PRICE_COLOR";


    public final static int TASK_FETCH_ACCOUNT = 2004;
    public final static int TASK_FETCH_BNB_HISTORY = 2041;
    public final static int TASK_FETCH_BNB_TOKENS = 2042;
    public final static int TASK_FETCH_BNB_TICKER = 2043;
    public final static int TASK_FETCH_BNB_MINI_TICKER = 2044;
    public final static int TASK_FETCH_KAVA_CDP_DEPOSIT = 2061;
    public final static int TASK_FETCH_KAVA_INCENTIVE_REWARD = 2076;
    public final static int TASK_FETCH_BNB_MINI_TOKENS = 2078;
    public final static int TASK_GEN_TX_HTLC_CREATE = 2079;
    public final static int TASK_GEN_TX_HTLC_CLAIM = 2080;
    public final static int TASK_FETCH_BNB_FEES = 2081;
    public final static int TASK_FETCH_OK_STAKING_INFO = 2082;
    public final static int TASK_FETCH_OK_UNBONDING_INFO = 2083;
    public final static int TASK_FETCH_OK_ACCOUNT_BALANCE = 2084;
    public final static int TASK_FETCH_OK_TOKEN_LIST = 2085;
    public final static int TASK_FETCH_OK_HISTORY = 2113;
    public final static int TASK_FETCH_KAVA_HARD_MODULE_ACCOUNT = 2123;
    public final static int TASK_FETCH_NODE_INFO = 2126;
    public final static int TASK_FETCH_OKEX_ALL_VALIDATORS = 2127;

    public final static int TASK_FETCH_API_ADDRESS_HISTORY = 2300;
    public final static int TASK_FETCH_API_STAKE_HISTORY = 2301;

    public final static int TASK_FETCH_MINTSCAN_PROPOSAL = 2804;
    public final static int TASK_FETCH_MINTSCAN_CW20_ASSETS = 2807;
    public final static int TASK_FETCH_MINTSCAN_PRICES = 2808;
    public final static int TASK_FETCH_MINTSCAN_ERC20_ASSETS = 2809;


    //gRPC
    public final static int TASK_GRPC_FETCH_BALANCE = 4001;
    public final static int TASK_GRPC_FETCH_BONDED_VALIDATORS = 4002;
    public final static int TASK_GRPC_FETCH_UNBONDED_VALIDATORS = 4003;
    public final static int TASK_GRPC_FETCH_UNBONDING_VALIDATORS = 4004;
    public final static int TASK_GRPC_FETCH_DELEGATIONS = 4005;
    public final static int TASK_GRPC_FETCH_UNDELEGATIONS = 4006;
    public final static int TASK_GRPC_FETCH_ALL_REWARDS = 4007;
    public final static int TASK_GRPC_FETCH_COMMISSION = 4008;
    public final static int TASK_GRPC_FETCH_STAKING_POOL = 4012;
    public final static int TASK_GRPC_FETCH_VALIDATOR_INFO = 4014;
    public final static int TASK_GRPC_FETCH_SELF_BONDING = 4015;
    public final static int TASK_GRPC_FETCH_WITHDRAW_ADDRESS = 4016;
    public final static int TASK_GRPC_FETCH_REDELEGATIONS_TO = 4017;
    public final static int TASK_GRPC_FETCH_REDELEGATIONS_FROM_TO = 4018;
    public final static int TASK_GRPC_FETCH_PROPOSAL_MY_VOTE = 4023;
    public final static int TASK_GRPC_FETCH_NODE_INFO = 4024;
    public final static int TASK_GRPC_FETCH_AUTH = 4025;
    public final static int TASK_GRPC_FETCH_TOTAL_SUPPLY = 4029;

    public final static int TASK_GRPC_FETCH_STARNAME_FEE = 4101;
    public final static int TASK_GRPC_FETCH_STARNAME_CONFIG = 4102;
    public final static int TASK_GRPC_FETCH_STARNAME_ACCOUNT = 4103;
    public final static int TASK_GRPC_FETCH_STARNAME_DOMAIN = 4104;
    public final static int TASK_GRPC_FETCH_STARNAME_RESOLVE = 4105;
    public final static int TASK_GRPC_FETCH_STARNAME_DOMAIN_INFO = 4106;

    public final static int TASK_FETCH_OSMOSIS_POOL_LIST = 4201;
    public final static int TASK_GRPC_FETCH_OSMOSIS_POOL_INFO = 4202;

    public final static int TASK_GRPC_FETCH_SIF_POOL_LIST = 4250;
    public final static int TASK_GRPC_FETCH_SIF_POOL_INFO = 4251;
    public final static int TASK_GRPC_FETCH_SIF_POOL_ASSET_LIST = 4252;
    public final static int TASK_GRPC_FETCH_SIF_MY_PROVIDER = 4253;

    public final static int TASK_GRPC_FETCH_NFTOKEN_LIST = 4270;
    public final static int TASK_GRPC_FETCH_NFTOKEN_INFO = 4271;

    public final static int TASK_GRPC_FETCH_KAVA_PRICES = 4277;
    public final static int TASK_GRPC_FETCH_KAVA_PRICE_TOKEN = 4278;
    public final static int TASK_GRPC_FETCH_KAVA_SWAP_PARAMS = 4279;
    public final static int TASK_GRPC_FETCH_KAVA_SWAP_POOLS = 4280;
    public final static int TASK_GRPC_FETCH_KAVA_SWAP_DEPOSITS = 4281;
    public final static int TASK_GRPC_FETCH_KAVA_SWAP_POOLS_INFO = 4282;
    public final static int TASK_GRPC_FETCH_KAVA_CDP_PARAMS = 4283;
    public final static int TASK_GRPC_FETCH_KAVA_MY_CDPS = 4284;
    public final static int TASK_GRPC_FETCH_KAVA_CDP_BY_DEPOSITOR = 4285;
    public final static int TASK_GRPC_FETCH_KAVA_HARD_PARAMS = 4286;
    public final static int TASK_GRPC_FETCH_KAVA_HARD_MY_DEPOSIT = 4287;
    public final static int TASK_GRPC_FETCH_KAVA_HARD_MY_BORROW = 4288;
    public final static int TASK_GRPC_FETCH_KAVA_HARD_INTEREST_RATE = 4289;
    public final static int TASK_GRPC_FETCH_KAVA_HARD_MODULE_ACCOUNT = 4290;
    public final static int TASK_GRPC_FETCH_KAVA_HARD_RESERVES = 4291;
    public final static int TASK_GRPC_FETCH_KAVA_HARD_TOTAL_DEPOSIT = 4292;
    public final static int TASK_GRPC_FETCH_KAVA_HARD_TOTAL_BORROW = 4293;

    public final static int TASK_GRPC_FETCH_AUTHZ_GRANTER_LIST = 4294;
    public final static int TASK_GRPC_FETCH_AUTHZ_GRANT_LIST = 4295;

    public final static int TASK_GRPC_FETCH_ALL_HOST_ZONE = 4300;
    public final static int TASK_GRPC_FETCH_EPOCH_TRACKER = 4301;
    public final static int TASK_GRPC_FETCH_HOST_ZONE_CHAINID = 4302;
    public final static int TASK_GRPC_FETCH_ALL_USER_REDEMPTION = 4303;

    public final static int TASK_GRPC_FETCH_OSMOSIS_ICNS = 4310;
    public final static int TASK_GRPC_FETCH_STARGAZE_NS = 4311;


    public final static String COSMOS_AUTH_TYPE_STDTX = "auth/StdTx";
    public final static String COSMOS_AUTH_TYPE_OKEX_ACCOUNT = "okexchain/EthAccount";

    public final static String COSMOS_MSG_TYPE_TRANSFER = "cosmos-sdk/Send";
    public final static String COSMOS_MSG_TYPE_TRANSFER2 = "cosmos-sdk/MsgSend";
    public final static String COSMOS_MSG_TYPE_DELEGATE = "cosmos-sdk/MsgDelegate";
    public final static String COSMOS_MSG_TYPE_UNDELEGATE = "cosmos-sdk/MsgUndelegate";
    public final static String COSMOS_MSG_TYPE_REDELEGATE = "cosmos-sdk/MsgBeginRedelegate";
    public final static String COSMOS_MSG_TYPE_WITHDRAW_DEL = "cosmos-sdk/MsgWithdrawDelegationReward";
    public final static String COSMOS_MSG_TYPE_WITHDRAW_MODIFY = "cosmos-sdk/MsgModifyWithdrawAddress";
    public final static String COSMOS_MSG_TYPE_VOTE = "cosmos-sdk/MsgVote";
    public final static String COSMOS_MSG_TYPE_IBC_TRANSFER = "cosmos-sdk/MsgTransfer";
    public final static String OSMOSIS_MSG_TYPE_SWAP = "osmosis/gamm/swap-exact-amount-in";

    public final static String OK_MSG_TYPE_TRANSFER = "okexchain/token/MsgTransfer";
    public final static String OK_MSG_TYPE_MULTI_TRANSFER = "okexchain/token/MsgMultiTransfer";
    public final static String OK_MSG_TYPE_DEPOSIT = "okexchain/staking/MsgDeposit";
    public final static String OK_MSG_TYPE_WITHDRAW = "okexchain/staking/MsgWithdraw";
    public final static String OK_MSG_TYPE_DIRECT_VOTE = "okexchain/staking/MsgAddShares";

    public final static String IOV_MSG_TYPE_DELETE_ACCOUNT = "starname/DeleteAccount";
    public final static String IOV_MSG_TYPE_DELETE_DOMAIN = "starname/DeleteDomain";
    public final static String IOV_MSG_TYPE_RENEW_DOMAIN = "starname/RenewDomain";
    public final static String IOV_MSG_TYPE_RENEW_ACCOUNT = "starname/RenewAccount";


    public final static String COSMOS_KEY_TYPE_PUBLIC = "tendermint/PubKeySecp256k1";
    public final static String ETHERMINT_KEY_TYPE_PUBLIC = "ethermint/PubKeyEthSecp256k1";

    public final static String CONST_PW_PURPOSE = "CONST_PW_PURPOSE";
    public final static int CONST_PW_DELETE_WALLET = 5001;
    public final static int CONST_PW_TX_SIMPLE_SEND = 5003;
    public final static int CONST_PW_TX_SIMPLE_DELEGATE = 5004;
    public final static int CONST_PW_TX_SIMPLE_UNDELEGATE = 5005;
    public final static int CONST_PW_TX_SIMPLE_REWARD = 5006;
    public final static int CONST_PW_TX_SIMPLE_REDELEGATE = 5009;
    public final static int CONST_PW_TX_SIMPLE_CHANGE_REWARD_ADDRESS = 5010;
    public final static int CONST_PW_TX_REINVEST = 5011;
    public final static int CONST_PW_TX_VOTE = 5012;
    public final static int CONST_PW_TX_CREATE_CDP = 5013;
    public final static int CONST_PW_TX_REPAY_CDP = 5014;
    public final static int CONST_PW_TX_DRAW_DEBT_CDP = 5015;
    public final static int CONST_PW_TX_DEPOSIT_CDP = 5016;
    public final static int CONST_PW_TX_WITHDRAW_CDP = 5017;
    public final static int CONST_PW_TX_CLAIM_INCENTIVE = 5020;
    public final static int CONST_PW_TX_OK_DEPOSIT = 5021;
    public final static int CONST_PW_TX_OK_WITHDRAW = 5022;
    public final static int CONST_PW_TX_OK_DIRECT_VOTE = 5023;
    public final static int CONST_PW_TX_REGISTER_DOMAIN = 5024;
    public final static int CONST_PW_TX_REGISTER_ACCOUNT = 5025;
    public final static int CONST_PW_TX_DELETE_DOMAIN = 5026;
    public final static int CONST_PW_TX_DELETE_ACCOUNT = 5027;
    public final static int CONST_PW_TX_RENEW_DOMAIN = 5028;
    public final static int CONST_PW_TX_RENEW_ACCOUNT = 5029;
    public final static int CONST_PW_TX_REPLACE_STARNAME = 5030;
    public final static int CONST_PW_TX_DEPOSIT_HARD = 5031;
    public final static int CONST_PW_TX_WITHDRAW_HARD = 5032;
    public final static int CONST_PW_TX_BORROW_HARD = 5034;
    public final static int CONST_PW_TX_REPAY_HARD = 5035;
    public final static int CONST_PW_TX_OSMOSIS_SWAP = 5036;
    public final static int CONST_PW_TX_KAVA_SWAP = 5043;
    public final static int CONST_PW_TX_KAVA_JOIN_POOL = 5044;
    public final static int CONST_PW_TX_KAVA_EXIT_POOL = 5045;
    public final static int CONST_PW_TX_IBC_TRANSFER = 5049;
    public final static int CONST_PW_TX_SIF_SWAP = 5051;
    public final static int CONST_PW_TX_SIF_JOIN_POOL = 5052;
    public final static int CONST_PW_TX_SIF_EXIT_POOL = 5053;
    public final static int CONST_PW_TX_MINT_NFT = 5055;
    public final static int CONST_PW_TX_SEND_NFT = 5056;
    public final static int CONST_PW_TX_EXECUTE_CONTRACT = 5057;
    public final static int CONST_PW_TX_IBC_CONTRACT = 5058;
    public final static int CONST_PW_TX_EVM_TRANSFER = 5059;

    public final static int CONST_PW_TX_AUTHZ_DELEGATE = 5060;
    public final static int CONST_PW_TX_AUTHZ_UNDELEGATE = 5061;
    public final static int CONST_PW_TX_AUTHZ_REDELEGATE = 5062;
    public final static int CONST_PW_TX_AUTHZ_SEND = 5063;
    public final static int CONST_PW_TX_AUTHZ_VOTE = 5064;
    public final static int CONST_PW_TX_AUTHZ_CLAIM_REWARD = 5065;
    public final static int CONST_PW_TX_AUTHZ_CLAIM_COMMISSION = 5066;

    public final static int CONST_PW_AUTO_PASS = 5070;
    public final static int CONST_PW_APP_LOCK = 5071;

    public final static int CONST_PW_TX_ADD_LIQUIDITY = 5080;
    public final static int CONST_PW_TX_REMOVE_LIQUIDITY = 5081;
    public final static int CONST_PW_TX_STRIDE_LIQUID_STAKING = 5082;
    public final static int CONST_PW_TX_STRIDE_LIQUID_UNSTAKING = 5083;
    public final static int CONST_PW_TX_PERSIS_LIQUID_STAKING = 5084;
    public final static int CONST_PW_TX_PERSIS_LIQUID_REDEEM = 5085;


    public final static int ERROR_CODE_UNKNOWN = 8000;
    public final static int ERROR_CODE_NETWORK = 8001;
    public final static int ERROR_CODE_INVALID_PASSWORD = 8002;
    public final static int ERROR_CODE_TIMEOUT = 8003;
    public final static int ERROR_CODE_BROADCAST = 8004;


    //HTLC swap support Token Types
    public final static String TOKEN_HTLC_KAVA_BNB = "bnb";
    public final static String TOKEN_HTLC_KAVA_BTCB = "btcb";
    public final static String TOKEN_HTLC_KAVA_XRPB = "xrpb";
    public final static String TOKEN_HTLC_KAVA_BUSD = "busd";

    public final static String TOKEN_HTLC_BINANCE_BNB = "BNB";
    public final static String TOKEN_HTLC_BINANCE_BTCB = "BTCB-1DE";
    public final static String TOKEN_HTLC_BINANCE_XRPB = "XRP-BF2";
    public final static String TOKEN_HTLC_BINANCE_BUSD = "BUSD-BD1";


    public final static long CONSTANT_S = 1000l;
    public final static long CONSTANT_10S = CONSTANT_S * 10;
    public final static long CONSTANT_30S = CONSTANT_S * 30;
    public final static long CONSTANT_M = CONSTANT_S * 60;
    public final static long CONSTANT_H = CONSTANT_M * 60;
    public final static long CONSTANT_D = CONSTANT_H * 24;

    public final static String BASE_GAS_AMOUNT = "500000";
    public final static String FEE_BNB_SEND = "0.000075";
    public final static String FEE_OKC_BASE = "0.0005";

    public final static String KAVA_SLIPPAGE = "30000000000000000";

    public final static String BINANCE_MAIN_BNB_DEPUTY = "bnb1jh7uv2rm6339yue8k4mj9406k3509kr4wt5nxn";
    public final static String KAVA_MAIN_BNB_DEPUTY = "kava1r4v2zdhdalfj2ydazallqvrus9fkphmglhn6u6";
    public final static String BINANCE_MAIN_BTCB_DEPUTY = "bnb1xz3xqf4p2ygrw9lhp5g5df4ep4nd20vsywnmpr";
    public final static String KAVA_MAIN_BTCB_DEPUTY = "kava14qsmvzprqvhwmgql9fr0u3zv9n2qla8zhnm5pc";
    public final static String BINANCE_MAIN_XRPB_DEPUTY = "bnb15jzuvvg2kf0fka3fl2c8rx0kc3g6wkmvsqhgnh";
    public final static String KAVA_MAIN_XRPB_DEPUTY = "kava1c0ju5vnwgpgxnrktfnkccuth9xqc68dcdpzpas";
    public final static String BINANCE_MAIN_BUSD_DEPUTY = "bnb10zq89008gmedc6rrwzdfukjk94swynd7dl97w8";
    public final static String KAVA_MAIN_BUSD_DEPUTY = "kava1hh4x3a4suu5zyaeauvmv7ypf7w9llwlfufjmuu";

    // Exchange Address
    public final static String EXCHANGE_BINANCE_ADDRESS_01 = "cosmos1j8pp7zvcu9z8vd882m284j29fn2dszh05cqvf9";
    public final static String EXCHANGE_BINANCE_ADDRESS_02 = "cosmos15v50ymp6n5dn73erkqtmq0u8adpl8d3ujv2e74";
    public final static String EXCHANGE_BINANCE_ADDRESS_03 = "osmo129uhlqcsvmehxgzcsdxksnsyz94dvea907e575";
    public final static String EXCHANGE_BITHUMB_ADDRESS = "cosmos1erzjr93gcqgvcs7azqaglrjqy5ntzn3da5p340";
    public final static String EXCHANGE_UPBIT_ADDRESS = "cosmos1hjyde2kfgtl78twvhs53u5j2gcsxrt649nn8j5";
    public final static String EXCHANGE_COINONE_ADDRESS = "cosmos1z7g5w84ynmjyg0kqpahdjqpj7yq34v3suckp0e";
    public final static String EXCHANGE_MEXC_ADDRESS = "cosmos144sh8vyv5nqfylmg4mlydnpe3l4w780jsrmf4k";
    public final static String EXCHANGE_HITBTC_ADDRESS = "cosmos1ghz39h0zkugxs3tst8mfvsy2g98xdaah83xl0t";
    public final static String EXCHANGE_DIGFINEX_ADDRESS = "cosmos10njsfnzz5lqch2p5362ueyyus98dje0vdsmds7";


    // ICNS Contract Address
    public final static String ICNS_OSMOSIS_ADDRESS = "osmo1xk0s8xgktn9x5vwcgtjdxqzadg88fgn33p8u9cnpdxwemvxscvast52cdd";
    public final static String NS_STARGZE_ADDRESS = "stars1fx74nkqkw2748av8j7ew7r3xt9cgjqduwn8m0ur5lhe49uhlsasszc5fhr";

    public final static String EXPLORER_NOTICE_MINTSCAN = "https://notice.mintscan.io/";

    public final static String NFT_INFURA = "https://ipfs.infura.io/ipfs/";

    public final static String EXPLORER_BASE_URL = "https://www.mintscan.io/";
    public final static String EXPLORER_TESTNET_URL = "https://testnet.mintscan.io/";
    public final static String CHAIN_BASE_URL = "https://raw.githubusercontent.com/cosmostation/chainlist/master/chain/";
    public final static String COINGECKO_URL = "https://www.coingecko.com/en/coins/";

    //cosmostation
    public final static String COSMOSTATION_HOMEPAGE = "https://www.cosmostation.io/";
    public final static String COSMOSTATION_BLOG = "https://medium.com/cosmostation";
    public final static String COSMOSTATION_TELEGRAM = "https://t.me/cosmostation";
    public final static String COSMOSTATION_TERM_EN = "https://cosmostation.io/service_en";
    public final static String COSMOSTATION_TERM_KR = "https://cosmostation.io/service_kr";
    public final static String COSMOSTATION_PRIVACY_POLICY = "https://cosmostation.io/privacy-policy";
    public final static String COSMOSTATION_GITHUB = "https://github.com/cosmostation/cosmostation-android";


    //NFT Denom Default config
    public final static String STATION_NFT_DENOM = "station";


    public final static BigDecimal DAY_SEC = new BigDecimal("86400");
    public final static BigDecimal MONTH_SEC = DAY_SEC.multiply(new BigDecimal("30"));
    public final static BigDecimal YEAR_SEC = DAY_SEC.multiply(new BigDecimal("365"));

}
