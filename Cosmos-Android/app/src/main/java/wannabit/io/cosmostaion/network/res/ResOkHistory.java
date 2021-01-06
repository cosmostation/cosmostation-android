package wannabit.io.cosmostaion.network.res;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import wannabit.io.cosmostaion.model.type.Msg;

public class ResOkHistory {

    public final static int OK_TYPE_TRANSFER        = 1;
    public final static int OK_TYPE_NEW_ORDER       = 2;
    public final static int OK_TYPE_CANCEL_ORDER    = 3;

    public final static int OK_TYPE_SIDE_BUY        = 1;
    public final static int OK_TYPE_SIDE_SELL       = 2;
    public final static int OK_TYPE_SIDE_SEND       = 3;
    public final static int OK_TYPE_SIDE_RECEIVE    = 4;

    @SerializedName("code")
    public int code;

    @SerializedName("msg")
    public String msg;

    @SerializedName("detail_msg")
    public String detail_msg;

    @SerializedName("data")
    public Data data;

    public class Data {
        @SerializedName("data")
        public ArrayList<DataDetail> dataDetails;

        @SerializedName("param_page")
        public PageParam page;

    }

    public class DataDetail {
        @SerializedName("txhash")
        public String txhash;

        @SerializedName("type")
        public int type;

        @SerializedName("address")
        public String address;

        @SerializedName("symbol")
        public String symbol;

        @SerializedName("side")
        public int side;

        @SerializedName("quantity")
        public String quantity;

        @SerializedName("fee")
        public String fee;

        @SerializedName("timestamp")
        public long timestamp;

    }

    public class PageParam {
        @SerializedName("page")
        public int page;

        @SerializedName("per_page")
        public int per_page;

        @SerializedName("total")
        public int total;

    }
}
