package wannabit.io.cosmostaion.data.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.cosmos.base.v1beta1.CoinProto
import com.google.gson.Gson
import com.google.gson.JsonObject
import io.grpc.ManagedChannel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.bouncycastle.util.encoders.Base64
import org.web3j.protocol.Web3j
import org.web3j.protocol.http.HttpService
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.FetchState
import wannabit.io.cosmostaion.chain.cosmosClass.ChainNeutron
import wannabit.io.cosmostaion.chain.cosmosClass.ChainOkt996Keccak
import wannabit.io.cosmostaion.chain.cosmosClass.ChainZenrock
import wannabit.io.cosmostaion.chain.evmClass.ChainOktEvm
import wannabit.io.cosmostaion.chain.fetcher.suiCoinType
import wannabit.io.cosmostaion.chain.majorClass.ChainBitCoin86
import wannabit.io.cosmostaion.chain.majorClass.ChainSui
import wannabit.io.cosmostaion.chain.majorClass.SUI_MAIN_DENOM
import wannabit.io.cosmostaion.chain.testnetClass.ChainGnoTestnet
import wannabit.io.cosmostaion.chain.testnetClass.ChainInitiaTestnet
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.BaseUtils
import wannabit.io.cosmostaion.common.ByteUtils
import wannabit.io.cosmostaion.common.formatJsonString
import wannabit.io.cosmostaion.common.regexWithNumberAndChar
import wannabit.io.cosmostaion.data.model.res.NetworkResult
import wannabit.io.cosmostaion.data.model.res.Token
import wannabit.io.cosmostaion.data.model.res.VestingData
import wannabit.io.cosmostaion.data.repository.wallet.WalletRepository
import wannabit.io.cosmostaion.data.viewmodel.event.SingleLiveEvent
import wannabit.io.cosmostaion.database.Prefs
import wannabit.io.cosmostaion.database.model.BaseAccount
import wannabit.io.cosmostaion.database.model.RefAddress
import wannabit.io.cosmostaion.ui.main.CosmostationApp
import java.math.BigDecimal
import java.util.concurrent.TimeUnit

