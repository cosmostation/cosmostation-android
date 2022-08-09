package wannabit.io.cosmostaion.activities.txs.authz;

import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_ALL_REWARDS;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_AUTH;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_AUTHZ_GRANT_LIST;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_BALANCE;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_COMMISSION;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_DELEGATIONS;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_UNDELEGATIONS;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf2.Any;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;

import cosmos.authz.v1beta1.Authz;
import cosmos.bank.v1beta1.Tx;
import cosmos.base.v1beta1.CoinOuterClass;
import cosmos.distribution.v1beta1.Distribution;
import cosmos.staking.v1beta1.Staking;
import cosmos.vesting.v1beta1.Vesting;
import desmos.profiles.v1beta1.ModelsProfile;
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
import wannabit.io.cosmostaion.utils.WDp;
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
    private ArrayList<Coin> mGranterBalance = new ArrayList<>();
    private ArrayList<Coin> mGranterAvailable = new ArrayList<>();;
    private ArrayList<Coin> mGranterVesting = new ArrayList<>();;
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
                onFetchAuthzGranter();
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
        mGranterAuth = null;
        mGranterBalance.clear();
        mGranterAvailable.clear();
        mGranterVesting.clear();
        mGranterDelegations.clear();
        mGranterUndelegations.clear();
        mGranterRewards.clear();
        mGranterCommission = new Coin(mChainConfig.mainDenom(), "0");
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
                            mGranterBalance.add(new Coin(coin.getDenom(), coin.getAmount()));
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

            checkAccountType();
            mAuthzDetailAdapter.notifyDataSetChanged();
        }
    }

    private void checkAccountType() {
        if (mGranterAuth == null) return;
        Any rawAccount = mGranterAuth;
        if (mChainConfig.baseChain().equals(BaseChain.DESMOS_MAIN) && rawAccount.getTypeUrl().contains(ModelsProfile.Profile.getDescriptor().getFullName())) {
            try {
                ModelsProfile.Profile profileAccount = ModelsProfile.Profile.parseFrom(rawAccount.getValue());
                if (profileAccount != null) {
                    onCheckVesting(profileAccount.getAccount());
                } else {
                    onCheckVesting(rawAccount);
                }
            } catch (InvalidProtocolBufferException e) {
                e.printStackTrace();
            }
        } else {
            onCheckVesting(rawAccount);
        }
    }

    private void onCheckVesting(Any rawAccount) {
        ArrayList<Coin> balances = new ArrayList<>();
        for (Coin coin : mGranterBalance) {
            balances.add(coin);
        }

        if (rawAccount.getTypeUrl().contains(Vesting.PeriodicVestingAccount.getDescriptor().getFullName())) {
            Vesting.PeriodicVestingAccount vestingAccount = null;
            try {
                vestingAccount = Vesting.PeriodicVestingAccount.parseFrom(rawAccount.getValue());
            } catch (InvalidProtocolBufferException e) {
                return;
            }
            for (Coin coin : balances) {
                String denom = coin.denom;
                BigDecimal dpAvailable = BigDecimal.ZERO;
                BigDecimal dpVesting = BigDecimal.ZERO;
                BigDecimal originalVesting = BigDecimal.ZERO;
                BigDecimal remainVesting = BigDecimal.ZERO;
                BigDecimal delegatedVesting = BigDecimal.ZERO;

                dpAvailable = new BigDecimal(coin.amount);
                for (CoinOuterClass.Coin vesting : vestingAccount.getBaseVestingAccount().getOriginalVestingList()) {
                    if (vesting.getDenom().equals(denom)) {
                        originalVesting = originalVesting.add(new BigDecimal(vesting.getAmount()));
                    }
                }
                for (CoinOuterClass.Coin vesting : vestingAccount.getBaseVestingAccount().getDelegatedVestingList()) {
                    if (vesting.getDenom().equals(denom)) {
                        delegatedVesting = delegatedVesting.add(new BigDecimal(vesting.getAmount()));
                    }
                }
                remainVesting = WDp.onParsePeriodicRemainVestingsAmountByDenom(vestingAccount, denom);
                dpVesting = remainVesting.subtract(delegatedVesting);
                dpVesting = dpVesting.compareTo(BigDecimal.ZERO) <= 0 ? BigDecimal.ZERO : dpVesting;
                if (remainVesting.compareTo(delegatedVesting) > 0) {
                    dpAvailable = dpAvailable.subtract(remainVesting).add(delegatedVesting);
                }
                mGranterAvailable.add(new Coin(denom, dpAvailable.toPlainString()));
                mGranterVesting.add(new Coin(denom, dpVesting.toPlainString()));
            }

        } else if (rawAccount.getTypeUrl().contains(Vesting.ContinuousVestingAccount.getDescriptor().getFullName())) {
            Vesting.ContinuousVestingAccount vestingAccount = null;
            try {
                vestingAccount = Vesting.ContinuousVestingAccount.parseFrom(rawAccount.getValue());
            } catch (InvalidProtocolBufferException e) {
                return;
            }
            for (Coin coin : balances) {
                String denom = coin.denom;
                BigDecimal dpAvailable = BigDecimal.ZERO;
                BigDecimal dpVesting = BigDecimal.ZERO;
                BigDecimal originalVesting = BigDecimal.ZERO;
                BigDecimal remainVesting = BigDecimal.ZERO;
                BigDecimal delegatedVesting = BigDecimal.ZERO;

                dpAvailable = new BigDecimal(coin.amount);
                for (CoinOuterClass.Coin vesting : vestingAccount.getBaseVestingAccount().getOriginalVestingList()) {
                    if (vesting.getDenom().equals(denom)) {
                        originalVesting = originalVesting.add(new BigDecimal(vesting.getAmount()));
                    }
                }
                for (CoinOuterClass.Coin vesting : vestingAccount.getBaseVestingAccount().getDelegatedVestingList()) {
                    if (vesting.getDenom().equals(denom)) {
                        delegatedVesting = delegatedVesting.add(new BigDecimal(vesting.getAmount()));
                    }
                }
                long cTime = Calendar.getInstance().getTime().getTime();
                long vestingStart = vestingAccount.getStartTime() * 1000;
                long vestingEnd = vestingAccount.getBaseVestingAccount().getEndTime() * 1000;
                if (cTime < vestingStart) {
                    remainVesting = originalVesting;
                } else if (cTime > vestingEnd) {
                    remainVesting = BigDecimal.ZERO;
                } else if (cTime < vestingEnd) {
                    float progress = ((float) (cTime - vestingStart) / (float) (vestingEnd - vestingStart));
                    remainVesting = originalVesting.multiply(new BigDecimal(1 - progress)).setScale(0, RoundingMode.UP);
                }
                dpVesting = remainVesting.subtract(delegatedVesting);
                dpVesting = dpVesting.compareTo(BigDecimal.ZERO) <= 0 ? BigDecimal.ZERO : dpVesting;
                if (remainVesting.compareTo(delegatedVesting) > 0) {
                    dpAvailable = dpAvailable.subtract(remainVesting).add(delegatedVesting);
                }
                mGranterAvailable.add(new Coin(denom, dpAvailable.toPlainString()));
                mGranterVesting.add(new Coin(denom, dpVesting.toPlainString()));
            }

        } else if (rawAccount.getTypeUrl().contains(Vesting.DelayedVestingAccount.getDescriptor().getFullName())) {
            Vesting.DelayedVestingAccount vestingAccount = null;
            try {
                vestingAccount = Vesting.DelayedVestingAccount.parseFrom(rawAccount.getValue());
            } catch (InvalidProtocolBufferException e) {
                return;
            }
            for (Coin coin : balances) {
                String denom = coin.denom;
                BigDecimal dpAvailable = BigDecimal.ZERO;
                BigDecimal dpVesting = BigDecimal.ZERO;
                BigDecimal originalVesting = BigDecimal.ZERO;
                BigDecimal remainVesting = BigDecimal.ZERO;
                BigDecimal delegatedVesting = BigDecimal.ZERO;

                dpAvailable = new BigDecimal(coin.amount);
                for (CoinOuterClass.Coin vesting : vestingAccount.getBaseVestingAccount().getOriginalVestingList()) {
                    if (vesting.getDenom().equals(denom)) {
                        originalVesting = originalVesting.add(new BigDecimal(vesting.getAmount()));
                    }
                }
                for (CoinOuterClass.Coin vesting : vestingAccount.getBaseVestingAccount().getDelegatedVestingList()) {
                    if (vesting.getDenom().equals(denom)) {
                        delegatedVesting = delegatedVesting.add(new BigDecimal(vesting.getAmount()));
                    }
                }
                long cTime = Calendar.getInstance().getTime().getTime();
                long vestingEnd = vestingAccount.getBaseVestingAccount().getEndTime() * 1000;
                if (cTime < vestingEnd) {
                    remainVesting = originalVesting;
                }
                dpVesting = remainVesting.subtract(delegatedVesting);
                dpVesting = dpVesting.compareTo(BigDecimal.ZERO) <= 0 ? BigDecimal.ZERO : dpVesting;
                if (remainVesting.compareTo(delegatedVesting) > 0) {
                    dpAvailable = dpAvailable.subtract(remainVesting).add(delegatedVesting);
                }
                mGranterAvailable.add(new Coin(denom, dpAvailable.toPlainString()));
                mGranterVesting.add(new Coin(denom, dpVesting.toPlainString()));
            }
        } else {
            mGranterAvailable = mGranterBalance;
        }
    }

    private Coin getAvailableMain() {
        BigDecimal result = BigDecimal.ZERO;
        for (Coin coin : mGranterAvailable) {
            if (coin.denom.equalsIgnoreCase(mChainConfig.mainDenom())) {
                result = new BigDecimal(coin.amount);
            }
        }
        return new Coin(mChainConfig.mainDenom(), result.toPlainString());
    }

    private Coin getVestingMain() {
        BigDecimal result = BigDecimal.ZERO;
        for (Coin coin : mGranterVesting) {
            if (coin.denom.equalsIgnoreCase(mChainConfig.mainDenom())) {
                result = new BigDecimal(coin.amount);
            }
        }
        return new Coin(mChainConfig.mainDenom(), result.toPlainString());
    }

    private Coin getDelegatedSum() {
        BigDecimal result = BigDecimal.ZERO;
        for (Staking.DelegationResponse delegation : mGranterDelegations) {
            result = result.add(new BigDecimal(delegation.getBalance().getAmount()));
        }
        return new Coin(mChainConfig.mainDenom(), result.toPlainString());
    }

    private Coin getUnbondingSum() {
        BigDecimal result = BigDecimal.ZERO;
        for (Staking.UnbondingDelegation unbondingDelegation : mGranterUndelegations) {
            for (Staking.UnbondingDelegationEntry entry : unbondingDelegation.getEntriesList()) {
                result = result.add(new BigDecimal(entry.getBalance()));
            }
        }
        return new Coin(mChainConfig.mainDenom(), result.toPlainString());
    }

    private Coin getRewardSum() {
        BigDecimal result = BigDecimal.ZERO;
        for (cosmos.distribution.v1beta1.Distribution.DelegationDelegatorReward reward : mGranterRewards) {
            for (cosmos.base.v1beta1.CoinOuterClass.DecCoin coin : reward.getRewardList()) {
                if (coin.getDenom().equalsIgnoreCase(mChainConfig.mainDenom())) {
                    result = result.add(new BigDecimal(coin.getAmount()));
                }
            }
        }
        result = result.movePointLeft(18);
        return new Coin(mChainConfig.mainDenom(), result.toPlainString());
    }

    private boolean onCommonCheck() {
        if (!mAccount.hasPrivateKey) {
            onInsertKeyDialog();
            return false;
        }
        if (!WDp.isTxFeePayable(this, getBaseDao(), mChainConfig)) {
            Toast.makeText(this, getString(R.string.error_not_enough_fee), Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public void onStartAuthzDelegate() {
        if (!onCommonCheck()) return;

        if (getDelegateAuthz() != null) {
            Intent intent = new Intent(this, AuthzDelegateActivity.class);
            intent.putExtra("grant", getDelegateAuthz());
            intent.putExtra("granter", mGranter);
            intent.putExtra("grantAvailable", mGranterAvailable);
            intent.putExtra("grantVesting", mGranterVesting);
            intent.putExtra("granterDelegations", mGranterDelegations);
            intent.putExtra("granterUndelegations", mGranterUndelegations);
            intent.putExtra("granterRewards", mGranterRewards);
            startActivity(intent);
        } else {
            Toast.makeText(this, getString(R.string.error_no_authz_type), Toast.LENGTH_SHORT).show();
            return;
        }
    }

    public void onStartAuthzUndelegate() {
        if (!onCommonCheck()) return;

        if (getUndelegateAuthz() != null) {
            Intent intent = new Intent(this, AuthzUndelegateActivity.class);
            intent.putExtra("grant", getUndelegateAuthz());
            intent.putExtra("granter", mGranter);
            intent.putExtra("granterDelegations", mGranterDelegations);
            intent.putExtra("granterUndelegations", mGranterUndelegations);
            intent.putExtra("granterRewards", mGranterRewards);
            startActivity(intent);
        } else {
            Toast.makeText(this, getString(R.string.error_no_authz_type), Toast.LENGTH_SHORT).show();
            return;
        }
    }

    public void onStartAuthzRedelegate() {
        if (!onCommonCheck()) return;

        if (getRedelegateAuthz() != null) {
            Intent intent = new Intent(this, AuthzRedelegateActivity.class);
            intent.putExtra("grant", getRedelegateAuthz());
            intent.putExtra("granter", mGranter);
            intent.putExtra("granterDelegations", mGranterDelegations);
            intent.putExtra("granterUndelegations", mGranterUndelegations);
            intent.putExtra("granterRewards", mGranterRewards);
            startActivity(intent);
        } else {
            Toast.makeText(this, getString(R.string.error_no_authz_type), Toast.LENGTH_SHORT).show();
            return;
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
                holder.onBindGranteeInfo(getBaseDao(), mChainConfig, mAccount);
            } else if (getItemViewType(position) == TYPE_AUTHZ_GRANTER) {
                AuthzGranterInfoHolder holder = (AuthzGranterInfoHolder) viewHolder;
                holder.onBindGranterInfo(getBaseDao(), mChainConfig, mGranter, getAvailableMain(), getVestingMain(), getDelegatedSum(), getUnbondingSum(),
                                            getRewardSum(), mGranterCommission);
            } else if (getItemViewType(position) == TYPE_AUTHZ_EXECUTE) {
                AuthzExecuteInfoHolder holder = (AuthzExecuteInfoHolder) viewHolder;
                holder.onBindGrantsInfoHolder(getBaseDao(), mChainConfig, position - 2);
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
