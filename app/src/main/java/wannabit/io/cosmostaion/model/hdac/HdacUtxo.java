package wannabit.io.cosmostaion.model.hdac;

import com.google.gson.annotations.SerializedName;

import org.bitcoinj.core.Coin;
import org.bitcoinj.core.Sha256Hash;
import org.bitcoinj.core.UTXO;
import org.bitcoinj.script.Script;

import java.math.BigDecimal;
import java.math.BigInteger;

public class HdacUtxo {
    @SerializedName("unspent_hash")
    public String unspent_hash;

    @SerializedName("address")
    public String address;

    @SerializedName("scriptPubKey")
    public String scriptPubKey;

    @SerializedName("amount")
    public String amount;

    @SerializedName("vout")
    public long vout;

    @SerializedName("confirmations")
    public long confirmations;

    @SerializedName("satoshis")
    public long satoshis;

    @SerializedName("txid")
    public String txid;

    public UTXO toUTXO() {
        byte[] txid;
        long index;
        Coin value;
        int height = 0; // not need
        boolean coinbase = false; // ???
        Script script;
        String address;
        try {
            txid = new BigInteger(this.txid, 32).toByteArray();
            index = this.vout;
            value = Coin.valueOf(new BigDecimal(this.amount).movePointRight(8).setScale(0).longValue());
            script = new Script(new BigInteger(this.scriptPubKey, 16).toByteArray());
            address = this.address;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        Sha256Hash hash = Sha256Hash.wrap(this.txid);
        return new UTXO(hash, index, value, height, coinbase, script, address);
    }
}
