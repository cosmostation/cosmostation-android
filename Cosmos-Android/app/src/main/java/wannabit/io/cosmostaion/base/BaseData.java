package wannabit.io.cosmostaion.base;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;

import net.sqlcipher.Cursor;
import net.sqlcipher.database.SQLiteDatabase;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.dao.Balance;
import wannabit.io.cosmostaion.dao.BnbToken;
import wannabit.io.cosmostaion.dao.BondingState;
import wannabit.io.cosmostaion.dao.Password;
import wannabit.io.cosmostaion.dao.UnBondingState;
import wannabit.io.cosmostaion.model.type.Validator;
import wannabit.io.cosmostaion.network.res.ResBnbFee;
import wannabit.io.cosmostaion.network.res.ResCdpOwnerStatus;
import wannabit.io.cosmostaion.network.res.ResCdpParam;
import wannabit.io.cosmostaion.network.res.ResCgcTic;
import wannabit.io.cosmostaion.network.res.ResIovFee;
import wannabit.io.cosmostaion.network.res.ResKavaIncentiveParam;
import wannabit.io.cosmostaion.network.res.ResKavaIncentiveReward;
import wannabit.io.cosmostaion.network.res.ResKavaMarketPrice;
import wannabit.io.cosmostaion.network.res.ResLcdIrisPool;
import wannabit.io.cosmostaion.network.res.ResLcdKavaAccountInfo;
import wannabit.io.cosmostaion.network.res.ResOkDeposit;
import wannabit.io.cosmostaion.network.res.ResOkTokenList;
import wannabit.io.cosmostaion.network.res.ResOkWithdraw;
import wannabit.io.cosmostaion.network.res.ResStakingPool;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WLog;

import static wannabit.io.cosmostaion.base.BaseChain.BNB_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.BNB_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.KAVA_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.KAVA_TEST;
import static wannabit.io.cosmostaion.base.BaseConstant.FEE_BNB_SEND;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_BNB;

public class BaseData {

    private BaseApplication     mApp;
    private SharedPreferences   mSharedPreferences;
    private SQLiteDatabase      mSQLiteDatabase;
    public ArrayList<Validator> mAllValidators = new ArrayList<>();
    public ArrayList<Validator> mMyValidators = new ArrayList<>();
    public ArrayList<Validator> mTopValidators = new ArrayList<>();
    public ArrayList<Validator> mOtherValidators = new ArrayList<>();
    public ResStakingPool       mStakingPool;
    public ResLcdIrisPool       mIrisStakingPool;
    public BigDecimal           mInflation = BigDecimal.ZERO;
    public BigDecimal           mProvisions = BigDecimal.ZERO;


    //COMMON DATA
    public ResLcdKavaAccountInfo.Result                                     mKavaAccount;
    public ResCdpParam.Result                                               mKavaCdpParams;
    public ArrayList<ResCdpOwnerStatus.MyCDP>                               mMyOwenCdp = new ArrayList<>();
    public HashMap<String, ResKavaMarketPrice.Result>                       mKavaTokenPrices = new HashMap<>();
    public ResKavaIncentiveParam.IncentiveParam                             mKavaIncentiveParam;
    public ArrayList<ResKavaIncentiveReward.KavaUnclaimedIncentiveReward>   mKavaUnClaimedIncentiveRewards = new ArrayList<>();

    public ArrayList<BnbToken>                                              mBnbTokens = new ArrayList<>();
    public ArrayList<ResBnbFee>                                             mBnbFees = new ArrayList<>();

    public ResOkDeposit                                                     mOkDeposit;
    public ResOkWithdraw                                                    mOkWithdraw;
    public ResOkTokenList                                                   mOkTokenList;

    public ResIovFee.IovFee                                                 mStarNameFee;

    public BigDecimal getBnbTransferFee() {
        BigDecimal result =  BigDecimal.ZERO;
        for (ResBnbFee fee: mBnbFees) {
            if (fee.fixed_fee_params != null) {
                if (fee.fixed_fee_params.msg_type.equals("send")) {
                    result = new BigDecimal(fee.fixed_fee_params.fee).movePointLeft(8);
                }
            }
        }
        return result;
    }

    public BaseData(BaseApplication apps) {
        this.mApp = apps;
        this.mSharedPreferences = getSharedPreferences();
        SQLiteDatabase.loadLibs(mApp);
    }

    private SharedPreferences getSharedPreferences() {
        if(mSharedPreferences == null)
            mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(mApp);
        return mSharedPreferences;
    }

    public SQLiteDatabase getBaseDB() {
        if(mSQLiteDatabase == null) {
            mSQLiteDatabase = BaseDB.getInstance(mApp).getWritableDatabase(mApp.getString(R.string.db_password));
        }
        return mSQLiteDatabase;
    }


