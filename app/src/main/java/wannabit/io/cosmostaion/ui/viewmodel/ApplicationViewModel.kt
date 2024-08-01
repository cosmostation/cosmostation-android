package wannabit.io.cosmostaion.ui.viewmodel

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
import org.web3j.protocol.Web3j
import org.web3j.protocol.http.HttpService
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.cosmosClass.ChainNeutron
import wannabit.io.cosmostaion.chain.cosmosClass.ChainOkt996Keccak
import wannabit.io.cosmostaion.chain.evmClass.ChainOktEvm
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.BaseUtils
import wannabit.io.cosmostaion.common.ByteUtils
import wannabit.io.cosmostaion.data.model.res.NetworkResult
import wannabit.io.cosmostaion.data.model.res.Token
import wannabit.io.cosmostaion.data.model.res.VestingData
import wannabit.io.cosmostaion.data.repository.wallet.WalletRepository
import wannabit.io.cosmostaion.database.Prefs
import wannabit.io.cosmostaion.database.model.BaseAccount
import wannabit.io.cosmostaion.database.model.RefAddress
import wannabit.io.cosmostaion.ui.main.CosmostationApp
import wannabit.io.cosmostaion.ui.viewmodel.event.SingleLiveEvent
import java.math.BigDecimal
import java.util.concurrent.TimeUnit

