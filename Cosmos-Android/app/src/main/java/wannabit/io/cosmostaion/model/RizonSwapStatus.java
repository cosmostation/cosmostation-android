package wannabit.io.cosmostaion.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class RizonSwapStatus {
    @SerializedName("status")
    public String status;

    @SerializedName("_id")
    public String id;

    @SerializedName("from")
    public String from;

    @SerializedName("to")
    public String to;

    @SerializedName("amount")
    public Double amount;

    @SerializedName("hdacTxId")
    public String hdacTxId;

    @SerializedName("rizonTx")
    public Object rizonTx;

    @SerializedName("rizonTxId")
    public String rizonTxId;

    @SerializedName("__v")
    public int v;

    @SerializedName("hdacTx")
    public HdacTx hdacTx;

    public class HdacTx {
        @SerializedName("txid")
        public String txid;

        @SerializedName("confirmations")
        public int confirmations;

        @SerializedName("blockhash")
        public String blockhash;

        @SerializedName("blockheight")
        public long blockheight;

        @SerializedName("time")
        public long time;

        @SerializedName("valueOut")
        public double valueOut;

        @SerializedName("data")
        public ArrayList<String> data;

    }
}
