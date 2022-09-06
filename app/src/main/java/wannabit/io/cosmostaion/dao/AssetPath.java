package wannabit.io.cosmostaion.dao;

import android.os.Parcel;
import android.os.Parcelable;

public class AssetPath implements Parcelable {
    public String channel;
    public String port;

    public AssetPath(String channel, String port) {
        this.channel = channel;
        this.port = port;
    }

    protected AssetPath(Parcel in) {
        channel = in.readString();
        port = in.readString();
    }

    public static final Creator<AssetPath> CREATOR = new Creator<AssetPath>() {
        @Override
        public AssetPath createFromParcel(Parcel in) {
            return new AssetPath(in);
        }

        @Override
        public AssetPath[] newArray(int size) {
            return new AssetPath[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(channel);
        parcel.writeString(port);
    }

    public String getIBCContract() {
        if (port != null) {
            return port.replaceAll("wasm.", "");
        }
        return "";
    }
}
