package wannabit.io.cosmostaion.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.chain.EthereumLine
import wannabit.io.cosmostaion.chain.Line
import wannabit.io.cosmostaion.database.AppDatabase
import wannabit.io.cosmostaion.database.model.Balance
import wannabit.io.cosmostaion.database.model.BaseAccount
import wannabit.io.cosmostaion.database.model.Wallet
import wannabit.io.cosmostaion.network.MintscanService
import wannabit.io.cosmostaion.network.model.Price

class ApplicationViewModel(application: Application) : AndroidViewModel(application) {
    companion object {
        val shared
            get() = CosmostationApp.instance.applicationViewModel
    }

    val pricesLiveData = MutableLiveData<List<Price>>()
    val balancesLiveData = MutableLiveData<List<Balance>>()
    val supportChains = MutableLiveData<List<Line>>()
    //TODO apply to Transformations.map

    val currentWalletLiveData = MutableLiveData<Wallet>()
    val currentBaseAccountLiveData = MutableLiveData<BaseAccount>()

    fun loadSupportChains() = CoroutineScope(Dispatchers.IO).launch {
        val lines = mutableListOf<Line>()
        val cosmos = CosmosLine("Cosmos", "", "118", BaseChain.Cosmos("cosmoshub-4", "cosmos", "uatom", "Cosmos", 6, "ATOM", "", "", "", "grpc-cosmos.cosmostation.io"))
        lines.add(cosmos)
        val osmosis = CosmosLine("Osmosis", "", "118", BaseChain.Cosmos("osmosis-1", "osmo", "uosmo", "Osmosis", 6, "OSMO", "", "", "", "grpc-osmosis.cosmostation.io"))
        lines.add(osmosis)
        val ethereum = EthereumLine("Ethereum", "", "60", BaseChain.Ethereum("", "", "https://rpc.flashbots.net", "Ethereum", 18, "ETH"))
        lines.add(ethereum)
        supportChains.postValue(lines)
    }

    fun loadPrices() = CoroutineScope(Dispatchers.IO).launch {
        try {
            val result = MintscanService.create().price()
            pricesLiveData.postValue(result)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun loadBalances() = CoroutineScope(Dispatchers.IO).launch {
        currentWalletLiveData.value?.let { wallet ->
            supportChains.value?.forEach {
                it.loadBalances(wallet)
            }
            balancesLiveData.postValue(AppDatabase.getInstance().balanceDao().selectAll())
        }
    }

//    fun loadBalances() = CoroutineScope(Dispatchers.IO).launch {
//        val wallet = AppDatabase.getInstance().walletDao().selectById(Prefs.lastUserId)!!
//        Chain.allChains().forEach { chain ->
//            when (chain.chainConfig) {
//                is ChainConfig.Cosmos -> {
//                    val address = chain.chainConfig.getAddress(wallet.seed!!)
////                    val balance = chain.chainConfig.getBalance(address)
//                    val channel = ManagedChannelBuilder.forAddress(chain.chainConfig.grpcUrl, 443).useTransportSecurity().build()
//                    val stub = QueryGrpc.newBlockingStub(channel).withDeadlineAfter(5, TimeUnit.SECONDS)
//                    val pageRequest = PaginationProto.PageRequest.newBuilder().setLimit(2000).build()
//                    val request = QueryProto.QueryAllBalancesRequest.newBuilder().setPagination(pageRequest).setAddress(address).build()
//                    val response = stub.allBalances(request)
//                    response.balancesList.forEach {
//                        AppDatabase.getInstance().balanceDao().insert(Balance(wallet.id, chain.chainName, it.denom, it.amount, Date().time))
//                    }
//                }
//
//                is ChainConfig.Ethereum -> {
//                    val address = chain.chainConfig.getAddress(wallet.seed!!)
//                    val web3 = Web3j.build(HttpService("https://rpc.flashbots.net"))
//                    val response = web3.ethGetBalance(address, DefaultBlockParameterName.LATEST).sendAsync().get()
//                    AppDatabase.getInstance().balanceDao().insert(Balance(wallet.id, chain.chainName, chain.chainConfig.displayDenom, response.balance.toString(), Date().time))
//                }
//
//                else -> {}
//            }
//            balancesLiveData.postValue(AppDatabase.getInstance().balanceDao().selectAll())
//        }
//    }

}