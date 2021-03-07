package wannabit.io.cosmostaion.model;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;
import java.math.RoundingMode;

import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.network.res.ResStakingPool;

public class Tally_V1 {
    @SerializedName("yes")
    public String yes;

    @SerializedName("abstain")
    public String abstain;

    @SerializedName("no")
    public String no;

    @SerializedName("no_with_veto")
    public String no_with_veto;

    public BigDecimal sum() {
        return new BigDecimal(yes).add(new BigDecimal(abstain)).add(new BigDecimal(no)).add(new BigDecimal(no_with_veto));
    }

    public BigDecimal getYesPer() {
        if (sum().equals(BigDecimal.ZERO) || (new BigDecimal(yes).longValue() == 0)) {
            return BigDecimal.ZERO.setScale(2);
        }
        return new BigDecimal(yes).movePointRight(2).divide(sum(), 2, RoundingMode.HALF_UP);
    }

    public BigDecimal getNoPer() {
        if (sum().equals(BigDecimal.ZERO) || (new BigDecimal(no).longValue() == 0)) {
            return BigDecimal.ZERO.setScale(2);
        }
        return new BigDecimal(no).movePointRight(2).divide(sum(), 2, RoundingMode.HALF_UP);
    }

    public BigDecimal getAbstainPer() {
        if (sum().equals(BigDecimal.ZERO) || (new BigDecimal(abstain).longValue() == 0)) {
            return BigDecimal.ZERO.setScale(2);
        }
        return new BigDecimal(abstain).movePointRight(2).divide(sum(), 2, RoundingMode.HALF_UP);
    }

    public BigDecimal getVetoPer() {
        if (sum().equals(BigDecimal.ZERO) || (new BigDecimal(no_with_veto).longValue() == 0)) {
            return BigDecimal.ZERO.setScale(2);
        }
        return new BigDecimal(no_with_veto).movePointRight(2).divide(sum(), 2, RoundingMode.HALF_UP);
    }

    public BigDecimal getTurnout(BaseData baseData) {
        BigDecimal result = BigDecimal.ZERO;
        if (baseData != null && baseData.mStakingPool_V1 != null) {
            if (sum().equals(BigDecimal.ZERO)) {
                return BigDecimal.ZERO.setScale(2);

            } else {
                BigDecimal bondedToken = baseData.mStakingPool_V1.getBondedTokens();
                return sum().movePointRight(2).divide(bondedToken, 2, RoundingMode.HALF_UP);
            }
        }

        return result;
    }
}
