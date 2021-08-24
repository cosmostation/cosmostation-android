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

import java.math.BigDecimal;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.SendActivity;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.dialog.Dialog_AccountShow;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WKey;
import wannabit.io.cosmostaion.utils.WUtil;
import wannabit.io.cosmostaion.widget.BaseHolder;
import wannabit.io.cosmostaion.widget.HistoryHolder;
import wannabit.io.cosmostaion.widget.tokenDetail.TokenAkashHolder;
import wannabit.io.cosmostaion.widget.tokenDetail.TokenAltheaHolder;
import wannabit.io.cosmostaion.widget.tokenDetail.TokenBnbHolder;
import wannabit.io.cosmostaion.widget.tokenDetail.TokenCosmosHolder;
import wannabit.io.cosmostaion.widget.tokenDetail.TokenCrytoHolder;
import wannabit.io.cosmostaion.widget.tokenDetail.TokenIrisHolder;
import wannabit.io.cosmostaion.widget.tokenDetail.TokenKavaHolder;
import wannabit.io.cosmostaion.widget.tokenDetail.TokenMediblocHolder;
import wannabit.io.cosmostaion.widget.tokenDetail.TokenOKExHolder;
import wannabit.io.cosmostaion.widget.tokenDetail.TokenOsmosisHolder;
import wannabit.io.cosmostaion.widget.tokenDetail.TokenPersisHolder;
import wannabit.io.cosmostaion.widget.tokenDetail.TokenRizonHolder;
import wannabit.io.cosmostaion.widget.tokenDetail.TokenSentinelHolder;
import wannabit.io.cosmostaion.widget.tokenDetail.TokenSifHolder;
import wannabit.io.cosmostaion.widget.tokenDetail.TokenStarnameHolder;
import wannabit.io.cosmostaion.widget.tokenDetail.VestingHolder;

import static wannabit.io.cosmostaion.base.BaseChain.AKASH_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.ALTHEA_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.BNB_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.BNB_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.COSMOS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.COSMOS_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.CRYPTO_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.IOV_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.IRIS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.IRIS_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.KAVA_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.KAVA_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.MEDI_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.MEDI_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.OKEX_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.OK_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.OSMOSIS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.PERSIS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.RIZON_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.SENTINEL_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.SIF_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.isGRPC;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_SIMPLE_SEND;

public class StakingTokenGrpcActivity extends BaseActivity implements View.OnClickListener {

    private Toolbar             mToolbar;
    private ImageView           mToolbarSymbolImg;
    private TextView            mToolbarSymbol;
    private TextView            mItemPerPrice;
    private ImageView           mItemUpDownImg;
    private TextView            mItemUpDownPrice;

    private CardView            mBtnAddressPopup;
    private ImageView           mKeyState;
    private TextView            mAddress;
    private TextView            mTotalValue;

    private SwipeRefreshLayout  mSwipeRefreshLayout;
    private RecyclerView        mRecyclerView;

    private RelativeLayout      mBtnIbcSend;
    private RelativeLayout      mBtnSend;

    private StakingTokenGrpcAdapter         mAdapter;
    private Boolean                         mHasVesting = false;
    private String                          mMainDenom;

