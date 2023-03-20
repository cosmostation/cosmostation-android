package wannabit.io.cosmostaion.utils;

import static android.text.Spanned.SPAN_INCLUSIVE_INCLUSIVE;
import static wannabit.io.cosmostaion.base.BaseChain.*;
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
import java.util.Optional;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cosmos.base.abci.v1beta1.Abci;
import cosmos.base.v1beta1.CoinOuterClass;
import cosmos.staking.v1beta1.Staking;
import cosmos.tx.v1beta1.ServiceOuterClass;
import cosmos.vesting.v1beta1.Vesting;
import stride.vesting.Vesting.StridePeriodicVestingAccount;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.base.chains.Binance;
import wannabit.io.cosmostaion.base.chains.ChainConfig;
import wannabit.io.cosmostaion.base.chains.ChainFactory;
import wannabit.io.cosmostaion.base.chains.Crescent;
import wannabit.io.cosmostaion.base.chains.Kava;
import wannabit.io.cosmostaion.base.chains.Nyx;
import wannabit.io.cosmostaion.base.chains.Okc;
import wannabit.io.cosmostaion.base.chains.Osmosis;
import wannabit.io.cosmostaion.dao.Asset;
import wannabit.io.cosmostaion.dao.AssetPath;
import wannabit.io.cosmostaion.dao.Balance;
import wannabit.io.cosmostaion.dao.BnbTicker;
import wannabit.io.cosmostaion.dao.BnbToken;
import wannabit.io.cosmostaion.dao.FeeInfo;
import wannabit.io.cosmostaion.dao.MintscanToken;
import wannabit.io.cosmostaion.dao.OkToken;
import wannabit.io.cosmostaion.dao.Param;
import wannabit.io.cosmostaion.dao.Price;
import wannabit.io.cosmostaion.model.type.BnbHistory;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.network.res.ResProposal;

public class WDp {
    public static SpannableString getDpAmount2(Context c, BigDecimal input, int divideDecimal, int displayDecimal) {
        SpannableString result;
        BigDecimal amount = input.movePointLeft(divideDecimal).setScale(displayDecimal, BigDecimal.ROUND_DOWN);
        result = new SpannableString(getDecimalFormat(displayDecimal).format(amount));
        result.setSpan(new RelativeSizeSpan(0.8f), result.length() - displayDecimal, result.length(), SPAN_INCLUSIVE_INCLUSIVE);
        return result;
    }

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

    public static String getDpSymbol(BaseData baseData, ChainConfig chainConfig, String denom) {
        if (chainConfig == null || denom == null || denom.isEmpty()) {
            return "UNKNOWN";
        }
        final Asset asset = baseData.getAsset(chainConfig, denom);
        final MintscanToken mintscanToken = baseData.getCw20Asset(chainConfig, denom);

        if (asset != null) {
            return asset.symbol;

        } else if (mintscanToken != null) {
            return mintscanToken.symbol;

        } else {
            if (chainConfig.mainDenom().equalsIgnoreCase(denom)) {
                return chainConfig.mainSymbol();

            } else if (denom.startsWith("gamm/pool/")) {
                String[] split = denom.split("/");
                return "GAMM-" + split[split.length - 1];

            } else if (denom.startsWith("pool") || denom.startsWith("share")) {
                return denom.toUpperCase();

            } else if (chainConfig.baseChain().equals(BNB_MAIN)) {
                if (baseData.getBnbToken(denom) != null)
                    return baseData.getBnbToken(denom).original_symbol.toUpperCase();
                else return denom.toUpperCase();

            } else if (chainConfig.baseChain().equals(OKEX_MAIN)) {
                if (baseData.okToken(denom) != null)
                    return baseData.okToken(denom).original_symbol.toUpperCase();
                else return denom.toUpperCase();

            } else if (denom.equalsIgnoreCase(TOKEN_HTLC_BINANCE_BNB)) {
                return "BNB";
            } else if (denom.equalsIgnoreCase(TOKEN_HTLC_BINANCE_BTCB)) {
                return "BTC";
            } else if (denom.equalsIgnoreCase(TOKEN_HTLC_BINANCE_XRPB)) {
                return "XRP";
            } else if (denom.equalsIgnoreCase(TOKEN_HTLC_BINANCE_BUSD)) {
                return "BUSD";
            }
        }
        return "UNKNOWN";
    }

