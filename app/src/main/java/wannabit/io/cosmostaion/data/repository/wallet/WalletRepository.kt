package wannabit.io.cosmostaion.data.repository.wallet

import com.cosmos.auth.v1beta1.QueryProto
import com.cosmos.bank.v1beta1.QueryProto.QueryAllBalancesResponse
import com.cosmos.distribution.v1beta1.QueryProto.QueryDelegationTotalRewardsResponse
import com.cosmos.staking.v1beta1.QueryProto.QueryDelegatorUnbondingDelegationsResponse
import com.cosmos.staking.v1beta1.StakingProto
import com.cosmwasm.wasm.v1.QueryProto.QuerySmartContractStateResponse
import io.grpc.ManagedChannel
import retrofit2.Response
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.chain.cosmosClass.ChainNeutron
import wannabit.io.cosmostaion.data.model.req.MoonPayReq
import wannabit.io.cosmostaion.data.model.res.AppVersion
import wannabit.io.cosmostaion.data.model.res.AssetResponse
import wannabit.io.cosmostaion.data.model.res.ChainResponse
import wannabit.io.cosmostaion.data.model.res.Dao
import wannabit.io.cosmostaion.data.model.res.MoonPay
import wannabit.io.cosmostaion.data.model.res.NetworkResult
import wannabit.io.cosmostaion.data.model.res.Param
import wannabit.io.cosmostaion.data.model.res.Price
import wannabit.io.cosmostaion.data.model.res.Token
import wannabit.io.cosmostaion.data.model.res.TokenResponse
import wannabit.io.cosmostaion.data.model.res.Vault

interface WalletRepository {
    suspend fun version(): NetworkResult<Response<AppVersion>>

    suspend fun chain(): NetworkResult<Response<ChainResponse>>

    suspend fun price(currency: String): NetworkResult<Response<List<Price>>>

    suspend fun asset(): NetworkResult<Response<AssetResponse>>

    suspend fun param(line: CosmosLine): NetworkResult<Param?>

    suspend fun token(line: CosmosLine): NetworkResult<TokenResponse>

    suspend fun auth(
        managedChannel: ManagedChannel, line: CosmosLine
    ): NetworkResult<QueryProto.QueryAccountResponse?>

    suspend fun balance(
        channel: ManagedChannel, line: CosmosLine
    ): NetworkResult<QueryAllBalancesResponse?>

    suspend fun delegation(
        channel: ManagedChannel, line: CosmosLine
    ): NetworkResult<com.cosmos.staking.v1beta1.QueryProto.QueryDelegatorDelegationsResponse>

    suspend fun unBonding(
        channel: ManagedChannel, line: CosmosLine
    ): NetworkResult<QueryDelegatorUnbondingDelegationsResponse>

    suspend fun reward(
        channel: ManagedChannel, line: CosmosLine
    ): NetworkResult<QueryDelegationTotalRewardsResponse>

    suspend fun rewardAddress(channel: ManagedChannel, line: CosmosLine): NetworkResult<String>

    suspend fun bondedValidator(
        channel: ManagedChannel
    ): NetworkResult<MutableList<StakingProto.Validator>>

    suspend fun unBondedValidator(
        channel: ManagedChannel
    ): NetworkResult<MutableList<StakingProto.Validator>>

    suspend fun unBondingValidator(
        channel: ManagedChannel
    ): NetworkResult<MutableList<StakingProto.Validator>>

    suspend fun evmTxHash(chain: String?, evmTxHash: String?): NetworkResult<Response<String>>

    suspend fun moonPay(data: MoonPayReq): NetworkResult<Response<MoonPay>>

    suspend fun cw20Balance(
        channel: ManagedChannel, line: CosmosLine, token: Token
    )

    suspend fun erc20Balance(
        channel: ManagedChannel, line: CosmosLine, token: Token
    )

    //neutron
    suspend fun daoData(line: CosmosLine): NetworkResult<MutableList<Dao>>

    suspend fun vaultData(line: CosmosLine): NetworkResult<MutableList<Vault>>

    suspend fun vestingData(
        channel: ManagedChannel,
        line: CosmosLine
    ): NetworkResult<QuerySmartContractStateResponse>

    suspend fun vaultDeposit(
        channel: ManagedChannel,
        line: ChainNeutron,
        vaultList: MutableList<Vault>?
    )
}