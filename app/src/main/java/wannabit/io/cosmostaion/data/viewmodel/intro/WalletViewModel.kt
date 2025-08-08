package wannabit.io.cosmostaion.data.viewmodel.intro

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.babylon.btcstaking.v1.QueryProto
import com.babylon.finality.v1.QueryProto.ActiveFinalityProvidersAtHeightResponse
import com.cosmos.base.v1beta1.CoinProto
import com.cosmos.staking.v1beta1.StakingProto
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import io.grpc.ManagedChannel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.bouncycastle.util.encoders.Base64
import org.web3j.protocol.Web3j
import org.web3j.protocol.http.HttpService
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.FetchState
import wannabit.io.cosmostaion.chain.cosmosClass.ChainBabylon
import wannabit.io.cosmostaion.chain.cosmosClass.ChainInitia
import wannabit.io.cosmostaion.chain.cosmosClass.ChainZenrock
import wannabit.io.cosmostaion.chain.evmClass.ChainOktEvm
import wannabit.io.cosmostaion.chain.fetcher.FinalityProvider
import wannabit.io.cosmostaion.chain.majorClass.ChainBitCoin86
import wannabit.io.cosmostaion.chain.majorClass.ChainIota
import wannabit.io.cosmostaion.chain.majorClass.ChainSui
import wannabit.io.cosmostaion.chain.majorClass.SUI_MAIN_DENOM
import wannabit.io.cosmostaion.chain.testnetClass.ChainBabylonTestnet
import wannabit.io.cosmostaion.chain.testnetClass.ChainGnoTestnet
import wannabit.io.cosmostaion.common.BaseConstant
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.CosmostationConstants
import wannabit.io.cosmostaion.common.formatJsonString
import wannabit.io.cosmostaion.common.regexWithNumberAndChar
import wannabit.io.cosmostaion.common.toHex
import wannabit.io.cosmostaion.data.model.req.Cw721Model
import wannabit.io.cosmostaion.data.model.req.Cw721TokenModel
import wannabit.io.cosmostaion.data.model.req.MoonPayReq
import wannabit.io.cosmostaion.data.model.res.AppVersion
import wannabit.io.cosmostaion.data.model.res.AssetResponse
import wannabit.io.cosmostaion.data.model.res.Cw20TokenResponse
import wannabit.io.cosmostaion.data.model.res.Cw721
import wannabit.io.cosmostaion.data.model.res.Cw721Response
import wannabit.io.cosmostaion.data.model.res.Erc20TokenResponse
import wannabit.io.cosmostaion.data.model.res.Grc20TokenResponse
import wannabit.io.cosmostaion.data.model.res.NetworkResult
import wannabit.io.cosmostaion.data.model.res.NoticeResponse
import wannabit.io.cosmostaion.data.model.res.Token
import wannabit.io.cosmostaion.data.model.res.getAvailableUtxosFromRaw
import wannabit.io.cosmostaion.data.model.res.printAvailableUtxosJson
import wannabit.io.cosmostaion.data.repository.wallet.WalletRepository
import wannabit.io.cosmostaion.data.viewmodel.event.SingleLiveEvent
import wannabit.io.cosmostaion.database.AppDatabase
import wannabit.io.cosmostaion.database.CryptoHelper
import wannabit.io.cosmostaion.database.Prefs
import wannabit.io.cosmostaion.database.model.Password
import wannabit.io.cosmostaion.sign.BitcoinJs
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
        val loadCw20Deferred = async { walletRepository.cw20() }
        val loadErc20Deferred = async { walletRepository.erc20() }
        val loadGrc20Deferred = async { walletRepository.grc20() }
        val loadCw721Deferred = async { walletRepository.cw721() }
        val loadEcoSystemDeferred = async { walletRepository.ecoSystemInfo() }

        val responses = awaitAll(
            loadParamDeferred,
            loadAssetDeferred,
            loadCw20Deferred,
            loadErc20Deferred,
            loadGrc20Deferred,
            loadCw721Deferred,
            loadEcoSystemDeferred
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

                        is Cw20TokenResponse -> {
                            response.data.assets?.let { assets ->
                                assets.forEach { it.type = "cw20" }
                                BaseData.cw20Tokens = assets
                            }
                        }

                        is Erc20TokenResponse -> {
                            response.data.assets?.let { assets ->
                                assets.forEach { it.type = "erc20" }
                                BaseData.erc20Tokens = assets }
                        }

                        is Grc20TokenResponse -> {
                            response.data.assets?.let { assets ->
                                assets.forEach { it.type = "grc20" }
                                BaseData.grc20Tokens = assets
                            }
                        }

                        is Cw721Response -> {
                            response.data.assets?.let { BaseData.cw721Tokens = it }
                        }

                        is MutableList<*> -> {
                            if (response.data.isEmpty()) {
                                mutableListOf<JsonObject>()
                            } else {
                                BaseData.originEcosystems?.clear()
                                BaseData.originEcosystems?.addAll(response.data as MutableList<JsonObject>)

                                val ecoList = response.data as MutableList<JsonObject>
                                ecoList.forEach { ecosystem ->
                                    val isPinnedValue =
                                        Prefs.getPinnedDapps().contains(ecosystem["id"].asInt)
                                    ecosystem.addProperty("isPinned", isPinnedValue)
                                }

                                if (Prefs.dappFilter == 0) {
                                    ecoList.sortWith(compareBy { it["name"].asString })
                                } else {
                                    ecoList.sortWith(compareByDescending<JsonObject> { ecosystem ->
                                        ecosystem["chains"].asJsonArray.size()
                                    }.thenBy { ecosystem -> ecosystem["name"].asString })
                                }
                                BaseData.ecosystems = ecoList
                            }
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

    fun loadBtcStakeData(chain: ChainBitCoin86) = viewModelScope.launch(Dispatchers.IO) {
        val channel = if (chain.isTestnet) {
            ChainBabylonTestnet().cosmosFetcher()?.getChannel()
        } else {
            ChainBabylon().cosmosFetcher()?.getChannel()
        }

        if (chain.btcFetcher()?.btcFinalityProviders?.isNotEmpty() == true) {
            return@launch
        }

        var finalityVotingPower = mutableListOf<ActiveFinalityProvidersAtHeightResponse>()
        var finalityProviders = mutableListOf<QueryProto.FinalityProviderResponse>()
        val tempFinalityProviders = mutableListOf<FinalityProvider>()

        try {
            val loadStatusHeightDeferred = async { walletRepository.statusHeight(channel) }
            val loadFinalityProviderDeferred =
                async { walletRepository.btcFinalityProviders(channel) }
            val loadBtcParamsDeferred = async { walletRepository.btcParams(channel) }
            val loadFeeDeferred = async { walletRepository.btcFee(chain) }

            val statusHeightResult = loadStatusHeightDeferred.await()
            if (statusHeightResult is NetworkResult.Success) {
                when (val response = walletRepository.btcFinalityVotingPower(
                    chain, statusHeightResult.data
                )) {
                    is NetworkResult.Success -> {
                        finalityVotingPower = response.data
                    }

                    is NetworkResult.Error -> {
                        _chainDataErrorMessage.postValue("error type : ${response.errorType}  error message : ${response.errorMessage}")
                    }
                }
            }

            val finalityProviderResult = loadFinalityProviderDeferred.await()
            if (finalityProviderResult is NetworkResult.Success) {
                finalityProviders = finalityProviderResult.data
            }

            val btcParamsResult = loadBtcParamsDeferred.await()
            if (btcParamsResult is NetworkResult.Success) {
                chain.btcFetcher()?.btcParams = btcParamsResult.data
            }

            val btcFeeResult = loadFeeDeferred.await()
            if (btcFeeResult is NetworkResult.Success && btcFeeResult.data is JsonObject) {
                chain.btcFetcher()?.btcFastFee = btcFeeResult.data["fastestFee"].asLong
            } else {
                chain.btcFetcher()?.btcFastFee = 0L
            }

            withContext(Dispatchers.Default) {
                tempFinalityProviders.clear()
                if (finalityVotingPower.isNotEmpty() && finalityProviders.isNotEmpty()) {
                    finalityProviders.forEach { provider ->
                        val btcPk = provider.btcPk.toByteArray().toHex()
                        val votingPower =
                            finalityVotingPower.firstOrNull { it.btcPkHex == btcPk }?.votingPower
                        if (votingPower != null) {
                            tempFinalityProviders.add(
                                FinalityProvider(
                                    provider, votingPower.toString()
                                )
                            )

                        } else {
                            tempFinalityProviders.add(
                                FinalityProvider(
                                    provider, "0"
                                )
                            )
                        }
                    }
                }

                val dataTempProviders = tempFinalityProviders.toMutableList()
                dataTempProviders.sortWith { o1, o2 ->
                    when {
                        o1.provider.description.moniker == "Cosmostation" -> -1
                        o2.provider.description.moniker == "Cosmostation" -> 1
                        o1.provider.jailed && !o2.provider.jailed -> 1
                        !o1.provider.jailed && o2.provider.jailed -> -1
                        o1.votingPower.toLong() > o2.votingPower.toLong() -> -1
                        o1.votingPower.toLong() < o2.votingPower.toLong() -> 1
                        else -> 0
                    }
                }

                chain.btcFetcher()?.btcFinalityProviders = dataTempProviders

                val activeList: MutableList<JsonObject> = mutableListOf()
                val unBondingList: MutableList<JsonObject> = mutableListOf()
                val withdrawAbleList: MutableList<JsonObject> = mutableListOf()

                chain.btcFetcher()?.btcStakingData?.get("data")?.asJsonArray?.forEach { data ->
                    if (data.asJsonObject["state"].asString == "ACTIVE") {
                        activeList.add(data.asJsonObject)
                    }

                    if (data.asJsonObject["state"].asString == "EARLY_UNBONDING" || data.asJsonObject["state"].asString == "TIMELOCK_UNBONDING") {
                        unBondingList.add(data.asJsonObject)
                    }

                    if (data.asJsonObject["state"].asString.contains("EARLY_UNBONDING_WITHDRAWABLE")) {
                        withdrawAbleList.add(data.asJsonObject)
                    }
                }

                val matchedActivePairs: List<Pair<JsonObject, FinalityProvider>> =
                    activeList.mapNotNull { active ->
                        val pkHex =
                            active["finality_provider_btc_pks_hex"]?.asJsonArray?.firstOrNull()?.asString

                        val matchedProvider =
                            chain.btcFetcher()?.btcFinalityProviders?.firstOrNull { provider ->
                                provider.provider.btcPk.toByteArray().toHex() == pkHex
                            }

                        if (matchedProvider != null) {
                            Pair(active, matchedProvider)
                        } else {
                            null
                        }
                    }
                chain.btcFetcher()?.btcActiveStakingData = matchedActivePairs

                val matchedUnBondingPairs: List<Pair<JsonObject, FinalityProvider>> =
                    unBondingList.mapNotNull { active ->
                        val pkHex =
                            active["finality_provider_btc_pks_hex"]?.asJsonArray?.firstOrNull()?.asString

                        val matchedProvider =
                            chain.btcFetcher()?.btcFinalityProviders?.firstOrNull { provider ->
                                provider.provider.btcPk.toByteArray().toHex() == pkHex
                            }

                        if (matchedProvider != null) {
                            Pair(active, matchedProvider)
                        } else {
                            null
                        }
                    }
                chain.btcFetcher()?.btcUnBondingStakingData = matchedUnBondingPairs

                val matchedWithdrawPairs: List<Pair<JsonObject, FinalityProvider>> =
                    withdrawAbleList.mapNotNull { active ->
                        val pkHex =
                            active["finality_provider_btc_pks_hex"]?.asJsonArray?.firstOrNull()?.asString

                        val matchedProvider =
                            chain.btcFetcher()?.btcFinalityProviders?.firstOrNull { provider ->
                                provider.provider.btcPk.toByteArray().toHex() == pkHex
                            }

                        if (matchedProvider != null) {
                            Pair(active, matchedProvider)
                        } else {
                            null
                        }
                    }
                chain.btcFetcher()?.btcWithdrawAbleStakingData = matchedWithdrawPairs
            }

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

    private val _btcDataLoadDone = MutableLiveData<Boolean>()
    val btcDataLoadDone: LiveData<Boolean> = _btcDataLoadDone

    fun loadBtcDelegateData(
        chain: ChainBitCoin86
    ) = viewModelScope.launch(Dispatchers.IO) {
        chain.apply {
            try {
                val loadBtcClientTipHeightDeferred =
                    async { walletRepository.btcClientTip(this@apply) }
                val loadIsValidDeferred = async { walletRepository.mempoolIsValidAddress(chain) }
                val loadUtxoDeferred = async { walletRepository.mempoolUtxo(chain) }

                val btcClientTipHeightResult = loadBtcClientTipHeightDeferred.await()
                val isValidAddressResult = loadIsValidDeferred.await()
                val utxoResult = loadUtxoDeferred.await()

                if (btcClientTipHeightResult is NetworkResult.Success && btcClientTipHeightResult.data is JsonObject) {
                    btcFetcher()?.btcClientTipHeight =
                        btcClientTipHeightResult.data["header"].asJsonObject["height"].asLong
                } else {
                    btcFetcher()?.btcClientTipHeight = 0L
                }

                if (isValidAddressResult is NetworkResult.Success && isValidAddressResult.data is JsonObject) {
                    val isValid = isValidAddressResult.data
                    if (utxoResult is NetworkResult.Success) {
                        val originalUTxo = utxoResult.data

                        fun dummyHasInscription(txid: String, vout: Int): Boolean {
                            return false
                        }

                        val availableUTxo = getAvailableUtxosFromRaw(
                            originalUTxo.toString(), isValid.toString(), ::dummyHasInscription
                        )
                        btcFetcher()?.btcUtxo = printAvailableUtxosJson(availableUTxo)

                    } else {
                        btcFetcher()?.btcUtxo = ""
                    }

                }

                val network = if (chain.isTestnet) "testnet" else "mainnet"
                val privateKeyHex = chain.privateKey?.toHex()
                val signerAddress = chain.mainAddress
                val stakingParams =
                    chain.btcFetcher?.btcNetworkInfo?.get("data")?.asJsonObject?.get(
                        "params"
                    )?.asJsonObject?.get("bbn")?.asJsonArray

                val initBTCStakingFunction = """function initBTCStakingFunction() {
                                            const signMessage = initBTCStaking('${network}', '${privateKeyHex}', '${signerAddress}', '${stakingParams.toString()}');
                                            return signMessage;
                                        }""".trimMargin()
                BitcoinJs.mergeFunction(initBTCStakingFunction)
                BitcoinJs.executeFunction("initBTCStakingFunction()")

                _btcDataLoadDone.postValue(true)

            } catch (e: Exception) {
                _btcDataLoadDone.postValue(false)
            }
        }
    }

    private val _btcEstimateFee = MutableLiveData<String>()
    val btcEstimateFee: LiveData<String> = _btcEstimateFee

    fun btcEstimateFee(
        chain: ChainBitCoin86, bitcoinJs: BitcoinJs, finalityProvider: FinalityProvider?
    ) = viewModelScope.launch(Dispatchers.IO) {
        try {
            val feeRate = chain.btcFetcher?.btcFastFee
            val tipHeight = chain.btcFetcher?.btcClientTipHeight
            val provider = finalityProvider?.provider?.btcPk?.toByteArray()?.toHex()

            val stakerBtcInfo = JsonObject().apply {
                addProperty("address", chain.mainAddress)
                addProperty("stakerPublicKeyHex", chain.publicKey?.toHex())
            }

            val stakingInput = JsonObject().apply {
                addProperty("finalityProviderPkNoCoordHex", provider)
                addProperty("stakingAmountSat", chain.btcFetcher?.btcParams?.minStakingValueSat)
                addProperty(
                    "stakingTimelock", chain.btcFetcher?.btcParams?.maxStakingTimeBlocks?.toLong()
                )
            }

            val availableUTxo = JsonParser.parseString(chain.btcFetcher?.btcUtxo).asJsonArray
            val dpAvailableUTxo = GsonBuilder().setPrettyPrinting().create().toJson(availableUTxo)

            val estimateFeeFunction = """function estimateFeeFunction() {     
                    const estimate = estimateBtcStakingFee('${stakerBtcInfo}', ${tipHeight}, '${stakingInput}', `$dpAvailableUTxo`, ${feeRate});
                         return estimate;
                    }""".trimMargin()
            bitcoinJs.mergeFunction(estimateFeeFunction)
            val estimateFee = bitcoinJs.executeFunction("estimateFeeFunction()")

            _btcEstimateFee.postValue(estimateFee.toString())

        } catch (e: Exception) {
            _btcEstimateFee.postValue(e.message.toString())
        }
    }

    fun loadGrpcStakeData(
        chain: BaseChain
    ) = viewModelScope.launch(Dispatchers.IO) {
        val channel = chain.cosmosFetcher?.getChannel()
        when (chain) {
            is ChainInitia -> {
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

            is ChainZenrock -> {
                if (chain.zenrockFetcher()?.zenrockValidators?.isNotEmpty() == true) {
                    return@launch
                }
                val tempValidators =
                    mutableListOf<com.zrchain.validation.HybridValidationProto.ValidatorHV>()
                try {
                    val loadBondedDeferred =
                        async { walletRepository.zenrockBondedValidator(channel, chain) }
                    val loadUnBondedDeferred =
                        async { walletRepository.zenrockUnBondedValidator(channel, chain) }
                    val loadUnBondingDeferred =
                        async { walletRepository.zenrockUnBondingValidator(channel, chain) }

                    val bondedValidatorsResult = loadBondedDeferred.await()
                    if (bondedValidatorsResult is NetworkResult.Success) {
                        bondedValidatorsResult.data.let { data ->
                            if (data is Collection<*>) {
                                tempValidators.addAll(data as Collection<com.zrchain.validation.HybridValidationProto.ValidatorHV>)
                            }
                        }
                    }

                    val unBondedValidatorsResult = loadUnBondedDeferred.await()
                    if (unBondedValidatorsResult is NetworkResult.Success) {
                        unBondedValidatorsResult.data.let { data ->
                            if (data is Collection<*>) {
                                tempValidators.addAll(data as Collection<com.zrchain.validation.HybridValidationProto.ValidatorHV>)
                            }
                        }
                    }

                    val unBondingValidatorsResult = loadUnBondingDeferred.await()
                    if (unBondingValidatorsResult is NetworkResult.Success) {
                        unBondingValidatorsResult.data.let { data ->
                            if (data is Collection<*>) {
                                tempValidators.addAll(data as Collection<com.zrchain.validation.HybridValidationProto.ValidatorHV>)
                            }
                        }
                    }

                    chain.zenrockFetcher()?.zenrockOriginValidators?.addAll(tempValidators)

                    val dataTempValidators = tempValidators.toMutableList()
                    dataTempValidators.sortWith { o1, o2 ->
                        when {
                            o1.description.moniker == "Cosmostation" -> -1
                            o2.description.moniker == "Cosmostation" -> 1
                            o1.jailed && !o2.jailed -> 1
                            !o1.jailed && o2.jailed -> -1
                            o1.tokensNative.toDouble() > o2.tokensNative.toDouble() -> -1
                            o1.tokensNative.toDouble() < o2.tokensNative.toDouble() -> 1
                            else -> 0
                        }
                    }
                    chain.zenrockFetcher()?.zenrockValidators = dataTempValidators

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
                _editErc20Balance.value = token.address
            }
        }
    }

    private val _editGrc20Balance = MutableLiveData<String>()
    val editGrc20Balance: LiveData<String> get() = _editGrc20Balance

    fun grc20Balance(chain: BaseChain, token: Token) {
        viewModelScope.launch(Dispatchers.IO) {
            walletRepository.grc20Balance(chain, token)
            withContext(Dispatchers.Main) {
                _editGrc20Balance.value = token.address
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
            is ChainBitCoin86 -> {
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

            is ChainIota -> {
                chain.iotaFetcher()?.let { fetcher ->
                    chain.apply {
                        when (val response = walletRepository.iotaBalance(fetcher, this)) {
                            is NetworkResult.Success -> {
                                fetcher.iotaBalances.clear()
                                response.data?.get("result")?.asJsonArray?.forEach { balance ->
                                    val coinType = balance.asJsonObject["coinType"].asString
                                    val amount =
                                        balance.asJsonObject["totalBalance"].asString.toBigDecimal()
                                    fetcher.iotaBalances.add(Pair(coinType, amount))
                                }
                                fetcher.iotaBalances.sortWith { o1, o2 ->
                                    when {
                                        o1.first == SUI_MAIN_DENOM -> -1
                                        o2.first == SUI_MAIN_DENOM -> 1
                                        else -> 0
                                    }
                                }

                                fetchState = FetchState.SUCCESS
                                coinCnt = fetcher.iotaBalances.size
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
                if (chain is ChainGnoTestnet) {
                    chain.gnoRpcFetcher()?.let { fetcher ->
                        when (val response = walletRepository.rpcAuth(chain)) {
                            is NetworkResult.Success -> {
                                if (response.data.isSuccessful) {
                                    val tempBalances: MutableList<CoinProto.Coin> = mutableListOf()
                                    val jsonResponse = Gson().fromJson(
                                        response.data.body?.string(), JsonObject::class.java
                                    )
                                    val data =
                                        jsonResponse["result"].asJsonObject["response"].asJsonObject["ResponseBase"].asJsonObject["Data"].asString
                                    val decodeData = formatJsonString(String(Base64.decode(data)))
                                    if (decodeData == "null") {
                                        tempBalances.add(
                                            CoinProto.Coin.newBuilder().setDenom(chain.stakeDenom)
                                                .setAmount("0").build()
                                        )
                                        fetcher.gnoBalances = tempBalances
                                        chain.fetchState = FetchState.SUCCESS
                                        chain.coinCnt = 0
                                        withContext(Dispatchers.Main) {
                                            _balanceResult.value = chain.tag
                                        }

                                    } else {
                                        val dataJson =
                                            Gson().fromJson(decodeData, JsonObject::class.java)
                                        val accountData = dataJson["BaseAccount"].asJsonObject
                                        if (accountData["coins"].asString.isNotEmpty()) {
                                            tempBalances.add(
                                                CoinProto.Coin.newBuilder()
                                                    .setDenom(accountData["coins"].asString.regexWithNumberAndChar().first)
                                                    .setAmount(accountData["coins"].asString.regexWithNumberAndChar().second)
                                                    .build()
                                            )

                                        } else {
                                            tempBalances.add(
                                                CoinProto.Coin.newBuilder()
                                                    .setDenom(chain.stakeDenom).setAmount("0")
                                                    .build()
                                            )
                                        }

                                        fetcher.gnoBalances = tempBalances
                                        chain.fetchState = FetchState.SUCCESS
                                        chain.coinCnt =
                                            if (fetcher.gnoBalances?.get(0)?.amount?.toBigDecimal() == BigDecimal.ZERO) 0 else 1
                                        withContext(Dispatchers.Main) {
                                            _balanceResult.value = chain.tag
                                        }
                                    }

                                } else {
                                    fetcher.gnoBalances = null
                                    chain.fetchState = FetchState.FAIL
                                    withContext(Dispatchers.Main) {
                                        _balanceResult.value = chain.tag
                                    }
                                }
                            }

                            is NetworkResult.Error -> {
                                fetcher.gnoBalances = null
                                chain.fetchState = FetchState.FAIL
                                withContext(Dispatchers.Main) {
                                    _balanceResult.value = chain.tag
                                }
                            }
                        }
                    }

                } else {
                    chain.cosmosFetcher()?.let { cosmosFetcher ->
                        val channel = cosmosFetcher.getChannel()
                        when (val response = walletRepository.balance(channel, chain)) {
                            is NetworkResult.Success -> {
                                chain.cosmosFetcher?.cosmosBalances = response.data
                                chain.fetchState = FetchState.SUCCESS
                                chain.coinCnt = chain.cosmosFetcher?.cosmosBalances?.count { BaseData.getAsset(chain.apiName, it.denom) != null } ?: 0
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
    fun cw721AllTokens(chain: BaseChain, list: Cw721) = viewModelScope.launch(Dispatchers.IO) {
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
                                                    chain, list.contractAddress, tokenId
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