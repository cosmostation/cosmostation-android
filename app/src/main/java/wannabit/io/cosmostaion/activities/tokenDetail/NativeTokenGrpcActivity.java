package wannabit.io.cosmostaion.activities.tokenDetail;

import static wannabit.io.cosmostaion.base.BaseChain.KAVA_MAIN;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_IBC_TRANSFER;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_SIMPLE_SEND;
import static wannabit.io.cosmostaion.base.BaseConstant.EMONEY_COIN_IMG_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.KAVA_COIN_IMG_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HARD;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_ION;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_SWP;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_USDX;

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

import com.squareup.picasso.Picasso;

import java.math.BigDecimal;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.SendActivity;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import com.fulldive.wallet.presentation.accounts.AccountShowDialogFragment;
import wannabit.io.cosmostaion.dialog.Dialog_IBC_Send_Warning;
import wannabit.io.cosmostaion.dialog.Dialog_WatchMode;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WUtil;
import wannabit.io.cosmostaion.widget.tokenDetail.TokenDetailSupportHolder;
import wannabit.io.cosmostaion.widget.tokenDetail.VestingHolder;

public class NativeTokenGrpcActivity extends BaseActivity implements View.OnClickListener {

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
    private RelativeLayout mBtnBep3Send;
    private RelativeLayout mBtnSend;

    private NativeTokenGrpcAdapter mAdapter;
    private String mNativeGrpcDenom;

    private int mDivideDecimal = 6;
    private BigDecimal mTotalAmount = BigDecimal.ZERO;

