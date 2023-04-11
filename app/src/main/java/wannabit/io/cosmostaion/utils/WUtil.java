package wannabit.io.cosmostaion.utils;

import static wannabit.io.cosmostaion.base.BaseChain.AKASH_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.BAND_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.BNB_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.CERTIK_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.COSMOS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.CRYPTO_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.IOV_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.IRIS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.KAVA_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.OKEX_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.OSMOSIS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.PERSIS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.REGEN_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.RIZON_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.SECRET_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.SENTINEL_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.SIF_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.STRIDE_MAIN;
import static wannabit.io.cosmostaion.base.BaseConstant.BINANCE_MAIN_BNB_DEPUTY;
import static wannabit.io.cosmostaion.base.BaseConstant.BINANCE_MAIN_BTCB_DEPUTY;
import static wannabit.io.cosmostaion.base.BaseConstant.BINANCE_MAIN_BUSD_DEPUTY;
import static wannabit.io.cosmostaion.base.BaseConstant.BINANCE_MAIN_XRPB_DEPUTY;
import static wannabit.io.cosmostaion.base.BaseConstant.COSMOS_AUTH_TYPE_OKEX_ACCOUNT;
import static wannabit.io.cosmostaion.base.BaseConstant.EXCHANGE_BINANCE_ADDRESS_01;
import static wannabit.io.cosmostaion.base.BaseConstant.EXCHANGE_BINANCE_ADDRESS_02;
import static wannabit.io.cosmostaion.base.BaseConstant.EXCHANGE_BINANCE_ADDRESS_03;
import static wannabit.io.cosmostaion.base.BaseConstant.EXCHANGE_BITHUMB_ADDRESS;
import static wannabit.io.cosmostaion.base.BaseConstant.EXCHANGE_COINONE_ADDRESS;
import static wannabit.io.cosmostaion.base.BaseConstant.EXCHANGE_DIGFINEX_ADDRESS;
import static wannabit.io.cosmostaion.base.BaseConstant.EXCHANGE_HITBTC_ADDRESS;
import static wannabit.io.cosmostaion.base.BaseConstant.EXCHANGE_MEXC_ADDRESS;
import static wannabit.io.cosmostaion.base.BaseConstant.EXCHANGE_UPBIT_ADDRESS;
import static wannabit.io.cosmostaion.base.BaseConstant.KAVA_MAIN_BNB_DEPUTY;
import static wannabit.io.cosmostaion.base.BaseConstant.KAVA_MAIN_BTCB_DEPUTY;
import static wannabit.io.cosmostaion.base.BaseConstant.KAVA_MAIN_BUSD_DEPUTY;
import static wannabit.io.cosmostaion.base.BaseConstant.KAVA_MAIN_XRPB_DEPUTY;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HTLC_BINANCE_BNB;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HTLC_BINANCE_BTCB;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HTLC_BINANCE_BUSD;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HTLC_BINANCE_XRPB;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HTLC_KAVA_BNB;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HTLC_KAVA_BTCB;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HTLC_KAVA_BUSD;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HTLC_KAVA_XRPB;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Typeface;
import android.media.ExifInterface;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf2.Any;
import com.google.zxing.common.BitMatrix;

import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.nio.ByteBuffer;
import java.security.cert.CertificateException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import cosmos.base.v1beta1.CoinOuterClass;
import cosmos.distribution.v1beta1.Distribution;
import cosmos.staking.v1beta1.Staking;
import cosmos.vesting.v1beta1.Vesting;
import kava.cdp.v1beta1.Genesis;
import kava.hard.v1beta1.Hard;
import okhttp3.OkHttpClient;
import osmosis.gamm.v1beta1.BalancerPool;
import sifnode.clp.v1.Querier;
import starnamed.x.starname.v1beta1.Types;
import stride.vesting.Vesting.StridePeriodicVestingAccount;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.MainActivity;
import wannabit.io.cosmostaion.activities.txs.kava.DAppsList5Activity;
import wannabit.io.cosmostaion.activities.txs.liquidstaking.PersisLSActivity;
import wannabit.io.cosmostaion.activities.txs.liquidstaking.StrideLSActivity;
import wannabit.io.cosmostaion.activities.txs.nft.NFTListActivity;
import wannabit.io.cosmostaion.activities.txs.osmosis.SwapViewActivity;
import wannabit.io.cosmostaion.activities.txs.sif.SifDexListActivity;
import wannabit.io.cosmostaion.activities.txs.starname.StarNameListActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.base.chains.ChainConfig;
import wannabit.io.cosmostaion.base.chains.ChainFactory;
import wannabit.io.cosmostaion.base.chains.Kava;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.dao.Balance;
import wannabit.io.cosmostaion.dao.MintscanToken;
import wannabit.io.cosmostaion.model.ExportStarName;
import wannabit.io.cosmostaion.model.UnbondingInfo;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.model.type.Validator;
import wannabit.io.cosmostaion.network.res.ResBnbAccountInfo;
import wannabit.io.cosmostaion.network.res.ResOkAccountInfo;
import wannabit.io.cosmostaion.network.res.ResOkAccountToken;

public class WUtil {

    public static Account getAccountFromBnbLcd(long id, ResBnbAccountInfo lcd) {
        Account result = new Account();
        result.id = id;
        result.address = lcd.address;
        result.sequenceNumber = Integer.parseInt(lcd.sequence);
        result.accountNumber = Integer.parseInt(lcd.account_number);
        return result;
    }

    public static Account getAccountFromOkLcd(long id, ResOkAccountInfo lcd) {
        Account result = new Account();
        result.id = id;
        if (lcd.type.equals(COSMOS_AUTH_TYPE_OKEX_ACCOUNT)) {
            result.address = lcd.value.eth_address;
            result.sequenceNumber = Integer.parseInt(lcd.value.sequence);
            result.accountNumber = Integer.parseInt(lcd.value.account_number);
        }
        return result;
    }

    public static ArrayList<Balance> getBalancesFromBnbLcd(long accountId, ResBnbAccountInfo lcd) {
        long time = System.currentTimeMillis();
        ArrayList<Balance> result = new ArrayList<>();
        if (lcd.balances != null && lcd.balances.size() > 0) {
            for (ResBnbAccountInfo.BnbBalance coin : lcd.balances) {
                Balance temp = new Balance();
                temp.accountId = accountId;
                temp.symbol = coin.symbol;
                temp.balance = new BigDecimal(coin.free);
                temp.locked = new BigDecimal(coin.locked);
                temp.frozen = new BigDecimal(coin.frozen);
                temp.fetchTime = time;
                result.add(temp);
            }
        }
        return result;
    }

    public static ArrayList<Balance> getBalancesFromOkLcd(long accountId, ResOkAccountToken lcd) {
        long time = System.currentTimeMillis();
        ArrayList<Balance> result = new ArrayList<>();
        if (lcd.data != null && lcd.data.currencies != null && lcd.data.currencies.size() > 0) {
            for (ResOkAccountToken.OkCurrency currency : lcd.data.currencies) {
                Balance temp = new Balance();
                temp.accountId = accountId;
                temp.symbol = currency.symbol;
                temp.balance = new BigDecimal(currency.available);
                temp.locked = new BigDecimal(currency.locked);
                temp.fetchTime = time;
                result.add(temp);
            }
        }
        return result;
    }

    public static String prettyPrinter(Object object) {
        String result = "";
        try {
            result = new ObjectMapper().writer().withDefaultPrettyPrinter().writeValueAsString(object);
        } catch (Exception e) {
            result = "Print json error";
        }
        return result;
    }


    public static boolean checkPasscodePattern(String pincode) {
        if (pincode.length() != 5)
            return false;
        String regex = "^\\d{4}+[A-Z]{1}$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(pincode);
        boolean isNormal = m.matches();
        return isNormal;
    }

    public static Gson getPresentor() {
//        return new GsonBuilder().disableHtmlEscaping().serializeNulls().create();
        return new GsonBuilder().disableHtmlEscaping().create();
    }


