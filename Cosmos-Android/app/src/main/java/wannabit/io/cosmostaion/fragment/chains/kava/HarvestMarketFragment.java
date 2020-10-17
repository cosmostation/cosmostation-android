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
import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.KavaCdpDetailActivity;
import wannabit.io.cosmostaion.activities.chains.kava.DAppsListActivity;
import wannabit.io.cosmostaion.activities.chains.kava.HarvestDetailActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.dao.BondingState;
import wannabit.io.cosmostaion.model.type.Validator;
import wannabit.io.cosmostaion.network.res.ResKavaHarvestDeposit;
import wannabit.io.cosmostaion.network.res.ResKavaHarvestParam;
import wannabit.io.cosmostaion.network.res.ResKavaHarvestReward;
import wannabit.io.cosmostaion.task.FetchTask.KavaHarvestDepositTask;
import wannabit.io.cosmostaion.task.FetchTask.KavaHarvestParamTask;
import wannabit.io.cosmostaion.task.FetchTask.KavaHarvestRewardTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WLog;

import static wannabit.io.cosmostaion.base.BaseConstant.KAVA_HARVEST_MARKET_IMG_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_FETCH_KAVA_HARVEST_DEPOSIT;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_FETCH_KAVA_HARVEST_PARAM;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_FETCH_KAVA_HARVEST_REWARD;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_KAVA;

public class HarvestMarketFragment extends BaseFragment implements TaskListener {

    private SwipeRefreshLayout                                  mSwipeRefreshLayout;
    private RecyclerView                                        mRecyclerView;
    private RelativeLayout                                      mProgress;
    private HarvestMarketAdapter                                mAdapter;

    private Account                                             mAccount;
    private BaseChain                                           mBaseChain;
    public ResKavaHarvestParam                                  mHarvestParam;
    public ArrayList<ResKavaHarvestParam.DistributionSchedule>  mMyLps = new ArrayList<>();
    public ArrayList<ResKavaHarvestParam.DistributionSchedule>  mOtherLps = new ArrayList<>();
    public ArrayList<ResKavaHarvestDeposit.HarvestDeposit>      mHavestDeposits = new ArrayList<>();
    public ArrayList<ResKavaHarvestReward.HarvestReward>        mHavestRewards = new ArrayList<>();
    public ArrayList<ResKavaHarvestReward.HarvestReward>        mHavestStakeRewards = new ArrayList<>();
    public ArrayList<ResKavaHarvestReward.HarvestReward>        mHavestLpRewards = new ArrayList<>();

