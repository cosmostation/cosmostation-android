package wannabit.io.cosmostaion.utils;

import android.content.Context;
import android.content.res.ColorStateList;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;
import android.widget.TextView;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.TimeZone;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.dao.Balance;
import wannabit.io.cosmostaion.dao.BondingState;
import wannabit.io.cosmostaion.dao.Reward;
import wannabit.io.cosmostaion.dao.TotalReward;
import wannabit.io.cosmostaion.dao.UnBondingState;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.model.type.Input;
import wannabit.io.cosmostaion.model.type.Msg;
import wannabit.io.cosmostaion.model.type.Output;
import wannabit.io.cosmostaion.model.type.Validator;
import wannabit.io.cosmostaion.network.res.ResLcdIrisPool;
import wannabit.io.cosmostaion.network.res.ResLcdIrisReward;

import static android.text.Spanned.SPAN_INCLUSIVE_INCLUSIVE;
import static wannabit.io.cosmostaion.base.BaseConstant.IS_TEST;

public class WDp {

    public static SpannableString getDpAmount(Context c, BigDecimal input, int point, BaseChain chain) {
        SpannableString result;
        BigDecimal amount = input.setScale(point, BigDecimal.ROUND_DOWN);
        if (chain.equals(BaseChain.COSMOS_MAIN)) {
            amount = amount.divide(new BigDecimal("1000000"), 6, BigDecimal.ROUND_DOWN);
            result = new SpannableString(getDecimalFormat(c, point).format(amount));
            result.setSpan(new RelativeSizeSpan(0.8f), result.length() - point, result.length(), SPAN_INCLUSIVE_INCLUSIVE);

        } else if (chain.equals(BaseChain.IRIS_MAIN)) {
            amount = amount.divide(new BigDecimal("1000000000000000000"), 18, BigDecimal.ROUND_DOWN);
            result = new SpannableString(getDecimalFormat(c, point).format(amount));
            result.setSpan(new RelativeSizeSpan(0.8f), result.length() - point, result.length(), SPAN_INCLUSIVE_INCLUSIVE);

        } else {
            result = new SpannableString(getDecimalFormat(c, 0).format(amount));

        }
        return result;
    }

    public static SpannableString getDpString(String input, int point) {
        SpannableString result;
        result = new SpannableString(input);
        result.setSpan(new RelativeSizeSpan(0.8f), result.length() - point, result.length(), SPAN_INCLUSIVE_INCLUSIVE);
        return result;
    }

    public static SpannableString getDpAtomRewardAmount(Context c, ArrayList<Reward> rewards, String valOpAddr, BaseChain chain) {
        BigDecimal sum = BigDecimal.ZERO;
        for(Reward reward : rewards) {
            if(reward.validatorAddress.equals(valOpAddr)) {
                sum = reward.getAtomAmount();
                break;
            }
        }
        return getDpAmount(c, sum, 6, chain);
    }

    public static SpannableString getDpAllAtomRewardAmount(Context c, ArrayList<Reward> rewards, BaseChain chain) {
        BigDecimal sum = BigDecimal.ZERO;
        for(Reward reward : rewards) {
            sum = sum.add(reward.getAtomAmount().setScale(0, BigDecimal.ROUND_DOWN));
        }
        return getDpAmount(c, sum, 6, chain);
    }

    public static SpannableString getDpAllIrisRewardAmount(Context c, ResLcdIrisReward rewards, BaseChain chain) {
        return getDpAmount(c, rewards.getSimpleIrisReward(), 6, chain);
    }

    public static SpannableString getValidatorReward(Context c, ArrayList<Reward> rewards, String valOpAddress, BaseChain chain) {
        BigDecimal result = BigDecimal.ZERO;
        for(Reward reward : rewards) {
            if(reward.validatorAddress.equals(valOpAddress)) {
                result = reward.getAtomAmount();
                break;
            }
        }
        return getDpAmount(c, result, 6, chain);
    }

    public static SpannableString getIrisValidatorReward(Context c, ResLcdIrisReward reward, String valOpAddress, BaseChain chain) {
        return getDpAmount(c, reward.getPerValReward(valOpAddress), 6, chain);
    }

    public static BigDecimal getValidatorReward(ArrayList<Reward> rewards, String valOpAddress) {
        BigDecimal result = BigDecimal.ZERO;
        for(Reward reward : rewards) {
            if(reward.validatorAddress.equals(valOpAddress)) {
                result = reward.getAtomAmount();
                break;
            }
        }
        return result;
    }

    public static BigDecimal getYield(BigDecimal bonded, BigDecimal provision, BigDecimal commission) {
        BigDecimal result = BigDecimal.ZERO;
        try {
            result = provision.multiply(BigDecimal.ONE.subtract(commission)).multiply(new BigDecimal("100")).divide(bonded, 2, RoundingMode.HALF_UP);

        }catch (Exception e) {}
        return result;
    }

    public static SpannableString getYieldString(BigDecimal bonded, BigDecimal provision, BigDecimal commission) {
        BigDecimal result = BigDecimal.ZERO;
        try {
            result = provision.multiply(BigDecimal.ONE.subtract(commission)).multiply(new BigDecimal("100")).divide(bonded, 2, RoundingMode.HALF_UP);

        }catch (Exception e) {}
        return getPercentDp(result);
    }

