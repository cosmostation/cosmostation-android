package wannabit.io.cosmostaion.data.api

import retrofit2.http.GET
import wannabit.io.cosmostaion.data.model.res.SupportConfig

interface ChainApi {
    @GET("/cosmostation/chainlist/master/wallet_mobile/dapp/config.json")
    suspend fun supportConfig(): SupportConfig
}