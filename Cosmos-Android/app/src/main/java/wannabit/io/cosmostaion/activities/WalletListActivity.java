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
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.dao.Balance;
import wannabit.io.cosmostaion.dao.BondingState;
import wannabit.io.cosmostaion.dao.Reward;
import wannabit.io.cosmostaion.dao.TotalReward;
import wannabit.io.cosmostaion.dao.UnBondingState;
import wannabit.io.cosmostaion.model.type.Validator;
import wannabit.io.cosmostaion.task.AccountInfoTask;
import wannabit.io.cosmostaion.task.BondingStateTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.task.TotalRewardTask;
import wannabit.io.cosmostaion.task.UnBondingStateTask;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WLog;

public class WalletListActivity extends BaseActivity implements TaskListener {

    private Toolbar             mToolbar;
    private TextView            mToolbarTitle;
    private TextView            mTvAtomValue, mTvAtomTotal;
    private TextView            mTvPhotonValue, mTvPhotonTotal;
    private SwipeRefreshLayout  mSwipeRefreshLayout;
    private RecyclerView        mRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;

    private ArrayList<Account>                          mAccounts = new ArrayList<>();
    private HashMap<Long, ArrayList<Balance>>           mTotalBalance = new HashMap<>();
    private HashMap<Long, ArrayList<BondingState>>      mTotalBondings = new HashMap<>();
    private HashMap<Long, ArrayList<UnBondingState>>    mTotalUnbondings = new HashMap<>();
    private HashMap<Long, TotalReward>                  mTotalRewards = new HashMap<>();
    private WalletAdapter                               mWalletAdapter;

