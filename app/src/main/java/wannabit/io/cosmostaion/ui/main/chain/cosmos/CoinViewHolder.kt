package wannabit.io.cosmostaion.ui.main.chain.cosmos

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.formatAmount
import wannabit.io.cosmostaion.common.formatAssetValue
import wannabit.io.cosmostaion.common.priceChangeStatus
import wannabit.io.cosmostaion.common.priceChangeStatusColor
import wannabit.io.cosmostaion.common.setTokenImg
import wannabit.io.cosmostaion.common.visibleOrGone
import wannabit.io.cosmostaion.data.model.res.Coin
import wannabit.io.cosmostaion.data.model.res.CoinType
import wannabit.io.cosmostaion.database.Prefs
import wannabit.io.cosmostaion.databinding.ItemCosmosLineTokenBinding
import java.math.RoundingMode

class CoinViewHolder(
    val context: Context, private val binding: ItemCosmosLineTokenBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(chain: BaseChain, coin: Coin, position: Int, cnt: Int) {
        binding.apply {
            headerLayout.visibleOrGone(position == 0)
            headerCnt.text = cnt.toString()
            if (position == 0) {
                when (coin.type) {
                    CoinType.NATIVE -> headerTitle.text =
                        context.getString(R.string.str_native_coins)

                    CoinType.IBC -> headerTitle.text = context.getString(R.string.str_ibc_coins)
                    CoinType.BRIDGE -> headerTitle.text =
                        context.getString(R.string.str_bridge_coins)

                    else -> headerTitle.text = context.getString(R.string.str_unknown_coins)
                }
            }
            coinView.setBackgroundResource(R.drawable.item_bg)

            BaseData.getAsset(chain.apiName, coin.denom)?.let { asset ->
                tokenImg.setTokenImg(asset)
                tokenName.text = asset.symbol

                tokenPrice.text = formatAssetValue(BaseData.getPrice(asset.coinGeckoId))
                BaseData.lastUpDown(asset.coinGeckoId).let { lastUpDown ->
                    tokenPriceChange.priceChangeStatusColor(lastUpDown)
                    tokenPriceChange.text = priceChangeStatus(lastUpDown)
                }

                asset.decimals?.let { decimal ->
                    val amount = coin.amount.toBigDecimal().movePointLeft(decimal)
                        .setScale(6, RoundingMode.DOWN)
                    if (Prefs.hideValue) {
                        coinAmount.visibility = View.GONE
                        coinAmountValue.visibility = View.GONE
                        hideValue.visibility = View.VISIBLE
                    } else {
                        coinAmount.visibility = View.VISIBLE
                        coinAmountValue.visibility = View.VISIBLE
                        hideValue.visibility = View.GONE

                        coinAmount.text = formatAmount(amount.toPlainString(), 6)
                        coinAmountValue.text =
                            chain.grpcFetcher?.denomValue(coin.denom)?.let { formatAssetValue(it) }
                    }
                }
            }
        }
    }
}