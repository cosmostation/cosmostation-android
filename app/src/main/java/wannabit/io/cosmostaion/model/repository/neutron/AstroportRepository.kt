package wannabit.io.cosmostaion.model.repository.neutron

import com.google.gson.Gson
import com.google.protobuf.ByteString
import cosmwasm.wasm.v1.QueryGrpc
import cosmwasm.wasm.v1.QueryOuterClass
import retrofit2.Response
import retrofit2.awaitResponse
import wannabit.io.cosmostaion.base.chains.ChainConfig
import wannabit.io.cosmostaion.network.ApiClient
import wannabit.io.cosmostaion.network.ChannelBuilder
import wannabit.io.cosmostaion.network.req.neutron.*
import wannabit.io.cosmostaion.network.res.neutron.Pair
import wannabit.io.cosmostaion.network.res.neutron.ResPairData
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class AstroportRepository @Inject constructor() {

    suspend fun getSwapPairData(chainConfig: ChainConfig): Response<ArrayList<ResPairData>> {
        return ApiClient.getMintscan().getSwapPairData(chainConfig.chainName()).awaitResponse()
    }

    fun getSwapRateData(chainConfig: ChainConfig, inputCoin: Pair?, inputAmount: String, outputCoin: Pair?, contractAddress: String?): String? {
        try {
            val req: Any?
            req = if (inputCoin?.type == "cw20") {
                if (outputCoin?.type == "cw20") {
                    SwapRateReq(Simulation(OfferAsset(Info(null, Token(inputCoin.address)), inputAmount), AskAssetInfo(null, Token(outputCoin.address))))
                } else {
                    SwapRateReq(Simulation(OfferAsset(Info(null, Token(inputCoin.address)), inputAmount), AskAssetInfo(NativeToken(outputCoin!!.denom), null)))
                }

            } else {
                if (outputCoin?.type == "cw20") {
                    SwapRateReq(Simulation(OfferAsset(Info(NativeToken(inputCoin!!.denom), null), inputAmount), AskAssetInfo(null, Token(outputCoin.address))))
                } else {
                    SwapRateReq(Simulation(OfferAsset(Info(NativeToken(inputCoin!!.denom), null), inputAmount), AskAssetInfo(NativeToken(outputCoin!!.denom), null)))
                }
            }

            return getData(req, chainConfig, contractAddress)
        } catch (_: Exception) { }
        return null
    }

    fun getData(req: Any?, chainConfig: ChainConfig, contractAddress: String?): String? {
        try {
            val jsonData = Gson().toJson(req)
            val queryData = ByteString.copyFromUtf8(jsonData)

            val mStub = QueryGrpc.newBlockingStub(ChannelBuilder.getChain(chainConfig.baseChain())).withDeadlineAfter(ChannelBuilder.TIME_OUT.toLong(), TimeUnit.SECONDS)
            val request = QueryOuterClass.QuerySmartContractStateRequest.newBuilder().setAddress(contractAddress).setQueryData(queryData).build()

            mStub.smartContractState(request).apply {
                return this.data.toStringUtf8()
            }
        } catch (_: Exception) { }
        return null
    }
}