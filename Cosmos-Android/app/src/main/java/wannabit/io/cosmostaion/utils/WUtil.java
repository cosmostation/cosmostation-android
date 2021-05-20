package wannabit.io.cosmostaion.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf2.Any;
import com.google.zxing.common.BitMatrix;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.nio.ByteBuffer;
import java.security.cert.CertificateException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
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
import cosmos.gov.v1beta1.Gov;
import cosmos.staking.v1beta1.Staking;
import cosmos.vesting.v1beta1.Vesting;
import okhttp3.OkHttpClient;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.dao.Balance;
import wannabit.io.cosmostaion.dao.BnbTicker;
import wannabit.io.cosmostaion.dao.BnbToken;
import wannabit.io.cosmostaion.model.ExportStarName;
import wannabit.io.cosmostaion.model.StarNameResource;
import wannabit.io.cosmostaion.model.UnbondingInfo;
import wannabit.io.cosmostaion.model.kava.HardMyBorrow;
import wannabit.io.cosmostaion.model.kava.HardMyDeposit;
import wannabit.io.cosmostaion.model.kava.HardParam;
import wannabit.io.cosmostaion.model.kava.MarketPrice;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.model.type.Proposal;
import wannabit.io.cosmostaion.model.type.Validator;
import wannabit.io.cosmostaion.model.type.Vote;
import wannabit.io.cosmostaion.network.res.ResBnbAccountInfo;
import wannabit.io.cosmostaion.network.res.ResLcdAccountInfo;
import wannabit.io.cosmostaion.network.res.ResLcdKavaAccountInfo;
import wannabit.io.cosmostaion.network.res.ResLcdVestingAccountInfo;
import wannabit.io.cosmostaion.network.res.ResOkAccountInfo;
import wannabit.io.cosmostaion.network.res.ResOkAccountToken;

import static wannabit.io.cosmostaion.base.BaseChain.AKASH_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.BAND_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.BNB_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.BNB_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.CERTIK_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.CERTIK_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.COSMOS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.COSMOS_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.CRYPTO_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.FETCHAI_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.IOV_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.IOV_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.IRIS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.IRIS_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.KAVA_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.KAVA_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.KI_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.OKEX_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.OK_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.PERSIS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.SECRET_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.SENTINEL_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.SIF_MAIN;
import static wannabit.io.cosmostaion.base.BaseConstant.*;

public class WUtil {

    public static Account getAccountFromLcd(long id, ResLcdAccountInfo lcd) {
        Account result = new Account();
        result.id = id;
        if (lcd.result != null && lcd.height != null) {
            if (lcd.result.type.equals(COSMOS_AUTH_TYPE_ACCOUNT) ||
                    lcd.result.type.equals(BaseConstant.COSMOS_AUTH_TYPE_ACCOUNT_LEGACY) ||
                    lcd.result.type.equals(BaseConstant.IRIS_BANK_TYPE_ACCOUNT) ||
                    lcd.result.type.equals(COSMOS_AUTH_TYPE_CERTIK_MANUAL)) {
                result.address = lcd.result.value.address;
                result.sequenceNumber = Integer.parseInt(lcd.result.value.sequence);
                result.accountNumber = Integer.parseInt(lcd.result.value.account_number);
                return result;
            } else {
                result.address = lcd.result.value.BaseVestingAccount.BaseAccount.address;
                result.sequenceNumber = Integer.parseInt(lcd.result.value.BaseVestingAccount.BaseAccount.sequence);
                result.accountNumber = Integer.parseInt(lcd.result.value.BaseVestingAccount.BaseAccount.account_number);
                return result;
            }
        }
        if (lcd.type.equals(COSMOS_AUTH_TYPE_ACCOUNT) ||
                lcd.type.equals(BaseConstant.COSMOS_AUTH_TYPE_ACCOUNT_LEGACY) ||
                lcd.type.equals(BaseConstant.IRIS_BANK_TYPE_ACCOUNT) ||
                lcd.result.type.equals(COSMOS_AUTH_TYPE_CERTIK_MANUAL)) {
            result.address = lcd.value.address;
            result.sequenceNumber = Integer.parseInt(lcd.value.sequence);
            result.accountNumber = Integer.parseInt(lcd.value.account_number);
            return result;
        } else {
            result.address = lcd.value.BaseVestingAccount.BaseAccount.address;
            result.sequenceNumber = Integer.parseInt(lcd.value.BaseVestingAccount.BaseAccount.sequence);
            result.accountNumber = Integer.parseInt(lcd.value.BaseVestingAccount.BaseAccount.account_number);
            return result;
        }
    }

    public static Account getAccountFromBnbLcd(long id, ResBnbAccountInfo lcd) {
        Account result = new Account();
        result.id = id;
        result.address = lcd.address;
        result.sequenceNumber = Integer.parseInt(lcd.sequence);
        result.accountNumber = Integer.parseInt(lcd.account_number);
        return result;
    }

    public static Account getAccountFromKavaLcd(long id, ResLcdKavaAccountInfo lcd) {
        Account result = new Account();
        result.id = id;
        if (lcd.result != null && lcd.height != null) {
            if (lcd.result.type.equals(COSMOS_AUTH_TYPE_ACCOUNT)) {
                result.address = lcd.result.value.address;
                result.sequenceNumber = Integer.parseInt(lcd.result.value.sequence);
                result.accountNumber = Integer.parseInt(lcd.result.value.account_number);

            } else if (lcd.result.type.equals(BaseConstant.COSMOS_AUTH_TYPE_VESTING_ACCOUNT) || lcd.result.type.equals(COSMOS_AUTH_TYPE_P_VESTING_ACCOUNT)) {
                result.address = lcd.result.value.address;
                result.sequenceNumber = Integer.parseInt(lcd.result.value.sequence);
                result.accountNumber = Integer.parseInt(lcd.result.value.account_number);
            }
        }
        return result;
    }

    public static Account getAccountFromOkLcd(long id, ResOkAccountInfo lcd) {
        Account result = new Account();
        result.id = id;
        if (lcd.type.equals(COSMOS_AUTH_TYPE_OKEX_ACCOUNT)) {
            result.address = lcd.value.address;
            result.sequenceNumber = Integer.parseInt(lcd.value.sequence);
            result.accountNumber = Integer.parseInt(lcd.value.account_number);
        }
        return result;
    }

    public static Account getAccountFromVestingLcd(long id, ResLcdVestingAccountInfo lcd) {
        Account result = new Account();
        result.id = id;
        if (lcd.result != null && lcd.height != null) {
            result.address = lcd.result.value.address;
            result.sequenceNumber = Integer.parseInt(lcd.result.value.sequence);
            result.accountNumber = Integer.parseInt(lcd.result.value.account_number);
        }
        return result;
    }


    public static ArrayList<Balance> getBalancesFromLcd(long accountId, ResLcdAccountInfo lcd) {
        long time = System.currentTimeMillis();
        ArrayList<Balance> result = new ArrayList<>();
        if (lcd != null && lcd.result != null && lcd.height != null) {
            if(lcd.result.type.equals(COSMOS_AUTH_TYPE_ACCOUNT) ||
                    lcd.result.type.equals(BaseConstant.COSMOS_AUTH_TYPE_ACCOUNT_LEGACY) ||
                    lcd.result.type.equals(BaseConstant.IRIS_BANK_TYPE_ACCOUNT) ||
                    lcd.result.type.equals(COSMOS_AUTH_TYPE_CERTIK_MANUAL)) {
                if (lcd.result.value.coins != null && lcd.result.value.coins.size() > 0){
                    for (Coin coin : lcd.result.value.coins) {
                        Balance temp = new Balance();
                        temp.accountId = accountId;
                        temp.symbol = coin.denom;
                        temp.balance = new BigDecimal(coin.amount);
                        temp.fetchTime = time;
                        result.add(temp);
                    }
                }
                return result;
            } else {
                if (lcd.result.value.BaseVestingAccount.BaseAccount.coins != null && lcd.result.value.BaseVestingAccount.BaseAccount.coins.size() > 0){
                    for(Coin coin : lcd.result.value.BaseVestingAccount.BaseAccount.coins) {
                        Balance temp = new Balance();
                        temp.accountId = accountId;
                        temp.symbol = coin.denom;
                        temp.balance = new BigDecimal(coin.amount);
                        temp.fetchTime = time;
                        result.add(temp);
                    }
                }
                return result;
            }
        }
        if(lcd.type.equals(COSMOS_AUTH_TYPE_ACCOUNT) ||
                lcd.type.equals(BaseConstant.COSMOS_AUTH_TYPE_ACCOUNT_LEGACY) ||
                lcd.type.equals(BaseConstant.IRIS_BANK_TYPE_ACCOUNT) ||
                lcd.result.type.equals(COSMOS_AUTH_TYPE_CERTIK_MANUAL)) {
            if (lcd.value.coins != null && lcd.value.coins.size() > 0){
                for(Coin coin : lcd.value.coins) {
                    Balance temp = new Balance();
                    temp.accountId = accountId;
                    temp.symbol = coin.denom;
                    temp.balance = new BigDecimal(coin.amount);
                    temp.fetchTime = time;
                    result.add(temp);
                }
            }
            return result;
        } else {
            if (lcd.value.BaseVestingAccount.BaseAccount.coins != null && lcd.value.BaseVestingAccount.BaseAccount.coins.size() > 0){
                for(Coin coin : lcd.value.BaseVestingAccount.BaseAccount.coins) {
                    Balance temp = new Balance();
                    temp.accountId = accountId;
                    temp.symbol = coin.denom;
                    temp.balance = new BigDecimal(coin.amount);
                    temp.fetchTime = time;
                    result.add(temp);
                }
            }
            return result;
        }
    }

