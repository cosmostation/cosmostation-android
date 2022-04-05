package wannabit.io.cosmostaion.utils;

import static android.text.Spanned.SPAN_INCLUSIVE_INCLUSIVE;
import static wannabit.io.cosmostaion.base.BaseChain.AKASH_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.ALTHEA_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.ALTHEA_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.AXELAR_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.BAND_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.BITCANNA_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.BITSONG_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.BNB_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.CERBERUS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.CERTIK_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.CHIHUAHUA_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.COMDEX_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.COSMOS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.COSMOS_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.CRYPTO_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.CUDOS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.DESMOS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.EMONEY_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.EVMOS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.FETCHAI_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.GRABRIDGE_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.IMVERSED_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.INJ_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.IOV_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.IRIS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.IRIS_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.JUNO_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.KAVA_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.KI_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.KONSTELL_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.LUM_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.MEDI_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.OKEX_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.OMNIFLIX_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.OSMOSIS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.PERSIS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.PROVENANCE_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.REGEN_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.RIZON_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.SECRET_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.SENTINEL_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.SIF_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.STARGAZE_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.UMEE_MAIN;
import static wannabit.io.cosmostaion.base.BaseConstant.*;
import static wannabit.io.cosmostaion.network.res.ResBnbSwapInfo.BNB_STATUS_COMPLETED;
import static wannabit.io.cosmostaion.network.res.ResBnbSwapInfo.BNB_STATUS_OPEN;
import static wannabit.io.cosmostaion.network.res.ResBnbSwapInfo.BNB_STATUS_REFUNDED;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.RelativeSizeSpan;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cosmos.base.abci.v1beta1.Abci;
import cosmos.base.v1beta1.CoinOuterClass;
import cosmos.staking.v1beta1.Staking;
import cosmos.tx.v1beta1.ServiceOuterClass;
import cosmos.vesting.v1beta1.Vesting;
import kava.cdp.v1beta1.Genesis;
import kava.cdp.v1beta1.QueryOuterClass;
import osmosis.gamm.poolmodels.balancer.BalancerPool;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.dao.Assets;
import wannabit.io.cosmostaion.dao.Balance;
import wannabit.io.cosmostaion.dao.ChainParam;
import wannabit.io.cosmostaion.dao.Cw20Assets;
import wannabit.io.cosmostaion.dao.IbcToken;
import wannabit.io.cosmostaion.dao.OkTicker;
import wannabit.io.cosmostaion.dao.OkToken;
import wannabit.io.cosmostaion.dao.Price;
import wannabit.io.cosmostaion.model.type.BnbHistory;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.network.res.ResBnbSwapInfo;
import wannabit.io.cosmostaion.network.res.ResNodeInfo;
import wannabit.io.cosmostaion.network.res.ResProposal;

