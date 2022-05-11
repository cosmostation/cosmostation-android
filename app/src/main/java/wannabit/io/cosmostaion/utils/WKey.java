package wannabit.io.cosmostaion.utils;

import static org.bitcoinj.core.ECKey.CURVE;
import static wannabit.io.cosmostaion.base.BaseChain.AKASH_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.ALTHEA_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.ASSETMANTLE_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.AXELAR_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.BAND_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.BITCANNA_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.BITSONG_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.BNB_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.CERBERUS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.CERTIK_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.CHIHUAHUA_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.COMDEX_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.COSMOS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.COSMOS_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.CRESCENT_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.CRESCENT_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.CRYPTO_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.CUDOS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.DESMOS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.EMONEY_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.EVMOS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.FETCHAI_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.GRABRIDGE_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.INJ_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.IOV_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.IRIS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.IRIS_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.JUNO_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.KAVA_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.KI_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.KONSTELL_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.LUM_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.MEDI_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.OKEX_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.OMNIFLIX_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.OSMOSIS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.PERSIS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.PROVENANCE_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.REGEN_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.RIZON_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.SECRET_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.SENTINEL_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.SIF_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.STARGAZE_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.STATION_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.UMEE_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.getChain;

import android.util.Base64;

import com.google.common.collect.ImmutableList;
import com.google.protobuf.ByteString;
import com.google.protobuf2.Any;

import org.bitcoinj.core.Bech32;
import org.bitcoinj.core.ECKey;
import org.bitcoinj.crypto.ChildNumber;
import org.bitcoinj.crypto.DeterministicHierarchy;
import org.bitcoinj.crypto.DeterministicKey;
import org.bitcoinj.crypto.HDKeyDerivation;
import org.bitcoinj.crypto.MnemonicCode;
import org.bitcoinj.crypto.MnemonicException;
import org.bouncycastle.crypto.digests.RIPEMD160Digest;
import org.bouncycastle.util.encoders.Hex;
import org.web3j.crypto.Keys;

import java.io.ByteArrayOutputStream;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cosmos.auth.v1beta1.QueryOuterClass;
import wannabit.io.cosmostaion.BuildConfig;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.crypto.Sha256;
import wannabit.io.cosmostaion.dao.Account;

public class WKey {

    private static final String CHARSET = "qpzry9x8gf2tvdw0s3jn54khce6mua7l";

    public static byte[] getEntropy() {
        byte[] seed = new byte[32];
        new SecureRandom().nextBytes(seed);
        return seed;
    }

    public static List<String> getRandomMnemonic(byte[] entropy) {
        List<String> result = new ArrayList<>();
        try {
            result = MnemonicCode.INSTANCE.toMnemonic(entropy);

        } catch (MnemonicException.MnemonicLengthException e) {
            if(BuildConfig.DEBUG)
                e.printStackTrace();

        }
        return result;
    }

    public static byte[] toEntropy(ArrayList<String> words) {
        try {
            return new MnemonicCode().toEntropy(words);
        }catch (Exception e) {
            return null;
        }
    }


    public static byte[] getHDSeed(byte[] entropy) {
        try {
            return MnemonicCode.toSeed(MnemonicCode.INSTANCE.toMnemonic(entropy), "");
        } catch (Exception e) {
            return null;
        }
    }

    public static byte[] getByteHdSeedFromWords(ArrayList<String> words) {
        return getHDSeed(toEntropy(words));
    }

    public static String getStringHdSeedFromWords(ArrayList<String> words) {
        return WUtil.ByteArrayToHexString(getByteHdSeedFromWords(words));
    }

