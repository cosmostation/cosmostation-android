package wannabit.io.cosmostaion.fragment.chains.kava;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.chains.kava.CdpDetailActivity;
import wannabit.io.cosmostaion.activities.chains.kava.DAppsListActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.network.res.ResCdpOwnerStatus;
import wannabit.io.cosmostaion.network.res.ResCdpParam;
import wannabit.io.cosmostaion.network.res.ResKavaIncentiveParam;
import wannabit.io.cosmostaion.network.res.ResKavaIncentiveReward;
import wannabit.io.cosmostaion.network.res.ResKavaMarketPrice;
import wannabit.io.cosmostaion.network.res.ResKavaPriceFeedParam;
import wannabit.io.cosmostaion.task.FetchTask.KavaCdpByOwnerTask;
import wannabit.io.cosmostaion.task.FetchTask.KavaCdpParamTask;
import wannabit.io.cosmostaion.task.FetchTask.KavaIncentiveParamTask;
import wannabit.io.cosmostaion.task.FetchTask.KavaIncentiveRewardTask;
import wannabit.io.cosmostaion.task.FetchTask.KavaMarketPriceTask;
import wannabit.io.cosmostaion.task.FetchTask.KavaPriceFeedParamTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WUtil;

import static wannabit.io.cosmostaion.base.BaseConstant.KAVA_CDP_MARKET_IMG_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_FETCH_KAVA_CDP_OWENER;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_FETCH_KAVA_INCENTIVE_PARAM;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_FETCH_KAVA_INCENTIVE_REWARD;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_FETCH_KAVA_PRICE_FEED_PARAM;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_FETCH_KAVA_TOKEN_PRICE;

public class CdpMarketFragment extends BaseFragment implements TaskListener {

    private SwipeRefreshLayout                                          mSwipeRefreshLayout;
    private RecyclerView                                                mRecyclerView;
    private RelativeLayout                                              mProgress;
    private CdpMarketAdapter                                            mAdapter;

    private Account                                                     mAccount;
    private BaseChain                                                   mBaseChain;
    private ResCdpParam.Result                                          mCdpParam;
    private ArrayList<ResCdpOwnerStatus.MyCDP>                          mMyOwenCdps = new ArrayList<>();
    private ArrayList<ResCdpParam.KavaCollateralParam>                  mOtherCdps = new ArrayList<>();
    private ArrayList<ResKavaIncentiveReward.IncentiveRewardClaimable>  mIncentiveClaimables= new ArrayList<>();
    private HashMap<String, ResKavaMarketPrice.Result>                  mKavaTokenPrices = new HashMap<>();


