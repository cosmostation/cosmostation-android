package wannabit.io.cosmostaion.activities.chains.kava;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.math.BigDecimal;
import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.network.res.ResKavaHarvestAccount;
import wannabit.io.cosmostaion.network.res.ResKavaHarvestDeposit;
import wannabit.io.cosmostaion.network.res.ResKavaHarvestParam;
import wannabit.io.cosmostaion.network.res.ResKavaHarvestReward;
import wannabit.io.cosmostaion.network.res.ResKavaMarketPrice;
import wannabit.io.cosmostaion.network.res.ResKavaPriceFeedParam;
import wannabit.io.cosmostaion.task.FetchTask.KavaHarvestDepositTask;
import wannabit.io.cosmostaion.task.FetchTask.KavaHarvestParamTask;
import wannabit.io.cosmostaion.task.FetchTask.KavaHarvestPoolTask;
import wannabit.io.cosmostaion.task.FetchTask.KavaHarvestRewardTask;
import wannabit.io.cosmostaion.task.FetchTask.KavaMarketPriceTask;
import wannabit.io.cosmostaion.task.FetchTask.KavaPriceFeedParamTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WLog;

import static wannabit.io.cosmostaion.base.BaseConstant.KAVA_COIN_IMG_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.KAVA_HARVEST_MARKET_IMG_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_FETCH_KAVA_HARVEST_ACCOUNT;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_FETCH_KAVA_HARVEST_DEPOSIT;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_FETCH_KAVA_HARVEST_PARAM;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_FETCH_KAVA_HARVEST_REWARD;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_FETCH_KAVA_PRICE_FEED_PARAM;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_FETCH_KAVA_TOKEN_PRICE;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HARD;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_KAVA;

public class HarvestDetailActivity extends BaseActivity implements TaskListener {

    private Toolbar             mToolbar;
    private SwipeRefreshLayout  mSwipeRefreshLayout;
    private RelativeLayout      mLoadingLayer;
    private Button              mOpenDeposit;

    private CardView            mHarvestTopCard, mHarvestMyCard, mHarvestAssetCard;

    private ImageView           mTopMarketImg;
    private TextView            mTopMarketTitle;
    private TextView            mTopStartTime, mTopEndTime, mTopPoolSupplyAmount, mTopPoolSupplyAmountDenom;

    private ImageView           mMyDepositCoinImg;
    private TextView            mMyDepositCoinTitle;
    private TextView            mMyDepositAmount, mMyDepositAmountDenom;
    private RelativeLayout      mMyBtnDeposit, mMyBtnWithdraw;
    private ImageView           mMyRewardCoinImg;
    private TextView            mMyRewardCoinTitle;
    private TextView            mMyRewardAmount, mMyRewardAmountDenom;
    private RelativeLayout      mMyBtnClaim;

    private RelativeLayout      mAssetDepositLayer;
    private ImageView           mAssetDepositImg, mAssetRewardImg;
    private TextView            mAssetDepositDenom, mAssetDepositAmount, mAssetRewardDenom, mAssetRewardAmount, mAssetKavaDenom, mAssetKavaAmount;
    private TextView            mDepositValue, mRewardValue, mKavaValue;


