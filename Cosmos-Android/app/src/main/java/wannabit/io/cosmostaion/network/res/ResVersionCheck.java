package wannabit.io.cosmostaion.network.res;

import com.google.gson.annotations.SerializedName;

public class ResVersionCheck {

    @SerializedName("app_name")
    public String app_name;

    @SerializedName("device_type")
    public String device_type;

    @SerializedName("enable")
    public boolean enable;

    @SerializedName("version")
    public int version;

    @SerializedName("timestamp")
    public String timestamp;

}