    public static SpannableString getIrisYieldString(ResLcdIrisPool pool, BigDecimal commission) {
        BigDecimal result = BigDecimal.ZERO;
        if(pool != null) {
            try {
                result = pool.geTotal().multiply(new BigDecimal(0.04)).multiply(BigDecimal.ONE.subtract(commission)).multiply(new BigDecimal("100")).divide(pool.getBonded(), 2, RoundingMode.HALF_UP);

            }catch (Exception e) {}
        }
        return getPercentDp(result);
    }

    public static BigDecimal getDailyReturn(BigDecimal bonded, BigDecimal provision, BigDecimal commission, BigDecimal delegated) {
        BigDecimal result = BigDecimal.ZERO;
        try {
            result = provision.multiply(BigDecimal.ONE.subtract(commission)).multiply(delegated).divide(bonded.multiply(new BigDecimal("365000000")), 12, RoundingMode.HALF_UP);

        }catch (Exception e) {}
        return result;
    }

    public static SpannableString getDailyReturn(Context c, BigDecimal bonded, BigDecimal provision, BigDecimal commission, BigDecimal delegated) {
        BigDecimal value = BigDecimal.ZERO;
        try {
            value = provision.multiply(BigDecimal.ONE.subtract(commission)).multiply(delegated).divide(bonded.multiply(new BigDecimal("365000000")), 12, RoundingMode.HALF_UP);

        }catch (Exception e) {}

        SpannableString result;
        result = new SpannableString(getDecimalFormat(c, 12).format(value));
        result.setSpan(new RelativeSizeSpan(0.8f), result.length() - 12, result.length(), SPAN_INCLUSIVE_INCLUSIVE);
        return result;
    }


    public static BigDecimal getMonthlyReturn(BigDecimal bonded, BigDecimal provision, BigDecimal commission, BigDecimal delegated) {
        BigDecimal result = BigDecimal.ZERO;
        try {
            result = provision.multiply(BigDecimal.ONE.subtract(commission)).multiply(delegated).divide(bonded.multiply(new BigDecimal("12000000")), 12, RoundingMode.HALF_UP);

        }catch (Exception e) {}
        return result;
    }

    public static SpannableString getMonthlyReturn(Context c, BigDecimal bonded, BigDecimal provision, BigDecimal commission, BigDecimal delegated) {
        BigDecimal value = BigDecimal.ZERO;
        try {
            value = provision.multiply(BigDecimal.ONE.subtract(commission)).multiply(delegated).divide(bonded.multiply(new BigDecimal("12000000")), 12, RoundingMode.HALF_UP);

        }catch (Exception e) {}
        SpannableString result;
        result = new SpannableString(getDecimalFormat(c, 12).format(value));
        result.setSpan(new RelativeSizeSpan(0.8f), result.length() - 12, result.length(), SPAN_INCLUSIVE_INCLUSIVE);
        return result;
    }

    public static SpannableString getIrisDailyReturn(Context c, ResLcdIrisPool pool, BigDecimal commission, BigDecimal delegated) {
        BigDecimal value = BigDecimal.ZERO;
        try {
            value = pool.geTotal().multiply(new BigDecimal(0.04)).multiply(BigDecimal.ONE.subtract(commission)).multiply(delegated).divide(pool.getBonded().multiply(new BigDecimal("365000000000000000000")), 18, RoundingMode.HALF_UP);

        }catch (Exception e) {}

        SpannableString result;
        result = new SpannableString(getDecimalFormat(c, 18).format(value));
        result.setSpan(new RelativeSizeSpan(0.8f), result.length() - 18, result.length(), SPAN_INCLUSIVE_INCLUSIVE);
        return result;
    }

    public static SpannableString getIrisMonthlyReturn(Context c, ResLcdIrisPool pool, BigDecimal commission, BigDecimal delegated) {
        BigDecimal value = BigDecimal.ZERO;
        try {
            value = pool.geTotal().multiply(new BigDecimal(0.04)).multiply(BigDecimal.ONE.subtract(commission)).multiply(delegated).divide(pool.getBonded().multiply(new BigDecimal("12000000000000000000")), 18, RoundingMode.HALF_UP);

        }catch (Exception e) {}
        SpannableString result;
        result = new SpannableString(getDecimalFormat(c, 18).format(value));
        result.setSpan(new RelativeSizeSpan(0.8f), result.length() - 18, result.length(), SPAN_INCLUSIVE_INCLUSIVE);
        return result;
    }

    public static SpannableString getDpBalance(Context c, ArrayList<Balance> balances, BaseChain chain) {
        BigDecimal sum = BigDecimal.ZERO;
        if (chain.equals(BaseChain.COSMOS_MAIN)) {
            for(Balance balance : balances) {
                if(balance.symbol.equals(BaseConstant.COSMOS_ATOM)) {
                    sum = balance.balance;
                }
            }
            return getDpAmount(c, sum, 6, chain);

        } else if (chain.equals(BaseChain.IRIS_MAIN)) {
            for(Balance balance : balances) {
                if(balance.symbol.equals(BaseConstant.COSMOS_IRIS_ATTO)) {
                    sum = balance.balance;
                }
            }
            return getDpAmount(c, sum, 6, chain);

        } else {
            return getDpAmount(c, sum, 6, chain);

        }
    }

