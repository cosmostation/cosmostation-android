package wannabit.io.cosmostaion.dao;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class IovToken implements Parcelable {

    @SerializedName("tokenTicker")
    public String tokenTicker;

    @SerializedName("tokenName")
    public String tokenName;

    @SerializedName("fractionalDigits")
    public int fractionalDigits;

    public IovToken() {
    }

    public IovToken(String tokenTicker, String tokenName, int fractionalDigits) {
        this.tokenTicker = tokenTicker;
        this.tokenName = tokenName;
        this.fractionalDigits = fractionalDigits;
    }

    protected IovToken(Parcel in) {
        readFromParcel(in);
    }

    public void readFromParcel(Parcel in) {
        tokenTicker = in.readString();
        tokenName = in.readString();
        fractionalDigits = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(tokenTicker);
        dest.writeString(tokenName);
        dest.writeInt(fractionalDigits);
    }


    public static final Creator<IovToken> CREATOR = new Creator<IovToken>() {
        @Override
        public IovToken createFromParcel(Parcel in) {
            return new IovToken(in);
        }

        @Override
        public IovToken[] newArray(int size) {
            return new IovToken[size];
        }
    };
}
