package wannabit.io.cosmostaion.utils;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.RelativeSizeSpan;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

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
import osmosis.gamm.v1beta1.BalancerPoolOuterClass;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.dao.Balance;
import wannabit.io.cosmostaion.dao.ChainParam;
import wannabit.io.cosmostaion.dao.IbcToken;
import wannabit.io.cosmostaion.dao.OkTicker;
import wannabit.io.cosmostaion.dao.OkToken;
import wannabit.io.cosmostaion.dao.Price;
import wannabit.io.cosmostaion.model.kava.Cdp;
import wannabit.io.cosmostaion.model.kava.CollateralParam;
import wannabit.io.cosmostaion.model.kava.MarketPrice;
import wannabit.io.cosmostaion.model.type.BnbHistory;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.model.type.Input;
import wannabit.io.cosmostaion.model.type.Msg;
import wannabit.io.cosmostaion.model.type.Output;
import wannabit.io.cosmostaion.network.res.ResBnbSwapInfo;
import wannabit.io.cosmostaion.network.res.ResKavaSwapInfo;
import wannabit.io.cosmostaion.network.res.ResNodeInfo;
import wannabit.io.cosmostaion.network.res.ResOkHistory;
import wannabit.io.cosmostaion.network.res.ResProposal;
import wannabit.io.cosmostaion.network.res.ResTxInfo;

