package com.fulldive.wallet.interactors.secret

import org.bitcoinj.core.Bech32
import org.bitcoinj.core.ECKey
import org.bitcoinj.crypto.*
import org.bouncycastle.crypto.digests.RIPEMD160Digest
import org.bouncycastle.util.encoders.Hex
import org.web3j.crypto.Keys
import wannabit.io.cosmostaion.base.BaseChain
import wannabit.io.cosmostaion.crypto.Sha256
import java.io.ByteArrayOutputStream
import java.math.BigInteger

object MnemonicUtils {
    val MNEMONIC_WORDS_COUNT = 24
    private val GENERATORS = intArrayOf(0x3b6a57b2, 0x26508e6d, 0x1ea119fa, 0x3d4233dd, 0x2a1462b3)
    private val CHARSET = "qpzry9x8gf2tvdw0s3jn54khce6mua7l".toByteArray()

    @Throws(Exception::class)
    fun createAddress(
        chain: BaseChain,
        entropy: String,
        path: Int,
        customPath: Int
    ): String {
        val childKey = getCreateKeyWithPathFromEntropy(chain, entropy, path, customPath)
        return when (chain) {
            BaseChain.OKEX_MAIN -> {
                when (customPath) {
                    0 -> generateTenderAddressFromPrivateKey(childKey.privateKeyAsHex)
                    1, 2 -> generateEthAddressFromPrivateKey(childKey.privateKeyAsHex)
                    else -> throw IllegalStateException("custom path $customPath for OKEX")
                }
            }
            BaseChain.EVMOS_MAIN,
            BaseChain.INJ_MAIN -> {
                generateAddressFromPrivateKey(fetchPrefix(chain), childKey.privateKeyAsHex)
            }
            else -> {
                getDpAddress(fetchPrefix(chain), childKey.publicKeyAsHex)
            }
        }
    }


    fun byteArrayToHexString(bytes: ByteArray): String {
        val hexArray = charArrayOf(
            '0',
            '1',
            '2',
            '3',
            '4',
            '5',
            '6',
            '7',
            '8',
            '9',
            'a',
            'b',
            'c',
            'd',
            'e',
            'f'
        )
        val hexChars = CharArray(bytes.size * 2)
        for (j in bytes.indices) {
            val v = bytes[j].toInt() and 0xFF
            hexChars[j * 2] = hexArray[v ushr 4]
            hexChars[j * 2 + 1] = hexArray[v and 0x0F]
        }
        return String(hexChars)
    }

    @Throws(IllegalArgumentException::class)
    fun hexStringToByteArray(s: String): ByteArray {
        val len = s.length
        require(len % 2 != 1) { "Hex string must have even number of characters" }
        val data = ByteArray(len / 2)
        var i = 0
        while (i < len) {
            data[i / 2] = ((Character.digit(s[i], 16) shl 4)
                + Character.digit(s[i + 1], 16)).toByte()
            i += 2
        }
        return data
    }

    @Throws(Exception::class)
    private fun getHDSeed(entropy: ByteArray): ByteArray {
        return MnemonicCode.toSeed(MnemonicCode.INSTANCE.toMnemonic(entropy), "")
    }


    @Throws(Exception::class)
    private fun getCreateKeyWithPathFromEntropy(
        chain: BaseChain,
        entropy: String,
        path: Int,
        customPath: Int
    ): DeterministicKey {
        val result: DeterministicKey
        val masterKey = HDKeyDerivation.createMasterPrivateKey(
            getHDSeed(hexStringToByteArray(entropy))
        )
        val parentPath = getParentPath(chain, customPath)
        result = if (chain != BaseChain.FETCHAI_MAIN || customPath != 2) {
            DeterministicHierarchy(masterKey)
                .deriveChild(
                    parentPath,
                    true,
                    true,
                    ChildNumber(path)
                )
        } else {
            val targetKey = DeterministicHierarchy(masterKey)
                .deriveChild(
                    parentPath,
                    true,
                    true,
                    ChildNumber(path, true)
                )
            DeterministicHierarchy(targetKey)
                .deriveChild(
                    listOf(ChildNumber.ZERO),
                    true,
                    true,
                    ChildNumber.ZERO
                )
        }
        return result
    }

