package wannabit.io.cosmostaion.model.repository.neutron

import com.google.gson.Gson
import com.google.protobuf.ByteString
import cosmwasm.wasm.v1.QueryGrpc
import cosmwasm.wasm.v1.QueryOuterClass
import retrofit2.Response
import retrofit2.awaitResponse
import wannabit.io.cosmostaion.base.chains.ChainConfig
import wannabit.io.cosmostaion.dao.Account
import wannabit.io.cosmostaion.network.ApiClient
import wannabit.io.cosmostaion.network.ChannelBuilder
import wannabit.io.cosmostaion.network.req.neutron.MyVote
import wannabit.io.cosmostaion.network.req.neutron.ProposalList
import wannabit.io.cosmostaion.network.req.neutron.ProposalListReq
import wannabit.io.cosmostaion.network.req.neutron.ProposalMyVote
import wannabit.io.cosmostaion.network.res.neutron.ResDaoData
import wannabit.io.cosmostaion.network.res.neutron.ResMyVoteStatus
import java.util.concurrent.TimeUnit

class DaoRepository {

    suspend fun getDaoData(chainConfig: ChainConfig): Response<List<ResDaoData>> {
        return ApiClient.getChainBase().getDaoData(chainConfig.chainName()).awaitResponse()
    }

    fun getDaoProposalListData(chainConfig: ChainConfig, contractAddress: String?): String? {
        try {
            val req = ProposalListReq(ProposalList())
            return getData(req, chainConfig, contractAddress)
        } catch (_: Exception) { }
        return null
    }

    fun getDaoProposalMyStatusData(chainConfig: ChainConfig, contractAddress: String?, proposalId: Int, account: Account): String? {
        try {
            val req = ProposalMyVote(MyVote(proposalId, account.address))
            return getData(req, chainConfig, contractAddress)
        } catch (_: Exception) { }
        return null
    }

    suspend fun getMyVoteStatus(chainConfig: ChainConfig, account: Account): Response<List<ResMyVoteStatus>> {
        return ApiClient.getNeutron().getDaoMyVoteStatus(chainConfig.chainName(), account.address).awaitResponse()
    }

    fun getData(req: Any?, chainConfig: ChainConfig, contractAddress: String?): String? {
        try {
            val jsonData = Gson().toJson(req)
            val queryData = ByteString.copyFromUtf8(jsonData)

            val mStub = QueryGrpc.newBlockingStub(ChannelBuilder.getChain(chainConfig.baseChain())).withDeadlineAfter(ChannelBuilder.TIME_OUT.toLong(), TimeUnit.SECONDS)
            val request = QueryOuterClass.QuerySmartContractStateRequest.newBuilder().setAddress(contractAddress).setQueryData(queryData).build()

            mStub.smartContractState(request).apply {
                return this.data.toStringUtf8()
            }
        } catch (_: Exception) { }
        return null
    }
}