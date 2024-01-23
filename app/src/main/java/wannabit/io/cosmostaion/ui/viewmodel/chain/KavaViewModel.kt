package wannabit.io.cosmostaion.ui.viewmodel.chain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cosmos.base.v1beta1.CoinProto
import com.kava.bep3.v1beta1.Bep3Proto
import com.kava.bep3.v1beta1.QueryProto.AssetSupplyResponse
import com.kava.bep3.v1beta1.QueryProto.QueryAssetSuppliesResponse
import com.kava.bep3.v1beta1.QueryProto.QueryAtomicSwapResponse
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
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.chain.cosmosClass.ChainBinanceBeacon
import wannabit.io.cosmostaion.common.safeApiCall
import wannabit.io.cosmostaion.data.api.RetrofitInstance
import wannabit.io.cosmostaion.data.model.res.AccountResponse
import wannabit.io.cosmostaion.data.model.res.NetworkResult
import wannabit.io.cosmostaion.data.model.res.SwapIdResponse
import wannabit.io.cosmostaion.data.repository.chain.KavaRepository
import java.util.concurrent.TimeUnit

class KavaViewModel(private val kavaRepository: KavaRepository) : ViewModel() {

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    private var _incentiveResult = MutableLiveData<QueryProto.QueryRewardsResponse?>()
    val incentiveResult: LiveData<QueryProto.QueryRewardsResponse?> get() = _incentiveResult
    fun incentive(managedChannel: ManagedChannel, address: String?) =
        viewModelScope.launch(Dispatchers.IO) {
            try {
                when (val response = kavaRepository.incentive(managedChannel, address)) {
                    is NetworkResult.Success -> {
                        _incentiveResult.postValue(response.data)
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


    private val _lendingData = MutableLiveData<LendingData?>()
    val lendingData: LiveData<LendingData?> get() = _lendingData
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
                                LendingData(
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

    private val _swapData = MutableLiveData<SwapData?>()
    val swapData: LiveData<SwapData?> get() = _swapData
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
                            _swapData.postValue(SwapData(swapPools, swapMyDeposits))
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

    private val _bep3Data = MutableLiveData<Bep3Data?>()
    val bep3Data: LiveData<Bep3Data?> get() = _bep3Data
    fun bep3Data(managedChannel: ManagedChannel) = viewModelScope.launch(Dispatchers.IO) {
        try {
            val bep3ParamDeferred = async { kavaRepository.bep3Param(managedChannel) }
            val bep3SupplyDeferred = async { kavaRepository.bep3Supply(managedChannel) }

            val responses = awaitAll(bep3ParamDeferred, bep3SupplyDeferred)

            var bep3Params: MutableList<Bep3Proto.AssetParam>? = mutableListOf()
            var bep3Supplies: MutableList<AssetSupplyResponse>? = mutableListOf()

            responses.forEach { response ->
                when (response) {
                    is NetworkResult.Success -> {
                        when (response.data) {
                            is com.kava.bep3.v1beta1.QueryProto.QueryParamsResponse -> {
                                response.data.params.assetParamsList?.let { bep3Params = it }
                            }

                            is QueryAssetSuppliesResponse -> {
                                response.data.assetSuppliesList?.let { bep3Supplies = it }
                            }
                        }
                        _bep3Data.postValue(Bep3Data(bep3Params, bep3Supplies))
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

    private var _bnbSwapIdResult = MutableLiveData<SwapIdResponse?>()
    val bnbSwapIdResult: LiveData<SwapIdResponse?> get() = _bnbSwapIdResult

    private var _kavaSwapIdResult = MutableLiveData<QueryAtomicSwapResponse?>()
    val kavaSwapIdResult: LiveData<QueryAtomicSwapResponse?> get() = _kavaSwapIdResult

    private val _bep3SwapIdErrorMessage = MutableLiveData<String>()
    val bep3SwapIdErrorMessage: LiveData<String> get() = _bep3SwapIdErrorMessage

    fun bep3SwapId(managedChannel: ManagedChannel, expectedSwapId: String?, toChain: CosmosLine?) =
        viewModelScope.launch(Dispatchers.IO) {
            if (toChain is ChainBinanceBeacon) {
                when (val response =
                    safeApiCall { RetrofitInstance.beaconApi.swapById(expectedSwapId) }) {
                    is NetworkResult.Success -> {
                        _bnbSwapIdResult.postValue(response.data)
                    }

                    is NetworkResult.Error -> {
                        _bep3SwapIdErrorMessage.postValue("error type : ${response.errorType}  error message : ${response.errorMessage}")
                    }
                }

            } else {
                try {
                    when (val response =
                        kavaRepository.bep3SwapId(managedChannel, expectedSwapId)) {
                        is NetworkResult.Success -> {
                            _kavaSwapIdResult.postValue(response.data)
                        }

                        is NetworkResult.Error -> {
                            _bep3SwapIdErrorMessage.postValue("error type : ${response.errorType}  error message : ${response.errorMessage}")
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

    private var _bep3AccountInfoResult = MutableLiveData<AccountResponse?>()
    val bep3AccountInfoResult: LiveData<AccountResponse?> get() = _bep3AccountInfoResult
    fun accountInfo(address: String?) = viewModelScope.launch(Dispatchers.IO) {
        when (val response = safeApiCall { RetrofitInstance.beaconApi.accountInfo(address) }) {
            is NetworkResult.Success -> {
                _bep3AccountInfoResult.postValue(response.data)
            }

            is NetworkResult.Error -> {
                _bep3AccountInfoResult.postValue(null)
            }
        }
    }
}

data class LendingData(
    var lendingParam: HardProto.Params?,
    val lendingRates: MutableList<MoneyMarketInterestRate>?,
    val lendingTotalDeposits: MutableList<CoinProto.Coin>?,
    val lendingTotalBorrows: MutableList<CoinProto.Coin>?,
    val lendingMyDeposits: MutableList<com.kava.hard.v1beta1.QueryProto.DepositResponse>?,
    val lendingMyBorrows: MutableList<com.kava.hard.v1beta1.QueryProto.BorrowResponse>?,
    val lendingReserve: MutableList<CoinProto.Coin>?
)

data class SwapData(
    var swapPools: MutableList<com.kava.swap.v1beta1.QueryProto.PoolResponse>?,
    var swapMyDeposits: MutableList<com.kava.swap.v1beta1.QueryProto.DepositResponse>?
)

data class Bep3Data(
    var bep3Param: MutableList<Bep3Proto.AssetParam>?,
    var bep3Supplies: MutableList<AssetSupplyResponse>?
)