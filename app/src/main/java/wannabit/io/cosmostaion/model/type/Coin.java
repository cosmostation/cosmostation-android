package wannabit.io.cosmostaion.model.type;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import wannabit.io.cosmostaion.utils.WLog;

public class Coin implements Parcelable {

    @SerializedName("denom")
    public String denom;

    @SerializedName("amount")
    public String amount;


    public Coin() {
    }

    public Coin(String denom, String amount) {
        this.denom = denom;
        this.amount = amount;
    }

    protected Coin(Parcel in) {
        readFromParcel(in);
    }

    public void readFromParcel(Parcel in) {
        denom = in.readString();
        amount = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(denom);
        dest.writeString(amount);
    }

    public static final Creator<Coin> CREATOR = new Creator<Coin>() {
        @Override
        public Coin createFromParcel(Parcel in) {
            return new Coin(in);
        }

        @Override
        public Coin[] newArray(int size) {
            return new Coin[size];
        }
    };

    public boolean isIbc() {
        if (denom.startsWith("ibc/")) {
            return true;
        }
        return false;
    }

    public String getIbcHash() {
        if (!isIbc()) { return null; }
        return denom.replaceAll("ibc/", "");
    }

    public boolean osmosisAmm() {
        if (denom.startsWith("gamm/pool/")) {
            return true;
        }
        return false;
    }

    public String osmosisAmmDpDenom() {
        String[] split = denom.split("/");
        return "GAMM-" + split[split.length - 1];
    }

    public long osmosisAmmPoolId() {
        String id = denom.replace("gamm/pool/", "");
        return Long.parseLong(id);
    }

    public long injectivePoolId() {
        String id = denom.replace("share", "");
        return Long.parseLong(id);
    }

}
