package wannabit.io.cosmostaion.utils;

import android.content.Context;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.dao.Balance;
import wannabit.io.cosmostaion.dao.BondingState;
import wannabit.io.cosmostaion.dao.Reward;
import wannabit.io.cosmostaion.dao.UnBondingState;

import static android.text.Spanned.SPAN_INCLUSIVE_INCLUSIVE;

public class WDp {

    public static SpannableString getDpAmount(Context c, BigDecimal input, int point) {
        SpannableString result;
        BigDecimal amount = input.setScale(point, BigDecimal.ROUND_CEILING);
        result = new SpannableString(getDecimalFormat(c, point).format(amount));
        result.setSpan(new RelativeSizeSpan(0.8f), result.length() - point, result.length(), SPAN_INCLUSIVE_INCLUSIVE);
        return result;
    }

    public static SpannableString getDpDelegatedAmount(Context c, ArrayList<BondingState> bondingStates, String valOpAddr) {
        SpannableString result = new SpannableString("0");
        for(BondingState bond : bondingStates) {
            if(bond.validatorAddress.equals(valOpAddr)) {
                result =  getDpAmount(c, bond.shares, 6);
                break;
            }
        }
        return result;
    }

    public static SpannableString getDpAtomRewardAmount(Context c, ArrayList<Reward> rewards, String valOpAddr) {
        SpannableString result = new SpannableString("0");
        for(Reward reward : rewards) {
            if(reward.validatorAddress.equals(valOpAddr)) {
                result =  getDpAmount(c, reward.getAtomAmount(), 6);
                break;
            }
        }
        return result;
    }

    public static SpannableString getDpAllAtomRewardAmount(Context c, ArrayList<Reward> rewards) {
        BigDecimal sum = BigDecimal.ZERO;
        for(Reward reward : rewards) {
            sum = sum.add(reward.getAtomAmount());
        }
        return getDpAmount(c, sum, 6);
    }


    public static SpannableString getDpAtomBalance(Context c, ArrayList<Balance> balances) {
        SpannableString result = new SpannableString("0");
        for(Balance balance : balances) {
            if(balance.symbol.equals(BaseConstant.COSMOS_ATOM)) {
                result = getDpAmount(c, balance.balance, 6);
            }
        }
        return result;
    }

    public static SpannableString getDpAllDelegatedAmount(Context c, ArrayList<BondingState> bondings) {
        BigDecimal sum = BigDecimal.ZERO;
        for(BondingState bonding : bondings) {
            sum = sum.add(bonding.shares);
        }
        return getDpAmount(c, sum, 6);
    }

    public static SpannableString getDpAllUnbondingAmount(Context c, ArrayList<UnBondingState> unbondings) {
        BigDecimal sum = BigDecimal.ZERO;
        for(UnBondingState unbonding : unbondings) {
            sum = sum.add(unbonding.balance);
        }
        return getDpAmount(c, sum, 6);
    }

    public static SpannableString getDpAllAtom(Context c, ArrayList<Balance> balances, ArrayList<BondingState> bondings, ArrayList<UnBondingState> unbondings, ArrayList<Reward> rewards) {
        BigDecimal sum = BigDecimal.ZERO;
        for(Balance balance : balances) {
            if(balance.symbol.equals(BaseConstant.COSMOS_ATOM)) {
                sum = sum.add(balance.balance);
            }
        }
        for(BondingState bonding : bondings) {
            sum = sum.add(bonding.shares);
        }
        for(UnBondingState unbonding : unbondings) {
            sum = sum.add(unbonding.balance);
        }
        for(Reward reward : rewards) {
            sum = sum.add(reward.getAtomAmount());
        }
        return getDpAmount(c, sum, 6);
    }






    public static SpannableString getDpPhotonBalance(Context c, ArrayList<Balance> balances) {
        SpannableString result = new SpannableString("0");
        for(Balance balance : balances) {
            if(balance.symbol.equals(BaseConstant.COSMOS_PHOTON)) {
                result = getDpAmount(c, balance.balance, 6);
            }
        }
        return result;
    }

    public static SpannableString getDpAllPhotonRewardAmount(Context c, ArrayList<Reward> rewards) {
        BigDecimal sum = BigDecimal.ZERO;
        for(Reward reward : rewards) {
            sum = sum.add(reward.getPhotonAmount());
        }
        return getDpAmount(c, sum, 6);
    }

    public static SpannableString getDpAllPhoton(Context c, ArrayList<Balance> balances, ArrayList<Reward> rewards) {
        BigDecimal sum = BigDecimal.ZERO;
        for(Balance balance : balances) {
            if(balance.symbol.equals(BaseConstant.COSMOS_PHOTON)) {
                sum = sum.add(balance.balance);
            }
        }
        for(Reward reward : rewards) {
            sum = sum.add(reward.getPhotonAmount());
        }
        return getDpAmount(c, sum, 6);
    }



























    public static DecimalFormat getDecimalFormat(Context c, int cnt) {
        DecimalFormat decimalformat = null;
        switch (cnt) {
            case 0:
                decimalformat = new DecimalFormat(c.getString(R.string.str_decimal_pattern_0));
                break;
            case 1:
                decimalformat = new DecimalFormat(c.getString(R.string.str_decimal_pattern_1));
                break;
            case 2:
                decimalformat = new DecimalFormat(c.getString(R.string.str_decimal_pattern_2));
                break;
            case 3:
                decimalformat = new DecimalFormat(c.getString(R.string.str_decimal_pattern_3));
                break;
            case 4:
                decimalformat = new DecimalFormat(c.getString(R.string.str_decimal_pattern_4));
                break;
            case 5:
                decimalformat = new DecimalFormat(c.getString(R.string.str_decimal_pattern_5));
                break;
            case 6:
                decimalformat = new DecimalFormat(c.getString(R.string.str_decimal_pattern_6));
                break;
            case 7:
                decimalformat = new DecimalFormat(c.getString(R.string.str_decimal_pattern_7));
                break;
            case 8:
                decimalformat = new DecimalFormat(c.getString(R.string.str_decimal_pattern_8));
                break;
            case 9:
                decimalformat = new DecimalFormat(c.getString(R.string.str_decimal_pattern_9));
                break;
            case 10:
                decimalformat = new DecimalFormat(c.getString(R.string.str_decimal_pattern_10));
                break;
            case 11:
                decimalformat = new DecimalFormat(c.getString(R.string.str_decimal_pattern_11));
                break;
            case 12:
                decimalformat = new DecimalFormat(c.getString(R.string.str_decimal_pattern_12));
                break;
            case 13:
                decimalformat = new DecimalFormat(c.getString(R.string.str_decimal_pattern_13));
                break;
            case 14:
                decimalformat = new DecimalFormat(c.getString(R.string.str_decimal_pattern_14));
                break;
            case 15:
                decimalformat = new DecimalFormat(c.getString(R.string.str_decimal_pattern_15));
                break;
            case 16:
                decimalformat = new DecimalFormat(c.getString(R.string.str_decimal_pattern_16));
                break;
            case 17:
                decimalformat = new DecimalFormat(c.getString(R.string.str_decimal_pattern_17));
                break;
            case 18:
                decimalformat = new DecimalFormat(c.getString(R.string.str_decimal_pattern_18));
                break;

            default:
                decimalformat = new DecimalFormat(c.getString(R.string.str_decimal_pattern_6));
                break;
        }
        return decimalformat;
    }
}
