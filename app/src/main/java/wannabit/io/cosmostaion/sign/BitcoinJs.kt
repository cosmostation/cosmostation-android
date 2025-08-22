package wannabit.io.cosmostaion.sign

import android.content.Context
import androidx.javascriptengine.JavaScriptIsolate
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object BitcoinJs {
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

                val jsCode = context.assets.open("bitcoin.js")
                    .bufferedReader().use { it.readText() }

                val polyfillCode = """
                        class TextEncoder {
                            encode(input) {
                                const encoder = new Uint8Array(
                                    [...unescape(encodeURIComponent(input))].map(c => c.charCodeAt(0))
                                );
                                return encoder;
                            }
                        }

                        class TextDecoder {
                            decode(input) {
                                return decodeURIComponent(escape(String.fromCharCode(...input)));
                            }
                        }

                        globalThis.TextEncoder = TextEncoder;
                        globalThis.btoa = TextEncoder;
                        globalThis.TextDecoder = TextDecoder;
                    """.trimIndent()
                jsIsolate?.evaluateJavaScriptAsync(polyfillCode)?.get()

                val cryptoPolyfill = """
                        var globalScope = typeof globalThis !== "undefined" ? globalThis : this;

                        if (!globalScope.crypto) {
                            Object.defineProperty(globalScope, "crypto", {
                                value: {
                                    getRandomValues: function(buffer) {
                                        if (!(buffer instanceof Uint8Array)) {
                                            throw new TypeError("Expected Uint8Array");
                                        }
                                        for (let i = 0; i < buffer.length; i++) {
                                            buffer[i] = Math.floor(Math.random() * 256);
                                        }
                                        return buffer;
                                    },
                                    subtle: {
                                        digest: async function(algorithm, data) {
                                            if (algorithm.name !== "SHA-256") {
                                                throw new Error("Only SHA-256 is supported in this polyfill.");
                                            }
                                            let buffer = new Uint8Array(data);
                                            let hash = 0;
                                            for (let i = 0; i < buffer.length; i++) {
                                                hash = (hash * 31 + buffer[i]) % 4294967296;
                                            }
                                            return new Uint8Array([hash & 0xFF, (hash >> 8) & 0xFF, (hash >> 16) & 0xFF, (hash >> 24) & 0xFF]).buffer;
                                        },

                                        importKey: async function(format, keyData, algorithm, extractable, keyUsages) {
                                            if (format !== "raw") {
                                                throw new Error("Only raw format is supported in this polyfill.");
                                            }
                                            if (algorithm.name !== "HMAC" || algorithm.hash.name !== "SHA-256") {
                                                throw new Error("Only HMAC-SHA256 is supported in this polyfill.");
                                            }
                                            return { key: keyData, algorithm: algorithm };
                                        },

                                        sign: async function(algorithm, key, data) {
                                            if (algorithm.name !== "HMAC" || algorithm.hash.name !== "SHA-256") {
                                                throw new Error("Only HMAC-SHA256 is supported in this polyfill.");
                                            }
                                            let buffer = new Uint8Array(data);
                                            let keyBuffer = new Uint8Array(key.key);
                                            let hash = 0;
                                            for (let i = 0; i < buffer.length; i++) {
                                                hash = (hash * 37 + buffer[i] * keyBuffer[i % keyBuffer.length]) % 4294967296;
                                            }
                                            return new Uint8Array([hash & 0xFF, (hash >> 8) & 0xFF, (hash >> 16) & 0xFF, (hash >> 24) & 0xFF]).buffer;
                                        }
                                    }
                                },
                                writable: false,
                                enumerable: false,
                                configurable: false
                            });
                        }
                    """.trimIndent()
                jsIsolate?.evaluateJavaScriptAsync(cryptoPolyfill)?.get()

                jsIsolate?.evaluateJavaScriptAsync(jsCode)?.get()

                initializationDeferred?.complete(true)
            } catch (e: Exception) {
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