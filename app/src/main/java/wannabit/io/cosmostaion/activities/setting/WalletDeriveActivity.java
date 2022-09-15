package wannabit.io.cosmostaion.activities.setting;

import static wannabit.io.cosmostaion.base.BaseChain.BNB_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.OKEX_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.isGRPC;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import cosmos.base.v1beta1.CoinOuterClass;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.chains.ChainConfig;
import wannabit.io.cosmostaion.base.chains.ChainFactory;
import wannabit.io.cosmostaion.crypto.CryptoHelper;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.dao.Derive;
import wannabit.io.cosmostaion.dao.MWords;
import wannabit.io.cosmostaion.dialog.CommonAlertDialog;
import wannabit.io.cosmostaion.dialog.NumberPickerDialog;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.res.ResBnbAccountInfo;
import wannabit.io.cosmostaion.network.res.ResOkAccountToken;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.task.UserTask.GenerateAccountTask;
import wannabit.io.cosmostaion.task.UserTask.OverrideAccountTask;
import wannabit.io.cosmostaion.task.gRpcTask.BalanceGrpcTask;
import wannabit.io.cosmostaion.utils.PushManager;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WKey;

public class WalletDeriveActivity extends BaseActivity implements View.OnClickListener, TaskListener {

    private String mPKey;

    private Toolbar mToolbar;
    private TextView mToolbarTitle;
    private RelativeLayout mPathLayer;
    private TextView mPathText;
    private RecyclerView mAccountRecyclerView;
    private Button mBtnAdd;

    private AccountListAdapter mAccountListAdapter;

    private boolean mPrivateKeyMode = false;
    private boolean mIsDetailMode = false;
    private MWords mWords;

