package wannabit.io.cosmostaion.model.kava;

import com.google.gson.annotations.SerializedName;

import wannabit.io.cosmostaion.model.type.Coin;

public class CdpDeposit {
    @SerializedName("cdp_id")
    public String cdp_id;

    @SerializedName("depositor")
    public String depositor;

    @SerializedName("amount")
    public Coin amount;
}
