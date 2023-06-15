package wannabit.io.cosmostaion.database

import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import android.util.Base64
import wannabit.io.cosmostaion.database.model.EncryptData
import java.security.KeyPairGenerator
import java.security.KeyStore
import java.security.KeyStoreException
import java.security.Signature
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.GCMParameterSpec

object CryptoHelper {
    private const val KEYSTORE = "AndroidKeyStore"
    private const val TRANSFORMATION = "AES/GCM/NoPadding"
    private const val TYPE_RSA = "RSA"
    private const val SIGNATURE_SHA256withRSA = "SHA256withRSA"
    private const val PASSWORD_KEY = "PASSWORD_KEY"
    private const val FINGER_KEY = "FINGER_KEY"

    @Throws(Exception::class)
    fun loadKeyStore(): KeyStore {
        return try {
            val keyStore = KeyStore.getInstance(KEYSTORE)
            keyStore.load(null)
            keyStore
        } catch (e: Exception) {
            throw e
        }
    }

    @Throws(Exception::class)
    private fun getEncodeCipher(alias: String, withAuth: Boolean): Cipher {
        val encCipher = Cipher.getInstance(TRANSFORMATION)
        val keyStore = loadKeyStore()
        generateKeyIfNecessary(keyStore, alias, withAuth)
        val key = keyStore.getKey(alias, null) as SecretKey
        encCipher.init(Cipher.ENCRYPT_MODE, key)
        return encCipher
    }

    @Throws(Exception::class)
    private fun getDecodeCipher(alias: String, iv: ByteArray): Cipher {
        val decCipher = Cipher.getInstance(TRANSFORMATION)
        val keyStore = loadKeyStore()
        val secretKey = (keyStore.getEntry(alias, null) as KeyStore.SecretKeyEntry).secretKey
        val spec = GCMParameterSpec(128, iv)
        decCipher.init(Cipher.DECRYPT_MODE, secretKey, spec)
        return decCipher
    }

    fun doEncryptData(alias: String, resource: String, withAuth: Boolean): EncryptData? {
        return try {
            val cipher = getEncodeCipher(alias, withAuth)
            val end = cipher.doFinal(resource.toByteArray(charset("UTF-8")))
            EncryptData(end, cipher.iv)
        } catch (e: Exception) {
            null
        }
    }

    fun doDecryptData(alias: String, resource: String, iv: String): String? {
        return try {
            val cipher = getDecodeCipher(alias, Base64.decode(iv, Base64.DEFAULT))
            String(cipher.doFinal(Base64.decode(resource, Base64.DEFAULT)), Charsets.UTF_8)
        } catch (e: Exception) {
            null
        }
    }

    fun signData(inputStr: String, alias: String): String? {
        var result: String? = null
        result = try {
            val data = inputStr.toByteArray()
            val keyStore = loadKeyStore()
            generateKeyPairIfNecessary(keyStore, alias)
            val entry = keyStore.getEntry(alias, null) as? KeyStore.PrivateKeyEntry ?: return null
            val s = Signature.getInstance(SIGNATURE_SHA256withRSA)
            s.initSign(entry.privateKey)
            s.update(data)
            val signature = s.sign()
            Base64.encodeToString(signature, Base64.DEFAULT)
        } catch (e: Exception) {
            return result
        }
        return result
    }

    fun verifyData(input: String, signatureStr: String?, alias: String?): Boolean {
        var result = false
        try {
            val data = input.toByteArray()
            val keyStore = loadKeyStore()
            val signature = Base64.decode(signatureStr, Base64.DEFAULT)
            val s = Signature.getInstance(SIGNATURE_SHA256withRSA)
            s.initVerify(keyStore.getCertificate(alias))
            s.update(data)
            result = s.verify(signature)
        } catch (e: Exception) {
        }
        return result
    }

    private fun generateKeyIfNecessary(keyStore: KeyStore, alias: String, withAuth: Boolean): Boolean {
        try {
            return keyStore.containsAlias(alias) || generateKey(alias, withAuth)
        } catch (e: KeyStoreException) {
            e.printStackTrace()
        }
        return false
    }

    private fun generateKeyPairIfNecessary(keyStore: KeyStore, alias: String): Boolean {
        try {
            return keyStore.containsAlias(alias) || generateKeyPair(alias)
        } catch (e: KeyStoreException) {
            e.printStackTrace()
        }
        return false
    }

    private fun generateKey(alias: String, withAuth: Boolean): Boolean {
        return try {
            val keyGenerator = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, KEYSTORE)
            keyGenerator.init(
                KeyGenParameterSpec.Builder(
                    alias, KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
                ).setBlockModes(KeyProperties.BLOCK_MODE_GCM).setUserAuthenticationRequired(withAuth).setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE).build()
            )
            keyGenerator.generateKey()
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    private fun generateKeyPair(alias: String): Boolean {
        return try {
            val kpGenerator = KeyPairGenerator.getInstance(TYPE_RSA, KEYSTORE)
            kpGenerator.initialize(
                KeyGenParameterSpec.Builder(alias, KeyProperties.PURPOSE_SIGN).setDigests(KeyProperties.DIGEST_SHA256).setSignaturePaddings(KeyProperties.SIGNATURE_PADDING_RSA_PKCS1).build()
            )
            kpGenerator.generateKeyPair()
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    @Throws(Exception::class)
    fun isKeystoreContainAlias(alias: String?): Boolean {
        val keyStore = loadKeyStore()
        return try {
            keyStore.containsAlias(alias)
        } catch (e: KeyStoreException) {
            e.printStackTrace()
            throw e
        }
    }

    @Throws(Exception::class)
    fun deleteKey(alias: String?) {
        val keyStore = loadKeyStore()
        try {
            keyStore.deleteEntry(alias)
        } catch (e: KeyStoreException) {
            e.printStackTrace()
            throw e
        }
    }
}