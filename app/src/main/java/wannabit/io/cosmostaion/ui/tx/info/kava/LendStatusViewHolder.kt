package wannabit.io.cosmostaion.ui.tx.info.kava

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.cosmos.base.v1beta1.CoinProto
import com.kava.hard.v1beta1.HardProto
import com.kava.pricefeed.v1beta1.QueryProto
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.formatAssetValue
import wannabit.io.cosmostaion.common.getLTV
import wannabit.io.cosmostaion.common.kavaOraclePrice
import wannabit.io.cosmostaion.common.spotMarketId
import wannabit.io.cosmostaion.databinding.ItemLendStatusBinding
import java.math.BigDecimal
import java.math.RoundingMode

class LendStatusViewHolder(
    val context: Context,
    private val binding: ItemLendStatusBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(
        lendingParam: HardProto.Params?,
        priceFeed: QueryProto.QueryPricesResponse?,
        lendMyDeposits: MutableList<CoinProto.Coin>,
        lendMyBorrows: MutableList<CoinProto.Coin>
    ) {
        binding.apply {
            lendStatusView.setBackgroundResource(R.drawable.item_bg)
            if (lendingParam == null && priceFeed == null) { return }
            lendingParam?.let { param ->
                var totalDepositValue = BigDecimal.ZERO
                var totalLTVValue = BigDecimal.ZERO
                lendMyDeposits.forEach { coin ->
                    val decimal = BaseData.assets?.firstOrNull { it.denom == coin.denom }?.decimals ?: 6
                    val LTV = param.getLTV(coin.denom)
                    val marketPrice = priceFeed?.kavaOraclePrice(param.spotMarketId(coin.denom))
                    val depositValue = coin.amount.toBigDecimal().movePointLeft(decimal).multiply(marketPrice).setScale(12, RoundingMode.DOWN)
                    val ltvValue = depositValue.multiply(LTV)
                    totalDepositValue = totalDepositValue.add(depositValue)
                    totalLTVValue = totalLTVValue.add(ltvValue)
                }

                mySuppliedValue.text = formatAssetValue(totalDepositValue)
                availableCredit.text = formatAssetValue(totalLTVValue)

                var totalBorrowValue = BigDecimal.ZERO
                lendMyBorrows.forEach { coin ->
                    val decimal = BaseData.assets?.firstOrNull { it.denom == coin.denom }?.decimals ?: 6
                    val marketPrice = priceFeed?.kavaOraclePrice(param.spotMarketId(coin.denom))
                    val borrowValue = coin.amount.toBigDecimal().movePointLeft(decimal).multiply(marketPrice).setScale(12, RoundingMode.DOWN)
                    totalBorrowValue = totalBorrowValue.add(borrowValue)
                }
                val remainBorrowable = if (totalLTVValue.subtract(totalBorrowValue) > BigDecimal.ZERO) {
                    totalLTVValue.subtract(totalBorrowValue)
                } else {
                    BigDecimal.ZERO
                }
                myBorrowedValue.text = formatAssetValue(totalBorrowValue)
                remainBorrowableValue.text = formatAssetValue(remainBorrowable)
            }
        }
    }
}