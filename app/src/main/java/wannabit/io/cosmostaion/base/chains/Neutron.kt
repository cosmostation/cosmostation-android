package wannabit.io.cosmostaion.base.chains

import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.base.BaseChain
import wannabit.io.cosmostaion.base.BaseConstant

class Neutron: ChainConfig() {

    override fun baseChain(): BaseChain { return BaseChain.NEUTRON_MAIN }
    override fun chainImg(): Int { return R.drawable.chain_neutron }
    override fun chainInfoImg(): Int { return R.drawable.infoicon_neutron }
    override fun chainInfoTitle(): Int { return R.string.str_front_guide_title_neutron }
    override fun chainInfoMsg(): Int { return R.string.str_front_guide_msg_neutron }
    override fun chainColor(): Int { return R.color.color_neutron }
    override fun chainBgColor(): Int { return R.color.colorTransBgNeutron }
    override fun chainTabColor(): Int { return R.color.color_tab_myvalidator_neutron }
    override fun chainName(): String { return "neutron" }
    override fun chainKoreanName(): String { return "뉴트론" }
    override fun chainTitle(): String { return "neutron" }
    override fun chainIdPrefix(): String { return "neutron-" }

    override fun mainDenomImg(): Int { return R.drawable.token_neutron }
    override fun mainDenom(): String { return "untrn" }
    override fun mainSymbol(): String { return "NTRN" }
    override fun addressPrefix(): String { return "neutron" }

    override fun erc20CoinSupport(): Boolean { return true }
    override fun dexSupport(): Boolean { return false }
    override fun wasmSupport(): Boolean { return true }
    override fun wcSupport(): Boolean { return true }
    override fun authzSupport(): Boolean { return false }

    override fun grpcUrl(): String { return "grpc-neutron.cosmostation.io" }
    override fun explorerUrl(): String { return BaseConstant.EXPLORER_BASE_URL + "neutron/" }
    override fun homeInfoLink(): String { return "https://neutron.org/" }
    override fun blogInfoLink(): String { return "https://blog.neutron.org/" }
    override fun coingeckoLink(): String { return "" }
}