class ApplicationViewModel(
    application: Application, private val walletRepository: WalletRepository
) : AndroidViewModel(application) {
    companion object {
        val shared
            get() = CosmostationApp.instance.applicationViewModel
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

    private val _chainDataErrorMessage = MutableLiveData<String>()
    val chainDataErrorMessage: LiveData<String> get() = _chainDataErrorMessage

    fun loadChainData(
        chain: BaseChain, baseAccountId: Long, isEdit: Boolean
    ) = CoroutineScope(Dispatchers.IO).launch {
        chain.apply {
            cosmosFetcher()?.let {
                if (supportCw20 || supportEvm) {
                    when (val response = walletRepository.token(this)) {
                        is NetworkResult.Success -> {
                            cosmosFetcher?.tokens = response.data
                            if (supportNft) {
                                when (val infoResponse = walletRepository.cw721Info(apiName)) {
                                    is NetworkResult.Success -> {
                                        cosmosFetcher?.cw721s = infoResponse.data
                                    }

                                    is NetworkResult.Error -> {
                                        _chainDataErrorMessage.postValue("error type : ${infoResponse.errorType}  error message : ${infoResponse.errorMessage}")
                                    }
                                }
                            }
                        }

                        is NetworkResult.Error -> {
                            _chainDataErrorMessage.postValue("error type : ${response.errorType}  error message : ${response.errorMessage}")
                        }
                    }

                } else if (supportNft) {
                    when (val response = walletRepository.cw721Info(apiName)) {
                        is NetworkResult.Success -> {
                            cosmosFetcher?.cw721s = response.data
                        }

                        is NetworkResult.Error -> {
                            _chainDataErrorMessage.postValue("error type : ${response.errorType}  error message : ${response.errorMessage}")
                        }
                    }
                }
            }

            if (this is ChainOkt996Keccak) {
                loadOktLcdData(this, baseAccountId, isEdit)
            } else {
                if (supportEvm) {
                    loadEvmChainData(this, baseAccountId, isEdit)
                }
                if (supportCosmos()) {
                    loadGrpcAuthData(this, baseAccountId, isEdit)
                }
            }
        }
    }

    var fetchedResult = SingleLiveEvent<String>()
    var fetchedTokenResult = SingleLiveEvent<String>()

    var fetchedTotalResult = SingleLiveEvent<String>()

    var editFetchedResult = SingleLiveEvent<String>()
    var editFetchedTokenResult = SingleLiveEvent<String>()

    var txFetchedResult = SingleLiveEvent<String>()

    private fun loadGrpcAuthData(
        chain: BaseChain, baseAccountId: Long, isEdit: Boolean
    ) = CoroutineScope(Dispatchers.IO).launch {
        chain.apply {
            val channel = chain.cosmosFetcher?.getChannel()
            when (walletRepository.auth(channel, this)) {
                is NetworkResult.Success -> {
                    fetchedState = false
                    withContext(Dispatchers.Main) {
                        fetchedResult.value = tag
                    }
                    loadGrpcMoreData(channel, baseAccountId, chain, isEdit)
                }

                is NetworkResult.Error -> {
                    val loadBalanceDeferred = async { walletRepository.balance(channel, chain) }
                    val loadRewardAddressDeferred =
                        async { walletRepository.rewardAddress(channel, chain) }
                    val loadFeeMarketDeferred = async { walletRepository.baseFee(channel, chain) }

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

                    fetchedState = false
                    withContext(Dispatchers.Main) {
                        fetchedResult.value = tag
                    }
                    delay(2000)

                    fetchedState = true
                    fetched = true
                    if (fetched) {
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
                        withContext(Dispatchers.Main) {
                            if (isEdit) {
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

    private fun loadGrpcMoreData(
        channel: ManagedChannel?, id: Long, chain: BaseChain, isEdit: Boolean
    ) = CoroutineScope(Dispatchers.IO).launch {
        chain.apply {
            try {
                val loadBalanceDeferred = async { walletRepository.balance(channel, chain) }
                if (chain.supportStaking) {
                    val loadDelegationDeferred =
                        async { walletRepository.delegation(channel, chain) }
                    val loadUnBondingDeferred = async { walletRepository.unBonding(channel, chain) }
                    val loadRewardDeferred = async { walletRepository.reward(channel, chain) }
                    val loadRewardAddressDeferred =
                        async { walletRepository.rewardAddress(channel, chain) }
                    val loadFeeMarketDeferred = async { walletRepository.baseFee(channel, chain) }

                    val balanceResult = loadBalanceDeferred.await()
                    val delegationResult = loadDelegationDeferred.await()
                    val unBondingResult = loadUnBondingDeferred.await()
                    val rewardResult = loadRewardDeferred.await()
                    val rewardAddressResult = loadRewardAddressDeferred.await()
                    val feeMarketResult = loadFeeMarketDeferred.await()

                    if (balanceResult is NetworkResult.Success && balanceResult.data is MutableList<*>) {
                        cosmosFetcher?.cosmosBalances = balanceResult.data
                    } else if (balanceResult is NetworkResult.Error) {
                        _chainDataErrorMessage.postValue("error type : ${balanceResult.errorType}  error message : ${balanceResult.errorMessage}")
                    }
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

                } else {
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
                    }
                }

                BaseUtils.onParseVesting(this)
                fetchedState = true
                fetched = true
                if (fetched) {
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
                    withContext(Dispatchers.Main) {
                        if (isEdit) {
                            editFetchedResult.value = tag
                        } else {
                            fetchedResult.value = tag
                            txFetchedResult.value = tag
                        }
                    }

                    if (supportCw20) {
                        val tokenBalanceDeferredList = cosmosFetcher?.tokens?.map { token ->
                            async { walletRepository.cw20Balance(channel, chain, token) }
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
                        withContext(Dispatchers.Main) {
                            if (isEdit) {
                                editFetchedTokenResult.value = tag
                            } else {
                                fetchedTokenResult.value = tag
                            }
                        }
                    }

                    fetchedTotalResult.postValue(tag)
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

    fun loadEvmChainData(chain: BaseChain, baseAccountId: Long, isEdit: Boolean) =
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
                        fetched = true
                        if (fetched) {
                            if (oktFetcher()?.oktAccountInfo?.isJsonNull != true) {
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
                                withContext(Dispatchers.Main) {
                                    if (isEdit) {
                                        editFetchedResult.postValue(tag)
                                    } else {
                                        fetchedResult.value = tag
                                    }
                                }

                                val tokenBalanceDeferredList =
                                    evmRpcFetcher.evmTokens.map { token ->
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
                                withContext(Dispatchers.Main) {
                                    if (isEdit) {
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
                                    if (isEdit) {
                                        editFetchedResult.postValue(tag)
                                    } else {
                                        fetchedResult.value = tag
                                    }
                                }
                            }
                        }

                    } else if (supportCosmos()) {
                        val tokenBalanceDeferredList = evmRpcFetcher.evmTokens.map { token ->
                            async { walletRepository.erc20Balance(this@apply, token) }
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
                            cosmosFetcher?.cosmosBalances?.count {
                                BaseData.getAsset(
                                    apiName, it.denom
                                ) != null
                            }?.toLong() ?: 0L
                        )
                        BaseData.updateRefAddressesToken(evmRefAddress)
                        withContext(Dispatchers.Main) {
                            if (isEdit) {
                                editFetchedTokenResult.value = tag
                            } else {
                                fetchedTokenResult.value = tag
                            }
                        }
                        fetchedTotalResult.postValue(tag)

                    } else {
                        fetched = true
                        if (fetched) {
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
                            withContext(Dispatchers.Main) {
                                if (isEdit) {
                                    editFetchedResult.value = tag
                                } else {
                                    fetchedResult.value = tag
                                }
                            }

                            val tokenBalanceDeferredList = if (userDisplayToken == null) {
                                evmRpcFetcher.evmTokens.filter { it.default }.map { token ->
                                    async { walletRepository.erc20Balance(this@apply, token) }
                                }

                            } else {
                                evmRpcFetcher.evmTokens.filter { userDisplayToken.contains(it.address) }
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
                            withContext(Dispatchers.Main) {
                                if (isEdit) {
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
        chain: BaseChain, baseAccountId: Long, isEdit: Boolean
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

                fetched = true
                if (fetched) {
                    if (oktFetcher()?.oktAccountInfo?.isJsonNull != true) {
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
                        withContext(Dispatchers.Main) {
                            if (isEdit) {
                                editFetchedResult.postValue(tag)
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
                            if (isEdit) {
                                editFetchedResult.postValue(tag)
                            } else {
                                fetchedResult.value = tag
                            }
                        }
                    }
                }
            }
        }
    }

    private var _filterDataResult = MutableLiveData<Boolean>()
    val filterDataResult: LiveData<Boolean> get() = _filterDataResult
    fun updateFilterData(isShowAll: Boolean) = viewModelScope.launch(Dispatchers.IO) {
        _filterDataResult.postValue(isShowAll)
    }
}