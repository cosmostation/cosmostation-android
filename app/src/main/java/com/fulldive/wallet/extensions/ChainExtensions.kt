package com.fulldive.wallet.extensions

import com.google.gson.JsonParser
import org.bitcoinj.crypto.ChildNumber
import wannabit.io.cosmostaion.base.BaseChain

private fun fetchPath(json: String, customPath: Int): Triple<Int, Boolean, Boolean> {
    var lastZero = true
    var lastHardenedZero = true
    var number = 118
    safe {
        val jsonObject = JsonParser.parseString(json).asJsonObject
        val pathParam = if (jsonObject.has("$customPath")) {
            jsonObject.getAsJsonPrimitive("$customPath").asString
        } else {
            jsonObject.getAsJsonPrimitive("default").asString
        }
        val chunks = pathParam.split(":")
        number = chunks[0].toInt()
        if (chunks.size > 1) {
            lastHardenedZero = !chunks[1].contains('h')
            lastZero = !chunks[1].contains('0')
        }
    }

    return Triple(number, lastHardenedZero, lastZero)
}

fun BaseChain.getPath(customPath: Int): List<ChildNumber> {
    val (childNumber, lastHardenedZero, lastZero) = fetchPath(
        pathConfig,
        customPath
    )

    return mutableListOf(
        ChildNumber(44, true),
        ChildNumber(childNumber, true)
    )
        .apply {
            if (lastHardenedZero) {
                add(ChildNumber.ZERO_HARDENED)
            }
            if (lastZero) {
                add(ChildNumber.ZERO)
            }
        }
}


fun BaseChain.getPathString(path: Int, customPath: Int): String {
    val items = mutableListOf("m", "44'")
    var hint = ""

    val (childNumber, lastHardenedZero, lastZero) = fetchPath(
        pathConfig,
        customPath
    )

    items.add("$childNumber'")
    var hardPath = true
    if (lastHardenedZero) {
        items.add("0'")
        hardPath = false
    }
    if (lastZero) {
        items.add("0")
        hardPath = false
    }
    if (hardPath) {
        items.add("$path'")
    } else {
        items.add("$path")
    }
    if (!lastHardenedZero) {
        items.add("0")
    }
    if ((this != BaseChain.FETCHAI_MAIN || customPath == 2) && !lastZero) {
        items.add("0")
    }

    if (this == BaseChain.OKEX_MAIN) {
        hint = when (customPath) {
            0 -> " (Tendermint Type)"
            1 -> " (Ethermint Type)"
            else -> " (Ethereum Type)"
        }
    }
    return items.joinToString("/") + hint
}
