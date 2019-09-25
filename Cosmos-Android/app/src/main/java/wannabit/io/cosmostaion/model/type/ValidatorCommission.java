package wannabit.io.cosmostaion.model.type;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class ValidatorCommission implements Parcelable {

    //TODO rollback cosmos-hub2
//    @SerializedName("commission_rates")
//    public ValidatorCommissionRate commission_rates;

    @SerializedName("rate")
    public String rate;

    @SerializedName("max_rate")
    public String max_rate;

    @SerializedName("max_change_rate")
    public String max_change_rate;

    @SerializedName("update_time")
    public String update_time;

    public ValidatorCommission() {
    }

//    public ValidatorCommission(ValidatorCommissionRate commission_rates, String rate, String max_rate, String max_change_rate, String update_time) {
//        this.commission_rates = commission_rates;
//        this.rate = rate;
//        this.max_rate = max_rate;
//        this.max_change_rate = max_change_rate;
//        this.update_time = update_time;
//    }
public ValidatorCommission(String rate, String max_rate, String max_change_rate, String update_time) {
    this.rate = rate;
    this.max_rate = max_rate;
    this.max_change_rate = max_change_rate;
    this.update_time = update_time;
}

    protected ValidatorCommission(Parcel in) {
        readFromParcel(in);
    }

    public void readFromParcel(Parcel in) {
//        commission_rates = in.readParcelable(ValidatorCommissionRate.class.getClassLoader());
        update_time = in.readString();
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
//        dest.writeParcelable(commission_rates, flags);
        dest.writeString(update_time);
        dest.writeString(rate);
        dest.writeString(max_rate);
        dest.writeString(max_change_rate);
    }

    public static final Creator<ValidatorCommission> CREATOR = new Creator<ValidatorCommission>() {
        @Override
        public ValidatorCommission createFromParcel(Parcel in) {
            return new ValidatorCommission(in);
        }

        @Override
        public ValidatorCommission[] newArray(int size) {
            return new ValidatorCommission[size];
        }
    };
}
