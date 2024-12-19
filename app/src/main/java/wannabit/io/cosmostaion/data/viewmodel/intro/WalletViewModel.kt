package wannabit.io.cosmostaion.data.viewmodel.intro

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cosmos.staking.v1beta1.StakingProto
import com.google.gson.JsonObject
import io.grpc.ManagedChannel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.web3j.protocol.Web3j
import org.web3j.protocol.http.HttpService
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.FetchState
import wannabit.io.cosmostaion.chain.evmClass.ChainOktEvm
import wannabit.io.cosmostaion.chain.majorClass.ChainBitCoin84
import wannabit.io.cosmostaion.chain.majorClass.ChainNamada
import wannabit.io.cosmostaion.chain.majorClass.ChainSui
import wannabit.io.cosmostaion.chain.majorClass.SUI_MAIN_DENOM
import wannabit.io.cosmostaion.chain.testnetClass.ChainInitiaTestnet
import wannabit.io.cosmostaion.common.BaseConstant
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.CosmostationConstants
import wannabit.io.cosmostaion.data.model.req.Cw721Model
import wannabit.io.cosmostaion.data.model.req.Cw721TokenModel
import wannabit.io.cosmostaion.data.model.req.MoonPayReq
import wannabit.io.cosmostaion.data.model.res.AppVersion
import wannabit.io.cosmostaion.data.model.res.AssetResponse
import wannabit.io.cosmostaion.data.model.res.NetworkResult
import wannabit.io.cosmostaion.data.model.res.NoticeResponse
import wannabit.io.cosmostaion.data.model.res.Token
import wannabit.io.cosmostaion.data.repository.wallet.WalletRepository
import wannabit.io.cosmostaion.data.viewmodel.event.SingleLiveEvent
import wannabit.io.cosmostaion.database.AppDatabase
import wannabit.io.cosmostaion.database.CryptoHelper
import wannabit.io.cosmostaion.database.model.Password
import java.math.BigDecimal
import java.util.concurrent.TimeUnit

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
                                BaseData.setLastParamTime()
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

    private val _chainDataErrorMessage = MutableLiveData<String>()
    val chainDataErrorMessage: LiveData<String> get() = _chainDataErrorMessage

    fun loadGrpcStakeData(
        chain: BaseChain
    ) = viewModelScope.launch(Dispatchers.IO) {
        val channel = chain.cosmosFetcher?.getChannel()
        when (chain) {
            is ChainNamada -> {
                when (val response = walletRepository.namadaValidators(chain)) {
                    is NetworkResult.Success -> {
                        val tempActiveValidators = mutableListOf<JsonObject>()
                        val tempInActiveValidators = mutableListOf<JsonObject>()
                        tempActiveValidators.addAll(response.data.filter { it["state"].asString == "consensus" })
                        tempInActiveValidators.addAll(response.data.filter { it["state"].asString != "consensus" })

                        tempActiveValidators.sortWith(compareBy<JsonObject> {
                            if (!it["name"].isJsonNull && it["name"].asString.lowercase() == "cosmostation") 0 else 1
                        }.thenBy { it["rank"].asInt })
                        chain.namadaFetcher()?.namadaValidators?.addAll(tempActiveValidators)

                        tempInActiveValidators.sortWith { o1, o2 ->
                            when {
                                o1["votingPower"].asString.toDouble() > o2["votingPower"].asString.toDouble() -> -1
                                o1["votingPower"].asString.toDouble() < o2["votingPower"].asString.toDouble() -> 1
                                else -> 0
                            }
                        }
                        chain.namadaFetcher()?.namadaValidators?.addAll(tempInActiveValidators)
                    }

                    is NetworkResult.Error -> {
                        _chainDataErrorMessage.postValue("error type : ${response.errorType}  error message : ${response.errorMessage}")
                    }
                }

            }

            is ChainInitiaTestnet -> {
                if (chain.initiaFetcher()?.initiaValidators?.isNotEmpty() == true) {
                    return@launch
                }
                val tempValidators = mutableListOf<com.initia.mstaking.v1.StakingProto.Validator>()
                try {
                    val loadBondedDeferred =
                        async { walletRepository.initiaBondedValidator(channel, chain) }
                    val loadUnBondedDeferred =
                        async { walletRepository.initiaUnBondedValidator(channel, chain) }
                    val loadUnBondingDeferred =
                        async { walletRepository.initiaUnBondingValidator(channel, chain) }

                    val bondedValidatorsResult = loadBondedDeferred.await()
                    if (bondedValidatorsResult is NetworkResult.Success) {
                        bondedValidatorsResult.data.let { data ->
                            if (data is Collection<*>) {
                                tempValidators.addAll(data as Collection<com.initia.mstaking.v1.StakingProto.Validator>)
                            }
                        }
                    }

                    val unBondedValidatorsResult = loadUnBondedDeferred.await()
                    if (unBondedValidatorsResult is NetworkResult.Success) {
                        unBondedValidatorsResult.data.let { data ->
                            if (data is Collection<*>) {
                                tempValidators.addAll(data as Collection<com.initia.mstaking.v1.StakingProto.Validator>)
                            }
                        }
                    }

                    val unBondingValidatorsResult = loadUnBondingDeferred.await()
                    if (unBondingValidatorsResult is NetworkResult.Success) {
                        unBondingValidatorsResult.data.let { data ->
                            if (data is Collection<*>) {
                                tempValidators.addAll(data as Collection<com.initia.mstaking.v1.StakingProto.Validator>)
                            }
                        }
                    }

                    chain.initiaFetcher()?.initiaOriginValidators?.addAll(tempValidators)

                    val dataTempValidators = tempValidators.toMutableList()
                    dataTempValidators.sortWith { o1, o2 ->
                        when {
                            o1.description.moniker == "Cosmostation" -> -1
                            o2.description.moniker == "Cosmostation" -> 1
                            o1.jailed && !o2.jailed -> 1
                            !o1.jailed && o2.jailed -> -1
                            o1.tokensList.first { it.denom == chain.stakeDenom }.amount.toDouble() > o2.tokensList.first { it.denom == chain.stakeDenom }.amount.toDouble() -> -1
                            o1.tokensList.first { it.denom == chain.stakeDenom }.amount.toDouble() < o2.tokensList.first { it.denom == chain.stakeDenom }.amount.toDouble() -> 1
                            else -> 0
                        }
                    }
                    chain.initiaFetcher()?.initiaValidators = dataTempValidators

                } finally {
                    channel?.shutdown()
                    try {
                        if (channel?.awaitTermination(5, TimeUnit.SECONDS) == false) {
                            channel.shutdownNow()
                        }
                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    }
                }

            }

            else -> {
                if (chain.cosmosFetcher?.cosmosValidators?.isNotEmpty() == true) {
                    return@launch
                }
                val tempValidators = mutableListOf<StakingProto.Validator>()
                try {
                    val loadBondedDeferred =
                        async { walletRepository.bondedValidator(channel, chain) }
                    val loadUnBondedDeferred =
                        async { walletRepository.unBondedValidator(channel, chain) }
                    val loadUnBondingDeferred =
                        async { walletRepository.unBondingValidator(channel, chain) }

                    val bondedValidatorsResult = loadBondedDeferred.await()
                    if (bondedValidatorsResult is NetworkResult.Success) {
                        bondedValidatorsResult.data.let { data ->
                            if (data is Collection<*>) {
                                tempValidators.addAll(data as Collection<StakingProto.Validator>)
                            }
                        }
                    }

                    val unBondedValidatorsResult = loadUnBondedDeferred.await()
                    if (unBondedValidatorsResult is NetworkResult.Success) {
                        unBondedValidatorsResult.data.let { data ->
                            if (data is Collection<*>) {
                                tempValidators.addAll(data as Collection<StakingProto.Validator>)
                            }
                        }
                    }

                    val unBondingValidatorsResult = loadUnBondingDeferred.await()
                    if (unBondingValidatorsResult is NetworkResult.Success) {
                        unBondingValidatorsResult.data.let { data ->
                            if (data is Collection<*>) {
                                tempValidators.addAll(data as Collection<StakingProto.Validator>)
                            }
                        }
                    }

                    chain.cosmosFetcher?.cosmosOriginValidators?.addAll(tempValidators)

                    val dataTempValidators = tempValidators.toMutableList()
                    dataTempValidators.sortWith { o1, o2 ->
                        when {
                            o1.description.moniker == "Cosmostation" -> -1
                            o2.description.moniker == "Cosmostation" -> 1
                            o1.jailed && !o2.jailed -> 1
                            !o1.jailed && o2.jailed -> -1
                            o1.tokens.toDouble() > o2.tokens.toDouble() -> -1
                            o1.tokens.toDouble() < o2.tokens.toDouble() -> 1
                            else -> 0
                        }
                    }
                    chain.cosmosFetcher?.cosmosValidators = dataTempValidators

                } finally {
                    channel?.shutdown()
                    try {
                        if (channel?.awaitTermination(5, TimeUnit.SECONDS) == false) {
                            channel.shutdownNow()
                        }
                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    }
                }
            }
        }
    }

    private val _editErc20Balance = MutableLiveData<String>()
    val editErc20Balance: LiveData<String> get() = _editErc20Balance

    fun erc20Balance(chain: BaseChain, token: Token) {
        viewModelScope.launch(Dispatchers.IO) {
            walletRepository.erc20Balance(chain, token)
            withContext(Dispatchers.Main) {
                _editErc20Balance.value = token.contract
            }
        }
    }

    private val _editCw20Balance = MutableLiveData<String>()
    val editCw20Balance: LiveData<String> get() = _editCw20Balance

    fun cw20Balance(channel: ManagedChannel?, chain: BaseChain, token: Token) {
        viewModelScope.launch(Dispatchers.IO) {
            walletRepository.cw20Balance(channel, chain, token)
            withContext(Dispatchers.Main) {
                _editCw20Balance.value = token.contract
            }
        }
    }

    private var _balanceResult = MutableLiveData<String>()
    val balanceResult: LiveData<String> get() = _balanceResult

    fun evmBalance(chain: BaseChain) = viewModelScope.launch(Dispatchers.IO) {
        chain.evmRpcFetcher()?.let {
            when (val response = walletRepository.evmBalance(chain)) {
                is NetworkResult.Success -> {
                    chain.evmRpcFetcher?.evmBalance = response.data.toBigDecimal()
                    chain.web3j = Web3j.build(HttpService(chain.evmRpcFetcher?.getEvmRpc()))
                    chain.fetchState = FetchState.SUCCESS
                    chain.coinCnt = chain.evmRpcFetcher?.valueCoinCnt() ?: 0
                    withContext(Dispatchers.Main) {
                        _balanceResult.value = chain.tag
                    }
                }

                is NetworkResult.Error -> {
                    chain.evmRpcFetcher?.evmBalance = BigDecimal.ZERO
                    chain.web3j = null
                    chain.fetchState = FetchState.FAIL
                    withContext(Dispatchers.Main) {
                        _balanceResult.value = chain.tag
                    }
                }
            }
        }
    }

    fun balance(chain: BaseChain) = viewModelScope.launch(Dispatchers.IO) {
        when (chain) {
            is ChainBitCoin84 -> {
                chain.apply {
                    btcFetcher()?.let { fetcher ->
                        when (val response = walletRepository.bitBalance(chain)) {
                            is NetworkResult.Success -> {
                                val address = response.data["address"].asString
                                if (address.uppercase() != chain.mainAddress.uppercase()) {
                                    fetchState = FetchState.FAIL
                                }

                                val chainFundedTxoSum =
                                    response.data["chain_stats"].asJsonObject["funded_txo_sum"].asLong.toBigDecimal()
                                val chainSpentTxoSum =
                                    response.data["chain_stats"].asJsonObject["spent_txo_sum"].asLong.toBigDecimal()
                                val mempoolFundedTxoSum =
                                    response.data["mempool_stats"].asJsonObject["funded_txo_sum"].asLong.toBigDecimal()
                                val mempoolSpentTxoSum =
                                    response.data["mempool_stats"].asJsonObject["spent_txo_sum"].asLong.toBigDecimal()

                                fetcher.btcBalances = chainFundedTxoSum.subtract(chainSpentTxoSum)
                                    .subtract(mempoolSpentTxoSum).max(
                                        BigDecimal.ZERO
                                    )
                                fetcher.btcPendingInput = mempoolFundedTxoSum
                                fetcher.btcPendingOutput = mempoolSpentTxoSum

                                fetchState = FetchState.SUCCESS
                                coinCnt =
                                    if (chain.btcFetcher()?.btcBalances == BigDecimal.ZERO && chain.btcFetcher()?.btcPendingInput == BigDecimal.ZERO) 0 else 1
                                withContext(Dispatchers.Main) {
                                    _balanceResult.value = chain.tag
                                }
                            }

                            is NetworkResult.Error -> {
                                fetchState = FetchState.FAIL
                                withContext(Dispatchers.Main) {
                                    _balanceResult.value = chain.tag
                                }
                            }
                        }
                    }
                }
            }

            is ChainSui -> {
                chain.suiFetcher()?.let { fetcher ->
                    chain.apply {
                        when (val response = walletRepository.suiBalance(fetcher, this)) {
                            is NetworkResult.Success -> {
                                fetcher.suiBalances.clear()
                                response.data?.get("result")?.asJsonArray?.forEach { balance ->
                                    val coinType = balance.asJsonObject["coinType"].asString
                                    val amount =
                                        balance.asJsonObject["totalBalance"].asString.toBigDecimal()
                                    fetcher.suiBalances = fetcher.suiBalances ?: mutableListOf()
                                    fetcher.suiBalances.add(Pair(coinType, amount))
                                }
                                fetcher.suiBalances.sortWith { o1, o2 ->
                                    when {
                                        o1.first == SUI_MAIN_DENOM -> -1
                                        o2.first == SUI_MAIN_DENOM -> 1
                                        else -> 0
                                    }
                                }

                                fetchState = FetchState.SUCCESS
                                coinCnt = fetcher.suiBalances.size
                                withContext(Dispatchers.Main) {
                                    _balanceResult.value = chain.tag
                                }
                            }

                            is NetworkResult.Error -> {
                                fetchState = FetchState.FAIL
                                withContext(Dispatchers.Main) {
                                    _balanceResult.value = chain.tag
                                }
                            }
                        }
                    }
                }
            }

            is ChainOktEvm -> {
                chain.oktFetcher()?.let {
                    when (val response = walletRepository.oktAccountInfo(chain)) {
                        is NetworkResult.Success -> {
                            chain.oktFetcher?.oktAccountInfo = response.data
                            chain.fetchState = FetchState.SUCCESS
                            chain.coinCnt =
                                chain.oktFetcher?.oktAccountInfo?.get("value")?.asJsonObject?.get("coins")?.asJsonArray?.size()
                                    ?: 0
                            withContext(Dispatchers.Main) {
                                _balanceResult.value = chain.tag
                            }
                        }

                        is NetworkResult.Error -> {
                            chain.oktFetcher?.oktAccountInfo = null
                            chain.fetchState = FetchState.FAIL
                            withContext(Dispatchers.Main) {
                                _balanceResult.value = chain.tag
                            }
                        }
                    }
                }
            }

            else -> {
                chain.cosmosFetcher()?.let { cosmosFetcher ->
                    val channel = cosmosFetcher.getChannel()
                    when (val response = walletRepository.balance(channel, chain)) {
                        is NetworkResult.Success -> {
                            chain.cosmosFetcher?.cosmosBalances = response.data
                            chain.fetchState = FetchState.SUCCESS
                            chain.coinCnt = chain.cosmosFetcher()?.valueCoinCnt() ?: 0
                            withContext(Dispatchers.Main) {
                                _balanceResult.value = chain.tag
                            }
                        }

                        is NetworkResult.Error -> {
                            chain.cosmosFetcher?.cosmosBalances = null
                            chain.fetchState = FetchState.FAIL
                            withContext(Dispatchers.Main) {
                                _balanceResult.value = chain.tag
                            }
                        }
                    }
                }
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
    fun cw721AllTokens(chain: BaseChain, list: JsonObject) = viewModelScope.launch(Dispatchers.IO) {
        val channel = chain.cosmosFetcher?.getChannel()
        when (val response = walletRepository.cw721TokenIds(channel, chain, list)) {
            is NetworkResult.Success -> {
                response.data?.let { tokenIds ->
                    if (tokenIds.size() > 0) {
                        val jobs =
                            tokenIds.asJsonObject["tokens"].asJsonArray.map { tokenIdElement ->
                                async {
                                    val tokenId = tokenIdElement.asString
                                    when (val tokenInfo = walletRepository.cw721TokenInfo(
                                        channel, chain, list, tokenId
                                    )) {
                                        is NetworkResult.Success -> {
                                            when (val tokenDetail =
                                                walletRepository.cw721TokenDetail(
                                                    chain,
                                                    list.asJsonObject["contractAddress"].asString,
                                                    tokenId
                                                )) {
                                                is NetworkResult.Success -> {
                                                    Cw721TokenModel(
                                                        tokenId, tokenInfo.data, tokenDetail.data
                                                    )
                                                }

                                                is NetworkResult.Error -> {
                                                    Cw721TokenModel(
                                                        tokenId, tokenInfo.data, null
                                                    )
                                                }
                                            }
                                        }

                                        is NetworkResult.Error -> {
                                            null
                                        }
                                    }
                                }
                            }
                        val tokens = jobs.awaitAll().filterNotNull()
                        if (tokens.isNotEmpty()) {
                            chain.cosmosFetcher?.cw721Models?.add(
                                Cw721Model(
                                    list, tokens.toMutableList()
                                )
                            )
                        }
                        cw721ModelResult.postValue(chain.tag)
                    } else {
                        cw721ModelResult.postValue(chain.tag)
                    }
                }
            }

            is NetworkResult.Error -> {
                _errorMessage.postValue("error type : ${response.errorType}  error message : ${response.errorMessage}")
            }
        }
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

    private val _noticeErrorMessage = MutableLiveData<String>()
    val noticeErrorMessage: LiveData<String> get() = _noticeErrorMessage

    private var _noticeDataResult = MutableLiveData<NoticeResponse?>()
    val noticeDataResult: LiveData<NoticeResponse?> get() = _noticeDataResult
    fun notice() = viewModelScope.launch(Dispatchers.IO) {
        when (val response = walletRepository.notice()) {
            is NetworkResult.Success -> {
                _noticeDataResult.postValue(response.data)
            }

            is NetworkResult.Error -> {
                _noticeErrorMessage.postValue("error type : ${response.errorType}  error message : ${response.errorMessage}")
            }
        }
    }
}