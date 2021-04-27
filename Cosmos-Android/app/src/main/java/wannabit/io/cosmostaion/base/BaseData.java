package wannabit.io.cosmostaion.base;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;

import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf2.Any;

import net.sqlcipher.Cursor;
import net.sqlcipher.database.SQLiteDatabase;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import cosmos.base.v1beta1.CoinOuterClass;
import cosmos.distribution.v1beta1.Distribution;
import cosmos.staking.v1beta1.Staking;
import cosmos.vesting.v1beta1.Vesting;
import irismod.token.TokenOuterClass;
import tendermint.p2p.Types;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.crypto.EncResult;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.dao.Balance;
import wannabit.io.cosmostaion.dao.BnbToken;
import wannabit.io.cosmostaion.dao.Password;
import wannabit.io.cosmostaion.model.BondingInfo;
import wannabit.io.cosmostaion.model.NodeInfo;
import wannabit.io.cosmostaion.model.RewardInfo;
import wannabit.io.cosmostaion.model.UnbondingInfo;
import wannabit.io.cosmostaion.model.kava.AuctionParam;
import wannabit.io.cosmostaion.model.kava.CdpParam;
import wannabit.io.cosmostaion.model.kava.HardMyBorrow;
import wannabit.io.cosmostaion.model.kava.HardMyDeposit;
import wannabit.io.cosmostaion.model.kava.HardParam;
import wannabit.io.cosmostaion.model.kava.IncentiveParam;
import wannabit.io.cosmostaion.model.kava.IncentiveReward;
import wannabit.io.cosmostaion.model.kava.MarketPrice;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.model.type.Validator;
import wannabit.io.cosmostaion.network.res.ResBandOracleStatus;
import wannabit.io.cosmostaion.network.res.ResBnbFee;
import wannabit.io.cosmostaion.network.res.ResCgcTic;
import wannabit.io.cosmostaion.network.res.ResIovConfig;
import wannabit.io.cosmostaion.network.res.ResIovFee;
import wannabit.io.cosmostaion.network.res.ResLcdKavaAccountInfo;
import wannabit.io.cosmostaion.network.res.ResMintParam;
import wannabit.io.cosmostaion.network.res.ResOkAccountInfo;
import wannabit.io.cosmostaion.network.res.ResOkStaking;
import wannabit.io.cosmostaion.network.res.ResOkTickersList;
import wannabit.io.cosmostaion.network.res.ResOkTokenList;
import wannabit.io.cosmostaion.network.res.ResOkUnbonding;
import wannabit.io.cosmostaion.network.res.ResStakingPool;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WKey;
import wannabit.io.cosmostaion.utils.WLog;
import wannabit.io.cosmostaion.utils.WUtil;

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
import static wannabit.io.cosmostaion.base.BaseChain.OKEX_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.OK_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.PERSIS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.SECRET_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.SENTINEL_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.SIF_MAIN;
import static wannabit.io.cosmostaion.base.BaseConstant.FEE_BNB_SEND;
import static wannabit.io.cosmostaion.base.BaseConstant.PRE_CRYPTO_TIC;
import static wannabit.io.cosmostaion.base.BaseConstant.PRE_CRYPTO_UP_DOWN_24;
import static wannabit.io.cosmostaion.base.BaseConstant.PRE_EVENT_HIDE;
import static wannabit.io.cosmostaion.base.BaseConstant.PRE_FETCH_TIC;
import static wannabit.io.cosmostaion.base.BaseConstant.PRE_FETCH_UP_DOWN_24;
import static wannabit.io.cosmostaion.base.BaseConstant.PRE_OKEX_TIC;
import static wannabit.io.cosmostaion.base.BaseConstant.PRE_OKEX_UP_DOWN_24;
import static wannabit.io.cosmostaion.base.BaseConstant.PRE_PERSISTENCE_TIC;
import static wannabit.io.cosmostaion.base.BaseConstant.PRE_PERSISTENCE_UP_DOWN_24;
import static wannabit.io.cosmostaion.base.BaseConstant.PRE_SECRET_TIC;
import static wannabit.io.cosmostaion.base.BaseConstant.PRE_SECRET_UP_DOWN_24;
import static wannabit.io.cosmostaion.base.BaseConstant.PRE_SENTINEL_TIC;
import static wannabit.io.cosmostaion.base.BaseConstant.PRE_SENTINEL_UP_DOWN_24;
import static wannabit.io.cosmostaion.base.BaseConstant.PRE_SIF_TIC;
import static wannabit.io.cosmostaion.base.BaseConstant.PRE_SIF_UP_DOWN_24;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_BNB;

public class BaseData {

    private BaseApplication         mApp;
    private SharedPreferences       mSharedPreferences;
    private SQLiteDatabase          mSQLiteDatabase;
    public String                   mCopySalt;
    public EncResult                mCopyEncResult;

    //COMMON DATA
    public NodeInfo                     mNodeInfo;
    public ArrayList<Validator>         mAllValidators = new ArrayList<>();
    public ArrayList<Validator>         mMyValidators = new ArrayList<>();
    public ArrayList<Validator>         mTopValidators = new ArrayList<>();
    public ArrayList<Validator>         mOtherValidators = new ArrayList<>();

    public ArrayList<Balance>           mBalances = new ArrayList<>();
    public ArrayList<BondingInfo>       mMyDelegations = new ArrayList<>();
    public ArrayList<UnbondingInfo>     mMyUnbondings = new ArrayList<>();
    public ArrayList<RewardInfo>        mMyRewards = new ArrayList<>();

    public ResStakingPool               mStakingPool;
    public BigDecimal                   mInflation = BigDecimal.ZERO;
    public BigDecimal                   mProvisions = BigDecimal.ZERO;
    public ResMintParam.MintParam       mMintParam;

    //COMMON DATA FOR KAVA-7
    public ResLcdKavaAccountInfo.Result     mKavaAccount;
    public CdpParam                         mCdpParam;
    public IncentiveParam                   mIncentiveParam5;
    public HardParam                        mHardParam;
    public AuctionParam                     mAuctionParam;
    public IncentiveReward                  mIncentiveRewards;
    public ArrayList<HardMyDeposit>         mMyHardDeposit = new ArrayList<>();
    public ArrayList<HardMyBorrow>          mMyHardBorrow = new ArrayList<>();
    public ArrayList<Coin>                  mModuleCoins = new ArrayList<>();
    public ArrayList<Coin>                  mReserveCoins = new ArrayList<>();
    public HashMap<String, MarketPrice>     mKavaTokenPrices = new HashMap<>();

    //COMMON DATA FOR BINANCE
    public ArrayList<BnbToken>      mBnbTokens = new ArrayList<>();
    public ArrayList<ResBnbFee>     mBnbFees = new ArrayList<>();

    //COMMON DATA FOR OKEX
    public ResOkAccountInfo         mOkAccountInfo;
    public ResOkStaking             mOkStaking;
    public ResOkUnbonding           mOkUnbonding;
    public ResOkTokenList           mOkTokenList;
    public ResOkTickersList         mOkTickersList;
    public BigDecimal               mOKBPrice = BigDecimal.ZERO;

    //COMMON DATA FOR STARNAME
    public ResIovFee.IovFee         mStarNameFee;
    public ResIovConfig.IovConfig   mStarNameConfig;

    //COMMON DATA FOR BAND
    public ResBandOracleStatus      mBandOracles;

    public BigDecimal availableAmount(String denom) {
        BigDecimal result = BigDecimal.ZERO;
        for (Balance balance: mBalances) {
            if (balance.symbol.equalsIgnoreCase(denom)) {
                result = balance.balance;
            }
        }
        return result;
    }

    public BigDecimal lockedAmount(String denom) {
        BigDecimal result = BigDecimal.ZERO;
        for (Balance balance: mBalances) {
            if (balance.symbol.equalsIgnoreCase(denom)) {
                result = balance.locked;
            }
        }
        return result;
    }

