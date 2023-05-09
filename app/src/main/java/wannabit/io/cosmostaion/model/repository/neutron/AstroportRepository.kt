package wannabit.io.cosmostaion.model.repository.neutron

import com.google.gson.Gson
import com.google.protobuf.ByteString
import cosmwasm.wasm.v1.QueryGrpc
import cosmwasm.wasm.v1.QueryOuterClass
import wannabit.io.cosmostaion.base.chains.ChainConfig
import wannabit.io.cosmostaion.network.ChannelBuilder
import wannabit.io.cosmostaion.network.req.neutron.Pairs
import wannabit.io.cosmostaion.network.req.neutron.SwapListReq
import java.util.concurrent.TimeUnit

class AstroportRepository {

    fun getSwapPairListData(chainConfig: ChainConfig, contractAddress: String?): String? {
        try {
            val req = SwapListReq(Pairs())
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