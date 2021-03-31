package wannabit.io.cosmostaion.utils;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.RelativeSizeSpan;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.protobuf2.Any;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import cosmos.base.abci.v1beta1.Abci;
import cosmos.base.v1beta1.CoinOuterClass;
import cosmos.distribution.v1beta1.Distribution;
import cosmos.gov.v1beta1.Gov;
import cosmos.params.v1beta1.Params;
import cosmos.staking.v1beta1.Staking;
import cosmos.tx.v1beta1.ServiceOuterClass;
import cosmos.upgrade.v1beta1.Upgrade;
import cosmos.vesting.v1beta1.Vesting;
import ibc.core.client.v1.Client;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.dao.Balance;
import wannabit.io.cosmostaion.dao.BondingState;
import wannabit.io.cosmostaion.dao.OkTicker;
import wannabit.io.cosmostaion.dao.OkToken;
import wannabit.io.cosmostaion.dao.Reward;
import wannabit.io.cosmostaion.dao.UnBondingState;
import wannabit.io.cosmostaion.model.kava.Cdp;
import wannabit.io.cosmostaion.model.kava.CollateralParam;
import wannabit.io.cosmostaion.model.kava.MarketPrice;
import wannabit.io.cosmostaion.model.type.BnbHistory;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.model.type.Input;
import wannabit.io.cosmostaion.model.type.Msg;
import wannabit.io.cosmostaion.model.type.Output;
import wannabit.io.cosmostaion.model.type.Validator;
import wannabit.io.cosmostaion.network.res.ResBnbSwapInfo;
import wannabit.io.cosmostaion.network.res.ResKavaSwapInfo;
import wannabit.io.cosmostaion.network.res.ResNodeInfo;
import wannabit.io.cosmostaion.network.res.ResOkHistory;
import wannabit.io.cosmostaion.network.res.ResOkStaking;
import wannabit.io.cosmostaion.network.res.ResOkUnbonding;
import wannabit.io.cosmostaion.network.res.ResTxInfo;

import static android.text.Spanned.SPAN_INCLUSIVE_INCLUSIVE;
import static wannabit.io.cosmostaion.base.BaseChain.AKASH_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.BAND_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.BNB_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.BNB_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.CERTIK_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.CERTIK_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.COSMOS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.COSMOS_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.IOV_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.IOV_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.IRIS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.IRIS_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.KAVA_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.KAVA_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.OKEX_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.OK_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.PERSIS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.SECRET_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.SENTINEL_MAIN;
import static wannabit.io.cosmostaion.base.BaseConstant.AKASH_VAL_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.BAND_VAL_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.CERTIK_VAL_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.COSMOS_VAL_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.DAY_SEC;
import static wannabit.io.cosmostaion.base.BaseConstant.IOV_VAL_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.IRIS_VAL_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.KAVA_VAL_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.MONTH_SEC;
import static wannabit.io.cosmostaion.base.BaseConstant.OKEX_VAL_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.PERSIS_VAL_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.SECRET_VAL_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.SENTINEL_VAL_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_AKASH;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_ATOM;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_BAND;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_BNB;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_CERTIK;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_COSMOS_TEST;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_DVPN;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HARD;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_IOV;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_IOV_TEST;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_IRIS;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_IRIS_TEST;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_KAVA;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_OK;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_SECRET;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_XPRT;
import static wannabit.io.cosmostaion.base.BaseConstant.YEAR_SEC;
import static wannabit.io.cosmostaion.network.res.ResBnbSwapInfo.BNB_STATUS_COMPLETED;
import static wannabit.io.cosmostaion.network.res.ResBnbSwapInfo.BNB_STATUS_OPEN;
import static wannabit.io.cosmostaion.network.res.ResBnbSwapInfo.BNB_STATUS_REFUNDED;
import static wannabit.io.cosmostaion.network.res.ResOkHistory.OK_TYPE_CANCEL_ORDER;
import static wannabit.io.cosmostaion.network.res.ResOkHistory.OK_TYPE_NEW_ORDER;
import static wannabit.io.cosmostaion.network.res.ResOkHistory.OK_TYPE_SIDE_SEND;
import static wannabit.io.cosmostaion.network.res.ResOkHistory.OK_TYPE_TRANSFER;

public class WDp {
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

    public static SpannableString getDpGasRate(String input) {
        SpannableString result;
        result = new SpannableString(input);
        result.setSpan(new RelativeSizeSpan(0.8f), 2, result.length(), SPAN_INCLUSIVE_INCLUSIVE);
        return result;
    }



    public static void showCoinDp(Context c, Coin coin, TextView denomTv, TextView amountTv, BaseChain chain) {
        if (chain.equals(COSMOS_MAIN)) {
            DpMainDenom(c, chain.getChain(), denomTv);
            amountTv.setText(getDpAmount2(c, new BigDecimal(coin.amount), 6, 6));

        } else if (chain.equals(IRIS_MAIN)) {
            if (coin.denom.equals(TOKEN_IRIS)) {
                DpMainDenom(c, chain.getChain(), denomTv);
            } else {
                denomTv.setText(coin.denom.toUpperCase());
            }
            amountTv.setText(getDpAmount2(c, new BigDecimal(coin.amount), 6, 6));


        } else if (chain.equals(KAVA_MAIN) || chain.equals(KAVA_TEST)) {
            if (coin.denom.equals(TOKEN_KAVA)) {
                DpMainDenom(c, chain.getChain(), denomTv);
            } else if (coin.denom.equals(TOKEN_HARD)) {
                denomTv.setText(coin.denom.toUpperCase());
                denomTv.setTextColor(c.getResources().getColor(R.color.colorHard));
            } else {
                denomTv.setText(coin.denom.toUpperCase());
                denomTv.setTextColor(c.getResources().getColor(R.color.colorWhite));
            }
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

        } else if (chain.equals(OKEX_MAIN) || chain.equals(OK_TEST)) {
            if (coin.denom.equals(TOKEN_OK)) {
                DpMainDenom(c, chain.getChain(), denomTv);
            } else {
                denomTv.setText(coin.denom.toUpperCase());
            }
            amountTv.setText(getDpAmount2(c, new BigDecimal(coin.amount), 0, 18));

        } else if (chain.equals(CERTIK_MAIN) || chain.equals(CERTIK_TEST)) {
            DpMainDenom(c, chain.getChain(), denomTv);
            amountTv.setText(getDpAmount2(c, new BigDecimal(coin.amount), 6, 6));

        } else if (chain.equals(SECRET_MAIN)) {
            DpMainDenom(c, chain.getChain(), denomTv);
            amountTv.setText(getDpAmount2(c, new BigDecimal(coin.amount), 6, 6));

        } else if (chain.equals(AKASH_MAIN)) {
            DpMainDenom(c, chain.getChain(), denomTv);
            amountTv.setText(getDpAmount2(c, new BigDecimal(coin.amount), 6, 6));

        } else if (chain.equals(PERSIS_MAIN)) {
            DpMainDenom(c, chain.getChain(), denomTv);
            amountTv.setText(getDpAmount2(c, new BigDecimal(coin.amount), 6, 6));

        } else if (chain.equals(SENTINEL_MAIN)) {
            DpMainDenom(c, chain.getChain(), denomTv);
            amountTv.setText(getDpAmount2(c, new BigDecimal(coin.amount), 6, 6));

        } else if (chain.equals(COSMOS_TEST)) {
            if (coin.denom.equals(TOKEN_COSMOS_TEST)) {
                DpMainDenom(c, chain.getChain(), denomTv);
            } else {
                denomTv.setText(coin.denom.toUpperCase());
            }
            amountTv.setText(getDpAmount2(c, new BigDecimal(coin.amount), 6, 6));

        } else if (chain.equals(IRIS_TEST)) {
            if (coin.denom.equals(TOKEN_IRIS_TEST)) {
                DpMainDenom(c, chain.getChain(), denomTv);
            } else {
                denomTv.setText(coin.denom.toUpperCase());
            }
            amountTv.setText(getDpAmount2(c, new BigDecimal(coin.amount), 6, 6));

        }
    }

