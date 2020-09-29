package wannabit.io.cosmostaion.network.res;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import wannabit.io.cosmostaion.model.StarNameAccount;

public class ResIovStarNameAccountInDomain {

    @SerializedName("height")
    public String height;

    @SerializedName("result")
    public IovAccountInDomainValue result;

    public class IovAccountInDomainValue {
        @SerializedName("accounts")
        public ArrayList<StarNameAccount> accounts;

    }
}
