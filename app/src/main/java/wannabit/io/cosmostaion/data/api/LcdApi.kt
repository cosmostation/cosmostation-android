package wannabit.io.cosmostaion.data.api

import com.google.gson.JsonObject
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import wannabit.io.cosmostaion.data.model.req.BroadcastReq
import wannabit.io.cosmostaion.data.model.req.BroadcastTxReq
import wannabit.io.cosmostaion.data.model.req.MoveStakeReq
import wannabit.io.cosmostaion.data.model.req.MoveTransactionBlock
import wannabit.io.cosmostaion.data.model.req.MoveUnStakeReq
import wannabit.io.cosmostaion.data.model.req.SimulateTxReq
import wannabit.io.cosmostaion.data.model.res.LegacyRes

interface LcdApi {
    @GET("cosmos/auth/v1beta1/accounts/{address}")
    suspend fun lcdAuthInfo(@Path("address") address: String?): JsonObject

    @GET("cosmos/bank/v1beta1/balances/{address}")
    suspend fun lcdBalanceInfo(
        @Path("address") address: String?, @Query("pagination.limit") limit: String
    ): JsonObject

    @GET("cosmos/staking/v1beta1/delegations/{address}")
    suspend fun lcdDelegationInfo(@Path("address") address: String?): JsonObject

    @GET("cosmos/staking/v1beta1/delegators/{address}/unbonding_delegations")
    suspend fun lcdUnBondingInfo(@Path("address") address: String?): JsonObject

    @GET("cosmos/distribution/v1beta1/delegators/{address}/rewards")
    suspend fun lcdRewardInfo(@Path("address") address: String?): JsonObject

    @GET("cosmos/distribution/v1beta1/delegators/{address}/withdraw_address")
    suspend fun lcdRewardAddressInfo(@Path("address") address: String?): JsonObject

    @GET("feemarket/v1/gas_prices")
    suspend fun lcdFeeMarketInfo(): JsonObject

    @GET("cosmos/staking/v1beta1/validators?status=BOND_STATUS_BONDED&pagination.limit=500")
    suspend fun lcdBondedValidatorInfo(): JsonObject

    @GET("cosmos/staking/v1beta1/validators?status=BOND_STATUS_UNBONDED&pagination.limit=500")
    suspend fun lcdUnBondedValidatorInfo(): JsonObject

    @GET("cosmos/staking/v1beta1/validators?status=BOND_STATUS_UNBONDING&pagination.limit=500")
    suspend fun lcdUnBondingValidatorInfo(): JsonObject

    @GET("cosmos/gov/v1/proposals")
    suspend fun lcdV1Proposals(
        @Query("pagination.limit") limit: String,
        @Query("pagination.key") nextKey: String? = "",
        @Query("pagination.reverse") reverse: Boolean
    ): JsonObject

    @GET("cosmos/gov/v1beta1/proposals")
    suspend fun lcdV1beta1Proposals(
        @Query("pagination.limit") limit: String,
        @Query("pagination.key") nextKey: String? = "",
        @Query("pagination.reverse") reverse: Boolean
    ): JsonObject

    @GET("cosmos/gov/v1/proposals/{proposal_id}/votes/{voter}")
    suspend fun lcdV1VoteStatus(
        @Path("proposal_id") proposalId: String, @Path("voter") voter: String
    ): JsonObject

    @GET("cosmos/gov/v1beta1/proposals/{proposal_id}/votes/{voter}")
    suspend fun lcdV1beta1VoteStatus(
        @Path("proposal_id") proposalId: String, @Path("voter") voter: String
    ): JsonObject

    //initia
    @GET("initia/mstaking/v1/delegations/{address}")
    suspend fun lcdInitiaDelegationInfo(@Path("address") address: String?): JsonObject

    @GET("initia/mstaking/v1/delegators/{address}/unbonding_delegations")
    suspend fun lcdInitiaUnBondingInfo(@Path("address") address: String?): JsonObject

    @GET("initia/mstaking/v1/validators?status=BOND_STATUS_BONDED&pagination.limit=500")
    suspend fun lcdInitiaBondedValidatorInfo(): JsonObject

    @GET("initia/mstaking/v1/validators?status=BOND_STATUS_UNBONDED&pagination.limit=500")
    suspend fun lcdInitiaUnBondedValidatorInfo(): JsonObject

    @GET("initia/mstaking/v1/validators?status=BOND_STATUS_UNBONDING&pagination.limit=500")
    suspend fun lcdInitiaUnBondingValidatorInfo(): JsonObject

    @GET("cosmwasm/wasm/v1/contract/{address}/smart/{query_data}")
    suspend fun lcdContractInfo(
        @Path("address") address: String?, @Path("query_data") queryData: String
    ): JsonObject

