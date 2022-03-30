package wannabit.io.cosmostaion.utils.hdac;

import com.google.common.collect.ImmutableList;

import org.apache.commons.lang3.ArrayUtils;
import org.bitcoinj.core.Base58;
import org.bitcoinj.core.ECKey;
import org.bitcoinj.core.UTXO;
import org.bitcoinj.crypto.ChildNumber;
import org.bitcoinj.crypto.DeterministicHierarchy;
import org.bitcoinj.crypto.DeterministicKey;
import org.bitcoinj.crypto.HDKeyDerivation;
import org.bitcoinj.wallet.DeterministicSeed;
import org.bouncycastle.crypto.digests.RIPEMD160Digest;
import org.bouncycastle.crypto.digests.SHA256Digest;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.model.hdac.HdacUtxo;


public class HdacUtil {
    private final static String HDAC_BURN_ADDRESS_TEST = "hXXXXXXXXXXXXXXXXXXXXXXXXXXXYHCpEz";
    private final static String HDAC_BURN_ADDRESS_MAIN = "HJXXXXXXXXXXXXXXXXXXXXXXXXXXVarS5i";

    private final static BigDecimal HDAC_TXFEE = new BigDecimal("10000000");

    protected final static int mAddressHeader_Mainnet = 40;
    protected final static int mAddressHeader_Testnet = 100;
    protected final static String mAddressChecksumValue_Mainnet = "48444143";
    ;
    protected final static String mAddressChecksumValue_Testnet = "48545354";


    private HdacNetworkParams mParams;
    private DeterministicHierarchy mDeterministicHierarchy;
    private DeterministicKey mMasterKey;


    public HdacUtil(List<String> words) {
        try {
            DeterministicSeed dSeed = new DeterministicSeed(words, null, "", 0);
            this.mParams = HdacNetworkParams.getDefault();
            this.mMasterKey = HDKeyDerivation.createMasterPrivateKey(dSeed.getSeedBytes());
            this.mDeterministicHierarchy = new DeterministicHierarchy(mMasterKey);
        } catch (Exception e) {
        }
    }

