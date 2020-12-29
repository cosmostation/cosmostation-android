package wannabit.io.cosmostaion.network.res;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ResOkStaking {
    @SerializedName("delegator_address")
    public String delegator_address;

    @SerializedName("validator_address")
    public ArrayList<String> validator_address;

    @SerializedName("shares")
    public String shares;

    @SerializedName("tokens")
    public String tokens;

    @SerializedName("is_proxy")
    public boolean is_proxy;

    @SerializedName("total_delegated_tokens")
    public String total_delegated_tokens;

    @SerializedName("proxy_address")
    public String proxy_address;
}
