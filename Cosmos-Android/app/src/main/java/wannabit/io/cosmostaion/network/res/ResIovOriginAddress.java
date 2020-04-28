package wannabit.io.cosmostaion.network.res;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.utils.WLog;

public class ResIovOriginAddress {

    @SerializedName("key")
    public String key;

    @SerializedName("model")
    public StarNameModel model;

    public class StarNameModel {

        @SerializedName("targets")
        public ArrayList<StarNameTarget> targets;

        @SerializedName("owner")
        public String owner;
    }

    public class StarNameTarget {
        @SerializedName("blockchain_id")
        public String blockchain_id;

        @SerializedName("address")
        public String address;
    }

    public String getOriginAddress(BaseChain chain) {
        if (model == null || model.targets == null || model.targets.size() == 0) {
            return null;
        }
        for (StarNameTarget target:model.targets) {
            if (target.blockchain_id.contains(chain.getChain())) {
                return target.address;
            }
        }
        return null;
    }
}
