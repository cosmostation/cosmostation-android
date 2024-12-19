package wannabit.io.cosmostaion.chain.fetcher

import com.google.gson.JsonObject
import net.i2p.crypto.eddsa.EdDSAEngine
import net.i2p.crypto.eddsa.EdDSAPrivateKey
import net.i2p.crypto.eddsa.spec.EdDSANamedCurveTable
import net.i2p.crypto.eddsa.spec.EdDSAPrivateKeySpec
import org.bouncycastle.util.encoders.Hex
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.majorClass.ChainNamada
import wannabit.io.cosmostaion.chain.majorClass.NAMADA_MAIN_DENOM
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.toHex
import java.math.BigDecimal
import java.math.RoundingMode
import java.security.MessageDigest

class NamadaFetcher(private val chain: BaseChain) : CosmosFetcher(chain) {

    var namadaBalances: MutableList<JsonObject>? = mutableListOf()
    var namadaBond: JsonObject? = JsonObject()
    var namadaUnBond: MutableList<JsonObject> = mutableListOf()
    var namadaReward: MutableList<JsonObject>? = mutableListOf()
    var namadaWithdraw: MutableList<JsonObject> = mutableListOf()
    var namadaGas: MutableList<JsonObject>? = mutableListOf()
    var namadaValidators: MutableList<JsonObject>? = mutableListOf()

    override fun allAssetValue(isUsd: Boolean?): BigDecimal {
        return namadaBalanceValueSum(isUsd).add(namadaBondValueSum(isUsd))
            .add(namadaUnBondValueSum(isUsd)).add(namadaRewardValueSum(isUsd))
            .add(namadaWithdrawValueSum(isUsd))
    }

    override fun denomValue(denom: String, isUsd: Boolean?): BigDecimal {
        return if (denom == NAMADA_MAIN_DENOM) {
            namadaBalanceValue(denom, isUsd).add(namadaUnBondValueSum(isUsd))
                .add(namadaUnBondValueSum(isUsd)).add(namadaRewardValueSum(isUsd))
                .add(namadaWithdrawValueSum(isUsd))
        } else {
            namadaBalanceValue(denom, isUsd)
        }
    }

    fun namadaBalanceAmount(denom: String): BigDecimal {
        if (namadaBalances?.isNotEmpty() == true) {
            return namadaBalances?.firstOrNull { it["tokenAddress"].asString == denom }
                ?.get("minDenomAmount")?.asString?.toBigDecimal() ?: BigDecimal.ZERO
        }
        return BigDecimal.ZERO
    }

    fun namadaBalanceValue(denom: String, isUsd: Boolean? = false): BigDecimal {
        BaseData.getAsset(chain.apiName, denom)?.let { asset ->
            val price = BaseData.getPrice(asset.coinGeckoId, isUsd)
            val amount = namadaBalanceAmount(denom)
            asset.decimals?.let { decimal ->
                return price.multiply(amount).movePointLeft(decimal).setScale(6, RoundingMode.DOWN)
            } ?: run {
                return BigDecimal.ZERO
            }
        }
        return BigDecimal.ZERO
    }

    fun namadaBalanceValueSum(isUsd: Boolean? = false): BigDecimal {
        var sum = BigDecimal.ZERO
        if (namadaBalances?.isNotEmpty() == true) {
            namadaBalances?.forEach { balance ->
                sum = sum.add(namadaBalanceValue(balance["tokenAddress"].asString, isUsd))
            }
        }
        return sum
    }

    fun namadaBondAmountSum(): BigDecimal {
        var sum = BigDecimal.ZERO
        namadaBond?.get("results")?.asJsonArray?.let { bonds ->
            if (bonds.size() > 0) {
                bonds.forEach { bond ->
                    sum = sum.add(bond.asJsonObject["minDenomAmount"].asString.toBigDecimal())
                }
            } else {
                sum = BigDecimal.ZERO
            }

        } ?: run {
            sum = BigDecimal.ZERO
        }
        return sum
    }

    private fun namadaBondValueSum(isUsd: Boolean? = false): BigDecimal {
        BaseData.getAsset(chain.apiName, NAMADA_MAIN_DENOM)?.let { asset ->
            val price = BaseData.getPrice(asset.coinGeckoId, isUsd)
            val amount = namadaBondAmountSum()
            return price.multiply(amount).movePointLeft(asset.decimals ?: 6)
                .setScale(6, RoundingMode.DOWN)
        }
        return BigDecimal.ZERO
    }

    fun namadaUnBondAmountSum(): BigDecimal {
        var sum = BigDecimal.ZERO
        if (namadaUnBond.isNotEmpty()) {
            namadaUnBond.forEach { unBond ->
                sum = sum.add(unBond.asJsonObject["minDenomAmount"].asString.toBigDecimal())
            }
        } else {
            sum = BigDecimal.ZERO
        }
        return sum
    }

    private fun namadaUnBondValueSum(isUsd: Boolean? = false): BigDecimal {
        BaseData.getAsset(chain.apiName, NAMADA_MAIN_DENOM)?.let { asset ->
            val price = BaseData.getPrice(asset.coinGeckoId, isUsd)
            val amount = namadaUnBondAmountSum()
            return price.multiply(amount).movePointLeft(asset.decimals ?: 6)
                .setScale(6, RoundingMode.DOWN)
        }
        return BigDecimal.ZERO
    }

    fun namadaRewardAmountSum(): BigDecimal {
        var sum = BigDecimal.ZERO
        namadaReward?.forEach { reward ->
            sum = sum.add(reward["minDenomAmount"].asString.toBigDecimal())
        }
        return sum
    }

