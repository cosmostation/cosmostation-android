package wannabit.io.cosmostaion.ui.main.chain

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.formatAssetValue
import wannabit.io.cosmostaion.common.formatString
import wannabit.io.cosmostaion.common.priceChangeStatus
import wannabit.io.cosmostaion.common.priceChangeStatusColor
import wannabit.io.cosmostaion.common.setTokenImg
import wannabit.io.cosmostaion.common.visibleOrGone
import wannabit.io.cosmostaion.data.model.res.Token
import wannabit.io.cosmostaion.databinding.ItemCosmosLineTokenBinding
import java.math.RoundingMode

class TokenViewHolder(
    val context: Context,
    private val binding: ItemCosmosLineTokenBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(line: CosmosLine, token: Token, cnt: Int, position: Int) {
        binding.apply {
            coinView.setBackgroundResource(R.drawable.item_bg)
            headerLayout.visibleOrGone(position == 0)
            headerTitle.text = context.getString(R.string.str_contract_coins)
            headerCnt.text = cnt.toString()

            tokenImg.setTokenImg(token.assetImg())
            tokenName.text = token.symbol

            tokenPrice.text = formatAssetValue(BaseData.getPrice(token.coinGeckoId))
            BaseData.lastUpDown(token.coinGeckoId).let { lastUpDown ->
                tokenPriceChange.priceChangeStatusColor(lastUpDown)
                tokenPriceChange.text = priceChangeStatus(lastUpDown)
            }

            token.amount?.toBigDecimal()?.movePointLeft(token.decimals)?.setScale(6, RoundingMode.DOWN)?.let { amount ->
                coinAmount.text = formatString(amount.toPlainString(), 6)
                coinAmountValue.text = formatAssetValue(line.cw20Value(token.address))
            }
        }
    }
}