package wannabit.io.cosmostaion.fragment.chains.kava;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;

import kava.cdp.v1beta1.Genesis;
import kava.cdp.v1beta1.QueryOuterClass;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.chains.kava.DAppsList5Activity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.model.kava.IncentiveReward;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.task.gRpcTask.KavaMyCdpsGrpcTask;
import wannabit.io.cosmostaion.widget.BaseHolder;
import wannabit.io.cosmostaion.widget.CdpMyHolder;
import wannabit.io.cosmostaion.widget.CdpOtherHolder;

import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_KAVA_MY_CDPS;

public class ListCdpFragment extends BaseFragment implements TaskListener {
    private SwipeRefreshLayout  mSwipeRefreshLayout;
    private RecyclerView        mRecyclerView;
    private RelativeLayout      mProgress;

    private Account             mAccount;
    private BaseChain           mBaseChain;
    private CdpMarketAdapter    mAdapter;

    private Genesis.Params                                  mCdpParams;
    private ArrayList<QueryOuterClass.CDPResponse>          mMyCdps = new ArrayList<>();
    private ArrayList<Genesis.CollateralParam>              mOtherCdps = new ArrayList<>();
    private IncentiveReward                                 mIncentiveRewards;
//    private CdpParam                                        mCdpParams;
//    private ArrayList<MyCdp>                                mMyCdps = new ArrayList<>();
//    private ArrayList<CollateralParam>                      mOtherCdps = new ArrayList<>();



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
        mCdpParams = getBaseDao().mCdpParams;
        mIncentiveRewards = getBaseDao().mIncentiveRewards;
        onFetchCdpInfo();
    }

    private int mTaskCount = 0;
    public void onFetchCdpInfo() {
        mMyCdps.clear();
        mOtherCdps.clear();
        mTaskCount = 1;
        new KavaMyCdpsGrpcTask(getBaseApplication(), this, mBaseChain, mAccount).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    @Override
    public void onTaskResponse(TaskResult result) {
        if(!isAdded()) return;
        mTaskCount--;
        if (result.taskType == TASK_GRPC_FETCH_KAVA_MY_CDPS) {
            if (result.isSuccess && result.resultData != null) {
                mMyCdps = (ArrayList<QueryOuterClass.CDPResponse>) result.resultData;
            }
        }

        if (mTaskCount == 0) {
            for (Genesis.CollateralParam cdpParam: mCdpParams.getCollateralParamsList()) {
                boolean has = false;
                for (QueryOuterClass.CDPResponse cdpMy: mMyCdps) {
                    if (cdpMy.getType().equalsIgnoreCase(cdpParam.getType())) {
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

    private class CdpMarketAdapter extends RecyclerView.Adapter<BaseHolder> {
        private static final int TYPE_MY_CDP            = 1;
        private static final int TYPE_OTHER_CDP         = 2;

        @NonNull
        @Override
        public BaseHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
            if (viewType == TYPE_MY_CDP) {
                return new CdpMyHolder(getLayoutInflater().inflate(R.layout.item_cdp_list_my, viewGroup, false));
            } else if (viewType == TYPE_OTHER_CDP) {
                return new CdpOtherHolder(getLayoutInflater().inflate(R.layout.item_cdp_list_all, viewGroup, false));
            }
            return null;
        }

        @Override
        public void onBindViewHolder(@NonNull BaseHolder viewHolder, int position) {
            if (getItemViewType(position) == TYPE_MY_CDP) {
                final QueryOuterClass.CDPResponse myCdp = mMyCdps.get(position);
                viewHolder.onBindMyCdp(getContext(), getBaseDao(), myCdp);


            } else if (getItemViewType(position) == TYPE_OTHER_CDP) {
                final Genesis.CollateralParam otherCdp = mOtherCdps.get(position - mMyCdps.size());
                viewHolder.onBindOtherCdp(getContext(), otherCdp);
            }
        }

        @Override
        public int getItemCount() {
            return mMyCdps.size() + mOtherCdps.size();
        }

        @Override
        public int getItemViewType(int position) {
            if (position < mMyCdps.size()) {
                return TYPE_MY_CDP;
            } else {
                return TYPE_OTHER_CDP;
            }
        }
    }

    private DAppsList5Activity getSActivity() {
        return (DAppsList5Activity)getBaseActivity();
    }
}
