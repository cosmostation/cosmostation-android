package wannabit.io.cosmostaion.fragment.chains.kava;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.chains.kava.ClaimIncentiveActivity;
import wannabit.io.cosmostaion.activities.chains.kava.DAppsList5Activity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.dialog.Dialog_WatchMode;
import wannabit.io.cosmostaion.network.res.ResKavaCdpParam;
import wannabit.io.cosmostaion.network.res.ResKavaIncentiveReward5;
import wannabit.io.cosmostaion.network.res.ResKavaMyCdps;
import wannabit.io.cosmostaion.task.FetchTask.KavaCdpByOwnerTask;
import wannabit.io.cosmostaion.task.FetchTask.KavaIncentiveRewardTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;
import wannabit.io.cosmostaion.widget.BaseHolder;
import wannabit.io.cosmostaion.widget.CdpIncentiveHolder;
import wannabit.io.cosmostaion.widget.CdpMyHolder;
import wannabit.io.cosmostaion.widget.CdpOtherHolder;

import static wannabit.io.cosmostaion.base.BaseConstant.TASK_FETCH_KAVA_CDP_OWENER;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_FETCH_KAVA_INCENTIVE_REWARD;

public class ListCdpFragment extends BaseFragment implements TaskListener {
    private SwipeRefreshLayout  mSwipeRefreshLayout;
    private RecyclerView        mRecyclerView;
    private RelativeLayout      mProgress;

    private Account             mAccount;
    private BaseChain           mBaseChain;
    private CdpMarketAdapter    mAdapter;

    private ResKavaCdpParam.CdpParam                        mCdpParams;
    private ArrayList<ResKavaMyCdps.MyCDP>                  mMyCdps = new ArrayList<>();
    private ArrayList<ResKavaCdpParam.KavaCollateralParam>  mOtherCdps = new ArrayList<>();
    private ResKavaIncentiveReward5.IncentiveReward5        mIncentiveRewards5;


