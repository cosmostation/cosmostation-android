package wannabit.io.cosmostaion.model

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.launch
import retrofit2.awaitResponse
import wannabit.io.cosmostaion.base.chains.ChainConfig
import wannabit.io.cosmostaion.dao.Account
import wannabit.io.cosmostaion.network.ApiClient
import wannabit.io.cosmostaion.network.res.ResProposal
import wannabit.io.cosmostaion.network.res.ResVoteStatus

class VoteViewModel : BaseViewModel() {

    private var _proposal = MutableLiveData<ResProposal>()
    val proposal: LiveData<ResProposal> get() = _proposal

    fun loadProposal(c: Context, chainConfig: ChainConfig, proposalId: String) = scope.launch {
        val response = ApiClient.getMintscan(c).getProposal(chainConfig.chainName(), proposalId).awaitResponse()

        if (response.isSuccessful) {
            _proposal.postValue(response.body())
        }
    }

    private var _myVote = MutableLiveData<ResVoteStatus>()
    val myVote: LiveData<ResVoteStatus> get() = _myVote

    fun loadMyVoteProposal(c: Context, chainConfig: ChainConfig, account: Account) = scope.launch {
        val response = ApiClient.getMintscan(c).getVoteStatus(chainConfig.chainName(), account.address).awaitResponse()

        if (response.isSuccessful) {
            _myVote.postValue(response.body())
        }
    }
}