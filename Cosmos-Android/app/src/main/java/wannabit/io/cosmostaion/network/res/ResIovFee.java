package wannabit.io.cosmostaion.network.res;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;

public class ResIovFee {
    @SerializedName("height")
    public String height;

    @SerializedName("result")
    public IovFeeValue result;


    public class IovFeeValue {
        @SerializedName("fees")
        public IovFee fees;

    }


    public class IovFee {
        @SerializedName("fee_coin_denom")
        public String fee_coin_denom;

        @SerializedName("fee_coin_price")
        public String fee_coin_price;

        @SerializedName("fee_default")
        public String fee_default;

        @SerializedName("register_account_closed")
        public String register_account_closed;

        @SerializedName("register_account_open")
        public String register_account_open;

        @SerializedName("transfer_account_closed")
        public String transfer_account_closed;

        @SerializedName("transfer_account_open")
        public String transfer_account_open;

        @SerializedName("replace_account_resources")
        public String replace_account_resources;

        @SerializedName("add_account_certificate")
        public String add_account_certificate;

        @SerializedName("del_account_certificate")
        public String del_account_certificate;

        @SerializedName("set_account_metadata")
        public String set_account_metadata;

        @SerializedName("register_domain_1")
        public String register_domain_1;

        @SerializedName("register_domain_2")
        public String register_domain_2;

        @SerializedName("register_domain_3")
        public String register_domain_3;

        @SerializedName("register_domain_4")
        public String register_domain_4;

        @SerializedName("register_domain_5")
        public String register_domain_5;

        @SerializedName("register_domain_default")
        public String register_domain_default;

        @SerializedName("register_open_domain_multiplier")
        public String register_open_domain_multiplier;

        @SerializedName("transfer_domain_closed")
        public String transfer_domain_closed;

        @SerializedName("transfer_domain_open")
        public String transfer_domain_open;

        @SerializedName("renew_domain_open")
        public String renew_domain_open;


        public BigDecimal getDomainFee(String domain) {
            if (domain.length() == 1) {
                return new BigDecimal(register_domain_1).movePointRight(6);

            } else if (domain.length() == 2) {
                return new BigDecimal(register_domain_2).movePointRight(6);

            } else if (domain.length() == 3) {
                return new BigDecimal(register_domain_3).movePointRight(6);

            } else if (domain.length() == 4) {
                return new BigDecimal(register_domain_4).movePointRight(6);

            } else {
                return new BigDecimal(register_domain_5).movePointRight(6);
            }
        }

        public BigDecimal getAccountFee(boolean open) {
            if (open) {
                return new BigDecimal(register_account_open).movePointRight(6);

            } else {
                return new BigDecimal(register_account_closed).movePointRight(6);

            }
        }

        public BigDecimal getReplaceFee() {
            return new BigDecimal(replace_account_resources).movePointRight(6);
        }


    }
}
