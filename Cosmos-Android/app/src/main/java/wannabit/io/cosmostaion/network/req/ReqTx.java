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


    public ReqTx() {

    }

//    public ReqTx(int from, int size, String address,) {
//        this.from = from;
//        this.size = size;
//    }


    public ReqTx(int from, int searchType, boolean isDesc, String address) {
        this.from = from;

        HeightKeyword heightKeyword = new HeightKeyword();
        if(isDesc) heightKeyword.order = "desc";
        else heightKeyword.order = "asc";
        Sort sort = new Sort();
        sort.heightkeyword = heightKeyword;
        ArrayList<Sort> sorts = new ArrayList<>();
        sorts.add(sort);
        this.sort = sorts;

        MultiMatch multiMatch = new MultiMatch();
        multiMatch.query = address;
        ArrayList<String> fields = new ArrayList<>();
        if(searchType == 0) {
            fields.add(delegator_addr);
            fields.add(from_addr);
            fields.add(to_addr);
            fields.add(depositor_addr);
            fields.add(voter_addr);
            fields.add(input_addr);
            fields.add(output_addr);
        }
        multiMatch.fields = fields;
        Query query = new Query();
        query.multi_match = multiMatch;
        this.query = query;

        this.size = 100;
    }

    public static final String delegator_addr = "tx.value.msg.value.delegator_addr";
    public static final String from_addr = "tx.value.msg.value.from_address";
    public static final String to_addr = "tx.value.msg.value.to_address";
    public static final String depositor_addr = "tx.value.msg.value.depositor";
    public static final String voter_addr = "tx.value.msg.value.voter";
    public static final String input_addr = "tx.value.msg.value.input.address";
    public static final String output_addr = "tx.value.msg.value.output.address";

}
