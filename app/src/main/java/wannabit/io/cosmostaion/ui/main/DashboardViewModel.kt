package wannabit.io.cosmostaion.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class DashboardViewModel : ViewModel() {
    val balances = MutableLiveData<List<String>>()

    fun loadBalances() = CoroutineScope(Dispatchers.IO).launch {

    }

    private var _chainList = MutableLiveData<List<String>>()
    val chainList: LiveData<List<String>> get() = _chainList

    fun loadChainList() = CoroutineScope(Dispatchers.IO).launch {
        val supportList: MutableList<String> = mutableListOf()
        val cosmosList: MutableList<String> = mutableListOf("Cosmos", "Osmosis", "Neutron", "Akash")
        val ethList: MutableList<String> = mutableListOf("ETH", "Sui", "Aptos", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "")

        supportList.addAll(cosmosList)
        supportList.addAll(ethList)
        _chainList.postValue(supportList)
    }
}