package wannabit.io.cosmostaion.dao;

import com.google.gson.annotations.SerializedName;

public class IbcToken {
    @SerializedName("auth")
    public boolean auth;

    @SerializedName("hash")
    public String hash;

    @SerializedName("base_denom")
    public String base_denom;

    @SerializedName("display_denom")
    public String display_denom;

    @SerializedName("decimal")
    public int decimal;

    @SerializedName("channel_id")
    public String channel_id;

    @SerializedName("port_id")
    public String port_id;

    @SerializedName("moniker")
    public String moniker;

    @SerializedName("counter_party")
    public counterParty counter_party;

    public class counterParty {
        @SerializedName("chain_id")
        public String chain_id;

        @SerializedName("channel_id")
        public String channel_id;

        @SerializedName("port_id")
        public String port_id;
    }
}
