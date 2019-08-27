package wannabit.io.cosmostaion.base;

public class BaseConstant {
    public final static boolean                 IS_SHOWLOG                                      = true;
    public final static boolean                 IS_TEST                                         = true;
    public final static boolean                 IS_FEE_FREE 					                = false;
    public final static String                  LOG_TAG                                         = "Cosmostation";

    public final static String                  DB_NAME			                                = "WannaBit";
    public final static int                     DB_VERSION			                            = 2;
    public final static String                  DB_TABLE_PASSWORD			                    = "paswd";
    public final static String                  DB_TABLE_ACCOUNT			                    = "accnt";
    public final static String                  DB_TABLE_BALANCE			                    = "balan";
    public final static String                  DB_TABLE_BONDING			                    = "bondi";
    public final static String                  DB_TABLE_UNBONDING			                    = "unbond";
    public final static String                  DB_TABLE_PRICE			                        = "price";

    public final static String                  PRE_USER_ID			                            = "PRE_USER_ID";
    public final static String                  PRE_VALIDATOR_SORTING			                = "PRE_VALIDATOR_SORTING";
    public final static String                  PRE_MY_VALIDATOR_SORTING			            = "PRE_MY_VALIDATOR_SORTING";
    public final static String                  PRE_ATOM_TIC			                        = "PRE_ATOM_TIC";
    public final static String                  PRE_ATOM_UP_DOWN_24			                    = "PRE_ATOM_UP_DOWN_24";
    public final static String                  PRE_IRIS_TIC			                        = "PRE_IRIS_TIC";
    public final static String                  PRE_IRIS_UP_DOWN_24			                    = "PRE_IRIS_UP_DOWN_24";
    public final static String                  PRE_BNB_TIC                                     = "PRE_BNB_TIC";
    public final static String                  PRE_BNB_UP_DOWN_24			                    = "PRE_BNB_UP_DOWN_24";
    public final static String                  PRE_CURRENCY			                        = "PRE_CURRENCY";
    public final static String                  PRE_MARKET  			                        = "PRE_MARKET";
    public final static String                  PRE_USING_APP_LOCK			                    = "PRE_USING_APP_LOCK";
    public final static String                  PRE_USING_FINGERPRINT			                = "PRE_USING_FINGERPRINT";
    public final static String                  PRE_APP_LOCK_TIME			                    = "PRE_APP_LOCK_TIME";
    public final static String                  PRE_APP_LOCK_LEAVE_TIME			                = "PRE_APP_LOCK_LEAVE_TIME";



