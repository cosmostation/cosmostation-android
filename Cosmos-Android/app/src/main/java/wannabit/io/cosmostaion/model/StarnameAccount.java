package wannabit.io.cosmostaion.model;

import com.google.gson.annotations.SerializedName;

public class StarnameAccount {
    @SerializedName("domain")
    public String domain;

    @SerializedName("name")
    public String name;

    @SerializedName("owner")
    public String owner;

    @SerializedName("valid_until")
    public long valid_until;

    @SerializedName("resources")
    public String resources;

    @SerializedName("certificates")
    public String certificates;

    @SerializedName("broker")
    public String broker;

    @SerializedName("metadata_uri")
    public String metadata_uri;
}
