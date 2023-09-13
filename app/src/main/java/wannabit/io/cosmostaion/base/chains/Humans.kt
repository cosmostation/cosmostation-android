package wannabit.io.cosmostaion.base.chains

import com.google.common.collect.ImmutableList
import org.bitcoinj.crypto.ChildNumber
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.base.BaseChain
import wannabit.io.cosmostaion.base.BaseConstant

class Humans : ChainConfig() {

    override fun baseChain(): BaseChain { return BaseChain.HUMANS_MAIN }
    override fun chainImg(): Int { return R.drawable.chain_humans }
    override fun chainInfoImg(): Int { return R.drawable.infoicon_humans }
    override fun chainInfoTitle(): Int { return R.string.str_front_guide_title_humans}
    override fun chainInfoMsg(): Int { return R.string.str_front_guide_msg_humans }
    override fun chainColor(): Int { return R.color.color_humans }
    override fun chainBgColor(): Int { return R.color.colorTransBgHumans }
    override fun chainTabColor(): Int { return R.color.color_tab_myvalidator_humans }
    override fun chainName(): String { return "humans" }
    override fun chainKoreanName(): String { return "휴먼스" }
    override fun chainIdPrefix(): String { return "humans_" }

    override fun mainDenomImg(): Int { return R.drawable.token_humans }
    override fun mainDenom(): String { return "aheart" }
    override fun decimal(): Int { return 18 }
    override fun addressPrefix(): String { return "human" }
    override fun ethAccountType(): Boolean { return true }

    override fun dexSupport(): Boolean { return false }
    override fun wcSupport(): Boolean { return false }
    override fun authzSupport(): Boolean { return true }


    override fun grpcUrl(): String { return "grpc-humans.cosmostation.io" }
    override fun explorerUrl(): String { return BaseConstant.EXPLORER_BASE_URL + "humans/" }
    override fun homeInfoLink(): String { return "https://humans.ai/" }
    override fun blogInfoLink(): String { return "https://blog.humans.ai/" }
    override fun coingeckoLink(): String { return BaseConstant.COINGECKO_URL + "humans-ai" }

    override fun defaultPath(): String { return "m/44'/60'/0'/0/X" }

    override fun setParentPath(customPath: Int): List<ChildNumber>? {
        return ImmutableList.of(ChildNumber(44, true), ChildNumber(60, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO)
    }
}