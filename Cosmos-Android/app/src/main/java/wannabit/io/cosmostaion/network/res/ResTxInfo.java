package wannabit.io.cosmostaion.network.res;

import android.text.TextUtils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import wannabit.io.cosmostaion.model.StdTx;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.model.type.Msg;
import wannabit.io.cosmostaion.utils.WLog;

public class ResTxInfo {
    @SerializedName("height")
    public String height;

    @SerializedName("txhash")
    public String txhash;

    @SerializedName("code")
    public Integer code;

    @SerializedName("logs")
    @Expose
    public Object logs;

    @SerializedName("gas_wanted")
    public String gas_wanted;

    @SerializedName("gas_used")
    public String gas_used;

    @SerializedName("tx")
    public StdTx tx;

    @SerializedName("timestamp")
    public String timestamp;

    @SerializedName("raw_log")
    public String raw_log;

    @SerializedName("events")
    public ArrayList<Event> events;

    public class Log {
        @SerializedName("msg_index")
        public int msg_index;

        @SerializedName("success")
        public boolean success;

        @SerializedName("log")
        public String log;

        @SerializedName("events")
        public ArrayList<Event> events;
    }

    public boolean isSuccess() {
        boolean result = true;
        try {
            Log temp = new Gson().fromJson(new Gson().toJson(logs), Log.class);
            result = temp.success;

        } catch (Exception e) { }

        try {
            ArrayList<Log> temp = new Gson().fromJson(new Gson().toJson(logs), new TypeToken<List<Log>>(){}.getType());
            for (Log log:temp) {
                if (!TextUtils.isEmpty(log.log)) {
                    result = false;
                    break;
                }
            }

        } catch (Exception e) { }

        if (code != null && code > 0) {
            result = false;
        }

        return result;
    }

    public String failMessage() {
        String result = "";
        try {
            Log temp = new Gson().fromJson(new Gson().toJson(logs), Log.class);
            result = temp.log;

        } catch (Exception e) { }

        try {
            ArrayList<Log> temp = new Gson().fromJson(new Gson().toJson(logs), new TypeToken<List<Log>>(){}.getType());
            for (Log log:temp) {
                if (!TextUtils.isEmpty(log.log)) {
                    result = log.log;
                    break;
                }
            }

        } catch (Exception e) { }

        if (raw_log != null) {
            result = raw_log;
        }

        return result.replace(" ", "\u00A0");
    }

    public BigDecimal simpleFee() {
        BigDecimal result = BigDecimal.ZERO;
        if (tx != null && tx.value != null && tx.value.fee != null &&
                tx.value.fee.amount != null && tx.value.fee.amount.size() > 0) {
            return new BigDecimal(tx.value.fee.amount.get(0).amount);
        }
        return result;
    }

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

    public ArrayList<Msg> getMsgs() {
        ArrayList<Msg> result = new ArrayList<>();
        if (tx != null && tx.value != null && tx.value.msg != null && tx.value.msg.size() > 0) {
            result = tx.value.msg;
        }
        return result;
    }

    public ArrayList<Event> getEvent() {
        if (events != null) return events;
        try {
            ArrayList<Log> temp = new Gson().fromJson(new Gson().toJson(logs), new TypeToken<List<Log>>(){}.getType());
            return temp.get(0).events;
        } catch (Exception e) {
            return null;
        }
    }


    public BigDecimal simpleReward(String opAdd) {
        BigDecimal result = BigDecimal.ZERO;
        if (getEvent() != null) {
            for (Event event:getEvent()) {
                if (event.type.equals("withdraw_rewards")) {
                    for (int i = 0; i < event.attributes.size(); i ++) {
                        if (event.attributes.get(i).key.equals("validator") && event.attributes.get(i).value.equals(opAdd)) {
                            if (i-1 < event.attributes.size() && event.attributes.get(i-1) != null && event.attributes.get(i-1).key.equals("amount")) {
                                String temp = event.attributes.get(i-1).value.replace("uatom", "").replace("ukava", "");
                                result = new BigDecimal(temp);
                            }
                        }

                    }
                }
            }
        }
        return result;
    }

    public BigDecimal simpleAutoReward() {
        BigDecimal result = BigDecimal.ZERO;
        if (getEvent() != null) {
            for (Event event:getEvent()) {
                if (event.type.equals("transfer")) {
                    for (EventAttribute attr:event.attributes) {
                        if (attr.key.equals("amount")) {
                            String temp = attr.value.replace("uatom", "").replace("ukava", "");
                            result = new BigDecimal(temp);
                            break;
                        }
                    }
                }
            }
        }
        return result;
    }

    public BigDecimal simpleCommission() {
        BigDecimal result = BigDecimal.ZERO;
        if (getEvent() != null) {
            for (Event event:getEvent()) {
                if (event.type.equals("withdraw_commission")) {
                    for (EventAttribute attr:event.attributes) {
                        if (attr.key.equals("amount")) {
                            String temp = attr.value.replace("uatom", "").replace("ukava", "");
                            result = new BigDecimal(temp);
                            break;
                        }
                    }
                }
            }
        }
        return result;
    }

    public Coin simpleSwapCoin() {
        Coin coin  = new Coin();
        if (getEvent() != null) {
            for (Event event:getEvent()) {
                if (event.type.equals("transfer")) {
                    for (EventAttribute attr:event.attributes) {
                        if (attr.key.equals("amount")) {
                            String value = attr.value;
                            String denom = value.replaceAll("[0-9]", "");
                            String amount = value.replaceAll("[^0-9]", "");
                            coin.denom = denom;
                            coin.amount = amount;
                        }
                    }
                }
            }
        }
        return coin;
    }

    public String simpleSwapId() {
        String result = "";
        if (getEvent() != null) {
            for (Event event:getEvent()) {
                if (event.type.equals("createAtomicSwap")) {
                    for (EventAttribute attr:event.attributes) {
                        if (attr.key.equals("atomic_swap_id")) {
                            result = attr.value;
                        }
                    }
                }
            }
        }
        return result;
    }




    public class Event {
        @SerializedName("type")
        public String type;
        @SerializedName("attributes")
        public ArrayList<EventAttribute> attributes;
    }

    public class EventAttribute {
        @SerializedName("key")
        public String key;
        @SerializedName("value")
        public String value;
    }




    //Field for Iris
    @SerializedName("hash")
    public String hash;

    @SerializedName("result")
    public Result result;

    public class Result {
        @SerializedName("GasWanted")
        public String GasWanted;

        @SerializedName("GasUsed")
        public String GasUsed;

        @SerializedName("tags")
        public ArrayList<Tag> tags;
    }





    @SerializedName("tags")
    public ArrayList<Tag> tags;
    public class Tag {
        @SerializedName("key")
        public String key;

        @SerializedName("value")
        public String value;
    }
}
