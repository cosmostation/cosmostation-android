package wannabit.io.cosmostaion.activities.tokenDetail;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.squareup.picasso.Picasso;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.SendActivity;
import wannabit.io.cosmostaion.activities.WebActivity;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.dao.Balance;
import wannabit.io.cosmostaion.dao.BnbToken;
import wannabit.io.cosmostaion.dao.OkToken;
import wannabit.io.cosmostaion.dialog.Dialog_AccountShow;
import wannabit.io.cosmostaion.network.res.ResApiTxList;
import wannabit.io.cosmostaion.network.res.ResApiTxListCustom;
import wannabit.io.cosmostaion.network.res.ResBnbTic;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WKey;
import wannabit.io.cosmostaion.utils.WUtil;
import wannabit.io.cosmostaion.widget.BaseHolder;
import wannabit.io.cosmostaion.widget.HistoryHolder;
import wannabit.io.cosmostaion.widget.TokenDetailBaseHolder;
import wannabit.io.cosmostaion.widget.TokenDetailHardHolder;
import wannabit.io.cosmostaion.widget.VestingHolder;

import static wannabit.io.cosmostaion.base.BaseChain.BNB_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.BNB_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.KAVA_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.KAVA_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.OKEX_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.OK_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.SIF_MAIN;
import static wannabit.io.cosmostaion.base.BaseConstant.OKEX_COIN_IMG_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HARD;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HTLC_BINANCE_BTCB;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HTLC_BINANCE_BUSD;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HTLC_BINANCE_TEST_BTC;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HTLC_BINANCE_XRPB;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_IMG_URL;

public class NativeTokenDetailActivity extends BaseActivity implements View.OnClickListener {

    private Toolbar                 mToolbar;
    private ImageView               mBtnExplorer, mBtnAddressPopup;
    private ImageView               mKeyState;
    private TextView                mAddress;
    private SwipeRefreshLayout      mSwipeRefreshLayout;
    private RecyclerView            mRecyclerView;

    private Balance mBalance;
    private BnbToken mBnbToken;
    private HashMap<String, ResBnbTic>      mBnbTics = new HashMap<>();
    private String                          mOkDenom;
    private OkToken                         mOkToken;

    private RelativeLayout          mBtnSend;
    private LinearLayout            mBep3Layer;
    private RelativeLayout          mBtnBep3Send, mBtnBep3SimpleSend;

    private NativeTokenAdapter      mAdapter;

