package wannabit.io.cosmostaion.data.viewmodel.chain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cosmos.base.v1beta1.CoinProto
import com.kava.cdp.v1beta1.QueryProto.QueryCdpsResponse
import com.kava.cdp.v1beta1.QueryProto.QueryParamsResponse
import com.kava.hard.v1beta1.HardProto
import com.kava.hard.v1beta1.QueryProto.MoneyMarketInterestRate
import com.kava.hard.v1beta1.QueryProto.QueryBorrowsResponse
import com.kava.hard.v1beta1.QueryProto.QueryDepositsResponse
import com.kava.hard.v1beta1.QueryProto.QueryInterestRateResponse
import com.kava.hard.v1beta1.QueryProto.QueryReservesResponse
import com.kava.hard.v1beta1.QueryProto.QueryTotalBorrowedResponse
import com.kava.hard.v1beta1.QueryProto.QueryTotalDepositedResponse
import com.kava.incentive.v1beta1.QueryProto
import com.kava.pricefeed.v1beta1.QueryProto.QueryPricesResponse
import io.grpc.ManagedChannel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.fetcher.KavaFetcher
import wannabit.io.cosmostaion.data.model.res.NetworkResult
import wannabit.io.cosmostaion.data.repository.chain.KavaRepository
import java.util.concurrent.TimeUnit

