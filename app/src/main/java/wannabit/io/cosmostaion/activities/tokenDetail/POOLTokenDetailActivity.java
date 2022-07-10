package wannabit.io.cosmostaion.activities.tokenDetail;

import static wannabit.io.cosmostaion.base.BaseChain.COSMOS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.CRESCENT_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.INJ_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.OSMOSIS_MAIN;

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

import java.math.BigDecimal;

import tendermint.liquidity.v1beta1.Liquidity;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.dialog.Dialog_AccountShow;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WUtil;
import wannabit.io.cosmostaion.widget.tokenDetail.TokenDetailSupportHolder;

public class POOLTokenDetailActivity extends BaseActivity implements View.OnClickListener {

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

    private POOlTokenAdapter mAdapter;
    private String mPoolDenom;

    private int mDivideDecimal = 18;
    private int mDisplayDecimal = 18;
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
        mBtnIbcSend = findViewById(R.id.btn_ibc_send);
        mBtnSend = findViewById(R.id.btn_send);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mAccount = getBaseDao().onSelectAccount(getBaseDao().getLastUser());
        mBaseChain = BaseChain.getChain(mAccount.baseChain);
        mPoolDenom = getIntent().getStringExtra("denom");

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new POOlTokenAdapter();
        mRecyclerView.setAdapter(mAdapter);

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
        if (mBaseChain.equals(OSMOSIS_MAIN)) {
            WUtil.DpOsmosisTokenImg(getBaseDao(), mToolbarSymbolImg, mPoolDenom);
            String[] split = mPoolDenom.split("/");
            mToolbarSymbol.setText("GAMM-" + split[split.length - 1]);
            mToolbarSymbol.setTextColor(ContextCompat.getColor(POOLTokenDetailActivity.this, R.color.colorBlackDayNight));

            mDivideDecimal = 18;
            mDisplayDecimal = 18;
            mTotalAmount = getBaseDao().getAvailable(mPoolDenom);
            mTotalValue.setText(WDp.dpUserCurrencyValue(getBaseDao(), mPoolDenom, mTotalAmount, mDivideDecimal));

            mBtnIbcSend.setVisibility(View.VISIBLE);

        } else if (mBaseChain.equals(COSMOS_MAIN)) {
            WUtil.DpCosmosTokenImg(getBaseDao(), mToolbarSymbolImg, mPoolDenom);
            Liquidity.Pool poolInfo = getBaseDao().getGravityPoolByDenom(mPoolDenom);
            if (poolInfo != null) {
                mToolbarSymbol.setText("GDEX-" + poolInfo.getId());
                mToolbarSymbol.setTextColor(ContextCompat.getColor(POOLTokenDetailActivity.this, R.color.colorBlackDayNight));
            }

            mDivideDecimal = 6;
            mDisplayDecimal = 6;
            mTotalAmount = getBaseDao().getAvailable(mPoolDenom);
            mTotalValue.setText(WDp.dpUserCurrencyValue(getBaseDao(), mPoolDenom, mTotalAmount, mDivideDecimal));

            mBtnIbcSend.setVisibility(View.VISIBLE);

        } else if (mBaseChain.equals(INJ_MAIN)) {
            mToolbarSymbolImg.setImageResource(R.drawable.token_ic);
            mToolbarSymbol.setText("SHARE" + mPoolDenom.substring(5));
            mToolbarSymbol.setTextColor(ContextCompat.getColor(POOLTokenDetailActivity.this, R.color.colorBlackDayNight));

            mDivideDecimal = 18;
            mDisplayDecimal = 18;
            mTotalAmount = getBaseDao().getAvailable(mPoolDenom);
            mTotalValue.setText(WDp.dpUserCurrencyValue(getBaseDao(), mPoolDenom, mTotalAmount, mDivideDecimal));

            mBtnIbcSend.setVisibility(View.VISIBLE);

        } else if (mBaseChain.equals(CRESCENT_MAIN)) {
            mToolbarSymbolImg.setImageResource(R.drawable.token_crescentpool);
            mToolbarSymbol.setText(mPoolDenom.toUpperCase());
            mToolbarSymbol.setTextColor(ContextCompat.getColor(POOLTokenDetailActivity.this, R.color.colorBlackDayNight));

            mDivideDecimal = 12;
            mDisplayDecimal = 12;
            mTotalAmount = getBaseDao().getAvailable(mPoolDenom);
            mTotalValue.setText(WDp.dpUserCurrencyValue(getBaseDao(), mPoolDenom, mTotalAmount, mDivideDecimal));

            mBtnIbcSend.setVisibility(View.VISIBLE);
        }

