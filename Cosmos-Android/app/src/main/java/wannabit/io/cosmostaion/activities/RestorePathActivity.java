package wannabit.io.cosmostaion.activities;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.math.BigDecimal;
import java.util.ArrayList;

import cosmos.base.v1beta1.CoinOuterClass;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.dao.Balance;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.res.ResBnbAccountInfo;
import wannabit.io.cosmostaion.network.res.ResLcdAccountInfo;
import wannabit.io.cosmostaion.network.res.ResLcdKavaAccountInfo;
import wannabit.io.cosmostaion.network.res.ResLcdVestingAccountInfo;
import wannabit.io.cosmostaion.network.res.ResOkAccountToken;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.task.UserTask.GenerateAccountTask;
import wannabit.io.cosmostaion.task.UserTask.OverrideAccountTask;
import wannabit.io.cosmostaion.task.gRpcTask.BalanceGrpcTask;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WKey;
import wannabit.io.cosmostaion.utils.WLog;
import wannabit.io.cosmostaion.utils.WUtil;

import static wannabit.io.cosmostaion.base.BaseChain.AKASH_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.BAND_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.BNB_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.BNB_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.CERTIK_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.CERTIK_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.COSMOS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.COSMOS_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.FETCHAI_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.IOV_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.IOV_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.IRIS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.IRIS_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.KAVA_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.KAVA_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.OKEX_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.OK_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.SECRET_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.SENTINEL_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.SIF_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.isGRPC;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_CERTIK;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_IOV;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_IOV_TEST;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_KAVA;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_OK;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_SECRET;

public class RestorePathActivity extends BaseActivity implements TaskListener {

    private String              mHdSeed;
    private String              mEntropy;
    private int                 mWordSize;
    private BaseChain           mChain;

    private Toolbar                 mToolbar;
    private RecyclerView            mRecyclerView;
    private NewWalletAdapter        mNewWalletAdapter;

