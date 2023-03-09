package wannabit.io.cosmostaion.fragment.txs.liquidstaking;

import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_ALL_USER_REDEMPTION;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Optional;

import stride.records.Genesis;
import stride.stakeibc.EpochTrackerOuterClass;
import stride.stakeibc.HostZoneOuterClass;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.txs.liquidstaking.StrideLSActivity;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.base.chains.ChainConfig;
import wannabit.io.cosmostaion.base.chains.ChainFactory;
import wannabit.io.cosmostaion.dialog.SelectChainListDialog;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.task.gRpcTask.AllUserRedemptionGrpcTask;
import wannabit.io.cosmostaion.utils.WDp;

public class StrideLUSFragment extends BaseFragment implements View.OnClickListener, TaskListener {

    private RelativeLayout mBtnInputCoinList;
    private ImageView mInputImg;
    private TextView mInputCoin, mInputAmount;
    private Button mBtnStartUnStake;
    private LinearLayout mEmptyLayer;
    private RecyclerView mRecyclerView;
    private LiquidUnStakeListAdapter mAdapter;

    private ArrayList<HostZoneOuterClass.HostZone> mHostZones = new ArrayList<>();
    private EpochTrackerOuterClass.EpochTracker mDayEpoch;
    private ArrayList<Genesis.UserRedemptionRecord> mRecords = new ArrayList<>();

    private String mInputCoinDenom;
    private int mSelectedPosition = 0;
    private BigDecimal mAvailableMaxAmount = BigDecimal.ZERO;

