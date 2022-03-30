package wannabit.io.cosmostaion.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import cosmos.base.v1beta1.CoinOuterClass;
import wannabit.io.cosmostaion.model.type.Coin;

public class GDexManager {
    @SerializedName("address")
    public String address;

    @SerializedName("balances")
    public ArrayList<Coin> reserve = new ArrayList<>();

    public GDexManager(String address, List<CoinOuterClass.Coin> coins) {
        this.address = address;
        for (CoinOuterClass.Coin coin : coins) {
            reserve.add(new Coin(coin.getDenom(), coin.getAmount()));
        }
    }
}
