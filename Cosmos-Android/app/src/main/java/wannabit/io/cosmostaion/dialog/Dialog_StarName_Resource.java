package wannabit.io.cosmostaion.dialog;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.model.StarNameResource;
import wannabit.io.cosmostaion.utils.WUtil;

public class Dialog_StarName_Resource extends BottomSheetDialogFragment {

    private RecyclerView mRecyclerView;
    private ChainForResourceHolderAdapter mAdapter;
    private ArrayList<StarNameResource> mAlreadyChains;
    private ArrayList<StarNameResource> mAllChains;

    public static Dialog_StarName_Resource newInstance(Bundle bundle) {
        Dialog_StarName_Resource frag = new Dialog_StarName_Resource();
        frag.setArguments(bundle);
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view  = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_starname_resource, null);
        mRecyclerView = view.findViewById(R.id.recycler);
        mAlreadyChains = getArguments().getParcelableArrayList("resources");
        mAllChains = WUtil.getAllStarnameResources();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new ChainForResourceHolderAdapter();
        mRecyclerView.setAdapter(mAdapter);
        return view;
    }

    private class ChainForResourceHolderAdapter extends RecyclerView.Adapter<ChainForResourceHolderAdapter.ChainForResourceHolder> {
        @NonNull
        @Override
        public ChainForResourceHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new ChainForResourceHolder(getLayoutInflater().inflate(R.layout.item_dialog_destination_chain, viewGroup, false));
        }

        @Override
        public void onBindViewHolder(@NonNull ChainForResourceHolder holder, int position) {
            final StarNameResource resource = mAllChains.get(position);
            if (mAlreadyChains.contains(resource)) {
                holder.rootLayer.setBackground(getResources().getDrawable(R.drawable.box_et_gary));
            } else {
                holder.rootLayer.setBackground(getResources().getDrawable(R.drawable.box_et_white));
            }
            holder.chainImg.setImageDrawable(WUtil.getStarNameChainImg(getContext(), resource));
            holder.chainName.setText(WUtil.getStarNameChainName(resource));
            holder.rootLayer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!mAlreadyChains.contains(resource)) {
                        Intent resultIntent = new Intent();
                        resultIntent.putExtra("resource", resource);
                        getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, resultIntent);
                        getDialog().dismiss();
                    }
                }
            });

        }

        @Override
        public int getItemCount() {
            return mAllChains.size();
        }

        public class ChainForResourceHolder extends RecyclerView.ViewHolder {
            LinearLayout rootLayer;
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
