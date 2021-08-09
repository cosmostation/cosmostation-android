package wannabit.io.cosmostaion.model;

import com.google.gson.annotations.SerializedName;

import wannabit.io.cosmostaion.utils.hdac.HdacTx;

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

    @SerializedName("rizonTxId")
    public String rizonTxId;

    @SerializedName("__v")
    public int v;

    @SerializedName("hdacTx")
    public HdacTx hdacTx;

}
