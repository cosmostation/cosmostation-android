package wannabit.io.cosmostaion.ui.main.chain

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Window
import androidx.recyclerview.widget.LinearLayoutManager
import com.cosmos.base.v1beta1.CoinProto.Coin
import com.cosmos.distribution.v1beta1.DistributionProto
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.chain.cosmosClass.DYDX_USDC_DENOM
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.dialogResize
import wannabit.io.cosmostaion.databinding.DialogRewardBinding
import java.math.BigDecimal
import java.math.RoundingMode

class RewardDialog(
    context: Context,
    val selectedChain: CosmosLine,
    val rewards: MutableList<DistributionProto.DelegationDelegatorReward>
) : Dialog(context, R.style.CustomDialogTheme) {

    private lateinit var binding: DialogRewardBinding

    private lateinit var rewardAdapter: RewardAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DialogRewardBinding.inflate(layoutInflater)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(binding.root)

        context.dialogResize(this, 1f, 0.75f)

        initData()
        setUpClickAction()
    }

    private fun initData() {
        var rewardCoins: MutableList<Coin> = mutableListOf()
        rewards.forEach { delegatorRewards ->
            delegatorRewards.rewardList.forEach { deCoin ->
                val amount =
                    deCoin.amount.toBigDecimal().movePointLeft(18).setScale(0, RoundingMode.DOWN)
                if (amount > BigDecimal.ZERO) {
                    val index = rewardCoins.indexOfFirst { it.denom == deCoin.denom }
                    if (index != -1) {
                        val existingAmount = BigDecimal(rewardCoins[index].amount)
                        val addedAmount = existingAmount.add(amount)

                        val tempRewardCoins = rewardCoins.toMutableList()
                        tempRewardCoins[index] = Coin.newBuilder().setDenom(deCoin.denom)
                            .setAmount(addedAmount.toPlainString()).build()
                        rewardCoins = tempRewardCoins

                    } else {
                        rewardCoins.add(
                            Coin.newBuilder().setDenom(deCoin.denom).setAmount(amount.toString())
                                .build()
                        )
                    }
                }
            }
        }

        initRecyclerView(sortRewardCoins(rewardCoins))
    }

    private fun sortRewardCoins(rewardCoins: MutableList<Coin>): MutableList<Coin> {
        rewardCoins.sortWith { o1, o2 ->
            when {
                o1.denom == selectedChain.stakeDenom -> -1
                o2.denom == selectedChain.stakeDenom -> 1
                o1.denom == DYDX_USDC_DENOM -> -1
                o2.denom == DYDX_USDC_DENOM -> 1

                BaseData.getAsset(selectedChain.apiName, o1.denom) == null -> 1
                BaseData.getAsset(selectedChain.apiName, o2.denom) == null -> -1

                else -> {
                    var value1 = BigDecimal.ZERO
                    var value2 = BigDecimal.ZERO

                    BaseData.getAsset(selectedChain.apiName, o1.denom)?.let { asset ->
                        val price = BaseData.getPrice(asset.coinGeckoId)
                        val amount = o1.amount.toBigDecimal()
                        value1 = price.multiply(amount).movePointLeft(asset.decimals ?: 6)
                            .setScale(6, RoundingMode.DOWN)
                    }

                    BaseData.getAsset(selectedChain.apiName, o2.denom)?.let { asset ->
                        val price = BaseData.getPrice(asset.coinGeckoId)
                        val amount = o2.amount.toBigDecimal()
                        value2 = price.multiply(amount).movePointLeft(asset.decimals ?: 6)
                            .setScale(6, RoundingMode.DOWN)
                    }
                    when {
                        value1 > value2 -> -1
                        value1 < value2 -> 1
                        else -> 0
                    }
                }
            }
        }
        return rewardCoins
    }

    private fun initRecyclerView(rewardCoins: MutableList<Coin>) {
        binding.rewardDetail.text =
            context.getString(R.string.title_reward_detail, rewardCoins.count().toString())
        binding.recycler.apply {
            rewardAdapter = RewardAdapter(selectedChain)
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = rewardAdapter
            rewardAdapter.submitList(rewardCoins)
        }
    }

    private fun setUpClickAction() {
        binding.btnClose.setOnClickListener {
            this@RewardDialog.dismiss()
        }
    }
}