package wannabit.io.cosmostaion.ui.main.setting.wallet.chain

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.cosmos.base.tendermint.v1beta1.QueryProto.GetNodeInfoRequest
import com.cosmos.base.tendermint.v1beta1.ServiceGrpc.newBlockingStub
import io.grpc.ManagedChannel
import io.grpc.ManagedChannelBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.web3j.protocol.Web3j
import org.web3j.protocol.http.HttpService
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.chain.EthereumLine
import wannabit.io.cosmostaion.common.formatAmount
import wannabit.io.cosmostaion.common.goneOrVisible
import wannabit.io.cosmostaion.data.model.res.Params
import wannabit.io.cosmostaion.databinding.ItemEndpointBinding
import wannabit.io.cosmostaion.ui.main.setting.SettingBottomAdapter

class EndPointViewHolder(
    private val binding: ItemEndpointBinding
) : RecyclerView.ViewHolder(binding.root) {

    private var gapTime: Double? = null

    fun evmBind(
        fromChain: EthereumLine?,
        endpoint: Params.ChainListParams.GrpcEndpoint,
        listener: SettingBottomAdapter.EndpointListener?
    ) {
        binding.apply {
            provider.text = endpoint.provider
            providerUrl.text = endpoint.url.replace("https://", "")

            val checkTime = System.currentTimeMillis() / 1000.0
            val url = endpoint.url
            checkImg.goneOrVisible(fromChain?.getEvmRpc() != url)

            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val web3j = Web3j.build(HttpService(url))
                    web3j.web3ClientVersion()?.sendAsync()?.get()?.web3ClientVersion
                    gapTime = (System.currentTimeMillis() / 1000.0 - checkTime)
                    withContext(Dispatchers.Main) {
                        speedImg.visibility = View.VISIBLE
                        connectTime.visibility = View.VISIBLE
                        gapTime?.let {
                            if (it <= 1.2) {
                                speedImg.setImageResource(R.drawable.icon_vote_passed)
                            } else if (it <= 3) {
                                speedImg.setImageResource(R.drawable.icon_vote_deposit)
                            } else {
                                speedImg.setImageResource(R.drawable.icon_vote_rejected)
                            }
                            connectTime.text = formatAmount(it.toString(), 4)
                        }
                    }

                } catch (e: Exception) {
                    withContext(Dispatchers.Main) {
                        speedImg.visibility = View.VISIBLE
                        connectTime.visibility = View.VISIBLE
                        speedImg.setImageResource(R.drawable.icon_vote_rejected)
                        connectTime.text = "Unknown"
                    }
                }
            }

            endpointView.setOnClickListener {
                listener?.rpcSelect(endpoint.url, gapTime)
            }
        }
    }

    fun bind(
        fromChain: CosmosLine?,
        endpoint: Params.ChainListParams.GrpcEndpoint,
        listener: SettingBottomAdapter.EndpointListener?
    ) {
        binding.apply {
            provider.text = endpoint.provider
            providerUrl.text = endpoint.url

            val checkTime = System.currentTimeMillis() / 1000.0
            val host = endpoint.url.split(":")[0].trim()
            val port = endpoint.url.split(":").getOrNull(1)?.trim()?.toIntOrNull() ?: 443
            checkImg.goneOrVisible(fromChain?.getGrpc()?.first != host)

            CoroutineScope(Dispatchers.IO).launch {
                val channel = getChannel(host, port)
                try {
                    val stub = newBlockingStub(channel)
                    val request = GetNodeInfoRequest.newBuilder().build()
                    val response = stub.getNodeInfo(request)

                    if (response.defaultNodeInfo.network == fromChain?.chainId) {
                        gapTime = (System.currentTimeMillis() / 1000.0 - checkTime)
                        withContext(Dispatchers.Main) {
                            speedImg.visibility = View.VISIBLE
                            connectTime.visibility = View.VISIBLE
                            gapTime?.let {
                                if (it <= 1.2) {
                                    speedImg.setImageResource(R.drawable.icon_vote_passed)
                                } else if (it <= 3) {
                                    speedImg.setImageResource(R.drawable.icon_vote_deposit)
                                } else {
                                    speedImg.setImageResource(R.drawable.icon_vote_rejected)
                                }
                                connectTime.text = formatAmount(it.toString(), 4)
                            }
                        }

                    } else {
                        channel.shutdown()
                        withContext(Dispatchers.Main) {
                            speedImg.visibility = View.VISIBLE
                            connectTime.visibility = View.VISIBLE
                            speedImg.setImageResource(R.drawable.icon_vote_rejected)
                            connectTime.text = "ChainID failed"
                        }
                    }

                } catch (e: Exception) {
                    channel.shutdown()
                    withContext(Dispatchers.Main) {
                        speedImg.visibility = View.VISIBLE
                        connectTime.visibility = View.VISIBLE
                        speedImg.setImageResource(R.drawable.icon_vote_rejected)
                        connectTime.text = "Unknown"
                    }
                }
            }

            endpointView.setOnClickListener {
                listener?.select(endpoint.url, gapTime)
            }
        }
    }

    fun getChannel(host: String, port: Int): ManagedChannel {
        return ManagedChannelBuilder.forAddress(host, port).useTransportSecurity().build()
    }
}