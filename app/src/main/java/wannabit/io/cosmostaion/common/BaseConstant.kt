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
    const val CONST_RESTORE_QR_ACCOUNT = 2003

    const val COSMOS_AUTH_TYPE_STDTX = "auth/StdTx"
    const val OK_MSG_TYPE_TRANSFER = "okexchain/token/MsgTransfer"
    const val OK_MSG_TYPE_DEPOSIT = "okexchain/staking/MsgDeposit"
    const val OK_MSG_TYPE_WITHDRAW = "okexchain/staking/MsgWithdraw"
    const val OK_MSG_TYPE_ADD_SHARES = "okexchain/staking/MsgAddShares"

    const val COSMOS_KEY_TYPE_PUBLIC = "tendermint/PubKeySecp256k1"
    const val ETHERMINT_KEY_TYPE_PUBLIC = "ethermint/PubKeyEthSecp256k1"
    const val INJECTIVE_KEY_TYPE_PUBLIC = "injective/PubKeyEthSecp256k1"

    const val CONSTANT_S = 1000L
    const val CONSTANT_10S = CONSTANT_S * 10
    const val CONSTANT_30S = CONSTANT_S * 30
    const val CONSTANT_M = CONSTANT_S * 60
    const val CONSTANT_H = CONSTANT_M * 60
    const val CONSTANT_D = CONSTANT_H * 24
    const val BASE_GAS_AMOUNT = "800000"

    // ICNS Contract Address
    const val ICNS_OSMOSIS_ADDRESS =
        "osmo1xk0s8xgktn9x5vwcgtjdxqzadg88fgn33p8u9cnpdxwemvxscvast52cdd"
    const val NS_STARGZE_ADDRESS =
        "stars1fx74nkqkw2748av8j7ew7r3xt9cgjqduwn8m0ur5lhe49uhlsasszc5fhr"
    const val NS_ARCHWAY_ADDRESS =
        "archway1275jwjpktae4y4y0cdq274a2m0jnpekhttnfuljm6n59wnpyd62qppqxq0"
}