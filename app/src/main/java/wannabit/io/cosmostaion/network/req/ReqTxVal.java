package wannabit.io.cosmostaion.network.req;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ReqTxVal {

    @SerializedName("from")
    public int from;

    @SerializedName("size")
    public int size;


    @SerializedName("query")
    public Query query;

    @SerializedName("sort")
    public ArrayList<Sort> sort;



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

    public static class Sort {
        @SerializedName("height")
        public HeightKeyword height;

    }

    public static class HeightKeyword {
        @SerializedName("order")
        public String order;
    }


    public ReqTxVal(int from, int searchType, boolean isDesc, String address, String opAddress) {
        this.from = from;

        HeightKeyword heightKeyword = new HeightKeyword();
        if(isDesc) heightKeyword.order = "desc";
        else heightKeyword.order = "asc";
        Sort sort = new Sort();
        sort.height = heightKeyword;
        ArrayList<Sort> sorts = new ArrayList<>();
        sorts.add(sort);
        this.sort = sorts;


        Query query = new Query();

        BOOL2 bool2 = new BOOL2();

        ArrayList<MUST> musts = new ArrayList<>();

        MultiMatch multiMatch1 = new MultiMatch();
        multiMatch1.query = address;
        ArrayList<String> fields1 = new ArrayList<>();
        fields1.add(delegator_addr);
        fields1.add(delegator_address);
        multiMatch1.fields = fields1;


        MultiMatch multiMatch2 = new MultiMatch();
        multiMatch2.query = opAddress;
        ArrayList<String> fields2 = new ArrayList<>();
        if (searchType == 0) {
            fields2.add(validator_address);
            fields2.add(validator_dst_address);
            fields2.add(validator_src_address);
        } else if (searchType == 1) {
            fields2.add(validator_addr);
            fields2.add(validator_address);
            fields2.add(validator_op_addr);
            fields2.add(validator_dst_add);
            fields2.add(validator_src_add);
            fields2.add(result_tag_key);
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


    public static final String delegator_addr = "tx.value.msg.value.delegator_addr";
    public static final String delegator_address = "tx.value.msg.value.delegator_address";

    public static final String validator_address = "tx.value.msg.value.validator_address";
    public static final String validator_dst_address = "tx.value.msg.value.validator_dst_address";
    public static final String validator_src_address = "tx.value.msg.value.validator_src_address";

    public static final String validator_addr = "tx.value.msg.value.validator_addr";
    public static final String validator_op_addr = "tx.value.msg.value.val_operator_addr";
    public static final String validator_dst_add = "tx.value.msg.value.validator_dst_addr";
    public static final String validator_src_add = "tx.value.msg.value.validator_src_addr";
    public static final String result_tag_key = "result.tags.key";
}
