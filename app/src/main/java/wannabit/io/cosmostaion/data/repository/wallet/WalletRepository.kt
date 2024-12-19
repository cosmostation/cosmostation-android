package wannabit.io.cosmostaion.data.repository.wallet

import com.cosmos.base.v1beta1.CoinProto
import com.cosmos.distribution.v1beta1.DistributionProto
import com.cosmos.staking.v1beta1.StakingProto
import com.cosmwasm.wasm.v1.QueryProto.QuerySmartContractStateResponse
import com.google.gson.JsonObject
import io.grpc.ManagedChannel
import retrofit2.Response
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.fetcher.SuiFetcher
import wannabit.io.cosmostaion.chain.majorClass.ChainBitCoin84
import wannabit.io.cosmostaion.chain.majorClass.ChainNamada
import wannabit.io.cosmostaion.chain.majorClass.ChainSui
import wannabit.io.cosmostaion.chain.testnetClass.ChainInitiaTestnet
import wannabit.io.cosmostaion.data.model.req.MoonPayReq
import wannabit.io.cosmostaion.data.model.res.AppVersion
import wannabit.io.cosmostaion.data.model.res.AssetResponse
import wannabit.io.cosmostaion.data.model.res.MoonPay
import wannabit.io.cosmostaion.data.model.res.NetworkResult
import wannabit.io.cosmostaion.data.model.res.NoticeResponse
import wannabit.io.cosmostaion.data.model.res.Price
import wannabit.io.cosmostaion.data.model.res.Token
import wannabit.io.cosmostaion.database.model.Password

interface WalletRepository {
    suspend fun selectPassword(): NetworkResult<MutableList<Password>>

    suspend fun insertPassword(password: Password)

    suspend fun version(): NetworkResult<Response<AppVersion>>

    suspend fun price(currency: String): NetworkResult<List<Price>>

    suspend fun usdPrice(): NetworkResult<List<Price>>

    suspend fun asset(): NetworkResult<AssetResponse>

    suspend fun param(): NetworkResult<JsonObject?>

    suspend fun token(chain: BaseChain): NetworkResult<MutableList<Token>>

    suspend fun auth(
        channel: ManagedChannel?, chain: BaseChain
    ): NetworkResult<Unit>

    suspend fun balance(
        channel: ManagedChannel?, chain: BaseChain
    ): NetworkResult<MutableList<CoinProto.Coin>>

    suspend fun delegation(
        channel: ManagedChannel?, chain: BaseChain
    ): NetworkResult<MutableList<StakingProto.DelegationResponse>>

    suspend fun unBonding(
        channel: ManagedChannel?, chain: BaseChain
    ): NetworkResult<MutableList<StakingProto.UnbondingDelegation>>

    suspend fun reward(
        channel: ManagedChannel?, chain: BaseChain
    ): NetworkResult<MutableList<DistributionProto.DelegationDelegatorReward>>

    suspend fun rewardAddress(channel: ManagedChannel?, chain: BaseChain): NetworkResult<String>

    suspend fun baseFee(
        channel: ManagedChannel?, chain: BaseChain
    ): NetworkResult<MutableList<CoinProto.DecCoin>>?

    suspend fun bondedValidator(
        channel: ManagedChannel?, chain: BaseChain
    ): NetworkResult<MutableList<StakingProto.Validator>>

    suspend fun unBondedValidator(
        channel: ManagedChannel?, chain: BaseChain
    ): NetworkResult<MutableList<StakingProto.Validator>>

    suspend fun unBondingValidator(
        channel: ManagedChannel?, chain: BaseChain
    ): NetworkResult<MutableList<StakingProto.Validator>>

    suspend fun moonPay(data: MoonPayReq): NetworkResult<Response<MoonPay>>

    suspend fun cw20Balance(
        channel: ManagedChannel?, chain: BaseChain, token: Token
    )

    suspend fun erc20Balance(
        chain: BaseChain, token: Token
    )

    //neutron
    suspend fun vestingData(
        channel: ManagedChannel?, chain: BaseChain
    ): NetworkResult<QuerySmartContractStateResponse>

    suspend fun vaultDeposit(
        channel: ManagedChannel?, chain: BaseChain
    ): NetworkResult<String?>

