package wannabit.io.cosmostaion.network.req;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ReqTxToken {

    @SerializedName("from")
    public int from;

    @SerializedName("size")
    public int size;

    @SerializedName("query")
    public Query query;

    public static class Query {
        @SerializedName("bool")
        public BOOL2 bool;
    }


    public static class BOOL2 {
        @SerializedName("must")
        public ArrayList<MUST> must;

    }

    public static class MUST {
        @SerializedName("multi_match")
        public MultiMatch multi_match;
    }

    public static class MultiMatch {
        @SerializedName("query")
        public String query;

        @SerializedName("fields")
        public ArrayList<String> fields;
    }

    public ReqTxToken(int from, int searchType, boolean isDesc, String address, String denom) {
        Query query = new Query();
        BOOL2 bool2 = new BOOL2();
        ArrayList<MUST> musts = new ArrayList<>();
        MultiMatch multiMatch1 = new MultiMatch();
        multiMatch1.query = address;
        ArrayList<String> fields1 = new ArrayList<>();
        if (searchType == 0) {
            fields1.add(from_addr);
            fields1.add(to_addr);
            fields1.add(input_addr);
            fields1.add(output_addr);
        } else if (searchType == 1) {
            fields1.add(input_addr);
            fields1.add(output_addr);
        }
        multiMatch1.fields = fields1;


        MultiMatch multiMatch2 = new MultiMatch();
        multiMatch2.query = denom;
        ArrayList<String> fields2 = new ArrayList<>();
        if (searchType == 0) {
            fields2.add(denom1);
            fields2.add(denom2);
            fields2.add(denom3);
        } else if (searchType == 1) {
            fields2.add(denom2);
            fields2.add(denom3);
        }
        multiMatch2.fields = fields2;

        MUST must1 = new MUST();
        must1.multi_match = multiMatch1;

        MUST must2 = new MUST();
        must2.multi_match = multiMatch2;

        musts.add(must1);
        musts.add(must2);
        bool2.must = musts;
        query.bool = bool2;

        this.query = query;
        this.size = 100;

    }


    public static final String from_addr            = "tx.value.msg.value.from_address";
    public static final String to_addr              = "tx.value.msg.value.to_address";
    public static final String input_addr           = "tx.value.msg.value.inputs.address";
    public static final String output_addr          = "tx.value.msg.value.outputs.address";

    public static final String denom1               = "tx.value.msg.value.amount.denom";
    public static final String denom2               = "tx.value.msg.value.inputs.coins.denom";
    public static final String denom3               = "tx.value.msg.value.outputs.coins.denom";

}
