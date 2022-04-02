package wannabit.io.cosmostaion.network.res;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import wannabit.io.cosmostaion.model.type.BnbHistory;

public class ResBnbHistories {

    @SerializedName("tx")
    public List<BnbHistory> tx;
}