    private int mPath = 0;
    private final ArrayList<Derive> mDerives = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet_derive);
        mToolbar = findViewById(R.id.tool_bar);
        mToolbarTitle = findViewById(R.id.tool_title);
        mPathLayer = findViewById(R.id.hd_path_layer);
        mPathText = findViewById(R.id.path);
        mAccountRecyclerView = findViewById(R.id.recycler);
        mBtnAdd = findViewById(R.id.btn_add);

        mPathLayer.setOnClickListener(this);
        mBtnAdd.setOnClickListener(this);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        mAccountRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mAccountRecyclerView.setHasFixedSize(true);
        mAccountListAdapter = new AccountListAdapter();
        mAccountRecyclerView.setAdapter(mAccountListAdapter);

        mIsDetailMode = getIntent().getBooleanExtra("isDetailMode", false);
        if (mIsDetailMode) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        mPrivateKeyMode = getIntent().getBooleanExtra("privateKeyMode", false);
        if (mPrivateKeyMode) {
            mPKey = getIntent().getStringExtra("privateKey");
            mToolbarTitle.setText(getString(R.string.str_restore_key));
            mPathLayer.setVisibility(View.GONE);
        } else {
            mWords = getBaseDao().onSelectMnemonicById(getIntent().getLongExtra("id", -1));
            if (mWords != null) {
                mPKey = CryptoHelper.doDecryptData(getString(R.string.key_mnemonic) + mWords.uuid, mWords.resource, mWords.spec);
            }
            mToolbarTitle.setText(mWords.getName());
            mPathLayer.setVisibility(View.VISIBLE);
            mPathText.setText("" + mPath);
        }
        loadData();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
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

    private void loadData() {
        onShowWaitDialog();
        new Thread(() -> onGetAllKeyTypes()).start();
    }

    private void onGetAllKeyTypes() {
        runOnUiThread(() -> mAccountListAdapter.notifyDataSetChanged());

        mDerives.clear();
        for (BaseChain chain : BaseChain.SUPPORT_CHAINS()) {
            ChainConfig chainConfig = ChainFactory.getChain(chain);
            for (int i = 0; i < chainConfig.supportHdPaths().size(); i++) {
                String dpAddress = "";
                if (mPrivateKeyMode) {
                    dpAddress = WKey.getCreateDpAddressFromPkey(chain, mPKey, i);
                } else {
                    dpAddress = WKey.getCreateDpAddressFromEntropy(chain, mPKey, mPath, i);
                }
                int status = -1;
                Account checkAccount = getBaseDao().onSelectExistAccount(dpAddress, chain);
                if (checkAccount != null) {
                    if (checkAccount.hasPrivateKey) {
                        status = 2;
                    } else {
                        status = 1;
                    }
                } else {
                    status = 0;
                }
                Derive derive = new Derive(chain, i, mPath, chainConfig.getHdPath(i, String.valueOf(mPath)), dpAddress, status);
                if (!mDerives.stream().filter(item -> item.dpAddress.equalsIgnoreCase(derive.dpAddress)).findAny().isPresent()) {
                    mDerives.add(derive);
                }
            }
        }

        runOnUiThread(() -> {
            mAccountListAdapter.notifyDataSetChanged();
            onHideWaitDialog();
        });
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mPathLayer) && !this.isFinishing()) {
            NumberPickerDialog numberPicker = NumberPickerDialog.newInstance(null);
            numberPicker.selectListener = this::onSelectedHdPath;
            numberPicker.setCancelable(false);
            numberPicker.show(getSupportFragmentManager(), "dialog");

        } else if (v.equals(mBtnAdd)) {
            long selectedCnt = mDerives.stream().filter(derive -> derive.selected).count();
            if (selectedCnt == 0) {
                Toast.makeText(this, R.string.error_not_selected_to_import, Toast.LENGTH_SHORT).show();
                return;
            }
            CommonAlertDialog.showDoubleButton(this, getString(R.string.str_add_new), String.format(getString(R.string.str_add_wallet), String.valueOf(selectedCnt)),
                    getString(R.string.str_cancel), null, getString(R.string.str_confirm), view -> onSaveAccount());
        }
    }

    public void onSelectedHdPath(int path) {
        mPath = path;
        mPathText.setText("" + path);
        loadData();
    }

    private class AccountListAdapter extends RecyclerView.Adapter<AccountListAdapter.AccountHolder> {

        @NonNull
        @Override
        public AccountHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
            return new AccountHolder(getLayoutInflater().inflate(R.layout.item_mnemonic_account_list, viewGroup, false));
        }

        @Override
        public void onBindViewHolder(@NonNull AccountHolder holder, int position) {
            final Derive derive = mDerives.get(position);
            final BaseChain baseChain = derive.baseChain;
            final ChainConfig chainConfig = ChainFactory.getChain(baseChain);
            holder.accountChainImg.setImageResource(chainConfig.chainImg());
            holder.accountAddress.setText(derive.dpAddress);

            if (mPrivateKeyMode) {
                holder.accountKeyPath.setVisibility(View.GONE);
            } else {
                holder.accountKeyPath.setVisibility(View.VISIBLE);
                holder.accountKeyPath.setText(derive.fullPath);
            }

            if (derive.status == 2) {
                holder.accountState.setText("Imported");
                holder.accountCard.setBackground(ContextCompat.getDrawable(WalletDeriveActivity.this, R.drawable.box_white2_border));
                holder.accountDimLayer.setVisibility(View.VISIBLE);
                holder.accountDimLayer.setAlpha(0.5f);
            } else {
                if (derive.selected) {
                    holder.accountCard.setBackground(ContextCompat.getDrawable(WalletDeriveActivity.this, R.drawable.box_account_selected_photon));
                } else {
                    holder.accountCard.setBackground(ContextCompat.getDrawable(WalletDeriveActivity.this, R.drawable.box_account_unselected));
                }
                holder.accountDimLayer.setVisibility(View.GONE);
                holder.accountState.setText("");
            }

            holder.accountCard.setOnClickListener(view -> {
                if (derive.status == 2) {
                    return;
                }
                derive.selected = !derive.selected;
                if (derive.selected) {
                    holder.accountCard.setBackground(ContextCompat.getDrawable(WalletDeriveActivity.this, R.drawable.box_account_selected_photon));
                } else {
                    holder.accountCard.setBackground(ContextCompat.getDrawable(WalletDeriveActivity.this, R.drawable.box_account_unselected));
                }
            });

            loadBalance(holder, derive);
        }


        private void loadBalance(AccountHolder holder, Derive derive) {
            ChainConfig chainConfig = ChainFactory.getChain(derive.baseChain);
            if (derive.coin != null) {
                WDp.setDpCoin(WalletDeriveActivity.this, getBaseDao(), chainConfig, derive.coin, holder.accountDenom, holder.accountAvailable);
                return;
            }

            if (isGRPC(derive.baseChain)) {
                new Thread(() -> new BalanceGrpcTask(getBaseApplication(), result -> {
                    ArrayList<CoinOuterClass.Coin> balances = (ArrayList<CoinOuterClass.Coin>) result.resultData;
                    if (balances != null && balances.size() > 0) {
                        for (CoinOuterClass.Coin coin : balances) {
                            if (coin.getDenom().equalsIgnoreCase(chainConfig.mainDenom())) {
                                derive.coin = new Coin(coin.getDenom(), coin.getAmount());
                                runOnUiThread(() -> WDp.setDpCoin(WalletDeriveActivity.this, getBaseDao(), ChainFactory.getChain(derive.baseChain), derive.coin, holder.accountDenom, holder.accountAvailable));
                                return;
                            }
                        }
                    }

                    derive.coin = new Coin(chainConfig.mainDenom(), "0");
                    runOnUiThread(() -> WDp.setDpCoin(WalletDeriveActivity.this, getBaseDao(), ChainFactory.getChain(derive.baseChain), derive.coin, holder.accountDenom, holder.accountAvailable));
                }, derive.baseChain, derive.dpAddress).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)).start();

            } else {
                if (derive.baseChain.equals(BNB_MAIN)) {
                    new Thread(() -> ApiClient.getBnbChain().getAccountInfo(derive.dpAddress).enqueue(new Callback<ResBnbAccountInfo>() {
                        @Override
                        public void onResponse(Call<ResBnbAccountInfo> call, Response<ResBnbAccountInfo> response) {
                            if (response.isSuccessful() && response.body() != null && response.body().balances != null) {
                                for (ResBnbAccountInfo.BnbBalance balance : response.body().balances) {
                                    if (balance.symbol.equalsIgnoreCase(chainConfig.mainDenom())) {
                                        derive.coin = new Coin(balance.symbol, balance.free);
                                        runOnUiThread(() -> WDp.setDpCoin(WalletDeriveActivity.this, getBaseDao(), ChainFactory.getChain(derive.baseChain), derive.coin, holder.accountDenom, holder.accountAvailable));
                                        return;
                                    }
                                }
                            }

                            derive.coin = new Coin(chainConfig.mainDenom(), "0");
                            runOnUiThread(() -> WDp.setDpCoin(WalletDeriveActivity.this, getBaseDao(), ChainFactory.getChain(derive.baseChain), derive.coin, holder.accountDenom, holder.accountAvailable));
                        }

                        @Override
                        public void onFailure(Call<ResBnbAccountInfo> call, Throwable t) {
                        }
                    })).start();

                } else if (derive.baseChain.equals(OKEX_MAIN)) {
                    new Thread(() -> ApiClient.getOkexChain().getAccountBalance(derive.dpAddress).enqueue(new Callback<ResOkAccountToken>() {
                        @Override
                        public void onResponse(Call<ResOkAccountToken> call, Response<ResOkAccountToken> response) {
                            if (response.isSuccessful() && response.body() != null && response.body().data != null && response.body().data.currencies != null) {
                                for (ResOkAccountToken.OkCurrency balance : response.body().data.currencies) {
                                    if (balance.symbol.equals(chainConfig.mainDenom())) {
                                        derive.coin = new Coin(balance.symbol, balance.available);
                                        runOnUiThread(() -> WDp.setDpCoin(WalletDeriveActivity.this, getBaseDao(), ChainFactory.getChain(derive.baseChain), derive.coin, holder.accountDenom, holder.accountAvailable));
                                        return;
                                    }
                                }
                            }

                            derive.coin = new Coin(chainConfig.mainDenom(), "0");
                            runOnUiThread(() -> WDp.setDpCoin(WalletDeriveActivity.this, getBaseDao(), ChainFactory.getChain(derive.baseChain), derive.coin, holder.accountDenom, holder.accountAvailable));
                        }

                        @Override
                        public void onFailure(Call<ResOkAccountToken> call, Throwable t) {
                        }
                    })).start();
                }
            }
        }

        @Override
        public int getItemViewType(int position) {
            return position;
        }

        @Override
        public int getItemCount() {
            return mDerives.size();
        }

        public class AccountHolder extends RecyclerView.ViewHolder {
            FrameLayout accountCard, accountDimLayer;
            LinearLayout accountContent;
            ImageView accountChainImg;
            TextView accountAddress, accountState, accountKeyPath, accountAvailable, accountDenom;

            public AccountHolder(@NonNull View itemView) {
                super(itemView);
                accountCard = itemView.findViewById(R.id.accountCard);
                accountDimLayer = itemView.findViewById(R.id.dim_layer);
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

    public void onSaveAccount() {
        onShowWaitDialog();
        for (Derive derive : mDerives) {
            if (derive.selected) {
                mTaskCount = mTaskCount + 1;
                if (derive.status == 1) {
                    new OverrideAccountTask(getBaseApplication(), this, mWords, derive, mPKey, mPrivateKeyMode).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                } else {
                    new GenerateAccountTask(getBaseApplication(), this, mWords, derive, mPKey, mPrivateKeyMode).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                }
            }
        }
    }

    @Override
    public void onTaskResponse(TaskResult result) {
        if (isFinishing()) return;
        mTaskCount--;
        if (result.isSuccess) {
            Derive initDerive = mDerives.stream().filter(derive -> derive.selected).findFirst().get();
            Account initAccount = getBaseDao().onSelectExistAccount(initDerive.dpAddress, initDerive.baseChain);
            if (initAccount != null && initAccount.id != null) {
                getBaseDao().setLastUser(initAccount.id);
                getBaseDao().setLastChain(initDerive.baseChain.getChain());
            }
        }

        if (mTaskCount == 0) {
            PushManager.syncAddresses(getApplicationContext(), getBaseDao(), getBaseDao().getFCMToken());
            onHideWaitDialog();
            onStartMainActivity(0);
            finish();
        }
    }
}
