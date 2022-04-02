package wannabit.io.cosmostaion.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.res.ResAirdropClaimCheck;
import wannabit.io.cosmostaion.utils.WLog;
import wannabit.io.cosmostaion.utils.WUtil;

public class Dialog_Link_Accounts extends DialogFragment {

    private RecyclerView mRecyclerView;
    private AccountListAdapter mAccountListAdapter;

    private ArrayList<Account> mAccounts = new ArrayList<>();
    private ResAirdropClaimCheck mCheckClaim;

    public static Dialog_Link_Accounts newInstance(Bundle bundle) {
        Dialog_Link_Accounts frag = new Dialog_Link_Accounts();
        frag.setArguments(bundle);
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(0));
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_link_account, null);
        mRecyclerView = view.findViewById(R.id.recycler);
        mAccounts = getSActivity().getBaseDao().onSelectAllAccountsByChainWithKey(BaseChain.getChain(getArguments().getString("chainName")));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);
        mAccountListAdapter = new AccountListAdapter();
        mRecyclerView.setAdapter(mAccountListAdapter);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view);
        return builder.create();
    }


    private class AccountListAdapter extends RecyclerView.Adapter<AccountListAdapter.AccountHolder> {

        @NonNull
        @Override
        public AccountListAdapter.AccountHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new AccountListAdapter.AccountHolder(getLayoutInflater().inflate(R.layout.item_dialog_link_account, viewGroup, false));
        }

        @Override
        public void onBindViewHolder(@NonNull AccountListAdapter.AccountHolder holder, int position) {
            final Account account = mAccounts.get(position);
            holder.accountAddress.setText(account.address);
            holder.accountName.setText(account.getAccountTitle(getContext()));

            ApiClient.getAirDrop(getContext()).getClaimable(account.address).enqueue(new Callback<ResAirdropClaimCheck>() {
                @Override
                public void onResponse(Call<ResAirdropClaimCheck> call, Response<ResAirdropClaimCheck> response) {
                    if (response.isSuccessful()) {
                        mCheckClaim = new ResAirdropClaimCheck(response.body());
                        holder.accountAvailable.setText(mCheckClaim.getUnclaimedAirdropAmount().toString());
                    }
                }

                @Override
                public void onFailure(Call<ResAirdropClaimCheck> call, Throwable t) {
                    WLog.w("error : " + t.getMessage());
                }
            });

            holder.rootLayer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("position", position);
                    getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, resultIntent);
                    dismiss();
                }
            });

        }

        @Override
        public int getItemCount() {
            return mAccounts.size();
        }


        public class AccountHolder extends RecyclerView.ViewHolder {
            RelativeLayout rootLayer;
            TextView accountName, accountAddress, accountAvailable, accountDenom;

            public AccountHolder(@NonNull View itemView) {
                super(itemView);
                rootLayer = itemView.findViewById(R.id.rootLayer);
                accountName = itemView.findViewById(R.id.accountName);
                accountAddress = itemView.findViewById(R.id.accountAddress);
                accountAvailable = itemView.findViewById(R.id.accountAvailable);
                accountDenom = itemView.findViewById(R.id.accountDenom);
            }
        }
    }

    private BaseActivity getSActivity() {
        return (BaseActivity) getActivity();
    }
}
