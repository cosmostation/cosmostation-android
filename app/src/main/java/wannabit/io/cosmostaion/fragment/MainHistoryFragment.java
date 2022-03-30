package wannabit.io.cosmostaion.fragment;

import static wannabit.io.cosmostaion.base.BaseChain.BNB_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.COSMOS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.OKEX_MAIN;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.MainActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.model.type.BnbHistory;
import wannabit.io.cosmostaion.network.res.ResApiNewTxListCustom;
import wannabit.io.cosmostaion.network.res.ResOkHistory;
import wannabit.io.cosmostaion.task.FetchTask.ApiAccountTxsHistoryTask;
import wannabit.io.cosmostaion.task.FetchTask.BnbHistoryTask;
import wannabit.io.cosmostaion.task.FetchTask.OkHistoryTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.widget.HistoryNewHolder;
import wannabit.io.cosmostaion.widget.HistoryOldHolder;


public class MainHistoryFragment extends BaseFragment implements TaskListener {
    private CardView mCardView;
    private ImageView itemKeyStatus;
    private TextView mWalletAddress;
    private TextView mTotalValue;

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private LinearLayout mEmptyHistory;
    private HistoryAdapter mHistoryAdapter;
    private TextView mNotYet;

    private ArrayList<BnbHistory> mBnbHistory = new ArrayList<>();
    private ArrayList<ResOkHistory.Data.Hit> mOkHistory = new ArrayList<>();
    private ArrayList<ResApiNewTxListCustom> mApiNewTxCustomHistory = new ArrayList<>();

    private Account mAccount;
    private BaseChain mBaseChain;

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
        mCardView = rootView.findViewById(R.id.card_root);
        itemKeyStatus = rootView.findViewById(R.id.img_account);
        mWalletAddress = rootView.findViewById(R.id.wallet_address);
        mTotalValue = rootView.findViewById(R.id.total_value);
        mSwipeRefreshLayout = rootView.findViewById(R.id.layer_refresher);
        mRecyclerView = rootView.findViewById(R.id.recycler);
        mEmptyHistory = rootView.findViewById(R.id.empty_history);
        mNotYet = rootView.findViewById(R.id.text_notyet);
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

        mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getMainActivity().onAddressDialog();
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

        RecyclerViewHeader recyclerViewHeader = new RecyclerViewHeader(getMainActivity());
        mRecyclerView.addItemDecoration(recyclerViewHeader);

