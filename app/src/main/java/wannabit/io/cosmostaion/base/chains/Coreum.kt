package wannabit.io.cosmostaion.base.chains

import com.google.common.collect.ImmutableList
import org.bitcoinj.crypto.ChildNumber
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.base.BaseChain
import wannabit.io.cosmostaion.base.BaseConstant
import wannabit.io.cosmostaion.base.BaseConstant.COINGECKO_URL

class Coreum : ChainConfig() {

    override fun baseChain(): BaseChain { return BaseChain.COREUM_MAIN }
    override fun chainImg(): Int { return R.drawable.chain_coreum }
    override fun chainInfoImg(): Int { return R.drawable.infoicon_coreum }
    override fun chainInfoTitle(): Int { return R.string.str_front_guide_title_coreum}
    override fun chainInfoMsg(): Int { return R.string.str_front_guide_msg_coreum }
    override fun chainColor(): Int { return R.color.color_coreum }
    override fun chainBgColor(): Int { return R.color.colorTransBgCoreum }
    override fun chainTabColor(): Int { return R.color.color_tab_myvalidator_coreum }
    override fun chainName(): String { return "coreum" }
    override fun chainKoreanName(): String { return "코리움" }
    override fun chainTitle(): String { return "coreum" }
    override fun chainIdPrefix(): String { return "coreum-mainnet-" }

    override fun mainDenomImg(): Int { return R.drawable.token_coreum }
    override fun mainDenom(): String { return "ucore" }
    override fun addressPrefix(): String { return "core" }

    override fun dexSupport(): Boolean { return false }
    override fun wcSupport(): Boolean { return false }
    override fun authzSupport(): Boolean { return true }

    override fun grpcUrl(): String { return "grpc-coreum.cosmostation.io" }
    override fun explorerUrl(): String { return BaseConstant.EXPLORER_BASE_URL + "coreum/" }
    override fun homeInfoLink(): String { return "https://www.coreum.com/" }
    override fun blogInfoLink(): String { return "https://www.coreum.com/community#press-&-media" }
    override fun coingeckoLink(): String { return COINGECKO_URL + "coreum" }

    override fun defaultPath(): String { return "m/44'/990'/0'/0/X" }

    override fun setParentPath(customPath: Int): List<ChildNumber>? {
        return ImmutableList.of(ChildNumber(44, true), ChildNumber(990, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO)
    }
}