package wannabit.io.cosmostaion.common

import java.io.ByteArrayOutputStream

object ByteUtils {
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
}