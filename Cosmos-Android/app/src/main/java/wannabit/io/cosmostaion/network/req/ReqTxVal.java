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

        @SerializedName("filter")
        public Filter filter;

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


    public static class Filter {
        @SerializedName("bool")
        public Bool bool;
    }

    public static class Bool {
        @SerializedName("should")
        public ArrayList<Should> should;
    }

    public static class Should {
        @SerializedName("term")
        public Term term;
    }

    public static class Term {
        @SerializedName("tx.value.msg.value.validator_addr")
        public String validator_addr;

        @SerializedName("tx.value.msg.value.validator_src_address")
        public String validator_src_address;

        @SerializedName("tx.value.msg.value.validator_dst_address")
        public String validator_dst_address;

        @SerializedName("tx.value.msg.value.validator_address")
        public String validator_address;
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

        MultiMatch multiMatch = new MultiMatch();
        multiMatch.query = address;
        ArrayList<String> fields = new ArrayList<>();
        if(searchType == 0) {
            fields.add(delegator_addr);
            fields.add(delegator_address);
            fields.add(validator_src_address);
            fields.add(validator_dst_address);
        }
        multiMatch.fields = fields;

        MUST must = new MUST();
        must.multi_match = multiMatch;
        musts.add(must);
        bool2.must = musts;


        Should should1 = new Should();
        Should should2 = new Should();
        Should should3 = new Should();
        Should should4 = new Should();

        Term term1 = new Term();
        term1.validator_addr = opAddress;
        Term term2 = new Term();
        term2.validator_src_address = opAddress;
        Term term3 = new Term();
        term3.validator_address = opAddress;
        Term term4 = new Term();
        term4.validator_dst_address = opAddress;

        should1.term = term1;
        should2.term = term2;
        should3.term = term3;
        should4.term = term4;

        ArrayList<Should> shoulds = new ArrayList<>();
        shoulds.add(should1);
        shoulds.add(should2);
        shoulds.add(should3);
        shoulds.add(should4);

        Bool bool = new Bool();
        bool.should = shoulds;

        Filter filter = new Filter();
        filter.bool = bool;

        bool2.filter = filter;

        query.bool = bool2;

        this.query = query;
        this.size = 100;
    }


    public static final String delegator_addr = "tx.value.msg.value.delegator_addr";
    public static final String delegator_address = "tx.value.msg.value.delegator_address";
    public static final String validator_dst_address = "tx.value.msg.value.validator_dst_address";
    public static final String validator_src_address = "tx.value.msg.value.validator_src_address";
}
