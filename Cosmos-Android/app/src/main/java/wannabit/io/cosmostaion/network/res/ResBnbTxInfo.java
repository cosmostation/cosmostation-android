package wannabit.io.cosmostaion.network.res;

import com.google.gson.annotations.SerializedName;

import wannabit.io.cosmostaion.model.StdTx;
import wannabit.io.cosmostaion.model.type.Msg;

public class ResBnbTxInfo {

    @SerializedName("code")
    public Integer code;

    @SerializedName("height")
    public String height;

    @SerializedName("hash")
    public String hash;

    @SerializedName("ok")
    public boolean ok;

    @SerializedName("tx")
    public StdTx tx;

    @SerializedName("log")
    public String log;


    public String getMsgType(int position) {
        String result = "";
        if (tx != null && tx.value != null && tx.value.msg != null && tx.value.msg.size() > position) {
            result = tx.value.msg.get(position).type;
        }
        return result;
    }

    public Msg getMsg(int position) {
        Msg result = null;
        if (tx != null && tx.value != null && tx.value.msg != null && tx.value.msg.size() > position) {
            result = tx.value.msg.get(position);
        }
        return result;
    }
}
