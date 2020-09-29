package wannabit.io.cosmostaion.network.res;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import wannabit.io.cosmostaion.model.StarNameDomain;

public class ResIovStarNameDomain {
    @SerializedName("height")
    public String height;

    @SerializedName("result")
    public IovDomainValue result;


    public class IovDomainValue {
        @SerializedName("Domains")
        public  ArrayList<StarNameDomain> Domains;

    }
}