    public static SpannableString getDpBalanceCoin(Context c, ArrayList<Balance> balances, BaseChain chain, String denom) {
        BigDecimal sum = BigDecimal.ZERO;
        for(Balance balance : balances) {
            if(balance.symbol.equals(denom)) {
                sum = balance.balance;
            }
        }
        return getDpAmount(c, sum, 6, chain);
    }

    public static SpannableString getDpAllDelegatedAmount(Context c, ArrayList<BondingState> bondings, ArrayList<Validator> validators,  BaseChain chain) {
        BigDecimal sum = BigDecimal.ZERO;
        if (chain.equals(BaseChain.COSMOS_MAIN)) {
            for(BondingState bonding : bondings) {
                sum = sum.add(bonding.getBondingAmount(selectValidator(validators, bonding.validatorAddress)));
            }
            return getDpAmount(c, sum, 6, chain);

        } else if (chain.equals(BaseChain.IRIS_MAIN)) {
            for(BondingState bonding : bondings) {
                sum = sum.add(bonding.shares);
            }
            return getDpAmount(c, sum, 6, chain);
        } else {
            return getDpAmount(c, sum, 6, chain);

        }
    }

    public static Validator selectValidator(ArrayList<Validator> validators, String opAddress) {
        Validator result = null;
        for (Validator val:validators) {
            if(val.operator_address.equals(opAddress)) {
                result = val;
                break;
            }
        }
        return result;
    }

    public static SpannableString getDpAllUnbondingAmount(Context c, ArrayList<UnBondingState> unbondings, ArrayList<Validator> validators, BaseChain chain) {
        BigDecimal sum = BigDecimal.ZERO;
        for(UnBondingState unbonding : unbondings) {
            sum = sum.add(unbonding.balance);
        }
        return getDpAmount(c, sum, 6, chain);
    }

    public static SpannableString getDpAllAtom(Context c, ArrayList<Balance> balances, ArrayList<BondingState> bondings, ArrayList<UnBondingState> unbondings, ArrayList<Reward> rewards, ArrayList<Validator> validators, BaseChain chain) {
        BigDecimal sum = BigDecimal.ZERO;
        for(Balance balance : balances) {
            if (IS_TEST || balance.symbol.equals(BaseConstant.COSMOS_MUON)) {
                sum = sum.add(balance.balance);
            } else if (!IS_TEST || balance.symbol.equals(BaseConstant.COSMOS_ATOM)) {
                sum = sum.add(balance.balance);
            }
        }
        for(BondingState bonding : bondings) {
            sum = sum.add(bonding.getBondingAmount(selectValidator(validators, bonding.validatorAddress)));
        }
        for(UnBondingState unbonding : unbondings) {
            sum = sum.add(unbonding.balance);
        }
        for(Reward reward : rewards) {
            sum = sum.add(reward.getAtomAmount());
        }
        return getDpAmount(c, sum, 6, chain);
    }

    public static BigDecimal getAllAtom(ArrayList<Balance> balances, ArrayList<BondingState> bondings, ArrayList<UnBondingState> unbondings, ArrayList<Reward> rewards, ArrayList<Validator> validators) {
        BigDecimal sum = BigDecimal.ZERO;
        for(Balance balance : balances) {
            if(balance.symbol.equals(BaseConstant.COSMOS_ATOM) || balance.symbol.equals(BaseConstant.COSMOS_MUON)) {
                sum = sum.add(balance.balance);
            }
        }
        for(BondingState bonding : bondings) {
            sum = sum.add(bonding.getBondingAmount(selectValidator(validators, bonding.validatorAddress)));
        }
        for(UnBondingState unbonding : unbondings) {
            sum = sum.add(unbonding.balance);
        }
        for(Reward reward : rewards) {
            sum = sum.add(reward.getAtomAmount());
        }
        return sum;
    }

    public static SpannableString getDpAllAtom2(Context c, ArrayList<Balance> balances, ArrayList<BondingState> bondings, ArrayList<UnBondingState> unbondings, TotalReward totalReward, ArrayList<Validator> validators, BaseChain chain) {
        BigDecimal sum = BigDecimal.ZERO;
        if(balances != null) {
            for(Balance balance : balances) {
                if(balance.symbol.equals(BaseConstant.COSMOS_ATOM) || balance.symbol.equals(BaseConstant.COSMOS_MUON)) {
                    sum = sum.add(balance.balance);
                }
            }
        }
        for(BondingState bonding : bondings) {
            sum = sum.add(bonding.getBondingAmount(selectValidator(validators, bonding.validatorAddress)));
        }
        if(unbondings != null) {
            for(UnBondingState unbonding : unbondings) {
                sum = sum.add(unbonding.balance);
            }
        }
        if(totalReward != null && totalReward.coins != null) {
            for(Coin coin : totalReward.coins) {
                if(coin.denom.equals(BaseConstant.COSMOS_ATOM) || coin.denom.equals(BaseConstant.COSMOS_MUON)) {
                    sum = sum.add(new BigDecimal(coin.amount));
                    break;
                }
            }
        }
        return getDpAmount(c, sum, 6, chain);
    }