    public static void setDpSymbol(Context c, BaseData baseData, ChainConfig chainConfig, String denom, TextView textView) {
        if (chainConfig == null || denom == null || denom.isEmpty()) return;
        textView.setText(getDpSymbol(baseData, chainConfig, denom));
        if (chainConfig.mainDenom().equalsIgnoreCase(denom)) {
            textView.setTextColor(ContextCompat.getColor(c, chainConfig.chainColor()));

        } else if (chainConfig.baseChain().equals(KAVA_MAIN)) {
            if (denom.equalsIgnoreCase(Kava.KAVA_HARD_DENOM))
                textView.setTextColor(ContextCompat.getColor(c, R.color.color_hard));
            else if (denom.equalsIgnoreCase(Kava.KAVA_SWP_DENOM))
                textView.setTextColor(ContextCompat.getColor(c, R.color.color_swp));
            else if (denom.equalsIgnoreCase(Kava.KAVA_USDX_DENOM))
                textView.setTextColor(ContextCompat.getColor(c, R.color.color_usdx));
            else textView.setTextColor(ContextCompat.getColor(c, R.color.colorBlackDayNight));

        } else if (chainConfig.baseChain().equals(OSMOSIS_MAIN)) {
            if (denom.equalsIgnoreCase(Osmosis.OSMOSIS_ION_DENOM))
                textView.setTextColor(ContextCompat.getColor(c, R.color.color_ion));
            else textView.setTextColor(ContextCompat.getColor(c, R.color.colorBlackDayNight));

        } else if (chainConfig.baseChain().equals(CRESCENT_MAIN)) {
            if (denom.equalsIgnoreCase(Crescent.CRESCENT_BCRE_DENOM))
                textView.setTextColor(ContextCompat.getColor(c, R.color.color_bcre));
            else textView.setTextColor(ContextCompat.getColor(c, R.color.colorBlackDayNight));

        } else if (chainConfig.baseChain().equals(NYX_MAIN)) {
            if (denom.equalsIgnoreCase(Nyx.NYX_NYM_DENOM))
                textView.setTextColor(ContextCompat.getColor(c, R.color.color_nym));
            else textView.setTextColor(ContextCompat.getColor(c, R.color.colorBlackDayNight));

        } else {
            textView.setTextColor(ContextCompat.getColor(c, R.color.colorBlackDayNight));
        }
    }

    public static int getDenomDecimal(BaseData baseData, ChainConfig chainConfig, String denom) {
        if (chainConfig == null || denom == null || denom.isEmpty()) return 6;
        final Asset asset = baseData.getAsset(chainConfig, denom);
        final MintscanToken mintscanToken = baseData.getCw20Asset(chainConfig, denom);

        if (asset != null) {
            return asset.decimals;

        } else if (mintscanToken != null) {
            return mintscanToken.decimals;

        } else {
            if (chainConfig.mainDenom().equalsIgnoreCase(denom)) return chainConfig.decimal();
            if (denom.startsWith("gamm/pool/") || denom.startsWith("share")) return 18;
            else if (denom.startsWith("pool")) return 12;
        }
        return chainConfig.decimal();
    }

