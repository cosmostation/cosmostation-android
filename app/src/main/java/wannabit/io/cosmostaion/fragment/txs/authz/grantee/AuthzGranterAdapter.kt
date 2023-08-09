package wannabit.io.cosmostaion.fragment.txs.authz.grantee

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import cosmos.authz.v1beta1.Authz.GrantAuthorization
import wannabit.io.cosmostaion.databinding.ItemGranterBinding

class AuthzGranterAdapter(
    private val selectedItems: MutableList<GrantAuthorization>
) :
    ListAdapter<GrantAuthorization, GranterViewHolder>(GranterDiffCallback) {

    companion object {
        private val GranterDiffCallback = object : DiffUtil.ItemCallback<GrantAuthorization>() {
            override fun areItemsTheSame(oldItem: GrantAuthorization, newItem: GrantAuthorization): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: GrantAuthorization, newItem: GrantAuthorization): Boolean {
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