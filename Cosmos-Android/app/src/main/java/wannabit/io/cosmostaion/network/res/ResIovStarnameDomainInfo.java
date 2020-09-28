package wannabit.io.cosmostaion.network.res;

import com.google.gson.annotations.SerializedName;

import wannabit.io.cosmostaion.model.StarnameDomain;

public class ResIovStarnameDomainInfo {
    @SerializedName("height")
    public String height;

    @SerializedName("result")
    public IovStarnameDomainInfoValue result;


    public class IovStarnameDomainInfoValue {
        @SerializedName("Domains")
        public StarnameDomain Domains;

    }
}
