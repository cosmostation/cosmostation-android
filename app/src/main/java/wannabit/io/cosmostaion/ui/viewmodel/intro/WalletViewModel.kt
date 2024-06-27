package wannabit.io.cosmostaion.ui.viewmodel.intro

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cosmos.staking.v1beta1.StakingProto
import com.google.gson.JsonObject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.web3j.protocol.Web3j
import org.web3j.protocol.http.HttpService
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.chain.EthereumLine
import wannabit.io.cosmostaion.common.BaseConstant
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.CosmostationConstants
import wannabit.io.cosmostaion.common.getChannel
import wannabit.io.cosmostaion.data.model.req.MoonPayReq
import wannabit.io.cosmostaion.data.model.res.AppVersion
import wannabit.io.cosmostaion.data.model.res.AssetResponse
import wannabit.io.cosmostaion.data.model.res.NetworkResult
import wannabit.io.cosmostaion.data.repository.wallet.WalletRepository
import wannabit.io.cosmostaion.database.AppDatabase
import wannabit.io.cosmostaion.database.CryptoHelper
import wannabit.io.cosmostaion.database.model.Password
import wannabit.io.cosmostaion.ui.viewmodel.event.SingleLiveEvent
import java.math.BigDecimal

class WalletViewModel(private val walletRepository: WalletRepository) : ViewModel() {

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    private val _networkErrorMessage = MutableLiveData<String>()
    val networkErrorMessage: LiveData<String> get() = _networkErrorMessage

    private val _hasPassword = MutableLiveData<Boolean>()
    val hasPassword: LiveData<Boolean> get() = _hasPassword

    fun hasPassword() = viewModelScope.launch(Dispatchers.IO) {
        when (val response = walletRepository.selectPassword()) {
            is NetworkResult.Success -> {
                if (response.data.isNotEmpty()) {
                    _hasPassword.postValue(true)
                } else {
                    _hasPassword.postValue(false)
                }
            }

            is NetworkResult.Error -> {
                _errorMessage.postValue("error type : ${response.errorType}  error message : ${response.errorMessage}")
            }
        }
    }

    private val _insertPasswordResult = MutableLiveData<Password>()
    val insertPasswordResult: LiveData<Password> get() = _insertPasswordResult

    fun insertPassword(userInput: String) = viewModelScope.launch(Dispatchers.IO) {
        CryptoHelper.signData(userInput, CosmostationConstants.ENCRYPT_PASSWORD_KEY)
            ?.let { resource ->
                val password = Password(0, resource)
                walletRepository.insertPassword(password)
                _insertPasswordResult.postValue(password)
            }
    }

    private var _walletAppVersionResult = MutableLiveData<AppVersion>()
    val walletAppVersionResult: LiveData<AppVersion> get() = _walletAppVersionResult

    fun walletAppVersion() = viewModelScope.launch(Dispatchers.IO) {
        when (val response = walletRepository.version()) {
            is NetworkResult.Success -> {
                response.data.let { data ->
                    if (data.isSuccessful) {
                        _walletAppVersionResult.postValue(data.body())
                    } else {
                        _networkErrorMessage.postValue("Error")
                    }
                }
            }

            is NetworkResult.Error -> {
                _networkErrorMessage.postValue("error type : ${response.errorType}  error message : ${response.errorMessage}")
            }
        }
    }

    private val _defaultInfoDataResult = MutableLiveData<Boolean>()
    val defaultInfoDataResult: LiveData<Boolean> get() = _defaultInfoDataResult

