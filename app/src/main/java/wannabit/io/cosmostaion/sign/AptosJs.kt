package wannabit.io.cosmostaion.sign

import android.content.Context
import androidx.javascriptengine.JavaScriptIsolate
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object AptosJs {
    private var jsIsolate: JavaScriptIsolate? = null
    private var initializationDeferred: CompletableDeferred<Boolean>? = null
    private var isInitialized: Boolean = false

    fun initialize(context: Context): CompletableDeferred<Boolean> {
        if (initializationDeferred != null) return initializationDeferred!!
        initializationDeferred = CompletableDeferred()
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val sandbox = JsSandboxManager.getSandbox(context)
                jsIsolate = sandbox.createIsolate()

                val jsCode = context.assets.open("aptos.js")
                    .bufferedReader().use { it.readText() }
                jsIsolate?.evaluateJavaScriptAsync(jsCode)?.get()

                isInitialized = true
                initializationDeferred?.complete(true)

            } catch (e: Exception) {
                isInitialized = false
                initializationDeferred?.complete(false)
            }
        }
        return initializationDeferred!!
    }

    fun executeFunction(functionCall: String): String? {
        return jsIsolate?.evaluateJavaScriptAsync(functionCall)?.get()
    }

    fun mergeFunction(createTx: String) {
        jsIsolate?.evaluateJavaScriptAsync(createTx)?.get()
    }

    fun terminate() {
        jsIsolate?.close()
        jsIsolate = null
        initializationDeferred = null
    }

    fun isInitialized(): Boolean {
        return isInitialized
    }
}