    public static boolean isValidStringHdSeedFromWords(ArrayList<String> words) {
        if (getByteHdSeedFromWords(words) == null) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean isValidStringPrivateKey (String input) {
        boolean result = false;
        String regex = "^(0x|0X)?[a-fA-F0-9]{64}";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(input);
        if (m.matches()) {
            result = true;
        }
        return result;
    }

    public static List<ChildNumber> getParentPath(BaseChain chain, int customPath) {
        if (chain.equals(BNB_MAIN)) {
            return  ImmutableList.of(new ChildNumber(44, true), new ChildNumber(714, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO);

        } else if (chain.equals(BAND_MAIN)) {
            return  ImmutableList.of(new ChildNumber(44, true), new ChildNumber(494, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO);

        } else if (chain.equals(IOV_MAIN)) {
            return  ImmutableList.of(new ChildNumber(44, true), new ChildNumber(234, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO);

        } else if (chain.equals(PERSIS_MAIN)) {
            return  ImmutableList.of(new ChildNumber(44, true), new ChildNumber(750, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO);

        } else if (chain.equals(CRYPTO_MAIN)) {
            return  ImmutableList.of(new ChildNumber(44, true), new ChildNumber(394, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO);

        } else if (chain.equals(MEDI_MAIN)) {
            return  ImmutableList.of(new ChildNumber(44, true), new ChildNumber(371, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO);

        } else if (chain.equals(INJ_MAIN) || chain.equals(EVMOS_MAIN)) {
            return  ImmutableList.of(new ChildNumber(44, true), new ChildNumber(60, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO);

        } else if (chain.equals(BITSONG_MAIN)) {
            return  ImmutableList.of(new ChildNumber(44, true), new ChildNumber(639, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO);

        } else if (chain.equals(DESMOS_MAIN)) {
            return  ImmutableList.of(new ChildNumber(44, true), new ChildNumber(852, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO);

        } else if (chain.equals(PROVENANCE_MAIN)) {
            return  ImmutableList.of(new ChildNumber(44, true), new ChildNumber(505, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO);

        }

        else if (chain.equals(KAVA_MAIN)) {
            if (customPath == 0) {
                return  ImmutableList.of(new ChildNumber(44, true), new ChildNumber(118, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO);
            } else {
                return  ImmutableList.of(new ChildNumber(44, true), new ChildNumber(459, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO);
            }

        } else if (chain.equals(SECRET_MAIN)) {
            if (customPath == 0) {
                return  ImmutableList.of(new ChildNumber(44, true), new ChildNumber(118, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO);
            } else {
                return  ImmutableList.of(new ChildNumber(44, true), new ChildNumber(529, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO);
            }

        } else if (chain.equals(LUM_MAIN)) {
            if (customPath == 0) {
                return  ImmutableList.of(new ChildNumber(44, true), new ChildNumber(118, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO);
            } else {
                return  ImmutableList.of(new ChildNumber(44, true), new ChildNumber(880, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO);
            }

        } else if (chain.equals(FETCHAI_MAIN)) {
            if (customPath == 0) {
                return  ImmutableList.of(new ChildNumber(44, true), new ChildNumber(118, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO);
            } else if (customPath == 1){
                return  ImmutableList.of(new ChildNumber(44, true), new ChildNumber(60, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO);
            } else if (customPath == 2) {
                return  ImmutableList.of(new ChildNumber(44, true), new ChildNumber(60, true));
            } else {
                return  ImmutableList.of(new ChildNumber(44, true), new ChildNumber(60, true), ChildNumber.ZERO_HARDENED);
            }

        } else if (chain.equals(OKEX_MAIN)) {
            if (customPath == 0 || customPath == 1) {
                return  ImmutableList.of(new ChildNumber(44, true), new ChildNumber(996, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO);
            } else {
                return  ImmutableList.of(new ChildNumber(44, true), new ChildNumber(60, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO);
            }

        } else {
            return  ImmutableList.of(new ChildNumber(44, true), new ChildNumber(118, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO);
        }
    }

    public static List<ChildNumber> getFetchParentPath2() {
        return ImmutableList.of(ChildNumber.ZERO);
    }


    //singer
    public static DeterministicKey getKeyWithPathfromEntropy(Account account, String entropy) {
        BaseChain chain = getChain(account.baseChain);
        if (!chain.equals(FETCHAI_MAIN)) {
            DeterministicKey masterKey = HDKeyDerivation.createMasterPrivateKey(getHDSeed(WUtil.HexStringToByteArray(entropy)));
            return new DeterministicHierarchy(masterKey).deriveChild(WKey.getParentPath(chain, account.customPath), true, true,  new ChildNumber(Integer.parseInt(account.path)));
        } else {
            DeterministicKey masterKey = HDKeyDerivation.createMasterPrivateKey(getHDSeed(WUtil.HexStringToByteArray(entropy)));
            if (account.customPath != 2) {
                DeterministicKey targetKey = new DeterministicHierarchy(masterKey).deriveChild(WKey.getParentPath(chain, account.customPath), true, true,  new ChildNumber(Integer.parseInt(account.path)));
                return targetKey;
            } else {
                DeterministicKey targetKey = new DeterministicHierarchy(masterKey).deriveChild(WKey.getParentPath(chain, account.customPath), true, true,  new ChildNumber(Integer.parseInt(account.path), true));
                DeterministicKey targetKey2 = new DeterministicHierarchy(targetKey).deriveChild(WKey.getFetchParentPath2(), true, true,  ChildNumber.ZERO);
                return targetKey2;
            }
        }
    }

    // create, restore
    public static DeterministicKey getCreateKeyWithPathfromEntropy(BaseChain chain, String entropy, int path, int customPath) {
        if (!chain.equals(FETCHAI_MAIN)) {
            DeterministicKey masterKey = HDKeyDerivation.createMasterPrivateKey(getHDSeed(WUtil.HexStringToByteArray(entropy)));
            return new DeterministicHierarchy(masterKey).deriveChild(WKey.getParentPath(chain, customPath), true, true,  new ChildNumber(path));
        } else {
            DeterministicKey masterKey = HDKeyDerivation.createMasterPrivateKey(getHDSeed(WUtil.HexStringToByteArray(entropy)));
            if (customPath != 2) {
                DeterministicKey targetKey = new DeterministicHierarchy(masterKey).deriveChild(WKey.getParentPath(chain, customPath), true, true,  new ChildNumber(path));
                return targetKey;
            } else {
                DeterministicKey targetKey = new DeterministicHierarchy(masterKey).deriveChild(WKey.getParentPath(chain, customPath), true, true,  new ChildNumber(path, true));
                DeterministicKey targetKey2 = new DeterministicHierarchy(targetKey).deriveChild(WKey.getFetchParentPath2(), true, true,  ChildNumber.ZERO);
                return targetKey2;
            }
        }
    }

    public static boolean isMnemonicWords(ArrayList<String> words) {
        boolean result = true;
        List<String> mnemonics = MnemonicCode.INSTANCE.getWordList();
        for(String insert:words) {
            if(!mnemonics.contains(insert)) {
                result = false;
                break;
            }
        }
        return result;
    }


    public static boolean isValidBech32(String address) {
        boolean result = false;
        try {
            bech32Decode(address);
            result = true;
        } catch (Exception e) { }
        return result;
    }


    public static String getPubKeyValue(ECKey key) {
        String result = "";
        try {
            byte[] data = key.getPubKey();
            result = Base64.encodeToString(data, Base64.DEFAULT).replace("\n", "");
            WLog.w("base64 : " +  result);

        } catch (Exception e) {
            WLog.w("Exception");
        }
        return result;
    }

    // For gRpc Keys
    public static Any generateGrpcPubKeyFromPriv(QueryOuterClass.QueryAccountResponse auth, String privateKey) {
        ECKey ecKey = ECKey.fromPrivate(new BigInteger(privateKey, 16));
        if (auth.getAccount().getTypeUrl().contains("/injective.types.v1beta1.EthAccount")) {
            injective.crypto.v1beta1.ethsecp256k1.Keys.PubKey pubKey = injective.crypto.v1beta1.ethsecp256k1.Keys.PubKey.newBuilder().setKey(ByteString.copyFrom(ecKey.getPubKey())).build();
            return Any.newBuilder().setTypeUrl("/injective.crypto.v1beta1.ethsecp256k1.PubKey").setValue(pubKey.toByteString()).build();

        } else if (auth.getAccount().getTypeUrl().contains("/ethermint.types.v1.EthAccount")) {
            ethermint.crypto.v1.ethsecp256k1.Keys.PubKey pubKey = ethermint.crypto.v1.ethsecp256k1.Keys.PubKey.newBuilder().setKey(ByteString.copyFrom(ecKey.getPubKey())).build();
            return Any.newBuilder().setTypeUrl("/ethermint.crypto.v1.ethsecp256k1.PubKey").setValue(pubKey.toByteString()).build();

        } else {
            cosmos.crypto.secp256k1.Keys.PubKey pubKey = cosmos.crypto.secp256k1.Keys.PubKey.newBuilder().setKey(ByteString.copyFrom(ecKey.getPubKey())).build();
            return Any.newBuilder().setTypeUrl("/cosmos.crypto.secp256k1.PubKey").setValue(pubKey.toByteString()).build();
        }
    }

    // Ethermint Style Key gen (OKex)
    public static String createNewAddressSecp256k1(String mainPrefix, byte[] publickKey) throws Exception {
        byte[] uncompressedPubKey = CURVE.getCurve().decodePoint(publickKey).getEncoded(false);
        byte[] pub = new byte[64];
        System.arraycopy(uncompressedPubKey, 1, pub, 0, 64);

        byte[] address = Keys.getAddress(pub);
        WLog.w("eth address " + WUtil.ByteArrayToHexString(address));

        String addressResult = null;
        try {
            byte[] bytes = convertBits(address, 8, 5, true);
            addressResult = Bech32.encode(mainPrefix, bytes);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return addressResult;
    }

    public static String generateEthAddressFromPrivateKey(String privateKey) {
        String pubKey = generatePubKeyHexFromPriv(privateKey);
        byte[] uncompressedPubKey = CURVE.getCurve().decodePoint(Hex.decode(pubKey)).getEncoded(false);
        byte[] pub = new byte[64];
        System.arraycopy(uncompressedPubKey, 1, pub, 0, 64);

        byte[] address = Keys.getAddress(pub);
        return "0x" + WUtil.ByteArrayToHexString(address);
    }

    public static byte[] generateTenderAddressBytesFromPrivateKey(String privateKey) {
        String pubKey = generatePubKeyHexFromPriv(privateKey);
        MessageDigest digest = Sha256.getSha256Digest();
        byte[] hash = digest.digest(WUtil.HexStringToByteArray(pubKey));

        RIPEMD160Digest digest2 = new RIPEMD160Digest();
        digest2.update(hash, 0, hash.length);

        byte[] hash3 = new byte[digest2.getDigestSize()];
        digest2.doFinal(hash3, 0);

        return hash3;
    }

    public static String generateTenderAddressFromPrivateKey(String privateKey) {
        return "0x" + WUtil.ByteArrayToHexString(generateTenderAddressBytesFromPrivateKey(privateKey));
    }

    public static String convertAddressOkexToEth(String exAddress)  throws Exception {
        byte[] pub = convertBits(bech32Decode(exAddress).data, 5, 8, false);
        return "0x" + WUtil.ByteArrayToHexString(pub);
    }

    public static String convertAddressEthToOkex(String esAddress) throws Exception {
        String cosmoTypeAddress = esAddress;
        if (cosmoTypeAddress.startsWith("0x")) {
            cosmoTypeAddress = cosmoTypeAddress.replace("0x", "");
        }
        byte[] pub = WUtil.HexStringToByteArray(cosmoTypeAddress);
        String addressResult = null;
        try {
            byte[] bytes = convertBits(pub, 8, 5, true);
            addressResult = Bech32.encode("ex", bytes);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return addressResult;
    }

    public static boolean isValidEthAddress(String exAddress) {
        return org.web3j.crypto.WalletUtils.isValidAddress(exAddress);
    }


    public static String generatePubKeyHexFromPriv(String privateKey) {
        ECKey k = ECKey.fromPrivate(new BigInteger(privateKey, 16));
        return k.getPublicKeyAsHex();
    }

    public static String generateAddressFromPub(String prefix, String pubKey) {
        try {
            String addr = createNewAddressSecp256k1(prefix, Hex.decode(pubKey));
            return addr;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String generateAddressFromPriv(String prefix, String privateKey) {
        String pub = generatePubKeyHexFromPriv(privateKey);
        return generateAddressFromPub(prefix, pub);
    }


    public static String getDpAddress(BaseChain chain, String pubHex) {
        String result       = null;
        MessageDigest digest = Sha256.getSha256Digest();
        byte[] hash = digest.digest(WUtil.HexStringToByteArray(pubHex));

        RIPEMD160Digest digest2 = new RIPEMD160Digest();
        digest2.update(hash, 0, hash.length);

        byte[] hash3 = new byte[digest2.getDigestSize()];
        digest2.doFinal(hash3, 0);

        try {
            byte[] converted = convertBits(hash3, 8,5,true);
            if (chain.equals(COSMOS_MAIN) || chain.equals(COSMOS_TEST)) {
                result = bech32Encode("cosmos".getBytes(), converted);
            } else if (chain.equals(IRIS_MAIN) || chain.equals(IRIS_TEST)){
                result = bech32Encode("iaa".getBytes(), converted);
            } else if (chain.equals(BNB_MAIN)){
                result = bech32Encode("bnb".getBytes(), converted);
            } else if (chain.equals(KAVA_MAIN)){
                result = bech32Encode("kava".getBytes(), converted);
            } else if (chain.equals(BAND_MAIN)){
                result = bech32Encode("band".getBytes(), converted);
            } else if (chain.equals(IOV_MAIN)){
                result = bech32Encode("star".getBytes(), converted);
            } else if (chain.equals(CERTIK_MAIN)){
                result = bech32Encode("certik".getBytes(), converted);
            } else if (chain.equals(SECRET_MAIN)){
                result = bech32Encode("secret".getBytes(), converted);
            } else if (chain.equals(AKASH_MAIN)){
                result = bech32Encode("akash".getBytes(), converted);
            } else if (chain.equals(PERSIS_MAIN)){
                result = bech32Encode("persistence".getBytes(), converted);
            } else if (chain.equals(SENTINEL_MAIN)){
                result = bech32Encode("sent".getBytes(), converted);
            } else if (chain.equals(FETCHAI_MAIN)){
                result = bech32Encode("fetch".getBytes(), converted);
            } else if (chain.equals(CRYPTO_MAIN)){
                result = bech32Encode("cro".getBytes(), converted);
            } else if (chain.equals(SIF_MAIN)){
                result = bech32Encode("sif".getBytes(), converted);
            } else if (chain.equals(KI_MAIN)){
                result = bech32Encode("ki".getBytes(), converted);
            } else if (chain.equals(OSMOSIS_MAIN)){
                result = bech32Encode("osmo".getBytes(), converted);
            } else if (chain.equals(MEDI_MAIN)){
                result = bech32Encode("panacea".getBytes(), converted);
            } else if (chain.equals(EMONEY_MAIN)){
                result = bech32Encode("emoney".getBytes(), converted);
            } else if (chain.equals(RIZON_MAIN)){
                result = bech32Encode("rizon".getBytes(), converted);
            } else if (chain.equals(JUNO_MAIN)){
                result = bech32Encode("juno".getBytes(), converted);
            } else if (chain.equals(REGEN_MAIN)){
                result = bech32Encode("regen".getBytes(), converted);
            } else if (chain.equals(BITCANNA_MAIN)){
                result = bech32Encode("bcna".getBytes(), converted);
            } else if (chain.equals(ALTHEA_MAIN)){
                result = bech32Encode("althea".getBytes(), converted);
            } else if (chain.equals(STARGAZE_MAIN)){
                result = bech32Encode("stars".getBytes(), converted);
            } else if (chain.equals(GRABRIDGE_MAIN)){
                result = bech32Encode("gravity".getBytes(), converted);
            } else if (chain.equals(COMDEX_MAIN)){
                result = bech32Encode("comdex".getBytes(), converted);
            } else if (chain.equals(INJ_MAIN)){
                result = bech32Encode("inj".getBytes(), converted);
            } else if (chain.equals(BITSONG_MAIN)){
                result = bech32Encode("bitsong".getBytes(), converted);
            } else if (chain.equals(DESMOS_MAIN)){
                result = bech32Encode("desmos".getBytes(), converted);
            } else if (chain.equals(LUM_MAIN)){
                result = bech32Encode("lum".getBytes(), converted);
            } else if (chain.equals(CHIHUAHUA_MAIN)){
                result = bech32Encode("chihuahua".getBytes(), converted);
            } else if (chain.equals(UMEE_MAIN)){
                result = bech32Encode("umee".getBytes(), converted);
            } else if (chain.equals(AXELAR_MAIN)){
                result = bech32Encode("axelar".getBytes(), converted);
            } else if (chain.equals(KONSTELL_MAIN)){
                result = bech32Encode("darc".getBytes(), converted);
            } else if (chain.equals(EVMOS_MAIN)){
                result = bech32Encode("evmos".getBytes(), converted);
            } else if (chain.equals(CUDOS_MAIN)){
                result = bech32Encode("cudos".getBytes(), converted);
            } else if (chain.equals(PROVENANCE_MAIN)){
                result = bech32Encode("pb".getBytes(), converted);
            } else if (chain.equals(CERBERUS_MAIN)){
                result = bech32Encode("cerberus".getBytes(), converted);
            } else if (chain.equals(OMNIFLIX_MAIN)){
                result = bech32Encode("omniflix".getBytes(), converted);
            } else if (chain.equals(CRESCENT_MAIN) || chain.equals(CRESCENT_TEST)){
                result = bech32Encode("cre".getBytes(), converted);
            } else if (chain.equals(ASSETMANTLE_MAIN)){
                result = bech32Encode("mantle".getBytes(), converted);
            } else if (chain.equals(STATION_TEST)){
                result = bech32Encode("station".getBytes(), converted);
            }

        } catch (Exception e) {
            WLog.w("Secp256k1 genDPAddress Error");
        }
        return result;
    }

    public static String getUpgradeOKAddress(String oldAddress) {
        return bech32Encode("ex".getBytes(), bech32Decode(oldAddress).data);
    }

    public static String convertDpOpAddressToDpAddress(String dpOpAddress, BaseChain chain) {
        if (chain.equals(COSMOS_MAIN) || chain.equals(COSMOS_TEST)) {
            return bech32Encode("cosmos".getBytes(), bech32Decode(dpOpAddress).data);
        } else if (chain.equals(IRIS_MAIN) || chain.equals(IRIS_TEST)) {
            return bech32Encode("iaa".getBytes(), bech32Decode(dpOpAddress).data);
        } else if (chain.equals(KAVA_MAIN)) {
            return bech32Encode("kava".getBytes(), bech32Decode(dpOpAddress).data);
        } else if (chain.equals(BAND_MAIN)) {
            return bech32Encode("band".getBytes(), bech32Decode(dpOpAddress).data);
        } else if (chain.equals(IOV_MAIN)) {
            return bech32Encode("star".getBytes(), bech32Decode(dpOpAddress).data);
        } else if (chain.equals(CERTIK_MAIN)) {
            return bech32Encode("certik".getBytes(), bech32Decode(dpOpAddress).data);
        } else if (chain.equals(SECRET_MAIN)) {
            return bech32Encode("secret".getBytes(), bech32Decode(dpOpAddress).data);
        } else if (chain.equals(AKASH_MAIN)) {
            return bech32Encode("akash".getBytes(), bech32Decode(dpOpAddress).data);
        } else if (chain.equals(PERSIS_MAIN)) {
            return bech32Encode("persistence".getBytes(), bech32Decode(dpOpAddress).data);
        } else if (chain.equals(SENTINEL_MAIN)) {
            return bech32Encode("sent".getBytes(), bech32Decode(dpOpAddress).data);
        } else if (chain.equals(FETCHAI_MAIN)) {
            return bech32Encode("fetch".getBytes(), bech32Decode(dpOpAddress).data);
        } else if (chain.equals(CRYPTO_MAIN)) {
            return bech32Encode("cro".getBytes(), bech32Decode(dpOpAddress).data);
        } else if (chain.equals(SIF_MAIN)) {
            return bech32Encode("sif".getBytes(), bech32Decode(dpOpAddress).data);
        } else if (chain.equals(KI_MAIN)) {
            return bech32Encode("ki".getBytes(), bech32Decode(dpOpAddress).data);
        } else if (chain.equals(OSMOSIS_MAIN)) {
            return bech32Encode("osmo".getBytes(), bech32Decode(dpOpAddress).data);
        } else if (chain.equals(MEDI_MAIN)) {
            return bech32Encode("panacea".getBytes(), bech32Decode(dpOpAddress).data);
        } else if (chain.equals(EMONEY_MAIN)) {
            return bech32Encode("emoney".getBytes(), bech32Decode(dpOpAddress).data);
        } else if (chain.equals(RIZON_MAIN)) {
            return bech32Encode("rizon".getBytes(), bech32Decode(dpOpAddress).data);
        } else if (chain.equals(JUNO_MAIN)) {
            return bech32Encode("juno".getBytes(), bech32Decode(dpOpAddress).data);
        } else if (chain.equals(REGEN_MAIN)) {
            return bech32Encode("regen".getBytes(), bech32Decode(dpOpAddress).data);
        } else if (chain.equals(BITCANNA_MAIN)) {
            return bech32Encode("bcna".getBytes(), bech32Decode(dpOpAddress).data);
        } else if (chain.equals(ALTHEA_MAIN)) {
            return bech32Encode("althea".getBytes(), bech32Decode(dpOpAddress).data);
        } else if (chain.equals(STARGAZE_MAIN)) {
            return bech32Encode("stars".getBytes(), bech32Decode(dpOpAddress).data);
        } else if (chain.equals(GRABRIDGE_MAIN)) {
            return bech32Encode("gravity".getBytes(), bech32Decode(dpOpAddress).data);
        } else if (chain.equals(COMDEX_MAIN)) {
            return bech32Encode("comdex".getBytes(), bech32Decode(dpOpAddress).data);
        } else if (chain.equals(INJ_MAIN)) {
            return bech32Encode("inj".getBytes(), bech32Decode(dpOpAddress).data);
        } else if (chain.equals(BITSONG_MAIN)) {
            return bech32Encode("bitsong".getBytes(), bech32Decode(dpOpAddress).data);
        } else if (chain.equals(DESMOS_MAIN)) {
            return bech32Encode("desmos".getBytes(), bech32Decode(dpOpAddress).data);
        } else if (chain.equals(LUM_MAIN)) {
            return bech32Encode("lum".getBytes(), bech32Decode(dpOpAddress).data);
        } else if (chain.equals(CHIHUAHUA_MAIN)) {
            return bech32Encode("chihuahua".getBytes(), bech32Decode(dpOpAddress).data);
        } else if (chain.equals(UMEE_MAIN)) {
            return bech32Encode("umee".getBytes(), bech32Decode(dpOpAddress).data);
        } else if (chain.equals(AXELAR_MAIN)) {
            return bech32Encode("axelar".getBytes(), bech32Decode(dpOpAddress).data);
        } else if (chain.equals(KONSTELL_MAIN)) {
            return bech32Encode("darc".getBytes(), bech32Decode(dpOpAddress).data);
        } else if (chain.equals(EVMOS_MAIN)) {
            return bech32Encode("evmos".getBytes(), bech32Decode(dpOpAddress).data);
        } else if (chain.equals(CUDOS_MAIN)) {
            return bech32Encode("cudos".getBytes(), bech32Decode(dpOpAddress).data);
        } else if (chain.equals(PROVENANCE_MAIN)) {
            return bech32Encode("pb".getBytes(), bech32Decode(dpOpAddress).data);
        } else if (chain.equals(CERBERUS_MAIN)) {
            return bech32Encode("cerberus".getBytes(), bech32Decode(dpOpAddress).data);
        } else if (chain.equals(OMNIFLIX_MAIN)) {
            return bech32Encode("omniflix".getBytes(), bech32Decode(dpOpAddress).data);
        } else if (chain.equals(CRESCENT_MAIN) || chain.equals(CRESCENT_TEST)) {
            return bech32Encode("cre".getBytes(), bech32Decode(dpOpAddress).data);
        } else if (chain.equals(ASSETMANTLE_MAIN)) {
            return bech32Encode("mantle".getBytes(), bech32Decode(dpOpAddress).data);
        } else if (chain.equals(STATION_TEST)) {
            return bech32Encode("station".getBytes(), bech32Decode(dpOpAddress).data);
        }

        else {
            return "";
        }
    }

    public static String getCreateDpAddressFromEntropy(BaseChain chain, String entropy, int path, int customPath) {
        DeterministicKey childKey = getCreateKeyWithPathfromEntropy(chain, entropy, path, customPath);
        if (chain.equals(OKEX_MAIN) && customPath == 1 || chain.equals(OKEX_MAIN) && customPath == 2) {
            return generateEthAddressFromPrivateKey(childKey.getPrivateKeyAsHex());
        } else if (chain.equals(OKEX_MAIN) && customPath == 0) {
            return generateTenderAddressFromPrivateKey(childKey.getPrivateKeyAsHex());
        } else if (chain.equals(INJ_MAIN)) {
            return generateAddressFromPriv("inj", childKey.getPrivateKeyAsHex());
        } else if (chain.equals(EVMOS_MAIN)) {
            return generateAddressFromPriv("evmos", childKey.getPrivateKeyAsHex());
        }
        return getDpAddress(chain, childKey.getPublicKeyAsHex());
    }

    public static byte[] convertBits(byte[] data, int frombits, int tobits, boolean pad) throws Exception {
        int acc = 0;
        int bits = 0;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int maxv = (1 << tobits) - 1;
        for (int i = 0; i < data.length; i++) {
            int value = data[i] & 0xff;
            if ((value >>> frombits) != 0) {
                throw new Exception("invalid data range: data[" + i + "]=" + value + " (frombits=" + frombits + ")");
            }
            acc = (acc << frombits) | value;
            bits += frombits;
            while (bits >= tobits) {
                bits -= tobits;
                baos.write((acc >>> bits) & maxv);
            }
        }
        if (pad) {
            if (bits > 0) {
                baos.write((acc << (tobits - bits)) & maxv);
            }
        } else if (bits >= frombits) {
            throw new Exception("illegal zero padding");
        } else if (((acc << (tobits - bits)) & maxv) != 0) {
            throw new Exception("non-zero padding");
        }
        return baos.toByteArray();
    }

    public static String bech32Encode(byte[] hrp, byte[] data) {
        byte[] chk = createChecksum(hrp, data);
        byte[] combined = new byte[chk.length + data.length];

        System.arraycopy(data, 0, combined, 0, data.length);
        System.arraycopy(chk, 0, combined, data.length, chk.length);

        byte[] xlat = new byte[combined.length];
        for(int i = 0; i < combined.length; i++)   {
            xlat[i] = (byte)CHARSET.charAt(combined[i]);
        }

        byte[] ret = new byte[hrp.length + xlat.length + 1];
        System.arraycopy(hrp, 0, ret, 0, hrp.length);
        System.arraycopy(new byte[] { 0x31 }, 0, ret, hrp.length, 1);
        System.arraycopy(xlat, 0, ret, hrp.length + 1, xlat.length);

        return new String(ret);
    }

    public static HrpAndData bech32Decode(String bech) {

        if (!bech.equals(bech.toLowerCase()) && !bech.equals(bech.toUpperCase()))  {
            throw new IllegalArgumentException("bech32 cannot mix upper and lower case");
        }

        byte[] buffer = bech.getBytes();
        for (byte b : buffer) {
            if (b < 0x21 || b > 0x7e) throw new IllegalArgumentException("bech32 characters out of range");
        }

        bech = bech.toLowerCase();
        int pos = bech.lastIndexOf("1");
        if (pos < 1) {
            throw new IllegalArgumentException("bech32 missing separator");
        } else if (pos + 7 > bech.length()) {
            throw new IllegalArgumentException("bech32 separator misplaced");
        } else if (bech.length() < 8) {
            throw new IllegalArgumentException("bech32 input too short");
        } else if (bech.length() > 90) {
            throw new IllegalArgumentException("bech32 input too long");
        }

        String s = bech.substring(pos + 1);
        for (int i = 0; i < s.length(); i++) {
            if (CHARSET.indexOf(s.charAt(i)) == -1) {
                throw new IllegalArgumentException("bech32 characters  out of range");
            }
        }

        byte[] hrp = bech.substring(0, pos).getBytes();

        byte[] data = new byte[bech.length() - pos - 1];
        for (int j = 0, i = pos + 1; i < bech.length(); i++, j++) {
            data[j] = (byte)CHARSET.indexOf(bech.charAt(i));
        }

        if (!verifyChecksum(hrp, data)) {
            throw new IllegalArgumentException("invalid bech32 checksum");
        }

        byte[] ret = new byte[data.length - 6];
        System.arraycopy(data, 0, ret, 0, data.length - 6);

        return new HrpAndData(hrp, ret);
    }

    private static boolean verifyChecksum(byte[] hrp, byte[] data) {
        byte[] exp = hrpExpand(hrp);

        byte[] values = new byte[exp.length + data.length];
        System.arraycopy(exp, 0, values, 0, exp.length);
        System.arraycopy(data, 0, values, exp.length, data.length);

        return (1 == polymod(values));
    }

    private static byte[] createChecksum(byte[] hrp, byte[] data)  {
        byte[] zeroes = new byte[] { 0x00, 0x00, 0x00, 0x00, 0x00, 0x00 };
        byte[] expanded = hrpExpand(hrp);
        byte[] values = new byte[zeroes.length + expanded.length + data.length];

        System.arraycopy(expanded, 0, values, 0, expanded.length);
        System.arraycopy(data, 0, values, expanded.length, data.length);
        System.arraycopy(zeroes, 0, values, expanded.length + data.length, zeroes.length);

        int polymod = polymod(values) ^ 1;
        byte[] ret = new byte[6];
        for(int i = 0; i < ret.length; i++)   {
            ret[i] = (byte)((polymod >> 5 * (5 - i)) & 0x1f);
        }

        return ret;
    }

    private static int polymod(byte[] values)  {
        final int[] GENERATORS = { 0x3b6a57b2, 0x26508e6d, 0x1ea119fa, 0x3d4233dd, 0x2a1462b3 };

        int chk = 1;

        for(byte b : values)   {
            byte top = (byte)(chk >> 0x19);
            chk = b ^ ((chk & 0x1ffffff) << 5);
            for(int i = 0; i < 5; i++)   {
                chk ^= ((top >> i) & 1) == 1 ? GENERATORS[i] : 0;
            }
        }

        return chk;
    }

    private static byte[] hrpExpand(byte[] hrp) {
        byte[] buf1 = new byte[hrp.length];
        byte[] buf2 = new byte[hrp.length];
        byte[] mid = new byte[1];

        for (int i = 0; i < hrp.length; i++)   {
            buf1[i] = (byte)(hrp[i] >> 5);
        }
        mid[0] = 0x00;
        for (int i = 0; i < hrp.length; i++)   {
            buf2[i] = (byte)(hrp[i] & 0x1f);
        }

        byte[] ret = new byte[(hrp.length * 2) + 1];
        System.arraycopy(buf1, 0, ret, 0, buf1.length);
        System.arraycopy(mid, 0, ret, buf1.length, mid.length);
        System.arraycopy(buf2, 0, ret, buf1.length + mid.length, buf2.length);

        return ret;
    }


    public static class HrpAndData {

        public byte[] hrp;
        public byte[] data;

        public HrpAndData(byte[] hrp, byte[] data) { this.hrp = hrp; this.data = data; }
        public byte[] getHrp() { return this.hrp; }
        public byte[] getData() { return this.data; }

        @Override
        public String toString() {
            return "HrpAndData [hrp=" + Arrays.toString(hrp) + ", data=" + Arrays.toString(data) + "]";
        }
        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + Arrays.hashCode(data);
            result = prime * result + Arrays.hashCode(hrp);
            return result;
        }
        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            HrpAndData other = (HrpAndData) obj;
            if (!Arrays.equals(data, other.data))
                return false;
            if (!Arrays.equals(hrp, other.hrp))
                return false;
            return true;
        }
    }

    public static String getSwapId(byte[] randomNumberHash, String sender, String otherchainSender) throws Exception{
        byte[] s = convertBits(bech32Decode(sender).data, 5, 8, false);
        byte[] rhs = new byte[randomNumberHash.length + s.length];
        System.arraycopy(randomNumberHash, 0, rhs, 0, randomNumberHash.length);
        System.arraycopy(s, 0, rhs, randomNumberHash.length, s.length);

        byte[] o = otherchainSender.toLowerCase().getBytes(Charset.forName("UTF-8"));
        byte[] expectedSwapId = new byte[rhs.length + o.length];
        System.arraycopy(rhs, 0, expectedSwapId, 0, rhs.length);
        System.arraycopy(o, 0, expectedSwapId, rhs.length, o.length);

        WLog.w("expectedSwapId "+ WUtil.ByteArrayToHexString(expectedSwapId));

        byte[] expectedSwapIdSha = Sha256.getSha256Digest().digest(expectedSwapId);
        return WUtil.ByteArrayToHexString(expectedSwapIdSha);
    }
}