    private Boolean mHasVesting = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_token_detail_native);

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
        mBtnIbcSend = findViewById(R.id.btn_ibc_send);
        mBtnBep3Send = findViewById(R.id.btn_bep3_send);
        mBtnSend = findViewById(R.id.btn_send);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mAccount = getBaseDao().onSelectAccount(getBaseDao().getLastUser());
        mBaseChain = BaseChain.getChain(mAccount.baseChain);
        mNativeGrpcDenom = getIntent().getStringExtra("denom");

        if (mBaseChain.equals(KAVA_MAIN)) {
            if (getBaseDao().onParseRemainVestingsByDenom(mNativeGrpcDenom).size() > 0) {
                mHasVesting = true;
            }
            if (WUtil.isBep3Coin(mNativeGrpcDenom)) {
                mBtnBep3Send.setVisibility(View.VISIBLE);
            }
        }

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new NativeTokenGrpcAdapter();
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
        mBtnAddressPopup.setCardBackgroundColor(WDp.getChainBgColor(NativeTokenGrpcActivity.this, mBaseChain));
        mBtnIbcSend.setVisibility(View.VISIBLE);
        if (mBaseChain.equals(BaseChain.OSMOSIS_MAIN)) {
            WUtil.DpOsmosisTokenImg(getBaseDao(), mToolbarSymbolImg, mNativeGrpcDenom);
            mToolbarSymbol.setTextColor(getResources().getColor(R.color.colorIon));
            mToolbarSymbol.setText(getString(R.string.str_uion_c));
            if (mNativeGrpcDenom.equalsIgnoreCase(TOKEN_ION)) {
                mDivideDecimal = 6;

                mTotalAmount = getBaseDao().getAvailable(mNativeGrpcDenom);
            }

        } else if (mBaseChain.equals(BaseChain.EMONEY_MAIN)) {
            mToolbarSymbol.setText(mNativeGrpcDenom.toUpperCase());
            Picasso.get().load(EMONEY_COIN_IMG_URL + mNativeGrpcDenom + ".png").fit().placeholder(R.drawable.token_ic).error(R.drawable.token_ic).into(mToolbarSymbolImg);
            mTotalAmount = getBaseDao().getAvailable(mNativeGrpcDenom);

        } else if (mBaseChain.equals(BaseChain.KAVA_MAIN)) {
            if (mNativeGrpcDenom.equalsIgnoreCase(TOKEN_HARD)) {
                mToolbarSymbol.setTextColor(getResources().getColor(R.color.colorHard));
                mBtnAddressPopup.setCardBackgroundColor(getResources().getColor(R.color.colorTransBghard));
            } else if (mNativeGrpcDenom.equalsIgnoreCase(TOKEN_USDX)) {
                mToolbarSymbol.setTextColor(getResources().getColor(R.color.colorUsdx));
                mBtnAddressPopup.setCardBackgroundColor(getResources().getColor(R.color.colorTransBgusdx));
            } else if (mNativeGrpcDenom.equalsIgnoreCase(TOKEN_SWP)) {
                mToolbarSymbol.setTextColor(getResources().getColor(R.color.colorSwp));
                mBtnAddressPopup.setCardBackgroundColor(getResources().getColor(R.color.colorTransBgswp));
            }
            mToolbarSymbol.setText(mNativeGrpcDenom.toUpperCase());
            Picasso.get().load(KAVA_COIN_IMG_URL + mNativeGrpcDenom + ".png").fit().placeholder(R.drawable.token_ic).error(R.drawable.token_ic).into(mToolbarSymbolImg);
            mTotalAmount = getBaseDao().getAvailable(mNativeGrpcDenom);
            if (WUtil.isBep3Coin(mNativeGrpcDenom)) {
                mBtnIbcSend.setVisibility(View.GONE);
            }
        }

        mItemPerPrice.setText(WDp.dpPerUserCurrencyValue(getBaseDao(), mNativeGrpcDenom));
        mItemUpDownPrice.setText(WDp.dpValueChange(getBaseDao(), mNativeGrpcDenom));
        final BigDecimal lastUpDown = WDp.valueChange(getBaseDao(), mNativeGrpcDenom);
        if (lastUpDown.compareTo(BigDecimal.ZERO) > 0) {
            mItemUpDownImg.setVisibility(View.VISIBLE);
            mItemUpDownImg.setImageDrawable(getResources().getDrawable(R.drawable.ic_price_up));
        } else if (lastUpDown.compareTo(BigDecimal.ZERO) < 0) {
            mItemUpDownImg.setVisibility(View.VISIBLE);
            mItemUpDownImg.setImageDrawable(getResources().getDrawable(R.drawable.ic_price_down));
        } else {
            mItemUpDownImg.setVisibility(View.INVISIBLE);
        }

        mAddress.setText(mAccount.address);
        mTotalValue.setText(WDp.dpUserCurrencyValue(getBaseDao(), mNativeGrpcDenom, mTotalAmount, mDivideDecimal));
        mKeyState.setColorFilter(ContextCompat.getColor(getBaseContext(), R.color.colorGray0), android.graphics.PorterDuff.Mode.SRC_IN);
        if (mAccount.hasPrivateKey) {
            mKeyState.setColorFilter(WDp.getChainColor(getBaseContext(), mBaseChain), android.graphics.PorterDuff.Mode.SRC_IN);
        }
        mSwipeRefreshLayout.setRefreshing(false);
    }


    @Override
    public void onClick(View v) {
        if (v.equals(mBtnAddressPopup)) {
            AccountShowDialogFragment show = AccountShowDialogFragment.Companion.newInstance(
                    mAccount.getAccountTitle(this),
                    mAccount.address
            );
            show.setCancelable(true);
            getSupportFragmentManager().beginTransaction().add(show, "dialog").commitNowAllowingStateLoss();

        } else if (v.equals(mBtnIbcSend)) {
            if (!mAccount.hasPrivateKey) {
                Dialog_WatchMode add = Dialog_WatchMode.newInstance();
                add.setCancelable(true);
                getSupportFragmentManager().beginTransaction().add(add, "dialog").commitNowAllowingStateLoss();
                return;
            }
            final String mainDenom = mBaseChain.getMainDenom();
            final BigDecimal feeAmount = WUtil.getEstimateGasFeeAmount(this, mBaseChain, CONST_PW_TX_IBC_TRANSFER, 0);

            mTotalAmount = getBaseDao().getAvailable(mNativeGrpcDenom);
            if (mainDenom.equalsIgnoreCase(mNativeGrpcDenom)) {
                mTotalAmount = mTotalAmount.subtract(feeAmount);
            }
            if (mTotalAmount.compareTo(BigDecimal.ZERO) <= 0) {
                Toast.makeText(getBaseContext(), R.string.error_not_enough_budget, Toast.LENGTH_SHORT).show();
                return;
            }
            Bundle bundle = new Bundle();
            bundle.putString("sendTokenDenom", mNativeGrpcDenom);
            Dialog_IBC_Send_Warning warning = Dialog_IBC_Send_Warning.newInstance(bundle);
            warning.setCancelable(true);
            getSupportFragmentManager().beginTransaction().add(warning, "dialog").commitNowAllowingStateLoss();

        } else if (v.equals(mBtnSend)) {
            if (!mAccount.hasPrivateKey) {
                Dialog_WatchMode add = Dialog_WatchMode.newInstance();
                add.setCancelable(true);
                getSupportFragmentManager().beginTransaction().add(add, "dialog").commitNowAllowingStateLoss();
                return;
            }
            Intent intent = new Intent(getBaseContext(), SendActivity.class);
            BigDecimal mainAvailable = getBaseDao().getAvailable(mBaseChain.getMainDenom());
            BigDecimal feeAmount = WUtil.getEstimateGasFeeAmount(getBaseContext(), mBaseChain, CONST_PW_TX_SIMPLE_SEND, 0);
            if (mainAvailable.compareTo(feeAmount) < 0) {
                Toast.makeText(getBaseContext(), R.string.error_not_enough_fee, Toast.LENGTH_SHORT).show();
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
        private static final int TYPE_HISTORY = 100;

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
            if (viewType == TYPE_NATIVE) {
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
            if (mHasVesting) {
                return 2;
            }
            return 1;
        }

        @Override
        public int getItemViewType(int position) {
            if (mBaseChain.equals(KAVA_MAIN)) {
                if (mNativeGrpcDenom.equalsIgnoreCase(TOKEN_HARD) || mNativeGrpcDenom.equalsIgnoreCase(TOKEN_SWP)) {
                    if (mHasVesting) {
                        if (position == 0) return TYPE_NATIVE;
                        if (position == 1) return TYPE_VESTING;
                        else return TYPE_HISTORY;
                    } else {
                        if (position == 0) return TYPE_NATIVE;
                        else return TYPE_HISTORY;
                    }
                } else {
                    if (position == 0) return TYPE_NATIVE;
                    else return TYPE_HISTORY;
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
