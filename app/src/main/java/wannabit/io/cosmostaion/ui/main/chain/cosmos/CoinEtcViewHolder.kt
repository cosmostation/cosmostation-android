package wannabit.io.cosmostaion.ui.main.chain.cosmos

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.cosmosClass.ChainOkt996Keccak
import wannabit.io.cosmostaion.chain.evmClass.ChainOktEvm
import wannabit.io.cosmostaion.common.formatAmount
import wannabit.io.cosmostaion.common.setTokenImg
import wannabit.io.cosmostaion.common.visibleOrGone
import wannabit.io.cosmostaion.data.model.res.Coin
import wannabit.io.cosmostaion.database.Prefs
import wannabit.io.cosmostaion.databinding.ItemCosmosLineEtcBinding

class CoinEtcViewHolder(
    val context: Context,
    private val binding: ItemCosmosLineEtcBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bindOktCoin(chain: BaseChain, coin: Coin, position: Int, cnt: Int) {
        binding.apply {
            coinView.setBackgroundResource(R.drawable.item_bg)

            headerLayout.visibleOrGone(position == 0)
            headerTitle.text = context.getString(R.string.str_kip10_coins)
            headerCnt.text = cnt.toString()

            if (chain is ChainOkt996Keccak) {
                chain.oktTokenInfo?.data?.firstOrNull { it.symbol == coin.denom }?.let { token ->
                    tokenImg.setTokenImg(ChainOkt996Keccak().assetImg(token.original_symbol))
                    tokenName.text = token.original_symbol.uppercase()
                    tokenDescription.text = token.description

                    if (Prefs.hideValue) {
                        tokenAmount.text = "✱✱✱✱"
                        tokenAmount.textSize = 10f
                    } else {
                        val availableAmount = chain.lcdBalanceAmount(coin.denom)
                        tokenAmount.text = formatAmount(availableAmount.toPlainString(), 18)
                        tokenAmount.textSize = 14f
                    }
                }

            } else if (chain is ChainOktEvm) {
                chain.oktTokenInfo?.data?.firstOrNull { it.symbol == coin.denom }?.let { token ->
                    tokenImg.setTokenImg(ChainOktEvm().assetImg(token.original_symbol))
                    tokenName.text = token.original_symbol.uppercase()
                    tokenDescription.text = token.description

                    if (Prefs.hideValue) {
                        tokenAmount.text = "✱✱✱✱"
                        tokenAmount.textSize = 10f
                    } else {
                        val availableAmount = chain.lcdBalanceAmount(coin.denom)
                        tokenAmount.text = formatAmount(availableAmount.toPlainString(), 18)
                        tokenAmount.textSize = 14f
                    }
                }
            }
        }
    }
}