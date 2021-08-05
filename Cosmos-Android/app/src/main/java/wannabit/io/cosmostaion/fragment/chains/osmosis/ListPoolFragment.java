package wannabit.io.cosmostaion.fragment.chains.osmosis;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.math.BigDecimal;
import java.util.ArrayList;

import osmosis.gamm.v1beta1.PoolOuterClass;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.chains.osmosis.ExitPoolActivity;
import wannabit.io.cosmostaion.activities.chains.osmosis.JoinPoolActivity;
import wannabit.io.cosmostaion.activities.chains.osmosis.LabsListActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.dialog.Dialog_WatchMode;
import wannabit.io.cosmostaion.utils.WLog;
import wannabit.io.cosmostaion.utils.WUtil;
import wannabit.io.cosmostaion.widget.BaseHolder;
import wannabit.io.cosmostaion.widget.PoolMyHolder;
import wannabit.io.cosmostaion.widget.PoolOtherHolder;

import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_OSMOSIS_EXIT_POOL;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_OSMOSIS_JOIN_POOL;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_OSMOSIS;


public class ListPoolFragment extends BaseFragment {
    private RecyclerView        mRecyclerView;

    private Account             mAccount;
    private BaseChain           mBaseChain;
    private PoolListAdapter     mAdapter;

    public ArrayList<PoolOuterClass.Pool>       mPoolMyList = new ArrayList<>();
    public ArrayList<PoolOuterClass.Pool>       mPoolOtherList = new ArrayList<>();

    private String      coin0Denom;
    private String      coin1Denom;


    public static ListPoolFragment newInstance(Bundle bundle) {
        ListPoolFragment fragment = new ListPoolFragment();
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
        mRecyclerView           = rootView.findViewById(R.id.recycler);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getBaseActivity(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new PoolListAdapter();
        mRecyclerView.setAdapter(mAdapter);

        mAccount = getSActivity().mAccount;
        mBaseChain = getSActivity().mBaseChain;

        return rootView;
    }

    @Override
    public void onRefreshTab() {
        mPoolMyList = getSActivity().getBaseDao().mPoolMyList;
        mPoolOtherList = getSActivity().getBaseDao().mPoolOtherList;
        mAdapter.notifyDataSetChanged();
    }

    public void onCheckStartJoinPool(PoolOuterClass.Pool pool) {
        if (!mAccount.hasPrivateKey) {
            Dialog_WatchMode add = Dialog_WatchMode.newInstance();
            add.setCancelable(true);
            getFragmentManager().beginTransaction().add(add, "dialog").commitNowAllowingStateLoss();
            return;
        }
        BigDecimal feeAmount = WUtil.getEstimateGasFeeAmount(getSActivity(), mBaseChain, CONST_PW_TX_OSMOSIS_JOIN_POOL, 0);
        coin0Denom = pool.getPoolAssets(0).getToken().getDenom();
        coin1Denom = pool.getPoolAssets(1).getToken().getDenom();
        BigDecimal coin0Available = getBaseDao().getAvailable(coin0Denom);
        BigDecimal coin1Available = getBaseDao().getAvailable(coin1Denom);

        if (coin0Denom.equalsIgnoreCase(TOKEN_OSMOSIS)) {
            coin0Available = coin0Available.subtract(feeAmount);
        }

        if (coin1Denom.equalsIgnoreCase(TOKEN_OSMOSIS)) {
            coin1Available = coin1Available.subtract(feeAmount);
        }

        if (coin0Available.compareTo(BigDecimal.ZERO) <= 0 || coin1Available.compareTo(BigDecimal.ZERO) <= 0) {
            Toast.makeText(getContext(), R.string.error_not_enough_to_pool, Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(getContext(), JoinPoolActivity.class);
        intent.putExtra("mType", CONST_PW_TX_OSMOSIS_JOIN_POOL);
        intent.putExtra("coin0Denom", coin0Denom);
        intent.putExtra("coin1Denom", coin1Denom);
        intent.putExtra("coin0Amount", pool.getPoolAssets(0).getToken().getAmount());
        intent.putExtra("coin1Amount", pool.getPoolAssets(1).getToken().getAmount());
        startActivity(intent);
    }

    public void onCheckStartExitPool(PoolOuterClass.Pool pool) {
        if (!mAccount.hasPrivateKey) {
            Dialog_WatchMode add = Dialog_WatchMode.newInstance();
            add.setCancelable(true);
            getFragmentManager().beginTransaction().add(add, "dialog").commitNowAllowingStateLoss();
            return;
        }
        BigDecimal mainBalance = getSActivity().getBaseDao().getAvailable(TOKEN_OSMOSIS);
        BigDecimal feeAmount = WUtil.getEstimateGasFeeAmount(getSActivity(), mBaseChain, CONST_PW_TX_OSMOSIS_EXIT_POOL, 0);

        if (mainBalance.compareTo(feeAmount) < 0) {
            Toast.makeText(getContext(), R.string.error_not_enough_to_pool, Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(getContext(), ExitPoolActivity.class);
        intent.putExtra("mType", CONST_PW_TX_OSMOSIS_EXIT_POOL);
        startActivity(intent);
    }

    private class PoolListAdapter extends RecyclerView.Adapter<BaseHolder> {
        private static final int TYPE_MY_POOL            = 1;
        private static final int TYPE_OTHER_POOL         = 2;

        @NonNull
        @Override
        public BaseHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
            if (viewType == TYPE_MY_POOL) {
                return new PoolMyHolder(getLayoutInflater().inflate(R.layout.item_pool_list_my, viewGroup, false));
            } else if (viewType == TYPE_OTHER_POOL) {
                return new PoolOtherHolder(getLayoutInflater().inflate(R.layout.item_pool_list_all, viewGroup, false));
            }
            return null;
        }

        @Override
        public void onBindViewHolder(@NonNull BaseHolder viewHolder, int position) {
            if (getItemViewType(position) == TYPE_MY_POOL) {
                final PoolOuterClass.Pool myPool = mPoolMyList.get(position);
                viewHolder.onBindMyPool(getContext(), ListPoolFragment.this, getBaseDao(), myPool);
            }
            else if (getItemViewType(position) == TYPE_OTHER_POOL) {
                final PoolOuterClass.Pool otherPool = mPoolOtherList.get(position - mPoolMyList.size());
                viewHolder.onBindOtherPool(getContext(), ListPoolFragment.this, getBaseDao(), otherPool);
            }
        }

        @Override
        public int getItemCount() {
            return mPoolMyList.size() + mPoolOtherList.size();
        }

        @Override
        public int getItemViewType(int position) {
            if (position < mPoolMyList.size()) {
                return TYPE_MY_POOL;
            } else {
                return TYPE_OTHER_POOL;
            }
        }
    }

    private LabsListActivity getSActivity() { return (LabsListActivity)getBaseActivity(); }
}
