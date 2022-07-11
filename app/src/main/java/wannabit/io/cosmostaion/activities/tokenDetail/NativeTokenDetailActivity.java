package wannabit.io.cosmostaion.activities.tokenDetail;

import static wannabit.io.cosmostaion.base.BaseChain.BNB_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.KAVA_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.OKEX_MAIN;
import static wannabit.io.cosmostaion.base.BaseConstant.BINANCE_TOKEN_IMG_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_SIMPLE_SEND;
import static wannabit.io.cosmostaion.base.BaseConstant.OKEX_COIN_IMG_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_BNB;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_OK;

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

import com.squareup.picasso.Picasso;

import java.math.BigDecimal;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.txs.common.SendActivity;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.dao.BnbToken;
import wannabit.io.cosmostaion.dao.OkToken;
import wannabit.io.cosmostaion.dialog.AlertDialogUtils;
import wannabit.io.cosmostaion.dialog.Dialog_AccountShow;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WUtil;
import wannabit.io.cosmostaion.widget.tokenDetail.TokenDetailSupportHolder;
import wannabit.io.cosmostaion.widget.tokenDetail.VestingHolder;

public class NativeTokenDetailActivity extends BaseActivity implements View.OnClickListener {

    private Toolbar mToolbar;
    private ImageView mToolbarSymbolImg;
    private TextView mToolbarSymbol;
    private TextView mItemPerPrice;
    private ImageView mItemUpDownImg;
    private TextView mItemUpDownPrice;

    private CardView mBtnAddressPopup;
    private ImageView mKeyState;
    private TextView mTotalValue;
    private TextView mAddress;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;

    private RelativeLayout mBtnIbcSend;
    private RelativeLayout mBtnBep3Send;
    private RelativeLayout mBtnSend;

    private NativeTokenAdapter mAdapter;
    private String mDenom;

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
        mDenom = getIntent().getStringExtra("denom");

