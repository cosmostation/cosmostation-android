package wannabit.io.cosmostaion.network.res;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import wannabit.io.cosmostaion.model.kava.CdpDeposit;

public class ResCdpDepositStatus {

    @SerializedName("deposits")
    public ArrayList<CdpDeposit> result;
}
