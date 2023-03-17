package wannabit.io.cosmostaion.widget.txDetail

import android.content.Context
import android.graphics.PorterDuff
import android.view.View
import androidx.core.content.ContextCompat
import cosmos.distribution.v1beta1.Tx.MsgWithdrawDelegatorReward
import cosmos.tx.v1beta1.ServiceOuterClass
import wannabit.io.cosmostaion.base.BaseData
import wannabit.io.cosmostaion.base.chains.ChainConfig
import wannabit.io.cosmostaion.databinding.ItemTxRewardBinding
import wannabit.io.cosmostaion.utils.WDp

class TxStakingRewardHolder(itemView: View) : TxHolder(itemView) {

    private lateinit var binding: ItemTxRewardBinding

    override fun onBindMsg(c: Context?, baseData: BaseData?, chainConfig: ChainConfig?, response: ServiceOuterClass.GetTxResponse?, position: Int, address: String?) {
        super.onBindMsg(c, baseData, chainConfig, response, position, address)
        binding = ItemTxRewardBinding.bind(itemView)
        binding.txRewardIcon.setColorFilter(ContextCompat.getColor(c!!, chainConfig!!.chainColor()), PorterDuff.Mode.SRC_IN)

        response?.apply {
            val msg = MsgWithdrawDelegatorReward.parseFrom(response.tx.body.getMessages(position).value)
            binding.txRewardDelegator.text = msg.delegatorAddress
            binding.txRewardValidator.text = msg.validatorAddress
            baseData?.apply {
                binding.txRewardMoniker.text = "(" + baseData.getValidatorInfo(msg.validatorAddress).description.moniker + ")"

                val rewardsCoins = WDp.onParseStakeReward(response, position)
                if (rewardsCoins.size > 0) {
                    binding.rewardLayout0.visibility = View.VISIBLE
                    WDp.setDpCoin(c, baseData, chainConfig, rewardsCoins[0], binding.txRewardSymbol0, binding.txRewardAmount0)
                }

                if (rewardsCoins.size > 1) {
                    binding.rewardLayout1.visibility = View.VISIBLE
                    WDp.setDpCoin(c, baseData, chainConfig, rewardsCoins[1], binding.txRewardSymbol1, binding.txRewardAmount1)
                }

                if (rewardsCoins.size > 2) {
                    binding.rewardLayout2.visibility = View.VISIBLE
                    WDp.setDpCoin(c, baseData, chainConfig, rewardsCoins[2], binding.txRewardSymbol2, binding.txRewardAmount2)
                }

                if (rewardsCoins.size > 3) {
                    binding.rewardLayout3.visibility = View.VISIBLE
                    WDp.setDpCoin(c, baseData, chainConfig, rewardsCoins[3], binding.txRewardSymbol3, binding.txRewardAmount3)
                }
            }
        }
    }
}