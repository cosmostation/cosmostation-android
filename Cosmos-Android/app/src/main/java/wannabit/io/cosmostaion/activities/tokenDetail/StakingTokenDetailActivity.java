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
import wannabit.io.cosmostaion.activities.WebActivity;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.dao.Balance;
import wannabit.io.cosmostaion.dao.BnbToken;
import wannabit.io.cosmostaion.dialog.Dialog_AccountShow;
import wannabit.io.cosmostaion.dialog.Dialog_WatchMode;
import wannabit.io.cosmostaion.network.res.ResApiTxList;
import wannabit.io.cosmostaion.network.res.ResApiTxListCustom;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WKey;
import wannabit.io.cosmostaion.utils.WUtil;
import wannabit.io.cosmostaion.widget.BaseHolder;
import wannabit.io.cosmostaion.widget.HistoryHolder;
import wannabit.io.cosmostaion.widget.TokenKavaHolder;
import wannabit.io.cosmostaion.widget.TokenSifHolder;
import wannabit.io.cosmostaion.widget.VestingHolder;

import static wannabit.io.cosmostaion.base.BaseChain.BNB_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.BNB_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.KAVA_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.KAVA_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.OKEX_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.OK_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.SIF_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.isGRPC;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_SIMPLE_SEND;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_OK;

public class StakingTokenDetailActivity extends BaseActivity implements View.OnClickListener {

    private Toolbar             mToolbar;
    private ImageView           mBtnExplorer, mBtnAddressPopup;
    private RelativeLayout      mBtnSend;
    private ImageView           mKeyState;
    private TextView            mAddress;
    private SwipeRefreshLayout  mSwipeRefreshLayout;
    private RecyclerView        mRecyclerView;

    private Balance                         mBalance;
    private BnbToken                        mBnbToken;

