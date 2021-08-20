package wannabit.io.cosmostaion.activities.tokenDetail;

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
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.dao.IbcToken;
import wannabit.io.cosmostaion.dialog.Dialog_AccountShow;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WKey;

import static wannabit.io.cosmostaion.base.BaseChain.OKEX_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.OK_TEST;

public class IBCTokenDetailActivity extends BaseActivity implements View.OnClickListener{

    private Toolbar                         mToolbar;
    private ImageView                       mToolbarSymbolImg;
    private TextView                        mToolbarSymbol;

    private CardView                        mBtnAddressPopup;
    private ImageView                       mKeyState;
    private TextView                        mAddress;
    private TextView                        mTotalValue;

    private SwipeRefreshLayout              mSwipeRefreshLayout;
    private RecyclerView                    mRecyclerView;

    private RelativeLayout                  mBtnIbcSend;
    private RelativeLayout                  mBtnSend;

    private IBCTokenAdapter                 mAdapter;
    private String                          shareAddress;
    private String                          mMainDenom;
    private String                          mCoinDenom;
    private IbcToken                        mIbcToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ibc_token_detail);
        mToolbar                = findViewById(R.id.tool_bar);
        mToolbarSymbolImg       = findViewById(R.id.toolbar_symbol_img);
        mToolbarSymbol          = findViewById(R.id.toolbar_symbol);

        mBtnAddressPopup        = findViewById(R.id.card_root);
        mKeyState               = findViewById(R.id.img_account);
        mAddress                = findViewById(R.id.account_Address);
        mTotalValue             = findViewById(R.id.total_value);
        mSwipeRefreshLayout     = findViewById(R.id.layer_refresher);
        mRecyclerView           = findViewById(R.id.recycler);
        mBtnIbcSend             = findViewById(R.id.btn_ibc_send);
        mBtnSend                = findViewById(R.id.btn_send);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mAccount    = getBaseDao().onSelectAccount(getBaseDao().getLastUser());
        mBaseChain  = BaseChain.getChain(mAccount.baseChain);
        mMainDenom  = WDp.mainDenom(mBaseChain);
        mCoinDenom  = getIntent().getStringExtra("denom");
        mIbcToken   = getBaseDao().getIbcToken(mCoinDenom);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new IBCTokenAdapter();
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
        if (mIbcToken == null) {
            mToolbarSymbolImg.setImageDrawable(getResources().getDrawable(R.drawable.token_default_ibc));
            mToolbarSymbol.setText("Unknown");
            mToolbarSymbol.setTextColor(getResources().getColor(R.color.colorWhite));

        } else {
            if (mIbcToken.auth) {
                try {
                    Picasso.get().load(mIbcToken.moniker).fit().placeholder(R.drawable.token_default_ibc).error(R.drawable.token_default_ibc).into(mToolbarSymbolImg);
                } catch (Exception e){}
                mToolbarSymbol.setText(mIbcToken.display_denom.toUpperCase());
                mToolbarSymbol.setTextColor(getResources().getColor(R.color.colorWhite));
            } else {
                mToolbarSymbolImg.setImageDrawable(getResources().getDrawable(R.drawable.token_default_ibc));
                mToolbarSymbol.setText("Unknown");
                mToolbarSymbol.setTextColor(getResources().getColor(R.color.colorWhite));
            }
        }

        mBtnAddressPopup.setBackgroundColor(WDp.getChainBgColor(IBCTokenDetailActivity.this, mBaseChain));
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
        mTotalValue.setText(WDp.dpAllAssetValueUserCurrency(mBaseChain, getBaseDao()));
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

        } else if (v.equals(mBtnSend)) {
            onStartSendMainDenom();
        }
    }

    private class IBCTokenAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private static final int TYPE_AMOUNT                        = 0;
        private static final int TYPE_IBC_STATUS                    = 1;
        private static final int TYPE_HISTORY                       = 2;

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
            if(viewType == TYPE_AMOUNT) {
                return new AmountHolder(getLayoutInflater().inflate(R.layout.item_ibc_amount_detail, viewGroup, false));
            } else if(viewType == TYPE_IBC_STATUS) {
                return new IbcStatusHolder(getLayoutInflater().inflate(R.layout.item_ibc_token_status, viewGroup, false));
            }
            return null;
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
            if (getItemViewType(position) == TYPE_AMOUNT) {
                onBindAmount(viewHolder);
            } else if (getItemViewType(position) == TYPE_IBC_STATUS) {
                onBindIbcInfo(viewHolder);
            }
        }

        @Override
        public int getItemCount() {
            return 2;
        }

        @Override
        public int getItemViewType(int position) {
            if (position == 0) {
                return TYPE_AMOUNT;
            } else if (position == 1) {
                return TYPE_IBC_STATUS;
            } else {
                return TYPE_HISTORY;
            }
        }

        private void onBindAmount(RecyclerView.ViewHolder viewHolder) {
            final AmountHolder holder = (IBCTokenAdapter.AmountHolder) viewHolder;
            final BigDecimal availableAmount = getBaseDao().getAvailable(mMainDenom);
            final BigDecimal delegateAmount = getBaseDao().getDelegationSum();
            final BigDecimal unbondingAmount = getBaseDao().getUndelegationSum();
            final BigDecimal rewardAmount = getBaseDao().getRewardSum(mMainDenom);
            final BigDecimal totalAmount = availableAmount.add(delegateAmount).add(unbondingAmount).add(rewardAmount);

            holder.itemTotal.setText("" + WDp.getDpAmount2(IBCTokenDetailActivity.this, totalAmount, 6, 6));
            holder.itemAvailable.setText("" + WDp.getDpAmount2(IBCTokenDetailActivity.this, getBaseDao().getAvailable(mCoinDenom), 6, 6));
        }

        private void onBindIbcInfo(RecyclerView.ViewHolder viewHolder) {
            final IbcStatusHolder holder = (IBCTokenAdapter.IbcStatusHolder) viewHolder;
            if (mIbcToken.auth) {
                holder.itemIbcInfo.setText("Authed");
                holder.itemIbcInfo.setTextColor(getResources().getColor(R.color.colorAuth));
            } else {
                holder.itemIbcInfo.setText("UnKnown");
                holder.itemIbcInfo.setTextColor(getResources().getColor(R.color.colorGray5));
            }
            holder.itemOriginDenom.setText(mIbcToken.base_denom);
            holder.itemChainId.setText(mIbcToken.counter_party.chain_id);
            holder.itemRelayer.setText("");
            holder.itemDenom.setText("");
        }

        public class AmountHolder extends RecyclerView.ViewHolder {
            private TextView            itemTotal;
            private TextView            itemAvailable;

            public AmountHolder(View v) {
                super(v);
                itemTotal               = itemView.findViewById(R.id.ibc_total_amount);
                itemAvailable           = itemView.findViewById(R.id.ibc_available_amount);
            }
        }

        public class IbcStatusHolder extends RecyclerView.ViewHolder {
            private TextView            itemIbcInfo;
            private TextView            itemOriginDenom;
            private TextView            itemChainId;
            private TextView            itemRelayer;
            private TextView            itemDenom;

            public IbcStatusHolder(View v) {
                super(v);
                itemIbcInfo             = itemView.findViewById(R.id.ibc_info);
                itemOriginDenom         = itemView.findViewById(R.id.ibc_info_origin_denom);
                itemChainId             = itemView.findViewById(R.id.ibc_chain_id);
                itemRelayer             = itemView.findViewById(R.id.ibc_relayer);
                itemDenom               = itemView.findViewById(R.id.ibc_denom);
            }
        }
    }
}
