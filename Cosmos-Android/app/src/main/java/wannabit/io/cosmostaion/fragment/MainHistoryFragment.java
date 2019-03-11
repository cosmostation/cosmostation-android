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
import android.widget.TextView;

import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.MainActivity;
import wannabit.io.cosmostaion.activities.RestoreActivity;
import wannabit.io.cosmostaion.activities.WebActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.network.req.ReqTx;
import wannabit.io.cosmostaion.network.res.ResHistory;
import wannabit.io.cosmostaion.task.FetchTask.HistoryTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WLog;


public class MainHistoryFragment extends BaseFragment implements TaskListener {

    private SwipeRefreshLayout              mSwipeRefreshLayout;
    private RecyclerView                    mRecyclerView;
    private TextView                        mEmptyHistory;
    private HistoryAdapter                  mHistoryAdapter;

    private ArrayList<ResHistory.InnerHits> mHistory = new ArrayList<>();

    public static MainHistoryFragment newInstance(Bundle bundle) {
        MainHistoryFragment fragment = new MainHistoryFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setHasOptionsMenu(true);
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
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy >0) {
                    if (getMainActivity().mFloatBtn.isShown()) {
                        getMainActivity().mFloatBtn.hide();
                    }
                }
                else if (dy <0) {
                    if (!getMainActivity().mFloatBtn.isShown()) {
                        getMainActivity().mFloatBtn.show();
                    }
                }
            }
        });
        onFetchHistory();
        return rootView;
    }

    @Override
    public void onRefreshTab() {
        if(!isAdded()) return;
        onFetchHistory();
    }

    //    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        inflater.inflate(R.menu.history_menu, menu);