    public static SpannableString getDpAllIris(Context c, ArrayList<Balance> balances, ArrayList<BondingState> bondings, ArrayList<UnBondingState> unbondings, ResLcdIrisReward reward, BaseChain chain) {
        BigDecimal sum = BigDecimal.ZERO;
        for(Balance balance : balances) {
            if(balance.symbol.equals(BaseConstant.COSMOS_IRIS_ATTO)) {
                sum = sum.add(balance.balance);
            }
        }

        for(BondingState bonding : bondings) {
            sum = sum.add(bonding.shares);
        }

        for(UnBondingState unbonding : unbondings) {
            sum = sum.add(unbonding.balance);
        }

        if(reward != null) {
            sum = sum.add(reward.getSimpleIrisReward());
        }
        return getDpAmount(c, sum, 6, chain);
    }

    public static BigDecimal getAllIris(ArrayList<Balance> balances, ArrayList<BondingState> bondings, ArrayList<UnBondingState> unbondings, ResLcdIrisReward reward) {
        BigDecimal sum = BigDecimal.ZERO;
        for(Balance balance : balances) {
            if(balance.symbol.equals(BaseConstant.COSMOS_IRIS_ATTO)) {
                sum = sum.add(balance.balance);
            }
        }
        for(BondingState bonding : bondings) {
            sum = sum.add(bonding.shares);
        }
        for(UnBondingState unbonding : unbondings) {
            sum = sum.add(unbonding.balance);
        }
        if(reward != null) {
            sum = sum.add(reward.getSimpleIrisReward());
        }
        return sum;
    }




    public static SpannableString getDpPhotonBalance(Context c, ArrayList<Balance> balances, BaseChain chain) {
        BigDecimal sum = BigDecimal.ZERO;
        for(Balance balance : balances) {
            if(balance.symbol.equals(BaseConstant.COSMOS_PHOTON) || balance.symbol.equals(BaseConstant.COSMOS_PHOTINO)) {
                sum = balance.balance;
            }
        }
        return getDpAmount(c, sum, 6, chain);

    }

//    public static SpannableString getDpAllPhotonRewardAmount(Context c, ArrayList<Reward> rewards, BaseChain chain) {
//        BigDecimal sum = BigDecimal.ZERO;
//        for(Reward reward : rewards) {
//            sum = sum.add(reward.getPhotonAmount());
//        }
//        return getDpAmount(c, sum, 6, chain);
//    }
//
//    public static SpannableString getDpPhotonRewardAmount(Context c, ArrayList<Reward> rewards, String valOpAddr, BaseChain chain) {
//        BigDecimal sum = BigDecimal.ZERO;
//        for(Reward reward : rewards) {
//            if(reward.validatorAddress.equals(valOpAddr)) {
//                sum = reward.getPhotonAmount();
//                break;
//            }
//        }
//        return getDpAmount(c, sum, 6, chain);
//    }
//
//    public static SpannableString getDpAllPhoton(Context c, ArrayList<Balance> balances, ArrayList<Reward> rewards, BaseChain chain) {
//        BigDecimal sum = BigDecimal.ZERO;
//        for(Balance balance : balances) {
//            if(balance.symbol.equals(BaseConstant.COSMOS_PHOTON)|| balance.symbol.equals(BaseConstant.COSMOS_PHOTINO)) {
//                sum = sum.add(balance.balance);
//            }
//        }
//        for(Reward reward : rewards) {
//            sum = sum.add(reward.getPhotonAmount());
//        }
//        return getDpAmount(c, sum, 6, chain);
//    }

    public static SpannableString getDpAllPhoton2(Context c, ArrayList<Balance> balances, TotalReward totalReward, BaseChain chain) {
        BigDecimal sum = BigDecimal.ZERO;
        if(balances != null) {
            for(Balance balance : balances) {
                if(balance.symbol.equals(BaseConstant.COSMOS_PHOTON)|| balance.symbol.equals(BaseConstant.COSMOS_PHOTINO)) {
                    sum = sum.add(balance.balance);
                }
            }
        }
        if(totalReward != null && totalReward.coins != null) {
            for(Coin coin : totalReward.coins) {
                if(coin.denom.equals(BaseConstant.COSMOS_PHOTON)|| coin.denom.equals(BaseConstant.COSMOS_PHOTINO)) {
                    sum = sum.add(new BigDecimal(coin.amount));
                    break;
                }
            }
        }
        return getDpAmount(c, sum, 6, chain);
    }