    public static void setDpSymbolImg(BaseData baseData, ChainConfig chainConfig, String denom, ImageView imageView) {
        if (chainConfig == null || denom == null || denom.isEmpty())
            imageView.setImageResource(R.drawable.token_default);
        final Asset asset = baseData.getAsset(chainConfig, denom);

        if (asset != null) {
            Picasso.get().load(CHAIN_BASE_URL + asset.image).error(R.drawable.token_default).into(imageView);

        } else {
            if (chainConfig.mainDenom().equalsIgnoreCase(denom)) {
                imageView.setImageResource(chainConfig.mainDenomImg());

            } else if (denom.startsWith("gamm/pool") || denom.startsWith("pool") || denom.startsWith("share")) {
                Picasso.get().load(CHAIN_BASE_URL + chainConfig.chainName() + "/asset/pool.png").error(R.drawable.token_default).into(imageView);

            } else if (chainConfig.baseChain().equals(BNB_MAIN)) {
                BnbToken bnbToken = baseData.getBnbToken(denom);
                if (bnbToken != null) {
                    Picasso.get().load(Binance.BINANCE_COIN_IMG_URL + bnbToken.original_symbol.toLowerCase() + ".png").fit().placeholder(R.drawable.token_default).error(R.drawable.token_default).into(imageView);
                }

            } else if (chainConfig.baseChain().equals(OKEX_MAIN)) {
                OkToken okToken = baseData.okToken(denom);
                if (okToken != null) {
                    Picasso.get().load(Okc.OKC_COIN_IMG_URL + okToken.original_symbol + ".png").placeholder(R.drawable.token_default).error(R.drawable.token_default).fit().into(imageView);
                }

            } else {
                Picasso.get().load(CHAIN_BASE_URL + chainConfig.chainName() + "unknown.png").error(R.drawable.token_default).into(imageView);
            }
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

        final Asset asset = baseData.getAsset(chainConfig, denom);
        final MintscanToken mintscanToken = baseData.getCw20Asset(chainConfig, denom);
        if (asset != null) {
            amountTv.setText(getDpAmount2(new BigDecimal(amount), asset.decimals, asset.decimals));

        } else if (mintscanToken != null) {
            amountTv.setText(getDpAmount2(new BigDecimal(amount), mintscanToken.decimals, mintscanToken.decimals));

        } else {
            if (chainConfig.baseChain().equals(BNB_MAIN) || chainConfig.baseChain().equals(OKEX_MAIN)) {
                divideDecimal = getDenomDecimal(baseData, chainConfig, denom);
                displayDecimal = mainDisplayDecimal(chainConfig.baseChain());
            } else {
                divideDecimal = getDenomDecimal(baseData, chainConfig, denom);
                displayDecimal = getDenomDecimal(baseData, chainConfig, denom);
            }
            amountTv.setText(getDpAmount2(new BigDecimal(amount), divideDecimal, displayDecimal));
        }
    }

    public static ArrayList<FeeInfo> getFeeInfos(Context c, BaseData baseData) {
        ArrayList<FeeInfo> result = new ArrayList<>();
        if (baseData.mParam != null && baseData.mParam.getGasRate() != null && baseData.mParam.getGasRate().size() > 0) {
            for (String gasInfo : baseData.mParam.getGasRate()) {
                result.add(new FeeInfo(gasInfo));
            }

            if (result.size() == 1) {
                result.get(0).title = c.getString(R.string.str_fixed);
                result.get(0).msg = c.getString(R.string.str_fee_speed_title_fixed);
            } else if (result.size() == 2) {
                result.get(1).title = c.getString(R.string.str_average);
                result.get(1).msg = c.getString(R.string.str_fee_speed_title_average);
                if (result.get(0).feeDatas.get(0).gasRate.compareTo(BigDecimal.ZERO) == 0) {
                    result.get(0).title = c.getString(R.string.str_free);
                    result.get(0).msg = c.getString(R.string.str_fee_speed_title_zero);
                } else {
                    result.get(0).title = c.getString(R.string.str_tiny);
                    result.get(0).msg = c.getString(R.string.str_fee_speed_title_tiny);
                }
            } else if (result.size() == 3) {
                result.get(2).title = c.getString(R.string.str_average);
                result.get(2).msg = c.getString(R.string.str_fee_speed_title_average);
                result.get(1).title = c.getString(R.string.str_low);
                result.get(1).msg = c.getString(R.string.str_fee_speed_title_low);
                if (result.get(0).feeDatas.get(0).gasRate.compareTo(BigDecimal.ZERO) == 0) {
                    result.get(0).title = c.getString(R.string.str_free);
                    result.get(0).msg = c.getString(R.string.str_fee_speed_title_zero);
                } else {
                    result.get(0).title = c.getString(R.string.str_tiny);
                    result.get(0).msg = c.getString(R.string.str_fee_speed_title_tiny);
                }
            }
        }

        return result;
    }

    public static boolean isTxFeePayable(Context c, BaseData baseData, ChainConfig chainConfig) {
        if (isGRPC(chainConfig.baseChain())) {
            if (baseData == null || baseData.mParam == null || baseData.mParam.mGasPrice == null) return false;

            if (chainConfig.baseChain().equals(SIF_MAIN)) {
                if (new BigDecimal("100000000000000000").compareTo(baseData.getAvailable(chainConfig.mainDenom())) < 0) {
                    return true;
                }
                return false;
            }

        } else {
            if (chainConfig.baseChain().equals(BNB_MAIN)) {
                if (new BigDecimal(FEE_BNB_SEND).compareTo(baseData.availableAmount(chainConfig.mainDenom())) < 0) {
                    return true;
                }
                return false;
            } else if (chainConfig.baseChain().equals(OKEX_MAIN)) {
                if (new BigDecimal(FEE_OKC_BASE).compareTo(baseData.availableAmount(chainConfig.mainDenom())) < 0) {
                    return true;
                }
                return false;
            }
        }

        boolean result = false;
        for (Coin coin : getMinTxFeeAmounts(c, baseData)) {
            if (baseData.getAvailable(coin.denom).compareTo(new BigDecimal(coin.amount)) >= 0) {
                result = true;
            }
        }
        return result;
    }

    public static ArrayList<Coin> getMinTxFeeAmounts(Context c, BaseData baseData) {
        ArrayList<Coin> result = new ArrayList<>();
        BigDecimal gasAmount = new BigDecimal(BASE_GAS_AMOUNT);
        ArrayList<FeeInfo.FeeData> feeDatas = getFeeInfos(c, baseData).get(0).feeDatas;

        for (FeeInfo.FeeData feeData : feeDatas) {
            BigDecimal amount = feeData.gasRate.multiply(gasAmount).setScale(0, RoundingMode.UP);
            result.add(new Coin(feeData.denom, amount.toPlainString()));
        }
        return result;
    }

    public static BigDecimal getMainDenomFee(Context c, BaseData baseData, ChainConfig chainConfig) {
        if (chainConfig.baseChain().equals(SIF_MAIN)) {
            return new BigDecimal("100000000000000000");
        } else if (chainConfig.baseChain().equals(BNB_MAIN)) {
            return new BigDecimal(FEE_BNB_SEND);
        } else if (chainConfig.baseChain().equals(OKEX_MAIN)) {
            return new BigDecimal(FEE_OKC_BASE);
        }
        for (Coin coin : getMinTxFeeAmounts(c, baseData)) {
            if (coin.denom.equalsIgnoreCase(chainConfig.mainDenom())) {
                return new BigDecimal(coin.amount);
            } else {
                return BigDecimal.ZERO;
            }
        }
        return BigDecimal.ZERO;
    }

    public static AssetPath getAssetPath(BaseData baseData, ChainConfig fromChain, ChainConfig toChain, String denom) {
        Optional<Asset> msAsset = baseData.mAssets.stream().filter(item -> item.denom.equalsIgnoreCase(denom)).findFirst();
        MintscanToken msMintscanToken = baseData.getCw20Asset(fromChain, denom);

        for (Asset asset : baseData.mAssets) {
            if (msAsset.isPresent()) {
                if (asset.chain.equalsIgnoreCase(fromChain.chainName()) &&
                        asset.beforeChain(fromChain) != null && asset.beforeChain(fromChain).equalsIgnoreCase(toChain.chainName()) &&
                        asset.denom.equalsIgnoreCase(denom)) {
                    return new AssetPath(asset.channel, asset.port);
                }
                if (asset.chain.equalsIgnoreCase(toChain.chainName()) &&
                        asset.beforeChain(toChain) != null && asset.beforeChain(toChain).equalsIgnoreCase(fromChain.chainName()) &&
                        asset.counter_party.denom.equalsIgnoreCase(denom)) {
                    return new AssetPath(asset.counter_party.channel, asset.counter_party.port);
                }

            } else if (msMintscanToken != null) {
                if (asset.chain.equalsIgnoreCase(toChain.chainName()) &&
                        asset.beforeChain(toChain) != null && asset.beforeChain(toChain).equalsIgnoreCase(fromChain.chainName()) &&
                        asset.counter_party.denom.equalsIgnoreCase(msMintscanToken.address)) {
                    return new AssetPath(asset.counter_party.channel, asset.counter_party.port);
                }
            }
        }
        return null;
    }

    public static String getGeckoId(BaseData baseData, ChainConfig chainConfig) {
        if (chainConfig == null) return "";
        final Asset asset = baseData.getAsset(chainConfig, chainConfig.mainDenom());
        if (asset != null) {
            return asset.coinGeckoId;
        }
        if (chainConfig.baseChain().equals(BNB_MAIN)) {
            return Binance.BNB_GECKO_ID;
        }
        if (chainConfig.baseChain().equals(OKEX_MAIN)) {
            return Okc.OKC_GECKO_ID;
        }
        return "";
    }

    public static String getMonikerImgUrl(ChainConfig chainConfig, String opAddress) {
        if (chainConfig == null) {
            return "";
        }
        return CHAIN_BASE_URL + chainConfig.chainName() + "/moniker/" + opAddress + ".png";
    }

    public static void showChainDp(Context c, ChainConfig chainConfig, CardView cardName, CardView cardBody, CardView cardRewardAddress) {
        if (chainConfig.baseChain().equals(OKEX_MAIN) || chainConfig.baseChain().equals(BNB_MAIN) ||
                chainConfig.baseChain().equals(FETCHAI_MAIN) || chainConfig.baseChain().equals(ONOMY_MAIN)) {
            cardRewardAddress.setVisibility(View.GONE);
        } else {
            cardRewardAddress.setVisibility(View.VISIBLE);
        }
        cardName.setCardBackgroundColor(ContextCompat.getColor(c, chainConfig.chainBgColor()));
        cardBody.setCardBackgroundColor(ContextCompat.getColor(c, chainConfig.chainBgColor()));
        cardRewardAddress.setCardBackgroundColor(ContextCompat.getColor(c, chainConfig.chainBgColor()));
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
            if (WKey.isValidEthAddress(address) && chainConfig.baseChain().equals(OKEX_MAIN)) {
                return true;
            }
            return false;
        }

        if (!WKey.isValidBech32(address)) {
            return false;
        }
        String addressPrefix = chainConfig.addressPrefix() + "1";
        if (address.startsWith(addressPrefix)) {
            return true;
        }
        return false;
    }

