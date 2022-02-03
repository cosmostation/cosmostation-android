package wannabit.io.cosmostaion.dao;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;

public class Cw20Assets implements Parcelable {
    @SerializedName("id")
    public String id;

    @SerializedName("contract_address")
    public String contract_address;

    @SerializedName("denom")
    public String denom;

    @SerializedName("decimal")
    public int decimal;

    @SerializedName("display")
    public int display;

    @SerializedName("logo")
    public String logo;

    public String amount = "0";

    protected Cw20Assets(Parcel in) {
        id = in.readString();
        contract_address = in.readString();
        denom = in.readString();
        decimal = in.readInt();
        display = in.readInt();
        logo = in.readString();
        amount = in.readString();
    }

    public static final Creator<Cw20Assets> CREATOR = new Creator<Cw20Assets>() {
        @Override
        public Cw20Assets createFromParcel(Parcel in) {
            return new Cw20Assets(in);
        }

        @Override
        public Cw20Assets[] newArray(int size) {
            return new Cw20Assets[size];
        }
    };

    public BigDecimal getAmount() {
        return new BigDecimal(amount);
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(contract_address);
        dest.writeString(denom);
        dest.writeInt(decimal);
        dest.writeInt(display);
        dest.writeString(logo);
        dest.writeString(amount);
    }
}