    public static SpannableString getDpTotalPhotonAmount(Context c, HashMap<Long, ArrayList<Balance>> balanceHashMap,
                                                       HashMap<Long, TotalReward> rewardHashMap,
                                                         BaseChain chain) {
        BigDecimal sum = BigDecimal.ZERO;
        if(balanceHashMap != null) {
            for (long key : balanceHashMap.keySet() ) {
                for(Balance balance : balanceHashMap.get(key)) {
                    if(balance.symbol.equals(BaseConstant.COSMOS_PHOTON) || balance.symbol.equals(BaseConstant.COSMOS_PHOTINO)) {
                        sum = sum.add(balance.balance);
                    }
                }
            }
        }
        if(rewardHashMap != null) {
            for (long key : rewardHashMap.keySet() ) {
                for(Coin coin : rewardHashMap.get(key).coins) {
                    if(coin.denom.equals(BaseConstant.COSMOS_PHOTON) || coin.denom.equals(BaseConstant.COSMOS_PHOTINO)) {
                        sum = sum.add(new BigDecimal(coin.amount));
                    }
                }
            }
        }
        return getDpAmount(c, sum, 6, chain);
    }



//    public static String getDolor(Context c, BigDecimal input) {
//        DecimalFormat df = getDecimalFormat(c, 2);
//        return df.format(input);
//    }
//
//    public static SpannableString getDolorDp(BigDecimal input) {
//        return getDpString("$ " + input.setScale(2, RoundingMode.DOWN).toPlainString(), 2);
//    }

    public static SpannableString getPriceDp(Context c, BigDecimal input, String symbol, int currency) {
        if (currency == 5) {
            SpannableString result;
            result = new SpannableString(symbol + " " +getDecimalFormat(c, 8).format(input));
            result.setSpan(new RelativeSizeSpan(0.8f), result.length() - 8, result.length(), SPAN_INCLUSIVE_INCLUSIVE);
            return result;

        } else {
            SpannableString result;
            result = new SpannableString(symbol + " " +getDecimalFormat(c, 2).format(input));
            result.setSpan(new RelativeSizeSpan(0.8f), result.length() - 2, result.length(), SPAN_INCLUSIVE_INCLUSIVE);
            return result;

        }
    }

    public static SpannableString getPriceApproximatelyDp(Context c, BigDecimal input, String symbol, int currency) {
        if (currency == 5) {
            SpannableString result;
            result = new SpannableString(c.getString(R.string.str_approximately)+ " " + symbol + " " +getDecimalFormat(c, 8).format(input));
            result.setSpan(new RelativeSizeSpan(0.8f), result.length() - 8, result.length(), SPAN_INCLUSIVE_INCLUSIVE);
            return result;

        } else {
            SpannableString result;
            result = new SpannableString(c.getString(R.string.str_approximately)+ " " + symbol + " " +getDecimalFormat(c, 2).format(input));
            result.setSpan(new RelativeSizeSpan(0.8f), result.length() - 2, result.length(), SPAN_INCLUSIVE_INCLUSIVE);
            return result;

        }
    }


    public static SpannableString getPriceUpDown(BigDecimal input) {
        return getDpString(input.setScale(2, RoundingMode.DOWN).toPlainString() + "% (24h)", 9);
    }

    public static SpannableString getPercentDp(BigDecimal input) {
        return getDpString(input.setScale(2, RoundingMode.DOWN).toPlainString() + "%", 3);
    }


    public static SpannableString getSelfBondRate(String total, String self) {
        BigDecimal result = new BigDecimal(self).multiply(new BigDecimal("100")).divide(new BigDecimal(total), 2, RoundingMode.DOWN);
        return  getPercentDp(result);
    }

    public static SpannableString getCommissionRate(String rate) {
        BigDecimal result = new BigDecimal(rate).multiply(new BigDecimal("100")).setScale(2, RoundingMode.DOWN);
        return getPercentDp(result);
    }

    public static BigDecimal getCommissionRateDecimal(String rate) {
        return new BigDecimal(rate).setScale(2, RoundingMode.DOWN);
    }

    public static int getCommisionColor(String rateS) {
        int result = R.color.colorGray1;
        float rate = Float.parseFloat(rateS);
        if(rate > 0.1999f) {
            result = R.color.colorCommision4;
            return result;
        }
//        if(rate > 0.15f) {
//            result = R.color.colorCommision3;
//            return result;
//        }
//        if(rate > 0.121f) {
//            result = R.color.colorCommision3;
//            return result;
//        }
        return result;
    }

    public static BigDecimal uAtomToAtom(BigDecimal uatom) {
        return uatom.divide(new BigDecimal("1000000"), 6, RoundingMode.DOWN);
    }

    public static BigDecimal atomToUatom(BigDecimal atom) {
        return atom.multiply(new BigDecimal("1000000"));
    }

    public static BigDecimal attoToIris(BigDecimal atto) {
        return atto.divide(new BigDecimal("1000000000000000000"), 18, RoundingMode.DOWN);
    }