    public static ListCdpFragment newInstance(Bundle bundle) {
        ListCdpFragment fragment = new ListCdpFragment();
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
                if (mTaskCount > 0 || mCdpParams == null) {
                    mSwipeRefreshLayout.setRefreshing(false);
                } else {
                    onFetchCdpInfo();
                }
            }
        });

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getBaseActivity(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new CdpMarketAdapter();
        mRecyclerView.setAdapter(mAdapter);

        mAccount = getSActivity().mAccount;
        mBaseChain = getSActivity().mBaseChain;
        return rootView;
    }

    @Override
    public void onRefreshTab() {
        mCdpParams = getBaseDao().mCdpParam;
        onFetchCdpInfo();
    }

    private int mTaskCount = 0;
    public void onFetchCdpInfo() {
        mMyCdps.clear();
        mOtherCdps.clear();
        mTaskCount = 2;
        new KavaIncentiveRewardTask(getBaseApplication(), this, mBaseChain, mAccount, null).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        new KavaCdpByOwnerTask(getBaseApplication(), this, mBaseChain, mAccount.address, null).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    @Override
    public void onTaskResponse(TaskResult result) {
        if(!isAdded()) return;
        mTaskCount--;
        if (result.taskType == TASK_FETCH_KAVA_INCENTIVE_REWARD) {
            mIncentiveRewards5 = (ResKavaIncentiveReward5.IncentiveReward5)result.resultData;

        } else if (result.taskType == TASK_FETCH_KAVA_CDP_OWENER) {
            mMyCdps = (ArrayList<ResKavaMyCdps.MyCDP>)result.resultData;
        }

        if (mTaskCount == 0) {
            for (ResKavaCdpParam.KavaCollateralParam cdpParam: mCdpParams.collateral_params) {
                boolean has = false;
                for (ResKavaMyCdps.MyCDP cdpMy: mMyCdps) {
                    if (cdpMy.cdp.type.equals(cdpParam.type)) {
                        has = true;
                    }
                }
                if (!has) {
                    mOtherCdps.add(cdpParam);
                }
            }
            mAdapter.notifyDataSetChanged();
            mProgress.setVisibility(View.GONE);
            mSwipeRefreshLayout.setRefreshing(false);
        }

    }

    public void onCheckStartClaimIncentive() {
        if (!mAccount.hasPrivateKey) {
            Dialog_WatchMode add = Dialog_WatchMode.newInstance();
            add.setCancelable(true);
            getFragmentManager().beginTransaction().add(add, "dialog").commitNowAllowingStateLoss();
            return;
        }

        if (mCdpParams.circuit_breaker) {
            Toast.makeText(getContext(), R.string.error_circuit_breaker, Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(getContext(), ClaimIncentiveActivity.class);
//        intent.putExtra("collateral_type", mCollateralParam.type);
        startActivity(intent);
    }


    private class CdpMarketAdapter extends RecyclerView.Adapter<BaseHolder> {
        private static final int TYPE_INCENTIVE         = 0;
        private static final int TYPE_MY_CDP            = 1;
        private static final int TYPE_OTHER_CDP         = 2;

        @NonNull
        @Override
        public BaseHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
            if (viewType == TYPE_INCENTIVE) {
                return new CdpIncentiveHolder(getLayoutInflater().inflate(R.layout.item_cdp_list_incentive, viewGroup, false));
            } else if (viewType == TYPE_MY_CDP) {
                return new CdpMyHolder(getLayoutInflater().inflate(R.layout.item_cdp_list_my, viewGroup, false));
            } else if (viewType == TYPE_OTHER_CDP) {
                return new CdpOtherHolder(getLayoutInflater().inflate(R.layout.item_cdp_list_all, viewGroup, false));
            }
            return null;
        }

        @Override
        public void onBindViewHolder(@NonNull BaseHolder viewHolder, int position) {
            if (getItemViewType(position) == TYPE_INCENTIVE) {
                viewHolder.onBindUsdxIncentive(getContext(), ListCdpFragment.this, mIncentiveRewards5);

            } else if (getItemViewType(position) == TYPE_MY_CDP) {
                final ResKavaMyCdps.MyCDP myCdp;
                if (mIncentiveRewards5 != null &&  mIncentiveRewards5.usdx_minting_claims != null && mIncentiveRewards5.usdx_minting_claims.size() > 0) {
                    myCdp = mMyCdps.get(position - 1);
                } else {
                    myCdp = mMyCdps.get(position);
                }
                viewHolder.onBindMyCdp(getContext(), getBaseDao(), myCdp);


            } else if (getItemViewType(position) == TYPE_OTHER_CDP) {
                final ResKavaCdpParam.KavaCollateralParam otherCdp;
                if (mIncentiveRewards5 != null &&  mIncentiveRewards5.usdx_minting_claims != null && mIncentiveRewards5.usdx_minting_claims.size() > 0) {
                    otherCdp = mOtherCdps.get(position  - mMyCdps.size() - 1);
                } else {
                    otherCdp = mOtherCdps.get(position - mMyCdps.size() );
                }
                viewHolder.onBindOtherCdp(getContext(), otherCdp);
            }

        }

        @Override
        public int getItemCount() {
            if (mIncentiveRewards5 != null &&  mIncentiveRewards5.usdx_minting_claims != null && mIncentiveRewards5.usdx_minting_claims.size() > 0) {
                return mMyCdps.size() + mOtherCdps.size() + 1;
            } else {
                return mMyCdps.size() + mOtherCdps.size();
            }
        }

        @Override
        public int getItemViewType(int position) {
            if (mIncentiveRewards5 != null &&  mIncentiveRewards5.usdx_minting_claims != null && mIncentiveRewards5.usdx_minting_claims.size() > 0) {
                if (position == 0) {
                    return TYPE_INCENTIVE;
                } else if (position < (mMyCdps.size() + 1)) {
                    return TYPE_MY_CDP;
                } else {
                    return TYPE_OTHER_CDP;
                }

            } else {
                if (position < mMyCdps.size()) {
                    return TYPE_MY_CDP;
                } else {
                    return TYPE_OTHER_CDP;
                }
            }
        }
    }

    private DAppsList5Activity getSActivity() {
        return (DAppsList5Activity)getBaseActivity();
    }
}
