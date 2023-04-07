package wannabit.io.cosmostaion.model

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.common.collect.Lists
import com.google.gson.Gson
import com.google.protobuf.ByteString
import cosmwasm.wasm.v1.QueryGrpc.newBlockingStub
import cosmwasm.wasm.v1.QueryOuterClass
import ibc.core.channel.v1.QueryOuterClass.QueryPacketCommitmentsRequest
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import org.json.JSONObject
import retrofit2.awaitResponse
import starnamed.x.starname.v1beta1.QueryGrpc
import starnamed.x.starname.v1beta1.QueryOuterClass.QueryStarnameRequest
import wannabit.io.cosmostaion.base.BaseChain
import wannabit.io.cosmostaion.base.BaseConstant
import wannabit.io.cosmostaion.base.chains.ChainConfig
import wannabit.io.cosmostaion.dao.AssetPath
import wannabit.io.cosmostaion.dao.ICNSInfoReq
import wannabit.io.cosmostaion.dao.NSStargazeInfoReq
import wannabit.io.cosmostaion.dao.NameService
import wannabit.io.cosmostaion.dao.NameService.NameServiceType
import wannabit.io.cosmostaion.network.ApiClient
import wannabit.io.cosmostaion.network.ChannelBuilder
import wannabit.io.cosmostaion.network.ChannelBuilder.TIME_OUT
import wannabit.io.cosmostaion.utils.WUtil
import java.util.concurrent.TimeUnit

class SendViewModel : BaseViewModel() {

    private var _resource = MutableLiveData<ArrayList<NameService>>()
    val resource: LiveData<ArrayList<NameService>> get() = _resource

    fun loadResources(chainConfig: ChainConfig, userInput: String) = backScope.launch {
        try {
            val mStub = QueryGrpc.newBlockingStub(ChannelBuilder.getChain(BaseChain.IOV_MAIN)).withDeadlineAfter(TIME_OUT.toLong(), TimeUnit.SECONDS)
            val request = QueryStarnameRequest.newBuilder().setStarname(userInput).build()

            val matchAddress = WUtil.checkStarnameWithResource(chainConfig, mStub.starname(request).account.resourcesList)
            _resource.postValue(Lists.newArrayList(NameService(NameServiceType.STARNAME, userInput, matchAddress)))
        } catch (_: Exception) { }
    }

    private var _nameServices = MutableLiveData<ArrayList<NameService>>()
    val nameServices: LiveData<ArrayList<NameService>> get() = _nameServices

    fun loadIcnsAddress(chainConfig: ChainConfig, userInput: String) = backScope.launch {
        if (chainConfig.baseChain().equals(BaseChain.STARGAZE_MAIN)) {
            val osIcns = async { getOSIcnsAddress(userInput) }
            val sgIcns = async { getSGIcnsAddress(userInput.substring(0, userInput.indexOf("."))) }

            val icnsAll = awaitAll(osIcns, sgIcns)

            val nameServiceList = ArrayList<NameService>()
            if (icnsAll[0].isNotEmpty()) nameServiceList.add(NameService(NameServiceType.ICNS, userInput, icnsAll[0]))
            if (icnsAll[1].isNotEmpty()) nameServiceList.add(NameService(NameServiceType.STARGAZE, userInput, icnsAll[1]))

            _nameServices.postValue(nameServiceList)
        } else {
            val address = getOSIcnsAddress(userInput)
            _nameServices.postValue(Lists.newArrayList(NameService(NameServiceType.ICNS, userInput, address)))
        }
    }

    private suspend fun getOSIcnsAddress(userInput: String): String {
        try {
            val mStub = newBlockingStub(ChannelBuilder.getChain(BaseChain.OSMOSIS_MAIN)).withDeadlineAfter(TIME_OUT.toLong(), TimeUnit.SECONDS)
            val infoReq = ICNSInfoReq(userInput)
            val queryData = ByteString.copyFromUtf8(Gson().toJson(infoReq))
            val request = QueryOuterClass.QuerySmartContractStateRequest.newBuilder().setAddress(BaseConstant.ICNS_OSMOSIS_ADDRESS).setQueryData(queryData).build()

            mStub.smartContractState(request).apply {
                val json = JSONObject(data.toStringUtf8())
                return json.get("bech32_address").toString()
            }
        } catch (_: Exception) { }
        return ""
    }

    private suspend fun getSGIcnsAddress(userInput: String): String {
        try {
            val mStub = newBlockingStub(ChannelBuilder.getChain(BaseChain.STARGAZE_MAIN)).withDeadlineAfter(TIME_OUT.toLong(), TimeUnit.SECONDS)
            val infoReq = NSStargazeInfoReq(userInput)
            val queryData = ByteString.copyFromUtf8(Gson().toJson(infoReq))
            val request = QueryOuterClass.QuerySmartContractStateRequest.newBuilder().setAddress(BaseConstant.NS_STARGZE_ADDRESS).setQueryData(queryData).build()

            mStub.smartContractState(request).apply {
                return data.toStringUtf8().replace("\"".toRegex(), "")
            }
        } catch (_: Exception) { }
        return ""
    }

    private var _pendingTotal = MutableLiveData<Long>()
    val pendingTotal: LiveData<Long> get() = _pendingTotal

    fun loadPendingTotal(chainConfig: ChainConfig, assetPath: AssetPath) = backScope.launch {
        try {
            val mStub = ibc.core.channel.v1.QueryGrpc.newBlockingStub(ChannelBuilder.getChain(chainConfig.baseChain())).withDeadlineAfter(TIME_OUT.toLong(), TimeUnit.SECONDS)
            val request = QueryPacketCommitmentsRequest.newBuilder().setChannelId(assetPath.channel).setPortId(assetPath.port).build()

            _pendingTotal.postValue(mStub.packetCommitments(request).pagination.total)
        } catch (_: Exception) { }
    }

    private var _lastPing = MutableLiveData<String>()
    val lastPing: LiveData<String> get() = _lastPing

    fun loadLastPing(c: Context, assetPath: AssetPath, chainId: String) = backScope.launch {
        try {
            val response = ApiClient.getMintscan(c).getRelayer(chainId, assetPath.channel).awaitResponse()
            if (response.isSuccessful) {
                response.body()?.let {
                    _lastPing.postValue(it.relayer.last_ping_at)
                }
            }
        } catch (_: Exception) { _lastPing.postValue("") }
    }
}