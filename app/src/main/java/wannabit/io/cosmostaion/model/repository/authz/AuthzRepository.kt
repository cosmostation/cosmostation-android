package wannabit.io.cosmostaion.model.repository.authz

import cosmos.authz.v1beta1.Authz
import cosmos.authz.v1beta1.QueryGrpc.newBlockingStub
import cosmos.authz.v1beta1.QueryOuterClass.QueryGranteeGrantsRequest
import cosmos.authz.v1beta1.QueryOuterClass.QueryGranterGrantsRequest
import wannabit.io.cosmostaion.base.chains.ChainConfig
import wannabit.io.cosmostaion.model.NetworkResult
import wannabit.io.cosmostaion.model.type.Coin
import wannabit.io.cosmostaion.network.ChannelBuilder
import java.util.concurrent.TimeUnit

interface AuthzRepository {

    suspend fun getGranterData(chainConfig: ChainConfig, granter: String): NetworkResult<MutableList<Authz.GrantAuthorization>>

    suspend fun getGranteeData(chainConfig: ChainConfig, grantee: String): NetworkResult<MutableList<Authz.GrantAuthorization>>

    suspend fun getBalanceData(chainConfig: ChainConfig, granter: String): NetworkResult<Coin>
}