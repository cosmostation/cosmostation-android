package wannabit.io.cosmostaion.ui.main.chain

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.chain.cosmosClass.ChainBinanceBeacon
import wannabit.io.cosmostaion.chain.cosmosClass.ChainOkt60
import wannabit.io.cosmostaion.common.formatAmount
import wannabit.io.cosmostaion.common.setTokenImg
import wannabit.io.cosmostaion.common.visibleOrGone
import wannabit.io.cosmostaion.data.model.res.Coin
import wannabit.io.cosmostaion.database.Prefs
import wannabit.io.cosmostaion.databinding.ItemCosmosLineEtcBinding
import java.math.RoundingMode

class CoinEtcViewHolder(
    val context: Context,
    private val binding: ItemCosmosLineEtcBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bindBeaconCoin(line: CosmosLine, coin: Coin, position: Int, cnt: Int) {
        binding.apply {
            coinView.setBackgroundResource(R.drawable.item_bg)

            headerLayout.visibleOrGone(position == 0)
            headerTitle.text = context.getString(R.string.str_native_coins)
            headerCnt.text = cnt.toString()

            val token = line.lcdBeaconTokens.firstOrNull { it.symbol == coin.denom }
            if (token != null) {
                tokenImg.setTokenImg(ChainBinanceBeacon().assetImg(token.originalSymbol))
                tokenName.text = token.originalSymbol.uppercase()
                tokenDescription.text = token.name
            }

            if (Prefs.hideValue) {
                tokenAmount.text = "✱✱✱✱"
            } else {
                val amount = coin.amount.toBigDecimal().setScale(8, RoundingMode.DOWN)
                tokenAmount.text = formatAmount(amount.toPlainString(), 8)
            }
        }
    }

    fun bindOktCoin(line: ChainOkt60, coin: Coin, position: Int, cnt: Int) {
        binding.apply {
            coinView.setBackgroundResource(R.drawable.item_bg)

            headerLayout.visibleOrGone(position == 0)
            headerTitle.text = context.getString(R.string.str_kip10_coins)
            headerCnt.text = cnt.toString()

            line.oktTokenInfo?.data?.firstOrNull { it.symbol == coin.denom }?.let { token ->
                tokenImg.setTokenImg(ChainOkt60().assetImg(token.originalSymbol))
                tokenName.text = token.originalSymbol.uppercase()
                tokenDescription.text = token.description

                if (Prefs.hideValue) {
                    tokenAmount.text = "✱✱✱✱"
                } else {
                    val availableAmount = line.lcdBalanceAmount(coin.denom)
                    tokenAmount.text = formatAmount(availableAmount.toPlainString(), 18)
                }
            }
        }
    }
}