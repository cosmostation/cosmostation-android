package wannabit.io.cosmostaion.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.AccountDetailActivity;
import wannabit.io.cosmostaion.activities.AccountListActivity;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WLog;
import wannabit.io.cosmostaion.utils.WUtil;

public class Dialog_Wallet_for_Starname extends DialogFragment {

    private RecyclerView mRecyclerView;
    private WalletForStarNameAdapter mAdapter;

    private String  mUri;
    private BaseChain mChain;
    private ArrayList<Account> mWalletList = new ArrayList<>();

    public static Dialog_Wallet_for_Starname newInstance(Bundle bundle) {
        Dialog_Wallet_for_Starname frag = new Dialog_Wallet_for_Starname();
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
        mUri = getArguments().getString("chainUri");
        mChain = WUtil.getBaseChainWithUri(mUri);
        mWalletList = getSActivity().getBaseDao().onSelectAccountsByChain(mChain);

        View view  = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_wallet_for_starname, null);
        mRecyclerView = view.findViewById(R.id.recycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new WalletForStarNameAdapter();
        mRecyclerView.setAdapter(mAdapter);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view);
        return builder.create();
    }

    private class WalletForStarNameAdapter extends RecyclerView.Adapter<WalletForStarNameAdapter.WalletForStarNameHolder> {
        @NonNull
        @Override
        public WalletForStarNameHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new WalletForStarNameHolder(getLayoutInflater().inflate(R.layout.item_dialog_wallet_for_starname, viewGroup, false));

        }

        @Override
        public void onBindViewHolder(@NonNull WalletForStarNameHolder holder, int position) {
            final Account account = mWalletList.get(position);
            WDp.DpMainDenom(getSActivity(), account.baseChain, holder.accountDenom);
            holder.accountAddress.setText(account.address);
            holder.accountAvailable.setText(account.getLastTotal(getSActivity(), BaseChain.getChain(account.baseChain)));
            holder.accountKeyState.setColorFilter(ContextCompat.getColor(getSActivity(), R.color.colorGray0), android.graphics.PorterDuff.Mode.SRC_IN);
            if (account.hasPrivateKey) {
                holder.accountKeyState.setColorFilter(WDp.getChainColor(getSActivity(), BaseChain.getChain(account.baseChain)), android.graphics.PorterDuff.Mode.SRC_IN);
            }

            if (TextUtils.isEmpty(account.nickName)){
                holder.accountName.setText(getString(R.string.str_my_wallet) + account.id);
            } else {
                holder.accountName.setText(account.nickName);
            }
            holder.accountContent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((BaseActivity)getActivity()).onChoiceStarnameResourceAddress(account.address);
                    getDialog().dismiss();
                }
            });

        }

        @Override
        public int getItemCount() {
            return mWalletList.size();
        }

        public class WalletForStarNameHolder extends RecyclerView.ViewHolder {
            LinearLayout accountContent;
            ImageView  accountArrowSort, accountKeyState;
            TextView accountName, accountAddress, accountAvailable, accountDenom;

            public WalletForStarNameHolder(@NonNull View itemView) {
                super(itemView);
                accountArrowSort    = itemView.findViewById(R.id.accountArrowSort);
                accountContent      = itemView.findViewById(R.id.accountContent);
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
