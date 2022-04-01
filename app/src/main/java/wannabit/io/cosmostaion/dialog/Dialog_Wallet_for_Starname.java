package wannabit.io.cosmostaion.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.utils.StarnameAssets;
import wannabit.io.cosmostaion.utils.WDp;

public class Dialog_Wallet_for_Starname extends DialogFragment {

    private RecyclerView mRecyclerView;
    private WalletForStarNameAdapter mAdapter;

    private StarnameAssets mStarNameAsset;
    private String mUri;
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
        try {
            mStarNameAsset = getArguments().getParcelable("asset");
            mWalletList = getSActivity().getBaseDao().onSelectAccountsByChain(BaseChain.getChain(mStarNameAsset.chainName));
        } catch (Exception e) {
            mUri = getArguments().getString("chainUri");
            mWalletList = getSActivity().getBaseDao().onSelectAccountsByChain(BaseChain.getChain(StarnameAssets.getStarNameGetChain(mUri)));

        }
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_wallet_for_starname, null);
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
            holder.accountName.setText(account.getAccountTitle(getContext()));
            holder.accountContent.setOnClickListener(v -> {
                ((BaseActivity) getActivity()).onChoiceStarnameResourceAddress(account.address);
                getDialog().dismiss();
            });

        }

        @Override
        public int getItemCount() {
            return mWalletList.size();
        }

        public class WalletForStarNameHolder extends RecyclerView.ViewHolder {
            LinearLayout accountContent;
            ImageView accountArrowSort, accountKeyState;
            TextView accountName, accountAddress, accountAvailable, accountDenom;

            public WalletForStarNameHolder(@NonNull View itemView) {
                super(itemView);
                accountArrowSort = itemView.findViewById(R.id.accountArrowSort);
                accountContent = itemView.findViewById(R.id.accountContent);
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
