package wannabit.io.cosmostaion.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import cosmos.base.v1beta1.CoinOuterClass;

public class GDexManager {
    @SerializedName("address")
    public String address;

    @SerializedName("balances")
    public ArrayList<CoinOuterClass.Coin> reserve;

    public GDexManager(String address, ArrayList<CoinOuterClass.Coin> reserve) {
        this.address = address;
        this.reserve = reserve;
    }
}
