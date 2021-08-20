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
import wannabit.io.cosmostaion.dialog.Dialog_AccountShow;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WKey;

import static wannabit.io.cosmostaion.base.BaseChain.OKEX_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.OK_TEST;

public class POOLTokenDetailActivity extends BaseActivity implements View.OnClickListener{

    private Toolbar             mToolbar;
    private ImageView           mToolbarSymbolImg;
    private TextView            mToolbarSymbol;

    private CardView            mBtnAddressPopup;
    private ImageView           mKeyState;
    private TextView            mAddress;
    private TextView            mTotalValue;

    private SwipeRefreshLayout  mSwipeRefreshLayout;
    private RecyclerView        mRecyclerView;

    private RelativeLayout      mBtnSend;

    private POOlTokenAdapter    mAdapter;
    private String              shareAddress;
    private String              mMainDenom;
    private String              mCoinDenom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_token_detail_staking);

        mToolbar                = findViewById(R.id.tool_bar);
        mToolbarSymbolImg       = findViewById(R.id.toolbar_symbol_img);
        mToolbarSymbol          = findViewById(R.id.toolbar_symbol);

        mBtnAddressPopup        = findViewById(R.id.card_root);
        mKeyState               = findViewById(R.id.img_account);
        mAddress                = findViewById(R.id.account_Address);
        mTotalValue             = findViewById(R.id.total_value);
        mSwipeRefreshLayout     = findViewById(R.id.layer_refresher);
        mRecyclerView           = findViewById(R.id.recycler);
        mBtnSend                = findViewById(R.id.btn_send);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mAccount    = getBaseDao().onSelectAccount(getBaseDao().getLastUser());
        mBaseChain  = BaseChain.getChain(mAccount.baseChain);
        mMainDenom  = WDp.mainDenom(mBaseChain);
        mCoinDenom  = getIntent().getStringExtra("denom");

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
        mToolbarSymbolImg.setImageDrawable(getResources().getDrawable(R.drawable.token_pool));
        String [] split = mCoinDenom.split("/");
        mToolbarSymbol.setText("GAMM-" + split[split.length - 1]);
        mToolbarSymbol.setTextColor(getResources().getColor(R.color.colorWhite));

        mBtnAddressPopup.setBackgroundColor(WDp.getChainBgColor(POOLTokenDetailActivity.this, mBaseChain));
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

        } else if (v.equals(mBtnSend)) {
//            onStartSendMainDenom();
            Toast.makeText(getBaseContext(), R.string.error_prepare, Toast.LENGTH_SHORT).show();
            return;
        }
    }

    private class POOlTokenAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private static final int TYPE_AMOUNT                        = 0;
        private static final int TYPE_HISTORY                       = 2;

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
            if(viewType == TYPE_AMOUNT) {
                return new POOlTokenAdapter.AmountHolder(getLayoutInflater().inflate(R.layout.item_amount_detail, viewGroup, false));
            }
            return null;
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
            if (getItemViewType(position) == TYPE_AMOUNT) {
                onBindAmount(viewHolder);
            }
        }

        @Override
        public int getItemCount() {
            return 1;
        }

        @Override
        public int getItemViewType(int position) {
            if (position == 0) {
                return TYPE_AMOUNT;
            } else {
                return TYPE_HISTORY;
            }
        }

        private void onBindAmount(RecyclerView.ViewHolder viewHolder) {
            final POOlTokenAdapter.AmountHolder holder = (POOlTokenAdapter.AmountHolder) viewHolder;
            final BigDecimal totalAmount = getBaseDao().getAllMainAsset(mMainDenom);

            holder.itemTotal.setText("" + WDp.getDpAmount2(POOLTokenDetailActivity.this, totalAmount, 6, 6));
            holder.itemAvailable.setText("" + WDp.getDpAmount2(POOLTokenDetailActivity.this, getBaseDao().getAvailable(mCoinDenom), 18, 18));
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
    }
}
