package wannabit.io.cosmostaion.dao;

import com.google.gson.annotations.SerializedName;

public class AssetsV2 {
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

    @SerializedName("image")
    public String image;

    @SerializedName("contract")
    public String contract;
}
