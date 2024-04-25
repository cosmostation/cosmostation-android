package wannabit.io.cosmostaion.ui.tx.step.service

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.databinding.ItemAllChainCompoundingBinding

class AllChainCompoundingAdapter :
    ListAdapter<ClaimAllModel, AllChainCompoundingViewHolder>(AllChainCompoundingDiffCallback()) {

    private var onItemClickListener: ((ClaimAllModel) -> Unit)? = null

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): AllChainCompoundingViewHolder {
        val binding =
            ItemAllChainCompoundingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AllChainCompoundingViewHolder(parent.context, binding)
    }

    override fun onBindViewHolder(holder: AllChainCompoundingViewHolder, position: Int) {
        val reward = currentList[position]
        holder.bind(reward)

        holder.itemView.findViewById<View>(R.id.delete_layout).setOnClickListener {
            onItemClickListener?.let {
                it(reward)
            }
        }
    }

    private class AllChainCompoundingDiffCallback : DiffUtil.ItemCallback<ClaimAllModel>() {

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