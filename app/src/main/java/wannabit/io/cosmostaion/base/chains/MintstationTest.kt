package wannabit.io.cosmostaion.base.chains

import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.base.BaseChain
import wannabit.io.cosmostaion.base.BaseConstant

class MintstationTest: ChainConfig() {

    override fun baseChain(): BaseChain { return BaseChain.MINTSTATION_TEST }
    override fun chainImg(): Int { return R.drawable.chain_mintstation }
    override fun chainInfoImg(): Int { return R.drawable.infoicon_mintstation }
    override fun chainInfoTitle(): Int { return R.string.str_front_guide_title_mintstation }
    override fun chainInfoMsg(): Int { return R.string.str_front_guide_msg_mintstation }
    override fun chainColor(): Int { return R.color.color_mintstation }
    override fun chainBgColor(): Int { return R.color.colorTransBgMintstation }
    override fun chainTabColor(): Int { return R.color.color_tab_myvalidator_mintstation }
    override fun chainName(): String { return "mintstation-testnet" }
    override fun chainKoreanName(): String { return "민트스테이션테스트넷" }
    override fun chainTitle(): String { return "mintstation-testnet" }
    override fun chainIdPrefix(): String { return "mintstation-" }

    override fun mainDenomImg(): Int { return R.drawable.token_mintstation }
    override fun mainDenom(): String { return "umint" }
    override fun mainSymbol(): String { return "MINT" }
    override fun addressPrefix(): String { return "mint" }

    override fun dexSupport(): Boolean { return false }
    override fun wcSupport(): Boolean { return false }
    override fun authzSupport(): Boolean { return false }

    override fun grpcUrl(): String { return "grpc-office-mintstation.cosmostation.io" }
    override fun explorerUrl(): String { return BaseConstant.EXPLORER_BASE_URL + "mintstation-testnet/" }
    override fun homeInfoLink(): String { return "" }
    override fun blogInfoLink(): String { return "" }
    override fun coingeckoLink(): String { return "" }
}