package wannabit.io.cosmostaion.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.ByteUtils
import wannabit.io.cosmostaion.common.getChannel
import wannabit.io.cosmostaion.data.model.res.NetworkResult
import wannabit.io.cosmostaion.data.repository.wallet.WalletRepository
import wannabit.io.cosmostaion.database.model.BaseAccount
import wannabit.io.cosmostaion.database.model.RefAddress
import wannabit.io.cosmostaion.ui.main.CosmostationApp
import wannabit.io.cosmostaion.ui.viewmodel.event.SingleLiveEvent

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

    var walletEditResult = SingleLiveEvent<Pair<MutableList<String>, MutableList<String>>>()
    fun walletEdit(
        displayMainnetChains: MutableList<String>, displayTestnetChains: MutableList<String>
    ) = viewModelScope.launch(Dispatchers.IO) {
        walletEditResult.postValue(Pair(displayMainnetChains, displayTestnetChains))
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

    var styleOptionResult = SingleLiveEvent<Boolean>()
    fun styleOption(isChanged: Boolean) = viewModelScope.launch(Dispatchers.IO) {
        styleOptionResult.postValue(isChanged)
    }


    private val _chainDataErrorMessage = MutableLiveData<String>()
    val chainDataErrorMessage: LiveData<String> get() = _chainDataErrorMessage

    fun loadChainData(
        chain: BaseChain, baseAccountId: Long, isEdit: Boolean
    ) = CoroutineScope(Dispatchers.IO).launch {
        chain.apply {
            if (supportCw20 || supportErc20) {
                when (val response = walletRepository.token(this)) {
                    is NetworkResult.Success -> {
                        grpcFetcher.tokens = response.data
                        if (supportNft) {
                            when (val infoResponse = walletRepository.cw721Info(apiName)) {
                                is NetworkResult.Success -> {
                                    grpcFetcher.cw721s = infoResponse.data
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
                        grpcFetcher
                        grpcFetcher.cw721s = response.data
                    }

                    is NetworkResult.Error -> {
                        _chainDataErrorMessage.postValue("error type : ${response.errorType}  error message : ${response.errorMessage}")
                    }
                }
            }

            loadGrpcAuthData(this, baseAccountId, isEdit)

//            if (this is ChainOkt996Keccak) {
//                loadLcdData(this, baseAccountId, isEdit)
//            } else {
//                loadGrpcAuthData(this, baseAccountId, isEdit)
//            }
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
            getChannel(this)?.let { channel ->
                when (val response = walletRepository.auth(channel, this)) {
                    is NetworkResult.Success -> {
                        grpcFetcher.cosmosAuth = response.data?.account
//                        loadGrpcMoreData(channel, baseAccountId, chain, isEdit)
                    }

                    is NetworkResult.Error -> {
                        when (val balanceResponse = walletRepository.balance(channel, this)) {
                            is NetworkResult.Success -> {
                                balanceResponse.data?.balancesList?.let {
                                    grpcFetcher.cosmosBalances = it
                                }
                            }

                            is NetworkResult.Error -> {
                                grpcFetcher.cosmosBalances = null
                            }
                        }

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
    }

//    private fun loadGrpcMoreData(
//        channel: ManagedChannel, id: Long, line: CosmosLine, isEdit: Boolean
//    ) = CoroutineScope(Dispatchers.IO).launch {
//        line.apply {
//            try {
//                val loadBalanceDeferred = async { walletRepository.balance(channel, line) }
//                if (line.supportStaking) {
//                    val loadDelegationDeferred =
//                        async { walletRepository.delegation(channel, line) }
//                    val loadUnBondingDeferred = async { walletRepository.unBonding(channel, line) }
//                    val loadRewardDeferred = async { walletRepository.reward(channel, line) }
//                    val loadRewardAddressDeferred =
//                        async { walletRepository.rewardAddress(channel, line) }
//
//                    val responses = awaitAll(
//                        loadBalanceDeferred,
//                        loadDelegationDeferred,
//                        loadUnBondingDeferred,
//                        loadRewardDeferred,
//                        loadRewardAddressDeferred
//                    )
//
//                    responses.forEach { response ->
//                        when (response) {
//                            is NetworkResult.Success -> {
//                                when (response.data) {
//                                    is QueryProto.QueryAllBalancesResponse -> {
//                                        response.data.balancesList?.let { cosmosBalances = it }
//                                    }
//
//                                    is com.cosmos.staking.v1beta1.QueryProto.QueryDelegatorDelegationsResponse -> {
//                                        cosmosDelegations.clear()
//                                        response.data.delegationResponsesList.forEach { delegation ->
//                                            if (delegation.balance.amount != "0") {
//                                                cosmosDelegations.add(delegation)
//                                            }
//                                        }
//                                    }
//
//                                    is com.cosmos.staking.v1beta1.QueryProto.QueryDelegatorUnbondingDelegationsResponse -> {
//                                        response.data.unbondingResponsesList?.let {
//                                            cosmosUnbondings = it
//                                        }
//                                    }
//
//                                    is com.cosmos.distribution.v1beta1.QueryProto.QueryDelegationTotalRewardsResponse -> {
//                                        response.data.rewardsList?.let { cosmosRewards = it }
//                                    }
//
//                                    is String -> {
//                                        rewardAddress = response.data
//                                    }
//                                }
//                            }
//
//                            is NetworkResult.Error -> {
//                                _chainDataErrorMessage.postValue("error type : ${response.errorType}  error message : ${response.errorMessage}")
//                            }
//                        }
//                    }
//
//                } else {
//                    if (line is ChainNeutron) {
//                        val loadVaultDepositDeferred =
//                            async { walletRepository.vaultDeposit(channel, line) }
//                        val loadVestingDeferred =
//                            async { walletRepository.vestingData(channel, line) }
//
//                        val responses = awaitAll(
//                            loadBalanceDeferred, loadVestingDeferred, loadVaultDepositDeferred
//                        )
//
//                        responses.forEach { response ->
//                            when (response) {
//                                is NetworkResult.Success -> {
//                                    when (response.data) {
//                                        is QueryProto.QueryAllBalancesResponse -> {
//                                            response.data.balancesList?.let { cosmosBalances = it }
//                                        }
//
//                                        is com.cosmwasm.wasm.v1.QueryProto.QuerySmartContractStateResponse -> {
//                                            line.neutronVesting = Gson().fromJson(
//                                                response.data.data.toStringUtf8(),
//                                                VestingData::class.java
//                                            )
//                                        }
//
//                                        else -> {
//                                            line.neutronDeposited =
//                                                response.data.toString().toBigDecimal()
//                                        }
//                                    }
//                                }
//
//                                is NetworkResult.Error -> {
//                                    _chainDataErrorMessage.postValue("error type : ${response.errorType}  error message : ${response.errorMessage}")
//                                }
//                            }
//                        }
//                    }
//                }
//
//                BaseUtils.onParseVestingAccount(this)
//                fetched = true
//                if (fetched) {
//                    val refAddress = RefAddress(id,
//                        tag,
//                        address,
//                        ByteUtils.convertBech32ToEvm(address),
//                        allAssetValue(true).toPlainString(),
//                        allStakingDenomAmount().toString(),
//                        "0",
//                        cosmosBalances?.count { BaseData.getAsset(apiName, it.denom) != null }
//                            ?.toLong())
//                    BaseData.updateRefAddressesMain(refAddress)
//                    withContext(Dispatchers.Main) {
//                        if (isEdit) {
//                            editFetchedResult.value = tag
//                        } else {
//                            fetchedResult.value = tag
//                            txFetchedResult.value = tag
//                        }
//                    }
//
//                    if (supportCw20) {
//                        val tokenBalanceDeferredList = tokens.map { token ->
//                            async { walletRepository.cw20Balance(channel, line, token) }
//                        }
//
//                        tokenBalanceDeferredList.awaitAll()
//                        val cwRefAddress = RefAddress(
//                            id,
//                            tag,
//                            address,
//                            ByteUtils.convertBech32ToEvm(address),
//                            "0",
//                            "0",
//                            allTokenValue(true).toPlainString(),
//                            0
//                        )
//                        BaseData.updateRefAddressesToken(cwRefAddress)
//                        withContext(Dispatchers.Main) {
//                            if (isEdit) {
//                                editFetchedTokenResult.value = tag
//                            } else {
//                                fetchedTokenResult.value = tag
//                            }
//                        }
//                    }
//
//                    fetchedTotalResult.postValue(tag)
//                }
//
//            } finally {
//                channel.shutdown()
//                try {
//                    if (!channel.awaitTermination(5, TimeUnit.SECONDS)) {
//                        channel.shutdownNow()
//                    }
//                } catch (e: InterruptedException) {
//                    e.printStackTrace()
//                }
//            }
//        }
//    }

//    fun loadEvmChainData(line: EthereumLine, baseAccountId: Long, isEdit: Boolean) =
//        CoroutineScope(Dispatchers.IO).launch {
//            line.apply {
//                val userDisplayToken = Prefs.getDisplayErc20s(baseAccountId, tag)
//                if (supportCosmos) {
//                    if (this is ChainOktEvm) {
//                        val loadEvmTokenDeferred = async { walletRepository.evmToken(this@apply) }
//                        val loadEvmBalanceDeferred =
//                            async { walletRepository.evmBalance(this@apply) }
//
//                        val loadAccountInfoDeferred =
//                            async { walletRepository.oktAccountInfo(this@apply) }
//                        val loadDepositDeferred = async { walletRepository.oktDeposit(this@apply) }
//                        val loadWithdrawDeferred =
//                            async { walletRepository.oktWithdraw(this@apply) }
//                        val loadTokenDeferred = async { walletRepository.oktToken(this@apply) }
//
//                        val responses = awaitAll(
//                            loadEvmTokenDeferred,
//                            loadEvmBalanceDeferred,
//                            loadAccountInfoDeferred,
//                            loadDepositDeferred,
//                            loadWithdrawDeferred,
//                            loadTokenDeferred
//                        )
//
//                        responses.forEach { response ->
//                            when (response) {
//                                is NetworkResult.Success -> {
//                                    when (response.data) {
//                                        is MutableList<*> -> {
//                                            if (response.data.all { it is Token }) {
//                                                evmTokens = response.data as MutableList<Token>
//                                            }
//                                        }
//
//                                        is String -> {
//                                            line.web3j = Web3j.build(HttpService(getEvmRpc()))
//                                            evmBalance = response.data.toBigDecimal()
//                                        }
//
//                                        is OktAccountResponse -> {
//                                            oktLcdAccountInfo = response.data
//                                        }
//
//                                        is OktDepositedResponse -> {
//                                            oktDepositedInfo = response.data
//                                        }
//
//                                        is OktWithdrawResponse -> {
//                                            oktWithdrawInfo = response.data
//                                        }
//
//                                        is OktTokenResponse -> {
//                                            oktTokenInfo = response.data
//                                        }
//                                    }
//                                }
//
//                                is NetworkResult.Error -> {
//                                    line.web3j = null
//                                }
//                            }
//                        }
//
//                        fetched = true
//                        if (fetched) {
//                            if (oktLcdAccountInfo?.value?.address?.isNotEmpty() == true) {
//                                val refAddress = RefAddress(
//                                    baseAccountId,
//                                    tag,
//                                    address,
//                                    ByteUtils.convertBech32ToEvm(address),
//                                    allAssetValue(true).toString(),
//                                    lcdBalanceAmount(stakeDenom).toString(),
//                                    "0",
//                                    oktLcdAccountInfo?.value?.coins?.size?.toLong() ?: 0
//                                )
//                                BaseData.updateRefAddressesMain(refAddress)
//                                withContext(Dispatchers.Main) {
//                                    if (isEdit) {
//                                        editFetchedResult.value = tag
//                                    } else {
//                                        fetchedResult.value = tag
//                                    }
//                                }
//
//                                val tokenBalanceDeferredList = evmTokens.map { token ->
//                                    async { walletRepository.erc20Balance(line, token) }
//                                }
//
//                                tokenBalanceDeferredList.awaitAll()
//                                val evmRefAddress = RefAddress(
//                                    baseAccountId,
//                                    tag,
//                                    address,
//                                    ByteUtils.convertBech32ToEvm(address),
//                                    "0",
//                                    "0",
//                                    allTokenValue(true).toPlainString(),
//                                    0
//                                )
//                                BaseData.updateRefAddressesToken(evmRefAddress)
//                                withContext(Dispatchers.Main) {
//                                    if (isEdit) {
//                                        editFetchedTokenResult.value = tag
//                                    } else {
//                                        fetchedTokenResult.value = tag
//                                    }
//                                }
//                                fetchedTotalResult.postValue(tag)
//
//                            } else {
//                                val refAddress = RefAddress(
//                                    baseAccountId,
//                                    tag,
//                                    address,
//                                    ByteUtils.convertBech32ToEvm(address),
//                                    "0",
//                                    "0",
//                                    "0",
//                                    0
//                                )
//                                BaseData.updateRefAddressesMain(refAddress)
//                                withContext(Dispatchers.Main) {
//                                    if (isEdit) {
//                                        editFetchedResult.value = tag
//                                    } else {
//                                        fetchedResult.value = tag
//                                    }
//                                }
//                            }
//                        }
//
//                    } else {
//                        val loadEvmTokenDeferred = async { walletRepository.evmToken(this@apply) }
//                        val loadEvmBalanceDeferred =
//                            async { walletRepository.evmBalance(this@apply) }
//
//                        val tokenResult = loadEvmTokenDeferred.await()
//                        val balanceResult = loadEvmBalanceDeferred.await()
//
//                        if (tokenResult is NetworkResult.Success && tokenResult.data is MutableList<*> && tokenResult.data.all { it is Token }) {
//                            evmTokens = tokenResult.data
//                        }
//
//                        if (balanceResult is NetworkResult.Success && balanceResult.data is String) {
//                            line.web3j = Web3j.build(HttpService(getEvmRpc()))
//                            evmBalance = balanceResult.data.toBigDecimal()
//                        } else if (balanceResult is NetworkResult.Error) {
//                            line.web3j = null
//                        }
//                        loadGrpcAuthData(this, baseAccountId, isEdit)
//
//                        val tokenBalanceDeferredList = evmTokens.map { token ->
//                            async { walletRepository.erc20Balance(line, token) }
//                        }
//
//                        tokenBalanceDeferredList.awaitAll()
//                        val evmRefAddress = RefAddress(baseAccountId,
//                            tag,
//                            address,
//                            ByteUtils.convertBech32ToEvm(address),
//                            "0",
//                            "0",
//                            allTokenValue(true).toPlainString(),
//                            cosmosBalances?.count { BaseData.getAsset(apiName, it.denom) != null }
//                                ?.toLong() ?: 0L)
//                        BaseData.updateRefAddressesToken(evmRefAddress)
//                        withContext(Dispatchers.Main) {
//                            if (isEdit) {
//                                editFetchedTokenResult.value = tag
//                            } else {
//                                fetchedTokenResult.value = tag
//                            }
//                        }
//                        fetchedTotalResult.postValue(tag)
//                    }
//
//                } else {
//                    val loadEvmTokenDeferred = async { walletRepository.evmToken(this@apply) }
//                    val loadEvmBalanceDeferred = async { walletRepository.evmBalance(this@apply) }
//
//                    val tokenResult = loadEvmTokenDeferred.await()
//                    val balanceResult = loadEvmBalanceDeferred.await()
//
//                    if (tokenResult is NetworkResult.Success && tokenResult.data is MutableList<*> && tokenResult.data.all { it is Token }) {
//                        evmTokens = tokenResult.data
//                    }
//
//                    if (balanceResult is NetworkResult.Success && balanceResult.data is String) {
//                        line.web3j = Web3j.build(HttpService(getEvmRpc()))
//                        evmBalance = balanceResult.data.toBigDecimal()
//                    } else if (balanceResult is NetworkResult.Error) {
//                        line.web3j = null
//                    }
//
//                    fetched = true
//                    if (fetched) {
//                        val refAddress = RefAddress(
//                            baseAccountId,
//                            tag,
//                            "",
//                            address,
//                            allAssetValue(true).toString(),
//                            evmBalance.toString(),
//                            "0",
//                            if (evmBalance <= BigDecimal.ZERO) 0 else 1
//                        )
//                        BaseData.updateRefAddressesMain(refAddress)
//                        withContext(Dispatchers.Main) {
//                            if (isEdit) {
//                                editFetchedResult.value = tag
//                            } else {
//                                fetchedResult.value = tag
//                            }
//                        }
//
//                        val tokenBalanceDeferredList = if (userDisplayToken == null) {
//                            evmTokens.filter { it.default }.map { token ->
//                                async { walletRepository.erc20Balance(line, token) }
//                            }
//
//                        } else {
//                            evmTokens.filter { userDisplayToken.contains(it.address) }
//                                .map { token ->
//                                    async { walletRepository.erc20Balance(line, token) }
//                                }
//                        }
//
//                        tokenBalanceDeferredList.awaitAll()
//                        val evmRefAddress = RefAddress(
//                            baseAccountId,
//                            tag,
//                            "",
//                            address,
//                            "0",
//                            "0",
//                            allTokenValue(true).toPlainString(),
//                            0
//                        )
//                        BaseData.updateRefAddressesToken(evmRefAddress)
//                        withContext(Dispatchers.Main) {
//                            if (isEdit) {
//                                editFetchedTokenResult.value = tag
//                            } else {
//                                fetchedTokenResult.value = tag
//                            }
//                        }
//                        fetchedTotalResult.postValue(tag)
//                    }
//                }
//            }
//        }

//    private fun loadLcdData(
//        line: CosmosLine, baseAccountId: Long, isEdit: Boolean
//    ) = CoroutineScope(Dispatchers.IO).launch {
//        line.apply {
//            if (this is ChainOkt996Keccak) {
//                val loadAccountInfoDeferred = async { walletRepository.oktAccountInfo(line) }
//                val loadDepositDeferred = async { walletRepository.oktDeposit(line) }
//                val loadWithdrawDeferred = async { walletRepository.oktWithdraw(line) }
//                val loadTokenDeferred = async { walletRepository.oktToken(line) }
//
//                val responses = awaitAll(
//                    loadAccountInfoDeferred,
//                    loadDepositDeferred,
//                    loadWithdrawDeferred,
//                    loadTokenDeferred
//                )
//
//                responses.forEach { response ->
//                    when (response) {
//                        is NetworkResult.Success -> {
//                            when (response.data) {
//                                is OktAccountResponse -> {
//                                    oktLcdAccountInfo = response.data
//                                }
//
//                                is OktDepositedResponse -> {
//                                    oktDepositedInfo = response.data
//                                }
//
//                                is OktWithdrawResponse -> {
//                                    oktWithdrawInfo = response.data
//                                }
//
//                                is OktTokenResponse -> {
//                                    oktTokenInfo = response.data
//                                }
//                            }
//                        }
//
//                        is NetworkResult.Error -> {
//                            _chainDataErrorMessage.postValue("error type : ${response.errorType}  error message : ${response.errorMessage}")
//                        }
//                    }
//                }
//
//                fetched = true
//                if (fetched) {
//                    if (oktLcdAccountInfo?.value?.address?.isNotEmpty() == true) {
//                        val refAddress = RefAddress(
//                            baseAccountId,
//                            tag,
//                            address,
//                            ByteUtils.convertBech32ToEvm(address),
//                            allAssetValue(true).toString(),
//                            lcdBalanceAmount(stakeDenom).toString(),
//                            "0",
//                            oktLcdAccountInfo?.value?.coins?.size?.toLong() ?: 0
//                        )
//                        BaseData.updateRefAddressesMain(refAddress)
//                        withContext(Dispatchers.Main) {
//                            if (isEdit) {
//                                editFetchedResult.postValue(tag)
//                            } else {
//                                fetchedResult.value = tag
//                            }
//                        }
//
//                        val tokenBalanceDeferredList = tokens.map { token ->
//                            async { walletRepository.erc20Balance(line, token) }
//                        }
//
//                        tokenBalanceDeferredList.awaitAll()
//                        val evmRefAddress = RefAddress(
//                            baseAccountId,
//                            tag,
//                            address,
//                            ByteUtils.convertBech32ToEvm(address),
//                            "0",
//                            "0",
//                            allTokenValue(true).toPlainString()
//                        )
//                        BaseData.updateRefAddressesToken(evmRefAddress)
//                        withContext(Dispatchers.Main) {
//                            if (isEdit) {
//                                editFetchedTokenResult.postValue(tag)
//                            } else {
//                                fetchedTokenResult.value = tag
//                            }
//
//                            fetchedTotalResult.postValue(tag)
//                        }
//
//                    } else {
//                        val refAddress = RefAddress(
//                            baseAccountId,
//                            tag,
//                            address,
//                            ByteUtils.convertBech32ToEvm(address),
//                            "0",
//                            "0",
//                            "0",
//                            0
//                        )
//                        BaseData.updateRefAddressesMain(refAddress)
//                        withContext(Dispatchers.Main) {
//                            if (isEdit) {
//                                editFetchedResult.postValue(tag)
//                            } else {
//                                fetchedResult.value = tag
//                            }
//                        }
//                    }
//                }
//            }
//        }
//    }

    private var _filterDataResult = MutableLiveData<Boolean>()
    val filterDataResult: LiveData<Boolean> get() = _filterDataResult
    fun updateFilterData(isShowAll: Boolean) = viewModelScope.launch(Dispatchers.IO) {
        _filterDataResult.postValue(isShowAll)
    }
}