    public void setLastPriceTic(BaseChain chain, ResCgcTic tic) {
        if (chain.equals(BaseChain.COSMOS_MAIN)) {
            if (getCurrency() == 0) {
                getSharedPreferences().edit().putString(BaseConstant.PRE_ATOM_TIC, ""+tic.market_data.current_price.usd).commit();
                getSharedPreferences().edit().putString(BaseConstant.PRE_ATOM_UP_DOWN_24, ""+tic.market_data.price_change_24h.usd).commit();
            } else if (getCurrency() == 1) {
                getSharedPreferences().edit().putString(BaseConstant.PRE_ATOM_TIC, ""+tic.market_data.current_price.eur).commit();
                getSharedPreferences().edit().putString(BaseConstant.PRE_ATOM_UP_DOWN_24, ""+tic.market_data.price_change_24h.eur).commit();
            } else if (getCurrency() == 2) {
                getSharedPreferences().edit().putString(BaseConstant.PRE_ATOM_TIC, ""+tic.market_data.current_price.krw).commit();
                getSharedPreferences().edit().putString(BaseConstant.PRE_ATOM_UP_DOWN_24, ""+tic.market_data.price_change_24h.krw).commit();
            } else if (getCurrency() == 3) {
                getSharedPreferences().edit().putString(BaseConstant.PRE_ATOM_TIC, ""+tic.market_data.current_price.jpy).commit();
                getSharedPreferences().edit().putString(BaseConstant.PRE_ATOM_UP_DOWN_24, ""+tic.market_data.price_change_24h.jpy).commit();
            } else if (getCurrency() == 4) {
                getSharedPreferences().edit().putString(BaseConstant.PRE_ATOM_TIC, ""+tic.market_data.current_price.cny).commit();
                getSharedPreferences().edit().putString(BaseConstant.PRE_ATOM_UP_DOWN_24, ""+tic.market_data.price_change_24h.cny).commit();
            } else if (getCurrency() == 5) {
                getSharedPreferences().edit().putString(BaseConstant.PRE_ATOM_TIC, ""+tic.market_data.current_price.btc).commit();
                getSharedPreferences().edit().putString(BaseConstant.PRE_ATOM_UP_DOWN_24, ""+tic.market_data.price_change_24h.btc).commit();
            }

        } else if (chain.equals(BaseChain.IRIS_MAIN)) {
            if (getCurrency() == 0) {
                getSharedPreferences().edit().putString(BaseConstant.PRE_IRIS_TIC, ""+tic.market_data.current_price.usd).commit();
                getSharedPreferences().edit().putString(BaseConstant.PRE_IRIS_UP_DOWN_24, ""+tic.market_data.price_change_24h.usd).commit();
            } else if (getCurrency() == 1) {
                getSharedPreferences().edit().putString(BaseConstant.PRE_IRIS_TIC, ""+tic.market_data.current_price.eur).commit();
                getSharedPreferences().edit().putString(BaseConstant.PRE_IRIS_UP_DOWN_24, ""+tic.market_data.price_change_24h.eur).commit();
            } else if (getCurrency() == 2) {
                getSharedPreferences().edit().putString(BaseConstant.PRE_IRIS_TIC, ""+tic.market_data.current_price.krw).commit();
                getSharedPreferences().edit().putString(BaseConstant.PRE_IRIS_UP_DOWN_24, ""+tic.market_data.price_change_24h.krw).commit();
            } else if (getCurrency() == 3) {
                getSharedPreferences().edit().putString(BaseConstant.PRE_IRIS_TIC, ""+tic.market_data.current_price.jpy).commit();
                getSharedPreferences().edit().putString(BaseConstant.PRE_IRIS_UP_DOWN_24, ""+tic.market_data.price_change_24h.jpy).commit();
            } else if (getCurrency() == 4) {
                getSharedPreferences().edit().putString(BaseConstant.PRE_IRIS_TIC, ""+tic.market_data.current_price.cny).commit();
                getSharedPreferences().edit().putString(BaseConstant.PRE_IRIS_UP_DOWN_24, ""+tic.market_data.price_change_24h.cny).commit();
            } else if (getCurrency() == 5) {
                getSharedPreferences().edit().putString(BaseConstant.PRE_IRIS_TIC, ""+tic.market_data.current_price.btc).commit();
                getSharedPreferences().edit().putString(BaseConstant.PRE_IRIS_UP_DOWN_24, ""+tic.market_data.price_change_24h.btc).commit();
            }

        } else if (chain.equals(BaseChain.BNB_MAIN) || chain.equals(BNB_TEST)) {
            if (getCurrency() == 0) {
                getSharedPreferences().edit().putString(BaseConstant.PRE_BNB_TIC, ""+tic.market_data.current_price.usd).commit();
                getSharedPreferences().edit().putString(BaseConstant.PRE_BNB_UP_DOWN_24, ""+tic.market_data.price_change_24h.usd).commit();
            } else if (getCurrency() == 1) {
                getSharedPreferences().edit().putString(BaseConstant.PRE_BNB_TIC, ""+tic.market_data.current_price.eur).commit();
                getSharedPreferences().edit().putString(BaseConstant.PRE_BNB_UP_DOWN_24, ""+tic.market_data.price_change_24h.eur).commit();
            } else if (getCurrency() == 2) {
                getSharedPreferences().edit().putString(BaseConstant.PRE_BNB_TIC, ""+tic.market_data.current_price.krw).commit();
                getSharedPreferences().edit().putString(BaseConstant.PRE_BNB_UP_DOWN_24, ""+tic.market_data.price_change_24h.krw).commit();
            } else if (getCurrency() == 3) {
                getSharedPreferences().edit().putString(BaseConstant.PRE_BNB_TIC, ""+tic.market_data.current_price.jpy).commit();
                getSharedPreferences().edit().putString(BaseConstant.PRE_BNB_UP_DOWN_24, ""+tic.market_data.price_change_24h.jpy).commit();
            } else if (getCurrency() == 4) {
                getSharedPreferences().edit().putString(BaseConstant.PRE_BNB_TIC, ""+tic.market_data.current_price.cny).commit();
                getSharedPreferences().edit().putString(BaseConstant.PRE_BNB_UP_DOWN_24, ""+tic.market_data.price_change_24h.cny).commit();
            } else if (getCurrency() == 5) {
                getSharedPreferences().edit().putString(BaseConstant.PRE_BNB_TIC, ""+tic.market_data.current_price.btc).commit();
                getSharedPreferences().edit().putString(BaseConstant.PRE_BNB_UP_DOWN_24, ""+tic.market_data.price_change_24h.btc).commit();
            }

        } else if (chain.equals(BaseChain.KAVA_MAIN) || chain.equals(KAVA_TEST)) {
            getSharedPreferences().edit().putString(BaseConstant.PRE_KAVA_DOLLOR_TIC, ""+tic.market_data.current_price.usd).commit();
            if (getCurrency() == 0) {
                getSharedPreferences().edit().putString(BaseConstant.PRE_KAVA_TIC, ""+tic.market_data.current_price.usd).commit();
                getSharedPreferences().edit().putString(BaseConstant.PRE_KAVA_UP_DOWN_24, ""+tic.market_data.price_change_24h.usd).commit();
            } else if (getCurrency() == 1) {
                getSharedPreferences().edit().putString(BaseConstant.PRE_KAVA_TIC, ""+tic.market_data.current_price.eur).commit();
                getSharedPreferences().edit().putString(BaseConstant.PRE_KAVA_UP_DOWN_24, ""+tic.market_data.price_change_24h.eur).commit();
            } else if (getCurrency() == 2) {
                getSharedPreferences().edit().putString(BaseConstant.PRE_KAVA_TIC, ""+tic.market_data.current_price.krw).commit();
                getSharedPreferences().edit().putString(BaseConstant.PRE_KAVA_UP_DOWN_24, ""+tic.market_data.price_change_24h.krw).commit();
            } else if (getCurrency() == 3) {
                getSharedPreferences().edit().putString(BaseConstant.PRE_KAVA_TIC, ""+tic.market_data.current_price.jpy).commit();
                getSharedPreferences().edit().putString(BaseConstant.PRE_KAVA_UP_DOWN_24, ""+tic.market_data.price_change_24h.jpy).commit();
            } else if (getCurrency() == 4) {
                getSharedPreferences().edit().putString(BaseConstant.PRE_KAVA_TIC, ""+tic.market_data.current_price.cny).commit();
                getSharedPreferences().edit().putString(BaseConstant.PRE_KAVA_UP_DOWN_24, ""+tic.market_data.price_change_24h.cny).commit();
            } else if (getCurrency() == 5) {
                getSharedPreferences().edit().putString(BaseConstant.PRE_KAVA_TIC, ""+tic.market_data.current_price.btc).commit();
                getSharedPreferences().edit().putString(BaseConstant.PRE_KAVA_UP_DOWN_24, ""+tic.market_data.price_change_24h.btc).commit();
            }

        } else if (chain.equals(BaseChain.BAND_MAIN)) {
            if (getCurrency() == 0) {
                getSharedPreferences().edit().putString(BaseConstant.PRE_BAND_TIC, ""+tic.market_data.current_price.usd).commit();
                getSharedPreferences().edit().putString(BaseConstant.PRE_BAND_UP_DOWN_24, ""+tic.market_data.price_change_24h.usd).commit();
            } else if (getCurrency() == 1) {
                getSharedPreferences().edit().putString(BaseConstant.PRE_BAND_TIC, ""+tic.market_data.current_price.eur).commit();
                getSharedPreferences().edit().putString(BaseConstant.PRE_BAND_UP_DOWN_24, ""+tic.market_data.price_change_24h.eur).commit();
            } else if (getCurrency() == 2) {
                getSharedPreferences().edit().putString(BaseConstant.PRE_BAND_TIC, ""+tic.market_data.current_price.krw).commit();
                getSharedPreferences().edit().putString(BaseConstant.PRE_BAND_UP_DOWN_24, ""+tic.market_data.price_change_24h.krw).commit();
            } else if (getCurrency() == 3) {
                getSharedPreferences().edit().putString(BaseConstant.PRE_BAND_TIC, ""+tic.market_data.current_price.jpy).commit();
                getSharedPreferences().edit().putString(BaseConstant.PRE_BAND_UP_DOWN_24, ""+tic.market_data.price_change_24h.jpy).commit();
            } else if (getCurrency() == 4) {
                getSharedPreferences().edit().putString(BaseConstant.PRE_BAND_TIC, ""+tic.market_data.current_price.cny).commit();
                getSharedPreferences().edit().putString(BaseConstant.PRE_BAND_UP_DOWN_24, ""+tic.market_data.price_change_24h.cny).commit();
            } else if (getCurrency() == 5) {
                getSharedPreferences().edit().putString(BaseConstant.PRE_BAND_TIC, ""+tic.market_data.current_price.btc).commit();
                getSharedPreferences().edit().putString(BaseConstant.PRE_BAND_UP_DOWN_24, ""+tic.market_data.price_change_24h.btc).commit();
            }

        }

    }


