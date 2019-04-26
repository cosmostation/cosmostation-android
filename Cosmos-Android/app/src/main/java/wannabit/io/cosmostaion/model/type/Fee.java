package wannabit.io.cosmostaion.model.type;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Fee implements Parcelable {

    @SerializedName("gas")
    public String gas;

    @SerializedName("amount")
    public ArrayList<Coin> amount;


    public Fee() {
    }

    public Fee(String gas, ArrayList<Coin> amount) {
        this.gas = gas;
        this.amount = amount;
    }

    protected Fee(Parcel in) {
        readFromParcel(in);
    }

    public void readFromParcel(Parcel in) {
        gas = in.readString();
        amount = new ArrayList<>();
        in.readTypedList(amount, Coin.CREATOR);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(gas);
        dest.writeTypedList(amount);
    }

    public static final Creator<Fee> CREATOR = new Creator<Fee>() {
        @Override
        public Fee createFromParcel(Parcel in) {
            return new Fee(in);
        }

        @Override
        public Fee[] newArray(int size) {
            return new Fee[size];
        }
    };
}