    fun defaultInfoData() = CoroutineScope(Dispatchers.IO).launch {
        price(BaseData.currencyName().lowercase())
        val loadParamDeferred = async { walletRepository.param() }
        val loadAssetDeferred = async { walletRepository.asset() }

        val responses = awaitAll(
            loadParamDeferred, loadAssetDeferred
        )

        responses.forEach { response ->
            when (response) {
                is NetworkResult.Success -> {
                    when (response.data) {
                        is JsonObject -> {
                            if (!response.data.isJsonNull) {
                                BaseData.chainParam = response.data
                            }
                        }

                        is AssetResponse -> {
                            response.data.assets?.let { BaseData.assets = it }
                        }
                    }
                }

                is NetworkResult.Error -> {

                }
            }
        }
        _defaultInfoDataResult.postValue(true)
    }

    var updatePriceResult = SingleLiveEvent<String>()

    fun price(currency: String, force: Boolean? = false) = viewModelScope.launch(Dispatchers.IO) {
        if (!BaseData.priceUpdateIfNeed() && force == false) {
            return@launch
        }
        when (val response = walletRepository.price(currency)) {
            is NetworkResult.Success -> {
                response.data.let { data ->
                    BaseData.prices = data
                    BaseData.setLastPriceTime()
                    BaseData.baseAccount?.updateAllValue()
                    updatePriceResult.postValue(currency)
                }
            }

            is NetworkResult.Error -> {
                _errorMessage.postValue("error type : ${response.errorType}  error message : ${response.errorMessage}")
            }
        }

        when (val response = walletRepository.usdPrice()) {
            is NetworkResult.Success -> {
                response.data.let { data ->
                    BaseData.usdPrices = data
                }
            }

            is NetworkResult.Error -> {
                _errorMessage.postValue("error type : ${response.errorType}  error message : ${response.errorMessage}")
            }
        }
    }

    var pushStatusResult = SingleLiveEvent<Boolean>()
    fun pushStatus(fcmToken: String) = viewModelScope.launch(Dispatchers.IO) {
        when (val response = walletRepository.pushStatus(fcmToken)) {
            is NetworkResult.Success -> {
                response.data.let { data ->
                    if (data.isSuccessful) {
                        pushStatusResult.postValue(data.body()?.subscribe)
                    } else {
                        _errorMessage.postValue("Error")
                    }
                }
            }

            is NetworkResult.Error -> {
                _errorMessage.postValue("error type : ${response.errorType}  error message : ${response.errorMessage}")
            }
        }
    }

    private val _chainDataErrorMessage = MutableLiveData<String>()
    val chainDataErrorMessage: LiveData<String> get() = _chainDataErrorMessage

    fun loadGrpcStakeData(
        line: CosmosLine
    ) = viewModelScope.launch(Dispatchers.IO) {
        if (line.cosmosValidators.size > 0) {
            return@launch
        }
        val tempValidators = mutableListOf<StakingProto.Validator>()

        val channel = getChannel(line)
        try {
//            val loadBondedDeferred = async { walletRepository.bondedValidator(channel) }
//            val loadUnBondedDeferred = async { walletRepository.unBondedValidator(channel) }
//            val loadUnBondingDeferred = async { walletRepository.unBondingValidator(channel) }
//
//            val bondedValidatorsResult = loadBondedDeferred.await()
//            if (bondedValidatorsResult is NetworkResult.Success) {
//                bondedValidatorsResult.data.let { data ->
//                    if (data is Collection<*>) {
//                        tempValidators.addAll(data as Collection<StakingProto.Validator>)
//                    }
//                }
//            }
//
//            val unBondedValidatorsResult = loadUnBondedDeferred.await()
//            if (unBondedValidatorsResult is NetworkResult.Success) {
//                unBondedValidatorsResult.data.let { data ->
//                    if (data is Collection<*>) {
//                        tempValidators.addAll(data as Collection<StakingProto.Validator>)
//                    }
//                }
//            }
//
//            val unBondingValidatorsResult = loadUnBondingDeferred.await()
//            if (unBondingValidatorsResult is NetworkResult.Success) {
//                unBondingValidatorsResult.data.let { data ->
//                    if (data is Collection<*>) {
//                        tempValidators.addAll(data as Collection<StakingProto.Validator>)
//                    }
//                }
//            }
//
//            val dataTempValidators = tempValidators.toMutableList()
//            dataTempValidators.sortWith { o1, o2 ->
//                when {
//                    o1.description.moniker == "Cosmostation" -> -1
//                    o2.description.moniker == "Cosmostation" -> 1
//                    o1.jailed && !o2.jailed -> 1
//                    !o1.jailed && o2.jailed -> -1
//                    o1.tokens.toDouble() > o2.tokens.toDouble() -> -1
//                    o1.tokens.toDouble() < o2.tokens.toDouble() -> 1
//                    else -> 0
//                }
//            }
//            line.cosmosValidators = dataTempValidators

        } finally {
//            channel.shutdown()
//            try {
//                if (!channel.awaitTermination(5, TimeUnit.SECONDS)) {
//                    channel.shutdownNow()
//                }
//            } catch (e: InterruptedException) {
//                e.printStackTrace()
//            }
        }
    }