    public BigDecimal delegatedSumAmount() {
        BigDecimal result = BigDecimal.ZERO;
        for (BondingInfo bondingInfo: mMyDelegations) {
            if (bondingInfo.balance != null) {
                result = result.add(new BigDecimal(bondingInfo.balance.amount));
            }
        }
        return result;
    }

    public BigDecimal delegatedAmountByValidator(String opAddress) {
        BigDecimal result = BigDecimal.ZERO;
        for (BondingInfo bondingInfo: mMyDelegations) {
            if (bondingInfo.validator_address.equals(opAddress) && bondingInfo.balance != null) {
                result = result.add(new BigDecimal(bondingInfo.balance.amount));
            }
        }
        return result;
    }

    public BigDecimal unbondingSumAmount() {
        BigDecimal result = BigDecimal.ZERO;
        for (UnbondingInfo unbondingInfo: mMyUnbondings) {
            if (unbondingInfo.entries != null) {
                for (UnbondingInfo.Entry entry: unbondingInfo.entries) {
                    result = result.add(new BigDecimal(entry.balance));
                }

            }
        }
        return result;
    }

    public BigDecimal unbondingAmountByValidator(String opAddress) {
        BigDecimal result = BigDecimal.ZERO;
        for (UnbondingInfo unbondingInfo: mMyUnbondings) {
            if (unbondingInfo.validator_address.equals(opAddress) && unbondingInfo.entries != null) {
                for (UnbondingInfo.Entry entry: unbondingInfo.entries) {
                    result = result.add(new BigDecimal(entry.balance));
                }
            }
        }
        return result;
    }

    public BigDecimal rewardAmount(String denom) {
        BigDecimal result = BigDecimal.ZERO;
        for (RewardInfo rewardInfo: mMyRewards) {
            if (rewardInfo.reward != null) {
                for (Coin coin: rewardInfo.reward) {
                    if (coin.denom.equals(denom)) {
                        result = result.add(new BigDecimal(coin.amount).setScale(0, RoundingMode.DOWN));
                    }
                }

            }
        }
        return result;
    }

    public BigDecimal rewardAmountByValidator(String denom, String opAddress) {
        BigDecimal result = BigDecimal.ZERO;
        for (RewardInfo rewardInfo: mMyRewards) {
            if (rewardInfo.validator_address.equals(opAddress) && rewardInfo.reward != null) {
                for (Coin coin: rewardInfo.reward) {
                    if (coin.denom.equals(denom)) {
                        result = result.add(new BigDecimal(coin.amount).setScale(0, RoundingMode.DOWN));
                    }
                }
            }
        }
        return result;
    }

    public BigDecimal okDepositAmount() {
        BigDecimal sum = BigDecimal.ZERO;
        if (mOkStaking != null && !TextUtils.isEmpty(mOkStaking.tokens)) {
            sum = new BigDecimal(mOkStaking.tokens);
        }
        return sum;
    }

    public BigDecimal okWithdrawAmount() {
        BigDecimal sum = BigDecimal.ZERO;
        if (mOkUnbonding != null && !TextUtils.isEmpty(mOkUnbonding.quantity)) {
            sum = new BigDecimal(mOkUnbonding.quantity);
        }
        return sum;
    }

    public BigDecimal getAllMainAssetOld(String denom) {
        return availableAmount(denom).add(lockedAmount(denom)).add(delegatedSumAmount()).add(unbondingSumAmount()).add(rewardAmount(denom));
    }

    //gRPC
    public Types.DefaultNodeInfo                                mGRpcNodeInfo;
    public Any                                                  mGRpcAccount;
    public ArrayList<Staking.Validator>                         mGRpcTopValidators = new ArrayList<>();
    public ArrayList<Staking.Validator>                         mGRpcOtherValidators = new ArrayList<>();
    public ArrayList<Staking.Validator>                         mGRpcAllValidators = new ArrayList<>();
    public ArrayList<Staking.Validator>                         mGRpcMyValidators = new ArrayList<>();

    public ArrayList<Coin>                                      mGrpcBalance = new ArrayList<>();
    public ArrayList<Coin>                                      mGrpcVesting = new ArrayList<>();
    public ArrayList<Staking.DelegationResponse>                mGrpcDelegations = new ArrayList<>();
    public ArrayList<Staking.UnbondingDelegation>               mGrpcUndelegations = new ArrayList<>();
    public ArrayList<Distribution.DelegationDelegatorReward>    mGrpcRewards = new ArrayList<>();

    public Staking.Pool                                         mGrpcStakingPool;
    public cosmos.mint.v1beta1.Mint.Params                      mGrpcParamMint;
    public BigDecimal                                           mGrpcInflation;
    public BigDecimal                                           mGrpcProvision;

    public irishub.mint.Mint.Params                             mGrpcIrisParamMint;
    public ArrayList<TokenOuterClass.Token>                     mGrpcIrisTokens = new ArrayList<>();

    public String getChainId() {
        if (mNodeInfo != null) {
            return mNodeInfo.network;
        }
        return "";
    }

    //gRPC funcs
    public String getChainIdGrpc() {
        if (mGRpcNodeInfo != null) {
            return mGRpcNodeInfo.getNetwork();
        }
        return "";
    }


    public BigDecimal getAvailable(String denom) {
        BigDecimal result = BigDecimal.ZERO;
        for (Coin coin: mGrpcBalance) {
            if (coin.denom.equalsIgnoreCase(denom)) {
                result = new BigDecimal(coin.amount);
            }
        }
        return result;
    }

    public BigDecimal getVesting(String denom) {
        BigDecimal result = BigDecimal.ZERO;
        for (Coin coin: mGrpcVesting) {
            if (coin.denom.equalsIgnoreCase(denom)) {
                result = new BigDecimal(coin.amount);
            }
        }
        return result;
    }

    public ArrayList<Vesting.Period> onParseRemainVestingsByDenom(String denom) {
        ArrayList<Vesting.Period> result = new ArrayList<>();
        if (mGRpcAccount.getTypeUrl().contains(Vesting.PeriodicVestingAccount.getDescriptor().getFullName())) {
            try {
                Vesting.PeriodicVestingAccount vestingAccount = Vesting.PeriodicVestingAccount.parseFrom(mGRpcAccount.getValue());
                return WDp.onParsePeriodicRemainVestingsByDenom(vestingAccount, denom);
            } catch (InvalidProtocolBufferException e) { }

        } else if (mGRpcAccount.getTypeUrl().contains(Vesting.ContinuousVestingAccount.getDescriptor().getFullName())) {
            try {
                Vesting.ContinuousVestingAccount vestingAccount = Vesting.ContinuousVestingAccount.parseFrom(mGRpcAccount.getValue());
                long cTime = Calendar.getInstance().getTime().getTime();
                long vestingEnd = vestingAccount.getBaseVestingAccount().getEndTime() * 1000;
                if (cTime < vestingEnd) {
                    for (CoinOuterClass.Coin vesting : vestingAccount.getBaseVestingAccount().getOriginalVestingList()) {
                        if (vesting.getDenom().equals(denom)) {
                            result.add(Vesting.Period.newBuilder().setLength(vestingEnd).addAllAmount(vestingAccount.getBaseVestingAccount().getOriginalVestingList()).build());
                        }
                    }
                }
            } catch (InvalidProtocolBufferException e) { }
        }
        return result;
    }

    public BigDecimal onParseRemainVestingsAmountSumByDenom(String denom) {
        BigDecimal result = BigDecimal.ZERO;
        for (Vesting.Period vps: onParseRemainVestingsByDenom(denom)) {
            for (CoinOuterClass.Coin coin : vps.getAmountList()) {
                if (coin.getDenom().equals(denom)) {
                    result = result.add(new BigDecimal(coin.getAmount()));
                }
            }
        }
        return result;
    }

    public BigDecimal getDelegatable(String denom) {
        return getAvailable(denom).add(getVesting(denom));
    }