    public static ArrayList<Balance> getBalancesFromBnbLcd(long accountId, ResBnbAccountInfo lcd) {
        long time = System.currentTimeMillis();
        ArrayList<Balance> result = new ArrayList<>();
        if (lcd.balances != null && lcd.balances.size() > 0) {
            for(ResBnbAccountInfo.BnbBalance coin : lcd.balances) {
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

    public static ArrayList<Balance> getBalancesFromKavaLcd(long accountId, ResLcdKavaAccountInfo lcd) {
        long time = System.currentTimeMillis();
        ArrayList<Balance> result = new ArrayList<>();
        if (lcd != null && lcd.result != null && lcd.height != null) {
            if (lcd.result.type.equals(COSMOS_AUTH_TYPE_ACCOUNT)) {
                if (lcd.result.value.coins != null && lcd.result.value.coins.size() > 0) {
                    for (Coin coin : lcd.result.value.coins) {
                        Balance temp = new Balance();
                        temp.accountId = accountId;
                        temp.symbol = coin.denom;
                        temp.balance = new BigDecimal(coin.amount);
                        temp.fetchTime = time;
                        result.add(temp);
                    }
                }
                return result;

            }  else if (lcd.result.type.equals(BaseConstant.COSMOS_AUTH_TYPE_VESTING_ACCOUNT) || lcd.result.type.equals(COSMOS_AUTH_TYPE_P_VESTING_ACCOUNT)) {
                BigDecimal dpBalance = BigDecimal.ZERO;
                BigDecimal dpVesting = BigDecimal.ZERO;
                BigDecimal originalVesting = BigDecimal.ZERO;
                BigDecimal remainVesting = BigDecimal.ZERO;
                BigDecimal delegatedVesting = BigDecimal.ZERO;

                if (lcd.result.value.coins != null && lcd.result.value.coins.size() > 0) {
                    for (Coin coin : lcd.result.value.coins) {
                        if (coin.denom.equals(TOKEN_KAVA)) {
                            dpBalance = BigDecimal.ZERO;
                            dpVesting = BigDecimal.ZERO;
                            originalVesting = BigDecimal.ZERO;
                            remainVesting = BigDecimal.ZERO;
                            delegatedVesting = BigDecimal.ZERO;
                            dpBalance = new BigDecimal(coin.amount);

                            if (lcd.result.value.original_vesting != null && lcd.result.value.original_vesting.size() > 0) {
                                for (Coin vesting : lcd.result.value.original_vesting) {
                                    if (vesting.denom.equals(TOKEN_KAVA)) {
                                        originalVesting = originalVesting.add(new BigDecimal(vesting.amount));
                                    }
                                }
                            }

                            if (lcd.result.value.delegated_vesting != null && lcd.result.value.delegated_vesting.size() > 0) {
                                for (Coin vesting : lcd.result.value.delegated_vesting) {
                                    if (vesting.denom.equals(TOKEN_KAVA)) {
                                        delegatedVesting = delegatedVesting.add(new BigDecimal(vesting.amount));
                                    }
                                }
                            }

                            WLog.w("kava dpBalance " +  dpBalance);
                            WLog.w("kava originalVesting " +  originalVesting);
                            WLog.w("kava delegatedVesting " +  delegatedVesting);

                            remainVesting = lcd.result.value.getCalcurateVestingAmountSumByDenom(TOKEN_KAVA);
                            WLog.w("kava remainVesting " +  remainVesting);

                            dpVesting = remainVesting.subtract(delegatedVesting);
                            WLog.w("kava  dpVesting " +  dpVesting);
                            if (dpVesting.compareTo(BigDecimal.ZERO) <= 0) {
                                dpVesting = BigDecimal.ZERO;
                            }
                            WLog.w("kava  dpVesting1 " +  dpVesting);

                            if (remainVesting.compareTo(delegatedVesting) > 0) {
                                dpBalance = dpBalance.subtract(remainVesting).add(delegatedVesting);
                            }
                            WLog.w("kava dpBalancee " +  dpBalance);

                            Balance temp = new Balance();
                            temp.accountId = accountId;
                            temp.symbol = TOKEN_KAVA;
                            temp.balance = dpBalance;
                            temp.frozen = delegatedVesting;
                            temp.locked = dpVesting;
                            temp.fetchTime = time;
                            result.add(temp);


                        } else if (coin.denom.equals(TOKEN_HARD)) {
                            dpBalance = BigDecimal.ZERO;
                            dpVesting = BigDecimal.ZERO;
                            originalVesting = BigDecimal.ZERO;
                            remainVesting = BigDecimal.ZERO;
                            delegatedVesting = BigDecimal.ZERO;
                            dpBalance = new BigDecimal(coin.amount);

                            if (lcd.result.value.original_vesting != null && lcd.result.value.original_vesting.size() > 0) {
                                for (Coin vesting : lcd.result.value.original_vesting) {
                                    if (vesting.denom.equals(TOKEN_HARD)) {
                                        originalVesting = originalVesting.add(new BigDecimal(vesting.amount));
                                    }
                                }
                            }
                            WLog.w("hard dpBalance " +  dpBalance);
                            WLog.w("hard originalVesting " +  originalVesting);
                            remainVesting = lcd.result.value.getCalcurateVestingAmountSumByDenom(TOKEN_HARD);

                            dpBalance = dpBalance.subtract(remainVesting);
                            WLog.w("hard dpBalancee " +  dpBalance);

                            Balance temp = new Balance();
                            temp.accountId = accountId;
                            temp.symbol = coin.denom;
                            temp.balance = dpBalance;
                            temp.locked = remainVesting;
                            temp.fetchTime = time;
                            result.add(temp);

                        } else {
                            Balance temp = new Balance();
                            temp.accountId = accountId;
                            temp.symbol = coin.denom;
                            temp.balance = new BigDecimal(coin.amount);
                            temp.fetchTime = time;
                            result.add(temp);
                        }
                    }
                }
            }
        }
        return result;
    }

    public static ArrayList<Balance> getBalancesFromOkLcd(long accountId, ResOkAccountToken lcd) {
        long time = System.currentTimeMillis();
        ArrayList<Balance> result = new ArrayList<>();
        if (lcd.data != null && lcd.data.currencies != null && lcd.data.currencies.size() > 0) {
            for(ResOkAccountToken.OkCurrency currency : lcd.data.currencies) {
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

    public static ArrayList<Balance> getBalancesFromVestingLcd(long accountId, ResLcdVestingAccountInfo lcd) {
        long time = System.currentTimeMillis();
        ArrayList<Balance> result = new ArrayList<>();
        if (lcd != null && lcd.result != null && lcd.height != null) {
            if (lcd.result.type.equals(COSMOS_AUTH_TYPE_ACCOUNT)) {
                if (lcd.result.value.coins != null && lcd.result.value.coins.size() > 0) {
                    for(Coin coin : lcd.result.value.coins) {
                        Balance temp = new Balance();
                        temp.accountId = accountId;
                        temp.symbol = coin.denom;
                        temp.balance = new BigDecimal(coin.amount);
                        temp.fetchTime = time;
                        result.add(temp);
                    }
                }

            } else if (lcd.result.type.equals(COSMOS_AUTH_TYPE_P_VESTING_ACCOUNT)) {
                for( Coin coin : lcd.result.value.coins) {
                    String denom = coin.denom;
                    BigDecimal dpBalance = BigDecimal.ZERO;
                    BigDecimal dpVesting = BigDecimal.ZERO;
                    BigDecimal originalVesting = BigDecimal.ZERO;
                    BigDecimal remainVesting = BigDecimal.ZERO;
                    BigDecimal delegatedVesting = BigDecimal.ZERO;

                    dpBalance = new BigDecimal(coin.amount);
                    WLog.w("dpBalance " +  denom + "  " +  dpBalance);

                    for (Coin vesting : lcd.result.value.original_vesting) {
                        if (vesting.denom.equals(denom)) {
                            originalVesting = originalVesting.add(new BigDecimal(vesting.amount));
                        }
                    }
                    WLog.w("originalVesting " +  denom + "  " +  originalVesting);

                    for (Coin vesting : lcd.result.value.delegated_vesting) {
                        if (vesting.denom.equals(denom)) {
                            delegatedVesting = delegatedVesting.add(new BigDecimal(vesting.amount));
                        }
                    }
                    WLog.w("delegatedVesting " +  denom + "  " +  delegatedVesting);

                    remainVesting = lcd.getCalcurateVestingAmountSumByDenom(denom);
                    WLog.w("remainVesting " +  denom + "  " +  remainVesting);

                    dpVesting = remainVesting.subtract(delegatedVesting);
                    WLog.w("dpVestingA " +  denom + "  " +  dpVesting);

                    dpVesting = dpVesting.compareTo(BigDecimal.ZERO) <= 0 ? BigDecimal.ZERO : dpVesting;
                    WLog.w("dpVestingB " +  denom + "  " +  dpVesting);

                    if (remainVesting.compareTo(delegatedVesting)> 0) {
                        dpBalance = dpBalance.subtract(remainVesting).add(delegatedVesting);
                    }
                    WLog.w("final dpBalance  " +  denom + "  " +  dpBalance);

                    Balance temp = new Balance();
                    temp.accountId = accountId;
                    temp.symbol = denom;
                    temp.balance = dpBalance;
                    temp.frozen = delegatedVesting;
                    temp.locked = dpVesting;
                    temp.fetchTime = time;
                    result.add(temp);
                }

            } else if (lcd.result.type.equals(COSMOS_AUTH_TYPE_C_VESTING_ACCOUNT)) {
                for( Coin coin : lcd.result.value.coins) {
                    String denom = coin.denom;
                    BigDecimal dpBalance = BigDecimal.ZERO;
                    BigDecimal dpVesting = BigDecimal.ZERO;
                    BigDecimal originalVesting = BigDecimal.ZERO;
                    BigDecimal remainVesting = BigDecimal.ZERO;
                    BigDecimal delegatedVesting = BigDecimal.ZERO;

                    dpBalance = new BigDecimal(coin.amount);
                    WLog.w("dpBalance " +  denom + "  " +  dpBalance);

                    for (Coin vesting : lcd.result.value.original_vesting) {
                        if (vesting.denom.equals(denom)) {
                            originalVesting = originalVesting.add(new BigDecimal(vesting.amount));
                        }
                    }
                    WLog.w("originalVesting " +  denom + "  " +  originalVesting);

                    for (Coin vesting : lcd.result.value.delegated_vesting) {
                        if (vesting.denom.equals(denom)) {
                            delegatedVesting = delegatedVesting.add(new BigDecimal(vesting.amount));
                        }
                    }
                    WLog.w("delegatedVesting " +  denom + "  " +  delegatedVesting);

                    long cTime = Calendar.getInstance().getTime().getTime();
                    long vestingStart = lcd.result.value.getStartTime() * 1000;
                    long vestingEnd = lcd.result.value.getEndTime() * 1000;
                    if (cTime < vestingStart) {
                        remainVesting = originalVesting;
                    } else if (cTime > vestingEnd) {
                        remainVesting = BigDecimal.ZERO;
                    } else if (cTime < vestingEnd) {
                        float progress = ((float)(cTime - vestingStart) / (float)(vestingEnd - vestingStart));
                        remainVesting = originalVesting.multiply(new BigDecimal(1 - progress)).setScale(0, RoundingMode.UP);
                    }
                    WLog.w("remainVesting " +  denom + "  " +  remainVesting);

                    dpVesting = remainVesting.subtract(delegatedVesting);
                    WLog.w("dpVestingA " +  denom + "  " +  dpVesting);

                    dpVesting = dpVesting.compareTo(BigDecimal.ZERO) <= 0 ? BigDecimal.ZERO : dpVesting;
                    WLog.w("dpVestingB " +  denom + "  " +  dpVesting);

                    if (remainVesting.compareTo(delegatedVesting)> 0) {
                        dpBalance = dpBalance.subtract(remainVesting).add(delegatedVesting);
                    }
                    WLog.w("final dpBalance  " +  denom + "  " +  dpBalance);

                    Balance temp = new Balance();
                    temp.accountId = accountId;
                    temp.symbol = denom;
                    temp.balance = dpBalance;
                    temp.frozen = delegatedVesting;
                    temp.locked = dpVesting;
                    temp.fetchTime = time;
                    result.add(temp);
                }

            } else if (lcd.result.type.equals(COSMOS_AUTH_TYPE_D_VESTING_ACCOUNT)) {
                for( Coin coin : lcd.result.value.coins) {
                    String denom = coin.denom;
                    BigDecimal dpBalance = BigDecimal.ZERO;
                    BigDecimal dpVesting = BigDecimal.ZERO;
                    BigDecimal originalVesting = BigDecimal.ZERO;
                    BigDecimal remainVesting = BigDecimal.ZERO;
                    BigDecimal delegatedVesting = BigDecimal.ZERO;

                    dpBalance = new BigDecimal(coin.amount);
                    WLog.w("dpBalance " +  denom + "  " +  dpBalance);

                    for (Coin vesting : lcd.result.value.original_vesting) {
                        if (vesting.denom.equals(denom)) {
                            originalVesting = originalVesting.add(new BigDecimal(vesting.amount));
                        }
                    }
                    WLog.w("originalVesting " +  denom + "  " +  originalVesting);

                    for (Coin vesting : lcd.result.value.delegated_vesting) {
                        if (vesting.denom.equals(denom)) {
                            delegatedVesting = delegatedVesting.add(new BigDecimal(vesting.amount));
                        }
                    }
                    WLog.w("delegatedVesting " +  denom + "  " +  delegatedVesting);

                    long cTime = Calendar.getInstance().getTime().getTime();
                    long vestingEnd = lcd.result.value.getEndTime() * 1000;

                    if (cTime < vestingEnd) {
                        remainVesting = originalVesting;
                    }
                    WLog.w("remainVesting " +  denom + "  " +  remainVesting);

                    dpVesting = remainVesting.subtract(delegatedVesting);
                    WLog.w("dpVestingA " +  denom + "  " +  dpVesting);

                    dpVesting = dpVesting.compareTo(BigDecimal.ZERO) <= 0 ? BigDecimal.ZERO : dpVesting;
                    WLog.w("dpVestingB " +  denom + "  " +  dpVesting);

                    if (remainVesting.compareTo(delegatedVesting)> 0) {
                        dpBalance = dpBalance.subtract(remainVesting).add(delegatedVesting);
                    }
                    WLog.w("final dpBalance  " +  denom + "  " +  dpBalance);

                    Balance temp = new Balance();
                    temp.accountId = accountId;
                    temp.symbol = denom;
                    temp.balance = dpBalance;
                    temp.frozen = delegatedVesting;
                    temp.locked = dpVesting;
                    temp.fetchTime = time;
                    result.add(temp);
                }

            }
        }
        return result;
    }

    public static Balance getTokenBalance(ArrayList<Balance> list, String symbol) {
        for (Balance balance:list) {
            if (balance.symbol.equals(symbol)) {
                return balance;
            }
        }
        return null;
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
        if(pincode.length() != 5)
            return false;
        String regex = "^\\d{4}+[A-Z]{1}$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(pincode);
        boolean isNormal = m.matches();
        return isNormal;
    }


    public static long cosmosTimetoLocalLong(Context c, String rawValue) {
        try {
            SimpleDateFormat cosmosFormat = new SimpleDateFormat(c.getString(R.string.str_block_time_format));
            cosmosFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            return cosmosFormat.parse(rawValue).getTime();
        } catch (Exception e) {

        }
        return 0;
    }


    public static Gson getPresentor(){
//        return new GsonBuilder().disableHtmlEscaping().serializeNulls().create();
        return new GsonBuilder().disableHtmlEscaping().create();
    }



    public static String ByteArrayToHexString(byte[] bytes) {
        final char[] hexArray = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
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
                    + Character.digit(s.charAt(i+1), 16));
        }
        return data;
    }

    public static int[] Bytearray2intarray(byte[] barray) {
        int[] iarray = new int[barray.length];
        int i = 0;
        for (byte b : barray)
            iarray[i++] = b & 0xff;
        return iarray;
    }

    public static String BytearryToDecimalString(byte[] barray) {
        String result = "";
        int[] iarray = new int[barray.length];
        int i = 0;
        for (byte b : barray) {
            iarray[i++] = b & 0xff;
            result = result + " " + (b & 0xff);
        }
        return result;
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

    public static String hexToStr(String hex) {
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
        Collections.sort(validators, new Comparator<Validator>() {
            @Override
            public int compare(Validator o1, Validator o2) {
                if(o1.description.moniker.equalsIgnoreCase("Cosmostation")) return -1;
                if(o2.description.moniker.equalsIgnoreCase("Cosmostation")) return 1;
                return o1.description.moniker.compareTo(o2.description.moniker);
            }
        });
        Collections.sort(validators, new Comparator<Validator>() {
            @Override
            public int compare(Validator o1, Validator o2) {
                if (o1.jailed && !o2.jailed) return 1;
                else if (!o1.jailed && o2.jailed) return -1;
                else return 0;
            }
        });
    }

    public static void onSortByValidatorNameV1(ArrayList<Staking.Validator> validators) {
        Collections.sort(validators, new Comparator<Staking.Validator>() {
            @Override
            public int compare(Staking.Validator o1, Staking.Validator o2) {
                if(o1.getDescription().getMoniker().equalsIgnoreCase("Cosmostation")) return -1;
                if(o2.getDescription().getMoniker().equalsIgnoreCase("Cosmostation")) return 1;
                return o1.getDescription().getMoniker().compareTo(o2.getDescription().getMoniker());
            }
        });
        Collections.sort(validators, new Comparator<Staking.Validator>() {
            @Override
            public int compare(Staking.Validator o1, Staking.Validator o2) {
                if (o1.getJailed() && !o2.getJailed()) return 1;
                else if (!o1.getJailed() && o2.getJailed()) return -1;
                else return 0;
            }
        });
    }

    public static void onSortByValidatorPower(ArrayList<Validator> validators) {
        Collections.sort(validators, new Comparator<Validator>() {
            @Override
            public int compare(Validator o1, Validator o2) {
                if(o1.description.moniker.equalsIgnoreCase("Cosmostation")) return -1;
                if(o2.description.moniker.equalsIgnoreCase("Cosmostation")) return 1;

                if (Double.parseDouble(o1.tokens) > Double.parseDouble(o2.tokens)) return -1;
                else if (Double.parseDouble(o1.tokens) < Double.parseDouble(o2.tokens)) return 1;
                else return 0;
            }
        });
        Collections.sort(validators, new Comparator<Validator>() {
            @Override
            public int compare(Validator o1, Validator o2) {
                if (o1.jailed && !o2.jailed) return 1;
                else if (!o1.jailed && o2.jailed) return -1;
                else return 0;
            }
        });
    }

    public static void onSortByValidatorPowerV1(ArrayList<Staking.Validator> validators) {
        Collections.sort(validators, new Comparator<Staking.Validator>() {
            @Override
            public int compare(Staking.Validator o1, Staking.Validator o2) {
                if(o1.getDescription().getMoniker().equalsIgnoreCase("Cosmostation")) return -1;
                if(o2.getDescription().getMoniker().equalsIgnoreCase("Cosmostation")) return 1;

                if (Double.parseDouble(o1.getTokens()) > Double.parseDouble(o2.getTokens())) return -1;
                else if (Double.parseDouble(o1.getTokens()) < Double.parseDouble(o2.getTokens())) return 1;
                else return 0;
            }
        });
        Collections.sort(validators, new Comparator<Staking.Validator>() {
            @Override
            public int compare(Staking.Validator o1, Staking.Validator o2) {
                if (o1.getJailed() && !o2.getJailed()) return 1;
                else if (!o1.getJailed() && o2.getJailed()) return -1;
                else return 0;
            }
        });
    }

    public static void onSortByOKValidatorPower(ArrayList<Validator> validators) {
        Collections.sort(validators, new Comparator<Validator>() {
            @Override
            public int compare(Validator o1, Validator o2) {
                if(o1.description.moniker.equalsIgnoreCase("Cosmostation")) return -1;
                if(o2.description.moniker.equalsIgnoreCase("Cosmostation")) return 1;

                if (Double.parseDouble(o1.delegator_shares) > Double.parseDouble(o2.delegator_shares)) return -1;
                else if (Double.parseDouble(o1.delegator_shares) < Double.parseDouble(o2.delegator_shares)) return 1;
                else return 0;
            }
        });
        Collections.sort(validators, new Comparator<Validator>() {
            @Override
            public int compare(Validator o1, Validator o2) {
                if (o1.jailed && !o2.jailed) return 1;
                else if (!o1.jailed && o2.jailed) return -1;
                else return 0;
            }
        });
    }



    public static void onSortByDelegate(ArrayList<Validator> validators, final BaseData dao) {
        Collections.sort(validators, new Comparator<Validator>() {
            @Override
            public int compare(Validator o1, Validator o2) {
                if(o1.description.moniker.equalsIgnoreCase("Cosmostation")) return -1;
                if(o2.description.moniker.equalsIgnoreCase("Cosmostation")) return 1;
                BigDecimal bondingO1 = dao.delegatedAmountByValidator(o1.operator_address);
                BigDecimal bondingO2 = dao.delegatedAmountByValidator(o2.operator_address);
                return bondingO2.compareTo(bondingO1);

            }
        });
        Collections.sort(validators, new Comparator<Validator>() {
            @Override
            public int compare(Validator o1, Validator o2) {
                if (o1.jailed && !o2.jailed) return 1;
                else if (!o1.jailed && o2.jailed) return -1;
                else return 0;
            }
        });
    }

    public static void onSortByDelegateV1(ArrayList<Staking.Validator> validators, final BaseData dao) {
        Collections.sort(validators, new Comparator<Staking.Validator>() {
            @Override
            public int compare(Staking.Validator o1, Staking.Validator o2) {
                if(o1.getDescription().getMoniker().equalsIgnoreCase("Cosmostation")) return -1;
                if(o2.getDescription().getMoniker().equalsIgnoreCase("Cosmostation")) return 1;
                BigDecimal bondingO1 = dao.getDelegation(o1.getOperatorAddress());
                BigDecimal bondingO2 = dao.getDelegation(o2.getOperatorAddress());
                return bondingO2.compareTo(bondingO1);
            }
        });
        Collections.sort(validators, new Comparator<Staking.Validator>() {
            @Override
            public int compare(Staking.Validator o1, Staking.Validator o2) {
                if (o1.getJailed() && !o2.getJailed()) return 1;
                else if (!o1.getJailed() && o2.getJailed()) return -1;
                else return 0;
            }
        });
    }

    public static void onSortByReward(ArrayList<Validator> validators, String denom, BaseData basedata) {
        Collections.sort(validators, new Comparator<Validator>() {
            @Override
            public int compare(Validator o1, Validator o2) {
                if(o1.description.moniker.equalsIgnoreCase("Cosmostation")) return -1;
                if(o2.description.moniker.equalsIgnoreCase("Cosmostation")) return 1;

                BigDecimal rewardO1 = basedata.rewardAmountByValidator(denom, o1.operator_address);
                BigDecimal rewardO2 = basedata.rewardAmountByValidator(denom, o2.operator_address);
                return rewardO2.compareTo(rewardO1);
            }
        });
        Collections.sort(validators, new Comparator<Validator>() {
            @Override
            public int compare(Validator o1, Validator o2) {
                if (o1.jailed && !o2.jailed) return 1;
                else if (!o1.jailed && o2.jailed) return -1;
                else return 0;
            }
        });
    }

    public static void onSortByRewardV1(ArrayList<Staking.Validator> validators, String denom, final BaseData dao) {
        Collections.sort(validators, new Comparator<Staking.Validator>() {
            @Override
            public int compare(Staking.Validator o1, Staking.Validator o2) {
                if(o1.getDescription().getMoniker().equalsIgnoreCase("Cosmostation")) return -1;
                if(o2.getDescription().getMoniker().equalsIgnoreCase("Cosmostation")) return 1;
                BigDecimal rewardO1 = dao.getReward(denom, o1.getOperatorAddress());
                BigDecimal rewardO2 = dao.getReward(denom, o2.getOperatorAddress());
                return rewardO2.compareTo(rewardO1);
            }
        });
        Collections.sort(validators, new Comparator<Staking.Validator>() {
            @Override
            public int compare(Staking.Validator o1, Staking.Validator o2) {
                if (o1.getJailed() && !o2.getJailed()) return 1;
                else if (!o1.getJailed() && o2.getJailed()) return -1;
                else return 0;
            }
        });
    }

    public static void onSortByOnlyReward(ArrayList<Validator> validators, String denom, BaseData basedata) {
        Collections.sort(validators, new Comparator<Validator>() {
            @Override
            public int compare(Validator o1, Validator o2) {
                BigDecimal rewardO1 = basedata.rewardAmountByValidator(denom, o1.operator_address);
                BigDecimal rewardO2 = basedata.rewardAmountByValidator(denom, o2.operator_address);
                return rewardO2.compareTo(rewardO1);
            }
        });
    }

    public static void onSortRewardAmount(ArrayList<Distribution.DelegationDelegatorReward> rewards, String denom) {
        Collections.sort(rewards, new Comparator<Distribution.DelegationDelegatorReward>() {
            @Override
            public int compare(Distribution.DelegationDelegatorReward o1, Distribution.DelegationDelegatorReward o2) {
                BigDecimal rewardO1 = getGrpcRewardAmount(o1, denom);
                BigDecimal rewardO2 = getGrpcRewardAmount(o2, denom);
                return rewardO2.compareTo(rewardO1);
            }
        });
    }

    public static BigDecimal getGrpcRewardAmount(Distribution.DelegationDelegatorReward reward, String denom) {
        BigDecimal result = BigDecimal.ZERO;
        result = decCoinAmount(reward.getRewardList(), denom);
        return result;
    }

    public static BigDecimal decCoinAmount(List<CoinOuterClass.DecCoin> coins, String denom) {
        BigDecimal result = BigDecimal.ZERO;
        for (CoinOuterClass.DecCoin coin: coins) {
            if (coin.getDenom().equals(denom)) {
                return new BigDecimal(coin.getAmount()).movePointLeft(18).setScale(0, RoundingMode.DOWN);
            }
        }
        return result;
    }

    public static void onSortingByCommission(ArrayList<Validator> validators, final BaseChain chain) {
        Collections.sort(validators, new Comparator<Validator>() {
            @Override
            public int compare(Validator o1, Validator o2) {
                if(o1.description.moniker.equalsIgnoreCase("Cosmostation")) return -1;
                if(o2.description.moniker.equalsIgnoreCase("Cosmostation")) return 1;
                if (Float.parseFloat(o1.commission.commission_rates.rate) > Float.parseFloat(o2.commission.commission_rates.rate)) return 1;
                else if (Float.parseFloat(o1.commission.commission_rates.rate) < Float.parseFloat(o2.commission.commission_rates.rate)) return -1;
                else return 0;
            }
        });
        Collections.sort(validators, new Comparator<Validator>() {
            @Override
            public int compare(Validator o1, Validator o2) {
                if (o1.jailed && !o2.jailed) return 1;
                else if (!o1.jailed && o2.jailed) return -1;
                else return 0;
            }
        });
    }

    public static void onSortingByCommissionV1(ArrayList<Staking.Validator> validators) {
        Collections.sort(validators, new Comparator<Staking.Validator>() {
            @Override
            public int compare(Staking.Validator o1, Staking.Validator o2) {
                if(o1.getDescription().getMoniker().equalsIgnoreCase("Cosmostation")) return -1;
                if(o2.getDescription().getMoniker().equalsIgnoreCase("Cosmostation")) return 1;
                if (Float.parseFloat(o1.getCommission().getCommissionRates().getRate()) > Float.parseFloat(o2.getCommission().getCommissionRates().getRate())) return 1;
                else if (Float.parseFloat(o1.getCommission().getCommissionRates().getRate()) < Float.parseFloat(o2.getCommission().getCommissionRates().getRate())) return -1;
                else return 0;
            }
        });
        Collections.sort(validators, new Comparator<Staking.Validator>() {
            @Override
            public int compare(Staking.Validator o1, Staking.Validator o2) {
                if (o1.getJailed() && !o2.getJailed()) return 1;
                else if (!o1.getJailed() && o2.getJailed()) return -1;
                else return 0;
            }
        });
    }

    public static void onSortingProposal(ArrayList<Proposal> proposals, BaseChain chain) {
        Collections.sort(proposals, new Comparator<Proposal>() {
            @Override
            public int compare(Proposal o1, Proposal o2) {
                if (Integer.parseInt(o1.id) < Integer.parseInt(o2.id)) return 1;
                else if (Integer.parseInt(o1.id) > Integer.parseInt(o2.id)) return -1;
                else return 0;
            }
        });
    }

    public static void onSortingGrpcProposals(ArrayList<Gov.Proposal> proposals) {
        Collections.sort(proposals, new Comparator<Gov.Proposal>() {
            @Override
            public int compare(Gov.Proposal o1, Gov.Proposal o2) {
                if (o1.getProposalId() < o2.getProposalId()) return 1;
                else if (o1.getProposalId() > o2.getProposalId()) return -1;
                return 0;
            }
        });
    }

    public static void onSortingTokenByAmount(ArrayList<Balance> balances, final BaseChain chain) {
        Collections.sort(balances, new Comparator<Balance>() {
            @Override
            public int compare(Balance o1, Balance o2) {
                if (chain.equals(BNB_MAIN) || chain.equals(BNB_TEST)) {
                    if(o1.symbol.equals(TOKEN_BNB)) return -1;
                    if(o2.symbol.equals(TOKEN_BNB)) return 1;

                } else if (chain.equals(KAVA_MAIN) || chain.equals(KAVA_TEST)) {
                    if(o1.symbol.equals(TOKEN_KAVA)) return -1;
                    if(o2.symbol.equals(TOKEN_KAVA)) return 1;
                    return o2.balance.movePointLeft(WUtil.getKavaCoinDecimal(o2.symbol)).compareTo(o1.balance.movePointLeft(WUtil.getKavaCoinDecimal(o1.symbol)));

                } else if (chain.equals(IOV_MAIN)) {
                    if(o1.symbol.equals(TOKEN_IOV)) return -1;
                    if(o2.symbol.equals(TOKEN_IOV)) return 1;

                } else if (chain.equals(BAND_MAIN)) {
                    if(o1.symbol.equals(TOKEN_BAND)) return -1;
                    if(o2.symbol.equals(TOKEN_BAND)) return 1;

                } else if (chain.equals(IOV_TEST)) {
                    if(o1.symbol.equals(TOKEN_IOV_TEST)) return -1;
                    if(o2.symbol.equals(TOKEN_IOV_TEST)) return 1;

                } else if (chain.equals(OKEX_MAIN) || chain.equals(OK_TEST)) {
                    if(o1.symbol.equals(TOKEN_OK)) return -1;
                    if(o2.symbol.equals(TOKEN_OK)) return 1;
                    if(o1.symbol.equals(TOKEN_OK_OKB)) return -1;
                    if(o2.symbol.equals(TOKEN_OK_OKB)) return 1;
                    if(o1.symbol.equals("okb-c4d")) return -1;
                    if(o2.symbol.equals("okb-c4d")) return 1;

                } else if (chain.equals(CERTIK_MAIN) || chain.equals(CERTIK_TEST)) {
                    if(o1.symbol.equals(TOKEN_CERTIK)) return -1;
                    if(o2.symbol.equals(TOKEN_CERTIK)) return 1;

                } else if (chain.equals(SIF_MAIN)) {
                    if(o1.symbol.equals(TOKEN_SIF)) return -1;
                    if(o2.symbol.equals(TOKEN_SIF)) return 1;

                } else if (chain.equals(KI_MAIN)) {
                    if(o1.symbol.equals(TOKEN_KI)) return -1;
                    if(o2.symbol.equals(TOKEN_KI)) return 1;

                }
                return o2.balance.compareTo(o1.balance);
            }
        });
    }

    public static void onSortingTokenByAmountV1(ArrayList<Coin> coin, final BaseChain chain) {
        Collections.sort(coin, new Comparator<Coin>() {
            @Override
            public int compare(Coin o1, Coin o2) {
                if (chain.equals(COSMOS_MAIN)) {
                    if(o1.denom.equals(TOKEN_ATOM)) return -1;
                    if(o2.denom.equals(TOKEN_ATOM)) return 1;

                } else if (chain.equals(IRIS_MAIN)) {
                    if(o1.denom.equals(TOKEN_IRIS)) return -1;
                    if(o2.denom.equals(TOKEN_IRIS)) return 1;

                } else if (chain.equals(AKASH_MAIN)) {
                    if(o1.denom.equals(TOKEN_AKASH)) return -1;
                    if(o2.denom.equals(TOKEN_AKASH)) return 1;

                } else if (chain.equals(PERSIS_MAIN)) {
                    if(o1.denom.equals(TOKEN_XPRT)) return -1;
                    if(o2.denom.equals(TOKEN_XPRT)) return 1;

                } else if (chain.equals(CRYPTO_MAIN)) {
                    if(o1.denom.equals(TOKEN_XPRT)) return -1;
                    if(o2.denom.equals(TOKEN_XPRT)) return 1;

                }

                else if (chain.equals(COSMOS_TEST)) {
                    if(o1.denom.equals(TOKEN_COSMOS_TEST)) return -1;
                    if(o2.denom.equals(TOKEN_COSMOS_TEST)) return 1;

                } else if (chain.equals(IRIS_TEST)) {
                    if(o1.denom.equals(TOKEN_IRIS_TEST)) return -1;
                    if(o2.denom.equals(TOKEN_IRIS_TEST)) return 1;

                }
                return o1.denom.compareTo(o2.denom);
            }
        });
    }

    public static void onSortingTokenByName(ArrayList<Balance> balances, final BaseChain chain) {
        Collections.sort(balances, new Comparator<Balance>() {
            @Override
            public int compare(Balance o1, Balance o2) {
                if (chain.equals(BNB_MAIN) || chain.equals(BNB_TEST)) {
                    if(o1.symbol.equals(TOKEN_BNB)) return -1;
                    if(o2.symbol.equals(TOKEN_BNB)) return 1;

                } else if (chain.equals(KAVA_MAIN) || chain.equals(KAVA_TEST)) {
                    if(o1.symbol.equals(TOKEN_KAVA)) return -1;
                    if(o2.symbol.equals(TOKEN_KAVA)) return 1;

                } else if (chain.equals(IOV_MAIN)) {
                    if(o1.symbol.equals(TOKEN_IOV)) return -1;
                    if(o2.symbol.equals(TOKEN_IOV)) return 1;

                } else if (chain.equals(BAND_MAIN)) {
                    if(o1.symbol.equals(TOKEN_BAND)) return -1;
                    if(o2.symbol.equals(TOKEN_BAND)) return 1;

                } else if (chain.equals(IOV_TEST)) {
                    if(o1.symbol.equals(TOKEN_IOV_TEST)) return -1;
                    if(o2.symbol.equals(TOKEN_IOV_TEST)) return 1;

                } else if (chain.equals(OKEX_MAIN) || chain.equals(OK_TEST)) {
                    if(o1.symbol.equals(TOKEN_OK)) return -1;
                    if(o2.symbol.equals(TOKEN_OK)) return 1;
                    if(o1.symbol.equals(TOKEN_OK_OKB)) return -1;
                    if(o2.symbol.equals(TOKEN_OK_OKB)) return 1;
                    if(o1.symbol.equals("okb-c4d")) return -1;
                    if(o2.symbol.equals("okb-c4d")) return 1;

                } else if (chain.equals(CERTIK_MAIN) || chain.equals(CERTIK_TEST)) {
                    if(o1.symbol.equals(TOKEN_CERTIK)) return -1;
                    if(o2.symbol.equals(TOKEN_CERTIK)) return 1;

                } else if (chain.equals(SIF_MAIN)) {
                    if(o1.symbol.equals(TOKEN_SIF)) return -1;
                    if(o2.symbol.equals(TOKEN_SIF)) return 1;

                } else if (chain.equals(KI_MAIN)) {
                    if(o1.symbol.equals(TOKEN_KI)) return -1;
                    if(o2.symbol.equals(TOKEN_KI)) return 1;

                }
                return o1.symbol.compareTo(o2.symbol);
            }
        });
    }

    public static void onSortingTokenByNameV1(ArrayList<Coin> coin, final BaseChain chain) {
        Collections.sort(coin, new Comparator<Coin>() {
            @Override
            public int compare(Coin o1, Coin o2) {
                if (chain.equals(COSMOS_MAIN)) {
                    if(o1.denom.equals(TOKEN_ATOM)) return -1;
                    if(o2.denom.equals(TOKEN_ATOM)) return 1;

                } else if (chain.equals(IRIS_MAIN)) {
                    if(o1.denom.equals(TOKEN_IRIS)) return -1;
                    if(o2.denom.equals(TOKEN_IRIS)) return 1;

                } else if (chain.equals(AKASH_MAIN)) {
                    if(o1.denom.equals(TOKEN_AKASH)) return -1;
                    if(o2.denom.equals(TOKEN_AKASH)) return 1;

                } else if (chain.equals(PERSIS_MAIN)) {
                    if(o1.denom.equals(TOKEN_XPRT)) return -1;
                    if(o2.denom.equals(TOKEN_XPRT)) return 1;

                } else if (chain.equals(CRYPTO_MAIN)) {
                    if(o1.denom.equals(TOKEN_XPRT)) return -1;
                    if(o2.denom.equals(TOKEN_XPRT)) return 1;

                }

                else if (chain.equals(COSMOS_TEST)) {
                    if(o1.denom.equals(TOKEN_COSMOS_TEST)) return -1;
                    if(o2.denom.equals(TOKEN_COSMOS_TEST)) return 1;

                } else if (chain.equals(IRIS_TEST)) {
                    if(o1.denom.equals(TOKEN_IRIS_TEST)) return -1;
                    if(o2.denom.equals(TOKEN_IRIS_TEST)) return 1;

                }
                return o1.denom.compareTo(o2.denom);
            }
        });
    }

    public static void onSortingTokenByValue(ArrayList<Balance> balances, final BaseChain chain) {
        Collections.sort(balances, new Comparator<Balance>() {
            @Override
            public int compare(Balance o1, Balance o2) {
                if (chain.equals(BNB_MAIN) || chain.equals(BNB_TEST)) {
                    if(o1.symbol.equals(TOKEN_BNB)) return -1;
                    if(o2.symbol.equals(TOKEN_BNB)) return 1;

                } else if (chain.equals(KAVA_MAIN) || chain.equals(KAVA_TEST)) {
                    if(o1.symbol.equals(TOKEN_KAVA)) return -1;
                    if(o2.symbol.equals(TOKEN_KAVA)) return 1;

                } else if (chain.equals(IOV_MAIN)) {
                    if(o1.symbol.equals(TOKEN_IOV)) return -1;
                    if(o2.symbol.equals(TOKEN_IOV)) return 1;

                } else if (chain.equals(BAND_MAIN)) {
                    if(o1.symbol.equals(TOKEN_BAND)) return -1;
                    if(o2.symbol.equals(TOKEN_BAND)) return 1;

                } else if (chain.equals(IOV_TEST)) {
                    if(o1.symbol.equals(TOKEN_IOV_TEST)) return -1;
                    if(o2.symbol.equals(TOKEN_IOV_TEST)) return 1;

                } else if (chain.equals(OKEX_MAIN) || chain.equals(OK_TEST)) {
                    if(o1.symbol.equals(TOKEN_OK)) return -1;
                    if(o2.symbol.equals(TOKEN_OK)) return 1;
                    if(o1.symbol.equals(TOKEN_OK_OKB)) return -1;
                    if(o2.symbol.equals(TOKEN_OK_OKB)) return 1;
                    if(o1.symbol.equals("okb-c4d")) return -1;
                    if(o2.symbol.equals("okb-c4d")) return 1;

                } else if (chain.equals(CERTIK_MAIN) || chain.equals(CERTIK_TEST)) {
                    if(o1.symbol.equals(TOKEN_CERTIK)) return -1;
                    if(o2.symbol.equals(TOKEN_CERTIK)) return 1;

                } else if (chain.equals(SIF_MAIN)) {
                    if(o1.symbol.equals(TOKEN_SIF)) return -1;
                    if(o2.symbol.equals(TOKEN_SIF)) return 1;

                } else if (chain.equals(KI_MAIN)) {
                    if(o1.symbol.equals(TOKEN_KI)) return -1;
                    if(o2.symbol.equals(TOKEN_KI)) return 1;

                }
                return o1.symbol.compareTo(o2.symbol);
            }
        });
    }

    public static void onSortingTokenByValueV1(ArrayList<Coin> coin, final BaseChain chain) {
        Collections.sort(coin, new Comparator<Coin>() {
            @Override
            public int compare(Coin o1, Coin o2) {
                if (chain.equals(COSMOS_MAIN)) {
                    if(o1.denom.equals(TOKEN_ATOM)) return -1;
                    if(o2.denom.equals(TOKEN_ATOM)) return 1;

                } else if (chain.equals(IRIS_MAIN)) {
                    if(o1.denom.equals(TOKEN_IRIS)) return -1;
                    if(o2.denom.equals(TOKEN_IRIS)) return 1;

                } else if (chain.equals(AKASH_MAIN)) {
                    if(o1.denom.equals(TOKEN_AKASH)) return -1;
                    if(o2.denom.equals(TOKEN_AKASH)) return 1;

                } else if (chain.equals(PERSIS_MAIN)) {
                    if(o1.denom.equals(TOKEN_XPRT)) return -1;
                    if(o2.denom.equals(TOKEN_XPRT)) return 1;

                } else if (chain.equals(CRYPTO_MAIN)) {
                    if(o1.denom.equals(TOKEN_XPRT)) return -1;
                    if(o2.denom.equals(TOKEN_XPRT)) return 1;

                }

                else if (chain.equals(COSMOS_TEST)) {
                    if(o1.denom.equals(TOKEN_COSMOS_TEST)) return -1;
                    if(o2.denom.equals(TOKEN_COSMOS_TEST)) return 1;

                } else if (chain.equals(IRIS_TEST)) {
                    if(o1.denom.equals(TOKEN_IRIS_TEST)) return -1;
                    if(o2.denom.equals(TOKEN_IRIS_TEST)) return 1;

                }
                return o1.denom.compareTo(o2.denom);
            }
        });
    }

    public static void onSortingCoins(ArrayList<Coin> coins, BaseChain chain) {
        Collections.sort(coins, new Comparator<Coin>() {
            @Override
            public int compare(Coin o1, Coin o2) {
                if(o1.denom.equals(WDp.mainDenom(chain))) return -1;
                if(o2.denom.equals(WDp.mainDenom(chain))) return 1;
                else return 0;
            }
        });
    }

    public static ArrayList<UnbondingInfo.DpEntry> onSortUnbondingsRecent(Context c, ArrayList<UnbondingInfo> unbondingInfos) {
        ArrayList<UnbondingInfo.DpEntry> result = new ArrayList<>();
        for (UnbondingInfo unbondingInfo: unbondingInfos) {
            for (UnbondingInfo.Entry entry: unbondingInfo.entries) {
                result.add(new UnbondingInfo.DpEntry(unbondingInfo.validator_address, entry.completion_time, entry.balance));
            }
        }

        Collections.sort(result, new Comparator<UnbondingInfo.DpEntry>() {
            @Override
            public int compare(UnbondingInfo.DpEntry o1, UnbondingInfo.DpEntry o2) {
                return WDp.dateToLong(c, o1.completion_time) < WDp.dateToLong(c, o2.completion_time) ?  -1 : 1;
            }
        });
        return result;
    }


    public static void onSortingAccount(ArrayList<Account> accounts) {
        Collections.sort(accounts, new Comparator<Account>() {
            @Override
            public int compare(Account o1, Account o2) {
                if (o1.sortOrder > o2.sortOrder) return 1;
                else if (o1.sortOrder < o2.sortOrder) return -1;
                else return 0;

            }
        });
    }

    public static String getCGCId(BaseChain chain) {
        if (chain.equals(COSMOS_MAIN) || chain.equals(COSMOS_TEST)) {
            return BaseConstant.CGC_ATOM;

        } else if (chain.equals(IRIS_MAIN) || chain.equals(IRIS_TEST)) {
            return BaseConstant.CGC_IRIS;

        } else if (chain.equals(BNB_MAIN) || chain.equals(BNB_TEST)) {
            return BaseConstant.CGC_BNB;

        } else if (chain.equals(KAVA_MAIN) || chain.equals(KAVA_TEST)) {
            return BaseConstant.CGC_KAVA;

        } else if (chain.equals(BAND_MAIN)) {
            return BaseConstant.CGC_BAND;

        } else if (chain.equals(IOV_MAIN) || chain.equals(IOV_TEST)) {
            return BaseConstant.CGC_IOV;

        } else if (chain.equals(CERTIK_MAIN) || chain.equals(CERTIK_TEST)) {
            return BaseConstant.CGC_CERTIK;

        } else if (chain.equals(AKASH_MAIN)) {
            return BaseConstant.CGC_AKASH;

        } else if (chain.equals(SECRET_MAIN)) {
            return CGC_SECRET;

        } else if (chain.equals(OKEX_MAIN) || chain.equals(OK_TEST)) {
            return CGC_OKEX;

        } else if (chain.equals(SENTINEL_MAIN)) {
            return CGC_SENTINEL;

        } else if (chain.equals(PERSIS_MAIN)) {
            return CGC_PERSISTENCE;

        } else if (chain.equals(FETCHAI_MAIN)) {
            return CGC_FETCH;

        } else if (chain.equals(CRYPTO_MAIN)) {
            return CGC_CRYPTO;

        } else if (chain.equals(SIF_MAIN)) {
            return CGC_SIF;

        } else if (chain.equals(KI_MAIN)) {
            return CGC_KI;

        }
        return BaseConstant.CGC_ATOM;
    }

    public static int getMaxMemoSize(BaseChain chain) {
        if (chain.equals(BNB_MAIN) || chain.equals(BNB_TEST)) {
            return BaseConstant.MEMO_BNB;
        }
        return BaseConstant.MEMO_ATOM;
    }

    public static int getCharSize(String memo) {
        int result = 1000;
        try {
            result = memo.trim().getBytes("UTF-8").length;
        } catch (Exception e) { }

        return result;
    }

    public static String marketPrice(BaseChain basechain, BaseData basedata) {
        String result = "usdt";
        if (basechain.equals(COSMOS_MAIN) || basechain.equals(COSMOS_TEST)) {
            result = result + ",uatom";
            for (Coin coin: basedata.mGrpcBalance) {
                if (coin.denom != WDp.mainDenom(basechain)) {
                }
            }

        } else if (basechain.equals(IRIS_MAIN) || basechain.equals(IRIS_TEST)) {
            result = result + ",uiris";
            for (Coin coin: basedata.mGrpcBalance) {
                if (coin.denom != WDp.mainDenom(basechain)) {
                }
            }

        } else if (basechain.equals(AKASH_MAIN)) {
            result = result + ",uakt";
            for (Coin coin: basedata.mGrpcBalance) {
                if (coin.denom != WDp.mainDenom(basechain)) {
                }
            }

        } else if (basechain.equals(PERSIS_MAIN)) {
            result = result + ",uxprt";
            for (Coin coin: basedata.mGrpcBalance) {
                if (coin.denom != WDp.mainDenom(basechain)) {
                }
            }

        } else if (basechain.equals(CRYPTO_MAIN)) {
            result = result + ",basecro";
            for (Coin coin: basedata.mGrpcBalance) {
                if (coin.denom != WDp.mainDenom(basechain)) {
                }
            }

        }

        else if (basechain.equals(BNB_MAIN) || basechain.equals(BNB_TEST)) {
            result = result + ",bnb";

        } else if (basechain.equals(KAVA_MAIN) || basechain.equals(KAVA_TEST)) {
            result = result + ",ukava,hard";

        } else if (basechain.equals(OKEX_MAIN) || basechain.equals(OK_TEST)) {
            result = result + ",okb,okt";

        } else if (basechain.equals(BAND_MAIN)) {
            result = result + ",uband";

        } else if (basechain.equals(IOV_MAIN) || basechain.equals(IOV_TEST)) {
            result = result + ",uiov";

        } else if (basechain.equals(CERTIK_MAIN) || basechain.equals(CERTIK_TEST)) {
            result = result + ",uctk";

        } else if (basechain.equals(SECRET_MAIN)) {
            result = result + ",uscrt";

        } else if (basechain.equals(SENTINEL_MAIN)) {
            result = result + ",udvpn";

        } else if (basechain.equals(FETCHAI_MAIN)) {
            result = result + ",afet";

        } else if (basechain.equals(SIF_MAIN)) {
            result = result + ",rowan";
            for (Balance balance: basedata.mBalances) {
                if (balance.symbol != WDp.mainDenom(basechain) && balance.symbol.startsWith("c")) {
                    result = result + "," + balance.symbol.substring(1);
                }
            }

        } else if (basechain.equals(KI_MAIN)) {
            result = result + ",uxki";
        }
        return result;
    }

//    public static int getKavaCoinDecimal(CdpParam params, Balance balance) {
//        int result = 0;
//        if (params != null) {
//            if (params.debt_param != null) {
//                return Integer.parseInt(params.debt_param.conversion_factor);
//            }
//            if (params.collateral_params != null) {
//                for (CollateralParam collateralParams: params.collateral_params) {
//                    if (collateralParams.denom.equals(balance.symbol)) {
//                        return Integer.parseInt(collateralParams.conversion_factor);
//                    }
//                }
//            }
//        }
//        return result;
//    }

    public static int getKavaCoinDecimal(Coin coin) {
        if (coin.denom.equalsIgnoreCase(TOKEN_KAVA)) {
            return 6;
        } else if (coin.denom.equalsIgnoreCase(TOKEN_HARD)) {
            return 6;
        } else if (coin.denom.equalsIgnoreCase("xrpb") || coin.denom.equalsIgnoreCase("xrbp")) {
            return 8;
        } else if (coin.denom.equalsIgnoreCase("btc")) {
            return 8;
        } else if (coin.denom.equalsIgnoreCase("usdx")) {
            return 6;
        } else if (coin.denom.equalsIgnoreCase("bnb")) {
            return 8;
        } else if (coin.denom.equalsIgnoreCase("btcb") || coin.denom.equalsIgnoreCase("hbtc")) {
            return 8;
        } else if (coin.denom.equalsIgnoreCase("busd")) {
            return 8;
        }
        return 0;

    }

    public static int getKavaCoinDecimal(String denom) {
        if (denom.equalsIgnoreCase(TOKEN_KAVA)) {
            return 6;
        } else if (denom.equalsIgnoreCase(TOKEN_HARD)) {
            return 6;
        } else if (denom.equalsIgnoreCase("xrpb") || denom.equalsIgnoreCase("xrbp")) {
            return 8;
        } else if (denom.equalsIgnoreCase("btc")) {
            return 8;
        } else if (denom.equalsIgnoreCase("usdx")) {
            return 6;
        } else if (denom.equalsIgnoreCase("bnb")) {
            return 8;
        } else if (denom.equalsIgnoreCase("btcb") || denom.equalsIgnoreCase("hbtc")) {
            return 8;
        } else if (denom.equalsIgnoreCase("busd")) {
            return 8;
        }
        return 100;
    }

    public static int getSifCoinDecimal(String denom) {
        if (denom.equalsIgnoreCase(TOKEN_SIF)) { return 18; }
        else if (denom.equalsIgnoreCase("cusdt")) { return 6; }
        else if (denom.equalsIgnoreCase("cusdc")) { return 6; }
        else if (denom.equalsIgnoreCase("csrm")) { return 6; }
        else if (denom.equalsIgnoreCase("cwscrt")) { return 6; }
        else if (denom.equalsIgnoreCase("ccro")) { return 8; }
        else if (denom.equalsIgnoreCase("cwbtc")) { return 8; }
        return 18;
    }

    public static BnbToken getBnbMainToken(ArrayList<BnbToken> all) {
        if (all == null) return null;
        for (BnbToken token:all) {
            if (token.original_symbol.equals(TOKEN_BNB)) {
                return token;
            }
        }
        return null;
    }

    public static BigDecimal getBnbConvertAmount(BaseData baseData, String denom, BigDecimal amount) {
        BigDecimal result = BigDecimal.ZERO;
        for (BnbTicker ticker: baseData.mBnbTickers) {
            if (ticker.symbol.equals(getBnbTicSymbol(denom))) {
                if (isBnbBaseMarketToken(denom)) {
                    return amount.divide(new BigDecimal(ticker.lastPrice), 8, RoundingMode.DOWN);
                } else {
                    return amount.multiply(new BigDecimal(ticker.lastPrice)).setScale(8, RoundingMode.DOWN);
                }
            }
        }
        return result;
    }

    public static boolean isBnbBaseMarketToken(String symbol) {
        switch (symbol) {
            case "USDT.B-B7C":
                return true;
            case "ETH.B-261":
                return true;
            case "BTC.B-918":
                return true;


            case "USDSB-1AC":
                return true;
            case "THKDB-888":
                return true;
            case "TUSDB-888":
                return true;
            case "BTCB-1DE":
                return true;

            case "ETH-1C9":
                return true;
            case "IDRTB-178":
                return true;
            case "BUSD-BD1":
                return true;
            case "TAUDB-888":
                return true;


        }
        return false;
    }

    public static String getBnbTicSymbol(String symbol) {
        if (isBnbBaseMarketToken(symbol)) {
            return TOKEN_BNB + "_" + symbol;

        } else {
            return symbol + "_"+TOKEN_BNB;
        }
    }

    public static int getVoterTypeCnt(ArrayList<Vote> votes, String option) {
        int result = 0;
        if (votes == null) {
            return result;
        }
        for (Vote v:votes) {
            if (v.option.equals(option)) {
                result = result + 1;
            }
        }
        return result;
    }

    public static int getVoterTypeCnt_gRPC(ArrayList<Gov.Vote> votes, Gov.VoteOption option) {
        int result = 0;
        if (votes == null) {
            return result;
        }
        for (Gov.Vote v:votes) {
            if (v.getOption().equals(option)) {
                result = result + 1;
            }
        }
        return result;
    }

    public static String getMonikerName(String opAddress, ArrayList<Validator> validators, boolean bracket) {
        String result = "";
        for (Validator val:validators) {
            if (val.operator_address.equals(opAddress)) {
                if (bracket) {
                    return  "(" + val.description.moniker + ")";
                } else {
                    return  val.description.moniker;
                }

            }
        }
        return result;
    }


    public static byte[] long2Bytes(long x){
        ByteBuffer buffer = ByteBuffer.allocate(8);
        buffer.putLong(x);
        return buffer.array();
    }

    public static boolean isValidStarName(String starname) {
        boolean result = false;
        String regex = "[0-9a-z.-]{0,64}+\\*[a-z0-9.-]{3,16}";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(starname);
        if (m.matches()) {
            result = true;
        }
        return result;
    }

    public static boolean isValidDomain(String starname) {
        boolean result = false;
        String regex = "[a-z0-9]{4,32}";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(starname);
        if (m.matches()) {
            result = true;
        }
        return result;
    }

    public static boolean isValidAccount(String starname) {
        boolean result = false;
        String regex = "[a-z0-9.-]{1,63}";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(starname);
        if (m.matches()) {
            result = true;
        }
        return result;
    }


    public static String BITCOINCASH    = "asset:bch";
    public static String BITCOIN        = "asset:btc";
    public static String LITECOIN       = "asset:ltc";
    public static String BINANCE        = "asset:bnb";
    public static String LUNA           = "asset:luna";
    public static String COSMOS         = "asset:atom";
    public static String EMONEY         = "asset:ngm";
    public static String IRIS           = "asset:iris";
    public static String KAVA           = "asset:kava";
    public static String ETHEREUM       = "asset:eth";
    public static String STARNAME       = "asset:iov";
    public static String BAND           = "asset:band";
    public static String TEZOS          = "asset:xtz";
    public static String LISK           = "asset:lsk";


    public static ArrayList<StarNameResource> getAllStarnameResources() {
        ArrayList<StarNameResource> result = new ArrayList();
        result.add(new StarNameResource(STARNAME));
        result.add(new StarNameResource(COSMOS));
        result.add(new StarNameResource(BITCOIN));
        result.add(new StarNameResource(ETHEREUM));
        result.add(new StarNameResource(BINANCE));
        result.add(new StarNameResource(IRIS));
        result.add(new StarNameResource(KAVA));
        result.add(new StarNameResource(BAND));
        result.add(new StarNameResource(BITCOINCASH));
        result.add(new StarNameResource(LITECOIN));
        result.add(new StarNameResource(EMONEY));
        result.add(new StarNameResource(TEZOS));
        result.add(new StarNameResource(LISK));
        result.add(new StarNameResource(LUNA));
        return result;

    }

    public static Drawable getStarNameChainImg(Context c, StarNameResource res) {
        if (res.uri.equals(BITCOINCASH)) {
            return c.getResources().getDrawable(R.drawable.bcash_chain_img);

        } else if (res.uri.equals(BITCOIN)) {
            return c.getResources().getDrawable(R.drawable.bitcoin_chain_img);

        } else if (res.uri.equals(LITECOIN)) {
            return c.getResources().getDrawable(R.drawable.lite_chain_img);

        } else if (res.uri.equals(BINANCE)) {
            return c.getResources().getDrawable(R.drawable.binance_ch_img);

        } else if (res.uri.equals(LUNA)) {
            return c.getResources().getDrawable(R.drawable.terra_chain_img);

        } else if (res.uri.equals(COSMOS)) {
            return c.getResources().getDrawable(R.drawable.cosmos_wh_main);

        } else if (res.uri.equals(EMONEY)) {
            return c.getResources().getDrawable(R.drawable.emoney_chain_img);

        } else if (res.uri.equals(IRIS)) {
            return c.getResources().getDrawable(R.drawable.iris_wh);

        } else if (res.uri.equals(KAVA)) {
            return c.getResources().getDrawable(R.drawable.kava_img);

        } else if (res.uri.equals(ETHEREUM)) {
            return c.getResources().getDrawable(R.drawable.ethereum_chain_img);

        } else if (res.uri.equals(STARNAME)) {
            return c.getResources().getDrawable(R.drawable.iov_chain_img);

        } else if (res.uri.equals(BAND)) {
            return c.getResources().getDrawable(R.drawable.band_chain_img);

        } else if (res.uri.equals(TEZOS)) {
            return c.getResources().getDrawable(R.drawable.tezos_chain_img);

        } else if (res.uri.equals(LISK)) {
            return c.getResources().getDrawable(R.drawable.lisk_chain_img);

        }
        return c.getResources().getDrawable(R.drawable.default_chain_img);
    }

    public static String getStarNameChainName(StarNameResource res) {
        if (res.uri.equals(BITCOINCASH)) {
            return "Bitcoin Cash";

        } else if (res.uri.equals(BITCOIN)) {
            return "Bitcoin";

        } else if (res.uri.equals(LITECOIN)) {
            return "Litecoin";

        } else if (res.uri.equals(BINANCE)) {
            return "Binance";

        } else if (res.uri.equals(LUNA)) {
            return "Terra";

        } else if (res.uri.equals(COSMOS)) {
            return "Cosmos";

        } else if (res.uri.equals(EMONEY)) {
            return "E-Money";

        } else if (res.uri.equals(IRIS)) {
            return "Iris";

        } else if (res.uri.equals(KAVA)) {
            return "Kava";

        } else if (res.uri.equals(ETHEREUM)) {
            return "Ethereum";

        } else if (res.uri.equals(STARNAME)) {
            return "Starname";

        } else if (res.uri.equals(BAND)) {
            return "Band";

        } else if (res.uri.equals(TEZOS)) {
            return "Tezos";

        } else if (res.uri.equals(LISK)) {
            return "Lisk";

        }
        return res.uri;

    }

    public static BaseChain getBaseChainWithUri(String uri) {
        if (uri.equals(COSMOS)) {
            return COSMOS_MAIN;
        } else if (uri.equals(IRIS)) {
            return IRIS_MAIN;
        } else if (uri.equals(BINANCE)) {
            return BNB_MAIN;
        } else if (uri.equals(STARNAME)) {
            return IOV_MAIN;
        } else if (uri.equals(KAVA)) {
            return KAVA_MAIN;
        } else if (uri.equals(BAND)) {
            return BAND_MAIN;
        }
        return null;
    }

    public static ExportStarName getExportResource(ArrayList<Account> accounts) {
        ExportStarName result = new ExportStarName();
        result.type = "starname";
        for (Account account:accounts) {
            ExportStarName.ExportResource resource = new ExportStarName.ExportResource();
            if (BaseChain.getChain(account.baseChain).equals(COSMOS_MAIN)) {
                resource.ticker = "atom";
                resource.address = account.address;
                result.addresses.add(resource);

            } else if (BaseChain.getChain(account.baseChain).equals(IRIS_MAIN)) {
                resource.ticker = "iris";
                resource.address = account.address;
                result.addresses.add(resource);

            } else if (BaseChain.getChain(account.baseChain).equals(KAVA_MAIN)) {
                resource.ticker = "kava";
                resource.address = account.address;
                result.addresses.add(resource);

            } else if (BaseChain.getChain(account.baseChain).equals(BNB_MAIN)) {
                resource.ticker = "bnb";
                resource.address = account.address;
                result.addresses.add(resource);

            } else if (BaseChain.getChain(account.baseChain).equals(IOV_MAIN)) {
                resource.ticker = "iov";
                resource.address = account.address;
                result.addresses.add(resource);

            } else if (BaseChain.getChain(account.baseChain).equals(BAND_MAIN)) {
                resource.ticker = "band";
                resource.address = account.address;
                result.addresses.add(resource);

            }
        }
        return result;
    }

    public static BigDecimal getCBlockTime(BaseChain chain) {
        if (chain.equals(COSMOS_MAIN) || chain.equals(COSMOS_TEST)) {
            return BLOCK_TIME_COSMOS;

        } else if (chain.equals(IRIS_MAIN) || chain.equals(IRIS_TEST)) {
            return BLOCK_TIME_IRIS;

        } else if (chain.equals(IOV_MAIN)) {
            return BLOCK_TIME_IOV;

        } else if (chain.equals(KAVA_MAIN)) {
            return BLOCK_TIME_KAVA;

        } else if (chain.equals(BAND_MAIN)) {
            return BLOCK_TIME_BAND;

        } else if (chain.equals(CERTIK_MAIN)) {
            return BLOCK_TIME_CERTIK;

        } else if (chain.equals(SECRET_MAIN)) {
            return BLOCK_TIME_SECRET;

        } else if (chain.equals(AKASH_MAIN)) {
            return BLOCK_TIME_AKASH;

        } else if (chain.equals(PERSIS_MAIN)) {
            return BLOCK_TIME_PERSISTENCE;

        } else if (chain.equals(FETCHAI_MAIN)) {
            return BLOCK_TIME_FETCH;

        } else if (chain.equals(CRYPTO_MAIN)) {
            return BLOCK_TIME_CRYPTO;

        } else if (chain.equals(SIF_MAIN)) {
            return BLOCK_TIME_SIF;

        } else if (chain.equals(KI_MAIN)) {
            return BLOCK_TIME_KI;

        }
        return new BigDecimal("6");
    }

    public static Intent getGuide1Intent(BaseChain chain) {
        if (chain.equals(COSMOS_MAIN)) {
            if (Locale.getDefault().getLanguage().toLowerCase().equals("ko")) {
                return new Intent(Intent.ACTION_VIEW , Uri.parse("https://www.cosmostation.io/files/cosmostation_guide_app_ko.pdf"));
            } else {
                return new Intent(Intent.ACTION_VIEW , Uri.parse("https://www.cosmostation.io/files/cosmostation_guide_app_en.pdf"));
            }

        } else if (chain.equals(IRIS_MAIN)) {
            return new Intent(Intent.ACTION_VIEW , Uri.parse("https://www.irisnet.org/"));

        } else if (chain.equals(BNB_MAIN) || chain.equals(BNB_TEST)) {
            return new Intent(Intent.ACTION_VIEW , Uri.parse("https://www.binance.org"));

        } else if (chain.equals(KAVA_MAIN) || chain.equals(KAVA_TEST)) {
            return new Intent(Intent.ACTION_VIEW , Uri.parse("https://www.kava.io/registration/"));

        } else if (chain.equals(IOV_MAIN) || chain.equals(IOV_TEST)) {
            return new Intent(Intent.ACTION_VIEW , Uri.parse("https://www.starname.network/"));

        } else if (chain.equals(BAND_MAIN)) {
            return new Intent(Intent.ACTION_VIEW , Uri.parse("https://bandprotocol.com/"));

        } else if (chain.equals(OKEX_MAIN) || chain.equals(OK_TEST)) {
            return new Intent(Intent.ACTION_VIEW , Uri.parse("https://www.okex.com/"));

        } else if (chain.equals(CERTIK_MAIN) || chain.equals(CERTIK_TEST)) {
            return new Intent(Intent.ACTION_VIEW , Uri.parse("https://www.certik.foundation/"));

        } else if (chain.equals(AKASH_MAIN)) {
            return new Intent(Intent.ACTION_VIEW , Uri.parse("https://akash.network/"));

        } else if (chain.equals(SECRET_MAIN)) {
            return new Intent(Intent.ACTION_VIEW , Uri.parse("https://scrt.network"));

        } else if (chain.equals(PERSIS_MAIN)) {
            return new Intent(Intent.ACTION_VIEW , Uri.parse("https://persistence.one/"));

        } else if (chain.equals(SENTINEL_MAIN)) {
            return new Intent(Intent.ACTION_VIEW , Uri.parse("https://sentinel.co/"));

        } else if (chain.equals(FETCHAI_MAIN)) {
            return new Intent(Intent.ACTION_VIEW , Uri.parse("https://fetch.ai/"));

        } else if (chain.equals(CRYPTO_MAIN)) {
            return new Intent(Intent.ACTION_VIEW , Uri.parse("https://crypto.org/"));

        } else if (chain.equals(SIF_MAIN)) {
            return new Intent(Intent.ACTION_VIEW , Uri.parse("https://sifchain.finance/"));

        } else if (chain.equals(KI_MAIN)) {
            return new Intent(Intent.ACTION_VIEW , Uri.parse("https://foundation.ki/en"));

        }
        return null;
    }

    public static Intent getGuide2Intent(BaseChain chain) {
        if (chain.equals(COSMOS_MAIN)) {
            if (Locale.getDefault().getLanguage().toLowerCase().equals("ko")) {
                return new Intent(Intent.ACTION_VIEW , Uri.parse("https://guide.cosmostation.io/app_wallet_ko.html"));
            } else {
                return new Intent(Intent.ACTION_VIEW , Uri.parse("https://guide.cosmostation.io/app_wallet_en.html"));
            }

        } else if (chain.equals(IRIS_MAIN)) {
            return new Intent(Intent.ACTION_VIEW , Uri.parse("https://medium.com/irisnet-blog"));

        } else if (chain.equals(BNB_MAIN) || chain.equals(BNB_TEST)) {
            return new Intent(Intent.ACTION_VIEW , Uri.parse("https://medium.com/@binance"));

        } else if (chain.equals(KAVA_MAIN) || chain.equals(KAVA_TEST)) {
            return new Intent(Intent.ACTION_VIEW , Uri.parse("https://medium.com/kava-labs"));

        } else if (chain.equals(IOV_MAIN) || chain.equals(IOV_TEST)) {
            return new Intent(Intent.ACTION_VIEW , Uri.parse("https://medium.com/iov-internet-of-values"));

        } else if (chain.equals(BAND_MAIN)) {
            return new Intent(Intent.ACTION_VIEW , Uri.parse("https://medium.com/bandprotocol"));

        } else if (chain.equals(OKEX_MAIN) || chain.equals(OK_TEST)) {
            return new Intent(Intent.ACTION_VIEW , Uri.parse("https://www.okex.com/community"));

        } else if (chain.equals(CERTIK_MAIN) || chain.equals(CERTIK_TEST)) {
            return new Intent(Intent.ACTION_VIEW , Uri.parse("https://www.certik.foundation/blog"));

        } else if (chain.equals(AKASH_MAIN)) {
            return new Intent(Intent.ACTION_VIEW , Uri.parse("https://akash.network/"));

        } else if (chain.equals(SECRET_MAIN)) {
            return new Intent(Intent.ACTION_VIEW , Uri.parse("https://blog.scrt.network"));

        } else if (chain.equals(PERSIS_MAIN)) {
            return new Intent(Intent.ACTION_VIEW , Uri.parse("https://medium.com/persistence-blog"));

        } else if (chain.equals(SENTINEL_MAIN)) {
            return new Intent(Intent.ACTION_VIEW , Uri.parse("https://medium.com/sentinel"));

        } else if (chain.equals(FETCHAI_MAIN)) {
            return new Intent(Intent.ACTION_VIEW , Uri.parse("https://fetch.ai/blog/"));

        } else if (chain.equals(CRYPTO_MAIN)) {
            return new Intent(Intent.ACTION_VIEW , Uri.parse("https://crypto.org/community/"));

        } else if (chain.equals(SIF_MAIN)) {
            return new Intent(Intent.ACTION_VIEW , Uri.parse("https://medium.com/sifchain-finance"));

        } else if (chain.equals(KI_MAIN)) {
            return new Intent(Intent.ACTION_VIEW , Uri.parse("https://medium.com/ki-foundation"));

        }
        return null;
    }

//    public static BigDecimal getEstimateGasAmount(Context c, BaseChain basechain, int txType) {
//        return getEstimateGasAmount(c, basechain, txType, 0);
//    }

    public static BigDecimal getEstimateGasAmount(Context c, BaseChain basechain, int txType,  int valCnt) {
        BigDecimal result = BigDecimal.ZERO;
        if (basechain.equals(COSMOS_MAIN) || basechain.equals(AKASH_MAIN) || basechain.equals(PERSIS_MAIN) ||
                basechain.equals(CRYPTO_MAIN) || basechain.equals(COSMOS_TEST)) {
            if (txType == CONST_PW_TX_SIMPLE_SEND) {
                return new BigDecimal(V1_GAS_AMOUNT_LOW);
            } else if (txType == CONST_PW_TX_SIMPLE_DELEGATE) {
                return new BigDecimal(V1_GAS_AMOUNT_MID);
            } else if (txType == CONST_PW_TX_SIMPLE_UNDELEGATE) {
                return new BigDecimal(V1_GAS_AMOUNT_MID);
            } else if (txType == CONST_PW_TX_SIMPLE_REDELEGATE) {
                return new BigDecimal(V1_GAS_AMOUNT_HIGH);
            } else if (txType == CONST_PW_TX_REINVEST) {
                return new BigDecimal(V1_GAS_AMOUNT_HIGH);
            } else if (txType == CONST_PW_TX_SIMPLE_REWARD) {
                ArrayList<String> rewardGasFees = new ArrayList<String>(Arrays.asList(c.getResources().getStringArray(R.array.gas_multi_reward)));
                return new BigDecimal(rewardGasFees.get(valCnt - 1));
            } else if (txType == CONST_PW_TX_SIMPLE_CHANGE_REWARD_ADDRESS) {
                return new BigDecimal(V1_GAS_AMOUNT_LOW);
            } else if (txType == CONST_PW_TX_VOTE) {
                return new BigDecimal(V1_GAS_AMOUNT_LOW);
            }

        } else if (basechain.equals(IRIS_MAIN) || basechain.equals(IRIS_TEST)) {
            if (txType == CONST_PW_TX_SIMPLE_SEND) {
                return new BigDecimal(V1_GAS_AMOUNT_LOW);
            } else if (txType == CONST_PW_TX_SIMPLE_DELEGATE) {
                return new BigDecimal(V1_GAS_AMOUNT_MID);
            } else if (txType == CONST_PW_TX_SIMPLE_UNDELEGATE) {
                return new BigDecimal(V1_GAS_AMOUNT_MID);
            } else if (txType == CONST_PW_TX_SIMPLE_REDELEGATE) {
                return new BigDecimal(V1_GAS_AMOUNT_HIGH);
            } else if (txType == CONST_PW_TX_REINVEST) {
                return new BigDecimal(V1_GAS_AMOUNT_HIGH);
            } else if (txType == CONST_PW_TX_SIMPLE_REWARD) {
                ArrayList<String> rewardGasFees = new ArrayList<String>(Arrays.asList(c.getResources().getStringArray(R.array.gas_multi_reward)));
                return new BigDecimal(rewardGasFees.get(valCnt - 1));
            } else if (txType == CONST_PW_TX_SIMPLE_CHANGE_REWARD_ADDRESS) {
                return new BigDecimal(V1_GAS_AMOUNT_LOW);
            } else if (txType == CONST_PW_TX_VOTE) {
                return new BigDecimal(V1_GAS_AMOUNT_LOW);
            }

        }

        else if (basechain.equals(KAVA_MAIN) || basechain.equals(KAVA_TEST)) {
            if (txType == CONST_PW_TX_SIMPLE_SEND) {
                return new BigDecimal(KAVA_GAS_AMOUNT_SEND);
            } else if (txType == CONST_PW_TX_SIMPLE_DELEGATE) {
                return new BigDecimal(KAVA_GAS_AMOUNT_STAKE);
            } else if (txType == CONST_PW_TX_SIMPLE_UNDELEGATE) {
                return new BigDecimal(KAVA_GAS_AMOUNT_STAKE);
            } else if (txType == CONST_PW_TX_SIMPLE_REDELEGATE) {
                return new BigDecimal(KAVA_GAS_AMOUNT_REDELEGATE);
            } else if (txType == CONST_PW_TX_REINVEST) {
                return new BigDecimal(KAVA_GAS_AMOUNT_REINVEST);
            } else if (txType == CONST_PW_TX_SIMPLE_REWARD) {
                ArrayList<String> rewardGasFees = new ArrayList<String>(Arrays.asList(c.getResources().getStringArray(R.array.gas_multi_reward_kava)));
                return new BigDecimal(rewardGasFees.get(valCnt - 1));
            } else if (txType == CONST_PW_TX_VOTE) {
                return new BigDecimal(KAVA_GAS_AMOUNT_VOTE);
            } else if (txType == CONST_PW_TX_CLAIM_INCENTIVE || txType == CONST_PW_TX_CLAIM_HARVEST_REWARD) {
                return new BigDecimal(KAVA_GAS_AMOUNT_CLAIM_INCENTIVE);
            } else if (txType == CONST_PW_TX_CREATE_CDP || txType == CONST_PW_TX_DEPOSIT_CDP || txType == CONST_PW_TX_WITHDRAW_CDP || txType == CONST_PW_TX_DRAW_DEBT_CDP || txType == CONST_PW_TX_REPAY_CDP) {
                return new BigDecimal(KAVA_GAS_AMOUNT_CDP);
            } else if (txType == CONST_PW_TX_DEPOSIT_HARD || txType == CONST_PW_TX_WITHDRAW_HARD || txType == CONST_PW_TX_BORROW_HARD || txType == CONST_PW_TX_REPAY_HARD) {
                return new BigDecimal(KAVA_GAS_AMOUNT_HARD_POOL);
            } else if (txType == CONST_PW_TX_HTLS_REFUND) {
                return new BigDecimal(KAVA_GAS_AMOUNT_BEP3);
            }

        } else if (basechain.equals(BAND_MAIN)) {
            if (txType == CONST_PW_TX_SIMPLE_SEND) {
                return new BigDecimal(BAND_GAS_AMOUNT_SEND);
            } else if (txType == CONST_PW_TX_SIMPLE_DELEGATE) {
                return new BigDecimal(BAND_GAS_AMOUNT_STAKE);
            } else if (txType == CONST_PW_TX_SIMPLE_UNDELEGATE) {
                return new BigDecimal(BAND_GAS_AMOUNT_STAKE);
            } else if (txType == CONST_PW_TX_SIMPLE_REDELEGATE) {
                return new BigDecimal(BAND_GAS_AMOUNT_REDELEGATE);
            } else if (txType == CONST_PW_TX_REINVEST) {
                return new BigDecimal(BAND_GAS_AMOUNT_REINVEST);
            } else if (txType == CONST_PW_TX_SIMPLE_REWARD) {
                ArrayList<String> rewardGasFees = new ArrayList<String>(Arrays.asList(c.getResources().getStringArray(R.array.gas_multi_reward_kava)));
                return new BigDecimal(rewardGasFees.get(valCnt - 1));
            } else if (txType == CONST_PW_TX_SIMPLE_CHANGE_REWARD_ADDRESS) {
                return new BigDecimal(BAND_GAS_AMOUNT_ADDRESS_CHANGE);
            } else if (txType == CONST_PW_TX_VOTE) {
                return new BigDecimal(BAND_GAS_AMOUNT_VOTE);
            }

        } else if (basechain.equals(IOV_MAIN) || basechain.equals(IOV_TEST)) {
            if (txType == CONST_PW_TX_SIMPLE_SEND) {
                return new BigDecimal(IOV_GAS_AMOUNT_SEND);
            } else if (txType == CONST_PW_TX_SIMPLE_DELEGATE) {
                return new BigDecimal(IOV_GAS_AMOUNT_STAKE);
            } else if (txType == CONST_PW_TX_SIMPLE_UNDELEGATE) {
                return new BigDecimal(IOV_GAS_AMOUNT_STAKE);
            } else if (txType == CONST_PW_TX_SIMPLE_REDELEGATE) {
                return new BigDecimal(IOV_GAS_AMOUNT_REDELEGATE);
            } else if (txType == CONST_PW_TX_REINVEST) {
                return new BigDecimal(IOV_GAS_AMOUNT_REINVEST);
            } else if (txType == CONST_PW_TX_SIMPLE_REWARD) {
                ArrayList<String> rewardGasFees = new ArrayList<String>(Arrays.asList(c.getResources().getStringArray(R.array.gas_multi_reward_kava)));
                return new BigDecimal(rewardGasFees.get(valCnt - 1));
            } else if (txType == CONST_PW_TX_SIMPLE_CHANGE_REWARD_ADDRESS) {
                return new BigDecimal(IOV_GAS_AMOUNT_LOW);
            } else if (txType == CONST_PW_TX_VOTE) {
                return new BigDecimal(IOV_GAS_AMOUNT_LOW);
            } else if (txType == CONST_PW_TX_REGISTER_DOMAIN || txType == CONST_PW_TX_REGISTER_ACCOUNT) {
                return new BigDecimal(IOV_GAS_AMOUNT_REGISTER);
            } else if (txType == CONST_PW_TX_DELETE_DOMAIN || txType == CONST_PW_TX_DELETE_ACCOUNT) {
                return new BigDecimal(IOV_GAS_AMOUNT_DELETE);
            } else if (txType == CONST_PW_TX_RENEW_DOMAIN || txType == CONST_PW_TX_RENEW_ACCOUNT) {
                return new BigDecimal(IOV_GAS_AMOUNT_RENEW);
            } else if (txType == CONST_PW_TX_REPLACE_STARNAME) {
                return new BigDecimal(IOV_GAS_AMOUNT_REPLACE);
            }

        } else if (basechain.equals(OKEX_MAIN) || basechain.equals(OK_TEST)) {
            if (txType == CONST_PW_TX_SIMPLE_SEND) {
                return new BigDecimal(OK_GAS_AMOUNT_SEND);
            } else if (txType == CONST_PW_TX_OK_DEPOSIT || txType == CONST_PW_TX_OK_WITHDRAW) {
                return (new BigDecimal(OK_GAS_AMOUNT_STAKE_MUX).multiply(new BigDecimal(""+valCnt))).add(new BigDecimal(BaseConstant.OK_GAS_AMOUNT_STAKE));
            } else if (txType == CONST_PW_TX_OK_DIRECT_VOTE) {
                return (new BigDecimal(OK_GAS_AMOUNT_VOTE_MUX).multiply(new BigDecimal(""+valCnt))).add(new BigDecimal(BaseConstant.OK_GAS_AMOUNT_VOTE));
            }

        } else if (basechain.equals(CERTIK_MAIN) || basechain.equals(CERTIK_TEST)) {
            if (txType == CONST_PW_TX_SIMPLE_SEND) {
                return new BigDecimal(CERTIK_GAS_AMOUNT_SEND);
            } else if (txType == CONST_PW_TX_SIMPLE_DELEGATE) {
                return new BigDecimal(CERTIK_GAS_AMOUNT_STAKE);
            } else if (txType == CONST_PW_TX_SIMPLE_UNDELEGATE) {
                return new BigDecimal(CERTIK_GAS_AMOUNT_STAKE);
            } else if (txType == CONST_PW_TX_SIMPLE_REDELEGATE) {
                return new BigDecimal(CERTIK_GAS_AMOUNT_REDELEGATE);
            } else if (txType == CONST_PW_TX_REINVEST) {
                return new BigDecimal(CERTIK_GAS_AMOUNT_REINVEST);
            } else if (txType == CONST_PW_TX_SIMPLE_REWARD) {
                ArrayList<String> rewardGasFees = new ArrayList<String>(Arrays.asList(c.getResources().getStringArray(R.array.gas_multi_reward_kava)));
                return new BigDecimal(rewardGasFees.get(valCnt - 1));
            } else if (txType == CONST_PW_TX_SIMPLE_CHANGE_REWARD_ADDRESS) {
                return new BigDecimal(CERTIK_GAS_AMOUNT_REWARD_ADDRESS_CHANGE);
            } else if (txType == CONST_PW_TX_VOTE) {
                return new BigDecimal(CERTIK_GAS_AMOUNT_VOTE);
            }

        } else if (basechain.equals(SECRET_MAIN)) {
            if (txType == CONST_PW_TX_SIMPLE_SEND) {
                return new BigDecimal(SECRET_GAS_AMOUNT_SEND);
            } else if (txType == CONST_PW_TX_SIMPLE_DELEGATE) {
                return new BigDecimal(SECRET_GAS_AMOUNT_STAKE);
            } else if (txType == CONST_PW_TX_SIMPLE_UNDELEGATE) {
                return new BigDecimal(SECRET_GAS_AMOUNT_STAKE);
            } else if (txType == CONST_PW_TX_SIMPLE_REDELEGATE) {
                return new BigDecimal(SECRET_GAS_AMOUNT_REDELEGATE);
            } else if (txType == CONST_PW_TX_REINVEST) {
                return new BigDecimal(SECRET_GAS_AMOUNT_REINVEST);
            } else if (txType == CONST_PW_TX_SIMPLE_REWARD) {
                ArrayList<String> rewardGasFees = new ArrayList<String>(Arrays.asList(c.getResources().getStringArray(R.array.gas_multi_reward_kava)));
                return new BigDecimal(rewardGasFees.get(valCnt - 1));
            } else if (txType == CONST_PW_TX_SIMPLE_CHANGE_REWARD_ADDRESS) {
                return new BigDecimal(SECRET_GAS_AMOUNT_REWARD_ADDRESS_CHANGE);
            } else if (txType == CONST_PW_TX_VOTE) {
                return new BigDecimal(SECRET_GAS_AMOUNT_VOTE);
            }

        } else if (basechain.equals(SENTINEL_MAIN)) {
            if (txType == CONST_PW_TX_SIMPLE_SEND) {
                return new BigDecimal(SENTINEL_GAS_AMOUNT_SEND);
            } else if (txType == CONST_PW_TX_SIMPLE_DELEGATE) {
                return new BigDecimal(SENTINEL_GAS_AMOUNT_STAKE);
            } else if (txType == CONST_PW_TX_SIMPLE_UNDELEGATE) {
                return new BigDecimal(SENTINEL_GAS_AMOUNT_STAKE);
            } else if (txType == CONST_PW_TX_SIMPLE_REDELEGATE) {
                return new BigDecimal(SENTINEL_GAS_AMOUNT_REDELEGATE);
            } else if (txType == CONST_PW_TX_REINVEST) {
                return new BigDecimal(SENTINEL_GAS_AMOUNT_REINVEST);
            } else if (txType == CONST_PW_TX_SIMPLE_REWARD) {
                ArrayList<String> rewardGasFees = new ArrayList<String>(Arrays.asList(c.getResources().getStringArray(R.array.gas_multi_reward)));
                return new BigDecimal(rewardGasFees.get(valCnt - 1));
            } else if (txType == CONST_PW_TX_SIMPLE_CHANGE_REWARD_ADDRESS) {
                return new BigDecimal(SENTINEL_GAS_AMOUNT_REWARD_ADDRESS_CHANGE);
            } else if (txType == CONST_PW_TX_VOTE) {
                return new BigDecimal(SENTINEL_GAS_AMOUNT_VOTE);
            }

        } else if (basechain.equals(FETCHAI_MAIN)) {
            if (txType == CONST_PW_TX_SIMPLE_SEND) {
                return new BigDecimal(FETCH_GAS_AMOUNT_SEND);
            } else if (txType == CONST_PW_TX_SIMPLE_DELEGATE) {
                return new BigDecimal(FETCH_GAS_AMOUNT_STAKE);
            } else if (txType == CONST_PW_TX_SIMPLE_UNDELEGATE) {
                return new BigDecimal(FETCH_GAS_AMOUNT_STAKE);
            } else if (txType == CONST_PW_TX_SIMPLE_REDELEGATE) {
                return new BigDecimal(FETCH_GAS_AMOUNT_REDELEGATE);
            } else if (txType == CONST_PW_TX_REINVEST) {
                return new BigDecimal(FETCH_GAS_AMOUNT_REINVEST);
            } else if (txType == CONST_PW_TX_SIMPLE_REWARD) {
                ArrayList<String> rewardGasFees = new ArrayList<String>(Arrays.asList(c.getResources().getStringArray(R.array.gas_multi_reward)));
                return new BigDecimal(rewardGasFees.get(valCnt - 1));
            } else if (txType == CONST_PW_TX_SIMPLE_CHANGE_REWARD_ADDRESS) {
                return new BigDecimal(FETCH_GAS_AMOUNT_REWARD_ADDRESS_CHANGE);
            } else if (txType == CONST_PW_TX_VOTE) {
                return new BigDecimal(FETCH_GAS_AMOUNT_VOTE);
            }

        } else if (basechain.equals(SIF_MAIN)) {
            if (txType == CONST_PW_TX_SIMPLE_SEND) {
                return new BigDecimal(SIF_GAS_AMOUNT_SEND);
            } else if (txType == CONST_PW_TX_SIMPLE_DELEGATE) {
                return new BigDecimal(SIF_GAS_AMOUNT_STAKE);
            } else if (txType == CONST_PW_TX_SIMPLE_UNDELEGATE) {
                return new BigDecimal(SIF_GAS_AMOUNT_STAKE);
            } else if (txType == CONST_PW_TX_SIMPLE_REDELEGATE) {
                return new BigDecimal(SIF_GAS_AMOUNT_REDELEGATE);
            } else if (txType == CONST_PW_TX_REINVEST) {
                return new BigDecimal(SIF_GAS_AMOUNT_REINVEST);
            } else if (txType == CONST_PW_TX_SIMPLE_REWARD) {
                ArrayList<String> rewardGasFees = new ArrayList<String>(Arrays.asList(c.getResources().getStringArray(R.array.gas_multi_reward)));
                return new BigDecimal(rewardGasFees.get(valCnt - 1));
            } else if (txType == CONST_PW_TX_SIMPLE_CHANGE_REWARD_ADDRESS) {
                return new BigDecimal(SIF_GAS_AMOUNT_REWARD_ADDRESS_CHANGE);
            } else if (txType == CONST_PW_TX_VOTE) {
                return new BigDecimal(SIF_GAS_AMOUNT_VOTE);
            }

        } else if (basechain.equals(KI_MAIN)) {
            if (txType == CONST_PW_TX_SIMPLE_SEND) {
                return new BigDecimal(KI_GAS_AMOUNT_SEND);
            } else if (txType == CONST_PW_TX_SIMPLE_DELEGATE) {
                return new BigDecimal(KI_GAS_AMOUNT_STAKE);
            } else if (txType == CONST_PW_TX_SIMPLE_UNDELEGATE) {
                return new BigDecimal(KI_GAS_AMOUNT_STAKE);
            } else if (txType == CONST_PW_TX_SIMPLE_REDELEGATE) {
                return new BigDecimal(KI_GAS_AMOUNT_REDELEGATE);
            } else if (txType == CONST_PW_TX_REINVEST) {
                return new BigDecimal(KI_GAS_AMOUNT_REINVEST);
            } else if (txType == CONST_PW_TX_SIMPLE_REWARD) {
                ArrayList<String> rewardGasFees = new ArrayList<String>(Arrays.asList(c.getResources().getStringArray(R.array.gas_multi_reward)));
                return new BigDecimal(rewardGasFees.get(valCnt - 1));
            } else if (txType == CONST_PW_TX_SIMPLE_CHANGE_REWARD_ADDRESS) {
                return new BigDecimal(KI_GAS_AMOUNT_REWARD_ADDRESS_CHANGE);
            } else if (txType == CONST_PW_TX_VOTE) {
                return new BigDecimal(KI_GAS_AMOUNT_VOTE);
            }

        }
        return result;
    }

    public static BigDecimal getEstimateGasFeeAmount(Context c, BaseChain basechain, int txType,  int valCnt) {
        if (basechain.equals(COSMOS_MAIN) || basechain.equals(COSMOS_TEST)) {
            BigDecimal gasRate = new BigDecimal(COSMOS_GAS_RATE_AVERAGE);
            BigDecimal gasAmount = getEstimateGasAmount(c, basechain, txType, valCnt);
            return gasRate.multiply(gasAmount).setScale(0, RoundingMode.DOWN);

        } else if (basechain.equals(IRIS_MAIN) || basechain.equals(IRIS_TEST)) {
            BigDecimal gasRate = new BigDecimal(IRIS_GAS_RATE_AVERAGE);
            BigDecimal gasAmount = getEstimateGasAmount(c, basechain, txType, valCnt);
            return gasRate.multiply(gasAmount).setScale(0, RoundingMode.DOWN);

        } else if (basechain.equals(AKASH_MAIN)) {
            BigDecimal gasRate = new BigDecimal(COSMOS_GAS_RATE_AVERAGE);
            BigDecimal gasAmount = getEstimateGasAmount(c, basechain, txType, valCnt);
            return gasRate.multiply(gasAmount).setScale(0, RoundingMode.DOWN);

        } else if (basechain.equals(PERSIS_MAIN)) {
            BigDecimal gasRate = new BigDecimal(PERSIS_GAS_RATE_AVERAGE);
            BigDecimal gasAmount = getEstimateGasAmount(c, basechain, txType, valCnt);
            return gasRate.multiply(gasAmount).setScale(0, RoundingMode.DOWN);

        } else if (basechain.equals(CRYPTO_MAIN)) {
            BigDecimal gasRate = new BigDecimal(CRYPTO_GAS_RATE_TINY);
            BigDecimal gasAmount = getEstimateGasAmount(c, basechain, txType, valCnt);
            return gasRate.multiply(gasAmount).setScale(0, RoundingMode.DOWN);

        }

        else if (basechain.equals(BNB_MAIN) || basechain.equals(BNB_TEST)) {
            return new BigDecimal(FEE_BNB_SEND).setScale(8);

        } else if (basechain.equals(OKEX_MAIN) || basechain.equals(OK_TEST)) {
            BigDecimal gasRate = new BigDecimal(OK_GAS_RATE_AVERAGE);
            BigDecimal gasAmount = getEstimateGasAmount(c, basechain, txType, valCnt);
            return gasRate.multiply(gasAmount).setScale(18, RoundingMode.DOWN);
        }

        else if (basechain.equals(KAVA_MAIN) || basechain.equals(KAVA_TEST)) {
            return BigDecimal.ZERO;

        } else if (basechain.equals(BAND_MAIN)) {
            return BigDecimal.ZERO;

        } else if (basechain.equals(IOV_MAIN) || basechain.equals(IOV_TEST)) {
            BigDecimal gasRate = new BigDecimal(IOV_GAS_RATE_AVERAGE);
            BigDecimal gasAmount = getEstimateGasAmount(c, basechain, txType, valCnt);
            return gasRate.multiply(gasAmount).setScale(0, RoundingMode.DOWN);

        } else if (basechain.equals(CERTIK_MAIN) || basechain.equals(CERTIK_TEST)) {
            BigDecimal gasRate = new BigDecimal(CERTIK_GAS_RATE_AVERAGE);
            BigDecimal gasAmount = getEstimateGasAmount(c, basechain, txType, valCnt);
            return gasRate.multiply(gasAmount).setScale(0, RoundingMode.DOWN);

        } else if (basechain.equals(SECRET_MAIN)) {
            BigDecimal gasRate = new BigDecimal(SECRET_GAS_FEE_RATE_AVERAGE);
            BigDecimal gasAmount = getEstimateGasAmount(c, basechain, txType, valCnt);
            return gasRate.multiply(gasAmount).setScale(0, RoundingMode.DOWN);

        } else if (basechain.equals(SENTINEL_MAIN)) {
            BigDecimal gasRate = new BigDecimal(SENTINEL_GAS_FEE_RATE_AVERAGE);
            BigDecimal gasAmount = getEstimateGasAmount(c, basechain, txType, valCnt);
            return gasRate.multiply(gasAmount).setScale(0, RoundingMode.DOWN);

        } else if (basechain.equals(FETCHAI_MAIN)) {
            BigDecimal gasRate = new BigDecimal(FETCH_GAS_FEE_RATE_AVERAGE);
            BigDecimal gasAmount = getEstimateGasAmount(c, basechain, txType, valCnt);
            return gasRate.multiply(gasAmount).setScale(0, RoundingMode.DOWN);

        } else if (basechain.equals(SIF_MAIN)) {
            BigDecimal gasRate = new BigDecimal(SIF_GAS_FEE_RATE_AVERAGE);
            BigDecimal gasAmount = getEstimateGasAmount(c, basechain, txType, valCnt);
            return gasRate.multiply(gasAmount).setScale(0, RoundingMode.DOWN);

        } else if (basechain.equals(KI_MAIN)) {
            BigDecimal gasRate = new BigDecimal(KI_GAS_FEE_RATE_AVERAGE);
            BigDecimal gasAmount = getEstimateGasAmount(c, basechain, txType, valCnt);
            return gasRate.multiply(gasAmount).setScale(0, RoundingMode.DOWN);

        }
        return BigDecimal.ZERO;
    }

    public static BigDecimal getGasRate(BaseChain basechain, int position) {
        if (basechain.equals(IRIS_MAIN) || basechain.equals(IRIS_TEST)) {
            if (position == 0) {
                return new BigDecimal(COSMOS_GAS_RATE_TINY);
            } else if (position == 1) {
                return new BigDecimal(COSMOS_GAS_RATE_LOW);
            }
            return new BigDecimal(COSMOS_GAS_RATE_AVERAGE);

        } else if (basechain.equals(IRIS_MAIN) || basechain.equals(IRIS_TEST)) {
            if (position == 0) {
                return new BigDecimal(IRIS_GAS_RATE_TINY);
            } else if (position == 1) {
                return new BigDecimal(IRIS_GAS_RATE_LOW);
            }
            return new BigDecimal(IRIS_GAS_RATE_AVERAGE);

        } else if (basechain.equals(PERSIS_MAIN)) {
            if (position == 0) {
                return new BigDecimal(PERSIS_GAS_RATE_TINY);
            } else if (position == 1) {
                return new BigDecimal(PERSIS_GAS_RATE_LOW);
            }
            return new BigDecimal(PERSIS_GAS_RATE_AVERAGE);

        } else if (basechain.equals(CRYPTO_MAIN)) {
            if (position == 0) {
                return new BigDecimal(CRYPTO_GAS_RATE_TINY);
            } else if (position == 1) {
                return new BigDecimal(CRYPTO_GAS_RATE_LOW);
            }
            return new BigDecimal(CRYPTO_GAS_RATE_AVERAGE);

        }

        else if (basechain.equals(KAVA_MAIN) || basechain.equals(KAVA_TEST)) {
            if (position == 0) {
                return BigDecimal.ZERO;
            } else if (position == 1) {
                return new BigDecimal(KAVA_GAS_RATE_LOW);
            }
            return new BigDecimal(KAVA_GAS_RATE_AVERAGE);

        } else if (basechain.equals(BAND_MAIN)) {
            if (position == 0) {
                return BigDecimal.ZERO;
            } else if (position == 1) {
                return new BigDecimal(BAND_GAS_RATE_LOW);
            }
            return new BigDecimal(BAND_GAS_RATE_AVERAGE);

        }

        else if (basechain.equals(BNB_MAIN) || basechain.equals(BNB_TEST)) {
            return BigDecimal.ZERO;

        } else if (basechain.equals(IOV_MAIN) || basechain.equals(IOV_TEST)) {
            return new BigDecimal(IOV_GAS_RATE_AVERAGE);

        } else if (basechain.equals(OKEX_MAIN) || basechain.equals(OK_TEST)) {
            return new BigDecimal(OK_GAS_RATE_AVERAGE);

        } else if (basechain.equals(CERTIK_MAIN) || basechain.equals(CERTIK_TEST)) {
            return new BigDecimal(CERTIK_GAS_RATE_AVERAGE);

        } else if (basechain.equals(SECRET_MAIN)) {
            return new BigDecimal(SECRET_GAS_FEE_RATE_AVERAGE);

        } else if (basechain.equals(SENTINEL_MAIN)) {
            return new BigDecimal(SENTINEL_GAS_FEE_RATE_AVERAGE);

        } else if (basechain.equals(FETCHAI_MAIN)) {
            return new BigDecimal(FETCH_GAS_FEE_RATE_AVERAGE);

        } else if (basechain.equals(SIF_MAIN)) {
            return new BigDecimal(SIF_GAS_FEE_RATE_AVERAGE);

        } else if (basechain.equals(KI_MAIN)) {
            return new BigDecimal(KI_GAS_FEE_RATE_AVERAGE);

        }
        return BigDecimal.ZERO;
    }


    public static BigDecimal getHardSuppliedAmountByDenom(Context context, BaseData baseData, String denom, ArrayList<HardMyDeposit> myDeposit) {
        BigDecimal myDepositAmount = BigDecimal.ZERO;
        if (myDeposit != null && myDeposit.size() > 0) {
            for (Coin coin: myDeposit.get(0).amount) {
                if (coin.denom.equals(denom)) {
                    myDepositAmount = new BigDecimal(coin.amount);
                }
            }
        }
        return myDepositAmount;
    }

    public static BigDecimal getHardSuppliedValueByDenom(Context context, BaseData baseData, String denom, ArrayList<HardMyDeposit> myDeposit) {
        final BigDecimal denomPrice = getKavaPrice(baseData, denom);
        final int decimal           = WUtil.getKavaCoinDecimal(denom);
        return (getHardSuppliedAmountByDenom(context, baseData, denom, myDeposit)).movePointLeft(decimal).multiply(denomPrice);
    }

    public static BigDecimal getHardBorrowedAmountByDenom(Context context, BaseData baseData, String denom, ArrayList<HardMyBorrow> myBorrow) {
        BigDecimal myBorrowedAmount = BigDecimal.ZERO;
        if (myBorrow != null && myBorrow.size() > 0) {
            for (Coin coin: myBorrow.get(0).amount) {
                if (coin.denom.equals(denom)) {
                    myBorrowedAmount = new BigDecimal(coin.amount);
                }
            }
        }
        return myBorrowedAmount;
    }

    public static BigDecimal getHardBorrowedValueByDenom(Context context, BaseData baseData, String denom, ArrayList<HardMyBorrow> myBorrow) {
        final BigDecimal denomPrice = getKavaPrice(baseData, denom);
        final int decimal           = WUtil.getKavaCoinDecimal(denom);
        return (getHardBorrowedAmountByDenom(context, baseData, denom, myBorrow)).movePointLeft(decimal).multiply(denomPrice);

    }

    public static BigDecimal getHardBorrowableAmountByDenom (Context context, BaseData baseData, String denom, ArrayList<HardMyDeposit> myDeposit, ArrayList<HardMyBorrow> myBorrow, ArrayList<Coin> moduleCoins, ArrayList<Coin> reserveCoin) {
        BigDecimal totalLTVValue            = BigDecimal.ZERO;
        BigDecimal totalBorrowedValue       = BigDecimal.ZERO;
        BigDecimal totalBorrowAbleValue     = BigDecimal.ZERO;
        BigDecimal totalBorrowAbleAmount    = BigDecimal.ZERO;

        BigDecimal SystemBorrowableAmount   = BigDecimal.ZERO;
        BigDecimal SystemBorrowableValue    = BigDecimal.ZERO;
        BigDecimal moduleAmount             = BigDecimal.ZERO;
        BigDecimal reserveAmount            = BigDecimal.ZERO;


        final HardParam hardParam                       = baseData.mHardParam;
        final HardParam.HardMoneyMarket hardMoneyMarket = hardParam.getHardMoneyMarket(denom);
        final BigDecimal denomPrice                     = getKavaPrice(baseData, denom);
        final int decimal                               =  WUtil.getKavaCoinDecimal(denom);

        if (myDeposit != null && myDeposit.size() > 0) {
            for (Coin coin: myDeposit.get(0).amount) {
                int innnerDecimal       = WUtil.getKavaCoinDecimal(coin.denom);
                BigDecimal LTV          = hardParam.getLTV(coin.denom);
                BigDecimal depositValue = BigDecimal.ZERO;
                BigDecimal ltvValue     = BigDecimal.ZERO;
//                if (coin.denom.equals("usdx") || coin.denom.equals("busd")) {
                if (coin.denom.equals("usdx")) {
                    depositValue = (new BigDecimal(coin.amount)).movePointLeft(innnerDecimal);

                } else {
                    BigDecimal innerPrice = getKavaPrice(baseData, coin.denom);
                    depositValue = (new BigDecimal(coin.amount)).movePointLeft(innnerDecimal).multiply(innerPrice);

                }
                ltvValue = depositValue.multiply(LTV);
                totalLTVValue = totalLTVValue.add(ltvValue);

            }
        }

        if (myBorrow != null && myBorrow.size() > 0) {
            for (Coin coin: myBorrow.get(0).amount) {
                int innnerDecimal   = WUtil.getKavaCoinDecimal(coin.denom);
                BigDecimal borrowedValue    = BigDecimal.ZERO;
//                if (coin.denom.equals("usdx") || coin.denom.equals("busd")) {
                if (coin.denom.equals("usdx")) {
                    borrowedValue = (new BigDecimal(coin.amount)).movePointLeft(innnerDecimal);

                } else {
                    BigDecimal innerPrice = getKavaPrice(baseData, coin.denom);
                    borrowedValue = (new BigDecimal(coin.amount)).movePointLeft(innnerDecimal).multiply(innerPrice);

                }
                totalBorrowedValue = totalBorrowedValue.add(borrowedValue);
            }
        }
        totalBorrowAbleValue = (totalLTVValue.subtract(totalBorrowedValue)).max(BigDecimal.ZERO);
        totalBorrowAbleAmount = totalBorrowAbleValue.movePointRight(decimal).divide(denomPrice, decimal, RoundingMode.DOWN);
//        WLog.w("totalBorrowAbleAmount " +  totalBorrowAbleAmount);

        if (moduleCoins != null) {
            for (Coin coin: moduleCoins) {
                if (coin.denom.equals(denom)) {
                    moduleAmount = new BigDecimal(coin.amount);
                }
            }
        }
        if (reserveCoin != null) {
            for (Coin coin: reserveCoin) {
                if (coin.denom.equals(denom)) {
                    reserveAmount = new BigDecimal(coin.amount);
                }
            }
        }
        BigDecimal moduleBorrowable = moduleAmount.subtract(reserveAmount);
        if (hardMoneyMarket.borrow_limit.has_max_limit) {
            SystemBorrowableAmount = new BigDecimal(hardMoneyMarket.borrow_limit.maximum_limit).min(moduleBorrowable);
        } else {
            SystemBorrowableAmount = moduleBorrowable;
        }
        SystemBorrowableValue = SystemBorrowableAmount.movePointLeft(decimal).multiply(denomPrice);
//        WLog.w("SystemBorrowableAmount " +  SystemBorrowableAmount);

//        BigDecimal finalBorrowableValue = totalBorrowAbleValue.min(SystemBorrowableValue);
        BigDecimal finalBorrowableAmount = totalBorrowAbleAmount.min(SystemBorrowableAmount);

        return finalBorrowableAmount;
    }

    public static BigDecimal getHardBorrowableValueByDenom (Context context, BaseData baseData, String denom, ArrayList<HardMyDeposit> myDeposit, ArrayList<HardMyBorrow> myBorrow, ArrayList<Coin> moduleCoins, ArrayList<Coin> reserveCoin) {
        BigDecimal totalLTVValue            = BigDecimal.ZERO;
        BigDecimal totalBorrowedValue       = BigDecimal.ZERO;
        BigDecimal totalBorrowAbleValue     = BigDecimal.ZERO;
        BigDecimal totalBorrowAbleAmount    = BigDecimal.ZERO;

        BigDecimal SystemBorrowableAmount   = BigDecimal.ZERO;
        BigDecimal SystemBorrowableValue    = BigDecimal.ZERO;
        BigDecimal moduleAmount             = BigDecimal.ZERO;
        BigDecimal reserveAmount            = BigDecimal.ZERO;


        final HardParam hardParam                       = baseData.mHardParam;
        final HardParam.HardMoneyMarket hardMoneyMarket = hardParam.getHardMoneyMarket(denom);
        final BigDecimal denomPrice                     = getKavaPrice(baseData, denom);
        final int decimal                               =  WUtil.getKavaCoinDecimal(denom);

        if (myDeposit != null && myDeposit.size() > 0) {
            for (Coin coin: myDeposit.get(0).amount) {
                int innnerDecimal       = WUtil.getKavaCoinDecimal(coin.denom);
                BigDecimal LTV          = hardParam.getLTV(coin.denom);
                BigDecimal depositValue = BigDecimal.ZERO;
                BigDecimal ltvValue     = BigDecimal.ZERO;
//                if (coin.denom.equals("usdx") || coin.denom.equals("busd")) {
                if (coin.denom.equals("usdx")) {
                    depositValue = (new BigDecimal(coin.amount)).movePointLeft(innnerDecimal);

                } else {
                    BigDecimal innerPrice = getKavaPrice(baseData, coin.denom);
                    depositValue = (new BigDecimal(coin.amount)).movePointLeft(innnerDecimal).multiply(innerPrice);

                }
                ltvValue = depositValue.multiply(LTV);
                totalLTVValue = totalLTVValue.add(ltvValue);

            }
        }
//        WLog.w("totalLTVValue " + totalLTVValue);

        if (myBorrow != null && myBorrow.size() > 0) {
            for (Coin coin: myBorrow.get(0).amount) {
                int innnerDecimal           = WUtil.getKavaCoinDecimal(coin.denom);
                BigDecimal borrowedValue    = BigDecimal.ZERO;
//                if (coin.denom.equals("usdx") || coin.denom.equals("busd")) {
                if (coin.denom.equals("usdx")) {
                    borrowedValue = (new BigDecimal(coin.amount)).movePointLeft(innnerDecimal);

                } else {
                    BigDecimal innerPrice = getKavaPrice(baseData, coin.denom);
                    borrowedValue = (new BigDecimal(coin.amount)).movePointLeft(innnerDecimal).multiply(innerPrice);

                }
                totalBorrowedValue = totalBorrowedValue.add(borrowedValue);
            }
        }
        totalBorrowAbleValue = (totalLTVValue.subtract(totalBorrowedValue)).max(BigDecimal.ZERO);
        totalBorrowAbleAmount = totalBorrowAbleValue.movePointRight(decimal).divide(denomPrice, decimal, RoundingMode.DOWN);
//        WLog.w("totalBorrowAbleValue " + totalBorrowAbleValue);

        if (moduleCoins != null) {
            for (Coin coin: moduleCoins) {
                if (coin.denom.equals(denom)) {
                    moduleAmount = new BigDecimal(coin.amount);
                }
            }
        }
        if (reserveCoin != null) {
            for (Coin coin: reserveCoin) {
                if (coin.denom.equals(denom)) {
                    reserveAmount = new BigDecimal(coin.amount);
                }
            }
        }
        BigDecimal moduleBorrowable = moduleAmount.subtract(reserveAmount);
        if (hardMoneyMarket.borrow_limit.has_max_limit) {
            SystemBorrowableAmount = new BigDecimal(hardMoneyMarket.borrow_limit.maximum_limit).min(moduleBorrowable);
        } else {
            SystemBorrowableAmount = moduleBorrowable;
        }
//        WLog.w("moduleBorrowable " + moduleBorrowable);
        SystemBorrowableValue = SystemBorrowableAmount.movePointLeft(decimal).multiply(denomPrice);
//        WLog.w("SystemBorrowableValue " + SystemBorrowableValue);

        BigDecimal finalBorrowableValue = totalBorrowAbleValue.min(SystemBorrowableValue);
//        BigDecimal finalBorrowableAmount = totalBorrowAbleAmount.min(SystemBorrowableAmount);
        return finalBorrowableValue;
    }


    public static BigDecimal getKavaPrice(BaseData baseData, String denom) {
        BigDecimal result = BigDecimal.ZERO;
        if (denom.equals("usdx")) {
            result = BigDecimal.ONE;
        } else {
            HardParam hardParam = baseData.mHardParam;
            MarketPrice price   = baseData.mKavaTokenPrices.get(hardParam.getSpotMarketId(denom));
            if (price != null) {
                result = new BigDecimal(price.price);
            }
        }
        return result;
    }

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

        if (denom.equals(TOKEN_HTLC_BINANCE_TEST_BNB)) {
            return true;
        }
        if (denom.equals(TOKEN_HTLC_BINANCE_TEST_BTC)) {
            return true;
        }

        if (denom.equals(TOKEN_HTLC_KAVA_TEST_BNB)) {
            return true;
        }
        if (denom.equals(TOKEN_HTLC_KAVA_TEST_BTC)) {
            return true;
        }
        return false;
    }

    public static String getExplorer(BaseChain basechain) {
        if (basechain.equals(BNB_MAIN)) {
            return EXPLORER_BINANCE_MAIN;

        } else if (basechain.equals(BNB_TEST)) {
            return EXPLORER_BINANCE_TEST;

        } else if (basechain.equals(KAVA_MAIN)) {
            return EXPLORER_KAVA_MAIN;

        } else if (basechain.equals(KAVA_TEST)) {
            return EXPLORER_KAVA_TEST;

        } else if (basechain.equals(OKEX_MAIN)) {
            return EXPLORER_OKEX_MAIN;

        } else if (basechain.equals(OK_TEST)) {
            return EXPLORER_OKEX_TEST;

        } else if (basechain.equals(BAND_MAIN)) {
            return EXPLORER_BAND_MAIN;

        } else if (basechain.equals(IOV_MAIN)) {
            return EXPLORER_IOV_MAIN;

        } else if (basechain.equals(CERTIK_MAIN) || basechain.equals(CERTIK_TEST)) {
            return EXPLORER_CERTIK;

        } else if (basechain.equals(SECRET_MAIN)) {
            return EXPLORER_SECRET_MAIN;

        } else if (basechain.equals(SENTINEL_MAIN)) {
            return EXPLORER_SENTINEL_MAIN;

        } else if (basechain.equals(FETCHAI_MAIN)) {
            return EXPLORER_FETCHAI_MAIN;

        } else if (basechain.equals(SIF_MAIN)) {
            return EXPLORER_SIF_MAIN;

        } else if (basechain.equals(KI_MAIN)) {
            return EXPLORER_KI_MAIN;

        }

        else if (basechain.equals(COSMOS_MAIN)) {
            return EXPLORER_COSMOS_MAIN;

        } else if (basechain.equals(IRIS_MAIN)) {
            return EXPLORER_IRIS_MAIN;

        } else if (basechain.equals(AKASH_MAIN)) {
            return EXPLORER_AKASH_MAIN;

        } else if (basechain.equals(PERSIS_MAIN)) {
            return EXPLORER_PERSIS_MAIN;

        } else if (basechain.equals(CRYPTO_MAIN)) {
            return EXPLORER_CRYPTOORG_MAIN;

        }

        else if (basechain.equals(COSMOS_TEST)) {
            return EXPLORER_COSMOS_TEST;

        } else if (basechain.equals(IRIS_TEST)) {
            return EXPLORER_IRIS_TEST;

        }
        return "";
    }

    public static String getTxExplorer(BaseChain basechain, String hash) {
        if (basechain.equals(BNB_MAIN)) {
            return EXPLORER_BINANCE_MAIN + "txs/" + hash;

        } else if (basechain.equals(BNB_TEST)) {
            return EXPLORER_BINANCE_TEST + "tx/" + hash;

        } else if (basechain.equals(KAVA_MAIN)) {
            return EXPLORER_KAVA_MAIN + "txs/" + hash;

        } else if (basechain.equals(KAVA_TEST)) {
            return EXPLORER_KAVA_TEST + "txs/" + hash;

        } else if (basechain.equals(OKEX_MAIN)) {
            return EXPLORER_OKEX_MAIN + "tx/0x" + hash;

        } else if (basechain.equals(OK_TEST)) {
            return EXPLORER_OKEX_TEST + "tx/" + hash;

        } else if (basechain.equals(BAND_MAIN)) {
            return EXPLORER_BAND_MAIN + "tx/" + hash;

        } else if (basechain.equals(IOV_MAIN)) {
            return EXPLORER_IOV_MAIN + "txs/" + hash;

        } else if (basechain.equals(CERTIK_MAIN) || basechain.equals(CERTIK_TEST)) {
            return EXPLORER_CERTIK + "Transactions/" + hash;

        } else if (basechain.equals(SECRET_MAIN)) {
            return EXPLORER_SECRET_MAIN + "transactions/" + hash;

        } else if (basechain.equals(SENTINEL_MAIN)) {
            return EXPLORER_SENTINEL_MAIN + "txs/" + hash;

        } else if (basechain.equals(FETCHAI_MAIN)) {
            return EXPLORER_FETCHAI_MAIN + "txs/" + hash;

        } else if (basechain.equals(SIF_MAIN)) {
            return EXPLORER_SIF_MAIN + "txs/" + hash;

        } else if (basechain.equals(KI_MAIN)) {
            return EXPLORER_KI_MAIN + "txs/" + hash;

        }

        else if (basechain.equals(COSMOS_MAIN)) {
            return EXPLORER_COSMOS_MAIN + "txs/" + hash;

        } else if (basechain.equals(IRIS_MAIN)) {
            return EXPLORER_IRIS_MAIN + "txs/" + hash;

        } else if (basechain.equals(AKASH_MAIN)) {
            return EXPLORER_AKASH_MAIN + "txs/" + hash;

        } else if (basechain.equals(PERSIS_MAIN)) {
            return EXPLORER_PERSIS_MAIN + "txs/" + hash;

        } else if (basechain.equals(CRYPTO_MAIN)) {
            return EXPLORER_CRYPTOORG_MAIN + "txs/" + hash;

        }

        else if (basechain.equals(COSMOS_TEST)) {
            return EXPLORER_COSMOS_TEST + "txs/" + hash;

        } else if (basechain.equals(IRIS_TEST)) {
            return EXPLORER_IRIS_TEST + "txs/" + hash;

        }
        return "";
    }

    //parse & check vesting account
    public static void onParseVestingAccount(BaseData baseData) {
        WLog.w("onParseVestingAccount");
        Any account = baseData.mGRpcAccount;
        if (account == null ) return;
        ArrayList<Coin> sBalace = new ArrayList<>();
        for (Coin coin:baseData.mGrpcBalance) {
            sBalace.add(coin);
        }
        if (account.getTypeUrl().contains(Vesting.PeriodicVestingAccount.getDescriptor().getFullName())) {
            Vesting.PeriodicVestingAccount vestingAccount = null;
            try {
                vestingAccount = Vesting.PeriodicVestingAccount.parseFrom(account.getValue());
            } catch (InvalidProtocolBufferException e) {
                WLog.e("onParseVestingAccount " + e.getMessage());
                return;
            }
            for (Coin coin: sBalace) {
                String denom = coin.denom;
                BigDecimal dpBalance = BigDecimal.ZERO;
                BigDecimal dpVesting = BigDecimal.ZERO;
                BigDecimal originalVesting = BigDecimal.ZERO;
                BigDecimal remainVesting = BigDecimal.ZERO;
                BigDecimal delegatedVesting = BigDecimal.ZERO;

                dpBalance = new BigDecimal(coin.amount);
                WLog.w("dpBalance " +  denom + "  " +  dpBalance);

                for (CoinOuterClass.Coin vesting : vestingAccount.getBaseVestingAccount().getOriginalVestingList()) {
                    if (vesting.getDenom().equals(denom)) {
                        originalVesting = originalVesting.add(new BigDecimal(vesting.getAmount()));
                    }
                }
                WLog.w("originalVesting " +  denom + "  " +  originalVesting);

                for (CoinOuterClass.Coin vesting : vestingAccount.getBaseVestingAccount().getDelegatedVestingList()) {
                    if (vesting.getDenom().equals(denom)) {
                        delegatedVesting = delegatedVesting.add(new BigDecimal(vesting.getAmount()));
                    }
                }
                WLog.w("delegatedVesting " +  denom + "  " +  delegatedVesting);

                remainVesting = WDp.onParsePeriodicRemainVestingsAmountByDenom(vestingAccount, denom);
                WLog.w("remainVesting " +  denom + "  " +  remainVesting);

                dpVesting = remainVesting.subtract(delegatedVesting);
                WLog.w("dpVestingA " +  denom + "  " +  dpVesting);

                dpVesting = dpVesting.compareTo(BigDecimal.ZERO) <= 0 ? BigDecimal.ZERO : dpVesting;
                WLog.w("dpVestingB " +  denom + "  " +  dpVesting);

                if (remainVesting.compareTo(delegatedVesting)> 0) {
                    dpBalance = dpBalance.subtract(remainVesting).add(delegatedVesting);
                }
                WLog.w("final dpBalance  " +  denom + "  " +  dpBalance);

                if (dpVesting.compareTo(BigDecimal.ZERO) > 0) {
                    Coin vestingCoin = new Coin(denom, dpVesting.toPlainString());
                    baseData.mGrpcVesting.add(vestingCoin);
                    int replace = -1;
                    for (int i = 0; i < baseData.mGrpcBalance.size(); i ++) {
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
            for (Coin coin: sBalace) {
                String denom = coin.denom;
                BigDecimal dpBalance = BigDecimal.ZERO;
                BigDecimal dpVesting = BigDecimal.ZERO;
                BigDecimal originalVesting = BigDecimal.ZERO;
                BigDecimal remainVesting = BigDecimal.ZERO;
                BigDecimal delegatedVesting = BigDecimal.ZERO;
                dpBalance = new BigDecimal(coin.amount);
                WLog.w("dpBalance " +  denom + "  " +  dpBalance);

                for (CoinOuterClass.Coin vesting : vestingAccount.getBaseVestingAccount().getOriginalVestingList()) {
                    if (vesting.getDenom().equals(denom)) {
                        originalVesting = originalVesting.add(new BigDecimal(vesting.getAmount()));
                    }
                }
                WLog.w("originalVesting " +  denom + "  " +  originalVesting);

                for (CoinOuterClass.Coin vesting : vestingAccount.getBaseVestingAccount().getDelegatedVestingList()) {
                    if (vesting.getDenom().equals(denom)) {
                        delegatedVesting = delegatedVesting.add(new BigDecimal(vesting.getAmount()));
                    }
                }
                WLog.w("delegatedVesting " +  denom + "  " +  delegatedVesting);

                long cTime = Calendar.getInstance().getTime().getTime();
                long vestingStart = vestingAccount.getStartTime()  * 1000;
                long vestingEnd = vestingAccount.getBaseVestingAccount().getEndTime() * 1000;
                if (cTime < vestingStart) {
                    remainVesting = originalVesting;
                } else if (cTime > vestingEnd) {
                    remainVesting = BigDecimal.ZERO;
                } else if (cTime < vestingEnd) {
                    float progress = ((float)(cTime - vestingStart) / (float)(vestingEnd - vestingStart));
                    remainVesting = originalVesting.multiply(new BigDecimal(1 - progress)).setScale(0, RoundingMode.UP);
                }
                WLog.w("remainVesting " +  denom + "  " +  remainVesting);

                dpVesting = remainVesting.subtract(delegatedVesting);
                WLog.w("dpVestingA " +  denom + "  " +  dpVesting);

                dpVesting = dpVesting.compareTo(BigDecimal.ZERO) <= 0 ? BigDecimal.ZERO : dpVesting;
                WLog.w("dpVestingB " +  denom + "  " +  dpVesting);

                if (remainVesting.compareTo(delegatedVesting)> 0) {
                    dpBalance = dpBalance.subtract(remainVesting).add(delegatedVesting);
                }
                WLog.w("final dpBalance  " +  denom + "  " +  dpBalance);

                if (dpVesting.compareTo(BigDecimal.ZERO) > 0) {
                    Coin vestingCoin = new Coin(denom, dpVesting.toPlainString());
                    baseData.mGrpcVesting.add(vestingCoin);
                    int replace = -1;
                    for (int i = 0; i < baseData.mGrpcBalance.size(); i ++) {
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
            for (Coin coin: sBalace) {
                String denom = coin.denom;
                BigDecimal dpBalance = BigDecimal.ZERO;
                BigDecimal dpVesting = BigDecimal.ZERO;
                BigDecimal originalVesting = BigDecimal.ZERO;
                BigDecimal remainVesting = BigDecimal.ZERO;
                BigDecimal delegatedVesting = BigDecimal.ZERO;
                dpBalance = new BigDecimal(coin.amount);
                WLog.w("dpBalance " +  denom + "  " +  dpBalance);

                for (CoinOuterClass.Coin vesting : vestingAccount.getBaseVestingAccount().getOriginalVestingList()) {
                    if (vesting.getDenom().equals(denom)) {
                        originalVesting = originalVesting.add(new BigDecimal(vesting.getAmount()));
                    }
                }
                WLog.w("originalVesting " +  denom + "  " +  originalVesting);

                for (CoinOuterClass.Coin vesting : vestingAccount.getBaseVestingAccount().getDelegatedVestingList()) {
                    if (vesting.getDenom().equals(denom)) {
                        delegatedVesting = delegatedVesting.add(new BigDecimal(vesting.getAmount()));
                    }
                }
                WLog.w("delegatedVesting " +  denom + "  " +  delegatedVesting);

                long cTime = Calendar.getInstance().getTime().getTime();
                long vestingEnd = vestingAccount.getBaseVestingAccount().getEndTime() * 1000;
                if (cTime < vestingEnd) {
                    remainVesting = originalVesting;
                }
                WLog.w("remainVesting " +  denom + "  " +  remainVesting);

                dpVesting = remainVesting.subtract(delegatedVesting);
                WLog.w("dpVestingA " +  denom + "  " +  dpVesting);

                dpVesting = dpVesting.compareTo(BigDecimal.ZERO) <= 0 ? BigDecimal.ZERO : dpVesting;
                WLog.w("dpVestingB " +  denom + "  " +  dpVesting);

                if (remainVesting.compareTo(delegatedVesting)> 0) {
                    dpBalance = dpBalance.subtract(remainVesting).add(delegatedVesting);
                }
                WLog.w("final dpBalance  " +  denom + "  " +  dpBalance);

                if (dpVesting.compareTo(BigDecimal.ZERO) > 0) {
                    Coin vestingCoin = new Coin(denom, dpVesting.toPlainString());
                    baseData.mGrpcVesting.add(vestingCoin);
                    int replace = -1;
                    for (int i = 0; i < baseData.mGrpcBalance.size(); i ++) {
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

    public static void onParsePersisVestingAccount(BaseData baseData) {
        WLog.w("onParsePersisVestingAccount");
        Any account = baseData.mGRpcAccount;
        if (account == null ) return;
        ArrayList<Coin> sBalace = new ArrayList<>();
        for (Coin coin:baseData.mGrpcBalance) {
            sBalace.add(coin);
        }
        if (account.getTypeUrl().contains(Vesting.PeriodicVestingAccount.getDescriptor().getFullName())) {
            Vesting.PeriodicVestingAccount vestingAccount = null;
            try {
                vestingAccount = Vesting.PeriodicVestingAccount.parseFrom(account.getValue());
            } catch (InvalidProtocolBufferException e) {
                WLog.e("onParseVestingAccount " + e.getMessage());
                return;
            }
            for (Coin coin: sBalace) {
                String denom = coin.denom;
                BigDecimal bankBalance = BigDecimal.ZERO;
                BigDecimal dpBalance = BigDecimal.ZERO;
                BigDecimal dpVesting = BigDecimal.ZERO;
                BigDecimal remainVesting = BigDecimal.ZERO;

                bankBalance = new BigDecimal(coin.amount);
                WLog.w("bankBalance " +  denom + "  " +  bankBalance);

                remainVesting = WDp.onParsePeriodicRemainVestingsAmountByDenom(vestingAccount, denom);
                WLog.w("remainVesting " +  denom + "  " +  remainVesting);

                dpBalance = bankBalance.compareTo(remainVesting) <= 0 ? BigDecimal.ZERO : bankBalance.subtract(remainVesting);
                WLog.w("dpBalance " +  denom + "  " +  dpBalance);

                dpVesting = bankBalance.subtract(dpBalance);
                WLog.w("dpVesting " +  denom + "  " +  dpVesting);

                if (dpVesting.compareTo(BigDecimal.ZERO) > 0) {
                    Coin vestingCoin = new Coin(denom, dpVesting.toPlainString());
                    baseData.mGrpcVesting.add(vestingCoin);
                    int replace = -1;
                    for (int i = 0; i < baseData.mGrpcBalance.size(); i ++) {
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
            for (Coin coin: sBalace) {
                String denom = coin.denom;
                BigDecimal bankBalance = BigDecimal.ZERO;
                BigDecimal originalVesting = BigDecimal.ZERO;
                BigDecimal dpBalance = BigDecimal.ZERO;
                BigDecimal dpVesting = BigDecimal.ZERO;
                BigDecimal remainVesting = BigDecimal.ZERO;

                bankBalance = new BigDecimal(coin.amount);
                WLog.w("bankBalance " +  denom + "  " +  bankBalance);
                for (CoinOuterClass.Coin vesting : vestingAccount.getBaseVestingAccount().getOriginalVestingList()) {
                    if (vesting.getDenom().equals(denom)) {
                        originalVesting = originalVesting.add(new BigDecimal(vesting.getAmount()));
                    }
                }
                WLog.w("originalVesting " +  denom + "  " +  originalVesting);

                long cTime = Calendar.getInstance().getTime().getTime();
                long vestingStart = vestingAccount.getStartTime()  * 1000;
                long vestingEnd = vestingAccount.getBaseVestingAccount().getEndTime() * 1000;
                if (cTime < vestingStart) {
                    remainVesting = originalVesting;
                } else if (cTime > vestingEnd) {
                    remainVesting = BigDecimal.ZERO;
                } else if (cTime < vestingEnd) {
                    float progress = ((float)(cTime - vestingStart) / (float)(vestingEnd - vestingStart));
                    remainVesting = originalVesting.multiply(new BigDecimal(1 - progress)).setScale(0, RoundingMode.UP);
                }
                WLog.w("remainVesting " +  denom + "  " +  remainVesting);

                if (remainVesting.compareTo(BigDecimal.ZERO) > 0) {
                    dpBalance = BigDecimal.ZERO;
                    dpVesting = bankBalance;
                } else {
                    dpBalance = bankBalance;
                    dpVesting = BigDecimal.ZERO;
                }

                if (dpVesting.compareTo(BigDecimal.ZERO) > 0) {
                    Coin vestingCoin = new Coin(denom, dpVesting.toPlainString());
                    baseData.mGrpcVesting.add(vestingCoin);
                    int replace = -1;
                    for (int i = 0; i < baseData.mGrpcBalance.size(); i ++) {
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