    public final static int                     TASK_INIT_PW		                            = 2000;
    public final static int                     TASK_INIT_ACCOUNT		                        = 2002;
    public final static int                     TASK_INIT_EMPTY_ACCOUNT		                    = 2003;
    public final static int                     TASK_FETCH_ACCOUNT		                        = 2004;
    public final static int                     TASK_FETCH_ALL_VALIDATOR		                = 2005;
    public final static int                     TASK_FETCH_BONDING_STATE		                = 2006;
    public final static int                     TASK_FETCH_UNBONDING_STATE		                = 2007;
    public final static int                     TASK_FETCH_TOTAL_REWARDS		                = 2008;
    public final static int                     TASK_FETCH_SINGLE_VALIDATOR		                = 2009;
    public final static int                     TASK_FETCH_SINGLE_BONDING			            = 2010;
    public final static int                     TASK_FETCH_SINGLE_UNBONDING			            = 2011;
    public final static int                     TASK_FETCH_SINGLE_REWARD		                = 2012;
    public final static int                     TASK_FETCH_SINGLE_SELF_BONDING		            = 2013;
    public final static int                     TASK_PASSWORD_CHECK		                        = 2015;
    public final static int                     TASK_FETCH_WITHDRAW_ADDRESS		                = 2016;
    public final static int                     TASK_FETCH_HISTORY	                            = 2017;
    public final static int                     TASK_GEN_TX_SIMPLE_SEND	                        = 2018;
    public final static int                     TASK_OVERRIDE_ACCOUNT                           = 2019;
    public final static int                     TASK_GEN_TX_SIMPLE_DELEGATE	                    = 2020;
    public final static int                     TASK_GEN_TX_SIMPLE_UNDELEGATE	                = 2021;
    public final static int                     TASK_GEN_TX_SIMPLE_REWARD	                    = 2022;
    public final static int 				    TASK_FETCH_ALL_PROPOSAL		                    = 2023;
    public final static int 				    TASK_DELETE_USER		                        = 2024;
    public final static int 				    TASK_CHECK_MNEMONIC		                        = 2025;
    public final static int 				    TASK_FETCH_VAL_HISTORY	                        = 2026;
    public final static int 				    TASK_FETCH_UNBONDING_VALIDATOR		            = 2027;
    public final static int 				    TASK_FETCH_UNBONDED_VALIDATOR		            = 2028;
    public final static int 				    TASK_FETCH_SINGLE_REDELEGATE		            = 2029;
    public final static int 				    TASK_FETCH_SINGLE_ALL_REDELEGATE	            = 2030;
    public final static int 				    TASK_GEN_TX_SIMPLE_REDELEGATE	                = 2031;
    public final static int 				    TASK_GEN_TX_SIMPLE_REWARD_ADDRESS_CHANGE        = 2032;
    public final static int 				    TASK_FETCH_INFLATION		                    = 2033;
    public final static int 				    TASK_FETCH_PROVISIONS		                    = 2034;
    public final static int 				    TASK_FETCH_STAKING_POOL		                    = 2035;
    public final static int 				    TASK_GEN_TX_REINVEST	                        = 2036;
    public final static int 				    TASK_IRIS_REWARD	                            = 2037;
    public final static int 				    TASK_IRIS_POOL	                                = 2038;
    public final static int 				    TASK_IRIS_PROPOSAL		                        = 2039;
    public final static int 				    TASK_IRIS_REDELEGATE        		            = 2040;


    public final static String                  COSMOS_AUTH_TYPE_STDTX                          = "auth/StdTx";

    public final static String                  COSMOS_AUTH_TYPE_DELAYEDACCOUNT                 = "cosmos-sdk/DelayedVestingAccount";
    public final static String                  COSMOS_AUTH_TYPE_ACCOUNT                        = "cosmos-sdk/Account";

    public final static String                  COSMOS_MSG_TYPE_TRANSFER                        = "cosmos-sdk/Send";
    public final static String                  COSMOS_MSG_TYPE_TRANSFER2                       = "cosmos-sdk/MsgSend";
    public final static String                  COSMOS_MSG_TYPE_DELEGATE                        = "cosmos-sdk/MsgDelegate";
    public final static String                  COSMOS_MSG_TYPE_UNDELEGATE                      = "cosmos-sdk/Undelegate";
    public final static String                  COSMOS_MSG_TYPE_UNDELEGATE2                     = "cosmos-sdk/MsgUndelegate";
    public final static String                  COSMOS_MSG_TYPE_REDELEGATE                      = "cosmos-sdk/BeginRedelegate";
    public final static String                  COSMOS_MSG_TYPE_REDELEGATE2                     = "cosmos-sdk/MsgBeginRedelegate";
    public final static String                  COSMOS_MSG_TYPE_WITHDRAW_DEL                    = "cosmos-sdk/MsgWithdrawDelegationReward";
    public final static String                  COSMOS_MSG_TYPE_WITHDRAW_VAL                    = "cosmos-sdk/MsgWithdrawValidatorCommission";
    public final static String                  COSMOS_MSG_TYPE_WITHDRAW_MIDIFY                 = "cosmos-sdk/MsgModifyWithdrawAddress";
    public final static String                  COSMOS_MSG_TYPE_VOTE                            = "cosmos-sdk/MsgVote";
    public final static String                  COSMOS_MSG_TYPE_SUBMIT_PROPOSAL                 = "cosmos-sdk/MsgSubmitProposal";
    public final static String                  COSMOS_MSG_TYPE_DEPOSIT                         = "cosmos-sdk/MsgDeposit";
    public final static String                  COSMOS_MSG_TYPE_CREATE_VALIDATOR                = "cosmos-sdk/MsgCreateValidator";
    public final static String                  COSMOS_MSG_TYPE_EDIT_VALIDATOR                  = "cosmos-sdk/MsgEditValidator";




