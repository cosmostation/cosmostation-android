package wannabit.io.cosmostaion.database.model

import android.util.Base64

data class EncryptData(var encData: ByteArray, var ivData: ByteArray) {
    val encDataString: String?
        get() {
            return try {
                Base64.encodeToString(encData, 0)
            } catch (e: Exception) {
                null
            }
        }

    val ivDataString: String?
        get() {
            return try {
                Base64.encodeToString(ivData, 0)
            } catch (e: Exception) {
                null
            }
        }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as EncryptData

        if (!encData.contentEquals(other.encData)) return false
        if (!ivData.contentEquals(other.ivData)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = encData.contentHashCode()
        result = 31 * result + ivData.contentHashCode()
        return result
    }
}