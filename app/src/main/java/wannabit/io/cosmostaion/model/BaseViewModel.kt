package wannabit.io.cosmostaion.model

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob

open class BaseViewModel : ViewModel() {
    private val job = SupervisorJob()
    protected val scope = CoroutineScope(Dispatchers.Main + job)

    private val backJob = Job()
    protected val backScope = CoroutineScope(Dispatchers.IO + job)

    override fun onCleared() {
        job.cancel()
        backJob.cancel()
        super.onCleared()
    }
}