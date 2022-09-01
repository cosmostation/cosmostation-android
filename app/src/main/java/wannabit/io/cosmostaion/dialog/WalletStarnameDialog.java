package wannabit.io.cosmostaion.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
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
import wannabit.io.cosmostaion.utils.StarnameAssets;
import wannabit.io.cosmostaion.utils.WDp;

public class WalletStarnameDialog extends DialogFragment {

    private RecyclerView mRecyclerView;
    private WalletForStarNameAdapter mAdapter;
    private TextView mDialogTitle;
    private StarnameAssets mStarNameAsset;
    private String mUri;
    private ArrayList<Account> mWalletList = new ArrayList<>();

    public static WalletStarnameDialog newInstance(Bundle bundle) {
        WalletStarnameDialog frag = new WalletStarnameDialog();
        frag.setArguments(bundle);
        return frag;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        try {
            mStarNameAsset = getArguments().getParcelable("asset");
            mWalletList = getSActivity().getBaseDao().onSelectAccountsByChain(BaseChain.getChain(mStarNameAsset.chainName));
        } catch (Exception e) {
            mUri = getArguments().getString("chainUri");
            mWalletList = getSActivity().getBaseDao().onSelectAccountsByChain(BaseChain.getChain(StarnameAssets.getStarNameGetChain(mUri)));
        }
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_template_recycler, null);
        mDialogTitle = view.findViewById(R.id.dialog_title);
        mRecyclerView = view.findViewById(R.id.recycler);

        mDialogTitle.setText(R.string.str_select_wallet_for_address);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new WalletForStarNameAdapter();
        mRecyclerView.setAdapter(mAdapter);

        setCancelable(true);
        Dialog dialog = new AlertDialog.Builder(getActivity()).setView(view).create();
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.layout_trans_with_border);
        return dialog;
    }

    private class WalletForStarNameAdapter extends RecyclerView.Adapter<WalletForStarNameAdapter.WalletForStarNameHolder> {
        @NonNull
        @Override
        public WalletForStarNameHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new WalletForStarNameHolder(getLayoutInflater().inflate(R.layout.item_dialog_account, viewGroup, false));

        }

        @Override
        public void onBindViewHolder(@NonNull WalletForStarNameHolder holder, int position) {
            final Account account = mWalletList.get(position);
            final BaseChain baseChain = BaseChain.getChain(account.baseChain);
            final ChainConfig chainConfig = ChainFactory.getChain(baseChain);
            WDp.setDpSymbol(getSActivity(), getSActivity().getBaseDao(), chainConfig, chainConfig.mainDenom(), holder.accountDenom);
            holder.accountAddress.setText(account.address);
            holder.accountAvailable.setText(account.getLastTotal(getSActivity(), baseChain));

            holder.accountKeyState.setColorFilter(ContextCompat.getColor(getSActivity(), R.color.colorGray0), android.graphics.PorterDuff.Mode.SRC_IN);
            if (account.hasPrivateKey) {
                holder.accountKeyState.setImageResource(R.drawable.key_off);
                holder.accountKeyState.setColorFilter(ContextCompat.getColor(getSActivity(), chainConfig.chainColor()), android.graphics.PorterDuff.Mode.SRC_IN);
            } else {
                holder.accountKeyState.setImageResource(R.drawable.watchmode);
                holder.accountKeyState.setColorFilter(null);
            }

            if (TextUtils.isEmpty(account.nickName)) {
                holder.accountName.setText(getString(R.string.str_my_wallet) + account.id);
            } else {
                holder.accountName.setText(account.nickName);
            }
            holder.accountlayer.setOnClickListener(v -> {
                ((BaseActivity) getActivity()).onChoiceStarnameResourceAddress(account.address);
                getDialog().dismiss();
            });

        }

        @Override
        public int getItemCount() {
            return mWalletList.size();
        }

        public class WalletForStarNameHolder extends RecyclerView.ViewHolder {
            RelativeLayout accountlayer;
            ImageView accountKeyState;
            TextView accountName, accountAddress, accountAvailable, accountDenom;

            public WalletForStarNameHolder(@NonNull View itemView) {
                super(itemView);
                accountlayer = itemView.findViewById(R.id.rootLayer);
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
