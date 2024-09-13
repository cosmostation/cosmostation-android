package wannabit.io.cosmostaion.chain

import com.google.gson.JsonObject
import wannabit.io.cosmostaion.common.BaseData
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
        return (btcBalances.add(btcPendingInput)).multiply(price).setScale(8, RoundingMode.DOWN)
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
                P2PKH_VBYTE.OVERHEAD.toBigDecimal().add(
                    P2PKH_VBYTE.INPUTS.toBigDecimal().multiply(
                        BigDecimal(utxo!!.count())
                    )
                ).add(P2PKH_VBYTE.OUTPUTS.toBigDecimal().multiply(BigDecimal(2)))
            }

            else -> {
                BigDecimal.ZERO
            }
        }
    }

    fun txInputString(utxo: MutableList<JsonObject>?): String {
        var inputString = ""
        when (chain.accountKeyType.pubkeyType) {
            PubKeyType.BTC_NATIVE_SEGWIT -> {
                utxo?.forEach { tx ->
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

            PubKeyType.BTC_NESTED_SEGWIT -> {
                utxo?.forEach { tx ->
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

            PubKeyType.BTC_LEGACY -> {
                utxo?.forEach { tx ->

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

object P2WPKH_VBYTE {
    const val OVERHEAD = 11
    const val INPUTS = 68
    const val OUTPUTS = 31
    const val OP_RETURN = 83
}

object P2PKH_VBYTE {
    const val OVERHEAD = 10
    const val INPUTS = 148
    const val OUTPUTS = 34
    const val OP_RETURN = 83
}