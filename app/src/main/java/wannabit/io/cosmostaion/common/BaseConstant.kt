package wannabit.io.cosmostaion.common

object BaseConstant {
    const val DB_NAME = "WannaBit"
    const val DB_VERSION = 7
    const val DB_TABLE_PASSWORD = "paswd"
    const val DB_TABLE_ACCOUNT = "accnt"
    const val DB_TABLE_MNEMONIC = "mnemonic"
    const val DB_TABLE_BALANCE = "balan"
    const val DB_TABLE_BONDING = "bondi"
    const val DB_TABLE_UNBONDING = "unbond"

    const val SUCCESS = "Success"
    const val FAIL = "Fail"

    const val CONST_NEW_ACCOUNT = 2000
    const val CONST_RESTORE_MNEMONIC_ACCOUNT = 2001
    const val CONST_RESTORE_PRIVATE_ACCOUNT = 2002

    const val COSMOS_AUTH_TYPE_STDTX = "auth/StdTx"
    const val OK_MSG_TYPE_TRANSFER = "okexchain/token/MsgTransfer"
    const val OK_MSG_TYPE_DEPOSIT = "okexchain/staking/MsgDeposit"
    const val OK_MSG_TYPE_WITHDRAW = "okexchain/staking/MsgWithdraw"
    const val OK_MSG_TYPE_ADD_SHARES = "okexchain/staking/MsgAddShares"

    const val COSMOS_KEY_TYPE_PUBLIC = "tendermint/PubKeySecp256k1"
    const val ETHERMINT_KEY_TYPE_PUBLIC = "ethermint/PubKeyEthSecp256k1"
    const val INJECTIVE_KEY_TYPE_PUBLIC = "injective/PubKeyEthSecp256k1"

    //HTLC swap support Token Types
//    const val TOKEN_HTLC_KAVA_BNB = "bnb"
//    const val TOKEN_HTLC_KAVA_BTCB = "btcb"
//    const val TOKEN_HTLC_KAVA_XRPB = "xrpb"
//    const val TOKEN_HTLC_KAVA_BUSD = "busd"
//    const val TOKEN_HTLC_BINANCE_BNB = "BNB"
//    const val TOKEN_HTLC_BINANCE_BTCB = "BTCB-1DE"
//    const val TOKEN_HTLC_BINANCE_XRPB = "XRP-BF2"
//    const val TOKEN_HTLC_BINANCE_BUSD = "BUSD-BD1"
    const val CONSTANT_S = 1000L
    const val CONSTANT_10S = CONSTANT_S * 10
    const val CONSTANT_30S = CONSTANT_S * 30
    const val CONSTANT_M = CONSTANT_S * 60
    const val CONSTANT_H = CONSTANT_M * 60
    const val CONSTANT_D = CONSTANT_H * 24
    const val BASE_GAS_AMOUNT = "800000"

//    const val BINANCE_MAIN_BNB_DEPUTY = "bnb1jh7uv2rm6339yue8k4mj9406k3509kr4wt5nxn"
//    const val KAVA_MAIN_BNB_DEPUTY = "kava1r4v2zdhdalfj2ydazallqvrus9fkphmglhn6u6"
//    const val BINANCE_MAIN_BTCB_DEPUTY = "bnb1xz3xqf4p2ygrw9lhp5g5df4ep4nd20vsywnmpr"
//    const val KAVA_MAIN_BTCB_DEPUTY = "kava14qsmvzprqvhwmgql9fr0u3zv9n2qla8zhnm5pc"
//    const val BINANCE_MAIN_XRPB_DEPUTY = "bnb15jzuvvg2kf0fka3fl2c8rx0kc3g6wkmvsqhgnh"
//    const val KAVA_MAIN_XRPB_DEPUTY = "kava1c0ju5vnwgpgxnrktfnkccuth9xqc68dcdpzpas"
//    const val BINANCE_MAIN_BUSD_DEPUTY = "bnb10zq89008gmedc6rrwzdfukjk94swynd7dl97w8"
//    const val KAVA_MAIN_BUSD_DEPUTY = "kava1hh4x3a4suu5zyaeauvmv7ypf7w9llwlfufjmuu"
//
//    // Exchange Address
//    const val EXCHANGE_BINANCE_ADDRESS_01 = "cosmos1j8pp7zvcu9z8vd882m284j29fn2dszh05cqvf9"
//    const val EXCHANGE_BINANCE_ADDRESS_02 = "cosmos15v50ymp6n5dn73erkqtmq0u8adpl8d3ujv2e74"
//    const val EXCHANGE_BINANCE_ADDRESS_03 = "osmo129uhlqcsvmehxgzcsdxksnsyz94dvea907e575"
//    const val EXCHANGE_BITHUMB_ADDRESS = "cosmos1erzjr93gcqgvcs7azqaglrjqy5ntzn3da5p340"
//    const val EXCHANGE_UPBIT_ADDRESS = "cosmos1hjyde2kfgtl78twvhs53u5j2gcsxrt649nn8j5"
//    const val EXCHANGE_COINONE_ADDRESS = "cosmos1z7g5w84ynmjyg0kqpahdjqpj7yq34v3suckp0e"
//    const val EXCHANGE_MEXC_ADDRESS = "cosmos144sh8vyv5nqfylmg4mlydnpe3l4w780jsrmf4k"
//    const val EXCHANGE_HITBTC_ADDRESS = "cosmos1ghz39h0zkugxs3tst8mfvsy2g98xdaah83xl0t"
//    const val EXCHANGE_DIGFINEX_ADDRESS = "cosmos10njsfnzz5lqch2p5362ueyyus98dje0vdsmds7"

    // ICNS Contract Address
    const val ICNS_OSMOSIS_ADDRESS =
        "osmo1xk0s8xgktn9x5vwcgtjdxqzadg88fgn33p8u9cnpdxwemvxscvast52cdd"
    const val NS_STARGZE_ADDRESS =
        "stars1fx74nkqkw2748av8j7ew7r3xt9cgjqduwn8m0ur5lhe49uhlsasszc5fhr"
    const val NS_ARCHWAY_ADDRESS =
        "archway1275jwjpktae4y4y0cdq274a2m0jnpekhttnfuljm6n59wnpyd62qppqxq0"
}