    private var _balanceResult = MutableLiveData<String>()
    val balanceResult: LiveData<String> get() = _balanceResult

    fun evmBalance(line: EthereumLine) = viewModelScope.launch(Dispatchers.IO) {
        when (val response = walletRepository.evmBalance(line)) {
            is NetworkResult.Success -> {
                line.evmBalance = response.data.toBigDecimal()
                line.web3j = Web3j.build(HttpService(line.getEvmRpc()))
                line.fetched = true
                if (line.fetched) {
                    withContext(Dispatchers.Main) {
                        _balanceResult.value = line.tag
                    }
                }
            }

            is NetworkResult.Error -> {
                line.evmBalance = BigDecimal.ZERO
                line.web3j = null
                line.fetched = true
                if (line.fetched) {
                    withContext(Dispatchers.Main) {
                        _balanceResult.value = line.tag
                    }
                }
            }
        }
    }

    fun balance(line: CosmosLine) = viewModelScope.launch(Dispatchers.IO) {
        when (line) {
//            is ChainOkt996Keccak -> {
//                when (val response = walletRepository.oktAccountInfo(line)) {
//                    is NetworkResult.Success -> {
//                        line.oktLcdAccountInfo = response.data
//                        line.fetched = true
//                        if (line.fetched) {
//                            withContext(Dispatchers.Main) {
//                                _balanceResult.value = line.tag
//                            }
//                        }
//                    }
//
//                    is NetworkResult.Error -> {
//                        line.oktLcdAccountInfo = null
//                        line.fetched = true
//                        if (line.fetched) {
//                            withContext(Dispatchers.Main) {
//                                _balanceResult.value = line.tag
//                            }
//                        }
//                    }
//                }
//            }

            else -> {
//                val channel = getChannel(line)
//                when (val response = walletRepository.balance(channel, line)) {
//                    is NetworkResult.Success -> {
//                        response.data?.balancesList?.let {
//                            line.cosmosBalances = it
//                            line.fetched = true
//                            if (line.fetched) {
//                                withContext(Dispatchers.Main) {
//                                    _balanceResult.value = line.tag
//                                }
//                            }
//                        }
//                    }
//
//                    is NetworkResult.Error -> {
//                        line.cosmosBalances = null
//                        line.fetched = true
//                        if (line.fetched) {
//                            withContext(Dispatchers.Main) {
//                                _balanceResult.value = line.tag
//                            }
//                        }
//                    }
//                }
            }
        }
    }

    private var _pwCheckResult = MutableLiveData<String>()
    val pwCheckResult: LiveData<String> get() = _pwCheckResult
    fun checkPassword(input: String) = viewModelScope.launch(Dispatchers.IO) {
        val checkPw = AppDatabase.getInstance().passwordDao().selectAll()
        checkPw.forEach { password ->
            if (!CryptoHelper.verifyData(
                    input, password.resource, CosmostationConstants.ENCRYPT_PASSWORD_KEY
                )
            ) {
                _pwCheckResult.postValue(BaseConstant.FAIL)
            } else {
                _pwCheckResult.postValue(BaseConstant.SUCCESS)
            }
        }
    }

