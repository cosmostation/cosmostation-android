package wannabit.io.cosmostaion.data.repository.chain

import com.kava.cdp.v1beta1.QueryProto.QueryCdpsRequest
import com.kava.cdp.v1beta1.QueryProto.QueryParamsRequest
import com.kava.cdp.v1beta1.QueryProto.QueryParamsResponse
import com.kava.incentive.v1beta1.QueryGrpc
import com.kava.incentive.v1beta1.QueryProto
import com.kava.pricefeed.v1beta1.QueryGrpc.newBlockingStub
import com.kava.pricefeed.v1beta1.QueryProto.QueryPricesRequest
import com.kava.pricefeed.v1beta1.QueryProto.QueryPricesResponse
import io.grpc.ManagedChannel
import kotlinx.coroutines.Dispatchers
import wannabit.io.cosmostaion.common.safeApiCall
import wannabit.io.cosmostaion.data.model.res.NetworkResult
import java.util.concurrent.TimeUnit

class KavaRepositoryImpl : KavaRepository {

    override suspend fun incentive(
        managedChannel: ManagedChannel,
        address: String?
    ): NetworkResult<QueryProto.QueryRewardsResponse> {
        val stub = QueryGrpc.newBlockingStub(managedChannel).withDeadlineAfter(8, TimeUnit.SECONDS)
        val request = QueryProto.QueryRewardsRequest.newBuilder().setOwner(address).build()
        return safeApiCall(Dispatchers.IO) {
            stub.rewards(request)
        }
    }

    override suspend fun priceFeed(managedChannel: ManagedChannel): NetworkResult<QueryPricesResponse> {
        val stub = newBlockingStub(managedChannel)
            .withDeadlineAfter(8, TimeUnit.SECONDS)
        val request = QueryPricesRequest.newBuilder().build()
        return safeApiCall(Dispatchers.IO) {
            stub.prices(request)
        }
    }

    override suspend fun mintParam(managedChannel: ManagedChannel): NetworkResult<QueryParamsResponse> {
        val stub = com.kava.cdp.v1beta1.QueryGrpc.newBlockingStub(managedChannel)
            .withDeadlineAfter(8, TimeUnit.SECONDS)
        val request = QueryParamsRequest.newBuilder().build()
        return safeApiCall(Dispatchers.IO) {
            stub.params(request)
        }
    }

    override suspend fun myCdp(
        managedChannel: ManagedChannel,
        address: String?
    ): NetworkResult<com.kava.cdp.v1beta1.QueryProto.QueryCdpsResponse> {
        val stub = com.kava.cdp.v1beta1.QueryGrpc.newBlockingStub(managedChannel)
            .withDeadlineAfter(8, TimeUnit.SECONDS)
        val request = QueryCdpsRequest.newBuilder().setOwner(address).build()
        return safeApiCall(Dispatchers.IO) {
            stub.cdps(request)
        }
    }

    override suspend fun lendingParam(
        managedChannel: ManagedChannel
    ): NetworkResult<com.kava.hard.v1beta1.QueryProto.QueryParamsResponse> {
        val stub = com.kava.hard.v1beta1.QueryGrpc.newBlockingStub(managedChannel)
            .withDeadlineAfter(8, TimeUnit.SECONDS)
        val request = com.kava.hard.v1beta1.QueryProto.QueryParamsRequest.newBuilder().build()
        return safeApiCall(Dispatchers.IO) {
            stub.params(request)
        }
    }

    override suspend fun lendingRate(
        managedChannel: ManagedChannel
    ): NetworkResult<com.kava.hard.v1beta1.QueryProto.QueryInterestRateResponse> {
        val stub = com.kava.hard.v1beta1.QueryGrpc.newBlockingStub(managedChannel)
            .withDeadlineAfter(8, TimeUnit.SECONDS)
        val request = com.kava.hard.v1beta1.QueryProto.QueryInterestRateRequest.newBuilder().build()
        return safeApiCall(Dispatchers.IO) {
            stub.interestRate(request)
        }
    }

    override suspend fun lendingTotalDeposit(managedChannel: ManagedChannel): NetworkResult<com.kava.hard.v1beta1.QueryProto.QueryTotalDepositedResponse> {
        val stub = com.kava.hard.v1beta1.QueryGrpc.newBlockingStub(managedChannel)
            .withDeadlineAfter(8, TimeUnit.SECONDS)
        val request = com.kava.hard.v1beta1.QueryProto.QueryTotalDepositedRequest.newBuilder().build()
        return safeApiCall(Dispatchers.IO) {
            stub.totalDeposited(request)
        }
    }

    override suspend fun lendingTotalBorrow(managedChannel: ManagedChannel): NetworkResult<com.kava.hard.v1beta1.QueryProto.QueryTotalBorrowedResponse> {
        val stub = com.kava.hard.v1beta1.QueryGrpc.newBlockingStub(managedChannel)
            .withDeadlineAfter(8, TimeUnit.SECONDS)
        val request = com.kava.hard.v1beta1.QueryProto.QueryTotalBorrowedRequest.newBuilder().build()
        return safeApiCall(Dispatchers.IO) {
            stub.totalBorrowed(request)
        }
    }

