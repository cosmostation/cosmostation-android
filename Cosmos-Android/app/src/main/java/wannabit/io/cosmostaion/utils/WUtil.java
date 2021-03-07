package wannabit.io.cosmostaion.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.TextUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.zxing.common.BitMatrix;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.nio.ByteBuffer;
import java.security.cert.CertificateException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
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
import okhttp3.OkHttpClient;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.dao.Balance;
import wannabit.io.cosmostaion.dao.BnbToken;
import wannabit.io.cosmostaion.dao.BondingState;
import wannabit.io.cosmostaion.dao.IovToken;
import wannabit.io.cosmostaion.dao.IrisToken;
import wannabit.io.cosmostaion.dao.OkToken;
import wannabit.io.cosmostaion.dao.Reward;
import wannabit.io.cosmostaion.dao.UnBondingState;
import wannabit.io.cosmostaion.model.ExportStarName;
import wannabit.io.cosmostaion.model.Proposal_V1;
import wannabit.io.cosmostaion.model.Reward_V1;
import wannabit.io.cosmostaion.model.StarNameResource;
import wannabit.io.cosmostaion.model.Validator_V1;
import wannabit.io.cosmostaion.model.Vote_V1;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.model.type.IrisProposal;
import wannabit.io.cosmostaion.model.type.Proposal;
import wannabit.io.cosmostaion.model.type.Validator;
import wannabit.io.cosmostaion.model.type.Vote;
import wannabit.io.cosmostaion.network.res.ResBnbAccountInfo;
import wannabit.io.cosmostaion.network.res.ResBnbTic;
import wannabit.io.cosmostaion.network.res.ResCdpParam;
import wannabit.io.cosmostaion.network.res.ResLcdAccountInfo;
import wannabit.io.cosmostaion.network.res.ResLcdBonding;
import wannabit.io.cosmostaion.network.res.ResLcdIrisReward;
import wannabit.io.cosmostaion.network.res.ResLcdKavaAccountInfo;
import wannabit.io.cosmostaion.network.res.ResLcdUnBonding;
import wannabit.io.cosmostaion.network.res.ResOkAccountInfo;
import wannabit.io.cosmostaion.network.res.ResOkAccountToken;
import wannabit.io.cosmostaion.network.res.ResOkTokenList;

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
import static wannabit.io.cosmostaion.base.BaseChain.SECRET_MAIN;
import static wannabit.io.cosmostaion.base.BaseConstant.BLOCK_TIME_AKASH;
import static wannabit.io.cosmostaion.base.BaseConstant.BLOCK_TIME_BAND;
import static wannabit.io.cosmostaion.base.BaseConstant.BLOCK_TIME_CERTIK;
import static wannabit.io.cosmostaion.base.BaseConstant.BLOCK_TIME_COSMOS;
import static wannabit.io.cosmostaion.base.BaseConstant.BLOCK_TIME_IOV;
import static wannabit.io.cosmostaion.base.BaseConstant.BLOCK_TIME_IRIS;
import static wannabit.io.cosmostaion.base.BaseConstant.BLOCK_TIME_KAVA;
import static wannabit.io.cosmostaion.base.BaseConstant.BLOCK_TIME_SECRET;
import static wannabit.io.cosmostaion.base.BaseConstant.CGC_OKEX;
import static wannabit.io.cosmostaion.base.BaseConstant.CGC_SECRET;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_REINVEST;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_SIMPLE_CHANGE_REWARD_ADDRESS;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_SIMPLE_DELEGATE;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_SIMPLE_REDELEGATE;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_SIMPLE_REWARD;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_SIMPLE_SEND;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_SIMPLE_UNDELEGATE;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_VOTE;
import static wannabit.io.cosmostaion.base.BaseConstant.COSMOS_AUTH_TYPE_CERTIK_MANUAL;
import static wannabit.io.cosmostaion.base.BaseConstant.COSMOS_AUTH_TYPE_OKEX_ACCOUNT;
import static wannabit.io.cosmostaion.base.BaseConstant.COSMOS_GAS_RATE_AVERAGE;
import static wannabit.io.cosmostaion.base.BaseConstant.IRIS_GAS_RATE_AVERAGE;
import static wannabit.io.cosmostaion.base.BaseConstant.IRIS_PROPOAL_TYPE_BasicProposal;
import static wannabit.io.cosmostaion.base.BaseConstant.IRIS_PROPOAL_TYPE_CommunityTaxUsageProposal;
import static wannabit.io.cosmostaion.base.BaseConstant.IRIS_PROPOAL_TYPE_ParameterProposal;
import static wannabit.io.cosmostaion.base.BaseConstant.IRIS_PROPOAL_TYPE_PlainTextProposal;
import static wannabit.io.cosmostaion.base.BaseConstant.IRIS_PROPOAL_TYPE_SoftwareUpgradeProposal;
import static wannabit.io.cosmostaion.base.BaseConstant.IRIS_PROPOAL_TYPE_SystemHaltProposal;
import static wannabit.io.cosmostaion.base.BaseConstant.IRIS_PROPOAL_TYPE_TokenAdditionProposal;
import static wannabit.io.cosmostaion.base.BaseConstant.PERSISTENCE_COSMOS_EVENT_END;
import static wannabit.io.cosmostaion.base.BaseConstant.PERSISTENCE_COSMOS_EVENT_START;
import static wannabit.io.cosmostaion.base.BaseConstant.PERSISTENCE_KAVA_EVENT_END;
import static wannabit.io.cosmostaion.base.BaseConstant.PERSISTENCE_KAVA_EVENT_START;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_ATOM;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_BAND;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_BNB;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_CERTIK;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HARD;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_IOV;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_IOV_TEST;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_IRIS;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_KAVA;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_OK;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_OK_OKB;
import static wannabit.io.cosmostaion.base.BaseConstant.V1_GAS_AMOUNT_HIGH;
import static wannabit.io.cosmostaion.base.BaseConstant.V1_GAS_AMOUNT_LOW;
import static wannabit.io.cosmostaion.base.BaseConstant.V1_GAS_AMOUNT_MID;

