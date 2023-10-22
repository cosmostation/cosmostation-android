package wannabit.io.cosmostaion.ui.main.setting.chain

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.ChainType
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.chain.cosmosClass.ChainBinanceBeacon
import wannabit.io.cosmostaion.databinding.ItemChainManageBinding

class ChainManageViewHolder(
    val context: Context,
    private val binding: ItemChainManageBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(line: CosmosLine) {
        binding.apply {
            chainView.setBackgroundResource(R.drawable.item_bg)

            when (line.chainType) {
                ChainType.COSMOS_TYPE -> {
                    chainImg.setImageResource(line.logo)
                    chainName.text = line.name.uppercase()

                    if (line is ChainBinanceBeacon) {
                        endpointType.text = "LCD"
                        endpoint.text = line.lcdUrl

                    } else {
                        endpointType.text = "GRPC"
                        endpoint.text = line.grpcHost + " : " + line.grpcPort
                    }
                }

                else -> {}
            }
        }
    }
}