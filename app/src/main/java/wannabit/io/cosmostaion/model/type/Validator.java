package wannabit.io.cosmostaion.model.type;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;

public class Validator implements Parcelable {

    public static final int UNBONDED = 0;
    public static final int UNBONDING = 1;
    public static final int BONDED = 2;

    @SerializedName("operator_address")
    public String operator_address;

    @SerializedName("consensus_pubkey")
    public String consensus_pubkey;

    @SerializedName("jailed")
    public Boolean jailed;

    @SerializedName("status")
    public int status;

    @SerializedName("tokens")
    public String tokens;

    @SerializedName("delegator_shares")
    public String delegator_shares;

    @SerializedName("bond_height")
    public String bond_height;

    @SerializedName("unbonding_height")
    public String unbonding_height;

    @SerializedName("unbonding_time")
    public String unbonding_time;


    @SerializedName("description")
    public ValidatorDescrition description;

    @SerializedName("commission")
    public ValidatorCommission commission;

    public BigDecimal getCommission() {
        return new BigDecimal(commission.commission_rates.rate);
    }

    public Validator() {
    }

    public Validator(String operator_address, String consensus_pubkey, Boolean jailed, String tokens, int status,
                     String delegator_shares, String bond_height, String unbonding_height, String unbonding_time, ValidatorDescrition description, ValidatorCommission commission) {
        this.operator_address = operator_address;
        this.consensus_pubkey = consensus_pubkey;
        this.jailed = jailed;
        this.status = status;
        this.tokens = tokens;
        this.delegator_shares = delegator_shares;
        this.bond_height = bond_height;
        this.unbonding_height = unbonding_height;
        this.unbonding_time = unbonding_time;
        this.description = description;
        this.commission = commission;
    }

    protected Validator(Parcel in) {
        readFromParcel(in);
    }

    public void readFromParcel(Parcel in) {
        operator_address = in.readString();
        consensus_pubkey = in.readString();
        jailed = in.readByte() != 0;
        status = in.readInt();
        tokens = in.readString();
        delegator_shares = in.readString();
        bond_height = in.readString();
        unbonding_height = in.readString();
        unbonding_time = in.readString();
        description = in.readParcelable(ValidatorDescrition.class.getClassLoader());
        commission = in.readParcelable(ValidatorCommission.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(operator_address);
        dest.writeString(consensus_pubkey);
        dest.writeByte((byte) (jailed ? 1 : 0));
        dest.writeInt(status);
        dest.writeString(tokens);
        dest.writeString(delegator_shares);
        dest.writeString(bond_height);
        dest.writeString(unbonding_height);
        dest.writeString(unbonding_time);
        dest.writeParcelable(description, flags);
        dest.writeParcelable(commission, flags);
    }

    public static final Creator<Validator> CREATOR = new Creator<Validator>() {
        @Override
        public Validator createFromParcel(Parcel in) {
            return new Validator(in);
        }

        @Override
        public Validator[] newArray(int size) {
            return new Validator[size];
        }
    };


//    public class Description {
//        @SerializedName("moniker")
//        public String moniker;
//
//        @SerializedName("identity")
//        public String identity;
//
//        @SerializedName("website")
//        public String website;
//
//        @SerializedName("details")
//        public String details;
//    }
//
//    public class Commission {
//        @SerializedName("rate")
//        public String rate;
//
//        @SerializedName("max_rate")
//        public String max_rate;
//
//        @SerializedName("max_change_rate")
//        public String max_change_rate;
//
//        @SerializedName("update_time")
//        public String update_time;
//    }


    @Override
    public boolean equals(Object object) {
        boolean sameSame = false;

        if (object != null && object instanceof Validator) {
            sameSame = this.description.moniker.equals(((Validator) object).description.moniker);
        }

        return sameSame;
    }

}
