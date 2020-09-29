package wannabit.io.cosmostaion.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class StarNameAccount {
    @SerializedName("domain")
    public String domain;

    @SerializedName("name")
    public String name;

    @SerializedName("owner")
    public String owner;

    @SerializedName("valid_until")
    public long valid_until;

    @SerializedName("resources")
    public ArrayList<StarNameResource> resources;

    @SerializedName("certificates")
    public String certificates;

    @SerializedName("broker")
    public String broker;

    @SerializedName("metadata_uri")
    public String metadata_uri;

    public String getResourceSize() {
        if (resources == null || resources.size() == 0) {
            return "0";
        } else {
            return "" + resources.size();
        }
    }
}
