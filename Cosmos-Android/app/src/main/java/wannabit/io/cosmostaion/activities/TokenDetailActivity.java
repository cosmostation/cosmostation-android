package wannabit.io.cosmostaion.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.dao.Balance;
import wannabit.io.cosmostaion.dao.BnbToken;
import wannabit.io.cosmostaion.dao.IrisToken;
import wannabit.io.cosmostaion.dao.OkToken;
import wannabit.io.cosmostaion.dialog.Dialog_AccountShow;
import wannabit.io.cosmostaion.dialog.Dialog_WatchMode;
import wannabit.io.cosmostaion.model.type.BnbHistory;
import wannabit.io.cosmostaion.network.res.ResApiTxList;
import wannabit.io.cosmostaion.network.res.ResBnbTic;
import wannabit.io.cosmostaion.network.res.ResLcdKavaAccountInfo;
import wannabit.io.cosmostaion.task.FetchTask.ApiTokenTxsHistoryTask;
import wannabit.io.cosmostaion.task.FetchTask.BnbHistoryTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WLog;
import wannabit.io.cosmostaion.utils.WUtil;

import static wannabit.io.cosmostaion.base.BaseChain.BNB_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.BNB_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.COSMOS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.IRIS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.KAVA_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.KAVA_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.OKEX_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.OK_TEST;
import static wannabit.io.cosmostaion.base.BaseConstant.FEE_BNB_SEND;
import static wannabit.io.cosmostaion.base.BaseConstant.KAVA_COIN_IMG_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.OKEX_COIN_IMG_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_ATOM;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_BNB;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HTLC_BINANCE_BTCB;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HTLC_BINANCE_BUSD;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HTLC_BINANCE_TEST_BTC;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HTLC_BINANCE_XRPB;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HTLC_KAVA_BNB;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HTLC_KAVA_BTCB;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HTLC_KAVA_BUSD;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HTLC_KAVA_TEST_BNB;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HTLC_KAVA_TEST_BTC;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HTLC_KAVA_XRPB;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_IMG_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_IRIS;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_IRIS_ATTO;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_KAVA;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_OK;


public class TokenDetailActivity extends BaseActivity implements View.OnClickListener, TaskListener {

    private Toolbar                         mToolbar;
    private ImageView                       mBtnWebDetail, mBtnAddressDetail;
    private ImageView                       mKeyState;
    private TextView                        mAddress;
    private SwipeRefreshLayout              mSwipeRefreshLayout;
    private RecyclerView                    mRecyclerView;
    private TokenDetailAdapter              mTokenDetailAdapter;

    private Balance                         mBalance;
    private IrisToken                       mIrisToken;
    private BnbToken                        mBnbToken;
    private HashMap<String, ResBnbTic>      mBnbTics = new HashMap<>();
    private String                          mOkDenom;
    private OkToken                         mOkToken;