    public static StrideLUSFragment newInstance() {
        return new StrideLUSFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_stride_lus, container, false);
        mBtnInputCoinList = rootView.findViewById(R.id.btn_to_input_coin);
        mInputImg = rootView.findViewById(R.id.img_input_coin);
        mInputCoin = rootView.findViewById(R.id.txt_input_coin);
        mInputAmount = rootView.findViewById(R.id.input_amount);
        mBtnStartUnStake = rootView.findViewById(R.id.btn_start_unstake);
        mEmptyLayer = rootView.findViewById(R.id.empty_no_unstake);
        mRecyclerView = rootView.findViewById(R.id.recycler);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getBaseActivity(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new LiquidUnStakeListAdapter();
        mRecyclerView.setAdapter(mAdapter);
        RecyclerViewHeader recyclerViewHeader = new RecyclerViewHeader(getActivity());
        mRecyclerView.addItemDecoration(recyclerViewHeader);

        mBtnInputCoinList.setOnClickListener(this);
        mBtnStartUnStake.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onRefreshTab() {
        mHostZones = getSActivity().mHostZones;
        mDayEpoch = getSActivity().mDayEpoch;
        onUpdateView();
    }

    private void onUpdateView() {
        if (mHostZones == null || mDayEpoch == null) getSActivity().onBackPressed();

        mInputCoinDenom = "st" + mHostZones.get(mSelectedPosition).getHostDenom();
        int inputDecimal = WDp.getDenomDecimal(getBaseDao(), getSActivity().mChainConfig, mInputCoinDenom);

        WDp.setDpSymbolImg(getBaseDao(), getSActivity().mChainConfig, mInputCoinDenom, mInputImg);
        WDp.setDpSymbol(getActivity(), getBaseDao(), getSActivity().mChainConfig, mInputCoinDenom, mInputCoin);

        mAvailableMaxAmount = getBaseDao().getAvailable(mInputCoinDenom);
        mInputAmount.setText(WDp.getDpAmount2(getSActivity(), mAvailableMaxAmount, inputDecimal, inputDecimal));

        onFetchUserHistory();
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mBtnInputCoinList) && !getSActivity().isFinishing()) {
            Bundle bundleData = new Bundle();
            bundleData.putSerializable(SelectChainListDialog.SELECT_LIQUIDITY_STAKE_BUNDLE_KEY, mHostZones);
            bundleData.putInt(SelectChainListDialog.SELECT_CHAIN_LIST_BUNDLE_KEY, SelectChainListDialog.SELECT_LIQUIDITY_UNSTAKING_COIN_VALUE);
            SelectChainListDialog dialog = SelectChainListDialog.newInstance(bundleData);
            dialog.show(getParentFragmentManager(), SelectChainListDialog.class.getName());
            getParentFragmentManager().setFragmentResultListener(SelectChainListDialog.SELECT_CHAIN_LIST_BUNDLE_KEY, this, (requestKey, bundle) -> {
                mSelectedPosition = bundle.getInt(BaseConstant.POSITION);
                onUpdateView();
            });

        } else if (v.equals(mBtnStartUnStake)) {
            Optional<ChainConfig> configOptional = ChainFactory.SUPPRT_CONFIG().stream().filter(item -> item.mainDenom().equalsIgnoreCase(mHostZones.get(mSelectedPosition).getHostDenom())).findFirst();
            if (configOptional.isPresent()) {
                getSActivity().onClickUnStake(mHostZones.get(mSelectedPosition), mAvailableMaxAmount);
            } else {
                Toast.makeText(getContext(), getString(R.string.error_not_support_cosmostation), Toast.LENGTH_SHORT).show();
            }
        }
    }

    int mTaskCount;

    private void onFetchUserHistory() {
        mTaskCount = 1;
        mRecords.clear();
        new AllUserRedemptionGrpcTask(getBaseApplication(), this, getSActivity().mChainConfig, getSActivity().mAccount, mHostZones.get(mSelectedPosition).getChainId(), mDayEpoch.getEpochNumber()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    @Override
    public void onTaskResponse(TaskResult result) {
        mTaskCount--;
        if (result.taskType == TASK_GRPC_FETCH_ALL_USER_REDEMPTION) {
            if (result.isSuccess && result.resultData != null) {
                mRecords = (ArrayList<Genesis.UserRedemptionRecord>) result.resultData;
            }
        }

        if (mTaskCount == 0) {
            if (mRecords.size() > 0) {
                mEmptyLayer.setVisibility(View.GONE);
                mRecyclerView.setVisibility(View.VISIBLE);
                mAdapter.notifyDataSetChanged();
            } else {
                mEmptyLayer.setVisibility(View.VISIBLE);
            }
        }
    }

    private class LiquidUnStakeListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
            return new LiquidUnStakeHolder(getLayoutInflater().inflate(R.layout.item_unstake_record_list, viewGroup, false));
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
            onBindLiquidUnStakeItemViewHolder(viewHolder, position);
        }

        private void onBindLiquidUnStakeItemViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
            final LiquidUnStakeHolder holder = (LiquidUnStakeHolder) viewHolder;

            long gap = mDayEpoch.getEpochNumber() - mRecords.get(position).getEpochNumber();
            if (gap <= 0) {
                holder.mRequestDate.setText("Today");
            } else {
                holder.mRequestDate.setText(gap + " Days Ago");
            }

            Optional<ChainConfig> configOptional = ChainFactory.SUPPRT_CONFIG().stream().filter(item -> item.mainDenom().equalsIgnoreCase(mHostZones.get(mSelectedPosition).getHostDenom())).findFirst();
            if (configOptional.isPresent()) {
                WDp.setDpCoin(getActivity(), getBaseDao(), configOptional.get(), configOptional.get().mainDenom(), mRecords.get(position).getAmount(), holder.mLiquidUnstakeDenom, holder.mLiquidUnstakeAmount);
            } else {
                holder.mLiquidUnstakeDenom.setText("");
                holder.mLiquidUnstakeAmount.setText(WDp.getDpAmount2(new BigDecimal(mRecords.get(position).getAmount()), 6, 6));
            }
            holder.mLiquidUnstakeRecipient.setText(mRecords.get(position).getReceiver());
        }

        @Override
        public int getItemCount() {
            return mRecords.size();
        }

        public class LiquidUnStakeHolder extends RecyclerView.ViewHolder {
            TextView mRequestDate, mLiquidUnstakeAmount, mLiquidUnstakeDenom, mLiquidUnstakeRecipient;

            public LiquidUnStakeHolder(@NonNull View itemView) {
                super(itemView);
                mRequestDate = itemView.findViewById(R.id.request_date);
                mLiquidUnstakeAmount = itemView.findViewById(R.id.liquid_unstake_amount);
                mLiquidUnstakeDenom = itemView.findViewById(R.id.liquid_unstake_symbol);
                mLiquidUnstakeRecipient = itemView.findViewById(R.id.unstake_recipient);
            }
        }
    }

    public static class RecyclerViewHeader extends RecyclerView.ItemDecoration {
        private final int topPadding;

        private View headerView;
        private TextView mTitle, mItemCnt;

        public RecyclerViewHeader(Context context) {
            topPadding = dpToPx(context, 26);
        }

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

                mTitle.setText(R.string.str_unstaking_history);
                mItemCnt.setText(String.valueOf(state.getItemCount()));
                fixLayoutSize(headerView, parent);
            }
            mItemCnt.setText(String.valueOf(state.getItemCount()));

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
            int widthSpec = View.MeasureSpec.makeMeasureSpec(parent.getWidth(), View.MeasureSpec.EXACTLY);
            int heightSpec = View.MeasureSpec.makeMeasureSpec(parent.getHeight(), View.MeasureSpec.UNSPECIFIED);

            int childWidth = ViewGroup.getChildMeasureSpec(widthSpec, parent.getPaddingLeft() + parent.getPaddingRight(), view.getLayoutParams().width);
            int childHeight = ViewGroup.getChildMeasureSpec(heightSpec, parent.getPaddingTop() + parent.getPaddingBottom(), view.getLayoutParams().height);

            view.measure(childWidth, childHeight);
            view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        }
    }

    private StrideLSActivity getSActivity() {
        return (StrideLSActivity) getBaseActivity();
    }
}
