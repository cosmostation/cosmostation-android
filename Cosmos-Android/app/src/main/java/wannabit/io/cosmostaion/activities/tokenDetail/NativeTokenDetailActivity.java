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
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.math.BigDecimal;
import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.SendActivity;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.dialog.Dialog_AccountShow;
import wannabit.io.cosmostaion.dialog.Dialog_WatchMode;
import wannabit.io.cosmostaion.network.res.ResApiTxList;
import wannabit.io.cosmostaion.network.res.ResApiTxListCustom;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WKey;
import wannabit.io.cosmostaion.utils.WUtil;
import wannabit.io.cosmostaion.widget.BaseHolder;
import wannabit.io.cosmostaion.widget.HistoryHolder;
import wannabit.io.cosmostaion.widget.tokenDetail.TokenBaseHolder;
import wannabit.io.cosmostaion.widget.tokenDetail.TokenHardHolder;
import wannabit.io.cosmostaion.widget.tokenDetail.VestingHolder;

import static wannabit.io.cosmostaion.base.BaseChain.BNB_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.BNB_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.KAVA_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.KAVA_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.OKEX_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.OK_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.SIF_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.isGRPC;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_SIMPLE_SEND;
import static wannabit.io.cosmostaion.base.BaseConstant.FEE_BNB_SEND;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HARD;

public class NativeTokenDetailActivity extends BaseActivity implements View.OnClickListener {

    private Toolbar                         mToolbar;
    private ImageView                       mBtnAddressPopup;
    private ImageView                       mKeyState;
    private TextView                        mAddress;
    private SwipeRefreshLayout              mSwipeRefreshLayout;
    private RecyclerView                    mRecyclerView;

    private RelativeLayout                  mBtnIbcSend;
    private RelativeLayout                  mBtnBep3Send;
    private RelativeLayout                  mBtnSend;

