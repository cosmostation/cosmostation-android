package wannabit.io.cosmostaion.ui.main.setting.wallet.chain

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.cosmos.base.tendermint.v1beta1.QueryProto.GetNodeInfoRequest
import com.cosmos.base.tendermint.v1beta1.ServiceGrpc.newBlockingStub
import com.google.gson.Gson
import com.google.gson.JsonObject
import io.grpc.ManagedChannel
import io.grpc.ManagedChannelBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import org.web3j.protocol.Web3j
import org.web3j.protocol.http.HttpService
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.majorClass.ChainSui
import wannabit.io.cosmostaion.chain.CosmosEndPointType
import wannabit.io.cosmostaion.common.formatAmount
import wannabit.io.cosmostaion.common.goneOrVisible
import wannabit.io.cosmostaion.common.jsonRpcResponse
import wannabit.io.cosmostaion.common.visibleOrGone
import wannabit.io.cosmostaion.data.model.req.JsonRpcRequest
import wannabit.io.cosmostaion.databinding.ItemEndpointBinding
import wannabit.io.cosmostaion.ui.main.setting.SettingBottomAdapter
import java.io.IOException

class EndPointViewHolder(
    private val binding: ItemEndpointBinding
) : RecyclerView.ViewHolder(binding.root) {

    private var gapTime: Double? = null

    fun evmBind(
        fromChain: BaseChain?,
        endpoint: JsonObject,
        listener: SettingBottomAdapter.EndpointListener?
    ) {
        binding.apply {
            provider.text = endpoint.get("provider").asString
            providerUrl.text = endpoint.get("url").asString.replace("https://", "")

            val checkTime = System.currentTimeMillis() / 1000.0
            val url = endpoint.get("url").asString
            checkImg.goneOrVisible(fromChain?.evmRpcFetcher()?.getEvmRpc() != url)

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
                listener?.rpcSelect(endpoint.get("url").asString, gapTime)
            }
        }
    }

    fun bind(
        fromChain: BaseChain?,
        endpoint: JsonObject,
        listener: SettingBottomAdapter.EndpointListener?
    ) {
        binding.apply {
            provider.text = endpoint.get("provider").asString
            providerUrl.text = endpoint.get("url").asString

            val checkTime = System.currentTimeMillis() / 1000.0
            val host = endpoint.get("url").asString.split(":")[0].trim()
            val port =
                endpoint.get("url").asString.split(":").getOrNull(1)?.trim()?.toIntOrNull() ?: 443
            checkImg.visibleOrGone(
                fromChain?.cosmosFetcher()
                    ?.endPointType(fromChain) == CosmosEndPointType.USE_GRPC &&
                        fromChain.cosmosFetcher()?.getGrpc()?.first == host
            )

            CoroutineScope(Dispatchers.IO).launch {
                val channel = getChannel(host, port)
                try {
                    val stub = newBlockingStub(channel)
                    val request = GetNodeInfoRequest.newBuilder().build()
                    val response = stub.getNodeInfo(request)

                    if (response.defaultNodeInfo.network == fromChain?.chainIdCosmos) {
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
                listener?.select(endpoint.get("url").asString, gapTime)
            }
        }
    }

    fun lcdBind(
        fromChain: BaseChain?,
        endpoint: JsonObject,
        listener: SettingBottomAdapter.EndpointListener?
    ) {
        binding.apply {
            fromChain?.let { chain ->
                provider.text = endpoint.get("provider").asString
                providerUrl.text = endpoint.get("url").asString

                val checkTime = System.currentTimeMillis() / 1000.0
                var url = endpoint.get("url").asString
                if (!url.endsWith("/")) {
                    url += "/"
                }
                url += if (chain.name.startsWith("G-Bridge") || chain.name.startsWith("Sentinel")) {
                    "node_info"
                } else {
                    "cosmos/base/tendermint/v1beta1/node_info"
                }
                checkImg.visibleOrGone(
                    chain.cosmosFetcher?.endPointType(chain) == CosmosEndPointType.USE_LCD && chain.cosmosFetcher?.getLcd()
                        ?.contains(endpoint["url"].asString) == true
                )

                CoroutineScope(Dispatchers.IO).launch {
                    try {
                        val request = Request.Builder().url(url).build()
                        OkHttpClient().newCall(request).execute().use { response ->
                            if (!response.isSuccessful) throw IOException("Unexpected code $response")
                            val body = response.body?.string()
                            val json = Gson().fromJson(body, JsonObject::class.java)

                            val network = try {
                                json["default_node_info"].asJsonObject["network"].asString
                            } catch (e: Exception) {
                                json["node_info"].asJsonObject["network"].asString
                            }

                            if (network == chain.chainIdCosmos) {
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
                                speedImg.setImageResource(R.drawable.icon_vote_rejected)
                                connectTime.text = "ChainID Failed"
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
                    listener?.lcdSelect(endpoint.get("url").asString, gapTime)
                }
            }
        }
    }

    fun suiBind(
        fromChain: BaseChain?,
        endpoint: JsonObject,
        listener: SettingBottomAdapter.EndpointListener?
    ) {
        binding.apply {
            (fromChain as ChainSui).suiFetcher()?.let { fetcher ->
                provider.text = endpoint.get("provider").asString
                providerUrl.text = endpoint.get("url").asString.replace("https://", "")

                val checkTime = System.currentTimeMillis() / 1000.0
                val url = endpoint.get("url").asString
                checkImg.goneOrVisible(fetcher.suiRpc() != url)

                CoroutineScope(Dispatchers.IO).launch {
                    try {
                        val suiChainIdRequest = JsonRpcRequest(
                            method = "sui_getChainIdentifier", params = listOf()
                        )
                        val suiChainIdResponse = jsonRpcResponse(fetcher.suiRpc(), suiChainIdRequest)
                        if (suiChainIdResponse.isSuccessful) {
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
                            withContext(Dispatchers.Main) {
                                speedImg.visibility = View.VISIBLE
                                connectTime.visibility = View.VISIBLE
                                speedImg.setImageResource(R.drawable.icon_vote_rejected)
                                connectTime.text = "Unknown"
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
                    listener?.rpcSelect(endpoint.get("url").asString, gapTime)
                }
            }
        }
    }

    fun getChannel(host: String, port: Int): ManagedChannel {
        return ManagedChannelBuilder.forAddress(host, port).useTransportSecurity().build()
    }
}