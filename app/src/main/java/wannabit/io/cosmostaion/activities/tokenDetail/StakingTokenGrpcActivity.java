package wannabit.io.cosmostaion.activities.tokenDetail;

import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_IBC_TRANSFER;
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

import com.fulldive.wallet.presentation.accounts.AccountShowDialogFragment;
import com.squareup.picasso.Picasso;

import java.math.BigDecimal;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.SendActivity;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.dialog.Dialog_IBC_Send_Warning;
import wannabit.io.cosmostaion.dialog.Dialog_WatchMode;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WUtil;
import wannabit.io.cosmostaion.widget.BaseHolder;
import wannabit.io.cosmostaion.widget.tokenDetail.TokenStakingNewHolder;
import wannabit.io.cosmostaion.widget.tokenDetail.UnBondingHolder;
import wannabit.io.cosmostaion.widget.tokenDetail.VestingHolder;

public class StakingTokenGrpcActivity extends BaseActivity implements View.OnClickListener {

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

    private RelativeLayout mBtnIbcSend;
    private RelativeLayout mBtnSend;

    private StakingTokenGrpcAdapter mAdapter;
    private Boolean mHasVesting = false;
    private String mMainDenom;

    private int mDivideDecimal = 6;
    private int mDisplayDecimal = 6;
    private BigDecimal mTotalAmount = BigDecimal.ZERO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_token_detail);

        mToolbar = findViewById(R.id.toolbar);
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
        mBtnIbcSend = findViewById(R.id.btn_ibc_send);
        mBtnSend = findViewById(R.id.btn_send);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        account = getBaseDao().onSelectAccount(getBaseDao().getLastUser());
        baseChain = BaseChain.getChain(account.baseChain);
        mMainDenom = baseChain.getMainDenom();
        mDivideDecimal = WDp.mainDivideDecimal(baseChain);
        mDisplayDecimal = WDp.mainDivideDecimal(baseChain);

        if (getBaseDao().onParseRemainVestingsByDenom(baseChain.getMainDenom()).size() > 0) {
            mHasVesting = true;
        }
        mBtnIbcSend.setVisibility(View.VISIBLE);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new StakingTokenGrpcAdapter();
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
        mBtnIbcSend.setOnClickListener(this);
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
        Picasso.get().cancelRequest(mToolbarSymbolImg);
        mToolbarSymbolImg.setImageResource(baseChain.getCoinIcon());
        WDp.DpMainDenom(baseChain, mToolbarSymbol);

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

        mBtnAddressPopup.setCardBackgroundColor(WDp.getChainBgColor(StakingTokenGrpcActivity.this, baseChain));
        mAddress.setText(account.address);
        mKeyState.setColorFilter(ContextCompat.getColor(getBaseContext(), R.color.colorGray0), android.graphics.PorterDuff.Mode.SRC_IN);
        if (account.hasPrivateKey) {
            mKeyState.setColorFilter(WDp.getChainColor(getBaseContext(), baseChain), android.graphics.PorterDuff.Mode.SRC_IN);
        }
        mTotalAmount = getBaseDao().getAllMainAsset(mMainDenom);
        mTotalValue.setText(WDp.dpUserCurrencyValue(getBaseDao(), mMainDenom, mTotalAmount, mDivideDecimal));
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mBtnAddressPopup)) {
            AccountShowDialogFragment show = AccountShowDialogFragment.Companion.newInstance(
                    account.getAccountTitle(this),
                    account.address
            );
            showDialog(show);

        } else if (v.equals(mBtnIbcSend)) {
            if (!account.hasPrivateKey) {
                Dialog_WatchMode add = Dialog_WatchMode.newInstance();
                showDialog(add);
                return;
            }
            final String mainDenom = baseChain.getMainDenom();
            final BigDecimal feeAmount = WUtil.getEstimateGasFeeAmount(this, baseChain, CONST_PW_TX_IBC_TRANSFER, 0);

            mTotalAmount = getBaseDao().getAvailable(mMainDenom);
            if (mainDenom.equalsIgnoreCase(mMainDenom)) {
                mTotalAmount = mTotalAmount.subtract(feeAmount);
            }
            if (mTotalAmount.compareTo(BigDecimal.ZERO) <= 0) {
                Toast.makeText(getBaseContext(), R.string.error_not_enough_budget, Toast.LENGTH_SHORT).show();
                return;
            }
            Bundle bundle = new Bundle();
            bundle.putString("sendTokenDenom", mMainDenom);
            Dialog_IBC_Send_Warning warning = Dialog_IBC_Send_Warning.newInstance(bundle);
            showDialog(warning);
        } else if (v.equals(mBtnSend)) {
            if (!account.hasPrivateKey) {
                Dialog_WatchMode add = Dialog_WatchMode.newInstance();
                showDialog(add);
                return;
            }
            Intent intent = new Intent(getBaseContext(), SendActivity.class);
            BigDecimal mainAvailable = getBaseDao().getAvailable(baseChain.getMainDenom());
            BigDecimal feeAmount = WUtil.getEstimateGasFeeAmount(getBaseContext(), baseChain, CONST_PW_TX_SIMPLE_SEND, 0);
            if (mainAvailable.compareTo(feeAmount) < 0) {
                Toast.makeText(getBaseContext(), R.string.error_not_enough_fee, Toast.LENGTH_SHORT).show();
                return;
            }
            intent.putExtra("sendTokenDenom", mMainDenom);
            startActivity(intent);
        }
    }

    private class StakingTokenGrpcAdapter extends RecyclerView.Adapter<BaseHolder> {
        private static final int TYPE_STAKE_NEW = 0;

        private static final int TYPE_VESTING = 98;
        private static final int TYPE_UNBONDING = 99;
        private static final int TYPE_HISTORY = 100;

        @NonNull
        @Override
        public BaseHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
            if (viewType == TYPE_STAKE_NEW) {
                return new TokenStakingNewHolder(getLayoutInflater().inflate(R.layout.layout_card_staking_new, viewGroup, false));
            } else if (viewType == TYPE_VESTING) {
                return new VestingHolder(getLayoutInflater().inflate(R.layout.layout_vesting_schedule, viewGroup, false));
            } else if (viewType == TYPE_UNBONDING) {
                return new UnBondingHolder(getLayoutInflater().inflate(R.layout.item_wallet_undelegation, viewGroup, false));
            }
            return null;
        }

        @Override
        public void onBindViewHolder(@NonNull BaseHolder holder, int position) {
            if (getItemViewType(position) == TYPE_STAKE_NEW) {
                holder.onBindTokenHolder(getBaseContext(), baseChain, getBaseDao(), baseChain.getMainDenom());
            } else if (getItemViewType(position) == TYPE_VESTING) {
                holder.onBindTokenHolder(getBaseContext(), baseChain, getBaseDao(), baseChain.getMainDenom());

            } else if (getItemViewType(position) == TYPE_UNBONDING) {
                holder.onBindTokenHolder(getBaseContext(), baseChain, getBaseDao(), baseChain.getMainDenom());

            }
        }

        @Override
        public int getItemCount() {
            if (mHasVesting) {
                if (getBaseDao().mGrpcUndelegations.size() > 0) {
                    return 3;
                } else {
                    return 2;
                }
            } else {
                if (getBaseDao().mGrpcUndelegations.size() > 0) {
                    return 2;
                } else {
                    return 1;
                }
            }
        }

        @Override
        public int getItemViewType(int position) {
            if (position == 0) {
                return TYPE_STAKE_NEW;

            } else if (position == 1) {
                if (mHasVesting) {
                    return TYPE_VESTING;
                } else {
                    if (getBaseDao().mGrpcUndelegations.size() > 0) {
                        return TYPE_UNBONDING;
                    }
                }
            } else if (position == 2) {
                if (getBaseDao().mGrpcUndelegations.size() > 0) {
                    return TYPE_UNBONDING;
                }
            }
            return -1;
        }
    }
}
