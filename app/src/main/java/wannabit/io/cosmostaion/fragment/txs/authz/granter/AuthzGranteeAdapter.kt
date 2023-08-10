package wannabit.io.cosmostaion.fragment.txs.authz.granter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import cosmos.authz.v1beta1.Authz.GrantAuthorization
import wannabit.io.cosmostaion.base.BaseData
import wannabit.io.cosmostaion.base.chains.ChainConfig
import wannabit.io.cosmostaion.databinding.ItemGranteeBinding
import wannabit.io.cosmostaion.model.type.Coin

class AuthzGranteeAdapter(private val context: Context, private val baseData: BaseData, private val chainConfig: ChainConfig) :
    ListAdapter<Pair<GrantAuthorization, Coin>, GranteeViewHolder>(GranteeDiffCallback) {

    companion object {
        private val GranteeDiffCallback = object : DiffUtil.ItemCallback<Pair<GrantAuthorization, Coin>>() {
            override fun areItemsTheSame(oldItem: Pair<GrantAuthorization, Coin>, newItem: Pair<GrantAuthorization, Coin>): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Pair<GrantAuthorization, Coin>, newItem: Pair<GrantAuthorization, Coin>): Boolean {
                return oldItem == newItem
            }
        }
    }

    private var onItemClickListener: ((Pair<GrantAuthorization, Coin>) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GranteeViewHolder {
        val binding = ItemGranteeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GranteeViewHolder(binding, context, baseData, chainConfig)
    }

    override fun onBindViewHolder(holder: GranteeViewHolder, position: Int) {
        val grant = currentList[position]
        holder.bind(grant)
        holder.itemView.setOnClickListener {
            onItemClickListener?.let { it(grant) }
        }
    }

    fun setOnItemClickListener(listener: (Pair<GrantAuthorization, Coin>) -> Unit) {
        onItemClickListener = listener
    }
}