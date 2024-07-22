package wannabit.io.cosmostaion.ui.main.setting.wallet.chain

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.databinding.ItemChainManageBinding

class ChainManageViewHolder(
    private val binding: ItemChainManageBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(chain: BaseChain) {
        binding.apply {
            chainView.setBackgroundResource(R.drawable.item_bg)
            chainImg.setImageResource(chain.logo)
            chainName.text = chain.name.uppercase()

            if (chain.name == "OKT") {
                grpcLayout.visibility = View.VISIBLE
                rpcEndpointType.text = "EVM RPC"
                grpcEndpointType.text = "LCD"
                rpcEndpoint.text = chain.evmRpcFetcher()?.getEvmRpc()?.replace("https://", "")
                grpcEndpoint.text = chain.lcdFetcher()!!.getLcd().replace("https://", "")

            } else if (chain.isEvmCosmos()) {
                grpcLayout.visibility = View.VISIBLE
                rpcEndpointType.text = "EVM RPC"
                grpcEndpointType.text = "GRPC"
                rpcEndpoint.text = chain.evmRpcFetcher()?.getEvmRpc()?.replace("https://", "")
                grpcEndpoint.text =
                    chain.cosmosFetcher()?.getGrpc()?.first + " : " + chain.cosmosFetcher()
                        ?.getGrpc()?.second

            } else if (chain.isCosmos()) {
                grpcLayout.visibility = View.GONE
                rpcEndpointType.text = "GRPC"
                rpcEndpoint.text =
                    chain.cosmosFetcher()?.getGrpc()?.first + " : " + chain.cosmosFetcher()
                        ?.getGrpc()?.second

            } else {
                grpcLayout.visibility = View.GONE
                rpcEndpointType.text = "EVM RPC"
                rpcEndpoint.text = chain.evmRpcFetcher()?.getEvmRpc()?.replace("https://", "")
            }
        }
    }

    fun testnetBind(chain: BaseChain) {
        binding.apply {
            chainView.setBackgroundResource(R.drawable.item_bg)
            chainImg.setImageResource(chain.logo)
            chainName.text = chain.name.uppercase()

            if (chain.isEvmCosmos()) {
                grpcLayout.visibility = View.VISIBLE
                rpcEndpointType.text = "EVM RPC"
                grpcEndpointType.text = "GRPC"
                rpcEndpoint.text = chain.evmRpcFetcher()?.getEvmRpc()?.replace("https://", "")
                grpcEndpoint.text =
                    chain.cosmosFetcher()?.getGrpc()?.first + " : " + chain.cosmosFetcher()
                        ?.getGrpc()?.second

            } else {
                grpcLayout.visibility = View.GONE
                rpcEndpointType.text = "GRPC"
                rpcEndpoint.text =
                    chain.cosmosFetcher()?.getGrpc()?.first + " : " + chain.cosmosFetcher()
                        ?.getGrpc()?.second
            }
        }
    }
}