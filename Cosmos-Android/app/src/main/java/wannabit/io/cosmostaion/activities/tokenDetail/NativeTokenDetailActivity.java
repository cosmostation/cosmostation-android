package wannabit.io.cosmostaion.activities.tokenDetail;

import android.content.Intent;
import android.os.Bundle;
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

import com.squareup.picasso.Picasso;

import java.math.BigDecimal;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.SendActivity;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.dao.BnbToken;
import wannabit.io.cosmostaion.dao.OkToken;
import wannabit.io.cosmostaion.dialog.Dialog_AccountShow;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WKey;
import wannabit.io.cosmostaion.utils.WLog;
import wannabit.io.cosmostaion.utils.WUtil;
import wannabit.io.cosmostaion.widget.tokenDetail.TokenDetailSupportHolder;
import wannabit.io.cosmostaion.widget.tokenDetail.VestingHolder;

import static wannabit.io.cosmostaion.base.BaseChain.BNB_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.BNB_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.KAVA_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.KAVA_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.OKEX_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.OK_TEST;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_SIMPLE_SEND;
import static wannabit.io.cosmostaion.base.BaseConstant.KAVA_COIN_IMG_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.OKEX_COIN_IMG_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_BNB;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HARD;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_IMG_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_KAVA;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_OK;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_SWP;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_USDX;

public class NativeTokenDetailActivity extends BaseActivity implements View.OnClickListener {

    private Toolbar                         mToolbar;
    private ImageView                       mToolbarSymbolImg;
    private TextView                        mToolbarSymbol;
    private TextView                        mItemPerPrice;
    private ImageView                       mItemUpDownImg;
    private TextView                        mItemUpDownPrice;

    private CardView                        mBtnAddressPopup;
    private ImageView                       mKeyState;
    private TextView                        mTotalValue;
    private TextView                        mAddress;
    private SwipeRefreshLayout              mSwipeRefreshLayout;
    private RecyclerView                    mRecyclerView;

    private RelativeLayout                  mBtnIbcSend;
    private RelativeLayout                  mBtnBep3Send;
    private RelativeLayout                  mBtnSend;

    private NativeTokenAdapter              mAdapter;
    private String                          mDenom;

    private Boolean                         mHasVesting = false;
    private String                          shareAddress;