    public BigDecimal getDelegationSum() {
        BigDecimal sum = BigDecimal.ZERO;
        for (Staking.DelegationResponse delegation: mGrpcDelegations) {
            sum = sum.add(new BigDecimal(delegation.getBalance().getAmount()));
        }
        return sum;
    }

    public BigDecimal getDelegation(String valOpAddress) {
        BigDecimal result = BigDecimal.ZERO;
        for (Staking.DelegationResponse delegation: mGrpcDelegations) {
            if (delegation.getDelegation().getValidatorAddress().equals(valOpAddress)) {
                result = new BigDecimal(delegation.getBalance().getAmount());
            }
        }
        return result;
    }

    public Staking.DelegationResponse getDelegationInfo(String valOpAddress) {
        for (Staking.DelegationResponse delegation: mGrpcDelegations) {
            if (delegation.getDelegation().getValidatorAddress().equals(valOpAddress)) {
                return delegation;
            }
        }
        return null;
    }

    public BigDecimal getUndelegationSum() {
        BigDecimal sum = BigDecimal.ZERO;
        for (Staking.UnbondingDelegation undelegation: mGrpcUndelegations) {
            sum = sum.add(getAllUnbondingBalance(undelegation));
        }
        return sum;
    }

    public BigDecimal getUndelegation(String valOpAddress) {
        BigDecimal result = BigDecimal.ZERO;
        for (Staking.UnbondingDelegation undelegation: mGrpcUndelegations) {
            if (undelegation.getValidatorAddress().equals(valOpAddress)) {
                result = getAllUnbondingBalance(undelegation);
            }
        }
        return result;
    }

    public Staking.UnbondingDelegation getUndelegationInfo(String valOpAddress) {
        for (Staking.UnbondingDelegation undelegation: mGrpcUndelegations) {
            if (undelegation.getValidatorAddress().equals(valOpAddress)) {
                return undelegation;
            }
        }
        return null;
    }

    public BigDecimal getAllUnbondingBalance(Staking.UnbondingDelegation undelegation) {
        BigDecimal result = BigDecimal.ZERO;
        if (undelegation != null && undelegation.getEntriesList().size() > 0) {
            for (Staking.UnbondingDelegationEntry entry: undelegation.getEntriesList()) {
                result = result.add(new BigDecimal(entry.getBalance()));
            }
        }
        return result;
    }

    public BigDecimal getRewardSum(String denom) {
        BigDecimal sum = BigDecimal.ZERO;
        for (Distribution.DelegationDelegatorReward reward: mGrpcRewards) {
            sum = sum.add(WUtil.decCoinAmount(reward.getRewardList(), denom));
        }
        return sum;
    }

    public BigDecimal getReward(String denom, String valOpAddress) {
        BigDecimal result = BigDecimal.ZERO;
        for (Distribution.DelegationDelegatorReward reward: mGrpcRewards) {
            if (reward.getValidatorAddress().equals(valOpAddress)) {
                result = WUtil.decCoinAmount(reward.getRewardList(), denom);
            }
        }
        return result;
    }

    public Distribution.DelegationDelegatorReward getRewardInfo(String valOpAddress) {
        for (Distribution.DelegationDelegatorReward reward: mGrpcRewards) {
            if (reward.getValidatorAddress().equals(valOpAddress)) {
                return reward;
            }
        }
        return null;
    }

    public CoinOuterClass.DecCoin decCoin(List<CoinOuterClass.DecCoin> coins, String denom) {
        for (CoinOuterClass.DecCoin coin: coins) {
            if (coin.getDenom().equals(denom)) {
                return coin;
            }
        }
        return null;
    }

    public BigDecimal getAllMainAsset(String denom) {
        return getAvailable(denom).add(getVesting(denom)).add(getDelegationSum()).add(getUndelegationSum()).add(getRewardSum(denom));
    }


    public Staking.Validator getValidatorInfo(String valOpAddress) {
        for (Staking.Validator val: mGRpcAllValidators) {
            if (val.getOperatorAddress().equals(valOpAddress)) {
                return val;
            }
        }
        return null;
    }


