package wannabit.io.cosmostaion.dialog;

import android.os.Bundle;
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

import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseConstant;

public class NameConfirmDialog extends DialogFragment {

    public final static String NAME_BUNDLE_KEY = "name";
    public final static String MATCH_ADDRESS_BUNDLE_KEY = "matchAddress";

    public final static String CONFIRM_BUNDLE_KEY = "confirm";

    private RecyclerView mRecyclerView;
    private TextView mDialogTitle;
    private AccountListAdapter mAccountListAdapter;

    private String mNameService;
    private ArrayList<String> mMatchAddressList = new ArrayList<>();

    public static NameConfirmDialog newInstance(Bundle bundle) {
        NameConfirmDialog frag = new NameConfirmDialog();
        frag.setArguments(bundle);
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawableResource(R.color.colorTrans);
        View view = getLayoutInflater().inflate(R.layout.dialog_template_recycler, null);
        mDialogTitle = view.findViewById(R.id.dialog_title);
        mRecyclerView = view.findViewById(R.id.recycler);

        mNameService = getArguments().getString(NameConfirmDialog.NAME_BUNDLE_KEY);
        mMatchAddressList = (ArrayList<String>) getArguments().getSerializable(NameConfirmDialog.MATCH_ADDRESS_BUNDLE_KEY);

        mDialogTitle.setText(mNameService);

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
            return new AccountListAdapter.AccountHolder(getLayoutInflater().inflate(R.layout.item_dialog_icns, viewGroup, false));
        }

        @Override
        public void onBindViewHolder(@NonNull AccountListAdapter.AccountHolder holder, int position) {
            onBindReceiveAccountItemViewHolder(holder, position);
        }

        public void onBindReceiveAccountItemViewHolder(AccountListAdapter.AccountHolder holder, int position) {
            final String matchAddress = mMatchAddressList.get(position);
            if (mNameService.contains("*")) {
                holder.icnsImg.setImageResource(R.drawable.icon_iov_icns);
                holder.icnsAddress.setText(matchAddress);
            } else {
                holder.icnsImg.setImageResource(R.drawable.icon_icns);
                holder.icnsAddress.setText(matchAddress);
            }

            holder.rootLayer.setOnClickListener(v -> {
                Bundle result = new Bundle();
                result.putInt(BaseConstant.POSITION, position);
                getParentFragmentManager().setFragmentResult(NameConfirmDialog.CONFIRM_BUNDLE_KEY, result);
                dismiss();
            });
        }

        @Override
        public int getItemCount() {
            return mMatchAddressList.size();
        }

        public class AccountHolder extends RecyclerView.ViewHolder {
            RelativeLayout rootLayer;
            ImageView icnsImg;
            TextView icnsAddress;

            public AccountHolder(@NonNull View itemView) {
                super(itemView);
                rootLayer = itemView.findViewById(R.id.rootLayer);
                icnsImg = itemView.findViewById(R.id.icns_img);
                icnsAddress = itemView.findViewById(R.id.icns_address);
            }
        }
    }
}