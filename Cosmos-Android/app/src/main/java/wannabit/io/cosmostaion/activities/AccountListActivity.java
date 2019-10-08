package wannabit.io.cosmostaion.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.dialog.Dialog_AddAccount;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WLog;

public class AccountListActivity extends BaseActivity implements View.OnClickListener {

    private Toolbar                     mToolbar;
    private RecyclerView                mChainRecyclerView;
    private RecyclerView                mAccountRecyclerView;

    private ChainListAdapter            mChainListAdapter;
    private AccountListAdapter          mAccountListAdapter;
    private ItemTouchHelper             mItemTouchHelper;
    private int                         mSelectChainPosition = 0;
    private int                         mSelectAccountPosition = 0;
    private boolean                     isEditMode;
    private ArrayList<Account>          mAccounts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_list);
        mToolbar                = findViewById(R.id.tool_bar);
        mChainRecyclerView      = findViewById(R.id.chain_recycler);
        mAccountRecyclerView    = findViewById(R.id.account_recycler);

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

        mItemTouchHelper = new ItemTouchHelper(new ItemTouchHelperCallback(mAccountListAdapter));
        mItemTouchHelper.attachToRecyclerView(mAccountRecyclerView);
        mItemTouchHelper.attachToRecyclerView(null);


        mAccount = getBaseDao().onSelectAccount(getBaseDao().getLastUser());
        mBaseChain = BaseChain.getChain(mAccount.baseChain);

        onChainSelected(mSelectChainPosition);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.clear();
        MenuInflater inflater = getMenuInflater();
        if (isEditMode && mSelectChainPosition == 0) {
            inflater.inflate(R.menu.account_edite_menu, menu);

        } else if (!isEditMode && mSelectChainPosition == 0) {
            inflater.inflate(R.menu.account_done_menu, menu);

        } else {
            inflater.inflate(R.menu.account_normal_menu, menu);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.menu_sorting:
                isEditMode = !isEditMode;
                onEditModeUpdate();
                return true;
            case R.id.menu_done:
                isEditMode = !isEditMode;
                onEditModeUpdate();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void onEditModeUpdate() {
        if(isEditMode) {
            mItemTouchHelper.attachToRecyclerView(mAccountRecyclerView);
        } else {
            mItemTouchHelper.attachToRecyclerView(null);
        }
        mAccountListAdapter.notifyDataSetChanged();
        invalidateOptionsMenu();
    }

    private void onChainSelected(int position) {
        if (isEditMode) {
            isEditMode = !isEditMode;
        }
        invalidateOptionsMenu();
        mSelectChainPosition = position;
        mChainListAdapter.notifyDataSetChanged();
        if (mSelectChainPosition == 0) {
            mAccounts = getBaseDao().onSelectAccounts();

        } else if (mSelectChainPosition == 1) {
            mAccounts = getBaseDao().onSelectAccountsByChain(BaseChain.COSMOS_MAIN);

        } else if (mSelectChainPosition == 2) {
            mAccounts = getBaseDao().onSelectAccountsByChain(BaseChain.IRIS_MAIN);

        } else if (mSelectChainPosition == 3) {
            mAccounts = getBaseDao().onSelectAccountsByChain(BaseChain.BNB_MAIN);

        } else if (mSelectChainPosition == 4) {
            mAccounts = getBaseDao().onSelectAccountsByChain(BaseChain.IOV_MAIN);

        }
        mAccountListAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {

    }

    private class ChainListAdapter extends RecyclerView.Adapter<ChainListAdapter.ChainHolder> {

        @NonNull
        @Override
        public ChainListAdapter.ChainHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
            return new ChainHolder(getLayoutInflater().inflate(R.layout.item_accountlist_chain, viewGroup, false));
        }

        @Override
        public void onBindViewHolder(@NonNull ChainListAdapter.ChainHolder holder, int position) {
            holder.chainCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mSelectChainPosition != position) {
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                onChainSelected(position);
                            }
                        },150);
                    }
                }
            });

            if (position == 0) {
                holder.chainLayer.setVisibility(View.GONE);
                holder.allLayer.setVisibility(View.VISIBLE);
                if (mSelectChainPosition == position) {
                    holder.chainCard.setBackground(getResources().getDrawable(R.drawable.box_chain_selected));
                    holder.chainAll.setTextColor(getColor(R.color.colorWhite));
                } else {
                    holder.chainCard.setBackground(getResources().getDrawable(R.drawable.box_chain_unselected));
                    holder.chainAll.setTextColor(getColor(R.color.colorGray4));
                }
                return;

            } else if (position == 1) {
                holder.chainLayer.setVisibility(View.VISIBLE);
                holder.allLayer.setVisibility(View.GONE);
                holder.chainImg.setImageDrawable(getResources().getDrawable(R.drawable.cosmos_wh_main));
                holder.chainName.setText(getString(R.string.str_cosmos));


            } else if (position == 2) {
                holder.chainLayer.setVisibility(View.VISIBLE);
                holder.allLayer.setVisibility(View.GONE);
                holder.chainImg.setImageDrawable(getResources().getDrawable(R.drawable.iris_wh));
                holder.chainName.setText(getString(R.string.str_iris));

            } else if (position == 3) {
                holder.chainLayer.setVisibility(View.VISIBLE);
                holder.allLayer.setVisibility(View.GONE);
                holder.chainImg.setImageDrawable(getResources().getDrawable(R.drawable.binance_ch_img));
                holder.chainName.setText(getString(R.string.str_binance));

            } else if (position == 4) {
                holder.chainLayer.setVisibility(View.VISIBLE);
                holder.allLayer.setVisibility(View.GONE);
                holder.chainImg.setImageDrawable(getResources().getDrawable(R.drawable.iov_img));
                holder.chainName.setText(getString(R.string.str_iov));

            }

            if (mSelectChainPosition == position) {
                holder.chainCard.setBackground(getResources().getDrawable(R.drawable.box_chain_selected));
                holder.chainImg.setAlpha(1f);
                holder.chainName.setTextColor(getColor(R.color.colorWhite));
            } else {
                holder.chainCard.setBackground(getResources().getDrawable(R.drawable.box_chain_unselected));
                holder.chainImg.setAlpha(0.1f);
                holder.chainName.setTextColor(getColor(R.color.colorGray4));
            }

        }

        @Override
        public int getItemCount() {
            return 5;
        }


        public class ChainHolder extends RecyclerView.ViewHolder {
            FrameLayout chainCard;
            LinearLayout chainLayer, allLayer;
            ImageView  chainImg;
            TextView chainName, chainAll;
            public ChainHolder(@NonNull View itemView) {
                super(itemView);
                chainCard       = itemView.findViewById(R.id.chainCard);
                chainLayer      = itemView.findViewById(R.id.chainLayer);
                allLayer        = itemView.findViewById(R.id.allLayer);
                chainImg        = itemView.findViewById(R.id.chainImg);
                chainName       = itemView.findViewById(R.id.chainName);
                chainAll        = itemView.findViewById(R.id.chainAll);
            }
        }
    }


    private class AccountListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements ItemTouchHelperListener {
        private static final int TYPE_ACCOUNT       = 0;
        private static final int TYPE_ADD           = 1;

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
            if(viewType == TYPE_ACCOUNT) {
                return new AccountHolder(getLayoutInflater().inflate(R.layout.item_accountlist_account, viewGroup, false));
            } else {
                return new AccountAddHolder(getLayoutInflater().inflate(R.layout.item_accountlist_add, viewGroup, false));
            }
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
            if (getItemViewType(position) == TYPE_ACCOUNT) {
                final AccountHolder holder = (AccountHolder)viewHolder;
                final Account account = mAccounts.get(position);

                WDp.DpMainDenom(getBaseContext(), account.baseChain, holder.accountDenom);
                holder.accountAddress.setText(account.address);
                holder.accountAvailable.setText(account.getLastTotal(getBaseContext(), BaseChain.getChain(account.baseChain)));
                holder.accountKeyState.setColorFilter(ContextCompat.getColor(getBaseContext(), R.color.colorGray0), android.graphics.PorterDuff.Mode.SRC_IN);
                if (account.hasPrivateKey) {
                    holder.accountKeyState.setColorFilter(WDp.getChainColor(getBaseContext(), BaseChain.getChain(account.baseChain)), android.graphics.PorterDuff.Mode.SRC_IN);
                }

                if (TextUtils.isEmpty(account.nickName)){
                    holder.accountName.setText(getString(R.string.str_my_wallet) + account.id);
                } else {
                    holder.accountName.setText(account.nickName);
                }

                if (isEditMode) {
                    holder.accountArrowSort.setImageDrawable(getDrawable(R.drawable.ic_handle));
                    holder.accountArrowSort.setOnTouchListener(new View.OnTouchListener() {
                        public boolean onTouch(View v, MotionEvent event) {
                            if (event.getActionMasked() == MotionEvent.ACTION_DOWN) {
                                mItemTouchHelper.startDrag(viewHolder);
                            }
                            return false;
                        }
                    });

                } else {
                    holder.accountArrowSort.setImageDrawable(getDrawable(R.drawable.arrow_next_gr));
                    holder.accountCard.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(AccountListActivity.this, AccountDetailActivity.class);
                            intent.putExtra("id", ""+account.id);
                            startActivity(intent);
                        }
                    });
                }


            }  else if (getItemViewType(position) == TYPE_ADD) {
                final AccountAddHolder holder = (AccountAddHolder)viewHolder;
                holder.btn_account_add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Dialog_AddAccount add = Dialog_AddAccount.newInstance(null);
                        add.setCancelable(true);
                        getSupportFragmentManager().beginTransaction().add(add, "dialog").commitNowAllowingStateLoss();
                        //TODO need chain select
                    }
                });
            }
        }

        @Override
        public int getItemCount() {
            if (mSelectChainPosition == 0) {
                return mAccounts.size();
            } else {
                if (mAccounts.size() > 5) {
                    return mAccounts.size();
                } else {
                    return mAccounts.size() + 1;
                }
            }
        }

        @Override
        public int getItemViewType(int position) {
            if (mSelectChainPosition == 0) {
                return TYPE_ACCOUNT;
            } else {
                if (mAccounts.size() >= 5) {
                    return TYPE_ACCOUNT;
                } else {
                    if (position < mAccounts.size()) {
                        return TYPE_ACCOUNT;
                    } else {
                        return TYPE_ADD;
                    }
                }
            }
        }

        @Override
        public boolean onItemMove(int fromPosition, int toPosition) {
            Account fromItem = mAccounts.get(fromPosition);
            mAccounts.remove(fromPosition);
            mAccounts.add(toPosition, fromItem);

            notifyItemMoved(fromPosition, toPosition);
            return true;
        }


        public class AccountHolder extends RecyclerView.ViewHolder {
            FrameLayout accountCard;
            LinearLayout accountContent;
            ImageView  accountArrowSort, accountKeyState;
            TextView accountName, accountAddress, accountAvailable, accountDenom;
            public AccountHolder(@NonNull View itemView) {
                super(itemView);
                accountCard         = itemView.findViewById(R.id.accountCard);
                accountArrowSort    = itemView.findViewById(R.id.accountArrowSort);
                accountContent      = itemView.findViewById(R.id.accountContent);
                accountKeyState     = itemView.findViewById(R.id.accountKeyState);
                accountName         = itemView.findViewById(R.id.accountName);
                accountAddress      = itemView.findViewById(R.id.accountAddress);
                accountAvailable    = itemView.findViewById(R.id.accountAvailable);
                accountDenom        = itemView.findViewById(R.id.accountDenom);
            }
        }

        public class AccountAddHolder extends RecyclerView.ViewHolder {
            Button btn_account_add;
            public AccountAddHolder(@NonNull View itemView) {
                super(itemView);
                btn_account_add    = itemView.findViewById(R.id.btn_account_add);
            }
        }
    }

    public class ItemTouchHelperCallback extends ItemTouchHelper.Callback {
        ItemTouchHelperListener listener;

        public ItemTouchHelperCallback(ItemTouchHelperListener listener){
            this.listener = listener;
        }

        @Override
        public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
            int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
            int swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;

            return makeMovementFlags(dragFlags, swipeFlags);
        }

        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder source, @NonNull RecyclerView.ViewHolder target) {
            return listener.onItemMove(source.getAdapterPosition(), target.getAdapterPosition());
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        }
    }

    public interface ItemTouchHelperListener {
        boolean onItemMove(int fromPosition, int toPosition);
    }


}
/*
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
        mAccountListAdapter.notifyDataSetChanged();

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

                if (TextUtils.isEmpty(account.nickName)) {
                    holder.wallet_name.setText(getString(R.string.str_my_wallet) + account.id);
                } else {
                    holder.wallet_name.setText(account.nickName);
                }

                if (getBaseDao().onSelectBalance(account.id).size() > 0) {
                    holder.wallet_balance.setText(WDp.getDpAmount(getBaseContext(), getBaseDao().onSelectBalance(account.id).get(0).balance, 6, BaseChain.getChain(account.baseChain)));
                } else {
                    holder.wallet_balance.setText(WDp.getDpAmount(getBaseContext(), BigDecimal.ZERO, 6, BaseChain.getChain(account.baseChain)));
                }

                WDp.DpMainDenom(getBaseContext(), account.baseChain, holder.wallet_denom);
                if (BaseChain.getChain(account.baseChain).equals(BaseChain.COSMOS_MAIN)) {
                    holder.wallet_chain_img.setImageDrawable(getResources().getDrawable(R.drawable.cosmos_wh_main));
                    holder.wallet_card.setCardBackgroundColor(getResources().getColor(R.color.colorTransBg2));
                } else if (BaseChain.getChain(account.baseChain).equals(BaseChain.IRIS_MAIN)) {
                    holder.wallet_chain_img.setImageDrawable(getResources().getDrawable(R.drawable.iris_wh));
                    holder.wallet_card.setCardBackgroundColor(getResources().getColor(R.color.colorTransBg4));
                } else if (BaseChain.getChain(account.baseChain).equals(BaseChain.BNB_MAIN)) {
                    holder.wallet_chain_img.setImageDrawable(getResources().getDrawable(R.drawable.binance_ch_img));
                    holder.wallet_card.setCardBackgroundColor(getResources().getColor(R.color.colorTransBg5));
                }

                holder.wallet_address.setText(account.address);
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

                if(mFullAccounts.size() == 0) {
                    account = mAddressAccounts.get(position - 1);
                } else {
                    account = mAddressAccounts.get(position  - 2 - mFullAccounts.size());
                }

                if (TextUtils.isEmpty(account.nickName)){
                    holder.wallet_name.setText(getString(R.string.str_my_wallet) + account.id);
                } else  {
                    holder.wallet_name.setText(account.nickName);
                }

                if (getBaseDao().onSelectBalance(account.id).size() > 0) {
                    holder.wallet_balance.setText(WDp.getDpAmount(getBaseContext(), getBaseDao().onSelectBalance(account.id).get(0).balance, 6, BaseChain.getChain(account.baseChain)));
                } else {
                    holder.wallet_balance.setText(WDp.getDpAmount(getBaseContext(), BigDecimal.ZERO, 6, BaseChain.getChain(account.baseChain)));
                }

                WDp.DpMainDenom(getBaseContext(), account.baseChain, holder.wallet_denom);
                if (BaseChain.getChain(account.baseChain).equals(BaseChain.COSMOS_MAIN)) {
                    holder.wallet_chain_img.setImageDrawable(getResources().getDrawable(R.drawable.cosmos_wh_main));
                    holder.wallet_card.setCardBackgroundColor(getResources().getColor(R.color.colorTransBg2));
                } else if (BaseChain.getChain(account.baseChain).equals(BaseChain.IRIS_MAIN)) {
                    holder.wallet_chain_img.setImageDrawable(getResources().getDrawable(R.drawable.iris_wh));
                    holder.wallet_card.setCardBackgroundColor(getResources().getColor(R.color.colorTransBg4));
                } else if (BaseChain.getChain(account.baseChain).equals(BaseChain.BNB_MAIN)) {
                    holder.wallet_chain_img.setImageDrawable(getResources().getDrawable(R.drawable.binance_ch_img));
                    holder.wallet_card.setCardBackgroundColor(getResources().getColor(R.color.colorTransBg5));
                }


                holder.wallet_address.setText(account.address);

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
            ImageView  wallet_chain_img;
            TextView wallet_name, wallet_balance, wallet_denom, wallet_address;
            public AccountListHolder(@NonNull View itemView) {
                super(itemView);
                wallet_card         = itemView.findViewById(R.id.wallet_card);
                wallet_chain_img    = itemView.findViewById(R.id.account_chain_img);
                wallet_name         = itemView.findViewById(R.id.wallet_name);
                wallet_balance      = itemView.findViewById(R.id.wallet_balance_amount);
                wallet_denom        = itemView.findViewById(R.id.wallet_denom);
                wallet_address      = itemView.findViewById(R.id.wallet_address);
            }
        }
    }
}
*/