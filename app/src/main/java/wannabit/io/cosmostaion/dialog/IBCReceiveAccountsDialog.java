package wannabit.io.cosmostaion.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.chains.ChainConfig;
import wannabit.io.cosmostaion.base.chains.ChainFactory;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.utils.WDp;

public class IBCReceiveAccountsDialog extends DialogFragment {

    private ConstraintLayout mDialogLayout;
    private RecyclerView mRecyclerView;
    private TextView mDialogTitle;
    private AccountListAdapter mAccountListAdapter;

    private ArrayList<Account> mAccounts = new ArrayList<>();

    public static IBCReceiveAccountsDialog newInstance(Bundle bundle) {
        IBCReceiveAccountsDialog frag = new IBCReceiveAccountsDialog();
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
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_template_recycler, null);
        mDialogLayout = view.findViewById(R.id.dialog_layout);
        mDialogTitle = view.findViewById(R.id.dialog_title);
        mRecyclerView = view.findViewById(R.id.recycler);

        mAccounts = (ArrayList<Account>) getArguments().getSerializable("accounts");
        mDialogLayout.setBackgroundResource(R.drawable.layout_trans_with_border);
        mDialogTitle.setText(R.string.str_select_account);
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
            return new AccountListAdapter.AccountHolder(getLayoutInflater().inflate(R.layout.item_dialog_account, viewGroup, false));
        }

        @Override
        public void onBindViewHolder(@NonNull AccountListAdapter.AccountHolder holder, int position) {
            onBindReceiveAccountItemViewHolder(holder, position);
        }

        public void onBindReceiveAccountItemViewHolder(AccountHolder holder, int position) {
            final Account account = mAccounts.get(position);
            final BaseChain baseChain = BaseChain.getChain(account.baseChain);
            final ChainConfig chainConfig = ChainFactory.getChain(baseChain);
            holder.accountAddress.setText(account.address);

            if (TextUtils.isEmpty(account.nickName))
                holder.accountName.setText(getString(R.string.str_my_wallet) + account.id);
            else holder.accountName.setText(account.nickName);

            if (account.hasPrivateKey) {
                holder.accountKeyState.setImageResource(R.drawable.key_off);
                holder.accountKeyState.setColorFilter(ContextCompat.getColor(getSActivity(), chainConfig.chainColor()), android.graphics.PorterDuff.Mode.SRC_IN);
            } else {
                holder.accountKeyState.setImageResource(R.drawable.watchmode);
                holder.accountKeyState.setColorFilter(null);
            }
            WDp.setDpSymbol(getSActivity(), getSActivity().getBaseDao(), chainConfig, chainConfig.mainDenom(), holder.accountDenom);
            holder.accountAvailable.setText(account.getLastTotal(getSActivity(), baseChain));
            holder.rootLayer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("position", position);
                    getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, resultIntent);
                    getDialog().dismiss();
                }
            });
        }

        @Override
        public int getItemCount() {
            return mAccounts.size();
        }


        public class AccountHolder extends RecyclerView.ViewHolder {
            RelativeLayout rootLayer;
            ImageView accountKeyState;
            TextView accountName, accountAddress, accountAvailable, accountDenom;

            public AccountHolder(@NonNull View itemView) {
                super(itemView);
                rootLayer = itemView.findViewById(R.id.rootLayer);
                accountKeyState = itemView.findViewById(R.id.accountKeyState);
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
