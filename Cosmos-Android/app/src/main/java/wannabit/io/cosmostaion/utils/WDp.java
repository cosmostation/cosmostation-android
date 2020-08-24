package wannabit.io.cosmostaion.utils;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.RelativeSizeSpan;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.dao.Balance;
import wannabit.io.cosmostaion.dao.BondingState;
import wannabit.io.cosmostaion.dao.Reward;
import wannabit.io.cosmostaion.dao.UnBondingState;
import wannabit.io.cosmostaion.model.KavaCDP;
import wannabit.io.cosmostaion.model.type.BnbHistory;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.model.type.Input;
import wannabit.io.cosmostaion.model.type.Msg;
import wannabit.io.cosmostaion.model.type.Output;
import wannabit.io.cosmostaion.model.type.Validator;
import wannabit.io.cosmostaion.network.res.ResBnbNodeInfo;
import wannabit.io.cosmostaion.network.res.ResBnbSwapInfo;
import wannabit.io.cosmostaion.network.res.ResCdpParam;
import wannabit.io.cosmostaion.network.res.ResKavaSwapInfo;
import wannabit.io.cosmostaion.network.res.ResLcdIrisPool;
import wannabit.io.cosmostaion.network.res.ResLcdIrisReward;
import wannabit.io.cosmostaion.network.res.ResLcdKavaAccountInfo;
import wannabit.io.cosmostaion.network.res.ResOkDeposit;
import wannabit.io.cosmostaion.network.res.ResOkWithdraw;
import wannabit.io.cosmostaion.network.res.ResTxInfo;

import static android.text.Spanned.SPAN_INCLUSIVE_INCLUSIVE;
import static wannabit.io.cosmostaion.base.BaseChain.BAND_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.BNB_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.BNB_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.COSMOS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.IOV_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.IOV_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.IRIS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.KAVA_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.KAVA_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.OK_TEST;
import static wannabit.io.cosmostaion.base.BaseConstant.IS_TEST;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_ATOM;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_BAND;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_BNB;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_IOV;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_IOV_TEST;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_IRIS_ATTO;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_KAVA;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_MUON;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_OK_TEST;
import static wannabit.io.cosmostaion.network.res.ResBnbSwapInfo.BNB_STATUS_COMPLETED;
import static wannabit.io.cosmostaion.network.res.ResBnbSwapInfo.BNB_STATUS_OPEN;
import static wannabit.io.cosmostaion.network.res.ResBnbSwapInfo.BNB_STATUS_REFUNDED;

public class WDp {

    public static SpannableString getDpAmount(Context c, BigDecimal input, int point, BaseChain chain) {
        SpannableString result;
        BigDecimal amount = input.setScale(point, BigDecimal.ROUND_DOWN);
        if (chain.equals(COSMOS_MAIN) || chain.equals(KAVA_MAIN) || chain.equals(KAVA_TEST) ||
                chain.equals(BAND_MAIN) || chain.equals(IOV_MAIN) || chain.equals(IOV_TEST)) {
            amount = amount.divide(new BigDecimal("1000000"), 6, BigDecimal.ROUND_DOWN);
            result = new SpannableString(getDecimalFormat(c, point).format(amount));
            result.setSpan(new RelativeSizeSpan(0.8f), result.length() - point, result.length(), SPAN_INCLUSIVE_INCLUSIVE);

        } else if (chain.equals(IRIS_MAIN)) {
            amount = amount.divide(new BigDecimal("1000000000000000000"), 18, BigDecimal.ROUND_DOWN);
            result = new SpannableString(getDecimalFormat(c, point).format(amount));
            result.setSpan(new RelativeSizeSpan(0.8f), result.length() - point, result.length(), SPAN_INCLUSIVE_INCLUSIVE);

        } else if (chain.equals(BNB_MAIN) || chain.equals(BNB_TEST)) {
            result = new SpannableString(getDecimalFormat(c, point).format(amount));
            result.setSpan(new RelativeSizeSpan(0.8f), result.length() - point, result.length(), SPAN_INCLUSIVE_INCLUSIVE);

        } else {
            result = new SpannableString(getDecimalFormat(c, 0).format(amount));

        }
        return result;
    }

    //show display text with full input amount and to divide deciaml and to show point
    public static SpannableString getDpAmount2(Context c, BigDecimal input, int divideDecimal, int displayDecimal) {
        SpannableString result;
        BigDecimal amount = input.movePointLeft(divideDecimal).setScale(displayDecimal, BigDecimal.ROUND_DOWN);
        result = new SpannableString(getDecimalFormat(c, displayDecimal).format(amount));
        result.setSpan(new RelativeSizeSpan(0.8f), result.length() - displayDecimal, result.length(), SPAN_INCLUSIVE_INCLUSIVE);
        return result;
    }

    public static SpannableString getDpString(String input, int point) {
        SpannableString result;
        result = new SpannableString(input);
        result.setSpan(new RelativeSizeSpan(0.8f), result.length() - point, result.length(), SPAN_INCLUSIVE_INCLUSIVE);
        return result;
    }



    public static void showCoinDp(Context c, Coin coin, TextView denomTv, TextView amountTv, BaseChain chain) {
        if (chain.equals(COSMOS_MAIN)) {
            DpMainDenom(c, chain.getChain(), denomTv);
            amountTv.setText(getDpAmount2(c, new BigDecimal(coin.amount), 6, 6));

        } else if (chain.equals(IRIS_MAIN)) {
            if (coin.denom.equals(TOKEN_IRIS_ATTO)) {
                DpMainDenom(c, chain.getChain(), denomTv);
            } else {
                denomTv.setText(coin.denom.toUpperCase());
            }
            amountTv.setText(getDpAmount2(c, new BigDecimal(coin.amount), 18, 18));


        } else if (chain.equals(KAVA_MAIN) || chain.equals(KAVA_TEST)) {
            if (coin.denom.equals(TOKEN_KAVA)) {
                DpMainDenom(c, chain.getChain(), denomTv);
            } else {
                denomTv.setText(coin.denom.toUpperCase());
            }
            //TODO need check decimal with denom's type
            amountTv.setText(getDpAmount2(c, new BigDecimal(coin.amount), WUtil.getKavaCoinDecimal(coin), WUtil.getKavaCoinDecimal(coin)));

        } else if (chain.equals(IOV_MAIN) || chain.equals(IOV_TEST)) {
            if (coin.denom.equals(TOKEN_IOV) || coin.denom.equals(TOKEN_IOV_TEST)) {
                DpMainDenom(c, chain.getChain(), denomTv);

            } else {
                denomTv.setText(coin.denom.toUpperCase());

            }
            amountTv.setText(getDpAmount2(c, new BigDecimal(coin.amount), 6, 6));

        } else if (chain.equals(BNB_MAIN) || chain.equals(BNB_TEST)) {
            if (coin.denom.equals(TOKEN_BNB)) {
                DpMainDenom(c, chain.getChain(), denomTv);
            } else {
                denomTv.setText(coin.denom.toUpperCase());
            }
            amountTv.setText(getDpAmount2(c, new BigDecimal(coin.amount), 8, 8));

        } else if (chain.equals(BAND_MAIN)) {
            DpMainDenom(c, chain.getChain(), denomTv);
            amountTv.setText(getDpAmount2(c, new BigDecimal(coin.amount), 6, 6));

        } else if (chain.equals(OK_TEST)) {
            if (coin.denom.equals(TOKEN_OK_TEST)) {
                DpMainDenom(c, chain.getChain(), denomTv);
            } else {
                denomTv.setText(coin.denom.toUpperCase());
            }
            amountTv.setText(getDpAmount2(c, new BigDecimal(coin.amount), 0, 8));

        }
    }

