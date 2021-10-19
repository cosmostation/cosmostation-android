package wannabit.io.cosmostaion.model.kava;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import wannabit.io.cosmostaion.model.type.Coin;

public class SwapDeposit implements Parcelable {
    @SerializedName("depositor")
    public String depositor;

    @SerializedName("pool_id")
    public String pool_id;

    @SerializedName("shares_owned")
    public String shares_owned;

    @SerializedName("shares_value")
    public ArrayList<Coin> shares_value;

    protected SwapDeposit(Parcel in) {
        depositor = in.readString();
        pool_id = in.readString();
        shares_owned = in.readString();
        shares_value = in.createTypedArrayList(Coin.CREATOR);
    }

    public static final Creator<SwapDeposit> CREATOR = new Creator<SwapDeposit>() {
        @Override
        public SwapDeposit createFromParcel(Parcel in) {
            return new SwapDeposit(in);
        }

        @Override
        public SwapDeposit[] newArray(int size) {
            return new SwapDeposit[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(depositor);
        dest.writeString(pool_id);
        dest.writeString(shares_owned);
        dest.writeTypedList(shares_value);
    }
}
