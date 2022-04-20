package wannabit.io.cosmostaion.base;

import static wannabit.io.cosmostaion.base.BaseChain.BNB_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.COSMOS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.CRESCENT_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.CRESCENT_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.KAVA_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.LUM_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.OKEX_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.SECRET_MAIN;
import static wannabit.io.cosmostaion.base.BaseConstant.FEE_BNB_SEND;
import static wannabit.io.cosmostaion.base.BaseConstant.IOV_MSG_TYPE_RENEW_ACCOUNT;
import static wannabit.io.cosmostaion.base.BaseConstant.IOV_MSG_TYPE_RENEW_DOMAIN;
import static wannabit.io.cosmostaion.base.BaseConstant.PRE_EVENT_HIDE;
import static wannabit.io.cosmostaion.base.BaseConstant.PRE_USER_EXPENDED_CHAINS;
import static wannabit.io.cosmostaion.base.BaseConstant.PRE_USER_HIDEN_CHAINS;
import static wannabit.io.cosmostaion.base.BaseConstant.PRE_USER_SORTED_CHAINS;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_BNB;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_KAVA;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_OK;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;

import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf2.Any;

import net.sqlcipher.Cursor;
import net.sqlcipher.database.SQLiteDatabase;

import org.json.JSONArray;
import org.json.JSONException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import cosmos.base.v1beta1.CoinOuterClass;
import cosmos.distribution.v1beta1.Distribution;
import cosmos.staking.v1beta1.Staking;
import cosmos.vesting.v1beta1.Vesting;
import desmos.profiles.v1beta1.ModelsProfile;
import kava.cdp.v1beta1.Genesis;
import kava.hard.v1beta1.Hard;
import kava.pricefeed.v1beta1.QueryOuterClass;
import kava.swap.v1beta1.Swap;
import osmosis.gamm.poolmodels.balancer.BalancerPool;
import tendermint.liquidity.v1beta1.Liquidity;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.chain.ChainFactory;
import wannabit.io.cosmostaion.crypto.EncResult;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.dao.Assets;
import wannabit.io.cosmostaion.dao.Balance;
import wannabit.io.cosmostaion.dao.BnbTicker;
import wannabit.io.cosmostaion.dao.BnbToken;
import wannabit.io.cosmostaion.dao.ChainParam;
import wannabit.io.cosmostaion.dao.Cw20Assets;
import wannabit.io.cosmostaion.dao.IbcPath;
import wannabit.io.cosmostaion.dao.IbcToken;
import wannabit.io.cosmostaion.dao.OkToken;
import wannabit.io.cosmostaion.dao.Password;
import wannabit.io.cosmostaion.dao.Price;
import wannabit.io.cosmostaion.model.BondingInfo;
import wannabit.io.cosmostaion.model.GDexManager;
import wannabit.io.cosmostaion.model.NodeInfo;
import wannabit.io.cosmostaion.model.RewardInfo;
import wannabit.io.cosmostaion.model.SifIncentive;
import wannabit.io.cosmostaion.model.UnbondingInfo;
import wannabit.io.cosmostaion.model.kava.IncentiveParam;
import wannabit.io.cosmostaion.model.kava.IncentiveReward;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.model.type.Validator;
import wannabit.io.cosmostaion.network.res.ResBnbFee;
import wannabit.io.cosmostaion.network.res.ResOkAccountInfo;
import wannabit.io.cosmostaion.network.res.ResOkStaking;
import wannabit.io.cosmostaion.network.res.ResOkTickersList;
import wannabit.io.cosmostaion.network.res.ResOkTokenList;
import wannabit.io.cosmostaion.network.res.ResOkUnbonding;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WKey;
import wannabit.io.cosmostaion.utils.WLog;
import wannabit.io.cosmostaion.utils.WUtil;

public class BaseData {

    private BaseApplication         mApp;
    private SharedPreferences       mSharedPreferences;
    private SQLiteDatabase          mSQLiteDatabase;
    public String                   mCopySalt;
    public EncResult                mCopyEncResult;

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


    public ArrayList<Price>                 mPrices = new ArrayList<>();
    public ChainParam.Params                mChainParam;
    public ArrayList<IbcPath>               mIbcPaths = new ArrayList<>();
    public ArrayList<IbcToken>              mIbcTokens = new ArrayList<>();
    public ArrayList<Assets>                mAssets = new ArrayList<>();
    public ArrayList<Cw20Assets>            mCw20Assets = new ArrayList<>();

    public Price getPrice(String denom) {
        for (Price price: mPrices) {
            if (price.denom.equals(denom.toLowerCase())) {
                return price;
            }
        }
        return null;
    }

    public IbcToken getIbcToken(String denom) {
        String ibcHash = denom.replace("ibc/", "");
        for (IbcToken ibcToken: mIbcTokens) {
            if (ibcToken.hash.equals(ibcHash)) {
                return ibcToken;
            }
        }
        return null;
    }

