package wannabit.io.cosmostaion.model.repository.authz

import cosmos.authz.v1beta1.Authz
import cosmos.authz.v1beta1.QueryGrpc.newBlockingStub
import cosmos.authz.v1beta1.QueryOuterClass.QueryGranteeGrantsRequest
import cosmos.authz.v1beta1.QueryOuterClass.QueryGranterGrantsRequest
import wannabit.io.cosmostaion.base.chains.ChainConfig
import wannabit.io.cosmostaion.model.NetworkResult
import wannabit.io.cosmostaion.network.ChannelBuilder
import java.util.concurrent.TimeUnit

interface AuthzRepository {

    suspend fun getGranterData(chainConfig: ChainConfig, granter: String): NetworkResult<MutableList<Authz.GrantAuthorization>>

    fun getGranteeData(chainConfig: ChainConfig, grantee: String): List<Authz.GrantAuthorization>? {
        val mStub = newBlockingStub(ChannelBuilder.getChain(chainConfig.baseChain())).withDeadlineAfter(ChannelBuilder.TIME_OUT.toLong(), TimeUnit.SECONDS)
        val request = QueryGranteeGrantsRequest.newBuilder().setGrantee(grantee).build()

        return mStub.granteeGrants(request)?.grantsList
    }
}