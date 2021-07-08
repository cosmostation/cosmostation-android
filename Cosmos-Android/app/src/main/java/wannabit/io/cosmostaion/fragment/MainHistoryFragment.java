package wannabit.io.cosmostaion.fragment;

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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.MainActivity;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.model.type.BnbHistory;
import wannabit.io.cosmostaion.network.res.ResApiNewTxListCustom;
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
import wannabit.io.cosmostaion.widget.HistoryNewHolder;
import wannabit.io.cosmostaion.widget.HistoryOldHolder;

import static wannabit.io.cosmostaion.base.BaseChain.BNB_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.BNB_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.COSMOS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.IOV_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.IRIS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.OKEX_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.OK_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.OSMOSIS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.SECRET_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.isGRPC;


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
    private ArrayList<ResApiNewTxListCustom>        mApiNewTxCustomHistory = new ArrayList<>();

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
        mEmptyHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent txDetail = new Intent(getBaseActivity(), TxDetailgRPCActivity.class);
//                txDetail.putExtra("txHash", "CF91C15F336F06C7966A9DBC0CAE98A07C548FBFAC3CE787EF856A9018F35542");
//                txDetail.putExtra("isGen", false);
//                txDetail.putExtra("isSuccess", true);
//                startActivity(txDetail);
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
            case R.id.menu_explorer :
                getMainActivity().onExplorerView();
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
        if (isGRPC(getMainActivity().mBaseChain)) {
            new ApiAccountTxsHistoryTask(getBaseApplication(), this, getMainActivity().mAccount.address, getMainActivity().mBaseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        } else if (getMainActivity().mBaseChain.equals(BNB_MAIN) || getMainActivity().mBaseChain.equals(BNB_TEST)) {
            new BnbHistoryTask(getBaseApplication(), this, null, getMainActivity().mBaseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, getMainActivity().mAccount.address, WDp.threeMonthAgoTimeString(), WDp.cTimeString());

        } else if (getMainActivity().mBaseChain.equals(OKEX_MAIN) || getMainActivity().mBaseChain.equals(OK_TEST)) {
            new OkHistoryTask(getBaseApplication(), this, getMainActivity().mAccount.address, getMainActivity().mBaseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        } else if (getMainActivity().mBaseChain.equals(SECRET_MAIN)) {
            mEmptyHistory.setVisibility(View.GONE);
            mRecyclerView.setVisibility(View.GONE);
            mNotYet.setVisibility(View.VISIBLE);
            mNotYet.setText("Check with Explorer");

        }
        else  {
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
            if (isGRPC(getMainActivity().mBaseChain)) {
                if (getMainActivity().mBaseChain.equals(COSMOS_MAIN) || getMainActivity().mBaseChain.equals(IRIS_MAIN) || getMainActivity().mBaseChain.equals(OSMOSIS_MAIN) || getMainActivity().mBaseChain.equals(IOV_MAIN)) {
                    ArrayList<ResApiNewTxListCustom> hits = (ArrayList<ResApiNewTxListCustom>)result.resultData;
                    if (hits != null && hits.size() > 0) {
//                    WLog.w("Custom hit size " + hits.size());
                        mApiNewTxCustomHistory = hits;
                        mHistoryAdapter.notifyDataSetChanged();
                        mEmptyHistory.setVisibility(View.GONE);
                        mRecyclerView.setVisibility(View.VISIBLE);
                    } else {
                        mEmptyHistory.setVisibility(View.VISIBLE);
                        mRecyclerView.setVisibility(View.GONE);
                    }
                } else {
                    ArrayList<ResApiTxListCustom> hits = (ArrayList<ResApiTxListCustom>) result.resultData;
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
                }
            } else {
                ArrayList<ResApiTxList.Data> hits = (ArrayList<ResApiTxList.Data>)result.resultData;
                if (hits != null && hits.size() > 0) {
//                    WLog.w("hit size " + hits.size());
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

    public MainActivity getMainActivity() { return (MainActivity)getBaseActivity(); }

    private class HistoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private static final int TYPE_OLD_HISTORY           = 0;
        private static final int TYPE_NEW_HISTORY           = 1;

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
            if (viewType == TYPE_OLD_HISTORY) {
                return new HistoryOldHolder(getLayoutInflater().inflate(R.layout.item_history, viewGroup, false));
            } else {
                return new HistoryNewHolder(getLayoutInflater().inflate(R.layout.item_new_history, viewGroup, false));
            }
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
            if (getMainActivity().mBaseChain.equals(COSMOS_MAIN) || getMainActivity().mBaseChain.equals(IRIS_MAIN) ||
                    getMainActivity().mBaseChain.equals(OSMOSIS_MAIN) || getMainActivity().mBaseChain.equals(IOV_MAIN)) {
                HistoryNewHolder holder = (HistoryNewHolder) viewHolder;
                final ResApiNewTxListCustom history = mApiNewTxCustomHistory.get(position);
                holder.onBindNewHistory(getMainActivity(), history);

            } else {
                HistoryOldHolder holder = (HistoryOldHolder) viewHolder;
                if (isGRPC(getMainActivity().mBaseChain)) {
                    final ResApiTxListCustom history = mApiTxCustomHistory.get(position);
                    holder.onBindOldGrpcHistory(getMainActivity(), history);
                } else if (getMainActivity().mBaseChain.equals(BNB_MAIN) || getMainActivity().mBaseChain.equals(BNB_TEST)) {
                    final BnbHistory history = mBnbHistory.get(position);
                    holder.onBindOldBnbHistory(getMainActivity(), history);
                } else if (getMainActivity().mBaseChain.equals(OKEX_MAIN) || getMainActivity().mBaseChain.equals(OK_TEST)) {
                    final ResOkHistory.DataDetail history = mOkHistory.get(position);
                    holder.onBindOldOkHistory(getMainActivity(), history);
                } else {
                    final ResApiTxList.Data history = mApiTxHistory.get(position);
                    holder.onBindOldHistory(getMainActivity(), history);
                }
            }
        }

        @Override
        public int getItemViewType(int position) {
            if (getMainActivity().mBaseChain.equals(COSMOS_MAIN) || getMainActivity().mBaseChain.equals(IRIS_MAIN) ||
                    getMainActivity().mBaseChain.equals(OSMOSIS_MAIN) || getMainActivity().mBaseChain.equals(IOV_MAIN)) {
                return TYPE_NEW_HISTORY;
            } else {
                return TYPE_OLD_HISTORY;
            }
        }

        @Override
        public int getItemCount() {
            if (getMainActivity().mBaseChain.equals(BNB_MAIN) || getMainActivity().mBaseChain.equals(BNB_TEST)) {
                return mBnbHistory.size();
            } else if (getMainActivity().mBaseChain.equals(OKEX_MAIN) || getMainActivity().mBaseChain.equals(OK_TEST)) {
                return mOkHistory.size();
            } else if (getMainActivity().mBaseChain.equals(COSMOS_MAIN) || getMainActivity().mBaseChain.equals(IRIS_MAIN) ||
                    getMainActivity().mBaseChain.equals(OSMOSIS_MAIN) || getMainActivity().mBaseChain.equals(IOV_MAIN)) {
                return mApiNewTxCustomHistory.size();
            } else if (isGRPC(getMainActivity().mBaseChain)) {
                return mApiTxCustomHistory.size();
            } else {
                return mApiTxHistory.size();
            }
        }
    }
}

