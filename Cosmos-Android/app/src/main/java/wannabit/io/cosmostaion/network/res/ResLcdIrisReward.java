package wannabit.io.cosmostaion.network.res;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;
import java.util.ArrayList;

import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.model.type.Fee;

public class ResLcdIrisReward implements Parcelable {

    @SerializedName("total")
    public ArrayList<Coin> total;

    @SerializedName("delegations")
    public ArrayList<Delegations> delegations;

    @SerializedName("commission")
    public ArrayList<Coin> commission;

    public ResLcdIrisReward() {
    }

    public ResLcdIrisReward(ArrayList<Coin> total, ArrayList<Delegations> delegations, ArrayList<Coin> commission) {
        this.total = total;
        this.delegations = delegations;
        this.commission = commission;
    }

    protected ResLcdIrisReward(Parcel in) {
        readFromParcel(in);
    }

    public void readFromParcel(Parcel in) {
        total = new ArrayList<>();
        in.readTypedList(total, Coin.CREATOR);
        delegations = new ArrayList<>();
        in.readTypedList(delegations, Delegations.CREATOR);
        commission = new ArrayList<>();
        in.readTypedList(commission, Coin.CREATOR);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(total);
        dest.writeTypedList(delegations);
        dest.writeTypedList(commission);
    }

    public static final Creator<ResLcdIrisReward> CREATOR = new Creator<ResLcdIrisReward>() {
        @Override
        public ResLcdIrisReward createFromParcel(Parcel in) {
            return new ResLcdIrisReward(in);
        }

        @Override
        public ResLcdIrisReward[] newArray(int size) {
            return new ResLcdIrisReward[size];
        }
    };


    public static class Delegations implements Parcelable {

        @SerializedName("validator")
        public String validator;

        @SerializedName("reward")
        public ArrayList<Coin> reward;

        public Delegations() {
        }

        public Delegations(String validator, ArrayList<Coin> reward) {
            this.validator = validator;
            this.reward = reward;
        }

        protected Delegations(Parcel in) {
            readFromParcel(in);
        }

        public void readFromParcel(Parcel in) {
            validator = in.readString();
            reward = new ArrayList<>();
            in.readTypedList(reward, Coin.CREATOR);
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(validator);
            dest.writeTypedList(reward);
        }

        public static final Creator<Delegations> CREATOR = new Creator<Delegations>() {
            @Override
            public Delegations createFromParcel(Parcel in) {
                return new Delegations(in);
            }

            @Override
            public Delegations[] newArray(int size) {
                return new Delegations[size];
            }
        };
    }

    public BigDecimal getSimpleIrisReward() {
        BigDecimal sum = BigDecimal.ZERO;
        if(delegations != null && delegations.size() > 0) {
            for (Delegations delegation:delegations) {
                for(Coin reward:delegation.reward) {
                    if(reward.denom.equals(BaseConstant.TOKEN_IRIS_ATTO)) {
                        sum = sum.add(new BigDecimal(reward.amount));
                    }
                }
            }
        }
        return sum;
    }

    public BigDecimal getPerValReward(String valOpAddress) {
        BigDecimal sum = BigDecimal.ZERO;
        if(delegations != null && delegations.size() > 0) {
            for (Delegations delegation:delegations) {
                if (delegation.validator.equals(valOpAddress)) {
                    for (Coin reward:delegation.reward) {
                        if(reward.denom.equals(BaseConstant.TOKEN_IRIS_ATTO)) {
                            sum = sum.add(new BigDecimal(reward.amount));
                        }
                    }
                }
            }
        }
        return sum;
    }

    public Coin getPerValRewardCoin(String valOpAddress) {
        Coin result = null;
        if(delegations != null && delegations.size() > 0) {
            for (Delegations delegation:delegations) {
                if (delegation.validator.equals(valOpAddress)) {
                    result = delegation.reward.get(0);
                }
            }
        }
        return result;
    }
}
