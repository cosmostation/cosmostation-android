package wannabit.io.cosmostaion.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.math.BigDecimal;
import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WLog;

public class AccountListActivity extends BaseActivity implements View.OnClickListener {

    private Toolbar                     mToolbar;
    private RecyclerView                mRecyclerView;
    private Button                      mCreate, mImport;
    private LinearLayout                bottomLayer, bottomDetail, btnImportMnemonic, btnWatchAddress, mNmemonicLayer, mWatchLayer;


    private ArrayList<Account>          mFullAccounts = new ArrayList<>();
    private ArrayList<Account>          mAddressAccounts = new ArrayList<>();
    private AccountListAdapter          mAccountListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_list);
        mToolbar            = findViewById(R.id.tool_bar);
        mRecyclerView       = findViewById(R.id.recycler);
        bottomLayer         = findViewById(R.id.bottom_layer);
        bottomDetail        = findViewById(R.id.import_detail);
        btnImportMnemonic   = findViewById(R.id.btn_import_mnemonic);
        btnWatchAddress     = findViewById(R.id.btn_watch_address);
        mImport             = findViewById(R.id.btn_import);
        mCreate             = findViewById(R.id.btn_create);
        mNmemonicLayer      = findViewById(R.id.import_mnemonic_layer);
        mWatchLayer         = findViewById(R.id.import_watch_layer);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mImport.setOnClickListener(this);
        mCreate.setOnClickListener(this);
        btnImportMnemonic.setOnClickListener(this);
        btnWatchAddress.setOnClickListener(this);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);
        mAccountListAdapter = new AccountListAdapter();
        mRecyclerView.setAdapter(mAccountListAdapter);

    }


    @Override
    protected void onResume() {
        super.onResume();
        mFullAccounts.clear();
        mAddressAccounts.clear();
        ArrayList<Account> accounts = getBaseDao().onSelectAccounts();
        for(Account account:accounts) {
            if(account.hasPrivateKey) mFullAccounts.add(account);
            else mAddressAccounts.add(account);
        }
        WLog.w("accounts : " + accounts.size());
        WLog.w("mFullAccounts : " + mFullAccounts.size());
        WLog.w("mAddressAccounts : " + mAddressAccounts.size());

        mAccountListAdapter.notifyDataSetChanged();

        WLog.w("mAccountListAdapter : " + mAccountListAdapter.getItemCount());

        if(accounts.size() > 4) {
            bottomLayer.setVisibility(View.GONE);
        } else {
            bottomLayer.setVisibility(View.VISIBLE);
        }
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
    public void onClick(View v) {
        if (v.equals(mImport)) {
            mImport.setVisibility(View.GONE);
            bottomDetail.setVisibility(View.VISIBLE);
            Animation fadein = AnimationUtils.loadAnimation(this, R.anim.fade_in4);
            mNmemonicLayer.startAnimation(fadein);
            mWatchLayer.startAnimation(fadein);

        } else if (v.equals(mCreate)) {
            startActivity(new Intent(AccountListActivity.this, CreateActivity.class));

        } else if (v.equals(btnImportMnemonic)) {
            startActivity(new Intent(AccountListActivity.this, RestoreActivity.class));

        } else if (v.equals(btnWatchAddress)) {
            startActivity(new Intent(AccountListActivity.this, WatchingAccountAddActivity.class));
        }
    }

    private class AccountListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        private static final int TYPE_FULL_ACCOUNT              = 0;
        private static final int TYPE_ADDRESS_ACCOUNT           = 1;
        private static final int TYPE_FULL_HEADER               = 2;
        private static final int TYPE_ADDRESS_HEADER            = 3;


        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
            if(viewType == TYPE_FULL_ACCOUNT || viewType == TYPE_ADDRESS_ACCOUNT) {
                return new AccountListHolder(getLayoutInflater().inflate(R.layout.item_setting_account, viewGroup, false));

            } else if(viewType == TYPE_FULL_HEADER) {
                return  new AccountFullHeaderHolder(getLayoutInflater().inflate(R.layout.item_setting_accounts_full_header, viewGroup, false));

            } else if(viewType == TYPE_ADDRESS_HEADER) {
                return  new AccountAddressHeaderHolder(getLayoutInflater().inflate(R.layout.item_setting_accounts_address_header, viewGroup, false));

            } else {
                return null;
            }
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {

            if (getItemViewType(position) == TYPE_FULL_ACCOUNT) {
                final AccountListHolder holder = (AccountListHolder)viewHolder;
                final Account account= mFullAccounts.get(position - 1);

                holder.wallet_address.setText(account.address);

                if(TextUtils.isEmpty(account.nickName)) holder.wallet_name.setText("Wallet " + account.id);
                else holder.wallet_name.setText(account.nickName);


                if(getBaseDao().onSelectBalance(account.id).size() > 0) holder.wallet_atom_amount.setText(WDp.getDpAmount(getBaseContext(), getBaseDao().onSelectBalance(account.id).get(0).balance, 6, BaseChain.getChain(account.baseChain)));
                else holder.wallet_atom_amount.setText(WDp.getDpAmount(getBaseContext(), BigDecimal.ZERO, 6, BaseChain.getChain(account.baseChain)));

                holder.wallet_atom_title.setText(WDp.DpAtom(getBaseContext(), account.baseChain));

                holder.wallet_card.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(AccountListActivity.this, AccountDetailActivity.class);
                        intent.putExtra("id", ""+account.id);
                        startActivity(intent);

                    }
                });

            } else if (getItemViewType(position) == TYPE_ADDRESS_ACCOUNT) {
                final AccountListHolder holder = (AccountListHolder)viewHolder;
                final Account account;

                WLog.w("position : " + position);
                if(mFullAccounts.size() == 0) {
                    account = mAddressAccounts.get(position - 1);
                } else {
                    account = mAddressAccounts.get(position  - 2 - mFullAccounts.size());
                }
                holder.wallet_address.setText(account.address);

                if(TextUtils.isEmpty(account.nickName)) holder.wallet_name.setText("Wallet " + account.id);
                else holder.wallet_name.setText(account.nickName);

                if(getBaseDao().onSelectBalance(account.id).size() > 0) holder.wallet_atom_amount.setText(WDp.getDpAmount(getBaseContext(), getBaseDao().onSelectBalance(account.id).get(0).balance, 6, BaseChain.getChain(account.baseChain)));
                else holder.wallet_atom_amount.setText(WDp.getDpAmount(getBaseContext(), BigDecimal.ZERO, 6, BaseChain.getChain(account.baseChain)));

                holder.wallet_atom_title.setText(WDp.DpAtom(getBaseContext(), account.baseChain));

                holder.wallet_card.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(AccountListActivity.this, AccountDetailActivity.class);
                        intent.putExtra("id", ""+account.id);
                        startActivity(intent);

                    }
                });
            }


        }

        @Override
        public int getItemCount() {
            if(mFullAccounts.size() > 0 && mAddressAccounts.size() > 0) {
                return mFullAccounts.size() + mAddressAccounts.size() + 2;

            } else if (mFullAccounts.size() == 0) {
                return mAddressAccounts.size() + 1;

            } else if (mAddressAccounts.size() == 0) {
                return mFullAccounts.size() + 1;
            }
            return 0;
        }

        @Override
        public int getItemViewType(int position) {
            if(mFullAccounts.size() > 0 && mAddressAccounts.size() > 0) {
                if(position == 0) {
                    return TYPE_FULL_HEADER;
                } else if (position < mFullAccounts.size() + 1) {
                    return TYPE_FULL_ACCOUNT;
                } else if (position == mFullAccounts.size() + 1) {
                    return TYPE_ADDRESS_HEADER;
                } else {
                    return TYPE_ADDRESS_ACCOUNT;
                }
            } else if (mFullAccounts.size() == 0) {
                if(position == 0) {
                    return TYPE_ADDRESS_HEADER ;
                } else {
                    return TYPE_ADDRESS_ACCOUNT;
                }
            } else {
                if(position == 0) {
                    return TYPE_FULL_HEADER ;
                } else {
                    return TYPE_FULL_ACCOUNT;
                }
            }
        }


        public class AccountFullHeaderHolder extends RecyclerView.ViewHolder {
            public AccountFullHeaderHolder(@NonNull View itemView) {
                super(itemView);
            }
        }

        public class AccountAddressHeaderHolder extends RecyclerView.ViewHolder {
            public AccountAddressHeaderHolder(@NonNull View itemView) {
                super(itemView);
            }
        }

        public class AccountListHolder extends RecyclerView.ViewHolder {
            CardView wallet_card;
            TextView wallet_name, wallet_atom_amount, wallet_atom_title, wallet_address;
            public AccountListHolder(@NonNull View itemView) {
                super(itemView);
                wallet_card         = itemView.findViewById(R.id.wallet_card);
                wallet_name         = itemView.findViewById(R.id.wallet_name);
                wallet_atom_amount  = itemView.findViewById(R.id.wallet_atom_amount);
                wallet_atom_title   = itemView.findViewById(R.id.wallet_atom_title);
                wallet_address      = itemView.findViewById(R.id.wallet_address);
            }
        }
    }
}
