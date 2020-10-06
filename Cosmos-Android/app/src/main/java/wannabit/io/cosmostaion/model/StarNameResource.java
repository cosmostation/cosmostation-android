package wannabit.io.cosmostaion.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class StarNameResource implements Parcelable {
    @SerializedName("uri")
    public String uri;

    @SerializedName("resource")
    public String resource;

    public StarNameResource() {
    }

    public StarNameResource(String uri) {
        this.uri = uri;
    }

    public StarNameResource(String uri, String resource) {
        this.uri = uri;
        this.resource = resource;
    }

    protected StarNameResource(Parcel in) {
        readFromParcel(in);
    }

    public void readFromParcel(Parcel in) {
        uri = in.readString();
        resource = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(uri);
        dest.writeString(resource);
    }


    public static final Creator<StarNameResource> CREATOR = new Creator<StarNameResource>() {
        @Override
        public StarNameResource createFromParcel(Parcel in) {
            return new StarNameResource(in);
        }

        @Override
        public StarNameResource[] newArray(int size) {
            return new StarNameResource[size];
        }
    };


    @Override
    public boolean equals(Object obj) {
        if (obj == null) { return false; }

        if (obj.getClass() != this.getClass()) { return false; }

        final StarNameResource other = (StarNameResource) obj;

        if (!this.uri.equals(other.uri)) {
            return false;
        }
        return true;
    }

}