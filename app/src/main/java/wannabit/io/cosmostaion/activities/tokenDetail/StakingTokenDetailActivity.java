package wannabit.io.cosmostaion.activities.tokenDetail;

import static wannabit.io.cosmostaion.base.BaseChain.BNB_MAIN;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_SIMPLE_SEND;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.math.BigDecimal;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.SendActivity;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import com.fulldive.wallet.presentation.accounts.AccountShowDialogFragment;
import wannabit.io.cosmostaion.dialog.Dialog_WatchMode;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WUtil;
import wannabit.io.cosmostaion.widget.BaseHolder;
import wannabit.io.cosmostaion.widget.tokenDetail.TokenStakingOldHolder;
import wannabit.io.cosmostaion.widget.tokenDetail.UnBondingHolder;
import wannabit.io.cosmostaion.widget.tokenDetail.VestingHolder;

public class StakingTokenDetailActivity extends BaseActivity implements View.OnClickListener {

    private Toolbar mToolbar;
    private ImageView mToolbarSymbolImg;
    private TextView mToolbarSymbol;
    private TextView mItemPerPrice;
    private ImageView mItemUpDownImg;
    private TextView mItemUpDownPrice;

    private CardView mBtnAddressPopup;
    private ImageView mKeyState;
    private TextView mAddress;
    private TextView mTotalValue;

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;

    private RelativeLayout mBtnBep3Send;
    private RelativeLayout mBtnSend;

    private StakingTokenAdapter mAdapter;
    private Boolean mHasVesting = false;
    private String mMainDenom;