public class WDp {
    //show display text with full input amount and to divide deciaml and to show point
    public static SpannableString getDpAmount2(BigDecimal input, int divideDecimal, int displayDecimal) {
        SpannableString result;
        BigDecimal amount = input.movePointLeft(divideDecimal).setScale(displayDecimal, BigDecimal.ROUND_DOWN);
        result = new SpannableString(getDecimalFormat(displayDecimal).format(amount));
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

    public static void showCoinDp(Context c, BaseData baseData, Coin coin, TextView denomTv, TextView amountTv, BaseChain chain) {
        if (chain.isGRPC() && coin.isIbc()) {
            IbcToken ibcToken = baseData.getIbcToken(coin.getIbcHash());
            if (ibcToken != null && ibcToken.auth) {
                denomTv.setTextColor(ContextCompat.getColor(c, R.color.colorWhite));
                denomTv.setText(ibcToken.display_denom.toUpperCase());
                amountTv.setText(getDpAmount2(new BigDecimal(coin.amount), ibcToken.decimal, ibcToken.decimal));

            } else {
                denomTv.setTextColor(ContextCompat.getColor(c, R.color.colorWhite));
                denomTv.setText(R.string.str_unknown);
                amountTv.setText(getDpAmount2(new BigDecimal(coin.amount), 6, 6));
            }

        } else if (chain.equals(COSMOS_MAIN)) {
            if (coin.denom.equals(chain.getMainDenom())) {
                DpMainDenom(chain.getChain(), denomTv);
                amountTv.setText(getDpAmount2(new BigDecimal(coin.amount), 6, 6));
            } else {
                denomTv.setTextColor(ContextCompat.getColor(c, R.color.colorWhite));
                amountTv.setText(getDpAmount2(new BigDecimal(coin.amount), 6, 6));
                denomTv.setText(coin.denom.toUpperCase());
            }

        } else if (chain.equals(IMVERSED_MAIN)) {
            if (coin.denom.equals(chain.getMainDenom())) {
                DpMainDenom(chain.getChain(), denomTv);
            } else {
                denomTv.setTextColor(ContextCompat.getColor(c, R.color.colorWhite));
                denomTv.setText(coin.denom.toUpperCase());
            }
            amountTv.setText(getDpAmount2(new BigDecimal(coin.amount), 6, 6));

        } else if (chain.equals(IRIS_MAIN)) {
            if (coin.denom.equals(chain.getMainDenom())) {
                DpMainDenom(chain.getChain(), denomTv);
            } else {
                denomTv.setTextColor(ContextCompat.getColor(c, R.color.colorWhite));
                denomTv.setText(coin.denom.toUpperCase());
            }
            amountTv.setText(getDpAmount2(new BigDecimal(coin.amount), 6, 6));

        } else if (chain.equals(KAVA_MAIN)) {
            if (coin.denom.equals(chain.getMainDenom())) {
                DpMainDenom(chain.getChain(), denomTv);
            } else if (coin.denom.equals(TOKEN_HARD)) {
                denomTv.setText(coin.denom.toUpperCase());
                denomTv.setTextColor(ContextCompat.getColor(c, R.color.colorHard));
            } else if (coin.denom.equals(TOKEN_USDX)) {
                denomTv.setText(coin.denom.toUpperCase());
                denomTv.setTextColor(ContextCompat.getColor(c, R.color.colorUsdx));
            } else if (coin.denom.equals(TOKEN_SWP)) {
                denomTv.setText(coin.denom.toUpperCase());
                denomTv.setTextColor(ContextCompat.getColor(c, R.color.colorSwp));
            } else {
                denomTv.setText(coin.denom.toUpperCase());
                denomTv.setTextColor(ContextCompat.getColor(c, R.color.colorWhite));
            }
            amountTv.setText(getDpAmount2(new BigDecimal(coin.amount), WUtil.getKavaCoinDecimal(coin), WUtil.getKavaCoinDecimal(coin)));

        } else if (chain.equals(IOV_MAIN)) {
            if (coin.denom.equals(chain.getMainDenom())) {
                DpMainDenom(chain.getChain(), denomTv);
            } else {
                denomTv.setText(coin.denom.toUpperCase());

            }
            amountTv.setText(getDpAmount2(new BigDecimal(coin.amount), 6, 6));

        } else if (chain.equals(BNB_MAIN)) {
            if (coin.denom.equals(chain.getMainDenom())) {
                DpMainDenom(chain.getChain(), denomTv);
            } else {
                denomTv.setText(coin.denom.toUpperCase());
            }
            amountTv.setText(getDpAmount2(new BigDecimal(coin.amount), 8, 8));

        } else if (chain.equals(BAND_MAIN)) {
            if (coin.denom.equals(chain.getMainDenom())) {
                DpMainDenom(chain.getChain(), denomTv);
            } else {
                denomTv.setTextColor(ContextCompat.getColor(c, R.color.colorWhite));
                denomTv.setText(coin.denom.toUpperCase());
            }
            amountTv.setText(getDpAmount2(new BigDecimal(coin.amount), 6, 6));


        } else if (chain.equals(OKEX_MAIN)) {
            if (coin.denom.equals(chain.getMainDenom())) {
                DpMainDenom(chain.getChain(), denomTv);
            } else {
                denomTv.setText(coin.denom.toUpperCase());
            }
            amountTv.setText(getDpAmount2(new BigDecimal(coin.amount), 0, 18));

        } else if (chain.equals(CERTIK_MAIN)) {
            if (coin.denom.equals(chain.getMainDenom())) {
                DpMainDenom(chain.getChain(), denomTv);
            } else {
                denomTv.setTextColor(ContextCompat.getColor(c, R.color.colorWhite));
                denomTv.setText(coin.denom.toUpperCase());
            }
            amountTv.setText(getDpAmount2(new BigDecimal(coin.amount), 6, 6));


        } else if (chain.equals(SECRET_MAIN)) {
            if (coin.denom.equals(chain.getMainDenom())) {
                DpMainDenom(chain.getChain(), denomTv);
            } else {
                denomTv.setTextColor(ContextCompat.getColor(c, R.color.colorWhite));
                denomTv.setText(coin.denom.toUpperCase());
            }
            amountTv.setText(getDpAmount2(new BigDecimal(coin.amount), 6, 6));


        } else if (chain.equals(AKASH_MAIN)) {
            if (coin.denom.equals(chain.getMainDenom())) {
                DpMainDenom(chain.getChain(), denomTv);
            } else {
                denomTv.setTextColor(ContextCompat.getColor(c, R.color.colorWhite));
                denomTv.setText(coin.denom.toUpperCase());
            }
            amountTv.setText(getDpAmount2(new BigDecimal(coin.amount), 6, 6));

        } else if (chain.equals(PERSIS_MAIN)) {
            if (coin.denom.equals(chain.getMainDenom())) {
                DpMainDenom(chain.getChain(), denomTv);
            } else {
                denomTv.setTextColor(ContextCompat.getColor(c, R.color.colorWhite));
                denomTv.setText(coin.denom.toUpperCase());
            }
            amountTv.setText(getDpAmount2(new BigDecimal(coin.amount), 6, 6));

        } else if (chain.equals(SENTINEL_MAIN)) {
            if (coin.denom.equals(TOKEN_DVPN)) {
                DpMainDenom(chain.getChain(), denomTv);
            } else {
                denomTv.setTextColor(ContextCompat.getColor(c, R.color.colorWhite));
                denomTv.setText(coin.denom.toUpperCase());
            }
            amountTv.setText(getDpAmount2(new BigDecimal(coin.amount), 6, 6));

        } else if (chain.equals(FETCHAI_MAIN)) {
            if (coin.denom.equals(chain.getMainDenom())) {
                DpMainDenom(chain.getChain(), denomTv);
            } else {
                denomTv.setTextColor(ContextCompat.getColor(c, R.color.colorWhite));
                denomTv.setText(coin.denom.toUpperCase());
            }
            amountTv.setText(getDpAmount2(new BigDecimal(coin.amount), 18, 18));

        } else if (chain.equals(CRYPTO_MAIN)) {
            if (coin.denom.equals(chain.getMainDenom())) {
                DpMainDenom(chain.getChain(), denomTv);
            } else {
                denomTv.setTextColor(ContextCompat.getColor(c, R.color.colorWhite));
                denomTv.setText(coin.denom.toUpperCase());
            }
            amountTv.setText(getDpAmount2(new BigDecimal(coin.amount), 8, 8));

        } else if (chain.equals(SIF_MAIN)) {
            int decimal = WUtil.getSifCoinDecimal(coin.denom);
            if (coin.denom.equals(chain.getMainDenom())) {
                DpMainDenom(chain.getChain(), denomTv);
            } else if (coin.denom.startsWith("c")) {
                denomTv.setText(coin.denom.substring(1).toUpperCase());
                denomTv.setTextColor(ContextCompat.getColor(c, R.color.colorWhite));
            } else {
                denomTv.setText(coin.denom.toUpperCase());
                denomTv.setTextColor(ContextCompat.getColor(c, R.color.colorWhite));
            }
            amountTv.setText(getDpAmount2(new BigDecimal(coin.amount), decimal, decimal));

        } else if (chain.equals(KI_MAIN)) {
            if (coin.denom.equals(chain.getMainDenom())) {
                DpMainDenom(chain.getChain(), denomTv);
            } else {
                denomTv.setTextColor(ContextCompat.getColor(c, R.color.colorWhite));
                denomTv.setText(coin.denom.toUpperCase());
            }
            amountTv.setText(getDpAmount2(new BigDecimal(coin.amount), 6, 6));

        } else if (chain.equals(OSMOSIS_MAIN)) {
            if (coin.denom.equals(chain.getMainDenom())) {
                DpMainDenom(chain.getChain(), denomTv);
                amountTv.setText(getDpAmount2(new BigDecimal(coin.amount), 6, 6));

            } else if (coin.denom.equals(TOKEN_ION)) {
                denomTv.setText("ION");
                denomTv.setTextColor(ContextCompat.getColor(c, R.color.colorIon));
                amountTv.setText(getDpAmount2(new BigDecimal(coin.amount), 6, 6));

            } else if (coin.osmosisAmm()) {
                denomTv.setText(coin.osmosisAmmDpDenom());
                denomTv.setTextColor(ContextCompat.getColor(c, R.color.colorWhite));
                amountTv.setText(getDpAmount2(new BigDecimal(coin.amount), 18, 18));

            } else {
                denomTv.setText(coin.denom.toUpperCase());
                denomTv.setTextColor(ContextCompat.getColor(c, R.color.colorWhite));
                amountTv.setText(getDpAmount2(new BigDecimal(coin.amount), 6, 6));
            }

        } else if (chain.equals(MEDI_MAIN)) {
            if (coin.denom.equals(chain.getMainDenom())) {
                DpMainDenom(chain.getChain(), denomTv);
            } else {
                denomTv.setTextColor(ContextCompat.getColor(c, R.color.colorWhite));
                denomTv.setText(coin.denom.toUpperCase());
            }
            amountTv.setText(getDpAmount2(new BigDecimal(coin.amount), 6, 6));

        } else if (chain.equals(EMONEY_MAIN)) {
            if (coin.denom.equals(chain.getMainDenom())) {
                DpMainDenom(chain.getChain(), denomTv);
            } else {
                denomTv.setText(coin.denom.toUpperCase());
                denomTv.setTextColor(ContextCompat.getColor(c, R.color.colorWhite));
            }
            amountTv.setText(getDpAmount2(new BigDecimal(coin.amount), 6, 6));

        } else if (chain.equals(RIZON_MAIN)) {
            if (coin.denom.equals(chain.getMainDenom())) {
                DpMainDenom(chain.getChain(), denomTv);
            } else {
                denomTv.setTextColor(ContextCompat.getColor(c, R.color.colorWhite));
                denomTv.setText(coin.denom.toUpperCase());
            }
            amountTv.setText(getDpAmount2(new BigDecimal(coin.amount), 6, 6));

        } else if (chain.equals(JUNO_MAIN)) {
            if (coin.denom.equals(chain.getMainDenom())) {
                DpMainDenom(chain.getChain(), denomTv);
            } else {
                denomTv.setTextColor(ContextCompat.getColor(c, R.color.colorWhite));
                denomTv.setText(coin.denom.toUpperCase());
            }
            amountTv.setText(getDpAmount2(new BigDecimal(coin.amount), 6, 6));

        } else if (chain.equals(REGEN_MAIN)) {
            if (coin.denom.equals(chain.getMainDenom())) {
                DpMainDenom(chain.getChain(), denomTv);
            } else {
                denomTv.setTextColor(ContextCompat.getColor(c, R.color.colorWhite));
                denomTv.setText(coin.denom.toUpperCase());
            }
            amountTv.setText(getDpAmount2(new BigDecimal(coin.amount), 6, 6));

        } else if (chain.equals(BITCANNA_MAIN)) {
            if (coin.denom.equals(chain.getMainDenom())) {
                DpMainDenom(chain.getChain(), denomTv);
            } else {
                denomTv.setTextColor(ContextCompat.getColor(c, R.color.colorWhite));
                denomTv.setText(coin.denom.toUpperCase());
            }
            amountTv.setText(getDpAmount2(new BigDecimal(coin.amount), 6, 6));

        } else if (chain.equals(ALTHEA_MAIN)) {
            if (coin.denom.equals(chain.getMainDenom())) {
                DpMainDenom(chain.getChain(), denomTv);
            } else {
                denomTv.setTextColor(ContextCompat.getColor(c, R.color.colorWhite));
                denomTv.setText(coin.denom.toUpperCase());
            }
            amountTv.setText(getDpAmount2(new BigDecimal(coin.amount), 6, 6));

        } else if (chain.equals(STARGAZE_MAIN)) {
            if (coin.denom.equals(chain.getMainDenom())) {
                DpMainDenom(chain.getChain(), denomTv);
            } else {
                denomTv.setTextColor(ContextCompat.getColor(c, R.color.colorWhite));
                denomTv.setText(coin.denom.toUpperCase());
            }
            amountTv.setText(getDpAmount2(new BigDecimal(coin.amount), 6, 6));

        } else if (chain.equals(GRABRIDGE_MAIN)) {
            int decimal = WUtil.getGBridgeCoinDecimal(baseData, coin.denom);
            if (coin.denom.equals(chain.getMainDenom())) {
                DpMainDenom(chain.getChain(), denomTv);
            } else if (coin.denom.startsWith("gravity")) {
                final Assets assets = baseData.getAsset(coin.denom);
                if (assets != null) {
                    denomTv.setText(assets.origin_symbol);
                    denomTv.setTextColor(ContextCompat.getColor(c, R.color.colorWhite));
                }
            } else {
                denomTv.setTextColor(ContextCompat.getColor(c, R.color.colorWhite));
                denomTv.setText(coin.denom.toUpperCase());
            }
            amountTv.setText(getDpAmount2(new BigDecimal(coin.amount), decimal, decimal));

        } else if (chain.equals(COMDEX_MAIN)) {
            if (coin.denom.equals(chain.getMainDenom())) {
                DpMainDenom(chain.getChain(), denomTv);
            } else {
                denomTv.setTextColor(ContextCompat.getColor(c, R.color.colorWhite));
                denomTv.setText(coin.denom.toUpperCase());
            }
            amountTv.setText(getDpAmount2(new BigDecimal(coin.amount), 6, 6));

        } else if (chain.equals(INJ_MAIN)) {
            if (coin.denom.equals(chain.getMainDenom())) {
                DpMainDenom(chain.getChain(), denomTv);
                amountTv.setText(getDpAmount2(new BigDecimal(coin.amount), 18, 18));
            } else if (coin.denom.startsWith("peggy")) {
                final Assets assets = baseData.getAsset(coin.denom);
                if (assets != null) {
                    denomTv.setText(assets.origin_symbol);
                    denomTv.setTextColor(ContextCompat.getColor(c, R.color.colorWhite));
                    amountTv.setText(getDpAmount2(new BigDecimal(coin.amount), assets.decimal, assets.decimal));
                }
            } else {
                denomTv.setText(coin.denom.toUpperCase());
                denomTv.setTextColor(ContextCompat.getColor(c, R.color.colorWhite));
                amountTv.setText(getDpAmount2(new BigDecimal(coin.amount), 18, 18));
            }

        } else if (chain.equals(BITSONG_MAIN)) {
            if (coin.denom.equals(chain.getMainDenom())) {
                DpMainDenom(chain.getChain(), denomTv);
            } else {
                denomTv.setTextColor(ContextCompat.getColor(c, R.color.colorWhite));
                denomTv.setText(coin.denom.toUpperCase());
            }
            amountTv.setText(getDpAmount2(new BigDecimal(coin.amount), 6, 6));

        } else if (chain.equals(DESMOS_MAIN)) {
            if (coin.denom.equals(chain.getMainDenom())) {
                DpMainDenom(chain.getChain(), denomTv);
            } else {
                denomTv.setTextColor(ContextCompat.getColor(c, R.color.colorWhite));
                denomTv.setText(coin.denom.toUpperCase());
            }
            amountTv.setText(getDpAmount2(new BigDecimal(coin.amount), 6, 6));

        } else if (chain.equals(LUM_MAIN)) {
            if (coin.denom.equals(chain.getMainDenom())) {
                DpMainDenom(chain.getChain(), denomTv);
            } else {
                denomTv.setTextColor(ContextCompat.getColor(c, R.color.colorWhite));
                denomTv.setText(coin.denom.toUpperCase());
            }
            amountTv.setText(getDpAmount2(new BigDecimal(coin.amount), 6, 6));

        } else if (chain.equals(CHIHUAHUA_MAIN)) {
            if (coin.denom.equals(chain.getMainDenom())) {
                DpMainDenom(chain.getChain(), denomTv);
            } else {
                denomTv.setTextColor(ContextCompat.getColor(c, R.color.colorWhite));
                denomTv.setText(coin.denom.toUpperCase());
            }
            amountTv.setText(getDpAmount2(new BigDecimal(coin.amount), 6, 6));

        } else if (chain.equals(AXELAR_MAIN)) {
            if (coin.denom.equals(chain.getMainDenom())) {
                DpMainDenom(chain.getChain(), denomTv);
            } else {
                denomTv.setTextColor(ContextCompat.getColor(c, R.color.colorWhite));
                denomTv.setText(coin.denom.toUpperCase());
            }
            amountTv.setText(getDpAmount2(new BigDecimal(coin.amount), 6, 6));

        } else if (chain.equals(KONSTELL_MAIN)) {
            if (coin.denom.equals(chain.getMainDenom())) {
                DpMainDenom(chain.getChain(), denomTv);
            } else {
                denomTv.setTextColor(ContextCompat.getColor(c, R.color.colorWhite));
                denomTv.setText(coin.denom.toUpperCase());
            }
            amountTv.setText(getDpAmount2(new BigDecimal(coin.amount), 6, 6));

        } else if (chain.equals(UMEE_MAIN)) {
            if (coin.denom.equals(chain.getMainDenom())) {
                DpMainDenom(chain.getChain(), denomTv);
            } else {
                denomTv.setTextColor(ContextCompat.getColor(c, R.color.colorWhite));
                denomTv.setText(coin.denom.toUpperCase());
            }
            amountTv.setText(getDpAmount2(new BigDecimal(coin.amount), 6, 6));

        } else if (chain.equals(EVMOS_MAIN)) {
            if (coin.denom.equals(chain.getMainDenom())) {
                DpMainDenom(chain.getChain(), denomTv);
            } else {
                denomTv.setTextColor(ContextCompat.getColor(c, R.color.colorWhite));
                denomTv.setText(coin.denom.toUpperCase());
            }
            amountTv.setText(getDpAmount2(new BigDecimal(coin.amount), 18, 18));

        } else if (chain.equals(CUDOS_MAIN)) {
            if (coin.denom.equals(chain.getMainDenom())) {
                DpMainDenom(chain.getChain(), denomTv);
            } else {
                denomTv.setTextColor(ContextCompat.getColor(c, R.color.colorWhite));
                denomTv.setText(coin.denom.toUpperCase());
            }
            amountTv.setText(getDpAmount2(new BigDecimal(coin.amount), 18, 18));

        } else if (chain.equals(PROVENANCE_MAIN)) {
            if (coin.denom.equals(chain.getMainDenom())) {
                DpMainDenom(chain.getChain(), denomTv);
            } else {
                denomTv.setTextColor(ContextCompat.getColor(c, R.color.colorWhite));
                denomTv.setText(coin.denom.toUpperCase());
            }
            amountTv.setText(getDpAmount2(new BigDecimal(coin.amount), 9, 9));

        } else if (chain.equals(CERBERUS_MAIN)) {
            if (coin.denom.equals(chain.getMainDenom())) {
                DpMainDenom(chain.getChain(), denomTv);
            } else {
                denomTv.setTextColor(ContextCompat.getColor(c, R.color.colorWhite));
                denomTv.setText(coin.denom.toUpperCase());
            }
            amountTv.setText(getDpAmount2(new BigDecimal(coin.amount), 6, 6));

        } else if (chain.equals(OMNIFLIX_MAIN)) {
            if (coin.denom.equals(chain.getMainDenom())) {
                DpMainDenom(chain.getChain(), denomTv);
            } else {
                denomTv.setTextColor(ContextCompat.getColor(c, R.color.colorWhite));
                denomTv.setText(coin.denom.toUpperCase());
            }
            amountTv.setText(getDpAmount2(new BigDecimal(coin.amount), 6, 6));

        } else if (chain.equals(COSMOS_TEST)) {
            if (coin.denom.equals(chain.getMainDenom())) {
                DpMainDenom(chain.getChain(), denomTv);
            } else {
                denomTv.setTextColor(ContextCompat.getColor(c, R.color.colorWhite));
                denomTv.setText(coin.denom.toUpperCase());
            }
            amountTv.setText(getDpAmount2(new BigDecimal(coin.amount), 6, 6));

        } else if (chain.equals(IRIS_TEST)) {
            if (coin.denom.equals(chain.getMainDenom())) {
                DpMainDenom(chain.getChain(), denomTv);
            } else {
                denomTv.setTextColor(ContextCompat.getColor(c, R.color.colorWhite));
                denomTv.setText(coin.denom.toUpperCase());
            }
            amountTv.setText(getDpAmount2(new BigDecimal(coin.amount), 6, 6));

        }
    }

    public static void showCoinDp(Context c, BaseData baseData, String symbol, String amount, TextView denomTv, TextView amountTv, BaseChain chain) {
        if (chain.isGRPC() && symbol.startsWith("ibc")) {
            IbcToken ibcToken = baseData.getIbcToken(symbol.replaceAll("ibc/", ""));
            if (ibcToken != null && ibcToken.auth) {
                denomTv.setText(ibcToken.display_denom.toUpperCase());
                amountTv.setText(getDpAmount2(new BigDecimal(amount), ibcToken.decimal, ibcToken.decimal));

            } else {
                denomTv.setText(R.string.str_unknown);
                amountTv.setText(getDpAmount2(new BigDecimal(amount), 6, 6));
            }
            denomTv.setTextColor(ContextCompat.getColor(c, R.color.colorWhite));

        } else if (chain.equals(COSMOS_MAIN)) {
            if (symbol.equals(chain.getMainDenom())) {
                DpMainDenom(chain.getChain(), denomTv);
            } else {
                denomTv.setTextColor(ContextCompat.getColor(c, R.color.colorWhite));
                denomTv.setText(symbol.toUpperCase());
            }
            amountTv.setText(getDpAmount2(new BigDecimal(amount), 6, 6));

        } else if (chain.equals(IMVERSED_MAIN)) {
            if (symbol.equals(chain.getMainDenom())) {
                DpMainDenom(chain.getChain(), denomTv);
            } else {
                denomTv.setTextColor(ContextCompat.getColor(c, R.color.colorWhite));
                denomTv.setText(symbol.toUpperCase());
            }
            amountTv.setText(getDpAmount2(new BigDecimal(amount), 6, 6));

        } else if (chain.equals(IRIS_MAIN)) {
            if (symbol.equals(chain.getMainDenom())) {
                DpMainDenom(chain.getChain(), denomTv);
            } else {
                denomTv.setTextColor(ContextCompat.getColor(c, R.color.colorWhite));
                denomTv.setText(symbol.toUpperCase());
            }
            amountTv.setText(getDpAmount2(new BigDecimal(amount), 6, 6));

        } else if (chain.equals(KAVA_MAIN)) {
            if (symbol.equals(chain.getMainDenom())) {
                DpMainDenom(chain.getChain(), denomTv);
            } else if (symbol.equals(TOKEN_HARD)) {
                denomTv.setText(symbol.toUpperCase());
                denomTv.setTextColor(ContextCompat.getColor(c, R.color.colorHard));
            } else if (symbol.equals(TOKEN_USDX)) {
                denomTv.setText(symbol.toUpperCase());
                denomTv.setTextColor(ContextCompat.getColor(c, R.color.colorUsdx));
            } else if (symbol.equals(TOKEN_SWP)) {
                denomTv.setText(symbol.toUpperCase());
                denomTv.setTextColor(ContextCompat.getColor(c, R.color.colorSwp));
            } else {
                denomTv.setText(symbol.toUpperCase());
                denomTv.setTextColor(ContextCompat.getColor(c, R.color.colorWhite));
            }
            if (amountTv != null)
                amountTv.setText(getDpAmount2(new BigDecimal(amount), WUtil.getKavaCoinDecimal(baseData, symbol), WUtil.getKavaCoinDecimal(baseData, symbol)));

        } else if (chain.equals(IOV_MAIN)) {
            if (symbol.equals(chain.getMainDenom())) {
                DpMainDenom(chain.getChain(), denomTv);

            } else {
                denomTv.setTextColor(ContextCompat.getColor(c, R.color.colorWhite));
                denomTv.setText(symbol.toUpperCase());
            }
            amountTv.setText(getDpAmount2(new BigDecimal(amount), 6, 6));

        } else if (chain.equals(BNB_MAIN)) {
            if (symbol.equals(chain.getMainDenom())) {
                DpMainDenom(chain.getChain(), denomTv);

            } else {
                denomTv.setText(symbol.toUpperCase());
                denomTv.setTextColor(ContextCompat.getColor(c, R.color.colorWhite));
            }
            amountTv.setText(getDpAmount2(new BigDecimal(amount), 0, 8));

        } else if (chain.equals(BAND_MAIN)) {
            DpMainDenom(chain.getChain(), denomTv);
            amountTv.setText(getDpAmount2(new BigDecimal(amount), 6, 6));

        } else if (chain.equals(OKEX_MAIN)) {
            if (symbol.equals(chain.getMainDenom())) {
                DpMainDenom(chain.getChain(), denomTv);
            } else {
                denomTv.setText(symbol.toUpperCase());
                denomTv.setTextColor(ContextCompat.getColor(c, R.color.colorWhite));
            }
            amountTv.setText(getDpAmount2(new BigDecimal(amount), 0, 18));

        } else if (chain.equals(CERTIK_MAIN)) {
            if (symbol.equals(chain.getMainDenom())) {
                DpMainDenom(chain.getChain(), denomTv);
            } else {
                denomTv.setText(symbol.toUpperCase());
                denomTv.setTextColor(ContextCompat.getColor(c, R.color.colorWhite));
            }
            amountTv.setText(getDpAmount2(new BigDecimal(amount), 6, 6));

        } else if (chain.equals(SECRET_MAIN)) {
            if (symbol.equals(chain.getMainDenom())) {
                DpMainDenom(chain.getChain(), denomTv);
            } else {
                denomTv.setText(symbol.toUpperCase());
                denomTv.setTextColor(ContextCompat.getColor(c, R.color.colorWhite));
            }
            amountTv.setText(getDpAmount2(new BigDecimal(amount), 6, 6));

        } else if (chain.equals(AKASH_MAIN)) {
            if (symbol.equals(chain.getMainDenom())) {
                DpMainDenom(chain.getChain(), denomTv);
            } else {
                denomTv.setText(symbol.toUpperCase());
                denomTv.setTextColor(ContextCompat.getColor(c, R.color.colorWhite));
            }
            amountTv.setText(getDpAmount2(new BigDecimal(amount), 6, 6));

        } else if (chain.equals(PERSIS_MAIN)) {
            if (symbol.equals(chain.getMainDenom())) {
                DpMainDenom(chain.getChain(), denomTv);
            } else {
                denomTv.setText(symbol.toUpperCase());
                denomTv.setTextColor(ContextCompat.getColor(c, R.color.colorWhite));
            }
            amountTv.setText(getDpAmount2(new BigDecimal(amount), 6, 6));

        } else if (chain.equals(SENTINEL_MAIN)) {
            if (symbol.equals(chain.getMainDenom())) {
                DpMainDenom(chain.getChain(), denomTv);
            } else {
                denomTv.setText(symbol.toUpperCase());
                denomTv.setTextColor(ContextCompat.getColor(c, R.color.colorWhite));
            }
            amountTv.setText(getDpAmount2(new BigDecimal(amount), 6, 6));

        } else if (chain.equals(FETCHAI_MAIN)) {
            if (symbol.equals(chain.getMainDenom())) {
                DpMainDenom(chain.getChain(), denomTv);
            } else {
                denomTv.setText(symbol.toUpperCase());
                denomTv.setTextColor(ContextCompat.getColor(c, R.color.colorWhite));
            }
            amountTv.setText(getDpAmount2(new BigDecimal(amount), 18, 18));

        } else if (chain.equals(CRYPTO_MAIN)) {
            if (symbol.equals(chain.getMainDenom())) {
                DpMainDenom(chain.getChain(), denomTv);
            } else {
                denomTv.setText(symbol.toUpperCase());
                denomTv.setTextColor(ContextCompat.getColor(c, R.color.colorWhite));
            }
            amountTv.setText(getDpAmount2(new BigDecimal(amount), 8, 8));

        } else if (chain.equals(SIF_MAIN)) {
            int decimal = WUtil.getSifCoinDecimal(symbol);
            if (symbol.equals(chain.getMainDenom())) {
                DpMainDenom(chain.getChain(), denomTv);
            } else if (symbol.startsWith("c")) {
                denomTv.setText(symbol.substring(1).toUpperCase());
                denomTv.setTextColor(ContextCompat.getColor(c, R.color.colorWhite));
            } else {
                denomTv.setText(symbol.toUpperCase());
                denomTv.setTextColor(ContextCompat.getColor(c, R.color.colorWhite));
            }
            amountTv.setText(getDpAmount2(new BigDecimal(amount), decimal, decimal));

        } else if (chain.equals(KI_MAIN)) {
            if (symbol.equals(chain.getMainDenom())) {
                DpMainDenom(chain.getChain(), denomTv);
            } else {
                denomTv.setText(symbol.toUpperCase());
                denomTv.setTextColor(ContextCompat.getColor(c, R.color.colorWhite));
            }
            amountTv.setText(getDpAmount2(new BigDecimal(amount), 6, 6));

        } else if (chain.equals(OSMOSIS_MAIN)) {
            if (symbol.equals(chain.getMainDenom())) {
                DpMainDenom(chain.getChain(), denomTv);
                amountTv.setText(getDpAmount2(new BigDecimal(amount), 6, 6));

            } else if (symbol.equals(TOKEN_ION)) {
                denomTv.setText("ION");
                denomTv.setTextColor(ContextCompat.getColor(c, R.color.colorIon));
                amountTv.setText(getDpAmount2(new BigDecimal(amount), 6, 6));

            } else if (symbol.startsWith("gamm/pool/")) {
                String[] value = symbol.split("/");
                denomTv.setText("GAMM-" + value[value.length - 1]);
                denomTv.setTextColor(ContextCompat.getColor(c, R.color.colorWhite));
                amountTv.setText(getDpAmount2(new BigDecimal(amount), 18, 18));

            } else {
                denomTv.setText(symbol.toUpperCase());
                denomTv.setTextColor(ContextCompat.getColor(c, R.color.colorWhite));
                amountTv.setText(getDpAmount2(new BigDecimal(amount), 6, 6));
            }

        } else if (chain.equals(MEDI_MAIN)) {
            if (symbol.equals(chain.getMainDenom())) {
                DpMainDenom(chain.getChain(), denomTv);
            } else {
                denomTv.setText(symbol.toUpperCase());
                denomTv.setTextColor(ContextCompat.getColor(c, R.color.colorWhite));
            }
            amountTv.setText(getDpAmount2(new BigDecimal(amount), 6, 6));

        } else if (chain.equals(EMONEY_MAIN)) {
            if (symbol.equals(chain.getMainDenom())) {
                DpMainDenom(chain.getChain(), denomTv);
            } else {
                denomTv.setText(symbol.toUpperCase());
                denomTv.setTextColor(ContextCompat.getColor(c, R.color.colorWhite));
            }
            amountTv.setText(getDpAmount2(new BigDecimal(amount), 6, 6));

        } else if (chain.equals(RIZON_MAIN)) {
            if (symbol.equals(chain.getMainDenom())) {
                DpMainDenom(chain.getChain(), denomTv);
            } else {
                denomTv.setText(symbol.toUpperCase());
                denomTv.setTextColor(ContextCompat.getColor(c, R.color.colorWhite));
            }
            amountTv.setText(getDpAmount2(new BigDecimal(amount), 6, 6));

        } else if (chain.equals(JUNO_MAIN)) {
            if (symbol.equals(chain.getMainDenom())) {
                DpMainDenom(chain.getChain(), denomTv);
            } else {
                denomTv.setText(symbol.toUpperCase());
                denomTv.setTextColor(ContextCompat.getColor(c, R.color.colorWhite));
            }
            amountTv.setText(getDpAmount2(new BigDecimal(amount), 6, 6));

        } else if (chain.equals(REGEN_MAIN)) {
            if (symbol.equals(chain.getMainDenom())) {
                DpMainDenom(chain.getChain(), denomTv);
            } else {
                denomTv.setText(symbol.toUpperCase());
                denomTv.setTextColor(ContextCompat.getColor(c, R.color.colorWhite));
            }
            amountTv.setText(getDpAmount2(new BigDecimal(amount), 6, 6));

        } else if (chain.equals(BITCANNA_MAIN)) {
            if (symbol.equals(chain.getMainDenom())) {
                DpMainDenom(chain.getChain(), denomTv);
            } else {
                denomTv.setText(symbol.toUpperCase());
                denomTv.setTextColor(ContextCompat.getColor(c, R.color.colorWhite));
            }
            amountTv.setText(getDpAmount2(new BigDecimal(amount), 6, 6));

        } else if (chain.equals(ALTHEA_MAIN)) {
            if (symbol.equals(chain.getMainDenom())) {
                DpMainDenom(chain.getChain(), denomTv);
            } else {
                denomTv.setText(symbol.toUpperCase());
                denomTv.setTextColor(ContextCompat.getColor(c, R.color.colorWhite));
            }
            amountTv.setText(getDpAmount2(new BigDecimal(amount), 6, 6));

        } else if (chain.equals(STARGAZE_MAIN)) {
            if (symbol.equals(chain.getMainDenom())) {
                DpMainDenom(chain.getChain(), denomTv);
            } else {
                denomTv.setText(symbol.toUpperCase());
                denomTv.setTextColor(ContextCompat.getColor(c, R.color.colorWhite));
            }
            amountTv.setText(getDpAmount2(new BigDecimal(amount), 6, 6));

        } else if (chain.equals(GRABRIDGE_MAIN)) {
            int decimal = WUtil.getGBridgeCoinDecimal(baseData, symbol);
            if (symbol.equals(chain.getMainDenom())) {
                DpMainDenom(chain.getChain(), denomTv);
            } else if (symbol.startsWith("gravity")) {
                final Assets assets = baseData.getAsset(symbol);
                if (assets != null) {
                    denomTv.setText(assets.origin_symbol);
                    denomTv.setTextColor(ContextCompat.getColor(c, R.color.colorWhite));
                }
            } else {
                denomTv.setText(symbol.toUpperCase());
                denomTv.setTextColor(ContextCompat.getColor(c, R.color.colorWhite));
            }
            amountTv.setText(getDpAmount2(new BigDecimal(amount), decimal, decimal));

        } else if (chain.equals(COMDEX_MAIN)) {
            if (symbol.equals(chain.getMainDenom())) {
                DpMainDenom(chain.getChain(), denomTv);
            } else {
                denomTv.setText(symbol.toUpperCase());
                denomTv.setTextColor(ContextCompat.getColor(c, R.color.colorWhite));
            }
            amountTv.setText(getDpAmount2(new BigDecimal(amount), 6, 6));

        } else if (chain.equals(INJ_MAIN)) {
            if (symbol.equals(chain.getMainDenom())) {
                DpMainDenom(chain.getChain(), denomTv);
                amountTv.setText(getDpAmount2(new BigDecimal(amount), 18, 18));
            } else if (symbol.startsWith("peggy")) {
                final Assets assets = baseData.getAsset(symbol);
                if (assets != null) {
                    denomTv.setText(assets.origin_symbol);
                    denomTv.setTextColor(ContextCompat.getColor(c, R.color.colorWhite));
                    amountTv.setText(getDpAmount2(new BigDecimal(amount), assets.decimal, assets.decimal));
                }
            } else {
                denomTv.setText(symbol.toUpperCase());
                denomTv.setTextColor(ContextCompat.getColor(c, R.color.colorWhite));
                amountTv.setText(getDpAmount2(new BigDecimal(amount), 18, 18));
            }

        } else if (chain.equals(BITSONG_MAIN)) {
            if (symbol.equals(chain.getMainDenom())) {
                DpMainDenom(chain.getChain(), denomTv);
            } else {
                denomTv.setText(symbol.toUpperCase());
                denomTv.setTextColor(ContextCompat.getColor(c, R.color.colorWhite));
            }
            amountTv.setText(getDpAmount2(new BigDecimal(amount), 6, 6));

        } else if (chain.equals(DESMOS_MAIN)) {
            if (symbol.equals(chain.getMainDenom())) {
                DpMainDenom(chain.getChain(), denomTv);
            } else {
                denomTv.setText(symbol.toUpperCase());
                denomTv.setTextColor(ContextCompat.getColor(c, R.color.colorWhite));
            }
            amountTv.setText(getDpAmount2(new BigDecimal(amount), 6, 6));

        } else if (chain.equals(LUM_MAIN)) {
            if (symbol.equals(chain.getMainDenom())) {
                DpMainDenom(chain.getChain(), denomTv);
            } else {
                denomTv.setText(symbol.toUpperCase());
                denomTv.setTextColor(ContextCompat.getColor(c, R.color.colorWhite));
            }
            amountTv.setText(getDpAmount2(new BigDecimal(amount), 6, 6));

        } else if (chain.equals(CHIHUAHUA_MAIN)) {
            if (symbol.equals(chain.getMainDenom())) {
                DpMainDenom(chain.getChain(), denomTv);
            } else {
                denomTv.setText(symbol.toUpperCase());
                denomTv.setTextColor(ContextCompat.getColor(c, R.color.colorWhite));
            }
            amountTv.setText(getDpAmount2(new BigDecimal(amount), 6, 6));

        } else if (chain.equals(AXELAR_MAIN)) {
            if (symbol.equals(chain.getMainDenom())) {
                DpMainDenom(chain.getChain(), denomTv);
            } else {
                denomTv.setText(symbol.toUpperCase());
                denomTv.setTextColor(ContextCompat.getColor(c, R.color.colorWhite));
            }
            amountTv.setText(getDpAmount2(new BigDecimal(amount), 6, 6));

        } else if (chain.equals(KONSTELL_MAIN)) {
            if (symbol.equals(chain.getMainDenom())) {
                DpMainDenom(chain.getChain(), denomTv);
            } else {
                denomTv.setText(symbol.toUpperCase());
                denomTv.setTextColor(ContextCompat.getColor(c, R.color.colorWhite));
            }
            amountTv.setText(getDpAmount2(new BigDecimal(amount), 6, 6));

        } else if (chain.equals(UMEE_MAIN)) {
            if (symbol.equals(chain.getMainDenom())) {
                DpMainDenom(chain.getChain(), denomTv);
            } else {
                denomTv.setText(symbol.toUpperCase());
                denomTv.setTextColor(ContextCompat.getColor(c, R.color.colorWhite));
            }
            amountTv.setText(getDpAmount2(new BigDecimal(amount), 6, 6));

        } else if (chain.equals(EVMOS_MAIN)) {
            if (symbol.equals(chain.getMainDenom())) {
                DpMainDenom(chain.getChain(), denomTv);
            } else {
                denomTv.setText(symbol.toUpperCase());
                denomTv.setTextColor(ContextCompat.getColor(c, R.color.colorWhite));
            }
            amountTv.setText(getDpAmount2(new BigDecimal(amount), 18, 18));

        } else if (chain.equals(CUDOS_MAIN)) {
            if (symbol.equals(chain.getMainDenom())) {
                DpMainDenom(chain.getChain(), denomTv);
            } else {
                denomTv.setText(symbol.toUpperCase());
                denomTv.setTextColor(ContextCompat.getColor(c, R.color.colorWhite));
            }
            amountTv.setText(getDpAmount2(new BigDecimal(amount), 18, 18));

        } else if (chain.equals(PROVENANCE_MAIN)) {
            if (symbol.equals(chain.getMainDenom())) {
                DpMainDenom(chain.getChain(), denomTv);
            } else {
                denomTv.setText(symbol.toUpperCase());
                denomTv.setTextColor(ContextCompat.getColor(c, R.color.colorWhite));
            }
            amountTv.setText(getDpAmount2(new BigDecimal(amount), 9, 9));

        } else if (chain.equals(CERBERUS_MAIN)) {
            if (symbol.equals(chain.getMainDenom())) {
                DpMainDenom(chain.getChain(), denomTv);
            } else {
                denomTv.setText(symbol.toUpperCase());
                denomTv.setTextColor(ContextCompat.getColor(c, R.color.colorWhite));
            }
            amountTv.setText(getDpAmount2(new BigDecimal(amount), 6, 6));

        } else if (chain.equals(OMNIFLIX_MAIN)) {
            if (symbol.equals(chain.getMainDenom())) {
                DpMainDenom(chain.getChain(), denomTv);
            } else {
                denomTv.setText(symbol.toUpperCase());
                denomTv.setTextColor(ContextCompat.getColor(c, R.color.colorWhite));
            }
            amountTv.setText(getDpAmount2(new BigDecimal(amount), 6, 6));
        } else if (chain.equals(COSMOS_TEST)) {
            if (symbol.equals(chain.getMainDenom())) {
                DpMainDenom(chain.getChain(), denomTv);
            } else {
                denomTv.setText(symbol.toUpperCase());
                denomTv.setTextColor(ContextCompat.getColor(c, R.color.colorWhite));
            }
            amountTv.setText(getDpAmount2(new BigDecimal(amount), 6, 6));
        } else if (chain.equals(IRIS_TEST)) {
            if (symbol.equals(chain.getMainDenom())) {
                DpMainDenom(chain.getChain(), denomTv);
            } else {
                denomTv.setText(symbol.toUpperCase());
                denomTv.setTextColor(ContextCompat.getColor(c, R.color.colorWhite));
            }
            amountTv.setText(getDpAmount2(new BigDecimal(amount), 6, 6));

        }
    }

    public static void showChainDp(Context c, BaseChain baseChain, CardView cardName, CardView cardAlarm, CardView cardBody, CardView cardRewardAddress) {
        if (baseChain.equals(OKEX_MAIN) || baseChain.equals(KAVA_MAIN) || baseChain.equals(BNB_MAIN) || baseChain.equals(FETCHAI_MAIN)) {
            cardRewardAddress.setVisibility(View.GONE);
        } else {
            cardRewardAddress.setVisibility(View.VISIBLE);
        }
        cardName.setCardBackgroundColor(WDp.getChainBgColor(c, baseChain));
        cardAlarm.setCardBackgroundColor(WDp.getChainBgColor(c, baseChain));
        cardBody.setCardBackgroundColor(WDp.getChainBgColor(c, baseChain));
        cardRewardAddress.setCardBackgroundColor(WDp.getChainBgColor(c, baseChain));

        if (baseChain.equals(COSMOS_MAIN)) {
            cardAlarm.setVisibility(View.VISIBLE);
        } else {
            cardAlarm.setVisibility(View.GONE);
        }

        if (baseChain.equals(COSMOS_TEST)) {
            cardName.setCardBackgroundColor(c.getColor(R.color.colorTransBg));
            cardAlarm.setCardBackgroundColor(c.getColor(R.color.colorTransBg));
            cardAlarm.setVisibility(View.GONE);
            cardBody.setCardBackgroundColor(c.getColor(R.color.colorTransBg));
            cardRewardAddress.setCardBackgroundColor(c.getColor(R.color.colorTransBg));
            cardRewardAddress.setVisibility(View.VISIBLE);

        } else if (baseChain.equals(IRIS_TEST)) {
            cardName.setCardBackgroundColor(c.getColor(R.color.colorTransBg));
            cardAlarm.setCardBackgroundColor(c.getColor(R.color.colorTransBg));
            cardAlarm.setVisibility(View.GONE);
            cardBody.setCardBackgroundColor(c.getColor(R.color.colorTransBg));
            cardRewardAddress.setCardBackgroundColor(c.getColor(R.color.colorTransBg));
            cardRewardAddress.setVisibility(View.VISIBLE);

        }
    }

    public static void getChainHint(BaseChain chain, TextView textView) {
        final Context context = textView.getContext();
        int titleRes = R.string.str_unknown;
        if (chain != null) {
            titleRes = chain.getChainTitle();
        }
        textView.setText(String.format("(%s)", context.getString(titleRes)));
    }

    public static void getLayoutColor(Context c, BaseChain baseChain, LinearLayout[] wordsLayer) {
        for (LinearLayout linearLayout : wordsLayer) {
            linearLayout.setBackground(ContextCompat.getDrawable(c, baseChain.getMnemonicBackground()));
        }
    }

    public static BaseChain getChainTypeByChainId(String chainId) {
        if (chainId != null) {
            if (chainId.contains("cosmoshub-")) {
                return COSMOS_MAIN;
            } else if (chainId.contains("irishub-")) {
                return IRIS_MAIN;
            } else if (chainId.contains("iov-")) {
                return IOV_MAIN;
            } else if (chainId.contains("akashnet-")) {
                return AKASH_MAIN;
            } else if (chainId.contains("sentinelhub-")) {
                return SENTINEL_MAIN;
            } else if (chainId.contains("core-")) {
                return PERSIS_MAIN;
            } else if (chainId.contains("sifchain-")) {
                return SIF_MAIN;
            } else if (chainId.contains("osmosis-")) {
                return OSMOSIS_MAIN;
            } else if (chainId.contains("crypto-org-")) {
                return CRYPTO_MAIN;
            } else if (chainId.contains("laozi-mainnet")) {
                return BAND_MAIN;
            } else if (chainId.contains("shentu-")) {
                return CERTIK_MAIN;
            } else if (chainId.contains("panacea-")) {
                return MEDI_MAIN;
            } else if (chainId.contains("emoney-")) {
                return EMONEY_MAIN;
            } else if (chainId.contains("juno-")) {
                return JUNO_MAIN;
            } else if (chainId.contains("regen-")) {
                return REGEN_MAIN;
            } else if (chainId.contains("bitcanna-")) {
                return BITCANNA_MAIN;
            } else if (chainId.contains("stargaze-")) {
                return STARGAZE_MAIN;
            } else if (chainId.contains("fetchhub--")) {
                return FETCHAI_MAIN;
            } else if (chainId.contains("kichain-")) {
                return KI_MAIN;
            } else if (chainId.contains("secret-")) {
                return SECRET_MAIN;
            } else if (chainId.contains("titan-")) {
                return RIZON_MAIN;
            } else if (chainId.contains("comdex-")) {
                return COMDEX_MAIN;
            } else if (chainId.contains("bitsong-")) {
                return BITSONG_MAIN;
            } else if (chainId.contains("injective-")) {
                return INJ_MAIN;
            } else if (chainId.contains("desmos-")) {
                return DESMOS_MAIN;
            } else if (chainId.contains("gravity-bridge-")) {
                return GRABRIDGE_MAIN;
            } else if (chainId.contains("lum-network-")) {
                return LUM_MAIN;
            } else if (chainId.contains("chihuahua-")) {
                return CHIHUAHUA_MAIN;
            } else if (chainId.contains("kava-")) {
                return KAVA_MAIN;
            } else if (chainId.contains("axelar-")) {
                return AXELAR_MAIN;
            } else if (chainId.contains("darchub")) {
                return KONSTELL_MAIN;
            } else if (chainId.contains("umee-")) {
                return UMEE_MAIN;
            } else if (chainId.contains("evmos")) {
                return EVMOS_MAIN;
            } else if (chainId.contains("cudos-")) {
                return CUDOS_MAIN;
            } else if (chainId.contains("pio-mainnet-")) {
                return PROVENANCE_MAIN;
            } else if (chainId.contains("cerberus-")) {
                return CERBERUS_MAIN;
            } else if (chainId.contains("omniflixhub-")) {
                return OMNIFLIX_MAIN;
            }
        }
        return null;
    }

    public static String getChainNameByBaseChain(BaseChain baseChain) {
        if (baseChain != null) {
            if (baseChain.equals(COSMOS_MAIN)) {
                return "cosmos";
            } else if (baseChain.equals(IMVERSED_MAIN)) {
                return "imversed";
            } else if (baseChain.equals(IRIS_MAIN)) {
                return "iris";
            } else if (baseChain.equals(BNB_MAIN)) {
                return "bnb";
            } else if (baseChain.equals(OKEX_MAIN)) {
                return "okex";
            } else if (baseChain.equals(KAVA_MAIN)) {
                return "kava";
            } else if (baseChain.equals(BAND_MAIN)) {
                return "band";
            } else if (baseChain.equals(PERSIS_MAIN)) {
                return "persistence";
            } else if (baseChain.equals(IOV_MAIN)) {
                return "starname";
            } else if (baseChain.equals(CERTIK_MAIN)) {
                return "certik";
            } else if (baseChain.equals(AKASH_MAIN)) {
                return "akash";
            } else if (baseChain.equals(SENTINEL_MAIN)) {
                return "sentinel";
            } else if (baseChain.equals(FETCHAI_MAIN)) {
                return "fetchai";
            } else if (baseChain.equals(CRYPTO_MAIN)) {
                return "cryptoorg";
            } else if (baseChain.equals(SIF_MAIN)) {
                return "sifchain";
            } else if (baseChain.equals(RIZON_MAIN)) {
                return "rizon";
            } else if (baseChain.equals(KI_MAIN)) {
                return "kichain";
            } else if (baseChain.equals(OSMOSIS_MAIN)) {
                return "osmosis";
            } else if (baseChain.equals(MEDI_MAIN)) {
                return "medibloc";
            } else if (baseChain.equals(EMONEY_MAIN)) {
                return "emoney";
            } else if (baseChain.equals(REGEN_MAIN)) {
                return "regen";
            } else if (baseChain.equals(JUNO_MAIN)) {
                return "juno";
            } else if (baseChain.equals(BITCANNA_MAIN)) {
                return "bitcanna";
            } else if (baseChain.equals(STARGAZE_MAIN)) {
                return "stargaze";
            } else if (baseChain.equals(COMDEX_MAIN)) {
                return "comdex";
            } else if (baseChain.equals(SECRET_MAIN)) {
                return "secret";
            } else if (baseChain.equals(BITSONG_MAIN)) {
                return "bitsong";
            } else if (baseChain.equals(ALTHEA_MAIN)) {
                return "althea";
            } else if (baseChain.equals(INJ_MAIN)) {
                return "injective";
            } else if (baseChain.equals(DESMOS_MAIN)) {
                return "desmos";
            } else if (baseChain.equals(GRABRIDGE_MAIN)) {
                return "gravity-bridge";
            } else if (baseChain.equals(LUM_MAIN)) {
                return "lum";
            } else if (baseChain.equals(CHIHUAHUA_MAIN)) {
                return "chihuahua";
            } else if (baseChain.equals(AXELAR_MAIN)) {
                return "axelar";
            } else if (baseChain.equals(KONSTELL_MAIN)) {
                return "konstellation";
            } else if (baseChain.equals(UMEE_MAIN)) {
                return "umee";
            } else if (baseChain.equals(EVMOS_MAIN)) {
                return "evmos";
            } else if (baseChain.equals(CUDOS_MAIN)) {
                return "cudos";
            } else if (baseChain.equals(PROVENANCE_MAIN)) {
                return "provenance";
            } else if (baseChain.equals(CERBERUS_MAIN)) {
                return "cerberus";
            } else if (baseChain.equals(OMNIFLIX_MAIN)) {
                return "omniflix";
            } else if (baseChain.equals(COSMOS_TEST)) {
                return "cosmos-testnet";
            }
        }
        return null;
    }

    public static boolean isValidChainAddress(BaseChain baseChain, String address) {
        return isValidChainAddress(baseChain, address, true);
    }

    public static boolean isValidChainAddress(BaseChain baseChain, String address, boolean checkValidBech32) {
        boolean result = false;
        if (baseChain != null) {
            if (address.startsWith("0x")) {
                result = WKey.isValidEthAddress(address) && baseChain.equals(OKEX_MAIN);
            } else if (!checkValidBech32 || WKey.isValidBech32(address)) {
                result = address.startsWith(baseChain.getChainAddressPrefix());
            }
        }
        return result;
    }

    public static ArrayList<BaseChain> getChainsFromAddress(String address) {
        ArrayList<BaseChain> result = new ArrayList<>();
        if (address != null) {
            if (address.startsWith("0x")) {
                if (WKey.isValidEthAddress(address)) {
                    result.add(OKEX_MAIN);
                }
            } else if (WKey.isValidBech32(address)) {
                for (BaseChain chain : BaseChain.values()) {
                    if (address.startsWith(chain.getChainAddressPrefix())) {
                        result.add(chain);
                    }
                }
            }
        }
        return result.isEmpty() ? null : result;
    }

    public static String getDefaultRelayerImg(BaseChain chain) {
        if (chain != null) {
            if (chain.equals(AKASH_MAIN)) {
                return AKASH_UNKNOWN_RELAYER;
            } else if (chain.equals(BAND_MAIN)) {
                return BAND_UNKNOWN_RELAYER;
            } else if (chain.equals(CERTIK_MAIN)) {
                return CERTIK_UNKNOWN_RELAYER;
            } else if (chain.equals(COSMOS_MAIN)) {
                return COSMOS_UNKNOWN_RELAYER;
            } else if (chain.equals(CRYPTO_MAIN)) {
                return CRYPTO_UNKNOWN_RELAYER;
            } else if (chain.equals(EMONEY_MAIN)) {
                return EMONEY_UNKNOWN_RELAYER;
            } else if (chain.equals(FETCHAI_MAIN)) {
                return FETCHAI_UNKNOWN_RELAYER;
            } else if (chain.equals(IMVERSED_MAIN)) {
                return IMVERSED_UNKNOWN_RELAYER;
            } else if (chain.equals(IRIS_MAIN)) {
                return IRIS_UNKNOWN_RELAYER;
            } else if (chain.equals(JUNO_MAIN)) {
                return JUNO_UNKNOWN_RELAYER;
            } else if (chain.equals(OSMOSIS_MAIN)) {
                return OSMOSIS_UNKNOWN_RELAYER;
            } else if (chain.equals(PERSIS_MAIN)) {
                return PERSIS_UNKNOWN_RELAYER;
            } else if (chain.equals(REGEN_MAIN)) {
                return REGEN_UNKNOWN_RELAYER;
            } else if (chain.equals(SENTINEL_MAIN)) {
                return SENTINEL_UNKNOWN_RELAYER;
            } else if (chain.equals(SIF_MAIN)) {
                return SIFCHAIN_UNKNOWN_RELAYER;
            } else if (chain.equals(IOV_MAIN)) {
                return STARNAME_UNKNOWN_RELAYER;
            } else if (chain.equals(KI_MAIN)) {
                return KI_UNKNOWN_RELAYER;
            } else if (chain.equals(BITCANNA_MAIN)) {
                return BITCANNA_UNKNOWN_RELAYER;
            } else if (chain.equals(RIZON_MAIN)) {
                return RIZON_UNKNOWN_RELAYER;
            } else if (chain.equals(MEDI_MAIN)) {
                return MEDI_UNKNOWN_RELAYER;
            } else if (chain.equals(STARGAZE_MAIN)) {
                return STARGAZE_UNKNOWN_RELAYER;
            } else if (chain.equals(COMDEX_MAIN)) {
                return COMDEX_UNKNOWN_RELAYER;
            } else if (chain.equals(SECRET_MAIN)) {
                return SECRET_UNKNOWN_RELAYER;
            } else if (chain.equals(INJ_MAIN)) {
                return INJ_UNKNOWN_RELAYER;
            } else if (chain.equals(BITSONG_MAIN)) {
                return BITSONG_UNKNOWN_RELAYER;
            } else if (chain.equals(DESMOS_MAIN)) {
                return DESMOS_UNKNOWN_RELAYER;
            } else if (chain.equals(GRABRIDGE_MAIN)) {
                return GRAB_UNKNOWN_RELAYER;
            } else if (chain.equals(LUM_MAIN)) {
                return LUM_UNKNOWN_RELAYER;
            } else if (chain.equals(CHIHUAHUA_MAIN)) {
                return CHIHUAHUA_UNKNOWN_RELAYER;
            } else if (chain.equals(KAVA_MAIN)) {
                return KAVA_UNKNOWN_RELAYER;
            } else if (chain.equals(AXELAR_MAIN)) {
                return AXELAR_UNKNOWN_RELAYER;
            } else if (chain.equals(KONSTELL_MAIN)) {
                return KONSTELL_UNKNOWN_RELAYER;
            } else if (chain.equals(UMEE_MAIN)) {
                return UMEE_UNKNOWN_RELAYER;
            } else if (chain.equals(EVMOS_MAIN)) {
                return EVMOS_UNKNOWN_RELAYER;
            } else if (chain.equals(CUDOS_MAIN)) {
                return CUDOS_UNKNOWN_RELAYER;
            } else if (chain.equals(PROVENANCE_MAIN)) {
                return PROVENANCE_UNKNOWN_RELAYER;
            } else if (chain.equals(CERBERUS_MAIN)) {
                return CERBERUS_UNKNOWN_RELAYER;
            } else if (chain.equals(OMNIFLIX_MAIN)) {
                return OMNIFLIX_UNKNOWN_RELAYER;
            }
        }
        return null;
    }

    public static SpannableString getDpEstAprCommission(BaseData baseData, BaseChain chain, BigDecimal commission) {
        final ChainParam.Params param = baseData.mChainParam;
        BigDecimal apr = BigDecimal.ZERO;
        if (param != null) {
            apr = param.getApr(chain);
        }
        BigDecimal calCommission = BigDecimal.ONE.subtract(commission);
        BigDecimal aprCommission = apr.multiply(calCommission).movePointRight(2);
        return getPercentDp(aprCommission);
    }

    public static SpannableString getDailyReward(Context c, BaseData baseData, BigDecimal commission, BigDecimal delegated, BaseChain chain) {
        final ChainParam.Params param = baseData.mChainParam;
        BigDecimal apr = BigDecimal.ZERO;
        if (param != null) {
            if (BigDecimal.ZERO.compareTo(param.getRealApr(chain)) == 0) {
                apr = param.getApr(chain);
            } else {
                apr = param.getRealApr(chain);
            }
        }
        BigDecimal calCommission = BigDecimal.ONE.subtract(commission);
        BigDecimal aprCommission = apr.multiply(calCommission);
        BigDecimal dayReward = delegated.multiply(aprCommission).divide(new BigDecimal("365"), 0, RoundingMode.DOWN);
        return getDpAmount2(dayReward, mainDivideDecimal(chain), mainDisplayDecimal(chain));
    }

    public static SpannableString getMonthlyReward(Context c, BaseData baseData, BigDecimal commission, BigDecimal delegated, BaseChain chain) {
        final ChainParam.Params param = baseData.mChainParam;
        BigDecimal apr = BigDecimal.ZERO;
        if (param != null) {
            if (BigDecimal.ZERO.compareTo(param.getRealApr(chain)) == 0) {
                apr = param.getApr(chain);
            } else {
                apr = param.getRealApr(chain);
            }
        }
        BigDecimal calCommission = BigDecimal.ONE.subtract(commission);
        BigDecimal aprCommission = apr.multiply(calCommission);
        BigDecimal dayReward = delegated.multiply(aprCommission).divide(new BigDecimal("12"), 0, RoundingMode.DOWN);
        return getDpAmount2(dayReward, mainDivideDecimal(chain), mainDisplayDecimal(chain));
    }

    public static String getKavaBaseDenom(String denom) {
        if (denom.equalsIgnoreCase(KAVA_MAIN.getMainDenom())) {
            return KAVA_MAIN.getMainDenom();
        } else if (denom.equalsIgnoreCase(TOKEN_HARD)) {
            return TOKEN_HARD;
        } else if (denom.equalsIgnoreCase(TOKEN_USDX)) {
            return TOKEN_USDX;
        } else if (denom.equalsIgnoreCase(TOKEN_SWP)) {
            return TOKEN_SWP;
        } else if (denom.equalsIgnoreCase(TOKEN_HTLC_KAVA_BNB)) {
            return "bnb";
        } else if (denom.equalsIgnoreCase(TOKEN_HTLC_KAVA_XRPB) || denom.equalsIgnoreCase("xrbp")) {
            return "xrp";
        } else if (denom.equalsIgnoreCase(TOKEN_HTLC_KAVA_BUSD)) {
            return "busd";
        } else if (denom.contains("btc")) {
            return "btc";
        }
        return "";
    }

    public static BigDecimal kavaTokenDollorValue(BaseData baseData, String denom, BigDecimal amount) {
        int dpDecimal = WUtil.getKavaCoinDecimal(baseData, denom);
        HashMap<String, kava.pricefeed.v1beta1.QueryOuterClass.CurrentPriceResponse> prices = baseData.mKavaTokenPrice;
        if (denom.equals("hard") && prices.get("hard:usd") != null) {
            BigDecimal price = new BigDecimal(prices.get("hard:usd").getPrice());
            return amount.movePointLeft(dpDecimal).multiply(price);

        } else if (denom.contains("btc") && prices.get("btc:usd") != null) {
            BigDecimal price = new BigDecimal(prices.get("btc:usd").getPrice());
            return amount.movePointLeft(dpDecimal).multiply(price);

        } else if (denom.contains("bnb") && prices.get("bnb:usd") != null) {
            BigDecimal price = new BigDecimal(prices.get("bnb:usd").getPrice());
            return amount.movePointLeft(dpDecimal).multiply(price);

        } else if (denom.contains("xrp") && prices.get("xrp:usd") != null) {
            BigDecimal price = new BigDecimal(prices.get("xrp:usd").getPrice());
            return amount.movePointLeft(dpDecimal).multiply(price);

        } else if (denom.contains("usdx") && prices.get("usdx:usd") != null) {
            BigDecimal price = new BigDecimal(prices.get("usdx:usd").getPrice());
            return amount.movePointLeft(dpDecimal).multiply(price);

        } else if (denom.contains("busd") && prices.get("busd:usd") != null) {
            BigDecimal price = new BigDecimal(prices.get("busd:usd").getPrice());
            return amount.movePointLeft(dpDecimal).multiply(price);
        }
        return BigDecimal.ZERO;

    }

    public static String getKavaPriceFeedSymbol(String denom) {
        if (denom.equalsIgnoreCase(KAVA_MAIN.getMainDenom())) {
            return "kava:usd";
        } else if (denom.equalsIgnoreCase(TOKEN_HARD)) {
            return "hard:usd";
        } else if (denom.equalsIgnoreCase(TOKEN_USDX)) {
            return "usdx:usd";
        } else if (denom.equalsIgnoreCase(TOKEN_SWP)) {
            return "swp:usd";
        } else if (denom.equalsIgnoreCase(TOKEN_HTLC_KAVA_BNB)) {
            return "bnb:usd";
        } else if (denom.equalsIgnoreCase(TOKEN_HTLC_KAVA_XRPB)) {
            return "xrp:usd";
        } else if (denom.equalsIgnoreCase(TOKEN_HTLC_KAVA_BUSD)) {
            return "busd:usd";
        } else if (denom.contains("btc")) {
            return "btc:usd";
        }
        return "";
    }

    public static BigDecimal getKavaPriceFeed(BaseData baseData, String denom) {
        String feedSymbol = getKavaPriceFeedSymbol(denom);
        if (baseData.mKavaTokenPrice.get(feedSymbol) == null) {
            return BigDecimal.ZERO;
        }
        return new BigDecimal(baseData.mKavaTokenPrice.get(feedSymbol).getPrice()).movePointLeft(18);
    }

    public static BigDecimal convertTokenToKava(BaseData baseData, String denom) {
        BigDecimal tokenAmount = baseData.getAvailable(denom).add(baseData.getVesting(denom));
        BigDecimal totalTokenValue = kavaTokenDollorValue(baseData, denom, tokenAmount);
        return totalTokenValue.movePointRight(6).divide(perUsdValue(baseData, KAVA_MAIN.getMainDenom()), 6, RoundingMode.DOWN);
    }

    public static BigDecimal okExTokenDollorValue(BaseData baseData, OkToken okToken, BigDecimal amount) {
        if (okToken != null && okToken.original_symbol != null) {
            if (okToken.original_symbol.equals("usdt") || okToken.original_symbol.equals("usdc") || okToken.original_symbol.equals("usdk")) {
                return amount;

            } else if (okToken.original_symbol.equals("okb") && baseData.mOKBPrice != null) {
                return amount.multiply(baseData.mOKBPrice);

            } else if (baseData.mOkTickersList != null) {
                //TODO display with ticker update!
                ArrayList<OkTicker> tickers = baseData.mOkTickersList.data;
                return BigDecimal.ZERO;
            }
        }
        return BigDecimal.ZERO;
    }

    public static BigDecimal convertTokenToOkt(BaseData baseData, String denom) {
        OkToken okToken = baseData.okToken(denom);
        if (okToken != null) {
            BigDecimal tokenAmount = baseData.availableAmount(denom).add(baseData.lockedAmount(denom));
            BigDecimal totalTokenValue = okExTokenDollorValue(baseData, okToken, tokenAmount);
            return totalTokenValue.divide(perUsdValue(baseData, OKEX_MAIN.getMainDenom()), 18, RoundingMode.DOWN);
        }
        return BigDecimal.ZERO;
    }


    public static SpannableString getDpRawDollor(Context c, String price, int scale) {
        BigDecimal mPrice = new BigDecimal(price);
        SpannableString result;
        result = new SpannableString("$ " + getDecimalFormat(scale).format(mPrice));
        result.setSpan(new RelativeSizeSpan(0.8f), result.length() - scale, result.length(), SPAN_INCLUSIVE_INCLUSIVE);
        return result;
    }

    public static SpannableString getDpRawDollor(Context c, BigDecimal price, int scale) {
        SpannableString result;
        result = new SpannableString("$ " + getDecimalFormat(scale).format(price));
        result.setSpan(new RelativeSizeSpan(0.8f), result.length() - scale, result.length(), SPAN_INCLUSIVE_INCLUSIVE);
        return result;
    }

    public static SpannableString getPercentDp(BigDecimal input) {
        return getDpString(input.setScale(2, RoundingMode.DOWN).toPlainString() + "%", 3);
    }

    public static SpannableString getPercentDp(BigDecimal input, int scale) {
        return getDpString(input.setScale(scale, RoundingMode.DOWN).toPlainString() + "%", scale + 1);
    }


    //Token & Price

    public static BigDecimal valueChange(BaseData baseData, String denom) {
        if (baseData.getPrice(denom) != null) {
            return baseData.getPrice(denom).priceChange();
        }
        return BigDecimal.ZERO.setScale(2, RoundingMode.FLOOR);
    }

    public static SpannableString dpValueChange(BaseData baseData, String denom) {
        return getDpString(valueChange(baseData, denom).toPlainString() + "% (24h)", 9);
    }

    public static BigDecimal perUsdValue(BaseData baseData, String denom) {
        if (denom.contains("gamm/pool/")) {
            BalancerPool.Pool pool = baseData.getOsmosisPoolByDenom(denom);
            return WUtil.getOsmoLpTokenPerUsdPrice(baseData, pool);
        }
        if (denom.startsWith("pool") && denom.length() >= 68) {
            ChainParam.GdexStatus pool = baseData.getParamGravityPoolByDenom(denom);
            return WUtil.getParamGdexLpTokenPerUsdPrice(baseData, pool);
        }
        if (denom.equals(TOKEN_EMONEY_EUR) || denom.equals(TOKEN_EMONEY_CHF) || denom.equals(TOKEN_EMONEY_DKK) ||
                denom.equals(TOKEN_EMONEY_NOK) || denom.equals(TOKEN_EMONEY_SEK)) {
            if (baseData.getPrice("usdt") != null && baseData.getPrice("usdt").prices != null) {
                for (Price.Prices price : baseData.getPrice("usdt").prices) {
                    if (price.currency.equalsIgnoreCase(denom.substring(1))) {
                        return BigDecimal.ONE.divide(new BigDecimal(price.current_price), 18, RoundingMode.DOWN);
                    }
                }
            }
        }
        if (baseData.getPrice(denom) != null) {
            return baseData.getPrice(denom).currencyPrice("usd").setScale(18, RoundingMode.DOWN);
        }
        return BigDecimal.ZERO.setScale(18, RoundingMode.DOWN);
    }

    public static BigDecimal usdValue(BaseData baseData, String denom, BigDecimal amount, int divider) {
        return perUsdValue(baseData, denom).multiply(amount).movePointLeft(divider).setScale(3, RoundingMode.DOWN);
    }

    public static BigDecimal perUserCurrencyValue(BaseData baseData, String denom) {
        if (baseData.getCurrency() == 0) {
            return perUsdValue(baseData, denom);
        } else if (baseData.getPrice("usdt") != null) {
            final Price usdtInfo = baseData.getPrice("usdt");
            final BigDecimal usdtPrice = usdtInfo.currencyPrice(baseData.getCurrencyString().toLowerCase());
            return perUsdValue(baseData, denom).multiply(usdtPrice).setScale(3, RoundingMode.DOWN);
        }
        return BigDecimal.ZERO.setScale(3, RoundingMode.DOWN);
    }

    public static BigDecimal perBtcValue(BaseData baseData, String denom) {
        final Price usdtInfo = baseData.getPrice("usdt");
        if (usdtInfo != null) {
            final BigDecimal usdtPrice = usdtInfo.currencyPrice("btc");
            return perUsdValue(baseData, denom).multiply(usdtPrice).setScale(8, RoundingMode.DOWN);
        }
        return BigDecimal.ZERO.setScale(8, RoundingMode.DOWN);
    }

    public static SpannableString dpPerUserCurrencyValue(BaseData baseData, String denom) {
        BigDecimal totalValue = perUserCurrencyValue(baseData, denom);
        final String formatted = baseData.getCurrencySymbol() + " " + getDecimalFormat(3).format(totalValue);
        return dpCurrencyValue(formatted, 3);
    }

    public static BigDecimal userCurrencyValue(BaseData baseData, String denom, BigDecimal amount, int divider) {
        return perUserCurrencyValue(baseData, denom).multiply(amount).movePointLeft(divider).setScale(3, RoundingMode.DOWN);
    }

    public static SpannableString dpUserCurrencyValue(BaseData baseData, String denom, BigDecimal amount, int divider) {
        BigDecimal totalValue = userCurrencyValue(baseData, denom, amount, divider);
        final String formatted = baseData.getCurrencySymbol() + " " + getDecimalFormat(3).format(totalValue);
        return dpCurrencyValue(formatted, 3);
    }

    public static BigDecimal btcValue(BaseData baseData, String denom, BigDecimal amount, int divider) {
        return perBtcValue(baseData, denom).multiply(amount).movePointLeft(divider).setScale(8, RoundingMode.DOWN);
    }

    public static BigDecimal allAssetToUserCurrency(BaseChain baseChain, BaseData baseData) {
        BigDecimal totalValue = BigDecimal.ZERO;
        if (baseChain.isGRPC()) {
            for (Coin coin : baseData.mGrpcBalance) {
                if (coin.denom.equals(baseChain.getMainDenom())) {
                    BigDecimal amount = baseData.getAllMainAsset(baseChain.getMainDenom());
                    BigDecimal assetValue = userCurrencyValue(baseData, coin.denom, amount, mainDivideDecimal(baseChain));
                    totalValue = totalValue.add(assetValue);
                } else if (baseChain.equals(COSMOS_MAIN) && coin.denom.startsWith("pool")) {
                    BigDecimal amount = baseData.getAvailable(coin.denom);
                    BigDecimal assetValue = userCurrencyValue(baseData, coin.denom, amount, 6);
                    totalValue = totalValue.add(assetValue);
                } else if (baseChain.equals(OSMOSIS_MAIN) && coin.denom.equals(TOKEN_ION)) {
                    BigDecimal amount = baseData.getAvailable(coin.denom);
                    BigDecimal assetValue = userCurrencyValue(baseData, coin.denom, amount, 6);
                    totalValue = totalValue.add(assetValue);
                } else if (baseChain.equals(OSMOSIS_MAIN) && coin.denom.contains("gamm/pool")) {
                    BigDecimal amount = baseData.getAvailable(coin.denom);
                    BigDecimal assetValue = userCurrencyValue(baseData, coin.denom, amount, 18);
                    totalValue = totalValue.add(assetValue);
                } else if (baseChain.equals(SIF_MAIN) && coin.denom.startsWith("c")) {
                    BigDecimal amount = baseData.getAvailable(coin.denom);
                    int decimal = WUtil.getSifCoinDecimal(baseData, coin.denom);
                    BigDecimal assetValue = userCurrencyValue(baseData, coin.denom.substring(1), amount, decimal);
                    totalValue = totalValue.add(assetValue);
                } else if (baseChain.equals(EMONEY_MAIN) || coin.denom.startsWith("e")) {
                    BigDecimal available = baseData.getAvailable(coin.denom);
                    totalValue = totalValue.add(userCurrencyValue(baseData, coin.denom, available, 6));
                } else if (baseChain.equals(KAVA_MAIN) && !coin.isIbc()) {
                    BigDecimal amount = baseData.getAvailable(coin.denom);
                    amount = amount.add(baseData.getVesting(coin.denom));
                    String kavaDenom = WDp.getKavaBaseDenom(coin.denom);
                    int kavaDecimal = WUtil.getKavaCoinDecimal(baseData, coin.denom);
                    BigDecimal assetValue = userCurrencyValue(baseData, kavaDenom, amount, kavaDecimal);
                    totalValue = totalValue.add(assetValue);
                } else if (baseChain.equals(GRABRIDGE_MAIN) && coin.denom.startsWith("gravity")) {
                    Assets assets = baseData.getAsset(coin.denom);
                    BigDecimal available = baseData.getAvailable(assets.denom);
                    totalValue = totalValue.add(userCurrencyValue(baseData, assets.origin_symbol, available, assets.decimal));
                } else if (baseChain.equals(INJ_MAIN) && coin.denom.startsWith("peggy")) {
                    Assets assets = baseData.getAsset(coin.denom);
                    BigDecimal available = baseData.getAvailable(assets.denom);
                    totalValue = totalValue.add(userCurrencyValue(baseData, assets.origin_symbol, available, assets.decimal));
                } else if (coin.isIbc()) {
                    BigDecimal amount = baseData.getAvailable(coin.denom);
                    IbcToken ibcToken = baseData.getIbcToken(coin.denom);
                    if (ibcToken != null && ibcToken.auth) {
                        BigDecimal assetValue = userCurrencyValue(baseData, ibcToken.base_denom, amount, ibcToken.decimal);
                        totalValue = totalValue.add(assetValue);
                    }
                }
            }

            if (baseData.getCw20sGrpc(baseChain).size() > 0) {
                for (Cw20Assets assets : baseData.getCw20sGrpc(baseChain)) {
                    BigDecimal amount = assets.getAmount();
                    totalValue = totalValue.add(userCurrencyValue(baseData, assets.denom, amount, assets.decimal));
                }
            }
        } else if (baseChain.equals(BNB_MAIN)) {
            for (Balance balance : baseData.mBalances) {
                if (balance.symbol.equals(baseChain.getMainDenom())) {
                    BigDecimal amount = baseData.getAllBnbTokenAmount(balance.symbol);
                    BigDecimal assetValue = userCurrencyValue(baseData, baseChain.getMainDenom(), amount, mainDivideDecimal(baseChain));
                    totalValue = totalValue.add(assetValue);
                } else {
                    BigDecimal amount = baseData.getAllBnbTokenAmount(balance.symbol);
                    BigDecimal convertAmount = WUtil.getBnbConvertAmount(baseData, balance.symbol, amount);
                    BigDecimal assetValue = userCurrencyValue(baseData, baseChain.getMainDenom(), convertAmount, mainDivideDecimal(baseChain));
                    totalValue = totalValue.add(assetValue);
                }
            }

        } else if (baseChain.equals(OKEX_MAIN)) {
            for (Balance balance : baseData.mBalances) {
                if (balance.symbol.equals(baseChain.getMainDenom())) {
                    BigDecimal amount = baseData.getAllExToken(balance.symbol);
                    BigDecimal assetValue = userCurrencyValue(baseData, baseChain.getMainDenom(), amount, mainDivideDecimal(baseChain));
                    totalValue = totalValue.add(assetValue);
                } else {
                    BigDecimal convertAmount = convertTokenToOkt(baseData, balance.symbol);
                    BigDecimal assetValue = userCurrencyValue(baseData, baseChain.getMainDenom(), convertAmount, mainDivideDecimal(baseChain));
                    totalValue = totalValue.add(assetValue);
                }
            }
        } else {
            for (Balance balance : baseData.mBalances) {
                if (balance.symbol.equals(baseChain.getMainDenom())) {
                    BigDecimal amount = baseData.getAllMainAssetOld(balance.symbol);
                    BigDecimal assetValue = userCurrencyValue(baseData, balance.symbol, amount, mainDivideDecimal(baseChain));
                    totalValue = totalValue.add(assetValue);
                }
            }

        }
        return totalValue;
    }

    public static BigDecimal allAssetToBtc(BaseChain baseChain, BaseData baseData) {
        BigDecimal totalValue = BigDecimal.ZERO;
        if (baseChain.isGRPC()) {
            for (Coin coin : baseData.mGrpcBalance) {
                if (coin.denom.equals(baseChain.getMainDenom())) {
                    BigDecimal amount = baseData.getAllMainAsset(baseChain.getMainDenom());
                    BigDecimal btcValue = btcValue(baseData, coin.denom, amount, mainDivideDecimal(baseChain));
                    totalValue = totalValue.add(btcValue);
                } else if (baseChain.equals(OSMOSIS_MAIN) && coin.denom.equals(TOKEN_ION)) {
                    BigDecimal amount = baseData.getAvailable(coin.denom);
                    BigDecimal btcValue = btcValue(baseData, coin.denom, amount, 6);
                    totalValue = totalValue.add(btcValue);
                } else if (baseChain.equals(SIF_MAIN) && coin.denom.startsWith("c")) {
                    BigDecimal amount = baseData.getAvailable(coin.denom);
                    int decimal = WUtil.getSifCoinDecimal(baseData, coin.denom);
                    BigDecimal btcValue = btcValue(baseData, coin.denom.substring(1), amount, decimal);
                    totalValue = totalValue.add(btcValue);
                } else if (baseChain.equals(KAVA_MAIN)) {
                    if (coin.denom.equals(baseChain.getMainDenom())) {
                        BigDecimal amount = baseData.getAllMainAsset(coin.denom);
                        BigDecimal btcValue = btcValue(baseData, baseChain.getMainDenom(), amount, mainDivideDecimal(baseChain));
                        totalValue = totalValue.add(btcValue);
                    } else {
                        BigDecimal convertAmount = convertTokenToKava(baseData, coin.denom);
                        BigDecimal btcValue = btcValue(baseData, baseChain.getMainDenom(), convertAmount, mainDivideDecimal(baseChain));
                        totalValue = totalValue.add(btcValue);
                    }
                } else if (coin.denom.startsWith("ibc/")) {
                    BigDecimal amount = baseData.getAvailable(coin.denom);
                    IbcToken ibcToken = baseData.getIbcToken(coin.denom);
                    if (ibcToken != null && ibcToken.auth) {
                        BigDecimal btcValue = btcValue(baseData, ibcToken.base_denom, amount, ibcToken.decimal);
                        totalValue = totalValue.add(btcValue);
                    }
                }
            }
        } else if (baseChain.equals(BNB_MAIN)) {
            for (Balance balance : baseData.mBalances) {
                if (balance.symbol.equals(baseChain.getMainDenom())) {
                    BigDecimal amount = baseData.getAllBnbTokenAmount(balance.symbol);
                    BigDecimal btcValue = btcValue(baseData, baseChain.getMainDenom(), amount, mainDivideDecimal(baseChain));
                    totalValue = totalValue.add(btcValue);
                } else {
                    BigDecimal amount = baseData.getAllBnbTokenAmount(balance.symbol);
                    BigDecimal convertAmount = WUtil.getBnbConvertAmount(baseData, balance.symbol, amount);
                    BigDecimal btcValue = btcValue(baseData, baseChain.getMainDenom(), convertAmount, mainDivideDecimal(baseChain));
                    totalValue = totalValue.add(btcValue);
                }
            }

        } else if (baseChain.equals(OKEX_MAIN)) {
            for (Balance balance : baseData.mBalances) {
                if (balance.symbol.equals(baseChain.getMainDenom())) {
                    BigDecimal amount = baseData.getAllExToken(balance.symbol);
                    BigDecimal btcValue = btcValue(baseData, baseChain.getMainDenom(), amount, mainDivideDecimal(baseChain));
                    totalValue = totalValue.add(btcValue);
                } else {
                    BigDecimal convertAmount = convertTokenToOkt(baseData, balance.symbol);
                    BigDecimal btcValue = btcValue(baseData, baseChain.getMainDenom(), convertAmount, mainDivideDecimal(baseChain));
                    totalValue = totalValue.add(btcValue);
                }
            }

        } else {
            for (Balance balance : baseData.mBalances) {
                if (balance.symbol.equals(baseChain.getMainDenom())) {
                    BigDecimal amount = baseData.getAllMainAssetOld(balance.symbol);
                    BigDecimal btcValue = btcValue(baseData, balance.symbol, amount, mainDivideDecimal(baseChain));
                    totalValue = totalValue.add(btcValue);
                }
            }

        }
        return totalValue;
    }

    public static SpannableString dpAllAssetValueUserCurrency(BaseChain baseChain, BaseData baseData) {
        BigDecimal totalValue = allAssetToUserCurrency(baseChain, baseData);
        final String formatted = baseData.getCurrencySymbol() + " " + getDecimalFormat(3).format(totalValue);
        return dpCurrencyValue(formatted, 3);
    }

    public static SpannableString dpAllAssetValueBtc(BaseChain baseChain, BaseData baseData) {
        BigDecimal totalValue = allAssetToBtc(baseChain, baseData);
        final String formatted = getDecimalFormat(8).format(totalValue);
        return dpCurrencyValue(formatted, 8);
    }

    public static SpannableString dpCurrencyValue(String input, int dpPoint) {
        return getDpString(input, dpPoint);
    }


    public static SpannableString getSelfBondRate(String total, String self) {
        BigDecimal result = new BigDecimal(self).multiply(new BigDecimal("100")).divide(new BigDecimal(total), 2, RoundingMode.DOWN);
        return getPercentDp(result);
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

    public static String DpBNBTxType(Context c, BnbHistory history, String address) {
        String result = c.getString(R.string.tx_known);
        switch (history.txType) {
            case "NEW_ORDER":
                result = c.getString(R.string.tx_new_order);

                break;
            case "CANCEL_ORDER":
                result = c.getString(R.string.tx_Cancel_order);

                break;
            case "TRANSFER":
                if (!TextUtils.isEmpty(history.fromAddr) && address.equals(history.fromAddr)) {
                    result = c.getString(R.string.tx_send);
                } else {
                    result = c.getString(R.string.tx_receive);
                }

                break;
            case "HTL_TRANSFER":
                if (history.fromAddr.equals(address)) {
                    result = c.getString(R.string.tx_send_htlc);
                } else if (history.toAddr.equals(address)) {
                    result = c.getString(R.string.tx_receive_htlc);
                } else {
                    result = c.getString(R.string.tx_create_htlc);
                }

                break;
            case "CLAIM_HTL":
                result = c.getString(R.string.tx_claim_htlc);

                break;
            case "REFUND_HTL":
                result = c.getString(R.string.tx_refund_htlc);

                break;
        }
        return result;

    }

    public static String getPath(BaseChain chain, int position, int customPath) {
        if (chain.equals(BNB_MAIN)) {
            return BaseConstant.KEY_BNB_PATH + position;

        } else if (chain.equals(KAVA_MAIN)) {
            if (customPath == 0) {
                return BaseConstant.KEY_PATH + position;
            } else {
                return BaseConstant.KEY_NEW_KAVA_PATH + position;
            }

        } else if (chain.equals(BAND_MAIN)) {
            return BaseConstant.KEY_BAND_PATH + position;

        } else if (chain.equals(IOV_MAIN)) {
            return BaseConstant.KEY_IOV_PATH + position;

        } else if (chain.equals(OKEX_MAIN)) {
            if (customPath == 0) {
                return BaseConstant.KEY_NEW_OK_PATH + position + (" (Tendermint Type) ");
            } else if (customPath == 1) {
                return BaseConstant.KEY_NEW_OK_PATH + position + (" (Ethermint Type) ");
            } else {
                return BaseConstant.KEY_ETH_PATH + position + (" (Ethereum Type) ");
            }

        } else if (chain.equals(SECRET_MAIN)) {
            if (customPath == 0) {
                return BaseConstant.KEY_PATH + position;
            } else {
                return BaseConstant.KEY_NEW_SECRET_PATH + position;
            }

        } else if (chain.equals(PERSIS_MAIN)) {
            return BaseConstant.KEY_PERSIS_PATH + position;

        } else if (chain.equals(CRYPTO_MAIN)) {
            return BaseConstant.KEY_CRYPTO_PATH + position;

        } else if (chain.equals(MEDI_MAIN)) {
            return KEY_MEDI_PATH + position;

        } else if (chain.equals(ALTHEA_TEST)) {
            return KEY_ALTHEA_PATH + position;

        } else if (chain.equals(FETCHAI_MAIN)) {
            if (customPath == 1) {
                return KEY_ETH_NON_LEDGER_PATH + position;
            } else if (customPath == 2) {
                return KEY_ETH_LEDGER_LIVE_PATH_1 + position + KEY_ETH_LEDGER_LIVE_PATH_2;
            } else if (customPath == 3) {
                return KEY_ETH_LEDGER_LEGACY_PATH + position;
            }
            return BaseConstant.KEY_FETCH_BASE_PATH + position;

        } else if (chain.equals(INJ_MAIN) || chain.equals(EVMOS_MAIN)) {
            return KEY_ETH_PATH + position;

        } else if (chain.equals(BITSONG_MAIN)) {
            return KEY_BITSONG_PATH + position;

        } else if (chain.equals(DESMOS_MAIN)) {
            return KEY_DESMOS_PATH + position;

        } else if (chain.equals(LUM_MAIN)) {
            if (customPath == 0) {
                return KEY_PATH + position;
            } else {
                return KEY_LUM_PATH + position;
            }

        } else if (chain.equals(PROVENANCE_MAIN)) {
            return KEY_PROVENANCE_PATH + position;

        } else {
            return BaseConstant.KEY_PATH + position;

        }
    }


    public static DecimalFormat getDecimalFormat(int cnt) {
        NumberFormat formatter = NumberFormat.getNumberInstance(Locale.US);
        DecimalFormat decimalformat = (DecimalFormat) formatter;
        decimalformat.setRoundingMode(RoundingMode.DOWN);

        StringBuilder stringBuilder = new StringBuilder("###,###,###,###,###,###,###,##0");
        if (cnt > 0) {
            if (cnt <= 18) {
                stringBuilder.append('.');
                for (int i = 0; i < cnt; i++) {
                    stringBuilder.append('0');
                }
            } else {
                stringBuilder.append(".000000");
            }
        }
        decimalformat.applyLocalizedPattern(stringBuilder.toString());
        return decimalformat;
    }


    public static String getDpTime(Context c, long time) {
        String result = "??";
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(time);
            SimpleDateFormat simpleFormat = new SimpleDateFormat(c.getString(R.string.str_dp_time_format1));
            result = simpleFormat.format(calendar.getTimeInMillis());
        } catch (Exception e) {
        }
        return result;
    }

    public static String getUnbondTime(Context c, BaseData baseData, BaseChain baseChain) {
        String result = "??";
        try {
            if (baseData != null && baseData.mChainParam != null) {
                Calendar calendar = Calendar.getInstance();
                calendar.add(Calendar.DATE, baseData.mChainParam.getUnbonding(baseChain));
                SimpleDateFormat unbondFormat = new SimpleDateFormat(c.getString(R.string.str_dp_time_format2));
                result = unbondFormat.format(calendar.getTimeInMillis());
            }
        } catch (Exception e) {
        }
        return result + "   " + "(" + baseData.mChainParam.getUnbonding(baseChain) + c.getString(R.string.str_unbonding_days_after);
    }

    public static String getUnbondingTimeleft(Context c, long finishTime) {
        String result = "??";
        try {
            long now = Calendar.getInstance().getTimeInMillis();
            long left = finishTime - now;

            if (left >= BaseConstant.CONSTANT_D) {
                result = "(" + (left / BaseConstant.CONSTANT_D) + " days remaining)";
            } else if (left >= BaseConstant.CONSTANT_H) {
                result = "(" + (left / BaseConstant.CONSTANT_H) + " hours remaining)";
            } else if (left >= BaseConstant.CONSTANT_M) {
                result = "(" + (left / BaseConstant.CONSTANT_M) + " minutes remaining)";
            } else {
                return "Soon";
            }

        } catch (Exception e) {
        }

        return result;
    }

    public static long dateToLong(Context c, String rawValue) {
        long result = 0;
        try {
            SimpleDateFormat blockDateFormat = new SimpleDateFormat(c.getString(R.string.str_block_time_format));
            blockDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            result = blockDateFormat.parse(rawValue).getTime();
        } catch (Exception e) {
        }
        return result;
    }

    public static long dateToLong2(Context c, String rawValue) {
        long result = 0;
        try {
            SimpleDateFormat blockDateFormat = new SimpleDateFormat(c.getString(R.string.str_tx_time_format));
            blockDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            result = blockDateFormat.parse(rawValue).getTime();
        } catch (Exception e) {
        }
        return result;
    }

    public static String getTimeformat(Context c, String rawValue) {
        String result = "??";
        try {
            SimpleDateFormat blockDateFormat = new SimpleDateFormat(c.getString(R.string.str_block_time_format));
            SimpleDateFormat myFormat = new SimpleDateFormat(c.getString(R.string.str_dp_time_format1));
            blockDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            result = myFormat.format(blockDateFormat.parse(rawValue));
        } catch (Exception e) {
        }

        return result;
    }

    public static String getTimeTxformat(Context c, String rawValue) {
        String result = "??";
        try {
            SimpleDateFormat blockDateFormat = new SimpleDateFormat(c.getString(R.string.str_tx_time_format));
            SimpleDateFormat myFormat = new SimpleDateFormat(c.getString(R.string.str_dp_time_format1));
            blockDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            result = myFormat.format(blockDateFormat.parse(rawValue));
        } catch (Exception e) {
        }

        return result;
    }

    public static String getTimeVoteformat(Context c, String rawValue) {
        String result = "??";
        try {
            SimpleDateFormat blockDateFormat = new SimpleDateFormat(c.getString(R.string.str_vote_time_format));
            SimpleDateFormat myFormat = new SimpleDateFormat(c.getString(R.string.str_dp_time_format1));
            blockDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            result = myFormat.format(blockDateFormat.parse(rawValue));
        } catch (Exception e) {
        }

        return result;
    }

    public static String getTimeGap(Context c, String rawValue) {
        String result = "";
        try {
            SimpleDateFormat blockDateFormat = new SimpleDateFormat(c.getString(R.string.str_block_time_format));
            blockDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            Date blockTime = blockDateFormat.parse(rawValue);
            Date nowTime = Calendar.getInstance().getTime();

            long difference = nowTime.getTime() - blockTime.getTime();

            long differenceSeconds = difference / 1000 % 60;
            long differenceMinutes = difference / (60 * 1000) % 60;
            long differenceHours = difference / (60 * 60 * 1000) % 24;
            long differenceDays = difference / (24 * 60 * 60 * 1000);

            if (differenceDays > 1) {
                result = "" + differenceDays + " " + c.getString(R.string.str_day);
            } else if (differenceDays == 1) {
                result = "" + differenceDays + c.getString(R.string.str_d) + " " + differenceHours + c.getString(R.string.str_h);
            } else {
                if (differenceHours > 0) {
                    result = "" + differenceHours + c.getString(R.string.str_h) + " " + differenceMinutes + c.getString(R.string.str_m);
                } else {
                    if (differenceMinutes > 0) {
                        result = "" + differenceMinutes + c.getString(R.string.str_m) + " " + differenceSeconds + c.getString(R.string.str_s);
                    } else {
                        result = differenceSeconds + c.getString(R.string.str_s);
                    }
                }

            }

        } catch (Exception e) {
        }

        return "(" + result + " " + c.getString(R.string.str_ago) + ")";
    }

    public static String getTimeTxGap(Context c, String rawValue) {
        String result = "";
        try {
            SimpleDateFormat blockDateFormat = new SimpleDateFormat(c.getString(R.string.str_tx_time_format));
            blockDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            Date blockTime = blockDateFormat.parse(rawValue);
            Date nowTime = Calendar.getInstance().getTime();

            long difference = nowTime.getTime() - blockTime.getTime();

            long differenceSeconds = difference / 1000 % 60;
            long differenceMinutes = difference / (60 * 1000) % 60;
            long differenceHours = difference / (60 * 60 * 1000) % 24;
            long differenceDays = difference / (24 * 60 * 60 * 1000);

            if (differenceDays > 1) {
                result = "" + differenceDays + " " + c.getString(R.string.str_day);
            } else if (differenceDays == 1) {
                result = "" + differenceDays + c.getString(R.string.str_d) + " " + differenceHours + c.getString(R.string.str_h);
            } else {
                if (differenceHours > 0) {
                    result = "" + differenceHours + c.getString(R.string.str_h) + " " + differenceMinutes + c.getString(R.string.str_m);
                } else {
                    if (differenceMinutes > 0) {
                        result = "" + differenceMinutes + c.getString(R.string.str_m) + " " + differenceSeconds + c.getString(R.string.str_s);
                    } else {
                        result = differenceSeconds + c.getString(R.string.str_s);
                    }
                }

            }

        } catch (Exception e) {
        }

        return "(" + result + " " + c.getString(R.string.str_ago) + ")";
    }

    public static String getTimeTxGap(Context c, long rawValue) {
        String result = "";
        try {
            Date blockTime = new Date(rawValue);
            Date nowTime = Calendar.getInstance().getTime();

            long difference = nowTime.getTime() - blockTime.getTime();

            long differenceSeconds = difference / 1000 % 60;
            long differenceMinutes = difference / (60 * 1000) % 60;
            long differenceHours = difference / (60 * 60 * 1000) % 24;
            long differenceDays = difference / (24 * 60 * 60 * 1000);

            if (differenceDays > 1) {
                result = "" + differenceDays + " " + c.getString(R.string.str_day);
            } else if (differenceDays == 1) {
                result = "" + differenceDays + c.getString(R.string.str_d) + " " + differenceHours + c.getString(R.string.str_h);
            } else {
                if (differenceHours > 0) {
                    result = "" + differenceHours + c.getString(R.string.str_h) + " " + differenceMinutes + c.getString(R.string.str_m);
                } else {
                    if (differenceMinutes > 0) {
                        result = "" + differenceMinutes + c.getString(R.string.str_m) + " " + differenceSeconds + c.getString(R.string.str_s);
                    } else {
                        result = differenceSeconds + c.getString(R.string.str_s);
                    }
                }
            }

        } catch (Exception e) {
        }

        return "(" + result + " " + c.getString(R.string.str_ago) + ")";
    }


    public static String cTimeString() {
        Calendar c = Calendar.getInstance();
        return "" + c.getTimeInMillis();
    }

    public static String threeMonthAgoTimeString() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, -3);
        return "" + c.getTimeInMillis();
    }

    public static int getChainColor(Context c, BaseChain chain) {
        int colorResId = R.color.colorGray0;
        if (chain != null) {
            colorResId = chain.getDenomColor();
        }
        return ContextCompat.getColor(c, colorResId);
    }

    public static ColorStateList getChainTintColor(Context c, BaseChain chain) {
        int colorResId = R.color.colorTransBg;
        if (chain != null) {
            colorResId = chain.getDenomColor();
        }
        return ContextCompat.getColorStateList(c, colorResId);
    }

    public static int getChainBgColor(Context c, BaseChain chain) {
        int colorResId = chain != null ? chain.getChainBackground() : R.color.colorTransBg;
        return ContextCompat.getColor(c, colorResId);
    }

    public static ColorStateList getTabColor(Context c, BaseChain chain) {
        int colorResId = chain != null ? chain.getChainTabColor() : R.color.color_tab_myvalidator;
        return ContextCompat.getColorStateList(c, colorResId);
    }

    public static void DpMainDenom(BaseChain chain, TextView textview) {
        DpMainDenom(chain.getChain(), textview);
    }

    public static void DpMainDenom(String chainName, TextView textView) {
        BaseChain chain = BaseChain.getChain(chainName);
        if (chain != null) {
            textView.setTextColor(ContextCompat.getColor(textView.getContext(), chain.getDenomColor()));
            textView.setText(textView.getContext().getString(chain.getSymbolTitle()));
        }
    }

    public static int tokenDivideDecimal(BaseData baseData, BaseChain baseChain, String denom) {
        String mainDenom = baseChain.getMainDenom();
        if (baseChain.isGRPC()) {
            if (denom.equalsIgnoreCase(mainDenom)) {
                return mainDivideDecimal(baseChain);
            }
            if (denom.startsWith("ibc/")) {
                return WUtil.getIbcDecimal(baseData, denom);
            }
            if (baseChain.equals(COSMOS_MAIN)) {
                return WUtil.getCosmosCoinDecimal(baseData, denom);
            } else if (baseChain.equals(OSMOSIS_MAIN)) {
                return WUtil.getOsmosisCoinDecimal(baseData, denom);
            } else if (baseChain.equals(SIF_MAIN)) {
                return WUtil.getSifCoinDecimal(baseData, denom);
            } else if (baseChain.equals(GRABRIDGE_MAIN)) {
                return WUtil.getGBridgeCoinDecimal(baseData, denom);
            } else if (baseChain.equals(INJ_MAIN)) {
                return WUtil.getInjCoinDecimal(baseData, denom);
            }
        } else {
            return 6;
        }
        return 6;
    }

    public static int mainDivideDecimal(BaseChain chain) {
        if (chain.equals(BNB_MAIN)) {
            return 0;
        } else if (chain.equals(OKEX_MAIN)) {
            return 0;
        } else if (chain.equals(FETCHAI_MAIN) || chain.equals(SIF_MAIN) || chain.equals(INJ_MAIN) || chain.equals(EVMOS_MAIN) || chain.equals(CUDOS_MAIN)) {
            return 18;
        } else if (chain.equals(CRYPTO_MAIN)) {
            return 8;
        } else if (chain.equals(PROVENANCE_MAIN)) {
            return 9;
        } else {
            return 6;
        }
    }

    public static int mainDisplayDecimal(BaseChain chain) {
        if (chain.equals(BNB_MAIN)) {
            return 8;
        } else if (chain.equals(OKEX_MAIN)) {
            return 18;
        } else if (chain.equals(FETCHAI_MAIN) || chain.equals(SIF_MAIN) || chain.equals(INJ_MAIN) || chain.equals(EVMOS_MAIN) || chain.equals(CUDOS_MAIN)) {
            return 18;
        } else if (chain.equals(CRYPTO_MAIN)) {
            return 8;
        } else if (chain.equals(PROVENANCE_MAIN)) {
            return 9;
        } else {
            return 6;
        }
    }

    public static int mainDivideDecimal(String denom) {
        if (denom.equals(BNB_MAIN.getMainDenom())) {
            return 8;
        } else if (denom.equals(OKEX_MAIN.getMainDenom())) {
            return 18;
        } else if (denom.equals(TOKEN_FET) || denom.equals(TOKEN_SIF) || denom.equals(TOKEN_INJ) || denom.equals(TOKEN_EVMOS) || denom.equals(TOKEN_CUDOS)) {
            return 18;
        } else if (denom.equals(TOKEN_CRO)) {
            return 8;
        } else if (denom.equals(TOKEN_HASH)) {
            return 9;
        } else {
            return 6;
        }
    }

    public static int mainDisplayDecimal(String denom) {
        if (denom.equals(BNB_MAIN.getMainDenom())) {
            return 8;
        } else if (denom.equals(OKEX_MAIN.getMainDenom())) {
            return 18;
        } else if (denom.equals(TOKEN_FET) || denom.equals(TOKEN_SIF) || denom.equals(TOKEN_INJ) || denom.equals(TOKEN_EVMOS) || denom.equals(TOKEN_CUDOS)) {
            return 18;
        } else if (denom.equals(TOKEN_CRO)) {
            return 8;
        } else if (denom.equals(TOKEN_HASH)) {
            return 9;
        } else {
            return 6;
        }
    }

    public static int getDpRiskColor(Context c, BigDecimal riskRate) {
        if (riskRate.longValue() <= 50) {
            return ContextCompat.getColor(c, R.color.colorCdpSafe);
        } else if (riskRate.longValue() < 80) {
            return ContextCompat.getColor(c, R.color.colorCdpStable);
        } else {
            return ContextCompat.getColor(c, R.color.colorCdpDanger);
        }
    }

    public static void DpRiskRate(Context c, BigDecimal riskRate, TextView textView, ImageView imageview) {
        textView.setText(WDp.getDpAmount2(riskRate, 0, 2));
        if (riskRate.floatValue() <= 50) {
            textView.setTextColor(ContextCompat.getColor(c, R.color.colorCdpSafe));
            if (imageview != null) {
                imageview.setImageResource(R.drawable.img_safe);
            }

        } else if (riskRate.floatValue() < 80) {
            textView.setTextColor(ContextCompat.getColor(c, R.color.colorCdpStable));
            if (imageview != null) {
                imageview.setImageResource(R.drawable.img_stable);
            }

        } else {
            textView.setTextColor(ContextCompat.getColor(c, R.color.colorCdpDanger));
            if (imageview != null) {
                imageview.setImageResource(R.drawable.img_danger);

            }
        }

    }

    public static void DpRiskButton(Context c, BigDecimal riskRate, Button button) {
        if (riskRate.longValue() <= 50) {
            button.setBackgroundResource(R.drawable.btn_score_safe_fill);
            button.setTextColor(ContextCompat.getColor(c, R.color.colorBlack));
            button.setTypeface(null, Typeface.BOLD);
            button.setText(String.format(Locale.ENGLISH, "%s %s", c.getString(R.string.str_safe), riskRate.toPlainString()));

        } else if (riskRate.longValue() < 80) {
            button.setBackgroundResource(R.drawable.btn_score_stable_fill);
            button.setTextColor(ContextCompat.getColor(c, R.color.colorBlack));
            button.setTypeface(null, Typeface.BOLD);
            button.setText(String.format(Locale.ENGLISH, "%s %s", c.getString(R.string.str_stable), riskRate.toPlainString()));

        } else {
            button.setBackgroundResource(R.drawable.btn_score_danger_fill);
            button.setTextColor(ContextCompat.getColor(c, R.color.colorBlack));
            button.setTypeface(null, Typeface.BOLD);
            button.setText(String.format(Locale.ENGLISH, "%s %s", c.getString(R.string.str_danger), riskRate.toPlainString()));
        }
    }

    public static void DpRiskButton2(Context c, BigDecimal riskRate, Button button) {
        if (riskRate.longValue() <= 50) {
            button.setBackgroundResource(R.drawable.btn_score_safe_fill);
            button.setTextColor(ContextCompat.getColor(c, R.color.colorBlack));
            button.setTypeface(null, Typeface.BOLD);
            button.setText(R.string.str_safe);

        } else if (riskRate.longValue() < 80) {
            button.setBackgroundResource(R.drawable.btn_score_stable_fill);
            button.setTextColor(ContextCompat.getColor(c, R.color.colorBlack));
            button.setTypeface(null, Typeface.BOLD);
            button.setText(R.string.str_stable);

        } else {
            button.setBackgroundResource(R.drawable.btn_score_danger_fill);
            button.setTextColor(ContextCompat.getColor(c, R.color.colorBlack));
            button.setTypeface(null, Typeface.BOLD);
            button.setText(R.string.str_danger);
        }
    }

    public static void DpRiskRate2(Context c, BigDecimal riskRate, TextView text, TextView rate, LinearLayout layer) {
        rate.setText(WDp.getDpAmount2(riskRate, 0, 2));
        if (riskRate.longValue() <= 50) {
            rate.setText(R.string.str_safe);
            layer.setBackgroundResource(R.drawable.btn_score_safe_fill);

        } else if (riskRate.longValue() < 80) {
            text.setText(R.string.str_stable);
            layer.setBackgroundResource(R.drawable.btn_score_stable_fill);

        } else {
            text.setText(R.string.str_danger);
            layer.setBackgroundResource(R.drawable.btn_score_danger_fill);
        }
    }

    public static void DpRiskRate3(Context c, BigDecimal riskRate, TextView score, TextView rate) {
        score.setText(WDp.getDpAmount2(riskRate, 0, 2));
        if (riskRate.longValue() <= 50) {
            rate.setText(R.string.str_safe);
            rate.setTextColor(ContextCompat.getColor(c, R.color.colorCdpSafe));
            score.setTextColor(ContextCompat.getColor(c, R.color.colorCdpSafe));

        } else if (riskRate.longValue() < 80) {
            rate.setText(R.string.str_stable);
            rate.setTextColor(ContextCompat.getColor(c, R.color.colorCdpStable));
            score.setTextColor(ContextCompat.getColor(c, R.color.colorCdpStable));

        } else {
            rate.setText(R.string.str_danger);
            rate.setTextColor(ContextCompat.getColor(c, R.color.colorCdpDanger));
            score.setTextColor(ContextCompat.getColor(c, R.color.colorCdpDanger));
        }
    }

    public static BigDecimal getCdpGrpcHiddenFee(Context c, BigDecimal outstandingDebt, Genesis.CollateralParam paramCdp, QueryOuterClass.CDPResponse myCdp) {
        BigDecimal result = BigDecimal.ZERO;
        try {
            long now = Calendar.getInstance().getTimeInMillis();
            SimpleDateFormat blockDateFormat = new SimpleDateFormat(c.getString(R.string.str_block_time_format));
            blockDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            long start = blockDateFormat.parse(myCdp.getFeesUpdated().toString()).getTime();
            Long gap = (now - start) / 1000;
            //TODO 냥냥하게 패딩
            gap = gap + 30;

            Double double1 = Double.parseDouble(paramCdp.getStabilityFee());
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
    public static String getDpChainName(Context context, BaseChain chain) {
        return context.getString(chain.getChainTitle());
    }

    // HTLC using
    public static void onDpChain(Context c, BaseChain chain, ImageView imgView, TextView txtView) {
        if (imgView != null) {
            imgView.setImageResource(chain.getChainIcon());
        }
        txtView.setText(c.getString(chain.getChainTitle()));
    }

    public static void onDpSwapChain(Context c, BaseChain chain, ImageView imgView, TextView txtView) {
        if (imgView != null) {
            imgView.setImageResource(chain.getChainIcon());
        }
        if (chain.equals(BNB_MAIN)) {
            txtView.setText(c.getString(R.string.str_binance));
        } else if (chain.equals(KAVA_MAIN)) {
            txtView.setText(c.getString(R.string.str_kava));
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
        } else if (basechain.equals(IMVERSED_MAIN)) {
            return IMVERSED_VAL_URL + opAddress + ".png";
        } else if (basechain.equals(IRIS_MAIN) || basechain.equals(IRIS_TEST)) {
            return IRIS_VAL_URL + opAddress + ".png";
        } else if (basechain.equals(AKASH_MAIN)) {
            return AKASH_VAL_URL + opAddress + ".png";
        } else if (basechain.equals(SENTINEL_MAIN)) {
            return SENTINEL_VAL_URL + opAddress + ".png";
        } else if (basechain.equals(PERSIS_MAIN)) {
            return PERSIS_VAL_URL + opAddress + ".png";
        } else if (basechain.equals(CRYPTO_MAIN)) {
            return CRYPTO_VAL_URL + opAddress + ".png";
        } else if (basechain.equals(OSMOSIS_MAIN)) {
            return OSMOSIS_VAL_URL + opAddress + ".png";
        } else if (basechain.equals(IOV_MAIN)) {
            return IOV_VAL_URL + opAddress + ".png";
        } else if (basechain.equals(SIF_MAIN)) {
            return SIF_VAL_URL + opAddress + ".png";
        } else if (basechain.equals(CERTIK_MAIN)) {
            return CERTIK_VAL_URL + opAddress + ".png";
        } else if (basechain.equals(MEDI_MAIN)) {
            return MEDI_VAL_URL + opAddress + ".png";
        } else if (basechain.equals(EMONEY_MAIN)) {
            return EMONEY_VAL_URL + opAddress + ".png";
        } else if (basechain.equals(FETCHAI_MAIN)) {
            return FETCH_VAL_URL + opAddress + ".png";
        } else if (basechain.equals(BAND_MAIN)) {
            return BAND_VAL_URL + opAddress + ".png";
        } else if (basechain.equals(RIZON_MAIN)) {
            return RIZON_VAL_URL + opAddress + ".png";
        } else if (basechain.equals(JUNO_MAIN)) {
            return JUNO_VAL_URL + opAddress + ".png";
        } else if (basechain.equals(REGEN_MAIN)) {
            return REGEN_VAL_URL + opAddress + ".png";
        } else if (basechain.equals(BITCANNA_MAIN)) {
            return BITCANNA_VAL_URL + opAddress + ".png";
        } else if (basechain.equals(ALTHEA_MAIN) || basechain.equals(ALTHEA_TEST)) {
            return ALTHEA_VAL_URL + opAddress + ".png";
        } else if (basechain.equals(STARGAZE_MAIN)) {
            return STARGAZE_VAL_URL + opAddress + ".png";
        } else if (basechain.equals(GRABRIDGE_MAIN)) {
            return GRABRIDGE_VAL_URL + opAddress + ".png";
        } else if (basechain.equals(COMDEX_MAIN)) {
            return COMDEX_VAL_URL + opAddress + ".png";
        } else if (basechain.equals(INJ_MAIN)) {
            return INJ_VAL_URL + opAddress + ".png";
        } else if (basechain.equals(BITSONG_MAIN)) {
            return BITSONG_VAL_URL + opAddress + ".png";
        } else if (basechain.equals(DESMOS_MAIN)) {
            return DESMOS_VAL_URL + opAddress + ".png";
        } else if (basechain.equals(SECRET_MAIN)) {
            return SECRET_VAL_URL + opAddress + ".png";
        } else if (basechain.equals(KI_MAIN)) {
            return KI_VAL_URL + opAddress + ".png";
        } else if (basechain.equals(LUM_MAIN)) {
            return LUM_VAL_URL + opAddress + ".png";
        } else if (basechain.equals(CHIHUAHUA_MAIN)) {
            return CHIHUAHUA_VAL_URL + opAddress + ".png";
        } else if (basechain.equals(UMEE_MAIN)) {
            return UMEE_VAL_URL + opAddress + ".png";
        } else if (basechain.equals(AXELAR_MAIN)) {
            return AXELAR_VAL_URL + opAddress + ".png";
        } else if (basechain.equals(KAVA_MAIN)) {
            return KAVA_VAL_URL + opAddress + ".png";
        } else if (basechain.equals(KONSTELL_MAIN)) {
            return KONSTELL_VAL_URL + opAddress + ".png";
        } else if (basechain.equals(EVMOS_MAIN)) {
            return EVMOS_VAL_URL + opAddress + ".png";
        } else if (basechain.equals(CUDOS_MAIN)) {
            return CUDOS_VAL_URL + opAddress + ".png";
        } else if (basechain.equals(PROVENANCE_MAIN)) {
            return PROVENANCE_VAL_URL + opAddress + ".png";
        } else if (basechain.equals(CERBERUS_MAIN)) {
            return CERBERUS_VAL_URL + opAddress + ".png";
        } else if (basechain.equals(OMNIFLIX_MAIN)) {
            return OMNIFLIX_VAL_URL + opAddress + ".png";
        } else if (basechain.equals(OKEX_MAIN)) {
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

    public static BigDecimal geTallySum(ResProposal proposal) {
        return new BigDecimal(proposal.voteMeta.yes_amount).add(new BigDecimal(proposal.voteMeta.no_amount)).add(new BigDecimal(proposal.voteMeta.no_with_veto_amount)).add(new BigDecimal(proposal.voteMeta.abstain_amount));
    }

    public static BigDecimal getYesPer(ResProposal proposal) {
        if (geTallySum(proposal).equals(BigDecimal.ZERO) || (new BigDecimal(proposal.voteMeta.yes_amount).longValue() == 0)) {
            return BigDecimal.ZERO.setScale(2);
        }
        return new BigDecimal(proposal.voteMeta.yes_amount).movePointRight(2).divide(geTallySum(proposal), 2, RoundingMode.HALF_UP);
    }

    public static BigDecimal getNoPer(ResProposal proposal) {
        if (geTallySum(proposal).equals(BigDecimal.ZERO) || (new BigDecimal(proposal.voteMeta.no_amount).longValue() == 0)) {
            return BigDecimal.ZERO.setScale(2);
        }
        return new BigDecimal(proposal.voteMeta.no_amount).movePointRight(2).divide(geTallySum(proposal), 2, RoundingMode.HALF_UP);
    }

    public static BigDecimal getAbstainPer(ResProposal proposal) {
        if (geTallySum(proposal).equals(BigDecimal.ZERO) || (new BigDecimal(proposal.voteMeta.abstain_amount).longValue() == 0)) {
            return BigDecimal.ZERO.setScale(2);
        }
        return new BigDecimal(proposal.voteMeta.abstain_amount).movePointRight(2).divide(geTallySum(proposal), 2, RoundingMode.HALF_UP);
    }

    public static BigDecimal getVetoPer(ResProposal proposal) {
        if (geTallySum(proposal).equals(BigDecimal.ZERO) || (new BigDecimal(proposal.voteMeta.no_with_veto_amount).longValue() == 0)) {
            return BigDecimal.ZERO.setScale(2);
        }
        return new BigDecimal(proposal.voteMeta.no_with_veto_amount).movePointRight(2).divide(geTallySum(proposal), 2, RoundingMode.HALF_UP);
    }

    public static BigDecimal getTurnout(BaseChain baseCahin, BaseData baseData, ResProposal proposal) {
        BigDecimal result = BigDecimal.ZERO;
        if (baseData != null && baseData.mChainParam != null) {
            if (geTallySum(proposal).equals(BigDecimal.ZERO)) {
                return BigDecimal.ZERO.setScale(2);

            } else {
                BigDecimal bonded = baseData.mChainParam.getBondedAmount(baseCahin);
                return geTallySum(proposal).movePointRight(2).divide(bonded, 2, RoundingMode.HALF_UP);
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
            for (Abci.StringEvent event : response.getTxResponse().getLogs(position).getEventsList()) {
                if (event.getType().equals("transfer")) {
                    for (int i = 0; i < event.getAttributesList().size(); i++) {
                        if (event.getAttributes(i).getKey().equals("recipient") && event.getAttributes(i).getValue().equals(Addr)) {
                            for (int j = i; j < event.getAttributesList().size(); j++) {
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

    public static BigDecimal onParseStakeReward(BaseChain baseChain, ServiceOuterClass.GetTxResponse response, String valAddr, int position) {
        BigDecimal result = BigDecimal.ZERO;
        if (response.getTxResponse().getLogsCount() > 0 && response.getTxResponse().getLogs(position) != null) {
            for (Abci.StringEvent event : response.getTxResponse().getLogs(position).getEventsList()) {
                if (event.getType().equals("withdraw_rewards")) {
                    for (int i = 0; i < event.getAttributesList().size(); i++) {
                        if (event.getAttributes(i).getKey().equals("validator") && event.getAttributes(i).getValue().equals(valAddr)) {
                            String rawValue = event.getAttributes(i - 1).getValue();
                            if (rawValue != null) {
                                for (String rawCoin : rawValue.split(",")) {
                                    if (rawCoin.contains(baseChain.getMainDenom())) {
                                        result = result.add(new BigDecimal(rawCoin.replaceAll("[^0-9]", "")));
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return result;
    }

    public static ArrayList<Coin> onParseCommission(ServiceOuterClass.GetTxResponse response, int position) {
        ArrayList<Coin> result = new ArrayList<>();
        if (response.getTxResponse().getLogsCount() > 0 && response.getTxResponse().getLogs(position) != null) {
            for (Abci.StringEvent event : response.getTxResponse().getLogs(position).getEventsList()) {
                if (event.getType().equals("withdraw_commission")) {
                    for (int i = 0; i < event.getAttributesList().size(); i++) {
                        if (event.getAttributes(i).getKey().equals("amount")) {
                            String rawValue = event.getAttributes(i).getValue();
                            for (String rawCoin : rawValue.split(",")) {
                                Pattern p = Pattern.compile("([0-9])+");
                                Matcher m = p.matcher(rawCoin);
                                if (m.find()) {
                                    String amount = m.group();
                                    String denom = rawCoin.substring(m.end());
                                    result.add(new Coin(denom, amount));
                                }
                            }
                        }
                    }
                }
            }
        }
        return result;
    }

    public static ArrayList<Coin> onParseKavaIncentiveGrpc(ServiceOuterClass.GetTxResponse tx, int position) {
        ArrayList<Coin> result = new ArrayList<>();
        if (tx.getTxResponse().getLogsList() != null && tx.getTxResponse().getLogsCount() > position && tx.getTxResponse().getLogs(position) != null) {
            for (Abci.StringEvent event : tx.getTxResponse().getLogs(position).getEventsList()) {
                if (event.getType().equalsIgnoreCase("claim_reward")) {
                    for (int i = 0; i < event.getAttributesList().size(); i++) {
                        if (event.getAttributes(i).getKey().equalsIgnoreCase("claim_amount")) {
                            String rawValue = event.getAttributes(i).getValue();
                            for (String rawCoin : rawValue.split(",")) {
                                Pattern p = Pattern.compile("([0-9])+");
                                Matcher m = p.matcher(rawCoin);
                                if (m.find()) {
                                    String amount = m.group();
                                    String denom = rawCoin.substring(m.end());
                                    result.add(new Coin(denom, amount));
                                }
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
        for (int i = 0; i <= position; i++) {
            result = result + vestingAccount.getVestingPeriods(i).getLength();
        }
        return result * 1000;
    }

    public static ArrayList<Vesting.Period> onParsePeriodicRemainVestings(Vesting.PeriodicVestingAccount vestingAccount) {
        ArrayList<Vesting.Period> result = new ArrayList<>();
        long cTime = Calendar.getInstance().getTime().getTime();
        for (int i = 0; i < vestingAccount.getVestingPeriodsCount(); i++) {
            long unlockTime = onParsePeriodicUnLockTime(vestingAccount, i);
            if (cTime < unlockTime) {
                result.add(Vesting.Period.newBuilder().setLength(unlockTime).addAllAmount(vestingAccount.getVestingPeriods(i).getAmountList()).build());
            }
        }
        return result;
    }

    public static ArrayList<Vesting.Period> onParsePeriodicRemainVestingsByDenom(Vesting.PeriodicVestingAccount vestingAccount, String denom) {
        ArrayList<Vesting.Period> result = new ArrayList<>();
        for (Vesting.Period vp : onParsePeriodicRemainVestings(vestingAccount)) {
            for (CoinOuterClass.Coin coin : vp.getAmountList()) {
                if (coin.getDenom().equals(denom)) {
                    result.add(vp);
                }
            }
        }
        return result;
    }

    public static BigDecimal onParsePeriodicRemainVestingsAmountByDenom(Vesting.PeriodicVestingAccount vestingAccount, String denom) {
        BigDecimal result = BigDecimal.ZERO;
        ArrayList<Vesting.Period> vps = onParsePeriodicRemainVestingsByDenom(vestingAccount, denom);
        for (Vesting.Period vp : vps) {
            for (CoinOuterClass.Coin coin : vp.getAmountList()) {
                if (coin.getDenom().equals(denom)) {
                    result = result.add(new BigDecimal(coin.getAmount()));
                }
            }
        }
        return result;
    }

    public static BigDecimal getAmountVp(Vesting.Period vp, String denom) {
        BigDecimal result = BigDecimal.ZERO;
        for (CoinOuterClass.Coin coin : vp.getAmountList()) {
            if (coin.getDenom().equals(denom)) {
                return new BigDecimal(coin.getAmount());
            }
        }
        return result;
    }

}
