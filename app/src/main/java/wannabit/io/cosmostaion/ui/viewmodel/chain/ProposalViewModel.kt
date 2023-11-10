package wannabit.io.cosmostaion.ui.viewmodel.chain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import io.grpc.ManagedChannel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import wannabit.io.cosmostaion.data.model.res.CosmosProposal
import wannabit.io.cosmostaion.data.model.res.NetworkResult
import wannabit.io.cosmostaion.data.model.res.ProposalData
import wannabit.io.cosmostaion.data.model.res.ResDaoVoteStatus
import wannabit.io.cosmostaion.data.model.res.ResProposalData
import wannabit.io.cosmostaion.data.model.res.VoteStatus
import wannabit.io.cosmostaion.data.repository.chain.ProposalRepository

class ProposalViewModel(private val proposalRepository: ProposalRepository): ViewModel() {

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    private var _proposalResult = MutableLiveData<MutableList<CosmosProposal>?>()
    val proposalResult: LiveData<MutableList<CosmosProposal>?> get() = _proposalResult

    fun proposalList(chain: String) = CoroutineScope(Dispatchers.IO).launch {
        when (val response = proposalRepository.cosmosProposal(chain)) {
            is NetworkResult.Success -> {
                response.data.let { data ->
                    if (data.isSuccessful) {
                        _proposalResult.postValue(data.body())
                    } else {
                        _errorMessage.postValue("Error")
                    }
                }
            }

            is NetworkResult.Error -> {
                _errorMessage.postValue("error type : ${response.errorType}  error message : ${response.errorMessage}")
            }
        }
    }

    private var _voteStatusResult = MutableLiveData<VoteStatus?>()
    val voteStatusResult: LiveData<VoteStatus?> get() = _voteStatusResult

    fun voteStatus(chain: String, account: String?) = CoroutineScope(Dispatchers.IO).launch {
        when (val response = proposalRepository.voteStatus(chain, account)) {
            is NetworkResult.Success -> {
                response.data.let { data ->
                    if (data.isSuccessful) {
                        _voteStatusResult.postValue(data.body())
                    } else {
                        _errorMessage.postValue("Error")
                    }
                }
            }

            is NetworkResult.Error -> {
                _errorMessage.postValue("error type : ${response.errorType}  error message : ${response.errorMessage}")
            }
        }
    }


    private var _daoProposalResult = MutableLiveData<List<Pair<String?, ProposalData?>>>()
    val daoProposalResult: LiveData<List<Pair<String?, ProposalData?>>> get() = _daoProposalResult
    fun daoProposalList(managedChannel: ManagedChannel, contAddressList: MutableList<String?>) = CoroutineScope(Dispatchers.IO).launch {
        val daoProposalMap = contAddressList.flatMap { address ->
            val response = proposalRepository.daoProposal(managedChannel, address)
            Gson().fromJson(response, ResProposalData::class.java).proposals.map { Pair(address, it) }
        }
        _daoProposalResult.postValue(daoProposalMap)
    }

    private var _voteDaoStatusResult = MutableLiveData<MutableList<ResDaoVoteStatus>?>()
    val voteDaoStatusResult: LiveData<MutableList<ResDaoVoteStatus>?> get() = _voteDaoStatusResult
    fun daoMyVoteStatus(chain: String, address: String?) = CoroutineScope(Dispatchers.IO).launch {
        when (val response = proposalRepository.daoVoteStatus(chain, address)) {
            is NetworkResult.Success -> {
                response.data.let { data ->
                    if (data.isSuccessful) {
                        _voteDaoStatusResult.postValue(data.body())
                    } else {
                        _errorMessage.postValue("Error")
                    }
                }
            }

            is NetworkResult.Error -> {
                _errorMessage.postValue("error type : ${response.errorType}  error message : ${response.errorMessage}")
            }
        }
    }
}