package wannabit.io.cosmostaion.network.res;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;

import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.utils.WLog;

public class ResLcdKavaAccountInfo {
    @SerializedName("height")
    public String height;

    @SerializedName("result")
    public Result result;

    public class Result {
        @SerializedName("type")
        public String type;

        @SerializedName("value")
        public Value value;
    }

    public class Value {
        @SerializedName("address")
        public String address;

        @SerializedName("coins")
        public ArrayList<Coin> coins;

        @SerializedName("account_number")
        public String account_number;

        @SerializedName("sequence")
        public String sequence;

        @SerializedName("PeriodicVestingAccount")
        public PeriodicVestingAccount PeriodicVestingAccount;

        @SerializedName("vesting_period_progress")
        public ArrayList<VestingPeriodProgress> vesting_period_progress;

        @SerializedName("BaseVestingAccount")
        public BaseVestingAccount BaseVestingAccount;

        @SerializedName("vesting_periods")
        public ArrayList<VestingPeriod> vesting_periods;

        @SerializedName("original_vesting")
        public ArrayList<Coin> original_vesting;

        @SerializedName("delegated_vesting")
        public ArrayList<Coin> delegated_vesting;

        @SerializedName("start_time")
        public long start_time;

        @SerializedName("end_time")
        public long end_time;




        public ArrayList<VestingPeriod> getCalcurateVesting() {
            ArrayList<VestingPeriod> result = new ArrayList<>();
            if (vesting_periods != null) {
                long cTime = Calendar.getInstance().getTime().getTime();
                for (int i = 0; i < vesting_periods.size(); i ++) {
                    long unlockTime = getUnLockTime(i);
                    if (cTime < unlockTime) {
                        VestingPeriod temp = new VestingPeriod();
                        temp.length = unlockTime;
                        temp.amount = vesting_periods.get(i).amount;
                        result.add(temp);
                    }
                }
            }
            return result;
        }

        public int getAllCalcurateVestingCnt() {
            return getCalcurateVesting().size();
        }

        public int getCalcurateVestingCntByDenom(String denom) {
            int result = 0;
            for (VestingPeriod vp: getCalcurateVesting()) {
                for (Coin coin: vp.amount) {
                    if (coin.denom.equals(denom)) {
                        result = result + 1;
                    }
                }
            }
            return result;
        }

        public ArrayList<VestingPeriod> getCalcurateVestingByDenom(String denom) {
            ArrayList<VestingPeriod> result = new ArrayList<>();
            for (VestingPeriod vp: getCalcurateVesting()) {
                for (Coin coin: vp.amount) {
                    if (coin.denom.equals(denom)) {
                        result.add(vp);
                    }
                }
            }
            return result;
        }

        public long getCalcurateTime(String denom, int position) {
            return getCalcurateVestingByDenom(denom).get(position).length;
        }

        public BigDecimal getCalcurateAmount(String denom, int position) {
            BigDecimal result = BigDecimal.ZERO;
            VestingPeriod period = getCalcurateVestingByDenom(denom).get(position);
            for (Coin coin: period.amount) {
                if (coin.denom.equals(denom)) {
                    result = new BigDecimal(coin.amount);
                }
            }
            return result;
        }


        public BigDecimal getCalcurateVestingAmountSumByDenom(String denom) {
            BigDecimal result = BigDecimal.ZERO;
            for (VestingPeriod vp: getCalcurateVestingByDenom(denom)) {
                for (Coin coin: vp.amount) {
                    if (coin.denom.equals(denom)) {
                        result = result.add(new BigDecimal(coin.amount));
                    }
                }
            }
            return result;
        }

        public long getUnLockTime(int position) {
            long result = this.start_time;
            for (int i = 0; i <= position; i ++) {
                result = result + vesting_periods.get(i).length;
            }
            return result * 1000;
        }
    }

    public class PeriodicVestingAccount {

        @SerializedName("BaseVestingAccount")
        public BaseVestingAccount BaseVestingAccount;

        @SerializedName("vesting_periods")
        public ArrayList<VestingPeriod> vesting_periods;

    }

    public class BaseVestingAccount {
        @SerializedName("BaseAccount")
        public BaseAccount BaseAccount;

        @SerializedName("original_vesting")
        public ArrayList<Coin> original_vesting;

        @SerializedName("delegated_vesting")
        public ArrayList<Coin> delegated_vesting;

        @SerializedName("end_time")
        public String end_time;
    }

    public class BaseAccount {
        @SerializedName("address")
        public String address;

        @SerializedName("coins")
        public ArrayList<Coin> coins;

        @SerializedName("account_number")
        public String account_number;

        @SerializedName("sequence")
        public String sequence;
    }

    public class VestingPeriod {
        @SerializedName("length")
        public long length;

        @SerializedName("amount")
        public ArrayList<Coin> amount;
    }

    public class VestingPeriodProgress {
        @SerializedName("period_complete")
        public boolean period_complete;

        @SerializedName("vesting_successful")
        public boolean vesting_successful;

    }
}
