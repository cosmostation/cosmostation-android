package wannabit.io.cosmostaion.network.res;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;

import wannabit.io.cosmostaion.model.type.Validator;

public class ResLcdIrisPool implements Parcelable {

    @SerializedName("loose_tokens")
    public String loose_tokens;

    @SerializedName("bonded_tokens")
    public String bonded_tokens;

    @SerializedName("total_supply")
    public String total_supply;

    @SerializedName("bonded_ratio")
    public String bonded_ratio;

    public ResLcdIrisPool() {
    }

    public ResLcdIrisPool(String loose_tokens, String bonded_tokens, String total_supply, String bonded_ratio) {
        this.loose_tokens = loose_tokens;
        this.bonded_tokens = bonded_tokens;
        this.total_supply = total_supply;
        this.bonded_ratio = bonded_ratio;
    }

    protected ResLcdIrisPool(Parcel in) {
        readFromParcel(in);
    }

    public void readFromParcel(Parcel in) {
        this.loose_tokens = in.readString();
        this.bonded_tokens = in.readString();
        this.total_supply = in.readString();
        this.bonded_ratio = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(loose_tokens);
        dest.writeString(bonded_tokens);
        dest.writeString(total_supply);
        dest.writeString(bonded_ratio);
    }

    public static final Creator<ResLcdIrisPool> CREATOR = new Creator<ResLcdIrisPool>() {
        @Override
        public ResLcdIrisPool createFromParcel(Parcel in) {
            return new ResLcdIrisPool(in);
        }

        @Override
        public ResLcdIrisPool[] newArray(int size) {
            return new ResLcdIrisPool[size];
        }
    };

    public BigDecimal geTotal() {
        return new BigDecimal(total_supply);
    }

    public BigDecimal getBonded() {
        return new BigDecimal(bonded_tokens);
    }
}
