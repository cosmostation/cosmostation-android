package wannabit.io.cosmostaion.base.chains

import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.base.BaseChain
import wannabit.io.cosmostaion.base.BaseConstant

class Sei : ChainConfig() {

    override fun baseChain(): BaseChain { return BaseChain.SEI_MAIN }
    override fun chainImg(): Int { return R.drawable.chain_sei }
    override fun chainInfoImg(): Int { return R.drawable.infoicon_sei }
    override fun chainInfoTitle(): Int { return R.string.str_front_guide_title_sei}
    override fun chainInfoMsg(): Int { return R.string.str_front_guide_msg_sei }
    override fun chainColor(): Int { return R.color.color_sei }
    override fun chainBgColor(): Int { return R.color.colorTransBgSei }
    override fun chainTabColor(): Int { return R.color.color_tab_myvalidator_sei }
    override fun chainName(): String { return "sei" }
    override fun chainKoreanName(): String { return "세이" }
    override fun chainTitle(): String { return "sei" }
    override fun chainIdPrefix(): String { return "pacific-" }

    override fun mainDenomImg(): Int { return R.drawable.token_sei }
    override fun mainDenom(): String { return "usei" }
    override fun decimal(): Int { return 6 }
    override fun addressPrefix(): String { return "sei" }

    override fun dexSupport(): Boolean { return false }
    override fun wcSupport(): Boolean { return false }
    override fun authzSupport(): Boolean { return true }

    override fun grpcUrl(): String { return "grpc-sei.cosmostation.io" }
    override fun explorerUrl(): String { return BaseConstant.EXPLORER_BASE_URL + "sei/" }
    override fun homeInfoLink(): String { return "https://www.sei.io/" }
    override fun blogInfoLink(): String { return "https://blog.sei.io/" }
    override fun coingeckoLink(): String { return BaseConstant.COINGECKO_URL + "sei-network" }
}