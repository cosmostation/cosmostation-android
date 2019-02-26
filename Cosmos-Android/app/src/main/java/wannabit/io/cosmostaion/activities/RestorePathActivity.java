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
import android.widget.TextView;
import android.widget.Toast;

import org.bitcoinj.crypto.DeterministicKey;
import org.bitcoinj.crypto.HDKeyDerivation;

import java.math.BigDecimal;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseActivity;
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
import wannabit.io.cosmostaion.utils.WLog;
import wannabit.io.cosmostaion.utils.WUtil;

public class RestorePathActivity extends BaseActivity implements TaskListener {


    private String              mHdSeed;
    private String              mEntorpy;
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
        mEntorpy =  getIntent().getStringExtra("entorpy");
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
//        new GenerateAccountTask(getBaseApplication(), this).execute(mChain, ""+path, mHdSeed, ""+mWordSize);
        new GenerateAccountTask(getBaseApplication(), this).execute(mChain, ""+path, mEntorpy, ""+mWordSize);
    }

    private void onOverrideAccount(Account account, int path) {
//        new OverrideAccountTask(getBaseApplication(), this, account).execute(""+path, mHdSeed, ""+mWordSize);
        new OverrideAccountTask(getBaseApplication(), this, account).execute(""+path, mEntorpy, ""+mWordSize);
    }

    @Override
    public void onTaskResponse(TaskResult result) {
        if(isFinishing()) return;
        if (result.taskType == BaseConstant.TASK_INIT_ACCOUNT) {
            if(result.isSuccess) {
//                if(getBaseDao().onSelectAccounts().size() > 1) {
//                    onStartListActivity();
//                } else {
                    onStartMainActivity();
//                }
            } else {
                WLog.w("CREATE ACCOUNT with new mnemonic error : " + result.errorCode);
            }

        } else if (result.taskType == BaseConstant.TASK_OVERRIDE_ACCOUNT) {
            if(result.isSuccess) {
//                if(getBaseDao().onSelectAccounts().size() > 1) {
//                    onStartListActivity();
//                } else {
                    onStartMainActivity();
//                }
            } else {
                WLog.w("OVERRIDE with new mnemonic error : " + result.errorCode);
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
            String address = WKey.getDpAddressWithPath(mMasterKey, position);
            holder.new_path.setText(BaseConstant.KEY_PATH + position);
            holder.new_address.setText(address);
            final Account temp = getBaseDao().onSelectExistAccount(address, mChain);
            if(temp == null) {
                holder.new_state.setText("ready");
                holder.new_state.setTextColor(getResources().getColor(R.color.colorWhite));
                holder.card_new_wallet.setCardBackgroundColor(getResources().getColor(R.color.colorTransBg2));
            } else  {
                if(temp.hasPrivateKey) {
                    holder.new_state.setText("imported");
                    holder.new_state.setTextColor(getResources().getColor(R.color.colorGray1));
                    holder.card_new_wallet.setCardBackgroundColor(getResources().getColor(R.color.colorTransBg));
                } else {
                    holder.new_state.setText("override");
                    holder.new_state.setTextColor(getResources().getColor(R.color.colorWhite));
                    holder.card_new_wallet.setCardBackgroundColor(getResources().getColor(R.color.colorTransBg2));
                }
            }

            holder.atom_title.setText(WDp.DpAtom(getBaseContext(), mChain));
            holder.photon_title.setText(WDp.DpPoton(getBaseContext(), mChain));

            holder.card_new_wallet.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(holder.new_state.getText().toString().equals("ready")) {
                        onGenAccount(position);
                    } else if (holder.new_state.getText().toString().equals("imported")) {
                        Toast.makeText(getBaseContext(), getString(R.string.str_already_imported_key), Toast.LENGTH_SHORT).show();

                    } else {
                        onOverrideAccount(temp, position);
                    }
                }
            });

            ApiClient.getWannabitChain(getBaseContext()).getAccountInfo(address).enqueue(new Callback<ResLcdAccountInfo>() {
                @Override
                public void onResponse(Call<ResLcdAccountInfo> call, Response<ResLcdAccountInfo> response) {
                    if(response.isSuccessful() && response.body() != null) {
                        ArrayList<Balance> balance = WUtil.getBalancesFromLcd(-1, response.body());
                        if(balance != null && balance.size() > 0 && balance.get(0) != null)
                            holder.atom_amount.setText(WDp.getDpAmount(getBaseContext(), balance.get(0).balance, 6));
                        if(balance != null && balance.size() > 1 && balance.get(1) != null)
                            holder.photon_amount.setText(WDp.getDpAmount(getBaseContext(), balance.get(1).balance, 6));
                    } else {
                        holder.atom_amount.setText("0");
                        holder.photon_amount.setText("0");
                    }
                }
                @Override
                public void onFailure(Call<ResLcdAccountInfo> call, Throwable t) {
                    holder.atom_amount.setText("0");
                    holder.photon_amount.setText("0");
                }
            });


        }

        @Override
        public int getItemCount() {
            return 5;
        }

        public class NewWalletHolder extends RecyclerView.ViewHolder {
            CardView card_new_wallet;
            TextView new_path, new_state, new_address, atom_title, atom_amount, photon_title, photon_amount;

            public NewWalletHolder(View v) {
                super(v);
                card_new_wallet = itemView.findViewById(R.id.card_new_wallet);
                new_path        = itemView.findViewById(R.id.new_path);
                new_state       = itemView.findViewById(R.id.new_state);
                new_address     = itemView.findViewById(R.id.new_address);
                atom_title      = itemView.findViewById(R.id.atom_title);
                atom_amount     = itemView.findViewById(R.id.atom_amount);
                photon_title    = itemView.findViewById(R.id.photon_title);
                photon_amount   = itemView.findViewById(R.id.photon_amount);
            }
        }
    }
}
