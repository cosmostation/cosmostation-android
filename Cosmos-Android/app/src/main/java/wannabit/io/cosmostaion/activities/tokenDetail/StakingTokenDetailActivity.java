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
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.dialog.Dialog_AccountShow;
import wannabit.io.cosmostaion.network.res.ResApiTxList;
import wannabit.io.cosmostaion.network.res.ResApiTxListCustom;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WKey;
import wannabit.io.cosmostaion.widget.BaseHolder;
import wannabit.io.cosmostaion.widget.HistoryHolder;
import wannabit.io.cosmostaion.widget.tokenDetail.TokenAkashHolder;
import wannabit.io.cosmostaion.widget.tokenDetail.TokenBnbHolder;
import wannabit.io.cosmostaion.widget.tokenDetail.TokenCosmosHolder;
import wannabit.io.cosmostaion.widget.tokenDetail.TokenCrytoHolder;
import wannabit.io.cosmostaion.widget.tokenDetail.TokenIrisHolder;
import wannabit.io.cosmostaion.widget.tokenDetail.TokenKavaHolder;
import wannabit.io.cosmostaion.widget.tokenDetail.TokenOKExHolder;
import wannabit.io.cosmostaion.widget.tokenDetail.TokenPersisHolder;
import wannabit.io.cosmostaion.widget.tokenDetail.TokenRizonHolder;
import wannabit.io.cosmostaion.widget.tokenDetail.TokenSentinelHolder;
import wannabit.io.cosmostaion.widget.tokenDetail.TokenSifHolder;
import wannabit.io.cosmostaion.widget.tokenDetail.VestingHolder;

import static wannabit.io.cosmostaion.base.BaseChain.AKASH_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.BNB_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.BNB_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.COSMOS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.COSMOS_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.CRYPTO_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.IRIS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.IRIS_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.KAVA_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.KAVA_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.OKEX_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.OK_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.PERSIS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.RIZON_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.SENTINEL_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.SIF_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.isGRPC;

public class StakingTokenDetailActivity extends BaseActivity implements View.OnClickListener {

    private Toolbar             mToolbar;
    private ImageView           mBtnAddressPopup;
    private ImageView           mKeyState;
    private TextView            mAddress;
    private SwipeRefreshLayout  mSwipeRefreshLayout;
    private RecyclerView        mRecyclerView;

    private RelativeLayout      mBtnIbcSend;
    private RelativeLayout      mBtnBep3Send;
    private RelativeLayout      mBtnSend;

    private StakingTokenAdapter             mAdapter;
    private ArrayList<ResApiTxList.Data>    mApiTxHistory = new ArrayList<>();
    private ArrayList<ResApiTxListCustom>   mApiTxCustomHistory = new ArrayList<>();
    private Boolean                         mHasVesting = false;
    private String                          shareAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_token_detail_staking);

        mToolbar                = findViewById(R.id.tool_bar);
        mBtnAddressPopup        = findViewById(R.id.address_detail);
        mKeyState               = findViewById(R.id.img_account);
        mAddress                = findViewById(R.id.account_Address);
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

        if (isGRPC(mBaseChain)) {
            if (getBaseDao().onParseRemainVestingsByDenom(WDp.mainDenom(mBaseChain)).size() > 0) { mHasVesting = true; }
            mBtnIbcSend.setVisibility(View.VISIBLE);

        } else if (mBaseChain.equals(KAVA_MAIN) || mBaseChain.equals(KAVA_TEST)) {
            if (getBaseDao().mKavaAccount.value.getCalcurateVestingCntByDenom(WDp.mainDenom(mBaseChain)) > 0) { mHasVesting = true; }

        } else if (mBaseChain.equals(BNB_MAIN) || mBaseChain.equals(BNB_TEST)) {
            mBtnBep3Send.setVisibility(View.VISIBLE);

        }

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new StakingTokenAdapter();
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
        mBtnBep3Send.setOnClickListener(this);
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
            onStartHTLCSendActivity(WDp.mainDenom(mBaseChain));

        } else if (v.equals(mBtnSend)) {
            onStartSendMainDenom();
        }
    }


    private static final int TYPE_ATOM                  = 0;
    private static final int TYPE_IRIS                  = 1;
    private static final int TYPE_AKASH                 = 2;
    private static final int TYPE_SENTINEL              = 3;
    private static final int TYPE_PERSISTENCE           = 4;
    private static final int TYPE_CRYPTO                = 5;
    private static final int TYPE_RIZON                = 6;

    private static final int TYPE_SIF                   = 31;
    private static final int TYPE_OKEX                  = 32;
    private static final int TYPE_BNB                   = 33;
    private static final int TYPE_KAVA                  = 34;

    private static final int TYPE_VESTING               = 99;
    private static final int TYPE_HISTORY               = 100;

    private class StakingTokenAdapter extends RecyclerView.Adapter<BaseHolder> {

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
            }

            else if (viewType == TYPE_SIF) {
                return new TokenSifHolder(getLayoutInflater().inflate(R.layout.layout_card_sif, viewGroup, false));
            } else if (viewType == TYPE_OKEX) {
                return new TokenOKExHolder(getLayoutInflater().inflate(R.layout.layout_card_ok, viewGroup, false));
            } else if (viewType == TYPE_BNB) {
                return new TokenBnbHolder(getLayoutInflater().inflate(R.layout.layout_card_bnb, viewGroup, false));
            } else if (viewType == TYPE_KAVA) {
                return new TokenKavaHolder(getLayoutInflater().inflate(R.layout.layout_card_kava, viewGroup, false));
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

            }

            else if (getItemViewType(position) == TYPE_SIF) {
                holder.onBindTokenHolder(getBaseContext(), mBaseChain, getBaseDao(), WDp.mainDenom(mBaseChain));

            } else if (getItemViewType(position) == TYPE_OKEX) {
                holder.onBindTokenHolder(getBaseContext(), mBaseChain, getBaseDao(), WDp.mainDenom(mBaseChain));

            } else if (getItemViewType(position) == TYPE_BNB) {
                holder.onBindTokenHolder(getBaseContext(), mBaseChain, getBaseDao(), WDp.mainDenom(mBaseChain));

            } else if (getItemViewType(position) == TYPE_KAVA) {
                holder.onBindTokenHolder(getBaseContext(), mBaseChain, getBaseDao(), WDp.mainDenom(mBaseChain));
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
                } else if (mBaseChain.equals(RIZON_TEST)) {
                    return TYPE_RIZON;
                }

                if ((mBaseChain.equals(BNB_MAIN) || mBaseChain.equals(BNB_TEST))) {
                    return TYPE_BNB;
                } else if (mBaseChain.equals(KAVA_MAIN) || mBaseChain.equals(KAVA_TEST)) {
                    return TYPE_KAVA;
                } else if (mBaseChain.equals(OKEX_MAIN) || mBaseChain.equals(OK_TEST)) {
                    return TYPE_OKEX;
                } else if (mBaseChain.equals(SIF_MAIN)) {
                    return TYPE_SIF;
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
