package wannabit.io.cosmostaion.model.viewModel.neutron

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import wannabit.io.cosmostaion.base.chains.ChainConfig
import wannabit.io.cosmostaion.dao.Account
import wannabit.io.cosmostaion.model.NetworkResult
import wannabit.io.cosmostaion.model.repository.neutron.DaoRepository
import wannabit.io.cosmostaion.model.viewModel.BaseViewModel
import wannabit.io.cosmostaion.network.res.neutron.*
import javax.inject.Inject
import kotlin.Pair

@HiltViewModel
class DaoViewModel @Inject constructor(private val daoRepository: DaoRepository) : BaseViewModel() {

    private var _daoListData = MutableLiveData<NetworkResult<List<ResDaoData?>?>>()
    val daoListData: LiveData<NetworkResult<List<ResDaoData?>?>> get() = _daoListData

    fun loadDaoListData(chainConfig: ChainConfig) = backScope.launch {
        try {
            daoRepository.getDaoData(chainConfig).let { response ->
                if (response.isSuccessful) {
                    _daoListData.postValue(NetworkResult.Success(response.body()))
                } else {
                    _daoListData.postValue(NetworkResult.Error("Error"))
                }
            }
        } catch (e: Exception) {
            _daoListData.postValue(NetworkResult.Error(e.message, e.cause))
        }
    }

    private var _daoProposalListData = MutableLiveData<NetworkResult<List<Pair<String?, ProposalData?>>>>()
    val daoProposalListData: LiveData<NetworkResult<List<Pair<String?, ProposalData?>>>> get() = _daoProposalListData

    fun loadDaoProposalListData(chainConfig: ChainConfig, contractAddressList: MutableList<String?>) = backScope.launch {
        try {
            val proposalMap = contractAddressList.flatMap { address ->
                val response = daoRepository.getDaoProposalListData(chainConfig, address)
                Gson().fromJson(response, ResProposalData::class.java).proposals.map { Pair(address, it) }
            }
            _daoProposalListData.postValue(NetworkResult.Success(proposalMap))
        } catch (e: Exception) {
            _daoProposalListData.postValue(NetworkResult.Error(e.message, e.cause))
        }
    }

    private var _daoMyVoteStatusData = MutableLiveData<NetworkResult<List<ResMyVoteStatus>?>>()
    val daoMyVoteStatusData: LiveData<NetworkResult<List<ResMyVoteStatus>?>> get() = _daoMyVoteStatusData

    fun loadDaoProposalMyVoteData(chainConfig: ChainConfig, account: Account) = backScope.launch {
        try {
            daoRepository.getMyVoteStatus(chainConfig, account).let { response ->
                if (response.isSuccessful) {
                    _daoMyVoteStatusData.postValue(NetworkResult.Success(response.body()))
                } else {
                    _daoMyVoteStatusData.postValue(NetworkResult.Error("Error"))
                }
            }
        } catch (e: Exception) {
            _daoMyVoteStatusData.postValue(NetworkResult.Error(e.message, e.cause))
        }
    }

    private var _daoMemberStatus = MutableLiveData<NetworkResult<ResMemberList?>>()
    val daoMemberStatus: LiveData<NetworkResult<ResMemberList?>> get() = _daoMemberStatus

    fun loadMemberList(chainConfig: ChainConfig, contractAddress: String?) =  backScope.launch {
        try {
            daoRepository.getListMemberData(chainConfig, contractAddress)?.let { response ->
                if (response.isNotEmpty()) {
                    _daoMemberStatus.postValue(NetworkResult.Success(Gson().fromJson(response, ResMemberList::class.java)))
                } else {
                    _daoMemberStatus.postValue(NetworkResult.Error("Error"))
                }
            }
        } catch (e: Exception) {
            _daoMemberStatus.postValue(NetworkResult.Error(e.message, e.cause))
        }
    }
}