package wannabit.io.cosmostaion.ui.qr

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.chain.EthereumLine
import wannabit.io.cosmostaion.database.model.BaseAccount
import wannabit.io.cosmostaion.databinding.ItemQrBinding

class QrCodAdapter(
    private val account: BaseAccount, private val selectedChain: BaseChain
) : RecyclerView.Adapter<QrCodeViewHolder>() {

    companion object {
        const val VIEW_TYPE_EVM_ITEM = 0
        const val VIEW_TYPE_COSMOS_ITEM = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QrCodeViewHolder {
        val binding = ItemQrBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return QrCodeViewHolder(parent.context, binding)
    }

    override fun onBindViewHolder(holder: QrCodeViewHolder, position: Int) {
        when (holder.itemViewType) {
            VIEW_TYPE_EVM_ITEM -> holder.evmBind(account, selectedChain as EthereumLine)
            else -> holder.bind(account, selectedChain)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (selectedChain is EthereumLine) {
            if (selectedChain.supportCosmos) {
                when (position) {
                    0 -> VIEW_TYPE_EVM_ITEM
                    else -> VIEW_TYPE_COSMOS_ITEM
                }
            } else {
                VIEW_TYPE_EVM_ITEM
            }
        } else {
            VIEW_TYPE_COSMOS_ITEM
        }
    }

    override fun getItemCount(): Int {
        return if (selectedChain is EthereumLine) {
            if (selectedChain.supportCosmos) {
                2
            } else {
                1
            }
        } else {
            1
        }
    }
}