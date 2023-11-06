package wannabit.io.cosmostaion.data.repository.chain

import kotlinx.coroutines.Dispatchers
import retrofit2.Response
import wannabit.io.cosmostaion.common.safeApiCall
import wannabit.io.cosmostaion.data.api.RetrofitInstance
import wannabit.io.cosmostaion.data.model.res.CosmosProposal
import wannabit.io.cosmostaion.data.model.res.NetworkResult
import wannabit.io.cosmostaion.data.model.res.VoteStatus

class ProposalRepositoryImpl : ProposalRepository {

    override suspend fun cosmosProposal(
        chain: String
    ): NetworkResult<Response<MutableList<CosmosProposal>>> {
        return safeApiCall(Dispatchers.IO) {
            RetrofitInstance.mintscanApi.cosmosProposal(chain)
        }
    }

    override suspend fun voteStatus(
        chain: String,
        account: String?
    ): NetworkResult<Response<VoteStatus>> {
        return safeApiCall(Dispatchers.IO) {
            RetrofitInstance.mintscanApi.voteStatus(chain, account)
        }
    }
}