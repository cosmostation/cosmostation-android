package wannabit.io.cosmostaion.base.chains

import com.google.common.collect.ImmutableList
import org.bitcoinj.crypto.ChildNumber
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.base.BaseChain
import wannabit.io.cosmostaion.base.BaseConstant
import wannabit.io.cosmostaion.base.BaseConstant.COINGECKO_URL

class Terra : ChainConfig() {
    override fun baseChain(): BaseChain { return BaseChain.TERRA_MAIN }
    override fun chainImg(): Int { return R.drawable.chain_terra }
    override fun chainInfoImg(): Int { return R.drawable.infoicon_terra }
    override fun chainInfoTitle(): Int { return R.string.str_front_guide_title_terra }
    override fun chainInfoMsg(): Int { return R.string.str_front_guide_msg_terra }
    override fun chainColor(): Int { return R.color.color_terra }
    override fun chainBgColor(): Int { return R.color.colorTransBgTerra }
    override fun chainTabColor(): Int { return R.color.color_tab_myvalidator_terra }
    override fun chainName(): String { return "terra" }
    override fun chainKoreanName(): String { return "테라" }
    override fun chainTitle(): String { return "terra" }
    override fun chainIdPrefix(): String { return "phoenix-" }

    override fun mainDenomImg(): Int { return R.drawable.token_terra }
    override fun mainDenom(): String { return "uluna" }
    override fun mainSymbol(): String { return "LUNA" }
    override fun addressPrefix(): String { return "terra" }

    override fun erc20CoinSupport(): Boolean { return true }
    override fun dexSupport(): Boolean { return false }
    override fun wasmSupport(): Boolean { return true }
    override fun wcSupport(): Boolean { return false }
    override fun authzSupport(): Boolean { return true }

    override fun grpcUrl(): String { return "grpc-terra.cosmostation.io" }
    override fun explorerUrl(): String { return BaseConstant.EXPLORER_BASE_URL + "terra/" }
    override fun homeInfoLink(): String { return "" }
    override fun blogInfoLink(): String { return "https://medium.com/terra-money/" }
    override fun coingeckoLink(): String { return COINGECKO_URL + "terra"; }

    override fun defaultPath(): String { return "m/44'/330'/0'/0/X" }
    override fun setParentPath(customPath: Int): List<ChildNumber>? {
        return ImmutableList.of(ChildNumber(44, true), ChildNumber(330, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO)
    }
}