    public static String ByteArrayToHexString(byte[] bytes) {
        final char[] hexArray = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        char[] hexChars = new char[bytes.length * 2];
        int v;
        for (int j = 0; j < bytes.length; j++) {
            v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }

    public static byte[] HexStringToByteArray(String s) throws IllegalArgumentException {
        int len = s.length();
        if (len % 2 == 1) {
            throw new IllegalArgumentException("Hex string must have even number of characters");
        }
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }

    public static byte[] integerToBytes(BigInteger s, int length) {
        byte[] bytes = s.toByteArray();

        if (length < bytes.length) {
            byte[] tmp = new byte[length];
            System.arraycopy(bytes, bytes.length - tmp.length, tmp, 0, tmp.length);
            return tmp;
        } else if (length > bytes.length) {
            byte[] tmp = new byte[length];
            System.arraycopy(bytes, 0, tmp, tmp.length - bytes.length, bytes.length);
            return tmp;
        }
        return bytes;
    }

    public static String str2Hex(String bin) {
        char[] digital = "0123456789abcdef".toCharArray();
        StringBuffer sb = new StringBuffer("");
        byte[] bs = bin.getBytes();
        int bit;
        for (int i = 0; i < bs.length; i++) {
            bit = (bs[i] & 0x0f0) >> 4;
            sb.append(digital[bit]);
            bit = bs[i] & 0x0f;
            sb.append(digital[bit]);
        }
        return sb.toString();
    }

    public static String hex2Str(String hex) {
        String digital = "0123456789abcdef";
        char[] hex2char = hex.toCharArray();
        byte[] bytes = new byte[hex.length() / 2];
        int temp;
        for (int i = 0; i < bytes.length; i++) {
            temp = digital.indexOf(hex2char[2 * i]) * 16;
            temp += digital.indexOf(hex2char[2 * i + 1]);
            bytes[i] = (byte) (temp & 0xff);
        }
        return new String(bytes);
    }

    public static String bytes2Hex(byte[] raw) {
        String HEXES = "0123456789ABCDEF";
        if (raw == null) {
            return null;
        }
        final StringBuilder hex = new StringBuilder(2 * raw.length);
        for (final byte b : raw) {
            hex.append(HEXES.charAt((b & 0xF0) >> 4))
                    .append(HEXES.charAt((b & 0x0F)));
        }
        return hex.toString();
    }

    public static byte[] hex2Byte(String hex) {
        return new BigInteger(hex, 16).toByteArray();
    }


    //TODO for ssh ignore test
    public static OkHttpClient.Builder getUnsafeOkHttpClient() {
        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }
                    }
            };

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());

            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.sslSocketFactory(sslSocketFactory, (X509TrustManager) trustAllCerts[0]);
            builder.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });
            return builder;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Bitmap toBitmap(BitMatrix matrix) {
        int height = matrix.getHeight();
        int width = matrix.getWidth();
        Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                bmp.setPixel(x, y, matrix.get(x, y) ? Color.BLACK : Color.WHITE);
            }
        }
        return bmp;
    }


    /**
     * Sorts
     */
    public static void onSortByValidatorName(ArrayList<Validator> validators) {
        Collections.sort(validators, (o1, o2) -> {
            if ("Cosmostation".equalsIgnoreCase(o1.description.moniker)) return -1;
            if ("Cosmostation".equalsIgnoreCase(o2.description.moniker)) return 1;
            return o1.description.moniker.compareTo(o2.description.moniker);
        });
        Collections.sort(validators, (o1, o2) -> {
            if (o1.jailed && !o2.jailed) return 1;
            else if (!o1.jailed && o2.jailed) return -1;
            else return 0;
        });
    }

    public static void onSortByValidatorNameV1(ArrayList<Staking.Validator> validators) {
        Collections.sort(validators, (o1, o2) -> {
            if ("Cosmostation".equalsIgnoreCase(o1.getDescription().getMoniker())) return -1;
            if ("Cosmostation".equalsIgnoreCase(o2.getDescription().getMoniker())) return 1;
            return o1.getDescription().getMoniker().compareTo(o2.getDescription().getMoniker());
        });
        Collections.sort(validators, (o1, o2) -> {
            if (o1.getJailed() && !o2.getJailed()) return 1;
            else if (!o1.getJailed() && o2.getJailed()) return -1;
            else return 0;
        });
    }

    public static void onSortByValidatorPower(ArrayList<Validator> validators) {
        Collections.sort(validators, (o1, o2) -> {
            if ("Cosmostation".equalsIgnoreCase(o1.description.moniker)) return -1;
            if ("Cosmostation".equalsIgnoreCase(o2.description.moniker)) return 1;

            if (Double.parseDouble(o1.tokens) > Double.parseDouble(o2.tokens)) return -1;
            else if (Double.parseDouble(o1.tokens) < Double.parseDouble(o2.tokens)) return 1;
            else return 0;
        });
        Collections.sort(validators, (o1, o2) -> {
            if (o1.jailed && !o2.jailed) return 1;
            else if (!o1.jailed && o2.jailed) return -1;
            else return 0;
        });
    }

    public static void onSortByValidatorPowerV1(ArrayList<Staking.Validator> validators) {
        Collections.sort(validators, (o1, o2) -> {
            if ("Cosmostation".equalsIgnoreCase(o1.getDescription().getMoniker())) return -1;
            if ("Cosmostation".equalsIgnoreCase(o2.getDescription().getMoniker())) return 1;

            if (Double.parseDouble(o1.getTokens()) > Double.parseDouble(o2.getTokens()))
                return -1;
            else if (Double.parseDouble(o1.getTokens()) < Double.parseDouble(o2.getTokens()))
                return 1;
            else return 0;
        });
        Collections.sort(validators, (o1, o2) -> {
            if (o1.getJailed() && !o2.getJailed()) return 1;
            else if (!o1.getJailed() && o2.getJailed()) return -1;
            else return 0;
        });
    }

    public static void onSortByOKValidatorPower(ArrayList<Validator> validators) {
        Collections.sort(validators, (o1, o2) -> {
            if ("Cosmostation".equalsIgnoreCase(o1.description.moniker)) return -1;
            if ("Cosmostation".equalsIgnoreCase(o2.description.moniker)) return 1;

            if (Double.parseDouble(o1.delegator_shares) > Double.parseDouble(o2.delegator_shares))
                return -1;
            else if (Double.parseDouble(o1.delegator_shares) < Double.parseDouble(o2.delegator_shares))
                return 1;
            else return 0;
        });
        Collections.sort(validators, (o1, o2) -> {
            if (o1.jailed && !o2.jailed) return 1;
            else if (!o1.jailed && o2.jailed) return -1;
            else return 0;
        });
    }


    public static void onSortByDelegate(ArrayList<Validator> validators, final BaseData dao) {
        Collections.sort(validators, (o1, o2) -> {
            if ("Cosmostation".equalsIgnoreCase(o1.description.moniker)) return -1;
            if ("Cosmostation".equalsIgnoreCase(o2.description.moniker)) return 1;
            BigDecimal bondingO1 = dao.delegatedAmountByValidator(o1.operator_address);
            BigDecimal bondingO2 = dao.delegatedAmountByValidator(o2.operator_address);
            return bondingO2.compareTo(bondingO1);

        });
        Collections.sort(validators, (o1, o2) -> {
            if (o1.jailed && !o2.jailed) return 1;
            else if (!o1.jailed && o2.jailed) return -1;
            else return 0;
        });
    }

    public static void onSortByDelegateV1(ArrayList<Staking.Validator> validators, final BaseData dao) {
        Collections.sort(validators, (o1, o2) -> {
            if ("Cosmostation".equalsIgnoreCase(o1.getDescription().getMoniker())) return -1;
            if ("Cosmostation".equalsIgnoreCase(o2.getDescription().getMoniker())) return 1;
            BigDecimal bondingO1 = dao.getDelegation(o1.getOperatorAddress());
            BigDecimal bondingO2 = dao.getDelegation(o2.getOperatorAddress());
            return bondingO2.compareTo(bondingO1);
        });
        Collections.sort(validators, (o1, o2) -> {
            if (o1.getJailed() && !o2.getJailed()) return 1;
            else if (!o1.getJailed() && o2.getJailed()) return -1;
            else return 0;
        });
    }

    public static void onSortByReward(ArrayList<Validator> validators, String denom, BaseData basedata) {
        Collections.sort(validators, (o1, o2) -> {
            if ("Cosmostation".equalsIgnoreCase(o1.description.moniker)) return -1;
            if ("Cosmostation".equalsIgnoreCase(o2.description.moniker)) return 1;

            BigDecimal rewardO1 = basedata.rewardAmountByValidator(denom, o1.operator_address);
            BigDecimal rewardO2 = basedata.rewardAmountByValidator(denom, o2.operator_address);
            return rewardO2.compareTo(rewardO1);
        });
        Collections.sort(validators, (o1, o2) -> {
            if (o1.jailed && !o2.jailed) return 1;
            else if (!o1.jailed && o2.jailed) return -1;
            else return 0;
        });
    }

    public static void onSortByRewardV1(ArrayList<Staking.Validator> validators, String denom, final BaseData dao) {
        Collections.sort(validators, (o1, o2) -> {
            if ("Cosmostation".equalsIgnoreCase(o1.getDescription().getMoniker())) return -1;
            if ("Cosmostation".equalsIgnoreCase(o2.getDescription().getMoniker())) return 1;
            BigDecimal rewardO1 = dao.getReward(denom, o1.getOperatorAddress());
            BigDecimal rewardO2 = dao.getReward(denom, o2.getOperatorAddress());
            return rewardO2.compareTo(rewardO1);
        });
        Collections.sort(validators, (o1, o2) -> {
            if (o1.getJailed() && !o2.getJailed()) return 1;
            else if (!o1.getJailed() && o2.getJailed()) return -1;
            else return 0;
        });
    }

    public static void onSortRewardAmount(ArrayList<Distribution.DelegationDelegatorReward> rewards, String denom) {
        Collections.sort(rewards, (o1, o2) -> {
            BigDecimal rewardO1 = getGrpcRewardAmount(o1, denom);
            BigDecimal rewardO2 = getGrpcRewardAmount(o2, denom);
            return rewardO2.compareTo(rewardO1);
        });
    }

    public static BigDecimal getGrpcRewardAmount(Distribution.DelegationDelegatorReward reward, String denom) {
        BigDecimal result = BigDecimal.ZERO;
        result = decCoinAmount(reward.getRewardList(), denom);
        return result;
    }

    public static BigDecimal decCoinAmount(List<CoinOuterClass.DecCoin> coins, String denom) {
        BigDecimal result = BigDecimal.ZERO;
        for (CoinOuterClass.DecCoin coin : coins) {
            if (coin.getDenom().equals(denom)) {
                return new BigDecimal(coin.getAmount()).movePointLeft(18).setScale(0, RoundingMode.DOWN);
            }
        }
        return result;
    }

    public static void onSortingByCommission(ArrayList<Validator> validators, final BaseChain chain) {
        Collections.sort(validators, (o1, o2) -> {
            if ("Cosmostation".equalsIgnoreCase(o1.description.moniker)) return -1;
            if ("Cosmostation".equalsIgnoreCase(o2.description.moniker)) return 1;
            if (Float.parseFloat(o1.commission.commission_rates.rate) > Float.parseFloat(o2.commission.commission_rates.rate))
                return 1;
            else if (Float.parseFloat(o1.commission.commission_rates.rate) < Float.parseFloat(o2.commission.commission_rates.rate))
                return -1;
            else return 0;
        });
        Collections.sort(validators, (o1, o2) -> {
            if (o1.jailed && !o2.jailed) return 1;
            else if (!o1.jailed && o2.jailed) return -1;
            else return 0;
        });
    }

    public static void onSortingByCommissionV1(ArrayList<Staking.Validator> validators) {
        Collections.sort(validators, (o1, o2) -> {
            if ("Cosmostation".equalsIgnoreCase(o1.getDescription().getMoniker())) return -1;
            if ("Cosmostation".equalsIgnoreCase(o2.getDescription().getMoniker())) return 1;
            if (Float.parseFloat(o1.getCommission().getCommissionRates().getRate()) > Float.parseFloat(o2.getCommission().getCommissionRates().getRate()))
                return 1;
            else if (Float.parseFloat(o1.getCommission().getCommissionRates().getRate()) < Float.parseFloat(o2.getCommission().getCommissionRates().getRate()))
                return -1;
            else return 0;
        });
        Collections.sort(validators, (o1, o2) -> {
            if (o1.getJailed() && !o2.getJailed()) return 1;
            else if (!o1.getJailed() && o2.getJailed()) return -1;
            else return 0;
        });
    }

    public static void onSortingDenom(ArrayList<String> denom, BaseChain chain) {
        ChainConfig chainConfig = ChainFactory.getChain(chain);
        Collections.sort(denom, (o1, o2) -> {
            if (o1.equals(chainConfig.mainDenom())) return -1;
            if (o2.equals(chainConfig.mainDenom())) return 1;

            if (chain.equals(KAVA_MAIN)) {
                if (o1.equals(Kava.KAVA_HARD_DENOM)) return -1;
                if (o2.equals(Kava.KAVA_HARD_DENOM)) return 1;

            }
            return 0;
        });
    }

    public static void onSortingNativeCoins(ArrayList<Balance> balances, final BaseChain chain) {
        ChainConfig chainConfig = ChainFactory.getChain(chain);
        Collections.sort(balances, new Comparator<Balance>() {
            @Override
            public int compare(Balance o1, Balance o2) {
                if (o1.symbol.equals(chainConfig.mainDenom())) return -1;
                if (o2.symbol.equals(chainConfig.mainDenom())) return 1;

                if (chain.equals(OKEX_MAIN)) {
                    if (o1.symbol.equals("okb-c4d")) return -1;
                    if (o2.symbol.equals("okb-c4d")) return 1;
                }
                return o1.symbol.compareTo(o2.symbol);
            }
        });
    }

    public static void onSortingCoins(ArrayList<Coin> coins, BaseChain chain) {
        ChainConfig chainConfig = ChainFactory.getChain(chain);
        Collections.sort(coins, (o1, o2) -> {
            if (o1.denom.equals(chainConfig.mainDenom())) return -1;
            if (o2.denom.equals(chainConfig.mainDenom())) return 1;
            else return 0;
        });
    }

    public static void onSortingContract(ArrayList<MintscanToken> denom) {
        Collections.sort(denom, new Comparator<MintscanToken>() {
            @Override
            public int compare(MintscanToken o1, MintscanToken o2) {
                if (o1.symbol.equalsIgnoreCase("NETA")) return -1;
                if (o2.symbol.equalsIgnoreCase("NETA")) return 1;
                if (o1.symbol.equalsIgnoreCase("MARBLE")) return -1;
                if (o2.symbol.equalsIgnoreCase("MARBLE")) return 1;

                if (o1.symbol.equalsIgnoreCase("WEVMOS")) return -1;
                if (o2.symbol.equalsIgnoreCase("WEVMOS")) return 1;
                return 0;
            }
        });
    }

    public static ArrayList<UnbondingInfo.DpEntry> onSortUnbondingsRecent(Context c, ArrayList<UnbondingInfo> unbondingInfos) {
        ArrayList<UnbondingInfo.DpEntry> result = new ArrayList<>();
        for (UnbondingInfo unbondingInfo : unbondingInfos) {
            for (UnbondingInfo.Entry entry : unbondingInfo.entries) {
                result.add(new UnbondingInfo.DpEntry(unbondingInfo.validator_address, entry.completion_time, entry.balance));
            }
        }

        Collections.sort(result, (o1, o2) -> WDp.convertDateToLong(c.getString(R.string.str_block_time_format), o1.completion_time) <
                WDp.convertDateToLong(c.getString(R.string.str_block_time_format), o2.completion_time) ? -1 : 1);
        return result;
    }

    public static ArrayList<UnbondingInfo.DpEntry> onSortUnbondingsRecent_Grpc(Context c, ArrayList<Staking.UnbondingDelegation> unbondingGrpcInfos) {
        ArrayList<UnbondingInfo.DpEntry> result = new ArrayList<>();
        for (Staking.UnbondingDelegation unbondingGrpcInfo : unbondingGrpcInfos) {
            for (Staking.UnbondingDelegationEntry entry : unbondingGrpcInfo.getEntriesList()) {
                result.add(new UnbondingInfo.DpEntry(unbondingGrpcInfo.getValidatorAddress(), String.valueOf(entry.getCompletionTime().getSeconds()), entry.getBalance()));
            }
        }

        Collections.sort(result, (o1, o2) -> Long.parseLong(o1.completion_time) < Long.parseLong(o2.completion_time) ? -1 : 1);
        return result;
    }

    /**
     * @memo size
     */
    public static int getMaxMemoSize(BaseChain chain) {
        if (chain.equals(BNB_MAIN)) {
            return 100;
        }
        return 255;
    }

    public static int getCharSize(String memo) {
        int result = 1000;
        try {
            result = memo.trim().getBytes("UTF-8").length;
        } catch (Exception e) {
        }

        return result;
    }

    public static ArrayList<String> getExchangeAddressList() {
        return Lists.newArrayList(EXCHANGE_BINANCE_ADDRESS_01, EXCHANGE_BINANCE_ADDRESS_02, EXCHANGE_BINANCE_ADDRESS_03, EXCHANGE_BITHUMB_ADDRESS, EXCHANGE_UPBIT_ADDRESS,
                EXCHANGE_COINONE_ADDRESS, EXCHANGE_MEXC_ADDRESS, EXCHANGE_HITBTC_ADDRESS, EXCHANGE_DIGFINEX_ADDRESS);
    }

    /**
     * About Osmosis
     */
    public static BigDecimal getMyShareLpAmount(BaseData baseData, BalancerPool.Pool pool, String denom) {
        BigDecimal result = BigDecimal.ZERO;
        BigDecimal myShare = baseData.getAvailable("gamm/pool/" + pool.getId());
        String totalLpCoin = "";
        if (pool.getPoolAssets(0).getToken().getDenom().equalsIgnoreCase(denom)) {
            totalLpCoin = pool.getPoolAssets(0).getToken().getAmount();
        } else {
            totalLpCoin = pool.getPoolAssets(1).getToken().getAmount();
        }
        result = new BigDecimal(totalLpCoin).multiply(myShare).divide(new BigDecimal(pool.getTotalShares().getAmount()), 18, RoundingMode.DOWN);
        return result;
    }

    public static BigDecimal getOsmoLpTokenPerUsdPrice(BaseData baseData, BalancerPool.Pool pool) {
        try {
            BigDecimal totalShare = (new BigDecimal(pool.getTotalShares().getAmount())).movePointLeft(18).setScale(18, RoundingMode.DOWN);
            return getPoolValue(baseData, pool).divide(totalShare, 18, RoundingMode.DOWN);
        } catch (Exception e) {
            return BigDecimal.ZERO;
        }
    }

    public static BigDecimal getPoolValue(BaseData baseData, BalancerPool.Pool pool) {
        ChainConfig chainConfig = ChainFactory.getChain(BaseChain.OSMOSIS_MAIN);
        Coin coin0 = new Coin(pool.getPoolAssets(0).getToken().getDenom(), pool.getPoolAssets(0).getToken().getAmount());
        Coin coin1 = new Coin(pool.getPoolAssets(1).getToken().getDenom(), pool.getPoolAssets(1).getToken().getAmount());
        BigDecimal coin0Value = WDp.usdValue(baseData, coin0.denom, new BigDecimal(coin0.amount), WDp.getDenomDecimal(baseData, chainConfig, coin0.denom));
        BigDecimal coin1Value = WDp.usdValue(baseData, coin1.denom, new BigDecimal(coin1.amount), WDp.getDenomDecimal(baseData, chainConfig, coin1.denom));
        return coin0Value.add(coin1Value);
    }

    /**
     * About Sif
     */
    public static BigDecimal getNativeAmount(sifnode.clp.v1.Types.Pool pool) {
        return new BigDecimal(pool.getNativeAssetBalance());
    }

    public static BigDecimal getExternalAmount(sifnode.clp.v1.Types.Pool pool) {
        return new BigDecimal(pool.getExternalAssetBalance());
    }

    public static BigDecimal getUnitAmount(sifnode.clp.v1.Types.Pool pool) {
        return new BigDecimal(pool.getPoolUnits());
    }

    public static BigDecimal getPoolLpAmount(sifnode.clp.v1.Types.Pool pool, String denom) {
        if (denom != null) {
            ChainConfig chainConfig = ChainFactory.getChain(SIF_MAIN);
            if (denom.equals(chainConfig.mainDenom())) {
                return getNativeAmount(pool);
            } else {
                return getExternalAmount(pool);
            }
        }
        return BigDecimal.ONE;
    }

    public static BigDecimal getSifPoolPrice(sifnode.clp.v1.Types.Pool pool, String denom) {
        if (denom != null) {
            ChainConfig chainConfig = ChainFactory.getChain(SIF_MAIN);
            if (denom.equals(chainConfig.mainDenom())) {
                return new BigDecimal(pool.getSwapPriceNative());
            } else {
                return new BigDecimal(pool.getSwapPriceExternal());
            }
        }
        return BigDecimal.ONE;
    }

    public static BigDecimal getSifPoolValue(BaseData baseData, sifnode.clp.v1.Types.Pool pool) {
        final ChainConfig chainConfig = ChainFactory.getChain(SIF_MAIN);

        int rowanDecimal = WDp.getDenomDecimal(baseData, chainConfig, chainConfig.mainDenom());
        BigDecimal rowanAmount = new BigDecimal(pool.getNativeAssetBalance());
        BigDecimal rowanPrice = WDp.perUsdValue(baseData, chainConfig.mainDenom());

        int externalDecimal = WDp.getDenomDecimal(baseData, chainConfig, pool.getExternalAsset().getSymbol());
        BigDecimal externalAmount = new BigDecimal(pool.getExternalAssetBalance());
        String exteranlBaseDenom = pool.getExternalAsset().getSymbol();
        BigDecimal exteranlPrice = WDp.perUsdValue(baseData, exteranlBaseDenom);

        BigDecimal rowanValue = rowanAmount.multiply(rowanPrice).movePointLeft(rowanDecimal);
        BigDecimal externalValue = externalAmount.multiply(exteranlPrice).movePointLeft(externalDecimal).setScale(2, RoundingMode.DOWN);
        return rowanValue.add(externalValue);
    }

    public static BigDecimal getSifMyShareValue(BaseData baseData, sifnode.clp.v1.Types.Pool pool, Querier.LiquidityProviderRes myLp) {
        BigDecimal poolValue = getSifPoolValue(baseData, pool);
        BigDecimal totalUnit = new BigDecimal(pool.getPoolUnits());
        BigDecimal myUnit = new BigDecimal(myLp.getLiquidityProvider().getLiquidityProviderUnits());
        return poolValue.multiply(myUnit).divide(totalUnit, 2, RoundingMode.DOWN);
    }

    /**
     * About NFT
     */
    public static String getNftDescription(String data) {
        String description = "";
        try {
            JSONObject json = new JSONObject(data);
            description = json.getJSONObject("body").getString("description");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            JSONObject json = new JSONObject(data);
            description = json.getString("description");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return description;
    }

    public static String getNftIssuer(String data) {
        String issuer = "";
        try {
            JSONObject json = new JSONObject(data);
            issuer = json.getJSONObject("body").getString("issuerAddr");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            JSONObject json = new JSONObject(data);
            issuer = json.getString("issuerAddr");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return issuer;
    }

    public static String getNftImgUrl(String data) {
        String url = "";
        try {
            JSONObject json = new JSONObject(data);
            url = json.getString("image");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            JSONObject json = new JSONObject(data);
            url = json.getString("imgurl");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return url;
    }

    public static String getWalletName(Context c, Account account) {
        if (account == null) {
            return "";
        } else if (TextUtils.isEmpty(account.nickName)) {
            return c.getString(R.string.str_my_wallet) + account.id;
        } else {
            return account.nickName;
        }
    }

    // photo image rotate
    public static Bitmap rotateBitmap(Bitmap bitmap, int orientation) {
        Matrix matrix = new Matrix();
        switch (orientation) {
            case ExifInterface.ORIENTATION_NORMAL:
                return bitmap;
            case ExifInterface.ORIENTATION_FLIP_HORIZONTAL:
                matrix.setScale(-1, 1);
                break;
            case ExifInterface.ORIENTATION_ROTATE_180:
                matrix.setRotate(180);
                break;
            case ExifInterface.ORIENTATION_FLIP_VERTICAL:
                matrix.setRotate(180);
                matrix.postScale(-1, 1);
                break;
            case ExifInterface.ORIENTATION_TRANSPOSE:
                matrix.setRotate(90);
                matrix.postScale(-1, 1);
                break;
            case ExifInterface.ORIENTATION_ROTATE_90:
                matrix.setRotate(90);
                break;
            case ExifInterface.ORIENTATION_TRANSVERSE:
                matrix.setRotate(-90);
                matrix.postScale(-1, 1);
                break;
            case ExifInterface.ORIENTATION_ROTATE_270:
                matrix.setRotate(-90);
                break;
            default:
                return bitmap;
        }
        try {
            Bitmap bmRotated = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
            bitmap.recycle();
            return bmRotated;
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * About KAVA
     */
    //CDP
    public static BigDecimal getDpStabilityFee(Genesis.CollateralParam collateralParam) {
        return (new BigDecimal(collateralParam.getStabilityFee()).movePointLeft(18).subtract(BigDecimal.ONE)).multiply(new BigDecimal("31536000")).movePointRight(2);
    }

    public static BigDecimal getDpLiquidationPenalty(Genesis.CollateralParam collateralParam) {
        return new BigDecimal(collateralParam.getLiquidationPenalty()).movePointRight(2);
    }

    public static BigDecimal getRawDebtAmount(kava.cdp.v1beta1.QueryOuterClass.CDPResponse myCdp) {
        return new BigDecimal(myCdp.getPrincipal().getAmount()).add(new BigDecimal(myCdp.getAccumulatedFees().getAmount()));
    }

    public static BigDecimal getEstimatedTotalFee(Context c, kava.cdp.v1beta1.QueryOuterClass.CDPResponse myCdp, Genesis.CollateralParam collateralParam) {
        BigDecimal hiddenFeeValue = getCdpGrpcHiddenFee(c, getRawDebtAmount(myCdp), collateralParam, myCdp);
        return new BigDecimal(myCdp.getAccumulatedFees().getAmount()).add(hiddenFeeValue);
    }

    public static BigDecimal getEstimatedTotalDebt(Context c, kava.cdp.v1beta1.QueryOuterClass.CDPResponse myCdp, Genesis.CollateralParam cParam) {
        BigDecimal hiddenFeeValue = getCdpGrpcHiddenFee(c, getRawDebtAmount(myCdp), cParam, myCdp);
        return getRawDebtAmount(myCdp).add(hiddenFeeValue);
    }

    public static BigDecimal getLiquidationPrice(Context c, BaseData baseData, ChainConfig chainConfig, kava.cdp.v1beta1.QueryOuterClass.CDPResponse myCdp, Genesis.CollateralParam cParam) {
        int denomCDecimal = WDp.getDenomDecimal(baseData, chainConfig, myCdp.getCollateral().getDenom());
        int denomPDecimal = WDp.getDenomDecimal(baseData, chainConfig, myCdp.getPrincipal().getDenom());
        BigDecimal collateralAmount = new BigDecimal(myCdp.getCollateral().getAmount()).movePointLeft(denomCDecimal);
        BigDecimal estimatedDebtAmount = getEstimatedTotalDebt(c, myCdp, cParam).multiply(new BigDecimal(cParam.getLiquidationRatio())).movePointLeft(denomPDecimal);
        return estimatedDebtAmount.divide(collateralAmount, denomPDecimal, BigDecimal.ROUND_DOWN).movePointLeft(18);
    }

    public static BigDecimal getWithdrawableAmount(Context c, BaseData baseData, ChainConfig chainConfig, kava.cdp.v1beta1.QueryOuterClass.CDPResponse myCdp, Genesis.CollateralParam cParam, BigDecimal price, BigDecimal selfDeposit) {
        int denomCDecimal = WDp.getDenomDecimal(baseData, chainConfig, cParam.getDenom());
        int denomPDecimal = WDp.getDenomDecimal(baseData, chainConfig, cParam.getDebtLimit().getDenom());
        BigDecimal cValue = new BigDecimal(myCdp.getCollateralValue().getAmount());
        BigDecimal minCValue = getEstimatedTotalDebt(c, myCdp, cParam).multiply(new BigDecimal(cParam.getLiquidationRatio()).movePointLeft(18)).divide(new BigDecimal("0.95"), 0, BigDecimal.ROUND_DOWN);
        BigDecimal maxWithdrawableValue = cValue.subtract(minCValue);
        BigDecimal maxWithdrawableAmount = maxWithdrawableValue.movePointLeft(denomPDecimal - denomCDecimal).divide(price, 0, RoundingMode.DOWN);
        if (maxWithdrawableAmount.compareTo(selfDeposit) > 0) {
            maxWithdrawableAmount = selfDeposit;
        }
        if (maxWithdrawableAmount.compareTo(BigDecimal.ZERO) <= 0) {
            maxWithdrawableAmount = BigDecimal.ZERO;
        }
        return maxWithdrawableAmount;
    }

    public static BigDecimal getMoreLoanableAmount(Context c, kava.cdp.v1beta1.QueryOuterClass.CDPResponse myCdp, Genesis.CollateralParam cParam) {
        BigDecimal maxDebtValue = new BigDecimal(myCdp.getCollateralValue().getAmount()).divide(new BigDecimal(cParam.getLiquidationRatio()).movePointLeft(18), 0, RoundingMode.DOWN);
        maxDebtValue = maxDebtValue.multiply(new BigDecimal("0.95")).setScale(0, RoundingMode.DOWN);

        maxDebtValue = maxDebtValue.subtract(getEstimatedTotalDebt(c, myCdp, cParam));
        if (maxDebtValue.compareTo(BigDecimal.ZERO) <= 0) {
            maxDebtValue = BigDecimal.ZERO;
        }
        return maxDebtValue;

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
        textView.setText(WDp.getDpAmount2(c, riskRate, 0, 2));
        if (riskRate.floatValue() <= 50) {
            textView.setTextColor(ContextCompat.getColor(c, R.color.colorCdpSafe));
            if (imageview != null) {
                imageview.setImageDrawable(ContextCompat.getDrawable(c, R.drawable.img_safe));
            }

        } else if (riskRate.floatValue() < 80) {
            textView.setTextColor(ContextCompat.getColor(c, R.color.colorCdpStable));
            if (imageview != null) {
                imageview.setImageDrawable(ContextCompat.getDrawable(c, R.drawable.img_stable));
            }

        } else {
            textView.setTextColor(ContextCompat.getColor(c, R.color.colorCdpDanger));
            if (imageview != null) {
                imageview.setImageDrawable(ContextCompat.getDrawable(c, R.drawable.img_danger));

            }
        }

    }

    public static void DpRiskButton(Context c, BigDecimal riskRate, Button button) {
        if (riskRate.longValue() <= 50) {
            button.setBackground(ContextCompat.getDrawable(c, R.drawable.btn_score_safe_fill));
            button.setTextColor(ContextCompat.getColor(c, R.color.colorBlack));
            button.setTypeface(null, Typeface.BOLD);
            button.setText("SAFE " + riskRate.toPlainString());

        } else if (riskRate.longValue() < 80) {
            button.setBackground(ContextCompat.getDrawable(c, R.drawable.btn_score_stable_fill));
            button.setTextColor(ContextCompat.getColor(c, R.color.colorBlack));
            button.setTypeface(null, Typeface.BOLD);
            button.setText("STABLE " + riskRate.toPlainString());

        } else {
            button.setBackground(ContextCompat.getDrawable(c, R.drawable.btn_score_danger_fill));
            button.setTextColor(ContextCompat.getColor(c, R.color.colorBlack));
            button.setTypeface(null, Typeface.BOLD);
            button.setText("DANGER " + riskRate.toPlainString());
        }
    }

    public static void DpRiskButton2(Context c, BigDecimal riskRate, Button button) {
        if (riskRate.longValue() <= 50) {
            button.setBackground(ContextCompat.getDrawable(c, R.drawable.btn_score_safe_fill));
            button.setTextColor(ContextCompat.getColor(c, R.color.colorBlack));
            button.setTypeface(null, Typeface.BOLD);
            button.setText("SAFE");

        } else if (riskRate.longValue() < 80) {
            button.setBackground(ContextCompat.getDrawable(c, R.drawable.btn_score_stable_fill));
            button.setTextColor(ContextCompat.getColor(c, R.color.colorBlack));
            button.setTypeface(null, Typeface.BOLD);
            button.setText("STABLE");

        } else {
            button.setBackground(ContextCompat.getDrawable(c, R.drawable.btn_score_danger_fill));
            button.setTextColor(ContextCompat.getColor(c, R.color.colorBlack));
            button.setTypeface(null, Typeface.BOLD);
            button.setText("DANGER");
        }
    }

    public static void DpRiskRate2(Context c, BigDecimal riskRate, TextView text, TextView rate, LinearLayout layer) {
        rate.setText(WDp.getDpAmount2(c, riskRate, 0, 2));
        if (riskRate.longValue() <= 50) {
            text.setText("SAFE");
            layer.setBackground(ContextCompat.getDrawable(c, R.drawable.btn_score_safe_fill));

        } else if (riskRate.longValue() < 80) {
            text.setText("STABLE");
            layer.setBackground(ContextCompat.getDrawable(c, R.drawable.btn_score_stable_fill));

        } else {
            text.setText("DANGER");
            layer.setBackground(ContextCompat.getDrawable(c, R.drawable.btn_score_danger_fill));
        }
    }

    public static void DpRiskRate3(Context c, BigDecimal riskRate, TextView score, TextView rate) {
        score.setText(WDp.getDpAmount2(c, riskRate, 0, 2));
        if (riskRate.longValue() <= 50) {
            rate.setText("SAFE");
            rate.setTextColor(ContextCompat.getColor(c, R.color.colorCdpSafe));
            score.setTextColor(ContextCompat.getColor(c, R.color.colorCdpSafe));

        } else if (riskRate.longValue() < 80) {
            rate.setText("STABLE");
            rate.setTextColor(ContextCompat.getColor(c, R.color.colorCdpStable));
            score.setTextColor(ContextCompat.getColor(c, R.color.colorCdpStable));

        } else {
            rate.setText("DANGER");
            rate.setTextColor(ContextCompat.getColor(c, R.color.colorCdpDanger));
            score.setTextColor(ContextCompat.getColor(c, R.color.colorCdpDanger));
        }
    }

    public static BigDecimal getCdpGrpcHiddenFee(Context c, BigDecimal outstandingDebt, Genesis.CollateralParam paramCdp, kava.cdp.v1beta1.QueryOuterClass.CDPResponse myCdp) {
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

    //Hard
    public static String getSpotMarketId(kava.hard.v1beta1.Hard.Params Hardparams, String denom) {
        for (Hard.MoneyMarket moneyMarket : Hardparams.getMoneyMarketsList()) {
            if (moneyMarket.getDenom().equalsIgnoreCase(denom)) {
                return moneyMarket.getSpotMarketId();
            }
        }
        return null;
    }

    public static BigDecimal getLTV(kava.hard.v1beta1.Hard.Params Hardparams, String denom) {
        BigDecimal result = BigDecimal.ZERO;
        for (Hard.MoneyMarket moneyMarket : Hardparams.getMoneyMarketsList()) {
            if (moneyMarket.getDenom().equalsIgnoreCase(denom)) {
                result = new BigDecimal(moneyMarket.getBorrowLimit().getLoanToValue()).movePointLeft(18);
            }
        }
        return result;
    }

    public static Hard.MoneyMarket getHardMoneyMarket(Hard.Params params, String denom) {
        for (Hard.MoneyMarket market : params.getMoneyMarketsList()) {
            if (market.getDenom().equalsIgnoreCase(denom)) {
                return market;
            }
        }
        return null;
    }

    public static BigDecimal getKavaPrice(BaseData baseData, String denom) {
        BigDecimal result = BigDecimal.ZERO;
        if ("usdx".equals(denom)) {
            result = BigDecimal.ONE;
        } else {
            Hard.Params hardParam = baseData.mHardParams;
            result = baseData.getKavaOraclePrice(WUtil.getSpotMarketId(hardParam, denom));
        }
        return result;
    }

    public static BigDecimal getHardSuppliedAmountByDenom(Context context, BaseData baseData, String denom, ArrayList<kava.hard.v1beta1.QueryOuterClass.DepositResponse> myDeposit) {
        BigDecimal myDepositAmount = BigDecimal.ZERO;
        if (myDeposit != null && myDeposit.size() > 0) {
            for (CoinOuterClass.Coin coin : myDeposit.get(0).getAmountList()) {
                if (coin.getDenom().equalsIgnoreCase(denom)) {
                    myDepositAmount = new BigDecimal(coin.getAmount());
                }
            }
        }
        return myDepositAmount;
    }

    public static BigDecimal getHardSuppliedValueByDenom(Context context, BaseData baseData, String denom, ArrayList<kava.hard.v1beta1.QueryOuterClass.DepositResponse> myDeposit) {
        final BigDecimal denomPrice = getKavaPrice(baseData, denom);
        final int decimal = WDp.getDenomDecimal(baseData, ChainFactory.getChain(KAVA_MAIN), denom);
        return (getHardSuppliedAmountByDenom(context, baseData, denom, myDeposit)).movePointLeft(decimal).multiply(denomPrice);
    }

    public static BigDecimal getHardBorrowedAmountByDenom(Context context, BaseData baseData, String denom, ArrayList<kava.hard.v1beta1.QueryOuterClass.BorrowResponse> myBorrow) {
        BigDecimal myBorrowedAmount = BigDecimal.ZERO;
        if (myBorrow != null && myBorrow.size() > 0) {
            for (CoinOuterClass.Coin coin : myBorrow.get(0).getAmountList()) {
                if (coin.getDenom().equalsIgnoreCase(denom)) {
                    myBorrowedAmount = new BigDecimal(coin.getAmount());
                }
            }
        }
        return myBorrowedAmount;
    }

    public static BigDecimal getHardBorrowedValueByDenom(Context context, BaseData baseData, String denom, ArrayList<kava.hard.v1beta1.QueryOuterClass.BorrowResponse> myBorrow) {
        final BigDecimal denomPrice = getKavaPrice(baseData, denom);
        final int decimal = WDp.getDenomDecimal(baseData, ChainFactory.getChain(KAVA_MAIN), denom);
        return (getHardBorrowedAmountByDenom(context, baseData, denom, myBorrow)).movePointLeft(decimal).multiply(denomPrice);

    }

    public static BigDecimal getHardBorrowableAmountByDenom(Context context, BaseData baseData, String denom, ArrayList<kava.hard.v1beta1.QueryOuterClass.DepositResponse> myDeposit, ArrayList<kava.hard.v1beta1.QueryOuterClass.BorrowResponse> myBorrow, ArrayList<Coin> moduleCoins, ArrayList<CoinOuterClass.Coin> reserveCoin) {
        BigDecimal totalLTVValue = BigDecimal.ZERO;
        BigDecimal totalBorrowedValue = BigDecimal.ZERO;
        BigDecimal totalBorrowAbleValue = BigDecimal.ZERO;
        BigDecimal totalBorrowAbleAmount = BigDecimal.ZERO;

        BigDecimal SystemBorrowableAmount = BigDecimal.ZERO;
        BigDecimal moduleAmount = BigDecimal.ZERO;
        BigDecimal reserveAmount = BigDecimal.ZERO;

        final Hard.Params hardParam = baseData.mHardParams;
        final Hard.MoneyMarket hardMoneyMarket = WUtil.getHardMoneyMarket(hardParam, denom);
        final BigDecimal denomPrice = getKavaPrice(baseData, denom);
        final int decimal = WDp.getDenomDecimal(baseData, ChainFactory.getChain(KAVA_MAIN), denom);

        if (myDeposit != null && myDeposit.size() > 0) {
            for (CoinOuterClass.Coin coin : myDeposit.get(0).getAmountList()) {
                int innnerDecimal = WDp.getDenomDecimal(baseData, ChainFactory.getChain(KAVA_MAIN), coin.getDenom());
                BigDecimal LTV = WUtil.getLTV(hardParam, coin.getDenom());
                BigDecimal depositValue = BigDecimal.ZERO;
                BigDecimal ltvValue = BigDecimal.ZERO;
                if ("usdx".equalsIgnoreCase(coin.getDenom())) {
                    depositValue = (new BigDecimal(coin.getAmount())).movePointLeft(innnerDecimal);

                } else {
                    BigDecimal innerPrice = getKavaPrice(baseData, coin.getDenom());
                    depositValue = (new BigDecimal(coin.getAmount())).movePointLeft(innnerDecimal).multiply(innerPrice);

                }
                ltvValue = depositValue.multiply(LTV);
                totalLTVValue = totalLTVValue.add(ltvValue);
            }
        }

        if (myBorrow != null && myBorrow.size() > 0) {
            for (CoinOuterClass.Coin coin : myBorrow.get(0).getAmountList()) {
                int innnerDecimal = WDp.getDenomDecimal(baseData, ChainFactory.getChain(KAVA_MAIN), coin.getDenom());
                BigDecimal borrowedValue = BigDecimal.ZERO;
                if ("usdx".equalsIgnoreCase(coin.getDenom())) {
                    borrowedValue = (new BigDecimal(coin.getAmount())).movePointLeft(innnerDecimal);

                } else {
                    BigDecimal innerPrice = getKavaPrice(baseData, coin.getDenom());
                    borrowedValue = (new BigDecimal(coin.getAmount())).movePointLeft(innnerDecimal).multiply(innerPrice);

                }
                totalBorrowedValue = totalBorrowedValue.add(borrowedValue);
            }
        }
        totalBorrowAbleValue = (totalLTVValue.subtract(totalBorrowedValue)).max(BigDecimal.ZERO);
        totalBorrowAbleAmount = totalBorrowAbleValue.movePointRight(decimal).divide(denomPrice, decimal, RoundingMode.DOWN);

        if (moduleCoins != null) {
            for (Coin coin : moduleCoins) {
                if (coin.denom.equals(denom)) {
                    moduleAmount = new BigDecimal(coin.amount);
                }
            }
        }
        if (reserveCoin != null) {
            for (CoinOuterClass.Coin coin : reserveCoin) {
                if (coin.getDenom().equalsIgnoreCase(denom)) {
                    reserveAmount = new BigDecimal(coin.getAmount());
                }
            }
        }
        BigDecimal moduleBorrowable = moduleAmount.subtract(reserveAmount);
        if (hardMoneyMarket.getBorrowLimit().getHasMaxLimit()) {
            SystemBorrowableAmount = new BigDecimal(hardMoneyMarket.getBorrowLimit().getMaximumLimit()).min(moduleBorrowable);
        } else {
            SystemBorrowableAmount = moduleBorrowable;
        }
        BigDecimal finalBorrowableAmount = totalBorrowAbleAmount.min(SystemBorrowableAmount);

        return finalBorrowableAmount;
    }

    public static BigDecimal getHardBorrowableValueByDenom(Context context, BaseData baseData, String denom, ArrayList<kava.hard.v1beta1.QueryOuterClass.DepositResponse> myDeposit, ArrayList<kava.hard.v1beta1.QueryOuterClass.BorrowResponse> myBorrow, ArrayList<Coin> moduleCoins, ArrayList<CoinOuterClass.Coin> reserveCoin) {
        BigDecimal totalLTVValue = BigDecimal.ZERO;
        BigDecimal totalBorrowedValue = BigDecimal.ZERO;
        BigDecimal totalBorrowAbleValue = BigDecimal.ZERO;
        BigDecimal totalBorrowAbleAmount = BigDecimal.ZERO;

        BigDecimal SystemBorrowableAmount = BigDecimal.ZERO;
        BigDecimal SystemBorrowableValue = BigDecimal.ZERO;
        BigDecimal moduleAmount = BigDecimal.ZERO;
        BigDecimal reserveAmount = BigDecimal.ZERO;

        final Hard.Params hardParam = baseData.mHardParams;
        final Hard.MoneyMarket hardMoneyMarket = WUtil.getHardMoneyMarket(hardParam, denom);
        final BigDecimal denomPrice = getKavaPrice(baseData, denom);
        final int decimal = WDp.getDenomDecimal(baseData, ChainFactory.getChain(KAVA_MAIN), denom);

        if (myDeposit != null && myDeposit.size() > 0) {
            for (CoinOuterClass.Coin coin : myDeposit.get(0).getAmountList()) {
                int innnerDecimal = WDp.getDenomDecimal(baseData, ChainFactory.getChain(KAVA_MAIN), coin.getDenom());
                BigDecimal LTV = WUtil.getLTV(hardParam, coin.getDenom());
                BigDecimal depositValue = BigDecimal.ZERO;
                BigDecimal ltvValue = BigDecimal.ZERO;
                if ("usdx".equalsIgnoreCase(coin.getDenom())) {
                    depositValue = (new BigDecimal(coin.getAmount())).movePointLeft(innnerDecimal);

                } else {
                    BigDecimal innerPrice = getKavaPrice(baseData, coin.getDenom());
                    depositValue = (new BigDecimal(coin.getAmount())).movePointLeft(innnerDecimal).multiply(innerPrice);

                }
                ltvValue = depositValue.multiply(LTV);
                totalLTVValue = totalLTVValue.add(ltvValue);

            }
        }

        if (myBorrow != null && myBorrow.size() > 0) {
            for (CoinOuterClass.Coin coin : myBorrow.get(0).getAmountList()) {
                int innnerDecimal = WDp.getDenomDecimal(baseData, ChainFactory.getChain(KAVA_MAIN), coin.getDenom());
                BigDecimal borrowedValue = BigDecimal.ZERO;
                if ("usdx".equals(coin.getDenom())) {
                    borrowedValue = (new BigDecimal(coin.getAmount())).movePointLeft(innnerDecimal);

                } else {
                    BigDecimal innerPrice = getKavaPrice(baseData, coin.getDenom());
                    borrowedValue = (new BigDecimal(coin.getAmount())).movePointLeft(innnerDecimal).multiply(innerPrice);

                }
                totalBorrowedValue = totalBorrowedValue.add(borrowedValue);
            }
        }
        totalBorrowAbleValue = (totalLTVValue.subtract(totalBorrowedValue)).max(BigDecimal.ZERO);
        totalBorrowAbleAmount = totalBorrowAbleValue.movePointRight(decimal).divide(denomPrice, decimal, RoundingMode.DOWN);

        if (moduleCoins != null) {
            for (Coin coin : moduleCoins) {
                if (coin.denom.equals(denom)) {
                    moduleAmount = new BigDecimal(coin.amount);
                }
            }
        }
        if (reserveCoin != null) {
            for (CoinOuterClass.Coin coin : reserveCoin) {
                if (coin.getDenom().equals(denom)) {
                    reserveAmount = new BigDecimal(coin.getAmount());
                }
            }
        }
        BigDecimal moduleBorrowable = moduleAmount.subtract(reserveAmount);
        if (hardMoneyMarket.getBorrowLimit().getHasMaxLimit()) {
            SystemBorrowableAmount = new BigDecimal(hardMoneyMarket.getBorrowLimit().getMaximumLimit()).min(moduleBorrowable);
        } else {
            SystemBorrowableAmount = moduleBorrowable;
        }
        SystemBorrowableValue = SystemBorrowableAmount.movePointLeft(decimal).multiply(denomPrice);

        BigDecimal finalBorrowableValue = totalBorrowAbleValue.min(SystemBorrowableValue);
        return finalBorrowableValue;
    }

    public static String getDuputyKavaAddress(String denom) {
        if (denom.equalsIgnoreCase(TOKEN_HTLC_KAVA_BNB)) {
            return KAVA_MAIN_BNB_DEPUTY;
        } else if (denom.equalsIgnoreCase(TOKEN_HTLC_KAVA_BTCB)) {
            return KAVA_MAIN_BTCB_DEPUTY;
        } else if (denom.equalsIgnoreCase(TOKEN_HTLC_KAVA_XRPB)) {
            return KAVA_MAIN_XRPB_DEPUTY;
        } else if (denom.equalsIgnoreCase(TOKEN_HTLC_KAVA_BUSD)) {
            return KAVA_MAIN_BUSD_DEPUTY;
        }
        return "";
    }

    public static String getDuputyBnbAddress(String denom) {
        if (denom.equalsIgnoreCase(TOKEN_HTLC_KAVA_BNB)) {
            return BINANCE_MAIN_BNB_DEPUTY;
        } else if (denom.equalsIgnoreCase(TOKEN_HTLC_KAVA_BTCB)) {
            return BINANCE_MAIN_BTCB_DEPUTY;
        } else if (denom.equalsIgnoreCase(TOKEN_HTLC_KAVA_XRPB)) {
            return BINANCE_MAIN_XRPB_DEPUTY;
        } else if (denom.equalsIgnoreCase(TOKEN_HTLC_KAVA_BUSD)) {
            return BINANCE_MAIN_BUSD_DEPUTY;
        }
        return "";
    }

    public static byte[] long2Bytes(long x) {
        ByteBuffer buffer = ByteBuffer.allocate(8);
        buffer.putLong(x);
        return buffer.array();
    }

    public static boolean isValidStarName(String starname) {
        if (starname.startsWith("*") && starname.length() > 3) return true;
        String[] names = starname.split("\\*");
        if (names.length != 2) {
            return false;
        }
        return isValidAccount(names[0]) && isValidDomain(names[1]);
    }

    public static boolean isValidDomain(String starname) {
        boolean result = false;
        String regex = "^[mabcdefghijklnopqrstuvwxyz0123456789][-a-z0-9_]{0,2}$|^[-a-z0-9_]{4,32}$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(starname);
        if (m.matches()) {
            result = true;
        }
        return result;
    }

    public static boolean isValidAccount(String starname) {
        boolean result = false;
        String regex = "^[-.a-z0-9_]{1,63}$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(starname);
        if (m.matches()) {
            result = true;
        }
        return result;
    }

    public static String checkStarnameWithResource(ChainConfig chainConfig, List<Types.Resource> resources) {
        for (Types.Resource resource : resources) {
            if (WDp.isValidChainAddress(chainConfig, resource.getResource())) {
                return resource.getResource();
            }
        }
        return "";

    }

    public static ExportStarName getExportResource(ArrayList<Account> accounts) {
        ExportStarName result = new ExportStarName();
        result.type = "starname";
        for (Account account : accounts) {
            ExportStarName.ExportResource resource = new ExportStarName.ExportResource();
            if (BaseChain.getChain(account.baseChain).equals(COSMOS_MAIN)) {
                resource.ticker = "atom";
                resource.address = account.address;
                result.addresses.add(resource);

            } else if (BaseChain.getChain(account.baseChain).equals(IRIS_MAIN)) {
                resource.ticker = "iris";
                resource.address = account.address;
                result.addresses.add(resource);

            } else if (BaseChain.getChain(account.baseChain).equals(BNB_MAIN)) {
                resource.ticker = "bnb";
                resource.address = account.address;
                result.addresses.add(resource);

            } else if (BaseChain.getChain(account.baseChain).equals(OKEX_MAIN)) {
                resource.ticker = "okb";
                resource.address = account.address;
                result.addresses.add(resource);

            } else if (BaseChain.getChain(account.baseChain).equals(KAVA_MAIN)) {
                resource.ticker = "kava";
                resource.address = account.address;
                result.addresses.add(resource);

            } else if (BaseChain.getChain(account.baseChain).equals(BAND_MAIN)) {
                resource.ticker = "band";
                resource.address = account.address;
                result.addresses.add(resource);

            } else if (BaseChain.getChain(account.baseChain).equals(PERSIS_MAIN)) {
                resource.ticker = "xprt";
                resource.address = account.address;
                result.addresses.add(resource);

            } else if (BaseChain.getChain(account.baseChain).equals(IOV_MAIN)) {
                resource.ticker = "iov";
                resource.address = account.address;
                result.addresses.add(resource);

            } else if (BaseChain.getChain(account.baseChain).equals(CERTIK_MAIN)) {
                resource.ticker = "ctk";
                resource.address = account.address;
                result.addresses.add(resource);

            } else if (BaseChain.getChain(account.baseChain).equals(AKASH_MAIN)) {
                resource.ticker = "akt";
                resource.address = account.address;
                result.addresses.add(resource);

            } else if (BaseChain.getChain(account.baseChain).equals(SENTINEL_MAIN)) {
                resource.ticker = "dvpn";
                resource.address = account.address;
                result.addresses.add(resource);

//            } else if (BaseChain.getChain(account.baseChain).equals(FETCHAI_MAIN)) {
//                resource.ticker = "fet";
//                resource.address = account.address;
//                result.addresses.add(resource);

            } else if (BaseChain.getChain(account.baseChain).equals(CRYPTO_MAIN)) {
                resource.ticker = "cro";
                resource.address = account.address;
                result.addresses.add(resource);

            } else if (BaseChain.getChain(account.baseChain).equals(SIF_MAIN)) {
                resource.ticker = "rowan";
                resource.address = account.address;
                result.addresses.add(resource);

//            } else if (BaseChain.getChain(account.baseChain).equals(KI_MAIN)) {
//                resource.ticker = "ki";
//                resource.address = account.address;
//                result.addresses.add(resource);

            } else if (BaseChain.getChain(account.baseChain).equals(RIZON_MAIN)) {
                resource.ticker = "atolo";
                resource.address = account.address;
                result.addresses.add(resource);

            } else if (BaseChain.getChain(account.baseChain).equals(OSMOSIS_MAIN)) {
                resource.ticker = "osmo";
                resource.address = account.address;
                result.addresses.add(resource);

//            } else if (BaseChain.getChain(account.baseChain).equals(MEDI_MAIN)) {
//                resource.ticker = "med";
//                resource.address = account.address;
//                result.addresses.add(resource);
//
//            } else if (BaseChain.getChain(account.baseChain).equals(EMONEY_MAIN)) {
//                resource.ticker = "ngm";
//                resource.address = account.address;
//                result.addresses.add(resource);

            } else if (BaseChain.getChain(account.baseChain).equals(REGEN_MAIN)) {
                resource.ticker = "regen";
                resource.address = account.address;
                result.addresses.add(resource);

//            } else if (BaseChain.getChain(account.baseChain).equals(JUNO_MAIN)) {
//                resource.ticker = "juno";
//                resource.address = account.address;
//                result.addresses.add(resource);
//
//            } else if (BaseChain.getChain(account.baseChain).equals(BITCANNA_MAIN)) {
//                resource.ticker = "bcna";
//                resource.address = account.address;
//                result.addresses.add(resource);
//
//            } else if (BaseChain.getChain(account.baseChain).equals(STARGAZE_MAIN)) {
//                resource.ticker = "stars";
//                resource.address = account.address;
//                result.addresses.add(resource);

            } else if (BaseChain.getChain(account.baseChain).equals(SECRET_MAIN)) {
                resource.ticker = "scrt";
                resource.address = account.address;
                result.addresses.add(resource);

            }
        }
        return result;
    }

    public static void setDexTitle(MainActivity mainActivity, ChainConfig chainConfig, TextView dexTitle) {
        if (chainConfig.baseChain().equals(IRIS_MAIN) || chainConfig.baseChain().equals(CRYPTO_MAIN)) {
            dexTitle.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(mainActivity, R.drawable.icon_nft), null, null, null);
            dexTitle.setText(R.string.str_nft_c);
        } else if (chainConfig.baseChain().equals(IOV_MAIN)) {
            dexTitle.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(mainActivity, R.drawable.name_ic), null, null, null);
            dexTitle.setText(R.string.str_name_service);
        } else if (chainConfig.baseChain().equals(KAVA_MAIN)) {
            dexTitle.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(mainActivity, R.drawable.cdp_s_ic), null, null, null);
            dexTitle.setText(R.string.str_kava_dapp);
        } else if (chainConfig.baseChain().equals(SIF_MAIN)) {
            dexTitle.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(mainActivity, R.drawable.icon_sifdex), null, null, null);
            dexTitle.setText(R.string.str_sif_dex_title);
        } else if (chainConfig.baseChain().equals(OSMOSIS_MAIN)) {
            dexTitle.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(mainActivity, R.drawable.icon_osmosislab), null, null, null);
            dexTitle.setText(R.string.str_osmosis_defi_lab);
        } else if (chainConfig.baseChain().equals(STRIDE_MAIN) || chainConfig.baseChain().equals(PERSIS_MAIN)) {
            dexTitle.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(mainActivity, R.drawable.icon_liquidstake), null, null, null);
            dexTitle.setText(R.string.str_liquid_staking);
        }
    }

    public static Intent getDexIntent(MainActivity mainActivity, ChainConfig chainConfig) {
        if (chainConfig.baseChain().equals(IRIS_MAIN) || chainConfig.baseChain().equals(CRYPTO_MAIN)) {
            return new Intent(mainActivity, NFTListActivity.class);
        } else if (chainConfig.baseChain().equals(IOV_MAIN)) {
            return new Intent(mainActivity, StarNameListActivity.class);
        } else if (chainConfig.baseChain().equals(KAVA_MAIN)) {
            return new Intent(mainActivity, DAppsList5Activity.class);
        } else if (chainConfig.baseChain().equals(SIF_MAIN)) {
            return new Intent(mainActivity, SifDexListActivity.class);
        } else if (chainConfig.baseChain().equals(OSMOSIS_MAIN)) {
            return new Intent(mainActivity, SwapViewActivity.class);
        } else if (chainConfig.baseChain().equals(STRIDE_MAIN)) {
            return new Intent(mainActivity, StrideLSActivity.class);
        } else if (chainConfig.baseChain().equals(PERSIS_MAIN)) {
            return new Intent(mainActivity, PersisLSActivity.class);
        } else {
            return null;
        }
    }

    /**
     * About Kava
     */
    public static boolean isBep3Coin(String denom) {
        if (denom.equals(TOKEN_HTLC_BINANCE_BNB)) {
            return true;
        }
        if (denom.equals(TOKEN_HTLC_BINANCE_BTCB)) {
            return true;
        }
        if (denom.equals(TOKEN_HTLC_BINANCE_XRPB)) {
            return true;
        }
        if (denom.equals(TOKEN_HTLC_BINANCE_BUSD)) {
            return true;
        }

        if (denom.equals(TOKEN_HTLC_KAVA_BNB)) {
            return true;
        }
        if (denom.equals(TOKEN_HTLC_KAVA_BTCB)) {
            return true;
        }
        if (denom.equals(TOKEN_HTLC_KAVA_XRPB)) {
            return true;
        }
        if (denom.equals(TOKEN_HTLC_KAVA_BUSD)) {
            return true;
        }
        return false;
    }

    //parse & check vesting account
    public static void onParseVestingAccount(BaseData baseData) {
        Any account = baseData.mGRpcAccount;
        if (account == null) return;
        ArrayList<Coin> sBalace = new ArrayList<>();
        for (Coin coin : baseData.mGrpcBalance) {
            sBalace.add(coin);
        }

        String denom = "";
        BigDecimal dpBalance = BigDecimal.ZERO;
        BigDecimal dpVesting = BigDecimal.ZERO;
        BigDecimal originalVesting = BigDecimal.ZERO;
        BigDecimal remainVesting = BigDecimal.ZERO;
        BigDecimal delegatedVesting = BigDecimal.ZERO;

        if (account.getTypeUrl().contains(Vesting.PeriodicVestingAccount.getDescriptor().getFullName())) {
            Vesting.PeriodicVestingAccount vestingAccount = null;
            try {
                vestingAccount = Vesting.PeriodicVestingAccount.parseFrom(account.getValue());
            } catch (InvalidProtocolBufferException e) {
                WLog.e("onParseVestingAccount " + e.getMessage());
                return;
            }
            for (Coin coin : sBalace) {
                denom = coin.denom;
                dpBalance = new BigDecimal(coin.amount);
                WLog.w("dpBalance " + denom + "  " + dpBalance);

                for (CoinOuterClass.Coin vesting : vestingAccount.getBaseVestingAccount().getOriginalVestingList()) {
                    if (vesting.getDenom().equals(denom)) {
                        originalVesting = originalVesting.add(new BigDecimal(vesting.getAmount()));
                    }
                }
                WLog.w("originalVesting " + denom + "  " + originalVesting);

                for (CoinOuterClass.Coin vesting : vestingAccount.getBaseVestingAccount().getDelegatedVestingList()) {
                    if (vesting.getDenom().equals(denom)) {
                        delegatedVesting = delegatedVesting.add(new BigDecimal(vesting.getAmount()));
                    }
                }
                WLog.w("delegatedVesting " + denom + "  " + delegatedVesting);

                remainVesting = WDp.onParsePeriodicRemainVestingsAmountByDenom(vestingAccount, denom);
                WLog.w("remainVesting " + denom + "  " + remainVesting);

                dpVesting = remainVesting.subtract(delegatedVesting);
                WLog.w("dpVestingA " + denom + "  " + dpVesting);

                dpVesting = dpVesting.compareTo(BigDecimal.ZERO) <= 0 ? BigDecimal.ZERO : dpVesting;
                WLog.w("dpVestingB " + denom + "  " + dpVesting);

                if (remainVesting.compareTo(delegatedVesting) > 0) {
                    dpBalance = dpBalance.subtract(remainVesting).add(delegatedVesting);
                }
                WLog.w("final dpBalance  " + denom + "  " + dpBalance);

                if (dpVesting.compareTo(BigDecimal.ZERO) > 0) {
                    Coin vestingCoin = new Coin(denom, dpVesting.toPlainString());
                    baseData.mGrpcVesting.add(vestingCoin);
                    int replace = -1;
                    for (int i = 0; i < baseData.mGrpcBalance.size(); i++) {
                        if (baseData.mGrpcBalance.get(i).denom.equals(denom)) {
                            replace = i;
                        }
                    }
                    if (replace >= 0) {
                        baseData.mGrpcBalance.set(replace, new Coin(denom, dpBalance.toPlainString()));
                    }
                }
            }

        } else if (account.getTypeUrl().contains(Vesting.ContinuousVestingAccount.getDescriptor().getFullName())) {
            Vesting.ContinuousVestingAccount vestingAccount = null;
            try {
                vestingAccount = Vesting.ContinuousVestingAccount.parseFrom(account.getValue());
            } catch (InvalidProtocolBufferException e) {
                WLog.e("onParseVestingAccount " + e.getMessage());
                return;
            }
            for (Coin coin : sBalace) {
                denom = coin.denom;
                dpBalance = new BigDecimal(coin.amount);
                WLog.w("dpBalance " + denom + "  " + dpBalance);

                for (CoinOuterClass.Coin vesting : vestingAccount.getBaseVestingAccount().getOriginalVestingList()) {
                    if (vesting.getDenom().equals(denom)) {
                        originalVesting = originalVesting.add(new BigDecimal(vesting.getAmount()));
                    }
                }
                WLog.w("originalVesting " + denom + "  " + originalVesting);

                for (CoinOuterClass.Coin vesting : vestingAccount.getBaseVestingAccount().getDelegatedVestingList()) {
                    if (vesting.getDenom().equals(denom)) {
                        delegatedVesting = delegatedVesting.add(new BigDecimal(vesting.getAmount()));
                    }
                }
                WLog.w("delegatedVesting " + denom + "  " + delegatedVesting);

                long cTime = Calendar.getInstance().getTime().getTime();
                long vestingStart = vestingAccount.getStartTime() * 1000;
                long vestingEnd = vestingAccount.getBaseVestingAccount().getEndTime() * 1000;
                if (cTime < vestingStart) {
                    remainVesting = originalVesting;
                } else if (cTime > vestingEnd) {
                    remainVesting = BigDecimal.ZERO;
                } else if (cTime < vestingEnd) {
                    float progress = ((float) (cTime - vestingStart) / (float) (vestingEnd - vestingStart));
                    remainVesting = originalVesting.multiply(new BigDecimal(1 - progress)).setScale(0, RoundingMode.UP);
                }
                WLog.w("remainVesting " + denom + "  " + remainVesting);

                dpVesting = remainVesting.subtract(delegatedVesting);
                WLog.w("dpVestingA " + denom + "  " + dpVesting);

                dpVesting = dpVesting.compareTo(BigDecimal.ZERO) <= 0 ? BigDecimal.ZERO : dpVesting;
                WLog.w("dpVestingB " + denom + "  " + dpVesting);

                if (remainVesting.compareTo(delegatedVesting) > 0) {
                    dpBalance = dpBalance.subtract(remainVesting).add(delegatedVesting);
                }
                WLog.w("final dpBalance  " + denom + "  " + dpBalance);

                if (dpVesting.compareTo(BigDecimal.ZERO) > 0) {
                    Coin vestingCoin = new Coin(denom, dpVesting.toPlainString());
                    baseData.mGrpcVesting.add(vestingCoin);
                    int replace = -1;
                    for (int i = 0; i < baseData.mGrpcBalance.size(); i++) {
                        if (baseData.mGrpcBalance.get(i).denom.equals(denom)) {
                            replace = i;
                        }
                    }
                    if (replace >= 0) {
                        baseData.mGrpcBalance.set(replace, new Coin(denom, dpBalance.toPlainString()));
                    }
                }
            }

        } else if (account.getTypeUrl().contains(Vesting.DelayedVestingAccount.getDescriptor().getFullName())) {
            Vesting.DelayedVestingAccount vestingAccount = null;
            try {
                vestingAccount = Vesting.DelayedVestingAccount.parseFrom(account.getValue());
            } catch (InvalidProtocolBufferException e) {
                WLog.e("onParseVestingAccount " + e.getMessage());
                return;
            }
            for (Coin coin : sBalace) {
                denom = coin.denom;
                dpBalance = new BigDecimal(coin.amount);
                WLog.w("dpBalance " + denom + "  " + dpBalance);

                for (CoinOuterClass.Coin vesting : vestingAccount.getBaseVestingAccount().getOriginalVestingList()) {
                    if (vesting.getDenom().equals(denom)) {
                        originalVesting = originalVesting.add(new BigDecimal(vesting.getAmount()));
                    }
                }
                WLog.w("originalVesting " + denom + "  " + originalVesting);

                long cTime = Calendar.getInstance().getTime().getTime();
                long vestingEnd = vestingAccount.getBaseVestingAccount().getEndTime() * 1000;
                if (cTime < vestingEnd) {
                    remainVesting = originalVesting;
                }
                WLog.w("remainVesting " + denom + "  " + remainVesting);

                for (CoinOuterClass.Coin vesting : vestingAccount.getBaseVestingAccount().getDelegatedVestingList()) {
                    if (vesting.getDenom().equals(denom)) {
                        delegatedVesting = delegatedVesting.add(new BigDecimal(vesting.getAmount()));
                    }
                }

                WLog.w("delegatedVesting " + denom + "  " + delegatedVesting);

                dpVesting = remainVesting.subtract(delegatedVesting);
                WLog.w("dpVestingA " + denom + "  " + dpVesting);

                dpVesting = dpVesting.compareTo(BigDecimal.ZERO) <= 0 ? BigDecimal.ZERO : dpVesting;
                WLog.w("dpVestingB " + denom + "  " + dpVesting);

                if (remainVesting.compareTo(delegatedVesting) > 0) {
                    dpBalance = dpBalance.subtract(remainVesting).add(delegatedVesting);
                }
                WLog.w("final dpBalance  " + denom + "  " + dpBalance);

                if (dpVesting.compareTo(BigDecimal.ZERO) > 0) {
                    Coin vestingCoin = new Coin(denom, dpVesting.toPlainString());
                    baseData.mGrpcVesting.add(vestingCoin);
                    int replace = -1;
                    for (int i = 0; i < baseData.mGrpcBalance.size(); i++) {
                        if (baseData.mGrpcBalance.get(i).denom.equals(denom)) {
                            replace = i;
                        }
                    }
                    if (replace >= 0) {
                        baseData.mGrpcBalance.set(replace, new Coin(denom, dpBalance.toPlainString()));
                    }
                }
            }

        } else if (account.getTypeUrl().contains(StridePeriodicVestingAccount.getDescriptor().getFullName())) {
            StridePeriodicVestingAccount vestingAccount = null;
            try {
                vestingAccount = StridePeriodicVestingAccount.parseFrom(account.getValue());
            } catch (InvalidProtocolBufferException e) {
                return;
            }
            for (Coin coin : sBalace) {
                denom = coin.denom;
                BigDecimal delegatedFree = BigDecimal.ZERO;
                dpBalance = new BigDecimal(coin.amount);

                for (CoinOuterClass.Coin vesting : vestingAccount.getBaseVestingAccount().getOriginalVestingList()) {
                    if (vesting.getDenom().equals(denom)) {
                        originalVesting = originalVesting.add(new BigDecimal(vesting.getAmount()));
                    }
                }
                for (CoinOuterClass.Coin vesting : vestingAccount.getBaseVestingAccount().getDelegatedVestingList()) {
                    if (vesting.getDenom().equals(denom)) {
                        delegatedVesting = delegatedVesting.add(new BigDecimal(vesting.getAmount()));
                    }
                }
                for (CoinOuterClass.Coin vesting : vestingAccount.getBaseVestingAccount().getDelegatedFreeList()) {
                    if (vesting.getDenom().equals(denom)) {
                        delegatedFree = delegatedFree.add(new BigDecimal(vesting.getAmount()));
                    }
                }

                remainVesting = WDp.onParseStridePeriodicRemainVestingsAmountByDenom(vestingAccount, denom);
                dpVesting = remainVesting.subtract(delegatedVesting).subtract(delegatedFree);
                dpVesting = dpVesting.compareTo(BigDecimal.ZERO) <= 0 ? BigDecimal.ZERO : dpVesting;
                if (remainVesting.compareTo(delegatedVesting.add(delegatedFree)) > 0) {
                    dpBalance = dpBalance.subtract(remainVesting).add(delegatedVesting);
                }

                dpBalance = dpBalance.compareTo(BigDecimal.ZERO) <= 0 ? BigDecimal.ZERO : dpBalance;

                if (dpVesting.compareTo(BigDecimal.ZERO) > 0) {
                    Coin vestingCoin = new Coin(denom, dpVesting.toPlainString());
                    baseData.mGrpcVesting.add(vestingCoin);
                    int replace = -1;
                    for (int i = 0; i < baseData.mGrpcBalance.size(); i++) {
                        if (baseData.mGrpcBalance.get(i).denom.equals(denom)) {
                            replace = i;
                        }
                    }
                    if (replace >= 0) {
                        baseData.mGrpcBalance.set(replace, new Coin(denom, dpBalance.toPlainString()));
                    }
                }
            }
        }
    }
}
