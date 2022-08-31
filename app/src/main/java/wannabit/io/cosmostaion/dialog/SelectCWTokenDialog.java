package wannabit.io.cosmostaion.dialog;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.common.collect.Sets;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Set;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.dao.Cw20Asset;

public class SelectCWTokenDialog extends BottomSheetDialogFragment implements View.OnClickListener {

    private RecyclerView mRecyclerView;
    private TextView mDialogTitle;
    private ContractListAdapter mContractListAdapter;
    private Button mBtnCancel, mBtnConfirm;

    private ArrayList<Cw20Asset> mContractAssets = new ArrayList<>();
    private Set<String> checkedContractSet = Sets.newHashSet();
    private Account mAccount;

    public static SelectCWTokenDialog newInstance(Bundle bundle) {
        SelectCWTokenDialog frag = new SelectCWTokenDialog();
        frag.setArguments(bundle);
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_select_display_token, null);
        mDialogTitle = view.findViewById(R.id.dialog_title);
        mRecyclerView = view.findViewById(R.id.recycler);
        mBtnCancel = view.findViewById(R.id.btn_cancel);
        mBtnConfirm = view.findViewById(R.id.btn_confirm);

        mAccount = getSActivity().getBaseDao().onSelectAccount(getSActivity().getBaseDao().getLastUser());

        if (getTargetRequestCode() == 10) {
            mDialogTitle.setText(getString(R.string.str_select_contract_token));
            for (Cw20Asset asset : getSActivity().getBaseDao().mCw20Assets) {
                if (!asset.default_show) {
                    mContractAssets.add(asset);
                }
            }
            checkedContractSet = getSActivity().getBaseDao().getUserFavoTokens(mAccount.address);
        }

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);
        mContractListAdapter = new ContractListAdapter();
        mRecyclerView.setAdapter(mContractListAdapter);

        mBtnCancel.setOnClickListener(this);
        mBtnConfirm.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mBtnCancel)) {
            dismiss();

        } else if (v.equals(mBtnConfirm)) {
            getSActivity().getBaseDao().setUserFavoTokens(mAccount.address, checkedContractSet);
            getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, new Intent());
            dismiss();
        }
    }

    private class ContractListAdapter extends RecyclerView.Adapter<ContractListAdapter.ContractHolder> {

        @NonNull
        @Override
        public ContractListAdapter.ContractHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new ContractListAdapter.ContractHolder(getLayoutInflater().inflate(R.layout.item_dialog_contract, viewGroup, false));
        }

        @Override
        public void onBindViewHolder(@NonNull ContractListAdapter.ContractHolder holder, int position) {
            final Cw20Asset asset = mContractAssets.get(position);
            holder.itemDisplayToken.setOnCheckedChangeListener(null);
            Picasso.get().load(asset.assetImg()).fit().placeholder(R.drawable.token_default).error(R.drawable.token_default).into(holder.itemChainImg);
            holder.itemChainName.setText(asset.denom.toUpperCase());
            holder.itemDisplayToken.setChecked(checkedContractSet.contains(asset.contract_address));
            holder.itemDisplayToken.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if (isChecked) {
                    checkedContractSet.add(asset.contract_address);
                } else {
                    checkedContractSet.remove(asset.contract_address);
                }
            });
        }

        @Override
        public int getItemCount() {
            return mContractAssets.size();
        }

        public class ContractHolder extends RecyclerView.ViewHolder {
            private ImageView itemChainImg;
            private TextView itemChainName;
            private SwitchCompat itemDisplayToken;

            public ContractHolder(@NonNull View itemView) {
                super(itemView);
                itemChainImg = itemView.findViewById(R.id.chainImg);
                itemChainName = itemView.findViewById(R.id.chainName);
                itemDisplayToken = itemView.findViewById(R.id.switch_display_token);
            }
        }
    }

    private BaseActivity getSActivity() {
        return (BaseActivity) getActivity();
    }
}
