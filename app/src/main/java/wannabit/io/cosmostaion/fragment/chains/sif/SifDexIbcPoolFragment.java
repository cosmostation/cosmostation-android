package wannabit.io.cosmostaion.fragment.chains.sif;

import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_SIF_MY_PROVIDER;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;

import sifnode.clp.v1.Querier;
import sifnode.clp.v1.Types;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.chains.sif.SifDexListActivity;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.base.IRefreshTabListener;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.task.gRpcTask.SifDexMyProviderGrpcTask;
import wannabit.io.cosmostaion.widget.BaseHolder;
import wannabit.io.cosmostaion.widget.SifPoolMyHolder;
import wannabit.io.cosmostaion.widget.SifPoolOtherHolder;

public class SifDexIbcPoolFragment extends BaseFragment implements TaskListener, IRefreshTabListener {

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private IbcPoolListAdapter mAdapter;

    public ArrayList<Querier.LiquidityProviderRes> mMyIbcProviders = new ArrayList<>();
    public ArrayList<Types.Pool> mMyIbcPools = new ArrayList<>();
    public ArrayList<Types.Pool> mOtherIbcPools = new ArrayList<>();

    public static SifDexIbcPoolFragment newInstance(Bundle bundle) {
        SifDexIbcPoolFragment fragment = new SifDexIbcPoolFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_pool_list, container, false);
        mSwipeRefreshLayout = rootView.findViewById(R.id.layer_refresher);
        mRecyclerView = rootView.findViewById(R.id.recycler);

        mSwipeRefreshLayout.setColorSchemeColors(ContextCompat.getColor(rootView.getContext(), R.color.colorPrimary));
        mSwipeRefreshLayout.setOnRefreshListener(() -> getSActivity().onFetchPoolListInfo());

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getBaseActivity(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new IbcPoolListAdapter();
        mRecyclerView.setAdapter(mAdapter);

        return rootView;
    }

    @Override
    public void onRefreshTab() {
        if (getSActivity().mMyIbcAssets != null && getSActivity().mMyIbcAssets.size() > 0) {
            onFetchIbcListInfo();
        } else {
            mAdapter.notifyDataSetChanged();
        }
        mMyIbcPools = getSActivity().mMyIbcPools;
        mOtherIbcPools = getSActivity().mOtherIbcPools;
        mSwipeRefreshLayout.setRefreshing(false);
    }

    int mTaskCount;

    public void onFetchIbcListInfo() {
        mTaskCount = 1;
        for (String symbol : getSActivity().mMyIbcAssets) {
            new SifDexMyProviderGrpcTask(getBaseApplication(), this, getSActivity().baseChain, getSActivity().account, symbol).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        }
    }

    @Override
    public void onTaskResponse(TaskResult result) {
        mTaskCount--;
        if (result.taskType == TASK_GRPC_FETCH_SIF_MY_PROVIDER) {
            if (result.isSuccess && result.resultData != null) {
                mMyIbcProviders.add((Querier.LiquidityProviderRes) result.resultData);
            }
        }

        if (mTaskCount == 0) {
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                @Override
                public void run() {
                    mAdapter.notifyDataSetChanged();
                }
            }, 300);
        }
    }

    private class IbcPoolListAdapter extends RecyclerView.Adapter<BaseHolder> {
        private static final int TYPE_MY_POOL = 1;
        private static final int TYPE_OTHER_POOL = 2;

        @NonNull
        @Override
        public BaseHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
            if (viewType == TYPE_MY_POOL) {
                return new SifPoolMyHolder(getLayoutInflater().inflate(R.layout.item_sif_pool_list_my, viewGroup, false));
            } else if (viewType == TYPE_OTHER_POOL) {
                return new SifPoolOtherHolder(getLayoutInflater().inflate(R.layout.item_sif_pool_list_other, viewGroup, false));
            }
            return null;
        }

        @Override
        public void onBindViewHolder(@NonNull BaseHolder viewHolder, int position) {
            if (getItemViewType(position) == TYPE_MY_POOL) {
                final Types.Pool myPool = mMyIbcPools.get(position);
                sifnode.clp.v1.Querier.LiquidityProviderRes myProvider = null;
                for (Querier.LiquidityProviderRes provider : mMyIbcProviders) {
                    if (provider.getLiquidityProvider().getAsset().getSymbol().equalsIgnoreCase(myPool.getExternalAsset().getSymbol())) {
                        myProvider = provider;
                    }
                }
                viewHolder.onBindSifMyEthPool(getContext(), getSActivity(), getBaseDao(), myPool, myProvider);
            } else if (getItemViewType(position) == TYPE_OTHER_POOL) {
                final Types.Pool otherPool = mOtherIbcPools.get(position - mMyIbcPools.size());
                viewHolder.onBindSifOtherEthPool(getContext(), getSActivity(), getBaseDao(), otherPool);
            }
        }

        @Override
        public int getItemCount() {
            return mMyIbcPools.size() + mOtherIbcPools.size();
        }

        @Override
        public int getItemViewType(int position) {
            if (position < mMyIbcPools.size()) {
                return TYPE_MY_POOL;
            } else {
                return TYPE_OTHER_POOL;
            }
        }
    }

    private SifDexListActivity getSActivity() {
        return (SifDexListActivity) getBaseActivity();
    }
}