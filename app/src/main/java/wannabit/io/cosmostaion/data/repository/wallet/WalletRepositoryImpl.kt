package wannabit.io.cosmostaion.data.repository.wallet

import com.cosmos.auth.v1beta1.QueryProto
import com.cosmos.bank.v1beta1.QueryGrpc
import com.cosmos.bank.v1beta1.QueryProto.QueryAllBalancesRequest
import com.cosmos.bank.v1beta1.QueryProto.QueryAllBalancesResponse
import com.cosmos.base.query.v1beta1.PaginationProto
import com.cosmos.distribution.v1beta1.QueryProto.QueryDelegationTotalRewardsRequest
import com.cosmos.distribution.v1beta1.QueryProto.QueryDelegationTotalRewardsResponse
import com.cosmos.staking.v1beta1.QueryGrpc.newBlockingStub
import com.cosmos.staking.v1beta1.QueryProto.QueryDelegatorDelegationsRequest
import com.cosmos.staking.v1beta1.QueryProto.QueryDelegatorDelegationsResponse
import com.cosmos.staking.v1beta1.QueryProto.QueryDelegatorUnbondingDelegationsRequest
import com.cosmos.staking.v1beta1.QueryProto.QueryDelegatorUnbondingDelegationsResponse
import com.cosmos.staking.v1beta1.StakingProto
import com.cosmwasm.wasm.v1.QueryProto.QuerySmartContractStateResponse
import com.google.gson.Gson
import com.google.protobuf.ByteString
import io.grpc.ManagedChannel
import kotlinx.coroutines.Dispatchers
import org.json.JSONObject
import retrofit2.Response
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.chain.cosmosClass.ChainNeutron
import wannabit.io.cosmostaion.chain.cosmosClass.NEUTRON_VESTING_CONTRACT_ADDRESS
import wannabit.io.cosmostaion.common.safeApiCall
import wannabit.io.cosmostaion.data.api.RetrofitInstance
import wannabit.io.cosmostaion.data.api.RetrofitInstance.mintscanApi
import wannabit.io.cosmostaion.data.api.RetrofitInstance.walletApi
import wannabit.io.cosmostaion.data.model.req.Allocation
import wannabit.io.cosmostaion.data.model.req.AllocationReq
import wannabit.io.cosmostaion.data.model.req.MoonPayReq
import wannabit.io.cosmostaion.data.model.req.VotingPower
import wannabit.io.cosmostaion.data.model.req.VotingPowerReq
import wannabit.io.cosmostaion.data.model.res.AppVersion
import wannabit.io.cosmostaion.data.model.res.AssetResponse
import wannabit.io.cosmostaion.data.model.res.ChainResponse
import wannabit.io.cosmostaion.data.model.res.Cw20Balance
import wannabit.io.cosmostaion.data.model.res.Dao
import wannabit.io.cosmostaion.data.model.res.MoonPay
import wannabit.io.cosmostaion.data.model.res.NetworkResult
import wannabit.io.cosmostaion.data.model.res.Param
import wannabit.io.cosmostaion.data.model.res.Price
import wannabit.io.cosmostaion.data.model.res.Token
import wannabit.io.cosmostaion.data.model.res.TokenResponse
import wannabit.io.cosmostaion.data.model.res.Vault
import java.util.concurrent.TimeUnit

class WalletRepositoryImpl : WalletRepository {

    private var duration = 8L

    override suspend fun version(): NetworkResult<Response<AppVersion>> {
        return safeApiCall(Dispatchers.IO) {
            walletApi.version()
        }
    }

    override suspend fun chain(): NetworkResult<Response<ChainResponse>> {
        return safeApiCall(Dispatchers.IO) {
            mintscanApi.chain()
        }
    }

    override suspend fun price(currency: String): NetworkResult<Response<List<Price>>> {
        return safeApiCall(Dispatchers.IO) {
            mintscanApi.price(currency)
        }
    }

    override suspend fun asset(): NetworkResult<Response<AssetResponse>> {
        return safeApiCall(Dispatchers.IO) {
            mintscanApi.asset()
        }
    }

    override suspend fun param(line: CosmosLine): NetworkResult<Param?> {
        return safeApiCall(Dispatchers.IO) {
            mintscanApi.param(line.apiName).body()
        }
    }

    override suspend fun token(line: CosmosLine): NetworkResult<TokenResponse> {
        return safeApiCall(Dispatchers.IO) {
            if (line.supportCw20) {
                mintscanApi.cw20token(line.apiName)
            } else {
                mintscanApi.erc20token(line.apiName)
            }
        }
    }

