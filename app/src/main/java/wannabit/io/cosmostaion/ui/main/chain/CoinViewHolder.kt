package wannabit.io.cosmostaion.ui.main.chain

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.cosmos.base.v1beta1.CoinProto
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.formatAssetValue
import wannabit.io.cosmostaion.common.formatString
import wannabit.io.cosmostaion.common.priceChangeStatus
import wannabit.io.cosmostaion.common.priceChangeStatusColor
import wannabit.io.cosmostaion.common.setTokenImg
import wannabit.io.cosmostaion.common.visibleOrGone
import wannabit.io.cosmostaion.databinding.ItemCosmosLineTokenBinding
import java.math.RoundingMode

class CoinViewHolder(
    val context: Context,
    private val binding: ItemCosmosLineTokenBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bindNativeAsset(line: CosmosLine, coin: CoinProto.Coin, position: Int, cnt: Int) {
        binding.apply {
            headerLayout.visibleOrGone(position == 0)
            headerCnt.text = cnt.toString()
            if (position == 0) {
                if (itemViewType == CoinAdapter.VIEW_TYPE_NATIVE_ITEM) {
                    headerTitle.text = context.getString(R.string.str_native_coins)
                } else if (itemViewType == CoinAdapter.VIEW_TYPE_IBC_ITEM) {
                    headerTitle.text = context.getString(R.string.str_ibc_coins)
                }
            }
            coinView.setBackgroundResource(R.drawable.item_dash_bg)

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
                    coinAmountValue.text = formatAssetValue(amount)
                }
            }
        }
    }
}