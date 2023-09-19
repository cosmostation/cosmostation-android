package wannabit.io.cosmostaion.chain.cosmosClass

import com.google.common.collect.ImmutableList
import com.squareup.picasso.Picasso
import org.bitcoinj.crypto.ChildNumber
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.AccountKeyType
import wannabit.io.cosmostaion.chain.ChainType
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.chain.PubKeyType
import wannabit.io.cosmostaion.common.BaseConstant

class ChainBinanceBeacon : CosmosLine() {

    override var chainType: ChainType? = ChainType.COSMOS_TYPE
    override var name: String = "BNB Beacon"
    override var id: String = ""
    override var logo: Int = R.drawable.chain_binance
    override var swipeLogo: Int = R.drawable.chain_swipe_binance
    override var apiName: String = ""
    override var stakeDenom: String = "BNB"

    override var accountKeyType = AccountKeyType(PubKeyType.COSMOS_SECP256K1, "m/44'/714'/0'/0/X")
    override var setParentPath: List<ChildNumber> = ImmutableList.of(
        ChildNumber(44, true),
        ChildNumber(714, true),
        ChildNumber.ZERO_HARDENED,
        ChildNumber.ZERO
    )
    override var accountPrefix: String? = "bnb"

    val lcdUrl = "https://dex.binance.org/"
    val BNB_GECKO_ID = "binancecoin"

    fun assetImg(originSymbol: String): String {
        return BaseConstant.CHAIN_BASE_URL + "bnb-beacon-chain/asset/" + originSymbol.lowercase() + ".png"
    }
}