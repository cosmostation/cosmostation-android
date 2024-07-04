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
import wannabit.io.cosmostaion.data.model.res.Token
import wannabit.io.cosmostaion.database.Prefs
import wannabit.io.cosmostaion.databinding.ItemCosmosLineTokenBinding
import java.math.RoundingMode

class TokenViewHolder(
    val context: Context, private val binding: ItemCosmosLineTokenBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(chain: BaseChain, token: Token, cnt: Int, position: Int) {
        binding.apply {
            coinView.setBackgroundResource(R.drawable.item_bg)
            headerLayout.visibleOrGone(position == 0)
            headerTitle.text = if (chain.supportCw20) {
                context.getString(R.string.str_contract_tokens)
            } else {
                context.getString(R.string.str_erc20_tokens)
            }
//            headerTitle.text = when (chain) {
//                is EthereumLine -> { context.getString(R.string.str_erc20_tokens) }
//                is ChainOkt996Keccak -> { context.getString(R.string.str_kip20_tokens) }
//                else -> { context.getString(R.string.str_contract_tokens) }
//            }
            headerCnt.text = cnt.toString()

            tokenImg.setTokenImg(token.assetImg())
            tokenName.text = token.symbol

            tokenPrice.text = formatAssetValue(BaseData.getPrice(token.coinGeckoId))
            BaseData.lastUpDown(token.coinGeckoId).let { lastUpDown ->
                tokenPriceChange.priceChangeStatusColor(lastUpDown)
                tokenPriceChange.text = priceChangeStatus(lastUpDown)
            }

            token.amount?.toBigDecimal()?.movePointLeft(token.decimals)
                ?.setScale(6, RoundingMode.DOWN)?.let { amount ->
                    if (Prefs.hideValue) {
                        coinAmount.visibility = View.GONE
                        coinAmountValue.visibility = View.GONE
                        hideValue.visibility = View.VISIBLE
                    } else {
                        coinAmount.visibility = View.VISIBLE
                        coinAmountValue.visibility = View.VISIBLE
                        hideValue.visibility = View.GONE

                        coinAmount.text = formatAmount(amount.toPlainString(), 6)
                        if (chain.supportCw20) {
                            chain.grpcFetcher?.let {
                                coinAmountValue.text =
                                    formatAssetValue(it.tokenValue(token.address))
                            }
                        } else {
                            chain.evmRpcFetcher?.let {
                                coinAmountValue.text =
                                    formatAssetValue(it.tokenValue(token.address))
                            }
                        }
                    }
                }
        }
    }
}