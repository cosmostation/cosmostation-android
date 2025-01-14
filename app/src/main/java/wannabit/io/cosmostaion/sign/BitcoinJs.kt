package wannabit.io.cosmostaion.sign

import android.content.Context
import androidx.javascriptengine.JavaScriptIsolate
import androidx.javascriptengine.JavaScriptSandbox
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class BitCoinJS(private val context: Context) {

    private var isServiceBind = false
    private lateinit var sandbox: JavaScriptSandbox
    private var jsIsolate: JavaScriptIsolate? = null

    init {
        if (!isServiceBind) {
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    if (!isServiceBind) {
                        sandbox = JavaScriptSandbox.createConnectedInstanceAsync(context).get()
                        jsIsolate = sandbox.createIsolate()
                        isServiceBind = true
                    }
                    val jsCode = readJavaScriptFile()
                    jsIsolate?.evaluateJavaScriptAsync(jsCode)?.get()

                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    private fun readJavaScriptFile(): String {
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
            isServiceBind = false
        }
    }
}