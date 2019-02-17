package wannabit.io.cosmostaion.utils;

import android.content.Context;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.dao.Balance;
import wannabit.io.cosmostaion.dao.BondingState;
import wannabit.io.cosmostaion.dao.Reward;
import wannabit.io.cosmostaion.dao.TotalReward;
import wannabit.io.cosmostaion.dao.UnBondingState;
import wannabit.io.cosmostaion.model.type.Coin;

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
        BigDecimal sum = BigDecimal.ZERO;
        for(BondingState bond : bondingStates) {
            if(bond.validatorAddress.equals(valOpAddr)) {
                sum = bond.shares;
                break;
            }
        }
        return getDpAmount(c, sum, 6);

    }

    public static SpannableString getDpAtomRewardAmount(Context c, ArrayList<Reward> rewards, String valOpAddr) {
        BigDecimal sum = BigDecimal.ZERO;
        for(Reward reward : rewards) {
            if(reward.validatorAddress.equals(valOpAddr)) {
                sum = reward.getAtomAmount();
                break;
            }
        }
        return getDpAmount(c, sum, 6);
    }

    public static SpannableString getDpAllAtomRewardAmount(Context c, ArrayList<Reward> rewards) {
        BigDecimal sum = BigDecimal.ZERO;
        for(Reward reward : rewards) {
            sum = sum.add(reward.getAtomAmount());
        }
        return getDpAmount(c, sum, 6);
    }


    public static SpannableString getDpAtomBalance(Context c, ArrayList<Balance> balances) {
        BigDecimal sum = BigDecimal.ZERO;
        for(Balance balance : balances) {
            if(balance.symbol.equals(BaseConstant.COSMOS_ATOM)) {
                sum = balance.balance;
            }
        }
        return getDpAmount(c, sum, 6);
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

    public static SpannableString getDpAllAtom2(Context c, ArrayList<Balance> balances, ArrayList<BondingState> bondings, ArrayList<UnBondingState> unbondings, TotalReward totalReward) {
        BigDecimal sum = BigDecimal.ZERO;
        if(balances != null) {
            for(Balance balance : balances) {
                if(balance.symbol.equals(BaseConstant.COSMOS_ATOM)) {
                    sum = sum.add(balance.balance);
                }
            }
        }
        if(bondings != null) {
            for(BondingState bonding : bondings) {
                sum = sum.add(bonding.shares);
            }
        }
        if(unbondings != null) {
            for(UnBondingState unbonding : unbondings) {
                sum = sum.add(unbonding.balance);
            }
        }
        if(totalReward != null && totalReward.coins != null) {
            for(Coin coin : totalReward.coins) {
                if(coin.denom.equals(BaseConstant.COSMOS_ATOM)) {
                    sum = sum.add(new BigDecimal(coin.amount));
                    break;
                }
            }
        }
        return getDpAmount(c, sum, 6);
    }






    public static SpannableString getDpPhotonBalance(Context c, ArrayList<Balance> balances) {
        BigDecimal sum = BigDecimal.ZERO;
        for(Balance balance : balances) {
            if(balance.symbol.equals(BaseConstant.COSMOS_PHOTON)) {
                sum = balance.balance;
            }
        }
        return getDpAmount(c, sum, 6);

    }

    public static SpannableString getDpAllPhotonRewardAmount(Context c, ArrayList<Reward> rewards) {
        BigDecimal sum = BigDecimal.ZERO;
        for(Reward reward : rewards) {
            sum = sum.add(reward.getPhotonAmount());
        }
        return getDpAmount(c, sum, 6);
    }

    public static SpannableString getDpPhotonRewardAmount(Context c, ArrayList<Reward> rewards, String valOpAddr) {
        BigDecimal sum = BigDecimal.ZERO;
        for(Reward reward : rewards) {
            if(reward.validatorAddress.equals(valOpAddr)) {
                sum = reward.getPhotonAmount();
                break;
            }
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

    public static SpannableString getDpAllPhoton2(Context c, ArrayList<Balance> balances, TotalReward totalReward) {
        BigDecimal sum = BigDecimal.ZERO;
        if(balances != null) {
            for(Balance balance : balances) {
                if(balance.symbol.equals(BaseConstant.COSMOS_PHOTON)) {
                    sum = sum.add(balance.balance);
                }
            }
        }
        if(totalReward != null && totalReward.coins != null) {
            for(Coin coin : totalReward.coins) {
                if(coin.denom.equals(BaseConstant.COSMOS_PHOTON)) {
                    sum = sum.add(new BigDecimal(coin.amount));
                    break;
                }
            }
        }
        return getDpAmount(c, sum, 6);
    }






    public static SpannableString getDpTotalAtomAmount(Context c, HashMap<Long, ArrayList<Balance>> balanceHashMap,
                                                       HashMap<Long, ArrayList<BondingState>> bondingHashMap,
                                                       HashMap<Long, ArrayList<UnBondingState>> unbondingHashMap,
                                                       HashMap<Long, TotalReward> rewardHashMap) {
        BigDecimal sum = BigDecimal.ZERO;
        if(balanceHashMap != null) {
            for (long key : balanceHashMap.keySet() ) {
                for(Balance balance : balanceHashMap.get(key)) {
                    if(balance.symbol.equals(BaseConstant.COSMOS_ATOM)) {
                        sum = sum.add(balance.balance);
                    }
                }
            }
        }
        if(balanceHashMap != null) {
            for (long key : balanceHashMap.keySet() ) {
                for(BondingState bonding : bondingHashMap.get(key)) {
                    sum = sum.add(bonding.shares);
                }
            }
        }
        if(unbondingHashMap != null) {
            for (long key : unbondingHashMap.keySet() ) {
                for(UnBondingState unbonding : unbondingHashMap.get(key)) {
                    sum = sum.add(unbonding.balance);
                }
            }
        }
        if(rewardHashMap != null) {
            for (long key : rewardHashMap.keySet() ) {
                for(Coin coin : rewardHashMap.get(key).coins) {
                    if(coin.denom.equals(BaseConstant.COSMOS_ATOM)) {
                        sum = sum.add(new BigDecimal(coin.amount));
                    }
                }
            }
        }
        return getDpAmount(c, sum, 6);
    }

    public static SpannableString getDpTotalPhotonAmount(Context c, HashMap<Long, ArrayList<Balance>> balanceHashMap,
                                                       HashMap<Long, TotalReward> rewardHashMap) {
        BigDecimal sum = BigDecimal.ZERO;
        if(balanceHashMap != null) {
            for (long key : balanceHashMap.keySet() ) {
                for(Balance balance : balanceHashMap.get(key)) {
                    if(balance.symbol.equals(BaseConstant.COSMOS_PHOTON)) {
                        sum = sum.add(balance.balance);
                    }
                }
            }
        }
        if(rewardHashMap != null) {
            for (long key : rewardHashMap.keySet() ) {
                for(Coin coin : rewardHashMap.get(key).coins) {
                    if(coin.denom.equals(BaseConstant.COSMOS_PHOTON)) {
                        sum = sum.add(new BigDecimal(coin.amount));
                    }
                }
            }
        }
        return getDpAmount(c, sum, 6);
    }








    public static String getSelfBondRate(String total, String self) {
        BigDecimal result = new BigDecimal(self).multiply(new BigDecimal("100")).divide(new BigDecimal(total), 2, RoundingMode.DOWN);
        return  result.toPlainString()+"%";
    }

    public static String getCommissionRate(String rate) {
        BigDecimal result = new BigDecimal(rate).multiply(new BigDecimal("100")).setScale(2, RoundingMode.DOWN);
        return  result.toPlainString()+"%";
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
