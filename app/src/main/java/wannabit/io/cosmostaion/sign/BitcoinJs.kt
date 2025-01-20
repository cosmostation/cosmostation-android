package wannabit.io.cosmostaion.sign

import android.content.Context
import androidx.javascriptengine.JavaScriptIsolate
import androidx.javascriptengine.JavaScriptSandbox
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

object BitcoinJs {
    private var isServiceBind = false
    private lateinit var sandbox: JavaScriptSandbox
    private var jsIsolate: JavaScriptIsolate? = null

    fun initialize(context: Context, scope: CoroutineScope, onInitialized: () -> Unit) {
        if (!isServiceBind) {
            scope.launch(Dispatchers.IO) {
                try {
                    sandbox =
                        JavaScriptSandbox.createConnectedInstanceAsync(context.applicationContext)
                            .get()
                    jsIsolate = sandbox.createIsolate()
                    isServiceBind = true
                    val jsCode = readJavaScriptFile(context)
                    jsIsolate?.evaluateJavaScriptAsync(jsCode)?.get()

                    withContext(Dispatchers.Main) {
                        onInitialized()
                    }

                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        } else {
            onInitialized()
        }
    }

    private fun readJavaScriptFile(context: Context): String {
        return context.assets.open("bitcoin.js").bufferedReader().use { it.readText() }
    }

    fun executeFunction(functionCall: String): String? {
        return jsIsolate?.evaluateJavaScriptAsync(functionCall)?.get()
    }

    fun mergeFunction(createTx: String) {
        jsIsolate?.evaluateJavaScriptAsync(createTx)?.get()
    }

    fun unbindService() {
        if (isServiceBind) {
            sandbox.close()
            jsIsolate = null
            isServiceBind = false
        }
    }

    fun isInitialized(): Boolean {
        return isServiceBind
    }
}