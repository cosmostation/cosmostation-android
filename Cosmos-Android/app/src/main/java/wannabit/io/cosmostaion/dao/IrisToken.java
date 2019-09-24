package wannabit.io.cosmostaion.dao;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class IrisToken implements Parcelable {

    @SerializedName("base_token")
    public BaseToken base_token;

    public IrisToken() {
    }

    public IrisToken(BaseToken base_token) {
        this.base_token = base_token;
    }

    protected IrisToken(Parcel in) {
        readFromParcel(in);
    }

    public void readFromParcel(Parcel in) {
        base_token = in.readParcelable(BaseToken.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(base_token, flags);
    }

    public static final Creator<IrisToken> CREATOR = new Creator<IrisToken>() {
        @Override
        public IrisToken createFromParcel(Parcel in) {
            return new IrisToken(in);
        }

        @Override
        public IrisToken[] newArray(int size) {
            return new IrisToken[size];
        }
    };

    public static class BaseToken implements Parcelable {
        @SerializedName("id")
        public String id;

        @SerializedName("family")
        public String family;

        @SerializedName("source")
        public String source;

        @SerializedName("gateway")
        public String gateway;

        @SerializedName("symbol")
        public String symbol;

        @SerializedName("name")
        public String name;

        @SerializedName("decimal")
        public int decimal;

        @SerializedName("canonical_symbol")
        public String canonical_symbol;

        @SerializedName("min_unit_alias")
        public String min_unit_alias;

        public BaseToken() {
        }

        public BaseToken(String id, String family, String source, String gateway, String symbol, String name, int decimal, String canonical_symbol, String min_unit_alias) {
            this.id = id;
            this.family = family;
            this.source = source;
            this.gateway = gateway;
            this.symbol = symbol;
            this.name = name;
            this.decimal = decimal;
            this.canonical_symbol = canonical_symbol;
            this.min_unit_alias = min_unit_alias;
        }

        protected BaseToken(Parcel in) {
            readFromParcel(in);
        }

        public void readFromParcel(Parcel in) {
            id = in.readString();
            family = in.readString();
            source = in.readString();
            gateway = in.readString();
            symbol = in.readString();
            name = in.readString();
            decimal = in.readInt();
            canonical_symbol = in.readString();
            min_unit_alias = in.readString();
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(id);
            dest.writeString(family);
            dest.writeString(source);
            dest.writeString(gateway);
            dest.writeString(symbol);
            dest.writeString(name);
            dest.writeInt(decimal);
            dest.writeString(canonical_symbol);
            dest.writeString(min_unit_alias);
        }

        public static final Creator<BaseToken> CREATOR = new Creator<BaseToken>() {
            @Override
            public BaseToken createFromParcel(Parcel in) {
                return new BaseToken(in);
            }

            @Override
            public BaseToken[] newArray(int size) {
                return new BaseToken[size];
            }
        };

        public String getTxUnitDenom() {
            if (min_unit_alias != null)
                return min_unit_alias;
            else
                return id + "-min";
        }
    }


}