    private int mDivideDecimal = 6;
    private BigDecimal mTotalAmount = BigDecimal.ZERO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_token_detail);

        mToolbar = findViewById(R.id.tool_bar);
        mToolbarSymbolImg = findViewById(R.id.toolbar_symbol_img);
        mToolbarSymbol = findViewById(R.id.toolbar_symbol);
        mItemPerPrice = findViewById(R.id.per_price);
        mItemUpDownImg = findViewById(R.id.ic_price_updown);
        mItemUpDownPrice = findViewById(R.id.dash_price_updown_tx);

        mBtnAddressPopup = findViewById(R.id.card_root);
        mKeyState = findViewById(R.id.img_account);
        mAddress = findViewById(R.id.account_Address);
        mTotalValue = findViewById(R.id.total_value);
        mSwipeRefreshLayout = findViewById(R.id.layer_refresher);
        mRecyclerView = findViewById(R.id.recycler);
        mBtnBep3Send = findViewById(R.id.btn_bep3_send);
        mBtnSend = findViewById(R.id.btn_send);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mAccount = getBaseDao().onSelectAccount(getBaseDao().getLastUser());
        mBaseChain = BaseChain.getChain(mAccount.baseChain);
        mMainDenom = mBaseChain.getMainDenom();
        mDivideDecimal = WDp.mainDivideDecimal(mBaseChain);

        if (mBaseChain.equals(BNB_MAIN)) {
            mBtnBep3Send.setVisibility(View.VISIBLE);
        }

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new StakingTokenAdapter();
        mRecyclerView.setAdapter(mAdapter);

        //prepare for token history
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                onUpdateView();
            }
        });

        onUpdateView();
        mBtnAddressPopup.setOnClickListener(this);
        mBtnBep3Send.setOnClickListener(this);
        mBtnSend.setOnClickListener(this);
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

    private void onUpdateView() {
        WDp.getStakingTokenImg(mBaseChain, mToolbarSymbolImg);
        WDp.DpMainDenom(StakingTokenDetailActivity.this, mBaseChain, mToolbarSymbol);

        mItemPerPrice.setText(WDp.dpPerUserCurrencyValue(getBaseDao(), mMainDenom));
        mItemUpDownPrice.setText(WDp.dpValueChange(getBaseDao(), mMainDenom));
        final BigDecimal lastUpDown = WDp.valueChange(getBaseDao(), mMainDenom);
        if (lastUpDown.compareTo(BigDecimal.ZERO) > 0) {
            mItemUpDownImg.setVisibility(View.VISIBLE);
            mItemUpDownImg.setImageDrawable(getResources().getDrawable(R.drawable.ic_price_up));
        } else if (lastUpDown.compareTo(BigDecimal.ZERO) < 0) {
            mItemUpDownImg.setVisibility(View.VISIBLE);
            mItemUpDownImg.setImageDrawable(getResources().getDrawable(R.drawable.ic_price_down));
        } else {
            mItemUpDownImg.setVisibility(View.INVISIBLE);
        }

        mBtnAddressPopup.setCardBackgroundColor(WDp.getChainBgColor(StakingTokenDetailActivity.this, mBaseChain));
        mAddress.setText(mAccount.address);
        mKeyState.setColorFilter(ContextCompat.getColor(getBaseContext(), R.color.colorGray0), android.graphics.PorterDuff.Mode.SRC_IN);
        if (mAccount.hasPrivateKey) {
            mKeyState.setColorFilter(WDp.getChainColor(getBaseContext(), mBaseChain), android.graphics.PorterDuff.Mode.SRC_IN);
        }
        mTotalAmount = getBaseDao().getAllMainAssetOld(mMainDenom);
        mTotalValue.setText(WDp.dpUserCurrencyValue(getBaseDao(), mMainDenom, mTotalAmount, mDivideDecimal));
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mBtnAddressPopup)) {
            AccountShowDialogFragment show = AccountShowDialogFragment.Companion.newInstance(
                    mAccount.getAccountTitle(this),
                    mAccount.address
            );
            showDialog(show);

        } else if (v.equals(mBtnBep3Send)) {
            onStartHTLCSendActivity(mBaseChain.getMainDenom());

        } else if (v.equals(mBtnSend)) {
            if (!mAccount.hasPrivateKey) {
                Dialog_WatchMode add = Dialog_WatchMode.newInstance();
                showDialog(add);
                return;
            }
            Intent intent = new Intent(getBaseContext(), SendActivity.class);
            BigDecimal mainAvailable = getBaseDao().availableAmount(mBaseChain.getMainDenom());
            BigDecimal feeAmount = WUtil.getEstimateGasFeeAmount(getBaseContext(), mBaseChain, CONST_PW_TX_SIMPLE_SEND, 0);
            if (mainAvailable.compareTo(feeAmount) < 0) {
                Toast.makeText(getBaseContext(), R.string.error_not_enough_fee, Toast.LENGTH_SHORT).show();
                return;
            }
            intent.putExtra("sendTokenDenom", mMainDenom);
            startActivity(intent);
        }
    }

    private class StakingTokenAdapter extends RecyclerView.Adapter<BaseHolder> {
        private static final int TYPE_STAKE_OLD = 0;

        private static final int TYPE_VESTING = 98;
        private static final int TYPE_UNBONDING = 99;
        private static final int TYPE_HISTORY = 100;


        @NonNull
        @Override
        public BaseHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
            if (viewType == TYPE_STAKE_OLD) {
                return new TokenStakingOldHolder(getLayoutInflater().inflate(R.layout.layout_card_staking_old, viewGroup, false));
            } else if (viewType == TYPE_VESTING) {
                return new VestingHolder(getLayoutInflater().inflate(R.layout.layout_vesting_schedule, viewGroup, false));
            } else if (viewType == TYPE_UNBONDING) {
                return new UnBondingHolder(getLayoutInflater().inflate(R.layout.item_wallet_undelegation, viewGroup, false));
            }
            return null;
        }

        @Override
        public void onBindViewHolder(@NonNull BaseHolder holder, int position) {
            if (getItemViewType(position) == TYPE_STAKE_OLD) {
                holder.onBindTokenHolder(getBaseContext(), mBaseChain, getBaseDao(), mBaseChain.getMainDenom());
            } else if (getItemViewType(position) == TYPE_VESTING) {
                holder.onBindTokenHolder(getBaseContext(), mBaseChain, getBaseDao(), mBaseChain.getMainDenom());

            } else if (getItemViewType(position) == TYPE_UNBONDING) {
                holder.onBindTokenHolder(getBaseContext(), mBaseChain, getBaseDao(), mBaseChain.getMainDenom());

            } else if (getItemViewType(position) == TYPE_HISTORY) {

            }
        }

        @Override
        public int getItemCount() {
            if (mHasVesting) {
                if (getBaseDao().mMyUnbondings.size() > 0) {
                    return 3;
                } else {
                    return 2;
                }
            } else {
                if (getBaseDao().mMyUnbondings.size() > 0) {
                    return 2;
                } else {
                    return 1;
                }
            }
        }

        @Override
        public int getItemViewType(int position) {
            if (position == 0) {
                return TYPE_STAKE_OLD;

            } else if (position == 1) {
                if (mHasVesting) {
                    return TYPE_VESTING;
                } else {
                    if (getBaseDao().mMyUnbondings.size() > 0) {
                        return TYPE_UNBONDING;
                    }
                }
            } else if (position == 2) {
                if (getBaseDao().mMyUnbondings.size() > 0) {
                    return TYPE_UNBONDING;
                }
            }
            return -1;
        }
    }
}
