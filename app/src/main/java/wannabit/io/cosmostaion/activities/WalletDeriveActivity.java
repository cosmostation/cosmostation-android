package wannabit.io.cosmostaion.activities;

import android.content.Context;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WKey;

public class WalletDeriveActivity extends BaseActivity implements View.OnClickListener {

    private String mHdSeed;
    private String mEntropy;
    private int mWordSize;

    private Toolbar mToolbar;
    private TextView mAccountCnt, mChainCnt;
    private LinearLayout mPathLayer;
    private TextView mPathText;
    private RecyclerView mAccountRecyclerView;
    private Button mBtnAdd;

    private AccountListAdapter mAccountListAdapter;

    private int mPath = 0;
    private ArrayList<BaseChain> selectedChain = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet_derive);
        mToolbar = findViewById(R.id.tool_bar);
        mAccountCnt = findViewById(R.id.account_cnt);
        mChainCnt = findViewById(R.id.chain_cnt);
        mPathLayer = findViewById(R.id.path_layer);
        mPathText = findViewById(R.id.path);
        mAccountRecyclerView = findViewById(R.id.recycler);
        mBtnAdd = findViewById(R.id.btn_add);

        mPathLayer.setOnClickListener(this);
        mBtnAdd.setOnClickListener(this);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mAccountRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mAccountRecyclerView.setHasFixedSize(true);
        mAccountListAdapter = new AccountListAdapter();
        mAccountRecyclerView.setAdapter(mAccountListAdapter);

        mHdSeed = getIntent().getStringExtra("HDseed");
        mEntropy = getIntent().getStringExtra("entropy");
        mWordSize = getIntent().getIntExtra("size", 24);

        onUpdateView();

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

    private void onUpdateView() {
        mChainCnt.setText("" + BaseChain.SUPPORT_CHAINS().size());
    }

    private void onUpdatePath() {
        mPathText.setText("" + mPath);
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mPathLayer)) {
            onSelectedPathMenu(v);

        } else if (v.equals(mBtnAdd)) {

        }
    }

    private void onSelectedPathMenu(View v) {
        Context wrapper = new ContextThemeWrapper(this, R.style.PopupMenu);
        PopupMenu popupMenu = new PopupMenu(wrapper, v, Gravity.END, 0, R.style.PopupMenu);
        getMenuInflater().inflate(R.menu.path_popup, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem path) {
                switch (path.getItemId()) {
                    case R.id.action_path0:
                        mPath = 0;
                        break;

                    case R.id.action_path1:
                        mPath = 1;
                        break;
                }
                mAccountListAdapter.notifyDataSetChanged();
                onUpdatePath();
                return false;
            }
        });
        popupMenu.show();
    }

    private class AccountListAdapter extends RecyclerView.Adapter<AccountListAdapter.AccountHolder> {

        @NonNull
        @Override
        public AccountHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
            return new AccountHolder(getLayoutInflater().inflate(R.layout.item_mnemonic_account_list, viewGroup, false));
        }

        @Override
        public void onBindViewHolder(@NonNull AccountHolder holder, int position) {
            final BaseChain baseChain = BaseChain.SUPPORT_CHAINS().get(position);
            WDp.getChainImg(WalletDeriveActivity.this, baseChain, holder.accountChainImg);
            holder.accountKeyPath.setText(WDp.getPath(baseChain, mPath, 0));
//            holder.accountAddress.setText(WKey.getCreateDpAddressFromEntropy(baseChain, mEntropy, mPath, 0));

            holder.accountCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (selectedChain.contains(baseChain)) {
                        selectedChain.remove(baseChain);
                        holder.accountCard.setBackground(getResources().getDrawable(R.drawable.box_account_unselected));
                    } else {
                        selectedChain.add(baseChain);
                        holder.accountCard.setBackground(getResources().getDrawable(R.drawable.box_round_seleted_white));
                    }
                }
            });
        }

        @Override
        public int getItemViewType(int position) {
            return position;
        }

        @Override
        public int getItemCount() {
            return BaseChain.SUPPORT_CHAINS().size();
        }

        public class AccountHolder extends RecyclerView.ViewHolder {
            FrameLayout accountCard;
            LinearLayout accountContent;
            ImageView accountChainImg;
            TextView accountAddress, accountState, accountKeyPath, accountAvailable, accountDenom;

            public AccountHolder(@NonNull View itemView) {
                super(itemView);
                accountCard = itemView.findViewById(R.id.accountCard);
                accountContent = itemView.findViewById(R.id.accountContent);
                accountChainImg = itemView.findViewById(R.id.chain_img);
                accountAddress = itemView.findViewById(R.id.account_address);
                accountState = itemView.findViewById(R.id.account_state);
                accountKeyPath = itemView.findViewById(R.id.key_path);
                accountAvailable = itemView.findViewById(R.id.accountAvailable);
                accountDenom = itemView.findViewById(R.id.accountDenom);

            }
        }
    }
}
