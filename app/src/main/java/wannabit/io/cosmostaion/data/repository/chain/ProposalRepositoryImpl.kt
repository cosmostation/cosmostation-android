package wannabit.io.cosmostaion.data.repository.chain

import com.cosmwasm.wasm.v1.QueryGrpc
import com.cosmwasm.wasm.v1.QueryProto
import com.google.gson.Gson
import com.google.protobuf.ByteString
import kotlinx.coroutines.Dispatchers
import org.bouncycastle.util.encoders.Base64
import retrofit2.Response
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.CosmosEndPointType
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
        chain: String, account: String?
    ): NetworkResult<Response<VoteStatus>> {
        return safeApiCall(Dispatchers.IO) {
            RetrofitInstance.mintscanApi.voteStatus(chain, account)
        }
    }

    override suspend fun daoProposals(
        chain: BaseChain, contAddress: String?
    ): NetworkResult<String?> {
        val channel = chain.cosmosFetcher?.getChannel()
        val req = ProposalListReq(ProposalList())
        val queryData = ByteString.copyFromUtf8(Gson().toJson(req))

        return if (chain.cosmosFetcher?.endPointType(chain) == CosmosEndPointType.USE_GRPC) {
            val stub = QueryGrpc.newBlockingStub(channel).withDeadlineAfter(8, TimeUnit.SECONDS)
            val request =
                QueryProto.QuerySmartContractStateRequest.newBuilder().setAddress(contAddress)
                    .setQueryData(queryData).build()
            safeApiCall(Dispatchers.IO) {
                stub.smartContractState(request).data.toStringUtf8()
            }
        } else {
            val queryDataBase64 = Base64.toBase64String(queryData.toByteArray())
            safeApiCall(Dispatchers.IO) {
                RetrofitInstance.lcdApi(chain).lcdContractInfo(contAddress, queryDataBase64)
                    .let { response ->
                        ByteString.copyFromUtf8(Gson().toJson(response["data"].asJsonObject))
                            .toStringUtf8()
                    }
            }
        }
    }

    override suspend fun daoVoteStatus(
        chain: String, address: String?
    ): NetworkResult<Response<MutableList<ResDaoVoteStatus>>> {
        return safeApiCall(Dispatchers.IO) {
            RetrofitInstance.mintscanApi.daoVoteStatus(chain, address)
        }
    }
}