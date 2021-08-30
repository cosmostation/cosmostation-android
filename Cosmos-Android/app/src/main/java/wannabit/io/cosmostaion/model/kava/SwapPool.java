package wannabit.io.cosmostaion.model.kava;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import wannabit.io.cosmostaion.model.type.Coin;

public class SwapPool implements Parcelable {
    @SerializedName("name")
    public String name;

    @SerializedName("coins")
    public ArrayList<Coin> coins;

    @SerializedName("total_shares")
    public String total_shares;

    protected SwapPool(Parcel in) {
        name = in.readString();
        coins = in.createTypedArrayList(Coin.CREATOR);
        total_shares = in.readString();
    }

    public static final Creator<SwapPool> CREATOR = new Creator<SwapPool>() {
        @Override
        public SwapPool createFromParcel(Parcel in) {
            return new SwapPool(in);
        }

        @Override
        public SwapPool[] newArray(int size) {
            return new SwapPool[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeTypedList(coins);
        dest.writeString(total_shares);
    }
}
