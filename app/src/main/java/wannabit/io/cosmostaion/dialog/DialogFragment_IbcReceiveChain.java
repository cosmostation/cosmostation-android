package wannabit.io.cosmostaion.dialog;

import android.app.Activity;
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
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.dao.IbcPath;
import wannabit.io.cosmostaion.utils.WDp;

public class DialogFragment_IbcReceiveChain extends DialogFragment {

    private RecyclerView            mRecyclerView;
    private TextView                mDialogTitle;
    private RelayerListAdapter      mRelayerListAdapter;
    private ArrayList<IbcPath>      mIbcSendableRelayers = new ArrayList<>();

    public static DialogFragment_IbcReceiveChain newInstance(Bundle bundle) {
        DialogFragment_IbcReceiveChain frag = new DialogFragment_IbcReceiveChain();
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
        mDialogTitle = view.findViewById(R.id.dialog_title);
        mDialogTitle.setText(R.string.str_select_ibc_destination);
        mRecyclerView = view.findViewById(R.id.recycler);
        mIbcSendableRelayers = (ArrayList<IbcPath>) getArguments().getSerializable("chain");
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);
        mRelayerListAdapter = new RelayerListAdapter();
        mRecyclerView.setAdapter(mRelayerListAdapter);
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
            WDp.getChainImg(getSActivity(), toChain, holder.chainImg);
            WDp.getChainTitle2(getSActivity(), toChain, holder.chainName);

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
            return mIbcSendableRelayers.size();
        }

        public class RelayerListHolder extends RecyclerView.ViewHolder {
            LinearLayout rootLayer;
            ImageView chainImg;
            TextView chainName;
            public RelayerListHolder(@NonNull View itemView) {
                super(itemView);
                rootLayer   = itemView.findViewById(R.id.rootLayer);
                chainImg    = itemView.findViewById(R.id.chainImg);
                chainName   = itemView.findViewById(R.id.chainName);
            }
        }

    }

    private BaseActivity getSActivity() {
        return (BaseActivity)getActivity();
    }

}
