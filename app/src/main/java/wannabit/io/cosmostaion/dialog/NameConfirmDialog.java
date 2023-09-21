package wannabit.io.cosmostaion.dialog;

import static wannabit.io.cosmostaion.dao.NameService.NameServiceType;

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
import wannabit.io.cosmostaion.dao.NameService;
import wannabit.io.cosmostaion.utils.WLog;

public class NameConfirmDialog extends DialogFragment {

    public final static int SELECT_NAME_SERVICE_NAME = 8500;

    public final static String SELECT_NAME_SERVICE_BUNDLE_KEY = "nameserviceType";
    public final static String MATCH_NAME_SERVICE_BUNDLE_KEY = "nameservice";

    public final static String CONFIRM_BUNDLE_KEY = "confirm";

    private RecyclerView mRecyclerView;
    private TextView mDialogTitle;
    private AccountListAdapter mAccountListAdapter;
    
    private int mKeyValue;
    private ArrayList<NameService> mNameServices = new ArrayList<>();

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

        mKeyValue = getArguments().getInt(SELECT_NAME_SERVICE_BUNDLE_KEY);
        mNameServices = (ArrayList<NameService>) getArguments().getSerializable(MATCH_NAME_SERVICE_BUNDLE_KEY);

        if (mKeyValue == SELECT_NAME_SERVICE_NAME) {
            mDialogTitle.setText(getString(R.string.str_icns_confirm_title));
        } else {
            mDialogTitle.setText(mNameServices.get(0).name);
        }

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
            final NameService nameService = mNameServices.get(position);
            if (nameService.name.contains("*")) {
                holder.icnsImg.setImageResource(R.drawable.icon_iov_icns);
                holder.icnsAddress.setText(nameService.address);
            } else {
                if (nameService.type.equals(NameServiceType.ICNS)) {
                    holder.icnsImg.setImageResource(R.drawable.icon_icns);
                } else if (nameService.type.equals(NameServiceType.STARGAZE)) {
                    holder.icnsImg.setImageResource(R.drawable.icon_stargaze_ns);
                } else if (nameService.type.equals(NameServiceType.ARCHWAY)) {
                    holder.icnsImg.setImageResource(R.drawable.icon_archway_ns);
                } else if (nameService.type.equals(NameServiceType.ICNS_STARGAZE)) {
                    holder.icnsImg.setImageResource(R.drawable.icon_ns);
                } else {
                    holder.icnsImg.setImageResource(R.drawable.icon_icns_archway);
                }

                if (mKeyValue == SELECT_NAME_SERVICE_NAME) {
                    holder.icnsAddress.setText(nameService.name);
                } else {
                    holder.icnsAddress.setText(nameService.address);
                }
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
            return mNameServices.size();
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