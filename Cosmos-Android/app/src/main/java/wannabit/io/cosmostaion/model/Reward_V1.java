package wannabit.io.cosmostaion.model;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;
import java.util.ArrayList;

import wannabit.io.cosmostaion.model.type.Coin;

public class Reward_V1 {
    @SerializedName("validator_address")
    public String validator_address;

    @SerializedName("reward")
    public ArrayList<Coin> reward;

    public BigDecimal getRewardByDenom(String denom) {
        BigDecimal sum = BigDecimal.ZERO;
        if (reward == null) return sum;
        for (Coin coin: reward) {
            if (coin.denom.equals(denom)) {
                sum = sum.add(new BigDecimal(coin.amount));
            }
        }
        return sum;
    }
}