    public static void showCoinDp(Context c, String symbol, String amount, TextView denomTv, TextView amountTv, BaseChain chain) {
        if (chain.equals(COSMOS_MAIN)) {
            DpMainDenom(c, chain.getChain(), denomTv);
            amountTv.setText(getDpAmount2(c, new BigDecimal(amount), 6, 6));

        } else if (chain.equals(IRIS_MAIN)) {
            if (symbol.equals(TOKEN_IRIS_ATTO)) {
                DpMainDenom(c, chain.getChain(), denomTv);
            } else {
                denomTv.setText(symbol.toUpperCase());
            }
            //TODO need check decimal with denom's type
            amountTv.setText(getDpAmount2(c, new BigDecimal(amount), 18, 18));

        } else if (chain.equals(KAVA_MAIN) || chain.equals(KAVA_TEST)) {
            if (symbol.equals(TOKEN_KAVA)) {
                DpMainDenom(c, chain.getChain(), denomTv);
            } else {
                denomTv.setText(symbol.toUpperCase());
            }
            amountTv.setText(getDpAmount2(c, new BigDecimal(amount), WUtil.getKavaCoinDecimal(symbol), WUtil.getKavaCoinDecimal(symbol)));

        } else if (chain.equals(IOV_MAIN) || chain.equals(IOV_TEST)) {
            if (symbol.equals(TOKEN_IOV) || symbol.equals(TOKEN_IOV_TEST)) {
                DpMainDenom(c, chain.getChain(), denomTv);

            } else {
                denomTv.setText(symbol.toUpperCase());

            }
            amountTv.setText(getDpAmount2(c, new BigDecimal(amount), 6, 6));

        } else if (chain.equals(BNB_MAIN) || chain.equals(BNB_TEST)) {
            if (symbol.equals(TOKEN_BNB)) {
                DpMainDenom(c, chain.getChain(), denomTv);

            } else {
                denomTv.setText(symbol.toUpperCase());

            }
            amountTv.setText(getDpAmount2(c, new BigDecimal(amount), 8, 8));

        } else if (chain.equals(BAND_MAIN)) {
            DpMainDenom(c, chain.getChain(), denomTv);
            amountTv.setText(getDpAmount2(c, new BigDecimal(amount), 6, 6));

        }
    }

    public static SpannableString getDpAllRewardAmount(Context c, ArrayList<Reward> rewards, BaseChain chain, String denom) {
        BigDecimal sum = BigDecimal.ZERO;
        for(Reward reward : rewards) {
            sum = sum.add(reward.getRewardAmount(denom).setScale(0, BigDecimal.ROUND_DOWN));
        }
        return getDpAmount(c, sum, 6, chain);
    }

    public static BigDecimal getAllRewardAmount(ArrayList<Reward> rewards, String denom) {
        BigDecimal sum = BigDecimal.ZERO;
        for(Reward reward : rewards) {
            sum = sum.add(reward.getRewardAmount(denom).setScale(0, BigDecimal.ROUND_DOWN));
        }
        return sum;
    }

    public static SpannableString getDpAllIrisRewardAmount(Context c, ResLcdIrisReward rewards, BaseChain chain) {
        if (rewards != null) {
            return getDpAmount(c, rewards.getSimpleIrisReward(), 6, chain);
        } else {
            return getDpAmount(c, BigDecimal.ZERO, 6, chain);
        }
    }

    public static SpannableString getValidatorReward(Context c, ArrayList<Reward> rewards, String valOpAddress, BaseChain chain, String denom) {
        BigDecimal result = BigDecimal.ZERO;
        for(Reward reward : rewards) {
            if(reward.validatorAddress.equals(valOpAddress)) {
                result = reward.getRewardAmount(denom);
                break;
            }
        }
        return getDpAmount(c, result, 6, chain);
    }

    public static SpannableString getIrisValidatorReward(Context c, ResLcdIrisReward reward, String valOpAddress, BaseChain chain) {
        if(reward == null || TextUtils.isEmpty(valOpAddress)) {
            return getDpAmount(c, BigDecimal.ZERO, 6, chain);
        }
        return getDpAmount(c, reward.getPerValReward(valOpAddress), 6, chain);
    }

