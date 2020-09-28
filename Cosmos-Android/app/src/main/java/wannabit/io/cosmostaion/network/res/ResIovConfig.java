package wannabit.io.cosmostaion.network.res;

import com.google.gson.annotations.SerializedName;

public class ResIovConfig {
    @SerializedName("height")
    public String height;

    @SerializedName("result")
    public IovConfigValue result;


    public class IovConfigValue {
        @SerializedName("configuration")
        public IovConfig configuration;

    }

    public class IovConfig {
        @SerializedName("configurer")
        public String configurer;

        @SerializedName("valid_domain_name")
        public String valid_domain_name;

        @SerializedName("valid_account_name")
        public String valid_account_name;

        @SerializedName("valid_uri")
        public String valid_uri;

        @SerializedName("valid_resource")
        public String valid_resource;

        @SerializedName("domain_renew_period")
        public long domain_renew_period;

        @SerializedName("domain_renew_count_max")
        public long domain_renew_count_max;

        @SerializedName("domain_grace_period")
        public long domain_grace_period;

        @SerializedName("account_renew_period")
        public long account_renew_period;

        @SerializedName("account_renew_count_max")
        public long account_renew_count_max;

        @SerializedName("account_grace_period")
        public long account_grace_period;

        @SerializedName("resources_max")
        public long resources_max;

        @SerializedName("certificate_size_max")
        public long certificate_size_max;

        @SerializedName("certificate_count_max")
        public long certificate_count_max;

        @SerializedName("metadata_size_max")
        public long metadata_size_max;
    }
}
