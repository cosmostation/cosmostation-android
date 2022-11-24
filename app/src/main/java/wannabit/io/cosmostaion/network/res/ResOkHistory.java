package wannabit.io.cosmostaion.network.res;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ResOkHistory {

    @SerializedName("code")
    public String code;

    @SerializedName("msg")
    public String msg;

    @SerializedName("data")
    public ArrayList<Data> data;

    public class Data {
        @SerializedName("transactionLists")
        public ArrayList<transactionData> transactionLists;

        public class transactionData {
            @SerializedName("txId")
            public String txId;

            @SerializedName("blockHash")
            public String blockHash;

            @SerializedName("height")
            public String height;

            @SerializedName("transactionTime")
            public String transactionTime;

            @SerializedName("from")
            public String from;

            @SerializedName("to")
            public String to;

            @SerializedName("amount")
            public String amount;

            @SerializedName("transactionSymbol")
            public String transactionSymbol;

            @SerializedName("txFee")
            public String txFee;

            @SerializedName("state")
            public String state;
        }
    }
}
