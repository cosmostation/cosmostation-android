package wannabit.io.cosmostaion.cosmos

import org.mozilla.javascript.Context
import org.mozilla.javascript.Scriptable


class BitCoinJS(val context: android.content.Context) {

    private fun readJavaScriptFile(): String {
        return context.assets.open("bitcoin.js").bufferedReader().use { it.readText() }
    }

    fun test1(): String {
//        org.graalvm.polyglot.Context.create().use { context ->
//            val result: Value = context.eval("js", readJavaScriptFile())
//            Log.e("JSResult", "JavaScript result: " + result.asInt())
//        }
//        val r untime: V8 = V8.createV8Runtime()
//        return try {
//            val result: Int = runtime.executeIntegerScript(readJavaScriptFile())
//            Log.e("JSResult", "JavaScript result: $result")
//            result.toString()
//        } catch (e: Exception) {
//            e.printStackTrace()
//            "JavaScript execution error: ${e.message}"
//        } finally {
//            // Duktape 리소스 해제
//            duktape.close()
//        }
        return ""
    }

    fun test(): String {
        val rhino = Context.enter()
        return try {
            rhino.optimizationLevel = -1
            val scope: Scriptable = rhino.initStandardObjects()
            val jsSource = readJavaScriptFile()
            rhino.evaluateString(scope, jsSource, "JavaScript", 1, null)

            val testFunction = scope.get("test", scope) as org.mozilla.javascript.Function
            val testFunction1 = scope.get("isUint8ArraySupported", scope) as org.mozilla.javascript.Function
            val result = testFunction.call(rhino, scope, scope, arrayOf(null))
            val result1 = testFunction1.call(rhino, scope, scope, arrayOf(null))
            Context.toString(result)
//            Context.toString(result1)

        } catch (e: Exception) {
            e.printStackTrace()
            "JavaScript execution error: ${e.message}"
        } finally {
            Context.exit()
        }
    }
}