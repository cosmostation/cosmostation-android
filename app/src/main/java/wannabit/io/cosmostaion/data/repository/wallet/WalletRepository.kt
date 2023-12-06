package wannabit.io.cosmostaion.data.repository.wallet

import retrofit2.Response
import wannabit.io.cosmostaion.data.model.req.MoonPayReq
import wannabit.io.cosmostaion.data.model.res.AppVersion
import wannabit.io.cosmostaion.data.model.res.AssetResponse
import wannabit.io.cosmostaion.data.model.res.ChainResponse
import wannabit.io.cosmostaion.data.model.res.MoonPay
import wannabit.io.cosmostaion.data.model.res.NetworkResult
import wannabit.io.cosmostaion.data.model.res.Price

interface WalletRepository {
    suspend fun version(): NetworkResult<Response<AppVersion>>

    suspend fun chain(): NetworkResult<Response<ChainResponse>>

    suspend fun price(currency: String): NetworkResult<Response<List<Price>>>

    suspend fun asset(): NetworkResult<Response<AssetResponse>>

    suspend fun evmTxHash(chain: String?, evmTxHash: String?): NetworkResult<Response<String>>

    suspend fun moonPay(data: MoonPayReq):NetworkResult<Response<MoonPay>>
}