        onUpdateView();
        return rootView;
    }

    private void onUpdateView() {
        if (getMainActivity() == null || getMainActivity().mAccount == null) return;
        mAccount = getMainActivity().mAccount;
        mBaseChain = BaseChain.getChain(mAccount.baseChain);

        mCardView.setCardBackgroundColor(WDp.getChainBgColor(getMainActivity(), mBaseChain));
        if (mAccount.hasPrivateKey) {
            itemKeyStatus.setColorFilter(WDp.getChainColor(getMainActivity(), mBaseChain), android.graphics.PorterDuff.Mode.SRC_IN);
        } else {
            itemKeyStatus.setColorFilter(ContextCompat.getColor(getMainActivity(), R.color.colorGray0), android.graphics.PorterDuff.Mode.SRC_IN);
        }
        mWalletAddress.setText(mAccount.address);
        mTotalValue.setText(WDp.dpAllAssetValueUserCurrency(mBaseChain, getBaseDao()));
    }

    @Override
    public void onRefreshTab() {
        if (!isAdded()) return;
        onUpdateView();
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
        switch (item.getItemId()) {
            case R.id.menu_accounts:
                getMainActivity().onClickSwitchWallet();
                break;
            case R.id.menu_explorer:
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
        if (getMainActivity() == null || getMainActivity().mAccount == null) return;
        if (getMainActivity().mBaseChain.equals(BNB_MAIN)) {
            new BnbHistoryTask(getBaseApplication(), this, null, getMainActivity().mBaseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, getMainActivity().mAccount.address, WDp.threeMonthAgoTimeString(), WDp.cTimeString());

        } else if (getMainActivity().mBaseChain.equals(OKEX_MAIN)) {
            new OkHistoryTask(getBaseApplication(), this, getMainActivity().mAccount.address).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        } else {
            new ApiAccountTxsHistoryTask(getBaseApplication(), this, getMainActivity().mAccount.address, getMainActivity().mBaseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        }
    }

    @Override
    public void onTaskResponse(TaskResult result) {
        if (!isAdded()) return;
        if (result.taskType == BaseConstant.TASK_FETCH_BNB_HISTORY) {
            mBnbHistory = (ArrayList<BnbHistory>) result.resultData;
            if (mBnbHistory != null && mBnbHistory.size() > 0) {
                mEmptyHistory.setVisibility(View.GONE);
                mRecyclerView.setVisibility(View.VISIBLE);
                mHistoryAdapter.notifyDataSetChanged();
            } else {
                mEmptyHistory.setVisibility(View.VISIBLE);
                mRecyclerView.setVisibility(View.GONE);
            }

        } else if (result.taskType == BaseConstant.TASK_FETCH_OK_HISTORY) {
            mOkHistory = (ArrayList<ResOkHistory.Data.Hit>) result.resultData;
            if (result.isSuccess && mOkHistory != null && mOkHistory.size() > 0) {
                mEmptyHistory.setVisibility(View.GONE);
                mRecyclerView.setVisibility(View.VISIBLE);
                mHistoryAdapter.notifyDataSetChanged();
            } else {
                mEmptyHistory.setVisibility(View.VISIBLE);
                mRecyclerView.setVisibility(View.GONE);
            }

        } else if (result.taskType == BaseConstant.TASK_FETCH_API_ADDRESS_HISTORY) {
            mApiNewTxCustomHistory = (ArrayList<ResApiNewTxListCustom>) result.resultData;
            if (mApiNewTxCustomHistory != null && mApiNewTxCustomHistory.size() > 0) {
                mEmptyHistory.setVisibility(View.GONE);
                mRecyclerView.setVisibility(View.VISIBLE);
                mHistoryAdapter.notifyDataSetChanged();
            } else {
                mEmptyHistory.setVisibility(View.VISIBLE);
                mRecyclerView.setVisibility(View.GONE);
            }
        }
        mSwipeRefreshLayout.setRefreshing(false);
    }

    public MainActivity getMainActivity() {
        return (MainActivity) getBaseActivity();
    }

    private class HistoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private static final int TYPE_OLD_HISTORY = 0;
        private static final int TYPE_NEW_HISTORY = 1;

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
            if (getMainActivity().mBaseChain.isGRPC()) {
                HistoryNewHolder holder = (HistoryNewHolder) viewHolder;
                final ResApiNewTxListCustom history = mApiNewTxCustomHistory.get(position);
                holder.onBindNewHistory(getMainActivity(), getBaseDao(), history);

            } else {
                HistoryOldHolder holder = (HistoryOldHolder) viewHolder;
                if (getMainActivity().mBaseChain.equals(BNB_MAIN)) {
                    final BnbHistory history = mBnbHistory.get(position);
                    holder.onBindOldBnbHistory(getMainActivity(), history);
                } else if (getMainActivity().mBaseChain.equals(OKEX_MAIN)) {
                    final ResOkHistory.Data.Hit history = mOkHistory.get(position);
                    holder.onBindOldOkHistory(getMainActivity(), history);
                }
            }
        }

        @Override
        public int getItemViewType(int position) {
            if (getMainActivity().mBaseChain.isGRPC()) {
                return TYPE_NEW_HISTORY;
            } else {
                return TYPE_OLD_HISTORY;
            }
        }

        @Override
        public int getItemCount() {
            if (getMainActivity().mBaseChain.equals(BNB_MAIN)) {
                return mBnbHistory.size();
            } else if (getMainActivity().mBaseChain.equals(OKEX_MAIN)) {
                return mOkHistory.size();
            } else {
                return mApiNewTxCustomHistory.size();
            }
        }
    }

    public class RecyclerViewHeader extends RecyclerView.ItemDecoration {
        private final int topPadding;

        private View headerView;
        private TextView mItemCnt;

        public RecyclerViewHeader(Context context) {
            topPadding = dpToPx(context, 26);
        }

        // dp -> pixel 단위로 변경
        private int dpToPx(Context context, int dp) {
            return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
        }

        @Override
        public void onDrawOver(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
            super.onDrawOver(c, parent, state);

            if (headerView == null) {
                headerView = inflateHeaderView(parent);
                mItemCnt = (TextView) headerView.findViewById(R.id.recycler_cnt);
                mItemCnt.setText("" + state.getItemCount());
                fixLayoutSize(headerView, parent);
            }
            mItemCnt.setText("" + state.getItemCount());

            for (int i = 0; i < parent.getChildCount(); i++) {
                View child = parent.getChildAt(i);
                drawHeader(c, child, headerView);
            }
        }

        @Override
        public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);

            int position = parent.getChildAdapterPosition(view);
            if (position == 0) {
                outRect.top = topPadding;
            }
        }

        private View inflateHeaderView(RecyclerView parent) {
            return LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sticky_header, parent, false);
        }

        private void drawHeader(Canvas c, View child, View headerView) {
            c.save();
            headerView.draw(c);
            c.restore();
        }

        private void fixLayoutSize(View view, ViewGroup parent) {
            int widthSpec = View.MeasureSpec.makeMeasureSpec(parent.getWidth(),
                    View.MeasureSpec.EXACTLY);
            int heightSpec = View.MeasureSpec.makeMeasureSpec(parent.getHeight(),
                    View.MeasureSpec.UNSPECIFIED);

            int childWidth = ViewGroup.getChildMeasureSpec(widthSpec,
                    parent.getPaddingLeft() + parent.getPaddingRight(),
                    view.getLayoutParams().width);
            int childHeight = ViewGroup.getChildMeasureSpec(heightSpec,
                    parent.getPaddingTop() + parent.getPaddingBottom(),
                    view.getLayoutParams().height);

            view.measure(childWidth, childHeight);
            view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        }
    }
}

