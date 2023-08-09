package wannabit.io.cosmostaion.fragment.txs.authz.grantee

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import cosmos.authz.v1beta1.Authz
import wannabit.io.cosmostaion.databinding.ItemGranterBinding

class RevokeAdapterprivate(
    val selectedItems: MutableList<Authz.GrantAuthorization>
) :
    ListAdapter<Authz.GrantAuthorization, GranterViewHolder>(GranterDiffCallback) {

    companion object {
        private val GranterDiffCallback =
            object : DiffUtil.ItemCallback<Authz.GrantAuthorization>() {
                override fun areItemsTheSame(
                    oldItem: Authz.GrantAuthorization,
                    newItem: Authz.GrantAuthorization
                ): Boolean {
                    return oldItem == newItem
                }

                override fun areContentsTheSame(
                    oldItem: Authz.GrantAuthorization,
                    newItem: Authz.GrantAuthorization
                ): Boolean {
                    return oldItem == newItem
                }
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GranterViewHolder {
        val binding = ItemGranterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GranterViewHolder(binding, selectedItems)
    }

    override fun onBindViewHolder(holder: GranterViewHolder, position: Int) {
        val grant = currentList[position]
        holder.bind(grant)
    }
}