    private fun namadaRewardValueSum(isUsd: Boolean? = false): BigDecimal {
        BaseData.getAsset(chain.apiName, NAMADA_MAIN_DENOM)?.let { asset ->
            val price = BaseData.getPrice(asset.coinGeckoId, isUsd)
            val amount = namadaRewardAmountSum()
            return price.multiply(amount).movePointLeft(asset.decimals ?: 6)
                .setScale(6, RoundingMode.DOWN)
        }
        return BigDecimal.ZERO
    }

    fun namadaWithdrawAmountSum(): BigDecimal {
        var sum = BigDecimal.ZERO
        if (namadaWithdraw.isNotEmpty()) {
            namadaWithdraw.forEach { withdraw ->
                sum = sum.add(withdraw.asJsonObject["minDenomAmount"].asString.toBigDecimal())
            }
        } else {
            sum = BigDecimal.ZERO
        }
        return sum
    }

    private fun namadaWithdrawValueSum(isUsd: Boolean? = false): BigDecimal {
        BaseData.getAsset(chain.apiName, NAMADA_MAIN_DENOM)?.let { asset ->
            val price = BaseData.getPrice(asset.coinGeckoId, isUsd)
            val amount = namadaWithdrawAmountSum()
            return price.multiply(amount).movePointLeft(asset.decimals ?: 6)
                .setScale(6, RoundingMode.DOWN)
        }
        return BigDecimal.ZERO
    }
}

data class GlobalArgs(
    val expiration: String?, val code_hash: String, val chain_id: String
)

data class BondData(
    val validator: String, val amount: Long, val source: String, val args: GlobalArgs
)

data class Tx(
    val header: Header, val sections: Section
)

//object Transaction {
//    fun buildTx(args: GlobalArgs, data: BondData, wasm: String): Tx {
//        return Tx(data)
//    }
//
//    fun getSignBytes(tx: Tx): List<Hash> {
//        return listOf(Hash(tx.data.toString().hashCode().toString()))
//    }
//
//    fun attachRawSignatures(tx: Tx, signer: wannabit.io.cosmostaion.chain.fetcher.PublicKey, signature: Signature): Tx {
//        return tx.copy(metadata = tx.metadata + ("signatures" to listOf(signer.key to signature.signature)))
//    }
//}

data class Hash(val value: String)
data class PublicKey(val key: String)
data class Signature(val signature: String)
data class Authorization(val details: String)
data class TxError(val message: String)

//data class Tx(
//    val chainId: String,
//    var expiration: String?,
//    var header: Header = Header(),
//    var data: Map<String, Any?> = emptyMap(), // 트랜잭션 데이터
//    var sections: MutableList<Section> = mutableListOf(), // 서명 섹션
//    var wrappers: MutableList<Wrapper> = mutableListOf() // 추가 메타 데이터 (예: Fee)
//) {
//    fun getSignBytes(): List<String> {
//        return listOf(header.timestamp) // Example: Replace with actual hash logic
//    }
//
//    fun attachRawSignatures(signer: wannabit.io.cosmostaion.chain.fetcher.PublicKey, signature: Signature): Tx {
//        val updatedSections = sections.toMutableList()
//        updatedSections.add(
//            Section.Authorization(
//                Authorizations(
//                    targets = listOf(header.timestamp),
//                    signer = Signer.PubKeys(listOf(signer.key)),
//                    signatures = mapOf(0 to signature.signatureBytes)
//                )
//            )
//        )
//        return this.copy(sections = updatedSections)
//    }
//
//    fun attachFee(fee: Fee, feePayer: wannabit.io.cosmostaion.chain.fetcher.PublicKey, gasLimit: GasLimit): Tx {
//        val updatedWrappers = wrappers.toMutableList()
//        updatedWrappers.add(
//            Wrapper(fee = fee, feePayer = feePayer.key, gasLimit = gasLimit.value)
//        )
//        return this.copy(wrappers = updatedWrappers)
//    }
//
//    fun validateTx(): Boolean {
//        return true // Replace with actual validation logic
//    }
//}

data class Header(var timestamp: String = "2000-01-01T00:00:00Z")


fun generateSignature(tx: ByteArray, chain: ChainNamada): ByteArray {
    val hash = MessageDigest.getInstance("SHA-256").digest(tx)

    val spec: net.i2p.crypto.eddsa.spec.EdDSAParameterSpec = EdDSANamedCurveTable.getByName(
        EdDSANamedCurveTable.ED_25519
    )
    val privateKeySpec = EdDSAPrivateKeySpec(Hex.decode(chain.privateKey?.toHex()), spec)
    val privateKey = EdDSAPrivateKey(privateKeySpec)

    val signatureEngine = EdDSAEngine(MessageDigest.getInstance(spec.hashAlgorithm))
    signatureEngine.initSign(privateKey)
    signatureEngine.update(hash)
    return signatureEngine.sign()
//    return Base64.getEncoder().encodeToString(signatureEngine.sign())
}

data class Address(val value: String)

//data class PublicKey(val key: String)
//
//data class Signature(val signatureBytes: ByteArray)

data class Authorizations(
    val targets: List<String>, val signer: Signer, val signatures: Map<Int, ByteArray>
)

sealed class Section {
    data class Authorization(val authorization: Authorizations) : Section()
}

sealed class Signer {
    data class PubKeys(val keys: List<String>) : Signer()
}

data class Fee(val amountPerGasUnit: String, val token: String)

data class Wrapper(val fee: Fee, val feePayer: String, val gasLimit: Long)

data class GasLimit(val value: Long)

