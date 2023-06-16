package wannabit.io.cosmostaion.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DashboardViewModel : ViewModel() {
    val balances = MutableLiveData<List<String>>()

    fun loadBalances() = CoroutineScope(Dispatchers.IO).launch {

    }
}