    public final static String                  COSMOS_BANK_TYPE_STDTX                          = "irishub/bank/StdTx";

    public final static String                  IRIS_BANK_TYPE_ACCOUNT                          = "irishub/bank/Account";

    public final static String                  IRIS_MSG_TYPE_TRANSFER                          = "irishub/bank/Send";
    public final static String                  IRIS_MSG_TYPE_DELEGATE                          = "irishub/stake/MsgDelegate";
    public final static String                  IRIS_MSG_TYPE_UNDELEGATE                        = "irishub/stake/BeginUnbonding";
    public final static String                  IRIS_MSG_TYPE_REDELEGATE                        = "irishub/stake/BeginRedelegate";
    public final static String                  IRIS_MSG_TYPE_WITHDRAW                          = "irishub/distr/MsgWithdrawDelegationReward";
    public final static String                  IRIS_MSG_TYPE_WITHDRAW_ALL                      = "irishub/distr/MsgWithdrawDelegationRewardsAll";
    public final static String                  IRIS_MSG_TYPE_VOTE                              = "irishub/gov/MsgVote";
    public final static String                  IRIS_MSG_TYPE_DEPOSIT                           = "irishub/gov/MsgDeposit";
    public final static String                  IRIS_MSG_TYPE_SUBMIT_PROPOSAL                   = "irishub/gov/MsgSubmitProposal";
    public final static String                  IRIS_MSG_TYPE_CREATE_VALIDATOR                  = "irishub/stake/MsgCreateValidator";
    public final static String                  IRIS_MSG_TYPE_WITHDRAW_MIDIFY                   = "irishub/distr/MsgModifyWithdrawAddress";




    public final static String                  COSMOS_KEY_TYPE_PUBLIC                          = "tendermint/PubKeySecp256k1";


    public final static String                  CONST_PW_PURPOSE		                        = "CONST_PW_PURPOSE";
    public final static int                     CONST_PW_INIT		                            = 5000;
    public final static int                     CONST_PW_UNLOUCK		                        = 5001;
    public final static int                     CONST_PW_SIMPLE_CHECK		                    = 5002;
    public final static int                     CONST_PW_TX_SIMPLE_SEND		                    = 5003;
    public final static int                     CONST_PW_TX_SIMPLE_DELEGATE		                = 5004;
    public final static int                     CONST_PW_TX_SIMPLE_UNDELEGATE		            = 5005;
    public final static int                     CONST_PW_TX_SIMPLE_REWARD		                = 5006;
    public final static int                     CONST_PW_DELETE_ACCOUNT		                    = 5007;
    public final static int                     CONST_PW_CHECK_MNEMONIC	                        = 5008;
    public final static int                     CONST_PW_TX_SIMPLE_REDELEGATE		            = 5009;
    public final static int                     CONST_PW_TX_SIMPLE_CHANGE_REWARD_ADDRESS        = 5010;
    public final static int                     CONST_PW_TX_REINVEST		                    = 5011;


    public final static int                     TX_TYPE_UNKNOWN		                            = 3000;
    public final static int                     TX_TYPE_SEND		                            = 3001;
    public final static int                     TX_TYPE_RECEIVE		                            = 3002;
    public final static int                     TX_TYPE_DELEGATE		                        = 3003;
    public final static int                     TX_TYPE_UNDELEGATE		                        = 3004;
    public final static int                     TX_TYPE_REDELEGATE		                        = 3005;
    public final static int                     TX_TYPE_GET_REWARD		                        = 3006;
    public final static int                     TX_TYPE_GET_CPMMISSION		                    = 3007;
    public final static int                     TX_TYPE_CHAGE_REWARD_ADDRESS		            = 3008;
    public final static int                     TX_TYPE_TRANSFER		                        = 3009;
    public final static int                     TX_TYPE_VOTE		                            = 3010;
    public final static int                     TX_TYPE_SUBMIT_PROPOSAL		                    = 3011;
    public final static int                     TX_TYPE_DEPOSIT		                            = 3012;
    public final static int                     TX_TYPE_CREATE_VALIDATOR		                = 3013;
    public final static int                     TX_TYPE_EDIT_VALIDATOR		                    = 3014;
    public final static int                     TX_TYPE_REINVEST		                        = 3015;
    public final static int                     TX_TYPE_IRIS_GET_REWARD_ALL		                = 3016;


