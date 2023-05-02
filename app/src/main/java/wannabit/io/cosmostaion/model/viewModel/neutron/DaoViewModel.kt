package wannabit.io.cosmostaion.model.viewModel.neutron

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import wannabit.io.cosmostaion.base.chains.ChainConfig
import wannabit.io.cosmostaion.model.repository.neutron.DaoRepository
import wannabit.io.cosmostaion.model.viewModel.BaseViewModel
import wannabit.io.cosmostaion.network.req.neutron.ProposalList
import wannabit.io.cosmostaion.network.req.neutron.ProposalListReq
import wannabit.io.cosmostaion.network.res.neutron.ProposalData
import wannabit.io.cosmostaion.network.res.neutron.ResDaoData
import wannabit.io.cosmostaion.network.res.neutron.ResProposalData

class DaoViewModel(private val daoRepository: DaoRepository) : BaseViewModel() {

    private var _daoListData = MutableLiveData<List<ResDaoData?>?>()
    val daoListData: LiveData<List<ResDaoData?>?> get() = _daoListData

//    private var _daoProposalListData = MutableLiveData<List<Proposals?>>()
//    val daoProposalListData: LiveData<List<Proposals?>> get() = _daoProposalListData

    private var _daoProposalListData = MutableLiveData<MutableList<Pair<String?, ProposalData?>>>()
    val daoProposalListData: LiveData<MutableList<Pair<String?, ProposalData?>>> get() = _daoProposalListData

    fun loadDaoListData(chainConfig: ChainConfig) = backScope.launch {
        try {
            daoRepository.getDaoData(chainConfig).let { response ->
                if (response.isSuccessful) {
                    _daoListData.postValue(response.body())
                } else {
                    _daoListData.postValue(null)
                }
            }
        } catch (_: Exception) { }
    }

    fun loadDaoProposalListData(chainConfig: ChainConfig, contractAddressList: MutableList<String?>) = backScope.launch {
        val responseList = mutableListOf<Deferred<String?>>()

        val proposalMap = contractAddressList.flatMap { address ->
            val response = async { getData(ProposalListReq(ProposalList()), chainConfig, address) }.await()
            Gson().fromJson(response, ResProposalData::class.java).proposals.map { Pair(address, it) }
        }

        contractAddressList.forEach {
            responseList.add(async { getData(ProposalListReq(ProposalList()), chainConfig, it) })
        }

        val proposalPair = mutableListOf<Pair<String?, ProposalData?>>()
        responseList.awaitAll().forEachIndexed { index, responseString ->

            val address = contractAddressList[index]
            val proposals = Gson().fromJson(responseString, ResProposalData::class.java)

            proposals.proposals.forEach { proposalData ->
                proposalPair.add(Pair(address, proposalData))
            }

//            proposalPair.add(Pair(address, proposals))
//            proposalList.add(Proposals(address, proposals))
        }
        _daoProposalListData.postValue(proposalPair)
//        _daoProposalListData.postValue(proposalPair)
//        _daoProposalListData.postValue(proposalList)
    }
}