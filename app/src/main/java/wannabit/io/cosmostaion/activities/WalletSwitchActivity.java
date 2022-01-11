package wannabit.io.cosmostaion.activities;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.widget.mainWallet.ManageChainSwitchHolder;

public class WalletSwitchActivity extends BaseActivity {
    private RecyclerView mAccountRecyclerView;
    private AccountListAdapter mAccountListAdapter;

    private BaseChain mSelectedChain;
    private ArrayList<BaseChain> mExpendedChains = new ArrayList<>();
    private ArrayList<ChainAccounts> mChainAccounts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet_switch);
        findViewById(R.id.btn_close).setOnClickListener(view -> finish());
        mAccountRecyclerView = findViewById(R.id.account_recycler);
        mAccountRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mAccountRecyclerView.setHasFixedSize(true);
        mAccountListAdapter = new AccountListAdapter();
        mAccountRecyclerView.setAdapter(mAccountListAdapter);
        loadChains();
    }

    private void loadChains() {
        mAccount = getBaseDao().onSelectAccount(getBaseDao().getLastUser());
        ArrayList<BaseChain> mDisplayChains = getBaseDao().dpSortedChains();
        mExpendedChains = getBaseDao().getExpendedChains();
        mSelectedChain = BaseChain.getChain(mAccount.baseChain);
        getBaseDao().setLastChain(mSelectedChain.getChain());

        for (BaseChain chain : mDisplayChains) {
            if (mExpendedChains.contains(chain) || mSelectedChain.equals(chain)) {
                mChainAccounts.add(new ChainAccounts(true, chain, getBaseDao().onSelectAccountsByChain(chain)));
            } else {
                mChainAccounts.add(new ChainAccounts(false, chain, getBaseDao().onSelectAccountsByChain(chain)));
            }
        }
        mAccountListAdapter.notifyDataSetChanged();
    }

    public void onChangeWallet(Long walletId) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getBaseDao().setLastUser(walletId);
                finish();
            }
        }, 200);
    }

    private class AccountListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
            return new ManageChainSwitchHolder(getLayoutInflater().inflate(R.layout.item_account_list, viewGroup, false));
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
            final ManageChainSwitchHolder holder = (ManageChainSwitchHolder) viewHolder;
            holder.onBindChainSwitch(WalletSwitchActivity.this, mChainAccounts.get(position), mAccount);
        }

        @Override
        public int getItemCount() {
            return mChainAccounts.size();
        }
    }

    public class ChainAccounts {
        public boolean opened = false;
        public BaseChain baseChain;
        public ArrayList<Account> accounts;

        public ChainAccounts(boolean opened, BaseChain baseChain, ArrayList<Account> accounts) {
            this.opened = opened;
            this.baseChain = baseChain;
            this.accounts = accounts;
        }
    }
}