    private StakingTokenAdapter             mAdapter;
    private ArrayList<ResApiTxList.Data>    mApiTxHistory = new ArrayList<>();
    private ArrayList<ResApiTxListCustom>   mApiTxCustomHistory = new ArrayList<>();
    private Boolean                         mHasVesting = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_token_detail_staking);

        mToolbar                = findViewById(R.id.tool_bar);
        mBtnExplorer            = findViewById(R.id.web_detail);
        mBtnAddressPopup        = findViewById(R.id.address_detail);
        mBtnSend                = findViewById(R.id.btn_send);
        mKeyState               = findViewById(R.id.img_account);
        mAddress                = findViewById(R.id.account_Address);
        mSwipeRefreshLayout     = findViewById(R.id.layer_refresher);
        mRecyclerView           = findViewById(R.id.recycler);

        mBalance = getIntent().getParcelableExtra("balance");
        mBnbToken = getIntent().getParcelableExtra("bnbToken");

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mAccount = getBaseDao().onSelectAccount(getBaseDao().getLastUser());
        mBaseChain = BaseChain.getChain(mAccount.baseChain);
        if (mBaseChain.equals(KAVA_MAIN) || mBaseChain.equals(KAVA_TEST)) {
            if (getBaseDao().mKavaAccount.value.getCalcurateVestingCntByDenom(WDp.mainDenom(mBaseChain)) > 0) {
                mHasVesting = true;
            }
        }

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new StakingTokenAdapter();
        mRecyclerView.setAdapter(mAdapter);

        onUpdateView();

        mBtnExplorer.setOnClickListener(this);
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
        if (mBaseChain.equals(OKEX_MAIN) || mBaseChain.equals(OK_TEST)) {
            try {
                mAddress.setText(WKey.convertAddressOkexToEth(mAccount.address));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            mAddress.setText(mAccount.address);
        }
        mKeyState.setColorFilter(ContextCompat.getColor(getBaseContext(), R.color.colorGray0), android.graphics.PorterDuff.Mode.SRC_IN);
        if ((mBaseChain.equals(BNB_MAIN) || mBaseChain.equals(BNB_TEST)) && mAccount.hasPrivateKey) {
            mKeyState.setColorFilter(ContextCompat.getColor(getBaseContext(), R.color.colorBnb), android.graphics.PorterDuff.Mode.SRC_IN);

        } else if ((mBaseChain.equals(KAVA_MAIN) || mBaseChain.equals(KAVA_TEST)) && mAccount.hasPrivateKey) {
            mKeyState.setColorFilter(ContextCompat.getColor(getBaseContext(), R.color.colorKava), android.graphics.PorterDuff.Mode.SRC_IN);

        } else if ((mBaseChain.equals(OKEX_MAIN) || mBaseChain.equals(OK_TEST)) && mAccount.hasPrivateKey) {
            mKeyState.setColorFilter(ContextCompat.getColor(getBaseContext(), R.color.colorOK), android.graphics.PorterDuff.Mode.SRC_IN);

        } else if (mBaseChain.equals(SIF_MAIN) && mAccount.hasPrivateKey) {
            mKeyState.setColorFilter(ContextCompat.getColor(getBaseContext(), R.color.colorSif), android.graphics.PorterDuff.Mode.SRC_IN);
        }
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mBtnExplorer)) {
            Intent webintent = new Intent(this, WebActivity.class);
            webintent.putExtra("address", mAccount.address);
            webintent.putExtra("chain", mBaseChain.getChain());
            webintent.putExtra("goMain", false);
            startActivity(webintent);

        } else if (v.equals(mBtnAddressPopup)) {
            Bundle bundle = new Bundle();
            bundle.putString("address", mAccount.address);
            if (TextUtils.isEmpty(mAccount.nickName)) { bundle.putString("title", getString(R.string.str_my_wallet) + mAccount.id); }
            else { bundle.putString("title", mAccount.nickName); }
            Dialog_AccountShow show = Dialog_AccountShow.newInstance(bundle);
            show.setCancelable(true);
            getSupportFragmentManager().beginTransaction().add(show, "dialog").commitNowAllowingStateLoss();

        } else if (v.equals(mBtnSend)) {
            if (mAccount == null) return;
            if (!mAccount.hasPrivateKey) {
                Dialog_WatchMode add = Dialog_WatchMode.newInstance();
                add.setCancelable(true);
                getSupportFragmentManager().beginTransaction().add(add, "dialog").commitNowAllowingStateLoss();
                return;
            }

            if (isGRPC(mBaseChain)) {
                BigDecimal feeAmount = WUtil.getEstimateGasFeeAmount(getBaseContext(), mBaseChain, CONST_PW_TX_SIMPLE_SEND, 0);
                if (getBaseDao().getAvailable(WDp.mainDenom(mBaseChain)).compareTo(feeAmount) <= 0) {
                    Toast.makeText(getBaseContext(), R.string.error_not_enough_budget, Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent intent = new Intent(StakingTokenDetailActivity.this, SendActivity.class);
                intent.putExtra("sendTokenDenom", WDp.mainDenom(mBaseChain));
                startActivity(intent);
            }
        }

    }

    private static final int TYPE_BNB               = 0;
    private static final int TYPE_KAVA              = 1;
    private static final int TYPE_OKEX              = 2;
    private static final int TYPE_SIF               = 3;

    private static final int TYPE_VESTING           = 99;
    private static final int TYPE_HISTORY           = 100;


    private class StakingTokenAdapter extends RecyclerView.Adapter<BaseHolder> {

        @NonNull
        @Override
        public BaseHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
            if (viewType == TYPE_BNB) {
                return new BnbHolder(getLayoutInflater().inflate(R.layout.layout_card_bnb, viewGroup, false));

            } else if (viewType == TYPE_KAVA) {
                return new TokenKavaHolder(getLayoutInflater().inflate(R.layout.layout_card_kava, viewGroup, false));

            } else if (viewType == TYPE_OKEX) {
                return new OktHolder(getLayoutInflater().inflate(R.layout.layout_card_ok, viewGroup, false));

            } else if (viewType == TYPE_SIF) {
                return new TokenSifHolder(getLayoutInflater().inflate(R.layout.layout_card_sif, viewGroup, false));

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
            if (getItemViewType(position) == TYPE_HISTORY) {
                ResApiTxList.Data tx = null;
                if (mApiTxCustomHistory.size() > 0) {

                } else if (mApiTxHistory.size() > 0) {
                    if (mHasVesting) {
                        tx = mApiTxHistory.get(position - 2);
                    } else {
                        tx = mApiTxHistory.get(position - 1);
                    }
                    ((HistoryHolder)holder).onBindHistory(StakingTokenDetailActivity.this, tx, mAccount.address);
                }

            } else if (getItemViewType(position) == TYPE_OKEX) {
                onBindOkt(holder, position);

            } else if (getItemViewType(position) == TYPE_BNB) {
                onBindBnb(holder, position);
            }
//            else if (getItemViewType(position) == TYPE_VESTING) {
//
//            }
            else {
                holder.onBindTokenHolder(getBaseContext(), mBaseChain, getBaseDao(), WDp.mainDenom(mBaseChain));
            }
        }

        @Override
        public int getItemCount() {
            int cnt = 1;
            if (mApiTxCustomHistory != null) {
                cnt = cnt + mApiTxCustomHistory.size();
            }
            if (mApiTxHistory != null) {
                cnt = cnt + mApiTxHistory.size();
            }
            if (mHasVesting) {
                cnt = cnt + 1;
            }
            return cnt;
        }

        @Override
        public int getItemViewType(int position) {
            if (position == 0) {
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

    private void onBindBnb(RecyclerView.ViewHolder viewHolder, int position) {
        final BnbHolder holder = (BnbHolder)viewHolder;
        if (mBnbToken != null) {
            BigDecimal totalAmount = mBalance.locked.add(mBalance.balance);
            holder.mTvBnbBalance.setText(WDp.getDpAmount2(getBaseContext(), mBalance.balance, 0, 8));
            holder.mTvBnbLocked.setText(WDp.getDpAmount2(getBaseContext(), mBalance.locked, 0, 8));
            holder.mTvBnbTotal.setText(WDp.getDpAmount2(getBaseContext(), mBalance.locked.add(mBalance.balance), 0, 8));
            holder.mTvBnbValue.setText(WDp.getValueOfBnb(getBaseContext(), getBaseDao(), totalAmount));
        }
    }

    private void onBindOkt(RecyclerView.ViewHolder viewHolder, int position) {
        final OktHolder holder = (OktHolder)viewHolder;
        BigDecimal availableAmount = WDp.getAvailableCoin(getBaseDao().mBalances, TOKEN_OK);
        BigDecimal lockedAmount = WDp.getLockedCoin(getBaseDao().mBalances, TOKEN_OK);
        BigDecimal depositAmount = WDp.getOkDepositCoin(getBaseDao().mOkStaking);
        BigDecimal withdrawAmount = WDp.getOkWithdrawingCoin(getBaseDao().mOkUnbonding);
        BigDecimal totalAmount = availableAmount.add(lockedAmount).add(depositAmount).add(withdrawAmount);

        holder.mOkTotalAmount.setText(WDp.getDpAmount2(getBaseContext(), totalAmount, 0, 18));
        holder.mOkAvailable.setText(WDp.getDpAmount2(getBaseContext(), availableAmount, 0, 18));
        holder.mOkLocked.setText(WDp.getDpAmount2(getBaseContext(), lockedAmount, 0, 18));
        holder.mOkDeposit.setText(WDp.getDpAmount2(getBaseContext(), depositAmount, 0, 18));
        holder.mOkWithdrawing.setText(WDp.getDpAmount2(getBaseContext(), withdrawAmount, 0, 18));
        holder.mOkTotalValue.setText(WDp.getValueOfOk(getBaseContext(), getBaseDao(), totalAmount));

    }

    public class BnbHolder extends BaseHolder {
        private RelativeLayout  mBtnSendBnb, mBtnReceiveBnb, mBtnInterChain;
        private TextView        mTvBnbTotal, mTvBnbValue, mTvBnbBalance, mTvBnbLocked;

        public BnbHolder(View v) {
            super(v);
            mTvBnbTotal             = itemView.findViewById(R.id.dash_bnb_amount);
            mTvBnbValue             = itemView.findViewById(R.id.dash_bnb_value);
            mTvBnbBalance           = itemView.findViewById(R.id.dash_bnb_balance);
            mTvBnbLocked            = itemView.findViewById(R.id.dash_bnb_locked);
            mBtnSendBnb             = itemView.findViewById(R.id.btn_bnb_send);
            mBtnReceiveBnb          = itemView.findViewById(R.id.btn_bnb_receive);
            mBtnInterChain          = itemView.findViewById(R.id.btn_bep3_send2);
        }
    }

    public class OktHolder extends BaseHolder {
        private RelativeLayout  mBtnOkSend, mBtnOkReceive;
        private TextView        mOkTotalAmount, mOkTotalValue, mOkAvailable, mOkLocked, mOkDeposit, mOkWithdrawing;

        public OktHolder(View v) {
            super(v);
            mOkTotalAmount          = itemView.findViewById(R.id.ok_total_amount);
            mOkTotalValue           = itemView.findViewById(R.id.ok_total_value);
            mOkAvailable            = itemView.findViewById(R.id.ok_available);
            mOkLocked               = itemView.findViewById(R.id.ok_locked);
            mOkDeposit              = itemView.findViewById(R.id.ok_deposit);
            mOkWithdrawing          = itemView.findViewById(R.id.ok_withdrawing);
            mBtnOkSend              = itemView.findViewById(R.id.btn_ok_send);
            mBtnOkReceive           = itemView.findViewById(R.id.btn_ok_receive);
        }
    }
}
