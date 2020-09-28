package wannabit.io.cosmostaion.network.res;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import wannabit.io.cosmostaion.model.StarnameAccount;

public class ResIovStarnameAccount {
    @SerializedName("height")
    public String height;

    @SerializedName("result")
    public IovAccountValue result;

    public class IovAccountValue {
        @SerializedName("accounts")
        public ArrayList<StarnameAccount> accounts;

    }
}