    private fun getParentPath(chain: BaseChain?, customPath: Int): List<ChildNumber>? {
        val result = ArrayList<ChildNumber>()
        result.add(ChildNumber(44, true))
        val childNumber: Int
        var lastZero = true
        var lastHardenedZero = true
        childNumber = when (chain) {
            BaseChain.BNB_MAIN -> 714
            BaseChain.BAND_MAIN -> 494
            BaseChain.IOV_MAIN -> 234
            BaseChain.PERSIS_MAIN -> 750
            BaseChain.CRYPTO_MAIN -> 394
            BaseChain.MEDI_MAIN -> 371
            BaseChain.INJ_MAIN, BaseChain.EVMOS_MAIN -> 60
            BaseChain.BITSONG_MAIN -> 639
            BaseChain.DESMOS_MAIN -> 852
            BaseChain.PROVENANCE_MAIN -> 505
            BaseChain.KAVA_MAIN -> {
                when (customPath) {
                    0 -> 118
                    else -> 459
                }
            }
            BaseChain.SECRET_MAIN -> {
                when (customPath) {
                    0 -> 118
                    else -> 529
                }
            }
            BaseChain.LUM_MAIN -> {
                when (customPath) {
                    0 -> 118
                    else -> 880
                }
            }
            BaseChain.FETCHAI_MAIN -> {
                when (customPath) {
                    0 -> 118
                    1 -> 60
                    2 -> {
                        lastHardenedZero = false
                        lastZero = false
                        60
                    }
                    else -> {
                        lastZero = false
                        60
                    }
                }
            }
            BaseChain.OKEX_MAIN -> when (customPath) {
                0, 1 -> 996
                else -> 60
            }
            else -> 118
        }
        result.add(ChildNumber(childNumber, true))
        if (lastHardenedZero) {
            result.add(ChildNumber.ZERO_HARDENED)
        }
        if (lastZero) {
            result.add(ChildNumber.ZERO)
        }
        return result
    }

    // Ethermint Style Key gen (OKex)

    @Throws(java.lang.Exception::class)
    fun createNewAddressSecp256k1(mainPrefix: String, publickKey: ByteArray?): String {
        val uncompressedPubKey = ECKey.CURVE.curve.decodePoint(publickKey).getEncoded(false)
        val pub = ByteArray(64)
        System.arraycopy(uncompressedPubKey, 1, pub, 0, 64)
        val address = Keys.getAddress(pub)
        val bytes = convertBits(address, 8, 5, true)
        return Bech32.encode(mainPrefix, bytes)
    }

    private fun generateEthAddressFromPrivateKey(privateKey: String): String {
        val pubKey = hexPublicKeyFromPrivateKey(privateKey)
        val uncompressedPubKey = ECKey.CURVE.curve.decodePoint(Hex.decode(pubKey)).getEncoded(false)
        val pub = ByteArray(64)
        System.arraycopy(uncompressedPubKey, 1, pub, 0, 64)
        val address = Keys.getAddress(pub)
        return "0x" + byteArrayToHexString(address)
    }

    private fun generateTenderAddressFromPrivateKey(privateKey: String): String {
        val pubKey = hexPublicKeyFromPrivateKey(privateKey)
        val digest = Sha256.getSha256Digest()
        val hash = digest.digest(hexStringToByteArray(pubKey))
        val digest2 = RIPEMD160Digest()
        digest2.update(hash, 0, hash.size)
        val hash3 = ByteArray(digest2.digestSize)
        digest2.doFinal(hash3, 0)
        return "0x" + byteArrayToHexString(hash3)
    }

    private fun hexPublicKeyFromPrivateKey(privateKey: String): String {
        return ECKey.fromPrivate(BigInteger(privateKey, 16)).publicKeyAsHex
    }

    private fun generateAddressFromPublicKey(prefix: String, publicKey: String): String {
        return createNewAddressSecp256k1(prefix, Hex.decode(publicKey))
    }

    private fun generateAddressFromPrivateKey(prefix: String, privateKey: String): String {
        val publicKey = hexPublicKeyFromPrivateKey(privateKey)
        return generateAddressFromPublicKey(prefix, publicKey)
    }

    private fun fetchPrefix(chain: BaseChain): String {
        var prefix = chain.chainAddressPrefix
        if (prefix.endsWith("1")) {
            prefix = prefix.substring(0, prefix.length - 1)
        }
        return prefix
    }