    private String                          mDenom;
    private ArrayList<ResApiTxList.Data>    mApiTxHistory = new ArrayList<>();
    private ArrayList<ResApiTxListCustom>   mApiTxCustomHistory = new ArrayList<>();
    private Boolean                         mHasVesting = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_token_detail_native);

        mToolbar                = findViewById(R.id.tool_bar);
        mBtnExplorer            = findViewById(R.id.web_detail);
        mBtnAddressPopup        = findViewById(R.id.address_detail);
        mKeyState               = findViewById(R.id.img_account);
        mAddress                = findViewById(R.id.account_Address);
        mSwipeRefreshLayout     = findViewById(R.id.layer_refresher);
        mRecyclerView           = findViewById(R.id.recycler);
        mBtnSend                = findViewById(R.id.btn_send);
        mBep3Layer              = findViewById(R.id.layer_bep3);
        mBtnBep3Send            = findViewById(R.id.btn_bep3_send);
        mBtnBep3SimpleSend      = findViewById(R.id.btn_bep3_simple_send);

        mBalance = getIntent().getParcelableExtra("balance");
        mBnbToken = getIntent().getParcelableExtra("bnbToken");
        mBnbTics = (HashMap<String, ResBnbTic>)getIntent().getSerializableExtra("bnbTics");
        mOkDenom = getIntent().getStringExtra("okDenom");
        mOkToken = WUtil.getOkToken(getBaseDao().mOkTokenList, mOkDenom);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mAccount = getBaseDao().onSelectAccount(getBaseDao().getLastUser());
        mBaseChain = BaseChain.getChain(mAccount.baseChain);
        mDenom = getIntent().getStringExtra("denom");

        if (mBaseChain.equals(KAVA_MAIN) || mBaseChain.equals(KAVA_TEST)) {
            if (getBaseDao().mKavaAccount.value.getCalcurateVestingCntByDenom(mDenom) > 0) {
                mHasVesting = true;
            }
        }

        if (WUtil.isBep3Coin(mDenom)) { mBep3Layer.setVisibility(View.VISIBLE); }
        else { mBtnSend.setVisibility(View.VISIBLE); }

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new NativeTokenAdapter();
        mRecyclerView.setAdapter(mAdapter);

        onUpdateView();

        mBtnExplorer.setOnClickListener(this);
        mBtnAddressPopup.setOnClickListener(this);
        mBtnSend.setOnClickListener(this);
        mBtnBep3Send.setOnClickListener(this);
        mBtnBep3SimpleSend.setOnClickListener(this);
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

        } else if (v.equals(mBtnSend) || v.equals(mBtnBep3SimpleSend)) {
            Intent intent = new Intent(NativeTokenDetailActivity.this, SendActivity.class);
            intent.putExtra("sendTokenDenom", mDenom);
            startActivity(intent);

        } else if (v.equals(mBtnBep3Send)) {
            onStartHTLCSendActivity(mDenom);

        }

    }

    private class NativeTokenAdapter extends RecyclerView.Adapter<BaseHolder> {
        private static final int TYPE_UNKNOWN               = -1;
        private static final int TYPE_BASE_BNB            = 0;
        private static final int TYPE_BASE_KAVA           = 1;
        private static final int TYPE_BASE_OKEX           = 2;
        private static final int TYPE_BASE_SIF            = 3;

        private static final int TYPE_HARD                  = 98;
        private static final int TYPE_VESTING               = 99;
        private static final int TYPE_HISTORY               = 100;

        @NonNull
        @Override
        public BaseHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
            if (viewType == TYPE_UNKNOWN) {

            } else if (viewType == TYPE_BASE_BNB) {
                return new TokenHolder(getLayoutInflater().inflate(R.layout.layout_card_token, viewGroup, false));

            } else if (viewType == TYPE_BASE_KAVA) {
                return new TokenDetailBaseHolder(getLayoutInflater().inflate(R.layout.item_token_base, viewGroup, false));

            } else if (viewType == TYPE_BASE_OKEX) {
                return new TokenHolder(getLayoutInflater().inflate(R.layout.item_token_base, viewGroup, false));

            } else if (viewType == TYPE_BASE_SIF) {
                return new TokenDetailBaseHolder(getLayoutInflater().inflate(R.layout.item_token_base, viewGroup, false));

            }

            else if (viewType == TYPE_HARD) {
                return new TokenDetailHardHolder(getLayoutInflater().inflate(R.layout.item_token_hard, viewGroup, false));

            } else if (viewType == TYPE_VESTING) {
                return new VestingHolder(getLayoutInflater().inflate(R.layout.layout_vesting_schedule, viewGroup, false));

            } else if (viewType == TYPE_HISTORY) {
                return new HistoryHolder(getLayoutInflater().inflate(R.layout.item_history, viewGroup, false));
            }
            return null;
        }

        @Override
        public void onBindViewHolder(@NonNull BaseHolder viewHolder, int position) {
            if (getItemViewType(position) == TYPE_BASE_BNB) {
                onBindToken(viewHolder, position);

            } else if (getItemViewType(position) == TYPE_BASE_KAVA) {
                ((TokenDetailBaseHolder)viewHolder).onBindKavaToken(getBaseContext(), mBaseChain, getBaseDao(), mDenom);

            } else if (getItemViewType(position) == TYPE_BASE_OKEX) {
                onBindToken(viewHolder, position);

            } else if (getItemViewType(position) == TYPE_BASE_SIF) {
                ((TokenDetailBaseHolder)viewHolder).onBindSifToken(getBaseContext(), mBaseChain, getBaseDao(), mDenom);


            } else if (getItemViewType(position) == TYPE_HARD) {
                ((TokenDetailHardHolder)viewHolder).onBindHard(getBaseContext(), mBaseChain, getBaseDao());

            } else if (getItemViewType(position) == TYPE_VESTING) {
                ((VestingHolder)viewHolder).onBindTokenHolder(getBaseContext(), mBaseChain, getBaseDao(), mDenom);

            } else if (getItemViewType(position) == TYPE_HISTORY) {
                ResApiTxList.Data tx = null;
                if (mApiTxCustomHistory.size() > 0) {

                } else if (mApiTxHistory.size() > 0) {
                    if (mHasVesting) {
                        tx = mApiTxHistory.get(position - 2);
                    } else {
                        tx = mApiTxHistory.get(position - 1);
                    }
                    ((HistoryHolder)viewHolder).onBindHistory(NativeTokenDetailActivity.this, tx, mAccount.address);
                }

            } else if (getItemViewType(position) == TYPE_UNKNOWN) {

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
            if (mBaseChain.equals(BNB_MAIN) || mBaseChain.equals(BNB_TEST)) {
                if (position == 0) return TYPE_BASE_BNB;
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
                    if (position == 0) return TYPE_BASE_KAVA;
                    else return TYPE_HISTORY;
                }

            } else if (mBaseChain.equals(OKEX_MAIN) || mBaseChain.equals(OK_TEST)) {
                if (position == 0) return TYPE_BASE_OKEX;
                else return TYPE_HISTORY;

            } else if (mBaseChain.equals(SIF_MAIN)) {
                if (position == 0) return TYPE_BASE_SIF;
                else return TYPE_HISTORY;

            }
            return TYPE_UNKNOWN;
        }
    }

    private void onBindToken(RecyclerView.ViewHolder viewHolder, int position) {
        final TokenHolder holder = (TokenHolder)viewHolder;
        if (mBaseChain.equals(BNB_MAIN) && mBnbToken != null) {
            holder.mTokenLink.setVisibility(View.VISIBLE);
            holder.mTvTokenSymbol.setText(mBnbToken.original_symbol);
            holder.mTvTokenDenom.setText(mBnbToken.symbol);
            holder.mTvTokenTotal.setText(WDp.getDpAmount2(getBaseContext(), mBalance.getAllBnbBalance(), 0, 8));

            BigDecimal amount = BigDecimal.ZERO;
            ResBnbTic tic = mBnbTics.get(WUtil.getBnbTicSymbol(mBalance.symbol));
            if (tic != null) { amount = mBalance.exchangeToBnbAmount(tic); }
            holder.mTvTokenValue.setText(WDp.getValueOfBnb(getBaseContext(), getBaseDao(), amount));
            holder.mTvTokenAvailable.setText(WDp.getDpAmount2(getBaseContext(), mBalance.balance, 0, 8));
            holder. mTokenRewardLayer.setVisibility(View.GONE);
            try {
                Picasso.get().load(TOKEN_IMG_URL+mBnbToken.original_symbol+".png").fit().placeholder(R.drawable.token_ic).error(R.drawable.token_ic) .into(holder.mTokenImg);
            } catch (Exception e) {}

            if (mBalance.symbol.equals(TOKEN_HTLC_BINANCE_BTCB) || mBalance.symbol.equals(TOKEN_HTLC_BINANCE_XRPB) || mBalance.symbol.equals(TOKEN_HTLC_BINANCE_BUSD)) {
                holder.mBtnBep3Send.setVisibility(View.VISIBLE);
            } else {
                holder.mBtnBep3Send.setVisibility(View.GONE);
            }

        } else if (mBaseChain.equals(BNB_TEST) && mBnbToken != null) {
            holder.mTokenLink.setVisibility(View.VISIBLE);
            holder.mTvTokenSymbol.setText(mBnbToken.original_symbol);
            holder.mTvTokenDenom.setText(mBnbToken.symbol);
            holder.mTvTokenTotal.setText(WDp.getDpAmount2(getBaseContext(), mBalance.getAllBnbBalance(), 0, 8));

            BigDecimal amount = BigDecimal.ZERO;
            ResBnbTic tic = mBnbTics.get(WUtil.getBnbTicSymbol(mBalance.symbol));
            if (tic != null) {
                amount = mBalance.exchangeToBnbAmount(tic);
            }

            holder.mTvTokenValue.setText(WDp.getValueOfBnb(getBaseContext(), getBaseDao(), amount));
            holder.mTvTokenAvailable.setText(WDp.getDpAmount2(getBaseContext(), mBalance.balance, 0, 8));
            holder.mTokenRewardLayer.setVisibility(View.GONE);
            try {
                Picasso.get().load(TOKEN_IMG_URL+mBnbToken.original_symbol+".png").fit().placeholder(R.drawable.token_ic).error(R.drawable.token_ic).into(holder.mTokenImg);
            } catch (Exception e) {}
            if (mBalance.symbol.equals(TOKEN_HTLC_BINANCE_TEST_BTC)) {
                holder.mBtnBep3Send.setVisibility(View.VISIBLE);
            } else {
                holder.mBtnBep3Send.setVisibility(View.GONE);
            }
        } else if ((mBaseChain.equals(OKEX_MAIN) || mBaseChain.equals(OK_TEST)) && mOkToken != null) {
            holder.mTokenLink.setVisibility(View.VISIBLE);
            holder.mTokenLockedLayer.setVisibility(View.VISIBLE);
            holder.mTvTokenSymbol.setText(mOkToken.original_symbol.toUpperCase());
            holder.mTvTokenDenom.setText(mOkToken.description);

            BigDecimal availableAmount = WDp.getAvailableCoin(getBaseDao().mBalances, mOkToken.symbol);
            BigDecimal lockedAmount = WDp.getLockedCoin(getBaseDao().mBalances, mOkToken.symbol);
            BigDecimal totalAmount = availableAmount.add(lockedAmount);

            holder.mTvTokenTotal.setText(WDp.getDpAmount2(getBaseContext(), totalAmount, 0, 18));
            holder.mTvTokenAvailable.setText(WDp.getDpAmount2(getBaseContext(), availableAmount, 0, 18));
            holder.mTvTokenLocked.setText(WDp.getDpAmount2(getBaseContext(), lockedAmount, 0, 18));
            holder.mTvTokenValue.setText(WDp.getValueOfOk(getBaseContext(), getBaseDao(), totalAmount));
            try {
                Picasso.get().load(OKEX_COIN_IMG_URL+  mOkToken.original_symbol + ".png").placeholder(R.drawable.token_ic).error(R.drawable.token_ic).fit().into(holder.mTokenImg);
            } catch (Exception e) { }
        }
    }

    public class TokenHolder extends BaseHolder {
        private LinearLayout            mBtnTokenDetail;
        private RelativeLayout          mBtnSendToken, mBtnReceiveToken, mBtnBep3Send;
        private RelativeLayout          mTokenRewardLayer, mTokenLockedLayer, mTokenFrozenLayer, mVestingLayer, mHarvestDepositLayer, mHarvestRewardLayer;
        private ImageView               mTokenImg, mTokenLink;
        private TextView                mTvTokenSymbol, mTvTokenTotal, mTvTokenValue, mTvTokenDenom, mTvTokenAvailable, mTvTokenReward, mTvTokenLocked,
                mTvTokenFrozen, mTvTokenVesting, mTvTokenHarvestDeposit, mTvTokenHarvestReward;

        public TokenHolder(View v) {
            super(v);
            mTokenImg               = itemView.findViewById(R.id.dash_token_icon);
            mTokenLink              = itemView.findViewById(R.id.dash_token_link);
            mTvTokenSymbol          = itemView.findViewById(R.id.dash_token_symbol);
            mTvTokenTotal           = itemView.findViewById(R.id.dash_token_amount);
            mTvTokenValue           = itemView.findViewById(R.id.dash_token_value);
            mTvTokenDenom           = itemView.findViewById(R.id.dash_token_denom);
            mTvTokenAvailable       = itemView.findViewById(R.id.dash_token_available);
            mTokenLockedLayer       = itemView.findViewById(R.id.token_locked_layer);
            mTvTokenLocked          = itemView.findViewById(R.id.dash_token_lock);
            mTokenRewardLayer       = itemView.findViewById(R.id.token_reward_layer);
            mTvTokenReward          = itemView.findViewById(R.id.dash_token_reward);
            mTokenFrozenLayer       = itemView.findViewById(R.id.token_frozen_layer);
            mTvTokenFrozen          = itemView.findViewById(R.id.token_frozen);
            mVestingLayer           = itemView.findViewById(R.id.token_vesting_layer);
            mTvTokenVesting         = itemView.findViewById(R.id.token_vesting);
            mHarvestDepositLayer    = itemView.findViewById(R.id.token_harvest_deposit_layer);
            mTvTokenHarvestDeposit  = itemView.findViewById(R.id.token_harvest_deposit);
            mHarvestRewardLayer     = itemView.findViewById(R.id.token_harvest_reward_layer);
            mTvTokenHarvestReward   = itemView.findViewById(R.id.token_harvest_reward);
            mBtnSendToken           = itemView.findViewById(R.id.btn_token_send);
            mBtnReceiveToken        = itemView.findViewById(R.id.btn_token_receive);
            mBtnTokenDetail         = itemView.findViewById(R.id.btn_token_web);
            mBtnBep3Send            = itemView.findViewById(R.id.btn_bep3_send);
        }
    }
}
