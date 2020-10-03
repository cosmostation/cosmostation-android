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
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.model.type.BnbHistory;
import wannabit.io.cosmostaion.network.res.ResApiTxList;
import wannabit.io.cosmostaion.task.FetchTask.ApiAccountTxsHistoryTask;
import wannabit.io.cosmostaion.task.FetchTask.HistoryTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WLog;

import static wannabit.io.cosmostaion.base.BaseChain.BAND_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.BNB_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.BNB_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.CERTIK_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.COSMOS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.IOV_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.IOV_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.IRIS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.KAVA_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.KAVA_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.OK_TEST;


public class MainHistoryFragment extends BaseFragment implements TaskListener {

    private SwipeRefreshLayout              mSwipeRefreshLayout;
    private RecyclerView                    mRecyclerView;
    private LinearLayout                    mEmptyHistory;
    private HistoryAdapter                  mHistoryAdapter;
    private TextView                        mNotYet;

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
        mNotYet                 = rootView.findViewById(R.id.text_notyet);
        mNotYet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent txDetail = new Intent(getBaseActivity(), TxDetailActivity.class);
                txDetail.putExtra("txHash", "4D86342BA6056BEB9F5E1DB585917DE0EFC0624D0058C9D5039A2E94CD539EE9");
                txDetail.putExtra("isGen", false);
                txDetail.putExtra("isSuccess", true);
                startActivity(txDetail);
            }
        });

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
        if (getMainActivity().mBaseChain.equals(COSMOS_MAIN)) {
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
        mNotYet.setVisibility(View.GONE);
        if(getMainActivity() == null || getMainActivity().mAccount == null) return;
        if (getMainActivity().mBaseChain.equals(COSMOS_MAIN)) {
            new ApiAccountTxsHistoryTask(getBaseApplication(), this, getMainActivity().mAccount.address, getMainActivity().mBaseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        } else if (getMainActivity().mBaseChain.equals(IRIS_MAIN)) {
            new ApiAccountTxsHistoryTask(getBaseApplication(), this, getMainActivity().mAccount.address, getMainActivity().mBaseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        } else if (getMainActivity().mBaseChain.equals(BNB_MAIN) || getMainActivity().mBaseChain.equals(BNB_TEST)) {
            new HistoryTask(getBaseApplication(), this, null, getMainActivity().mBaseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, getMainActivity().mAccount.address, WDp.threeMonthAgoTimeString(), WDp.cTimeString());

        } else if (getMainActivity().mBaseChain.equals(KAVA_TEST) || getMainActivity().mBaseChain.equals(KAVA_MAIN)) {
            new ApiAccountTxsHistoryTask(getBaseApplication(), this, getMainActivity().mAccount.address, getMainActivity().mBaseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        } else if (getMainActivity().mBaseChain.equals(IOV_MAIN)) {
            new ApiAccountTxsHistoryTask(getBaseApplication(), this, getMainActivity().mAccount.address, getMainActivity().mBaseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        } else if (getMainActivity().mBaseChain.equals(BAND_MAIN)) {
            new ApiAccountTxsHistoryTask(getBaseApplication(), this, getMainActivity().mAccount.address, getMainActivity().mBaseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        } else if (getMainActivity().mBaseChain.equals(OK_TEST) || getMainActivity().mBaseChain.equals(IOV_TEST)) {
            mEmptyHistory.setVisibility(View.GONE);
            mRecyclerView.setVisibility(View.GONE);
            mNotYet.setVisibility(View.VISIBLE);

        } else if (getMainActivity().mBaseChain.equals(CERTIK_TEST)) {
            new ApiAccountTxsHistoryTask(getBaseApplication(), this, getMainActivity().mAccount.address, getMainActivity().mBaseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        }
    }

    @Override
    public void onTaskResponse(TaskResult result) {
        if(!isAdded()) return;
        if (result.taskType == BaseConstant.TASK_FETCH_BNB_HISTORY) {
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
            if (getMainActivity().mBaseChain.equals(COSMOS_MAIN) || getMainActivity().mBaseChain.equals(KAVA_MAIN) || getMainActivity().mBaseChain.equals(KAVA_TEST) ||
                    getMainActivity().mBaseChain.equals(BAND_MAIN) || getMainActivity().mBaseChain.equals(IOV_MAIN) || getMainActivity().mBaseChain.equals(CERTIK_TEST) ) {
                final ResApiTxList.Data tx = mApiTxHistory.get(position);
                if (tx.logs != null) {
                    viewHolder.historySuccess.setVisibility(View.GONE);
                } else {
                    viewHolder.historySuccess.setVisibility(View.VISIBLE);
                }
                viewHolder.historyType.setText(WDp.DpTxType(getContext(), tx.messages, getMainActivity().mAccount.address));
                viewHolder.history_time.setText(WDp.getTimeTxformat(getContext(), tx.time));
                viewHolder.history_time_gap.setText(WDp.getTimeTxGap(getContext(), tx.time));
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

            } else if (getMainActivity().mBaseChain.equals(IRIS_MAIN)) {
                final ResApiTxList.Data tx = mApiTxHistory.get(position);
                if(tx.result.Code > 0) {
                    viewHolder.historySuccess.setVisibility(View.VISIBLE);
                } else {
                    viewHolder.historySuccess.setVisibility(View.GONE);
                }
                viewHolder.historyType.setText(WDp.DpTxType(getContext(), tx.messages, getMainActivity().mAccount.address));
                viewHolder.history_time.setText(WDp.getTimeTxformat(getContext(), tx.time));
                viewHolder.history_time_gap.setText(WDp.getTimeTxGap(getContext(), tx.time));
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

            } else if (getMainActivity().mBaseChain.equals(BNB_MAIN) || getMainActivity().mBaseChain.equals(BNB_TEST)) {
                final BnbHistory history = mBnbHistory.get(position);
                viewHolder.historyType.setText(WDp.DpBNBTxType(getContext(), history, getMainActivity().mAccount.address));
                viewHolder.history_time.setText(WDp.getTimeformat(getContext(), history.timeStamp));
                viewHolder.history_time_gap.setText(WDp.getTimeGap(getContext(), history.timeStamp));
                viewHolder.history_block.setText(history.blockHeight + " block");
                viewHolder.historySuccess.setVisibility(View.GONE);
                viewHolder.historyRoot.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        if (history.txType.equals("HTL_TRANSFER") || history.txType.equals("CLAIM_HTL") || history.txType.equals("REFUND_HTL") ||
//                                history.txType.equals("NEW_ORDER") || history.txType.equals("CANCEL_ORDER") || history.txType.equals("TRANSFER")) {
                        if (history.txType.equals("HTL_TRANSFER") || history.txType.equals("CLAIM_HTL") || history.txType.equals("REFUND_HTL") || history.txType.equals("TRANSFER")) {
                            Intent txDetail = new Intent(getBaseActivity(), TxDetailActivity.class);
                            txDetail.putExtra("txHash", history.txHash);
                            txDetail.putExtra("isGen", false);
                            txDetail.putExtra("isSuccess", true);
                            txDetail.putExtra("bnbTime", history.timeStamp);
                            startActivity(txDetail);

                        } else {
                            Intent webintent = new Intent(getBaseActivity(), WebActivity.class);
                            webintent.putExtra("txid", history.txHash);
                            webintent.putExtra("chain", getMainActivity().mBaseChain.getChain());
                            startActivity(webintent);
                        }
                    }
                });
            }
        }

        @Override
        public int getItemCount() {
            if (getMainActivity().mBaseChain.equals(COSMOS_MAIN) || getMainActivity().mBaseChain.equals(IRIS_MAIN)) {
                return mApiTxHistory.size();
            } else if (getMainActivity().mBaseChain.equals(BNB_MAIN) || getMainActivity().mBaseChain.equals(BNB_TEST)) {
                return mBnbHistory.size();
            } else if (getMainActivity().mBaseChain.equals(KAVA_MAIN) || getMainActivity().mBaseChain.equals(KAVA_TEST)) {
                return mApiTxHistory.size();
            } else if (getMainActivity().mBaseChain.equals(BAND_MAIN) || getMainActivity().mBaseChain.equals(IOV_MAIN) || getMainActivity().mBaseChain.equals(CERTIK_TEST)) {
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
