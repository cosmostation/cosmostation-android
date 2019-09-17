package wannabit.io.cosmostaion.dao;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class BnbToken implements Parcelable {

    @SerializedName("symbol")
    public String symbol;

    @SerializedName("original_symbol")
    public String original_symbol;

    @SerializedName("name")
    public String name;

    @SerializedName("owner")
    public String owner;

    @SerializedName("total_supply")
    public String total_supply;

    @SerializedName("mintable")
    public boolean mintable;

    public BnbToken() {
    }

    public BnbToken(String symbol, String original_symbol, String name, String owner, String total_supply, boolean mintable) {
        this.symbol = symbol;
        this.original_symbol = original_symbol;
        this.name = name;
        this.owner = owner;
        this.total_supply = total_supply;
        this.mintable = mintable;
    }

    protected BnbToken(Parcel in) {
        readFromParcel(in);
    }

    public void readFromParcel(Parcel in) {
        symbol = in.readString();
        original_symbol = in.readString();
        name = in.readString();
        owner = in.readString();
        total_supply = in.readString();
        mintable = in.readByte() != 0;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(symbol);
        dest.writeString(original_symbol);
        dest.writeString(name);
        dest.writeString(owner);
        dest.writeString(total_supply);
        dest.writeByte((byte) (mintable ? 1 : 0));
    }

    public static final Creator<BnbToken> CREATOR = new Creator<BnbToken>() {
        @Override
        public BnbToken createFromParcel(Parcel in) {
            return new BnbToken(in);
        }

        @Override
        public BnbToken[] newArray(int size) {
            return new BnbToken[size];
        }
    };
}
