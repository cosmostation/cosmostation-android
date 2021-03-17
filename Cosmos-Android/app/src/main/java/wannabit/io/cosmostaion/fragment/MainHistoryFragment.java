package wannabit.io.cosmostaion.fragment;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.MainActivity;
import wannabit.io.cosmostaion.activities.TxDetailActivity;
import wannabit.io.cosmostaion.activities.TxDetailgRPCActivity;
import wannabit.io.cosmostaion.activities.WebActivity;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.model.type.BnbHistory;
import wannabit.io.cosmostaion.network.res.ResApiTxList;
import wannabit.io.cosmostaion.network.res.ResApiTxListCustom;
import wannabit.io.cosmostaion.network.res.ResOkHistory;
import wannabit.io.cosmostaion.task.FetchTask.ApiAccountTxsHistoryTask;
import wannabit.io.cosmostaion.task.FetchTask.BnbHistoryTask;
import wannabit.io.cosmostaion.task.FetchTask.OkHistoryTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WLog;

import static wannabit.io.cosmostaion.base.BaseChain.AKASH_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.BAND_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.BNB_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.BNB_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.CERTIK_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.CERTIK_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.COSMOS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.COSMOS_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.IOV_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.IOV_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.IRIS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.IRIS_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.KAVA_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.KAVA_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.OKEX_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.OK_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.SECRET_MAIN;
import static wannabit.io.cosmostaion.base.BaseConstant.PERSISTENCE_COSMOS_EVENT_ADDRESS;
import static wannabit.io.cosmostaion.base.BaseConstant.PERSISTENCE_COSMOS_EVENT_END;
import static wannabit.io.cosmostaion.base.BaseConstant.PERSISTENCE_COSMOS_EVENT_START;
import static wannabit.io.cosmostaion.base.BaseConstant.PERSISTENCE_KAVA_EVENT_ADDRESS;
import static wannabit.io.cosmostaion.base.BaseConstant.PERSISTENCE_KAVA_EVENT_END;
import static wannabit.io.cosmostaion.base.BaseConstant.PERSISTENCE_KAVA_EVENT_START;
import static wannabit.io.cosmostaion.base.BaseConstant.TX_TYPE_SEND;


public class MainHistoryFragment extends BaseFragment implements TaskListener {

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private LinearLayout                    mEmptyHistory;
    private HistoryAdapter                  mHistoryAdapter;
    private TextView                        mNotYet;

