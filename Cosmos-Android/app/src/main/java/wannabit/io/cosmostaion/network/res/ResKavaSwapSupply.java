package wannabit.io.cosmostaion.network.res;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;
import java.util.ArrayList;

import wannabit.io.cosmostaion.model.type.Coin;

public class ResKavaSwapSupply {

    @SerializedName("height")
    public String height;

    @SerializedName("result")
    public ArrayList<KavaSwapSupply> result;

    public KavaSwapSupply getSwapSupply(String denom) {
        if (result != null && result.size() > 0) {
            for (KavaSwapSupply supply:result) {
                if (supply.denom.equalsIgnoreCase(denom)) {
                    return supply;
                }
            }
        }
        return null;
    }

    public BigDecimal getSwapSupplyAmount(String denom) {
        BigDecimal result = BigDecimal.ZERO;
        KavaSwapSupply supply = getSwapSupply(denom);
        if (supply!= null) {
            BigDecimal incoming_supply = new BigDecimal(supply.incoming_supply.amount);
            BigDecimal outgoing_supply = new BigDecimal(supply.outgoing_supply.amount);
            BigDecimal current_supply = new BigDecimal(supply.current_supply.amount);
            BigDecimal supply_limit = new BigDecimal(supply.supply_limit.amount);
            result = supply_limit.subtract(current_supply).subtract(incoming_supply).subtract(outgoing_supply);
            if (result.compareTo(new BigDecimal("10000000")) < 0) {
                result = BigDecimal.ZERO;
            }
        }
        return result;
    }


    public class KavaSwapSupply implements Parcelable {

        @SerializedName("denom")
        public String denom;

        @SerializedName("incoming_supply")
        public Coin incoming_supply;

        @SerializedName("outgoing_supply")
        public Coin outgoing_supply;

        @SerializedName("current_supply")
        public Coin current_supply;

        @SerializedName("supply_limit")
        public Coin supply_limit;

        public BigDecimal getIncomingAmount() {
            BigDecimal result = BigDecimal.ZERO;
            if (incoming_supply != null && incoming_supply.amount != null) {
                result = new BigDecimal(incoming_supply.amount);
            }
            return result;
        }

        public BigDecimal getOutgoingAmount() {
            BigDecimal result = BigDecimal.ZERO;
            if (outgoing_supply != null && outgoing_supply.amount != null) {
                result = new BigDecimal(outgoing_supply.amount);
            }
            return result;
        }

        public BigDecimal getCurrentAmount() {
            BigDecimal result = BigDecimal.ZERO;
            if (current_supply != null && current_supply.amount != null) {
                result = new BigDecimal(current_supply.amount);
            }
            return result;
        }

        public BigDecimal getLimitAmount() {
            BigDecimal result = BigDecimal.ZERO;
            if (supply_limit != null && supply_limit.amount != null) {
                result = new BigDecimal(supply_limit.amount);
            }
            return result;
        }

        public BigDecimal getRemainAmount() {
            BigDecimal result = BigDecimal.ZERO;
            if (getRemainCap() != null) {
                return new BigDecimal(getRemainCap().amount);
            }
            return result;
        }

        public Coin getMaxSupply() {
            return supply_limit;
        }

        public Coin getCurrentCap() {
            if (supply_limit != null) {
                return new Coin(supply_limit.denom, getIncomingAmount().add(getOutgoingAmount()).add(getCurrentAmount()).toPlainString());
            }
            return null;
        }

        public Coin getRemainCap() {
            if (supply_limit != null) {
                BigDecimal remainAmount = getLimitAmount().subtract(getIncomingAmount()).subtract(getOutgoingAmount()).subtract(getCurrentAmount());
                if (remainAmount.compareTo(new BigDecimal("10000000")) < 0) {
                    remainAmount = BigDecimal.ZERO;
                }
                return new Coin(supply_limit.denom, remainAmount.toPlainString());
            }
            return null;
        }

        public KavaSwapSupply() {
        }

        public KavaSwapSupply(String denom, Coin incoming_supply, Coin outgoing_supply, Coin current_supply, Coin supply_limit) {
            this.denom = denom;
            this.incoming_supply = incoming_supply;
            this.outgoing_supply = outgoing_supply;
            this.current_supply = current_supply;
            this.supply_limit = supply_limit;
        }

        protected KavaSwapSupply(Parcel in) {
            readFromParcel(in);
        }

        public void readFromParcel(Parcel in) {
            denom = in.readString();
            incoming_supply = in.readParcelable(Coin.class.getClassLoader());
            outgoing_supply = in.readParcelable(Coin.class.getClassLoader());
            current_supply = in.readParcelable(Coin.class.getClassLoader());
            supply_limit = in.readParcelable(Coin.class.getClassLoader());
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(denom);
            dest.writeParcelable(incoming_supply, flags);
            dest.writeParcelable(outgoing_supply, flags);
            dest.writeParcelable(current_supply, flags);
            dest.writeParcelable(supply_limit, flags);
        }

        public final Creator<KavaSwapSupply> CREATOR = new Creator<KavaSwapSupply>() {
            @Override
            public KavaSwapSupply createFromParcel(Parcel in) {
                return new KavaSwapSupply(in);
            }

            @Override
            public KavaSwapSupply[] newArray(int size) {
                return new KavaSwapSupply[size];
            }
        };
    }
}
