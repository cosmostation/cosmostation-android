package wannabit.io.cosmostaion.dialog;

import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_BNB;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_KAVA;

import android.app.Activity;
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
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.utils.WDp;

public class DialogFragment_HtlcReceivableAccounts extends DialogFragment {

    private RecyclerView       mRecyclerView;
    private TextView           mDialogTitle;
    private AccountListAdapter mAccountListAdapter;

    private ArrayList<Account> mAccounts = new ArrayList<>();

    public static DialogFragment_HtlcReceivableAccounts newInstance(Bundle bundle) {
        DialogFragment_HtlcReceivableAccounts frag = new DialogFragment_HtlcReceivableAccounts();
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
        mRecyclerView       = view.findViewById(R.id.recycler);
        mAccounts = getSActivity().getBaseDao().onSelectAccountsByHtlcClaim(BaseChain.getChain(getArguments().getString("chainName")));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);
        mAccountListAdapter = new AccountListAdapter();
        mRecyclerView.setAdapter(mAccountListAdapter);
    }



    private class AccountListAdapter extends RecyclerView.Adapter<AccountListAdapter.AccountHolder> {

        @NonNull
        @Override
        public AccountHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new AccountHolder(getLayoutInflater().inflate(R.layout.item_dialog_accountlist_account, viewGroup, false));
        }

        @Override
        public void onBindViewHolder(@NonNull AccountHolder holder, int position) {
            final Account account = mAccounts.get(position);
            final BaseChain baseChain = BaseChain.getChain(account.baseChain);
            holder.accountKeyState.setColorFilter(ContextCompat.getColor(getContext(), R.color.colorGray0), android.graphics.PorterDuff.Mode.SRC_IN);
            holder.accountAddress.setText(account.address);

            if(TextUtils.isEmpty(account.nickName)) holder.accountName.setText(getString(R.string.str_my_wallet) + account.id);
            else holder.accountName.setText(account.nickName);
            if (baseChain.equals(BaseChain.BNB_MAIN)) {
                if (account.hasPrivateKey) {
                    holder.accountKeyState.setColorFilter(ContextCompat.getColor(getContext(), R.color.colorBnb), android.graphics.PorterDuff.Mode.SRC_IN);
                }
                WDp.showCoinDp(getContext(), getSActivity().getBaseDao(), TOKEN_BNB, account.getBnbBalanceScale().toPlainString(), holder.accountDenom, holder.accountAvailable, baseChain);

            } else if (baseChain.equals(BaseChain.KAVA_MAIN)) {
                if (account.hasPrivateKey) {
                    holder.accountKeyState.setColorFilter(ContextCompat.getColor(getContext(), R.color.colorKava), android.graphics.PorterDuff.Mode.SRC_IN);
                }
                WDp.showCoinDp(getContext(), getSActivity().getBaseDao(), TOKEN_KAVA, account.getTokenBalance(TOKEN_KAVA).toPlainString(), holder.accountDenom, holder.accountAvailable, baseChain);
            }

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
                rootLayer           = itemView.findViewById(R.id.rootLayer);
                accountKeyState     = itemView.findViewById(R.id.accountKeyState);
                accountName         = itemView.findViewById(R.id.accountName);
                accountAddress      = itemView.findViewById(R.id.accountAddress);
                accountAvailable    = itemView.findViewById(R.id.accountAvailable);
                accountDenom        = itemView.findViewById(R.id.accountDenom);
            }
        }
    }

    private BaseActivity getSActivity() {
        return (BaseActivity)getActivity();
    }
}
