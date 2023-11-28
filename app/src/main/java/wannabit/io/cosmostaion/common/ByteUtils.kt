package wannabit.io.cosmostaion.common

import org.bouncycastle.crypto.digests.RIPEMD160Digest
import java.io.ByteArrayOutputStream
import java.math.BigInteger
import java.nio.ByteBuffer

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
}