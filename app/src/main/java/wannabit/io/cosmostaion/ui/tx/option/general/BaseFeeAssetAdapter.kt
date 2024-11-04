package wannabit.io.cosmostaion.ui.tx.option.general

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.cosmos.base.v1beta1.CoinProto
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.databinding.ItemAssetBinding

class BaseFeeAssetAdapter(private val selectedChain: BaseChain) :
    ListAdapter<CoinProto.DecCoin, AssetViewHolder>(BaseFeeAssetDiffCallback()) {

    private var onItemClickListener: ((String) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AssetViewHolder {
        val binding = ItemAssetBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AssetViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AssetViewHolder, position: Int) {
        val feeData = currentList[position]
        holder.baseFeeBind(selectedChain, feeData)

        holder.itemView.setOnClickListener {
            onItemClickListener?.let {
                feeData.denom?.let { denom -> it(denom) }
            }
        }
    }

    private class BaseFeeAssetDiffCallback : DiffUtil.ItemCallback<CoinProto.DecCoin>() {

        override fun areItemsTheSame(
            oldItem: CoinProto.DecCoin, newItem: CoinProto.DecCoin
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: CoinProto.DecCoin, newItem: CoinProto.DecCoin
        ): Boolean {
            return oldItem == newItem
        }
    }

    fun setOnItemClickListener(listener: (String) -> Unit) {
        onItemClickListener = listener
    }
}