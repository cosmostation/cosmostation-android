package wannabit.io.cosmostaion.ui.main.chain.major

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.sui.rpc.v2.ObjectProto
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.databinding.ItemNftBinding
import wannabit.io.cosmostaion.ui.main.chain.cosmos.NftViewHolder

class SuiNftAdapter(val chain: BaseChain) :
    ListAdapter<ObjectProto.Object, NftViewHolder>(NftDiffCallback()) {

    private var onItemClickListener: ((BaseChain, ObjectProto.Object?) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NftViewHolder {
        val binding = ItemNftBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NftViewHolder(parent.context, binding)
    }

    override fun onBindViewHolder(holder: NftViewHolder, position: Int) {
        val info = currentList[position]
        holder.suiBind(info)
        holder.itemView.setOnClickListener {
            onItemClickListener?.let {
                it(chain, info)
            }
        }
    }

    private class NftDiffCallback : DiffUtil.ItemCallback<ObjectProto.Object>() {

        override fun areItemsTheSame(
            oldItem: ObjectProto.Object,
            newItem: ObjectProto.Object
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: ObjectProto.Object,
            newItem: ObjectProto.Object
        ): Boolean {
            return oldItem == newItem
        }
    }

    fun setOnItemClickListener(listener: (BaseChain, ObjectProto.Object?) -> Unit) {
        onItemClickListener = listener
    }
}