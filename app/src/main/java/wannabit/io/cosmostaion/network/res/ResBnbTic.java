package wannabit.io.cosmostaion.network.res;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class ResBnbTic implements Parcelable {

    @SerializedName("symbol")
    public String symbol;

    @SerializedName("baseAssetName")
    public String baseAssetName;

    @SerializedName("quoteAssetName")
    public String quoteAssetName;

    @SerializedName("lastPrice")
    public String lastPrice;

    public ResBnbTic() {
    }

    public ResBnbTic(String symbol, String baseAssetName, String quoteAssetName, String lastPrice) {
        this.symbol = symbol;
        this.baseAssetName = baseAssetName;
        this.quoteAssetName = quoteAssetName;
        this.lastPrice = lastPrice;
    }

    protected ResBnbTic(Parcel in) {
        readFromParcel(in);
    }

    public void readFromParcel(Parcel in) {
        symbol = in.readString();
        baseAssetName = in.readString();
        quoteAssetName = in.readString();
        lastPrice = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(symbol);
        dest.writeString(baseAssetName);
        dest.writeString(quoteAssetName);
        dest.writeString(lastPrice);
    }

    public static final Creator<ResBnbTic> CREATOR = new Creator<ResBnbTic>() {
        @Override
        public ResBnbTic createFromParcel(Parcel in) {
            return new ResBnbTic(in);
        }

        @Override
        public ResBnbTic[] newArray(int size) {
            return new ResBnbTic[size];
        }
    };
}
