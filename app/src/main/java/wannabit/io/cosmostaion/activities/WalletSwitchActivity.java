package wannabit.io.cosmostaion.activities;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.dao.ChainAccounts;
import wannabit.io.cosmostaion.utils.WDp;

public class WalletSwitchActivity extends BaseActivity {
    private RecyclerView mChainRecyclerView;
    private ChainListAdapter mChainListAdapter;

    private BaseChain mSelectedChain;
    private ArrayList<BaseChain> mExpendedChains = new ArrayList<>();
    private ArrayList<ChainAccounts> mChainAccounts = new ArrayList<>();

    private ImageView mBtnClose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet_switch);
        mBtnClose = findViewById(R.id.btn_close);
        mChainRecyclerView = findViewById(R.id.account_recycler);

        mBtnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setExpendChains();
                finish();
            }
        });
        mChainRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mChainRecyclerView.setHasFixedSize(true);
        mChainListAdapter = new ChainListAdapter();
        mChainRecyclerView.setAdapter(mChainListAdapter);
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
        mChainRecyclerView.scrollToPosition(getBaseDao().dpSortedChains().indexOf(mSelectedChain));
        mChainListAdapter.notifyDataSetChanged();
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

    private class ChainListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
            return new ChainHolder(getLayoutInflater().inflate(R.layout.item_account_list, viewGroup, false));
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
            final ChainHolder holder = (ChainHolder) viewHolder;
            final ChainAccounts data = mChainAccounts.get(position);
            holder.accountCard.setCardBackgroundColor(WDp.getChainBgColor(WalletSwitchActivity.this, data.baseChain));
            WDp.getChainImg(WalletSwitchActivity.this, data.baseChain, holder.accountChainImg);
            WDp.getChainTitle2(WalletSwitchActivity.this, data.baseChain, holder.accountChainName);
            holder.accountWalletCnt.setText("" + data.accounts.size());

            if (data.opened && data.accounts.size() > 0) {
                holder.hiddenView.setVisibility(View.VISIBLE);
            }

            holder.accountSelect.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    data.opened = !data.opened;
                    if (holder.hiddenView.getVisibility() == View.VISIBLE) {
                        holder.hiddenView.setVisibility(View.GONE);
                    } else {
                        TransitionManager.beginDelayedTransition(holder.accountCard, new AutoTransition());
                        holder.hiddenView.setVisibility(View.VISIBLE);
                    }
                    if (data.opened && data.accounts.size() > 0) {
                        holder.hiddenView.setVisibility(View.VISIBLE);
                    }
                }
            });

            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            holder.hiddenView.removeAllViews();
            for (Account account : data.accounts) {
                View accountLayout = inflater.inflate(R.layout.item_chain_account, null);
                holder.hiddenView.addView(accountLayout);

                FrameLayout accountCard = accountLayout.findViewById(R.id.accountCard);
                ImageView accountState = accountLayout.findViewById(R.id.accountKeyState);
                TextView accountName = accountLayout.findViewById(R.id.accountName);
                TextView accountAddress = accountLayout.findViewById(R.id.accountAddress);
                TextView accountAvailable = accountLayout.findViewById(R.id.accountAvailable);
                TextView accountDenom = accountLayout.findViewById(R.id.accountDenom);

                if (account.id.equals(mAccount.id)) {
                    accountCard.setBackground(getResources().getDrawable(R.drawable.box_round_seleted_white));
                } else {
                    accountCard.setBackground(getResources().getDrawable(R.drawable.box_round_darkgray));
                }
                WDp.DpMainDenom(WalletSwitchActivity.this, account.baseChain, accountDenom);
                accountAddress.setText(account.address);
                accountAvailable.setText(account.getLastTotal(WalletSwitchActivity.this, BaseChain.getChain(account.baseChain)));
                accountState.setColorFilter(ContextCompat.getColor(WalletSwitchActivity.this, R.color.colorGray0), android.graphics.PorterDuff.Mode.SRC_IN);
                if (account.hasPrivateKey) {
                    accountState.setColorFilter(WDp.getChainColor(WalletSwitchActivity.this, BaseChain.getChain(account.baseChain)), android.graphics.PorterDuff.Mode.SRC_IN);
                }

                if (TextUtils.isEmpty(account.nickName)){
                    accountName.setText(getString(R.string.str_my_wallet) + account.id);
                } else {
                    accountName.setText(account.nickName);
                }

                accountCard.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (account.id.equals(mAccount.id)) {
                           finish();
                           return;
                        }
                        onChangeWallet(account.id);
                    }
                });
            }
        }

        @Override
        public int getItemViewType(int position) {
            return position;
        }

        @Override
        public int getItemCount() {
            return mChainAccounts.size();
        }

        public class ChainHolder extends RecyclerView.ViewHolder {
            private CardView accountCard;
            private RelativeLayout accountSelect;
            private ImageView accountChainImg;
            private TextView accountChainName, accountWalletCnt;
            private LinearLayout hiddenView;

            public ChainHolder(@NonNull View itemView) {
                super(itemView);
                accountCard         = itemView.findViewById(R.id.card_chain);
                accountSelect       = itemView.findViewById(R.id.chain_layer);
                accountChainImg     = itemView.findViewById(R.id.chain_img);
                accountChainName    = itemView.findViewById(R.id.chain_name);
                accountWalletCnt    = itemView.findViewById(R.id.wallet_cnt);
                hiddenView          = itemView.findViewById(R.id.hidden_view);
            }
        }
    }
}

