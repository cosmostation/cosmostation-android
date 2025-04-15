package wannabit.io.cosmostaion.ui.tx.option.validator

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.fetcher.FinalityProvider
import wannabit.io.cosmostaion.common.toHex
import wannabit.io.cosmostaion.databinding.ItemValidatorDefaultBinding

class FinalityProviderAdapter(private val chain: BaseChain) :
    ListAdapter<FinalityProvider, ValidatorDefaultViewHolder>(ProviderDefaultDiffCallback()) {

    private var onItemClickListener: ((String) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ValidatorDefaultViewHolder {
        val binding =
            ItemValidatorDefaultBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ValidatorDefaultViewHolder(parent.context, binding)
    }

    override fun onBindViewHolder(holder: ValidatorDefaultViewHolder, position: Int) {
        val provider = currentList[position]
        holder.providerBind(chain, provider)

        holder.itemView.setOnClickListener {
            onItemClickListener?.let {
                it(provider.provider.btcPk.toByteArray().toHex())
            }
        }
    }

    private class ProviderDefaultDiffCallback : DiffUtil.ItemCallback<FinalityProvider>() {

        override fun areItemsTheSame(
            oldItem: FinalityProvider, newItem: FinalityProvider
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: FinalityProvider, newItem: FinalityProvider
        ): Boolean {
            return oldItem == newItem
        }
    }

    fun setOnItemClickListener(listener: (String) -> Unit) {
        onItemClickListener = listener
    }
}