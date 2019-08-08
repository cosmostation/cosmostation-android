package wannabit.io.cosmostaion.network.res;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;
import java.util.ArrayList;

import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.model.type.Coin;

public class ResLcdIrisReward {

    @SerializedName("total")
    public ArrayList<Coin> total;

    @SerializedName("delegations")
    public ArrayList<Delegations> delegations;

    @SerializedName("commission")
    public ArrayList<Coin> commission;


    public class Delegations {

        @SerializedName("validator")
        public String validator;

        @SerializedName("reward")
        public ArrayList<Coin> reward;

    }

    public BigDecimal getSimpleIrisReward() {
        BigDecimal sum = BigDecimal.ZERO;
        if(delegations != null && delegations.size() > 0) {
            for (Delegations delegation:delegations) {
                for(Coin reward:delegation.reward) {
                    if(reward.denom.equals(BaseConstant.COSMOS_IRIS_ATTO)) {
                        sum = sum.add(new BigDecimal(reward.amount));
                    }
                }
            }
        }
        return sum;
    }


    public BigDecimal getPerValReward(String valOpAddress) {
        BigDecimal sum = BigDecimal.ZERO;
        if(delegations != null && delegations.size() > 0) {
            for (Delegations delegation:delegations) {
                if (delegation.validator.equals(valOpAddress)) {
                    for (Coin reward:delegation.reward) {
                        if(reward.denom.equals(BaseConstant.COSMOS_IRIS_ATTO)) {
                            sum = sum.add(new BigDecimal(reward.amount));
                        }
                    }
                }
            }
        }
        return sum;
    }

    public Coin getPerValRewardCoin(String valOpAddress) {
        Coin result = null;
        if(delegations != null && delegations.size() > 0) {
            for (Delegations delegation:delegations) {
                if (delegation.validator.equals(valOpAddress)) {
                    result = delegation.reward.get(0);
                }
            }
        }
        return result;
    }
}
