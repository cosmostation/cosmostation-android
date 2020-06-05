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
import android.widget.TextView;

import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.dialog.Dialog_AddAccount;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WUtil;

public class AccountListActivity extends BaseActivity implements View.OnClickListener {

    private Toolbar                     mToolbar;
    private RecyclerView                mChainRecyclerView;
    private RecyclerView                mAccountRecyclerView;

    private ChainListAdapter            mChainListAdapter;
    private AccountListAdapter          mAccountListAdapter;
    private ItemTouchHelper             mItemTouchHelper;
    private int                         mSelectChainPosition = 0;
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

        mAccount = getBaseDao().onSelectAccount(getBaseDao().getLastUser());
        mBaseChain = BaseChain.getChain(mAccount.baseChain);
        mSelectChainPosition = getBaseDao().getLastChain();
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
        getBaseDao().setLastChain(mSelectChainPosition);
        mChainListAdapter.notifyDataSetChanged();
        if (mSelectChainPosition == 0) {
            mAccounts = getBaseDao().onSelectAccounts();

        } else {
            final BaseChain chain = BaseChain.SUPPORT_CHAINS().get(position - 1);
            mAccounts = getBaseDao().onSelectAccountsByChain(chain);

        }
        WUtil.onSortingAccount(mAccounts);
        mAccountListAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {

    }

    private void onSaveNewOrder() {
        for (int i = 0 ; i < mAccounts.size(); i ++) {
                mAccounts.get(i).sortOrder = Long.valueOf(i);
        }
        getBaseDao().onUpdateAccountOrders(mAccounts);
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

            } else {
                final BaseChain chain = BaseChain.SUPPORT_CHAINS().get(position - 1);
                if (chain.equals(BaseChain.COSMOS_MAIN)) {
                    holder.chainLayer.setVisibility(View.VISIBLE);
                    holder.allLayer.setVisibility(View.GONE);
                    holder.chainImg.setImageDrawable(getResources().getDrawable(R.drawable.cosmos_wh_main));
                    holder.chainName.setText(getString(R.string.str_cosmos));

                } else if (chain.equals(BaseChain.IRIS_MAIN)) {
                    holder.chainLayer.setVisibility(View.VISIBLE);
                    holder.allLayer.setVisibility(View.GONE);
                    holder.chainImg.setImageDrawable(getResources().getDrawable(R.drawable.iris_wh));
                    holder.chainName.setText(getString(R.string.str_iris));

                } else if (chain.equals(BaseChain.BNB_MAIN)) {
                    holder.chainLayer.setVisibility(View.VISIBLE);
                    holder.allLayer.setVisibility(View.GONE);
                    holder.chainImg.setImageDrawable(getResources().getDrawable(R.drawable.binance_ch_img));
                    holder.chainName.setText(getString(R.string.str_binance));

                } else if (chain.equals(BaseChain.KAVA_MAIN)) {
                    holder.chainLayer.setVisibility(View.VISIBLE);
                    holder.allLayer.setVisibility(View.GONE);
                    holder.chainImg.setImageDrawable(getResources().getDrawable(R.drawable.kava_img));
                    holder.chainName.setText(getString(R.string.str_kava));

                } else if (chain.equals(BaseChain.IOV_MAIN)) {
                    holder.chainLayer.setVisibility(View.VISIBLE);
                    holder.allLayer.setVisibility(View.GONE);
                    holder.chainImg.setImageDrawable(getResources().getDrawable(R.drawable.iov_img));
                    holder.chainName.setText(getString(R.string.str_iov));

                } else if (chain.equals(BaseChain.BAND_MAIN)) {
                    holder.chainLayer.setVisibility(View.VISIBLE);
                    holder.allLayer.setVisibility(View.GONE);
                    holder.chainImg.setImageDrawable(getResources().getDrawable(R.drawable.band_chain_img));
                    holder.chainName.setText(getString(R.string.str_band));

                } else if (chain.equals(BaseChain.BNB_TEST)) {
                    holder.chainLayer.setVisibility(View.VISIBLE);
                    holder.allLayer.setVisibility(View.GONE);
                    holder.chainImg.setImageDrawable(getResources().getDrawable(R.drawable.binancetestnet));
                    holder.chainName.setText(getString(R.string.str_binance_test));

                } else if (chain.equals(BaseChain.KAVA_TEST)) {
                    holder.chainLayer.setVisibility(View.VISIBLE);
                    holder.allLayer.setVisibility(View.GONE);
                    holder.chainImg.setImageDrawable(getResources().getDrawable(R.drawable.kava_test_img));
                    holder.chainName.setText(getString(R.string.str_kava_test));

                }
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
            return BaseChain.SUPPORT_CHAINS().size() + 1;
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
                                if (mItemTouchHelper != null)
                                    mItemTouchHelper.startDrag(viewHolder);
                            }
                            return false;
                        }
                    });
                    holder.accountCard.setOnClickListener(null);

                } else {
                    holder.accountArrowSort.setImageDrawable(getDrawable(R.drawable.arrow_next_gr));
                    holder.accountArrowSort.setOnTouchListener(null);
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
                        Bundle bundle  = new Bundle();
                        final BaseChain selectChain = BaseChain.SUPPORT_CHAINS().get(mSelectChainPosition - 1);
                        bundle.putString("chain", selectChain.getChain());
                        Dialog_AddAccount add = Dialog_AddAccount.newInstance(bundle);
                        add.setCancelable(true);
                        getSupportFragmentManager().beginTransaction().add(add, "dialog").commitNowAllowingStateLoss();
                    }
                });
            }
        }

        @Override
        public int getItemCount() {
            if (mSelectChainPosition == 0) {
                return mAccounts.size();
            } else {
                if (mAccounts.size() >= 5) {
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
            onSaveNewOrder();
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