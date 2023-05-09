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
import wannabit.io.cosmostaion.network.req.neutron.TotalPower
import wannabit.io.cosmostaion.network.req.neutron.TotalPowerReq
import wannabit.io.cosmostaion.network.res.neutron.ResVaultData
import java.util.concurrent.TimeUnit

class VaultRepository {

    suspend fun getVaultData(chainConfig: ChainConfig): Response<List<ResVaultData>> {
        return ApiClient.getNeutron().getVaultData(chainConfig.chainName()).awaitResponse()
    }

    fun getVaultDepositData(chainConfig: ChainConfig, contractAddress: String?): String? {
        try {
            val req = TotalPowerReq(TotalPower())
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