    public void setLastAtomTic(Double price) {
        getSharedPreferences().edit().putString(BaseConstant.PRE_ATOM_TIC, ""+price).commit();
    }

    public double getLastAtomTic() {
        String priceS = getSharedPreferences().getString(BaseConstant.PRE_ATOM_TIC, "0");
        try {
            return Double.parseDouble(priceS);
        }catch (Exception e) {
            return Double.parseDouble("0");
        }
    }

    public void setLastAtomUpDown(Double price) {
        getSharedPreferences().edit().putString(BaseConstant.PRE_ATOM_UP_DOWN_24, ""+price).commit();
    }

    public double getLastAtomUpDown() {
        String priceS = getSharedPreferences().getString(BaseConstant.PRE_ATOM_UP_DOWN_24, "0");
        try {
            return Double.parseDouble(priceS);
        }catch (Exception e) {
            return Double.parseDouble("0");
        }
    }


    public void setLastIrisTic(Double price) {
        getSharedPreferences().edit().putString(BaseConstant.PRE_IRIS_TIC, ""+price).commit();
    }

    public double getLastIrisTic() {
        String priceS = getSharedPreferences().getString(BaseConstant.PRE_IRIS_TIC, "0");
        try {
            return Double.parseDouble(priceS);
        }catch (Exception e) {
            return Double.parseDouble("0");
        }
    }

    public void setLastIrisUpDown(Double price) {
        getSharedPreferences().edit().putString(BaseConstant.PRE_IRIS_UP_DOWN_24, ""+price).commit();
    }

    public double getLastIrisUpDown() {
        String priceS = getSharedPreferences().getString(BaseConstant.PRE_IRIS_UP_DOWN_24, "0");
        try {
            return Double.parseDouble(priceS);
        }catch (Exception e) {
            return Double.parseDouble("0");
        }
    }


    public void setLastBnbTic(Double price) {
        getSharedPreferences().edit().putString(BaseConstant.PRE_BNB_TIC, ""+price).commit();
    }

    public double getLastBnbTic() {
        String priceS = getSharedPreferences().getString(BaseConstant.PRE_BNB_TIC, "0");
        try {
            return Double.parseDouble(priceS);
        }catch (Exception e) {
            return Double.parseDouble("0");
        }
    }

    public void setLastBnbUpDown(Double price) {
        getSharedPreferences().edit().putString(BaseConstant.PRE_BNB_UP_DOWN_24, ""+price).commit();
    }

    public double getLastBnbUpDown() {
        String priceS = getSharedPreferences().getString(BaseConstant.PRE_BNB_UP_DOWN_24, "0");
        try {
            return Double.parseDouble(priceS);
        }catch (Exception e) {
            return Double.parseDouble("0");
        }
    }


    public void setLastKavaTic(Double price) {
        getSharedPreferences().edit().putString(BaseConstant.PRE_KAVA_TIC, ""+price).commit();
    }

    public double getLastKavaTic() {
        String priceS = getSharedPreferences().getString(BaseConstant.PRE_KAVA_TIC, "0");
        try {
            return Double.parseDouble(priceS);
        } catch (Exception e) {
            return Double.parseDouble("0");
        }
    }

    public BigDecimal getLastKavaDollorTic() {
        String priceS = getSharedPreferences().getString(BaseConstant.PRE_KAVA_DOLLOR_TIC, "0");
        try {
            return new BigDecimal(priceS);
        } catch (Exception e) {
            return BigDecimal.ZERO;
        }
    }

    public void setLastKavaUpDown(Double price) {
        getSharedPreferences().edit().putString(BaseConstant.PRE_KAVA_UP_DOWN_24, ""+price).commit();
    }

    public double getLastKavaUpDown() {
        String priceS = getSharedPreferences().getString(BaseConstant.PRE_KAVA_UP_DOWN_24, "0");
        try {
            return Double.parseDouble(priceS);
        }catch (Exception e) {
            return Double.parseDouble("0");
        }
    }

    public void setLastBandTic(Double price) {
        getSharedPreferences().edit().putString(BaseConstant.PRE_BAND_TIC, ""+price).commit();
    }

