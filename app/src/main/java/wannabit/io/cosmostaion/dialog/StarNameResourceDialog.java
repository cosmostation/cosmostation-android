package wannabit.io.cosmostaion.dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import starnamed.x.starname.v1beta1.Types;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.utils.StarnameAssets;
import wannabit.io.cosmostaion.utils.StarnameResourceWrapper;

public class StarNameResourceDialog extends BottomSheetDialogFragment {

    public final static String STAR_NAME_RESOURCE_BUNDLE_KEY = "starNameResource";
    public final static String STAR_NAME_RESOURCE_WRAPPER_BUNDLE_KEY = "resources";

    private RecyclerView mRecyclerView;
    private TextView mtextView;
    private ChainForResourceHolderAdapter mAdapter;
    private ArrayList<Types.Resource> mAlreadyChains;
    private ArrayList<StarnameAssets> mAllChains;

    public static StarNameResourceDialog newInstance(Bundle bundle) {
        StarNameResourceDialog frag = new StarNameResourceDialog();
        frag.setArguments(bundle);
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_template_recycler, null);
        view.setBackgroundResource(R.color.colorWhite2DayNight);
        mtextView = view.findViewById(R.id.dialog_title);
        mtextView.setText(R.string.str_select_chain_for_address);
        mRecyclerView = view.findViewById(R.id.recycler);

        StarnameResourceWrapper wrapper = (StarnameResourceWrapper) getArguments().getSerializable(StarNameResourceDialog.STAR_NAME_RESOURCE_WRAPPER_BUNDLE_KEY);
        mAlreadyChains = wrapper.array;
        mAllChains = StarnameAssets.getStarnameAssets();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new ChainForResourceHolderAdapter();
        mRecyclerView.setAdapter(mAdapter);
        return view;
    }

    private boolean alreadyHave(StarnameAssets toInsert) {
        for (Types.Resource already : mAlreadyChains) {
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
                holder.rootLayer.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.box_et_gary));
            } else {
                holder.rootLayer.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.box_et_chain));
            }
            Picasso.get().load(StarnameAssets.getStarNameChainImgUrl(resource.url)).fit().placeholder(R.drawable.token_default).error(R.drawable.token_default).into(holder.chainImg);
            holder.chainName.setText(StarnameAssets.getStarNameChainName(resource.url));
            holder.rootLayer.setOnClickListener(v -> {
                if (!alreadyHave(resource)) {
                    Bundle result = new Bundle();
                    result.putParcelable("resource", resource);
                    getParentFragmentManager().setFragmentResult(StarNameResourceDialog.STAR_NAME_RESOURCE_BUNDLE_KEY, result);
                    dismiss();
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
                rootLayer = itemView.findViewById(R.id.rootLayer);
                chainImg = itemView.findViewById(R.id.chainImg);
                chainName = itemView.findViewById(R.id.chainName);
            }
        }
    }
}