class ApplicationViewModel(
    application: Application, private val walletRepository: WalletRepository
) : AndroidViewModel(application) {
    companion object {
        val shared
            get() = CosmostationApp.instance.applicationViewModel
    }

    private val _chainDataErrorMessage = MutableLiveData<String>()
    val chainDataErrorMessage: LiveData<String> get() = _chainDataErrorMessage

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
                }
            }

            is NetworkResult.Error -> {
                _chainDataErrorMessage.postValue("error type : ${response.errorType}  error message : ${response.errorMessage}")
            }
        }

        when (val response = walletRepository.usdPrice()) {
            is NetworkResult.Success -> {
                response.data.let { data ->
                    BaseData.usdPrices = data
                }
            }

            is NetworkResult.Error -> {
                _chainDataErrorMessage.postValue("error type : ${response.errorType}  error message : ${response.errorMessage}")
            }
        }
    }

    private val _updateParamResult = MutableLiveData<Boolean>()
    val updateParamResult: LiveData<Boolean> get() = _updateParamResult
    fun param() = viewModelScope.launch(Dispatchers.IO) {
        if (!BaseData.paramUpdateIfNeed()) {
            return@launch
        }
        when (val response = walletRepository.param()) {
            is NetworkResult.Success -> {
                BaseData.chainParam = response.data
                BaseData.setLastParamTime()
                _updateParamResult.postValue(true)
            }

            is NetworkResult.Error -> {
                _chainDataErrorMessage.postValue("error type : ${response.errorType}  error message : ${response.errorMessage}")
            }
        }
    }

    private var _currentAccountResult = MutableLiveData<Pair<Boolean, BaseAccount?>>()
    val currentAccountResult: LiveData<Pair<Boolean, BaseAccount?>> get() = _currentAccountResult
    fun currentAccount(baseAccount: BaseAccount?, isNew: Boolean) =
        viewModelScope.launch(Dispatchers.IO) {
            _currentAccountResult.postValue(Pair(isNew, baseAccount))
        }

    private var _changeNameResult = MutableLiveData<BaseAccount?>()
    val changeNameResult: LiveData<BaseAccount?> get() = _changeNameResult
    fun changeName(baseAccount: BaseAccount?) = viewModelScope.launch(Dispatchers.IO) {
        _changeNameResult.postValue(baseAccount)
    }

    var txRecreateResult = SingleLiveEvent<Int>()
    fun txRecreate(page: Int) = viewModelScope.launch(Dispatchers.IO) {
        txRecreateResult.postValue(page)
    }

    var walletEditResult = SingleLiveEvent<MutableList<String>>()
    fun walletEdit(
        displayChains: MutableList<String>
    ) = viewModelScope.launch(Dispatchers.IO) {
        walletEditResult.postValue(displayChains)
    }

    private var _hideValueResult = MutableLiveData<Boolean>()
    val hideValueResult: LiveData<Boolean> get() = _hideValueResult
    fun hideValue() = CoroutineScope(Dispatchers.IO).launch {
        _hideValueResult.postValue(true)
    }

    var displayLegacyResult = SingleLiveEvent<Boolean>()
    fun displayLegacy(isDisplay: Boolean) = viewModelScope.launch(Dispatchers.IO) {
        displayLegacyResult.postValue(isDisplay)
    }

    var displayTestnetResult = SingleLiveEvent<Boolean>()
    fun displayTestnet(isDisplay: Boolean) = viewModelScope.launch(Dispatchers.IO) {
        displayTestnetResult.postValue(isDisplay)
    }

    var styleOptionResult = SingleLiveEvent<Boolean>()
    fun styleOption(isChanged: Boolean) = viewModelScope.launch(Dispatchers.IO) {
        styleOptionResult.postValue(isChanged)
    }

    private var _changeBgResult = MutableLiveData<Int>()
    val changeBgResult: LiveData<Int> get() = _changeBgResult
    fun changeBg(bg: Int) = viewModelScope.launch(Dispatchers.IO) {
        _changeBgResult.postValue(bg)
    }

    var serviceTxResult = SingleLiveEvent<Boolean>()
    fun serviceTx() = viewModelScope.launch(Dispatchers.IO) {
        serviceTxResult.postValue(true)
    }

    private var _filterDataResult = MutableLiveData<Boolean>()
    val filterDataResult: LiveData<Boolean> get() = _filterDataResult
    fun updateFilterData(isShowAll: Boolean) = viewModelScope.launch(Dispatchers.IO) {
        _filterDataResult.postValue(isShowAll)
    }

    private var _chainFilterResult = MutableLiveData<Boolean>()
    val chainFilterResult: LiveData<Boolean> get() = _chainFilterResult
    fun chainFilter(isValueFilter: Boolean) = CoroutineScope(Dispatchers.IO).launch {
        _chainFilterResult.postValue(isValueFilter)
    }

    fun loadChainData(
        chain: BaseChain,
        baseAccountId: Long,
        isEdit: Boolean? = false,
        isTx: Boolean? = false,
        isRefresh: Boolean? = false
    ) = CoroutineScope(Dispatchers.IO).launch {
        chain.apply {
            fetchState = FetchState.BUSY
            cosmosFetcher()?.let {
                if (isSupportCw20()) {
                    when (val response = walletRepository.token(this)) {
                        is NetworkResult.Success -> {
                            cosmosFetcher?.tokens = response.data
                        }

                        is NetworkResult.Error -> {
                            _chainDataErrorMessage.postValue("error type : ${response.errorType}  error message : ${response.errorMessage}")
                        }
                    }
                }

                if (isSupportErc20()) {
                    when (val response = walletRepository.evmToken(this)) {
                        is NetworkResult.Success -> {
                            chain.evmRpcFetcher()?.evmTokens = response.data
                        }

                        is NetworkResult.Error -> {
                            _chainDataErrorMessage.postValue("error type : ${response.errorType}  error message : ${response.errorMessage}")
                        }
                    }
                }

                if (isSupportGrc20()) {
                    when (val response = walletRepository.token(this)) {
                        is NetworkResult.Success -> {
                            (chain as ChainGnoTestnet).gnoRpcFetcher()?.grc20Tokens = response.data
                        }

                        is NetworkResult.Error -> {
                            _chainDataErrorMessage.postValue("error type : ${response.errorType}  error message : ${response.errorMessage}")
                        }
                    }
                }

                if (isSupportCw721()) {
                    when (val response = walletRepository.cw721Info(apiName)) {
                        is NetworkResult.Success -> {
                            val data = response.data["assets"].asJsonArray
                            data.forEach {
                                cosmosFetcher?.cw721s?.add(it.asJsonObject)
                            }
                        }

                        is NetworkResult.Error -> {
                            _chainDataErrorMessage.postValue("error type : ${response.errorType}  error message : ${response.errorMessage}")
                        }
                    }
                }
            }

            if (this is ChainBitCoin86) {
                loadBtcData(baseAccountId, this, isEdit)
            } else if (this is ChainOkt996Keccak) {
                loadOktLcdData(this, baseAccountId, isEdit)
            } else if (this is ChainSui) {
                loadSuiData(baseAccountId, this, isEdit, isTx, isRefresh)
            } else {
                if (this is ChainGnoTestnet) {
                    loadRpcData(this, baseAccountId, isEdit)
                } else if (supportCosmos() && this !is ChainOktEvm) {
                    loadGrpcAuthData(this, baseAccountId, isEdit, isTx, isRefresh)
                } else {
                    loadEvmChainData(this, baseAccountId, isEdit)
                }
            }
        }
    }

    var fetchedResult = SingleLiveEvent<String>()
    var fetchedTokenResult = SingleLiveEvent<String>()

    var fetchedTotalResult = SingleLiveEvent<String>()

    var editFetchedResult = SingleLiveEvent<String>()
    var editFetchedTokenResult = SingleLiveEvent<String>()

    var refreshStakingInfoFetchedResult = SingleLiveEvent<String>()

    var txFetchedResult = SingleLiveEvent<String>()

    val notifyTxResult = SingleLiveEvent<Unit>()
    val notifyRefreshResult = SingleLiveEvent<Unit>()

    fun notifyTxEvent() {
        notifyTxResult.postValue(Unit)
    }

    fun notifyRefreshEvent() {
        notifyRefreshResult.postValue(Unit)
    }

    private fun loadGrpcAuthData(
        chain: BaseChain,
        baseAccountId: Long,
        isEdit: Boolean? = false,
        isTx: Boolean? = false,
        isRefresh: Boolean? = false
    ) = CoroutineScope(Dispatchers.IO).launch {
        chain.apply {
            val channel = cosmosFetcher?.getChannel()
            when (walletRepository.auth(channel, this)) {
                is NetworkResult.Success -> {
                    loadGrpcMoreData(channel, baseAccountId, this, isEdit, isTx, isRefresh)
                }

                is NetworkResult.Error -> {
                    val loadBalanceDeferred =
                        async { walletRepository.balance(channel, this@apply) }
                    val loadRewardAddressDeferred =
                        async { walletRepository.rewardAddress(channel, this@apply) }
                    val loadFeeMarketDeferred =
                        async { walletRepository.baseFee(channel, this@apply) }

                    val results = awaitAll(
                        loadBalanceDeferred, loadRewardAddressDeferred, loadFeeMarketDeferred
                    )

                    results.forEach { result ->
                        when (result) {
                            is NetworkResult.Success -> {
                                when (result.data) {
                                    is MutableList<*> -> {
                                        if (result.data.all { it is CoinProto.Coin }) {
                                            cosmosFetcher?.cosmosBalances =
                                                result.data as MutableList<CoinProto.Coin>

                                        } else if (result.data.all { it is CoinProto.DecCoin }) {
                                            cosmosFetcher?.cosmosBaseFees?.clear()
                                            (result.data as MutableList<CoinProto.DecCoin>).forEach { baseFee ->
                                                if (BaseData.getAsset(
                                                        apiName, baseFee.denom
                                                    ) != null
                                                ) {
                                                    cosmosFetcher?.cosmosBaseFees?.add(baseFee)
                                                }
                                            }
                                            cosmosFetcher?.cosmosBaseFees?.sortWith { o1, o2 ->
                                                if (o1.denom == chain.stakeDenom && o2.denom != chain.stakeDenom) {
                                                    -1
                                                } else {
                                                    0
                                                }
                                            }
                                        }
                                    }

                                    is String -> {
                                        cosmosFetcher?.rewardAddress = result.data
                                    }

                                }
                            }

                            is NetworkResult.Error -> {
                                _chainDataErrorMessage.postValue("error type : ${result.errorType}  error message : ${result.errorMessage}")
                            }

                            else -> {}
                        }
                    }

                    delay(2000)
                    fetchState = if (cosmosFetcher?.cosmosBalances == null) {
                        FetchState.FAIL
                    } else {
                        FetchState.SUCCESS
                    }

                    if (fetchState == FetchState.SUCCESS) {
                        val refAddress = RefAddress(
                            baseAccountId,
                            tag,
                            address,
                            ByteUtils.convertBech32ToEvm(address),
                            "0",
                            "0",
                            "0",
                            0
                        )
                        BaseData.updateRefAddressesMain(refAddress)
                    }
                    withContext(Dispatchers.Main) {
                        if (isEdit == true) {
                            editFetchedResult.value = tag
                        } else {
                            fetchedResult.value = tag
                        }
                    }
                }
            }
        }
    }

    private fun loadGrpcMoreData(
        channel: ManagedChannel?,
        id: Long,
        chain: BaseChain,
        isEdit: Boolean? = false,
        isTx: Boolean? = false,
        isRefresh: Boolean? = false
    ) = CoroutineScope(Dispatchers.IO).launch {
        chain.apply {
            try {
                if (chain.supportEvm) {
                    evmRpcFetcher()?.let { evmRpcFetcher ->
                        val loadEvmTokenDeferred = async { walletRepository.evmToken(this@apply) }
                        val loadEvmBalanceDeferred =
                            async { walletRepository.evmBalance(this@apply) }

                        val tokenResult = loadEvmTokenDeferred.await()
                        val balanceResult = loadEvmBalanceDeferred.await()

                        if (tokenResult is NetworkResult.Success && tokenResult.data is MutableList<*> && tokenResult.data.all { it is Token }) {
                            evmRpcFetcher.evmTokens = tokenResult.data
                        }

                        if (balanceResult is NetworkResult.Success && balanceResult.data is String) {
                            web3j = Web3j.build(HttpService(evmRpcFetcher.getEvmRpc()))
                            evmRpcFetcher.evmBalance = balanceResult.data.toBigDecimal()
                        } else if (balanceResult is NetworkResult.Error) {
                            web3j = null
                        }
                    }
                }

                val loadBalanceDeferred = async { walletRepository.balance(channel, chain) }
                if (this is ChainNeutron) {
                    val loadTokenInfoDeferred = async { walletRepository.token(this@apply) }
                    val loadVaultDepositDeferred =
                        async { walletRepository.vaultDeposit(channel, this@apply) }
                    val loadVestingDeferred =
                        async { walletRepository.vestingData(channel, this@apply) }
                    val loadFeeMarketDeferred =
                        async { walletRepository.baseFee(channel, this@apply) }

                    val responses = awaitAll(
                        loadBalanceDeferred,
                        loadTokenInfoDeferred,
                        loadVestingDeferred,
                        loadVaultDepositDeferred,
                        loadFeeMarketDeferred
                    )

                    responses.forEach { response ->
                        when (response) {
                            is NetworkResult.Success -> {
                                when (response.data) {
                                    is MutableList<*> -> {
                                        if (response.data.isEmpty()) {
                                            mutableListOf<CoinProto.Coin>()
                                        } else {
                                            if (response.data.all { it is Token }) {
                                                cosmosFetcher?.tokens =
                                                    response.data as MutableList<Token>

                                            } else if (response.data.all { it is CoinProto.Coin }) {
                                                cosmosFetcher?.cosmosBalances =
                                                    response.data as MutableList<CoinProto.Coin>

                                            } else if (response.data.all { it is CoinProto.DecCoin }) {
                                                cosmosFetcher?.cosmosBaseFees?.clear()
                                                (response.data as MutableList<CoinProto.DecCoin>).forEach { baseFee ->
                                                    if (BaseData.getAsset(
                                                            apiName, baseFee.denom
                                                        ) != null
                                                    ) {
                                                        cosmosFetcher?.cosmosBaseFees?.add(
                                                            baseFee
                                                        )
                                                    }
                                                }
                                                cosmosFetcher?.cosmosBaseFees?.sortWith { o1, o2 ->
                                                    if (o1.denom == chain.stakeDenom && o2.denom != chain.stakeDenom) {
                                                        -1
                                                    } else {
                                                        0
                                                    }
                                                }
                                            }
                                        }
                                    }

                                    is com.cosmwasm.wasm.v1.QueryProto.QuerySmartContractStateResponse -> {
                                        neutronFetcher()?.neutronVesting = Gson().fromJson(
                                            response.data.data.toStringUtf8(),
                                            VestingData::class.java
                                        )
                                    }

                                    else -> {
                                        neutronFetcher()?.neutronDeposited =
                                            response.data.toString().toBigDecimal()
                                    }
                                }
                            }

                            is NetworkResult.Error -> {
                                _chainDataErrorMessage.postValue("error type : ${response.errorType}  error message : ${response.errorMessage}")
                            }

                            else -> {}
                        }
                    }

                } else {
                    val loadRewardDeferred = async { walletRepository.reward(channel, chain) }
                    val loadRewardAddressDeferred =
                        async { walletRepository.rewardAddress(channel, chain) }
                    val loadFeeMarketDeferred = async { walletRepository.baseFee(channel, chain) }

                    if (chain is ChainInitiaTestnet) {
                        val loadDelegationDeferred =
                            async { walletRepository.initiaDelegation(channel, chain) }
                        val loadUnBondingDeferred =
                            async { walletRepository.initiaUnBonding(channel, chain) }

                        val delegationResult = loadDelegationDeferred.await()
                        val unBondingResult = loadUnBondingDeferred.await()

                        if (delegationResult is NetworkResult.Success && delegationResult.data is MutableList<*>) {
                            chain.initiaFetcher()?.initiaDelegations?.clear()
                            delegationResult.data.forEach { delegation ->
                                delegation.balanceList.filter { it.denom == chain.stakeDenom }
                                    .forEach { balance ->
                                        if (balance.amount.toBigDecimal() > BigDecimal.ZERO) {
                                            chain.initiaFetcher()?.initiaDelegations?.add(delegation)
                                        }
                                    }
                            }
                        } else if (delegationResult is NetworkResult.Error) {
                            _chainDataErrorMessage.postValue("error type : ${delegationResult.errorType}  error message : ${delegationResult.errorMessage}")
                        }

                        if (unBondingResult is NetworkResult.Success && unBondingResult.data is MutableList<*>) {
                            chain.initiaFetcher()?.initiaUnbondings = unBondingResult.data
                        } else if (unBondingResult is NetworkResult.Error) {
                            _chainDataErrorMessage.postValue("error type : ${unBondingResult.errorType}  error message : ${unBondingResult.errorMessage}")
                        }

                    } else if (chain is ChainZenrock) {
                        val loadDelegationDeferred =
                            async { walletRepository.zenrockDelegation(channel, chain) }
                        val loadUnBondingDeferred =
                            async { walletRepository.zenrockUnBonding(channel, chain) }

                        val delegationResult = loadDelegationDeferred.await()
                        val unBondingResult = loadUnBondingDeferred.await()

                        if (delegationResult is NetworkResult.Success && delegationResult.data is MutableList<*>) {
                            chain.zenrockFetcher()?.zenrockDelegations?.clear()
                            delegationResult.data.forEach { delegation ->
                                if (delegation.balance.amount.toBigDecimal() > BigDecimal.ZERO) {
                                    chain.zenrockFetcher()?.zenrockDelegations?.add(
                                        delegation
                                    )
                                }
                            }

                        } else if (delegationResult is NetworkResult.Error) {
                            _chainDataErrorMessage.postValue("error type : ${delegationResult.errorType}  error message : ${delegationResult.errorMessage}")
                        }

                        if (unBondingResult is NetworkResult.Success && unBondingResult.data is MutableList<*>) {
                            chain.zenrockFetcher()?.zenrockUnbondings = unBondingResult.data
                        } else if (unBondingResult is NetworkResult.Error) {
                            _chainDataErrorMessage.postValue("error type : ${unBondingResult.errorType}  error message : ${unBondingResult.errorMessage}")
                        }

                    } else {
                        val loadDelegationDeferred =
                            async { walletRepository.delegation(channel, chain) }
                        val loadUnBondingDeferred =
                            async { walletRepository.unBonding(channel, chain) }

                        val delegationResult = loadDelegationDeferred.await()
                        val unBondingResult = loadUnBondingDeferred.await()

                        if (delegationResult is NetworkResult.Success && delegationResult.data is MutableList<*>) {
                            cosmosFetcher?.cosmosDelegations?.clear()
                            delegationResult.data.forEach { delegation ->
                                if (delegation.balance.amount.toBigDecimal() > BigDecimal.ZERO) {
                                    cosmosFetcher?.cosmosDelegations?.add(
                                        delegation
                                    )
                                }
                            }
                        } else if (delegationResult is NetworkResult.Error) {
                            _chainDataErrorMessage.postValue("error type : ${delegationResult.errorType}  error message : ${delegationResult.errorMessage}")
                        }

                        if (unBondingResult is NetworkResult.Success && unBondingResult.data is MutableList<*>) {
                            cosmosFetcher?.cosmosUnbondings = unBondingResult.data
                        } else if (unBondingResult is NetworkResult.Error) {
                            _chainDataErrorMessage.postValue("error type : ${unBondingResult.errorType}  error message : ${unBondingResult.errorMessage}")
                        }
                    }

                    val balanceResult = loadBalanceDeferred.await()
                    val rewardResult = loadRewardDeferred.await()
                    val rewardAddressResult = loadRewardAddressDeferred.await()
                    val feeMarketResult = loadFeeMarketDeferred.await()

                    if (balanceResult is NetworkResult.Success && balanceResult.data is MutableList<*>) {
                        cosmosFetcher?.cosmosBalances = balanceResult.data
                    } else if (balanceResult is NetworkResult.Error) {
                        _chainDataErrorMessage.postValue("error type : ${balanceResult.errorType}  error message : ${balanceResult.errorMessage}")
                    }

                    if (rewardResult is NetworkResult.Success && rewardResult.data is MutableList<*>) {
                        cosmosFetcher?.cosmosRewards = rewardResult.data
                    } else if (rewardResult is NetworkResult.Error) {
                        _chainDataErrorMessage.postValue("error type : ${rewardResult.errorType}  error message : ${rewardResult.errorMessage}")
                    }

                    if (rewardAddressResult is NetworkResult.Success && rewardAddressResult.data is String) {
                        cosmosFetcher?.rewardAddress = rewardAddressResult.data
                    } else if (rewardAddressResult is NetworkResult.Error) {
                        _chainDataErrorMessage.postValue("error type : ${rewardAddressResult.errorType}  error message : ${rewardAddressResult.errorMessage}")
                    }

                    if (feeMarketResult is NetworkResult.Success && feeMarketResult.data is MutableList<*>) {
                        cosmosFetcher?.cosmosBaseFees?.clear()
                        feeMarketResult.data.forEach { baseFee ->
                            if (BaseData.getAsset(
                                    apiName, baseFee.denom
                                ) != null
                            ) {
                                cosmosFetcher?.cosmosBaseFees?.add(baseFee)
                            }
                        }
                        cosmosFetcher?.cosmosBaseFees?.sortWith { o1, o2 ->
                            if (o1.denom == chain.stakeDenom && o2.denom != chain.stakeDenom) {
                                -1
                            } else {
                                0
                            }
                        }
                    } else if (feeMarketResult is NetworkResult.Error) {
                        _chainDataErrorMessage.postValue("error type : ${feeMarketResult.errorType}  error message : ${feeMarketResult.errorMessage}")
                    }
                }

                fetchState = when {
                    cosmosFetcher?.cosmosBalances == null -> FetchState.FAIL
                    supportEvm && web3j == null -> FetchState.FAIL
                    else -> FetchState.SUCCESS
                }

                if (fetchState == FetchState.SUCCESS) {
                    BaseUtils.onParseVesting(this)
                    val refAddress = RefAddress(
                        id,
                        tag,
                        address,
                        evmAddress,
                        cosmosFetcher?.allAssetValue(true).toString(),
                        cosmosFetcher?.allStakingDenomAmount().toString(),
                        "0",
                        cosmosFetcher?.cosmosBalances?.count {
                            BaseData.getAsset(
                                apiName, it.denom
                            ) != null
                        }?.toLong()
                    )
                    BaseData.updateRefAddressesMain(refAddress)
                    coinCnt = chain.cosmosFetcher()?.valueCoinCnt() ?: 0
                    withContext(Dispatchers.Main) {
                        if (isEdit == true) {
                            editFetchedResult.value = tag
                        } else if (isTx == true) {
                            txFetchedResult.value = tag
                        } else if (isRefresh == true) {
                            refreshStakingInfoFetchedResult.value = tag
                        } else {
                            fetchedResult.value = tag
                        }
                    }

                    if (isSupportCw20()) {
                        val userDisplayToken = Prefs.getDisplayCw20s(id, tag)
                        val tokenBalanceDeferredList = if (userDisplayToken == null) {
                            cosmosFetcher?.tokens?.filter { it.wallet_preload ?: false }
                                ?.map { token ->
                                    async { walletRepository.cw20Balance(channel, chain, token) }
                                }

                        } else {
                            cosmosFetcher?.tokens?.filter { userDisplayToken.contains(it.contract) }
                                ?.map { token ->
                                    async { walletRepository.cw20Balance(channel, chain, token) }
                                }
                        }

                        tokenBalanceDeferredList?.awaitAll()
                        val cwRefAddress = RefAddress(
                            id,
                            tag,
                            address,
                            evmAddress,
                            "0",
                            "0",
                            cosmosFetcher?.allTokenValue(true)?.toPlainString(),
                            0
                        )
                        BaseData.updateRefAddressesToken(cwRefAddress)
                        tokenCnt = chain.cosmosFetcher()?.valueTokenCnt() ?: 0
                    }

                    if (isSupportErc20()) {
                        if (web3j != null) {
                            evmRpcFetcher()?.let { evmRpcFetcher ->
                                val userDisplayToken = Prefs.getDisplayErc20s(id, tag)
                                val tokenBalanceDeferredList = if (userDisplayToken == null) {
                                    evmRpcFetcher.evmTokens.filter { it.wallet_preload ?: false }
                                        .map { token ->
                                            async {
                                                walletRepository.erc20Balance(
                                                    this@apply, token
                                                )
                                            }
                                        }

                                } else {
                                    evmRpcFetcher.evmTokens.filter { userDisplayToken.contains(it.contract) }
                                        .map { token ->
                                            async {
                                                walletRepository.erc20Balance(
                                                    this@apply, token
                                                )
                                            }
                                        }
                                }

                                tokenBalanceDeferredList.awaitAll()
                                val evmRefAddress = RefAddress(
                                    id,
                                    tag,
                                    address,
                                    evmAddress,
                                    "0",
                                    "0",
                                    evmRpcFetcher.allTokenValue(true).toPlainString(),
                                    cosmosFetcher?.cosmosBalances?.count {
                                        BaseData.getAsset(
                                            apiName, it.denom
                                        ) != null
                                    }?.toLong() ?: 0L
                                )
                                BaseData.updateRefAddressesToken(evmRefAddress)
                                tokenCnt = if (Prefs.getDisplayErc20s(id, chain.tag) != null) {
                                    Prefs.getDisplayErc20s(id, chain.tag)?.size.toString().toInt()
                                } else {
                                    evmRpcFetcher.valueTokenCnt()
                                }
                            }
                        }
                    }
                    withContext(Dispatchers.Main) {
                        if (isEdit == true) {
                            editFetchedTokenResult.value = tag
                        } else {
                            fetchedTokenResult.value = tag
                        }
                    }
                    fetchedTotalResult.postValue(tag)

                } else if (fetchState == FetchState.FAIL) {
                    withContext(Dispatchers.Main) {
                        if (isEdit == true) {
                            editFetchedResult.value = tag
                        } else {
                            fetchedResult.value = tag
                        }
                    }
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
    }

    fun loadEvmChainData(chain: BaseChain, baseAccountId: Long, isEdit: Boolean? = false) =
        CoroutineScope(Dispatchers.IO).launch {
            chain.apply {
                evmRpcFetcher()?.let { evmRpcFetcher ->
                    val userDisplayToken = Prefs.getDisplayErc20s(baseAccountId, tag)
                    val loadEvmTokenDeferred = async { walletRepository.evmToken(this@apply) }
                    val loadEvmBalanceDeferred = async { walletRepository.evmBalance(this@apply) }

                    val tokenResult = loadEvmTokenDeferred.await()
                    val balanceResult = loadEvmBalanceDeferred.await()

                    if (tokenResult is NetworkResult.Success && tokenResult.data is MutableList<*> && tokenResult.data.all { it is Token }) {
                        evmRpcFetcher.evmTokens = tokenResult.data
                    }

                    if (balanceResult is NetworkResult.Success && balanceResult.data is String) {
                        web3j = Web3j.build(HttpService(evmRpcFetcher.getEvmRpc()))
                        evmRpcFetcher.evmBalance = balanceResult.data.toBigDecimal()
                    } else if (balanceResult is NetworkResult.Error) {
                        web3j = null
                    }

                    if (this is ChainOktEvm) {
                        oktFetcher()?.let {
                            val loadAccountInfoDeferred =
                                async { walletRepository.oktAccountInfo(this@apply) }
                            val loadDepositDeferred =
                                async { walletRepository.oktDeposit(this@apply) }
                            val loadWithdrawDeferred =
                                async { walletRepository.oktWithdraw(this@apply) }
                            val loadTokenDeferred = async { walletRepository.oktToken(this@apply) }

                            val accountInfoResult = loadAccountInfoDeferred.await()
                            val depositResult = loadDepositDeferred.await()
                            val withdrawResult = loadWithdrawDeferred.await()
                            val oktTokenResult = loadTokenDeferred.await()

                            if (accountInfoResult is NetworkResult.Success && accountInfoResult.data is JsonObject) {
                                it.oktAccountInfo = accountInfoResult.data
                            } else {
                                it.oktAccountInfo = null
                            }

                            if (depositResult is NetworkResult.Success && depositResult.data is JsonObject) {
                                oktFetcher?.oktDeposits = depositResult.data
                            }
                            if (withdrawResult is NetworkResult.Success && withdrawResult.data is JsonObject) {
                                oktFetcher?.oktWithdaws = withdrawResult.data
                            }
                            if (oktTokenResult is NetworkResult.Success && oktTokenResult.data is JsonObject) {
                                oktFetcher?.oktTokens = oktTokenResult.data
                            }
                        }
                    }

                    if (this is ChainOktEvm) {
                        fetchState =
                            if (web3j == null || oktFetcher()?.oktAccountInfo?.isJsonNull == true) {
                                FetchState.FAIL
                            } else {
                                FetchState.SUCCESS
                            }

                        if (fetchState == FetchState.SUCCESS) {
                            val refAddress = RefAddress(
                                baseAccountId,
                                tag,
                                address,
                                evmAddress,
                                oktFetcher?.allAssetValue(true).toString(),
                                evmRpcFetcher.evmBalance.toString(),
                                "0",
                                oktFetcher?.oktAccountInfo?.get("value")?.asJsonObject?.get("coins")?.asJsonArray?.size()
                                    ?.toLong()
                            )
                            BaseData.updateRefAddressesMain(refAddress)
                            coinCnt =
                                oktFetcher?.oktAccountInfo?.get("value")?.asJsonObject?.get("coins")?.asJsonArray?.size()
                                    ?: 0
                            withContext(Dispatchers.Main) {
                                if (isEdit == true) {
                                    editFetchedResult.value = tag
                                } else {
                                    fetchedResult.value = tag
                                }
                            }

                            val tokenBalanceDeferredList = evmRpcFetcher.evmTokens.map { token ->
                                async { walletRepository.erc20Balance(chain, token) }
                            }

                            tokenBalanceDeferredList.awaitAll()
                            val evmRefAddress = RefAddress(
                                baseAccountId,
                                tag,
                                address,
                                evmAddress,
                                "0",
                                "0",
                                evmRpcFetcher.allTokenValue(true).toPlainString(),
                                0
                            )
                            BaseData.updateRefAddressesToken(evmRefAddress)
                            tokenCnt = evmRpcFetcher.valueTokenCnt()
                            withContext(Dispatchers.Main) {
                                if (isEdit == true) {
                                    editFetchedTokenResult.value = tag
                                } else {
                                    fetchedTokenResult.value = tag
                                }
                            }
                            fetchedTotalResult.postValue(tag)

                        } else {
                            val refAddress = RefAddress(
                                baseAccountId, tag, address, evmAddress, "0", "0", "0", 0
                            )
                            BaseData.updateRefAddressesMain(refAddress)
                            withContext(Dispatchers.Main) {
                                if (isEdit == true) {
                                    editFetchedResult.value = tag
                                } else {
                                    fetchedResult.value = tag
                                }
                            }
                        }

                    } else {
                        fetchState = if (web3j != null) {
                            FetchState.SUCCESS
                        } else {
                            FetchState.FAIL
                        }

                        if (fetchState == FetchState.SUCCESS) {
                            val refAddress = RefAddress(
                                baseAccountId,
                                tag,
                                "",
                                evmAddress,
                                evmRpcFetcher.allAssetValue(true).toString(),
                                evmRpcFetcher.evmBalance.toString(),
                                "0",
                                if (BigDecimal.ZERO >= evmRpcFetcher.evmBalance) 0 else 1
                            )
                            BaseData.updateRefAddressesMain(refAddress)
                            coinCnt = evmRpcFetcher.valueCoinCnt()
                            withContext(Dispatchers.Main) {
                                if (isEdit == true) {
                                    editFetchedResult.value = tag
                                } else {
                                    fetchedResult.value = tag
                                }
                            }

                            val tokenBalanceDeferredList = if (userDisplayToken == null) {
                                evmRpcFetcher.evmTokens.filter { it.wallet_preload ?: false }
                                    .map { token ->
                                        async { walletRepository.erc20Balance(this@apply, token) }
                                    }

                            } else {
                                evmRpcFetcher.evmTokens.filter { userDisplayToken.contains(it.contract) }
                                    .map { token ->
                                        async { walletRepository.erc20Balance(this@apply, token) }
                                    }
                            }

                            tokenBalanceDeferredList.awaitAll()
                            val evmRefAddress = RefAddress(
                                baseAccountId,
                                tag,
                                "",
                                evmAddress,
                                "0",
                                "0",
                                evmRpcFetcher.allTokenValue(true).toPlainString(),
                                0
                            )
                            BaseData.updateRefAddressesToken(evmRefAddress)
                            tokenCnt =
                                if (Prefs.getDisplayErc20s(baseAccountId, chain.tag) != null) {
                                    Prefs.getDisplayErc20s(
                                        baseAccountId, chain.tag
                                    )?.size.toString().toInt()
                                } else {
                                    evmRpcFetcher.valueTokenCnt()
                                }
                            withContext(Dispatchers.Main) {
                                if (isEdit == true) {
                                    editFetchedTokenResult.value = tag
                                } else {
                                    fetchedTokenResult.value = tag
                                }
                            }
                            fetchedTotalResult.postValue(tag)

                        } else {
                            withContext(Dispatchers.Main) {
                                if (isEdit == true) {
                                    editFetchedTokenResult.value = tag
                                } else {
                                    fetchedTokenResult.value = tag
                                }
                            }
                            fetchedTotalResult.postValue(tag)
                        }
                    }
                }
            }
        }

    private fun loadOktLcdData(
        chain: BaseChain, baseAccountId: Long, isEdit: Boolean? = false
    ) = CoroutineScope(Dispatchers.IO).launch {
        chain.apply {
            if (this is ChainOkt996Keccak) {
                val loadAccountInfoDeferred = async { walletRepository.oktAccountInfo(this@apply) }
                val loadDepositDeferred = async { walletRepository.oktDeposit(this@apply) }
                val loadWithdrawDeferred = async { walletRepository.oktWithdraw(this@apply) }
                val loadTokenDeferred = async { walletRepository.oktToken(this@apply) }

                val accountInfoResult = loadAccountInfoDeferred.await()
                val depositResult = loadDepositDeferred.await()
                val withdrawResult = loadWithdrawDeferred.await()
                val tokenResult = loadTokenDeferred.await()

                if (accountInfoResult is NetworkResult.Success && accountInfoResult.data is JsonObject) {
                    oktFetcher()?.oktAccountInfo = accountInfoResult.data
                } else {
                    oktFetcher()?.oktAccountInfo = null
                }

                if (depositResult is NetworkResult.Success && depositResult.data is JsonObject) {
                    oktFetcher()?.oktDeposits = depositResult.data
                }
                if (withdrawResult is NetworkResult.Success && withdrawResult.data is JsonObject) {
                    oktFetcher()?.oktWithdaws = withdrawResult.data
                }
                if (tokenResult is NetworkResult.Success && tokenResult.data is JsonObject) {
                    oktFetcher()?.oktTokens = tokenResult.data
                }

                fetchState = if (oktFetcher()?.oktAccountInfo?.isJsonNull == true) {
                    FetchState.FAIL
                } else {
                    FetchState.SUCCESS
                }

                if (fetchState == FetchState.SUCCESS) {
                    val refAddress = RefAddress(
                        baseAccountId,
                        tag,
                        address,
                        evmAddress,
                        oktFetcher?.allAssetValue(true).toString(),
                        oktFetcher?.oktBalanceAmount(stakeDenom).toString(),
                        "0",
                        oktFetcher?.oktAccountInfo?.get("value")?.asJsonObject?.get("coins")?.asJsonArray?.size()
                            ?.toLong()
                    )
                    BaseData.updateRefAddressesMain(refAddress)
                    coinCnt =
                        oktFetcher?.oktAccountInfo?.get("value")?.asJsonObject?.get("coins")?.asJsonArray?.size()
                            ?: 0
                    withContext(Dispatchers.Main) {
                        if (isEdit == true) {
                            editFetchedResult.value = tag
                        } else {
                            fetchedResult.value = tag
                        }
                    }

                } else {
                    val refAddress = RefAddress(
                        baseAccountId, tag, address, evmAddress, "0", "0", "0", 0
                    )
                    BaseData.updateRefAddressesMain(refAddress)
                    withContext(Dispatchers.Main) {
                        if (isEdit == true) {
                            editFetchedResult.value = tag
                        } else {
                            fetchedResult.value = tag
                        }
                    }
                }
            }
        }
    }

    fun loadSuiData(
        id: Long,
        chain: BaseChain,
        isEdit: Boolean? = false,
        isTx: Boolean? = false,
        isRefresh: Boolean? = false
    ) = CoroutineScope(Dispatchers.IO).launch {
        (chain as ChainSui).suiFetcher()?.let { fetcher ->
            chain.apply {
                fetcher.suiSystem = JsonObject()
                fetcher.suiBalances.clear()
                fetcher.suiStakedList.clear()
                fetcher.suiObjects.clear()
                fetcher.suiValidators.clear()
                fetcher.suiApys.clear()
                fetcher.suiCoinMeta.clear()

                try {
                    val loadSystemStateDeferred =
                        async { walletRepository.suiSystemState(fetcher, this@apply) }
                    val loadOwnedObjectDeferred =
                        async { walletRepository.suiOwnedObject(fetcher, this@apply, null) }
                    val loadStakesDeferred =
                        async { walletRepository.suiStakes(fetcher, this@apply) }
                    val loadApysDeferred = async { walletRepository.suiApys(fetcher, this@apply) }

                    val systemStateResult = loadSystemStateDeferred.await()
                    loadOwnedObjectDeferred.await()
                    val stakesResult = loadStakesDeferred.await()
                    val apysResult = loadApysDeferred.await()

                    if (systemStateResult is NetworkResult.Success) {
                        fetcher.suiSystem = systemStateResult.data
                        fetcher.suiSystem["result"].asJsonObject["activeValidators"].asJsonArray.forEach { validator ->
                            fetcher.suiValidators.add(validator.asJsonObject)
                        }
                        fetcher.suiValidators.sortWith { o1, o2 ->
                            when {
                                o1["name"].asString == "Cosmostation" -> -1
                                o2["name"].asString == "Cosmostation" -> 1
                                else -> o2["votingPower"]?.asInt?.compareTo(
                                    o1["votingPower"]?.asInt ?: 0
                                ) ?: 0
                            }
                        }

                    } else if (systemStateResult is NetworkResult.Error) {
                        _chainDataErrorMessage.postValue("error type : ${systemStateResult.errorType}  error message : ${systemStateResult.errorMessage}")
                    }

                    if (apysResult is NetworkResult.Success) {
                        fetcher.suiApys = apysResult.data
                        fetcher.suiApys.sortByDescending {
                            it["apy"]?.asDouble ?: Double.MIN_VALUE
                        }

                    } else if (apysResult is NetworkResult.Error) {
                        _chainDataErrorMessage.postValue("error type : ${apysResult.errorType}  error message : ${apysResult.errorMessage}")
                    }

                    fetcher.suiObjects.forEach { suiObject ->
                        val coinType = suiObject["data"].asJsonObject["type"].asString.suiCoinType()
                        if (coinType != null) {
                            val fields =
                                suiObject["data"].asJsonObject["content"].asJsonObject["fields"].asJsonObject
                            fields?.get("balance")?.let { balance ->
                                val index =
                                    fetcher.suiBalances.indexOfFirst { it.first == coinType }
                                if (index != -1) {
                                    val alreadyAmount = fetcher.suiBalances[index].second
                                    val sumAmount =
                                        alreadyAmount?.add(balance.asString.toBigDecimal())
                                    fetcher.suiBalances[index] = Pair(coinType, sumAmount)

                                } else {
                                    val newAmount = balance.asString.toBigDecimal()
                                    fetcher.suiBalances.add(Pair(coinType, newAmount))
                                }
                            }
                        }
                    }

                    if (stakesResult is NetworkResult.Success) {
                        stakesResult.data["result"].asJsonArray.forEach { stake ->
                            fetcher.suiStakedList.add(stake.asJsonObject)
                        }

                    } else if (stakesResult is NetworkResult.Error) {
                        _chainDataErrorMessage.postValue("error type : ${stakesResult.errorType}  error message : ${stakesResult.errorMessage}")
                    }

                    withContext(Dispatchers.Default) {
                        val coinMetaDeferred = fetcher.suiBalances.map { (coinType, _) ->
                            async {
                                walletRepository.suiCoinMetadata(fetcher, this@apply, coinType)
                            }
                        }

                        coinMetaDeferred.forEachIndexed { index, deferred ->
                            val coinMetadataResult = deferred.await()
                            if (coinMetadataResult is NetworkResult.Success && fetcher.suiBalances.isNotEmpty()) {
                                fetcher.suiBalances[index].first?.let { type ->
                                    val result = coinMetadataResult.data["result"]?.asJsonObject
                                    if (result != null) {
                                        fetcher.suiCoinMeta[type] = result
                                    }
                                }

                            } else if (coinMetadataResult is NetworkResult.Error) {
                                _chainDataErrorMessage.postValue("Coin metadata fetch error: ${coinMetadataResult.errorMessage}")
                            }
                        }
                    }

                    fetchState = FetchState.SUCCESS
                    val refAddress = RefAddress(
                        id,
                        tag,
                        mainAddress,
                        "",
                        fetcher.allAssetValue(true).toString(),
                        fetcher.suiBalanceAmount(SUI_MAIN_DENOM).toString(),
                        "0",
                        fetcher.suiBalances.size.toLong()
                    )
                    BaseData.updateRefAddressesMain(refAddress)
                    coinCnt = fetcher.suiBalances.size

                    withContext(Dispatchers.Main) {
                        if (isEdit == true) {
                            editFetchedResult.value = tag
                        } else if (isTx == true) {
                            txFetchedResult.value = tag
                        } else if (isRefresh == true) {
                            refreshStakingInfoFetchedResult.value = tag
                        } else {
                            fetchedResult.value = tag
                        }
                    }

                } catch (e: Exception) {
                    fetchState = FetchState.FAIL
                    withContext(Dispatchers.Main) {
                        if (isEdit == true) {
                            editFetchedResult.value = tag
                        } else if (isTx == true) {
                            txFetchedResult.value = tag
                        } else if (isRefresh == true) {
                            refreshStakingInfoFetchedResult.value = tag
                        } else {
                            fetchedResult.value = tag
                        }
                    }
                }
            }
        }
    }

    fun loadBtcData(
        id: Long, chain: ChainBitCoin86, isEdit: Boolean? = false
    ) = CoroutineScope(Dispatchers.IO).launch {
        chain.btcFetcher()?.let { fetcher ->
            chain.apply {
                fetcher.btcBalances = BigDecimal.ZERO
                fetcher.btcPendingInput = BigDecimal.ZERO
                fetcher.btcPendingOutput = BigDecimal.ZERO

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
                        val refAddress = RefAddress(
                            id,
                            tag,
                            mainAddress,
                            "",
                            fetcher.allAssetValue(true).toString(),
                            "0",
                            "0",
                            if (fetcher.btcBalances == BigDecimal.ZERO && fetcher.btcPendingInput == BigDecimal.ZERO) 0 else 1
                        )
                        BaseData.updateRefAddressesMain(refAddress)
                        coinCnt =
                            if (chain.btcFetcher()?.btcBalances == BigDecimal.ZERO && chain.btcFetcher()?.btcPendingInput == BigDecimal.ZERO) 0 else 1

                        withContext(Dispatchers.Main) {
                            if (isEdit == true) {
                                editFetchedResult.value = tag
                            } else {
                                fetchedResult.value = tag
                            }
                        }
                    }

                    is NetworkResult.Error -> {
                        fetchState = FetchState.FAIL
                        withContext(Dispatchers.Main) {
                            if (isEdit == true) {
                                editFetchedResult.value = tag
                            } else {
                                fetchedResult.value = tag
                            }
                        }
                    }
                }
            }
        }
    }

    private fun loadRpcData(chain: BaseChain, id: Long, isEdit: Boolean? = false) =
        CoroutineScope(Dispatchers.IO).launch {
            chain.apply {
                when (val response = walletRepository.rpcAuth(chain)) {
                    is NetworkResult.Success -> {
                        (chain as ChainGnoTestnet).gnoRpcFetcher()?.let { fetcher ->
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
                                        CoinProto.Coin.newBuilder().setDenom(stakeDenom)
                                            .setAmount("0").build()
                                    )
                                    fetcher.gnoBalances = tempBalances

                                    val refAddress = RefAddress(
                                        id,
                                        tag,
                                        address,
                                        "",
                                        fetcher.allAssetValue(true).toString(),
                                        "0",
                                        "0",
                                        0
                                    )
                                    BaseData.updateRefAddressesMain(refAddress)
                                    coinCnt = 0

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
                                            CoinProto.Coin.newBuilder().setDenom(stakeDenom)
                                                .setAmount("0").build()
                                        )
                                    }

                                    fetcher.gnoBalances = tempBalances
                                    fetcher.gnoAccountNumber =
                                        accountData["account_number"].asString.toLong()
                                    fetcher.gnoSequence = accountData["sequence"].asString.toLong()

                                    val refAddress = RefAddress(
                                        id,
                                        tag,
                                        address,
                                        "",
                                        fetcher.allAssetValue(true).toString(),
                                        "0",
                                        "0",
                                        if (fetcher.gnoBalances?.get(0)?.amount?.toBigDecimal() == BigDecimal.ZERO) 0 else 1
                                    )
                                    BaseData.updateRefAddressesMain(refAddress)
                                    coinCnt =
                                        if (fetcher.gnoBalances?.get(0)?.amount?.toBigDecimal() == BigDecimal.ZERO) 0 else 1
                                }

                                withContext(Dispatchers.Main) {
                                    if (isEdit == true) {
                                        editFetchedResult.value = tag
                                    } else {
                                        fetchedResult.value = tag
                                    }
                                }

                                if (isSupportGrc20()) {
                                    val userDisplayToken = Prefs.getDisplayGrc20s(id, tag)
                                    val tokenBalanceDeferredList = if (userDisplayToken == null) {
                                        fetcher.grc20Tokens.filter { it.wallet_preload ?: false }
                                            .map { token ->
                                                async {
                                                    walletRepository.grc20Balance(
                                                        chain, token
                                                    )
                                                }
                                            }

                                    } else {
                                        fetcher.grc20Tokens.filter { userDisplayToken.contains(it.contract) }
                                            .map { token ->
                                                async {
                                                    walletRepository.grc20Balance(
                                                        chain, token
                                                    )
                                                }
                                            }
                                    }

                                    tokenBalanceDeferredList.awaitAll()
                                    val grcRefAddress = RefAddress(
                                        id,
                                        tag,
                                        address,
                                        "",
                                        "0",
                                        "0",
                                        gnoRpcFetcher?.allGrc20TokenValue(true)?.toPlainString(),
                                        0
                                    )
                                    BaseData.updateRefAddressesToken(grcRefAddress)
                                    tokenCnt = fetcher.valueGrc20TokenCnt()
                                    fetchState = FetchState.SUCCESS

                                    withContext(Dispatchers.Main) {
                                        if (isEdit == true) {
                                            editFetchedTokenResult.value = tag
                                        } else {
                                            fetchedTokenResult.value = tag
                                        }
                                    }
                                    fetchedTotalResult.postValue(tag)
                                }

                            } else {
                                fetchState = FetchState.FAIL
                                withContext(Dispatchers.Main) {
                                    if (isEdit == true) {
                                        editFetchedResult.value = tag
                                    } else {
                                        fetchedResult.value = tag
                                    }
                                }
                            }
                        }
                    }

                    is NetworkResult.Error -> {
                        fetchState = FetchState.FAIL
                        withContext(Dispatchers.Main) {
                            if (isEdit == true) {
                                editFetchedResult.value = tag
                            } else {
                                fetchedResult.value = tag
                            }
                        }
                    }
                }
            }
        }
}