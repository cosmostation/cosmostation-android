package wannabit.io.cosmostaion.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.utils.WDp;

public class AccountListActivity extends BaseActivity implements View.OnClickListener {

    private Toolbar mToolbar;
    private TextView mBtnEdit;
    private RecyclerView mChainRecyclerView;
    private RecyclerView mAccountRecyclerView;

    private ChainListAdapter mChainListAdapter;
    private AccountListAdapter mAccountListAdapter;
    private BaseChain mSelectedChain;
    private ArrayList<BaseChain> mDisplayChains = new ArrayList<>();
    private ArrayList<Account> mDisplayAccounts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_list);
        mToolbar = findViewById(R.id.tool_bar);
        mBtnEdit = findViewById(R.id.btn_edit);
        mChainRecyclerView = findViewById(R.id.chain_recycler);
        mAccountRecyclerView = findViewById(R.id.account_recycler);

        mSelectedChain = getBaseDao().getLastChain();

        mBtnEdit.setOnClickListener(this);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mChainRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mChainRecyclerView.setHasFixedSize(true);
        mChainListAdapter = new ChainListAdapter();
        mChainRecyclerView.setAdapter(mChainListAdapter);

        mAccountRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mAccountRecyclerView.setHasFixedSize(true);
        mAccountListAdapter = new AccountListAdapter();
        mAccountRecyclerView.setAdapter(mAccountListAdapter);

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
        onChainSelect(getBaseDao().getLastChain());
    }

    public void onChainSelect(BaseChain baseChain) {
        mDisplayChains = getBaseDao().dpSortedChains();
        mSelectedChain = baseChain;
        getBaseDao().setLastChain(mSelectedChain.getChain());
        mDisplayAccounts = getBaseDao().onSelectAccountsByChain(mSelectedChain);
        mChainRecyclerView.scrollToPosition(mDisplayChains.indexOf(getBaseDao().getLastChain()));

        mChainListAdapter.notifyDataSetChanged();
        mAccountListAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mBtnEdit)) {
            Intent intent = new Intent(AccountListActivity.this, WalletEditActivity.class);
            startActivity(intent);
        }
    }

    private class ChainListAdapter extends RecyclerView.Adapter<ChainListAdapter.ChainHolder> {

        @NonNull
        @Override
        public ChainListAdapter.ChainHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
            return new ChainHolder(getLayoutInflater().inflate(R.layout.item_accountlist_chain, viewGroup, false));
        }

        @Override
        public void onBindViewHolder(@NonNull ChainListAdapter.ChainHolder holder, final int position) {
            BaseChain chain = mDisplayChains.get(position);
            holder.chainCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (chain != mSelectedChain) {
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                onChainSelect(chain);
                                getBaseDao().setUserSortedChains(mDisplayChains);
                            }
                        }, 150);
                    }
                }
            });
            WDp.getChainImg(AccountListActivity.this, chain, holder.chainImg);
            WDp.getChainTitle2(AccountListActivity.this, chain, holder.chainName);

            if (chain.equals(mSelectedChain)) {
                holder.chainCard.setBackground(ContextCompat.getDrawable(AccountListActivity.this, R.drawable.box_chain_selected));
                holder.chainImg.setAlpha(1f);
                holder.chainName.setTextColor(getColor(R.color.colorWhite));
            } else {
                holder.chainCard.setBackground(ContextCompat.getDrawable(AccountListActivity.this, R.drawable.box_chain_unselected));
                holder.chainImg.setAlpha(0.1f);
                holder.chainName.setTextColor(getColor(R.color.colorGray4));
            }

        }

        @Override
        public int getItemCount() {
            return mDisplayChains.size();
        }


        public class ChainHolder extends RecyclerView.ViewHolder {
            FrameLayout chainCard;
            LinearLayout chainLayer;
            ImageView chainImg;
            TextView chainName;

            public ChainHolder(@NonNull View itemView) {
                super(itemView);
                chainCard = itemView.findViewById(R.id.chainCard);
                chainLayer = itemView.findViewById(R.id.chainLayer);
                chainImg = itemView.findViewById(R.id.chainImg);
                chainName = itemView.findViewById(R.id.chainName);
            }
        }
    }


    private class AccountListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
            return new AccountHolder(getLayoutInflater().inflate(R.layout.item_accountlist_account, viewGroup, false));
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
            final AccountHolder holder = (AccountHolder) viewHolder;
            final Account account = mDisplayAccounts.get(position);

            WDp.DpMainDenom(getBaseContext(), account.baseChain, holder.accountDenom);
            holder.accountAddress.setText(account.address);
            holder.accountAvailable.setText(account.getLastTotal(getBaseContext(), BaseChain.getChain(account.baseChain)));
            holder.accountKeyState.setColorFilter(ContextCompat.getColor(getBaseContext(), R.color.colorGray0), android.graphics.PorterDuff.Mode.SRC_IN);
            if (account.hasPrivateKey) {
                holder.accountKeyState.setColorFilter(WDp.getChainColor(getBaseContext(), BaseChain.getChain(account.baseChain)), android.graphics.PorterDuff.Mode.SRC_IN);
            }

            if (TextUtils.isEmpty(account.nickName)) {
                holder.accountName.setText(getString(R.string.str_my_wallet) + account.id);
            } else {
                holder.accountName.setText(account.nickName);
            }

            holder.accountCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(AccountListActivity.this, AccountDetailActivity.class);
                    intent.putExtra("id", "" + account.id);
                    startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return mDisplayAccounts.size();
        }

        public class AccountHolder extends RecyclerView.ViewHolder {
            FrameLayout accountCard;
            LinearLayout accountContent;
            ImageView accountArrowSort, accountKeyState;
            TextView accountName, accountAddress, accountAvailable, accountDenom;

            public AccountHolder(@NonNull View itemView) {
                super(itemView);
                accountCard = itemView.findViewById(R.id.accountCard);
                accountArrowSort = itemView.findViewById(R.id.accountArrowSort);
                accountContent = itemView.findViewById(R.id.accountContent);
                accountKeyState = itemView.findViewById(R.id.accountKeyState);
                accountName = itemView.findViewById(R.id.accountName);
                accountAddress = itemView.findViewById(R.id.accountAddress);
                accountAvailable = itemView.findViewById(R.id.accountAvailable);
                accountDenom = itemView.findViewById(R.id.accountDenom);
            }
        }
    }
}