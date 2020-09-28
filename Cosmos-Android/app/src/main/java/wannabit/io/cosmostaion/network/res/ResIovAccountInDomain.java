package wannabit.io.cosmostaion.network.res;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import wannabit.io.cosmostaion.model.StarnameAccount;

public class ResIovAccountInDomain {

    @SerializedName("height")
    public String height;

    @SerializedName("result")
    public IovAccountInDomainValue result;

    public class IovAccountInDomainValue {
        @SerializedName("accounts")
        public ArrayList<StarnameAccount> accounts;

    }
}