    public static void showCoinDp(Context c, String symbol, String amount, TextView denomTv, TextView amountTv, BaseChain chain) {
        if (chain.equals(COSMOS_MAIN)) {
            DpMainDenom(c, chain.getChain(), denomTv);
            amountTv.setText(getDpAmount2(c, new BigDecimal(amount), 6, 6));

        } else if (chain.equals(IRIS_MAIN)) {
            if (symbol.equals(TOKEN_IRIS)) {
                DpMainDenom(c, chain.getChain(), denomTv);
            } else {
                denomTv.setText(symbol.toUpperCase());
            }
            amountTv.setText(getDpAmount2(c, new BigDecimal(amount), 6, 6));

        } else if (chain.equals(KAVA_MAIN) || chain.equals(KAVA_TEST)) {
            if (symbol.equals(TOKEN_KAVA)) {
                DpMainDenom(c, chain.getChain(), denomTv);
            } else if (symbol.equals(TOKEN_HARD)) {
                denomTv.setText(symbol.toUpperCase());
                denomTv.setTextColor(c.getResources().getColor(R.color.colorHard));
            } else {
                denomTv.setText(symbol.toUpperCase());
                denomTv.setTextColor(c.getResources().getColor(R.color.colorWhite));
            }
            if (amountTv != null) amountTv.setText(getDpAmount2(c, new BigDecimal(amount), WUtil.getKavaCoinDecimal(symbol), WUtil.getKavaCoinDecimal(symbol)));

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

        } else if (chain.equals(OKEX_MAIN) || chain.equals(OK_TEST)) {
            if (symbol.equals(TOKEN_OK)) {
                DpMainDenom(c, chain.getChain(), denomTv);
            } else {
                denomTv.setText(symbol.toUpperCase());
            }
            amountTv.setText(getDpAmount2(c, new BigDecimal(amount), 0, 18));

        } else if (chain.equals(CERTIK_MAIN) || chain.equals(CERTIK_TEST)) {
            DpMainDenom(c, chain.getChain(), denomTv);
            amountTv.setText(getDpAmount2(c, new BigDecimal(amount), 6, 6));

        } else if (chain.equals(SECRET_MAIN)) {
            DpMainDenom(c, chain.getChain(), denomTv);
            amountTv.setText(getDpAmount2(c, new BigDecimal(amount), 6, 6));

        } else if (chain.equals(AKASH_MAIN)) {
            DpMainDenom(c, chain.getChain(), denomTv);
            amountTv.setText(getDpAmount2(c, new BigDecimal(amount), 6, 6));

        } else if (chain.equals(PERSIS_MAIN)) {
            DpMainDenom(c, chain.getChain(), denomTv);
            amountTv.setText(getDpAmount2(c, new BigDecimal(amount), 6, 6));

        } else if (chain.equals(SENTINEL_MAIN)) {
            DpMainDenom(c, chain.getChain(), denomTv);
            amountTv.setText(getDpAmount2(c, new BigDecimal(amount), 6, 6));

        } else if (chain.equals(COSMOS_TEST)) {
            if (symbol.equals(TOKEN_COSMOS_TEST)) {
                DpMainDenom(c, chain.getChain(), denomTv);
            } else {
                denomTv.setText(symbol.toUpperCase());
            }
            amountTv.setText(getDpAmount2(c, new BigDecimal(amount), 6, 6));
        } else if (chain.equals(IRIS_TEST)) {
            if (symbol.equals(TOKEN_IRIS_TEST)) {
                DpMainDenom(c, chain.getChain(), denomTv);
            } else {
                denomTv.setText(symbol.toUpperCase());
            }
            amountTv.setText(getDpAmount2(c, new BigDecimal(amount), 6, 6));

        }
    }

    public static SpannableString getDpAllRewardAmount(Context c, ArrayList<Reward> rewards, BaseChain chain, String denom) {
        BigDecimal sum = BigDecimal.ZERO;
        for(Reward reward : rewards) {
            sum = sum.add(reward.getRewardAmount(denom).setScale(0, BigDecimal.ROUND_DOWN));
        }
        return getDpAmount2(c, sum, 6, 6);
    }

    public static BigDecimal getAllRewardAmount(ArrayList<Reward> rewards, String denom) {
        BigDecimal sum = BigDecimal.ZERO;
        for(Reward reward : rewards) {
            sum = sum.add(reward.getRewardAmount(denom).setScale(0, BigDecimal.ROUND_DOWN));
        }
        return sum;
    }

