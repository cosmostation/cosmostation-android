package wannabit.io.cosmostaion.utils.hdac;

import org.bitcoinj.core.Base58;
import org.bitcoinj.core.Coin;
import org.bitcoinj.core.ECKey;
import org.bitcoinj.core.LegacyAddress;
import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.core.Sha256Hash;
import org.bitcoinj.core.Transaction;
import org.bitcoinj.core.TransactionConfidence;
import org.bitcoinj.core.TransactionInput;
import org.bitcoinj.core.TransactionOutPoint;
import org.bitcoinj.core.UTXO;
import org.bitcoinj.script.Script;
import org.bitcoinj.script.ScriptBuilder;
import org.bitcoinj.script.ScriptOpCodes;
import org.json.JSONObject;

import wannabit.io.cosmostaion.utils.WUtil;

public class HdacTx {
    private final NetworkParameters mParams;
    private final HdacTxBuilder mTxBuilder;

    public HdacTx() {
        this(null);
    }

    public HdacTx(byte[] payload) {
        this.mParams = HdacNetworkParams.getDefault();
        this.mTxBuilder = new HdacTxBuilder(payload);
    }


    public void addInput(UTXO utxo) {
        if (utxo != null) {
            TransactionOutPoint outPoint = new TransactionOutPoint(mParams, utxo.getIndex(), utxo.getHash());
            TransactionInput input = new TransactionInput(mParams, null, utxo.getScript().getProgram(), outPoint, utxo.getValue());
            mTxBuilder.getTransaction().addInput(input);
        }

    }

    public void addSignedInput(JSONObject unspent, ECKey sign) {
        if (unspent != null) {
            Script script;
            try {
                script = new Script(WUtil.hex2Byte(unspent.getString("scriptPubKey")));
                Sha256Hash hash = Sha256Hash.wrap(unspent.getString("txid"));
                long index = unspent.getLong("vout");
                if (script != null && hash != null && index >= 0) {
                    TransactionOutPoint outPoint = new TransactionOutPoint(mParams, index, hash);
                    mTxBuilder.getTransaction().addSignedInput(outPoint, script, sign, Transaction.SigHash.ALL, false);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public void addSignedInput(UTXO utxo, ECKey sign) {
        if (utxo != null) {
            TransactionOutPoint outPoint = new TransactionOutPoint(mParams, utxo.getIndex(), utxo.getHash());
            mTxBuilder.getTransaction().addSignedInput(outPoint, utxo.getScript(), sign, Transaction.SigHash.ALL, false);
        }
    }

    public void addOutput(String address, long amount) {
        if (address != null && !address.isEmpty()) {
            byte[] hash160 = new byte[20];
            byte[] dec = Base58.decode(address);
            if (dec != null && dec.length == 25) {
                System.arraycopy(dec, 1, hash160, 0, 20);
                mTxBuilder.mTransaction.addOutput(Coin.valueOf(amount), new LegacyAddress(mParams, hash160));
            }
        }
    }

    public void addOpReturnOutput(byte[] data) {
        if (data != null && data.length > 0) {
            mTxBuilder.getTransaction().addOutput(Transaction.MIN_NONDUST_OUTPUT, new ScriptBuilder().op(ScriptOpCodes.OP_RETURN).data(data).build());
        }
    }

    public HdacTxBuilder getTxBuilder() {
        return mTxBuilder;
    }

    public class HdacTxBuilder {
        private final Transaction mTransaction;
        private final Transaction.Purpose mPurpose = Transaction.Purpose.USER_PAYMENT;

        public HdacTxBuilder(byte[] payload) {
            if (payload != null) mTransaction = new Transaction(mParams, payload);
            else mTransaction = new Transaction(mParams);
        }

        public Transaction getTransaction() {
            return mTransaction;
        }

        public HdacTxBuilder build() {
            mTransaction.getConfidence().setSource(TransactionConfidence.Source.SELF);
            mTransaction.setPurpose(mPurpose);
            return this;
        }

        public String toHex() {
            mTransaction.setPurpose(mPurpose);
            return WUtil.bytes2Hex(mTransaction.unsafeBitcoinSerialize());
        }
    }
}
