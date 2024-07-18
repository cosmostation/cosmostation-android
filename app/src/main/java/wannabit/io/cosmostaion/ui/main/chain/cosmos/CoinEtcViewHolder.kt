package wannabit.io.cosmostaion.ui.main.chain.cosmos

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.OktFetcher
import wannabit.io.cosmostaion.chain.cosmosClass.ChainOkt996Keccak
import wannabit.io.cosmostaion.chain.evmClass.ChainOktEvm
import wannabit.io.cosmostaion.common.formatAmount
import wannabit.io.cosmostaion.common.setTokenImg
import wannabit.io.cosmostaion.common.visibleOrGone
import wannabit.io.cosmostaion.data.model.res.Coin
import wannabit.io.cosmostaion.database.Prefs
import wannabit.io.cosmostaion.databinding.ItemCosmosLineEtcBinding

class CoinEtcViewHolder(
    val context: Context, private val binding: ItemCosmosLineEtcBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bindOktCoin(chain: BaseChain, coin: Coin, position: Int, cnt: Int) {
        binding.apply {
            coinView.setBackgroundResource(R.drawable.item_bg)
            headerLayout.visibleOrGone(position == 0)
            headerTitle.text = context.getString(R.string.str_kip10_coins)
            headerCnt.text = cnt.toString()

            when (chain) {
                is ChainOkt996Keccak -> updateTokenInfo(coin, chain.oktFetcher)
                is ChainOktEvm -> updateTokenInfo(coin, chain.oktFetcher)
            }
        }
    }

    private fun updateTokenInfo(coin: Coin, oktFetcher: OktFetcher?) {
        binding.apply {
            oktFetcher?.lcdOktTokens?.get("data")?.asJsonArray?.firstOrNull { it.asJsonObject["symbol"].asString == coin.denom }
                ?.let { token ->
                    tokenImg.setTokenImg(ChainOkt996Keccak().assetImg(token.asJsonObject["original_symbol"].asString))
                    tokenName.text = token.asJsonObject["original_symbol"].asString.uppercase()
                    tokenDescription.text = token.asJsonObject["description"].asString

                    if (Prefs.hideValue) {
                        tokenAmount.text = "✱✱✱✱"
                        tokenAmount.textSize = 10f
                    } else {
                        val availableAmount = oktFetcher.lcdBalanceAmount(coin.denom)
                        tokenAmount.text = formatAmount(availableAmount.toString(), 18)
                        tokenAmount.textSize = 14f
                    }
                }
        }
    }
}