    public static SpannableString getValidatorReward(Context c, ArrayList<Reward> rewards, String valOpAddress, BaseChain chain, String denom) {
        BigDecimal result = BigDecimal.ZERO;
        for(Reward reward : rewards) {
            if (reward.validatorAddress.equals(valOpAddress)) {
                result = reward.getRewardAmount(denom);
                break;
            }
        }
        return getDpAmount2(c, result, 6, 6);
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


    //get reward without commission per block per one staking coin
    public static BigDecimal getYieldPerBlock(BaseData baseData, BaseChain chain) {
        BigDecimal result = BigDecimal.ZERO;
        if (chain.equals(COSMOS_MAIN) || chain.equals(AKASH_MAIN) || chain.equals(PERSIS_MAIN) || chain.equals(COSMOS_TEST)) {
            if (baseData == null || baseData.mGrpcStakingPool == null || baseData.mGrpcProvision == null || baseData.mGrpcParamMint == null) { return result; }
            BigDecimal provisions = baseData.mGrpcProvision;
            BigDecimal bonded = new BigDecimal(baseData.mGrpcStakingPool.getBondedTokens());
            BigDecimal blocksPerYear = new BigDecimal(baseData.mGrpcParamMint.getBlocksPerYear());
            return provisions.divide(bonded, 24, RoundingMode.DOWN).divide(blocksPerYear, 24, RoundingMode.DOWN);

        } else if (chain.equals(IRIS_MAIN) || chain.equals(IRIS_TEST)) {
            if (baseData == null || baseData.mGrpcStakingPool == null || baseData.mGrpcIrisParamMint == null) { return result; }
            BigDecimal bonded = new BigDecimal(baseData.mGrpcStakingPool.getBondedTokens());
            BigDecimal unbonded = new BigDecimal(baseData.mGrpcStakingPool.getNotBondedTokens());
            BigDecimal inflation_base = new BigDecimal("2000000000000000");
            BigDecimal provisions = inflation_base.multiply(new BigDecimal(baseData.mGrpcIrisParamMint.getInflation())).movePointLeft(18);
            return provisions.divide(bonded, 24, RoundingMode.DOWN).divide(new BigDecimal("6311520"), 24, RoundingMode.DOWN);

        } else {
            if (baseData == null || baseData.mStakingPool == null || baseData.mProvisions == null || baseData.mMintParam == null) { return result; }
            BigDecimal provisions = baseData.mProvisions;
            BigDecimal bonded = new BigDecimal(baseData.mStakingPool.result.bonded_tokens);
            BigDecimal blocksPerYear = new BigDecimal(baseData.mMintParam.blocks_per_year);
            return provisions.divide(bonded, 24, RoundingMode.DOWN).divide(blocksPerYear, 24, RoundingMode.DOWN);
        }
    }

    //display estimate apr by own checked block time
    public static SpannableString getDpEstApr(BaseData baseData, BaseChain chain) {
        BigDecimal rpr = getYieldPerBlock(baseData, chain);
        BigDecimal estApr = YEAR_SEC.divide(WUtil.getCBlockTime(chain), 24, RoundingMode.DOWN).multiply(rpr).movePointRight(2);
        return getPercentDp(estApr);
    }

    public static SpannableString getDpEstAprCommission(BaseData baseData, BaseChain chain, BigDecimal commission) {
        BigDecimal rpr = getYieldPerBlock(baseData, chain);
        BigDecimal commissionCal = BigDecimal.ONE.subtract(commission);
        BigDecimal estAprCommission = YEAR_SEC.divide(WUtil.getCBlockTime(chain), 24, RoundingMode.DOWN).multiply(commissionCal).multiply(rpr).movePointRight(2);
        return getPercentDp(estAprCommission);
    }

    public static SpannableString getDailyReward(Context c, BaseData baseData, BigDecimal commission, BigDecimal delegated, BaseChain chain) {
        BigDecimal rpr = getYieldPerBlock(baseData, chain);
        BigDecimal commissionCal = BigDecimal.ONE.subtract(commission);
        BigDecimal estDpr = DAY_SEC.multiply(commissionCal).multiply(rpr).multiply(delegated).divide(WUtil.getCBlockTime(chain), 12, RoundingMode.DOWN);
        return getDpAmount2(c, estDpr, 6, 12);
    }

    public static SpannableString getMonthlyReward(Context c, BaseData baseData, BigDecimal commission, BigDecimal delegated, BaseChain chain) {
        BigDecimal rpr = getYieldPerBlock(baseData, chain);
        BigDecimal commissionCal = BigDecimal.ONE.subtract(commission);
        BigDecimal estDpr = MONTH_SEC.multiply(commissionCal).multiply(rpr).multiply(delegated).divide(WUtil.getCBlockTime(chain), 12, RoundingMode.DOWN);
        return getDpAmount2(c, estDpr, 6, 12);
    }


    public static BigDecimal getAvailableCoin(ArrayList<Balance> balances, String denom) {
        BigDecimal sum = BigDecimal.ZERO;
        for (Balance balance : balances) {
            if (balance.symbol.equalsIgnoreCase(denom)) {
                sum = balance.balance;
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

    public static BigDecimal getOkDepositCoin(ResOkStaking deposit) {
        BigDecimal sum = BigDecimal.ZERO;
        if (deposit != null && !TextUtils.isEmpty(deposit.tokens)) {
            sum = new BigDecimal(deposit.tokens);
        }
        return sum;
    }

    public static BigDecimal getOkWithdrawingCoin(ResOkUnbonding withdraw) {
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

    public static BigDecimal getKavaVestingAmount(ArrayList<Balance> balances, String denom) {
        BigDecimal sum = BigDecimal.ZERO;
        for (Balance balance : balances) {
            if (balance.symbol.equalsIgnoreCase(denom)) {
                sum = balance.frozen;
            }
        }
        return sum;
    }

    public static BigDecimal getKavaTokenAll(BaseData baseData, ArrayList<Balance> balances, String denom) {
        BigDecimal sum = BigDecimal.ZERO;
        for (Balance balance : balances) {
            if (balance.symbol.equals(denom)) {
                sum = sum.add(balance.balance);
                sum = sum.add(balance.frozen);
            }
        }
        return sum;
    }

    public static BigDecimal kavaTokenDollorValue(BaseChain chain, BaseData baseData, String denom, BigDecimal amount) {
        int dpDecimal = WUtil.getKavaCoinDecimal(denom);
        if (denom.equals("usdx") || denom.equals("busd")) {
            return amount.movePointLeft(dpDecimal);

        } else {
            HashMap<String, MarketPrice> prices = baseData.mKavaTokenPrices;
            if (denom.equals("hard") && prices.get("hard:usd:30") != null) {
                BigDecimal price = new BigDecimal(prices.get("hard:usd:30").price);
                return amount.movePointLeft(dpDecimal).multiply(price);

            } else if (denom.contains("btc") && prices.get("btc:usd:30") != null) {
                BigDecimal price = new BigDecimal(prices.get("btc:usd:30").price);
                return amount.movePointLeft(dpDecimal).multiply(price);

            } else if (denom.contains("bnb") && prices.get("bnb:usd:30") != null) {
                BigDecimal price = new BigDecimal(prices.get("bnb:usd:30").price);
                return amount.movePointLeft(dpDecimal).multiply(price);

            } else if (denom.contains("xrp") && prices.get("xrp:usd:30") != null) {
                BigDecimal price = new BigDecimal(prices.get("xrp:usd:30").price);
                return amount.movePointLeft(dpDecimal).multiply(price);
            }
        }
        return BigDecimal.ZERO;

    }

    public static BigDecimal okExTokenDollorValue(BaseData baseData, OkToken okToken, BigDecimal amount) {
        if (okToken.original_symbol.equals("usdt") || okToken.original_symbol.equals("usdc") || okToken.original_symbol.equals("usdk")) {
            return amount;

        } else if (okToken.original_symbol.equals("okb") && baseData.mOKBPrice != null) {
            return amount.multiply(baseData.mOKBPrice);

        } else if (baseData.mOkTickersList != null) {
            //TODO display with ticker update!
            ArrayList<OkTicker> tickers = baseData.mOkTickersList.data;
            return BigDecimal.ZERO;

        }
        return BigDecimal.ZERO;
    }

    public static BigDecimal getMainAssetValue(Context c, BaseData dao, BigDecimal amount, BaseChain chain) {
        int dpDecimal = dao.getCurrency() == 5 ? 8 : 2;
        BigDecimal price = dao.getLastPriceTic(chain);
        if (chain.equals(COSMOS_MAIN) || chain.equals(IRIS_MAIN) || chain.equals(KAVA_MAIN) || chain.equals(AKASH_MAIN) || chain.equals(PERSIS_MAIN) || chain.equals(SENTINEL_MAIN) ||
                chain.equals(COSMOS_TEST) || chain.equals(IRIS_TEST) || chain.equals(KAVA_TEST)) {
            return amount.multiply(price).movePointLeft(6).setScale(dpDecimal, RoundingMode.DOWN);
        } else if (chain.equals(OKEX_MAIN) || chain.equals(OK_TEST)) {
            return amount.multiply(price).setScale(dpDecimal, RoundingMode.DOWN);
        } else {
            return BigDecimal.ZERO;
        }
    }

    public static SpannableString getDpMainAssetValue(Context c, BaseData dao, BigDecimal amount, BaseChain chain) {
        int dpDecimal = dao.getCurrency() == 5 ? 8 : 2;
        SpannableString result = new SpannableString(dao.getCurrencySymbol() + " " +getDecimalFormat(c, dpDecimal).format(getMainAssetValue(c, dao, amount, chain)));
        result.setSpan(new RelativeSizeSpan(0.8f), result.length() - dpDecimal, result.length(), SPAN_INCLUSIVE_INCLUSIVE);
        return result;
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

    public static BigDecimal getAllDelegatedAmount(ArrayList<BondingState> bondings, ArrayList<Validator> validators,  BaseChain chain) {
        BigDecimal sum = BigDecimal.ZERO;
        if (bondings == null || bondings.size() == 0) return sum;
        for(BondingState bonding : bondings) {
            sum = sum.add(bonding.getBondingAmount(selectValidator(validators, bonding.validatorAddress)));
        }
        return sum;
    }

    public static BigDecimal getAllUnbondingAmount(ArrayList<UnBondingState> unbondings) {
        BigDecimal sum = BigDecimal.ZERO;
        if (unbondings == null || unbondings.size() == 0) return sum;
        for(UnBondingState unbonding : unbondings) {
            sum = sum.add(unbonding.balance);
        }
        return sum;
    }

    public static BigDecimal getAllKava(BaseData baseData, ArrayList<Balance> balances, ArrayList<BondingState> bondings, ArrayList<UnBondingState> unbondings, ArrayList<Reward> rewards, ArrayList<Validator> validators) {
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

    public static BigDecimal getAllOk(Balance balance, ResOkStaking deposit, ResOkUnbonding withdraw) {
        BigDecimal sum = BigDecimal.ZERO;
        if (balance != null) {
            sum = sum.add(balance.balance);
            sum = sum.add(balance.locked);
        }
        sum = sum.add(getOkDepositCoin(deposit));
        sum = sum.add(getOkWithdrawingCoin(withdraw));

        return sum;
    }

    public static BigDecimal getAllCtk(ArrayList<Balance> balances, ArrayList<BondingState> bondings, ArrayList<UnBondingState> unbondings, ArrayList<Reward> rewards, ArrayList<Validator> validators) {
        BigDecimal sum = BigDecimal.ZERO;
        for(Balance balance : balances) {
            if(balance.symbol.equals(TOKEN_CERTIK)) {
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
                sum = sum.add(reward.getRewardAmount(TOKEN_CERTIK));
            }
        }
        return sum;
    }

    public static BigDecimal getAllSecret(ArrayList<Balance> balances, ArrayList<BondingState> bondings, ArrayList<UnBondingState> unbondings, ArrayList<Reward> rewards, ArrayList<Validator> validators) {
        BigDecimal sum = BigDecimal.ZERO;
        for(Balance balance : balances) {
            if(balance.symbol.equals(TOKEN_SECRET)) {
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
                sum = sum.add(reward.getRewardAmount(TOKEN_SECRET));
            }
        }
        return sum;
    }

    public static BigDecimal getAllSentinel(ArrayList<Balance> balances, ArrayList<BondingState> bondings, ArrayList<UnBondingState> unbondings, ArrayList<Reward> rewards, ArrayList<Validator> validators) {
        BigDecimal sum = BigDecimal.ZERO;
        for(Balance balance : balances) {
            if(balance.symbol.equals(TOKEN_DVPN)) {
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
                sum = sum.add(reward.getRewardAmount(TOKEN_DVPN));
            }
        }
        return sum;
    }

    public static SpannableString getPriceDp(Context c, BigDecimal input, String symbol, int currency) {
        if (currency == 5) {
            SpannableString result;
            result = new SpannableString(symbol + " " + getDecimalFormat(c, 8).format(input));
            result.setSpan(new RelativeSizeSpan(0.8f), result.length() - 8, result.length(), SPAN_INCLUSIVE_INCLUSIVE);
            return result;

        } else {
            SpannableString result;
            result = new SpannableString(symbol + " " + getDecimalFormat(c, 2).format(input));
            result.setSpan(new RelativeSizeSpan(0.8f), result.length() - 2, result.length(), SPAN_INCLUSIVE_INCLUSIVE);
            return result;
        }
    }

    public static SpannableString getPriceApproximatelyDp(Context c, BigDecimal input, String symbol, int currency) {
        if (currency == 5) {
            SpannableString result;
            result = new SpannableString(c.getString(R.string.str_approximately)+ " " + symbol + " " + getDecimalFormat(c, 8).format(input));
            result.setSpan(new RelativeSizeSpan(0.8f), result.length() - 8, result.length(), SPAN_INCLUSIVE_INCLUSIVE);
            return result;

        } else {
            SpannableString result;
            result = new SpannableString(c.getString(R.string.str_approximately)+ " " + symbol + " " + getDecimalFormat(c, 2).format(input));
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
            totalPrice = totalAmount.multiply(new BigDecimal(""+dao.getLastIovTic())).movePointLeft(6).setScale(8, RoundingMode.DOWN);
            SpannableString result;
            result = new SpannableString(dao.getCurrencySymbol() + " " +getDecimalFormat(c, 8).format(totalPrice));
            result.setSpan(new RelativeSizeSpan(0.8f), result.length() - 8, result.length(), SPAN_INCLUSIVE_INCLUSIVE);
            return result;

        } else {
            totalPrice = totalAmount.multiply(new BigDecimal(""+dao.getLastIovTic())).movePointLeft(6).setScale(2, RoundingMode.DOWN);
            SpannableString result;
            result = new SpannableString(dao.getCurrencySymbol() + " " +getDecimalFormat(c, 2).format(totalPrice));
            result.setSpan(new RelativeSizeSpan(0.8f), result.length() - 2, result.length(), SPAN_INCLUSIVE_INCLUSIVE);
            return result;
        }
    }

    public static SpannableString getValueOfOk(Context c, BaseData dao, BigDecimal totalAmount) {
        BigDecimal totalPrice = BigDecimal.ZERO;
        if(dao.getCurrency() == 5) {
            totalPrice = totalAmount.multiply(new BigDecimal(""+dao.getLastOKexTic())).setScale(8, RoundingMode.DOWN);
            SpannableString result;
            result = new SpannableString(dao.getCurrencySymbol() + " " +getDecimalFormat(c, 8).format(totalPrice));
            result.setSpan(new RelativeSizeSpan(0.8f), result.length() - 8, result.length(), SPAN_INCLUSIVE_INCLUSIVE);
            return result;

        } else {
            totalPrice = totalAmount.multiply(new BigDecimal(""+dao.getLastOKexTic())).setScale(2, RoundingMode.DOWN);
            SpannableString result;
            result = new SpannableString(dao.getCurrencySymbol() + " " +getDecimalFormat(c, 2).format(totalPrice));
            result.setSpan(new RelativeSizeSpan(0.8f), result.length() - 2, result.length(), SPAN_INCLUSIVE_INCLUSIVE);
            return result;
        }
    }

    public static SpannableString getValueOfCertik(Context c, BaseData dao, BigDecimal totalAmount) {
        BigDecimal totalPrice = BigDecimal.ZERO;
        if(dao.getCurrency() == 5) {
            totalPrice = totalAmount.multiply(new BigDecimal(""+dao.getLastCertikTic())).movePointLeft(6).setScale(8, RoundingMode.DOWN);
            SpannableString result;
            result = new SpannableString(dao.getCurrencySymbol() + " " +getDecimalFormat(c, 8).format(totalPrice));
            result.setSpan(new RelativeSizeSpan(0.8f), result.length() - 8, result.length(), SPAN_INCLUSIVE_INCLUSIVE);
            return result;

        } else {
            totalPrice = totalAmount.multiply(new BigDecimal(""+dao.getLastCertikTic())).movePointLeft(6).setScale(2, RoundingMode.DOWN);
            SpannableString result;
            result = new SpannableString(dao.getCurrencySymbol() + " " +getDecimalFormat(c, 2).format(totalPrice));
            result.setSpan(new RelativeSizeSpan(0.8f), result.length() - 2, result.length(), SPAN_INCLUSIVE_INCLUSIVE);
            return result;
        }
    }

    public static SpannableString getValueOfSecret(Context c, BaseData dao, BigDecimal totalAmount) {
        BigDecimal totalPrice = BigDecimal.ZERO;
        if(dao.getCurrency() == 5) {
            totalPrice = totalAmount.multiply(new BigDecimal(""+dao.getLastSecretTic())).movePointLeft(6).setScale(8, RoundingMode.DOWN);
            SpannableString result;
            result = new SpannableString(dao.getCurrencySymbol() + " " +getDecimalFormat(c, 8).format(totalPrice));
            result.setSpan(new RelativeSizeSpan(0.8f), result.length() - 8, result.length(), SPAN_INCLUSIVE_INCLUSIVE);
            return result;

        } else {
            totalPrice = totalAmount.multiply(new BigDecimal(""+dao.getLastSecretTic())).movePointLeft(6).setScale(2, RoundingMode.DOWN);
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

    public static SpannableString getDpCommissionGrpcRate(Staking.Validator validator) {
        BigDecimal result = getCommissionGrpcRate(validator);
        result = result.movePointRight(2).setScale(2, RoundingMode.DOWN);
        return getPercentDp(result);
    }

    public static BigDecimal getCommissionGrpcRate(Staking.Validator validator) {
        BigDecimal result = BigDecimal.ZERO;
        if (validator != null && validator.getCommission() != null && validator.getCommission().getCommissionRates() != null &&
                validator.getCommission().getCommissionRates().getRate() != null) {
            result = new BigDecimal(validator.getCommission().getCommissionRates().getRate()).movePointLeft(18);
        }
        return result;
    }


    public static BigDecimal uAtomToAtom(BigDecimal uatom) {
        return uatom.divide(new BigDecimal("1000000"), 6, RoundingMode.DOWN);
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
                msg.type.equals(BaseConstant.IRIS_MSG_TYPE_TRANSFER) ||
                msg.type.equals(BaseConstant.CERTIK_MSG_TYPE_TRANSFER)) {
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

        } else if (msg.type.equals(BaseConstant.KAVA_MSG_TYPE_LIQUIDATE_CDP)) {
            result = BaseConstant.TX_TYPE_KAVA_LIQUIDATE_CDP;

        } else if (msg.type.equals(BaseConstant.KAVA_MSG_TYPE_BEP3_CREATE_SWAP)) {
            result = BaseConstant.TX_TYPE_KAVA_BEP3_CREATE;

        } else if (msg.type.equals(BaseConstant.KAVA_MSG_TYPE_BEP3_CLAM_SWAP)) {
            result = BaseConstant.TX_TYPE_KAVA_BEP3_CLAIM;

        } else if (msg.type.equals(BaseConstant.KAVA_MSG_TYPE_BEP3_REFUND_SWAP)) {
            result = BaseConstant.TX_TYPE_KAVA_BEP3_REFUND;

        }else if (msg.type.equals(BaseConstant.KAVA_MSG_TYPE_DEPOSIT_HAVEST) || msg.type.equals(BaseConstant.KAVA_MSG_TYPE_DEPOSIT_HARD)) {
            result = BaseConstant.TX_TYPE_KAVA_DEPOSIT_HARD;

        } else if (msg.type.equals(BaseConstant.KAVA_MSG_TYPE_WITHDRAW_HAVEST) || msg.type.equals(BaseConstant.KAVA_MSG_TYPE_WITHDRAW_HARD)) {
            result = BaseConstant.TX_TYPE_KAVA_WITHDRAW_HARD;

        } else if (msg.type.equals(BaseConstant.KAVA_MSG_TYPE_BORROW_HARD)) {
            result = BaseConstant.TX_TYPE_KAVA_BORROW_HARD;

        } else if (msg.type.equals(BaseConstant.KAVA_MSG_TYPE_REPAY_HARD)) {
            result = BaseConstant.TX_TYPE_KAVA_REPAY_HARD;

        } else if (msg.type.equals(BaseConstant.KAVA_MSG_TYPE_LIQUIDATE_HARD)) {
            result = BaseConstant.TX_TYPE_KAVA_LIQUIDATE_HARD;

        } else if (msg.type.equals(BaseConstant.KAVA_MSG_TYPE_INCENTIVE_REWARD) || msg.type.equals(BaseConstant.KAVA_MSG_TYPE_USDX_MINT_INCENTIVE)) {
            result = BaseConstant.TX_TYPE_KAVA_INCENTIVE_REWARD;

        } else if (msg.type.equals(BaseConstant.KAVA_MSG_TYPE_CLAIM_HAVEST) || msg.type.equals(BaseConstant.KAVA_MSG_TYPE_CLAIM_HARD_INCENTIVE)) {
            result = BaseConstant.TX_TYPE_KAVA_CLAIM_HARVEST;

        }


        else if (msg.type.equals(BaseConstant.IOV_MSG_TYPE_REGISTER_DOMAIN)) {
            result = BaseConstant.TX_TYPE_STARNAME_REGISTER_DOMAIN;

        } else if (msg.type.equals(BaseConstant.IOV_MSG_TYPE_REGISTER_ACCOUNT)) {
            result = BaseConstant.TX_TYPE_STARNAME_REGISTER_ACCOUNT;

        } else if (msg.type.equals(BaseConstant.IOV_MSG_TYPE_DELETE_ACCOUNT)) {
            result = BaseConstant.TX_TYPE_STARNAME_DELETE_ACCOUNT;

        } else if (msg.type.equals(BaseConstant.IOV_MSG_TYPE_DELETE_DOMAIN)) {
            result = BaseConstant.TX_TYPE_STARNAME_DELETE_DOMAIN;

        } else if (msg.type.equals(BaseConstant.IOV_MSG_TYPE_REPLACE_ACCOUNT_RESOURCE)) {
            result = BaseConstant.TX_TYPE_STARNAME_REPLACE_RESOURCE;

        } else if (msg.type.equals(BaseConstant.IOV_MSG_TYPE_RENEW_DOMAIN)) {
            result = BaseConstant.TX_TYPE_STARNAME_RENEW_DOMAIN;

        } else if (msg.type.equals(BaseConstant.IOV_MSG_TYPE_RENEW_ACCOUNT)) {
            result = BaseConstant.TX_TYPE_STARNAME_RENEW_ACCOUNT;

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

            case BaseConstant.TX_TYPE_KAVA_LIQUIDATE_CDP:
                result = c.getString(R.string.tx_kava_liquidate_cdp);
                break;

            case BaseConstant.TX_TYPE_KAVA_DEPOSIT_HARD:
                result = c.getString(R.string.tx_kava_hard_deposit);
                break;

            case BaseConstant.TX_TYPE_KAVA_WITHDRAW_HARD:
                result = c.getString(R.string.tx_kava_hard_withdraw);
                break;

            case BaseConstant.TX_TYPE_KAVA_BORROW_HARD:
                result = c.getString(R.string.tx_kava_hard_borrow);
                break;

            case BaseConstant.TX_TYPE_KAVA_REPAY_HARD:
                result = c.getString(R.string.tx_kava_hard_repay);
                break;

            case BaseConstant.TX_TYPE_KAVA_LIQUIDATE_HARD:
                result = c.getString(R.string.tx_kava_hard_liquidate);
                break;

            case BaseConstant.TX_TYPE_KAVA_INCENTIVE_REWARD:
                result = c.getString(R.string.tx_kava_incentive_reward);
                break;

            case BaseConstant.TX_TYPE_KAVA_CLAIM_HARVEST:
                result = c.getString(R.string.tx_kava_incentive_hard);
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



            case BaseConstant.TX_TYPE_STARNAME_REGISTER_DOMAIN:
                result = c.getString(R.string.tx_starname_registe_domain);
                break;

            case BaseConstant.TX_TYPE_STARNAME_REGISTER_ACCOUNT:
                result = c.getString(R.string.tx_starname_registe_account);
                break;

            case BaseConstant.TX_TYPE_STARNAME_DELETE_ACCOUNT:
                result = c.getString(R.string.tx_starname_delete_account);
                break;

            case BaseConstant.TX_TYPE_STARNAME_DELETE_DOMAIN:
                result = c.getString(R.string.tx_starname_delete_domain);
                break;

            case BaseConstant.TX_TYPE_STARNAME_REPLACE_RESOURCE:
                result = c.getString(R.string.tx_starname_update_resource);
                break;

            case BaseConstant.TX_TYPE_STARNAME_RENEW_DOMAIN:
                result = c.getString(R.string.tx_starname_renew_domain);
                break;

            case BaseConstant.TX_TYPE_STARNAME_RENEW_ACCOUNT:
                result = c.getString(R.string.tx_starname_renew_account);
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

    public static String DpOkTxType(Context c, ResOkHistory.DataDetail history) {
        String result = c.getString(R.string.tx_known);
        if (history.type == OK_TYPE_TRANSFER) {
            if (history.side == OK_TYPE_SIDE_SEND) {
                result = c.getString(R.string.tx_send);
            } else {
                result = c.getString(R.string.tx_receive);
            }

        } else if (history.type == OK_TYPE_NEW_ORDER) {
            result = c.getString(R.string.tx_new_order);

        } else if (history.type == OK_TYPE_CANCEL_ORDER) {
            result = c.getString(R.string.tx_Cancel_order);

        }
        return result;
    }

    public static String getPath(BaseChain chain, int position, boolean newBip) {
        if (chain.equals(BNB_MAIN) || chain.equals(BNB_TEST)) {
            return BaseConstant.KEY_BNB_PATH + String.valueOf(position);

        } else if (chain.equals(KAVA_MAIN) || chain.equals(KAVA_TEST)) {
            if (newBip) {
                return BaseConstant.KEY_NEW_KAVA_PATH + String.valueOf(position);
            } else {
                return BaseConstant.KEY_PATH + String.valueOf(position);
            }

        } else if (chain.equals(BAND_MAIN)) {
            return BaseConstant.KEY_BAND_PATH + String.valueOf(position);

        } else if (chain.equals(IOV_MAIN) || chain.equals(IOV_TEST)) {
            return BaseConstant.KEY_IOV_PATH + String.valueOf(position);

        } else if (chain.equals(OKEX_MAIN) || chain.equals(OK_TEST)) {
            if (newBip) {
                return  ("(Ethermint Type) ") + BaseConstant.KEY_NEW_OK_PATH + String.valueOf(position);
            } else {
                return ("(Tendermint Type) ") + BaseConstant.KEY_NEW_OK_PATH + String.valueOf(position);
            }

        } else if (chain.equals(SECRET_MAIN)) {
            if (newBip) {
                return BaseConstant.KEY_NEW_SECRET_PATH + String.valueOf(position);
            } else {
                return BaseConstant.KEY_PATH + String.valueOf(position);
            }

        }  else if (chain.equals(PERSIS_MAIN)) {
            return BaseConstant.KEY_PERSIS_PATH + String.valueOf(position);

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

    public static String getUnbondTime(Context c, BaseChain chain) {
        String result = "??";
        try {
            if (chain.equals(IOV_MAIN) || chain.equals(IOV_TEST)) {
                Calendar calendar = Calendar.getInstance();
                calendar.add(Calendar.DATE, 3);
                SimpleDateFormat unbondFormat = new SimpleDateFormat(c.getString(R.string.str_dp_time_format2));
                result = unbondFormat.format(calendar.getTimeInMillis());
                return result + "   " +c.getString(R.string.str_unbonding_3days_after);

            } else if (chain.equals(OKEX_MAIN) || chain.equals(OK_TEST)) {
                Calendar calendar = Calendar.getInstance();
                calendar.add(Calendar.DATE, 14);
                SimpleDateFormat unbondFormat = new SimpleDateFormat(c.getString(R.string.str_dp_time_format2));
                result = unbondFormat.format(calendar.getTimeInMillis());
                return result + "   " +c.getString(R.string.str_unbonding_14days_after);

            } else if (chain.equals(SENTINEL_MAIN)) {
                Calendar calendar = Calendar.getInstance();
                calendar.add(Calendar.DATE, 28);
                SimpleDateFormat unbondFormat = new SimpleDateFormat(c.getString(R.string.str_dp_time_format2));
                result = unbondFormat.format(calendar.getTimeInMillis());
                return result + "   " +c.getString(R.string.str_unbonding_28days_after);

            } else {
                Calendar calendar = Calendar.getInstance();
                calendar.add(Calendar.DATE, 21);
                SimpleDateFormat unbondFormat = new SimpleDateFormat(c.getString(R.string.str_dp_time_format2));
                result = unbondFormat.format(calendar.getTimeInMillis());
                return result + "   " +c.getString(R.string.str_unbonding_21days_after);
            }

        } catch (Exception e) {};

        return result;
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

    public static String getTimeTxformatShort(Context c, String rawValue) {
        String result = "??";
        try {
            SimpleDateFormat blockDateFormat = new SimpleDateFormat(c.getString(R.string.str_tx_time_format));
            SimpleDateFormat myFormat = new SimpleDateFormat(c.getString(R.string.str_dp_time_format4));
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

    public static String getTimeTxGap(Context c, long rawValue) {
        String result = "";
        try {
            Date blockTime  = new Date(rawValue);
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
        if (chain.equals(COSMOS_MAIN) || chain.equals(COSMOS_TEST)) {
            return c.getResources().getColor(R.color.colorAtom);
        } else if (chain.equals(IRIS_MAIN) || chain.equals(IRIS_TEST)) {
            return c.getResources().getColor(R.color.colorIris);
        } else if (chain.equals(BNB_MAIN) || chain.equals(BNB_TEST)) {
            return c.getResources().getColor(R.color.colorBnb);
        } else if (chain.equals(KAVA_MAIN) || chain.equals(KAVA_TEST)) {
            return c.getResources().getColor(R.color.colorKava);
        } else if (chain.equals(IOV_MAIN) || chain.equals(IOV_TEST)) {
            return c.getResources().getColor(R.color.colorIov);
        } else if (chain.equals(BAND_MAIN)) {
            return c.getResources().getColor(R.color.colorBand);
        } else if (chain.equals(OKEX_MAIN) || chain.equals(OK_TEST)) {
            return c.getResources().getColor(R.color.colorOK);
        } else if (chain.equals(CERTIK_MAIN) || chain.equals(CERTIK_TEST)) {
            return c.getResources().getColor(R.color.colorCertik);
        } else if (chain.equals(SECRET_MAIN)) {
            return c.getResources().getColor(R.color.colorSecret);
        } else if (chain.equals(AKASH_MAIN)) {
            return c.getResources().getColor(R.color.colorAkash);
        } else if (chain.equals(PERSIS_MAIN)) {
            return c.getResources().getColor(R.color.colorPersis);
        } else if (chain.equals(SENTINEL_MAIN)) {
            return c.getResources().getColor(R.color.colorSentinel);
        } else {
            return c.getResources().getColor(R.color.colorGray0);
        }
    }

    public static int getChainBgColor(Context c, BaseChain chain) {
        if (chain.equals(COSMOS_MAIN) || chain.equals(COSMOS_TEST)) {
            return c.getResources().getColor(R.color.colorTransBgCosmos);
        } else if (chain.equals(IRIS_MAIN) || chain.equals(IRIS_TEST)) {
            return c.getResources().getColor(R.color.colorTransBgIris);
        } else if (chain.equals(BNB_MAIN) || chain.equals(BNB_TEST)) {
            return c.getResources().getColor(R.color.colorTransBgBinance);
        } else if (chain.equals(KAVA_MAIN) || chain.equals(KAVA_TEST)) {
            return c.getResources().getColor(R.color.colorTransBgKava);
        } else if (chain.equals(IOV_MAIN) || chain.equals(IOV_TEST)) {
            return c.getResources().getColor(R.color.colorTransBgStarname);
        } else if (chain.equals(BAND_MAIN)) {
            return c.getResources().getColor(R.color.colorTransBgBand);
        } else if (chain.equals(OKEX_MAIN) || chain.equals(OK_TEST)) {
            return c.getResources().getColor(R.color.colorTransBgOkex);
        } else if (chain.equals(CERTIK_MAIN) || chain.equals(CERTIK_TEST)) {
            return c.getResources().getColor(R.color.colorTransBgCertik);
        } else if (chain.equals(SECRET_MAIN)) {
            return c.getResources().getColor(R.color.colorTransBgSecret);
        } else if (chain.equals(AKASH_MAIN)) {
            return c.getResources().getColor(R.color.colorTransBgAkash);
        } else if (chain.equals(PERSIS_MAIN)) {
            return c.getResources().getColor(R.color.colorTransBgPersis);
        } else if (chain.equals(SENTINEL_MAIN)) {
            return c.getResources().getColor(R.color.colorTransBgSentinel);
        } else {
            return c.getResources().getColor(R.color.colorTransBg);
        }

    }

    public static ColorStateList getTabColor(Context c, BaseChain chain) {
        if(chain.equals(COSMOS_MAIN) || chain.equals(COSMOS_TEST)) {
            return c.getResources().getColorStateList(R.color.color_tab_myvalidator);
        } else if(chain.equals(IRIS_MAIN) || chain.equals(IRIS_TEST)) {
            return c.getResources().getColorStateList(R.color.color_tab_myvalidator_iris);
        } else if(chain.equals(KAVA_MAIN) || chain.equals(KAVA_TEST)) {
            return c.getResources().getColorStateList(R.color.color_tab_myvalidator_kava);
        } else if(chain.equals(BAND_MAIN)) {
            return c.getResources().getColorStateList(R.color.color_tab_myvalidator_band);
        } else if(chain.equals(IOV_MAIN) || chain.equals(IOV_TEST)) {
            return c.getResources().getColorStateList(R.color.color_tab_myvalidator_iov);
        } else if(chain.equals(OKEX_MAIN) || chain.equals(OK_TEST)) {
            return c.getResources().getColorStateList(R.color.color_tab_myvalidator_ok);
        } else if(chain.equals(CERTIK_MAIN) || chain.equals(CERTIK_TEST)) {
            return c.getResources().getColorStateList(R.color.color_tab_myvalidator_certik);
        } else if (chain.equals(SECRET_MAIN)) {
            return c.getResources().getColorStateList(R.color.color_tab_myvalidator_secret);
        } else if (chain.equals(AKASH_MAIN)) {
            return c.getResources().getColorStateList(R.color.color_tab_myvalidator_akash);
        } else if (chain.equals(PERSIS_MAIN)) {
            return c.getResources().getColorStateList(R.color.color_tab_myvalidator_persis);
        } else if (chain.equals(SENTINEL_MAIN)) {
            return c.getResources().getColorStateList(R.color.color_tab_myvalidator_sentinel);
        }
        return null;
    }

    public static ColorStateList getChainTintColor(Context c, BaseChain chain) {
        if(chain.equals(COSMOS_MAIN) || chain.equals(COSMOS_TEST)) {
            return c.getResources().getColorStateList(R.color.colorAtom);
        } else if(chain.equals(IRIS_MAIN) || chain.equals(IRIS_TEST)) {
            return c.getResources().getColorStateList(R.color.colorIris);
        } else if(chain.equals(KAVA_MAIN) || chain.equals(KAVA_TEST)) {
            return c.getResources().getColorStateList(R.color.colorKava);
        } else if(chain.equals(BAND_MAIN)) {
            return c.getResources().getColorStateList(R.color.colorBand);
        } else if(chain.equals(IOV_MAIN) || chain.equals(IOV_TEST)) {
            return c.getResources().getColorStateList(R.color.colorIov);
        } else if(chain.equals(OKEX_MAIN) || chain.equals(OK_TEST)) {
            return c.getResources().getColorStateList(R.color.colorOK);
        } else if (chain.equals(CERTIK_MAIN) || chain.equals(CERTIK_TEST)) {
            return c.getResources().getColorStateList(R.color.colorCertik);
        } else if (chain.equals(SECRET_MAIN)) {
            return c.getResources().getColorStateList(R.color.colorSecret);
        } else if (chain.equals(AKASH_MAIN)) {
            return c.getResources().getColorStateList(R.color.colorAkash);
        } else if (chain.equals(PERSIS_MAIN)) {
            return c.getResources().getColorStateList(R.color.colorPersis);
        } else if (chain.equals(SENTINEL_MAIN)) {
            return c.getResources().getColorStateList(R.color.colorSentinel);
        }
        return null;
    }

    public static void DpMainDenom(Context c, BaseChain chain, TextView textview) {
        DpMainDenom(c, chain.getChain(), textview);
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

        } else if (BaseChain.getChain(chain).equals(OKEX_MAIN) || BaseChain.getChain(chain).equals(OK_TEST)) {
            textview.setTextColor(c.getResources().getColor(R.color.colorOK));
            textview.setText(c.getString(R.string.s_okt));

        } else if (BaseChain.getChain(chain).equals(CERTIK_MAIN) || BaseChain.getChain(chain).equals(CERTIK_TEST)) {
            textview.setTextColor(c.getResources().getColor(R.color.colorCertik));
            textview.setText(c.getString(R.string.s_ctk));

        } else if (BaseChain.getChain(chain).equals(SECRET_MAIN)) {
            textview.setTextColor(c.getResources().getColor(R.color.colorSecret));
            textview.setText(c.getString(R.string.s_scrt));

        } else if (BaseChain.getChain(chain).equals(AKASH_MAIN)) {
            textview.setTextColor(c.getResources().getColor(R.color.colorAkash));
            textview.setText(c.getString(R.string.s_akt));

        } else if (BaseChain.getChain(chain).equals(PERSIS_MAIN)) {
            textview.setTextColor(c.getResources().getColor(R.color.colorPersis));
            textview.setText(c.getString(R.string.s_xprt));

        } else if (BaseChain.getChain(chain).equals(SENTINEL_MAIN)) {
            textview.setTextColor(c.getResources().getColor(R.color.colorSentinel));
            textview.setText(c.getString(R.string.s_dvpn));

        } else if (BaseChain.getChain(chain).equals(COSMOS_TEST)) {
            textview.setTextColor(c.getResources().getColor(R.color.colorAtom));
            textview.setText(c.getString(R.string.s_muon));

        } else if (BaseChain.getChain(chain).equals(IRIS_TEST)) {
            textview.setTextColor(c.getResources().getColor(R.color.colorIris));
            textview.setText(c.getString(R.string.s_bif));

        }
    }

    public static String mainDenom(BaseChain chain) {
        if (chain.equals(COSMOS_MAIN)) {
            return TOKEN_ATOM;
        } else if(chain.equals(IRIS_MAIN)) {
            return TOKEN_IRIS;
        } else if(chain.equals(KAVA_MAIN) || chain.equals(KAVA_TEST)) {
            return TOKEN_KAVA;
        } else if(chain.equals(BAND_MAIN)) {
            return TOKEN_BAND;
        } else if(chain.equals(IOV_MAIN)) {
            return TOKEN_IOV;
        } else if(chain.equals(OKEX_MAIN) || chain.equals(OK_TEST)) {
            return TOKEN_OK;
        } else if (chain.equals(CERTIK_MAIN) || chain.equals(CERTIK_TEST)) {
            return TOKEN_CERTIK;
        } else if (chain.equals(SECRET_MAIN)) {
            return TOKEN_SECRET;
        } else if (chain.equals(AKASH_MAIN)) {
            return TOKEN_AKASH;
        } else if (chain.equals(PERSIS_MAIN)) {
            return TOKEN_XPRT;
        } else if (chain.equals(SENTINEL_MAIN)) {
            return TOKEN_DVPN;
        } else if (chain.equals(COSMOS_TEST)) {
            return TOKEN_COSMOS_TEST;
        } else if (chain.equals(IRIS_TEST)) {
            return TOKEN_IRIS_TEST;
        } else if (chain.equals(IOV_TEST)) {
            return TOKEN_IOV_TEST;
        }
        return "";
    }

    public static int getDpRiskColor(Context c, BigDecimal riskRate) {
        if (riskRate.longValue() <= 50) {
            return c.getResources().getColor(R.color.colorCdpSafe);
        } else if (riskRate.longValue() < 80) {
            return c.getResources().getColor(R.color.colorCdpStable);
        } else {
            return c.getResources().getColor(R.color.colorCdpDanger);
        }
    }

    public static void DpRiskRate(Context c, BigDecimal riskRate, TextView textView, ImageView imageview) {
        textView.setText(WDp.getDpAmount2(c, riskRate, 0, 2));
        if (riskRate.floatValue() <= 50) {
            textView.setTextColor(c.getResources().getColor(R.color.colorCdpSafe));
            if (imageview != null) {
                imageview.setImageDrawable(c.getResources().getDrawable(R.drawable.img_safe));
            }

        } else if (riskRate.floatValue() < 80) {
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

    public static BigDecimal getCdpHiddenFee(Context c, BigDecimal outstandingDebt, CollateralParam paramCdp, Cdp myCdp) {
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

    // HTLC using
    public static String getDpChainName(Context c, BaseChain chain) {
        if (chain.equals(COSMOS_MAIN)) {
            return c.getString(R.string.str_cosmos_hub_2);

        } else if (chain.equals(IRIS_MAIN)) {
            return c.getString(R.string.str_iris_net_2);

        } else if (chain.equals(BNB_MAIN)) {
            return c.getString(R.string.str_binance_net_2);

        } else if (chain.equals(KAVA_MAIN)) {
            return c.getString(R.string.str_kava_net_2);

        } else if (chain.equals(IOV_MAIN)) {
            return c.getString(R.string.str_iov_net_2);

        } else if (chain.equals(BAND_MAIN)) {
            return c.getString(R.string.str_band_chain_2);

        } else if (chain.equals(CERTIK_MAIN)) {
            return c.getString(R.string.str_certik_chain_2);

        } else if (chain.equals(BNB_TEST)) {
            return c.getString(R.string.str_binance_test_net_2);

        } else if (chain.equals(KAVA_TEST)) {
            return c.getString(R.string.str_kava_net_test_2);

        } else if (chain.equals(IOV_TEST)) {
            return c.getString(R.string.str_iov_net_test_2);

        } else if (chain.equals(OKEX_MAIN)) {
            return c.getString(R.string.str_ok_net2);

        } else if (chain.equals(OK_TEST)) {
            return c.getString(R.string.str_ok_net_test_2);

        } else if (chain.equals(CERTIK_TEST)) {
            return c.getString(R.string.str_certik_chain_test_2);

        }
        return "";

    }

    // HTLC using
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

        } else if (chain.equals(OKEX_MAIN)) {
            if (imgView != null) imgView.setImageDrawable(c.getResources().getDrawable(R.drawable.okex_chain_img));
            txtView.setText(c.getString(R.string.str_ok_net2));

        } else if (chain.equals(OK_TEST)) {
            if (imgView != null) imgView.setImageDrawable(c.getResources().getDrawable(R.drawable.okex_testnet_img));
            txtView.setText(c.getString(R.string.str_ok_net_test_2));

        } else if (chain.equals(CERTIK_MAIN)) {
            if (imgView != null) imgView.setImageDrawable(c.getResources().getDrawable(R.drawable.certik_chain_img));
            txtView.setText(c.getString(R.string.str_certik_chain_2));

        } else if (chain.equals(CERTIK_TEST)) {
            if (imgView != null) imgView.setImageDrawable(c.getResources().getDrawable(R.drawable.certik_testnet_img));
            txtView.setText(c.getString(R.string.str_certik_chain_test_2));

        }
    }

    public static void onDpSwapChain(Context c, BaseChain chain, ImageView imgView, TextView txtView) {
        if (chain.equals(BNB_MAIN)) {
            if (imgView != null) imgView.setImageDrawable(c.getResources().getDrawable(R.drawable.binance_ch_img));
            txtView.setText(c.getString(R.string.str_binance));

        } else if (chain.equals(BNB_TEST)) {
            if (imgView != null) imgView.setImageDrawable(c.getResources().getDrawable(R.drawable.binancetestnet));
            txtView.setText(c.getString(R.string.str_binance));

        } else if (chain.equals(KAVA_MAIN)) {
            if (imgView != null) imgView.setImageDrawable(c.getResources().getDrawable(R.drawable.kava_img));
            txtView.setText(c.getString(R.string.str_kava));

        } else if (chain.equals(KAVA_TEST)) {
            if (imgView != null) imgView.setImageDrawable(c.getResources().getDrawable(R.drawable.kava_test_img));
            txtView.setText(c.getString(R.string.str_kava));
        }
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

    public static String getBnbHtlcStatus(Context c, ResBnbSwapInfo resBnbSwapInfo, ResNodeInfo resNodeInfo) {
        if (resBnbSwapInfo == null || resNodeInfo == null) {
            return "-";
        }
        if (resBnbSwapInfo.status == BNB_STATUS_REFUNDED) {
            return c.getString(R.string.str_bep3_status_refunded);

        } else if (resBnbSwapInfo.status == BNB_STATUS_COMPLETED) {
            return c.getString(R.string.str_bep3_status_completed);

        } else if (resBnbSwapInfo.status == BNB_STATUS_OPEN && resBnbSwapInfo.expireHeight < resNodeInfo.getCHeight()) {
            return c.getString(R.string.str_bep3_status_expired);

        }
        return c.getString(R.string.str_bep3_status_open);

    }

    public static String getMonikerImgUrl(BaseChain basechain, String opAddress) {
        if (basechain.equals(COSMOS_MAIN) || basechain.equals(COSMOS_TEST)) {
            return COSMOS_VAL_URL + opAddress + ".png";
        } else if (basechain.equals(IRIS_MAIN) || basechain.equals(IRIS_TEST)) {
            return IRIS_VAL_URL + opAddress + ".png";
        } else if (basechain.equals(AKASH_MAIN)) {
            return AKASH_VAL_URL + opAddress + ".png";
        } else if (basechain.equals(PERSIS_MAIN)) {
            return PERSIS_VAL_URL + opAddress + ".png";
        }

        else if (basechain.equals(KAVA_MAIN) || basechain.equals(KAVA_TEST)) {
            return KAVA_VAL_URL + opAddress + ".png";
        } else if (basechain.equals(BAND_MAIN)) {
            return BAND_VAL_URL + opAddress + ".png";
        } else if (basechain.equals(IOV_MAIN) || basechain.equals(IOV_TEST)) {
            return IOV_VAL_URL + opAddress + ".png";
        } else if (basechain.equals(CERTIK_MAIN) || basechain.equals(CERTIK_TEST)) {
            return CERTIK_VAL_URL + opAddress + ".png";
        } else if (basechain.equals(SECRET_MAIN)) {
            return SECRET_VAL_URL + opAddress + ".png";
        } else if (basechain.equals(SENTINEL_MAIN)) {
            return SENTINEL_VAL_URL + opAddress + ".png";
        } else if (basechain.equals(OKEX_MAIN) || basechain.equals(OK_TEST)) {
            return OKEX_VAL_URL + opAddress + ".png";
        }
        return "";
    }

    public static ArrayList<Coin> getCoins(Object amount) {
        ArrayList<Coin> result = new ArrayList<>();
        try {
            Coin temp = new Gson().fromJson(new Gson().toJson(amount), Coin.class);
            result.add(temp);

        } catch (Exception e) {
        }

        try {
            result = new Gson().fromJson(new Gson().toJson(amount), new TypeToken<List<Coin>>() {
            }.getType());
        } catch (Exception e) {
        }
        return result;
    }

    public static BigDecimal systemQuorum(BaseChain basechain) {
        BigDecimal result = new BigDecimal("0.5");
        if (basechain.equals(COSMOS_MAIN) || basechain.equals(PERSIS_MAIN) || basechain.equals(COSMOS_TEST)) {
            result = new BigDecimal("0.4");
        } else if (basechain.equals(IRIS_MAIN) || basechain.equals(IRIS_TEST)) {
            result = new BigDecimal("0.5");
        } else if (basechain.equals(AKASH_MAIN) || basechain.equals(SENTINEL_MAIN)) {
            result = new BigDecimal("0.334");
        }
        return result.movePointRight(2).setScale(2);
    }

    public static String getDpOption(String option) {
        if (option.equals("1")) {
            return "Yes";
        } else if (option.equals("3")) {
            return "No";
        } else if (option.equals("4")) {
            return "No With Veto";
        } else if (option.equals("2")) {
            return "Abstain";
        }
        return option;
    }


    public static String getProposalTitle(Gov.Proposal proposal) {
        try {
            if (proposal.getContent().getTypeUrl().equals("/cosmos.gov.v1beta1.TextProposal")) {
                Gov.TextProposal textProposal = Gov.TextProposal.parseFrom(proposal.getContent().getValue());
                return textProposal.getTitle();

            } else if (proposal.getContent().getTypeUrl().equals("/cosmos.params.v1beta1.ParameterChangeProposal")) {
                Params.ParameterChangeProposal parameterChangeProposal = Params.ParameterChangeProposal.parseFrom(proposal.getContent().getValue());
                return parameterChangeProposal.getTitle();

            } else if (proposal.getContent().getTypeUrl().equals("/ibc.core.client.v1.ClientUpdateProposal")) {
                Client.ClientUpdateProposal clientUpdateProposal = Client.ClientUpdateProposal.parseFrom(proposal.getContent().getValue());
                return clientUpdateProposal.getTitle();

            } else if (proposal.getContent().getTypeUrl().equals("/cosmos.distribution.v1beta1.CommunityPoolSpendProposal")) {
                Distribution.CommunityPoolSpendProposal communityPoolSpendProposal = Distribution.CommunityPoolSpendProposal.parseFrom(proposal.getContent().getValue());
                return communityPoolSpendProposal.getTitle();

            } else if (proposal.getContent().getTypeUrl().equals("/cosmos.upgrade.v1beta1.SoftwareUpgradeProposal")) {
                Upgrade.SoftwareUpgradeProposal softwareUpgradeProposal = Upgrade.SoftwareUpgradeProposal.parseFrom(proposal.getContent().getValue());
                return softwareUpgradeProposal.getTitle();

            } else if (proposal.getContent().getTypeUrl().equals("/cosmos.upgrade.v1beta1.CancelSoftwareUpgradeProposal")) {
                Upgrade.CancelSoftwareUpgradeProposal cancelSoftwareUpgradeProposal = Upgrade.CancelSoftwareUpgradeProposal.parseFrom(proposal.getContent().getValue());
                return cancelSoftwareUpgradeProposal.getTitle();
            }

        } catch (Exception e) { }
        return "";
    }

    public static String getProposalDescription(Gov.Proposal proposal) {
        try {
            if (proposal.getContent().getTypeUrl().equals("/cosmos.gov.v1beta1.TextProposal")) {
                Gov.TextProposal textProposal = Gov.TextProposal.parseFrom(proposal.getContent().getValue());
                return textProposal.getDescription();

            } else if (proposal.getContent().getTypeUrl().equals("/cosmos.params.v1beta1.ParameterChangeProposal")) {
                Params.ParameterChangeProposal parameterChangeProposal = Params.ParameterChangeProposal.parseFrom(proposal.getContent().getValue());
                return parameterChangeProposal.getDescription();

            } else if (proposal.getContent().getTypeUrl().equals("/ibc.core.client.v1.ClientUpdateProposal")) {
                Client.ClientUpdateProposal clientUpdateProposal = Client.ClientUpdateProposal.parseFrom(proposal.getContent().getValue());
                return clientUpdateProposal.getDescription();

            } else if (proposal.getContent().getTypeUrl().equals("/cosmos.distribution.v1beta1.CommunityPoolSpendProposal")) {
                Distribution.CommunityPoolSpendProposal communityPoolSpendProposal = Distribution.CommunityPoolSpendProposal.parseFrom(proposal.getContent().getValue());
                return communityPoolSpendProposal.getDescription();

            } else if (proposal.getContent().getTypeUrl().equals("/cosmos.upgrade.v1beta1.SoftwareUpgradeProposal")) {
                Upgrade.SoftwareUpgradeProposal softwareUpgradeProposal = Upgrade.SoftwareUpgradeProposal.parseFrom(proposal.getContent().getValue());
                return softwareUpgradeProposal.getDescription();

            } else if (proposal.getContent().getTypeUrl().equals("/cosmos.upgrade.v1beta1.CancelSoftwareUpgradeProposal")) {
                Upgrade.CancelSoftwareUpgradeProposal cancelSoftwareUpgradeProposal = Upgrade.CancelSoftwareUpgradeProposal.parseFrom(proposal.getContent().getValue());
                return cancelSoftwareUpgradeProposal.getDescription();
            }

        } catch (Exception e) { }
        return "";
    }

    public static Drawable getProposalStatusImg(Context c, Gov.Proposal proposal) {
        if (proposal.getStatus().equals(Gov.ProposalStatus.PROPOSAL_STATUS_DEPOSIT_PERIOD)) {
            return c.getResources().getDrawable(R.drawable.ic_deposit_img);

        } else if (proposal.getStatus().equals(Gov.ProposalStatus.PROPOSAL_STATUS_VOTING_PERIOD)) {
            return c.getResources().getDrawable(R.drawable.ic_voting_img);

        } else if (proposal.getStatus().equals(Gov.ProposalStatus.PROPOSAL_STATUS_REJECTED)) {
            return c.getResources().getDrawable(R.drawable.ic_rejected_img);

        } else if (proposal.getStatus().equals(Gov.ProposalStatus.PROPOSAL_STATUS_PASSED)) {
            return c.getResources().getDrawable(R.drawable.ic_passed_img);
        }
        return null;
    }

    public static String getProposalStatusTxt(Context c, Gov.Proposal proposal) {
        if (proposal.getStatus().equals(Gov.ProposalStatus.PROPOSAL_STATUS_DEPOSIT_PERIOD)) {
            return "DepositPeriod";

        } else if (proposal.getStatus().equals(Gov.ProposalStatus.PROPOSAL_STATUS_VOTING_PERIOD)) {
            return "VotingPeriod";

        } else if (proposal.getStatus().equals(Gov.ProposalStatus.PROPOSAL_STATUS_REJECTED)) {
            return "Rejected";

        } else if (proposal.getStatus().equals(Gov.ProposalStatus.PROPOSAL_STATUS_PASSED)) {
            return "Passed";
        }
        return "unKnown";
    }

    public static String getProposalType(Gov.Proposal proposal) {
        String[] split = proposal.getContent().getTypeUrl().split("\\.");
        return split[split.length - 1];
    }


    public static String getProposalStartTime(Context c, Gov.Proposal proposal) {
        if (proposal.getStatus().equals(Gov.ProposalStatus.PROPOSAL_STATUS_DEPOSIT_PERIOD)) {
            return c.getString(R.string.str_vote_wait_deposit);
        } else {
            return WDp.getDpTime(c, proposal.getVotingStartTime().getSeconds() * 1000);
        }
    }

    public static String geProposalEndTime(Context c, Gov.Proposal proposal) {
        if (proposal.getStatus().equals(Gov.ProposalStatus.PROPOSAL_STATUS_DEPOSIT_PERIOD)) {
            return c.getString(R.string.str_vote_wait_deposit);
        } else {
            return WDp.getDpTime(c, proposal.getVotingEndTime().getSeconds() * 1000);
        }
    }


    public static BigDecimal geTallySum(Gov.TallyResult tally) {
        return new BigDecimal(tally.getYes()).add(new BigDecimal(tally.getNo())).add(new BigDecimal(tally.getAbstain())).add(new BigDecimal(tally.getNoWithVeto()));
    }

    public static BigDecimal getYesPer(Gov.TallyResult tally) {
        if (geTallySum(tally).equals(BigDecimal.ZERO) || (new BigDecimal(tally.getYes()).longValue() == 0)) {
            return BigDecimal.ZERO.setScale(2);
        }
        return new BigDecimal(tally.getYes()).movePointRight(2).divide(geTallySum(tally), 2, RoundingMode.HALF_UP);
    }

    public static BigDecimal getNoPer(Gov.TallyResult tally) {
        if (geTallySum(tally).equals(BigDecimal.ZERO) || (new BigDecimal(tally.getNo()).longValue() == 0)) {
            return BigDecimal.ZERO.setScale(2);
        }
        return new BigDecimal(tally.getNo()).movePointRight(2).divide(geTallySum(tally), 2, RoundingMode.HALF_UP);
    }

    public static BigDecimal getAbstainPer(Gov.TallyResult tally) {
        if (geTallySum(tally).equals(BigDecimal.ZERO) || (new BigDecimal(tally.getAbstain()).longValue() == 0)) {
            return BigDecimal.ZERO.setScale(2);
        }
        return new BigDecimal(tally.getAbstain()).movePointRight(2).divide(geTallySum(tally), 2, RoundingMode.HALF_UP);
    }

    public static BigDecimal getVetoPer(Gov.TallyResult tally) {
        if (geTallySum(tally).equals(BigDecimal.ZERO) || (new BigDecimal(tally.getNoWithVeto()).longValue() == 0)) {
            return BigDecimal.ZERO.setScale(2);
        }
        return new BigDecimal(tally.getNoWithVeto()).movePointRight(2).divide(geTallySum(tally), 2, RoundingMode.HALF_UP);
    }

    public static BigDecimal getTurnout(BaseData baseData, Gov.TallyResult tally) {
        BigDecimal result = BigDecimal.ZERO;
        if (baseData != null && baseData.mGrpcStakingPool != null) {
            if (geTallySum(tally).equals(BigDecimal.ZERO)) {
                return BigDecimal.ZERO.setScale(2);

            } else {
                BigDecimal bonded = new BigDecimal(baseData.mGrpcStakingPool.getBondedTokens());
                return geTallySum(tally).movePointRight(2).divide(bonded, 2, RoundingMode.HALF_UP);
            }
        }
        return result;
    }

    public static BigDecimal onParseFee(ServiceOuterClass.GetTxResponse response) {
        BigDecimal result = BigDecimal.ZERO;
        if (response.getTx().getAuthInfo().getFee().getAmountCount() > 0) {
            return new BigDecimal(response.getTx().getAuthInfo().getFee().getAmount(0).getAmount());
        }
        return result;
    }

    public static BigDecimal onParseAutoReward(ServiceOuterClass.GetTxResponse response, String Addr, int position) {
        BigDecimal result = BigDecimal.ZERO;
        if (response.getTxResponse().getLogsCount() > 0 && response.getTxResponse().getLogs(position) != null) {
            for (Abci.StringEvent event: response.getTxResponse().getLogs(position).getEventsList()) {
                if (event.getType().equals("transfer")) {
                    for (int i = 0; i < event.getAttributesList().size(); i ++) {
                        if (event.getAttributes(i).getKey().equals("recipient") && event.getAttributes(i).getValue().equals(Addr)) {
                            for (int j = i; j < event.getAttributesList().size(); j ++) {
                                if (event.getAttributes(j).getKey().equals("amount") && event.getAttributes(j).getValue() != null) {
                                    String temp = event.getAttributes(j).getValue().replaceAll("[^0-9]", "");
                                    result = result.add(new BigDecimal(temp));
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
        return result;
    }

    public static BigDecimal onParseStakeReward(ServiceOuterClass.GetTxResponse response, String valAddr, int position) {
        BigDecimal result = BigDecimal.ZERO;
        if (response.getTxResponse().getLogsCount() > 0 && response.getTxResponse().getLogs(position) != null) {
            for (Abci.StringEvent event: response.getTxResponse().getLogs(position).getEventsList()) {
                if (event.getType().equals("withdraw_rewards")) {
                    for (int i = 0; i < event.getAttributesList().size(); i ++) {
                        if (event.getAttributes(i).getKey().equals("validator") && event.getAttributes(i).getValue().equals(valAddr)) {
                            String temp = event.getAttributes(i - 1).getValue().replaceAll("[^0-9]", "");
                            result = result.add(new BigDecimal(temp));
                            break;
                        }
                    }
                }
            }
        }
        return result;
    }

    public static BigDecimal onParseCommission(ServiceOuterClass.GetTxResponse response, int position) {
        BigDecimal result = BigDecimal.ZERO;
        if (response.getTxResponse().getLogsCount() > 0 && response.getTxResponse().getLogs(position) != null) {
            for (Abci.StringEvent event: response.getTxResponse().getLogs(position).getEventsList()) {
                if (event.getType().equals("withdraw_commission")) {
                    for (int i = 0; i < event.getAttributesList().size(); i ++) {
                        if (event.getAttributes(i).getKey().equals("amount")) {
                            if (event.getAttributes(i).getValue() != null) {
                                String temp = event.getAttributes(i).getValue().replaceAll("[^0-9]", "");
                                result = result.add(new BigDecimal(temp));
                                break;
                            }
                        }
                    }
                }
            }
        }
        return result;
    }

    public static long onParsePeriodicUnLockTime(Vesting.PeriodicVestingAccount vestingAccount, int position) {
        long result = vestingAccount.getStartTime();
        for (int i = 0; i <= position; i ++) {
            result = result + vestingAccount.getVestingPeriods(i).getLength();
        }
        return result * 1000;
    }

    public static ArrayList<Vesting.Period> onParsePeriodicRemainVestings(Vesting.PeriodicVestingAccount vestingAccount) {
        ArrayList<Vesting.Period> result = new ArrayList<>();
        long cTime = Calendar.getInstance().getTime().getTime();
        for (int i = 0; i < vestingAccount.getVestingPeriodsCount(); i ++) {
            long unlockTime = onParsePeriodicUnLockTime(vestingAccount, i);
            if (cTime < unlockTime) {
                result.add(Vesting.Period.newBuilder().setLength(unlockTime).addAllAmount(vestingAccount.getVestingPeriods(i).getAmountList()).build());
            }
        }
        return result;
    }

    public static ArrayList<Vesting.Period> onParsePeriodicRemainVestingsByDenom(Vesting.PeriodicVestingAccount vestingAccount, String denom) {
        ArrayList<Vesting.Period> result = new ArrayList<>();
        for (Vesting.Period vp: onParsePeriodicRemainVestings(vestingAccount)) {
            for (CoinOuterClass.Coin coin: vp.getAmountList()) {
                if (coin.getDenom().equals(denom)) {
                    result.add(vp);
                }
            }
        }
        return result;
    }

    public static int onParseAllPeriodicRemainVestingsCnt(Vesting.PeriodicVestingAccount vestingAccount) {
        return onParsePeriodicRemainVestings(vestingAccount).size();
    }

    public static int onParsePeriodicRemainVestingsCntByDenom(Vesting.PeriodicVestingAccount vestingAccount, String denom) {
        return onParsePeriodicRemainVestingsByDenom(vestingAccount, denom).size();
    }

    public static long onParsePeriodicRemainVestingTime(Vesting.PeriodicVestingAccount vestingAccount, String denom, int position) {
        return onParsePeriodicRemainVestingsByDenom(vestingAccount, denom).get(position).getLength();
    }

    public static BigDecimal onParsePeriodicRemainVestingsAmountByDenom(Vesting.PeriodicVestingAccount vestingAccount, String denom) {
        BigDecimal result = BigDecimal.ZERO;
        ArrayList<Vesting.Period> vps = onParsePeriodicRemainVestingsByDenom(vestingAccount, denom);
        for (Vesting.Period vp: vps) {
            for (CoinOuterClass.Coin coin: vp.getAmountList()) {
                if (coin.getDenom().equals(denom)) {
                    result = result.add(new BigDecimal(coin.getAmount()));
                }
            }
        }
        return result;
    }

    public static BigDecimal onParsePeriodicRemainVestingAmount(Vesting.PeriodicVestingAccount vestingAccount, String denom, int position) {
        ArrayList<Vesting.Period> vps = onParsePeriodicRemainVestingsByDenom(vestingAccount, denom);
        if (vps.size() > position && vps.get(position) != null) {
            for (CoinOuterClass.Coin coin: vps.get(position).getAmountList()) {
                return new BigDecimal(coin.getAmount());
            }
        }
        return BigDecimal.ZERO;
    }

    public static BigDecimal getAmountVp(Vesting.Period vp, String denom) {
        BigDecimal result = BigDecimal.ZERO;
        for (CoinOuterClass.Coin coin: vp.getAmountList()) {
            if (coin.getDenom().equals(denom)) {
                return new BigDecimal(coin.getAmount());
            }
        }
        return result;
    }

}
