package wannabit.io.cosmostaion.activities.chains.osmosis;

import android.os.Bundle;
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

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;

import osmosis.gamm.v1beta1.PoolOuterClass;
import osmosis.incentives.GaugeOuterClass;
import osmosis.lockup.Lock;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.dialog.Dialog_Osmo_Lockup_Duration;
import wannabit.io.cosmostaion.dialog.Dialog_Osmo_Unbonding_All;
import wannabit.io.cosmostaion.dialog.Dialog_Osmo_Unlock_All;
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
    private Toolbar mToolbar;
    private RecyclerView mRecyclerView;
    private Button mBtnNewEarning;
    private TextView mPoolIdTv;
    private TextView mPoolCoinPairTv;
    private TextView mPoolAprsTv1, mPoolAprsTv7, mPoolAprsTv14;
    private TextView mAvailableAmountTv, mAvailableDenomTv, mAvailableValueTv;

    private EarningDetailsAdapter mAdapter;

    private PoolOuterClass.Pool                 mPool;
    private ArrayList<GaugeOuterClass.Gauge>    mGauges;
    public ArrayList<Lock.PeriodLock>           mLockUps = new ArrayList<>();
    public ArrayList<Lock.PeriodLock>           mBondedList = new ArrayList<>();
    public ArrayList<Lock.PeriodLock>           mUnbondingList = new ArrayList<>();
    public ArrayList<Lock.PeriodLock>           mUnbondedList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_earning_detail);
        mToolbar            = findViewById(R.id.tool_bar);
        mRecyclerView       = findViewById(R.id.recycler);
        mBtnNewEarning      = findViewById(R.id.btn_start_earning);
        mPoolIdTv           = findViewById(R.id.pool_id);
        mPoolCoinPairTv     = findViewById(R.id.coin_pair);
        mPoolAprsTv1        = findViewById(R.id.aprs1);
        mPoolAprsTv7        = findViewById(R.id.aprs7);
        mPoolAprsTv14       = findViewById(R.id.aprs14);
        mAvailableAmountTv  = findViewById(R.id.available_amount);
        mAvailableDenomTv   = findViewById(R.id.available_denom);
        mAvailableValueTv   = findViewById(R.id.available_value);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mAccount            = getBaseDao().onSelectAccount(getBaseDao().getLastUser());
        mBaseChain          = BaseChain.getChain(mAccount.baseChain);

        try {
            mPool = PoolOuterClass.Pool.parseFrom(getIntent().getByteArrayExtra("osmosisPool"));
            OsmosisGaugeWrapper gaugeWrapper = (OsmosisGaugeWrapper) getIntent().getSerializableExtra("osmosisGauges");
            if (gaugeWrapper != null) { mGauges = gaugeWrapper.array; }
            OsmosisPeriodLockWrapper lockupWrapper = (OsmosisPeriodLockWrapper) getIntent().getSerializableExtra("osmosislockups");
            if (lockupWrapper != null) { mLockUps = lockupWrapper.array; }

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
            mPoolIdTv.setText("MY EARNING #" + mPool.getId());
            mPoolIdTv.setTextColor(getResources().getColor(R.color.colorOsmosis));
        } else {
            mPoolIdTv.setText("EARNING #" + mPool.getId());
        }
        mPoolCoinPairTv.setText(WUtil.dpOsmosisTokenName(coin0.denom) + " / " + WUtil.dpOsmosisTokenName(coin1.denom));

        mPoolAprsTv1.setText(WDp.getPercentDp(apr1));
        mPoolAprsTv7.setText(WDp.getPercentDp(apr7));
        mPoolAprsTv14.setText(WDp.getPercentDp(apr14));

        BigDecimal availableAmount = getBaseDao().getAvailable("gamm/pool/" + mPool.getId());
        BigDecimal availableValue = availableAmount.multiply(lpCoinPrice).movePointLeft(18).setScale(2, RoundingMode.DOWN);
        mAvailableAmountTv.setText(WDp.getDpAmount2(getBaseContext(), availableAmount, 18, 18));
        mAvailableDenomTv.setText("GAMM-" + mPool.getId());
        mAvailableValueTv.setText(WDp.getDpRawDollor(getBaseContext(), availableValue, 2));

        //display recycler
        for (Lock.PeriodLock lockup: mLockUps) {
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

        mBtnNewEarning.setOnClickListener(this);
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
        if (!mAccount.hasPrivateKey) {
            Dialog_WatchMode add = Dialog_WatchMode.newInstance();
            add.setCancelable(true);
            getSupportFragmentManager().beginTransaction().add(add, "dialog").commitNowAllowingStateLoss();
            return;
        }

        BigDecimal availableAmount = getBaseDao().getAvailable("gamm/pool/" + mPool.getId());
        if (availableAmount.compareTo(BigDecimal.ZERO) <= 0) {
            Toast.makeText(EarningDetailActivity.this, R.string.error_not_enough_to_balance, Toast.LENGTH_SHORT).show();
            return;
        }

        Dialog_Osmo_Lockup_Duration bottomSheetDialog = Dialog_Osmo_Lockup_Duration.getInstance();
        getSupportFragmentManager().beginTransaction().add(bottomSheetDialog, "dialog").commitNowAllowingStateLoss();
    }

    public void onStartNewEarning(long unbondingDuration) {
        WLog.w("onStartNewEarning " + unbondingDuration);

    }

    public void onCheckUnbonding(Lock.PeriodLock lockup) {
        WLog.w("onCheckUnbonding " + lockup.getID());
        if (!mAccount.hasPrivateKey) {
            Dialog_WatchMode add = Dialog_WatchMode.newInstance();
            add.setCancelable(true);
            getSupportFragmentManager().beginTransaction().add(add, "dialog").commitNowAllowingStateLoss();
            return;
        }

        ArrayList<Lock.PeriodLock> tempLockups = new ArrayList<>();
        BigDecimal totalToUnbonding = BigDecimal.ZERO;
        for (Lock.PeriodLock lock: mBondedList) {
            if (lock.getDuration().getSeconds() == lockup.getDuration().getSeconds()) {
                tempLockups.add(lock);
                totalToUnbonding = totalToUnbonding.add(new BigDecimal(lock.getCoins(0).getAmount()));
            }
        }

        if (tempLockups.size() > 1) {
            //display dialog for start unbonding all for same class
            Bundle bundle = new Bundle();
            bundle.putSerializable("single", lockup.toByteArray());
            OsmosisPeriodLockWrapper lockupsWrapper = new OsmosisPeriodLockWrapper(tempLockups);
            bundle.putSerializable("all", lockupsWrapper);
            bundle.putString("amount", totalToUnbonding.toPlainString());
            Dialog_Osmo_Unbonding_All bottomSheetDialog = Dialog_Osmo_Unbonding_All.getInstance(bundle);
            getSupportFragmentManager().beginTransaction().add(bottomSheetDialog, "dialog").commitNowAllowingStateLoss();
        } else {
            onStartUnbonding(tempLockups);
        }
    }

    public void onStartUnbonding(ArrayList<Lock.PeriodLock> lockups) {
        WLog.w("onStartUnbonding " + lockups.size());

    }

    public void onCheckUnlock(Lock.PeriodLock lockup) {
        WLog.w("onCheckUnlock " + lockup.getID());
        if (!mAccount.hasPrivateKey) {
            Dialog_WatchMode add = Dialog_WatchMode.newInstance();
            add.setCancelable(true);
            getSupportFragmentManager().beginTransaction().add(add, "dialog").commitNowAllowingStateLoss();
            return;
        }

        ArrayList<Lock.PeriodLock> tempLockups = new ArrayList<>();
        BigDecimal totalToUnlock = BigDecimal.ZERO;
        for (Lock.PeriodLock lock: mUnbondedList) {
            tempLockups.add(lock);
            totalToUnlock = totalToUnlock.add(new BigDecimal(lock.getCoins(0).getAmount()));
        }
        if (tempLockups.size() > 1) {
            //TODO display dialog for start unbonding all for same class
            Bundle bundle = new Bundle();
            bundle.putSerializable("single", lockup.toByteArray());
            OsmosisPeriodLockWrapper lockupsWrapper = new OsmosisPeriodLockWrapper(tempLockups);
            bundle.putSerializable("all", lockupsWrapper);
            bundle.putString("amount", totalToUnlock.toPlainString());
            Dialog_Osmo_Unlock_All bottomSheetDialog = Dialog_Osmo_Unlock_All.getInstance(bundle);
            getSupportFragmentManager().beginTransaction().add(bottomSheetDialog, "dialog").commitNowAllowingStateLoss();

        } else {
            onStartUnlock(tempLockups);
        }
    }

    public void onStartUnlock(ArrayList<Lock.PeriodLock> lockups) {
        WLog.w("onStartUnlock " + lockups.size());

    }


    private class EarningDetailsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private static final int TYPE_BONDED        = 1;
        private static final int TYPE_UNBONDING     = 2;
        private static final int TYPE_UNBONDED      = 3;

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
                final EarningBondedHolder holder = (EarningBondedHolder)viewHolder;
                final Lock.PeriodLock lockup = mBondedList.get(position);
                holder.onBindView(getBaseContext(), EarningDetailActivity.this, getBaseDao(), mPool, lockup, mGauges);

            } else if (getItemViewType(position) == TYPE_UNBONDING) {
                final EarningUnbondingHolder holder = (EarningUnbondingHolder)viewHolder;
                final Lock.PeriodLock lockup = mUnbondingList.get(position - mBondedList.size());
                holder.onBindView(getBaseContext(), EarningDetailActivity.this, getBaseDao(), mPool, lockup, mGauges);

            } else if (getItemViewType(position) == TYPE_UNBONDED) {
                final EarningUnbondedHolder holder = (EarningUnbondedHolder)viewHolder;
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
}
