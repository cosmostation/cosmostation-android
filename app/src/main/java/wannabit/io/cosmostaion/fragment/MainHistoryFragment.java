package wannabit.io.cosmostaion.fragment;

import static wannabit.io.cosmostaion.base.BaseChain.BNB_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.OKEX_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.isGRPC;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
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
import wannabit.io.cosmostaion.base.chains.ChainConfig;
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
    private TextView mWalletAddress, mEthAddress;
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
    private ChainConfig mChainConfig;

    public static MainHistoryFragment newInstance() {
        return new MainHistoryFragment();
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
        mEthAddress = rootView.findViewById(R.id.eth_address);
        mTotalValue = rootView.findViewById(R.id.total_value);
        mSwipeRefreshLayout = rootView.findViewById(R.id.layer_refresher);
        mRecyclerView = rootView.findViewById(R.id.recycler);
        mEmptyHistory = rootView.findViewById(R.id.empty_history);
        mNotYet = rootView.findViewById(R.id.text_notyet);

        mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getMainActivity().onClickQrCopy(mChainConfig, mAccount);
            }
        });

        mSwipeRefreshLayout.setColorSchemeColors(ContextCompat.getColor(getMainActivity(), R.color.colorPrimary));
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
        mChainConfig = getMainActivity().mChainConfig;

        mCardView.setCardBackgroundColor(ContextCompat.getColor(getActivity(), mChainConfig.chainBgColor()));
        getMainActivity().setAccountKeyStatus(getActivity(), mAccount, mChainConfig, itemKeyStatus);
        mWalletAddress.setText(mAccount.address);
        getMainActivity().setEthAddress(mChainConfig, mEthAddress);
        mTotalValue.setText(WDp.dpAllAssetValueUserCurrency(mBaseChain, getBaseDao(), mChainConfig));
    }

    @Override
    public void onRefreshTab() {
        if (!isAdded()) return;
        onUpdateView();
        onFetchHistory();
    }

    private void onFetchHistory() {
        mNotYet.setVisibility(View.GONE);
        if (getMainActivity() == null || getMainActivity().mAccount == null) return;
        if (mBaseChain.equals(BNB_MAIN)) {
            new BnbHistoryTask(getBaseApplication(), this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, mAccount.address, WDp.threeMonthAgoTimeString(), WDp.cTimeString());
        } else if (mBaseChain.equals(OKEX_MAIN)) {
            new OkHistoryTask(getBaseApplication(), this, mAccount.address).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        } else {
            new ApiAccountTxsHistoryTask(getBaseApplication(), this, mBaseChain, mAccount.address).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
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
            if (isGRPC(mBaseChain)) {
                HistoryNewHolder holder = (HistoryNewHolder) viewHolder;
                final ResApiNewTxListCustom history = mApiNewTxCustomHistory.get(position);
                holder.onBindNewHistory(getMainActivity(), getBaseDao(), mChainConfig, history);

            } else {
                HistoryOldHolder holder = (HistoryOldHolder) viewHolder;
                if (mBaseChain.equals(BNB_MAIN)) {
                    final BnbHistory history = mBnbHistory.get(position);
                    holder.onBindOldBnbHistory(getMainActivity(), mChainConfig, history);
                } else if (mBaseChain.equals(OKEX_MAIN)) {
                    final ResOkHistory.Data.Hit history = mOkHistory.get(position);
                    holder.onBindOldOkHistory(getMainActivity(), mChainConfig, history);
                }
            }
        }

        @Override
        public int getItemViewType(int position) {
            if (isGRPC(mBaseChain)) {
                return TYPE_NEW_HISTORY;
            } else {
                return TYPE_OLD_HISTORY;
            }
        }

        @Override
        public int getItemCount() {
            if (mBaseChain.equals(BNB_MAIN)) {
                return mBnbHistory.size();
            } else if (mBaseChain.equals(OKEX_MAIN)) {
                return mOkHistory.size();
            } else {
                return mApiNewTxCustomHistory.size();
            }
        }
    }

    public class RecyclerViewHeader extends RecyclerView.ItemDecoration {
        private final int topPadding;

        private View headerView;
        private TextView mTitle, mItemCnt;

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
                mTitle = (TextView) headerView.findViewById(R.id.header_title);
                mItemCnt = (TextView) headerView.findViewById(R.id.recycler_cnt);

                mTitle.setText(R.string.str_history_title);
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

