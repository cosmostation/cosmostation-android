package wannabit.io.cosmostaion.widget.txDetail

import android.content.Context
import android.graphics.PorterDuff
import android.view.View
import androidx.core.content.ContextCompat
import cosmos.tx.v1beta1.ServiceOuterClass
import wannabit.io.cosmostaion.base.BaseData
import wannabit.io.cosmostaion.base.chains.ChainConfig
import wannabit.io.cosmostaion.databinding.ItemTxAuthzRevokeBinding

class TxAuthzRevokeHolder(itemView: View) : TxHolder(itemView) {

    private var binding: ItemTxAuthzRevokeBinding

    init {
        binding = ItemTxAuthzRevokeBinding.bind(itemView)
    }

    override fun onBindMsg(c: Context?, baseData: BaseData?, chainConfig: ChainConfig?, response: ServiceOuterClass.GetTxResponse?, position: Int, address: String?) {
        super.onBindMsg(c, baseData, chainConfig, response, position, address)
        binding.apply {
            authzRevokeImg.setColorFilter(ContextCompat.getColor(c!!, chainConfig!!.chainColor()), PorterDuff.Mode.SRC_IN)
            response?.apply {
                val msg = cosmos.authz.v1beta1.Tx.MsgRevoke.parseFrom(response.tx.body.getMessages(position).value)
                txGranter.text = msg.granter
                txGrantee.text = msg.grantee

                val grantType = when {
                    msg.msgTypeUrl.contains(cosmos.distribution.v1beta1.Tx.MsgWithdrawDelegatorReward.getDescriptor().fullName) -> "Claim Reward"
                    msg.msgTypeUrl.contains(cosmos.distribution.v1beta1.Tx.MsgWithdrawValidatorCommission.getDescriptor().fullName) -> "Claim Commission"
                    msg.msgTypeUrl.equals(cosmos.gov.v1beta1.Tx.MsgVote.getDescriptor().fullName, true) -> "Vote"
                    msg.msgTypeUrl.equals(cosmos.gov.v1beta1.Tx.MsgVoteWeighted.getDescriptor().fullName, true) -> "Vote Weighted"
                    msg.msgTypeUrl.contains(cosmos.staking.v1beta1.Tx.MsgDelegate.getDescriptor().fullName) -> "Delegate"
                    msg.msgTypeUrl.contains(cosmos.staking.v1beta1.Tx.MsgBeginRedelegate.getDescriptor().fullName) -> "Redelegate"
                    msg.msgTypeUrl.contains(cosmos.staking.v1beta1.Tx.MsgUndelegate.getDescriptor().fullName) -> "Undelegate"
                    msg.msgTypeUrl.contains(cosmos.bank.v1beta1.Tx.MsgSend.getDescriptor().fullName) -> "Send"
                    else -> "Etc"
                }
                txAuthzType.text = grantType
            }
        }
    }
}
