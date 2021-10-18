package wannabit.io.cosmostaion.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import wannabit.io.cosmostaion.model.type.Coin;

public class RewardInfo {
    @SerializedName("validator_address")
    public String validator_address;

    @SerializedName("reward")
    public ArrayList<Coin> reward;
}
