package wannabit.io.cosmostaion.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.SpannableString;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf2.Any;
import com.google.zxing.common.BitMatrix;
import com.squareup.picasso.Picasso;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.nio.ByteBuffer;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
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
import osmosis.gamm.v1beta1.PoolOuterClass;
import osmosis.incentives.GaugeOuterClass;
import osmosis.lockup.Lock;
import osmosis.poolincentives.v1beta1.QueryOuterClass;
import starnamed.x.starname.v1beta1.Types;
import tendermint.liquidity.v1beta1.Liquidity;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.MainActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.dao.Balance;
import wannabit.io.cosmostaion.dao.BnbTicker;
import wannabit.io.cosmostaion.dao.BnbToken;
import wannabit.io.cosmostaion.dao.IbcToken;
import wannabit.io.cosmostaion.model.ExportStarName;
import wannabit.io.cosmostaion.model.GDexManager;
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
import static wannabit.io.cosmostaion.base.BaseChain.ALTHEA_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.AXELAR_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.BAND_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.BNB_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.BNB_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.CERTIK_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.CERTIK_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.COSMOS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.COSMOS_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.CRYPTO_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.EMONEY_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.FETCHAI_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.IOV_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.IOV_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.IRIS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.IRIS_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.JUNO_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.KAVA_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.KAVA_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.KI_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.MEDI_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.MEDI_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.OKEX_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.OK_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.OSMOSIS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.PERSIS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.RIZON_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.RIZON_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.SECRET_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.SENTINEL_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.SIF_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.UMEE_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.isGRPC;
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

                        } else if (coin.denom.equals(TOKEN_SWP)) {
                            dpBalance = BigDecimal.ZERO;
                            dpVesting = BigDecimal.ZERO;
                            originalVesting = BigDecimal.ZERO;
                            remainVesting = BigDecimal.ZERO;
                            delegatedVesting = BigDecimal.ZERO;
                            dpBalance = new BigDecimal(coin.amount);

                            if (lcd.result.value.original_vesting != null && lcd.result.value.original_vesting.size() > 0) {
                                for (Coin vesting : lcd.result.value.original_vesting) {
                                    if (vesting.denom.equals(TOKEN_SWP)) {
                                        originalVesting = originalVesting.add(new BigDecimal(vesting.amount));
                                    }
                                }
                            }
                            WLog.w("TOKEN_SWP dpBalance " +  dpBalance);
                            WLog.w("TOKEN_SWP originalVesting " +  originalVesting);
                            remainVesting = lcd.result.value.getCalcurateVestingAmountSumByDenom(TOKEN_SWP);

                            dpBalance = dpBalance.subtract(remainVesting);
                            WLog.w("TOKEN_SWP dpBalancee " +  dpBalance);

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

    public static String bytes2Hex( byte[] raw) {
        String HEXES = "0123456789ABCDEF";
        if ( raw == null ) {
            return null;
        }
        final StringBuilder hex = new StringBuilder( 2 * raw.length );
        for ( final byte b : raw ) {
            hex.append(HEXES.charAt((b & 0xF0) >> 4))
                    .append(HEXES.charAt((b & 0x0F)));
        }
        return hex.toString();
    }

    public static byte[] hex2Byte(String hex) {
        return new BigInteger(hex,16).toByteArray();
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

    public static void onSortingCtkGrpcProposals(ArrayList<shentu.gov.v1alpha1.Gov.Proposal> proposals) {
        Collections.sort(proposals, new Comparator<shentu.gov.v1alpha1.Gov.Proposal>() {
            @Override
            public int compare(shentu.gov.v1alpha1.Gov.Proposal o1, shentu.gov.v1alpha1.Gov.Proposal o2) {
                if (o1.getProposalId() < o2.getProposalId()) return 1;
                else if (o1.getProposalId() > o2.getProposalId()) return -1;
                return 0;
            }
        });
    }

    public static void onSortingDenom(ArrayList<String> denom, BaseChain chain) {
        Collections.sort(denom, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if(o1.equals(WDp.mainDenom(chain))) return -1;
                if(o2.equals(WDp.mainDenom(chain))) return 1;

                if (chain.equals(KAVA_MAIN)) {
                    if(o1.equals(TOKEN_HARD)) return -1;
                    if(o2.equals(TOKEN_HARD)) return 1;

                }
                return 0;
            }
        });
    }

    public static void onSortingNativeCoins(ArrayList<Balance> balances, final BaseChain chain) {
        Collections.sort(balances, new Comparator<Balance>() {
            @Override
            public int compare(Balance o1, Balance o2) {
                if(o1.symbol.equals(WDp.mainDenom(chain))) return -1;
                if(o2.symbol.equals(WDp.mainDenom(chain))) return 1;

                if (chain.equals(KAVA_MAIN) || chain.equals(KAVA_TEST)) {
                    if(o1.symbol.equals(TOKEN_HARD)) return -1;
                    if(o2.symbol.equals(TOKEN_HARD)) return 1;

                } else if (chain.equals(OKEX_MAIN) || chain.equals(OK_TEST)) {
                    if (o1.symbol.equals("okb-c4d")) return -1;
                    if (o2.symbol.equals("okb-c4d")) return 1;
                }
                return o1.symbol.compareTo(o2.symbol);
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

    public static void onSortingOsmosisPool(ArrayList<Coin> coins) {
        Collections.sort(coins, new Comparator<Coin>() {
            @Override
            public int compare(Coin o1, Coin o2) {
                if (o1.osmosisAmmPoolId() < o2.osmosisAmmPoolId()) return -1;
                else if (o1.osmosisAmmPoolId() > o2.osmosisAmmPoolId()) return 1;
                return 0;
            }
        });
    }

    public static void onSortingGravityPool(ArrayList<Coin> coins, BaseData baseData) {
        Collections.sort(coins, new Comparator<Coin>() {
            @Override
            public int compare(Coin o1, Coin o2) {
                long id1 = baseData.getGravityPoolByDenom(o1.denom).getId();
                long id2 = baseData.getGravityPoolByDenom(o2.denom).getId();
                return id1 < id2 ?  -1 : 1;
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

    public static ArrayList<UnbondingInfo.DpEntry> onSortUnbondingsRecent_Grpc(Context c, ArrayList<Staking.UnbondingDelegation> unbondingGrpcInfos) {
        ArrayList<UnbondingInfo.DpEntry> result = new ArrayList<>();
        for (Staking.UnbondingDelegation unbondingGrpcInfo: unbondingGrpcInfos) {
            for (Staking.UnbondingDelegationEntry entry: unbondingGrpcInfo.getEntriesList()) {
                result.add(new UnbondingInfo.DpEntry(unbondingGrpcInfo.getValidatorAddress(), String.valueOf(entry.getCompletionTime().getSeconds()), entry.getBalance()));
            }
        }

        Collections.sort(result, new Comparator<UnbondingInfo.DpEntry>() {
            @Override
            public int compare(UnbondingInfo.DpEntry o1, UnbondingInfo.DpEntry o2) {
                return Long.parseLong(o1.completion_time) < Long.parseLong(o2.completion_time) ?  -1 : 1;
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

    /**
     * @memo size
     */
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

    /**
     * token price
     */
    public static String marketPrice(BaseChain basechain, BaseData basedata) {
        String result = "usdt";
        if (isGRPC(basechain)) {
            result = result + "," + WDp.mainDenom(basechain);
            for (IbcToken ibcToken: basedata.mIbcTokens) {
                if (ibcToken.auth) {
                    result = result + "," + ibcToken.base_denom;
                }
            }

        }

        if (basechain.equals(OSMOSIS_MAIN)) {
            result = result + ",uion";

        } else if (basechain.equals(SIF_MAIN)) {
            result = result + ",rowan";
            for (Coin coin: basedata.mGrpcBalance) {
                if (coin.denom != WDp.mainDenom(basechain) && coin.denom.startsWith("c")) {
                    result = result + "," + coin.denom.substring(1);
                }
            }

        }
        else if (basechain.equals(BNB_MAIN) || basechain.equals(BNB_TEST)) {
            result = result + ",bnb";

        } else if (basechain.equals(KAVA_MAIN)) {
            result = result + ",ukava,hard,swp,usdx,bnb,xrp,busd,btc";

        } else if (basechain.equals(OKEX_MAIN) || basechain.equals(OK_TEST)) {
            result = result + ",okb,okt";

        } else if (basechain.equals(SECRET_MAIN)) {
            result = result + ",uscrt";

        } else if (basechain.equals(KI_MAIN)) {
            result = result + ",uxki";

        }
        return result;
    }

    /**
     * coin decimal
     */
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
        } else if (coin.denom.equalsIgnoreCase("swp")) {
            return 6;
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
        } else if (denom.equalsIgnoreCase("swp")) {
            return 6;
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

    public static int getCosmosCoinDecimal(BaseData baseData, String denom) {
        if (denom.equalsIgnoreCase(TOKEN_ATOM)) { return 6; }
        else if (denom.startsWith("pool")) {
            Liquidity.Pool poolInfo = baseData.getGravityPoolByDenom(denom);
            if (poolInfo != null) { return 6; }
        } else if (denom.startsWith("ibc/")) {
            IbcToken ibcToken = baseData.getIbcToken(denom.replaceAll("ibc/", ""));
            if (ibcToken.auth == true) { return ibcToken.decimal; }
        }
        return 6;
    }

    public static int getOsmosisCoinDecimal(String denom) {
        if (denom.equalsIgnoreCase(TOKEN_OSMOSIS)) { return 6; }
        else if (denom.equalsIgnoreCase(TOKEN_ION)) { return 6; }
        else if (denom.startsWith("gamm/pool/")) { return 18; }
        else if (denom.startsWith("ibc/")) {
            IbcToken ibcToken = BaseData.getIbcToken(denom.replaceAll("ibc/", ""));
            if (ibcToken.auth == true) { return ibcToken.decimal; }
        }
        return 6;
    }

    public static int getIbcDecimal(String denom) {
        IbcToken ibcToken = BaseData.getIbcToken(denom.replaceAll("ibc/", ""));
        if (ibcToken.auth) { return ibcToken.decimal; }
        else { return 6; }
    }

    /**
     * Token Name
     */
    public static String dpCosmosTokenName(BaseData baseData, String denom) {
        if (denom.equals(TOKEN_ATOM)) {
            return "ATOM";

        } else if (denom.startsWith("pool")) {
            Liquidity.Pool poolInfo = baseData.getGravityPoolByDenom(denom);
            if (poolInfo != null) {
                return "GDEX-" + poolInfo.getId();
            } else {
                return "UnKnown";
            }

        } else if (denom.startsWith("ibc/")) {
            IbcToken ibcToken = BaseData.getIbcToken(denom.replaceAll("ibc/", ""));
            if (ibcToken.auth == true) {
                return ibcToken.display_denom.toUpperCase();
            } else {
                return "UnKnown";
            }
        }
        return denom;
    }

    public static String dpCosmosTokenName(Context c, BaseData baseData, TextView textView, String denom) {
        if (denom.equals(TOKEN_ATOM)) {
            textView.setTextColor(c.getResources().getColor(R.color.colorAtom));
            textView.setText("ATOM");

        } else if (denom.startsWith("pool")) {
            textView.setTextColor(c.getResources().getColor(R.color.colorWhite));
            Liquidity.Pool poolInfo = baseData.getGravityPoolByDenom(denom);
            if (poolInfo != null) {
                textView.setText("GDEX-" + poolInfo.getId());
            } else {
                textView.setText("UnKnown");
            }

        } else if (denom.startsWith("ibc/")) {
            textView.setTextColor(c.getResources().getColor(R.color.colorWhite));
            IbcToken ibcToken = BaseData.getIbcToken(denom.replaceAll("ibc/", ""));
            if (ibcToken.auth == true) {
                textView.setText(ibcToken.display_denom.toUpperCase());
            } else {
                textView.setText("UnKnown");
            }

        } else {
            textView.setTextColor(c.getResources().getColor(R.color.colorWhite));
            textView.setText("UnKnown");
        }
        return denom;
    }

    public static String dpKavaTokenName(Context c, TextView textView, String denom) {
        if (denom.equalsIgnoreCase(TOKEN_KAVA)) {
            textView.setTextColor(c.getResources().getColor(R.color.colorKava));
            textView.setText(R.string.str_kava_c);
        } else if (denom.equalsIgnoreCase(TOKEN_HARD)) {
            textView.setTextColor(c.getResources().getColor(R.color.colorHard));
            textView.setText("HARD");
        } else if (denom.equalsIgnoreCase(TOKEN_USDX)) {
            textView.setTextColor(c.getResources().getColor(R.color.colorUsdx));
            textView.setText("USDX");
        } else if (denom.equalsIgnoreCase(TOKEN_SWP)) {
            textView.setTextColor(c.getResources().getColor(R.color.colorSwp));
            textView.setText("SWP");
        } else if (denom.equalsIgnoreCase(TOKEN_HTLC_KAVA_BNB)) {
            textView.setTextColor(c.getResources().getColor(R.color.colorWhite));
            textView.setText("BNB");
        } else if (denom.equalsIgnoreCase(TOKEN_HTLC_KAVA_XRPB) || denom.equalsIgnoreCase("xrbp")) {
            textView.setTextColor(c.getResources().getColor(R.color.colorWhite));
            textView.setText("XRPB");
        } else if (denom.equalsIgnoreCase(TOKEN_HTLC_KAVA_BUSD)) {
            textView.setTextColor(c.getResources().getColor(R.color.colorWhite));
            textView.setText("BUSD");
        } else if (denom.contains("btc")) {
            textView.setTextColor(c.getResources().getColor(R.color.colorWhite));
            textView.setText("BTCB");
        }
        return denom;
    }

    public static String dpOsmosisTokenName(String denom) {
        if (denom.equals(TOKEN_OSMOSIS)) {
            return "OSMO";

        } else if (denom.equals(TOKEN_ION)) {
            return "ION";

        } else if (denom.startsWith("gamm/pool/")) {
            String[] split = denom.split("/");
            return "GAMM-" + split[split.length - 1];

        } else if (denom.startsWith("ibc/")) {
            IbcToken ibcToken = BaseData.getIbcToken(denom.replaceAll("ibc/", ""));
            if (ibcToken.auth == true) {
                return ibcToken.display_denom.toUpperCase();
            } else {
                return "UnKnown";
            }
        }
        return denom;
    }

    public static String dpOsmosisTokenName(Context c, TextView textView, String denom) {
        if (denom.equals(TOKEN_OSMOSIS)) {
            textView.setTextColor(c.getResources().getColor(R.color.colorOsmosis));
            textView.setText("OSMO");

        } else if (denom.equals(TOKEN_ION)) {
            textView.setTextColor(c.getResources().getColor(R.color.colorIon));
            textView.setText("ION");

        } else if (denom.startsWith("gamm/pool/")) {
            textView.setTextColor(c.getResources().getColor(R.color.colorWhite));
            String[] split = denom.split("/");
            textView.setText("GAMM-" + split[split.length - 1]);

        } else if (denom.startsWith("ibc/")) {
            textView.setTextColor(c.getResources().getColor(R.color.colorWhite));
            IbcToken ibcToken = BaseData.getIbcToken(denom.replaceAll("ibc/", ""));
            if (ibcToken.auth == true) {
                textView.setText(ibcToken.display_denom.toUpperCase());
            } else {
                textView.setText("UnKnown");
            }
        }
        return denom;
    }

    /**
     * Token Img
     */
    public static void DpCosmosTokenImg(BaseData baseData, ImageView imageView, String denom) {
        if (denom.equalsIgnoreCase(TOKEN_ATOM)) {
            Picasso.get().cancelRequest(imageView);
            imageView.setImageResource(R.drawable.atom_ic);
        } else if (denom.startsWith("pool")) {
            Liquidity.Pool poolInfo = baseData.getGravityPoolByDenom(denom);
            if (poolInfo != null) {
                imageView.setImageResource(R.drawable.token_gravitydex);
            }
        } else if (denom.startsWith("ibc/")) {
            IbcToken ibcToken = BaseData.getIbcToken(denom.replaceAll("ibc/", ""));
            try {
                Picasso.get().load(ibcToken.moniker).fit().placeholder(R.drawable.token_default_ibc).error(R.drawable.token_default_ibc).into(imageView);
            } catch (Exception e){}
        }
    }

    public static void DpOsmosisTokenImg(ImageView imageView, String denom) {
        if (denom.equalsIgnoreCase(TOKEN_OSMOSIS)) {
            Picasso.get().cancelRequest(imageView);
            imageView.setImageResource(R.drawable.token_osmosis);
        } else if (denom.equalsIgnoreCase(TOKEN_ION)) {
            imageView.setImageResource(R.drawable.token_ion);
        } else if (denom.startsWith("gamm/pool/")) {
            imageView.setImageResource(R.drawable.token_pool);
        } else if (denom.startsWith("ibc/")) {
            IbcToken ibcToken = BaseData.getIbcToken(denom.replaceAll("ibc/", ""));
            try {
                Picasso.get().load(ibcToken.moniker).fit().placeholder(R.drawable.token_default_ibc).error(R.drawable.token_default_ibc).into(imageView);
            } catch (Exception e){}
        }
    }

    // cosmos gravity dex
    public static GDexManager getGDexManager(BaseData baseData, String address) {
        for (GDexManager gDexManager: baseData.mGDexManager) {
            if (gDexManager.address.equalsIgnoreCase(address)) {
                return gDexManager;
            }
        }
        return null;
    }

    public static BigDecimal getLpAmount (BaseData baseData, String address, String denom) {
        BigDecimal result = BigDecimal.ZERO;
        if (getGDexManager(baseData, address) != null) {
            for (Coin coin: getGDexManager(baseData, address).reserve) {
                if (coin.denom.equalsIgnoreCase(denom)) {
                    result = new BigDecimal(coin.amount);
                }
            }
        }
        return result;
    }

    /**
     * About Osmosis
     */
    public static BigDecimal getMyShareLpAmount(BaseData baseData, PoolOuterClass.Pool pool, String denom) {
        BigDecimal result = BigDecimal.ZERO;
        BigDecimal myShare = baseData.getAvailable("gamm/pool/" + pool.getId());
        String totalLpCoin = "";
        if (pool.getPoolAssets(0).getToken().getDenom().equalsIgnoreCase(denom)) {
            totalLpCoin = pool.getPoolAssets(0).getToken().getAmount();
        } else {
            totalLpCoin = pool.getPoolAssets(1).getToken().getAmount();
        }
        result = new BigDecimal(totalLpCoin).multiply(myShare).divide(new BigDecimal(pool.getTotalShares().getAmount()),18, RoundingMode.DOWN);
        return result;
    }

    public static ArrayList<GaugeOuterClass.Gauge> getGaugesByPoolId(long poolId, ArrayList<QueryOuterClass.IncentivizedPool> incentivizedPools, ArrayList<GaugeOuterClass.Gauge> allGauges) {
        ArrayList<Long> gaugeIds = new ArrayList<Long>();
        ArrayList<GaugeOuterClass.Gauge> result = new ArrayList<GaugeOuterClass.Gauge>();
        for (QueryOuterClass.IncentivizedPool pool: incentivizedPools) {
            if (pool.getPoolId() == poolId) {
                gaugeIds.add(pool.getGaugeId());
            }
        }
        for (GaugeOuterClass.Gauge gauge: allGauges) {
            if (gaugeIds.contains(gauge.getId())) {
                result.add(gauge);
            }
        }
        return result;
    }

    public static ArrayList<Lock.PeriodLock> getLockupByPoolId(long poolId, ArrayList<Lock.PeriodLock> lockups) {
        ArrayList<Lock.PeriodLock> result = new ArrayList<Lock.PeriodLock>();
        for (Lock.PeriodLock lockup: lockups) {
            Coin lpCoin = new Coin(lockup.getCoins(0).getDenom(), lockup.getCoins(0).getAmount());
            if (lpCoin.osmosisAmmPoolId() == poolId) {
                result.add(lockup);
            }
        }
        return result;
    }

//    public static BigDecimal getCosmosLpTokenPerUsdPrice(BaseData baseData, BigDecimal coin0Amount, BigDecimal coin1Amount) {
//        BigDecimal totalShare = coin0Amount.add(coin1Amount).movePointLeft(18).setScale(18, RoundingMode.DOWN);
//        return getPoolValue(baseData, pool).divide(totalShare, 18, RoundingMode.DOWN);
//    }

    public static BigDecimal getOsmoLpTokenPerUsdPrice(BaseData baseData, PoolOuterClass.Pool pool) {
        BigDecimal totalShare = (new BigDecimal(pool.getTotalShares().getAmount())).movePointLeft(18).setScale(18, RoundingMode.DOWN);
        return getPoolValue(baseData, pool).divide(totalShare, 18, RoundingMode.DOWN);
    }

    public static BigDecimal getPoolValue(BaseData baseData, PoolOuterClass.Pool pool) {
        Coin coin0 = new Coin(pool.getPoolAssets(0).getToken().getDenom(), pool.getPoolAssets(0).getToken().getAmount());
        Coin coin1 = new Coin(pool.getPoolAssets(1).getToken().getDenom(), pool.getPoolAssets(1).getToken().getAmount());
        BigDecimal coin0Value = WDp.usdValue(baseData, baseData.getBaseDenom(coin0.denom), new BigDecimal(coin0.amount), WUtil.getOsmosisCoinDecimal(coin0.denom));
        BigDecimal coin1Value = WDp.usdValue(baseData, baseData.getBaseDenom(coin1.denom), new BigDecimal(coin1.amount), WUtil.getOsmosisCoinDecimal(coin1.denom));
        return coin0Value.add(coin1Value);
    }

    public static BigDecimal getNextIncentiveAmount(ArrayList<GaugeOuterClass.Gauge> gauges, int position) {
        if (gauges.size() != 3) { return BigDecimal.ZERO; }
        BigDecimal incentive1Day = BigDecimal.ZERO;
        BigDecimal incentive7Day = BigDecimal.ZERO;
        BigDecimal incentive14Day = BigDecimal.ZERO;
        if (gauges.get(0).getDistributedCoinsCount() == 0) { return BigDecimal.ZERO; }
        else {
            incentive1Day = (new BigDecimal(gauges.get(0).getCoins(0).getAmount())).subtract(new BigDecimal(gauges.get(0).getDistributedCoins(0).getAmount()));
        }
        if (gauges.get(1).getDistributedCoinsCount() == 0) { return BigDecimal.ZERO; }
        else {
            incentive7Day = (new BigDecimal(gauges.get(1).getCoins(0).getAmount())).subtract(new BigDecimal(gauges.get(1).getDistributedCoins(0).getAmount()));
        }
        if (gauges.get(2).getDistributedCoinsCount() == 0) { return BigDecimal.ZERO; }
        else {
            incentive14Day = (new BigDecimal(gauges.get(2).getCoins(0).getAmount())).subtract(new BigDecimal(gauges.get(2).getDistributedCoins(0).getAmount()));
        }
        if (position == 0) {
            return incentive1Day;
        } else if ( position == 1) {
            return incentive1Day.add(incentive7Day);
        } else {
            return incentive1Day.add(incentive7Day).add(incentive14Day);
        }
    }

    public static BigDecimal getPoolArp(BaseData baseData, PoolOuterClass.Pool pool, ArrayList<GaugeOuterClass.Gauge> gauges, int position) {
        BigDecimal poolValue = getPoolValue(baseData, pool);
        BigDecimal incentiveAmount = getNextIncentiveAmount(gauges, position);
        BigDecimal incentiveValue = WDp.usdValue(baseData, baseData.getBaseDenom(TOKEN_OSMOSIS), incentiveAmount, WUtil.getOsmosisCoinDecimal(TOKEN_OSMOSIS));
        return incentiveValue.multiply(new BigDecimal("36500")).divide(poolValue, 12, RoundingMode.DOWN);
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

    public static BigDecimal getBnbTokenUserCurrencyPrice(BaseData baseData, String denom) {
        BigDecimal result = BigDecimal.ZERO;
        for (BnbTicker ticker: baseData.mBnbTickers) {
            if (ticker.symbol.equals(getBnbTicSymbol(denom))) {
                if (isBnbBaseMarketToken(denom)) {
                    BigDecimal perPrice = BigDecimal.ONE.divide(new BigDecimal(ticker.lastPrice), 8, RoundingMode.DOWN);
                    return perPrice.multiply(WDp.perUserCurrencyValue(baseData, TOKEN_BNB));
                } else {
                    BigDecimal perPrice = BigDecimal.ONE.multiply(new BigDecimal(ticker.lastPrice)).setScale(8, RoundingMode.DOWN);;
                    return perPrice.multiply(WDp.perUserCurrencyValue(baseData, TOKEN_BNB));
                }
            }
        }
        return result;
    }

    public static SpannableString dpBnbTokenUserCurrencyPrice(BaseData baseData, String denom) {
        final String formatted = baseData.getCurrencySymbol()  + " " + WDp.getDecimalFormat(3).format(getBnbTokenUserCurrencyPrice(baseData, denom));
        return WDp.getDpString(formatted, 3);
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

    public static int getVoterTypeCnt_CtkgRPC(ArrayList<shentu.gov.v1alpha1.Gov.Vote> votes, Gov.VoteOption option) {
        int result = 0;
        if (votes == null) {
            return result;
        }
        for (shentu.gov.v1alpha1.Gov.Vote v:votes) {
            if (v.getDeposit().getOption().equals(option)) {
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

    public static String checkStarnameWithResource(BaseChain chain, List<Types.Resource> resources) {
        for (Types.Resource resource: resources) {
            if (WDp.isValidChainAddress(chain, resource.getResource())) {
                return resource.getResource();
            }
        }
        return "";

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

    /**
     * Real Block Time
     */
    public static BigDecimal getRealBlockTime(BaseChain chain) {
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

        } else if (chain.equals(SENTINEL_MAIN)) {
            return BLOCK_TIME_SENTINEL;

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

        } else if (chain.equals(MEDI_MAIN)) {
            return BLOCK_TIME_MEDI;

        }
        return BigDecimal.ZERO;
    }

    public static BigDecimal getRealBlockPerYear(BaseChain chain) {
        if (getRealBlockTime(chain) == BigDecimal.ZERO) {
            return BigDecimal.ZERO;
        }
        return YEAR_SEC.divide(getRealBlockTime(chain), 2, RoundingMode.DOWN);
    }

    /**
     * coingeko
     */
    public static Intent getCoingekoIntent(MainActivity mainActivity) {
        if (mainActivity.mBaseChain.equals(COSMOS_MAIN)) {
            mainActivity.startActivity(new Intent(Intent.ACTION_VIEW , Uri.parse("https://www.coingecko.com/en/coins/cosmos")));

        } else if (mainActivity.mBaseChain.equals(IRIS_MAIN)) {
            mainActivity.startActivity(new Intent(Intent.ACTION_VIEW , Uri.parse("https://www.coingecko.com/en/coins/irisnet")));

        } else if (mainActivity.mBaseChain.equals(IOV_MAIN)) {
            mainActivity.startActivity(new Intent(Intent.ACTION_VIEW , Uri.parse("https://www.coingecko.com/en/coins/starname")));

        } else if (mainActivity.mBaseChain.equals(BNB_MAIN)) {
            mainActivity.startActivity(new Intent(Intent.ACTION_VIEW , Uri.parse("https://www.coingecko.com/en/coins/binancecoin")));

        } else if (mainActivity.mBaseChain.equals(KAVA_MAIN)) {
            mainActivity.startActivity(new Intent(Intent.ACTION_VIEW , Uri.parse("https://www.coingecko.com/en/coins/kava")));

        } else if (mainActivity.mBaseChain.equals(BAND_MAIN)) {
            mainActivity.startActivity(new Intent(Intent.ACTION_VIEW , Uri.parse("https://www.coingecko.com/en/coins/band-protocol")));

        } else if (mainActivity.mBaseChain.equals(CERTIK_MAIN)) {
            mainActivity.startActivity(new Intent(Intent.ACTION_VIEW , Uri.parse("https://www.coingecko.com/en/coins/certik")));

        } else if (mainActivity.mBaseChain.equals(SECRET_MAIN)) {
            mainActivity.startActivity(new Intent(Intent.ACTION_VIEW , Uri.parse("https://www.coingecko.com/en/coins/secret")));

        } else if (mainActivity.mBaseChain.equals(AKASH_MAIN)) {
            mainActivity.startActivity(new Intent(Intent.ACTION_VIEW , Uri.parse("https://www.coingecko.com/en/coins/akash-network")));

        } else if (mainActivity.mBaseChain.equals(OKEX_MAIN)) {
            mainActivity.startActivity(new Intent(Intent.ACTION_VIEW , Uri.parse("https://www.coingecko.com/en/coins/okexchain")));

        } else if (mainActivity.mBaseChain.equals(SENTINEL_MAIN)) {
            mainActivity.startActivity(new Intent(Intent.ACTION_VIEW , Uri.parse("https://www.coingecko.com/en/coins/sentinel")));

        } else if (mainActivity.mBaseChain.equals(PERSIS_MAIN)) {
            mainActivity.startActivity(new Intent(Intent.ACTION_VIEW , Uri.parse("https://www.coingecko.com/en/coins/persistence")));

        } else if (mainActivity.mBaseChain.equals(FETCHAI_MAIN)) {
            mainActivity.startActivity(new Intent(Intent.ACTION_VIEW , Uri.parse("https://www.coingecko.com/en/coins/fetch-ai")));

        } else if (mainActivity.mBaseChain.equals(CRYPTO_MAIN)) {
            mainActivity.startActivity(new Intent(Intent.ACTION_VIEW , Uri.parse("https://www.coingecko.com/en/coins/crypto-com-chain")));

        } else if (mainActivity.mBaseChain.equals(SIF_MAIN)) {
            mainActivity.startActivity(new Intent(Intent.ACTION_VIEW , Uri.parse("https://www.coingecko.com/en/coins/sifchain")));

        } else if (mainActivity.mBaseChain.equals(KI_MAIN)) {
            mainActivity.startActivity(new Intent(Intent.ACTION_VIEW , Uri.parse("https://www.coingecko.com/en/coins/ki")));

        } else if (mainActivity.mBaseChain.equals(OSMOSIS_MAIN)) {
            mainActivity.startActivity(new Intent(Intent.ACTION_VIEW , Uri.parse("https://www.coingecko.com/en/coins/osmosis")));

        } else if (mainActivity.mBaseChain.equals(MEDI_MAIN)) {
            mainActivity.startActivity(new Intent(Intent.ACTION_VIEW , Uri.parse("https://www.coingecko.com/en/coins/medibloc")));

        } else if (mainActivity.mBaseChain.equals(EMONEY_MAIN)) {
            mainActivity.startActivity(new Intent(Intent.ACTION_VIEW , Uri.parse("https://www.coingecko.com/en/coins/e-money")));
        }
        return null;
    }

    /**
     * Main Guide
     */
    public static void getGuide(MainActivity mainActivity, ImageView guideImg, TextView guideTitle, TextView guideMsg, Button guideBtn1, Button guideBtn2) {
        if (mainActivity.mBaseChain.equals(COSMOS_MAIN) || mainActivity.mBaseChain.equals(COSMOS_TEST)) {
            guideImg.setImageDrawable(mainActivity.getResources().getDrawable(R.drawable.guide_img));
            guideTitle.setText(R.string.str_front_guide_title);
            guideMsg.setText(R.string.str_front_guide_msg);
            guideBtn1.setText(R.string.str_guide);
            guideBtn2.setText(R.string.str_faq);
        
        } else if (mainActivity.mBaseChain.equals(IRIS_MAIN) || mainActivity.mBaseChain.equals(IRIS_TEST)) {
            guideImg.setImageDrawable(mainActivity.getResources().getDrawable(R.drawable.irisnet_img));
            guideTitle.setText(R.string.str_front_guide_title_iris);
            guideMsg.setText(R.string.str_front_guide_msg_iris);
            guideBtn1.setText(R.string.str_faq_iris);
            guideBtn2.setText(R.string.str_guide_iris);

        } else if (mainActivity.mBaseChain.equals(BNB_MAIN) || mainActivity.mBaseChain.equals(BNB_TEST)) {
            guideImg.setImageDrawable(mainActivity.getResources().getDrawable(R.drawable.binance_img));
            guideTitle.setText(R.string.str_front_guide_title_binance);
            guideMsg.setText(R.string.str_front_guide_msg_bnb);
            guideBtn1.setText(R.string.str_faq_bnb);
            guideBtn2.setText(R.string.str_guide_bnb);

        } else if (mainActivity.mBaseChain.equals(KAVA_MAIN)) {
            guideImg.setImageDrawable(mainActivity.getResources().getDrawable(R.drawable.kavamain_img));
            guideTitle.setText(R.string.str_front_guide_title_kava);
            guideMsg.setText(R.string.str_front_guide_msg_kava);
            guideBtn1.setText(R.string.str_faq_kava);
            guideBtn2.setText(R.string.str_guide_kava);

        } else if (mainActivity.mBaseChain.equals(IOV_MAIN) || mainActivity.mBaseChain.equals(IOV_TEST)) {
            guideImg.setImageDrawable(mainActivity.getResources().getDrawable(R.drawable.iov_img));
            guideTitle.setText(R.string.str_front_guide_title_iov);
            guideMsg.setText(R.string.str_front_guide_msg_iov);
            guideBtn1.setText(R.string.str_faq_iov);
            guideBtn2.setText(R.string.str_guide_iov);

        } else if (mainActivity.mBaseChain.equals(BAND_MAIN)) {
            guideImg.setImageDrawable(mainActivity.getResources().getDrawable(R.drawable.infoicon_bandprotocol));
            guideTitle.setText(R.string.str_front_guide_title_band);
            guideMsg.setText(R.string.str_front_guide_msg_band);
            guideBtn1.setText(R.string.str_faq_band);
            guideBtn2.setText(R.string.str_guide_band);

        } else if (mainActivity.mBaseChain.equals(OKEX_MAIN) || mainActivity.mBaseChain.equals(OK_TEST)) {
            guideImg.setImageDrawable(mainActivity.getResources().getDrawable(R.drawable.okex_img));
            guideTitle.setText(R.string.str_front_guide_title_ok);
            guideMsg.setText(R.string.str_front_guide_msg_ok);
            guideBtn1.setText(R.string.str_faq_ok);
            guideBtn2.setText(R.string.str_guide_ok);

        } else if (mainActivity.mBaseChain.equals(CERTIK_MAIN) || mainActivity.mBaseChain.equals(CERTIK_TEST)) {
            guideImg.setImageDrawable(mainActivity.getResources().getDrawable(R.drawable.certik_img));
            guideTitle.setText(R.string.str_front_guide_title_certik);
            guideMsg.setText(R.string.str_front_guide_msg_certik);
            guideBtn1.setText(R.string.str_faq_certik);
            guideBtn2.setText(R.string.str_guide_certik);

        } else if (mainActivity.mBaseChain.equals(AKASH_MAIN)) {
            guideImg.setImageDrawable(mainActivity.getResources().getDrawable(R.drawable.akash_img));
            guideTitle.setText(R.string.str_front_guide_title_akash);
            guideMsg.setText(R.string.str_front_guide_msg_akash);
            guideBtn1.setText(R.string.str_faq_akash);
            guideBtn2.setText(R.string.str_guide_akash);

        } else if (mainActivity.mBaseChain.equals(SECRET_MAIN)) {
            guideImg.setImageDrawable(mainActivity.getResources().getDrawable(R.drawable.secret_img));
            guideTitle.setText(R.string.str_front_guide_title_secret);
            guideMsg.setText(R.string.str_front_guide_msg_secret);
            guideBtn1.setText(R.string.str_faq_secret);
            guideBtn2.setText(R.string.str_guide_secret);

        } else if (mainActivity.mBaseChain.equals(PERSIS_MAIN)) {
            guideImg.setImageDrawable(mainActivity.getResources().getDrawable(R.drawable.persistence_img));
            guideTitle.setText(R.string.str_front_guide_title_persis);
            guideMsg.setText(R.string.str_front_guide_msg_persis);
            guideBtn1.setText(R.string.str_faq_persis);
            guideBtn2.setText(R.string.str_guide_persis);

        } else if (mainActivity.mBaseChain.equals(SENTINEL_MAIN)) {
            guideImg.setImageDrawable(mainActivity.getResources().getDrawable(R.drawable.sentinel_img));
            guideTitle.setText(R.string.str_front_guide_title_sentinel);
            guideMsg.setText(R.string.str_front_guide_msg_sentinel);
            guideBtn1.setText(R.string.str_faq_sentinel);
            guideBtn2.setText(R.string.str_guide_sentinel);

        } else if (mainActivity.mBaseChain.equals(FETCHAI_MAIN)) {
            guideImg.setImageDrawable(mainActivity.getResources().getDrawable(R.drawable.fetchai_img));
            guideTitle.setText(R.string.str_front_guide_title_fetch);
            guideMsg.setText(R.string.str_front_guide_msg_fetch);
            guideBtn1.setText(R.string.str_faq_fetch);
            guideBtn2.setText(R.string.str_guide_fetch);

        } else if (mainActivity.mBaseChain.equals(CRYPTO_MAIN)) {
            guideImg.setImageDrawable(mainActivity.getResources().getDrawable(R.drawable.cryptochain_img));
            guideTitle.setText(R.string.str_front_guide_title_crypto);
            guideMsg.setText(R.string.str_front_guide_msg_crypto);
            guideBtn1.setText(R.string.str_faq_crypto);
            guideBtn2.setText(R.string.str_guide_crypto);

        } else if (mainActivity.mBaseChain.equals(SIF_MAIN)) {
            guideImg.setImageDrawable(mainActivity.getResources().getDrawable(R.drawable.sifchain_img));
            guideTitle.setText(R.string.str_front_guide_title_sif);
            guideMsg.setText(R.string.str_front_guide_msg_sif);
            guideBtn1.setText(R.string.str_faq_sif);
            guideBtn2.setText(R.string.str_guide_sif);

        } else if (mainActivity.mBaseChain.equals(KI_MAIN)) {
            guideImg.setImageDrawable(mainActivity.getResources().getDrawable(R.drawable.kifoundation_img));
            guideTitle.setText(R.string.str_front_guide_title_ki);
            guideMsg.setText(R.string.str_front_guide_msg_ki);
            guideBtn1.setText(R.string.str_faq_ki);
            guideBtn2.setText(R.string.str_guide_ki);

        } else if (mainActivity.mBaseChain.equals(OSMOSIS_MAIN)) {
            guideImg.setImageDrawable(mainActivity.getResources().getDrawable(R.drawable.infoicon_osmosis));
            guideTitle.setText(R.string.str_front_guide_title_osmosis);
            guideMsg.setText(R.string.str_front_guide_msg_osmosis);
            guideBtn1.setText(R.string.str_faq_osmosis);
            guideBtn2.setText(R.string.str_guide_osmosis);

        } else if (mainActivity.mBaseChain.equals(RIZON_MAIN) || mainActivity.mBaseChain.equals(RIZON_TEST)) {
            guideImg.setImageDrawable(mainActivity.getResources().getDrawable(R.drawable.infoicon_rizon));
            guideTitle.setText(R.string.str_front_guide_title_rizon);
            guideMsg.setText(R.string.str_front_guide_msg_rizon);
            guideBtn1.setText(R.string.str_faq_rizon);
            guideBtn2.setText(R.string.str_guide_rizon);

        } else if (mainActivity.mBaseChain.equals(MEDI_MAIN) || mainActivity.mBaseChain.equals(MEDI_TEST)) {
            guideImg.setImageDrawable(mainActivity.getResources().getDrawable(R.drawable.medibloc_img));
            guideTitle.setText(R.string.str_front_guide_title_medi);
            guideMsg.setText(R.string.str_front_guide_msg_medi);
            guideBtn1.setText(R.string.str_faq_medi);
            guideBtn2.setText(R.string.str_guide_medi);

        } else if (mainActivity.mBaseChain.equals(EMONEY_MAIN)) {
            guideImg.setImageDrawable(mainActivity.getResources().getDrawable(R.drawable.infoicon_emoney));
            guideTitle.setText(R.string.str_front_guide_title_emoney);
            guideMsg.setText(R.string.str_front_guide_msg_emoney);
            guideBtn1.setText(R.string.str_faq_emoney);
            guideBtn2.setText(R.string.str_guide_emoney);

        } else if (mainActivity.mBaseChain.equals(JUNO_MAIN)) {
            guideImg.setImageDrawable(mainActivity.getResources().getDrawable(R.drawable.infoicon_juno));
            guideTitle.setText(R.string.str_front_guide_title_juno);
            guideMsg.setText(R.string.str_front_guide_msg_juno);
            guideBtn1.setText(R.string.str_faq_juno);
            guideBtn2.setText(R.string.str_guide_juno);

        } else if (mainActivity.mBaseChain.equals(ALTHEA_TEST)) {
            guideImg.setImageDrawable(mainActivity.getResources().getDrawable(R.drawable.althea_img));
            guideTitle.setText(R.string.str_front_guide_title_althea);
            guideMsg.setText(R.string.str_front_guide_msg_althea);
            guideBtn1.setText(R.string.str_faq_althea);
            guideBtn2.setText(R.string.str_guide_althea);

        } else if (mainActivity.mBaseChain.equals(UMEE_TEST)) {
            guideImg.setImageDrawable(mainActivity.getResources().getDrawable(R.drawable.infoicon_umee));
            guideTitle.setText(R.string.str_front_guide_title_umee);
            guideMsg.setText(R.string.str_front_guide_msg_umee);
            guideBtn1.setText(R.string.str_faq_umee);
            guideBtn2.setText(R.string.str_guide_umee);

        } else if (mainActivity.mBaseChain.equals(AXELAR_TEST)) {
            guideImg.setImageDrawable(mainActivity.getResources().getDrawable(R.drawable.infoicon_axelar));
            guideTitle.setText(R.string.str_front_guide_title_axelar);
            guideMsg.setText(R.string.str_front_guide_msg_axelar);
            guideBtn1.setText(R.string.str_faq_axelar);
            guideBtn2.setText(R.string.str_guide_axelar);

        } 
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

        } else if (chain.equals(KAVA_MAIN)) {
            return new Intent(Intent.ACTION_VIEW , Uri.parse("https://www.kava.io/registration/"));

        } else if (chain.equals(IOV_MAIN) || chain.equals(IOV_TEST)) {
            return new Intent(Intent.ACTION_VIEW , Uri.parse("https://www.starname.me/"));

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

        } else if (chain.equals(OSMOSIS_MAIN)) {
            return new Intent(Intent.ACTION_VIEW , Uri.parse("https://osmosis.zone/"));

        } else if (chain.equals(MEDI_MAIN) || chain.equals(MEDI_TEST)) {
            if (Locale.getDefault().getLanguage().toLowerCase().equals("ko")) {
                return new Intent(Intent.ACTION_VIEW , Uri.parse("https://medibloc.com"));
            } else {
                return new Intent(Intent.ACTION_VIEW , Uri.parse("https://medibloc.com/en/ "));
            }

        } else if (chain.equals(EMONEY_MAIN)) {
            return new Intent(Intent.ACTION_VIEW , Uri.parse("https://www.e-money.com/"));

        } else if (chain.equals(RIZON_MAIN)) {
            return new Intent(Intent.ACTION_VIEW , Uri.parse("https://www.hdactech.com/en/index.do"));

        } else if (chain.equals(JUNO_MAIN)) {
            return new Intent(Intent.ACTION_VIEW , Uri.parse("https://junochain.com/"));

        } else if (chain.equals(UMEE_TEST)) {
            return new Intent(Intent.ACTION_VIEW , Uri.parse("https://umee.cc/"));

        } else if (chain.equals(AXELAR_TEST)) {
            return new Intent(Intent.ACTION_VIEW , Uri.parse("https://axelar.network/"));
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

        } else if (chain.equals(KAVA_MAIN)) {
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

        } else if (chain.equals(OSMOSIS_MAIN)) {
            return new Intent(Intent.ACTION_VIEW , Uri.parse("https://medium.com/osmosis"));

        } else if (chain.equals(MEDI_MAIN) || chain.equals(MEDI_TEST)) {
            if (Locale.getDefault().getLanguage().toLowerCase().equals("ko")) {
                return new Intent(Intent.ACTION_VIEW , Uri.parse("https://blog.medibloc.org/"));
            } else {
                return new Intent(Intent.ACTION_VIEW , Uri.parse("https://medium.com/medibloc"));
            }

        } else if (chain.equals(EMONEY_MAIN)) {
            return new Intent(Intent.ACTION_VIEW , Uri.parse("https://emoneytokenstandard.org/"));

        } else if (chain.equals(RIZON_MAIN) || chain.equals(RIZON_TEST)) {
            return new Intent(Intent.ACTION_VIEW , Uri.parse("https://medium.com/hdac"));

        } else if (chain.equals(JUNO_MAIN)) {
            return new Intent(Intent.ACTION_VIEW , Uri.parse("https://medium.com/@JunoNetwork"));

        } else if (chain.equals(UMEE_TEST)) {
            return new Intent(Intent.ACTION_VIEW , Uri.parse("https://medium.com/umeeblog"));

        } else if (chain.equals(AXELAR_TEST)) {
            return new Intent(Intent.ACTION_VIEW , Uri.parse("https://axelar.network/blog"));

        }
        return null;
    }

    public static String getExplorer(BaseChain basechain) {
        if (basechain.equals(BNB_MAIN)) {
            return EXPLORER_BINANCE_MAIN;

        } else if (basechain.equals(BNB_TEST)) {
            return EXPLORER_BINANCE_TEST;

        } else if (basechain.equals(KAVA_MAIN)) {
            return EXPLORER_KAVA_MAIN;

        } else if (basechain.equals(OKEX_MAIN)) {
            return EXPLORER_OKEX_MAIN;

        } else if (basechain.equals(OK_TEST)) {
            return EXPLORER_OKEX_TEST;

        } else if (basechain.equals(SECRET_MAIN)) {
            return EXPLORER_SECRET_MAIN;

        } else if (basechain.equals(KI_MAIN)) {
            return EXPLORER_KI_MAIN;

        } else if (basechain.equals(MEDI_TEST)) {
            return EXPLORER_MEDI_TEST;

        }

        else if (basechain.equals(COSMOS_MAIN)) {
            return EXPLORER_COSMOS_MAIN;

        } else if (basechain.equals(IRIS_MAIN)) {
            return EXPLORER_IRIS_MAIN;

        } else if (basechain.equals(AKASH_MAIN)) {
            return EXPLORER_AKASH_MAIN;

        } else if (basechain.equals(SENTINEL_MAIN)) {
            return EXPLORER_SENTINEL_MAIN;

        } else if (basechain.equals(PERSIS_MAIN)) {
            return EXPLORER_PERSIS_MAIN;

        } else if (basechain.equals(CRYPTO_MAIN)) {
            return EXPLORER_CRYPTOORG_MAIN;

        } else if (basechain.equals(OSMOSIS_MAIN)) {
            return EXPLORER_OSMOSIS_MAIN;

        } else if (basechain.equals(IOV_MAIN)) {
            return EXPLORER_IOV_MAIN;

        } else if (basechain.equals(SIF_MAIN)) {
            return EXPLORER_SIF_MAIN;

        } else if (basechain.equals(BAND_MAIN)) {
            return EXPLORER_BAND_MAIN;

        } else if (basechain.equals(MEDI_MAIN)) {
            return EXPLORER_MEDI_MAIN;

        } else if (basechain.equals(CERTIK_MAIN)) {
            return EXPLORER_CERTIK_MAIN;

        } else if (basechain.equals(EMONEY_MAIN)) {
            return EXPLORER_EMONEY_MAIN;

        } else if (basechain.equals(FETCHAI_MAIN)) {
            return EXPLORER_FETCHAI_MAIN;

        } else if (basechain.equals(RIZON_MAIN)) {
            return EXPLORER_RIZON_MAIN;

        } else if (basechain.equals(JUNO_MAIN)) {
            return EXPLORER_JUNO_MAIN;

        }

        else if (basechain.equals(COSMOS_TEST)) {
            return EXPLORER_COSMOS_TEST;

        } else if (basechain.equals(IRIS_TEST)) {
            return EXPLORER_IRIS_TEST;

        } else if (basechain.equals(ALTHEA_TEST)) {
            return EXPLORER_ALTHEA_TEST;

        } else if (basechain.equals(RIZON_TEST)) {
            return EXPLORER_RIZON_TEST;

        } else if (basechain.equals(UMEE_TEST)) {
            return EXPLORER_UMEE_TEST;

        } else if (basechain.equals(AXELAR_TEST)) {
            return EXPLORER_AXELAR_TEST;

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

        } else if (basechain.equals(OKEX_MAIN)) {
            return EXPLORER_OKEX_MAIN + "tx/0x" + hash;

        } else if (basechain.equals(OK_TEST)) {
            return EXPLORER_OKEX_TEST + "tx/" + hash;

        } else if (basechain.equals(SECRET_MAIN)) {
            return EXPLORER_SECRET_MAIN + "transactions/" + hash;

        } else if (basechain.equals(KI_MAIN)) {
            return EXPLORER_KI_MAIN + "txs/" + hash;

        }

        else if (basechain.equals(COSMOS_MAIN)) {
            return EXPLORER_COSMOS_MAIN + "txs/" + hash;

        } else if (basechain.equals(IRIS_MAIN)) {
            return EXPLORER_IRIS_MAIN + "txs/" + hash;

        } else if (basechain.equals(AKASH_MAIN)) {
            return EXPLORER_AKASH_MAIN + "txs/" + hash;

        } else if (basechain.equals(SENTINEL_MAIN)) {
            return EXPLORER_SENTINEL_MAIN + "txs/" + hash;

        } else if (basechain.equals(PERSIS_MAIN)) {
            return EXPLORER_PERSIS_MAIN + "txs/" + hash;

        } else if (basechain.equals(CRYPTO_MAIN)) {
            return EXPLORER_CRYPTOORG_MAIN + "txs/" + hash;

        } else if (basechain.equals(OSMOSIS_MAIN)) {
            return EXPLORER_OSMOSIS_MAIN + "txs/" + hash;

        } else if (basechain.equals(IOV_MAIN)) {
            return EXPLORER_IOV_MAIN + "txs/" + hash;

        } else if (basechain.equals(SIF_MAIN)) {
            return EXPLORER_SIF_MAIN + "txs/" + hash;

        } else if (basechain.equals(BAND_MAIN)) {
            return EXPLORER_BAND_MAIN + "txs/" + hash;

        } else if (basechain.equals(MEDI_MAIN)) {
            return EXPLORER_MEDI_MAIN + "txs/" + hash;

        } else if (basechain.equals(CERTIK_MAIN)) {
            return EXPLORER_CERTIK_MAIN + "txs/" + hash;

        } else if (basechain.equals(EMONEY_MAIN)) {
            return EXPLORER_EMONEY_MAIN + "txs/" + hash;

        } else if (basechain.equals(FETCHAI_MAIN)) {
            return EXPLORER_FETCHAI_MAIN + "txs/" + hash;

        } else if (basechain.equals(RIZON_MAIN)) {
            return EXPLORER_RIZON_MAIN + "txs/" + hash;

        } else if (basechain.equals(JUNO_MAIN)) {
            return EXPLORER_JUNO_MAIN + "txs/" + hash;

        }

        else if (basechain.equals(COSMOS_TEST)) {
            return EXPLORER_COSMOS_TEST + "txs/" + hash;

        } else if (basechain.equals(IRIS_TEST)) {
            return EXPLORER_IRIS_TEST + "txs/" + hash;

        } else if (basechain.equals(RIZON_TEST)) {
            return EXPLORER_RIZON_TEST + "tx/" + hash;

        } else if (basechain.equals(ALTHEA_TEST)) {
            return EXPLORER_ALTHEA_TEST + "txs/" + hash;

        } else if (basechain.equals(MEDI_TEST)) {
            return EXPLORER_MEDI_TEST + "txs/" + hash;

        } else if (basechain.equals(UMEE_TEST)) {
            return EXPLORER_UMEE_TEST + "txs/" + hash;

        } else if (basechain.equals(AXELAR_TEST)) {
            return EXPLORER_AXELAR_TEST + "txs/" + hash;

        }
        return "";
    }

    /**
     * Chain Gas Amount
     */
    public static BigDecimal getEstimateGasAmount(Context c, BaseChain basechain, int txType,  int valCnt) {
        BigDecimal result = BigDecimal.ZERO;
        if (basechain.equals(COSMOS_MAIN) || basechain.equals(IRIS_MAIN) || basechain.equals(AKASH_MAIN) || basechain.equals(PERSIS_MAIN) ||
                basechain.equals(CRYPTO_MAIN) || basechain.equals(EMONEY_MAIN) || basechain.equals(RIZON_MAIN) || basechain.equals(JUNO_MAIN) ||
                basechain.equals(COSMOS_TEST) || basechain.equals(IRIS_TEST) || basechain.equals(RIZON_TEST) || basechain.equals(ALTHEA_TEST) || basechain.equals(UMEE_TEST) || basechain.equals(AXELAR_TEST)) {
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
            } else if (txType == CONST_PW_TX_GDEX_SWAP) {
                return new BigDecimal(COSMOS_GAS_AMOUNT_SWAP);
            } else if (txType == CONST_PW_TX_GDEX_DEPOSIT) {
                return new BigDecimal(COSMOS_GAS_AMOUNT_JOIN_POOL);
            } else if (txType == CONST_PW_TX_GDEX_WITHDRAW) {
                return new BigDecimal(COSMOS_GAS_AMOUNT_EXIT_POOL);
            } else if (txType == CONST_PW_TX_IBC_TRANSFER) {
                return new BigDecimal(COSMOS_GAS_AMOUNT_IBC_SEND);
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
            } else if (txType == CONST_PW_TX_IBC_TRANSFER) {
                return new BigDecimal(IOV_GAS_AMOUNT_IBC_SEND);
            }

        } else if (basechain.equals(OSMOSIS_MAIN)) {
            if (txType == CONST_PW_TX_SIMPLE_SEND) {
                return new BigDecimal(OSMOSIS_GAS_AMOUNT_SEND);
            } else if (txType == CONST_PW_TX_SIMPLE_DELEGATE) {
                return new BigDecimal(OSMOSIS_GAS_AMOUNT_STAKE);
            } else if (txType == CONST_PW_TX_SIMPLE_UNDELEGATE) {
                return new BigDecimal(OSMOSIS_GAS_AMOUNT_STAKE);
            } else if (txType == CONST_PW_TX_SIMPLE_REDELEGATE) {
                return new BigDecimal(OSMOSIS_GAS_AMOUNT_REDELEGATE);
            } else if (txType == CONST_PW_TX_REINVEST) {
                return new BigDecimal(OSMOSIS_GAS_AMOUNT_REINVEST);
            } else if (txType == CONST_PW_TX_SIMPLE_REWARD) {
                ArrayList<String> rewardGasFees = new ArrayList<String>(Arrays.asList(c.getResources().getStringArray(R.array.gas_multi_reward)));
                return new BigDecimal(rewardGasFees.get(valCnt - 1));
            } else if (txType == CONST_PW_TX_SIMPLE_CHANGE_REWARD_ADDRESS) {
                return new BigDecimal(OSMOSIS_GAS_AMOUNT_LOW);
            } else if (txType == CONST_PW_TX_VOTE) {
                return new BigDecimal(OSMOSIS_GAS_AMOUNT_LOW);
            } else if (txType == CONST_PW_TX_OSMOSIS_SWAP) {
                return new BigDecimal(OSMOSIS_GAS_AMOUNT_SWAP);
            } else if (txType == CONST_PW_TX_OSMOSIS_JOIN_POOL || txType == CONST_PW_TX_OSMOSIS_EXIT_POOL) {
                return new BigDecimal(OSMOSIS_GAS_AMOUNT_POOL);
            } else if (txType == CONST_PW_TX_OSMOSIS_EARNING) {
                return new BigDecimal(OSMOSIS_GAS_AMOUNT_LOCK);
            } else if (txType == CONST_PW_TX_OSMOSIS_BEGIN_UNBONDING) {
                return new BigDecimal(OSMOSIS_GAS_AMOUNT_BEGIN_UNBONDING);
            } else if (txType == CONST_PW_TX_OSMOSIS_UNLOCK) {
                return new BigDecimal(OSMOSIS_GAS_AMOUNT_UNLOCK);
            } else if (txType == CONST_PW_TX_IBC_TRANSFER) {
                return new BigDecimal(OSMOSIS_GAS_AMOUNT_IBC_SEND);
            }
        }

        else if (basechain.equals(KAVA_MAIN)) {
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
            } else if (txType == CONST_PW_TX_KAVA_SWAP) {
                return new BigDecimal(KAVA_GAS_AMOUNT_SWAP);
            } else if (txType == CONST_PW_TX_KAVA_JOIN_POOL) {
                return new BigDecimal(KAVA_GAS_AMOUNT_JOIN_POOL);
            } else if (txType == CONST_PW_TX_KAVA_EXIT_POOL) {
                return new BigDecimal(KAVA_GAS_AMOUNT_EXIT_POOL);
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
            } else if (txType == CONST_PW_TX_IBC_TRANSFER) {
                return new BigDecimal(BAND_GAS_AMOUNT_IBC_SEND);
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
            } else if (txType == CONST_PW_TX_IBC_TRANSFER) {
                return new BigDecimal(CERTIK_GAS_AMOUNT_IBC_SEND);
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
                ArrayList<String> rewardGasFees = new ArrayList<String>(Arrays.asList(c.getResources().getStringArray(R.array.gas_multi_reward_kava)));
                return new BigDecimal(rewardGasFees.get(valCnt - 1));
            } else if (txType == CONST_PW_TX_SIMPLE_CHANGE_REWARD_ADDRESS) {
                return new BigDecimal(SENTINEL_GAS_AMOUNT_REWARD_ADDRESS_CHANGE);
            } else if (txType == CONST_PW_TX_VOTE) {
                return new BigDecimal(SENTINEL_GAS_AMOUNT_VOTE);
            } else if (txType == CONST_PW_TX_IBC_TRANSFER) {
                return new BigDecimal(SENTINEL_GAS_AMOUNT_IBC_SEND);
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
            } else if (txType == CONST_PW_TX_IBC_TRANSFER) {
                return new BigDecimal(FETCH_GAS_AMOUNT_IBC_SEND);
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
            } else if (txType == CONST_PW_TX_IBC_TRANSFER) {
                return new BigDecimal(SIF_GAS_AMOUNT_IBC_SEND);
            } else if (txType == CONST_PW_TX_SIF_CLAIM_INCENTIVE) {
                return new BigDecimal(SIF_GAS_AMOUNT_CLAIM_INCENTIVE);
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

        } else if (basechain.equals(MEDI_MAIN) || basechain.equals(MEDI_TEST)) {
            if (txType == CONST_PW_TX_SIMPLE_SEND) {
                return new BigDecimal(MEDI_GAS_AMOUNT_SEND);
            } else if (txType == CONST_PW_TX_SIMPLE_DELEGATE) {
                return new BigDecimal(MEDI_GAS_AMOUNT_STAKE);
            } else if (txType == CONST_PW_TX_SIMPLE_UNDELEGATE) {
                return new BigDecimal(MEDI_GAS_AMOUNT_STAKE);
            } else if (txType == CONST_PW_TX_SIMPLE_REDELEGATE) {
                return new BigDecimal(MEDI_GAS_AMOUNT_REDELEGATE);
            } else if (txType == CONST_PW_TX_REINVEST) {
                return new BigDecimal(MEDI_GAS_AMOUNT_REINVEST);
            } else if (txType == CONST_PW_TX_SIMPLE_REWARD) {
                ArrayList<String> rewardGasFees = new ArrayList<String>(Arrays.asList(c.getResources().getStringArray(R.array.gas_multi_reward)));
                return new BigDecimal(rewardGasFees.get(valCnt - 1));
            } else if (txType == CONST_PW_TX_SIMPLE_CHANGE_REWARD_ADDRESS) {
                return new BigDecimal(MEDI_GAS_AMOUNT_REWARD_ADDRESS_CHANGE);
            } else if (txType == CONST_PW_TX_VOTE) {
                return new BigDecimal(MEDI_GAS_AMOUNT_VOTE);
            } else if (txType == CONST_PW_TX_IBC_TRANSFER) {
                return new BigDecimal(MEDI_GAS_AMOUNT_IBC_SEND);
            }

        }
        return result;
    }

    /**
     *
     * Chain Gas Rate
     */
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

        } else if (basechain.equals(SENTINEL_MAIN)) {
            BigDecimal gasRate = new BigDecimal(SENTINEL_GAS_RATE_AVERAGE);
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

        } else if (basechain.equals(OSMOSIS_MAIN)) {
            BigDecimal gasRate = new BigDecimal(OSMOSIS_GAS_RATE_AVERAGE);
            BigDecimal gasAmount = getEstimateGasAmount(c, basechain, txType, valCnt);
            return gasRate.multiply(gasAmount).setScale(0, RoundingMode.DOWN);

        } else if (basechain.equals(IOV_MAIN) || basechain.equals(IOV_TEST)) {
            BigDecimal gasRate = new BigDecimal(STARNAME_GAS_RATE_AVERAGE);
            BigDecimal gasAmount = getEstimateGasAmount(c, basechain, txType, valCnt);
            return gasRate.multiply(gasAmount).setScale(0, RoundingMode.DOWN);

        } else if (basechain.equals(SIF_MAIN)) {
            BigDecimal gasRate = new BigDecimal(SIF_GAS_RATE_AVERAGE);
            BigDecimal gasAmount = getEstimateGasAmount(c, basechain, txType, valCnt);
            return gasRate.multiply(gasAmount).setScale(0, RoundingMode.DOWN);

        } else if (basechain.equals(CERTIK_MAIN) || basechain.equals(CERTIK_TEST)) {
            BigDecimal gasRate = new BigDecimal(CERTIK_GAS_RATE_AVERAGE);
            BigDecimal gasAmount = getEstimateGasAmount(c, basechain, txType, valCnt);
            return gasRate.multiply(gasAmount).setScale(0, RoundingMode.DOWN);

        } else if (basechain.equals(BAND_MAIN)) {
            BigDecimal gasRate = new BigDecimal(BAND_GAS_RATE_TINY);
            BigDecimal gasAmount = getEstimateGasAmount(c, basechain, txType, valCnt);
            return gasRate.multiply(gasAmount).setScale(0, RoundingMode.DOWN);

        } else if (basechain.equals(MEDI_MAIN) || basechain.equals(MEDI_TEST)) {
            BigDecimal gasRate = new BigDecimal(MEDI_GAS_RATE_AVERAGE);
            BigDecimal gasAmount = getEstimateGasAmount(c, basechain, txType, valCnt);
            return gasRate.multiply(gasAmount).setScale(0, RoundingMode.DOWN);

        } else if (basechain.equals(EMONEY_MAIN)) {
            BigDecimal gasRate = new BigDecimal(EMONEY_GAS_RATE_AVERAGE);
            BigDecimal gasAmount = getEstimateGasAmount(c, basechain, txType, valCnt);
            return gasRate.multiply(gasAmount).setScale(0, RoundingMode.DOWN);

        } else if (basechain.equals(FETCHAI_MAIN)) {
            BigDecimal gasRate = new BigDecimal(FETCH_GAS_RATE_AVERAGE);
            BigDecimal gasAmount = getEstimateGasAmount(c, basechain, txType, valCnt);
            return gasRate.multiply(gasAmount).setScale(0, RoundingMode.DOWN);

        } else if (basechain.equals(RIZON_MAIN) || basechain.equals(RIZON_TEST)) {
            BigDecimal gasRate = new BigDecimal(COSMOS_GAS_RATE_AVERAGE);
            BigDecimal gasAmount = getEstimateGasAmount(c, basechain, txType, valCnt);
            return gasRate.multiply(gasAmount).setScale(0, RoundingMode.DOWN);

        } else if (basechain.equals(JUNO_MAIN)) {
            BigDecimal gasRate = new BigDecimal(JUNO_GAS_RATE_AVERAGE);
            BigDecimal gasAmount = getEstimateGasAmount(c, basechain, txType, valCnt);
            return gasRate.multiply(gasAmount).setScale(0, RoundingMode.DOWN);

        } else if (basechain.equals(ALTHEA_TEST)) {
            BigDecimal gasRate = new BigDecimal(COSMOS_GAS_RATE_AVERAGE);
            BigDecimal gasAmount = getEstimateGasAmount(c, basechain, txType, valCnt);
            return gasRate.multiply(gasAmount).setScale(0, RoundingMode.DOWN);

        } else if (basechain.equals(UMEE_TEST)) {
            BigDecimal gasRate = new BigDecimal(COSMOS_GAS_RATE_AVERAGE);
            BigDecimal gasAmount = getEstimateGasAmount(c, basechain, txType, valCnt);
            return gasRate.multiply(gasAmount).setScale(0, RoundingMode.DOWN);

        } else if (basechain.equals(AXELAR_TEST)) {
            BigDecimal gasRate = new BigDecimal(COSMOS_GAS_RATE_AVERAGE);
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

        else if (basechain.equals(KAVA_MAIN)) {
            return BigDecimal.ZERO;

        } else if (basechain.equals(SECRET_MAIN)) {
            BigDecimal gasRate = new BigDecimal(SECRET_GAS_FEE_RATE_AVERAGE);
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
        if (basechain.equals(COSMOS_MAIN) || basechain.equals(AKASH_MAIN) || basechain.equals(RIZON_MAIN) ||
                basechain.equals(COSMOS_TEST) || basechain.equals(RIZON_TEST) || basechain.equals(ALTHEA_TEST) || basechain.equals(UMEE_TEST) || basechain.equals(AXELAR_TEST)) {
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

        } else if (basechain.equals(SENTINEL_MAIN)) {
            if (position == 0) {
                return new BigDecimal(SENTINEL_GAS_RATE_TINY);
            } else if (position == 1) {
                return new BigDecimal(SENTINEL_GAS_RATE_LOW);
            }
            return new BigDecimal(SENTINEL_GAS_RATE_AVERAGE);

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

        } else if (basechain.equals(OSMOSIS_MAIN)) {
            if (position == 0) {
                return new BigDecimal(OSMOSIS_GAS_RATE_TINY);
            } else if (position == 1) {
                return new BigDecimal(OSMOSIS_GAS_RATE_LOW);
            }
            return new BigDecimal(OSMOSIS_GAS_RATE_AVERAGE);

        } else if (basechain.equals(IOV_MAIN) || basechain.equals(IOV_TEST)) {
            if (position == 0) {
                return new BigDecimal(STARNAME_GAS_RATE_TINY);
            } else if (position == 1) {
                return new BigDecimal(STARNAME_GAS_RATE_LOW);
            }
            return new BigDecimal(STARNAME_GAS_RATE_AVERAGE);

        } else if (basechain.equals(SIF_MAIN)) {
            if (position == 0) {
                return new BigDecimal(SIF_GAS_RATE_TINY);
            } else if (position == 1) {
                return new BigDecimal(SIF_GAS_RATE_LOW);
            }
            return new BigDecimal(SIF_GAS_RATE_AVERAGE);

        } else if (basechain.equals(BAND_MAIN)) {
            if (position == 0) {
                return new BigDecimal(BAND_GAS_RATE_TINY);
            } else if (position == 1) {
                return new BigDecimal(BAND_GAS_RATE_LOW);
            }
            return new BigDecimal(BAND_GAS_RATE_AVERAGE);

        } else if (basechain.equals(MEDI_MAIN) || basechain.equals(MEDI_TEST)) {
            if (position == 0) {
                return new BigDecimal(MEDI_GAS_RATE_TINY);
            } else if (position == 1) {
                return new BigDecimal(MEDI_GAS_RATE_LOW);
            }
            return new BigDecimal(MEDI_GAS_RATE_AVERAGE);

        } else if (basechain.equals(CERTIK_MAIN)) {
            if (position == 0) {
                return new BigDecimal(CERTIK_GAS_RATE_TINY);
            } else if (position == 1) {
                return new BigDecimal(CERTIK_GAS_RATE_LOW);
            }
            return new BigDecimal(CERTIK_GAS_RATE_AVERAGE);

        } else if (basechain.equals(EMONEY_MAIN)) {
            if (position == 0) {
                return new BigDecimal(EMONEY_GAS_RATE_TINY);
            } else if (position == 1) {
                return new BigDecimal(EMONEY_GAS_RATE_LOW);
            }
            return new BigDecimal(EMONEY_GAS_RATE_AVERAGE);

        } else if (basechain.equals(FETCHAI_MAIN)) {
            if (position == 0) {
                return new BigDecimal(FETCH_GAS_RATE_TINY);
            } else if (position == 1) {
                return new BigDecimal(FETCH_GAS_RATE_LOW);
            }
            return new BigDecimal(FETCH_GAS_RATE_AVERAGE);

        } else if (basechain.equals(JUNO_MAIN)) {
            if (position == 0) {
                return new BigDecimal(JUNO_GAS_RATE_TINY);
            } else if (position == 1) {
                return new BigDecimal(JUNO_GAS_RATE_LOW);
            }
            return new BigDecimal(JUNO_GAS_RATE_AVERAGE);
        }

        else if (basechain.equals(KAVA_MAIN)) {
            if (position == 0) {
                return BigDecimal.ZERO.setScale(3);
            } else if (position == 1) {
                return new BigDecimal(KAVA_GAS_RATE_LOW);
            }
            return new BigDecimal(KAVA_GAS_RATE_AVERAGE);

        }

        else if (basechain.equals(BNB_MAIN) || basechain.equals(BNB_TEST)) {
            return BigDecimal.ZERO.setScale(3);

        } else if (basechain.equals(OKEX_MAIN) || basechain.equals(OK_TEST)) {
            return new BigDecimal(OK_GAS_RATE_AVERAGE);

        } else if (basechain.equals(SECRET_MAIN)) {
            return new BigDecimal(SECRET_GAS_FEE_RATE_AVERAGE);

        } else if (basechain.equals(KI_MAIN)) {
            return new BigDecimal(KI_GAS_FEE_RATE_AVERAGE);

        }
        return BigDecimal.ZERO.setScale(3);
    }


    /**
     * About Kava
     */
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

    //parse & check vesting account
    public static void onParseVestingAccount(BaseData baseData, BaseChain baseChain) {
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

//                for (CoinOuterClass.Coin vesting : vestingAccount.getBaseVestingAccount().getDelegatedVestingList()) {
//                    if (vesting.getDenom().equals(denom)) {
//                        delegatedVesting = delegatedVesting.add(new BigDecimal(vesting.getAmount()));
//                    }
//                }

                long cTime = Calendar.getInstance().getTime().getTime();
                long vestingEnd = vestingAccount.getBaseVestingAccount().getEndTime() * 1000;
                if (cTime < vestingEnd) {
                    remainVesting = originalVesting;
                }
                WLog.w("remainVesting " +  denom + "  " +  remainVesting);

                if (coin.denom.equalsIgnoreCase(WDp.mainDenom(baseChain))) {
                    BigDecimal stakedAmount = baseData.getDelegationSum();
                    if (remainVesting.compareTo(stakedAmount) >= 0) {
                        delegatedVesting = stakedAmount;
                    } else {
                        delegatedVesting = remainVesting;
                    }
                }

                WLog.w("delegatedVesting " +  denom + "  " +  delegatedVesting);

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
