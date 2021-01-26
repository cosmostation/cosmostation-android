package wannabit.io.cosmostaion.model;

import android.text.TextUtils;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;
import java.util.ArrayList;

import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.model.type.Coin;

public class Delegation_V1 {
    @SerializedName("delegation")
    public DelegationDetail_V1 delegation;

    @SerializedName("balance")
    public Coin balance;


    public class DelegationDetail_V1 {
        @SerializedName("delegator_address")
        public String delegator_address;

        @SerializedName("validator_address")
        public String validator_address;

        @SerializedName("shares")
        public String shares;
    }

    public BigDecimal getDelegation() {
        BigDecimal result = BigDecimal.ZERO;
        if (balance != null && !TextUtils.isEmpty(balance.amount)) {
            result = new BigDecimal(balance.amount);
        }
        return result;
    }
}
