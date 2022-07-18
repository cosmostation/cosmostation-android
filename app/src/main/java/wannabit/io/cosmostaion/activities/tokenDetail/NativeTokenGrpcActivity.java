package wannabit.io.cosmostaion.activities.tokenDetail;

import static wannabit.io.cosmostaion.base.BaseChain.CRESCENT_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.KAVA_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.NYX_MAIN;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_IBC_TRANSFER;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_SIMPLE_SEND;
import static wannabit.io.cosmostaion.base.BaseConstant.EMONEY_COIN_IMG_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.KAVA_COIN_IMG_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HARD;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_SWP;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
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

import com.google.common.collect.Lists;
import com.squareup.picasso.Picasso;

import java.math.BigDecimal;
import java.util.List;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.txs.common.SendActivity;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.chains.ChainFactory;
import wannabit.io.cosmostaion.dialog.AlertDialogUtils;
import wannabit.io.cosmostaion.dialog.Dialog_AccountShow;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WUtil;
import wannabit.io.cosmostaion.widget.tokenDetail.TokenDetailSupportHolder;
import wannabit.io.cosmostaion.widget.tokenDetail.VestingHolder;

public class NativeTokenGrpcActivity extends BaseActivity implements View.OnClickListener {

    private Toolbar mToolbar;
    private ImageView mToolbarSymbolImg;
    private TextView mToolbarSymbol, mToolbarChannel;
    private TextView mItemPerPrice;
    private ImageView mItemUpDownImg;
    private TextView mItemUpDownPrice;

    private CardView mBtnAddressPopup;
    private ImageView mKeyState;
    private TextView mAddress;
    private TextView mTotalValue;

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private NativeTokenGrpcAdapter mAdapter;

    private RelativeLayout mBtnIbcSend;
    private RelativeLayout mBtnBep3Send;
    private RelativeLayout mBtnSend;

    private String mNativeGrpcDenom;

