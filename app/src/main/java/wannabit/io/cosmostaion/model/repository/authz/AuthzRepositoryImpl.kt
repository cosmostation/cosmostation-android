package wannabit.io.cosmostaion.model.repository.authz

import cosmos.authz.v1beta1.Authz
import cosmos.authz.v1beta1.QueryGrpc
import cosmos.authz.v1beta1.QueryOuterClass
import cosmos.bank.v1beta1.QueryGrpc.*
import cosmos.bank.v1beta1.QueryOuterClass.*
import wannabit.io.cosmostaion.base.chains.ChainConfig
import wannabit.io.cosmostaion.model.NetworkResult
import wannabit.io.cosmostaion.model.type.Coin
import wannabit.io.cosmostaion.network.ChannelBuilder
import wannabit.io.cosmostaion.utils.WLog
import wannabit.io.cosmostaion.utils.safeApiCall
import java.util.concurrent.TimeUnit

class AuthzRepositoryImpl : AuthzRepository {

    override suspend fun getGranterData(chainConfig: ChainConfig, granter: String): NetworkResult<MutableList<Authz.GrantAuthorization>> {
        val stub = QueryGrpc.newBlockingStub(ChannelBuilder.getChain(chainConfig.baseChain())).withDeadlineAfter(ChannelBuilder.TIME_OUT.toLong(), TimeUnit.SECONDS)
        val request = QueryOuterClass.QueryGranterGrantsRequest.newBuilder().setGranter(granter).build()
        return safeApiCall {
            stub.granterGrants(request).grantsList
        }
    }

    override suspend fun getGranteeData(chainConfig: ChainConfig, grantee: String): NetworkResult<MutableList<Authz.GrantAuthorization>> {
        val stub = QueryGrpc.newBlockingStub(ChannelBuilder.getChain(chainConfig.baseChain())).withDeadlineAfter(ChannelBuilder.TIME_OUT.toLong(), TimeUnit.SECONDS)
        val request = QueryOuterClass.QueryGranteeGrantsRequest.newBuilder().setGrantee(grantee).build()
        return safeApiCall {
            stub.granteeGrants(request).grantsList.distinctBy { it.granter }.toMutableList()
        }
    }

    override suspend fun getBalanceData(chainConfig: ChainConfig, granter: String): NetworkResult<Coin> {
        val stub = newBlockingStub(ChannelBuilder.getChain(chainConfig.baseChain())).withDeadlineAfter(ChannelBuilder.TIME_OUT.toLong(), TimeUnit.SECONDS)
        val request = QueryAllBalancesRequest.newBuilder().setAddress(granter).build()
        val tempCoin = stub.allBalances(request).balancesList.first { coin -> coin.denom.equals(chainConfig.mainDenom(), true) }
        return safeApiCall {
            Coin(tempCoin.denom, tempCoin.amount)
        }
    }
}