package wannabit.io.cosmostaion.network.res;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import wannabit.io.cosmostaion.base.BaseChain;

public class ResIovNameResolve {
    @SerializedName("height")
    public String height;

    @SerializedName("result")
    public IovNameValue result;


    public String getAddressWithChain(BaseChain chain) {
        String matchedAddress = "";
        if (result != null && result.account != null && result.account.resources != null) {
            for (NameResource resource:result.account.resources) {
                if (chain.equals(BaseChain.COSMOS_MAIN)) {
                    if (resource.uri.equals("asset:atom") && resource.resource.startsWith("cosmos")) {
                        return  resource.resource;
                    }

                } else if (chain.equals(BaseChain.IRIS_MAIN)) {
                    if (resource.uri.equals("asset:iris") && resource.resource.startsWith("iaa")) {
                        return  resource.resource;
                    }

                } else if (chain.equals(BaseChain.BNB_MAIN)) {
                    if (resource.uri.equals("asset:bnb") && resource.resource.startsWith("bnb")) {
                        return  resource.resource;
                    }

                } else if (chain.equals(BaseChain.KAVA_MAIN)) {
                    if (resource.uri.equals("asset:kava") && resource.resource.startsWith("kava")) {
                        return  resource.resource;
                    }

                } else if (chain.equals(BaseChain.IOV_MAIN)) {
                    if (resource.uri.equals("asset:iov") && resource.resource.startsWith("star")) {
                        return  resource.resource;
                    }

                }
            }
        }
        return matchedAddress;
    }


    public class IovNameValue {
        @SerializedName("account")
        public NameAccount account;

    }

    public class NameAccount {
        @SerializedName("domain")
        public String domain;

        @SerializedName("name")
        public String name;

        @SerializedName("owner")
        public String owner;

        @SerializedName("valid_until")
        public String valid_until;

        @SerializedName("certificates")
        public String certificates;

        @SerializedName("broker")
        public String broker;

        @SerializedName("metadata_uri")
        public String metadata_uri;

        @SerializedName("resources")
        public ArrayList<NameResource> resources;


    }


    public class NameResource {
        @SerializedName("uri")
        public String uri;

        @SerializedName("resource")
        public String resource;

    }
}
