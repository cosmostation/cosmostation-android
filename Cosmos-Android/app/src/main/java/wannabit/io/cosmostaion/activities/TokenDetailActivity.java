package wannabit.io.cosmostaion.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.dao.Balance;
import wannabit.io.cosmostaion.dao.BnbToken;
import wannabit.io.cosmostaion.dao.IrisToken;
import wannabit.io.cosmostaion.dialog.Dialog_WatchMode;
import wannabit.io.cosmostaion.fragment.MainHistoryFragment;
import wannabit.io.cosmostaion.model.type.BnbHistory;
import wannabit.io.cosmostaion.network.req.ReqTxToken;
import wannabit.io.cosmostaion.network.req.ReqTxVal;
import wannabit.io.cosmostaion.network.res.ResBnbTic;
import wannabit.io.cosmostaion.network.res.ResHistory;
import wannabit.io.cosmostaion.task.FetchTask.HistoryTask;
import wannabit.io.cosmostaion.task.FetchTask.TokenHistoryTask;
import wannabit.io.cosmostaion.task.FetchTask.ValHistoryTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WLog;
import wannabit.io.cosmostaion.utils.WUtil;

import static wannabit.io.cosmostaion.base.BaseConstant.COSMOS_ATOM;
import static wannabit.io.cosmostaion.base.BaseConstant.COSMOS_BNB;
import static wannabit.io.cosmostaion.base.BaseConstant.COSMOS_IRIS_ATTO;
import static wannabit.io.cosmostaion.base.BaseConstant.COSMOS_MUON;
import static wannabit.io.cosmostaion.base.BaseConstant.IS_TEST;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_IMG_URL;

public class TokenDetailActivity extends BaseActivity implements View.OnClickListener, TaskListener {

    private ImageView               mChainBg;
    private Toolbar                 mToolbar;

    private CardView                mAtomCard, mIrisCard, mBnbCard, TokenCard;
    private TextView                mTvAtomTotal, mTvAtomValue, mTvAtomAvailable,
                                    mTvAtomDelegated, mTvAtomUnBonding, mTvAtomRewards;
    private TextView                mTvIrisTotal, mTvIrisValue, mTvIrisAvailable,
                                    mTvIrisDelegated, mTvIrisUnBonding, mTvIrisRewards;
    private TextView                mTvBnbTotal, mTvBnbValue, mTvBnbBalance, mTvBnbLocked;
    private ImageView               mTokenImg, mTokenLink;
    private TextView                mTvTokenSymbol, mTvTokenTotal, mTvTokenValue, mTvTokenDenom,
                                    mTvTokenAvailable, mTvTokenReward;
    private LinearLayout            mAtomAction, mIrisAction, mBtnTokenDetail;
    private RelativeLayout          mBnbAction;
    private RelativeLayout          mBtnSendAtom, mBtnSendIris, mBtnSendBnb, mBtnSendToken;
    private TextView                mHistoryCnt;
    private SwipeRefreshLayout      mSwipeRefreshLayout;
    private RecyclerView            mRecyclerView;
    private LinearLayout            mEmptyHistory;

    private Balance                 mBalance;
    private IrisToken               mIrisToken;
    private BnbToken                mBnbToken;
    private HashMap<String, ResBnbTic>  mBnbTics = new HashMap<>();

    private TokenHistoryAdapter             mTokenHistoryAdapter;

