package wannabit.io.cosmostaion.ui.main.chain.cosmos

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.evmClass.ChainOktEvm
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.formatAmount
import wannabit.io.cosmostaion.common.formatAssetValue
import wannabit.io.cosmostaion.common.priceChangeStatus
import wannabit.io.cosmostaion.common.priceChangeStatusColor
import wannabit.io.cosmostaion.common.setTokenImg
import wannabit.io.cosmostaion.data.model.res.Coin
import wannabit.io.cosmostaion.database.Prefs
import wannabit.io.cosmostaion.databinding.ItemCosmosLineEtcBinding

class CoinEtcViewHolder(
    val context: Context, private val binding: ItemCosmosLineEtcBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bindOktCoin(chain: BaseChain, coin: Coin) {
        binding.apply {
            coinView.setBackgroundResource(R.drawable.item_bg)

            BaseData.getAsset(chain.apiName, coin.denom)?.let { asset ->
                tokenImg.setTokenImg(asset)
                tokenImg.clipToOutline = true
                tokenName.text = asset.symbol

                tokenPrice.text = formatAssetValue(BaseData.getPrice(asset.coinGeckoId))
                BaseData.lastUpDown(asset.coinGeckoId).let { lastUpDown ->
                    tokenPriceChange.priceChangeStatusColor(lastUpDown)
                    tokenPriceChange.text = priceChangeStatus(lastUpDown)
                }

                if (Prefs.hideValue) {
                    coinAmount.visibility = View.GONE
                    coinAmountValue.visibility = View.GONE
                    hideValue.visibility = View.VISIBLE
                } else {
                    coinAmount.visibility = View.VISIBLE
                    coinAmountValue.visibility = View.VISIBLE
                    hideValue.visibility = View.GONE

                    val availableAmount =
                        (chain as ChainOktEvm).oktFetcher?.oktBalanceAmount(coin.denom)
                    coinAmount.text = formatAmount(availableAmount.toString(), 18)
                    coinAmountValue.text =
                        chain.cosmosFetcher?.denomValue(coin.denom)?.let { formatAssetValue(it) }
                }
            }
        }
    }
}