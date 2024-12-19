package wannabit.io.cosmostaion.ui.tx.info.major

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.majorClass.ChainNamada
import wannabit.io.cosmostaion.databinding.ItemStakingInfoBinding
import wannabit.io.cosmostaion.databinding.ItemUnstakingInfoBinding
import wannabit.io.cosmostaion.ui.tx.info.OptionType
import wannabit.io.cosmostaion.ui.tx.info.StakingViewHolder
import wannabit.io.cosmostaion.ui.tx.info.UnstakingViewHolder

class NamadaBondingAdapter(
    val selectedChain: BaseChain,
    private val validators: MutableList<JsonObject>,
    private val delegations: MutableList<JsonElement>,
    private val optionType: OptionType,
    private var listener: ClickListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (optionType) {
            OptionType.STAKE -> {
                val binding = ItemStakingInfoBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                StakingViewHolder(parent.context, binding)
            }

            OptionType.UNSTAKE -> {
                val binding = ItemUnstakingInfoBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                UnstakingViewHolder(parent.context, binding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (optionType) {
            OptionType.STAKE -> {
                if (holder is StakingViewHolder) {
                    val delegation = delegations[position].asJsonObject
                    validators.firstOrNull { it["address"].asString == delegation["validator"].asJsonObject["address"].asString }?.let { validator ->
                        holder.namadaBind(
                            selectedChain as ChainNamada, validator, delegation, listener
                        )
                    }
                }
            }

            OptionType.UNSTAKE -> {

            }
        }
    }

    override fun getItemCount(): Int {
        return delegations.size
    }

    interface ClickListener {
        fun selectStakingAction()
        fun selectUnStakingCancelAction()
    }
}