    //initia
    suspend fun initiaDelegation(
        channel: ManagedChannel?, chain: ChainInitiaTestnet
    ): NetworkResult<MutableList<com.initia.mstaking.v1.StakingProto.DelegationResponse>>

    suspend fun initiaUnBonding(
        channel: ManagedChannel?, chain: ChainInitiaTestnet
    ): NetworkResult<MutableList<com.initia.mstaking.v1.StakingProto.UnbondingDelegation>>

    suspend fun initiaBondedValidator(
        channel: ManagedChannel?, chain: ChainInitiaTestnet
    ): NetworkResult<MutableList<com.initia.mstaking.v1.StakingProto.Validator>>

    suspend fun initiaUnBondedValidator(
        channel: ManagedChannel?, chain: ChainInitiaTestnet
    ): NetworkResult<MutableList<com.initia.mstaking.v1.StakingProto.Validator>>

    suspend fun initiaUnBondingValidator(
        channel: ManagedChannel?, chain: ChainInitiaTestnet
    ): NetworkResult<MutableList<com.initia.mstaking.v1.StakingProto.Validator>>

    //okt
    suspend fun oktAccountInfo(
        chain: BaseChain
    ): NetworkResult<JsonObject?>

    suspend fun oktDeposit(
        chain: BaseChain
    ): NetworkResult<JsonObject?>

    suspend fun oktWithdraw(
        chain: BaseChain
    ): NetworkResult<JsonObject?>

    suspend fun oktToken(
        chain: BaseChain
    ): NetworkResult<JsonObject?>

    suspend fun evmToken(chain: BaseChain): NetworkResult<MutableList<Token>>

    suspend fun evmBalance(chain: BaseChain): NetworkResult<String>

    suspend fun cw721Info(chain: String): NetworkResult<JsonObject>

    suspend fun cw721TokenIds(
        channel: ManagedChannel?, chain: BaseChain, list: JsonObject
    ): NetworkResult<JsonObject?>

    suspend fun cw721TokenInfo(
        channel: ManagedChannel?, chain: BaseChain, list: JsonObject, tokenId: String
    ): NetworkResult<JsonObject?>

    suspend fun cw721TokenDetail(
        chain: BaseChain, contractAddress: String, tokenId: String
    ): NetworkResult<JsonObject>

    suspend fun ecoSystem(chain: String): NetworkResult<MutableList<JsonObject>>

    suspend fun notice(): NetworkResult<NoticeResponse>

    // Sui
    suspend fun suiBalance(
        fetcher: SuiFetcher, chain: ChainSui
    ): NetworkResult<JsonObject?>

    suspend fun suiSystemState(
        fetcher: SuiFetcher, chain: ChainSui
    ): NetworkResult<JsonObject>

    suspend fun suiOwnedObject(
        fetcher: SuiFetcher, chain: ChainSui, cursor: String?
    )

    suspend fun suiStakes(
        fetcher: SuiFetcher, chain: ChainSui
    ): NetworkResult<JsonObject>

    suspend fun suiCoinMetadata(
        fetcher: SuiFetcher, chain: ChainSui, coinType: String?
    ): NetworkResult<JsonObject>

    suspend fun suiApys(
        fetcher: SuiFetcher, chain: ChainSui
    ): NetworkResult<MutableList<JsonObject>>

    suspend fun bitBalance(chain: ChainBitCoin84): NetworkResult<JsonObject>

    //namada
    suspend fun namadaBalance(chain: ChainNamada): NetworkResult<MutableList<JsonObject>>

    suspend fun namadaBond(chain: ChainNamada): NetworkResult<JsonObject>

    suspend fun namadaUnBond(chain: ChainNamada): NetworkResult<JsonObject>

    suspend fun namadaReward(chain: ChainNamada): NetworkResult<MutableList<JsonObject>>

    suspend fun namadaWithdraw(chain: ChainNamada): NetworkResult<JsonObject>

    suspend fun namadaGas(chain: ChainNamada): NetworkResult<MutableList<JsonObject>>

    suspend fun namadaValidators(chain: ChainNamada): NetworkResult<MutableList<JsonObject>>
}