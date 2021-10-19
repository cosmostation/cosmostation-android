package wannabit.io.cosmostaion.dao;

import com.google.gson.annotations.SerializedName;

import java.lang.annotation.Annotation;
import java.util.ArrayList;

public class IbcPath implements SerializedName{
    @SerializedName("chain_id")
    public String chain_id;

    @SerializedName("paths")
    public ArrayList<Path> paths;

    @Override
    public String value() {
        return null;
    }

    @Override
    public String[] alternate() {
        return new String[0];
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return null;
    }

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


