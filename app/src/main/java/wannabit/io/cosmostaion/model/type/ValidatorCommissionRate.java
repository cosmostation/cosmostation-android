package wannabit.io.cosmostaion.model.type;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class ValidatorCommissionRate implements Parcelable {
    @SerializedName("rate")
    public String rate;

    @SerializedName("max_rate")
    public String max_rate;

    @SerializedName("max_change_rate")
    public String max_change_rate;

    public ValidatorCommissionRate() {
    }

    public ValidatorCommissionRate(String rate, String max_rate, String max_change_rate) {
        this.rate = rate;
        this.max_rate = max_rate;
        this.max_change_rate = max_change_rate;
    }

    protected ValidatorCommissionRate(Parcel in) {
        readFromParcel(in);
    }

    public void readFromParcel(Parcel in) {
        rate = in.readString();
        max_rate = in.readString();
        max_change_rate = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(rate);
        dest.writeString(max_rate);
        dest.writeString(max_change_rate);
    }

    public static final Creator<ValidatorCommissionRate> CREATOR = new Creator<ValidatorCommissionRate>() {
        @Override
        public ValidatorCommissionRate createFromParcel(Parcel in) {
            return new ValidatorCommissionRate(in);
        }

        @Override
        public ValidatorCommissionRate[] newArray(int size) {
            return new ValidatorCommissionRate[size];
        }
    };
}
