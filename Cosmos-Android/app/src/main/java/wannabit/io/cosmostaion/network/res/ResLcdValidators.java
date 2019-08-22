package wannabit.io.cosmostaion.network.res;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import wannabit.io.cosmostaion.model.type.Validator;

public class ResLcdValidators implements Parcelable {

    @SerializedName("height")
    public String height;

    @SerializedName("result")
    public ArrayList<Validator> result;

    public ResLcdValidators() {
    }

    public ResLcdValidators(String height, ArrayList<Validator> result) {
        this.height = height;
        this.result = result;
    }

    protected ResLcdValidators(Parcel in) {
        readFromParcel(in);
    }

    public void readFromParcel(Parcel in) {
        height = in.readString();
        result = new ArrayList<>();
        in.readTypedList(result, Validator.CREATOR);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(height);
        dest.writeTypedList(result);
    }

    public static final Creator<ResLcdValidators> CREATOR = new Creator<ResLcdValidators>() {
        @Override
        public ResLcdValidators createFromParcel(Parcel in) {
            return new ResLcdValidators(in);
        }

        @Override
        public ResLcdValidators[] newArray(int size) {
            return new ResLcdValidators[size];
        }
    };
}
