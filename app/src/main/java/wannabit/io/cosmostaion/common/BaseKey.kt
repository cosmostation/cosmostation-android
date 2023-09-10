package wannabit.io.cosmostaion.common

import org.bitcoinj.core.Bech32
import org.bitcoinj.core.ECKey
import org.bitcoinj.core.Sha256Hash
import org.bitcoinj.crypto.ChildNumber
import org.bitcoinj.crypto.DeterministicHierarchy
import org.bitcoinj.crypto.HDKeyDerivation
import org.bitcoinj.crypto.MnemonicCode
import org.bitcoinj.crypto.MnemonicException.MnemonicLengthException
import org.bouncycastle.util.encoders.Hex
import org.web3j.crypto.Keys
import wannabit.io.cosmostaion.BuildConfig
import wannabit.io.cosmostaion.chain.PubKeyType

object BaseKey {

//    fun getMnemonicWords() : List<String> {
//        BaseData.baseAccount?.let { baseAccount ->
//            val hexEntropy = CryptoHelper.doDecryptData(
//                CosmostationConstants.ENCRYPT_MNEMONIC_KEY + baseAccount.uuid,
//                baseAccount.resource,
//                baseAccount.spec
//            )
//            hexEntropy?.let {
//                return mutableListOf()
//            }
//        }
//    }

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

    fun getByteSeedFromWords(words: List<String>): ByteArray? {
        return getHDSeed(MnemonicCode().toEntropy(words))
    }

    fun getPrivateKey(seed: ByteArray?, parentPaths: List<ChildNumber>, lastPath: String): ByteArray? {
        val masterKey = HDKeyDerivation.createMasterPrivateKey(seed)
        val deterministicKey = DeterministicHierarchy(masterKey).deriveChild(parentPaths, true, true, ChildNumber(Integer.parseInt(lastPath)))
        return deterministicKey.privKeyBytes
    }

    fun getPubKeyFromPKey(privateKey: ByteArray?): ByteArray? {
        val ecKey = ECKey.fromPrivate(privateKey)
        return ecKey.pubKey
    }

    fun getAddressFromPubKey(pubKey: ByteArray?, pubKeyType: PubKeyType, prefix: String? = null): String {
        var result = ""
        when (pubKeyType) {
            PubKeyType.COSMOS_SECP256K1 -> {
                val sha256Hash = Sha256Hash.hash(pubKey)
                val ripemd160 = ByteUtils.hashToRIPMD160(sha256Hash)
                val converted = ByteUtils.convertBits(ripemd160, 8, 5, true)
                result = Bech32.encode(Bech32.Encoding.BECH32, prefix, converted)
            }

            PubKeyType.ETH_KECCAK256 -> {
                val uncompressedPubKey = ECKey.CURVE.curve.decodePoint(pubKey).getEncoded(false)
                val pub = ByteArray(64)
                System.arraycopy(uncompressedPubKey, 1, pub, 0, 64)

                val address = Keys.getAddress(pub)
                val bytes = ByteUtils.convertBits(address, 8, 5, true)
                result = Bech32.encode(Bech32.Encoding.BECH32, prefix, bytes)
            }

            else -> return result
        }
        return result
    }
}