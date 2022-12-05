package wannabit.io.cosmostaion.activities.tokenDetail;

import static wannabit.io.cosmostaion.base.BaseChain.KAVA_MAIN;

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
import wannabit.io.cosmostaion.base.chains.Kava;
import wannabit.io.cosmostaion.dao.Asset;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WUtil;
import wannabit.io.cosmostaion.widget.tokenDetail.TokenDetailHolder;
import wannabit.io.cosmostaion.widget.tokenDetail.VestingHolder;

public class NativeTokenGrpcActivity extends BaseActivity implements View.OnClickListener {

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
    private NativeTokenGrpcAdapter mAdapter;

    private RelativeLayout mBtnBep3Send;
    private RelativeLayout mBtnSend;

    private String mNativeGrpcDenom;
    private Boolean mHasVesting = false;

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
        mAddress = findViewById(R.id.account_Address);
        mEthAddress = findViewById(R.id.eth_address);
        mTotalValue = findViewById(R.id.total_value);
        mSwipeRefreshLayout = findViewById(R.id.layer_refresher);
        mRecyclerView = findViewById(R.id.recycler);
        mBtnBep3Send = findViewById(R.id.btn_bep3_send);
        mBtnSend = findViewById(R.id.btn_send);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mAccount = getBaseDao().onSelectAccount(getBaseDao().getLastUser());
        mChainConfig = ChainFactory.getChain(BaseChain.getChain(mAccount.baseChain));
        mNativeGrpcDenom = getIntent().getStringExtra("denom");

        if (mChainConfig.baseChain().equals(KAVA_MAIN)) {
            if (getBaseDao().onParseRemainVestingsByDenom(mNativeGrpcDenom).size() > 0) mHasVesting = true;
            if (WUtil.isBep3Coin(mNativeGrpcDenom)) mBtnBep3Send.setVisibility(View.VISIBLE);
        }

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new NativeTokenGrpcAdapter();
        mRecyclerView.setAdapter(mAdapter);

        mSwipeRefreshLayout.setOnRefreshListener(() -> onUpdateView());

        onUpdateView();
        mBtnAddressPopup.setOnClickListener(this);
        mBtnSend.setOnClickListener(this);
        mBtnBep3Send.setOnClickListener(this);
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
        BigDecimal totalAmount = getBaseDao().getAvailable(mNativeGrpcDenom).add(getBaseDao().getVesting(mNativeGrpcDenom));
        int decimal = WDp.getDenomDecimal(getBaseDao(), mChainConfig, mNativeGrpcDenom);
        WDp.setDpSymbolImg(getBaseDao(), mChainConfig, mNativeGrpcDenom, mToolbarSymbolImg);
        mToolbarSymbol.setText(WDp.getDpSymbol(getBaseDao(), mChainConfig, mNativeGrpcDenom));

        final Asset asset = getBaseDao().getAsset(mChainConfig, mNativeGrpcDenom);
        mItemPerPrice.setText(WDp.dpPrice(getBaseDao(), asset.coinGeckoId));
        WDp.valueChangeStatus(this, getBaseDao(), asset.coinGeckoId, mItemUpDownPrice);
        mTotalValue.setText(WDp.dpAssetValue(getBaseDao(), asset.coinGeckoId, totalAmount, decimal));

        mBtnAddressPopup.setCardBackgroundColor(ContextCompat.getColor(NativeTokenGrpcActivity.this, mChainConfig.chainBgColor()));
        mAddress.setText(mAccount.address);
        setEthAddress(mChainConfig, mEthAddress);
        setAccountKeyStatus(this, mAccount, mChainConfig, mKeyState);
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mBtnAddressPopup)) {
            onClickQrCopy(mChainConfig, mAccount);

        } else if (v.equals(mBtnSend)) {
            if (!mAccount.hasPrivateKey) {
                onInsertKeyDialog();
                return;
            }
            if (!WDp.isTxFeePayable(this, getBaseDao(), mChainConfig)) {
                Toast.makeText(this, R.string.error_not_enough_fee, Toast.LENGTH_SHORT).show();
                return;
            }

            Intent intent = new Intent(getBaseContext(), SendActivity.class);
            intent.putExtra("sendTokenDenom", mNativeGrpcDenom);
            startActivity(intent);

        } else if (v.equals(mBtnBep3Send)) {
            onStartHTLCSendActivity(mNativeGrpcDenom);
        }
    }

    private class NativeTokenGrpcAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private static final int TYPE_NATIVE = 0;
        private static final int TYPE_VESTING = 1;

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
            if (viewType == TYPE_NATIVE) {
                return new TokenDetailHolder(getLayoutInflater().inflate(R.layout.item_amount_detail, viewGroup, false));
            } else {
                return new VestingHolder(getLayoutInflater().inflate(R.layout.layout_vesting_schedule, viewGroup, false));
            }
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
            if (getItemViewType(position) == TYPE_NATIVE) {
                TokenDetailHolder holder = (TokenDetailHolder) viewHolder;
                holder.onBindNativeTokengRPC(NativeTokenGrpcActivity.this, mChainConfig, getBaseDao(), mNativeGrpcDenom);

            } else if (getItemViewType(position) == TYPE_VESTING) {
                VestingHolder holder = (VestingHolder) viewHolder;
                holder.onBindTokenHolder(getBaseContext(), mChainConfig.baseChain(), getBaseDao(), mNativeGrpcDenom);
            }
        }

        @Override
        public int getItemCount() {
            if (mHasVesting) return 2;
            else return 1;
        }

        @Override
        public int getItemViewType(int position) {
            if (mChainConfig.baseChain().equals(KAVA_MAIN)) {
                if (mNativeGrpcDenom.equalsIgnoreCase(Kava.KAVA_HARD_DENOM) || mNativeGrpcDenom.equalsIgnoreCase(Kava.KAVA_SWP_DENOM)) {
                    if (mHasVesting) {
                        if (position == 0) return TYPE_NATIVE;
                        if (position == 1) return TYPE_VESTING;
                        else return -1;
                    } else {
                        if (position == 0) return TYPE_NATIVE;
                        else return -1;
                    }
                } else {
                    if (position == 0) return TYPE_NATIVE;
                    else return -1;
                }

            } else {
                if (position == 0) {
                    return TYPE_NATIVE;
                }
            }
            return -1;
        }
    }
}