    public static HarvestMarketFragment newInstance(Bundle bundle) {
        HarvestMarketFragment fragment = new HarvestMarketFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_harvest_market, container, false);
        mSwipeRefreshLayout     = rootView.findViewById(R.id.layer_refresher);
        mRecyclerView           = rootView.findViewById(R.id.recycler);
        mProgress               = rootView.findViewById(R.id.reward_progress);
        mSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (mTaskCount > 0) { mSwipeRefreshLayout.setRefreshing(false);
                } else { onFetchHarvestInfo(); }
            }
        });

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getBaseActivity(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new HarvestMarketAdapter();
        mRecyclerView.setAdapter(mAdapter);

        mAccount = getSActivity().mAccount;
        mBaseChain = getSActivity().mBaseChain;
        onFetchHarvestInfo();
        return rootView;
    }



    private int mTaskCount = 0;
    public void onFetchHarvestInfo() {
        mTaskCount = 3;
        mMyLps.clear();
        mOtherLps.clear();
        mHavestDeposits.clear();
        mHavestRewards.clear();
        mHavestStakeRewards.clear();
        mHavestLpRewards.clear();
        getBaseDao().mHavestDeposits.clear();
        getBaseDao().mHavestRewards.clear();
        new KavaHarvestParamTask(getBaseApplication(), this, BaseChain.getChain(mAccount.baseChain)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        new KavaHarvestDepositTask(getBaseApplication(), this, BaseChain.getChain(mAccount.baseChain), mAccount).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        new KavaHarvestRewardTask(getBaseApplication(), this, BaseChain.getChain(mAccount.baseChain), mAccount).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    @Override
    public void onTaskResponse(TaskResult result) {
        if(!isAdded()) return;
        mTaskCount--;
        if (result.taskType == TASK_FETCH_KAVA_HARVEST_PARAM) {
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

        }

        if (mTaskCount == 0) {
            mHarvestParam = getBaseDao().mHarvestParam;
            mHavestDeposits = getBaseDao().mHavestDeposits;
            mHavestRewards = getBaseDao().mHavestRewards;
            onSortHarvest();


            mAdapter.notifyDataSetChanged();
            mProgress.setVisibility(View.GONE);
            mSwipeRefreshLayout.setRefreshing(false);
        }
    }

    private void onSortHarvest() {
        if (mHavestRewards != null) {
            for (ResKavaHarvestReward.HarvestReward hReward:mHavestRewards) {
                if (hReward.type.equals("lp")) {
                    mHavestLpRewards.add(hReward);
                } else if (hReward.type.equals("stake")) {
                    mHavestStakeRewards.add(hReward);
                }
            }
        }
//        WLog.w("mHavestLpRewards " + mHavestLpRewards.size());
//        WLog.w("mHavestStakeRewards " + mHavestStakeRewards.size());

        if (mHarvestParam != null) {
            for (ResKavaHarvestParam.DistributionSchedule schedule:mHarvestParam.result.liquidity_provider_schedules) {
                if (!schedule.active) {
                    continue;
                }
                boolean has = false;
                if (mHavestDeposits != null) {
                    for (ResKavaHarvestDeposit.HarvestDeposit deposit:mHavestDeposits) {
                        if (deposit.amount.denom.equals(schedule.deposit_denom)) {
                            has = true;
                        }
                    }
                }
                if (has) {
                    mMyLps.add(schedule);
                } else {
                    mOtherLps.add(schedule);
                }
            }
        }
//        WLog.w("mMyLps " + mMyLps.size());
//        WLog.w("mOtherLps " + mOtherLps.size());
    }


    private class HarvestMarketAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private static final int TYPE_STAKER_HEADER         = 0;
        private static final int TYPE_STAKER                = 1;
        private static final int TYPE_LP_HEADER             = 3;
        private static final int TYPE_LP_REWARD             = 4;
        private static final int TYPE_MY_LP                 = 5;
        private static final int TYPE_OTHER_LP              = 6;


        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
            if (viewType == TYPE_STAKER_HEADER) {
                return new HeaderStakerHolder(getLayoutInflater().inflate(R.layout.item_harvest_list_stake_header, viewGroup, false));
            } else if (viewType == TYPE_STAKER) {
                return new StakerHolder(getLayoutInflater().inflate(R.layout.item_harvest_list_stake, viewGroup, false));
            } else if (viewType == TYPE_LP_HEADER) {
                return new HeaderLpHolder(getLayoutInflater().inflate(R.layout.item_harvest_list_lp_header, viewGroup, false));
            } else if (viewType == TYPE_LP_REWARD) {
                return new LpRewardHolder(getLayoutInflater().inflate(R.layout.item_harvest_list_reward_lp, viewGroup, false));
            } else if (viewType == TYPE_MY_LP) {
                return new MyLpHolder(getLayoutInflater().inflate(R.layout.item_harvest_list_my_lp, viewGroup, false));
            } else if (viewType == TYPE_OTHER_LP) {
                return new OtherLpHolder(getLayoutInflater().inflate(R.layout.item_harvest_list_other_lp, viewGroup, false));
            }
            return null;
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
            if (getItemViewType(position) == TYPE_STAKER_HEADER || getItemViewType(position) == TYPE_LP_HEADER) {

            } else if (getItemViewType(position) == TYPE_STAKER) {
                onBindStake(viewHolder, position);

            } else if (getItemViewType(position) == TYPE_LP_REWARD) {
                onBindLpTotalReward(viewHolder, position);

            } else if (getItemViewType(position) == TYPE_MY_LP) {
                onBindMyLp(viewHolder, position);

            } else if (getItemViewType(position) == TYPE_OTHER_LP) {
                onBindOtherLp(viewHolder, position);

            }
        }

        @Override
        public int getItemCount() {
            if (mHavestLpRewards.size() > 0) {
                return mMyLps.size() + mOtherLps.size() + 4;
            } else {
                return mMyLps.size() + mOtherLps.size() + 3;
            }
        }

        @Override
        public int getItemViewType(int position) {
            if (position == 0) {
                return TYPE_STAKER_HEADER;

            } else if (position == 1) {
                return TYPE_STAKER;

            } else if (position == 2) {
                return TYPE_LP_HEADER;

            } else {
                if (mHavestLpRewards.size() > 0) {
                    if (position == 3) {
                        return TYPE_LP_REWARD;
                    } else if (position < (mMyLps.size() + 4)) {
                        return TYPE_MY_LP;
                    } else {
                        return TYPE_OTHER_LP;
                    }

                } else {
                    if (position < (mMyLps.size() + 3)) {
                        return TYPE_MY_LP;
                    } else {
                        return TYPE_OTHER_LP;
                    }
                }
            }
        }

        private void onBindStake(RecyclerView.ViewHolder viewHolder, int position) {
            final StakerHolder holder = (StakerHolder)viewHolder;
            holder.itemTitleMarket.setText("KAVA STAKE");
            if (mHarvestParam != null && mHarvestParam.getKavaStakerSchedule() != null) {
                holder.itemStartTime.setText(WDp.getTimeTxformat(getContext(), mHarvestParam.getKavaStakerSchedule().distribution_schedule.start));
                holder.itemEndTime.setText(WDp.getTimeTxformat(getContext(), mHarvestParam.getKavaStakerSchedule().distribution_schedule.end));

                BigDecimal stakeRewardSum = BigDecimal.ZERO;
                for (ResKavaHarvestReward.HarvestReward stakeReward:mHavestStakeRewards) {
                    stakeRewardSum = stakeRewardSum.add(new BigDecimal(stakeReward.amount.amount));
                }
                holder.itemRewardAmount.setText(WDp.getDpAmount2(getContext(), stakeRewardSum, 6, 6));

                ArrayList<BondingState> bondings = getBaseDao().onSelectBondingStates(mAccount.id);
                ArrayList<Validator> allValidators = getBaseDao().mAllValidators;
                BigDecimal delegatedAmount = WDp.getAllDelegatedAmount(bondings, allValidators, mBaseChain);
                holder.itemTotalDelegate.setText(WDp.getDpAmount2(getContext(), delegatedAmount, 6, 6));

                holder.itemBtnClaim.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        WLog.w("Claim Stake reward");
                    }
                });
            }
        }

        private void onBindLpTotalReward(RecyclerView.ViewHolder viewHolder, int position) {
            final LpRewardHolder holder = (LpRewardHolder)viewHolder;
            if (mHavestLpRewards != null) {
                BigDecimal lpRewardSum = BigDecimal.ZERO;
                for (ResKavaHarvestReward.HarvestReward lpReward:mHavestLpRewards) {
                    lpRewardSum = lpRewardSum.add(new BigDecimal(lpReward.amount.amount));
                }
                holder.itemTotalLpRewardAmount.setText(WDp.getDpAmount2(getContext(), lpRewardSum, 6, 6));
            }
        }

        private void onBindMyLp(RecyclerView.ViewHolder viewHolder, int position) {
            final MyLpHolder holder = (MyLpHolder)viewHolder;
            ResKavaHarvestParam.DistributionSchedule myLp;
            if (mHavestLpRewards.size() > 0) {
                myLp = mMyLps.get(position - 4);
            } else {
                myLp = mMyLps.get(position - 3);
            }
            ResKavaHarvestDeposit.HarvestDeposit myDeposit = null;
            for (ResKavaHarvestDeposit.HarvestDeposit deposit:mHavestDeposits) {
                if (deposit.amount.denom.equals(myLp.deposit_denom)) {
                    myDeposit = deposit;
                    break;
                }
            }
            ResKavaHarvestReward.HarvestReward myReward = null;
            for (ResKavaHarvestReward.HarvestReward reward:mHavestLpRewards) {
                if (reward.deposit_denom.equals(myLp.deposit_denom)) {
                    myReward = reward;
                    break;
                }
            }
//            WLog.w("myLp " + myLp.deposit_denom);
//            WLog.w("myDeposit " + myDeposit.depositor);
//            WLog.w("myReward " + myReward.amount);
            if (myLp != null) {
                String marketTitle = myLp.deposit_denom.equals(TOKEN_KAVA) ? "kava" : myLp.deposit_denom;
                holder.itemTitleMarket.setText(marketTitle.toUpperCase() + " POOL");
                holder.itemStartTime.setText(WDp.getTimeTxformat(getContext(), myLp.start));
                holder.itemEndTime.setText(WDp.getTimeTxformat(getContext(), myLp.end));
                try {
                    Picasso.get().load(KAVA_HARVEST_MARKET_IMG_URL + "lp" + myLp.deposit_denom + ".png").fit().into(holder.itemImgMarket);
                } catch (Exception e) { }
                holder.itemRoot.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getSActivity(), HarvestDetailActivity.class);
                        intent.putExtra("deposit_denom", myLp.deposit_denom);
                        startActivity(intent);
                    }
                });

            }
            if (myDeposit != null) {
                holder.itemdepositAmount.setText(WDp.getDpAmount2(getContext(), new BigDecimal(myDeposit.amount.amount), 6, 6));

            }
            if (myReward != null) {
                holder.itemRewardAmount.setText(WDp.getDpAmount2(getContext(), new BigDecimal(myReward.amount.amount), 6, 6));
            }
        }

        private void onBindOtherLp(RecyclerView.ViewHolder viewHolder, int position) {
            final OtherLpHolder holder = (OtherLpHolder)viewHolder;
            ResKavaHarvestParam.DistributionSchedule otherLp;
            if (mHavestLpRewards.size() > 0) {
                otherLp = mOtherLps.get(position - mMyLps.size() - 4);
            } else {
                otherLp = mOtherLps.get(position - mMyLps.size() - 3);
            }
//            WLog.w("otherLp " + otherLp.deposit_denom);
            if (otherLp != null) {
                String marketTitle = otherLp.deposit_denom.equals(TOKEN_KAVA) ? "kava" : otherLp.deposit_denom;
                holder.itemTitleMarket.setText(marketTitle.toUpperCase() + " POOL");
                holder.itemStartTime.setText(WDp.getTimeTxformat(getContext(), otherLp.start));
                holder.itemEndTime.setText(WDp.getTimeTxformat(getContext(), otherLp.end));
                try {
                    Picasso.get().load(KAVA_HARVEST_MARKET_IMG_URL + "lp" + otherLp.deposit_denom + ".png").fit().into(holder.itemImgMarket);
                } catch (Exception e) { }
                holder.itemRoot.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getSActivity(), HarvestDetailActivity.class);
                        intent.putExtra("deposit_denom", otherLp.deposit_denom);
                        startActivity(intent);
                    }
                });
            }

        }


        public class HeaderStakerHolder extends RecyclerView.ViewHolder {
            public HeaderStakerHolder(@NonNull View itemView) {
                super(itemView);
            }
        }

        public class HeaderLpHolder extends RecyclerView.ViewHolder {
            public HeaderLpHolder(@NonNull View itemView) {
                super(itemView);
            }
        }

        public class StakerHolder extends RecyclerView.ViewHolder {
            CardView itemRoot;
            ImageView itemImgMarket;
            TextView itemTitleMarket;
            TextView itemStartTime, itemEndTime, itemTotalDelegate, itemRewardAmount;
            RelativeLayout itemBtnClaim;

            public StakerHolder(@NonNull View itemView) {
                super(itemView);
                itemRoot            = itemView.findViewById(R.id.card_cdp_my);
                itemImgMarket       = itemView.findViewById(R.id.market_img);
                itemTitleMarket     = itemView.findViewById(R.id.market_title);
                itemStartTime       = itemView.findViewById(R.id.start_time);
                itemEndTime         = itemView.findViewById(R.id.end_time);
                itemTotalDelegate   = itemView.findViewById(R.id.total_delegated_amount);
                itemRewardAmount    = itemView.findViewById(R.id.harvest_reward_amount);
                itemBtnClaim        = itemView.findViewById(R.id.btn_kava_reward);
            }
        }

        public class LpRewardHolder extends RecyclerView.ViewHolder {
            TextView itemTotalLpRewardAmount;

            public LpRewardHolder(@NonNull View itemView) {
                super(itemView);
                itemTotalLpRewardAmount = itemView.findViewById(R.id.lp_reward_amount);
            }
        }

        public class MyLpHolder extends RecyclerView.ViewHolder {
            CardView itemRoot;
            ImageView itemImgMarket;
            TextView itemTitleMarket;
            TextView itemStartTime, itemEndTime, itemdepositAmount, itemRewardAmount;


            public MyLpHolder(@NonNull View itemView) {
                super(itemView);
                itemRoot            = itemView.findViewById(R.id.card_cdp_my);
                itemImgMarket       = itemView.findViewById(R.id.market_img);
                itemTitleMarket     = itemView.findViewById(R.id.market_title);
                itemStartTime       = itemView.findViewById(R.id.start_time);
                itemEndTime         = itemView.findViewById(R.id.end_time);
                itemdepositAmount   = itemView.findViewById(R.id.deposited_amount);
                itemRewardAmount    = itemView.findViewById(R.id.harvest_reward_amount);
            }
        }

        public class OtherLpHolder extends RecyclerView.ViewHolder {
            CardView itemRoot;
            ImageView itemImgMarket;
            TextView itemTitleMarket;
            TextView itemStartTime, itemEndTime;

            public OtherLpHolder(@NonNull View itemView) {
                super(itemView);
                itemRoot            = itemView.findViewById(R.id.card_cdp_my);
                itemImgMarket       = itemView.findViewById(R.id.market_img);
                itemTitleMarket     = itemView.findViewById(R.id.market_title);
                itemStartTime       = itemView.findViewById(R.id.start_time);
                itemEndTime         = itemView.findViewById(R.id.end_time);
            }
        }
    }

    private DAppsListActivity getSActivity() {
        return (DAppsListActivity)getBaseActivity();
    }
}
