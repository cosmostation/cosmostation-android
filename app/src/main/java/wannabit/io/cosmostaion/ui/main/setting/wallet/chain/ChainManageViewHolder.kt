package wannabit.io.cosmostaion.ui.main.setting.wallet.chain

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.CosmosEndPointType
import wannabit.io.cosmostaion.chain.majorClass.ChainBitCoin86
import wannabit.io.cosmostaion.chain.majorClass.ChainIota
import wannabit.io.cosmostaion.chain.majorClass.ChainSui
import wannabit.io.cosmostaion.chain.testnetClass.ChainGnoTestnet
import wannabit.io.cosmostaion.common.setChainLogo
import wannabit.io.cosmostaion.databinding.ItemChainManageBinding

class ChainManageViewHolder(
    private val binding: ItemChainManageBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(chain: BaseChain) {
        binding.apply {
            chainView.setBackgroundResource(R.drawable.item_bg)
            chainImg.setChainLogo(chain)

            when (chain) {
                is ChainBitCoin86 -> {
                    restLayout.visibility = View.VISIBLE
                    evmLayout.visibility = View.GONE

                    chainName.text = chain.name
                    restEndpoint.text = chain.btcFetcher()?.mempoolUrl()?.replace("https://", "")
                    restEndpointType.text = "API"
                }

                is ChainSui -> {
                    restLayout.visibility = View.VISIBLE
                    evmLayout.visibility = View.GONE

                    chainName.text = chain.name
                    restEndpoint.text = chain.suiFetcher()?.suiRpc()?.replace("https://", "")
                    restEndpointType.text = "SUI RPC"
                }

                is ChainIota -> {
                    restLayout.visibility = View.VISIBLE
                    evmLayout.visibility = View.GONE

                    chainName.text = chain.name
                    restEndpoint.text = chain.iotaFetcher()?.iotaRpc()?.replace("https://", "")
                    restEndpointType.text = "IOTA RPC"
                }

                else -> {
                    if (chain.cosmosFetcher()?.endPointType(chain) == CosmosEndPointType.USE_GRPC) {
                        if (chain.isEvmCosmos()) {
                            restLayout.visibility = View.GONE
                            evmLayout.visibility = View.VISIBLE

                            evmChainName.text = chain.name
                            rpcEndpoint.text = chain.cosmosFetcher()
                                ?.getGrpc()?.first + " : " + chain.cosmosFetcher()
                                ?.getGrpc()?.second
                            rpcEndpointType.text = "GRPC"
                            evmRpcEndpoint.text =
                                chain.evmRpcFetcher()?.getEvmRpc()?.replace("https://", "")
                            evmRpcEndpointType.text = "EVM RPC"

                        } else {
                            restLayout.visibility = View.VISIBLE
                            evmLayout.visibility = View.GONE

                            chainName.text = chain.name
                            restEndpoint.text = chain.cosmosFetcher()
                                ?.getGrpc()?.first + " : " + chain.cosmosFetcher()
                                ?.getGrpc()?.second
                            restEndpointType.text = "GRPC"
                        }

                    } else if (chain.cosmosFetcher()
                            ?.endPointType(chain) == CosmosEndPointType.USE_LCD
                    ) {
                        if (chain.isEvmCosmos()) {
                            restLayout.visibility = View.GONE
                            evmLayout.visibility = View.VISIBLE

                            evmChainName.text = chain.name
                            rpcEndpoint.text =
                                chain.cosmosFetcher()?.getLcd()?.replace("https://", "")
                            rpcEndpointType.text = "REST"
                            evmRpcEndpoint.text =
                                chain.evmRpcFetcher()?.getEvmRpc()?.replace("https://", "")
                            evmRpcEndpointType.text = "EVM RPC"

                        } else {
                            restLayout.visibility = View.VISIBLE
                            evmLayout.visibility = View.GONE

                            chainName.text = chain.name
                            restEndpoint.text =
                                chain.cosmosFetcher()?.getLcd()?.replace("https://", "")
                            restEndpointType.text = "REST"
                        }

                    } else {
                        restLayout.visibility = View.VISIBLE
                        evmLayout.visibility = View.GONE

                        chainName.text = chain.name
                        restEndpoint.text =
                            chain.evmRpcFetcher()?.getEvmRpc()?.replace("https://", "")
                        restEndpointType.text = "EVM RPC"
                    }
                }
            }
        }
    }

    fun testnetBind(chain: BaseChain) {
        binding.apply {
            chainView.setBackgroundResource(R.drawable.item_bg)
            chainImg.setChainLogo(chain)

            when (chain) {
                is ChainBitCoin86 -> {
                    restLayout.visibility = View.VISIBLE
                    evmLayout.visibility = View.GONE

                    chainName.text = chain.name
                    restEndpoint.text = chain.btcFetcher()?.mempoolUrl()?.replace("https://", "")
                    restEndpointType.text = "API"
                }

                is ChainGnoTestnet -> {
                    restLayout.visibility = View.VISIBLE
                    evmLayout.visibility = View.GONE

                    chainName.text = chain.name
                    restEndpoint.text = chain.gnoRpcFetcher()?.gnoRpc()?.replace("https://", "")
                    restEndpointType.text = "RPC"
                }

                else -> {
                    if (chain.cosmosFetcher()?.endPointType(chain) == CosmosEndPointType.USE_GRPC) {
                        if (chain.isEvmCosmos()) {
                            restLayout.visibility = View.GONE
                            evmLayout.visibility = View.VISIBLE

                            evmChainName.text = chain.name
                            rpcEndpoint.text = chain.cosmosFetcher()
                                ?.getGrpc()?.first + " : " + chain.cosmosFetcher()
                                ?.getGrpc()?.second
                            rpcEndpointType.text = "GRPC"
                            evmRpcEndpoint.text =
                                chain.evmRpcFetcher()?.getEvmRpc()?.replace("https://", "")
                            evmRpcEndpointType.text = "EVM RPC"

                        } else {
                            restLayout.visibility = View.VISIBLE
                            evmLayout.visibility = View.GONE

                            chainName.text = chain.name
                            restEndpoint.text = chain.cosmosFetcher()
                                ?.getGrpc()?.first + " : " + chain.cosmosFetcher()
                                ?.getGrpc()?.second
                            restEndpointType.text = "GRPC"
                        }

                    } else if (chain.cosmosFetcher()
                            ?.endPointType(chain) == CosmosEndPointType.USE_LCD
                    ) {
                        if (chain.isEvmCosmos()) {
                            restLayout.visibility = View.GONE
                            evmLayout.visibility = View.VISIBLE

                            evmChainName.text = chain.name
                            rpcEndpoint.text =
                                chain.cosmosFetcher()?.getLcd()?.replace("https://", "")
                            rpcEndpointType.text = "REST"
                            evmRpcEndpoint.text =
                                chain.evmRpcFetcher()?.getEvmRpc()?.replace("https://", "")
                            evmRpcEndpointType.text = "EVM RPC"

                        } else {
                            restLayout.visibility = View.VISIBLE
                            evmLayout.visibility = View.GONE

                            chainName.text = chain.name
                            restEndpoint.text =
                                chain.cosmosFetcher()?.getLcd()?.replace("https://", "")
                            restEndpointType.text = "REST"
                        }

                    } else {
                        restLayout.visibility = View.VISIBLE
                        evmLayout.visibility = View.GONE

                        chainName.text = chain.name
                        restEndpoint.text =
                            chain.evmRpcFetcher()?.getEvmRpc()?.replace("https://", "")
                        restEndpointType.text = "EVM RPC"
                    }
                }
            }
        }
    }
}