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
import retrofit2.awaitResponse
import wannabit.io.cosmostaion.base.BaseChain
import wannabit.io.cosmostaion.base.BaseConstant
import wannabit.io.cosmostaion.base.chains.ChainConfig
import wannabit.io.cosmostaion.dao.Account
import wannabit.io.cosmostaion.network.ApiClient
import wannabit.io.cosmostaion.network.ChannelBuilder
import wannabit.io.cosmostaion.network.req.neutron.*
import wannabit.io.cosmostaion.network.req.neutron.Proposal
import wannabit.io.cosmostaion.network.res.neutron.*
import java.util.concurrent.TimeUnit

class NeutronViewModel() : BaseViewModel() {

    private var _pair = MutableLiveData<ResPairData?>()
    val pair: LiveData<ResPairData?> get() = _pair

    private var _neutronData = MutableLiveData<List<ResVaultData?>?>()
    val neutronData: LiveData<List<ResVaultData?>?> get() = _neutronData

    private var _daoData = MutableLiveData<List<ResDaoData?>?>()
    val daoData: LiveData<List<ResDaoData?>?> get() = _daoData

    private var _testData = MutableLiveData<List<Proposals?>>()
    val testData: LiveData<List<Proposals?>> get() = _testData

    private var _proposals = MutableLiveData<MutableMap<String?, List<ProposalData?>>>()
    val proposals: MutableLiveData<MutableMap<String?, List<ProposalData?>>> get() = _proposals

    private var _data = MutableLiveData<List<String?>>()
    val data: LiveData<List<String?>> get() = _data

    fun loadData(chainConfig: ChainConfig) = backScope.launch {
        val loadData = listOf(async { getPairData(chainConfig) })
        loadData.awaitAll()

        _pair.postValue(loadData[0].await())
    }

//    fun loadNeutronVaultData(chainConfig: ChainConfig) = backScope.launch{
//        try {
//            neutronRepo.getNeutronVaultData(chainConfig).let { response ->
//               if (response.isSuccessful) {
//                   _neutronData.postValue(response.body())
//               } else {
//                   _neutronData.postValue(null)
//               }
//            }
//        } catch (_: Exception) { }
//    }

    // vault github
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

        _data.postValue(loadData.awaitAll())
    }

//    fun loadNeutronDaoData(chainConfig: ChainConfig) = backScope.launch {
//        try {
//            neutronRepo.getNeutronDaoData(chainConfig).let { response ->
//                if (response.isSuccessful) {
//                    _daoData.postValue(response.body())
//                } else {
//                    _daoData.postValue(null)
//                }
//            }
//        } catch (_: Exception) { }
//    }

    // dao github
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

    fun loadDaoProposalListData(chainConfig: ChainConfig, contractAddressList: List<String?>) = backScope.launch {
//        val loadData = getData(ProposalListReq(ProposalList()), chainConfig, contractAddress)
//        val proposals = Gson().fromJson(loadData, ResProposalData::class.java)

        val responseList = mutableListOf<Deferred<String?>>()
        contractAddressList.forEach {
            responseList.add(async { getData(ProposalListReq(ProposalList()), chainConfig, it) })
        }
        val testList = mutableListOf<Proposals>()
        responseList.awaitAll().forEachIndexed { index, responseString ->
            val address = contractAddressList[index]
            val proposals = Gson().fromJson(responseString, ResProposalData::class.java)

            testList.add(Proposals(address, proposals))
        }
        _testData.postValue(testList)

//        responseList.awaitAll().forEach {
//            _testData.postValue(Proposals("", it))
//        }
//        val loadData = listOf(async { getData(ProposalListReq(ProposalList()), chainConfig, contractAddress) })
//        val loadData = listOf(async { getData(ProposalListReq(ProposalList()), chainConfig, proposalModules[0]?.address) },
//            async { getData(ProposalListReq(ProposalList()), chainConfig, proposalModules[1]?.address) },
//            async { getData(ProposalListReq(ProposalList()), chainConfig, proposalModules[2]?.address) })
//        WLog.w("test1234 : " + mutableMapOf(contractAddress to proposals.proposals).size)
//        WLog.w("Test1234 : $proposals")
//        _testData.postValue(Proposals(contractAddress, proposals))
//        _proposals.postValue(mutableMapOf(contractAddress to proposals.proposals))
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

    fun loadSubDaoSingleProposalData(chainConfig: ChainConfig, contractAddress: String?) = backScope.launch {

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

data class testReq(val proposals: List<Proposal>)
class Proposal