    private int                             mDivideDecimal = 6;
    private int                             mDisplayDecimal = 6;
    private BigDecimal                      mTotalAmount = BigDecimal.ZERO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_token_detail_native);

        mToolbar                = findViewById(R.id.tool_bar);
        mToolbarSymbolImg       = findViewById(R.id.toolbar_symbol_img);
        mToolbarSymbol          = findViewById(R.id.toolbar_symbol);
        mItemPerPrice           = findViewById(R.id.per_price);
        mItemUpDownImg          = findViewById(R.id.ic_price_updown);
        mItemUpDownPrice        = findViewById(R.id.dash_price_updown_tx);

        mBtnAddressPopup        = findViewById(R.id.card_root);
        mKeyState               = findViewById(R.id.img_account);
        mAddress                = findViewById(R.id.account_Address);
        mTotalValue             = findViewById(R.id.total_value);
        mSwipeRefreshLayout     = findViewById(R.id.layer_refresher);
        mRecyclerView           = findViewById(R.id.recycler);
        mBtnIbcSend             = findViewById(R.id.btn_ibc_send);
        mBtnBep3Send            = findViewById(R.id.btn_bep3_send);
        mBtnSend                = findViewById(R.id.btn_send);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mAccount = getBaseDao().onSelectAccount(getBaseDao().getLastUser());
        mBaseChain = BaseChain.getChain(mAccount.baseChain);
        mDenom = getIntent().getStringExtra("denom");

        if (mBaseChain.equals(KAVA_MAIN) || mBaseChain.equals(KAVA_TEST)) {
            if (getBaseDao().mKavaAccount.value.getCalcurateVestingCntByDenom(mDenom) > 0) { mHasVesting = true; }
            if (WUtil.isBep3Coin(mDenom)) { mBtnBep3Send.setVisibility(View.VISIBLE); }

        } else if (mBaseChain.equals(BNB_MAIN) || mBaseChain.equals(BNB_TEST)) {
            if (WUtil.isBep3Coin(mDenom)) { mBtnBep3Send.setVisibility(View.VISIBLE); }

        }

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new NativeTokenAdapter();
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
        mBtnAddressPopup.setBackgroundColor(WDp.getChainBgColor(NativeTokenDetailActivity.this, mBaseChain));
        if (mBaseChain.equals(KAVA_MAIN) || mBaseChain.equals(KAVA_TEST)) {
            BigDecimal totalAmount = getBaseDao().availableAmount(mDenom);
            totalAmount = totalAmount.add(getBaseDao().lockedAmount(mDenom));
            String baseDenom = WDp.getKavaBaseDenom(mDenom);
            int kavaDecimal = WUtil.getKavaCoinDecimal(mDenom);
            Picasso.get().load(KAVA_COIN_IMG_URL + mDenom + ".png").fit().placeholder(R.drawable.token_ic).error(R.drawable.token_ic).into(mToolbarSymbolImg);
            if (mDenom.equalsIgnoreCase(TOKEN_HARD)) {
                mToolbarSymbol.setTextColor(getResources().getColor(R.color.colorHard));
                mBtnAddressPopup.setBackgroundColor(getResources().getColor(R.color.colorTransBghard));
            } else if (mDenom.equalsIgnoreCase(TOKEN_USDX)) {
                mToolbarSymbol.setTextColor(getResources().getColor(R.color.colorUsdx));
                mBtnAddressPopup.setBackgroundColor(getResources().getColor(R.color.colorTransBgusdx));
            } else if (mDenom.equalsIgnoreCase(TOKEN_SWP)) {
                mToolbarSymbol.setTextColor(getResources().getColor(R.color.colorSwp));
                mBtnAddressPopup.setBackgroundColor(getResources().getColor(R.color.colorTransBgswp));
            }
            mToolbarSymbol.setText(mDenom.toUpperCase());
            mTotalValue.setText(WDp.dpUserCurrencyValue(getBaseDao(), baseDenom, totalAmount, kavaDecimal));

            mItemPerPrice.setText(WDp.dpPerUserCurrencyValue(getBaseDao(), baseDenom));
            mItemUpDownPrice.setText(WDp.dpValueChange(getBaseDao(), baseDenom));
            final BigDecimal lastUpDown = WDp.valueChange(getBaseDao(), baseDenom);
            if (lastUpDown.compareTo(BigDecimal.ZERO) > 0) {
                mItemUpDownImg.setVisibility(View.VISIBLE);
                mItemUpDownImg.setImageDrawable(getResources().getDrawable(R.drawable.ic_price_up));
            } else if (lastUpDown.compareTo(BigDecimal.ZERO) < 0) {
                mItemUpDownImg.setVisibility(View.VISIBLE);
                mItemUpDownImg.setImageDrawable(getResources().getDrawable(R.drawable.ic_price_down));
            } else {
                mItemUpDownImg.setVisibility(View.INVISIBLE);
            }

        } else if (mBaseChain.equals(OKEX_MAIN) || mBaseChain.equals(OK_TEST)) {
            final OkToken okToken = getBaseDao().okToken(mDenom);
            Picasso.get().load(OKEX_COIN_IMG_URL + okToken.original_symbol + ".png").placeholder(R.drawable.token_ic).error(R.drawable.token_ic).fit().into(mToolbarSymbolImg);
            mToolbarSymbol.setText(okToken.original_symbol.toUpperCase());
            mToolbarSymbol.setTextColor(getResources().getColor(R.color.colorWhite));

            BigDecimal convertedOktAmount = WDp.convertTokenToOkt(getBaseDao(), mDenom);
            mTotalValue.setText(WDp.dpUserCurrencyValue(getBaseDao(), TOKEN_OK, convertedOktAmount, 0));

            if (okToken.original_symbol.equalsIgnoreCase("okb")) {
                mItemPerPrice.setText(WDp.dpPerUserCurrencyValue(getBaseDao(), "okb"));
                mItemUpDownPrice.setText(WDp.dpValueChange(getBaseDao(), "okb"));
                final BigDecimal lastUpDown = WDp.valueChange(getBaseDao(), "okb");
                if (lastUpDown.compareTo(BigDecimal.ZERO) > 0) {
                    mItemUpDownImg.setVisibility(View.VISIBLE);
                    mItemUpDownImg.setImageDrawable(getResources().getDrawable(R.drawable.ic_price_up));
                } else if (lastUpDown.compareTo(BigDecimal.ZERO) < 0) {
                    mItemUpDownImg.setVisibility(View.VISIBLE);
                    mItemUpDownImg.setImageDrawable(getResources().getDrawable(R.drawable.ic_price_down));
                } else {
                    mItemUpDownImg.setVisibility(View.INVISIBLE);
                }
            } else {
                mItemPerPrice.setText("");
                mItemUpDownPrice.setText("");
                mItemUpDownImg.setVisibility(View.INVISIBLE);
            }

        } else if (mBaseChain.equals(BNB_MAIN) || mBaseChain.equals(BNB_TEST)) {
            final BigDecimal amount = getBaseDao().getAllBnbTokenAmount(mDenom);
            final BnbToken bnbToken = getBaseDao().getBnbToken(mDenom);
            Picasso.get().load(TOKEN_IMG_URL+bnbToken.original_symbol+".png").placeholder(R.drawable.token_ic).error(R.drawable.token_ic).fit().into(mToolbarSymbolImg);
            mToolbarSymbol.setText(bnbToken.original_symbol.toUpperCase());
            mToolbarSymbol.setTextColor(getResources().getColor(R.color.colorWhite));

            BigDecimal convertedBnbAmount = WUtil.getBnbConvertAmount(getBaseDao(), mDenom, amount);
            mTotalValue.setText(WDp.dpUserCurrencyValue(getBaseDao(), TOKEN_BNB, convertedBnbAmount, 0));

            mItemPerPrice.setText(WUtil.dpBnbTokenUserCurrencyPrice(getBaseDao(), mDenom));
            mItemUpDownPrice.setText("");
            mItemUpDownImg.setVisibility(View.INVISIBLE);
        }

        if (mBaseChain.equals(OKEX_MAIN) || mBaseChain.equals(OK_TEST)) {
            try {
                shareAddress = WKey.convertAddressOkexToEth(mAccount.address);
                mAddress.setText(shareAddress);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            shareAddress = mAccount.address;
            mAddress.setText(shareAddress);
        }
        mKeyState.setColorFilter(ContextCompat.getColor(getBaseContext(), R.color.colorGray0), android.graphics.PorterDuff.Mode.SRC_IN);
        if (mAccount.hasPrivateKey) {
            mKeyState.setColorFilter(WDp.getChainColor(getBaseContext(), mBaseChain), android.graphics.PorterDuff.Mode.SRC_IN);
        }
        mSwipeRefreshLayout.setRefreshing(false);
    }


    @Override
    public void onClick(View v) {
        if (v.equals(mBtnAddressPopup)) {
            Bundle bundle = new Bundle();
            bundle.putString("address", shareAddress);
            if (TextUtils.isEmpty(mAccount.nickName)) { bundle.putString("title", getString(R.string.str_my_wallet) + mAccount.id); }
            else { bundle.putString("title", mAccount.nickName); }
            Dialog_AccountShow show = Dialog_AccountShow.newInstance(bundle);
            show.setCancelable(true);
            getSupportFragmentManager().beginTransaction().add(show, "dialog").commitNowAllowingStateLoss();

        } else if (v.equals(mBtnIbcSend)) {
            Toast.makeText(getBaseContext(), R.string.error_prepare, Toast.LENGTH_SHORT).show();
            return;

        } else if (v.equals(mBtnBep3Send)) {
            onStartHTLCSendActivity(mDenom);

        } else if (v.equals(mBtnSend)) {
            Intent intent = new Intent(getBaseContext(), SendActivity.class);
            BigDecimal mainAvailable = getBaseDao().availableAmount(WDp.mainDenom(mBaseChain));
            BigDecimal feeAmount = WUtil.getEstimateGasFeeAmount(getBaseContext(), mBaseChain, CONST_PW_TX_SIMPLE_SEND, 0);
            if (mainAvailable.compareTo(feeAmount) < 0) {
                Toast.makeText(getBaseContext(), R.string.error_not_enough_fee, Toast.LENGTH_SHORT).show();
                return;
            }
            intent.putExtra("sendTokenDenom", mDenom);
            startActivity(intent);
        }

    }

    private class NativeTokenAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private static final int TYPE_UNKNOWN               = -1;
        private static final int TYPE_KAVA                  = 0;
        private static final int TYPE_BNB                   = 1;
        private static final int TYPE_OKEX                  = 2;

        private static final int TYPE_VESTING               = 99;
        private static final int TYPE_HISTORY               = 100;

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
            if (viewType == TYPE_UNKNOWN) {

            } else if (viewType == TYPE_KAVA) {
                return new TokenDetailSupportHolder(getLayoutInflater().inflate(R.layout.item_amount_detail, viewGroup, false));

            } else if (viewType == TYPE_BNB) {
                return new TokenDetailSupportHolder(getLayoutInflater().inflate(R.layout.item_amount_detail, viewGroup, false));

            } else if (viewType == TYPE_OKEX) {
                return new TokenDetailSupportHolder(getLayoutInflater().inflate(R.layout.item_amount_detail, viewGroup, false));
            }

            else if (viewType == TYPE_VESTING) {
                return new VestingHolder(getLayoutInflater().inflate(R.layout.layout_vesting_schedule, viewGroup, false));
            }

