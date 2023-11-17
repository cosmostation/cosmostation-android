package wannabit.io.cosmostaion.data.repository.chain

import com.kava.incentive.v1beta1.QueryProto
import com.kava.pricefeed.v1beta1.QueryProto.QueryPricesResponse
import io.grpc.ManagedChannel
import wannabit.io.cosmostaion.data.model.res.NetworkResult

interface KavaRepository {

    suspend fun incentive(managedChannel: ManagedChannel, address: String?): NetworkResult<QueryProto.QueryRewardsResponse>

    suspend fun priceFeed(managedChannel: ManagedChannel): NetworkResult<QueryPricesResponse>

    suspend fun mintParam(managedChannel: ManagedChannel): NetworkResult<com.kava.cdp.v1beta1.QueryProto.QueryParamsResponse>

    suspend fun myCdp(managedChannel: ManagedChannel, address: String?): NetworkResult<com.kava.cdp.v1beta1.QueryProto.QueryCdpsResponse>

    suspend fun lendingParam(managedChannel: ManagedChannel): NetworkResult<com.kava.hard.v1beta1.QueryProto.QueryParamsResponse>

    suspend fun lendingRate(managedChannel: ManagedChannel): NetworkResult<com.kava.hard.v1beta1.QueryProto.QueryInterestRateResponse>

    suspend fun lendingTotalDeposit(managedChannel: ManagedChannel): NetworkResult<com.kava.hard.v1beta1.QueryProto.QueryTotalDepositedResponse>

    suspend fun lendingTotalBorrow(managedChannel: ManagedChannel): NetworkResult<com.kava.hard.v1beta1.QueryProto.QueryTotalBorrowedResponse>

    suspend fun lendingMyDeposit(managedChannel: ManagedChannel, address: String?): NetworkResult<com.kava.hard.v1beta1.QueryProto.QueryDepositsResponse>

    suspend fun lendingMyBorrow(managedChannel: ManagedChannel, address: String?): NetworkResult<com.kava.hard.v1beta1.QueryProto.QueryBorrowsResponse>

    suspend fun lendingReserve(managedChannel: ManagedChannel): NetworkResult<com.kava.hard.v1beta1.QueryProto.QueryReservesResponse>

    suspend fun swapList(managedChannel: ManagedChannel): NetworkResult<com.kava.swap.v1beta1.QueryProto.QueryPoolsResponse>

    suspend fun swapMyDeposit(managedChannel: ManagedChannel, address: String?): NetworkResult<com.kava.swap.v1beta1.QueryProto.QueryDepositsResponse>
}