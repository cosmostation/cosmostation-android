package wannabit.io.cosmostaion.ui.main

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import wannabit.io.cosmostaion.database.model.Chain
import wannabit.io.cosmostaion.database.model.ChainConfig
import wannabit.io.cosmostaion.databinding.ItemDashboardBinding
import wannabit.io.cosmostaion.ui.line.CosmosLineActivity
import wannabit.io.cosmostaion.ui.line.EthereumLineActivity

class DashboardAdapter(private val context: Context, val chains: MutableList<Chain> = mutableListOf()) : RecyclerView.Adapter<DashboardViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashboardViewHolder {
        val binding = ItemDashboardBinding.inflate(LayoutInflater.from(context), parent, false)
        return DashboardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DashboardViewHolder, position: Int) {
        val chain = chains[position]
        holder.binding.apply {
            name.text = chain.chainName
            ApplicationViewModel.shared.pricesLiveData.value?.let {
                it.find {
                    when (chain.chainConfig) {
                        is ChainConfig.Cosmos -> {
                            it.coinGeckoId.lowercase() == chain.chainConfig.chainName.lowercase()
                        }

                        is ChainConfig.Ethereum -> {
                            it.coinGeckoId.lowercase() == chain.chainConfig.chainName.lowercase()
                        }

                        else -> {
                            false
                        }
                    }
                }?.let { price.text = "${it.current_price}" }
            }
            ApplicationViewModel.shared.balancesLiveData.value?.let {
                it.find {
                    when (chain.chainConfig) {
                        is ChainConfig.Cosmos -> {
                            it.denom.lowercase() == chain.chainConfig.baseDenom.lowercase()
                        }

                        is ChainConfig.Ethereum -> {
                            it.denom.lowercase() == chain.chainConfig.displayDenom.lowercase()
                        }

                        else -> {
                            false
                        }
                    }
                }?.let { amount.text = it.amount }
            }
            root.setOnClickListener {
                when (chain.chainConfig) {
                    is ChainConfig.Cosmos -> {
                        context.startActivity(Intent(context, CosmosLineActivity::class.java))
                    }

                    is ChainConfig.Ethereum -> {
                        context.startActivity(Intent(context, EthereumLineActivity::class.java))
                    }

                    else -> {}
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return chains.size
    }
}