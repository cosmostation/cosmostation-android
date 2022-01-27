package wannabit.io.cosmostaion.network.res;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ResOkHistory {

    @SerializedName("code")
    public int code;

    @SerializedName("msg")
    public String msg;

    @SerializedName("detail_msg")
    public String detail_msg;

    @SerializedName("data")
    public Data data;

    public class Data {
        @SerializedName("hits")
        public ArrayList<Hit> hits;

        public class Hit {
            @SerializedName("hash")
            public String hash;

            @SerializedName("blocktime")
            public long blocktime;

            @SerializedName("blockHash")
            public String blockHash;

            @SerializedName("blockHeight")
            public long blockHeight;

            @SerializedName("transactionData")
            public ArrayList<TransactionData> transactionDatas;

            public class TransactionData {
                @SerializedName("type")
                public String type;
            }
        }
    }
}
