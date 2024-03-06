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
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.common.formatAmount
import wannabit.io.cosmostaion.data.model.res.Params
import wannabit.io.cosmostaion.databinding.ItemEndpointBinding

class EndPointViewHolder(
    private val binding: ItemEndpointBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(fromChain: CosmosLine?, endpoint: Params.ChainListParams.GrpcEndpoint) {
        binding.apply {
            provider.text = endpoint.provider
            providerUrl.text = endpoint.url

            val checkTime = System.currentTimeMillis()
            val host = endpoint.url.split(":")[0].trim()
            val port = endpoint.url.split(":").getOrNull(1)?.trim()?.toIntOrNull() ?: 443

            CoroutineScope(Dispatchers.IO).launch {
                val channel = getChannel(host, port)
                try {
                    val stub = newBlockingStub(channel)
                    val request = GetNodeInfoRequest.newBuilder().build()
                    val response = stub.getNodeInfo(request)
                    if (response.defaultNodeInfo.network == fromChain?.chainId) {
                        val gapTime = (System.currentTimeMillis() - checkTime) / 1000
                        withContext(Dispatchers.Main) {
                            speedImg.visibility = View.VISIBLE
                            connectTime.visibility = View.VISIBLE
                            if (gapTime <= 1.2) {
                                speedImg.setImageResource(R.drawable.icon_vote_passed)
                            } else if (gapTime <= 3) {
                                speedImg.setImageResource(R.drawable.icon_vote_deposit)
                            } else {
                                speedImg.setImageResource(R.drawable.icon_vote_rejected)
                            }
                            connectTime.text = formatAmount(gapTime.toString(), 4)
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
        }
    }

    fun getChannel(host: String, port: Int): ManagedChannel {
        return ManagedChannelBuilder.forAddress(host, port).useTransportSecurity().build()
    }
}