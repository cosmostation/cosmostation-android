package wannabit.io.cosmostaion.common

import org.bitcoinj.core.AddressFormatException
import org.bitcoinj.core.Base58
import org.bitcoinj.core.Bech32
import org.bitcoinj.core.NetworkParameters
import org.bitcoinj.core.SegwitAddress
import org.bitcoinj.core.Sha256Hash
import org.bitcoinj.params.MainNetParams
import org.bitcoinj.params.TestNet3Params
import org.bouncycastle.crypto.digests.RIPEMD160Digest
import java.io.ByteArrayOutputStream
import java.math.BigInteger
import java.nio.ByteBuffer
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

object ByteUtils {

    fun hashToRIPMD160(sha256Hash: ByteArray): ByteArray {
        val rDigest = RIPEMD160Digest()
        rDigest.update(sha256Hash, 0, sha256Hash.size)
        val digestResult = ByteArray(rDigest.digestSize)
        rDigest.doFinal(digestResult, 0)
        return digestResult
    }

    fun convertBits(data: ByteArray, fromBits: Int, toBits: Int, pad: Boolean): ByteArray {
        var acc = 0
        var bits = 0
        val baos = ByteArrayOutputStream()
        val maxv = (1 shl toBits) - 1
        for (i in data.indices) {
            val value = data[i].toInt() and 0xff
            if (value ushr fromBits != 0) {
                throw Exception("invalid data range: data[$i]=$value (fromBits=$fromBits)")
            }
            acc = (acc shl fromBits) or value
            bits += fromBits
            while (bits >= toBits) {
                bits -= toBits
                baos.write((acc ushr bits) and maxv)
            }
        }
        if (pad) {
            if (bits > 0) {
                baos.write((acc shl (toBits - bits)) and maxv)
            }
        } else if (bits >= fromBits) {
            throw Exception("illegal zero padding")
        } else if ((acc shl (toBits - bits)) and maxv != 0) {
            throw Exception("non-zero padding")
        }
        return baos.toByteArray()
    }

    fun integerToBytes(s: BigInteger, length: Int): ByteArray {
        val bytes = s.toByteArray()
        if (length < bytes.size) {
            val tmp = ByteArray(length)
            System.arraycopy(bytes, bytes.size - tmp.size, tmp, 0, tmp.size)
            return tmp
        } else if (length > bytes.size) {
            val tmp = ByteArray(length)
            System.arraycopy(bytes, 0, tmp, tmp.size - bytes.size, bytes.size)
            return tmp
        }
        return bytes
    }

    fun longToBytes(x: Long): ByteArray {
        val buffer = ByteBuffer.allocate(8)
        buffer.putLong(x)
        return buffer.array()
    }

    fun hexStringToByteArray(s: String): ByteArray {
        val len = s.length
        val data = ByteArray(len / 2)

        var j = 0
        for (i in 0 until len step 2) {
            data[j++] = ((Character.digit(s[i], 16) shl 4) + Character.digit(s[i + 1], 16)).toByte()
        }
        return data
    }

    fun convertBech32ToEvm(address: String?): String {
        try {
            val pub = convertBits(Bech32.decode(address).data, 5, 8, false)
            return "0x" + pub.toHex()
        } catch (e: AddressFormatException.InvalidDataLength) {
            e.printStackTrace()
        }
        return ""
    }

    fun convertEvmToBech32(address: String, prefix: String?): String {
        val pub = hexStringToByteArray(address.replace("0x", ""))
        val bytes = convertBits(pub, 8, 5, true)
        return Bech32.encode(Bech32.Encoding.BECH32, prefix, bytes)
    }

    fun shaking(input: ByteArray?, key: ByteArray): Pair<ByteArray, ByteArray> {
        val mac = Mac.getInstance("HmacSHA512")
        val secretKeySpec = SecretKeySpec(key, "HmacSHA512")
        mac.init(secretKeySpec)
        val result = mac.doFinal(input)
        return Pair(result.sliceArray(0..31), result.sliceArray(32..63))
    }

    fun base58ChecksumEncode(networkAndHash: ByteArray): String {
        val checksum = Sha256Hash.hash(Sha256Hash.hash(networkAndHash)).copyOfRange(0, 4)
        val dataWithChecksum = networkAndHash + checksum
        return Base58.encode(dataWithChecksum)
    }

    fun segWitOutputScript(ripemd160: ByteArray, versionByte: Byte): ByteArray {
        val script = ByteArray(2 + ripemd160.size)
        script[0] = versionByte
        script[1] = ripemd160.size.toByte()
        System.arraycopy(ripemd160, 0, script, 2, ripemd160.size)
        return script
    }

    fun encodeSegWitAddress(ripemd160: ByteArray, prefix: String?): String {
        val params = if (prefix == "bc") {
            MainNetParams.get()
        } else {
            TestNet3Params.get()
        }
        val segwitAddress = SegwitAddress.fromHash(params, ripemd160)
        return segwitAddress.toBech32()
    }
}