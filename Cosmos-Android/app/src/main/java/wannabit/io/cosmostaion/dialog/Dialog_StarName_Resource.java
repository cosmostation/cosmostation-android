package wannabit.io.cosmostaion.dialog;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import starnamed.x.starname.v1beta1.Types;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.utils.StarnameAssets;
import wannabit.io.cosmostaion.utils.StarnameResourceWrapper;
import wannabit.io.cosmostaion.utils.WUtil;

public class Dialog_StarName_Resource extends BottomSheetDialogFragment {

    private RecyclerView mRecyclerView;
    private ChainForResourceHolderAdapter mAdapter;
    private ArrayList<Types.Resource> mAlreadyChains;
    private ArrayList<StarnameAssets> mAllChains;

    public static Dialog_StarName_Resource newInstance(Bundle bundle) {
        Dialog_StarName_Resource frag = new Dialog_StarName_Resource();
        frag.setArguments(bundle);
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view  = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_starname_resource, null);
        mRecyclerView = view.findViewById(R.id.recycler);
        StarnameResourceWrapper wrapper = (StarnameResourceWrapper)getArguments().getSerializable("resources");
        mAlreadyChains = wrapper.array;
        mAllChains = StarnameAssets.getStarnameAssets();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new ChainForResourceHolderAdapter();
        mRecyclerView.setAdapter(mAdapter);
        return view;
    }

    private boolean alreadyHave(StarnameAssets toInsert) {
        for (Types.Resource already: mAlreadyChains) {
            if (already.getUri().equals(toInsert.url)) {
                return true;
            }
        }
        return false;
    }

    private class ChainForResourceHolderAdapter extends RecyclerView.Adapter<ChainForResourceHolderAdapter.ChainForResourceHolder> {
        @NonNull
        @Override
        public ChainForResourceHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new ChainForResourceHolder(getLayoutInflater().inflate(R.layout.item_dialog_destination_chain, viewGroup, false));
        }

        @Override
        public void onBindViewHolder(@NonNull ChainForResourceHolder holder, int position) {
            final StarnameAssets resource = mAllChains.get(position);
            if (alreadyHave(resource)) {
                holder.rootLayer.setBackground(getResources().getDrawable(R.drawable.box_et_gary));
            } else {
                holder.rootLayer.setBackground(getResources().getDrawable(R.drawable.box_et_white));
            }

            Picasso.get().load(StarnameAssets.getStarNameChainImgUrl(resource.url)).fit().into(holder.chainImg);
            holder.chainName.setText(StarnameAssets.getStarNameChainName(resource.url));
            holder.rootLayer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!alreadyHave(resource)) {
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