    public IbcPath.Path getIbcPath(String channelId) {
        for (IbcPath ibcPath: mIbcPaths) {
            for (IbcPath.Path path: ibcPath.paths) {
                if (path.channel_id.equals(channelId)) {
                    return path;
                }
            }
        }
        return null;
    }

    public Assets getAsset(String denom) {
        for (Assets assets: mAssets) {
            if (assets.denom.equalsIgnoreCase(denom)) {
                return assets;
            }
        }
        return null;
    }

    public void setCw20Balance(String contAddress, String amount) {
        for (Cw20Assets assets: mCw20Assets) {
            if (assets.contract_address.equalsIgnoreCase(contAddress)) {
                assets.setAmount(amount);
            }
        }
    }

    public ArrayList<Cw20Assets> getCw20sGrpc(BaseChain baseChain) {
        ArrayList<Cw20Assets> result = new ArrayList<>();
        if (mCw20Assets.size() > 0) {
            for (Cw20Assets assets: mCw20Assets) {
                if (assets.chain.equalsIgnoreCase(ChainFactory.getChain(baseChain).getChainName()) && assets.getAmount() != null && assets.getAmount().compareTo(BigDecimal.ZERO) > 0) {
                    result.add(assets);
                }
            }
        }
        return result;
    }

    public Cw20Assets getCw20_gRPC(String contAddress) {
        if (mCw20Assets != null && mCw20Assets.size() > 0) {
            for (Cw20Assets assets: mCw20Assets) {
                if (assets.contract_address.equalsIgnoreCase(contAddress)) {
                    return assets;
                }
            }
        }
        return null;
    }

    public String getBaseDenom(String denom) {
        if (denom != null) {
            if (denom.startsWith("ibc/")) {
                IbcToken ibcToken = getIbcToken(denom.replaceAll("ibc/", ""));
                if (ibcToken != null && ibcToken.auth) {
                    if (ibcToken.base_denom.startsWith("cw20:")) {
                        String cAddress = ibcToken.base_denom.replaceAll("cw20:", "");
                        for (Cw20Assets assets: mCw20Assets) {
                            if (assets.contract_address.equalsIgnoreCase(cAddress)) {
                                return assets.denom;
                            }
                        }
                    } else {
                        return ibcToken.base_denom;
                    }
                } else {
                    return "UNKNOWN";
                }
            } else if (denom.startsWith("c")) {
                return denom.substring(1);
            }
        }
        return denom;
    }

    public String getIbcRelayerImg(BaseChain baseChain, String channelId) {
        String url = "";
        if (getIbcPath(channelId).relayer_img != null) {
            url = getIbcPath(channelId).relayer_img;
        } else {
            url = ChainFactory.getChain(baseChain).getDefaultRelayerImg();
        }
        return url;
    }

    public ArrayList<IbcPath> getIbcSendableRelayers() {
        ArrayList<IbcPath> result = new ArrayList<>();
        for (IbcPath ibcPath: mIbcPaths) {
            for (IbcPath.Path path: ibcPath.paths) {
                if (path.auth != null && path.auth) {
                    result.add(ibcPath);
                }
            }
        }
        Set<IbcPath> arr2 = new HashSet<>(result);
        ArrayList<IbcPath> resArr2 = new ArrayList<>(arr2);
        return resArr2;
    }

    public ArrayList<IbcPath> getIbcRollbackRelayer(String denom) {
        ArrayList<IbcPath> result = new ArrayList<>();
        IbcToken ibcToken = getIbcToken(denom.replaceAll("ibc/", ""));
        for (IbcPath ibcPath: mIbcPaths) {
            for (IbcPath.Path path: ibcPath.paths) {
                if (path.channel_id != null && path.channel_id.equalsIgnoreCase(ibcToken.channel_id)) {
                    result.add(ibcPath);
                }
            }
        }
        return result;
    }

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

    //COMMON DATA FOR BINANCE
    public ArrayList<BnbToken>      mBnbTokens = new ArrayList<>();
    public ArrayList<BnbTicker>     mBnbTickers = new ArrayList<>();
    public ArrayList<ResBnbFee>     mBnbFees = new ArrayList<>();

    //COMMON DATA FOR OKEX
    public ResOkAccountInfo         mOkAccountInfo;
    public ResOkStaking             mOkStaking;
    public ResOkUnbonding           mOkUnbonding;
    public ResOkTokenList           mOkTokenList;
    public ResOkTickersList         mOkTickersList;
    public BigDecimal               mOKBPrice = BigDecimal.ZERO;

    //INCENTIVE DATA FOR SIF
    public SifIncentive.User        mSifLmIncentive;

