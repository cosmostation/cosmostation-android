package wannabit.io.cosmostaion.dialog;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawableResource(R.drawable.layout_trans_with_border);
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_template_recycler, null);
        mDialogTitle = view.findViewById(R.id.dialog_title);
        mRecyclerView = view.findViewById(R.id.recycler);
        mIbcSendableRelayers = (ArrayList<IbcPath>) getArguments().getSerializable("chain");
        mDialogTitle.setText(R.string.str_select_ibc_destination);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);
        mRelayerListAdapter = new RelayerListAdapter();
        mRecyclerView.setAdapter(mRelayerListAdapter);
        return view;
    }

    private class RelayerListAdapter extends RecyclerView.Adapter<RelayerListAdapter.RelayerListHolder> {

        @NonNull
        @Override
        public RelayerListAdapter.RelayerListHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new RelayerListAdapter.RelayerListHolder(getLayoutInflater().inflate(R.layout.item_dialog_receive_chian, viewGroup, false));
        }

        @Override
        public void onBindViewHolder(@NonNull RelayerListAdapter.RelayerListHolder holder, int position) {
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
            RelativeLayout rootLayer;
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
