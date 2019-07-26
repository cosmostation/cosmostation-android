package wannabit.io.cosmostaion.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.bitcoinj.crypto.DeterministicKey;
import org.bitcoinj.crypto.HDKeyDerivation;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.dao.Balance;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.res.ResLcdAccountInfo;
import wannabit.io.cosmostaion.task.UserTask.GenerateAccountTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.task.UserTask.OverrideAccountTask;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WKey;
import wannabit.io.cosmostaion.utils.WUtil;

public class RestorePathActivity extends BaseActivity implements TaskListener {


    private String              mHdSeed;
    private String              mEntropy;
    private int                 mWordSize;
    private String              mChain;
    private DeterministicKey    mMasterKey;

    private Toolbar                 mToolbar;
    private RecyclerView            mRecyclerView;
    private NewWalletAdapter        mNewWalletAdapter;

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
        mChain = getIntent().getStringExtra("chain");
        mWordSize = getIntent().getIntExtra("size", 24);
        mMasterKey = HDKeyDerivation.createMasterPrivateKey(WUtil.HexStringToByteArray(mHdSeed));

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
        new GenerateAccountTask(getBaseApplication(), this).execute(mChain, ""+path, mEntropy, ""+mWordSize);
    }

    private void onOverrideAccount(Account account, int path) {
        new OverrideAccountTask(getBaseApplication(), this, account).execute(""+path, mEntropy, ""+mWordSize);
    }

    @Override
    public void onTaskResponse(TaskResult result) {
        if(isFinishing()) return;
        if (result.taskType == BaseConstant.TASK_INIT_ACCOUNT) {
            if(result.isSuccess) {
                onStartMainActivity();
            }

        } else if (result.taskType == BaseConstant.TASK_OVERRIDE_ACCOUNT) {
            if(result.isSuccess) {
                onStartMainActivity();
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
        public void onBindViewHolder(@NonNull final NewWalletHolder holder, final int position) {
            String address = WKey.getDpAddressWithPath(mMasterKey, mChain, position);
            holder.newPath.setText(BaseConstant.KEY_PATH + position);
            holder.newAddress.setText(address);
            final Account temp = getBaseDao().onSelectExistAccount(address, mChain);
            if(temp == null) {
                holder.newState.setText(getString(R.string.str_ready));
                holder.newState.setTextColor(getResources().getColor(R.color.colorWhite));
                holder.cardNewWallet.setCardBackgroundColor(getResources().getColor(R.color.colorTransBg2));
            } else  {
                if(temp.hasPrivateKey) {
                    holder.newState.setText(getString(R.string.str_imported));
                    holder.newState.setTextColor(getResources().getColor(R.color.colorGray1));
                    holder.cardNewWallet.setCardBackgroundColor(getResources().getColor(R.color.colorTransBg));
                } else {
                    holder.newState.setText(getString(R.string.str_override));
                    holder.newState.setTextColor(getResources().getColor(R.color.colorWhite));
                    holder.cardNewWallet.setCardBackgroundColor(getResources().getColor(R.color.colorTransBg2));
                }
            }
            holder.cardNewWallet.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(holder.newState.getText().toString().equals(getString(R.string.str_ready))) {
                        if(getBaseDao().onSelectAccounts().size() >= 5) {
                            Toast.makeText(getBaseContext(), getString(R.string.error_max_account_over), Toast.LENGTH_SHORT).show();
                            return;
                        }
                        onGenAccount(position);
                    } else if (holder.newState.getText().toString().equals(getString(R.string.str_imported))) {
                        Toast.makeText(getBaseContext(), getString(R.string.str_already_imported_key), Toast.LENGTH_SHORT).show();

                    } else {
                        onOverrideAccount(temp, position);
                    }
                }
            });

            if (mChain.equals(BaseChain.COSMOS_MAIN.getChain())) {
                holder.atomLayer.setVisibility(View.VISIBLE);
                ApiClient.getCosmosChain(getBaseContext()).getAccountInfo(address).enqueue(new Callback<ResLcdAccountInfo>() {
                    @Override
                    public void onResponse(Call<ResLcdAccountInfo> call, Response<ResLcdAccountInfo> response) {
                        if(response.isSuccessful() && response.body() != null && response.body().value.coins != null) {
                            ArrayList<Balance> balance = WUtil.getBalancesFromLcd(-1, response.body());
                            if(balance != null && balance.size() > 0 && balance.get(0) != null)
                                holder.atomAmount.setText(WDp.getDpAmount(getBaseContext(), balance.get(0).balance, 6, BaseChain.getChain(mChain)));
                        } else {
                            holder.atomAmount.setText("0");
                        }
                    }
                    @Override
                    public void onFailure(Call<ResLcdAccountInfo> call, Throwable t) {
                        holder.atomAmount.setText("0");
                    }
                });

            } else if (mChain.equals(BaseChain.IRIS_MAIN.getChain())) {
                holder.irisLayer.setVisibility(View.VISIBLE);
                ApiClient.getIrisChain(getBaseContext()).getBankInfo(address).enqueue(new Callback<ResLcdAccountInfo>() {
                    @Override
                    public void onResponse(Call<ResLcdAccountInfo> call, Response<ResLcdAccountInfo> response) {
                        if(response.isSuccessful() && response.body() != null && response.body().value.coins != null) {
                            ArrayList<Balance> balance = WUtil.getBalancesFromLcd(-1, response.body());
                            if(balance != null && balance.size() > 0 && balance.get(0) != null)
                                holder.irisAmount.setText(WDp.getDpAmount(getBaseContext(), balance.get(0).balance, 6, BaseChain.getChain(mChain)));
                        } else {
                            holder.irisAmount.setText("0");
                        }
                    }
                    @Override
                    public void onFailure(Call<ResLcdAccountInfo> call, Throwable t) {
                        holder.irisAmount.setText("0");
                    }
                });
            }





        }

        @Override
        public int getItemCount() {
            return 5;
        }

        public class NewWalletHolder extends RecyclerView.ViewHolder {
            CardView cardNewWallet;
            RelativeLayout atomLayer, photonLayer, irisLayer;
            TextView newPath, newState, newAddress, atomAmount, photonAmount, irisAmount;

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
            }
        }
    }
}
