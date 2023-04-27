package wannabit.io.cosmostaion.model.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.protobuf.ByteString
import cosmos.base.abci.v1beta1.Abci
import cosmos.tx.v1beta1.ServiceGrpc
import cosmos.tx.v1beta1.ServiceOuterClass
import cosmwasm.wasm.v1.QueryGrpc
import cosmwasm.wasm.v1.QueryOuterClass.QuerySmartContractStateRequest
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import retrofit2.awaitResponse
import wannabit.io.cosmostaion.base.BaseChain
import wannabit.io.cosmostaion.base.BaseConstant
import wannabit.io.cosmostaion.base.chains.ChainConfig
import wannabit.io.cosmostaion.dao.Account
import wannabit.io.cosmostaion.network.ApiClient
import wannabit.io.cosmostaion.network.ChannelBuilder
import wannabit.io.cosmostaion.network.req.neutron.*
import wannabit.io.cosmostaion.network.res.neutron.ResConfigData
import wannabit.io.cosmostaion.network.res.neutron.ResDaoData
import wannabit.io.cosmostaion.network.res.neutron.ResPairData
import java.util.concurrent.TimeUnit

class NeutronViewModel : BaseViewModel() {

    private var _pair = MutableLiveData<ResPairData?>()
    val pair: LiveData<ResPairData?> get() = _pair

    private var _neutronData = MutableLiveData<List<ResConfigData?>?>()
    val neutronData: MutableLiveData<List<ResConfigData?>?> get() = _neutronData

    private var _depositData = MutableLiveData<List<String?>>()
    val depositData: LiveData<List<String?>> get() = _depositData

    private var _daoData = MutableLiveData<List<ResDaoData?>?>()
    val daoData: MutableLiveData<List<ResDaoData?>?> get() = _daoData

    private var _data = MutableLiveData<List<String?>>()
    val data: LiveData<List<String?>> get() = _data

    fun loadData(chainConfig: ChainConfig) = backScope.launch {
        val loadData = listOf(async { getPairData(chainConfig) })
        loadData.awaitAll()

        _pair.postValue(loadData[0].await())
    }

    fun loadNeutronData(chainConfig: ChainConfig) = backScope.launch {
        try {
            val response = ApiClient.getChainBase().getVaultData(chainConfig.chainName()).awaitResponse()

            if (response.isSuccessful) {
                _neutronData.postValue(response.body())
            } else {
                _neutronData.postValue(null)
            }
        } catch (_: Exception) {
            _neutronData.postValue(listOf())
        }
    }

    fun loadNeutronDepositData(chainConfig: ChainConfig, account: Account, contractAddress: String?) = backScope.launch {
        val loadData = listOf(async { getData(TotalPowerReq(TotalPower()), chainConfig, contractAddress) },
            async { getData(VotingPowerReq(VotingPower(account.address)), chainConfig, contractAddress) })

        _depositData.postValue(loadData.awaitAll())
    }

    // github disconnect
    fun loadMainVaultData(account: Account, chainConfig: ChainConfig) = backScope.launch {
        var contractAddress = ""
        if (chainConfig.baseChain().equals(BaseChain.NEUTRON_TEST)) {
            contractAddress = BaseConstant.NEUTRON_NTRN_VAULT_TESTNET_ADDRESS
        }
        val loadData = listOf(async { getData(GetConfigReq(GetConfig()), chainConfig, contractAddress) },
            async { getData(TotalPowerReq(TotalPower()), chainConfig, contractAddress) },
            async { getData(VotingPowerReq(VotingPower(account.address)), chainConfig, contractAddress) })

        _data.postValue(loadData.awaitAll())
    }

    fun loadDaoData(chainConfig: ChainConfig) = backScope.launch {
        try {
            val response = ApiClient.getChainBase().getDaoData(chainConfig.chainName()).awaitResponse()

            if (response.isSuccessful) {
                _daoData.postValue(response.body())
            } else {
                _daoData.postValue(null)
            }
        } catch (_: Exception) {
            _daoData.postValue(listOf())
        }
    }

    fun loadMainDaoData(chainConfig: ChainConfig) = backScope.launch {
        var contractAddress = ""
        if (chainConfig.baseChain().equals(BaseChain.NEUTRON_TEST)) {
            contractAddress = BaseConstant.NEUTRON_NTRN_DAO_TESTNET_ADDRESS
        }
        val loadData = listOf(async { getData(ConfigReq(Config()), chainConfig, contractAddress) })

        _data.postValue(loadData.awaitAll())
    }

    fun loadDaoProposalListData(chainConfig: ChainConfig) = backScope.launch {
        val loadData = listOf(async { getData(ProposalListReq(ProposalList()), chainConfig, BaseConstant.NEUTRON_NTRN_DAO_SINGLE_TESTNET_ADDRESS) },
            async { getData(ProposalListReq(ProposalList()), chainConfig, BaseConstant.NEUTRON_NTRN_DAO_MULTI_TESTNET_ADDRESS) },
            async { getData(ProposalListReq(ProposalList()), chainConfig, BaseConstant.NEUTRON_NTRN_DAO_OVERRULE_TESTNET_ADDRESS) })

        _data.postValue(loadData.awaitAll())
    }

    fun loadDaoSingleProposalData(chainConfig: ChainConfig, proposal_id: Int) = backScope.launch {
        var contractAddress = ""
        if (chainConfig.baseChain().equals(BaseChain.NEUTRON_TEST)) {
            contractAddress = BaseConstant.NEUTRON_NTRN_DAO_SINGLE_TESTNET_ADDRESS
        }
        val loadData = listOf(getData(ProposalDataReq(Proposal(proposal_id)), chainConfig, contractAddress))
        _data.postValue(loadData)
    }

    fun loadDaoMultiProposalData(chainConfig: ChainConfig, proposal_id: Int) = backScope.launch {
        var contractAddress = ""
        if (chainConfig.baseChain().equals(BaseChain.NEUTRON_TEST)) {
            contractAddress = BaseConstant.NEUTRON_NTRN_DAO_MULTI_TESTNET_ADDRESS
        }
        val loadData = listOf(getData(ProposalDataReq(Proposal(proposal_id)), chainConfig, contractAddress))
        _data.postValue(loadData)
    }

    private fun getData(req: Any?, chainConfig: ChainConfig, contractAddress: String?): String? {
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

    private var _txResponse = MutableLiveData<Abci.TxResponse>()
    val txResponse: LiveData<Abci.TxResponse> get() = _txResponse

    fun broadCastTx(baseChain: BaseChain, broadcastTxRequest: ServiceOuterClass.BroadcastTxRequest) = backScope.launch {
        val txService = ServiceGrpc.newBlockingStub(ChannelBuilder.getChain(baseChain))
        val response = txService.broadcastTx(broadcastTxRequest)
        _txResponse.postValue(response.txResponse)
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