    private int mDivideDecimal = 6;
    private Boolean mHasVesting = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_token_detail);
        mToolbar = findViewById(R.id.tool_bar);
        mToolbarSymbolImg = findViewById(R.id.toolbar_symbol_img);
        mToolbarSymbol = findViewById(R.id.toolbar_symbol);
        mToolbarChannel = findViewById(R.id.toolbar_channel);
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
        mBtnBep3Send = findViewById(R.id.btn_bep3_send);
        mBtnSend = findViewById(R.id.btn_send);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mAccount = getBaseDao().onSelectAccount(getBaseDao().getLastUser());
        mBaseChain = BaseChain.getChain(mAccount.baseChain);
        mChainConfig = ChainFactory.getChain(mBaseChain);
        mNativeGrpcDenom = getIntent().getStringExtra("denom");
        mToolbarChannel.setVisibility(View.GONE);

        if (mBaseChain.equals(KAVA_MAIN)) {
            if (getBaseDao().onParseRemainVestingsByDenom(mNativeGrpcDenom).size() > 0) {
                mHasVesting = true;
            }
            if (WUtil.isBep3Coin(mNativeGrpcDenom)) {
                mBtnBep3Send.setVisibility(View.VISIBLE);
                mBtnIbcSend.setVisibility(View.GONE);
            }
        }

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new NativeTokenGrpcAdapter();
        mRecyclerView.setAdapter(mAdapter);

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                onUpdateView();
            }
        });

        onUpdateView();
        mBtnAddressPopup.setOnClickListener(this);
        mBtnSend.setOnClickListener(this);
        mBtnIbcSend.setOnClickListener(this);
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
        if (mBaseChain.equals(BaseChain.OSMOSIS_MAIN)) {
            mToolbarSymbolImg.setImageResource(R.drawable.token_ion);
            mToolbarSymbol.setTextColor(ContextCompat.getColor(NativeTokenGrpcActivity.this, R.color.colorIon));

        } else if (mBaseChain.equals(BaseChain.EMONEY_MAIN)) {
            mToolbarSymbol.setText(mNativeGrpcDenom.toUpperCase());
            Picasso.get().load(EMONEY_COIN_IMG_URL + mNativeGrpcDenom + ".png").fit().placeholder(R.drawable.token_default).error(R.drawable.token_default).into(mToolbarSymbolImg);

        } else if (mBaseChain.equals(BaseChain.KAVA_MAIN)) {
            mToolbarSymbol.setText(mNativeGrpcDenom.toUpperCase());
            Picasso.get().load(KAVA_COIN_IMG_URL + mNativeGrpcDenom + ".png").fit().placeholder(R.drawable.token_default).error(R.drawable.token_default).into(mToolbarSymbolImg);

        } else if (mBaseChain.equals(CRESCENT_MAIN)) {
            mToolbarSymbolImg.setImageDrawable(ContextCompat.getDrawable(NativeTokenGrpcActivity.this, R.drawable.token_bcre));
            mToolbarSymbol.setTextColor(ContextCompat.getColor(NativeTokenGrpcActivity.this, R.color.color_crescent2));

        } else if (mBaseChain.equals(NYX_MAIN)) {
            mToolbarSymbolImg.setImageDrawable(ContextCompat.getDrawable(NativeTokenGrpcActivity.this, R.drawable.token_nym));
            mToolbarSymbol.setText(R.string.str_nym_c);
            mToolbarSymbol.setTextColor(ContextCompat.getColor(NativeTokenGrpcActivity.this, R.color.color_nym));
        }
        mToolbarSymbol.setText(WDp.getDisplaySymbol(getBaseDao(), mChainConfig, mNativeGrpcDenom));
        mTotalValue.setText(WDp.dpUserCurrencyValue(getBaseDao(), mNativeGrpcDenom, getBaseDao().getAvailable(mNativeGrpcDenom), mDivideDecimal));

        mItemPerPrice.setText(WDp.dpPerUserCurrencyValue(getBaseDao(), mNativeGrpcDenom));
        mItemUpDownPrice.setText(WDp.dpValueChange(getBaseDao(), mNativeGrpcDenom));
        final BigDecimal lastUpDown = WDp.valueChange(getBaseDao(), mNativeGrpcDenom);
        if (lastUpDown.compareTo(BigDecimal.ZERO) > 0) {
            mItemUpDownImg.setVisibility(View.VISIBLE);
            mItemUpDownImg.setImageDrawable(ContextCompat.getDrawable(NativeTokenGrpcActivity.this, R.drawable.ic_price_up));
        } else if (lastUpDown.compareTo(BigDecimal.ZERO) < 0) {
            mItemUpDownImg.setVisibility(View.VISIBLE);
            mItemUpDownImg.setImageDrawable(ContextCompat.getDrawable(NativeTokenGrpcActivity.this, R.drawable.ic_price_down));
        } else {
            mItemUpDownImg.setVisibility(View.INVISIBLE);
        }

        mBtnAddressPopup.setCardBackgroundColor(ContextCompat.getColor(NativeTokenGrpcActivity.this, mChainConfig.chainBgColor()));
        mAddress.setText(mAccount.address);
        isAccountKey(mKeyState);
        mSwipeRefreshLayout.setRefreshing(false);
    }


    @Override
    public void onClick(View v) {
        if (v.equals(mBtnAddressPopup)) {
            Bundle bundle = new Bundle();
            bundle.putString("address", mAccount.address);
            if (TextUtils.isEmpty(mAccount.nickName)) {
                bundle.putString("title", getString(R.string.str_my_wallet) + mAccount.id);
            } else {
                bundle.putString("title", mAccount.nickName);
            }
            Dialog_AccountShow show = Dialog_AccountShow.newInstance(bundle);
            show.setCancelable(true);
            getSupportFragmentManager().beginTransaction().add(show, "dialog").commitNowAllowingStateLoss();

        } else if (v.equals(mBtnIbcSend)) {
            if (!mAccount.hasPrivateKey) {
                onInsertKeyDialog();
            }

            final BigDecimal feeAmount = WUtil.getEstimateGasFeeAmount(this, mBaseChain, CONST_PW_TX_IBC_TRANSFER, 0);

            List<String> availableFeeDenomList = Lists.newArrayList();
            for (String denom : WDp.getGasDenomList(mBaseChain)) {
                if (getBaseDao().getAvailable(denom).compareTo(feeAmount) >= 0) {
                    availableFeeDenomList.add(denom);
                }
            }
            if (availableFeeDenomList.isEmpty()) {
                Toast.makeText(getBaseContext(), R.string.error_not_enough_budget, Toast.LENGTH_SHORT).show();
                return;
            }

            AlertDialogUtils.showSingleButtonDialog(this, getString(R.string.str_ibc_warning_c),
                    Html.fromHtml(getString(R.string.str_ibc_warning_msg1) + "<br><br>" + getString(R.string.str_ibc_warning_msg2)),
                    Html.fromHtml("<font color=\"#007AFF\">" + getString(R.string.str_ibc_continue_c) + "</font>"), view -> onCheckIbcTransfer(mNativeGrpcDenom));

        } else if (v.equals(mBtnSend)) {
            if (!mAccount.hasPrivateKey) {
                onInsertKeyDialog();
            }
            Intent intent = new Intent(getBaseContext(), SendActivity.class);
            BigDecimal feeAmount = WUtil.getEstimateGasFeeAmount(getBaseContext(), mBaseChain, CONST_PW_TX_SIMPLE_SEND, 0);

            List<String> availableFeeDenomList = Lists.newArrayList();
            for (String denom : WDp.getGasDenomList(mBaseChain)) {
                if (getBaseDao().getAvailable(denom).compareTo(feeAmount) >= 0) {
                    availableFeeDenomList.add(denom);
                }
            }
            if (availableFeeDenomList.isEmpty()) {
                Toast.makeText(getBaseContext(), R.string.error_not_enough_budget, Toast.LENGTH_SHORT).show();
                return;
            }

            intent.putExtra("sendTokenDenom", mNativeGrpcDenom);
            startActivity(intent);

        } else if (v.equals(mBtnBep3Send)) {
            onStartHTLCSendActivity(mNativeGrpcDenom);
        }

    }

    private class NativeTokenGrpcAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private static final int TYPE_UNKNOWN = -1;
        private static final int TYPE_NATIVE = 0;

        private static final int TYPE_VESTING = 99;

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
            if (viewType == TYPE_UNKNOWN) {

            } else if (viewType == TYPE_NATIVE) {
                return new TokenDetailSupportHolder(getLayoutInflater().inflate(R.layout.item_amount_detail, viewGroup, false));

            } else if (viewType == TYPE_VESTING) {
                return new VestingHolder(getLayoutInflater().inflate(R.layout.layout_vesting_schedule, viewGroup, false));

            }
            return null;
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
            if (getItemViewType(position) == TYPE_NATIVE) {
                TokenDetailSupportHolder holder = (TokenDetailSupportHolder) viewHolder;
                holder.onBindNativeTokengRPC(NativeTokenGrpcActivity.this, mBaseChain, getBaseDao(), mNativeGrpcDenom);

            } else if (getItemViewType(position) == TYPE_VESTING) {
                VestingHolder holder = (VestingHolder) viewHolder;
                holder.onBindTokenHolder(getBaseContext(), mBaseChain, getBaseDao(), mNativeGrpcDenom);
            }
        }

        @Override
        public int getItemCount() {
            if (mHasVesting) { return 2; }
            return 1;
        }

        @Override
        public int getItemViewType(int position) {
            if (mBaseChain.equals(KAVA_MAIN)) {
                if (mNativeGrpcDenom.equalsIgnoreCase(TOKEN_HARD) || mNativeGrpcDenom.equalsIgnoreCase(TOKEN_SWP)) {
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
            return TYPE_UNKNOWN;
        }
    }
}
