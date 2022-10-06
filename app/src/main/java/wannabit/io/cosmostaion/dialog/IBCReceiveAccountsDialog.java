package wannabit.io.cosmostaion.dialog;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.base.chains.ChainConfig;
import wannabit.io.cosmostaion.base.chains.ChainFactory;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.utils.WDp;

public class IBCReceiveAccountsDialog extends DialogFragment {

    public final static String IBC_RECEIVE_ACCOUNTS_BUNDLE_KEY = "ibcReceiveAccounts";
    public final static String ACCOUNTS_BUNDLE_KEY = "accounts";

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
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawableResource(R.color.colorTrans);
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_template_recycler, null);
        mDialogTitle = view.findViewById(R.id.dialog_title);
        mRecyclerView = view.findViewById(R.id.recycler);

        mAccounts = (ArrayList<Account>) getArguments().getSerializable(IBCReceiveAccountsDialog.ACCOUNTS_BUNDLE_KEY);
        mDialogTitle.setText(R.string.str_select_account);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);
        mAccountListAdapter = new AccountListAdapter();
        mRecyclerView.setAdapter(mAccountListAdapter);
        return view;
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
            holder.rootLayer.setOnClickListener(v -> {
                Bundle result = new Bundle();
                result.putInt(BaseConstant.POSITION, position);
                getParentFragmentManager().setFragmentResult(IBCReceiveAccountsDialog.IBC_RECEIVE_ACCOUNTS_BUNDLE_KEY, result);
                dismiss();
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
