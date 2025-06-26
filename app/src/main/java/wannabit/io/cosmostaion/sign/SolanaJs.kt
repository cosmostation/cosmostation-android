package wannabit.io.cosmostaion.sign

import android.content.Context
import androidx.javascriptengine.JavaScriptIsolate
import androidx.javascriptengine.JavaScriptSandbox
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


object SolanaJs {
    private var sandbox: JavaScriptSandbox? = null
    private var jsIsolate: JavaScriptIsolate? = null
    private var initializationDeferred: CompletableDeferred<Boolean>? = null
    private var isInitialized: Boolean = false

    fun initialize(context: Context): CompletableDeferred<Boolean> {
        if (initializationDeferred == null) {
            initializationDeferred = CompletableDeferred()

            CoroutineScope(Dispatchers.IO).launch {
                try {
                    sandbox =
                        JavaScriptSandbox.createConnectedInstanceAsync(context.applicationContext)
                            .get()
                    jsIsolate = sandbox?.createIsolate()

                    val jsCode =
                        context.assets.open("solana.js").bufferedReader().use { it.readText() }
                    jsIsolate?.evaluateJavaScriptAsync(jsCode)?.get()

                    isInitialized = true
                    initializationDeferred?.complete(true)
                } catch (e: Exception) {
                    isInitialized = false
                    initializationDeferred?.complete(false)
                }
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
        sandbox?.close()
        sandbox = null
        initializationDeferred = null
    }

    fun isInitialized(): Boolean {
        return isInitialized
    }
}