    public TokenOuterClass.Token getIrisToken(String denom) {
        for (TokenOuterClass.Token token: mGrpcIrisTokens) {
            if (token.getMinUnit().equals(denom)) {
                return token;
            }
        }
        return null;
    }




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
        if (chain.equals(COSMOS_MAIN) || chain.equals(COSMOS_TEST)) {
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

        } else if (chain.equals(IRIS_MAIN) || chain.equals(IRIS_TEST)) {
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

        } else if (chain.equals(BaseChain.IOV_MAIN) || chain.equals(IOV_TEST)) {
            if (getCurrency() == 0) {
                getSharedPreferences().edit().putString(BaseConstant.PRE_IOV_TIC, ""+tic.market_data.current_price.usd).commit();
                getSharedPreferences().edit().putString(BaseConstant.PRE_IOV_UP_DOWN_24, ""+tic.market_data.price_change_24h.usd).commit();
            } else if (getCurrency() == 1) {
                getSharedPreferences().edit().putString(BaseConstant.PRE_IOV_TIC, ""+tic.market_data.current_price.eur).commit();
                getSharedPreferences().edit().putString(BaseConstant.PRE_IOV_UP_DOWN_24, ""+tic.market_data.price_change_24h.eur).commit();
            } else if (getCurrency() == 2) {
                getSharedPreferences().edit().putString(BaseConstant.PRE_IOV_TIC, ""+tic.market_data.current_price.krw).commit();
                getSharedPreferences().edit().putString(BaseConstant.PRE_IOV_UP_DOWN_24, ""+tic.market_data.price_change_24h.krw).commit();
            } else if (getCurrency() == 3) {
                getSharedPreferences().edit().putString(BaseConstant.PRE_IOV_TIC, ""+tic.market_data.current_price.jpy).commit();
                getSharedPreferences().edit().putString(BaseConstant.PRE_IOV_UP_DOWN_24, ""+tic.market_data.price_change_24h.jpy).commit();
            } else if (getCurrency() == 4) {
                getSharedPreferences().edit().putString(BaseConstant.PRE_IOV_TIC, ""+tic.market_data.current_price.cny).commit();
                getSharedPreferences().edit().putString(BaseConstant.PRE_IOV_UP_DOWN_24, ""+tic.market_data.price_change_24h.cny).commit();
            } else if (getCurrency() == 5) {
                getSharedPreferences().edit().putString(BaseConstant.PRE_IOV_TIC, ""+tic.market_data.current_price.btc).commit();
                getSharedPreferences().edit().putString(BaseConstant.PRE_IOV_UP_DOWN_24, ""+tic.market_data.price_change_24h.btc).commit();
            }

        } else if (chain.equals(BaseChain.CERTIK_MAIN) || chain.equals(CERTIK_TEST)) {
            if (getCurrency() == 0) {
                getSharedPreferences().edit().putString(BaseConstant.PRE_CERTIK_TIC, ""+tic.market_data.current_price.usd).commit();
                getSharedPreferences().edit().putString(BaseConstant.PRE_CERTIK_UP_DOWN_24, ""+tic.market_data.price_change_24h.usd).commit();
            } else if (getCurrency() == 1) {
                getSharedPreferences().edit().putString(BaseConstant.PRE_CERTIK_TIC, ""+tic.market_data.current_price.eur).commit();
                getSharedPreferences().edit().putString(BaseConstant.PRE_CERTIK_UP_DOWN_24, ""+tic.market_data.price_change_24h.eur).commit();
            } else if (getCurrency() == 2) {
                getSharedPreferences().edit().putString(BaseConstant.PRE_CERTIK_TIC, ""+tic.market_data.current_price.krw).commit();
                getSharedPreferences().edit().putString(BaseConstant.PRE_CERTIK_UP_DOWN_24, ""+tic.market_data.price_change_24h.krw).commit();
            } else if (getCurrency() == 3) {
                getSharedPreferences().edit().putString(BaseConstant.PRE_CERTIK_TIC, ""+tic.market_data.current_price.jpy).commit();
                getSharedPreferences().edit().putString(BaseConstant.PRE_CERTIK_UP_DOWN_24, ""+tic.market_data.price_change_24h.jpy).commit();
            } else if (getCurrency() == 4) {
                getSharedPreferences().edit().putString(BaseConstant.PRE_CERTIK_TIC, ""+tic.market_data.current_price.cny).commit();
                getSharedPreferences().edit().putString(BaseConstant.PRE_CERTIK_UP_DOWN_24, ""+tic.market_data.price_change_24h.cny).commit();
            } else if (getCurrency() == 5) {
                getSharedPreferences().edit().putString(BaseConstant.PRE_CERTIK_TIC, ""+tic.market_data.current_price.btc).commit();
                getSharedPreferences().edit().putString(BaseConstant.PRE_CERTIK_UP_DOWN_24, ""+tic.market_data.price_change_24h.btc).commit();
            }

        } else if (chain.equals(BaseChain.AKASH_MAIN)) {
            if (getCurrency() == 0) {
                getSharedPreferences().edit().putString(BaseConstant.PRE_AKASH_TIC, ""+tic.market_data.current_price.usd).commit();
                getSharedPreferences().edit().putString(BaseConstant.PRE_AKASH_UP_DOWN_24, ""+tic.market_data.price_change_24h.usd).commit();
            } else if (getCurrency() == 1) {
                getSharedPreferences().edit().putString(BaseConstant.PRE_AKASH_TIC, ""+tic.market_data.current_price.eur).commit();
                getSharedPreferences().edit().putString(BaseConstant.PRE_AKASH_UP_DOWN_24, ""+tic.market_data.price_change_24h.eur).commit();
            } else if (getCurrency() == 2) {
                getSharedPreferences().edit().putString(BaseConstant.PRE_AKASH_TIC, ""+tic.market_data.current_price.krw).commit();
                getSharedPreferences().edit().putString(BaseConstant.PRE_AKASH_UP_DOWN_24, ""+tic.market_data.price_change_24h.krw).commit();
            } else if (getCurrency() == 3) {
                getSharedPreferences().edit().putString(BaseConstant.PRE_AKASH_TIC, ""+tic.market_data.current_price.jpy).commit();
                getSharedPreferences().edit().putString(BaseConstant.PRE_AKASH_UP_DOWN_24, ""+tic.market_data.price_change_24h.jpy).commit();
            } else if (getCurrency() == 4) {
                getSharedPreferences().edit().putString(BaseConstant.PRE_AKASH_TIC, ""+tic.market_data.current_price.cny).commit();
                getSharedPreferences().edit().putString(BaseConstant.PRE_AKASH_UP_DOWN_24, ""+tic.market_data.price_change_24h.cny).commit();
            } else if (getCurrency() == 5) {
                getSharedPreferences().edit().putString(BaseConstant.PRE_AKASH_TIC, ""+tic.market_data.current_price.btc).commit();
                getSharedPreferences().edit().putString(BaseConstant.PRE_AKASH_UP_DOWN_24, ""+tic.market_data.price_change_24h.btc).commit();
            }

        } else if (chain.equals(SECRET_MAIN)) {
            if (getCurrency() == 0) {
                getSharedPreferences().edit().putString(PRE_SECRET_TIC, ""+tic.market_data.current_price.usd).commit();
                getSharedPreferences().edit().putString(PRE_SECRET_UP_DOWN_24, ""+tic.market_data.price_change_24h.usd).commit();
            } else if (getCurrency() == 1) {
                getSharedPreferences().edit().putString(PRE_SECRET_TIC, ""+tic.market_data.current_price.eur).commit();
                getSharedPreferences().edit().putString(PRE_SECRET_UP_DOWN_24, ""+tic.market_data.price_change_24h.eur).commit();
            } else if (getCurrency() == 2) {
                getSharedPreferences().edit().putString(PRE_SECRET_TIC, ""+tic.market_data.current_price.krw).commit();
                getSharedPreferences().edit().putString(PRE_SECRET_UP_DOWN_24, ""+tic.market_data.price_change_24h.krw).commit();
            } else if (getCurrency() == 3) {
                getSharedPreferences().edit().putString(PRE_SECRET_TIC, ""+tic.market_data.current_price.jpy).commit();
                getSharedPreferences().edit().putString(PRE_SECRET_UP_DOWN_24, ""+tic.market_data.price_change_24h.jpy).commit();
            } else if (getCurrency() == 4) {
                getSharedPreferences().edit().putString(PRE_SECRET_TIC, ""+tic.market_data.current_price.cny).commit();
                getSharedPreferences().edit().putString(PRE_SECRET_UP_DOWN_24, ""+tic.market_data.price_change_24h.cny).commit();
            } else if (getCurrency() == 5) {
                getSharedPreferences().edit().putString(PRE_SECRET_TIC, ""+tic.market_data.current_price.btc).commit();
                getSharedPreferences().edit().putString(PRE_SECRET_UP_DOWN_24, ""+tic.market_data.price_change_24h.btc).commit();
            }

        } else if (chain.equals(OKEX_MAIN) || chain.equals(OK_TEST)) {
            getSharedPreferences().edit().putString(BaseConstant.PRE_OKEX_DOLLOR_TIC, ""+tic.market_data.current_price.usd).commit();
            if (getCurrency() == 0) {
                getSharedPreferences().edit().putString(PRE_OKEX_TIC, ""+tic.market_data.current_price.usd).commit();
                getSharedPreferences().edit().putString(PRE_OKEX_UP_DOWN_24, ""+tic.market_data.price_change_24h.usd).commit();
            } else if (getCurrency() == 1) {
                getSharedPreferences().edit().putString(PRE_OKEX_TIC, ""+tic.market_data.current_price.eur).commit();
                getSharedPreferences().edit().putString(PRE_OKEX_UP_DOWN_24, ""+tic.market_data.price_change_24h.eur).commit();
            } else if (getCurrency() == 2) {
                getSharedPreferences().edit().putString(PRE_OKEX_TIC, ""+tic.market_data.current_price.krw).commit();
                getSharedPreferences().edit().putString(PRE_OKEX_UP_DOWN_24, ""+tic.market_data.price_change_24h.krw).commit();
            } else if (getCurrency() == 3) {
                getSharedPreferences().edit().putString(PRE_OKEX_TIC, ""+tic.market_data.current_price.jpy).commit();
                getSharedPreferences().edit().putString(PRE_OKEX_UP_DOWN_24, ""+tic.market_data.price_change_24h.jpy).commit();
            } else if (getCurrency() == 4) {
                getSharedPreferences().edit().putString(PRE_OKEX_TIC, ""+tic.market_data.current_price.cny).commit();
                getSharedPreferences().edit().putString(PRE_OKEX_UP_DOWN_24, ""+tic.market_data.price_change_24h.cny).commit();
            } else if (getCurrency() == 5) {
                getSharedPreferences().edit().putString(PRE_OKEX_TIC, ""+tic.market_data.current_price.btc).commit();
                getSharedPreferences().edit().putString(PRE_OKEX_UP_DOWN_24, ""+tic.market_data.price_change_24h.btc).commit();
            }

        } else if (chain.equals(SENTINEL_MAIN)) {
            if (getCurrency() == 0) {
                getSharedPreferences().edit().putString(PRE_SENTINEL_TIC, ""+tic.market_data.current_price.usd).commit();
                getSharedPreferences().edit().putString(PRE_SENTINEL_UP_DOWN_24, ""+tic.market_data.price_change_24h.usd).commit();
            } else if (getCurrency() == 1) {
                getSharedPreferences().edit().putString(PRE_SENTINEL_TIC, ""+tic.market_data.current_price.eur).commit();
                getSharedPreferences().edit().putString(PRE_SENTINEL_UP_DOWN_24, ""+tic.market_data.price_change_24h.eur).commit();
            } else if (getCurrency() == 2) {
                getSharedPreferences().edit().putString(PRE_SENTINEL_TIC, ""+tic.market_data.current_price.krw).commit();
                getSharedPreferences().edit().putString(PRE_SENTINEL_UP_DOWN_24, ""+tic.market_data.price_change_24h.krw).commit();
            } else if (getCurrency() == 3) {
                getSharedPreferences().edit().putString(PRE_SENTINEL_TIC, ""+tic.market_data.current_price.jpy).commit();
                getSharedPreferences().edit().putString(PRE_SENTINEL_UP_DOWN_24, ""+tic.market_data.price_change_24h.jpy).commit();
            } else if (getCurrency() == 4) {
                getSharedPreferences().edit().putString(PRE_SENTINEL_TIC, ""+tic.market_data.current_price.cny).commit();
                getSharedPreferences().edit().putString(PRE_SENTINEL_UP_DOWN_24, ""+tic.market_data.price_change_24h.cny).commit();
            } else if (getCurrency() == 5) {
                getSharedPreferences().edit().putString(PRE_SENTINEL_TIC, ""+tic.market_data.current_price.btc).commit();
                getSharedPreferences().edit().putString(PRE_SENTINEL_UP_DOWN_24, ""+tic.market_data.price_change_24h.btc).commit();
            }

        } else if (chain.equals(PERSIS_MAIN)) {
            if (getCurrency() == 0) {
                getSharedPreferences().edit().putString(PRE_PERSISTENCE_TIC, ""+tic.market_data.current_price.usd).commit();
                getSharedPreferences().edit().putString(PRE_PERSISTENCE_UP_DOWN_24, ""+tic.market_data.price_change_24h.usd).commit();
            } else if (getCurrency() == 1) {
                getSharedPreferences().edit().putString(PRE_PERSISTENCE_TIC, ""+tic.market_data.current_price.eur).commit();
                getSharedPreferences().edit().putString(PRE_PERSISTENCE_UP_DOWN_24, ""+tic.market_data.price_change_24h.eur).commit();
            } else if (getCurrency() == 2) {
                getSharedPreferences().edit().putString(PRE_PERSISTENCE_TIC, ""+tic.market_data.current_price.krw).commit();
                getSharedPreferences().edit().putString(PRE_PERSISTENCE_UP_DOWN_24, ""+tic.market_data.price_change_24h.krw).commit();
            } else if (getCurrency() == 3) {
                getSharedPreferences().edit().putString(PRE_PERSISTENCE_TIC, ""+tic.market_data.current_price.jpy).commit();
                getSharedPreferences().edit().putString(PRE_PERSISTENCE_UP_DOWN_24, ""+tic.market_data.price_change_24h.jpy).commit();
            } else if (getCurrency() == 4) {
                getSharedPreferences().edit().putString(PRE_PERSISTENCE_TIC, ""+tic.market_data.current_price.cny).commit();
                getSharedPreferences().edit().putString(PRE_PERSISTENCE_UP_DOWN_24, ""+tic.market_data.price_change_24h.cny).commit();
            } else if (getCurrency() == 5) {
                getSharedPreferences().edit().putString(PRE_PERSISTENCE_TIC, ""+tic.market_data.current_price.btc).commit();
                getSharedPreferences().edit().putString(PRE_PERSISTENCE_UP_DOWN_24, ""+tic.market_data.price_change_24h.btc).commit();
            }

        } else if (chain.equals(FETCHAI_MAIN)) {
            if (getCurrency() == 0) {
                getSharedPreferences().edit().putString(PRE_FETCH_TIC, ""+tic.market_data.current_price.usd).commit();
                getSharedPreferences().edit().putString(PRE_FETCH_UP_DOWN_24, ""+tic.market_data.price_change_24h.usd).commit();
            } else if (getCurrency() == 1) {
                getSharedPreferences().edit().putString(PRE_FETCH_TIC, ""+tic.market_data.current_price.eur).commit();
                getSharedPreferences().edit().putString(PRE_FETCH_UP_DOWN_24, ""+tic.market_data.price_change_24h.eur).commit();
            } else if (getCurrency() == 2) {
                getSharedPreferences().edit().putString(PRE_FETCH_TIC, ""+tic.market_data.current_price.krw).commit();
                getSharedPreferences().edit().putString(PRE_FETCH_UP_DOWN_24, ""+tic.market_data.price_change_24h.krw).commit();
            } else if (getCurrency() == 3) {
                getSharedPreferences().edit().putString(PRE_FETCH_TIC, ""+tic.market_data.current_price.jpy).commit();
                getSharedPreferences().edit().putString(PRE_FETCH_UP_DOWN_24, ""+tic.market_data.price_change_24h.jpy).commit();
            } else if (getCurrency() == 4) {
                getSharedPreferences().edit().putString(PRE_FETCH_TIC, ""+tic.market_data.current_price.cny).commit();
                getSharedPreferences().edit().putString(PRE_FETCH_UP_DOWN_24, ""+tic.market_data.price_change_24h.cny).commit();
            } else if (getCurrency() == 5) {
                getSharedPreferences().edit().putString(PRE_FETCH_TIC, ""+tic.market_data.current_price.btc).commit();
                getSharedPreferences().edit().putString(PRE_FETCH_UP_DOWN_24, ""+tic.market_data.price_change_24h.btc).commit();
            }

        } else if (chain.equals(CRYPTO_MAIN)) {
            if (getCurrency() == 0) {
                getSharedPreferences().edit().putString(PRE_CRYPTO_TIC, ""+tic.market_data.current_price.usd).commit();
                getSharedPreferences().edit().putString(PRE_CRYPTO_UP_DOWN_24, ""+tic.market_data.price_change_24h.usd).commit();
            } else if (getCurrency() == 1) {
                getSharedPreferences().edit().putString(PRE_CRYPTO_TIC, ""+tic.market_data.current_price.eur).commit();
                getSharedPreferences().edit().putString(PRE_CRYPTO_UP_DOWN_24, ""+tic.market_data.price_change_24h.eur).commit();
            } else if (getCurrency() == 2) {
                getSharedPreferences().edit().putString(PRE_CRYPTO_TIC, ""+tic.market_data.current_price.krw).commit();
                getSharedPreferences().edit().putString(PRE_CRYPTO_UP_DOWN_24, ""+tic.market_data.price_change_24h.krw).commit();
            } else if (getCurrency() == 3) {
                getSharedPreferences().edit().putString(PRE_CRYPTO_TIC, ""+tic.market_data.current_price.jpy).commit();
                getSharedPreferences().edit().putString(PRE_CRYPTO_UP_DOWN_24, ""+tic.market_data.price_change_24h.jpy).commit();
            } else if (getCurrency() == 4) {
                getSharedPreferences().edit().putString(PRE_CRYPTO_TIC, ""+tic.market_data.current_price.cny).commit();
                getSharedPreferences().edit().putString(PRE_CRYPTO_UP_DOWN_24, ""+tic.market_data.price_change_24h.cny).commit();
            } else if (getCurrency() == 5) {
                getSharedPreferences().edit().putString(PRE_CRYPTO_TIC, ""+tic.market_data.current_price.btc).commit();
                getSharedPreferences().edit().putString(PRE_CRYPTO_UP_DOWN_24, ""+tic.market_data.price_change_24h.btc).commit();
            }

        } else if (chain.equals(SIF_MAIN)) {
            if (getCurrency() == 0) {
                getSharedPreferences().edit().putString(PRE_SIF_TIC, ""+tic.market_data.current_price.usd).commit();
                getSharedPreferences().edit().putString(PRE_SIF_UP_DOWN_24, ""+tic.market_data.price_change_24h.usd).commit();
            } else if (getCurrency() == 1) {
                getSharedPreferences().edit().putString(PRE_SIF_TIC, ""+tic.market_data.current_price.eur).commit();
                getSharedPreferences().edit().putString(PRE_SIF_UP_DOWN_24, ""+tic.market_data.price_change_24h.eur).commit();
            } else if (getCurrency() == 2) {
                getSharedPreferences().edit().putString(PRE_SIF_TIC, ""+tic.market_data.current_price.krw).commit();
                getSharedPreferences().edit().putString(PRE_SIF_UP_DOWN_24, ""+tic.market_data.price_change_24h.krw).commit();
            } else if (getCurrency() == 3) {
                getSharedPreferences().edit().putString(PRE_SIF_TIC, ""+tic.market_data.current_price.jpy).commit();
                getSharedPreferences().edit().putString(PRE_SIF_UP_DOWN_24, ""+tic.market_data.price_change_24h.jpy).commit();
            } else if (getCurrency() == 4) {
                getSharedPreferences().edit().putString(PRE_SIF_TIC, ""+tic.market_data.current_price.cny).commit();
                getSharedPreferences().edit().putString(PRE_SIF_UP_DOWN_24, ""+tic.market_data.price_change_24h.cny).commit();
            } else if (getCurrency() == 5) {
                getSharedPreferences().edit().putString(PRE_SIF_TIC, ""+tic.market_data.current_price.btc).commit();
                getSharedPreferences().edit().putString(PRE_SIF_UP_DOWN_24, ""+tic.market_data.price_change_24h.btc).commit();
            }

        }


    }

    public BigDecimal getLastPriceTic(BaseChain chain) {
        if (chain.equals(COSMOS_MAIN) || chain.equals(COSMOS_TEST)) {
            return BigDecimal.valueOf(getLastAtomTic());

        } else if (chain.equals(IRIS_MAIN) || chain.equals(IRIS_TEST)) {
            return BigDecimal.valueOf(getLastIrisTic());

        } else if (chain.equals(BNB_MAIN) || chain.equals(BNB_TEST)) {
            return BigDecimal.valueOf(getLastBnbTic());

        } else if (chain.equals(KAVA_MAIN) || chain.equals(KAVA_TEST)) {
            return BigDecimal.valueOf(getLastKavaTic());

        } else if (chain.equals(IOV_MAIN) || chain.equals(IOV_TEST)) {
            return BigDecimal.valueOf(getLastIovTic());

        } else if (chain.equals(BAND_MAIN)) {
            return BigDecimal.valueOf(getLastBandTic());

        } else if (chain.equals(CERTIK_MAIN) || chain.equals(CERTIK_TEST)) {
            return BigDecimal.valueOf(getLastCertikTic());

        } else if (chain.equals(AKASH_MAIN)) {
            return BigDecimal.valueOf(getLastAkashTic());

        } else if (chain.equals(SECRET_MAIN)) {
            return BigDecimal.valueOf(getLastSecretTic());

        } else if (chain.equals(OKEX_MAIN) || chain.equals(OK_TEST)) {
            return BigDecimal.valueOf(getLastOKexTic());

        } else if (chain.equals(SENTINEL_MAIN)) {
            return BigDecimal.valueOf(getLastSentinelTic());

        } else if (chain.equals(PERSIS_MAIN)) {
            return BigDecimal.valueOf(getLastPersistencelTic());

        } else if (chain.equals(FETCHAI_MAIN)) {
            return BigDecimal.valueOf(getLastFetchTic());

        } else if (chain.equals(CRYPTO_MAIN)) {
            return BigDecimal.valueOf(getLastCryptoTic());

        } else if (chain.equals(SIF_MAIN)) {
            return BigDecimal.valueOf(getLastSifTic());

        }
        return BigDecimal.ZERO;
    }

    public BigDecimal getLastPriceUpDown(BaseChain chain) {
        if (chain.equals(COSMOS_MAIN) || chain.equals(COSMOS_TEST)) {
            return BigDecimal.valueOf(getLastAtomUpDown());

        } else if (chain.equals(IRIS_MAIN) || chain.equals(IRIS_TEST)) {
            return BigDecimal.valueOf(getLastIrisUpDown());

        } else if (chain.equals(BNB_MAIN) || chain.equals(BNB_TEST)) {
            return BigDecimal.valueOf(getLastBnbUpDown());

        } else if (chain.equals(KAVA_MAIN) || chain.equals(KAVA_TEST)) {
            return BigDecimal.valueOf(getLastKavaUpDown());

        } else if (chain.equals(IOV_MAIN) || chain.equals(IOV_TEST)) {
            return BigDecimal.valueOf(getLastIovUpDown());

        } else if (chain.equals(BAND_MAIN)) {
            return BigDecimal.valueOf(getLastBandUpDown());

        } else if (chain.equals(CERTIK_MAIN) || chain.equals(CERTIK_TEST)) {
            return BigDecimal.valueOf(getLastCertikUpDown());

        } else if (chain.equals(AKASH_MAIN)) {
            return BigDecimal.valueOf(getLastAkashUpDown());

        } else if (chain.equals(SECRET_MAIN)) {
            return BigDecimal.valueOf(getLastSecretUpDown());

        } else if (chain.equals(OKEX_MAIN) || chain.equals(OK_TEST)) {
            return BigDecimal.valueOf(getLastOKexUpDown());

        } else if (chain.equals(SENTINEL_MAIN)) {
            return BigDecimal.valueOf(getLastSentinelUpDown());

        } else if (chain.equals(PERSIS_MAIN)) {
            return BigDecimal.valueOf(getLastPersistencelUpDown());

        } else if (chain.equals(FETCHAI_MAIN)) {
            return BigDecimal.valueOf(getLastFetchUpDown());

        } else if (chain.equals(CRYPTO_MAIN)) {
            return BigDecimal.valueOf(getLastCryptoUpDown());

        } else if (chain.equals(SIF_MAIN)) {
            return BigDecimal.valueOf(getLastSifUpDown());
        }
        return BigDecimal.ZERO;

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

    public void setLastCertikTic(Double price) {
        getSharedPreferences().edit().putString(BaseConstant.PRE_CERTIK_TIC, ""+price).commit();
    }

    public double getLastCertikTic() {
        String priceS = getSharedPreferences().getString(BaseConstant.PRE_CERTIK_TIC, "0");
        try {
            return Double.parseDouble(priceS);
        }catch (Exception e) {
            return Double.parseDouble("0");
        }
    }

    public void setLastCertikUpDown(Double price) {
        getSharedPreferences().edit().putString(BaseConstant.PRE_CERTIK_UP_DOWN_24, ""+price).commit();
    }

    public double getLastCertikUpDown() {
        String priceS = getSharedPreferences().getString(BaseConstant.PRE_CERTIK_UP_DOWN_24, "0");
        try {
            return Double.parseDouble(priceS);
        }catch (Exception e) {
            return Double.parseDouble("0");
        }
    }


    public void setLastAkashTic(Double price) {
        getSharedPreferences().edit().putString(BaseConstant.PRE_AKASH_TIC, ""+price).commit();
    }

    public double getLastAkashTic() {
        String priceS = getSharedPreferences().getString(BaseConstant.PRE_AKASH_TIC, "0");
        try {
            return Double.parseDouble(priceS);
        }catch (Exception e) {
            return Double.parseDouble("0");
        }
    }

    public void setLastAkashUpDown(Double price) {
        getSharedPreferences().edit().putString(BaseConstant.PRE_AKASH_UP_DOWN_24, ""+price).commit();
    }

    public double getLastAkashUpDown() {
        String priceS = getSharedPreferences().getString(BaseConstant.PRE_AKASH_UP_DOWN_24, "0");
        try {
            return Double.parseDouble(priceS);
        }catch (Exception e) {
            return Double.parseDouble("0");
        }
    }

    public void setLastSecretTic(Double price) {
        getSharedPreferences().edit().putString(PRE_SECRET_TIC, ""+price).commit();
    }

    public double getLastSecretTic() {
        String priceS = getSharedPreferences().getString(PRE_SECRET_TIC, "0");
        try {
            return Double.parseDouble(priceS);
        }catch (Exception e) {
            return Double.parseDouble("0");
        }
    }

    public void setLastSecretUpDown(Double price) {
        getSharedPreferences().edit().putString(PRE_SECRET_UP_DOWN_24, ""+price).commit();
    }

    public double getLastSecretUpDown() {
        String priceS = getSharedPreferences().getString(PRE_SECRET_UP_DOWN_24, "0");
        try {
            return Double.parseDouble(priceS);
        }catch (Exception e) {
            return Double.parseDouble("0");
        }
    }

    public void setLastOKexTic(Double price) {
        getSharedPreferences().edit().putString(PRE_OKEX_TIC, ""+price).commit();
    }

    public double getLastOKexTic() {
        String priceS = getSharedPreferences().getString(PRE_OKEX_TIC, "0");
        try {
            return Double.parseDouble(priceS);
        } catch (Exception e) {
            return Double.parseDouble("0");
        }
    }

    public BigDecimal getLastOKexDollorTic() {
        String priceS = getSharedPreferences().getString(BaseConstant.PRE_OKEX_DOLLOR_TIC, "0");
        try {
            return new BigDecimal(priceS);
        } catch (Exception e) {
            return BigDecimal.ZERO;
        }
    }

    public void setLastOKexUpDown(Double price) {
        getSharedPreferences().edit().putString(PRE_OKEX_UP_DOWN_24, ""+price).commit();
    }

    public double getLastOKexUpDown() {
        String priceS = getSharedPreferences().getString(PRE_OKEX_UP_DOWN_24, "0");
        try {
            return Double.parseDouble(priceS);
        }catch (Exception e) {
            return Double.parseDouble("0");
        }
    }

    public void setLastSentinelTic(Double price) {
        getSharedPreferences().edit().putString(PRE_SENTINEL_TIC, ""+price).commit();
    }

    public double getLastSentinelTic() {
        String priceS = getSharedPreferences().getString(PRE_SENTINEL_TIC, "0");
        try {
            return Double.parseDouble(priceS);
        } catch (Exception e) {
            return Double.parseDouble("0");
        }
    }

    public void setLastSentinelUpDown(Double price) {
        getSharedPreferences().edit().putString(PRE_SENTINEL_UP_DOWN_24, ""+price).commit();
    }

    public double getLastSentinelUpDown() {
        String priceS = getSharedPreferences().getString(PRE_SENTINEL_UP_DOWN_24, "0");
        try {
            return Double.parseDouble(priceS);
        }catch (Exception e) {
            return Double.parseDouble("0");
        }
    }

    public void setLastPersistenceTic(Double price) {
        getSharedPreferences().edit().putString(PRE_PERSISTENCE_TIC, ""+price).commit();
    }

    public double getLastPersistencelTic() {
        String priceS = getSharedPreferences().getString(PRE_PERSISTENCE_TIC, "0");
        try {
            return Double.parseDouble(priceS);
        } catch (Exception e) {
            return Double.parseDouble("0");
        }
    }

    public void setLastPersistencelUpDown(Double price) {
        getSharedPreferences().edit().putString(PRE_PERSISTENCE_UP_DOWN_24, ""+price).commit();
    }

    public double getLastPersistencelUpDown() {
        String priceS = getSharedPreferences().getString(PRE_PERSISTENCE_UP_DOWN_24, "0");
        try {
            return Double.parseDouble(priceS);
        }catch (Exception e) {
            return Double.parseDouble("0");
        }
    }

    public void setLastFetchTic(Double price) {
        getSharedPreferences().edit().putString(PRE_FETCH_TIC, ""+price).commit();
    }

    public double getLastFetchTic() {
        String priceS = getSharedPreferences().getString(PRE_FETCH_TIC, "0");
        try {
            return Double.parseDouble(priceS);
        } catch (Exception e) {
            return Double.parseDouble("0");
        }
    }

    public void setLastFetchUpDown(Double price) {
        getSharedPreferences().edit().putString(PRE_FETCH_UP_DOWN_24, ""+price).commit();
    }

    public double getLastFetchUpDown() {
        String priceS = getSharedPreferences().getString(PRE_FETCH_UP_DOWN_24, "0");
        try {
            return Double.parseDouble(priceS);
        }catch (Exception e) {
            return Double.parseDouble("0");
        }
    }

    public void setLastCryptoTic(Double price) {
        getSharedPreferences().edit().putString(PRE_CRYPTO_TIC, ""+price).commit();
    }

    public double getLastCryptoTic() {
        String priceS = getSharedPreferences().getString(PRE_CRYPTO_TIC, "0");
        try {
            return Double.parseDouble(priceS);
        } catch (Exception e) {
            return Double.parseDouble("0");
        }
    }

    public void setLastCryptoUpDown(Double price) {
        getSharedPreferences().edit().putString(PRE_CRYPTO_UP_DOWN_24, ""+price).commit();
    }

    public double getLastCryptoUpDown() {
        String priceS = getSharedPreferences().getString(PRE_CRYPTO_UP_DOWN_24, "0");
        try {
            return Double.parseDouble(priceS);
        }catch (Exception e) {
            return Double.parseDouble("0");
        }
    }

    public void setLastSifTic(Double price) {
        getSharedPreferences().edit().putString(PRE_SIF_TIC, ""+price).commit();
    }

    public double getLastSifTic() {
        String priceS = getSharedPreferences().getString(PRE_SIF_TIC, "0");
        try {
            return Double.parseDouble(priceS);
        } catch (Exception e) {
            return Double.parseDouble("0");
        }
    }

    public void setLastSifUpDown(Double price) {
        getSharedPreferences().edit().putString(PRE_SIF_UP_DOWN_24, ""+price).commit();
    }

    public double getLastSifUpDown() {
        String priceS = getSharedPreferences().getString(PRE_SIF_UP_DOWN_24, "0");
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

    public void setEventTime() {
        Date dt = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.add(Calendar.DATE, 1);
        getSharedPreferences().edit().putLong(PRE_EVENT_HIDE, c.getTimeInMillis()).commit();
    }

    public boolean getEventTime() {
        Date dt = new Date();
        if (dt.getTime() > getSharedPreferences().getLong(PRE_EVENT_HIDE, 1)) {
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
        ArrayList<Account> result = new ArrayList<>();
        Cursor cursor 	= getBaseDB().query(BaseConstant.DB_TABLE_ACCOUNT, new String[]{"id", "uuid", "nickName", "isFavo", "address", "baseChain",
                "hasPrivateKey", "resource", "spec", "fromMnemonic", "path",
                "isValidator", "sequenceNumber", "accountNumber", "fetchTime", "msize", "importTime", "lastTotal", "sortOrder", "pushAlarm", "newBip"}, "address == ?", new String[]{address}, null, null, null);
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

        for (Account account:result) {
            if (chain.equals(BaseChain.getChain(account.baseChain))) {
                return account;
            }
        }
        return null;
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



    public void upgradeAaccountAddressforOk() {
        ArrayList<Account> allOKAccounts =  onSelectAccountsByChain(OKEX_MAIN);
        for (Account account : allOKAccounts) {
            if (account.address.startsWith("okexchain")) {
                account.address = WKey.getUpgradeOKAddress(account.address);
                updateAccountAddress(account);
            }
        }
        ArrayList<Account> allOKTestAccounts =  onSelectAccountsByChain(OK_TEST);
        for (Account account : allOKTestAccounts) {
            if (account.address.startsWith("okexchain")) {
                account.address = WKey.getUpgradeOKAddress(account.address);
                updateAccountAddress(account);
            }
        }
    }

    //for okchain display address
    public long updateAccountAddress(Account account) {
        ContentValues values = new ContentValues();
        values.put("address",      account.address);
        return getBaseDB().update(BaseConstant.DB_TABLE_ACCOUNT, values, "id = ?", new String[]{""+account.id} );
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



//    public ArrayList<BondingState> onSelectBondingStates(long accountId) {
//        ArrayList<BondingState> result = new ArrayList<>();
//        Cursor cursor 	= getBaseDB().query(BaseConstant.DB_TABLE_BONDING, new String[]{"accountId", "validatorAddress", "shares", "fetchTime"}, "accountId == ?", new String[]{""+accountId}, null, null, null);
//        if(cursor != null && cursor.moveToFirst()) {
//            do {
//                BondingState delegate = new BondingState(
//                        cursor.getLong(0),
//                        cursor.getString(1),
//                        new BigDecimal(cursor.getString(2)),
//                        cursor.getLong(3));
//                result.add(delegate);
//            } while (cursor.moveToNext());
//        }
//        cursor.close();
//        return result;
//    }
//
//    public BondingState onSelectBondingState(long accountId, String vAddr) {
//        BondingState result = null;
//        Cursor cursor 	= getBaseDB().query(BaseConstant.DB_TABLE_BONDING, new String[]{"accountId", "validatorAddress", "shares", "fetchTime"}, "accountId == ? AND validatorAddress = ?", new String[]{""+accountId, vAddr}, null, null, null);
//        if(cursor != null && cursor.moveToFirst()) {
//            result = new BondingState(
//                    cursor.getLong(0),
//                    cursor.getString(1),
//                    new BigDecimal(cursor.getString(2)),
//                    cursor.getLong(3));
//        }
//        cursor.close();
//        return result;
//    }
//
//
//    public long onInsertBondingStates(BondingState bonding) {
//        ContentValues values = new ContentValues();
//        values.put("accountId",         bonding.accountId);
//        values.put("validatorAddress",  bonding.validatorAddress);
//        values.put("shares",            bonding.shares.toPlainString());
//        values.put("fetchTime",         bonding.fetchTime);
//        return getBaseDB().insertOrThrow(BaseConstant.DB_TABLE_BONDING, null, values);
//
//    }
//
//    public void onUpdateBondingStates(long accountId, ArrayList<BondingState> bondings) {
//        onDeleteBondingStates(accountId);
//        for(BondingState bonding: bondings) {
//            onInsertBondingStates(bonding);
//        }
//    }
//
//    public void onUpdateBondingState(long accountId, BondingState bonding) {
//        onDeleteBondingState(accountId, bonding.validatorAddress);
//        onInsertBondingStates(bonding);
//    }
//
//    public boolean onHasBondingStates(BondingState bondingState) {
//        boolean existed = false;
//        Cursor cursor 	= getBaseDB().query(BaseConstant.DB_TABLE_BONDING, new String[]{"accountId", "validatorAddress", "shares", "fetchTime"}, "accountId == ? AND validatorAddress == ?", new String[]{""+bondingState.accountId, bondingState.validatorAddress}, null, null, null);
//        if(cursor != null && cursor.getCount() > 0) {
//            existed = true;
//        }
//        cursor.close();
//        return existed;
//    }
//
//    public boolean onDeleteBondingStates(long accountId) {
//        return getBaseDB().delete(BaseConstant.DB_TABLE_BONDING, "accountId = ?", new String[]{""+accountId}) > 0;
//    }
//
//    public boolean onDeleteBondingState(long accountId, String vAddr) {
//        return getBaseDB().delete(BaseConstant.DB_TABLE_BONDING, "accountId = ? AND validatorAddress = ?", new String[]{""+accountId, vAddr}) > 0;
//    }
//
//
//
//    public ArrayList<UnBondingState> onSelectUnbondingStates(long accountId) {
//        ArrayList<UnBondingState> result = new ArrayList<>();
//        Cursor cursor 	= getBaseDB().query(BaseConstant.DB_TABLE_UNBONDING, new String[]{"accountId", "validatorAddress", "creationHeight", "completionTime", "initialBalance", "balance", "fetchTime"}, "accountId == ?", new String[]{""+accountId}, null, null, null);
//        if(cursor != null && cursor.moveToFirst()) {
//            do {
//                UnBondingState temp = new UnBondingState(
//                        cursor.getLong(0),
//                        cursor.getString(1),
//                        cursor.getString(2),
//                        cursor.getLong(3),
//                        new BigDecimal(cursor.getString(4)),
//                        new BigDecimal(cursor.getString(5)),
//                        cursor.getLong(6));
//                result.add(temp);
//            } while (cursor.moveToNext());
//        }
//        cursor.close();
//        return result;
//    }
//
//    public UnBondingState onSelectUnbondingState(long accountId, String vAddr) {
//        UnBondingState result = null;
//        Cursor cursor 	= getBaseDB().query(BaseConstant.DB_TABLE_UNBONDING, new String[]{"accountId", "validatorAddress", "creationHeight", "completionTime", "initialBalance", "balance", "fetchTime"}, "accountId == ? AND validatorAddress = ?", new String[]{""+accountId, vAddr}, null, null, null);
//        if(cursor != null && cursor.moveToFirst()) {
//            result = new UnBondingState(
//                    cursor.getLong(0),
//                    cursor.getString(1),
//                    cursor.getString(2),
//                    cursor.getLong(3),
//                    new BigDecimal(cursor.getString(4)),
//                    new BigDecimal(cursor.getString(5)),
//                    cursor.getLong(6));
//        }
//        cursor.close();
//        return result;
//    }
//
//    public ArrayList<UnBondingState> onSelectUnbondingStates(long accountId, String vAddr) {
//        ArrayList<UnBondingState> result = new ArrayList<>();
//        Cursor cursor 	= getBaseDB().query(BaseConstant.DB_TABLE_UNBONDING, new String[]{"accountId", "validatorAddress", "creationHeight", "completionTime", "initialBalance", "balance", "fetchTime"}, "accountId == ? AND validatorAddress = ?", new String[]{""+accountId, vAddr}, null, null, null);
//        if(cursor != null && cursor.moveToFirst()) {
//            do {
//                UnBondingState temp = new UnBondingState(
//                        cursor.getLong(0),
//                        cursor.getString(1),
//                        cursor.getString(2),
//                        cursor.getLong(3),
//                        new BigDecimal(cursor.getString(4)),
//                        new BigDecimal(cursor.getString(5)),
//                        cursor.getLong(6));
//                result.add(temp);
//            } while (cursor.moveToNext());
//        }
//        cursor.close();
//        return result;
//    }
//
//    public long onInsertUnbondingStates(UnBondingState unbonding) {
//        ContentValues values = new ContentValues();
//        values.put("accountId",         unbonding.accountId);
//        values.put("validatorAddress",  unbonding.validatorAddress);
//        values.put("creationHeight",    unbonding.creationHeight);
//        values.put("completionTime",    unbonding.completionTime);
//        values.put("initialBalance",    unbonding.initialBalance.toPlainString());
//        values.put("balance",           unbonding.balance.toPlainString());
//        values.put("fetchTime",         unbonding.fetchTime);
//        return getBaseDB().insertOrThrow(BaseConstant.DB_TABLE_UNBONDING, null, values);
//    }
//
//    public boolean onDeleteUnbondingStates(long accountId) {
//        return getBaseDB().delete(BaseConstant.DB_TABLE_UNBONDING, "accountId = ?", new String[]{""+accountId}) > 0;
//    }
//
//    public boolean onDeleteUnbondingState(long accountId, String vAddr) {
//        return getBaseDB().delete(BaseConstant.DB_TABLE_UNBONDING, "accountId = ? AND validatorAddress = ?", new String[]{""+accountId, vAddr}) > 0;
//    }
//
//    public void onUpdateUnbondingStates(long accountId, ArrayList<UnBondingState> unbondings) {
//        onDeleteUnbondingStates(accountId);
//        for(UnBondingState unbond: unbondings) {
//            onInsertUnbondingStates(unbond);
//        }
//    }
//
//    public void onUpdateUnbondingState(long accountId, UnBondingState unbonding) {
//        onDeleteUnbondingState(accountId, unbonding.validatorAddress);
//        onInsertUnbondingStates(unbonding);
//    }

}