class KavaViewModel(private val kavaRepository: KavaRepository) : ViewModel() {

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    private var _incentiveResult = MutableLiveData<QueryProto.QueryRewardsResponse?>()
    val incentiveResult: LiveData<QueryProto.QueryRewardsResponse?> get() = _incentiveResult
    fun incentive(chain: BaseChain) = viewModelScope.launch(Dispatchers.IO) {
        chain.cosmosFetcher?.getChannel()?.let { channel ->
            try {
                when (val response = kavaRepository.incentive(channel, chain.address)) {
                    is NetworkResult.Success -> {
                        _incentiveResult.postValue(response.data)
                    }

                    is NetworkResult.Error -> {
                        _errorMessage.postValue("error type : ${response.errorType}  error message : ${response.errorMessage}")
                    }
                }

            } finally {
                channel.shutdown()
                try {
                    if (!channel.awaitTermination(5, TimeUnit.SECONDS)) {
                        channel.shutdownNow()
                    }
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
        }
    }

    private var _priceFeedResult = MutableLiveData<QueryPricesResponse?>()
    val priceFeedResult: LiveData<QueryPricesResponse?> get() = _priceFeedResult
    fun priceFeed(managedChannel: ManagedChannel) = viewModelScope.launch(Dispatchers.IO) {
        try {
            when (val response = kavaRepository.priceFeed(managedChannel)) {
                is NetworkResult.Success -> {
                    _priceFeedResult.postValue(response.data)
                }

                is NetworkResult.Error -> {
                    _errorMessage.postValue("error type : ${response.errorType}  error message : ${response.errorMessage}")
                }
            }
        } finally {
            managedChannel.shutdown()
            try {
                if (!managedChannel.awaitTermination(5, TimeUnit.SECONDS)) {
                    managedChannel.shutdownNow()
                }
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }
    }

    private var _mintParamResult = MutableLiveData<QueryParamsResponse?>()
    val mintParamResult: LiveData<QueryParamsResponse?> get() = _mintParamResult
    fun mintParam(managedChannel: ManagedChannel) = viewModelScope.launch(Dispatchers.IO) {
        try {
            when (val response = kavaRepository.mintParam(managedChannel)) {
                is NetworkResult.Success -> {
                    _mintParamResult.postValue(response.data)
                }

                is NetworkResult.Error -> {
                    _errorMessage.postValue("error type : ${response.errorType}  error message : ${response.errorMessage}")
                }
            }
        } finally {
            managedChannel.shutdown()
            try {
                if (!managedChannel.awaitTermination(5, TimeUnit.SECONDS)) {
                    managedChannel.shutdownNow()
                }
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }
    }

    private var _myCdpResult = MutableLiveData<QueryCdpsResponse?>()
    val myCdpResult: LiveData<QueryCdpsResponse?> get() = _myCdpResult
    fun myCdp(managedChannel: ManagedChannel, address: String?) =
        viewModelScope.launch(Dispatchers.IO) {
            try {
                when (val response = kavaRepository.myCdp(managedChannel, address)) {
                    is NetworkResult.Success -> {
                        _myCdpResult.postValue(response.data)
                    }

                    is NetworkResult.Error -> {
                        _errorMessage.postValue("error type : ${response.errorType}  error message : ${response.errorMessage}")
                    }
                }
            } finally {
                managedChannel.shutdown()
                try {
                    if (!managedChannel.awaitTermination(5, TimeUnit.SECONDS)) {
                        managedChannel.shutdownNow()
                    }
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
        }


    private val _lendingData = MutableLiveData<KavaFetcher.LendingData?>()
    val lendingData: LiveData<KavaFetcher.LendingData?> get() = _lendingData
    fun lendingData(managedChannel: ManagedChannel, address: String?) =
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val lendingParamDeferred = async { kavaRepository.lendingParam(managedChannel) }
                val interestRateDeferred = async { kavaRepository.lendingRate(managedChannel) }
                val totalDepositDeferred =
                    async { kavaRepository.lendingTotalDeposit(managedChannel) }
                val totalBorrowDeferred =
                    async { kavaRepository.lendingTotalBorrow(managedChannel) }
                val myDepositDeferred =
                    async { kavaRepository.lendingMyDeposit(managedChannel, address) }
                val myBorrowDeferred =
                    async { kavaRepository.lendingMyBorrow(managedChannel, address) }
                val reserveDeferred = async { kavaRepository.lendingReserve(managedChannel) }

                val responses = awaitAll(
                    lendingParamDeferred,
                    interestRateDeferred,
                    totalDepositDeferred,
                    totalBorrowDeferred,
                    myDepositDeferred,
                    myBorrowDeferred,
                    reserveDeferred
                )

                var lendingParam: HardProto.Params? = null
                var lendingRates: MutableList<MoneyMarketInterestRate>? = mutableListOf()
                var lendingTotalDeposits: MutableList<CoinProto.Coin>? = mutableListOf()
                var lendingTotalBorrows: MutableList<CoinProto.Coin>? = mutableListOf()
                var lendingMyDeposits: MutableList<com.kava.hard.v1beta1.QueryProto.DepositResponse>? =
                    mutableListOf()
                var lendingMyBorrows: MutableList<com.kava.hard.v1beta1.QueryProto.BorrowResponse>? =
                    mutableListOf()
                var lendingReserve: MutableList<CoinProto.Coin>? = mutableListOf()

                responses.forEach { response ->
                    when (response) {
                        is NetworkResult.Success -> {
                            when (response.data) {
                                is com.kava.hard.v1beta1.QueryProto.QueryParamsResponse -> {
                                    response.data.params?.let { lendingParam = it }
                                }

                                is QueryInterestRateResponse -> {
                                    response.data.interestRatesList?.let { lendingRates = it }
                                }

                                is QueryTotalDepositedResponse -> {
                                    response.data.suppliedCoinsList?.let {
                                        lendingTotalDeposits = it
                                    }
                                }

                                is QueryTotalBorrowedResponse -> {
                                    response.data.borrowedCoinsList?.let {
                                        lendingTotalBorrows = it
                                    }
                                }

                                is QueryDepositsResponse -> {
                                    response.data.depositsList?.let { lendingMyDeposits = it }
                                }

                                is QueryBorrowsResponse -> {
                                    response.data.borrowsList?.let { lendingMyBorrows = it }
                                }

                                is QueryReservesResponse -> {
                                    response.data.amountList?.let { lendingReserve = it }
                                }
                            }
                            _lendingData.postValue(
                                KavaFetcher.LendingData(
                                    lendingParam,
                                    lendingRates,
                                    lendingTotalDeposits,
                                    lendingTotalBorrows,
                                    lendingMyDeposits,
                                    lendingMyBorrows,
                                    lendingReserve
                                )
                            )
                        }

                        is NetworkResult.Error -> {
                            _errorMessage.postValue("error type : ${response.errorType}  error message : ${response.errorMessage}")
                        }
                    }
                }

            } finally {
                managedChannel.shutdown()
                try {
                    if (!managedChannel.awaitTermination(5, TimeUnit.SECONDS)) {
                        managedChannel.shutdownNow()
                    }
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
        }

    private val _swapData = MutableLiveData<KavaFetcher.SwapData?>()
    val swapData: LiveData<KavaFetcher.SwapData?> get() = _swapData
    fun swapData(managedChannel: ManagedChannel, address: String?) =
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val swapListDeferred = async { kavaRepository.swapList(managedChannel) }
                val swapMyDepositDeferred =
                    async { kavaRepository.swapMyDeposit(managedChannel, address) }

                val responses = awaitAll(swapListDeferred, swapMyDepositDeferred)

                var swapPools: MutableList<com.kava.swap.v1beta1.QueryProto.PoolResponse>? =
                    mutableListOf()
                var swapMyDeposits: MutableList<com.kava.swap.v1beta1.QueryProto.DepositResponse>? =
                    mutableListOf()

                responses.forEach { response ->
                    when (response) {
                        is NetworkResult.Success -> {
                            when (response.data) {
                                is com.kava.swap.v1beta1.QueryProto.QueryPoolsResponse -> {
                                    response.data.poolsList?.let { swapPools = it }
                                }

                                is com.kava.swap.v1beta1.QueryProto.QueryDepositsResponse -> {
                                    response.data.depositsList?.let { swapMyDeposits = it }
                                }
                            }
                            _swapData.postValue(KavaFetcher.SwapData(swapPools, swapMyDeposits))
                        }

                        is NetworkResult.Error -> {
                            _errorMessage.postValue("error type : ${response.errorType}  error message : ${response.errorMessage}")
                        }
                    }
                }

            } finally {
                managedChannel.shutdown()
                try {
                    if (!managedChannel.awaitTermination(5, TimeUnit.SECONDS)) {
                        managedChannel.shutdownNow()
                    }
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
        }

    private var _myDepositsResult =
        MutableLiveData<com.kava.earn.v1beta1.QueryProto.QueryDepositsResponse?>()
    val myDepositsResult: LiveData<com.kava.earn.v1beta1.QueryProto.QueryDepositsResponse?> get() = _myDepositsResult
    fun myDeposits(managedChannel: ManagedChannel, address: String?) =
        viewModelScope.launch(Dispatchers.IO) {
            try {
                when (val response = kavaRepository.myDeposits(managedChannel, address)) {
                    is NetworkResult.Success -> {
                        _myDepositsResult.postValue(response.data)
                    }

                    is NetworkResult.Error -> {
                        _errorMessage.postValue("error type : ${response.errorType}  error message : ${response.errorMessage}")
                    }
                }
            } finally {
                managedChannel.shutdown()
                try {
                    if (!managedChannel.awaitTermination(5, TimeUnit.SECONDS)) {
                        managedChannel.shutdownNow()
                    }
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
        }
}