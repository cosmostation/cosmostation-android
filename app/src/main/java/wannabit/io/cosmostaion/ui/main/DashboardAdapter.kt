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
            word.text = chain.chainName
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