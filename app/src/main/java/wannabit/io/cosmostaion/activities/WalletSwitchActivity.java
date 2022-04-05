package wannabit.io.cosmostaion.activities;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.dao.ChainAccounts;
import wannabit.io.cosmostaion.widget.mainWallet.ManageChainSwitchHolder;

public class WalletSwitchActivity extends BaseActivity {
    private RecyclerView mAccountRecyclerView;
    private AccountListAdapter mAccountListAdapter;

    private BaseChain mSelectedChain;
    private ArrayList<BaseChain> mExpendedChains = new ArrayList<>();
    private ArrayList<ChainAccounts> mChainAccounts = new ArrayList<>();

    private ImageView mBtnClose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet_switch);
        mBtnClose = findViewById(R.id.btn_close);
        mAccountRecyclerView = findViewById(R.id.account_recycler);

        mBtnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setExpendChains();
                finish();
            }
        });
        mAccountRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mAccountRecyclerView.setHasFixedSize(true);
        mAccountListAdapter = new AccountListAdapter();
        mAccountRecyclerView.setAdapter(mAccountListAdapter);
        loadChains();
    }

    private void loadChains() {
        account = getBaseDao().onSelectAccount(getBaseDao().getLastUser());
        ArrayList<BaseChain> mDisplayChains = getBaseDao().dpSortedChains();
        mExpendedChains = getBaseDao().getExpendedChains();
        mSelectedChain = BaseChain.getChain(account.baseChain);
        getBaseDao().setLastChain(mSelectedChain.getChain());

        for (BaseChain chain : mDisplayChains) {
            if (mExpendedChains.contains(chain) || mSelectedChain.equals(chain)) {
                mChainAccounts.add(new ChainAccounts(true, chain, getBaseDao().onSelectAccountsByChain(chain)));
            } else {
                mChainAccounts.add(new ChainAccounts(false, chain, getBaseDao().onSelectAccountsByChain(chain)));
            }
        }
        mAccountRecyclerView.scrollToPosition(getBaseDao().dpSortedChains().indexOf(mSelectedChain));
        mAccountListAdapter.notifyDataSetChanged();
    }

    public void onChangeWallet(Long walletId) {
        getBaseDao().setLastUser(walletId);
        setExpendChains();
        finish();
    }

    public void setExpendChains() {
        mExpendedChains.clear();
        for (ChainAccounts chainAccounts : mChainAccounts) {
            if (chainAccounts.opened) {
                mExpendedChains.add(chainAccounts.baseChain);
            }
        }
        getBaseDao().setExpendedChains(mExpendedChains);
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
            holder.onBindChainSwitch(WalletSwitchActivity.this, mChainAccounts.get(position), account);
        }

        @Override
        public int getItemCount() {
            return mChainAccounts.size();
        }
    }
}

