package wannabit.io.cosmostaion.model;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;

public class Validator_V1 {
    public static final String BONDED_V1        = "BOND_STATUS_BONDED";
    public static final String UNBONDED_V1      = "BOND_STATUS_UNBONDED";
    public static final String UNBONDING_V1     = "BOND_STATUS_UNBONDING";

    @SerializedName("operator_address")
    public String operator_address;

    @SerializedName("jailed")
    public Boolean jailed;

    @SerializedName("status")
    public String status;

    @SerializedName("tokens")
    public String tokens;

    @SerializedName("delegator_shares")
    public String delegator_shares;

    @SerializedName("bond_height")
    public String bond_height;

    @SerializedName("unbonding_height")
    public String unbonding_height;

    @SerializedName("unbonding_time")
    public String unbonding_time;

    @SerializedName("min_self_delegation")
    public String min_self_delegation;

    @SerializedName("description")
    public Description_V1 description;

    @SerializedName("commission")
    public Commission_V1 commission;

    public BigDecimal getCommission() {
        return new BigDecimal(commission.commission_rates.rate);
    }

}