    private fun getDpAddress(prefix: String, publicHexKey: String): String {
        val result: String
        val digest = Sha256.getSha256Digest()
        val hash = digest.digest(hexStringToByteArray(publicHexKey))
        val digest2 = RIPEMD160Digest()
        digest2.update(hash, 0, hash.size)
        val hash3 = ByteArray(digest2.digestSize)
        digest2.doFinal(hash3, 0)
        val converted = convertBits(hash3, 8, 5, true)
        result = bech32Encode(prefix.toByteArray(), converted)
        return result
    }

    private fun bech32Encode(hrp: ByteArray, data: ByteArray): String {
        val chk = createChecksum(hrp, data)
        val combined = ByteArray(chk.size + data.size)
        System.arraycopy(data, 0, combined, 0, data.size)
        System.arraycopy(chk, 0, combined, data.size, chk.size)
        val xlat = ByteArray(combined.size)
        for (i in combined.indices) {
            xlat[i] = CHARSET[combined[i].toInt()]
        }
        val ret = ByteArray(hrp.size + xlat.size + 1)
        System.arraycopy(hrp, 0, ret, 0, hrp.size)
        System.arraycopy(byteArrayOf(0x31), 0, ret, hrp.size, 1)
        System.arraycopy(xlat, 0, ret, hrp.size + 1, xlat.size)
        return String(ret)
    }

    @Throws(java.lang.Exception::class)
    fun convertBits(data: ByteArray, frombits: Int, tobits: Int, pad: Boolean): ByteArray {
        var acc = 0
        var bits = 0
        val baos = ByteArrayOutputStream()
        val maxv = (1 shl tobits) - 1
        for (i in data.indices) {
            val value: Int = data[i].toInt() and 0xff
            if (value ushr frombits != 0) {
                throw IllegalStateException("invalid data range: data[$i]=$value (frombits=$frombits)")
            }
            acc = acc shl frombits or value
            bits += frombits
            while (bits >= tobits) {
                bits -= tobits
                baos.write(acc ushr bits and maxv)
            }
        }
        when {
            pad -> {
                if (bits > 0) {
                    baos.write(acc shl tobits - bits and maxv)
                }
            }
            bits >= frombits -> throw IllegalStateException("illegal zero padding")
            acc shl tobits - bits and maxv != 0 -> throw IllegalStateException("non-zero padding")
        }
        return baos.toByteArray()
    }

    private fun createChecksum(hrp: ByteArray, data: ByteArray): ByteArray {
        val zeroes = byteArrayOf(0x00, 0x00, 0x00, 0x00, 0x00, 0x00)
        val expanded = hrpExpand(hrp)
        val values = ByteArray(zeroes.size + expanded.size + data.size)
        System.arraycopy(expanded, 0, values, 0, expanded.size)
        System.arraycopy(data, 0, values, expanded.size, data.size)
        System.arraycopy(zeroes, 0, values, expanded.size + data.size, zeroes.size)
        val polymod = polymod(values) xor 1
        val ret = ByteArray(6)
        for (i in ret.indices) {
            ret[i] = (polymod shr 5 * (5 - i) and 0x1f).toByte()
        }
        return ret
    }

    private fun polymod(values: ByteArray): Int {
        var chk = 1
        for (b in values) {
            val top = (chk shr 0x19).toByte()
            chk = b.toInt() xor (chk and 0x1ffffff shl 5)
            for (i in 0..4) {
                chk = chk xor if (top.toInt() shr i and 1 == 1) GENERATORS[i] else 0
            }
        }
        return chk
    }

    private fun hrpExpand(hrp: ByteArray): ByteArray {
        val buf1 = ByteArray(hrp.size)
        val buf2 = ByteArray(hrp.size)
        val mid = ByteArray(1)
        for (i in hrp.indices) {
            buf1[i] = (hrp[i].toInt() shr 5).toByte()
        }
        mid[0] = 0x00
        for (i in hrp.indices) {
            buf2[i] = (hrp[i].toInt() and 0x1f).toByte()
        }
        val ret = ByteArray(hrp.size * 2 + 1)
        System.arraycopy(buf1, 0, ret, 0, buf1.size)
        System.arraycopy(mid, 0, ret, buf1.size, mid.size)
        System.arraycopy(buf2, 0, ret, buf1.size + mid.size, buf2.size)
        return ret
    }
}