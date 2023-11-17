package wannabit.io.cosmostaion.ui.tx.info.kava

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.kava.swap.v1beta1.QueryProto
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.formatAmount
import wannabit.io.cosmostaion.common.formatAssetValue
import wannabit.io.cosmostaion.common.setTokenImg
import wannabit.io.cosmostaion.databinding.ItemPoolBinding
import java.math.RoundingMode

class PoolListViewHolder(
    private val binding: ItemPoolBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bindMyPool(
        selectChain: CosmosLine,
        pool: QueryProto.PoolResponse?,
        deposit: QueryProto.DepositResponse?,
        listener: PoolListAdapter.ClickListener
    ) {
        binding.apply {
            poolView.setBackgroundResource(R.drawable.item_bg)
            depositLayout.visibility = View.VISIBLE
            if (deposit == null || pool == null) { return }
            poolView.setOnClickListener {
                listener.myPoolSelect(deposit.poolId, deposit)
            }
            val coin1 = pool.getCoins(0)
            val coin2 = pool.getCoins(1)

            val my1 = deposit.getSharesValue(0)
            val my2 = deposit.getSharesValue(1)

            BaseData.getAsset(selectChain.apiName, coin1.denom)?.let { asset1 ->
                BaseData.getAsset(selectChain.apiName, coin2.denom)?.let { asset2 ->
                    asset1.decimals?.let { decimal1 ->
                        asset2.decimals?.let { decimal2 ->
                            pool0Img.setTokenImg(asset1)
                            pool1Img.setTokenImg(asset2)
                            poolType.text = asset1.symbol + " : " + asset2.symbol

                            val coin1Price = BaseData.getPrice(asset1.coinGeckoId)
                            val coin2Price = BaseData.getPrice(asset2.coinGeckoId)
                            val coin1Value = coin1.amount.toBigDecimal().multiply(coin1Price).movePointLeft(decimal1).setScale(12, RoundingMode.DOWN)
                            val coin2Value = coin2.amount.toBigDecimal().multiply(coin2Price).movePointLeft(decimal2).setScale(12, RoundingMode.DOWN)

                            tvlValue.text = formatAssetValue(coin1Value.add(coin2Value))
                            pool0Amount.text = formatAmount(coin1.amount.toBigDecimal().movePointLeft(decimal1).setScale(decimal1, RoundingMode.DOWN).toPlainString(), 3)
                            pool0Denom.text = asset1.symbol
                            pool1Amount.text = formatAmount(coin2.amount.toBigDecimal().movePointLeft(decimal2).setScale(decimal2, RoundingMode.DOWN).toPlainString(), 3)
                            pool1Denom.text = asset2.symbol

                            val my1Value = my1.amount.toBigDecimal().multiply(coin1Price).movePointLeft(decimal1).setScale(12, RoundingMode.DOWN)
                            val my2Value = my2.amount.toBigDecimal().multiply(coin2Price).movePointLeft(decimal2).setScale(12, RoundingMode.DOWN)
                            depositValue.text = formatAssetValue(my1Value.add(my2Value))
                        }
                    }
                }
            }
        }
    }

    fun bindOtherPool(selectChain: CosmosLine,
                      pool: QueryProto.PoolResponse?,
                      listener: PoolListAdapter.ClickListener
    ) {
        binding.apply {
            poolView.setBackgroundResource(R.drawable.item_bg)
            depositLayout.visibility = View.GONE
            if (pool == null) { return }
            poolView.setOnClickListener {
                listener.otherPoolSelect(pool.name)
            }
            val coin1 = pool.getCoins(0)
            val coin2 = pool.getCoins(1)

            BaseData.getAsset(selectChain.apiName, coin1.denom)?.let { asset1 ->
                BaseData.getAsset(selectChain.apiName, coin2.denom)?.let { asset2 ->
                    asset1.decimals?.let { decimal1 ->
                        asset2.decimals?.let { decimal2 ->
                            pool0Img.setTokenImg(asset1)
                            pool1Img.setTokenImg(asset2)
                            poolType.text = asset1.symbol + " : " + asset2.symbol

                            val coin1Price = BaseData.getPrice(asset1.coinGeckoId)
                            val coin2Price = BaseData.getPrice(asset2.coinGeckoId)
                            val coin1Value = coin1.amount.toBigDecimal().multiply(coin1Price).movePointLeft(decimal1).setScale(12, RoundingMode.DOWN)
                            val coin2Value = coin2.amount.toBigDecimal().multiply(coin2Price).movePointLeft(decimal2).setScale(12, RoundingMode.DOWN)

                            tvlValue.text = formatAssetValue(coin1Value.add(coin2Value))
                            pool0Amount.text = formatAmount(coin1.amount.toBigDecimal().movePointLeft(decimal1).setScale(decimal1, RoundingMode.DOWN).toPlainString(), 3)
                            pool0Denom.text = asset1.symbol
                            pool1Amount.text = formatAmount(coin2.amount.toBigDecimal().movePointLeft(decimal2).setScale(decimal2, RoundingMode.DOWN).toPlainString(), 3)
                            pool1Denom.text = asset2.symbol
                        }
                    }
                }
            }
        }
    }
}