    private int                 mDivideDecimal = 6;
    private int                 mDisplayDecimal = 6;
    private BigDecimal          mTotalAmount = BigDecimal.ZERO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_token_detail);

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
        mBtnSend                = findViewById(R.id.btn_send);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mAccount = getBaseDao().onSelectAccount(getBaseDao().getLastUser());
        mBaseChain = BaseChain.getChain(mAccount.baseChain);
        mMainDenom = WDp.mainDenom(mBaseChain);
        mDivideDecimal = WDp.mainDivideDecimal(mBaseChain);
        mDisplayDecimal = WDp.mainDivideDecimal(mBaseChain);

        if (isGRPC(mBaseChain)) {
            if (getBaseDao().onParseRemainVestingsByDenom(WDp.mainDenom(mBaseChain)).size() > 0) { mHasVesting = true; }
            mBtnIbcSend.setVisibility(View.VISIBLE);
        }

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new StakingTokenGrpcAdapter();
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
        WDp.getStakingTokenImg(mBaseChain, mToolbarSymbolImg);
        WDp.DpMainDenom(StakingTokenGrpcActivity.this, mBaseChain, mToolbarSymbol);

        mItemPerPrice.setText(WDp.dpPerUserCurrencyValue(getBaseDao(), mMainDenom));
        mItemUpDownPrice.setText(WDp.dpValueChange(getBaseDao(), mMainDenom));
        final BigDecimal lastUpDown = WDp.valueChange(getBaseDao(), mMainDenom);
        if (lastUpDown.compareTo(BigDecimal.ZERO) > 0) {
            mItemUpDownImg.setVisibility(View.VISIBLE);
            mItemUpDownImg.setImageDrawable(getResources().getDrawable(R.drawable.ic_price_up));
        } else if (lastUpDown.compareTo(BigDecimal.ZERO) < 0) {
            mItemUpDownImg.setVisibility(View.VISIBLE);
            mItemUpDownImg.setImageDrawable(getResources().getDrawable(R.drawable.ic_price_down));
        } else {
            mItemUpDownImg.setVisibility(View.INVISIBLE);
        }

        mBtnAddressPopup.setBackgroundColor(WDp.getChainBgColor(StakingTokenGrpcActivity.this, mBaseChain));
        mAddress.setText(mAccount.address);
        mKeyState.setColorFilter(ContextCompat.getColor(getBaseContext(), R.color.colorGray0), android.graphics.PorterDuff.Mode.SRC_IN);
        if (mAccount.hasPrivateKey) {
            mKeyState.setColorFilter(WDp.getChainColor(getBaseContext(), mBaseChain), android.graphics.PorterDuff.Mode.SRC_IN);
        }
        mTotalAmount = getBaseDao().getAllMainAsset(mMainDenom);
        mTotalValue.setText(WDp.dpUserCurrencyValue(getBaseDao(), mMainDenom, mTotalAmount, mDivideDecimal));
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mBtnAddressPopup)) {
            Bundle bundle = new Bundle();
            bundle.putString("address", mAccount.address);
            if (TextUtils.isEmpty(mAccount.nickName)) { bundle.putString("title", getString(R.string.str_my_wallet) + mAccount.id); }
            else { bundle.putString("title", mAccount.nickName); }
            Dialog_AccountShow show = Dialog_AccountShow.newInstance(bundle);
            show.setCancelable(true);
            getSupportFragmentManager().beginTransaction().add(show, "dialog").commitNowAllowingStateLoss();

        } else if (v.equals(mBtnIbcSend)) {
            Toast.makeText(getBaseContext(), R.string.error_prepare, Toast.LENGTH_SHORT).show();
            return;

        } else if (v.equals(mBtnSend)) {
            Intent intent = new Intent(getBaseContext(), SendActivity.class);
            BigDecimal mainAvailable = getBaseDao().getAvailable(WDp.mainDenom(mBaseChain));
            BigDecimal feeAmount = WUtil.getEstimateGasFeeAmount(getBaseContext(), mBaseChain, CONST_PW_TX_SIMPLE_SEND, 0);
            if (mainAvailable.compareTo(feeAmount) < 0) {
                Toast.makeText(getBaseContext(), R.string.error_not_enough_fee, Toast.LENGTH_SHORT).show();
                return;
            }
            intent.putExtra("sendTokenDenom", mMainDenom);
            startActivity(intent);
        }
    }


    private static final int TYPE_ATOM                  = 0;
    private static final int TYPE_IRIS                  = 1;
    private static final int TYPE_AKASH                 = 2;
    private static final int TYPE_SENTINEL              = 3;
    private static final int TYPE_PERSISTENCE           = 4;
    private static final int TYPE_CRYPTO                = 5;
    private static final int TYPE_RIZON                 = 6;
    private static final int TYPE_ALTHEA                = 7;
    private static final int TYPE_OSMOSIS               = 8;
    private static final int TYPE_STARNAME              = 9;
    private static final int TYPE_SIF                   = 10;
    private static final int TYPE_MEDI                  = 11;
