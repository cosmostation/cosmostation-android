package wannabit.io.cosmostaion.network.res;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import wannabit.io.cosmostaion.model.type.BnbHistory;

public class ResBnbHistories {

    @SerializedName("tx")
    public ArrayList<BnbHistory> tx;

}
