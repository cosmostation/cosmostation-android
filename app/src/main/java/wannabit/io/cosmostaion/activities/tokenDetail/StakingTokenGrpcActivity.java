package wannabit.io.cosmostaion.activities.tokenDetail;

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
import wannabit.io.cosmostaion.activities.txs.common.SendActivity;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.chains.ChainFactory;
import wannabit.io.cosmostaion.dao.Asset;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.widget.BaseHolder;
import wannabit.io.cosmostaion.widget.tokenDetail.TokenDetailHolder;
import wannabit.io.cosmostaion.widget.tokenDetail.UnBondingHolder;
import wannabit.io.cosmostaion.widget.tokenDetail.VestingHolder;

public class StakingTokenGrpcActivity extends BaseActivity implements View.OnClickListener {

    private Toolbar mToolbar;
    private ImageView mToolbarSymbolImg;
    private TextView mToolbarSymbol;
    private TextView mItemPerPrice;
    private TextView mItemUpDownPrice;

    private CardView mBtnAddressPopup;
    private ImageView mKeyState;
    private TextView mAddress, mEthAddress;
    private TextView mTotalValue;

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private StakingTokenGrpcAdapter mAdapter;

    private RelativeLayout mBtnSend;

    private Boolean mHasVesting = false;
    private String mMainDenom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_token_detail);
        mToolbar = findViewById(R.id.tool_bar);
        mToolbarSymbolImg = findViewById(R.id.toolbar_symbol_img);
        mToolbarSymbol = findViewById(R.id.toolbar_symbol);
        mItemPerPrice = findViewById(R.id.per_price);
        mItemUpDownPrice = findViewById(R.id.dash_price_updown_tx);

        mBtnAddressPopup = findViewById(R.id.card_root);
        mKeyState = findViewById(R.id.img_account);
        mAddress = findViewById(R.id.wallet_address);
        mEthAddress = findViewById(R.id.eth_address);
        mTotalValue = findViewById(R.id.total_value);
        mSwipeRefreshLayout = findViewById(R.id.layer_refresher);
        mRecyclerView = findViewById(R.id.recycler);
        mBtnSend = findViewById(R.id.btn_send);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mAccount = getBaseDao().onSelectAccount(getBaseDao().getLastUser());
        mChainConfig = ChainFactory.getChain(BaseChain.getChain(mAccount.baseChain));
        mMainDenom = mChainConfig.mainDenom();

        if (getBaseDao().onParseRemainVestingsByDenom(mChainConfig.mainDenom()).size() > 0) mHasVesting = true;

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new StakingTokenGrpcAdapter();
        mRecyclerView.setAdapter(mAdapter);

        mSwipeRefreshLayout.setOnRefreshListener(() -> onUpdateView());

        onUpdateView();
        mBtnAddressPopup.setOnClickListener(this);
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
        mToolbarSymbolImg.setImageResource(mChainConfig.mainDenomImg());
        mToolbarSymbol.setText(WDp.getDpSymbol(getBaseDao(), mChainConfig, mMainDenom));

        final Asset asset = getBaseDao().getAsset(mChainConfig, mMainDenom);
        mItemPerPrice.setText(WDp.dpPrice(getBaseDao(), asset.coinGeckoId));
        WDp.valueChangeStatus(this, getBaseDao(), asset.coinGeckoId, mItemUpDownPrice);
        mTotalValue.setText(WDp.dpAssetValue(getBaseDao(), asset.coinGeckoId, getBaseDao().getAllMainAsset(mMainDenom), WDp.getDenomDecimal(getBaseDao(), mChainConfig, mMainDenom)));

        mBtnAddressPopup.setCardBackgroundColor(ContextCompat.getColor(StakingTokenGrpcActivity.this, mChainConfig.chainBgColor()));
        setAccountKeyStatus(this, mAccount, mChainConfig, mKeyState);
        mAddress.setText(mAccount.address);
        setEthAddress(mChainConfig, mEthAddress);
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mBtnAddressPopup)) {
            onClickQrCopy(mChainConfig, mAccount);

        } else if (v.equals(mBtnSend)) {
            if (!mAccount.hasPrivateKey && !mAccount.isLedger()) {
                onInsertKeyDialog();
                return;
            }
            if (!WDp.isTxFeePayable(this, getBaseDao(), mChainConfig)) {
                Toast.makeText(this, R.string.error_not_enough_fee, Toast.LENGTH_SHORT).show();
                return;
            }

            BigDecimal mainAvailable = getBaseDao().getAvailable(mChainConfig.mainDenom());

            if (BigDecimal.ZERO.compareTo(mainAvailable) >= 0) {
                Toast.makeText(getBaseContext(), R.string.error_not_enough_fee, Toast.LENGTH_SHORT).show();
                return;
            }

            Intent intent = new Intent(getBaseContext(), SendActivity.class);
            intent.putExtra("sendTokenDenom", mMainDenom);
            startActivity(intent);
        }
    }

    private class StakingTokenGrpcAdapter extends RecyclerView.Adapter<BaseHolder> {
        private static final int TYPE_STAKE_NEW = 0;

        private static final int TYPE_VESTING = 98;
        private static final int TYPE_UNBONDING = 99;

        @NonNull
        @Override
        public BaseHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
            if (viewType == TYPE_STAKE_NEW) {
                if (mChainConfig.baseChain().equals(BaseChain.NEUTRON_TEST)) {
                    return new TokenDetailHolder(getLayoutInflater().inflate(R.layout.layout_card_neutron, viewGroup, false));
                } else {
                    return new TokenDetailHolder(getLayoutInflater().inflate(R.layout.layout_card_staking_new, viewGroup, false));
                }
            } else if (viewType == TYPE_VESTING) {
                return new VestingHolder(getLayoutInflater().inflate(R.layout.layout_vesting_schedule, viewGroup, false));
            } else {
                return new UnBondingHolder(getLayoutInflater().inflate(R.layout.item_wallet_undelegation, viewGroup, false));
            }
        }

        @Override
        public void onBindViewHolder(@NonNull BaseHolder holder, int position) {
            if (getItemViewType(position) == TYPE_STAKE_NEW) {
                holder.onBindTokenHolder(getBaseContext(), mChainConfig.baseChain(), getBaseDao(), mChainConfig.mainDenom());

            } else if (getItemViewType(position) == TYPE_VESTING) {
                holder.onBindTokenHolder(getBaseContext(), mChainConfig.baseChain(), getBaseDao(), mChainConfig.mainDenom());

            } else if (getItemViewType(position) == TYPE_UNBONDING) {
                holder.onBindTokenHolder(getBaseContext(), mChainConfig.baseChain(), getBaseDao(), mChainConfig.mainDenom());
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
