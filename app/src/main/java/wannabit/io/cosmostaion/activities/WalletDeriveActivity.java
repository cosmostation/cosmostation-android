package wannabit.io.cosmostaion.activities;

import static wannabit.io.cosmostaion.base.BaseChain.BNB_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.OKEX_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.isGRPC;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

import cosmos.base.v1beta1.CoinOuterClass;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.crypto.CryptoHelper;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.dao.Derive;
import wannabit.io.cosmostaion.dao.MWords;
import wannabit.io.cosmostaion.dialog.AlertDialogUtils;
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
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WKey;

public class WalletDeriveActivity extends BaseActivity implements View.OnClickListener, TaskListener {

    private String mEntropy;

    private Toolbar mToolbar;
    private TextView mToolbarTitle, mAccountCnt, mChainCnt;
    private LinearLayout mCntLayer, mPathLayer;
    private TextView mPathText;
    private RecyclerView mAccountRecyclerView;
    private Button mBtnAdd;

    private AccountListAdapter mAccountListAdapter;

    private boolean mPrivateKeyMode = false;
    private MWords mWords;

    private int mPath = 0;
    private final ArrayList<Derive> mDerives = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet_derive);
        mToolbar = findViewById(R.id.tool_bar);
        mToolbarTitle = findViewById(R.id.tool_title);
        mAccountCnt = findViewById(R.id.account_cnt);
        mChainCnt = findViewById(R.id.chain_cnt);
        mCntLayer = findViewById(R.id.cnt_layer);
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

        if (getIntent().getLongExtra("id", -1) == -1) {
            mWords = getBaseDao().onSelectMnemonicById(Long.valueOf(getIntent().getStringExtra("id")));
        } else {
            mWords = getBaseDao().onSelectMnemonicById(getIntent().getLongExtra("id", -1));
        }

        if (mWords != null) {
            mEntropy = CryptoHelper.doDecryptData(getString(R.string.key_mnemonic) + mWords.uuid, mWords.resource, mWords.spec);
        }

        if (mPrivateKeyMode) {
            mToolbarTitle.setText(getString(R.string.str_restore_key));
            mPathLayer.setVisibility(View.GONE);
        } else {
            mToolbarTitle.setText(mWords.getName());
            mPathLayer.setVisibility(View.VISIBLE);
            mPathText.setText("" + mPath);
        }
        loadData();

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
        new Thread(() -> {
            onGetAllKeyTypes();
        }).start();
    }

    @SuppressLint("NewApi")
    private void onGetAllKeyTypes() {
        runOnUiThread(() -> {
            mDerives.clear();
            mAccountListAdapter.notifyDataSetChanged();
        });
        for (BaseChain chain : BaseChain.SUPPORT_CHAINS()) {
            for (int i = 0; i < hdPathCount(chain); i++) {
                String dpAddress = WKey.getCreateDpAddressFromEntropy(chain, mEntropy, mPath, i);
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
                Derive derive = new Derive(chain, i, mPath, WDp.getAllPath(chain, mPath, i), dpAddress, status);
                if (!mDerives.stream().filter(item -> item.dpAddress.equalsIgnoreCase(derive.dpAddress)).findAny().isPresent()) {
                    mDerives.add(derive);
                }
            }
        }

        runOnUiThread(() -> {
            mAccountListAdapter.notifyDataSetChanged();
            onUpdateCnt();
            onUpdateView();
        });
    }

    private void onUpdateView() {
        onHideWaitDialog();
        if (mPrivateKeyMode) {
            mCntLayer.setVisibility(View.GONE);
        } else {
            mCntLayer.setVisibility(View.VISIBLE);
        }
    }

    @SuppressLint("NewApi")
    private void onUpdateCnt() {
        int allKeyCnt = mDerives.size();
        long alreadyCnt = mDerives.stream().filter(derive -> derive.status == 2).count();
        long selectedCnt = mDerives.stream().filter(derive -> derive.selected).count();

        if (selectedCnt == 0) {
            mAccountCnt.setText("" + alreadyCnt);
            mAccountCnt.setTextColor(getResources().getColor(R.color.colorGray1));
        } else {
            mAccountCnt.setText("" + (alreadyCnt + selectedCnt));
            mAccountCnt.setTextColor(getResources().getColor(R.color.colorPhoton));
        }
        mChainCnt.setText("" + allKeyCnt);
    }

    @SuppressLint("NewApi")
    @Override
    public void onClick(View v) {
        if (v.equals(mPathLayer)) {
            NumberPickerDialog numberPicker = NumberPickerDialog.newInstance(null);
            numberPicker.selectListener = this::onSelectedHdPath;
            numberPicker.setCancelable(false);
            numberPicker.show(getSupportFragmentManager(), "dialog");

        } else if (v.equals(mBtnAdd)) {
            long selectedCnt = mDerives.stream().filter(derive -> derive.selected).count();
            AlertDialogUtils.showDoubleButtonDialog(this, getString(R.string.str_add_new), String.format(getString(R.string.str_add_wallet), String.valueOf(selectedCnt)), getString(R.string.str_cancel), null, getString(R.string.str_confirm), view -> onSaveAccount());
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
            WDp.getChainImg(WalletDeriveActivity.this, baseChain, holder.accountChainImg);
            holder.accountAddress.setText(derive.dpAddress);

            if (mPrivateKeyMode) {
                holder.accountKeyPath.setVisibility(View.GONE);
            } else {
                holder.accountKeyPath.setVisibility(View.VISIBLE);
                holder.accountKeyPath.setText(derive.fullPath);
            }

            if (derive.status == 2) {
                holder.accountState.setText("Imported");
                holder.accountDimLayer.setVisibility(View.VISIBLE);
                holder.accountDimLayer.setAlpha(1f);
            } else {
                holder.accountDimLayer.setVisibility(View.GONE);
                holder.accountCard.setBackground(ContextCompat.getDrawable(WalletDeriveActivity.this, R.drawable.box_accout_unselected));
                holder.accountState.setText("");
            }

            holder.accountCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (derive.status == 2) {
                        return;
                    }
                    derive.selected = !derive.selected;
                    if (derive.selected) {
                        holder.accountCard.setBackground(ContextCompat.getDrawable(WalletDeriveActivity.this, R.drawable.box_round_seleted_white));
                    } else {
                        holder.accountCard.setBackground(ContextCompat.getDrawable(WalletDeriveActivity.this, R.drawable.box_accout_unselected));
                    }
                    onUpdateCnt();
                }
            });

            loadBalance(holder, derive);
        }


        private void loadBalance(AccountHolder holder, Derive derive) {
            if (derive.coin != null) {
                WDp.showCoinDp(WalletDeriveActivity.this, getBaseDao(), derive.coin, holder.accountDenom, holder.accountAvailable, derive.baseChain);
                return;
            }

            if (isGRPC(derive.baseChain)) {
                new Thread(() -> {
                    new BalanceGrpcTask(getBaseApplication(), new TaskListener() {
                        @Override
                        public void onTaskResponse(TaskResult result) {
                            ArrayList<CoinOuterClass.Coin> balances = (ArrayList<CoinOuterClass.Coin>) result.resultData;
                            if (balances != null && balances.size() > 0) {
                                for (CoinOuterClass.Coin coin : balances) {
                                    if (coin.getDenom().equalsIgnoreCase(WDp.mainDenom(derive.baseChain))) {
                                        derive.coin = new Coin(coin.getDenom(), coin.getAmount());
                                        runOnUiThread(() -> {
                                            WDp.showCoinDp(WalletDeriveActivity.this, getBaseDao(), derive.coin, holder.accountDenom, holder.accountAvailable, derive.baseChain);
                                        });
                                        return;
                                    }
                                }
                            }

                            derive.coin = new Coin(WDp.mainDenom(derive.baseChain), "0");
                            runOnUiThread(() -> {
                                WDp.showCoinDp(WalletDeriveActivity.this, getBaseDao(), derive.coin, holder.accountDenom, holder.accountAvailable, derive.baseChain);
                            });
                        }
                    }, derive.baseChain, derive.dpAddress).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                }).start();

            } else {
                if (derive.baseChain.equals(BNB_MAIN)) {
                    new Thread(() -> {
                        ApiClient.getBnbChain(getBaseContext()).getAccountInfo(derive.dpAddress).enqueue(new Callback<ResBnbAccountInfo>() {
                            @Override
                            public void onResponse(Call<ResBnbAccountInfo> call, Response<ResBnbAccountInfo> response) {
                                if (response.isSuccessful() && response.body() != null && response.body().balances != null) {
                                    for (ResBnbAccountInfo.BnbBalance balance : response.body().balances) {
                                        if (balance.symbol.equalsIgnoreCase(WDp.mainDenom(derive.baseChain))) {
                                            derive.coin = new Coin(balance.symbol, balance.free);
                                            runOnUiThread(() -> {
                                                WDp.showCoinDp(WalletDeriveActivity.this, getBaseDao(), derive.coin, holder.accountDenom, holder.accountAvailable, derive.baseChain);
                                            });
                                            return;
                                        }
                                    }
                                }

                                derive.coin = new Coin(WDp.mainDenom(derive.baseChain), "0");
                                runOnUiThread(() -> {
                                    WDp.showCoinDp(WalletDeriveActivity.this, getBaseDao(), derive.coin, holder.accountDenom, holder.accountAvailable, derive.baseChain);
                                });
                            }
                            @Override
                            public void onFailure(Call<ResBnbAccountInfo> call, Throwable t) {
                            }
                        });
                    }).start();

                } else if (derive.baseChain.equals(OKEX_MAIN)) {
                    new Thread(() -> {
                        ApiClient.getOkexChain(getBaseContext()).getAccountBalance(derive.dpAddress).enqueue(new Callback<ResOkAccountToken>() {
                            @Override
                            public void onResponse(Call<ResOkAccountToken> call, Response<ResOkAccountToken> response) {
                                if (response.isSuccessful() && response.body() != null && response.body().data != null && response.body().data.currencies != null) {
                                    for (ResOkAccountToken.OkCurrency balance : response.body().data.currencies) {
                                        if (balance.symbol.equals(WDp.mainDenom(derive.baseChain))) {
                                            derive.coin = new Coin(balance.symbol, balance.available);
                                            runOnUiThread(() -> {
                                                WDp.showCoinDp(WalletDeriveActivity.this, getBaseDao(), derive.coin, holder.accountDenom, holder.accountAvailable, derive.baseChain);
                                            });
                                            return;
                                        }
                                    }
                                }

                                derive.coin = new Coin(WDp.mainDenom(derive.baseChain), "0");
                                runOnUiThread(() -> {
                                    WDp.showCoinDp(WalletDeriveActivity.this, getBaseDao(), derive.coin, holder.accountDenom, holder.accountAvailable, derive.baseChain);
                                });
                            }
                            @Override
                            public void onFailure(Call<ResOkAccountToken> call, Throwable t) {
                            }
                        });
                    }).start();
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

    public static int hdPathCount(BaseChain baseChain) {
        switch (baseChain) {
            case KAVA_MAIN:
            case LUM_MAIN:
            case SECRET_MAIN:
                return 2;
            case OKEX_MAIN:
                return 3;
            case FETCHAI_MAIN:
                return 4;
            default:
                return 1;
        }
    }

    public void onSaveAccount() {
        onShowWaitDialog();
        for (Derive derive : mDerives) {
            if (derive.selected) {
                if (derive.status == 1) {
                    final Account account = getBaseDao().onSelectExistAccount(derive.dpAddress, derive.baseChain);
                    new OverrideAccountTask(getBaseApplication(), account, this, mWords, derive).execute(mEntropy);
                } else {
                    new GenerateAccountTask(getBaseApplication(), this, mWords, derive).execute(mEntropy);
                }
            }
        }
    }

    @SuppressLint("NewApi")
    @Override
    public void onTaskResponse(TaskResult result) {
        if (isFinishing()) return;
        if (result.taskType == BaseConstant.TASK_INIT_ACCOUNT) {
            if (result.isSuccess) {
                Derive initDerive = mDerives.stream().filter(derive -> derive.selected).findFirst().get();
                Account initAccount = getBaseDao().onSelectExistAccount(initDerive.dpAddress, initDerive.baseChain);
                getBaseDao().setLastUser(initAccount.id);
                getBaseDao().setLastChain(initDerive.baseChain.getChain());
                onStartMainActivity(0);
            }

        } else if (result.taskType == BaseConstant.TASK_OVERRIDE_ACCOUNT) {
            if (result.isSuccess) {
                onStartMainActivity(0);
            }
        }
    }
}
