package wannabit.io.cosmostaion.model.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.protobuf.ByteString
import cosmwasm.wasm.v1.QueryGrpc
import cosmwasm.wasm.v1.QueryOuterClass.QuerySmartContractStateRequest
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import org.json.JSONObject
import wannabit.io.cosmostaion.base.BaseConstant
import wannabit.io.cosmostaion.base.chains.ChainConfig
import wannabit.io.cosmostaion.dao.Account
import wannabit.io.cosmostaion.network.ChannelBuilder
import wannabit.io.cosmostaion.network.req.neutron.*
import wannabit.io.cosmostaion.network.res.neutron.ResPairData
import java.util.concurrent.TimeUnit

class NeutronViewModel : BaseViewModel() {

    private var _pair = MutableLiveData<ResPairData?>()
    val pair: LiveData<ResPairData?> get() = _pair

    private var _data = MutableLiveData<List<String?>>()
    val data: LiveData<List<String?>> get() = _data

    fun loadData(chainConfig: ChainConfig) = backScope.launch {
        val loadData = listOf(async { getPairData(chainConfig) })
        loadData.awaitAll()

        _pair.postValue(loadData[0].await())
    }

    fun loadVaultData(account: Account, chainConfig: ChainConfig, contractAddress: String) = backScope.launch {
        val loadData = listOf(async { getData(ConfigReq(GetConfig()), chainConfig, contractAddress) },
            async { getData(TotalPowerReq(TotalPower()), chainConfig, contractAddress) },
            async { getData(VotingPowerReq(VotingPower(account.address)), chainConfig, contractAddress) })

        _data.postValue(loadData.awaitAll())
    }

    private fun getData(req: Any?, chainConfig: ChainConfig, contractAddress: String): String? {
        try {
            val jsonData = Gson().toJson(req)
            val queryData = ByteString.copyFromUtf8(jsonData)

            val mStub = QueryGrpc.newBlockingStub(ChannelBuilder.getChain(chainConfig.baseChain())).withDeadlineAfter(ChannelBuilder.TIME_OUT.toLong(), TimeUnit.SECONDS)
            val request = QuerySmartContractStateRequest.newBuilder().setAddress(contractAddress).setQueryData(queryData).build()

            mStub.smartContractState(request).apply {
                return this.data.toStringUtf8()
            }
        } catch (_: Exception) { }
        return null
    }

    private fun getPairData(chainConfig: ChainConfig): ResPairData? {
        try {
            val req = PairReq(Pairs())
            val jsonData = Gson().toJson(req)
            val queryData = ByteString.copyFromUtf8(jsonData)

            val mStub = QueryGrpc.newBlockingStub(ChannelBuilder.getChain(chainConfig.baseChain())).withDeadlineAfter(ChannelBuilder.TIME_OUT.toLong(), TimeUnit.SECONDS)
            val request = QuerySmartContractStateRequest.newBuilder().setAddress(BaseConstant.NEUTRON_PAIR_ADDRESS).setQueryData(queryData).build()

            mStub.smartContractState(request).apply {
                return Gson().fromJson(data.toStringUtf8(), ResPairData::class.java)
            }
        } catch (_: Exception) {
            return null
        }
    }
}

data class PairReq(val pairs: Pairs)
class Pairs
