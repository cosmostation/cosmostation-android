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
        return new BigDecimal(yes).movePointRight(2).divide(sum(), 2, RoundingMode.DOWN);
    }

    public BigDecimal getNoPer() {
        return new BigDecimal(no).movePointRight(2).divide(sum(), 2, RoundingMode.DOWN);
    }

    public BigDecimal getAbstainPer() {
        return new BigDecimal(abstain).movePointRight(2).divide(sum(), 2, RoundingMode.DOWN);
    }

    public BigDecimal getVetoPer() {
        return new BigDecimal(no_with_veto).movePointRight(2).divide(sum(), 2, RoundingMode.DOWN);
    }
}