//            } else if (viewType == TYPE_HISTORY) {
//                return new HistoryHolder(getLayoutInflater().inflate(R.layout.item_history, viewGroup, false));
//            }
            return null;
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
            if (getItemViewType(position) == TYPE_KAVA) {
                TokenDetailSupportHolder holder = (TokenDetailSupportHolder) viewHolder;
                holder.onBindKavaToken(NativeTokenDetailActivity.this, getBaseDao(), mDenom);

            } else if (getItemViewType(position) == TYPE_BNB) {
                TokenDetailSupportHolder holder = (TokenDetailSupportHolder) viewHolder;
                holder.onBindBNBTokens(NativeTokenDetailActivity.this, getBaseDao(), mDenom);

            } else if (getItemViewType(position) == TYPE_OKEX) {
                TokenDetailSupportHolder holder = (TokenDetailSupportHolder) viewHolder;
                holder.onBindOKTokens(NativeTokenDetailActivity.this, getBaseDao(), mDenom);
            }

            else if (getItemViewType(position) == TYPE_VESTING) {
                VestingHolder holder = (VestingHolder) viewHolder;
                holder.onBindTokenHolder(getBaseContext(), mBaseChain, getBaseDao(), mDenom);
//
//            } else if (getItemViewType(position) == TYPE_HISTORY) {
//
//            } else if (getItemViewType(position) == TYPE_UNKNOWN) {
            }

        }

        @Override
        public int getItemCount() {
            if (mHasVesting) {
                return 2;
            }
            return 1;
//            int cnt = 1;
//            if (mApiTxCustomHistory != null) {
//                cnt = cnt + mApiTxCustomHistory.size();
//            }
//            if (mApiTxHistory != null) {
//                cnt = cnt + mApiTxHistory.size();
//            }
//            if (mHasVesting) {
//                cnt = cnt + 1;
//            }
//            return cnt;
        }

        @Override
        public int getItemViewType(int position) {
            if (mBaseChain.equals(KAVA_MAIN) || mBaseChain.equals(KAVA_TEST)) {
                if (mDenom.equalsIgnoreCase(TOKEN_HARD) || mDenom.equalsIgnoreCase(TOKEN_SWP)) {
                    if (mHasVesting) {
                        if (position == 0) return TYPE_KAVA;
                        if (position == 1) return TYPE_VESTING;
                        else return TYPE_HISTORY;
                    } else {
                        if (position == 0) return TYPE_KAVA;
                        else return TYPE_HISTORY;
                    }
                } else {
                    if (position == 0) return TYPE_KAVA;
                    else return TYPE_HISTORY;
                }

            } else if (mBaseChain.equals(BNB_MAIN) || mBaseChain.equals(BNB_TEST)) {
                if (position == 0) return TYPE_BNB;
                else return TYPE_HISTORY;

            } else if (mBaseChain.equals(OKEX_MAIN) || mBaseChain.equals(OK_TEST)) {
                if (position == 0) return TYPE_OKEX;
                else return TYPE_HISTORY;
            }
            return TYPE_UNKNOWN;
        }
    }
}
