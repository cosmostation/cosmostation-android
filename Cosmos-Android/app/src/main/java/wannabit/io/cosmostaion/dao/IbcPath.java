package wannabit.io.cosmostaion.dao;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class IbcPath {
    @SerializedName("chain_id")
    public String chain_id;

    @SerializedName("paths")
    public ArrayList<Path> paths;

    public class Path {
        @SerializedName("auth")
        public Boolean auth;

        @SerializedName("channel_id")
        public String channel_id;

        @SerializedName("port_id")
        public String port_id;

        @SerializedName("counter_party")
        public counterParty counter_party;
    }

    public class counterParty {
        @SerializedName("channel_id")
        public String channel_id;

        @SerializedName("port_id")
        public String port_id;
    }
}


