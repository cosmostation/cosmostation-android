package wannabit.io.cosmostaion.network.res;

import android.content.Context;

import com.google.gson.annotations.SerializedName;

import org.json.JSONArray;
import org.json.JSONException;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.utils.WLog;

public class ResApiTxListCustom {

    @SerializedName("id")
    public int id;

    @SerializedName("chain_id")
    public String chain_id;

    @SerializedName("height")
    public int height;

    @SerializedName("code")
    public int code;

    @SerializedName("tx_hash")
    public String tx_hash;

    @SerializedName("messages")
    public String messages;

    @SerializedName("timestamp")
    public String timestamp;


    public JSONArray getMsgs() {
        try {
            return new JSONArray(messages);
        } catch (JSONException err){ WLog.e("getMsgs Error" + err.toString()); }
        return null;
    }

    public int getMsgCnt() {
        if (getMsgs() != null) {
            return getMsgs().length();
        }
        return 0;
    }

    public boolean isSuccess() {
        if (code > 0) {
            return false;
        }
        return true;
    }

    public String getMsgType(Context c, String address) {
        if (getMsgCnt() == 0) {
            return "Known";

        } else {
            try {
                String result = "";
                String msgType =  getMsgs().getJSONObject(0).getString("@type");
                if (msgType.contains("MsgDelegate")) {
                    result = c.getString(R.string.tx_delegate);
                } else if (msgType.contains("MsgUndelegate")) {
                    result = c.getString(R.string.tx_undelegate);

                } else if (msgType.contains("MsgWithdrawDelegatorReward")) {
                    result = c.getString(R.string.tx_get_reward);

                } else if (msgType.contains("MsgSend")) {
                    if (getMsgs().getJSONObject(0).getString("to_address").equals(address)) {
                        result = c.getString(R.string.tx_receive);
                    } else if (getMsgs().getJSONObject(0).getString("from_address").equals(address)) {
                        result = c.getString(R.string.tx_send);
                    } else {
                        result = c.getString(R.string.tx_transfer);
                    }

                } else if (msgType.contains("MsgBeginRedelegate")) {
                    result = c.getString(R.string.tx_redelegate);

                } else if (msgType.contains("MsgSetWithdrawAddress")) {
                    result = c.getString(R.string.tx_change_reward_address);
                }
                return result;



            } catch (Exception e) {}
            return "Known";
        }
    }

}
