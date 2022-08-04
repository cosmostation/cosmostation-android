package wannabit.io.cosmostaion.activities.txs.authz;

import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_ALL_REWARDS;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_AUTH;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_AUTHZ_GRANT_LIST;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_BALANCE;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_COMMISSION;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_DELEGATIONS;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_UNDELEGATIONS;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf2.Any;

import java.util.ArrayList;

import cosmos.authz.v1beta1.Authz;
import cosmos.bank.v1beta1.Tx;
import cosmos.base.v1beta1.CoinOuterClass;
import cosmos.distribution.v1beta1.Distribution;
import cosmos.staking.v1beta1.Staking;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.chains.ChainFactory;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.task.gRpcTask.AllRewardGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.AuthGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.AuthzGrantsGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.BalanceGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.CommissionGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.DelegationsGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.UnDelegationsGrpcTask;
import wannabit.io.cosmostaion.utils.WKey;
import wannabit.io.cosmostaion.widget.authz.AuthzExecuteInfoHolder;
import wannabit.io.cosmostaion.widget.authz.AuthzGranteeInfoHolder;
import wannabit.io.cosmostaion.widget.authz.AuthzGranterInfoHolder;

public class AuthzDetailActivity extends BaseActivity implements TaskListener {

    private Toolbar mToolbar;
    private TextView mTitle;

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private AuthzDetailAdapter mAuthzDetailAdapter;

    private String mGranter;
    private ArrayList<Authz.Grant> mGrants = new ArrayList<>();

