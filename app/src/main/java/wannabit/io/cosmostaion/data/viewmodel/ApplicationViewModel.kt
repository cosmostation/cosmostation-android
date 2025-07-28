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
import wannabit.io.cosmostaion.chain.cosmosClass.ChainBabylon
import wannabit.io.cosmostaion.chain.cosmosClass.ChainInitia
import wannabit.io.cosmostaion.chain.cosmosClass.ChainNeutron
import wannabit.io.cosmostaion.chain.cosmosClass.ChainOkt996Keccak
import wannabit.io.cosmostaion.chain.cosmosClass.ChainZenrock
import wannabit.io.cosmostaion.chain.evmClass.ChainOktEvm
import wannabit.io.cosmostaion.chain.fetcher.FinalityProvider
import wannabit.io.cosmostaion.chain.fetcher.iotaCoinType
import wannabit.io.cosmostaion.chain.fetcher.suiCoinType
import wannabit.io.cosmostaion.chain.majorClass.ChainBitCoin86
import wannabit.io.cosmostaion.chain.majorClass.ChainIota
import wannabit.io.cosmostaion.chain.majorClass.ChainSui
import wannabit.io.cosmostaion.chain.majorClass.IOTA_MAIN_DENOM
import wannabit.io.cosmostaion.chain.majorClass.SUI_MAIN_DENOM
import wannabit.io.cosmostaion.chain.testnetClass.ChainGnoTestnet
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.ByteUtils
import wannabit.io.cosmostaion.common.formatJsonString
import wannabit.io.cosmostaion.common.regexWithNumberAndChar
import wannabit.io.cosmostaion.common.toHex
import wannabit.io.cosmostaion.data.model.res.NetworkResult
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

    var themeOptionResult = SingleLiveEvent<Boolean>()
    fun themeOption(isChanged: Boolean) = viewModelScope.launch(Dispatchers.IO) {
        themeOptionResult.postValue(isChanged)
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
            chain.cosmosFetcher()?.tokens =
                BaseData.cw20Tokens?.filter { it.chainName == chain.apiName }?.map { token ->
                    token.type = "cw20"
                    token
                }?.toMutableList() ?: mutableListOf()

            chain.evmRpcFetcher()?.evmTokens =
                BaseData.erc20Tokens?.filter { it.chainName == chain.apiName }?.map { token ->
                    token.type = "erc20"
                    token
                }?.toMutableList() ?: mutableListOf()

            chain.gnoRpcFetcher?.grc20Tokens =
                BaseData.grc20Tokens?.filter { it.chainName == chain.apiName }?.map { token ->
                    token.type = "grc20"
                    token
                }?.toMutableList() ?: mutableListOf()

            chain.cosmosFetcher?.cw721Tokens =
                BaseData.cw721Tokens?.filter { it.chain == chain.apiName }?.toMutableList()
                    ?: mutableListOf()

            when (this) {
                is ChainBitCoin86 -> loadBtcData(baseAccountId, this, isEdit)
                is ChainOkt996Keccak -> loadOktLcdData(this, baseAccountId, isEdit)
                is ChainSui -> loadSuiData(baseAccountId, this, isEdit, isTx, isRefresh)
                is ChainIota -> loadIotaData(baseAccountId, this, isEdit, isTx, isRefresh)
                is ChainGnoTestnet -> loadRpcData(this, baseAccountId, isEdit)
                else -> {
                    if (supportCosmos() && this !is ChainOktEvm) {
                        loadGrpcAuthData(this, baseAccountId, isEdit, isTx, isRefresh)
                    } else {
                        loadEvmChainData(this, baseAccountId, isEdit)
                    }
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
    val notifySuiTxResult = SingleLiveEvent<Unit>()

    fun notifyTxEvent() {
        notifyTxResult.postValue(Unit)
    }

    fun notifyRefreshEvent() {
        notifyRefreshResult.postValue(Unit)
    }

    fun notifySuiTxEvent() {
        notifySuiTxResult.postValue(Unit)
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
                    val loadSpendableBalanceDeferred =
                        async { walletRepository.spendableBalance(channel, this@apply) }
                    val loadBalanceDeferred =
                        async { walletRepository.balance(channel, this@apply) }
                    val loadRewardAddressDeferred =
                        async { walletRepository.rewardAddress(channel, this@apply) }
                    val loadFeeMarketDeferred =
                        async { walletRepository.baseFee(channel, this@apply) }

                    val spendableBalanceResult = loadSpendableBalanceDeferred.await()
                    val balanceResult = loadBalanceDeferred.await()
                    val rewardAddressResult = loadRewardAddressDeferred.await()
                    val feeMarketResult = loadFeeMarketDeferred.await()

                    if (spendableBalanceResult is NetworkResult.Success) {
                        cosmosFetcher?.cosmosAvailable = spendableBalanceResult.data
                    }

                    if (balanceResult is NetworkResult.Success) {
                        cosmosFetcher?.cosmosBalances = balanceResult.data
                    }

                    if (rewardAddressResult is NetworkResult.Success) {
                        cosmosFetcher?.rewardAddress = rewardAddressResult.data
                    }

                    if (feeMarketResult is NetworkResult.Success) {
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
                            if (o1.denom == chain.stakeDenom && o2.denom != chain.stakeDenom) -1
                            else 0
                        }
                    }

                    delay(2000)
                    fetchState = if (cosmosFetcher?.cosmosBalances == null) FetchState.FAIL
                    else FetchState.SUCCESS

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
                        val loadEvmBalanceDeferred =
                            async { walletRepository.evmBalance(this@apply) }

                        val balanceResult = loadEvmBalanceDeferred.await()

                        if (balanceResult is NetworkResult.Success && balanceResult.data is String) {
                            web3j = Web3j.build(HttpService(evmRpcFetcher.getEvmRpc()))
                            evmRpcFetcher.evmBalance = balanceResult.data.toBigDecimal()
                        } else if (balanceResult is NetworkResult.Error) {
                            web3j = null
                        }
                    }
                }

                val loadSpendableBalanceDeferred =
                    async { walletRepository.spendableBalance(channel, chain) }
                val loadBalanceDeferred = async { walletRepository.balance(channel, chain) }
                val loadRewardDeferred = async { walletRepository.reward(channel, chain) }
                val loadRewardAddressDeferred =
                    async { walletRepository.rewardAddress(channel, chain) }
                val loadFeeMarketDeferred = async { walletRepository.baseFee(channel, chain) }

                if (chain is ChainInitia) {
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
                    }

                    if (unBondingResult is NetworkResult.Success && unBondingResult.data is MutableList<*>) {
                        chain.initiaFetcher()?.initiaUnbondings = unBondingResult.data
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
                    }

                    if (unBondingResult is NetworkResult.Success && unBondingResult.data is MutableList<*>) {
                        chain.zenrockFetcher()?.zenrockUnbondings = unBondingResult.data
                    }

                } else {
                    if (chain is ChainNeutron) {
                        val loadVaultDepositDeferred =
                            async { walletRepository.vaultDeposit(channel, this@apply) }
                        val loadVestingDeferred =
                            async { walletRepository.vestingData(channel, this@apply) }
                        val loadRewardsDeferred =
                            async { walletRepository.stakingRewards(channel, this@apply) }

                        val responses = awaitAll(
                            loadVestingDeferred, loadVaultDepositDeferred, loadRewardsDeferred
                        )

                        responses.forEach { response ->
                            when (response) {
                                is NetworkResult.Success -> {
                                    when (response.data) {
                                        is com.cosmwasm.wasm.v1.QueryProto.QuerySmartContractStateResponse -> {
                                            chain.neutronFetcher()?.neutronVesting =
                                                Gson().fromJson(
                                                    response.data.data.toStringUtf8(),
                                                    VestingData::class.java
                                                )
                                        }

                                        is String -> {
                                            chain.neutronFetcher()?.neutronDeposited =
                                                response.data.toString().toBigDecimal()
                                        }

                                        else -> {
                                            chain.neutronFetcher()?.neutronRewards =
                                                response.data.toString().toBigDecimal()
                                        }
                                    }
                                }

                                is NetworkResult.Error -> {
                                    _chainDataErrorMessage.postValue("error type : ${response.errorType}  error message : ${response.errorMessage}")
                                }
                            }
                        }
                    }

                    if (chain is ChainBabylon) {
                        val loadRewardGaugeDeferred =
                            async { walletRepository.btcReward(channel, chain) }
                        val loadBtcStakedStatusDeferred =
                            async { walletRepository.btcStakingStatus(chain) }

                        val rewardGaugeResult = loadRewardGaugeDeferred.await()
                        val btcStakedStatusResult = loadBtcStakedStatusDeferred.await()

                        if (rewardGaugeResult is NetworkResult.Success) {
                            chain.babylonFetcher()?.btcRewards = rewardGaugeResult.data
                        } else if (rewardGaugeResult is NetworkResult.Error) {
                            _chainDataErrorMessage.postValue("error type : ${rewardGaugeResult.errorType}  error message : ${rewardGaugeResult.errorMessage}")
                        }

                        if (btcStakedStatusResult is NetworkResult.Success) {
                            chain.babylonFetcher()?.btcStakedStatus = btcStakedStatusResult.data
                        }
                    }

                    val loadDelegationDeferred =
                        async { walletRepository.delegation(channel, chain) }
                    val loadUnBondingDeferred = async { walletRepository.unBonding(channel, chain) }

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
                    }

                    if (unBondingResult is NetworkResult.Success && unBondingResult.data is MutableList<*>) {
                        cosmosFetcher?.cosmosUnbondings = unBondingResult.data
                    }
                }

                val spendableBalanceResult = loadSpendableBalanceDeferred.await()
                val balanceResult = loadBalanceDeferred.await()
                val rewardResult = loadRewardDeferred.await()
                val rewardAddressResult = loadRewardAddressDeferred.await()
                val feeMarketResult = loadFeeMarketDeferred.await()

                if (spendableBalanceResult is NetworkResult.Success && spendableBalanceResult.data is MutableList<*>) {
                    cosmosFetcher?.cosmosAvailable = spendableBalanceResult.data
                }

                if (balanceResult is NetworkResult.Success && balanceResult.data is MutableList<*>) {
                    cosmosFetcher?.cosmosBalances = balanceResult.data
                }

                if (rewardResult is NetworkResult.Success && rewardResult.data is MutableList<*>) {
                    cosmosFetcher?.cosmosRewards = rewardResult.data
                }

                if (rewardAddressResult is NetworkResult.Success && rewardAddressResult.data is String) {
                    cosmosFetcher?.rewardAddress = rewardAddressResult.data
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
                        if (o1.denom == chain.stakeDenom && o2.denom != chain.stakeDenom) -1
                        else 0
                    }
                }

                fetchState = when {
                    cosmosFetcher?.cosmosBalances == null -> FetchState.FAIL
                    supportEvm && web3j == null -> FetchState.FAIL
                    else -> FetchState.SUCCESS
                }

                if (fetchState == FetchState.SUCCESS) {
                    val refAddress = RefAddress(
                        id,
                        tag,
                        address,
                        evmAddress,
                        cosmosFetcher?.allAssetValue(true).toString(),
                        cosmosFetcher?.allStakingDenomAmount().toString(),
                        "0",
                        chain.cosmosFetcher()?.valueCoinCnt()?.toLong() ?: 0L
                    )
                    BaseData.updateRefAddressesMain(refAddress)
                    coinValue = cosmosFetcher?.allAssetValue()
                    coinUsdValue = cosmosFetcher?.allAssetValue(true)
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

                    var cw20TokenValue = BigDecimal.ZERO
                    var cw20TokenUsdValue = BigDecimal.ZERO
                    var erc20TokenValue = BigDecimal.ZERO
                    var erc20TokenUsdValue = BigDecimal.ZERO
                    var cw20TokenCnt = 0
                    var erc20TokenCnt = 0

                    if (isSupportCw20()) {
                        val userDisplayToken = Prefs.getDisplayCw20s(id, tag)
                        val tokenBalanceDeferredList = if (userDisplayToken == null) {
                            cosmosFetcher?.tokens?.filter { it.wallet_preload ?: false }
                                ?.map { token ->
                                    async { walletRepository.cw20Balance(channel, chain, token) }
                                }

                        } else {
                            cosmosFetcher?.tokens?.filter { userDisplayToken.contains(it.address) }
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
                            cosmosFetcher?.allTokenValue(id, true)?.toPlainString(),
                            0
                        )
                        BaseData.updateRefAddressesToken(cwRefAddress)
                        cw20TokenValue = cosmosFetcher?.allTokenValue(id)
                        cw20TokenUsdValue = cosmosFetcher?.allTokenValue(id, true)
                        cw20TokenCnt = chain.cosmosFetcher()?.displayTokenCnt(id) ?: 0
                    }

                    if (isSupportErc20()) {
                        if (web3j != null) {
                            evmRpcFetcher()?.let { evmRpcFetcher ->
                                val userDisplayToken = Prefs.getDisplayErc20s(id, tag)
                                if (isSupportErc20()) {
                                    if (isSupportMultiCall() && multicallAddress().isNotEmpty()) {
                                        withContext(Dispatchers.Default) {
                                            walletRepository.erc20MultiBalance(this@apply)
                                        }

                                    } else {
                                        val tokenBalanceDeferredList =
                                            if (userDisplayToken == null) {
                                                evmRpcFetcher.evmTokens.filter {
                                                    it.wallet_preload ?: false
                                                }.map { token ->
                                                    async {
                                                        walletRepository.erc20Balance(
                                                            this@apply, token
                                                        )
                                                    }
                                                }

                                            } else {
                                                evmRpcFetcher.evmTokens.filter {
                                                    userDisplayToken.contains(
                                                        it.address
                                                    )
                                                }.map { token ->
                                                    async {
                                                        walletRepository.erc20Balance(
                                                            this@apply, token
                                                        )
                                                    }
                                                }
                                            }
                                        tokenBalanceDeferredList.awaitAll()
                                    }
                                }

                                val evmRefAddress = RefAddress(
                                    id,
                                    tag,
                                    address,
                                    evmAddress,
                                    "0",
                                    "0",
                                    evmRpcFetcher.allTokenValue(id, true).toPlainString(),
                                    cosmosFetcher?.cosmosBalances?.count {
                                        BaseData.getAsset(
                                            apiName, it.denom
                                        ) != null
                                    }?.toLong() ?: 0L
                                )
                                BaseData.updateRefAddressesToken(evmRefAddress)
                                erc20TokenValue = evmRpcFetcher.allTokenValue(id)
                                erc20TokenUsdValue = evmRpcFetcher.allTokenValue(id, true)
                                erc20TokenCnt = evmRpcFetcher.displayTokenCnt(id)
                            }
                        }
                    }

                    tokenValue = cw20TokenValue.add(erc20TokenValue)
                    tokenUsdValue = cw20TokenUsdValue.add(erc20TokenUsdValue)
                    tokenCnt = cw20TokenCnt + erc20TokenCnt

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
                    val loadEvmBalanceDeferred = async { walletRepository.evmBalance(this@apply) }

                    val balanceResult = loadEvmBalanceDeferred.await()

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

                            val accountInfoResult = loadAccountInfoDeferred.await()
                            val depositResult = loadDepositDeferred.await()
                            val withdrawResult = loadWithdrawDeferred.await()

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
                        }
                    }

                    if (this is ChainOktEvm) {
                        fetchState =
                            if (web3j == null || oktFetcher()?.oktAccountInfo?.isJsonNull == true) FetchState.FAIL
                            else FetchState.SUCCESS

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
                            coinValue = oktFetcher?.allAssetValue()
                            coinUsdValue = oktFetcher?.allAssetValue(true)
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

                            withContext(Dispatchers.Default) {
                                walletRepository.erc20MultiBalance(this@apply)

                                val evmRefAddress = RefAddress(
                                    baseAccountId,
                                    tag,
                                    address,
                                    evmAddress,
                                    "0",
                                    "0",
                                    evmRpcFetcher.allTokenValue(baseAccountId, true)
                                        .toPlainString(),
                                    0
                                )
                                BaseData.updateRefAddressesToken(evmRefAddress)
                                tokenValue = evmRpcFetcher.allTokenValue(baseAccountId)
                                tokenUsdValue = evmRpcFetcher.allTokenValue(baseAccountId, true)
                                tokenCnt = evmRpcFetcher.displayTokenCnt(baseAccountId)

                                withContext(Dispatchers.Main) {
                                    if (isEdit == true) {
                                        editFetchedTokenResult.value = tag
                                    } else {
                                        fetchedTokenResult.value = tag
                                    }
                                }
                                fetchedTotalResult.postValue(tag)
                            }

                            val evmRefAddress = RefAddress(
                                baseAccountId,
                                tag,
                                address,
                                evmAddress,
                                "0",
                                "0",
                                evmRpcFetcher.allTokenValue(baseAccountId, true).toPlainString(),
                                0
                            )
                            BaseData.updateRefAddressesToken(evmRefAddress)
                            tokenValue = evmRpcFetcher.allTokenValue(baseAccountId)
                            tokenUsdValue = evmRpcFetcher.allTokenValue(baseAccountId, true)
                            tokenCnt = evmRpcFetcher.displayTokenCnt(baseAccountId)

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
                        fetchState = if (web3j != null) FetchState.SUCCESS
                        else FetchState.FAIL

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
                            coinValue = evmRpcFetcher.allAssetValue()
                            coinUsdValue = evmRpcFetcher.allAssetValue(true)
                            coinCnt = evmRpcFetcher.valueCoinCnt()

                            withContext(Dispatchers.Main) {
                                if (isEdit == true) {
                                    editFetchedResult.value = tag
                                } else {
                                    fetchedResult.value = tag
                                }
                            }

                            if (isSupportErc20()) {
                                if (isSupportMultiCall() && multicallAddress().isNotEmpty()) {
                                    withContext(Dispatchers.Default) {
                                        walletRepository.erc20MultiBalance(this@apply)
                                    }

                                } else {
                                    val tokenBalanceDeferredList = if (userDisplayToken == null) {
                                        evmRpcFetcher.evmTokens.filter {
                                            it.wallet_preload ?: false
                                        }.map { token ->
                                            async {
                                                walletRepository.erc20Balance(
                                                    this@apply, token
                                                )
                                            }
                                        }

                                    } else {
                                        evmRpcFetcher.evmTokens.filter {
                                            userDisplayToken.contains(
                                                it.address
                                            )
                                        }.map { token ->
                                            async {
                                                walletRepository.erc20Balance(
                                                    this@apply, token
                                                )
                                            }
                                        }
                                    }
                                    tokenBalanceDeferredList.awaitAll()
                                }
                            }

                            val evmRefAddress = RefAddress(
                                baseAccountId,
                                tag,
                                "",
                                evmAddress,
                                "0",
                                "0",
                                evmRpcFetcher.allTokenValue(baseAccountId, true).toPlainString(),
                                0
                            )
                            BaseData.updateRefAddressesToken(evmRefAddress)
                            tokenValue = evmRpcFetcher.allTokenValue(baseAccountId)
                            tokenUsdValue = evmRpcFetcher.allTokenValue(baseAccountId, true)
                            tokenCnt = evmRpcFetcher.displayTokenCnt(baseAccountId)

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
        chain: ChainOkt996Keccak, baseAccountId: Long, isEdit: Boolean? = false
    ) = CoroutineScope(Dispatchers.IO).launch {
        chain.apply {
            val loadAccountInfoDeferred = async { walletRepository.oktAccountInfo(this@apply) }
            val loadDepositDeferred = async { walletRepository.oktDeposit(this@apply) }
            val loadWithdrawDeferred = async { walletRepository.oktWithdraw(this@apply) }

            val accountInfoResult = loadAccountInfoDeferred.await()
            val depositResult = loadDepositDeferred.await()
            val withdrawResult = loadWithdrawDeferred.await()

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

            fetchState = if (oktFetcher()?.oktAccountInfo?.isJsonNull == true) FetchState.FAIL
            else FetchState.SUCCESS

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
                coinValue = oktFetcher?.allAssetValue()
                coinUsdValue = oktFetcher?.allAssetValue(true)
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
                    }

                    if (apysResult is NetworkResult.Success) {
                        fetcher.suiApys = apysResult.data
                        fetcher.suiApys.sortByDescending {
                            it["apy"]?.asDouble ?: Double.MIN_VALUE
                        }
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
                                    val result = coinMetadataResult.data["result"]
                                    val resultData = when (result) {
                                        is JsonObject -> {
                                            result.asJsonObject
                                        }

                                        else -> {
                                            null
                                        }
                                    }
                                    if (resultData != null) {
                                        fetcher.suiCoinMeta[type] = resultData
                                    }
                                }
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
                    coinValue = fetcher.allAssetValue()
                    coinUsdValue = fetcher.allAssetValue(true)
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

    fun loadIotaData(
        id: Long,
        chain: BaseChain,
        isEdit: Boolean? = false,
        isTx: Boolean? = false,
        isRefresh: Boolean? = false
    ) = CoroutineScope(Dispatchers.IO).launch {
        (chain as ChainIota).iotaFetcher()?.let { fetcher ->
            chain.apply {
                fetcher.iotaSystem = JsonObject()
                fetcher.iotaBalances.clear()
                fetcher.iotaStakedList.clear()
                fetcher.iotaObjects.clear()
                fetcher.iotaValidators.clear()
                fetcher.iotaApys.clear()
                fetcher.iotaCoinMeta.clear()

                try {
                    val loadSystemStateDeferred =
                        async { walletRepository.iotaSystemState(fetcher, this@apply) }
                    val loadOwnedObjectDeferred =
                        async { walletRepository.iotaOwnedObject(fetcher, this@apply, null) }
                    val loadStakesDeferred =
                        async { walletRepository.iotaStakes(fetcher, this@apply) }
                    val loadApysDeferred = async { walletRepository.iotaApys(fetcher, this@apply) }

                    val systemStateResult = loadSystemStateDeferred.await()
                    loadOwnedObjectDeferred.await()
                    val stakesResult = loadStakesDeferred.await()
                    val apysResult = loadApysDeferred.await()

                    if (systemStateResult is NetworkResult.Success) {
                        fetcher.iotaSystem = systemStateResult.data
                        fetcher.iotaSystem["result"].asJsonObject["activeValidators"].asJsonArray.forEach { validator ->
                            fetcher.iotaValidators.add(validator.asJsonObject)
                        }

                        fetcher.iotaValidators.sortWith { o1, o2 ->
                            when {
                                o1["name"].asString == "Cosmostation" -> -1
                                o2["name"].asString == "Cosmostation" -> 1
                                else -> o2["votingPower"]?.asInt?.compareTo(
                                    o1["votingPower"]?.asInt ?: 0
                                ) ?: 0
                            }
                        }
                    }

                    if (apysResult is NetworkResult.Success) {
                        fetcher.iotaApys = apysResult.data
                        fetcher.iotaApys.sortByDescending {
                            it["apy"]?.asDouble ?: Double.MIN_VALUE
                        }
                    }

                    fetcher.iotaObjects.forEach { suiObject ->
                        val coinType =
                            suiObject["data"].asJsonObject["type"].asString.iotaCoinType()
                        if (coinType != null) {
                            val fields =
                                suiObject["data"].asJsonObject["content"].asJsonObject["fields"].asJsonObject
                            fields?.get("balance")?.let { balance ->
                                val index =
                                    fetcher.iotaBalances.indexOfFirst { it.first == coinType }
                                if (index != -1) {
                                    val alreadyAmount = fetcher.iotaBalances[index].second
                                    val sumAmount =
                                        alreadyAmount?.add(balance.asString.toBigDecimal())
                                    fetcher.iotaBalances[index] = Pair(coinType, sumAmount)

                                } else {
                                    val newAmount = balance.asString.toBigDecimal()
                                    fetcher.iotaBalances.add(Pair(coinType, newAmount))
                                }
                            }
                        }
                    }

                    if (stakesResult is NetworkResult.Success) {
                        stakesResult.data["result"].asJsonArray.forEach { stake ->
                            fetcher.iotaStakedList.add(stake.asJsonObject)
                        }
                    }

                    withContext(Dispatchers.Default) {
                        val coinMetaDeferred = fetcher.iotaBalances.map { (coinType, _) ->
                            async {
                                walletRepository.iotaCoinMetadata(fetcher, this@apply, coinType)
                            }
                        }

                        coinMetaDeferred.forEachIndexed { index, deferred ->
                            val coinMetadataResult = deferred.await()
                            if (coinMetadataResult is NetworkResult.Success && fetcher.iotaBalances.isNotEmpty()) {
                                fetcher.iotaBalances[index].first?.let { type ->
                                    val result = coinMetadataResult.data["result"]
                                    val resultData = when (result) {
                                        is JsonObject -> {
                                            result.asJsonObject
                                        }

                                        else -> {
                                            null
                                        }
                                    }
                                    if (resultData != null) {
                                        fetcher.iotaCoinMeta[type] = resultData
                                    }
                                }
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
                        fetcher.iotaBalanceAmount(IOTA_MAIN_DENOM).toString(),
                        "0",
                        fetcher.iotaBalances.size.toLong()
                    )
                    BaseData.updateRefAddressesMain(refAddress)
                    coinValue = fetcher.allAssetValue()
                    coinUsdValue = fetcher.allAssetValue(true)
                    coinCnt = fetcher.iotaBalances.size

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
        id: Long, chain: ChainBitCoin86, isEdit: Boolean? = false, isRefresh: Boolean? = false
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

                        when (val stakingResponse = walletRepository.bitStakingBalance(chain)) {
                            is NetworkResult.Success -> {
                                chain.btcFetcher()?.btcStakingData = stakingResponse.data
                            }

                            is NetworkResult.Error -> {
                                _chainDataErrorMessage.postValue("error type : ${stakingResponse.errorType}  error message : ${stakingResponse.errorMessage}")
                            }
                        }

                        when (val networkInfo = walletRepository.btcNetworkInfo(chain)) {
                            is NetworkResult.Success -> {
                                chain.btcFetcher()?.btcNetworkInfo = networkInfo.data
                            }

                            is NetworkResult.Error -> {
                                _chainDataErrorMessage.postValue("error type : ${networkInfo.errorType}  error message : ${networkInfo.errorMessage}")
                            }
                        }

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
                        coinValue = fetcher.allAssetValue()
                        coinUsdValue = fetcher.allAssetValue(true)
                        coinCnt =
                            if (chain.btcFetcher()?.btcBalances == BigDecimal.ZERO && chain.btcFetcher()?.btcPendingInput == BigDecimal.ZERO) 0 else 1

                        withContext(Dispatchers.Main) {
                            if (isEdit == true) {
                                editFetchedResult.value = tag
                            } else if (isRefresh == true) {
                                refreshStakingInfoFetchedResult.value = tag
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

                                    fetchState = FetchState.SUCCESS
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

                                    fetchState = FetchState.SUCCESS
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
                                    coinValue = fetcher.allAssetValue()
                                    coinUsdValue = fetcher.allAssetValue(true)
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
                                        fetcher.grc20Tokens.filter { userDisplayToken.contains(it.address) }
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
                                        gnoRpcFetcher?.allGrc20TokenValue(id, true)
                                            ?.toPlainString(),
                                        0
                                    )
                                    BaseData.updateRefAddressesToken(grcRefAddress)
                                    tokenValue = fetcher.allGrc20TokenValue(id)
                                    tokenUsdValue = fetcher.allGrc20TokenValue(id, true)
                                    tokenCnt = fetcher.displayTokenCnt(id)

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

    var refreshStakeData = SingleLiveEvent<Boolean>()

    fun refreshBtcStakeData(chain: ChainBitCoin86) = viewModelScope.launch(Dispatchers.IO) {
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

        refreshStakeData.postValue(true)
    }
}