    public double getLastBandTic() {
        String priceS = getSharedPreferences().getString(BaseConstant.PRE_BAND_TIC, "0");
        try {
            return Double.parseDouble(priceS);
        } catch (Exception e) {
            return Double.parseDouble("0");
        }
    }

    public void setLastBandUpDown(Double price) {
        getSharedPreferences().edit().putString(BaseConstant.PRE_BAND_UP_DOWN_24, ""+price).commit();
    }

    public double getLastBandUpDown() {
        String priceS = getSharedPreferences().getString(BaseConstant.PRE_BAND_UP_DOWN_24, "0");
        try {
            return Double.parseDouble(priceS);
        }catch (Exception e) {
            return Double.parseDouble("0");
        }
    }

    public void setLastIovTic(Double price) {
        getSharedPreferences().edit().putString(BaseConstant.PRE_IOV_TIC, ""+price).commit();
    }

    public double getLastIovTic() {
        String priceS = getSharedPreferences().getString(BaseConstant.PRE_IOV_TIC, "0");
        try {
            return Double.parseDouble(priceS);
        }catch (Exception e) {
            return Double.parseDouble("0");
        }
    }

    public void setLastIovUpDown(Double price) {
        getSharedPreferences().edit().putString(BaseConstant.PRE_IOV_UP_DOWN_24, ""+price).commit();
    }

    public double getLastIovUpDown() {
        String priceS = getSharedPreferences().getString(BaseConstant.PRE_IOV_UP_DOWN_24, "0");
        try {
            return Double.parseDouble(priceS);
        }catch (Exception e) {
            return Double.parseDouble("0");
        }
    }



    public void setValSorting(int sort) {
        getSharedPreferences().edit().putInt(BaseConstant.PRE_VALIDATOR_SORTING, sort).commit();
    }

    public int getValSorting() {
        return getSharedPreferences().getInt(BaseConstant.PRE_VALIDATOR_SORTING, 1);
    }


    public void setMyValSorting(int sort) {
        getSharedPreferences().edit().putInt(BaseConstant.PRE_MY_VALIDATOR_SORTING, sort).commit();
    }

    public int getMyValSorting() {
        return getSharedPreferences().getInt(BaseConstant.PRE_MY_VALIDATOR_SORTING, 1);
    }

    public void setLastUser(long user) {
        getSharedPreferences().edit().putLong(BaseConstant.PRE_USER_ID, user).commit();
    }

    public String getLastUser() {
        long result = -1;
        if(getSharedPreferences().getLong(BaseConstant.PRE_USER_ID, -1) != result) {
            result = getSharedPreferences().getLong(BaseConstant.PRE_USER_ID, -1);
        } else {
            if (onSelectAccounts().size() > 0) {
                result =  onSelectAccounts().get(0).accountNumber;
            }
        }
        return ""+result;
    }



    public int getLastChain() {
        int position =  getSharedPreferences().getInt(BaseConstant.PRE_SELECTED_CHAIN, 0);
        if (BaseChain.SUPPORT_CHAINS().size() < position) {
            return 0;
        } else {
            return position;
        }
    }

    public void setLastChain(int position) {
        getSharedPreferences().edit().putInt(BaseConstant.PRE_SELECTED_CHAIN, position).commit();
    }


    public int getCurrency() {
        return getSharedPreferences().getInt(BaseConstant.PRE_CURRENCY, 0);
    }

    public String getCurrencyString() {
        if (getCurrency() == 1) {
            return "EUR";
        } else if (getCurrency() == 2) {
            return "KRW";
        } else if (getCurrency() == 3) {
            return "JPY";
        } else if (getCurrency() == 4) {
            return "CNY";
        } else if (getCurrency() == 5) {
            return "BTC";
        } else {
            return "USD";
        }
    }

    public String getCurrencySymbol() {
        if (getCurrency() == 1) {
            return "€";
        } else if (getCurrency() == 2) {
            return "₩";
        } else if (getCurrency() == 3) {
            return "¥";
        } else if (getCurrency() == 4) {
            return "¥";
        } else if (getCurrency() == 5) {
            return "\u20BF";
        } else {
            return "$";
        }
    }

    public void setCurrency(int currency) {
        getSharedPreferences().edit().putInt(BaseConstant.PRE_CURRENCY, currency).commit();
    }

    public int getMarket() {
        //return always coingecko
//        return getSharedPreferences().getInt(BaseConstant.PRE_MARKET, 0);
        return 0;
    }

    public String getMarketString(Context c) {
        if (getMarket() == 0) {
            return c.getString(R.string.str_coingecko);
        } else {
            return c.getString(R.string.str_coinmarketcap);
        }
    }

    public void setMarket(int market) {
        getSharedPreferences().edit().putInt(BaseConstant.PRE_MARKET, market).commit();
    }


    public boolean getUsingAppLock() {
        return getSharedPreferences().getBoolean(BaseConstant.PRE_USING_APP_LOCK, false);
    }

    public void setUsingAppLock(boolean using) {
        getSharedPreferences().edit().putBoolean(BaseConstant.PRE_USING_APP_LOCK, using).commit();
    }

    public boolean getUsingFingerPrint() {
        return getSharedPreferences().getBoolean(BaseConstant.PRE_USING_FINGERPRINT, false);
    }

    public void setUsingFingerPrint(boolean using) {
        getSharedPreferences().edit().putBoolean(BaseConstant.PRE_USING_FINGERPRINT, using).commit();
    }

    public int getAppLockTriggerTime() {
        return getSharedPreferences().getInt(BaseConstant.PRE_APP_LOCK_TIME, 0);
    }

    public void setAppLockTriggerTime(int trigger) {
        getSharedPreferences().edit().putInt(BaseConstant.PRE_APP_LOCK_TIME, trigger).commit();
    }

    public long getAppLockLeaveTime() {
        return getSharedPreferences().getLong(BaseConstant.PRE_APP_LOCK_LEAVE_TIME, 0);
    }

    public void setAppLockLeaveTime() {
        getSharedPreferences().edit().putLong(BaseConstant.PRE_APP_LOCK_LEAVE_TIME, System.currentTimeMillis()).commit();
    }

    public String getAppLockLeaveTimeString(Context c) {
        WLog.w("getAppLockLeaveTime " + getAppLockTriggerTime());
        if (getAppLockTriggerTime() == 1) {
            return c.getString(R.string.str_applock_time_10sec);
        } else if (getAppLockTriggerTime() == 2) {
            return c.getString(R.string.str_applock_time_30sec);
        } else if (getAppLockTriggerTime() == 3) {
            return c.getString(R.string.str_applock_time_60sec);
        } else {
            return c.getString(R.string.str_applock_time_immediately);
        }
    }

