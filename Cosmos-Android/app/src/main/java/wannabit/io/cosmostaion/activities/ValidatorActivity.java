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
import android.text.SpannableString;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.math.BigDecimal;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.dao.Balance;
import wannabit.io.cosmostaion.dao.BondingState;
import wannabit.io.cosmostaion.dao.Reward;
import wannabit.io.cosmostaion.dao.UnBondingState;
import wannabit.io.cosmostaion.dialog.Dialog_Not_Top_100;
import wannabit.io.cosmostaion.dialog.Dialog_RedelegationLimited;
import wannabit.io.cosmostaion.dialog.Dialog_WatchMode;
import wannabit.io.cosmostaion.model.type.Validator;
import wannabit.io.cosmostaion.network.req.ReqTxVal;
import wannabit.io.cosmostaion.network.res.ResHistory;
import wannabit.io.cosmostaion.network.res.ResLcdBonding;
import wannabit.io.cosmostaion.network.res.ResLcdIrisPool;
import wannabit.io.cosmostaion.network.res.ResLcdIrisRedelegate;
import wannabit.io.cosmostaion.network.res.ResLcdIrisReward;
import wannabit.io.cosmostaion.task.FetchTask.IrisRedelegateStateTask;
import wannabit.io.cosmostaion.task.FetchTask.IrisRewardTask;
import wannabit.io.cosmostaion.task.FetchTask.ValHistoryTask;
import wannabit.io.cosmostaion.task.SingleFetchTask.CheckWithdrawAddressTask;
import wannabit.io.cosmostaion.task.SingleFetchTask.SingleBondingStateTask;
import wannabit.io.cosmostaion.task.SingleFetchTask.SingleRedelegateStateTask;
import wannabit.io.cosmostaion.task.SingleFetchTask.SingleRewardTask;
import wannabit.io.cosmostaion.task.SingleFetchTask.SingleSelfBondingStateTask;
import wannabit.io.cosmostaion.task.SingleFetchTask.SingleUnBondingStateTask;
import wannabit.io.cosmostaion.task.SingleFetchTask.SingleValidatorInfoTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WKey;

import static wannabit.io.cosmostaion.base.BaseConstant.COSMOS_ATOM;
import static wannabit.io.cosmostaion.base.BaseConstant.COSMOS_VAL_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.IRIS_VAL_URL;

public class ValidatorActivity extends BaseActivity implements TaskListener {

    private ImageView                       mChainBg;
    private Toolbar                         mToolbar;
    private SwipeRefreshLayout              mSwipeRefreshLayout;
    private RecyclerView                    mRecyclerView;

    private ValidatorAdapter                mValidatorAdapter;

    private Validator                       mValidator;
    private BondingState                    mBondingState;
    private ArrayList<UnBondingState>       mUnBondingStates;
    private Reward                          mReward;
    private ArrayList<ResHistory.InnerHits> mTx = new ArrayList<>();

    private SpannableString                 mSelfBondingRate;

    private int                             mTaskCount;
    private TaskResult                      mRedelegateResultThisVal;

    public BigDecimal                       mBondedToken = BigDecimal.ZERO;
    public BigDecimal                       mProvisions = BigDecimal.ZERO;