    public static ArrayList<BaseChain> getChainsFromAddress(String address) {
        if (address != null) {
            if (address.startsWith("0x")) {
                if (WKey.isValidEthAddress(address)) {
                    return Lists.newArrayList(EVMOS_MAIN, KAVA_MAIN, OKEX_MAIN, XPLA_MAIN, CANTO_MAIN);
                }
                return null;
            }

            if (!WKey.isValidBech32(address)) {
                return null;
            }
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
        final Param param = baseData.mParam;
        final ChainConfig chainConfig = ChainFactory.getChain(chain);
        BigDecimal apr = BigDecimal.ZERO;
        if (param != null) {
            apr = param.getApr(chainConfig);
        }
        BigDecimal calCommission = BigDecimal.ONE.subtract(commission);
        BigDecimal aprCommission = apr.multiply(calCommission).movePointRight(2);
        return getPercentDp(aprCommission);
    }

    public static SpannableString getDailyReward(BaseData baseData, BigDecimal commission, BigDecimal delegated, BaseChain chain) {
        final Param param = baseData.mParam;
        final ChainConfig chainConfig = ChainFactory.getChain(chain);
        BigDecimal apr = BigDecimal.ZERO;
        if (param != null) {
            if (BigDecimal.ZERO.compareTo(param.getRealApr(chainConfig)) == 0) {
                apr = param.getApr(chainConfig);
            } else {
                apr = param.getRealApr(chainConfig);
            }
        }
        BigDecimal calCommission = BigDecimal.ONE.subtract(commission);
        BigDecimal aprCommission = apr.multiply(calCommission);
        BigDecimal dayReward = delegated.multiply(aprCommission).divide(new BigDecimal("365"), 0, RoundingMode.DOWN);
        return getDpAmount2(dayReward, getDenomDecimal(baseData, chainConfig, chainConfig.mainDenom()), mainDisplayDecimal(chain));
    }

    public static SpannableString getMonthlyReward(BaseData baseData, BigDecimal commission, BigDecimal delegated, BaseChain chain) {
        final Param param = baseData.mParam;
        final ChainConfig chainConfig = ChainFactory.getChain(chain);
        BigDecimal apr = BigDecimal.ZERO;
        if (param != null) {
            if (BigDecimal.ZERO.compareTo(param.getRealApr(chainConfig)) == 0) {
                apr = param.getApr(chainConfig);
            } else {
                apr = param.getRealApr(chainConfig);
            }
        }
        BigDecimal calCommission = BigDecimal.ONE.subtract(commission);
        BigDecimal aprCommission = apr.multiply(calCommission);
        BigDecimal dayReward = delegated.multiply(aprCommission).divide(new BigDecimal("12"), 0, RoundingMode.DOWN);
        return getDpAmount2(dayReward, WDp.getDenomDecimal(baseData, chainConfig, chainConfig.mainDenom()), mainDisplayDecimal(chain));
    }

    public static BigDecimal kavaTokenDollorValue(BaseData baseData, ChainConfig chainConfig, String denom, BigDecimal
            amount) {
        int dpDecimal = WDp.getDenomDecimal(baseData, chainConfig, denom);
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
            Optional<Asset> asset = baseData.mAssets.stream().filter(item -> item.denom.equalsIgnoreCase(denom)).findFirst();

            if (asset.isPresent()) {
                if (denom.startsWith("ibc/")) {
                    return asset.get().origin_denom + ":usd";
                } else {
                    String priceDenom = "";
                    if (denom.equalsIgnoreCase(ChainFactory.getChain(BaseChain.KAVA_MAIN).mainDenom())) {
                        priceDenom = "kava";
                    } else if (denom.contains("btc")) {
                        priceDenom = "btc";
                    } else {
                        priceDenom = denom;
                    }
                    return priceDenom + ":usd";
                }
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

    public static BigDecimal bnbConvertAmount(BaseData baseData, String denom) {
        BnbTicker bnbTicker = baseData.getBnbTicker(denom);
        BigDecimal amount = baseData.getAllBnbTokenAmount(denom);

        if (bnbTicker != null) {
            if (bnbTicker.baseAssetName.equalsIgnoreCase(Binance.BNB_MAIN_DENOM)) {
                return amount.divide(new BigDecimal(bnbTicker.lastPrice), 8, RoundingMode.DOWN);
            } else {
                return amount.multiply(new BigDecimal(bnbTicker.lastPrice)).setScale(8, RoundingMode.DOWN);
            }
        }
        return BigDecimal.ZERO;
    }

    public static BigDecimal bnbTokenPrice(BaseData baseData, String denom) {
        BnbTicker bnbTicker = baseData.getBnbTicker(denom);
        if (bnbTicker != null) {
            BigDecimal perPrice;
            if (bnbTicker.baseAssetName.equalsIgnoreCase(Binance.BNB_MAIN_DENOM)) {
                perPrice = BigDecimal.ONE.divide(new BigDecimal(bnbTicker.lastPrice), 8, RoundingMode.DOWN);
            } else {
                perPrice = BigDecimal.ONE.multiply(new BigDecimal(bnbTicker.lastPrice)).setScale(8, RoundingMode.DOWN);
            }
            return perPrice.multiply(price(baseData, Binance.BNB_GECKO_ID));
        }
        return BigDecimal.ZERO;
    }

    public static SpannableString dpBnbTokenPrice(BaseData baseData, String denom) {
        final String formatted = baseData.getCurrencySymbol() + " " + WDp.getDecimalFormat(3).format(bnbTokenPrice(baseData, denom));
        return WDp.getDpString(formatted, 3);
    }

    public static BigDecimal okExTokenDollorValue(BaseData baseData, OkToken okToken, BigDecimal amount) {
        if (okToken != null && okToken.original_symbol != null) {
            if (okToken.original_symbol.equals("usdt") || okToken.original_symbol.equals("usdc") || okToken.original_symbol.equals("usdk")) {
                return amount;

            } else if (okToken.original_symbol.equals("okb") && baseData.mOKBPrice != null) {
                return amount.multiply(baseData.mOKBPrice);

            }
        }
        return BigDecimal.ZERO;
    }

    public static BigDecimal convertTokenToOkt(BaseData baseData, String denom) {
        OkToken okToken = baseData.okToken(denom);
        if (okToken != null) {
            BigDecimal tokenAmount = baseData.availableAmount(denom).add(baseData.lockedAmount(denom));
            BigDecimal totalTokenValue = okExTokenDollorValue(baseData, okToken, tokenAmount);
            if (!BigDecimal.ZERO.equals(perUsdValue(baseData, Okc.OKC_MAIN_DENOM))) {
                return totalTokenValue.divide(perUsdValue(baseData, Okc.OKC_MAIN_DENOM), 18, RoundingMode.DOWN);
            }
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
    public static BigDecimal priceChange(BaseData baseData, String coinGeckoId) {
        Price coinPrice = baseData.getPrice(coinGeckoId);
        if (coinPrice != null) {
            return new BigDecimal(coinPrice.daily_price_change_in_percentage).setScale(2, RoundingMode.FLOOR);
        } else {
            return BigDecimal.ZERO.setScale(2, RoundingMode.FLOOR);
        }
    }

    public static BigDecimal price(BaseData baseData, String coinGeckoId) {
        Price coinPrice = baseData.getPrice(coinGeckoId);
        if (coinPrice != null) {
            return new BigDecimal(coinPrice.current_price);
        } else {
            return BigDecimal.ZERO.setScale(3, RoundingMode.DOWN);
        }
    }

    public static SpannableString dpPrice(BaseData baseData, String coinGeckoId) {
        final String formatted = baseData.getCurrencySymbol() + " " + getDecimalFormat(3).format(price(baseData, coinGeckoId));
        return dpCurrencyValue(formatted, 3);
    }

    public static BigDecimal assetValue(BaseData baseData, String coinGeckoId, BigDecimal amount, int divider) {
        return price(baseData, coinGeckoId).multiply(amount).movePointLeft(divider).setScale(3, RoundingMode.DOWN);
    }

    public static SpannableString dpAssetValue(BaseData baseData, String coinGeckoId, BigDecimal amount, int divider) {
        BigDecimal totalValue = assetValue(baseData, coinGeckoId, amount, divider);
        final String formatted = baseData.getCurrencySymbol() + " " + getDecimalFormat(3).format(totalValue);
        return dpCurrencyValue(formatted, 3);
    }

    public static BigDecimal perUsdValue(BaseData baseData, String denom) {
        return BigDecimal.ZERO;
    }

    public static BigDecimal usdValue(BaseData baseData, String denom, BigDecimal amount, int divider) {
        return BigDecimal.ZERO;
    }

    public static BigDecimal allAssetValue(BaseChain baseChain, BaseData baseData, ChainConfig chainConfig) {
        BigDecimal totalValue = BigDecimal.ZERO;
        if (isGRPC(baseChain)) {
            for (Coin coin : baseData.mGrpcBalance) {
                final Asset asset = baseData.getAsset(chainConfig, coin.denom);
                if (asset != null) {
                    if (asset.type.equalsIgnoreCase("staking")) {
                        BigDecimal totalAmount = baseData.getAllMainAsset(asset.denom);
                        BigDecimal assetValue = assetValue(baseData, asset.coinGeckoId, totalAmount, asset.decimals);
                        totalValue = totalValue.add(assetValue);

                    } else if (asset.type.equalsIgnoreCase("native")) {
                        BigDecimal totalAmount = baseData.getAvailable(asset.denom).add(baseData.getVesting(asset.denom));
                        BigDecimal assetValue = assetValue(baseData, asset.coinGeckoId, totalAmount, asset.decimals);
                        totalValue = totalValue.add(assetValue);

                    } else {
                        BigDecimal totalAmount = baseData.getAvailable(asset.denom);
                        BigDecimal assetValue = assetValue(baseData, asset.coinGeckoId, totalAmount, asset.decimals);
                        totalValue = totalValue.add(assetValue);
                    }
                }
            }

        } else if (baseChain.equals(BNB_MAIN)) {
            for (Balance balance : baseData.mBalances) {
                BigDecimal allBnb = BigDecimal.ZERO;
                BigDecimal amount = baseData.getAllBnbTokenAmount(balance.symbol);
                if (balance.symbol.equals(chainConfig.mainDenom())) {
                    allBnb = allBnb.add(amount);
                } else {
                    allBnb = allBnb.add(bnbConvertAmount(baseData, balance.symbol));
                }
                BigDecimal assetValue = assetValue(baseData, Binance.BNB_GECKO_ID, allBnb, 0);
                totalValue = totalValue.add(assetValue);
            }

        } else if (baseChain.equals(OKEX_MAIN)) {
            for (Balance balance : baseData.mBalances) {
                BigDecimal allOKT = BigDecimal.ZERO;
                if (balance.symbol.equals(chainConfig.mainDenom())) {
                    allOKT = allOKT.add(baseData.getAllExToken(balance.symbol));
                }
                BigDecimal assetValue = assetValue(baseData, Okc.OKC_GECKO_ID, allOKT, 0);
                totalValue = totalValue.add(assetValue);
            }
        }

        if (baseData.mCw20MyTokens.size() > 0) {
            for (MintscanToken myAsset : baseData.mCw20MyTokens) {
                BigDecimal amount = myAsset.getAmount();
                totalValue = totalValue.add(assetValue(baseData, myAsset.coinGeckoId, amount, myAsset.decimals));
            }

        } else if (baseData.mErc20MyTokens.size() > 0) {
            for (MintscanToken myAsset : baseData.mErc20MyTokens) {
                BigDecimal amount = myAsset.getAmount();
                totalValue = totalValue.add(assetValue(baseData, myAsset.coinGeckoId, amount, myAsset.decimals));
            }
        }
        return totalValue;
    }

    public static SpannableString dpAllAssetValue(BaseChain baseChain, BaseData baseData, ChainConfig chainConfig) {
        BigDecimal totalValue = allAssetValue(baseChain, baseData, chainConfig);
        final String formatted = baseData.getCurrencySymbol() + " " + getDecimalFormat(3).format(totalValue);
        return dpCurrencyValue(formatted, 3);
    }

    public static SpannableString dpCurrencyValue(String input, int dpPoint) {
        return getDpString(input, dpPoint);
    }

    public static void valueChangeStatus(Context c, BaseData baseData, String coinGeckoId, TextView changeTxt) {
        BigDecimal lastUpDown = WDp.priceChange(baseData, coinGeckoId);
        if (BigDecimal.ZERO.compareTo(lastUpDown) > 0) {
            if (baseData.getPriceColorOption() == 1) {
                changeTxt.setTextColor(ContextCompat.getColor(c, R.color.colorVoteNo));
            } else {
                changeTxt.setTextColor(ContextCompat.getColor(c, R.color.colorVoteYes));
            }
            changeTxt.setText(getDpString(lastUpDown.toPlainString() + "%", 3));
        } else if (BigDecimal.ZERO.compareTo(lastUpDown) < 0) {
            if (baseData.getPriceColorOption() == 1) {
                changeTxt.setTextColor(ContextCompat.getColor(c, R.color.colorVoteYes));
            } else {
                changeTxt.setTextColor(ContextCompat.getColor(c, R.color.colorVoteNo));
            }
            changeTxt.setText(getDpString("+" + lastUpDown.toPlainString() + "%", 3));
        } else {
            changeTxt.setText("");
        }
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

    public static String getOkcDpTime(Context c, long txTime) {
        String result = "??";
        try {
            Calendar calendar = Calendar.getInstance();
            long timeZone = TimeZone.getDefault().getOffset(new Date().getTime());
            calendar.setTimeInMillis(txTime + timeZone);
            SimpleDateFormat simpleFormat = new SimpleDateFormat(c.getString(R.string.str_dp_time_format1));
            result = simpleFormat.format(calendar.getTimeInMillis());
        } catch (Exception e) {
        }

        return result;
    }

    public static String getUnbondTime(Context c, BaseData baseData, BaseChain baseChain) {
        String result = "??";
        try {
            if (baseData != null && baseData.mParam != null) {
                Calendar calendar = Calendar.getInstance();
                calendar.add(Calendar.DATE, baseData.mParam.getUnbonding(baseChain));
                SimpleDateFormat unbondFormat = new SimpleDateFormat(c.getString(R.string.str_dp_time_format2));
                result = unbondFormat.format(calendar.getTimeInMillis());
            }
            return result + "   " + "(" + baseData.mParam.getUnbonding(baseChain) + c.getString(R.string.str_unbonding_days_after);
        } catch (Exception e) {
            return result;
        }
    }

    public static String getGapTime(long finishTime) {
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
            } else {
                result = "(" + (left / BaseConstant.CONSTANT_S) + " seconds ago)";
            }

        } catch (Exception e) {
        }

        return result;
    }

    public static String getTimeWithoutTransVerse(long finishTime) {
        String remainTime = getGapTime(finishTime);
        return remainTime.substring(1, remainTime.length() - 1);
    }

    public static long convertDateToLong(String format, String rawValue) {
        long result = 0;
        try {
            SimpleDateFormat blockDateFormat = new SimpleDateFormat(format);
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

    public static String getOkcTimeTxGap(Context c, long rawValue) {
        String result = "";
        try {
            long timeZone = TimeZone.getDefault().getOffset(new Date().getTime());
            long txTime = rawValue + timeZone;
            long now = Calendar.getInstance().getTimeInMillis();
            long difference = now - txTime;

            long differenceDays = difference / (24 * 60 * 60 * 1000);

            if (differenceDays > 1) {
                result = "(" + "" + differenceDays + " " + c.getString(R.string.str_day) + "" + c.getString(R.string.str_ago) + ")";
            } else {
                result = "(" + "" + "D-Day" + "" + ")";
            }

        } catch (Exception e) {
        }

        return result;
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

    public static int mainDisplayDecimal(BaseChain chain) {
        if (isGRPC(chain)) {
            return ChainFactory.getChain(chain).decimal();
        } else {
            if (chain.equals(BNB_MAIN)) {
                return 8;
            } else if (chain.equals(OKEX_MAIN)) {
                return 18;
            }
        }
        return 6;
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

    public static BigDecimal getTurnout(BaseData baseData, ResProposal proposal) {
        BigDecimal result = BigDecimal.ZERO;
        if (baseData != null && baseData.mParam != null) {
            if (geTallySum(proposal).equals(BigDecimal.ZERO)) {
                return BigDecimal.ZERO.setScale(2);

            } else {
                BigDecimal bonded = baseData.mParam.getTurnoutBondedAmount();
                return geTallySum(proposal).movePointRight(2).divide(bonded, 2, RoundingMode.HALF_UP);
            }
        }
        return result;
    }

    public static Coin onParseFee(ChainConfig chainConfig, ServiceOuterClass.GetTxResponse response) {
        if (response.getTx().getAuthInfo().getFee().getAmountCount() > 0) {
            return new Coin(response.getTx().getAuthInfo().getFee().getAmount(0).getDenom(), response.getTx().getAuthInfo().getFee().getAmount(0).getAmount());
        } else {
            return new Coin(chainConfig.mainDenom(), "0");
        }
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

    public static ArrayList<Coin> onParseStakeReward(ServiceOuterClass.GetTxResponse response, int position) {
        ArrayList<Coin> result = new ArrayList<>();
        if (response.getTxResponse().getLogsCount() > 0 && response.getTxResponse().getLogs(position) != null) {
            for (Abci.StringEvent event : response.getTxResponse().getLogs(position).getEventsList()) {
                if (event.getType().equals("withdraw_rewards")) {
                    for (int i = 0; i < event.getAttributesList().size(); i++) {
                        if (event.getAttributes(i).getKey().equals("amount")) {
                            String rawValue = event.getAttributes(i).getValue();
                            if (rawValue != null) {
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

    public static ArrayList<Coin> onParseLiquidAmountGrpc(ServiceOuterClass.GetTxResponse response, int position) {
        ArrayList<Coin> result = new ArrayList<>();
        if (response.getTxResponse().getLogsCount() > 0 && response.getTxResponse().getLogs(position) != null) {
            for (Abci.StringEvent event : response.getTxResponse().getLogs(position).getEventsList()) {
                if (event.getType().equals("transfer")) {
                    for (int i = 0; i < event.getAttributesList().size(); i++) {
                        if (event.getAttributes(i).getKey().equals("recipient")) {
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

    public static ArrayList<Vesting.Period> onParseStridePeriodicRemainVestingsByDenom(StridePeriodicVestingAccount vestingAccount, String denom) {
        ArrayList<Vesting.Period> result = new ArrayList<>();
        long cTime = Calendar.getInstance().getTime().getTime();
        for (stride.vesting.Vesting.Period period : vestingAccount.getVestingPeriodsList()) {
            long vestingEnd = (period.getStartTime() + period.getLength()) * 1000;
            if (cTime < vestingEnd) {
                for (CoinOuterClass.Coin vesting : period.getAmountList()) {
                    if (vesting.getDenom().equals(denom)) {
                        result.add(Vesting.Period.newBuilder().setLength(vestingEnd).addAllAmount(period.getAmountList()).build());
                    }
                }
            }
        }
        return result;
    }

    public static BigDecimal onParseStridePeriodicRemainVestingsAmountByDenom(StridePeriodicVestingAccount vestingAccount, String denom) {
        BigDecimal result = BigDecimal.ZERO;
        ArrayList<Vesting.Period> vpList = onParseStridePeriodicRemainVestingsByDenom(vestingAccount, denom);
        for (Vesting.Period vp : vpList) {
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