    override suspend fun lendingMyDeposit(managedChannel: ManagedChannel, address: String?): NetworkResult<com.kava.hard.v1beta1.QueryProto.QueryDepositsResponse> {
        val stub = com.kava.hard.v1beta1.QueryGrpc.newBlockingStub(managedChannel)
            .withDeadlineAfter(8, TimeUnit.SECONDS)
        val request = com.kava.hard.v1beta1.QueryProto.QueryDepositsRequest.newBuilder().setOwner(address).build()
        return safeApiCall(Dispatchers.IO) {
            stub.deposits(request)
        }
    }

    override suspend fun lendingMyBorrow(managedChannel: ManagedChannel, address: String?): NetworkResult<com.kava.hard.v1beta1.QueryProto.QueryBorrowsResponse> {
        val stub = com.kava.hard.v1beta1.QueryGrpc.newBlockingStub(managedChannel)
            .withDeadlineAfter(8, TimeUnit.SECONDS)
        val request = com.kava.hard.v1beta1.QueryProto.QueryBorrowsRequest.newBuilder().setOwner(address).build()
        return safeApiCall(Dispatchers.IO) {
            stub.borrows(request)
        }
    }

    override suspend fun lendingReserve(managedChannel: ManagedChannel): NetworkResult<com.kava.hard.v1beta1.QueryProto.QueryReservesResponse> {
        val stub = com.kava.hard.v1beta1.QueryGrpc.newBlockingStub(managedChannel)
            .withDeadlineAfter(8, TimeUnit.SECONDS)
        val request = com.kava.hard.v1beta1.QueryProto.QueryReservesRequest.newBuilder().build()
        return safeApiCall(Dispatchers.IO) {
            stub.reserves(request)
        }
    }

    override suspend fun swapList(managedChannel: ManagedChannel): NetworkResult<com.kava.swap.v1beta1.QueryProto.QueryPoolsResponse> {
        val stub = com.kava.swap.v1beta1.QueryGrpc.newBlockingStub(managedChannel)
            .withDeadlineAfter(8, TimeUnit.SECONDS)
        val request = com.kava.swap.v1beta1.QueryProto.QueryPoolsRequest.newBuilder().build()
        return safeApiCall(Dispatchers.IO) {
            stub.pools(request)
        }
    }

    override suspend fun swapMyDeposit(
        managedChannel: ManagedChannel,
        address: String?
    ): NetworkResult<com.kava.swap.v1beta1.QueryProto.QueryDepositsResponse> {
        val stub = com.kava.swap.v1beta1.QueryGrpc.newBlockingStub(managedChannel)
            .withDeadlineAfter(8, TimeUnit.SECONDS)
        val request = com.kava.swap.v1beta1.QueryProto.QueryDepositsRequest.newBuilder().setOwner(address).build()
        return safeApiCall(Dispatchers.IO) {
            stub.deposits(request)
        }
    }

    override suspend fun bep3Param(managedChannel: ManagedChannel): NetworkResult<com.kava.bep3.v1beta1.QueryProto.QueryParamsResponse> {
        val stub = com.kava.bep3.v1beta1.QueryGrpc.newBlockingStub(managedChannel)
            .withDeadlineAfter(8, TimeUnit.SECONDS)
        val request = com.kava.bep3.v1beta1.QueryProto.QueryParamsRequest.newBuilder().build()
        return safeApiCall(Dispatchers.IO) {
            stub.params(request)
        }
    }

    override suspend fun bep3Supply(managedChannel: ManagedChannel): NetworkResult<com.kava.bep3.v1beta1.QueryProto.QueryAssetSuppliesResponse> {
        val stub = com.kava.bep3.v1beta1.QueryGrpc.newBlockingStub(managedChannel)
            .withDeadlineAfter(8, TimeUnit.SECONDS)
        val request = com.kava.bep3.v1beta1.QueryProto.QueryAssetSuppliesRequest.newBuilder().build()
        return safeApiCall(Dispatchers.IO) {
            stub.assetSupplies(request)
        }
    }

    override suspend fun bep3SwapId(
        managedChannel: ManagedChannel,
        expectedSwapId: String?
    ): NetworkResult<com.kava.bep3.v1beta1.QueryProto.QueryAtomicSwapResponse> {
        val stub = com.kava.bep3.v1beta1.QueryGrpc.newBlockingStub(managedChannel)
            .withDeadlineAfter(8, TimeUnit.SECONDS)
        val request = com.kava.bep3.v1beta1.QueryProto.QueryAtomicSwapRequest.newBuilder().setSwapId(expectedSwapId).build()
        return safeApiCall(Dispatchers.IO) {
            stub.atomicSwap(request)
        }
    }
}