//    private static final int TYPE_BAND                  = 10;

    private static final int TYPE_VESTING               = 99;
    private static final int TYPE_HISTORY               = 100;

    private class StakingTokenGrpcAdapter extends RecyclerView.Adapter<BaseHolder> {

        @NonNull
        @Override
        public BaseHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
            if (viewType == TYPE_ATOM) {
                return new TokenCosmosHolder(getLayoutInflater().inflate(R.layout.layout_card_atom, viewGroup, false));
            } else if (viewType == TYPE_IRIS) {
                return new TokenIrisHolder(getLayoutInflater().inflate(R.layout.layout_card_iris, viewGroup, false));
            } else if (viewType == TYPE_AKASH) {
                return new TokenAkashHolder(getLayoutInflater().inflate(R.layout.layout_card_akash, viewGroup, false));
            } else if (viewType == TYPE_SENTINEL) {
                return new TokenSentinelHolder(getLayoutInflater().inflate(R.layout.layout_card_sentinel, viewGroup, false));
            } else if (viewType == TYPE_PERSISTENCE) {
                return new TokenPersisHolder(getLayoutInflater().inflate(R.layout.layout_card_persistence, viewGroup, false));
            } else if (viewType == TYPE_CRYPTO) {
                return new TokenCrytoHolder(getLayoutInflater().inflate(R.layout.layout_card_cryto, viewGroup, false));
            } else if (viewType == TYPE_RIZON) {
                return new TokenRizonHolder(getLayoutInflater().inflate(R.layout.layout_card_rizon, viewGroup, false));
            } else if (viewType == TYPE_ALTHEA) {
                return new TokenAltheaHolder(getLayoutInflater().inflate(R.layout.layout_card_althea, viewGroup, false));
            } else if (viewType == TYPE_OSMOSIS) {
                return new TokenOsmosisHolder(getLayoutInflater().inflate(R.layout.layout_card_osmosis, viewGroup, false));
            } else if (viewType == TYPE_STARNAME) {
                return new TokenStarnameHolder(getLayoutInflater().inflate(R.layout.layout_card_starname, viewGroup, false));
            } else if (viewType == TYPE_SIF) {
                return new TokenSifHolder(getLayoutInflater().inflate(R.layout.layout_card_sif, viewGroup, false));
            } else if (viewType == TYPE_MEDI) {
                return new TokenMediblocHolder(getLayoutInflater().inflate(R.layout.layout_card_medi, viewGroup, false));
//            } else if (viewType == TYPE_BAND) {
//                return new TokenBandHolder(getLayoutInflater().inflate(R.layout.layout_card_band_token, viewGroup, false));

            }

            else if (viewType == TYPE_VESTING) {
                return new VestingHolder(getLayoutInflater().inflate(R.layout.layout_vesting_schedule, viewGroup, false));
            } else if (viewType == TYPE_HISTORY) {
                return new HistoryHolder(getLayoutInflater().inflate(R.layout.item_history, viewGroup, false));
            }
            return null;
        }

        @Override
        public void onBindViewHolder(@NonNull BaseHolder holder, int position) {
            if (getItemViewType(position) == TYPE_ATOM) {
                holder.onBindTokenHolder(getBaseContext(), mBaseChain, getBaseDao(), WDp.mainDenom(mBaseChain));

            } else if (getItemViewType(position) == TYPE_IRIS) {
                holder.onBindTokenHolder(getBaseContext(), mBaseChain, getBaseDao(), WDp.mainDenom(mBaseChain));

            } else if (getItemViewType(position) == TYPE_AKASH) {
                holder.onBindTokenHolder(getBaseContext(), mBaseChain, getBaseDao(), WDp.mainDenom(mBaseChain));

            } else if (getItemViewType(position) == TYPE_SENTINEL) {
                holder.onBindTokenHolder(getBaseContext(), mBaseChain, getBaseDao(), WDp.mainDenom(mBaseChain));

            } else if (getItemViewType(position) == TYPE_PERSISTENCE) {
                holder.onBindTokenHolder(getBaseContext(), mBaseChain, getBaseDao(), WDp.mainDenom(mBaseChain));

            } else if (getItemViewType(position) == TYPE_CRYPTO) {
                holder.onBindTokenHolder(getBaseContext(), mBaseChain, getBaseDao(), WDp.mainDenom(mBaseChain));

            } else if (getItemViewType(position) == TYPE_RIZON) {
                holder.onBindTokenHolder(getBaseContext(), mBaseChain, getBaseDao(), WDp.mainDenom(mBaseChain));

            } else if (getItemViewType(position) == TYPE_ALTHEA) {
                holder.onBindTokenHolder(getBaseContext(), mBaseChain, getBaseDao(), WDp.mainDenom(mBaseChain));

            } else if (getItemViewType(position) == TYPE_OSMOSIS) {
                holder.onBindTokenHolder(getBaseContext(), mBaseChain, getBaseDao(), WDp.mainDenom(mBaseChain));

            } else if (getItemViewType(position) == TYPE_STARNAME) {
                holder.onBindTokenHolder(getBaseContext(), mBaseChain, getBaseDao(), WDp.mainDenom(mBaseChain));

            } else if (getItemViewType(position) == TYPE_SIF) {
                holder.onBindTokenHolder(getBaseContext(), mBaseChain, getBaseDao(), WDp.mainDenom(mBaseChain));

            } else if (getItemViewType(position) == TYPE_MEDI) {
                holder.onBindTokenHolder(getBaseContext(), mBaseChain, getBaseDao(), WDp.mainDenom(mBaseChain));

//            } else if (getItemViewType(position) == TYPE_BAND) {
//                holder.onBindTokenHolder(getBaseContext(), mBaseChain, getBaseDao(), WDp.mainDenom(mBaseChain));

            }

            else if (getItemViewType(position) == TYPE_VESTING) {
                holder.onBindTokenHolder(getBaseContext(), mBaseChain, getBaseDao(), WDp.mainDenom(mBaseChain));

            } else if (getItemViewType(position) == TYPE_HISTORY) {

            }
//            if (getItemViewType(position) == TYPE_HISTORY) {
//                ResApiTxList.Data tx = null;
//                if (mApiTxCustomHistory.size() > 0) {
//
//                } else if (mApiTxHistory.size() > 0) {
//                    if (mHasVesting) {
//                        tx = mApiTxHistory.get(position - 2);
//                    } else {
//                        tx = mApiTxHistory.get(position - 1);
//                    }
//                    ((HistoryHolder)holder).onBindHistory(StakingTokenDetailActivity.this, tx, mAccount.address);
//                }
//
//            }
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
            if (position == 0) {
                if ((mBaseChain.equals(COSMOS_MAIN) || mBaseChain.equals(COSMOS_TEST))) {
                    return TYPE_ATOM;
                } else if (mBaseChain.equals(IRIS_MAIN) || mBaseChain.equals(IRIS_TEST)) {
                    return TYPE_IRIS;
                } else if (mBaseChain.equals(AKASH_MAIN)) {
                    return TYPE_AKASH;
                } else if (mBaseChain.equals(SENTINEL_MAIN)) {
                    return TYPE_SENTINEL;
                } else if (mBaseChain.equals(PERSIS_MAIN)) {
                    return TYPE_PERSISTENCE;
                } else if (mBaseChain.equals(CRYPTO_MAIN)) {
                    return TYPE_CRYPTO;
                } else if (mBaseChain.equals(OSMOSIS_MAIN)) {
                    return TYPE_OSMOSIS;
                } else if (mBaseChain.equals(IOV_MAIN)) {
                    return TYPE_STARNAME;
                } else if (mBaseChain.equals(RIZON_TEST)) {
                    return TYPE_RIZON;
                } else if (mBaseChain.equals(ALTHEA_TEST)) {
                    return TYPE_ALTHEA;
                } else if (mBaseChain.equals(SIF_MAIN)) {
                    return TYPE_SIF;
                } else if (mBaseChain.equals(MEDI_MAIN) || mBaseChain.equals(MEDI_TEST)) {
                    return TYPE_MEDI;
//                } else if (mBaseChain.equals(BAND_MAIN)) {
//                    return TYPE_BAND;
                }

                return -1;

            } else if (position == 1 && mHasVesting) {
                return TYPE_VESTING;

            } else {
                return TYPE_HISTORY;
            }
        }
    }
}
