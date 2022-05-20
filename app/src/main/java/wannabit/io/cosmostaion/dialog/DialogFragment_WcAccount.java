package wannabit.io.cosmostaion.dialog;

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
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.math.BigDecimal;
import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.utils.WDp;

public class DialogFragment_WcAccount extends DialogFragment {

    private RecyclerView       mRecyclerView;
    private TextView           mDialogTitle;
    private AccountListAdapter mAccountListAdapter;

    private ArrayList<Account> mAccounts = new ArrayList<>();
    private OnDialogSelectListener mOnSelectListener = null;
    private Long id;

    public static DialogFragment_WcAccount newInstance(Bundle bundle) {
        DialogFragment_WcAccount frag = new DialogFragment_WcAccount();
        frag.setArguments(bundle);
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(0));
        return inflater.inflate(R.layout.dialog_recycler_dialog_template, container);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mDialogTitle           = view.findViewById(R.id.dialog_title);
        mDialogTitle.setText(R.string.str_select_account);
        mRecyclerView = view.findViewById(R.id.recycler);
        mAccounts = getSActivity().getBaseDao().onSelectAllAccountsByChainWithKey(WDp.getChainTypeByChainId(getArguments().getString("chainName")));
        id = getArguments().getLong("id");
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);
        mAccountListAdapter = new AccountListAdapter();
        mRecyclerView.setAdapter(mAccountListAdapter);
    }

    public void setOnSelectListener(OnDialogSelectListener mOnSelectListener) {
        this.mOnSelectListener = mOnSelectListener;
    }


    private class AccountListAdapter extends RecyclerView.Adapter<AccountListAdapter.AccountHolder> {

        @NonNull
        @Override
        public AccountListAdapter.AccountHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new AccountListAdapter.AccountHolder(getLayoutInflater().inflate(R.layout.item_dialog_accountlist_account, viewGroup, false));
        }

        @Override
        public void onBindViewHolder(@NonNull AccountListAdapter.AccountHolder holder, int position) {
            final Account account = mAccounts.get(position);
            final BaseChain baseChain = BaseChain.getChain(account.baseChain);
            final int dpDecimal = WDp.mainDisplayDecimal(baseChain);
            holder.accountKeyState.setColorFilter(WDp.getChainColor(getContext(), baseChain), android.graphics.PorterDuff.Mode.SRC_IN);
            holder.accountAddress.setText(account.address);

            if (TextUtils.isEmpty(account.nickName))
                holder.accountName.setText(getString(R.string.str_my_wallet) + account.id);
            else holder.accountName.setText(account.nickName);
            WDp.DpMainDenom(getSActivity(), baseChain, holder.accountDenom);
            holder.accountAvailable.setText(WDp.getDpAmount2(getSActivity(), new BigDecimal(account.lastTotal), dpDecimal, 6));
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
    }
}