    public static int getHistoryDpType(ArrayList<Msg> msgs, String address) {
        int result = BaseConstant.TX_TYPE_UNKNOWN;
        if (msgs == null || msgs.size() <= 0)
            return result;

        if (msgs != null && msgs.size() == 2) {
            if (msgs.get(0).type.equals(BaseConstant.COSMOS_MSG_TYPE_WITHDRAW_DEL) &&
                    msgs.get(1).type.equals(BaseConstant.COSMOS_MSG_TYPE_DELEGATE)) {
                return BaseConstant.TX_TYPE_REINVEST;
            }

            if (msgs.get(0).type.equals(BaseConstant.IRIS_MSG_TYPE_WITHDRAW) &&
                    msgs.get(1).type.equals(BaseConstant.IRIS_MSG_TYPE_DELEGATE)) {
                return BaseConstant.TX_TYPE_REINVEST;
            }
        }

        Msg msg = msgs.get(0);
        if (msg.type.equals(BaseConstant.COSMOS_MSG_TYPE_TRANSFER) ||
                msg.type.equals(BaseConstant.COSMOS_MSG_TYPE_TRANSFER2) ||
                msg.type.equals(BaseConstant.IRIS_MSG_TYPE_TRANSFER) ) {
            if (msg.value.from_address != null && msg.value.from_address.equals(address)) {
                result = BaseConstant.TX_TYPE_SEND;
            } else if (msg.value.to_address != null && msg.value.to_address.equals(address)) {
                result = BaseConstant.TX_TYPE_RECEIVE;
            } else {
                if (msg.value.inputs != null && msg.value.inputs.size() > 0) {
                    for (Input input:msg.value.inputs) {
                        if(input.address.equals(address)) {
                            return BaseConstant.TX_TYPE_SEND;
                        }
                    }
                }
                if (msg.value.outputs != null && msg.value.outputs.size() > 0) {
                    for (Output output:msg.value.outputs) {
                        if(output.address.equals(address)) {
                            return BaseConstant.TX_TYPE_RECEIVE;
                        }
                    }

                }
                result = BaseConstant.TX_TYPE_TRANSFER;
            }
        } else if (msg.type.equals(BaseConstant.COSMOS_MSG_TYPE_DELEGATE) ||
                msg.type.equals(BaseConstant.IRIS_MSG_TYPE_DELEGATE)) {
            result = BaseConstant.TX_TYPE_DELEGATE;

        } else if (msg.type.equals(BaseConstant.COSMOS_MSG_TYPE_UNDELEGATE) ||
                msg.type.equals(BaseConstant.COSMOS_MSG_TYPE_UNDELEGATE2) ||
                msg.type.equals(BaseConstant.IRIS_MSG_TYPE_UNDELEGATE)) {
            result = BaseConstant.TX_TYPE_UNDELEGATE;

        } else if (msg.type.equals(BaseConstant.COSMOS_MSG_TYPE_REDELEGATE) ||
                msg.type.equals(BaseConstant.COSMOS_MSG_TYPE_REDELEGATE2) ||
                msg.type.equals(BaseConstant.IRIS_MSG_TYPE_REDELEGATE)) {
            result = BaseConstant.TX_TYPE_REDELEGATE;

        } else if (msg.type.equals(BaseConstant.COSMOS_MSG_TYPE_WITHDRAW_DEL) ||
                msg.type.equals(BaseConstant.IRIS_MSG_TYPE_WITHDRAW)) {
            result = BaseConstant.TX_TYPE_GET_REWARD;

        } else if (msg.type.equals(BaseConstant.COSMOS_MSG_TYPE_WITHDRAW_VAL)) {
            result = BaseConstant.TX_TYPE_GET_CPMMISSION;

        } else if (msg.type.equals(BaseConstant.COSMOS_MSG_TYPE_WITHDRAW_MIDIFY) ||
                msg.type.equals(BaseConstant.IRIS_MSG_TYPE_WITHDRAW_MIDIFY)) {
            result = BaseConstant.TX_TYPE_CHAGE_REWARD_ADDRESS;

        } else if (msg.type.equals(BaseConstant.COSMOS_MSG_TYPE_VOTE) ||
                msg.type.equals(BaseConstant.IRIS_MSG_TYPE_VOTE)) {
            result = BaseConstant.TX_TYPE_VOTE;

        } else if (msg.type.equals(BaseConstant.COSMOS_MSG_TYPE_SUBMIT_PROPOSAL) ||
                msg.type.equals(BaseConstant.IRIS_MSG_TYPE_SUBMIT_PROPOSAL)) {
            result = BaseConstant.TX_TYPE_SUBMIT_PROPOSAL;

        } else if (msg.type.equals(BaseConstant.COSMOS_MSG_TYPE_DEPOSIT) ||
                msg.type.equals(BaseConstant.IRIS_MSG_TYPE_DEPOSIT)) {
            result = BaseConstant.TX_TYPE_DEPOSIT;

        } else if (msg.type.equals(BaseConstant.COSMOS_MSG_TYPE_CREATE_VALIDATOR) ||
                msg.type.equals(BaseConstant.IRIS_MSG_TYPE_CREATE_VALIDATOR)) {
            result = BaseConstant.TX_TYPE_CREATE_VALIDATOR;

        } else if (msg.type.equals(BaseConstant.COSMOS_MSG_TYPE_EDIT_VALIDATOR)) {
            result = BaseConstant.TX_TYPE_EDIT_VALIDATOR;

        } else if (msg.type.equals(BaseConstant.IRIS_MSG_TYPE_WITHDRAW_ALL)) {
            result = BaseConstant.TX_TYPE_IRIS_GET_REWARD_ALL;

        }
        return result;
    }

