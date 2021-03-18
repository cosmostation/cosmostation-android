package wannabit.io.cosmostaion.network.res;

import android.text.TextUtils;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;
import java.util.ArrayList;

import wannabit.io.cosmostaion.model.kava.CdpParam;
import wannabit.io.cosmostaion.model.type.Coin;

public class ResKavaCdpParam {

    @SerializedName("height")
    public String height;

    @SerializedName("result")
    public CdpParam result;
}
