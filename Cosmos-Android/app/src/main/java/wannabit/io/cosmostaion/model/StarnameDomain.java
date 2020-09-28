package wannabit.io.cosmostaion.model;

import com.google.gson.annotations.SerializedName;

public class StarnameDomain {
    @SerializedName("name")
    public String name;

    @SerializedName("admin")
    public String admin;

    @SerializedName("valid_until")
    public long valid_until;

    @SerializedName("type")
    public String type;

    @SerializedName("broker")
    public String broker;
}
