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
                txAuthzType.text = msg.msgTypeUrl
            }
        }
    }
}
