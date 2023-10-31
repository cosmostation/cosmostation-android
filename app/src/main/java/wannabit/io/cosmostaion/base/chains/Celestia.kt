package wannabit.io.cosmostaion.base.chains

import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.base.BaseChain
import wannabit.io.cosmostaion.base.BaseConstant

class Celestia: ChainConfig() {

    override fun baseChain(): BaseChain { return BaseChain.CELESTIA_MAIN }
    override fun chainImg(): Int { return R.drawable.chain_celestia }
    override fun chainInfoImg(): Int { return R.drawable.infoicon_celestia }
    override fun chainInfoTitle(): Int { return R.string.str_front_guide_title_celestia }
    override fun chainInfoMsg(): Int { return R.string.str_front_guide_msg_celestia }
    override fun chainColor(): Int { return R.color.color_celestia }
    override fun chainBgColor(): Int { return R.color.colorTransBgCelestia }
    override fun chainTabColor(): Int { return R.color.color_tab_myvalidator_celestia }
    override fun chainName(): String { return "celestia" }
    override fun chainKoreanName(): String { return "셀레스티아" }
    override fun chainTitle(): String { return "celestia" }
    override fun chainIdPrefix(): String { return "celestia" }

    override fun mainDenomImg(): Int { return R.drawable.token_celestia }
    override fun mainDenom(): String { return "utia" }
    override fun mainSymbol(): String { return "TIA" }
    override fun addressPrefix(): String { return "celestia" }

    override fun dexSupport(): Boolean { return false }
    override fun wcSupport(): Boolean { return false }
    override fun authzSupport(): Boolean { return false }

    override fun grpcUrl(): String { return "grpc-celestia.cosmostation.io" }
    override fun explorerUrl(): String { return BaseConstant.EXPLORER_BASE_URL + "celestia/" }
    override fun homeInfoLink(): String { return "https://celestia.org/" }
    override fun blogInfoLink(): String { return "https://blog.celestia.org/" }
    override fun coingeckoLink(): String { return "" }
}