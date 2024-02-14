package wannabit.io.cosmostaion.ui.main.setting.wallet.importQR

import org.mozilla.javascript.Context
import org.mozilla.javascript.Scriptable

class CryptoJS(val context: android.content.Context) {

    private fun readJavaScriptFile(): String {
        return context.assets.open("aes.js").bufferedReader().use { it.readText() }
    }

    fun decryptWithJS(encryptedString: String, masterKey: String): String {
        val rhino = Context.enter()

        try {
            rhino.optimizationLevel = -1
            val scope: Scriptable = rhino.initStandardObjects()
            val jsSource = readJavaScriptFile()
            rhino.evaluateString(scope, jsSource, "JavaScript", 1, null)

            val decryptFunction = scope.get("decrypt", scope) as org.mozilla.javascript.Function
            val result =
                decryptFunction.call(rhino, scope, scope, arrayOf(encryptedString, masterKey, null))

            return Context.toString(result)

        } finally {
            Context.exit()
        }
    }
}