package wannabit.io.cosmostaion.model;

import com.google.gson.annotations.SerializedName;

public class Node_Info {
    @SerializedName("network")
    public String network;

    @SerializedName("version")
    public String version;

    @SerializedName("channels")
    public String channels;

    @SerializedName("moniker")
    public String moniker;


}
