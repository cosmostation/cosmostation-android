package wannabit.io.cosmostaion.ui.tx.option.general

import androidx.recyclerview.widget.RecyclerView
import com.cosmos.base.v1beta1.CoinProto
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.testnetClass.ChainGnoTestnet
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.amountHandlerLeft
import wannabit.io.cosmostaion.common.formatAmount
import wannabit.io.cosmostaion.common.formatAssetValue
import wannabit.io.cosmostaion.common.setTokenImg
import wannabit.io.cosmostaion.data.model.res.FeeData
import wannabit.io.cosmostaion.databinding.ItemAssetBinding
import java.math.BigDecimal

class AssetViewHolder(
    private val binding: ItemAssetBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(chain: BaseChain, feeData: FeeData) {
        binding.apply {
            feeData.denom?.let { denom ->
                BaseData.getAsset(chain.apiName, denom)?.let { asset ->
                    tokenImg.setTokenImg(asset)
                    tokenName.text = asset.symbol

                    val feeCoin = if (chain is ChainGnoTestnet) {
                        chain.gnoRpcFetcher?.gnoBalances?.firstOrNull { it.denom == denom }
                    } else {
                        chain.cosmosFetcher?.cosmosAvailable?.firstOrNull { it.denom == denom }
                    }

                    feeCoin?.let {
                        val amount = it.amount.toBigDecimal().amountHandlerLeft(asset.decimals ?: 6)
                        val price = BaseData.getPrice(asset.coinGeckoId)
                        val value = price.multiply(amount)

                        feeBalance.text = formatAmount(amount.toPlainString(), asset.decimals ?: 6)
                        feeValue.text = formatAssetValue(value)
                    } ?: run {
                        feeBalance.text = formatAmount(BigDecimal.ZERO.toPlainString(), asset.decimals ?: 6)
                        feeValue.text = formatAssetValue(BigDecimal.ZERO)
                    }
                }
            }
        }
    }

    fun baseFeeBind(chain: BaseChain, baseFeeData: CoinProto.DecCoin) {
        binding.apply {
            baseFeeData.denom?.let { denom ->
                BaseData.getAsset(chain.apiName, denom)?.let { asset ->
                    tokenImg.setTokenImg(asset)
                    tokenName.text = asset.symbol

                    chain.cosmosFetcher?.cosmosAvailable?.firstOrNull { it.denom == denom }
                        ?.let { feeCoin ->
                            val amount =
                                feeCoin.amount.toBigDecimal().amountHandlerLeft(asset.decimals ?: 6)
                            val price = BaseData.getPrice(asset.coinGeckoId)
                            val value = price.multiply(amount)

                            feeBalance.text =
                                formatAmount(amount.toPlainString(), asset.decimals ?: 6)
                            feeValue.text = formatAssetValue(value)

                        } ?: run {
                        feeBalance.text = formatAmount(BigDecimal.ZERO.toPlainString(), asset.decimals ?: 6)
                        feeValue.text = formatAssetValue(BigDecimal.ZERO)
                    }
                }
            }
        }
    }
}