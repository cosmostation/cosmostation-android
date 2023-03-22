package wannabit.io.cosmostaion.base.chains

import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.base.BaseChain
import wannabit.io.cosmostaion.base.BaseConstant

class Quasar : ChainConfig() {

    override fun baseChain(): BaseChain { return BaseChain.QUASAR_MAIN }
    override fun chainImg(): Int { return R.drawable.chain_quasar }
    override fun chainInfoImg(): Int { return R.drawable.infoicon_quasar }
    override fun chainInfoTitle(): Int { return R.string.str_front_guide_title_quasar }
    override fun chainInfoMsg(): Int { return R.string.str_front_guide_msg_quasar }
    override fun chainColor(): Int { return R.color.color_quasar }
    override fun chainBgColor(): Int { return R.color.colorTransBgQuasar }
    override fun chainTabColor(): Int { return R.color.color_tab_myvalidator_quasar }
    override fun chainName(): String { return "quasar" }
    override fun chainKoreanName(): String { return "퀘이사" }
    override fun chainTitle(): String { return "quasar" }
    override fun chainIdPrefix(): String { return "quasar-" }

    override fun mainDenomImg(): Int { return R.drawable.token_quasar }
    override fun mainDenom(): String { return "uqsr" }
    override fun addressPrefix(): String { return "quasar" }

    override fun dexSupport(): Boolean { return false }
    override fun wcSupport(): Boolean { return false }
    override fun authzSupport(): Boolean { return true }

    override fun grpcUrl(): String { return "grpc-quasar.cosmostation.io" }
    override fun explorerUrl(): String { return BaseConstant.EXPLORER_BASE_URL + "quasar/" }
    override fun homeInfoLink(): String { return "https://www.quasar.fi/" }
    override fun blogInfoLink(): String { return "https://medium.com/@quasar.fi" }
    override fun coingeckoLink(): String { return "" }
}