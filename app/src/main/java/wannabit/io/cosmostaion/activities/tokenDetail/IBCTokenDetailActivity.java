package wannabit.io.cosmostaion.activities.tokenDetail;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
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
import wannabit.io.cosmostaion.base.chains.ChainConfig;
import wannabit.io.cosmostaion.base.chains.ChainFactory;
import wannabit.io.cosmostaion.dao.IbcToken;
import wannabit.io.cosmostaion.dialog.AlertDialogUtils;
import wannabit.io.cosmostaion.utils.WDp;

public class IBCTokenDetailActivity extends BaseActivity implements View.OnClickListener {

    private Toolbar mToolbar;
    private ImageView mToolbarSymbolImg;
    private TextView mToolbarSymbol;
    private TextView mToolbarChannel;
    private TextView mItemPerPrice;
    private ImageView mItemUpDownImg;
    private TextView mItemUpDownPrice;

    private CardView mBtnAddressPopup;
    private ImageView mKeyState;
    private TextView mAddress, mEthAddress;
    private TextView mTotalValue;

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private IBCTokenAdapter mAdapter;

    private RelativeLayout mBtnIbcSend;
    private RelativeLayout mBtnSend;

    private String mIbcDenom;
    private IbcToken mIbcToken;
    private int mIbcDecimal = 6;

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
        mEthAddress = findViewById(R.id.eth_address);
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
        mChainConfig = ChainFactory.getChain(mBaseChain);
        mIbcDenom = getIntent().getStringExtra("denom");
        mIbcToken = getBaseDao().getIbcToken(mIbcDenom);

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                onUpdateView();
            }
        });

        if (mIbcToken != null) {
            mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
            mRecyclerView.setHasFixedSize(true);
            mAdapter = new IBCTokenAdapter();
            mRecyclerView.setAdapter(mAdapter);

            onUpdateView();
        } else {
            onForceBack();
        }
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
        final String baseDenom = getBaseDao().getBaseDenom(mChainConfig, mIbcDenom);
        mIbcDecimal = WDp.getDenomDecimal(getBaseDao(), mChainConfig, mIbcDenom);
        if (mIbcToken != null) {
            WDp.setDpSymbolImg(getBaseDao(), mChainConfig, mIbcDenom, mToolbarSymbolImg);
            WDp.setDpSymbol(this, getBaseDao(), mChainConfig, mIbcDenom, mToolbarSymbol);
            mToolbarChannel.setText("(" + mIbcToken.channel_id + ")");

            if (mIbcToken.auth) {
                mTotalValue.setText(WDp.dpUserCurrencyValue(getBaseDao(), baseDenom, getBaseDao().getAvailable(mIbcDenom), mIbcDecimal));

                mItemPerPrice.setText(WDp.dpPerUserCurrencyValue(getBaseDao(), baseDenom));
                mItemUpDownPrice.setText(WDp.dpValueChange(getBaseDao(), baseDenom));
                final BigDecimal lastUpDown = WDp.valueChange(getBaseDao(), baseDenom);
                if (lastUpDown.compareTo(BigDecimal.ZERO) > 0) {
                    mItemUpDownImg.setVisibility(View.VISIBLE);
                    mItemUpDownImg.setImageResource(R.drawable.ic_price_up);
                } else if (lastUpDown.compareTo(BigDecimal.ZERO) < 0) {
                    mItemUpDownImg.setVisibility(View.VISIBLE);
                    mItemUpDownImg.setImageResource(R.drawable.ic_price_down);
                } else {
                    mItemUpDownImg.setVisibility(View.INVISIBLE);
                }

            } else {
                mToolbarSymbolImg.setImageResource(R.drawable.token_default_ibc);
                mItemPerPrice.setText("");
                mItemUpDownPrice.setText("");
                mItemUpDownImg.setVisibility(View.INVISIBLE);
            }
        }

        mBtnAddressPopup.setCardBackgroundColor(ContextCompat.getColor(IBCTokenDetailActivity.this, mChainConfig.chainBgColor()));
        setAccountKeyStatus(this, mAccount, mChainConfig, mKeyState);
        mAddress.setText(mAccount.address);
        setEthAddress(mChainConfig, mEthAddress);
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mBtnAddressPopup)) {
            onClickQrCopy(mChainConfig, mAccount);

        } else if (v.equals(mBtnIbcSend)) {
            if (!mAccount.hasPrivateKey) {
                onInsertKeyDialog();
                return;
            }
            if (!WDp.isTxFeePayable(this, getBaseDao(), mChainConfig)) {
                Toast.makeText(this, R.string.error_not_enough_fee, Toast.LENGTH_SHORT).show();
                return;
            }

            AlertDialogUtils.showSingleButtonDialog(this, getString(R.string.str_ibc_warning_c),
                    Html.fromHtml(getString(R.string.str_ibc_warning_msg1) + "<br><br>" + getString(R.string.str_ibc_warning_msg2)),
                    Html.fromHtml("<font color=\"#007AFF\">" + getString(R.string.str_ibc_continue_c) + "</font>"), view -> onCheckIbcTransfer(mIbcDenom));

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
            intent.putExtra("sendTokenDenom", mIbcDenom);
            startActivity(intent);
        }
    }

    private void onForceBack() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                onBackPressed();
            }
        }, 250);
    }

    private class IBCTokenAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
            return new IbcStatusHolder(getLayoutInflater().inflate(R.layout.item_ibc_token_status, viewGroup, false));
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
            final IbcStatusHolder holder = (IBCTokenAdapter.IbcStatusHolder) viewHolder;
            final BigDecimal totalAmount = getBaseDao().getAvailable(mIbcDenom);
            if (mIbcToken.auth) {
                mIbcDecimal = mIbcToken.decimal;
            }
            try {
                Picasso.get().load(getIbcRelayerImg(mChainConfig, mIbcToken.channel_id)).into(holder.itemRelayer);
            } catch (Exception e) { }
            holder.itemCurrentAmount.setText(WDp.getDpAmount2(IBCTokenDetailActivity.this, totalAmount, mIbcDecimal, mIbcDecimal));
            holder.itemCurrentDenom.setText("ibc/" + mIbcToken.hash);

            holder.itemAcrossChain.setText(mIbcToken.counter_party.chain_id);
            holder.itemAcrossDenom.setText(mIbcToken.base_denom);
        }

        @Override
        public int getItemCount() {
            return 1;
        }

        public class IbcStatusHolder extends RecyclerView.ViewHolder {
            private ImageView itemRelayer;
            private TextView itemCurrentAmount;
            private TextView itemCurrentDenom;
            private TextView itemAcrossChain;
            private TextView itemAcrossDenom;

            public IbcStatusHolder(View v) {
                super(v);
                itemRelayer = itemView.findViewById(R.id.img_relayer);
                itemCurrentAmount = itemView.findViewById(R.id.current_amount);
                itemCurrentDenom = itemView.findViewById(R.id.current_denom);
                itemAcrossChain = itemView.findViewById(R.id.across_chain);
                itemAcrossDenom = itemView.findViewById(R.id.across_denom);
            }
        }
    }

    public String getIbcRelayerImg(ChainConfig chainConfig, String channelId) {
        String url = "";
        if (getBaseDao().getIbcPath(channelId).relayer_img != null) {
            url = getBaseDao().getIbcPath(channelId).relayer_img;
        } else {
            url = chainConfig.relayerImgUrl();
        }
        return url;
    }
}
