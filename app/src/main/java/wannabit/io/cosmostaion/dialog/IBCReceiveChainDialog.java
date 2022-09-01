package wannabit.io.cosmostaion.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
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
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.chains.ChainConfig;
import wannabit.io.cosmostaion.base.chains.ChainFactory;
import wannabit.io.cosmostaion.dao.IbcPath;
import wannabit.io.cosmostaion.utils.WDp;

public class IBCReceiveChainDialog extends DialogFragment {

    private RecyclerView mRecyclerView;
    private TextView mDialogTitle;
    private RelayerListAdapter mRelayerListAdapter;
    private ArrayList<IbcPath> mIbcSendableRelayers = new ArrayList<>();

    public static IBCReceiveChainDialog newInstance(Bundle bundle) {
        IBCReceiveChainDialog frag = new IBCReceiveChainDialog();
        frag.setArguments(bundle);
        return frag;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_template_recycler, null);
        mDialogTitle = view.findViewById(R.id.dialog_title);
        mRecyclerView = view.findViewById(R.id.recycler);
        mIbcSendableRelayers = (ArrayList<IbcPath>) getArguments().getSerializable("chain");

        mDialogTitle.setText(R.string.str_select_ibc_destination);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);
        mRelayerListAdapter = new RelayerListAdapter();
        mRecyclerView.setAdapter(mRelayerListAdapter);

        setCancelable(true);
        Dialog dialog = new AlertDialog.Builder(getActivity()).setView(view).create();
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.layout_trans_with_border);
        return dialog;
    }

    private class RelayerListAdapter extends RecyclerView.Adapter<RelayerListAdapter.RelayerListHolder> {

        @NonNull
        @Override
        public RelayerListHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new RelayerListHolder(getLayoutInflater().inflate(R.layout.item_dialog_receive_chian, viewGroup, false));
        }

        @Override
        public void onBindViewHolder(@NonNull RelayerListHolder holder, int position) {
            onBindIbcReceiveChainItemViewHoler(holder, position);
        }

        private void onBindIbcReceiveChainItemViewHoler(RelayerListHolder holder, int position) {
            final IbcPath ibcPath = mIbcSendableRelayers.get(position);
            final BaseChain toChain = WDp.getChainTypeByChainId(ibcPath.chain_id);
            final ChainConfig chainConfig = ChainFactory.getChain(toChain);
            holder.chainImg.setImageResource(chainConfig.chainImg());
            holder.chainName.setText(chainConfig.chainTitleToUp());

            holder.rootLayer.setOnClickListener(v -> {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("position", position);
                getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, resultIntent);
                getDialog().dismiss();
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
}