    public void setFCMToken(String token) {
        getSharedPreferences().edit().putString(BaseConstant.PRE_FCM_TOKEN, token).commit();
    }

    public String getFCMToken() {
        return  getSharedPreferences().getString(BaseConstant.PRE_FCM_TOKEN, "");
    }



    public void setTokenSorting(int sort) {
        getSharedPreferences().edit().putInt(BaseConstant.PRE_TOKEN_SORTING, sort).commit();
    }

    public int getTokenSorting() {
        return getSharedPreferences().getInt(BaseConstant.PRE_TOKEN_SORTING, 1);
    }

    public void setKavaWarn() {
        Date dt = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.add(Calendar.DATE, 3);
        getSharedPreferences().edit().putLong(BaseConstant.PRE_KAVA_TESTNET_WARN, c.getTimeInMillis()).commit();
    }

    public boolean getKavaWarn() {
        Date dt = new Date();
        if (dt.getTime() > getSharedPreferences().getLong(BaseConstant.PRE_KAVA_TESTNET_WARN, 1)) {
            return true;
        }
        return false;
    }

    public Password onSelectPassword() {
        Password result = null;
        Cursor cursor 	= getBaseDB().query(BaseConstant.DB_TABLE_PASSWORD, new String[]{"resource", "spec"}, null, null, null, null, null);
        if(cursor != null && cursor.moveToFirst()) {
            result = new Password(cursor.getString(0), cursor.getString(1));
        }
        cursor.close();
        return result;
    }

    public boolean onHasPassword() {
        boolean existed = false;
        Cursor cursor 	= getBaseDB().query(BaseConstant.DB_TABLE_PASSWORD, new String[]{"resource", "spec"}, null, null, null, null, null);
        if(cursor != null && cursor.getCount() > 0) {
            existed = true;
        }
        cursor.close();
        return existed;
    }

    public long onInsertPassword(Password password) {
        long result = -1;
        if(onHasPassword()) return result;

        ContentValues values = new ContentValues();
        values.put("resource",  password.resource);
        values.put("spec",      password.spec);
        return getBaseDB().insertOrThrow(BaseConstant.DB_TABLE_PASSWORD, null, values);
    }

    //    public ArrayList<Mnemonic> onSelectMnemonics() {
//        ArrayList<Mnemonic> result = new ArrayList<>();
//        Cursor cursor 	= getBaseDB().query(BaseConstant.DB_TABLE_MNEMONIC, new String[]{"id", "uuid", "resource", "spec", "dpMasterKey", "typeSize"}, null, null, null, null, null);
//        if(cursor != null && cursor.moveToFirst()) {
//            do {
//                Mnemonic mnemonic = new Mnemonic(
//                        cursor.getLong(0),
//                        cursor.getString(1),
//                        cursor.getString(2),
//                        cursor.getString(3),
//                        cursor.getString(4),
//                        cursor.getInt(5));
//                result.add(mnemonic);
//            } while (cursor.moveToNext());
//        }
//        cursor.close();
//        return result;
//    }
//
//    public Mnemonic onSelectMnemonic(String id) {
//        Mnemonic result = null;
//        Cursor cursor 	= getBaseDB().query(BaseConstant.DB_TABLE_MNEMONIC, new String[]{"id", "uuid", "resource", "spec", "dpMasterKey", "typeSize"}, "id == ?", new String[]{id}, null, null, null);
//        if(cursor != null && cursor.moveToFirst()) {
//            result = new Mnemonic(
//                    cursor.getLong(0),
//                    cursor.getString(1),
//                    cursor.getString(2),
//                    cursor.getString(3),
//                    cursor.getString(4),
//                    cursor.getInt(5));
//        }
//        cursor.close();
//        return result;
//    }
//
//    public long onInsertMnemonic(Mnemonic mnemonic) {
//        long result = -1;
//        if(isDupleMnemonic(mnemonic.dpMasterKey)) return result;
//        ContentValues values = new ContentValues();
//        values.put("uuid",          mnemonic.uuid);
//        values.put("resource",      mnemonic.resource);
//        values.put("spec",          mnemonic.spec);
//        values.put("dpMasterKey",   mnemonic.dpMasterKey);
//        values.put("typeSize",      mnemonic.typeSize);
//        return getBaseDB().insertOrThrow(BaseConstant.DB_TABLE_MNEMONIC, null, values);
//    }
//
//    public boolean onHasMnemonic() {
//        boolean existed = false;
//        Cursor cursor 	= getBaseDB().query(BaseConstant.DB_TABLE_MNEMONIC, new String[]{"id"}, null, null, null, null, null);
//        if(cursor != null && cursor.getCount() > 0) {
//            existed = true;
//        }
//        cursor.close();
//        return existed;
//    }
//
//    public boolean isDupleMnemonic(String dpMasterKey) {
//        boolean existed = false;
//        Cursor cursor 	= getBaseDB().query(BaseConstant.DB_TABLE_MNEMONIC, new String[]{"id"}, "dpMasterKey == ?", new String[]{dpMasterKey}, null, null, null);
//        if(cursor != null && cursor.getCount() > 0) {
//            existed = true;
//        }
//        cursor.close();
//        return existed;
//    }
//
//    public boolean onDeleteMnemonic(String id) {
//        return getBaseDB().delete(BaseConstant.DB_TABLE_MNEMONIC, "id = ?", new String[]{id}) > 0;
//    }



