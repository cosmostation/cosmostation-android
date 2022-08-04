package wannabit.io.cosmostaion.network.req;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PushSyncRequest {
    @SerializedName("fcm_token")
    public String fcm_token;

    @SerializedName("accounts")
    public List<PushAccount> accounts;

    public PushSyncRequest(String fcm_token, List<PushAccount> accounts) {
        this.fcm_token = fcm_token;
        this.accounts = accounts;
    }

    public static class PushAccount {
        @SerializedName("address")
        public String address;

        @SerializedName("chain")
        public String chain;

        public PushAccount(String address, String chain) {
            this.address = address;
            this.chain = chain;
        }
    }
}