    private ArrayList<ResHistory.InnerHits> mHistory = new ArrayList<>();
    private ArrayList<BnbHistory>           mBnbHistory = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_token_detail);
        mChainBg                = findViewById(R.id.chain_bg);
        mToolbar                = findViewById(R.id.tool_bar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mAtomCard               = findViewById(R.id.card_atom);
        mTvAtomTotal            = mAtomCard.findViewById(R.id.dash_atom_amount);
        mTvAtomValue            = mAtomCard.findViewById(R.id.dash_atom_value);
        mTvAtomAvailable        = mAtomCard.findViewById(R.id.dash_atom_undelegate);
        mTvAtomDelegated        = mAtomCard.findViewById(R.id.dash_atom_delegate);
        mTvAtomUnBonding        = mAtomCard.findViewById(R.id.dash_atom_unbonding);
        mTvAtomRewards          = mAtomCard.findViewById(R.id.dash_atom_reward);
        mAtomAction             = mAtomCard.findViewById(R.id.layer_cosmos_actions);
        mBtnSendAtom            = mAtomCard.findViewById(R.id.btn_atom_send);

        mIrisCard               = findViewById(R.id.card_iris);
        mTvIrisTotal            = mIrisCard.findViewById(R.id.dash_iris_amount);
        mTvIrisValue            = mIrisCard.findViewById(R.id.dash_iris_value);
        mTvIrisAvailable        = mIrisCard.findViewById(R.id.dash_iris_undelegate);
        mTvIrisDelegated        = mIrisCard.findViewById(R.id.dash_iris_delegate);
        mTvIrisUnBonding        = mIrisCard.findViewById(R.id.dash_iris_unbonding);
        mTvIrisRewards          = mIrisCard.findViewById(R.id.dash_iris_reward);
        mIrisAction             = mIrisCard.findViewById(R.id.layer_iris_actions);
        mBtnSendIris            = mIrisCard.findViewById(R.id.btn_iris_send);

        mBnbCard                = findViewById(R.id.card_bnb);
        mTvBnbTotal             = mBnbCard.findViewById(R.id.dash_bnb_amount);
        mTvBnbValue             = mBnbCard.findViewById(R.id.dash_bnb_value);
        mTvBnbBalance           = mBnbCard.findViewById(R.id.dash_bnb_balance);
        mTvBnbLocked            = mBnbCard.findViewById(R.id.dash_bnb_locked);
        mBnbAction              = mBnbCard.findViewById(R.id.btn_wallet_connect);
        mBtnSendBnb             = mBnbCard.findViewById(R.id.btn_bnb_send);

        TokenCard               = findViewById(R.id.card_token);
        mTokenImg               = TokenCard.findViewById(R.id.dash_token_icon);
        mTokenLink              = TokenCard.findViewById(R.id.dash_token_link);
        mTvTokenSymbol          = TokenCard.findViewById(R.id.dash_token_symbol);
        mTvTokenTotal           = TokenCard.findViewById(R.id.dash_token_amount);
        mTvTokenValue           = TokenCard.findViewById(R.id.dash_token_value);
        mTvTokenDenom           = TokenCard.findViewById(R.id.dash_token_denom);
        mTvTokenAvailable       = TokenCard.findViewById(R.id.dash_token_available);
        mTvTokenReward          = TokenCard.findViewById(R.id.dash_token_reward);
        mBtnSendToken           = TokenCard.findViewById(R.id.btn_token_send);
        mBtnTokenDetail         = TokenCard.findViewById(R.id.btn_token_web);

        mHistoryCnt             = findViewById(R.id.token_cnt);
        mSwipeRefreshLayout     = findViewById(R.id.layer_refresher);
        mRecyclerView           = findViewById(R.id.recycler);
        mEmptyHistory           = findViewById(R.id.empty_history);

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                onFetchTokenHistory();
            }
        });
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);
        mTokenHistoryAdapter = new TokenHistoryAdapter();
        mRecyclerView.setAdapter(mTokenHistoryAdapter);

        mBalance = getIntent().getParcelableExtra("balance");
        mIrisToken = getIntent().getParcelableExtra("irisToken");
        mBnbToken = getIntent().getParcelableExtra("bnbToken");
        mBnbTics = (HashMap<String, ResBnbTic>)getIntent().getSerializableExtra("bnbTics");

        mAllValidators = getIntent().getParcelableArrayListExtra("allValidators");
        mIrisReward = getIntent().getParcelableExtra("irisreward");
        mRewards = getIntent().getParcelableArrayListExtra("rewards");

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

    @Override
    protected void onResume() {
        super.onResume();

        mAccount = getBaseDao().onSelectAccount(getBaseDao().getLastUser());
        mBaseChain = BaseChain.getChain(mAccount.baseChain);

        if (mBaseChain.equals(BaseChain.COSMOS_MAIN) && mBalance.symbol.equals(COSMOS_ATOM) ||
                mBaseChain.equals(BaseChain.COSMOS_MAIN) && (IS_TEST && mBalance.symbol.equals(COSMOS_MUON))) {
            mAtomCard.setVisibility(View.VISIBLE);
            mAtomAction.setVisibility(View.GONE);
            mBtnSendAtom.setVisibility(View.VISIBLE);
            mBtnSendAtom.setOnClickListener(this);

            mBalances = getBaseDao().onSelectBalance(mAccount.id);
            mBondings = getBaseDao().onSelectBondingStates(mAccount.id);
            mUnbondings = getBaseDao().onSelectUnbondingStates(mAccount.id);

            mTvAtomTotal.setText(WDp.getDpAllAtom(this, mBalances, mBondings, mUnbondings, mRewards, mAllValidators, mBaseChain));
            mTvAtomAvailable.setText(WDp.getDpAvailableCoin(this, mBalances, mBaseChain, COSMOS_ATOM));
            mTvAtomDelegated.setText(WDp.getDpAllDelegatedAmount(this, mBondings, mAllValidators, mBaseChain));
            mTvAtomUnBonding.setText(WDp.getDpAllUnbondingAmount(this, mUnbondings, mAllValidators, mBaseChain));
            mTvAtomRewards.setText(WDp.getDpAllAtomRewardAmount(this, mRewards, mBaseChain));

            BigDecimal totalAmount = WDp.getAllAtom(mBalances, mBondings, mUnbondings, mRewards, mAllValidators);
            mTvAtomValue.setText(WDp.getTotalValueAtom(this, getBaseDao(), totalAmount));

        } else if (mBaseChain.equals(BaseChain.IRIS_MAIN) && mBalance.symbol.equals(COSMOS_IRIS_ATTO)) {
            mIrisCard.setVisibility(View.VISIBLE);
            mIrisAction.setVisibility(View.GONE);
            mBtnSendIris.setVisibility(View.VISIBLE);
            mBtnSendIris.setOnClickListener(this);

            mBalances = getBaseDao().onSelectBalance(mAccount.id);
            mBondings = getBaseDao().onSelectBondingStates(mAccount.id);
            mUnbondings = getBaseDao().onSelectUnbondingStates(mAccount.id);

            mTvIrisTotal.setText(WDp.getDpAmount(this, WDp.getAllIris(mBalances, mBondings, mUnbondings, mIrisReward), 18, mBaseChain));
            mTvIrisAvailable.setText(WDp.getDpAmount(this, WDp.getAvailableCoin(mBalances, COSMOS_IRIS_ATTO), 18, mBaseChain));
            mTvIrisDelegated.setText(WDp.getDpAmount(this, WDp.getAllDeleagtedAmount(mBondings, mAllValidators, mBaseChain), 18, mBaseChain));
            mTvIrisUnBonding.setText(WDp.getDpAmount(this, WDp.getUnbondingAmount(mUnbondings, mAllValidators), 18, mBaseChain));
            mTvIrisRewards.setText(WDp.getDpAmount(this, mIrisReward.getSimpleIrisReward(), 18, mBaseChain));

            BigDecimal totalAmount = WDp.getAllIris(mBalances, mBondings, mUnbondings, mIrisReward);
            mTvIrisValue.setText(WDp.getTotalValueIris(this, getBaseDao(), totalAmount));

        } else if (mBaseChain.equals(BaseChain.BNB_MAIN) && mBalance.symbol.equals(COSMOS_BNB)) {
            mBnbCard.setVisibility(View.VISIBLE);
            mBnbAction.setVisibility(View.GONE);
            mBtnSendBnb.setVisibility(View.VISIBLE);
            mBtnSendBnb.setOnClickListener(this);

            if (mBnbToken != null) {
                BigDecimal totalAmount = mBalance.locked.add(mBalance.balance);
                mTvBnbBalance.setText(WDp.getDpAmount(this, mBalance.balance, 8, mBaseChain));
                mTvBnbLocked.setText(WDp.getDpAmount(this, mBalance.locked, 8, mBaseChain));
                mTvBnbTotal.setText(WDp.getDpAmount(this, mBalance.locked.add(mBalance.balance), 8, mBaseChain));
                mTvBnbValue.setText(WDp.getTotalValueBnb(this, getBaseDao(), totalAmount));

            } else {
                mTvBnbBalance.setText(WDp.getDpAmount(this, BigDecimal.ZERO, 8, mBaseChain));
                mTvBnbLocked.setText(WDp.getDpAmount(this, BigDecimal.ZERO, 8, mBaseChain));
                mTvBnbTotal.setText(WDp.getDpAmount(this, BigDecimal.ZERO, 8, mBaseChain));
                mTvBnbValue.setText(WDp.getTotalValueBnb(this, getBaseDao(), BigDecimal.ZERO));
            }

        } else {
            TokenCard.setVisibility(View.VISIBLE);
            mBtnSendToken.setVisibility(View.VISIBLE);
            mBalances = getBaseDao().onSelectBalance(mAccount.id);

            if (mBaseChain.equals(BaseChain.COSMOS_MAIN)) {

            } else if (mBaseChain.equals(BaseChain.IRIS_MAIN) && mIrisToken != null) {
                mTokenLink.setVisibility(View.GONE);
                mBtnSendToken.setOnClickListener(this);

            } else if (mBaseChain.equals(BaseChain.BNB_MAIN) && mBnbToken != null) {
                mTokenLink.setVisibility(View.VISIBLE);
                mBtnTokenDetail.setOnClickListener(this);
                mBtnSendToken.setOnClickListener(this);
                mTvTokenSymbol.setText(mBnbToken.original_symbol);
                mTvTokenTotal.setText(WDp.getDpAmount(this, mBalance.getAllBnbBalance(), 8, mBaseChain));

                BigDecimal amount = BigDecimal.ZERO;
                ResBnbTic tic = mBnbTics.get(WUtil.getBnbTicSymbol(mBalance.symbol));
                if (tic != null) {
                    amount = mBalance.exchangeToBnbAmount(tic);
                }

                mTvTokenValue.setText(WDp.getTotalValueBnb(this, getBaseDao(), amount));
                mTvTokenDenom.setText(mBnbToken.symbol);
                mTvTokenAvailable.setText(WDp.getDpAmount(this, mBalance.balance, 8, mBaseChain));
                mTvTokenReward.setText(WDp.getDpAmount(this, BigDecimal.ZERO, 8, mBaseChain));
                try {
                    Picasso.get().load(TOKEN_IMG_URL+mBnbToken.original_symbol+".png")
                            .fit().placeholder(R.drawable.token_ic).error(R.drawable.token_ic)
                            .into(mTokenImg);
                }catch (Exception e) {}


            } else {
                onBackPressed();
            }
        }

        onFetchTokenHistory();
    }

    private void onFetchTokenHistory() {
        if (mBaseChain.equals(BaseChain.COSMOS_MAIN)) {
            ReqTxToken req = new ReqTxToken(0, 0, true, mAccount.address, mBalance.symbol);
            new TokenHistoryTask(getBaseApplication(), this, req, BaseChain.getChain(mAccount.baseChain)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        } else if (mBaseChain.equals(BaseChain.IRIS_MAIN)) {
            ReqTxToken req = new ReqTxToken(0, 1, true, mAccount.address, mBalance.symbol);
            new TokenHistoryTask(getBaseApplication(), this, req, BaseChain.getChain(mAccount.baseChain)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        } else if (mBaseChain.equals(BaseChain.BNB_MAIN)) {
            new HistoryTask(getBaseApplication(), this, null, mBaseChain)
                    .executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, mAccount.address, WDp.threeMonthAgoTimeString(), WDp.cTimeString(), mBnbToken.symbol);

        }
    }

    @Override
    public void onTaskResponse(TaskResult result) {
        if(isFinishing()) return;
        if (result.taskType == BaseConstant.TASK_FETCH_TOKEN_HISTORY) {
            ArrayList<ResHistory.InnerHits> hits = (ArrayList<ResHistory.InnerHits>)result.resultData;
            if(hits != null && hits.size() > 0) {
                mHistory = hits;
                mHistoryCnt.setText(""+mHistory.size());
                mTokenHistoryAdapter.notifyDataSetChanged();
                mEmptyHistory.setVisibility(View.GONE);
                mRecyclerView.setVisibility(View.VISIBLE);
            } else {
                mHistoryCnt.setText("0");
                mEmptyHistory.setVisibility(View.VISIBLE);
                mRecyclerView.setVisibility(View.GONE);
            }

        } else if (result.taskType == BaseConstant.TASK_FETCH_BNB_HISTORY) {
            ArrayList<BnbHistory> hits = (ArrayList<BnbHistory>)result.resultData;
            if (hits != null && hits.size() > 0) {
                mBnbHistory = hits;
                mHistoryCnt.setText(""+mBnbHistory.size());
                mTokenHistoryAdapter.notifyDataSetChanged();
                mEmptyHistory.setVisibility(View.GONE);
                mRecyclerView.setVisibility(View.VISIBLE);

            } else {
                mHistoryCnt.setText("0");
                mEmptyHistory.setVisibility(View.VISIBLE);
                mRecyclerView.setVisibility(View.GONE);
            }
        }
        mSwipeRefreshLayout.setRefreshing(false);
    }



    @Override
    public void onClick(View v) {
        if (v.equals(mBtnSendAtom)) {
            if (onCheckSendable()) {
                startActivity(new Intent(TokenDetailActivity.this, SendActivity.class));
            }

        } else if (v.equals(mBtnSendIris)) {
            if (onCheckSendable()) {
                Intent intent = new Intent(TokenDetailActivity.this, SendActivity.class);
                intent.putExtra("irisToken", mIrisToken);
                startActivity(intent);
            }

        } else if (v.equals(mBtnSendBnb)) {
            if (onCheckSendable()) {
                Intent intent = new Intent(TokenDetailActivity.this, SendActivity.class);
                intent.putExtra("bnbToken", mBnbToken);
                startActivity(intent);
            }

        } else if (v.equals(mBtnSendToken)) {
            if (onCheckSendable()) {
                Intent intent = new Intent(TokenDetailActivity.this, SendActivity.class);
                intent.putExtra("irisToken", mIrisToken);
                intent.putExtra("bnbToken", mBnbToken);
                intent.putExtra("bnbTics", mBnbTics);
                startActivity(intent);
            }

        } else if (v.equals(mBtnTokenDetail)) {
            Intent webintent = new Intent(this, WebActivity.class);
            webintent.putExtra("asset", mBnbToken.symbol);
            webintent.putExtra("chain", mBaseChain.getChain());
            startActivity(webintent);
        }
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
        if (mBaseChain.equals(BaseChain.COSMOS_MAIN)) {
            for (Balance balance:balances) {
                if (balance.symbol.equals(BaseConstant.COSMOS_ATOM) && ((balance.balance.compareTo(BigDecimal.ONE)) > 0)) {
                    hasbalance  = true;
                }
            }
        } else if (mBaseChain.equals(BaseChain.IRIS_MAIN)) {
            for (Balance balance:balances) {
                if (balance.symbol.equals(BaseConstant.COSMOS_IRIS_ATTO) && ((balance.balance.compareTo(new BigDecimal("200000000000000000"))) > 0)) {
                    hasbalance  = true;
                }
            }
            WLog.w("mIrisToken " + mIrisToken.base_token.symbol);
        } else if (mBaseChain.equals(BaseChain.BNB_MAIN)) {
            for (Balance balance:balances) {
                if (balance.symbol.equals(BaseConstant.COSMOS_BNB) && ((balance.balance.compareTo(new BigDecimal("0.000375"))) > 0)) {
                    hasbalance  = true;
                }
            }
            WLog.w("mBnbToken " + mBnbToken.symbol);
        }

        if(!hasbalance){
            Toast.makeText(getBaseContext(), R.string.error_not_enough_budget, Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private class TokenHistoryAdapter extends RecyclerView.Adapter<TokenHistoryAdapter.HistoryHolder> {

        @NonNull
        @Override
        public HistoryHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new HistoryHolder(getLayoutInflater().inflate(R.layout.item_history, viewGroup, false));
        }

        @Override
        public void onBindViewHolder(@NonNull HistoryHolder viewHolder, int position) {
            if (mBaseChain.equals(BaseChain.COSMOS_MAIN) || mBaseChain.equals(BaseChain.IRIS_MAIN)) {
                final ResHistory.Source source = mHistory.get(position)._source;
                if (mBaseChain.equals(BaseChain.COSMOS_MAIN)) {
                    if(!source.result.isSuccess()) {
                        viewHolder.historySuccess.setVisibility(View.VISIBLE);
                    } else {
                        viewHolder.historySuccess.setVisibility(View.GONE);
                    }
                } else if (mBaseChain.equals(BaseChain.IRIS_MAIN)) {
                    if(source.result.Code > 0) {
                        viewHolder.historySuccess.setVisibility(View.VISIBLE);
                    } else {
                        viewHolder.historySuccess.setVisibility(View.GONE);
                    }
                }
                viewHolder.historyType.setText(WDp.DpTxType(getBaseContext(), source.tx.value.msg, mAccount.address));
                viewHolder.history_time.setText(WDp.getTimeformat(getBaseContext(), source.time));
                viewHolder.history_time_gap.setText(WDp.getTimeGap(getBaseContext(), source.time));
                viewHolder.history_block.setText(source.height + " block");
                viewHolder.historyRoot.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent webintent = new Intent(TokenDetailActivity.this, WebActivity.class);
                        webintent.putExtra("txid", source.hash);
                        webintent.putExtra("chain", mBaseChain.getChain());
                        startActivity(webintent);
                    }
                });

            } else if (mBaseChain.equals(BaseChain.BNB_MAIN)) {
                final BnbHistory history = mBnbHistory.get(position);
                viewHolder.historyType.setText(history.txType);
                viewHolder.history_time.setText(WDp.getTimeformat(getBaseContext(), history.timeStamp));
                viewHolder.history_time_gap.setText(WDp.getTimeGap(getBaseContext(), history.timeStamp));
                viewHolder.history_block.setText(history.blockHeight + " block");
                viewHolder.historySuccess.setVisibility(View.GONE);
            }
        }

        @Override
        public int getItemCount() {
            if (mBaseChain.equals(BaseChain.COSMOS_MAIN) || mBaseChain.equals(BaseChain.IRIS_MAIN)) {
                return mHistory.size();
            } else if (mBaseChain.equals(BaseChain.BNB_MAIN)) {
                return mBnbHistory.size();
            }
            return 0;
        }


        public class HistoryHolder extends RecyclerView.ViewHolder {
            private CardView historyRoot;
            private TextView historyType, historySuccess, history_time, history_block, history_time_gap;

            public HistoryHolder(View v) {
                super(v);
                historyRoot         = itemView.findViewById(R.id.card_history);
                historyType         = itemView.findViewById(R.id.history_type);
                historySuccess      = itemView.findViewById(R.id.history_success);
                history_time        = itemView.findViewById(R.id.history_time);
                history_block       = itemView.findViewById(R.id.history_block_height);
                history_time_gap    = itemView.findViewById(R.id.history_time_gap);
            }
        }
    }
}
