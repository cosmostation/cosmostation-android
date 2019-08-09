package wannabit.io.cosmostaion.network.res;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import wannabit.io.cosmostaion.model.type.Coin;

public class ResLcdIrisRedelegate implements Parcelable {
    @SerializedName("delegator_addr")
    public String delegator_addr;

    @SerializedName("validator_src_addr")
    public String validator_src_addr;

    @SerializedName("validator_dst_addr")
    public String validator_dst_addr;

    @SerializedName("creation_height")
    public String creation_height;

    @SerializedName("min_time")
    public String min_time;

    @SerializedName("initial_balance")
    public String initial_balance;

    @SerializedName("balance")
    public String balance;

    @SerializedName("shares_src")
    public String shares_src;

    @SerializedName("shares_dst")
    public String shares_dst;

    public ResLcdIrisRedelegate() {
    }

    public ResLcdIrisRedelegate(String delegator_addr, String validator_src_addr, String validator_dst_addr, String creation_height, String min_time, String initial_balance, String balance, String shares_src, String shares_dst) {
        this.delegator_addr = delegator_addr;
        this.validator_src_addr = validator_src_addr;
        this.validator_dst_addr = validator_dst_addr;
        this.creation_height = creation_height;
        this.min_time = min_time;
        this.initial_balance = initial_balance;
        this.balance = balance;
        this.shares_src = shares_src;
        this.shares_dst = shares_dst;
    }

    protected ResLcdIrisRedelegate(Parcel in) {
        readFromParcel(in);
    }

    public void readFromParcel(Parcel in) {
        delegator_addr = in.readString();
        validator_src_addr = in.readString();
        validator_dst_addr = in.readString();
        creation_height = in.readString();
        min_time = in.readString();
        initial_balance = in.readString();
        balance = in.readString();
        shares_src = in.readString();
        shares_dst = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(delegator_addr);
        dest.writeString(validator_src_addr);
        dest.writeString(validator_dst_addr);
        dest.writeString(creation_height);
        dest.writeString(min_time);
        dest.writeString(initial_balance);
        dest.writeString(balance);
        dest.writeString(shares_src);
        dest.writeString(shares_dst);
    }

    public static final Creator<ResLcdIrisRedelegate> CREATOR = new Creator<ResLcdIrisRedelegate>() {
        @Override
        public ResLcdIrisRedelegate createFromParcel(Parcel in) {
            return new ResLcdIrisRedelegate(in);
        }

        @Override
        public ResLcdIrisRedelegate[] newArray(int size) {
            return new ResLcdIrisRedelegate[size];
        }
    };
}
