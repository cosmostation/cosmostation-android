package wannabit.io.cosmostaion.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.dao.IbcPath;
import wannabit.io.cosmostaion.utils.WDp;

public class Dialog_IBC_Receive_Chain extends DialogFragment {

    private RecyclerView mRecyclerView;
    private RelayerListAdapter mRelayerListAdapter;
    private ArrayList<IbcPath> mIbcSendableRelayers = new ArrayList<>();

    public static Dialog_IBC_Receive_Chain newInstance(Bundle bundle) {
        Dialog_IBC_Receive_Chain frag = new Dialog_IBC_Receive_Chain();
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
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_ibc_receive_chain, null);
        mRecyclerView = view.findViewById(R.id.recycler);
        mIbcSendableRelayers = (ArrayList<IbcPath>) getArguments().getSerializable("chain");

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);
        mRelayerListAdapter = new RelayerListAdapter();
        mRecyclerView.setAdapter(mRelayerListAdapter);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view);
        return builder.create();
    }

    private class RelayerListAdapter extends RecyclerView.Adapter<RelayerListAdapter.RelayerListHolder> {

        @NonNull
        @Override
        public RelayerListHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new RelayerListHolder(getLayoutInflater().inflate(R.layout.item_dialog_ibc_receive_list, viewGroup, false));
        }

        @Override
        public void onBindViewHolder(@NonNull RelayerListHolder holder, int position) {
            final IbcPath ibcPath = mIbcSendableRelayers.get(position);
            final BaseChain toChain = WDp.getChainTypeByChainId(ibcPath.chain_id);
            holder.chainImg.setImageResource(toChain.getChainIcon());
            holder.chainName.setText(toChain.getChainAlterTitle());

            holder.rootLayer.setOnClickListener(v -> {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("position", position);
                getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, resultIntent);
                dismiss();
            });
        }

        @Override
        public int getItemCount() {
            return mIbcSendableRelayers.size();
        }

        public class RelayerListHolder extends RecyclerView.ViewHolder {
            LinearLayout rootLayer;
            ImageView chainImg;
            TextView chainName;

            public RelayerListHolder(@NonNull View itemView) {
                super(itemView);
                rootLayer = itemView.findViewById(R.id.rootLayer);
                chainImg = itemView.findViewById(R.id.chainImg);
                chainName = itemView.findViewById(R.id.chainName);
            }
        }

    }

    private BaseActivity getSActivity() {
        return (BaseActivity) getActivity();
    }

}
