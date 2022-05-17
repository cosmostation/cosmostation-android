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
import wannabit.io.cosmostaion.utils.WDp;

public class DialogFragment_HtlcReceiveChain extends DialogFragment {

    private RecyclerView                mRecyclerView;
    private TextView                    mDialogTitle;
    private DestinationChainListAdapter mDestinationChainListAdapter;
    private ArrayList<BaseChain>        mToChainList;

    public static DialogFragment_HtlcReceiveChain newInstance(Bundle bundle) {
        DialogFragment_HtlcReceiveChain frag = new DialogFragment_HtlcReceiveChain();
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
        mDialogTitle           = view.findViewById(R.id.dialog_title);
        mDialogTitle.setText(R.string.str_select_receive_chain);
        mRecyclerView = view.findViewById(R.id.recycler);
        mToChainList = BaseChain.getHtlcSendable(BaseChain.getChain(getArguments().getString("chainName")));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);
        mDestinationChainListAdapter = new DestinationChainListAdapter();
        mRecyclerView.setAdapter(mDestinationChainListAdapter);
    }

    private class DestinationChainListAdapter extends RecyclerView.Adapter<DestinationChainListAdapter.DestinationChainHolder> {

        @NonNull
        @Override
        public DestinationChainHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new DestinationChainHolder(getLayoutInflater().inflate(R.layout.item_dialog_destination_chain, viewGroup, false));
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
