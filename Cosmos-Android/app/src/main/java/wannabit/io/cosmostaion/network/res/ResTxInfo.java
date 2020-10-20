package wannabit.io.cosmostaion.network.res;

import android.text.TextUtils;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;
import java.util.ArrayList;

import wannabit.io.cosmostaion.model.StdTx;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.model.type.Msg;

public class ResTxInfo {
    @SerializedName("height")
    public String height;

    @SerializedName("txhash")
    public String txhash;

    @SerializedName("code")
    public Integer code;

    @SerializedName("logs")
    public ArrayList<Log> logs;

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




    public boolean isSuccess() {
        boolean result = true;
        if (code != null && code > 0) {
            result = false;
        }
        return result;
    }

    public String failMessage() {
        String result = "";
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

    public BigDecimal simpleReward(String opAdd, int position) {
        BigDecimal result = BigDecimal.ZERO;
        if (logs != null && logs.get(position) != null) {
            for (Event event:logs.get(position).events) {
                if (event.type.equals("withdraw_rewards")) {
                    for (int i = 0; i < event.attributes.size(); i ++) {
                        if (event.attributes.get(i).key.equals("validator") && event.attributes.get(i).value.equals(opAdd)) {
                            if (i-1 < event.attributes.size() && event.attributes.get(i-1) != null && event.attributes.get(i-1).key.equals("amount")) {
                                if (event.attributes.get(i-1).value != null) {
                                    String temp = event.attributes.get(i-1).value.replaceAll("[^0-9]", "");
                                    result = new BigDecimal(temp);
                                }
                            }
                        }

                    }
                }
            }
        }
        return result;
    }

    public BigDecimal simpleAutoReward(String Addr, int position) {
        BigDecimal result = BigDecimal.ZERO;
        if (logs != null && logs.get(position) != null) {
            for (Event event:logs.get(position).events) {
                if (event.type.equals("transfer")) {
                    for (int i = 0; i < event.attributes.size(); i ++) {
                        if (event.attributes.get(i).key.equals("recipient") && event.attributes.get(i).value.equals(Addr)) {
                            for (int j = i; j < event.attributes.size(); j ++) {
                                if (event.attributes.get(j).key.equals("amount") && event.attributes.get(j).value != null) {
                                    String temp = event.attributes.get(j).value.replaceAll("[^0-9]", "");
                                    result = result.add(new BigDecimal(temp));
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
        return result;
    }

    public BigDecimal simpleCommission(int position) {
        BigDecimal result = BigDecimal.ZERO;
        if (logs != null && logs.get(position) != null) {
            for (Event event:logs.get(position).events) {
                if (event.type.equals("withdraw_commission")) {
                    for (EventAttribute attr:event.attributes) {
                        if (attr.key.equals("amount")) {
                            if (attr.value != null) {
                                String temp = attr.value.replaceAll("[^0-9]", "");
                                result = new BigDecimal(temp);
                            }
                        }
                    }
                }
            }
        }
        return result;
    }

    public Coin simpleSwapCoin() {
        Coin coin  = new Coin();
        if (logs != null && logs.get(0) != null && logs.get(0).events != null) {
            for (Event event:logs.get(0).events) {
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
        if (logs != null && logs.get(0) != null && logs.get(0).events != null) {
            for (Event event:logs.get(0).events) {
                if (event.type.equals("create_atomic_swap")) {
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

    public Coin simpleIncentive(int position) {
        Coin coin  = new Coin();
        BigDecimal amountSum = BigDecimal.ZERO;
        if (logs != null && logs.get(position) != null) {
            for (Event event:logs.get(position).events) {
                if (event.type.equals("claim_reward")) {
                    for (EventAttribute attr:event.attributes) {
                        if (attr.key.equals("claim_amount")) {
                            String value = attr.value;
                            String denom = value.replaceAll("[0-9]", "");
                            String amount = value.replaceAll("[^0-9]", "");
                            coin.denom = denom;
                            amountSum = amountSum.add(new BigDecimal(amount));
                        }
                    }
                }
            }
        }
        coin.amount = amountSum.toPlainString();
        return coin;
    }

    public Coin simpleRefund() {
        Coin coin  = new Coin();
        if (logs != null && logs.get(0) != null && logs.get(0).events != null) {
            for (Event event:logs.get(0).events) {
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

    public Coin simpleHarvestReward() {
        Coin coin  = new Coin();
        if (logs != null && logs.get(0) != null && logs.get(0).events != null) {
            for (Event event:logs.get(0).events) {
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
        @SerializedName("Code")
        public int Code;

        @SerializedName("Log")
        public String Log;

        @SerializedName("GasWanted")
        public String GasWanted;

        @SerializedName("GasUsed")
        public String GasUsed;

        @SerializedName("Tags")
        public ArrayList<Tag> Tags;
    }

    public class Tag {
        @SerializedName("key")
        public String key;

        @SerializedName("value")
        public String value;
    }


    public boolean isIrisSuccess() {
        boolean isSuccess = false;
        if (result != null && result.Code == 0) {
            isSuccess = true;
        }
        return isSuccess;
    }

    public String failMsgIris() {
        String msg = "";
        if (result != null && !TextUtils.isEmpty(result.Log)) {
            msg = result.Log;
        }
        return msg.replace(" ", "\u00A0");
    }

    public BigDecimal simpleUsedFeeIris() {
        return simpleFee().multiply(new BigDecimal(result.GasUsed)).divide(new BigDecimal(result.GasWanted), 18, BigDecimal.ROUND_DOWN);
    }


    public BigDecimal simpleRewardIris() {
        BigDecimal reward = BigDecimal.ZERO;
        if (result != null && result.Tags != null) {
            for (Tag tag:result.Tags) {
                if (tag.key.equals("withdraw-reward-total")) {
                    if (tag.value != null) {
                        String temp = tag.value.replaceAll("[^0-9]", "");
                        reward = new BigDecimal(temp);
                    }
                }

            }
        }
        return reward;
    }


    public ArrayList<Tag> rewardValidatorsIris() {
        ArrayList<Tag> validators = new ArrayList<>();
        if (result != null && result.Tags != null) {
            for (Tag tag:result.Tags) {
                if (tag.key.startsWith("withdraw-reward-from-validator-")) {
                    validators.add(tag);
                }
            }
        }
        return validators;
    }

    public String rewardValidatorIris(int position) {
        return rewardValidatorsIris().get(position).key.replace("withdraw-reward-from-validator-", "");
    }

    public BigDecimal rewardAmountIris(int position) {
        String temp = rewardValidatorsIris().get(position).value.replaceAll("[^0-9]", "");
        return new BigDecimal(temp);
    }

    public BigDecimal simpleCommissionIris() {
        BigDecimal reward = BigDecimal.ZERO;
        if (result != null && result.Tags != null) {
            for (Tag tag:result.Tags) {
                if (tag.key.equals("withdraw-reward-commission")) {
                    if (tag.value != null) {
                        String temp = tag.value.replaceAll("[^0-9]", "");
                        reward = new BigDecimal(temp);
                    }
                }
            }
        }
        return reward;
    }
}