        mItemPerPrice.setText(WDp.dpPerUserCurrencyValue(getBaseDao(), mPoolDenom));
        mItemUpDownPrice.setText(WDp.dpValueChange(getBaseDao(), mPoolDenom));
        final BigDecimal lastUpDown = WDp.valueChange(getBaseDao(), mPoolDenom);
        if (lastUpDown.compareTo(BigDecimal.ZERO) > 0) {
            mItemUpDownImg.setVisibility(View.VISIBLE);
            mItemUpDownImg.setImageDrawable(ContextCompat.getDrawable(POOLTokenDetailActivity.this, R.drawable.ic_price_up));
        } else if (lastUpDown.compareTo(BigDecimal.ZERO) < 0) {
            mItemUpDownImg.setVisibility(View.VISIBLE);
            mItemUpDownImg.setImageDrawable(ContextCompat.getDrawable(POOLTokenDetailActivity.this, R.drawable.ic_price_down));
        } else {
            mItemUpDownImg.setVisibility(View.INVISIBLE);
        }

        mBtnAddressPopup.setCardBackgroundColor(WDp.getChainBgColor(POOLTokenDetailActivity.this, mBaseChain));
        mAddress.setText(mAccount.address);
        mKeyState.setColorFilter(ContextCompat.getColor(getBaseContext(), R.color.colorGray0), android.graphics.PorterDuff.Mode.SRC_IN);
        if (mAccount.hasPrivateKey) {
            mKeyState.setImageResource(R.drawable.key_off);
            mKeyState.setColorFilter(WDp.getChainColor(this, mBaseChain), android.graphics.PorterDuff.Mode.SRC_IN);
        } else {
            mKeyState.setImageResource(R.drawable.watchmode);
            mKeyState.setColorFilter(R.color.colorTransBg, android.graphics.PorterDuff.Mode.SRC_IN);
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

        } else if (v.equals(mBtnSend)) {
            Toast.makeText(POOLTokenDetailActivity.this, getString(R.string.error_prepare), Toast.LENGTH_SHORT).show();
            return;
//            Intent intent = new Intent(getBaseContext(), SendActivity.class);
//            BigDecimal mainAvailable = getBaseDao().getAvailable(WDp.mainDenom(mBaseChain));
//            BigDecimal feeAmount = WUtil.getEstimateGasFeeAmount(getBaseContext(), mBaseChain, CONST_PW_TX_SIMPLE_SEND, 0);
//            if (mainAvailable.compareTo(feeAmount) < 0) {
//                Toast.makeText(getBaseContext(), R.string.error_not_enough_fee, Toast.LENGTH_SHORT).show();
//                return;
//            }
//            intent.putExtra("sendTokenDenom", mPoolDenom);
//            startActivity(intent);
        } else if (v.equals(mBtnIbcSend)) {
            Toast.makeText(POOLTokenDetailActivity.this, getString(R.string.error_prepare), Toast.LENGTH_SHORT).show();
            return;
        }
    }

    private class POOlTokenAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private static final int TYPE_UNKNOWN = -1;
        private static final int TYPE_POOL_TOKEN = 0;

        private static final int TYPE_HISTORY = 100;

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
            if (viewType == TYPE_UNKNOWN) {

            } else if (viewType == TYPE_POOL_TOKEN) {
                return new TokenDetailSupportHolder(getLayoutInflater().inflate(R.layout.item_amount_detail, viewGroup, false));
            }

//            } else if (viewType == TYPE_HISTORY) {
//                return new HistoryHolder(getLayoutInflater().inflate(R.layout.item_history, viewGroup, false));
//            }
            return null;
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
            if (getItemViewType(position) == TYPE_POOL_TOKEN) {
                TokenDetailSupportHolder holder = (TokenDetailSupportHolder) viewHolder;
                holder.onBindPoolToken(POOLTokenDetailActivity.this, mBaseChain, getBaseDao(), mPoolDenom);
            }
//
//            } else if (getItemViewType(position) == TYPE_HISTORY) {
//
//            } else if (getItemViewType(position) == TYPE_UNKNOWN) {
        }

        @Override
        public int getItemCount() {
            return 1;
        }

        @Override
        public int getItemViewType(int position) {
            if (position == 0) return TYPE_POOL_TOKEN;
            else return TYPE_HISTORY;
        }
    }
}