        if (mBaseChain.equals(BNB_MAIN)) {
            if (WUtil.isBep3Coin(mDenom)) {
                mBtnBep3Send.setVisibility(View.VISIBLE);
            }
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
        mBtnAddressPopup.setCardBackgroundColor(WDp.getChainBgColor(NativeTokenDetailActivity.this, mBaseChain));
        if (mBaseChain.equals(OKEX_MAIN)) {
            final OkToken okToken = getBaseDao().okToken(mDenom);
            Picasso.get().load(OKEX_COIN_IMG_URL + okToken.original_symbol + ".png").placeholder(R.drawable.token_default).error(R.drawable.token_default).fit().into(mToolbarSymbolImg);
            mToolbarSymbol.setText(okToken.original_symbol.toUpperCase());
            mToolbarSymbol.setTextColor(ContextCompat.getColor(NativeTokenDetailActivity.this, R.color.colorBlackDayNight));

            BigDecimal convertedOktAmount = WDp.convertTokenToOkt(getBaseDao(), mDenom);
            mTotalValue.setText(WDp.dpUserCurrencyValue(getBaseDao(), TOKEN_OK, convertedOktAmount, 0));

            if (okToken.original_symbol.equalsIgnoreCase("okb")) {
                mItemPerPrice.setText(WDp.dpPerUserCurrencyValue(getBaseDao(), "okb"));
                mItemUpDownPrice.setText(WDp.dpValueChange(getBaseDao(), "okb"));
                final BigDecimal lastUpDown = WDp.valueChange(getBaseDao(), "okb");
                if (lastUpDown.compareTo(BigDecimal.ZERO) > 0) {
                    mItemUpDownImg.setVisibility(View.VISIBLE);
                    mItemUpDownImg.setImageDrawable(ContextCompat.getDrawable(NativeTokenDetailActivity.this, R.drawable.ic_price_up));
                } else if (lastUpDown.compareTo(BigDecimal.ZERO) < 0) {
                    mItemUpDownImg.setVisibility(View.VISIBLE);
                    mItemUpDownImg.setImageDrawable(ContextCompat.getDrawable(NativeTokenDetailActivity.this, R.drawable.ic_price_down));
                } else {
                    mItemUpDownImg.setVisibility(View.INVISIBLE);
                }
            } else {
                mItemPerPrice.setText("");
                mItemUpDownPrice.setText("");
                mItemUpDownImg.setVisibility(View.INVISIBLE);
            }

        } else if (mBaseChain.equals(BNB_MAIN)) {
            final BigDecimal amount = getBaseDao().getAllBnbTokenAmount(mDenom);
            final BnbToken bnbToken = getBaseDao().getBnbToken(mDenom);
            Picasso.get().load(BINANCE_TOKEN_IMG_URL + bnbToken.original_symbol + ".png").placeholder(R.drawable.token_default).error(R.drawable.token_default).fit().into(mToolbarSymbolImg);
            mToolbarSymbol.setText(bnbToken.original_symbol.toUpperCase());
            mToolbarSymbol.setTextColor(ContextCompat.getColor(NativeTokenDetailActivity.this, R.color.colorBlackDayNight));

            BigDecimal convertedBnbAmount = WUtil.getBnbConvertAmount(getBaseDao(), mDenom, amount);
            mTotalValue.setText(WDp.dpUserCurrencyValue(getBaseDao(), TOKEN_BNB, convertedBnbAmount, 0));

            mItemPerPrice.setText(WUtil.dpBnbTokenUserCurrencyPrice(getBaseDao(), mDenom));
            mItemUpDownPrice.setText("");
            mItemUpDownImg.setVisibility(View.INVISIBLE);
        }

        mAddress.setText(mAccount.address);
        mKeyState.setColorFilter(ContextCompat.getColor(getBaseContext(), R.color.colorGray0), android.graphics.PorterDuff.Mode.SRC_IN);
        if (mAccount.hasPrivateKey) {
            mKeyState.setImageResource(R.drawable.key_off);
            mKeyState.setColorFilter(WDp.getChainColor(this, mBaseChain), android.graphics.PorterDuff.Mode.SRC_IN);
        } else {
            mKeyState.setImageResource(R.drawable.watchmode);
            mKeyState.setColorFilter(null);
        }
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
            Toast.makeText(getBaseContext(), R.string.error_prepare, Toast.LENGTH_SHORT).show();
            return;

        } else if (v.equals(mBtnBep3Send)) {
            onStartHTLCSendActivity(mDenom);

        } else if (v.equals(mBtnSend)) {
            if (!mAccount.hasPrivateKey) {
                AlertDialogUtils.showDoubleButtonDialog(this, getString(R.string.str_only_observe_title), getString(R.string.str_only_observe_msg),
                        Html.fromHtml("<font color=\"#9C6CFF\">" + getString(R.string.str_add_mnemonics) + "</font>"), view -> onAddMnemonicForAccount(),
                        getString(R.string.str_close), null);
                return;
            }
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
        private static final int TYPE_UNKNOWN = -1;
        private static final int TYPE_NATIVE = 0;

        private static final int TYPE_VESTING = 99;
        private static final int TYPE_HISTORY = 100;

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
            if (viewType == TYPE_UNKNOWN) {

            } else if (viewType == TYPE_NATIVE) {
                return new TokenDetailSupportHolder(getLayoutInflater().inflate(R.layout.item_amount_detail, viewGroup, false));

            } else if (viewType == TYPE_VESTING) {
                return new VestingHolder(getLayoutInflater().inflate(R.layout.layout_vesting_schedule, viewGroup, false));
            }

//            } else if (viewType == TYPE_HISTORY) {
//                return new HistoryHolder(getLayoutInflater().inflate(R.layout.item_history, viewGroup, false));
//            }
            return null;
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
            if (getItemViewType(position) == TYPE_NATIVE) {
                TokenDetailSupportHolder holder = (TokenDetailSupportHolder) viewHolder;
                if (mBaseChain.equals(KAVA_MAIN)) {
                    holder.onBindKavaToken(NativeTokenDetailActivity.this, getBaseDao(), mDenom);
                } else if (mBaseChain.equals(BNB_MAIN)) {
                    holder.onBindBNBTokens(NativeTokenDetailActivity.this, getBaseDao(), mDenom);
                } else if (mBaseChain.equals(OKEX_MAIN)) {
                    holder.onBindOKTokens(NativeTokenDetailActivity.this, getBaseDao(), mDenom);
                }

            } else if (getItemViewType(position) == TYPE_VESTING) {
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
        }

        @Override
        public int getItemViewType(int position) {
            if (mBaseChain.equals(BNB_MAIN)) {
                if (position == 0) return TYPE_NATIVE;
                else return TYPE_HISTORY;

            } else if (mBaseChain.equals(OKEX_MAIN)) {
                if (position == 0) return TYPE_NATIVE;
                else return TYPE_HISTORY;
            }
            return TYPE_UNKNOWN;
        }
    }
}
