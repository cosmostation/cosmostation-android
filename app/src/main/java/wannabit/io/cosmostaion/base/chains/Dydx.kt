package wannabit.io.cosmostaion.base.chains

import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.base.BaseChain
import wannabit.io.cosmostaion.base.BaseConstant

class Dydx : ChainConfig() {

    override fun baseChain(): BaseChain { return BaseChain.DYDX_MAIN }
    override fun chainImg(): Int { return R.drawable.chain_dydx }
    override fun chainInfoImg(): Int { return R.drawable.infoicon_dydx }
    override fun chainInfoTitle(): Int { return R.string.str_front_guide_title_dydx }
    override fun chainInfoMsg(): Int { return R.string.str_front_guide_msg_dydx }
    override fun chainColor(): Int { return R.color.color_dydx }
    override fun chainBgColor(): Int { return R.color.colorTransBgDydx }
    override fun chainTabColor(): Int { return R.color.color_tab_myvalidator_dydx }
    override fun chainName(): String { return "dydx" }
    override fun chainKoreanName(): String { return "디와이디엑스" }
    override fun chainTitle(): String { return "dYdX" }
    override fun chainIdPrefix(): String { return "dYdX-mainnet-" }

    override fun mainDenomImg(): Int { return R.drawable.token_dydx }
    override fun mainDenom(): String { return "adydx" }
    override fun decimal(): Int { return 18 }
    override fun addressPrefix(): String { return "dydx" }

    override fun dexSupport(): Boolean { return false }
    override fun wcSupport(): Boolean { return false }
    override fun authzSupport(): Boolean { return true }

    override fun grpcUrl(): String { return "grpc-dydx.cosmostation.io" }
    override fun explorerUrl(): String { return BaseConstant.EXPLORER_BASE_URL + "quasar/" }
    override fun homeInfoLink(): String { return "https://dydx.exchange/" }
    override fun blogInfoLink(): String { return "https://dydx.exchange/blog/" }
    override fun coingeckoLink(): String { return BaseConstant.COINGECKO_URL + "dydx" }
}