    @GET("cosmos/base/tendermint/v1beta1/blocks/latest")
    suspend fun lcdNewLastHeightInfo(): JsonObject

    @GET("ibc/core/channel/v1/channels/{channel}/ports/{port}/client_state")
    suspend fun lcdIbcClientInfo(
        @Path("channel") channel: String?, @Path("port") port: String?
    ): JsonObject

    @POST("cosmos/tx/v1beta1/simulate")
    @Headers("Content-Type: application/json")
    suspend fun lcdSimulateTx(@Body txReq: SimulateTxReq): Response<JsonObject>

    @POST("cosmos/tx/v1beta1/txs")
    @Headers("Content-Type: application/json")
    suspend fun lcdBroadcastTx(@Body txReq: BroadcastTxReq): JsonObject

    @GET("cosmos/tx/v1beta1/txs/{hash}")
    suspend fun lcdTxInfo(@Path("hash") hash: String?): Response<JsonObject>

    @GET("auth/accounts/{address}")
    suspend fun oktAccountInfo(@Path("address") address: String?): JsonObject

    @GET("staking/delegators/{address}")
    suspend fun oktDepositInfo(@Path("address") address: String?): JsonObject

    @GET("staking/delegators/{address}/unbonding_delegations")
    suspend fun oktWithdrawInfo(@Path("address") address: String?): JsonObject

    @GET("staking/validators?status=all")
    suspend fun oktValidators(): MutableList<JsonObject>

    @POST("txs")
    suspend fun broadTx(@Body data: BroadcastReq?): LegacyRes


    //Sui
    @POST("buildStakingRequest")
    suspend fun unSafeStake(@Body parameters: MoveStakeReq): String

    @POST("buildUnstakingRequest")
    suspend fun unSafeUnStake(@Body parameters: MoveUnStakeReq): String

    @POST("buildSuiTransaction")
    suspend fun transactionBlock(@Body parameters: MoveTransactionBlock): Response<String>

    //Iota
    @POST("buildIotaStakingRequest")
    suspend fun unSafeIotaStake(@Body parameters: MoveStakeReq): String

    @POST("buildIotaUnstakingRequest")
    suspend fun unSafeIotaUnStake(@Body parameters: MoveUnStakeReq): String

    @POST("buildIotaTransaction")
    suspend fun iotaTransactionBlock(@Body parameters: MoveTransactionBlock): Response<String>


    //Bit
    @GET("api/address/{address}")
    suspend fun bitBalance(@Path("address") address: String): JsonObject

    @GET("v2/delegations")
    suspend fun bitStakingBalance(@Query("staker_pk_hex") pubkey: String): JsonObject

    @GET("api/address/{address}/txs")
    suspend fun bitTxHistory(
        @Path("address") address: String, @Query("after_txid") afterTxId: String
    ): MutableList<JsonObject>

    @GET("api/blocks/tip/height")
    suspend fun bitBlockHeight(): Long

    @GET("api/address/{address}/utxo")
    suspend fun bitUtxo(@Path("address") address: String): MutableList<JsonObject>

    @GET("api/v1/validate-address/{address}")
    suspend fun bitIsValidAddress(@Path("address") address: String): JsonObject

    @GET("api/tx/{txhash}")
    suspend fun bitTx(@Path("txhash") txhash: String): Response<JsonObject>

    @GET("babylon/finality/v1/finality_providers/{height}")
    suspend fun btcFinalityVotingPower(@Path("height") height: Long): JsonObject

    @GET("v2/network-info")
    suspend fun bitNetworkInfo(): JsonObject

    @GET("babylon/btclightclient/v1/tip")
    suspend fun btcClientTioHeight(): JsonObject

    @GET("api/v1/fees/recommended")
    suspend fun btcFee(): JsonObject

    //Babylon
    @GET("babylon/incentive/address/{address}/reward_gauge")
    suspend fun lcdBtcReward(@Path("address") address: String): JsonObject

    @GET("cosmos/base/node/v1beta1/status")
    suspend fun lcdChainHeight(): JsonObject

    @GET("babylon/btccheckpoint/v1/params")
    suspend fun lcdBtcCheckpointParam(): JsonObject

    @GET("babylon/epoching/v1/current_epoch")
    suspend fun lcdCurrentEpoch(): JsonObject

    @GET("babylon/epoching/v1/epochs/{epoch_num}/messages?pagination.limit=500")
    suspend fun lcdEpochMsg(@Path("epoch_num") epochNum: Long): JsonObject

    @GET("cosmos/tx/v1beta1/txs/{hash}")
    suspend fun lcdEpochTxType(@Path("hash") hash: String?): JsonObject

    //Atomone
    @GET("atomone/photon/v1/conversion_rate")
    suspend fun conversionRate(): JsonObject
}