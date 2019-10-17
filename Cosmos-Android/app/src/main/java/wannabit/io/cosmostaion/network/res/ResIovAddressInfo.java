package wannabit.io.cosmostaion.network.res;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ResIovAddressInfo {

    @SerializedName("id")
    public String startId;

    @SerializedName("owner")
    public String owner;

    @SerializedName("targets")
    public ArrayList<IovTarget> targets;


    public class IovTarget {
        public String chainId;
        public String address;

    }
}
