package wannabit.io.cosmostaion.ui.tx.option.swap

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.cosmos.base.v1beta1.CoinProto
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.databinding.ItemAssetSelectBinding
import wannabit.io.cosmostaion.ui.tx.genTx.TargetAsset

class AssetSelectAdapter(
    private val selectedChain: BaseChain?, private val balances: MutableList<CoinProto.Coin>?
) : ListAdapter<TargetAsset, AssetSelectViewHolder>(AssetDiffCallback()) {

    private var onItemClickListener: ((String) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AssetSelectViewHolder {
        val binding =
            ItemAssetSelectBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AssetSelectViewHolder(parent.context, binding)
    }

    override fun onBindViewHolder(holder: AssetSelectViewHolder, position: Int) {
        val asset = currentList[position]
        holder.bind(selectedChain, asset, balances)

        holder.itemView.setOnClickListener {
            onItemClickListener?.let {
                it(asset.denom)
            }
        }
    }

    private class AssetDiffCallback : DiffUtil.ItemCallback<TargetAsset>() {

        override fun areItemsTheSame(oldItem: TargetAsset, newItem: TargetAsset): Boolean {
            return oldItem == newItem
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: TargetAsset, newItem: TargetAsset): Boolean {
            return oldItem == newItem
        }
    }

    fun setOnItemClickListener(listener: (String) -> Unit) {
        onItemClickListener = listener
    }
}
