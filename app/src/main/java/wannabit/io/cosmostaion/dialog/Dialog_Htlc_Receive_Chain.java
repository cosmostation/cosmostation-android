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
import wannabit.io.cosmostaion.utils.WDp;

public class Dialog_Htlc_Receive_Chain extends DialogFragment {

    private RecyclerView mRecyclerView;
    private TextView mDialogTitle;
    private DestinationChainListAdapter mDestinationChainListAdapter;
    private ArrayList<BaseChain> mToChainList;

    public static Dialog_Htlc_Receive_Chain newInstance(Bundle bundle) {
        Dialog_Htlc_Receive_Chain frag = new Dialog_Htlc_Receive_Chain();
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
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_template_recycler, null);
        mDialogTitle = view.findViewById(R.id.dialog_title);
        mDialogTitle.setText(R.string.str_select_receive_chain);
        mRecyclerView = view.findViewById(R.id.recycler);
        mToChainList = BaseChain.getHtlcSendable(BaseChain.getChain(getArguments().getString("chainName")));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);
        mDestinationChainListAdapter = new DestinationChainListAdapter();
        mRecyclerView.setAdapter(mDestinationChainListAdapter);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view);
        return builder.create();
    }

    private class DestinationChainListAdapter extends RecyclerView.Adapter<DestinationChainListAdapter.DestinationChainHolder> {

        @NonNull
        @Override
        public DestinationChainHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new DestinationChainHolder(getLayoutInflater().inflate(R.layout.item_dialog_ibc_receive_list, viewGroup, false));
        }

        @Override
        public void onBindViewHolder(@NonNull DestinationChainHolder holder, int position) {
            final BaseChain baseChain = mToChainList.get(position);
            WDp.onDpChain(getContext(), baseChain, holder.chainImg, holder.chainName);
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
            return mToChainList.size();
        }

        public class DestinationChainHolder extends RecyclerView.ViewHolder {
            LinearLayout rootLayer;
            ImageView chainImg;
            TextView chainName;

            public DestinationChainHolder(@NonNull View itemView) {
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
