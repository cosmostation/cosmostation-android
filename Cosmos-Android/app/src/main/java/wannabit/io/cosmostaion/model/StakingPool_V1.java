package wannabit.io.cosmostaion.model;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;

public class StakingPool_V1 {

    @SerializedName("not_bonded_tokens")
    public String not_bonded_tokens;

    @SerializedName("bonded_tokens")
    public String bonded_tokens;

    public BigDecimal getBondedTokens() {
        if (bonded_tokens == null) return BigDecimal.ZERO;
        return new BigDecimal(bonded_tokens);
    }

    public BigDecimal getUnbondedTokens() {
        if (not_bonded_tokens == null) return BigDecimal.ZERO;
        return new BigDecimal(not_bonded_tokens);
    }

    public BigDecimal getTotalTokens() {
        return getBondedTokens().add(getUnbondedTokens());
    }
}