    private int                                         mTaskCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet_list);

        mToolbar                    = findViewById(R.id.tool_bar);
        mToolbarTitle               = findViewById(R.id.toolbar_title);
        mTvAtomValue                = findViewById(R.id.dash_atom_value);
        mTvAtomTotal                = findViewById(R.id.dash_atom_amount);
        mTvPhotonValue              = findViewById(R.id.dash_photon_value);
        mTvPhotonTotal              = findViewById(R.id.dash_photon_amount);
        mSwipeRefreshLayout         = findViewById(R.id.layer_refresher);
        mRecyclerView               = findViewById(R.id.recycler);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        mSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                onFetchAccounts();
            }
        });

        mLinearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        mWalletAdapter = new WalletAdapter();
        mRecyclerView.setAdapter(mWalletAdapter);

    }

    @Override
    protected void onResume() {
        super.onResume();
        mAccounts = getBaseDao().onSelectAccounts();
        onFetchAccounts();
        onFetchDB();
        mWalletAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.list_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_setting:
                WLog.w("menu_setting");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void onFetchDB() {
        for(Account account:mAccounts) {
            mTotalBalance.put(account.id, getBaseDao().onSelectBalance(account.id));
            mTotalBondings.put(account.id, getBaseDao().onSelectBondingStates(account.id));
            mTotalUnbondings.put(account.id, getBaseDao().onSelectUnbondingStates(account.id));
        }

        mTvAtomValue.setText("N/A");
        mTvAtomTotal.setText(WDp.getDpTotalAtomAmount(getBaseContext(), mTotalBalance, mTotalBondings, mTotalUnbondings, mTotalRewards));
        mTvPhotonValue.setText("N/A");
        mTvPhotonTotal.setText(WDp.getDpTotalPhotonAmount(getBaseContext(), mTotalBalance, mTotalRewards));
        mSwipeRefreshLayout.setRefreshing(false);
    }

    private void onFetchAccounts() {
        if(mTaskCount > 0) {
            mSwipeRefreshLayout.setRefreshing(false);
            return;
        }
        mTaskCount = 4;
        new AccountInfoTask(getBaseApplication(), this, mAccounts).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        new BondingStateTask(getBaseApplication(), this, mAccounts).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        new UnBondingStateTask(getBaseApplication(), this, mAccounts).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        new TotalRewardTask(getBaseApplication(), this, mAccounts).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    @Override
    public void onTaskResponse(TaskResult result) {
        mTaskCount--;
        if (result.taskType == BaseConstant.TASK_FETCH_ACCOUNT) {

        } else if(result.taskType == BaseConstant.TASK_FETCH_BONDING_STATE) {

        } else if (result.taskType == BaseConstant.TASK_FETCH_UNBONDING_STATE) {

        } else if (result.taskType == BaseConstant.TASK_FETCH_TOTAL_REWARDS) {
            mTotalRewards = (HashMap<Long, TotalReward>)result.resultData;
        }
        if(mTaskCount == 0) {
            onFetchDB();
            mWalletAdapter.notifyDataSetChanged();
        }

    }

    private class WalletAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        private static final int TYPE_WALLET                 = 0;
        private static final int TYPE_ADD_WALLET             = 1;

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
            if(viewType == TYPE_WALLET) {
                View v = getLayoutInflater().inflate(R.layout.item_wallet, viewGroup, false);
                return  new WalletHolder(v);

            } else if(viewType == TYPE_ADD_WALLET) {
                View v = getLayoutInflater().inflate(R.layout.item_wallet_add, viewGroup, false);
                return  new WalletAddHolder(v);

            }
            return null;
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
            if (getItemViewType(position) == TYPE_WALLET) {
                final WalletHolder holder                   = (WalletHolder)viewHolder;
                final Account account                       = mAccounts.get(position);
                final ArrayList<Balance> balances           = mTotalBalance.get(account.id);
                final ArrayList<BondingState> bondings      = mTotalBondings.get(account.id);
                final ArrayList<UnBondingState> unbondings  = mTotalUnbondings.get(account.id);
                final TotalReward totalReward               = mTotalRewards.get(account.id);


                if(TextUtils.isEmpty(account.nickName)) {
                    holder.itemTvNickname.setText("Wallet " + account.id);
                } else {
                    holder.itemTvNickname.setText(account.nickName);
                }
                holder.itemTvAddress.setText(account.address);
                holder.itemTvAtomAmount.setText(WDp.getDpAllAtom2(getBaseContext(), balances, bondings, unbondings, totalReward));
                holder.itemTvPhotonAmount.setText(WDp.getDpAllPhoton2(getBaseContext(), balances, totalReward));
                holder.itemBtnManage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        WLog.w("Go MANAGE");

                    }
                });
                holder.itemBtnDetail.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        WLog.w("Go detail");
                        getBaseDao().setLastUser(account.id);
                        onStartMainActivity();
                    }
                });

            } else if (getItemViewType(position) == TYPE_ADD_WALLET) {
                final WalletAddHolder holder    = (WalletAddHolder)viewHolder;
                holder.itemBtnWalletAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        WLog.w("Go Add");
                        startActivity(new Intent(WalletListActivity.this, CreateActivity.class));
                    }
                });
            }

        }

        @Override
        public int getItemCount() {
            if(mAccounts.size() > 5) {
                return  mAccounts.size();
            } else {
                return  mAccounts.size() + 1;
            }
        }

        @Override
        public int getItemViewType(int position) {
            if(mAccounts.size() > 5) {
                return TYPE_WALLET;
            } else {
                if(position < mAccounts.size()) return TYPE_WALLET;
                else return TYPE_ADD_WALLET;
            }
        }


        public class WalletAddHolder extends RecyclerView.ViewHolder {
            Button itemBtnWalletAdd;

            public WalletAddHolder(View v) {
                super(v);
                itemBtnWalletAdd = itemView.findViewById(R.id.btn_wallet_add);
            }
        }

        public class WalletHolder extends RecyclerView.ViewHolder {
            CardView    itemRoot;
            TextView    itemTvNickname, itemTvAddress, itemTvAtomAmount, itemTvPhotonAmount;
            Button      itemBtnManage, itemBtnDetail;


            public WalletHolder(View v) {
                super(v);
                itemRoot            = itemView.findViewById(R.id.card_wallet);
                itemTvNickname      = itemView.findViewById(R.id.wallet_nickname);
                itemTvAddress       = itemView.findViewById(R.id.wallet_address);
                itemTvAtomAmount    = itemView.findViewById(R.id.wallet_atom_amount);
                itemTvPhotonAmount  = itemView.findViewById(R.id.wallet_photon_amount);
                itemBtnManage       = itemView.findViewById(R.id.btn_wallet_manage);
                itemBtnDetail       = itemView.findViewById(R.id.btn_wallet_detail);
            }
        }
    }
}
