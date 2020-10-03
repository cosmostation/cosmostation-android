package wannabit.io.cosmostaion.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.model.StarNameResource;

public class Dialog_StarName_Resource extends DialogFragment {

    private RecyclerView mRecyclerView;
    private ChainForResourceHolderAdapter mAdapter;
    private ArrayList<StarNameResource> mChainList;

    public static Dialog_StarName_Resource newInstance(Bundle bundle) {
        Dialog_StarName_Resource frag = new Dialog_StarName_Resource();
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
        View view  = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_htlc_receive_chain, null);
        mRecyclerView = view.findViewById(R.id.recycler);
        mChainList = getArguments().getParcelableArrayList("resources");
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new ChainForResourceHolderAdapter();
        mRecyclerView.setAdapter(mAdapter);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view);
        return builder.create();
    }



    private class ChainForResourceHolderAdapter extends RecyclerView.Adapter<ChainForResourceHolderAdapter.ChainForResourceHolder> {
        @NonNull
        @Override
        public ChainForResourceHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new ChainForResourceHolder(getLayoutInflater().inflate(R.layout.item_dialog_destination_chain, viewGroup, false));

        }

        @Override
        public void onBindViewHolder(@NonNull ChainForResourceHolder holder, int position) {
            final StarNameResource resource = mChainList.get(position);
            holder.chainImg.setImageDrawable(resource.getChainImg(getContext()));
            holder.chainName.setText(resource.getChainName());
            holder.rootLayer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("resource", resource);
                    getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, resultIntent);
                    getDialog().dismiss();
                }
            });

        }

        @Override
        public int getItemCount() {
            return mChainList.size();
        }

        public class ChainForResourceHolder extends RecyclerView.ViewHolder {
            RelativeLayout rootLayer;
            ImageView chainImg;
            TextView chainName;
            public ChainForResourceHolder(@NonNull View itemView) {
                super(itemView);
                rootLayer   = itemView.findViewById(R.id.rootLayer);
                chainImg    = itemView.findViewById(R.id.chainImg);
                chainName   = itemView.findViewById(R.id.chainName);
            }
        }
    }
}
