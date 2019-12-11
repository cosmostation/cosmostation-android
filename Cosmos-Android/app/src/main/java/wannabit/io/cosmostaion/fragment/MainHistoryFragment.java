package wannabit.io.cosmostaion.fragment;

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
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.MainActivity;
import wannabit.io.cosmostaion.activities.WebActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.model.type.BnbHistory;
import wannabit.io.cosmostaion.network.req.ReqTx;
import wannabit.io.cosmostaion.network.res.ResHistory;
import wannabit.io.cosmostaion.task.FetchTask.HistoryTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WLog;
import wannabit.io.cosmostaion.utils.WUtil;


public class MainHistoryFragment extends BaseFragment implements TaskListener {

    private SwipeRefreshLayout              mSwipeRefreshLayout;
    private RecyclerView                    mRecyclerView;
    private LinearLayout                    mEmptyHistory;
    private HistoryAdapter                  mHistoryAdapter;

    private ArrayList<ResHistory.InnerHits> mHistory = new ArrayList<>();
    private ArrayList<BnbHistory>           mBnbHistory = new ArrayList<>();

    public static MainHistoryFragment newInstance(Bundle bundle) {
        MainHistoryFragment fragment = new MainHistoryFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main_history, container, false);
        mSwipeRefreshLayout     = rootView.findViewById(R.id.layer_refresher);
        mRecyclerView           = rootView.findViewById(R.id.recycler);
        mEmptyHistory           = rootView.findViewById(R.id.empty_history);

        mSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                onFetchHistory();
            }
        });

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getBaseActivity(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);
        mHistoryAdapter = new HistoryAdapter();
        mRecyclerView.setAdapter(mHistoryAdapter);
        onFetchHistory();
        return rootView;
    }

    @Override
    public void onRefreshTab() {
        if(!isAdded()) return;
        onFetchHistory();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.main_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.menu_accounts :
                getMainActivity().onShowTopAccountsView();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void onFetchHistory() {
        if(getMainActivity() == null || getMainActivity().mAccount == null) return;
        if (getMainActivity().mBaseChain.equals(BaseChain.COSMOS_MAIN) || getMainActivity().mBaseChain.equals(BaseChain.KAVA_MAIN)) {
            ReqTx req = new ReqTx(0, 0, true, getMainActivity().mAccount.address, getMainActivity().mBaseChain);
//            WLog.w("onFetchHistory : " +  WUtil.prettyPrinter(req));
            new HistoryTask(getBaseApplication(), this, req, getMainActivity().mBaseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        } else if (getMainActivity().mBaseChain.equals(BaseChain.IRIS_MAIN)) {
            ReqTx req = new ReqTx(0, 1, true, getMainActivity().mAccount.address, getMainActivity().mBaseChain);
//            WLog.w("onFetchHistory : " +  WUtil.prettyPrinter(req));
            new HistoryTask(getBaseApplication(), this, req, getMainActivity().mBaseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        } else if (getMainActivity().mBaseChain.equals(BaseChain.BNB_MAIN)) {
            new HistoryTask(getBaseApplication(), this, null, getMainActivity().mBaseChain)
                    .executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, getMainActivity().mAccount.address, WDp.threeMonthAgoTimeString(), WDp.cTimeString());

        }
    }

    @Override
    public void onTaskResponse(TaskResult result) {
        if(!isAdded()) return;
        if (result.taskType == BaseConstant.TASK_FETCH_HISTORY) {
            ArrayList<ResHistory.InnerHits> hits = (ArrayList<ResHistory.InnerHits>)result.resultData;
            if (hits != null && hits.size() > 0) {
                WLog.w("hit size " + hits.size());
                mHistory = hits;
                mHistoryAdapter.notifyDataSetChanged();
                mEmptyHistory.setVisibility(View.GONE);
                mRecyclerView.setVisibility(View.VISIBLE);
            } else {
                mEmptyHistory.setVisibility(View.VISIBLE);
                mRecyclerView.setVisibility(View.GONE);
            }
        } else if (result.taskType == BaseConstant.TASK_FETCH_BNB_HISTORY) {
            ArrayList<BnbHistory> hits = (ArrayList<BnbHistory>)result.resultData;
            if (hits != null && hits.size() > 0) {
                WLog.w("hit size " + hits.size());
                mBnbHistory = hits;
                mHistoryAdapter.notifyDataSetChanged();
                mEmptyHistory.setVisibility(View.GONE);
                mRecyclerView.setVisibility(View.VISIBLE);

            } else {
                mEmptyHistory.setVisibility(View.VISIBLE);
                mRecyclerView.setVisibility(View.GONE);

            }
        }
        mSwipeRefreshLayout.setRefreshing(false);
    }


    private class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryHolder> {

        @NonNull
        @Override
        public HistoryHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new HistoryHolder(getLayoutInflater().inflate(R.layout.item_history, viewGroup, false));
        }

        @Override
        public void onBindViewHolder(@NonNull HistoryHolder viewHolder, int position) {
            if (getMainActivity().mBaseChain.equals(BaseChain.COSMOS_MAIN)) {
                final ResHistory.Source source = mHistory.get(position)._source;
                if(!source.isSuccess()) {
                    viewHolder.historySuccess.setVisibility(View.VISIBLE);
                } else {
                    viewHolder.historySuccess.setVisibility(View.GONE);
                }
                viewHolder.historyType.setText(WDp.DpTxType(getContext(), source.tx.value.msg, getMainActivity().mAccount.address));
                viewHolder.history_time.setText(WDp.getTimeformat(getContext(), source.timestamp));
                viewHolder.history_time_gap.setText(WDp.getTimeGap(getContext(), source.timestamp));
                viewHolder.history_block.setText(source.height + " block");
                viewHolder.historyRoot.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent webintent = new Intent(getBaseActivity(), WebActivity.class);
                        webintent.putExtra("txid", source.hash);
                        webintent.putExtra("chain", getMainActivity().mBaseChain.getChain());
                        startActivity(webintent);
                    }
                });

            } else if (getMainActivity().mBaseChain.equals(BaseChain.IRIS_MAIN)) {
                final ResHistory.Source source = mHistory.get(position)._source;
                if(source.result.Code > 0) {
                    viewHolder.historySuccess.setVisibility(View.VISIBLE);
                } else {
                    viewHolder.historySuccess.setVisibility(View.GONE);
                }
                viewHolder.historyType.setText(WDp.DpTxType(getContext(), source.tx.value.msg, getMainActivity().mAccount.address));
                viewHolder.history_time.setText(WDp.getTimeformat(getContext(), source.time));
                viewHolder.history_time_gap.setText(WDp.getTimeGap(getContext(), source.time));
                viewHolder.history_block.setText(source.height + " block");
                viewHolder.historyRoot.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent webintent = new Intent(getBaseActivity(), WebActivity.class);
                        webintent.putExtra("txid", source.hash);
                        webintent.putExtra("chain", getMainActivity().mBaseChain.getChain());
                        startActivity(webintent);
                    }
                });

            } else if (getMainActivity().mBaseChain.equals(BaseChain.BNB_MAIN)) {
                final BnbHistory history = mBnbHistory.get(position);
                viewHolder.historyType.setText(WDp.DpBNBTxType(getContext(), history, getMainActivity().mAccount.address));
                viewHolder.history_time.setText(WDp.getTimeformat(getContext(), history.timeStamp));
                viewHolder.history_time_gap.setText(WDp.getTimeGap(getContext(), history.timeStamp));
                viewHolder.history_block.setText(history.blockHeight + " block");
                viewHolder.historySuccess.setVisibility(View.GONE);
                viewHolder.historyRoot.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent webintent = new Intent(getBaseActivity(), WebActivity.class);
                        webintent.putExtra("txid", history.txHash);
                        webintent.putExtra("chain", getMainActivity().mBaseChain.getChain());
                        startActivity(webintent);
                    }
                });

            } else if (getMainActivity().mBaseChain.equals(BaseChain.KAVA_MAIN)) {
                final ResHistory.Source source = mHistory.get(position)._source;
                if (source.isSuccess()) {
                    viewHolder.historySuccess.setVisibility(View.GONE);
                } else {
                    viewHolder.historySuccess.setVisibility(View.VISIBLE);
                }
                viewHolder.historyType.setText(WDp.DpTxType(getContext(), source.tx.value.msg, getMainActivity().mAccount.address));
                viewHolder.history_time.setText(WDp.getTimeformat(getContext(), source.timestamp));
                viewHolder.history_time_gap.setText(WDp.getTimeGap(getContext(), source.timestamp));
                viewHolder.history_block.setText(source.height + " block");
                viewHolder.historyRoot.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent webintent = new Intent(getBaseActivity(), WebActivity.class);
                        webintent.putExtra("txid", source.hash);
                        webintent.putExtra("chain", getMainActivity().mBaseChain.getChain());
                        startActivity(webintent);
                    }
                });
            }
        }

        @Override
        public int getItemCount() {
            if (getMainActivity().mBaseChain.equals(BaseChain.COSMOS_MAIN) ||
                    getMainActivity().mBaseChain.equals(BaseChain.IRIS_MAIN) ||
                    getMainActivity().mBaseChain.equals(BaseChain.KAVA_MAIN)) {
                return mHistory.size();
            } else if (getMainActivity().mBaseChain.equals(BaseChain.BNB_MAIN)) {
                return mBnbHistory.size();
            }
            return 0;
        }

        public class HistoryHolder extends RecyclerView.ViewHolder {
            private CardView historyRoot;
            private TextView historyType, historySuccess, history_time, history_block, history_time_gap;

            public HistoryHolder(View v) {
                super(v);
                historyRoot         = itemView.findViewById(R.id.card_history);
                historyType         = itemView.findViewById(R.id.history_type);
                historySuccess      = itemView.findViewById(R.id.history_success);
                history_time        = itemView.findViewById(R.id.history_time);
                history_block       = itemView.findViewById(R.id.history_block_height);
                history_time_gap    = itemView.findViewById(R.id.history_time_gap);
            }
        }

    }

    public MainActivity getMainActivity() {
        return (MainActivity)getBaseActivity();
    }
}
