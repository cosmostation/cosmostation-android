package wannabit.io.cosmostaion.base.chains

import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.base.BaseChain
import wannabit.io.cosmostaion.base.BaseConstant

class Stafi : ChainConfig() {

    override fun baseChain(): BaseChain { return BaseChain.STAFI_MAIN }
    override fun chainImg(): Int { return R.drawable.chain_stafi }
    override fun chainInfoImg(): Int { return R.drawable.infoicon_stafi }
    override fun chainInfoTitle(): Int { return R.string.str_front_guide_title_stafi }
    override fun chainInfoMsg(): Int { return R.string.str_front_guide_msg_stafi }
    override fun chainColor(): Int { return R.color.color_stafi }
    override fun chainBgColor(): Int { return R.color.colorTransBgStafi }
    override fun chainTabColor(): Int { return R.color.color_tab_myvalidator_stafi }
    override fun chainName(): String { return "stafi" }
    override fun chainKoreanName(): String { return "스테파이" }
    override fun chainTitle(): String { return "stafi" }
    override fun chainIdPrefix(): String { return "stafihub-" }

    override fun mainDenomImg(): Int { return R.drawable.token_stafi }
    override fun mainDenom(): String { return "ufis" }
    override fun mainSymbol(): String { return "FIS" }
    override fun addressPrefix(): String { return "stafi" }

    override fun dexSupport(): Boolean { return false }
    override fun wcSupport(): Boolean { return false }
    override fun authzSupport(): Boolean { return false }

    override fun grpcUrl(): String { return "grpc-stafi.cosmostation.io" }
    override fun explorerUrl(): String { return BaseConstant.EXPLORER_BASE_URL + "stafi/" }
    override fun homeInfoLink(): String { return "https://www.stafihub.io/" }
    override fun blogInfoLink(): String { return "https://stafi-protocol.medium.com/" }
    override fun coingeckoLink(): String { return BaseConstant.COINGECKO_URL + "stafi" }
}