    override suspend fun auth(
        managedChannel: ManagedChannel, line: CosmosLine
    ): NetworkResult<QueryProto.QueryAccountResponse?> {
        val stub = com.cosmos.auth.v1beta1.QueryGrpc.newBlockingStub(managedChannel)
            .withDeadlineAfter(duration, TimeUnit.SECONDS)
        val request = QueryProto.QueryAccountRequest.newBuilder().setAddress(line.address).build()
        return safeApiCall(Dispatchers.IO) {
            stub.account(request)
        }
    }

    override suspend fun balance(
        channel: ManagedChannel, line: CosmosLine
    ): NetworkResult<QueryAllBalancesResponse?> {
        val pageRequest = PaginationProto.PageRequest.newBuilder().setLimit(2000).build()
        val stub = QueryGrpc.newBlockingStub(channel).withDeadlineAfter(duration, TimeUnit.SECONDS)
        val request =
            QueryAllBalancesRequest.newBuilder().setPagination(pageRequest).setAddress(line.address)
                .build()
        return safeApiCall(Dispatchers.IO) {
            stub.allBalances(request)
        }
    }

    override suspend fun delegation(
        channel: ManagedChannel, line: CosmosLine
    ): NetworkResult<QueryDelegatorDelegationsResponse> {
        val stub = newBlockingStub(channel).withDeadlineAfter(duration, TimeUnit.SECONDS)
        val request =
            QueryDelegatorDelegationsRequest.newBuilder().setDelegatorAddr(line.address).build()
        return safeApiCall(Dispatchers.IO) {
            stub.delegatorDelegations(request)
        }
    }

    override suspend fun unBonding(
        channel: ManagedChannel, line: CosmosLine
    ): NetworkResult<QueryDelegatorUnbondingDelegationsResponse> {
        val stub = newBlockingStub(channel).withDeadlineAfter(duration, TimeUnit.SECONDS)
        val request =
            QueryDelegatorUnbondingDelegationsRequest.newBuilder().setDelegatorAddr(line.address)
                .build()
        return safeApiCall(Dispatchers.IO) {
            stub.delegatorUnbondingDelegations(request)
        }
    }

    override suspend fun reward(
        channel: ManagedChannel, line: CosmosLine
    ): NetworkResult<QueryDelegationTotalRewardsResponse> {
        val stub = com.cosmos.distribution.v1beta1.QueryGrpc.newBlockingStub(channel)
            .withDeadlineAfter(duration, TimeUnit.SECONDS)
        val request =
            QueryDelegationTotalRewardsRequest.newBuilder().setDelegatorAddress(line.address)
                .build()
        return safeApiCall(Dispatchers.IO) {
            stub.delegationTotalRewards(request)
        }
    }

    override suspend fun rewardAddress(
        channel: ManagedChannel, line: CosmosLine
    ): NetworkResult<String> {
        val stub = com.cosmos.distribution.v1beta1.QueryGrpc.newBlockingStub(channel)
            .withDeadlineAfter(duration, TimeUnit.SECONDS)
        val request =
            com.cosmos.distribution.v1beta1.QueryProto.QueryDelegatorWithdrawAddressRequest.newBuilder()
                .setDelegatorAddress(line.address).build()
        return safeApiCall(Dispatchers.IO) {
            stub.delegatorWithdrawAddress(request).withdrawAddress
        }
    }

    override suspend fun bondedValidator(
        channel: ManagedChannel
    ): NetworkResult<MutableList<StakingProto.Validator>> {
        val pageRequest = PaginationProto.PageRequest.newBuilder().setLimit(500).build()
        val stub = newBlockingStub(channel).withDeadlineAfter(duration, TimeUnit.SECONDS)
        val request = com.cosmos.staking.v1beta1.QueryProto.QueryValidatorsRequest.newBuilder()
            .setPagination(pageRequest).setStatus("BOND_STATUS_BONDED").build()
        return safeApiCall(Dispatchers.IO) {
            stub.validators(request).validatorsList
        }
    }

    override suspend fun unBondedValidator(
        channel: ManagedChannel
    ): NetworkResult<MutableList<StakingProto.Validator>> {
        val pageRequest = PaginationProto.PageRequest.newBuilder().setLimit(500).build()
        val stub = newBlockingStub(channel).withDeadlineAfter(duration, TimeUnit.SECONDS)
        val request = com.cosmos.staking.v1beta1.QueryProto.QueryValidatorsRequest.newBuilder()
            .setPagination(pageRequest).setStatus("BOND_STATUS_UNBONDED").build()
        return safeApiCall(Dispatchers.IO) {
            stub.validators(request).validatorsList
        }
    }

