package wannabit.io.cosmostaion.model;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.utils.WLog;

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

    public static String BITCOINCASH    = "asset:bch";
    public static String BITCOIN        = "asset:btc";
    public static String LITECOIN       = "asset:ltc";
    public static String BINANCE        = "asset:bnb";
    public static String LUNA           = "asset:luna";
    public static String COSMOS         = "asset:atom";
    public static String EMONEY         = "asset:ngm";
    public static String IRIS           = "asset:iris";
    public static String KAVA           = "asset:kava";
    public static String ETHEREUM       = "asset:eth";
    public static String STARNAME       = "asset:iov";
    public static String BAND           = "asset:band";
    public static String TEZOS          = "asset:xtz";
    public static String LISK           = "asset:lsk";

    public Drawable getChainImg(Context c) {
        if (uri.equals(BITCOINCASH)) {
            return c.getResources().getDrawable(R.drawable.bcash_chain_img);

        } else if (uri.equals(BITCOIN)) {
            return c.getResources().getDrawable(R.drawable.bitcoin_chain_img);

        } else if (uri.equals(LITECOIN)) {
            return c.getResources().getDrawable(R.drawable.lite_chain_img);

        } else if (uri.equals(BINANCE)) {
            return c.getResources().getDrawable(R.drawable.binance_ch_img);

        } else if (uri.equals(LUNA)) {
            return c.getResources().getDrawable(R.drawable.terra_chain_img);

        } else if (uri.equals(COSMOS)) {
            return c.getResources().getDrawable(R.drawable.cosmos_wh_main);

        } else if (uri.equals(EMONEY)) {
            return c.getResources().getDrawable(R.drawable.emoney_chain_img);

        } else if (uri.equals(IRIS)) {
            return c.getResources().getDrawable(R.drawable.iris_wh);

        } else if (uri.equals(KAVA)) {
            return c.getResources().getDrawable(R.drawable.kava_img);

        } else if (uri.equals(ETHEREUM)) {
            return c.getResources().getDrawable(R.drawable.ethereum_chain_img);

        } else if (uri.equals(STARNAME)) {
            return c.getResources().getDrawable(R.drawable.iov_chain_img);

        } else if (uri.equals(BAND)) {
            return c.getResources().getDrawable(R.drawable.band_chain_img);

        } else if (uri.equals(TEZOS)) {
            return c.getResources().getDrawable(R.drawable.tezos_chain_img);

        } else if (uri.equals(LISK)) {
            return c.getResources().getDrawable(R.drawable.lisk_chain_img);

        }
        return c.getResources().getDrawable(R.drawable.default_chain_img);
    }

    public String getChainName() {
        if (uri.equals(BITCOINCASH)) {
            return "BitCoin Cash";

        } else if (uri.equals(BITCOIN)) {
            return "BitCoin";

        } else if (uri.equals(LITECOIN)) {
            return "LiteCoin";

        } else if (uri.equals(BINANCE)) {
            return "Binance";

        } else if (uri.equals(LUNA)) {
            return "Terra";

        } else if (uri.equals(COSMOS)) {
            return "Cosmos";

        } else if (uri.equals(EMONEY)) {
            return "E-Money";

        } else if (uri.equals(IRIS)) {
            return "Iris";

        } else if (uri.equals(KAVA)) {
            return "Kava";

        } else if (uri.equals(ETHEREUM)) {
            return "Ethereum";

        } else if (uri.equals(STARNAME)) {
            return "Starname";

        } else if (uri.equals(BAND)) {
            return "Band";

        } else if (uri.equals(TEZOS)) {
            return "Tezos";

        } else if (uri.equals(LISK)) {
            return "Lisk";

        }
        return uri;

    }




}