package wannabit.io.cosmostaion.dao;

import android.os.Parcel;
import android.os.Parcelable;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.model.type.Fee;

import static wannabit.io.cosmostaion.base.BaseConstant.IS_TEST;

public class Reward implements Parcelable {

    public Long                 accountId;
    public String               validatorAddress;
    public ArrayList<Coin>      amount;
    public Long                 fetchTime;

    public Reward() {
    }

    public Reward(Long accountId, String validatorAddress, ArrayList<Coin> amount, Long fetchTime) {
        this.accountId = accountId;
        this.validatorAddress = validatorAddress;
        this.amount = amount;
        this.fetchTime = fetchTime;
    }

    protected Reward(Parcel in) {
        readFromParcel(in);
    }

    public void readFromParcel(Parcel in) {
        accountId = in.readLong();
        validatorAddress = in.readString();
        amount = new ArrayList<>();
        in.readTypedList(amount, Coin.CREATOR);
        fetchTime = in.readLong();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(accountId);
        dest.writeString(validatorAddress);
        dest.writeTypedList(amount);
        dest.writeLong(fetchTime);
    }

    public static final Creator<Reward> CREATOR = new Creator<Reward>() {
        @Override
        public Reward createFromParcel(Parcel in) {
            return new Reward(in);
        }

        @Override
        public Reward[] newArray(int size) {
            return new Reward[size];
        }
    };

    public BigDecimal getAtomAmount() {
        BigDecimal result = BigDecimal.ZERO;
        for(Coin coin:amount) {
            if (IS_TEST || coin.denom.equals(BaseConstant.COSMOS_MUON)) {
                result = new BigDecimal(coin.amount).setScale(0, RoundingMode.DOWN);

            } else if (!IS_TEST || coin.denom.equals(BaseConstant.COSMOS_ATOM)) {
                result = new BigDecimal(coin.amount).setScale(0, RoundingMode.DOWN);
            }
        }
        return result;
    }

//    public BigDecimal getPhotonAmount() {
//        BigDecimal result = BigDecimal.ZERO;
//        for(Coin coin:amount) {
//            if(coin.denom.equals(BaseConstant.COSMOS_PHOTON) || coin.denom.equals(BaseConstant.COSMOS_PHOTINO)) {
//                result = new BigDecimal(coin.amount).setScale(0, RoundingMode.DOWN);
//                break;
//            }
//        }
//        return result;
//    }
}
