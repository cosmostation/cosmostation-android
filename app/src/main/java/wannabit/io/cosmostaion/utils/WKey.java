package wannabit.io.cosmostaion.utils;

import static org.bitcoinj.core.ECKey.CURVE;
import static wannabit.io.cosmostaion.base.BaseChain.FETCHAI_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.KAVA_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.OKEX_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.XPLA_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.getChain;

import android.util.Base64;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
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

import cosmos.auth.v1beta1.Auth;
import cosmos.auth.v1beta1.QueryGrpc;
import cosmos.auth.v1beta1.QueryOuterClass;
import cosmos.vesting.v1beta1.Vesting;
import wannabit.io.cosmostaion.BuildConfig;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.chains.ChainConfig;
import wannabit.io.cosmostaion.base.chains.ChainFactory;
import wannabit.io.cosmostaion.crypto.CryptoHelper;
import wannabit.io.cosmostaion.crypto.Sha256;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.network.ChannelBuilder;

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
            if (BuildConfig.DEBUG)
                e.printStackTrace();

        }
        return result;
    }

    public static byte[] toEntropy(ArrayList<String> words) {
        try {
            return new MnemonicCode().toEntropy(words);
        } catch (Exception e) {
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

    public static boolean isValidStringPrivateKey(String input) {
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
        if (chain != null) {
            ChainConfig chainConfig = ChainFactory.getChain(chain);
            return chainConfig.setParentPath(customPath);
        }
        return null;
    }

    public static List<ChildNumber> getFetchParentPath2() {
        return ImmutableList.of(ChildNumber.ZERO);
    }


    //singer
    public static DeterministicKey getKeyWithPathfromEntropy(Account account, String entropy) {
        BaseChain chain = getChain(account.baseChain);
        if (!chain.equals(FETCHAI_MAIN)) {
            DeterministicKey masterKey = HDKeyDerivation.createMasterPrivateKey(getHDSeed(WUtil.HexStringToByteArray(entropy)));
            return new DeterministicHierarchy(masterKey).deriveChild(WKey.getParentPath(chain, account.customPath), true, true, new ChildNumber(Integer.parseInt(account.path)));
        } else {
            DeterministicKey masterKey = HDKeyDerivation.createMasterPrivateKey(getHDSeed(WUtil.HexStringToByteArray(entropy)));
            if (account.customPath != 2) {
                DeterministicKey targetKey = new DeterministicHierarchy(masterKey).deriveChild(WKey.getParentPath(chain, account.customPath), true, true, new ChildNumber(Integer.parseInt(account.path)));
                return targetKey;
            } else {
                DeterministicKey targetKey = new DeterministicHierarchy(masterKey).deriveChild(WKey.getParentPath(chain, account.customPath), true, true, new ChildNumber(Integer.parseInt(account.path), true));
                DeterministicKey targetKey2 = new DeterministicHierarchy(targetKey).deriveChild(WKey.getFetchParentPath2(), true, true, ChildNumber.ZERO);
                return targetKey2;
            }
        }
    }

    // create, restore
    public static DeterministicKey getCreateKeyWithPathfromEntropy(BaseChain chain, String entropy, int path, int customPath) {
        if (!chain.equals(FETCHAI_MAIN)) {
            DeterministicKey masterKey = HDKeyDerivation.createMasterPrivateKey(getHDSeed(WUtil.HexStringToByteArray(entropy)));
            return new DeterministicHierarchy(masterKey).deriveChild(WKey.getParentPath(chain, customPath), true, true, new ChildNumber(path));
        } else {
            DeterministicKey masterKey = HDKeyDerivation.createMasterPrivateKey(getHDSeed(WUtil.HexStringToByteArray(entropy)));
            if (customPath != 2) {
                DeterministicKey targetKey = new DeterministicHierarchy(masterKey).deriveChild(WKey.getParentPath(chain, customPath), true, true, new ChildNumber(path));
                return targetKey;
            } else {
                DeterministicKey targetKey = new DeterministicHierarchy(masterKey).deriveChild(WKey.getParentPath(chain, customPath), true, true, new ChildNumber(path, true));
                DeterministicKey targetKey2 = new DeterministicHierarchy(targetKey).deriveChild(WKey.getFetchParentPath2(), true, true, ChildNumber.ZERO);
                return targetKey2;
            }
        }
    }

    public static boolean isMnemonicWords(ArrayList<String> words) {
        boolean result = true;
        List<String> mnemonics = MnemonicCode.INSTANCE.getWordList();
        for (String insert : words) {
            if (!mnemonics.contains(insert)) {
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
        } catch (Exception e) {
        }
        return result;
    }


    public static String getPubKeyValue(ECKey key) {
        String result = "";
        try {
            byte[] data = key.getPubKey();
            result = Base64.encodeToString(data, Base64.DEFAULT).replace("\n", "");
            WLog.w("base64 : " + result);

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
            try {
                Any authAccount = auth.getAccount();
                Auth.BaseAccount baseAccount = null;
                if (authAccount.getTypeUrl().contains(Auth.BaseAccount.getDescriptor().getFullName())) {
                    baseAccount = Auth.BaseAccount.parseFrom(authAccount.getValue());

                } else if (authAccount.getTypeUrl().contains(Vesting.PeriodicVestingAccount.getDescriptor().getFullName())) {
                    baseAccount = Vesting.PeriodicVestingAccount.parseFrom(authAccount.getValue()).getBaseVestingAccount().getBaseAccount();

                } else if (authAccount.getTypeUrl().contains(Vesting.ContinuousVestingAccount.getDescriptor().getFullName())) {
                    baseAccount = Vesting.ContinuousVestingAccount.parseFrom(authAccount.getValue()).getBaseVestingAccount().getBaseAccount();

                } else if (authAccount.getTypeUrl().contains(Vesting.DelayedVestingAccount.getDescriptor().getFullName())) {
                    baseAccount = Vesting.DelayedVestingAccount.parseFrom(authAccount.getValue()).getBaseVestingAccount().getBaseAccount();
                }

                if (baseAccount.getPubKey().getTypeUrl().contains("/ethermint.crypto.v1.ethsecp256k1.PubKey")) {
                    ethermint.crypto.v1.ethsecp256k1.Keys.PubKey pubKey = ethermint.crypto.v1.ethsecp256k1.Keys.PubKey.newBuilder().setKey(ByteString.copyFrom(ecKey.getPubKey())).build();
                    return Any.newBuilder().setTypeUrl("/ethermint.crypto.v1.ethsecp256k1.PubKey").setValue(pubKey.toByteString()).build();
                } else {
                    cosmos.crypto.secp256k1.Keys.PubKey pubKey = cosmos.crypto.secp256k1.Keys.PubKey.newBuilder().setKey(ByteString.copyFrom(ecKey.getPubKey())).build();
                    return Any.newBuilder().setTypeUrl("/cosmos.crypto.secp256k1.PubKey").setValue(pubKey.toByteString()).build();
                }

            } catch (InvalidProtocolBufferException e) { e.printStackTrace(); }
        }
        return null;
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

    public static String convertAddressToEth(String exAddress) throws Exception {
        byte[] pub = convertBits(bech32Decode(exAddress).data, 5, 8, false);
        return "0x" + WUtil.ByteArrayToHexString(pub);
    }

    public static String convertAddressEthToTender(BaseChain baseChain, String esAddress) {
        String cosmoTypeAddress = esAddress;
        if (cosmoTypeAddress.startsWith("0x")) {
            cosmoTypeAddress = cosmoTypeAddress.replace("0x", "");
        }
        byte[] pub = WUtil.HexStringToByteArray(cosmoTypeAddress);
        String addressResult = null;
        try {
            byte[] bytes = convertBits(pub, 8, 5, true);
            if (baseChain.equals(KAVA_MAIN)) {
                addressResult = Bech32.encode("kava", bytes);
            } else {
                addressResult = Bech32.encode("evmos", bytes);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return addressResult;
    }

    public static String convertAddressEthToOkex(String esAddress) {
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
        String result = null;
        MessageDigest digest = Sha256.getSha256Digest();
        byte[] hash = digest.digest(WUtil.HexStringToByteArray(pubHex));

        RIPEMD160Digest digest2 = new RIPEMD160Digest();
        digest2.update(hash, 0, hash.length);

        byte[] hash3 = new byte[digest2.getDigestSize()];
        digest2.doFinal(hash3, 0);

        try {
            byte[] converted = convertBits(hash3, 8, 5, true);
            if (chain != null) {
                ChainConfig chainConfig = ChainFactory.getChain(chain);
                result = bech32Encode(chainConfig.addressPrefix().getBytes(), converted);
            }
        } catch (Exception e) {
            WLog.w("Secp256k1 genDPAddress Error");
        }
        return result;
    }

    public static String getUpgradeOKAddress(String oldAddress) {
        return bech32Encode("ex".getBytes(), bech32Decode(oldAddress).data);
    }

    public static String convertDpOpAddressToDpAddress(String dpOpAddress, ChainConfig chainConfig) {
        if (chainConfig != null) {
            return bech32Encode(chainConfig.addressPrefix().getBytes(), bech32Decode(dpOpAddress).data);
        }
        return "";
    }

    public static String convertDpAddressToDpOpAddress(String dpOpAddress, ChainConfig chainConfig) {
        if (chainConfig != null) {
            return bech32Encode(chainConfig.validatorPrefix().getBytes(), bech32Decode(dpOpAddress).data);
        }
        return "";
    }

    public static String getCreateDpAddressFromEntropy(BaseChain chain, String entropy, int path, int customPath) {
        DeterministicKey childKey = getCreateKeyWithPathfromEntropy(chain, entropy, path, customPath);
        ChainConfig chainConfig = ChainFactory.getChain(chain);
        if (chain.equals(OKEX_MAIN)) {
            if (customPath == 0) {
                return generateTenderAddressFromPrivateKey(childKey.getPrivateKeyAsHex());
            } else {
                return generateEthAddressFromPrivateKey(childKey.getPrivateKeyAsHex());
            }
        } else if (chainConfig.ethAccountType()) {
            if (chain.equals(XPLA_MAIN)) {
                if (customPath == 0) {
                    return getDpAddress(chain, generatePubKeyHexFromPriv(childKey.getPrivateKeyAsHex()));
                } else {
                    return generateAddressFromPriv(chainConfig.addressPrefix(), childKey.getPrivateKeyAsHex());
                }
            } else {
                return generateAddressFromPriv(chainConfig.addressPrefix(), childKey.getPrivateKeyAsHex());
            }
        } else {
            return getDpAddress(chain, childKey.getPublicKeyAsHex());
        }
    }

    public static String getCreateDpAddressFromPkey(BaseChain chain, String pKey, int customPath) {
        ChainConfig chainConfig = ChainFactory.getChain(chain);
        if (chain.equals(OKEX_MAIN)) {
            if (customPath == 0) {
                return generateTenderAddressFromPrivateKey(pKey);
            } else {
                return generateEthAddressFromPrivateKey(pKey);
            }
        } else if (chainConfig.ethAccountType()) {
            if (chain.equals(XPLA_MAIN)) {
                if (customPath == 0) {
                    return getDpAddress(chain, generatePubKeyHexFromPriv(pKey));
                } else {
                    return generateAddressFromPriv(chainConfig.addressPrefix(), pKey);
                }
            } else {
                return generateAddressFromPriv(chainConfig.addressPrefix(), pKey);
            }
        } else {
            return getDpAddress(chain, generatePubKeyHexFromPriv(pKey));
        }
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
        for (int i = 0; i < combined.length; i++) {
            xlat[i] = (byte) CHARSET.charAt(combined[i]);
        }

        byte[] ret = new byte[hrp.length + xlat.length + 1];
        System.arraycopy(hrp, 0, ret, 0, hrp.length);
        System.arraycopy(new byte[]{0x31}, 0, ret, hrp.length, 1);
        System.arraycopy(xlat, 0, ret, hrp.length + 1, xlat.length);

        return new String(ret);
    }

    public static HrpAndData bech32Decode(String bech) {

        if (!bech.equals(bech.toLowerCase()) && !bech.equals(bech.toUpperCase())) {
            throw new IllegalArgumentException("bech32 cannot mix upper and lower case");
        }

        byte[] buffer = bech.getBytes();
        for (byte b : buffer) {
            if (b < 0x21 || b > 0x7e)
                throw new IllegalArgumentException("bech32 characters out of range");
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
            data[j] = (byte) CHARSET.indexOf(bech.charAt(i));
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

    private static byte[] createChecksum(byte[] hrp, byte[] data) {
        byte[] zeroes = new byte[]{0x00, 0x00, 0x00, 0x00, 0x00, 0x00};
        byte[] expanded = hrpExpand(hrp);
        byte[] values = new byte[zeroes.length + expanded.length + data.length];

        System.arraycopy(expanded, 0, values, 0, expanded.length);
        System.arraycopy(data, 0, values, expanded.length, data.length);
        System.arraycopy(zeroes, 0, values, expanded.length + data.length, zeroes.length);

        int polymod = polymod(values) ^ 1;
        byte[] ret = new byte[6];
        for (int i = 0; i < ret.length; i++) {
            ret[i] = (byte) ((polymod >> 5 * (5 - i)) & 0x1f);
        }

        return ret;
    }

    private static int polymod(byte[] values) {
        final int[] GENERATORS = {0x3b6a57b2, 0x26508e6d, 0x1ea119fa, 0x3d4233dd, 0x2a1462b3};

        int chk = 1;

        for (byte b : values) {
            byte top = (byte) (chk >> 0x19);
            chk = b ^ ((chk & 0x1ffffff) << 5);
            for (int i = 0; i < 5; i++) {
                chk ^= ((top >> i) & 1) == 1 ? GENERATORS[i] : 0;
            }
        }

        return chk;
    }

    private static byte[] hrpExpand(byte[] hrp) {
        byte[] buf1 = new byte[hrp.length];
        byte[] buf2 = new byte[hrp.length];
        byte[] mid = new byte[1];

        for (int i = 0; i < hrp.length; i++) {
            buf1[i] = (byte) (hrp[i] >> 5);
        }
        mid[0] = 0x00;
        for (int i = 0; i < hrp.length; i++) {
            buf2[i] = (byte) (hrp[i] & 0x1f);
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

        public HrpAndData(byte[] hrp, byte[] data) {
            this.hrp = hrp;
            this.data = data;
        }

        public byte[] getHrp() {
            return this.hrp;
        }

        public byte[] getData() {
            return this.data;
        }

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

    public static String getSwapId(byte[] randomNumberHash, String sender, String otherchainSender) throws Exception {
        byte[] s = convertBits(bech32Decode(sender).data, 5, 8, false);
        byte[] rhs = new byte[randomNumberHash.length + s.length];
        System.arraycopy(randomNumberHash, 0, rhs, 0, randomNumberHash.length);
        System.arraycopy(s, 0, rhs, randomNumberHash.length, s.length);

        byte[] o = otherchainSender.toLowerCase().getBytes(Charset.forName("UTF-8"));
        byte[] expectedSwapId = new byte[rhs.length + o.length];
        System.arraycopy(rhs, 0, expectedSwapId, 0, rhs.length);
        System.arraycopy(o, 0, expectedSwapId, rhs.length, o.length);

        WLog.w("expectedSwapId " + WUtil.ByteArrayToHexString(expectedSwapId));

        byte[] expectedSwapIdSha = Sha256.getSha256Digest().digest(expectedSwapId);
        return WUtil.ByteArrayToHexString(expectedSwapIdSha);
    }

    public static ECKey getECKey(BaseApplication app, Account account) {
        if (account.fromMnemonic) {
            String entropy = CryptoHelper.doDecryptData(app.getString(R.string.key_mnemonic) + account.uuid, account.resource, account.spec);
            DeterministicKey deterministicKey = WKey.getKeyWithPathfromEntropy(account, entropy);
            return ECKey.fromPrivate(new BigInteger(deterministicKey.getPrivateKeyAsHex(), 16));
        } else {
            String privateKey = CryptoHelper.doDecryptData(app.getString(R.string.key_private) + account.uuid, account.resource, account.spec);
            return ECKey.fromPrivate(new BigInteger(privateKey, 16));
        }
    }

    public static QueryOuterClass.QueryAccountResponse onAuthResponse(BaseChain baseChain, Account account) {
        QueryGrpc.QueryBlockingStub authStub = QueryGrpc.newBlockingStub(ChannelBuilder.getChain(baseChain));
        QueryOuterClass.QueryAccountRequest request = QueryOuterClass.QueryAccountRequest.newBuilder().setAddress(account.address).build();
        return authStub.account(request);
    }
}
