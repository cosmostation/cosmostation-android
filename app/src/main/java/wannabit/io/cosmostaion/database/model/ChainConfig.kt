package wannabit.io.cosmostaion.database.model

import org.bitcoinj.core.Bech32
import org.bitcoinj.core.ECKey
import org.bitcoinj.crypto.ChildNumber
import org.bitcoinj.crypto.DeterministicHierarchy
import org.bitcoinj.crypto.HDKeyDerivation
import org.bouncycastle.crypto.digests.RIPEMD160Digest
import org.web3j.crypto.Credentials
import wannabit.io.cosmostaion.common.ByteUtils
import java.math.BigInteger
import java.security.MessageDigest

sealed class ChainConfig {
    data class Cosmos(
        val chainId: String,
        val bech32Prefix: String,
        val baseDenom: String,
        val chainName: String,
        val decimal: Int,
        val displayDenom: String,
        val gasInfo: String,
        val gasRate: String,
        val restUrl: String,
        val grpcUrl: String
    ) : ChainConfig() {
        fun getAddress(seed: ByteArray): String {
            val masterKey = HDKeyDerivation.createMasterPrivateKey(seed)
            val targetKey =
                DeterministicHierarchy(masterKey).deriveChild(listOf(ChildNumber(44, true), ChildNumber(118, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO), true, true, ChildNumber(0))
            val key = ECKey.fromPrivate(BigInteger(targetKey.privateKeyAsHex, 16))
            val digest = MessageDigest.getInstance("SHA-256")
            val hash = digest.digest(key.pubKey)
            val rDigest = RIPEMD160Digest()
            rDigest.update(hash, 0, hash.size)
            val digestResult = ByteArray(rDigest.digestSize)
            rDigest.doFinal(digestResult, 0)
            val bitConvertedHash = ByteUtils.convertBits(digestResult, 8, 5, true)
            return Bech32.encode(Bech32.Encoding.BECH32, "cosmos", bitConvertedHash)
        }
    }

    data class Ethereum(
        val chainId: String,
        val networkName: String,
        val rpcUrl: String,
        val chainName: String,
        val decimal: Int,
        val displayDenom: String,
    ) : ChainConfig() {
        fun getAddress(seed: ByteArray): String {
            val masterKey = HDKeyDerivation.createMasterPrivateKey(seed)
            val targetKey =
                DeterministicHierarchy(masterKey).deriveChild(listOf(ChildNumber(44, true), ChildNumber(60, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO), true, true, ChildNumber(0))
            val key = ECKey.fromPrivate(BigInteger(targetKey.privateKeyAsHex, 16))
            val credentials = Credentials.create(key.privateKeyAsHex)
            return credentials.address
        }
    }

    data class Sui(
        val chainId: String,
        val networkName: String,
        val rpcUrl: String,
        val chainName: String,
        val decimal: Int,
        val displayDenom: String,
    ) : ChainConfig()
}