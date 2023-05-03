package wannabit.io.cosmostaion.model.viewModel.neutron

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import kotlinx.coroutines.launch
import wannabit.io.cosmostaion.base.chains.ChainConfig
import wannabit.io.cosmostaion.model.repository.neutron.DaoRepository
import wannabit.io.cosmostaion.model.viewModel.BaseViewModel
import wannabit.io.cosmostaion.network.res.neutron.ProposalData
import wannabit.io.cosmostaion.network.res.neutron.ResDaoData
import wannabit.io.cosmostaion.network.res.neutron.ResProposalData
import wannabit.io.cosmostaion.utils.WLog

class DaoViewModel(private val daoRepository: DaoRepository) : BaseViewModel() {

    private var _daoListData = MutableLiveData<List<ResDaoData?>?>()
    val daoListData: LiveData<List<ResDaoData?>?> get() = _daoListData

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

    private var _daoProposalListData = MutableLiveData<List<Pair<String?, ProposalData?>>>()
    val daoProposalListData: LiveData<List<Pair<String?, ProposalData?>>> get() = _daoProposalListData

    fun loadDaoProposalListData(chainConfig: ChainConfig, contractAddressList: MutableList<String?>) = backScope.launch {
        try {
            val proposalMap = contractAddressList.flatMap { address ->
                val response = daoRepository.getDaoProposalListData(chainConfig, address)
                    Gson().fromJson(response, ResProposalData::class.java).proposals.map { Pair(address, it) }
            }
            _daoProposalListData.postValue(proposalMap)
        } catch (_: Exception) { }
    }

    fun loadDaoProposalMyVoteData(chainConfig: ChainConfig, contractAddressList: MutableList<String?>) = backScope.launch {
        try {

        } catch (_: Exception) { }
    }

    private var _daoProposalData = MutableLiveData<String?>()
    val daoProposalData: LiveData<String?> get() = _daoProposalData

    fun loadDaoProposalData(chainConfig: ChainConfig, contractAddress: String, proposalId: Int) = backScope.launch {
        try {
            daoRepository.getDaoProposalData(chainConfig, contractAddress, proposalId).let { response ->
                response?.let {
                    WLog.w("Test1234 : $it")
                    _daoProposalData.postValue(response)
                }
            }
        } catch (_: Exception) { }
    }
}