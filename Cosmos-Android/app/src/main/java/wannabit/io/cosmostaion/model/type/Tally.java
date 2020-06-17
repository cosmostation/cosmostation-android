package wannabit.io.cosmostaion.model.type;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Tally {

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
        if (sum().equals(BigDecimal.ZERO)) {
            return BigDecimal.ZERO.setScale(2);
        }
        return new BigDecimal(yes).movePointRight(2).divide(sum(), 2, RoundingMode.DOWN);
    }

    public BigDecimal getNoPer() {
        if (sum().equals(BigDecimal.ZERO)) {
            return BigDecimal.ZERO.setScale(2);
        }
        return new BigDecimal(no).movePointRight(2).divide(sum(), 2, RoundingMode.DOWN);
    }

    public BigDecimal getAbstainPer() {
        if (sum().equals(BigDecimal.ZERO)) {
            return BigDecimal.ZERO.setScale(2);
        }
        return new BigDecimal(abstain).movePointRight(2).divide(sum(), 2, RoundingMode.DOWN);
    }

    public BigDecimal getVetoPer() {
        if (sum().equals(BigDecimal.ZERO)) {
            return BigDecimal.ZERO.setScale(2);
        }
        return new BigDecimal(no_with_veto).movePointRight(2).divide(sum(), 2, RoundingMode.DOWN);
    }
}
