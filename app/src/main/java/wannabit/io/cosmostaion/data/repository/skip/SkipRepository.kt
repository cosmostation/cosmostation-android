package wannabit.io.cosmostaion.data.repository.skip

import com.google.gson.JsonObject
import retrofit2.Response
import wannabit.io.cosmostaion.data.model.req.SkipMsgReq
import wannabit.io.cosmostaion.data.model.req.SkipRouteReq
import wannabit.io.cosmostaion.data.model.res.NetworkResult
import wannabit.io.cosmostaion.data.model.res.SkipChainResponse
import wannabit.io.cosmostaion.data.model.res.SkipMsgResponse
import wannabit.io.cosmostaion.data.model.res.SkipRouteResponse

interface SkipRepository {

    suspend fun skipChains(): NetworkResult<SkipChainResponse>

    suspend fun skipAssets(): NetworkResult<JsonObject>

    suspend fun skipRoute(req: SkipRouteReq): NetworkResult<Response<SkipRouteResponse>>

    suspend fun skipMsg(req: SkipMsgReq): NetworkResult<Response<SkipMsgResponse>>
}