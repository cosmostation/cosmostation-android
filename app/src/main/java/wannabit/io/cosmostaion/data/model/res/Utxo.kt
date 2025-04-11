package wannabit.io.cosmostaion.data.model.res

import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import com.google.gson.JsonParser

const val LOW_VALUE_UTXO_THRESHOLD = 10_000

data class FinalUtxo(
    val txid: String,
    val vout: Int,
    val value: Long,
    val scriptPubKey: String,
    val status: JsonObject
)

fun getAvailableUtxosFromRaw(
    rawUtxoJson: String,
    validateAddressInfoJson: String,
    hasInscription: (String, Int) -> Boolean
): List<FinalUtxo> {
    val rawUtxos = JsonParser.parseString(rawUtxoJson).asJsonArray
    val validateAddressInfo = JsonParser.parseString(validateAddressInfoJson).asJsonObject

    val isValid = validateAddressInfo["isvalid"]?.asBoolean ?: false
    val scriptPubKey = validateAddressInfo["scriptPubKey"]?.asString ?: ""

    if (!isValid) {
        throw IllegalArgumentException("Invalid address")
    }

    val sortedUtxos = rawUtxos.mapNotNull { el ->
        try {
            el.asJsonObject
        } catch (e: Exception) {
            null
        }
    }.sortedByDescending { it["value"].asLong }

    val flattened = sortedUtxos.mapNotNull { utxo ->
        val status = utxo["status"]?.asJsonObject ?: return@mapNotNull null
        val confirmed = status["confirmed"]?.asBoolean ?: false
        if (!confirmed) return@mapNotNull null

        val txid = utxo["txid"]?.asString ?: return@mapNotNull null
        val vout = utxo["vout"]?.asInt ?: return@mapNotNull null
        val value = utxo["value"]?.asLong ?: return@mapNotNull null

        FinalUtxo(txid, vout, value, scriptPubKey, status)
    }

    val nonDust = flattened.filter { it.value > LOW_VALUE_UTXO_THRESHOLD }

    val filtered = nonDust.filter { !hasInscription(it.txid, it.vout) }

    return filtered
}

fun printAvailableUtxosJson(utxos: List<FinalUtxo>): String {
    val gson = GsonBuilder().setPrettyPrinting().create()
    val jsonArray = utxos.map { utxo ->
        val obj = JsonObject().apply {
            addProperty("txid", utxo.txid)
            addProperty("vout", utxo.vout)
            addProperty("value", utxo.value)
            addProperty("scriptPubKey", utxo.scriptPubKey)
            add("status", utxo.status)
        }
        obj
    }

    return gson.toJson(jsonArray)
}