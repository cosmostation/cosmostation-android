package wannabit.io.cosmostaion.network.req;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ReqTx {

    @SerializedName("from")
    public int from;

    @SerializedName("size")
    public int size;


    @SerializedName("query")
    public Query query;

    @SerializedName("sort")
    public ArrayList<Sort> sort;



    public static class Query {
        @SerializedName("multi_match")
        public MultiMatch multi_match;
    }


    public static class MultiMatch {
        @SerializedName("query")
        public String query;

        @SerializedName("fields")
        public ArrayList<String> fields;
    }


    public static class Sort {
        @SerializedName("height.keyword")
        public HeightKeyword heightkeyword;

    }

    public static class HeightKeyword {
        @SerializedName("order")
        public String order;
    }
}