    private ArrayList<BnbHistory>           mBnbHistory = new ArrayList<>();
    private ArrayList<ResApiTxList.Data>    mApiTxHistory = new ArrayList<>();
    private Boolean                         mHasVesting = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_token_detail);

        mAccount = getBaseDao().onSelectAccount(getBaseDao().getLastUser());
        mBaseChain = BaseChain.getChain(mAccount.baseChain);

        mToolbar = findViewById(R.id.tool_bar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mBtnWebDetail           = findViewById(R.id.web_detail);
        mBtnAddressDetail       = findViewById(R.id.address_detail);
        mKeyState               = findViewById(R.id.img_account);
        mAddress                = findViewById(R.id.account_Address);

        mSwipeRefreshLayout     = findViewById(R.id.layer_refresher);
        mRecyclerView           = findViewById(R.id.recycler);

        mBalance = getIntent().getParcelableExtra("balance");
        mIrisToken = getIntent().getParcelableExtra("irisToken");
        mBnbToken = getIntent().getParcelableExtra("bnbToken");
        mBnbTics = (HashMap<String, ResBnbTic>)getIntent().getSerializableExtra("bnbTics");
        mOkDenom = getIntent().getStringExtra("okDenom");
        mOkToken = WUtil.getOkToken(getBaseDao().mOkTokenList, mOkDenom);
        onCheckVesting();

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                onFetchTokenHistory();
            }
        });
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);
        mTokenDetailAdapter = new TokenDetailAdapter();
        mRecyclerView.setAdapter(mTokenDetailAdapter);

        onFetchTokenHistory();
        onUpdateView();
        mBtnWebDetail.setOnClickListener(this);
        mBtnAddressDetail.setOnClickListener(this);

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

    private void onCheckVesting() {
        if (mBaseChain.equals(KAVA_MAIN) || mBaseChain.equals(KAVA_MAIN)) {
            if (getBaseDao().mKavaAccount.value.getCalcurateVestingCntByDenom(mBalance.symbol) > 0) {
                mHasVesting = true;
            }
        }
    }

    private void onUpdateView() {
        mAddress.setText(mAccount.address);
        mKeyState.setColorFilter(ContextCompat.getColor(getBaseContext(), R.color.colorGray0), android.graphics.PorterDuff.Mode.SRC_IN);
        if (mBaseChain.equals(COSMOS_MAIN) && mAccount.hasPrivateKey) {
            mKeyState.setColorFilter(ContextCompat.getColor(getBaseContext(), R.color.colorAtom), android.graphics.PorterDuff.Mode.SRC_IN);

        } else if (mBaseChain.equals(IRIS_MAIN) && mAccount.hasPrivateKey) {
            mKeyState.setColorFilter(ContextCompat.getColor(getBaseContext(), R.color.colorIris), android.graphics.PorterDuff.Mode.SRC_IN);

        } else if ((mBaseChain.equals(BNB_MAIN) || mBaseChain.equals(BNB_TEST)) && mAccount.hasPrivateKey) {
            mKeyState.setColorFilter(ContextCompat.getColor(getBaseContext(), R.color.colorBnb), android.graphics.PorterDuff.Mode.SRC_IN);

        } else if ((mBaseChain.equals(KAVA_MAIN) || mBaseChain.equals(KAVA_TEST)) && mAccount.hasPrivateKey) {
            mKeyState.setColorFilter(ContextCompat.getColor(getBaseContext(), R.color.colorKava), android.graphics.PorterDuff.Mode.SRC_IN);

        } else if ((mBaseChain.equals(OKEX_MAIN) || mBaseChain.equals(OK_TEST)) && mAccount.hasPrivateKey) {
            mKeyState.setColorFilter(ContextCompat.getColor(getBaseContext(), R.color.colorOK), android.graphics.PorterDuff.Mode.SRC_IN);
        }
    }

    private void onFetchTokenHistory() {
        if (mBaseChain.equals(COSMOS_MAIN)) {
            new ApiTokenTxsHistoryTask(getBaseApplication(), this, mAccount.address, mBalance.symbol, mBaseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        } else if (mBaseChain.equals(IRIS_MAIN)) {
            new ApiTokenTxsHistoryTask(getBaseApplication(), this, mAccount.address, mBalance.symbol, mBaseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        } else if (mBaseChain.equals(BNB_MAIN) || mBaseChain.equals(BNB_TEST)) {
            new BnbHistoryTask(getBaseApplication(), this, null, mBaseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, mAccount.address, WDp.threeMonthAgoTimeString(), WDp.cTimeString(), mBnbToken.symbol);

        } else if (mBaseChain.equals(KAVA_MAIN)) {
            new ApiTokenTxsHistoryTask(getBaseApplication(), this, mAccount.address, mBalance.symbol, mBaseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        } else if (mBaseChain.equals(KAVA_TEST)) {
            new ApiTokenTxsHistoryTask(getBaseApplication(), this, mAccount.address, mBalance.symbol, mBaseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        } else if (mBaseChain.equals(OKEX_MAIN) || mBaseChain.equals(OK_TEST)) {
            mSwipeRefreshLayout.setRefreshing(false);

        }
    }

    @Override
    public void onTaskResponse(TaskResult result) {
        if(isFinishing()) return;
        if (result.taskType == BaseConstant.TASK_FETCH_BNB_HISTORY) {
            mBnbHistory = (ArrayList<BnbHistory>)result.resultData;
            mTokenDetailAdapter.notifyDataSetChanged();
            if (mBnbHistory != null) { WLog.w("mBnbHistory " + mBnbHistory.size()); }

        } else if (result.taskType == BaseConstant.TASK_FETCH_API_TOKEN_HISTORY) {
            mApiTxHistory = (ArrayList<ResApiTxList.Data>)result.resultData;
            mTokenDetailAdapter.notifyDataSetChanged();
            if (mApiTxHistory != null) { WLog.w("mApiTxHistory " + mBnbHistory.size()); }

        }
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mBtnWebDetail)) {
            Intent webintent = new Intent(this, WebActivity.class);
            webintent.putExtra("address", mAccount.address);
            webintent.putExtra("chain", mBaseChain.getChain());
            webintent.putExtra("goMain", false);
            startActivity(webintent);

        } else if (v.equals(mBtnAddressDetail)) {
            onClickReceive();
        }

    }


    private void onClickSend(int type) {
        if (onCheckSendable()) {
            if (type == TYPE_ATOM) {
                startActivity(new Intent(TokenDetailActivity.this, SendActivity.class));

            } else if (type == TYPE_IRIS) {
                Intent intent = new Intent(TokenDetailActivity.this, SendActivity.class);
                intent.putExtra("irisToken", mIrisToken);
                startActivity(intent);

            } else if (type == TYPE_BNB) {
                Intent intent = new Intent(TokenDetailActivity.this, SendActivity.class);
                intent.putExtra("bnbToken", mBnbToken);
                startActivity(intent);

            } else if (type == TYPE_KAVA) {
                Intent intent = new Intent(TokenDetailActivity.this, SendActivity.class);
                intent.putExtra("kavaDenom", TOKEN_KAVA);
                startActivity(intent);

            } else if (type == TYPE_OKT) {
                Intent intent = new Intent(TokenDetailActivity.this, SendActivity.class);
                intent.putExtra("okDenom", TOKEN_OK);
                startActivity(intent);

            } else if (type == TYPE_TOKEN) {
                Intent intent = new Intent(TokenDetailActivity.this, SendActivity.class);
                if (mBaseChain.equals(IRIS_MAIN)) {
                    intent.putExtra("irisToken", mIrisToken);
                } else if (mBaseChain.equals(BNB_MAIN) || mBaseChain.equals(BNB_TEST)) {
                    intent.putExtra("bnbToken", mBnbToken);
                    intent.putExtra("bnbTics", mBnbTics);
                } else if (mBaseChain.equals(KAVA_MAIN) || mBaseChain.equals(KAVA_TEST)) {
                    intent.putExtra("kavaDenom", mBalance.symbol);
                } else if (mBaseChain.equals(OKEX_MAIN) || mBaseChain.equals(OK_TEST)) {
                    intent.putExtra("okDenom", mOkToken.symbol);
                }
                startActivity(intent);
            }

        }
    }

    private void onClickReceive() {
        Bundle bundle = new Bundle();
        bundle.putString("address", mAccount.address);
        if (TextUtils.isEmpty(mAccount.nickName)) { bundle.putString("title", getString(R.string.str_my_wallet) + mAccount.id); }
        else { bundle.putString("title", mAccount.nickName); }
        Dialog_AccountShow show = Dialog_AccountShow.newInstance(bundle);
        show.setCancelable(true);
        getSupportFragmentManager().beginTransaction().add(show, "dialog").commitNowAllowingStateLoss();

    }

    private void onClickTokenDetail() {
        if (mBaseChain.equals(BNB_MAIN) || mBaseChain.equals(BNB_TEST)) {
            Intent webintent = new Intent(this, WebActivity.class);
            webintent.putExtra("asset", mBnbToken.symbol);
            webintent.putExtra("chain", mBaseChain.getChain());
            startActivity(webintent);
        } else if (mBaseChain.equals(KAVA_MAIN) || mBaseChain.equals(KAVA_TEST)) {
            Intent webintent = new Intent(this, WebActivity.class);
            webintent.putExtra("asset", "usdx");
            webintent.putExtra("chain", mBaseChain.getChain());
            startActivity(webintent);
        } else if (mBaseChain.equals(OKEX_MAIN) || mBaseChain.equals(OK_TEST)) {
            Intent webintent = new Intent(this, WebActivity.class);
            webintent.putExtra("asset", mOkToken.symbol);
            webintent.putExtra("chain", mBaseChain.getChain());
            startActivity(webintent);
        }

    }

    private void onClickHTLCSend() {
        onStartHTLCSendActivity(mBalance.symbol);
    }

    public boolean onCheckSendable() {
        if (mAccount == null) return false;
        if (!mAccount.hasPrivateKey) {
            Dialog_WatchMode add = Dialog_WatchMode.newInstance();
            add.setCancelable(true);
            getSupportFragmentManager().beginTransaction().add(add, "dialog").commitNowAllowingStateLoss();
            return false;
        }

        ArrayList<Balance> balances = getBaseDao().onSelectBalance(mAccount.id);
        boolean hasbalance = false;
        if (mBaseChain.equals(COSMOS_MAIN) || mBaseChain.equals(KAVA_MAIN) || mBaseChain.equals(KAVA_TEST)) {
            if (WDp.getAvailableCoin(balances, mBalance.symbol).compareTo(BigDecimal.ZERO) <= 0) {
                Toast.makeText(getBaseContext(), R.string.error_not_enough_to_balance, Toast.LENGTH_SHORT).show();
                return false;
            }
            hasbalance  = true;

        } else if (mBaseChain.equals(IRIS_MAIN)) {
            if (WDp.getAvailableCoin(balances, TOKEN_IRIS_ATTO).compareTo(new BigDecimal("200000000000000000")) > 0) {
                hasbalance  = true;
            }
            if (!mIrisToken.base_token.symbol.equals(TOKEN_IRIS)) {
                Toast.makeText(getBaseContext(), R.string.error_iris_token_not_yet, Toast.LENGTH_SHORT).show();
                return false;
            }

        } else if (mBaseChain.equals(BNB_MAIN) || mBaseChain.equals(BNB_TEST)) {
            if (WDp.getAvailableCoin(balances, TOKEN_BNB).compareTo(new BigDecimal(FEE_BNB_SEND)) > 0) {
                hasbalance  = true;
            }

        } else if (mBaseChain.equals(OKEX_MAIN) || mBaseChain.equals(OK_TEST)) {
            if (WDp.getAvailableCoin(balances, TOKEN_OK).compareTo(new BigDecimal("0.2")) > 0) {
                hasbalance  = true;
            }
        }

        if (!hasbalance){
            Toast.makeText(getBaseContext(), R.string.error_not_enough_budget, Toast.LENGTH_SHORT).show();
        }
        return hasbalance;
    }

    private static final int TYPE_ATOM              = 0;
    private static final int TYPE_IRIS              = 1;
    private static final int TYPE_BNB               = 2;
    private static final int TYPE_KAVA              = 3;
    private static final int TYPE_OKT               = 4;
    private static final int TYPE_TOKEN             = 98;
    private static final int TYPE_VESTING           = 99;
    private static final int TYPE_HISTORY           = 100;
    private class TokenDetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
            if (viewType == TYPE_ATOM) {
                return new AtomHolder(getLayoutInflater().inflate(R.layout.layout_card_atom, viewGroup, false));

            } else if (viewType == TYPE_IRIS) {
                return new IrisHolder(getLayoutInflater().inflate(R.layout.layout_card_iris, viewGroup, false));

            } else if (viewType == TYPE_BNB) {
                return new BnbHolder(getLayoutInflater().inflate(R.layout.layout_card_bnb, viewGroup, false));

            } else if (viewType == TYPE_KAVA) {
                return new KavaHolder(getLayoutInflater().inflate(R.layout.layout_card_kava, viewGroup, false));

            } else if (viewType == TYPE_OKT) {
                return new OktHolder(getLayoutInflater().inflate(R.layout.layout_card_ok, viewGroup, false));

            } else if (viewType == TYPE_TOKEN) {
                return new TokenHolder(getLayoutInflater().inflate(R.layout.layout_card_token, viewGroup, false));

            } else if (viewType == TYPE_VESTING) {
                return new VestingHolder(getLayoutInflater().inflate(R.layout.layout_vesting_schedule, viewGroup, false));

            } else if (viewType == TYPE_HISTORY) {
                return new TokenHistoryHolder(getLayoutInflater().inflate(R.layout.item_history, viewGroup, false));

            }
            return null;
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
            if (getItemViewType(position) == TYPE_ATOM) {
                onBindAtom(viewHolder, position);

            } else if (getItemViewType(position) == TYPE_IRIS) {
                onBindIris(viewHolder, position);

            } else if (getItemViewType(position) == TYPE_BNB) {
                onBindBnb(viewHolder, position);

            } else if (getItemViewType(position) == TYPE_KAVA) {
                onBindKava(viewHolder, position);

            } else if (getItemViewType(position) == TYPE_OKT) {
                onBindOkt(viewHolder, position);

            } else if (getItemViewType(position) == TYPE_TOKEN) {
                onBindToken(viewHolder, position);

            } else if (getItemViewType(position) == TYPE_VESTING) {
                onBindVesting(viewHolder, position);

            } else if (getItemViewType(position) == TYPE_HISTORY) {
                onBindHistory(viewHolder, position);

            }
        }

        @Override
        public int getItemCount() {
            if (mBaseChain.equals(BNB_MAIN) || mBaseChain.equals(BNB_TEST)) {
                if (mBnbHistory != null) {
                    return mBnbHistory.size() + 1;
                }
                return 1;

            } else {
                int cnt = 1;
                if (mApiTxHistory != null) {
                    cnt = cnt + mApiTxHistory.size();
                }
                if (mHasVesting) {
                    cnt = cnt + 1;
                }
                return cnt;
            }
        }

        @Override
        public int getItemViewType(int position) {
            if (position == 0) {
                if (mBaseChain.equals(COSMOS_MAIN) && mBalance.symbol.equals(TOKEN_ATOM)) {
                    return TYPE_ATOM;

                } else if (mBaseChain.equals(IRIS_MAIN) && mBalance.symbol.equals(TOKEN_IRIS_ATTO)) {
                    return TYPE_IRIS;

                } else if ((mBaseChain.equals(BNB_MAIN) || mBaseChain.equals(BNB_TEST)) && mBalance.symbol.equals(TOKEN_BNB)) {
                    return TYPE_BNB;

                } else if ((mBaseChain.equals(KAVA_MAIN) || mBaseChain.equals(KAVA_TEST))&& mBalance.symbol.equals(TOKEN_KAVA)) {
                    return TYPE_KAVA;

                } else if ((mBaseChain.equals(OKEX_MAIN) || mBaseChain.equals(OK_TEST)) && mOkDenom.equals(TOKEN_OK)) {
                    return TYPE_OKT;

                } else {
                    return TYPE_TOKEN;
                }

            } else if (position == 1 && mHasVesting) {
                return TYPE_VESTING;

            } else {
                return TYPE_HISTORY;
            }
        }

        private void onBindAtom(RecyclerView.ViewHolder viewHolder, int position) {
            final AtomHolder holder = (AtomHolder)viewHolder;
            BigDecimal totalAmount = WDp.getAllAtom(getBaseDao().mBalances, getBaseDao().mBondings, getBaseDao().mUnbondings, getBaseDao().mRewards, getBaseDao().mAllValidators);
            holder.mTvAtomTotal.setText(WDp.getDpAmount(getBaseContext(), totalAmount, 6, mBaseChain));
            holder.mTvAtomAvailable.setText(WDp.getDpAvailableCoin(getBaseContext(), getBaseDao().mBalances, mBaseChain, TOKEN_ATOM));
            holder.mTvAtomDelegated.setText(WDp.getDpAllDelegatedAmount(getBaseContext(), getBaseDao().mBondings, getBaseDao().mAllValidators, mBaseChain));
            holder.mTvAtomUnBonding.setText(WDp.getDpAllUnbondingAmount(getBaseContext(), getBaseDao().mUnbondings, getBaseDao().mAllValidators, mBaseChain));
            holder.mTvAtomUnBonding.setText(WDp.getDpAllUnbondingAmount(getBaseContext(), getBaseDao().mUnbondings, getBaseDao().mAllValidators, mBaseChain));
            holder.mTvAtomRewards.setText(WDp.getDpAllRewardAmount(getBaseContext(), getBaseDao().mRewards, mBaseChain, TOKEN_ATOM));
            holder.mTvAtomValue.setText(WDp.getValueOfAtom(getBaseContext(), getBaseDao(), totalAmount));

            holder.mBtnSendAtom.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickSend(TYPE_ATOM);
                }
            });
            holder.mBtnReceiveAtom.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickReceive();
                }
            });
        }

        private void onBindIris(RecyclerView.ViewHolder viewHolder, int position) {
            final IrisHolder holder = (IrisHolder)viewHolder;
            BigDecimal totalAmount = WDp.getAllIris(getBaseDao().mBalances, getBaseDao().mBondings, getBaseDao().mUnbondings, getBaseDao().mIrisReward, getBaseDao().mAllValidators);
            holder.mTvIrisTotal.setText(WDp.getDpAmount(getBaseContext(), totalAmount, 18, mBaseChain));
            holder.mTvIrisAvailable.setText(WDp.getDpAmount(getBaseContext(), WDp.getAvailableCoin(getBaseDao().mBalances, TOKEN_IRIS_ATTO), 18, mBaseChain));
            holder.mTvIrisDelegated.setText(WDp.getDpAmount(getBaseContext(), WDp.getAllDelegatedAmount(getBaseDao().mBondings, getBaseDao().mAllValidators, mBaseChain), 18, mBaseChain));
            holder.mTvIrisUnBonding.setText(WDp.getDpAmount(getBaseContext(), WDp.getUnbondingAmount(getBaseDao().mUnbondings), 18, mBaseChain));
            holder.mTvIrisRewards.setText(WDp.getDpAmount(getBaseContext(), getBaseDao().mIrisReward.getSimpleIrisReward(), 18, mBaseChain));
            holder.mTvIrisValue.setText(WDp.getValueOfIris(getBaseContext(), getBaseDao(), totalAmount));

            holder.mBtnSendIris.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickSend(TYPE_IRIS);
                }
            });
            holder.mBtnReceiveIris.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickReceive();
                }
            });
        }

        private void onBindBnb(RecyclerView.ViewHolder viewHolder, int position) {
            final BnbHolder holder = (BnbHolder)viewHolder;
            if (mBnbToken != null) {
                BigDecimal totalAmount = mBalance.locked.add(mBalance.balance);
                holder.mTvBnbBalance.setText(WDp.getDpAmount(getBaseContext(), mBalance.balance, 8, mBaseChain));
                holder.mTvBnbLocked.setText(WDp.getDpAmount(getBaseContext(), mBalance.locked, 8, mBaseChain));
                holder.mTvBnbTotal.setText(WDp.getDpAmount(getBaseContext(), mBalance.locked.add(mBalance.balance), 8, mBaseChain));
                holder.mTvBnbValue.setText(WDp.getValueOfBnb(getBaseContext(), getBaseDao(), totalAmount));
            }

            holder.mBtnSendBnb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickSend(TYPE_BNB);
                }
            });
            holder.mBtnReceiveBnb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickReceive();
                }
            });
            holder.mBtnInterChain.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickHTLCSend();
                }
            });
        }

        private void onBindKava(RecyclerView.ViewHolder viewHolder, int position) {
            final KavaHolder holder = (KavaHolder)viewHolder;
            BigDecimal availableAmount = WDp.getAvailableCoin(getBaseDao().mBalances, TOKEN_KAVA);
            BigDecimal delegateAmount = WDp.getAllDelegatedAmount(getBaseDao().mBondings, getBaseDao().mAllValidators, mBaseChain);
            BigDecimal unbondingAmount = WDp.getUnbondingAmount(getBaseDao().mUnbondings);
            BigDecimal rewardAmount = WDp.getAllRewardAmount(getBaseDao().mRewards, TOKEN_KAVA);
            BigDecimal vestingAmount = WDp.getLockedCoin(getBaseDao().mBalances, TOKEN_KAVA);
            BigDecimal harvestDepositAmount = WDp.getHavestDepositAmount(getBaseDao(), TOKEN_KAVA);
            BigDecimal unclaimedIncentiveAmount = WDp.getUnclaimedIncentiveAmount(getBaseDao(), TOKEN_KAVA);
            BigDecimal totalAmount = WDp.getAllKava(getBaseDao(), getBaseDao().mBalances, getBaseDao().mBondings, getBaseDao().mUnbondings, getBaseDao().mRewards, getBaseDao().mAllValidators);

            holder.mTvKavaTotal.setText(WDp.getDpAmount2(getBaseContext(), totalAmount, 6, 6));
            holder.mTvKavaAvailable.setText(WDp.getDpAmount2(getBaseContext(), availableAmount, 6, 6));
            holder.mTvKavaDelegated.setText(WDp.getDpAmount2(getBaseContext(), delegateAmount, 6, 6));
            holder.mTvKavaUnBonding.setText(WDp.getDpAmount2(getBaseContext(), unbondingAmount, 6, 6));
            holder.mTvKavaRewards.setText(WDp.getDpAmount2(getBaseContext(), rewardAmount, 6, 6));
            holder.mTvKavaVesting.setText(WDp.getDpAmount2(getBaseContext(), vestingAmount, 6, 6));
            holder.mTvKavaDeposit.setText(WDp.getDpAmount2(getBaseContext(), harvestDepositAmount, 6, 6));
            holder.mTvKavaIncentive.setText(WDp.getDpAmount2(getBaseContext(), unclaimedIncentiveAmount, 6, 6));
            holder.mTvKavaValue.setText(WDp.getValueOfKava(getBaseContext(), getBaseDao(), totalAmount));

            if (!vestingAmount.equals(BigDecimal.ZERO)) { holder.mKavaVestingLayer.setVisibility(View.VISIBLE);
            } else { holder.mKavaVestingLayer.setVisibility(View.GONE); }
            if (!harvestDepositAmount.equals(BigDecimal.ZERO)) { holder.mKavaDepositLayer.setVisibility(View.VISIBLE);
            } else { holder.mKavaDepositLayer.setVisibility(View.GONE); }
            if (!unclaimedIncentiveAmount.equals(BigDecimal.ZERO)) { holder.mKavaIncentiveLayer.setVisibility(View.VISIBLE);
            } else { holder.mKavaIncentiveLayer.setVisibility(View.GONE); }

            holder.mBtnSendKava.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickSend(TYPE_KAVA);
                }
            });
            holder.mBtnReceiveKava.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickReceive();
                }
            });

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

            holder.mBtnOkSend.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickSend(TYPE_OKT);
                }
            });
            holder.mBtnOkReceive.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickReceive();
                }
            });
        }

        private void onBindToken(RecyclerView.ViewHolder viewHolder, int position) {
            final TokenHolder holder = (TokenHolder)viewHolder;
            if (mBaseChain.equals(COSMOS_MAIN)) {

            } else if (mBaseChain.equals(IRIS_MAIN) && mIrisToken != null) {
                holder.mTokenLink.setVisibility(View.GONE);
                holder.mTvTokenSymbol.setText(mIrisToken.base_token.symbol.toUpperCase());
                holder.mTvTokenDenom.setText(mBalance.symbol);

                holder.mTvTokenTotal.setText(WDp.getDpAmount(getBaseContext(), mBalance.balance, mIrisToken.base_token.decimal, mBaseChain));
                holder.mTvTokenAvailable.setText(WDp.getDpAmount(getBaseContext(), mBalance.balance, mIrisToken.base_token.decimal, mBaseChain));
                holder.mTokenRewardLayer.setVisibility(View.GONE);
                holder.mTokenImg.setImageDrawable(getResources().getDrawable(R.drawable.token_ic));

            } else if (mBaseChain.equals(BNB_MAIN) && mBnbToken != null) {
                holder.mTokenLink.setVisibility(View.VISIBLE);
                holder.mTvTokenSymbol.setText(mBnbToken.original_symbol);
                holder.mTvTokenDenom.setText(mBnbToken.symbol);
                holder.mTvTokenTotal.setText(WDp.getDpAmount(getBaseContext(), mBalance.getAllBnbBalance(), 8, mBaseChain));

                BigDecimal amount = BigDecimal.ZERO;
                ResBnbTic tic = mBnbTics.get(WUtil.getBnbTicSymbol(mBalance.symbol));
                if (tic != null) { amount = mBalance.exchangeToBnbAmount(tic); }
                holder.mTvTokenValue.setText(WDp.getValueOfBnb(getBaseContext(), getBaseDao(), amount));
                holder.mTvTokenAvailable.setText(WDp.getDpAmount(getBaseContext(), mBalance.balance, 8, mBaseChain));
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
                holder.mTvTokenTotal.setText(WDp.getDpAmount(getBaseContext(), mBalance.getAllBnbBalance(), 8, mBaseChain));

                BigDecimal amount = BigDecimal.ZERO;
                ResBnbTic tic = mBnbTics.get(WUtil.getBnbTicSymbol(mBalance.symbol));
                if (tic != null) {
                    amount = mBalance.exchangeToBnbAmount(tic);
                }

                holder.mTvTokenValue.setText(WDp.getValueOfBnb(getBaseContext(), getBaseDao(), amount));
                holder.mTvTokenAvailable.setText(WDp.getDpAmount(getBaseContext(), mBalance.balance, 8, mBaseChain));
                holder.mTokenRewardLayer.setVisibility(View.GONE);
                try {
                    Picasso.get().load(TOKEN_IMG_URL+mBnbToken.original_symbol+".png").fit().placeholder(R.drawable.token_ic).error(R.drawable.token_ic).into(holder.mTokenImg);
                } catch (Exception e) {}
                if (mBalance.symbol.equals(TOKEN_HTLC_BINANCE_TEST_BTC)) {
                    holder.mBtnBep3Send.setVisibility(View.VISIBLE);
                } else {
                    holder.mBtnBep3Send.setVisibility(View.GONE);
                }

            } else if (mBaseChain.equals(KAVA_MAIN) && mBalance != null) {
                final int dpDecimal                         = WUtil.getKavaCoinDecimal(mBalance.symbol);
                final BigDecimal tokenTotalAmount           = WDp.getKavaTokenAll(getBaseDao(), getBaseDao().mBalances, mBalance.symbol);
                final BigDecimal availableTokenAmount       = WDp.getAvailableCoin(getBaseDao().mBalances, mBalance.symbol);
                final BigDecimal vestingTokenAmount         = WDp.getKavaVestingAmount(getBaseDao().mBalances, mBalance.symbol);
                final BigDecimal havestDepositTokenAmount   = WDp.getHavestDepositAmount(getBaseDao(), mBalance.symbol);
                final BigDecimal havestRewardTokenAmount    = WDp.getHavestRewardAmount(getBaseDao(), mBalance.symbol);
                final BigDecimal tokenTotalValue            = WDp.kavaTokenDollorValue(getBaseDao(), mBalance.symbol, tokenTotalAmount);
                final BigDecimal convertedKavaAmount        = tokenTotalValue.divide(getBaseDao().getLastKavaDollorTic(), WUtil.getKavaCoinDecimal(TOKEN_KAVA), RoundingMode.DOWN);

                holder.mTokenLink.setVisibility(View.GONE);
                holder.mTvTokenSymbol.setText(mBalance.symbol.toUpperCase());
                holder.mTvTokenDenom.setText(mBalance.symbol);

                holder.mTvTokenTotal.setText(WDp.getDpAmount2(getBaseContext(), tokenTotalAmount, dpDecimal, dpDecimal));
                holder.mTvTokenValue.setText(WDp.getValueOfKava(getBaseContext(), getBaseDao(), convertedKavaAmount.movePointRight(WUtil.getKavaCoinDecimal(TOKEN_KAVA))));
                holder.mTvTokenAvailable.setText(WDp.getDpAmount2(getBaseContext(), availableTokenAmount, dpDecimal, dpDecimal));
                holder.mTvTokenVesting.setText(WDp.getDpAmount2(getBaseContext(), vestingTokenAmount, dpDecimal, dpDecimal));
                holder.mTvTokenHarvestDeposit.setText(WDp.getDpAmount2(getBaseContext(), havestDepositTokenAmount, dpDecimal, dpDecimal));
                holder.mTvTokenHarvestReward.setText(WDp.getDpAmount2(getBaseContext(), havestRewardTokenAmount, dpDecimal, dpDecimal));
                if (!vestingTokenAmount.equals(BigDecimal.ZERO)) { holder.mVestingLayer.setVisibility(View.VISIBLE); }
                holder.mHarvestDepositLayer.setVisibility(View.VISIBLE);
                if (!havestRewardTokenAmount.equals(BigDecimal.ZERO)) { holder.mHarvestRewardLayer.setVisibility(View.VISIBLE); }

                try {
                    Picasso.get().load(KAVA_COIN_IMG_URL+mBalance.symbol+".png").fit().placeholder(R.drawable.token_ic).error(R.drawable.token_ic).into(holder.mTokenImg);

                } catch (Exception e) { }
                if (mBalance.symbol.equals(TOKEN_HTLC_KAVA_BNB) || mBalance.symbol.equals(TOKEN_HTLC_KAVA_BTCB) || mBalance.symbol.equals(TOKEN_HTLC_KAVA_XRPB) || mBalance.symbol.equals(TOKEN_HTLC_KAVA_BUSD)) {
                    holder.mBtnBep3Send.setVisibility(View.VISIBLE);
                } else {
                    holder.mBtnBep3Send.setVisibility(View.GONE);
                }

            } else if (mBaseChain.equals(KAVA_TEST) && mBalance != null) {
                final int dpDecimal                         = WUtil.getKavaCoinDecimal(mBalance.symbol);
                final BigDecimal tokenTotalAmount           = WDp.getKavaTokenAll(getBaseDao(), getBaseDao().mBalances, mBalance.symbol);
                final BigDecimal availableTokenAmount       = WDp.getAvailableCoin(getBaseDao().mBalances, mBalance.symbol);
                final BigDecimal vestingTokenAmount         = WDp.getKavaVestingAmount(getBaseDao().mBalances, mBalance.symbol);
                final BigDecimal havestDepositTokenAmount   = WDp.getHavestDepositAmount(getBaseDao(), mBalance.symbol);
                final BigDecimal havestRewardTokenAmount    = WDp.getHavestRewardAmount(getBaseDao(), mBalance.symbol);
                final BigDecimal tokenTotalValue            = WDp.kavaTokenDollorValue(getBaseDao(), mBalance.symbol, tokenTotalAmount);
                final BigDecimal convertedKavaAmount        = tokenTotalValue.divide(getBaseDao().getLastKavaDollorTic(), WUtil.getKavaCoinDecimal(TOKEN_KAVA), RoundingMode.DOWN);

                holder.mTokenLink.setVisibility(View.GONE);
                holder.mTvTokenSymbol.setText(mBalance.symbol.toUpperCase());
                holder.mTvTokenDenom.setText(mBalance.symbol);

                holder.mTvTokenTotal.setText(WDp.getDpAmount2(getBaseContext(), tokenTotalAmount, dpDecimal, dpDecimal));
                holder.mTvTokenValue.setText(WDp.getValueOfKava(getBaseContext(), getBaseDao(), convertedKavaAmount.movePointRight(WUtil.getKavaCoinDecimal(TOKEN_KAVA))));
                holder.mTvTokenAvailable.setText(WDp.getDpAmount2(getBaseContext(), availableTokenAmount, dpDecimal, dpDecimal));
                holder.mTvTokenVesting.setText(WDp.getDpAmount2(getBaseContext(), vestingTokenAmount, dpDecimal, dpDecimal));
                holder.mTvTokenHarvestDeposit.setText(WDp.getDpAmount2(getBaseContext(), havestDepositTokenAmount, dpDecimal, dpDecimal));
                holder.mTvTokenHarvestReward.setText(WDp.getDpAmount2(getBaseContext(), havestRewardTokenAmount, dpDecimal, dpDecimal));
                if (!vestingTokenAmount.equals(BigDecimal.ZERO)) { holder.mVestingLayer.setVisibility(View.VISIBLE); }
                holder.mHarvestDepositLayer.setVisibility(View.VISIBLE);
                if (!havestRewardTokenAmount.equals(BigDecimal.ZERO)) { holder.mHarvestRewardLayer.setVisibility(View.VISIBLE); }

                try {
                    Picasso.get().load(KAVA_COIN_IMG_URL+mBalance.symbol+".png").fit().placeholder(R.drawable.token_ic).error(R.drawable.token_ic).into(holder.mTokenImg);

                } catch (Exception e) { }
                if (mBalance.symbol.equals(TOKEN_HTLC_KAVA_TEST_BNB) || mBalance.symbol.equals(TOKEN_HTLC_KAVA_TEST_BTC)) {
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

            holder.mBtnSendToken.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickSend(TYPE_TOKEN);
                }
            });
            holder.mBtnReceiveToken.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickReceive();
                }
            });
            holder.mBtnTokenDetail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickTokenDetail();
                }
            });
            holder.mBtnBep3Send.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickHTLCSend();
                }
            });

        }

        private void onBindVesting(RecyclerView.ViewHolder viewHolder, int position) {
            final VestingHolder holder = (VestingHolder)viewHolder;
            if (mBaseChain.equals(KAVA_MAIN) && mBalance.symbol.equals(TOKEN_KAVA)) {
                holder.mVestingRoot.setCardBackgroundColor(getResources().getColor(R.color.colorTransBgKava));
            }
            final ResLcdKavaAccountInfo.Value mKavaAccount = getBaseDao().mKavaAccount.value;
            holder.mVestingCnt.setText("(" + mKavaAccount.getCalcurateVestingCntByDenom(mBalance.symbol) + ")");
            holder.mVestingTotalAmount.setText(WDp.getDpAmount2(getBaseContext(), mKavaAccount.getCalcurateVestingAmountSumByDenom(mBalance.symbol), 6, 6));

            holder.mVestingTime0.setText(WDp.getDpTime(getBaseContext(), mKavaAccount.getCalcurateTime(mBalance.symbol, 0)));
            holder.mVestingGap0.setText(WDp.getUnbondingTimeleft(getBaseContext(), mKavaAccount.getCalcurateTime(mBalance.symbol, 0)));
            holder.mVestingAmount0.setText(WDp.getDpAmount2(getBaseContext(), mKavaAccount.getCalcurateAmount(mBalance.symbol, 0), 6, 6));
            if (getBaseDao().mKavaAccount.value.getCalcurateVestingCntByDenom(mBalance.symbol) > 1) {
                holder.mVestingLayer1.setVisibility(View.VISIBLE);
                holder.mVestingTime1.setText(WDp.getDpTime(getBaseContext(), mKavaAccount.getCalcurateTime(mBalance.symbol, 1)));
                holder.mVestingGap1.setText(WDp.getUnbondingTimeleft(getBaseContext(), mKavaAccount.getCalcurateTime(mBalance.symbol, 1)));
                holder.mVestingAmount1.setText(WDp.getDpAmount2(getBaseContext(), mKavaAccount.getCalcurateAmount(mBalance.symbol, 1), 6, 6));
            }
            if (getBaseDao().mKavaAccount.value.getCalcurateVestingCntByDenom(mBalance.symbol) > 2) {
                holder.mVestingLayer2.setVisibility(View.VISIBLE);
                holder.mVestingTime2.setText(WDp.getDpTime(getBaseContext(), mKavaAccount.getCalcurateTime(mBalance.symbol, 2)));
                holder.mVestingGap2.setText(WDp.getUnbondingTimeleft(getBaseContext(), mKavaAccount.getCalcurateTime(mBalance.symbol, 2)));
                holder.mVestingAmount2.setText(WDp.getDpAmount2(getBaseContext(), mKavaAccount.getCalcurateAmount(mBalance.symbol, 2), 6, 6));
            }
            if (getBaseDao().mKavaAccount.value.getCalcurateVestingCntByDenom(mBalance.symbol) > 3) {
                holder.mVestingLayer3.setVisibility(View.VISIBLE);
                holder.mVestingTime3.setText(WDp.getDpTime(getBaseContext(), mKavaAccount.getCalcurateTime(mBalance.symbol, 3)));
                holder.mVestingGap3.setText(WDp.getUnbondingTimeleft(getBaseContext(), mKavaAccount.getCalcurateTime(mBalance.symbol, 3)));
                holder.mVestingAmount3.setText(WDp.getDpAmount2(getBaseContext(), mKavaAccount.getCalcurateAmount(mBalance.symbol, 3), 6, 6));
            }
            if (getBaseDao().mKavaAccount.value.getCalcurateVestingCntByDenom(mBalance.symbol) > 4) {
                holder.mVestingLayer4.setVisibility(View.VISIBLE);
                holder.mVestingTime4.setText(WDp.getDpTime(getBaseContext(), mKavaAccount.getCalcurateTime(mBalance.symbol, 4)));
                holder.mVestingGap4.setText(WDp.getUnbondingTimeleft(getBaseContext(), mKavaAccount.getCalcurateTime(mBalance.symbol, 4)));
                holder.mVestingAmount4.setText(WDp.getDpAmount2(getBaseContext(), mKavaAccount.getCalcurateAmount(mBalance.symbol, 4), 6, 6));
            }
        }

        private void onBindHistory(RecyclerView.ViewHolder holder, int position) {
            final TokenHistoryHolder viewHolder = (TokenHistoryHolder)holder;
            if (mBaseChain.equals(COSMOS_MAIN) || mBaseChain.equals(KAVA_MAIN) || mBaseChain.equals(KAVA_TEST)) {
                final ResApiTxList.Data tx = mApiTxHistory.get(mHasVesting ? position - 2 : position - 1);
                if (tx.logs != null) {
                    viewHolder.historySuccess.setVisibility(View.GONE);
                } else {
                    viewHolder.historySuccess.setVisibility(View.VISIBLE);
                }
                viewHolder.historyType.setText(WDp.DpTxType(getBaseContext(), tx.messages, mAccount.address));
                viewHolder.history_time.setText(WDp.getTimeTxformat(getBaseContext(), tx.time));
                viewHolder.history_time_gap.setText(WDp.getTimeTxGap(getBaseContext(), tx.time));
                viewHolder.history_block.setText("" + tx.height + " block");
                viewHolder.historyRoot.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent txDetail = new Intent(getBaseContext(), TxDetailActivity.class);
                        txDetail.putExtra("txHash", tx.tx_hash);
                        txDetail.putExtra("isGen", false);
                        txDetail.putExtra("isSuccess", true);
                        startActivity(txDetail);
                    }
                });

            } else if (mBaseChain.equals(BaseChain.IRIS_MAIN)) {
                final ResApiTxList.Data tx = mApiTxHistory.get(position - 1);
                if(tx.result.Code > 0) {
                    viewHolder.historySuccess.setVisibility(View.VISIBLE);
                } else {
                    viewHolder.historySuccess.setVisibility(View.GONE);
                }
                viewHolder.historyType.setText(WDp.DpTxType(getBaseContext(), tx.messages, mAccount.address));
                viewHolder.history_time.setText(WDp.getTimeTxformat(getBaseContext(), tx.time));
                viewHolder.history_time_gap.setText(WDp.getTimeTxGap(getBaseContext(), tx.time));
                viewHolder.historyRoot.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent txDetail = new Intent(getBaseContext(), TxDetailActivity.class);
                        txDetail.putExtra("txHash", tx.tx_hash);
                        txDetail.putExtra("isGen", false);
                        txDetail.putExtra("isSuccess", true);
                        startActivity(txDetail);
                    }
                });

            } else if (mBaseChain.equals(BaseChain.BNB_MAIN) || mBaseChain.equals(BaseChain.BNB_TEST)) {
                final BnbHistory history = mBnbHistory.get(position - 1);
                viewHolder.historyType.setText(history.txType);
                viewHolder.historyType.setText(WDp.DpBNBTxType(getBaseContext(), history, mAccount.address));
                viewHolder.history_time.setText(WDp.getTimeformat(getBaseContext(), history.timeStamp));
                viewHolder.history_time_gap.setText(WDp.getTimeGap(getBaseContext(), history.timeStamp));
                viewHolder.history_block.setText(history.blockHeight + " block");
                viewHolder.historySuccess.setVisibility(View.GONE);
                viewHolder.historyRoot.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (history.txType.equals("HTL_TRANSFER") || history.txType.equals("CLAIM_HTL") || history.txType.equals("REFUND_HTL")) {
                            Intent txDetail = new Intent(getBaseContext(), TxDetailActivity.class);
                            txDetail.putExtra("txHash", history.txHash);
                            txDetail.putExtra("isGen", false);
                            txDetail.putExtra("isSuccess", true);
                            txDetail.putExtra("bnbTime", history.timeStamp);
                            startActivity(txDetail);

                        } else {
                            Intent webintent = new Intent(getBaseContext(), WebActivity.class);
                            webintent.putExtra("txid", history.txHash);
                            webintent.putExtra("chain", mBaseChain.getChain());
                            startActivity(webintent);
                        }
                    }
                });
            }
        }


        public class AtomHolder extends RecyclerView.ViewHolder {
            private RelativeLayout  mBtnSendAtom, mBtnReceiveAtom;
            private TextView        mTvAtomTotal, mTvAtomValue, mTvAtomAvailable, mTvAtomDelegated, mTvAtomUnBonding, mTvAtomRewards;

            public AtomHolder(View v) {
                super(v);
                mTvAtomTotal            = itemView.findViewById(R.id.dash_atom_amount);
                mTvAtomValue            = itemView.findViewById(R.id.dash_atom_value);
                mTvAtomAvailable        = itemView.findViewById(R.id.dash_atom_undelegate);
                mTvAtomDelegated        = itemView.findViewById(R.id.dash_atom_delegate);
                mTvAtomUnBonding        = itemView.findViewById(R.id.dash_atom_unbonding);
                mTvAtomRewards          = itemView.findViewById(R.id.dash_atom_reward);
                mBtnSendAtom            = itemView.findViewById(R.id.btn_atom_send);
                mBtnReceiveAtom         = itemView.findViewById(R.id.btn_atom_receive);
            }
        }

        public class IrisHolder extends RecyclerView.ViewHolder {
            private RelativeLayout  mBtnSendIris, mBtnReceiveIris;
            private TextView        mTvIrisTotal, mTvIrisValue, mTvIrisAvailable, mTvIrisDelegated, mTvIrisUnBonding, mTvIrisRewards;

            public IrisHolder(View v) {
                super(v);
                mTvIrisTotal            = itemView.findViewById(R.id.dash_iris_amount);
                mTvIrisValue            = itemView.findViewById(R.id.dash_iris_value);
                mTvIrisAvailable        = itemView.findViewById(R.id.dash_iris_undelegate);
                mTvIrisDelegated        = itemView.findViewById(R.id.dash_iris_delegate);
                mTvIrisUnBonding        = itemView.findViewById(R.id.dash_iris_unbonding);
                mTvIrisRewards          = itemView.findViewById(R.id.dash_iris_reward);
                mBtnReceiveIris         = itemView.findViewById(R.id.btn_iris_receive);
                mBtnSendIris            = itemView.findViewById(R.id.btn_iris_send);
            }
        }

        public class BnbHolder extends RecyclerView.ViewHolder {
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

                mTvBnbBalance.setText(WDp.getDpAmount(getBaseContext(), BigDecimal.ZERO, 8, mBaseChain));
                mTvBnbLocked.setText(WDp.getDpAmount(getBaseContext(), BigDecimal.ZERO, 8, mBaseChain));
                mTvBnbTotal.setText(WDp.getDpAmount(getBaseContext(), BigDecimal.ZERO, 8, mBaseChain));
                mTvBnbValue.setText(WDp.getValueOfBnb(getBaseContext(), getBaseDao(), BigDecimal.ZERO));
            }
        }

        public class KavaHolder extends RecyclerView.ViewHolder {
            private RelativeLayout  mBtnSendKava, mBtnReceiveKava;
            private RelativeLayout  mKavaVestingLayer, mKavaDepositLayer, mKavaIncentiveLayer;
            private TextView        mTvKavaTotal, mTvKavaValue, mTvKavaAvailable, mTvKavaVesting, mTvKavaDelegated, mTvKavaUnBonding, mTvKavaRewards, mTvKavaDeposit, mTvKavaIncentive;

            public KavaHolder(View v) {
                super(v);
                mTvKavaTotal            = itemView.findViewById(R.id.dash_kava_amount);
                mTvKavaValue            = itemView.findViewById(R.id.dash_kava_value);
                mTvKavaAvailable        = itemView.findViewById(R.id.dash_kava_undelegate);
                mTvKavaDelegated        = itemView.findViewById(R.id.dash_kava_delegate);
                mTvKavaUnBonding        = itemView.findViewById(R.id.dash_kava_unbonding);
                mTvKavaRewards          = itemView.findViewById(R.id.dash_kava_reward);
                mTvKavaVesting          = itemView.findViewById(R.id.dash_kava_vesting);
                mTvKavaDeposit          = itemView.findViewById(R.id.dash_kava_harvest_deposited);
                mTvKavaIncentive        = itemView.findViewById(R.id.dash_kava_unclaimed_incentive);
                mKavaVestingLayer       = itemView.findViewById(R.id.kava_harvest_vesting_layer);
                mKavaDepositLayer       = itemView.findViewById(R.id.kava_harvest_deposit_layer);
                mKavaIncentiveLayer     = itemView.findViewById(R.id.kava_harvest_incentive_layer);
                mBtnSendKava            = itemView.findViewById(R.id.btn_kava_send);
                mBtnReceiveKava         = itemView.findViewById(R.id.btn_kava_receive);
            }
        }

        public class OktHolder extends RecyclerView.ViewHolder {
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

        public class TokenHolder extends RecyclerView.ViewHolder {
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

        public class VestingHolder extends RecyclerView.ViewHolder {
                private CardView            mVestingRoot;
                private TextView            mVestingCnt, mVestingTotalAmount;
                private RelativeLayout      mVestingLayer0, mVestingLayer1, mVestingLayer2, mVestingLayer3, mVestingLayer4;
                private TextView            mVestingTime0, mVestingTime1, mVestingTime2, mVestingTime3, mVestingTime4;
                private TextView            mVestingGap0, mVestingGap1, mVestingGap2, mVestingGap3, mVestingGap4;
                private TextView            mVestingAmount0, mVestingAmount1, mVestingAmount2, mVestingAmount3, mVestingAmount4;

            public VestingHolder(View v) {
                super(v);
                mVestingRoot            = itemView.findViewById(R.id.card_root);
                mVestingCnt             = itemView.findViewById(R.id.vesting_count);
                mVestingTotalAmount     = itemView.findViewById(R.id.total_vesting_amount);
                mVestingLayer0          = itemView.findViewById(R.id.vesting_layer0);
                mVestingLayer1          = itemView.findViewById(R.id.vesting_layer1);
                mVestingLayer2          = itemView.findViewById(R.id.vesting_layer2);
                mVestingLayer3          = itemView.findViewById(R.id.vesting_layer3);
                mVestingLayer4          = itemView.findViewById(R.id.vesting_layer4);
                mVestingTime0           = itemView.findViewById(R.id.vesting_time0);
                mVestingTime1           = itemView.findViewById(R.id.vesting_time1);
                mVestingTime2           = itemView.findViewById(R.id.vesting_time2);
                mVestingTime3           = itemView.findViewById(R.id.vesting_time3);
                mVestingTime4           = itemView.findViewById(R.id.vesting_time4);
                mVestingGap0            = itemView.findViewById(R.id.vesting_gap0);
                mVestingGap1            = itemView.findViewById(R.id.vesting_gap1);
                mVestingGap2            = itemView.findViewById(R.id.vesting_gap2);
                mVestingGap3            = itemView.findViewById(R.id.vesting_gap3);
                mVestingGap4            = itemView.findViewById(R.id.vesting_gap4);
                mVestingAmount0         = itemView.findViewById(R.id.vesting_amount0);
                mVestingAmount1         = itemView.findViewById(R.id.vesting_amount1);
                mVestingAmount2         = itemView.findViewById(R.id.vesting_amount2);
                mVestingAmount3         = itemView.findViewById(R.id.vesting_amount3);
                mVestingAmount4         = itemView.findViewById(R.id.vesting_amount4);
            }
        }

        public class TokenHistoryHolder extends RecyclerView.ViewHolder {
            private CardView historyRoot;
            private TextView historyType, historySuccess, history_time, history_block, history_time_gap;

            public TokenHistoryHolder(View v) {
                super(v);
                historyRoot             = itemView.findViewById(R.id.card_history);
                historyType             = itemView.findViewById(R.id.history_type);
                historySuccess          = itemView.findViewById(R.id.history_success);
                history_time            = itemView.findViewById(R.id.history_time);
                history_block           = itemView.findViewById(R.id.history_block_height);
                history_time_gap        = itemView.findViewById(R.id.history_time_gap);
            }
        }
    }
}