    private Any mGranterAuth;
    private Coin mGranterBalance;
    private Coin mGranterAvailable;
    private Coin mGranterVesting;
    private ArrayList<Staking.DelegationResponse> mGranterDelegations = new ArrayList<>();
    private ArrayList<Staking.UnbondingDelegation> mGranterUndelegations = new ArrayList<>();
    private ArrayList<Distribution.DelegationDelegatorReward> mGranterRewards = new ArrayList<>();
    private Coin mGranterCommission;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authz_detail);
        mToolbar = findViewById(R.id.tool_bar);
        mTitle = findViewById(R.id.toolbar_title);
        mSwipeRefreshLayout = findViewById(R.id.layer_refresher);
        mRecyclerView = findViewById(R.id.recycler);
        mTitle.setText(getString(R.string.str_authz_detail_title));

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mAccount = getBaseDao().onSelectAccount(getBaseDao().getLastUser());
        mBaseChain = BaseChain.getChain(mAccount.baseChain);
        mChainConfig = ChainFactory.getChain(mBaseChain);
        mGranter = getIntent().getStringExtra("granter");

        mSwipeRefreshLayout.setColorSchemeColors(ContextCompat.getColor(this, R.color.colorPrimary));
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
            }
        });

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);
        mAuthzDetailAdapter = new AuthzDetailAdapter();
        mRecyclerView.setAdapter(mAuthzDetailAdapter);

        onFetchAuthzGranter();
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

    private int mTaskCount = 0;
    public void onFetchAuthzGranter() {
        onShowWaitDialog();
        mGrants.clear();
        mGranterBalance = new Coin(mChainConfig.mainDenom(), "0");
        mGranterAvailable = new Coin(mChainConfig.mainDenom(), "0");
        mGranterVesting = new Coin(mChainConfig.mainDenom(), "0");
        mGranterDelegations.clear();
        mGranterUndelegations.clear();
        mGranterRewards.clear();
        mTaskCount = 7;
        new AuthzGrantsGrpcTask(getBaseApplication(), this, mBaseChain, mAccount, mGranter).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        new AuthGrpcTask(getBaseApplication(), this, mBaseChain, mGranter).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        new BalanceGrpcTask(getBaseApplication(), this, mBaseChain, mGranter).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        new DelegationsGrpcTask(getBaseApplication(), this, mBaseChain, mGranter).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        new UnDelegationsGrpcTask(getBaseApplication(), this, mBaseChain, mGranter).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        new AllRewardGrpcTask(getBaseApplication(), this, mBaseChain, mGranter).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        new CommissionGrpcTask(getBaseApplication(), this, mBaseChain, WKey.convertDpAddressToDpOpAddress(mGranter, mChainConfig)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    @Override
    public void onTaskResponse(TaskResult result) {
        if (isFinishing()) return;
        mTaskCount--;
        if (result.taskType == TASK_GRPC_FETCH_AUTHZ_GRANT_LIST) {
            if (result.isSuccess && result.resultData != null) {
                mGrants = (ArrayList<Authz.Grant>) result.resultData;
            }

        } else if (result.taskType == TASK_GRPC_FETCH_AUTH) {
            if (result.isSuccess && result.resultData != null) {
                mGranterAuth = (Any) result.resultData;
            }

        } else if (result.taskType == TASK_GRPC_FETCH_BALANCE) {
            if (result.isSuccess && result.resultData != null) {
                ArrayList<CoinOuterClass.Coin> balance = (ArrayList<CoinOuterClass.Coin>) result.resultData;
                if (balance.size() > 0) {
                    for (CoinOuterClass.Coin coin : balance) {
                        if (coin.getDenom().equalsIgnoreCase(mChainConfig.mainDenom())) {
                            mGranterBalance = new Coin(coin.getDenom(), coin.getAmount());
                        }
                    }
                }
            }

        } else if (result.taskType == TASK_GRPC_FETCH_DELEGATIONS) {
            if (result.isSuccess && result.resultData != null) {
                mGranterDelegations = (ArrayList<Staking.DelegationResponse>) result.resultData;
            }

        } else if (result.taskType == TASK_GRPC_FETCH_UNDELEGATIONS) {
            if (result.isSuccess && result.resultData != null) {
                mGranterUndelegations = (ArrayList<Staking.UnbondingDelegation>) result.resultData;
            }

        } else if (result.taskType == TASK_GRPC_FETCH_ALL_REWARDS) {
            if (result.isSuccess && result.resultData != null) {
                mGranterRewards = (ArrayList<Distribution.DelegationDelegatorReward>) result.resultData;
            }

        } else if (result.taskType == TASK_GRPC_FETCH_COMMISSION) {
            if (result.isSuccess && result.resultData != null) {
                mGranterCommission = (Coin) result.resultData;
            }
        }

        if (mTaskCount == 0) {
            onHideWaitDialog();
            mSwipeRefreshLayout.setRefreshing(false);
            mAuthzDetailAdapter.notifyDataSetChanged();
        }
    }

    private class AuthzDetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private static final int TYPE_AUTHZ_GRANTEE = 0;
        private static final int TYPE_AUTHZ_GRANTER = 1;
        private static final int TYPE_AUTHZ_EXECUTE = 2;

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
            if (viewType == TYPE_AUTHZ_GRANTEE) {
                return new AuthzGranteeInfoHolder(getLayoutInflater().inflate(R.layout.item_authz_grantee_info, viewGroup, false));
            } else if (viewType == TYPE_AUTHZ_GRANTER) {
                return new AuthzGranterInfoHolder(getLayoutInflater().inflate(R.layout.item_authz_granter_info, viewGroup, false));
            } else if (viewType == TYPE_AUTHZ_EXECUTE) {
                return new AuthzExecuteInfoHolder(getLayoutInflater().inflate(R.layout.item_authz_execute_info, viewGroup, false));
            }
            return null;
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
            if (getItemViewType(position) == TYPE_AUTHZ_GRANTEE) {
                AuthzGranteeInfoHolder holder = (AuthzGranteeInfoHolder) viewHolder;
                holder.onBindGranteeInfo(AuthzDetailActivity.this, getBaseDao(), mChainConfig, mAccount);
            } else if (getItemViewType(position) == TYPE_AUTHZ_GRANTER) {
                AuthzGranterInfoHolder holder = (AuthzGranterInfoHolder) viewHolder;
                holder.onBindGranterInfo(AuthzDetailActivity.this, getBaseDao(), mChainConfig, mGranter);
            } else if (getItemViewType(position) == TYPE_AUTHZ_EXECUTE) {
                AuthzExecuteInfoHolder holder = (AuthzExecuteInfoHolder) viewHolder;
                holder.onBindGrantsInfoHolder(AuthzDetailActivity.this, getBaseDao(), mChainConfig, position - 2);
            }
        }

        @Override
        public int getItemCount() {
            return 9;
        }

        @Override
        public int getItemViewType(int position) {
            if (position == 0) {
                return TYPE_AUTHZ_GRANTEE;
            } else if (position == 1) {
                return TYPE_AUTHZ_GRANTER;
            } else {
                return TYPE_AUTHZ_EXECUTE;
            }
        }
    }

    public Authz.Grant getSendAuthz() {
        Authz.Grant result = null;
        for (Authz.Grant grant : mGrants) {
            if (grant.getAuthorization().getTypeUrl().contains(Authz.GenericAuthorization.getDescriptor().getFullName())) {
                try {
                    Authz.GenericAuthorization genericAuth = Authz.GenericAuthorization.parseFrom(grant.getAuthorization().getValue());
                    if (genericAuth.getMsg().contains(Tx.MsgSend.getDescriptor().getFullName())) {
                        result = grant;
                    }
                } catch (InvalidProtocolBufferException e) { e.printStackTrace(); }
            }

            if (grant.getAuthorization().getTypeUrl().contains(cosmos.bank.v1beta1.Authz.SendAuthorization.getDescriptor().getFullName())) {
                result = grant;
            }
        }
        return result;
    }

    public Authz.Grant getDelegateAuthz() {
        Authz.Grant result = null;
        for (Authz.Grant grant : mGrants) {
            if (grant.getAuthorization().getTypeUrl().contains(Authz.GenericAuthorization.getDescriptor().getFullName())) {
                try {
                    Authz.GenericAuthorization genericAuth = Authz.GenericAuthorization.parseFrom(grant.getAuthorization().getValue());
                    if (genericAuth.getMsg().contains(cosmos.staking.v1beta1.Tx.MsgDelegate.getDescriptor().getFullName())) {
                        result = grant;
                    }
                } catch (InvalidProtocolBufferException e) { e.printStackTrace(); }
            }

            if (grant.getAuthorization().getTypeUrl().contains(cosmos.staking.v1beta1.Authz.StakeAuthorization.getDescriptor().getFullName())) {
                try {
                    cosmos.staking.v1beta1.Authz.StakeAuthorization stakeAuth = cosmos.staking.v1beta1.Authz.StakeAuthorization.parseFrom(grant.getAuthorization().getValue());
                    if (stakeAuth.getAuthorizationType().equals(cosmos.staking.v1beta1.Authz.AuthorizationType.AUTHORIZATION_TYPE_DELEGATE)) {
                        result = grant;
                    }
                } catch (InvalidProtocolBufferException e) { e.printStackTrace(); }
            }
        }
        return result;
    }

    public Authz.Grant getUndelegateAuthz() {
        Authz.Grant result = null;
        for (Authz.Grant grant : mGrants) {
            if (grant.getAuthorization().getTypeUrl().contains(Authz.GenericAuthorization.getDescriptor().getFullName())) {
                try {
                    Authz.GenericAuthorization genericAuth = Authz.GenericAuthorization.parseFrom(grant.getAuthorization().getValue());
                    if (genericAuth.getMsg().contains(cosmos.staking.v1beta1.Tx.MsgUndelegate.getDescriptor().getFullName())) {
                        result = grant;
                    }
                } catch (InvalidProtocolBufferException e) { e.printStackTrace(); }
            }

            if (grant.getAuthorization().getTypeUrl().contains(cosmos.staking.v1beta1.Authz.StakeAuthorization.getDescriptor().getFullName())) {
                try {
                    cosmos.staking.v1beta1.Authz.StakeAuthorization stakeAuth = cosmos.staking.v1beta1.Authz.StakeAuthorization.parseFrom(grant.getAuthorization().getValue());
                    if (stakeAuth.getAuthorizationType().equals(cosmos.staking.v1beta1.Authz.AuthorizationType.AUTHORIZATION_TYPE_UNDELEGATE)) {
                        result = grant;
                    }
                } catch (InvalidProtocolBufferException e) { e.printStackTrace(); }
            }
        }
        return result;
    }

    public Authz.Grant getRedelegateAuthz() {
        Authz.Grant result = null;
        for (Authz.Grant grant : mGrants) {
            if (grant.getAuthorization().getTypeUrl().contains(Authz.GenericAuthorization.getDescriptor().getFullName())) {
                try {
                    Authz.GenericAuthorization genericAuth = Authz.GenericAuthorization.parseFrom(grant.getAuthorization().getValue());
                    if (genericAuth.getMsg().contains(cosmos.staking.v1beta1.Tx.MsgBeginRedelegate.getDescriptor().getFullName())) {
                        result = grant;
                    }
                } catch (InvalidProtocolBufferException e) { e.printStackTrace(); }
            }

            if (grant.getAuthorization().getTypeUrl().contains(cosmos.staking.v1beta1.Authz.StakeAuthorization.getDescriptor().getFullName())) {
                try {
                    cosmos.staking.v1beta1.Authz.StakeAuthorization stakeAuth = cosmos.staking.v1beta1.Authz.StakeAuthorization.parseFrom(grant.getAuthorization().getValue());
                    if (stakeAuth.getAuthorizationType().equals(cosmos.staking.v1beta1.Authz.AuthorizationType.AUTHORIZATION_TYPE_REDELEGATE)) {
                        result = grant;
                    }
                } catch (InvalidProtocolBufferException e) { e.printStackTrace(); }
            }
        }
        return result;
    }

    public Authz.Grant getRewardAuthz() {
        Authz.Grant result = null;
        for (Authz.Grant grant : mGrants) {
            if (grant.getAuthorization().getTypeUrl().contains(Authz.GenericAuthorization.getDescriptor().getFullName())) {
                try {
                    Authz.GenericAuthorization genericAuth = Authz.GenericAuthorization.parseFrom(grant.getAuthorization().getValue());
                    if (genericAuth.getMsg().contains(cosmos.distribution.v1beta1.Tx.MsgWithdrawDelegatorReward.getDescriptor().getFullName())) {
                        result = grant;
                    }
                } catch (InvalidProtocolBufferException e) { e.printStackTrace(); }
            }
        }
        return result;
    }

    public Authz.Grant getCommissionAuthz() {
        Authz.Grant result = null;
        for (Authz.Grant grant : mGrants) {
            if (grant.getAuthorization().getTypeUrl().contains(Authz.GenericAuthorization.getDescriptor().getFullName())) {
                try {
                    Authz.GenericAuthorization genericAuth = Authz.GenericAuthorization.parseFrom(grant.getAuthorization().getValue());
                    if (genericAuth.getMsg().contains(cosmos.distribution.v1beta1.Tx.MsgWithdrawValidatorCommission.getDescriptor().getFullName())) {
                        result = grant;
                    }
                } catch (InvalidProtocolBufferException e) { e.printStackTrace(); }
            }
        }
        return result;
    }

    public Authz.Grant getVoteAuthz() {
        Authz.Grant result = null;
        for (Authz.Grant grant : mGrants) {
            if (grant.getAuthorization().getTypeUrl().contains(Authz.GenericAuthorization.getDescriptor().getFullName())) {
                try {
                    Authz.GenericAuthorization genericAuth = Authz.GenericAuthorization.parseFrom(grant.getAuthorization().getValue());
                    if (genericAuth.getMsg().contains(cosmos.gov.v1beta1.Tx.MsgVote.getDescriptor().getFullName())) {
                        result = grant;
                    }
                } catch (InvalidProtocolBufferException e) { e.printStackTrace(); }
            }
        }
        return result;
    }
}
