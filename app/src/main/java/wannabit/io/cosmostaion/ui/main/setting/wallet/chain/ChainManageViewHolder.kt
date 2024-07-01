package wannabit.io.cosmostaion.ui.main.setting.wallet.chain

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.chain.EthereumLine
import wannabit.io.cosmostaion.databinding.ItemChainManageBinding

class ChainManageViewHolder(
    private val binding: ItemChainManageBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun evmBind(line: EthereumLine) {
        binding.apply {
            chainView.setBackgroundResource(R.drawable.item_bg)

            chainImg.setImageResource(line.logo)
            chainName.text = line.name.uppercase()

//            if (line.supportCosmos) {
//                grpcLayout.visibility = View.VISIBLE
//                rpcEndpointType.text = "EVM RPC"
//                rpcEndpoint.text = line.getEvmRpc().replace("https://", "")
////                if (line is ChainOktEvm) {
////                    grpcEndpointType.text = "LCD"
////                    grpcEndpoint.text = line.lcdUrl
////                } else {
////                    grpcEndpointType.text = "GRPC"
////                    grpcEndpoint.text = line.getGrpc().first + " : " + line.getGrpc().second
////                }
//
//            } else {
//                grpcLayout.visibility = View.GONE
//                rpcEndpointType.text = "EVM RPC"
//                rpcEndpoint.text = line.getEvmRpc().replace("https://", "")
//            }
        }
    }

    fun bind(line: CosmosLine) {
        binding.apply {
            chainView.setBackgroundResource(R.drawable.item_bg)

            chainImg.setImageResource(line.logo)
            chainName.text = line.name.uppercase()

            grpcLayout.visibility = View.GONE
            rpcEndpointType.text = "GRPC"
            rpcEndpoint.text = line.getGrpc().first + " : " + line.getGrpc().second
        }
    }
}