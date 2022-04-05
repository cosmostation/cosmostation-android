package wannabit.io.cosmostaion.activities.chains.osmosis;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fulldive.wallet.presentation.chains.osmo.OsmoLockupDurationDialogFragment;
import com.fulldive.wallet.presentation.chains.osmo.OsmoUnbondingAllDialogFragment;
import com.fulldive.wallet.presentation.chains.osmo.OsmoUnlockAll;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;

import osmosis.gamm.poolmodels.balancer.BalancerPool;
import osmosis.incentives.GaugeOuterClass;
import osmosis.lockup.Lock;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.dialog.Dialog_WatchMode;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.utils.OsmosisGaugeWrapper;
import wannabit.io.cosmostaion.utils.OsmosisPeriodLockWrapper;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WLog;
import wannabit.io.cosmostaion.utils.WUtil;
import wannabit.io.cosmostaion.widget.osmosis.EarningBondedHolder;
import wannabit.io.cosmostaion.widget.osmosis.EarningUnbondedHolder;
import wannabit.io.cosmostaion.widget.osmosis.EarningUnbondingHolder;

public class EarningDetailActivity extends BaseActivity implements View.OnClickListener {
    private static final int TYPE_BONDED = 1;
    private static final int TYPE_UNBONDING = 2;
    private static final int TYPE_UNBONDED = 3;

    private int mSection;          // section 구분

    private Toolbar mToolbar;
    private RecyclerView mRecyclerView;
    private Button mBtnNewEarning;
    private TextView mPoolIdTv;
    private TextView mPoolCoinPairTv;
    private TextView mPoolAprsTv1, mPoolAprsTv7, mPoolAprsTv14;
    private TextView mAvailableAmountTv, mAvailableDenomTv, mAvailableValueTv;

    private EarningDetailsAdapter mAdapter;
    private RecyclerViewHeader mRecyclerViewHeader;

