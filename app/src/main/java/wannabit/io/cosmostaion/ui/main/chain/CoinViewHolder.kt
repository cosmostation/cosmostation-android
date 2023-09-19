package wannabit.io.cosmostaion.ui.main.chain

import android.content.Context
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.chain.cosmosClass.ChainBinanceBeacon
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.formatAssetValue
import wannabit.io.cosmostaion.common.formatString
import wannabit.io.cosmostaion.common.priceChangeStatus
import wannabit.io.cosmostaion.common.priceChangeStatusColor
import wannabit.io.cosmostaion.common.setTokenImg
import wannabit.io.cosmostaion.common.visibleOrGone
import wannabit.io.cosmostaion.data.model.Coin
import wannabit.io.cosmostaion.data.model.CoinType
import wannabit.io.cosmostaion.databinding.ItemCosmosLineTokenBinding
import java.math.RoundingMode

class CoinViewHolder(
    val context: Context,
    private val binding: ItemCosmosLineTokenBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(line: CosmosLine, coin: Coin, position: Int, cnt: Int) {
        if (line is ChainBinanceBeacon) {
            bindBeaconCoin(line, coin, position, cnt)

        } else {
            binding.apply {
                headerLayout.visibleOrGone(position == 0)
                headerCnt.text = cnt.toString()
                if (position == 0) {
                    when (coin.type) {
                        CoinType.NATIVE -> headerTitle.text = context.getString(R.string.str_native_coins)
                        CoinType.IBC -> headerTitle.text = context.getString(R.string.str_ibc_coins)
                        CoinType.BRIDGE -> headerTitle.text = context.getString(R.string.str_bridge_coins)
                        else -> headerTitle.text = context.getString(R.string.str_unknown_coins)
                    }
                }
                coinView.setBackgroundResource(R.drawable.item_bg)

                BaseData.getAsset(line.apiName, coin.denom)?.let { asset ->
                    tokenImg.setTokenImg(asset)
                    tokenName.text = asset.symbol?.uppercase()

                    tokenPrice.text = formatAssetValue(BaseData.getPrice(asset.coinGeckoId))
                    BaseData.lastUpDown(asset.coinGeckoId).let { lastUpDown ->
                        tokenPriceChange.priceChangeStatusColor(lastUpDown)
                        tokenPriceChange.text = priceChangeStatus(lastUpDown)
                    }

                    asset.decimals?.let { decimal ->
                        val amount = coin.amount.toBigDecimal().movePointLeft(decimal).setScale(6, RoundingMode.HALF_UP)
                        coinAmount.text = formatString(amount.toPlainString(), 6)
                        coinAmountValue.text = formatAssetValue(line.denomValue(coin.denom))
                    }
                }
            }
        }
    }

    private fun bindBeaconCoin(line: CosmosLine, coin: Coin, position: Int, cnt: Int) {
        binding.apply {
            coinView.setBackgroundResource(R.drawable.item_bg)
            tokenValueLayout.visibility = View.GONE
            val params = tokenInfoLayout.layoutParams as ConstraintLayout.LayoutParams
            params.verticalBias = 0.5f
            tokenInfoLayout.layoutParams = params

            headerLayout.visibleOrGone(position == 0)
            headerTitle.text = context.getString(R.string.str_native_coins)
            headerCnt.text = cnt.toString()

            val token = line.lcdBeaconTokens.firstOrNull { it.symbol == coin.denom }
            if (token != null) {
                tokenImg.setTokenImg(ChainBinanceBeacon().assetImg(token.originalSymbol))
                tokenName.text = token.originalSymbol.uppercase()
                tokenPrice.text = token.name
            }
            val amount = coin.amount.toBigDecimal().setScale(8, RoundingMode.HALF_UP)
            coinAmount.text = formatString(amount.toPlainString(), 8)
        }
    }
}