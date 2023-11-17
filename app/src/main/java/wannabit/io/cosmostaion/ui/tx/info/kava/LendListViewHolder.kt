package wannabit.io.cosmostaion.ui.tx.info.kava

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.cosmos.base.v1beta1.CoinProto
import com.kava.hard.v1beta1.HardProto
import com.squareup.picasso.Picasso
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.cosmosClass.KAVA_LEMD_IMG_URL
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.formatAmount
import wannabit.io.cosmostaion.common.formatAssetValue
import wannabit.io.cosmostaion.common.kavaOraclePrice
import wannabit.io.cosmostaion.databinding.ItemLendBinding
import java.math.BigDecimal
import java.math.RoundingMode

class LendListViewHolder(
    val context: Context,
    private val binding: ItemLendBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(
        lendMarket: HardProto.MoneyMarket?,
        priceFeed: com.kava.pricefeed.v1beta1.QueryProto.QueryPricesResponse?,
        lendMyDeposits: MutableList<CoinProto.Coin>,
        lendMyBorrows: MutableList<CoinProto.Coin>,
        listener: LendListAdapter.ClickListener
    ) {
        binding.apply {
            lendView.setBackgroundResource(R.drawable.item_bg)
            if (lendMarket == null && priceFeed == null) { return }

            lendView.setOnClickListener {
                listener.lendOption(lendMarket?.denom)
            }

            BaseData.assets?.firstOrNull { it.denom == lendMarket?.denom }?.let { asset ->
                asset.decimals?.let { decimal ->
                    val lendImgDenom = asset.originDenom
                    Picasso.get().load(KAVA_LEMD_IMG_URL + "lp" + lendImgDenom + ".png").fit().into(marketImg)
                    val title = lendMarket?.spotMarketId?.replace(":30", "")?.replace(":720", "")
                    marketType.text = title?.uppercase()

                    lendMarket?.let { market ->
                        var myDepositAmount = BigDecimal.ZERO
                        lendMyDeposits.forEach { coin ->
                            if (coin.denom == market.denom) {
                                myDepositAmount = coin.amount.toBigDecimal()
                            }
                        }
                        val marketIdPrice = priceFeed?.kavaOraclePrice(market.spotMarketId)
                        val myDepositValue = myDepositAmount.movePointLeft(decimal).multiply(marketIdPrice).setScale(12, RoundingMode.DOWN)
                        mySuppliedAmount.text = formatAmount(myDepositAmount.movePointLeft(decimal).setScale(decimal, RoundingMode.DOWN).toPlainString(), decimal)
                        mySuppliedDenom.text = asset.symbol
                        mySuppliedValue.text = formatAssetValue(myDepositValue)

                        var myBorrowAmount = BigDecimal.ZERO
                        lendMyBorrows.forEach { coin ->
                            if (coin.denom == market.denom) {
                                myBorrowAmount = coin.amount.toBigDecimal()
                            }
                        }
                        val myBorrowValue = myBorrowAmount.movePointLeft(decimal).multiply(marketIdPrice).setScale(12, RoundingMode.DOWN)
                        myBorrowedAmount.text = formatAmount(myBorrowAmount.movePointLeft(decimal).setScale(decimal, RoundingMode.DOWN).toPlainString(), decimal)
                        myBorrowedDenom.text = asset.symbol
                        myBorrowedValue.text = formatAssetValue(myBorrowValue)
                    }
                }

            } ?: run {
                return
            }
        }
    }
}