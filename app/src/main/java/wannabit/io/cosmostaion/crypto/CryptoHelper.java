package wannabit.io.cosmostaion.crypto;

import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyProperties;
import android.util.Base64;

import androidx.annotation.NonNull;

import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.Signature;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;

import wannabit.io.cosmostaion.utils.WLog;

public class CryptoHelper {

    private static final String KEYSTORE = "AndroidKeyStore";
    private static final String TRANSFORMATION = "AES/GCM/NoPadding";
    private static final String TYPE_RSA = "RSA";
    private static final String SIGNATURE_SHA256withRSA = "SHA256withRSA";

    private static final String PASSWORD_KEY = "PASSWORD_KEY";
    private static final String FINGER_KEY = "FINGER_KEY";


    public static KeyStore loadKeyStore() throws Exception {
        try {
            final KeyStore keyStore = KeyStore.getInstance(KEYSTORE);
            keyStore.load(null);
            return keyStore;

        } catch (Exception e) {
            WLog.w("loadKeyStore Error");
            throw e;
        }
    }


    private static Cipher getEncodeCipher(String alias, boolean withAuth) throws Exception {
        final Cipher encCipher = Cipher.getInstance(TRANSFORMATION);
        final KeyStore keyStore = loadKeyStore();
        generateKeyIfNecessary(keyStore, alias, withAuth);
        SecretKey key = (SecretKey) keyStore.getKey(alias, null);
        encCipher.init(Cipher.ENCRYPT_MODE, key);
        return encCipher;
    }

    private static Cipher getDecodeCipher(String alias, byte[] iv) throws Exception {
        final Cipher decCipher = Cipher.getInstance(TRANSFORMATION);
        final KeyStore keyStore = loadKeyStore();
        SecretKey secretKey = ((KeyStore.SecretKeyEntry) keyStore.getEntry(alias, null)).getSecretKey();
        final GCMParameterSpec spec = new GCMParameterSpec(128, iv);
        decCipher.init(Cipher.DECRYPT_MODE, secretKey, spec);
        return decCipher;
    }


    public static EncResult doEncryptData(String alias, String resource, boolean withAuth) {
        EncResult result = null;
        try {
            Cipher cipher = getEncodeCipher(alias, withAuth);
            byte[] end = cipher.doFinal(resource.getBytes("UTF-8"));
            result = new EncResult(end, cipher.getIV());

        } catch (Exception e) {
            result = null;
        }
        return result;
    }

    public static String doDecryptData(String alias, String resource, String iv) {
        String result = null;
        try {
            Cipher cipher = getDecodeCipher(alias, Base64.decode(iv, Base64.DEFAULT));
            result = new String(cipher.doFinal(Base64.decode(resource, Base64.DEFAULT)), "UTF-8");
        } catch (Exception e) {
            result = null;
        }
        return result;

    }


    public static String signData(String inputStr, String alias) {
        String result = null;
        try {
            final byte[] data = inputStr.getBytes();
            final KeyStore keyStore = loadKeyStore();
            generateKeyPairIfNecessary(keyStore, alias);
            KeyStore.Entry entry = keyStore.getEntry(alias, null);
            if (entry == null) {
                return null;
            }
            if (!(entry instanceof KeyStore.PrivateKeyEntry)) {
                return null;
            }
            Signature s = Signature.getInstance(SIGNATURE_SHA256withRSA);
            s.initSign(((KeyStore.PrivateKeyEntry) entry).getPrivateKey());
            s.update(data);
            byte[] signature = s.sign();
            result = Base64.encodeToString(signature, Base64.DEFAULT);

        } catch (Exception e) {
            return result;
        }
        return result;
    }

    public static boolean verifyData(String input, String signatureStr, String alias) {
        boolean result = false;
        try {

            final byte[] data = input.getBytes();
            final KeyStore keyStore = loadKeyStore();

            byte[] signature = Base64.decode(signatureStr, Base64.DEFAULT);
            Signature s = Signature.getInstance(SIGNATURE_SHA256withRSA);

            s.initVerify(keyStore.getCertificate(alias));
            s.update(data);
            result = s.verify(signature);

        } catch (Exception e) {
            WLog.w("verifyData Error");

        }
        return result;

    }


    private static boolean generateKeyIfNecessary(@NonNull KeyStore keyStore, String alias, boolean withAuth) {
        try {
            return keyStore.containsAlias(alias) || generateKey(alias, withAuth);
        } catch (KeyStoreException e) {
            e.printStackTrace();
        }
        return false;
    }

    private static boolean generateKeyPairIfNecessary(@NonNull KeyStore keyStore, String alias) {
        try {
            return keyStore.containsAlias(alias) || generateKeyPair(alias);
        } catch (KeyStoreException e) {
            e.printStackTrace();
        }
        return false;
    }


    private static boolean generateKey(String alias, boolean withAuth) {
        try {
            final KeyGenerator keyGenerator = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, KEYSTORE);
            keyGenerator.init(new
                    KeyGenParameterSpec.Builder(alias,
                    KeyProperties.PURPOSE_ENCRYPT | KeyProperties.PURPOSE_DECRYPT)
                    .setBlockModes(KeyProperties.BLOCK_MODE_GCM)
                    .setUserAuthenticationRequired(withAuth)
                    .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
                    .build());
            keyGenerator.generateKey();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    private static boolean generateKeyPair(String alias) {
        try {
            KeyPairGenerator kpGenerator = KeyPairGenerator.getInstance(TYPE_RSA, KEYSTORE);
            kpGenerator.initialize(new KeyGenParameterSpec.Builder(alias, KeyProperties.PURPOSE_SIGN)
                    .setDigests(KeyProperties.DIGEST_SHA256)
                    .setSignaturePaddings(KeyProperties.SIGNATURE_PADDING_RSA_PKCS1)
                    .build());
            kpGenerator.generateKeyPair();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    public static boolean isKeystoreContainAlias(String alias) throws Exception {
        final KeyStore keyStore = loadKeyStore();
        try {
            return keyStore.containsAlias(alias);
        } catch (KeyStoreException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public static void deleteKey(String alias) throws Exception {
        final KeyStore keyStore = loadKeyStore();
        try {
            keyStore.deleteEntry(alias);
        } catch (KeyStoreException e) {
            e.printStackTrace();
            throw e;
        }
    }

}