    //GRPC for KAVA
    public ArrayList<QueryOuterClass.CurrentPriceResponse>              mKavaPrices = new ArrayList<>();
    public HashMap<String, QueryOuterClass.CurrentPriceResponse>        mKavaTokenPrice = new HashMap<>();
    public IncentiveParam                                               mIncentiveParam5;
    public IncentiveReward                                              mIncentiveRewards;
    public Swap.Params                                                  mSwapParams;
    public Genesis.Params                                               mCdpParams;
    public Hard.Params                                                  mHardParams;
    public ArrayList<Coin>                                              mModuleCoins = new ArrayList<>();
    public ArrayList<CoinOuterClass.Coin>                               mReserveCoins = new ArrayList<>();
    public ArrayList<kava.hard.v1beta1.QueryOuterClass.DepositResponse> mMyHardDeposits = new ArrayList<>();
    public ArrayList<kava.hard.v1beta1.QueryOuterClass.BorrowResponse>  mMyHardBorrows = new ArrayList<>();

    public String getChainId() {
        if (mNodeInfo != null) {
            return mNodeInfo.network;
        }
        return "";
    }

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

    public BigDecimal delegatableAmount(String denom) {
        return availableAmount(denom).add(lockedAmount(denom));
    }

    public BigDecimal frozenAmount(String denom) {
        BigDecimal result = BigDecimal.ZERO;
        for (Balance balance: mBalances) {
            if (balance.symbol.equalsIgnoreCase(denom)) {
                result = balance.frozen;
            }
        }
        return result;
    }

    public BigDecimal delegatedSumAmount() {
        BigDecimal result = BigDecimal.ZERO;
        for (BondingInfo bondingInfo: mMyDelegations) {
            if (bondingInfo.balance != null) {
                result = result.add(bondingInfo.getBalance());
            }
        }
        return result;
    }

