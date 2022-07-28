package wannabit.io.cosmostaion.utils;

import static android.text.Spanned.SPAN_INCLUSIVE_INCLUSIVE;
import static wannabit.io.cosmostaion.base.BaseChain.AKASH_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.ALTHEA_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.ASSETMANTLE_MAIN;
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
import static wannabit.io.cosmostaion.base.BaseChain.CRESCENT_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.CRESCENT_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.CRYPTO_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.CUDOS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.DESMOS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.EMONEY_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.EVMOS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.FETCHAI_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.GRABRIDGE_MAIN;
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
import static wannabit.io.cosmostaion.base.BaseChain.NYX_MAIN;
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
import static wannabit.io.cosmostaion.base.BaseChain.STATION_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.UMEE_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.getChain;
import static wannabit.io.cosmostaion.base.BaseChain.isGRPC;
import static wannabit.io.cosmostaion.base.BaseConstant.*;

import android.content.Context;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.RelativeSizeSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import com.google.common.collect.Lists;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cosmos.base.abci.v1beta1.Abci;
import cosmos.base.v1beta1.CoinOuterClass;
import cosmos.staking.v1beta1.Staking;
import cosmos.tx.v1beta1.ServiceOuterClass;
import cosmos.vesting.v1beta1.Vesting;
import osmosis.gamm.v1beta1.BalancerPool;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.base.chains.Binance;
import wannabit.io.cosmostaion.base.chains.ChainConfig;
import wannabit.io.cosmostaion.base.chains.ChainFactory;
import wannabit.io.cosmostaion.base.chains.Crescent;
import wannabit.io.cosmostaion.base.chains.Emoney;
import wannabit.io.cosmostaion.base.chains.Kava;
import wannabit.io.cosmostaion.base.chains.Nyx;
import wannabit.io.cosmostaion.base.chains.Okc;
import wannabit.io.cosmostaion.base.chains.Osmosis;
import wannabit.io.cosmostaion.dao.Assets;
import wannabit.io.cosmostaion.dao.Balance;
import wannabit.io.cosmostaion.dao.BnbToken;
import wannabit.io.cosmostaion.dao.ChainParam;
import wannabit.io.cosmostaion.dao.Cw20Assets;
import wannabit.io.cosmostaion.dao.IbcToken;
import wannabit.io.cosmostaion.dao.OkTicker;
import wannabit.io.cosmostaion.dao.OkToken;
import wannabit.io.cosmostaion.dao.Price;
import wannabit.io.cosmostaion.model.type.BnbHistory;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.network.res.ResProposal;

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

    public static String getDpSymbol(BaseData baseData, ChainConfig chainConfig, String denom) {
        if (chainConfig == null || denom == null || denom.isEmpty()) { return "UNKNOWN"; }
        if (chainConfig.mainDenom().equalsIgnoreCase(denom)) {
            return chainConfig.mainSymbol();

        } else if (denom.startsWith("ibc/")) {
            IbcToken ibcToken = baseData.getIbcToken(denom.replaceAll("ibc/", ""));
            if (ibcToken != null && ibcToken.auth) {
                return ibcToken.display_denom.toUpperCase();
            } else {
                return "UNKNOWN";
            }

        } else if (chainConfig.baseChain().equals(KAVA_MAIN)) {
            if (denom.equalsIgnoreCase(Kava.KAVA_HARD_DENOM)) return "HARD";
            else if (denom.equalsIgnoreCase(Kava.KAVA_USDX_DENOM)) return "USDX";
            else if (denom.equalsIgnoreCase(Kava.KAVA_SWP_DENOM)) return "SWP";
            else if (denom.equalsIgnoreCase(TOKEN_HTLC_KAVA_BNB) || denom.equalsIgnoreCase(TOKEN_HTLC_BINANCE_BNB)) return "BNB";
            else if (denom.equalsIgnoreCase(TOKEN_HTLC_KAVA_XRPB) || denom.equalsIgnoreCase(TOKEN_HTLC_BINANCE_XRPB)) return "XPRB";
            else if (denom.equalsIgnoreCase(TOKEN_HTLC_KAVA_BUSD) || denom.equalsIgnoreCase(TOKEN_HTLC_BINANCE_BUSD)) return "BUSD";
            else if (denom.equalsIgnoreCase(TOKEN_HTLC_KAVA_BTCB) || denom.equalsIgnoreCase(TOKEN_HTLC_BINANCE_BTCB)) return "BTCB";

        } else if (chainConfig.baseChain().equals(OSMOSIS_MAIN)) {
            if (denom.equalsIgnoreCase(Osmosis.OSMOSIS_ION_DENOM)) return "ION";
            else if (denom.startsWith("gamm/pool/")) {
                String[] split = denom.split("/");
                return "GAMM-" + split[split.length - 1];
            }

        } else if (chainConfig.baseChain().equals(SIF_MAIN)) {
            if (denom.startsWith("c")) return denom.substring(1).toUpperCase();
            else denom.toUpperCase();

        } else if (chainConfig.baseChain().equals(CRESCENT_MAIN)) {
            if (denom.equalsIgnoreCase(Crescent.CRESCENT_BCRE_DENOM)) return "BCRE";
            else if (denom.startsWith("pool")) { return denom.toUpperCase(); }

        } else if (chainConfig.baseChain().equals(GRABRIDGE_MAIN)) {
            if (baseData.getAsset(denom) != null) return baseData.getAsset(denom).origin_symbol;
            else return denom.toUpperCase();

        } else if (chainConfig.baseChain().equals(INJ_MAIN)) {
            if (baseData.getAsset(denom) != null) return baseData.getAsset(denom).origin_symbol;
            else if (denom.startsWith("share")) return denom.toUpperCase();

        } else if (chainConfig.baseChain().equals(NYX_MAIN)) {
            if (denom.equalsIgnoreCase(Nyx.NYX_NYM_DENOM)) return "NYM";
            else return denom.toUpperCase();

        } else if (chainConfig.baseChain().equals(BNB_MAIN)) {
            if (baseData.getBnbToken(denom) != null) return baseData.getBnbToken(denom).original_symbol.toUpperCase();
            else return denom.toUpperCase();

        } else if (chainConfig.baseChain().equals(OKEX_MAIN)) {
            if (baseData.okToken(denom) != null) return baseData.okToken(denom).original_symbol.toUpperCase();
            else return denom.toUpperCase();
        }
        return denom.toUpperCase();
    }

    public static void setDpSymbol(Context c, BaseData baseData, ChainConfig chainConfig, String denom, TextView textView) {
        if (chainConfig == null || denom == null || denom.isEmpty()) return;
        textView.setText(getDpSymbol(baseData, chainConfig, denom));
        if (chainConfig.mainDenom().equalsIgnoreCase(denom)) {
            textView.setTextColor(ContextCompat.getColor(c, chainConfig.chainColor()));

        } else if (chainConfig.baseChain().equals(KAVA_MAIN)) {
            if (denom.equalsIgnoreCase(Kava.KAVA_HARD_DENOM)) textView.setTextColor(ContextCompat.getColor(c, R.color.color_hard));
            else if (denom.equalsIgnoreCase(Kava.KAVA_SWP_DENOM)) textView.setTextColor(ContextCompat.getColor(c, R.color.color_swp));
            else if (denom.equalsIgnoreCase(Kava.KAVA_USDX_DENOM)) textView.setTextColor(ContextCompat.getColor(c, R.color.color_usdx));
            else textView.setTextColor(ContextCompat.getColor(c, R.color.colorBlackDayNight));

        } else if (chainConfig.baseChain().equals(OSMOSIS_MAIN)) {
            if (denom.equalsIgnoreCase(Osmosis.OSMOSIS_ION_DENOM)) textView.setTextColor(ContextCompat.getColor(c, R.color.color_ion));
            else textView.setTextColor(ContextCompat.getColor(c, R.color.colorBlackDayNight));

        } else if (chainConfig.baseChain().equals(CRESCENT_MAIN)) {
            if (denom.equalsIgnoreCase(Crescent.CRESCENT_BCRE_DENOM)) textView.setTextColor(ContextCompat.getColor(c, R.color.color_bcre));
            else textView.setTextColor(ContextCompat.getColor(c, R.color.colorBlackDayNight));

        } else if (chainConfig.baseChain().equals(NYX_MAIN)) {
            if (denom.equalsIgnoreCase(Nyx.NYX_NYM_DENOM)) textView.setTextColor(ContextCompat.getColor(c, R.color.color_nym));
            else textView.setTextColor(ContextCompat.getColor(c, R.color.colorBlackDayNight));

        } else {
            textView.setTextColor(ContextCompat.getColor(c, R.color.colorBlackDayNight));
        }
    }

    public static int getDenomDecimal(BaseData baseData, ChainConfig chainConfig, String denom) {
        if (chainConfig == null || denom == null || denom.isEmpty()) return 6;
        if (denom.startsWith("ibc/")) {
            IbcToken ibcToken = baseData.getIbcToken(denom.replaceAll("ibc/", ""));
            return ibcToken.decimal;

        } else if (chainConfig.mainDenom().equalsIgnoreCase(denom)) {
            return chainConfig.decimal();

        } else if (chainConfig.baseChain().equals(OSMOSIS_MAIN)) {
            if (denom.equalsIgnoreCase(Osmosis.OSMOSIS_ION_DENOM)) return 6;
            else if (denom.startsWith("gamm/pool/")) return 18;
            else return 6;

        } else if (chainConfig.baseChain().equals(SIF_MAIN)) {
            if (baseData.getAsset(denom) != null) return baseData.getAsset(denom).decimal;
            else return 18;

        } else if (chainConfig.baseChain().equals(GRABRIDGE_MAIN)) {
            if (baseData.getAsset(denom) != null) return baseData.getAsset(denom).decimal;
            else return 18;

        } else if (chainConfig.baseChain().equals(KAVA_MAIN)) {
            if (denom.equalsIgnoreCase("btc")) return 8;
            else if (denom.equalsIgnoreCase("bnb")) return 8;
            else if (denom.equalsIgnoreCase("btcb") || denom.equalsIgnoreCase("hbtc")) return 8;
            else if (denom.equalsIgnoreCase("busd")) return 8;
            else if (denom.equalsIgnoreCase("xrpb") || denom.equalsIgnoreCase("xrbp")) return 8;
            else return 6;

        } else if (chainConfig.baseChain().equals(INJ_MAIN)) {
            if (denom.startsWith("share")) return 18;
            else if (denom.startsWith("peggy0x")) {
                if (baseData.getAsset(denom) != null) return baseData.getAsset(denom).decimal;
            } else {
                return 18;
            }

        } else if (chainConfig.baseChain().equals(CRESCENT_MAIN)) {
            if (denom.equalsIgnoreCase(Crescent.CRESCENT_BCRE_DENOM)) return 6;
            else if (denom.startsWith("pool")) return 12;

        }
        return chainConfig.decimal();
    }

    public static void setDpSymbolImg(BaseData baseData, ChainConfig chainConfig, String denom, ImageView imageView) {
        if (chainConfig == null || denom == null || denom.isEmpty()) {
            imageView.setImageResource(R.drawable.token_default);
        }

        if (chainConfig.mainDenom().equalsIgnoreCase(denom)) {
            imageView.setImageResource(chainConfig.mainDenomImg());

        } else if (denom.startsWith("ibc/")) {
            IbcToken ibcToken = baseData.getIbcToken(denom.replaceAll("ibc/", ""));
            if (ibcToken != null) {
                Picasso.get().load(ibcToken.moniker).fit().placeholder(R.drawable.token_default_ibc).error(R.drawable.token_default_ibc).into(imageView);
            }

        } else if (chainConfig.baseChain().equals(KAVA_MAIN)) {
            Picasso.get().load(Kava.KAVA_COIN_IMG_URL + denom + ".png").fit().placeholder(R.drawable.token_default).error(R.drawable.token_default).into(imageView);

        } else if (chainConfig.baseChain().equals(OSMOSIS_MAIN)) {
            if (denom.equalsIgnoreCase(Osmosis.OSMOSIS_ION_DENOM)) imageView.setImageResource(R.drawable.token_ion);
            else if (denom.startsWith("gamm/pool/")) imageView.setImageResource(R.drawable.token_pool);

        } else if (chainConfig.baseChain().equals(SIF_MAIN)) {
            if (baseData.getAsset(denom) != null) {
                Assets asset = baseData.getAsset(denom);
                Picasso.get().load(ASSET_IMG_URL + asset.logo).fit().placeholder(R.drawable.token_default).error(R.drawable.token_default).into(imageView);
            }

        } else if (chainConfig.baseChain().equals(CRESCENT_MAIN)) {
            if (denom.equalsIgnoreCase(Crescent.CRESCENT_BCRE_DENOM)) imageView.setImageResource(R.drawable.token_bcre);
            else if (denom.startsWith("pool")) imageView.setImageResource(R.drawable.token_crescentpool);

        } else if (chainConfig.baseChain().equals(EMONEY_MAIN)) {
            Picasso.get().load(Emoney.EMONEY_COIN_IMG_URL + denom + ".png").fit().placeholder(R.drawable.token_default).error(R.drawable.token_default).into(imageView);

        } else if (chainConfig.baseChain().equals(GRABRIDGE_MAIN)) {
            if (baseData.getAsset(denom) != null) {
                Assets asset = baseData.getAsset(denom);
                Picasso.get().load(ASSET_IMG_URL + asset.logo).fit().placeholder(R.drawable.token_default).error(R.drawable.token_default).into(imageView);
            }

        } else if (chainConfig.baseChain().equals(INJ_MAIN)) {
            if (denom.startsWith("share")) imageView.setImageResource(R.drawable.injectivepool_token);
            else if (baseData.getAsset(denom) != null) {
                Assets asset = baseData.getAsset(denom);
                Picasso.get().load(ASSET_IMG_URL + asset.logo).fit().placeholder(R.drawable.token_default).error(R.drawable.token_default).into(imageView);
            }

        } else if (chainConfig.baseChain().equals(NYX_MAIN)) {
            if (denom.equalsIgnoreCase(Nyx.NYX_NYM_DENOM)) imageView.setImageResource(R.drawable.token_nym);
            else imageView.setImageResource(R.drawable.token_default);

        } else if (chainConfig.baseChain().equals(BNB_MAIN)) {
            BnbToken bnbToken = baseData.getBnbToken(denom);
            if (bnbToken != null) {
                Picasso.get().load(Binance.BINANCE_COIN_IMG_URL + bnbToken.original_symbol + ".png").fit().placeholder(R.drawable.token_default).error(R.drawable.token_default).into(imageView);
            }

        } else if (chainConfig.baseChain().equals(OKEX_MAIN)) {
            OkToken okToken = baseData.okToken(denom);
            if (okToken != null) {
                Picasso.get().load(Okc.OKC_COIN_IMG_URL + okToken.original_symbol + ".png").placeholder(R.drawable.token_default).error(R.drawable.token_default).fit().into(imageView);
            }

        } else {
            imageView.setImageResource(R.drawable.token_default);
        }
    }

    public static void setDpCoin(Context c, BaseData baseData, ChainConfig chainConfig, Coin coin, TextView denomTv, TextView amountTv) {
        setDpCoin(c, baseData, chainConfig, coin.denom, coin.amount, denomTv, amountTv);
    }

    public static void setDpCoin(Context c, BaseData baseData, ChainConfig chainConfig, String denom, String amount, TextView denomTv, TextView amountTv) {
        if (chainConfig == null || denom == null || denom.isEmpty()) return;
        setDpSymbol(c, baseData, chainConfig, denom, denomTv);
        int divideDecimal = 6;
        int displayDecimal = 6;
        if (denom.startsWith("ibc/")) {
            IbcToken ibcToken = baseData.getIbcToken(denom.replaceAll("ibc/", ""));
            if (ibcToken != null && ibcToken.auth) {
                divideDecimal = getDenomDecimal(baseData, chainConfig, denom);
                displayDecimal = getDenomDecimal(baseData, chainConfig, denom);
            }
            amountTv.setText(getDpAmount2(c, new BigDecimal(amount), divideDecimal, displayDecimal));
        }

        if (chainConfig.baseChain().equals(BNB_MAIN) || chainConfig.baseChain().equals(OKEX_MAIN)) {
            divideDecimal = getDenomDecimal(baseData, chainConfig, denom);
            displayDecimal = mainDisplayDecimal(chainConfig.baseChain());
        } else {
            divideDecimal = getDenomDecimal(baseData, chainConfig, denom);
            displayDecimal = getDenomDecimal(baseData, chainConfig, denom);
        }
        amountTv.setText(getDpAmount2(c, new BigDecimal(amount), divideDecimal, displayDecimal));
    }

    public static void showCoinDp(Context c, BaseData baseData, Coin coin, TextView denomTv, TextView amountTv, BaseChain chain) {
        showCoinDp(c, baseData, coin.denom, coin.amount, denomTv, amountTv, chain);
    }

    public static void showCoinDp(Context c, BaseData baseData, String symbol, String amount, TextView denomTv, TextView amountTv, BaseChain chain) {
        if (isGRPC(chain) && symbol.startsWith("ibc")) {
            IbcToken ibcToken = baseData.getIbcToken(symbol.replaceAll("ibc/", ""));
            if (ibcToken != null && ibcToken.auth) {
                denomTv.setText(ibcToken.display_denom.toUpperCase());
                amountTv.setText(getDpAmount2(c, new BigDecimal(amount), ibcToken.decimal, ibcToken.decimal));

            } else {
                denomTv.setText("Unknown");
                amountTv.setText(getDpAmount2(c, new BigDecimal(amount), 6, 6));
            }
            denomTv.setTextColor(ContextCompat.getColor(c, R.color.colorBlackDayNight));

        } else if (chain.equals(COSMOS_MAIN)) {
            if (symbol.equals(TOKEN_ATOM)) {
                DpMainDenom(c, chain.getChain(), denomTv);
            } else {
                denomTv.setTextColor(ContextCompat.getColor(c, R.color.colorBlackDayNight));
                denomTv.setText(symbol.toUpperCase());
            }
            amountTv.setText(getDpAmount2(c, new BigDecimal(amount), 6, 6));

        } else if (chain.equals(IRIS_MAIN)) {
            if (symbol.equals(TOKEN_IRIS)) {
                DpMainDenom(c, chain.getChain(), denomTv);
            } else {
                denomTv.setTextColor(ContextCompat.getColor(c, R.color.colorBlackDayNight));
                denomTv.setText(symbol.toUpperCase());
            }
            amountTv.setText(getDpAmount2(c, new BigDecimal(amount), 6, 6));

        } else if (chain.equals(KAVA_MAIN)) {
            if (symbol.equals(TOKEN_KAVA)) {
                DpMainDenom(c, chain.getChain(), denomTv);
            } else if (symbol.equals(TOKEN_HARD)) {
                denomTv.setText(symbol.toUpperCase());
                denomTv.setTextColor(ContextCompat.getColor(c, R.color.color_hard));
            } else if (symbol.equals(TOKEN_USDX)) {
                denomTv.setText(symbol.toUpperCase());
                denomTv.setTextColor(ContextCompat.getColor(c, R.color.color_usdx));
            } else if (symbol.equals(TOKEN_SWP)) {
                denomTv.setText(symbol.toUpperCase());
                denomTv.setTextColor(ContextCompat.getColor(c, R.color.color_swp));
            } else {
                denomTv.setText(symbol.toUpperCase());
                denomTv.setTextColor(ContextCompat.getColor(c, R.color.colorBlackDayNight));
            }
            if (amountTv != null)
                amountTv.setText(getDpAmount2(c, new BigDecimal(amount), WUtil.getKavaCoinDecimal(baseData, symbol), WUtil.getKavaCoinDecimal(baseData, symbol)));

        } else if (chain.equals(IOV_MAIN)) {
            if (symbol.equals(TOKEN_IOV)) {
                DpMainDenom(c, chain.getChain(), denomTv);

            } else {
                denomTv.setTextColor(ContextCompat.getColor(c, R.color.colorBlackDayNight));
                denomTv.setText(symbol.toUpperCase());
            }
            amountTv.setText(getDpAmount2(c, new BigDecimal(amount), 6, 6));

        } else if (chain.equals(BNB_MAIN)) {
            if (symbol.equals(TOKEN_BNB)) {
                DpMainDenom(c, chain.getChain(), denomTv);
            } else {
                denomTv.setText(symbol.toUpperCase());
                denomTv.setTextColor(ContextCompat.getColor(c, R.color.colorBlackDayNight));
            }
            amountTv.setText(getDpAmount2(c, new BigDecimal(amount), 0, 8));

        } else if (chain.equals(BAND_MAIN)) {
            if (symbol.equals(TOKEN_BAND)) {
                DpMainDenom(c, chain.getChain(), denomTv);
            } else {
                denomTv.setText(symbol.toUpperCase());
                denomTv.setTextColor(ContextCompat.getColor(c, R.color.colorBlackDayNight));
            }
            amountTv.setText(getDpAmount2(c, new BigDecimal(amount), 6, 6));

        } else if (chain.equals(OKEX_MAIN)) {
            if (symbol.equals(TOKEN_OK)) {
                DpMainDenom(c, chain.getChain(), denomTv);
            } else {
                denomTv.setText(symbol.toUpperCase());
                denomTv.setTextColor(ContextCompat.getColor(c, R.color.colorBlackDayNight));
            }
            amountTv.setText(getDpAmount2(c, new BigDecimal(amount), 0, 18));

        } else if (chain.equals(CERTIK_MAIN)) {
            if (symbol.equals(TOKEN_CERTIK)) {
                DpMainDenom(c, chain.getChain(), denomTv);
            } else {
                denomTv.setText(symbol.toUpperCase());
                denomTv.setTextColor(ContextCompat.getColor(c, R.color.colorBlackDayNight));
            }
            amountTv.setText(getDpAmount2(c, new BigDecimal(amount), 6, 6));

        } else if (chain.equals(SECRET_MAIN)) {
            if (symbol.equals(TOKEN_SECRET)) {
                DpMainDenom(c, chain.getChain(), denomTv);
            } else {
                denomTv.setText(symbol.toUpperCase());
                denomTv.setTextColor(ContextCompat.getColor(c, R.color.colorBlackDayNight));
            }
            amountTv.setText(getDpAmount2(c, new BigDecimal(amount), 6, 6));

        } else if (chain.equals(AKASH_MAIN)) {
            if (symbol.equals(TOKEN_AKASH)) {
                DpMainDenom(c, chain.getChain(), denomTv);
            } else {
                denomTv.setText(symbol.toUpperCase());
                denomTv.setTextColor(ContextCompat.getColor(c, R.color.colorBlackDayNight));
            }
            amountTv.setText(getDpAmount2(c, new BigDecimal(amount), 6, 6));

        } else if (chain.equals(PERSIS_MAIN)) {
            if (symbol.equals(TOKEN_XPRT)) {
                DpMainDenom(c, chain.getChain(), denomTv);
            } else {
                denomTv.setText(symbol.toUpperCase());
                denomTv.setTextColor(ContextCompat.getColor(c, R.color.colorBlackDayNight));
            }
            amountTv.setText(getDpAmount2(c, new BigDecimal(amount), 6, 6));

        } else if (chain.equals(SENTINEL_MAIN)) {
            if (symbol.equals(TOKEN_DVPN)) {
                DpMainDenom(c, chain.getChain(), denomTv);
            } else {
                denomTv.setText(symbol.toUpperCase());
                denomTv.setTextColor(ContextCompat.getColor(c, R.color.colorBlackDayNight));
            }
            amountTv.setText(getDpAmount2(c, new BigDecimal(amount), 6, 6));

        } else if (chain.equals(FETCHAI_MAIN)) {
            if (symbol.equals(TOKEN_FET)) {
                DpMainDenom(c, chain.getChain(), denomTv);
            } else {
                denomTv.setText(symbol.toUpperCase());
                denomTv.setTextColor(ContextCompat.getColor(c, R.color.colorBlackDayNight));
            }
            amountTv.setText(getDpAmount2(c, new BigDecimal(amount), 18, 18));

        } else if (chain.equals(CRYPTO_MAIN)) {
            if (symbol.equals(TOKEN_CRO)) {
                DpMainDenom(c, chain.getChain(), denomTv);
            } else {
                denomTv.setText(symbol.toUpperCase());
                denomTv.setTextColor(ContextCompat.getColor(c, R.color.colorBlackDayNight));
            }
            amountTv.setText(getDpAmount2(c, new BigDecimal(amount), 8, 8));

        } else if (chain.equals(SIF_MAIN)) {
            int decimal = WUtil.getSifCoinDecimal(symbol);
            if (symbol.equals(TOKEN_SIF)) {
                DpMainDenom(c, chain.getChain(), denomTv);
            } else if (symbol.startsWith("c")) {
                denomTv.setText(symbol.substring(1).toUpperCase());
                denomTv.setTextColor(ContextCompat.getColor(c, R.color.colorBlackDayNight));
            } else {
                denomTv.setText(symbol.toUpperCase());
                denomTv.setTextColor(ContextCompat.getColor(c, R.color.colorBlackDayNight));
            }
            amountTv.setText(getDpAmount2(c, new BigDecimal(amount), decimal, decimal));

        } else if (chain.equals(KI_MAIN)) {
            if (symbol.equals(TOKEN_KI)) {
                DpMainDenom(c, chain.getChain(), denomTv);
            } else {
                denomTv.setText(symbol.toUpperCase());
                denomTv.setTextColor(ContextCompat.getColor(c, R.color.colorBlackDayNight));
            }
            amountTv.setText(getDpAmount2(c, new BigDecimal(amount), 6, 6));

        } else if (chain.equals(OSMOSIS_MAIN)) {
            if (symbol.equals(TOKEN_OSMOSIS)) {
                DpMainDenom(c, chain.getChain(), denomTv);
                amountTv.setText(getDpAmount2(c, new BigDecimal(amount), 6, 6));

            } else if (symbol.equals(TOKEN_ION)) {
                denomTv.setText("ION");
                denomTv.setTextColor(ContextCompat.getColor(c, R.color.color_ion));
                amountTv.setText(getDpAmount2(c, new BigDecimal(amount), 6, 6));

            } else if (symbol.startsWith("gamm/pool/")) {
                String[] value = symbol.split("/");
                denomTv.setText("GAMM-" + value[value.length - 1]);
                denomTv.setTextColor(ContextCompat.getColor(c, R.color.colorBlackDayNight));
                amountTv.setText(getDpAmount2(c, new BigDecimal(amount), 18, 18));

            } else {
                denomTv.setText(symbol.toUpperCase());
                denomTv.setTextColor(ContextCompat.getColor(c, R.color.colorBlackDayNight));
                amountTv.setText(getDpAmount2(c, new BigDecimal(amount), 6, 6));
            }

        } else if (chain.equals(MEDI_MAIN)) {
            if (symbol.equals(TOKEN_MEDI)) {
                DpMainDenom(c, chain.getChain(), denomTv);
            } else {
                denomTv.setText(symbol.toUpperCase());
                denomTv.setTextColor(ContextCompat.getColor(c, R.color.colorBlackDayNight));
            }
            amountTv.setText(getDpAmount2(c, new BigDecimal(amount), 6, 6));

        } else if (chain.equals(EMONEY_MAIN)) {
            if (symbol.equalsIgnoreCase(TOKEN_NGM)) {
                DpMainDenom(c, chain.getChain(), denomTv);
            } else {
                denomTv.setText(symbol.toUpperCase());
                denomTv.setTextColor(ContextCompat.getColor(c, R.color.colorBlackDayNight));
            }
            amountTv.setText(getDpAmount2(c, new BigDecimal(amount), 6, 6));

        } else if (chain.equals(RIZON_MAIN)) {
            if (symbol.equals(TOKEN_RIZON)) {
                DpMainDenom(c, chain.getChain(), denomTv);
            } else {
                denomTv.setText(symbol.toUpperCase());
                denomTv.setTextColor(ContextCompat.getColor(c, R.color.colorBlackDayNight));
            }
            amountTv.setText(getDpAmount2(c, new BigDecimal(amount), 6, 6));

        } else if (chain.equals(JUNO_MAIN)) {
            if (symbol.equals(TOKEN_JUNO)) {
                DpMainDenom(c, chain.getChain(), denomTv);
            } else {
                denomTv.setText(symbol.toUpperCase());
                denomTv.setTextColor(ContextCompat.getColor(c, R.color.colorBlackDayNight));
            }
            amountTv.setText(getDpAmount2(c, new BigDecimal(amount), 6, 6));

        } else if (chain.equals(REGEN_MAIN)) {
            if (symbol.equals(TOKEN_REGEN)) {
                DpMainDenom(c, chain.getChain(), denomTv);
            } else {
                denomTv.setText(symbol.toUpperCase());
                denomTv.setTextColor(ContextCompat.getColor(c, R.color.colorBlackDayNight));
            }
            amountTv.setText(getDpAmount2(c, new BigDecimal(amount), 6, 6));

        } else if (chain.equals(BITCANNA_MAIN)) {
            if (symbol.equals(TOKEN_BITCANNA)) {
                DpMainDenom(c, chain.getChain(), denomTv);
            } else {
                denomTv.setText(symbol.toUpperCase());
                denomTv.setTextColor(ContextCompat.getColor(c, R.color.colorBlackDayNight));
            }
            amountTv.setText(getDpAmount2(c, new BigDecimal(amount), 6, 6));

        } else if (chain.equals(ALTHEA_MAIN)) {
            if (symbol.equals(TOKEN_ALTHEA)) {
                DpMainDenom(c, chain.getChain(), denomTv);
            } else {
                denomTv.setText(symbol.toUpperCase());
                denomTv.setTextColor(ContextCompat.getColor(c, R.color.colorBlackDayNight));
            }
            amountTv.setText(getDpAmount2(c, new BigDecimal(amount), 6, 6));

        } else if (chain.equals(STARGAZE_MAIN)) {
            if (symbol.equals(TOKEN_STARGAZE)) {
                DpMainDenom(c, chain.getChain(), denomTv);
            } else {
                denomTv.setText(symbol.toUpperCase());
                denomTv.setTextColor(ContextCompat.getColor(c, R.color.colorBlackDayNight));
            }
            amountTv.setText(getDpAmount2(c, new BigDecimal(amount), 6, 6));

        } else if (chain.equals(GRABRIDGE_MAIN)) {
            int decimal = WUtil.getGBridgeCoinDecimal(baseData, symbol);
            if (symbol.equals(TOKEN_GRABRIDGE)) {
                DpMainDenom(c, chain.getChain(), denomTv);
            } else if (symbol.startsWith("gravity")) {
                final Assets assets = baseData.getAsset(symbol);
                if (assets != null) {
                    denomTv.setText(assets.origin_symbol);
                    denomTv.setTextColor(ContextCompat.getColor(c, R.color.colorBlackDayNight));
                }
            } else {
                denomTv.setText(symbol.toUpperCase());
                denomTv.setTextColor(ContextCompat.getColor(c, R.color.colorBlackDayNight));
            }
            amountTv.setText(getDpAmount2(c, new BigDecimal(amount), decimal, decimal));

        } else if (chain.equals(COMDEX_MAIN)) {
            if (symbol.equals(TOKEN_COMDEX)) {
                DpMainDenom(c, chain.getChain(), denomTv);
            } else {
                denomTv.setText(symbol.toUpperCase());
                denomTv.setTextColor(ContextCompat.getColor(c, R.color.colorBlackDayNight));
            }
            amountTv.setText(getDpAmount2(c, new BigDecimal(amount), 6, 6));

        } else if (chain.equals(INJ_MAIN)) {
            if (symbol.equals(TOKEN_INJ)) {
                DpMainDenom(c, chain.getChain(), denomTv);
                amountTv.setText(getDpAmount2(c, new BigDecimal(amount), 18, 18));
            } else if (symbol.startsWith("peggy")) {
                final Assets assets = baseData.getAsset(symbol);
                if (assets != null) {
                    denomTv.setText(assets.origin_symbol);
                    denomTv.setTextColor(ContextCompat.getColor(c, R.color.colorBlackDayNight));
                    amountTv.setText(getDpAmount2(c, new BigDecimal(amount), assets.decimal, assets.decimal));
                }
            } else {
                denomTv.setText(symbol.toUpperCase());
                denomTv.setTextColor(ContextCompat.getColor(c, R.color.colorBlackDayNight));
                amountTv.setText(getDpAmount2(c, new BigDecimal(amount), 18, 18));
            }

        } else if (chain.equals(BITSONG_MAIN)) {
            if (symbol.equals(TOKEN_BITSONG)) {
                DpMainDenom(c, chain.getChain(), denomTv);
            } else {
                denomTv.setText(symbol.toUpperCase());
                denomTv.setTextColor(ContextCompat.getColor(c, R.color.colorBlackDayNight));
            }
            amountTv.setText(getDpAmount2(c, new BigDecimal(amount), 6, 6));

        } else if (chain.equals(DESMOS_MAIN)) {
            if (symbol.equals(TOKEN_DESMOS)) {
                DpMainDenom(c, chain.getChain(), denomTv);
            } else {
                denomTv.setText(symbol.toUpperCase());
                denomTv.setTextColor(ContextCompat.getColor(c, R.color.colorBlackDayNight));
            }
            amountTv.setText(getDpAmount2(c, new BigDecimal(amount), 6, 6));

        } else if (chain.equals(LUM_MAIN)) {
            if (symbol.equals(TOKEN_LUM)) {
                DpMainDenom(c, chain.getChain(), denomTv);
            } else {
                denomTv.setText(symbol.toUpperCase());
                denomTv.setTextColor(ContextCompat.getColor(c, R.color.colorBlackDayNight));
            }
            amountTv.setText(getDpAmount2(c, new BigDecimal(amount), 6, 6));

        } else if (chain.equals(CHIHUAHUA_MAIN)) {
            if (symbol.equals(TOKEN_CHIHUAHUA)) {
                DpMainDenom(c, chain.getChain(), denomTv);
            } else {
                denomTv.setText(symbol.toUpperCase());
                denomTv.setTextColor(ContextCompat.getColor(c, R.color.colorBlackDayNight));
            }
            amountTv.setText(getDpAmount2(c, new BigDecimal(amount), 6, 6));

        } else if (chain.equals(AXELAR_MAIN)) {
            if (symbol.equals(TOKEN_AXELAR)) {
                DpMainDenom(c, chain.getChain(), denomTv);
            } else {
                denomTv.setText(symbol.toUpperCase());
                denomTv.setTextColor(ContextCompat.getColor(c, R.color.colorBlackDayNight));
            }
            amountTv.setText(getDpAmount2(c, new BigDecimal(amount), 6, 6));

        } else if (chain.equals(KONSTELL_MAIN)) {
            if (symbol.equals(TOKEN_DARC)) {
                DpMainDenom(c, chain.getChain(), denomTv);
            } else {
                denomTv.setText(symbol.toUpperCase());
                denomTv.setTextColor(ContextCompat.getColor(c, R.color.colorBlackDayNight));
            }
            amountTv.setText(getDpAmount2(c, new BigDecimal(amount), 6, 6));

        } else if (chain.equals(UMEE_MAIN)) {
            if (symbol.equals(TOKEN_UMEE)) {
                DpMainDenom(c, chain.getChain(), denomTv);
            } else {
                denomTv.setText(symbol.toUpperCase());
                denomTv.setTextColor(ContextCompat.getColor(c, R.color.colorBlackDayNight));
            }
            amountTv.setText(getDpAmount2(c, new BigDecimal(amount), 6, 6));

        } else if (chain.equals(EVMOS_MAIN)) {
            if (symbol.equals(TOKEN_EVMOS)) {
                DpMainDenom(c, chain.getChain(), denomTv);
            } else {
                denomTv.setText(symbol.toUpperCase());
                denomTv.setTextColor(ContextCompat.getColor(c, R.color.colorBlackDayNight));
            }
            amountTv.setText(getDpAmount2(c, new BigDecimal(amount), 18, 18));

        } else if (chain.equals(CUDOS_MAIN)) {
            if (symbol.equals(TOKEN_CUDOS)) {
                DpMainDenom(c, chain.getChain(), denomTv);
            } else {
                denomTv.setText(symbol.toUpperCase());
                denomTv.setTextColor(ContextCompat.getColor(c, R.color.colorBlackDayNight));
            }
            amountTv.setText(getDpAmount2(c, new BigDecimal(amount), 18, 18));

        } else if (chain.equals(PROVENANCE_MAIN)) {
            if (symbol.equals(TOKEN_HASH)) {
                DpMainDenom(c, chain.getChain(), denomTv);
            } else {
                denomTv.setText(symbol.toUpperCase());
                denomTv.setTextColor(ContextCompat.getColor(c, R.color.colorBlackDayNight));
            }
            amountTv.setText(getDpAmount2(c, new BigDecimal(amount), 9, 9));

        } else if (chain.equals(CERBERUS_MAIN)) {
            if (symbol.equals(TOKEN_CRBRUS)) {
                DpMainDenom(c, chain.getChain(), denomTv);
            } else {
                denomTv.setText(symbol.toUpperCase());
                denomTv.setTextColor(ContextCompat.getColor(c, R.color.colorBlackDayNight));
            }
            amountTv.setText(getDpAmount2(c, new BigDecimal(amount), 6, 6));

        } else if (chain.equals(OMNIFLIX_MAIN)) {
            if (symbol.equals(TOKEN_FLIX)) {
                DpMainDenom(c, chain.getChain(), denomTv);
            } else {
                denomTv.setText(symbol.toUpperCase());
                denomTv.setTextColor(ContextCompat.getColor(c, R.color.colorBlackDayNight));
            }
            amountTv.setText(getDpAmount2(c, new BigDecimal(amount), 6, 6));

        } else if (chain.equals(CRESCENT_MAIN)) {
            if (symbol.equals(TOKEN_CRE)) {
                DpMainDenom(c, chain.getChain(), denomTv);
            } else if (symbol.equals(TOKEN_BCRE)) {
                denomTv.setText(R.string.str_bcre_c);
                denomTv.setTextColor(ContextCompat.getColor(c, R.color.color_bcre));
            } else {
                denomTv.setText(symbol.toUpperCase());
                denomTv.setTextColor(ContextCompat.getColor(c, R.color.colorBlackDayNight));
            }
            amountTv.setText(getDpAmount2(c, new BigDecimal(amount), 6, 6));

        } else if (chain.equals(ASSETMANTLE_MAIN)) {
            if (symbol.equals(TOKEN_MANTLE)) {
                DpMainDenom(c, chain.getChain(), denomTv);
            } else {
                denomTv.setText(symbol.toUpperCase());
                denomTv.setTextColor(ContextCompat.getColor(c, R.color.colorBlackDayNight));
            }
            amountTv.setText(getDpAmount2(c, new BigDecimal(amount), 6, 6));

        } else if (chain.equals(NYX_MAIN)) {
            if (symbol.equals(TOKEN_NYX)) {
                DpMainDenom(c, chain.getChain(), denomTv);
            } else if (symbol.equals(TOKEN_NYM)) {
                denomTv.setText(R.string.str_nym_c);
                denomTv.setTextColor(ContextCompat.getColor(c, R.color.color_nym));
            } else {
                denomTv.setText(symbol.toUpperCase());
                denomTv.setTextColor(ContextCompat.getColor(c, R.color.colorBlackDayNight));
            }
            amountTv.setText(getDpAmount2(c, new BigDecimal(amount), 6, 6));

        } else if (chain.equals(STATION_TEST)) {
            if (symbol.equals(TOKEN_STATION)) {
                DpMainDenom(c, chain.getChain(), denomTv);
            }
            amountTv.setText(getDpAmount2(c, new BigDecimal(amount), 6, 6));

        } else if (chain.equals(COSMOS_TEST)) {
            if (symbol.equals(TOKEN_COSMOS_TEST)) {
                DpMainDenom(c, chain.getChain(), denomTv);
            } else {
                denomTv.setText(symbol.toUpperCase());
                denomTv.setTextColor(ContextCompat.getColor(c, R.color.colorBlackDayNight));
            }
            amountTv.setText(getDpAmount2(c, new BigDecimal(amount), 6, 6));

        } else if (chain.equals(IRIS_TEST)) {
            if (symbol.equals(TOKEN_IRIS_TEST)) {
                DpMainDenom(c, chain.getChain(), denomTv);
            } else {
                denomTv.setText(symbol.toUpperCase());
                denomTv.setTextColor(ContextCompat.getColor(c, R.color.colorBlackDayNight));
            }
            amountTv.setText(getDpAmount2(c, new BigDecimal(amount), 6, 6));

        } else if (chain.equals(CRESCENT_TEST)) {
            if (symbol.equals(TOKEN_CRESCENT_TEST)) {
                DpMainDenom(c, chain.getChain(), denomTv);
            } else {
                denomTv.setText(symbol.toUpperCase());
                denomTv.setTextColor(ContextCompat.getColor(c, R.color.colorBlackDayNight));
            }
            amountTv.setText(getDpAmount2(c, new BigDecimal(amount), 6, 6));
        }
    }

    public static void showChainDp(Context c, BaseChain baseChain, CardView cardName, CardView cardAlarm, CardView cardBody, CardView cardRewardAddress) {
        if (baseChain.equals(OKEX_MAIN) || baseChain.equals(BNB_MAIN) || baseChain.equals(FETCHAI_MAIN)) {
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
    }

    public static BaseChain getChainTypeByChainId(String chainId) {
        if (chainId != null) {
            ArrayList<ChainConfig> allConfigs = ChainFactory.SUPPRT_CONFIG();
            for (ChainConfig chainConfig : allConfigs) {
                if (chainId.contains(chainConfig.chainIdPrefix())) {
                    return chainConfig.baseChain();
                }
            }
        }
        return null;
    }

    public static boolean isValidChainAddress(ChainConfig chainConfig, String address) {
        if (chainConfig == null) return false;
        if (address.startsWith("0x")) {
            if (WKey.isValidEthAddress(address) && chainConfig.baseChain().equals(OKEX_MAIN)) { return true; }
            return false;
        }

        if (!WKey.isValidBech32(address)) { return false; }
        String addressPrefix = chainConfig.addressPrefix() + "1";
        if (address.startsWith(addressPrefix)) { return true; }
        return false;
    }

    public static ArrayList<BaseChain> getChainsFromAddress(String address) {
        if (address != null) {
            if (address.startsWith("0x")) {
                if (WKey.isValidEthAddress(address)) { return Lists.newArrayList(OKEX_MAIN); }
                return null;
            }

            if (!WKey.isValidBech32(address)) { return null; }
            ArrayList<ChainConfig> allConfigs = ChainFactory.SUPPRT_CONFIG();
            for (ChainConfig chainConfig : allConfigs) {
                String addressPrefix = chainConfig.addressPrefix() + "1";
                if (address.startsWith(addressPrefix)) {
                    return Lists.newArrayList(chainConfig.baseChain());
                }
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
        return getDpAmount2(c, dayReward, mainDivideDecimal(chain), mainDisplayDecimal(chain));
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

    public static BigDecimal kavaTokenDollorValue(BaseData baseData, String denom, BigDecimal
            amount) {
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

    public static String getKavaPriceFeedSymbol(BaseData baseData, String denom) {
        if (denom != null) {
            if (denom.startsWith("ibc/")) {
                IbcToken ibcToken = baseData.getIbcToken(denom);
                return ibcToken.display_denom + ":usd";
            } else {
                String priceDenom = "";
                if (denom.equalsIgnoreCase(TOKEN_KAVA)) {
                    priceDenom = "kava";
                } else if (denom.contains("btc")) {
                    priceDenom = "btc";
                } else {
                    priceDenom = denom;
                }
                return priceDenom + ":usd";
            }
        }
        return "";
    }

    public static BigDecimal getKavaPriceFeed(BaseData baseData, String denom) {
        String feedSymbol = getKavaPriceFeedSymbol(baseData, denom);
        if (baseData.mKavaTokenPrice.get(feedSymbol) == null) {
            return BigDecimal.ZERO;
        }
        return new BigDecimal(baseData.mKavaTokenPrice.get(feedSymbol).getPrice()).movePointLeft(18);
    }

    public static BigDecimal convertTokenToKava(BaseData baseData, String denom) {
        BigDecimal tokenAmount = baseData.getAvailable(denom).add(baseData.getVesting(denom));
        BigDecimal totalTokenValue = kavaTokenDollorValue(baseData, denom, tokenAmount);
        return totalTokenValue.movePointRight(6).divide(perUsdValue(baseData, TOKEN_KAVA), 6, RoundingMode.DOWN);
    }

    public static BigDecimal okExTokenDollorValue(BaseData baseData, OkToken
            okToken, BigDecimal amount) {
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
        if (denom.equals(Emoney.EMONEY_EUR_DENOM) || denom.equals(Emoney.EMONEY_CHF_DENOM) || denom.equals(Emoney.EMONEY_DKK_DENOM) ||
                denom.equals(Emoney.EMONEY_NOK_DENOM) || denom.equals(Emoney.EMONEY_SEK_DENOM)) {
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
        if (isGRPC(baseChain)) {
            for (Coin coin : baseData.mGrpcBalance) {
                if (coin.denom.equals(mainDenom(baseChain))) {
                    BigDecimal amount = baseData.getAllMainAsset(mainDenom(baseChain));
                    BigDecimal assetValue = userCurrencyValue(baseData, coin.denom, amount, mainDivideDecimal(baseChain));
                    totalValue = totalValue.add(assetValue);
                } else if (baseChain.equals(OSMOSIS_MAIN) && coin.denom.equals(TOKEN_ION) ||
                        baseChain.equals(CRESCENT_MAIN) && coin.denom.equals(TOKEN_BCRE) ||
                        baseChain.equals(NYX_MAIN) && coin.denom.equals(TOKEN_NYM)) {
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
                } else if (baseChain.equals(EMONEY_MAIN) && coin.denom.startsWith("e")) {
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

        } else if (baseChain.equals(OKEX_MAIN)) {
            for (Balance balance : baseData.mBalances) {
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

        } else {
            for (Balance balance : baseData.mBalances) {
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
            for (Coin coin : baseData.mGrpcBalance) {
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
                } else if (baseChain.equals(KAVA_MAIN)) {
                    if (coin.denom.equals(mainDenom(baseChain))) {
                        BigDecimal amount = baseData.getAllMainAsset(coin.denom);
                        BigDecimal btcValue = btcValue(baseData, TOKEN_KAVA, amount, mainDivideDecimal(baseChain));
                        totalValue = totalValue.add(btcValue);
                    } else {
                        BigDecimal convertAmount = convertTokenToKava(baseData, coin.denom);
                        BigDecimal btcValue = btcValue(baseData, TOKEN_KAVA, convertAmount, mainDivideDecimal(baseChain));
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

        } else if (baseChain.equals(OKEX_MAIN)) {
            for (Balance balance : baseData.mBalances) {
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

        } else {
            for (Balance balance : baseData.mBalances) {
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

    public static DecimalFormat getDecimalFormat(int cnt) {
        NumberFormat formatter = NumberFormat.getNumberInstance(Locale.US);
        DecimalFormat decimalformat = (DecimalFormat) formatter;
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
            return result + "   " + "(" + baseData.mChainParam.getUnbonding(baseChain) + c.getString(R.string.str_unbonding_days_after);
        } catch (Exception e) {
            return result;
        }
    }

    public static String getGapTime(Context c, long finishTime) {
        String result = "??";
        try {
            long now = Calendar.getInstance().getTimeInMillis();
            long left = finishTime - now;

            if (left >= BaseConstant.CONSTANT_D) {
                result = "(D-" + (left / BaseConstant.CONSTANT_D) + ")";
            } else if (left >= BaseConstant.CONSTANT_H) {
                result = "(" + (left / BaseConstant.CONSTANT_H) + " hours ago)";
            } else if (left >= BaseConstant.CONSTANT_M) {
                result = "(" + (left / BaseConstant.CONSTANT_M) + " minutes ago)";
            } else if (left >= BaseConstant.CONSTANT_S) {
                result =  "(" + (left / BaseConstant.CONSTANT_S) + " seconds ago)";
            } else {
                result = "Soon";
            }

        } catch (Exception e) { }

        return result;
    }

    public static String getUnbondingTimefrom(Context c, String rawStartTime) {
        String result = "??";
        try {
            long now = Calendar.getInstance().getTimeInMillis();

            SimpleDateFormat blockDateFormat = new SimpleDateFormat(c.getString(R.string.str_block_time_format));
            blockDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            long start = blockDateFormat.parse(rawStartTime).getTime();
            long left = start + BaseConstant.COSMOS_UNBONDING_TIME - now;

//            WLog.w("start : " + start);
//            WLog.w("COSMOS_UNBONDING_TIME : " + BaseConstant.COSMOS_UNBONDING_TIME);
//            WLog.w("now : " + now);

            if (left >= BaseConstant.CONSTANT_D) {
                result = "(D-" + (left / BaseConstant.CONSTANT_D) + ")";
            } else if (left >= BaseConstant.CONSTANT_H) {
                result = "(H-" + (left / BaseConstant.CONSTANT_H) + ")";
            } else if (left < 0) {
                return "completed";
            } else {
                return "in hour";
            }

        } catch (Exception e) { }

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

    public static long dateToLong2(String rawValue) {
        long result = 0;
        try {
            SimpleDateFormat blockDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
            blockDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            result = blockDateFormat.parse(rawValue).getTime();
        } catch (Exception e) {
        }

        return result;
    }

    public static String getDateformat(Context c, String rawValue) {
        String result = "??";
        try {
            SimpleDateFormat blockDateFormat = new SimpleDateFormat(c.getString(R.string.str_block_time_format));
            SimpleDateFormat myFormat = new SimpleDateFormat(c.getString(R.string.str_dp_date_format));
            blockDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            result = myFormat.format(blockDateFormat.parse(rawValue));
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

    public static String getTimeTxformatShort(Context c, String rawValue) {
        String result = "??";
        try {
            SimpleDateFormat blockDateFormat = new SimpleDateFormat(c.getString(R.string.str_tx_time_format));
            SimpleDateFormat myFormat = new SimpleDateFormat(c.getString(R.string.str_dp_time_format4));
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

    public static String  getTimeTxGap(Context c, long rawValue) {
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
        if (chain != null) {
            ChainConfig chainConfig = ChainFactory.getChain(chain);
            return ContextCompat.getColor(c, chainConfig.chainColor());
        }
        return ContextCompat.getColor(c, R.color.colorTrans);
    }

    public static int getChainBgColor(Context c, BaseChain chain) {
        if (chain != null) {
            ChainConfig chainConfig = ChainFactory.getChain(chain);
            return ContextCompat.getColor(c, chainConfig.chainBgColor());
        }
        return ContextCompat.getColor(c, R.color.colorTransBg);
    }

    public static void DpMainDenom(Context c, BaseChain chain, TextView textview) {
        DpMainDenom(c, chain.getChain(), textview);
    }

    public static void DpMainDenom(Context c, String chain, TextView textview) {
        if (!chain.isEmpty()) {
            ChainConfig chainConfig = ChainFactory.getChain(getChain(chain));
            textview.setText(chainConfig.mainSymbol());
            textview.setTextColor(ContextCompat.getColor(c, chainConfig.chainColor()));
        }
    }

    public static String mainDenom(BaseChain chain) {
        if (chain != null) {
            ChainConfig chainConfig = ChainFactory.getChain(chain);
            return chainConfig.mainDenom();
        }
        return "";
    }

    public static ArrayList<String> getGasDenomList(BaseChain baseChain) {
        if (baseChain.equals(NYX_MAIN)) {
            return Lists.newArrayList(TOKEN_NYM);
        } else if (baseChain.equals(CRESCENT_MAIN)) {
            return Lists.newArrayList(TOKEN_CRE, TOKEN_BCRE);
        }
        return Lists.newArrayList(mainDenom(baseChain));
    }

    public static void setGasDenomTv(Context c, BaseChain baseChain, String denom, TextView denomTv) {
        if (denom.equalsIgnoreCase(TOKEN_BCRE)) {
            denomTv.setTextColor(ContextCompat.getColor(c, R.color.color_bcre));
            denomTv.setText(c.getString(R.string.str_bcre_c));
        } else if (denom.equalsIgnoreCase(TOKEN_NYM)) {
            denomTv.setTextColor(ContextCompat.getColor(c, R.color.color_nym));
            denomTv.setText(c.getString(R.string.str_nym_c));
        } else {
            WDp.DpMainDenom(c, baseChain, denomTv);
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
            if (baseChain.equals(OSMOSIS_MAIN)) {
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
        if (chain.equals(BNB_MAIN) || chain.equals(OKEX_MAIN)) {
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
        if (chain.equals(BNB_MAIN) || chain.equals(CRYPTO_MAIN)) {
            return 8;
        } else if (chain.equals(OKEX_MAIN) || chain.equals(FETCHAI_MAIN) || chain.equals(SIF_MAIN) || chain.equals(INJ_MAIN) || chain.equals(EVMOS_MAIN) || chain.equals(CUDOS_MAIN)) {
            return 18;
        } else if (chain.equals(PROVENANCE_MAIN)) {
            return 9;
        } else {
            return 6;
        }
    }

    public static int mainDivideDecimal(String denom) {
        if (denom.equals(TOKEN_BNB)) {
            return 8;
        } else if (denom.equals(TOKEN_OK)) {
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

    public static void getProposalStatus(Context c, ResProposal proposal, ImageView
            statusImg, TextView status) {
        if (proposal != null) {
            if (proposal.proposal_status.equalsIgnoreCase("PROPOSAL_STATUS_DEPOSIT_PERIOD") || proposal.proposal_status.equalsIgnoreCase("DepositPeriod")) {
                statusImg.setImageDrawable(ContextCompat.getDrawable(c, R.drawable.ic_deposit_img));
                status.setText("DepositPeriod");

            } else if (proposal.proposal_status.equalsIgnoreCase("PROPOSAL_STATUS_VOTING_PERIOD") ||
                    proposal.proposal_status.equalsIgnoreCase("PROPOSAL_STATUS_CERTIFIER_VOTING_PERIOD") ||
                    proposal.proposal_status.equalsIgnoreCase("PROPOSAL_STATUS_VALIDATOR_VOTING_PERIOD") ||
                    proposal.proposal_status.equalsIgnoreCase("VotingPeriod")) {
                statusImg.setImageDrawable(ContextCompat.getDrawable(c, R.drawable.ic_voting_img));
                status.setText("VotingPeriod");

            } else if (proposal.proposal_status.equalsIgnoreCase("PROPOSAL_STATUS_REJECTED") || proposal.proposal_status.equalsIgnoreCase("Rejected")) {
                statusImg.setImageDrawable(ContextCompat.getDrawable(c, R.drawable.ic_rejected_img));
                status.setText("Rejected");
            } else if (proposal.proposal_status.equalsIgnoreCase("PROPOSAL_STATUS_PASSED") || proposal.proposal_status.equalsIgnoreCase("Passed")) {
                statusImg.setImageDrawable(ContextCompat.getDrawable(c, R.drawable.ic_passed_img));
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

    public static BigDecimal getTurnout(BaseChain baseCahin, BaseData baseData, ResProposal
            proposal) {
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

    public static ArrayList<Coin> onParseAutoReward(ServiceOuterClass.GetTxResponse response, String Addr, int position) {
        ArrayList<Coin> result = new ArrayList<>();
        if (response.getTxResponse().getLogsCount() > 0 && response.getTxResponse().getLogs(position) != null) {
            for (Abci.StringEvent event : response.getTxResponse().getLogs(position).getEventsList()) {
                if (event.getType().equals("transfer")) {
                    for (int i = 0; i < event.getAttributesList().size(); i++) {
                        if (event.getAttributes(i).getKey().equals("recipient") && event.getAttributes(i).getValue().equals(Addr)) {
                            for (int j = i; j < event.getAttributesList().size(); j++) {
                                if (event.getAttributes(j).getKey().equals("amount") && event.getAttributes(j).getValue() != null) {
                                    String rawValue = event.getAttributes(j).getValue();
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
                                    if (rawCoin.contains(WDp.mainDenom(baseChain))) {
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

    public static ArrayList<Vesting.Period> onParsePeriodicRemainVestings
            (Vesting.PeriodicVestingAccount vestingAccount) {
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
