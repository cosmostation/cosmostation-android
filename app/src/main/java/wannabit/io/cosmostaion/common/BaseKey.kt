package wannabit.io.cosmostaion.common

import android.content.Context
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import net.i2p.crypto.eddsa.EdDSAPrivateKey
import net.i2p.crypto.eddsa.EdDSAPublicKey
import net.i2p.crypto.eddsa.Utils
import net.i2p.crypto.eddsa.spec.EdDSANamedCurveTable
import net.i2p.crypto.eddsa.spec.EdDSAPrivateKeySpec
import net.i2p.crypto.eddsa.spec.EdDSAPublicKeySpec
import org.bitcoinj.core.Bech32
import org.bitcoinj.core.ECKey
import org.bitcoinj.core.Sha256Hash
import org.bitcoinj.crypto.ChildNumber
import org.bitcoinj.crypto.DeterministicHierarchy
import org.bitcoinj.crypto.HDKeyDerivation
import org.bitcoinj.crypto.MnemonicCode
import org.bitcoinj.crypto.MnemonicException.MnemonicLengthException
import org.bouncycastle.jcajce.provider.digest.Blake2b
import org.bouncycastle.util.encoders.Hex
import org.web3j.crypto.Keys
import org.web3j.crypto.WalletUtils
import wannabit.io.cosmostaion.BuildConfig
import wannabit.io.cosmostaion.chain.PubKeyType
import wannabit.io.cosmostaion.chain.majorClass.ChainSui
import wannabit.io.cosmostaion.sign.BitcoinJs
import java.security.SecureRandom

object BaseKey {

    fun getEntropy(size: Int): ByteArray {
        val seed = ByteArray(size)
        SecureRandom().nextBytes(seed)
        return seed
    }

    fun getRandomMnemonic(entropy: ByteArray): List<String>? {
        return MnemonicCode.INSTANCE.toMnemonic(entropy)
    }

    fun toEntropy(words: List<String>): ByteArray? {
        return MnemonicCode().toEntropy(words)
    }

    fun getMnemonicWords(hexEntropy: ByteArray?): List<String> {
        var words: List<String> = ArrayList()
        try {
            words = MnemonicCode.INSTANCE.toMnemonic(hexEntropy)
        } catch (e: MnemonicLengthException) {
            if (BuildConfig.DEBUG) e.printStackTrace()
        }
        return words
    }

    fun getHDSeed(entropy: ByteArray?): ByteArray? {
        return MnemonicCode.toSeed(MnemonicCode.INSTANCE.toMnemonic(entropy), "")
    }

    private fun getByteSeedFromWords(words: List<String>): ByteArray? {
        return getHDSeed(MnemonicCode().toEntropy(words))
    }

    private fun getEd25519PrivateKey(seed: ByteArray?, lastPath: String): ByteArray {
        var pair = ByteUtils.shaking(seed, "ed25519 seed".toByteArray())
        val components = ChainSui().getHDPath(lastPath).split("/")
        val nodes = mutableListOf<Int>()
        for (component in components.subList(1, components.size)) {
            val index = component.trim('\'').toInt()
            nodes.add(index)
        }
        nodes.forEach {
            val buffer = ByteArray(size = 4)
            for (i in 0..3) buffer[i] = ((0x80000000 + it) shr ((3 - i) * 8) and 0xff).toByte()
            val pathBuffer = ByteArray(size = 1) + pair.first + buffer
            pair = ByteUtils.shaking(pathBuffer, pair.second)
        }

        val keySpecs = EdDSANamedCurveTable.getByName(EdDSANamedCurveTable.ED_25519)
        val privateKeySpec = EdDSAPrivateKeySpec(pair.first, keySpecs)
        val privateKey = EdDSAPrivateKey(privateKeySpec)
        return privateKey.seed
    }

    fun getPrivateKey(
        pubKeyType: PubKeyType, seed: ByteArray?, parentPaths: List<ChildNumber>, lastPath: String
    ): ByteArray? {
        return if (pubKeyType == PubKeyType.SUI_ED25519) {
            getEd25519PrivateKey(seed, lastPath)
        } else {
            val masterKey = HDKeyDerivation.createMasterPrivateKey(seed)
            val deterministicKey = DeterministicHierarchy(masterKey).deriveChild(
                parentPaths, true, true, ChildNumber(Integer.parseInt(lastPath))
            )
            deterministicKey.privKeyBytes
        }
    }

