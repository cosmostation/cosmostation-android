package wannabit.io.cosmostaion.utils;

import com.google.common.collect.ImmutableList;

import org.bitcoinj.crypto.ChildNumber;
import org.bitcoinj.crypto.DeterministicHierarchy;
import org.bitcoinj.crypto.DeterministicKey;
import org.bitcoinj.crypto.HDKeyDerivation;
import org.bitcoinj.crypto.MnemonicCode;

import java.io.ByteArrayOutputStream;
import java.security.SecureRandom;
import java.security.interfaces.ECKey;
import java.util.ArrayList;
import java.util.List;

public class WKey {

    public static byte[] getEntropy() {
        byte[] seed = new byte[24];
        new SecureRandom().nextBytes(seed);
        return seed;
    }

    public static String getSeedfromWords(ArrayList<String> words) {
        try {
            return WUtil.ByteArrayToHexString(toEntropy(words));

        } catch (Exception e) {
            return null;
        }
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

    public static byte[] getHDSeed(String seed) {
        try {
            byte[] entropy     = WUtil.HexStringToByteArray(seed);
            return MnemonicCode.toSeed(MnemonicCode.INSTANCE.toMnemonic(entropy), "");
        } catch (Exception e) {
            return null;
        }
    }


    public static List<ChildNumber> getParentPath() {
        return  ImmutableList.of(new ChildNumber(44, true), new ChildNumber(118, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO);
    }


    public static DeterministicKey getKeyWithPath(String seed, int path) {
        DeterministicKey masterKey      = HDKeyDerivation.createMasterPrivateKey(WUtil.HexStringToByteArray(seed));
        return new DeterministicHierarchy(masterKey).deriveChild(WKey.getParentPath(), true, true,  new ChildNumber(path));

    }








    final  static String COSMOS_PRE_PUB_KEY = "eb5ae98721";
    final  static String COSMOS_PRE_PRI_KEY = "e1b0f79b20";


    public static String getCosmosUserDpAddress(String pubHex) {
        String result       = null;
        String sumHex       = COSMOS_PRE_PUB_KEY + pubHex;
        byte[] sumHexByte   = WUtil.HexStringToByteArray(sumHex);
        try {
            byte[] converted = convertBits(sumHexByte, 8,5,true);
            result = bech32Encode("cosmos".getBytes(), converted);
        } catch (Exception e) {
            WLog.w("getCosmosUserDpAddress Error");
            return result;

        }
        return result;
    }

    public static String getCosmosUserDpPubKey(String pubHex) {
        String result       = null;
        String sumHex       = COSMOS_PRE_PUB_KEY + pubHex;
        byte[] sumHexByte   = WUtil.HexStringToByteArray(sumHex);
        try {
            byte[] converted = convertBits(sumHexByte, 8,5,true);
            result = bech32Encode("cosmospub".getBytes(), converted);
        } catch (Exception e) {
            WLog.w("getCosmosUserDpPubKey Error");
            return result;

        }
        return result;
    }




    private static final String CHARSET = "qpzry9x8gf2tvdw0s3jn54khce6mua7l";

    private static byte[] convertBits(byte[] data, int frombits, int tobits, boolean pad) throws Exception {
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
}
