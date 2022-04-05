package wannabit.io.cosmostaion.activities.tokenDetail;

import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_EXECUTE_CONTRACT;

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
import wannabit.io.cosmostaion.activities.chains.contract.SendContractActivity;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.dao.Cw20Assets;
import wannabit.io.cosmostaion.dialog.Dialog_WatchMode;
import com.fulldive.wallet.presentation.accounts.AccountShowDialogFragment;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WUtil;
import wannabit.io.cosmostaion.widget.tokenDetail.TokenDetailSupportHolder;

public class ContractTokenGrpcActivity extends BaseActivity implements View.OnClickListener {

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
    private RelativeLayout mBtnSend;

    private Cw20TokenGrpcAdapter mAdapter;

    private Cw20Assets mCw20Asset;
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
        mCw20Asset = getIntent().getParcelableExtra("cw20Asset");
        mBtnIbcSend.setVisibility(View.VISIBLE);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new Cw20TokenGrpcAdapter();
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
        mBtnAddressPopup.setCardBackgroundColor(WDp.getChainBgColor(ContractTokenGrpcActivity.this, baseChain));
        if (mCw20Asset != null) {
            Picasso.get().load(mCw20Asset.logo).fit().placeholder(R.drawable.token_ic).error(R.drawable.token_ic).into(mToolbarSymbolImg);
            mToolbarSymbol.setText(mCw20Asset.denom.toUpperCase());
            mToolbarSymbol.setTextColor(getResources().getColor(R.color.colorWhite));
            mTotalAmount = mCw20Asset.getAmount();

            mItemPerPrice.setText(WDp.dpPerUserCurrencyValue(getBaseDao(), mCw20Asset.denom));
            mItemUpDownPrice.setText(WDp.dpValueChange(getBaseDao(), mCw20Asset.denom));
            final BigDecimal lastUpDown = WDp.valueChange(getBaseDao(), mCw20Asset.denom);
            if (lastUpDown.compareTo(BigDecimal.ZERO) > 0) {
                mItemUpDownImg.setVisibility(View.VISIBLE);
                mItemUpDownImg.setImageDrawable(getResources().getDrawable(R.drawable.ic_price_up));
            } else if (lastUpDown.compareTo(BigDecimal.ZERO) < 0) {
                mItemUpDownImg.setVisibility(View.VISIBLE);
                mItemUpDownImg.setImageDrawable(getResources().getDrawable(R.drawable.ic_price_down));
            } else {
                mItemUpDownImg.setVisibility(View.INVISIBLE);
            }

            mAddress.setText(account.address);
            mKeyState.setColorFilter(ContextCompat.getColor(getBaseContext(), R.color.colorGray0), android.graphics.PorterDuff.Mode.SRC_IN);
            if (account.hasPrivateKey) {
                mKeyState.setColorFilter(WDp.getChainColor(getBaseContext(), baseChain), android.graphics.PorterDuff.Mode.SRC_IN);
            }
            mTotalValue.setText(WDp.dpUserCurrencyValue(getBaseDao(), mCw20Asset.denom, mTotalAmount, mCw20Asset.decimal));
            mSwipeRefreshLayout.setRefreshing(false);
        }
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
            Toast.makeText(getBaseContext(), R.string.error_prepare, Toast.LENGTH_SHORT).show();
            return;

        } else if (v.equals(mBtnSend)) {
            if (!account.hasPrivateKey) {
                Dialog_WatchMode add = Dialog_WatchMode.newInstance();
                showDialog(add);
                return;
            }
            Intent intent = new Intent(getBaseContext(), SendContractActivity.class);
            BigDecimal mainAvailable = getBaseDao().getAvailable(baseChain.getMainDenom());
            BigDecimal feeAmount = WUtil.getEstimateGasFeeAmount(getBaseContext(), baseChain, CONST_PW_TX_EXECUTE_CONTRACT, 0);
            if (mainAvailable.compareTo(feeAmount) < 0) {
                Toast.makeText(getBaseContext(), R.string.error_not_enough_fee, Toast.LENGTH_SHORT).show();
                return;
            }
            intent.putExtra("mCw20SendContract", mCw20Asset.contract_address);
            startActivity(intent);
        }
    }

    private class Cw20TokenGrpcAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private static final int TYPE_UNKNOWN = -1;
        private static final int TYPE_CW20 = 0;

        private static final int TYPE_HISTORY = 100;

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
            if (viewType == TYPE_UNKNOWN) {

            } else if (viewType == TYPE_CW20) {
                return new TokenDetailSupportHolder(getLayoutInflater().inflate(R.layout.item_amount_detail, viewGroup, false));
            }
            return null;
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
            if (getItemViewType(position) == TYPE_CW20) {
                TokenDetailSupportHolder holder = (TokenDetailSupportHolder) viewHolder;
                holder.onBindCw20Token(ContractTokenGrpcActivity.this, baseChain, getBaseDao(), mCw20Asset);
            }
        }

        @Override
        public int getItemCount() {
            return 1;
        }

        @Override
        public int getItemViewType(int position) {
            if (position == 0) return TYPE_CW20;
            else return TYPE_HISTORY;
        }
    }
}