public class WUtil {

    public static Account getAccountFromLcd(long id, ResLcdAccountInfo lcd) {
        Account result = new Account();
        result.id = id;
        if (lcd.result != null && lcd.height != null) {
            if (lcd.result.type.equals(BaseConstant.COSMOS_AUTH_TYPE_ACCOUNT) ||
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
        if (lcd.type.equals(BaseConstant.COSMOS_AUTH_TYPE_ACCOUNT) ||
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
            if (lcd.result.type.equals(BaseConstant.COSMOS_AUTH_TYPE_ACCOUNT)) {
                result.address = lcd.result.value.address;
                result.sequenceNumber = Integer.parseInt(lcd.result.value.sequence);
                result.accountNumber = Integer.parseInt(lcd.result.value.account_number);

            } else if (lcd.result.type.equals(BaseConstant.COSMOS_AUTH_TYPE_VESTING_ACCOUNT) || lcd.result.type.equals(BaseConstant.COSMOS_AUTH_TYPE_P_VESTING_ACCOUNT)) {
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

    public static ArrayList<Balance> getBalancesFromLcd(long accountId, ResLcdAccountInfo lcd) {
        long time = System.currentTimeMillis();
        ArrayList<Balance> result = new ArrayList<>();
        if (lcd.result != null && lcd.height != null) {
            if(lcd.result.type.equals(BaseConstant.COSMOS_AUTH_TYPE_ACCOUNT) ||
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
        if(lcd.type.equals(BaseConstant.COSMOS_AUTH_TYPE_ACCOUNT) ||
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
        if (lcd.result != null && lcd.height != null) {
            if (lcd.result.type.equals(BaseConstant.COSMOS_AUTH_TYPE_ACCOUNT)) {
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

            }  else if (lcd.result.type.equals(BaseConstant.COSMOS_AUTH_TYPE_VESTING_ACCOUNT) || lcd.result.type.equals(BaseConstant.COSMOS_AUTH_TYPE_P_VESTING_ACCOUNT)) {
                BigDecimal dpBalance = BigDecimal.ZERO;
                BigDecimal dpVesting = BigDecimal.ZERO;
                BigDecimal originalVesting = BigDecimal.ZERO;
                BigDecimal remainVesting = BigDecimal.ZERO;
                BigDecimal delegatedVesting = BigDecimal.ZERO;

                if (lcd.result.value.coins != null && lcd.result.value.coins.size() > 0) {
                    for (Coin coin : lcd.result.value.coins) {
                        if (coin.denom.equals(TOKEN_KAVA)) {
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
                            WLog.w("hard remainVesting " +  remainVesting);

                            dpBalance = dpBalance.subtract(remainVesting);
                            WLog.w("hard dpBalancee " +  dpBalance);

                            Balance temp = new Balance();
                            temp.accountId = accountId;
                            temp.symbol = coin.denom;
                            temp.balance = dpBalance;
                            temp.frozen = remainVesting;
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


    public static Balance getTokenBalance(ArrayList<Balance> list, String symbol) {
        for (Balance balance:list) {
            if (balance.symbol.equals(symbol)) {
                return balance;
            }
        }
        return null;
    }


    public static ArrayList<BondingState> getBondingFromLcds(long accountId, ArrayList<ResLcdBonding> list, BaseChain chain) {
        long time = System.currentTimeMillis();
        ArrayList<BondingState> result = new ArrayList<>();
        if (chain.equals(COSMOS_MAIN) || chain.equals(KAVA_MAIN) || chain.equals(BAND_MAIN) ||
                chain.equals(KAVA_TEST) || chain.equals(IOV_MAIN) || chain.equals(IOV_TEST) ||
                chain.equals(CERTIK_MAIN) || chain.equals(CERTIK_TEST) || chain.equals(SECRET_MAIN) || chain.equals(AKASH_MAIN)) {
            for(ResLcdBonding val : list) {
                String valAddress = "";
                if(!TextUtils.isEmpty(val.validator_addr))
                    valAddress = val.validator_addr;
                if(!TextUtils.isEmpty(val.validator_address))
                    valAddress = val.validator_address;

                BondingState temp = new BondingState(accountId, valAddress, new BigDecimal(val.shares), time);
                result.add(temp);
            }

        } else if (chain.equals(IRIS_MAIN)) {
            for(ResLcdBonding val : list) {
                String valAddress = "";
                if(!TextUtils.isEmpty(val.validator_addr))
                    valAddress = val.validator_addr;
                if(!TextUtils.isEmpty(val.validator_address))
                    valAddress = val.validator_address;

                BondingState temp = new BondingState(accountId, valAddress, new BigDecimal(val.shares).movePointRight(18), time);
                result.add(temp);
            }
        }

        return result;
    }

    public static BondingState getBondingFromLcd(long accountId, ResLcdBonding lcd, BaseChain chain) {
        String valAddress = "";
        if(!TextUtils.isEmpty(lcd.validator_addr))
            valAddress = lcd.validator_addr;
        if(!TextUtils.isEmpty(lcd.validator_address))
            valAddress = lcd.validator_address;

        if (chain.equals(COSMOS_MAIN) || chain.equals(KAVA_MAIN) || chain.equals(BAND_MAIN) ||
                chain.equals(KAVA_TEST) || chain.equals(IOV_MAIN) || chain.equals(IOV_TEST) ||
                chain.equals(CERTIK_MAIN) || chain.equals(CERTIK_TEST) || chain.equals(SECRET_MAIN) || chain.equals(AKASH_MAIN)) {
            return new BondingState(accountId, valAddress, new BigDecimal(lcd.shares), System.currentTimeMillis());

        } else if (chain.equals(IRIS_MAIN)) {
            return new BondingState(accountId, valAddress, new BigDecimal(lcd.shares).movePointRight(18), System.currentTimeMillis());

        }
        return null;
    }

    public static ArrayList<UnBondingState> getUnbondingFromLcds(Context c, BaseChain chain, long accountId, ArrayList<ResLcdUnBonding> list) {
        long time = System.currentTimeMillis();
        ArrayList<UnBondingState> result = new ArrayList<>();
        if (chain.equals(COSMOS_MAIN) || chain.equals(KAVA_MAIN) || chain.equals(BAND_MAIN) ||
                chain.equals(KAVA_TEST) || chain.equals(IOV_MAIN) || chain.equals(IOV_TEST) ||
                chain.equals(CERTIK_MAIN) || chain.equals(CERTIK_TEST) || chain.equals(SECRET_MAIN) || chain.equals(AKASH_MAIN)) {
            for(ResLcdUnBonding val : list) {
                String valAddress = "";
                if(!TextUtils.isEmpty(val.validator_addr))
                    valAddress = val.validator_addr;
                if(!TextUtils.isEmpty(val.validator_address))
                    valAddress = val.validator_address;

                for(ResLcdUnBonding.Entry entry:val.entries) {
                    UnBondingState temp = new UnBondingState(
                            accountId,
                            valAddress,
                            entry.creation_height,
                            WUtil.cosmosTimetoLocalLong(c, entry.completion_time),
                            new BigDecimal(entry.getinitial_balance()),
                            new BigDecimal(entry.getbalance()),
                            time
                    );
                    result.add(temp);
                }
            }

        } else if (chain.equals(IRIS_MAIN)) {
            for(ResLcdUnBonding val : list) {
                String valAddress = "";
                if(!TextUtils.isEmpty(val.validator_addr))
                    valAddress = val.validator_addr;
                if(!TextUtils.isEmpty(val.validator_address))
                    valAddress = val.validator_address;

                UnBondingState temp = new UnBondingState(
                        accountId,
                        valAddress,
                        val.creation_height,
                        WUtil.cosmosTimetoLocalLong(c, val.min_time),
                        new BigDecimal(val.initial_balance.replace("iris","")).movePointRight(18),
                        new BigDecimal(val.balance.replace("iris","")).movePointRight(18),
                        time
                );
                result.add(temp);
            }
        }
        return result;
    }

    public static ArrayList<UnBondingState> getUnbondingFromLcd(Context c, long accountId, ResLcdUnBonding lcd) {
        long time = System.currentTimeMillis();
        ArrayList<UnBondingState> result = new ArrayList<>();
        for(ResLcdUnBonding.Entry entry:lcd.entries) {
            String valAddress = "";
            if(!TextUtils.isEmpty(lcd.validator_addr))
                valAddress = lcd.validator_addr;
            if(!TextUtils.isEmpty(lcd.validator_address))
                valAddress = lcd.validator_address;

            UnBondingState temp = new UnBondingState(
                    accountId,
                    valAddress,
                    entry.creation_height,
                    WUtil.cosmosTimetoLocalLong(c, entry.completion_time),
                    new BigDecimal(entry.getinitial_balance()),
                    new BigDecimal(entry.getbalance()),
                    time
            );
            result.add(temp);
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

    public static void onSortByValidatorNameV2(ArrayList<Validator_V1> validators) {
        Collections.sort(validators, new Comparator<Validator_V1>() {
            @Override
            public int compare(Validator_V1 o1, Validator_V1 o2) {
                if(o1.description.moniker.equalsIgnoreCase("Cosmostation")) return -1;
                if(o2.description.moniker.equalsIgnoreCase("Cosmostation")) return 1;
                return o1.description.moniker.compareTo(o2.description.moniker);
            }
        });
        Collections.sort(validators, new Comparator<Validator_V1>() {
            @Override
            public int compare(Validator_V1 o1, Validator_V1 o2) {
                if (o1.jailed && !o2.jailed) return 1;
                else if (!o1.jailed && o2.jailed) return -1;
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

    public static void onSortByValidatorPowerV2(ArrayList<Validator_V1> validators) {
        Collections.sort(validators, new Comparator<Validator_V1>() {
            @Override
            public int compare(Validator_V1 o1, Validator_V1 o2) {
                if(o1.description.moniker.equalsIgnoreCase("Cosmostation")) return -1;
                if(o2.description.moniker.equalsIgnoreCase("Cosmostation")) return 1;

                if (Double.parseDouble(o1.tokens) > Double.parseDouble(o2.tokens)) return -1;
                else if (Double.parseDouble(o1.tokens) < Double.parseDouble(o2.tokens)) return 1;
                else return 0;
            }
        });
        Collections.sort(validators, new Comparator<Validator_V1>() {
            @Override
            public int compare(Validator_V1 o1, Validator_V1 o2) {
                if (o1.jailed && !o2.jailed) return 1;
                else if (!o1.jailed && o2.jailed) return -1;
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



    public static void onSortByDelegate(final long userId, ArrayList<Validator> validators, final BaseData dao) {
        Collections.sort(validators, new Comparator<Validator>() {
            @Override
            public int compare(Validator o1, Validator o2) {
                if(o1.description.moniker.equalsIgnoreCase("Cosmostation")) return -1;
                if(o2.description.moniker.equalsIgnoreCase("Cosmostation")) return 1;

                BigDecimal bondingO1 = BigDecimal.ZERO;
                BigDecimal bondingO2 = BigDecimal.ZERO;
                if(dao.onSelectBondingState(userId, o1.operator_address) != null &&
                        dao.onSelectBondingState(userId, o1.operator_address).getBondingAmount(o1) != null) {
                    bondingO1  = dao.onSelectBondingState(userId, o1.operator_address).getBondingAmount(o1) ;
                }
                if(dao.onSelectBondingState(userId, o2.operator_address) != null &&
                        dao.onSelectBondingState(userId, o2.operator_address).getBondingAmount(o2)  != null) {
                    bondingO2  = dao.onSelectBondingState(userId, o2.operator_address).getBondingAmount(o2) ;
                }
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

    public static void onSortByDelegateV1(final long userId, ArrayList<Staking.Validator> validators, final BaseData dao) {
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

    public static void onSortByDelegateV2(final long userId, ArrayList<Validator_V1> validators, final BaseData dao) {
        Collections.sort(validators, new Comparator<Validator_V1>() {
            @Override
            public int compare(Validator_V1 o1, Validator_V1 o2) {
                if(o1.description.moniker.equalsIgnoreCase("Cosmostation")) return -1;
                if(o2.description.moniker.equalsIgnoreCase("Cosmostation")) return 1;

                BigDecimal bondingO1 = WDp.getDelegation(dao, o1.operator_address);
                BigDecimal bondingO2 = WDp.getDelegation(dao, o2.operator_address);
                return bondingO2.compareTo(bondingO1);
            }
        });
        Collections.sort(validators, new Comparator<Validator_V1>() {
            @Override
            public int compare(Validator_V1 o1, Validator_V1 o2) {
                if (o1.jailed && !o2.jailed) return 1;
                else if (!o1.jailed && o2.jailed) return -1;
                else return 0;
            }
        });
    }

    public static void onSortByReward(ArrayList<Validator> validators, final ArrayList<Reward> rewards, String denom) {
        Collections.sort(validators, new Comparator<Validator>() {
            @Override
            public int compare(Validator o1, Validator o2) {
                if(o1.description.moniker.equalsIgnoreCase("Cosmostation")) return -1;
                if(o2.description.moniker.equalsIgnoreCase("Cosmostation")) return 1;

                BigDecimal rewardO1 = WDp.getValidatorReward(rewards, o1.operator_address, denom);
                BigDecimal rewardO2 = WDp.getValidatorReward(rewards, o2.operator_address, denom);
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


    public static void onSortByRewardV2(ArrayList<Validator_V1> validators, String denom, final BaseData dao) {
        Collections.sort(validators, new Comparator<Validator_V1>() {
            @Override
            public int compare(Validator_V1 o1, Validator_V1 o2) {
                if(o1.description.moniker.equalsIgnoreCase("Cosmostation")) return -1;
                if(o2.description.moniker.equalsIgnoreCase("Cosmostation")) return 1;

                BigDecimal rewardO1 = WDp.getReward(dao, denom, o1.operator_address);
                BigDecimal rewardO2 = WDp.getReward(dao, denom, o2.operator_address);
                return rewardO2.compareTo(rewardO1);
            }
        });
        Collections.sort(validators, new Comparator<Validator_V1>() {
            @Override
            public int compare(Validator_V1 o1, Validator_V1 o2) {
                if (o1.jailed && !o2.jailed) return 1;
                else if (!o1.jailed && o2.jailed) return -1;
                else return 0;
            }
        });
    }

    public static void onSortByOnlyReward(ArrayList<Validator> validators, final ArrayList<Reward> rewards, String denom) {
        Collections.sort(validators, new Comparator<Validator>() {
            @Override
            public int compare(Validator o1, Validator o2) {
                BigDecimal rewardO1 = WDp.getValidatorReward(rewards, o1.operator_address, denom);
                BigDecimal rewardO2 = WDp.getValidatorReward(rewards, o2.operator_address, denom);
                return rewardO2.compareTo(rewardO1);
            }
        });
    }

    public static void onSortRewardAmount_V1(ArrayList<Reward_V1> rewards, String denom) {
        Collections.sort(rewards, new Comparator<Reward_V1>() {
            @Override
            public int compare(Reward_V1 o1, Reward_V1 o2) {
                BigDecimal rewardO1 = o1.getRewardByDenom(denom);
                BigDecimal rewardO2 = o2.getRewardByDenom(denom);
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
                return new BigDecimal(coin.getAmount()).movePointLeft(18);
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
                if (chain.equals(COSMOS_MAIN) || chain.equals(KAVA_MAIN) || chain.equals(KAVA_TEST)
                        || chain.equals(BAND_MAIN) || chain.equals(IOV_MAIN) || chain.equals(IOV_TEST) || chain.equals(CERTIK_MAIN) || chain.equals(CERTIK_TEST) || chain.equals(SECRET_MAIN)) {
                    if (Float.parseFloat(o1.commission.commission_rates.rate) > Float.parseFloat(o2.commission.commission_rates.rate)) return 1;
                    else if (Float.parseFloat(o1.commission.commission_rates.rate) < Float.parseFloat(o2.commission.commission_rates.rate)) return -1;
                    else return 0;
                } else if (chain.equals(IRIS_MAIN)) {
                    if (Float.parseFloat(o1.commission.rate) > Float.parseFloat(o2.commission.rate)) return 1;
                    else if (Float.parseFloat(o1.commission.rate) < Float.parseFloat(o2.commission.rate)) return -1;
                    else return 0;
                }
                return 0;
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

    public static void onSortingByCommissionV2(ArrayList<Validator_V1> validators) {
        Collections.sort(validators, new Comparator<Validator_V1>() {
            @Override
            public int compare(Validator_V1 o1, Validator_V1 o2) {
                if(o1.description.moniker.equalsIgnoreCase("Cosmostation")) return -1;
                if(o2.description.moniker.equalsIgnoreCase("Cosmostation")) return 1;
                if (Float.parseFloat(o1.commission.commission_rates.rate) > Float.parseFloat(o2.commission.commission_rates.rate)) return 1;
                else if (Float.parseFloat(o1.commission.commission_rates.rate) < Float.parseFloat(o2.commission.commission_rates.rate)) return -1;
                else return 0;
            }
        });
        Collections.sort(validators, new Comparator<Validator_V1>() {
            @Override
            public int compare(Validator_V1 o1, Validator_V1 o2) {
                if (o1.jailed && !o2.jailed) return 1;
                else if (!o1.jailed && o2.jailed) return -1;
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

    public static void onSortingIrisProposal(ArrayList<IrisProposal> proposals) {
        Collections.sort(proposals, new Comparator<IrisProposal>() {
            @Override
            public int compare(IrisProposal o1, IrisProposal o2) {
                if (Integer.parseInt(o1.value.basicProposal.proposal_id) < Integer.parseInt(o2.value.basicProposal.proposal_id)) return 1;
                else if (Integer.parseInt(o1.value.basicProposal.proposal_id) > Integer.parseInt(o2.value.basicProposal.proposal_id)) return -1;
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

    public static void onSortingProposalsV1(ArrayList<Proposal_V1> proposals) {
        Collections.sort(proposals, new Comparator<Proposal_V1>() {
            @Override
            public int compare(Proposal_V1 o1, Proposal_V1 o2) {
                if (Integer.parseInt(o1.proposal_id) < Integer.parseInt(o2.proposal_id)) return 1;
                else if (Integer.parseInt(o1.proposal_id) > Integer.parseInt(o2.proposal_id)) return -1;
                else return 0;
            }
        });
    }


    public static void onSortIrisByReward(ArrayList<Validator> validators, final ResLcdIrisReward reward) {
        Collections.sort(validators, new Comparator<Validator>() {
            @Override
            public int compare(Validator o1, Validator o2) {
                if(o1.description.moniker.equalsIgnoreCase("Cosmostation")) return -1;
                if(o2.description.moniker.equalsIgnoreCase("Cosmostation")) return 1;

                BigDecimal rewardO1 = reward.getPerValReward(o1.operator_address);
                BigDecimal rewardO2 = reward.getPerValReward(o2.operator_address);
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

    public static void onSortIrisOnlyByReward(ArrayList<Validator> validators, final ResLcdIrisReward reward) {
        Collections.sort(validators, new Comparator<Validator>() {
            @Override
            public int compare(Validator o1, Validator o2) {

                BigDecimal rewardO1 = reward.getPerValReward(o1.operator_address);
                BigDecimal rewardO2 = reward.getPerValReward(o2.operator_address);
                return rewardO2.compareTo(rewardO1);
            }
        });
    }

    public static void onSortingTokenByAmount(ArrayList<Balance> balances, final BaseChain chain) {
        Collections.sort(balances, new Comparator<Balance>() {
            @Override
            public int compare(Balance o1, Balance o2) {
                if (chain.equals(COSMOS_MAIN)) {
                    if(o1.symbol.equals(TOKEN_ATOM)) return -1;
                    if(o2.symbol.equals(TOKEN_ATOM)) return 1;

                } else if (chain.equals(IRIS_MAIN)) {
                    if(o1.symbol.equals(TOKEN_IRIS)) return -1;
                    if(o2.symbol.equals(TOKEN_IRIS)) return 1;

                } else if (chain.equals(BNB_MAIN) || chain.equals(BNB_TEST)) {
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

                }
                return o2.balance.compareTo(o1.balance);
            }
        });
    }

    public static void onSortingTokenByName(ArrayList<Balance> balances, final BaseChain chain) {
        Collections.sort(balances, new Comparator<Balance>() {
            @Override
            public int compare(Balance o1, Balance o2) {
                if (chain.equals(COSMOS_MAIN)) {
                    if(o1.symbol.equals(TOKEN_ATOM)) return -1;
                    if(o2.symbol.equals(TOKEN_ATOM)) return 1;

                } else if (chain.equals(IRIS_MAIN)) {
                    if(o1.symbol.equals(TOKEN_IRIS)) return -1;
                    if(o2.symbol.equals(TOKEN_IRIS)) return 1;

                } else if (chain.equals(BNB_MAIN) || chain.equals(BNB_TEST)) {
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

                }
                return o1.symbol.compareTo(o2.symbol);
            }
        });
    }

    public static void onSortingBnbTokenByValue(ArrayList<Balance> balances, HashMap<String, ResBnbTic> tics) {
        Collections.sort(balances, new Comparator<Balance>() {
            @Override
            public int compare(Balance o1, Balance o2) {
                if(o1.symbol.equals(TOKEN_BNB)) return -1;
                if(o2.symbol.equals(TOKEN_BNB)) return 1;

                ResBnbTic tic1 = tics.get(WUtil.getBnbTicSymbol(o1.symbol));
                ResBnbTic tic2 = tics.get(WUtil.getBnbTicSymbol(o2.symbol));
                if (tic1 != null && tic2 != null) {
                    BigDecimal o1Amount = o1.exchangeToBnbAmount(tic1);
                    BigDecimal o2Amount = o2.exchangeToBnbAmount(tic2);
                    return o2Amount.compareTo(o1Amount);
                } else {
                    return 0;
                }
            }
        });
    }

    public static void onSortingKavaTokenByValue(BaseData baseData, ArrayList<Balance> balances) {
        Collections.sort(balances, new Comparator<Balance>() {
            @Override
            public int compare(Balance o1, Balance o2) {
                if(o1.symbol.equals(TOKEN_KAVA)) return -1;
                if(o2.symbol.equals(TOKEN_KAVA)) return 1;

                BigDecimal amount1 = WDp.getKavaTokenAll(baseData, balances, o1.symbol);
                BigDecimal amount2 = WDp.getKavaTokenAll(baseData, balances, o2.symbol);
                BigDecimal value1 = WDp.kavaTokenDollorValue(baseData, o1.symbol, amount1);
                BigDecimal value2 = WDp.kavaTokenDollorValue(baseData, o2.symbol, amount2);

                return value2.compareTo(value1);
            }
        });
    }


    public static void onSortingCoins(ArrayList<Coin> coins, BaseChain chain) {
        Collections.sort(coins, new Comparator<Coin>() {
            @Override
            public int compare(Coin o1, Coin o2) {
                if (chain.equals(KAVA_MAIN) || chain.equals(KAVA_TEST)) {
                    if(o1.denom.equals(TOKEN_KAVA)) return -1;
                    if(o2.denom.equals(TOKEN_KAVA)) return 1;
                    else return 0;
                } else if (chain.equals(OKEX_MAIN) || chain.equals(OK_TEST)) {
                    if(o1.denom.equals(TOKEN_OK)) return -1;
                    if(o2.denom.equals(TOKEN_OK)) return 1;
                    else return 0;

                } else {
                    return 0;
                }
            }
        });
    }

    public static void onSortUnbondingsRecent(ArrayList<UnBondingState> UnBondingStates) {
        Collections.sort(UnBondingStates, new Comparator<UnBondingState>() {
            @Override
            public int compare(UnBondingState o1, UnBondingState o2) {
                return o1.completionTime < o2.completionTime ?  -1 : 1;

            }
        });
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

    public static ArrayList<Validator> getTopVals(ArrayList<Validator> allValidators) {
        ArrayList<Validator> result = new ArrayList<>();
        for(Validator v:allValidators) {
            if(v.status == Validator.BONDED) {
                result.add(v);
            }
        }
        return result;

    }

    public static ArrayList<Validator> getOthersVals(ArrayList<Validator> allValidators) {
        ArrayList<Validator> result = new ArrayList<>();
        for(Validator v:allValidators) {
            if(v.status != Validator.BONDED) {
                result.add(v);
            }
        }
        return result;
    }

    public static Validator selectValidatorByAddr(ArrayList<Validator> validators, String opAddr) {
        for (Validator v:validators) {
            if (v.operator_address.equals(opAddr)) {
                return v;
            }
        }
        return null;
    }


    public static int getCMCId(BaseChain chain) {
        if (chain.equals(COSMOS_MAIN)) {
            return BaseConstant.CMC_ATOM;

        } else if (chain.equals(IRIS_MAIN)) {
            return BaseConstant.CMC_IRIS;

        } else if (chain.equals(BNB_MAIN) || chain.equals(BNB_TEST)) {
            return BaseConstant.CMC_BNB;

        } else if (chain.equals(KAVA_MAIN) || chain.equals(KAVA_TEST)) {
            return BaseConstant.CMC_KAVA;
        }
        return BaseConstant.CMC_ATOM;
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

        }
        return BaseConstant.CGC_ATOM;
    }

    public static int getMaxMemoSize(BaseChain chain) {
        if (chain.equals(COSMOS_MAIN) || chain.equals(KAVA_MAIN) || chain.equals(KAVA_TEST) ||
                chain.equals(IOV_MAIN) || chain.equals(BAND_MAIN) || chain.equals(IOV_TEST) ||
                chain.equals(OK_TEST) || chain.equals(CERTIK_MAIN) || chain.equals(CERTIK_TEST) ||
                chain.equals(AKASH_MAIN) || chain.equals(SECRET_MAIN) || chain.equals(OKEX_MAIN) ||
                chain.equals(COSMOS_TEST) || chain.equals(IRIS_TEST)) {
            return BaseConstant.MEMO_ATOM;

        } else if (chain.equals(IRIS_MAIN)) {
            return BaseConstant.MEMO_IRIS;

        } else if (chain.equals(BNB_MAIN) || chain.equals(BNB_TEST)) {
            return BaseConstant.MEMO_BNB;
        }
        return BaseConstant.MEMO_IRIS;
    }

    public static int getCharSize(String memo) {
        int result = 1000;
        try {
            result = memo.trim().getBytes("UTF-8").length;
        } catch (Exception e) { }

        return result;
    }

    public static ResCdpParam.KavaCollateralParam getCdpCoinParm(ResCdpParam.Result params, Balance balance) {
        if (params != null) {
            for (ResCdpParam.KavaCollateralParam param:params.collateral_params) {
                if (param.denom.equals(balance.symbol)) {
                    return param;
                }
            }
            return null;

        } else {
            return null;
        }
    }

    public static int getKavaCoinDecimal(ResCdpParam.Result params, Balance balance) {
        int result = 0;
        if (params != null) {
            if (params.debt_param != null) {
                return Integer.parseInt(params.debt_param.conversion_factor);
            }

            if (params.collateral_params != null) {
                for (ResCdpParam.KavaCollateralParam collateralParams: params.collateral_params) {
                    if (collateralParams.denom.equals(balance.symbol)) {
                        return Integer.parseInt(collateralParams.conversion_factor);
                    }
                }
            }
        }

        return result;
    }

    public static int getKavaCoinDecimal(Coin coin) {
        if (coin.denom.equalsIgnoreCase(TOKEN_KAVA)) {
            return 6;
        } else if (coin.denom.equalsIgnoreCase(TOKEN_HARD)) {
            return 6;
        } else if (coin.denom.equalsIgnoreCase("xrpb")) {
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
        } else if (denom.equalsIgnoreCase("xrpb")) {
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

    public static BnbToken getBnbToken(ArrayList<BnbToken> all, Balance balance) {
        if (all == null || balance == null) return null;
        for (BnbToken token:all) {
            if (token.symbol.equals(balance.symbol)) {
                return token;
            }
        }
        return null;
    }

    public static BnbToken getBnbToken(ArrayList<BnbToken> all, String symbol) {
        if (all == null || symbol == null) return null;
        for (BnbToken token:all) {
            if (token.symbol.equals(symbol)) {
                return token;
            }
        }
        return null;
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

    public static IrisToken getIrisToken(ArrayList<IrisToken> all, Balance balance) {
        if (all == null || balance == null) return null;
        for (IrisToken token:all) {
            if(balance.symbol.split("-")[0].equals(token.base_token.id)) {
                return token;
            }
        }
        return null;
    }

    public static OkToken getOkToken(ResOkTokenList okTokenList, String denom) {
        if (okTokenList == null || okTokenList.data == null || TextUtils.isEmpty(denom)) return null;
        for (OkToken token:okTokenList.data) {
            if (token.symbol.equals(denom)) {
                return token;
            }
        }
        return null;
    }

    public static IrisToken getIrisMainToken(ArrayList<IrisToken> all) {
        if (all == null) return null;
        for (IrisToken token:all) {
            if (token.base_token.id.equals(TOKEN_IRIS)) {
                return token;
            }
        }
        return null;
    }

    public static IovToken getIovToken(ArrayList<IovToken> all, Balance balance) {
        if (all == null || balance == null) return null;
        for (IovToken token:all) {
            if(balance.symbol.equals(token.tokenTicker)) {
                return token;
            }
        }
        return null;
    }

    public static IovToken getIovMainToken(ArrayList<IovToken> all) {
        if (all == null) return null;
        for (IovToken token:all) {
            if (token.tokenTicker.equals(TOKEN_IOV)) {
                return token;
            }
        }
        return null;
    }

    public static BigDecimal getQuotient(String value) {
        BigDecimal dividend = new BigDecimal(value);
        return dividend.divide(BigDecimal.ONE, 0, RoundingMode.DOWN);
    }

    public static BigDecimal getRemainder(String value) {
        BigDecimal dividend = new BigDecimal(value);
        BigDecimal quotient = dividend.divide(BigDecimal.ONE, 0, RoundingMode.DOWN);
        return  dividend.subtract(quotient);
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


    public static String getIrisProposalType(Context c, String type) {
        String result = c.getString(R.string.str_iris_proposal_type_BasicProposal);
        if (type.equals(IRIS_PROPOAL_TYPE_BasicProposal)) {
            result = c.getString(R.string.str_iris_proposal_type_BasicProposal);
        } else if (type.equals(IRIS_PROPOAL_TYPE_ParameterProposal)) {
            result = c.getString(R.string.str_iris_proposal_type_ParameterProposal);
        } else if (type.equals(IRIS_PROPOAL_TYPE_PlainTextProposal)) {
            result = c.getString(R.string.str_iris_proposal_type_PlainTextProposal);
        } else if (type.equals(IRIS_PROPOAL_TYPE_TokenAdditionProposal)) {
            result = c.getString(R.string.str_iris_proposal_type_TokenAdditionProposal);
        } else if (type.equals(IRIS_PROPOAL_TYPE_SoftwareUpgradeProposal)) {
            result = c.getString(R.string.str_iris_proposal_type_SoftwareUpgradeProposal);
        } else if (type.equals(IRIS_PROPOAL_TYPE_SystemHaltProposal)) {
            result = c.getString(R.string.str_iris_proposal_type_SystemHaltProposal);
        } else if (type.equals(IRIS_PROPOAL_TYPE_CommunityTaxUsageProposal)) {
            result = c.getString(R.string.str_iris_proposal_type_CommunityTaxUsageProposal);
        }
        return result;
    }


    public static String getIrisMonikerName(ArrayList<Validator> validators, String address) {
        String opAddress = WKey.convertDpAddressToDpOpAddress(address, IRIS_MAIN);
        String result = address;
        for (Validator v:validators) {
            if (v.operator_address.equals(opAddress)) {
                result = v.description.moniker;
            }
        }
        return result;
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

    public static int getVoterTypeCnt_V1(ArrayList<Vote_V1> votes, String option) {
        int result = 0;
        if (votes == null) {
            return result;
        }
        for (Vote_V1 v:votes) {
            if (v.option.equals(option)) {
                result = result + 1;
            }
        }
        return result;
    }

    public static Vote getMyVote(ArrayList<Vote> votes, String address) {
        if (votes == null) {
            return null;
        }
        for (Vote v:votes) {
            if (v.voter.equals(address)) {
                return v;
            }
        }
        return null;

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


    public static boolean isDisplayEventCard(BaseData baseData, BaseChain chain) {
        if (baseData == null || baseData.mStakingPool == null) {
            return false;
        }
        if (chain.equals(COSMOS_MAIN)) {
            if (baseData.mStakingPool.getHeight() > PERSISTENCE_COSMOS_EVENT_START &&
                    baseData.mStakingPool.getHeight() < PERSISTENCE_COSMOS_EVENT_END &&
                    baseData.getEventTime()) {
                return true;
            }

        } else if (chain.equals(KAVA_MAIN)) {
            if (baseData.mStakingPool.getHeight() > PERSISTENCE_KAVA_EVENT_START &&
                    baseData.mStakingPool.getHeight() < PERSISTENCE_KAVA_EVENT_END &&
                    baseData.getEventTime()) {
                return true;
            }

        }
        return false;
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

        }
        return null;
    }

    public static BigDecimal getEstimateGasAmount(Context c, BaseChain basechain, int txType,  int valCnt) {
        BigDecimal result = BigDecimal.ZERO;
        if (basechain.equals(COSMOS_MAIN) || basechain.equals(COSMOS_TEST)) {
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

        }
        return BigDecimal.ZERO;
    }
}