    public DeterministicKey getKey() {
        List<ChildNumber> parentPath = ImmutableList.of(new ChildNumber(44, true), new ChildNumber(200, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO);
        return mDeterministicHierarchy.deriveChild(parentPath, true, true, new ChildNumber(0));
    }

    public String getAddress(BaseChain baseChain) {
        return convPubkeyToHdacAddress(getKey().getPubKey(), baseChain);
    }

    public BigDecimal getBalance(BaseChain baseChain, ArrayList<HdacUtxo> utxos) {
        BigDecimal result = BigDecimal.ZERO;
        for (HdacUtxo utxo : utxos) {
            if (baseChain.equals(BaseChain.RIZON_TEST)) {
                if (utxo.satoshis > 0 && utxo.confirmations > 0) {
                    result = result.add(new BigDecimal(utxo.amount));
                }
            } else {
                if (utxo.satoshis > 0 && utxo.confirmations >= 8) {
                    result = result.add(new BigDecimal(utxo.amount));
                }
            }
        }
        return result.movePointRight(8);
    }

    public ArrayList<HdacUtxo> getRemainUtxo(BaseChain baseChain, ArrayList<HdacUtxo> utxos) {
        ArrayList<HdacUtxo> result = new ArrayList<>();
        for (HdacUtxo utxo : utxos) {
            if (baseChain.equals(BaseChain.RIZON_TEST)) {
                if (utxo.satoshis > 0 && utxo.confirmations >= 1) {
                    result.add(utxo);
                }
            } else {
                if (utxo.satoshis > 0 && utxo.confirmations >= 1) {
                    result.add(utxo);
                }
            }

        }
        return result;
    }

    public String genRawTxSend(BaseChain baseChain, ArrayList<HdacUtxo> utxos, String toAddress, BigDecimal send_amount) {
        String txHex = null;
        HdacTx hdacTx = new HdacTx();
        BigDecimal balance = getBalance(baseChain, utxos);
        BigDecimal fee = new BigDecimal("0.1").movePointRight(8);
        BigDecimal remain = balance.subtract(send_amount).subtract(fee);

        hdacTx.addOutput(toAddress, send_amount.longValue());
        hdacTx.addOutput(getAddress(baseChain), remain.longValue());
        for (int i = 0; i < utxos.size(); i++) {
            UTXO utxo = utxos.get(i).toUTXO();
            hdacTx.addInput(utxo);
        }
        txHex = hdacTx.getTxBuilder().toHex();
        return txHex;
    }

    public String genSignedTxSend(BaseChain baseChain, ArrayList<HdacUtxo> utxos, String toAddress, BigDecimal send_amount) {
        String txHex = null;
        HdacTx hdacTx = new HdacTx();
        BigDecimal balance = getBalance(baseChain, utxos);
        BigDecimal fee = new BigDecimal("0.1").movePointRight(8);
        BigDecimal remain = balance.subtract(send_amount).subtract(fee);

        hdacTx.addOutput(toAddress, send_amount.longValue());
        hdacTx.addOutput(getAddress(baseChain), remain.longValue());

        for (int i = 0; i < utxos.size(); i++) {
            UTXO utxo = utxos.get(i).toUTXO();
            ECKey ecKey = ECKey.fromPrivate(new BigInteger(getKey().getPrivateKeyAsHex(), 16));
            if (ecKey != null) hdacTx.addSignedInput(utxo, ecKey);
        }
        txHex = hdacTx.getTxBuilder().toHex();
        return txHex;
    }

    public String genRawTxSwap(BaseChain baseChain, ArrayList<HdacUtxo> utxos, String rizonAddress) {
        String txHex = null;
        HdacTx hdacTx = new HdacTx();
        BigDecimal balance = getBalance(baseChain, utxos);
        BigDecimal toBurnAmount = balance.subtract(HDAC_TXFEE);

        if (baseChain.equals(BaseChain.RIZON_TEST)) {
            hdacTx.addOutput(HDAC_BURN_ADDRESS_TEST, toBurnAmount.longValue());
        } else {
            hdacTx.addOutput(HDAC_BURN_ADDRESS_MAIN, toBurnAmount.longValue());
        }
        hdacTx.addOpReturnOutput(rizonAddress.getBytes());

        for (int i = 0; i < utxos.size(); i++) {
            UTXO utxo = utxos.get(i).toUTXO();
            hdacTx.addInput(utxo);
        }
        txHex = hdacTx.getTxBuilder().toHex();
        return txHex;
    }

    public String genSignedTxSwap(BaseChain baseChain, ArrayList<HdacUtxo> utxos, String rizonAddress) {
        String txHex = null;
        HdacTx hdacTx = new HdacTx();
        BigDecimal balance = getBalance(baseChain, utxos);
        BigDecimal toBurnAmount = balance.subtract(HDAC_TXFEE);

        if (baseChain.equals(BaseChain.RIZON_TEST)) {
            hdacTx.addOutput(HDAC_BURN_ADDRESS_TEST, toBurnAmount.longValue());
        } else {
            hdacTx.addOutput(HDAC_BURN_ADDRESS_MAIN, toBurnAmount.longValue());
        }
        hdacTx.addOpReturnOutput(rizonAddress.getBytes());

        for (int i = 0; i < utxos.size(); i++) {
            UTXO utxo = utxos.get(i).toUTXO();
            ECKey ecKey = ECKey.fromPrivate(new BigInteger(getKey().getPrivateKeyAsHex(), 16));
            if (ecKey != null) hdacTx.addSignedInput(utxo, ecKey);
        }
        txHex = hdacTx.getTxBuilder().toHex();
        return txHex;
    }


    public String convPubkeyToHdacAddress(byte[] buf, BaseChain baseChain) {
        byte[] hash = ripemd160(sha256(buf));
        byte[] payload = new byte[hash.length + 1];
//        payload[0] = (byte)mParams.getAddressHeader();
        if (!baseChain.equals(BaseChain.RIZON_TEST)) {
            payload[0] = (byte) mAddressHeader_Mainnet;
        } else {
            payload[0] = (byte) mAddressHeader_Testnet;
        }

        for (int i = 0; i < hash.length; i++) {
            payload[i + 1] = hash[i];
        }

        ByteBuffer payloadHash = ByteBuffer.wrap(sha256(sha256(payload)));

        payloadHash.flip(); //position 0
        payloadHash.limit(4);

        byte[] checksum = new byte[4];
        for (int cs_id = 0; cs_id < checksum.length; cs_id++) {
            checksum[cs_id] = payloadHash.get(cs_id);
        }
        ArrayUtils.reverse(checksum);

//        byte[] hdacChecksum = hexToByte(mParams.getAddressChecksumValue());
        byte[] hdacChecksum = null;
        if (!baseChain.equals(BaseChain.RIZON_TEST)) {
            hdacChecksum = hexToByte(mAddressChecksumValue_Mainnet);
        } else {
            hdacChecksum = hexToByte(mAddressChecksumValue_Testnet);
        }

        ArrayUtils.reverse(hdacChecksum);
        int length = hdacChecksum.length;//Math.max(checksum.length, hdacChecksum.length);
        byte[] checksumBuf = new byte[length];
        for (int i = 0; i < length; ++i) {
            byte xor = (byte) (0xff & ((int) (i < checksum.length ? checksum[i] : 0) ^ (int) (i < hdacChecksum.length ? hdacChecksum[i] : 0)));
            checksumBuf[i] = xor;
        }

        byte[] hdacAddrByte = new byte[payload.length + length];
        int hdacAddrByte_index = 0;
        for (; hdacAddrByte_index < payload.length; hdacAddrByte_index++) {
            hdacAddrByte[hdacAddrByte_index] = payload[hdacAddrByte_index];
        }

        ArrayUtils.reverse(checksumBuf);
        for (int i = 0; i < checksumBuf.length; i++) {
            hdacAddrByte[hdacAddrByte_index + i] = checksumBuf[i];
        }
        return Base58.encode(hdacAddrByte);
    }

    private static byte[] ripemd160(byte[] buf) {
        byte byteData[] = null;
        RIPEMD160Digest digest = new RIPEMD160Digest();
        digest.update(buf, 0, buf.length);
        byteData = new byte[digest.getDigestSize()];
        digest.doFinal(byteData, 0);
        return byteData;
    }

    private static byte[] sha256(byte[] buf) {
        SHA256Digest digest = new SHA256Digest();
        byte[] byteData = new byte[digest.getDigestSize()];
        digest.update(buf, 0, buf.length);
        digest.doFinal(byteData, 0);
        return byteData;
    }

    public static byte[] hexToByte(String hex) {
        return new BigInteger(hex, 16).toByteArray();
    }
}
