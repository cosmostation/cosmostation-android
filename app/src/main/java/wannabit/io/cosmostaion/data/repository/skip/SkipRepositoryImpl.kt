package wannabit.io.cosmostaion.data.repository.skip

import com.google.gson.JsonObject
import kotlinx.coroutines.Dispatchers
import retrofit2.Response
import wannabit.io.cosmostaion.common.safeApiCall
import wannabit.io.cosmostaion.data.api.RetrofitInstance
import wannabit.io.cosmostaion.data.model.req.SkipMsgReq
import wannabit.io.cosmostaion.data.model.req.SkipRouteReq
import wannabit.io.cosmostaion.data.model.res.NetworkResult
import wannabit.io.cosmostaion.data.model.res.SkipChainResponse
import wannabit.io.cosmostaion.data.model.res.SkipMsgResponse
import wannabit.io.cosmostaion.data.model.res.SkipRouteResponse

class SkipRepositoryImpl : SkipRepository {

    override suspend fun skipChains(): NetworkResult<SkipChainResponse> {
        return safeApiCall(Dispatchers.IO) {
            RetrofitInstance.skipApi.skipChains()
        }
    }

    override suspend fun skipAssets(): NetworkResult<JsonObject> {
        return safeApiCall(Dispatchers.IO) {
            RetrofitInstance.skipApi.skipAssets()
        }
    }

    override suspend fun skipRoute(req: SkipRouteReq): NetworkResult<Response<SkipRouteResponse>> {
        return safeApiCall(Dispatchers.IO) {
            RetrofitInstance.skipApi.skipRoute(req)
        }
    }

    override suspend fun skipMsg(req: SkipMsgReq): NetworkResult<Response<SkipMsgResponse>> {
        return safeApiCall(Dispatchers.IO) {
            RetrofitInstance.skipApi.skipMsg(req)
        }
    }
}