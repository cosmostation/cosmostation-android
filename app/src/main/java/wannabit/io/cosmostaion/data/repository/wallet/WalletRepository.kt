package wannabit.io.cosmostaion.data.repository.wallet

import com.cosmos.auth.v1beta1.QueryProto
import com.cosmos.bank.v1beta1.QueryProto.QueryAllBalancesResponse
import com.cosmos.distribution.v1beta1.QueryProto.QueryDelegationTotalRewardsResponse
import com.cosmos.staking.v1beta1.QueryProto.QueryDelegatorUnbondingDelegationsResponse
import com.cosmos.staking.v1beta1.StakingProto
import com.cosmwasm.wasm.v1.QueryProto.QuerySmartContractStateResponse
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import io.grpc.ManagedChannel
import retrofit2.Response
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.chain.EthereumLine
import wannabit.io.cosmostaion.chain.cosmosClass.ChainNeutron
import wannabit.io.cosmostaion.data.model.req.MoonPayReq
import wannabit.io.cosmostaion.data.model.res.AppVersion
import wannabit.io.cosmostaion.data.model.res.AssetResponse
import wannabit.io.cosmostaion.data.model.res.MoonPay
import wannabit.io.cosmostaion.data.model.res.NetworkResult
import wannabit.io.cosmostaion.data.model.res.OktAccountResponse
import wannabit.io.cosmostaion.data.model.res.OktDepositedResponse
import wannabit.io.cosmostaion.data.model.res.OktTokenResponse
import wannabit.io.cosmostaion.data.model.res.OktWithdrawResponse
import wannabit.io.cosmostaion.data.model.res.Price
import wannabit.io.cosmostaion.data.model.res.PushStatus
import wannabit.io.cosmostaion.data.model.res.Token
import wannabit.io.cosmostaion.database.model.Password

interface WalletRepository {
    suspend fun selectPassword(): NetworkResult<MutableList<Password>>

    suspend fun insertPassword(password: Password)

    suspend fun version(): NetworkResult<Response<AppVersion>>

    suspend fun price(currency: String): NetworkResult<List<Price>>

    suspend fun usdPrice(): NetworkResult<List<Price>>

    suspend fun pushStatus(fcmToken: String): NetworkResult<Response<PushStatus>>

    suspend fun asset(): NetworkResult<AssetResponse>

    suspend fun param(): NetworkResult<JsonObject?>

    suspend fun token(line: CosmosLine): NetworkResult<MutableList<Token>>

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

    suspend fun moonPay(data: MoonPayReq): NetworkResult<Response<MoonPay>>

    suspend fun cw20Balance(
        channel: ManagedChannel, line: CosmosLine, token: Token
    )

    suspend fun erc20Balance(
        line: CosmosLine, token: Token
    )

    //neutron
    suspend fun vestingData(
        channel: ManagedChannel, line: CosmosLine
    ): NetworkResult<QuerySmartContractStateResponse>

    suspend fun vaultDeposit(
        channel: ManagedChannel, line: ChainNeutron
    ): NetworkResult<String?>

    //lcd
    suspend fun oktAccountInfo(
        line: CosmosLine
    ): NetworkResult<OktAccountResponse?>

    suspend fun oktDeposit(
        line: CosmosLine
    ): NetworkResult<OktDepositedResponse?>

    suspend fun oktWithdraw(
        line: CosmosLine
    ): NetworkResult<OktWithdrawResponse?>

    suspend fun oktToken(
        line: CosmosLine
    ): NetworkResult<OktTokenResponse?>

    suspend fun evmToken(evmLine: EthereumLine): NetworkResult<MutableList<Token>>

    suspend fun evmBalance(evmLine: EthereumLine): NetworkResult<String>

    suspend fun cw721Info(chain: String): NetworkResult<MutableList<JsonObject>>

    suspend fun cw721TokenIds(
        channel: ManagedChannel, line: CosmosLine, list: JsonObject
    ): NetworkResult<JsonObject?>

    suspend fun cw721TokenInfo(
        channel: ManagedChannel, line: CosmosLine, list: JsonObject, tokenId: String
    ): NetworkResult<JsonObject?>

    suspend fun cw721TokenDetail(
        line: CosmosLine, contractAddress: String, tokenId: String
    ): NetworkResult<JsonObject>

    suspend fun ecoSystem(chain: String): NetworkResult<MutableList<JsonObject>>
}