    fun getPubKeyFromPKey(privateKey: ByteArray?, pubKeyType: PubKeyType): ByteArray? {
        return when (pubKeyType) {
            PubKeyType.SUI_ED25519 -> {
                val keySpec = EdDSANamedCurveTable.getByName(EdDSANamedCurveTable.ED_25519)
                val privateKeySpec = EdDSAPrivateKeySpec(Hex.decode(privateKey?.toHex()), keySpec)
                val pubKeySpec = EdDSAPublicKeySpec(privateKeySpec.a, keySpec)
                val publicKey = EdDSAPublicKey(pubKeySpec)
                publicKey.abyte

            }

            else -> {
                val ecKey = ECKey.fromPrivate(privateKey)
                ecKey.pubKey
            }
        }
    }

    suspend fun getAddressFromPubKey(
        context: Context,
        pubKey: ByteArray?,
        pubKeyType: PubKeyType,
        prefix: String? = null,
        network: String? = null
    ): String {
        var result = ""
        when (pubKeyType) {
            PubKeyType.COSMOS_SECP256K1 -> {
                val sha256Hash = Sha256Hash.hash(pubKey)
                val ripemd160 = ByteUtils.hashToRIPMD160(sha256Hash)
                val converted = ByteUtils.convertBits(ripemd160, 8, 5, true)
                result = Bech32.encode(Bech32.Encoding.BECH32, prefix, converted)

            }

            PubKeyType.ETH_KECCAK256, PubKeyType.BERA_SECP256K1 -> {
                val uncompressedPubKey = ECKey.CURVE.curve.decodePoint(pubKey).getEncoded(false)
                val pub = ByteArray(64)
                System.arraycopy(uncompressedPubKey, 1, pub, 0, 64)
                result = "0x" + Keys.getAddress(pub).toHex()

            }

            PubKeyType.SUI_ED25519 -> {
                pubKey?.let { pub ->
                    val data = ByteArray(1) + pub
                    val blake2b = Blake2b.Blake2b256()
                    blake2b.update(data)
                    val hex = Utils.bytesToHex(blake2b.digest())
                    result = "0x${hex.substring(0, 64)}"
                }

            }

            else -> {
                val deferredResult = CompletableDeferred<String>()
                withContext(Dispatchers.IO) {
                    if (!BitcoinJs.isInitialized()) {
                        BitcoinJs.initialize(context).await()
                    }

                    try {
                        val publicKey = pubKey?.toHex()
                        val unique = System.nanoTime()
                        val uniqueFunctionName = "addressFunction_${unique}"

                        val addressFunction = """function $uniqueFunctionName() {
                                    const address = getAddress('${publicKey}', '${bitType(pubKeyType)}', '${network}');
                                    return address;
                                }""".trimMargin()
                        BitcoinJs.mergeFunction(addressFunction)
                        val address = BitcoinJs.executeFunction("$uniqueFunctionName()").toString()
                        deferredResult.complete(address)

                    } catch (e: Exception) {
                        deferredResult.completeExceptionally(e)
                    }
                    result = deferredResult.await()
                }
            }
        }
        return result
    }

    fun isMnemonicWords(words: List<String>): Boolean {
        var result = true
        val mnemonics = MnemonicCode.INSTANCE.wordList
        for (insert in words) {
            if (!mnemonics.contains(insert)) {
                result = false
                break
            }
        }
        return result
    }

    fun isValidStringHdSeedFromWords(words: List<String>): Boolean {
        return getByteSeedFromWords(words) != null
    }

    fun isValidBech32(address: String?): Boolean {
        var result = false
        try {
            Bech32.decode(address)
            result = true
        } catch (_: Exception) {
        }
        return result
    }

    fun isValidEthAddress(address: String?): Boolean {
        return WalletUtils.isValidAddress(address)
    }
}