    private ArrayList<BnbHistory>                   mBnbHistory = new ArrayList<>();
    private ArrayList<ResOkHistory.DataDetail>      mOkHistory = new ArrayList<>();
    private ArrayList<ResApiTxList.Data>            mApiTxHistory = new ArrayList<>();
    private ArrayList<ResApiTxListCustom>           mApiTxCustomHistory = new ArrayList<>();

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
//                Intent txDetail = new Intent(getBaseActivity(), TxDetailActivity.class);
//                txDetail.putExtra("txHash", "13391795E8DFCDDBC5D27D293702A8BE49B04914410DECC75C612DF75E5246CB");
//                txDetail.putExtra("isGen", false);
//                txDetail.putExtra("isSuccess", true);
//                startActivity(txDetail);
//                if (getMainActivity().mBaseChain.equals(SECRET_MAIN)) {
//                    Intent webintent = new Intent(getMainActivity(), WebActivity.class);
//                    webintent.putExtra("address", getMainActivity().mAccount.address);
//                    webintent.putExtra("chain", getMainActivity().mBaseChain.getChain());
//                    webintent.putExtra("goMain", false);
//                    getMainActivity().startActivity(webintent);
//                }
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
        if (getMainActivity().mBaseChain.equals(BNB_MAIN) || getMainActivity().mBaseChain.equals(BNB_TEST)) {
            new BnbHistoryTask(getBaseApplication(), this, null, getMainActivity().mBaseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, getMainActivity().mAccount.address, WDp.threeMonthAgoTimeString(), WDp.cTimeString());

        } else if (getMainActivity().mBaseChain.equals(KAVA_TEST) || getMainActivity().mBaseChain.equals(KAVA_MAIN)) {
            new ApiAccountTxsHistoryTask(getBaseApplication(), this, getMainActivity().mAccount.address, getMainActivity().mBaseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        } else if (getMainActivity().mBaseChain.equals(IOV_MAIN)) {
            new ApiAccountTxsHistoryTask(getBaseApplication(), this, getMainActivity().mAccount.address, getMainActivity().mBaseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        } else if (getMainActivity().mBaseChain.equals(BAND_MAIN)) {
            new ApiAccountTxsHistoryTask(getBaseApplication(), this, getMainActivity().mAccount.address, getMainActivity().mBaseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        } else if (getMainActivity().mBaseChain.equals(IOV_TEST)) {
            mEmptyHistory.setVisibility(View.GONE);
            mRecyclerView.setVisibility(View.GONE);
            mNotYet.setVisibility(View.VISIBLE);
            mNotYet.setText("Coming Soon!!");

        } else if (getMainActivity().mBaseChain.equals(CERTIK_MAIN) || getMainActivity().mBaseChain.equals(CERTIK_TEST)) {
            new ApiAccountTxsHistoryTask(getBaseApplication(), this, getMainActivity().mAccount.address, getMainActivity().mBaseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        } else if (getMainActivity().mBaseChain.equals(SECRET_MAIN)) {
            mEmptyHistory.setVisibility(View.GONE);
            mRecyclerView.setVisibility(View.GONE);
            mNotYet.setVisibility(View.VISIBLE);
            mNotYet.setText("Check with Explorer");
        } else if (getMainActivity().mBaseChain.equals(OKEX_MAIN) || getMainActivity().mBaseChain.equals(OK_TEST)) {
            new OkHistoryTask(getBaseApplication(), this, getMainActivity().mAccount.address, getMainActivity().mBaseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        }

        else if (getMainActivity().mBaseChain.equals(COSMOS_MAIN) || getMainActivity().mBaseChain.equals(IRIS_MAIN) || getMainActivity().mBaseChain.equals(AKASH_MAIN) ||
                getMainActivity().mBaseChain.equals(COSMOS_TEST) ||getMainActivity().mBaseChain.equals(IRIS_TEST)) {
            new ApiAccountTxsHistoryTask(getBaseApplication(), this, getMainActivity().mAccount.address, getMainActivity().mBaseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        }
    }

    @Override
    public void onTaskResponse(TaskResult result) {
        if(!isAdded()) return;
        if (result.taskType == BaseConstant.TASK_FETCH_BNB_HISTORY) {
            ArrayList<BnbHistory> hits = (ArrayList<BnbHistory>)result.resultData;
            if (hits != null && hits.size() > 0) {
//                WLog.w("hit size " + hits.size());
                mBnbHistory = hits;
                mHistoryAdapter.notifyDataSetChanged();
                mEmptyHistory.setVisibility(View.GONE);
                mRecyclerView.setVisibility(View.VISIBLE);

            } else {
                mEmptyHistory.setVisibility(View.VISIBLE);
                mRecyclerView.setVisibility(View.GONE);

            }

        } else if (result.taskType == BaseConstant.TASK_FETCH_OK_HISTORY) {
            ResOkHistory hits = (ResOkHistory)result.resultData;
            if (hits != null && hits.data != null && hits.data.dataDetails != null && hits.data.dataDetails.size() > 0) {
//                WLog.w("hit size " + hits.data.dataDetails.size());
                mOkHistory = hits.data.dataDetails;
                mHistoryAdapter.notifyDataSetChanged();
                mEmptyHistory.setVisibility(View.GONE);
                mRecyclerView.setVisibility(View.VISIBLE);

            } else {
                mEmptyHistory.setVisibility(View.VISIBLE);
                mRecyclerView.setVisibility(View.GONE);
            }

        } else if (result.taskType == BaseConstant.TASK_FETCH_API_ADDRESS_HISTORY) {
            if (getMainActivity().mBaseChain.equals(COSMOS_MAIN) || getMainActivity().mBaseChain.equals(IRIS_MAIN) || getMainActivity().mBaseChain.equals(AKASH_MAIN) ||
                    getMainActivity().mBaseChain.equals(COSMOS_TEST) ||getMainActivity().mBaseChain.equals(IRIS_TEST)) {
                ArrayList<ResApiTxListCustom> hits = (ArrayList<ResApiTxListCustom>)result.resultData;
                if (hits != null && hits.size() > 0) {
//                    WLog.w("Custom hit size " + hits.size());
                    mApiTxCustomHistory = hits;
                    mHistoryAdapter.notifyDataSetChanged();
                    mEmptyHistory.setVisibility(View.GONE);
                    mRecyclerView.setVisibility(View.VISIBLE);

                } else {
                    mEmptyHistory.setVisibility(View.VISIBLE);
                    mRecyclerView.setVisibility(View.GONE);
                }

            } else {
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
            if (getMainActivity().mBaseChain.equals(COSMOS_MAIN) || getMainActivity().mBaseChain.equals(IRIS_MAIN) || getMainActivity().mBaseChain.equals(AKASH_MAIN) ||
                    getMainActivity().mBaseChain.equals(COSMOS_TEST) ||getMainActivity().mBaseChain.equals(IRIS_TEST)) {
                final ResApiTxListCustom history = mApiTxCustomHistory.get(position);
                viewHolder.historyType.setText(history.getMsgType(getContext(), getMainActivity().mAccount.address));
                viewHolder.history_time.setText(WDp.getTimeTxformat(getContext(), history.timestamp));
                viewHolder.history_time_gap.setText(WDp.getTimeTxGap(getContext(), history.timestamp));
                viewHolder.history_block.setText(history.height + " block");
                if (history.isSuccess()) {
                    viewHolder.historySuccess.setVisibility(View.GONE);
                } else {
                    viewHolder.historySuccess.setVisibility(View.VISIBLE);
                }
                viewHolder.historyRoot.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        Intent txDetail = new Intent(getBaseActivity(), TxDetailActivity.class);
//                        txDetail.putExtra("txHash", history.tx_hash);
//                        txDetail.putExtra("isGen", false);
//                        txDetail.putExtra("isSuccess", true);
//                        startActivity(txDetail);
                        Intent txDetail = new Intent(getBaseActivity(), TxDetailgRPCActivity.class);
                        txDetail.putExtra("txHash", history.tx_hash);
                        txDetail.putExtra("isGen", false);
                        txDetail.putExtra("isSuccess", true);
                        startActivity(txDetail);
                    }
                });

            } else {
                if (getMainActivity().mBaseChain.equals(BNB_MAIN) || getMainActivity().mBaseChain.equals(BNB_TEST)) {
                    final BnbHistory history = mBnbHistory.get(position);
                    viewHolder.historyType.setText(WDp.DpBNBTxType(getContext(), history, getMainActivity().mAccount.address));
                    viewHolder.history_time.setText(WDp.getTimeformat(getContext(), history.timeStamp));
                    viewHolder.history_time_gap.setText(WDp.getTimeGap(getContext(), history.timeStamp));
                    viewHolder.history_block.setText(history.blockHeight + " block");
                    viewHolder.historySuccess.setVisibility(View.GONE);
                    viewHolder.historyRoot.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
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

                } else if (getMainActivity().mBaseChain.equals(OKEX_MAIN) || getMainActivity().mBaseChain.equals(OK_TEST)) {
                    final ResOkHistory.DataDetail history = mOkHistory.get(position);
                    viewHolder.historySuccess.setVisibility(View.GONE);
                    viewHolder.historyType.setText(WDp.DpOkTxType(getContext(), history));
                    viewHolder.history_time.setText(WDp.getDpTime(getContext(), history.timestamp * 1000));
                    viewHolder.history_time_gap.setText(WDp.getTimeTxGap(getContext(), history.timestamp * 1000));
                    viewHolder.history_block.setText(history.txhash);
                    viewHolder.historyRoot.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent webintent = new Intent(getBaseActivity(), WebActivity.class);
                            webintent.putExtra("txid", history.txhash);
                            webintent.putExtra("chain", getMainActivity().mBaseChain.getChain());
                            startActivity(webintent);
                        }
                    });

                } else {
                    final ResApiTxList.Data tx = mApiTxHistory.get(position);
                    viewHolder.historyType.setTextColor(getResources().getColor(R.color.colorWhite));
                    viewHolder.historyRoot.setCardBackgroundColor(getResources().getColor(R.color.colorTransBg));
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

                    //TODO STAKE DROP EVENT
                    if (WDp.getHistoryDpType(tx.messages, getMainActivity().mAccount.address) == TX_TYPE_SEND) {
                        if (tx.height > PERSISTENCE_COSMOS_EVENT_START && tx.height < PERSISTENCE_COSMOS_EVENT_END) {
                            if (tx.messages.get(0).value.to_address.equals(PERSISTENCE_COSMOS_EVENT_ADDRESS) && tx.messages.get(0).value.from_address.equals(getMainActivity().mAccount.address)) {
                                viewHolder.historyType.setText("Persistence\nStake Drop");
                                viewHolder.historyType.setTextColor(getResources().getColor(R.color.colorStakeDrop));
                                viewHolder.historyRoot.setCardBackgroundColor(getResources().getColor(R.color.colorStakeDropBG));
                            }

                        } else if (tx.height > PERSISTENCE_KAVA_EVENT_START && tx.height < PERSISTENCE_KAVA_EVENT_END) {
                            if (tx.messages.get(0).value.to_address.equals(PERSISTENCE_KAVA_EVENT_ADDRESS) && tx.messages.get(0).value.from_address.equals(getMainActivity().mAccount.address)) {
                                viewHolder.historyType.setText("Persistence\nStake Drop");
                                viewHolder.historyType.setTextColor(getResources().getColor(R.color.colorStakeDrop));
                                viewHolder.historyRoot.setCardBackgroundColor(getResources().getColor(R.color.colorStakeDropBG));
                            }

                        }
                    }
                }
            }
        }

        @Override
        public int getItemCount() {
            if (getMainActivity().mBaseChain.equals(BNB_MAIN) || getMainActivity().mBaseChain.equals(BNB_TEST)) {
                return mBnbHistory.size();
            } else if (getMainActivity().mBaseChain.equals(OKEX_MAIN) || getMainActivity().mBaseChain.equals(OK_TEST)) {
                return mOkHistory.size();
            } else if (getMainActivity().mBaseChain.equals(COSMOS_MAIN) || getMainActivity().mBaseChain.equals(IRIS_MAIN) || getMainActivity().mBaseChain.equals(AKASH_MAIN) ||
                    getMainActivity().mBaseChain.equals(COSMOS_TEST) ||getMainActivity().mBaseChain.equals(IRIS_TEST)) {
                return mApiTxCustomHistory.size();
            } else {
                return mApiTxHistory.size();
            }
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
