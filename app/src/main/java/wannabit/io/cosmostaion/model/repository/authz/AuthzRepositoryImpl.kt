package wannabit.io.cosmostaion.model.repository.authz

import cosmos.authz.v1beta1.Authz
import cosmos.authz.v1beta1.QueryGrpc
import cosmos.authz.v1beta1.QueryOuterClass
import wannabit.io.cosmostaion.base.chains.ChainConfig
import wannabit.io.cosmostaion.model.NetworkResult
import wannabit.io.cosmostaion.network.ChannelBuilder
import wannabit.io.cosmostaion.utils.safeApiCall
import java.util.concurrent.TimeUnit

class AuthzRepositoryImpl : AuthzRepository {

    override suspend fun getGranterData(chainConfig: ChainConfig, granter: String): NetworkResult<MutableList<Authz.GrantAuthorization>> {
        val mStub = QueryGrpc.newBlockingStub(ChannelBuilder.getChain(chainConfig.baseChain())).withDeadlineAfter(ChannelBuilder.TIME_OUT.toLong(), TimeUnit.SECONDS)
        val request = QueryOuterClass.QueryGranterGrantsRequest.newBuilder().setGranter(granter).build()
        return safeApiCall {
            mStub.granterGrants(request).grantsList
        }
    }
}