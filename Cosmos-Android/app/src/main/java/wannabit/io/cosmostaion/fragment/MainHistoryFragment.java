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
import wannabit.io.cosmostaion.activities.TxDetailActivity;
import wannabit.io.cosmostaion.activities.WebActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.model.type.BnbHistory;
import wannabit.io.cosmostaion.network.req.ReqTx;
import wannabit.io.cosmostaion.network.res.ResApiTxList;
import wannabit.io.cosmostaion.network.res.ResHistory;
import wannabit.io.cosmostaion.task.FetchTask.ApiAccountTxsHistoryTask;
import wannabit.io.cosmostaion.task.FetchTask.HistoryTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WLog;
import wannabit.io.cosmostaion.utils.WUtil;

import static wannabit.io.cosmostaion.base.BaseConstant.TX_TYPE_REINVEST;
import static wannabit.io.cosmostaion.base.BaseConstant.TX_TYPE_SEND;
import static wannabit.io.cosmostaion.base.BaseConstant.TX_TYPE_UNKNOWN;


public class MainHistoryFragment extends BaseFragment implements TaskListener {

    private SwipeRefreshLayout              mSwipeRefreshLayout;
    private RecyclerView                    mRecyclerView;
    private LinearLayout                    mEmptyHistory;
    private HistoryAdapter                  mHistoryAdapter;

    private ArrayList<ResHistory.InnerHits> mHistory = new ArrayList<>();
    private ArrayList<BnbHistory>           mBnbHistory = new ArrayList<>();
    private ArrayList<ResApiTxList.Data>    mApiTxHistory = new ArrayList<>();

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
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        if (getMainActivity().mBaseChain.equals(BaseChain.COSMOS_MAIN)) {
            if (getMainActivity().mAccount.pushAlarm) {
                getMainActivity().getMenuInflater().inflate(R.menu.main_menu_alaram_on, menu);
            } else {
                getMainActivity().getMenuInflater().inflate(R.menu.main_menu_alaram_off, menu);
            }
        } else {
            getMainActivity().getMenuInflater().inflate(R.menu.main_menu, menu);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.menu_accounts :
                getMainActivity().onShowTopAccountsView();
                break;
            case R.id.menu_notification_off:
                getMainActivity().onUpdateUserAlarm(getMainActivity().mAccount, true);
                break;
            case R.id.menu_notification_on:
                getMainActivity().onUpdateUserAlarm(getMainActivity().mAccount, false);
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

        } else if (getMainActivity().mBaseChain.equals(BaseChain.BNB_MAIN) || getMainActivity().mBaseChain.equals(BaseChain.BNB_TEST)) {
            new HistoryTask(getBaseApplication(), this, null, getMainActivity().mBaseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, getMainActivity().mAccount.address, WDp.threeMonthAgoTimeString(), WDp.cTimeString());

        } else if (getMainActivity().mBaseChain.equals(BaseChain.KAVA_TEST)) {
            new ApiAccountTxsHistoryTask(getBaseApplication(), this, getMainActivity().mAccount.address, getMainActivity().mBaseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        } else if (getMainActivity().mBaseChain.equals(BaseChain.IOV_MAIN)) {
            mApiTxHistory.clear();
            mEmptyHistory.setVisibility(View.VISIBLE);
            mRecyclerView.setVisibility(View.GONE);
        }
    }

    @Override
    public void onTaskResponse(TaskResult result) {
        if(!isAdded()) return;
        if (result.taskType == BaseConstant.TASK_FETCH_HISTORY) {
            ArrayList<ResHistory.InnerHits> hits = (ArrayList<ResHistory.InnerHits>)result.resultData;
            if (hits != null && hits.size() > 0) {
//                WLog.w("hit size " + hits.size());
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

        } else if (result.taskType == BaseConstant.TASK_FETCH_API_ADDRESS_HISTORY) {
            ArrayList<ResApiTxList.Data> hits = (ArrayList<ResApiTxList.Data>)result.resultData;
            if (hits != null && hits.size() > 0) {
                WLog.w("hit size " + hits.size());
                mApiTxHistory = hits;
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
                        int TxType = WDp.getHistoryDpType(source.tx.value.msg, getMainActivity().mAccount.address);
                        if (TxType > TX_TYPE_UNKNOWN && TxType <= TX_TYPE_REINVEST) {
                            Intent txDetail = new Intent(getBaseActivity(), TxDetailActivity.class);
                            txDetail.putExtra("txHash", source.hash);
                            txDetail.putExtra("isGen", false);
                            txDetail.putExtra("isSuccess", true);
                            startActivity(txDetail);
                        } else {
                            Intent webintent = new Intent(getBaseActivity(), WebActivity.class);
                            webintent.putExtra("txid", source.hash);
                            webintent.putExtra("chain", getMainActivity().mBaseChain.getChain());
                            startActivity(webintent);
                        }
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

            } else if (getMainActivity().mBaseChain.equals(BaseChain.BNB_MAIN) || getMainActivity().mBaseChain.equals(BaseChain.BNB_TEST)) {
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
                        int TxType = WDp.getHistoryDpType(source.tx.value.msg, getMainActivity().mAccount.address);
                        if (TxType > TX_TYPE_UNKNOWN && TxType <= TX_TYPE_REINVEST) {
                            Intent txDetail = new Intent(getBaseActivity(), TxDetailActivity.class);
                            txDetail.putExtra("txHash", source.hash);
                            txDetail.putExtra("isGen", false);
                            txDetail.putExtra("isSuccess", true);
                            startActivity(txDetail);
                        } else {
                            Intent webintent = new Intent(getBaseActivity(), WebActivity.class);
                            webintent.putExtra("txid", source.hash);
                            webintent.putExtra("chain", getMainActivity().mBaseChain.getChain());
                            startActivity(webintent);
                        }
                    }
                });

            } else if (getMainActivity().mBaseChain.equals(BaseChain.KAVA_TEST)) {
                final ResApiTxList.Data tx = mApiTxHistory.get(position);
                if (tx.isSuccess()) {
                    viewHolder.historySuccess.setVisibility(View.GONE);
                } else {
                    viewHolder.historySuccess.setVisibility(View.VISIBLE);
                }
                viewHolder.historyType.setText(WDp.DpTxType(getContext(), tx.messages, getMainActivity().mAccount.address));
                viewHolder.history_time.setText(WDp.getTimeTxformat(getContext(), tx.timestamp));
                viewHolder.history_time_gap.setText(WDp.getTimeTxGap(getContext(), tx.timestamp));
                viewHolder.history_block.setText("" + tx.height + " block");
                viewHolder.historyRoot.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent txDetail = new Intent(getBaseActivity(), TxDetailActivity.class);
                        txDetail.putExtra("txHash", tx.tx_hash);
                        txDetail.putExtra("isGen", false);
                        txDetail.putExtra("isSuccess", true);
                        startActivity(txDetail);
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
            } else if (getMainActivity().mBaseChain.equals(BaseChain.BNB_MAIN) ||
                    getMainActivity().mBaseChain.equals(BaseChain.BNB_TEST)) {
                return mBnbHistory.size();
            } else if (getMainActivity().mBaseChain.equals(BaseChain.KAVA_TEST)) {
                return mApiTxHistory.size();
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
