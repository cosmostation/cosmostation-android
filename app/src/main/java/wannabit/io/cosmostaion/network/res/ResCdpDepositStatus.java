package wannabit.io.cosmostaion.network.res;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;
import java.util.ArrayList;

import wannabit.io.cosmostaion.model.kava.CdpDeposit;

public class ResCdpDepositStatus {

    @SerializedName("height")
    public String height;

    @SerializedName("result")
    public ArrayList<CdpDeposit> result;

    public BigDecimal getSelfDeposit(String address) {
        for (CdpDeposit deposit:result) {
            if (deposit.depositor.equals(address)) {
                return new BigDecimal(deposit.amount.amount);
            }
        }
        return BigDecimal.ZERO;
    }

}
