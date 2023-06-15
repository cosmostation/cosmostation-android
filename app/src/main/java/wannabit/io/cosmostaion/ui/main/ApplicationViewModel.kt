package wannabit.io.cosmostaion.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.cosmos.bank.v1beta1.QueryGrpc
import com.cosmos.bank.v1beta1.QueryProto
import com.cosmos.base.query.v1beta1.PaginationProto
import io.grpc.ManagedChannelBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.web3j.protocol.Web3j
import org.web3j.protocol.core.DefaultBlockParameterName
import org.web3j.protocol.http.HttpService
import wannabit.io.cosmostaion.database.AppDatabase
import wannabit.io.cosmostaion.database.Prefs
import wannabit.io.cosmostaion.database.model.Balance
import wannabit.io.cosmostaion.database.model.Chain
import wannabit.io.cosmostaion.database.model.ChainConfig
import wannabit.io.cosmostaion.database.model.Wallet
import wannabit.io.cosmostaion.network.MintscanService
import wannabit.io.cosmostaion.network.model.Price
import java.util.Date
import java.util.concurrent.TimeUnit

class ApplicationViewModel(application: Application) : AndroidViewModel(application) {
    companion object {
        val shared
            get() = CosmostationApp.instance.applicationViewModel
    }

    val pricesLiveData = MutableLiveData<List<Price>>()
    val balancesLiveData = MutableLiveData<List<Balance>>()
    //TODO apply to Transformations.map

    val currentWalletLiveData = MutableLiveData<Wallet>()

    fun loadPrices() = CoroutineScope(Dispatchers.IO).launch {
        try {
            val result = MintscanService.create().price()
            pricesLiveData.postValue(result)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun loadBalances() = CoroutineScope(Dispatchers.IO).launch {
        val wallet = AppDatabase.getInstance().walletDao().selectById(Prefs.lastUserId)!!
        Chain.allChains().forEach { chain ->
            when (chain.chainConfig) {
                is ChainConfig.Cosmos -> {
                    val address = chain.chainConfig.getAddress(wallet.seed!!)
//                    val balance = chain.chainConfig.getBalance(address)
                    val channel = ManagedChannelBuilder.forAddress(chain.chainConfig.grpcUrl, 443).useTransportSecurity().build()
                    val stub = QueryGrpc.newBlockingStub(channel).withDeadlineAfter(5, TimeUnit.SECONDS)
                    val pageRequest = PaginationProto.PageRequest.newBuilder().setLimit(2000).build()
                    val request = QueryProto.QueryAllBalancesRequest.newBuilder().setPagination(pageRequest).setAddress(address).build()
                    val response = stub.allBalances(request)
                    response.balancesList.forEach {
                        AppDatabase.getInstance().balanceDao().insert(Balance(wallet.id, chain.chainName, it.denom, it.amount, Date().time))
                    }
                }

                is ChainConfig.Ethereum -> {
                    val address = chain.chainConfig.getAddress(wallet.seed!!)
                    val web3 = Web3j.build(HttpService("https://rpc.flashbots.net"))
                    val response = web3.ethGetBalance(address, DefaultBlockParameterName.LATEST).sendAsync().get()
                    AppDatabase.getInstance().balanceDao().insert(Balance(wallet.id, chain.chainName, chain.chainConfig.displayDenom, response.balance.toString(), Date().time))
                }

                else -> {}
            }
            balancesLiveData.postValue(AppDatabase.getInstance().balanceDao().selectAll())
        }
    }

}