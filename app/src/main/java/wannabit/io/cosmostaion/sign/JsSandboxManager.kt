package wannabit.io.cosmostaion.sign

import android.content.Context
import androidx.javascriptengine.JavaScriptSandbox
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex

object JsSandboxManager {
    private var sandbox: JavaScriptSandbox? = null
    private var connecting: CompletableDeferred<JavaScriptSandbox>? = null
    private val lock = Mutex()

    suspend fun getSandbox(context: Context): JavaScriptSandbox {
        lock.lock()
        try {
            sandbox?.let { return it }
            if (connecting == null) {
                connecting = CompletableDeferred()
                CoroutineScope(Dispatchers.IO).launch {
                    try {
                        val sb = JavaScriptSandbox
                            .createConnectedInstanceAsync(context.applicationContext)
                            .get()
                        sandbox = sb
                        connecting?.complete(sb)
                    } catch (e: Exception) {
                        connecting?.completeExceptionally(e)
                    }
                }
            }
            return connecting!!.await()

        } finally {
            lock.unlock()
        }
    }

    fun close() {
        sandbox?.close()
        sandbox = null
        connecting = null
    }
}