    private val _moonPayDataErrorMessage = MutableLiveData<String>()
    val moonPayDataErrorMessage: LiveData<String> get() = _moonPayDataErrorMessage

    private var _moonPayResult = MutableLiveData<String>()
    val moonPayResult: LiveData<String> get() = _moonPayResult
    fun moonPay(data: MoonPayReq) = CoroutineScope(Dispatchers.IO).launch {
        when (val response = walletRepository.moonPay(data)) {
            is NetworkResult.Success -> {
                _moonPayResult.postValue(response.data.body()?.signature)
            }

            is NetworkResult.Error -> {
                _moonPayDataErrorMessage.postValue("error type : ${response.errorType}  error message : ${response.errorMessage}")
            }
        }
    }

    var cw721ModelResult = SingleLiveEvent<String>()
    fun cw721AllTokens(line: CosmosLine, list: JsonObject) =
        viewModelScope.launch(Dispatchers.IO) {
            val channel = getChannel(line)

//            when (val response = walletRepository.cw721TokenIds(channel, line, list)) {
//                is NetworkResult.Success -> {
//                    response.data?.let { tokenIds ->
//                        if (tokenIds.size() > 0) {
//                            val jobs =
//                                tokenIds.asJsonObject["tokens"].asJsonArray.map { tokenIdElement ->
//                                    async {
//                                        val tokenId = tokenIdElement.asString
//                                        when (val tokenInfo = walletRepository.cw721TokenInfo(
//                                            channel, line, list, tokenId
//                                        )) {
//                                            is NetworkResult.Success -> {
//                                                when (val tokenDetail =
//                                                    walletRepository.cw721TokenDetail(
//                                                        line,
//                                                        list.asJsonObject["contractAddress"].asString,
//                                                        tokenId
//                                                    )) {
//                                                    is NetworkResult.Success -> {
//                                                        Cw721TokenModel(
//                                                            tokenId,
//                                                            tokenInfo.data,
//                                                            tokenDetail.data
//                                                        )
//                                                    }
//
//                                                    is NetworkResult.Error -> {
//                                                        Cw721TokenModel(
//                                                            tokenId,
//                                                            tokenInfo.data,
//                                                            null
//                                                        )
//                                                    }
//                                                }
//                                            }
//
//                                            is NetworkResult.Error -> {
//                                                null
//                                            }
//                                        }
//                                    }
//                                }
//                            val tokens = jobs.awaitAll().filterNotNull()
//                            if (tokens.isNotEmpty()) {
//                                line.cw721Models.add(Cw721Model(list, tokens.toMutableList()))
//                            }
//                            cw721ModelResult.postValue(line.tag)
//                        } else {
//                            cw721ModelResult.postValue(line.tag)
//                        }
//                    }
//                }
//
//                is NetworkResult.Error -> {
//                    _errorMessage.postValue("error type : ${response.errorType}  error message : ${response.errorMessage}")
//                }
//            }
        }

    private val _ecoSystemErrorMessage = MutableLiveData<String>()
    val ecoSystemErrorMessage: LiveData<String> get() = _ecoSystemErrorMessage

    private var _ecoSystemListResult = MutableLiveData<MutableList<JsonObject>?>()
    val ecoSystemListResult: LiveData<MutableList<JsonObject>?> get() = _ecoSystemListResult
    fun ecoSystemList(chain: String) = CoroutineScope(Dispatchers.IO).launch {
        when (val response = walletRepository.ecoSystem(chain)) {
            is NetworkResult.Success -> {
                _ecoSystemListResult.postValue(response.data)
            }

            is NetworkResult.Error -> {
                _ecoSystemErrorMessage.postValue("error type : ${response.errorType}  error message : ${response.errorMessage}")
            }
        }
    }
}