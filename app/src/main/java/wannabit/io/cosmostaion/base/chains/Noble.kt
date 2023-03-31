package wannabit.io.cosmostaion.base.chains

import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.base.BaseChain
import wannabit.io.cosmostaion.base.BaseConstant
import java.util.*

class Noble : ChainConfig() {

    override fun baseChain(): BaseChain { return BaseChain.NOBLE_MAIN }
    override fun chainImg(): Int { return R.drawable.chain_noble }
    override fun chainInfoImg(): Int { return R.drawable.infoicon_noble }
    override fun chainInfoTitle(): Int { return R.string.str_front_guide_title_noble }
    override fun chainInfoMsg(): Int { return R.string.str_front_guide_msg_noble }
    override fun chainColor(): Int { return R.color.color_noble }
    override fun chainBgColor(): Int { return R.color.colorTransBgNoble }
    override fun chainTabColor(): Int { return R.color.color_tab_myvalidator_noble }
    override fun chainName(): String { return "noble" }
    override fun chainKoreanName(): String { return "노블" }
    override fun chainTitle(): String { return "noble" }
    override fun chainIdPrefix(): String { return "noble-" }

    override fun mainDenomImg(): Int { return R.drawable.token_noble }
    override fun mainDenom(): String { return "ustake" }
    override fun mainSymbol(): String { return "NOBLE" }
    override fun addressPrefix(): String { return "noble" }

    override fun dexSupport(): Boolean { return false }
    override fun wcSupport(): Boolean { return false }
    override fun authzSupport(): Boolean { return true }

    override fun grpcUrl(): String { return "grpc-noble.cosmostation.io" }
    override fun explorerUrl(): String { return BaseConstant.EXPLORER_BASE_URL + "noble/" }
    override fun homeInfoLink(): String { return "https://nobleassets.xyz/" }
    override fun blogInfoLink(): String { return "https://mirror.xyz/nobleassets.eth" }
    override fun coingeckoLink(): String { return "" }
}