    public static String getHistoryDpCnt(ArrayList<Msg> msgs) {
        String result = "";
        if(msgs.size() > 2) {
            result = result + " + " + (msgs.size() - 1);
        }
        return result;
    }





    public static DecimalFormat getDecimalFormat(Context c, int cnt) {
        NumberFormat formatter = NumberFormat.getNumberInstance(Locale.US);
        DecimalFormat decimalformat = (DecimalFormat)formatter;
        switch (cnt) {
            case 0:
                decimalformat.applyLocalizedPattern(c.getString(R.string.str_decimal_pattern_0));
                break;
            case 1:
                decimalformat.applyLocalizedPattern(c.getString(R.string.str_decimal_pattern_1));
                break;
            case 2:
                decimalformat.applyLocalizedPattern(c.getString(R.string.str_decimal_pattern_2));
                break;
            case 3:
                decimalformat.applyLocalizedPattern(c.getString(R.string.str_decimal_pattern_3));
                break;
            case 4:
                decimalformat.applyLocalizedPattern(c.getString(R.string.str_decimal_pattern_4));
                break;
            case 5:
                decimalformat.applyLocalizedPattern(c.getString(R.string.str_decimal_pattern_5));
                break;
            case 6:
                decimalformat.applyLocalizedPattern(c.getString(R.string.str_decimal_pattern_6));
                break;
            case 7:
                decimalformat.applyLocalizedPattern(c.getString(R.string.str_decimal_pattern_7));
                break;
            case 8:
                decimalformat.applyLocalizedPattern(c.getString(R.string.str_decimal_pattern_8));
                break;
            case 9:
                decimalformat.applyLocalizedPattern(c.getString(R.string.str_decimal_pattern_9));
                break;
            case 10:
                decimalformat.applyLocalizedPattern(c.getString(R.string.str_decimal_pattern_10));
                break;
            case 11:
                decimalformat.applyLocalizedPattern(c.getString(R.string.str_decimal_pattern_11));
                break;
            case 12:
                decimalformat.applyLocalizedPattern(c.getString(R.string.str_decimal_pattern_12));
                break;
            case 13:
                decimalformat.applyLocalizedPattern(c.getString(R.string.str_decimal_pattern_13));
                break;
            case 14:
                decimalformat.applyLocalizedPattern(c.getString(R.string.str_decimal_pattern_14));
                break;
            case 15:
                decimalformat.applyLocalizedPattern(c.getString(R.string.str_decimal_pattern_15));
                break;
            case 16:
                decimalformat.applyLocalizedPattern(c.getString(R.string.str_decimal_pattern_16));
                break;
            case 17:
                decimalformat.applyLocalizedPattern(c.getString(R.string.str_decimal_pattern_17));
                break;
            case 18:
                decimalformat.applyLocalizedPattern(c.getString(R.string.str_decimal_pattern_18));
                break;

            default:
                decimalformat.applyLocalizedPattern(c.getString(R.string.str_decimal_pattern_6));
                break;
        }
        return decimalformat;
    }


    public static String getDpTime(Context c, long time) {
        String result = "??";
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(time);
            SimpleDateFormat simpleFormat = new SimpleDateFormat(c.getString(R.string.str_dp_time_format1));
            result = simpleFormat.format(calendar.getTimeInMillis());
        } catch (Exception e) {};

