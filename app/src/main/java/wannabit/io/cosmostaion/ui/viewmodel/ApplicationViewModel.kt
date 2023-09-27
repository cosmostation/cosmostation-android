package wannabit.io.cosmostaion.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import wannabit.io.cosmostaion.ui.main.CosmostationApp
import java.math.BigDecimal

class ApplicationViewModel(application: Application) : AndroidViewModel(application) {
    companion object {
        val shared
            get() = CosmostationApp.instance.applicationViewModel
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


    fun test(): BigDecimal {
        return BigDecimal.ZERO
    }
}