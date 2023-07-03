package wannabit.io.cosmostaion.base.chains

import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.base.BaseChain
import wannabit.io.cosmostaion.base.BaseConstant

class Archway : ChainConfig() {

    override fun baseChain(): BaseChain { return BaseChain.ARCHWAY_MAIN }
    override fun chainImg(): Int { return R.drawable.chain_archway }
    override fun chainInfoImg(): Int { return R.drawable.infoicon_archway }
    override fun chainInfoTitle(): Int { return R.string.str_front_guide_title_archway}
    override fun chainInfoMsg(): Int { return R.string.str_front_guide_msg_archway }
    override fun chainColor(): Int { return R.color.color_archway }
    override fun chainBgColor(): Int { return R.color.colorTransBgArchway }
    override fun chainTabColor(): Int { return R.color.color_tab_myvalidator_archway }
    override fun chainName(): String { return "archway" }
    override fun chainKoreanName(): String { return "아치웨이" }
    override fun chainTitle(): String { return "archway" }
    override fun chainIdPrefix(): String { return "archway-" }

    override fun mainDenomImg(): Int { return R.drawable.token_archway }
    override fun mainDenom(): String { return "aarch" }
    override fun decimal(): Int { return 18 }
    override fun addressPrefix(): String { return "archway" }

    override fun dexSupport(): Boolean { return false }
    override fun wcSupport(): Boolean { return false }
    override fun authzSupport(): Boolean { return true }

    override fun grpcUrl(): String { return "grpc-archway.cosmostation.io" }
    override fun explorerUrl(): String { return BaseConstant.EXPLORER_BASE_URL + "archway/" }
    override fun homeInfoLink(): String { return "https://archway.io/" }
    override fun blogInfoLink(): String { return "https://blog.archway.io/" }
    override fun coingeckoLink(): String { return BaseConstant.COINGECKO_URL + "archway" }
}