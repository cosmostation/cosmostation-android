package wannabit.io.cosmostaion.dao;

import com.google.gson.annotations.SerializedName;

public class Assets {
    @SerializedName("chain")
    public String chain;

    @SerializedName("denom")
    public String denom;

    @SerializedName("type")
    public String type;

    @SerializedName("base_denom")
    public String base_denom;

    @SerializedName("base_type")
    public String base_type;

    @SerializedName("dp_denom")
    public String dp_denom;

    @SerializedName("origin_chain")
    public String origin_chain;

    @SerializedName("decimal")
    public int decimal;

    @SerializedName("description")
    public String description;

    @SerializedName("path")
    public String path;

    @SerializedName("channel")
    public String channel;

    @SerializedName("port")
    public String port;

    @SerializedName("counter_party")
    public CounterParty counter_party;

    @SerializedName("image")
    public String image;

    @SerializedName("coinGeckoId")
    public String coinGeckoId;

    @SerializedName("contract")
    public String contract;


    public class CounterParty {
        @SerializedName("channel")
        public String channel;

        @SerializedName("port")
        public String port;

        @SerializedName("denom")
        public String denom;
    }
}
