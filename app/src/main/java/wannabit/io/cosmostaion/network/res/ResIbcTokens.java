package wannabit.io.cosmostaion.network.res;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import wannabit.io.cosmostaion.dao.IbcToken;

public class ResIbcTokens {
    @SerializedName("ibc_tokens")
    public ArrayList<IbcToken> ibc_tokens;
}
