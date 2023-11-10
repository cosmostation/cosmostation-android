package wannabit.io.cosmostaion.data.repository.chain

import com.cosmwasm.wasm.v1.QueryGrpc
import com.cosmwasm.wasm.v1.QueryProto
import com.google.gson.Gson
import com.google.protobuf.ByteString
import io.grpc.ManagedChannel
import kotlinx.coroutines.Dispatchers
import retrofit2.Response
import wannabit.io.cosmostaion.common.safeApiCall
import wannabit.io.cosmostaion.data.api.RetrofitInstance
import wannabit.io.cosmostaion.data.model.req.ProposalList
import wannabit.io.cosmostaion.data.model.req.ProposalListReq
import wannabit.io.cosmostaion.data.model.res.CosmosProposal
import wannabit.io.cosmostaion.data.model.res.NetworkResult
import wannabit.io.cosmostaion.data.model.res.ResDaoVoteStatus
import wannabit.io.cosmostaion.data.model.res.VoteStatus
import java.util.concurrent.TimeUnit

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

    override suspend fun daoProposal(
        managedChannel: ManagedChannel,
        contAddress: String?
    ): String {
        val stub = QueryGrpc.newBlockingStub(managedChannel).withDeadlineAfter(20L, TimeUnit.SECONDS)
        val req = ProposalListReq(ProposalList())
        val queryData = ByteString.copyFromUtf8(Gson().toJson(req))
        val request = QueryProto.QuerySmartContractStateRequest.newBuilder()
            .setAddress(contAddress)
            .setQueryData(queryData)
            .build()

        stub.smartContractState(request).apply {
            return this.data.toStringUtf8()
        }
    }

    override suspend fun daoVoteStatus(
        chain: String,
        address: String?
    ): NetworkResult<Response<MutableList<ResDaoVoteStatus>>> {
        return safeApiCall(Dispatchers.IO) {
            RetrofitInstance.mintscanApi.daoVoteStatus(chain, address)
        }
    }
}