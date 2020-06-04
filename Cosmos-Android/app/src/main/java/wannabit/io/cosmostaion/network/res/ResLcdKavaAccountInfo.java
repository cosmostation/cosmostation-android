package wannabit.io.cosmostaion.network.res;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;

import wannabit.io.cosmostaion.model.type.Coin;

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


        public int getCVestingCnt() {
            int result = 0;
            if (vesting_periods != null) {
                long cTime = Calendar.getInstance().getTime().getTime();
                for (VestingPeriod vestingPeriod:vesting_periods) {
                    long unlockTime = (this.start_time + vestingPeriod.length) * 1000;
                    if (cTime < unlockTime) {
                        result = result + 1;
                    }
                }
            }
            return result;
        }

        public BigDecimal getCVestingSum() {
            BigDecimal result = BigDecimal.ZERO;
            if (vesting_periods != null) {
                long cTime = Calendar.getInstance().getTime().getTime();
                for (VestingPeriod vestingPeriod:vesting_periods) {
                    long unlockTime = (this.start_time + vestingPeriod.length) * 1000;
                    if (cTime < unlockTime) {
                        for (Coin coin:vestingPeriod.amount) {
                            result = result.add(new BigDecimal(coin.amount));
                        }
                    }
                }
            }
            return result;
        }

        public ArrayList<VestingPeriod> getCVestingPeriods() {
            ArrayList<VestingPeriod> result = new ArrayList<>();
            if (vesting_periods != null) {
                long cTime = Calendar.getInstance().getTime().getTime();
                for (VestingPeriod vestingPeriod:vesting_periods) {
                    long unlockTime = (this.start_time + vestingPeriod.length) * 1000;
                    if (cTime < unlockTime) {
                        result.add(vestingPeriod);
                    }
                }
            }
            return result;
        }

        public VestingPeriod getCVestingPeriod(int position) {
            return getCVestingPeriods().get(position);
        }

        public BigDecimal getCVestingPeriodAmount(int position) {
            BigDecimal result = BigDecimal.ZERO;
            VestingPeriod vestingPeriod = getCVestingPeriod(position);
            for (Coin coin:vestingPeriod.amount) {
                result = result.add(new BigDecimal(coin.amount));
            }
            return result;
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