    override suspend fun unBondingValidator(
        channel: ManagedChannel
    ): NetworkResult<MutableList<StakingProto.Validator>> {
        val pageRequest = PaginationProto.PageRequest.newBuilder().setLimit(500).build()
        val stub = newBlockingStub(channel).withDeadlineAfter(duration, TimeUnit.SECONDS)
        val request = com.cosmos.staking.v1beta1.QueryProto.QueryValidatorsRequest.newBuilder()
            .setPagination(pageRequest).setStatus("BOND_STATUS_UNBONDING").build()
        return safeApiCall(Dispatchers.IO) {
            stub.validators(request).validatorsList
        }
    }

    override suspend fun evmTxHash(
        chain: String?, evmTxHash: String?
    ): NetworkResult<Response<String>> {
        return safeApiCall(Dispatchers.IO) {
            mintscanApi.evmTxHash(chain, evmTxHash)
        }
    }

    override suspend fun moonPay(data: MoonPayReq): NetworkResult<Response<MoonPay>> {
        return safeApiCall(Dispatchers.IO) {
            walletApi.moonPay(data)
        }
    }

    override suspend fun cw20Balance(
        channel: ManagedChannel, line: CosmosLine, token: Token
    ) {
        val stub = com.cosmwasm.wasm.v1.QueryGrpc.newBlockingStub(channel)
            .withDeadlineAfter(duration, TimeUnit.SECONDS)
        val req = Cw20Balance(line.address)
        val jsonData = Gson().toJson(req)
        val queryData = ByteString.copyFromUtf8(jsonData)

        val request = com.cosmwasm.wasm.v1.QueryProto.QuerySmartContractStateRequest.newBuilder()
            .setAddress(token.address).setQueryData(queryData).build()

        stub.smartContractState(request)?.let { response ->
            val json = JSONObject(response.data.toStringUtf8())
            token.amount = json.get("balance").toString()
        }
    }

    override suspend fun erc20Balance(channel: ManagedChannel, line: CosmosLine, token: Token) {
        TODO("Not yet implemented")
    }

    override suspend fun daoData(
        line: CosmosLine
    ): NetworkResult<MutableList<Dao>> {
        return safeApiCall(Dispatchers.IO) {
            RetrofitInstance.chainListApi.daoData(line.apiName)
        }
    }

    override suspend fun vaultData(
        line: CosmosLine
    ): NetworkResult<MutableList<Vault>> {
        return safeApiCall(Dispatchers.IO) {
            RetrofitInstance.chainListApi.vaultData(line.apiName)
        }
    }

    override suspend fun vestingData(
        channel: ManagedChannel, line: CosmosLine
    ): NetworkResult<QuerySmartContractStateResponse> {
        val req = AllocationReq(Allocation(line.address))
        val jsonData = Gson().toJson(req)
        val queryData = ByteString.copyFromUtf8(jsonData)

        val stub = com.cosmwasm.wasm.v1.QueryGrpc.newBlockingStub(channel)
            .withDeadlineAfter(duration, TimeUnit.SECONDS)
        val request = com.cosmwasm.wasm.v1.QueryProto.QuerySmartContractStateRequest.newBuilder()
            .setAddress(NEUTRON_VESTING_CONTRACT_ADDRESS).setQueryData(queryData).build()

        return safeApiCall(Dispatchers.IO) {
            stub.smartContractState(request)
        }
    }

    override suspend fun vaultDeposit(
        channel: ManagedChannel,
        line: ChainNeutron,
        vaultList: MutableList<Vault>?
    ) {
        val req = VotingPowerReq(VotingPower(line.address))
        val jsonData = Gson().toJson(req)
        val queryData = ByteString.copyFromUtf8(jsonData)

        val stub = com.cosmwasm.wasm.v1.QueryGrpc.newBlockingStub(channel).withDeadlineAfter(8, TimeUnit.SECONDS)
        val request = com.cosmwasm.wasm.v1.QueryProto.QuerySmartContractStateRequest.newBuilder().setAddress(vaultList?.get(0)?.address).setQueryData(queryData).build()

        stub.smartContractState(request)?.let { response ->
            val json = JSONObject(response.data.toStringUtf8())
            line.neutronDeposited = json.getString("power").toBigDecimal()
        }
    }
}