import static android.text.Spanned.SPAN_INCLUSIVE_INCLUSIVE;
import static wannabit.io.cosmostaion.base.BaseChain.AKASH_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.ALTHEA_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.ALTHEA_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.AXELAR_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.BAND_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.BITCANNA_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.BITSONG_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.BNB_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.BNB_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.CERTIK_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.CERTIK_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.COMDEX_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.COSMOS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.COSMOS_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.CRYPTO_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.DESMOS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.EMONEY_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.FETCHAI_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.GRABRIDGE_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.INJ_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.IOV_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.IOV_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.IRIS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.IRIS_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.JUNO_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.KAVA_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.KAVA_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.KI_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.LUM_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.MEDI_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.MEDI_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.OKEX_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.OK_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.OSMOSIS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.PERSIS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.REGEN_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.RIZON_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.RIZON_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.SECRET_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.SENTINEL_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.SIF_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.STARGAZE_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.UMEE_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.isGRPC;
import static wannabit.io.cosmostaion.base.BaseConstant.*;
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
        if (isGRPC(chain) && coin.isIbc()) {
            IbcToken ibcToken = baseData.getIbcToken(coin.getIbcHash());
            if (ibcToken.auth) {
                denomTv.setTextColor(c.getResources().getColor(R.color.colorWhite));
                denomTv.setText(ibcToken.display_denom.toUpperCase());
                amountTv.setText(getDpAmount2(c, new BigDecimal(coin.amount), ibcToken.decimal, ibcToken.decimal));

            } else {
                denomTv.setTextColor(c.getResources().getColor(R.color.colorWhite));
                denomTv.setText("Unknown");
                amountTv.setText(getDpAmount2(c, new BigDecimal(coin.amount), 6, 6));
            }

        } else if (chain.equals(COSMOS_MAIN)) {
            if (coin.denom.equals(TOKEN_ATOM)) {
                DpMainDenom(c, chain.getChain(), denomTv);
                amountTv.setText(getDpAmount2(c, new BigDecimal(coin.amount), 6, 6));
            } else {
                denomTv.setTextColor(c.getResources().getColor(R.color.colorWhite));
                amountTv.setText(getDpAmount2(c, new BigDecimal(coin.amount), 6, 6));
                denomTv.setText(coin.denom.toUpperCase());
            }
        } else if (chain.equals(IRIS_MAIN)) {
            if (coin.denom.equals(TOKEN_IRIS)) {
                DpMainDenom(c, chain.getChain(), denomTv);
            } else {
                denomTv.setTextColor(c.getResources().getColor(R.color.colorWhite));
                denomTv.setText(coin.denom.toUpperCase());
            }
            amountTv.setText(getDpAmount2(c, new BigDecimal(coin.amount), 6, 6));


        } else if (chain.equals(KAVA_MAIN)) {
            if (coin.denom.equals(TOKEN_KAVA)) {
                DpMainDenom(c, chain.getChain(), denomTv);
            } else if (coin.denom.equals(TOKEN_HARD)) {
                denomTv.setText(coin.denom.toUpperCase());
                denomTv.setTextColor(c.getResources().getColor(R.color.colorHard));
            } else if (coin.denom.equals(TOKEN_USDX)) {
                denomTv.setText(coin.denom.toUpperCase());
                denomTv.setTextColor(c.getResources().getColor(R.color.colorUsdx));
            } else if (coin.denom.equals(TOKEN_SWP)) {
                denomTv.setText(coin.denom.toUpperCase());
                denomTv.setTextColor(c.getResources().getColor(R.color.colorSwp));
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
            if (coin.denom.equals(TOKEN_BAND)) {
                DpMainDenom(c, chain.getChain(), denomTv);
            } else {
                denomTv.setTextColor(c.getResources().getColor(R.color.colorWhite));
                denomTv.setText(coin.denom.toUpperCase());
            }
            amountTv.setText(getDpAmount2(c, new BigDecimal(coin.amount), 6, 6));


        } else if (chain.equals(OKEX_MAIN) || chain.equals(OK_TEST)) {
            if (coin.denom.equals(TOKEN_OK)) {
                DpMainDenom(c, chain.getChain(), denomTv);
            } else {
                denomTv.setText(coin.denom.toUpperCase());
            }
            amountTv.setText(getDpAmount2(c, new BigDecimal(coin.amount), 0, 18));

        } else if (chain.equals(CERTIK_MAIN) || chain.equals(CERTIK_TEST)) {
            if (coin.denom.equals(TOKEN_CERTIK)) {
                DpMainDenom(c, chain.getChain(), denomTv);
            } else {
                denomTv.setTextColor(c.getResources().getColor(R.color.colorWhite));
                denomTv.setText(coin.denom.toUpperCase());
            }
            amountTv.setText(getDpAmount2(c, new BigDecimal(coin.amount), 6, 6));


        } else if (chain.equals(SECRET_MAIN)) {
            if (coin.denom.equals(TOKEN_SECRET)) {
                DpMainDenom(c, chain.getChain(), denomTv);
            } else {
                denomTv.setTextColor(c.getResources().getColor(R.color.colorWhite));
                denomTv.setText(coin.denom.toUpperCase());
            }
            amountTv.setText(getDpAmount2(c, new BigDecimal(coin.amount), 6, 6));


        } else if (chain.equals(AKASH_MAIN)) {
            if (coin.denom.equals(TOKEN_AKASH)) {
                DpMainDenom(c, chain.getChain(), denomTv);
            } else {
                denomTv.setTextColor(c.getResources().getColor(R.color.colorWhite));
                denomTv.setText(coin.denom.toUpperCase());
            }
            amountTv.setText(getDpAmount2(c, new BigDecimal(coin.amount), 6, 6));

        } else if (chain.equals(PERSIS_MAIN)) {
            if (coin.denom.equals(TOKEN_XPRT)) {
                DpMainDenom(c, chain.getChain(), denomTv);
            } else {
                denomTv.setTextColor(c.getResources().getColor(R.color.colorWhite));
                denomTv.setText(coin.denom.toUpperCase());
            }
            amountTv.setText(getDpAmount2(c, new BigDecimal(coin.amount), 6, 6));

        } else if (chain.equals(SENTINEL_MAIN)) {
            if (coin.denom.equals(TOKEN_DVPN)) {
                DpMainDenom(c, chain.getChain(), denomTv);
            } else {
                denomTv.setTextColor(c.getResources().getColor(R.color.colorWhite));
                denomTv.setText(coin.denom.toUpperCase());
            }
            amountTv.setText(getDpAmount2(c, new BigDecimal(coin.amount), 6, 6));

        } else if (chain.equals(FETCHAI_MAIN)) {
            if (coin.denom.equals(TOKEN_FET)) {
                DpMainDenom(c, chain.getChain(), denomTv);
            } else {
                denomTv.setTextColor(c.getResources().getColor(R.color.colorWhite));
                denomTv.setText(coin.denom.toUpperCase());
            }
            amountTv.setText(getDpAmount2(c, new BigDecimal(coin.amount), 18, 18));

        } else if (chain.equals(CRYPTO_MAIN)) {
            if (coin.denom.equals(TOKEN_CRO)) {
                DpMainDenom(c, chain.getChain(), denomTv);
            } else {
                denomTv.setTextColor(c.getResources().getColor(R.color.colorWhite));
                denomTv.setText(coin.denom.toUpperCase());
            }
            amountTv.setText(getDpAmount2(c, new BigDecimal(coin.amount), 8, 8));

        } else if (chain.equals(SIF_MAIN)) {
            int decimal = WUtil.getSifCoinDecimal(coin.denom);
            if (coin.denom.equals(TOKEN_SIF)) {
                DpMainDenom(c, chain.getChain(), denomTv);
            } else if (coin.denom.startsWith("c")) {
                denomTv.setText(coin.denom.substring(1).toUpperCase());
                denomTv.setTextColor(c.getResources().getColor(R.color.colorWhite));
            } else {
                denomTv.setText(coin.denom.toUpperCase());
                denomTv.setTextColor(c.getResources().getColor(R.color.colorWhite));
            }
            amountTv.setText(getDpAmount2(c, new BigDecimal(coin.amount), decimal, decimal));

        } else if (chain.equals(KI_MAIN)) {
            if (coin.denom.equals(TOKEN_KI)) {
                DpMainDenom(c, chain.getChain(), denomTv);
            } else {
                denomTv.setTextColor(c.getResources().getColor(R.color.colorWhite));
                denomTv.setText(coin.denom.toUpperCase());
            }
            amountTv.setText(getDpAmount2(c, new BigDecimal(coin.amount), 6, 6));

        } else if (chain.equals(OSMOSIS_MAIN)) {
            if (coin.denom.equals(TOKEN_OSMOSIS)) {
                DpMainDenom(c, chain.getChain(), denomTv);
                amountTv.setText(getDpAmount2(c, new BigDecimal(coin.amount), 6, 6));

            } else if (coin.denom.equals(TOKEN_ION)) {
                denomTv.setText("ION");
                denomTv.setTextColor(c.getResources().getColor(R.color.colorIon));
                amountTv.setText(getDpAmount2(c, new BigDecimal(coin.amount), 6, 6));

            } else if (coin.osmosisAmm()) {
                denomTv.setText(coin.osmosisAmmDpDenom());
                denomTv.setTextColor(c.getResources().getColor(R.color.colorWhite));
                amountTv.setText(getDpAmount2(c, new BigDecimal(coin.amount), 18, 18));

            } else {
                denomTv.setText(coin.denom.toUpperCase());
                denomTv.setTextColor(c.getResources().getColor(R.color.colorWhite));
                amountTv.setText(getDpAmount2(c, new BigDecimal(coin.amount), 6, 6));
            }

        } else if (chain.equals(MEDI_MAIN) || chain.equals(MEDI_TEST)) {
            if (coin.denom.equals(TOKEN_MEDI)) {
                DpMainDenom(c, chain.getChain(), denomTv);
            } else {
                denomTv.setTextColor(c.getResources().getColor(R.color.colorWhite));
                denomTv.setText(coin.denom.toUpperCase());
            }
            amountTv.setText(getDpAmount2(c, new BigDecimal(coin.amount), 6, 6));

        } else if (chain.equals(EMONEY_MAIN)) {
            if (coin.denom.equals(TOKEN_NGM)) {
                DpMainDenom(c, chain.getChain(), denomTv);
            } else {
                denomTv.setText(coin.denom.toUpperCase());
                denomTv.setTextColor(c.getResources().getColor(R.color.colorWhite));
            }
            amountTv.setText(getDpAmount2(c, new BigDecimal(coin.amount), 6, 6));

        } else if (chain.equals(RIZON_MAIN) || chain.equals(RIZON_TEST)) {
            if (coin.denom.equals(TOKEN_RIZON)) {
                DpMainDenom(c, chain.getChain(), denomTv);
            } else {
                denomTv.setTextColor(c.getResources().getColor(R.color.colorWhite));
                denomTv.setText(coin.denom.toUpperCase());
            }
            amountTv.setText(getDpAmount2(c, new BigDecimal(coin.amount), 6, 6));

        } else if (chain.equals(JUNO_MAIN)) {
            if (coin.denom.equals(TOKEN_JUNO)) {
                DpMainDenom(c, chain.getChain(), denomTv);
            } else {
                denomTv.setTextColor(c.getResources().getColor(R.color.colorWhite));
                denomTv.setText(coin.denom.toUpperCase());
            }
            amountTv.setText(getDpAmount2(c, new BigDecimal(coin.amount), 6, 6));

        } else if (chain.equals(REGEN_MAIN)) {
            if (coin.denom.equals(TOKEN_REGEN)) {
                DpMainDenom(c, chain.getChain(), denomTv);
            } else {
                denomTv.setTextColor(c.getResources().getColor(R.color.colorWhite));
                denomTv.setText(coin.denom.toUpperCase());
            }
            amountTv.setText(getDpAmount2(c, new BigDecimal(coin.amount), 6, 6));

        } else if (chain.equals(BITCANNA_MAIN)) {
            if (coin.denom.equals(TOKEN_BITCANNA)) {
                DpMainDenom(c, chain.getChain(), denomTv);
            } else {
                denomTv.setTextColor(c.getResources().getColor(R.color.colorWhite));
                denomTv.setText(coin.denom.toUpperCase());
            }
            amountTv.setText(getDpAmount2(c, new BigDecimal(coin.amount), 6, 6));

        } else if (chain.equals(ALTHEA_MAIN) || chain.equals(ALTHEA_TEST)) {
            if (coin.denom.equals(TOKEN_ALTHEA)) {
                DpMainDenom(c, chain.getChain(), denomTv);
            } else {
                denomTv.setTextColor(c.getResources().getColor(R.color.colorWhite));
                denomTv.setText(coin.denom.toUpperCase());
            }
            amountTv.setText(getDpAmount2(c, new BigDecimal(coin.amount), 6, 6));

        } else if (chain.equals(STARGAZE_MAIN)) {
            if (coin.denom.equals(TOKEN_STARGAZE)) {
                DpMainDenom(c, chain.getChain(), denomTv);
            } else {
                denomTv.setTextColor(c.getResources().getColor(R.color.colorWhite));
                denomTv.setText(coin.denom.toUpperCase());
            }
            amountTv.setText(getDpAmount2(c, new BigDecimal(coin.amount), 6, 6));

        } else if (chain.equals(GRABRIDGE_MAIN)) {
            if (coin.denom.equals(TOKEN_GRABRIDGE)) {
                DpMainDenom(c, chain.getChain(), denomTv);
            } else {
                denomTv.setTextColor(c.getResources().getColor(R.color.colorWhite));
                denomTv.setText(coin.denom.toUpperCase());
            }
            amountTv.setText(getDpAmount2(c, new BigDecimal(coin.amount), 6, 6));

        } else if (chain.equals(COMDEX_MAIN)) {
            if (coin.denom.equals(TOKEN_COMDEX)) {
                DpMainDenom(c, chain.getChain(), denomTv);
            } else {
                denomTv.setTextColor(c.getResources().getColor(R.color.colorWhite));
                denomTv.setText(coin.denom.toUpperCase());
            }
            amountTv.setText(getDpAmount2(c, new BigDecimal(coin.amount), 6, 6));

        } else if (chain.equals(INJ_MAIN)) {
            if (coin.denom.equals(TOKEN_INJ)) {
                DpMainDenom(c, chain.getChain(), denomTv);
            } else {
                denomTv.setTextColor(c.getResources().getColor(R.color.colorWhite));
                denomTv.setText(coin.denom.toUpperCase());
            }
            amountTv.setText(getDpAmount2(c, new BigDecimal(coin.amount), 18, 18));

        } else if (chain.equals(BITSONG_MAIN)) {
            if (coin.denom.equals(TOKEN_BITSONG)) {
                DpMainDenom(c, chain.getChain(), denomTv);
            } else {
                denomTv.setTextColor(c.getResources().getColor(R.color.colorWhite));
                denomTv.setText(coin.denom.toUpperCase());
            }
            amountTv.setText(getDpAmount2(c, new BigDecimal(coin.amount), 6, 6));

        } else if (chain.equals(DESMOS_MAIN)) {
            if (coin.denom.equals(TOKEN_DESMOS)) {
                DpMainDenom(c, chain.getChain(), denomTv);
            } else {
                denomTv.setTextColor(c.getResources().getColor(R.color.colorWhite));
                denomTv.setText(coin.denom.toUpperCase());
            }
            amountTv.setText(getDpAmount2(c, new BigDecimal(coin.amount), 6, 6));

        } else if (chain.equals(LUM_MAIN)) {
            if (coin.denom.equals(TOKEN_LUM)) {
                DpMainDenom(c, chain.getChain(), denomTv);
            } else {
                denomTv.setTextColor(c.getResources().getColor(R.color.colorWhite));
                denomTv.setText(coin.denom.toUpperCase());
            }
            amountTv.setText(getDpAmount2(c, new BigDecimal(coin.amount), 6, 6));

        }

        else if (chain.equals(COSMOS_TEST)) {
            if (coin.denom.equals(TOKEN_COSMOS_TEST)) {
                DpMainDenom(c, chain.getChain(), denomTv);
            } else {
                denomTv.setTextColor(c.getResources().getColor(R.color.colorWhite));
                denomTv.setText(coin.denom.toUpperCase());
            }
            amountTv.setText(getDpAmount2(c, new BigDecimal(coin.amount), 6, 6));

        } else if (chain.equals(IRIS_TEST)) {
            if (coin.denom.equals(TOKEN_IRIS_TEST)) {
                DpMainDenom(c, chain.getChain(), denomTv);
            } else {
                denomTv.setTextColor(c.getResources().getColor(R.color.colorWhite));
                denomTv.setText(coin.denom.toUpperCase());
            }
            amountTv.setText(getDpAmount2(c, new BigDecimal(coin.amount), 6, 6));

        } else if (chain.equals(UMEE_TEST)) {
            if (coin.denom.equals(TOKEN_UMEE)) {
                DpMainDenom(c, chain.getChain(), denomTv);
            } else {
                denomTv.setTextColor(c.getResources().getColor(R.color.colorWhite));
                denomTv.setText(coin.denom.toUpperCase());
            }
            amountTv.setText(getDpAmount2(c, new BigDecimal(coin.amount), 6, 6));

        } else if (chain.equals(AXELAR_TEST)) {
            if (coin.denom.equals(TOKEN_AXELAR)) {
                DpMainDenom(c, chain.getChain(), denomTv);
            } else {
                denomTv.setTextColor(c.getResources().getColor(R.color.colorWhite));
                denomTv.setText(coin.denom.toUpperCase());
            }
            amountTv.setText(getDpAmount2(c, new BigDecimal(coin.amount), 6, 6));
        }
    }

    public static void showCoinDp(Context c, BaseData baseData, String symbol, String amount, TextView denomTv, TextView amountTv, BaseChain chain) {
        if (isGRPC(chain) && symbol.startsWith("ibc")) {
            IbcToken ibcToken = baseData.getIbcToken(symbol.replaceAll("ibc/", ""));
            if (ibcToken.auth) {
                denomTv.setTextColor(c.getResources().getColor(R.color.colorWhite));
                denomTv.setText(ibcToken.display_denom.toUpperCase());
                amountTv.setText(getDpAmount2(c, new BigDecimal(amount), ibcToken.decimal, ibcToken.decimal));

            } else {
                denomTv.setTextColor(c.getResources().getColor(R.color.colorWhite));
                denomTv.setText("Unknown");
                amountTv.setText(getDpAmount2(c, new BigDecimal(amount), 6, 6));
            }

        } else if (chain.equals(COSMOS_MAIN)) {
            if (symbol.equals(TOKEN_ATOM)) {
                DpMainDenom(c, chain.getChain(), denomTv);
            } else {
                denomTv.setTextColor(c.getResources().getColor(R.color.colorWhite));
                denomTv.setText(symbol.toUpperCase());
            }
            amountTv.setText(getDpAmount2(c, new BigDecimal(amount), 6, 6));

        } else if (chain.equals(IRIS_MAIN)) {
            if (symbol.equals(TOKEN_IRIS)) {
                DpMainDenom(c, chain.getChain(), denomTv);
            } else {
                denomTv.setTextColor(c.getResources().getColor(R.color.colorWhite));
                denomTv.setText(symbol.toUpperCase());
            }
            amountTv.setText(getDpAmount2(c, new BigDecimal(amount), 6, 6));

        } else if (chain.equals(KAVA_MAIN)) {
            if (symbol.equals(TOKEN_KAVA)) {
                DpMainDenom(c, chain.getChain(), denomTv);
            } else if (symbol.equals(TOKEN_HARD)) {
                denomTv.setText(symbol.toUpperCase());
                denomTv.setTextColor(c.getResources().getColor(R.color.colorHard));
            } else if (symbol.equals(TOKEN_USDX)) {
                denomTv.setText(symbol.toUpperCase());
                denomTv.setTextColor(c.getResources().getColor(R.color.colorUsdx));
            } else if (symbol.equals(TOKEN_SWP)) {
                denomTv.setText(symbol.toUpperCase());
                denomTv.setTextColor(c.getResources().getColor(R.color.colorSwp));
            } else {
                denomTv.setText(symbol.toUpperCase());
                denomTv.setTextColor(c.getResources().getColor(R.color.colorWhite));
            }
            if (amountTv != null) amountTv.setText(getDpAmount2(c, new BigDecimal(amount), WUtil.getKavaCoinDecimal(symbol), WUtil.getKavaCoinDecimal(symbol)));

        } else if (chain.equals(IOV_MAIN) || chain.equals(IOV_TEST)) {
            if (symbol.equals(TOKEN_IOV) || symbol.equals(TOKEN_IOV_TEST)) {
                DpMainDenom(c, chain.getChain(), denomTv);

            } else {
                denomTv.setTextColor(c.getResources().getColor(R.color.colorWhite));
                denomTv.setText(symbol.toUpperCase());
            }
            amountTv.setText(getDpAmount2(c, new BigDecimal(amount), 6, 6));

        } else if (chain.equals(BNB_MAIN) || chain.equals(BNB_TEST)) {
            if (symbol.equals(TOKEN_BNB)) {
                DpMainDenom(c, chain.getChain(), denomTv);

            } else {
                denomTv.setText(symbol.toUpperCase());
                denomTv.setTextColor(c.getResources().getColor(R.color.colorWhite));
            }
            amountTv.setText(getDpAmount2(c, new BigDecimal(amount), 0, 8));

        } else if (chain.equals(BAND_MAIN)) {
            DpMainDenom(c, chain.getChain(), denomTv);
            amountTv.setText(getDpAmount2(c, new BigDecimal(amount), 6, 6));

        } else if (chain.equals(OKEX_MAIN) || chain.equals(OK_TEST)) {
            if (symbol.equals(TOKEN_OK)) {
                DpMainDenom(c, chain.getChain(), denomTv);
            } else {
                denomTv.setText(symbol.toUpperCase());
                denomTv.setTextColor(c.getResources().getColor(R.color.colorWhite));
            }
            amountTv.setText(getDpAmount2(c, new BigDecimal(amount), 0, 18));

        } else if (chain.equals(CERTIK_MAIN) || chain.equals(CERTIK_TEST)) {
            if (symbol.equals(TOKEN_CERTIK)) {
                DpMainDenom(c, chain.getChain(), denomTv);
            } else {
                denomTv.setText(symbol.toUpperCase());
                denomTv.setTextColor(c.getResources().getColor(R.color.colorWhite));
            }
            amountTv.setText(getDpAmount2(c, new BigDecimal(amount), 6, 6));

        } else if (chain.equals(SECRET_MAIN)) {
            if (symbol.equals(TOKEN_SECRET)) {
                DpMainDenom(c, chain.getChain(), denomTv);
            } else {
                denomTv.setText(symbol.toUpperCase());
                denomTv.setTextColor(c.getResources().getColor(R.color.colorWhite));
            }
            amountTv.setText(getDpAmount2(c, new BigDecimal(amount), 6, 6));

        } else if (chain.equals(AKASH_MAIN)) {
            if (symbol.equals(TOKEN_AKASH)) {
                DpMainDenom(c, chain.getChain(), denomTv);
            } else {
                denomTv.setText(symbol.toUpperCase());
                denomTv.setTextColor(c.getResources().getColor(R.color.colorWhite));
            }
            amountTv.setText(getDpAmount2(c, new BigDecimal(amount), 6, 6));

        } else if (chain.equals(PERSIS_MAIN)) {
            if (symbol.equals(TOKEN_XPRT)) {
                DpMainDenom(c, chain.getChain(), denomTv);
            } else {
                denomTv.setText(symbol.toUpperCase());
                denomTv.setTextColor(c.getResources().getColor(R.color.colorWhite));
            }
            amountTv.setText(getDpAmount2(c, new BigDecimal(amount), 6, 6));

        } else if (chain.equals(SENTINEL_MAIN)) {
            if (symbol.equals(TOKEN_DVPN)) {
                DpMainDenom(c, chain.getChain(), denomTv);
            } else {
                denomTv.setText(symbol.toUpperCase());
                denomTv.setTextColor(c.getResources().getColor(R.color.colorWhite));
            }
            amountTv.setText(getDpAmount2(c, new BigDecimal(amount), 6, 6));

        } else if (chain.equals(FETCHAI_MAIN)) {
            if (symbol.equals(TOKEN_FET)) {
                DpMainDenom(c, chain.getChain(), denomTv);
            } else {
                denomTv.setText(symbol.toUpperCase());
                denomTv.setTextColor(c.getResources().getColor(R.color.colorWhite));
            }
            amountTv.setText(getDpAmount2(c, new BigDecimal(amount), 18, 18));

        } else if (chain.equals(CRYPTO_MAIN)) {
            if (symbol.equals(TOKEN_CRO)) {
                DpMainDenom(c, chain.getChain(), denomTv);
            } else {
                denomTv.setText(symbol.toUpperCase());
                denomTv.setTextColor(c.getResources().getColor(R.color.colorWhite));
            }
            amountTv.setText(getDpAmount2(c, new BigDecimal(amount), 8, 8));

        } else if (chain.equals(SIF_MAIN)) {
            int decimal = WUtil.getSifCoinDecimal(symbol);
            if (symbol.equals(TOKEN_SIF)) {
                DpMainDenom(c, chain.getChain(), denomTv);
            } else if (symbol.startsWith("c")) {
                denomTv.setText(symbol.substring(1).toUpperCase());
                denomTv.setTextColor(c.getResources().getColor(R.color.colorWhite));
            } else {
                denomTv.setText(symbol.toUpperCase());
                denomTv.setTextColor(c.getResources().getColor(R.color.colorWhite));
            }
            amountTv.setText(getDpAmount2(c, new BigDecimal(amount), decimal, decimal));

        } else if (chain.equals(KI_MAIN)) {
            if (symbol.equals(TOKEN_KI)) {
                DpMainDenom(c, chain.getChain(), denomTv);
            } else {
                denomTv.setText(symbol.toUpperCase());
                denomTv.setTextColor(c.getResources().getColor(R.color.colorWhite));
            }
            amountTv.setText(getDpAmount2(c, new BigDecimal(amount), 6, 6));

        } else if (chain.equals(OSMOSIS_MAIN)) {
            if (symbol.equals(TOKEN_OSMOSIS)) {
                DpMainDenom(c, chain.getChain(), denomTv);
                amountTv.setText(getDpAmount2(c, new BigDecimal(amount), 6, 6));

            } else if (symbol.equals(TOKEN_ION)) {
                denomTv.setText("ION");
                denomTv.setTextColor(c.getResources().getColor(R.color.colorIon));
                amountTv.setText(getDpAmount2(c, new BigDecimal(amount), 6, 6));

            } else if (symbol.startsWith("gamm/pool/")) {
                String[] value = symbol.split("/");
                denomTv.setText("GAMM-" + value[value.length - 1]);
                denomTv.setTextColor(c.getResources().getColor(R.color.colorWhite));
                amountTv.setText(getDpAmount2(c, new BigDecimal(amount), 18, 18));

            } else {
                denomTv.setText(symbol.toUpperCase());
                denomTv.setTextColor(c.getResources().getColor(R.color.colorWhite));
                amountTv.setText(getDpAmount2(c, new BigDecimal(amount), 6, 6));
            }

        } else if (chain.equals(MEDI_MAIN) || chain.equals(MEDI_TEST)) {
            if (symbol.equals(TOKEN_MEDI)) {
                DpMainDenom(c, chain.getChain(), denomTv);
            } else {
                denomTv.setText(symbol.toUpperCase());
                denomTv.setTextColor(c.getResources().getColor(R.color.colorWhite));
            }
            amountTv.setText(getDpAmount2(c, new BigDecimal(amount), 6, 6));

        } else if (chain.equals(EMONEY_MAIN)) {
            if (symbol.equalsIgnoreCase(TOKEN_NGM)) {
                DpMainDenom(c, chain.getChain(), denomTv);
            } else {
                denomTv.setText(symbol.toUpperCase());
                denomTv.setTextColor(c.getResources().getColor(R.color.colorWhite));
            }
            amountTv.setText(getDpAmount2(c, new BigDecimal(amount), 6, 6));

        } else if (chain.equals(RIZON_MAIN) || chain.equals(RIZON_TEST)) {
            if (symbol.equals(TOKEN_RIZON)) {
                DpMainDenom(c, chain.getChain(), denomTv);
            } else {
                denomTv.setText(symbol.toUpperCase());
                denomTv.setTextColor(c.getResources().getColor(R.color.colorWhite));
            }
            amountTv.setText(getDpAmount2(c, new BigDecimal(amount), 6, 6));

        } else if (chain.equals(JUNO_MAIN)) {
            if (symbol.equals(TOKEN_JUNO)) {
                DpMainDenom(c, chain.getChain(), denomTv);
            } else {
                denomTv.setText(symbol.toUpperCase());
                denomTv.setTextColor(c.getResources().getColor(R.color.colorWhite));
            }
            amountTv.setText(getDpAmount2(c, new BigDecimal(amount), 6, 6));

        } else if (chain.equals(REGEN_MAIN)) {
            if (symbol.equals(TOKEN_REGEN)) {
                DpMainDenom(c, chain.getChain(), denomTv);
            } else {
                denomTv.setText(symbol.toUpperCase());
                denomTv.setTextColor(c.getResources().getColor(R.color.colorWhite));
            }
            amountTv.setText(getDpAmount2(c, new BigDecimal(amount), 6, 6));

        } else if (chain.equals(BITCANNA_MAIN)) {
            if (symbol.equals(TOKEN_BITCANNA)) {
                DpMainDenom(c, chain.getChain(), denomTv);
            } else {
                denomTv.setText(symbol.toUpperCase());
                denomTv.setTextColor(c.getResources().getColor(R.color.colorWhite));
            }
            amountTv.setText(getDpAmount2(c, new BigDecimal(amount), 6, 6));

        } else if (chain.equals(ALTHEA_MAIN) || chain.equals(ALTHEA_TEST)) {
            if (symbol.equals(TOKEN_ALTHEA)) {
                DpMainDenom(c, chain.getChain(), denomTv);
            } else {
                denomTv.setText(symbol.toUpperCase());
                denomTv.setTextColor(c.getResources().getColor(R.color.colorWhite));
            }
            amountTv.setText(getDpAmount2(c, new BigDecimal(amount), 6, 6));

        } else if (chain.equals(STARGAZE_MAIN)) {
            if (symbol.equals(TOKEN_STARGAZE)) {
                DpMainDenom(c, chain.getChain(), denomTv);
            } else {
                denomTv.setText(symbol.toUpperCase());
                denomTv.setTextColor(c.getResources().getColor(R.color.colorWhite));
            }
            amountTv.setText(getDpAmount2(c, new BigDecimal(amount), 6, 6));

        } else if (chain.equals(GRABRIDGE_MAIN)) {
            if (symbol.equals(TOKEN_GRABRIDGE)) {
                DpMainDenom(c, chain.getChain(), denomTv);
            } else {
                denomTv.setText(symbol.toUpperCase());
                denomTv.setTextColor(c.getResources().getColor(R.color.colorWhite));
            }
            amountTv.setText(getDpAmount2(c, new BigDecimal(amount), 6, 6));

        } else if (chain.equals(COMDEX_MAIN)) {
            if (symbol.equals(TOKEN_COMDEX)) {
                DpMainDenom(c, chain.getChain(), denomTv);
            } else {
                denomTv.setText(symbol.toUpperCase());
                denomTv.setTextColor(c.getResources().getColor(R.color.colorWhite));
            }
            amountTv.setText(getDpAmount2(c, new BigDecimal(amount), 6, 6));

        } else if (chain.equals(INJ_MAIN)) {
            if (symbol.equals(TOKEN_INJ)) {
                DpMainDenom(c, chain.getChain(), denomTv);
            } else {
                denomTv.setText(symbol.toUpperCase());
                denomTv.setTextColor(c.getResources().getColor(R.color.colorWhite));
            }
            amountTv.setText(getDpAmount2(c, new BigDecimal(amount), 18, 18));

        } else if (chain.equals(BITSONG_MAIN)) {
            if (symbol.equals(TOKEN_BITSONG)) {
                DpMainDenom(c, chain.getChain(), denomTv);
            } else {
                denomTv.setText(symbol.toUpperCase());
                denomTv.setTextColor(c.getResources().getColor(R.color.colorWhite));
            }
            amountTv.setText(getDpAmount2(c, new BigDecimal(amount), 6, 6));

        } else if (chain.equals(DESMOS_MAIN)) {
            if (symbol.equals(TOKEN_DESMOS)) {
                DpMainDenom(c, chain.getChain(), denomTv);
            } else {
                denomTv.setText(symbol.toUpperCase());
                denomTv.setTextColor(c.getResources().getColor(R.color.colorWhite));
            }
            amountTv.setText(getDpAmount2(c, new BigDecimal(amount), 6, 6));

        } else if (chain.equals(LUM_MAIN)) {
            if (symbol.equals(TOKEN_LUM)) {
                DpMainDenom(c, chain.getChain(), denomTv);
            } else {
                denomTv.setText(symbol.toUpperCase());
                denomTv.setTextColor(c.getResources().getColor(R.color.colorWhite));
            }
            amountTv.setText(getDpAmount2(c, new BigDecimal(amount), 6, 6));

        }

        else if (chain.equals(COSMOS_TEST)) {
            if (symbol.equals(TOKEN_COSMOS_TEST)) {
                DpMainDenom(c, chain.getChain(), denomTv);
            } else {
                denomTv.setText(symbol.toUpperCase());
                denomTv.setText(symbol.toUpperCase());
            }
            amountTv.setText(getDpAmount2(c, new BigDecimal(amount), 6, 6));
        } else if (chain.equals(IRIS_TEST)) {
            if (symbol.equals(TOKEN_IRIS_TEST)) {
                DpMainDenom(c, chain.getChain(), denomTv);
            } else {
                denomTv.setText(symbol.toUpperCase());
                denomTv.setText(symbol.toUpperCase());
            }
            amountTv.setText(getDpAmount2(c, new BigDecimal(amount), 6, 6));

        } else if (chain.equals(UMEE_TEST)) {
            if (symbol.equals(TOKEN_UMEE)) {
                DpMainDenom(c, chain.getChain(), denomTv);
            } else {
                denomTv.setText(symbol.toUpperCase());
                denomTv.setText(symbol.toUpperCase());
            }
            amountTv.setText(getDpAmount2(c, new BigDecimal(amount), 6, 6));

        } else if (chain.equals(AXELAR_TEST)) {
            if (symbol.equals(TOKEN_AXELAR)) {
                DpMainDenom(c, chain.getChain(), denomTv);
            } else {
                denomTv.setText(symbol.toUpperCase());
                denomTv.setText(symbol.toUpperCase());
            }
            amountTv.setText(getDpAmount2(c, new BigDecimal(amount), 6, 6));
        }
    }

    public static void showChainDp(Context c, BaseChain baseChain, CardView cardName, CardView cardAlarm, CardView cardBody, CardView cardRewardAddress) {
        if (baseChain.equals(OKEX_MAIN) || baseChain.equals(KAVA_MAIN) || baseChain.equals(BNB_MAIN) || baseChain.equals(FETCHAI_MAIN) ||
                baseChain.equals(OK_TEST) || baseChain.equals(BNB_TEST)) {
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

        } else if (baseChain.equals(BNB_TEST)) {
            cardName.setCardBackgroundColor(c.getColor(R.color.colorTransBg));
            cardAlarm.setCardBackgroundColor(c.getColor(R.color.colorTransBg));
            cardAlarm.setVisibility(View.GONE);
            cardBody.setCardBackgroundColor(c.getColor(R.color.colorTransBg));
            cardRewardAddress.setCardBackgroundColor(c.getColor(R.color.colorTransBg));
            cardRewardAddress.setVisibility(View.GONE);

        } else if (baseChain.equals(IOV_TEST)) {
            cardName.setCardBackgroundColor(c.getColor(R.color.colorTransBg));
            cardAlarm.setCardBackgroundColor(c.getColor(R.color.colorTransBg));
            cardAlarm.setVisibility(View.GONE);
            cardBody.setCardBackgroundColor(c.getColor(R.color.colorTransBg));
            cardRewardAddress.setCardBackgroundColor(c.getColor(R.color.colorTransBg));
            cardRewardAddress.setVisibility(View.VISIBLE);

        } else if (baseChain.equals(OK_TEST)) {
            cardName.setCardBackgroundColor(c.getColor(R.color.colorTransBg));
            cardAlarm.setCardBackgroundColor(c.getColor(R.color.colorTransBg));
            cardAlarm.setVisibility(View.GONE);
            cardBody.setCardBackgroundColor(c.getColor(R.color.colorTransBg));
            cardRewardAddress.setCardBackgroundColor(c.getColor(R.color.colorTransBg));
            cardRewardAddress.setVisibility(View.GONE);

        } else if (baseChain.equals(CERTIK_TEST)) {
            cardName.setCardBackgroundColor(c.getColor(R.color.colorTransBg));
            cardAlarm.setCardBackgroundColor(c.getColor(R.color.colorTransBg));
            cardAlarm.setVisibility(View.GONE);
            cardBody.setCardBackgroundColor(c.getColor(R.color.colorTransBg));
            cardRewardAddress.setCardBackgroundColor(c.getColor(R.color.colorTransBg));
            cardRewardAddress.setVisibility(View.VISIBLE);

        } else if (baseChain.equals(RIZON_TEST)) {
            cardName.setCardBackgroundColor(c.getColor(R.color.colorTransBg));
            cardAlarm.setCardBackgroundColor(c.getColor(R.color.colorTransBg));
            cardAlarm.setVisibility(View.GONE);
            cardBody.setCardBackgroundColor(c.getColor(R.color.colorTransBg));
            cardRewardAddress.setCardBackgroundColor(c.getColor(R.color.colorTransBg));
            cardRewardAddress.setVisibility(View.VISIBLE);

        } else if (baseChain.equals(MEDI_TEST)) {
            cardName.setCardBackgroundColor(c.getColor(R.color.colorTransBg));
            cardAlarm.setCardBackgroundColor(c.getColor(R.color.colorTransBg));
            cardAlarm.setVisibility(View.GONE);
            cardBody.setCardBackgroundColor(c.getColor(R.color.colorTransBg));
            cardRewardAddress.setCardBackgroundColor(c.getColor(R.color.colorTransBg));
            cardRewardAddress.setVisibility(View.VISIBLE);

        } else if (baseChain.equals(ALTHEA_TEST)) {
            cardName.setCardBackgroundColor(c.getColor(R.color.colorTransBg));
            cardAlarm.setCardBackgroundColor(c.getColor(R.color.colorTransBg));
            cardAlarm.setVisibility(View.GONE);
            cardBody.setCardBackgroundColor(c.getColor(R.color.colorTransBg));
            cardRewardAddress.setCardBackgroundColor(c.getColor(R.color.colorTransBg));
            cardRewardAddress.setVisibility(View.VISIBLE);

        } else if (baseChain.equals(UMEE_TEST)) {
            cardName.setCardBackgroundColor(c.getColor(R.color.colorTransBg));
            cardAlarm.setCardBackgroundColor(c.getColor(R.color.colorTransBg));
            cardAlarm.setVisibility(View.GONE);
            cardBody.setCardBackgroundColor(c.getColor(R.color.colorTransBg));
            cardRewardAddress.setCardBackgroundColor(c.getColor(R.color.colorTransBg));
            cardRewardAddress.setVisibility(View.VISIBLE);

        } else if (baseChain.equals(AXELAR_TEST)) {
            cardName.setCardBackgroundColor(c.getColor(R.color.colorTransBg));
            cardAlarm.setCardBackgroundColor(c.getColor(R.color.colorTransBg));
            cardAlarm.setVisibility(View.GONE);
            cardBody.setCardBackgroundColor(c.getColor(R.color.colorTransBg));
            cardRewardAddress.setCardBackgroundColor(c.getColor(R.color.colorTransBg));
            cardRewardAddress.setVisibility(View.VISIBLE);
        }
    }

    public static void getChainImg(Context c, BaseChain baseChain, ImageView chainImg) {
        if (baseChain != null) {
            if (baseChain.equals(COSMOS_MAIN)) {
                chainImg.setImageDrawable(c.getDrawable(R.drawable.cosmos_wh_main));
            } else if (baseChain.equals(IRIS_MAIN)) {
                chainImg.setImageDrawable(c.getDrawable(R.drawable.iris_wh));
            } else if (baseChain.equals(BNB_MAIN)) {
                chainImg.setImageDrawable(c.getDrawable(R.drawable.binance_ch_img));
            } else if (baseChain.equals(KAVA_MAIN)) {
                chainImg.setImageDrawable(c.getDrawable(R.drawable.kava_img));
            } else if (baseChain.equals(IOV_MAIN)) {
                chainImg.setImageDrawable(c.getDrawable(R.drawable.chain_starname));
            } else if (baseChain.equals(BAND_MAIN)) {
                chainImg.setImageDrawable(c.getDrawable(R.drawable.band_chain_img));
            } else if (baseChain.equals(CERTIK_MAIN)) {
                chainImg.setImageDrawable(c.getDrawable(R.drawable.certik_chain_img));
            } else if (baseChain.equals(SECRET_MAIN)) {
                chainImg.setImageDrawable(c.getDrawable(R.drawable.chainsecret));
            } else if (baseChain.equals(AKASH_MAIN)) {
                chainImg.setImageDrawable(c.getDrawable(R.drawable.akash_chain_img));
            } else if (baseChain.equals(OKEX_MAIN)) {
                chainImg.setImageDrawable(c.getDrawable(R.drawable.okex_chain_img));
            } else if (baseChain.equals(PERSIS_MAIN)) {
                chainImg.setImageDrawable(c.getDrawable(R.drawable.chainpersistence));
            } else if (baseChain.equals(SENTINEL_MAIN)) {
                chainImg.setImageDrawable(c.getDrawable(R.drawable.chainsentinel));
            } else if (baseChain.equals(FETCHAI_MAIN)) {
                chainImg.setImageDrawable(c.getDrawable(R.drawable.chainfetchai));
            } else if (baseChain.equals(CRYPTO_MAIN)) {
                chainImg.setImageDrawable(c.getDrawable(R.drawable.chaincrypto));
            } else if (baseChain.equals(SIF_MAIN)) {
                chainImg.setImageDrawable(c.getDrawable(R.drawable.chainsifchain));
            } else if (baseChain.equals(KI_MAIN)) {
                chainImg.setImageDrawable(c.getDrawable(R.drawable.chain_kifoundation));
            } else if (baseChain.equals(OSMOSIS_MAIN)) {
                chainImg.setImageDrawable(c.getDrawable(R.drawable.chain_osmosis));
            } else if (baseChain.equals(MEDI_MAIN)) {
                chainImg.setImageDrawable(c.getDrawable(R.drawable.chainmedibloc));
            } else if (baseChain.equals(EMONEY_MAIN)) {
                chainImg.setImageDrawable(c.getDrawable(R.drawable.chain_emoney));
            } else if (baseChain.equals(RIZON_MAIN)) {
                chainImg.setImageDrawable(c.getDrawable(R.drawable.chain_rizon));
            } else if (baseChain.equals(JUNO_MAIN)) {
                chainImg.setImageDrawable(c.getDrawable(R.drawable.chain_juno));
            } else if (baseChain.equals(REGEN_MAIN)) {
                chainImg.setImageDrawable(c.getDrawable(R.drawable.chain_regen));
            } else if (baseChain.equals(BITCANNA_MAIN)) {
                chainImg.setImageDrawable(c.getDrawable(R.drawable.chain_bitcanna));
            } else if (baseChain.equals(ALTHEA_MAIN)) {
                chainImg.setImageDrawable(c.getDrawable(R.drawable.chain_althea));
            } else if (baseChain.equals(STARGAZE_MAIN)) {
                chainImg.setImageDrawable(c.getDrawable(R.drawable.chain_stargaze));
            } else if (baseChain.equals(GRABRIDGE_MAIN)) {
                chainImg.setImageDrawable(c.getDrawable(R.drawable.chain_gravitybridge));
            } else if (baseChain.equals(COMDEX_MAIN)) {
                chainImg.setImageDrawable(c.getDrawable(R.drawable.chain_comdex));
            } else if (baseChain.equals(INJ_MAIN)) {
                chainImg.setImageDrawable(c.getDrawable(R.drawable.chain_injective));
            } else if (baseChain.equals(BITSONG_MAIN)) {
                chainImg.setImageDrawable(c.getDrawable(R.drawable.chain_bitsong));
            } else if (baseChain.equals(DESMOS_MAIN)) {
                chainImg.setImageDrawable(c.getDrawable(R.drawable.chain_desmos));
            } else if (baseChain.equals(LUM_MAIN)) {
                chainImg.setImageDrawable(c.getDrawable(R.drawable.chain_lumnetwork));

            }

            else if (baseChain.equals(COSMOS_TEST)) {
                chainImg.setImageDrawable(c.getDrawable(R.drawable.chain_test_cosmos));
            } else if (baseChain.equals(IRIS_TEST)) {
                chainImg.setImageDrawable(c.getDrawable(R.drawable.chain_test_iris));
            } else if (baseChain.equals(BNB_TEST)) {
                chainImg.setImageDrawable(c.getDrawable(R.drawable.binancetestnet));
            } else if (baseChain.equals(IOV_TEST)) {
                chainImg.setImageDrawable(c.getDrawable(R.drawable.iov_testnet_img));
            } else if (baseChain.equals(OK_TEST)) {
                chainImg.setImageDrawable(c.getDrawable(R.drawable.okex_testnet_img));
            } else if (baseChain.equals(CERTIK_TEST)) {
                chainImg.setImageDrawable(c.getDrawable(R.drawable.certik_testnet_img));
            } else if (baseChain.equals(MEDI_TEST)) {
                chainImg.setImageDrawable(c.getDrawable(R.drawable.testnet_medibloc));
            } else if (baseChain.equals(ALTHEA_TEST)) {
                chainImg.setImageDrawable(c.getDrawable(R.drawable.testnet_althea));
            } else if (baseChain.equals(UMEE_TEST)) {
                chainImg.setImageDrawable(c.getDrawable(R.drawable.testnet_umee));
            } else if (baseChain.equals(AXELAR_TEST)) {
                chainImg.setImageDrawable(c.getDrawable(R.drawable.testnet_axelar));
            } else if (baseChain.equals(RIZON_TEST)) {
                chainImg.setImageDrawable(c.getDrawable(R.drawable.testnet_rizon));
            }
        } else {
            chainImg.setImageDrawable(c.getDrawable(R.drawable.default_chain_img));
        }
    }

    public static void getChainTitle(Context c, BaseChain baseChain, TextView chainName) {
        if (baseChain != null) {
            if (baseChain.equals(COSMOS_MAIN)) {
                chainName.setText(c.getString(R.string.str_cosmos_hub));
            } else if (baseChain.equals(IRIS_MAIN)) {
                chainName.setText(c.getString(R.string.str_iris_net));
            } else if (baseChain.equals(BNB_MAIN)) {
                chainName.setText(c.getString(R.string.str_binance_net));
            } else if (baseChain.equals(KAVA_MAIN)) {
                chainName.setText(c.getString(R.string.str_kava_net));
            } else if (baseChain.equals(IOV_MAIN)) {
                chainName.setText(c.getString(R.string.str_iov_net));
            } else if (baseChain.equals(BAND_MAIN)) {
                chainName.setText(c.getString(R.string.str_band_chain));
            } else if (baseChain.equals(CERTIK_MAIN)) {
                chainName.setText(c.getString(R.string.str_certik_chain));
            } else if (baseChain.equals(SECRET_MAIN)) {
                chainName.setText(c.getString(R.string.str_secret_chain));
            } else if (baseChain.equals(AKASH_MAIN)) {
                chainName.setText(c.getString(R.string.str_akash_chain));
            } else if (baseChain.equals(OKEX_MAIN)) {
                chainName.setText(c.getString(R.string.str_ok_net));
            } else if (baseChain.equals(PERSIS_MAIN)) {
                chainName.setText(c.getString(R.string.str_persis_net));
            } else if (baseChain.equals(SENTINEL_MAIN)) {
                chainName.setText(c.getString(R.string.str_sentinel_net));
            } else if (baseChain.equals(FETCHAI_MAIN)) {
                chainName.setText(c.getString(R.string.str_fetch_net));
            } else if (baseChain.equals(CRYPTO_MAIN)) {
                chainName.setText(c.getString(R.string.str_crypto_net));
            } else if (baseChain.equals(SIF_MAIN)) {
                chainName.setText(c.getString(R.string.str_sif_net));
            } else if (baseChain.equals(KI_MAIN)) {
                chainName.setText(c.getString(R.string.str_ki_net));
            } else if (baseChain.equals(OSMOSIS_MAIN)) {
                chainName.setText(c.getString(R.string.str_osmosis_net));
            } else if (baseChain.equals(MEDI_MAIN)) {
                chainName.setText(c.getString(R.string.str_medi_net));
            } else if (baseChain.equals(EMONEY_MAIN)) {
                chainName.setText(c.getString(R.string.str_emoney_net));
            } else if (baseChain.equals(RIZON_MAIN)) {
                chainName.setText(c.getString(R.string.str_rizon_net));
            } else if (baseChain.equals(JUNO_MAIN)) {
                chainName.setText(c.getString(R.string.str_juno_net));
            } else if (baseChain.equals(REGEN_MAIN)) {
                chainName.setText(c.getString(R.string.str_regen_net));
            } else if (baseChain.equals(BITCANNA_MAIN)) {
                chainName.setText(c.getString(R.string.str_bitcanna_net));
            } else if (baseChain.equals(ALTHEA_MAIN)) {
                chainName.setText(c.getString(R.string.str_althea_net));
            } else if (baseChain.equals(STARGAZE_MAIN)) {
                chainName.setText(c.getString(R.string.str_stargaze_net));
            } else if (baseChain.equals(GRABRIDGE_MAIN)) {
                chainName.setText(c.getString(R.string.str_grabridge_net));
            } else if (baseChain.equals(COMDEX_MAIN)) {
                chainName.setText(c.getString(R.string.str_comdex_net));
            } else if (baseChain.equals(INJ_MAIN)) {
                chainName.setText(c.getString(R.string.str_inj_net));
            } else if (baseChain.equals(BITSONG_MAIN)) {
                chainName.setText(c.getString(R.string.str_bitsong_net));
            } else if (baseChain.equals(DESMOS_MAIN)) {
                chainName.setText(c.getString(R.string.str_desmos_net));
            } else if (baseChain.equals(LUM_MAIN)) {
                chainName.setText(c.getString(R.string.str_lum_net));

            }

            else if (baseChain.equals(COSMOS_TEST)) {
                chainName.setText(c.getString(R.string.str_cosmos_testnet));
            } else if (baseChain.equals(IRIS_TEST)) {
                chainName.setText(c.getString(R.string.str_iris_testnet));
            } else if (baseChain.equals(BNB_TEST)) {
                chainName.setText(c.getString(R.string.str_binance_test_net));
            } else if (baseChain.equals(IOV_TEST)) {
                chainName.setText(c.getString(R.string.str_iov_net_test));
            } else if (baseChain.equals(OK_TEST)) {
                chainName.setText(c.getString(R.string.str_ok_net_test));
            } else if (baseChain.equals(CERTIK_TEST)) {
                chainName.setText(c.getString(R.string.str_certik_chain_test));
            } else if (baseChain.equals(RIZON_TEST)) {
                chainName.setText(c.getString(R.string.str_rizon_chain_test));
            } else if (baseChain.equals(MEDI_TEST)) {
                chainName.setText(c.getString(R.string.str_medi_chain_test));
            } else if (baseChain.equals(ALTHEA_TEST)) {
                chainName.setText(c.getString(R.string.str_althea_chain_test));
            } else if (baseChain.equals(UMEE_TEST)) {
                chainName.setText(c.getString(R.string.str_umee_chain_test));
            } else if (baseChain.equals(AXELAR_TEST)) {
                chainName.setText(c.getString(R.string.str_axelar_chain_test));
            }
        } else {
            chainName.setText("Unknown");
        }

    }

    public static void getChainTitle2(Context c, BaseChain baseChain, TextView chainName) {
        if (baseChain != null) {
            if (baseChain.equals(COSMOS_MAIN)) {
                chainName.setText(c.getString(R.string.str_cosmos));
            } else if (baseChain.equals(IRIS_MAIN)) {
                chainName.setText(c.getString(R.string.str_iris));
            } else if (baseChain.equals(BNB_MAIN)) {
                chainName.setText(c.getString(R.string.str_binance));
            } else if (baseChain.equals(KAVA_MAIN)) {
                chainName.setText(c.getString(R.string.str_kava));
            } else if (baseChain.equals(IOV_MAIN)) {
                chainName.setText(c.getString(R.string.str_iov));
            } else if (baseChain.equals(BAND_MAIN)) {
                chainName.setText(c.getString(R.string.str_band));
            } else if (baseChain.equals(CERTIK_MAIN)) {
                chainName.setText(c.getString(R.string.str_certik_main));
            } else if (baseChain.equals(SECRET_MAIN)) {
                chainName.setText(c.getString(R.string.str_secret_main));
            } else if (baseChain.equals(AKASH_MAIN)) {
                chainName.setText(c.getString(R.string.str_akash_main));
            } else if (baseChain.equals(OKEX_MAIN)) {
                chainName.setText(c.getString(R.string.str_okex_main));
            } else if (baseChain.equals(PERSIS_MAIN)) {
                chainName.setText(c.getString(R.string.str_persis_main));
            } else if (baseChain.equals(SENTINEL_MAIN)) {
                chainName.setText(c.getString(R.string.str_sentinel_main));
            } else if (baseChain.equals(FETCHAI_MAIN)) {
                chainName.setText(c.getString(R.string.str_fetch_main));
            } else if (baseChain.equals(CRYPTO_MAIN)) {
                chainName.setText(c.getString(R.string.str_crypto_main));
            } else if (baseChain.equals(SIF_MAIN)) {
                chainName.setText(c.getString(R.string.str_sif_main));
            } else if (baseChain.equals(KI_MAIN)) {
                chainName.setText(c.getString(R.string.str_ki_main));
            } else if (baseChain.equals(OSMOSIS_MAIN)) {
                chainName.setText(c.getString(R.string.str_osmosis_main));
            } else if (baseChain.equals(MEDI_MAIN)) {
                chainName.setText(c.getString(R.string.str_medi_main));
            } else if (baseChain.equals(EMONEY_MAIN)) {
                chainName.setText(c.getString(R.string.str_emoney_main));
            } else if (baseChain.equals(RIZON_MAIN)) {
                chainName.setText(c.getString(R.string.str_rizon_main));
            } else if (baseChain.equals(JUNO_MAIN)) {
                chainName.setText(c.getString(R.string.str_juno_main));
            } else if (baseChain.equals(REGEN_MAIN)) {
                chainName.setText(c.getString(R.string.str_regen_main));
            } else if (baseChain.equals(BITCANNA_MAIN)) {
                chainName.setText(c.getString(R.string.str_bitcanna_main));
            } else if (baseChain.equals(ALTHEA_MAIN)) {
                chainName.setText(c.getString(R.string.str_althea_main));
            } else if (baseChain.equals(STARGAZE_MAIN)) {
                chainName.setText(c.getString(R.string.str_stargaze_main));
            } else if (baseChain.equals(GRABRIDGE_MAIN)) {
                chainName.setText(c.getString(R.string.str_grabridge_main));
            } else if (baseChain.equals(COMDEX_MAIN)) {
                chainName.setText(c.getString(R.string.str_comdex_main));
            } else if (baseChain.equals(INJ_MAIN)) {
                chainName.setText(c.getString(R.string.str_inj_main));
            } else if (baseChain.equals(BITSONG_MAIN)) {
                chainName.setText(c.getString(R.string.str_bitsong_main));
            } else if (baseChain.equals(DESMOS_MAIN)) {
                chainName.setText(c.getString(R.string.str_desmos_main));
            } else if (baseChain.equals(DESMOS_MAIN)) {
                chainName.setText(c.getString(R.string.str_lum_main));

            }

            else if (baseChain.equals(COSMOS_TEST)) {
                chainName.setText(c.getString(R.string.str_cosmos_test));
            } else if (baseChain.equals(IRIS_TEST)) {
                chainName.setText(c.getString(R.string.str_iris_test));
            } else if (baseChain.equals(BNB_TEST)) {
                chainName.setText(c.getString(R.string.str_binance_test));
            } else if (baseChain.equals(IOV_TEST)) {
                chainName.setText(c.getString(R.string.str_iov_test));
            } else if (baseChain.equals(OK_TEST)) {
                chainName.setText(c.getString(R.string.str_ok_test));
            } else if (baseChain.equals(CERTIK_TEST)) {
                chainName.setText(c.getString(R.string.str_certik_test));
            } else if (baseChain.equals(RIZON_TEST)) {
                chainName.setText(c.getString(R.string.str_rizon_test));
            } else if (baseChain.equals(MEDI_TEST)) {
                chainName.setText(c.getString(R.string.str_medi_test));
            } else if (baseChain.equals(ALTHEA_TEST)) {
                chainName.setText(c.getString(R.string.str_althea_test));
            } else if (baseChain.equals(UMEE_TEST)) {
                chainName.setText(c.getString(R.string.str_umee_test));
            } else if (baseChain.equals(AXELAR_TEST)) {
                chainName.setText(c.getString(R.string.str_axelar_test));
            }
        } else {
            chainName.setText("Unknown");
        }
    }

    public static void getFloatBtn(Context c, BaseChain baseChain, FloatingActionButton floatBtn) {
        if (baseChain.equals(COSMOS_MAIN)) {
            floatBtn.setBackgroundTintList(c.getResources().getColorStateList(R.color.colorAtom));
        } else if (baseChain.equals(IRIS_MAIN)) {
            floatBtn.setBackgroundTintList(c.getResources().getColorStateList(R.color.colorIris));
        } else if (baseChain.equals(BNB_MAIN)) {
            floatBtn.setBackgroundTintList(c.getResources().getColorStateList(R.color.colorBnb));
        } else if (baseChain.equals(KAVA_MAIN)) {
            floatBtn.setBackgroundTintList(c.getResources().getColorStateList(R.color.colorKava));
        } else if (baseChain.equals(IOV_MAIN)) {
            floatBtn.setBackgroundTintList(c.getResources().getColorStateList(R.color.colorIov));
        } else if (baseChain.equals(BAND_MAIN)) {
            floatBtn.setBackgroundTintList(c.getResources().getColorStateList(R.color.colorBand));
        } else if (baseChain.equals(CERTIK_MAIN)) {
            floatBtn.setBackgroundTintList(c.getResources().getColorStateList(R.color.colorCertik));
        } else if (baseChain.equals(SECRET_MAIN)) {
            floatBtn.setBackgroundTintList(c.getResources().getColorStateList(R.color.colorSecret2));
        } else if (baseChain.equals(AKASH_MAIN)) {
            floatBtn.setBackgroundTintList(c.getResources().getColorStateList(R.color.colorAkash));
        } else if (baseChain.equals(OKEX_MAIN)) {
            floatBtn.setBackgroundTintList(c.getResources().getColorStateList(R.color.colorOK));
        } else if (baseChain.equals(PERSIS_MAIN)) {
            floatBtn.setBackgroundTintList(c.getResources().getColorStateList(R.color.colorBlack));
            floatBtn.setImageTintList(c.getResources().getColorStateList(R.color.colorPersis));
        } else if (baseChain.equals(SENTINEL_MAIN)) {
            floatBtn.setBackgroundTintList(c.getResources().getColorStateList(R.color.colorSentinel3));
        } else if (baseChain.equals(FETCHAI_MAIN)) {
            floatBtn.setBackgroundTintList(c.getResources().getColorStateList(R.color.colorFetch));
        } else if (baseChain.equals(CRYPTO_MAIN)) {
            floatBtn.setBackgroundTintList(c.getResources().getColorStateList(R.color.colorCryto2));
        } else if (baseChain.equals(SIF_MAIN)) {
            floatBtn.setBackgroundTintList(c.getResources().getColorStateList(R.color.colorSif));
        } else if (baseChain.equals(KI_MAIN)) {
            floatBtn.setBackgroundTintList(c.getResources().getColorStateList(R.color.colorKi));
        } else if (baseChain.equals(OSMOSIS_MAIN)) {
            floatBtn.setBackgroundTintList(c.getResources().getColorStateList(R.color.colorOsmosis));
        } else if (baseChain.equals(MEDI_MAIN)) {
            floatBtn.setBackgroundTintList(c.getResources().getColorStateList(R.color.colorWhite));
            floatBtn.setImageTintList(c.getResources().getColorStateList(R.color.colorMedi));
        } else if (baseChain.equals(EMONEY_MAIN)) {
            floatBtn.setBackgroundTintList(c.getResources().getColorStateList(R.color.colorEmoney));
            floatBtn.setImageTintList(c.getResources().getColorStateList(R.color.colorWhite));
        } else if (baseChain.equals(RIZON_MAIN)) {
            floatBtn.setBackgroundTintList(c.getResources().getColorStateList(R.color.colorRizon));
        } else if (baseChain.equals(JUNO_MAIN)) {
            floatBtn.setBackgroundTintList(c.getResources().getColorStateList(R.color.colorJuno));
        } else if (baseChain.equals(REGEN_MAIN)) {
            floatBtn.setBackgroundTintList(c.getResources().getColorStateList(R.color.colorRegen));
        } else if (baseChain.equals(BITCANNA_MAIN)) {
            floatBtn.setBackgroundTintList(c.getResources().getColorStateList(R.color.colorBitcanna));
        } else if (baseChain.equals(ALTHEA_MAIN)) {
            floatBtn.setBackgroundTintList(c.getResources().getColorStateList(R.color.colorAlthea));
        } else if (baseChain.equals(STARGAZE_MAIN)) {
            floatBtn.setBackgroundTintList(c.getResources().getColorStateList(R.color.colorStargaze));
        } else if (baseChain.equals(GRABRIDGE_MAIN)) {
            floatBtn.setBackgroundTintList(c.getResources().getColorStateList(R.color.colorGraBridge));
        } else if (baseChain.equals(COMDEX_MAIN)) {
            floatBtn.setBackgroundTintList(c.getResources().getColorStateList(R.color.colorTransBgComdex));
            floatBtn.setImageTintList(c.getResources().getColorStateList(R.color.colorComdex));
        } else if (baseChain.equals(INJ_MAIN)) {
            floatBtn.setBackgroundTintList(c.getResources().getColorStateList(R.color.colorInj));
            floatBtn.setImageTintList(c.getResources().getColorStateList(R.color.colorBlack));
        } else if (baseChain.equals(BITSONG_MAIN)) {
            floatBtn.setBackgroundTintList(c.getResources().getColorStateList(R.color.colorBitsong));
        } else if (baseChain.equals(DESMOS_MAIN)) {
            floatBtn.setBackgroundTintList(c.getResources().getColorStateList(R.color.colorDesmos));
        } else if (baseChain.equals(LUM_MAIN)) {
            floatBtn.setBackgroundTintList(c.getResources().getColorStateList(R.color.colorLum));

        }

        else if (baseChain.equals(COSMOS_TEST)) {
            floatBtn.setBackgroundTintList(c.getResources().getColorStateList(R.color.colorAtom));
        } else if (baseChain.equals(IOV_TEST)) {
            floatBtn.setBackgroundTintList(c.getResources().getColorStateList(R.color.colorIov));
        } else if (baseChain.equals(OK_TEST)) {
            floatBtn.setBackgroundTintList(c.getResources().getColorStateList(R.color.colorOK));
        } else if (baseChain.equals(CERTIK_TEST)) {
            floatBtn.setBackgroundTintList(c.getResources().getColorStateList(R.color.colorCertik));
        } else if (baseChain.equals(RIZON_TEST)) {
            floatBtn.setBackgroundTintList(c.getResources().getColorStateList(R.color.colorRizon));
        } else if (baseChain.equals(MEDI_TEST)) {
            floatBtn.setBackgroundTintList(c.getResources().getColorStateList(R.color.colorMedi));
        } else if (baseChain.equals(ALTHEA_TEST)) {
            floatBtn.setBackgroundTintList(c.getResources().getColorStateList(R.color.colorAlthea));
            floatBtn.setImageTintList(c.getResources().getColorStateList(R.color.colorBlack));
        } else if (baseChain.equals(UMEE_TEST)) {
            floatBtn.setBackgroundTintList(c.getResources().getColorStateList(R.color.colorUmee));
            floatBtn.setImageTintList(c.getResources().getColorStateList(R.color.colorWhite));
        } else if (baseChain.equals(AXELAR_TEST)) {
            floatBtn.setBackgroundTintList(c.getResources().getColorStateList(R.color.colorAxelar));
            floatBtn.setImageTintList(c.getResources().getColorStateList(R.color.colorBlack));
        }
    }

    public static void getLayoutColor(Context c, BaseChain baseChain, LinearLayout[] wordsLayer) {
        for(int i = 0; i < wordsLayer.length; i++) {
            if (baseChain.equals(COSMOS_MAIN)) {
                wordsLayer[i].setBackground(c.getDrawable(R.drawable.box_round_atom));
            } else if (baseChain.equals(IRIS_MAIN)) {
                wordsLayer[i].setBackground(c.getDrawable(R.drawable.box_round_iris));
            } else if (baseChain.equals(BNB_MAIN)) {
                wordsLayer[i].setBackground(c.getDrawable(R.drawable.box_round_bnb));
            } else if (baseChain.equals(KAVA_MAIN)) {
                wordsLayer[i].setBackground(c.getDrawable(R.drawable.box_round_kava));
            } else if (baseChain.equals(IOV_MAIN)) {
                wordsLayer[i].setBackground(c.getDrawable(R.drawable.box_round_iov));
            } else if (baseChain.equals(BAND_MAIN)) {
                wordsLayer[i].setBackground(c.getDrawable(R.drawable.box_round_band));
            } else if (baseChain.equals(CERTIK_MAIN)) {
                wordsLayer[i].setBackground(c.getDrawable(R.drawable.box_round_certik));
            } else if (baseChain.equals(AKASH_MAIN)) {
                wordsLayer[i].setBackground(c.getDrawable(R.drawable.box_round_akash));
            } else if (baseChain.equals(SECRET_MAIN)) {
                wordsLayer[i].setBackground(c.getDrawable(R.drawable.box_round_secret));
            } else if (baseChain.equals(OKEX_MAIN)) {
                wordsLayer[i].setBackground(c.getDrawable(R.drawable.box_round_okex));
            } else if (baseChain.equals(PERSIS_MAIN)) {
                wordsLayer[i].setBackground(c.getDrawable(R.drawable.box_round_persis));
            } else if (baseChain.equals(SENTINEL_MAIN)) {
                wordsLayer[i].setBackground(c.getDrawable(R.drawable.box_round_sentinel));
            } else if (baseChain.equals(FETCHAI_MAIN)) {
                wordsLayer[i].setBackground(c.getDrawable(R.drawable.box_round_fetch));
            } else if (baseChain.equals(CRYPTO_MAIN)) {
                wordsLayer[i].setBackground(c.getDrawable(R.drawable.box_round_cryto));
            } else if (baseChain.equals(SIF_MAIN)) {
                wordsLayer[i].setBackground(c.getDrawable(R.drawable.box_round_sif));
            } else if (baseChain.equals(KI_MAIN)) {
                wordsLayer[i].setBackground(c.getDrawable(R.drawable.box_round_ki));
            } else if (baseChain.equals(OSMOSIS_MAIN)) {
                wordsLayer[i].setBackground(c.getDrawable(R.drawable.box_round_osmosis));
            } else if (baseChain.equals(MEDI_MAIN)) {
                wordsLayer[i].setBackground(c.getDrawable(R.drawable.box_round_medi));
            } else if (baseChain.equals(EMONEY_MAIN)) {
                wordsLayer[i].setBackground(c.getDrawable(R.drawable.box_round_emoney));
            } else if (baseChain.equals(RIZON_MAIN) || baseChain.equals(RIZON_TEST)) {
                wordsLayer[i].setBackground(c.getDrawable(R.drawable.box_round_rizon));
            } else if (baseChain.equals(JUNO_MAIN)) {
                wordsLayer[i].setBackground(c.getDrawable(R.drawable.box_round_juno));
            } else if (baseChain.equals(REGEN_MAIN)) {
                wordsLayer[i].setBackground(c.getDrawable(R.drawable.box_round_regen));
            } else if (baseChain.equals(BITCANNA_MAIN)) {
                wordsLayer[i].setBackground(c.getDrawable(R.drawable.box_round_bitcanna));
            } else if (baseChain.equals(ALTHEA_MAIN)) {
                wordsLayer[i].setBackground(c.getDrawable(R.drawable.box_round_althea));
            } else if (baseChain.equals(STARGAZE_MAIN)) {
                wordsLayer[i].setBackground(c.getDrawable(R.drawable.box_round_stargaze));
            } else if (baseChain.equals(GRABRIDGE_MAIN)) {
                wordsLayer[i].setBackground(c.getDrawable(R.drawable.box_round_grabridge));
            } else if (baseChain.equals(COMDEX_MAIN)) {
                wordsLayer[i].setBackground(c.getDrawable(R.drawable.box_round_comdex));
            } else if (baseChain.equals(INJ_MAIN)) {
                wordsLayer[i].setBackground(c.getDrawable(R.drawable.box_round_inj));
            } else if (baseChain.equals(BITSONG_MAIN)) {
                wordsLayer[i].setBackground(c.getDrawable(R.drawable.box_round_bitsong));
            } else if (baseChain.equals(DESMOS_MAIN)) {
                wordsLayer[i].setBackground(c.getDrawable(R.drawable.box_round_desmos));
            } else if (baseChain.equals(LUM_MAIN)) {
                wordsLayer[i].setBackground(c.getDrawable(R.drawable.box_round_lum));
            } else if (baseChain.equals(UMEE_TEST)) {
                wordsLayer[i].setBackground(c.getDrawable(R.drawable.box_round_umee));
            } else if (baseChain.equals(AXELAR_TEST)) {
                wordsLayer[i].setBackground(c.getDrawable(R.drawable.box_round_axelar));

            }
            else if (baseChain.equals(BNB_TEST) || baseChain.equals(KAVA_TEST) || baseChain.equals(IOV_TEST) || baseChain.equals(OK_TEST) ||
                    baseChain.equals(CERTIK_TEST) || baseChain.equals(COSMOS_TEST) || baseChain.equals(IRIS_TEST) || baseChain.equals(MEDI_TEST) || baseChain.equals(ALTHEA_TEST)) {
                wordsLayer[i].setBackground(c.getDrawable(R.drawable.box_round_darkgray));
            }
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
            } else if (chainId.contains("laozi-mainnet-")) {
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
            } else if (chainId.contains("inj-")) {
                return INJ_MAIN;
            } else if (chainId.contains("desmos-")) {
                return DESMOS_MAIN;
            } else if (chainId.contains("gravity-bridge-")) {
                return GRABRIDGE_MAIN;
            } else if (chainId.contains("lum-network-")) {
                return LUM_MAIN;
            }
        }
        return null;
    }

    public static void getChainByAddress(BaseChain baseChain, String address, TextView textView) {
        if (baseChain != null) {
            if (baseChain.equals(COSMOS_MAIN)) {
                if (!address.startsWith("cosmos1")) {
                    textView.setText("");
                }
            } else if (baseChain.equals(IRIS_MAIN)) {
                if (!address.startsWith("iaa1")) {
                    textView.setText("");
                }
            } else if (baseChain.equals(IOV_MAIN)) {
                if (!address.startsWith("star")) {
                    textView.setText("");
                }
            } else if (baseChain.equals(AKASH_MAIN)) {
                if (!address.startsWith("akash1")) {
                    textView.setText("");
                }
            } else if (baseChain.equals(SENTINEL_MAIN)) {
                if (!address.startsWith("sent1")) {
                    textView.setText("");
                }
            } else if (baseChain.equals(PERSIS_MAIN)) {
                if (!address.startsWith("persistence1")) {
                    textView.setText("");
                }
            } else if (baseChain.equals(SIF_MAIN)) {
                if (!address.startsWith("sif1")) {
                    textView.setText("");
                }
            } else if (baseChain.equals(OSMOSIS_MAIN)) {
                if (!address.startsWith("osmo1")) {
                    textView.setText("");
                }
            } else if (baseChain.equals(CRYPTO_MAIN)) {
                if (!address.startsWith("cro1")) {
                    textView.setText("");
                }
            } else if (baseChain.equals(BAND_MAIN)) {
                if (!address.startsWith("band1")) {
                    textView.setText("");
                }
            } else if (baseChain.equals(CERTIK_MAIN)) {
                if (!address.startsWith("certik1")) {
                    textView.setText("");
                }
            } else if (baseChain.equals(MEDI_MAIN)) {
                if (!address.startsWith("panacea1")) {
                    textView.setText("");
                }
            } else if (baseChain.equals(EMONEY_MAIN)) {
                if (!address.startsWith("emoney1")) {
                    textView.setText("");
                }
            } else if (baseChain.equals(REGEN_MAIN)) {
                if (!address.startsWith("regen1")) {
                    textView.setText("");
                }
            } else if (baseChain.equals(JUNO_MAIN)) {
                if (!address.startsWith("juno1")) {
                    textView.setText("");
                }
            } else if (baseChain.equals(FETCHAI_MAIN)) {
                if (!address.startsWith("fetch1")) {
                    textView.setText("");
                }
            } else if (baseChain.equals(RIZON_MAIN)) {
                if (!address.startsWith("rizon1")) {
                    textView.setText("");
                }
            } else if (baseChain.equals(KI_MAIN)) {
                if (!address.startsWith("ki1")) {
                    textView.setText("");
                }
            } else if (baseChain.equals(BITCANNA_MAIN)) {
                if (!address.startsWith("bcna1")) {
                    textView.setText("");
                }
            } else if (baseChain.equals(STARGAZE_MAIN)) {
                if (!address.startsWith("stars1")) {
                    textView.setText("");
                }
            } else if (baseChain.equals(COMDEX_MAIN)) {
                if (!address.startsWith("comdex1")) {
                    textView.setText("");
                }
            } else if (baseChain.equals(SECRET_MAIN)) {
                if (!address.startsWith("secret1")) {
                    textView.setText("");
                }
            } else if (baseChain.equals(BITSONG_MAIN)) {
                if (!address.startsWith("bitsong1")) {
                    textView.setText("");
                }
            } else if (baseChain.equals(INJ_MAIN)) {
                if (!address.startsWith("inj1")) {
                    textView.setText("");
                }
            } else if (baseChain.equals(DESMOS_MAIN)) {
                if (!address.startsWith("desmos1")) {
                    textView.setText("");
                }
            } else if (baseChain.equals(GRABRIDGE_MAIN)) {
                if (!address.startsWith("gravity1")) {
                    textView.setText("");
                }
            } else if (baseChain.equals(LUM_MAIN)) {
                if (!address.startsWith("lum1")) {
                    textView.setText("");
                }
            }
        }
    }

    public static String getChainNameByBaseChain(BaseChain baseChain) {
        if (baseChain != null) {
            if (baseChain.equals(COSMOS_MAIN)) {
                return "cosmos";
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
            }  else if (baseChain.equals(IOV_MAIN)) {
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
            }

            else if (baseChain.equals(BNB_MAIN)) {
                return "bnb";
            } else if (baseChain.equals(OKEX_MAIN)) {
                return "okex";
            }

            else if (baseChain.equals(COSMOS_TEST)) {
                return "cosmos-testnet";
            } else if (baseChain.equals(RIZON_TEST)) {
                return "rizon-testnet";
            } else if (baseChain.equals(AXELAR_TEST)) {
                return "axelar";
            } else if (baseChain.equals(UMEE_TEST)) {
                return "umee";
            }
        }
        return null;
    }

    public static boolean isValidChainAddress(BaseChain baseChain, String address) {
        if (baseChain != null) {
            if (address.startsWith("0x")) {
                if (!WKey.isValidEthAddress(address)) { return false; }
                if (baseChain.equals(OKEX_MAIN)) { return true; }
                return false;
            }

            if (!WKey.isValidBech32(address)) { return false; }
            if (address.startsWith("cosmos1") && baseChain.equals(COSMOS_MAIN)) { return true; }
            else if (address.startsWith("iaa1") && baseChain.equals(IRIS_MAIN)) { return true; }
            else if (address.startsWith("bnb1") && baseChain.equals(BNB_MAIN)) { return true; }
            else if (address.startsWith("kava1") && baseChain.equals(KAVA_MAIN)) { return true; }
            else if (address.startsWith("star1") && baseChain.equals(IOV_MAIN)) { return true; }
            else if (address.startsWith("band1") && baseChain.equals(BAND_MAIN)) { return true; }
            else if (address.startsWith("secret1") && baseChain.equals(SECRET_MAIN)) { return true; }
            else if (address.startsWith("ex1") && baseChain.equals(OKEX_MAIN)) { return true; }
            else if (address.startsWith("certik1") && baseChain.equals(CERTIK_MAIN)) { return true; }
            else if (address.startsWith("akash1") && baseChain.equals(AKASH_MAIN)) { return true; }
            else if (address.startsWith("persistence1") && baseChain.equals(PERSIS_MAIN)) { return true; }
            else if (address.startsWith("sent1") && baseChain.equals(SENTINEL_MAIN)) { return true; }
            else if (address.startsWith("fetch1") && baseChain.equals(FETCHAI_MAIN)) { return true; }
            else if (address.startsWith("cro1") && baseChain.equals(CRYPTO_MAIN)) { return true; }
            else if (address.startsWith("sif1") && baseChain.equals(SIF_MAIN)) { return true; }
            else if (address.startsWith("rizon1") && baseChain.equals(RIZON_MAIN)) { return true; }
            else if (address.startsWith("ki1") && baseChain.equals(KI_MAIN)) { return true; }
            else if (address.startsWith("panacea1") && baseChain.equals(MEDI_MAIN)) { return true; }
            else if (address.startsWith("osmo1") && baseChain.equals(OSMOSIS_MAIN)) { return true; }
            else if (address.startsWith("emoney1") && baseChain.equals(EMONEY_MAIN)) { return true; }
            else if (address.startsWith("juno1") && baseChain.equals(JUNO_MAIN)) { return true; }
            else if (address.startsWith("regen1") && baseChain.equals(REGEN_MAIN)) { return true; }
            else if (address.startsWith("bcna1") && baseChain.equals(BITCANNA_MAIN)) { return true; }
            else if (address.startsWith("althea1") && baseChain.equals(ALTHEA_MAIN)) { return true; }
            else if (address.startsWith("stars1") && baseChain.equals(STARGAZE_MAIN)) { return true; }
            else if (address.startsWith("gravity1") && baseChain.equals(GRABRIDGE_MAIN)) { return true; }
            else if (address.startsWith("comdex1") && baseChain.equals(COMDEX_MAIN)) { return true; }
            else if (address.startsWith("inj1") && baseChain.equals(INJ_MAIN)) { return true; }
            else if (address.startsWith("bitsong1") && baseChain.equals(BITSONG_MAIN)) { return true; }
            else if (address.startsWith("desmos1") && baseChain.equals(DESMOS_MAIN)) { return true; }
            else if (address.startsWith("lum1") && baseChain.equals(LUM_MAIN)) { return true; }
        }
        return false;
    }

    public static String getDefaultRelayerImg(BaseChain chain) {
        if (chain != null) {
            if (chain.equals(AKASH_MAIN)) { return AKASH_UNKNOWN_RELAYER; }
            else if (chain.equals(BAND_MAIN)) { return BAND_UNKNOWN_RELAYER; }
            else if (chain.equals(CERTIK_MAIN)) { return CERTIK_UNKNOWN_RELAYER; }
            else if (chain.equals(COSMOS_MAIN)) { return COSMOS_UNKNOWN_RELAYER; }
            else if (chain.equals(CRYPTO_MAIN)) { return CRYPTO_UNKNOWN_RELAYER; }
            else if (chain.equals(EMONEY_MAIN)) { return EMONEY_UNKNOWN_RELAYER; }
            else if (chain.equals(FETCHAI_MAIN)) { return FETCHAI_UNKNOWN_RELAYER; }
            else if (chain.equals(IRIS_MAIN)) { return IRIS_UNKNOWN_RELAYER; }
            else if (chain.equals(JUNO_MAIN)) { return JUNO_UNKNOWN_RELAYER; }
            else if (chain.equals(OSMOSIS_MAIN)) { return OSMOSIS_UNKNOWN_RELAYER; }
            else if (chain.equals(PERSIS_MAIN)) { return PERSIS_UNKNOWN_RELAYER; }
            else if (chain.equals(REGEN_MAIN)) { return REGEN_UNKNOWN_RELAYER; }
            else if (chain.equals(SENTINEL_MAIN)) { return SENTINEL_UNKNOWN_RELAYER; }
            else if (chain.equals(SIF_MAIN)) { return SIFCHAIN_UNKNOWN_RELAYER; }
            else if (chain.equals(IOV_MAIN)) { return STARNAME_UNKNOWN_RELAYER; }
            else if (chain.equals(KI_MAIN)) { return KI_UNKNOWN_RELAYER; }
            else if (chain.equals(BITCANNA_MAIN)) { return BITCANNA_UNKNOWN_RELAYER; }
            else if (chain.equals(RIZON_MAIN)) { return RIZON_UNKNOWN_RELAYER; }
            else if (chain.equals(MEDI_MAIN)) { return MEDI_UNKNOWN_RELAYER; }
            else if (chain.equals(STARGAZE_MAIN)) { return STARGAZE_UNKNOWN_RELAYER; }
            else if (chain.equals(COMDEX_MAIN)) { return COMDEX_UNKNOWN_RELAYER; }
            else if (chain.equals(SECRET_MAIN)) { return SECRET_UNKNOWN_RELAYER; }
            else if (chain.equals(INJ_MAIN)) { return INJ_UNKNOWN_RELAYER; }
            else if (chain.equals(BITSONG_MAIN)) { return BITSONG_UNKNOWN_RELAYER; }
            else if (chain.equals(DESMOS_MAIN)) { return DESMOS_UNKNOWN_RELAYER; }
            else if (chain.equals(GRABRIDGE_MAIN)) { return GRAB_UNKNOWN_RELAYER; }
            else if (chain.equals(LUM_MAIN)) { return LUM_UNKNOWN_RELAYER; }
            else if (chain.equals(UMEE_TEST)) { return UMEE_UNKNOWN_RELAYER; }
        }
        return null;
    }

    public static SpannableString getDpEstAprCommission(BaseData baseData, BaseChain chain, BigDecimal commission) {
        final ChainParam.Params param = baseData.mChainParam;
        BigDecimal apr = BigDecimal.ZERO;
        if (param != null) { apr = param.getApr(chain); }
        BigDecimal calCommission = BigDecimal.ONE.subtract(commission);
        BigDecimal aprCommission = apr.multiply(calCommission).movePointRight(2);
        return getPercentDp(aprCommission);
    }

    public static SpannableString getDailyReward(Context c, BaseData baseData, BigDecimal commission, BigDecimal delegated, BaseChain chain) {
        final ChainParam.Params param = baseData.mChainParam;
        BigDecimal apr = BigDecimal.ZERO;
        if (param != null) {
            if (BigDecimal.ZERO.compareTo(param.getRealApr(chain)) == 0) { apr = param.getApr(chain); }
            else { apr = param.getRealApr(chain); }
        }
        BigDecimal calCommission = BigDecimal.ONE.subtract(commission);
        BigDecimal aprCommission = apr.multiply(calCommission);
        BigDecimal dayReward = delegated.multiply(aprCommission).divide(new BigDecimal("365") ,0, RoundingMode.DOWN);
        return getDpAmount2(c, dayReward, mainDivideDecimal(chain), mainDisplayDecimal(chain));
    }

    public static SpannableString getMonthlyReward(Context c, BaseData baseData, BigDecimal commission, BigDecimal delegated, BaseChain chain) {
        final ChainParam.Params param = baseData.mChainParam;
        BigDecimal apr = BigDecimal.ZERO;
        if (param.getRealApr(chain).equals(BigDecimal.ZERO)) { apr = param.getApr(chain); }
        else { apr = param.getRealApr(chain); }
        BigDecimal calCommission = BigDecimal.ONE.subtract(commission);
        BigDecimal aprCommission = apr.multiply(calCommission);
        BigDecimal dayReward = delegated.multiply(aprCommission).divide(new BigDecimal("12"), 0, RoundingMode.DOWN);
        return getDpAmount2(c, dayReward, mainDivideDecimal(chain), mainDisplayDecimal(chain));
    }

    public static String getKavaBaseDenom(String denom) {
        if (denom.equalsIgnoreCase(TOKEN_KAVA)) {
            return TOKEN_KAVA;
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
        int dpDecimal = WUtil.getKavaCoinDecimal(denom);
        HashMap<String, MarketPrice> prices = baseData.mKavaTokenPrices;
        if (denom.equals("hard") && prices.get("hard:usd") != null) {
            BigDecimal price = new BigDecimal(prices.get("hard:usd").price);
            return amount.movePointLeft(dpDecimal).multiply(price);

        } else if (denom.contains("btc") && prices.get("btc:usd") != null) {
            BigDecimal price = new BigDecimal(prices.get("btc:usd").price);
            return amount.movePointLeft(dpDecimal).multiply(price);

        } else if (denom.contains("bnb") && prices.get("bnb:usd") != null) {
            BigDecimal price = new BigDecimal(prices.get("bnb:usd").price);
            return amount.movePointLeft(dpDecimal).multiply(price);

        } else if (denom.contains("xrp") && prices.get("xrp:usd") != null) {
            BigDecimal price = new BigDecimal(prices.get("xrp:usd").price);
            return amount.movePointLeft(dpDecimal).multiply(price);

        } else if (denom.contains("usdx") && prices.get("usdx:usd") != null) {
            BigDecimal price = new BigDecimal(prices.get("usdx:usd").price);
            return amount.movePointLeft(dpDecimal).multiply(price);

        } else if (denom.contains("busd") && prices.get("busd:usd") != null) {
            BigDecimal price = new BigDecimal(prices.get("busd:usd").price);
            return amount.movePointLeft(dpDecimal).multiply(price);
        }
        return BigDecimal.ZERO;

    }

    public static String getKavaPriceFeedSymbol(String denom) {
        if (denom.equalsIgnoreCase(TOKEN_KAVA)) {
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
        if (baseData.mKavaTokenPrices.get(feedSymbol) == null){
            return BigDecimal.ZERO;
        }
        return new BigDecimal(baseData.mKavaTokenPrices.get(feedSymbol).price);
    }

    public static BigDecimal convertTokenToKava(BaseData baseData, String denom) {
        BigDecimal tokenAmount = baseData.availableAmount(denom).add(baseData.lockedAmount(denom));
        BigDecimal totalTokenValue = kavaTokenDollorValue(baseData, denom, tokenAmount);
        return totalTokenValue.movePointRight(6).divide(perUsdValue(baseData, TOKEN_KAVA), 6, RoundingMode.DOWN);
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
            return totalTokenValue.divide(perUsdValue(baseData, TOKEN_OK), 18, RoundingMode.DOWN);
        }
        return BigDecimal.ZERO;
    }


    public static SpannableString getDpRawDollor(Context c, String price, int scale) {
        BigDecimal mPrice = new BigDecimal(price);
        SpannableString result;
        result = new SpannableString("$ " +getDecimalFormat(scale).format(mPrice));
        result.setSpan(new RelativeSizeSpan(0.8f), result.length() - scale, result.length(), SPAN_INCLUSIVE_INCLUSIVE);
        return result;
    }

    public static SpannableString getDpRawDollor(Context c, BigDecimal price, int scale) {
        SpannableString result;
        result = new SpannableString("$ " +getDecimalFormat(scale).format(price));
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
            BalancerPoolOuterClass.BalancerPool pool = baseData.getOsmosisPoolByDenom(denom);
            return WUtil.getOsmoLpTokenPerUsdPrice(baseData, pool);
        }
        if (denom.startsWith("pool") && denom.length() >= 68) {
            ChainParam.GdexStatus pool = baseData.getParamGravityPoolByDenom(denom);
            return WUtil.getParamGdexLpTokenPerUsdPrice(baseData, pool);
        }
        if (denom.equals(TOKEN_EMONEY_EUR) || denom.equals(TOKEN_EMONEY_CHF) || denom.equals(TOKEN_EMONEY_DKK) ||
                denom.equals(TOKEN_EMONEY_NOK) || denom.equals(TOKEN_EMONEY_SEK)) {
            if (baseData.getPrice("usdt") != null && baseData.getPrice("usdt").prices != null) {
                for (Price.Prices price: baseData.getPrice("usdt").prices) {
                    if (price.currency.equalsIgnoreCase(denom.substring(1))) {
                        return BigDecimal.ONE.divide(new BigDecimal(price.current_price), 3, RoundingMode.DOWN);
                    }
                }
            }
        }
        if (baseData.getPrice(denom) != null) {
            return baseData.getPrice(denom).currencyPrice("usd").setScale(3, RoundingMode.DOWN);
        }
        return BigDecimal.ZERO.setScale(3, RoundingMode.DOWN);
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
        final String formatted = baseData.getCurrencySymbol()  + " " + getDecimalFormat(3).format(totalValue);
        return dpCurrencyValue(formatted, 3);
    }

    public static BigDecimal userCurrencyValue(BaseData baseData, String denom, BigDecimal amount, int divider) {
        return perUserCurrencyValue(baseData, denom).multiply(amount).movePointLeft(divider).setScale(3, RoundingMode.DOWN);
    }

    public static SpannableString dpUserCurrencyValue(BaseData baseData, String denom, BigDecimal amount, int divider) {
        BigDecimal totalValue = userCurrencyValue(baseData, denom, amount, divider);
        final String formatted = baseData.getCurrencySymbol()  + " " + getDecimalFormat(3).format(totalValue);
        return dpCurrencyValue(formatted, 3);
    }

    public static BigDecimal btcValue(BaseData baseData, String denom, BigDecimal amount, int divider) {
        return perBtcValue(baseData, denom).multiply(amount).movePointLeft(divider).setScale(8, RoundingMode.DOWN);
    }

    public static BigDecimal allAssetToUserCurrency(BaseChain baseChain, BaseData baseData) {
        BigDecimal totalValue = BigDecimal.ZERO;
        if (isGRPC(baseChain)) {
            for (Coin coin: baseData.mGrpcBalance) {
                if (coin.denom.equals(mainDenom(baseChain))) {
                    BigDecimal amount = baseData.getAllMainAsset(mainDenom(baseChain));
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
                } else if (coin.isIbc()) {
                    BigDecimal amount = baseData.getAvailable(coin.denom);
                    IbcToken ibcToken = baseData.getIbcToken(coin.denom);
                    if (ibcToken != null && ibcToken.auth) {
                        BigDecimal assetValue = userCurrencyValue(baseData, ibcToken.base_denom, amount, ibcToken.decimal);
                        totalValue = totalValue.add(assetValue);
                    }
                }
            }
        }

        else if (baseChain.equals(BNB_MAIN) || baseChain.equals(BNB_TEST)) {
            for (Balance balance: baseData.mBalances) {
                if (balance.symbol.equals(mainDenom(baseChain))) {
                    BigDecimal amount = baseData.getAllBnbTokenAmount(balance.symbol);
                    BigDecimal assetValue = userCurrencyValue(baseData, TOKEN_BNB, amount, mainDivideDecimal(baseChain));
                    totalValue = totalValue.add(assetValue);
                } else {
                    BigDecimal amount = baseData.getAllBnbTokenAmount(balance.symbol);
                    BigDecimal convertAmount = WUtil.getBnbConvertAmount(baseData, balance.symbol, amount);
                    BigDecimal assetValue = userCurrencyValue(baseData, TOKEN_BNB, convertAmount, mainDivideDecimal(baseChain));
                    totalValue = totalValue.add(assetValue);
                }
            }

        } else if (baseChain.equals(KAVA_MAIN) || baseChain.equals(KAVA_TEST)) {
            for (Balance balance: baseData.mBalances) {
                if (balance.symbol.equals(mainDenom(baseChain))) {
                    BigDecimal amount = baseData.getAllMainAssetOld(balance.symbol);
                    BigDecimal assetValue = userCurrencyValue(baseData, TOKEN_KAVA, amount, mainDivideDecimal(baseChain));
                    totalValue = totalValue.add(assetValue);
                } else {
                    BigDecimal amount = baseData.availableAmount(balance.symbol);
                    amount = amount.add(baseData.lockedAmount(balance.symbol));
                    String kavaDenom = WDp.getKavaBaseDenom(balance.symbol);
                    int kavaDecimal = WUtil.getKavaCoinDecimal(balance.symbol);
                    BigDecimal assetValue = userCurrencyValue(baseData, kavaDenom, amount, kavaDecimal);
                    totalValue = totalValue.add(assetValue);
                }
            }

        } else if (baseChain.equals(OKEX_MAIN) || baseChain.equals(OK_TEST)) {
            for (Balance balance: baseData.mBalances) {
                if (balance.symbol.equals(mainDenom(baseChain))) {
                    BigDecimal amount = baseData.getAllExToken(balance.symbol);
                    BigDecimal assetValue = userCurrencyValue(baseData, TOKEN_OK, amount, mainDivideDecimal(baseChain));
                    totalValue = totalValue.add(assetValue);
                } else {
                    BigDecimal convertAmount = convertTokenToOkt(baseData, balance.symbol);
                    BigDecimal assetValue = userCurrencyValue(baseData, TOKEN_OK, convertAmount, mainDivideDecimal(baseChain));
                    totalValue = totalValue.add(assetValue);
                }
            }
        }

        else {
            for (Balance balance: baseData.mBalances) {
                if (balance.symbol.equals(mainDenom(baseChain))) {
                    BigDecimal amount = baseData.getAllMainAssetOld(balance.symbol);
                    BigDecimal assetValue = userCurrencyValue(baseData, balance.symbol, amount, mainDivideDecimal(baseChain));
                    totalValue = totalValue.add(assetValue);
                } else {

                }
            }

        }
        return totalValue;
    }

    public static BigDecimal allAssetToBtc(BaseChain baseChain, BaseData baseData) {
        BigDecimal totalValue = BigDecimal.ZERO;
        if (isGRPC(baseChain)) {
            for (Coin coin: baseData.mGrpcBalance) {
                if (coin.denom.equals(mainDenom(baseChain))) {
                    BigDecimal amount = baseData.getAllMainAsset(mainDenom(baseChain));
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
                } else if (coin.denom.startsWith("ibc/")) {
                    BigDecimal amount = baseData.getAvailable(coin.denom);
                    IbcToken ibcToken = baseData.getIbcToken(coin.denom);
                    if (ibcToken != null && ibcToken.auth) {
                        BigDecimal btcValue = btcValue(baseData, ibcToken.base_denom, amount, ibcToken.decimal);
                        totalValue = totalValue.add(btcValue);
                    }
                }
            }
        }

        else if (baseChain.equals(BNB_MAIN) || baseChain.equals(BNB_TEST)) {
            for (Balance balance: baseData.mBalances) {
                if (balance.symbol.equals(mainDenom(baseChain))) {
                    BigDecimal amount = baseData.getAllBnbTokenAmount(balance.symbol);
                    BigDecimal btcValue = btcValue(baseData, TOKEN_BNB, amount, mainDivideDecimal(baseChain));
                    totalValue = totalValue.add(btcValue);
                } else {
                    BigDecimal amount = baseData.getAllBnbTokenAmount(balance.symbol);
                    BigDecimal convertAmount = WUtil.getBnbConvertAmount(baseData, balance.symbol, amount);
                    BigDecimal btcValue = btcValue(baseData, TOKEN_BNB, convertAmount, mainDivideDecimal(baseChain));
                    totalValue = totalValue.add(btcValue);
                }
            }

        } else if (baseChain.equals(KAVA_MAIN) || baseChain.equals(KAVA_TEST)) {
            for (Balance balance: baseData.mBalances) {
                if (balance.symbol.equals(mainDenom(baseChain))) {
                    BigDecimal amount = baseData.getAllMainAssetOld(balance.symbol);
                    BigDecimal btcValue = btcValue(baseData, TOKEN_KAVA, amount, mainDivideDecimal(baseChain));
                    totalValue = totalValue.add(btcValue);
                } else {
                    BigDecimal convertAmount = convertTokenToKava(baseData, balance.symbol);
                    BigDecimal btcValue = btcValue(baseData, TOKEN_KAVA, convertAmount, mainDivideDecimal(baseChain));
                    totalValue = totalValue.add(btcValue);
                }
            }

        } else if (baseChain.equals(OKEX_MAIN) || baseChain.equals(OK_TEST)) {
            for (Balance balance: baseData.mBalances) {
                if (balance.symbol.equals(mainDenom(baseChain))) {
                    BigDecimal amount = baseData.getAllExToken(balance.symbol);
                    BigDecimal btcValue = btcValue(baseData, TOKEN_OK, amount, mainDivideDecimal(baseChain));
                    totalValue = totalValue.add(btcValue);
                } else {
                    BigDecimal convertAmount = convertTokenToOkt(baseData, balance.symbol);
                    BigDecimal btcValue = btcValue(baseData, TOKEN_OK, convertAmount, mainDivideDecimal(baseChain));
                    totalValue = totalValue.add(btcValue);
                }
            }

        }

        else {
            for (Balance balance: baseData.mBalances) {
                if (balance.symbol.equals(mainDenom(baseChain))) {
                    BigDecimal amount = baseData.getAllMainAssetOld(balance.symbol);
                    BigDecimal btcValue = btcValue(baseData, balance.symbol, amount, mainDivideDecimal(baseChain));
                    totalValue = totalValue.add(btcValue);
                } else {

                }
            }

        }
        return totalValue;
    }

    public static SpannableString dpAllAssetValueUserCurrency(BaseChain baseChain, BaseData baseData) {
        BigDecimal totalValue = allAssetToUserCurrency(baseChain, baseData);
        final String formatted = baseData.getCurrencySymbol()  + " " + getDecimalFormat(3).format(totalValue);
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

        } else if (msg.type.equals(BaseConstant.COSMOS_MSG_TYPE_UNJAIL)) {
            result = BaseConstant.TX_TYPE_UNJAIL;

        } else if (msg.type.equals(BaseConstant.IRIS_MSG_TYPE_WITHDRAW_ALL)) {
            result = BaseConstant.TX_TYPE_IRIS_GET_REWARD_ALL;

        } else if (msg.type.equals(BaseConstant.IRIS_MSG_TYPE_ISSUE_TOKEN)) {
            result = BaseConstant.TX_TYPE_IRIS_ISSUE_TOKEN;

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

    public static String getPath(BaseChain chain, int position, boolean newBip, int customPath) {
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

        } else if (chain.equals(PERSIS_MAIN)) {
            return BaseConstant.KEY_PERSIS_PATH + String.valueOf(position);

        } else if (chain.equals(CRYPTO_MAIN)) {
            return BaseConstant.KEY_CRYPTO_PATH + String.valueOf(position);

        } else if (chain.equals(MEDI_MAIN) || chain.equals(MEDI_TEST)) {
            return KEY_MEDI_PATH + String.valueOf(position);

        } else if (chain.equals(ALTHEA_TEST)) {
            return KEY_ALTHEA_PATH + String.valueOf(position);

        } else if (chain.equals(FETCHAI_MAIN)) {
            if (customPath == 1) {
                return KEY_ETH_NON_LEDGER_PATH + String.valueOf(position);
            } else if (customPath == 2) {
                return KEY_ETH_LEDGER_LIVE_PATH_1 + String.valueOf(position) + KEY_ETH_LEDGER_LIVE_PATH_2;
            } else if (customPath == 3) {
                return KEY_ETH_LEDGER_LEGACY_PATH + String.valueOf(position);
            }
            return BaseConstant.KEY_FETCH_BASE_PATH + String.valueOf(position);

        } else if (chain.equals(INJ_MAIN)) {
            return KEY_INJ_PATH + String.valueOf(position);

        } else if (chain.equals(BITSONG_MAIN)) {
            return KEY_BITSONG_PATH + String.valueOf(position);

        } else if (chain.equals(DESMOS_MAIN)) {
            return KEY_DESMOS_PATH + String.valueOf(position);

        } else if (chain.equals(LUM_MAIN)) {
            return KEY_LUM_PATH + String.valueOf(position);

        } else {
            return BaseConstant.KEY_PATH + String.valueOf(position);

        }
    }


    public static DecimalFormat getDecimalFormat(int cnt) {
        NumberFormat formatter = NumberFormat.getNumberInstance(Locale.US);
        DecimalFormat decimalformat = (DecimalFormat)formatter;
        decimalformat.setRoundingMode(RoundingMode.DOWN);
        switch (cnt) {
            case 0:
                decimalformat.applyLocalizedPattern("###,###,###,###,###,###,###,##0");
                break;
            case 1:
                decimalformat.applyLocalizedPattern("###,###,###,###,###,###,###,##0.0");
                break;
            case 2:
                decimalformat.applyLocalizedPattern("###,###,###,###,###,###,###,##0.00");
                break;
            case 3:
                decimalformat.applyLocalizedPattern("###,###,###,###,###,###,###,##0.000");
                break;
            case 4:
                decimalformat.applyLocalizedPattern("###,###,###,###,###,###,###,##0.0000");
                break;
            case 5:
                decimalformat.applyLocalizedPattern("###,###,###,###,###,###,###,##0.00000");
                break;
            case 6:
                decimalformat.applyLocalizedPattern("###,###,###,###,###,###,###,##0.000000");
                break;
            case 7:
                decimalformat.applyLocalizedPattern("###,###,###,###,###,###,###,##0.0000000");
                break;
            case 8:
                decimalformat.applyLocalizedPattern("###,###,###,###,###,###,###,##0.00000000");
                break;
            case 9:
                decimalformat.applyLocalizedPattern("###,###,###,###,###,###,###,##0.000000000");
                break;
            case 10:
                decimalformat.applyLocalizedPattern("###,###,###,###,###,###,###,##0.0000000000");
                break;
            case 11:
                decimalformat.applyLocalizedPattern("###,###,###,###,###,###,###,##0.00000000000");
                break;
            case 12:
                decimalformat.applyLocalizedPattern("###,###,###,###,###,###,###,##0.000000000000");
                break;
            case 13:
                decimalformat.applyLocalizedPattern("###,###,###,###,###,###,###,##0.0000000000000");
                break;
            case 14:
                decimalformat.applyLocalizedPattern("###,###,###,###,###,###,###,##0.00000000000000");
                break;
            case 15:
                decimalformat.applyLocalizedPattern("###,###,###,###,###,###,###,##0.000000000000000");
                break;
            case 16:
                decimalformat.applyLocalizedPattern("###,###,###,###,###,###,###,##0.0000000000000000");
                break;
            case 17:
                decimalformat.applyLocalizedPattern("###,###,###,###,###,###,###,##0.00000000000000000");
                break;
            case 18:
                decimalformat.applyLocalizedPattern("###,###,###,###,###,###,###,##0.000000000000000000");
                break;

            default:
                decimalformat.applyLocalizedPattern("###,###,###,###,###,###,###,##0.000000");
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

    public static String getUnbondTime(Context c, BaseData baseData, BaseChain baseChain) {
        String result = "??";
        try {
            if (baseData != null && baseData.mChainParam != null) {
                Calendar calendar = Calendar.getInstance();
                calendar.add(Calendar.DATE, baseData.mChainParam.getUnbonding(baseChain));
                SimpleDateFormat unbondFormat = new SimpleDateFormat(c.getString(R.string.str_dp_time_format2));
                result = unbondFormat.format(calendar.getTimeInMillis());
            }
        } catch (Exception e) {}
        return result + "   " + "(" + baseData.mChainParam.getUnbonding(baseChain) + c.getString(R.string.str_unbonding_days_after);
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

    public static long dateToLong(Context c, String rawValue) {
        long result = 0;
        try {
            SimpleDateFormat blockDateFormat = new SimpleDateFormat(c.getString(R.string.str_block_time_format));
            blockDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            result = blockDateFormat.parse(rawValue).getTime();
        } catch (Exception e) {};
        return result;
    }

    public static long dateToLong2(Context c, String rawValue) {
        long result = 0;
        try {
            SimpleDateFormat blockDateFormat = new SimpleDateFormat(c.getString(R.string.str_tx_time_format));
            blockDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            result = blockDateFormat.parse(rawValue).getTime();
        } catch (Exception e) {};
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

    public static String getTimeVoteformat(Context c, String rawValue) {
        String result = "??";
        try {
            SimpleDateFormat blockDateFormat = new SimpleDateFormat(c.getString(R.string.str_vote_time_format));
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
        if (chain != null) {
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
            } else if (chain.equals(FETCHAI_MAIN)) {
                return c.getResources().getColor(R.color.colorFetch);
            } else if (chain.equals(CRYPTO_MAIN)) {
                return c.getResources().getColor(R.color.colorCryto);
            } else if (chain.equals(SIF_MAIN)) {
                return c.getResources().getColor(R.color.colorSif);
            } else if (chain.equals(KI_MAIN)) {
                return c.getResources().getColor(R.color.colorKi);
            } else if (chain.equals(OSMOSIS_MAIN)) {
                return c.getResources().getColor(R.color.colorOsmosis);
            } else if (chain.equals(RIZON_MAIN) || chain.equals(RIZON_TEST)) {
                return c.getResources().getColor(R.color.colorRizon);
            } else if (chain.equals(MEDI_MAIN) || chain.equals(MEDI_TEST)) {
                return c.getResources().getColor(R.color.colorMedi);
            } else if (chain.equals(EMONEY_MAIN)) {
                return c.getResources().getColor(R.color.colorEmoney);
            } else if (chain.equals(JUNO_MAIN)) {
                return c.getResources().getColor(R.color.colorJuno);
            } else if (chain.equals(REGEN_MAIN)) {
                return c.getResources().getColor(R.color.colorRegen);
            } else if (chain.equals(BITCANNA_MAIN)) {
                return c.getResources().getColor(R.color.colorBitcanna);
            } else if (chain.equals(ALTHEA_MAIN) || chain.equals(ALTHEA_TEST)) {
                return c.getResources().getColor(R.color.colorAlthea);
            } else if (chain.equals(STARGAZE_MAIN)) {
                return c.getResources().getColor(R.color.colorStargaze);
            } else if (chain.equals(GRABRIDGE_MAIN)) {
                return c.getResources().getColor(R.color.colorGraBridge);
            } else if (chain.equals(COMDEX_MAIN)) {
                return c.getResources().getColor(R.color.colorComdex);
            } else if (chain.equals(INJ_MAIN)) {
                return c.getResources().getColor(R.color.colorInj);
            } else if (chain.equals(BITSONG_MAIN)) {
                return c.getResources().getColor(R.color.colorBitsong);
            } else if (chain.equals(DESMOS_MAIN)) {
                return c.getResources().getColor(R.color.colorDesmos);
            } else if (chain.equals(LUM_MAIN)) {
                return c.getResources().getColor(R.color.colorLum);
            } else if (chain.equals(UMEE_TEST)) {
                return c.getResources().getColor(R.color.colorUmee);
            } else if (chain.equals(AXELAR_TEST)) {
                return c.getResources().getColor(R.color.colorAxelar);
            }
        }
        return c.getResources().getColor(R.color.colorGray0);
    }

    public static int getChainBgColor(Context c, BaseChain chain) {
        if (chain != null) {
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
            } else if (chain.equals(FETCHAI_MAIN)) {
                return c.getResources().getColor(R.color.colorTransBgFetch);
            } else if (chain.equals(CRYPTO_MAIN)) {
                return c.getResources().getColor(R.color.colorTransBgCryto);
            } else if (chain.equals(SIF_MAIN)) {
                return c.getResources().getColor(R.color.colorTransBgSif);
            } else if (chain.equals(KI_MAIN)) {
                return c.getResources().getColor(R.color.colorTransBgKi);
            } else if (chain.equals(OSMOSIS_MAIN)) {
                return c.getResources().getColor(R.color.colorTransBgOsmosis);
            } else if (chain.equals(RIZON_MAIN) || chain.equals(RIZON_TEST)) {
                return c.getResources().getColor(R.color.colorTransBgRizon);
            } else if (chain.equals(MEDI_MAIN) || chain.equals(MEDI_TEST)) {
                return c.getResources().getColor(R.color.colorTransBgMedi);
            } else if (chain.equals(EMONEY_MAIN)) {
                return c.getResources().getColor(R.color.colorTransBgEmoney);
            } else if (chain.equals(JUNO_MAIN)) {
                return c.getResources().getColor(R.color.colorTransBgJuno);
            } else if (chain.equals(REGEN_MAIN)) {
                return c.getResources().getColor(R.color.colorTransBgRegen);
            } else if (chain.equals(BITCANNA_MAIN)) {
                return c.getResources().getColor(R.color.colorTransBgBitcanna);
            } else if (chain.equals(ALTHEA_MAIN) || chain.equals(ALTHEA_TEST)) {
                return c.getResources().getColor(R.color.colorTransBgAlthea);
            } else if (chain.equals(STARGAZE_MAIN)) {
                return c.getResources().getColor(R.color.colorTransBgStargaze);
            } else if (chain.equals(GRABRIDGE_MAIN)) {
                return c.getResources().getColor(R.color.colorTransBgGraBridge);
            } else if (chain.equals(COMDEX_MAIN)) {
                return c.getResources().getColor(R.color.colorTransBgComdex);
            } else if (chain.equals(INJ_MAIN)) {
                return c.getResources().getColor(R.color.colorTransBgInj);
            } else if (chain.equals(BITSONG_MAIN)) {
                return c.getResources().getColor(R.color.colorTransBgBitsong);
            } else if (chain.equals(DESMOS_MAIN)) {
                return c.getResources().getColor(R.color.colorTransBgDesmos);
            } else if (chain.equals(LUM_MAIN)) {
                return c.getResources().getColor(R.color.colorTransBgLum);
            }

            else if (chain.equals(UMEE_TEST)) {
                return c.getResources().getColor(R.color.colorTransBgUmee);
            } else if (chain.equals(AXELAR_TEST)) {
                return c.getResources().getColor(R.color.colorTransBgAxelar);
            }
        }
        return c.getResources().getColor(R.color.colorTransBg);
    }

    public static ColorStateList getTabColor(Context c, BaseChain chain) {
        if (chain != null) {
            if (chain.equals(COSMOS_MAIN) || chain.equals(COSMOS_TEST)) {
                return c.getResources().getColorStateList(R.color.color_tab_myvalidator);
            } else if (chain.equals(IRIS_MAIN) || chain.equals(IRIS_TEST)) {
                return c.getResources().getColorStateList(R.color.color_tab_myvalidator_iris);
            } else if (chain.equals(KAVA_MAIN) || chain.equals(KAVA_TEST)) {
                return c.getResources().getColorStateList(R.color.color_tab_myvalidator_kava);
            } else if (chain.equals(BAND_MAIN)) {
                return c.getResources().getColorStateList(R.color.color_tab_myvalidator_band);
            } else if (chain.equals(IOV_MAIN) || chain.equals(IOV_TEST)) {
                return c.getResources().getColorStateList(R.color.color_tab_myvalidator_iov);
            } else if (chain.equals(OKEX_MAIN) || chain.equals(OK_TEST)) {
                return c.getResources().getColorStateList(R.color.color_tab_myvalidator_ok);
            } else if (chain.equals(CERTIK_MAIN) || chain.equals(CERTIK_TEST)) {
                return c.getResources().getColorStateList(R.color.color_tab_myvalidator_certik);
            } else if (chain.equals(SECRET_MAIN)) {
                return c.getResources().getColorStateList(R.color.color_tab_myvalidator_secret);
            } else if (chain.equals(AKASH_MAIN)) {
                return c.getResources().getColorStateList(R.color.color_tab_myvalidator_akash);
            } else if (chain.equals(PERSIS_MAIN)) {
                return c.getResources().getColorStateList(R.color.color_tab_myvalidator_persis);
            } else if (chain.equals(SENTINEL_MAIN)) {
                return c.getResources().getColorStateList(R.color.color_tab_myvalidator_sentinel);
            } else if (chain.equals(FETCHAI_MAIN)) {
                return c.getResources().getColorStateList(R.color.color_tab_myvalidator_fetch);
            } else if (chain.equals(CRYPTO_MAIN)) {
                return c.getResources().getColorStateList(R.color.color_tab_myvalidator_cryto);
            } else if (chain.equals(SIF_MAIN)) {
                return c.getResources().getColorStateList(R.color.color_tab_myvalidator_sif);
            } else if (chain.equals(KI_MAIN)) {
                return c.getResources().getColorStateList(R.color.color_tab_myvalidator_ki);
            } else if (chain.equals(OSMOSIS_MAIN)) {
                return c.getResources().getColorStateList(R.color.color_tab_myvalidator_osmosis);
            } else if (chain.equals(RIZON_MAIN) || chain.equals(RIZON_TEST)) {
                return c.getResources().getColorStateList(R.color.color_tab_myvalidator_rizon);
            } else if (chain.equals(MEDI_MAIN) || chain.equals(MEDI_TEST)) {
                return c.getResources().getColorStateList(R.color.color_tab_myvalidator_med);
            } else if (chain.equals(EMONEY_MAIN)) {
                return c.getResources().getColorStateList(R.color.color_tab_myvalidator_emoney);
            } else if (chain.equals(JUNO_MAIN)) {
                return c.getResources().getColorStateList(R.color.color_tab_myvalidator_juno);
            } else if (chain.equals(REGEN_MAIN)) {
                return c.getResources().getColorStateList(R.color.color_tab_myvalidator_regen);
            } else if (chain.equals(BITCANNA_MAIN)) {
                return c.getResources().getColorStateList(R.color.color_tab_myvalidator_bitcanna);
            } else if (chain.equals(ALTHEA_MAIN) || chain.equals(ALTHEA_TEST)) {
                return c.getResources().getColorStateList(R.color.color_tab_myvalidator_althea);
            } else if (chain.equals(STARGAZE_MAIN)) {
                return c.getResources().getColorStateList(R.color.color_tab_myvalidator_stargaze);
            } else if (chain.equals(GRABRIDGE_MAIN)) {
                return c.getResources().getColorStateList(R.color.color_tab_myvalidator_grabridge);
            } else if (chain.equals(COMDEX_MAIN)) {
                return c.getResources().getColorStateList(R.color.color_tab_myvalidator_comdex);
            } else if (chain.equals(INJ_MAIN)) {
                return c.getResources().getColorStateList(R.color.color_tab_myvalidator_inj);
            } else if (chain.equals(BITSONG_MAIN)) {
                return c.getResources().getColorStateList(R.color.color_tab_myvalidator_bitsong);
            } else if (chain.equals(DESMOS_MAIN)) {
                return c.getResources().getColorStateList(R.color.color_tab_myvalidator_desmos);
            } else if (chain.equals(LUM_MAIN)) {
                return c.getResources().getColorStateList(R.color.color_tab_myvalidator_lum);
            } else if (chain.equals(UMEE_TEST)) {
                return c.getResources().getColorStateList(R.color.color_tab_myvalidator_umee);
            } else if (chain.equals(AXELAR_TEST)) {
                return c.getResources().getColorStateList(R.color.color_tab_myvalidator_axelar);
            }
        }
        return c.getResources().getColorStateList(R.color.color_tab_myvalidator);
    }

    public static ColorStateList getChainTintColor(Context c, BaseChain chain) {
        if (chain != null) {
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
            } else if (chain.equals(FETCHAI_MAIN)) {
                return c.getResources().getColorStateList(R.color.colorFetch);
            } else if (chain.equals(CRYPTO_MAIN)) {
                return c.getResources().getColorStateList(R.color.colorCryto);
            } else if (chain.equals(SIF_MAIN)) {
                return c.getResources().getColorStateList(R.color.colorSif);
            } else if (chain.equals(KI_MAIN)) {
                return c.getResources().getColorStateList(R.color.colorKi);
            } else if (chain.equals(OSMOSIS_MAIN)) {
                return c.getResources().getColorStateList(R.color.colorOsmosis);
            } else if (chain.equals(RIZON_MAIN) || chain.equals(RIZON_TEST)) {
                return c.getResources().getColorStateList(R.color.colorRizon);
            } else if (chain.equals(MEDI_MAIN) || chain.equals(MEDI_TEST)) {
                return c.getResources().getColorStateList(R.color.colorMedi);
            } else if (chain.equals(EMONEY_MAIN)) {
                return c.getResources().getColorStateList(R.color.colorEmoney);
            } else if (chain.equals(JUNO_MAIN)) {
                return c.getResources().getColorStateList(R.color.colorJuno);
            } else if (chain.equals(REGEN_MAIN)) {
                return c.getResources().getColorStateList(R.color.colorRegen);
            } else if (chain.equals(BITCANNA_MAIN)) {
                return c.getResources().getColorStateList(R.color.colorBitcanna);
            } else if (chain.equals(ALTHEA_MAIN) || chain.equals(ALTHEA_TEST)) {
                return c.getResources().getColorStateList(R.color.colorAlthea);
            } else if (chain.equals(STARGAZE_MAIN)) {
                return c.getResources().getColorStateList(R.color.colorStargaze);
            } else if (chain.equals(GRABRIDGE_MAIN)) {
                return c.getResources().getColorStateList(R.color.colorGraBridge);
            } else if (chain.equals(COMDEX_MAIN)) {
                return c.getResources().getColorStateList(R.color.colorComdex);
            } else if (chain.equals(INJ_MAIN)) {
                return c.getResources().getColorStateList(R.color.colorInj);
            } else if (chain.equals(BITSONG_MAIN)) {
                return c.getResources().getColorStateList(R.color.colorBitsong);
            } else if (chain.equals(DESMOS_MAIN)) {
                return c.getResources().getColorStateList(R.color.colorDesmos);
            } else if (chain.equals(LUM_MAIN)) {
                return c.getResources().getColorStateList(R.color.colorLum);
            } else if (chain.equals(UMEE_TEST)) {
                return c.getResources().getColorStateList(R.color.colorUmee);
            } else if (chain.equals(AXELAR_TEST)) {
                return c.getResources().getColorStateList(R.color.colorAxelar);
            }
        }
        return c.getResources().getColorStateList(R.color.colorTransBg);
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

        } else if (BaseChain.getChain(chain).equals(FETCHAI_MAIN)) {
            textview.setTextColor(c.getResources().getColor(R.color.colorFetch));
            textview.setText(c.getString(R.string.s_fet));

        } else if (BaseChain.getChain(chain).equals(CRYPTO_MAIN)) {
            textview.setTextColor(c.getResources().getColor(R.color.colorCryto));
            textview.setText(c.getString(R.string.s_cro));

        } else if (BaseChain.getChain(chain).equals(SIF_MAIN)) {
            textview.setTextColor(c.getResources().getColor(R.color.colorSif));
            textview.setText(c.getString(R.string.s_sif));

        } else if (BaseChain.getChain(chain).equals(KI_MAIN)) {
            textview.setTextColor(c.getResources().getColor(R.color.colorKi));
            textview.setText(c.getString(R.string.s_ki));

        } else if (BaseChain.getChain(chain).equals(OSMOSIS_MAIN)) {
            textview.setTextColor(c.getResources().getColor(R.color.colorOsmosis));
            textview.setText(c.getString(R.string.s_osmosis));

        } else if (BaseChain.getChain(chain).equals(MEDI_MAIN) || BaseChain.getChain(chain).equals(MEDI_TEST)) {
            textview.setTextColor(c.getResources().getColor(R.color.colorMedi));
            textview.setText(c.getString(R.string.s_medi));

        } else if (BaseChain.getChain(chain).equals(EMONEY_MAIN)) {
            textview.setTextColor(c.getResources().getColor(R.color.colorEmoney));
            textview.setText(c.getString(R.string.s_emoney));

        } else if (BaseChain.getChain(chain).equals(RIZON_MAIN) || BaseChain.getChain(chain).equals(RIZON_TEST)) {
            textview.setTextColor(c.getResources().getColor(R.color.colorRizon));
            textview.setText(c.getString(R.string.s_rizon));

        } else if (BaseChain.getChain(chain).equals(JUNO_MAIN)) {
            textview.setTextColor(c.getResources().getColor(R.color.colorJuno));
            textview.setText(c.getString(R.string.s_juno));

        } else if (BaseChain.getChain(chain).equals(REGEN_MAIN)) {
            textview.setTextColor(c.getResources().getColor(R.color.colorRegen));
            textview.setText(c.getString(R.string.s_regen));

        } else if (BaseChain.getChain(chain).equals(BITCANNA_MAIN)) {
            textview.setTextColor(c.getResources().getColor(R.color.colorBitcanna));
            textview.setText(c.getString(R.string.s_bitcanna));

        } else if (BaseChain.getChain(chain).equals(ALTHEA_MAIN) || BaseChain.getChain(chain).equals(ALTHEA_TEST)) {
            textview.setTextColor(c.getResources().getColor(R.color.colorAlthea));
            textview.setText(c.getString(R.string.s_althea));

        } else if (BaseChain.getChain(chain).equals(STARGAZE_MAIN)) {
            textview.setTextColor(c.getResources().getColor(R.color.colorStargaze));
            textview.setText(c.getString(R.string.s_stargaze));

        } else if (BaseChain.getChain(chain).equals(GRABRIDGE_MAIN)) {
            textview.setTextColor(c.getResources().getColor(R.color.colorGraBridge));
            textview.setText(c.getString(R.string.s_grabridge));

        } else if (BaseChain.getChain(chain).equals(COMDEX_MAIN)) {
            textview.setTextColor(c.getResources().getColor(R.color.colorComdex));
            textview.setText(c.getString(R.string.s_comdex));

        } else if (BaseChain.getChain(chain).equals(INJ_MAIN)) {
            textview.setTextColor(c.getResources().getColor(R.color.colorInj));
            textview.setText(c.getString(R.string.s_inj));

        } else if (BaseChain.getChain(chain).equals(BITSONG_MAIN)) {
            textview.setTextColor(c.getResources().getColor(R.color.colorBitsong));
            textview.setText(c.getString(R.string.s_bitsong));

        } else if (BaseChain.getChain(chain).equals(DESMOS_MAIN)) {
            textview.setTextColor(c.getResources().getColor(R.color.colorDesmos));
            textview.setText(c.getString(R.string.s_desmos));

        } else if (BaseChain.getChain(chain).equals(LUM_MAIN)) {
            textview.setTextColor(c.getResources().getColor(R.color.colorLum));
            textview.setText(c.getString(R.string.s_lum));

        }

        else if (BaseChain.getChain(chain).equals(COSMOS_TEST)) {
            textview.setTextColor(c.getResources().getColor(R.color.colorAtom));
            textview.setText(c.getString(R.string.s_muon));

        } else if (BaseChain.getChain(chain).equals(IRIS_TEST)) {
            textview.setTextColor(c.getResources().getColor(R.color.colorIris));
            textview.setText(c.getString(R.string.s_bif));

        } else if (BaseChain.getChain(chain).equals(UMEE_TEST)) {
            textview.setTextColor(c.getResources().getColor(R.color.colorUmee));
            textview.setText(c.getString(R.string.s_umee));

        } else if (BaseChain.getChain(chain).equals(AXELAR_TEST)) {
            textview.setTextColor(c.getResources().getColor(R.color.colorAxelar));
            textview.setText(c.getString(R.string.s_axelar));

        }
    }

    public static String mainDenom(BaseChain chain) {
        if (chain.equals(COSMOS_MAIN)) {
            return TOKEN_ATOM;
        } else if(chain.equals(IRIS_MAIN)) {
            return TOKEN_IRIS;
        } else if(chain.equals(BNB_MAIN) || chain.equals(BNB_TEST)) {
            return TOKEN_BNB;
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
        } else if (chain.equals(FETCHAI_MAIN)) {
            return TOKEN_FET;
        } else if (chain.equals(CRYPTO_MAIN)) {
            return TOKEN_CRO;
        } else if (chain.equals(SIF_MAIN)) {
            return TOKEN_SIF;
        } else if (chain.equals(KI_MAIN)) {
            return TOKEN_KI;
        } else if (chain.equals(OSMOSIS_MAIN)) {
            return TOKEN_OSMOSIS;
        } else if (chain.equals(COSMOS_TEST)) {
            return TOKEN_COSMOS_TEST;
        } else if (chain.equals(IRIS_TEST)) {
            return TOKEN_IRIS_TEST;
        } else if (chain.equals(IOV_TEST)) {
            return TOKEN_IOV_TEST;
        } else if (chain.equals(RIZON_MAIN) || chain.equals(RIZON_TEST)) {
            return TOKEN_RIZON;
        } else if (chain.equals(MEDI_MAIN) || chain.equals(MEDI_TEST)) {
            return TOKEN_MEDI;
        } else if (chain.equals(EMONEY_MAIN)) {
            return TOKEN_NGM;
        } else if (chain.equals(JUNO_MAIN)) {
            return TOKEN_JUNO;
        } else if (chain.equals(REGEN_MAIN)) {
            return TOKEN_REGEN;
        } else if (chain.equals(BITCANNA_MAIN)) {
            return TOKEN_BITCANNA;
        } else if (chain.equals(ALTHEA_MAIN) || chain.equals(ALTHEA_TEST)) {
            return TOKEN_ALTHEA;
        } else if (chain.equals(STARGAZE_MAIN)) {
            return TOKEN_STARGAZE;
        } else if (chain.equals(GRABRIDGE_MAIN)) {
            return TOKEN_GRABRIDGE;
        } else if (chain.equals(COMDEX_MAIN)) {
            return TOKEN_COMDEX;
        } else if (chain.equals(INJ_MAIN)) {
            return TOKEN_INJ;
        } else if (chain.equals(BITSONG_MAIN)) {
            return TOKEN_BITSONG;
        } else if (chain.equals(DESMOS_MAIN)) {
            return TOKEN_DESMOS;
        } else if (chain.equals(LUM_MAIN)) {
            return TOKEN_LUM;
        } else if (chain.equals(UMEE_TEST)) {
            return TOKEN_UMEE;
        } else if (chain.equals(AXELAR_TEST)) {
            return TOKEN_AXELAR;
        }
        return "";
    }

    public static void getStakingTokenImg(BaseChain baseChain, ImageView imageView) {
        Picasso.get().cancelRequest(imageView);
        if (baseChain != null) {
            if (baseChain.equals(COSMOS_MAIN) || baseChain.equals(COSMOS_TEST)) {
                imageView.setImageResource(R.drawable.atom_ic);
            } else if (baseChain.equals(IRIS_MAIN) || baseChain.equals(IRIS_TEST)) {
                imageView.setImageResource(R.drawable.iris_toket_img);
            } else if (baseChain.equals(BNB_MAIN) || baseChain.equals(BNB_TEST)) {
                imageView.setImageResource(R.drawable.bnb_token_img);
            } else if (baseChain.equals(OKEX_MAIN) || baseChain.equals(OK_TEST)) {
                imageView.setImageResource(R.drawable.okex_token_img);
            } else if (baseChain.equals(AKASH_MAIN)) {
                imageView.setImageResource(R.drawable.akash_token_img);
            } else if (baseChain.equals(KAVA_MAIN)) {
                imageView.setImageResource(R.drawable.kava_token_img);
            } else if (baseChain.equals(BAND_MAIN)) {
                imageView.setImageResource(R.drawable.band_token_img);
            } else if (baseChain.equals(IOV_MAIN)) {
                imageView.setImageResource(R.drawable.token_starname);
            } else if (baseChain.equals(SECRET_MAIN)) {
                imageView.setImageResource(R.drawable.tokensecret);
            } else if (baseChain.equals(CERTIK_MAIN) || baseChain.equals(CERTIK_TEST)) {
                imageView.setImageResource(R.drawable.certik_token_img);
            } else if (baseChain.equals(PERSIS_MAIN)) {
                imageView.setImageResource(R.drawable.tokenpersistence);
            } else if (baseChain.equals(SENTINEL_MAIN)) {
                imageView.setImageResource(R.drawable.tokensentinel);
            } else if (baseChain.equals(FETCHAI_MAIN)) {
                imageView.setImageResource(R.drawable.tokenfetchai);
            } else if (baseChain.equals(CRYPTO_MAIN)) {
                imageView.setImageResource(R.drawable.tokencrypto);
            } else if (baseChain.equals(SIF_MAIN)) {
                imageView.setImageResource(R.drawable.tokensifchain);
            } else if (baseChain.equals(KI_MAIN)) {
                imageView.setImageResource(R.drawable.token_kifoundation);
            } else if (baseChain.equals(OSMOSIS_MAIN)) {
                imageView.setImageResource(R.drawable.token_osmosis);
            } else if (baseChain.equals(MEDI_MAIN)) {
                imageView.setImageResource(R.drawable.tokenmedibloc);
            } else if (baseChain.equals(EMONEY_MAIN)) {
                imageView.setImageResource(R.drawable.token_emoney);
            } else if (baseChain.equals(RIZON_MAIN) || baseChain.equals(RIZON_TEST)) {
                imageView.setImageResource(R.drawable.token_rizon);
            } else if (baseChain.equals(JUNO_MAIN)) {
                imageView.setImageResource(R.drawable.token_juno);
            } else if (baseChain.equals(REGEN_MAIN)) {
                imageView.setImageResource(R.drawable.token_regen);
            } else if (baseChain.equals(BITCANNA_MAIN)) {
                imageView.setImageResource(R.drawable.token_bitcanna);
            } else if (baseChain.equals(ALTHEA_MAIN) || baseChain.equals(ALTHEA_TEST)) {
                imageView.setImageResource(R.drawable.token_althea);
            } else if (baseChain.equals(STARGAZE_MAIN)) {
                imageView.setImageResource(R.drawable.token_stargaze);
            } else if (baseChain.equals(GRABRIDGE_MAIN)) {
                imageView.setImageResource(R.drawable.token_gravitybridge);
            } else if (baseChain.equals(COMDEX_MAIN)) {
                imageView.setImageResource(R.drawable.token_comdex);
            } else if (baseChain.equals(INJ_MAIN)) {
                imageView.setImageResource(R.drawable.token_injective);
            } else if (baseChain.equals(BITSONG_MAIN)) {
                imageView.setImageResource(R.drawable.token_bitsong);
            } else if (baseChain.equals(DESMOS_MAIN)) {
                imageView.setImageResource(R.drawable.token_desmos);
            } else if (baseChain.equals(LUM_MAIN)) {
                imageView.setImageResource(R.drawable.token_lum);
            } else if (baseChain.equals(UMEE_TEST)) {
                imageView.setImageResource(R.drawable.token_umee);
            } else if (baseChain.equals(AXELAR_TEST)) {
                imageView.setImageResource(R.drawable.token_axelar);
            }
        } else {
            imageView.setImageResource(R.drawable.token_ic);
        }
    }

    public static int tokenDivideDecimal(BaseData baseData, BaseChain baseChain, String denom) {
        String mainDenom = mainDenom(baseChain);
        if (isGRPC(baseChain)) {
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
            }
        } else {
            return 6;
        }
        return 6;
    }

    public static int mainDivideDecimal(BaseChain chain) {
        if (chain.equals(BNB_MAIN) || chain.equals(BNB_TEST)) {
            return 0;
        } else if (chain.equals(OKEX_MAIN) || chain.equals(OK_TEST)) {
            return 0;
        } else if (chain.equals(FETCHAI_MAIN) || chain.equals(SIF_MAIN) || chain.equals(INJ_MAIN)) {
            return 18;
        } else if (chain.equals(CRYPTO_MAIN)) {
            return 8;
        } else {
            return 6;
        }
    }

    public static int mainDisplayDecimal(BaseChain chain) {
        if (chain.equals(BNB_MAIN) || chain.equals(BNB_TEST)) {
            return 8;
        } else if (chain.equals(OKEX_MAIN) || chain.equals(OK_TEST)) {
            return 18;
        } else if (chain.equals(FETCHAI_MAIN) || chain.equals(SIF_MAIN) || chain.equals(INJ_MAIN)) {
            return 18;
        } else if (chain.equals(CRYPTO_MAIN)) {
            return 8;
        } else {
            return 6;
        }
    }

    public static int mainDivideDecimal(String denom) {
        if (denom.equals(TOKEN_BNB)) {
            return 8;
        } else if (denom.equals(TOKEN_OK)) {
            return 18;
        } else if (denom.equals(TOKEN_FET) || denom.equals(TOKEN_SIF) || denom.equals(TOKEN_INJ)) {
            return 18;
        } else if (denom.equals(TOKEN_CRO)) {
            return 8;
        } else {
            return 6;
        }
    }

    public static int mainDisplayDecimal(String denom) {
        if (denom.equals(TOKEN_BNB)) {
            return 8;
        } else if (denom.equals(TOKEN_OK)) {
            return 18;
        } else if (denom.equals(TOKEN_FET) || denom.equals(TOKEN_SIF) || denom.equals(TOKEN_INJ)) {
            return 18;
        } else if (denom.equals(TOKEN_CRO)) {
            return 8;
        } else {
            return 6;
        }
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
            if (imgView != null) imgView.setImageDrawable(c.getResources().getDrawable(R.drawable.chain_starname));
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
        } else if (basechain.equals(SENTINEL_MAIN)) {
            return SENTINEL_VAL_URL + opAddress + ".png";
        } else if (basechain.equals(PERSIS_MAIN)) {
            return PERSIS_VAL_URL + opAddress + ".png";
        } else if (basechain.equals(CRYPTO_MAIN)) {
            return CRYPTO_VAL_URL + opAddress + ".png";
        } else if (basechain.equals(OSMOSIS_MAIN)) {
            return OSMOSIS_VAL_URL + opAddress + ".png";
        } else if (basechain.equals(IOV_MAIN) || basechain.equals(IOV_TEST)) {
            return IOV_VAL_URL + opAddress + ".png";
        } else if (basechain.equals(SIF_MAIN)) {
            return SIF_VAL_URL + opAddress + ".png";
        } else if (basechain.equals(CERTIK_MAIN)) {
            return CERTIK_VAL_URL + opAddress + ".png";
        } else if (basechain.equals(MEDI_MAIN) || basechain.equals(MEDI_TEST)) {
            return MEDI_VAL_URL + opAddress + ".png";
        } else if (basechain.equals(EMONEY_MAIN)) {
            return EMONEY_VAL_URL + opAddress + ".png";
        } else if (basechain.equals(FETCHAI_MAIN)) {
            return FETCH_VAL_URL + opAddress + ".png";
        } else if (basechain.equals(BAND_MAIN)) {
            return BAND_VAL_URL + opAddress + ".png";
        } else if (basechain.equals(RIZON_MAIN) || basechain.equals(RIZON_TEST)) {
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
        } else if (basechain.equals(UMEE_TEST)) {
            return UMEE_VAL_URL + opAddress + ".png";
        } else if (basechain.equals(AXELAR_TEST)) {
            return AXELAR_VAL_URL + opAddress + ".png";
        }

        else if (basechain.equals(KAVA_MAIN)) {
            return KAVA_VAL_URL + opAddress + ".png";
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

    public static void getProposalStatus(Context c, ResProposal proposal, ImageView statusImg, TextView status) {
        if (proposal != null) {
            if (proposal.proposal_status.equalsIgnoreCase("PROPOSAL_STATUS_DEPOSIT_PERIOD") || proposal.proposal_status.equalsIgnoreCase("DepositPeriod")) {
                statusImg.setImageDrawable(c.getResources().getDrawable(R.drawable.ic_deposit_img));
                status.setText("DepositPeriod");

            } else if (proposal.proposal_status.equalsIgnoreCase("PROPOSAL_STATUS_VOTING_PERIOD") ||
                    proposal.proposal_status.equalsIgnoreCase("PROPOSAL_STATUS_CERTIFIER_VOTING_PERIOD") ||
                    proposal.proposal_status.equalsIgnoreCase("PROPOSAL_STATUS_VALIDATOR_VOTING_PERIOD") ||
                    proposal.proposal_status.equalsIgnoreCase("VotingPeriod")) {
                statusImg.setImageDrawable(c.getResources().getDrawable(R.drawable.ic_voting_img));
                status.setText("VotingPeriod");

            } else if (proposal.proposal_status.equalsIgnoreCase("PROPOSAL_STATUS_REJECTED") || proposal.proposal_status.equalsIgnoreCase("Rejected")) {
                statusImg.setImageDrawable(c.getResources().getDrawable(R.drawable.ic_rejected_img));
                status.setText("Rejected");
            } else if (proposal.proposal_status.equalsIgnoreCase("PROPOSAL_STATUS_PASSED") || proposal.proposal_status.equalsIgnoreCase("Passed")) {
                statusImg.setImageDrawable(c.getResources().getDrawable(R.drawable.ic_passed_img));
                status.setText("Passed");
            }
        }
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
