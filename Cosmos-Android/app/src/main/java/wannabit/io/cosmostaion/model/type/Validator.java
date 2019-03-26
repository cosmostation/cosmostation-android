package wannabit.io.cosmostaion.model.type;

import com.google.gson.annotations.SerializedName;

public class Validator {

    @SerializedName("operator_address")
    public String operator_address;

    @SerializedName("consensus_pubkey")
    public String consensus_pubkey;

    @SerializedName("jailed")
    public Boolean jailed;

    @SerializedName("tokens")
    public String tokens;

    @SerializedName("delegator_shares")
    public String delegator_shares;

    @SerializedName("bond_height")
    public Boolean bond_height;

    @SerializedName("unbonding_height")
    public String unbonding_height;

    @SerializedName("unbonding_time")
    public String unbonding_time;


    @SerializedName("description")
    public Description description;

    @SerializedName("commission")
    public Commission commission;


    public class Description {
        @SerializedName("moniker")
        public String moniker;

        @SerializedName("identity")
        public String identity;

        @SerializedName("website")
        public String website;

        @SerializedName("details")
        public String details;
    }

    public class Commission {
        @SerializedName("rate")
        public String rate;

        @SerializedName("max_rate")
        public String max_rate;

        @SerializedName("max_change_rate")
        public String max_change_rate;

        @SerializedName("update_time")
        public String update_time;
    }


    @Override
    public boolean equals(Object object)
    {
        boolean sameSame = false;

        if (object != null && object instanceof Validator) {
            sameSame = this.description.moniker.equals(((Validator)object).description.moniker);
        }

        return sameSame;
    }
}
