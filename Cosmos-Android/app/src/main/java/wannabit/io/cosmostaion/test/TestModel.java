package wannabit.io.cosmostaion.test;

import com.google.gson.annotations.SerializedName;

import wannabit.io.cosmostaion.network.req.ReqTx;

public class TestModel {

    @SerializedName("query")
    public MatchH query;


    public static class MatchH {
        @SerializedName("match")
        public HashH match;
    }

    public static class HashH {
        @SerializedName("hash")
        public String hash;
    }
}
