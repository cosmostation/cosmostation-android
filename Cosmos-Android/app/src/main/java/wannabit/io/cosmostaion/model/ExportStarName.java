package wannabit.io.cosmostaion.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ExportStarName {
    @SerializedName("type")
    public String type;

    @SerializedName("addresses")
    public ArrayList<ExportResource> addresses = new ArrayList<>();

    public ExportStarName() {
    }

    public static class ExportResource {
        @SerializedName("ticker")
        public String ticker;

        @SerializedName("address")
        public String address;

        public ExportResource() {
        }

        public ExportResource(String ticker, String address) {
            this.ticker = ticker;
            this.address = address;
        }
    }
}
