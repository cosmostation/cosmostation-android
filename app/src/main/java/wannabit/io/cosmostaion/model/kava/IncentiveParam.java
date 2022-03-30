package wannabit.io.cosmostaion.model.kava;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;
import java.util.ArrayList;

public class IncentiveParam {
    @SerializedName("claim_multipliers")
    public ArrayList<ClaimMultiplier> claim_multipliers;

    public BigDecimal getFactor(String denom, int position) {
        for (ClaimMultiplier claimMultiplier : claim_multipliers) {
            if (claimMultiplier.denom.equalsIgnoreCase(denom)) {
                return new BigDecimal(claimMultiplier.multipliers.get(position).factor);
            }
        }
        return BigDecimal.ZERO;
    }
}
