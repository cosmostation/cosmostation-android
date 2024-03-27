package wannabit.io.cosmostaion.ui.tx.step

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.database.model.BaseAccount
import wannabit.io.cosmostaion.databinding.ItemAllChainClaimBinding

class AllChainClaimAdapter :
    ListAdapter<ClaimAllModel, AllChainClaimViewHolder>(AllChainClaimDiffCallback()) {

    private var onItemClickListener: ((ClaimAllModel) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllChainClaimViewHolder {
        val binding =
            ItemAllChainClaimBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AllChainClaimViewHolder(parent.context, binding)
    }

    override fun onBindViewHolder(holder: AllChainClaimViewHolder, position: Int) {
        val reward = currentList[position]
        holder.bind(reward)

        holder.itemView.findViewById<View>(R.id.delete_layout).setOnClickListener {
            onItemClickListener?.let {
                it(reward)
            }
        }
    }

    private class AllChainClaimDiffCallback : DiffUtil.ItemCallback<ClaimAllModel>() {

        override fun areItemsTheSame(
            oldItem: ClaimAllModel, newItem: ClaimAllModel
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: ClaimAllModel, newItem: ClaimAllModel
        ): Boolean {
            return oldItem == newItem
        }
    }

    fun setOnItemClickListener(listener: (ClaimAllModel) -> Unit) {
        onItemClickListener = listener
    }
}