package wannabit.io.cosmostaion.cosmos

import android.content.Context
import androidx.javascriptengine.JavaScriptIsolate
import androidx.javascriptengine.JavaScriptSandbox


class BitCoinJS(private val context: Context) {

    private var isServiceBind = false
    private lateinit var sandbox: JavaScriptSandbox
    private lateinit var jsIsolate: JavaScriptIsolate

    init {
        if (!isServiceBind) {
            sandbox = JavaScriptSandbox.createConnectedInstanceAsync(context).get()
            jsIsolate = sandbox.createIsolate()
            isServiceBind = true
        }
        val jsCode = readJavaScriptFile()
        jsIsolate.evaluateJavaScriptAsync(jsCode).get()
    }

    private fun readJavaScriptFile(): String {
        return context.assets.open("bitcoin.js").bufferedReader().use { it.readText() }
    }

    fun executeFunction(functionCall: String): String {
        return jsIsolate.evaluateJavaScriptAsync(functionCall).get()
    }

    fun mergeFunction(createTx: String) {
        jsIsolate.evaluateJavaScriptAsync(createTx).get()
    }

    fun unbindService() {
        if (isServiceBind) {
            sandbox.close()
            isServiceBind = false
        }
    }
}