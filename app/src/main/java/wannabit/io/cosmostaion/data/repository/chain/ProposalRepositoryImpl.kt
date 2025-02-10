package wannabit.io.cosmostaion.data.repository.chain

import com.cosmos.base.query.v1beta1.PaginationProto
import com.cosmos.gov.v1beta1.GovProto
import com.cosmwasm.wasm.v1.QueryGrpc
import com.cosmwasm.wasm.v1.QueryProto
import com.google.gson.Gson
import com.google.gson.JsonParser
import com.google.protobuf.ByteString
import kotlinx.coroutines.Dispatchers
import org.bouncycastle.util.encoders.Base64
import retrofit2.Response
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.CosmosEndPointType
import wannabit.io.cosmostaion.common.safeApiCall
import wannabit.io.cosmostaion.data.api.RetrofitInstance
import wannabit.io.cosmostaion.data.api.RetrofitInstance.lcdApi
import wannabit.io.cosmostaion.data.model.req.ProposalList
import wannabit.io.cosmostaion.data.model.req.ProposalListReq
import wannabit.io.cosmostaion.data.model.res.CosmosProposal
import wannabit.io.cosmostaion.data.model.res.NetworkResult
import wannabit.io.cosmostaion.data.model.res.ResDaoVoteStatus
import wannabit.io.cosmostaion.data.model.res.VoteStatus
import java.time.Instant
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

    override suspend fun onChainProposal(
        chain: BaseChain, pageKey: ByteString?
    ): NetworkResult<Pair<MutableList<CosmosProposal>, ByteString?>> {
        if (chain.cosmosFetcher?.endPointType(chain) == CosmosEndPointType.USE_GRPC) {
            val channel = chain.cosmosFetcher?.getChannel()
            val pageRequest = if (pageKey != null) {
                PaginationProto.PageRequest.newBuilder().setLimit(200).setKey(pageKey)
                    .setReverse(true).build()
            } else {
                PaginationProto.PageRequest.newBuilder().setLimit(200).setReverse(true).build()
            }
            try {
                val stub = com.cosmos.gov.v1.QueryGrpc.newBlockingStub(channel)
                    .withDeadlineAfter(20L, TimeUnit.SECONDS)
                val request = com.cosmos.gov.v1.QueryProto.QueryProposalsRequest.newBuilder()
                    .setPagination(pageRequest).build()
                val nextKey = stub.proposals(request).pagination.nextKey
                val tempProposals = stub.proposals(request).proposalsList.toMutableList()

                val cosmosProposals: MutableList<CosmosProposal> = mutableListOf()
                tempProposals.forEach { proposal ->
                    val title = if (proposal.title.isNotEmpty()) {
                        proposal.title
                    } else if (proposal.messagesList.isNotEmpty()) {
                        proposal.messagesList.first().typeUrl.split(".").last()
                    } else if (proposal.metadata.isNotEmpty()) {
                        val json = JsonParser.parseString(proposal.metadata).asJsonObject
                        json["title"].asString
                    } else {
                        ""
                    }

                    val cosmosProposal = CosmosProposal(
                        proposal.id.toString(),
                        title,
                        proposal.summary,
                        proposal.status.name,
                        (proposal.votingEndTime.seconds * 1000).toString(),
                        false,
                        "",
                        "",
                        "",
                        ""
                    )
                    cosmosProposals.add(cosmosProposal)
                }

                return safeApiCall(Dispatchers.IO) {
                    Pair(cosmosProposals, nextKey)
                }

            } catch (e: Exception) {
                val stub = com.cosmos.gov.v1beta1.QueryGrpc.newBlockingStub(channel)
                    .withDeadlineAfter(20L, TimeUnit.SECONDS)
                val request = com.cosmos.gov.v1beta1.QueryProto.QueryProposalsRequest.newBuilder()
                    .setPagination(pageRequest).build()
                val nextKey = stub.proposals(request).pagination.nextKey
                val tempProposals = stub.proposals(request).proposalsList.toMutableList()

                val cosmosProposals: MutableList<CosmosProposal> = mutableListOf()
                tempProposals.forEach { proposal ->
                    val content = GovProto.TextProposal.parseFrom(proposal.content.value)
                    val title = content.title.ifEmpty {
                        proposal.content.typeUrl.split(".").last()
                    }
                    val cosmosProposal = CosmosProposal(
                        proposal.proposalId.toString(),
                        title,
                        "",
                        proposal.status.name,
                        (proposal.votingEndTime.seconds * 1000).toString(),
                        false,
                        "",
                        "",
                        "",
                        ""
                    )
                    cosmosProposals.add(cosmosProposal)
                }

                return safeApiCall(Dispatchers.IO) {
                    Pair(cosmosProposals, nextKey)
                }
            }

        } else {
            val key = if (pageKey != null) {
                pageKey.toStringUtf8()
            } else {
                ""
            }

            try {
                val response = lcdApi(chain).lcdV1Proposals("200", key, true)
                val keyObject = response["pagination"].asJsonObject["next_key"] ?: null
                val nextKey = if (keyObject?.isJsonNull == false) {
                    keyObject.asString
                } else {
                    ""
                }
                val keyByteString = ByteString.copyFromUtf8(nextKey)
                val tempProposals = response["proposals"].asJsonArray

                val cosmosProposals: MutableList<CosmosProposal> = mutableListOf()
                tempProposals.forEach { proposal ->
                    val title = if (proposal.asJsonObject.has("title")) {
                        proposal.asJsonObject["title"].asString.ifEmpty {
                            val messages = proposal.asJsonObject["messages"].asJsonArray
                            if (messages.size() > 0) {
                                if (!messages[0].asJsonObject.has("content")) {
                                    messages[0].asJsonObject["@type"].asString.split(".").last()
                                } else {
                                    messages[0].asJsonObject["content"].asJsonObject["title"].asString
                                }

                            } else if (proposal.asJsonObject.has("metadata")) {
                                val json =
                                    JsonParser.parseString(proposal.asJsonObject["metadata"].asString).asJsonObject
                                json["title"].asString
                            } else {
                                ""
                            }
                        }
                    } else {
                        val messages = proposal.asJsonObject["messages"].asJsonArray
                        if (messages.size() > 0) {
                            if (messages[0].asJsonObject.has("content")) {
                                messages[0].asJsonObject["content"].asJsonObject["title"].asString
                            } else {
                                messages[0].asJsonObject["@type"].asString.split(".").last()
                            }
                        } else if (proposal.asJsonObject.has("metadata")) {
                            val json =
                                JsonParser.parseString(proposal.asJsonObject["metadata"].asString).asJsonObject
                            json["title"].asString
                        } else {
                            ""
                        }
                    }
                    val endTime =
                        Instant.parse(proposal.asJsonObject["voting_end_time"].asString).epochSecond * 1000

                    val cosmosProposal = CosmosProposal(
                        proposal.asJsonObject["id"].asString,
                        title,
                        "",
                        proposal.asJsonObject["status"].asString,
                        endTime.toString(),
                        false,
                        "",
                        "",
                        "",
                        ""
                    )
                    cosmosProposals.add(cosmosProposal)
                }

                return safeApiCall(Dispatchers.IO) {
                    Pair(cosmosProposals, keyByteString)
                }

            } catch (e: Exception) {
                val response = lcdApi(chain).lcdV1beta1Proposals("200", key, true)
                val keyObject = response["pagination"].asJsonObject["next_key"] ?: null
                val nextKey = if (keyObject?.isJsonNull == false) {
                    keyObject.asString
                } else {
                    ""
                }
                val keyByteString = ByteString.copyFromUtf8(nextKey)
                val tempProposals = response["proposals"].asJsonArray

                val cosmosProposals: MutableList<CosmosProposal> = mutableListOf()
                tempProposals.forEach { proposal ->
                    val content = proposal.asJsonObject["content"].asJsonObject
                    val title = if (content.has("title")) {
                        content["title"].asString
                    } else {
                        content["@type"].asString.split(".").last()
                    }
                    val endTime =
                        Instant.parse(proposal.asJsonObject["voting_end_time"].asString).epochSecond * 1000

                    val cosmosProposal = CosmosProposal(
                        proposal.asJsonObject["proposal_id"].asString,
                        title,
                        "",
                        proposal.asJsonObject["status"].asString,
                        endTime.toString(),
                        false,
                        "",
                        "",
                        "",
                        ""
                    )
                    cosmosProposals.add(cosmosProposal)
                }

                return safeApiCall(Dispatchers.IO) {
                    Pair(cosmosProposals, keyByteString)
                }
            }
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
                lcdApi(chain).lcdContractInfo(contAddress, queryDataBase64).let { response ->
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