    public static CdpMarketFragment newInstance(Bundle bundle) {
        CdpMarketFragment fragment = new CdpMarketFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_cdp_market, container, false);
        mSwipeRefreshLayout     = rootView.findViewById(R.id.layer_refresher);
        mRecyclerView           = rootView.findViewById(R.id.recycler);
        mProgress               = rootView.findViewById(R.id.reward_progress);
        mSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (mTaskCount > 0) { mSwipeRefreshLayout.setRefreshing(false);
                } else { onFetchCdpInfo(); }
            }
        });

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getBaseActivity(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new CdpMarketAdapter();
        mRecyclerView.setAdapter(mAdapter);

        mAccount = getSActivity().mAccount;
        mBaseChain = getSActivity().mBaseChain;
        onFetchCdpInfo();
        return rootView;
    }

    private int mTaskCount = 0;
    public void onFetchCdpInfo() {
        mTaskCount = 3;
        mMyOwenCdps.clear();
        mOtherCdps.clear();
        mIncentiveClaimables.clear();
        getBaseDao().mMyOwenCdp.clear();
        getBaseDao().mKavaUnClaimedIncentiveRewards.clear();
        new KavaCdpParamTask(getBaseApplication(), this, BaseChain.getChain(mAccount.baseChain)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        new KavaIncentiveParamTask(getBaseApplication(), this, BaseChain.getChain(mAccount.baseChain)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        new KavaPriceFeedParamTask(getBaseApplication(), this, BaseChain.getChain(mAccount.baseChain)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    @Override
    public void onTaskResponse(TaskResult result) {
        if(!isAdded()) return;
        mTaskCount--;
        if (result.taskType == BaseConstant.TASK_FETCH_KAVA_CDP_PARAM) {
            if (result.isSuccess && result.resultData != null) {
                final ResCdpParam.Result cdpParam = (ResCdpParam.Result)result.resultData;
                getBaseDao().mKavaCdpParams = cdpParam;
                if (cdpParam != null && cdpParam.collateral_params != null && cdpParam.collateral_params.size() > 0) {
                    mTaskCount = mTaskCount + cdpParam.collateral_params.size();
                    for (ResCdpParam.KavaCollateralParam param:getBaseDao().mKavaCdpParams.collateral_params) {
                        new KavaCdpByOwnerTask(getBaseApplication(), this, BaseChain.getChain(mAccount.baseChain), mAccount.address, param).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                    }
                }
            }

        } else if (result.taskType == TASK_FETCH_KAVA_CDP_OWENER) {
            if (result.isSuccess && result.resultData != null) {
                getBaseDao().mMyOwenCdp.add((ResCdpOwnerStatus.MyCDP)result.resultData);
            }

        } else if (result.taskType == TASK_FETCH_KAVA_PRICE_FEED_PARAM) {
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

        } else if (result.taskType == TASK_FETCH_KAVA_INCENTIVE_PARAM) {
            if (result.isSuccess && result.resultData != null) {
                getBaseDao().mKavaIncentiveParam = (ResKavaIncentiveParam.IncentiveParam)result.resultData;
                mTaskCount = mTaskCount + getBaseDao().mKavaIncentiveParam.rewards.size();
                for (ResKavaIncentiveParam.IncentiveReward rewardParam:getBaseDao().mKavaIncentiveParam.rewards) {
                    new KavaIncentiveRewardTask(getBaseApplication(), this, BaseChain.getChain(mAccount.baseChain), mAccount, rewardParam).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                }
            }

        } else if (result.taskType == TASK_FETCH_KAVA_INCENTIVE_REWARD) {
            if (result.isSuccess && result.resultData != null) {
                getBaseDao().mKavaUnClaimedIncentiveRewards.addAll((ArrayList<ResKavaIncentiveReward.IncentiveRewardClaimable>) result.resultData);
            }

        }
        if (mTaskCount == 0) {
            mCdpParam = getBaseDao().mKavaCdpParams;
            mKavaTokenPrices = getBaseDao().mKavaTokenPrices;
            mIncentiveClaimables = getBaseDao().mKavaUnClaimedIncentiveRewards;
            mMyOwenCdps = getBaseDao().mMyOwenCdp;
            onGetOtherCdp();

            mAdapter.notifyDataSetChanged();
            mProgress.setVisibility(View.GONE);
            mSwipeRefreshLayout.setRefreshing(false);
        }
    }

    private void onGetOtherCdp() {
        mOtherCdps.clear();
        for (ResCdpParam.KavaCollateralParam param:mCdpParam.collateral_params) {
            boolean has = false;
            for (ResCdpOwnerStatus.MyCDP my:mMyOwenCdps) {
                if (my.cdp.type.equals(param.type)) {
                    has = true;
                }
            }
            if (!has) {
                mOtherCdps.add(param);
            }
        }
    }


    private class CdpMarketAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private static final int TYPE_UNCLAIMED_INCENTIVE       = 0;
        private static final int TYPE_MY_CDP                    = 1;
        private static final int TYPE_OTHER_CDP                 = 2;


        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
            if (viewType == TYPE_UNCLAIMED_INCENTIVE) {
                return new IncentivepHolder(getLayoutInflater().inflate(R.layout.layout_kava_incentive, viewGroup, false));
            } else if (viewType == TYPE_MY_CDP) {
                return new MyCdpHolder(getLayoutInflater().inflate(R.layout.item_cdp_list_my, viewGroup, false));
            } else if (viewType == TYPE_OTHER_CDP) {
                return new OtherCdpHolder(getLayoutInflater().inflate(R.layout.item_cdp_list_all, viewGroup, false));

            }
            return null;
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
            if (getItemViewType(position) == TYPE_UNCLAIMED_INCENTIVE) {
                final IncentivepHolder holder = (IncentivepHolder)viewHolder;
                BigDecimal totalIncen = BigDecimal.ZERO;
                for (ResKavaIncentiveReward.IncentiveRewardClaimable reward:mIncentiveClaimables) {
                    if (reward.claimable) {
                        totalIncen = totalIncen.add(new BigDecimal(reward.claim.reward.amount));
                    }
                }
                holder.itemIncentiveAmount.setText(WDp.getDpAmount2(getContext(), totalIncen, 6, 6));

            } else if (getItemViewType(position) == TYPE_MY_CDP) {
                final MyCdpHolder holder = (MyCdpHolder)viewHolder;
                ResCdpOwnerStatus.MyCDP status;
                if (mIncentiveClaimables.size() > 0) {
                    status = mMyOwenCdps.get(position - 1);
                } else {
                    status = mMyOwenCdps.get(position);

                }
                final ResCdpParam.KavaCollateralParam collateralParam = mCdpParam.getCollateralParamByType(status.cdp.type);
                final ResKavaMarketPrice.Result price = mKavaTokenPrices.get(collateralParam.liquidation_market_id);
                final int denomPDecimal = WUtil.getKavaCoinDecimal(status.getPDenom());

                final BigDecimal currentPrice = new BigDecimal(price.price);
                final BigDecimal liquidationPrice = status.getLiquidationPrice(getContext(), collateralParam);
                final BigDecimal riskRate = new BigDecimal(100).subtract((currentPrice.subtract(liquidationPrice)).movePointRight(2).divide(currentPrice, 2, RoundingMode.DOWN));

//            WLog.w("currentPrice " +  currentPrice);
//            WLog.w("liquidationPrice " +  liquidationPrice);
//            WLog.w("riskRate " +  riskRate);

                holder.itemTitleMarket.setText(collateralParam.getDpMarketId());
                holder.itemDebtValueTitle.setText(String.format(getString(R.string.str_debt_value), status.getPDenom().toUpperCase()));
                holder.itemCollateralValueTitle.setText(String.format(getString(R.string.str_collateral_value_title3), status.getDenom().toUpperCase()));

                final BigDecimal debtValue = status.getPrincipalAmount();
                final BigDecimal feeValue = status.getAccumulatedFees();
                final BigDecimal hiddenFeeValue = WDp.getCdpHiddenFee(getContext(), debtValue.add(feeValue), collateralParam, status.cdp);
                final BigDecimal totalDebtValue = debtValue.add(feeValue).add(hiddenFeeValue);
                holder.itemDebtValue.setText(WDp.getDpRawDollor(getContext(), totalDebtValue.movePointLeft(denomPDecimal), 2));

                final BigDecimal currentCollateralValue = new BigDecimal(status.collateral_value.amount);
                holder.itemCollateralValue.setText(WDp.getDpRawDollor(getContext(), currentCollateralValue.movePointLeft(denomPDecimal), 2));

                holder.itemStabilityFee.setText(WDp.getPercentDp(collateralParam.getDpStabilityFee(), 2));
                holder.itemLiquidationPenalty.setText(WDp.getPercentDp(collateralParam.getDpLiquidationPenalty(), 2));

                holder.itemCurrentPriceTitle.setText(String.format(getString(R.string.str_current_title3), status.getDenom().toUpperCase()));
                holder.itemCurrentPrice.setText(WDp.getDpRawDollor(getContext(), currentPrice, 4));

                holder.itemLiquidationPriceTitle.setText(String.format(getString(R.string.str_liquidation_title3), status.getDenom().toUpperCase()));
                holder.itemLiquidationPrice.setText(WDp.getDpRawDollor(getContext(), liquidationPrice, 4));

                WDp.DpRiskRate(getContext(), riskRate, holder.itemRiskScore,  holder.itemImgRisk);
                try {
                    Picasso.get().load(KAVA_CDP_MARKET_IMG_URL+  collateralParam.getImagePath()).fit().into(holder.itemImgMarket);
                } catch (Exception e) { }

                holder.itemRoot.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getSActivity(), CdpDetailActivity.class);
                        intent.putExtra("collateralParamType", collateralParam.type);
                        intent.putExtra("marketId", collateralParam.liquidation_market_id);
                        startActivity(intent);
                    }
                });

            } else if (getItemViewType(position) == TYPE_OTHER_CDP) {
                final OtherCdpHolder holder = (OtherCdpHolder)viewHolder;
                ResCdpParam.KavaCollateralParam collateralParam;
                if (mIncentiveClaimables.size() > 0) {
                    collateralParam = mOtherCdps.get(position - mMyOwenCdps.size() - 1);
                } else {
                    collateralParam = mOtherCdps.get(position - mMyOwenCdps.size());

                }
                holder.itemTitleMarket.setText(collateralParam.getDpMarketId());
                holder.itemCollateralRate.setText(WDp.getPercentDp(collateralParam.getDpLiquidationRatio(), 2));
                holder.itemStabilityFee.setText(WDp.getPercentDp(collateralParam.getDpStabilityFee(), 2));
                holder.itemLiquidationPenalty.setText(WDp.getPercentDp(collateralParam.getDpLiquidationPenalty(), 2));

                Picasso.get().cancelRequest(holder.itemImgMarket);
                try {
                    Picasso.get().load(KAVA_CDP_MARKET_IMG_URL+  collateralParam.getImagePath()) .fit().into(holder.itemImgMarket);

                } catch (Exception e) { }
                holder.itemRoot.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getSActivity(), CdpDetailActivity.class);
                        intent.putExtra("collateralParamType", collateralParam.type);
                        intent.putExtra("marketId", collateralParam.liquidation_market_id);
                        startActivity(intent);
                    }
                });
            }

        }

        @Override
        public int getItemCount() {
            return mMyOwenCdps.size() + mOtherCdps.size() + mIncentiveClaimables.size();
        }

        @Override
        public int getItemViewType(int position) {
            if (mIncentiveClaimables.size() > 0) {
                if (position == 0) {
                    return TYPE_UNCLAIMED_INCENTIVE;
                } else if (position < (mMyOwenCdps.size() + 1)) {
                    return TYPE_MY_CDP;
                } else {
                    return TYPE_OTHER_CDP;
                }

            } else {
                if (position < mMyOwenCdps.size()) {
                    return TYPE_MY_CDP;
                } else {
                    return TYPE_OTHER_CDP;
                }
            }
        }

        public class IncentivepHolder extends RecyclerView.ViewHolder {
            TextView itemIncentiveAmount, itemIncentiveAmountDenom;

            public IncentivepHolder(@NonNull View itemView) {
                super(itemView);
                itemIncentiveAmount = itemView.findViewById(R.id.incentive_amount);
                itemIncentiveAmountDenom = itemView.findViewById(R.id.incentive_symbol);
            }
        }

        public class MyCdpHolder extends RecyclerView.ViewHolder {
            CardView itemRoot;
            ImageView itemImgMarket, itemImgRisk;
            TextView itemTitleMarket, itemRiskScore;
            TextView itemDebtValueTitle, itemDebtValue, itemCollateralValueTitle, itemCollateralValue;
            TextView itemStabilityFee, itemLiquidationPenalty;
            TextView itemCurrentPriceTitle, itemCurrentPrice, itemLiquidationPriceTitle, itemLiquidationPrice;


            public MyCdpHolder(@NonNull View itemView) {
                super(itemView);
                itemRoot = itemView.findViewById(R.id.card_cdp_my);
                itemImgMarket = itemView.findViewById(R.id.market_img);
                itemTitleMarket = itemView.findViewById(R.id.market_title);
                itemImgRisk = itemView.findViewById(R.id.cdp_safe_img);
                itemRiskScore = itemView.findViewById(R.id.cdp_safe_rate);
                itemDebtValueTitle = itemView.findViewById(R.id.cdp_debt_value_title);
                itemDebtValue = itemView.findViewById(R.id.cdp_debt_value);
                itemCollateralValueTitle = itemView.findViewById(R.id.cdp_collateral_value_title);
                itemCollateralValue = itemView.findViewById(R.id.cdp_collateral_value);
                itemStabilityFee = itemView.findViewById(R.id.cdp_stability_fee);
                itemLiquidationPenalty = itemView.findViewById(R.id.cdp_str_liquidation_penalty);
                itemCurrentPriceTitle = itemView.findViewById(R.id.current_price_title);
                itemCurrentPrice = itemView.findViewById(R.id.current_price);
                itemLiquidationPriceTitle = itemView.findViewById(R.id.liquidation_price_title);
                itemLiquidationPrice = itemView.findViewById(R.id.liquidation_price);
            }
        }

        public class OtherCdpHolder extends RecyclerView.ViewHolder {
            CardView itemRoot;
            ImageView itemImgMarket;
            TextView itemTitleMarket;
            TextView itemCollateralRate, itemStabilityFee, itemLiquidationPenalty;


            public OtherCdpHolder(@NonNull View itemView) {
                super(itemView);
                itemRoot = itemView.findViewById(R.id.card_cdp_all);
                itemImgMarket = itemView.findViewById(R.id.cdp_market_img);
                itemTitleMarket = itemView.findViewById(R.id.cdp_market_title);
                itemCollateralRate = itemView.findViewById(R.id.cdp_collateral_rate);
                itemStabilityFee = itemView.findViewById(R.id.cdp_stability_fee);
                itemLiquidationPenalty = itemView.findViewById(R.id.cdp_str_liquidation_penalty);
            }
        }
    }

    private DAppsListActivity getSActivity() {
        return (DAppsListActivity)getBaseActivity();
    }
}