    private NativeTokenAdapter              mAdapter;
    private String                          mDenom;
    private ArrayList<ResApiTxList.Data>    mApiTxHistory = new ArrayList<>();
    private ArrayList<ResApiTxListCustom>   mApiTxCustomHistory = new ArrayList<>();
    private Boolean                         mHasVesting = false;
    private String                          shareAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_token_detail_native);

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
        mDenom = getIntent().getStringExtra("denom");

        if (isGRPC(mBaseChain)) {

        } else if (mBaseChain.equals(KAVA_MAIN) || mBaseChain.equals(KAVA_TEST)) {
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
            onStartHTLCSendActivity(mDenom);

        } else if (v.equals(mBtnSend)) {
            if (!mAccount.hasPrivateKey) {
                Dialog_WatchMode add = Dialog_WatchMode.newInstance();
                add.setCancelable(true);
                getSupportFragmentManager().beginTransaction().add(add, "dialog").commitNowAllowingStateLoss();
                return;
            }

            if (isGRPC(mBaseChain)) {
                Intent intent = new Intent(getBaseContext(), SendActivity.class);
                BigDecimal feeAmount = WUtil.getEstimateGasFeeAmount(getBaseContext(), mBaseChain, CONST_PW_TX_SIMPLE_SEND, 0);
                if (getBaseDao().getAvailable(WDp.mainDenom(mBaseChain)).compareTo(feeAmount) <= 0) {
                    Toast.makeText(getBaseContext(), R.string.error_not_enough_budget, Toast.LENGTH_SHORT).show();
                    return;
                }
                intent.putExtra("sendTokenDenom", mDenom);
                startActivity(intent);

            }

            else if (mBaseChain.equals(BNB_MAIN) || mBaseChain.equals(BNB_TEST)) {
                if (getBaseDao().availableAmount(WDp.mainDenom(mBaseChain)).compareTo(new BigDecimal(FEE_BNB_SEND)) < 0) {
                    Toast.makeText(getBaseContext(), R.string.error_not_enough_budget, Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent(getBaseContext(), SendActivity.class);
                intent.putExtra("bnbToken", getBaseDao().getBnbToken(mDenom));
                startActivity(intent);

            } else if (mBaseChain.equals(KAVA_MAIN) || mBaseChain.equals(KAVA_TEST)) {
                Intent intent = new Intent(getBaseContext(), SendActivity.class);
                intent.putExtra("sendTokenDenom", mDenom);
                startActivity(intent);

            } else if (mBaseChain.equals(OKEX_MAIN) || mBaseChain.equals(OK_TEST)) {
                if (getBaseDao().availableAmount(WDp.mainDenom(mBaseChain)).compareTo(new BigDecimal("0.002")) < 0) {
                    Toast.makeText(getBaseContext(), R.string.error_not_enough_budget, Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent(getBaseContext(), SendActivity.class);
                intent.putExtra("okDenom", mDenom);
                startActivity(intent);

            } else if (mBaseChain.equals(SIF_MAIN)) {
                Toast.makeText(getBaseContext(), R.string.error_prepare, Toast.LENGTH_SHORT).show();
                return;
            }

        }

    }

    private class NativeTokenAdapter extends RecyclerView.Adapter<BaseHolder> {
        private static final int TYPE_UNKNOWN               = -1;
        private static final int TYPE_BNB                   = 0;
        private static final int TYPE_KAVA                  = 1;
        private static final int TYPE_OKEX                  = 2;
        private static final int TYPE_SIF                   = 3;

        private static final int TYPE_HARD                  = 98;
        private static final int TYPE_VESTING               = 99;
        private static final int TYPE_HISTORY               = 100;

        @NonNull
        @Override
        public BaseHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
            if (viewType == TYPE_UNKNOWN) {

            } else if (viewType == TYPE_BNB) {
                return new TokenBaseHolder(getLayoutInflater().inflate(R.layout.item_token_base, viewGroup, false));

            } else if (viewType == TYPE_KAVA) {
                return new TokenBaseHolder(getLayoutInflater().inflate(R.layout.item_token_base, viewGroup, false));

            } else if (viewType == TYPE_OKEX) {
                return new TokenBaseHolder(getLayoutInflater().inflate(R.layout.item_token_base, viewGroup, false));

            } else if (viewType == TYPE_SIF) {
                return new TokenBaseHolder(getLayoutInflater().inflate(R.layout.item_token_base, viewGroup, false));

            }

            else if (viewType == TYPE_HARD) {
                return new TokenHardHolder(getLayoutInflater().inflate(R.layout.item_token_hard, viewGroup, false));

            } else if (viewType == TYPE_VESTING) {
                return new VestingHolder(getLayoutInflater().inflate(R.layout.layout_vesting_schedule, viewGroup, false));

            } else if (viewType == TYPE_HISTORY) {
                return new HistoryHolder(getLayoutInflater().inflate(R.layout.item_history, viewGroup, false));
            }
            return null;
        }

        @Override
        public void onBindViewHolder(@NonNull BaseHolder viewHolder, int position) {
            if (getItemViewType(position) == TYPE_BNB) {
                viewHolder.onBindTokenHolder(getBaseContext(), mBaseChain, getBaseDao(), mDenom);

            } else if (getItemViewType(position) == TYPE_KAVA) {
                viewHolder.onBindTokenHolder(getBaseContext(), mBaseChain, getBaseDao(), mDenom);

            } else if (getItemViewType(position) == TYPE_OKEX) {
                viewHolder.onBindTokenHolder(getBaseContext(), mBaseChain, getBaseDao(), mDenom);

            } else if (getItemViewType(position) == TYPE_SIF) {
                viewHolder.onBindTokenHolder(getBaseContext(), mBaseChain, getBaseDao(), mDenom);


            } else if (getItemViewType(position) == TYPE_HARD) {
                viewHolder.onBindTokenHolder(getBaseContext(), mBaseChain, getBaseDao(), mDenom);

            } else if (getItemViewType(position) == TYPE_VESTING) {
                viewHolder.onBindTokenHolder(getBaseContext(), mBaseChain, getBaseDao(), mDenom);

            } else if (getItemViewType(position) == TYPE_HISTORY) {

            } else if (getItemViewType(position) == TYPE_UNKNOWN) {

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
            if (mBaseChain.equals(BNB_MAIN) || mBaseChain.equals(BNB_TEST)) {
                if (position == 0) return TYPE_BNB;
                else return TYPE_HISTORY;

            } else if (mBaseChain.equals(KAVA_MAIN) || mBaseChain.equals(KAVA_TEST)) {
                if (mDenom.equals(TOKEN_HARD)) {
                    if (mHasVesting) {
                        if (position == 0) return TYPE_HARD;
                        if (position == 1) return TYPE_VESTING;
                        else return TYPE_HISTORY;
                    } else {
                        if (position == 0) return TYPE_HARD;
                        else return TYPE_HISTORY;
                    }

                } else {
                    if (position == 0) return TYPE_KAVA;
                    else return TYPE_HISTORY;
                }

            } else if (mBaseChain.equals(OKEX_MAIN) || mBaseChain.equals(OK_TEST)) {
                if (position == 0) return TYPE_OKEX;
                else return TYPE_HISTORY;

            } else if (mBaseChain.equals(SIF_MAIN)) {
                if (position == 0) return TYPE_SIF;
                else return TYPE_HISTORY;

            }
            return TYPE_UNKNOWN;
        }
    }
}
