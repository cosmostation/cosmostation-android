package wannabit.io.cosmostaion.ui.option.tx.swap

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.cosmos.base.v1beta1.CoinProto
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.data.model.res.Asset
import wannabit.io.cosmostaion.databinding.ItemAssetSelectBinding

class AssetSelectAdapter(
    private val selectedChain: CosmosLine?,
    private val balances: MutableList<CoinProto.Coin>?
) : ListAdapter<Asset, AssetSelectViewHolder>(AssetDiffCallback()) {

    private var onItemClickListener: ((String) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AssetSelectViewHolder {
        val binding = ItemAssetSelectBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AssetSelectViewHolder(parent.context, binding)
    }

    override fun onBindViewHolder(holder: AssetSelectViewHolder, position: Int) {
        val asset = currentList[position]
        holder.bind(selectedChain, asset, balances)

        holder.itemView.setOnClickListener {
            onItemClickListener?.let {
                asset.denom?.let { denom -> it(denom)}
            }
        }
    }

    private class AssetDiffCallback : DiffUtil.ItemCallback<Asset>() {

        override fun areItemsTheSame(oldItem: Asset, newItem: Asset): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Asset, newItem: Asset): Boolean {
            return oldItem == newItem
        }
    }

    fun setOnItemClickListener(listener: (String) -> Unit) {
        onItemClickListener = listener
    }
}