//        super.onCreateOptionsMenu(menu, inflater);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.menu_sorting:
//                WLog.w("menu_sorting");
//                startActivity(new Intent(getMainActivity(), RestoreActivity.class));
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }

    private void onFetchHistory() {
        if(getMainActivity() == null || getMainActivity().mAccount == null) return;
//        WLog.w("onFetchHistory");
        ReqTx req = new ReqTx(0, 0, true, getMainActivity().mAccount.address);
//        String jsonText = new Gson().toJson(req);
//        WLog.w("jsonText : " + jsonText);
        new HistoryTask(getBaseApplication(), this, req, BaseChain.getChain(getMainActivity().mAccount.baseChain)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    @Override
    public void onTaskResponse(TaskResult result) {
        if(!isAdded()) return;
        if (result.taskType == BaseConstant.TASK_FETCH_HISTORY) {
            ArrayList<ResHistory.InnerHits> hits = (ArrayList<ResHistory.InnerHits>)result.resultData;
            if(hits != null && hits.size() > 0) {
                WLog.w("hit size " + hits.size());
                mHistory = hits;
                mHistoryAdapter.notifyDataSetChanged();
                mEmptyHistory.setVisibility(View.GONE);
                mRecyclerView.setVisibility(View.VISIBLE);
            } else {
                WLog.w("hit null");
                mEmptyHistory.setVisibility(View.VISIBLE);
                mRecyclerView.setVisibility(View.GONE);
            }
            mSwipeRefreshLayout.setRefreshing(false);
        }
    }


    private class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryHolder> {


        @NonNull
        @Override
        public HistoryHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new HistoryHolder(getLayoutInflater().inflate(R.layout.item_history, viewGroup, false));
        }

        @Override
        public void onBindViewHolder(@NonNull HistoryHolder viewHolder, int position) {
            final ResHistory.Source source = mHistory.get(position)._source;
            int dpType = WDp.getHistoryDpType(source.tx.value.msg, getMainActivity().mAccount.address);
            switch (dpType) {
                case BaseConstant.TX_TYPE_SEND:
                    viewHolder.historyType.setText(getString(R.string.tx_send));
                    viewHolder.historyType.setTextColor(getResources().getColor(R.color.colorPhoton));
                    break;

                case BaseConstant.TX_TYPE_RECEIVE:
                    viewHolder.historyType.setText(getString(R.string.tx_receive));
                    viewHolder.historyType.setTextColor(getResources().getColor(R.color.colorAtom));
                    break;

                case BaseConstant.TX_TYPE_TRANSFER:
                    viewHolder.historyType.setText(getString(R.string.tx_transfer));
                    viewHolder.historyType.setTextColor(getResources().getColor(R.color.colorWhite));
                    break;

                case BaseConstant.TX_TYPE_DELEGATE:
                    viewHolder.historyType.setText(getString(R.string.tx_delegate));
                    viewHolder.historyType.setTextColor(getResources().getColor(R.color.colorWhite));
                    break;

                case BaseConstant.TX_TYPE_UNDELEGATE:
                    viewHolder.historyType.setText(getString(R.string.tx_undelegate));
                    viewHolder.historyType.setTextColor(getResources().getColor(R.color.colorWhite));
                    break;

                case BaseConstant.TX_TYPE_REDELEGATE:
                    viewHolder.historyType.setText(getString(R.string.tx_redelegate));
                    viewHolder.historyType.setTextColor(getResources().getColor(R.color.colorWhite));
                    break;

                case BaseConstant.TX_TYPE_GET_REWARD:
                    viewHolder.historyType.setText(getString(R.string.tx_get_reward));
                    viewHolder.historyType.setTextColor(getResources().getColor(R.color.colorWhite));
                    break;

                case BaseConstant.TX_TYPE_GET_CPMMISSION:
                    viewHolder.historyType.setText(getString(R.string.tx_get_commission));
                    viewHolder.historyType.setTextColor(getResources().getColor(R.color.colorWhite));
                    break;

                case BaseConstant.TX_TYPE_CHAGE_REWARD_ADDRESS:
                    viewHolder.historyType.setText(getString(R.string.tx_change_reward_address));
                    viewHolder.historyType.setTextColor(getResources().getColor(R.color.colorWhite));
                    break;

                case BaseConstant.TX_TYPE_UNKNOWN:
                    viewHolder.historyType.setText(getString(R.string.tx_known));
                    viewHolder.historyType.setTextColor(getResources().getColor(R.color.colorWhite));
                    break;

            }

            if(!source.result.isSuccess()) {
                viewHolder.historySuccess.setVisibility(View.VISIBLE);
            } else {
                viewHolder.historySuccess.setVisibility(View.GONE);
            }

            viewHolder.history_time.setText(WDp.getTimeformat(getContext(), source.time));
            viewHolder.history_block.setText(source.height + " block");
            viewHolder.history_hash.setText(source.hash);
            viewHolder.historyRoot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    WLog.w("GO detail!!");
                    Intent webintent = new Intent(getBaseActivity(), WebActivity.class);
                    webintent.putExtra("txid", source.hash);
                    startActivity(webintent);
                }
            });

        }

        @Override
        public int getItemCount() {
            return mHistory.size();
        }

        public class HistoryHolder extends RecyclerView.ViewHolder {
            private CardView historyRoot;
            private TextView historyType, historySuccess, history_time, history_block, history_hash;

            public HistoryHolder(View v) {
                super(v);
                historyRoot         = itemView.findViewById(R.id.card_history);
                historyType         = itemView.findViewById(R.id.history_type);
                historySuccess      = itemView.findViewById(R.id.history_success);
                history_time        = itemView.findViewById(R.id.history_time);
                history_block       = itemView.findViewById(R.id.history_block_height);
                history_hash        = itemView.findViewById(R.id.history_hash);
            }
        }

    }


    public MainActivity getMainActivity() {
        return (MainActivity)getBaseActivity();
    }
}




/*
public class MainHistoryFragment extends BaseFragment {

    public static MainHistoryFragment newInstance(Bundle bundle) {
        MainHistoryFragment fragment = new MainHistoryFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main_history, container, false);
        return rootView;
    }
}
*/
