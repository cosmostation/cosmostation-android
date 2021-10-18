package wannabit.io.cosmostaion.network.res;

import com.google.gson.annotations.SerializedName;

import wannabit.io.cosmostaion.model.type.Coin;

public class ResBnbSwapInfo {

    public static final int BNB_STATUS_OPEN         = 1;
    public static final int BNB_STATUS_COMPLETED    = 2;
    public static final int BNB_STATUS_REFUNDED     = 3;

    @SerializedName("swapId")
    public String swapId;

    @SerializedName("fromAddr")
    public String fromAddr;

    @SerializedName("status")
    public int status;

    @SerializedName("randomNumber")
    public String randomNumber;

    @SerializedName("outAmount")
    public String outAmount;

    @SerializedName("expireHeight")
    public long expireHeight;

    public Coin getSendCoin() {
        String[] output = outAmount.split(":");
        return new Coin(output[1].trim(),output[0].trim());
    }
}
