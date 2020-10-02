package wannabit.io.cosmostaion.network.res;

import com.google.gson.annotations.SerializedName;

import wannabit.io.cosmostaion.model.StarNameDomain;

public class ResIovStarNameDomainInfo {
    @SerializedName("height")
    public String height;

    @SerializedName("result")
    public IovStarnameDomainInfoValue result;

    @SerializedName("error")
    public String error;


    public class IovStarnameDomainInfoValue {
        @SerializedName("Domains")
        public StarNameDomain Domains;

    }
}