    public ResLcdIrisReward                 mIrisReward;
    public ResLcdIrisPool                   mIrisPool;
    public ArrayList<ResLcdIrisRedelegate>  mIrisRedelegateState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validator);
        mChainBg                    = findViewById(R.id.chain_bg);
        mToolbar                    = findViewById(R.id.tool_bar);
        mSwipeRefreshLayout         = findViewById(R.id.layer_refresher);
        mRecyclerView               = findViewById(R.id.recycler);

        mAccount        = getBaseDao().onSelectAccount(getBaseDao().getLastUser());
        mBaseChain      = BaseChain.getChain(mAccount.baseChain);
        mValidator      = getIntent().getParcelableExtra("validator");

        mBondedToken = new BigDecimal(getIntent().getStringExtra("bondedToken"));
        mProvisions = new BigDecimal(getIntent().getStringExtra("provisions"));

        if (getIntent().getParcelableExtra("irispool") != null) {
            mIrisPool = getIntent().getParcelableExtra("irispool");
        }

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                onInitFetch();

            }
        });
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);
    }

    @Override
    protected void onResume() {
        super.onResume();


        if(mAccount == null) {
            onBackPressed();
        }

        if(mValidator == null || TextUtils.isEmpty(mValidator.operator_address)) {
            onBackPressed();
        }

        mBondingState       = getBaseDao().onSelectBondingState(mAccount.id, mValidator.operator_address);
        mUnBondingStates    = getBaseDao().onSelectUnbondingStates(mAccount.id, mValidator.operator_address);

        mValidatorAdapter = new ValidatorAdapter();
        mRecyclerView.setAdapter(mValidatorAdapter);

        onInitFetch();
        onFetchValHistory();
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


    private void onInitFetch() {
        if(mTaskCount > 0) return;
        if (mBaseChain.equals(BaseChain.COSMOS_MAIN)) {
            mTaskCount = 5;
            new SingleValidatorInfoTask(getBaseApplication(), this, mValidator.operator_address, BaseChain.getChain(mAccount.baseChain)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new SingleBondingStateTask(getBaseApplication(), this, mAccount, mValidator.operator_address).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new SingleSelfBondingStateTask(getBaseApplication(), this, WKey.convertDpOpAddressToDpAddress(mValidator.operator_address, BaseChain.getChain(mAccount.baseChain)), mValidator.operator_address, BaseChain.getChain(mAccount.baseChain)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new SingleUnBondingStateTask(getBaseApplication(), this, mAccount, mValidator.operator_address).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new SingleRedelegateStateTask(getBaseApplication(), this, mAccount, mValidator).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            if(mBondingState != null) {
                mTaskCount = mTaskCount + 1;
                new SingleRewardTask(getBaseApplication(), this, mAccount, mValidator.operator_address).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            }

        } else if (mBaseChain.equals(BaseChain.IRIS_MAIN)) {
            mTaskCount = 6;
            new SingleValidatorInfoTask(getBaseApplication(), this, mValidator.operator_address, BaseChain.getChain(mAccount.baseChain)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new SingleBondingStateTask(getBaseApplication(), this, mAccount, mValidator.operator_address).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new SingleSelfBondingStateTask(getBaseApplication(), this, WKey.convertDpOpAddressToDpAddress(mValidator.operator_address, BaseChain.getChain(mAccount.baseChain)), mValidator.operator_address, BaseChain.getChain(mAccount.baseChain)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new SingleUnBondingStateTask(getBaseApplication(), this, mAccount, mValidator.operator_address).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new IrisRewardTask(getBaseApplication(), this, mAccount).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new IrisRedelegateStateTask(getBaseApplication(), this, mAccount).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        }


    }

    private void onCheckDelegate() {
        if (mAccount == null || mValidator == null) return;
        if (!mAccount.hasPrivateKey) {
            Dialog_WatchMode add = Dialog_WatchMode.newInstance();
            add.setCancelable(true);
            getSupportFragmentManager().beginTransaction().add(add, "dialog").commitNowAllowingStateLoss();
            return;
        }

        ArrayList<Balance> balances = getBaseDao().onSelectBalance(mAccount.id);
        boolean hasbalance = false;
        if (mBaseChain.equals(BaseChain.COSMOS_MAIN)) {
            for (Balance balance:balances) {
                if(balance.symbol.equals(BaseConstant.COSMOS_ATOM) && ((balance.balance.compareTo(new BigDecimal("1"))) > 0)) {
                    hasbalance  = true;
                }
            }
        } else if (mBaseChain.equals(BaseChain.IRIS_MAIN)) {
            for (Balance balance:balances) {
                if(balance.symbol.equals(BaseConstant.COSMOS_IRIS_ATTO) && ((balance.balance.compareTo(new BigDecimal("400000000000000000"))) > 0)) {
                    hasbalance  = true;
                }
            }
        }
        if (!hasbalance) {
            Toast.makeText(getBaseContext(), R.string.error_not_enough_budget, Toast.LENGTH_SHORT).show();
            return;
        }

        if (mValidator.jailed) {
            Toast.makeText(getBaseContext(), R.string.error_disabled_jailed, Toast.LENGTH_SHORT).show();
            return;
        }

        if (mValidator.status != Validator.BONDED) {
            Dialog_Not_Top_100 add = Dialog_Not_Top_100.newInstance(null);
            add.setCancelable(true);
            getSupportFragmentManager().beginTransaction().add(add, "dialog").commitNowAllowingStateLoss();
        } else {
            onStartDelegate();
        }

    }

    public void onStartDelegate() {
        Intent toDelegate = new Intent(ValidatorActivity.this, DelegateActivity.class);
        toDelegate.putExtra("validator", mValidator);
        startActivity(toDelegate);
    }

    public void onCheckRedelegate() {
        if (!mAccount.hasPrivateKey) {
            Dialog_WatchMode add = Dialog_WatchMode.newInstance();
            add.setCancelable(true);
            getSupportFragmentManager().beginTransaction().add(add, "dialog").commitNowAllowingStateLoss();
            return;
        }

        if (mBondingState == null || mBondingState.getBondingAmount(mValidator).compareTo(BigDecimal.ZERO) <= 0) {
            Toast.makeText(getBaseContext(), R.string.error_no_delegate, Toast.LENGTH_SHORT).show();
            return;
        }

        ArrayList<Balance> balances = getBaseDao().onSelectBalance(mAccount.id);
        boolean hasbalance = false;
        if (mBaseChain.equals(BaseChain.COSMOS_MAIN)) {
            for (Balance balance:balances) {
                if (balance.symbol.equals(BaseConstant.COSMOS_ATOM) && ((balance.balance.compareTo(new BigDecimal("1"))) > 0)) {
                    hasbalance  = true;
                }
            }
            if (!(mRedelegateResultThisVal != null && mRedelegateResultThisVal.isSuccess && mRedelegateResultThisVal.resultData == null)) {
                Dialog_RedelegationLimited add = Dialog_RedelegationLimited.newInstance();
                add.setCancelable(true);
                getSupportFragmentManager().beginTransaction().add(add, "dialog").commitNowAllowingStateLoss();
                return;
            }
        } else if (mBaseChain.equals(BaseChain.IRIS_MAIN)) {
            for (Balance balance:balances) {
                if (balance.symbol.equals(BaseConstant.COSMOS_IRIS_ATTO) && ((balance.balance.compareTo(new BigDecimal("520000000000000000"))) > 0)) {
                    hasbalance  = true;
                }
            }
            if (mIrisRedelegateState != null && mIrisRedelegateState.size() > 0) {
                for (ResLcdIrisRedelegate state:mIrisRedelegateState) {
                    if (state.validator_dst_addr.equals(mValidator.operator_address)) {
                        Dialog_RedelegationLimited add = Dialog_RedelegationLimited.newInstance();
                        add.setCancelable(true);
                        getSupportFragmentManager().beginTransaction().add(add, "dialog").commitNowAllowingStateLoss();
                        return;
                    }
                }
            }
        }

        if (!hasbalance) {
            Toast.makeText(getBaseContext(), R.string.error_not_enough_budget, Toast.LENGTH_SHORT).show();
            return;
        }
        onStartRedelegate();
    }

    public void onStartRedelegate() {
        Intent reDelegate = new Intent(ValidatorActivity.this, RedelegateActivity.class);
        reDelegate.putExtra("validator", mValidator);
        reDelegate.putExtra("bondedToken", mBondedToken.toPlainString());
        reDelegate.putExtra("provisions", mProvisions.toPlainString());
        reDelegate.putExtra("irispool", mIrisPool);
        reDelegate.putExtra("irisReState", mIrisRedelegateState);
        startActivity(reDelegate);
    }

    private void onStartUndelegate() {
        if (mAccount == null || mValidator == null) return;
        if (!mAccount.hasPrivateKey) {
            Dialog_WatchMode add = Dialog_WatchMode.newInstance();
            add.setCancelable(true);
            getSupportFragmentManager().beginTransaction().add(add, "dialog").commitNowAllowingStateLoss();
            return;
        }
        if (mBondingState == null || mBondingState.getBondingAmount(mValidator).compareTo(BigDecimal.ZERO) <= 0) {
            Toast.makeText(getBaseContext(), R.string.error_no_delegate, Toast.LENGTH_SHORT).show();
            return;
        }

        if (mBaseChain.equals(BaseChain.COSMOS_MAIN)) {
            if (mUnBondingStates != null && mUnBondingStates.size() >= 7){
                Toast.makeText(getBaseContext(), R.string.error_unbond_cnt_over, Toast.LENGTH_SHORT).show();
                return;
            }
        } else if (mBaseChain.equals(BaseChain.IRIS_MAIN)) {
            if (mUnBondingStates != null && mUnBondingStates.size() >= 1){
                Toast.makeText(getBaseContext(), R.string.error_unbond_cnt_over_iris, Toast.LENGTH_SHORT).show();
                return;
            }
        }

        ArrayList<Balance> balances = getBaseDao().onSelectBalance(mAccount.id);
        boolean hasbalance = false;
        if (mBaseChain.equals(BaseChain.COSMOS_MAIN)) {
            for (Balance balance:balances) {
                if (balance.symbol.equals(BaseConstant.COSMOS_ATOM) && ((balance.balance.compareTo(BigDecimal.ONE)) >= 0)) {
                    hasbalance  = true;
                }
            }

        } else if (mBaseChain.equals(BaseChain.IRIS_MAIN)) {
            for (Balance balance:balances) {
                if (balance.symbol.equals(BaseConstant.COSMOS_IRIS_ATTO) && ((balance.balance.compareTo(new BigDecimal("400000000000000000"))) >= 0)) {
                    hasbalance  = true;
                }
            }
        }
        if (!hasbalance) {
            Toast.makeText(getBaseContext(), R.string.error_not_enough_budget, Toast.LENGTH_SHORT).show();
            return;
        }

        Intent unDelegate = new Intent(ValidatorActivity.this, UndelegateActivity.class);
        unDelegate.putExtra("validator", mValidator);
        startActivity(unDelegate);
    }

    private void onGetReward() {
        if (mAccount == null || mValidator == null) return;
        if (!mAccount.hasPrivateKey) {
            Dialog_WatchMode add = Dialog_WatchMode.newInstance();
            add.setCancelable(true);
            getSupportFragmentManager().beginTransaction().add(add, "dialog").commitNowAllowingStateLoss();
            return;
        }

        ArrayList<Balance> balances = getBaseDao().onSelectBalance(mAccount.id);
        boolean hasbalance = false;
        if (mBaseChain.equals(BaseChain.COSMOS_MAIN)) {
            if(mReward == null || mReward.amount == null || mReward.amount.get(0) == null) {
                Toast.makeText(getBaseContext(), R.string.error_not_enough_reward, Toast.LENGTH_SHORT).show();
                return;
            }

            if (new BigDecimal(mReward.amount.get(0).amount).compareTo(new BigDecimal("1")) <= 0) {
                Toast.makeText(getBaseContext(), R.string.error_small_reward, Toast.LENGTH_SHORT).show();
                return;
            }

            for (Balance balance:balances) {
                if(balance.symbol.equals(BaseConstant.COSMOS_ATOM) && ((balance.balance.compareTo(BigDecimal.ONE)) >= 0)) {
                    hasbalance  = true;
                }
            }

        } else if (mBaseChain.equals(BaseChain.IRIS_MAIN)) {
            if (mIrisReward == null || mIrisReward.getPerValReward(mValidator.operator_address) == BigDecimal.ZERO) {
                Toast.makeText(getBaseContext(), R.string.error_not_enough_reward, Toast.LENGTH_SHORT).show();
                return;
            }

            if (mIrisReward.getPerValReward(mValidator.operator_address).compareTo(new BigDecimal("400000000000000000")) <= 0) {
                Toast.makeText(getBaseContext(), R.string.error_small_reward, Toast.LENGTH_SHORT).show();
                return;
            }

            for (Balance balance:balances) {
                if(balance.symbol.equals(BaseConstant.COSMOS_IRIS_ATTO) && ((balance.balance.compareTo(new BigDecimal("400000000000000000"))) >= 0)) {
                    hasbalance  = true;
                }
            }
        }
        if (!hasbalance) {
            Toast.makeText(getBaseContext(), R.string.error_not_enough_budget, Toast.LENGTH_SHORT).show();
            return;
        }

        ArrayList<Validator> val = new ArrayList<>();
        val.add(mValidator);
        Intent claimReward = new Intent(ValidatorActivity.this, ClaimRewardActivity.class);
        claimReward.putExtra("opAddresses", val);
        startActivity(claimReward);
    }

    private void onCheckReInvest() {
        if (!mAccount.hasPrivateKey) {
            Dialog_WatchMode add = Dialog_WatchMode.newInstance();
            add.setCancelable(true);
            getSupportFragmentManager().beginTransaction().add(add, "dialog").commitNowAllowingStateLoss();
            return;
        }

        ArrayList<Balance> balances = getBaseDao().onSelectBalance(mAccount.id);
        boolean hasbalance = false;
        if (mBaseChain.equals(BaseChain.COSMOS_MAIN)) {
            if (mReward == null || mReward.amount == null || mReward.amount.get(0) == null) {
                Toast.makeText(getBaseContext(), R.string.error_not_enough_reward, Toast.LENGTH_SHORT).show();
                return;
            }

            if (new BigDecimal(mReward.amount.get(0).amount).compareTo(BigDecimal.ONE) <= 0) {
                Toast.makeText(getBaseContext(), R.string.error_small_reward, Toast.LENGTH_SHORT).show();
                return;
            }

            for (Balance balance:balances) {
                if(balance.symbol.equals(BaseConstant.COSMOS_ATOM) && ((balance.balance.compareTo(BigDecimal.ONE)) >= 0)) {
                    hasbalance  = true;
                }
            }

        } else if (mBaseChain.equals(BaseChain.IRIS_MAIN)) {
            if (mIrisReward == null) {
                Toast.makeText(getBaseContext(), R.string.error_not_enough_reward, Toast.LENGTH_SHORT).show();
                return;
            }

            if (mIrisReward.getPerValReward(mValidator.operator_address).compareTo(new BigDecimal("400000000000000000")) <= 0) {
                Toast.makeText(getBaseContext(), R.string.error_small_reward, Toast.LENGTH_SHORT).show();
                return;
            }

            for (Balance balance:balances) {
                if(balance.symbol.equals(BaseConstant.COSMOS_IRIS_ATTO) && ((balance.balance.compareTo(new BigDecimal("400000000000000000"))) >= 0)) {
                    hasbalance  = true;
                }
            }
        }

        if (!hasbalance) {
            Toast.makeText(getBaseContext(), R.string.error_not_enough_budget, Toast.LENGTH_SHORT).show();
            return;
        }

        new CheckWithdrawAddressTask(getBaseApplication(), new TaskListener() {
            @Override
            public void onTaskResponse(TaskResult result) {
                String rewardAddress = (String)result.resultData;
                if (rewardAddress == null || !rewardAddress.equals(mAccount.address)) {
                    Toast.makeText(getBaseContext(), R.string.error_reward_address_changed_msg, Toast.LENGTH_SHORT).show();
                } else {
                    Intent reinvest = new Intent(ValidatorActivity.this, ReInvestActivity.class);
                    reinvest.putExtra("validator", mValidator);
                    startActivity(reinvest);
                }
            }
        }, mAccount).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

    }


    private void onFetchValHistory() {
        mTaskCount++;
        if (mBaseChain.equals(BaseChain.COSMOS_MAIN)) {
            ReqTxVal req = new ReqTxVal(0, 0, true, mAccount.address, mValidator.operator_address);
//            WLog.w("onFetchValHistory : " +  WUtil.prettyPrinter(req));
            new ValHistoryTask(getBaseApplication(), this, req, BaseChain.getChain(mAccount.baseChain)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        } else if (mBaseChain.equals(BaseChain.IRIS_MAIN)) {
            ReqTxVal req = new ReqTxVal(0, 1, true, mAccount.address, mValidator.operator_address);
//            WLog.w("onFetchValHistory : " +  WUtil.prettyPrinter(req));
            new ValHistoryTask(getBaseApplication(), this, req, BaseChain.getChain(mAccount.baseChain)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        }

    }

    @Override
    public void onTaskResponse(TaskResult result) {
//        WLog.w("onTaskResponse " + result.taskType + "   " + mTaskCount);
        mTaskCount--;
        if (isFinishing()) return;
        if (result.taskType == BaseConstant.TASK_FETCH_SINGLE_VALIDATOR) {
            mValidator = (Validator)result.resultData;
            if (mValidator == null) {
                Toast.makeText(getBaseContext(), R.string.error_network_error, Toast.LENGTH_SHORT).show();
            }

        } else if (result.taskType == BaseConstant.TASK_FETCH_SINGLE_BONDING) {
            mBondingState = getBaseDao().onSelectBondingState(mAccount.id, mValidator.operator_address);
            if (mBondingState != null && mValidator != null && mBaseChain.equals(BaseChain.COSMOS_MAIN)) {
                mTaskCount = mTaskCount + 1;
                new SingleRewardTask(getBaseApplication(), this, mAccount, mValidator.operator_address).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            }

        } else if (result.taskType == BaseConstant.TASK_FETCH_SINGLE_UNBONDING) {
            if (result.isSuccess)
                mUnBondingStates = getBaseDao().onSelectUnbondingStates(mAccount.id, mValidator.operator_address);

        } else if (result.taskType == BaseConstant.TASK_FETCH_SINGLE_SELF_BONDING) {
            ResLcdBonding temp = (ResLcdBonding)result.resultData;
            if(temp != null)
                mSelfBondingRate = WDp.getSelfBondRate(mValidator.tokens, temp.shares);

        } else if (result.taskType == BaseConstant.TASK_FETCH_SINGLE_REWARD) {
            mReward = (Reward)result.resultData;

        } else if (result.taskType == BaseConstant.TASK_FETCH_VAL_HISTORY) {
            ArrayList<ResHistory.InnerHits> hits = (ArrayList<ResHistory.InnerHits>)result.resultData;
            if(hits != null && hits.size() > 0) {
                mTx = hits;
            }
        } else if (result.taskType == BaseConstant.TASK_FETCH_SINGLE_REDELEGATE) {
            mRedelegateResultThisVal = result;

        } else if (result.taskType == BaseConstant.TASK_IRIS_REWARD) {
            mIrisReward = (ResLcdIrisReward)result.resultData;

        } else if (result.taskType == BaseConstant.TASK_IRIS_REDELEGATE) {
            mIrisRedelegateState = (ArrayList<ResLcdIrisRedelegate>)result.resultData;
        }

        if (mTaskCount == 0) {
            mValidatorAdapter.notifyDataSetChanged();
            mSwipeRefreshLayout.setRefreshing(false);
        }
    }


    private class ValidatorAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private static final int TYPE_VALIDATOR             = 0;
        private static final int TYPE_MY_VALIDATOR          = 1;
        private static final int TYPE_ACTION                = 2;
        private static final int TYPE_HISTORY_HEADER        = 3;
        private static final int TYPE_HISTORY               = 4;
        private static final int TYPE_HISTORY_EMPTY         = 5;

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
            if(viewType == TYPE_VALIDATOR) {
                return new ValidatorHolder(getLayoutInflater().inflate(R.layout.item_validator_detail, viewGroup, false));
            } else if(viewType == TYPE_MY_VALIDATOR) {
                return new MyValidatorHolder(getLayoutInflater().inflate(R.layout.item_validator_my_detail, viewGroup, false));
            } else if(viewType == TYPE_ACTION) {
                return new MyActionHolder(getLayoutInflater().inflate(R.layout.item_validator_my_action, viewGroup, false));
            } else if(viewType == TYPE_HISTORY_HEADER) {
                return new HistoryHeaderHolder(getLayoutInflater().inflate(R.layout.item_validator_history_header, viewGroup, false));
            } else if(viewType == TYPE_HISTORY) {
                return new HistoryHolder(getLayoutInflater().inflate(R.layout.item_validator_history, viewGroup, false));
            } else if(viewType == TYPE_HISTORY_EMPTY) {
                return new HistoryEmptyHolder(getLayoutInflater().inflate(R.layout.item_validator_history_empty, viewGroup, false));
            }
            return null;
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
            if (getItemViewType(position) == TYPE_VALIDATOR) {
                final ValidatorHolder holder = (ValidatorHolder)viewHolder;
                holder.itemTvMoniker.setText(mValidator.description.moniker);
                holder.itemTvAddress.setText(mValidator.operator_address);
                holder.itemImgFree.setVisibility(View.GONE);

                if (!TextUtils.isEmpty(mValidator.description.website)) {
                    holder.itemTvWebsite.setText(mValidator.description.website);
                } else {
                    holder.itemTvWebsite.setVisibility(View.GONE);
                }

                if (!TextUtils.isEmpty(mValidator.description.details)) {
                    holder.itemTvDescription.setText(mValidator.description.details);
                } else {
                    holder.itemTvDescription.setVisibility(View.GONE);
                }

                if (mBaseChain.equals(BaseChain.COSMOS_MAIN)) {
                    //TODO rollback cosmos-hub2
//                    holder.itemTvCommissionRate.setText(WDp.getCommissionRate(mValidator.commission.commission_rates.rate));
                    holder.itemTvCommissionRate.setText(WDp.getCommissionRate(mValidator.commission.rate));
                    holder.itemTvTotalBondAmount.setText(WDp.getDpAmount(getBaseContext(), new BigDecimal(mValidator.tokens), 6, BaseChain.getChain(mAccount.baseChain)));
                    if(mValidator.status == Validator.BONDED) {
                        if(mBondedToken != null && mProvisions != null) {
                            //TODO rollback cosmos-hub2
//                            holder.itemTvYieldRate.setText(WDp.getYieldString(mBondedToken, mProvisions, new BigDecimal(mValidator.commission.commission_rates.rate)));
                            holder.itemTvYieldRate.setText(WDp.getYieldString(mBondedToken, mProvisions, new BigDecimal(mValidator.commission.rate)));
                        }
                    } else {
                        holder.itemTvYieldRate.setText(WDp.getYieldString(BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO));
                        holder.itemTvYieldRate.setTextColor(getResources().getColor(R.color.colorRed));
                    }
                    try {
                        Picasso.get().load(COSMOS_VAL_URL+mValidator.operator_address+".png")
                                .fit().placeholder(R.drawable.validator_none_img).error(R.drawable.validator_none_img)
                                .into(holder.itemAvatar);
                    } catch (Exception e){}

                } else if (mBaseChain.equals(BaseChain.IRIS_MAIN)) {
                    holder.itemTvCommissionRate.setText(WDp.getCommissionRate(mValidator.commission.rate));
                    holder.itemTvTotalBondAmount.setText(WDp.getDpAmount(getBaseContext(), new BigDecimal(mValidator.tokens).movePointRight(18), 18, BaseChain.getChain(mAccount.baseChain)));
                    if(mValidator.status == Validator.BONDED) {
                        if(mIrisPool != null) {
                            holder.itemTvYieldRate.setText(WDp.getIrisYieldString(mIrisPool, new BigDecimal(mValidator.commission.rate)));
                        }
                    } else {
                        holder.itemTvYieldRate.setText(WDp.getYieldString(BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO));
                        holder.itemTvYieldRate.setTextColor(getResources().getColor(R.color.colorRed));
                    }
                    try {
                        Picasso.get().load(IRIS_VAL_URL+mValidator.operator_address+".png")
                                .fit().placeholder(R.drawable.validator_none_img).error(R.drawable.validator_none_img)
                                .into(holder.itemAvatar);
                    } catch (Exception e){}

                }

                if(!TextUtils.isEmpty(mSelfBondingRate)){
                    holder.itemTvSelfBondRate.setText(mSelfBondingRate);
                } else{
                    holder.itemTvSelfBondRate.setText(WDp.getPercentDp(BigDecimal.ZERO));
                }

                holder.itemBtnDelegate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onCheckDelegate();

                    }
                });

                if(mValidator.jailed) {
                    holder.itemAvatar.setBorderColor(getResources().getColor(R.color.colorRed));
                    holder.itemImgRevoked.setVisibility(View.VISIBLE);
                } else {
                    holder.itemAvatar.setBorderColor(getResources().getColor(R.color.colorGray3));
                    holder.itemImgRevoked.setVisibility(View.GONE);
                }


            } else if (getItemViewType(position) == TYPE_MY_VALIDATOR) {
                final MyValidatorHolder holder = (MyValidatorHolder)viewHolder;
                holder.itemTvMoniker.setText(mValidator.description.moniker);
                holder.itemTvAddress.setText(mValidator.operator_address);
                holder.itemImgFree.setVisibility(View.GONE);

                if (!TextUtils.isEmpty(mValidator.description.website)) {
                    holder.itemTvWebsite.setText(mValidator.description.website);
                } else {
                    holder.itemTvWebsite.setVisibility(View.GONE);
                }

                if (!TextUtils.isEmpty(mValidator.description.details))  {
                    holder.itemTvDescription.setText(mValidator.description.details);
                } else {
                    holder.itemTvDescription.setVisibility(View.GONE);
                }

                if (mBaseChain.equals(BaseChain.COSMOS_MAIN)) {
                    //TODO rollback cosmos-hub2
//                    holder.itemTvCommissionRate.setText(WDp.getCommissionRate(mValidator.commission.commission_rates.rate));
                    holder.itemTvCommissionRate.setText(WDp.getCommissionRate(mValidator.commission.rate));
                    holder.itemTvTotalBondAmount.setText(WDp.getDpAmount(getBaseContext(), new BigDecimal(mValidator.tokens), 6, BaseChain.getChain(mAccount.baseChain)));
                    if(mValidator.status == Validator.BONDED) {
                        if(mBondedToken != null && mProvisions != null) {
                            //TODO rollback cosmos-hub2
//                            holder.itemTvYieldRate.setText(WDp.getYieldString(mBondedToken, mProvisions, new BigDecimal(mValidator.commission.commission_rates.rate)));
                            holder.itemTvYieldRate.setText(WDp.getYieldString(mBondedToken, mProvisions, new BigDecimal(mValidator.commission.rate)));
                        }
                    } else {
                        holder.itemTvYieldRate.setText(WDp.getYieldString(BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO));
                        holder.itemTvYieldRate.setTextColor(getResources().getColor(R.color.colorRed));
                    }
                    try {
                        Picasso.get().load(COSMOS_VAL_URL+mValidator.operator_address+".png")
                                .fit().placeholder(R.drawable.validator_none_img).error(R.drawable.validator_none_img)
                                .into(holder.itemAvatar);
                    } catch (Exception e){}

                } else if (mBaseChain.equals(BaseChain.IRIS_MAIN)) {
                    holder.itemTvCommissionRate.setText(WDp.getCommissionRate(mValidator.commission.rate));
                    holder.itemTvTotalBondAmount.setText(WDp.getDpAmount(getBaseContext(), new BigDecimal(mValidator.tokens).movePointRight(18), 18, BaseChain.getChain(mAccount.baseChain)));
                    if(mValidator.status == Validator.BONDED) {
                        if(mIrisPool != null) {
                            holder.itemTvYieldRate.setText(WDp.getIrisYieldString(mIrisPool, new BigDecimal(mValidator.commission.rate)));
                        }
                    } else {
                        holder.itemTvYieldRate.setText(WDp.getYieldString(BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO));
                        holder.itemTvYieldRate.setTextColor(getResources().getColor(R.color.colorRed));
                    }
                    try {
                        Picasso.get().load(IRIS_VAL_URL+mValidator.operator_address+".png")
                                .fit().placeholder(R.drawable.validator_none_img).error(R.drawable.validator_none_img)
                                .into(holder.itemAvatar);
                    } catch (Exception e){}

                }

                if (!TextUtils.isEmpty(mSelfBondingRate)){
                    holder.itemTvSelfBondRate.setText(mSelfBondingRate);
                } else{
                    holder.itemTvSelfBondRate.setText(WDp.getPercentDp(BigDecimal.ZERO));
                }

                if (mValidator.jailed) {
                    holder.itemAvatar.setBorderColor(getResources().getColor(R.color.colorRed));
                    holder.itemImgRevoked.setVisibility(View.VISIBLE);
                } else {
                    holder.itemAvatar.setBorderColor(getResources().getColor(R.color.colorGray3));
                    holder.itemImgRevoked.setVisibility(View.GONE);
                }

            } else if (getItemViewType(position) == TYPE_ACTION) {
                final MyActionHolder holder = (MyActionHolder)viewHolder;
                if (mBaseChain.equals(BaseChain.COSMOS_MAIN)) {
                    holder.itemRoot.setCardBackgroundColor(getResources().getColor(R.color.colorTransBg2));
                    if (mValidator.status == Validator.BONDED) {
                        if (mBondingState != null && mBondingState.getBondingAmount(mValidator) != null) {
                            holder.itemTvDelegatedAmount.setText(WDp.getDpAmount(getBaseContext(), mBondingState.getBondingAmount(mValidator), 6, BaseChain.getChain(mAccount.baseChain)));
                            //TODO rollback cosmos-hub2
//                            holder.itemDailyReturn.setText(WDp.getDailyReturn(getBaseContext(), mBondedToken, mProvisions, new BigDecimal(mValidator.commission.commission_rates.rate), mBondingState.getBondingAmount(mValidator)));
//                            holder.itemMonthlyReturn.setText(WDp.getMonthlyReturn(getBaseContext(), mBondedToken, mProvisions, new BigDecimal(mValidator.commission.commission_rates.rate), mBondingState.getBondingAmount(mValidator)));
                            holder.itemDailyReturn.setText(WDp.getDailyReturn(getBaseContext(), mBondedToken, mProvisions, new BigDecimal(mValidator.commission.rate), mBondingState.getBondingAmount(mValidator)));
                            holder.itemMonthlyReturn.setText(WDp.getMonthlyReturn(getBaseContext(), mBondedToken, mProvisions, new BigDecimal(mValidator.commission.rate), mBondingState.getBondingAmount(mValidator)));
                        } else {
                            holder.itemTvDelegatedAmount.setText(WDp.getDpAmount(getBaseContext(), BigDecimal.ZERO, 6, BaseChain.getChain(mAccount.baseChain)));
                            holder.itemDailyReturn.setText("-");
                            holder.itemMonthlyReturn.setText("-");
                        }
                    } else {
                        if (mBondingState != null && mBondingState.getBondingAmount(mValidator) != null) {
                            holder.itemTvDelegatedAmount.setText(WDp.getDpAmount(getBaseContext(), mBondingState.getBondingAmount(mValidator), 6, BaseChain.getChain(mAccount.baseChain)));
                        } else {
                            holder.itemTvDelegatedAmount.setText(WDp.getDpAmount(getBaseContext(), BigDecimal.ZERO, 6, BaseChain.getChain(mAccount.baseChain)));
                        }
                        holder.itemDailyReturn.setText(WDp.getDailyReturn(getBaseContext(), BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ONE, BigDecimal.ZERO));
                        holder.itemMonthlyReturn.setText(WDp.getMonthlyReturn(getBaseContext(), BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ONE, BigDecimal.ZERO));
                        holder.itemDailyReturn.setTextColor(getResources().getColor(R.color.colorRed));
                        holder.itemMonthlyReturn.setTextColor(getResources().getColor(R.color.colorRed));
                    }

                    if (mUnBondingStates != null && mUnBondingStates.size() > 0 ) {
                        BigDecimal sum = BigDecimal.ZERO;
                        for(UnBondingState unbond:mUnBondingStates) {
                            sum = sum.add(unbond.balance);
                        }
                        holder.itemTvUnbondingAmount.setText(WDp.getDpAmount(getBaseContext(), sum, 6, BaseChain.getChain(mAccount.baseChain)));
                    } else {
                        holder.itemTvUnbondingAmount.setText(WDp.getDpAmount(getBaseContext(), BigDecimal.ZERO, 6, BaseChain.getChain(mAccount.baseChain)));
                    }
                    if (mReward != null) {
                        holder.itemTvSimpleReward.setText(WDp.getDpAmount(getBaseContext(), mReward.getRewardAmount(COSMOS_ATOM), 6, BaseChain.getChain(mAccount.baseChain)));
                    } else {
                        holder.itemTvSimpleReward.setText(WDp.getDpAmount(getBaseContext(), BigDecimal.ZERO, 6, BaseChain.getChain(mAccount.baseChain)));
                    }

                } else if (mBaseChain.equals(BaseChain.IRIS_MAIN)) {
                    holder.itemRoot.setCardBackgroundColor(getResources().getColor(R.color.colorTransBg4));
                    if (mValidator.status == Validator.BONDED) {
                        if (mBondingState != null && mBondingState.getBondingAmount(mValidator) != null) {
                            holder.itemTvDelegatedAmount.setText(WDp.getDpAmount(getBaseContext(), mBondingState.getBondingAmount(mValidator), 18, BaseChain.getChain(mAccount.baseChain)));
                            holder.itemDailyReturn.setText(WDp.getIrisDailyReturn(getBaseContext(), mIrisPool, new BigDecimal(mValidator.commission.rate), mBondingState.getBondingAmount(mValidator)));
                            holder.itemMonthlyReturn.setText(WDp.getIrisMonthlyReturn(getBaseContext(), mIrisPool, new BigDecimal(mValidator.commission.rate), mBondingState.getBondingAmount(mValidator)));
                        } else {
                            holder.itemTvDelegatedAmount.setText(WDp.getDpAmount(getBaseContext(), BigDecimal.ZERO, 18, BaseChain.getChain(mAccount.baseChain)));
                            holder.itemDailyReturn.setText("-");
                            holder.itemMonthlyReturn.setText("-");
                        }
                    } else {
                        if (mBondingState != null && mBondingState.getBondingAmount(mValidator) != null) {
                            holder.itemTvDelegatedAmount.setText(WDp.getDpAmount(getBaseContext(), mBondingState.getBondingAmount(mValidator), 18, BaseChain.getChain(mAccount.baseChain)));
                        } else {
                            holder.itemTvDelegatedAmount.setText(WDp.getDpAmount(getBaseContext(), BigDecimal.ZERO, 18, BaseChain.getChain(mAccount.baseChain)));
                        }
                        holder.itemDailyReturn.setText(WDp.getDailyReturn(getBaseContext(), BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ONE, BigDecimal.ZERO));
                        holder.itemMonthlyReturn.setText(WDp.getMonthlyReturn(getBaseContext(), BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ONE, BigDecimal.ZERO));
                        holder.itemDailyReturn.setTextColor(getResources().getColor(R.color.colorRed));
                        holder.itemMonthlyReturn.setTextColor(getResources().getColor(R.color.colorRed));
                    }

                    if (mUnBondingStates != null && mUnBondingStates.size() > 0 ) {
                        BigDecimal sum = BigDecimal.ZERO;
                        for(UnBondingState unbond:mUnBondingStates) {
                            sum = sum.add(unbond.balance);
                        }
                        holder.itemTvUnbondingAmount.setText(WDp.getDpAmount(getBaseContext(), sum, 18, BaseChain.getChain(mAccount.baseChain)));
                    } else {
                        holder.itemTvUnbondingAmount.setText(WDp.getDpAmount(getBaseContext(), BigDecimal.ZERO, 18, BaseChain.getChain(mAccount.baseChain)));
                    }
                    if (mIrisReward != null && mIrisReward.getPerValReward(mValidator.operator_address) != BigDecimal.ZERO) {
                        holder.itemTvSimpleReward.setText(WDp.getDpAmount(getBaseContext(), mIrisReward.getPerValReward(mValidator.operator_address), 18, BaseChain.getChain(mAccount.baseChain)));
                    } else {
                        holder.itemTvSimpleReward.setText(WDp.getDpAmount(getBaseContext(), BigDecimal.ZERO, 18, BaseChain.getChain(mAccount.baseChain)));
                    }
                }

                holder.itemBtnDelegate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onCheckDelegate();
                    }
                });
                holder.itemBtnUndelegate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onStartUndelegate();
                    }
                });
                holder.itemBtnRedelegate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onCheckRedelegate();
                    }
                });
                holder.itemBtnReward.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onGetReward();
                    }
                });
                holder.itemBtnReinvest.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onCheckReInvest();
                    }
                });

            } else if (getItemViewType(position) == TYPE_HISTORY_HEADER) {
                final HistoryHeaderHolder holder = (HistoryHeaderHolder)viewHolder;

            } else if (getItemViewType(position) == TYPE_HISTORY) {
                final HistoryHolder holder = (HistoryHolder)viewHolder;
                final ResHistory.Source source;
                if(mBondingState == null && (mUnBondingStates == null || mUnBondingStates.size() < 1)) {
                    source = mTx.get(position - 2)._source;
                } else {
                    source = mTx.get(position - 3)._source;
                }

                holder.history_time.setText(WDp.getTimeformat(getBaseContext(), source.time));
                holder.history_time_gap.setText(WDp.getTimeGap(getBaseContext(), source.time));
                holder.historyType.setText(WDp.DpTxType(getBaseContext(), source.tx.value.msg, mAccount.address));

                if (mBaseChain.equals(BaseChain.COSMOS_MAIN)) {
                    if(!source.result.isSuccess()) {
                        holder.historySuccess.setVisibility(View.VISIBLE);
                    } else {
                        holder.historySuccess.setVisibility(View.GONE);
                    }
                } else if (mBaseChain.equals(BaseChain.IRIS_MAIN)) {
                    if(source.result.Code > 0) {
                        holder.historySuccess.setVisibility(View.VISIBLE);
                    } else {
                        holder.historySuccess.setVisibility(View.GONE);
                    }
                }

                holder.history_block.setText(source.height + " block");
                holder.historyRoot.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent webintent = new Intent(getBaseContext(), WebActivity.class);
                        webintent.putExtra("txid", source.hash);
                        webintent.putExtra("chain", mAccount.baseChain);
                        startActivity(webintent);
                    }
                });


            } else if (getItemViewType(position) == TYPE_HISTORY_EMPTY) {
                final HistoryEmptyHolder holder = (HistoryEmptyHolder)viewHolder;

            }
        }

        @Override
        public int getItemViewType(int position) {
            if(mBondingState == null && (mUnBondingStates == null || mUnBondingStates.size() < 1)) {
                if(position == 0) {
                    return TYPE_VALIDATOR;
                } else if (position == 1) {
                    return TYPE_HISTORY_HEADER;
                }

            } else {
                if(position == 0) {
                    return TYPE_MY_VALIDATOR;
                } else if (position == 1) {
                    return TYPE_ACTION;
                } else if (position == 2) {
                    return TYPE_HISTORY_HEADER;
                }
            }
            if(mTx.size() > 0) {
                return TYPE_HISTORY;
            }
            return TYPE_HISTORY_EMPTY;
        }

        @Override
        public int getItemCount() {
            if(mBondingState == null && (mUnBondingStates == null || mUnBondingStates.size() < 1)) {
                if(mTx.size() > 0) {
                    return mTx.size() + 2;
                } else {
                    return 3;
                }
            } else {
                if(mTx.size() > 0) {
                    return mTx.size() + 3;
                } else {
                    return 4;
                }
            }
        }

        public class ValidatorHolder extends RecyclerView.ViewHolder {
            CircleImageView itemAvatar;
            ImageView    itemImgRevoked;
            ImageView    itemImgFree;
            TextView    itemTvMoniker;
            TextView    itemTvAddress;
            TextView    itemTvWebsite;
            TextView    itemTvDescription;
            TextView    itemTvTotalBondAmount;
            TextView    itemTvSelfBondRate;
            TextView    itemTvYieldRate;
            TextView    itemTvCommissionRate;
            Button      itemBtnDelegate;

            public ValidatorHolder(View v) {
                super(v);
                itemAvatar              = itemView.findViewById(R.id.validator_avatar);
                itemImgRevoked          = itemView.findViewById(R.id.avatar_validator_revoke);
                itemImgFree             = itemView.findViewById(R.id.avatar_validator_free);
                itemTvMoniker           = itemView.findViewById(R.id.validator_moniker);
                itemTvAddress           = itemView.findViewById(R.id.validator_address);
                itemTvWebsite           = itemView.findViewById(R.id.validator_site);
                itemTvDescription       = itemView.findViewById(R.id.validator_description);
                itemTvTotalBondAmount   = itemView.findViewById(R.id.validator_total_bonded);
                itemTvSelfBondRate      = itemView.findViewById(R.id.validator_self_bond_rate);
                itemTvYieldRate         = itemView.findViewById(R.id.validator_yield);
                itemTvCommissionRate    = itemView.findViewById(R.id.validator_commission);
                itemBtnDelegate         = itemView.findViewById(R.id.validator_btn_delegate);
            }
        }

        public class MyValidatorHolder extends RecyclerView.ViewHolder {
            CardView            itemRoot;
            CircleImageView     itemAvatar;
            ImageView           itemImgRevoked;
            ImageView           itemImgFree;
            TextView            itemTvMoniker, itemTvAddress, itemTvWebsite, itemTvDescription, itemTvTotalBondAmount,
                                itemTvYieldRate, itemTvSelfBondRate, itemTvCommissionRate;

            public MyValidatorHolder(View v) {
                super(v);
                itemRoot                = itemView.findViewById(R.id.root);
                itemAvatar              = itemView.findViewById(R.id.validator_avatar);
                itemImgRevoked          = itemView.findViewById(R.id.avatar_validator_revoke);
                itemImgFree             = itemView.findViewById(R.id.avatar_validator_free);
                itemTvMoniker           = itemView.findViewById(R.id.validator_moniker);
                itemTvAddress           = itemView.findViewById(R.id.validator_address);
                itemTvWebsite           = itemView.findViewById(R.id.validator_site);
                itemTvDescription       = itemView.findViewById(R.id.validator_description);
                itemTvTotalBondAmount   = itemView.findViewById(R.id.validator_total_bonded);
                itemTvSelfBondRate      = itemView.findViewById(R.id.validator_self_bond_rate);
                itemTvYieldRate         = itemView.findViewById(R.id.validator_yield);
                itemTvCommissionRate    = itemView.findViewById(R.id.validator_commission);
            }
        }

        public class MyActionHolder extends RecyclerView.ViewHolder {
            CardView            itemRoot;
            TextView            itemTvDelegatedAmount, itemTvUnbondingAmount, itemTvAtomReward, itemTvPhotonReward, itemTvSimpleReward;
            Button              itemBtnDelegate, itemBtnUndelegate, itemBtnRedelegate, itemBtnReward, itemBtnReinvest ;
            TextView            itemAtomTitle, itemPhotonTitle;
            RelativeLayout      itemAtomLayer, itemPhotonLayer;
            TextView            itemDailyReturn, itemMonthlyReturn;

            public MyActionHolder(View v) {
                super(v);
                itemRoot                = itemView.findViewById(R.id.root);
                itemTvDelegatedAmount   = itemView.findViewById(R.id.validator_delegated);
                itemTvUnbondingAmount   = itemView.findViewById(R.id.validator_unbonding);
                itemTvAtomReward        = itemView.findViewById(R.id.validator_atom_reward);
                itemTvPhotonReward      = itemView.findViewById(R.id.validator_photon_reward);
                itemBtnDelegate         = itemView.findViewById(R.id.validator_btn_delegate);
                itemBtnUndelegate       = itemView.findViewById(R.id.validator_btn_undelegate);
                itemBtnRedelegate       = itemView.findViewById(R.id.validator_btn_redelegate);
                itemBtnReward           = itemView.findViewById(R.id.validator_btn_claim_reward);
                itemBtnReinvest         = itemView.findViewById(R.id.validator_btn_reinvest);
                itemAtomTitle           = itemView.findViewById(R.id.action_atom_title);
                itemPhotonTitle         = itemView.findViewById(R.id.action_photon_title);
                itemPhotonLayer         = itemView.findViewById(R.id.validator_photon_reward_layer);
                itemAtomLayer           = itemView.findViewById(R.id.validator_atom_reward_layer);
                itemTvSimpleReward      = itemView.findViewById(R.id.validator_simple_reward);
                itemDailyReturn         = itemView.findViewById(R.id.validator_daily_return);
                itemMonthlyReturn       = itemView.findViewById(R.id.validator_monthly_return);
            }
        }

        public class HistoryHeaderHolder extends RecyclerView.ViewHolder {

            public HistoryHeaderHolder(View v) {
                super(v);
            }
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

        public class HistoryEmptyHolder extends RecyclerView.ViewHolder {

            public HistoryEmptyHolder(View v) {
                super(v);
            }
        }

    }

}
