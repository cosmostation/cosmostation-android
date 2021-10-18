package wannabit.io.cosmostaion.network.res;

import com.google.gson.annotations.SerializedName;

public class ResBnbFee {

    @SerializedName("msg_type")
    public String msg_type;

    @SerializedName("fee")
    public long fee;

    @SerializedName("fee_for")
    public long fee_for;



    @SerializedName("fixed_fee_params")
    public BnbFixedFeeParam fixed_fee_params;

    @SerializedName("multi_transfer_fee")
    public long multi_transfer_fee;

    @SerializedName("lower_limit_as_multi")
    public long lower_limit_as_multi;


    public class BnbFixedFeeParam {
        @SerializedName("msg_type")
        public String msg_type;

        @SerializedName("fee")
        public long fee;

        @SerializedName("fee_for")
        public long fee_for;

    }
}
