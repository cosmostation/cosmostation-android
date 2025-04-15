package wannabit.io.cosmostaion.ui.tx.option.validator

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.cosmos.staking.v1beta1.StakingProto.Validator
import com.google.gson.JsonObject
import com.initia.mstaking.v1.StakingProto
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.cosmosClass.ChainZenrock
import wannabit.io.cosmostaion.chain.fetcher.FinalityProvider
import wannabit.io.cosmostaion.chain.majorClass.ChainBitCoin86
import wannabit.io.cosmostaion.chain.testnetClass.ChainInitiaTestnet
import wannabit.io.cosmostaion.common.toHex
import wannabit.io.cosmostaion.databinding.ItemValidatorBinding
import wannabit.io.cosmostaion.ui.tx.info.major.BtcTxType

class ValidatorAdapter(
    private val selectedChain: BaseChain, private val btcTxType: BtcTxType? = null
) : ListAdapter<Any, ValidatorViewHolder>(ValidatorDiffCallback()) {

    private var onItemClickListener: ((String) -> Unit)? = null
    private var onBitItemClickListener: ((Pair<JsonObject, FinalityProvider>) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ValidatorViewHolder {
        val binding =
            ItemValidatorBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ValidatorViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ValidatorViewHolder, position: Int) {
        val validator = currentList[position]
        when (selectedChain) {
            is ChainBitCoin86 -> {
                holder.bitBind(selectedChain, btcTxType, validator as Pair<JsonObject, FinalityProvider>)
                holder.itemView.setOnClickListener {
                    onBitItemClickListener?.let {
                        it(validator)
                    }
                }
            }

            is ChainInitiaTestnet -> {
                holder.initiaBind(selectedChain, validator as StakingProto.Validator)
                holder.itemView.setOnClickListener {
                    onItemClickListener?.let {
                        it(validator.operatorAddress)
                    }
                }
            }

            is ChainZenrock -> {
                holder.zenrockBind(
                    selectedChain,
                    validator as com.zrchain.validation.HybridValidationProto.ValidatorHV
                )
                holder.itemView.setOnClickListener {
                    onItemClickListener?.let {
                        it(validator.operatorAddress)
                    }
                }
            }

            else -> {
                holder.bind(selectedChain, validator as Validator)

                holder.itemView.setOnClickListener {
                    onItemClickListener?.let {
                        it(validator.operatorAddress)
                    }
                }
            }
        }
    }

    private class ValidatorDiffCallback : DiffUtil.ItemCallback<Any>() {
        override fun areItemsTheSame(
            oldItem: Any, newItem: Any
        ): Boolean {
            return oldItem == newItem
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(
            oldItem: Any, newItem: Any
        ): Boolean {
            return oldItem == newItem
        }
    }

    fun setOnItemClickListener(listener: (String) -> Unit) {
        onItemClickListener = listener
    }

    fun setOnBitItemClickListener(listener: (Pair<JsonObject, FinalityProvider>) -> Unit) {
        onBitItemClickListener = listener
    }
}
