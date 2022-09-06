package wannabit.io.cosmostaion.dao;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Collections;

import wannabit.io.cosmostaion.base.chains.ChainConfig;

public class Asset implements Parcelable {
    @SerializedName("chain")
    public String chain;

    @SerializedName("denom")
    public String denom;

    @SerializedName("type")
    public String type;

    @SerializedName("base_denom")
    public String base_denom;

    @SerializedName("base_type")
    public String base_type;

    @SerializedName("dp_denom")
    public String dp_denom;

    @SerializedName("origin_chain")
    public String origin_chain;

    @SerializedName("decimal")
    public int decimal;

    @SerializedName("description")
    public String description;

    @SerializedName("path")
    public String path;

    @SerializedName("channel")
    public String channel;

    @SerializedName("port")
    public String port;

    @SerializedName("counter_party")
    public CounterParty counter_party;

    @SerializedName("image")
    public String image;

    @SerializedName("coinGeckoId")
    public String coinGeckoId;

    @SerializedName("contract")
    public String contract;

    protected Asset(Parcel in) {
        chain = in.readString();
        denom = in.readString();
        type = in.readString();
        base_denom = in.readString();
        base_type = in.readString();
        dp_denom = in.readString();
        origin_chain = in.readString();
        decimal = in.readInt();
        description = in.readString();
        path = in.readString();
        channel = in.readString();
        port = in.readString();
        image = in.readString();
        coinGeckoId = in.readString();
        contract = in.readString();
    }

    public static final Creator<Asset> CREATOR = new Creator<Asset>() {
        @Override
        public Asset createFromParcel(Parcel in) {
            return new Asset(in);
        }

        @Override
        public Asset[] newArray(int size) {
            return new Asset[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(chain);
        parcel.writeString(denom);
        parcel.writeString(type);
        parcel.writeString(base_denom);
        parcel.writeString(base_type);
        parcel.writeString(dp_denom);
        parcel.writeString(origin_chain);
        parcel.writeInt(decimal);
        parcel.writeString(description);
        parcel.writeString(path);
        parcel.writeString(channel);
        parcel.writeString(port);
        parcel.writeString(image);
        parcel.writeString(coinGeckoId);
        parcel.writeString(contract);
    }


    public class CounterParty {
        @SerializedName("channel")
        public String channel;

        @SerializedName("port")
        public String port;

        @SerializedName("denom")
        public String denom;
    }

    public String beforeChain(ChainConfig chainConfig) {
        if (path != null) {
            String[] chainPath = path.split(">");
            ArrayList<String> chainNames = new ArrayList<>();
            Collections.addAll(chainNames, chainPath);
            int matched = chainNames.indexOf(chainConfig.chainName());
            if (matched > 0) {
                return chainPath[matched - 1];
            }
        }
        return null;
    }
}
