package wannabit.io.cosmostaion.widget.txDetail.liquidstaking

import android.content.Context
import android.graphics.PorterDuff
import android.view.View
import androidx.core.content.ContextCompat
import cosmos.tx.v1beta1.ServiceOuterClass
import pstake.lscosmos.v1beta1.Msgs
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.base.BaseData
import wannabit.io.cosmostaion.base.chains.ChainConfig
import wannabit.io.cosmostaion.databinding.ItemTxPersisLiquidBinding
import wannabit.io.cosmostaion.utils.WDp
import wannabit.io.cosmostaion.widget.txDetail.TxHolder

class TxPersisLiquidHolder(itemView: View) : TxHolder(itemView) {

    private lateinit var binding: ItemTxPersisLiquidBinding

    override fun onBindMsg(c: Context?, baseData: BaseData?, chainConfig: ChainConfig?, response: ServiceOuterClass.GetTxResponse?, position: Int, address: String?) {
        super.onBindMsg(c, baseData, chainConfig, response, position, address)
        binding = ItemTxPersisLiquidBinding.bind(itemView)
        binding.liquidImg.setColorFilter(ContextCompat.getColor(c!!, chainConfig!!.chainColor()), PorterDuff.Mode.SRC_IN)

        response?.apply {
            if (this.tx.body.getMessages(position).typeUrl.contains("MsgLiquidStake")) {
                val msg = Msgs.MsgLiquidStake.parseFrom(response.tx.body.getMessages(position).value)
                binding.liquidTitle.text = c.getString(R.string.tx_persis_liquid_stake)
                binding.txLiquidDelegator.text = msg.delegatorAddress

                WDp.setDpCoin(c, baseData, chainConfig, msg.amount.denom, msg.amount.amount, binding.txLiquidAmountInSymbol, binding.txLiquidAmountIn)
                WDp.onParseLiquidAmountGrpc(response, position).find { it.denom.startsWith("stk/") }?.let { dpCoin ->
                    WDp.setDpCoin(c, baseData, chainConfig, dpCoin, binding.txLiquidAmountOutSymbol, binding.txLiquidAmountOut)
                }
            }
        }
    }
}