    private String                                      mDepositDenom;
    private ResKavaHarvestParam.DistributionSchedule    mDistributionSchedule;
    private ResKavaHarvestAccount.HarvestAccountValue   mHarvestPool;
    private ResKavaHarvestDeposit.HarvestDeposit        mMyHarvestDeposit;
    private ResKavaHarvestReward.HarvestReward          mMyHarvestReward;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_harvest_detail);

        mToolbar                    = findViewById(R.id.tool_bar);
        mSwipeRefreshLayout         = findViewById(R.id.layer_refresher);
        mLoadingLayer               = findViewById(R.id.loadingLayer);
        mOpenDeposit                = findViewById(R.id.btn_open_harvest);

        mHarvestTopCard             = findViewById(R.id.card_harvest_info);
        mTopMarketImg               = mHarvestTopCard.findViewById(R.id.market_img);
        mTopMarketTitle             = mHarvestTopCard.findViewById(R.id.market_title);
        mTopStartTime               = mHarvestTopCard.findViewById(R.id.start_time);
        mTopEndTime                 = mHarvestTopCard.findViewById(R.id.end_time);
        mTopPoolSupplyAmount        = mHarvestTopCard.findViewById(R.id.total_deposited_amount);
        mTopPoolSupplyAmountDenom   = mHarvestTopCard.findViewById(R.id.total_deposited_symbol);

        mHarvestMyCard              = findViewById(R.id.card_harvest_my);
        mMyDepositCoinImg           = mHarvestMyCard.findViewById(R.id.deposit_icon);
        mMyDepositCoinTitle         = mHarvestMyCard.findViewById(R.id.deposit_denom);
        mMyDepositAmount            = mHarvestMyCard.findViewById(R.id.deposited_amount);
        mMyDepositAmountDenom       = mHarvestMyCard.findViewById(R.id.deposited_symbol);
        mMyBtnDeposit               = mHarvestMyCard.findViewById(R.id.btn_deposit);
        mMyBtnWithdraw              = mHarvestMyCard.findViewById(R.id.btn_withdraw);
        mMyRewardCoinImg            = mHarvestMyCard.findViewById(R.id.reward_icon);
        mMyRewardCoinTitle          = mHarvestMyCard.findViewById(R.id.reward_denom);
        mMyRewardAmount             = mHarvestMyCard.findViewById(R.id.harvest_reward_amount);
        mMyRewardAmountDenom        = mHarvestMyCard.findViewById(R.id.harvest_reward_symbol);
        mMyBtnClaim                 = mHarvestMyCard.findViewById(R.id.btn_claim_reward);

        mHarvestAssetCard           = findViewById(R.id.card_harvest_asset);
        mAssetDepositLayer          = mHarvestAssetCard.findViewById(R.id.collateral_layer);
        mAssetDepositImg            = mHarvestAssetCard.findViewById(R.id.collateral_icon);
        mAssetRewardImg             = mHarvestAssetCard.findViewById(R.id.principal_icon);
        mAssetDepositDenom          = mHarvestAssetCard.findViewById(R.id.collateral_denom);
        mAssetDepositAmount         = mHarvestAssetCard.findViewById(R.id.collateral_amount);
        mAssetRewardDenom           = mHarvestAssetCard.findViewById(R.id.principal_denom);
        mAssetRewardAmount          = mHarvestAssetCard.findViewById(R.id.principal_amount);
        mAssetKavaDenom             = mHarvestAssetCard.findViewById(R.id.kava_denom);
        mAssetKavaAmount            = mHarvestAssetCard.findViewById(R.id.kava_amount);
        mDepositValue               = mHarvestAssetCard.findViewById(R.id.collateral_value);
        mRewardValue                = mHarvestAssetCard.findViewById(R.id.principal_value);
        mKavaValue                  = mHarvestAssetCard.findViewById(R.id.kava_value);
        mDepositValue.setText("");
        mRewardValue.setText("");
        mKavaValue.setText("");

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mAccount = getBaseDao().onSelectAccount(getBaseDao().getLastUser());
        mBaseChain = BaseChain.getChain(mAccount.baseChain);
        mBalances = mAccount.getBalances();
        mDepositDenom = getIntent().getStringExtra("deposit_denom");

        mSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                onFetchHarvestInfo();
            }
        });
        onFetchHarvestInfo();
        mOpenDeposit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onHarvestDeposit();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void onUpdateView() {
        onUpdateTopView();
        onUpdateMyView();
        onUpdateAssetView();
    }

    private void onUpdateTopView() {
        if (mDistributionSchedule != null && mHarvestPool != null) {
            mHarvestTopCard.setVisibility(View.VISIBLE);
            String marketTitle = mDistributionSchedule.deposit_denom.equals(TOKEN_KAVA) ? "kava" : mDistributionSchedule.deposit_denom;
            mTopMarketTitle.setText(marketTitle.toUpperCase() + " POOL");
            mTopStartTime.setText(WDp.getTimeTxformat(getBaseContext(), mDistributionSchedule.start));
            mTopEndTime.setText(WDp.getTimeTxformat(getBaseContext(), mDistributionSchedule.end));
            try {
                Picasso.get().load(KAVA_HARVEST_MARKET_IMG_URL + "lp" + mDistributionSchedule.deposit_denom + ".png").fit().into(mTopMarketImg);
            } catch (Exception e) { }


            Coin totalSupply = null;
            for (Coin coin:mHarvestPool.coins) {
                if (coin.denom.equals(mDepositDenom)) {
                    totalSupply = coin;
                    break;
                }
            }
            if (totalSupply != null) {
                WDp.showCoinDp(getBaseContext(), totalSupply, mTopPoolSupplyAmountDenom, mTopPoolSupplyAmount, mBaseChain);
            }

        }

    }

    private void onUpdateMyView() {
        if (mMyHarvestDeposit != null) {
            mHarvestMyCard.setVisibility(View.VISIBLE);
            if (mDepositDenom.equals(TOKEN_KAVA)) {
                WDp.DpMainDenom(getBaseContext(), mBaseChain.getChain(), mMyDepositCoinTitle);
            } else if (mDepositDenom.equals(TOKEN_HARD)) {
                mMyRewardCoinTitle.setText(mDepositDenom.toUpperCase());
                mMyRewardCoinTitle.setTextColor(getResources().getColor(R.color.colorHard));
            } else {
                mMyRewardCoinTitle.setText(mDepositDenom.toUpperCase());
                mMyRewardCoinTitle.setTextColor(getResources().getColor(R.color.colorWhite));
            }
            WDp.showCoinDp(getBaseContext(), mMyHarvestDeposit.amount, mMyDepositAmountDenom, mMyDepositAmount, mBaseChain);
            WDp.showCoinDp(getBaseContext(), mMyHarvestReward.amount, mMyRewardAmountDenom, mMyRewardAmount, mBaseChain);
            try {
                Picasso.get().load(KAVA_COIN_IMG_URL + mDepositDenom + ".png").fit().into(mMyDepositCoinImg);
                Picasso.get().load(KAVA_COIN_IMG_URL + "hard" + ".png").fit().into(mMyRewardCoinImg);
            } catch (Exception e) { }
            mMyBtnDeposit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onHarvestDeposit();
                }
            });
            mMyBtnWithdraw.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onHarvestWithdraw();
                }
            });
            mMyBtnClaim.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onHarvestClaim();
                }
            });

        }

    }

    private void onUpdateAssetView() {
        mHarvestAssetCard.setVisibility(View.VISIBLE);
        if (mDepositDenom.equals(TOKEN_KAVA) || mDepositDenom.equals(TOKEN_HARD)) {
            mAssetDepositLayer.setVisibility(View.GONE);
        }
        BigDecimal targetAvailable = WDp.getAvailableCoin(mBalances, mDepositDenom);
        BigDecimal hardAvailable = WDp.getAvailableCoin(mBalances, TOKEN_HARD);
        BigDecimal kavaAvailable = WDp.getAvailableCoin(mBalances, TOKEN_KAVA);

        WDp.showCoinDp(getBaseContext(), mDepositDenom, targetAvailable.toPlainString(), mAssetDepositDenom, mAssetDepositAmount, mBaseChain);
        WDp.showCoinDp(getBaseContext(), TOKEN_HARD, hardAvailable.toPlainString(), mAssetRewardDenom, mAssetRewardAmount, mBaseChain);
        WDp.showCoinDp(getBaseContext(), TOKEN_KAVA, kavaAvailable.toPlainString(), mAssetKavaDenom, mAssetKavaAmount, mBaseChain);
        try {
            Picasso.get().load(KAVA_COIN_IMG_URL + mDepositDenom + ".png").fit().into(mAssetDepositImg);
            Picasso.get().load(KAVA_COIN_IMG_URL + "hard" + ".png").fit().into(mAssetRewardImg);
        } catch (Exception e) { }
    }

    private void onHarvestDeposit() {
        WLog.w("onHarvestDeposit");

    }

    private void onHarvestWithdraw() {
        WLog.w("onHarvestWithdraw");

    }

    private void onHarvestClaim() {
        WLog.w("onHarvestClaim");

    }

    private int mTaskCount = 0;
    public void onFetchHarvestInfo() {
        mTaskCount = 3;
        getBaseDao().mKavaTokenPrices.clear();
        getBaseDao().mHavestDeposits.clear();
        getBaseDao().mHavestRewards.clear();
        new KavaPriceFeedParamTask(getBaseApplication(), this, BaseChain.getChain(mAccount.baseChain)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        new KavaHarvestParamTask(getBaseApplication(), this, BaseChain.getChain(mAccount.baseChain)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        new KavaHarvestDepositTask(getBaseApplication(), this, BaseChain.getChain(mAccount.baseChain), mAccount).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        new KavaHarvestRewardTask(getBaseApplication(), this, BaseChain.getChain(mAccount.baseChain), mAccount).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        new KavaHarvestPoolTask(getBaseApplication(), this, BaseChain.getChain(mAccount.baseChain)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    @Override
    public void onTaskResponse(TaskResult result) {
        if(isFinishing()) return;
        mTaskCount--;
        if (result.taskType == TASK_FETCH_KAVA_PRICE_FEED_PARAM) {
            getBaseDao().mKavaTokenPrices.clear();
            if (result.isSuccess && result.resultData != null) {
                final ResKavaPriceFeedParam.KavaPriceParam kavaPriceParam = (ResKavaPriceFeedParam.KavaPriceParam)result.resultData;
                getBaseDao().mKavaTokenPrices.clear();
                if (kavaPriceParam != null && kavaPriceParam.markets != null && kavaPriceParam.markets.size() > 0) {
                    mTaskCount = mTaskCount + kavaPriceParam.markets.size();
                    for (ResKavaPriceFeedParam.KavaPriceMarket market:kavaPriceParam.markets) {
                        new KavaMarketPriceTask(getBaseApplication(), this, BaseChain.getChain(mAccount.baseChain), market.market_id).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                    }
                }
            }

        } else if (result.taskType == TASK_FETCH_KAVA_TOKEN_PRICE) {
            if (result.isSuccess && result.resultData != null) {
                final ResKavaMarketPrice.Result price = (ResKavaMarketPrice.Result)result.resultData;
                getBaseDao().mKavaTokenPrices.put(price.market_id, price);
            }

        } else if (result.taskType == TASK_FETCH_KAVA_HARVEST_PARAM) {
            if (result.isSuccess && result.resultData != null) {
                if (result.isSuccess && result.resultData != null) {
                    getBaseDao().mHarvestParam = (ResKavaHarvestParam)result.resultData;
                }
            }

        } else if (result.taskType == TASK_FETCH_KAVA_HARVEST_DEPOSIT) {
            if (result.isSuccess && result.resultData != null) {
                getBaseDao().mHavestDeposits = ((ResKavaHarvestDeposit)result.resultData).result;
            }

        } else if (result.taskType == TASK_FETCH_KAVA_HARVEST_REWARD) {
            if (result.isSuccess && result.resultData != null) {
                getBaseDao().mHavestRewards = ((ResKavaHarvestReward)result.resultData).result;
            }

        } else if (result.taskType == TASK_FETCH_KAVA_HARVEST_ACCOUNT) {
            if (result.isSuccess && result.resultData != null) {
                ResKavaHarvestAccount poolAccount = ((ResKavaHarvestAccount)result.resultData);
                for (ResKavaHarvestAccount.HarvestAccount account:poolAccount.result) {
                    if (account.value.name.equals("harvest")) {
                        mHarvestPool = account.value;
                        break;
                    }
                }
            }

        }

        if (mTaskCount == 0) {
            ResKavaHarvestParam harvestParam                            = getBaseDao().mHarvestParam;
            ArrayList<ResKavaHarvestDeposit.HarvestDeposit> deposits    = getBaseDao().mHavestDeposits;
            ArrayList<ResKavaHarvestReward.HarvestReward> rewards       = getBaseDao().mHavestRewards;

            if (harvestParam == null || mHarvestPool == null) {
                WLog.w("Error");
            }
            for (ResKavaHarvestParam.DistributionSchedule schedule:harvestParam.result.liquidity_provider_schedules) {
                if (schedule.deposit_denom.equals(mDepositDenom)) {
                    mDistributionSchedule = schedule;
                    break;
                }
            }

            if (deposits != null) {
                for (ResKavaHarvestDeposit.HarvestDeposit deposit:deposits) {
                    if (deposit.amount.denom.equals(mDepositDenom)) {
                        mMyHarvestDeposit = deposit;
                        break;
                    }
                }
                mOpenDeposit.setVisibility(View.GONE);
            } else {
                mOpenDeposit.setVisibility(View.VISIBLE);
            }

            if (rewards != null) {
                for (ResKavaHarvestReward.HarvestReward reward:rewards) {
                    if (reward.deposit_denom.equals(mDepositDenom) && reward.type.equals("lp")) {
                        mMyHarvestReward = reward;
                        break;
                    }
                }
            }

            if (mMyHarvestDeposit != null) {
                WLog.w("mMyHarvestDeposit " + mMyHarvestDeposit.amount.amount);
            }
            if (mMyHarvestReward != null) {
                WLog.w("mMyHarvestReward " + mMyHarvestReward.amount.amount);
            }

            mSwipeRefreshLayout.setRefreshing(false);
            mLoadingLayer.setVisibility(View.GONE);
            onUpdateView();
        }
    }

}
