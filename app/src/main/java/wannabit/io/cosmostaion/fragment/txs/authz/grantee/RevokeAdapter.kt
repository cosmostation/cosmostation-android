package wannabit.io.cosmostaion.fragment.txs.authz.grantee

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import cosmos.authz.v1beta1.Authz
import wannabit.io.cosmostaion.databinding.ItemGranterBinding
import wannabit.io.cosmostaion.databinding.ItemRevokeBinding

class RevokeAdapter :
    ListAdapter<Authz.GrantAuthorization, RevokeViewHolder>(RevokeDiffCallback) {

    companion object {
        private val RevokeDiffCallback =
            object : DiffUtil.ItemCallback<Authz.GrantAuthorization>() {
                override fun areItemsTheSame(oldItem: Authz.GrantAuthorization, newItem: Authz.GrantAuthorization): Boolean {
                    return oldItem == newItem
                }

                override fun areContentsTheSame(oldItem: Authz.GrantAuthorization, newItem: Authz.GrantAuthorization): Boolean {
                    return oldItem == newItem
                }
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RevokeViewHolder {
        val binding = ItemRevokeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RevokeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RevokeViewHolder, position: Int) {
        val grant = currentList[position]
        holder.bind(grant)
    }
}