    public BigDecimal delegatedAmountByValidator(String opAddress) {
        BigDecimal result = BigDecimal.ZERO;
        for (BondingInfo bondingInfo: mMyDelegations) {
            if (bondingInfo.validator_address.equals(opAddress) && bondingInfo.balance != null) {
                result = result.add(bondingInfo.getBalance());
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

    public OkToken okToken(String denom) {
        for (OkToken token: mOkTokenList.data) {
            if (token.symbol.equals(denom)) {
                return token;
            }
        }
        return null;
    }

    public BigDecimal getTokenAmount(ArrayList<Balance> balances, String symbol) {
        BigDecimal result = BigDecimal.ZERO;
        if (balances != null) {
            for (Balance balance: balances) {
                if (balance.symbol.equalsIgnoreCase(symbol)) {
                    result = balance.balance;
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

    public BigDecimal getAllBnbTokenAmount(String denom) {
        return availableAmount(denom).add(lockedAmount(denom)).add(frozenAmount(denom));
    }

    public BnbToken getBnbToken(String denom) {
        for (BnbToken token: mBnbTokens) {
            if (token.symbol.equals(denom)) {
                return token;
            }
        }
        return null;
    }

    public BigDecimal getAllExToken(String denom) {
        if (denom.equals(TOKEN_OK)) {
            return availableAmount(denom).add(lockedAmount(denom)).add(okDepositAmount()).add(okWithdrawAmount());
        } else {
            return availableAmount(denom).add(lockedAmount(denom));
        }
    }

    //gRPC
    public tendermint.p2p.Types.NodeInfo                        mGRpcNodeInfo;
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

    //COMMON DATA FOR STARNAME
    public starnamed.x.configuration.v1beta1.Types.Fees         mGrpcStarNameFee;
    public starnamed.x.configuration.v1beta1.Types.Config       mGrpcStarNameConfig;

    //Osmosis pool list
    public ArrayList<BalancerPool.Pool>                              mGrpcOsmosisPool = new ArrayList<>();

    //Gravity pool list
    public ArrayList<Liquidity.Pool>                            mGrpcGravityPools = new ArrayList<>();
    public Liquidity.Params                                     mParams;

    //Gravity GDex Manager
    public ArrayList<GDexManager>                               mGDexManager = new ArrayList<>();
    //Gravity GDex Denom Supply
    public ArrayList<Coin>                                      mGDexPoolTokens = new ArrayList<>();

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
        if (mGRpcAccount != null && mGRpcAccount.getTypeUrl().contains(ModelsProfile.Profile.getDescriptor().getFullName())) {
            try {
                ModelsProfile.Profile profile = ModelsProfile.Profile.parseFrom(mGRpcAccount.getValue());
                if (profile.getAccount().getTypeUrl().contains(Vesting.PeriodicVestingAccount.getDescriptor().getFullName())) {
                    Vesting.PeriodicVestingAccount vestingAccount = Vesting.PeriodicVestingAccount.parseFrom(profile.getAccount().getValue());
                    return WDp.onParsePeriodicRemainVestingsByDenom(vestingAccount, denom);

                } else if (profile.getAccount().getTypeUrl().contains(Vesting.ContinuousVestingAccount.getDescriptor().getFullName())){
                    Vesting.ContinuousVestingAccount vestingAccount = Vesting.ContinuousVestingAccount.parseFrom(profile.getAccount().getValue());
                    long cTime = Calendar.getInstance().getTime().getTime();
                    long vestingEnd = vestingAccount.getBaseVestingAccount().getEndTime() * 1000;
                    if (cTime < vestingEnd) {
                        for (CoinOuterClass.Coin vesting : vestingAccount.getBaseVestingAccount().getOriginalVestingList()) {
                            if (vesting.getDenom().equals(denom)) {
                                result.add(Vesting.Period.newBuilder().setLength(vestingEnd).addAllAmount(vestingAccount.getBaseVestingAccount().getOriginalVestingList()).build());
                            }
                        }
                    }
                }
            } catch (InvalidProtocolBufferException e) { }

        } else {
            if (mGRpcAccount != null && mGRpcAccount.getTypeUrl().contains(Vesting.PeriodicVestingAccount.getDescriptor().getFullName())) {
                try {
                    Vesting.PeriodicVestingAccount vestingAccount = Vesting.PeriodicVestingAccount.parseFrom(mGRpcAccount.getValue());
                    return WDp.onParsePeriodicRemainVestingsByDenom(vestingAccount, denom);
                } catch (InvalidProtocolBufferException e) { }

            } else if (mGRpcAccount != null && mGRpcAccount.getTypeUrl().contains(Vesting.ContinuousVestingAccount.getDescriptor().getFullName())) {
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
        }
        return result;
    }

    public BigDecimal getDelegatable(BaseChain baseChain, String denom) {
        if (baseChain.equals(CRESCENT_MAIN) || baseChain.equals(CRESCENT_TEST)) {
            return getAvailable(denom);
        }
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

    // for kava funcs
    public Genesis.CollateralParam getCollateralParamByType(String type) {
        Genesis.CollateralParam result = null;
        if (mCdpParams != null && mCdpParams.getCollateralParamsList().size() > 0) {
            for (Genesis.CollateralParam collateralParam: mCdpParams.getCollateralParamsList()) {
                if (collateralParam.getType().equalsIgnoreCase(type)) {
                    return collateralParam;
                }
            }
        }
        return result;
    }

    public BigDecimal getKavaOraclePrice(String market_id) {
        BigDecimal price = BigDecimal.ZERO;
        if (mKavaTokenPrice != null && mKavaTokenPrice.get(market_id).getPrice() != null) {
            price = new BigDecimal(mKavaTokenPrice.get(market_id).getPrice()).movePointLeft(18);
        }
        return price;
    }

    // for starname funcs
    public BigDecimal getStarNameRegisterDomainFee(String domain, String type) {
        BigDecimal feeAmount = BigDecimal.ZERO;
        if (mGrpcStarNameFee == null) { return feeAmount; }
        if (TextUtils.isEmpty(domain) || domain.length() <= 3) {
            return feeAmount;
        } else if (domain.length() == 4) {
            feeAmount = new BigDecimal(mGrpcStarNameFee.getRegisterDomain4()).divide(new BigDecimal(mGrpcStarNameFee.getFeeCoinPrice()), 0, RoundingMode.DOWN);
        } else if (domain.length() == 5) {
            feeAmount = new BigDecimal(mGrpcStarNameFee.getRegisterDomain5()).divide(new BigDecimal(mGrpcStarNameFee.getFeeCoinPrice()), 0, RoundingMode.DOWN);
        } else {
            feeAmount = new BigDecimal(mGrpcStarNameFee.getRegisterDomainDefault()).divide(new BigDecimal(mGrpcStarNameFee.getFeeCoinPrice()), 0, RoundingMode.DOWN);
        }
        if (type.equals("open")) {
            feeAmount = feeAmount.multiply(new BigDecimal(mGrpcStarNameFee.getRegisterOpenDomainMultiplier()).movePointLeft(18));
        }
        return feeAmount;
    }

    public BigDecimal getStarNameRegisterAccountFee(String type) {
        BigDecimal feeAmount = BigDecimal.ZERO;
        if (mGrpcStarNameFee == null) { return feeAmount; }
        if (type.equals("open")) {
            return new BigDecimal(mGrpcStarNameFee.getRegisterAccountOpen()).divide(new BigDecimal(mGrpcStarNameFee.getFeeCoinPrice()), 0, RoundingMode.DOWN);
        } else {
            return new BigDecimal(mGrpcStarNameFee.getRegisterAccountClosed()).divide(new BigDecimal(mGrpcStarNameFee.getFeeCoinPrice()), 0, RoundingMode.DOWN);
        }
    }

    public BigDecimal getStarNameRenewDomainFee(String domain, String type) {
        BigDecimal feeAmount = BigDecimal.ZERO;
        if (mGrpcStarNameFee == null) { return feeAmount; }
        if (type == "open") {
            return new BigDecimal(mGrpcStarNameFee.getRenewDomainOpen()).divide(new BigDecimal(mGrpcStarNameFee.getFeeCoinPrice()), 0, RoundingMode.DOWN);
        } else {
            BigDecimal registerFee = getStarNameRegisterDomainFee(domain, type);
            BigDecimal addtionalFee = new BigDecimal(mGrpcStarNameFee.getRegisterAccountClosed()).divide(new BigDecimal(mGrpcStarNameFee.getFeeCoinPrice()), 0, RoundingMode.DOWN);
            return registerFee.add(addtionalFee);
        }
    }

    public BigDecimal getStarNameRenewAccountFee(String type) {
        BigDecimal feeAmount = BigDecimal.ZERO;
        if (mGrpcStarNameFee == null) { return feeAmount; }
        if (type.equals("open")) {
            return new BigDecimal(mGrpcStarNameFee.getRegisterAccountOpen()).divide(new BigDecimal(mGrpcStarNameFee.getFeeCoinPrice()), 0, RoundingMode.DOWN);
        } else {
            return new BigDecimal(mGrpcStarNameFee.getRegisterAccountClosed()).divide(new BigDecimal(mGrpcStarNameFee.getFeeCoinPrice()), 0, RoundingMode.DOWN);
        }
    }

    public BigDecimal getReplaceFee() {
        BigDecimal feeAmount = BigDecimal.ZERO;
        if (mGrpcStarNameFee == null) { return feeAmount; }
        return new BigDecimal(mGrpcStarNameFee.getReplaceAccountResources()).divide(new BigDecimal(mGrpcStarNameFee.getFeeCoinPrice()), 0, RoundingMode.DOWN);
    }

    public long getStarNameRegisterDomainExpireTime() {
        if (mGrpcStarNameConfig == null) { return 0; }
        return Calendar.getInstance().getTimeInMillis() + mGrpcStarNameConfig.getDomainRenewalPeriod().getSeconds() * 1000;
    }

    public long getRenewExpireTime(String type, long currentExpire) {
        if (mGrpcStarNameConfig == null) { return 0; }
        if (type.equals(IOV_MSG_TYPE_RENEW_DOMAIN)) {
            return currentExpire + mGrpcStarNameConfig.getDomainRenewalPeriod().getSeconds() * 1000;
        } else if (type.equals(IOV_MSG_TYPE_RENEW_ACCOUNT)) {
            return currentExpire + mGrpcStarNameConfig.getAccountRenewalPeriod().getSeconds() * 1000;
        }
        return 0;
    }

    public Liquidity.Pool getGravityPoolByDenom(String denom) {
        for (Liquidity.Pool pool: mGrpcGravityPools) {
            if (pool.getPoolCoinDenom().equals(denom)) {
                return pool;
            }
        }
        return null;
    }

    public ChainParam.GdexStatus getParamGravityPoolByDenom(String denom) {
        for (ChainParam.GdexStatus gdexStatus: mChainParam.getmGdexList()) {
            if (gdexStatus.pool_token.equals(denom)) {
                return gdexStatus;
            }
        }
        return null;
    }


    public BalancerPool.Pool getOsmosisPoolByDenom(String denom) {
        for (BalancerPool.Pool pool: mGrpcOsmosisPool) {
            if (pool.getTotalShares().getDenom().equals(denom)) {
                return pool;
            }
        }
        return null;
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
        Account account = onSelectAccount(String.valueOf(getSharedPreferences().getLong(BaseConstant.PRE_USER_ID, -1)));
        BaseChain mBaseChain = BaseChain.getChain(account.baseChain);
        if (!dpSortedChains().contains(mBaseChain)) {
            for (BaseChain chain : dpSortedChains()) {
                if (onSelectAccountsByChain(chain).size() > 0) {
                    return String.valueOf(onSelectAccountsByChain(chain).get(0).id);
                }
            }
        }
        return String.valueOf(getSharedPreferences().getLong(BaseConstant.PRE_USER_ID, -1));
    }

    public BaseChain getLastChain() {
        String chainName = getSharedPreferences().getString(BaseConstant.PRE_SELECTED_CHAINS, COSMOS_MAIN.getChain());
        if (userSortedChains().contains(BaseChain.getChain(chainName))) {
            return BaseChain.getChain(chainName);
        } else {
            return COSMOS_MAIN;
        }
    }

    public void setLastChain(String chainName) {
        getSharedPreferences().edit().putString(BaseConstant.PRE_SELECTED_CHAINS, chainName).commit();
    }

    public int getCurrency() {
        return getSharedPreferences().getInt(BaseConstant.PRE_CURRENCY, 0);
    }

    public String getCurrencyString() {
        if (getCurrency() == 0) {
            return "USD";
        } else if (getCurrency() == 1) {
            return "EUR";
        } else if (getCurrency() == 2) {
            return "KRW";
        } else if (getCurrency() == 3) {
            return "JPY";
        } else if (getCurrency() == 4) {
            return "CNY";
        } else if (getCurrency() == 5) {
            return "RUB";
        } else if (getCurrency() == 6) {
            return "GBP";
        } else if (getCurrency() == 7) {
            return "INR";
        } else if (getCurrency() == 8) {
            return "BRL";
        } else if (getCurrency() == 9) {
            return "IDR";
        } else if (getCurrency() == 10) {
            return "DKK";
        } else if (getCurrency() == 11) {
            return "NOK";
        } else if (getCurrency() == 12) {
            return "SEK";
        } else if (getCurrency() == 13) {
            return "CHF";
        } else if (getCurrency() == 14) {
            return "AUD";
        } else if (getCurrency() == 15) {
            return "CAD";
        } else if (getCurrency() == 16) {
            return "MYR";
        }
        return "";
    }

    public String getCurrencySymbol() {
        if (getCurrency() == 0) {
            return "$";
        } else if (getCurrency() == 1) {
            return "€";
        } else if (getCurrency() == 2) {
            return "₩";
        } else if (getCurrency() == 3) {
            return "¥";
        } else if (getCurrency() == 4) {
            return "¥";
        } else if (getCurrency() == 5) {
            return "₽";
        } else if (getCurrency() == 6) {
            return "£";
        } else if (getCurrency() == 7) {
            return "₹";
        } else if (getCurrency() == 8) {
            return "R$";
        } else if (getCurrency() == 9) {
            return "Rp";
        } else if (getCurrency() == 10) {
            return "Kr";
        } else if (getCurrency() == 11) {
            return "Kr";
        } else if (getCurrency() == 12) {
            return "Kr";
        } else if (getCurrency() == 13) {
            return "sFr";
        } else if (getCurrency() == 14) {
            return "AU$";
        } else if (getCurrency() == 15) {
            return "$";
        } else if (getCurrency() == 16) {
            return "RM";
        }
        return "";
    }

    public void setCurrency(int currency) {
        getSharedPreferences().edit().putInt(BaseConstant.PRE_CURRENCY, currency).commit();
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

    public void setUserHidenChains(ArrayList<BaseChain> hidedChains) {
        JSONArray array = new JSONArray();
        for (BaseChain baseChain: hidedChains) {
            array.put(baseChain.getChain());
        }
        if (!hidedChains.isEmpty()) {
            getSharedPreferences().edit().putString(PRE_USER_HIDEN_CHAINS, array.toString()).commit();
        } else{
            getSharedPreferences().edit().putString(PRE_USER_HIDEN_CHAINS, null).commit();
        }
    }

    public ArrayList<String> getUserHiddenChains() {
        String json = getSharedPreferences().getString(PRE_USER_HIDEN_CHAINS, null);
        ArrayList<String> hideChains = new ArrayList<>();
        if (json != null) {
            try {
                JSONArray array = new JSONArray(json);
                for (int i = 0; i < array.length(); i++) {
                    hideChains.add(array.optString(i));
                }
            } catch (JSONException e) { e.printStackTrace(); }

        }
        return hideChains;
    }

    public void setUserSortedChains(ArrayList<BaseChain> displayedChains) {
        JSONArray array = new JSONArray();
        for (BaseChain baseChain: displayedChains) {
            array.put(baseChain.getChain());
        }
        if (!displayedChains.isEmpty()) {
            getSharedPreferences().edit().putString(PRE_USER_SORTED_CHAINS, array.toString()).commit();
        } else{
            getSharedPreferences().edit().putString(PRE_USER_SORTED_CHAINS, null).commit();
        }
    }

    public ArrayList<String> getUserSortedChains() {
        String json = getSharedPreferences().getString(PRE_USER_SORTED_CHAINS, null);
        ArrayList<String> displayChains = new ArrayList<>();
        if (json != null) {
            try {
                JSONArray array = new JSONArray(json);
                for (int i = 0; i < array.length(); i++) {
                    displayChains.add(array.optString(i));
                }
            } catch (JSONException e) { e.printStackTrace(); }

        }
        return displayChains;
    }

    public ArrayList<BaseChain> userHideChains() {
        ArrayList<BaseChain> result = new ArrayList<>();
        ArrayList<BaseChain> mAllChains = new ArrayList<>();
        ArrayList<String> hiddenChains = getUserHiddenChains();
        for (BaseChain baseChain: BaseChain.SUPPORT_CHAINS()) {
            if (!baseChain.equals(BaseChain.COSMOS_MAIN)) {
                mAllChains.add(baseChain);
            }
        }
        for (BaseChain baseChain: mAllChains) {
            if (hiddenChains.contains(baseChain.getChain())) {
                result.add(baseChain);
            }
        }

        return result;
    }

    public ArrayList<BaseChain> userDisplayChains() {
        ArrayList<BaseChain> result = new ArrayList<>();
        ArrayList<BaseChain> mAllChains = new ArrayList<>();
        ArrayList<BaseChain> hiddenChains = userHideChains();
        for (BaseChain baseChain: BaseChain.SUPPORT_CHAINS()) {
            if (!baseChain.equals(BaseChain.COSMOS_MAIN)) {
                mAllChains.add(baseChain);
            }
        }
        for (BaseChain baseChain: mAllChains) {
            if (!hiddenChains.contains(baseChain)) {
                result.add(baseChain);
            }
        }
        ArrayList<BaseChain> sorted = new ArrayList<>();
        for (String chainName: getUserSortedChains()) {
            if (result.contains(BaseChain.getChain(chainName))) {
                sorted.add(BaseChain.getChain(chainName));
            }
        }
        for (BaseChain baseChain: result) {
            if (!sorted.contains(baseChain)) {
                sorted.add(baseChain);
            }
        }
        return sorted;
    }

    public ArrayList<BaseChain> userSortedChains() {
        ArrayList<BaseChain> result = new ArrayList<>();
        ArrayList<BaseChain> rawDpChains = userDisplayChains();
        ArrayList<String> orderedChains = getUserHiddenChains();
        for (BaseChain chain: rawDpChains) {
            if(!orderedChains.contains(chain.getChain())) {
                result.add(chain);
            }
        }
        return result;
    }

    public ArrayList<BaseChain> dpSortedChains() {
        ArrayList<BaseChain> result = new ArrayList<>();
        result.add(BaseChain.COSMOS_MAIN);
        ArrayList<BaseChain> rawDpChains = userDisplayChains();
        ArrayList<String> orderedChains = getUserHiddenChains();
        for (BaseChain chain: rawDpChains) {
            if(!orderedChains.contains(chain.getChain())) {
                result.add(chain);
            }
        }
        return result;
    }

    public void setExpendedChains(ArrayList<BaseChain> chains) {
        JSONArray array = new JSONArray();
        for (BaseChain baseChain: chains) {
            array.put(baseChain.getChain());
        }
        if (!chains.isEmpty()) {
            getSharedPreferences().edit().putString(PRE_USER_EXPENDED_CHAINS, array.toString()).commit();
        } else{
            getSharedPreferences().edit().putString(PRE_USER_EXPENDED_CHAINS, null).commit();
        }
    }

    public ArrayList<BaseChain> getExpendedChains() {
        String json = getSharedPreferences().getString(PRE_USER_EXPENDED_CHAINS, null);
        ArrayList<BaseChain> chains = new ArrayList<>();
        if (json != null) {
            try {
                JSONArray array = new JSONArray(json);
                for (int i = 0; i < array.length(); i++) {
                    chains.add(BaseChain.getChain(array.optString(i)));
                }
            } catch (JSONException e) { e.printStackTrace(); }

        }
        return chains;
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


    public ArrayList<Account> onSelectAccounts() {
        ArrayList<Account> result = new ArrayList<>();
        Cursor cursor 	= getBaseDB().query(BaseConstant.DB_TABLE_ACCOUNT, new String[]{"id", "uuid", "nickName", "isFavo", "address", "baseChain",
                "hasPrivateKey", "resource", "spec", "fromMnemonic", "path",
                "isValidator", "sequenceNumber", "accountNumber", "fetchTime", "msize", "importTime", "lastTotal", "sortOrder", "pushAlarm", "newBip", "customPath"}, null, null, null, null, null);
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
                        cursor.getInt(20) > 0,
                        cursor.getInt(21)
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

    public ArrayList<Account> onSelectAllAccountsByChainWithKey(BaseChain chain) {
        ArrayList<Account> result = new ArrayList<>();
        ArrayList<Account> AllAccount = onSelectAccounts();
        for (Account account:AllAccount) {
            if (BaseChain.getChain(account.baseChain).equals(chain) && account.hasPrivateKey) {
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
                if (chain.equals(BNB_MAIN)) {
                    if (getTokenAmount(account.balances, TOKEN_BNB).compareTo(new BigDecimal(FEE_BNB_SEND)) >= 0) {
                        result.add(account);
                    }
                } else if (chain.equals(KAVA_MAIN)) {
                    if (getTokenAmount(account.balances, TOKEN_KAVA).compareTo(new BigDecimal("12500")) >= 0) {
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
                "isValidator", "sequenceNumber", "accountNumber", "fetchTime", "msize", "importTime", "lastTotal", "sortOrder", "pushAlarm", "newBip", "customPath"}, "id == ?", new String[]{id}, null, null, null);
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
                    cursor.getInt(20) > 0,
                    cursor.getInt(21)
            );
            result.setBalances(onSelectBalance(result.id));
        }
        cursor.close();
        if (!BaseChain.IS_SUPPORT_CHAIN(result.baseChain)) {
            return onSelectAccounts().get(0);
        }
        return result;
    }

    public Account  onSelectExistAccount(String address, BaseChain chain) {
        ArrayList<Account> result = new ArrayList<>();
        Cursor cursor 	= getBaseDB().query(BaseConstant.DB_TABLE_ACCOUNT, new String[]{"id", "uuid", "nickName", "isFavo", "address", "baseChain",
                "hasPrivateKey", "resource", "spec", "fromMnemonic", "path",
                "isValidator", "sequenceNumber", "accountNumber", "fetchTime", "msize", "importTime", "lastTotal", "sortOrder", "pushAlarm", "newBip", "customPath"}, "address == ?", new String[]{address}, null, null, null);
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
                        cursor.getInt(20) > 0,
                        cursor.getInt(21)
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
                "isValidator", "sequenceNumber", "accountNumber", "fetchTime", "msize", "importTime", "lastTotal", "sortOrder", "pushAlarm", "newBip", "customPath"}, "address == ?", new String[]{address}, null, null, null);
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
                    cursor.getInt(20) > 0,
                    cursor.getInt(21)
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
        values.put("customPath",       account.customPath);
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

    public long onOverrideAccount(Account account) {
        ContentValues values = new ContentValues();
        values.put("hasPrivateKey",     account.hasPrivateKey);
        values.put("resource",          account.resource);
        values.put("spec",              account.spec);
        values.put("fromMnemonic",      account.fromMnemonic);
        values.put("path",              account.path);
        values.put("msize",             account.msize);
        values.put("newBip",            account.newBip44);
        values.put("customPath",       account.customPath);
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

    //set custompath 118 - > 0,
    public void upgradeAaccountAddressforPath() {
        ArrayList<Account> allOKAccounts = onSelectAccountsByChain(OKEX_MAIN);
        // update address with "0x" Eth style
        for (Account account: allOKAccounts) {
            if (account.newBip44 && account.customPath == 0) {
                account.customPath = 1;
                updateAccountPathType(account);
            }
            if (account.address.startsWith("okexchain")) {
                try {
                    account.address = WKey.getUpgradeOKAddress(account.address);
                } catch (Exception e) { e.printStackTrace(); }
            }
        }

        allOKAccounts = onSelectAccountsByChain(OKEX_MAIN);
        for (Account account: allOKAccounts) {
            if (account.address.startsWith("ex")) {
                try {
                    account.address = WKey.convertAddressOkexToEth(account.address);
                    updateAccountAddress(account);
                } catch (Exception e) { e.printStackTrace(); }
            }
        }

        //set custompath 118 -> 0, 529 -> 1
        ArrayList<Account> allSecretAccount = onSelectAccountsByChain(SECRET_MAIN);
        for (Account account: allSecretAccount) {
            if (account.fromMnemonic) {
                if (account.newBip44 && account.customPath != 1) {
                    account.customPath = 1;
                    updateAccountPathType(account);
                }
                if (!account.newBip44 && account.customPath != 0) {
                    account.customPath = 0;
                    updateAccountPathType(account);
                }
            }
        }

        //set custompath 118 -> 0, 459 -> 1
        ArrayList<Account> allKavaAccount = onSelectAccountsByChain(KAVA_MAIN);
        for (Account account: allKavaAccount) {
            if (account.fromMnemonic) {
                if (account.newBip44 && account.customPath != 1) {
                    account.customPath = 1;
                    updateAccountPathType(account);
                }
                if (!account.newBip44 && account.customPath != 0) {
                    account.customPath = 0;
                    updateAccountPathType(account);
                }
            }
        }

        //set custompath 118 -> 0, 880 -> 1
        ArrayList<Account> allLumAccount = onSelectAccountsByChain(LUM_MAIN);
        for (Account account: allLumAccount) {
            if (account.fromMnemonic) {
                if (account.newBip44 && account.customPath != 1) {
                    account.customPath = 1;
                    updateAccountPathType(account);
                }
                if (!account.newBip44 && account.customPath != 0) {
                    account.customPath = 0;
                    updateAccountPathType(account);
                }
            }
        }
    }

    //for okchain display address
    public long updateAccountAddress(Account account) {
        ContentValues values = new ContentValues();
        values.put("address",      account.address);
        return getBaseDB().update(BaseConstant.DB_TABLE_ACCOUNT, values, "id = ?", new String[]{""+account.id} );
    }

    //for okchain key custom_path 0 -> tendermint(996), 1 -> ethermint(996), 2 -> etherium(60)
    public long updateAccountPathType(Account account) {
        if (account.importTime > 1643986800000L) { return -1; }
        ContentValues values = new ContentValues();
        values.put("customPath",   account.customPath);
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