        return result;
    }

    public static String getUnbondTime(Context c, BaseChain chain) {
        String result = "??";
        try {
            if(chain.equals(BaseChain.COSMOS_MAIN) || chain.equals(BaseChain.IRIS_MAIN)) {
                Calendar calendar = Calendar.getInstance();
                calendar.add(Calendar.DATE, 21);
                SimpleDateFormat unbondFormat = new SimpleDateFormat(c.getString(R.string.str_dp_time_format2));
                result = unbondFormat.format(calendar.getTimeInMillis());
                return result + "   " +c.getString(R.string.str_unbonding_21days_after);

            } else {
                Calendar calendar = Calendar.getInstance();
                calendar.add(Calendar.DATE, 3);
                SimpleDateFormat unbondFormat = new SimpleDateFormat(c.getString(R.string.str_dp_time_format2));
                result = unbondFormat.format(calendar.getTimeInMillis());
                return result + "   " +c.getString(R.string.str_unbonding_3days_after);
            }

        } catch (Exception e) {};

        return result;
    }

    public static String getUnbondingTimeleft(Context c, long finishTime) {
        String result = "??";
        try {
            long now        = Calendar.getInstance().getTimeInMillis();
            SimpleDateFormat blockDateFormat = new SimpleDateFormat(c.getString(R.string.str_block_time_format));
            blockDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            long left       = finishTime - now;

            if (left >= BaseConstant.CONSTANT_D ) {
                result = "(D-" + (left / BaseConstant.CONSTANT_D) +")";
            } else if (left >= BaseConstant.CONSTANT_H ) {
                result = "(H-" + (left / BaseConstant.CONSTANT_H) +")";
            } else {
                return "SOON";
            }

        } catch (Exception e){}

        return result;
    }

    public static String getUnbondingTimefrom(Context c, String rawStartTime) {
        String result = "??";
        try {
            long now   = Calendar.getInstance().getTimeInMillis();

            SimpleDateFormat blockDateFormat = new SimpleDateFormat(c.getString(R.string.str_block_time_format));
            blockDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            long start = blockDateFormat.parse(rawStartTime).getTime();
            long left  = start + BaseConstant.COSMOS_UNBONDING_TIME - now;

//            WLog.w("start : " + start);
//            WLog.w("COSMOS_UNBONDING_TIME : " + BaseConstant.COSMOS_UNBONDING_TIME);
//            WLog.w("now : " + now);

            if (left >= BaseConstant.CONSTANT_D ) {
                result = "(D-" + (left / BaseConstant.CONSTANT_D) +")";
            } else if (left >= BaseConstant.CONSTANT_H ) {
                result = "(H-" + (left / BaseConstant.CONSTANT_H) +")";
            } else if (left < 0){
                return "completed";
            } else {
                return "in hour";
            }

        } catch (Exception e){}

        return result;
    }

    public static String getTimeformat(Context c, String rawValue) {
        String result = "??";
        try {
            SimpleDateFormat blockDateFormat = new SimpleDateFormat(c.getString(R.string.str_block_time_format));
            SimpleDateFormat myFormat = new SimpleDateFormat(c.getString(R.string.str_dp_time_format1));
            blockDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            result = myFormat.format(blockDateFormat.parse(rawValue));
        } catch (Exception e) {};

        return result;
    }

    public static String getTimeTxformat(Context c, String rawValue) {
        String result = "??";
        try {
            SimpleDateFormat blockDateFormat = new SimpleDateFormat(c.getString(R.string.str_tx_time_format));
            SimpleDateFormat myFormat = new SimpleDateFormat(c.getString(R.string.str_dp_time_format1));
            blockDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            result = myFormat.format(blockDateFormat.parse(rawValue));
        } catch (Exception e) {};

        return result;
    }

    public static String getTimeGap(Context c, String rawValue) {
        String result = "";
        try {
            SimpleDateFormat blockDateFormat = new SimpleDateFormat(c.getString(R.string.str_block_time_format));
            blockDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            Date blockTime  = blockDateFormat.parse(rawValue);
            Date nowTime    = Calendar.getInstance().getTime();

            long difference = nowTime.getTime() - blockTime.getTime();

            long differenceSeconds = difference / 1000 % 60;
            long differenceMinutes = difference / (60 * 1000) % 60;
            long differenceHours = difference / (60 * 60 * 1000) % 24;
            long differenceDays = difference / (24 * 60 * 60 * 1000);

            if(differenceDays > 1) {
                result = ""+differenceDays+ " " + c.getString(R.string.str_day);
            } else if (differenceDays == 1){
                result = ""+differenceDays + c.getString(R.string.str_d) + " " + differenceHours + c.getString(R.string.str_h);
            } else {
                if (differenceHours > 0) {
                    result = ""+differenceHours+ c.getString(R.string.str_h) + " " + differenceMinutes + c.getString(R.string.str_m);
                } else {
                    if(differenceMinutes > 0) {
                        result = ""+differenceMinutes+ c.getString(R.string.str_m) + " " + differenceSeconds + c.getString(R.string.str_s);
                    } else {
                        result = differenceSeconds + c.getString(R.string.str_s);
                    }
                }

            }

        } catch (Exception e) {}

        return "(" + result + " " + c.getString(R.string.str_ago) +")";
    }

    public static String DpAtom(Context c) {
        String result = c.getString(R.string.s_atom);
        return result;
    }

    public static String DpPoton(Context c) {
        String result = c.getString(R.string.s_photon);
        return result;
    }

    public static String DpIris(Context c) {
        String result = c.getString(R.string.s_iris);
        return result;
    }

    public static int getChainColor(Context c, String chain) {
        if(chain.equals(BaseChain.COSMOS_MAIN.getChain())) {
            return c.getResources().getColor(R.color.colorAtom);
        } else {
            return c.getResources().getColor(R.color.colorIris);
        }
    }

    public static ColorStateList getTabColor(Context c, String chain) {
        if(chain.equals(BaseChain.COSMOS_MAIN.getChain())) {
            return c.getResources().getColorStateList(R.color.color_tab_myvalidator);
        } else {
            return c.getResources().getColorStateList(R.color.color_tab_myvalidator_iris);
        }
    }

    public static ColorStateList getChainTintColor(Context c, String chain) {
        if(chain.equals(BaseChain.COSMOS_MAIN.getChain())) {
            return c.getResources().getColorStateList(R.color.colorAtom);
        } else {
            return c.getResources().getColorStateList(R.color.colorIris);
        }
    }

    public static void DpMainDenom(Context c, String chain, TextView textview) {
        if (chain.equals(BaseChain.COSMOS_MAIN.getChain())) {
            textview.setTextColor(c.getResources().getColor(R.color.colorAtom));
            textview.setText(c.getString(R.string.s_atom));

        } else if (chain.equals(BaseChain.IRIS_MAIN.getChain())) {
            textview.setTextColor(c.getResources().getColor(R.color.colorIris));
            textview.setText(c.getString(R.string.s_iris));
        }
    }
}