    public ArrayList<Account> onSelectAccounts() {
        ArrayList<Account> result = new ArrayList<>();
        Cursor cursor 	= getBaseDB().query(BaseConstant.DB_TABLE_ACCOUNT, new String[]{"id", "uuid", "nickName", "isFavo", "address", "baseChain",
                "hasPrivateKey", "resource", "spec", "fromMnemonic", "path",
                "isValidator", "sequenceNumber", "accountNumber", "fetchTime", "msize", "importTime", "lastTotal", "sortOrder", "pushAlarm", "newBip"}, null, null, null, null, null);
        if(cursor != null && cursor.moveToFirst()) {
            do {
                Account account = new Account(
                        cursor.getLong(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getInt(3) > 0,
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getInt(6) > 0,
                        cursor.getString(7),
                        cursor.getString(8),
                        cursor.getInt(9) > 0,
                        cursor.getString(10),
                        cursor.getInt(11) > 0,
                        cursor.getInt(12),
                        cursor.getInt(13),
                        cursor.getLong(14),
                        cursor.getInt(15),
                        cursor.getLong(16),
                        cursor.getString(17),
                        cursor.getLong(18),
                        cursor.getInt(19) > 0,
                        cursor.getInt(20) > 0
                );
                account.setBalances(onSelectBalance(account.id));
                result.add(account);
            } while (cursor.moveToNext());
        }
        cursor.close();

        Iterator<Account> iterator = result.iterator();
        while(iterator.hasNext()){
            Account account = iterator.next();
            if (!BaseChain.IS_SUPPORT_CHAIN(account.baseChain)) {
                iterator.remove();
            }
        }
        return result;
    }

    public ArrayList<Account> onSelectAccountsByChain(BaseChain chain) {
        ArrayList<Account> result = new ArrayList<>();
        ArrayList<Account> AllAccount = onSelectAccounts();
        for (Account account:AllAccount) {
            if (BaseChain.getChain(account.baseChain).equals(chain)) {
                result.add(account);
            }
        }
        return result;
    }

    public ArrayList<Account> onSelectAccountsByHtlcClaim(BaseChain chain) {
        ArrayList<Account> result = new ArrayList<>();
        ArrayList<Account> AllAccount = onSelectAccounts();
        for (Account account:AllAccount) {
            if (BaseChain.getChain(account.baseChain).equals(chain) && account.hasPrivateKey) {
                if (chain.equals(KAVA_MAIN) || chain.equals(KAVA_TEST)) {
                    result.add(account);

                } else if (chain.equals(BNB_MAIN) || chain.equals(BNB_TEST)) {
                    if (WDp.getAvailableCoin(account.balances, TOKEN_BNB).compareTo(new BigDecimal(FEE_BNB_SEND)) >= 0) {
                        result.add(account);
                    }
                }
            }
        }

        return result;
    }

    public Account onSelectAccount(String id) {
        Account result = null;
        Cursor cursor 	= getBaseDB().query(BaseConstant.DB_TABLE_ACCOUNT, new String[]{"id", "uuid", "nickName", "isFavo", "address", "baseChain",
                "hasPrivateKey", "resource", "spec", "fromMnemonic", "path",
                "isValidator", "sequenceNumber", "accountNumber", "fetchTime", "msize", "importTime", "lastTotal", "sortOrder", "pushAlarm", "newBip"}, "id == ?", new String[]{id}, null, null, null);
        if(cursor != null && cursor.moveToFirst()) {
            result = new Account(
                    cursor.getLong(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getInt(3) > 0,
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getInt(6) > 0,
                    cursor.getString(7),
                    cursor.getString(8),
                    cursor.getInt(9) > 0,
                    cursor.getString(10),
                    cursor.getInt(11) > 0,
                    cursor.getInt(12),
                    cursor.getInt(13),
                    cursor.getLong(14),
                    cursor.getInt(15),
                    cursor.getLong(16),
                    cursor.getString(17),
                    cursor.getLong(18),
                    cursor.getInt(19) > 0,
                    cursor.getInt(20) > 0
            );
            result.setBalances(onSelectBalance(result.id));
        }
        cursor.close();
        if (!BaseChain.IS_SUPPORT_CHAIN(result.baseChain)) {
            return onSelectAccounts().get(0);
        }
        return result;
    }

    public Account onSelectExistAccount(String address, BaseChain chain) {
        Account result = null;
        Cursor cursor 	= getBaseDB().query(BaseConstant.DB_TABLE_ACCOUNT, new String[]{"id", "uuid", "nickName", "isFavo", "address", "baseChain",
                "hasPrivateKey", "resource", "spec", "fromMnemonic", "path",
                "isValidator", "sequenceNumber", "accountNumber", "fetchTime", "msize", "importTime", "lastTotal", "sortOrder", "pushAlarm", "newBip"}, "address == ?", new String[]{address}, null, null, null);
        if(cursor != null && cursor.moveToFirst()) {
            result = new Account(
                    cursor.getLong(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getInt(3) > 0,
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getInt(6) > 0,
                    cursor.getString(7),
                    cursor.getString(8),
                    cursor.getInt(9) > 0,
                    cursor.getString(10),
                    cursor.getInt(11) > 0,
                    cursor.getInt(12),
                    cursor.getInt(13),
                    cursor.getLong(14),
                    cursor.getInt(15),
                    cursor.getLong(16),
                    cursor.getString(17),
                    cursor.getLong(18),
                    cursor.getInt(19) > 0,
                    cursor.getInt(20) > 0
            );
            result.setBalances(onSelectBalance(result.id));
        }
        cursor.close();
        if (result != null && chain.equals(BaseChain.getChain(result.baseChain))) {
            return result;
        } else {
            return null;
        }
    }

    public Account onSelectExistAccount2(String address) {
        Account result = null;
        Cursor cursor 	= getBaseDB().query(BaseConstant.DB_TABLE_ACCOUNT, new String[]{"id", "uuid", "nickName", "isFavo", "address", "baseChain",
                "hasPrivateKey", "resource", "spec", "fromMnemonic", "path",
                "isValidator", "sequenceNumber", "accountNumber", "fetchTime", "msize", "importTime", "lastTotal", "sortOrder", "pushAlarm", "newBip"}, "address == ?", new String[]{address}, null, null, null);
        if(cursor != null && cursor.moveToFirst()) {
            result = new Account(
                    cursor.getLong(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getInt(3) > 0,
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getInt(6) > 0,
                    cursor.getString(7),
                    cursor.getString(8),
                    cursor.getInt(9) > 0,
                    cursor.getString(10),
                    cursor.getInt(11) > 0,
                    cursor.getInt(12),
                    cursor.getInt(13),
                    cursor.getLong(14),
                    cursor.getInt(15),
                    cursor.getLong(16),
                    cursor.getString(17),
                    cursor.getLong(18),
                    cursor.getInt(19) > 0,
                    cursor.getInt(20) > 0
            );
            result.setBalances(onSelectBalance(result.id));
        }
        cursor.close();
        return result;
    }

    public long onInsertAccount(Account account) {
        long result = -1;
        if(isDupleAccount(account.address, account.baseChain)) return result;
        ContentValues values = new ContentValues();
        values.put("uuid",              account.uuid);
        values.put("nickName",          account.nickName);
        values.put("isFavo",            account.isFavo);
        values.put("address",           account.address);
        values.put("baseChain",         account.baseChain);
        values.put("hasPrivateKey",     account.hasPrivateKey);
        values.put("resource",          account.resource);
        values.put("spec",              account.spec);
        values.put("fromMnemonic",      account.fromMnemonic);
        values.put("path",              account.path);
        values.put("isValidator",       account.isValidator);
        values.put("sequenceNumber",    account.sequenceNumber);
        values.put("accountNumber",     account.accountNumber);
        values.put("fetchTime",         account.fetchTime);
        values.put("msize",             account.msize);
        values.put("importTime",        account.importTime);
        values.put("sortOrder",         9999l);
        values.put("pushAlarm",         account.pushAlarm);
        values.put("newBip",            account.newBip44);
        return getBaseDB().insertOrThrow(BaseConstant.DB_TABLE_ACCOUNT, null, values);
    }

    public long onUpdateAccount(Account account) {
        ContentValues values = new ContentValues();
        if(!TextUtils.isEmpty(account.nickName))
            values.put("nickName",          account.nickName);
        if(account.isFavo != null)
            values.put("isFavo",            account.isFavo);
        if(account.sequenceNumber != null)
            values.put("sequenceNumber",    account.sequenceNumber);
        if(account.accountNumber != null)
            values.put("accountNumber",    account.accountNumber);
        if(account.fetchTime != null)
            values.put("fetchTime",         account.fetchTime);
        if(account.baseChain != null)
            values.put("baseChain",         account.baseChain);
        return getBaseDB().update(BaseConstant.DB_TABLE_ACCOUNT, values, "id = ?", new String[]{""+account.id} );
    }

    public long onUpdateLastTotalAccount(Account account, String amount) {
        ContentValues values = new ContentValues();
        values.put("lastTotal",          amount);
        return getBaseDB().update(BaseConstant.DB_TABLE_ACCOUNT, values, "id = ?", new String[]{""+account.id} );
    }

    public void onUpdateAccountOrders(ArrayList<Account> accounts) {
        for (Account account:accounts) {
            ContentValues values = new ContentValues();
            values.put("sortOrder",          account.sortOrder);
            getBaseDB().update(BaseConstant.DB_TABLE_ACCOUNT, values, "id = ?", new String[]{""+account.id} );
        }
    }

    public Account onUpdatePushEnabled(Account account, boolean using) {
        ContentValues values = new ContentValues();
        values.put("pushAlarm",          using);
        getBaseDB().update(BaseConstant.DB_TABLE_ACCOUNT, values, "id = ?", new String[]{""+account.id} );
        return onSelectAccount(""+account.id);
    }

    public long onUpdateTestChain(Account account) {
        WLog.w("onUpdateTestChain : " + account.baseChain);
        ContentValues values = new ContentValues();
        values.put("baseChain",            account.baseChain);
        return getBaseDB().update(BaseConstant.DB_TABLE_ACCOUNT, values, "id = ?", new String[]{""+account.id} );
    }

    public long onOverrideAccount(Account account) {
        ContentValues values = new ContentValues();
        values.put("hasPrivateKey",     account.hasPrivateKey);
        values.put("resource",          account.resource);
        values.put("spec",              account.spec);
        values.put("fromMnemonic",      account.fromMnemonic);
        values.put("path",              account.path);
        values.put("msize",             account.msize);
        values.put("newBip",            account.newBip44);
        return getBaseDB().update(BaseConstant.DB_TABLE_ACCOUNT, values, "id = ?", new String[]{""+account.id} );
    }


    public boolean isDupleAccount(String address, String chain) {
        boolean existed = false;
        Cursor cursor 	= getBaseDB().query(BaseConstant.DB_TABLE_ACCOUNT, new String[]{"id"}, "address == ? AND baseChain == ?", new String[]{address, chain}, null, null, null);
        if(cursor != null && cursor.getCount() > 0) {
            existed = true;
        }
        cursor.close();
        return existed;
    }

    public boolean onDeleteAccount(String id) {
        //TODO delete Tx or else data with this account
        onDeleteBalance(id);
        return getBaseDB().delete(BaseConstant.DB_TABLE_ACCOUNT, "id = ?", new String[]{id}) > 0;
    }



    public ArrayList<Balance> onSelectBalance(long accountId) {
        ArrayList<Balance> result = new ArrayList<>();
        Cursor cursor 	= getBaseDB().query(BaseConstant.DB_TABLE_BALANCE, new String[]{"accountId", "symbol", "balance", "fetchTime", "frozen", "locked"}, "accountId == ?", new String[]{""+accountId}, null, null, null);
        if(cursor != null && cursor.moveToFirst()) {
            do {
                Balance balance = new Balance(
                        cursor.getLong(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getLong(3),
                        cursor.getString(4),
                        cursor.getString(5));
                result.add(balance);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return result;
    }

    public long onInsertBalance(Balance balance) {
        if(onHasBalance(balance)) {
            return onUpdateBalance(balance);
        } else {
            ContentValues values = new ContentValues();
            values.put("accountId",         balance.accountId);
            values.put("symbol",            balance.symbol);
            values.put("balance",           balance.balance.toPlainString());
            values.put("fetchTime",         balance.fetchTime);
            if (balance.frozen != null)
                values.put("frozen",        balance.frozen.toPlainString());
            if (balance.locked != null)
                values.put("locked",        balance.locked.toPlainString());
            return getBaseDB().insertOrThrow(BaseConstant.DB_TABLE_BALANCE, null, values);
        }
    }

    public long onUpdateBalance(Balance balance) {
        onDeleteBalance(""+balance.accountId);
        return onInsertBalance(balance);
    }

    public void onUpdateBalances(long accountId,  ArrayList<Balance> balances) {
        if (balances == null || balances.size() == 0) {
            onDeleteBalance(""+accountId);
            return;
        }
        onDeleteBalance(""+balances.get(0).accountId);
        for(Balance balance : balances) {
            onInsertBalance(balance);
        }
    }

    public boolean onHasBalance(Balance balance) {
        boolean existed = false;
        Cursor cursor 	= getBaseDB().query(BaseConstant.DB_TABLE_BALANCE, new String[]{"accountId", "symbol", "balance", "fetchTime"}, "accountId == ? AND symbol == ? ", new String[]{""+balance.accountId, balance.symbol}, null, null, null);
        if(cursor != null && cursor.getCount() > 0) {
            existed = true;
        }
        cursor.close();
        return existed;
    }

    public boolean onDeleteBalance(String accountId) {
        return getBaseDB().delete(BaseConstant.DB_TABLE_BALANCE, "accountId = ?", new String[]{accountId}) > 0;
    }



    public ArrayList<BondingState> onSelectBondingStates(long accountId) {
        ArrayList<BondingState> result = new ArrayList<>();
        Cursor cursor 	= getBaseDB().query(BaseConstant.DB_TABLE_BONDING, new String[]{"accountId", "validatorAddress", "shares", "fetchTime"}, "accountId == ?", new String[]{""+accountId}, null, null, null);
        if(cursor != null && cursor.moveToFirst()) {
            do {
                BondingState delegate = new BondingState(
                        cursor.getLong(0),
                        cursor.getString(1),
                        new BigDecimal(cursor.getString(2)),
                        cursor.getLong(3));
                result.add(delegate);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return result;
    }

    public BondingState onSelectBondingState(long accountId, String vAddr) {
        BondingState result = null;
        Cursor cursor 	= getBaseDB().query(BaseConstant.DB_TABLE_BONDING, new String[]{"accountId", "validatorAddress", "shares", "fetchTime"}, "accountId == ? AND validatorAddress = ?", new String[]{""+accountId, vAddr}, null, null, null);
        if(cursor != null && cursor.moveToFirst()) {
            result = new BondingState(
                    cursor.getLong(0),
                    cursor.getString(1),
                    new BigDecimal(cursor.getString(2)),
                    cursor.getLong(3));
        }
        cursor.close();
        return result;
    }


    public long onInsertBondingStates(BondingState bonding) {
        ContentValues values = new ContentValues();
        values.put("accountId",         bonding.accountId);
        values.put("validatorAddress",  bonding.validatorAddress);
        values.put("shares",            bonding.shares.toPlainString());
        values.put("fetchTime",         bonding.fetchTime);
        return getBaseDB().insertOrThrow(BaseConstant.DB_TABLE_BONDING, null, values);

    }

    public void onUpdateBondingStates(long accountId, ArrayList<BondingState> bondings) {
        onDeleteBondingStates(accountId);
        for(BondingState bonding: bondings) {
            onInsertBondingStates(bonding);
        }
    }

    public void onUpdateBondingState(long accountId, BondingState bonding) {
        onDeleteBondingState(accountId, bonding.validatorAddress);
        onInsertBondingStates(bonding);
    }

    public boolean onHasBondingStates(BondingState bondingState) {
        boolean existed = false;
        Cursor cursor 	= getBaseDB().query(BaseConstant.DB_TABLE_BONDING, new String[]{"accountId", "validatorAddress", "shares", "fetchTime"}, "accountId == ? AND validatorAddress == ?", new String[]{""+bondingState.accountId, bondingState.validatorAddress}, null, null, null);
        if(cursor != null && cursor.getCount() > 0) {
            existed = true;
        }
        cursor.close();
        return existed;
    }

    public boolean onDeleteBondingStates(long accountId) {
        return getBaseDB().delete(BaseConstant.DB_TABLE_BONDING, "accountId = ?", new String[]{""+accountId}) > 0;
    }

    public boolean onDeleteBondingState(long accountId, String vAddr) {
        return getBaseDB().delete(BaseConstant.DB_TABLE_BONDING, "accountId = ? AND validatorAddress = ?", new String[]{""+accountId, vAddr}) > 0;
    }



    public ArrayList<UnBondingState> onSelectUnbondingStates(long accountId) {
        ArrayList<UnBondingState> result = new ArrayList<>();
        Cursor cursor 	= getBaseDB().query(BaseConstant.DB_TABLE_UNBONDING, new String[]{"accountId", "validatorAddress", "creationHeight", "completionTime", "initialBalance", "balance", "fetchTime"}, "accountId == ?", new String[]{""+accountId}, null, null, null);
        if(cursor != null && cursor.moveToFirst()) {
            do {
                UnBondingState temp = new UnBondingState(
                        cursor.getLong(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getLong(3),
                        new BigDecimal(cursor.getString(4)),
                        new BigDecimal(cursor.getString(5)),
                        cursor.getLong(6));
                result.add(temp);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return result;
    }

    public UnBondingState onSelectUnbondingState(long accountId, String vAddr) {
        UnBondingState result = null;
        Cursor cursor 	= getBaseDB().query(BaseConstant.DB_TABLE_UNBONDING, new String[]{"accountId", "validatorAddress", "creationHeight", "completionTime", "initialBalance", "balance", "fetchTime"}, "accountId == ? AND validatorAddress = ?", new String[]{""+accountId, vAddr}, null, null, null);
        if(cursor != null && cursor.moveToFirst()) {
            result = new UnBondingState(
                    cursor.getLong(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getLong(3),
                    new BigDecimal(cursor.getString(4)),
                    new BigDecimal(cursor.getString(5)),
                    cursor.getLong(6));
        }
        cursor.close();
        return result;
    }

    public ArrayList<UnBondingState> onSelectUnbondingStates(long accountId, String vAddr) {
        ArrayList<UnBondingState> result = new ArrayList<>();
        Cursor cursor 	= getBaseDB().query(BaseConstant.DB_TABLE_UNBONDING, new String[]{"accountId", "validatorAddress", "creationHeight", "completionTime", "initialBalance", "balance", "fetchTime"}, "accountId == ? AND validatorAddress = ?", new String[]{""+accountId, vAddr}, null, null, null);
        if(cursor != null && cursor.moveToFirst()) {
            do {
                UnBondingState temp = new UnBondingState(
                        cursor.getLong(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getLong(3),
                        new BigDecimal(cursor.getString(4)),
                        new BigDecimal(cursor.getString(5)),
                        cursor.getLong(6));
                result.add(temp);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return result;
    }

    public long onInsertUnbondingStates(UnBondingState unbonding) {
        ContentValues values = new ContentValues();
        values.put("accountId",         unbonding.accountId);
        values.put("validatorAddress",  unbonding.validatorAddress);
        values.put("creationHeight",    unbonding.creationHeight);
        values.put("completionTime",    unbonding.completionTime);
        values.put("initialBalance",    unbonding.initialBalance.toPlainString());
        values.put("balance",           unbonding.balance.toPlainString());
        values.put("fetchTime",         unbonding.fetchTime);
        return getBaseDB().insertOrThrow(BaseConstant.DB_TABLE_UNBONDING, null, values);
    }

    public boolean onDeleteUnbondingStates(long accountId) {
        return getBaseDB().delete(BaseConstant.DB_TABLE_UNBONDING, "accountId = ?", new String[]{""+accountId}) > 0;
    }

    public boolean onDeleteUnbondingState(long accountId, String vAddr) {
        return getBaseDB().delete(BaseConstant.DB_TABLE_UNBONDING, "accountId = ? AND validatorAddress = ?", new String[]{""+accountId, vAddr}) > 0;
    }

    public void onUpdateUnbondingStates(long accountId, ArrayList<UnBondingState> unbondings) {
        onDeleteUnbondingStates(accountId);
        for(UnBondingState unbond: unbondings) {
            onInsertUnbondingStates(unbond);
        }
    }

    public void onUpdateUnbondingState(long accountId, UnBondingState unbonding) {
        onDeleteUnbondingState(accountId, unbonding.validatorAddress);
        onInsertUnbondingStates(unbonding);
    }

}
