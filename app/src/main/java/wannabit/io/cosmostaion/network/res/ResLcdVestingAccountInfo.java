package wannabit.io.cosmostaion.network.res;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;

import wannabit.io.cosmostaion.model.type.Coin;

public class ResLcdVestingAccountInfo {

    @SerializedName("height")
    public String height;

    @SerializedName("result")
    public VestingAccountInfoResult result;

    public ArrayList<VestingPeriod> getCalcurateVesting() {
        ArrayList<VestingPeriod> periods = new ArrayList<>();
        if (result.value.vesting_periods != null) {
            long cTime = Calendar.getInstance().getTime().getTime();
            for (int i = 0; i < result.value.vesting_periods.size(); i++) {
                long unlockTime = getUnLockTime(i);
                if (cTime < unlockTime) {
                    VestingPeriod temp = new VestingPeriod();
                    temp.length = "" + unlockTime;
                    temp.amount = result.value.vesting_periods.get(i).amount;
                    periods.add(temp);
                }
            }
        }
        return periods;
    }

    public int getCalcurateVestingCntByDenom(String denom) {
        int cnt = 0;
        for (VestingPeriod vp : getCalcurateVesting()) {
            for (Coin coin : vp.amount) {
                if (coin.denom.equals(denom)) {
                    cnt = cnt + 1;
                }
            }
        }
        return cnt;
    }

    public ArrayList<VestingPeriod> getCalcurateVestingByDenom(String denom) {
        ArrayList<VestingPeriod> result = new ArrayList<>();
        for (VestingPeriod vp : getCalcurateVesting()) {
            for (Coin coin : vp.amount) {
                if (coin.denom.equals(denom)) {
                    result.add(vp);
                }
            }
        }
        return result;
    }

    public long getCalcurateTime(String denom, int position) {
        return getCalcurateVestingByDenom(denom).get(position).getLength();
    }

    public BigDecimal getCalcurateAmount(String denom, int position) {
        BigDecimal result = BigDecimal.ZERO;
        VestingPeriod period = getCalcurateVestingByDenom(denom).get(position);
        for (Coin coin : period.amount) {
            if (coin.denom.equals(denom)) {
                result = new BigDecimal(coin.amount);
            }
        }
        return result;
    }

    public BigDecimal getCalcurateVestingAmountSumByDenom(String denom) {
        BigDecimal result = BigDecimal.ZERO;
        for (VestingPeriod vp : getCalcurateVestingByDenom(denom)) {
            for (Coin coin : vp.amount) {
                if (coin.denom.equals(denom)) {
                    result = result.add(new BigDecimal(coin.amount));
                }
            }
        }
        return result;
    }


    public long getUnLockTime(int position) {
        long time = result.value.getStartTime();
        for (int i = 0; i <= position; i++) {
            time = time + result.value.vesting_periods.get(i).getLength();
        }
        return time * 1000;
    }


    public class VestingAccountInfoResult {
        @SerializedName("type")
        public String type;

        @SerializedName("value")
        public VestingAccountInfoValue value;

    }

    public class VestingAccountInfoValue {
        @SerializedName("address")
        public String address;

        @SerializedName("coins")
        public ArrayList<Coin> coins;

        @SerializedName("account_number")
        public String account_number;

        @SerializedName("sequence")
        public String sequence;

        @SerializedName("original_vesting")
        public ArrayList<Coin> original_vesting;

        @SerializedName("delegated_free")
        public ArrayList<Coin> delegated_free;

        @SerializedName("delegated_vesting")
        public ArrayList<Coin> delegated_vesting;

        @SerializedName("start_time")
        public String start_time;

        @SerializedName("end_time")
        public String end_time;

        @SerializedName("vesting_periods")
        public ArrayList<VestingPeriod> vesting_periods;

        public long getStartTime() {
            return Long.parseLong(start_time);
        }

        public long getEndTime() {
            return Long.parseLong(end_time);
        }

    }

    public class VestingPeriod {
        @SerializedName("length")
        public String length;

        @SerializedName("amount")
        public ArrayList<Coin> amount;

        public long getLength() {
            return Long.parseLong(length);
        }
    }
}
