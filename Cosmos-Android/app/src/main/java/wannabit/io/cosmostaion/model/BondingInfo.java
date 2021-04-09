package wannabit.io.cosmostaion.model;

import com.google.gson.annotations.SerializedName;

import wannabit.io.cosmostaion.model.type.Coin;

public class BondingInfo {
    @SerializedName("delegator_address")
    public String delegator_address;

    @SerializedName("validator_address")
    public String validator_address;

    @SerializedName("shares")
    public String shares;

    @SerializedName("balance")
    public Coin balance;
}