    public static BigDecimal getValidatorReward(ArrayList<Reward> rewards, String valOpAddress, String denom) {
        BigDecimal result = BigDecimal.ZERO;
        for(Reward reward : rewards) {
            if(reward.validatorAddress.equals(valOpAddress)) {
                result = reward.getRewardAmount(denom);
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
            value = provision.multiply(BigDecimal.ONE.subtract(commission)).multiply(delegated).divide(bonded.multiply(new BigDecimal("365000000")), 12, RoundingMode.DOWN);

        }catch (Exception e) {}

        SpannableString result;
        result = new SpannableString(getDecimalFormat(c, 12).format(value));
        result.setSpan(new RelativeSizeSpan(0.8f), result.length() - 12, result.length(), SPAN_INCLUSIVE_INCLUSIVE);
        return result;
    }


    public static BigDecimal getMonthlyReturn(BigDecimal bonded, BigDecimal provision, BigDecimal commission, BigDecimal delegated) {
        BigDecimal result = BigDecimal.ZERO;
        try {
            result = provision.multiply(BigDecimal.ONE.subtract(commission)).multiply(delegated).divide(bonded.multiply(new BigDecimal("12000000")), 12, RoundingMode.DOWN);

        }catch (Exception e) {}
        return result;
    }

    public static SpannableString getMonthlyReturn(Context c, BigDecimal bonded, BigDecimal provision, BigDecimal commission, BigDecimal delegated) {
        BigDecimal value = BigDecimal.ZERO;
        try {
            value = provision.multiply(BigDecimal.ONE.subtract(commission)).multiply(delegated).divide(bonded.multiply(new BigDecimal("12000000")), 12, RoundingMode.DOWN);

        }catch (Exception e) {}
        SpannableString result;
        result = new SpannableString(getDecimalFormat(c, 12).format(value));
        result.setSpan(new RelativeSizeSpan(0.8f), result.length() - 12, result.length(), SPAN_INCLUSIVE_INCLUSIVE);
        return result;
    }

    public static SpannableString getIrisDailyReturn(Context c, ResLcdIrisPool pool, BigDecimal commission, BigDecimal delegated) {
        BigDecimal value = BigDecimal.ZERO;
        try {
            value = pool.geTotal().multiply(new BigDecimal(0.04)).setScale(0, RoundingMode.DOWN).multiply(BigDecimal.ONE.subtract(commission)).multiply(delegated).divide(pool.getBonded().multiply(new BigDecimal("365000000000000000000")), 18, RoundingMode.DOWN);

        }catch (Exception e) {}

        SpannableString result;
        result = new SpannableString(getDecimalFormat(c, 18).format(value));
        result.setSpan(new RelativeSizeSpan(0.8f), result.length() - 18, result.length(), SPAN_INCLUSIVE_INCLUSIVE);
        return result;
    }

    public static SpannableString getIrisMonthlyReturn(Context c, ResLcdIrisPool pool, BigDecimal commission, BigDecimal delegated) {
        BigDecimal value = BigDecimal.ZERO;
        try {
            value = pool.geTotal().multiply(new BigDecimal(0.04)).setScale(0, RoundingMode.DOWN).multiply(BigDecimal.ONE.subtract(commission)).multiply(delegated).divide(pool.getBonded().multiply(new BigDecimal("12000000000000000000")), 18, RoundingMode.DOWN);

        }catch (Exception e) {}
        SpannableString result;
        result = new SpannableString(getDecimalFormat(c, 18).format(value));
        result.setSpan(new RelativeSizeSpan(0.8f), result.length() - 18, result.length(), SPAN_INCLUSIVE_INCLUSIVE);
        return result;
    }

    public static BigDecimal getAvailableCoin(ArrayList<Balance> balances, String denom) {
        BigDecimal sum = BigDecimal.ZERO;
        for (Balance balance : balances) {
            if (denom.equals(TOKEN_ATOM) && IS_TEST) {
                if (balance.symbol.equalsIgnoreCase(TOKEN_MUON)) {
                    sum = balance.balance;
                }
            } else {
                if (balance.symbol.equalsIgnoreCase(denom)) {
                    sum = balance.balance;
                }
            }
        }
        return sum;
    }

    public static BigDecimal getLockedCoin(ArrayList<Balance> balances, String denom) {
        BigDecimal sum = BigDecimal.ZERO;
        for (Balance balance : balances) {
            if (balance.symbol.equalsIgnoreCase(denom)) {
                sum = balance.locked;
            }
        }
        return sum;
    }

    public static BigDecimal getOkDepositCoin(ResOkDeposit deposit) {
        BigDecimal sum = BigDecimal.ZERO;
        if (deposit != null && !TextUtils.isEmpty(deposit.tokens)) {
            sum = new BigDecimal(deposit.tokens);
        }
        return sum;
    }

    public static BigDecimal getOkWithdrawingCoin(ResOkWithdraw withdraw) {
        BigDecimal sum = BigDecimal.ZERO;
        if (withdraw != null && !TextUtils.isEmpty(withdraw.quantity)) {
            sum = new BigDecimal(withdraw.quantity);
        }
        return sum;
    }

    public static BigDecimal getDelegableAmount(ArrayList<Balance> balances, String denom) {
        BigDecimal sum = BigDecimal.ZERO;
        for (Balance balance : balances) {
            if (balance.symbol.equalsIgnoreCase(denom)) {
                sum = balance.balance.add(balance.locked);
            }
        }
        return sum;
    }


    public static SpannableString getDpAvailableCoin(Context c, ArrayList<Balance> balances, BaseChain chain, String denom) {
        return getDpAmount(c, getAvailableCoin(balances, denom), 6, chain);
    }


    public static BigDecimal getVestedCoin(ArrayList<Balance> balances, String denom) {
        BigDecimal sum = BigDecimal.ZERO;
        for (Balance balance : balances) {
            if (balance.symbol.equals(denom)) {
                sum = balance.locked;
            }
        }
        return sum;
    }

    public static SpannableString getDpVestedCoin(Context c, ArrayList<Balance> balances, BaseChain chain, String denom) {
        return getDpAmount(c, getVestedCoin(balances, denom), 6, chain);
    }

    public static SpannableString getDpAllDelegatedAmount(Context c, ArrayList<BondingState> bondings, ArrayList<Validator> validators,  BaseChain chain) {
        return getDpAmount(c, getAllDelegatedAmount(bondings, validators, chain), 6, chain);
    }

    public static BigDecimal getAllDelegatedAmount(ArrayList<BondingState> bondings, ArrayList<Validator> validators,  BaseChain chain) {
        BigDecimal sum = BigDecimal.ZERO;
        if (bondings == null || bondings.size() == 0) return sum;
        if (chain.equals(COSMOS_MAIN) || chain.equals(KAVA_MAIN) || chain.equals(BAND_MAIN) ||
                chain.equals(KAVA_TEST) || chain.equals(IOV_TEST)) {
            for(BondingState bonding : bondings) {
                sum = sum.add(bonding.getBondingAmount(selectValidator(validators, bonding.validatorAddress)));
            }
        } else if (chain.equals(IRIS_MAIN)) {
            for(BondingState bonding : bondings) {
                sum = sum.add(bonding.getBondingAmount(selectValidator(validators, bonding.validatorAddress)));
            }
        }
        return sum;
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
        return getDpAmount(c, getUnbondingAmount(unbondings), 6, chain);
    }

    public static BigDecimal getUnbondingAmount(ArrayList<UnBondingState> unbondings) {
        BigDecimal sum = BigDecimal.ZERO;
        if (unbondings == null || unbondings.size() == 0) return sum;
        for(UnBondingState unbonding : unbondings) {
            sum = sum.add(unbonding.balance);
        }
        return sum;
    }

    public static SpannableString getDpAllAtom(Context c, ArrayList<Balance> balances, ArrayList<BondingState> bondings, ArrayList<UnBondingState> unbondings, ArrayList<Reward> rewards, ArrayList<Validator> validators, BaseChain chain) {
        return getDpAmount(c, getAllAtom(balances, bondings,unbondings,rewards,validators), 6, chain);
    }

    public static BigDecimal getAllAtom(ArrayList<Balance> balances, ArrayList<BondingState> bondings, ArrayList<UnBondingState> unbondings, ArrayList<Reward> rewards, ArrayList<Validator> validators) {
        BigDecimal sum = BigDecimal.ZERO;
        for(Balance balance : balances) {
            if(balance.symbol.equals(BaseConstant.TOKEN_ATOM) || balance.symbol.equals(BaseConstant.TOKEN_MUON)) {
                sum = sum.add(balance.balance);
            }
        }
        if(bondings != null) {
            for(BondingState bonding : bondings) {
                sum = sum.add(bonding.getBondingAmount(selectValidator(validators, bonding.validatorAddress)));
            }
        }
        if (unbondings != null) {
            for(UnBondingState unbonding : unbondings) {
                sum = sum.add(unbonding.balance);
            }
        }
        if (rewards != null) {
            for(Reward reward : rewards) {
                sum = sum.add(reward.getRewardAmount(TOKEN_ATOM));
            }
        }
        return sum;
    }

    public static BigDecimal getAllKava(ArrayList<Balance> balances, ArrayList<BondingState> bondings, ArrayList<UnBondingState> unbondings, ArrayList<Reward> rewards, ArrayList<Validator> validators) {
        BigDecimal sum = BigDecimal.ZERO;
        for (Balance balance : balances) {
            if (balance.symbol.equals(BaseConstant.TOKEN_KAVA)) {
                sum = sum.add(balance.balance);
                sum = sum.add(balance.locked);
            }
        }
        if (bondings != null) {
            for(BondingState bonding : bondings) {
                sum = sum.add(bonding.getBondingAmount(selectValidator(validators, bonding.validatorAddress)));
            }
        }
        if (unbondings != null) {
            for(UnBondingState unbonding : unbondings) {
                sum = sum.add(unbonding.balance);
            }
        }
        if (rewards != null) {
            for(Reward reward : rewards) {
                sum = sum.add(reward.getRewardAmount(TOKEN_KAVA));
            }
        }
        return sum;
    }

        public static SpannableString getDpAllIris(Context c, ArrayList<Balance> balances, ArrayList<BondingState> bondings, ArrayList<UnBondingState> unbondings, ResLcdIrisReward reward, ArrayList<Validator> validators, BaseChain chain) {
        return getDpAmount(c, getAllIris(balances, bondings, unbondings, reward, validators), 6, chain);
    }

    public static BigDecimal getAllIris(ArrayList<Balance> balances, ArrayList<BondingState> bondings, ArrayList<UnBondingState> unbondings, ResLcdIrisReward reward, ArrayList<Validator> validators) {
        BigDecimal sum = BigDecimal.ZERO;
        for (Balance balance : balances) {
            if(balance.symbol.equals(TOKEN_IRIS_ATTO)) {
                sum = sum.add(balance.balance);
            }
        }
        if (bondings != null) {
            for(BondingState bonding : bondings) {
                sum = sum.add(bonding.getBondingAmount(selectValidator(validators, bonding.validatorAddress)));
            }
        }
        for (UnBondingState unbonding : unbondings) {
            sum = sum.add(unbonding.balance);
        }
        if (reward != null) {
            sum = sum.add(reward.getSimpleIrisReward());
        }
        return sum;
    }

    public static BigDecimal getAllBand(ArrayList<Balance> balances, ArrayList<BondingState> bondings, ArrayList<UnBondingState> unbondings, ArrayList<Reward> rewards, ArrayList<Validator> validators) {
        BigDecimal sum = BigDecimal.ZERO;
        for(Balance balance : balances) {
            if(balance.symbol.equals(TOKEN_BAND)) {
                sum = sum.add(balance.balance);
            }
        }
        if (bondings != null) {
            for(BondingState bonding : bondings) {
                sum = sum.add(bonding.getBondingAmount(selectValidator(validators, bonding.validatorAddress)));
            }
        }
        if (unbondings != null) {
            for(UnBondingState unbonding : unbondings) {
                sum = sum.add(unbonding.balance);
            }
        }
        if (rewards != null) {
            for(Reward reward : rewards) {
                sum = sum.add(reward.getRewardAmount(TOKEN_BAND));
            }
        }
        return sum;
    }

    public static BigDecimal getAllIov(ArrayList<Balance> balances, ArrayList<BondingState> bondings, ArrayList<UnBondingState> unbondings, ArrayList<Reward> rewards, ArrayList<Validator> validators) {
        BigDecimal sum = BigDecimal.ZERO;
        for(Balance balance : balances) {
            if(balance.symbol.equals(TOKEN_IOV) || balance.symbol.equals(TOKEN_IOV_TEST)) {
                sum = sum.add(balance.balance);
            }
        }
        if (bondings != null) {
            for(BondingState bonding : bondings) {
                sum = sum.add(bonding.getBondingAmount(selectValidator(validators, bonding.validatorAddress)));
            }
        }
        if (unbondings != null) {
            for(UnBondingState unbonding : unbondings) {
                sum = sum.add(unbonding.balance);
            }
        }
        if (rewards != null) {
            for(Reward reward : rewards) {
                sum = sum.add(reward.getRewardAmount(TOKEN_IOV));
                sum = sum.add(reward.getRewardAmount(TOKEN_IOV_TEST));
            }
        }
        return sum;
    }

    public static BigDecimal getAllOk(Balance balance, ResOkDeposit deposit, ResOkWithdraw withdraw) {
        BigDecimal sum = BigDecimal.ZERO;
        if (balance != null) {
            sum = sum.add(balance.balance);
            sum = sum.add(balance.locked);
        }
        sum = sum.add(getOkDepositCoin(deposit));
        sum = sum.add(getOkWithdrawingCoin(withdraw));

        return sum;
    }

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

    public static SpannableString getValueOfAtom(Context c, BaseData dao, BigDecimal totalAmount) {
        BigDecimal totalPrice = BigDecimal.ZERO;
        if(dao.getCurrency() == 5) {
            totalPrice = totalAmount.multiply(new BigDecimal(""+dao.getLastAtomTic())).movePointLeft(6).setScale(8, RoundingMode.DOWN);
            SpannableString result;
            result = new SpannableString(dao.getCurrencySymbol() + " " +getDecimalFormat(c, 8).format(totalPrice));
            result.setSpan(new RelativeSizeSpan(0.8f), result.length() - 8, result.length(), SPAN_INCLUSIVE_INCLUSIVE);
            return result;

        } else {
            totalPrice = totalAmount.multiply(new BigDecimal(""+dao.getLastAtomTic())).movePointLeft(6).setScale(2, RoundingMode.DOWN);
            SpannableString result;
            result = new SpannableString(dao.getCurrencySymbol() + " " +getDecimalFormat(c, 2).format(totalPrice));
            result.setSpan(new RelativeSizeSpan(0.8f), result.length() - 2, result.length(), SPAN_INCLUSIVE_INCLUSIVE);
            return result;
        }
    }

    public static SpannableString getValueOfIris(Context c, BaseData dao, BigDecimal totalAmount) {
        BigDecimal totalPrice = BigDecimal.ZERO;
        if(dao.getCurrency() == 5) {
            totalPrice = totalAmount.multiply(new BigDecimal(""+dao.getLastIrisTic())).movePointLeft(18).setScale(8, RoundingMode.DOWN);
            SpannableString result;
            result = new SpannableString(dao.getCurrencySymbol() + " " +getDecimalFormat(c, 8).format(totalPrice));
            result.setSpan(new RelativeSizeSpan(0.8f), result.length() - 8, result.length(), SPAN_INCLUSIVE_INCLUSIVE);
            return result;

        } else {
            totalPrice = totalAmount.multiply(new BigDecimal(""+dao.getLastIrisTic())).movePointLeft(18).setScale(2, RoundingMode.DOWN);
            SpannableString result;
            result = new SpannableString(dao.getCurrencySymbol() + " " +getDecimalFormat(c, 2).format(totalPrice));
            result.setSpan(new RelativeSizeSpan(0.8f), result.length() - 2, result.length(), SPAN_INCLUSIVE_INCLUSIVE);
            return result;
        }
    }

    public static SpannableString getValueOfBnb(Context c, BaseData dao, BigDecimal totalAmount) {
        BigDecimal totalPrice = BigDecimal.ZERO;
        if(dao.getCurrency() == 5) {
            totalPrice = totalAmount.multiply(new BigDecimal(""+dao.getLastBnbTic())).setScale(8, RoundingMode.DOWN);
            SpannableString result;
            result = new SpannableString(dao.getCurrencySymbol() + " " +getDecimalFormat(c, 8).format(totalPrice));
            result.setSpan(new RelativeSizeSpan(0.8f), result.length() - 8, result.length(), SPAN_INCLUSIVE_INCLUSIVE);
            return result;

        } else {
            totalPrice = totalAmount.multiply(new BigDecimal(""+dao.getLastBnbTic())).setScale(2, RoundingMode.DOWN);
            SpannableString result;
            result = new SpannableString(dao.getCurrencySymbol() + " " +getDecimalFormat(c, 2).format(totalPrice));
            result.setSpan(new RelativeSizeSpan(0.8f), result.length() - 2, result.length(), SPAN_INCLUSIVE_INCLUSIVE);
            return result;
        }
    }

    public static SpannableString getValueOfKava(Context c, BaseData dao, BigDecimal totalAmount) {
        BigDecimal totalPrice = BigDecimal.ZERO;
        if(dao.getCurrency() == 5) {
            totalPrice = totalAmount.multiply(new BigDecimal(""+dao.getLastKavaTic())).movePointLeft(6).setScale(8, RoundingMode.DOWN);
            SpannableString result;
            result = new SpannableString(dao.getCurrencySymbol() + " " +getDecimalFormat(c, 8).format(totalPrice));
            result.setSpan(new RelativeSizeSpan(0.8f), result.length() - 8, result.length(), SPAN_INCLUSIVE_INCLUSIVE);
            return result;

        } else {
            totalPrice = totalAmount.multiply(new BigDecimal(""+dao.getLastKavaTic())).movePointLeft(6).setScale(2, RoundingMode.DOWN);
            SpannableString result;
            result = new SpannableString(dao.getCurrencySymbol() + " " +getDecimalFormat(c, 2).format(totalPrice));
            result.setSpan(new RelativeSizeSpan(0.8f), result.length() - 2, result.length(), SPAN_INCLUSIVE_INCLUSIVE);
            return result;
        }
    }

    public static SpannableString getValueOfBand(Context c, BaseData dao, BigDecimal totalAmount) {
        BigDecimal totalPrice = BigDecimal.ZERO;
        if(dao.getCurrency() == 5) {
            totalPrice = totalAmount.multiply(new BigDecimal(""+dao.getLastBandTic())).movePointLeft(6).setScale(8, RoundingMode.DOWN);
            SpannableString result;
            result = new SpannableString(dao.getCurrencySymbol() + " " +getDecimalFormat(c, 8).format(totalPrice));
            result.setSpan(new RelativeSizeSpan(0.8f), result.length() - 8, result.length(), SPAN_INCLUSIVE_INCLUSIVE);
            return result;

        } else {
            totalPrice = totalAmount.multiply(new BigDecimal(""+dao.getLastBandTic())).movePointLeft(6).setScale(2, RoundingMode.DOWN);
            SpannableString result;
            result = new SpannableString(dao.getCurrencySymbol() + " " +getDecimalFormat(c, 2).format(totalPrice));
            result.setSpan(new RelativeSizeSpan(0.8f), result.length() - 2, result.length(), SPAN_INCLUSIVE_INCLUSIVE);
            return result;
        }

    }

    public static SpannableString getValueOfIov(Context c, BaseData dao, BigDecimal totalAmount) {
        BigDecimal totalPrice = BigDecimal.ZERO;
        if(dao.getCurrency() == 5) {
//            totalPrice = totalAmount.multiply(new BigDecimal(""+dao.getLastBandTic())).movePointLeft(6).setScale(8, RoundingMode.DOWN);
            SpannableString result;
            result = new SpannableString(dao.getCurrencySymbol() + " " +getDecimalFormat(c, 8).format(totalPrice));
            result.setSpan(new RelativeSizeSpan(0.8f), result.length() - 8, result.length(), SPAN_INCLUSIVE_INCLUSIVE);
            return result;

        } else {
//            totalPrice = totalAmount.multiply(new BigDecimal(""+dao.getLastBandTic())).movePointLeft(6).setScale(2, RoundingMode.DOWN);
            SpannableString result;
            result = new SpannableString(dao.getCurrencySymbol() + " " +getDecimalFormat(c, 2).format(totalPrice));
            result.setSpan(new RelativeSizeSpan(0.8f), result.length() - 2, result.length(), SPAN_INCLUSIVE_INCLUSIVE);
            return result;
        }
    }

    public static SpannableString getValueOfOk(Context c, BaseData dao, BigDecimal totalAmount) {
        BigDecimal totalPrice = BigDecimal.ZERO;
        if(dao.getCurrency() == 5) {
//            totalPrice = totalAmount.multiply(new BigDecimal(""+dao.getLastBandTic())).movePointLeft(6).setScale(8, RoundingMode.DOWN);
            SpannableString result;
            result = new SpannableString(dao.getCurrencySymbol() + " " +getDecimalFormat(c, 8).format(totalPrice));
            result.setSpan(new RelativeSizeSpan(0.8f), result.length() - 8, result.length(), SPAN_INCLUSIVE_INCLUSIVE);
            return result;

        } else {
//            totalPrice = totalAmount.multiply(new BigDecimal(""+dao.getLastBandTic())).movePointLeft(6).setScale(2, RoundingMode.DOWN);
            SpannableString result;
            result = new SpannableString(dao.getCurrencySymbol() + " " +getDecimalFormat(c, 2).format(totalPrice));
            result.setSpan(new RelativeSizeSpan(0.8f), result.length() - 2, result.length(), SPAN_INCLUSIVE_INCLUSIVE);
            return result;
        }
    }

    public static SpannableString getDpRawDollor(Context c, String price, int scale) {
        BigDecimal mPrice = new BigDecimal(price);
        SpannableString result;
        result = new SpannableString("$ " +getDecimalFormat(c, scale).format(mPrice));
        result.setSpan(new RelativeSizeSpan(0.8f), result.length() - scale, result.length(), SPAN_INCLUSIVE_INCLUSIVE);
        return result;
    }

    public static SpannableString getDpRawDollor(Context c, BigDecimal price, int scale) {
        SpannableString result;
        result = new SpannableString("$ " +getDecimalFormat(c, scale).format(price));
        result.setSpan(new RelativeSizeSpan(0.8f), result.length() - scale, result.length(), SPAN_INCLUSIVE_INCLUSIVE);
        return result;
    }

    public static SpannableString getZeroValue(Context c, BaseData dao) {
        if(dao.getCurrency() == 5) {
            SpannableString result;
            result = new SpannableString(dao.getCurrencySymbol() + " " +getDecimalFormat(c, 8).format(BigDecimal.ZERO));
            result.setSpan(new RelativeSizeSpan(0.8f), result.length() - 8, result.length(), SPAN_INCLUSIVE_INCLUSIVE);
            return result;
        } else {
            SpannableString result;
            result = new SpannableString(dao.getCurrencySymbol() + " " +getDecimalFormat(c, 2).format(BigDecimal.ZERO));
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

    public static SpannableString getPercentDp(BigDecimal input, int scale) {
        return getDpString(input.setScale(scale, RoundingMode.DOWN).toPlainString() + "%", scale + 1);
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

        } else if  (msg.type.equals(BaseConstant.COSMOS_MSG_TYPE_TRANSFER3)) {
            if (msg.value.inputs != null && msg.value.outputs != null) {
                for (Input input:msg.value.inputs) {
                    if (address.equals(input.address)) {
                        return BaseConstant.TX_TYPE_SEND;
                    }
                }
                for (Output output:msg.value.outputs) {
                    if (address.equals(output.address)) {
                        return BaseConstant.TX_TYPE_RECEIVE;
                    }
                }
            }
            result = BaseConstant.TX_TYPE_TRANSFER;

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
            result = BaseConstant.TX_TYPE_GET_COMMISSION;

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

        } else if (msg.type.equals(BaseConstant.IRIS_MSG_TYPE_ISSUE_TOKEN)) {
            result = BaseConstant.TX_TYPE_IRIS_ISSUE_TOKEN;

        } else if (msg.type.equals(BaseConstant.KAVA_MSG_TYPE_POST_PRICE)) {
            result = BaseConstant.TX_TYPE_KAVA_POST_PRICE;

        } else if (msg.type.equals(BaseConstant.KAVA_MSG_TYPE_CREATE_CDP)) {
            result = BaseConstant.TX_TYPE_KAVA_CREATE_CDP;

        } else if (msg.type.equals(BaseConstant.KAVA_MSG_TYPE_DEPOSIT_CDP)) {
            result = BaseConstant.TX_TYPE_KAVA_DEPOSIT_CDP;

        } else if (msg.type.equals(BaseConstant.KAVA_MSG_TYPE_WITHDRAW_CDP)) {
            result = BaseConstant.TX_TYPE_KAVA_WITHDRAW_CDP;

        } else if (msg.type.equals(BaseConstant.KAVA_MSG_TYPE_DRAWDEBT_CDP)) {
            result = BaseConstant.TX_TYPE_KAVA_DRAWDEBT_CDP;

        } else if (msg.type.equals(BaseConstant.KAVA_MSG_TYPE_REPAYDEBT_CDP)) {
            result = BaseConstant.TX_TYPE_KAVA_REPAYDEBT_CDP;

        } else if (msg.type.equals(BaseConstant.KAVA_MSG_TYPE_BEP3_CREATE_SWAP)) {
            result = BaseConstant.TX_TYPE_KAVA_BEP3_CREATE;

        } else if (msg.type.equals(BaseConstant.KAVA_MSG_TYPE_BEP3_CLAM_SWAP)) {
            result = BaseConstant.TX_TYPE_KAVA_BEP3_CLAIM;

        } else if (msg.type.equals(BaseConstant.KAVA_MSG_TYPE_BEP3_REFUND_SWAP)) {
            result = BaseConstant.TX_TYPE_KAVA_BEP3_REFUND;

        } else if (msg.type.equals(BaseConstant.KAVA_MSG_TYPE_INCENTIVE_REWARD)) {
            result = BaseConstant.TX_TYPE_KAVA_INCENTIVE_REWARD;

        }

        return result;
    }



    public static String DpTxType(Context c, ArrayList<Msg> msgs, String address) {
        String result = "";
        int dpType = getHistoryDpType(msgs, address);
        switch (dpType) {
            case BaseConstant.TX_TYPE_SEND:
                result = c.getString(R.string.tx_send);
                break;

            case BaseConstant.TX_TYPE_RECEIVE:
                result = c.getString(R.string.tx_receive);
                break;

            case BaseConstant.TX_TYPE_TRANSFER:
                result = c.getString(R.string.tx_transfer);
                break;

            case BaseConstant.TX_TYPE_DELEGATE:
                result = c.getString(R.string.tx_delegate);
                break;

            case BaseConstant.TX_TYPE_UNDELEGATE:
                result = c.getString(R.string.tx_undelegate);
                break;

            case BaseConstant.TX_TYPE_REDELEGATE:
                result = c.getString(R.string.tx_redelegate);
                break;

            case BaseConstant.TX_TYPE_GET_REWARD:
                result = c.getString(R.string.tx_get_reward);
                break;

            case BaseConstant.TX_TYPE_GET_COMMISSION:
                result = c.getString(R.string.tx_get_commission);
                break;

            case BaseConstant.TX_TYPE_CHAGE_REWARD_ADDRESS:
                result = c.getString(R.string.tx_change_reward_address);
                break;

            case BaseConstant.TX_TYPE_VOTE:
                result = c.getString(R.string.tx_vote);
                break;

            case BaseConstant.TX_TYPE_SUBMIT_PROPOSAL:
                result = c.getString(R.string.tx_submit_proposal);
                break;

            case BaseConstant.TX_TYPE_DEPOSIT:
                result = c.getString(R.string.tx_deposit);
                break;

            case BaseConstant.TX_TYPE_CREATE_VALIDATOR:
                result = c.getString(R.string.tx_create_validator);
                break;

            case BaseConstant.TX_TYPE_EDIT_VALIDATOR:
                result = c.getString(R.string.tx_edit_validator);
                break;

            case BaseConstant.TX_TYPE_REINVEST:
                result = c.getString(R.string.tx_reinvest);
                break;

            case BaseConstant.TX_TYPE_IRIS_GET_REWARD_ALL:
                result = c.getString(R.string.tx_get_reward_all);
                break;

            case BaseConstant.TX_TYPE_IRIS_ISSUE_TOKEN:
                result = c.getString(R.string.tx_issue_token);
                break;

            case BaseConstant.TX_TYPE_KAVA_POST_PRICE:
                result = c.getString(R.string.tx_kava_post_price);
                break;

            case BaseConstant.TX_TYPE_KAVA_CREATE_CDP:
                result = c.getString(R.string.tx_kava_create_cdp);
                break;

            case BaseConstant.TX_TYPE_KAVA_DEPOSIT_CDP:
                result = c.getString(R.string.tx_kava_deposit_cdp);
                break;

            case BaseConstant.TX_TYPE_KAVA_WITHDRAW_CDP:
                result = c.getString(R.string.tx_kava_withdraw_cdp);
                break;

            case BaseConstant.TX_TYPE_KAVA_DRAWDEBT_CDP:
                result = c.getString(R.string.tx_kava_drawdebt_cdp);
                break;

            case BaseConstant.TX_TYPE_KAVA_REPAYDEBT_CDP:
                result = c.getString(R.string.tx_kava_repaydebt_cdp);
                break;

            case BaseConstant.TX_TYPE_KAVA_BEP3_CREATE:
                result = c.getString(R.string.tx_kava_bep3_create);
                break;

            case BaseConstant.TX_TYPE_KAVA_BEP3_CLAIM:
                result = c.getString(R.string.tx_kava_bep3_claim);
                break;

            case BaseConstant.TX_TYPE_KAVA_BEP3_REFUND:
                result = c.getString(R.string.tx_kava_bep3_refund);
                break;

            case BaseConstant.TX_TYPE_KAVA_INCENTIVE_REWARD:
                result = c.getString(R.string.tx_kava_incentive_reward);
                break;

            case BaseConstant.TX_TYPE_UNKNOWN:
                result = c.getString(R.string.tx_known);
                break;

        }
        if (dpType != BaseConstant.TX_TYPE_REINVEST && msgs.size() > 1) {
            result = result + "\n+ " + (msgs.size() - 1);
        }
        return result;
    }

    public static String DpBNBTxType(Context c, BnbHistory history, String address) {
        String result = c.getString(R.string.tx_known);
        if (history.txType.equals("NEW_ORDER")) {
            result = c.getString(R.string.tx_new_order);

        } else if (history.txType.equals("CANCEL_ORDER")) {
            result = c.getString(R.string.tx_Cancel_order);

        } else if (history.txType.equals("TRANSFER")) {
            if (!TextUtils.isEmpty(history.fromAddr) && address.equals(history.fromAddr)) {
                result = c.getString(R.string.tx_send);
            } else {
                result = c.getString(R.string.tx_receive);
            }

        } else if (history.txType.equals("HTL_TRANSFER")) {
            if (history.fromAddr.equals(address)) {
                result = c.getString(R.string.tx_send_htlc);
            } else if (history.toAddr.equals(address)) {
                result = c.getString(R.string.tx_receive_htlc);
            } else {
                result = c.getString(R.string.tx_create_htlc);
            }

        } else if (history.txType.equals("CLAIM_HTL")) {
            result = c.getString(R.string.tx_claim_htlc);

        } else if (history.txType.equals("REFUND_HTL")) {
            result = c.getString(R.string.tx_refund_htlc);

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

    public static String getPath(BaseChain chain, int position, boolean newBip) {
        if (chain.equals(BNB_MAIN) || chain.equals(BNB_TEST)) {
            return BaseConstant.KEY_BNB_PATH + String.valueOf(position);

        } else if (chain.equals(IOV_MAIN)) {
            return BaseConstant.KEY_IOV_PATH + String.valueOf(position) +"'";

        } else if (chain.equals(KAVA_MAIN) || chain.equals(KAVA_TEST)) {
            if (newBip) {
                return BaseConstant.KEY_NEW_KAVA_PATH + String.valueOf(position);
            } else {
                return BaseConstant.KEY_PATH + String.valueOf(position);
            }

        } else if (chain.equals(BAND_MAIN)) {
            return BaseConstant.KEY_BAND_PATH + String.valueOf(position);

        } else if (chain.equals(IOV_TEST)) {
            return BaseConstant.KEY_NEW_IOV_PATH + String.valueOf(position);

        } else if (chain.equals(OK_TEST)) {
            return BaseConstant.KEY_NEW_OK_PATH + String.valueOf(position);

        } else {
            return BaseConstant.KEY_PATH + String.valueOf(position);

        }
    }


    public static DecimalFormat getDecimalFormat(Context c, int cnt) {
        NumberFormat formatter = NumberFormat.getNumberInstance(Locale.US);
        DecimalFormat decimalformat = (DecimalFormat)formatter;
        decimalformat.setRoundingMode(RoundingMode.DOWN);
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

    public static String getVestingTime(Context c, long startTime, ResLcdKavaAccountInfo.VestingPeriod vestingPeriod) {
        long unlockTime = (startTime + vestingPeriod.length) * 1000;
        return getDpTime(c, unlockTime);
    }

    public static String getUnbondTime(Context c, BaseChain chain) {
        String result = "??";
        try {
            if (chain.equals(COSMOS_MAIN) || chain.equals(IRIS_MAIN) || chain.equals(KAVA_MAIN) || chain.equals(KAVA_TEST) || chain.equals(BAND_MAIN) || chain.equals(OK_TEST)) {
                Calendar calendar = Calendar.getInstance();
                calendar.add(Calendar.DATE, 21);
                SimpleDateFormat unbondFormat = new SimpleDateFormat(c.getString(R.string.str_dp_time_format2));
                result = unbondFormat.format(calendar.getTimeInMillis());
                return result + "   " +c.getString(R.string.str_unbonding_21days_after);

            } else if (chain.equals(IOV_MAIN)){
                //TODO need check confirm
                Calendar calendar = Calendar.getInstance();
                calendar.add(Calendar.DATE, 3);
                SimpleDateFormat unbondFormat = new SimpleDateFormat(c.getString(R.string.str_dp_time_format2));
                result = unbondFormat.format(calendar.getTimeInMillis());
                return result + "   " +c.getString(R.string.str_unbonding_3days_after);

            } else if (chain.equals(IOV_TEST)){
                Calendar calendar = Calendar.getInstance();
                calendar.add(Calendar.DATE, 3);
                SimpleDateFormat unbondFormat = new SimpleDateFormat(c.getString(R.string.str_dp_time_format2));
                result = unbondFormat.format(calendar.getTimeInMillis());
                return result + "   " +c.getString(R.string.str_unbonding_3days_after);

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

    public static String getVestingTimeGap(Context c, long startTime, ResLcdKavaAccountInfo.VestingPeriod vestingPeriod) {
        long unlockTime = (startTime + vestingPeriod.length) * 1000;
        return getUnbondingTimeleft(c, unlockTime);

    }

    public static String getUnbondingTimeleft(Context c, long finishTime) {
        String result = "??";
        try {
            long now        = Calendar.getInstance().getTimeInMillis();
            long left       = finishTime - now;

            if (left >= BaseConstant.CONSTANT_D ) {
                result = "(" + (left / BaseConstant.CONSTANT_D) +" days remaining)";
            } else if (left >= BaseConstant.CONSTANT_H ) {
                result = "(" + (left / BaseConstant.CONSTANT_H) +" hours remaining)";
            }  else if (left >= BaseConstant.CONSTANT_M ) {
                result = "(" + (left / BaseConstant.CONSTANT_M) +" minutes remaining)";
            } else {
                return "Soon";
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

    public static String getDateformat(Context c, String rawValue) {
        String result = "??";
        try {
            SimpleDateFormat blockDateFormat = new SimpleDateFormat(c.getString(R.string.str_block_time_format));
            SimpleDateFormat myFormat = new SimpleDateFormat(c.getString(R.string.str_dp_date_format));
            blockDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            result = myFormat.format(blockDateFormat.parse(rawValue));
        } catch (Exception e) {};

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

    public static String getTimeTxGap(Context c, String rawValue) {
        String result = "";
        try {
            SimpleDateFormat blockDateFormat = new SimpleDateFormat(c.getString(R.string.str_tx_time_format));
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




    public static String cTimeString() {
        Calendar c = Calendar.getInstance();
        return ""+c.getTimeInMillis();
    }

    public static String threeMonthAgoTimeString() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, - 3);
        return ""+c.getTimeInMillis();
    }

    public static int getChainColor(Context c, BaseChain chain) {
        if (chain.equals(COSMOS_MAIN)) {
            return c.getResources().getColor(R.color.colorAtom);
        } else if (chain.equals(IRIS_MAIN)) {
            return c.getResources().getColor(R.color.colorIris);
        } else if (chain.equals(BNB_MAIN) || chain.equals(BNB_TEST)) {
            return c.getResources().getColor(R.color.colorBnb);
        } else if (chain.equals(KAVA_MAIN) || chain.equals(KAVA_TEST)) {
            return c.getResources().getColor(R.color.colorKava);
        } else if (chain.equals(IOV_MAIN) || chain.equals(IOV_TEST)) {
            return c.getResources().getColor(R.color.colorIov);
        } else if (chain.equals(BAND_MAIN)) {
            return c.getResources().getColor(R.color.colorBand);
        } else if (chain.equals(OK_TEST)) {
            return c.getResources().getColor(R.color.colorOK);
        }  else {
            return c.getResources().getColor(R.color.colorGray0);
        }
    }

    public static ColorStateList getTabColor(Context c, BaseChain chain) {
        if(chain.equals(COSMOS_MAIN)) {
            return c.getResources().getColorStateList(R.color.color_tab_myvalidator);
        } else if(chain.equals(IRIS_MAIN)) {
            return c.getResources().getColorStateList(R.color.color_tab_myvalidator_iris);
        } else if(chain.equals(KAVA_MAIN) || chain.equals(KAVA_TEST)) {
            return c.getResources().getColorStateList(R.color.color_tab_myvalidator_kava);
        } else if(chain.equals(BAND_MAIN)) {
            return c.getResources().getColorStateList(R.color.color_tab_myvalidator_band);
        } else if(chain.equals(IOV_MAIN) || chain.equals(IOV_TEST)) {
            return c.getResources().getColorStateList(R.color.color_tab_myvalidator_iov);
        } else if(chain.equals(OK_TEST)) {
            return c.getResources().getColorStateList(R.color.color_tab_myvalidator_ok);
        }
        return null;
    }

    public static ColorStateList getChainTintColor(Context c, BaseChain chain) {
        if(chain.equals(COSMOS_MAIN)) {
            return c.getResources().getColorStateList(R.color.colorAtom);
        } else if(chain.equals(IRIS_MAIN)) {
            return c.getResources().getColorStateList(R.color.colorIris);
        } else if(chain.equals(KAVA_MAIN) || chain.equals(KAVA_TEST)) {
            return c.getResources().getColorStateList(R.color.colorKava);
        } else if(chain.equals(BAND_MAIN)) {
            return c.getResources().getColorStateList(R.color.colorBand);
        } else if(chain.equals(IOV_MAIN) || chain.equals(IOV_TEST)) {
            return c.getResources().getColorStateList(R.color.colorIov);
        } else if(chain.equals(OK_TEST)) {
            return c.getResources().getColorStateList(R.color.colorOK);
        }
        return null;
    }

    public static void DpMainDenom(Context c, String chain, TextView textview) {
        if (BaseChain.getChain(chain).equals(COSMOS_MAIN)) {
            textview.setTextColor(c.getResources().getColor(R.color.colorAtom));
            textview.setText(c.getString(R.string.s_atom));

        } else if (BaseChain.getChain(chain).equals(IRIS_MAIN)) {
            textview.setTextColor(c.getResources().getColor(R.color.colorIris));
            textview.setText(c.getString(R.string.s_iris));

        } else if (BaseChain.getChain(chain).equals(BNB_MAIN) || BaseChain.getChain(chain).equals(BNB_TEST)) {
            textview.setTextColor(c.getResources().getColor(R.color.colorBnb));
            textview.setText(c.getString(R.string.s_bnb));

        } else if (BaseChain.getChain(chain).equals(KAVA_MAIN) || BaseChain.getChain(chain).equals(KAVA_TEST)) {
            textview.setTextColor(c.getResources().getColor(R.color.colorKava));
            textview.setText(c.getString(R.string.s_kava));

        } else if (BaseChain.getChain(chain).equals(IOV_MAIN) || BaseChain.getChain(chain).equals(IOV_TEST)) {
            textview.setTextColor(c.getResources().getColor(R.color.colorIov));
            textview.setText(c.getString(R.string.s_iov));

        } else if (BaseChain.getChain(chain).equals(BAND_MAIN)) {
            textview.setTextColor(c.getResources().getColor(R.color.colorBand));
            textview.setText(c.getString(R.string.s_band));

        } else if (BaseChain.getChain(chain).equals(OK_TEST)) {
            textview.setTextColor(c.getResources().getColor(R.color.colorOK));
            textview.setText(c.getString(R.string.s_tok));
        }
    }

    public static String getDpMainDenom(Context c, String chain) {
        if (BaseChain.getChain(chain).equals(COSMOS_MAIN)) {
            return c.getString(R.string.s_atom);
        } else if (BaseChain.getChain(chain).equals(IRIS_MAIN)) {
            return c.getString(R.string.s_iris);
        } else if (BaseChain.getChain(chain).equals(BNB_MAIN) || BaseChain.getChain(chain).equals(BNB_TEST)) {
            return c.getString(R.string.s_bnb);
        } else if (BaseChain.getChain(chain).equals(KAVA_MAIN) || BaseChain.getChain(chain).equals(KAVA_TEST)) {
            return c.getString(R.string.s_kava);
        } else if (BaseChain.getChain(chain).equals(IOV_MAIN) || BaseChain.getChain(chain).equals(IOV_TEST)) {
            return c.getString(R.string.s_iov);
        } else if (BaseChain.getChain(chain).equals(BAND_MAIN)) {
            return c.getString(R.string.s_band);
        } else if (BaseChain.getChain(chain).equals(OK_TEST)) {
            return c.getString(R.string.s_tok);
        }
        return "";

    }

    public static Spanned DpLiquidationPriceTitle(Context c, String Denom) {
        String strFront = c.getString(R.string.str_liquidation_title1);
//        String strChange = " <font color=\"#FFFFFF\">" + Denom + "</font> ";
        String strChange = " <font color=\"#7A7f88\">" + Denom + "</font> ";
        String strBack = c.getString(R.string.str_liquidation_title2);
        return Html.fromHtml(strFront + strChange + strBack);
    }

    public static Spanned DpBeforeLiquidationPriceTitle(Context c, String Denom) {
        String strFront = c.getString(R.string.str_before_liquidation_title1);
//        String strChange = " <font color=\"#FFFFFF\">" + Denom + "</font> ";
        String strChange = " <font color=\"#7A7f88\">" + Denom + "</font> ";
        String strBack = c.getString(R.string.str_liquidation_title2);
        return Html.fromHtml(strFront + strChange + strBack);
    }

    public static Spanned DpAfterLiquidationPriceTitle(Context c, String Denom) {
        String strFront = c.getString(R.string.str_after_liquidation_title1);
//        String strChange = " <font color=\"#FFFFFF\">" + Denom + "</font> ";
        String strChange = " <font color=\"#7A7f88\">" + Denom + "</font> ";
        String strBack = c.getString(R.string.str_liquidation_title2);
        return Html.fromHtml(strFront + strChange + strBack);
    }

    public static Spanned DpCurrentPriceTitle(Context c, String Denom) {
        String strFront = c.getString(R.string.str_current_title1);
//        String strChange = " <font color=\"#FFFFFF\">" + Denom + "</font> ";
        String strChange = " <font color=\"#7A7f88\">" + Denom + "</font> ";
        String strBack = c.getString(R.string.str_current_title2);
        return Html.fromHtml(strFront + strChange + strBack);
    }

    public static Spanned DpCollateralTitle(Context c, String Denom) {
        String strFront = c.getString(R.string.str_collateral_title1);
//        String strChange = " <font color=\"#FFFFFF\">" + Denom + "</font> ";
        String strChange = " <font color=\"#7A7f88\">" + Denom + "</font> ";
        String strBack = c.getString(R.string.str_collateral_title2);
        return Html.fromHtml(strFront + strChange + strBack);
    }

    public static Spanned DpLoanedTitle(Context c, String Denom) {
        String strFront = c.getString(R.string.str_loaned_title1);
//        String strChange = " <font color=\"#FFFFFF\">" + Denom + "</font> ";
        String strChange = " <font color=\"#7A7f88\">" + Denom + "</font> ";
        String strBack = c.getString(R.string.str_loaned_title2);
        return Html.fromHtml(strFront + strChange + strBack);
    }

    public static Spanned DpPaymentTitle(Context c, String Denom) {
        String strFront = c.getString(R.string.str_payment_title1);
//        String strChange = " <font color=\"#FFFFFF\">" + Denom + "</font> ";
        String strChange = " <font color=\"#7A7f88\">" + Denom + "</font> ";
        String strBack = c.getString(R.string.str_payment_title2);
        return Html.fromHtml(strFront + strChange + strBack);
    }

    public static Spanned DpRemainDebtTitle(Context c, String Denom) {
        String strFront = c.getString(R.string.str_remain_debt_title1);
//        String strChange = " <font color=\"#FFFFFF\">" + Denom + "</font> ";
        String strChange = " <font color=\"#7A7f88\">" + Denom + "</font> ";
        String strBack = c.getString(R.string.str_remain_debt_title2);
        return Html.fromHtml(strFront + strChange + strBack);
    }

    public static Spanned DpCollateralValueTitle(Context c, String Denom) {
        String strFront = c.getString(R.string.str_collateral_value_title1);
//        String strChange = " <font color=\"#FFFFFF\">" + Denom + "</font> ";
        String strChange = " <font color=\"#7A7f88\">" + Denom + "</font> ";
        String strBack = c.getString(R.string.str_collateral_value_title2);
        return Html.fromHtml(strFront + strChange + strBack);
    }

    public static void DpRiskRate(Context c, BigDecimal riskRate, TextView textView, ImageView imageview) {
        textView.setText(WDp.getDpAmount2(c, riskRate, 0, 2));
        if (riskRate.longValue() < 50) {
            textView.setTextColor(c.getResources().getColor(R.color.colorCdpSafe));
            if (imageview != null) {
                imageview.setImageDrawable(c.getResources().getDrawable(R.drawable.img_safe));
            }

        } else if (riskRate.longValue() < 80) {
            textView.setTextColor(c.getResources().getColor(R.color.colorCdpStable));
            if (imageview != null) {
                imageview.setImageDrawable(c.getResources().getDrawable(R.drawable.img_stable));
            }

        } else {
            textView.setTextColor(c.getResources().getColor(R.color.colorCdpDanger));
            if (imageview != null) {
                imageview.setImageDrawable(c.getResources().getDrawable(R.drawable.img_danger));

            }
        }

    }

    public static void DpRiskButton(Context c, BigDecimal riskRate, Button button) {
        if (riskRate.longValue() <= 50) {
            button.setBackground(c.getResources().getDrawable(R.drawable.btn_score_safe_fill));
            button.setTextColor(c.getResources().getColor(R.color.colorBlack));
            button.setTypeface(null, Typeface.BOLD);
            button.setText("SAFE " + riskRate.toPlainString());

        } else if (riskRate.longValue() < 80) {
            button.setBackground(c.getResources().getDrawable(R.drawable.btn_score_stable_fill));
            button.setTextColor(c.getResources().getColor(R.color.colorBlack));
            button.setTypeface(null, Typeface.BOLD);
            button.setText("STABLE " + riskRate.toPlainString());

        } else {
            button.setBackground(c.getResources().getDrawable(R.drawable.btn_score_danger_fill));
            button.setTextColor(c.getResources().getColor(R.color.colorBlack));
            button.setTypeface(null, Typeface.BOLD);
            button.setText("DANGER " + riskRate.toPlainString());
        }
    }

    public static void DpRiskButton2(Context c, BigDecimal riskRate, Button button) {
        if (riskRate.longValue() <= 50) {
            button.setBackground(c.getResources().getDrawable(R.drawable.btn_score_safe_fill));
            button.setTextColor(c.getResources().getColor(R.color.colorBlack));
            button.setTypeface(null, Typeface.BOLD);
            button.setText("SAFE");

        } else if (riskRate.longValue() < 80) {
            button.setBackground(c.getResources().getDrawable(R.drawable.btn_score_stable_fill));
            button.setTextColor(c.getResources().getColor(R.color.colorBlack));
            button.setTypeface(null, Typeface.BOLD);
            button.setText("STABLE");

        } else {
            button.setBackground(c.getResources().getDrawable(R.drawable.btn_score_danger_fill));
            button.setTextColor(c.getResources().getColor(R.color.colorBlack));
            button.setTypeface(null, Typeface.BOLD);
            button.setText("DANGER");
        }
    }

    public static void DpRiskRate2(Context c, BigDecimal riskRate, TextView text, TextView rate, LinearLayout layer) {
        rate.setText(WDp.getDpAmount2(c, riskRate, 0, 2));
        if (riskRate.longValue() <= 50) {
            text.setText("SAFE");
            layer.setBackground(c.getResources().getDrawable(R.drawable.btn_score_safe_fill));

        } else if (riskRate.longValue() < 80) {
            text.setText("STABLE");
            layer.setBackground(c.getResources().getDrawable(R.drawable.btn_score_stable_fill));

        } else {
            text.setText("DANGER");
            layer.setBackground(c.getResources().getDrawable(R.drawable.btn_score_danger_fill));
        }
    }

    public static void DpRiskRate3(Context c, BigDecimal riskRate, TextView score, TextView rate) {
        score.setText(WDp.getDpAmount2(c, riskRate, 0, 2));
        if (riskRate.longValue() <= 50) {
            rate.setText("SAFE");
            rate.setTextColor(c.getResources().getColor(R.color.colorCdpSafe));
            score.setTextColor(c.getResources().getColor(R.color.colorCdpSafe));

        } else if (riskRate.longValue() < 80) {
            rate.setText("STABLE");
            rate.setTextColor(c.getResources().getColor(R.color.colorCdpStable));
            score.setTextColor(c.getResources().getColor(R.color.colorCdpStable));

        } else {
            rate.setText("DANGER");
            rate.setTextColor(c.getResources().getColor(R.color.colorCdpDanger));
            score.setTextColor(c.getResources().getColor(R.color.colorCdpDanger));
        }
    }

    public static BigDecimal getCdpHiddenFee(Context c, BigDecimal outstandingDebt,  ResCdpParam.KavaCollateralParam paramCdp, KavaCDP myCdp) {
        BigDecimal result = BigDecimal.ZERO;
        try {
            long now   = Calendar.getInstance().getTimeInMillis();
            SimpleDateFormat blockDateFormat = new SimpleDateFormat(c.getString(R.string.str_block_time_format));
            blockDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            long start = blockDateFormat.parse(myCdp.fees_updated).getTime();
            Long gap  = (now - start)/1000;
            //TODO 냥냥하게 패딩
            gap = gap + 30;

            Double double1 = Double.parseDouble(paramCdp.stability_fee);
            Double double2 = gap.doubleValue();

            Double pow = Math.pow(double1, double2);
            result = outstandingDebt.multiply(new BigDecimal(pow.toString())).setScale(0, RoundingMode.UP).subtract(outstandingDebt);
            return result;
        } catch (Exception e) {
            WLog.w("e " + e.getMessage());
        }
        return result;
    }

    public static void onDpChain(Context c, BaseChain chain, ImageView imgView, TextView txtView) {
        if (chain.equals(COSMOS_MAIN)) {
            if (imgView != null) imgView.setImageDrawable(c.getResources().getDrawable(R.drawable.cosmos_wh_main));
            txtView.setText(c.getString(R.string.str_cosmos_hub_2));

        } else if (chain.equals(IRIS_MAIN)) {
            if (imgView != null) imgView.setImageDrawable(c.getResources().getDrawable(R.drawable.iris_wh));
            txtView.setText(c.getString(R.string.str_iris_net_2));

        } else if (chain.equals(BNB_MAIN)) {
            if (imgView != null) imgView.setImageDrawable(c.getResources().getDrawable(R.drawable.binance_ch_img));
            txtView.setText(c.getString(R.string.str_binance_net_2));

        } else if (chain.equals(BNB_TEST)) {
            if (imgView != null) imgView.setImageDrawable(c.getResources().getDrawable(R.drawable.binancetestnet));
            txtView.setText(c.getString(R.string.str_binance_test_net_2));

        } else if (chain.equals(KAVA_MAIN)) {
            if (imgView != null) imgView.setImageDrawable(c.getResources().getDrawable(R.drawable.kava_img));
            txtView.setText(c.getString(R.string.str_kava_net_2));

        } else if (chain.equals(KAVA_TEST)) {
            if (imgView != null) imgView.setImageDrawable(c.getResources().getDrawable(R.drawable.kava_test_img));
            txtView.setText(c.getString(R.string.str_kava_net_test_2));

        } else if (chain.equals(IOV_MAIN)) {
            if (imgView != null) imgView.setImageDrawable(c.getResources().getDrawable(R.drawable.iov_chain_img));
            txtView.setText(c.getString(R.string.str_iov_net_2));

        } else if (chain.equals(BAND_MAIN)) {
            if (imgView != null) imgView.setImageDrawable(c.getResources().getDrawable(R.drawable.band_chain_img));
            txtView.setText(c.getString(R.string.str_band_chain_2));

        } else if (chain.equals(IOV_TEST)) {
            if (imgView != null) imgView.setImageDrawable(c.getResources().getDrawable(R.drawable.iov_testnet_img));
            txtView.setText(c.getString(R.string.str_iov_net_test_2));

        } else if (chain.equals(OK_TEST)) {
            if (imgView != null) imgView.setImageDrawable(c.getResources().getDrawable(R.drawable.okex_testnet_img));
            txtView.setText(c.getString(R.string.str_ok_net_test_2));

        }
        txtView.setTextColor(getChainColor(c, chain));


    }

    public static String getKavaHtlcStatus(Context c, ResTxInfo resTxInfo, ResKavaSwapInfo resKavaSwapInfo) {
        if (!resTxInfo.isSuccess()) {
            return c.getString(R.string.str_bep3_status_open);
        }

        if (resKavaSwapInfo == null) {
            return c.getString(R.string.str_bep3_status_completed);
        }

        if (resKavaSwapInfo.result.status.equals(ResKavaSwapInfo.STATUS_EXPIRED)) {
            return c.getString(R.string.str_bep3_status_expired);
        } else if (resKavaSwapInfo.result.status.equals(ResKavaSwapInfo.STATUS_OPEN)) {
            return c.getString(R.string.str_bep3_status_open);
        } else {
            return c.getString(R.string.str_bep3_status_completed);
        }
    }

    public static String getBnbHtlcStatus(Context c, ResBnbSwapInfo resBnbSwapInfo, ResBnbNodeInfo resBnbNodeInfo) {
        if (resBnbSwapInfo == null || resBnbNodeInfo == null) {
            return "-";
        }
        if (resBnbSwapInfo.status == BNB_STATUS_REFUNDED) {
            return c.getString(R.string.str_bep3_status_refunded);

        } else if (resBnbSwapInfo.status == BNB_STATUS_COMPLETED) {
            return c.getString(R.string.str_bep3_status_completed);

        } else if (resBnbSwapInfo.status == BNB_STATUS_OPEN && resBnbSwapInfo.expireHeight < resBnbNodeInfo.getCHeight()) {
            return c.getString(R.string.str_bep3_status_expired);

        }
        return c.getString(R.string.str_bep3_status_open);

    }

}
