package wannabit.io.cosmostaion.model.type;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class ValidatorDescrition implements Parcelable {
    @SerializedName("moniker")
    public String moniker;

    @SerializedName("identity")
    public String identity;

    @SerializedName("website")
    public String website;

    @SerializedName("details")
    public String details;

    public ValidatorDescrition() {
    }

    public ValidatorDescrition(String moniker, String identity, String website, String details) {
        this.moniker = moniker;
        this.identity = identity;
        this.website = website;
        this.details = details;
    }

    protected ValidatorDescrition(Parcel in) {
        readFromParcel(in);
    }

    public void readFromParcel(Parcel in) {
        moniker = in.readString();
        identity = in.readString();
        website = in.readString();
        details = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(moniker);
        dest.writeString(identity);
        dest.writeString(website);
        dest.writeString(details);
    }

    public static final Creator<ValidatorDescrition> CREATOR = new Creator<ValidatorDescrition>() {
        @Override
        public ValidatorDescrition createFromParcel(Parcel in) {
            return new ValidatorDescrition(in);
        }

        @Override
        public ValidatorDescrition[] newArray(int size) {
            return new ValidatorDescrition[size];
        }
    };
}
