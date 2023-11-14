package wannabit.io.cosmostaion.data.repository.chain

import com.kava.incentive.v1beta1.QueryProto
import com.kava.pricefeed.v1beta1.QueryProto.*
import io.grpc.ManagedChannel
import retrofit2.Response
import wannabit.io.cosmostaion.data.model.res.NetworkResult

interface KavaRepository {

    suspend fun incentive(managedChannel: ManagedChannel, address: String?): NetworkResult<QueryProto.QueryRewardsResponse>

    suspend fun priceFeed(managedChannel: ManagedChannel): NetworkResult<QueryPricesResponse>

    suspend fun mintParam(managedChannel: ManagedChannel): NetworkResult<com.kava.cdp.v1beta1.QueryProto.QueryParamsResponse>

    suspend fun myCdp(managedChannel: ManagedChannel, address: String?): NetworkResult<com.kava.cdp.v1beta1.QueryProto.QueryCdpsResponse>
}