package wannabit.io.cosmostaion.network.res;

import android.graphics.Picture;
import android.text.TextUtils;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import wannabit.io.cosmostaion.model.type.Coin;

public class ResKeyBaseUser {

    @SerializedName("them")
    public ArrayList<Them> them;

    public String getUrl() {
        String result = them.get(0).pictures.primary.url;
        if(TextUtils.isEmpty(result)) {
            return "error";
        } else {
            return result;
        }
    }

    public class Them {
        @SerializedName("pictures")
        public Picture pictures;
    }

    public class Picture {
        @SerializedName("primary")
        public Primary primary;
    }

    public class Primary {
        @SerializedName("url")
        public String url;
    }
}