    public final static int                     ERROR_CODE_UNKNOWN		                        = 8000;
    public final static int                     ERROR_CODE_NETWORK		                        = 8001;
    public final static int                     ERROR_CODE_INVALID_PASSWORD		                = 8002;
    public final static int                     ERROR_CODE_TIMEOUT	                            = 8003;
    public final static int                     ERROR_CODE_BROADCAST	                        = 8004;


    public final static String                  COSMOS_ATOM                                     = "uatom";
    public final static String                  COSMOS_MUON                                     = "muon";
    public final static String                  COSMOS_PHOTON                                   = "photon";
    public final static String                  COSMOS_PHOTINO                                  = "photino";
    public final static String                  COSMOS_IRIS_ATTO                                = "iris-atto";
    public final static String                  COSMOS_BNB                                      = "BNB";


    //TODO HardCoding!!
    public final static long                    COSMOS_UNBONDING_TIME                           = 1814400000;
    public final static long                    COSMOS_UNBONDING_DAY                            = 3;


    public final static String                  KEY_PATH		                                = "44'/118'/0'/0/";
    public final static String                  KEY_BNB_PATH		                            = "44'/714'/0'/0/";
    public final static String                  characterFilter                                 = "[^\\p{L}\\p{M}\\p{N}\\p{P}\\p{Z}\\p{Cf}\\p{Cs}\\s]";


    public final static long					CONSTANT_S			                            = 1000l;
    public final static long					CONSTANT_10S			                        = CONSTANT_S * 10;
    public final static long					CONSTANT_30S			                        = CONSTANT_S * 30;
    public final static long					CONSTANT_M			                            = CONSTANT_S * 60;
    public final static long					CONSTANT_H			                            = CONSTANT_M * 60;
    public final static long					CONSTANT_D			                            = CONSTANT_H * 24;


    public final static int                     MEMO_ATOM                                        = 255;
    public final static int                     MEMO_IRIS                                        = 100;

    public final static String                  FEE_GAS_RATE_LOW                                = "0.0025";
    public final static String                  FEE_GAS_RATE_AVERAGE                            = "0.025";


    public final static String                  FEE_GAS_AMOUNT_HALF                             = "100000";
    public final static String                  FEE_GAS_AMOUNT_AVERAGE                          = "200000";
    public final static String                  FEE_GAS_AMOUNT_REDELEGATE                       = "240000";
    public final static String                  FEE_GAS_AMOUNT_REINVEST                         = "220000";


    public final static String                  FEE_IRIS_GAS_RATE_AVERAGE                       = "0.000008";

    public final static String                  FEE_IRIS_GAS_AMOUNT_SEND                        = "25000";
    public final static String                  FEE_IRIS_GAS_AMOUNT_AVERAGE                     = "50000";
    public final static String                  FEE_IRIS_GAS_AMOUNT_REDELEGATE                  = "65000";
    public final static String                  FEE_IRIS_GAS_AMOUNT_REWARD_BASE                 = "10000";
    public final static String                  FEE_IRIS_GAS_AMOUNT_REWARD_MUX                  = "5000";


    public final static String                  CGC_ATOM                                        = "cosmos";
    public final static String                  CGC_IRIS                                        = "iris-network";
    public final static String                  CGC_BNB                                         = "binancecoin";
    public final static int                     CMC_ATOM                                        = 3794;
    public final static int                     CMC_IRIS                                        = 3874;
    public final static int                     CMC_BNB                                         = 1839;
}
