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

    @SerializedName("origin_chain")
    public String origin_chain;

    @SerializedName("origin_denom")
    public String origin_denom;

    @SerializedName("origin_type")
    public String origin_type;

    @SerializedName("symbol")
    public String symbol;

    @SerializedName("decimals")
    public int decimals;

    @SerializedName("description")
    public String description;

    @SerializedName("enable")
    public boolean enable;

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
        origin_chain = in.readString();
        origin_denom = in.readString();
        origin_type = in.readString();
        symbol = in.readString();
        decimals = in.readInt();
        description = in.readString();
        enable = in.readByte() != 0;
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
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(chain);
        dest.writeString(denom);
        dest.writeString(type);
        dest.writeString(origin_chain);
        dest.writeString(origin_denom);
        dest.writeString(origin_type);
        dest.writeString(symbol);
        dest.writeInt(decimals);
        dest.writeString(description);
        dest.writeByte((byte) (enable ? 1 : 0));
        dest.writeString(path);
        dest.writeString(channel);
        dest.writeString(port);
        dest.writeString(image);
        dest.writeString(coinGeckoId);
        dest.writeString(contract);
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
