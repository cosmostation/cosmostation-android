package wannabit.io.cosmostaion.ui.viewmodel.chain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import io.grpc.ManagedChannel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.chain.cosmosClass.NEUTRON_MULTI_MODULE
import wannabit.io.cosmostaion.chain.cosmosClass.NEUTRON_OVERRULE_MODULE
import wannabit.io.cosmostaion.chain.cosmosClass.NEUTRON_SINGLE_MODULE
import wannabit.io.cosmostaion.common.concurrentForEach
import wannabit.io.cosmostaion.data.model.res.CosmosProposal
import wannabit.io.cosmostaion.data.model.res.NetworkResult
import wannabit.io.cosmostaion.data.model.res.ProposalData
import wannabit.io.cosmostaion.data.model.res.ResDaoVoteStatus
import wannabit.io.cosmostaion.data.model.res.ResProposalData
import wannabit.io.cosmostaion.data.model.res.VoteData
import wannabit.io.cosmostaion.data.model.res.VoteStatus
import wannabit.io.cosmostaion.data.repository.chain.ProposalRepository
import wannabit.io.cosmostaion.ui.viewmodel.event.SingleLiveEvent

class ProposalViewModel(private val proposalRepository: ProposalRepository) : ViewModel() {

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    private var _proposalResult = MutableLiveData<MutableList<CosmosProposal>?>()
    val proposalResult: LiveData<MutableList<CosmosProposal>?> get() = _proposalResult

    fun proposalList(chain: String) = viewModelScope.launch(Dispatchers.IO) {
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

    private var _proposalChainResult =
        MutableLiveData<Pair<BaseChain, MutableList<CosmosProposal>?>>()
    val proposalChainResult: LiveData<Pair<BaseChain, MutableList<CosmosProposal>?>> get() = _proposalChainResult

    fun proposalChainList(stakedChains: MutableList<BaseChain>) = viewModelScope.launch(Dispatchers.IO) {
        var progress = 0
        val toShowProposals = mutableListOf<CosmosProposal>()
        val myVotes = mutableListOf<VoteData>()

        stakedChains.asSequence().concurrentForEach { chain ->
            when (val response = proposalRepository.cosmosProposal(chain.apiName)) {
                is NetworkResult.Success -> {
                    response.data.let { data ->
                        if (data.isSuccessful) {
                            data.body()?.forEach { proposal ->
                                if (proposal.isVotingPeriod() && !proposal.isScam()) {
                                    toShowProposals.add(proposal)
                                }
                            }

                            if (toShowProposals.isNotEmpty()) {
                                val address = (chain as CosmosLine).address
                                voteStatus(chain.apiName, address)
                            }

                        } else {
                            return@concurrentForEach
                        }
                    }
                }

                is NetworkResult.Error -> {
                    return@concurrentForEach
                }
            }
        }

//        when (val response = proposalRepository.cosmosProposal(chain.apiName)) {
//            is NetworkResult.Success -> {
//                response.data.let { data ->
//                    if (data.isSuccessful) {
//                        _proposalChainResult.postValue(Pair(chain, data.body()))
//                    } else {
//                        _errorMessage.postValue("Error")
//                    }
//                }
//            }
//
//            is NetworkResult.Error -> {
//                _errorMessage.postValue("error type : ${response.errorType}  error message : ${response.errorMessage}")
//            }
//        }
    }

    private var _voteStatusResult = MutableLiveData<VoteStatus?>()
    val voteStatusResult: LiveData<VoteStatus?> get() = _voteStatusResult

    fun voteStatus(chain: String, account: String?) = viewModelScope.launch(Dispatchers.IO) {
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

    private var _voteChainStatusResult = MutableLiveData<Pair<BaseChain, VoteStatus?>>()
    val voteChainStatusResult: LiveData<Pair<BaseChain, VoteStatus?>> get() = _voteChainStatusResult

    fun voteChainStatus(chain: BaseChain, account: String?) =
        viewModelScope.launch(Dispatchers.IO) {
            when (val response = proposalRepository.voteStatus(chain.apiName, account)) {
                is NetworkResult.Success -> {
                    response.data.let { data ->
                        if (data.isSuccessful) {
                            _voteChainStatusResult.postValue(Pair(chain, data.body()))
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

    private var _daoSingleProposalsResult = MutableLiveData<MutableList<ProposalData?>>()
    val daoSingleProposalsResult: LiveData<MutableList<ProposalData?>> get() = _daoSingleProposalsResult

    private var _daoMultipleProposalsResult = MutableLiveData<MutableList<ProposalData?>>()
    val daoMultipleProposalsResult: LiveData<MutableList<ProposalData?>> get() = _daoMultipleProposalsResult

    private var _daoOverruleProposalsResult = MutableLiveData<MutableList<ProposalData?>>()
    val daoOverruleProposalsResult: LiveData<MutableList<ProposalData?>> get() = _daoOverruleProposalsResult

    fun daoProposals(managedChannel: ManagedChannel, contAddress: String, type: Int) =
        viewModelScope.launch(Dispatchers.IO) {
            when (val response = proposalRepository.daoProposals(managedChannel, contAddress)) {
                is NetworkResult.Success -> {
                    when (type) {
                        NEUTRON_SINGLE_MODULE -> {
                            _daoSingleProposalsResult.postValue(
                                Gson().fromJson(
                                    response.data, ResProposalData::class.java
                                )?.proposals
                            )

                        }

                        NEUTRON_MULTI_MODULE -> {
                            _daoMultipleProposalsResult.postValue(
                                Gson().fromJson(
                                    response.data, ResProposalData::class.java
                                )?.proposals
                            )

                        }

                        NEUTRON_OVERRULE_MODULE -> {
                            _daoOverruleProposalsResult.postValue(
                                Gson().fromJson(
                                    response.data, ResProposalData::class.java
                                )?.proposals
                            )
                        }
                    }
                }

                is NetworkResult.Error -> {
                    _errorMessage.postValue("error type : ${response.errorType}  error message : ${response.errorMessage}")
                }
            }
        }

    var voteDaoStatusResult = SingleLiveEvent<MutableList<ResDaoVoteStatus>?>()
    fun daoMyVoteStatus(chain: String, address: String?) = viewModelScope.launch(Dispatchers.IO) {
        when (val response = proposalRepository.daoVoteStatus(chain, address)) {
            is NetworkResult.Success -> {
                response.data.let { data ->
                    if (data.isSuccessful) {
                        voteDaoStatusResult.postValue(data.body())
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