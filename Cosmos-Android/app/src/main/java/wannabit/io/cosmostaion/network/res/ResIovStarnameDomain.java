package wannabit.io.cosmostaion.network.res;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import wannabit.io.cosmostaion.model.StarnameDomain;

public class ResIovStarnameDomain {
    @SerializedName("height")
    public String height;

    @SerializedName("result")
    public IovDomainValue result;


    public class IovDomainValue {
        @SerializedName("Domains")
        public  ArrayList<StarnameDomain> Domains;

    }
}
