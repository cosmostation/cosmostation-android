package wannabit.io.cosmostaion.ui.main.setting.wallet.chain

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.CosmosEndPointType
import wannabit.io.cosmostaion.chain.majorClass.ChainBitCoin84
import wannabit.io.cosmostaion.chain.majorClass.ChainSui
import wannabit.io.cosmostaion.databinding.ItemChainManageBinding

class ChainManageViewHolder(
    private val binding: ItemChainManageBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(chain: BaseChain) {
        binding.apply {
            chainView.setBackgroundResource(R.drawable.item_bg)
            chainImg.setImageResource(chain.logo)
            chainName.text = chain.name.uppercase()

            if (chain is ChainBitCoin84) {
                grpcLayout.visibility = View.GONE
                rpcEndpointType.text = "API"
                rpcEndpoint.text = chain.btcFetcher()?.mempoolUrl()?.replace("https://", "")

            } else if (chain is ChainSui) {
                grpcLayout.visibility = View.GONE
                rpcEndpointType.text = "RPC"
                rpcEndpoint.text = chain.suiFetcher()?.suiRpc()?.replace("https://", "")

            } else if (chain.cosmosFetcher()?.endPointType(chain) == CosmosEndPointType.USE_GRPC) {
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

            } else if (chain.cosmosFetcher()?.endPointType(chain) == CosmosEndPointType.USE_LCD) {
                if (chain.isEvmCosmos()) {
                    grpcLayout.visibility = View.VISIBLE
                    rpcEndpointType.text = "EVM RPC"
                    grpcEndpointType.text = "REST"
                    rpcEndpoint.text = chain.evmRpcFetcher()?.getEvmRpc()?.replace("https://", "")
                    grpcEndpoint.text = chain.cosmosFetcher()?.getLcd()?.replace("https://", "")
                } else {
                    grpcLayout.visibility = View.GONE
                    rpcEndpointType.text = "Rest"
                    rpcEndpoint.text = chain.cosmosFetcher()?.getLcd()?.replace("https://", "")
                }

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

            if (chain is ChainBitCoin84) {
                grpcLayout.visibility = View.GONE
                rpcEndpointType.text = "API"
                rpcEndpoint.text = chain.btcFetcher()?.mempoolUrl()?.replace("https://", "")

            } else if (chain.cosmosFetcher()?.endPointType(chain) == CosmosEndPointType.USE_GRPC) {
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

            } else {
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
}