    private boolean                 mIsNewBip44;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restore_path);

        mToolbar           = findViewById(R.id.tool_bar);
        mRecyclerView       = findViewById(R.id.recycler);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);
        mNewWalletAdapter = new NewWalletAdapter();
        mRecyclerView.setAdapter(mNewWalletAdapter);

        mHdSeed = getIntent().getStringExtra("HDseed");
        mEntropy =  getIntent().getStringExtra("entropy");
        mChain = BaseChain.getChain(getIntent().getStringExtra("chain"));
        mWordSize = getIntent().getIntExtra("size", 24);
        mIsNewBip44 = getIntent().getBooleanExtra("bip44", false);
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

    private void onGenAccount(int path) {
        onShowWaitDialog();
        new GenerateAccountTask(getBaseApplication(), mChain, this, mIsNewBip44).execute(""+path, mEntropy, ""+mWordSize);
    }

    private void onOverrideAccount(Account account, int path) {
        new OverrideAccountTask(getBaseApplication(), mChain, account, this, mIsNewBip44).execute(""+path, mEntropy, ""+mWordSize);
    }

    @Override
    public void onTaskResponse(TaskResult result) {
        if(isFinishing()) return;
        if (result.taskType == BaseConstant.TASK_INIT_ACCOUNT) {
            if(result.isSuccess) {
                onStartMainActivity(0);
            }

        } else if (result.taskType == BaseConstant.TASK_OVERRIDE_ACCOUNT) {
            if(result.isSuccess) {
                onStartMainActivity(0);
            }
        }
    }



    private class NewWalletAdapter extends RecyclerView.Adapter<NewWalletAdapter.NewWalletHolder> {

        @NonNull
        @Override
        public NewWalletHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new NewWalletHolder(getLayoutInflater().inflate(R.layout.item_new_wallet, viewGroup, false));
        }

        @Override
        public void onBindViewHolder(@NonNull final NewWalletHolder holder, @SuppressLint("RecyclerView") final int position) {
            String address = WKey.getDpAddressWithPath(mHdSeed, mChain, position, mIsNewBip44);
            holder.newPath.setText(WDp.getPath(mChain, position, mIsNewBip44));
            holder.newAddress.setText(address);
            final Account temp = getBaseDao().onSelectExistAccount(address, mChain);
            if (temp == null) {
                holder.newState.setText(getString(R.string.str_ready));
                holder.newState.setTextColor(getResources().getColor(R.color.colorWhite));
                holder.cardNewWallet.setCardBackgroundColor(WDp.getChainBgColor(getBaseContext(), mChain));
            } else  {
                if(temp.hasPrivateKey) {
                    holder.newState.setText(getString(R.string.str_imported));
                    holder.newState.setTextColor(getResources().getColor(R.color.colorGray1));
                    holder.cardNewWallet.setCardBackgroundColor(getResources().getColor(R.color.colorTransBg));
                } else {
                    holder.newState.setText(getString(R.string.str_override));
                    holder.newState.setTextColor(getResources().getColor(R.color.colorWhite));
                    holder.cardNewWallet.setCardBackgroundColor(WDp.getChainBgColor(getBaseContext(), mChain));
                }
            }
            holder.cardNewWallet.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(holder.newState.getText().toString().equals(getString(R.string.str_ready))) {
                        onGenAccount(position);
                    } else if (holder.newState.getText().toString().equals(getString(R.string.str_imported))) {
                        Toast.makeText(getBaseContext(), getString(R.string.str_already_imported_key), Toast.LENGTH_SHORT).show();

                    } else {
                        onOverrideAccount(temp, position);
                    }
                }
            });

            if (isGRPC(mChain)) {
                holder.coinLayer.setVisibility(View.VISIBLE);
                WDp.showCoinDp(getBaseContext(), WDp.mainDenom(mChain),"0", holder.coinDenom, holder.coinAmount, mChain);
                new BalanceGrpcTask(getBaseApplication(), new TaskListener() {
                    @Override
                    public void onTaskResponse(TaskResult result) {
                        WLog.w("result " + result.resultData);
                        ArrayList<CoinOuterClass.Coin> balances = (ArrayList<CoinOuterClass.Coin>)result.resultData;
                        if (balances != null && balances.size() > 0) {
                            for (CoinOuterClass.Coin balance: balances) {
                                if (balance.getDenom().equals(WDp.mainDenom(mChain))) {
                                    WDp.showCoinDp(getBaseContext(), balance.getDenom(), balance.getAmount(), holder.coinDenom, holder.coinAmount, mChain);
                                }
                            }
                        }
                    }
                }, mChain, address).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

            } else if (mChain.equals(BNB_MAIN)) {
                holder.bnbLayer.setVisibility(View.VISIBLE);
                holder.bnbAmount.setText(WDp.getDpAmount2(getBaseContext(), BigDecimal.ZERO, 0, 8));
                ApiClient.getBnbChain(getBaseContext()).getAccountInfo(address).enqueue(new Callback<ResBnbAccountInfo>() {
                    @Override
                    public void onResponse(Call<ResBnbAccountInfo> call, Response<ResBnbAccountInfo> response) {
                        if(response.isSuccessful() && response.body() != null && response.body().balances != null) {
                            for (ResBnbAccountInfo.BnbBalance balance:response.body().balances) {
                                if (balance.symbol.equals(BaseConstant.TOKEN_BNB)) {
                                    holder.bnbAmount.setText(WDp.getDpAmount2(getBaseContext(), new BigDecimal(balance.free), 0, 8));
                                    break;
                                }
                            }
                        }
                    }
                    @Override
                    public void onFailure(Call<ResBnbAccountInfo> call, Throwable t) { }
                });

            } else if (mChain.equals(KAVA_MAIN)) {
                holder.kavaLayer.setVisibility(View.VISIBLE);
                holder.kavaAmount.setText(WDp.getDpAmount2(getBaseContext(), BigDecimal.ZERO, 0, 6));
                ApiClient.getKavaChain(getBaseContext()).getAccountInfo(address).enqueue(new Callback<ResLcdKavaAccountInfo>() {
                    @Override
                    public void onResponse(Call<ResLcdKavaAccountInfo> call, Response<ResLcdKavaAccountInfo> response) {
                        ArrayList<Balance> balances = WUtil.getBalancesFromKavaLcd(-1, response.body());
                        holder.kavaAmount.setText(WDp.getDpAmount2(getBaseContext(), WDp.getAvailableCoin(balances, TOKEN_KAVA), 6, 6));
                    }

                    @Override
                    public void onFailure(Call<ResLcdKavaAccountInfo> call, Throwable t) { }
                });

            } else if (mChain.equals(IOV_MAIN)) {
                holder.iovLayer.setVisibility(View.VISIBLE);
                holder.iovAmount.setText(WDp.getDpAmount2(getBaseContext(), BigDecimal.ZERO, 6, 6));
                ApiClient.getIovChain(getBaseContext()).getAccountInfo(address).enqueue(new Callback<ResLcdAccountInfo>() {
                    @Override
                    public void onResponse(Call<ResLcdAccountInfo> call, Response<ResLcdAccountInfo> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            ArrayList<Balance> balance = WUtil.getBalancesFromLcd(-1, response.body());
                            if(balance != null && balance.size() > 0 && balance.get(0) != null) {
                                holder.iovAmount.setText(WDp.getDpAmount2(getBaseContext(), WDp.getAvailableCoin(balance, TOKEN_IOV), 6, 6));
                            }
                        }
                    }
                    @Override
                    public void onFailure(Call<ResLcdAccountInfo> call, Throwable t) {}
                });


            } else if (mChain.equals(BAND_MAIN)) {
                holder.bandLayer.setVisibility(View.VISIBLE);
                holder.bandAmount.setText(WDp.getDpAmount2(getBaseContext(), BigDecimal.ZERO, 6, 6));
                ApiClient.getBandChain(getBaseContext()).getAccountInfo(address).enqueue(new Callback<ResLcdAccountInfo>() {
                    @Override
                    public void onResponse(Call<ResLcdAccountInfo> call, Response<ResLcdAccountInfo> response) {
                        if(response.isSuccessful() && response.body() != null) {
                            ArrayList<Balance> balance = WUtil.getBalancesFromLcd(-1, response.body());
                            if(balance != null && balance.size() > 0 && balance.get(0) != null)
                                holder.bandAmount.setText(WDp.getDpAmount2(getBaseContext(), balance.get(0).balance, 6, 6));
                        }
                    }
                    @Override
                    public void onFailure(Call<ResLcdAccountInfo> call, Throwable t) { }
                });

            } else if (mChain.equals(CERTIK_MAIN)) {
                holder.certikLayer.setVisibility(View.VISIBLE);
                holder.certikAmount.setText(WDp.getDpAmount2(getBaseContext(), BigDecimal.ZERO, 6, 6));
                ApiClient.getCertikChain(getBaseContext()).getAccountInfo(address).enqueue(new Callback<ResLcdAccountInfo>() {
                    @Override
                    public void onResponse(Call<ResLcdAccountInfo> call, Response<ResLcdAccountInfo> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            ArrayList<Balance> balance = WUtil.getBalancesFromLcd(-1, response.body());
                            if(balance != null && balance.size() > 0 && balance.get(0) != null)
                                holder.certikAmount.setText(WDp.getDpAmount2(getBaseContext(), WDp.getAvailableCoin(balance, TOKEN_CERTIK), 6, 6));
                        }
                    }
                    @Override
                    public void onFailure(Call<ResLcdAccountInfo> call, Throwable t) { }
                });

            } else if (mChain.equals(BNB_TEST)) {
                holder.bnbLayer.setVisibility(View.VISIBLE);
                holder.bnbAmount.setText(WDp.getDpAmount2(getBaseContext(), BigDecimal.ZERO, 0, 8));
                ApiClient.getBnbTestChain(getBaseContext()).getAccountInfo(address).enqueue(new Callback<ResBnbAccountInfo>() {
                    @Override
                    public void onResponse(Call<ResBnbAccountInfo> call, Response<ResBnbAccountInfo> response) {
                        if(response.isSuccessful() && response.body() != null && response.body().balances != null) {
                            for (ResBnbAccountInfo.BnbBalance balance:response.body().balances) {
                                if (balance.symbol.equals(BaseConstant.TOKEN_BNB)) {
                                    holder.bnbAmount.setText(WDp.getDpAmount2(getBaseContext(), new BigDecimal(balance.free), 0, 8));
                                    break;
                                }
                            }
                        }
                    }
                    @Override
                    public void onFailure(Call<ResBnbAccountInfo> call, Throwable t) { }
                });

            } else if (mChain.equals(KAVA_TEST)) {
                holder.kavaLayer.setVisibility(View.VISIBLE);
                holder.kavaAmount.setText(WDp.getDpAmount2(getBaseContext(), BigDecimal.ZERO, 0, 6));
                ApiClient.getKavaTestChain(getBaseContext()).getAccountInfo(address).enqueue(new Callback<ResLcdKavaAccountInfo>() {
                    @Override
                    public void onResponse(Call<ResLcdKavaAccountInfo> call, Response<ResLcdKavaAccountInfo> response) {
                        ArrayList<Balance> balances = WUtil.getBalancesFromKavaLcd(-1, response.body());
                        holder.kavaAmount.setText(WDp.getDpAmount2(getBaseContext(), WDp.getAvailableCoin(balances, TOKEN_KAVA), 6, 6));

                    }

                    @Override
                    public void onFailure(Call<ResLcdKavaAccountInfo> call, Throwable t) { }
                });

            } else if (mChain.equals(IOV_TEST)) {
                holder.iovLayer.setVisibility(View.VISIBLE);
                holder.iovAmount.setText(WDp.getDpAmount2(getBaseContext(), BigDecimal.ZERO, 6, 6));
                ApiClient.getIovTestChain(getBaseContext()).getAccountInfo(address).enqueue(new Callback<ResLcdAccountInfo>() {
                    @Override
                    public void onResponse(Call<ResLcdAccountInfo> call, Response<ResLcdAccountInfo> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            ArrayList<Balance> balance = WUtil.getBalancesFromLcd(-1, response.body());
                            if(balance != null && balance.size() > 0 && balance.get(0) != null)
                                holder.iovAmount.setText(WDp.getDpAmount2(getBaseContext(), WDp.getAvailableCoin(balance, TOKEN_IOV_TEST), 6, 6));
                        }
                    }
                    @Override
                    public void onFailure(Call<ResLcdAccountInfo> call, Throwable t) { }
                });

            } else if (mChain.equals(OKEX_MAIN)) {
                holder.okLayer.setVisibility(View.VISIBLE);
                holder.okAmount.setText(WDp.getDpAmount2(getBaseContext(), BigDecimal.ZERO, 0, 18));
                ApiClient.getOkexChain(getBaseContext()).getAccountBalance(address).enqueue(new Callback<ResOkAccountToken>() {
                    @Override
                    public void onResponse(Call<ResOkAccountToken> call, Response<ResOkAccountToken> response) {
                        if(response.isSuccessful() && response.body() != null && response.body().data != null && response.body().data.currencies != null) {
                            for (ResOkAccountToken.OkCurrency balance:response.body().data.currencies) {
                                if (balance.symbol.equals(TOKEN_OK)) {
                                    holder.okAmount.setText(WDp.getDpAmount2(getBaseContext(), new BigDecimal(balance.available), 0, 18));
                                    break;
                                }
                            }
                        }
                    }
                    @Override
                    public void onFailure(Call<ResOkAccountToken> call, Throwable t) { }
                });

            } else if (mChain.equals(OK_TEST)) {
                holder.okLayer.setVisibility(View.VISIBLE);
                holder.okAmount.setText(WDp.getDpAmount2(getBaseContext(), BigDecimal.ZERO, 0, 18));
                ApiClient.getOkTestChain(getBaseContext()).getAccountBalance(address).enqueue(new Callback<ResOkAccountToken>() {
                    @Override
                    public void onResponse(Call<ResOkAccountToken> call, Response<ResOkAccountToken> response) {
                        if(response.isSuccessful() && response.body() != null && response.body().data != null && response.body().data.currencies != null) {
                            for (ResOkAccountToken.OkCurrency balance:response.body().data.currencies) {
                                if (balance.symbol.equals(TOKEN_OK)) {
                                    holder.okAmount.setText(WDp.getDpAmount2(getBaseContext(), new BigDecimal(balance.available), 0, 18));
                                    break;
                                }
                            }
                        }
                    }
                    @Override
                    public void onFailure(Call<ResOkAccountToken> call, Throwable t) { }
                });

            } else if (mChain.equals(CERTIK_TEST)) {
                holder.certikLayer.setVisibility(View.VISIBLE);
                holder.certikAmount.setText(WDp.getDpAmount2(getBaseContext(), BigDecimal.ZERO, 6, 6));
                ApiClient.getCertikTestChain(getBaseContext()).getAccountInfo(address).enqueue(new Callback<ResLcdAccountInfo>() {
                    @Override
                    public void onResponse(Call<ResLcdAccountInfo> call, Response<ResLcdAccountInfo> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            ArrayList<Balance> balance = WUtil.getBalancesFromLcd(-1, response.body());
                            if(balance != null && balance.size() > 0 && balance.get(0) != null)
                                holder.certikAmount.setText(WDp.getDpAmount2(getBaseContext(), WDp.getAvailableCoin(balance, TOKEN_CERTIK), 6, 6));
                        }
                    }
                    @Override
                    public void onFailure(Call<ResLcdAccountInfo> call, Throwable t) { }
                });

            } else if (mChain.equals(SECRET_MAIN)) {
                holder.secretLayer.setVisibility(View.VISIBLE);
                holder.secretAmount.setText(WDp.getDpAmount2(getBaseContext(), BigDecimal.ZERO, 6, 6));
                ApiClient.getSecretChain(getBaseContext()).getAccountInfo(address).enqueue(new Callback<ResLcdAccountInfo>() {
                    @Override
                    public void onResponse(Call<ResLcdAccountInfo> call, Response<ResLcdAccountInfo> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            ArrayList<Balance> balance = WUtil.getBalancesFromLcd(-1, response.body());
                            if (balance != null && balance.size() > 0 && balance.get(0) != null)
                                holder.secretAmount.setText(WDp.getDpAmount2(getBaseContext(), WDp.getAvailableCoin(balance, TOKEN_SECRET), 6, 6));
                        }
                    }
                    @Override
                    public void onFailure(Call<ResLcdAccountInfo> call, Throwable t) { }
                });

            } else if (mChain.equals(SENTINEL_MAIN)) {
                holder.coinLayer.setVisibility(View.VISIBLE);
                WDp.showCoinDp(getBaseContext(), WDp.mainDenom(mChain),"0", holder.coinDenom, holder.coinAmount, mChain);
                ApiClient.getSentinelChain(getBaseContext()).getAccountInfo(address).enqueue(new Callback<ResLcdVestingAccountInfo>() {
                    @Override
                    public void onResponse(Call<ResLcdVestingAccountInfo> call, Response<ResLcdVestingAccountInfo> response) {
                        if (response.isSuccessful() && response.body() != null && response.body().result != null && response.body().result.value != null && response.body().result.value.coins != null) {
                            ArrayList<Coin> coins = response.body().result.value.coins ;
                            for (Coin coin: coins) {
                                if (coin.denom.equals(WDp.mainDenom(mChain))) {
                                    WDp.showCoinDp(getBaseContext(), coin, holder.coinDenom, holder.coinAmount, mChain);
                                }
                            }
                        }
                    }
                    @Override
                    public void onFailure(Call<ResLcdVestingAccountInfo> call, Throwable t) { }
                });

            } else if (mChain.equals(FETCHAI_MAIN)) {
                holder.coinLayer.setVisibility(View.VISIBLE);
                WDp.showCoinDp(getBaseContext(), WDp.mainDenom(mChain),"0", holder.coinDenom, holder.coinAmount, mChain);
                ApiClient.getFetchChain(getBaseContext()).getAccountInfo(address).enqueue(new Callback<ResLcdAccountInfo>() {
                    @Override
                    public void onResponse(Call<ResLcdAccountInfo> call, Response<ResLcdAccountInfo> response) {
                        if (response.isSuccessful() && response.body() != null && response.body().result != null && response.body().result.value != null && response.body().result.value.coins != null) {
                            ArrayList<Coin> coins = response.body().result.value.coins ;
                            for (Coin coin: coins) {
                                if (coin.denom.equals(WDp.mainDenom(mChain))) {
                                    WDp.showCoinDp(getBaseContext(), coin, holder.coinDenom, holder.coinAmount, mChain);
                                }
                            }
                        }
                    }
                    @Override
                    public void onFailure(Call<ResLcdAccountInfo> call, Throwable t) { }
                });

            } else if (mChain.equals(SIF_MAIN)) {
                holder.coinLayer.setVisibility(View.VISIBLE);
                WDp.showCoinDp(getBaseContext(), WDp.mainDenom(mChain),"0", holder.coinDenom, holder.coinAmount, mChain);
                ApiClient.getSifChain(getBaseContext()).getAccountInfo(address).enqueue(new Callback<ResLcdAccountInfo>() {
                    @Override
                    public void onResponse(Call<ResLcdAccountInfo> call, Response<ResLcdAccountInfo> response) {
                        if (response.isSuccessful() && response.body() != null && response.body().result != null && response.body().result.value != null && response.body().result.value.coins != null) {
                            ArrayList<Coin> coins = response.body().result.value.coins ;
                            for (Coin coin: coins) {
                                if (coin.denom.equals(WDp.mainDenom(mChain))) {
                                    WDp.showCoinDp(getBaseContext(), coin, holder.coinDenom, holder.coinAmount, mChain);
                                }
                            }
                        }
                    }
                    @Override
                    public void onFailure(Call<ResLcdAccountInfo> call, Throwable t) { }
                });
            }

        }

        @Override
        public int getItemCount() {
            return 5;
        }

        public class NewWalletHolder extends RecyclerView.ViewHolder {
            CardView cardNewWallet;
            RelativeLayout atomLayer, photonLayer, irisLayer, bnbLayer, kavaLayer, iovLayer, bandLayer, okLayer, certikLayer, akashLayer, secretLayer;
            TextView newPath, newState, newAddress, atomAmount, photonAmount, irisAmount, bnbAmount, kavaAmount, iovAmount, bandAmount, okAmount, certikAmount, akashAmount, secretAmount;
            RelativeLayout coinLayer;
            TextView coinDenom, coinAmount;

            public NewWalletHolder(View v) {
                super(v);
                cardNewWallet       = itemView.findViewById(R.id.card_new_wallet);
                newPath             = itemView.findViewById(R.id.new_path);
                newState            = itemView.findViewById(R.id.new_state);
                newAddress          = itemView.findViewById(R.id.new_address);
                atomLayer           = itemView.findViewById(R.id.atom_layer);
                atomAmount          = itemView.findViewById(R.id.atom_amount);
                photonLayer         = itemView.findViewById(R.id.photon_layer);
                photonAmount        = itemView.findViewById(R.id.photon_amount);
                irisLayer           = itemView.findViewById(R.id.iris_layer);
                irisAmount          = itemView.findViewById(R.id.iris_amount);
                bnbLayer            = itemView.findViewById(R.id.bnb_layer);
                bnbAmount           = itemView.findViewById(R.id.bnb_amount);
                kavaLayer           = itemView.findViewById(R.id.kava_layer);
                kavaAmount          = itemView.findViewById(R.id.kava_amount);
                iovLayer            = itemView.findViewById(R.id.iov_layer);
                iovAmount           = itemView.findViewById(R.id.iov_amount);
                bandLayer           = itemView.findViewById(R.id.band_layer);
                bandAmount          = itemView.findViewById(R.id.band_amount);
                okLayer             = itemView.findViewById(R.id.ok_layer);
                okAmount            = itemView.findViewById(R.id.ok_amount);
                certikLayer         = itemView.findViewById(R.id.certik_layer);
                certikAmount        = itemView.findViewById(R.id.certik_amount);
                akashLayer          = itemView.findViewById(R.id.akash_layer);
                akashAmount         = itemView.findViewById(R.id.akash_amount);
                secretLayer         = itemView.findViewById(R.id.secret_layer);
                secretAmount        = itemView.findViewById(R.id.secret_amount);
                coinLayer         = itemView.findViewById(R.id.coin_layer);
                coinDenom         = itemView.findViewById(R.id.coin_denom);
                coinAmount        = itemView.findViewById(R.id.coin_amount);
            }
        }
    }
}
