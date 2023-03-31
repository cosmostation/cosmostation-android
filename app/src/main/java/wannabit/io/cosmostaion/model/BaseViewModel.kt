package wannabit.io.cosmostaion.model

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

open class BaseViewModel : ViewModel() {
    private val job = SupervisorJob()
    protected val scope = CoroutineScope(Dispatchers.Main + job)

    override fun onCleared() {
        job.cancel()
        super.onCleared()
    }
}