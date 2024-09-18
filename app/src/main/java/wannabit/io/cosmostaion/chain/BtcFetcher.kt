package wannabit.io.cosmostaion.chain

import com.google.gson.Gson
import com.google.gson.JsonObject
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.jsonRpcResponse
import wannabit.io.cosmostaion.data.model.req.JsonRpcRequest
import java.math.BigDecimal
import java.math.RoundingMode

class BtcFetcher(private val chain: BaseChain) : CosmosFetcher(chain) {

    var btcBalances = BigDecimal.ZERO
    var btcPendingInput = BigDecimal.ZERO
    var btcPendingOutput = BigDecimal.ZERO
    var btcBlockHeight: Long = 0
    var btcHistory: MutableList<JsonObject> = mutableListOf()

    var bitState = true

    override fun allAssetValue(isUsd: Boolean?): BigDecimal {
        val price = BaseData.getPrice(chain.coinGeckoId, isUsd)
        return (btcBalances.add(btcPendingInput)).multiply(price).movePointLeft(8).setScale(8, RoundingMode.DOWN)
    }

    fun mempoolUrl(): String {
        if (chain.isTestnet) {
            return "https://mempool.space/testnet4/"
        }
        return "https://mempool.space/"
    }

    fun bitType(): String {
        return when (chain.accountKeyType.pubkeyType) {
            PubKeyType.BTC_NATIVE_SEGWIT -> {
                "p2wpkh"
            }

            PubKeyType.BTC_NESTED_SEGWIT -> {
                "p2sh"
            }

            PubKeyType.BTC_LEGACY -> {
                "p2pkh"
            }

            else -> {
                ""
            }
        }
    }

    fun network(): String {
        return if (!chain.isTestnet) "bitcoin" else "testnet"
    }

    fun bitVBytesFee(utxo: MutableList<JsonObject>?): BigDecimal {
        return when (chain.accountKeyType.pubkeyType) {
            PubKeyType.BTC_NATIVE_SEGWIT -> {
                P2WPKH_VBYTE.OVERHEAD.toBigDecimal().add(
                    P2WPKH_VBYTE.INPUTS.toBigDecimal().multiply(
                        BigDecimal(utxo!!.count())
                    )
                ).add(P2WPKH_VBYTE.OUTPUTS.toBigDecimal().multiply(BigDecimal(2)))
            }

            PubKeyType.BTC_NESTED_SEGWIT -> {
                P2SH_VBYTE.OVERHEAD.toBigDecimal().add(
                    P2SH_VBYTE.INPUTS.toBigDecimal().multiply(
                        BigDecimal(utxo!!.count())
                    )
                ).add(P2SH_VBYTE.OUTPUTS.toBigDecimal().multiply(BigDecimal(2)))
            }

            else -> {
                P2PKH_VBYTE.OVERHEAD.toBigDecimal().add(
                    P2PKH_VBYTE.INPUTS.toBigDecimal().multiply(
                        BigDecimal(utxo!!.count())
                    )
                ).add(P2PKH_VBYTE.OUTPUTS.toBigDecimal().multiply(BigDecimal(2)))
            }
        }
    }

    fun txInputString(utxo: MutableList<JsonObject>?): String {
        var inputString = ""
        when (chain.accountKeyType.pubkeyType) {
            PubKeyType.BTC_NATIVE_SEGWIT -> {
                utxo?.forEach { tx ->
                    if (tx["status"].asJsonObject["confirmed"].asBoolean) {
                        val input = """
                            {
                                hash: '${tx["txid"].asString}',
                                index: ${tx["vout"].asInt},
                                witnessUtxo: {
                                    script: senderPayment.output,
                                    value: ${tx["value"].asLong}
                                }
                            },
                            """
                        inputString += input
                    }
                }
            }

            PubKeyType.BTC_NESTED_SEGWIT -> {
                utxo?.forEach { tx ->
                    if (tx["status"].asJsonObject["confirmed"].asBoolean) {
                        val input = """
                            {
                                hash: '${tx["txid"].asString}',
                                index: ${tx["vout"].asInt},
                                redeemScript: senderPayment.redeem.output,
                                witnessUtxo: {
                                    script: senderPayment.output,
                                    value: ${tx["value"].asLong}
                                }
                            },
                            """
                        inputString += input
                    }
                }
            }

            PubKeyType.BTC_LEGACY -> {
                utxo?.forEach { tx ->
                    if (tx["status"].asJsonObject["block_hash"] != null) {
                        val rawTransactionRequest = JsonRpcRequest(
                            method = "getrawtransaction", params = listOf(
                                tx["txid"].asString, false, tx["status"].asJsonObject["block_hash"].asString
                            )
                        )
                        val rawTransactionResponse =
                            jsonRpcResponse(chain.rpcUrl, rawTransactionRequest)
                        val rawTransactionJsonObject = Gson().fromJson(
                            rawTransactionResponse.body?.string(), JsonObject::class.java
                        )
                        val nonWitnessUtxoHex = rawTransactionJsonObject["result"].asString

                        if (tx["status"].asJsonObject["confirmed"].asBoolean) {
                            val input = """
                                {
                                    hash: '${tx["txid"].asString}',
                                    index: ${tx["vout"].asInt},
                                    nonWitnessUtxo: aTb('${nonWitnessUtxoHex}'),
                                },
                                """
                            inputString += input
                        }
                    }
                }
            }

            else -> {}
        }
        return inputString
    }

    fun txOutputString(
        receiver: String, toAmount: String, changedValue: String, opReturn: String?
    ): String {
        return if (opReturn?.isNotEmpty() == true) {
            """
                {
                    address: '${receiver}',
                    value: ${toAmount.toLong()}
                },
                {
                    address: '${chain.mainAddress}',
                    value: ${changedValue.toLong()}
                },
                m('${opReturn}')
                """

        } else {
            """
                {
                    address: '${receiver}',
                    value: ${toAmount.toLong()}
                },
                {
                    address: '${chain.mainAddress}',
                    value: ${changedValue.toLong()}
                },
            """
        }
    }
}

const val OP_RETURN = 83

object P2WPKH_VBYTE {
    const val OVERHEAD = 11
    const val INPUTS = 68
    const val OUTPUTS = 31
}

object P2SH_VBYTE {
    const val OVERHEAD = 10
    const val INPUTS = 297
    const val OUTPUTS = 32
}

object P2PKH_VBYTE {
    const val OVERHEAD = 10
    const val INPUTS = 148
    const val OUTPUTS = 34
}