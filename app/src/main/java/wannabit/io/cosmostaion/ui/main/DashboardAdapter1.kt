package wannabit.io.cosmostaion.ui.main

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.chain.EthereumLine
import wannabit.io.cosmostaion.chain.Line
import wannabit.io.cosmostaion.databinding.ItemDashboardBinding
import wannabit.io.cosmostaion.ui.line.cosmos.CosmosLineActivity
import wannabit.io.cosmostaion.ui.line.ethereum.EthereumLineActivity

class DashboardAdapter1(private val context: Context, val lines: MutableList<Line> = mutableListOf()) : RecyclerView.Adapter<DashboardViewHolder1>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashboardViewHolder1 {
        val binding = ItemDashboardBinding.inflate(LayoutInflater.from(context), parent, false)
        return DashboardViewHolder1(binding)
    }

    override fun onBindViewHolder(holder: DashboardViewHolder1, position: Int) {
        val chain = lines[position]
        holder.binding.apply {
            name.text = chain.chainName
            ApplicationViewModel.shared.pricesLiveData.value?.let {
                it.find {
                    when (chain) {
                        is CosmosLine -> {
                            it.coinGeckoId.lowercase() == chain.config.chainName.lowercase()
                        }

                        is EthereumLine -> {
                            it.coinGeckoId.lowercase() == chain.config.chainName.lowercase()
                        }

                        else -> {
                            false
                        }
                    }
                }?.let { price.text = "${it.current_price}" }
            }
            ApplicationViewModel.shared.balancesLiveData.value?.let {
                it.find {
                    when (chain) {
                        is CosmosLine -> {
                            it.denom.lowercase() == chain.config.baseDenom.lowercase()
                        }

                        is EthereumLine -> {
                            it.denom.lowercase() == chain.config.displayDenom.lowercase()
                        }

                        else -> {
                            false
                        }
                    }
                }?.let { amount.text = it.amount }
            }
            root.setOnClickListener {
                when (chain) {
                    is CosmosLine -> {
                        context.startActivity(Intent(context, CosmosLineActivity::class.java).putExtra("chain", chain))
                    }

                    is EthereumLine -> {
                        context.startActivity(Intent(context, EthereumLineActivity::class.java).putExtra("chain", chain))
                    }

                    else -> {}
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return lines.size
    }
}