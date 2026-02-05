package wannabit.io.cosmostaion.common

object CosmostationConstants {
    const val LEGACY_DATABASE_KEY = "wannabit"
    const val LEGACY_DB_TABLE_PASSWORD = "paswd"
    const val LEGACY_DB_TABLE_ACCOUNT = "accnt"
    const val LEGACY_DB_TABLE_MNEMONIC = "mnemonic"

    const val ENCRYPT_MNEMONIC_KEY = "MNEMONIC_KEY"
    const val ENCRYPT_PRIVATE_KEY = "PRIVATE_KEY"
    const val ENCRYPT_PASSWORD_KEY = "PASSWORD_KEY"

    const val MINTSCAN_API_URL = "https://front.api.mintscan.io"
    const val SKIP_API_URL = "https://api.skip.money/"

    // explorer & github
    const val EXPLORER_BASE_URL = "https://www.mintscan.io/"
    const val EXPLORER_BASE_TX_URL = "https://www.mintscan.io/{apiName}/tx/{hash}"
    const val CHAIN_BASE_URL =
        "https://raw.githubusercontent.com/cosmostation/chainlist/master/chain/"
    const val CHAIN_MONIKER_URL = "https://serve.dev-mintscan.com/assets/moniker/"
    const val WALLET_BASE_URL =
        "https://raw.githubusercontent.com/cosmostation/chainlist/master/wallet/"

    //cosmostation
    const val COSMOSTATION_HOMEPAGE = "https://www.cosmostation.io/"
    const val COSMOSTATION_TERM_EN = "https://cosmostation.io/service_en"
    const val COSMOSTATION_TERM_KR = "https://cosmostation.io/service_kr"
    const val COSMOSTATION_PRIVACY_POLICY = "https://cosmostation.io/privacy-policy"
    const val COSMOSTATION_GITHUB = "https://github.com/cosmostation/cosmostation-android"

    const val MOON_PAY_URL = "https://buy.moonpay.io"
    const val KADO_MONEY_URL = "https://app.kado.money"
    const val BINANCE_BUY_URL = "https://www.binance.com/en/crypto/buy"

    const val MOON_PAY_PUBLIC_KEY = "pk_live_zbG1BOGMVTcfKibboIE2K3vduJBTuuCn"
    const val KADO_PUBLIC_KEY = "18e55363-1d76-456c-8d4d-ecee7b9517ea"

    const val DAPP_ADDITIONAL_SCRIPT =
        """window.navigator.__defineGetter__('userAgent', function () {
                                        return 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/125.0.0.0 Safari/537.36';
                                       });"""
}