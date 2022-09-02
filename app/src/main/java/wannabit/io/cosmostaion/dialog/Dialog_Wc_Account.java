package wannabit.io.cosmostaion.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
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

public class Dialog_Wc_Account extends DialogFragment {

    private RecyclerView mRecyclerView;
    private TextView mDialogTitle;
    private AccountListAdapter mAccountListAdapter;

    private ArrayList<Account> mAccounts = new ArrayList<>();
    private OnDialogSelectListener mOnSelectListener = null;
    private Long id;

    public static Dialog_Wc_Account newInstance(Bundle bundle) {
        Dialog_Wc_Account frag = new Dialog_Wc_Account();
        frag.setArguments(bundle);
        return frag;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_template_recycler, null);
        mDialogTitle = view.findViewById(R.id.dialog_title);
        mDialogTitle.setText(R.string.str_select_account);
        mRecyclerView = view.findViewById(R.id.recycler);
        mAccounts = getSActivity().getBaseDao().onSelectAllAccountsByChainWithKey(WDp.getChainTypeByChainId(getArguments().getString("chainName")));
        id = getArguments().getLong("id");
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);
        mAccountListAdapter = new AccountListAdapter();
        mRecyclerView.setAdapter(mAccountListAdapter);

        Dialog dialog = new AlertDialog.Builder(getActivity()).setView(view).setOnDismissListener(dialogInterface -> mOnSelectListener.onCancel()).setCancelable(true).create();
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.layout_trans_with_border);
        return dialog;

    }

    public void setOnSelectListener(OnDialogSelectListener mOnSelectListener) {
        this.mOnSelectListener = mOnSelectListener;
    }


    private class AccountListAdapter extends RecyclerView.Adapter<AccountListAdapter.AccountHolder> {

        @NonNull
        @Override
        public AccountListAdapter.AccountHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new AccountListAdapter.AccountHolder(getLayoutInflater().inflate(R.layout.item_dialog_account, viewGroup, false));
        }

        @Override
        public void onBindViewHolder(@NonNull AccountListAdapter.AccountHolder holder, int position) {
            final Account account = mAccounts.get(position);
            final BaseChain baseChain = BaseChain.getChain(account.baseChain);
            final ChainConfig chainConfig = ChainFactory.getChain(baseChain);
            holder.accountKeyState.setColorFilter(ContextCompat.getColor(getSActivity(), chainConfig.chainColor()), android.graphics.PorterDuff.Mode.SRC_IN);
            holder.accountAddress.setText(account.address);

            if (TextUtils.isEmpty(account.nickName))
                holder.accountName.setText(getString(R.string.str_my_wallet) + account.id);
            else holder.accountName.setText(account.nickName);
            WDp.setDpSymbol(getSActivity(), getSActivity().getBaseDao(), chainConfig, chainConfig.mainDenom(), holder.accountDenom);
            holder.accountAvailable.setText(account.getLastTotal(getSActivity(), baseChain));
            holder.rootLayer.setOnClickListener(v -> {
                if (mOnSelectListener != null) {
                    mOnSelectListener.onSelect(id, account);
                }
                getDialog().dismiss();
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

    public interface OnDialogSelectListener {
        void onSelect(Long id, Account account);

        void onCancel();
    }
}
