package wannabit.io.cosmostaion.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import wannabit.io.cosmostaion.database.model.Chain
import wannabit.io.cosmostaion.database.model.ChainConfig

class DashboardViewModel : ViewModel() {
    val balances = MutableLiveData<List<String>>()

    fun loadBalances() = CoroutineScope(Dispatchers.IO).launch {
        Chain.allChains().forEach {
            when (it.chainConfig) {
                is ChainConfig.Cosmos -> {
                    //TODO balanceDao.update()
                }
                is ChainConfig.Ethereum -> {}
                else -> {}
            }
        }
    }
}