    private BalancerPool.Pool mPool;
    private ArrayList<GaugeOuterClass.Gauge> mGauges;
    public ArrayList<Lock.PeriodLock> mLockUps = new ArrayList<>();
    public ArrayList<Lock.PeriodLock> mBondedList = new ArrayList<>();
    public ArrayList<Lock.PeriodLock> mUnbondingList = new ArrayList<>();
    public ArrayList<Lock.PeriodLock> mUnbondedList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_earning_detail);
        mToolbar = findViewById(R.id.toolbar);
        mRecyclerView = findViewById(R.id.recycler);
        mBtnNewEarning = findViewById(R.id.btn_start_earning);
        mPoolIdTv = findViewById(R.id.pool_id);
        mPoolCoinPairTv = findViewById(R.id.coin_pair);
        mPoolAprsTv1 = findViewById(R.id.aprs1);
        mPoolAprsTv7 = findViewById(R.id.aprs7);
        mPoolAprsTv14 = findViewById(R.id.aprs14);
        mAvailableAmountTv = findViewById(R.id.available_amount);
        mAvailableDenomTv = findViewById(R.id.available_denom);
        mAvailableValueTv = findViewById(R.id.available_value);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        account = getBaseDao().onSelectAccount(getBaseDao().getLastUser());
        baseChain = BaseChain.getChain(account.baseChain);

        try {
            mPool = BalancerPool.Pool.parseFrom(getIntent().getByteArrayExtra("osmosisPool"));
            OsmosisGaugeWrapper gaugeWrapper = (OsmosisGaugeWrapper) getIntent().getSerializableExtra("osmosisGauges");
            if (gaugeWrapper != null) {
                mGauges = gaugeWrapper.array;
            }
            OsmosisPeriodLockWrapper lockupWrapper = (OsmosisPeriodLockWrapper) getIntent().getSerializableExtra("osmosislockups");
            if (lockupWrapper != null) {
                mLockUps = lockupWrapper.array;
            }

        } catch (Exception e) {
            WLog.w("Passing bundle Error");
        }

        //display top card data
        Coin coin0 = new Coin(mPool.getPoolAssets(0).getToken().getDenom(), mPool.getPoolAssets(0).getToken().getAmount());
        Coin coin1 = new Coin(mPool.getPoolAssets(1).getToken().getDenom(), mPool.getPoolAssets(1).getToken().getAmount());
        BigDecimal lpCoinPrice = WUtil.getOsmoLpTokenPerUsdPrice(getBaseDao(), mPool);
        BigDecimal apr1 = WUtil.getPoolArp(getBaseDao(), mPool, mGauges, 0);
        BigDecimal apr7 = WUtil.getPoolArp(getBaseDao(), mPool, mGauges, 1);
        BigDecimal apr14 = WUtil.getPoolArp(getBaseDao(), mPool, mGauges, 2);

        if (mLockUps.size() > 0) {
            mPoolIdTv.setText("#" + mPool.getId() + " MY EARNING");
            mPoolIdTv.setTextColor(getResources().getColor(R.color.colorOsmosis));
        } else {
            mPoolIdTv.setText("#" + mPool.getId() + " EARNING");
        }
        mPoolCoinPairTv.setText(WUtil.dpOsmosisTokenName(getBaseDao(), coin0.denom) + " / " + WUtil.dpOsmosisTokenName(getBaseDao(), coin1.denom));

        mPoolAprsTv1.setText(WDp.getPercentDp(apr1));
        mPoolAprsTv7.setText(WDp.getPercentDp(apr7));
        mPoolAprsTv14.setText(WDp.getPercentDp(apr14));

        BigDecimal availableAmount = getBaseDao().getAvailable("gamm/pool/" + mPool.getId());
        BigDecimal availableValue = availableAmount.multiply(lpCoinPrice).movePointLeft(18).setScale(2, RoundingMode.DOWN);
        mAvailableAmountTv.setText(WDp.getDpAmount2(availableAmount, 18, 18));
        mAvailableDenomTv.setText("GAMM-" + mPool.getId());
        mAvailableValueTv.setText(WDp.getDpRawDollor(getBaseContext(), availableValue, 2));

        //display recycler
        for (Lock.PeriodLock lockup : mLockUps) {
            long now = new Date().getTime();
            long endTime = lockup.getEndTime().getSeconds() * 1000;
            if (endTime == -62135596800000l) {
                mBondedList.add(lockup);
            } else if (endTime > now) {
                mUnbondingList.add(lockup);
            } else {
                mUnbondedList.add(lockup);
            }
        }
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new EarningDetailsAdapter();
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerViewHeader = new RecyclerViewHeader(EarningDetailActivity.this, true, getSectionCall());
        mRecyclerView.addItemDecoration(mRecyclerViewHeader);

        mBtnNewEarning.setOnClickListener(this);
    }

    private SectionCallback getSectionCall() {
        return new SectionCallback() {
            @Override
            public boolean isSection(int position) {
                return position == 0 || position == mBondedList.size() || position == mBondedList.size() + mUnbondingList.size();
            }

            @Override
            public String SecitonHeader(ArrayList<Lock.PeriodLock> lockArrayList, int section) {
                if (section == TYPE_BONDED) {
                    return getString(R.string.str_earing_bonded_header);
                } else if (section == TYPE_UNBONDING) {
                    return getString(R.string.str_earing_unbonding_header);
                } else if (section == TYPE_UNBONDED) {
                    return getString(R.string.str_earing_unbonded_header);
                }
                return getString(R.string.str_unknown_token_title);
            }
        };
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onClick(View view) {
        if (view.equals(mBtnNewEarning)) {
            onCheckNewEarning();
        }
    }

    public void onCheckNewEarning() {
        WLog.w("onCheckNewEarning");
        if (!account.hasPrivateKey) {
            Dialog_WatchMode add = Dialog_WatchMode.newInstance();
            showDialog(add);
            return;
        }

        BigDecimal availableAmount = getBaseDao().getAvailable("gamm/pool/" + mPool.getId());
        if (availableAmount.compareTo(BigDecimal.ZERO) <= 0) {
            Toast.makeText(EarningDetailActivity.this, R.string.error_not_enough_to_balance, Toast.LENGTH_SHORT).show();
            return;
        }

        showDialog(OsmoLockupDurationDialogFragment.Companion.newInstance());
    }

    public void onStartNewEarning(long unbondingDuration) {
        WLog.w("onStartNewEarning " + unbondingDuration);
        Intent intent = new Intent(this, StartEarningActivity.class);
        intent.putExtra("osmosisPool", mPool.toByteArray());
        intent.putExtra("osmosisDuration", unbondingDuration);
        startActivity(intent);

    }

    public void onCheckUnbonding(Lock.PeriodLock lockup) {
        WLog.w("onCheckUnbonding " + lockup.getID());
        if (!account.hasPrivateKey) {
            Dialog_WatchMode add = Dialog_WatchMode.newInstance();
            showDialog(add);
            return;
        }

        ArrayList<Lock.PeriodLock> tempLockups = new ArrayList<>();
        BigDecimal totalToUnbonding = BigDecimal.ZERO;
        for (Lock.PeriodLock lock : mBondedList) {
            if (lock.getDuration().getSeconds() == lockup.getDuration().getSeconds()) {
                tempLockups.add(lock);
                totalToUnbonding = totalToUnbonding.add(new BigDecimal(lock.getCoins(0).getAmount()));
            }
        }

        if (tempLockups.size() > 1) {
            //display dialog for start unbonding all for same class
            OsmosisPeriodLockWrapper lockupsWrapper = new OsmosisPeriodLockWrapper(tempLockups);
            OsmoUnbondingAllDialogFragment bottomSheetDialog = OsmoUnbondingAllDialogFragment.Companion.netInstance(
                    lockupsWrapper,
                    lockup.toByteArray()
            );
            showDialog(bottomSheetDialog);
        } else {
            onStartUnbonding(tempLockups);
        }
    }

    public void onStartUnbonding(ArrayList<Lock.PeriodLock> lockups) {
        WLog.w("onStartUnbonding " + lockups.size());
        Intent intent = new Intent(this, StartUnbondingActivity.class);
        OsmosisPeriodLockWrapper lockupsWrapper = new OsmosisPeriodLockWrapper(lockups);
        intent.putExtra("osmosislockups", lockupsWrapper);
        startActivity(intent);
    }

    public void onCheckUnlock(Lock.PeriodLock lockup) {
        WLog.w("onCheckUnlock " + lockup.getID());
        if (!account.hasPrivateKey) {
            Dialog_WatchMode add = Dialog_WatchMode.newInstance();
            showDialog(add);
            return;
        }

        ArrayList<Lock.PeriodLock> tempLockups = new ArrayList<>(mUnbondedList);

        if (tempLockups.size() > 1) {
            //TODO display dialog for start unbonding all for same class
            OsmosisPeriodLockWrapper lockupsWrapper = new OsmosisPeriodLockWrapper(tempLockups);
            OsmoUnlockAll bottomSheetDialog = OsmoUnlockAll.Companion.netInstance(
                    lockupsWrapper,
                    lockup.toByteArray()
            );
            showDialog(bottomSheetDialog);
        } else {
            onStartUnlock(tempLockups);
        }
    }

    public void onStartUnlock(ArrayList<Lock.PeriodLock> lockups) {
        WLog.w("onStartUnlock " + lockups.size());
        Intent intent = new Intent(this, StartUnlockActivity.class);
        OsmosisPeriodLockWrapper lockupsWrapper = new OsmosisPeriodLockWrapper(lockups);
        intent.putExtra("osmosislockups", lockupsWrapper);
        startActivity(intent);
    }


    private class EarningDetailsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
            if (viewType == TYPE_BONDED) {
                return new EarningBondedHolder(getLayoutInflater().inflate(R.layout.item_osmosis_earning_detail_bonded, viewGroup, false));
            } else if (viewType == TYPE_UNBONDING) {
                return new EarningUnbondingHolder(getLayoutInflater().inflate(R.layout.item_osmosis_earning_detail_unbonding, viewGroup, false));
            } else if (viewType == TYPE_UNBONDED) {
                return new EarningUnbondedHolder(getLayoutInflater().inflate(R.layout.item_osmosis_earning_detail_unbonded, viewGroup, false));
            }
            return null;
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
            if (getItemViewType(position) == TYPE_BONDED) {
                final EarningBondedHolder holder = (EarningBondedHolder) viewHolder;
                final Lock.PeriodLock lockup = mBondedList.get(position);
                holder.onBindView(getBaseContext(), EarningDetailActivity.this, getBaseDao(), mPool, lockup, mGauges);

            } else if (getItemViewType(position) == TYPE_UNBONDING) {
                final EarningUnbondingHolder holder = (EarningUnbondingHolder) viewHolder;
                final Lock.PeriodLock lockup = mUnbondingList.get(position - mBondedList.size());
                holder.onBindView(getBaseContext(), EarningDetailActivity.this, getBaseDao(), mPool, lockup, mGauges);

            } else if (getItemViewType(position) == TYPE_UNBONDED) {
                final EarningUnbondedHolder holder = (EarningUnbondedHolder) viewHolder;
                final Lock.PeriodLock lockup = mUnbondedList.get(position - mBondedList.size() - mUnbondingList.size());
                holder.onBindView(getBaseContext(), EarningDetailActivity.this, getBaseDao(), mPool, lockup, mGauges);

            }

        }

        @Override
        public int getItemCount() {
            return mBondedList.size() + mUnbondingList.size() + mUnbondedList.size();
        }

        @Override
        public int getItemViewType(int position) {
            if (position < mBondedList.size()) {
                return TYPE_BONDED;
            } else if (position < (mBondedList.size() + mUnbondingList.size())) {
                return TYPE_UNBONDING;
            } else {
                return TYPE_UNBONDED;
            }
        }
    }

    // Section Header
    public class RecyclerViewHeader extends RecyclerView.ItemDecoration {
        private final int topPadding;

        private final boolean sticky;
        private final SectionCallback sectionCallback;

        private View headerView;
        private TextView mHeaderTitle;
        private TextView mItemCnt;

        public RecyclerViewHeader(Context context, boolean sticky, @NonNull SectionCallback sectionCallback) {
            this.sticky = sticky;
            this.sectionCallback = sectionCallback;

            topPadding = dpToPx(context, 26);
        }

        // dp -> pixel 단위로 변경
        private int dpToPx(Context context, int dp) {
            return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
        }

        @Override
        public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
            super.onDrawOver(c, parent, state);

            if (headerView == null) {
                headerView = inflateHeaderView(parent);
                mHeaderTitle = (TextView) headerView.findViewById(R.id.header_title);
                mItemCnt = (TextView) headerView.findViewById(R.id.recycler_cnt);
                fixLayoutSize(headerView, parent);
            }

            String previousHeader = "";
            for (int i = 0; i < parent.getChildCount(); i++) {
                View child = parent.getChildAt(i);
                final int position = parent.getChildAdapterPosition(child);
                if (position == RecyclerView.NO_POSITION) {
                    return;
                }

                String title = "";
                mSection = parent.getAdapter().getItemViewType(position);
                if (mSection == TYPE_BONDED) {
                    title = sectionCallback.SecitonHeader(mBondedList, mSection);
                    mItemCnt.setText("" + mBondedList.size());
                } else if (mSection == TYPE_UNBONDING) {
                    title = sectionCallback.SecitonHeader(mUnbondingList, mSection);
                    mItemCnt.setText("" + mUnbondingList.size());
                } else if (mSection == TYPE_UNBONDED) {
                    title = sectionCallback.SecitonHeader(mUnbondedList, mSection);
                    mItemCnt.setText("" + mUnbondedList.size());
                }
                mHeaderTitle.setText(title);
                if (!previousHeader.equals(title) || sectionCallback.isSection(position)) {
                    drawHeader(c, child, headerView);
                    previousHeader = title;
                }
            }
        }

        @Override
        public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);

            int position = parent.getChildAdapterPosition(view);
            if (sectionCallback.isSection(position)) {
                outRect.top = topPadding;
            }
        }

        private void drawHeader(Canvas c, View child, View headerView) {
            c.save();
            if (sticky) {
                c.translate(0, Math.max(0, child.getTop() - headerView.getHeight()));
            } else {
                c.translate(0, child.getTop() - headerView.getHeight());
            }
            headerView.draw(c);
            c.restore();
        }

        private View inflateHeaderView(RecyclerView parent) {
            return LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sticky_header, parent, false);
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

    public interface SectionCallback {
        boolean isSection(int position);

        String SecitonHeader(ArrayList<Lock.PeriodLock> lockArrayList, int section);
    }
}
